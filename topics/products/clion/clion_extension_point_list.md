[//]: # (title: CLion Extension Point and Listener List)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

100 Extension Points and 27 Listeners for CLion

See [](extension_point_list.md) for IntelliJ Platform.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## CLion

### CLion - Listeners

| Topic                                                                                                                                                                           | Listener |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| [FileSymbolTablesCache#OUT_OF_CODE_BLOCK_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level]      | [`Listener`](upsource:///platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [CMakeWorkspaceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspaceListener)                                                | `CMakeWorkspaceListener` |
| [CubeMXManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.embedded.stm32cubemx.CubeMXManager.CubeStatusListener)                                          | `CubeStatusListener` |
| [CLionExternalBuildManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.execution.external.build.CLionExternalBuildManagerListener)                 | `CLionExternalBuildManagerListener` |
| [MakefileBuildTargetsManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.makefile.execution.build.MakefileBuildTargetsManagerListener)             | `MakefileBuildTargetsManagerListener` |
| [CPPToolchainsConfigurable#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.toolchains.CPPToolchainsConfigurable.Listener)                                      | `Listener` |
| [CPPToolchainsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.toolchains.CPPToolchainsListener)                                                       | `CPPToolchainsListener` |
| [ExecutableListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.CidrRunConfigurationExecutableEditor.ExecutableListener)                             | `ExecutableListener` |
| [CidrBuildListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.build.CidrBuildListener)  ![Project-Level][project-level]                             | `CidrBuildListener` |
| [ExternalWorkspaceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.external.system.workspace.ExternalWorkspaceListener)                                    | `ExternalWorkspaceListener` |
| [ClangLanguageServiceProviderListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.ClangLanguageServiceProviderListener)               | `ClangLanguageServiceProviderListener` |
| [ClangServerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.lsp.server.ClangServerListener)                                      | `ClangServerListener` |
| [ClangTelemetryListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.lsp.telemetry.ClangTelemetryListener)                             | `ClangTelemetryListener` |
| [ClangMemoryUsageWatchDogListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.memory.ClangMemoryUsageWatchDogListener)                | `ClangMemoryUsageWatchDogListener` |
| [OCLanguageServiceReparsingPassListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.reparsing.OCLanguageServiceReparsingPassListener) | `OCLanguageServiceReparsingPassListener` |
| [ClangdSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.settings.ClangdSettingsListener)                                  | `ClangdSettingsListener` |
| [ClangdSettingsListener#TOPIC_UI](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.settings.ClangdSettingsListener)                               | `ClangdSettingsListener` |
| [CidrInjectionListener#INJECTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.editor.CidrInjectionListener)                                                | `CidrInjectionListener` |
| [OCInclusionContextListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.preprocessor.OCInclusionContextListener)                                          | `OCInclusionContextListener` |
| [FileSymbolTableCacheListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTableCacheListener)                                  | `FileSymbolTableCacheListener` |
| [FileSymbolTablesPackListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesPackListener)                                  | `FileSymbolTablesPackListener` |
| [OCFileSymbolTableListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbolTableListener)                                        | `OCFileSymbolTableListener` |
| [OCWorkspaceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.workspace.OCWorkspaceListener)                                                           | `OCWorkspaceListener` |
| [CidrRootConfigurationListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.CidrRootConfigurationListener)                                              | `CidrRootConfigurationListener` |
| [CidrWorkspaceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.workspace.CidrWorkspaceListener)                                                    | `CidrWorkspaceListener` |
| [RemoteDeploymentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.system.RemoteDeploymentListener)                                                         | `RemoteDeploymentListener` |
| [AllowedModules#INVALIDATION_TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)                                                                                      | `Runnable` |

### CidrCoveragePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.coverage.coverageComposer](https://jb.gg/ipe?extensions=cidr.coverage.coverageComposer) ![Non-Dynamic][non-dynamic] | `CidrCoverageComposer` |
| [cidr.coverage.coverageComposerRunner](https://jb.gg/ipe?extensions=cidr.coverage.coverageComposerRunner) ![Non-Dynamic][non-dynamic] | `CidrCoverageComposerRunner` |
| [cidr.coverage.coverageDataFileProvider](https://jb.gg/ipe?extensions=cidr.coverage.coverageDataFileProvider) ![Non-Dynamic][non-dynamic] | `CidrCoverageDataFileProvider` |
| [cidr.coverage.coverageErrorProcessor](https://jb.gg/ipe?extensions=cidr.coverage.coverageErrorProcessor) ![Non-Dynamic][non-dynamic] | `CidrCoverageErrorProcessor` |
| [cidr.coverage.coverageViewExtensionProvider](https://jb.gg/ipe?extensions=cidr.coverage.coverageViewExtensionProvider) ![Non-Dynamic][non-dynamic] | `CidrCoverageViewExtensionProvider` |
| [cidr.coverage.gcovCoverageToolsProvider](https://jb.gg/ipe?extensions=cidr.coverage.gcovCoverageToolsProvider) ![Non-Dynamic][non-dynamic] | `GCovCoverageToolProvider` |
| [cidr.coverage.llvmCoverageToolsProvider](https://jb.gg/ipe?extensions=cidr.coverage.llvmCoverageToolsProvider) ![Non-Dynamic][non-dynamic] | `LLVMCoverageToolsProvider` |

### CidrDebuggerPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.debugger.backendConsoleInjectionHelper](https://jb.gg/ipe?extensions=cidr.debugger.backendConsoleInjectionHelper) | `BackendConsoleInjectionHelper` |
| [cidr.debugger.customDebuggerProvider](https://jb.gg/ipe?extensions=cidr.debugger.customDebuggerProvider) | `CidrCustomDebuggerProvider` |
| [cidr.debugger.debugProcessConfigurator](https://jb.gg/ipe?extensions=cidr.debugger.debugProcessConfigurator) | `CidrDebugProcessConfigurator` |
| [cidr.debugger.editorsExtension](https://jb.gg/ipe?extensions=cidr.debugger.editorsExtension) | `CidrDebuggerEditorsExtension` |
| [cidr.debugger.formatters.natvis.provider](https://jb.gg/ipe?extensions=cidr.debugger.formatters.natvis.provider) | `NatvisFileProvider` |
| [cidr.debugger.languageSupport](https://jb.gg/ipe?extensions=cidr.debugger.languageSupport) | `CidrDebuggerLanguageSupport` |
| [cidr.debugger.lineBreakpointFileTypesProvider](https://jb.gg/ipe?extensions=cidr.debugger.lineBreakpointFileTypesProvider) | `CidrLineBreakpointFileTypesProvider` |
| [cidr.debugger.valueRendererExtension](https://jb.gg/ipe?extensions=cidr.debugger.valueRendererExtension) ![Non-Dynamic][non-dynamic] | `ValueRendererExtension` |
| [cidr.debugger.valueRendererFactory](https://jb.gg/ipe?extensions=cidr.debugger.valueRendererFactory) ![Non-Dynamic][non-dynamic] | `ValueRendererFactory` |

### CidrDFAPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [dfa.console](https://jb.gg/ipe?extensions=dfa.console) | `Console` |

### CidrExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.buildConfigurationProvider](https://jb.gg/ipe?extensions=cidr.buildConfigurationProvider) | `CidrBuildConfigurationProvider` |
| [cidr.projectTaskContextProvider](https://jb.gg/ipe?extensions=cidr.projectTaskContextProvider) | `CidrProjectTaskContextProvider` |
| [cidr.resolveConfigurationProvider](https://jb.gg/ipe?extensions=cidr.resolveConfigurationProvider) | `CidrResolveConfigurationProvider` |
| [cidr.runConfigurationExtension](https://jb.gg/ipe?extensions=cidr.runConfigurationExtension) ![Non-Dynamic][non-dynamic] | `CidrRunConfigurationExtensionBase` |
| [cidr.targetConfigurationHelper](https://jb.gg/ipe?extensions=cidr.targetConfigurationHelper) | `CidrTargetConfigurationHelper` |
| [cidr.testFrameworkDetector](https://jb.gg/ipe?extensions=cidr.testFrameworkDetector) ![Non-Dynamic][non-dynamic] | `CidrTestFrameworkDetector` |

### CidrLangBase.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.annotatorInspectionToolProvider](https://jb.gg/ipe?extensions=cidr.lang.annotatorInspectionToolProvider) ![Non-Dynamic][non-dynamic] | [`NotNullProducer`](upsource:///platform/util/src/com/intellij/util/NotNullProducer.java) |
| [cidr.lang.fileTypeHelper](https://jb.gg/ipe?extensions=cidr.lang.fileTypeHelper) ![Non-Dynamic][non-dynamic] | `OCFileTypeHelper` |
| [cidr.lang.knownModuleDetector](https://jb.gg/ipe?extensions=cidr.lang.knownModuleDetector) ![Internal API][internal] | `CidrKnownModuleDetector` |
| [cidr.lang.languageKindHelper](https://jb.gg/ipe?extensions=cidr.lang.languageKindHelper) ![Non-Dynamic][non-dynamic] | `OCLanguageKindCalculatorHelper` |
| [cidr.lang.languageKindProvider](https://jb.gg/ipe?extensions=cidr.lang.languageKindProvider) ![Non-Dynamic][non-dynamic] | `OCLanguageKindProvider` |
| [cidr.lang.newFileLangBackendHandler](https://jb.gg/ipe?extensions=cidr.lang.newFileLangBackendHandler) ![Non-Dynamic][non-dynamic] | `OCNewFileLangBackendHandler` |
| [cidr.lang.newFileModelHandlerProvider](https://jb.gg/ipe?extensions=cidr.lang.newFileModelHandlerProvider) ![Non-Dynamic][non-dynamic] | `OCNewFileProjectModelHandlerProvider` |
| [cidr.lang.projectWizardFilesFormatter](https://jb.gg/ipe?extensions=cidr.lang.projectWizardFilesFormatter) ![Non-Dynamic][non-dynamic] | `CidrProjectWizardFilesFormatter` |
| [cidr.lang.standaloneInspectionToolProvider](https://jb.gg/ipe?extensions=cidr.lang.standaloneInspectionToolProvider) ![Non-Dynamic][non-dynamic] | [`NotNullProducer`](upsource:///platform/util/src/com/intellij/util/NotNullProducer.java) |

### CidrLangPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.annotatorHelper](https://jb.gg/ipe?extensions=cidr.lang.annotatorHelper) ![Non-Dynamic][non-dynamic] | `OCAnnotatorHelper` |
| [cidr.lang.autoImportHelper](https://jb.gg/ipe?extensions=cidr.lang.autoImportHelper) ![Non-Dynamic][non-dynamic] | `OCAutoImportHelper` |
| [cidr.lang.callStructureProvider](https://jb.gg/ipe?extensions=cidr.lang.callStructureProvider) ![Non-Dynamic][non-dynamic] | `PolyglotCallStructureProvider` |
| [cidr.lang.customHeaderProvider](https://jb.gg/ipe?extensions=cidr.lang.customHeaderProvider) ![Non-Dynamic][non-dynamic] | `CustomHeaderProvider` |
| [cidr.lang.doxygenExtension](https://jb.gg/ipe?extensions=cidr.lang.doxygenExtension) ![Non-Dynamic][non-dynamic] | `Doxygen` |
| [cidr.lang.externalCompletionProvider](https://jb.gg/ipe?extensions=cidr.lang.externalCompletionProvider) ![Non-Dynamic][non-dynamic] | `ExternalCompletionProvider` |
| [cidr.lang.externalInspections](https://jb.gg/ipe?extensions=cidr.lang.externalInspections) | `OCExternalInspections` |
| [cidr.lang.externalResolver](https://jb.gg/ipe?extensions=cidr.lang.externalResolver) | `OCExternalResolver` |
| [cidr.lang.fileWideHighlighter](https://jb.gg/ipe?extensions=cidr.lang.fileWideHighlighter) ![Non-Dynamic][non-dynamic] | `FileWideHighlighter` |
| [cidr.lang.foreignUsagesRenameProcessor](https://jb.gg/ipe?extensions=cidr.lang.foreignUsagesRenameProcessor) ![Non-Dynamic][non-dynamic] | `OCForeignUsagesRenameProcessor` |
| [cidr.lang.groupedFileNaming](https://jb.gg/ipe?extensions=cidr.lang.groupedFileNaming) ![Non-Dynamic][non-dynamic] | `OCGroupedFileNaming` |
| [cidr.lang.includeHelper](https://jb.gg/ipe?extensions=cidr.lang.includeHelper) ![Non-Dynamic][non-dynamic] | `OCIncludeHelper` |
| [cidr.lang.includeHierarchyProvider](https://jb.gg/ipe?extensions=cidr.lang.includeHierarchyProvider) ![Non-Dynamic][non-dynamic] | [`HierarchyProvider`](upsource:///platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [cidr.lang.initialBuildingActivity](https://jb.gg/ipe?extensions=cidr.lang.initialBuildingActivity) ![Non-Dynamic][non-dynamic] | `OCInitialBuildingActivity` |
| [cidr.lang.languageKindContributor](https://jb.gg/ipe?extensions=cidr.lang.languageKindContributor) ![Non-Dynamic][non-dynamic] | `OCLanguageKindContributor` |
| [cidr.lang.libraryFileConfigurationProvider](https://jb.gg/ipe?extensions=cidr.lang.libraryFileConfigurationProvider) ![Non-Dynamic][non-dynamic] | `OCLibraryFileResolveConfigurationProvider` |
| [cidr.lang.moduleMapManagerRequestor](https://jb.gg/ipe?extensions=cidr.lang.moduleMapManagerRequestor) ![Non-Dynamic][non-dynamic] | `ModuleMapManagerRequestor` |
| [cidr.lang.moduleMapPlatformTypeProvider](https://jb.gg/ipe?extensions=cidr.lang.moduleMapPlatformTypeProvider) ![Non-Dynamic][non-dynamic] | `ModuleMapPlatformTypeProvider` |
| [cidr.lang.moduleMapRootSerializer](https://jb.gg/ipe?extensions=cidr.lang.moduleMapRootSerializer) ![Non-Dynamic][non-dynamic] | `ModuleMapRootSerializer` |
| [cidr.lang.moduleMapSearchRootProvider](https://jb.gg/ipe?extensions=cidr.lang.moduleMapSearchRootProvider) ![Non-Dynamic][non-dynamic] | `ModuleMapSearchRootProvider` |
| [cidr.lang.moduleResolver](https://jb.gg/ipe?extensions=cidr.lang.moduleResolver) ![Non-Dynamic][non-dynamic] | `OCModuleResolver` |
| [cidr.lang.ocAdditionalFileSymbolTableBuilder](https://jb.gg/ipe?extensions=cidr.lang.ocAdditionalFileSymbolTableBuilder) ![Non-Dynamic][non-dynamic] | `OCAdditionalFileSymbolTableBuilder` |
| [cidr.lang.ocDirectInheritorsSearch](https://jb.gg/ipe?extensions=cidr.lang.ocDirectInheritorsSearch) ![Non-Dynamic][non-dynamic] | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [cidr.lang.ocResolveRootAndConfigurationProvider](https://jb.gg/ipe?extensions=cidr.lang.ocResolveRootAndConfigurationProvider) ![Non-Dynamic][non-dynamic] | `OCResolveRootAndConfigurationProvider` |
| [cidr.lang.renameHandlerExtension](https://jb.gg/ipe?extensions=cidr.lang.renameHandlerExtension) ![Non-Dynamic][non-dynamic] | `OCRenameHandlerExtension` |
| [cidr.lang.renameProcessorExtension](https://jb.gg/ipe?extensions=cidr.lang.renameProcessorExtension) ![Non-Dynamic][non-dynamic] | `OCRenameProcessorExtension` |
| [cidr.lang.resourceCompletionProviders](https://jb.gg/ipe?extensions=cidr.lang.resourceCompletionProviders) ![Non-Dynamic][non-dynamic] | `OCResourceCompletionProviders` |
| [cidr.lang.resourceFilesProvider](https://jb.gg/ipe?extensions=cidr.lang.resourceFilesProvider) ![Non-Dynamic][non-dynamic] | `OCResourceFilesProvider` |
| [cidr.lang.searchHelper](https://jb.gg/ipe?extensions=cidr.lang.searchHelper) ![Non-Dynamic][non-dynamic] | `OCSearchHelper` |
| [cidr.lang.serializerProvider](https://jb.gg/ipe?extensions=cidr.lang.serializerProvider) ![Non-Dynamic][non-dynamic] | `SerializerProvider` |
| [cidr.lang.symbolTableProvider](https://jb.gg/ipe?extensions=cidr.lang.symbolTableProvider) ![Non-Dynamic][non-dynamic] | `FileSymbolTableProvider` |
| [cidr.lang.testFramework](https://jb.gg/ipe?extensions=cidr.lang.testFramework) ![Non-Dynamic][non-dynamic] | `OCTestFramework` |
| [cidr.lang.typeStructureProvider](https://jb.gg/ipe?extensions=cidr.lang.typeStructureProvider) ![Non-Dynamic][non-dynamic] | `PolyglotTypeStructureProvider` |
| [cidr.projectModel.unloadedResolveContextsManager](https://jb.gg/ipe?extensions=cidr.projectModel.unloadedResolveContextsManager) | `OCUnloadedResolveContextsManager` |

### CidrProjectModelPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.appleFrameworkFilter](https://jb.gg/ipe?extensions=cidr.lang.appleFrameworkFilter) ![Project-Level][project-level] | `AppleFrameworkFilter` |
| [cidr.lang.compilerKindProvider](https://jb.gg/ipe?extensions=cidr.lang.compilerKindProvider) | `OCCompilerKindProvider` |
| [cidr.lang.compilerResolver](https://jb.gg/ipe?extensions=cidr.lang.compilerResolver) ![Project-Level][project-level] | `OCCompilerResolver` |
| [cidr.lang.headerSearchRootFactory](https://jb.gg/ipe?extensions=cidr.lang.headerSearchRootFactory) | `HeadersSearchRootFactory` |
| [cidr.lang.resolveConfigurationSelector](https://jb.gg/ipe?extensions=cidr.lang.resolveConfigurationSelector) | `OCResolveConfigurationSelector` |
| [cidr.projectModel.deserializingVetoCondition](https://jb.gg/ipe?extensions=cidr.projectModel.deserializingVetoCondition) | `OCWorkspaceDeserializingVetoCondition` |
| [cidr.projectModel.msvcPchHelper](https://jb.gg/ipe?extensions=cidr.projectModel.msvcPchHelper) | `OCMsvcPchHelper` |
| [cidr.projectModel.supportedFileChecker](https://jb.gg/ipe?extensions=cidr.projectModel.supportedFileChecker) ![Non-Dynamic][non-dynamic] | `OCSupportedFileChecker` |

### CidrTestingPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.testing.testIndexContributor](https://jb.gg/ipe?extensions=cidr.lang.testing.testIndexContributor) | `CidrTestIndexContributor` |

### CidrToolchainsPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.devEnvironmentChecker](https://jb.gg/ipe?extensions=cidr.devEnvironmentChecker) ![Non-Dynamic][non-dynamic] | `DevEnvironmentChecker` |

### CidrWorkspaceModelCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.project.is.known.checker](https://jb.gg/ipe?extensions=cidr.project.is.known.checker) ![Non-Dynamic][non-dynamic] | `KnownProjectChecker` |
| [cidr.project.workspaceProvider](https://jb.gg/ipe?extensions=cidr.project.workspaceProvider) ![Non-Dynamic][non-dynamic] | `CidrWorkspaceProvider` |
| [com.jetbrains.cidr.fus.projectModelTypeProvider](https://jb.gg/ipe?extensions=com.jetbrains.cidr.fus.projectModelTypeProvider) | `CidrProjectModelTypeProvider` |

### CidrWorkspaceModelIde.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.markRootActionAvailability](https://jb.gg/ipe?extensions=cidr.markRootActionAvailability) ![Non-Dynamic][non-dynamic] | `CidrMarkRootActionAvailability` |

### ClangFormatPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.clangFormatProvider](https://jb.gg/ipe?extensions=com.intellij.clangFormatProvider) ![Non-Dynamic][non-dynamic] | `ClangFormatChangeSettingsProvider` |

### CLionCMakeCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cmake.languageKindRecognizer](https://jb.gg/ipe?extensions=com.intellij.cmake.languageKindRecognizer) | `CMakeLanguageKindRecognizer` |
| [com.intellij.cmake.loadOnStartupDependency](https://jb.gg/ipe?extensions=com.intellij.cmake.loadOnStartupDependency) | `FutureProvider` |
| [com.intellij.cmake.profileLoadContributor](https://jb.gg/ipe?extensions=com.intellij.cmake.profileLoadContributor) | `FutureProvider` |
| [com.intellij.cmake.runnerStep](https://jb.gg/ipe?extensions=com.intellij.cmake.runnerStep) ![Non-Dynamic][non-dynamic] | `CMakeRunnerStep` |
| [com.intellij.cmake.targetToConfigProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.targetToConfigProvider) | `CMakeTargetToConfigProvider` |

### CLionExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [clion.buildToolWindowActivator.contributor](https://jb.gg/ipe?extensions=clion.buildToolWindowActivator.contributor) | `Contributor` |
| [clion.compoundConfigurationContext](https://jb.gg/ipe?extensions=clion.compoundConfigurationContext) | `CidrCompoundConfigurationContext` |
| [clion.externalConfigurationProvider](https://jb.gg/ipe?extensions=clion.externalConfigurationProvider) | `CLionExternalConfigurationProvider` |

### CLionExternalSystemPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [clion.externalLoadNotificationAware](https://jb.gg/ipe?extensions=clion.externalLoadNotificationAware) | `CLionExternalLoadNotificationAware` |

### CLionWizard.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.openWizardStepProvider](https://jb.gg/ipe?extensions=cidr.openWizardStepProvider) | `OpenWizardStepProvider` |

### CMakePsiPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cmake.completion.environmentProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.completion.environmentProvider) | `CMakeEnvironmentVariableProvider` |
| [com.intellij.cmake.fileLocationProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.fileLocationProvider) | `CMakeFileLocationProvider` |

### com.intellij.cidr.lang.clangd

| Extension Point | Implementation |
|-----------------|----------------|
| [clangd.clangTidyAnnotationApplier](https://jb.gg/ipe?extensions=clangd.clangTidyAnnotationApplier) | `ClangTidyAnnotationApplier` |
| [clangd.clangTidyResolveInfoProvider](https://jb.gg/ipe?extensions=clangd.clangTidyResolveInfoProvider) | `ClangTidyResolveInfoProvider` |
| [clangd.clangdAnnotatorUtil](https://jb.gg/ipe?extensions=clangd.clangdAnnotatorUtil) | `ClangAnnotatorUtil` |
| [clangd.clangdBridge](https://jb.gg/ipe?extensions=clangd.clangdBridge) | `ClangdBridgeInterface` |
| [com.intellij.cidrCommandLineParser](https://jb.gg/ipe?extensions=com.intellij.cidrCommandLineParser) ![Project-Level][project-level] | `CidrCommandLineParser` |

### com.intellij.clion-makefile

| Extension Point | Implementation |
|-----------------|----------------|
| [clion.makefile.buildSystemDetector](https://jb.gg/ipe?extensions=clion.makefile.buildSystemDetector) | `MkBuildSystemDetector` |
| [clion.makefile.projectPreConfigurator](https://jb.gg/ipe?extensions=clion.makefile.projectPreConfigurator) | `MkProjectPreConfigurator` |


[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
