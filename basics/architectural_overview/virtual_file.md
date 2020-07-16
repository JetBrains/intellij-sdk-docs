---
title: Virtual Files
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A [`VirtualFile`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) (VF) is the *IntelliJ Platform's* representation of a file in a [Virtual File System (VFS)](/basics/virtual_file_system.md).

Most commonly, a virtual file is a file in a local file system. 
However, the *IntelliJ Platform* supports multiple pluggable file system implementations, so virtual files can also represent classes in a JAR file, old revisions of files loaded from a version control repository, and so on.

The VFS level deals only with binary content. Contents of a `VirtualFile` are treated as a stream of bytes, but concepts like encodings and line separators are handled on higher system levels.

## How do I get a virtual file?

From an action
: `e.getData(PlatformDataKeys.VIRTUAL_FILE)` or `e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY)` for multiple selection

From a path in the local file system: 
:  - `LocalFileSystem.getInstance().findFileByIoFile()`
- `VirtualFileManager.findFileByNioPath()`/`refreshAndFindFileByNioPath()` (2020.2 and later)

From a PSI file
: `psiFile.getVirtualFile()` (may return `null` if the PSI file exists only in memory)

From a document
: `FileDocumentManager.getInstance().getFile()`

## What can I do with it?

Typical file operations are available, such as traverse the file system, get file contents, rename, move, or delete. 
Recursive iteration should be performed using `VfsUtilCore.iterateChildrenRecursively()` to prevent endless loops caused by recursive symlinks.

## Where does it come from?

The VFS is built incrementally by scanning the file system up and down, starting from the project root.
VFS _refreshes_ detect new files appearing in the file system.
A refresh operation can be initiated programmatically using `VirtualFileManager.syncRefresh()`/`asyncRefresh()` or `VirtualFile.refresh()`.
VFS refreshes are also triggered whenever file system watchers receive file system change notifications.

Invoking a VFS refresh might be necessary for accessing a file that has just been created by an external tool through the IntelliJ Platform APIs.

## How long does a virtual file persist?

A particular file on disk is represented by equal `VirtualFile` instances for the entire lifetime of the IDE process.
There may be several instances corresponding to the same file, and they can be garbage-collected. 
The file is a `UserDataHolder`, and the user data is shared between those equal instances. 
If a file is deleted, its corresponding VirtualFile instance becomes invalid (`isValid()` returns `false`), and operations cause exceptions.

## How do I create a virtual file?

Usually, you don't. As a general rule, files are created either through the PSI API or through the regular `java.io.File` API.

If one needs to create a file through VFS, use `VirtualFile.createChildData()` to create a `VirtualFile` instance and `VirtualFile.setBinaryContent()` to write some data to the file.

## How do I get notified when VFS changes?

> **NOTE** See [Virtual file system events](/basics/virtual_file_system.md#virtual-file-system-events) for important details.

Implement [`BulkFileListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) and subscribe to the [message bus](/reference_guide/messaging_infrastructure.md) topic `VirtualFileManager.VFS_CHANGES`. For example:

```java
project.getMessageBus().connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        // handle the events
    }
});
```

See [Message Infrastructure](/reference_guide/messaging_infrastructure.md) and [Plugin Listeners](/basics/plugin_structure/plugin_listeners.md) for more details.

For a non-blocking alternative, starting with version 2019.2 of the platform, see [`AsyncFileListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java).

Plugins targeting versions 2017.2 or older of the platform can use the now deprecated `VirtualFileManager.addVirtualFileListener()` method, which allows you to receive notifications about all changes in the VFS.

## Are there any utilities for analyzing and manipulating virtual files?

[`VfsUtil`](upsource:///platform/analysis-api/src/com/intellij/openapi/vfs/VfsUtil.java) and [`VfsUtilCore`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VfsUtilCore.java) provide utility methods for analyzing files in the Virtual File System.

Use [`ProjectLocator`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectLocator.java) to find the projects that contain a given virtual file.

## How do I extend VFS?

To provide an alternative file system implementation (for example, an FTP file system), implement the [`VirtualFileSystem`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) class (most likely you'll also need to implement `VirtualFile`), and register your implementation via `com.intellij.virtualFileSystem` extension point (2019.2 and later) or [application component](/basics/plugin_structure/plugin_components.md) for earlier versions.

To hook into operations performed in the local file system (for example, when developing a version control system integration that needs custom rename/move handling), implement [`LocalFileOperationsHandler`](upsource:///platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) and register it via `LocalFileSystem.registerAuxiliaryFileOperationsHandler()`.

## What are the rules for working with VFS?

See [Virtual File System](/basics/virtual_file_system.md) for a detailed description of the VFS architecture and usage guidelines.
