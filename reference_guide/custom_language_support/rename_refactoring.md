---
title: Rename Refactoring
---


The operation of the Rename refactoring is quite similar to that of Find Usages.
It uses the same rules for locating the element to be renamed, and the same index of words for locating the files which may have references to the element being renamed.

When the rename refactoring is performed, the method
[PsiNamedElement.setName()](upsource:///platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
is called for the renamed element, and
[PsiReference.handleElementRename()](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
is called for all references to the renamed element.
Both of these methods perform basically the same action: replace the underlying AST node of the PSI element with the node containing the new text entered by the user.
Creating a fully correct AST node from scratch is quite difficult.
Thus, surprisingly, the easiest way to get the replacement node is to create a dummy file in the custom language so that it would contain the necessary node in its parse tree, build the parse tree and extract the necessary node from it.

**Example:**
[setName()](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)<!--#L58-->
implementation for a
[Properties language plugin](upsource:///plugins/properties)


Another interface related to the Rename refactoring is
[NamesValidator](upsource:///platform/lang-api/src/com/intellij/lang/refactoring/NamesValidator.java).
This interface allows a plugin to check if the name entered by the user in the `Rename` dialog is a valid identifier (and not a keyword) according to the custom language rules.
If an implementation of this interface is not provided by the plugin, Java rules for validating identifiers are used.
Implementations of
[NamesValidator](upsource:///platform/lang-api/src/com/intellij/lang/refactoring/NamesValidator.java)
are registered in the `com.intellij.lang.namesValidator` extension point.

**Example**:
[NamesValidator](upsource:///plugins/properties/src/com/intellij/lang/properties/PropertiesNamesValidator.java)
for
[Properties language plugin](upsource:///plugins/properties)


Further customization of the Rename refactoring processing is possible on multiple levels.
Providing a custom implementation of the
[RenameHandler](upsource:///platform/lang-api/src/com/intellij/refactoring/rename/RenameHandler.java)
interface allows you to entirely replace the UI and workflow of the rename refactoring, and also to support renaming something which is not a
[PsiElement](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
at all.

**Example**:
[RenameHandler](upsource:///plugins/properties/src/com/intellij/lang/properties/refactoring/rename/ResourceBundleFromEditorRenameHandler.java)
for renaming a resource bundle in the
[Properties language plugin](upsource:///plugins/properties)


If you're fine with the standard UI but need to extend the default logic of renaming, you can provide an implementation of the
[RenamePsiElementProcessor](upsource:///platform/lang-impl/src/com/intellij/refactoring/rename/RenamePsiElementProcessor.java)
interface.
This allows you to:

*  Rename an element different from the one on which the action was invoked (a super method, for example)

*  Rename multiple elements at once (if their names are linked according to the logic of your language)

*  Check for name conflicts (existing names etc.)

*  Customize how search for code references or text references is performed

*  etc.

**Example**:
[RenamePsiElementProcessor](upsource:///plugins/properties/src/com/intellij/lang/properties/refactoring/rename/RenamePropertyProcessor.java)
for renaming a property in
[Properties plugin language](upsource:///plugins/properties)
