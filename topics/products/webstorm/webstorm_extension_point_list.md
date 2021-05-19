[//]: # (title: WebStorm Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

56 Extension Points (EP) for WebStorm

See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

See [Plugin Extensions](plugin_extensions.md) on how to declare extensions in your plugin.
  
**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is related EP class. 

#### Note Legend

| Icon | Description | Details |
|---|---|---|
| ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | Non-Dynamic Extension Point | Installation/update of plugin requires restart ([Dynamic Plugins](dynamic_plugins.md)) |
| ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) | Experimental API | Implementation annotated with [`@ApiStatus.Experimental`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), API might be altered or removed without prior notice |
| ![Internal API](https://img.shields.io/badge/-Internal_API-red) | Internal API | Implementation annotated with [`@ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), should not be used by 3rd party |
| ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | Project-Level Extension Point | Declared with `area="IDEA_PROJECT"`, can have `Project` as constructor parameter |

## WebStorm

### com.intellij.css

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.css.classOrIdUsagesProvider](https://jb.gg/ipe?extensions=com.intellij.css.classOrIdUsagesProvider) | `CssClassOrIdUsagesProvider` | 
| [com.intellij.css.cssInspectionFilter](https://jb.gg/ipe?extensions=com.intellij.css.cssInspectionFilter) | `CssInspectionFilter` | 
| [com.intellij.css.cssIntentionFilter](https://jb.gg/ipe?extensions=com.intellij.css.cssIntentionFilter) | `CssIntentionFilter` | 
| [com.intellij.css.dialect](https://jb.gg/ipe?extensions=com.intellij.css.dialect) | `CssDialect` | 
| [com.intellij.css.elementDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.css.elementDescriptorProvider) | `CssElementDescriptorProvider` | 
| [com.intellij.css.embeddedCssProvider](https://jb.gg/ipe?extensions=com.intellij.css.embeddedCssProvider) | `EmbeddedCssProvider` | 
| [com.intellij.css.inclusionContext](https://jb.gg/ipe?extensions=com.intellij.css.inclusionContext) | `CssInclusionContext` | 
| [com.intellij.css.structureViewChildrenProvider](https://jb.gg/ipe?extensions=com.intellij.css.structureViewChildrenProvider) | `CssStructureViewElementsProvider` | 
| [com.intellij.css.supportedFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.css.supportedFileTypesProvider) | `CssSupportedFileTypesProvider` | 


### JavaScript

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.JSImplicitElementsIndexFileTypeProvider](https://jb.gg/ipe?extensions=JavaScript.JSImplicitElementsIndexFileTypeProvider) | `JSImplicitElementsIndexFileTypeProvider` | 
| [JavaScript.TypeScriptAnnotatorCheckerProvider](https://jb.gg/ipe?extensions=JavaScript.TypeScriptAnnotatorCheckerProvider) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `TypeScriptAnnotatorCheckerProvider` | 
| [JavaScript.analysisHandlersFactory](https://jb.gg/ipe?extensions=JavaScript.analysisHandlersFactory) | `JSAnalysisHandlersFactory` | 
| [JavaScript.classInheritorsProvider](https://jb.gg/ipe?extensions=JavaScript.classInheritorsProvider) | `JSClassInheritorsProvider` | 
| [JavaScript.completionHelper](https://jb.gg/ipe?extensions=JavaScript.completionHelper) | `JSCompletionHelper` | 
| [JavaScript.dialectSpecificHandlersFactory](https://jb.gg/ipe?extensions=JavaScript.dialectSpecificHandlersFactory) | `JSDialectSpecificHandlersFactory` | 
| [JavaScript.elementScopeProvider](https://jb.gg/ipe?extensions=JavaScript.elementScopeProvider) | `JSElementResolveScopeProvider` | 
| [JavaScript.frameworkIndexingHandler](https://jb.gg/ipe?extensions=JavaScript.frameworkIndexingHandler) | `FrameworkIndexingHandler` | 
| [JavaScript.frameworkSpecificHandler](https://jb.gg/ipe?extensions=JavaScript.frameworkSpecificHandler) | `JSFrameworkSpecificHandler` | 
| [JavaScript.handlersFactory](https://jb.gg/ipe?extensions=JavaScript.handlersFactory) | `JSHandlersFactory` | 
| [JavaScript.iconProvider](https://jb.gg/ipe?extensions=JavaScript.iconProvider) | `JSIconProvider` | 
| [JavaScript.importCandidatesFactory](https://jb.gg/ipe?extensions=JavaScript.importCandidatesFactory) | `CandidatesFactory` | 
| [JavaScript.indexedFileTypeProvider](https://jb.gg/ipe?extensions=JavaScript.indexedFileTypeProvider) | `IndexedFileTypeProvider` | 
| [JavaScript.indexedFilesFilter](https://jb.gg/ipe?extensions=JavaScript.indexedFilesFilter) | `JSIndexedFilesFilterProvider` | 
| [JavaScript.inheritedLanguagesConfigurableProvider](https://jb.gg/ipe?extensions=JavaScript.inheritedLanguagesConfigurableProvider) | `JSInheritedLanguagesConfigurableProvider` | 
| [JavaScript.intentionAndInspectionFilter](https://jb.gg/ipe?extensions=JavaScript.intentionAndInspectionFilter) | `IntentionAndInspectionFilter` | 
| [JavaScript.jestPackageProvider](https://jb.gg/ipe?extensions=JavaScript.jestPackageProvider) | `JestPackageProvider` | 
| [JavaScript.lang.templates](https://jb.gg/ipe?extensions=JavaScript.lang.templates) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) | 
| [JavaScript.languageServiceProvider](https://jb.gg/ipe?extensions=JavaScript.languageServiceProvider) ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `JSLanguageServiceProvider` | 
| [JavaScript.linterSaveAction](https://jb.gg/ipe?extensions=JavaScript.linterSaveAction) | `LinterSaveAction` | 
| [JavaScript.moduleReferenceContributor](https://jb.gg/ipe?extensions=JavaScript.moduleReferenceContributor) | `JSModuleReferenceContributor` | 
| [JavaScript.nodeModulesIndexableFileNamesProvider](https://jb.gg/ipe?extensions=JavaScript.nodeModulesIndexableFileNamesProvider) | `NodeModulesIndexableFileNamesProvider` | 
| [JavaScript.predefinedLibraryProvider](https://jb.gg/ipe?extensions=JavaScript.predefinedLibraryProvider) | `JSPredefinedLibraryProvider` | 
| [JavaScript.resolveHelper](https://jb.gg/ipe?extensions=JavaScript.resolveHelper) | `JSResolveHelper` | 
| [JavaScript.runConfigurationBuilder](https://jb.gg/ipe?extensions=JavaScript.runConfigurationBuilder) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `JSRunConfigurationBuilder` | 
| [JavaScript.smartCompletionContributor](https://jb.gg/ipe?extensions=JavaScript.smartCompletionContributor) | `JSSmartCompletionContributor` | 
| [JavaScript.spellcheckerProvider](https://jb.gg/ipe?extensions=JavaScript.spellcheckerProvider) | `JSSpellcheckerProvider` | 
| [JavaScript.textAttributesKeyProvider](https://jb.gg/ipe?extensions=JavaScript.textAttributesKeyProvider) | `JSTextAttributeKeysProvider` | 
| [JavaScript.tsImportResolver](https://jb.gg/ipe?extensions=JavaScript.tsImportResolver) | `TypeScriptImportsResolverProvider` | 
| [JavaScript.unresolvedReferenceErrorUpdater](https://jb.gg/ipe?extensions=JavaScript.unresolvedReferenceErrorUpdater) ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) | `JSUnresolvedReferenceErrorUpdater` | 
| [JavaScript.webpackLocator](https://jb.gg/ipe?extensions=JavaScript.webpackLocator) | `WebPackConfigLocator` | 
| [NodeJS.runConfigurationLocationFilter](https://jb.gg/ipe?extensions=NodeJS.runConfigurationLocationFilter) | `NodeRunConfigurationLocationFilter` | 
| [com.intellij.JavaScript.linter.descriptor](https://jb.gg/ipe?extensions=com.intellij.JavaScript.linter.descriptor) | `JSLinterDescriptor` | 
| [com.intellij.eslint.ruleMappersFactory](https://jb.gg/ipe?extensions=com.intellij.eslint.ruleMappersFactory) | `EslintRuleMappersFactory` | 
| [com.intellij.javascript.extract.interface.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.extract.interface.extension) | `JSCustomExtractInterfaceHandler` | 
| [com.intellij.javascript.introduce.variable.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.introduce.variable.extension) | `JSCustomIntroduceVariableHandler` | 
| [com.intellij.javascript.json.schema.provider](https://jb.gg/ipe?extensions=com.intellij.javascript.json.schema.provider) | `JsonSchemaInJavaScriptProvider` | 
| [com.intellij.javascript.library.externalDefinitionsContributor](https://jb.gg/ipe?extensions=com.intellij.javascript.library.externalDefinitionsContributor) | `TypeScriptExternalDefinitionsContributor` | 
| [com.intellij.javascript.module.provider](https://jb.gg/ipe?extensions=com.intellij.javascript.module.provider) | `JSModuleConnectionProvider` | 
| [com.intellij.javascript.names.suggester](https://jb.gg/ipe?extensions=com.intellij.javascript.names.suggester) | `JSNamesSuggester` | 
| [com.intellij.javascript.rename.extension](https://jb.gg/ipe?extensions=com.intellij.javascript.rename.extension) | `JSRenameExtension` | 
| [com.intellij.jsbtFileManager](https://jb.gg/ipe?extensions=com.intellij.jsbtFileManager) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `JsbtFileManager` | 
| [com.intellij.jsbtService](https://jb.gg/ipe?extensions=com.intellij.jsbtService) | `JsbtApplicationService` | 
| [com.intellij.lang.typescript.languageService.extension](https://jb.gg/ipe?extensions=com.intellij.lang.typescript.languageService.extension) | `TypeScriptServiceExtension` | 
           
### NodeJS

| Extension Point | Implementation |
|-----------------|----------------|
| [NodeJS.runConfigurationExtension](https://jb.gg/ipe?extensions=NodeJS.runConfigurationExtension) | `NodeJSRunConfigurationExtension` | 


### org.jetbrains.plugins.sass

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.sass.extension](https://jb.gg/ipe?extensions=com.intellij.sass.extension) | `SassExtension` | 

