[//]: # (title: Multiple Carets)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Most editor actions (keyboard navigation, text insertion and deletion, etc.) will be applied to each caret independently.
Each caret has its own associated selection, which is a continuous range of document characters (can be empty).
When after some action two or more carets end up in the same visual position, they are merged into a single caret with their associated selections merged into a single one.
A similar thing will happen when selections for several carets become overlapped: only one of the carets will remain, and the selections will be merged.

There's a concept of _primary_ caret — the one on which non-multi-caret-aware actions and the actions which need a single-point document context (like code completion) will operate.
Currently, the most recent caret is considered the primary one.

## Core Functionality

Core logic related to multi-caret implementation such as accessing currently existing carets, adding and removing carets, is available via [`CaretModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java).
For text selection, see [`SelectionModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/SelectionModel.java).

Methods in `CaretModel` and `SelectionModel` to query and modify caret and selection positions work by default on the primary caret.
In the context of `CaretModel.runForEachCaret()` method though, they operate on the current caret.

`SelectionModel.getBlockSelectionStarts()` and `getBlockSelectionEnds()` work in multi-caret state, returning all selected regions.

## Editor Actions

### EditorAction and EditorActionHandler

When [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) is invoked, an additional parameter will be passed to it: a caret instance on which it should operate, or `null` if it's invoked without any caret context.
If the handler invokes another handler (delegate handler for the same `actionId` or a completely unrelated handler), that parameter should normally be passed to the delegate unchanged (unless no context caret has been provided to the handler, but it needs to invoke another handler on a specific caret).
Of course, the handler can just ignore the caret parameter if its functionality is not related to caret/selection position.

If the handler needs to implement multi-caret functionality it can do so explicitly in the overridden `doExecute` method, but if it just needs that method to be invoked for each caret, it suffices to pass a parameter to `EditorActionHandler` constructor to make `doExecute` called for each caret when the handler is invoked without a specific caret context.

### Editor Action Delegates

The following delegates are available:

* [`EnterHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java)
* [`BackspaceHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceHandlerDelegate.java)
* [`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java)
* [`EditorNavigationDelegate`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/EditorNavigationDelegate.java)
* [`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java)
* [`CommentCompleteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CommentCompleteHandler.java)
* [`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java)
* [`CodeBlockProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CodeBlockProvider.java)

There is no need to make any changes in the handlers to support multiple carets — they are already invoked for each caret.

## Typing Actions

### TypedActionHandler, TypedHandlerDelegate

[`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) and [`TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) implementations are invoked only once for each typed character.
If those handlers need to support multiple carets, they will need to implement that explicitly.

[`EditorModificationUtil`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/EditorModificationUtil.java).
`typeInStringAtCaretHonorMultipleCarets()` method is available to do the most common task in this case: inserting the same text into all caret positions and/or moving all carets relatively to their current position.
Examples of its usage:

* [`TypedAction`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java).
* [`XmlGtTypedHandler`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/editorActions/XmlGtTypedHandler.java).

> [`TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) implementations are invoked automatically for each caret.
> If one wants to implement custom multicaret behaviour on typing, [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) needs to be provided instead.
>
{type="note"}

## Code Insight Actions

Existing actions inheriting from [`CodeInsightAction`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/actions/CodeInsightAction.java) will work for primary caret only.
To support multiple carets, one should subclass [`MultiCaretCodeInsightAction`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/MultiCaretCodeInsightAction.java) instead.
Each caret might have a different editor and PSI instance, so using the old API is not possible.
