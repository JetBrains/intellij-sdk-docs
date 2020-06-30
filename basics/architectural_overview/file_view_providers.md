---
title: File View Providers
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A file view provider ([`FileViewProvider`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProvider.java)) manages access to multiple PSI trees within a single file.

For example, a JSPX page has a separate PSI tree for the Java code in it (`PsiJavaFile`), a separate tree for the XML code (`XmlFile`), and a separate tree for JSP as a whole ([`JspFile`](upsource:///java/jsp-openapi/src/com/intellij/psi/jsp/JspFile.java)).

Each of the PSI trees covers the entire contents of the file, and contains special "outer language elements" in the places where contents in a different language can be found.

A `FileViewProvider` instance corresponds to a single `VirtualFile`, a single `Document`, and can be used to retrieve multiple `PsiFile` instances.

## How do I get a FileViewProvider?

* From a `VirtualFile`: `PsiManager.getInstance(project).findViewProvider()`
* From a `PsiFile`: `psiFile.getViewProvider()`

## What can I do with a FileViewProvider?

* To get the set of all languages for which PSI trees exist in a file: `fileViewProvider.getLanguages()`
* To get the PSI tree for a particular language: `fileViewProvider.getPsi(language)`. For example, to get the PSI tree for XML, use `fileViewProvider.getPsi(XMLLanguage.INSTANCE)`.
* To find an element of a particular language at the specified offset in the file: `fileViewProvider.findElementAt(offset, language)`

## How do I extend the FileViewProvider?

To create a file type that has multiple interspersing trees for different languages, a plugin must contain an extension to the `com.intellij.fileType.fileViewProviderFactory` extension point.

Implement [`FileViewProviderFactory`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) and return your `FileViewProvider` implementation from `createFileViewProvider()` method.

Register as follows in `plugin.xml`:

```xml
<extensions defaultExtensionNs="com.intellij">
  <fileType.fileViewProviderFactory filetype="%file_type%" implementationClass="com.plugin.MyFileViewProviderFactory" />
</extensions>
```

Where `%file_type%` refers to the type of the file being created (for example, "JFS").