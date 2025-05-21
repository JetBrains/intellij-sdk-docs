<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /CSS
- /plugins/JavaScriptLanguage
- /plugins/NodeJS
- /plugins/sass
-->


<snippet id="content">

80 Extension Points and 5 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## WebStorm

### WebStorm – Listeners

| Topic | Listener |
|-------|----------|
| [`JestConsoleProperties#COVERAGE_CONFIG_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.jest.JestCoverageConfigListener)  ![Project-Level][project-level] | `JestCoverageConfigListener` |
| [`PackageJsonFileManager#CHANGES_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.nodejs.packageJson.PackageJsonFileManager.PackageJsonChangesListener)  ![Project-Level][project-level] | `PackageJsonChangesListener` |
| [`VitestConsoleProperties#COVERAGE_CONFIG_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.testing.vitest.coverage.VitestCoverageConfigListener)  ![Project-Level][project-level] | `VitestCoverageConfigListener` |
| [`JSLibraryManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.library.JSLibraryManager.JSLibraryManagerChangeListener)  ![Project-Level][project-level] | `JSLibraryManagerChangeListener` |
| [`JSRemoteModulesRegistry#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.modules.remote.JSRemoteModulesChangeListener)  | `JSRemoteModulesChangeListener` |


### com.intellij.css

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.classOrIdUsagesProvider"/></include> | `CssClassOrIdUsagesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssFileReferenceProvider"/></include> | `CssFileReferenceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssInspectionFilter"/></include> | `CssInspectionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssIntentionFilter"/></include> | `CssIntentionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssRelatedStylesheetsProvider"/></include> | `CssRelatedStylesheetsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.dialect"/></include> | `CssDialect` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.elementDescriptorProvider"/></include> | `CssElementDescriptorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.embeddedCssProvider"/></include> | `EmbeddedCssProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.inclusionContext"/></include> | `CssInclusionContext` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.structureViewChildrenProvider"/></include> | `CssStructureViewElementsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.supportedFileTypesProvider"/></include> | `CssSupportedFileTypesProvider` |

### intellij.javascript.impl.diagrams.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.module.provider"/></include> | `JSModuleConnectionProvider` |

### intellij.javascript.web.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.webTypes"/></include> ![Removal][removal] | `n/a` |

### js-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.FlowJSAnnotatorCheckerProvider"/></include> | `FlowJSAnnotatorCheckerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.JSImplicitElementsIndexFileTypeProvider"/></include> | `JSImplicitElementsIndexFileTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.TypeScriptAnnotatorCheckerProvider"/></include> ![Project-Level][project-level] | `TypeScriptAnnotatorCheckerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.analysisHandlersFactory"/></include> | `JSAnalysisHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.classInheritorsProvider"/></include> | `JSClassInheritorsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.completionHelper"/></include> | `JSCompletionHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.completionPlaceFilter"/></include> ![Project-Level][project-level] | `JSCompletionPlaceFilterProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.componentUsageProvider"/></include> | `JSComponentUsageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.conditionalCompilationDefinitionsProvider"/></include> | `JSConditionalCompilationDefinitionsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.dialectSpecificHandlersFactory"/></include> | `JSDialectSpecificHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.directFileReferenceResolverProvider"/></include> | `JSDirectFileReferenceResolverProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.elementScopeProvider"/></include> | `JSElementResolveScopeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkIndexingHandler"/></include> | `FrameworkIndexingHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkSpecificHandler"/></include> | `JSFrameworkSpecificHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkSpecificStructureViewExtension"/></include> | `JSFrameworkSpecificStructureExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.handlersFactory"/></include> | `JSHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.iconProvider"/></include> | `JSIconProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importCandidatesFactory"/></include> | `CandidatesFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importCandidatesFilterFactory"/></include> | `FilterFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importModulePathStrategy"/></include> ![Experimental][experimental] | `JSImportModulePathStrategy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.indexedFileTypeProvider"/></include> | `IndexedFileTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.inheritedLanguagesConfigurableProvider"/></include> | `JSInheritedLanguagesConfigurableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.intentionAndInspectionFilter"/></include> | `IntentionAndInspectionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.itemPresentation"/></include> ![Experimental][experimental] | `JSItemPresentationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jestPackageProvider"/></include> | `JestPackageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jsDocCustomTagsHandler"/></include> ![Experimental][experimental] | `JSDocCustomTagsHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jsxImplementation"/></include> | `JSXImplementation` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.lang.templates"/></include> ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.languageServiceProvider"/></include> ![Project-Level][project-level] | `JSLanguageServiceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.languageServiceQuickFixProvider"/></include> | `JSLanguageServiceQuickFixProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.languageServiceRemoteHelperFactory"/></include> | `Factory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.minifiedFileIndexingProvider"/></include> ![Internal][internal] | `JSMinifiedFileIndexingProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.moduleExportsProvider"/></include> | `JSModuleExportsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.moduleReferenceContributor"/></include> | `JSModuleReferenceContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.nodeModulesIndexableFileNamesProvider"/></include> | `NodeModulesIndexableFileNamesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.nodeRunConfigurationExtension"/></include> | `AbstractNodeRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.predefinedLibraryProvider"/></include> | `JSPredefinedLibraryProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.projectGeneratorPanelCustomizer"/></include> | `ProjectGeneratorSettingsCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.resolveHelper"/></include> | `JSResolveHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.runConfigurationBuilder"/></include> ![Project-Level][project-level] | `JSRunConfigurationBuilder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.scanningFileListenerContributor"/></include> | `ScanningFileListenerContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.smartCompletionContributor"/></include> | `JSSmartCompletionContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.spellcheckerProvider"/></include> | `JSSpellcheckerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.testFrameworkDetector"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.testWatchProvider"/></include> | `JsTestWatchProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsConfigCustomizer"/></include> ![Experimental][experimental] | `TypeScriptConfigCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsImportResolver"/></include> | `TypeScriptImportsResolverProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsServiceExtension"/></include> | `TypeScriptServiceExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.typeScriptCompilerTypePatcher"/></include> | `TypeScriptCompilerTypePatcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.unresolvedReferenceErrorUpdater"/></include> ![Experimental][experimental] | `JSUnresolvedReferenceErrorUpdater` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.webBundlerCssReferenceContributor"/></include> | `JSModuleReferenceContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.webBundlerDefinition"/></include> | `WebBundlerDefinition` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.xmlBackedClassProvider"/></include> | `XmlBackedJSClassProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="NodeJS.runConfigurationLocationFilter"/></include> | `NodeRunConfigurationLocationFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.JavaScript.linter.descriptor"/></include> | `JSLinterDescriptor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.JavaScript.linter.execution.suppressor"/></include> | `JSLinterExecutionSuppressor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.eslint.ruleMappersFactory"/></include> | `EslintRuleMappersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.extract.interface.extension"/></include> | `JSCustomExtractInterfaceHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.introduce.variable.extension"/></include> | `JSCustomIntroduceVariableHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.json.schema.provider"/></include> | `JsonSchemaInJavaScriptProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.library.externalDefinitionsContributor"/></include> | `TypeScriptExternalDefinitionsContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.names.suggester"/></include> | `JSNamesSuggester` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.rename.extension"/></include> | `JSRenameExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jsbtFileManagerProvider"/></include> | `JsbtFileManagerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jsbtService"/></include> | `JsbtApplicationService` |

### org.jetbrains.plugins.node-remote-interpreter

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.node-remote-interpreter.nodeRemoteTargetRunSetupFactory"/></include> | `NodeRemoteTargetRunSetupFactory` |

### org.jetbrains.plugins.sass

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sass.extension"/></include> | `SassExtension` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
