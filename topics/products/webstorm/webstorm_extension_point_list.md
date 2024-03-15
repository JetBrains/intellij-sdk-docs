<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory:
     /CSS/
     /plugins/JavaScriptLanguage/
     /plugins/NodeJS/
     /plugins/sass/
-->

# WebStorm Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for WebStorm.</link-summary>

74 Extension Points and 5 Listeners for WebStorm

See [](extension_point_list.md) for IntelliJ Platform and [](oss_plugins_extension_point_list.md) for additional plugins.

<include from="snippets.md" element-id="ep_list_legend"/>

## WebStorm

### WebStorm - Listeners

| Topic | Listener |
|-------|----------|
| [JestConsoleProperties#COVERAGE_CONFIG_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.jest.JestCoverageConfigListener)  ![Project-Level][project-level] | `JestCoverageConfigListener` |
| [PackageJsonFileManager#CHANGES_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.nodejs.packageJson.PackageJsonFileManager.PackageJsonChangesListener)  ![Project-Level][project-level] | `PackageJsonChangesListener` |
| [VitestConsoleProperties#COVERAGE_CONFIG_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.testing.vitest.coverage.VitestCoverageConfigListener)  ![Project-Level][project-level] | `VitestCoverageConfigListener` |
| [JSLibraryManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.library.JSLibraryManager.JSLibraryManagerChangeListener)  ![Project-Level][project-level] | `JSLibraryManagerChangeListener` |
| [JSRemoteModulesRegistry#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.modules.remote.JSRemoteModulesChangeListener)  | `JSRemoteModulesChangeListener` |


### com.intellij.css

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.css.classOrIdUsagesProvider](https://jb.gg/ipe?extensions=com.intellij.css.classOrIdUsagesProvider) | `CssClassOrIdUsagesProvider` |
| [com.intellij.css.cssFileReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.css.cssFileReferenceProvider) | `CssFileReferenceProvider` |
| [com.intellij.css.cssInspectionFilter](https://jb.gg/ipe?extensions=com.intellij.css.cssInspectionFilter) | `CssInspectionFilter` |
| [com.intellij.css.cssIntentionFilter](https://jb.gg/ipe?extensions=com.intellij.css.cssIntentionFilter) | `CssIntentionFilter` |
| [com.intellij.css.dialect](https://jb.gg/ipe?extensions=com.intellij.css.dialect) | `CssDialect` |
| [com.intellij.css.elementDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.css.elementDescriptorProvider) | `CssElementDescriptorProvider` |
| [com.intellij.css.embeddedCssProvider](https://jb.gg/ipe?extensions=com.intellij.css.embeddedCssProvider) | `EmbeddedCssProvider` |
| [com.intellij.css.inclusionContext](https://jb.gg/ipe?extensions=com.intellij.css.inclusionContext) | `CssInclusionContext` |
| [com.intellij.css.structureViewChildrenProvider](https://jb.gg/ipe?extensions=com.intellij.css.structureViewChildrenProvider) | `CssStructureViewElementsProvider` |
| [com.intellij.css.supportedFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.css.supportedFileTypesProvider) | `CssSupportedFileTypesProvider` |

### intellij.javascript.impl.diagrams.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.javascript.module.provider](https://jb.gg/ipe?extensions=com.intellij.javascript.module.provider) | `JSModuleConnectionProvider` |

### intellij.javascript.web.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.javascript.webTypes](https://jb.gg/ipe?extensions=com.intellij.javascript.webTypes) ![Removal][removal] | `n/a` |

### js-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.FlowJSAnnotatorCheckerProvider](https://jb.gg/ipe?extensions=JavaScript.FlowJSAnnotatorCheckerProvider) | `FlowJSAnnotatorCheckerProvider` |
| [JavaScript.JSImplicitElementsIndexFileTypeProvider](https://jb.gg/ipe?extensions=JavaScript.JSImplicitElementsIndexFileTypeProvider) | `JSImplicitElementsIndexFileTypeProvider` |
| [JavaScript.TypeScriptAnnotatorCheckerProvider](https://jb.gg/ipe?extensions=JavaScript.TypeScriptAnnotatorCheckerProvider) ![Project-Level][project-level] | `TypeScriptAnnotatorCheckerProvider` |
| [JavaScript.analysisHandlersFactory](https://jb.gg/ipe?extensions=JavaScript.analysisHandlersFactory) | `JSAnalysisHandlersFactory` |
| [JavaScript.classInheritorsProvider](https://jb.gg/ipe?extensions=JavaScript.classInheritorsProvider) | `JSClassInheritorsProvider` |
| [JavaScript.completionHelper](https://jb.gg/ipe?extensions=JavaScript.completionHelper) | `JSCompletionHelper` |
| [JavaScript.completionPlaceFilter](https://jb.gg/ipe?extensions=JavaScript.completionPlaceFilter) ![Project-Level][project-level] | `JSCompletionPlaceFilterProvider` |
| [JavaScript.componentUsageProvider](https://jb.gg/ipe?extensions=JavaScript.componentUsageProvider) | `JSComponentUsageProvider` |
| [JavaScript.conditionalCompilationDefinitionsProvider](https://jb.gg/ipe?extensions=JavaScript.conditionalCompilationDefinitionsProvider) | `JSConditionalCompilationDefinitionsProvider` |
| [JavaScript.dialectSpecificHandlersFactory](https://jb.gg/ipe?extensions=JavaScript.dialectSpecificHandlersFactory) | `JSDialectSpecificHandlersFactory` |
| [JavaScript.elementScopeProvider](https://jb.gg/ipe?extensions=JavaScript.elementScopeProvider) | `JSElementResolveScopeProvider` |
| [JavaScript.frameworkIndexingHandler](https://jb.gg/ipe?extensions=JavaScript.frameworkIndexingHandler) | `FrameworkIndexingHandler` |
| [JavaScript.frameworkSpecificHandler](https://jb.gg/ipe?extensions=JavaScript.frameworkSpecificHandler) | `JSFrameworkSpecificHandler` |
| [JavaScript.frameworkSpecificStructureViewExtension](https://jb.gg/ipe?extensions=JavaScript.frameworkSpecificStructureViewExtension) | `JSFrameworkSpecificStructureExtension` |
| [JavaScript.handlersFactory](https://jb.gg/ipe?extensions=JavaScript.handlersFactory) | `JSHandlersFactory` |
| [JavaScript.iconProvider](https://jb.gg/ipe?extensions=JavaScript.iconProvider) | `JSIconProvider` |
| [JavaScript.importCandidatesFactory](https://jb.gg/ipe?extensions=JavaScript.importCandidatesFactory) | `CandidatesFactory` |
| [JavaScript.importCandidatesFilterFactory](https://jb.gg/ipe?extensions=JavaScript.importCandidatesFilterFactory) | `FilterFactory` |
| [JavaScript.importModulePathStrategy](https://jb.gg/ipe?extensions=JavaScript.importModulePathStrategy) ![Experimental][experimental] | `JSImportModulePathStrategy` |
| [JavaScript.indexedFileTypeProvider](https://jb.gg/ipe?extensions=JavaScript.indexedFileTypeProvider) | `IndexedFileTypeProvider` |
| [JavaScript.inheritedLanguagesConfigurableProvider](https://jb.gg/ipe?extensions=JavaScript.inheritedLanguagesConfigurableProvider) | `JSInheritedLanguagesConfigurableProvider` |
| [JavaScript.intentionAndInspectionFilter](https://jb.gg/ipe?extensions=JavaScript.intentionAndInspectionFilter) | `IntentionAndInspectionFilter` |
| [JavaScript.jestPackageProvider](https://jb.gg/ipe?extensions=JavaScript.jestPackageProvider) | `JestPackageProvider` |
| [JavaScript.jsDocCustomTagsHandler](https://jb.gg/ipe?extensions=JavaScript.jsDocCustomTagsHandler) ![Experimental][experimental] | `JSDocCustomTagsHandler` |
| [JavaScript.jsxImplementation](https://jb.gg/ipe?extensions=JavaScript.jsxImplementation) | `JSXImplementation` |
| [JavaScript.lang.templates](https://jb.gg/ipe?extensions=JavaScript.lang.templates) ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [JavaScript.languageServiceProvider](https://jb.gg/ipe?extensions=JavaScript.languageServiceProvider) ![Project-Level][project-level] | `JSLanguageServiceProvider` |
| [JavaScript.languageServiceRemoteHelperFactory](https://jb.gg/ipe?extensions=JavaScript.languageServiceRemoteHelperFactory) | `Factory` |
| [JavaScript.minifiedFileIndexingProvider](https://jb.gg/ipe?extensions=JavaScript.minifiedFileIndexingProvider) ![Internal][internal] | `JSMinifiedFileIndexingProvider` |
| [JavaScript.moduleExportsProvider](https://jb.gg/ipe?extensions=JavaScript.moduleExportsProvider) | `JSModuleExportsProvider` |
| [JavaScript.moduleReferenceContributor](https://jb.gg/ipe?extensions=JavaScript.moduleReferenceContributor) | `JSModuleReferenceContributor` |
| [JavaScript.nodeModulesIndexableFileNamesProvider](https://jb.gg/ipe?extensions=JavaScript.nodeModulesIndexableFileNamesProvider) | `NodeModulesIndexableFileNamesProvider` |
| [JavaScript.nodeRunConfigurationExtension](https://jb.gg/ipe?extensions=JavaScript.nodeRunConfigurationExtension) | `AbstractNodeRunConfigurationExtension` |
| [JavaScript.predefinedLibraryProvider](https://jb.gg/ipe?extensions=JavaScript.predefinedLibraryProvider) | `JSPredefinedLibraryProvider` |
| [JavaScript.projectGeneratorPanelCustomizer](https://jb.gg/ipe?extensions=JavaScript.projectGeneratorPanelCustomizer) | `ProjectGeneratorSettingsCustomizer` |
| [JavaScript.resolveHelper](https://jb.gg/ipe?extensions=JavaScript.resolveHelper) | `JSResolveHelper` |
| [JavaScript.runConfigurationBuilder](https://jb.gg/ipe?extensions=JavaScript.runConfigurationBuilder) ![Project-Level][project-level] | `JSRunConfigurationBuilder` |
| [JavaScript.scanningFileListenerContributor](https://jb.gg/ipe?extensions=JavaScript.scanningFileListenerContributor) | `ScanningFileListenerContributor` |
| [JavaScript.smartCompletionContributor](https://jb.gg/ipe?extensions=JavaScript.smartCompletionContributor) | `JSSmartCompletionContributor` |
| [JavaScript.spellcheckerProvider](https://jb.gg/ipe?extensions=JavaScript.spellcheckerProvider) | `JSSpellcheckerProvider` |
| [JavaScript.testWatchProvider](https://jb.gg/ipe?extensions=JavaScript.testWatchProvider) | `JsTestWatchProvider` |
| [JavaScript.tsConfigCustomizer](https://jb.gg/ipe?extensions=JavaScript.tsConfigCustomizer) ![Experimental][experimental] | `TypeScriptConfigCustomizer` |
| [JavaScript.tsImportResolver](https://jb.gg/ipe?extensions=JavaScript.tsImportResolver) | `TypeScriptImportsResolverProvider` |
| [JavaScript.tsServiceExtension](https://jb.gg/ipe?extensions=JavaScript.tsServiceExtension) | `TypeScriptServiceExtension` |
| [JavaScript.unresolvedReferenceErrorUpdater](https://jb.gg/ipe?extensions=JavaScript.unresolvedReferenceErrorUpdater) ![Experimental][experimental] | `JSUnresolvedReferenceErrorUpdater` |
| [JavaScript.webBundlerCssReferenceContributor](https://jb.gg/ipe?extensions=JavaScript.webBundlerCssReferenceContributor) | `JSModuleReferenceContributor` |
| [JavaScript.webBundlerDefinition](https://jb.gg/ipe?extensions=JavaScript.webBundlerDefinition) | `WebBundlerDefinition` |
| [JavaScript.xmlBackedClassProvider](https://jb.gg/ipe?extensions=JavaScript.xmlBackedClassProvider) | `XmlBackedJSClassProvider` |
| [NodeJS.runConfigurationLocationFilter](https://jb.gg/ipe?extensions=NodeJS.runConfigurationLocationFilter) | `NodeRunConfigurationLocationFilter` |
| [com.intellij.JavaScript.linter.descriptor](https://jb.gg/ipe?extensions=com.intellij.JavaScript.linter.descriptor) | `JSLinterDescriptor` |
| [com.intellij.JavaScript.linter.execution.suppressor](https://jb.gg/ipe?extensions=com.intellij.JavaScript.linter.execution.suppressor) | `JSLinterExecutionSuppressor` |
| [com.intellij.eslint.ruleMappersFactory](https://jb.gg/ipe?extensions=com.intellij.eslint.ruleMappersFactory) | `EslintRuleMappersFactory` |
| [com.intellij.javascript.extract.interface.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.extract.interface.extension) | `JSCustomExtractInterfaceHandler` |
| [com.intellij.javascript.introduce.variable.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.introduce.variable.extension) | `JSCustomIntroduceVariableHandler` |
| [com.intellij.javascript.json.schema.provider](https://jb.gg/ipe?extensions=com.intellij.javascript.json.schema.provider) | `JsonSchemaInJavaScriptProvider` |
| [com.intellij.javascript.library.externalDefinitionsContributor](https://jb.gg/ipe?extensions=com.intellij.javascript.library.externalDefinitionsContributor) | `TypeScriptExternalDefinitionsContributor` |
| [com.intellij.javascript.names.suggester](https://jb.gg/ipe?extensions=com.intellij.javascript.names.suggester) | `JSNamesSuggester` |
| [com.intellij.javascript.rename.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.rename.extension) | `JSRenameExtension` |
| [com.intellij.jsbtFileManagerProvider](https://jb.gg/ipe?extensions=com.intellij.jsbtFileManagerProvider) | `JsbtFileManagerProvider` |
| [com.intellij.jsbtService](https://jb.gg/ipe?extensions=com.intellij.jsbtService) | `JsbtApplicationService` |

### org.jetbrains.plugins.node-remote-interpreter

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.node-remote-interpreter.nodeRemoteTargetRunSetupFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.node-remote-interpreter.nodeRemoteTargetRunSetupFactory) | `NodeRemoteTargetRunSetupFactory` |

### org.jetbrains.plugins.sass

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.sass.extension](https://jb.gg/ipe?extensions=com.intellij.sass.extension) | `SassExtension` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
