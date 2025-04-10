<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PHP Type Providers

<link-summary>Providing element type information for PHP type inference mechanism.</link-summary>

Type inference in PhpStorm is built on top of type providers, each of which is responsible for inferring the types of
specific PSI elements.
For example, `com.jetbrains.php.lang.psi.resolve.types.PhpArrayAccessTP` is responsible for inferring the types of expressions like `$arr[10]`.
There are dozens of such providers, and they all work one after another to provide type information when needed.

All providers inherit from `com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider4`, which is registered in
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.jetbrains.php.typeProvider4"/></include>.

## Types in PhpStorm

The first phase of type inference takes place at the indexing stage.
At this phase, PhpStorm calls `PhpTypeProvider4.getType()` on each type provider.
PhpStorm only has access to local information from the current file and can't use information from other files as well as indexes because it doesn't yet build them.
Sometimes, it can deduce the exact type from this information, but in other cases this is impossible because PhpStorm requires information from other files.

Because of this, there are two kinds of types in PhpStorm:

- Complete types
- Incomplete types

### Complete Types

Complete types are types that are known exactly based on **only** the local information of the current file.

```php
<?php

$a = 100;
```

The type of the expression that's assigned to the `$a` variable is Complete type `int`, since PhpStorm can definitely infer that a numeric literal is of type `int`.

```php
<?php

function foo(string $a): string {
  echo $a;
}
```

Here, since the `$a` parameter has a `string` type hint, PhpStorm can infer the Complete type `string`.

### Incomplete Types

Incomplete types are types that need additional information from other project files besides the containing file.

Suppose we have two files:

<path>foo.php</path>:

```php
<?php

function foo(): string {
  return "Hello World!";
}
```

<path>main.php</path>:

```php
<?php

$a = foo();
echo $a;
```

In the <path>main.php</path> file, we call the `foo()`, which is defined in another <path>foo.php</path> file.
Because of this, PhpStorm won't be able to infer the type of the `$a` variable during the indexing stage, since it depends on the definition of the `foo()` from another file.

For such cases, PhpStorm will create an Incomplete type in which writes all the necessary information to resolve the type when it finishes indexing.
In this case, it's the name of the function being called, so PhpStorm will create an Incomplete type `#F\foo`.

#### Incomplete Types Structure

At the beginning there is a `#` character, which is a marker that the type is Incomplete.

It's followed by the type provider's unique key.
PhpStorm uses this key to decide which type provider to pass the Incomplete type to resolve.

The rest of the line is the encoded information.
In the example above, this is the fully qualified name of the function.

PhpStorm will further pass the created Incomplete type after it finishes the indexing to resolve it into a Complete type.
It will choose instance of `PhpTypeProvider4` that have `PhpTypeProvider4.getKey()` equal to the character after `#`.
In other words, same provider is supposed to provide incomplete type starting with `#` + result of call `PhpTypeProvider4.getKey()`, this will result in that this type will be passes to the same `PhpTypeProvider4.complete()` instance during resolve.

During indexing, PhpStorm collects information about all types of elements in this way and stores them in the index.
When there is a need for the type of some expression, PhpStorm passes the Incomplete type obtained at the indexing stage for resolving.

#### Incomplete Types Resolving

The second phase of type inference is the global Incomplete type resolution.
At this phase, PhpStorm calls `PhpTypeProvider4.complete()` of each type provider.
All Incomplete types are passed to the providers that created them.
At this point, PhpStorm can access any information from other files to resolve the Incomplete type.

### Union Types

Since PHP is a dynamically typed language, at the type inference stage PhpStorm can get a situation where the type can be either one or the other.

For example:

```php
<?php

$a = 100;
if (rand(0, 10) > 5) {
  $a = "Hello World!";
}

$a; // (1)
```

In (1), the variable `$a` will be of type `int|string` because PhpStorm can't deduce exactly which branch the execution will take.

