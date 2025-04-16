<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Implementing Lexer

<link-summary>A Lexer defines how a file's contents are broken into tokens.</link-summary>

The lexer, or [lexical analyzer](https://en.wikipedia.org/wiki/Lexical_analysis), defines how a file's contents are broken into tokens.
The lexer serves as a foundation for nearly all features of custom language plugins, from basic syntax highlighting to advanced code analysis features.

The API for the lexer is defined by the [`Lexer`](%gh-ic%/platform/core-api/src/com/intellij/lexer/Lexer.java) interface.

The IDE invokes the lexer in three main contexts, and the plugin can provide different lexer implementations if needed:

*  [Syntax highlighting](syntax_highlighting_and_error_highlighting.md#lexer): The lexer is returned from the implementation of the
   [`SyntaxHighlighterFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java)
   interface which is registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.syntaxHighlighterFactory"/></include>.

*  [Building the syntax tree of a file](grammar_and_parser.md): the lexer is expected to be returned from
   [`ParserDefinition.createLexer()`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java)
   implementation registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.parserDefinition"/></include>.

*  Building the index of the words contained in the file:
   if the lexer-based words scanner implementation is used, the lexer is passed to the
   [`DefaultWordsScanner`](%gh-ic%/platform/indexing-impl/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java)
   constructor. See also [](find_usages.md).

The lexer used for syntax highlighting can be invoked incrementally to process only the file's changed part.
In contrast, lexers used in other contexts are always called to process an entire file or a complete language construction embedded in a different language file.

### Lexer State

A lexer that can be used incrementally may need to return its *state*, which means the context corresponding to each position in a file.
For example, a [Java lexer](%gh-ic%/java/java-psi-impl/src/com/intellij/lang/java/lexer/JavaLexer.java) could have separate states for top-level context, comment context, and string literal context.

An essential requirement for a syntax highlighting lexer is that its state must be represented by a single integer number returned from `Lexer.getState()`.
That state will be passed to the `Lexer.start()` method, along with the start offset of the fragment to process, when lexing is resumed from the middle of a file.
Lexers used in other contexts can always return `0` from `getState()`.

### Lexer Implementation

The easiest way to create a lexer for a custom language plugin is to use [JFlex](https://jflex.de).

Classes [`FlexLexer`](%gh-ic%/platform/core-impl/src/com/intellij/lexer/FlexLexer.java) and [`FlexAdapter`](%gh-ic%/platform/core-impl/src/com/intellij/lexer/FlexAdapter.java) adapt JFlex lexers to the IntelliJ Platform Lexer API.
A [patched version of JFlex](https://github.com/JetBrains/intellij-deps-jflex) can be used with the lexer skeleton file [`idea-flex.skeleton`](%gh-ic%/tools/lexer/idea-flex.skeleton) located in the [IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community) source to create lexers compatible with `FlexAdapter`.
The patched version of JFlex provides a new command-line option `--charat` that changes the JFlex generated code to work with the IntelliJ Platform skeleton.
Enabling `--charat` option passes the source data for lexing as a `java.lang.CharSequence` and not as an array of characters.

For developing lexers using JFlex, the [Grammar-Kit plugin](https://plugins.jetbrains.com/plugin/6606-grammar-kit) can be useful.
It provides syntax highlighting and other useful features for editing JFlex files (<path>*.flex</path>).

> Lexers, and in particular JFlex-based lexers, need to be created so that they always match the entire contents of the file, without any gaps between tokens, and generate special tokens for characters which are not valid at their location.
> Lexers must never abort prematurely because of an invalid character.
>
{style="note"}

**Examples:**
- [JFlex](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/Properties.flex) definition file for [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Lexer](lexer_and_parser_definition.md)

### Token Types

Types of tokens for lexers are defined by instances of [`IElementType`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/IElementType.java).
Many token types common for all languages are defined in the [`TokenType`](%gh-ic%/platform/core-api/src/com/intellij/psi/TokenType.java) interface.
Custom language plugins should reuse these token types wherever applicable.
For all other token types, the plugin needs to create new [`IElementType`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/IElementType.java) instances and associate with the language in which the token type is used.
The same [`IElementType`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/IElementType.java) instance should be returned every time a particular token type is encountered by the lexer.

**Example:**
[Token types](%gh-ic%/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/parsing/PropertiesTokenTypes.java) for [Properties language plugin](%gh-ic%/plugins/properties)

Groups of related types (e.g., keywords) can be defined using [`TokenSet`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/TokenSet.java).
All `TokenSet` for a language should be grouped in a dedicated `$Language$TokenSets` class for re-use.

**Example:**
[`GroovyTokenSets`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/GroovyTokenSets.java)

### Embedded Language

An important feature that can be implemented at the lexer level is mixing languages within a file, such as embedding fragments of Java code in some template language.
Suppose a language supports embedding its fragments in another language.
In that case, it needs to define the chameleon token types for different types of fragments that can be embedded, and these token types need to implement the [`ILazyParseableElementType`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/ILazyParseableElementType.java) interface.
The enclosing language's lexer needs to return the entire fragment of the embedded language as a single chameleon token, of the type defined by the embedded language.
To parse the contents of the chameleon token, the IDE will call the parser of the embedded language through a call to [`ILazyParseableElementType.parseContents()`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/ILazyParseableElementType.java).
