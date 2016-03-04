---
title: File View Providers
---

A file view provider (see the [FileViewProvider](upsource:///platform/core-api/src/com/intellij/psi/FileViewProvider.java) class) was introduced in *IntelliJ IDEA* 6.0. Its main purpose is to manage access to multiple PSI trees within a single file.

For example, a JSPX page has a separate PSI tree for the Java code in it (`PsiJavaFile`), a separate tree for the XML code (`XmlFile`), and a separate tree for JSP as a whole [JspFile](upsource:///java/jsp-openapi/src/com/intellij/psi/jsp/JspFile.java)).

Each of the PSI trees covers the entire contents of the file, and contains special "outer language elements" in the places where contents in a different language can be found.

A [`FileViewProvider`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProvider.java) instance corresponds to a single `VirtualFile`, a single `Document`, and can be used to retrieve multiple `PsiFile` instances.

## How do I get an FVP?

* From a VirtualFile: `PsiManager.getInstance(project).findViewProvider()`
* From a PSI file: `psiFile.getViewProvider()`

## What can I do with an FVP?

* To get the list of all languages for which PSI trees exist in a file: `fileViewProvider.getLanguages()`
* To get the PSI tree for a particular language: `fileViewProvider.getPsi(language)`, where the `language` parameter can take values of the [Language](upsource:///platform/core-api/src/com/intellij/lang/Language.java) type defined in [StdLanguages](upsource:///platform/platform-api/src/com/intellij/lang/StdLanguages.java) class. For example, to get the PSI tree for XML, use `fileViewProvider.getPsi(StdLanguages.XML)`.
* To find an element of a particular language at the specified offset in the file: `fileViewProvider.findElementAt(offset,language)`

## How do I extend the FileViewProvider?

To create a file type that has multiple interspersing trees for different languages, your plugin must contain an extension to the `fileType.fileViewProviderFactory` [extension point](/basics/plugin_structure/plugin_extensions_and_extension_points.md) available in the *IntelliJ Platform* core.

This extension point is declared using the [FileTypeExtensionPoint](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeExtensionPoint.java)
bean class.

To access this extension point, create a Java class that implements the [FileViewProviderFactory](upsource:///platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) interface, and in this class, override the `createFileViewProvider` method.

To declare the extension to the `fileType.fileViewProviderFactory` extension point, to the `<extensions>` section of the plugin.xml file, add the following syntax:

```xml
<extensions>
  <fileType.fileViewProviderFactory filetype="%file_type%" implementationClass="%class_name%" />
</extensions>
```

Where `%file_type%` refers to the type of the file being created (for example, "JFS"), and the `%class_name%` refers to the name of your Java class that implements the [`FileViewProviderFactory`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) interface.