PhpStorm stores this types as separate strings inside the [`PhpType`](#phptype) class.
Each of the types can be either Complete or Incomplete.
In the Incomplete type resolving process, PhpStorm will resolve each union type individually.

Since some providers may return types for the same PSI element, union types may appear for some elements.

## `PhpType`

PhpStorm uses the `com.jetbrains.php.lang.psi.resolve.types.PhpType` class to work with types.

To add types to it, use `add()`, which can take either another `PhpType` or a `string`.

To check that a type is Complete, use `isComplete()`.

To resolve the Incomplete type, use `global()`.
This method shouldn't be used during indexing, namely inside `PhpTypeProvider4.getType()`.

### How to get `PhpType` from PSI?

In PhpStorm, PSI elements with types implement the `com.jetbrains.php.lang.psi.elements.PhpTypedElement` interface.
To get the type of element, use the `getType()`.

## `PhpTypeProvider4` Implementation

`PhpTypeProvider4` interface:

```java
/**
 * Extension point to implement to provide Type information on various PhpPsiElements.
 */
public interface PhpTypeProvider4 {
  ExtensionPointName<PhpTypeProvider4> EP_NAME = ExtensionPointName.create("com.jetbrains.php.typeProvider4");

  /**
   * @return Your custom signature key, i.e. "Я". Do not use any of PhpTypeSignatureKey.XXX constants though!
   */
  char getKey();

  /**
   * @param element to deduce type for - using only LOCAL info. <b>THIS IS MOST CRUCIAL ASPECT TO FOLLOW</b>
   * @return type for element, null if no insight. You can return a custom signature here to be later decoded by getBySignature.
   */
  @Nullable
  PhpType getType(PsiElement element);

  /**
   * @param expression to complete - Here you can use index lookups
   * @param project well so you can reach the PhpIndex based stuff
   * @return type for element, null if no insight. You can return a custom signature here to be later decoded by getBySignature.
   */
  @Nullable
  PhpType complete(String expression, Project project);

  /**
   * Here you can extend the signature lookups
   * @param expression Signature expression to decode. You can use PhpIndex.getBySignature() to look up expression internals.
   * @param visited Recursion guard: please pass this on into any phpIndex calls having same parameter
   * @param depth Recursion guard: please pass this on into any phpIndex calls having same parameter
   * @param project well so you can reach the PhpIndex
   * @return null if no match
   */
  Collection<? extends PhpNamedElement> getBySignature(String expression, Set<String> visited, int depth, Project project);

  default boolean emptyResultIsComplete() {
    return false;
  }

  @Internal
  default boolean interceptsNativeSignature() {
    return false;
  }

}
```

To implement `PhpTypeProvider4`, you need to override 4 methods:

1. `getKey()` returns a character that will be unique for this type provider.
   This can be any character, as long as it's unique, for example, PhpStorm uses hieroglyphs.
   See also `com.jetbrains.php.lang.psi.resolve.types.PhpCharBasedTypeKey`.

   > When choosing a provider key, keep in mind that other plugins may already be using it.
   > In this case, there will be an error stating that the key is duplicated, which will lead to incorrect work of both plugins.
   >
   {style="note"}

2. `getType()` returns the type of the expression for the given element.
   It's called at the indexing stage, and therefore its implementation **can't access** any information from the index and **must** rely only on local information.
   If you need some information, then pack the required data into a string and return an Incomplete type based on this string.

3. `complete()` resolves an Incomplete type into a Complete type.
   All strings of Incomplete types are sequentially passed to this method, it should return a Complete type for them.

4. `getBySignature()` provides additional elements or references.

You can also override the `emptyResultIsComplete()`, which indicates whether the `null` returned from the `complete()` is a valid result, which means that PhpStorm won't add the `mixed` to the resulting type.

## Example Implementation

The goal of this example is to provide types for field references assigned in `setUp` method if containing class is PHPUnit one.

### Define a `PhpUnitFiledInitializedInSetUpMethodsTP`

```java
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.*;
import com.jetbrains.php.lang.psi.elements.impl.PhpClassImpl;
import com.jetbrains.php.phpunit.PhpUnitUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

public class PhpUnitFiledInitializedInSetUpMethodsTP implements PhpTypeProvider4 {

  @Override
  public char getKey() {
    return 'Ю';
  }

  @Nullable
  @Override
  public PhpType getType(PsiElement element) {
    if (element instanceof Field) {
      PhpClass phpClass = ((Field) element).getContainingClass();
      if (phpClass != null) {
        MultiMap<String, AssignmentExpression> accessMap = PhpClassImpl.getPhpUnitSetUpAssignmentsPerField(phpClass);
        if (accessMap.containsKey(((Field) element).getName())) {
          Collection<AssignmentExpression> expressions = accessMap.get(((Field) element).getName());
          for (AssignmentExpression expression : expressions) {
            PhpType type = expression.getType();
            if (!type.isEmpty()) {
              PhpType phpType = new PhpType();
              String classFQN = phpClass.getFQN();
              for (String t : type.getTypes()) {
                phpType.add("#" + getKey() + classFQN + getKey() + t);
              }
              return phpType;
            }
          }
        }
      }
    }
    return null;
  }

  @Nullable
  @Override
  public PhpType complete(String expression, Project project) {
    int indexOfSign = expression.indexOf(getKey());
    int indexOfDelimiter = expression.indexOf(getKey(), indexOfSign + 1);
    String classFqn = expression.substring(indexOfSign + 1, indexOfDelimiter);
    String type = expression.substring(indexOfDelimiter + 1);
    if (isPhpUnitClass(project, classFqn)) {
      return new PhpType().add(type);
    }
    return null;
  }

  static boolean isPhpUnitClass(Project project, @NotNull String classFqn) {
    return PhpIndex.getInstance(project).getClassesByFQN(classFqn).stream().anyMatch(PhpUnitUtil::extendsRootTestClass);
  }

  @Override
  public Collection<? extends PhpNamedElement> getBySignature(String expression, Set<String> visited, int depth, Project project) {
    return null;
  }

}
```

### Register the `PhpUnitFiledInitializedInSetUpMethodsTP`

The `PhpUnitFiledInitializedInSetUpMethodsTP` implementation is registered with the IntelliJ Platform in the
plugin configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.jetbrains.php.typeProvider4"/></include>.

```xml
<extensions defaultExtensionNs="com.jetbrains">
  <php.typeProvider4
          implementation="com.jetbrains.php.lang.psi.resolve.types.PhpUnitFiledInitializedInSetUpMethodsTP"/>
</extensions>
```
