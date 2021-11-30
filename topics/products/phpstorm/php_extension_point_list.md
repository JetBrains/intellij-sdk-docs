[//]: # (title: PHP Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

54 Extension Points (EP) and 7 Listeners for PHP

See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## PhpStorm

### PhpStorm - Listeners

| Topic                                               | Listener                                   |
|-----------------------------------------------------|--------------------------------------------|
| `StateChangedListener#TOPIC`                        | `StateChangedListener`                     |
| `StateChangedListener#LANGUAGE_LEVEL_CHANGED_TOPIC` | `StateChangedListener`                     |
| `PhpProjectWorkspaceConfiguration#TOPIC`            | `PhpProjectWorkspaceConfigurationListener` |
| `DefaultStubsPathListener#TOPIC`                    | `DefaultStubsPathListener`                 |
| `PhpInterpreterConflictResolveListener#TOPIC`       | `PhpInterpreterConflictResolveListener`    |
| `StateChangedListener#TOPIC`                        | `StateChangedListener`                     |
| `PhpRemoteInterpreterChangedListener#TOPIC`         | `PhpRemoteInterpreterChangedListener`      |

### com.intellij.phing

| Extension Point                                                                                                                                         | Implementation                    |
|---------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| [com.intellij.phing.phpFileDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.phing.phpFileDescriptionProvider) ![Non-Dynamic][non-dynamic] | `PhingPhpFileDescriptionProvider` |
| [com.intellij.phing.propertyFilesManager](https://jb.gg/ipe?extensions=com.intellij.phing.propertyFilesManager) ![Non-Dynamic][non-dynamic]             | `PropertyFilesManager`            |

### com.intellij.php.psalm

| Extension Point                                                                                                                                                                               | Implementation               |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------|
| [com.jetbrains.php.tools.quality.Psalm.PsalmConfigurationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.Psalm.PsalmConfigurationProvider) ![Non-Dynamic][non-dynamic] | `PsalmConfigurationProvider` |

### com.intellij.php.tools.quality.phpstan

| Extension Point                                                                                                                                                                                       | Implementation                 |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------|
| [com.jetbrains.php.tools.quality.PhpStan.PhpStanConfigurationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.PhpStan.PhpStanConfigurationProvider) ![Non-Dynamic][non-dynamic] | `PhpStanConfigurationProvider` |

### com.jetbrains.php

| Extension Point                                                                                                                                                                               | Implementation                                                                                              |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| [com.intellij.php.debug.template.configurable](https://jb.gg/ipe?extensions=com.intellij.php.debug.template.configurable) ![Project-Level][project-level]                                     | `PhpTemplateDebugConfigurable`                                                                              |
| [com.intellij.php.debug.templateLanguage](https://jb.gg/ipe?extensions=com.intellij.php.debug.templateLanguage)                                                                               | `PhpTemplateLanguagePathMapper`                                                                             |
| [com.intellij.php.typeProvider2](https://jb.gg/ipe?extensions=com.intellij.php.typeProvider2) ![Deprecated][deprecated]                                                                       | `PhpTypeProvider2`                                                                                          |
| [com.intellij.phpDeadCode](https://jb.gg/ipe?extensions=com.intellij.phpDeadCode)                                                                                                             | [`EntryPoint`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| [com.jetbrains.php.arrayShapesProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.arrayShapesProvider)                                                                                   | `PhpArrayShapesProvider`                                                                                    |
| [com.jetbrains.php.classAliasProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.classAliasProvider)                                                                                     | `PhpClassAliasProvider`                                                                                     |
| [com.jetbrains.php.composer.execProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.composer.execProvider)                                                                               | `ComposerExecutionProvider`                                                                                 |
| [com.jetbrains.php.composerConfigClient](https://jb.gg/ipe?extensions=com.jetbrains.php.composerConfigClient)                                                                                 | `ComposerConfigClient`                                                                                      |
| [com.jetbrains.php.config.customFormatFunctionsProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.config.customFormatFunctionsProvider)                                                 | `PhpCustomFormatFunctionsProvider`                                                                          |
| [com.jetbrains.php.config.interpreterFormProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.config.interpreterFormProvider)                                                             | `PhpInterpreterFormProvider`                                                                                |
| [com.jetbrains.php.config.interpreters.PhpInterpretersStateListener](https://jb.gg/ipe?extensions=com.jetbrains.php.config.interpreters.PhpInterpretersStateListener)                         | `PhpInterpretersStateListener`                                                                              |
| [com.jetbrains.php.coreMethodProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.coreMethodProvider)                                                                                     | `PhpCoreHandler`                                                                                            |
| [com.jetbrains.php.customFunctionIndex](https://jb.gg/ipe?extensions=com.jetbrains.php.customFunctionIndex)                                                                                   | `PhpCustomFunctionIndex`                                                                                    |
| [com.jetbrains.php.customFunctionPredicate](https://jb.gg/ipe?extensions=com.jetbrains.php.customFunctionPredicate) ![Internal API][internal]                                                 | `PhpCustomFunctionPredicateIndex`                                                                           |
| [com.jetbrains.php.debug.mapping.localPathFixer](https://jb.gg/ipe?extensions=com.jetbrains.php.debug.mapping.localPathFixer)                                                                 | `PhpLocalPathFixer`                                                                                         |
| [com.jetbrains.php.deprecationFixesProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.deprecationFixesProvider)                                                                         | `PhpDeprecationQuickFixesProvider`                                                                          |
| [com.jetbrains.php.deprecationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.deprecationProvider)                                                                                   | `PhpDeprecationProvider`                                                                                    |
| [com.jetbrains.php.docPrefixProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.docPrefixProvider) ![Internal API][internal]                                                             | `PhpDocPrefixProvider`                                                                                      |
| [com.jetbrains.php.docTagParserExtension](https://jb.gg/ipe?extensions=com.jetbrains.php.docTagParserExtension)                                                                               | `PhpDocTagParser`                                                                                           |
| [com.jetbrains.php.docTagValuesStubProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.docTagValuesStubProvider)                                                                         | `PhpCustomDocTagValuesStubProvider`                                                                         |
| [com.jetbrains.php.externalUsagesSearcher](https://jb.gg/ipe?extensions=com.jetbrains.php.externalUsagesSearcher)                                                                             | `PhpExternalUsagesSearcher`                                                                                 |
| [com.jetbrains.php.frameworkProjectConfigurableProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.frameworkProjectConfigurableProvider)                                                 | `PhpFrameworkConfigurableProvider`                                                                          |
| [com.jetbrains.php.frameworkUsageProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.frameworkUsageProvider)                                                                             | `PhpFrameworkUsageProvider`                                                                                 |
| [com.jetbrains.php.injectionExternalFragmentSubstProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.injectionExternalFragmentSubstProvider) ![Project-Level][project-level]             | `PhpInjectionExternalFragmentSubstProvider`                                                                 |
| [com.jetbrains.php.keyTypeProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.keyTypeProvider) ![Experimental API][experimental]                                                         | `PhpKeyTypeProvider`                                                                                        |
| [com.jetbrains.php.libraryRoot](https://jb.gg/ipe?extensions=com.jetbrains.php.libraryRoot) ![Internal API][internal]                                                                         | `PhpLibraryRootProvider`                                                                                    |
| [com.jetbrains.php.magicMethodProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.magicMethodProvider)                                                                                   | `PhpMagicHandler`                                                                                           |
| [com.jetbrains.php.metaSignatureResolver](https://jb.gg/ipe?extensions=com.jetbrains.php.metaSignatureResolver) ![Internal API][internal]                                                     | `PhpMetaSignatureResolver`                                                                                  |
| [com.jetbrains.php.metaTableProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.metaTableProvider) ![Internal API][internal]                                                             | `PhpMetaTableProvider`                                                                                      |
| [com.jetbrains.php.noReturnProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.noReturnProvider) ![Experimental API][experimental]                                                       | `PhpNoReturnProvider`                                                                                       |
| [com.jetbrains.php.openSettingsProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.openSettingsProvider)                                                                                 | `Settings`                                                                                                  |
| [com.jetbrains.php.phpunit.phpUnitSettingsLoader](https://jb.gg/ipe?extensions=com.jetbrains.php.phpunit.phpUnitSettingsLoader)                                                               | `PhpUnitSettingsLoader`                                                                                     |
| [com.jetbrains.php.predefinedVariableProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.predefinedVariableProvider)                                                                     | `PhpPredefinedVariableProvider`                                                                             |
| [com.jetbrains.php.referenceResolver2](https://jb.gg/ipe?extensions=com.jetbrains.php.referenceResolver2)                                                                                     | `PhpMultipleDeclarationFilter`                                                                              |
| [com.jetbrains.php.relatedToPhpFilesContributor](https://jb.gg/ipe?extensions=com.jetbrains.php.relatedToPhpFilesContributor)                                                                 | `RelatedToPhpFilesContributor`                                                                              |
| [com.jetbrains.php.remote.remoteInterpreterManager](https://jb.gg/ipe?extensions=com.jetbrains.php.remote.remoteInterpreterManager)                                                           | `PhpRemoteInterpreterManager`                                                                               |
| [com.jetbrains.php.testFramework.phpTestOldConfigHolder](https://jb.gg/ipe?extensions=com.jetbrains.php.testFramework.phpTestOldConfigHolder) ![Deprecated][deprecated]                       | `PhpTestFrameworkOldConfigHolder`                                                                           |
| [com.jetbrains.php.testFrameworkType](https://jb.gg/ipe?extensions=com.jetbrains.php.testFrameworkType)                                                                                       | `PhpTestFrameworkType`                                                                                      |
| [com.jetbrains.php.tools.projectConfigurableForm](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.projectConfigurableForm) ![Project-Level][project-level]                               | `QualityToolProjectConfigurableForm`                                                                        |
| [com.jetbrains.php.tools.quality.messDetector.messDetectorConfigurationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.messDetector.messDetectorConfigurationProvider) | `MessDetectorConfigurationProvider`                                                                         |
| [com.jetbrains.php.tools.quality.phpCSFixer.phpCSFixerConfigurationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.phpCSFixer.phpCSFixerConfigurationProvider)         | `PhpCSFixerConfigurationProvider`                                                                           |
| [com.jetbrains.php.tools.quality.phpcs.phpCSConfigurationProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.phpcs.phpCSConfigurationProvider)                             | `PhpCSConfigurationProvider`                                                                                |
| [com.jetbrains.php.tools.quality.type](https://jb.gg/ipe?extensions=com.jetbrains.php.tools.quality.type)                                                                                     | `QualityToolType`                                                                                           |
| [com.jetbrains.php.typeProvider3](https://jb.gg/ipe?extensions=com.jetbrains.php.typeProvider3) ![Deprecated][deprecated]                                                                     | `PhpTypeProvider3`                                                                                          |
| [com.jetbrains.php.typeProvider4](https://jb.gg/ipe?extensions=com.jetbrains.php.typeProvider4)                                                                                               | `PhpTypeProvider4`                                                                                          |

### com.jetbrains.php.behat

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.php.behat.gherkinContextProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.behat.gherkinContextProvider) | `ContextInterfaceProvider` |

### com.jetbrains.php.framework

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.php.framework.descriptionProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.framework.descriptionProvider) | `FrameworkDescriptionProvider` |

### phpstorm-remote-interpreter-plugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.php.remote.interpreter.ui.customConfigProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.remote.interpreter.ui.customConfigProvider) | `PhpProjectConfigComponentProvider` |
| [com.jetbrains.php.remote.phpHelperScriptProvider](https://jb.gg/ipe?extensions=com.jetbrains.php.remote.phpHelperScriptProvider) | `PhpHelperScriptProvider` |
| [com.jetbrains.php.remote.remoteProcessManager](https://jb.gg/ipe?extensions=com.jetbrains.php.remote.remoteProcessManager) | `PhpRemoteProcessManager` |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
