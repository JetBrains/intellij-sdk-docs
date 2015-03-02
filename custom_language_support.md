---
title: Custom Language Support
---

<!--
INITIAL_SOURCE TODO
-->

# {{ page.title }}

Please ask questions or suggest missing topics in [plugin development forum](http://devnet.jetbrains.com/community/idea/open_api_and_plugin_development).

## Introduction

IntelliJ Platform is a powerful platform for building development tools targeting *any* language. Most of IDE features consist of language-independent and language-specific parts, and you can support a particular feature for your language with a small amount of effort: you just need to implement the language-specific part, and the language-independent part is provided for you by the platform.

This article will explain the main concepts of the Language API and will guide you through the sequence of steps which are usually required to develop a custom language plugin. You can obtain additional information about the Language API from the JavaDoc comments for the Language API classes and from the source code of the Properties language support, which is part of the IntelliJ IDEA Community Edition source code.


## Tutorial

If you prefer a full example to the detailed description offered on this page, please check out a step-by-step tutorial how to define custom language support on example of ".properties" files: [Custom Language Support Tutorial](http://confluence.jetbrains.com/display/IntelliJIDEA/Custom+Language+Support)


## Registering a File Type

The first step in developing a custom language plugin is registering a file type the language will be associated with. The IDE normally determines the type of a file by looking at its file name.

A custom language file type is a class derived from `LanguageFileType`, which passes a `Language` implementation class to its base class constructor. To register a file type, the plugin developer provides an implementation of the `FileTypeFactory` interface, which is registered via the `com.intellij.fileTypeFactory` extension point.

[Example: LanguageFileType implementation in Properties plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesFileType.java)

To verify that the file type is registered correctly, you can implement the `LanguageFileType.getIcon()` method and verify that the correct icon is displayed for files which have the extension(s) associated with your file type.

## Implementing a Lexer

The lexer (lexical analyzer) defines how the contents of a file is broken into tokens. The lexer serves as a foundation for nearly all of the features of custom language plugins, from basic syntax highlighting to advanced code analysis features. The API for the lexer is defined by the [Lexer](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lexer/Lexer.java) interface.

The IDE invokes the lexer in three main contexts, and the plugin can provide different lexer implementations for these contexts:

* Syntax highlighting: The lexer is returned from the implementation of the `SyntaxHighlighterFactory` interface which is registed in the `com.intellij.lang.syntaxHighlighterFactory` extension point.
* Building the syntax tree of a file: the lexer is expected to be returned from `ParserDefinition.createLexer()`, and the `ParserDefinition` interface is registered in the `com.intellij.lang.parserDefinition` extension point.
* Building the index of the words contained in the file: If the lexer-based words scanner implementation is used, the lexer is passed to the `DefaultWordsScanner` constructor.

The lexer used for syntax highlighting can be invoked incrementally to process only the changed part of a file, whereas lexers used in other contexts are always called to process an entire file, or a complete language construction embedded in a file in a different language.

A lexer that can be used incrementally may need to return its *state*, which means the context corresponding to each position in a file. For example, a Java lexer could have separate states for top level context, comment context and string literal context. An important requirement for a syntax highlighting lexer is that its state must be represented by a single integer number (returned from `Lexer.getState()`). That state will be passed to the `Lexer.start()` method, along with the start offset of the fragment to process, when lexing is resumed from the middle of a file. Lexers used in other contexts can always return `0` from the `getState()` method.

The easiest way to create a lexer for a custom language plugin is to use [JFlex](http://jflex.de). Adapter classes (`FlexLexer` and `FlexAdapter`) adapt JFlex lexers to the IntelliJ Platform Lexer API. The source code of IntelliJ IDEA Community Edition includes a patched version of JFlex 1.4 (`tools/lexer/jflex-1.4`) and lexer skeleton file (`tools/lexer/idea-flex.skeleton`) which can be used for creating lexers compatible with `FlexAdapter`. The patched version of JFlex provides a new command line option `--charat` which changes the JFlex generated code so that it works with the IntelliJ Platform skeleton (which passes the source data for lexing as a `CharSequence` and not as an array of characters).


For developing lexers using JFlex, the [JFlex Support](https://plugins.jetbrains.com/plugin/?id=263) plugin can be useful. It provides syntax highlighting and other useful features for editing JFlex files. [GrammarKit plugin](https://plugins.jetbrains.com/plugin/?id=6606) also has builtin JFlex support.

Note that lexers, and in particular JFlex-based lexers, need to be created in such a way that they always match the entire contents of the file, without any gaps between tokens, and generate special tokens for characters which are not valid at their location. Lexers must never abort prematurely because of an invalid character.

[Example: Lexer definition for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/parsing/Properties.flex)


Types of tokens for lexers are defined by instances of `IElementType`. A number of token types common for all languages are defined in the `TokenType` interface; custom language plugins should reuse these token types wherever applicable. For all other token types, the plugin needs to create new `IElementType` instances and associate with the language in which the token type is used. The same `IElementType` instance should be returned every time a particular token type is encountered by the lexer.

[Example: Token types for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/parsing/PropertiesTokenTypes.java)


An important feature which can be implemented at lexer level is mixing languages within a file (for example, embedding fragments of Java code in some template language). If a language supports embedding its fragments in another language, it needs to define the chameleon token types for different types of fragments which can be embedded, and these token types need to implement the `ILazyParseableElementType` interface. The lexer of the enclosing language needs to return the entire fragment of the embedded language as a single chameleon token, of the type defined by the embedded language. To parse the contents of the chameleon token, the IDE will call the parser of the embedded language through a call to `ILazyParseableElementType.parseContents()`.

## Implementing a Parser and PSI

Parsing files in IntelliJ Platform is a two-step process. First, an abstract syntax tree (AST) is built, defining the structure of the program. AST nodes are created internally by the IDE and are represented by instances of the [ASTNode](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/ASTNode.java) class. Each AST node has an associated element type (`IElementType` instance), and the element types are defined by the language plugin. The top-level node of the AST tree for a file needs to have a special element type, implementing the `IFileElementType` interface.

The AST nodes have a direct mapping to text ranges in the underlying document (the bottom-most nodes of the AST match individual tokens returned by the lexer, and higher level nodes match multiple-token fragments). Operations performed on nodes of the AST tree (inserting, removing, reordering nodes and so on) are immediately reflected as changes to the text of the underlying document.

Second, a PSI (Program Structure Interface) tree is built on top of the AST, adding semantics and methods for manipulating specific language constructs. Nodes of the PSI tree are represented by classes implementing the `PsiElement` interface and are created by the language plugin in the `ParserDefinition.createElement()` method. The top-level node of the PSI tree for a file needs to implement the `PsiFile` interface, and is created in the `ParserDefinition.createFile()` method.

[Example: ParserDefinition for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertiesParserDefinition.java)


The lifecycle of the PSI is described in more detail in [Architectural Overview](architectural_overview.html).

The base classes for the PSI implementation (`PsiFileBase`, the base implementation of `PsiFile`, and `ASTWrapperPsiElement`, the base implementation of `PsiElement`) are provided by IntelliJ Platform. 

There is currently no ready way to reuse existing language grammars (for example, from ANTLR) for creating custom language parsers. The parsers need to be coded manually.

Custom language parser and PSI classes can be generated from grammars using [Grammar-Kit](https://plugins.jetbrains.com/plugin/?id=6606) plugin. Besides code generation it provides various features for editing grammar files: syntax highlighting, quick navigation, refactorings and more. The plugin is built using its own engine and its source code is [available](https://github.com/JetBrains/Grammar-Kit).

The language plugin provides the parser implementation as an implementation of the `PsiParser` interface, returned from `ParserDefinition.createParser()`. The parser receives an instance of the `PsiBuilder` class, which is used to get the stream of tokens from the lexer and to hold the intermediate state of the AST being built. The parser must process all tokens returned by the lexer up to the end of stream (until `PsiBuilder.getTokenType()` returns `null`), even if the tokens are not valid according to the language syntax.

[Example: PsiParser implementation for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertiesParser.java)


The parser works by setting pairs of markers (`PsiBuilder.Marker` instances) within the stream of tokens received from the lexer. Each pair of markers defines the range of lexer tokens for a single node in the AST tree. If a pair of markers is nested in another pair (starts after its start and ends before its end), it becomes the child node of the outer pair.

The element type for the marker pair (and for the AST node created from it) is specified when the end marker is set (by a call to `PsiBuilder.Marker.done()`). Also, it is possible to drop a start marker before its end marker has been set. The `drop()` method drops only a single start marker without affecting any markers added after it, and the `rollbackTo()` method drops the start marker and all markers added after it and reverts the lexer position to the start marker. These methods can be used to implement lookahead when parsing.

The method `PsiBuilder.Marker.precede()` is useful for right-to-left parsing when you don't know how many markers you need at a certain position until you read more input. For example, a binary expression a+b+c needs to be parsed as `( (a+b) + c )`. Thus, two start markers are needed at the position of the token 'a', but that is not known until the token 'c' is read. When the parser reaches the '+' token following 'b', it can call `precede()` to duplicate the start marker at 'a' position, and then put its matching end marker after 'c'.

An important feature of `PsiBuilder` is its handling of whitespace and comments. The types of tokens which are treated as whitespace or comments are defined by the methods `getWhitespaceTokens()` and `getCommentTokens()` in the `ParserDefinition` class. `PsiBuilder` automatically omits whitespace and comment tokens from the stream of tokens it passes to `PsiParser`, and adjusts the token ranges of AST nodes so that leading and trailing whitespace tokens are not included in the node.

The token set returned from `ParserDefinition.getCommentTokens()` is also used to search for TO DO items.

In order to better understand the process of building a PSI tree for a simple expression, you can refer to the following diagram:

![PsiBuilder](custom_language_support/PsiBuilder.gif)

In general, there is no single right way to implement a PSI for a custom language, and the plugin author can choose the PSI structure and set of methods which are the most convenient for the code which uses the PSI (error analysis, refactorings and so on). However, there is one base interface which needs to be used by a custom language PSI implementation in order to support features like rename and find usages. Every element which can be renamed or referenced (a class definition, a method definition and so on) needs to implement the [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) interface, with methods `getName()` and `setName()`.

A number of functions which can be used for implementing and using the PSI can be found in the `com.intellij.psi.util` package, and in particular in the `PsiUtil` and `PsiTreeUtil` classes.

A very helpful tool for debugging the PSI implementation is the [PsiViewer plugin](https://plugins.jetbrains.com/plugin/?id=227). It can show you the structure of the PSI built by your plugin, the properties of every PSI element and highlight its text range.

Please see [Indexing and PSI Stubs](indexing_and_psi_stubs.html) for advanced topics.


## Syntax Highlighting and Error Highlighting

The class used to specify how a particular range of text should be highlighted is called `TextAttributesKey`. An instance of this class is created for every distinct type of item which should be highlighted (keyword, number, string and so on). The `TextAttributesKey` defines the default attributes which are applied to items of the corresponding type (for example, keywords are bold, numbers are blue, strings are bold and green). The mapping of the `TextAttributesKey` to specific attributes used in an editor is defined by the `EditorColorsScheme` class, and can be configured by the user if the plugin provides an appropriate configuration interface. Highlighting from multiple `TextAttributeKey` items can be layered - for example, one key may define an item's boldness and another its color.

Please note new functionality about Language Defaults and support for additional color schemes as detailed in TODO Color Scheme Management in Intellij IDEA 12.1


### Lexer

The syntax and error highlighting is performed on multiple levels. The first level of syntax highlighting is based on the lexer output, and is provided through the `SyntaxHighlighter` interface. The syntax highlighter returns the `TextAttributeKey` instances for each token type which needs special highlighting. For highlighting lexer errors, the standard `TextAttributeKey` for bad characters (`HighligherColors.BAD_CHARACTER`) can be used.

[Example: SyntaxHighlighlighter implementation for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesHighlighter.java)


### Parser

The second level of error highlighting happens during parsing. If a particular sequence of tokens is invalid according to the grammar of the language, the `PsiBuilder.error()` method can be used to highlight the invalid tokens and display an error message showing why they are not valid.

### Annotator

The third level of highlighting is performed through the `Annotator` interface. A plugin can register one or more annotators in the `com.intellij.annotator` extension point, and these annotators are called during the background highlighting pass to process the elements in the PSI tree of the custom language. Annotators can analyze not only the syntax, but also the semantics using PSI, and thus can provide much more complex syntax and error highlighting logic. The annotator can also provide quick fixes to problems it detects.

When the file is changed, the annotator is called incrementally to process only changed elements in the PSI tree.

To highlight a region of text as a warning or error, the annotator calls `createErrorAnnotation()` or `createWarningAnnotation()` on the `AnnotationHolder` object passed to it, and optionally calls `registerFix()` on the returned `Annotation` object to add a quick fix for the error or warning. To apply additional syntax highlighting, the annotator can call `AnnotationHolder.createInfoAnnotation()` with an empty message and then call `Annotation.setTextAttributes()` to specify the text attributes key for the highlighting.

[Example: Annotator for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesAnnotator.java)


### External tool

Finally, if the custom language employs external tools for validating files in the language (for example, uses the Xerces library for XML schema validation), it can provide an implementation of the `ExternalAnnotator` interface and register it in `com.intellij.externalAnnotator` extension point. The `ExternalAnnotator` highlighting has the lowest priority and is invoked only after all other background processing has completed. It uses the same `AnnotationHolder` interface for converting the output of the external tool into editor highlighting.

### Color settings

The plugin can also provide a configuration interface to allow the user to configure the colors used for highlighting specific items. In order to do that, it should provide an implementation of `ColorSettingPage` and register it in the `com.intellij.colorSettingsPage` extension point.

[Example: ColorSettingsPage for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/openapi/options/colors/pages/PropertiesColorsPage.java)

The "Export to HTML" feature uses the same syntax highlighting mechanism as the editor, so it will work automatically for custom languages which provide a syntax highlighter.

## References and Resolve

One of the most important and tricky parts in the implementation of a custom language PSI is resolving references. Resolving references means the ability to go from the usage of an element (access of a variable, call of a method and so on) to the declaration of the element (the variable definition, the method declaration and so on). This is obviously needed in order to support "Go to Declaration" action (Ctrl-B and Ctrl-Click), and it is also a prerequisite for the Find Usages action, the Rename refactoring and the code completion.

All PSI elements which work as references (for which the Go to Declaration action applies) need to implement the `PsiElement.getReference()` method and to return a [PsiReference](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReference.java) implementation from that method. The `PsiReference` interface can be implemented by the same class as `PsiElement`, or by a different class. An element can also contain multiple references (for example, a string literal can contain multiple substrings which are valid full-qualified class names), in which case it can implement `PsiElement.getReferences()` and return the references as an array.

The main method of the PsiReference interface is `resolve()`, which returns the element to which the reference points, or `null` if it was not possible to resolve the reference to a valid element (for example, it points to an undefined class). A counterpart to this method is `isReferenceTo()`, which checks if the reference resolves to the specified element. The latter method can be implemented by calling `resolve()` and comparing the result with the passed PSI element, but additional optimizations (for example, performing the tree walk only if the text of the element is equal to the text of the reference) are possible.

[Example: Reference to a ResourceBundle in the Properties plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/ResourceBundleReference.java)


There's a set of interfaces which can be used as a base for implementing resolve support, namely the [PsiScopeProcessor interface](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) and the `PsiElement.processDeclarations()` method. These interfaces have a number of extra complexities which are not necessary for most custom languages (like support for substituting Java generics types), but they are required if the custom language can have references to Java code. If Java interoperability is not required, the plugin can forgo the standard interfaces and provide its own, different implementation of resolve.

The implementation of resolve based on the standard helper classes contains of the following components:

* A class implementing the `PsiScopeProcessor` interface which gathers the possible declarations for the reference and stops the resolve process when it has successfully completed. The main method which needs to be implemented is `execute()`, which is called to process every declaration encountered during the resolve, and returns `true` if the resolve needs to be continued or `false` if the declaration has been found. (The methods `getHint()` and `handleEvent()` are used for internal optimizations and can be left empty in the `PsiScopeProcessor` implementations for custom languages.)

* A function which walks the PSI tree up from the reference location until the resolve has successfully completed or until the end of the resolve scope has been reached. If the target of the reference is located in a different file, the file can be located, for example, using `FilenameIndex.getFilesByName()` (if the file name is known) or by iterating through all custom language files in the project (`iterateContent()` in the `FileIndex` interface obtained from `ProjectRootManager.getFileIndex()`).

* The individual PSI elements, on which the `processDeclarations()` method is called during the PSI tree walk. If a PSI element is a declaration, it passes itself to the `execute()` method of the `PsiScopeProcessor` passed to it. Also, if necessary according to the language scoping rules, a PSI element can pass the `PsiScopeProcessor` to its child elements.

An extension of the `PsiReference` interface, which allows a reference to resolve to multiple targets, is the [PsiPolyVariantReference](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java) interface. The targets to which the reference resolves are returned from the `multiResolve()` method. The "Go to Declaration" action for such references allows the user to choose the target to navigate to. The implementation of `multiResolve()` can be also based on `PsiScopeProcessor`, and can collect all valid targets for the reference instead of stopping when the first valid target is found.

The "Quick Definition Lookup" action is based on the same mechanism as "Go to Declaration", so it becomes automatically available for all references which can be resolved by the language plugin.

## Code Completion

There are two main types of code completion that can be provided by custom language plugins: reference completion and contributor-based completion.

Reference completion is easier to implement, but supports only the basic completion action. Contributor-based completion provides more features, supports all three completion types (basic, smart and class name) and can be used, for example, to implement keyword completion.

### Reference Completion

To fill the completion list, the IDE calls `PsiReference.getVariants()` either on the reference at the caret location or on a dummy reference that would be placed at the caret. This method needs to return an array of objects containing either strings, `PsiElement` instances or instances of the `LookupElement` class. If a `PsiElement` instance is returned in the array, the completion list shows the icon for the element.

The most common way to implement `getVariants()` is to use the same function for walking up the tree as in `PsiReference.resolve()`, and a different implementation of `PsiScopeProcessor` which collects all declarations passed to its `processDeclarations()` method and returns them as an array for filling the completion list.

### Contributor-based Completion

Implementing the [CompletionContributor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) interface gives you the greatest control over the operation of code completion for your language. Note that the JavaDoc of that class contains a detailed FAQ for implementing code completion.

The core scenario of using `CompletionContributor` consists of calling the `extend()` method and passing in the *pattern* specifying the context in which this completion variant is applicable, as well as a *completion provider* which generates the items to show in the completion list.

[Example: CompletionContributor for completing keywords in MANIFEST.MF files](https://github.com/JetBrains/intellij-plugins/blob/master/osmorc/src/org/osmorc/manifest/completion/OsgiManifestCompletionContributor.java)


Items shown in the completion list are represented by instances of the `LookupElement` interface. These instances are normally created through the `LookupElementBuilder` class. For every lookup element, you can specify the following attributes:

* Text, tail text and type text. Tail text is shown next to the main item text, is not used for prefix matching, and can be used, for example, to show the parameter list of the method. Type text is shown right-aligned in the lookup list and can be used to show the return type or containing class of a method, for example.
* Icon
* Text attributes (bold, strikeout etc.)
* Insert handler. The insert handler is a callback which is called when the item is selected, and can be used to perform additional modifications of the text (for example, to put in the parentheses for a method call)

## Find Usages

The "Find Usages" action is a multi-step process, and each step of the process requires involvement from the custom language plugin. The language plugin participates in the Find Usages process by registering an implementation of `FindUsagesProvider` in the `com.intellij.lang.findUsagesProvider` extension point, and through the PSI implementation (`PsiNamedElement` and `PsiReference` interfaces).

[Example: Implementation of FindUsagesProvider in Properties plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/findUsages/PropertiesFindUsagesProvider.java)


The steps of the Find Usages action are the following:

* Before the Find Usages action can be invoked, the IDE builds an index of words present in every file in the custom language. Using the `WordsScanner` implementation returned from `FindUsagesProvider.getWordsScanner()`, the contents of every file are loaded and passes it to the words scanner, along with the words consumer. The words scanner breaks the text into words, defines the context for each word (code, comments or literals) and passes the word to the consumer. The simplest way to implement the words scanner is to use the `DefaultWordsScanner` implementation, passing to it the sets of lexer token types which are treated as identifiers, literals and comments. The default words scanner will use the lexer to break the text into tokens, and will handle breaking the text of comment and literal tokens into individual words.

* When the user invokes the Find Usages action, the IDE locates the PSI element the references to which will be searched. The PSI element at the cursor (the direct tree parent of the token at the cursor position) must be either a `PsiNamedElement` or a `PsiReference` which resolves to a `PsiNamedElement`. The word cache will be used to search for the text returned from the `PsiNamedElement.getName()` method. Also, if the text range of the `PsiNamedElement` includes some other text besides the identifier returned from `getName()` (for example, if the `PsiNamedElement` represents a JavaScript function and its text range includes the "`function`" keyword in addition to the name of the function), the method `getTextOffset()` must be overridden for the `PsiNamedElement`, and must return the start offset of the name identifier within the text range of the element.

* Once the element is located, the IDE calls `FindUsagesProvider.canFindUsagesFor()` to ask the plugin if the Find Usages action is applicable to the specific element.

* When showing the Find Usages dialog to the user, `FindUsagesProvider.getType()` and `FindUsagesProvider.getDescriptiveName()`  are called to determine how the element should be presented to the user.

* For every file containing the searched words, the IDE builds the PSI tree and recursively descends that tree. The text of each element is broken into words and then scanned. If the element was indexed as an identifier, every word is checked to be a `PsiReference` resolving to the element the usages of which are searched. If the element was indexed as a comment or literal and the search in comments or literals is enabled, it checks if the word is equal to the name of the searched element.

* After the usages are collected, results are shown in the usages pane. The text shown for each found element is taken from the `FindUsagesProvider.getNodeText()` method.

To have the title of the found element be correctly displayed in the title of the Find Usages toolwindow, you need to provide an implementation of the `ElementDescriptionProvider` interface. The `ElementDescriptionLocation` passed to the provider in this case will be an instance of `UsageViewLongNameLocation`.

[Example: ElementDescriptionProvider for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesDescriptionProvider.java)




## Rename Refactoring

The operation of the Rename refactoring is quite similar to that of Find Usages. It uses the same rules for locating the element to be renamed, and the same index of words for locating the files which may have references to the element being renamed.

When the rename refactoring is performed, the method `PsiNamedElement.setName()` is called for the renamed element, and `PsiReference.handleElementRename()` is called for all references to the renamed element. Both of these methods perform basically the same action: replace the underlying AST node of the PSI element with the node containing the new text entered by the user. Creating a fully correct AST node from scratch is quite difficult. Thus, surprisingly, the easiest way to get the replacement node is to create a dummy file in the custom language so that it would contain the necessary node in its parse tree, build the parse tree and extract the necessary node from it.

[Example: setName() implementation for a Property](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L58)




Another interface related to the Rename refactoring is `NamesValidator`. This interface allows a plugin to check if the name entered by the user in the Rename dialog is a valid identifier (and not a keyword) according to the custom language rules. If an implementation of this interface is not provided by the plugin, Java rules for validating identifiers are used. Implementations of `NamesValidator` are registered in the `com.intellij.lang.namesValidator` extension point.

[Example: NamesValidator for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesNamesValidator.java)




Further customization of the Rename refactoring processing is possible on multiple levels. Providing a custom implementation of the `RenameHandler` interface allows you to entirely replace the UI and workflow of the rename refactoring, and also to support renaming something which is not a `PsiElement` at all.

[Example: RenameHandler for renaming a resource bundle](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/rename/ResourceBundleFromEditorRenameHandler.java)




If you're fine with the standard UI but need to extend the default logic of renaming, you can provide an implementation of the `RenamePsiElementProcessor` interface. This allows you to:
* Rename an element different from the one on which the action was invoked (a super method, for example)
* Rename multiple elements at once (if their names are linked according to the logic of your language)
* Check for name conflicts (existing names etc.)
* Customize how search for code references or text references is performed
* etc.

[Example: RenamePsiElementProcesssor for renaming a property](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/rename/RenamePropertyProcessor.java)




## Safe Delete Refactoring

The Safe Delete refactoring also builds on the same Find Usages framework as Rename. In addition to that, in order to support Safe Delete, a plugin needs to implement two things:

* The `RefactoringSupportProvider` interface, registered in the `com.intellij.lang.refactoringSupport` extension point, and the `isSafeDeleteAvailable()` method, which checks if the Safe Delete refactoring is available for a specific PSI element;

* The `PsiElement.delete()` method for the PsiElement subclasses for which Safe Delete is available. Deleting PSI elements is implemented by deleting the underlying AST nodes from the AST tree (which, in turn, causes the text ranges corresponding to the AST nodes to be deleted from the document).

[Example: delete() implementation for a Property](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L363)




If needed, it's possible to further customize how Safe Delete is performed for a particular type of element (how references are searched, etc). This is done by implementing the `SafeDeleteProcessorDelegate` interface.

[Example: SafeDeleteProcessorDelegate implementation for a .properties file](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/PropertiesFilesSafeDeleteProcessor.java)




## Code Formatter

The IntelliJ Platform includes a powerful framework for implementing custom language formatters. In this framework, the plugin specifies the *constraints* on the spacing between different syntax elements, and the formatting engine, provided by the IDE, calculates the smallest number of whitespace modifications that need to be performed on the file to make it match the constraints.

The process of formatting a file or a file fragment consists of the following main steps:

* The _formatting model builder_ (`FormattingModelBuilder`), implemented by the plugin, provides a formatting model (`FormattingModel`) for the document to be formatted

* The formatting model is requested to build the structure of the file as applies to formatting, as a tree of _blocks_ (`Block`) with associated indent, wrap, alignment and spacing settings.

* The formatting engine calculates the sequence of whitespace characters (spaces, tabs and/or line breaks) that needs to be placed at every block boundary, based on the formatting model provided by the plugin.

* The formatting model is requested to insert the calculated whitespace characters at necessary positions in the file.

The structure of blocks is usually built in such a way that it mirrors the PSI structure of the file - for example, in Java code, the top-level formatting block covers the entire file, its children cover individual classes in the file, blocks on the next level cover methods inside classes, and so on. The formatter modifies only the characters between blocks, and the tree of blocks must be built in such a way that the bottom-level blocks cover all non-whitespace characters in the file: otherwise the characters between blocks may be deleted by the formatter.

If the formatting operation does not affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built - rather, only blocks for the text range covered by the formatting operation and their parents are built.

For every block, the plugin specifies the following properties:

* The _spacing_ (`Spacing`) specifies what spaces or line breaks are inserted between the specified children of the block. The spacing object specifies the minimum and maximum number of spaces that must be placed between the specified child blocks, the minimum number of line breaks to place there, and whether the existing line breaks and blank lines should be preserved. The formatting model can also specify that the spacing between the specified blocks may not be modified by the formatter.

* The _indent_ specifies how the block is indented relative to its parent block. There are different modes of indenting defined by factory methods in the Indent class. The most commonly used are the none indent (which means the child block is not indented), the regular indent (the child block is indented by the number of spaces specified in the "Project Code Style \| General \| Indent" setting) and the continuation indent (based on "Project Code Style \| General \| Continuation Indent" setting). If the formatting model does not specify an indent, the "continuation without first" mode is used, which means that the first block in a sequence of blocks with that type is not indented and the following blocks are indented with a continuation indent.

* The _wrap_ (`Wrap`) specifies whether the content of the block is wrapped to the next line. Wrapping is performed by inserting a line break before the block content. The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.

* The _alignment_ (`Alignment`) specifies which blocks should be aligned with each other. If two blocks with the alignment property set to the same object instance are placed in different lines, and if the second block is the first non-whitespace block in its line, the formatter inserts white spaces before the second block so that it starts from the same column as the first one.

For each of these properties, a number of special use settings exists, which are described in the JavaDoc comments for the respective classes.
See also `SpacingBuilder` which aids in building rule-based configuration.

An important special case in using the formatter is the smart indent performed when the user presses the Enter key in a source code file. To determine the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block, depending on the return value of the `isIncomplete()` method for the block before the caret. If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed, like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret; otherwise, it's called on the parent block.

New in 13: code formatting can be suppressed per region via [special comments](http://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

### Code Style Settings

To specify the default indent size for the language provided by your plugin, and to allow the user to configure the tab size and indent size you need to implement the `FileTypeIndentOptionsProvider` interface and to register the implementation in the `fileTypeIndentOptionsProvider` extension point. The return value of `createIndentOptions()` determines the default indent size.

### Rearranger

New in 12

Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin. Rules can be refined via modifiers and name, ordering can be applied additionally. Please see `com.intellij.psi.codeStyle.arrangement.Rearranger` and related for JavaDoc.

## Code Inspections and Intentions

The code inspections for custom languages use the same API as all other code inspections, based on the `LocalInspectionTool` class.

The functionality of `LocalInspectionTool` partially duplicates that of `Annotator`. The main differences are that `LocalInspectionTool` supports batch analysis of code (through the Analyze \| Inspect Code... action), the possibility to turn off the inspection (globally or by suppressing them on various levels) and to configure the inspection options. If none of that is required and the analysis only needs to run in the active editor, `Annotator` provides better performance (because of its support for incremental analysis) and more flexibility for highlighting errors.

[Example: A simple inspection for properties files](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/codeInspection/TrailingSpacesInPropertyInspection.java)




The code intentions for custom languages also use the regular API for intentions. The intention classes need to implement the `IntentionAction` interface and to be registered using the `<intentionAction>` bean in your `plugin.xml`.

[Example: A simple intention action for Groovy](https://github.com/JetBrains/intellij-community/blob/master/plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java)




## Structure View

The Structure View implementation used for a specific file type can be customized on many levels. If a custom language plugin provides an implementation of the `StructureView` interface, it can completely replace the standard structure view implementation with a custom user interface component. However, for most languages this is not necessary, and the standard Structure View implementation provided by IntelliJ Platform can be reused.

The starting point for the structure view is the `PsiStructureViewFactory` interface, which is registered in the `com.intellij.lang.psiStructureViewFactory` extension point.

[Example: PsiStructureViewFactory for .properties files](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/structureView/PropertiesStructureViewBuilderFactory.java)




To reuse the IntelliJ Platform implementation of the Structure View, the plugin returns a `TreeBasedStructureViewBuilder` from its `PsiStructureViewFactory.getStructureViewBuilder()` method. As the model for the builder, the plugin can specify a subclass of `TextEditorBasedStructureViewModel`, and by overriding methods of this subclass it customizes the structure view for a specific language.

[Example: StructureViewModel for .properties files](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesFileStructureViewModel.java)




The main method to override is `getRoot()`, which returns the instance of a class implementing the `StructureViewTreeElement` interface. There exists no  standard implementation of this interface, so a plugin will need to implement it completely.

The structure view tree is usually built as a partial mirror of the PSI tree. In the implementation of `StructureViewTreeElement.getChildren()`, the plugin can specify which of the child elements of a specific PSI tree node need to be represented as elements in the structure view. Another important method is `getPresentation()`, which can be used to customize the text, attributes and icon used to represent an element in the structure view.

The implementation of `StructureViewTreeElement.getChildren()` needs to be matched by `TextEditorBasedStructureViewModel.getSuitableClasses()`. The latter method returns an array of `PsiElement`\-derived classes which can be shown as structure view elements, and is used to select the Structure View item matching the cursor position when the structure view is first opened or when the "Autoscroll from source" option is used.

[Example: StructureViewElement for a property](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesStructureViewElement.java)




## Surround With

In order to support the "Code \| Surround With..." action, the plugin needs to register one or more implementations of the `SurroundDescriptor` interface in the `com.intellij.lang.surroundDescriptor` extension point. Each of the surround descriptors defines a possible type of code fragment which can be surrounded - for example, one surround descriptor can handle surrounding expressions, and another can handle statements. Each surround descriptor, in turn, contains an array of `Surrounder` objects, defining specific templates which can be used for surrounding the selected code fragment (for example, "Surround With if", "Surround With for" and so on).

When the "Surround With..." action is invoked, the IDE queries all surround descriptors for the language until it finds one that returns a non-empty array from its `getElementsToSurround()` method. Then it calls the `Surrounder.isApplicable()` method for each surrounder in that descriptor to check if the specific template is applicable in the current context. Once the user selects a specific surrounder from the popup menu, the `Surrounder.surroundElements()` method is used to execute the surround action.

## Go to Class and Go to Symbol

A custom language plugin can provide its own items to be included in the lists shown when the user chooses the "Go to \| Class..." or "Go to \| Symbol..." action. In order to do so, the plugin must provide implementations for the `ChooseByNameContributor` interface (separate implementations need to be provided for "Go to Class" and "Go to Symbol"), and register them in the `com.intellij.gotoClassContributor` and `com.intellij.gotoSymbolContributor` extension points.

Each contributor needs to be able to return a complete list of names to show in the list for a specified project, which will then be filtered by the IDE according to the text typed by the user in the dialog. For each name in that list, the contributor needs to provide a list of `NavigationItem` instances (typically `PsiElement`), which specify the destinations to jump to when a specific name is selected from the list.

## Documentation

To provide different kinds of documentation support (tooltips on Ctrl-hover, quick documentation popup etc.), the plugin needs to provide an implementation of the `DocumentationProvider` interface and register it in the `lang.documentationProvider` extension point. A standard base class for such implementations is available (`AbstractDocumentationProvider`).

[Example: DocumentationProvider for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java)




The `getQuickNavigateInfo()` method returns the text to be displayed when the user holds the mouse over an element with Ctrl pressed.


## Additional Minor Features

In order to implement *brace matching*, once the syntax highlighting lexer has been implemented, all that is required is to implement the `PairedBraceMatcher` interface and to return an array of brace pairs (`BracePair`) for the language. Each brace pair specifies the characters for the opening and closing braces and the lexer token types for these characters. (In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair. The IDE will match such braces, but the highlighting for such braces will not be fully correct.)

Certain types of braces can be marked as structural. Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of other types between them, and an opening non-structural braces is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.

The *code folding* is controlled by the plugin through the `FoldingBuilder` interface. The interface returns the list of text ranges which are foldable (as an array of `FoldingDescriptor` objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

The *Comment Code* feature is controlled through the `Commenter` interface. The interface can return the prefix for the line comment, and the prefix and suffix for the block comment, if such features are supported by the language.

[Example: Commenter for Properties language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java)


To support smart/semantic *Join Lines* see `com.intellij.codeInsight.editorActions.JoinLinesHandlerDelegate`.

*Smart Enter* (e.g. autocomplete missing semicolon/parentheses) can be provided via `com.intellij.codeInsight.editorActions.smartEnter.SmartEnterProcessor`.

*Naming suggestions* for Rename Refactoring can be provided via `com.intellij.refactoring.rename.NameSuggestionProvider`.

*Semantic highlight usages* (e.g. exit points) can be achieved using `com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactory`.

*View\|Parameter Info* is provided via `com.intellij.lang.parameterInfo.ParameterInfoHandler` (extension point `codeInsight.parameterInfo`).

The *To Do view* is supported automatically if the plugin provides a correct implementation of the `ParserDefinition.getCommentTokens()` method.

The *View \| Context Info* feature is supported for custom languages since IntelliJ IDEA 10.5. In order for it to work, you need to have a structure view implementation based on a `TreeBasedStructureViewBuilder`, and additionally to provide an implementation of `DeclarationRangeHandler` for your language and to register it in the `declarationRangeHandler` extension point.

*Spellchecking* can be provided via EP `spellchecker.support` (`SpellcheckingStrategy`) where you can return `Tokenizer` to use, possibly depending on the passed in `PsiElement` (or `EMPTY_TOKENIZER` for no spellchecking).

New in 13: user-configurable *reference injections* can be provided via `referenceInjector` extension point (`ReferenceInjector`) (IntelliLang plugin required).
