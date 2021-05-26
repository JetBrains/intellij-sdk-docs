[//]: # (title: CLion Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

82 Extension Points (EP) for CLion

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
        
## CLion
               
### CidrCoveragePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.coverage.coverageComposer](https://jb.gg/ipe?extensions=cidr.coverage.coverageComposer) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrCoverageComposer` | 
| [cidr.coverage.coverageComposerRunner](https://jb.gg/ipe?extensions=cidr.coverage.coverageComposerRunner) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrCoverageComposerRunner` | 
| [cidr.coverage.coverageDataFileProvider](https://jb.gg/ipe?extensions=cidr.coverage.coverageDataFileProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrCoverageDataFileProvider` | 
| [cidr.coverage.coverageErrorProcessor](https://jb.gg/ipe?extensions=cidr.coverage.coverageErrorProcessor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrCoverageErrorProcessor` | 
| [cidr.coverage.coverageViewExtensionProvider](https://jb.gg/ipe?extensions=cidr.coverage.coverageViewExtensionProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrCoverageViewExtensionProvider` | 
| [cidr.coverage.gcovCoverageToolsProvider](https://jb.gg/ipe?extensions=cidr.coverage.gcovCoverageToolsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `GCovCoverageToolProvider` | 
| [cidr.coverage.llvmCoverageToolsProvider](https://jb.gg/ipe?extensions=cidr.coverage.llvmCoverageToolsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `LLVMCoverageToolsProvider` | 

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
| [cidr.debugger.valueRendererExtension](https://jb.gg/ipe?extensions=cidr.debugger.valueRendererExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ValueRendererExtension` | 
| [cidr.debugger.valueRendererFactory](https://jb.gg/ipe?extensions=cidr.debugger.valueRendererFactory) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ValueRendererFactory` | 

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
| [cidr.runConfigurationExtension](https://jb.gg/ipe?extensions=cidr.runConfigurationExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrRunConfigurationExtensionBase` | 
| [cidr.targetConfigurationHelper](https://jb.gg/ipe?extensions=cidr.targetConfigurationHelper) | `CidrTargetConfigurationHelper` | 
| [cidr.testFrameworkDetector](https://jb.gg/ipe?extensions=cidr.testFrameworkDetector) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrTestFrameworkDetector` | 

### CidrLangBase.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.fileTypeHelper](https://jb.gg/ipe?extensions=cidr.lang.fileTypeHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCFileTypeHelper` | 
| [cidr.lang.languageKindHelper](https://jb.gg/ipe?extensions=cidr.lang.languageKindHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCLanguageKindCalculatorHelper` | 
| [cidr.lang.languageKindProvider](https://jb.gg/ipe?extensions=cidr.lang.languageKindProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCLanguageKindProvider` | 
| [cidr.lang.newFileLangBackendHandler](https://jb.gg/ipe?extensions=cidr.lang.newFileLangBackendHandler) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCNewFileLangBackendHandler` | 
| [cidr.lang.newFileModelHandlerProvider](https://jb.gg/ipe?extensions=cidr.lang.newFileModelHandlerProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCNewFileProjectModelHandlerProvider` | 
| [cidr.lang.projectWizardFilesFormatter](https://jb.gg/ipe?extensions=cidr.lang.projectWizardFilesFormatter) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrProjectWizardFilesFormatter` | 

### CidrLangPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.annotatorHelper](https://jb.gg/ipe?extensions=cidr.lang.annotatorHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCAnnotatorHelper` | 
| [cidr.lang.autoImportHelper](https://jb.gg/ipe?extensions=cidr.lang.autoImportHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCAutoImportHelper` | 
| [cidr.lang.customHeaderProvider](https://jb.gg/ipe?extensions=cidr.lang.customHeaderProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CustomHeaderProvider` | 
| [cidr.lang.doxygenExtension](https://jb.gg/ipe?extensions=cidr.lang.doxygenExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `Doxygen` | 
| [cidr.lang.externalCompletionProvider](https://jb.gg/ipe?extensions=cidr.lang.externalCompletionProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ExternalCompletionProvider` | 
| [cidr.lang.externalInspections](https://jb.gg/ipe?extensions=cidr.lang.externalInspections) | `OCExternalInspections` | 
| [cidr.lang.externalResolver](https://jb.gg/ipe?extensions=cidr.lang.externalResolver) | `OCExternalResolver` | 
| [cidr.lang.fileWideHighlighter](https://jb.gg/ipe?extensions=cidr.lang.fileWideHighlighter) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `FileWideHighlighter` | 
| [cidr.lang.foreignUsagesRenameProcessor](https://jb.gg/ipe?extensions=cidr.lang.foreignUsagesRenameProcessor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCForeignUsagesRenameProcessor` | 
| [cidr.lang.groupedFileNaming](https://jb.gg/ipe?extensions=cidr.lang.groupedFileNaming) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCGroupedFileNaming` | 
| [cidr.lang.includeHelper](https://jb.gg/ipe?extensions=cidr.lang.includeHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCIncludeHelper` | 
| [cidr.lang.includeHierarchyProvider](https://jb.gg/ipe?extensions=cidr.lang.includeHierarchyProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | [`HierarchyProvider`](upsource:///platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) | 
| [cidr.lang.initialBuildingActivity](https://jb.gg/ipe?extensions=cidr.lang.initialBuildingActivity) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCInitialBuildingActivity` | 
| [cidr.lang.languageKindContributor](https://jb.gg/ipe?extensions=cidr.lang.languageKindContributor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCLanguageKindContributor` | 
| [cidr.lang.libraryFileConfigurationProvider](https://jb.gg/ipe?extensions=cidr.lang.libraryFileConfigurationProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCLibraryFileResolveConfigurationProvider` | 
| [cidr.lang.moduleMapManagerRequestor](https://jb.gg/ipe?extensions=cidr.lang.moduleMapManagerRequestor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ModuleMapManagerRequestor` | 
| [cidr.lang.moduleMapSearchRootProvider](https://jb.gg/ipe?extensions=cidr.lang.moduleMapSearchRootProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ModuleMapSearchRootProvider` | 
| [cidr.lang.moduleResolver](https://jb.gg/ipe?extensions=cidr.lang.moduleResolver) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCModuleResolver` | 
| [cidr.lang.ocAdditionalFileSymbolTableBuilder](https://jb.gg/ipe?extensions=cidr.lang.ocAdditionalFileSymbolTableBuilder) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCAdditionalFileSymbolTableBuilder` | 
| [cidr.lang.ocDirectInheritorsSearch](https://jb.gg/ipe?extensions=cidr.lang.ocDirectInheritorsSearch) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) | 
| [cidr.lang.renameHandlerExtension](https://jb.gg/ipe?extensions=cidr.lang.renameHandlerExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCRenameHandlerExtension` | 
| [cidr.lang.renameProcessorExtension](https://jb.gg/ipe?extensions=cidr.lang.renameProcessorExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCRenameProcessorExtension` | 
| [cidr.lang.resourceCompletionProviders](https://jb.gg/ipe?extensions=cidr.lang.resourceCompletionProviders) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCResourceCompletionProviders` | 
| [cidr.lang.resourceFilesProvider](https://jb.gg/ipe?extensions=cidr.lang.resourceFilesProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCResourceFilesProvider` | 
| [cidr.lang.searchHelper](https://jb.gg/ipe?extensions=cidr.lang.searchHelper) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCSearchHelper` | 
| [cidr.lang.serializerProvider](https://jb.gg/ipe?extensions=cidr.lang.serializerProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SerializerProvider` | 
| [cidr.lang.standaloneInspectionToolProvider](https://jb.gg/ipe?extensions=cidr.lang.standaloneInspectionToolProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | [`NotNullProducer`](upsource:///platform/util/src/com/intellij/util/NotNullProducer.java) | 
| [cidr.lang.symbolTableProvider](https://jb.gg/ipe?extensions=cidr.lang.symbolTableProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SymbolTableProvider` | 
| [cidr.lang.testFramework](https://jb.gg/ipe?extensions=cidr.lang.testFramework) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCTestFramework` | 
| [cidr.lang.typeStructureProvider](https://jb.gg/ipe?extensions=cidr.lang.typeStructureProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `PolyglotTypeStructureProvider` | 
| [cidr.projectModel.unloadedResolveContextsManager](https://jb.gg/ipe?extensions=cidr.projectModel.unloadedResolveContextsManager) | `OCUnloadedResolveContextsManager` | 

### CidrProjectModelPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.appleFrameworkFilter](https://jb.gg/ipe?extensions=cidr.lang.appleFrameworkFilter) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `AppleFrameworkFilter` | 
| [cidr.lang.compilerKindProvider](https://jb.gg/ipe?extensions=cidr.lang.compilerKindProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCCompilerKindProvider` | 
| [cidr.lang.compilerResolver](https://jb.gg/ipe?extensions=cidr.lang.compilerResolver) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCCompilerResolver` | 
| [cidr.lang.headerSearchRootFactory](https://jb.gg/ipe?extensions=cidr.lang.headerSearchRootFactory) | `HeadersSearchRootFactory` | 
| [cidr.lang.resolveConfigurationSelector](https://jb.gg/ipe?extensions=cidr.lang.resolveConfigurationSelector) | `OCResolveConfigurationSelector` | 
| [cidr.projectModel.deserializingVetoCondition](https://jb.gg/ipe?extensions=cidr.projectModel.deserializingVetoCondition) | `OCWorkspaceDeserializingVetoCondition` | 
| [cidr.projectModel.msvcPchHelper](https://jb.gg/ipe?extensions=cidr.projectModel.msvcPchHelper) | `OCMsvcPchHelper` | 
| [cidr.projectModel.supportedFileChecker](https://jb.gg/ipe?extensions=cidr.projectModel.supportedFileChecker) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `OCSupportedFileChecker` | 

### CidrToolchainsPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.devEnvironmentChecker](https://jb.gg/ipe?extensions=cidr.devEnvironmentChecker) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `DevEnvironmentChecker` | 

### CidrWorkspaceModelCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.project.is.known.checker](https://jb.gg/ipe?extensions=cidr.project.is.known.checker) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `KnownProjectChecker` | 
| [cidr.project.workspaceProvider](https://jb.gg/ipe?extensions=cidr.project.workspaceProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrWorkspaceProvider` | 

### CidrWorkspaceModelIde.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.markRootActionAvailability](https://jb.gg/ipe?extensions=cidr.markRootActionAvailability) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CidrMarkRootActionAvailability` | 

### CLionCMakeCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cmake.runnerStep](https://jb.gg/ipe?extensions=com.intellij.cmake.runnerStep) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CMakeRunnerStep` | 
| [com.intellij.cmake.targetToConfigProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.targetToConfigProvider) | `CMakeTargetToConfigProvider` | 

### CLionExecutionPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [clion.buildToolWindowActivator.contributor](https://jb.gg/ipe?extensions=clion.buildToolWindowActivator.contributor) | `Contributor` | 
| [clion.compoundConfigurationContext](https://jb.gg/ipe?extensions=clion.compoundConfigurationContext) | `CidrCompoundConfigurationContext` | 
| [clion.externalConfigurationProvider](https://jb.gg/ipe?extensions=clion.externalConfigurationProvider) | `CLionExternalConfigurationProvider` | 

### CLionWizard.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.openWizardStepProvider](https://jb.gg/ipe?extensions=cidr.openWizardStepProvider) | `OpenWizardStepProvider` | 

### CMakePsiPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cmake.completion.environmentProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.completion.environmentProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CMakeEnvironmentVariableProvider` | 
| [com.intellij.cmake.fileLocationProvider](https://jb.gg/ipe?extensions=com.intellij.cmake.fileLocationProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CMakeFileLocationProvider` | 

### com.intellij.cidr.lang.clangd

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cidrCommandLineParser](https://jb.gg/ipe?extensions=com.intellij.cidrCommandLineParser) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `CidrCommandLineParser` | 
| [com.intellij.clangFormatProvider](https://jb.gg/ipe?extensions=com.intellij.clangFormatProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ClangFormatChangeSettingsProvider` | 
