[//]: # (title: Rename Refactoring)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Rename refactoring operation is quite similar to that of [Find Usages](find_usages.md).
It uses the same rules for locating the element to be renamed and the same index of words for finding the files that may have references to the element being renamed.

When the rename refactoring is performed, the method [`PsiNamedElement.setName()`](upsource:///platform/core-api/src/com/intellij/psi/PsiNamedElement.java) is called for the renamed element, and [`PsiReference.handleElementRename()`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java) is called for all references to the renamed element.
These methods perform basically the same action: replace the underlying AST node of the PSI element with the node containing the new text entered by the user.
Creating an entirely correct AST node from scratch is quite tricky.
Thus, surprisingly, the easiest way to get the replacement node is to create a dummy file in the custom language so that it would contain the necessary node in its parse tree, build the parse tree and extract the required node from it.

**Examples:**
- [`setName()`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java) implementation for a [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Reference Contributor](reference_contributor.md)

To disable renaming for specific elements, implement `com.intellij.openapi.util.Condition<T>` for PsiElement of type `T` and register it in `com.intellij.vetoRenameCondition` extension point.

### Name Validation
[`NamesValidator`](upsource:///platform/analysis-api/src/com/intellij/lang/refactoring/NamesValidator.java) allows a plugin to check if the name entered by the user in the `Rename` dialog is a valid identifier (and not a keyword) according to the custom language rules.
If an implementation of this interface is not provided by the plugin, Java rules for validating identifiers are used.
Implementations of `NamesValidator` are registered in the `com.intellij.lang.namesValidator` extension point.

**Example**:
[`PropertiesNamesValidator`](upsource:///plugins/properties/src/com/intellij/lang/properties/PropertiesNamesValidator.java) for [Properties language plugin](upsource:///plugins/properties)

Another way to check is
[`RenameInputValidator`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameInputValidator.java),
unlike `NamesValidator` it allows you to more flexibly check the entered name for correctness based on the rule defined in the `isInputValid()` method.

To determine which elements this validator will apply to, override the `getPattern()` method returning the pattern of the element to validate.

**Example**:
[`YAMLAnchorRenameInputValidator`](upsource:///plugins/yaml/src/org/jetbrains/yaml/resolve/YAMLAnchorRenameInputValidator.java) validating YAML language anchor names

`RenameInputValidator` can be extended to
[`RenameInputValidatorEx`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameInputValidatorEx.java)
to override the default error message.
The `getErrorMessage()` method should return a custom error message in case of an invalid name, or `null` otherwise.

Note that `getErrorMessage()` only works if all `RenameInputValidator` accept the new name in `isInputValid()` and the name is a valid identifier for the language of the element.

**Example**:
[`YamlKeyValueRenameInputValidator`](upsource:///plugins/yaml/src/org/jetbrains/yaml/refactoring/rename/YamlKeyValueRenameInputValidator.java) validating YAML language keys

Implementations of `RenameInputValidator` or `RenameInputValidatorEx` are registered in the `com.intellij.renameInputValidator` extension point.

### Custom Rename UI and Workflow
Further customization of the Rename refactoring processing is possible on multiple levels.
Providing a custom implementation of the [`RenameHandler`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) interface allows you to entirely replace the UI and workflow of the rename refactoring, and also to support renaming something which is not a [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) at all.

**Example**:
[`RenameHandler`](upsource:///plugins/properties/properties-resource-bundle-editor/src/com/intellij/lang/properties/refactoring/rename/ResourceBundleFromEditorRenameHandler.java) for renaming a resource bundle in the [Properties language plugin](upsource:///plugins/properties)

If you're okay with the standard UI but need to extend the default logic of renaming, you can provide an implementation of the [`RenamePsiElementProcessor`](upsource:///platform/lang-impl/src/com/intellij/refactoring/rename/RenamePsiElementProcessor.java) interface.
This allows you to:

* Rename an element different from the one on which the action was invoked (a super method, for example)
* Rename multiple elements at once (if their names are linked according to the logic of your language)
* Check for name conflicts (existing names, etc.)
* Customize how a search for code references or text references is performed
* etc.

**Example**:
[`RenamePsiElementProcessor`](upsource:///plugins/properties/src/com/intellij/lang/properties/refactoring/rename/RenamePropertyProcessor.java) for renaming a property in [Properties plugin language](upsource:///plugins/properties)
