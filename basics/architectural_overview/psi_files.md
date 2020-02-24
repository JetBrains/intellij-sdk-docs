---
title: PSI Files
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A PSI (Program Structure Interface) file is the root of a structure representing the contents of a file as a hierarchy of elements in a particular programming language.

The [`PsiFile`](upsource:///platform/core-api/src/com/intellij/psi/PsiFile.java) class is the common base class for all PSI files, while files in a specific language are usually represented by its subclasses.  For example, the [`PsiJavaFile`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiJavaFile.java) class represents a Java file, and the [`XmlFile`](upsource:///xml/xml-psi-api/src/com/intellij/psi/xml/XmlFile.java) class represents an XML file.

Unlike `VirtualFile` and `Document`, which have application scope (even if multiple projects are open, each file is represented by the same `VirtualFile` instance), PSI has project scope (the same file is represented by multiple `PsiFile` instances if the file belongs to multiple projects open at the same time).

## How do I get a PSI file?

* From an action: `e.getData(LangDataKeys.PSI_FILE)`.
* From a VirtualFile: `PsiManager.getInstance(project).findFile()`
* From a Document: `PsiDocumentManager.getInstance(project).getPsiFile()`
* From an element inside the file: `psiElement.getContainingFile()`
* To find files with a specific name anywhere in the project, use `FilenameIndex.getFilesByName(project, name, scope)`

## What can I do with a PSI file?

Most interesting modification operations are performed on the level of individual PSI elements, not files as a whole.

To iterate over the elements in a file, use `psiFile.accept(new PsiRecursiveElementWalkingVisitor()...);`

## Where does a PSI file come from?

As PSI is language-dependent, PSI files are created through the [`Language`](upsource:///platform/core-api/src/com/intellij/lang/Language.java) object, by using the `LanguageParserDefinitions.INSTANCE.forLanguage(language).createFile(fileViewProvider)` method.

Like documents, PSI files are created on demand when the PSI is accessed for a particular file.

## How long do PSI files persist?

Like documents, PSI files are weakly referenced from the corresponding `VirtualFile` instances and can be garbage-collected if not referenced by anyone.

## How do I create a PSI file?

The [`PsiFileFactory`](upsource:///platform/core-api/src/com/intellij/psi/PsiFileFactory.java) `createFileFromText()` method creates an in-memory PSI file with the specified contents.

To save the PSI file to disk, use the [`PsiDirectory`](upsource:///platform/core-api/src/com/intellij/psi/PsiDirectory.java) `add()` method.

## How do I get notified when PSI files change?

`PsiManager.getInstance(project).addPsiTreeChangeListener()` allows you to receive notifications about all changes to the PSI tree of a project.

## How do I extend PSI?

PSI can be extended to support additional languages through custom language plugins. For more details on developing custom language plugins, see the [Custom Language Support](/reference_guide/custom_language_support.md) reference guide.

## What are the rules for working with PSI?

Any changes done to the content of PSI files are reflected in documents, so all [rules for working with documents](documents.md#what-are-the-rules-of-working-with-documents) (read/write actions, commands, read-only status handling) are in effect.
