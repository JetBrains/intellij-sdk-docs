---
title: Navigating the PSI
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

There are three main ways to navigate the PSI: *top-down*, *bottom-up*, and using *references*. In the first scenario, 
you have a PSI file or another higher-level element (for example, a method), and you need to find all elements that match a
specified condition (for example, all variable declarations). In the second scenario, you have a specific point
in the PSI tree (for example, the element at caret) and need to find out something about its context (for example,
the element in which it has been declared). Finally, *references* allow you to navigate from the use of an element
(e.g., a method call) to the declaration (the method being called) and back. References are described in a
[separate topic](psi_references.md).


## Top-Down Navigation

The most common way to perform top-down navigation is to use a *visitor*. To use a visitor, you create a class
(usually an anonymous inner class) that extends the base visitor class, override the methods that handle the elements
you're interested in, and pass the visitor instance it to `PsiElement.accept()`.

The base classes for visitors are language-specific. For example, if you need to process elements in a Java file,
you extend `JavaRecursiveElementVisitor` and override the methods corresponding to the Java element types you're
interested in. 

The following snippet shows the use of a visitor to find all Java local variable declarations:

```java
file.accept(new JavaRecursiveElementVisitor() {
  @Override
  public void visitLocalVariable(PsiLocalVariable variable) {
    super.visitLocalVariable(variable);
    System.out.println("Found a variable at offset " + variable.getTextRange().getStartOffset());
  }
});
```

In many cases, you can also use more specific APIs for top-down navigation. For example, if you need to get a list of
all methods in a Java class, you can do that using a visitor, but a much easier way to do that is to call `PsiClass.getMethods()`.

[`PsiTreeUtil`](upsource:///platform/core-api/src/com/intellij/psi/util/PsiTreeUtil.java) contains a number of
general-purpose, language-independent functions for PSI tree navigation, some of which (for example, `findChildrenOfType()`)
perform top-down navigation.

## Bottom-Up Navigation

The starting point for bottom-up navigation is either a specific element in the PSI tree (for example, the result of
resolving a reference), or an offset. If you have an offset, you can find the corresponding PSI element by calling
`PsiFile.findElementAt()`. This method returns the element at the lowest level of the tree (for example, an identifier),
and you need to navigate the tree up if you want to determine the broader context.

In most cases, bottom-up navigation is performed by calling `PsiTreeUtil.getParentOfType()`. This method goes up the
tree until it finds the element of the type you've specified. For example, to find the containing method, you call
`PsiTreeUtil.getParentOfType(element, PsiMethod.class)`.

In some cases, you can also use specific navigation methods. For example, to find the class where a method is contained,
you call `PsiMethod.getContainingClass()`.

The following snippet shows how these calls can be used together:

```java
PsiFile psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);
PsiElement element = psiFile.findElementAt(offset);
PsiMethod containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod.class);
PsiClass containingClass = containingMethod.getContainingClass();
```

To see how the navigation works in practice, please refer to the 
[code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/psi_demo/src/main/java/org/intellij/sdk/psi/PsiNavigationDemoAction.java).
