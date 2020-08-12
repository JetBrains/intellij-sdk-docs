# Simple Language Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Custom Language Support Tutorial in IntelliJ SDK Docs][docs:custom_language_support_tutorial]*

## Quickstart

Defines a new language, _Simple language_ with support for syntax highlighting, annotations, code completion, and other features.

### Extension Points

| Name                                          | Implementation                                                                          | Extension Point Class                                                      |
| --------------------------------------------- | --------------------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| `com.intellij.fileType`                       | [SimpleFileType][file:SimpleFileType]                                                   | [LanguageFileType][sdk:LanguageFileType]                                   |
| `com.intellij.lang.parserDefinition`          | [SimpleParserDefinition][file:SimpleParserDefinition]                                   | [ParserDefinition][sdk:ParserDefinition]                                   |
| `com.intellij.lang.syntaxHighlighterFactory`  | [SimpleSyntaxHighlighterFactory][file:SimpleSyntaxHighlighterFactory]                   | [SyntaxHighlighterFactory][sdk:SyntaxHighlighterFactory]                   |
| `com.intellij.colorSettingsPage`              | [SimpleColorSettingsPage][file:SimpleColorSettingsPage]                                 | [ColorSettingsPage][sdk:ColorSettingsPage]                                 |
| `com.intellij.annotator`                      | [SimpleAnnotator][file:SimpleAnnotator]                                                 | [Annotator][sdk:Annotator]                                                 |
| `com.intellij.codeInsight.lineMarkerProvider` | [SimpleLineMarkerProvider][file:SimpleLineMarkerProvider]                               | [RelatedItemLineMarkerProvider][sdk:RelatedItemLineMarkerProvider]         |
| `com.intellij.completion.contributor`         | [SimpleCompletionContributor][file:SimpleCompletionContributor]                         | [CompletionContributor][sdk:CompletionContributor]                         |
| `com.intellij.psi.referenceContributor`       | [SimpleReferenceContributor][file:SimpleReferenceContributor]                           | [PsiReferenceContributor][sdk:PsiReferenceContributor]                     |
| `com.intellij.lang.refactoringSupport`        | [SimpleRefactoringSupportProvider][file:SimpleRefactoringSupportProvider]               | [RefactoringSupportProvider][sdk:RefactoringSupportProvider]               |
| `com.intellij.lang.findUsagesProvider`        | [SimpleFindUsagesProvider][file:SimpleFindUsagesProvider]                               | [FindUsagesProvider][sdk:FindUsagesProvider]                               |
| `com.intellij.lang.foldingBuilder`            | [SimpleFoldingBuilder][file:SimpleFoldingBuilder]                                       | [FoldingBuilderEx][sdk:FoldingBuilderEx]                                   |
| `com.intellij.gotoSymbolContributor`          | [SimpleChooseByNameContributor][file:SimpleChooseByNameContributor]                     | [ChooseByNameContributor][sdk:ChooseByNameContributor]                     |
| `com.intellij.lang.psiStructureViewFactory`   | [SimpleStructureViewFactory][file:SimpleStructureViewFactory]                           | [PsiStructureViewFactory][sdk:PsiStructureViewFactory]                     |
| `com.intellij.lang.formatter`                 | [SimpleFormattingModelBuilder][file:SimpleFormattingModelBuilder]                       | [FormattingModelBuilder][sdk:FormattingModelBuilder]                       |
| `com.intellij.codeStyleSettingsProvider`      | [SimpleCodeStyleSettingsProvider][file:SimpleCodeStyleSettingsProvider]                 | [CodeStyleSettingsProvider][sdk:CodeStyleSettingsProvider]                 |
| `com.intellij.langCodeStyleSettingsProvider`  | [SimpleLanguageCodeStyleSettingsProvider][file:SimpleLanguageCodeStyleSettingsProvider] | [LanguageCodeStyleSettingsProvider][sdk:LanguageCodeStyleSettingsProvider] |
| `com.intellij.lang.commenter`                 | [SimpleCommenter][file:SimpleCommenter]                                                 | [Commenter][sdk:Commenter]                                                 |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:custom_language_support_tutorial]: https://jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:SimpleFileType]: ./src/main/java/org/intellij/sdk/language/SimpleFileType.java
[file:SimpleParserDefinition]: ./src/main/java/org/intellij/sdk/language/SimpleParserDefinition.java
[file:SimpleSyntaxHighlighterFactory]: ./src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighterFactory.java
[file:SimpleColorSettingsPage]: ./src/main/java/org/intellij/sdk/language/SimpleColorSettingsPage.java
[file:SimpleAnnotator]: ./src/main/java/org/intellij/sdk/language/SimpleAnnotator.java
[file:SimpleLineMarkerProvider]: ./src/main/java/org/intellij/sdk/language/SimpleLineMarkerProvider.java
[file:SimpleCompletionContributor]: ./src/main/java/org/intellij/sdk/language/SimpleCompletionContributor.java
[file:SimpleReferenceContributor]: ./src/main/java/org/intellij/sdk/language/SimpleReferenceContributor.java
[file:SimpleRefactoringSupportProvider]: ./src/main/java/org/intellij/sdk/language/SimpleRefactoringSupportProvider.java
[file:SimpleFindUsagesProvider]: ./src/main/java/org/intellij/sdk/language/SimpleFindUsagesProvider.java
[file:SimpleFoldingBuilder]: ./src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java
[file:SimpleChooseByNameContributor]: ./src/main/java/org/intellij/sdk/language/SimpleChooseByNameContributor.java
[file:SimpleStructureViewFactory]: ./src/main/java/org/intellij/sdk/language/SimpleStructureViewFactory.java
[file:SimpleFormattingModelBuilder]: ./src/main/java/org/intellij/sdk/language/SimpleFormattingModelBuilder.java
[file:SimpleCodeStyleSettingsProvider]: ./src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettingsProvider.java
[file:SimpleLanguageCodeStyleSettingsProvider]: ./src/main/java/org/intellij/sdk/language/SimpleLanguageCodeStyleSettingsProvider.java
[file:SimpleCommenter]: ./src/main/java/org/intellij/sdk/language/SimpleCommenter.java

[sdk:LanguageFileType]: upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java
[sdk:ParserDefinition]: upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java
[sdk:SyntaxHighlighterFactory]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java
[sdk:ColorSettingsPage]: upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java
[sdk:Annotator]: upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java
[sdk:RelatedItemLineMarkerProvider]: upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/RelatedItemLineMarkerProvider.java
[sdk:CompletionContributor]: upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java
[sdk:PsiReferenceContributor]: upsource:///platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java
[sdk:RefactoringSupportProvider]: upsource:///platform/lang-api/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java
[sdk:FindUsagesProvider]: upsource:///platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java
[sdk:FoldingBuilderEx]: upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingBuilderEx.java
[sdk:ChooseByNameContributor]: upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java
[sdk:PsiStructureViewFactory]: upsource:///platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java
[sdk:FormattingModelBuilder]: upsource:///platform/lang-api/src/com/intellij/formatting/FormattingModelBuilder.java
[sdk:CodeStyleSettingsProvider]: upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java
[sdk:LanguageCodeStyleSettingsProvider]: upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java
[sdk:Commenter]: upsource:///platform/core-api/src/com/intellij/lang/Commenter.java
