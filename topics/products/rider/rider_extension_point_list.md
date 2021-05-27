[//]: # (title: Rider Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

61 Extension Points (EP) for Rider

See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>
                                     
## Rider

### com.jetbrains.rider-cpp
com.jetbrains.rider-cpp

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.rider-cpp.run.configurations.cpp](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `CppConfigurationParametersExtension` | 


### DotNetPlugin.xml
DotNetPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backend.actions.support](https://jb.gg/ipe?extensions=com.intellij.backend.actions.support) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderActionSupportPolicy` | 
| [com.intellij.backend.auto.import.support](https://jb.gg/ipe?extensions=com.intellij.backend.auto.import.support) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderAutoImportSupportPolicy` | 
| [com.intellij.backend.autoPopup.support](https://jb.gg/ipe?extensions=com.intellij.backend.autoPopup.support) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderAutoPopupSupportPolicy` | 
| [com.intellij.backend.markup.adapterFactory](https://jb.gg/ipe?extensions=com.intellij.backend.markup.adapterFactory) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `FrontendMarkupAdapterFactory` | 
| [com.intellij.code.cleanup.support](https://jb.gg/ipe?extensions=com.intellij.code.cleanup.support) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderCodeCleanupSupportPolicy` | 
| [com.intellij.lang.altEnter](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `BulbMenuModelFactory` | 
| [com.intellij.lang.altEnter.popupModelDelegate](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter.popupModelDelegate) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `PopupModelDelegate` | 
| [com.intellij.projectModelViewUpdater](https://jb.gg/ipe?extensions=com.intellij.projectModelViewUpdater) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `ProjectModelViewUpdater` | 
| [com.intellij.rider.altEnter.layouter](https://jb.gg/ipe?extensions=com.intellij.rider.altEnter.layouter) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderAltEnterLayouter` | 
| [com.intellij.rider.credentials.provider](https://jb.gg/ipe?extensions=com.intellij.rider.credentials.provider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ICredentialsProvider` | 
| [com.intellij.rider.defaultVcsRootPolicyExtension](https://jb.gg/ipe?extensions=com.intellij.rider.defaultVcsRootPolicyExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `DefaultVcsRootPolicyExtension` | 
| [com.intellij.rider.diagnostics.specialPathsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.diagnostics.specialPathsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SpecialPathsProvider` | 
| [com.intellij.rider.extraSettingsSync](https://jb.gg/ipe?extensions=com.intellij.rider.extraSettingsSync) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ExtraSettingsSync` | 
| [com.intellij.rider.protocol.hostFlagsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.protocol.hostFlagsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ReSharperHostFlagsProvider` | 
| [com.intellij.rider.wrappedMergeableIconProvider](https://jb.gg/ipe?extensions=com.intellij.rider.wrappedMergeableIconProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderWrappedMergeableIconProvider` | 
| [com.intellij.solutionLoadNotification](https://jb.gg/ipe?extensions=com.intellij.solutionLoadNotification) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `SolutionLoadNotification` | 

### DotNetProjectView.xml
DotNetProjectView.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.fileModuleProvider](https://jb.gg/ipe?extensions=com.intellij.rider.fileModuleProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `IRiderFileModuleProvider` | 
| [com.intellij.rider.workspaceExtension](https://jb.gg/ipe?extensions=com.intellij.rider.workspaceExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `RiderWorkspaceExtension` | 

### intellij.rider.cpp.debugger
intellij.rider.cpp.debugger

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.cpp.debuggerSettings](https://jb.gg/ipe?extensions=com.intellij.rider.cpp.debuggerSettings) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CppDebuggerSettings` | 

### RiderExtensionPoints.xml
RiderExtensionPoints.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeLensPainterProvider](https://jb.gg/ipe?extensions=com.intellij.codeLensPainterProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ICodeLensEntryBasePainter` | 
| [com.intellij.dotNetRuntimeType](https://jb.gg/ipe?extensions=com.intellij.dotNetRuntimeType) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `DotNetRuntimeType` | 
| [com.intellij.fileSystemExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.fileSystemExplorerCustomization) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `FileSystemExplorerCustomization` | 
| [com.intellij.moveProviderExtension](https://jb.gg/ipe?extensions=com.intellij.moveProviderExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `MoveProviderExtension` | 
| [com.intellij.nestingRulesLanguageExtensions](https://jb.gg/ipe?extensions=com.intellij.nestingRulesLanguageExtensions) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderNestingRulesLanguageExtensions` | 
| [com.intellij.nugetCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.nugetCredentialProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `NuGetCredentialProvider` | 
| [com.intellij.projectModelViewExtensions](https://jb.gg/ipe?extensions=com.intellij.projectModelViewExtensions) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `ProjectModelViewExtensions` | 
| [com.intellij.projectTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.projectTemplateProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderProjectTemplateProvider` | 
| [com.intellij.rider.ProfileActionPrinter](https://jb.gg/ipe?extensions=com.intellij.rider.ProfileActionPrinter) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ProfileActionPrinter` | 
| [com.intellij.rider.ProjectTypesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.ProjectTypesProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderProjectTypesProvider` | 
| [com.intellij.rider.android.project.validator](https://jb.gg/ipe?extensions=com.intellij.rider.android.project.validator) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ICustomAndroidProjectValidator` | 
| [com.intellij.rider.backendCrashAnalyser](https://jb.gg/ipe?extensions=com.intellij.rider.backendCrashAnalyser) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `BackendCrashAnalyzer` | 
| [com.intellij.rider.breakpoint.customPanelProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPanelProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `IDotNetLineBreakpointCustomPanelsProvider` | 
| [com.intellij.rider.breakpoint.customPopupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPopupActionsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `IDotNetLineBreakpointPopupActionsProvider` | 
| [com.intellij.rider.codeLens.vcsDeclarationRangesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.codeLens.vcsDeclarationRangesProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `VcsDeclarationRangesProvider` | 
| [com.intellij.rider.codeLensProvider](https://jb.gg/ipe?extensions=com.intellij.rider.codeLensProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `CodeLensProvider` | 
| [com.intellij.rider.contextHelp](https://jb.gg/ipe?extensions=com.intellij.rider.contextHelp) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderContextHelpExtension` | 
| [com.intellij.rider.debug.breakpoint.handler.factory](https://jb.gg/ipe?extensions=com.intellij.rider.debug.breakpoint.handler.factory) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `IDotNetSupportedBreakpointHandlerFactory` | 
| [com.intellij.rider.debuggerSupportPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.debuggerSupportPolicy) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderDebuggerSupportPolicy` | 
| [com.intellij.rider.namingPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.namingPageProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `NamingPageProvider` | 
| [com.intellij.rider.patchCommandLine](https://jb.gg/ipe?extensions=com.intellij.rider.patchCommandLine) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `PatchCommandLineExtension` | 
| [com.intellij.rider.pencils.filters.provider](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.filters.provider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `PencilsFiltersProvider` | 
| [com.intellij.rider.pencils.inspectionToolGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.inspectionToolGroup) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.rider.pencils.pencilsFilterGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.pencilsFilterGroup) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.rider.projectView.actions.projectTemplating.backend.reSharperProjectTemplateCustomizer](https://jb.gg/ipe?extensions=com.intellij.rider.projectView.actions.projectTemplating.backend.reSharperProjectTemplateCustomizer) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `ReSharperProjectTemplateCustomizer` | 
| [com.intellij.rider.publishConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.rider.publishConfigurationProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RiderContextPublishProvider` | 
| [com.intellij.rider.refactoringPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.refactoringPageProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `RefactoringPageProvider` | 
| [com.intellij.rider.run.configurations.dotNetExe](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.dotNetExe) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `DotNetExeConfigurationExtension` | 
| [com.intellij.rider.run.configurations.host.executor](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.host.executor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `RunConfigurationHostExecutorExtensions` | 
| [com.intellij.rider.run.configurations.launchSettings](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `LaunchSettingsConfigurationExtension` | 
| [com.intellij.rider.run.configurations.launchSettings.command](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings.command) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `LaunchSettingsCommandExtension` | 
| [com.intellij.rider.run.configurations.project](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.project) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `DotNetProjectConfigurationExtension` | 
| [com.intellij.rider.runToPopupShowPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.runToPopupShowPolicy) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `RiderRunToPopupShowPolicy` | 
| [com.intellij.rider.unitTesting.actionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.actionsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `RiderUnitTestActionsProvider` | 
| [com.intellij.rider.unitTesting.sessionHandler](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.sessionHandler) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `IRiderUnitTestDebuggerSessionsHandler` | 
| [com.intellij.rider.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.rider.writingAccessProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `RiderDebugWritingAccessProvider` | 
| [com.intellij.rider.xaml.preview.editor](https://jb.gg/ipe?extensions=com.intellij.rider.xaml.preview.editor) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `XamlPreviewEditorExtension` | 
| [com.intellij.solutionExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerCustomization) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `SolutionExplorerCustomization` | 
| [com.intellij.solutionExplorerRootProvider](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerRootProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `SolutionExplorerRootProvider` | 
| [com.intellij.solutionManagerExtensions](https://jb.gg/ipe?extensions=com.intellij.solutionManagerExtensions) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SolutionManagerExtensions` | 
| [com.intellij.solutionViewPsiNodeNavigator](https://jb.gg/ipe?extensions=com.intellij.solutionViewPsiNodeNavigator) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `SolutionViewPsiNodeNavigator` | 
