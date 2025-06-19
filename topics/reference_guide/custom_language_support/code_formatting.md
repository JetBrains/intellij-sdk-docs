<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

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
use the [built-in PSI viewer](explore_api.md#internalMode) to inspect how formatting blocks are built.
To invoke the builtin PSI viewer with the possibility of inspecting <control>Block Structure</control>,
use <ui-path>Tools | View PSI Structure...</ui-path> or <ui-path>Tools | View PSI Structure of Current File...</ui-path>.

![Formatting Blocks Structure](psi_viewer_formatting_blocks.png){width="706"}

The image above shows a snippet of code at the top, the PSI structure at the bottom left and the block structure at the bottom right.
Like in this example, the structure of blocks is usually built to reflect the PSI structure of the file.
In other words, there is a root block that covers the entire file and its nested children cover smaller portions like classes, functions, etc. down to
statements, identifiers, or braces.
Comparing the PSI and block structure above reveals obvious similarities in the nesting.

In general, however, PSI structure and formatting model are two different things serving different purposes.
While the structure of formatting blocks and PSI are usually similar, they do not have to match one-to-one.
Additionally, it is vital to understand that the formatter modifies only characters between blocks.
Therefore, the tree of blocks must cover all non-whitespace characters the bottom-level, or otherwise, the formatter may delete the characters between blocks.
On the other hand, spacing elements should never be covered by blocks unless the space is intended to be left as it is.

## Implementation

To format a file or a file fragment, the following steps are required:

* Implement [`FormattingModelBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java)
  and register it as <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.formatter"/></include> in the <path>plugin.xml</path>.
* The main purpose of the formatting model builder is its `createModel()` method that must provide a
  [`FormattingModel`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModel.java) necessary for the framework to format the document.
  As an argument, it receives a [`FormattingContext`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingContext.java) which
  provides text range, PSI element, and other information required for formatting.
* The formatting model builds the tree of _blocks_ ([`Block`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Block.java)) for the file where each block has associated indent, wrap, alignment, and spacing settings.
  Based on the provided tree of blocks, the formatting engine calculates the sequence of whitespace characters (spaces, tabs, and/or line breaks)
  that needs to be adjusted at necessary positions in the file.

### `FormattingModelBuilder` and `FormattingModel`

When implementing a `FormattingModelBuilder`, it is common to use the `createModel()` method and its `FormattingContext` argument to set the stage
for building the formatting blocks with the `FormattingModel`.
Typically, the following steps are part of this preparation:

* Retrieve both global and custom code style settings.
  Use the `codeStyleSettings` property of the `FormattingContext` to retrieve the settings which in turn provide access to custom settings through `getCustomSettings()`.
  These are commonly passed along and will be used to determine the correct amount of whitespace for indentations, wraps, etc. when building the tree of formatting blocks.
* Create an instance of [`SpacingBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/SpacingBuilder.java) using the formatting settings.
* Build the root `Block` that covers the whole file using the settings, the spacing builder, and the PSI node from the formatting context.
* The root `Block` needs all required information for building its subblocks recursively and is used in the `FormattingModel` returned from `createModel()`

The IntelliJ Platform already provides concrete implementations of `FormattingModel` that should be used instead of implementing a custom one.
Formatters for custom (programming) languages are usually built so that they mirror the PSI structure of the file.
Although not required, this approach is reasonable when thinking about, for example, Java code where the top-level formatting block covers the entire file,
its children cover individual classes, blocks on the next level cover methods inside classes, and so on.
For these cases, plugins should create a [`PsiBasedFormattingModel`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/PsiBasedFormattingModel.java) by using
[`FormattingModelProvider.createFormattingModelForPsiFile()`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelProvider.java).

Another implementation of `FormattingModel` is [`DocumentBasedFormattingModel`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/DocumentBasedFormattingModel.java).
However, in most cases, the `PsiBasedFormattingModel` should meet the requirements of custom language plugins.

### Building the `Block` Tree

Instead of implementing the `Block` interface, [`AbstractBlock`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/common/AbstractBlock.java)
should be used as a base class which provides useful default implementations.
Although the block implementation of a plugin is specific to the custom language, for the implementation of `AbstractBlock` some general remarks can be given.
It is common to store an instance of the `SpacingBuilder` which can directly be used when implementing the `getSpacing()` method
by calling the `getSpacing()` method of the `SpacingBuilder`.

Much of the work when implementing `AbstractBlock` goes into implementing `buildChildren()` that calculates blocks for the children of the current block's AST node.
Use `getNode().getChildren()` to retrieve the AST node's children of the current block,
and or each child that is not whitespace, build a subblock which is then added to the list of subblocks.
While the exact method of building a subblock highly depends on the specific language,
in general, the code for determining the correct `Alignment`, `Indent` and `Wrap` of a block inspects `IElementType`,
checks if the node is in a specific `TokenSet` or asserts other properties.

The other two more intricate methods that need to be implemented are `getChildAttributes()` and `isIncomplete()`.
Both are important when determining what indentation to use when `Enter` is pressed.
To calculate the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block.
This depends on the return value of the `isIncomplete()` method for the block before the caret.
If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed,
like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret;
otherwise, it's called on the parent block.

As an example, think of _contexts_ (also called _blocks_) in languages like Java or C that are wrapped in curly braces.
If the `ASTNode` of the current block is such a context or container element, the `getChildAttributes()` method could return
`Indent.getNormalIndent()` to indent block elements in such a context.
Similarly, the `isComplete()` method could check if for such context elements the first and last child are indeed the open and close
curly braces and return `false` if not.

If the formatting operation doesn't affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built.
Rather, only blocks for the text range covered by the formatting operation and their parents are built.
Also note that code formatting can be suppressed per region via [special comments](https://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

For every block, the plugin specifies the following properties for which several particular use settings exist.
These settings have a detailed description in the Javadoc comments for the respective classes.

#### Indent

[`Indent`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Indent.java) specifies how the block is indented relative to its parent block.
It provides various factory methods to create different types of indents based on specific formatting settings.
There are different modes of indenting defined by factory methods, and the most commonly used are:

* The none indent (`getNoneIndent()`) means the child block is not indented.
* The regular indent (`getNormalIndent()`) indents the child block by the number of spaces specified in the <control>Tabs and Indents | Indent</control> code style settings.
* The continuation indent (`getContinuationIndent()`) is based on <control>Tabs and Indents | Continuation indent</control> code style settings, typically used for multi-line statements.

If not specified, the default "continuation without first" indent mode is used, meaning the first block is not indented, but later blocks are.
The class also allows for configuring indents to be relative to their direct parent block or to enforce parent indents on children starting on a new line.
This is useful in complex formatting scenarios, such as aligning blocks within a method call or ensuring consistent indentation in nested structures.
`Indent` also includes methods to create indents with a specific number of spaces and options to control their behavior relative to parent blocks.

#### Spacing

[`Spacing`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Spacing.java) indicates what spaces or line breaks are inserted between the specified children of a block.
The spacing object specifies the minimum and maximum number of spaces that must be placed between the child blocks.
It also specifies the minimum number of line breaks to include and whether the existing line breaks and blank lines should be preserved.
The formatting model can also specify that the formatter may not modify the spacing between the specified blocks.

[`SpacingBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/SpacingBuilder.java) helps to build a rule-based configuration for spacing and is an easy way to specify when to put spaces before, after, between, or inside certain elements.
The rules typically reflect all possible spacing settings for a language.
An example on how to implement such a spacing builder can be found in the
[Custom Language Support Tutorial: Formatter](formatter.md#define-a-formatting-model-builder).


#### Wrap

The _wrap_ ([`Wrap`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Wrap.java)) specifies whether the content of the block is wrapped to the next line.
Wrapping is performed by inserting a line break before the block content.
The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.


#### Alignment

[`Alignment`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Alignment.java) is designed to specify which blocks should be aligned with each other in a formatting model.
Blocks that return the same `Alignment` object instance from the `getAlignment()` method will be aligned together.
If two blocks with the same alignment are on different lines and the second block is the first non-whitespace block on its line,
the formatter inserts whitespaces before the second block to align it with the first block.

The `Alignment` includes the `Anchor` enum with two values, `LEFT` and `RIGHT`, determining how the code blocks are aligned.
The default alignment is created with the left anchor.
To create an alignment instance, the class provides several static methods:

- `createAlignment()`: Creates an alignment with default settings (backward shift not allowed, left anchor).
- `createAlignment(boolean allowBackwardShift)`: Creates an alignment specifying whether backward shift is allowed, with the left anchor.
- `createAlignment(boolean allowBackwardShift, Anchor anchor)`: Creates an alignment with the specified backward shift setting and anchor.

Backward shift allows a former-aligned element to be shifted right to align with a later element.
For example, in the code:

```
int start  = 1;
int finish = 2;
```

the `=` in `int start = 1` can be shifted right to align with the `=` in `int finish = 2` if backward shift is allowed.

Additionally, the `createChildAlignment()` method allows creating an alignment where blocks are aligned based on a base alignment.
This is useful for cases where nested alignments are needed, such as aligning a ternary operator with its condition.

### Examples

- [Custom Language Support Tutorial: Formatter](formatter.md)
- [`JsonFormattingBuilderModel`](%gh-ic%/json/split/src/com/intellij/json/formatter/JsonFormattingBuilderModel.java) as an example that uses a `PsiBasedFormattingModel`.
- [`MarkdownFormattingModelBuilder`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/lang/formatter/MarkdownFormattingModelBuilder.kt) as an example that uses a `DocumentBasedFormattingModel`.

### Further Tips

- To change the default "block name" taken from class name, return a custom `Block.getDebugName()`.
- The formatter in the IntelliJ Platform SDK only controls spacing, including indentation, between adjacent leaf blocks.
  Therefore, the first leaf block won't get indentation.
  If this is needed, use a [`PostFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java)
  ([see also below](#post-processor)) to add the required indentation at the beginning
  ([see forum post with example](https://intellij-support.jetbrains.com/hc/en-us/community/posts/7982433627666-Formatting-indents-make-me-go-nuts)).
- An indent is ignored if a corresponding block doesn't start with a new line.
  To enforce the indent in this case using `enforceIndentToChildren` parameter in the factory method `Indent#getIndent(Intent.Type.NORMAL, false, true)`
  ([see forum post with example](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010627319-Formatting-Block-to-indent-relative-to-where-its-parent-would-be)).


## Non-Whitespace Modifications

Sometimes a plugin requires performing non-whitespace character modifications like reordering methods, changing letter cases, or adding missing braces.
The formatting framework provides extension points allowing to achieve these goals.

### Pre-Processor

Allows executing additional processing before the actual formatting is performed.
For example, it can be used to adjust the formatting range or modify the code by adding, removing, or converting elements like braces, semicolons, quotes, etc.
All the introduced changes will be handled by the main formatting step.

To register a formatting pre-processor, a plugin has to provide an implementation of [`PreFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java)
and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.preFormatProcessor"/></include>.

**Example:**
[`JsonTrailingCommaRemover`](%gh-ic%/json/split/src/com/intellij/json/formatter/JsonTrailingCommaRemover.java) removing trailing commas in JSON files

### Post-Processor

It is similar to the pre-processor but is run after the actual formatting is performed.
It can be used for adding, removing, or converting elements like braces, semicolons, quotes, changing letter-cases, etc.

To register a formatting post-processor, a plugin has to provide an implementation of [`PostFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java)
and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.postFormatProcessor"/></include>.

**Example:**
[`TrailingCommaPostFormatProcessor`](%gh-ic%/plugins/kotlin/code-insight/impl-base/src/org/jetbrains/kotlin/idea/formatter/TrailingCommaPostFormatProcessor.kt) inserting trailing commas in Kotlin files

### Rearranger

Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin.
Rules can be refined via modifiers and name; ordering can be applied additionally.
See [`Rearranger`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) and related for Javadoc.

## Code Style Settings

To set the default indent size for a plugin's language and allow user configuration of tab and indent sizes,
implement the [`FileTypeIndentOptionsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java)
interface and register it at the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileTypeIndentOptionsProvider"/></include>.
The return value of `createIndentOptions()` sets the default indent size.


**Example:**
[Custom Language Support Tutorial: Code Style Settings](code_style_settings.md)

## Restricting Formatting

Use [`LanguageFormattingRestriction`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/LanguageFormattingRestriction.java) to restrict (automatic) code formatting for given contexts.

## External Code Formatter
<primary-label ref="2021.3"/>

Register [`AsyncDocumentFormattingService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/AsyncDocumentFormattingService.java) implementation
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.formattingService"/></include> to invoke external formatter instead of IDE's builtin formatter.

**Example:**
[`ShExternalFormatter`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/formatter/ShExternalFormatter.java) from _Shell Script_ plugin
