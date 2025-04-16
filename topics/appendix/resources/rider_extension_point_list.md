<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /rider/ -->

# Rider Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for Rider.</link-summary>

<tldr>

**Product-Specific Plugin Development**: [Rider](rider.md)

</tldr>

145 Extension Points and 9 Listeners for Rider

See [](intellij_platform_extension_point_list.md) for IntelliJ Platform.

<include from="snippets.topic" element-id="ep_list_legend"/>

## Rider

### Rider – Listeners

| Topic | Listener |
|-------|----------|
| [RiderStyleCopConfigurable#STYLE_COP_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [SSHCredentialsInClipboardNotifier.Companion#SSH_CREDENTIALS_IN_CLIPBOARD_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.debugger.attach.remoting.SSHCredentialsInClipboardNotifier)  | `SSHCredentialsInClipboardNotifier` |
| [DotnetDebuggerSymbolsLoadedListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.debugger.modulesView.actions.DotnetDebuggerSymbolsLoadedListener)  | `DotnetDebuggerSymbolsLoadedListener` |
| [FrontendTypedHandlerManager#BEFORE_TYPING_SENT](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.editorActions.IFrontendTypingListener)  | `IFrontendTypingListener` |
| [RiderDockerDeploymentListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.plugins.appender.docker.deployment.RiderDockerDeploymentListener)  | `RiderDockerDeploymentListener` |
| [AutoAttachDebuggerListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.AutoAttachDebuggerListener)  | `AutoAttachDebuggerListener` |
| [PublishConfigurationValidationListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.configurations.publishing.PublishConfigurationValidationListener)  | `PublishConfigurationValidationListener` |
| [MSBuildEvaluationListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.environment.MSBuildEvaluationListener)  | `MSBuildEvaluationListener` |
| [RiderGlobalBackendProgressListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.services.RiderGlobalProgressHost.RiderGlobalBackendProgressListener)  | `RiderGlobalBackendProgressListener` |


### com.intellij.rider.frontend.customization

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rdclient.rider.completion.helper](https://jb.gg/ipe?extensions=com.intellij.rdclient.rider.completion.helper) | `CompletionHelper` |

### com.jetbrains.dotTrace.dotMemory

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.dotTrace.dotMemory.runtime.detector](https://jb.gg/ipe?extensions=com.jetbrains.dotTrace.dotMemory.runtime.detector) | `DotTraceRuntimeDetector` |

### com.jetbrains.rider-cpp

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.rider-cpp.run.configurations.cpp](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `CppConfigurationParametersExtension` |
| [com.jetbrains.rider-cpp.run.configurations.cpp.launch.profile](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp.launch.profile) ![Non-Dynamic][non-dynamic] | `CppProjectLaunchProfile` |
| [com.jetbrains.rider-cpp.run.configurations.cpp.platformInfoProvider](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp.platformInfoProvider) ![Non-Dynamic][non-dynamic] | `CppPlatformInfoProvider` |
| [com.jetbrains.rider-cpp.run.configurations.cpp.platformPluginPromotion](https://jb.gg/ipe?extensions=com.jetbrains.rider-cpp.run.configurations.cpp.platformPluginPromotion) ![Non-Dynamic][non-dynamic] ![Internal][internal] | `CppPlatformPluginPromotion` |
| [rider.cpp.debugProcessExtension](https://jb.gg/ipe?extensions=rider.cpp.debugProcessExtension) | `RiderCppDebugProcessExtension` |

### com.jetbrains.rider.razor

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.blazorDialectSubstitutor](https://jb.gg/ipe?extensions=com.intellij.rider.blazorDialectSubstitutor) ![Non-Dynamic][non-dynamic] | `BlazorHtmlDialectSubstitutor` |
| [com.intellij.rider.webTypingAssistClassifier](https://jb.gg/ipe?extensions=com.intellij.rider.webTypingAssistClassifier) | `WebTypingAssistClassifier` |

### DotNetPluginEP.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backend.actions.support](https://jb.gg/ipe?extensions=com.intellij.backend.actions.support) | `RiderActionSupportPolicy` |
| [com.intellij.backend.auto.import.support](https://jb.gg/ipe?extensions=com.intellij.backend.auto.import.support) | `RiderAutoImportSupportPolicy` |
| [com.intellij.backend.autoPopup.support](https://jb.gg/ipe?extensions=com.intellij.backend.autoPopup.support) | `RiderAutoPopupSupportPolicy` |
| [com.intellij.backend.markup.adapterFactory](https://jb.gg/ipe?extensions=com.intellij.backend.markup.adapterFactory) ![Non-Dynamic][non-dynamic] | `FrontendMarkupAdapterFactory` |
| [com.intellij.backend.typedHandler](https://jb.gg/ipe?extensions=com.intellij.backend.typedHandler) | `FrontendTypedHandler` |
| [com.intellij.completion.completionSessionStrategy](https://jb.gg/ipe?extensions=com.intellij.completion.completionSessionStrategy) | `CompletionSessionStrategy` |
| [com.intellij.frontend.completion.helper](https://jb.gg/ipe?extensions=com.intellij.frontend.completion.helper) | `ICompletionHelper` |
| [com.intellij.lang.altEnter](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter) | `BulbMenuModelFactory` |
| [com.intellij.lang.altEnter.popupModelDelegate](https://jb.gg/ipe?extensions=com.intellij.lang.altEnter.popupModelDelegate) | `PopupModelDelegate` |
| [com.intellij.navbar.members.support](https://jb.gg/ipe?extensions=com.intellij.navbar.members.support) | `RiderNavBarMembersSupport` |
| [com.intellij.projectModelViewUpdater](https://jb.gg/ipe?extensions=com.intellij.projectModelViewUpdater) ![Project-Level][project-level] | `ProjectModelViewUpdater` |
| [com.intellij.rdclient.actionsDataContextProvider](https://jb.gg/ipe?extensions=com.intellij.rdclient.actionsDataContextProvider) ![Internal][internal] | `FrontendActionsDataContextProvider` |
| [com.intellij.rdclient.preemptiveCompletionSuppressor](https://jb.gg/ipe?extensions=com.intellij.rdclient.preemptiveCompletionSuppressor) | `PreemptiveCompletionSuppressor` |
| [com.intellij.rdclient.typingPolicy](https://jb.gg/ipe?extensions=com.intellij.rdclient.typingPolicy) | `CustomTypingSessionPolicy` |
| [com.intellij.rider.action.fallback.strategy](https://jb.gg/ipe?extensions=com.intellij.rider.action.fallback.strategy) | `RiderAsyncBackendDelegatingActionFallbackStrategy` |
| [com.intellij.rider.additionalQuickDocProvider](https://jb.gg/ipe?extensions=com.intellij.rider.additionalQuickDocProvider) | `AdditionalQuickDocProvider` |
| [com.intellij.rider.altEnter.layouter](https://jb.gg/ipe?extensions=com.intellij.rider.altEnter.layouter) | `RiderAltEnterLayouter` |
| [com.intellij.rider.backendCrashAnalyser](https://jb.gg/ipe?extensions=com.intellij.rider.backendCrashAnalyser) | `BackendCrashAnalyzer` |
| [com.intellij.rider.backendLogXmlPathProvider](https://jb.gg/ipe?extensions=com.intellij.rider.backendLogXmlPathProvider) | `RiderCustomBackendLogXmlPathProvider` |
| [com.intellij.rider.codeStyleContentConverter](https://jb.gg/ipe?extensions=com.intellij.rider.codeStyleContentConverter) | `RiderCodeStyleContentPageConverter` |
| [com.intellij.rider.credentials.provider](https://jb.gg/ipe?extensions=com.intellij.rider.credentials.provider) | `ICredentialsProvider` |
| [com.intellij.rider.diagnostics.specialPathsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.diagnostics.specialPathsProvider) | `SpecialPathsProvider` |
| [com.intellij.rider.documentBehaviour](https://jb.gg/ipe?extensions=com.intellij.rider.documentBehaviour) | `RiderDocumentBehaviour` |
| [com.intellij.rider.editors.customBackendLanguageSupport](https://jb.gg/ipe?extensions=com.intellij.rider.editors.customBackendLanguageSupport) | `RiderCustomBackendLanguageSupport` |
| [com.intellij.rider.extraSettingsSync](https://jb.gg/ipe?extensions=com.intellij.rider.extraSettingsSync) | `ExtraSettingsSync` |
| [com.intellij.rider.fileBreadcrumbExtensions](https://jb.gg/ipe?extensions=com.intellij.rider.fileBreadcrumbExtensions) | `CustomFileBreadcrumbExtensions` |
| [com.intellij.rider.grave.filter](https://jb.gg/ipe?extensions=com.intellij.rider.grave.filter) ![Experimental][experimental] | `RiderHighlightingGraveFilter` |
| [com.intellij.rider.ideaInspectionBackendSuppressionSupport](https://jb.gg/ipe?extensions=com.intellij.rider.ideaInspectionBackendSuppressionSupport) | `IdeaInspectionBackendSuppressionSupport` |
| [com.intellij.rider.namingPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.namingPageProvider) | `NamingPageProvider` |
| [com.intellij.rider.protocol.hostEnvProvider](https://jb.gg/ipe?extensions=com.intellij.rider.protocol.hostEnvProvider) | `RiderBackendEnvProvider` |
| [com.intellij.rider.riderApplicationPreloadListener](https://jb.gg/ipe?extensions=com.intellij.rider.riderApplicationPreloadListener) | `RiderApplicationPreloadListener` |
| [com.intellij.rider.smartTabsBackendSynchronizer](https://jb.gg/ipe?extensions=com.intellij.rider.smartTabsBackendSynchronizer) ![Non-Dynamic][non-dynamic] | `RiderSmartTabsBackendSynchronizer` |
| [com.intellij.rider.startupListener](https://jb.gg/ipe?extensions=com.intellij.rider.startupListener) ![Internal][internal] | `RiderInitialStartupListener` |
| [com.intellij.selfProfilingPaths.customizer](https://jb.gg/ipe?extensions=com.intellij.selfProfilingPaths.customizer) | `DotnetSelfProfilerPathsCustomizer` |
| [com.intellij.solutionLoadNotification](https://jb.gg/ipe?extensions=com.intellij.solutionLoadNotification) ![Project-Level][project-level] | `SolutionLoadNotification` |

### DotNetProjectView.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.projectModelIconProvider](https://jb.gg/ipe?extensions=com.intellij.rider.projectModelIconProvider) | `ProjectModelIconProvider` |
| [com.intellij.rider.workspaceCountableProjectsPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.workspaceCountableProjectsPolicy) | `CountableProjectsPolicy` |

### intellij.rider.cpp.debugger

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.cpp.debuggerSettings](https://jb.gg/ipe?extensions=com.intellij.rider.cpp.debuggerSettings) ![Non-Dynamic][non-dynamic] | `CppDebuggerSettings` |

### intellij.rider.plugins.appender.database.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.database.connectionStringRetriever](https://jb.gg/ipe?extensions=com.intellij.rider.database.connectionStringRetriever) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DatabaseConnectionUrlRetriever` |
| [com.intellij.rider.database.connectionStringToJdbcUrlMapper](https://jb.gg/ipe?extensions=com.intellij.rider.database.connectionStringToJdbcUrlMapper) ![Project-Level][project-level] | `ConnectionStringToJdbcUrlConverter` |
| [com.intellij.rider.database.connectionStringsFactory](https://jb.gg/ipe?extensions=com.intellij.rider.database.connectionStringsFactory) ![Project-Level][project-level] | `ConnectionStringsFactory` |
| [com.intellij.rider.database.connectionStringsFinder](https://jb.gg/ipe?extensions=com.intellij.rider.database.connectionStringsFinder) ![Project-Level][project-level] | `ConnectionStringsFinder` |
| [com.intellij.rider.database.dotnetDataProvider](https://jb.gg/ipe?extensions=com.intellij.rider.database.dotnetDataProvider) ![Project-Level][project-level] | `DotnetDataProvider` |
| [com.intellij.rider.database.jdbcUrlToConnectionStringConverter](https://jb.gg/ipe?extensions=com.intellij.rider.database.jdbcUrlToConnectionStringConverter) ![Project-Level][project-level] | `JdbcUrlToConnectionStringConverter` |
| [com.intellij.rider.database.schemaCompareDataModelCreatedListener](https://jb.gg/ipe?extensions=com.intellij.rider.database.schemaCompareDataModelCreatedListener) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SchemaCompareDataModelCreatedListener` |

### intellij.rider.plugins.appender.docker.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.dockerDebugProvider](https://jb.gg/ipe?extensions=com.intellij.rider.dockerDebugProvider) ![Non-Dynamic][non-dynamic] ![Internal][internal] | `RiderDockerDebugProvider` |
| [com.intellij.rider.dockerDeploymentTransformer](https://jb.gg/ipe?extensions=com.intellij.rider.dockerDeploymentTransformer) ![Non-Dynamic][non-dynamic] ![Internal][internal] | `RiderDockerDeploymentTransformer` |

### intellij.rider.plugins.appender.javascript.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.packageJson.configuration.handler](https://jb.gg/ipe?extensions=JavaScript.packageJson.configuration.handler) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderPackageJsonConfiguratorHandler` |

### intellij.rider.rdclient.languages.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.rdclient.breakingWorkflowChangeInterceptor](https://jb.gg/ipe?extensions=com.intellij.rider.rdclient.breakingWorkflowChangeInterceptor) ![Non-Dynamic][non-dynamic] ![Experimental][experimental] ![Internal][internal] | `RiderBreakingWorkflowChangeInterceptor` |

### org.jetbrains.plugins.clion.radler

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.radler.externalSymbolsProvider](https://jb.gg/ipe?extensions=cidr.radler.externalSymbolsProvider) | `RadExternalSymbolsProvider` |
| [com.intellij.rider.refactoringPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.refactoringPageProvider) ![Non-Dynamic][non-dynamic] | `RefactoringPageProvider` |

### RiderCwmCoreEP.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.client.typedHandler](https://jb.gg/ipe?extensions=com.intellij.rider.client.typedHandler) | `RiderClientLookupTypedHandler` |

### RiderDebugger.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.debugger.evaluation.advice](https://jb.gg/ipe?extensions=com.intellij.rider.debugger.evaluation.advice) | `DebugEvalAdviceProvider` |

### RiderExtensionPoints.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.fileSystemExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.fileSystemExplorerCustomization) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `FileSystemExplorerCustomization` |
| [com.intellij.moveProviderExtension](https://jb.gg/ipe?extensions=com.intellij.moveProviderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `MoveProviderExtension` |
| [com.intellij.nestingRulesLanguageExtensions](https://jb.gg/ipe?extensions=com.intellij.nestingRulesLanguageExtensions) ![Non-Dynamic][non-dynamic] | `RiderNestingRulesLanguageExtensions` |
| [com.intellij.nugetCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.nugetCredentialProvider) ![Non-Dynamic][non-dynamic] | `NuGetCredentialProvider` |
| [com.intellij.openDirectoryExtensions](https://jb.gg/ipe?extensions=com.intellij.openDirectoryExtensions) ![Non-Dynamic][non-dynamic] | `OpenDirectoryExtensions` |
| [com.intellij.projectModelViewExtensions](https://jb.gg/ipe?extensions=com.intellij.projectModelViewExtensions) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ProjectModelViewExtensions` |
| [com.intellij.projectTemplateCustomizer](https://jb.gg/ipe?extensions=com.intellij.projectTemplateCustomizer) ![Non-Dynamic][non-dynamic] | `ProjectTemplateCustomizer` |
| [com.intellij.projectTemplateDialogProvider](https://jb.gg/ipe?extensions=com.intellij.projectTemplateDialogProvider) ![Non-Dynamic][non-dynamic] | `ProjectTemplateDialogProvider` |
| [com.intellij.projectTemplateProviderNew](https://jb.gg/ipe?extensions=com.intellij.projectTemplateProviderNew) ![Non-Dynamic][non-dynamic] | `ProjectTemplateProvider` |
| [com.intellij.rider.ProjectTypesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.ProjectTypesProvider) ![Non-Dynamic][non-dynamic] | `RiderProjectTypesProvider` |
| [com.intellij.rider.SolutionFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.SolutionFileTypesProvider) ![Non-Dynamic][non-dynamic] | `SolutionFileTypesProvider` |
| [com.intellij.rider.action.technical.support.info.provider](https://jb.gg/ipe?extensions=com.intellij.rider.action.technical.support.info.provider) ![Non-Dynamic][non-dynamic] | `RiderTechnicalSupportInfoProvider` |
| [com.intellij.rider.breakpoint.customPanelProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPanelProvider) ![Non-Dynamic][non-dynamic] | `IDotNetLineBreakpointCustomPanelsProvider` |
| [com.intellij.rider.breakpoint.customPopupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.breakpoint.customPopupActionsProvider) ![Non-Dynamic][non-dynamic] | `IDotNetLineBreakpointPopupActionsProvider` |
| [com.intellij.rider.build.riderBuildConsoleDecorator](https://jb.gg/ipe?extensions=com.intellij.rider.build.riderBuildConsoleDecorator) ![Project-Level][project-level] | `RiderBuildConsoleDecorator` |
| [com.intellij.rider.buildButtonModeProvider](https://jb.gg/ipe?extensions=com.intellij.rider.buildButtonModeProvider) | `BuildButtonModeProvider` |
| [com.intellij.rider.codeLens.vcsDeclarationRangesProvider](https://jb.gg/ipe?extensions=com.intellij.rider.codeLens.vcsDeclarationRangesProvider) ![Non-Dynamic][non-dynamic] | `VcsDeclarationRangesProvider` |
| [com.intellij.rider.completion.csharpIdentifierPartHelper](https://jb.gg/ipe?extensions=com.intellij.rider.completion.csharpIdentifierPartHelper) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `CSharpIdentifierPartHelper` |
| [com.intellij.rider.completion.preselectionStrategy](https://jb.gg/ipe?extensions=com.intellij.rider.completion.preselectionStrategy) ![Non-Dynamic][non-dynamic] | `RiderFrontendLanguagesPreselectionStrategy` |
| [com.intellij.rider.configurationExecutorExtension](https://jb.gg/ipe?extensions=com.intellij.rider.configurationExecutorExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderConfigurationExecutorExtension` |
| [com.intellij.rider.configurationLaunchSettingsExtension](https://jb.gg/ipe?extensions=com.intellij.rider.configurationLaunchSettingsExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderConfigurationLaunchSettingsExtension` |
| [com.intellij.rider.consoleFilter](https://jb.gg/ipe?extensions=com.intellij.rider.consoleFilter) | `RiderConsoleFilterExtension` |
| [com.intellij.rider.contributedLanguageElementNameCrawler](https://jb.gg/ipe?extensions=com.intellij.rider.contributedLanguageElementNameCrawler) | `RiderContributedLanguageElementNameCrawler` |
| [com.intellij.rider.debug.breakpoint.handler.factory](https://jb.gg/ipe?extensions=com.intellij.rider.debug.breakpoint.handler.factory) ![Non-Dynamic][non-dynamic] | `IDotNetSupportedBreakpointHandlerFactory` |
| [com.intellij.rider.debugger.editAndContinue.dotNetEncInfoAutodetect](https://jb.gg/ipe?extensions=com.intellij.rider.debugger.editAndContinue.dotNetEncInfoAutodetect) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetEncInfoAutodetect` |
| [com.intellij.rider.debugger.value.evaluator.factory](https://jb.gg/ipe?extensions=com.intellij.rider.debugger.value.evaluator.factory) ![Non-Dynamic][non-dynamic] | `RiderCustomComponentEvaluatorFactory` |
| [com.intellij.rider.debugger.value.presenter](https://jb.gg/ipe?extensions=com.intellij.rider.debugger.value.presenter) ![Non-Dynamic][non-dynamic] | `RiderDebuggerValuePresenter` |
| [com.intellij.rider.debuggerSupportPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.debuggerSupportPolicy) ![Non-Dynamic][non-dynamic] | `RiderDebuggerSupportPolicy` |
| [com.intellij.rider.editSourceSuppressor](https://jb.gg/ipe?extensions=com.intellij.rider.editSourceSuppressor) ![Non-Dynamic][non-dynamic] | `RiderEditSourceSuppressor` |
| [com.intellij.rider.extendedCodeStructure](https://jb.gg/ipe?extensions=com.intellij.rider.extendedCodeStructure) ![Non-Dynamic][non-dynamic] | `RiderExtendedFileStructure` |
| [com.intellij.rider.externalDirectoryProvider](https://jb.gg/ipe?extensions=com.intellij.rider.externalDirectoryProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ExternalDirectoryProvider` |
| [com.intellij.rider.fileTemplating.postCreateAction](https://jb.gg/ipe?extensions=com.intellij.rider.fileTemplating.postCreateAction) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderNewFileFromTemplateExtension` |
| [com.intellij.rider.filesIndexingRuleProvider](https://jb.gg/ipe?extensions=com.intellij.rider.filesIndexingRuleProvider) ![Non-Dynamic][non-dynamic] | `RiderFilesIndexingRuleProvider` |
| [com.intellij.rider.findPopupProjectScopeProvider](https://jb.gg/ipe?extensions=com.intellij.rider.findPopupProjectScopeProvider) | `FindPopupProjectScopeProvider` |
| [com.intellij.rider.godotDetector](https://jb.gg/ipe?extensions=com.intellij.rider.godotDetector) | `GodotDetector` |
| [com.intellij.rider.gotoCustomizer](https://jb.gg/ipe?extensions=com.intellij.rider.gotoCustomizer) | `GotoCustomizer` |
| [com.intellij.rider.guidPresenter](https://jb.gg/ipe?extensions=com.intellij.rider.guidPresenter) ![Project-Level][project-level] | `GuidGeneratorPresenter` |
| [com.intellij.rider.newFileListener](https://jb.gg/ipe?extensions=com.intellij.rider.newFileListener) ![Project-Level][project-level] | `RiderNewFileListener` |
| [com.intellij.rider.newRunConfigurationTreeGroupingProvider](https://jb.gg/ipe?extensions=com.intellij.rider.newRunConfigurationTreeGroupingProvider) ![Non-Dynamic][non-dynamic] | `RiderNewRunConfigurationTreeGroupingProvider` |
| [com.intellij.rider.patchCommandLine](https://jb.gg/ipe?extensions=com.intellij.rider.patchCommandLine) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `PatchCommandLineExtension` |
| [com.intellij.rider.pencils.filters.provider](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.filters.provider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `PencilsFiltersProvider` |
| [com.intellij.rider.pencils.inspectionToolGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.inspectionToolGroup) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.rider.pencils.pencilsFilterGroup](https://jb.gg/ipe?extensions=com.intellij.rider.pencils.pencilsFilterGroup) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.rider.problemsView.actionsHandler](https://jb.gg/ipe?extensions=com.intellij.rider.problemsView.actionsHandler) ![Non-Dynamic][non-dynamic] | `RiderProblemsViewActionsHandler` |
| [com.intellij.rider.problemsView.problems.notifier](https://jb.gg/ipe?extensions=com.intellij.rider.problemsView.problems.notifier) ![Non-Dynamic][non-dynamic] | `ProblemsViewNotifier` |
| [com.intellij.rider.problemsView.problems.processor](https://jb.gg/ipe?extensions=com.intellij.rider.problemsView.problems.processor) | `RiderProblemsDiffProcessor` |
| [com.intellij.rider.publish.publishSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.publish.publishSettingsProvider) ![Project-Level][project-level] | `IPublishRuntimeCoreSettingsProvider` |
| [com.intellij.rider.publishConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.rider.publishConfigurationProvider) ![Non-Dynamic][non-dynamic] | `RiderContextPublishProvider` |
| [com.intellij.rider.reader.mode.matcher](https://jb.gg/ipe?extensions=com.intellij.rider.reader.mode.matcher) | `RiderCustomReaderModeMatcher` |
| [com.intellij.rider.refactoringPageProvider](https://jb.gg/ipe?extensions=com.intellij.rider.refactoringPageProvider) ![Non-Dynamic][non-dynamic] | `RefactoringPageProvider` |
| [com.intellij.rider.resolveContextWidgetProvider](https://jb.gg/ipe?extensions=com.intellij.rider.resolveContextWidgetProvider) ![Non-Dynamic][non-dynamic] | `RiderResolveContextWidgetProvider` |
| [com.intellij.rider.run.configurations.dotNetExe](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.dotNetExe) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetExeConfigurationExtension` |
| [com.intellij.rider.run.configurations.externalRunConfigurationGenerator](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.externalRunConfigurationGenerator) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ExternalRunConfigurationGeneratorExtension` |
| [com.intellij.rider.run.configurations.host](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.host) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunConfigurationHostExtensions` |
| [com.intellij.rider.run.configurations.host.executor](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.host.executor) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunConfigurationHostExecutorExtensions` |
| [com.intellij.rider.run.configurations.launchSettings](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `LaunchSettingsConfigurationExtension` |
| [com.intellij.rider.run.configurations.launchSettings.command](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings.command) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `LaunchSettingsCommandExtension` |
| [com.intellij.rider.run.configurations.launchSettings.generator](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.launchSettings.generator) | `LaunchSettingsConfigGeneratorExtension` |
| [com.intellij.rider.run.configurations.multiPlatform.mac.extension](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.multiPlatform.mac.extension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `MacRunConfigurationExtension` |
| [com.intellij.rider.run.configurations.project](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.project) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetProjectConfigurationExtension` |
| [com.intellij.rider.run.configurations.riderCoreDumpConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.riderCoreDumpConfigurationProvider) ![Non-Dynamic][non-dynamic] | `RiderCoreDumpConfigurationProvider` |
| [com.intellij.rider.run.configurations.uwp](https://jb.gg/ipe?extensions=com.intellij.rider.run.configurations.uwp) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UwpConfigurationExtension` |
| [com.intellij.rider.run.dotNetProfileConsoleViewProvider](https://jb.gg/ipe?extensions=com.intellij.rider.run.dotNetProfileConsoleViewProvider) ![Non-Dynamic][non-dynamic] | `DotNetProfileConsoleViewProviderExtension` |
| [com.intellij.rider.runToPopupShowPolicy](https://jb.gg/ipe?extensions=com.intellij.rider.runToPopupShowPolicy) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderRunToPopupShowPolicy` |
| [com.intellij.rider.runWidgetSuspenderExtension](https://jb.gg/ipe?extensions=com.intellij.rider.runWidgetSuspenderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunWidgetSuspenderExtension` |
| [com.intellij.rider.runtime.dotNetRuntimeAutodetect](https://jb.gg/ipe?extensions=com.intellij.rider.runtime.dotNetRuntimeAutodetect) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetRuntimeAutodetect` |
| [com.intellij.rider.solutionConfigurationPresenter](https://jb.gg/ipe?extensions=com.intellij.rider.solutionConfigurationPresenter) | `SolutionConfigurationPresenter` |
| [com.intellij.rider.solutionConfigurationToolbarCustomizer](https://jb.gg/ipe?extensions=com.intellij.rider.solutionConfigurationToolbarCustomizer) | `SolutionConfigurationToolbarCustomizer` |
| [com.intellij.rider.unitTesting.actionsProvider](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.actionsProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderUnitTestActionsProvider` |
| [com.intellij.rider.unitTesting.sessionHandler](https://jb.gg/ipe?extensions=com.intellij.rider.unitTesting.sessionHandler) ![Non-Dynamic][non-dynamic] | `IRiderUnitTestDebuggerSessionsHandler` |
| [com.intellij.rider.unityDetector](https://jb.gg/ipe?extensions=com.intellij.rider.unityDetector) ![Project-Level][project-level] | `UnityDetector` |
| [com.intellij.rider.web.extensions.companionDebugStarter](https://jb.gg/ipe?extensions=com.intellij.rider.web.extensions.companionDebugStarter) ![Non-Dynamic][non-dynamic] | `DotNetCompanionDebugStarter` |
| [com.intellij.rider.web.extensions.webBrowserDebugSupport](https://jb.gg/ipe?extensions=com.intellij.rider.web.extensions.webBrowserDebugSupport) ![Non-Dynamic][non-dynamic] | `WebBrowserDebugSupport` |
| [com.intellij.rider.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.rider.writingAccessProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderDebugWritingAccessProvider` |
| [com.intellij.rider.xaml.preview.editor](https://jb.gg/ipe?extensions=com.intellij.rider.xaml.preview.editor) ![Non-Dynamic][non-dynamic] | `XamlPreviewEditorExtension` |
| [com.intellij.solutionExplorerCustomization](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerCustomization) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionExplorerCustomization` |
| [com.intellij.solutionExplorerRootProvider](https://jb.gg/ipe?extensions=com.intellij.solutionExplorerRootProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionExplorerRootProvider` |
| [com.intellij.solutionManagerExtensions](https://jb.gg/ipe?extensions=com.intellij.solutionManagerExtensions) ![Non-Dynamic][non-dynamic] | `SolutionManagerExtensions` |
| [com.intellij.solutionViewPsiNodeNavigator](https://jb.gg/ipe?extensions=com.intellij.solutionViewPsiNodeNavigator) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionViewPsiNodeNavigator` |

### RiderReformatAndCleanup.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.code.cleanup.support](https://jb.gg/ipe?extensions=com.intellij.code.cleanup.support) | `RiderCodeCleanupSupportPolicy` |
| [com.intellij.rider.ProfileActionPrinter](https://jb.gg/ipe?extensions=com.intellij.rider.ProfileActionPrinter) ![Non-Dynamic][non-dynamic] | `ProfileActionPrinter` |
| [com.intellij.rider.cleanupAction](https://jb.gg/ipe?extensions=com.intellij.rider.cleanupAction) ![Non-Dynamic][non-dynamic] | `CleanupAction` |

### RiderSettingsSync.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.rider.settings.machineDependentBackendSetting](https://jb.gg/ipe?extensions=com.intellij.rider.settings.machineDependentBackendSetting) ![Non-Dynamic][non-dynamic] | `n/a` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
