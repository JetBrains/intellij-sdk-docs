<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /ruby
-->


<snippet id="content">

88 Extension Points and 13 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## RubyMine

### RubyMine – Listeners

| Topic | Listener |
|-------|----------|
| [`RbsLanguageSettingsChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.ruby.rbs.settings.RbsLanguageSettingsChangedListener)  | `RbsLanguageSettingsChangedListener` |
| [`ChefTopics#COOKBOOK`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.chef.sdk.CookbooksListener)  | `CookbooksListener` |
| [`GemManager#GEMSET_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.GemManager.GemSetListener)  | `GemSetListener` |
| [`GemManager#MODULE_GEMS_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.GemManager.ModuleGemsListener)  ![Project-Level][project-level] | `ModuleGemsListener` |
| [`GemRequirementsHolder#GEM_REQUIREMENTS_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.gem.module.GemRequirementsHolder.RequirementsChangedListener)  ![Project-Level][project-level] | `RequirementsChangedListener` |
| [`InflectorService#INFLECTIONS_CHANGED`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.InflectorService.InflectionChanged)  | `InflectionChanged` |
| [`AssetsRegistrationWatcher#ASSETS_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.codeInsight.sprockets.assetsPaths.AssetsRegistrationWatcher.AssetsListener)  | `AssetsListener` |
| [`MigrationParser#MIGRATIONS_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.database.MigrationParser.MigrationListener)  ![Project-Level][project-level] | `MigrationListener` |
| [`RailsPathsChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.rails.facet.configuration.RailsPathsChangedListener)  | `RailsPathsChangedListener` |
| [`RubyRemoteInterpreterManager#RUBY_REMOTE_SDK_TRANSFER_LISTENER_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.remote.RubyRemoteSdkTransferListener)  | `RubyRemoteSdkTransferListener` |
| [`RequireSetChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.cache.RequiresIndexExtension.RequireSetChangedListener)  | `RequireSetChangedListener` |
| [`RubySdkType#SDK_PATHS_INITIALIZED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.ruby.sdk.RubySdkType.SdkPathsInitializedListener)  | `SdkPathsInitializedListener` |
| [`RVMSupportUtil#RVM_GEMSET_ADDED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.ruby.version.management.rvm.RVMSupportUtil.RVMGemsetListener)  | `RVMGemsetListener` |


### intellij.ruby.coverage.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.coverage.deserializer"/></include> | `RubyCoverageDeserializationProvider` |

### intellij.ruby.frontback.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.formatter.additionalSpacingProcessor"/></include> | `RubyAdditionalSpacingProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.languageLevelProvider"/></include> | `LanguageLevelProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyBuilderFactory"/></include> | `RubyBuilderFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.templates.elements.provider"/></include> | `TemplateElementsProvider` |

### ruby-core.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.associationFactory"/></include> | `AssociationFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.attributeProvider"/></include> | `AttributeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.autoImportProvider"/></include> | `RubyAutoImportProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.callNavigationOffsetProvider"/></include> | `StructureCallNavigationOffsetProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.callTypeProvider"/></include> | `RubyCallTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.completionProvider"/></include> | `RubyCompletionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.fileLocationProvider"/></include> | `FileLocationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.gem.module.gemInfrastructure"/></include> | `GemInfrastructure` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.gemScannerSuppressor"/></include> | `GemScannerSuppressor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.implicitGemProvider"/></include> | `ImplicitRequireGemProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.implicitRequireProvider"/></include> | `ImplicitRequireProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.includeExtendReceiverFqnProvider"/></include> | `RubyIncludeExtendReceiverFqnProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.methodMissingProvider"/></include> | `MethodMissingProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.model.psiSymbolDeclarationsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.moduleGemDependencyProvider"/></include> | `ModuleGemDependencyProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.moduleGemProvider"/></include> | `ModuleGemProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.overriddenMethodGenerator"/></include> | `OverriddenMethodGenerator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.paramDefProvider"/></include> | `ParamDefProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.psiElementFactory"/></include> | `PsiElementFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rails.viewFileTemplateProvider"/></include> | `RailsViewFileTemplateProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.renameHelper"/></include> | `RubyRenameHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.routesProvider"/></include> | `RubyRoutesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.coercibleExpressionTypeProvider"/></include> | `RubyCoercibleExpressionTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedArgumentNilabilityProvider"/></include> | `RubyExpectedArgumentNilabilityProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedArgumentTypeProvider"/></include> | `RubyExpectedArgumentTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedConstantTypeProvider"/></include> | `RubyExpectedConstantTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedGlobalVariableTypeProvider"/></include> | `RubyExpectedGlobalVariableTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedMethodSignatureProvider"/></include> | `RubyExpectedMethodSignatureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedParameterTypeProvider"/></include> | `RubyExpectedParameterTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedReturnTypeProvider"/></include> | `RubyExpectedReturnTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.expectedVariableTypeProvider"/></include> | `RubyExpectedVariableTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.findUsagesSecondaryElementsProvider"/></include> | `RubyFindUsagesSecondaryElementsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.run.runEnvironmentProvider"/></include> | `RunEnvironmentProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.run.testFrameworkConfigurator"/></include> | `TestFrameworkConfigurator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyElementNameAndDescriptionProvider"/></include> | `RubyElementNameAndDescriptionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyLocalVariablesProvider"/></include> | `RubyLocalVariablesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyParamDefSearchTextProvider"/></include> | `RubyParamDefSearchTextProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyParameterInfoDelegateProvider"/></include> | `RubyParameterInfoDelegateProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyRenameProcessor"/></include> | `RenameProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubySdkConfigurator"/></include> | `RubySdkConfigurator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubySdkRefresher"/></include> | `SdkRefresher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubySuperMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubySupportProvider"/></include> | `RubySupportProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyTypeProvider"/></include> | `RubyTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.run.console.filter"/></include> | `RubyConsoleFilterProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.runnableScriptFilter"/></include> | `RunnableScriptFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.sdkConfigFactory"/></include> | `Factory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.superMethodInfoProvider"/></include> | `RubySuperMethodInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.symbolMixinsProvider"/></include> | `RubySymbolMixinsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.symbolProvider"/></include> | `RubySymbolProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.symbolicTypeInferenceProvider"/></include> | `SymbolicTypeInferenceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.templates.integration"/></include> | `TemplateIntegration` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.versionManagerHandler"/></include> | `RubyVersionManagerHandler` |

### ruby-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ruby.projectStructure.sourceRootEditHandler"/></include> | `RubyModuleSourceRootEditProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.breadcrumbsCustomizer"/></include> | `RubyBreadcrumbsCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.debug.infoProvider"/></include> | `ContextInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.gem.detector"/></include> | `GemDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.gemFacetEditorTab"/></include> | `GemFacetEditorTabFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.generatorConfigurator"/></include> | `GeneratorActionConfigurator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.i18n.i18nProvider"/></include> | `I18nProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.inflectionsProvider"/></include> | `RubyInflectionsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rails.assetsPathsProvider"/></include> | `SprocketAssetsPathProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rails.sprocketsDirectiveContextProvider"/></include> | `SprocketsDirectiveContextProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rails.viewFileTypesProvider"/></include> | `RailsViewFileTypesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.railsFacetEditorTab"/></include> | `FacetEditorTabFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.railsModelFieldsProvider"/></include> | `RailsModelFieldsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.railsNavigateFrom"/></include> | `RailsNavigateFromProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.railsSchemaParser"/></include> | `RailsSchemaParser` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rake.rakeRunCommandLineModifierProvider"/></include> | `RakeRunCommandLineModifierProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rake.runConfigurationSettingsFactory"/></include> | `RakeRunConfigurationSettingsFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rerunFailedTestsActionProvider"/></include> | `RubyRerunFailedTestsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.associatedDeclarationProvider"/></include> ![DumbAware][dumb-aware] | `RubyAssociatedDeclarationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.run.configuration.debugger.rubyDebugHelperFactory"/></include> | `RubyDebugHelperFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.topLevelSymbolProvider"/></include> | `RubyTopLevelSymbolProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.ruby.typeSignatureProvider"/></include> ![DumbAware][dumb-aware] | `RubyTypeSignatureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyFileStructureProvider"/></include> | `RubyFileStructureViewProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyInsertHandlerProvider"/></include> | `RubyInsertHandlerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.rubyMethodTypeDocPresentationProvider"/></include> | `RubyMethodTypeDocPresentationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.runConfigurationExtension"/></include> | `RubyRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.structureViewCustomizer"/></include> | `RubyStructureViewCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.testing.rspec.rspecContextNameProvider"/></include> | `RSpecContextNameProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.ruby.testing.rspec.rspecContextSymbolProvider"/></include> | `RSpecContextSymbolProvider` |

### ruby-rbs.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.ruby.rbs.containerHierarchyMapper"/></include> | `RbsContainerHierarchyMapper` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
