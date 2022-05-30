[//]: # (title: PHP Type Providers)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Type inference in PhpStorm is built on top of type providers, each of which is responsible for inferring the types of
specific PSI elements.
For example, `com.jetbrains.php.lang.psi.resolve.types.PhpArrayAccessTP` is responsible for inferring the types of expressions like `$arr[10]`.
There are dozens of such providers, and they all work one after another to provide type information when needed.

All providers inherit from `com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider4`, which is registered in the `com.jetbrains.php.typeProvider4` extension point.

## Types in PhpStorm

The first phase of type inference in PhpStorm takes place at the indexing stage.
At this phase, the `PhpTypeProvider4.getType()` method is called on each type provider.
PhpStorm only has access to local information from the current file and cannot use information from others.
In some cases, the exact type can be deduced from this information, but in other cases this is not possible because information from other files is required.

Because of this, types in PhpStorm are divided into two types:

- Complete types
- Incomplete types

### Complete types

Complete types are types that are known exactly based on **only** the local information of the current file.

```
<?php

$a = 100;
```

The type of the expression that is assigned to the `$a` variable is Complete type `int`, since PhpStorm can definitely infer that a numeric literal is of type `int`.

```
<?php

function foo(): string {
  return "Hello World!";
}

$a = foo();
```

Here, since the `foo()` function call and its definition are in the same file, PhpStorm can infer the Complete type `string`.

### Incomplete types

Incomplete types are types that require additional information from other project files besides the containing file.

Suppose we have two files:

<path>foo.php</path>:

```
<?php

function foo(): string {
  return "Hello World!";
}
```

<path>main.php</path>:

```
<?php

$a = foo();
echo $a;
```

In the `main.php` file, we call the `foo()` function, which is defined in another `foo.php` file.
Because of this, PhpStorm will not be able to infer the type of the `$a` variable during the indexing stage, since it depends on the definition of the `foo()` function from another file.

For such cases, PhpStorm will create an Incomplete type in which write all the necessary information to resolve the type when indexing is finished.
In this case, it's the name of the function being called, so PhpStorm will create an Incomplete type `#F\foo`.

#### Incomplete Types Structure

At the very beginning there is a `#` character, which is a marker that the type is Incomplete.

It is followed by the type provider's unique key.
PhpStorm uses this key to determine which type provider to pass the Incomplete type to resolve.

The rest of the line is the encoded information.
In the example above, this is the fully qualified name of the function.

The created Incomplete type will be further passed to the `PhpTypeProvider4.complete()` method after indexing is done to resolve it into a Complete type.

During indexing, the IDE collects information about all types of elements in this way and stores them in the index.
When there is a need for the type of some expression, the type obtained at the Incomplete indexing stage is passed for resolving.

#### Incomplete Types Resolving

The second phase of type inference is the global Incomplete type resolution.
At this phase, the `PhpTypeProvider4.complete()` method is called on each type provider.
All Incomplete types are passed to the providers that created them.
PhpStorm can access any information from other files to resolve the Incomplete type.

### Union Types

Since PHP is a dynamically typed language, at the type inference stage PhpStorm can get a situation where the type can be either one or the other.

For example:

```
<?php

$a = 100;
if (rand(0, 10) > 5) {
  $a = "Hello World!";
}

$a; // (1)
```

In (1), the variable `$a` will be of type `int|string` because PhpStorm can't deduce exactly which branch the execution will take.

PhpStorm stores such types as separate strings inside the [`PhpType`](#phptype) class.
Each of the types can also be either Complete or Incomplete.
In the Incomplete type resolving process, each union type will be resolved individually.

Since some providers may return types for the same PSI element, union types may also appear for some elements.

## PhpType

PhpStorm uses the `com.jetbrains.php.lang.psi.resolve.types.PhpType` class to work with types.

To add types to it, use the `add()` method, which can take either another `PhpType` or a `string`.

To check that a type is Complete use the `isComplete()` method.

To resolve the Incomplete type, use the `global()` method.

### How to get PhpType from PSI?

In PhpStorm, PSI elements with types implement the `com.jetbrains.php.lang.psi.elements.PhpTypedElement` interface.
To get the type of element, use the `getType()` method.

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

1. `getKey()` is a method that returns a character that will be unique for this type provider.
   This can be any character, as long as it is unique, for example, PhpStorm uses hieroglyphs.
   See also `com.jetbrains.php.lang.psi.resolve.types.PhpCharBasedTypeKey`.

   > When choosing a provider key, keep in mind that other plugins may already be using it.
   > In this case, there will be an error stating that the key is duplicated, which will lead to incorrect work of both plugins.
   >
   {type="note"}

2. `getType()` is a method that returns the type of the expression for the given element.
   This method is called at the indexing stage and therefore its implementation **cannot access** any information
   from the index and **must** rely only on local information.
   If you need some information, then pack the required data into a string and return an Incomplete type based on this string.

3. `complete()` is a method that resolves an Incomplete type into a Complete type.
   All strings of Incomplete types are sequentially passed to this method, the method should return a Complete type for them.

4. `getBySignature()` is a method with which you can provide additional elements or references.

You can also override the `emptyResultIsComplete()` method, which indicates whether the `null` returned from the `complete()` method is a valid result, which means that the `mixed` type will not be added to the resulting type.

## Example Implementation

The goal of this example is to provide types for filed references assigned in `setUp` method if containing class is PHPUnit one.

### Define a PhpUnitFiledInitializedInSetUpMethodsTP

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

### Register the PhpUnitFiledInitializedInSetUpMethodsTP

The `PhpUnitFiledInitializedInSetUpMethodsTP` implementation is registered with the IntelliJ Platform in the
plugin configuration file using the `com.intellij.php.typeProvider4` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <php.typeProvider4
          implementation="com.jetbrains.php.lang.psi.resolve.types.PhpUnitFiledInitializedInSetUpMethodsTP"/>
</extensions>
```
