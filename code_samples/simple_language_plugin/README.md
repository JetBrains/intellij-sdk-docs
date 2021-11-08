# Simple Language Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Custom Language Support Tutorial in IntelliJ SDK Docs][docs:custom_language_support_tutorial]*

## Quickstart

Defines a new language, _Simple language_ with support for syntax highlighting, annotations, code completion, and other features.

### Extension Points

| Name                                          | Implementation                                                                          | Extension Point Class               |
|-----------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------|
| `com.intellij.fileType`                       | [SimpleFileType][file:SimpleFileType]                                                   | `LanguageFileType`                  |
| `com.intellij.lang.parserDefinition`          | [SimpleParserDefinition][file:SimpleParserDefinition]                                   | `ParserDefinition`                  |
| `com.intellij.lang.syntaxHighlighterFactory`  | [SimpleSyntaxHighlighterFactory][file:SimpleSyntaxHighlighterFactory]                   | `SyntaxHighlighterFactory`          |
| `com.intellij.colorSettingsPage`              | [SimpleColorSettingsPage][file:SimpleColorSettingsPage]                                 | `ColorSettingsPage`                 |
| `com.intellij.annotator`                      | [SimpleAnnotator][file:SimpleAnnotator]                                                 | `Annotator`                         |
| `com.intellij.codeInsight.lineMarkerProvider` | [SimpleLineMarkerProvider][file:SimpleLineMarkerProvider]                               | `RelatedItemLineMarkerProvider`     |
| `com.intellij.completion.contributor`         | [SimpleCompletionContributor][file:SimpleCompletionContributor]                         | `CompletionContributor`             |
| `com.intellij.psi.referenceContributor`       | [SimpleReferenceContributor][file:SimpleReferenceContributor]                           | `PsiReferenceContributor`           |
| `com.intellij.lang.refactoringSupport`        | [SimpleRefactoringSupportProvider][file:SimpleRefactoringSupportProvider]               | `RefactoringSupportProvider`        |
| `com.intellij.lang.findUsagesProvider`        | [SimpleFindUsagesProvider][file:SimpleFindUsagesProvider]                               | `FindUsagesProvider`                |
| `com.intellij.lang.foldingBuilder`            | [SimpleFoldingBuilder][file:SimpleFoldingBuilder]                                       | `FoldingBuilderEx`                  |
| `com.intellij.gotoSymbolContributor`          | [SimpleChooseByNameContributor][file:SimpleChooseByNameContributor]                     | `ChooseByNameContributor`           |
| `com.intellij.lang.psiStructureViewFactory`   | [SimpleStructureViewFactory][file:SimpleStructureViewFactory]                           | `PsiStructureViewFactory`           |
| `com.intellij.lang.formatter`                 | [SimpleFormattingModelBuilder][file:SimpleFormattingModelBuilder]                       | `FormattingModelBuilder`            |
| `com.intellij.codeStyleSettingsProvider`      | [SimpleCodeStyleSettingsProvider][file:SimpleCodeStyleSettingsProvider]                 | `CodeStyleSettingsProvider`         |
| `com.intellij.langCodeStyleSettingsProvider`  | [SimpleLanguageCodeStyleSettingsProvider][file:SimpleLanguageCodeStyleSettingsProvider] | `LanguageCodeStyleSettingsProvider` |
| `com.intellij.lang.commenter`                 | [SimpleCommenter][file:SimpleCommenter]                                                 | `Commenter`                         |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:custom_language_support_tutorial]: https://plugins.jetbrains.com/docs/intellij/custom-language-support-tutorial.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

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

