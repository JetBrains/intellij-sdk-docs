---
title: Safe Delete Refactoring
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The _Safe Delete_ refactoring also builds on the same [Find Usages](find_usages.md) framework as [Rename Refactoring](rename_refactoring.md).

In addition to that, to support _Safe Delete_, a plugin needs to implement two things:

*  The
   [`RefactoringSupportProvider`](upsource:///platform/lang-api/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java)
   interface, registered in the `com.intellij.lang.refactoringSupport` extension point, and the `isSafeDeleteAvailable()` method, which checks if the _Safe Delete_ refactoring is available for a specific PSI element

*  The
   [`PsiElement.delete()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
   method for the
   [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
   subclasses for which _Safe Delete_ is available.
   Deleting PSI elements is implemented by deleting the underlying AST nodes from the AST tree (which, in turn, causes the text ranges corresponding to the AST nodes to be deleted from the document).


**Example:**
[`delete()`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)
implementation for a Property in 
[Properties language plugin](upsource:///plugins/properties/)


If needed, it's possible to further customize how _Safe Delete_ is performed for a particular type of element (e.g., how references are searched)
via [`SafeDeleteProcessorDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/safeDelete/SafeDeleteProcessorDelegate.java).


**Example**:
[`SafeDeleteProcessorDelegate`](upsource:///plugins/properties/src/com/intellij/lang/properties/refactoring/PropertiesFilesSafeDeleteProcessor.java)
implementation for
[Properties language plugin](upsource:///plugins/properties)
