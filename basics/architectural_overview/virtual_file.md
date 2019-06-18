---
title: Virtual Files
---

A virtual file [com.intellij.openapi.vfs.VirtualFile](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) is the *IntelliJ Platform's* representation of a file in a file system (VFS). Most commonly, a virtual file is a file in your local file system. However, the *IntelliJ Platform* supports multiple pluggable file system implementations, so virtual files can also represent classes in a JAR file, old revisions of files loaded from a version control repository, and so on.

The VFS level deals only with binary content. You can get or set the contents of a `VirtualFile` as a stream of bytes, but concepts like encodings and line separators are handled on higher system levels.

## How do I get a virtual file?

* From an action: `e.getData(PlatformDataKeys.VIRTUAL_FILE)`. If you are interested in multiple selection, you can also use `e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY)`.
* From a path in the local file system: `LocalFileSystem.getInstance().findFileByIoFile()`
* From a PSI file: `psiFile.getVirtualFile()` (may return null if the PSI file exists only in memory)
* From a document: `FileDocumentManager.getInstance().getFile()`

## What can I do with it?

Typical file operations are available, such as traverse the file system, get file contents, rename, move, or delete. Recursive iteration should be performed using `VfsUtilCore.iterateChildrenRecursively` to prevent endless loops caused by recursive symlinks.

## Where does it come from?

The VFS is built incrementally, by scanning the file system up and down starting from the project root. New files appearing in the file system are detected by VFS _refreshes_. A refresh operation can be initiated programmatically using (`VirtualFileManager.getInstance().refresh()` or `VirtualFile.refresh()`). VFS refreshes are also triggered whenever file system watchers receive file system change notifications (available on the Windows and Mac operating systems).

As a plugin developer, you may want to invoke a VFS refresh if you need to access a file that has just been created by an external tool through the IntelliJ Platform APIs.

## How long does a virtual file persist?

A particular file on disk is represented by equal `VirtualFile` instances for the entire lifetime of the IDEA process. There may be several instances corresponding to the same file, and they can be garbage-collected. The file is a `UserDataHolder`, and the user data is shared between those equal instances. If a file is deleted, its corresponding VirtualFile instance becomes invalid (the `isValid()` method returns `false` and operations cause exceptions).

## How do I create a virtual file?

Usually you don't. As a rule, files are created either through the PSI API or through the regular java.io.File API.

If you do need to create a file through VFS, you can use the `VirtualFile.createChildData()` method to create a `VirtualFile` instance and the `VirtualFile.setBinaryContent()` method to write some data to the file.

## How do I get notified when VFS changes?

The `VirtualFileManager.addVirtualFileListener()` method allows you to receive notifications about all changes in the VFS.

## Are there any utilities for analyzing and manipulating virtual files?

[`VfsUtil`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VfsUtil.java) and [`VfsUtilCore`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VfsUtilCore.java) provide utility methods for analyzing files in the Virtual File System.

You can use [`ProjectLocator`](upsource:///platform/core-api/src/com/intellij/openapi/project/ProjectLocator.java) to find the projects that contain a given virtual file.

## How do I extend VFS?

To provide an alternative file system implementation (for example, an FTP file system), implement the [VirtualFileSystem](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) class (most likely you'll also need to implement `VirtualFile`), and register your implementation as an [application component](/basics/plugin_structure/plugin_components.md).

To hook into operations performed in the local file system (for example, if you are developing a version control system integration that needs custom rename/move handling), implement the [LocalFileOperationsHandler](upsource:///platform/platform-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) interface and register it through the`LocalFileSystem.registerAuxiliaryFileOperationsHandler` method.

## What are the rules for working with VFS?

See [IntelliJ Platform Virtual File System](/basics/virtual_file_system.md) for a detailed description of the VFS architecture and usage guidelines.
