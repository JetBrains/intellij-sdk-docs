[//]: # (title: PSI Elements)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A PSI (Program Structure Interface) file represents a hierarchy of PSI elements (so-called _PSI trees_).
A single [PSI file](psi_files.md) (itself being a PSI element) may expose several PSI trees in specific programming languages (see [](file_view_providers.md)).
A PSI element, in its turn, can have child PSI elements.

PSI elements and operations at the level of individual PSI elements are used to explore the source code's internal structure as it is interpreted by the IntelliJ Platform.
For example, you can use PSI elements to perform code analysis, such as [code inspections](https://www.jetbrains.com/help/idea/code-inspection.html) or [intention actions](https://www.jetbrains.com/idea/help/intention-actions.html).

The [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) class is the common base class for PSI elements.

## How do I get a PSI element?

* From an Action: `e.getData(CommonDataKeys.PSI_ELEMENT)`.
  Note: if an editor is currently open and the element under caret is a [reference](psi_references.md), this will return the result of resolving the reference.
  This may or may not be what you need.
* From a file by offset: `PsiFile.findElementAt()`.
  Note: this returns the lowest level element ("leaf") at the specified offset, normally a lexer token.
  Most likely, you should use `PsiTreeUtil.getParentOfType()` to find the element you really need.
* By iterating through a PSI file: using a [`PsiRecursiveElementWalkingVisitor`](upsource:///platform/core-api/src/com/intellij/psi/PsiRecursiveElementWalkingVisitor.java).
* By resolving a [reference](psi_references.md): `PsiReference.resolve()`

## What can I do with PSI elements?

See [](psi_cookbook.md).
