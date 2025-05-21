<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /CIDR
-->


<snippet id="content">

149 Extension Points and 45 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## CLion

### CLion – Listeners

| Topic | Listener |
|-------|----------|
| [`CubeMXManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.clion.embedded.stm32cubemx.CubeMXManager.CubeStatusListener)  | `CubeStatusListener` |
| [`WestProjectListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.clion.west.WestProjectListener)  | `WestProjectListener` |
| [`WestConfigListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.clion.west.config.WestConfigListener)  | `WestConfigListener` |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.clion.west.settings.WestSettings.Listener)  | `Listener` |
| [`FileSymbolTablesCache#OUT_OF_CODE_BLOCK_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [`ConsistencyErrorTopicListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.rml.dfa.utils.ConsistencyErrorTopicListener)  | `ConsistencyErrorTopicListener` |
| [`CMakeSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.CMakeSettingsListener)  | `CMakeSettingsListener` |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.python.CMakePythonSdkService.Companion.Listener)  | `Listener` |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.python.CMakePythonSettingListenerService.Companion.Listener)  | `Listener` |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.python.CMakePythonSettingsService.Listener)  | `Listener` |
| [`CMakeWorkspaceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspaceListener)  | `CMakeWorkspaceListener` |
| [`CompDBSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.compdb.settings.CompDBSettingsListener)  | `CompDBSettingsListener` |
| [`CLionExternalBuildManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.execution.external.build.CLionExternalBuildManagerListener)  | `CLionExternalBuildManagerListener` |
| [`ClionProjectToolManagerListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.execution.external.build.ClionProjectToolManagerListener)  | `ClionProjectToolManagerListener` |
| [`MakefileBuildTargetsManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.makefile.execution.build.MakefileBuildTargetsManagerListener)  | `MakefileBuildTargetsManagerListener` |
| [`MakefileSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.makefile.settings.MakefileSettingsListener)  | `MakefileSettingsListener` |
| [`CPPToolchainsConfigurable#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.toolchains.CPPToolchainsConfigurable.Listener)  | `Listener` |
| [`CPPToolchainsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.toolchains.CPPToolchainsListener)  | `CPPToolchainsListener` |
| [`CidrBuildListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.build.CidrBuildListener)  ![Project-Level][project-level] | `CidrBuildListener` |
| [`CidrHighlighterNotifierService#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.testing.CidrHighlighterNotifierService.LineMarkNotifier)  | `LineMarkNotifier` |
| [`CidrTestScopeService#TEST_SCOPE_LISTENER_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.testing.CidrTestScopeService.TestScopeListener)  | `TestScopeListener` |
| [`ExternalWorkspaceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.external.system.workspace.ExternalWorkspaceListener)  | `ExternalWorkspaceListener` |
| [`ClangLanguageServiceProviderListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.ClangLanguageServiceProviderListener)  | `ClangLanguageServiceProviderListener` |
| [`ClangServerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.lsp.server.ClangServerListener)  | `ClangServerListener` |
| [`ClangTelemetryListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.lsp.telemetry.ClangTelemetryListener)  | `ClangTelemetryListener` |
| [`ClangMemoryUsageWatchDogListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.memory.ClangMemoryUsageWatchDogListener)  | `ClangMemoryUsageWatchDogListener` |
| [`OCLanguageServiceReparsingPassListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.reparsing.OCLanguageServiceReparsingPassListener)  | `OCLanguageServiceReparsingPassListener` |
| [`ClangdSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.settings.ClangdSettingsListener)  | `ClangdSettingsListener` |
| [`ClangdSettingsListener#TOPIC_UI`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.daemon.clang.clangd.settings.ClangdSettingsListener)  | `ClangdSettingsListener` |
| [`CidrInjectionListener#INJECTION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.editor.CidrInjectionListener)  | `CidrInjectionListener` |
| [`OCInclusionContextListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.preprocessor.OCInclusionContextListener)  | `OCInclusionContextListener` |
| [`FileSymbolTableCacheListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTableCacheListener)  | `FileSymbolTableCacheListener` |
| [`FileSymbolTablesPackListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesPackListener)  | `FileSymbolTablesPackListener` |
| [`OCFileSymbolTableListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbolTableListener)  | `OCFileSymbolTableListener` |
| [`OCSymbolBuildingListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.symbols.symtable.building.OCSymbolBuildingListener)  | `OCSymbolBuildingListener` |
| [`OCWorkspaceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.lang.workspace.OCWorkspaceListener)  | `OCWorkspaceListener` |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.meson.project.MesonModelManager.Listener)  | `Listener` |
| [`MesonSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.meson.settings.MesonSettingsListener)  | `MesonSettingsListener` |
| [`PackageManagerEventListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.packagemanager.vcpkg.PackageManagerEventListener)  | `PackageManagerEventListener` |
| [`CidrRootConfigurationListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.CidrRootConfigurationListener)  | `CidrRootConfigurationListener` |
| [`CidrWorkspaceInstantaneousStateChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.workspace.CidrWorkspaceInstantaneousStateChangeListener)  ![Project-Level][project-level] | `CidrWorkspaceInstantaneousStateChangeListener` |
| [`CidrWorkspaceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.workspace.CidrWorkspaceListener)  | `CidrWorkspaceListener` |
| [`OCRootsSynchronizerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.project.workspace.OCRootsSynchronizerListener)  | `OCRootsSynchronizerListener` |
| [`RemoteDeploymentListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.system.RemoteDeploymentListener)  | `RemoteDeploymentListener` |
| [`AllowedModules#INVALIDATION_TOPIC`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### CidrDebuggerPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.backendConsoleInjectionHelper"/></include> | `BackendConsoleInjectionHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.customDebuggerProvider"/></include> | `CidrCustomDebuggerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.debugProcessConfigurator"/></include> | `CidrDebugProcessConfigurator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.debuggerMessagesProvider"/></include> ![Internal][internal] | `CidrDebuggerMessagesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.disasmRegisterProvider"/></include> ![Experimental][experimental] | `CidrDisasmRegisterProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.editorsExtension"/></include> | `CidrDebuggerEditorsExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.formatters.natvis.provider"/></include> | `NatvisFileProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.frameChildrenContributor"/></include> | `CidrFrameChildrenContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.fullValueEvaluatorProvider"/></include> ![Experimental][experimental] | `CidrFullValueEvaluatorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.imageViewerProvider"/></include> | `CidrImageViewerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.languageSupport"/></include> | `CidrDebuggerLanguageSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.lineBreakpointFileTypesProvider"/></include> | `CidrLineBreakpointFileTypesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.localVariablesFilterHandler"/></include> | `LocalVariablesFilterHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.modulesHandler"/></include> ![Experimental][experimental] | `CidrDebuggerModulesHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.nativeSelfProfilerPathProvider"/></include> | `NativeSelfProfilerPathProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.suspendThreadSelector"/></include> | `CidrSuspendThreadSelector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.valueRendererExtension"/></include> ![Non-Dynamic][non-dynamic] | `ValueRendererExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.valueRendererFactory"/></include> ![Non-Dynamic][non-dynamic] | `ValueRendererFactory` |

### CidrExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.buildConfigurationProvider"/></include> | `CidrBuildConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectTaskContextProvider"/></include> | `CidrProjectTaskContextProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.resolveConfigurationProvider"/></include> | `CidrResolveConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.runConfigurationExtension"/></include> ![Non-Dynamic][non-dynamic] | `CidrRunConfigurationExtensionBase` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.targetConfigurationHelper"/></include> | `CidrTargetConfigurationHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.testFrameworkDetector"/></include> ![Non-Dynamic][non-dynamic] | `CidrTestFrameworkDetector` |

### CidrLangBase.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.annotatorInspectionToolProvider"/></include> ![Non-Dynamic][non-dynamic] | `Supplier` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.dfaInspectionConfig"/></include> ![Non-Dynamic][non-dynamic] | `OCDFAInspectionConfig` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.externalInspections"/></include> | `OCExternalInspections` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.fileTypeHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCFileTypeHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.knownModuleDetector"/></include> ![Internal][internal] | `CidrKnownModuleDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.langUtils"/></include> ![Non-Dynamic][non-dynamic] | `OCLanguageUtilsBase` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.languageKindHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCLanguageKindCalculatorHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.languageKindProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCLanguageKindProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.navigatableSymbolSearcherBridge"/></include> ![Non-Dynamic][non-dynamic] | `NavigatableSymbolSearcherBridge` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.newFileLangBackendHandler"/></include> ![Non-Dynamic][non-dynamic] | `OCNewFileLangBackendHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.newFileModelHandlerProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCNewFileProjectModelHandlerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.ownModuleDetector"/></include> | `CidrOwnModuleDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.projectWizardFilesFormatter"/></include> ![Non-Dynamic][non-dynamic] | `CidrProjectWizardFilesFormatter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.standaloneInspectionToolProvider"/></include> ![Non-Dynamic][non-dynamic] | `Supplier` |

### CidrLangPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.annotatorHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCAnnotatorHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.autoImportHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCAutoImportHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.buildSymbolsVeto"/></include> ![Non-Dynamic][non-dynamic] | `OCBuildSymbolsVetoExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.callStructureProvider"/></include> ![Non-Dynamic][non-dynamic] | `PolyglotCallStructureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.customHeaderProvider"/></include> ![Non-Dynamic][non-dynamic] | `CustomHeaderProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.doxygenExtension"/></include> ![Non-Dynamic][non-dynamic] | `Doxygen` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.externalResolver"/></include> | `OCExternalResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.fileToBuildSymbolsCollector"/></include> ![Non-Dynamic][non-dynamic] | `FileToBuildSymbolsCollector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.foreignUsagesRenameProcessor"/></include> ![Non-Dynamic][non-dynamic] | `OCForeignUsagesRenameProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.groupedFileNaming"/></include> ![Non-Dynamic][non-dynamic] | `OCGroupedFileNaming` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.includeHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCIncludeHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.includeHierarchyProvider"/></include> ![Non-Dynamic][non-dynamic] | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.initialBuildingActivity"/></include> ![Non-Dynamic][non-dynamic] | `OCInitialBuildingActivity` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.languageKindContributor"/></include> ![Non-Dynamic][non-dynamic] | `OCLanguageKindContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.libraryFileConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCLibraryFileResolveConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleBuilder.statisticsCollectorFactory"/></include> ![Non-Dynamic][non-dynamic] | `ModuleCacheBuilderStatisticsCollectorFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleMapManagerRequestor"/></include> ![Non-Dynamic][non-dynamic] | `ModuleMapManagerRequestor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleMapPlatformTypeProvider"/></include> ![Non-Dynamic][non-dynamic] | `ModuleMapPlatformTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleMapRootSerializer"/></include> ![Non-Dynamic][non-dynamic] | `ModuleMapRootSerializer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleMapSearchRootProvider"/></include> ![Non-Dynamic][non-dynamic] | `ModuleMapSearchRootProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.moduleResolver"/></include> ![Non-Dynamic][non-dynamic] | `OCModuleResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.ocAdditionalFileSymbolTableBuilder"/></include> ![Non-Dynamic][non-dynamic] | `OCAdditionalFileSymbolTableBuilder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.ocDirectInheritorsSearch"/></include> ![Non-Dynamic][non-dynamic] | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.ocInclusionContextLazyGetDefinitionProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCInclusionContextLazyGetDefinitionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.ocResolveRootAndConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCResolveRootAndConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.renameHandlerExtension"/></include> ![Non-Dynamic][non-dynamic] | `OCRenameHandlerExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.renameProcessorExtension"/></include> ![Non-Dynamic][non-dynamic] | `OCRenameProcessorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.resourceCompletionProviders"/></include> ![Non-Dynamic][non-dynamic] | `OCResourceCompletionProviders` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.resourceFilesProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCResourceFilesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.searchHelper"/></include> ![Non-Dynamic][non-dynamic] | `OCSearchHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.serializerProvider"/></include> ![Non-Dynamic][non-dynamic] | `SerializerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.symbolTableProvider"/></include> ![Non-Dynamic][non-dynamic] | `FileSymbolTableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.testFramework"/></include> ![Non-Dynamic][non-dynamic] | `OCTestFramework` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.typeStructureProvider"/></include> ![Non-Dynamic][non-dynamic] | `PolyglotTypeStructureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.requiredForCidrSmartMode"/></include> ![Non-Dynamic][non-dynamic] | `RequiredForCidrSmartMode` |

### CidrProjectModelPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.appleFrameworkFilter"/></include> ![Project-Level][project-level] | `AppleFrameworkFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.codeInsightUnavailabilityHighlighter"/></include> ![Non-Dynamic][non-dynamic] | `OCCodeInsightUnavailabilityHighlighter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.compilerKindProvider"/></include> | `OCCompilerKindProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.compilerResolver"/></include> ![Project-Level][project-level] | `OCCompilerResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.fileScopeProvider"/></include> ![Non-Dynamic][non-dynamic] | `OCFileScopeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.fileWideHighlighter"/></include> ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | `FileWideHighlighter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.headerSearchRootFactory"/></include> | `HeadersSearchRootFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.resolveConfigurationSelector"/></include> | `OCResolveConfigurationSelector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.deserializingVetoCondition"/></include> | `OCWorkspaceDeserializingVetoCondition` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.msvcPchHelper"/></include> | `OCMsvcPchHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.runAfterOCWorkspaceIsInitialized"/></include> ![Non-Dynamic][non-dynamic] | `RunAfterOCWorkspaceIsInitialized` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.runAfterOCWorkspaceIsLoaded"/></include> ![Non-Dynamic][non-dynamic] | `RunAfterOCWorkspaceIsLoaded` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.supportedFileChecker"/></include> ![Non-Dynamic][non-dynamic] | `OCSupportedFileChecker` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.unloadedResolveContextsManager"/></include> | `OCUnloadedResolveContextsManager` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.projectModel.workspaceLoadedCheck"/></include> | `OCWorkspaceLoadedChecker` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cidrCommandLineParser"/></include> ![Project-Level][project-level] | `CidrCommandLineParser` |

### CidrTestingPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.testing.potentialTestHolderRootsProvider"/></include> | `CidrPotentialTestHolderRootsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.lang.testing.testIndexContributor"/></include> | `CidrTestIndexContributor` |

### CidrToolchainsPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.devEnvironmentChecker"/></include> ![Non-Dynamic][non-dynamic] | `DevEnvironmentChecker` |

### CidrUtilExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.util.pluginPathMapper"/></include> | `CidrPluginPathMapper` |

### CidrWorkspaceModelCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.markRootActionAvailability"/></include> ![Non-Dynamic][non-dynamic] | `CidrMarkRootActionAvailability` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.is.known.checker"/></include> ![Non-Dynamic][non-dynamic] | `KnownProjectChecker` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.moduleNameSuffixProvider"/></include> ![Non-Dynamic][non-dynamic] | `WorkspaceModuleNameSuffixProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.notifications.editorNotificationWarningProvider"/></include> | `EditorNotificationWarningProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.popup.projectFixesProvider"/></include> | `ProjectFixesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.rootsBuilderProvider"/></include> ![Non-Dynamic][non-dynamic] | `Provider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.widget.widgetStatusProvider"/></include> | `WidgetStatusProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.project.workspaceProvider"/></include> ![Non-Dynamic][non-dynamic] | `CidrWorkspaceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.cidr.fus.projectModelTypeProvider"/></include> | `CidrProjectModelTypeProvider` |

### ClangFormatPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.clangFormatProvider"/></include> ![Non-Dynamic][non-dynamic] | `ClangFormatChangeSettingsProvider` |

### CLionCMakeCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.buildStep"/></include> | `CMakeBuildProcessListenerCreator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.cmakeSettingsFlavorProvider"/></include> | `CMakeSettingsFlavorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.flavorProvider"/></include> | `CMakeFlavorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.languageKindRecognizer"/></include> | `CMakeLanguageKindRecognizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.loadOnStartupDependency"/></include> | `FutureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.profileLoadContributor"/></include> | `FutureProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.runnerStep"/></include> ![Non-Dynamic][non-dynamic] | `CMakeRunnerStep` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.targetToConfigProvider"/></include> | `CMakeTargetToConfigProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.toolWindowFocusContributor"/></include> | `CMakeToolWindowFocusContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.workspace.compilerEnvironmentContributor"/></include> | `CMakeCompilerEnvironmentContributor` |

### CLionExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.debugger.targets.provider"/></include> | `DebugTargetsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.buildToolWindowActivator.contributor"/></include> | `Contributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.compoundConfigurationContext"/></include> | `CidrCompoundConfigurationContext` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.externalConfigurationProvider"/></include> | `CLionExternalConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.showAssembly.funcInfoProvider"/></include> | `CLionShowAssemblyFuncInfoProvider` |

### CLionMakefilePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.makefile.buildSystemDetector"/></include> | `MkBuildSystemDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clion.makefile.projectPreConfigurator"/></include> | `MkProjectPreConfigurator` |

### CLionWizard.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.openWizardStepProvider"/></include> | `OpenWizardStepProvider` |

### CMakePsiPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.bundledDocumentationProvider"/></include> | `CMakeBundledDocumentationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.completion.environmentProvider"/></include> | `CMakeEnvironmentVariableProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cmake.fileLocationProvider"/></include> | `CMakeFileLocationProvider` |

### com.intellij.cidr.lang.clangd

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clangd.clangTidyAnnotationApplier"/></include> | `ClangTidyAnnotationApplier` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clangd.clangTidyResolveInfoProvider"/></include> | `ClangTidyResolveInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clangd.clangdAnnotatorUtil"/></include> | `ClangAnnotatorUtil` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clangd.clangdBridge"/></include> | `ClangdBridgeInterface` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="clangd.externalCompletionProvider"/></include> ![DumbAware][dumb-aware] | `ExternalCompletionProvider` |

### com.intellij.cidr.uml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.uml.dragAndDropReceiver"/></include> | `UmlDiagramDragAndDropReceiver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.uml.umlDiagramProvider"/></include> | `UmlDiagramLanguage` |

### intellij.cidr.coverage.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.coverageComposer"/></include> ![Non-Dynamic][non-dynamic] | `CidrCoverageComposer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.coverageComposerRunner"/></include> ![Non-Dynamic][non-dynamic] | `CidrCoverageComposerRunner` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.coverageDataFileProvider"/></include> ![Non-Dynamic][non-dynamic] | `CidrCoverageDataFileProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.coverageErrorProcessor"/></include> ![Non-Dynamic][non-dynamic] | `CidrCoverageErrorProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.coverageViewExtensionProvider"/></include> ![Non-Dynamic][non-dynamic] | `CidrCoverageViewExtensionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.gcovCoverageToolsProvider"/></include> ![Non-Dynamic][non-dynamic] | `GCovCoverageToolProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.coverage.llvmCoverageToolsProvider"/></include> ![Non-Dynamic][non-dynamic] | `LLVMCoverageToolsProvider` |

### intellij.cidr.profiling.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.profiler.memory.environmentProvider"/></include> ![Non-Dynamic][non-dynamic] | `MemoryProfileEnvironmentProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.profiler.memory.presentation"/></include> ![Non-Dynamic][non-dynamic] | `MemoryProfilePresentation` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.profiler.valgrind.disabler"/></include> | `ValgrindDisabler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.profiler.valgrind.executionContext"/></include> | `ValgrindExecutionContext` |

### intellij.cidr.translateCode.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.showAssembly.translatorProviders"/></include> | `CidrTranslatorProvider` |

### intellij.clion.featuresTrainer.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.clion.lessons"/></include> | `CLionExternalLessons` |

### intellij.clion.runFile.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.cpp.runFile.entryPointDetector"/></include> | `CppFileEntryPointDetector` |

### intellij.clion.west.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.clion.west.westPathContributor"/></include> | `WestPathContributor` |

### intellij.rml.dfa.devtools.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rml.dfa.devtools.debug.provider"/></include> | `DfaDebugProvider` |

### intellij.rml.dfa.impl.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rml.dfa.impl.DfaDebugExtension"/></include> | `DfaDebugExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rml.dfa.ir.serialization.provider"/></include> | `IrSerializationProvider` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
