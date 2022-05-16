[//]: # (title: Code Formatter)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform includes a powerful framework for implementing custom language formatters.
In this framework, the plugin specifies the *constraints* on the whitespaces between different syntax elements.
The formatting engine, provided by the IDE, calculates the smallest number of whitespace modifications that need to be performed on the file to make it match the constraints.

The process of formatting a file or a file fragment consists of the following main steps:

*  The _formatting model builder_ (
   [`FormattingModelBuilder`](upsource:///platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java)
   ), implemented by the plugin, provides a formatting model (
   [`FormattingModel`](upsource:///platform/code-style-api/src/com/intellij/formatting/FormattingModel.java)
   ) for the document to be formatted.

*  The formatting model is requested to build the structure of the file as applies to formatting, as a tree of _blocks_ (
   [`Block`](upsource:///platform/code-style-api/src/com/intellij/formatting/Block.java)
   ) with an associated indent, wrap, alignment, and spacing settings.

*  The formatting engine calculates the sequence of whitespace characters (spaces, tabs, and/or line breaks) that needs to be placed at every block boundary, based on the plugin's formatting model.

*  The formatting model is requested to insert the calculated whitespace characters at necessary positions in the file.

The structure of blocks is usually built so that it mirrors the PSI structure of the file – for example, in Java code, the top-level formatting block covers the entire file.
Its children cover individual classes in the file, blocks on the next level cover methods inside classes, etc.
The formatter modifies only the characters between blocks, and the tree of blocks must be built so that the bottom-level blocks cover all non-whitespace characters in the file.
Otherwise, the formatter may delete the characters between blocks.

To better understand how to build the block structure, use [PsiViewer](explore_api.md#31-use-internal-mode-and-psiviewer) and inspect formatting blocks built for an existing language.
To invoke PsiViewer with the possibility of inspecting <control>Block Structure</control>, use <menupath>Tools | View PSI Structure...</menupath> or <menupath>Tools | View PSI Structure of Current File...</menupath>:

![Formatting Blocks Structure](psi_viewer_formatting_blocks.png){width="720"}

To change the default "block name" taken from class name, return custom `Block.getDebugName()`.

If the formatting operation does not affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built.
Rather, only blocks for the text range covered by the formatting operation and their parents are built.

For every block, the plugin specifies the following properties:

* The _spacing_ ([`Spacing`](upsource:///platform/code-style-api/src/com/intellij/formatting/Spacing.java)) specifies what spaces or line breaks are inserted between the specified children of the block.
   The spacing object specifies the minimum and maximum number of spaces that must be placed between the specified child blocks, the minimum number of line breaks to put there, and whether the existing line breaks and blank lines should be preserved.
   The formatting model can also specify that the formatter may not modify the spacing between the specified blocks.

* The _indent_ ([`Indent`](upsource:///platform/code-style-api/src/com/intellij/formatting/Indent.java)) specifies how the block is indented relative to its parent block.
   There are different modes of indenting defined by factory methods in the `Indent` class.
   The most commonly used are:
  * the none indent (which means the child block is not indented)
  * the regular indent (the child block is indented by the number of spaces specified in the <control>Tabs and Indents | Indent</control> code style setting)
  * the continuation indent (based on <control>Tabs and Indents | Continuation indent</control> code style setting)

  If the formatting model does not specify an indent, the "continuation without first" mode is used.
     This default means that the first block in a sequence of blocks with that type is not indented, and the following blocks are indented with a continuation indent.

* The _wrap_ ([`Wrap`](upsource:///platform/code-style-api/src/com/intellij/formatting/Wrap.java)) specifies whether the content of the block is wrapped to the next line.
   Wrapping is performed by inserting a line break before the block content.
   The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.

* The _alignment_ ([`Alignment`](upsource:///platform/code-style-api/src/com/intellij/formatting/Alignment.java)) specifies which blocks should be aligned with each other.
   If two blocks with the alignment property set to the same object instance are placed in different lines, and if the second block is the first non-whitespace block in its line, the formatter inserts white spaces before the second block, so that it starts from the same column as the first one.

For each of these properties, several particular use settings exist, described in the JavaDoc comments for the respective classes.
See also [`SpacingBuilder`](upsource:///platform/code-style-api/src/com/intellij/formatting/SpacingBuilder.java), which aids in building rule-based configuration.

An important special case in using the formatter is the smart indent performed when the user presses the `Enter` key in a source code file.
To determine the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block, depending on the return value of the `isIncomplete()` method for the block before the caret.
If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed, like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret; otherwise, it's called on the parent block.

Code formatting can be suppressed per region via [special comments](https://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

**Example**:
[Custom Language Support Tutorial: Formatter](formatter.md)

## Non-Whitespace Modifications

Sometimes a plugin requires performing non-whitespace characters modifications like reordering methods, changing letter cases, or adding missing braces.
The formatting framework provides extension points allowing to achieve these goals.

### Pre-Processor

Allows executing additional processing before the actual formatting is performed.
For example, it can be used to adjust the formatting range or modify the code by adding, removing, or converting elements like braces, semicolons, quotes, etc.
All the introduced changes will be handled by the main formatting step.

To register a formatting pre-processor, a plugin has to provide an implementation of [`PreFormatProcessor`](upsource:///platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) and register it in the `com.intellij.preFormatProcessor` extension point.

**Example:**
[`JsonTrailingCommaRemover`](upsource:///json/src/com/intellij/json/formatter/JsonTrailingCommaRemover.java) removing trailing commas in JSON files

### Post-Processor

It's similar to the pre-processor but is run after the actual formatting is performed.
It can be used for adding, removing, or converting elements like braces, semicolons, quotes, changing letter-cases, etc.

To register a formatting post-processor, a plugin has to provide an implementation of [`PostFormatProcessor`](upsource:///platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java) and register it in the `com.intellij.postFormatProcessor` extension point.

**Example:**
[`TrailingCommaPostFormatProcessor`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/formatter/TrailingCommaPostFormatProcessor.kt) inserting trailing commas in Kotlin files

### Rearranger

Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin.
Rules can be refined via modifiers and name; ordering can be applied additionally.
Please see [`Rearranger`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) and related for JavaDoc.

## Code Style Settings

To specify the default indent size for the language provided by your plugin, and to allow the user to configure the tab size and indent size, you need to implement the [`FileTypeIndentOptionsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java) interface and to register the implementation in the `com.intellij.fileTypeIndentOptionsProvider` extension point.
The return value of `createIndentOptions()` determines the default indent size.

**Example**:
[Custom Language Support Tutorial: Code Style Settings](code_style_settings.md)

## External Code Formatter

_2021.3_

Register [`AsyncDocumentFormattingService`](upsource:///platform/code-style-api/src/com/intellij/formatting/service/AsyncDocumentFormattingService.java) implementation in extension point [`com.intellij.formattingService`](https://jb.gg/ipe?extensions=com.intellij.formattingService) to invoke external formatter instead of IDE's builtin formatter.

**Example**:
[`ShExternalFormatter`](upsource:///plugins/sh/src/com/intellij/sh/formatter/ShExternalFormatter.java) from _Shell Script_ plugin
