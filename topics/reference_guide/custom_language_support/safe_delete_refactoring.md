<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Safe Delete Refactoring

<link-summary>Implementing the Safe Delete feature allowing to adjust the code that uses a deleted element.</link-summary>

<tldr>

**Product Help:** [Safe delete](https://www.jetbrains.com/help/idea/safe-delete.html)

</tldr>

The _Safe Delete_ refactoring also builds on the same [](find_usages.md) framework as [](rename_refactoring.md).

In addition to that, to support _Safe Delete_, a plugin needs to implement two things:

* The
   [`RefactoringSupportProvider`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java)
   interface, registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.refactoringSupport"/></include>, and the `isSafeDeleteAvailable()` method, which checks if the _Safe Delete_ refactoring is available for a specific PSI element

* The
   [`PsiElement.delete()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java)
   method for the
   [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java)
   subclasses for which _Safe Delete_ is available.
   Deleting PSI elements is implemented by deleting the underlying AST nodes from the AST tree (which, in turn, causes the text ranges corresponding to the AST nodes to be deleted from the document).

**Example:**
[`delete()`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java) implementation for a Property in  [Properties language plugin](%gh-ic%/plugins/properties)

If needed, it's possible to further customize how _Safe Delete_ is performed for a particular type of element (e.g., how references are searched) via [`SafeDeleteProcessorDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/safeDelete/SafeDeleteProcessorDelegate.java).

**Example:**
[`SafeDeleteProcessorDelegate`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/refactoring/PropertiesFilesSafeDeleteProcessor.java) implementation for [Properties language plugin](%gh-ic%/plugins/properties)
