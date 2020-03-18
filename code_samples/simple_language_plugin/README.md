[IntelliJ Platform SDK Code Samples](../README.md)

# Simple Language Sample Project

## Quickstart

Demonstrates how to add custom language support to an IntelliJ Platform-based IDE.

Defines a new language, _Simple language_ with support for syntax highlighting, annotations, code completion, and other
features.

See the [Custom Language Tutorial][docs_custom_language_support_tutorial] for more information.
      
## Structure

The plugin was developed using the [IntelliJ Platform SDK][docs_sdk].

The main file is [plugin.xml][plugin.xml], which is created accordingly to the [Plugin Configuration File documentation][docs_pluginxml].
It describes definitions of the actions, extensions, or listeners provided by the plugin:

### Extension Points

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| fileType | [SimpleFileType][fileType_implementation] | [LanguageFileType][fileType_interface] |
| lang.parserDefinition | [SimpleParserDefinition][parserDefinition_implementation] | [ParserDefinition][parserDefinition_interface] |
| lang.syntaxHighlighterFactory | [SimpleSyntaxHighlighterFactory][syntaxHighlighterFactory_implementation] | [SyntaxHighlighterFactory][syntaxHighlighterFactory_interface] |
| colorSettingsPage | [SimpleColorSettingsPage][colorSettingsPage_implementation] | [ColorSettingsPage][colorSettingsPage_interface] |
| annotator | [SimpleAnnotator][annotator_implementation] | [Annotator][annotator_interface] |
| codeInsight.lineMarkerProvider | [SimpleLineMarkerProvider][lineMarkerProvider_implementation] | [RelatedItemLineMarkerProvider][lineMarkerProvider_interface] |
| completion.contributor | [SimpleCompletionContributor][contributor_implementation] | [CompletionContributor][contributor_interface] |
| psi.referenceContributor | [SimpleReferenceContributor][referenceContributor_implementation] | [PsiReferenceContributor][referenceContributor_interface] |
| lang.refactoringSupport | [SimpleRefactoringSupportProvider][refactoringSupport_implementation] | [RefactoringSupportProvider][refactoringSupport_interface] |
| lang.findUsagesProvider | [SimpleFindUsagesProvider][findUsagesProvider_implementation] | [FindUsagesProvider][findUsagesProvider_interface] |
| lang.foldingBuilder | [SimpleFoldingBuilder][foldingBuilder_implementation] | [FoldingBuilderEx][foldingBuilder_interface] |
| gotoSymbolContributor | [SimpleChooseByNameContributor][gotoSymbolContributor_implementation] | [ChooseByNameContributor][gotoSymbolContributor_interface] |
| lang.psiStructureViewFactory | [SimpleStructureViewFactory][psiStructureViewFactory_implementation] | [PsiStructureViewFactory][psiStructureViewFactory_interface] |
| lang.formatter | [SimpleFormattingModelBuilder][formatter_implementation] | [FormattingModelBuilder][formatter_interface] |
| codeStyleSettingsProvider | [SimpleCodeStyleSettingsProvider][codeStyleSettingsProvider_implementation] | [CodeStyleSettingsProvider][codeStyleSettingsProvider_interface] |
| langCodeStyleSettingsProvider | [SimpleLanguageCodeStyleSettingsProvider][langCodeStyleSettingsProvider_implementation] | [LanguageCodeStyleSettingsProvider][langCodeStyleSettingsProvider_interface] |
| lang.commenter | [SimpleCommenter][commenter_implementation] | [Commenter][commenter_interface] |

[Extension Points documentation][docs_ep]

## Function

The language implemented in this example is named "Simple" which main definition is 

TODO

[plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[docs_custom_language_support_tutorial]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html
[docs_pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs_sdk]: https://www.jetbrains.org/intellij/sdk/docs/intro/about.html
[docs_ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs_run]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin

[fileType_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleFileType.java
[fileType_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java
[parserDefinition_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleParserDefinition.java
[parserDefinition_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/ParserDefinition.java
[syntaxHighlighterFactory_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleSyntaxHighlighterFactory.java
[syntaxHighlighterFactory_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java
[colorSettingsPage_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleColorSettingsPage.java
[colorSettingsPage_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java
[annotator_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleAnnotator.java
[annotator_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java
[lineMarkerProvider_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleLineMarkerProvider.java
[lineMarkerProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/daemon/RelatedItemLineMarkerProvider.java
[contributor_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleCompletionContributor.java
[contributor_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java
[referenceContributor_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleReferenceContributor.java
[referenceContributor_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java
[refactoringSupport_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleRefactoringSupportProvider.java
[refactoringSupport_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java
[findUsagesProvider_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleFindUsagesProvider.java
[findUsagesProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java
[foldingBuilder_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleFoldingBuilder.java
[foldingBuilder_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/folding/FoldingBuilderEx.java
[gotoSymbolContributor_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleChooseByNameContributor.java
[gotoSymbolContributor_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java
[psiStructureViewFactory_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleStructureViewFactory.java
[psiStructureViewFactory_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java
[formatter_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleFormattingModelBuilder.java
[formatter_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/FormattingModelBuilder.java
[codeStyleSettingsProvider_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleCodeStyleSettingsProvider.java
[codeStyleSettingsProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java
[langCodeStyleSettingsProvider_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleLanguageCodeStyleSettingsProvider.java
[langCodeStyleSettingsProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java
[commenter_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/SimpleCommenter.java
[commenter_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Commenter.java
