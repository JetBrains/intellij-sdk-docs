<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: 0b6db099218edf465d512609a04796a0c678177d -->

<!--
EP List Directories:
- /plugins/css
- /plugins/JavaScriptLanguage
- /plugins/NodeJS
- /plugins/sass
-->


<snippet id="content">

99 Extension Points and 5 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## WebStorm

### WebStorm – Listeners

| Topic | Listener |
|-------|----------|
| [`JestConsoleProperties#COVERAGE_CONFIG_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.jest.JestCoverageConfigListener)  ![Project-Level][project-level] | `JestCoverageConfigListener` |
| [`PackageJsonFileManager#CHANGES_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.nodejs.packageJson.PackageJsonFileManager.PackageJsonChangesListener)  ![Project-Level][project-level] | `PackageJsonChangesListener` |
| [`VitestConsoleProperties#COVERAGE_CONFIG_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javascript.testing.vitest.VitestCoverageConfigListener)  ![Project-Level][project-level] | `VitestCoverageConfigListener` |
| [`JSLibraryManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.library.JSLibraryManager.JSLibraryManagerChangeListener)  ![Project-Level][project-level] | `JSLibraryManagerChangeListener` |
| [`JSRemoteModulesRegistry#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.modules.remote.JSRemoteModulesChangeListener)  | `JSRemoteModulesChangeListener` |
{sticky-header="true"}


### intellij.css.analysis.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssInspectionFilter"/></include> | `CssInspectionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.unknownHtmlTagQuickFixesProvider"/></include> | `UnknownHtmlTagQuickFixesProvider` |
{sticky-header="true"}

### intellij.css.backend.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssIntentionFilter"/></include> | `CssIntentionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssRelatedStylesheetsProvider"/></include> | `CssRelatedStylesheetsProvider` |
{sticky-header="true"}

### intellij.css.common.psi.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.additionalScopeProvider"/></include> | `CssAdditionalScopeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.classOrIdUsagesProvider"/></include> | `CssClassOrIdUsagesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.cssFileReferenceProvider"/></include> | `CssFileReferenceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.elementDescriptorProvider"/></include> | `CssElementDescriptorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.embeddedCssProvider"/></include> | `EmbeddedCssProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.inclusionContext"/></include> | `CssInclusionContext` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.structureViewChildrenProvider"/></include> | `CssStructureViewElementsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.supportedFileTypesProvider"/></include> | `CssSupportedFileTypesProvider` |
{sticky-header="true"}

### intellij.css.common.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.dialect"/></include> | `CssDialect` |
{sticky-header="true"}

### intellij.css.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.blockManipulator"/></include> | `CssBlockManipulator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.descriptorHandler"/></include> | `CssDescriptorHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.selectorMatcher"/></include> ![Experimental][experimental] | `CssMatcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.css.styleSheetResolver"/></include> | `CssStylesheetResolver` |
{sticky-header="true"}

### intellij.javascript.backend.diagrams.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.module.provider"/></include> | `JSModuleConnectionProvider` |
{sticky-header="true"}

### intellij.javascript.backend.spellchecker.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.spellcheckerProvider"/></include> | `JSSpellcheckerProvider` |
{sticky-header="true"}

### intellij.javascript.backend.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.FlowJSAnnotatorCheckerProvider"/></include> | `FlowJSAnnotatorCheckerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.TypeScriptAnnotatorCheckerProvider"/></include> ![Project-Level][project-level] | `TypeScriptAnnotatorCheckerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.completionHelper"/></include> | `JSCompletionHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.completionPlaceFilter"/></include> ![Project-Level][project-level] | `JSCompletionPlaceFilterProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.componentUsageProvider"/></include> | `JSComponentUsageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.handlersFactory"/></include> | `JSHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.highlightingHandlersFactory"/></include> | `JSHighlightingHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importCandidatesFactory"/></include> | `CandidatesFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importCandidatesFilterFactory"/></include> | `FilterFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.intentionAndInspectionFilter"/></include> | `IntentionAndInspectionFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.languageServiceProvider"/></include> ![Deprecated][deprecated] ![Project-Level][project-level] | `JSLanguageServiceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.languageServiceQuickFixProvider"/></include> | `JSLanguageServiceQuickFixProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.nodeModulesIndexableFileNamesProvider"/></include> | `NodeModulesIndexableFileNamesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.nodeRunConfigurationExtension"/></include> | `AbstractNodeRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.predefinedLibraryProvider"/></include> | `JSPredefinedLibraryProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.runConfigurationBuilder"/></include> ![Project-Level][project-level] | `JSRunConfigurationBuilder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.runtimeProvider"/></include> ![Internal][internal] | `JSRuntimeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.scanningFileListenerContributor"/></include> | `ScanningFileListenerContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.smartCompletionContributor"/></include> | `JSSmartCompletionContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.testFrameworkDetector"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.testWatchProvider"/></include> | `JsTestWatchProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsConfigCustomizer"/></include> ![Experimental][experimental] | `TypeScriptConfigCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsImportResolver"/></include> | `TypeScriptImportsResolverProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.tsServiceExtension"/></include> | `TypeScriptServiceExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.webBundlerDefinition"/></include> | `WebBundlerDefinition` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="NodeJS.runConfigurationLocationFilter"/></include> | `NodeRunConfigurationLocationFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.JavaScript.linter.descriptor"/></include> | `JSLinterDescriptor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssClassNameProvider"/></include> | `CssClassNameProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssDocGenerator"/></include> | `CssDocGenerator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssFileExtensionProvider"/></include> | `CssFileExtensionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssIntegrationHelper"/></include> | `JsCssIntegrationHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.extract.interface.extension"/></include> | `JSCustomExtractInterfaceHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.introduce.variable.extension"/></include> | `JSCustomIntroduceVariableHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.json.schema.provider"/></include> | `JsonSchemaInJavaScriptProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.library.externalDefinitionsContributor"/></include> | `TypeScriptExternalDefinitionsContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.names.suggester"/></include> | `JSNamesSuggester` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.nodeCoreSourcesFetcher"/></include> | `NodeCoreSourcesFetcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.rename.extension"/></include> | `JSRenameExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jsbtFileManagerProvider"/></include> | `JsbtFileManagerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jsbtService"/></include> | `JsbtApplicationService` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typescript.kolar.transpiler"/></include> ![Project-Level][project-level] | `KolarTranspiler` |
{sticky-header="true"}

### intellij.javascript.codeinsight.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.analysisHandlersFactory"/></include> | `JSAnalysisHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jsxCopyPasteActionsProvider"/></include> | `JSXCopyPasteActionsProvider` |
{sticky-header="true"}

### intellij.javascript.common.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.commonHandlersFactory"/></include> | `JSCommonHandlersFactory` |
{sticky-header="true"}

### intellij.javascript.ideaUltimate.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.webFileReferenceFileTypeProvider"/></include> | `IdeaWebFileReferenceFileTypeProvider` |
{sticky-header="true"}

### intellij.javascript.psi.impl.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.JSImplicitElementsIndexFileTypeProvider"/></include> | `JSImplicitElementsIndexFileTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.classInheritorsProvider"/></include> | `JSClassInheritorsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.dialectSpecificHandlersFactory"/></include> | `JSDialectSpecificHandlersFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.directFileReferenceResolverProvider"/></include> | `JSDirectFileReferenceResolverProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.elementIndexingDataCalculator"/></include> | `JSElementIndexingDataCalculator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.elementScopeProvider"/></include> | `JSElementResolveScopeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkIndexingHandler"/></include> | `FrameworkIndexingHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkSpecificHandler"/></include> | `JSFrameworkSpecificHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.iconProvider"/></include> | `JSIconProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.importModulePathStrategy"/></include> ![Experimental][experimental] | `JSImportModulePathStrategy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.indexedFileTypeProvider"/></include> | `IndexedFileTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.inheritedLanguagesConfigurableProvider"/></include> | `JSInheritedLanguagesConfigurableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.itemPresentation"/></include> ![Experimental][experimental] | `JSItemPresentationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jsDocCustomTagsHandler"/></include> ![Experimental][experimental] | `JSDocCustomTagsHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jsxImplementation"/></include> | `JSXImplementation` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.lang.templates"/></include> ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.libraryVersionChecker"/></include> | `JSLibraryVersionChecker` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.minifiedFileIndexingProvider"/></include> ![Internal][internal] | `JSMinifiedFileIndexingProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.moduleExportsProvider"/></include> | `JSModuleExportsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.moduleReferenceContributor"/></include> | `JSModuleReferenceContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.resolveHelper"/></include> | `JSResolveHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.servicePoweredTypeEngineEvaluator"/></include> ![Internal][internal] | `JSServicePoweredTypeEngineEvaluator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.typeScriptCompilerTypeFilter"/></include> | `TypeScriptCompilerTypeFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.unresolvedReferenceErrorUpdater"/></include> ![Experimental][experimental] | `JSUnresolvedReferenceErrorUpdater` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.webBundlerCssReferenceContributor"/></include> | `JSModuleReferenceContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.xmlBackedClassProvider"/></include> | `XmlBackedJSClassProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssClassesProcessor"/></include> | `CssClassesProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssModuleNameProvider"/></include> | `CssModuleNameProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssResolveSupport"/></include> | `CssResolveSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.cssStylesheetDetector"/></include> | `CssStylesheetDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javascript.libReferenceSupport"/></include> | `JSLibReferenceSupport` |
{sticky-header="true"}

### intellij.javascript.structureView.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.frameworkSpecificStructureViewExtension"/></include> | `JSFrameworkSpecificStructureExtension` |
{sticky-header="true"}

### intellij.javascript.ultimate.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.jestPackageProvider"/></include> | `JestPackageProvider` |
{sticky-header="true"}

### intellij.sass.backend.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sass.extension"/></include> | `SassExtension` |
{sticky-header="true"}

### org.jetbrains.plugins.node-remote-interpreter

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.node-remote-interpreter.nodeRemoteTargetRunSetupFactory"/></include> | `NodeRemoteTargetRunSetupFactory` |
{sticky-header="true"}


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
