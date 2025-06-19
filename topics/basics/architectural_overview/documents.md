<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Documents

<link-summary>Introduction to documents - an editable sequence of Unicode characters, typically storing the content of a virtual file.</link-summary>

A [`Document`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/Document.java) is an editable sequence of Unicode characters, typically corresponding to the text contents of a [virtual file](virtual_file.md).

Line breaks in a document are _always_ normalized to `\n`.
The IntelliJ Platform handles encoding and line break conversions when loading and saving documents transparently.

## How do I get a `Document`?

| Context                          | API                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](action_system.md) | [`AnActionEvent.getData(CommonDataKeys.EDITOR).getDocument()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)                                                                                                                                                                                                                                                                             |
| [PSI File](psi_files.md)         | [`PsiDocumentManager.getDocument()`/`getCachedDocument()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java)                                                                                                                                                                                                                                                                                                  |
| [Virtual File](virtual_file.md)  | <p>[`FileDocumentManager.getDocument()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileEditor/FileDocumentManager.java) (forces the document content to be loaded from a disk if it wasn't loaded previously)</p><p>[`FileDocumentManager.getCachedDocument()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileEditor/FileDocumentManager.java) (use if only open or possibly modified documents are relevant)</p> |

## What can I do with a `Document`?

You may perform any operations that access or modify the file contents on the "plain text" level (as a sequence of characters, not as a tree of [](psi.md) elements).

## Where does a `Document` come from?

`Document` instances are created when some operation needs to access the text contents of a file (in particular, this is necessary to build the PSI for a file).
Also, document instances not linked to any [](virtual_file.md) can be created temporarily, for example, representing the contents of a text editor field in a dialog.

## How long does a `Document` persist?

Document instances are weakly referenced from `VirtualFile` instances.
Thus, an unmodified `Document` instance can be garbage-collected if no one references it, and a new instance is created if the document contents are reaccessed later.

> Storing `Document` references in long-term data structures of a plugin will cause memory leaks.
>
{style="warning"}

## How do I create a `Document`?

For creating a new file on disk, please do not create a `Document` but a PSI file and get its `Document` (see [](psi_files.md#how-do-i-create-a-psi-file)).
To create a `Document` instance that isn't bound to anything, use [`EditorFactory.createDocument()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/EditorFactory.java).

## How do I get notified when documents change?

* `Document.addDocumentListener()` allows receiving notifications about changes in a particular `Document` instance.
* `EditorFactory.getEventMulticaster().addDocumentListener()` allows receiving notifications about changes in all open documents.
* Register [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) [listener](plugin_listeners.md) or subscribe to `AppTopics.FILE_DOCUMENT_SYNC` on any level bus to receive notifications when a `Document` is saved or reloaded from disk.

## What are the rules of working with documents?

The general read/write action rules are in effect (see [](threading_model.md)).
Besides, any operations which modify the contents of the document must be wrapped in a command ([`CommandProcessor.executeCommand()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/command/CommandProcessor.java)).
`executeCommand()` calls can be nested, and the outermost `executeCommand()` call is added to the undo stack.
If multiple documents are modified within a command, undoing this command will, by default, show a confirmation dialog to the user.

If the file corresponding to a `Document` is read-only (for example, not checked out from the version control system), document modifications will fail.
Thus, before modifying the `Document`, it is necessary to call [`ReadonlyStatusHandler.ensureFilesWritable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/ReadonlyStatusHandler.java) to check out the file.

All text strings passed to `Document` modification methods (`setText()`, `insertString()`, `replaceString()`) must use only `\n` as line separators.

See also [Working with Text](working_with_text.md#safely-replacing-selected-text-in-the-document) in Editors Basics tutorial.

## Are there any utilities available for working with documents?

[`DocumentUtil`](%gh-ic%/platform/core-impl/src/com/intellij/util/DocumentUtil.java) contains utility methods for `Document` processing.
This allows you to get information like the text offsets of particular lines.
This is particularly useful when you need text location/offset information about a given `PsiElement`.

## How can I mark a region as read-only?

Use `Document.createGuardedBlock()`.

[`EditorActionManager.setReadonlyFragmentModificationHandler()`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionManager.java) can
be used to customize the behavior when the user attempts to modify a read-only region.
