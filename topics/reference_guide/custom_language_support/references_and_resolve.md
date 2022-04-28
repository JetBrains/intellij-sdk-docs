[//]: # (title: References and Resolve)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

One of the most important and tricky parts in implementing a custom language PSI is resolving references.
Resolving references gives users the ability to navigate from a PSI element usage (accessing a variable, calling a method, etc.) to the declaration of that element (the variable's definition, a method declaration, and so on).

This feature is needed in order to support the <menupath>Navigate | Declaration or Usages</menupath> action invoked by <shortcut>Ctrl/Cmd+B</shortcut> or clicking the mouse button while holding <shortcut>Ctrl/Cmd</shortcut> key, and it is a prerequisite for implementing the [Find Usages](find_usages.md) action, the [Rename Refactoring](rename_refactoring.md) and [Code Completion](code_completion.md).

The <menupath>View | Quick Definition</menupath> action is based on the same mechanism, so it becomes automatically available for all references that can be resolved by the language plugin.
To customize the exact document range to show in the popup (e.g., include "surrounding" code or comments), provide [`ImplementationTextSelectioner`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) registered in `com.intellij.lang.implementationTextSelectioner` extension point.

## PsiReference

All PSI elements which work as references (for which the <menupath>Navigate | Declaration or Usages</menupath> action applies) need to implement the
[`PsiElement.getReference()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) method and to return a [`PsiReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java) implementation from that method.
The `PsiReference` can be implemented by the same class as `PsiElement`, or by a different class.
An element can also contain multiple references (for example, a string literal can contain multiple substrings which are valid fully-qualified class names), in which case it can implement `PsiElement.getReferences()` and return the references as an array.
To optimize `PsiElement.getReferences()` performance, consider implementing [`HintedReferenceHost`](upsource:///platform/core-api/src/com/intellij/psi/HintedReferenceHost.java) to provide additional hints.

The primary method of the [`PsiReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java) interface is `resolve()`, which returns the element to which the reference points, or `null` if it was not possible to resolve the reference to a valid element (for example, should it point to an undefined class).
The resolved element should implement the [`PsiNamedElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiNamedElement.java) interface.
In order to enable more advanced functionality, prefer implementing [`PsiNameIdentifierOwner`](upsource:///platform/core-api/src/com/intellij/psi/PsiNameIdentifierOwner.java) over [`PsiNamedElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiNamedElement.java) where possible.

> While the referencing element and the referenced element both may have a name, only the element which **introduces** the name (e.g., the definition `int x = 42`) needs to implement `PsiNamedElement`.
> The referencing element at the point of usage (e.g., the `x` in the expression `x + 1`) should not implement `PsiNamedElement` since it does not _have_ a name.
>
{type="note"}

A counterpart to the `resolve()` method is `isReferenceTo()`, which checks if the reference resolves to the specified element.
The latter method can be implemented by calling `resolve()` and comparing the result with the passed PSI element.
Still, additional optimizations are possible (for example, performing the tree walk only if the element text is equal to the text of the reference).

**Examples**:

- [Reference](upsource:///plugins/properties/src/com/intellij/lang/properties/ResourceBundleReference.java) to a ResourceBundle in the [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Reference Contributor](reference_contributor.md)

## Implementing Resolve Logic

There is a set of interfaces that can be used as a base for implementing resolve support, namely the [`PsiScopeProcessor`](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) interface and the [`PsiElement.processDeclarations()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) method.
These interfaces have several extra complexities that are unnecessary for most custom languages (like support for substituting Java generics types).
Still, they are required if the custom language can have references to Java code.
If Java interoperability is not required, the plugin can forgo the standard interfaces and provide its own, different implementation of resolve.

Please see also [](psi_performance.md#cache-results-of-heavy-computations).

The implementation of resolve based on the standard helper classes contains the following components:

* A class implements the [`PsiScopeProcessor`](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) interface, which gathers the possible declarations for the reference and stops the resolve process when it has successfully completed.
  The primary method which needs to be implemented is `execute()`, which is called to process every declaration encountered during the resolve, and returns `true` if the resolve needs to be continued or `false` if the declaration has been found.
  The methods `getHint()` and `handleEvent()` are used for internal optimizations and can be left empty in the `PsiScopeProcessor` implementations for custom languages.
* A function which walks the PSI tree up from the reference location until the resolve has successfully completed or until the end of the resolve scope has been reached.
  If the target of the reference is located in a different file, the file can be located, for example, using [`FilenameIndex.getFilesByName()`](upsource:///platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java) (if the file name is known) or by iterating through all custom language files in the project (`iterateContent()` in the
  [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface obtained
  from
  [`ProjectRootManager.getFileIndex()`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)).
* The individual PSI elements, on which the `processDeclarations()` method is called during the PSI tree walk.
  If a PSI element is a declaration, it passes itself to the `execute()` method of the `PsiScopeProcessor` passed to it.
  Also, if necessary, according to the language scoping rules, a PSI element can pass the `PsiScopeProcessor` to its child elements.

## Resolving to Multiple Targets

An extension of the [`PsiReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java) interface, which allows a reference to resolve to multiple targets, is the [`PsiPolyVariantReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java) interface.
The targets to which the reference resolves are returned from the `multiResolve()` method.
The <menupath>Navigate | Declaration or Usages</menupath> action for such references allows the user to choose a navigation target in a popup.
The implementation of `multiResolve()` can be also based on [`PsiScopeProcessor`](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java), and can collect all valid targets for the reference instead of stopping when the first valid target is found.
