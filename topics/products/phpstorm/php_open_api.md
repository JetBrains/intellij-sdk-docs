[//]: # (title: PHP Open API)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Dependency in `plugin.xml`

```xml
<depends>com.jetbrains.php</depends>
```

## PHP PSI

`com.jetbrains.php.lang.psi.elements.*;`

## Utility Classes

## PHP Extension Points

> See [](php_extension_point_list.md) for the complete list.
>
{type="note"}

### `PhpTypeProvider`

Here is a code fragment that makes [PHPUnit field references in setUp support](https://youtrack.jetbrains.com/issue/WI-22143) work.

```xml
<php.typeProvider4
    implementation="com.jetbrains.php.lang.psi.resolve.types.PhpUnitFiledInitializedInSetUpMethodsTP"/>
```

Interface:

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
}
```

Sample implementation of provider.
The goal is to provide types for filed references assigned in `setUp` method if containing class is PHPUnit one.
Since during `getType` call it's impossible to access indices, we will encode all needed information and use it in 'complete' method where index access is allowed.

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
      PhpClass phpClass = ((Field)element).getContainingClass();
      if (phpClass != null) {
        MultiMap<String, AssignmentExpression> accessMap = PhpClassImpl.getPhpUnitSetUpAssignmentsPerField(phpClass);
        if (accessMap.containsKey(((Field)element).getName())) {
          Collection<AssignmentExpression> expressions = accessMap.get(((Field)element).getName());
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
