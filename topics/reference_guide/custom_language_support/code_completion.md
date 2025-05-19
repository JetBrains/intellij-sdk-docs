<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Completion

<link-summary>Implementing context-aware code completion in custom languages.</link-summary>

<tldr>

**Product Help:** [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html)

</tldr>

<link-summary>Providing reference and generic code completion.</link-summary>

Two types of code completion can be provided by custom language plugins: reference completion and contributor-based completion.

Reference completion is easier to implement but supports only the basic completion action.

Contributor-based completion provides more features, supports all completion types
(<ui-path>Code | Code Completion | Basic</ui-path> and <ui-path>Type Matching</ui-path>),
and can be used, for example, to implement keyword completion.

## Reference Completion

To fill the completion list, the IDE calls [`PsiReference.getVariants()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) either on the reference at the caret location or on a dummy reference that would be placed at the caret.
This method needs to return an array of objects containing either strings, [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) instances or instances of the [`LookupElement`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElement.java) class (see [Lookup Items](#lookup-items) below).
If a `PsiElement` instance is returned in the array, the completion list shows the icon for the element.

A common way to implement `getVariants()` is to use the same function for walking up the tree as in [`PsiReference.resolve()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java)
using a [`PsiScopeProcessor`](%gh-ic%/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) which collects all declarations passed to its `execute()` method and returns
them as an array for filling the completion list.

### Symbol Reference Completion

<primary-label ref="2020.3"/>

> This API is currently in development and thus in experimental state.
> See also [](declarations_and_references.md#references).
>
{style="warning"}

To provide completion variants by a `PsiSymbolReference` implement
[`PsiCompletableReference`](%gh-ic%/platform/analysis-api/src/com/intellij/model/psi/PsiCompletableReference.java).

## Contributor-Based Completion

Implementing [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) gives the greatest control over the operation of code completion.
Register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.completion.contributor"/></include> and specify the `language` attribute (unless it works on any supported language).

> Note that the Javadoc of that class contains a detailed FAQ for implementing code completion.
>
{style="note"}

The core scenario of using [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) consists of calling the `extend()` method and passing in the [Element Pattern](element_patterns.md)
specifying the context in which this completion variant is applicable.
The [`CompletionProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionProvider.java) then adds the items to show in the completion list.

Keep in mind that the pattern is checked against the leaf PSI element.
If you want to match a composite element, use `withParent()` or `withSuperParent()` methods.

If completion items do not depend on indexes (e.g., keywords), it can be marked as [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

**Examples:**

- [`CompletionContributor`](%gh-ij-plugins%/osmorc/src/org/osmorc/manifest/completion/OsgiManifestCompletionContributor.java) for completing keywords in MANIFEST.MF files.
- [Custom Language Support Tutorial: Completion Contributor](completion_contributor.md)

## Lookup Items

Items shown in the completion list are represented by instances of the [`LookupElement`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElement.java) interface.
These instances are typically created through the [`LookupElementBuilder`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElementBuilder.java) class.

If there's only one lookup item to be shown, the behavior can be customized via [`AutoCompletionPolicy`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/AutoCompletionPolicy.java).

### Lookup Item Attributes

Text
: Shown left-aligned.

Text attributes
: Text color/grayed text, Bold, Italic, Underlined, Strikeout

Tail text
: Shown next to the main item text (not used for prefix matching).
Can be grayed text.
Example: parameter list of a method

Type text
: Shown right-aligned in the lookup list.
Can be grayed text.
Example: containing class of a method

Icon/Type icon
: See [](icons.md).

Insert handler
: The [`InsertHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/InsertHandler.java) is called when the item is selected and performs additional modifications of the inserted text.
It can also [show the completion popup](#showing-completion-popup-programmatically) after insertion.
Example: add parentheses for a method call ([`ParenthesesInsertHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/util/ParenthesesInsertHandler.java))

### Runtime Presentation

The item presentation can also be performed via (re-usable) [`LookupElementRenderer`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElementRenderer.java).

For expensive calculations (for example, the presentation of an item to be shown depends on other items), use `LookupElementBuilder.withExpensiveRenderer()` to invoke the renderer in the background
while the completion list is already populating.

### Sorting

Use [`PrioritizedLookupElement`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/PrioritizedLookupElement.java) to control item sorting
if _all_ lookup items are guaranteed to be provided by this `CompletionContributor`.

## Code Completion FAQ

### Skipping Completion Popup

To skip the completion popup in the current context (for example, inside comments), implement [`CompletionConfidence`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionConfidence.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.completion.confidence"/></include>.

This can also be used to _prevent_ skipping provided by other plugins by returning `ThreeState.NO` and registering the extension with `order="first"`.

### Showing Completion Popup Programmatically

To trigger completion upon typing a specific character in the editor, override
[`TypedHandlerDelegate.checkAutoPopup()`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.typedHandler"/></include>.

If all conditions match, invoke [`AutoPopupController.scheduleAutoPopup()`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/AutoPopupController.java) and return `Result.STOP`.

### Completion Popup Events

Use [`LookupListener`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupListener.java) to receive notifications about completion popup lifecycle events.
