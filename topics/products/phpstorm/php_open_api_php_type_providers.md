[//]: # (title: PHP Type Providers)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Type inference in PhpStorm is built on top of type providers, each of which is responsible for inferring the types of specific PSI elements. For example, `com.jetbrains.php.lang.psi.resolve.types.PhpArrayAccessTP` is responsible for inferring the types of expressions like `$arr[10]`. There are dozens of such providers, and they all work one after another to provide type information when needed.

All providers inherit from `com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider4`, which is registered in the `com.jetbrains.php.typeProvider4` extension point.

## Types in PhpStorm

The first stage of type inference in PhpStorm takes place at the indexing stage, at this moment we don’t know anything about the functions that are in the code, we don’t know what types they return, we don’t know the classes and what methods are called, all this imposes restrictions of how types work in PhpStorm.

Because of this, types in PhpStorm are divided into two types:

- Complete types
- Incomplete types

Internally, types are stored in the index as strings, these strings can contain one type or several if it is a [Union Type](#union-types).

### Complete types

Complete types are types that are already known exactly, for example, in the following code:

```php
<?php
$a = 100;
```

The type of the expression that is assigned to the `$a` variable is Complete type `int`, since we can definitely say that a numeric literal is of type `int`.

```php
<?php

function foo(): string { return "Hello World!"; }
```

Here, since the `foo()` function has a type hint, the return type is also Complete.

### Incomplete types

Incomplete types are types that require additional resolving and contain additional information for accurate type inference.

Suppose we have the following code:

```php
<?php
class Foo {
  public function f() {}
}

function foo(): Foo { return new Foo(); }

foo()->f();
```

Here PhpStorm must somehow express the type of the expression `foo()` during indexing, however, during indexing, we cannot refer to the definition of the `foo()` function to return a specific type.

So PhpStorm creates an Incomplete type into which it writes all the information it might need to resolve the type, in this case the name of the function and its arguments.

For this case, PhpStorm will create a new Incomplete type `#琁\foo.`.

#### Incomplete Type Structure

At the very beginning there is a `#` character, which is a marker that the type is Incomplete.

It is followed by the type provider's unique key. According to it, you can understand that this type was created by the provider of the desired type.

The rest of the line is the encoded information. In the example above, it's the fully qualified name and a dot.

The created Incomplete type is returned from the function and will be processed further.

During indexing, the IDE collects information about all types of elements in this way and stores them in the index. When there is a need for the type of some expression, the type obtained at the Incomplete indexing stage is passed for resolving.

#### Incomplete Types Resolving

Incomplete types are resolved if it was necessary to infer the element type, since indexing has already been completed at this point, we have access to all information, for example, functions and their return types.

Receiving Incomplete type PhpStorm gets from it all the information that was placed there during the indexing stage, in the example above this is the name of the function and its arguments.

With this information, PhpStorm can access the index and get the information it needs. In the example above, this is the return type of the function.

As a result, the obtained Complete type is returned and will be processed further.

### Union Types

Since PHP is a dynamically typed language, at the type inference stage we can get a situation where the type is either one or the other. For example:

```php
<?php

$a = 100;
if (rand(0, 10) > 5) {
  $a = "Hello".
}

$a; // (1)
```

In (1), the variable `$a` will be of type `int|string` because we can't deduce exactly which branch the execution will take.

PhpStorm stores these types by separating each of the individual types with a `|` character. Each of the types can also be either Complete or Incomplete. In the Incomplete type resolution process, each union type will be resolved individually.

Since some providers may return types for the same PSI element, union types may also appear for some elements.

## PhpType

PhpStorm uses the `com.jetbrains.php.lang.psi.resolve.types.PhpType` class to work with types.

To add types to it, use the `add()` method, which can take either another `PhpType` or a `string`.
In this way, you can create a new type based on the Incomplete type, for example.

To check that a type is Complete use the `isComplete()` method.

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

In order to implement `PhpTypeProvider4`, you need to implement 4 methods.

1. `getKey()` is a method that returns a character that will be unique for this type provider. This can be any character, as long as it is unique, for example, PhpStorm uses hieroglyphs.
   For the convenience of checking that, for example, the Incomplete type is a type obtained from your provider, you can use the `PhpCharBasedTypeKey` class in which you need to overload the `getKey()` method that returns a character.
   In order to check that the type is created by your provider, it will be enough to call the `signed()` method, and in order to sign the type, it will be enough to call the `sign()` method.

2. `getType()` is a method that returns the type of the expression for the given element. This method is called at the indexing stage and therefore its implementation **cannot access** any information from the index. If some information is needed, then it is necessary to pack the required data into a string and return an Incomplete type based on this string.

3. `complete()` is a method that resolves an Incomplete type into a Complete type. All strings of Incomplete types are sequentially passed to this method, the method should return the Complete type for them.
   Note that Incomplete types from other type providers also come to this method, so you need to check that the incoming string belongs to your provider.

4. `getBySignature()` is a method with which you can provide additional elements or references.

You can also overload the `emptyResultIsComplete()` method, which indicates whether the `null` returned from the `complete()` method is a valid result. For example, if you index the type `int`, then the result returned from `complete()` will be `null` and it is a valid result.

**Examples:**

- [`TupleShapeTypeProvider`](https://github.com/unserialize/kphpstorm/blob/master/src/main/kotlin/com/vk/kphpstorm/typeProviders/TupleShapeTypeProvider.kt) for [KPHP language plugin](https://github.com/unserialize/kphpstorm)
- [`PsalmParamTypeProvider`](https://github.com/JetBrains/phpstorm-psalm-plugin/blob/eb8eb38fa1facc1e3614cc725ab76ec8bea80332/src/com/jetbrains/php/psalm/types/PsalmParamTypeProvider.java) for [Psalm plugin](https://github.com/JetBrains/phpstorm-psalm-plugin)

## Example Implementation

The goal is to provide types for filed references assigned in `setUp` method if containing class is PHPUnit one.

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
