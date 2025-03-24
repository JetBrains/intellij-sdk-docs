<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PSI Files

<link-summary>PSI File represents file content as a hierarchy of elements in a particular programming language.</link-summary>

A PSI (Program Structure Interface) file is the root of a structure representing a file's contents as a hierarchy of elements in a particular programming language.

The [`PsiFile`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFile.java) class is the common base class for all PSI files, while files in a specific language are usually represented by its subclasses.  For example, the [`PsiJavaFile`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiJavaFile.java) class represents a Java file, and the [`XmlFile`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFile.java) class represents an XML file.

Unlike [](virtual_file.md) and [](documents.md), which have application scope (even if multiple projects are open, each file is represented by the same
[`VirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) instance), PSI has [](project.md) scope:
the same file is represented by multiple `PsiFile` instances if the file belongs to multiple projects open at the same time.

## How do I get a PSI file?

| Context                         | API                                                                                                                                                                                                                                                                                                                                    |
|---------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](action_system.md)      | [`AnActionEvent.getData(CommonDataKeys.PSI_FILE)`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)                                                                                                                                                                                            |
| [Document](documents.md)        | [`PsiDocumentManager.getPsiFile()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java)                                                                                                                                                                                                                            |
| [PSI Element](psi_elements.md)  | [`PsiElement.getContainingFile()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) (may return `null` if the PSI element is not contained in a file)                                                                                                                                                                   |
| [Virtual File](virtual_file.md) | [`PsiManager.findFile()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiManager.java), [`PsiUtilCore.toPsiFiles()`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiUtilCore.java)                                                                                                                                          |
| File Name                       | [`FilenameIndex.getVirtualFilesByName()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java) and locate via [`PsiManager.findFile()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiManager.java) or [`PsiUtilCore.toPsiFiles()`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiUtilCore.java) |

## What can I do with a PSI file?

Most interesting modification operations are performed on the level of individual PSI elements, not files as a whole.

To iterate over the elements in a file, use

```java
psiFile.accept(new PsiRecursiveElementWalkingVisitor() {
  // visitor implementation ...
});
```

See also [](navigating_psi.md).

## Where does a PSI file come from?

As PSI is language-dependent, PSI files are created using the [`Language`](%gh-ic%/platform/core-api/src/com/intellij/lang/Language.java) instance:

```java
LanguageParserDefinitions.INSTANCE
    .forLanguage(MyLanguage.INSTANCE)
    .createFile(fileViewProvider);
```

Like [](documents.md), PSI files are created on-demand when the PSI is accessed for a particular file.

## How long do PSI files persist?

Like [](documents.md), PSI files are weakly referenced from the corresponding `VirtualFile` instances and can be garbage-collected if not referenced by anyone.

## How do I create a PSI file?

[`PsiFileFactory.createFileFromText()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFileFactory.java) creates an in-memory PSI file with the specified contents.

To save the PSI file to disk, use its parent directory's [`PsiDirectory.add()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDirectory.java).

## How do I get notified when PSI files change?

`PsiManager.addPsiTreeChangeListener()` allows you to receive notifications about all changes to the PSI tree of a project.
Alternatively, register [`PsiTreeChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiTreeChangeListener.java)
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.psi.treeChangeListener"/></include>.

> Please see [`PsiTreeChangeEvent`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiTreeChangeEvent.java) Javadoc for common problems when dealing with PSI events.
>
{style="note"}

## How do I extend PSI?

PSI can be extended to support additional languages through custom language plugins.
For more details on developing custom language plugins, see the [](custom_language_support.md) reference guide.

## What are the rules for working with PSI?

Any changes done to the content of PSI files are reflected in documents, so all [rules for working with documents](documents.md#what-are-the-rules-of-working-with-documents) (read/write actions, commands, read-only status handling) are in effect.
