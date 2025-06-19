<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Virtual File System

<link-summary>Virtual File System is an abstraction that allows working with local, remote, or custom file storages.</link-summary>

The Virtual File System (VFS) is a component of the IntelliJ Platform that encapsulates most of its activity for working with files represented as [Virtual File](virtual_file.md).

It serves the following main purposes:

* Providing a universal API for working with files regardless of their actual location (on disk, in an archive, on an HTTP server, etc.)
* Tracking file modifications and providing both old and new versions of the file content when a change is detected.
* Providing a possibility to [associate additional persistent data](virtual_file.md#how-can-i-store-additional-metadata-in-files) with a file in the VFS.

To provide the last two features, the VFS manages a _persistent snapshot_ of some of the user's hard disk contents.
The snapshot stores only those files which have been requested at least once through the VFS API, and is asynchronously updated to match the changes happening on the disk.

The snapshot is application level, not project level - so, if some file (for example, a class in the JDK) is referenced by multiple projects, only one copy of its contents will be stored in the VFS.

All VFS access operations go through the snapshot.

If some information is requested through the VFS APIs and is not available in the snapshot, it is loaded from disk and stored into the snapshot.
If the information is available in the snapshot, the snapshot data is returned.
The contents of files and the lists of files in directories are stored in the snapshot only if that specific information was accessed.
Otherwise, only file metadata like name, length, timestamp, attributes are stored.

> This means that the state of the file system and the file contents displayed in the IntelliJ Platform UI comes from the snapshot, which may not always match the disk's actual contents.
> For example, in some cases, deleted files can still be visible in the UI for some time before the deletion is picked up by the IntelliJ Platform.
>
{style="note"}

The snapshot is updated from disk during _refresh operations_, which generally happen asynchronously.
All write operations made through the VFS are synchronous - i.e., the contents are saved to disk immediately.

A refresh operation synchronizes the state of a part of the VFS with the actual disk contents.
Refresh operations are explicitly invoked by the IntelliJ Platform or plugin code - i.e., when a file is changed on disk while the IDE is running, the change will not be immediately picked up by the VFS.
The VFS will be updated during the next refresh operation, which includes the file in its scope.

IntelliJ Platform refreshes the entire project contents asynchronously on startup.
By default, it performs a refresh operation when the user switches to it from another app.
Still, users can turn this off via <ui-path>Settings | Appearance & Behavior | System Settings | Synchronize external changes\[...]</ui-path>.

On Windows, Mac, and Linux, a native file watcher process is started that receives file change notifications from the file system and reports them to the IntelliJ Platform.
If a file watcher is available, a refresh operation looks only at the files that have been reported as changed by the file watcher.
If no file watcher is present, a refresh operation walks through all directories and files in the refresh scope.

> Invoke [internal action](internal_actions_intro.md) <ui-path>Tools | Internal Actions | VFS | Show Watched VFS Roots</ui-path> to see all registered roots for current project.
>

Refresh operations are based on file timestamps.
If a file's contents were changed, but its timestamp remained the same, the IntelliJ Platform will not pick up the updated contents.

There is currently no facility for removing files from the snapshot.
If a file was loaded there once, it remains there forever unless it was deleted from the disk, and a refresh operation was called on one of its parent directories.

The VFS itself does not honor ignored files listed in <ui-path>Settings | Editor | File Types</ui-path> and folders to ignore and excluded folders listed in <ui-path>Project Structure | Modules | Sources | Excluded</ui-path>.
If the application code accesses them, the VFS will load and return their contents.
In most cases, the ignored files and excluded folders must be skipped from processing by higher-level code.

During the lifetime of a running instance of an IntelliJ Platform IDE, multiple `VirtualFile` instances may correspond to the same disk file.
They are equal, have the same `hashCode`, and share the user data.

## Synchronous and Asynchronous Refreshes

From the point of view of the caller, refresh operations can be either synchronous or asynchronous.
In fact, the refresh operations are executed according to their own threading policy.
The synchronous flag simply means that the calling thread will be blocked until the refresh operation (which will most likely run on a different thread) is completed.

Both synchronous and asynchronous refreshes can be initiated from any thread.
If a refresh is initiated from a background thread, the calling thread must not hold a read action, because otherwise, a deadlock would occur.
See [IntelliJ Platform Architectural Overview](threading_model.md) for more details on the threading model and read/write actions.

The same threading requirements also apply to functions like [`LocalFileSystem.refreshAndFindFileByPath()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileSystem.java), which perform a partial refresh if the file with the specified path is not found in the snapshot.

In nearly all cases, using asynchronous refreshes is strongly preferred.
If there is some code that needs to be executed after the refresh is complete, the code should be passed as a `postRunnable` parameter to one of the refresh methods:

* [`RefreshQueue.createSession()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/newvfs/RefreshQueue.java)
* [`VirtualFile.refresh()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java)

In some cases, synchronous refreshes can cause deadlocks, depending on which [locks](threading_model.md#read-write-lock) are held by the thread invoking the refresh operation.

## Virtual File System Events

All changes happening in the virtual file system, either due to refresh operations or caused by user actions, are reported as _virtual file system events_.
VFS events are always fired in the event dispatch thread and in a write action.

The most efficient way to listen to VFS events is to implement [`BulkFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) and to subscribe with it to the [`VirtualFileManager.VFS_CHANGES`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java) topic.
A non-blocking variant [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) is also available.
See [How do I get notified when VFS changes?](virtual_file.md#how-do-i-get-notified-when-vfs-changes) for implementation details.

> VFS listeners are application level and will receive events for changes happening in *all* the projects opened by the user.
> You may need to filter out events that aren't relevant to your task (e.g., via [`ProjectFileIndex.isInContent()`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)).
>
{style="warning"}

VFS events are sent both before and after each change, and you can access the old contents of the file in the before event.
Note that events caused by a refresh are sent after the changes have already occurred on disk.
So when you process the `beforeFileDeletion` event, for example, the file has already been deleted from disk.
However, it is still present in the VFS snapshot, and you can access its last contents using the VFS API.

Note that a refresh operation fires events only for changes in files that have been loaded in the snapshot.
For example, if you accessed a `VirtualFile` for a directory but never loaded its contents using [`VirtualFile.getChildren()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java), you may not get `fileCreated` notifications when files are created in that directory.

If you loaded only a single file in a directory using `VirtualFile.findChild()`, you will get notifications for changes to that file, but you may not get created/deleted notifications for other files in the same directory.
