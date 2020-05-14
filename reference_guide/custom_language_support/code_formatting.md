---
title: Code Formatter
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform includes a powerful framework for implementing custom language formatters.
In this framework, the plugin specifies the *constraints* on the spacing between different syntax elements, and the formatting engine, provided by the IDE, calculates the smallest number of whitespace modifications that need to be performed on the file to make it match the constraints.

The process of formatting a file or a file fragment consists of the following main steps:

*  The _formatting model builder_ (
   [`FormattingModelBuilder`](upsource:///platform/lang-api/src/com/intellij/formatting/FormattingModelBuilder.java)
   ), implemented by the plugin, provides a formatting model (
   [`FormattingModel`](upsource:///platform/lang-api/src/com/intellij/formatting/FormattingModel.java)
   ) for the document to be formatted

*  The formatting model is requested to build the structure of the file as applies to formatting, as a tree of _blocks_ (
   [`Block`](upsource:///platform/lang-api/src/com/intellij/formatting/Block.java)
   ) with associated indent, wrap, alignment and spacing settings.

*  The formatting engine calculates the sequence of whitespace characters (spaces, tabs and/or line breaks) that needs to be placed at every block boundary, based on the formatting model provided by the plugin.

*  The formatting model is requested to insert the calculated whitespace characters at necessary positions in the file.

The structure of blocks is usually built in such a way that it mirrors the PSI structure of the file - for example, in Java code, the top-level formatting block covers the entire file, its children cover individual classes in the file, blocks on the next level cover methods inside classes, and so on. The formatter modifies only the characters between blocks, and the tree of blocks must be built in such a way that the bottom-level blocks cover all non-whitespace characters in the file: otherwise the characters between blocks may be deleted by the formatter.

If the formatting operation does not affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built - rather, only blocks for the text range covered by the formatting operation and their parents are built.

For every block, the plugin specifies the following properties:

*  The _spacing_ (
   [`Spacing`](upsource:///platform/lang-api/src/com/intellij/formatting/Spacing.java)
   ) specifies what spaces or line breaks are inserted between the specified children of the block.
   The spacing object specifies the minimum and maximum number of spaces that must be placed between the specified child blocks, the minimum number of line breaks to place there, and whether the existing line breaks and blank lines should be preserved.
   The formatting model can also specify that the spacing between the specified blocks may not be modified by the formatter.

*  The _indent_ specifies how the block is indented relative to its parent block.
   There are different modes of indenting defined by factory methods in the Indent class.
   The most commonly used are the none indent (which means the child block is not indented), the regular indent (the child block is indented by the number of spaces specified in the **Project Code Style \| General \| Indent** setting) and the continuation indent (based on **Project Code Style \| General \| Continuation Indent** setting).
   If the formatting model does not specify an indent, the "continuation without first" mode is used, which means that the first block in a sequence of blocks with that type is not indented and the following blocks are indented with a continuation indent.

*  The _wrap_ (
   [`Wrap`](upsource:///platform/lang-api/src/com/intellij/formatting/Wrap.java)
   ) specifies whether the content of the block is wrapped to the next line.
   Wrapping is performed by inserting a line break before the block content.
   The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.

*  The _alignment_ (
   [`Alignment`](upsource:///platform/lang-api/src/com/intellij/formatting/Alignment.java)
   ) specifies which blocks should be aligned with each other.
   If two blocks with the alignment property set to the same object instance are placed in different lines, and if the second block is the first non-whitespace block in its line, the formatter inserts white spaces before the second block so that it starts from the same column as the first one.

For each of these properties, a number of special use settings exists, which are described in the JavaDoc comments for the respective classes.
See also
[`SpacingBuilder`](upsource:///platform/lang-api/src/com/intellij/formatting/SpacingBuilder.java)
which aids in building rule-based configuration.

An important special case in using the formatter is the smart indent performed when the user presses the `Enter` key in a source code file.
To determine the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block, depending on the return value of the `isIncomplete()` method for the block before the caret.
If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed, like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret; otherwise, it's called on the parent block.

**New in IntelliJ IDEA 13**:
Code formatting can be suppressed per region via [special comments](https://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

**Example**:
[Custom Language Support Tutorial: Formatter](/tutorials/custom_language_support/formatter.md)

### Code Style Settings

To specify the default indent size for the language provided by your plugin, and to allow the user to configure the tab size and indent size you need to implement the
[`FileTypeIndentOptionsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java)
interface and to register the implementation in the `com.intellij.fileTypeIndentOptionsProvider` extension point.
The return value of `createIndentOptions()` determines the default indent size.

**Example**:
[Custom Language Support Tutorial: Code Style Settings](/tutorials/custom_language_support/code_style_settings.md)

### Rearranger

**New in IntelliJ IDEA 12:**
Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin.
Rules can be refined via modifiers and name, ordering can be applied additionally.
Please see
[`Rearranger`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java)
and related for JavaDoc.
