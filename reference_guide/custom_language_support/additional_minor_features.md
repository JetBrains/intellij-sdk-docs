---
title: Additional Minor Features
---


In order to implement *brace matching*, once the syntax highlighting lexer has been implemented, all that is required is to implement the
[PairedBraceMatcher](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/PairedBraceMatcher.java)
interface and to return an array of brace pairs (
[BracePair](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/BracePair.java)
) for the language.
Each brace pair specifies the characters for the opening and closing braces and the lexer token types for these characters.
(In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair.
The IDE will match such braces, but the highlighting for such braces will not be fully correct.)

Certain types of braces can be marked as structural.
Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of other types between them, and an opening non-structural braces is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.

The *code folding* is controlled by the plugin through the
[FoldingBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java)
interface.
The interface returns the list of text ranges which are foldable (as an array of
[FoldingDescriptor](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java)
objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

The *Comment Code* feature is controlled through the
[Commenter](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Commenter.java)
interface.
The interface can return the prefix for the line comment, and the prefix and suffix for the block comment, if such features are supported by the language.

**Example:**
[Commenter](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java)
for [Properties language plugin](https://github.com/JetBrains/intellij-community/tree/master/plugins/properties)


To support smart/semantic *Join Lines* see
[JoinLinesHandlerDelegate](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java).

*Smart Enter* (e.g. autocomplete missing semicolon/parentheses) can be provided via
[SmartEnterProcessor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java).

*Naming suggestions* for Rename Refactoring can be provided via
[NameSuggestionProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/refactoring/rename/NameSuggestionProvider.java).

*Semantic highlight usages* (e.g. exit points) can be achieved using
[HighlightUsagesHandlerFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java).

**View \| Parameter Info** is provided via
[ParameterInfoHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java)
(extension point `codeInsight.parameterInfo`).

The *To Do view* is supported automatically if the plugin provides a correct implementation of the
[ParserDefinition.getCommentTokens()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/ParserDefinition.java)
method.

The **View \| Context Info** feature is supported for custom languages since IntelliJ IDEA 10.5.
In order for it to work, you need to have a structure view implementation based on a
[TreeBasedStructureViewBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java),
and additionally to provide an implementation of
[DeclarationRangeHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java)
for your language and to register it in the `declarationRangeHandler` extension point.

*Spellchecking* can be provided via EP `spellchecker.support` (
[SpellcheckingStrategy](https://github.com/JetBrains/intellij-community/blob/master/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)
) where you can return
[Tokenizer](https://github.com/JetBrains/intellij-community/blob/master/spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java)
to use, possibly depending on the passed in
[PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java)
(or `EMPTY_TOKENIZER` for no spellchecking).

**New in IntelliJ IDEA 13**: user-configurable *reference injections* can be provided via `referenceInjector` extension point (
[ReferenceInjector](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/injection/ReferenceInjector.java)
) (IntelliLang plugin required).
