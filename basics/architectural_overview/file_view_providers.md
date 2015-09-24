---
title: File View Providers
---

A file view provider (see the [FileViewProvider](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/core-api/src/com/intellij/psi/FileViewProvider.java) class) was introduced in IntelliJ IDEA 6.0. Its main purpose is to manage access to multiple PSI trees within a single file.
For example, a JSPX page has a separate PSI tree for the Java code in it (`PsiJavaFile`), a separate tree for the XML code (`XmlFile`), and a separate tree for JSP as a whole
[JspFile](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/java/jsp-openapi/src/com/intellij/psi/jsp/JspFile.java)).
Each of the PSI trees covers the entire contents of the file, and contains special "outer language elements" in the places where contents in a different language can be found.

A `FileViewProvider` instance corresponds to a single `VirtualFile`, a single `Document`, and can be used to retrieve multiple `PsiFile` instances.

## How do I get an FVP?

*  From a VirtualFile: `PsiManager.getInstance(project).findViewProvider()`
*  From a PSI file: `psiFile.getViewProvider()`

## What can I do with an FVP?

*  To get the list of all languages for which PSI trees exist in a file: `fileViewProvider.getLanguages()`
*  To get the PSI tree for a particular language: `fileViewProvider.getPsi(language)`, where the `language` parameter can take values of the
[Language](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/core-api/src/com/intellij/lang/Language.java)
type defined in
[StdLanguages](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/platform-api/src/com/intellij/lang/StdLanguages.java)
class. For example, to get the PSI tree for XML, use `fileViewProvider.getPsi(StdLanguages.XML)`.
*  To find an element of a particular language at the specified offset in the file: `fileViewProvider.findElementAt(offset,language)`

## How do I extend FVP?

To create a file type that has multiple interspersing trees for different languages, your plugin must contain an extension to the _fileType.fileViewProviderFactory_
[extension point](/basics/plugin_structure/plugin_extensions_and_extension_points.html)
available in the IntelliJ Platform core.
This extension point is declared using the
[FileTypeExtensionPoint](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeExtensionPoint.java)
bean class.
To access this extension point, create a Java class that implements the
[FileViewProviderFactory](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java)
interface, and in this class, override the `createFileViewProvider` method.
To declare the extension to the _fileType.fileViewProviderFactory_ extension point, to the `<extensions>` section of the plugin.xml file, add the following syntax:

```
<fileType.fileViewProviderFactory filetype=%file type% implementationClass=%class name%>
 </fileType.fileViewProviderFactory>
```

where the _%file type%_ refers to the type of the file being created (for example, to "JFS"), and the _%class name%_ refers to the name of your Java class that implements the `FileViewProviderFactory` interface.
