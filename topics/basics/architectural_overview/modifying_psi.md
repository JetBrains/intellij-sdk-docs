<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Modifying the PSI

<link-summary>Creating, deleting, and modifying PSI elements.</link-summary>

The PSI is a read/write representation of the source code as a tree of elements corresponding to a source file's structure.
You can modify the PSI by *adding*, *replacing*, and *deleting* PSI elements.

To perform these operations, you use methods such as `PsiElement.add()`, `PsiElement.delete()`, and `PsiElement.replace()`, as well as other methods defined in the [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) interface that let you process multiple elements in a single operation, or to specify the exact location in the tree where an element needs to be added.

Like document operations, PSI modifications need to be wrapped in a write action and in command (and can only be performed in the event dispatch thread).
See [the Documents article](documents.md#what-are-the-rules-of-working-with-documents) for more information on commands and write actions.

## Creating the New PSI

The PSI elements to add to the tree or replace existing PSI elements are usually *created from text*.
In the most general case, you use the `createFileFromText()` method of [`PsiFileFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiFileFactory.java) to create a new file that contains the code construct which you need to add to the tree or to use as a replacement for an existing element, traverse the resulting tree to locate the specific part that you need, and then pass that element to `add()` or `replace()`.
See also [](psi_files.md#how-do-i-create-a-psi-file).

Most languages provide factory methods that let you create specific code constructs more easily.
Examples:
* [`PsiJavaParserFacade`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiJavaParserFacade.java) class contains methods such as `createMethodFromText()`, which creates a Java method from the given text
* [`SimpleElementFactory.createProperty()`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementFactory.java) creating a Simple language property

When you're implementing refactorings, [intentions](code_intentions.md), or inspection [quickfixes](code_inspections_and_intentions.md) that work with existing code, the text that you pass to the various `createFromText()` methods will combine hard-coded fragments and fragments of code taken from the existing file.
For small code fragments (individual identifiers), you can simply append the text from the existing code to the text of the code fragment you are building.
In that case, you need to make sure that the resulting text is syntactically correct.
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

The PSI modification methods do not restrict you in the way you can build the resulting tree structure.
For example, when working with a Java class, you can add a `for` statement as a direct child of a `PsiMethod` element, even though the Java parser will never produce such a structure (the `for` statement will always be a child of the `PsiCodeBlock`) representing the method body.
Modifications that produce incorrect tree structures may appear to work, but they will lead to problems and exceptions later.
Therefore, you always need to ensure that the structure you built with PSI modification operations is the same as what the parser would produce when parsing the code that you've created.

To make sure you're not introducing inconsistencies, you can call `PsiTestUtil.checkFileStructure()` in the tests for your action that modifies the PSI.
This method ensures that the structure you've built is the same as what the parser produces.

## Whitespaces and Imports

When working with PSI modification functions, you should never create individual whitespace nodes (spaces or line breaks) from the text.
Instead, all whitespace modifications are performed by the formatter, which follows the code style settings selected by the user.
Formatting is automatically performed at the end of every command, and if you need, you can also perform it manually using the `reformat(PsiElement)` method in the [`CodeStyleManager`](%gh-ic%/platform/core-api/src/com/intellij/psi/codeStyle/CodeStyleManager.java) class.

Also, when working with Java code (or with code in other languages with a similar import mechanism such as Groovy or Python), you should never create imports manually.
Instead, you should insert fully-qualified names into the code you're generating, and then call the `shortenClassReferences()` method in the [`JavaCodeStyleManager`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/codeStyle/JavaCodeStyleManager.java) (or the equivalent API for the language you're working with).
This ensures that the imports are created according to the user's code style settings and inserted into the file's correct place.

## Combining PSI and Document Modifications

In some cases, you need to perform a PSI modification and then to perform an operation on the document you've just modified through the PSI (for example, start a [live template](live_templates.md)).
To complete the PSI-based post-processing (such as formatting) and commit the changes to the document, call `doPostponedOperationsAndUnblockDocument()` on [`PsiDocumentManager`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java) instance.
