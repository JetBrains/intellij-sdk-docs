---
layout: editable
title: File View Providers
---

A file view provider (see the [FileViewProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/FileViewProvider.java) class) is a new concept in IntelliJ IDEA beginning with version 6.0. Its main purpose is managing access to multiple PSI trees within a single file.
For example, a JSPX page has a separate PSI tree for the Java code in it (```PsiJavaFile```), a separate tree for the XML code (```XmlFile```), and a separate tree for JSP as a whole
[JspFile](https://github.com/JetBrains/intellij-community/blob/master/java/jsp-openapi/src/com/intellij/psi/jsp/JspFile.java)).
Each of the PSI trees covers the entire contents of the file, and contains special "outer language elements" in the places where contents in a different language can be found.

A ```FileViewProvider``` instance corresponds to a single ```VirtualFile```, a single ```Document```, and can be used to retrieve multiple ```PsiFile``` instances.

## How do I get one?

*  From a VirtualFile: ```PsiManager.getInstance(project).findViewProvider()```
*  From a PSI file: ```psiFile.getViewProvider()```

## What can I do with one?

*  To get the list of all languages for which PSI trees exist in a file: ```fileViewProvider.getLanguages()```
*  To get the PSI tree for a particular language: ```fileViewProvider.getPsi(language)```, where the ```language``` parameter can take values of the
[Language](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Language.java)
type defined in
[StdLanguages](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/lang/StdLanguages.java)
class. For example, to get the PSI tree for XML, use ```fileViewProvider.getPsi(StdLanguages.XML)```.
*  To find an element of a particular language at the specified offset in the file: ```fileViewProvider.findElementAt(offset,language)```

## How do I extend it?

To create a file type that has multiple interspersing trees for different languages, your plugin must contain an extension to the _fileType.fileViewProviderFactory_
[extension point](http://www.jetbrains.org/intellij/sdk/docs/plugin_extensions_and_extension_points.html)
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