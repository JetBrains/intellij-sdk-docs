---
title: Additional Minor Features
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A number of minor features are listed in the following format:

_EP: `fully.qualified.extensionPointName`_ - Extension Point Name (must be specified in `plugin.xml`)

_`com.extensionPoint.class`_ _description text_ - Extension Point class/interface to provide functionality

_- Sample 1_ - Sample implementation


### Brace Matching
EP: `com.intellij.lang.braceMatcher`

[`PairedBraceMatcher`](upsource:///platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java)
Returns an array of brace pairs (
[`BracePair`](upsource:///platform/analysis-api/src/com/intellij/lang/BracePair.java)
) specifying the characters for the opening and closing braces and the lexer token types for these characters.
(In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair.
The IDE will match such braces, but the highlighting for such braces will not be fully correct.)

Certain types of braces can be marked as structural.
Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of other types between them.
An opening non-structural brace is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.


### Comment Code
EP: `com.intellij.lang.commenter`

[`Commenter`](upsource:///platform/core-api/src/com/intellij/lang/Commenter.java) 
Returns the prefix for the line comment, and the prefix and suffix for the block comment, if supported by the language.

- [`Commenter`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java)
for [Properties language plugin](upsource:///plugins/properties/)
- [Custom Language Support Tutorial: Commenter](/tutorials/custom_language_support/commenter.md)


### Code Folding
EP: `com.intellij.lang.foldingBuilder`

[`FoldingBuilder`](upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java)
Returns the list of text ranges that are foldable (as an array of
[`FoldingDescriptor`](upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java)
objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

- [Custom Language Support Tutorial: Folding Builder](/tutorials/custom_language_support/folding_builder.md)


### Join Lines
EP: `com.intellij.joinLinesHandler`

[`JoinLinesHandlerDelegate`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java)
allows extending support smart/semantic *Edit \| Join Lines* (e.g., String literal split on multiple lines).


### Smart Enter
EP: `com.intellij.lang.smartEnterProcessor`

[`SmartEnterProcessor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java)
handles *Edit \| Complete Statement* (e.g., autocomplete missing semicolon/parentheses).

              
### Naming Suggestions
EP: `com.intellij.nameSuggestionProvider`

[`NameSuggestionProvider`](upsource:///platform/lang-api/src/com/intellij/refactoring/rename/NameSuggestionProvider.java).
provides name suggestions for the given element, e.g., for Rename refactoring.


### Semantic Highlight Usages
EP: `com.intellij.highlightUsagesHandlerFactory`

[`HighlightUsagesHandlerFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java)
allows highlighting e.g. Exit Points or Exceptions.

### Parameter Info
EP: `com.intellij.codeInsight.parameterInfo`

[`ParameterInfoHandler`](upsource:///platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java)
provides support for *View \| Parameter Info*.


### To Do View
EP: n/a

[`ParserDefinition.getCommentTokens()`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java)
must return the set of tokens treated as comments to populate *To Do View*.


### Context Info
EP: `com.intellij.declarationRangeHandler`

[`DeclarationRangeHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java)
provides *View \| Context Info* for custom languages with structure view implementation based on a
[`TreeBasedStructureViewBuilder`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).


### Spellchecking
EP: `com.intellij.spellchecker.support`

[`SpellcheckingStrategy`](upsource:///spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)
provides [`Tokenizer`](upsource:///spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java)
to use for given `PsiElement` (return `EMPTY_TOKENIZER` for no spellchecking).

### Reference Injection
EP: `com.intellij.referenceInjector`

[`ReferenceInjector`](upsource:///platform/lang-api/src/com/intellij/psi/injection/ReferenceInjector.java)
allows users to inject pre-defined references (e.g., "Encoding", "File Reference") into `PsiLanguageInjectionHost` elements (IntelliLang plugin required).


### Color Preview/Chooser
EP: `com.intellij.colorProvider`
 
[`ElementColorProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java)
renders gutter icon for element containing color information.
