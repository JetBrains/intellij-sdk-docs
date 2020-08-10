# Simple Language Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Custom Language Support Tutorial in IntelliJ SDK Docs][docs:custom_language_support_tutorial]*

## Quickstart

Defines a new language, _Simple language_ with support for syntax highlighting, annotations, code completion, and other
features.

## Structure

Simple Language Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                           | Implementation Class                                                                    | Interface                                                                  |
| ------------------------------ | --------------------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| fileType                       | [SimpleFileType][file:SimpleFileType]                                                   | [LanguageFileType][sdk:LanguageFileType]                                   |
| lang.parserDefinition          | [SimpleParserDefinition][file:SimpleParserDefinition]                                   | [ParserDefinition][sdk:ParserDefinition]                                   |
| lang.syntaxHighlighterFactory  | [SimpleSyntaxHighlighterFactory][file:SimpleSyntaxHighlighterFactory]                   | [SyntaxHighlighterFactory][sdk:SyntaxHighlighterFactory]                   |
| colorSettingsPage              | [SimpleColorSettingsPage][file:SimpleColorSettingsPage]                                 | [ColorSettingsPage][sdk:ColorSettingsPage]                                 |
| annotator                      | [SimpleAnnotator][file:SimpleAnnotator]                                                 | [Annotator][sdk:Annotator]                                                 |
| codeInsight.lineMarkerProvider | [SimpleLineMarkerProvider][file:SimpleLineMarkerProvider]                               | [RelatedItemLineMarkerProvider][sdk:RelatedItemLineMarkerProvider]         |
| completion.contributor         | [SimpleCompletionContributor][file:SimpleCompletionContributor]                         | [CompletionContributor][sdk:CompletionContributor]                         |
| psi.referenceContributor       | [SimpleReferenceContributor][file:SimpleReferenceContributor]                           | [PsiReferenceContributor][sdk:PsiReferenceContributor]                     |
| lang.refactoringSupport        | [SimpleRefactoringSupportProvider][file:SimpleRefactoringSupportProvider]               | [RefactoringSupportProvider][sdk:RefactoringSupportProvider]               |
| lang.findUsagesProvider        | [SimpleFindUsagesProvider][file:SimpleFindUsagesProvider]                               | [FindUsagesProvider][sdk:FindUsagesProvider]                               |
| lang.foldingBuilder            | [SimpleFoldingBuilder][file:SimpleFoldingBuilder]                                       | [FoldingBuilderEx][sdk:FoldingBuilderEx]                                   |
| gotoSymbolContributor          | [SimpleChooseByNameContributor][file:SimpleChooseByNameContributor]                     | [ChooseByNameContributor][sdk:ChooseByNameContributor]                     |
| lang.psiStructureViewFactory   | [SimpleStructureViewFactory][file:SimpleStructureViewFactory]                           | [PsiStructureViewFactory][sdk:PsiStructureViewFactory]                     |
| lang.formatter                 | [SimpleFormattingModelBuilder][file:SimpleFormattingModelBuilder]                       | [FormattingModelBuilder][sdk:FormattingModelBuilder]                       |
| codeStyleSettingsProvider      | [SimpleCodeStyleSettingsProvider][file:SimpleCodeStyleSettingsProvider]                 | [CodeStyleSettingsProvider][sdk:CodeStyleSettingsProvider]                 |
| langCodeStyleSettingsProvider  | [SimpleLanguageCodeStyleSettingsProvider][file:SimpleLanguageCodeStyleSettingsProvider] | [LanguageCodeStyleSettingsProvider][sdk:LanguageCodeStyleSettingsProvider] |
| lang.commenter                 | [SimpleCommenter][file:SimpleCommenter]                                                 | [Commenter][sdk:Commenter]                                                 |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:custom_language_support_tutorial]: https://jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
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
