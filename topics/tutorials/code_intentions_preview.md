# Intention Action Preview

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Guide for the preview of Intention and Quick Fix actions.</link-summary>
<primary-label ref="2022.3"/>

> [Intention](code_intentions.md) previews are supposed to work out-of-the-box in most cases.
> This page gives guidance to plugin authors in situations where this is not the case and to
> encourage thorough [testing](writing_tests.md) of intention actions.


The IntelliJ Platform can show a preview for
[`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java)
and
[`LocalQuickFix`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalQuickFix.java).
That means when the user shows [available context actions](https://www.jetbrains.com/help/idea/intention-actions.html#apply-intention-actions),
an additional small popup highlights the differences between the current code and the code after executing the intention.

Under the hood, this preview is created using a copy of the current file inside a separate,
headless editor where the intention is applied to get the result.
The difference between this result and the original code is used to build the preview where changes that are about to happen are highlighted.

The IntelliJ Platform has a default implementation for the preview,
which works out of the box when existing intentions fulfill specific requirements,
make only localized changes to the code, and require no further user input.

However, in specific cases, plugin authors will need to adapt their intentions to prepare them for the correct display of the preview.
In the following, plugin authors can learn the requirements for intention previews in detail and the alternative options for previews the IntelliJ Platform provides.

## Implementation

> While this page focuses on the preview for `IntentionAction`, the `generatePreview()`
> method exists also for `LocalQuickFix` and the documentation and all discussed points are valid for both use cases.
>
{style="note"}

The entry point for previews is `generatePreview()` available in
[`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java)
and
[`LocalQuickFix`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalQuickFix.java).
The default implementation will load the intention or quick fix and call its `invoke()` or `applyFix()` method on a copy of the original file.
It returns an instance of [`IntentionPreviewInfo`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/preview/IntentionPreviewInfo.java).
When the diff could be computed successfully it returns `IntentionPreviewInfo.DIFF` and when it was unsuccessful it returns
`IntentionPreviewInfo.EMPTY`.

The preferred way for plugin authors is to keep using their intentions or quick fixes as is and only adapt
the code if the default implementation of `generatePreview()` does not succeed.
Further down, we will give detailed information about necessary code changes and requirements.
However, sometimes it might not be viable to create the diff for the preview automatically.
In such cases, plugin authors have several options to override `generatePreview()` and return a different `IntentionPreviewInfo`.
The following options are available and described in the paragraphs below:

- Return an instance of `IntentionPreviewInfo.Html` to show a customized message that is dynamically generated from the available information.
- Return an instance of `IntentionPreviewInfo.CustomDiff` to provide an original and changed text that is used to show the diff.
- Return `IntentionPreviewInfo.EMPTY` in cases where it is not possible to show any preview.

See [`IntentionPreviewUtils`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/preview/IntentionPreviewUtils.java) for a number of useful helper methods.

### HTML Preview

To show a dynamically generated HTML description as a preview, return an instance of
`IntentionPreviewInfo.Html` from `generatePreview()`.
Some important tips are:

- You may use the
  [`HtmlChunk`](%gh-ic%/platform/util/src/com/intellij/openapi/util/text/HtmlChunk.java) API with it.
  If you want to display an icon, use `HtmlChunk#icon` but be careful because converting it to text and back (via `HtmlChunk.raw`) would remove the icon.
  Consider using `HtmlChunk.template` if necessary.
- Note that the description pane has a fixed width of 300px for the default font, and you should try to make the description as concise as possible.
- Any user interaction with the description pane is hardly possible and should not be relied upon.
  The first reason for that is that previews are usually displayed as a reaction to <shortcut>Alt+Enter</shortcut>
  by users who avoid using the mouse. Secondly, even with the mouse, it happens to hover over a different intention
  accidentally, which would close the current preview and make an interaction hard.
  Therefore, adding clickable links or buttons in previews is highly discouraged.
- When possible, use generic preview methods available in `IntentionPreviewInfo` (e.g., `rename()`, `navigate()`, `movePsi()`).
  They provide a uniform preview for common cases.

### Custom Diff Preview

