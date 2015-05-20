---
layout: editable
title: PSI Files
---

A PSI (Program Structure Interface) file is the root of a structure representing the contents of a file as a hierarchy of elements in a particular programming language.
The
[PsiFile](https://github.com/JetBrains/intellij-community/tree/master/platform/core-api/src/com/intellij/psi/PsiFile.java)
class is the common base class for all PSI files, while files in a specific language are usually represented by its subclasses.
For example, the
[PsiJavaFile](https://github.com/JetBrains/intellij-community/tree/master/java/java-psi-api/src/com/intellij/psi/PsiJavaFile.java)
class represents a Java file, and the
[XmlFile](https://github.com/JetBrains/intellij-community/tree/master/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFile.java)
class represents an XML file.

Unlike ```VirtualFile``` and ```Document```, which have application scope (even if multiple projects are open, each file is represented by the same ```VirtualFile``` instance), PSI has project scope (the same file is represented by multiple PsiFile instances if the file belongs to multiple projects open at the same time).

## How do I get one?

*  From an action: ```e.getData(LangDataKeys.PSI_FILE)```.
*  From a VirtualFile: ```PsiManager.getInstance(project).findFile()```
*  From a Document: ```PsiDocumentManager.getInstance(project).getPsiFile()```
*  From an element inside the file: ```psiElement.getContainingFile()```
*  To find files with a specific name anywhere in the project, use ```FilenameIndex.getFilesByName(project, name, scope)```

## What can I do with one?

Most interesting modification operations are performed on the level of individual PSI elements, not files as a whole.

To iterate over the elements in a file, use ```psiFile.accept(new PsiRecursiveElementWalkingVisitor()...);```

## Where does it come from?

As the PSI is language-dependent, PSI files are created through the 
[Language](https://github.com/JetBrains/intellij-community/tree/master/platform/core-api/src/com/intellij/lang/Language.java) 
object, using the ```LanguageParserDefinitions.INSTANCE.forLanguage(language).createFile(fileViewProvider)``` method.

Like documents, PSI files are created on demand when the PSI is accessed for a particular file.

## How long does it live?

Again, like documents, PSI files are weakly referenced from the corresponding ```VirtualFile``` instances and can be garbage collected if not referenced by anyone.

## How do I create one?

The
[PsiFileFactory](https://github.com/JetBrains/intellij-community/tree/master/platform/core-api/src/com/intellij/psi/PsiFileFactory.java).
```getInstance(project).createFileFromText()``` method creates an in-memory PSI file with the specified contents.
To save the PSI file to disk, use the
[PsiDirectory](https://github.com/JetBrains/intellij-community/tree/master/platform/core-api/src/com/intellij/psi/PsiDirectory.java).
```add()``` method.

## How do I get notified when it changes?

```PsiManager.getInstance(project).addPsiTreeChangeListener()``` allows you to receive notifications about all changes to the PSI tree of a project.


## How do I extend it?

The PSI can be extended to support additional languages through custom language plugins.
Developing such plugins is documented in 
[Custom Language Support](reference_guide/custom_language_support.html) 
reference guide.

## What are the rules for working with it?

Any changes done to the content of PSI files are reflected in documents, so all rules for working with documents (read/write actions, commands, read-only status handling) are in effect.
