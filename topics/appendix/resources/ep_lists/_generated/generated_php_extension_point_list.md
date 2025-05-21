<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /phpstorm
-->


<snippet id="content">

63 Extension Points and 11 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## PhpStorm

### PhpStorm – Listeners

| Topic | Listener |
|-------|----------|
| [`ComposerInstalledPackagesService#PACKAGE_MANAGER_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.composer.actions.update.ComposerInstalledPackagesService.ComposerUpdateListener)  ![Project-Level][project-level] | `ComposerUpdateListener` |
| [`RepositoriesComposerConfig#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.composer.json.cache.ComposerRepositoriesChangedListener)  | `ComposerRepositoriesChangedListener` |
| [`StateChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.config.PhpProjectSharedConfiguration.StateChangedListener)  | `StateChangedListener` |
| [`StateChangedListener#LANGUAGE_LEVEL_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.config.PhpProjectSharedConfiguration.StateChangedListener)  | `StateChangedListener` |
| [`PhpProjectWorkspaceConfiguration#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.config.PhpProjectWorkspaceConfigurationListener)  | `PhpProjectWorkspaceConfigurationListener` |
| [`DefaultStubsPathListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.config.PhpRuntimeConfiguration.DefaultStubsPathListener)  | `DefaultStubsPathListener` |
| [`PhpInterpreterConflictResolveListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.config.interpreters.PhpInterpretersManagerImpl.PhpInterpreterConflictResolveListener)  | `PhpInterpreterConflictResolveListener` |
| [`StateChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.debug.listener.PhpDebugExternalConnectionsAccepter.StateChangedListener)  | `StateChangedListener` |
| [`RectorChangesListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.rector.RectorChangesListener)  | `RectorChangesListener` |
| [`PhpRemoteInterpreterChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.php.remote.interpreter.ui.PhpRemoteInterpreterConfigurationForm.PhpRemoteInterpreterChangedListener)  | `PhpRemoteInterpreterChangedListener` |
| [`CustomExpectationNotifier.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.pestphp.pest.features.customExpectations.CustomExpectationNotifier)  ![Project-Level][project-level] | `CustomExpectationNotifier` |


### com.intellij.phing

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.phing.phpFileDescriptionProvider"/></include> ![Non-Dynamic][non-dynamic] | `PhingPhpFileDescriptionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.phing.propertyFilesManager"/></include> ![Non-Dynamic][non-dynamic] | `PropertyFilesManager` |

### com.intellij.php.psalm

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.Psalm.PsalmConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `PsalmConfigurationProvider` |

### com.intellij.php.tools.quality.phpstan

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.PhpStan.PhpStanConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `PhpStanConfigurationProvider` |

### com.jetbrains.php

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.php.debug.template.configurable"/></include> ![Internal][internal] ![Project-Level][project-level] | `PhpTemplateDebugConfigurable` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.php.debug.templateLanguage"/></include> ![Internal][internal] | `PhpTemplateLanguagePathMapper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.php.typeProvider2"/></include> ![Deprecated][deprecated] | `PhpTypeProvider2` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.phpDeadCode"/></include> | [`EntryPoint`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.phpRunConfigurationExtension"/></include> | `PhpRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.arrayShapesProvider"/></include> | `PhpArrayShapesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.baseLexerProvider"/></include> ![Internal][internal] | `PhpBaseLexerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.classAliasProvider"/></include> | `PhpClassAliasProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.composer.execProvider"/></include> | `ComposerExecutionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.composerConfigClient"/></include> ![Internal][internal] | `ComposerConfigClient` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.config.customFormatFunctionsProvider"/></include> | `PhpCustomFormatFunctionsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.config.interpreterFormProvider"/></include> | `PhpInterpreterFormProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.config.interpreters.PhpInterpretersStateListener"/></include> | `PhpInterpretersStateListener` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.coreMethodProvider"/></include> ![Internal][internal] | `PhpCoreHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.customFunctionIndex"/></include> | `PhpCustomFunctionIndex` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.customFunctionPredicate"/></include> ![Internal][internal] | `PhpCustomFunctionPredicateIndex` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.customTemplatesNamesProvider"/></include> ![Experimental][experimental] | `PhpCustomTemplatesNamesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.debug.mapping.localPathFixer"/></include> | `PhpLocalPathFixer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.deprecationFixesProvider"/></include> | `PhpDeprecationQuickFixesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.deprecationProvider"/></include> | `PhpDeprecationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.dfaStateFromAssertionProvider"/></include> ![Internal][internal] | `PhpDfaStateFromAssertionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.docPrefixProvider"/></include> ![Internal][internal] | `PhpDocPrefixProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.docTagValuesStubProvider"/></include> | `PhpCustomDocTagValuesStubProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.expressionClassNamesProvider"/></include> ![Internal][internal] | `PhpExpressionClassNamesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.externalUsagesSearcher"/></include> | `PhpExternalUsagesSearcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.frameworkProjectConfigurableProvider"/></include> | `PhpFrameworkConfigurableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.frameworkUsageProvider"/></include> | `PhpFrameworkUsageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.includedPathsContributor"/></include> | `PhpIncludedPathsContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.injectionExternalFragmentSubstProvider"/></include> ![Project-Level][project-level] | `PhpInjectionExternalFragmentSubstProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.keyTypeProvider"/></include> ![Internal][internal] | `PhpKeyTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.libraryRoot"/></include> ![Internal][internal] | `PhpLibraryRootProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.magicMethodProvider"/></include> ![Internal][internal] | `PhpMagicHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.metaSignatureResolver"/></include> ![Internal][internal] | `PhpMetaSignatureResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.metaTableProvider"/></include> ![Internal][internal] | `PhpMetaTableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.noReturnProvider"/></include> | `PhpNoReturnProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.openSettingsProvider"/></include> | `Settings` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.phpunit.phpUnitSettingsLoader"/></include> ![Internal][internal] | `PhpUnitSettingsLoader` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.predefinedVariableProvider"/></include> | `PhpPredefinedVariableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.referenceResolver2"/></include> | `PhpMultipleDeclarationFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.referenceScopeExtension"/></include> ![Experimental][experimental] | `PhpReferenceScopeExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.relatedToPhpFilesContributor"/></include> | `RelatedToPhpFilesContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.remote.remoteInterpreterManager"/></include> | `PhpRemoteInterpreterManager` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.templateLanguageHighlightingExtension"/></include> ![Internal][internal] | `TemplateLanguageBackgroundColorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.testFramework.phpTestOldConfigHolder"/></include> ![Deprecated][deprecated] ![Internal][internal] | `PhpTestFrameworkOldConfigHolder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.testFrameworkType"/></include> | `PhpTestFrameworkType` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.laravelPint.laravelPintConfigurationProvider"/></include> | `LaravelPintConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.messDetector.messDetectorConfigurationProvider"/></include> | `MessDetectorConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.phpCSFixer.phpCSFixerConfigurationProvider"/></include> | `PhpCSFixerConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.phpcs.phpCSConfigurationProvider"/></include> | `PhpCSConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.tools.quality.type"/></include> | `QualityToolType` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.typeProvider3"/></include> ![Deprecated][deprecated] | `PhpTypeProvider3` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.typeProvider4"/></include> | `PhpTypeProvider4` |

### com.jetbrains.php.behat

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.behat.gherkinContextProvider"/></include> | `ContextInterfaceProvider` |

### com.jetbrains.php.blade

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.blade.bladeDirectiveContributor"/></include> ![Experimental][experimental] | `BladeDirectiveContributor` |

### com.jetbrains.php.framework

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.framework.descriptionProvider"/></include> ![Internal][internal] | `FrameworkDescriptionProvider` |

### intellij.php.frontback.impl.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.php.docTagParserExtension"/></include> ![Internal][internal] | `PhpDocTagParser` |

### phpstorm-remote-interpreter-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.remote.interpreter.ui.customConfigProvider"/></include> | `PhpProjectConfigComponentProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.remote.phpHelperScriptProvider"/></include> | `PhpHelperScriptProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.php.remote.remoteProcessManager"/></include> | `PhpRemoteProcessManager` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
