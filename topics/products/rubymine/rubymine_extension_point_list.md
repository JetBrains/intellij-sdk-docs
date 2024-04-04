<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /ruby/ -->

# RubyMine Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for RubyMine.</link-summary>

80 Extension Points and 12 Listeners for RubyMine

See [](extension_point_list.md) for IntelliJ Platform.

<include from="snippets.md" element-id="ep_list_legend"/>

## RubyMine

### RubyMine - Listeners

| Topic | Listener |
|-------|----------|
| [ChefTopics#COOKBOOK](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.chef.sdk.CookbooksListener)  | `CookbooksListener` |
| [GemManager#GEMSET_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.GemManager.GemSetListener)  | `GemSetListener` |
| [GemManager#MODULE_GEMS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.GemManager.ModuleGemsListener)  ![Project-Level][project-level] | `ModuleGemsListener` |
| [GemRequirementsHolder#GEM_REQUIREMENTS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.module.GemRequirementsHolder.RequirementsChangedListener)  ![Project-Level][project-level] | `RequirementsChangedListener` |
| [InflectorService#INFLECTIONS_CHANGED](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.InflectorService.InflectionChanged)  | `InflectionChanged` |
| [AssetsRegistrationWatcher#ASSETS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.codeInsight.sprockets.assetsPaths.AssetsRegistrationWatcher.AssetsListener)  | `AssetsListener` |
| [MigrationParser#MIGRATIONS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.database.MigrationParser.MigrationListener)  ![Project-Level][project-level] | `MigrationListener` |
| [RailsPathsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.facet.configuration.RailsPathsChangedListener)  | `RailsPathsChangedListener` |
| [RubyRemoteInterpreterManager#RUBY_REMOTE_SDK_TRANSFER_LISTENER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.remote.RubyRemoteSdkTransferListener)  | `RubyRemoteSdkTransferListener` |
| [RequireSetChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.cache.RequiresIndexExtension.RequireSetChangedListener)  | `RequireSetChangedListener` |
| [RubySdkType#SDK_PATHS_INITIALIZED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.ruby.sdk.RubySdkType.SdkPathsInitializedListener)  | `SdkPathsInitializedListener` |
| [RVMSupportUtil#RVM_GEMSET_ADDED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.version.management.rvm.RVMSupportUtil.RVMGemsetListener)  | `RVMGemsetListener` |


### intellij.ruby.coverage.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.ruby.coverage.deserializer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.coverage.deserializer) | `RubyCoverageDeserializationProvider` |

### ruby-core.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.ruby.associationFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.associationFactory) | `AssociationFactory` |
| [org.jetbrains.plugins.ruby.attributeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.attributeProvider) | `AttributeProvider` |
| [org.jetbrains.plugins.ruby.autoImportProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.autoImportProvider) | `RubyAutoImportProvider` |
| [org.jetbrains.plugins.ruby.callNavigationOffsetProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.callNavigationOffsetProvider) | `StructureCallNavigationOffsetProvider` |
| [org.jetbrains.plugins.ruby.callTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.callTypeProvider) | `RubyCallTypeProvider` |
| [org.jetbrains.plugins.ruby.completionProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.completionProvider) | `RubyCompletionProvider` |
| [org.jetbrains.plugins.ruby.fileLocationProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.fileLocationProvider) | `FileLocationProvider` |
| [org.jetbrains.plugins.ruby.gem.module.gemInfrastructure](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.gem.module.gemInfrastructure) | `GemInfrastructure` |
| [org.jetbrains.plugins.ruby.gemScannerSuppressor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.gemScannerSuppressor) | `GemScannerSuppressor` |
| [org.jetbrains.plugins.ruby.implicitGemProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.implicitGemProvider) | `ImplicitRequireGemProvider` |
| [org.jetbrains.plugins.ruby.implicitRequireProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.implicitRequireProvider) | `ImplicitRequireProvider` |
| [org.jetbrains.plugins.ruby.includeExtendReceiverFqnProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.includeExtendReceiverFqnProvider) | `RubyIncludeExtendReceiverFqnProvider` |
| [org.jetbrains.plugins.ruby.methodMissingProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.methodMissingProvider) | `MethodMissingProvider` |
| [org.jetbrains.plugins.ruby.model.psiSymbolDeclarationsSearch](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.model.psiSymbolDeclarationsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [org.jetbrains.plugins.ruby.moduleGemDependencyProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.moduleGemDependencyProvider) | `ModuleGemDependencyProvider` |
| [org.jetbrains.plugins.ruby.moduleGemProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.moduleGemProvider) | `ModuleGemProvider` |
| [org.jetbrains.plugins.ruby.overriddenMethodGenerator](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.overriddenMethodGenerator) | `OverriddenMethodGenerator` |
| [org.jetbrains.plugins.ruby.paramDefProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.paramDefProvider) | `ParamDefProvider` |
| [org.jetbrains.plugins.ruby.rails.viewFileTemplateProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rails.viewFileTemplateProvider) | `RailsViewFileTemplateProvider` |
| [org.jetbrains.plugins.ruby.renameHelper](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.renameHelper) | `RubyRenameHelper` |
| [org.jetbrains.plugins.ruby.routesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.routesProvider) | `RubyRoutesProvider` |
| [org.jetbrains.plugins.ruby.ruby.coercibleExpressionTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.coercibleExpressionTypeProvider) | `RubyCoercibleExpressionTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedArgumentTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedArgumentTypeProvider) | `RubyExpectedArgumentTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedConstantTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedConstantTypeProvider) | `RubyExpectedConstantTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedGlobalVariableTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedGlobalVariableTypeProvider) | `RubyExpectedGlobalVariableTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedParameterTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedParameterTypeProvider) | `RubyExpectedParameterTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedReturnTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedReturnTypeProvider) | `RubyExpectedReturnTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.expectedVariableTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.expectedVariableTypeProvider) | `RubyExpectedVariableTypeProvider` |
| [org.jetbrains.plugins.ruby.ruby.findUsagesSecondaryElementsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.findUsagesSecondaryElementsProvider) | `RubyFindUsagesSecondaryElementsProvider` |
| [org.jetbrains.plugins.ruby.ruby.run.runEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.run.runEnvironmentProvider) | `RunEnvironmentProvider` |
| [org.jetbrains.plugins.ruby.ruby.run.testFrameworkConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.run.testFrameworkConfigurator) | `TestFrameworkConfigurator` |
| [org.jetbrains.plugins.ruby.rubyElementNameAndDescriptionProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyElementNameAndDescriptionProvider) | `RubyElementNameAndDescriptionProvider` |
| [org.jetbrains.plugins.ruby.rubyLocalVariablesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyLocalVariablesProvider) | `RubyLocalVariablesProvider` |
| [org.jetbrains.plugins.ruby.rubyParamDefSearchTextProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyParamDefSearchTextProvider) | `RubyParamDefSearchTextProvider` |
| [org.jetbrains.plugins.ruby.rubyParameterInfoDelegateProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyParameterInfoDelegateProvider) | `RubyParameterInfoDelegateProvider` |
| [org.jetbrains.plugins.ruby.rubyRenameProcessor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyRenameProcessor) | `RenameProcessor` |
| [org.jetbrains.plugins.ruby.rubySdkConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubySdkConfigurator) | `RubySdkConfigurator` |
| [org.jetbrains.plugins.ruby.rubySdkRefresher](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubySdkRefresher) | `SdkRefresher` |
| [org.jetbrains.plugins.ruby.rubySuperMethodsSearch](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubySuperMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [org.jetbrains.plugins.ruby.rubySupportProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubySupportProvider) | `RubySupportProvider` |
| [org.jetbrains.plugins.ruby.rubyTypeProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyTypeProvider) | `RubyTypeProvider` |
| [org.jetbrains.plugins.ruby.run.console.filter](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.run.console.filter) | `RubyConsoleFilterProvider` |
| [org.jetbrains.plugins.ruby.runnableScriptFilter](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.runnableScriptFilter) | `RunnableScriptFilter` |
| [org.jetbrains.plugins.ruby.superMethodInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.superMethodInfoProvider) | `RubySuperMethodInfoProvider` |
| [org.jetbrains.plugins.ruby.symbolMixinsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.symbolMixinsProvider) | `RubySymbolMixinsProvider` |
| [org.jetbrains.plugins.ruby.symbolProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.symbolProvider) | `RubySymbolProvider` |
| [org.jetbrains.plugins.ruby.symbolicTypeInferenceProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.symbolicTypeInferenceProvider) | `SymbolicTypeInferenceProvider` |
| [org.jetbrains.plugins.ruby.templates.integration](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.templates.integration) | `TemplateIntegration` |
| [org.jetbrains.plugins.ruby.versionManagerHandler](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.versionManagerHandler) | `RubyVersionManagerHandler` |

### ruby-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ruby.projectStructure.sourceRootEditHandler](https://jb.gg/ipe?extensions=com.intellij.ruby.projectStructure.sourceRootEditHandler) | `RubyModuleSourceRootEditProvider` |
| [org.jetbrains.plugins.ruby.breadcrumbsCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.breadcrumbsCustomizer) | `RubyBreadcrumbsCustomizer` |
| [org.jetbrains.plugins.ruby.debug.infoProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.debug.infoProvider) | `ContextInfoProvider` |
| [org.jetbrains.plugins.ruby.gem.detector](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.gem.detector) | `GemDetector` |
| [org.jetbrains.plugins.ruby.gemFacetEditorTab](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.gemFacetEditorTab) | `GemFacetEditorTabFactory` |
| [org.jetbrains.plugins.ruby.generatorConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.generatorConfigurator) | `GeneratorActionConfigurator` |
| [org.jetbrains.plugins.ruby.i18n.i18nProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.i18n.i18nProvider) | `I18nProvider` |
| [org.jetbrains.plugins.ruby.inflectionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.inflectionsProvider) | `RubyInflectionsProvider` |
| [org.jetbrains.plugins.ruby.methodTypeInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.methodTypeInfoProvider) | `RubyMethodTypeInfoProvider` |
| [org.jetbrains.plugins.ruby.rails.assetsPathsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rails.assetsPathsProvider) | `SprocketAssetsPathProvider` |
| [org.jetbrains.plugins.ruby.rails.sprocketsDirectiveContextProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rails.sprocketsDirectiveContextProvider) | `SprocketsDirectiveContextProvider` |
| [org.jetbrains.plugins.ruby.rails.viewFileTypesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rails.viewFileTypesProvider) | `RailsViewFileTypesProvider` |
| [org.jetbrains.plugins.ruby.railsFacetEditorTab](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.railsFacetEditorTab) | `FacetEditorTabFactory` |
| [org.jetbrains.plugins.ruby.railsModelFieldsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.railsModelFieldsProvider) | `RailsModelFieldsProvider` |
| [org.jetbrains.plugins.ruby.railsNavigateFrom](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.railsNavigateFrom) | `RailsNavigateFromProvider` |
| [org.jetbrains.plugins.ruby.railsSchemaParser](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.railsSchemaParser) | `RailsSchemaParser` |
| [org.jetbrains.plugins.ruby.rake.rakeRunCommandLineModifierProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rake.rakeRunCommandLineModifierProvider) | `RakeRunCommandLineModifierProvider` |
| [org.jetbrains.plugins.ruby.rake.runConfigurationSettingsFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rake.runConfigurationSettingsFactory) | `RakeRunConfigurationSettingsFactory` |
| [org.jetbrains.plugins.ruby.rerunFailedTestsActionProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rerunFailedTestsActionProvider) | `RubyRerunFailedTestsProvider` |
| [org.jetbrains.plugins.ruby.ruby.associatedDeclarationProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.associatedDeclarationProvider) ![DumbAware][dumb-aware] | `RubyAssociatedDeclarationProvider` |
| [org.jetbrains.plugins.ruby.ruby.run.configuration.debugger.rubyDebugHelperFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.run.configuration.debugger.rubyDebugHelperFactory) | `RubyDebugHelperFactory` |
| [org.jetbrains.plugins.ruby.ruby.topLevelSymbolProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.topLevelSymbolProvider) | `RubyTopLevelSymbolProvider` |
| [org.jetbrains.plugins.ruby.ruby.typeSignatureProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.ruby.typeSignatureProvider) ![DumbAware][dumb-aware] | `RubyTypeSignatureProvider` |
| [org.jetbrains.plugins.ruby.rubyFileStructureProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyFileStructureProvider) | `RubyFileStructureViewProvider` |
| [org.jetbrains.plugins.ruby.rubyInsertHandlerProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.rubyInsertHandlerProvider) | `RubyInsertHandlerProvider` |
| [org.jetbrains.plugins.ruby.runConfigurationExtension](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.runConfigurationExtension) | `RubyRunConfigurationExtension` |
| [org.jetbrains.plugins.ruby.structureViewCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.structureViewCustomizer) | `RubyStructureViewCustomizer` |
| [org.jetbrains.plugins.ruby.testing.rspec.rspecContextNameProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.testing.rspec.rspecContextNameProvider) | `RSpecContextNameProvider` |
| [org.jetbrains.plugins.ruby.testing.rspec.rspecContextSymbolProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.ruby.testing.rspec.rspecContextSymbolProvider) | `RSpecContextSymbolProvider` |

### ruby-rbs.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.lang.ruby.rbs.containerHierarchyMapper](https://jb.gg/ipe?extensions=com.intellij.lang.ruby.rbs.containerHierarchyMapper) | `RbsContainerHierarchyMapper` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
