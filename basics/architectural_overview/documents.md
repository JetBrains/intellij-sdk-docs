---
title: Documents
---

A document is an editable sequence of Unicode characters, which typically corresponds to the text contents of a virtual file. Line breaks in a document are _always_ normalized to `\n`. The *IntelliJ Platform* handles encoding and line break conversions when loading and saving documents transparently.

## How do I get a document?

* From an action: `e.getData(PlatformDataKeys.EDITOR).getDocument()`
* From a virtual file: `FileDocumentManager.getDocument()`. This call forces the document content to be loaded from disk if it wasn't loaded previously; if you're only interested in open documents or documents which may have been modified, use `FileDocumentManager.getCachedDocument()` instead.
* From a PSI file: `PsiDocumentManager.getInstance().getDocument()` or `PsiDocumentManager.getInstance().getCachedDocument()`

## What can I do with a Document?

You may perform any operations that access or modify the file contents on "plain text" level (as a sequence of characters, not as a tree of Java elements).

## Where does a Document come from?

Document instances are created when some operation needs to access the text contents of a file (in particular, this is needed to build the PSI for a file). Also, document instances not linked to any virtual files can be created temporarily, for example, to represent the contents of a text editor field in a dialog.

## How long does a Document persist?

Document instances are weakly referenced from `VirtualFile` instances. Thus, an unmodified [`Document`](upsource:///platform/core-api/src/com/intellij/openapi/editor/Document.java) instance can be garbage-collected if it isn't referenced by anyone, and a new instance will be created if the document contents is accessed again later. Storing `Document` references in long-term data structures of your plugin will cause memory leaks.

## How do I create a Document?

If you need to create a new file on disk, you don't create a `Document`: you create a PSI file and then get its `Document`. If you need to create a `Document` instance which isn't bound to anything, you can use `EditorFactory.createDocument`.

## How do I get notified when Documents change?

* `Document.addDocumentListener` allows you to receive notifications about changes in a particular `Document` instance.
* `EditorFactory.getEventMulticaster().addDocumentListener` allows you to receive notifications about changes in all open documents.
* Subscribe to `AppTopics#FILE_DOCUMENT_SYNC` on any level bus to receive notifications when any `Document` is saved or reloaded from disk.

## What are the rules of working with Documents?

The general read/write action rules are in effect. In addition to that, any operations which modify the contents of the document must be wrapped in a command (`CommandProcessor.getInstance().executeCommand()`). `executeCommand()` calls can be nested, and the outermost `executeCommand` call is added to the undo stack. If multiple documents are modified within a command, undoing this command will by default show a confirmation dialog to the user.

If the file corresponding to a `Document` is read-only (for example, not checked out from the version control system), document modifications will fail. Thus, before modifying the `Document`, it is necessary to call `ReadonlyStatusHandler.getInstance(project).ensureFilesWritable()` to check out the file if necessary.

All text strings passed to `Document` modification methods (`setText`, `insertString`, `replaceString`) must use only \n as line separators.

## Are there any utilities available for working with Documents?

[`DocumentUtil`](upsource:///platform/core-impl/src/com/intellij/util/DocumentUtil.java) contains utility methods for `Document` processing. This allows you to get information like the text offsets of particular lines. This is particularly useful when you need text location/offset information about a given PsiElement.
