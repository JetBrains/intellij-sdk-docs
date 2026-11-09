<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Navigating the PSI

<link-summary>Navigating the PSI tree elements to find required information about parents or children.</link-summary>

There are three main ways to navigate the PSI:
1. **Top-down**: There is a PSI file or another higher-level element (for example, a method), and the task is to find all elements that match a specified condition (for example, all variable declarations).
2. **Bottom-up**: There is a specific point in the PSI tree (for example, the element at caret), and the task is to find out something about its context (for example, the element in which it has been declared).
3. **References**: They provide navigation from the usages of an element (e.g., a method call) to the declaration (the method being called) and back.
References are described in a [separate topic](psi_references.md).

## Top-Down Navigation

The most common way to perform top-down navigation is to use a [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern).
To use a visitor, create a class (usually an anonymous inner class) that extends the base visitor class and overrides the methods that handle the elements of interest, and pass the visitor instance to `PsiElement.accept()`.

The base classes for visitors are language-specific.
For example, to process elements in a Java file, extend [`JavaRecursiveElementVisitor`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaRecursiveElementVisitor.java) and override the methods corresponding to the Java element types of interest.

The following snippet shows the use of a visitor to find all Java local variable declarations:

```java
file.accept(new JavaRecursiveElementVisitor() {
  @Override
  public void visitLocalVariable(@NotNull PsiLocalVariable variable) {
    super.visitLocalVariable(variable);
    System.out.println("Found a variable at offset " +
         variable.getTextRange().getStartOffset());
  }
});
```

In many cases, more specific APIs can also be used for top-down navigation.
For example, a visitor can collect all methods in a Java class, but a much easier way to do that is calling `PsiClass.getMethods()`.

[`PsiTreeUtil`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiTreeUtil.java) contains a number of general-purpose, language-independent functions for PSI tree navigation, some of which (for example, `findChildrenOfType()`) perform top-down navigation.

## Bottom-Up Navigation

The starting point for bottom-up navigation is either a specific element in the PSI tree (for example, the result of resolving a reference) or an offset.
To find the PSI element at a given offset, call `PsiFile.findElementAt()`.
This method returns the element at the lowest level of the tree (for example, an identifier), so navigate the tree up to determine the broader context.

In most cases, bottom-up navigation is performed by calling `PsiTreeUtil.getParentOfType()`.
This method goes up the tree until it finds the element of the specified type.
For example, to find the containing method, call `PsiTreeUtil.getParentOfType(element, PsiMethod.class)`.

In some cases, specific navigation methods are also available.
For example, to find the class where a method is contained, call `PsiMethod.getContainingClass()`.

The following snippet shows how these calls can be used together:

```java
PsiFile psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);
PsiElement element = psiFile.findElementAt(offset);
PsiMethod containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod.class);
PsiClass containingClass = containingMethod.getContainingClass();
```

To see how the navigation works in practice, please refer to the [code sample](%gh-sdk-samples-master%/psi_demo/src/main/java/org/intellij/sdk/psi/PsiNavigationDemoAction.java).