For situations where the changes of an intention are not bound to the current file,
a custom diff-like preview can be generated by returning `IntentionPreviewInfo.CustomDiff` from `generatePreview()`.
The diff is then generated by comparing `originalText` with `modifiedText`.
In many cases, `originalText` can be an empty string and then only the `modifiedText` will be displayed in the preview.
However, crafting some `originalText` might be helpful if:

- The diff should highlight the exact parts that were changed from `originalText` to `modifiedText`.
- The code changed in several unrelated lines. The preview can show several parts without then unchanged lines in the middle and separate the parts nicely.

Finally, when returning a custom diff, plugin authors should specify a file name if possible.

## Testing

If you use [`CodeInsightTestFixture`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestFixture.java),
please note the following useful methods:

- `checkPreviewAndLaunchAction()` is a replacement for `launchAction()`.
  If you call `launchAction()` directly and your action should generate diff-preview which equals to original file content,
  use `checkPreviewAndLaunchAction()` with this call, and preview will be checked as well.
- `checkIntentionPreviewHtml()` asserts that HTML preview is generated and that it equals to the supplied text.
- `getIntentionPreviewText()` returns the preview text for a given action.

If you are unsatisfied with these methods, or you have a custom framework and want to support preview testing,
please look through the implementations of the aforementioned methods.

If you use
[`LightQuickFixTestCase`](%gh-ic%/java/testFramework/src/com/intellij/codeInsight/daemon/quickFix/LightQuickFixTestCase.java)
or
[`LightQuickFixParameterizedTestCase`](%gh-ic%/java/testFramework/src/com/intellij/codeInsight/daemon/quickFix/LightQuickFixParameterizedTestCase.java)
with the usual setup of having your test-input as `beforeXyz` and `afterXyz`, you can test previews as well.
A special suffix `-preview` should be added to the comment line in the test-data to test preview.
For example:

<compare type="top-bottom" first-title="First line before" second-title="First line after">
<code-block>
// "Replace Arrays.asList().stream() with Stream.of()" "true"
</code-block>
<code-block>
// "Replace Arrays.asList().stream() with Stream.of()" "true-preview"
</code-block>
</compare>

This explicitly allows for preview testing and checks that the text generated by preview on a non-physical
file is the same as the text generated by actual action.

If you have a customized preview text or HTML, you may create a `previewXyz` file (preserve the original extension) near `beforeXyz` and `afterXyz`.
Now, the preview (no matter if it is a diff or HTML) will be compared to the content of that file.

Finally,
[`IntentionPreviewPopupUpdateProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/preview/IntentionPreviewPopupUpdateProcessor.kt)
provides useful helper functions.

## Preparation for the Default Diff Preview

To prepare existing intentions for the normal diff preview, plugin authors must understand the underlying framework and how it works to a certain degree.
This section will provide these details and guide developers through possible scenarios.

Normally, a non-physical copy of the file is provided to the `generatePreview()` method.
When overriding `generatePreview()`, you can apply any changes to that file and return `IntentionPreviewInfo.DIFF`.
The diff between the original file content and the changed file content will be shown in the preview.

The default implementation of `generatePreview()` employs further checks to ensure your action is safe.
These checks consist of the following:

1. Verify that [`startsInWriteAction()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) returns `true`.
2. Verify that [`getElementToMakeWritable()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/FileModifier.java) returns its argument.
3. Check if all instance fields in the class are either marked as `@SafeFieldForPreview` (defined in
   [`FileModifier`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/FileModifier.java)),
   their types are marked as `@SafeTypeForPreview`, or used types are known to be safe (primitives, strings, enums, classes, arrays of these, etc.)
   If this is the case, then the original `invoke()` or `applyFix()` method is called in a background thread
   on a non-physical copy of the file and with a mock editor
   ([`IntentionPreviewEditor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/preview/IntentionPreviewEditor.kt)).

If the above does not show a preview, there could be further problems of the following kind:

