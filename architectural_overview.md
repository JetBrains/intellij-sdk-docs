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

* [General Threading Rules](general_threading_rules.html)

* [Virtual Files](virtual_file.html)

* [Documents](documents.html)
 
* PSI Files

* File View Providers
 
* Psi Elements 

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