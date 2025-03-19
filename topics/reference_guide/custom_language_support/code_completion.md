<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Completion

<link-summary>Implementing context-aware code completion in custom languages.</link-summary>

<tldr>

**Product Help:** [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html)

</tldr>

<link-summary>Providing reference and generic code completion.</link-summary>

Two main types of code completion can be provided by custom language plugins: reference completion and contributor-based completion.

Reference completion is easier to implement, but supports only the basic completion action.
Contributor-based completion provides more features, supports all three completion types (basic, smart, and class name), and can be used, for example, to implement keyword completion.

### Reference Completion

To fill the completion list, the IDE calls [`PsiReference.getVariants()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) either on the reference at the caret location or on a dummy reference that would be placed at the caret.
This method needs to return an array of objects containing either strings, [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) instances or instances of the [`LookupElement`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElement.java) class (see [Lookup Items](#lookup-items) below).
If a [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) instance is returned in the array, the completion list shows the icon for the element.

The most common way to implement `getVariants()` is to use the same function for walking up the tree as in [`PsiReference.resolve()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java), and a different implementation of [`PsiScopeProcessor`](%gh-ic%/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) which collects all declarations passed to its `execute()` method and returns them as an array for filling the completion list.

#### Symbol Reference Completion
<primary-label ref="2020.3"/>

> This API is currently in development and thus in experimental state.
>
{style="warning"}

To provide completion variants by a `PsiSymbolReference` implement
[`PsiCompletableReference`](%gh-ic%/platform/analysis-api/src/com/intellij/model/psi/PsiCompletableReference.java).

### Contributor-Based Completion

Implementing the [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) interface gives you the greatest control over the operation of code completion for your language.
Register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.completion.contributor"/></include> and specify `language` attribute (unless it works on any supported language).

> Note that the Javadoc of that class contains a detailed FAQ for implementing code completion.
>
{style="note"}

The core scenario of using [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) consists of calling the `extend()` method and passing in the [Element Pattern](element_patterns.md) specifying the context in which this completion variant is applicable, as well as a *completion provider* which generates the items to show in the completion list.

Keep in mind that the pattern is checked against the leaf PSI element.
If you want to match a composite element, use `withParent()` or `withSuperParent()` methods.

If completion items do not depend on indexes (e.g., keywords), it can be marked as [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

**Examples:**
- [`CompletionContributor`](%gh-ij-plugins%/osmorc/src/org/osmorc/manifest/completion/OsgiManifestCompletionContributor.java) for completing keywords in MANIFEST.MF files.
- [Custom Language Support Tutorial: Completion Contributor](completion_contributor.md)

### Lookup Items
Items shown in the completion list are represented by instances of the [`LookupElement`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElement.java) interface.
These instances are typically created through the [`LookupElementBuilder`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupElementBuilder.java) class.

For every lookup element, you can specify the following attributes:

* **Text** Shown left-aligned.
* **Tail text** Shown next to the main item text, is not used for prefix matching, and can be used, for example, to show the parameter list of the method.
* **Type text** Shown right-aligned in the lookup list and can be used to show the return type or containing class of a method, for example.
* **Icon**
* **Text attributes** Bold, Strikeout, etc.
* **Insert handler** The insert handler is a callback which is called when the item is selected and can be used to perform additional modifications of the text (for example, to put in the parentheses for a method call)

### Code Completion FAQ

#### Showing Completion Popup Programmatically

Use [`AutoPopupController.scheduleAutoPopup()`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/AutoPopupController.java).

#### Completion Popup Events

Use [`LookupListener`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/lookup/LookupListener.java) to receive notifications about completion popup lifecycle events.
