[//]: # (title: Rider Extension Point and Listener List)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

79 Extension Points and 2 Listeners for Rider

See [](extension_point_list.md) for IntelliJ Platform.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## Rider

### Rider - Listeners

| Topic                                                                                                                                                            | Listener                                                                                                                             |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| [RiderStyleCopConfigurable#STYLE_COP_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)       | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [RiderClangFormatConfigurable#CLANG_FORMAT_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener) | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |

### com.jetbrains.dotTrace

| Extension Point                                                                                                 | Implementation            |
|-----------------------------------------------------------------------------------------------------------------|---------------------------|
| [com.jetbrains.dotTrace.runtime.detector](https://jb.gg/ipe?extensions=com.jetbrains.dotTrace.runtime.detector) | `DotTraceRuntimeDetector` |

### com.jetbrains.rider-cpp

| Extension Point                                                                                                                                                                           | Implementation                        |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| [com.jetbrains.rider-cpp.run.configurations.cpp](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `CppConfigurationParametersExtension` |

### DotNetPlugin.xml

| Extension Point                                                                                                                                                                               | Implementation                       |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| [com.intellij.backend.actions.support](https://jb.gg/ipe?extensions=com.intellij.backend.actions.support) ![Non-Dynamic][non-dynamic]                                                         | `RiderActionSupportPolicy`           |
| [com.intellij.backend.auto.import.support](https://jb.gg/ipe?extensions=com.intellij.backend.auto.import.support) ![Non-Dynamic][non-dynamic]                                                 | `RiderAutoImportSupportPolicy`       |
| [com.intellij.backend.autoPopup.support](https://jb.gg/ipe?extensions=com.intellij.backend.autoPopup.support) ![Non-Dynamic][non-dynamic]                                                     | `RiderAutoPopupSupportPolicy`        |
| [com.intellij.backend.markup.adapterFactory](https://jb.gg/ipe?extensions=com.intellij.backend.markup.adapterFactory) ![Non-Dynamic][non-dynamic]                                             | `FrontendMarkupAdapterFactory`       |
| [com.intellij.code.cleanup.support](https://jb.gg/ipe?extensions=com.intellij.code.cleanup.support) ![Non-Dynamic][non-dynamic]                                                               | `RiderCodeCleanupSupportPolicy`      |
| [com.intellij.lang.altEnter](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter) ![Non-Dynamic][non-dynamic]                                                                             | `BulbMenuModelFactory`               |
| [com.intellij.lang.altEnter.popupModelDelegate](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter.popupModelDelegate) ![Non-Dynamic][non-dynamic]                                       | `PopupModelDelegate`                 |
| [com.intellij.projectModelViewUpdater](https://jb.gg/ipe?extensions=com.intellij.projectModelViewUpdater) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                         | `ProjectModelViewUpdater`            |
| [com.intellij.rider.altEnter.layouter](https://jb.gg/ipe?extensions=com.intellij.rider.altEnter.layouter) ![Non-Dynamic][non-dynamic]                                                         | `RiderAltEnterLayouter`              |
| [com.intellij.rider.codeStyleContentConverter](https://jb.gg/ipe?extensions=com.intellij.rider.codeStyleContentConverter) ![Non-Dynamic][non-dynamic]                                         | `RiderCodeStyleContentPageConverter` |
| [com.intellij.rider.credentials.provider](https://jb.gg/ipe?extensions=com.intellij.rider.credentials.provider) ![Non-Dynamic][non-dynamic]                                                   | `ICredentialsProvider`               |
| [com.intellij.rider.defaultVcsRootPolicyExtension](https://jb.gg/ipe?extensions=com.intellij.rider.defaultVcsRootPolicyExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DefaultVcsRootPolicyExtension`      |
| [com.intellij.rider.diagnostics.specialPathsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.diagnostics.specialPathsProvider) ![Non-Dynamic][non-dynamic]                           | `SpecialPathsProvider`               |
| [com.intellij.rider.extraSettingsSync](https://jb.gg/ipe?extensions=com.intellij.rider.extraSettingsSync) ![Non-Dynamic][non-dynamic]                                                         | `ExtraSettingsSync`                  |
| [com.intellij.rider.fileBreadcrumbExtensions](https://jb.gg/ipe?extensions=com.intellij.rider.fileBreadcrumbExtensions)                                                                       | `CustomFileBreadcrumbExtensions`     |
| [com.intellij.rider.namingPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.namingPageProvider) ![Non-Dynamic][non-dynamic]                                                       | `NamingPageProvider`                 |
| [com.intellij.rider.protocol.hostFlagsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.protocol.hostFlagsProvider) ![Non-Dynamic][non-dynamic]                                       | `RiderBackendFlagsProvider`          |
| [com.intellij.rider.wrappedMergeableIconProvider](https://jb.gg/ipe?extensions=com.intellij.rider.wrappedMergeableIconProvider) ![Non-Dynamic][non-dynamic]                                   | `RiderWrappedMergeableIconProvider`  |
| [com.intellij.solutionLoadNotification](https://jb.gg/ipe?extensions=com.intellij.solutionLoadNotification) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                       | `SolutionLoadNotification`           |

### DotNetProjectView.xml

| Extension Point                                                                                                                                                         | Implementation             |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| [com.intellij.rider.fileModuleProvider](https://jb.gg/ipe?extensions=com.intellij.rider.fileModuleProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `IRiderFileModuleProvider` |
| [com.intellij.rider.workspaceExtension](https://jb.gg/ipe?extensions=com.intellij.rider.workspaceExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderWorkspaceExtension`  |

### intellij.rider.cpp.debugger

| Extension Point                                                                                                                             | Implementation        |
|---------------------------------------------------------------------------------------------------------------------------------------------|-----------------------|
| [com.intellij.rider.cpp.debuggerSettings](https://jb.gg/ipe?extensions=com.intellij.rider.cpp.debuggerSettings) ![Non-Dynamic][non-dynamic] | `CppDebuggerSettings` |

### org.jetbrains.plugins.clion.radler

| Extension Point                                                                                                                                   | Implementation            |
|---------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|
| [com.intellij.rider.refactoringPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.refactoringPageProvider) ![Non-Dynamic][non-dynamic] | `RefactoringPageProvider` |

### rider-plugins-appender.cloudconfig.xml

| Extension Point                                                                                                                                                                   | Implementation |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|
| [com.intellij.rider.settings.machineDependentBackendSetting](https://jb.gg/ipe?extensions=com.intellij.rider.settings.machineDependentBackendSetting) ![Non-Dynamic][non-dynamic] | `n/a`          |

### rider-plugins-appender.docker.xml

| Extension Point                                                                                                                                                           | Implementation              |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
| [com.intellij.rider.dockerDebugProvider](https://jb.gg/ipe?extensions=com.intellij.rider.dockerDebugProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `IRiderDockerDebugProvider` |

### rider-plugins-appender.javascript.xml

| Extension Point                                                                                                                                                                       | Implementation                        |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| [JavaScript.packageJson.configuration.handler](https://jb.gg/ipe?extensions=JavaScript.packageJson.configuration.handler) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderPackageJsonConfiguratorHandler` |

### RiderCppCore.xml

| Extension Point                                                                                                                                                               | Implementation            |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|
| [com.jetbrains.rider.cpp.fileType.cppFileTypeIconProvider](https://jb.gg/ipe?extensions=com.jetbrains.rider.cpp.fileType.cppFileTypeIconProvider) ![Non-Dynamic][non-dynamic] | `CppFileTypeIconProvider` |

### RiderExtensionPoints.xml

| Extension Point                                                                                                                                                                                                                                                     | Implementation                                 |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------|
| [com.intellij.dotNetRuntimeType](https://jb.gg/ipe?extensions=com.intellij.dotNetRuntimeType) ![Non-Dynamic][non-dynamic]                                                                                                                                           | `DotNetRuntimeType`                            |
| [com.intellij.fileSystemExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.fileSystemExplorerCustomization) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                               | `FileSystemExplorerCustomization`              |
| [com.intellij.moveProviderExtension](https://jb.gg/ipe?extensions=com.intellij.moveProviderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                                   | `MoveProviderExtension`                        |
| [com.intellij.nestingRulesLanguageExtensions](https://jb.gg/ipe?extensions=com.intellij.nestingRulesLanguageExtensions) ![Non-Dynamic][non-dynamic]                                                                                                                 | `RiderNestingRulesLanguageExtensions`          |
| [com.intellij.nugetCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.nugetCredentialProvider) ![Non-Dynamic][non-dynamic]                                                                                                                               | `NuGetCredentialProvider`                      |
| [com.intellij.projectModelViewExtensions](https://jb.gg/ipe?extensions=com.intellij.projectModelViewExtensions) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                         | `ProjectModelViewExtensions`                   |
| [com.intellij.projectTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.projectTemplateProvider) ![Non-Dynamic][non-dynamic]                                                                                                                               | `RiderProjectTemplateProvider`                 |
| [com.intellij.rider.ProfileActionPrinter](https://jb.gg/ipe?extensions=com.intellij.rider.ProfileActionPrinter) ![Non-Dynamic][non-dynamic]                                                                                                                         | `ProfileActionPrinter`                         |
| [com.intellij.rider.ProjectTypesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.ProjectTypesProvider) ![Non-Dynamic][non-dynamic]                                                                                                                         | `RiderProjectTypesProvider`                    |
| [com.intellij.rider.SolutionFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.SolutionFileTypesProvider) ![Non-Dynamic][non-dynamic]                                                                                                               | `SolutionFileTypesProvider`                    |
| [com.intellij.rider.action.technical.support.info.provider](https://jb.gg/ipe?extensions=com.intellij.rider.action.technical.support.info.provider) ![Non-Dynamic][non-dynamic]                                                                                     | `RiderTechnicalSupportInfoProvider`            |
| [com.intellij.rider.android.project.validator](https://jb.gg/ipe?extensions=com.intellij.rider.android.project.validator) ![Non-Dynamic][non-dynamic]                                                                                                               | `ICustomAndroidProjectValidator`               |
| [com.intellij.rider.backendCrashAnalyser](https://jb.gg/ipe?extensions=com.intellij.rider.backendCrashAnalyser) ![Non-Dynamic][non-dynamic]                                                                                                                         | `BackendCrashAnalyzer`                         |
| [com.intellij.rider.breakpoint.customPanelProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPanelProvider) ![Non-Dynamic][non-dynamic]                                                                                                     | `IDotNetLineBreakpointCustomPanelsProvider`    |
| [com.intellij.rider.breakpoint.customPopupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPopupActionsProvider) ![Non-Dynamic][non-dynamic]                                                                                       | `IDotNetLineBreakpointPopupActionsProvider`    |
| [com.intellij.rider.cleanupAction](https://jb.gg/ipe?extensions=com.intellij.rider.cleanupAction) ![Non-Dynamic][non-dynamic]                                                                                                                                       | `CleanupAction`                                |
| [com.intellij.rider.codeLens.vcsDeclarationRangesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.codeLens.vcsDeclarationRangesProvider) ![Non-Dynamic][non-dynamic]                                                                                       | `VcsDeclarationRangesProvider`                 |
| [com.intellij.rider.codeLensProvider](https://jb.gg/ipe?extensions=com.intellij.rider.codeLensProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic]                                                                                                       | `CodeLensProvider`                             |
| [com.intellij.rider.completion.preselectionStrategy](https://jb.gg/ipe?extensions=com.intellij.rider.completion.preselectionStrategy) ![Non-Dynamic][non-dynamic]                                                                                                   | `RiderFrontendLanguagesPreselectionStrategy`   |
| [com.intellij.rider.configurationExecutorExtension](https://jb.gg/ipe?extensions=com.intellij.rider.configurationExecutorExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                     | `RiderConfigurationExecutorExtension`          |
| [com.intellij.rider.configurationLaunchSettingsExtension](https://jb.gg/ipe?extensions=com.intellij.rider.configurationLaunchSettingsExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                         | `RiderConfigurationLaunchSettingsExtension`    |
| [com.intellij.rider.debug.breakpoint.handler.factory](https://jb.gg/ipe?extensions=com.intellij.rider.debug.breakpoint.handler.factory) ![Non-Dynamic][non-dynamic]                                                                                                 | `IDotNetSupportedBreakpointHandlerFactory`     |
| [com.intellij.rider.debuggerSupportPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.debuggerSupportPolicy) ![Non-Dynamic][non-dynamic]                                                                                                                       | `RiderDebuggerSupportPolicy`                   |
| [com.intellij.rider.editSourceSuppressor](https://jb.gg/ipe?extensions=com.intellij.rider.editSourceSuppressor) ![Non-Dynamic][non-dynamic]                                                                                                                         | `RiderEditSourceSuppressor`                    |
| [com.intellij.rider.fileTemplating.postCreateAction](https://jb.gg/ipe?extensions=com.intellij.rider.fileTemplating.postCreateAction) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                   | `RiderNewFileFromTemplateExtension`            |
| [com.intellij.rider.newRunConfigurationTreeGroupingProvider](https://jb.gg/ipe?extensions=com.intellij.rider.newRunConfigurationTreeGroupingProvider) ![Non-Dynamic][non-dynamic]                                                                                   | `RiderNewRunConfigurationTreeGroupingProvider` |
| [com.intellij.rider.patchCommandLine](https://jb.gg/ipe?extensions=com.intellij.rider.patchCommandLine) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                                 | `PatchCommandLineExtension`                    |
| [com.intellij.rider.pencils.filters.provider](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.filters.provider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                 | `PencilsFiltersProvider`                       |
| [com.intellij.rider.pencils.inspectionToolGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.inspectionToolGroup) ![Non-Dynamic][non-dynamic]                                                                                                           | `n/a`                                          |
| [com.intellij.rider.pencils.pencilsFilterGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.pencilsFilterGroup) ![Non-Dynamic][non-dynamic]                                                                                                             | `n/a`                                          |
| [com.intellij.rider.projectView.actions.projectTemplating.backend.reSharperProjectTemplateCustomizer](https://jb.gg/ipe?extensions=com.intellij.rider.projectView.actions.projectTemplating.backend.reSharperProjectTemplateCustomizer) ![Non-Dynamic][non-dynamic] | `ReSharperProjectTemplateCustomizer`           |
| [com.intellij.rider.publishConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.rider.publishConfigurationProvider) ![Non-Dynamic][non-dynamic]                                                                                                         | `RiderContextPublishProvider`                  |
| [com.intellij.rider.refactoringPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.refactoringPageProvider) ![Non-Dynamic][non-dynamic]                                                                                                                   | `RefactoringPageProvider`                      |
| [com.intellij.rider.run.configurations.dotNetExe](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.dotNetExe) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                         | `DotNetExeConfigurationExtension`              |
| [com.intellij.rider.run.configurations.host](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.host) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                   | `RunConfigurationHostExtensions`               |
| [com.intellij.rider.run.configurations.host.executor](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.host.executor) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                 | `RunConfigurationHostExecutorExtensions`       |
| [com.intellij.rider.run.configurations.launchSettings](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                               | `LaunchSettingsConfigurationExtension`         |
| [com.intellij.rider.run.configurations.launchSettings.command](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings.command) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                               | `LaunchSettingsCommandExtension`               |
| [com.intellij.rider.run.configurations.project](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.project) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                             | `DotNetProjectConfigurationExtension`          |
| [com.intellij.rider.run.configurations.riderCoreDumpConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.riderCoreDumpConfigurationProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                       | `RiderCoreDumpConfigurationProvider`           |
| [com.intellij.rider.runToPopupShowPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.runToPopupShowPolicy) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                         | `RiderRunToPopupShowPolicy`                    |
| [com.intellij.rider.runWidgetSuspenderExtension](https://jb.gg/ipe?extensions=com.intellij.rider.runWidgetSuspenderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                           | `RunWidgetSuspenderExtension`                  |
| [com.intellij.rider.unitTesting.actionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.actionsProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                           | `RiderUnitTestActionsProvider`                 |
| [com.intellij.rider.unitTesting.sessionHandler](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.sessionHandler) ![Non-Dynamic][non-dynamic]                                                                                                             | `IRiderUnitTestDebuggerSessionsHandler`        |
| [com.intellij.rider.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.rider.writingAccessProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                       | `RiderDebugWritingAccessProvider`              |
| [com.intellij.rider.xaml.preview.editor](https://jb.gg/ipe?extensions=com.intellij.rider.xaml.preview.editor) ![Non-Dynamic][non-dynamic]                                                                                                                           | `XamlPreviewEditorExtension`                   |
| [com.intellij.solutionExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerCustomization) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                   | `SolutionExplorerCustomization`                |
| [com.intellij.solutionExplorerRootProvider](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerRootProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                     | `SolutionExplorerRootProvider`                 |
| [com.intellij.solutionManagerExtensions](https://jb.gg/ipe?extensions=com.intellij.solutionManagerExtensions) ![Non-Dynamic][non-dynamic]                                                                                                                           | `SolutionManagerExtensions`                    |
| [com.intellij.solutionViewPsiNodeNavigator](https://jb.gg/ipe?extensions=com.intellij.solutionViewPsiNodeNavigator) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level]                                                                                     | `SolutionViewPsiNodeNavigator`                 |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
