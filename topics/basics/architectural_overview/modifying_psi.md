<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Modifying the PSI

<link-summary>Creating, deleting, and modifying PSI elements.</link-summary>

The PSI is a read/write representation of the source code as a tree of elements corresponding to a source file's structure.
The PSI can be modified by *adding*, *replacing*, and *deleting* PSI elements.

To perform these operations, use methods such as `PsiElement.add()`, `PsiElement.delete()`, `PsiElement.replace()`, and similar methods allowing to process multiple elements in a single operation, or to specify the exact location in the tree where an element needs to be added.

Like document operations, PSI modifications need to be wrapped in a write action and in command (and can only be performed in the event dispatch thread).
See [the Documents article](documents.md#what-are-the-rules-of-working-with-documents) for more information on commands and write actions.

## Creating the New PSI

The PSI elements to add to the tree or replace existing PSI elements are usually *created from text*.
In most cases, the flow is:

1. Use [`PsiFileFactory.createFileFromText()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFileFactory.java) to create a new file that contains the code construct that needs to be added to the tree or used as a replacement for an existing element. See also [](psi_files.md#how-do-i-create-a-psi-file).
2. Traverse the resulting tree to locate the required element and then pass it to `add()` or `replace()`.

Most languages provide factory methods to create specific code constructs more easily, for example:

* [`PsiJavaParserFacade`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiJavaParserFacade.java) class contains methods such as `createMethodFromText()`, which creates a Java method from the given text
* [`SimpleElementFactory.createProperty()`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementFactory.java) creating a Simple language property

When implementing refactorings, [intentions](code_intentions.md), or inspection [quickfixes](code_inspections_and_intentions.md) that work with existing code, the text passed to the various `createFromText()` methods will combine hard-coded fragments and fragments of code taken from the existing file.
For small code fragments (individual identifiers), append the text from the existing code to the text of the code fragment being built.
In that case, make sure that the resulting text is syntactically correct.
Otherwise, the `createFromText()` method will throw an exception.

For larger code fragments, it's best to perform the modification in several steps:

* create a replacement tree fragment from the text, leaving placeholders for the user code fragments;
* replace the placeholders with the user code fragments;
* replace the element in the original source file with the replacement tree.

This ensures that the user code's formatting is preserved and that the modification does not introduce any unwanted whitespace changes.
Just as everywhere else in the IntelliJ Platform API, the text passed to `createFileFromText()` and other `createFromText()` methods must use only `\n` as line separators.

As an example of this approach, see the quickfix in the `ComparingStringReferencesInspection` [example](code_inspections.md):

```java
```
{src="comparing_string_references_inspection/src/main/java/org/intellij/sdk/codeInspection/ComparingStringReferencesInspection.java" include-symbol="applyFix"}

## Maintaining Tree Structure Consistency

The PSI modification methods do not restrict the way of building the resulting tree structure.

For example, when working with a Java class, it is possible to add a `for` statement as a direct child of a `PsiMethod` element, even though the Java parser will never produce such a structure (the `for` statement will always be a child of the `PsiCodeBlock`) representing the method body.

Modifications that produce incorrect tree structures may appear to work, but they will lead to problems and exceptions later.
Therefore, always ensure that the structure built with PSI modification operations is the same as what the parser would produce when parsing the created code.

To make sure inconsistencies are not introduced, use `PsiTestUtil.checkFileStructure()` in the tests for actions modifying the PSI.
This method ensures that the built structure is the same as what the parser produces.

## Whitespaces and Imports

When working with PSI modification functions, do not create individual whitespace nodes (spaces or line breaks) from text.
Instead, all whitespace modifications are performed by the formatter, which follows the code style settings selected by the user.
Formatting is automatically performed at the end of every command and can be also performed manually with [`CodeStyleManager.reformat(PsiElement)`](%gh-ic%/platform/core-api/src/com/intellij/psi/codeStyle/CodeStyleManager.java) if needed.

Also, when working with Java code (or with code in other languages with a similar import mechanism such as Groovy or Python), do not create imports manually.
Instead, use fully qualified names in generated code and then call [`JavaCodeStyleManager.shortenClassReferences()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/codeStyle/JavaCodeStyleManager.java) (or the equivalent API for the code language).
This ensures that the imports are created according to the user's code style settings and inserted into the file's correct place.

## Combining PSI and Document Modifications

In some cases, after modifying a PSI, it is required to perform an operation on the modified document (for example, start a [live template](live_templates.md)).
To complete the PSI-based post-processing (such as formatting) and commit the changes to the document, call [`PsiDocumentManager.doPostponedOperationsAndUnblockDocument()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java).
