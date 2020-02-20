---
title: Modifying the PSI
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The PSI is a read-write representation of the source code as a tree of elements corresponding to the structure of a source
file. You can modify the PSI by *adding*, *replacing*, and *deleting* PSI elements.

To perform these operations, you use methods such as `PsiElement.add()`, `PsiElement.delete()`, and `PsiElement.replace()`,
as well as other methods defined in the `PsiElement` interface that let you process multiple elements in a single
operation, or to specify the exact location in the tree where an element needs to be added.

Just as document operations, PSI modifications need to be wrapped in a write action and in a command (and therefore
can only be performed in the event dispatch thread). See [the Documents article](documents.md#what-are-the-rules-of-working-with-documents)
for more information on commands and write actions.


## Creating the New PSI

The PSI elements to add to the tree, or to replace existing PSI elements, are normally *created from text*.
In the most general case, you use the `createFileFromText()` method of [`PsiFileFactory`](upsource:///platform/core-api/src/com/intellij/psi/PsiFileFactory.java)
to create a new file that contains the code construct which you need to add to the tree or to use as a replacement
for an existing element, traverse the resulting tree to locate the specific element that you need, and then pass that
element to `add()` or `replace()`.

Most languages provide factory methods which let you create specific code constructs more easily. For example,
the [`PsiJavaParserFacade`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiJavaParserFacade.java) class
contains methods such as `createMethodFromText()`, which creates a Java method from the given text.

When you're implementing refactorings, intentions or inspection quickfixes that work with existing code, the text that
you pass to the various `createFromText()` methods will combine hard-coded fragments and fragments of code taken from
the existing file. For small code fragments (individual identifiers), you can simply append the text from the existing
code to the text of the code fragment you're building. In that case, you need to make sure that the resulting text is 
syntactically correct, otherwise the `createFromText()` method will throw an exception. 

For larger code fragments, it's best to perform the modification in several steps: 

 * create a replacement tree fragment from text, leaving placeholders for the user code fragments;
 * replace the placeholders with the user code fragments;
 * replace the element in the original source file with the replacement tree.

This ensures that the formatting of the user code is preserved and that the modification doesn't introduce any unwanted
whitespace changes.  

As an example of this approach, see the quickfix in the `ComparingReferencesInspection` example:

```java
// binaryExpression holds a PSI expression of the form "x == y", which needs to be replaced with "x.equals(y)"
PsiBinaryExpression binaryExpression = (PsiBinaryExpression) descriptor.getPsiElement();
IElementType opSign = binaryExpression.getOperationTokenType();
PsiExpression lExpr = binaryExpression.getLOperand();
PsiExpression rExpr = binaryExpression.getROperand();

// Step 1: Create a replacement fragment from text, with "a" and "b" as placeholders
PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
PsiMethodCallExpression equalsCall =
    (PsiMethodCallExpression) factory.createExpressionFromText("a.equals(b)", null);

// Step 2: replace "a" and "b" with elements from the original file
equalsCall.getMethodExpression().getQualifierExpression().replace(lExpr);
equalsCall.getArgumentList().getExpressions()[0].replace(rExpr);

// Step 3: replace a larger element in the original file with the replacement tree
PsiExpression result = (PsiExpression) binaryExpression.replace(equalsCall);
```

Just as everywhere else in the IntelliJ Platform API, the text passed to `createFileFromText()` and other `createFromText()`
methods must use only `\n` as line separators.


## Maintaining Tree Structure Consistency

The PSI modification methods do not restrict you in the way you can build the resulting tree structure. For example,
when working with a Java class, you can add a `for` statement as a direct child of a `PsiMethod` element, even though
the Java parser will never produce such a structure (the `for` statement will always be a child of the `PsiCodeBlock`)
representing the method body). Modifications that produce incorrect tree structures may appear to work, but they will
lead to problems and exceptions later. Therefore, you always need to ensure that the structure you built with PSI
modification operations is the same as what the parser would produce when parsing the code that you've built.

To make sure you're not introducing inconsistencies, you can call `PsiTestUtil.checkFileStructure()` in the tests for
your action that modifies the PSI. This method ensures that the structure you've built is the same as what the parser produces.


## Whitespaces and Imports

When working with PSI modification functions, you should never create individual whitespace nodes (spaces or line breaks)
from text. Instead, all whitespace modifications are performed by the formatter, which follows the code style settings
selected by the user. Formatting is automatically performed at the end of every command, and if you need, you can
also perform it manually using the `reformat(PsiElement)` method in the
[`CodeStyleManager`](upsource:///platform/core-api/src/com/intellij/psi/codeStyle/CodeStyleManager.java) class.

Also, when working with Java code (or with code in other languages with a similar import mechanism such as Groovy or Python),
you should never create imports manually. Instead, you should insert fully-qualified names into the code you're
generating, and then call the `shortenClassReferences()` method in the 
[`JavaCodeStyleManager`](upsource:///java/java-psi-api/src/com/intellij/psi/codeStyle/JavaCodeStyleManager.java)
(or the equivalent API for the language you're working with). This ensures that the imports are created according to
the user's code style settings and inserted into the correct place of the file.


## Combining PSI and Document Modifications 

In some cases, you need to perform a PSI modification and then to perform an operation on the document you've just
modified through the PSI (for example, start a live template). In this case, you need to call a special method that
completes the PSI-based post-processing (such as formatting) and commits the changes to the document. The method
you need to call is called `doPostponedOperationsAndUnblockDocument()`, and it's defined in the
[`PsiDocumentManager`](upsource:///platform/core-api/src/com/intellij/psi/PsiDocumentManager.java) class.

