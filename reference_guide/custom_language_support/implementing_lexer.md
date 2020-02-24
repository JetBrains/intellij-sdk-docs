---
title: Implementing Lexer
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The lexer, or
[lexical analyzer](https://en.wikipedia.org/wiki/Lexical_analysis),
defines how the contents of a file is broken into tokens.
The lexer serves as a foundation for nearly all of the features of custom language plugins, from basic syntax highlighting to advanced code analysis features.
The API for the lexer is defined by the
[`Lexer`](upsource:///platform/core-api/src/com/intellij/lexer/Lexer.java) interface.

The IDE invokes the lexer in three main contexts, and the plugin can provide different lexer implementations for these contexts:

*  Syntax highlighting: The lexer is returned from the implementation of the
   [`SyntaxHighlighterFactory`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java)
   interface which is registered in the `com.intellij.lang.syntaxHighlighterFactory` extension point.

*  Building the syntax tree of a file: the lexer is expected to be returned from
   [`ParserDefinition.createLexer()`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java),
   and the
   [`ParserDefinition`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java)
   interface is registered in the `com.intellij.lang.parserDefinition` extension point.

*  Building the index of the words contained in the file:
   if the lexer-based words scanner implementation is used, the lexer is passed to the
   [`DefaultWordsScanner`](upsource:///platform/indexing-api/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java)
   constructor.

The lexer used for syntax highlighting can be invoked incrementally to process only the changed part of a file, whereas lexers used in other contexts are always called to process an entire file, or a complete language construction embedded in a file in a different language.

A lexer that can be used incrementally may need to return its *state*, which means the context corresponding to each position in a file.
For example, a
[Java lexer](upsource:///java/java-psi-impl/src/com/intellij/lang/java/lexer/JavaLexer.java)
could have separate states for top level context, comment context and string literal context.
An important requirement for a syntax highlighting lexer is that its state must be represented by a single integer number returned from
[`Lexer.getState()`](upsource:///platform/core-api/src/com/intellij/lexer/Lexer.java).
That state will be passed to the
[`Lexer.start()`](upsource:///platform/core-api/src/com/intellij/lexer/Lexer.java)
method, along with the start offset of the fragment to process, when lexing is resumed from the middle of a file.
Lexers used in other contexts can always return `0` from the `getState()` method.

The easiest way to create a lexer for a custom language plugin is to use [JFlex](https://jflex.de).
Classes
[`FlexLexer`](upsource:///platform/core-api/src/com/intellij/lexer/FlexLexer.java)
and
[`FlexAdapter`](upsource:///platform/core-api/src/com/intellij/lexer/FlexAdapter.java)
adapt JFlex lexers to the IntelliJ Platform Lexer API.
We have a
[patched version of JFlex](https://github.com/JetBrains/intellij-deps-jflex)
that can be used with the lexer skeleton file located at *tools/lexer/idea-flex.skeleton* in the
[IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community)
source to create lexers compatible with
[`FlexAdapter`](upsource:///platform/core-api/src/com/intellij/lexer/FlexAdapter.java).
The patched version of JFlex provides a new command line option `--charat` which changes the JFlex generated code so that it works with the IntelliJ Platform skeleton.
Enabling `--charat` option passes the source data for lexing as a
[`CharSequence`](https://docs.oracle.com/javase/8/docs/api/java/lang/CharSequence.html)
and not as an array of characters.


For developing lexers using JFlex, the [GrammarKit plugin](https://plugins.jetbrains.com/plugin/6606-grammar-kit) can be useful.
It provides syntax highlighting and other useful features for editing JFlex files.

> **NOTE** Lexers, and in particular JFlex-based lexers, need to be created in such a way that they always match the entire contents of the file, without any gaps between tokens, and generate special tokens for characters which are not valid at their location.
Lexers must never abort prematurely because of an invalid character.

**Example**:
- [`Lexer`](upsource:///plugins/properties/src/com/intellij/lang/properties/parsing/Properties.flex)
definition for
[Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Lexer](/tutorials/custom_language_support/lexer_and_parser_definition.md)

Types of tokens for lexers are defined by instances of
[`IElementType`](upsource:///platform/core-api/src/com/intellij/psi/tree/IElementType.java).
A number of token types common for all languages are defined in the
[`TokenType`](upsource:///platform/core-api/src/com/intellij/psi/TokenType.java)
interface.
Custom language plugins should reuse these token types wherever applicable.
For all other token types, the plugin needs to create new
[`IElementType`](upsource:///platform/core-api/src/com/intellij/psi/tree/IElementType.java)
instances and associate with the language in which the token type is used.
The same
[`IElementType`](upsource:///platform/core-api/src/com/intellij/psi/tree/IElementType.java)
instance should be returned every time a particular token type is encountered by the lexer.

**Example:**
[Token types](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/parsing/PropertiesTokenTypes.java)
for
[Properties language plugin](upsource:///plugins/properties)


An important feature which can be implemented at lexer level is mixing languages within a file, for example, embedding fragments of Java code in some template language.
If a language supports embedding its fragments in another language, it needs to define the chameleon token types for different types of fragments which can be embedded, and these token types need to implement the
[`ILazyParseableElementType`](upsource:///platform/core-api/src/com/intellij/psi/tree/ILazyParseableElementType.java)
interface.
The lexer of the enclosing language needs to return the entire fragment of the embedded language as a single chameleon token, of the type defined by the embedded language.
To parse the contents of the chameleon token, the IDE will call the parser of the embedded language through a call to
[`ILazyParseableElementType.parseContents()`](upsource:///platform/core-api/src/com/intellij/psi/tree/ILazyParseableElementType.java).
