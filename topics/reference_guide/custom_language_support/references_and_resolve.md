<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# References and Resolve

<link-summary>A PSI reference allows for resolving from a symbol usage to its declaration.</link-summary>

One of the most important and tricky parts in implementing a custom language PSI is resolving references.
Resolving references allows users to navigate from a PSI element usage (accessing a variable, calling a method, etc.) to the declaration of that element (the variable's definition, a method declaration, and so on).

This feature is required to support the <ui-path>Navigate | Declaration or Usages</ui-path> action invoked by <shortcut>Ctrl/Cmd+B</shortcut> or clicking the mouse button while holding <shortcut>Ctrl/Cmd</shortcut> key, and it is a prerequisite for implementing [finding usages](find_usages.md), [rename refactoring](rename_refactoring.md), and [code completion](code_completion.md).

The <ui-path>View | Quick Definition</ui-path> action is based on the same mechanism, so it becomes automatically available for all references that can be resolved by the language plugin.
To customize the exact document range to show in the popup (e.g., include "surrounding" code or comments), provide [`ImplementationTextSelectioner`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) registered
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.implementationTextSelectioner"/></include>.

## PSI References

All PSI elements which work as references (for which the <ui-path>Navigate | Declaration or Usages</ui-path> action applies) need to implement the
[`PsiElement.getReference()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) method and to return a [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) implementation from that method.
The `PsiReference` can be implemented by the same class as `PsiElement`, or by a different class.
An element can also contain multiple references (for example, a string literal can contain multiple substrings which are valid fully qualified class names), in which case it can implement `PsiElement.getReferences()` and return the references as an array.
To optimize `PsiElement.getReferences()` performance, consider implementing [`HintedReferenceHost`](%gh-ic%/platform/core-api/src/com/intellij/psi/HintedReferenceHost.java) to provide additional hints.

The primary method of the [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) interface is `resolve()`.
It returns the element to which the reference points, or `null` if it was not possible to resolve the reference to a valid element (for example, should it point to an undefined class).
The resolved element should implement the [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) interface.
To enable more advanced functionality, prefer implementing [`PsiNameIdentifierOwner`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNameIdentifierOwner.java) over [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) where possible.

> While the referencing element and the referenced element both may have a name, only the element which **introduces** the name (e.g., the definition `int x = 42`) needs to implement `PsiNamedElement`.
> The referencing element at the point of usage (e.g., the `x` in the expression `x + 1`) should not implement `PsiNamedElement` since it does not _have_ a name.
>
{style="note"}

A counterpart to the `resolve()` method is `isReferenceTo()`, which checks if the reference resolves to the specified element.
The latter method can be implemented by calling `resolve()` and comparing the result with the passed PSI element.
Still, additional optimizations are possible (for example, performing the tree walk only if the element text is equal to the text of the reference).

**Examples:**

- [Reference](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/ResourceBundleReference.java) to a ResourceBundle in the [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Reference Contributor](reference_contributor.md)

## Implementing Resolve Logic

There is a set of interfaces that can be used as a base for implementing resolve support, namely the [`PsiScopeProcessor`](%gh-ic%/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) interface and the [`PsiElement.processDeclarations()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) method.
These interfaces have several extra complexities that are unnecessary for most custom languages (like support for substituting Java generics types).
Still, they are required if the custom language can have references to Java code.
If Java interoperability is not required, the plugin can forgo the standard interfaces and provide its own, different implementation of resolve.

See also [](psi_performance.md#cache-results-of-heavy-computations).

The implementation of resolve based on the standard helper classes contains the following components:

* A class implements the [`PsiScopeProcessor`](%gh-ic%/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) interface, which gathers the possible declarations for the reference and stops the resolve process when it has successfully completed.
  The primary method which needs to be implemented is `execute()`, which is called to process every declaration encountered during the resolve, and returns `true` if the resolve needs to be continued or `false` if the declaration has been found.
  The methods `getHint()` and `handleEvent()` are used for internal optimizations and can be left empty in the `PsiScopeProcessor` implementations for custom languages.
* A function which walks the PSI tree up from the reference location until the resolve has successfully completed or until the end of the resolve scope has been reached.
  If the target of the reference is located in a different file, the file can be located, for example, using [`FilenameIndex.getFilesByName()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java) (if the file name is known) or by iterating through all custom language files in the project (`iterateContent()` in the
  [`ProjectFileIndex`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface obtained
  from
  [`ProjectRootManager.getFileIndex()`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)).
* The individual PSI elements, on which the `processDeclarations()` method is called during the PSI tree walk.
  If a PSI element is a declaration, it passes itself to the `execute()` method of the `PsiScopeProcessor` passed to it.
  Also, if necessary, according to the language scoping rules, a PSI element can pass the `PsiScopeProcessor` to its child elements.

## Resolving to Multiple Targets

[`PsiPolyVariantReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java) is an extension of [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) and allows a reference to resolve to multiple targets.
The targets to which the reference resolves are returned from the `multiResolve()` method.
The <ui-path>Navigate | Declaration or Usages</ui-path> action for such references allows the user to choose a navigation target in a popup.
The implementation of `multiResolve()` can be also based on [`PsiScopeProcessor`](%gh-ic%/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java), and can collect all valid targets for the reference instead of stopping when the first valid target is found.

> Consider using [`PsiPolyVariantReferenceBase`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiPolyVariantReferenceBase.java) as a base class for `PsiPolyVariantReference` implementations.

## Additional Highlighting
<primary-label ref="2022.2"/>

Implement [`HighlightedReference`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/highlighting/HighlightedReference.java) to add additional highlighting for non-obvious places (e.g., inside String literals).
Such references will automatically be highlighted using <control>String | Highlighted reference</control> text attributes from <ui-path>Settings | Editor | Color Scheme | Language Defaults</ui-path>.
