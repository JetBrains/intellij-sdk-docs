[//]: # (title: PSI Elements)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

A PSI (Program Structure Interface) file represents a hierarchy of PSI elements (so-called _PSI trees_).
A single [PSI file](psi_files.md) (itself being a PSI element) may expose several PSI trees in specific programming languages (see [](file_view_providers.md)).
A PSI element, in its turn, can have child PSI elements.

PSI elements and operations at the level of individual PSI elements are used to explore the source code's internal structure as it is interpreted by the IntelliJ Platform.
For example, you can use PSI elements to perform code analysis, such as [code inspections](https://www.jetbrains.com/help/idea/code-inspection.html) or [intention actions](https://www.jetbrains.com/idea/help/intention-actions.html).

The [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) class is the common base class for PSI elements.

## How do I get a PSI element?

| Context                          | API                                                                                                                                                                                                                                                                                                                                                                                               |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](basic_action_system.md) | [`AnActionEvent.getData(CommonDataKeys.PSI_ELEMENT)`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)<br/>Note: If an editor is currently open and the element under caret is a [reference](psi_references.md), this will return the result of resolving the reference.                                                                              |
| [PSI File](psi_files.md)         | [`PsiFile.findElementAt(offset)`](upsource:///platform/core-api/src/com/intellij/psi/PsiFile.java) - This returns a leaf element at the specified offset, normally a lexer token. Use `PsiTreeUtil.getParentOfType()` to find the element of the exact type.<br/>[`PsiRecursiveElementWalkingVisitor`](upsource:///platform/core-api/src/com/intellij/psi/PsiRecursiveElementWalkingVisitor.java) |
| [Reference](psi_references.md)   | [`PsiReference.resolve()`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)                                                                                                                                                                                                                                                                                                  |

## What can I do with PSI elements?

See [](psi_cookbook.md).
