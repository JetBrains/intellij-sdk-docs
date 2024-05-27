<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Formatter

<link-summary>Implementing a code formatter that aligns whitespaces according to the defined set of rules, and performs non-whitespace formatting modifications.</link-summary>

<tldr>

**Product Help:** [Code style and formatting](https://www.jetbrains.com/help/idea/code-style.html)

</tldr>

The IntelliJ Platform includes a powerful framework for implementing custom language formatters.
At its core, the framework represents formatting rules by nested _blocks_ ([`Block`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Block.java)) that cover the whole file.
Each block specifies *constraints* on whitespaces, like indents, wraps, spacing, or alignments.
This allows the formatting engine to calculate the smallest number of whitespace modifications necessary to properly format a file.

## Introduction

The easiest way to understand how formatting works in practice is to use a small code example in an existing language and
use [PsiViewer](explore_api.md#31-use-internal-mode-and-psiviewer) to inspect how formatting blocks are built.
To invoke PsiViewer with the possibility of inspecting <control>Block Structure</control>,
use <ui-path>Tools | View PSI Structure...</ui-path> or <ui-path>Tools | View PSI Structure of Current File...</ui-path>.

![Formatting Blocks Structure](psi_viewer_formatting_blocks_2.png){width="720"}

The image above shows a snippet of code at the top, the PSI structure at the bottom left and the block structure at the bottom right.
Like in this example, the structure of blocks is usually built to reflect the PSI structure of the file.
I.e., there is a root block that covers the entire file and its nested children cover smaller portions like classes, functions, etc. down to
statements, identifiers, or braces.
If you compare the Psi and block structure above, similarities in the nesting become obvious.

In general, however, PSI structure and formatting model are two different things serving different purposes.
While the structure of formatting blocks and PSI are usually similar, they do not have to match one-to-one.
Additionally, it is vital to understand that the formatter modifies only characters between blocks.
Therefore, the tree of blocks must cover all non-whitespace characters the bottom-level, or otherwise, the formatter may delete the characters between blocks.
On the other hand, spacing elements should never be covered by blocks unless you want the space to be left as it is.

## Implementation

To format a file or a file fragment, plugin authors are required to take the following steps:

* Implement [`FormattingModelBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java)
  and register it as `com.intellij.lang.formatter` in the <path>plugin.xml</path>.
* The main purpose of the formatting model builder is its `createModel` method that provides a
  [`FormattingModel`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModel.java) to format the document.
  This method gets the [`FormattingContext`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingContext.java) as an argument which
  provides all necessary information to properly build formatting blocks.
* The formatting model is requested to build the structure of the file as applies to formatting, as a tree of _blocks_ (
  [`Block`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Block.java)
  ) with an associated indent, wrap, alignment, and spacing settings.
  Based on the provided tree of blocks, the formatting engine calculates the sequence of whitespace characters (spaces, tabs, and/or line breaks)
  that needs to be inserted at necessary positions in the file.

The structure of blocks is usually built so that it mirrors the PSI structure of the file – for example, in Java code, the top-level formatting block covers the entire file.
Its children cover individual classes in the file, blocks on the next level cover methods inside classes, etc.
The formatter modifies only the characters between blocks, and the tree of blocks must be built so that the bottom-level blocks cover all non-whitespace characters in the file.
Otherwise, the formatter may delete the characters between blocks.

To better understand how to build the block structure, use [PsiViewer](explore_api.md#31-use-internal-mode-and-psiviewer) and inspect formatting blocks built for an existing language.
To invoke PsiViewer with the possibility of inspecting <control>Block Structure</control>, use <ui-path>Tools | View PSI Structure...</ui-path> or <ui-path>Tools | View PSI Structure of Current File...</ui-path>:

![Formatting Blocks Structure](psi_viewer_formatting_blocks.png){width="720"}

To change the default "block name" taken from class name, return custom `Block.getDebugName()`.

If the formatting operation does not affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built.
Rather, only blocks for the text range covered by the formatting operation and their parents are built.

For every block, the plugin specifies the following properties:

* The _spacing_ ([`Spacing`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Spacing.java)) specifies what spaces or line breaks are inserted between the specified children of the block.
   The spacing object specifies the minimum and maximum number of spaces that must be placed between the specified child blocks, the minimum number of line breaks to put there, and whether the existing line breaks and blank lines should be preserved.
   The formatting model can also specify that the formatter may not modify the spacing between the specified blocks.

* The _indent_ ([`Indent`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Indent.java)) specifies how the block is indented relative to its parent block.
   There are different modes of indenting defined by factory methods in the `Indent` class.
   The most commonly used are:
  * the none indent (which means the child block is not indented)
  * the regular indent (the child block is indented by the number of spaces specified in the <control>Tabs and Indents | Indent</control> code style setting)
  * the continuation indent (based on <control>Tabs and Indents | Continuation indent</control> code style setting)

  If the formatting model does not specify an indent, the "continuation without first" mode is used.
     This default means that the first block in a sequence of blocks with that type is not indented, and the following blocks are indented with a continuation indent.

* The _wrap_ ([`Wrap`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Wrap.java)) specifies whether the content of the block is wrapped to the next line.
   Wrapping is performed by inserting a line break before the block content.
   The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.

* The _alignment_ ([`Alignment`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Alignment.java)) specifies which blocks should be aligned with each other.
   If two blocks with the alignment property set to the same object instance are placed in different lines, and if the second block is the first non-whitespace block in its line, the formatter inserts whitespaces before the second block, so that it starts from the same column as the first one.

For each of these properties, several particular use settings exist, described in the Javadoc comments for the respective classes.
See also [`SpacingBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/SpacingBuilder.java), which aids in building rule-based configuration.

An important special case in using the formatter is the smart indent performed when the user presses the `Enter` key in a source code file.
To determine the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block, depending on the return value of the `isIncomplete()` method for the block before the caret.
If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed, like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret; otherwise, it's called on the parent block.

Code formatting can be suppressed per region via [special comments](https://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

**Example**:
[Custom Language Support Tutorial: Formatter](formatter.md)

### Practical Example

A powerful code formatting framework naturally comes with several interconnected parts whose relations might be confusing initially.
Therefore, here is a more specific guide on how to implement a code formatter which gives additional explanations.

First, implement and register a `FormattingModelBuilder`.
The `createModel(formattingContext: FormattingContext)` method will do several things:

* Retrieve both global and custom code style settings by using `formattingContext.codeStyleSettings` and `settings.getCustomSettings()`.
  These will be used to determine the correct amount of whitespace for indentations, wraps, etc. when building the tree of formatting blocks.
* Create an instance of your `SpacingBuilder` using the formatting settings. How to define a simple `SpacingBuilder` is shown in a moment.
* Using the settings, the spacing builder, and the PSI node from the `formattingContext`, build the root `Block` for the `formattingContext` which does not have a parent.

After that, use `FormattingModelProvider#createFormattingModelForPsiFile()` to return the standard implementation of `FormattingModel` that uses
your custom `Block` implementation.
Note that creating a formatting model on the basis of the PSI structure like this is not the only way.
The IntelliJ Platform SDK also contains, e.g., [`DocumentBasedFormattingModel`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/DocumentBasedFormattingModel.java)
for other use cases, and we refer to navigating the implementations of the `FormattingModel` to find other usages.

In this example, a useful implementation of a custom `Block` is to base it on `ASTBlock`, because it reflects the PSI structure (and therefore is also based on the AST tree).
Each block should store the following properties:

* Its parent block, because in some situations it can be necessary to check the parent block to determine the correct properties when building its subblocks.
* The underlying `ASTNode` to implement `getNode()`, `getTextRange()`, and to access its children for building subblocks.
* The settings for the language to access, e.g., alignment settings when building subblocks.
* It should have an instance of the `SpacingBuilder` which can directly be used when implementing the `getSpacing()` method.
  That means, the `getSpacing()` method of the block simply calls the `getSpacing()` method of the `SpacingBuilder`.
* Finally, it should know its `Alignment`, `Indent` and `Wrap` necessary to implement the corresponding getter methods for these properties.

Like in all tree-like structures, each block should have a list of subblocks usually called children in a tree.
Much of the work when implementing a `Block` class goes into implementing the `getSubBlocks()` method that calculates blocks for the children of the current block's AST node.
Therefore, a common implementation is to check whether the list of subblocks was already calculated and can be returned.
If not, it uses `getNode().getChildren()` and for each `ASTNode` child that is not whitespace, it builds a subblock which is then added to the list of subblocks.

Building the subblock highly depends on the specific language.
In general, however, this code inspects the `IElementType`, checks if the node is in a specific `TokenSet` or asserts other properties to determine the correct `Alignment`,
`Indent` and `Wrap`.
Then it returns a new `Block` using the parent and its own `ASTNode` and all calculated properties.

The other two more intricate methods that need to be implemented are `getChildAttributes()` and `isIncomplete()`.
Both are used to determine what indentation to use when Enter is pressed and depend on the structure of the custom language.
As an example, think of contexts in languages like Java or C that are wrapped in curly braces.
If the `ASTNode` of the current block is such a context or container element, the `getChildAttributes()` method could return
`Indent.getNormalIndent()` because block elements in such a context are usually indented.
Similarly, the `isComplete()` method could check if for such context elements the first and last child are indeed the open and close
curly braces and return `false` if not.

Finally, the mentioned `SpacingBuilder` is an easy way to specify when to put spaces before, after, between, inside, etc. certain elements.
It should reflect all possible spacing settings for your language.
Although authors are free to choose how to implement such a spacing builder, a simple template to create one from settings could look like this:

```kotlin
private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
  val customSettings = settings.getCustomSettings(YourLanguage::class.java)
  val commonSettings = settings.getCommonSettings(YourLanguage.INSTANCE)

  val spacesBeforeEq = if (customSettings.SPACE_BEFORE_EQ) 1 else 0
  val spacesAfterEq = if (customSettings.SPACE_AFTER_EQ) 1 else 0

  return SpacingBuilder(settings, YourLanguage.INSTANCE)
    .before(YourLanguageTypes.EQ).spacing(spacesBeforeEq, spacesBeforeEq, 0, false, 0)
    .after(YourLanguageTypes.EQ).spacing(spacesAfterEq, spacesAfterEq, 0, false, 0)
}
```

## Non-Whitespace Modifications

Sometimes a plugin requires performing non-whitespace character modifications like reordering methods, changing letter cases, or adding missing braces.
The formatting framework provides extension points allowing to achieve these goals.

### Pre-Processor

Allows executing additional processing before the actual formatting is performed.
For example, it can be used to adjust the formatting range or modify the code by adding, removing, or converting elements like braces, semicolons, quotes, etc.
All the introduced changes will be handled by the main formatting step.

To register a formatting pre-processor, a plugin has to provide an implementation of [`PreFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) and register it in the `com.intellij.preFormatProcessor` extension point.

**Example:**
[`JsonTrailingCommaRemover`](%gh-ic%/json/src/com/intellij/json/formatter/JsonTrailingCommaRemover.java) removing trailing commas in JSON files

### Post-Processor

It is similar to the pre-processor but is run after the actual formatting is performed.
It can be used for adding, removing, or converting elements like braces, semicolons, quotes, changing letter-cases, etc.

To register a formatting post-processor, a plugin has to provide an implementation of [`PostFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java) and register it in the `com.intellij.postFormatProcessor` extension point.

**Example:**
[`TrailingCommaPostFormatProcessor`](%gh-ic%/plugins/kotlin/code-insight/impl-base/src/org/jetbrains/kotlin/idea/formatter/TrailingCommaPostFormatProcessor.kt) inserting trailing commas in Kotlin files

### Rearranger

Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin.
Rules can be refined via modifiers and name; ordering can be applied additionally.
Please see [`Rearranger`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) and related for Javadoc.

## Code Style Settings

To specify the default indent size for the language provided by your plugin, and to allow the user to configure the tab size and indent size, you need to implement the [`FileTypeIndentOptionsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java) interface and to register the implementation in the `com.intellij.fileTypeIndentOptionsProvider` extension point.
The return value of `createIndentOptions()` determines the default indent size.

**Example**:
[Custom Language Support Tutorial: Code Style Settings](code_style_settings.md)

## Restricting Formatting

Use [`LanguageFormattingRestriction`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/LanguageFormattingRestriction.java) to restrict (automatic) code formatting for given contexts.

## External Code Formatter

_2021.3_

Register [`AsyncDocumentFormattingService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/AsyncDocumentFormattingService.java) implementation in the [`com.intellij.formattingService`](https://jb.gg/ipe?extensions=com.intellij.formattingService) extension point to invoke external formatter instead of IDE's builtin formatter.

**Example**:
[`ShExternalFormatter`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/formatter/ShExternalFormatter.java) from _Shell Script_ plugin

Todo:

- Create macOS screenshot for PSI Viewer
- Explain relations `Block`, `ASTBlock`, `AbstractBlock`
- Relations `plugin.xml`, `FormattingModelBuilder`, `FormattingModel`, `FormattingContext`
- Also `FormattingModelWithShiftIndentInsideDocument`, `DelegatingFormattingModel`, `PsiBasedFormattingModel`, `DocumentBasedFormattingModel`
