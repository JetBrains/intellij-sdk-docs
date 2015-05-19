---
layout: editable
title: Virtual Files
---

A virtual file
[com.intellij.openapi.vfs.VirtualFile](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) is IntelliJ IDEA's representation of a file in a file system (VFS). The most common case of a virtual file is a file in your local file system. However, IDEA supports multiple pluggable file system implementations, so virtual files can also represent classes in a JAR file, old revisions of files loaded from the CVS repository and so on.

The VFS level deals only with binary content.
You can get or set the contents of a VirtualFile as a stream of bytes, but concepts like encodings and line separators are handled by higher levels of the system.

#### How do I get one?

*  From an action: ```e.getData(PlatformDataKeys.VIRTUAL_FILE)```. If you are interested in multiple selection, you can also use ```e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY)```.

*  From a path in the local file system: ```LocalFileSystem.getInstance().findFileByIoFile()```

*  From a PSI file: ```psiFile.getVirtualFile()``` (may return null if the PSI file exists only in memory)

*  From a document: ```FileDocumentManager.getInstance().getFile()```

#### What can I do with one?

Traverse the file system, get file contents, rename, move, delete - typical file operations.

Recursive iteration should be performed using ```VfsUtilCore.iterateChildrenRecursively``` to prevent endless loops caused by recursive symlinks.

#### Where does it come from?

The VFS is built incrementally, by scanning the file system up and down starting from the project root.
New files appearing in the file system are detected by VFS _refreshes_. A refresh operation can be initiated programmatically using (```VirtualFileManager.getInstance().refresh()``` or ```VirtualFile.refresh()```).
VFS refreshes are also caused by the file system change notifications received by file system watchers (availble for the Windows and Mac operating systems).
As a plugin developer, you may need to invoke a VFS refresh if you need to access a file that has just been created by an external tool through IDEA's APIs.

#### How long does it live?

A particular file on disk is represented by the equal&nbsp;```VirtualFile``` instances for the entire lifetime of the IDEA process.
There might be several instances corresponding to the same file, and they can be garbage-collected.
The file is a ```UserDataHolder```, and the user data is shared between those equal instances.
If a file is deleted, its corresponding VirtualFile instance becomes invalid ( the ```isValid()``` method returns _false_ and operations cause exceptions).

#### How do I create one?

Usually you don't: as a rule, files are created either through the PSI API or through the regular java.io.File API.
If you do need to create a file through VFS, you can use the ```VirtualFile.createChildData()``` method to create a ```VirtualFile``` instance and the ```VirtualFile.setBinaryContent()``` method to write some data to the file.

#### How do I get notified when it changes?

The ```VirtualFileManager.addVirtualFileListener()``` method allows you to receive notifications about all changes in the VFS.

#### How do I extend it?

To provide an alternative file system implementation (for example, an FTP file system), implement the
[VirtualFileSystem](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java)
class (most likely you'll also need to implement ```VirtualFile```), and register your implementation as an
[application component](http://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_components.html).
To hook into operations performed in the local file system (for example, if you deal with development of a version control system integration that needs custom rename/move handling), implement the 
[LocalFileOperationsHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) 
interface and register it through the```LocalFileSystem.registerAuxiliaryFileOperationsHandler``` method.

#### What are the rules for working with it?

See
[IntelliJ Platform Virtual File System](http://www.jetbrains.org/intellij/sdk/docs/basics/virtual_file_system.html)
for a detailed description of the VFS architecture and usage rules.