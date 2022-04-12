[//]: # (title: Additional Minor Features)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Additional minor features for custom languages.</excerpt>

A number of minor features are listed in the following format:

_EP: `fully.qualified.extensionPointName`_ - Extension Point Name (must be specified in <path>plugin.xml</path>)

_`com.extensionPoint.class`_ _description text_ - Extension Point class/interface to provide functionality

_- Sample 1_ - Sample implementation

> See also [](extension_point_list.md#langextensionpointsxml) to discover more Language-related Extension Points. See also [](explore_api.md).
>
{type="tip"}

### Brace Matching

EP: `com.intellij.lang.braceMatcher`

[`PairedBraceMatcher`](upsource:///platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java)
Returns an array of brace pairs ([`BracePair`](upsource:///platform/analysis-api/src/com/intellij/lang/BracePair.java)) specifying the characters for the opening and closing braces and the lexer token types for these characters.
(In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair.
The IDE will match such braces, but the highlighting for such braces will not be entirely correct.)

Certain types of braces can be marked as structural.
Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of different types between them.
An opening non-structural brace is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.

### Comment Code

EP: `com.intellij.lang.commenter`

[`Commenter`](upsource:///platform/core-api/src/com/intellij/lang/Commenter.java) returns the prefix for the line comment, and the prefix and suffix for the block comment if supported by the language.

- [`Commenter`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java) for [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Commenter](commenter.md)

### Code Folding

EP: `com.intellij.lang.foldingBuilder`

[`FoldingBuilder`](upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) returns the list of foldable text ranges (as an array of [`FoldingDescriptor`](upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java) objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

- [Custom Language Support Tutorial: Folding Builder](folding_builder.md)

### Join Lines

EP: `com.intellij.joinLinesHandler`

[`JoinLinesHandlerDelegate`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) allows extending support smart/semantic <menupath>Edit | Join Lines</menupath> (e.g., String literal split on multiple lines).

### Smart Enter

EP: `com.intellij.lang.smartEnterProcessor`

[`SmartEnterProcessor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) handles <menupath>Edit | Complete Statement</menupath> (e.g., autocomplete missing semicolon/parentheses).

### Move Element Left/Right

EP: `com.intellij.moveLeftRightHandler`

Return children of given element from [`MoveElementLeftRightHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) for <menupath>Code | Move Element Left|Right</menupath>, e.g., method call parameters.
Alternatively, implement [`PsiListLikeElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiListLikeElement.java) in PSI element.

### Naming Suggestions

EP: `com.intellij.nameSuggestionProvider`

[`NameSuggestionProvider`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) provides name suggestions for the given element, e.g., for Rename refactoring.

### Semantic Highlight Usages

EP: `com.intellij.highlightUsagesHandlerFactory`

[`HighlightUsagesHandlerFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) allows highlighting e.g., Exit Points or Exceptions.

### TODO View

EP: n/a

[`ParserDefinition.getCommentTokens()`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java) must return the set of tokens treated as comments to populate the *TODO* window.

### Context Info

EP: `com.intellij.declarationRangeHandler`

[`DeclarationRangeHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) provides <menupath>View | Context Info</menupath> for custom languages with structure view implementation based on a [`TreeBasedStructureViewBuilder`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).

### Spellchecking

EP: `com.intellij.spellchecker.support`

[`SpellcheckingStrategy`](upsource:///spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java) provides [`Tokenizer`](upsource:///spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java) to use for given `PsiElement` (return `EMPTY_TOKENIZER` for no spellchecking).

### Reference Injection

EP: `com.intellij.referenceInjector`

[`ReferenceInjector`](upsource:///platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) allows users to inject pre-defined references (e.g., "Encoding", "File Reference") into `PsiLanguageInjectionHost` elements (IntelliLang plugin required).

### Color Preview/Chooser

EP: `com.intellij.colorProvider`

[`ElementColorProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) renders gutter icon for an element containing color information.

### File Includes

EP: `com.intellij.include.provider`

[`FileIncludeProvider`](upsource:///platform/lang-impl/src/com/intellij/psi/impl/include/FileIncludeProvider.java) provides information about _include_ statements resolving to files (e.g., `<xi:include>` in XML).
Including/included files can then be obtained via [`FileIncludeManager`](upsource:///platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeManager.java).

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
