---
title: Supporting multiple carets
---


## Introduction

Support for multiple independent carets has been added to editor implementation in IDEA 13.1.
Most editor actions (keyboard navigation, text insertion and deletion, etc) will be applied to each caret independently.
Each caret has its own associated selection, which is a continuous range of document characters (can be empty).
When after some action two or more carets end up in the same visual position, they are merged into a single caret with their associated selections merged into a single one. Similar thing will happen when selections for several carets become overlapped, only one of the carets will remain, and the selections will be merged.
There's a concept of 'primary' caret - the one on which non-multi-caret-aware actions and the actions which need a single-point document context (like code completion) will operate.
Currently, the most recent caret is considered the primary one.

## Core functionality

Core logic related to multi-caret implementation such as accessing currently existing carets adding and removing carets is available via
[CaretModel](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java)
interface, some changes also have been made in
[SelectionModel](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/SelectionModel.java)
interface.
Check Javadoc of those interfaces for details.

Notable changes from old behaviour:

*  Previously existing methods in CaretModel and SelectionModel to query and modify caret and selection positions work by default on the primary caret now. In the context of CaretModel._runForEachCaret_ method though, they operate on the current caret.
So the behaviour of legacy code (not using Caret interface) will depend on the context of its invocation.

*  Block selection doesn't exist as a separate concept anymore.
Correspondingly, block-selection-related methods in SelectionModel interface have changed behaviour - _hasBlockSelection()_ will always return false, _setBlockSelection()_ will create a multi-caret selection equivalent to the requested block selection.
_getBlockSelectionStarts()_ and _getBlockSelectionEnds()_ methods work in multi-caret state, returning all selected regions.

## Editor actions

### EditorAction and EditorActionHandler

When
[EditorActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) is invoked, additional parameter will be passed to it - caret instance on which it should operate, or null, if it's invoked without any caret context.
If the handler invokes another handler (delegate handler for the same actionId, or a completely unrelated handler), that parameter should normally be passed to the delegate unchanged (unless no context caret has been provided to the handler, but it needs to invoke another handler on a specific caret).
Of course, handler can just ignore the caret parameter if its functionality is not related to caret/selection position.

If handler needs to implement multi-caret functionality it can do so explicitly in the overridden _doExecute_ method, but if it just needs that method to be invoked for each caret, it's enough to pass a parameter to EditorActionHandler constructor to make _doExecute_ called for each caret, when the handler is invoked without specific caret context.

### Editor action delegates

The following delegates are available:

*  EnterHandlerDelegate
*  BackspaceHandlerDelegate
*  JoinLinesHandlerDelegate
*  EditorNavigationDelegate
*  SmartEnterProcessor
*  CommentCompleteHandler
*  StatementUpDownMover
*  CodeBlockProvider

At the moment there's no need to make any changes in the handlers to support multiple carets - they are already invoked for each caret.

## Typing actions

### TypedActionHandler, TypedHandlerDelegate

[TypedActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java)
and
[TypedHandlerDelegate](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java)
implementations are invoked only once for each typed character.
If those handlers need to support multiple carets, they will need to implement that explicitly.

[EditorModificationUtil](upsource:///platform/platform-api/src/com/intellij/openapi/editor/EditorModificationUtil.java).
_typeInStringAtCaretHonorMultipleCarets_ utility method is available to do the most common task in this case - inserting the same text into all caret positions and/or moving all carets relatively to their current position.
Examples of its usage:

*  [TypedAction](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java).

*  [XmlGtTypedHandler](upsource:///xml/impl/src/com/intellij/codeInsight/editorActions/XmlGtTypedHandler.java).

-----------
**Note**:
Starting from IDEA 14,
[TypedHandlerDelegate](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java)
implementations are invoked automatically for each caret. If one wants to implement custom multicaret behaviour on typing,
[TypedActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java)
needs to be provided instead.

-----------

## Code insight actions

Existing actions inheriting from
[CodeInsightAction](upsource:///platform/lang-api/src/com/intellij/codeInsight/actions/CodeInsightAction.java) will work for primary caret only.
To support in multiple carets one should inherit
[MultiCaretCodeInsightAction](upsource:///platform/lang-impl/src/com/intellij/codeInsight/actions/MultiCaretCodeInsightAction.java)
instead (each caret might have a different editor and PSI instance, so using old API is not possible).
It is available since IDEA 14.

