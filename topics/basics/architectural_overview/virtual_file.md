<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Virtual Files

<link-summary>Virtual Files represent local or remote files provided by the Virtual File System.</link-summary>

A [`VirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) (VF) is the IntelliJ Platform's representation of a file in a [Virtual File System (VFS)](virtual_file_system.md).

Most commonly, a virtual file is a file in a local file system.
However, the IntelliJ Platform supports multiple pluggable file system implementations, so virtual files can also represent classes in a JAR file, old revisions of files loaded from a version control repository, and so on.

The VFS level deals only with binary content.
Contents of a `VirtualFile` are treated as a stream of bytes, but concepts like encodings and line separators are handled at higher system levels.

## How do I get a virtual file?

| Context                    | API                                                                                                                                                                                                                                                                                                                                           |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](action_system.md) | <p>[`AnActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE)`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)</p><p>[`AnActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY)`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) for multiple selection</p> |
| [Document](documents.md)   | [`FileDocumentManager.getFile()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileEditor/FileDocumentManager.java)                                                                                                                                                                                                                     |
| [PSI File](psi_files.md)   | [`PsiFile.getVirtualFile()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFile.java) (may return `null` if the PSI file exists only in memory)                                                                                                                                                                                           |
| File Name                  | [`FilenameIndex.getVirtualFilesByName()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java)                                                                                                                                                                                                                       |
| Local File System Path     | <p>[`LocalFileSystem.findFileByIoFile()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileSystem.java)</p><p>[`VirtualFileManager.findFileByNioPath()`/`refreshAndFindFileByNioPath()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java)</p>                                               |

## What can I do with it?

Typical file operations are available, such as traverse the file system, get file contents, rename, move, or delete.
Recursive iteration should be performed using `VfsUtilCore.iterateChildrenRecursively()` to prevent endless loops caused by recursive symlinks.

## Where does it come from?

The VFS is built incrementally by scanning the file system up and down, starting from the project root.
VFS _refresh_ operations detect new files appearing in the file system.
A refresh operation can be initiated programmatically using `VirtualFileManager.syncRefresh()`/`asyncRefresh()` or `VirtualFile.refresh()`.
VFS refreshes are also triggered whenever file system watchers receive file system change notifications.

Invoking a VFS refresh might be necessary for accessing a file that has just been created by an external tool through the IntelliJ Platform APIs.

## How long does a virtual file persist?

A particular file on a disk is represented by equal `VirtualFile` instances for the IDE process's entire lifetime.
There may be several instances corresponding to the same file, and they can be garbage-collected.

The file is a [`UserDataHolder`](%gh-ic%/platform/util/src/com/intellij/openapi/util/UserDataHolder.java), and the user data is shared between those equal instances.
If a file is deleted, its corresponding `VirtualFile` instance becomes invalid (`isValid()` returns `false`), and operations cause exceptions.

## How do I create a virtual file?

Usually, you don't.
As a general rule, files are created either through the [PSI API](psi.md) or through the regular [`java.io.File`](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/File.html) API.

If one needs to create a file through VFS, use `VirtualFile.createChildData()` to create a `VirtualFile` instance and `VirtualFile.setBinaryContent()` to write some data to the file.

## How do I get notified when VFS changes?

> See [](virtual_file_system.md#virtual-file-system-events) for important details.
>
{style="note"}

Implement [`BulkFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) and subscribe to the [message bus](messaging_infrastructure.md) topic `VirtualFileManager.VFS_CHANGES`.
For example:

```java
project.getMessageBus().connect().subscribe(
    VirtualFileManager.VFS_CHANGES,
    new BulkFileListener() {
      @Override
      public void after(@NotNull List<? extends VFileEvent> events) {
        // handle the events
      }
    });
```

See [Message Infrastructure](messaging_infrastructure.md) and [Plugin Listeners](plugin_listeners.md) for more details.

For a non-blocking alternative see [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java).

## Are there any utilities for analyzing and manipulating virtual files?

[`VfsUtil`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/VfsUtil.java) and [`VfsUtilCore`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VfsUtilCore.java) provide utility methods for analyzing files in the Virtual File System.

For storing a large set of Virtual Files, use the dedicated `VfsUtilCore.createCompactVirtualFileSet()` method.

Use [`ProjectLocator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/ProjectLocator.kt) to find the projects that contain a given virtual file.

## How do I extend VFS?

To provide an alternative file system implementation (for example, an FTP file system), implement the [`VirtualFileSystem`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) class (most likely you'll also need to implement `VirtualFile`),
and register your implementation via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.virtualFileSystem"/></include>.

To hook into operations performed in the local file system (for example, when developing a version control system integration that needs custom rename/move handling), implement [`LocalFileOperationsHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) and register it via `LocalFileSystem.registerAuxiliaryFileOperationsHandler()`.

## What are the rules for working with VFS?

See [](virtual_file_system.md) for a detailed description of the VFS architecture and usage guidelines.

## How can I store additional metadata in files?

See:
- [`FilePropertyPusher`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/impl/FilePropertyPusher.java)
- [`FileAttribute`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/newvfs/FileAttribute.java)
