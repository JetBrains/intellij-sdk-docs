<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PSI Elements

<link-summary>Introduction to PSI elements.</link-summary>

A PSI (Program Structure Interface) file represents a hierarchy of PSI elements (so-called _PSI trees_).
A single [PSI file](psi_files.md) (itself being a PSI element) may expose several PSI trees in specific programming languages (see [](file_view_providers.md)).
A PSI element, in its turn, can have child PSI elements.

PSI elements and operations at the level of individual PSI elements are used to explore the source code's internal structure as it is interpreted by the IntelliJ Platform.
For example, you can use PSI elements to perform code analysis, such as [code inspections](https://www.jetbrains.com/help/idea/code-inspection.html) or [intention actions](https://www.jetbrains.com/idea/help/intention-actions.html).

The [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) class is the common base class for PSI elements.

> PSI classes for specific languages usually start with a language prefix, for example, [`JsonArray`](%gh-ic%/json/split/gen/com/intellij/json/psi/JsonArray.java).
>
> The Java PSI API, developed many years ago when there was no plan to support other languages, uses the `Psi` prefix, for example, [`PsiIdentifier`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiIdentifier.java) or [`PsiElementFactory`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiElementFactory.java).
> Don't confuse Java with the core PSI API.
>
> To use Java PSI API, [add a dependency](plugin_dependencies.md) on the Java plugin.

## How do I get a PSI element?

| Context                        | API                                                                                                                                                                                                                                                                                                                                                                                                |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](action_system.md)     | <p>[`AnActionEvent.getData(CommonDataKeys.PSI_ELEMENT)`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)</p><p>Note: If an editor is currently open and the element under caret is a [reference](psi_references.md), this will return the result of resolving the reference.</p>                                                                          |
| [PSI File](psi_files.md)       | <p>[`PsiFile.findElementAt(offset)`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFile.java) - This returns a leaf element at the specified offset, normally a lexer token. Use `PsiTreeUtil.getParentOfType()` to find the element of the exact type.</p><p>[`PsiRecursiveElementWalkingVisitor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiRecursiveElementWalkingVisitor.java)</p> |
| [Reference](psi_references.md) | [`PsiReference.resolve()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java)                                                                                                                                                                                                                                                                                                       |

## What can I do with PSI elements?

See [](psi_cookbook.md) and [](modifying_psi.md).
