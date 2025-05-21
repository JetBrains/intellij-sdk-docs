<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /rider
-->


<snippet id="content">

145 Extension Points and 9 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## Rider

### Rider – Listeners

| Topic | Listener |
|-------|----------|
| [`RiderStyleCopConfigurable#STYLE_COP_CONFIGURABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`SSHCredentialsInClipboardNotifier.Companion#SSH_CREDENTIALS_IN_CLIPBOARD_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.debugger.attach.remoting.SSHCredentialsInClipboardNotifier)  | `SSHCredentialsInClipboardNotifier` |
| [`DotnetDebuggerSymbolsLoadedListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.debugger.modulesView.actions.DotnetDebuggerSymbolsLoadedListener)  | `DotnetDebuggerSymbolsLoadedListener` |
| [`FrontendTypedHandlerManager#BEFORE_TYPING_SENT`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.editorActions.IFrontendTypingListener)  | `IFrontendTypingListener` |
| [`RiderDockerDeploymentListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.plugins.appender.docker.deployment.RiderDockerDeploymentListener)  | `RiderDockerDeploymentListener` |
| [`AutoAttachDebuggerListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.AutoAttachDebuggerListener)  | `AutoAttachDebuggerListener` |
| [`PublishConfigurationValidationListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.configurations.publishing.PublishConfigurationValidationListener)  | `PublishConfigurationValidationListener` |
| [`MSBuildEvaluationListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.run.environment.MSBuildEvaluationListener)  | `MSBuildEvaluationListener` |
| [`RiderGlobalBackendProgressListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.rider.services.RiderGlobalProgressHost.RiderGlobalBackendProgressListener)  | `RiderGlobalBackendProgressListener` |


### com.intellij.rider.frontend.customization

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rdclient.rider.completion.helper"/></include> | `CompletionHelper` |

### com.jetbrains.dotTrace.dotMemory

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.dotTrace.dotMemory.runtime.detector"/></include> | `DotTraceRuntimeDetector` |

### com.jetbrains.rider-cpp

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.rider-cpp.run.configurations.cpp"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `CppConfigurationParametersExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.rider-cpp.run.configurations.cpp.launch.profile"/></include> ![Non-Dynamic][non-dynamic] | `CppProjectLaunchProfile` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.rider-cpp.run.configurations.cpp.platformInfoProvider"/></include> ![Non-Dynamic][non-dynamic] | `CppPlatformInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.rider-cpp.run.configurations.cpp.platformPluginPromotion"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | `CppPlatformPluginPromotion` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="rider.cpp.debugProcessExtension"/></include> | `RiderCppDebugProcessExtension` |

### com.jetbrains.rider.razor

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.blazorDialectSubstitutor"/></include> ![Non-Dynamic][non-dynamic] | `BlazorHtmlDialectSubstitutor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.webTypingAssistClassifier"/></include> | `WebTypingAssistClassifier` |

### DotNetPluginEP.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backend.actions.support"/></include> | `RiderActionSupportPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backend.auto.import.support"/></include> | `RiderAutoImportSupportPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backend.autoPopup.support"/></include> | `RiderAutoPopupSupportPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backend.markup.adapterFactory"/></include> ![Non-Dynamic][non-dynamic] | `FrontendMarkupAdapterFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backend.typedHandler"/></include> | `FrontendTypedHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.completionSessionStrategy"/></include> | `CompletionSessionStrategy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.frontend.completion.helper"/></include> | `ICompletionHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.altEnter"/></include> | `BulbMenuModelFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.altEnter.popupModelDelegate"/></include> | `PopupModelDelegate` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.navbar.members.support"/></include> | `RiderNavBarMembersSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectModelViewUpdater"/></include> ![Project-Level][project-level] | `ProjectModelViewUpdater` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rdclient.actionsDataContextProvider"/></include> ![Internal][internal] | `FrontendActionsDataContextProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rdclient.preemptiveCompletionSuppressor"/></include> | `PreemptiveCompletionSuppressor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rdclient.typingPolicy"/></include> | `CustomTypingSessionPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.action.fallback.strategy"/></include> | `RiderAsyncBackendDelegatingActionFallbackStrategy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.additionalQuickDocProvider"/></include> | `AdditionalQuickDocProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.altEnter.layouter"/></include> | `RiderAltEnterLayouter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.backendCrashAnalyser"/></include> | `BackendCrashAnalyzer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.backendLogXmlPathProvider"/></include> | `RiderCustomBackendLogXmlPathProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.codeStyleContentConverter"/></include> | `RiderCodeStyleContentPageConverter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.credentials.provider"/></include> | `ICredentialsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.diagnostics.specialPathsProvider"/></include> | `SpecialPathsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.documentBehaviour"/></include> | `RiderDocumentBehaviour` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.editors.customBackendLanguageSupport"/></include> | `RiderCustomBackendLanguageSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.extraSettingsSync"/></include> | `ExtraSettingsSync` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.fileBreadcrumbExtensions"/></include> | `CustomFileBreadcrumbExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.grave.filter"/></include> ![Experimental][experimental] | `RiderHighlightingGraveFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.ideaInspectionBackendSuppressionSupport"/></include> | `IdeaInspectionBackendSuppressionSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.namingPageProvider"/></include> | `NamingPageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.protocol.hostEnvProvider"/></include> | `RiderBackendEnvProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.riderApplicationPreloadListener"/></include> | `RiderApplicationPreloadListener` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.smartTabsBackendSynchronizer"/></include> ![Non-Dynamic][non-dynamic] | `RiderSmartTabsBackendSynchronizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.startupListener"/></include> ![Internal][internal] | `RiderInitialStartupListener` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.selfProfilingPaths.customizer"/></include> | `DotnetSelfProfilerPathsCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.solutionLoadNotification"/></include> ![Project-Level][project-level] | `SolutionLoadNotification` |

### DotNetProjectView.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.projectModelIconProvider"/></include> | `ProjectModelIconProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.workspaceCountableProjectsPolicy"/></include> | `CountableProjectsPolicy` |

### intellij.rider.cpp.debugger

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.cpp.debuggerSettings"/></include> ![Non-Dynamic][non-dynamic] | `CppDebuggerSettings` |

### intellij.rider.plugins.appender.database.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.connectionStringRetriever"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DatabaseConnectionUrlRetriever` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.connectionStringToJdbcUrlMapper"/></include> ![Project-Level][project-level] | `ConnectionStringToJdbcUrlConverter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.connectionStringsFactory"/></include> ![Project-Level][project-level] | `ConnectionStringsFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.connectionStringsFinder"/></include> ![Project-Level][project-level] | `ConnectionStringsFinder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.dotnetDataProvider"/></include> ![Project-Level][project-level] | `DotnetDataProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.jdbcUrlToConnectionStringConverter"/></include> ![Project-Level][project-level] | `JdbcUrlToConnectionStringConverter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.database.schemaCompareDataModelCreatedListener"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SchemaCompareDataModelCreatedListener` |

### intellij.rider.plugins.appender.docker.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.dockerDebugProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | `RiderDockerDebugProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.dockerDeploymentTransformer"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | `RiderDockerDeploymentTransformer` |

### intellij.rider.plugins.appender.javascript.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.packageJson.configuration.handler"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderPackageJsonConfiguratorHandler` |

### intellij.rider.rdclient.languages.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.rdclient.breakingWorkflowChangeInterceptor"/></include> ![Non-Dynamic][non-dynamic] ![Experimental][experimental] ![Internal][internal] | `RiderBreakingWorkflowChangeInterceptor` |

### org.jetbrains.plugins.clion.radler

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="cidr.radler.externalSymbolsProvider"/></include> | `RadExternalSymbolsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.refactoringPageProvider"/></include> ![Non-Dynamic][non-dynamic] | `RefactoringPageProvider` |

### RiderCwmCoreEP.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.client.typedHandler"/></include> | `RiderClientLookupTypedHandler` |

### RiderDebugger.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debugger.evaluation.advice"/></include> | `DebugEvalAdviceProvider` |

### RiderExtensionPoints.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileSystemExplorerCustomization"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `FileSystemExplorerCustomization` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moveProviderExtension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `MoveProviderExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nestingRulesLanguageExtensions"/></include> ![Non-Dynamic][non-dynamic] | `RiderNestingRulesLanguageExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nugetCredentialProvider"/></include> ![Non-Dynamic][non-dynamic] | `NuGetCredentialProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openDirectoryExtensions"/></include> ![Non-Dynamic][non-dynamic] | `OpenDirectoryExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectModelViewExtensions"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ProjectModelViewExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplateCustomizer"/></include> ![Non-Dynamic][non-dynamic] | `ProjectTemplateCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplateDialogProvider"/></include> ![Non-Dynamic][non-dynamic] | `ProjectTemplateDialogProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplateProviderNew"/></include> ![Non-Dynamic][non-dynamic] | `ProjectTemplateProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.ProjectTypesProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderProjectTypesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.SolutionFileTypesProvider"/></include> ![Non-Dynamic][non-dynamic] | `SolutionFileTypesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.action.technical.support.info.provider"/></include> ![Non-Dynamic][non-dynamic] | `RiderTechnicalSupportInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.breakpoint.customPanelProvider"/></include> ![Non-Dynamic][non-dynamic] | `IDotNetLineBreakpointCustomPanelsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.breakpoint.customPopupActionsProvider"/></include> ![Non-Dynamic][non-dynamic] | `IDotNetLineBreakpointPopupActionsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.build.riderBuildConsoleDecorator"/></include> ![Project-Level][project-level] | `RiderBuildConsoleDecorator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.buildButtonModeProvider"/></include> | `BuildButtonModeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.codeLens.vcsDeclarationRangesProvider"/></include> ![Non-Dynamic][non-dynamic] | `VcsDeclarationRangesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.completion.csharpIdentifierPartHelper"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `CSharpIdentifierPartHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.completion.preselectionStrategy"/></include> ![Non-Dynamic][non-dynamic] | `RiderFrontendLanguagesPreselectionStrategy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.configurationExecutorExtension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderConfigurationExecutorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.configurationLaunchSettingsExtension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderConfigurationLaunchSettingsExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.consoleFilter"/></include> | `RiderConsoleFilterExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.contributedLanguageElementNameCrawler"/></include> | `RiderContributedLanguageElementNameCrawler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debug.breakpoint.handler.factory"/></include> ![Non-Dynamic][non-dynamic] | `IDotNetSupportedBreakpointHandlerFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debugger.editAndContinue.dotNetEncInfoAutodetect"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetEncInfoAutodetect` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debugger.value.evaluator.factory"/></include> ![Non-Dynamic][non-dynamic] | `RiderCustomComponentEvaluatorFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debugger.value.presenter"/></include> ![Non-Dynamic][non-dynamic] | `RiderDebuggerValuePresenter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.debuggerSupportPolicy"/></include> ![Non-Dynamic][non-dynamic] | `RiderDebuggerSupportPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.editSourceSuppressor"/></include> ![Non-Dynamic][non-dynamic] | `RiderEditSourceSuppressor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.extendedCodeStructure"/></include> ![Non-Dynamic][non-dynamic] | `RiderExtendedFileStructure` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.externalDirectoryProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ExternalDirectoryProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.fileTemplating.postCreateAction"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderNewFileFromTemplateExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.filesIndexingRuleProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderFilesIndexingRuleProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.findPopupProjectScopeProvider"/></include> | `FindPopupProjectScopeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.godotDetector"/></include> | `GodotDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.gotoCustomizer"/></include> | `GotoCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.guidPresenter"/></include> ![Project-Level][project-level] | `GuidGeneratorPresenter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.newFileListener"/></include> ![Project-Level][project-level] | `RiderNewFileListener` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.newRunConfigurationTreeGroupingProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderNewRunConfigurationTreeGroupingProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.patchCommandLine"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `PatchCommandLineExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.pencils.filters.provider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `PencilsFiltersProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.pencils.inspectionToolGroup"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.pencils.pencilsFilterGroup"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.problemsView.actionsHandler"/></include> ![Non-Dynamic][non-dynamic] | `RiderProblemsViewActionsHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.problemsView.problems.notifier"/></include> ![Non-Dynamic][non-dynamic] | `ProblemsViewNotifier` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.problemsView.problems.processor"/></include> | `RiderProblemsDiffProcessor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.publish.publishSettingsProvider"/></include> ![Project-Level][project-level] | `IPublishRuntimeCoreSettingsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.publishConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderContextPublishProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.reader.mode.matcher"/></include> | `RiderCustomReaderModeMatcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.refactoringPageProvider"/></include> ![Non-Dynamic][non-dynamic] | `RefactoringPageProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.resolveContextWidgetProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderResolveContextWidgetProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.dotNetExe"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetExeConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.externalRunConfigurationGenerator"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ExternalRunConfigurationGeneratorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.host"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunConfigurationHostExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.host.executor"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunConfigurationHostExecutorExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.launchSettings"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `LaunchSettingsConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.launchSettings.command"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `LaunchSettingsCommandExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.launchSettings.generator"/></include> | `LaunchSettingsConfigGeneratorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.multiPlatform.mac.extension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `MacRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.project"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetProjectConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.riderCoreDumpConfigurationProvider"/></include> ![Non-Dynamic][non-dynamic] | `RiderCoreDumpConfigurationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.configurations.uwp"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UwpConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.run.dotNetProfileConsoleViewProvider"/></include> ![Non-Dynamic][non-dynamic] | `DotNetProfileConsoleViewProviderExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.runToPopupShowPolicy"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderRunToPopupShowPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.runWidgetSuspenderExtension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RunWidgetSuspenderExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.runtime.dotNetRuntimeAutodetect"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DotNetRuntimeAutodetect` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.solutionConfigurationPresenter"/></include> | `SolutionConfigurationPresenter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.solutionConfigurationToolbarCustomizer"/></include> | `SolutionConfigurationToolbarCustomizer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.unitTesting.actionsProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderUnitTestActionsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.unitTesting.sessionHandler"/></include> ![Non-Dynamic][non-dynamic] | `IRiderUnitTestDebuggerSessionsHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.unityDetector"/></include> ![Project-Level][project-level] | `UnityDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.web.extensions.companionDebugStarter"/></include> ![Non-Dynamic][non-dynamic] | `DotNetCompanionDebugStarter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.web.extensions.webBrowserDebugSupport"/></include> ![Non-Dynamic][non-dynamic] | `WebBrowserDebugSupport` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.writingAccessProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `RiderDebugWritingAccessProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.xaml.preview.editor"/></include> ![Non-Dynamic][non-dynamic] | `XamlPreviewEditorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.solutionExplorerCustomization"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionExplorerCustomization` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.solutionExplorerRootProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionExplorerRootProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.solutionManagerExtensions"/></include> ![Non-Dynamic][non-dynamic] | `SolutionManagerExtensions` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.solutionViewPsiNodeNavigator"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SolutionViewPsiNodeNavigator` |

### RiderReformatAndCleanup.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.code.cleanup.support"/></include> | `RiderCodeCleanupSupportPolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.ProfileActionPrinter"/></include> ![Non-Dynamic][non-dynamic] | `ProfileActionPrinter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.cleanupAction"/></include> ![Non-Dynamic][non-dynamic] | `CleanupAction` |

### RiderSettingsSync.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rider.settings.machineDependentBackendSetting"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