1. There are fields that are safe but have not been marked with `@SafeFieldForPreview`.
   Safe fields are ones which do [not hold references to real PSI elements](dynamic_plugins.md#do-not-store-psi),
   and if they do, these references are used for reading only.
2. There are fields that actually hold references to a physical `PsiElement` and they are modified by the action/fix.
   You can try to get rid of them by extracting the necessary PSI from the [`ProblemDescriptor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/ProblemDescriptor.java)
   or using `PsiFile.getElementAt()` with the editor caret position.
   Another option is to override `getFileModifierForPreview(target)`. In this method, remap all the PSI elements to the target file (which is the copy of the source file)
   using `PsiTreeUtil.findSameElementInCopy()` and create a new instance of your action/fix.
   Example: [`DeleteMultiCatchFix.getFileModifierForPreview()`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/DeleteMultiCatchFix.java)
   Be careful, as there could be subclasses. Better play safe and declare your action/fix class as final.
3. `invoke()`/`applyFix()` starts a write action. If `startsInWriteAction()` returns `true`, then this is unnecessary, and you can remove the write action.
4. `invoke()`/`applyFix()` asserts (directly or indirectly) that it is invoked in the dispatch thread or inside a write action (see also [](threading_model.md)).
   The preview is applied in a background thread without a write action as it works on non-physical elements.
   You may consider removing these assertions or keeping them only if the preview is not active (use `IntentionPreviewUtils.isIntentionPreviewActive()`).
5. `PsiDocumentManager.getDocument(psiFile)` is used which isn't supported for a non-physical `psiFile`.
   Instead, use `psiFile.getViewProvider().getDocument()`.
6. `PsiDocumentManager.commitAllDocuments()` is used. It’s unlikely that this call is required, and it probably slows down your action.
   You can commit the current document via `PsiDocumentManager.commitDocument()`.
7. `FileEditorManager.openTextEditor()` or `FileEditorManager.getSelectedEditor()` is used to access the current editor (e.g., to start a template, position caret, add highlighting).
   Instead, use `FileEditorManager.getSelectedTextEditor()`, which should work in the preview and will point to a fake editor (where templates work).
   Also, you can branch on `IntentionPreviewUtils.isIntentionPreviewActive()` and avoid all editor-related operations because they won’t affect the preview anyway.
8. `Application.invokeLater()` is used. Currently, it’s prohibited in the preview and will fail with an exception.
   You may branch on `IntentionPreviewUtils.isIntentionPreviewActive()` and avoid doing this for preview, or you generate a custom preview as explained above.
   Also, note the tips in the paragraph below this list.
9. Non-trivial operations with the editor are used that are currently not implemented for the mock editor.
   We mock many operations but not all. E.g., `getFoldingModel()` is not currently supported.
   Avoid using these operations for non-physical files or for `IntentionPreviewEditor`.
10. Your action produces a side effect outside the current file. Examples include actions trying to change other PSI elements, changing the project or IDE settings, or launching an external process.
    In this case, `startsInWriteAction()` returning `true` and/or `getElementToMakeWritable()` returning its argument is incorrect.
    Override these methods properly and [create a custom preview](#custom-diff-preview).
11. Your action uses non-physical elements for some purpose and branches on `PsiElement.isPhysical()` already, so in preview mode, this branch is wrongly taken.
    Instead, use `IntentionPreviewUtils.isPreviewElement()`.
12. Your action modifies the results of `ReferencesSearch.search(declaration)`, which always belongs to the original physical file, no matter if the passed declaration is a copy or an original.
    If you expect all the results to update belonging to the current file, you may traverse the file (`PsiTreeUtil.processElements()`, `SyntaxTraverser.psiTraverser()`, etc.) instead.
    Alternatively, remap the results of the search to the file copy using `PsiTreeUtil.findSameElementInCopy()`.
    If you need to update something outside the current file, a single file preview won’t work anyway. Either avoid updating the elements for the preview or create a custom preview.

If your action returns `false` from `startsInWriteAction()`, you should override `generatePreview()` and provide a custom diff or HTML preview.
However, sometimes the default implementation of `generatePreview()` can still work.
Here are two common scenarios:

1. Your action displays an additional popup or dialog but makes only changes to the current file after that.
   A solution here could be to display the preview assuming that the user selected default options in your UI and delegate to a method that is called after the UI is displayed, providing the default options.
2. Your action modifies a declaration in the current file and then updates all the call sites/overrides/references (probably involving slow search operation).
   One solution is to show only the modified declaration in the preview, ignoring all updated call sites or restricting them to the current file.
   Use `IntentionPreviewUtils.isIntentionPreviewActive()` to restrict the search scope when in preview.


