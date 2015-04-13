---
layout: editable
title: Architectural Overview
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview
-->

The goal of this topic is to describe the architecture of IntelliJ IDEA from a plugin developer's point of view. It will be organized in a
task-based manner: rather than listing all the things that you can do with each object and describing how they are all implemented, it will try
to answer questions "what can I do with this object", "how do I get to this object" and so on.

This topic assumes that the reader is familiar with the basic concepts of IntelliJ IDEA plugin development. If you don't know anything at all about plugin development, you should start with the live demo and tutorials at 
[http://www.jetbrains.com/idea/plugins/index.html](http://www.jetbrains.com/idea/plugins/index.html), 
and then return to this document.

This topic covers the following subjects:

* General Threading Rules

* Virtual Files

* Documents
 
* PSI Files

* File View Providers
 
* Psi Elements 

## General Threading Rules

In general, the data structures of IntelliJ IDEA are covered by a single "multiple readers / single writer" lock. 
Reading data is allowed from any thread. 
Reading data from the UI thread does not require any special effort, however, read operations performed from any other thread need to be wrapped in a read action by using ```ApplicationManager.getApplication().runReadAction()```. 
Writing the data is only allowed from the UI thread, and write operations always need to be wrapped in a write action with ```ApplicationManager.getApplication().runWriteAction()```.

To pass control from a background thread to the event dispatch thread, instead of the standard ```SwingUtilities.invokeLater()```, plugins should use ```ApplicationManager.getApplication().invokeLater()```. The latter API allows to specify the _modality state_ for the call - the stack of modal dialogs under which the call is allowed to execute. Passing ```ModalityState.NON_MODAL``` means that the operation will be executed after all modal dialogs are closed, and passing ```ModalityState.stateForComponent()``` means that the operation may be executed while the specified component (part of a dialog) is still visible.

## Virtual Files

A virtual file 
[com.intellij.openapi.vfs.VirtualFile](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) is IntelliJ IDEA's representation of a file in a file system (VFS). The most common case of a virtual file is a file in your local file system. However, IDEA supports multiple pluggable file system implementations, so virtual files can also represent classes in a JAR file, old revisions of files loaded from the CVS repository and so on.

The VFS level deals only with binary content. You can get or set the contents of a VirtualFile as a stream of bytes, but concepts like encodings and line separators are handled by higher levels of the system.

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
There might be several instances corresponding to the same file, and they can be garbage-collected. The file is a ```UserDataHolder```, and the user data is shared between those equal instances. 
If a file is deleted, its corresponding VirtualFile instance becomes invalid ( the ```isValid()``` method returns _false_ and operations cause exceptions).

#### How do I create one?

Usually you don't: as a rule, files are created either through the PSI API or through the regular java.io.File API. If you do need to create a file through VFS, you can use the ```VirtualFile.createChildData()``` method to create a ```VirtualFile``` instance and the ```VirtualFile.setBinaryContent()``` method to write some data to the file.

#### How do I get notified when it changes?

The ```VirtualFileManager.addVirtualFileListener()``` method allows you to receive notifications about all changes in the VFS.

#### How do I extend it?

To provide an alternative file system implementation (for example, an FTP file system), implement the [VirtualFileSystem](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) class (most likely you'll also need to implement ```VirtualFile```), and register your implementation as an application component [http://confluence.jetbrains.net/display/IDEADEV/IntelliJ+IDEA+Plugin+Structure#IntelliJIDEAPluginStructure-PluginComponents]. 
To hook into operations performed in the local file system (for example, if you deal with development of a version control system integration that needs custom rename/move handling), implement the [LocalFileOperationsHandler] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) interface and register it through the```LocalFileSystem.registerAuxiliaryFileOperationsHandler``` method.

#### What are the rules for working with it?

See [IntelliJ IDEA Virtual File System]() for a detailed description of the VFS architecture and usage rules.

#### Samples

Sample plugin projects that illustrate how to work with virtual files are available in the _<%IDEA project directory%>/community/samples/vfs_ and _<%IDEA project directory%>/community/samples/textEditor_ directories.

## Documents

A document is an editable sequence of Unicode characters, which typically corresponds to the text contents of a virtual file. 
Line breaks in a document are always normalized to \n. IntelliJ IDEA handles encoding and line break conversions when loading and saving documents transparently.

#### How do I get one?

*  From an action: ```e.getData(PlatformDataKeys.EDITOR).getDocument()```
*  From a virtual file: ```FileDocumentManager.getDocument()```. This call forces the document content to be loaded from disk if it wasn't loaded previously; if you're only interested in open documents or documents which may have been modified, use ```FileDocumentManager.getCachedDocument()``` instead.
*  From a PSI file: ```PsiDocumentManager.getInstance().getDocument()``` or ```PsiDocumentManager.getInstance().getCachedDocument()```

#### What can I do with one?

Any operations which access or modify the file contents on "plain text" level (as a sequence of characters, not as a tree of Java elements).

#### Where does it come from?

Document instances are created when some operation needs to access the text contents of a file (in particular, this is needed to build the PSI for a file). Also, document instances not linked to any virtual files can be created temporarily, for example, to represent the contents of a text editor field in a dialog.

#### How long does it live?

Document instances are weakly referenced from VirtualFile instances. Thus, an unmodified Document instance can be garbage-collected if it isn't referenced by anyone, and a new instance will be created if the document contents is accessed again later. Storing Document references in long-term data structures of your plugin will cause memory leaks.

#### How do I create one?

If you need to create a new file on disk, you don't create a document: you create a PSI file and then get its document. If you need to create a document instance which isn't bound to anything, you can use ```EditorFactory.createDocument```.

#### How do I get notified when it changes?

*  ```Document.addDocumentListener``` allows you to receive notifications about changes in a particular Document instance.
*  ```EditorFactory.getEventMulticaster().addDocumentListener``` allows you to receive notifications about changes in all open documents.
*  ```FileDocumentManager.addFileDocumentManagerListener``` allows you to receive notifications when any document is saved or reloaded from disk.

#### What are the rules of working with it?

The general read/write action rules are in effect. In addition to that, any operations which modify the contents of the document must be wrapped in a command (```CommandProcessor.getInstance().executeCommand()```). ```executeCommand()``` calls can be nested, and the outermost ```executeCommand``` call is added to the undo stack. If multiple documents are modified within a command, undoing this command will by default show a confirmation dialog to the user.

If the file corresponding to a document is read-only (for example, not checked out from the version control system), document modifications will fail. Thus, before modifying the document, it is necessary to call ```ReadonlyStatusHandler.getInstance(project).ensureFilesWritable()``` to check out the file if necessary.

All text strings passed to document modification methods (```setText```, ```insertString```, ```replaceString```) must use only \n as line separators.

#### Samples

A sample plugin project that illustrates how to work with document files is available in the _<%IDEA project directory%>/community/samples/textEditor_ directory. For more information, see [Sample Text File Editor]().

## PSI Files

A PSI (Program Structure Interface) file is the root of a structure representing the contents of a file as a hierarchy of elements in a particular programming language. The
[PsiFile](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiFile.java) 
class is the common base class for all PSI files, while files in a specific language are usually represented by its subclasses.
For example, the 
[PsiJavaFile](https://github.com/JetBrains/intellij-community/blob/master/java/java-psi-api/src/com/intellij/psi/PsiJavaFile.java) 
class represents a Java file, and the 
[XmlFile](https://github.com/JetBrains/intellij-community/blob/master/xml/openapi/src/com/intellij/psi/xml/XmlFile.java)  
class represents an XML file.

Unlike ```VirtualFile``` and ```Document```, which have application scope (even if multiple projects are open, each file is represented by the same ```VirtualFile``` instance), PSI has project scope (the same file is represented by multiple PsiFile instances if the file belongs to multiple projects open at the same time).

#### How do I get one?

*  From an action: ```e.getData(LangDataKeys.PSI_FILE)```.
*  From a VirtualFile: ```PsiManager.getInstance(project).findFile()```
*  From a Document: ```PsiDocumentManager.getInstance(project).getPsiFile()```
*  From an element inside the file: ```psiElement.getContainingFile()```
*  To find files with a specific name anywhere in the project, use ```FilenameIndex.getFilesByName(project, name, scope)```

#### What can I do with one?

Most interesting modification operations are performed on the level of individual PSI elements, not files as a whole.

To iterate over the elements in a file, use ```psiFile.accept(new PsiRecursiveElementWalkingVisitor()...);```

#### Where does it come from?

As the PSI is language-dependent, PSI files are created through the (link: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Language.java text: Language ) object, using the ```LanguageParserDefinitions.INSTANCE.forLanguage(language).createFile(fileViewProvider)``` method.

Like documents, PSI files are created on demand when the PSI is accessed for a particular file.

#### How long does it live?

Again, like documents, PSI files are weakly referenced from the corresponding ```VirtualFile``` instances and can be garbage collected if not referenced by anyone.

#### How do I create one?

The 
[PsiFileFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiFileFactory.java).
```getInstance(project).createFileFromText()``` method creates an in-memory PSI file with the specified contents.
To save the PSI file to disk, use the
[PsiDirectory](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiDirectory.java).
```add()``` method.

#### How do I get notified when it changes?

```PsiManager.getInstance(project).addPsiTreeChangeListener()``` allows you to receive notifications about all changes to the PSI tree of a project.

#### How do I extend it?

The PSI can be extended to support additional languages through custom language plugins. Developing such plugins is extensively documented in [another article] (Developing Custom Language Plugins for IntelliJ IDEA).

#### What are the rules for working with it?

Any changes done to the content of PSI files are reflected in documents, so all rules for working with documents (read/write actions, commands, read-only status handling) are in effect.


## File View Providers

A file view provider (see the [FileViewProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/FileViewProvider.java) class) is a new concept in IntelliJ IDEA beginning with version 6.0. Its main purpose is managing access to multiple PSI trees within a single file. For example, a JSPX page has a separate PSI tree for the Java code in it (```PsiJavaFile```), a separate tree for the XML code (```XmlFile```), and a separate tree for JSP as a whole 
[JspFile](https://github.com/JetBrains/intellij-community/blob/master/java/jsp-openapi/src/com/intellij/psi/jsp/JspFile.java)). 
Each of the PSI trees covers the entire contents of the file, and contains special "outer language elements" in the places where contents in a different language can be found.

A ```FileViewProvider``` instance corresponds to a single ```VirtualFile```, a single ```Document```, and can be used to retrieve multiple ```PsiFile``` instances.

#### How do I get one?

*  From a VirtualFile: ```PsiManager.getInstance(project).findViewProvider()```
*  From a PSI file: ```psiFile.getViewProvider()```

#### What can I do with one?

*  To get the list of all languages for which PSI trees exist in a file: ```fileViewProvider.getLanguages()```
*  To get the PSI tree for a particular language: ```fileViewProvider.getPsi(language)```, where the ```language``` parameter can take values of the 
[Language](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Language.java) 
type defined in 
[StdLanguages](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/lang/StdLanguages.java) 
class. For example, to get the PSI tree for XML, use ```fileViewProvider.getPsi(StdLanguages.XML)```.
*  To find an element of a particular language at the specified offset in the file: ```fileViewProvider.findElementAt(offset,language)```

#### How do I extend it?

To create a file type that has multiple interspersing trees for different languages, your plugin must contain an extension to the _fileType.fileViewProviderFactory_ 
[extension point](http://confluence.jetbrains.net/display/IDEADEV/IntelliJ+IDEA+Plugin+Structure#IntelliJIDEAPluginStructure-PluginExtensions) 
available in the IntelliJ IDEA core. 
This extension point is declared using the 
[FileTypeExtensionPoint](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeExtensionPoint.java)
bean class. 
To access this extension point, create a Java class that implements the 
[FileViewProviderFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java)
interface, and in this class, override the ```createFileViewProvider``` method.
To declare the extension to the _fileType.fileViewProviderFactory_ extension point, to the <extensions> section of the plugin.xml file, add the following syntax:

```
<fileType.fileViewProviderFactory filetype=%file type% implementationClass=%class name%>
 </fileType.fileViewProviderFactory>
```

where the _%file type%_ refers to the type of the file to create (for example, to "JFS"), and the _%class name%_ refers to the name of your Java class that implements the ```FileViewProviderFactory``` interface.

## PSI Elements

A PSI (Program Structure Interface) file represents a hierarchy of PSI elements (so called _PSI trees_).  A single PSI file may include several PSI trees in a particular programming language. A PSI element, in its turn, can have child PSI elements.
PSI elements and operations on the level of individual PSI elements are used to explore the internal structure of source code as it is interpreted by **IntelliJ IDEA**. For example, you can use PSI elements to perform the code analysis, such as 
[code inspections](http://www.jetbrains.com/idea/webhelp/code-inspection.html)
or 
[intention actions](http://www.jetbrains.com/idea/webhelp/intention-actions.html).
The 
[PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java) 
class is the common base class for PSI elements.

#### How do I get a PSI element?

*  From an action: ```e.getData(LangDataKeys.PSI_ELEMENT)```. Note: if an editor is currently open and the element under caret is a reference, this will return the result of resolving the reference. This may or not be what you need.
*  From a file by offset: ```PsiFile.findElementAt()```. Note: this returns the lowest level element at the specified offset, which is normally a lexer token. 
You most likely need to use PsiTreeUtil.getParentOfType() to find the element you really need.
*  By iterating through a PSI file: using a ```PsiRecursiveElementWalkingVisitor```.
*  By resolving a reference: ```PsiReference.resolve()```

#### What can I do with one?

<!--TODO link to PSI Cook Book-->
See PSI Cook Book