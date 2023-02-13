# Extension Point and Listener List

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Overview of Extension Points and Listeners for IntelliJ Platform</link-summary>

1482 Extension Points and 240 Listeners for IntelliJ Platform %ijPlatform%

- [](#intellij-platform)
- [](#intellij-community-plugins)
- [](#android-plugin)

> Dedicated Extension Point/Listener Lists specific to IDEs are available under _Part VIII — Product Specific_.
>
> See also [](explore_api.md) for more information and strategies.
>

<include from="snippets.md" element-id="ep_list_legend"/>

## IntelliJ Platform

### IntelliJ Platform - Listeners

| Topic | Listener |
|-------|----------|
| [ProblemsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.analysis.problemsView.ProblemsListener)  | [`ProblemsListener`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/problemsView/ProblemsListener.kt) |
| [EditorOptionsListener#FOLDING_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#APPEARANCE_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#OPTIONS_PANEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#SMART_KEYS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#GUTTER_ICONS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [ReaderModeSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.actions.ReaderModeListener)  ![Project-Level][project-level] | [`ReaderModeListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/ReaderModeListener.kt) |
| [CodeVisionSettings#CODE_LENS_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.codeVision.settings.CodeVisionSettings.CodeVisionSettingsListener)  | [`CodeVisionSettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettings.kt) |
| [CompletionPhaseListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionPhaseListener)  | [`CompletionPhaseListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPhaseListener.java) |
| [DaemonCodeAnalyzer#DAEMON_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.DaemonCodeAnalyzer.DaemonListener)  | [`DaemonListener`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java) |
| [EditorTrackerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.EditorTrackerListener)  ![Project-Level][project-level] | [`EditorTrackerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/EditorTrackerListener.java) |
| [FileHighlightingSettingListener#SETTING_CHANGE](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSettingListener)  | [`FileHighlightingSettingListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/FileHighlightingSettingListener.java) |
| [DocumentationComponentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.documentation.DocumentationComponentListener)  ![Deprecated][deprecated] | [`DocumentationComponentListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationComponentListener.java) |
| [EditorHintListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.EditorHintListener)  | [`EditorHintListener`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/hint/EditorHintListener.java) |
| [ExternalParameterInfoChangesProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.ExternalParameterInfoChangesProvider)  | [`ExternalParameterInfoChangesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ExternalParameterInfoChangesProvider.java) |
| [InlayHintsSettings#INLAY_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hints.InlayHintsSettings.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSettings.kt) |
| [LookupManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.lookup.LookupManagerListener)  ![Project-Level][project-level] | [`LookupManagerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupManagerListener.java) |
| [TemplateManager#TEMPLATE_STARTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.template.TemplateManagerListener)  ![Project-Level][project-level] | [`TemplateManagerListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/TemplateManagerListener.java) |
| [GlobalInspectionContextEx#INSPECT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInspection.ex.InspectListener)  | [`InspectListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/ex/InspectListener.java) |
| [BatchUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.configurationStore.BatchUpdateListener)  ![Project-Level][project-level] | [`BatchUpdateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/configurationStore/BatchUpdateListener.java) |
| [PasswordSafeSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.credentialStore.PasswordSafeSettingsListener)  | [`PasswordSafeSettingsListener`](%gh-ic%/platform/credential-store/src/PasswordSafeSettingsListener.java) |
| [IdePerformanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.IdePerformanceListener)  | [`IdePerformanceListener`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/IdePerformanceListener.java) |
| [RunnablesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.RunnablesListener)  ![Experimental API][experimental] ![Internal API][internal] | [`RunnablesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/RunnablesListener.java) |
| [DvcsBranchManager#DVCS_BRANCH_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.branch.DvcsBranchManager.DvcsBranchManagerListener)  | [`DvcsBranchManagerListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/branch/DvcsBranchManager.java) |
| [VcsRepositoryManager#VCS_REPOSITORY_MAPPING_UPDATED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.repo.VcsRepositoryMappingListener)  | [`VcsRepositoryMappingListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryMappingListener.java) |
| [ExecutionManager#EXECUTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionListener)  ![Project-Level][project-level] | [`ExecutionListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionListener.java) |
| [ExecutionTargetManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionTargetListener)  | [`ExecutionTargetListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionTargetListener.java) |
| [RunManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.RunManagerListener)  | [`RunManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/execution/RunManagerListener.java) |
| [RunDashboardManager#DASHBOARD_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.dashboard.RunDashboardListener)  | [`RunDashboardListener`](%gh-ic%/platform/execution/src/com/intellij/execution/dashboard/RunDashboardListener.java) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.process.elevation.settings.ElevationSettings.Listener)  | [`Listener`](%gh-ic%/platform/elevation/src/com/intellij/execution/process/elevation/settings/ElevationSettings.kt) |
| [RunToolbarSlotManager#RUN_TOOLBAR_SLOT_CONFIGURATION_MAP_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.runToolbar.data.RWSlotsConfigurationListener)  ![Project-Level][project-level] | [`RWSlotsConfigurationListener`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/runToolbar/data/RWSlotsConfigurationListener.kt) |
| [ServiceEventListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.services.ServiceEventListener)  | [`ServiceEventListener`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceEventListener.java) |
| [SMTRunnerEventsListener#TEST_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener)  | [`SMTRunnerEventsListener`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/SMTRunnerEventsListener.java) |
| [RunContentManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunContentWithExecutorListener)  | [`RunContentWithExecutorListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ui/RunContentWithExecutorListener.java) |
| [FacetManager#FACETS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.facet.FacetManagerListener)  | [`FacetManagerListener`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetManagerListener.java) |
| [FeaturesRegistryListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeaturesRegistryListener)  ![Experimental API][experimental] | [`FeaturesRegistryListener`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/FeaturesRegistryListener.java) |
| [FindManager#FIND_MODEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.find.FindModelListener)  | [`FindModelListener`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindModelListener.java) |
| [AppLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.AppLifecycleListener)  | [`AppLifecycleListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/AppLifecycleListener.java) |
| [FrameStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.FrameStateListener)  | [`FrameStateListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/FrameStateListener.java) |
| [PowerSaveMode#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.PowerSaveMode.Listener)  | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/ide/PowerSaveMode.java) |
| [Companion#RECENT_PROJECTS_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.RecentProjectsManager.RecentProjectsChange)  | [`RecentProjectsChange`](%gh-ic%/platform/ide-core/src/com/intellij/ide/RecentProjectsManager.kt) |
| [SaveAndSyncHandlerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.SaveAndSyncHandlerListener)  ![Experimental API][experimental] | [`SaveAndSyncHandlerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SaveAndSyncHandlerListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmark.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmarks.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/bookmarks/BookmarksListener.java) |
| [BatchFileChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.file.BatchFileChangeListener)  | [`BatchFileChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/file/BatchFileChangeListener.java) |
| [TrustStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.impl.TrustStateListener)  ![Experimental API][experimental] | [`TrustStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) |
| [LightEditServiceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.lightEdit.LightEditServiceListener)  ![Experimental API][experimental] | [`LightEditServiceListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditServiceListener.java) |
| [NavBarModelListener#NAV_BAR](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.NavBarModelListener)  ![Project-Level][project-level] | [`NavBarModelListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelListener.java) |
| [ExperimentalToolbarStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.experimental.ExperimentalToolbarStateListener)  ![Project-Level][project-level] | [`ExperimentalToolbarStateListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/experimental/NewToolbarRootPaneExtension.kt) |
| [DynamicPluginListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.DynamicPluginListener)  | [`DynamicPluginListener`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginListener.kt) |
| [PluginRepositoryAuthListener#PLUGIN_REPO_AUTH_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.auth.PluginRepositoryAuthListener)  | [`PluginRepositoryAuthListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthListener.java) |
| [ProjectViewSelectionTopicKt#PROJECT_VIEW_SELECTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.ProjectViewSelectionListener)  | [`ProjectViewSelectionListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/projectView/ProjectViewSelectionTopic.kt) |
| [ProjectViewListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.impl.ProjectViewListener)  | [`ProjectViewListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewListener.java) |
| [LafManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.LafManagerListener)  | [`LafManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/LafManagerListener.java) |
| [UISettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.UISettingsListener)  | [`UISettingsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/UISettingsListener.java) |
| [VirtualFileAppearanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.VirtualFileAppearanceListener)  | [`VirtualFileAppearanceListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/VirtualFileAppearanceListener.java) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.customization.CustomActionsListener)  | [`CustomActionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomActionsListener.kt) |
| [ComponentHighlightingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.search.ComponentHighlightingListener)  | [`ComponentHighlightingListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/ComponentHighlightingListener.java) |
| [SettingsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.scopeChooser.ScopeEditorPanel.SettingsChangedListener)  | [`SettingsChangedListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeEditorPanel.java) |
| [EventLogConfigOptionsService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.internal.statistic.eventLog.EventLogConfigOptionsListener)  | [`EventLogConfigOptionsListener`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogConfigOptionsListener.java) |
| [ExternalResourceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javaee.ExternalResourceListener)  | [`ExternalResourceListener`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceListener.java) |
| [LogModel#LOG_MODEL_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.notification.EventLogListener)  | [`EventLogListener`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/EventLogListener.java) |
| [Notifications#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.notification.Notifications)  | [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java) |
| [ActionManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ActionManagerListener)  ![Internal API][internal] | [`ActionManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/ActionManagerListener.java) |
| [AnActionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ex.AnActionListener)  | [`AnActionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ex/AnActionListener.java) |
| [ToolbarActionsUpdatedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.impl.segmentedActionBar.ToolbarActionsUpdatedListener)  ![Internal API][internal] | [`ToolbarActionsUpdatedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/segmentedActionBar/ToolbarActionsUpdatedListener.java) |
| [ApplicationActivationListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.application.ApplicationActivationListener)  | [`ApplicationActivationListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) |
| [CommandListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.CommandListener)  | [`CommandListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/command/CommandListener.java) |
| [LatencyListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.actionSystem.LatencyListener)  | [`LatencyListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/LatencyListener.java) |
| [EditorColorsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.EditorColorsListener)  | [`EditorColorsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsListener.java) |
| [EditorColorsManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.impl.EditorColorsManagerListener)  ![Internal API][internal] | [`EditorColorsManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/colors/impl/EditorColorsManagerListener.kt) |
| [DocumentBulkUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.ex.DocumentBulkUpdateListener)  ![Deprecated][deprecated] | [`DocumentBulkUpdateListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/ex/DocumentBulkUpdateListener.java) |
| [ExternalSystemProjectNotificationAware#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectNotificationAware.Listener)  | [`Listener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autoimport/ExternalSystemProjectNotificationAware.kt) |
| [ProjectDataImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener)  ![Project-Level][project-level] | [`ProjectDataImportListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataImportListener.java) |
| [AppTopics#FILE_DOCUMENT_SYNC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [FileEditorManagerListener#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener)  ![Project-Level][project-level] | [`FileEditorManagerListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [Before#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener.Before)  | [`Before`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [RecentPlacesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.IdeDocumentHistoryImpl.RecentPlacesListener)  ![Project-Level][project-level] | [`RecentPlacesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/IdeDocumentHistoryImpl.java) |
| [FileTypeManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileTypes.FileTypeListener)  | [`FileTypeListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeListener.java) |
| [KeymapManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.KeymapManagerListener)  | [`KeymapManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapManagerListener.java) |
| [KeymapListener#CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.impl.ui.KeymapListener)  | [`KeymapListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/keymap/impl/ui/KeymapListener.java) |
| [AdvancedSettingsChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.advanced.AdvancedSettingsChangeListener)  | [`AdvancedSettingsChangeListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/options/advanced/AdvancedSettings.kt) |
| [ExternalUpdateRequest#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.ExternalUpdateRequest)  ![Experimental API][experimental] ![Internal API][internal] | [`ExternalUpdateRequest`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/options/newEditor/ExternalUpdateRequest.java) |
| [ProgressManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.ProgressManagerListener)  | [`ProgressManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManagerListener.java) |
| [ProgressSuspender#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.impl.ProgressSuspender.SuspenderListener)  | [`SuspenderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/impl/ProgressSuspender.java) |
| [ProgressWindow#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.util.ProgressWindow.Listener)  | [`Listener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) |
| [DumbService#DUMB_MODE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.DumbService.DumbModeListener)  ![Project-Level][project-level] | [`DumbModeListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.java) |
| [ProjectTopics#MODULES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Project-Level][project-level] | [`ModuleListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [ProjectManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectManagerListener)  | [`ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) |
| [ProjectLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.ProjectLifecycleListener)  | [`ProjectLifecycleListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/project/impl/ProjectLifecycleListener.java) |
| [ProjectJdkTable#JDK_TABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.projectRoots.ProjectJdkTable.Listener)  | [`Listener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) |
| [AdditionalLibraryRootsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.AdditionalLibraryRootsListener)  ![Experimental API][experimental] ![Project-Level][project-level] | [`AdditionalLibraryRootsListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsListener.java) |
| [ProjectTopics#PROJECT_ROOTS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Project-Level][project-level] | [`ModuleRootListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [RegistryManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.util.registry.RegistryValueListener)  | [`RegistryValueListener`](%gh-ic%/platform/util/src/com/intellij/openapi/util/registry/RegistryValueListener.java) |
| [BranchChangeListener#VCS_BRANCH_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchChangeListener)  | [`BranchChangeListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchChangeListener.java) |
| [BranchRenameListener#VCS_BRANCH_RENAMED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchRenameListener)  | [`BranchRenameListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchRenameListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED_IN_PLUGIN](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.PluginVcsMappingListener)  ![Project-Level][project-level] | [`PluginVcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/PluginVcsMappingListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.VcsMappingListener)  ![Project-Level][project-level] | [`VcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsMappingListener.java) |
| [ChangeListAvailabilityListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListAvailabilityListener)  ![Internal API][internal] | [`ChangeListAvailabilityListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListAvailabilityListener.java) |
| [ChangeListListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListListener)  | [`ChangeListListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListListener.java) |
| [ChangesViewModifier#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewModifier.ChangesViewModifierListener)  ![Project-Level][project-level] | [`ChangesViewModifierListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [ChangesViewWorkflowManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewWorkflowManager.ChangesViewWorkflowListener)  ![Project-Level][project-level] | [`ChangesViewWorkflowListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewWorkflowManager.java) |
| [ChangeListManagerImpl#LISTS_LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.LocalChangeListsLoadedListener)  ![Project-Level][project-level] | [`LocalChangeListsLoadedListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalChangeListsLoadedListener.java) |
| [VcsAnnotationRefresher#LOCAL_CHANGES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsAnnotationRefresher)  | [`VcsAnnotationRefresher`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsAnnotationRefresher.java) |
| [VcsEditorTabFilesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsEditorTabFilesListener)  | [`VcsEditorTabFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsEditorTabFilesManager.kt) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsFreezingProcess.Listener)  | [`Listener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsFreezingProcess.java) |
| [VcsManagedFilesHolder#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsManagedFilesHolder.VcsManagedFilesHolderListener)  ![Project-Level][project-level] | [`VcsManagedFilesHolderListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [CommittedChangesCache#COMMITTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesListener)  ![Project-Level][project-level] | [`CommittedChangesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesListener.java) |
| [CommittedChangesTreeBrowser#ITEMS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesTreeBrowser.CommittedChangesReloadListener)  | [`CommittedChangesReloadListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesTreeBrowser.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED_RESPONSE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.DetailedNotification)  | [`DetailedNotification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.Notification)  | [`Notification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [ChangesViewContentManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ui.ChangesViewContentManagerListener)  | [`ChangesViewContentManagerListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentManagerListener.kt) |
| [ProjectLevelVcsManagerEx#VCS_ACTIVATED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.ex.VcsActivationListener)  ![Project-Level][project-level] | [`VcsActivationListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/ex/VcsActivationListener.java) |
| [LineStatusTrackerSettingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerSettingListener)  | [`LineStatusTrackerSettingListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerSettingListener.java) |
| [UpdatedFilesListener#UPDATED_FILES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.update.UpdatedFilesListener)  | [`UpdatedFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/update/UpdatedFilesListener.java) |
| [EncodingManagerListener#ENCODING_MANAGER_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.encoding.EncodingManagerListener)  | [`EncodingManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/EncodingManagerListener.java) |
| [VirtualFileManager#VFS_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.newvfs.BulkFileListener)  | [`BulkFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) |
| [VirtualFilePointerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.pointers.VirtualFilePointerListener)  | [`VirtualFilePointerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/pointers/VirtualFilePointerListener.java) |
| [Info#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.StatusBarInfo)  ![Project-Level][project-level] | [`StatusBarInfo`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/StatusBarInfo.java) |
| [ToolWindowManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.ToolWindowManagerListener)  ![Project-Level][project-level] | [`ToolWindowManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/ex/ToolWindowManagerListener.java) |
| [TitleInfoProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.TitleInfoProvider.TitleInfoProviderListener)  ![Internal API][internal] | [`TitleInfoProviderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [WelcomeBalloonLayoutImpl#BALLOON_NOTIFICATION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeBalloonLayoutImpl.BalloonNotificationListener)  | [`BalloonNotificationListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeBalloonLayoutImpl.java) |
| [WelcomeScreenComponentListener#COMPONENT_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenComponentListener)  | [`WelcomeScreenComponentListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeScreenComponentListener.java) |
| [CloneableProjectsService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.cloneableProjects.CloneableProjectsService.CloneProjectListener)  | [`CloneProjectListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/cloneableProjects/CloneableProjectsService.kt) |
| [ModuleAttachListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.ModuleAttachListener)  | [`ModuleAttachListener`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/ModuleAttachListener.kt) |
| [ExternalSystemTestUtil#SETTINGS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.externalSystem.testFramework.TestExternalSystemSettingsListener)  | [`TestExternalSystemSettingsListener`](%gh-ic%/platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/TestExternalSystemSettingsListener.java) |
| [ProblemListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.problems.ProblemListener)  ![Project-Level][project-level] | [`ProblemListener`](%gh-ic%/platform/analysis-api/src/com/intellij/problems/ProblemListener.java) |
| [ProfileChangeAdapter#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.profile.ProfileChangeAdapter)  ![Project-Level][project-level] | [`ProfileChangeAdapter`](%gh-ic%/platform/analysis-api/src/com/intellij/profile/ProfileChangeAdapter.java) |
| [PsiDocumentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.PsiDocumentListener)  | [`PsiDocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentListener.java) |
| [PsiManagerImpl#ANY_PSI_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.AnyPsiChangeListener)  | [`AnyPsiChangeListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/AnyPsiChangeListener.java) |
| [PsiDocumentTransactionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.PsiDocumentTransactionListener)  | [`PsiDocumentTransactionListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiDocumentTransactionListener.java) |
| [FileTypeIndex#INDEX_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.search.FileTypeIndex.IndexChangeListener)  ![Experimental API][experimental] | [`IndexChangeListener`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) |
| [PsiModificationTracker#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [RefactoringEventListener#REFACTORING_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.refactoring.listeners.RefactoringEventListener)  | [`RefactoringEventListener`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/listeners/RefactoringEventListener.java) |
| [RemoteMappingsListener#REMOTE_MAPPINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.remote.RemoteMappingsListener)  | [`RemoteMappingsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/remote/RemoteMappingsListener.java) |
| [RemoteServerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.configuration.RemoteServerListener)  | [`RemoteServerListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/RemoteServerListener.java) |
| [ServerConnectionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.runtime.ServerConnectionListener)  | [`ServerConnectionListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/runtime/ServerConnectionListener.java) |
| [SpellCheckerEngineListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spellchecker.engine.SpellCheckerEngineListener)  | [`SpellCheckerEngineListener`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/engine/SpellCheckerEngineListener.java) |
| [ProjectTaskListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.task.ProjectTaskListener)  ![Project-Level][project-level] | [`ProjectTaskListener`](%gh-ic%/platform/lang-api/src/com/intellij/task/ProjectTaskListener.java) |
| [ToolWindowViewModelListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.viewModel.extraction.ToolWindowViewModelListener)  ![Project-Level][project-level] | [`ToolWindowViewModelListener`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelListener.java) |
| [UnindexedFilesUpdaterListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.UnindexedFilesUpdaterListener)  ![Deprecated][deprecated] | [`UnindexedFilesUpdaterListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/UnindexedFilesUpdaterListener.java) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.diagnostic.ProjectIndexingHistoryListener)  | [`ProjectIndexingHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [CommitModeManager#COMMIT_MODE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.CommitModeListener)  ![Project-Level][project-level] | [`CommitModeListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitModeManager#SETTINGS](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitMessageInspectionProfile#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.message.CommitMessageInspectionProfile.ProfileListener)  | [`ProfileListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/message/CommitMessageInspectionProfile.java) |
| [VcsProjectLog#VCS_PROJECT_LOG_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.impl.VcsProjectLog.ProjectLogListener)  ![Project-Level][project-level] | [`ProjectLogListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/VcsProjectLog.java) |
| [JpsProjectLoadedListener#LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsProjectLoadedListener)  ![Project-Level][project-level] | [`JpsProjectLoadedListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/JpsProjectLoadedListener.kt) |
| [WorkspaceModelTopics#CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.WorkspaceModelChangeListener)  ![Project-Level][project-level] | [`WorkspaceModelChangeListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/WorkspaceModelTopics.kt) |
| [XDebuggerManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XDebuggerManagerListener)  | [`XDebuggerManagerListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/XDebuggerManagerListener.java) |
| [XBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.breakpoints.XBreakpointListener)  | [`XBreakpointListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointListener.java) |
| [XDependentBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.impl.breakpoints.XDependentBreakpointListener)  | [`XDependentBreakpointListener`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/breakpoints/XDependentBreakpointListener.java) |
| [BreadcrumbsInitListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xml.breadcrumbs.BreadcrumbsInitListener)  ![Internal API][internal] | [`BreadcrumbsInitListener`](%gh-ic%/platform/platform-impl/src/com/intellij/xml/breadcrumbs/BreadcrumbsInitListener.java) |
| [IndexPatternProvider#INDEX_PATTERNS_CHANGED](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [TodoConfiguration#PROPERTY_CHANGE](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [JsonSchemaVfsListener#JSON_SCHEMA_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [JsonSchemaVfsListener#JSON_DEPS_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [SeverityRegistrar#SEVERITIES_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [RemoteRevisionsCache#REMOTE_VERSION_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [StructureViewWrapperImpl#STRUCTURE_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [UsageFilteringRuleProvider#RULES_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [ShelveChangesManager#SHELF_TOPIC](https://jb.gg/ipe/listeners?topics=javax.swing.event.ChangeListener)  | `ChangeListener` |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.ide.UpdateActionsListener)  | [`UpdateActionsListener`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxUpdateActions.kt) |
| [NotebookEditorModeKt#NOTEBOOK_EDITOR_MODE](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.ui.editor.actions.command.mode.NotebookEditorModeListener)  | [`NotebookEditorModeListener`](%gh-ic%/notebooks/notebook-ui/src/org/jetbrains/plugins/notebooks/ui/editor/actions/command/mode/NotebookEditorMode.kt) |
| [NotebookOutputInlayControllerKt#OUTPUT_LISTENER](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.visualization.outputs.OutputListener)  | [`OutputListener`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputInlayController.kt) |
| [GraphicsPanelKt#CHANGE_DARK_MODE_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.visualization.r.inlays.components.DarkModeNotifier)  | [`DarkModeNotifier`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/GraphicsPanel.kt) |
| [Companion#topic](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.visualization.r.inlays.components.NotebookInlayMouseListener)  | [`NotebookInlayMouseListener`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/NotebookInlayMouseListener.kt) |


### Analysis.xml

[`Analysis.xml`](%gh-ic%/platform/analysis-api/resources/META-INF/Analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.containerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.containerProvider) | [`ContainerProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/ContainerProvider.java) |
| [com.intellij.codeInsight.signatureHelp](https://jb.gg/ipe?extensions=com.intellij.codeInsight.signatureHelp) | [`SignatureHelpProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/signatureHelp/SignatureHelpProvider.java) |
| [com.intellij.codeInspection.InspectionExtension](https://jb.gg/ipe?extensions=com.intellij.codeInspection.InspectionExtension) | [`InspectionExtensionsFactory`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/lang/InspectionExtensionsFactory.java) |
| [com.intellij.completion.contributor](https://jb.gg/ipe?extensions=com.intellij.completion.contributor) | [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) |
| [com.intellij.completion.skip](https://jb.gg/ipe?extensions=com.intellij.completion.skip) | [`CompletionPreselectSkipper`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionPreselectSkipper.java) |
| [com.intellij.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.documentationProvider) | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.fileContextProvider](https://jb.gg/ipe?extensions=com.intellij.fileContextProvider) ![Project-Level][project-level] | [`FileContextProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileContextProvider.java) |
| [com.intellij.fileLookupInfoProvider](https://jb.gg/ipe?extensions=com.intellij.fileLookupInfoProvider) | [`FileLookupInfoProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/file/FileLookupInfoProvider.java) |
| [com.intellij.generatedSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.generatedSourcesFilter) | [`GeneratedSourcesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/roots/GeneratedSourcesFilter.java) |
| [com.intellij.globalInspection](https://jb.gg/ipe?extensions=com.intellij.globalInspection) | [`GlobalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/GlobalInspectionTool.java) |
| [com.intellij.gotoDeclarationHandler](https://jb.gg/ipe?extensions=com.intellij.gotoDeclarationHandler) | [`GotoDeclarationHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/navigation/actions/GotoDeclarationHandler.java) |
| [com.intellij.highlightErrorFilter](https://jb.gg/ipe?extensions=com.intellij.highlightErrorFilter) ![Project-Level][project-level] | [`HighlightErrorFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/highlighting/HighlightErrorFilter.java) |
| [com.intellij.inspectionElementsMerger](https://jb.gg/ipe?extensions=com.intellij.inspectionElementsMerger) | [`InspectionElementsMerger`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/ex/InspectionElementsMerger.java) |
| [com.intellij.inspectionToolProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionToolProvider) | [`InspectionToolProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionToolProvider.java) |
| [com.intellij.inspectionsReportConverter](https://jb.gg/ipe?extensions=com.intellij.inspectionsReportConverter) | [`InspectionsReportConverter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionsReportConverter.java) |
| [com.intellij.intentionAction](https://jb.gg/ipe?extensions=com.intellij.intentionAction) | [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) |
| [com.intellij.lang.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.documentationProvider) | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.lang.inspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.lang.inspectionSuppressor) | [`InspectionSuppressor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionSuppressor.java) |
| [com.intellij.languageInjector](https://jb.gg/ipe?extensions=com.intellij.languageInjector) | [`LanguageInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/LanguageInjector.java) |
| [com.intellij.liveTemplateContext](https://jb.gg/ipe?extensions=com.intellij.liveTemplateContext) | [`TemplateContextType`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/TemplateContextType.java) |
| [com.intellij.liveTemplateContextProvider](https://jb.gg/ipe?extensions=com.intellij.liveTemplateContextProvider) | [`LiveTemplateContextProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/LiveTemplateContextProvider.java) |
| [com.intellij.liveTemplateMacro](https://jb.gg/ipe?extensions=com.intellij.liveTemplateMacro) | [`Macro`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/Macro.java) |
| [com.intellij.localFileSystemTimestampEvaluator](https://jb.gg/ipe?extensions=com.intellij.localFileSystemTimestampEvaluator) ![Internal API][internal] | [`LocalFileSystemTimestampEvaluator`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/impl/local/LocalFileSystemTimestampEvaluator.java) |
| [com.intellij.localInspection](https://jb.gg/ipe?extensions=com.intellij.localInspection) | [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) |
| [com.intellij.weigher](https://jb.gg/ipe?extensions=com.intellij.weigher) | [`Weigher`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/Weigher.java) |

### AnalysisImpl.xml

[`AnalysisImpl.xml`](%gh-ic%/platform/analysis-impl/resources/META-INF/AnalysisImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dataflowIRProvider](https://jb.gg/ipe?extensions=com.intellij.dataflowIRProvider) | [`DataFlowIRProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/dataFlow/lang/ir/DataFlowIRProvider.java) |
| [com.intellij.elementLookupRenderer](https://jb.gg/ipe?extensions=com.intellij.elementLookupRenderer) ![Deprecated][deprecated] | [`ElementLookupRenderer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/lookup/impl/ElementLookupRenderer.java) |
| [com.intellij.liveTemplateOptionalProcessor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateOptionalProcessor) | [`TemplateOptionalProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplateOptionalProcessor.java) |
| [com.intellij.liveTemplatePreprocessor](https://jb.gg/ipe?extensions=com.intellij.liveTemplatePreprocessor) | [`TemplatePreprocessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplatePreprocessor.java) |
| [com.intellij.outerLanguageRangePatcher](https://jb.gg/ipe?extensions=com.intellij.outerLanguageRangePatcher) | [`OuterLanguageRangePatcher`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/templateLanguages/TemplateDataElementType.java) |
| [com.intellij.psi.fileReferenceHelper](https://jb.gg/ipe?extensions=com.intellij.psi.fileReferenceHelper) | [`FileReferenceHelper`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/FileReferenceHelper.java) |
| [com.intellij.resolveScopeEnlarger](https://jb.gg/ipe?extensions=com.intellij.resolveScopeEnlarger) | [`ResolveScopeEnlarger`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/ResolveScopeEnlarger.java) |
| [com.intellij.resolveScopeProvider](https://jb.gg/ipe?extensions=com.intellij.resolveScopeProvider) | [`ResolveScopeProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/ResolveScopeProvider.java) |
| [com.intellij.severitiesProvider](https://jb.gg/ipe?extensions=com.intellij.severitiesProvider) ![Non-Dynamic][non-dynamic] | [`SeveritiesProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SeveritiesProvider.java) |
| [com.intellij.useScopeEnlarger](https://jb.gg/ipe?extensions=com.intellij.useScopeEnlarger) | [`UseScopeEnlarger`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/UseScopeEnlarger.java) |
| [com.intellij.useScopeOptimizer](https://jb.gg/ipe?extensions=com.intellij.useScopeOptimizer) | [`ScopeOptimizer`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |

### builtInServer.xml

[`builtInServer.xml`](%gh-ic%/platform/built-in-server/resources/META-INF/builtInServer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.httpRequestHandler](https://jb.gg/ipe?extensions=com.intellij.httpRequestHandler) | [`HttpRequestHandler`](%gh-ic%/platform/platform-util-netty/src/org/jetbrains/ide/HttpRequestHandler.kt) |
| [com.intellij.toolboxServiceHandler](https://jb.gg/ipe?extensions=com.intellij.toolboxServiceHandler) ![Internal API][internal] | [`ToolboxServiceHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxRestService.kt) |
| [org.jetbrains.binaryRequestHandler](https://jb.gg/ipe?extensions=org.jetbrains.binaryRequestHandler) ![Non-Dynamic][non-dynamic] | [`BinaryRequestHandler`](%gh-ic%/platform/platform-util-netty/src/org/jetbrains/ide/BinaryRequestHandler.java) |
| [org.jetbrains.customPortServerManager](https://jb.gg/ipe?extensions=org.jetbrains.customPortServerManager) ![Non-Dynamic][non-dynamic] | [`CustomPortServerManager`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/ide/CustomPortServerManager.kt) |
| [org.jetbrains.jsonRpcDomain](https://jb.gg/ipe?extensions=org.jetbrains.jsonRpcDomain) ![Internal API][internal] | `Object` |
| [org.jetbrains.webServerFileHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerFileHandler) | [`WebServerFileHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerFileHandler.kt) |
| [org.jetbrains.webServerPathHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerPathHandler) | [`WebServerPathHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerPathHandler.kt) |
| [org.jetbrains.webServerRootsProvider](https://jb.gg/ipe?extensions=org.jetbrains.webServerRootsProvider) | [`WebServerRootsProvider`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/builtInWebServer/WebServerRootsProvider.kt) |

### CodeStyle.xml

[`CodeStyle.xml`](%gh-ic%/platform/code-style-impl/resources/META-INF/CodeStyle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeStyleSettingsModifier](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsModifier) ![Experimental API][experimental] | [`CodeStyleSettingsModifier`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/modifier/CodeStyleSettingsModifier.java) |
| [com.intellij.disabledIndentRangesProvider](https://jb.gg/ipe?extensions=com.intellij.disabledIndentRangesProvider) | [`DisabledIndentRangesProvider`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/DisabledIndentRangesProvider.java) |
| [com.intellij.externalFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.externalFormatProcessor) ![Experimental API][experimental] | [`ExternalFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/ExternalFormatProcessor.java) |
| [com.intellij.fileCodeStyleProvider](https://jb.gg/ipe?extensions=com.intellij.fileCodeStyleProvider) | [`FileCodeStyleProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/FileCodeStyleProvider.java) |
| [com.intellij.fileIndentOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.fileIndentOptionsProvider) | [`FileIndentOptionsProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/FileIndentOptionsProvider.java) |
| [com.intellij.formattingService](https://jb.gg/ipe?extensions=com.intellij.formattingService) | [`FormattingService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/FormattingService.java) |
| [com.intellij.lang.formatter](https://jb.gg/ipe?extensions=com.intellij.lang.formatter) | [`FormattingModelBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java) |
| [com.intellij.lang.formatter.newLineIndentMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.lang.formatter.newLineIndentMarkerProvider) | [`NewLineIndentMarkerProvider`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/codeStyle/NewLineIndentMarkerProvider.java) |
| [com.intellij.lang.formatter.restriction](https://jb.gg/ipe?extensions=com.intellij.lang.formatter.restriction) | [`LanguageFormattingRestriction`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/LanguageFormattingRestriction.java) |
| [com.intellij.lang.formatter.syntaxErrorsVerifier](https://jb.gg/ipe?extensions=com.intellij.lang.formatter.syntaxErrorsVerifier) | [`CustomAutoFormatSyntaxErrorsVerifier`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/CustomAutoFormatSyntaxErrorsVerifier.java) |
| [com.intellij.lang.importOptimizer](https://jb.gg/ipe?extensions=com.intellij.lang.importOptimizer) | [`ImportOptimizer`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/ImportOptimizer.java) |
| [com.intellij.lang.indentStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.indentStrategy) | [`IndentStrategy`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/IndentStrategy.java) |
| [com.intellij.lang.lineWrapStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.lineWrapStrategy) | [`LineWrapPositionStrategy`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/LineWrapPositionStrategy.java) |
| [com.intellij.lang.rearranger](https://jb.gg/ipe?extensions=com.intellij.lang.rearranger) | [`Rearranger`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) |
| [com.intellij.lang.whiteSpaceFormattingStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.whiteSpaceFormattingStrategy) | [`WhiteSpaceFormattingStrategy`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/WhiteSpaceFormattingStrategy.java) |
| [com.intellij.postFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.postFormatProcessor) | [`PostFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java) |
| [com.intellij.preFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.preFormatProcessor) | [`PreFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) |

### com.intellij.platform.images

[`com.intellij.platform.images`](%gh-ic%/images/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.images.themeFilter](https://jb.gg/ipe?extensions=com.intellij.images.themeFilter) | [`ThemeFilter`](%gh-ic%/images/src/org/intellij/images/thumbnail/actions/ThemeFilter.java) |

### CompletionExtensionPoints.xml

[`CompletionExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/CompletionExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.wordCompletionFilter](https://jb.gg/ipe?extensions=com.intellij.codeInsight.wordCompletionFilter) | [`WordCompletionElementFilter`](%gh-ic%/platform/lang-api/src/com/intellij/lang/WordCompletionElementFilter.java) |
| [com.intellij.completion.confidence](https://jb.gg/ipe?extensions=com.intellij.completion.confidence) | [`CompletionConfidence`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionConfidence.java) |
| [com.intellij.completion.ml.contextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.contextFeatures) ![Internal API][internal] | [`ContextFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementFeatures) ![Internal API][internal] | [`ElementFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ElementFeatureProvider.java) |
| [com.intellij.completion.ml.model](https://jb.gg/ipe?extensions=com.intellij.completion.ml.model) ![Internal API][internal] | [`RankingModelProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/ml/completion/RankingModelProvider.java) |
| [com.intellij.completion.plainTextSymbol](https://jb.gg/ipe?extensions=com.intellij.completion.plainTextSymbol) | [`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java) |
| [com.intellij.completion.preselectionBehaviourProvider](https://jb.gg/ipe?extensions=com.intellij.completion.preselectionBehaviourProvider) | [`CompletionPreselectionBehaviourProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPreselectionBehaviourProvider.java) |
| [com.intellij.createDirectoryCompletionContributor](https://jb.gg/ipe?extensions=com.intellij.createDirectoryCompletionContributor) | [`CreateDirectoryCompletionContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CreateDirectoryCompletionContributor.java) |
| [com.intellij.lookup.actionProvider](https://jb.gg/ipe?extensions=com.intellij.lookup.actionProvider) | [`LookupActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupActionProvider.java) |
| [com.intellij.lookup.charFilter](https://jb.gg/ipe?extensions=com.intellij.lookup.charFilter) | [`CharFilter`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/lookup/CharFilter.java) |
| [com.intellij.lookup.usageDetails](https://jb.gg/ipe?extensions=com.intellij.lookup.usageDetails) ![Internal API][internal] | [`LookupUsageDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupUsageDescriptor.java) |
| [com.intellij.templateParameterTraversalPolicy](https://jb.gg/ipe?extensions=com.intellij.templateParameterTraversalPolicy) | [`TemplateParameterTraversalPolicy`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/TemplateParameterTraversalPolicy.java) |

### Core.xml

[`Core.xml`](%gh-ic%/platform/core-api/resources/META-INF/Core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.applicationService](https://jb.gg/ipe?extensions=com.intellij.applicationService) | `n/a` |
| [com.intellij.backgroundPostStartupActivity](https://jb.gg/ipe?extensions=com.intellij.backgroundPostStartupActivity) | [`StartupActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.editorFactoryDocumentListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryDocumentListener) | [`DocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/event/DocumentListener.java) |
| [com.intellij.fileTypeDetector](https://jb.gg/ipe?extensions=com.intellij.fileTypeDetector) | [`FileTypeDetector`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeRegistry.java) |
| [com.intellij.filetype.decompiler](https://jb.gg/ipe?extensions=com.intellij.filetype.decompiler) | [`BinaryFileDecompiler`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/BinaryFileDecompiler.java) |
| [com.intellij.iconLayerProvider](https://jb.gg/ipe?extensions=com.intellij.iconLayerProvider) ![Non-Dynamic][non-dynamic] | [`IconLayerProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconLayerProvider.java) |
| [com.intellij.iconProvider](https://jb.gg/ipe?extensions=com.intellij.iconProvider) | [`IconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconProvider.java) |
| [com.intellij.lang.commenter](https://jb.gg/ipe?extensions=com.intellij.lang.commenter) | [`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) |
| [com.intellij.lang.elementManipulator](https://jb.gg/ipe?extensions=com.intellij.lang.elementManipulator) | [`ElementManipulator`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementManipulator.java) |
| [com.intellij.lang.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.lang.fileViewProviderFactory) | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.lang.parserDefinition](https://jb.gg/ipe?extensions=com.intellij.lang.parserDefinition) | [`ParserDefinition`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java) |
| [com.intellij.lang.substitutor](https://jb.gg/ipe?extensions=com.intellij.lang.substitutor) | [`LanguageSubstitutor`](%gh-ic%/platform/core-api/src/com/intellij/psi/LanguageSubstitutor.java) |
| [com.intellij.languageBundle](https://jb.gg/ipe?extensions=com.intellij.languageBundle) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | `n/a` |
| [com.intellij.languageInjectionContributor](https://jb.gg/ipe?extensions=com.intellij.languageInjectionContributor) | [`LanguageInjectionContributor`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) |
| [com.intellij.languageInjectionPerformer](https://jb.gg/ipe?extensions=com.intellij.languageInjectionPerformer) | [`LanguageInjectionPerformer`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java) |
| [com.intellij.metaLanguage](https://jb.gg/ipe?extensions=com.intellij.metaLanguage) | [`MetaLanguage`](%gh-ic%/platform/core-api/src/com/intellij/lang/MetaLanguage.java) |
| [com.intellij.moduleService](https://jb.gg/ipe?extensions=com.intellij.moduleService) | `n/a` |
| [com.intellij.multiHostInjector](https://jb.gg/ipe?extensions=com.intellij.multiHostInjector) ![Project-Level][project-level] | [`MultiHostInjector`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java) |
| [com.intellij.pom.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.pom.declarationSearcher) | [`PomDeclarationSearcher`](%gh-ic%/platform/core-api/src/com/intellij/pom/PomDeclarationSearcher.java) |
| [com.intellij.postStartupActivity](https://jb.gg/ipe?extensions=com.intellij.postStartupActivity) | [`StartupActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.projectService](https://jb.gg/ipe?extensions=com.intellij.projectService) | `n/a` |
| [com.intellij.requiredForSmartModeStartupActivity](https://jb.gg/ipe?extensions=com.intellij.requiredForSmartModeStartupActivity) | [`RequiredForSmartMode`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.startupActivity](https://jb.gg/ipe?extensions=com.intellij.startupActivity) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`InitProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.stubElementTypeHolder](https://jb.gg/ipe?extensions=com.intellij.stubElementTypeHolder) | `n/a` |
| [com.intellij.vfs.asyncListener](https://jb.gg/ipe?extensions=com.intellij.vfs.asyncListener) | [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) |
| [com.intellij.virtualFileManagerListener](https://jb.gg/ipe?extensions=com.intellij.virtualFileManagerListener) | [`VirtualFileManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| [com.intellij.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.writingAccessProvider) ![Project-Level][project-level] | [`WritingAccessProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/WritingAccessProvider.java) |

### CoreImpl.xml

[`CoreImpl.xml`](%gh-ic%/platform/core-impl/resources/META-INF/CoreImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.controlFlowProvider](https://jb.gg/ipe?extensions=com.intellij.controlFlowProvider) | [`ControlFlowProvider`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/controlflow/ControlFlowProvider.java) |
| [com.intellij.diagnostic.freezeProfiler](https://jb.gg/ipe?extensions=com.intellij.diagnostic.freezeProfiler) ![Internal API][internal] | [`FreezeProfiler`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/FreezeProfiler.java) |
| [com.intellij.documentWriteAccessGuard](https://jb.gg/ipe?extensions=com.intellij.documentWriteAccessGuard) ![Experimental API][experimental] | [`DocumentWriteAccessGuard`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/impl/DocumentWriteAccessGuard.java) |
| [com.intellij.elementsToHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.elementsToHighlightFilter) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.lang.ast.factory](https://jb.gg/ipe?extensions=com.intellij.lang.ast.factory) | [`ASTFactory`](%gh-ic%/platform/core-impl/src/com/intellij/lang/ASTFactory.java) |
| [com.intellij.lang.tokenSeparatorGenerator](https://jb.gg/ipe?extensions=com.intellij.lang.tokenSeparatorGenerator) | [`TokenSeparatorGenerator`](%gh-ic%/platform/core-api/src/com/intellij/lang/TokenSeparatorGenerator.java) |
| [com.intellij.lang.treePatcher](https://jb.gg/ipe?extensions=com.intellij.lang.treePatcher) | [`TreePatcher`](%gh-ic%/platform/core-impl/src/com/intellij/psi/templateLanguages/TreePatcher.java) |
| [com.intellij.psi.implicitReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.implicitReferenceProvider) | [`ImplicitReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java) |
| [com.intellij.psi.referenceContributor](https://jb.gg/ipe?extensions=com.intellij.psi.referenceContributor) | [`PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java) |
| [com.intellij.psi.symbolReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.symbolReferenceProvider) | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.psi.treeChangeListener](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangeListener) ![Project-Level][project-level] | [`PsiTreeChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiTreeChangeListener.java) |
| [com.intellij.psi.treeChangePreprocessor](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangePreprocessor) ![Project-Level][project-level] | [`PsiTreeChangePreprocessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiTreeChangePreprocessor.java) |
| [com.intellij.smartPointer.anchorProvider](https://jb.gg/ipe?extensions=com.intellij.smartPointer.anchorProvider) | [`SmartPointerAnchorProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/smartPointers/SmartPointerAnchorProvider.java) |
| [com.intellij.treeCopyHandler](https://jb.gg/ipe?extensions=com.intellij.treeCopyHandler) | [`TreeCopyHandler`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeCopyHandler.java) |
| [com.intellij.virtualFileSystem](https://jb.gg/ipe?extensions=com.intellij.virtualFileSystem) | [`VirtualFileSystem`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) |

### DomPlugin.xml

[`DomPlugin.xml`](%gh-ic%/xml/dom-impl/src/META-INF/DomPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dom.converter](https://jb.gg/ipe?extensions=com.intellij.dom.converter) | `n/a` |
| [com.intellij.dom.customAnnotationChecker](https://jb.gg/ipe?extensions=com.intellij.dom.customAnnotationChecker) | [`DomCustomAnnotationChecker`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomCustomAnnotationChecker.java) |
| [com.intellij.dom.extender](https://jb.gg/ipe?extensions=com.intellij.dom.extender) | [`DomExtender`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtender.java) |
| [com.intellij.dom.fileDescription](https://jb.gg/ipe?extensions=com.intellij.dom.fileDescription) ![Deprecated][deprecated] | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.fileMetaData](https://jb.gg/ipe?extensions=com.intellij.dom.fileMetaData) | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.dom.gotoSuper) | [`DomElementNavigationProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementNavigationProvider.java) |
| [com.intellij.dom.implementation](https://jb.gg/ipe?extensions=com.intellij.dom.implementation) | [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) |
| [com.intellij.dom.uiControlsProvider](https://jb.gg/ipe?extensions=com.intellij.dom.uiControlsProvider) ![Non-Dynamic][non-dynamic] | [`Consumer`](%gh-ic%/platform/util-rt/src/com/intellij/util/Consumer.java) |
| [com.intellij.moduleContextProvider](https://jb.gg/ipe?extensions=com.intellij.moduleContextProvider) | [`ModuleContextProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ModuleContextProvider.java) |

### duplicates-analysis.xml

[`duplicates-analysis.xml`](%gh-ic%/platform/duplicates-analysis/resources/META-INF/duplicates-analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.equivalenceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.equivalenceDescriptorProvider) | [`EquivalenceDescriptorProvider`](%gh-ic%/platform/duplicates-analysis/src/com/intellij/dupLocator/equivalence/EquivalenceDescriptorProvider.java) |

### dvcs.xml

[`dvcs.xml`](%gh-ic%/platform/dvcs-impl/src/META-INF/dvcs.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cherryPicker](https://jb.gg/ipe?extensions=com.intellij.cherryPicker) ![Project-Level][project-level] | [`VcsCherryPicker`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/cherrypick/VcsCherryPicker.java) |
| [com.intellij.clonePathProvider](https://jb.gg/ipe?extensions=com.intellij.clonePathProvider) | [`ClonePathProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/ClonePathProvider.java) |
| [com.intellij.commitNodeUiRenderExtension](https://jb.gg/ipe?extensions=com.intellij.commitNodeUiRenderExtension) ![Experimental API][experimental] | [`CommitNodeUiRenderExtension`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/ui/CommitNodeUiRenderExtension.java) |
| [com.intellij.prePushHandler](https://jb.gg/ipe?extensions=com.intellij.prePushHandler) ![Project-Level][project-level] | [`PrePushHandler`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PrePushHandler.java) |
| [com.intellij.pushDialogActionsProvider](https://jb.gg/ipe?extensions=com.intellij.pushDialogActionsProvider) ![Internal API][internal] | [`PushDialogActionsProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogActionsProvider.kt) |
| [com.intellij.pushDialogCustomizer](https://jb.gg/ipe?extensions=com.intellij.pushDialogCustomizer) ![Internal API][internal] | [`PushDialogCustomizer`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogCustomizer.kt) |
| [com.intellij.pushSupport](https://jb.gg/ipe?extensions=com.intellij.pushSupport) ![Project-Level][project-level] | [`PushSupport`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/push/PushSupport.java) |
| [com.intellij.vcsRepositoryCreator](https://jb.gg/ipe?extensions=com.intellij.vcsRepositoryCreator) | [`VcsRepositoryCreator`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryCreator.java) |

### Editor.xml

[`Editor.xml`](%gh-ic%/platform/editor-ui-api/resources/META-INF/Editor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.editorFactoryListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryListener) | [`EditorFactoryListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorFactoryListener.java) |
| [com.intellij.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.syntaxHighlighter) | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |

### EditorExtensionPoints.xml

[`EditorExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/EditorExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backspaceHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.backspaceHandlerDelegate) | [`BackspaceHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceHandlerDelegate.java) |
| [com.intellij.basicWordSelectionFilter](https://jb.gg/ipe?extensions=com.intellij.basicWordSelectionFilter) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.bidiRegionsSeparator](https://jb.gg/ipe?extensions=com.intellij.bidiRegionsSeparator) | [`BidiRegionsSeparator`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/bidi/BidiRegionsSeparator.java) |
| [com.intellij.codeBlockProvider](https://jb.gg/ipe?extensions=com.intellij.codeBlockProvider) | [`CodeBlockProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CodeBlockProvider.java) |
| [com.intellij.codeInsight.fillParagraph](https://jb.gg/ipe?extensions=com.intellij.codeInsight.fillParagraph) | [`ParagraphFillHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/fillParagraph/ParagraphFillHandler.java) |
| [com.intellij.commentCompleteHandler](https://jb.gg/ipe?extensions=com.intellij.commentCompleteHandler) | [`CommentCompleteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CommentCompleteHandler.java) |
| [com.intellij.copyPastePostProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePostProcessor) | [`CopyPastePostProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePostProcessor.java) |
| [com.intellij.copyPastePreProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePreProcessor) | [`CopyPastePreProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePreProcessor.java) |
| [com.intellij.customPasteProvider](https://jb.gg/ipe?extensions=com.intellij.customPasteProvider) | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.editor.backspaceModeOverride](https://jb.gg/ipe?extensions=com.intellij.editor.backspaceModeOverride) | [`BackspaceModeOverride`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceModeOverride.java) |
| [com.intellij.enterBetweenBracesDelegate](https://jb.gg/ipe?extensions=com.intellij.enterBetweenBracesDelegate) | [`EnterBetweenBracesDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterBetweenBracesDelegate.java) |
| [com.intellij.enterHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.enterHandlerDelegate) | [`EnterHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java) |
| [com.intellij.extendWordSelectionHandler](https://jb.gg/ipe?extensions=com.intellij.extendWordSelectionHandler) | [`ExtendWordSelectionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java) |
| [com.intellij.flipCommaIntention.flipper](https://jb.gg/ipe?extensions=com.intellij.flipCommaIntention.flipper) | [`Flipper`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/FlipCommaIntention.java) |
| [com.intellij.generalEditorOptionsCustomizer](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsCustomizer) ![Internal API][internal] | [`EditorOptionsPageCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/EditorOptionsPageCustomizer.kt) |
| [com.intellij.generalEditorOptionsExtension](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsExtension) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.joinLinesHandler](https://jb.gg/ipe?extensions=com.intellij.joinLinesHandler) | [`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) |
| [com.intellij.lang.emacs](https://jb.gg/ipe?extensions=com.intellij.lang.emacs) | [`EmacsProcessingHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/emacs/EmacsProcessingHandler.java) |
| [com.intellij.lang.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.lang.quoteHandler) | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.lang.smartEnterProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.smartEnterProcessor) | [`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) |
| [com.intellij.listSplitJoinContext](https://jb.gg/ipe?extensions=com.intellij.listSplitJoinContext) ![Experimental API][experimental] | [`ListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt) |
| [com.intellij.moveLeftRightHandler](https://jb.gg/ipe?extensions=com.intellij.moveLeftRightHandler) | [`MoveElementLeftRightHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) |
| [com.intellij.preserveIndentOnPaste](https://jb.gg/ipe?extensions=com.intellij.preserveIndentOnPaste) | `n/a` |
| [com.intellij.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.quoteHandler) | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.selectionUnquotingFilter](https://jb.gg/ipe?extensions=com.intellij.selectionUnquotingFilter) | [`UnquotingFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectionQuotingTypedHandler.java) |
| [com.intellij.statementUpDownMover](https://jb.gg/ipe?extensions=com.intellij.statementUpDownMover) | [`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java) |
| [com.intellij.typedHandler](https://jb.gg/ipe?extensions=com.intellij.typedHandler) | [`TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) |
| [com.intellij.typingActionsExtension](https://jb.gg/ipe?extensions=com.intellij.typingActionsExtension) ![Experimental API][experimental] | [`TypingActionsExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/TypingActionsExtension.java) |
| [com.intellij.wordBoundaryFilter](https://jb.gg/ipe?extensions=com.intellij.wordBoundaryFilter) | [`WordBoundaryFilter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/actions/WordBoundaryFilter.java) |

### ExternalSystemDependencyUpdater.xml

[`ExternalSystemDependencyUpdater.xml`](%gh-ic%/platform/external-system-api/dependency-updater/resources/META-INF/ExternalSystemDependencyUpdater.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.externalSystem.dependencyModifier](https://jb.gg/ipe?extensions=com.intellij.externalSystem.dependencyModifier) ![Experimental API][experimental] ![Project-Level][project-level] | [`ExternalDependencyModificator`](%gh-ic%/platform/external-system-api/dependency-updater/src/com/intellij/externalSystem/ExternalDependencyModificator.java) |

### ExternalSystemExtensionPoints.xml

[`ExternalSystemExtensionPoints.xml`](%gh-ic%/platform/external-system-impl/resources/META-INF/ExternalSystemExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.externalExecutionAware](https://jb.gg/ipe?extensions=com.intellij.externalExecutionAware) ![Experimental API][experimental] | [`ExternalSystemExecutionAware`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemExecutionAware.kt) |
| [com.intellij.externalIconProvider](https://jb.gg/ipe?extensions=com.intellij.externalIconProvider) | [`ExternalSystemIconProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemIconProvider.kt) |
| [com.intellij.externalProjectDataService](https://jb.gg/ipe?extensions=com.intellij.externalProjectDataService) | [`ProjectDataService`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataService.java) |
| [com.intellij.externalProjectStructureCustomizer](https://jb.gg/ipe?extensions=com.intellij.externalProjectStructureCustomizer) | [`ExternalProjectStructureCustomizer`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/importing/ExternalProjectStructureCustomizer.java) |
| [com.intellij.externalProjectWatcherContributor](https://jb.gg/ipe?extensions=com.intellij.externalProjectWatcherContributor) ![Deprecated][deprecated] | [`Contributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/autoimport/ExternalSystemProjectsWatcherImpl.java) |
| [com.intellij.externalSystem.beforeRunTaskImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.beforeRunTaskImporter) | [`BeforeRunTaskImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/BeforeRunTaskImporter.java) |
| [com.intellij.externalSystem.debuggerBackend](https://jb.gg/ipe?extensions=com.intellij.externalSystem.debuggerBackend) | [`DebuggerBackendExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/debugger/DebuggerBackendExtension.java) |
| [com.intellij.externalSystem.facetConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.facetConfigurationImporter) | [`FacetConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/FacetConfigurationImporter.java) |
| [com.intellij.externalSystem.modifiableModelsProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystem.modifiableModelsProvider) ![Experimental API][experimental] | [`ModifiableModelsProviderExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ModifiableModelsProviderExtension.java) |
| [com.intellij.externalSystem.moduleDataServiceExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystem.moduleDataServiceExtension) ![Deprecated][deprecated] | [`ModuleDataServiceExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ModuleDataServiceExtension.java) |
| [com.intellij.externalSystem.runConfigurationEx](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationEx) | [`ExternalSystemRunConfigurationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/configuration/ExternalSystemRunConfigurationExtension.kt) |
| [com.intellij.externalSystem.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationExtension) ![Deprecated][deprecated] | [`ExternalSystemRunConfigurationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemRunConfigurationExtension.java) |
| [com.intellij.externalSystem.runConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationImporter) | [`RunConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/RunConfigurationImporter.java) |
| [com.intellij.externalSystemConfigLocator](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigLocator) | [`ExternalSystemConfigLocator`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/settings/ExternalSystemConfigLocator.java) |
| [com.intellij.externalSystemConfigurationHandler](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigurationHandler) ![Experimental API][experimental] | [`ConfigurationHandler`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/ConfigurationHandler.java) |
| [com.intellij.externalSystemContentRootContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemContentRootContributor) | [`ExternalSystemContentRootContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemContentRootContributor.kt) |
| [com.intellij.externalSystemCrcCalculator](https://jb.gg/ipe?extensions=com.intellij.externalSystemCrcCalculator) ![Experimental API][experimental] | [`ExternalSystemCrcCalculator`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemCrcCalculator.kt) |
| [com.intellij.externalSystemDependencyAnalyzer](https://jb.gg/ipe?extensions=com.intellij.externalSystemDependencyAnalyzer) | [`DependencyAnalyzerExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/dependency/analyzer/DependencyAnalyzerExtension.kt) |
| [com.intellij.externalSystemExecutionConsoleManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemExecutionConsoleManager) | [`ExternalSystemExecutionConsoleManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/execution/ExternalSystemExecutionConsoleManager.java) |
| [com.intellij.externalSystemKeymapProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemKeymapProvider) | [`ActionsProvider`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemKeymapExtension.java) |
| [com.intellij.externalSystemManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemManager) | [`ExternalSystemManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemManager.java) |
| [com.intellij.externalSystemNotificationExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystemNotificationExtension) | [`ExternalSystemNotificationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/notification/ExternalSystemNotificationExtension.java) |
| [com.intellij.externalSystemOutputDispatcher](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputDispatcher) | [`ExternalSystemOutputDispatcherFactory`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputDispatcherFactory.kt) |
| [com.intellij.externalSystemOutputParserProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputParserProvider) | [`ExternalSystemOutputParserProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputParserProvider.java) |
| [com.intellij.externalSystemSettingsListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemSettingsListener) | [`ExternalSystemSettingsListenerEx`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/settings/ExternalSystemSettingsListenerEx.kt) |
| [com.intellij.externalSystemTaskNotificationListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemTaskNotificationListener) | [`ExternalSystemTaskNotificationListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/task/ExternalSystemTaskNotificationListener.java) |
| [com.intellij.externalSystemUnlinkedProjectAware](https://jb.gg/ipe?extensions=com.intellij.externalSystemUnlinkedProjectAware) | [`ExternalSystemUnlinkedProjectAware`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autolink/ExternalSystemUnlinkedProjectAware.kt) |
| [com.intellij.externalSystemViewContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemViewContributor) | [`ExternalSystemViewContributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/view/ExternalSystemViewContributor.java) |
| [com.intellij.externalSystemWorkspaceContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemWorkspaceContributor) ![Experimental API][experimental] | [`ExternalSystemWorkspaceContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ExternalSystemWorkspaceContributor.java) |

### FormatterExtensionPoints.xml

[`FormatterExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/FormatterExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.documentMerger](https://jb.gg/ipe?extensions=com.intellij.documentMerger) | [`DocumentMerger`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/DocumentMerger.java) |
| [com.intellij.fileSetDescriptorFactory](https://jb.gg/ipe?extensions=com.intellij.fileSetDescriptorFactory) | [`FileSetDescriptorFactory`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/fileSet/FileSetDescriptorFactory.java) |
| [com.intellij.fileTypeIndentOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.fileTypeIndentOptionsProvider) | [`FileTypeIndentOptionsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java) |
| [com.intellij.formatOnSaveOptions.defaultsProvider](https://jb.gg/ipe?extensions=com.intellij.formatOnSaveOptions.defaultsProvider) | [`DefaultsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/onSave/FormatOnSaveOptionsBase.java) |
| [com.intellij.formatting.injectedOptions](https://jb.gg/ipe?extensions=com.intellij.formatting.injectedOptions) | [`InjectedFormattingOptionsProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/InjectedFormattingOptionsProvider.java) |
| [com.intellij.lang.formatting.extractor](https://jb.gg/ipe?extensions=com.intellij.lang.formatting.extractor) | [`LangCodeStyleExtractor`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/extractor/differ/LangCodeStyleExtractor.java) |
| [com.intellij.langCodeStyleSettingsContributor](https://jb.gg/ipe?extensions=com.intellij.langCodeStyleSettingsContributor) ![Internal API][internal] | [`LanguageCodeStyleSettingsContributor`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsContributor.java) |
| [com.intellij.langCodeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.langCodeStyleSettingsProvider) | [`LanguageCodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java) |
| [com.intellij.lineIndentProvider](https://jb.gg/ipe?extensions=com.intellij.lineIndentProvider) | [`LineIndentProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/lineIndent/LineIndentProvider.java) |
| [com.intellij.predefinedCodeStyle](https://jb.gg/ipe?extensions=com.intellij.predefinedCodeStyle) | [`PredefinedCodeStyle`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/PredefinedCodeStyle.java) |
| [com.intellij.rearranger.ui](https://jb.gg/ipe?extensions=com.intellij.rearranger.ui) | [`Factory`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/std/ArrangementUiComponent.java) |

### IdeCore.xml

[`IdeCore.xml`](%gh-ic%/platform/ide-core-impl/resources/META-INF/IdeCore.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.applicationInitializedListener](https://jb.gg/ipe?extensions=com.intellij.applicationInitializedListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ApplicationInitializedListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/ApplicationInitializedListener.kt) |
| [com.intellij.notificationGroup](https://jb.gg/ipe?extensions=com.intellij.notificationGroup) | `n/a` |
| [com.intellij.registryKey](https://jb.gg/ipe?extensions=com.intellij.registryKey) | `n/a` |

### Indexing.xml

[`Indexing.xml`](%gh-ic%/platform/indexing-api/resources/META-INF/Indexing.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeUsageScopeOptimizer](https://jb.gg/ipe?extensions=com.intellij.codeUsageScopeOptimizer) | [`ScopeOptimizer`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |
| [com.intellij.definitionsSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsSearch) ![Deprecated][deprecated] | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.dumbServiceInitializationCondition](https://jb.gg/ipe?extensions=com.intellij.dumbServiceInitializationCondition) ![Internal API][internal] | [`DumbServiceInitializationCondition`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/DumbServiceInitializationCondition.java) |
| [com.intellij.fileBasedIndex](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndex) | [`FileBasedIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java) |
| [com.intellij.fileBasedIndexInfrastructureExtension](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexInfrastructureExtension) ![Internal API][internal] | [`FileBasedIndexInfrastructureExtension`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/FileBasedIndexInfrastructureExtension.java) |
| [com.intellij.fileBasedIndexLayout](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexLayout) ![Internal API][internal] | [`FileBasedIndexLayoutProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/storage/FileBasedIndexLayoutProvider.java) |
| [com.intellij.findModelExtension](https://jb.gg/ipe?extensions=com.intellij.findModelExtension) ![Internal API][internal] | [`FindModelExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/find/FindModelExtension.java) |
| [com.intellij.indexableFilesContributor](https://jb.gg/ipe?extensions=com.intellij.indexableFilesContributor) ![Experimental API][experimental] | [`IndexableFilesContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFilesContributor.java) |
| [com.intellij.indexedRootsProvider](https://jb.gg/ipe?extensions=com.intellij.indexedRootsProvider) | [`IndexableSetContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/IndexableSetContributor.java) |
| [com.intellij.indexingFlavor](https://jb.gg/ipe?extensions=com.intellij.indexingFlavor) ![Experimental API][experimental] ![Internal API][internal] | [`FileIndexingFlavorProvider`](%gh-ic%/platform/core-api/src/com/intellij/util/indexing/flavor/FileIndexingFlavorProvider.java) |
| [com.intellij.projectIndexingHistoryListener](https://jb.gg/ipe?extensions=com.intellij.projectIndexingHistoryListener) | [`ProjectIndexingHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [com.intellij.referencesSearch](https://jb.gg/ipe?extensions=com.intellij.referencesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.stubIndex](https://jb.gg/ipe?extensions=com.intellij.stubIndex) | [`StubIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubIndexExtension.java) |

### Inspect.xml

[`Inspect.xml`](%gh-ic%/platform/inspect/resources/META-INF/Inspect.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.inspectResultsConsumer](https://jb.gg/ipe?extensions=com.intellij.inspectResultsConsumer) ![Internal API][internal] | [`InspectResultsConsumer`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/InspectResultsConsumer.java) |
| [com.intellij.inspectionApplicationFactory](https://jb.gg/ipe?extensions=com.intellij.inspectionApplicationFactory) ![Internal API][internal] | [`InspectionApplicationFactory`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/InspectionApplicationFactory.java) |
| [com.intellij.inspectionGroupProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionGroupProvider) | [`InspectionGroupProvider`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/inspectionProfile/InspectionGroupProvider.kt) |

### intellij.json.xml

[`intellij.json.xml`](%gh-ic%/json/resources/intellij.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.JsonSchema.ContentAwareSchemaFileProvider](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ContentAwareSchemaFileProvider) ![Internal API][internal] | [`ContentAwareJsonSchemaFileProvider`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/ContentAwareJsonSchemaFileProvider.java) |
| [JavaScript.JsonSchema.ProviderFactory](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ProviderFactory) | [`JsonSchemaProviderFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaProviderFactory.java) |
| [com.intellij.json.catalog.exclusion](https://jb.gg/ipe?extensions=com.intellij.json.catalog.exclusion) | [`JsonSchemaCatalogExclusion`](%gh-ic%/json/src/com/jetbrains/jsonSchema/remote/JsonSchemaCatalogExclusion.java) |
| [com.intellij.json.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.json.customStructureViewFactory) ![Internal API][internal] | [`JsonCustomStructureViewFactory`](%gh-ic%/json/src/com/intellij/json/structureView/JsonCustomStructureViewFactory.java) |
| [com.intellij.json.jsonLikePsiWalkerFactory](https://jb.gg/ipe?extensions=com.intellij.json.jsonLikePsiWalkerFactory) | [`JsonLikePsiWalkerFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonLikePsiWalkerFactory.java) |
| [com.intellij.json.jsonLiteralChecker](https://jb.gg/ipe?extensions=com.intellij.json.jsonLiteralChecker) | [`JsonLiteralChecker`](%gh-ic%/json/src/com/intellij/json/codeinsight/JsonLiteralChecker.java) |
| [com.intellij.json.jsonSchemaEnabler](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaEnabler) | [`JsonSchemaEnabler`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaEnabler.java) |
| [com.intellij.json.jsonStandardComplianceProvider](https://jb.gg/ipe?extensions=com.intellij.json.jsonStandardComplianceProvider) | [`JsonStandardComplianceProvider`](%gh-ic%/json/src/com/intellij/json/codeinsight/JsonStandardComplianceProvider.java) |
| [com.intellij.json.jsonWidgetSuppressor](https://jb.gg/ipe?extensions=com.intellij.json.jsonWidgetSuppressor) | [`JsonWidgetSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) |

### intellij.notebooks.visualization.xml

[`intellij.notebooks.visualization.xml`](%gh-ic%/notebooks/visualization/resources/intellij.notebooks.visualization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.datavis.inlays.components.inlayOutputProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.inlayOutputProvider) | [`InlayOutputProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/InlayOutputProvider.kt) |
| [com.intellij.datavis.inlays.components.inlayStateCustomizer](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.inlayStateCustomizer) ![Experimental API][experimental] | [`InlayStateCustomizer`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/InlayStateCustomizer.kt) |
| [com.intellij.datavis.inlays.components.multiOutputProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.multiOutputProvider) | [`MultiOutputProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/MultiOutputProvider.kt) |
| [com.intellij.datavis.inlays.inlayDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.inlayDescriptorProvider) | [`InlayDescriptorProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/InlayElementDescriptor.kt) |
| [com.intellij.datavis.r.inlays.components.graphicsManagerProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.r.inlays.components.graphicsManagerProvider) | [`GraphicsManagerProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/GraphicsManagerProvider.kt) |
| [org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider) | [`NotebookEditorAppearanceProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookEditorAppearanceProvider.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory) | [`NotebookOutputComponentFactory`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputComponentFactory.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper) | [`NotebookOutputComponentWrapper`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputComponentWrapper.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor) | [`NotebookOutputDataKeyExtractor`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputDataKeyExtractor.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellInlayController](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellInlayController) | [`Factory`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellInlayController.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellLinesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellLinesProvider) | [`NotebookCellLinesProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellLinesProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider) | [`NotebookCellSelectionModelProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellSelectionModelProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider) | [`NotebookIntervalPointerFactoryProvider`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookIntervalPointerFactoryProvider.kt) |
| [org.jetbrains.plugins.notebooks.visualization.r.inlays.visualisation.uiCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.visualization.r.inlays.visualisation.uiCustomizer) | [`UiCustomizer`](%gh-ic%/notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/ui/UiCustomizer.kt) |

### intellij.platform.remoteServers.impl.xml

[`intellij.platform.remoteServers.impl.xml`](%gh-ic%/platform/remote-servers/impl/resources/intellij.platform.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.defaultConfigurable.includeServerType](https://jb.gg/ipe?extensions=com.intellij.remoteServer.defaultConfigurable.includeServerType) | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |
| [com.intellij.remoteServer.deploymentSource.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.deploymentSource.type) | [`DeploymentSourceType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/deployment/DeploymentSourceType.java) |
| [com.intellij.remoteServer.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.remoteServer.runConfigurationExtension) | [`DeployToServerRunConfigurationExtension`](%gh-ic%/platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerRunConfigurationExtension.java) |
| [com.intellij.remoteServer.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.type) | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |

### intellij.platform.tips.xml

[`intellij.platform.tips.xml`](%gh-ic%/platform/tips-of-the-day/resources/intellij.platform.tips.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tipAndTrickPromotionFactory](https://jb.gg/ipe?extensions=com.intellij.tipAndTrickPromotionFactory) ![Internal API][internal] | [`TipAndTrickPromotionFactory`](%gh-ic%/platform/tips-of-the-day/src/com/intellij/ide/util/TipAndTrickPromotionFactory.kt) |

### LangExtensionPoints.xml

[`LangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.aliasingPsiTargetMapper](https://jb.gg/ipe?extensions=com.intellij.aliasingPsiTargetMapper) | [`AliasingPsiTargetMapper`](%gh-ic%/platform/core-api/src/com/intellij/psi/targets/AliasingPsiTargetMapper.java) |
| [com.intellij.analyzeStacktraceFilter](https://jb.gg/ipe?extensions=com.intellij.analyzeStacktraceFilter) ![Project-Level][project-level] | [`Filter`](%gh-ic%/platform/execution/src/com/intellij/execution/filters/Filter.java) |
| [com.intellij.anchorReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.anchorReferenceProvider) | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.annotator](https://jb.gg/ipe?extensions=com.intellij.annotator) | [`Annotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) |
| [com.intellij.anonymousElementProvider](https://jb.gg/ipe?extensions=com.intellij.anonymousElementProvider) | [`AnonymousElementProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/navigation/AnonymousElementProvider.java) |
| [com.intellij.autoImportOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.autoImportOptionsProvider) ![Project-Level][project-level] | [`AutoImportOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/AutoImportOptionsProvider.java) |
| [com.intellij.bookmarkProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarkProvider) ![Project-Level][project-level] | [`BookmarkProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarkProvider.java) |
| [com.intellij.bookmarksListProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarksListProvider) ![Project-Level][project-level] | [`BookmarksListProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListProvider.java) |
| [com.intellij.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.braceMatcher) | [`BraceMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/BraceMatcher.java) |
| [com.intellij.breadcrumbsInfoProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsInfoProvider) | [`BreadcrumbsProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java) |
| [com.intellij.cacheBuilder](https://jb.gg/ipe?extensions=com.intellij.cacheBuilder) | [`WordsScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java) |
| [com.intellij.callHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.callHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.cantBeStatic](https://jb.gg/ipe?extensions=com.intellij.cantBeStatic) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeBlockSupportHandler](https://jb.gg/ipe?extensions=com.intellij.codeBlockSupportHandler) | [`CodeBlockSupportHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java) |
| [com.intellij.codeCompletionConfigurable](https://jb.gg/ipe?extensions=com.intellij.codeCompletionConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.codeFoldingOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.codeFoldingOptionsProvider) | [`CodeFoldingOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/CodeFoldingOptionsProvider.java) |
| [com.intellij.codeInsight.codeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProvider) ![Experimental API][experimental] | [`CodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProvider.kt) |
| [com.intellij.codeInsight.codeVisionProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProviderFactory) | [`CodeVisionProviderFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProviderFactory.kt) |
| [com.intellij.codeInsight.codeVisionSettingsPreviewLanguage](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionSettingsPreviewLanguage) | `n/a` |
| [com.intellij.codeInsight.daemonBoundCodeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.daemonBoundCodeVisionProvider) | [`DaemonBoundCodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hints/codeVision/DaemonBoundCodeVisionProvider.kt) |
| [com.intellij.codeInsight.delegateMethods](https://jb.gg/ipe?extensions=com.intellij.codeInsight.delegateMethods) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.gotoSuper) | [`CodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/CodeInsightActionHandler.java) |
| [com.intellij.codeInsight.implementMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implementMethod) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.inlayProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProvider) | [`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt) |
| [com.intellij.codeInsight.inlayProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProviderFactory) | [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt) |
| [com.intellij.codeInsight.lineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.lineMarkerProvider) | [`LineMarkerProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProvider.java) |
| [com.intellij.codeInsight.overrideMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.overrideMethod) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.parameterInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo) | [`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java) |
| [com.intellij.codeInsight.parameterInfo.controller.provider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.controller.provider) | [`ParameterInfoControllerProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoControllerProvider.java) |
| [com.intellij.codeInsight.parameterInfo.listener](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.listener) | [`ParameterInfoListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoListener.java) |
| [com.intellij.codeInsight.parameterNameHints](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHints) | [`InlayParameterHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java) |
| [com.intellij.codeInsight.parameterNameHintsSuppressor](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHintsSuppressor) | [`ParameterNameHintsSuppressor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt) |
| [com.intellij.codeInsight.surroundWithRangeAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeInsight.surroundWithRangeAdjuster) | [`SurroundWithRangeAdjuster`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/generation/surroundWith/SurroundWithRangeAdjuster.java) |
| [com.intellij.codeInsight.typeInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.typeInfo) | [`ExpressionTypeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/lang/ExpressionTypeProvider.java) |
| [com.intellij.codeInsight.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.unresolvedReferenceQuickFixProvider) | [`UnresolvedReferenceQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/quickfix/UnresolvedReferenceQuickFixProvider.java) |
| [com.intellij.codeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsProvider) | [`CodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java) |
| [com.intellij.codeVisionPainterProvider](https://jb.gg/ipe?extensions=com.intellij.codeVisionPainterProvider) ![Non-Dynamic][non-dynamic] | [`ICodeVisionEntryBasePainter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/ui/renderers/painters/ICodeVisionEntryBasePainter.kt) |
| [com.intellij.colorAndFontDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.colorAndFontDescriptorProvider) | [`ColorAndFontDescriptorsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorAndFontDescriptorsProvider.java) |
| [com.intellij.colorAndFontPanelFactory](https://jb.gg/ipe?extensions=com.intellij.colorAndFontPanelFactory) | [`ColorAndFontPanelFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ColorAndFontPanelFactory.java) |
| [com.intellij.colorProvider](https://jb.gg/ipe?extensions=com.intellij.colorProvider) | [`ElementColorProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) |
| [com.intellij.colorSettingsPage](https://jb.gg/ipe?extensions=com.intellij.colorSettingsPage) | [`ColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java) |
| [com.intellij.commandLineInspectionProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.commandLineInspectionProjectConfigurator) | [`CommandLineInspectionProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/CommandLineInspectionProjectConfigurator.java) |
| [com.intellij.commentTokenSetProvider](https://jb.gg/ipe?extensions=com.intellij.commentTokenSetProvider) | [`CommentTokenSetProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/cache/CommentTokenSetProvider.java) |
| [com.intellij.concatenationAwareInjector](https://jb.gg/ipe?extensions=com.intellij.concatenationAwareInjector) ![Project-Level][project-level] | [`ConcatenationAwareInjector`](%gh-ic%/platform/lang-api/src/com/intellij/lang/injection/ConcatenationAwareInjector.java) |
| [com.intellij.configurationProducer](https://jb.gg/ipe?extensions=com.intellij.configurationProducer) ![Deprecated][deprecated] | [`RuntimeConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/junit/RuntimeConfigurationProducer.java) |
| [com.intellij.configurationType](https://jb.gg/ipe?extensions=com.intellij.configurationType) | [`ConfigurationType`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java) |
| [com.intellij.console.folding](https://jb.gg/ipe?extensions=com.intellij.console.folding) | [`ConsoleFolding`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ConsoleFolding.java) |
| [com.intellij.consoleActionsPostProcessor](https://jb.gg/ipe?extensions=com.intellij.consoleActionsPostProcessor) | [`ConsoleActionsPostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/ConsoleActionsPostProcessor.java) |
| [com.intellij.consoleFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleFilterProvider) | [`ConsoleFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleFilterProvider.java) |
| [com.intellij.consoleHistoryModelProvider](https://jb.gg/ipe?extensions=com.intellij.consoleHistoryModelProvider) | [`ConsoleHistoryModelProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleHistoryModelProvider.java) |
| [com.intellij.consoleInputFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleInputFilterProvider) | [`ConsoleInputFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleInputFilterProvider.java) |
| [com.intellij.createFromTemplateActionReplacer](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateActionReplacer) | [`CreateFromTemplateActionReplacer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateActionReplacer.java) |
| [com.intellij.createFromTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateHandler) | [`CreateFromTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateHandler.java) |
| [com.intellij.customFoldingProvider](https://jb.gg/ipe?extensions=com.intellij.customFoldingProvider) | [`CustomFoldingProvider`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/CustomFoldingProvider.java) |
| [com.intellij.customLiveTemplate](https://jb.gg/ipe?extensions=com.intellij.customLiveTemplate) | [`CustomLiveTemplate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/CustomLiveTemplate.java) |
| [com.intellij.customScopesFilter](https://jb.gg/ipe?extensions=com.intellij.customScopesFilter) | [`CustomScopesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesFilter.java) |
| [com.intellij.customScopesProvider](https://jb.gg/ipe?extensions=com.intellij.customScopesProvider) ![Project-Level][project-level] | [`CustomScopesProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesProvider.java) |
| [com.intellij.customUsageSearcher](https://jb.gg/ipe?extensions=com.intellij.customUsageSearcher) | [`CustomUsageSearcher`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/CustomUsageSearcher.java) |
| [com.intellij.daemon.changeLocalityDetector](https://jb.gg/ipe?extensions=com.intellij.daemon.changeLocalityDetector) | [`ChangeLocalityDetector`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ChangeLocalityDetector.java) |
| [com.intellij.daemon.externalAnnotatorsFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.externalAnnotatorsFilter) | [`ExternalAnnotatorsFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) |
| [com.intellij.daemon.highlightInfoFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.highlightInfoFilter) | [`HighlightInfoFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoFilter.java) |
| [com.intellij.daemon.indentsPassFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.indentsPassFilter) | [`IndentsPassFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IndentsPassFilter.java) |
| [com.intellij.daemon.intentionActionFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.intentionActionFilter) | [`IntentionActionFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/IntentionActionFilter.java) |
| [com.intellij.daemon.statusItemMerger](https://jb.gg/ipe?extensions=com.intellij.daemon.statusItemMerger) | [`StatusItemMerger`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/StatusItemMerger.java) |
| [com.intellij.daemon.tooltipActionProvider](https://jb.gg/ipe?extensions=com.intellij.daemon.tooltipActionProvider) | [`TooltipActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/tooltips/TooltipActionProvider.java) |
| [com.intellij.declarationRangeHandler](https://jb.gg/ipe?extensions=com.intellij.declarationRangeHandler) | [`DeclarationRangeHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) |
| [com.intellij.defaultHighlightingSettingProvider](https://jb.gg/ipe?extensions=com.intellij.defaultHighlightingSettingProvider) | [`DefaultHighlightingSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/DefaultHighlightingSettingProvider.java) |
| [com.intellij.defaultLiveTemplates](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplates) | `n/a` |
| [com.intellij.defaultLiveTemplatesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplatesProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`DefaultLiveTemplatesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) |
| [com.intellij.defaultTemplatePropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultTemplatePropertiesProvider) | [`DefaultTemplatePropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/DefaultTemplatePropertiesProvider.java) |
| [com.intellij.definitionsScopedSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsScopedSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.diffPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.diffPreviewProvider) ![Non-Dynamic][non-dynamic] | [`DiffPreviewProvider`](%gh-ic%/platform/diff-api/src/com/intellij/openapi/diff/impl/settings/DiffPreviewProvider.java) |
| [com.intellij.dynamicContextProvider](https://jb.gg/ipe?extensions=com.intellij.dynamicContextProvider) | [`DynamicContextProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/paths/DynamicContextProvider.java) |
| [com.intellij.editorAppearanceConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorAppearanceConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.editorOptionsProvider) | [`EditorOptionsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsProvider.java) |
| [com.intellij.editorSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorSmartKeysConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorTabsConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorTabsConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.elementDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.elementDescriptionProvider) | [`ElementDescriptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java) |
| [com.intellij.elementPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.elementPreviewProvider) | [`ElementPreviewProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/preview/ElementPreviewProvider.java) |
| [com.intellij.elementSignatureProvider](https://jb.gg/ipe?extensions=com.intellij.elementSignatureProvider) | [`ElementSignatureProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/folding/impl/ElementSignatureProvider.java) |
| [com.intellij.emptyInspectionTreeActionProvider](https://jb.gg/ipe?extensions=com.intellij.emptyInspectionTreeActionProvider) | [`EmptyInspectionTreeActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/EmptyInspectionTreeActionProvider.java) |
| [com.intellij.errorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.errorOptionsProvider) | [`ErrorOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/ErrorOptionsProvider.java) |
| [com.intellij.errorQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.errorQuickFixProvider) | [`ErrorQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/ErrorQuickFixProvider.java) |
| [com.intellij.executionTargetLanguageRuntimeType](https://jb.gg/ipe?extensions=com.intellij.executionTargetLanguageRuntimeType) | [`LanguageRuntimeType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/LanguageRuntimeType.kt) |
| [com.intellij.executionTargetProvider](https://jb.gg/ipe?extensions=com.intellij.executionTargetProvider) | [`ExecutionTargetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/ExecutionTargetProvider.java) |
| [com.intellij.executionTargetType](https://jb.gg/ipe?extensions=com.intellij.executionTargetType) | [`TargetEnvironmentType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/TargetEnvironmentType.kt) |
| [com.intellij.executor](https://jb.gg/ipe?extensions=com.intellij.executor) | [`Executor`](%gh-ic%/platform/execution/src/com/intellij/execution/Executor.java) |
| [com.intellij.externalAnnotator](https://jb.gg/ipe?extensions=com.intellij.externalAnnotator) | [`ExternalAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) |
| [com.intellij.facet.toolWindow](https://jb.gg/ipe?extensions=com.intellij.facet.toolWindow) | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.facetType](https://jb.gg/ipe?extensions=com.intellij.facetType) | [`FacetType`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetType.java) |
| [com.intellij.favoriteNodeProvider](https://jb.gg/ipe?extensions=com.intellij.favoriteNodeProvider) ![Deprecated][deprecated] ![Project-Level][project-level] | [`FavoriteNodeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/favoritesTreeView/FavoriteNodeProvider.java) |
| [com.intellij.favoritesListProvider](https://jb.gg/ipe?extensions=com.intellij.favoritesListProvider) ![Deprecated][deprecated] ![Project-Level][project-level] | [`FavoritesListProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/favoritesTreeView/FavoritesListProvider.java) |
| [com.intellij.filePasteProvider](https://jb.gg/ipe?extensions=com.intellij.filePasteProvider) | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.fileStructureGroupRuleProvider](https://jb.gg/ipe?extensions=com.intellij.fileStructureGroupRuleProvider) | [`FileStructureGroupRuleProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/FileStructureGroupRuleProvider.java) |
| [com.intellij.fileTemplateGroup](https://jb.gg/ipe?extensions=com.intellij.fileTemplateGroup) | [`FileTemplateGroupDescriptorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptorFactory.java) |
| [com.intellij.fileType.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.fileType.fileViewProviderFactory) | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.fileTypeStatisticProvider](https://jb.gg/ipe?extensions=com.intellij.fileTypeStatisticProvider) | [`FileTypeStatisticProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/fileTypes/FileTypeStatisticProvider.java) |
| [com.intellij.filetype.prebuiltStubsProvider](https://jb.gg/ipe?extensions=com.intellij.filetype.prebuiltStubsProvider) ![Deprecated][deprecated] | [`PrebuiltStubsProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/stubs/PrebuiltStubs.kt) |
| [com.intellij.filetype.stubBuilder](https://jb.gg/ipe?extensions=com.intellij.filetype.stubBuilder) | [`BinaryFileStubBuilder`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/BinaryFileStubBuilder.java) |
| [com.intellij.findInProjectExtension](https://jb.gg/ipe?extensions=com.intellij.findInProjectExtension) ![Internal API][internal] | [`FindInProjectExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/FindInProjectExtension.kt) |
| [com.intellij.findUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.findUsagesHandlerFactory) ![Project-Level][project-level] | [`FindUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/FindUsagesHandlerFactory.java) |
| [com.intellij.focusModeProvider](https://jb.gg/ipe?extensions=com.intellij.focusModeProvider) ![Experimental API][experimental] | [`FocusModeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/focusMode/FocusModeProvider.java) |
| [com.intellij.framework.detector](https://jb.gg/ipe?extensions=com.intellij.framework.detector) | [`FrameworkDetector`](%gh-ic%/platform/lang-api/src/com/intellij/framework/detection/FrameworkDetector.java) |
| [com.intellij.generalCodeStyleOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalCodeStyleOptionsProvider) | [`GeneralCodeStyleOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/GeneralCodeStyleOptionsProvider.java) |
| [com.intellij.globalIndexFilter](https://jb.gg/ipe?extensions=com.intellij.globalIndexFilter) ![Internal API][internal] | [`GlobalIndexFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/GlobalIndexFilter.java) |
| [com.intellij.goto.nonProjectScopeDisabler](https://jb.gg/ipe?extensions=com.intellij.goto.nonProjectScopeDisabler) | `n/a` |
| [com.intellij.gotoActionAliasMatcher](https://jb.gg/ipe?extensions=com.intellij.gotoActionAliasMatcher) | [`GotoActionAliasMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoActionAliasMatcher.java) |
| [com.intellij.gotoClassContributor](https://jb.gg/ipe?extensions=com.intellij.gotoClassContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileContributor](https://jb.gg/ipe?extensions=com.intellij.gotoFileContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileCustomizer](https://jb.gg/ipe?extensions=com.intellij.gotoFileCustomizer) | [`GotoFileCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoFileCustomizer.java) |
| [com.intellij.gotoPrimeSymbolContributor](https://jb.gg/ipe?extensions=com.intellij.gotoPrimeSymbolContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoRelatedProvider](https://jb.gg/ipe?extensions=com.intellij.gotoRelatedProvider) | [`GotoRelatedProvider`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/GotoRelatedProvider.java) |
| [com.intellij.gotoSymbolContributor](https://jb.gg/ipe?extensions=com.intellij.gotoSymbolContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoTargetPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetPresentationProvider) | [`GotoTargetPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetPresentationProvider.java) |
| [com.intellij.gotoTargetRendererProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetRendererProvider) ![Deprecated][deprecated] | [`GotoTargetRendererProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetRendererProvider.java) |
| [com.intellij.heavyBracesHighlighter](https://jb.gg/ipe?extensions=com.intellij.heavyBracesHighlighter) ![Non-Dynamic][non-dynamic] | [`HeavyBraceHighlighter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HeavyBraceHighlighter.java) |
| [com.intellij.hectorComponentProvider](https://jb.gg/ipe?extensions=com.intellij.hectorComponentProvider) ![Project-Level][project-level] | [`HectorComponentPanelsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/HectorComponentPanelsProvider.java) |
| [com.intellij.highlightInfoPostFilter](https://jb.gg/ipe?extensions=com.intellij.highlightInfoPostFilter) ![Project-Level][project-level] | [`HighlightInfoPostFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoPostFilter.java) |
| [com.intellij.highlightRangeExtension](https://jb.gg/ipe?extensions=com.intellij.highlightRangeExtension) | [`HighlightRangeExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightRangeExtension.java) |
| [com.intellij.highlightUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.highlightUsagesHandlerFactory) | [`HighlightUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) |
| [com.intellij.highlightVisitor](https://jb.gg/ipe?extensions=com.intellij.highlightVisitor) ![Project-Level][project-level] | [`HighlightVisitor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightVisitor.java) |
| [com.intellij.highlightingPassFactory](https://jb.gg/ipe?extensions=com.intellij.highlightingPassFactory) | [`TextEditorHighlightingPassFactoryRegistrar`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeHighlighting/TextEditorHighlightingPassFactoryRegistrar.java) |
| [com.intellij.idIndexer](https://jb.gg/ipe?extensions=com.intellij.idIndexer) | [`IdIndexer`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/id/IdIndexer.java) |
| [com.intellij.implementationViewDocumentFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewDocumentFactory) | [`ImplementationViewDocumentFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewDocumentFactory.kt) |
| [com.intellij.implementationViewSessionFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewSessionFactory) | [`ImplementationViewSessionFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewSession.kt) |
| [com.intellij.implicitUsageProvider](https://jb.gg/ipe?extensions=com.intellij.implicitUsageProvider) | [`ImplicitUsageProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ImplicitUsageProvider.java) |
| [com.intellij.importFilteringRule](https://jb.gg/ipe?extensions=com.intellij.importFilteringRule) | [`ImportFilteringRule`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/ImportFilteringRule.java) |
| [com.intellij.include.provider](https://jb.gg/ipe?extensions=com.intellij.include.provider) | [`FileIncludeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/impl/include/FileIncludeProvider.java) |
| [com.intellij.indexPatternBuilder](https://jb.gg/ipe?extensions=com.intellij.indexPatternBuilder) | [`IndexPatternBuilder`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/search/IndexPatternBuilder.java) |
| [com.intellij.indexPatternProvider](https://jb.gg/ipe?extensions=com.intellij.indexPatternProvider) ![Non-Dynamic][non-dynamic] | [`IndexPatternProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/IndexPatternProvider.java) |
| [com.intellij.indexPatternSearch](https://jb.gg/ipe?extensions=com.intellij.indexPatternSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.indexableEntityInducedChangesProvider](https://jb.gg/ipe?extensions=com.intellij.indexableEntityInducedChangesProvider) ![Experimental API][experimental] | [`IndexableEntityInducedChangesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/IndexableEntityInducedChangesProvider.java) |
| [com.intellij.indexableEntityProvider](https://jb.gg/ipe?extensions=com.intellij.indexableEntityProvider) ![Experimental API][experimental] | [`IndexableEntityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/IndexableEntityProvider.java) |
| [com.intellij.indexableFilesFilter](https://jb.gg/ipe?extensions=com.intellij.indexableFilesFilter) ![Non-Dynamic][non-dynamic] ![Experimental API][experimental] ![Internal API][internal] | [`IndexableFilesFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/IndexableFilesFilter.java) |
| [com.intellij.indexableIteratorBuilderHandler](https://jb.gg/ipe?extensions=com.intellij.indexableIteratorBuilderHandler) | [`IndexableIteratorBuilderHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/builders/IndexableIteratorBuilderHandler.java) |
| [com.intellij.inspectionProfileActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionProfileActionProvider) | [`InspectionProfileActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionProfileActionProvider.java) |
| [com.intellij.inspectionResultsExportActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionResultsExportActionProvider) | [`InspectionResultsExportActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/ui/actions/InspectionResultsExportActionProvider.kt) |
| [com.intellij.intentionMenuContributor](https://jb.gg/ipe?extensions=com.intellij.intentionMenuContributor) ![Internal API][internal] | [`IntentionMenuContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IntentionMenuContributor.java) |
| [com.intellij.intentionsOrderProvider](https://jb.gg/ipe?extensions=com.intellij.intentionsOrderProvider) | [`IntentionsOrderProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionsOrderProvider.java) |
| [com.intellij.internalFileTemplate](https://jb.gg/ipe?extensions=com.intellij.internalFileTemplate) | `n/a` |
| [com.intellij.lang.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.lang.braceMatcher) | [`PairedBraceMatcher`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java) |
| [com.intellij.lang.codeReferenceSearcher](https://jb.gg/ipe?extensions=com.intellij.lang.codeReferenceSearcher) | [`CodeReferenceSearcher`](%gh-ic%/platform/indexing-api/src/com/intellij/model/search/CodeReferenceSearcher.java) |
| [com.intellij.lang.directNavigationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.directNavigationProvider) ![Experimental API][experimental] | [`DirectNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java) |
| [com.intellij.lang.documentation](https://jb.gg/ipe?extensions=com.intellij.lang.documentation) | [`DocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/DocumentationTargetProvider.java) |
| [com.intellij.lang.documentationFixer](https://jb.gg/ipe?extensions=com.intellij.lang.documentationFixer) | [`DocCommentFixer`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocCommentFixer.java) |
| [com.intellij.lang.documentationLinkHandler](https://jb.gg/ipe?extensions=com.intellij.lang.documentationLinkHandler) ![Experimental API][experimental] | [`DocumentationLinkHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/DocumentationLinkHandler.java) |
| [com.intellij.lang.documentationToolWindowManager](https://jb.gg/ipe?extensions=com.intellij.lang.documentationToolWindowManager) ![Deprecated][deprecated] | [`DocToolWindowManager`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocToolWindowManager.java) |
| [com.intellij.lang.findUsagesProvider](https://jb.gg/ipe?extensions=com.intellij.lang.findUsagesProvider) | [`FindUsagesProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) |
| [com.intellij.lang.foldingBuilder](https://jb.gg/ipe?extensions=com.intellij.lang.foldingBuilder) | [`FoldingBuilder`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) |
| [com.intellij.lang.implementationTextProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextProcessor) | [`ImplementationTextProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextProcessor.java) |
| [com.intellij.lang.implementationTextSelectioner](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextSelectioner) | [`ImplementationTextSelectioner`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) |
| [com.intellij.lang.psiDocumentation](https://jb.gg/ipe?extensions=com.intellij.lang.psiDocumentation) ![Experimental API][experimental] | [`PsiDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/psi/PsiDocumentationTargetProvider.java) |
| [com.intellij.lang.psiElementExternalizer](https://jb.gg/ipe?extensions=com.intellij.lang.psiElementExternalizer) | [`PsiElementExternalizer`](%gh-ic%/platform/lang-api/src/com/intellij/lang/PsiElementExternalizer.java) |
| [com.intellij.lang.psiStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.lang.psiStructureViewFactory) | [`PsiStructureViewFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) |
| [com.intellij.lang.sliceProvider](https://jb.gg/ipe?extensions=com.intellij.lang.sliceProvider) | [`SliceLanguageSupportProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/slicer/SliceLanguageSupportProvider.java) |
| [com.intellij.lang.structureViewExtension](https://jb.gg/ipe?extensions=com.intellij.lang.structureViewExtension) | [`StructureViewExtension`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/StructureViewExtension.java) |
| [com.intellij.lang.surroundDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.surroundDescriptor) | [`SurroundDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) |
| [com.intellij.lang.symbolDocumentation](https://jb.gg/ipe?extensions=com.intellij.lang.symbolDocumentation) ![Experimental API][experimental] | [`SymbolDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/symbol/SymbolDocumentationTargetProvider.java) |
| [com.intellij.lang.symbolSearchTarget](https://jb.gg/ipe?extensions=com.intellij.lang.symbolSearchTarget) | [`SymbolSearchTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java) |
| [com.intellij.lang.symbolTypeProvider](https://jb.gg/ipe?extensions=com.intellij.lang.symbolTypeProvider) ![Experimental API][experimental] | [`SymbolTypeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/SymbolTypeProvider.java) |
| [com.intellij.lang.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighter) | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |
| [com.intellij.lang.unwrapDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.unwrapDescriptor) | [`UnwrapDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/unwrap/UnwrapDescriptor.java) |
| [com.intellij.library.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.library.presentationProvider) | [`LibraryPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryPresentationProvider.java) |
| [com.intellij.library.type](https://jb.gg/ipe?extensions=com.intellij.library.type) | [`LibraryType`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryType.java) |
| [com.intellij.librarySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.librarySettingsProvider) | [`LibrarySettingsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/LibrarySettingsProvider.java) |
| [com.intellij.liveTemplateSubstitutor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateSubstitutor) | [`TemplateSubstitutor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/TemplateSubstitutor.java) |
| [com.intellij.longLineInspectionPolicy](https://jb.gg/ipe?extensions=com.intellij.longLineInspectionPolicy) | [`LongLineInspectionPolicy`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/longLine/LongLineInspectionPolicy.java) |
| [com.intellij.macro](https://jb.gg/ipe?extensions=com.intellij.macro) | [`Macro`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/Macro.java) |
| [com.intellij.macroFilter](https://jb.gg/ipe?extensions=com.intellij.macroFilter) | [`MacroFilter`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/MacroFilter.java) |
| [com.intellij.metaDataContributor](https://jb.gg/ipe?extensions=com.intellij.metaDataContributor) | [`MetaDataContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/meta/MetaDataContributor.java) |
| [com.intellij.methodHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.methodHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.methodNavigationOffsetProvider](https://jb.gg/ipe?extensions=com.intellij.methodNavigationOffsetProvider) | [`MethodNavigationOffsetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/navigation/MethodNavigationOffsetProvider.java) |
| [com.intellij.modelScopeItemPresenter](https://jb.gg/ipe?extensions=com.intellij.modelScopeItemPresenter) ![Non-Dynamic][non-dynamic] | [`ModelScopeItemPresenter`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/dialog/ModelScopeItemPresenter.java) |
| [com.intellij.module.workingDirectoryProvider](https://jb.gg/ipe?extensions=com.intellij.module.workingDirectoryProvider) | [`WorkingDirectoryProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/openapi/module/WorkingDirectoryProvider.java) |
| [com.intellij.moduleBuilder](https://jb.gg/ipe?extensions=com.intellij.moduleBuilder) | [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) |
| [com.intellij.moduleConfigurationEditorProvider](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurationEditorProvider) | [`ModuleConfigurationEditorProvider`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/roots/ui/configuration/ModuleConfigurationEditorProvider.java) |
| [com.intellij.moduleNameGenerator](https://jb.gg/ipe?extensions=com.intellij.moduleNameGenerator) ![Experimental API][experimental] | [`ModuleNameGenerator`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleNameGenerator.java) |
| [com.intellij.moduleRendererFactory](https://jb.gg/ipe?extensions=com.intellij.moduleRendererFactory) | [`ModuleRendererFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/ModuleRendererFactory.java) |
| [com.intellij.moduleType](https://jb.gg/ipe?extensions=com.intellij.moduleType) | [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) |
| [com.intellij.multiLangCommenter](https://jb.gg/ipe?extensions=com.intellij.multiLangCommenter) | [`MultipleLangCommentProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/templateLanguages/MultipleLangCommentProvider.java) |
| [com.intellij.multipleRunLocationsProvider](https://jb.gg/ipe?extensions=com.intellij.multipleRunLocationsProvider) | [`MultipleRunLocationsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/MultipleRunLocationsProvider.kt) |
| [com.intellij.navbar](https://jb.gg/ipe?extensions=com.intellij.navbar) | [`NavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelExtension.java) |
| [com.intellij.navbar.item.provider](https://jb.gg/ipe?extensions=com.intellij.navbar.item.provider) | [`NavBarItemProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navbar/NavBarItemProvider.kt) |
| [com.intellij.navbarLeftSide](https://jb.gg/ipe?extensions=com.intellij.navbarLeftSide) ![Internal API][internal] | [`NavBarLeftSideExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarLeftSideExtension.java) |
| [com.intellij.optionsApplicabilityFilter](https://jb.gg/ipe?extensions=com.intellij.optionsApplicabilityFilter) | [`OptionsApplicabilityFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/OptionsApplicabilityFilter.java) |
| [com.intellij.outOfSourcesChecker](https://jb.gg/ipe?extensions=com.intellij.outOfSourcesChecker) | [`OutOfSourcesChecker`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/projectRoots/OutOfSourcesChecker.java) |
| [com.intellij.packageDependencies.visitor](https://jb.gg/ipe?extensions=com.intellij.packageDependencies.visitor) | [`DependencyVisitorFactory`](%gh-ic%/platform/analysis-impl/src/com/intellij/packageDependencies/DependencyVisitorFactory.java) |
| [com.intellij.pathReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.pathReferenceProvider) | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.patternDialectProvider](https://jb.gg/ipe?extensions=com.intellij.patternDialectProvider) ![Non-Dynamic][non-dynamic] | [`PatternDialectProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/packageDependencies/ui/PatternDialectProvider.java) |
| [com.intellij.patterns.patternClass](https://jb.gg/ipe?extensions=com.intellij.patterns.patternClass) | `Object` |
| [com.intellij.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.presentationProvider) | [`PresentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/PresentationProvider.java) |
| [com.intellij.previewHintProvider](https://jb.gg/ipe?extensions=com.intellij.previewHintProvider) ![Deprecated][deprecated] | [`PreviewHintProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/preview/PreviewHintProvider.java) |
| [com.intellij.printHandler](https://jb.gg/ipe?extensions=com.intellij.printHandler) | [`PrintActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/PrintActionHandler.java) |
| [com.intellij.printOption](https://jb.gg/ipe?extensions=com.intellij.printOption) | [`PrintOption`](%gh-ic%/platform/lang-impl/src/com/intellij/codeEditor/printing/PrintOption.java) |
| [com.intellij.problemFileHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.problemFileHighlightFilter) ![Project-Level][project-level] | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.problemHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.problemHighlightFilter) | [`ProblemHighlightFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ProblemHighlightFilter.java) |
| [com.intellij.problemsViewPanelProvider](https://jb.gg/ipe?extensions=com.intellij.problemsViewPanelProvider) ![Project-Level][project-level] | [`ProblemsViewPanelProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/problemsView/toolWindow/ProblemsViewPanelProvider.kt) |
| [com.intellij.programRunner](https://jb.gg/ipe?extensions=com.intellij.programRunner) | [`ProgramRunner`](%gh-ic%/platform/execution/src/com/intellij/execution/runners/ProgramRunner.java) |
| [com.intellij.project.converterProvider](https://jb.gg/ipe?extensions=com.intellij.project.converterProvider) | [`ConverterProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/conversion/ConverterProvider.java) |
| [com.intellij.projectFacetListener](https://jb.gg/ipe?extensions=com.intellij.projectFacetListener) | [`ProjectFacetListener`](%gh-ic%/platform/lang-api/src/com/intellij/facet/ProjectFacetListener.java) |
| [com.intellij.projectSdkSetupValidator](https://jb.gg/ipe?extensions=com.intellij.projectSdkSetupValidator) | [`ProjectSdkSetupValidator`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java) |
| [com.intellij.projectStructure.sourceRootEditHandler](https://jb.gg/ipe?extensions=com.intellij.projectStructure.sourceRootEditHandler) | [`ModuleSourceRootEditHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/ModuleSourceRootEditHandler.java) |
| [com.intellij.projectTaskRunner](https://jb.gg/ipe?extensions=com.intellij.projectTaskRunner) | [`ProjectTaskRunner`](%gh-ic%/platform/lang-api/src/com/intellij/task/ProjectTaskRunner.java) |
| [com.intellij.projectTemplateFileProcessor](https://jb.gg/ipe?extensions=com.intellij.projectTemplateFileProcessor) | [`ProjectTemplateFileProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/projectWizard/ProjectTemplateFileProcessor.java) |
| [com.intellij.projectTemplateParameterFactory](https://jb.gg/ipe?extensions=com.intellij.projectTemplateParameterFactory) | [`ProjectTemplateParameterFactory`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ProjectTemplateParameterFactory.java) |
| [com.intellij.projectView.externalLibraries.workspaceModelNodesProvider](https://jb.gg/ipe?extensions=com.intellij.projectView.externalLibraries.workspaceModelNodesProvider) ![Experimental API][experimental] | [`ExternalLibrariesWorkspaceModelNodesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/nodes/ExternalLibrariesWorkspaceModelNodesProvider.java) |
| [com.intellij.projectViewNestingRulesProvider](https://jb.gg/ipe?extensions=com.intellij.projectViewNestingRulesProvider) | [`ProjectViewNestingRulesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/projectView/ProjectViewNestingRulesProvider.java) |
| [com.intellij.projectViewNodeDecorator](https://jb.gg/ipe?extensions=com.intellij.projectViewNodeDecorator) ![Project-Level][project-level] | [`ProjectViewNodeDecorator`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/ProjectViewNodeDecorator.java) |
| [com.intellij.projectViewPane](https://jb.gg/ipe?extensions=com.intellij.projectViewPane) ![Project-Level][project-level] | [`AbstractProjectViewPane`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/AbstractProjectViewPane.java) |
| [com.intellij.projectViewPaneSelectionHelper](https://jb.gg/ipe?extensions=com.intellij.projectViewPaneSelectionHelper) | [`ProjectViewPaneSelectionHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewPaneSelectionHelper.java) |
| [com.intellij.properties.files.provider](https://jb.gg/ipe?extensions=com.intellij.properties.files.provider) | [`PropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/properties/provider/PropertiesProvider.java) |
| [com.intellij.psi.declarationProvider](https://jb.gg/ipe?extensions=com.intellij.psi.declarationProvider) | [`PsiSymbolDeclarationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java) |
| [com.intellij.psi.referenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.referenceProvider) ![Non-Dynamic][non-dynamic] | [`PsiReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| [com.intellij.readWriteAccessDetector](https://jb.gg/ipe?extensions=com.intellij.readWriteAccessDetector) | [`ReadWriteAccessDetector`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/highlighting/ReadWriteAccessDetector.java) |
| [com.intellij.readerModeMatcher](https://jb.gg/ipe?extensions=com.intellij.readerModeMatcher) | [`ReaderModeMatcher`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeMatcher.kt) |
| [com.intellij.readerModeProvider](https://jb.gg/ipe?extensions=com.intellij.readerModeProvider) | [`ReaderModeProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeProvider.kt) |
| [com.intellij.refGraphAnnotator](https://jb.gg/ipe?extensions=com.intellij.refGraphAnnotator) | [`RefGraphAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/RefGraphAnnotator.java) |
| [com.intellij.referenceImporter](https://jb.gg/ipe?extensions=com.intellij.referenceImporter) | [`ReferenceImporter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/ReferenceImporter.java) |
| [com.intellij.referenceInjector](https://jb.gg/ipe?extensions=com.intellij.referenceInjector) | [`ReferenceInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) |
| [com.intellij.referenceProviderType](https://jb.gg/ipe?extensions=com.intellij.referenceProviderType) | [`PsiReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| [com.intellij.retypeFileAssistant](https://jb.gg/ipe?extensions=com.intellij.retypeFileAssistant) | [`RetypeFileAssistant`](%gh-ic%/platform/lang-impl/src/com/intellij/internal/retype/RetypeFileAction.kt) |
| [com.intellij.roots.watchedRootsProvider](https://jb.gg/ipe?extensions=com.intellij.roots.watchedRootsProvider) | [`WatchedRootsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/WatchedRootsProvider.java) |
| [com.intellij.runAnything.commandCustomizer](https://jb.gg/ipe?extensions=com.intellij.runAnything.commandCustomizer) | [`RunAnythingCommandCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/commands/RunAnythingCommandCustomizer.java) |
| [com.intellij.runAnything.commandHandler](https://jb.gg/ipe?extensions=com.intellij.runAnything.commandHandler) | [`RunAnythingCommandHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/handlers/RunAnythingCommandHandler.java) |
| [com.intellij.runAnything.executionProvider](https://jb.gg/ipe?extensions=com.intellij.runAnything.executionProvider) | [`RunAnythingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingProvider.java) |
| [com.intellij.runAnything.helpGroup](https://jb.gg/ipe?extensions=com.intellij.runAnything.helpGroup) | [`RunAnythingHelpGroup`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/groups/RunAnythingHelpGroup.java) |
| [com.intellij.runConfigurationBeforeRunProviderDelegate](https://jb.gg/ipe?extensions=com.intellij.runConfigurationBeforeRunProviderDelegate) | [`RunConfigurationBeforeRunProviderDelegate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/impl/RunConfigurationBeforeRunProviderDelegate.java) |
| [com.intellij.runConfigurationProducer](https://jb.gg/ipe?extensions=com.intellij.runConfigurationProducer) | [`RunConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/RunConfigurationProducer.java) |
| [com.intellij.runConfigurationTargetEnvironmentAdjuster](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTargetEnvironmentAdjuster) ![Internal API][internal] | [`RunConfigurationTargetEnvironmentAdjuster`](%gh-ic%/platform/execution/src/com/intellij/execution/target/RunConfigurationTargetEnvironmentAdjuster.kt) |
| [com.intellij.runConfigurationTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTemplateProvider) ![Project-Level][project-level] | [`RunConfigurationTemplateProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/impl/RunManagerImpl.kt) |
| [com.intellij.runConfigurationsSettings](https://jb.gg/ipe?extensions=com.intellij.runConfigurationsSettings) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`RunConfigurationsSettings`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfigurationsSettings.java) |
| [com.intellij.runDashboardCustomizer](https://jb.gg/ipe?extensions=com.intellij.runDashboardCustomizer) | [`RunDashboardCustomizer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardCustomizer.java) |
| [com.intellij.runDashboardDefaultTypesProvider](https://jb.gg/ipe?extensions=com.intellij.runDashboardDefaultTypesProvider) | [`RunDashboardDefaultTypesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardDefaultTypesProvider.java) |
| [com.intellij.runDashboardGroupingRule](https://jb.gg/ipe?extensions=com.intellij.runDashboardGroupingRule) | [`RunDashboardGroupingRule`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardGroupingRule.java) |
| [com.intellij.runLineMarkerContributor](https://jb.gg/ipe?extensions=com.intellij.runLineMarkerContributor) | [`RunLineMarkerContributor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/lineMarker/RunLineMarkerContributor.java) |
| [com.intellij.runToolbarProcess](https://jb.gg/ipe?extensions=com.intellij.runToolbarProcess) | [`RunToolbarProcess`](%gh-ic%/platform/execution/src/com/intellij/execution/runToolbar/RunToolbarProcess.kt) |
| [com.intellij.runningApplicationUpdaterProvider](https://jb.gg/ipe?extensions=com.intellij.runningApplicationUpdaterProvider) | [`RunningApplicationUpdaterProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/update/RunningApplicationUpdaterProvider.java) |
| [com.intellij.saveFileAsTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.saveFileAsTemplateHandler) | [`SaveFileAsTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SaveFileAsTemplateHandler.java) |
| [com.intellij.scopeDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.scopeDescriptorProvider) | [`ScopeDescriptorProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeDescriptorProvider.java) |
| [com.intellij.scopeParserExtension](https://jb.gg/ipe?extensions=com.intellij.scopeParserExtension) | [`PackageSetParserExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/search/scope/packageSet/PackageSetParserExtension.java) |
| [com.intellij.scratch.creationHelper](https://jb.gg/ipe?extensions=com.intellij.scratch.creationHelper) | [`ScratchFileCreationHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/scratch/ScratchFileCreationHelper.java) |
| [com.intellij.scratch.rootType](https://jb.gg/ipe?extensions=com.intellij.scratch.rootType) | [`RootType`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/scratch/RootType.java) |
| [com.intellij.sdkDownload](https://jb.gg/ipe?extensions=com.intellij.sdkDownload) | [`SdkDownload`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/projectRoot/SdkDownload.java) |
| [com.intellij.sdkFinder](https://jb.gg/ipe?extensions=com.intellij.sdkFinder) | [`SdkFinder`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/SdkFinder.java) |
| [com.intellij.sdkType](https://jb.gg/ipe?extensions=com.intellij.sdkType) | [`SdkType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/projectRoots/SdkType.java) |
| [com.intellij.searchEverywhereClassifier](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereClassifier) | [`SearchEverywhereClassifier`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SearchEverywhereClassifier.java) |
| [com.intellij.searchEverywhereContributor](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereContributor) | [`SearchEverywhereContributorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributorFactory.java) |
| [com.intellij.searchEverywhereMlService](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMlService) ![Internal API][internal] | [`SearchEverywhereMlService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| [com.intellij.searchEverywhereResultsEqualityProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereResultsEqualityProvider) | [`SEResultsEqualityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEResultsEqualityProvider.kt) |
| [com.intellij.searchScopesProvider](https://jb.gg/ipe?extensions=com.intellij.searchScopesProvider) | [`SearchScopeProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/search/SearchScopeProvider.java) |
| [com.intellij.searcher](https://jb.gg/ipe?extensions=com.intellij.searcher) | [`Searcher`](%gh-ic%/platform/indexing-api/src/com/intellij/model/search/Searcher.kt) |
| [com.intellij.semContributor](https://jb.gg/ipe?extensions=com.intellij.semContributor) | [`SemContributor`](%gh-ic%/platform/lang-api/src/com/intellij/semantic/SemContributor.java) |
| [com.intellij.serviceViewContributor](https://jb.gg/ipe?extensions=com.intellij.serviceViewContributor) | [`ServiceViewContributor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceViewContributor.java) |
| [com.intellij.silentChangeVetoer](https://jb.gg/ipe?extensions=com.intellij.silentChangeVetoer) ![Internal API][internal] | [`SilentChangeVetoer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SilentChangeVetoer.kt) |
| [com.intellij.stacktrace.fold](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold) | `n/a` |
| [com.intellij.stacktrace.fold.line.modifier](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold.line.modifier) ![Experimental API][experimental] | [`ConsoleLineModifier`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleLineModifier.java) |
| [com.intellij.statistician](https://jb.gg/ipe?extensions=com.intellij.statistician) | [`Statistician`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/statistics/Statistician.java) |
| [com.intellij.stepsBeforeRunProvider](https://jb.gg/ipe?extensions=com.intellij.stepsBeforeRunProvider) ![Project-Level][project-level] | [`BeforeRunTaskProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/BeforeRunTaskProvider.java) |
| [com.intellij.structureViewBuilder](https://jb.gg/ipe?extensions=com.intellij.structureViewBuilder) | [`StructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewBuilder.java) |
| [com.intellij.symbolDeclarationPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.symbolDeclarationPresentationProvider) | [`SymbolDeclarationPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/presentation/SymbolDeclarationPresentationProvider.java) |
| [com.intellij.symbolNavigation](https://jb.gg/ipe?extensions=com.intellij.symbolNavigation) ![Experimental API][experimental] | [`SymbolNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java) |
| [com.intellij.targetElementEvaluator](https://jb.gg/ipe?extensions=com.intellij.targetElementEvaluator) | [`TargetElementEvaluator`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/TargetElementEvaluator.java) |
| [com.intellij.targetElementUtilExtender](https://jb.gg/ipe?extensions=com.intellij.targetElementUtilExtender) | [`TargetElementUtilExtender`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/TargetElementUtilExtender.java) |
| [com.intellij.templateCompletionProcessor](https://jb.gg/ipe?extensions=com.intellij.templateCompletionProcessor) | [`TemplateCompletionProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/macro/TemplateCompletionProcessor.java) |
| [com.intellij.testActionProvider](https://jb.gg/ipe?extensions=com.intellij.testActionProvider) | [`ToggleModelActionProvider`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/ToggleModelActionProvider.java) |
| [com.intellij.testCreator](https://jb.gg/ipe?extensions=com.intellij.testCreator) | [`TestCreator`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestCreator.java) |
| [com.intellij.testFinder](https://jb.gg/ipe?extensions=com.intellij.testFinder) | [`TestFinder`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestFinder.java) |
| [com.intellij.testSrcLocator](https://jb.gg/ipe?extensions=com.intellij.testSrcLocator) ![Deprecated][deprecated] | [`TestLocationProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/testIntegration/TestLocationProvider.java) |
| [com.intellij.todoExtraPlaces](https://jb.gg/ipe?extensions=com.intellij.todoExtraPlaces) | [`ExtraPlaceChecker`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/todo/TodoIndexers.java) |
| [com.intellij.todoIndexer](https://jb.gg/ipe?extensions=com.intellij.todoIndexer) | [`DataIndexer`](%gh-ic%/platform/util/src/com/intellij/util/indexing/DataIndexer.java) |
| [com.intellij.toolsCustomizer](https://jb.gg/ipe?extensions=com.intellij.toolsCustomizer) | [`ToolsCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsCustomizer.java) |
| [com.intellij.toolsProvider](https://jb.gg/ipe?extensions=com.intellij.toolsProvider) | [`ToolsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsProvider.java) |
| [com.intellij.trafficLightRendererContributor](https://jb.gg/ipe?extensions=com.intellij.trafficLightRendererContributor) | [`TrafficLightRendererContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/TrafficLightRendererContributor.java) |
| [com.intellij.treeGenerator](https://jb.gg/ipe?extensions=com.intellij.treeGenerator) | [`TreeGenerator`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeGenerator.java) |
| [com.intellij.treeStructureProvider](https://jb.gg/ipe?extensions=com.intellij.treeStructureProvider) ![Project-Level][project-level] | [`TreeStructureProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) |
| [com.intellij.typeDeclarationProvider](https://jb.gg/ipe?extensions=com.intellij.typeDeclarationProvider) | [`TypeDeclarationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/actions/TypeDeclarationProvider.java) |
| [com.intellij.typeHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.typeHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.typeIcon](https://jb.gg/ipe?extensions=com.intellij.typeIcon) | `Object` |
| [com.intellij.typeName](https://jb.gg/ipe?extensions=com.intellij.typeName) | `Object` |
| [com.intellij.uiDebuggerExtension](https://jb.gg/ipe?extensions=com.intellij.uiDebuggerExtension) ![Non-Dynamic][non-dynamic] | [`UiDebuggerExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ui/debugger/UiDebuggerExtension.java) |
| [com.intellij.usageContextPanelProvider](https://jb.gg/ipe?extensions=com.intellij.usageContextPanelProvider) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageContextPanel.java) |
| [com.intellij.usageFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.usageFeaturesProvider) ![Experimental API][experimental] | [`UsageSimilarityFeaturesProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/similarity/features/UsageSimilarityFeaturesProvider.java) |
| [com.intellij.usageFilteringRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageFilteringRuleProvider) | [`UsageFilteringRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleProvider.java) |
| [com.intellij.usageGroupingRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageGroupingRuleProvider) | [`UsageGroupingRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageGroupingRuleProvider.java) |
| [com.intellij.usageTargetProvider](https://jb.gg/ipe?extensions=com.intellij.usageTargetProvider) | [`UsageTargetProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageTargetProvider.java) |
| [com.intellij.usageToPsiElementProvider](https://jb.gg/ipe?extensions=com.intellij.usageToPsiElementProvider) | [`UsageToPsiElementProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageToPsiElementProvider.java) |
| [com.intellij.usageTypeProvider](https://jb.gg/ipe?extensions=com.intellij.usageTypeProvider) | [`UsageTypeProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/rules/UsageTypeProvider.java) |
| [com.intellij.usageViewElementsListener](https://jb.gg/ipe?extensions=com.intellij.usageViewElementsListener) | [`UsageViewElementsListener`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewElementsListener.java) |
| [com.intellij.usageViewFactory](https://jb.gg/ipe?extensions=com.intellij.usageViewFactory) | [`UsageViewFactory`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewFactory.java) |

### LangExtensions.xml

[`LangExtensions.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dependenciesToolWindow.tabProvider](https://jb.gg/ipe?extensions=com.intellij.dependenciesToolWindow.tabProvider) | [`DependenciesToolWindowTabProvider`](%gh-ic%/platform/dependencies-toolwindow/src/com/intellij/dependencytoolwindow/DependenciesToolWindowTabProvider.kt) |

### PlatformExtensionPoints.xml

[`PlatformExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ApplicationLoadListener](https://jb.gg/ipe?extensions=com.intellij.ApplicationLoadListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ApplicationLoadListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ApplicationLoadListener.java) |
| [com.intellij.aboutPopupDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.aboutPopupDescriptionProvider) | [`AboutPopupDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/AboutPopupDescriptionProvider.kt) |
| [com.intellij.achromatopsiaSupport](https://jb.gg/ipe?extensions=com.intellij.achromatopsiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.actionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.actionConfigurationCustomizer) ![Non-Dynamic][non-dynamic] | [`ActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionConfigurationCustomizer.java) |
| [com.intellij.actionFromOptionDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.actionFromOptionDescriptorProvider) | [`ActionFromOptionDescriptorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/ActionFromOptionDescriptorProvider.java) |
| [com.intellij.actionOnSave](https://jb.gg/ipe?extensions=com.intellij.actionOnSave) | [`ActionOnSave`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/impl/ActionsOnSaveFileDocumentManagerListener.java) |
| [com.intellij.actionOnSaveInfoProvider](https://jb.gg/ipe?extensions=com.intellij.actionOnSaveInfoProvider) | [`ActionOnSaveInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/ActionOnSaveInfoProvider.java) |
| [com.intellij.actionPromoter](https://jb.gg/ipe?extensions=com.intellij.actionPromoter) | [`ActionPromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/ActionPromoter.java) |
| [com.intellij.additionalTextAttributes](https://jb.gg/ipe?extensions=com.intellij.additionalTextAttributes) | `n/a` |
| [com.intellij.advancedSetting](https://jb.gg/ipe?extensions=com.intellij.advancedSetting) | `n/a` |
| [com.intellij.appStarter](https://jb.gg/ipe?extensions=com.intellij.appStarter) | [`ApplicationStarter`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationStarter.kt) |
| [com.intellij.applicationConfigurable](https://jb.gg/ipe?extensions=com.intellij.applicationConfigurable) | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.baseDirectorySuggester](https://jb.gg/ipe?extensions=com.intellij.baseDirectorySuggester) | [`BaseDirectorySuggester`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/project/BaseDirectorySuggester.kt) |
| [com.intellij.beforeRunStartupTasks](https://jb.gg/ipe?extensions=com.intellij.beforeRunStartupTasks) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`BeforeRunStartupTasks`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/startup/BeforeRunStartupTasks.kt) |
| [com.intellij.breadcrumbsPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsPresentationProvider) | [`BreadcrumbsPresentationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/xml/breadcrumbs/BreadcrumbsPresentationProvider.java) |
| [com.intellij.bundledColorScheme](https://jb.gg/ipe?extensions=com.intellij.bundledColorScheme) | `n/a` |
| [com.intellij.bundledInspectionProfile](https://jb.gg/ipe?extensions=com.intellij.bundledInspectionProfile) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.bundledKeymap](https://jb.gg/ipe?extensions=com.intellij.bundledKeymap) | `n/a` |
| [com.intellij.bundledQuickListsProvider](https://jb.gg/ipe?extensions=com.intellij.bundledQuickListsProvider) | [`BundledQuickListsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/BundledQuickListsProvider.java) |
| [com.intellij.cachedValuesFactory](https://jb.gg/ipe?extensions=com.intellij.cachedValuesFactory) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`CachedValuesFactory`](%gh-ic%/platform/core-impl/src/com/intellij/util/CachedValuesFactory.java) |
| [com.intellij.cachesInvalidator](https://jb.gg/ipe?extensions=com.intellij.cachesInvalidator) | [`CachesInvalidator`](%gh-ic%/platform/ide-core/src/com/intellij/ide/caches/CachesInvalidator.java) |
| [com.intellij.classpathStorageProvider](https://jb.gg/ipe?extensions=com.intellij.classpathStorageProvider) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ClasspathStorageProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/impl/storage/ClasspathStorageProvider.java) |
| [com.intellij.codeInsight.folding.collapseBlockHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.folding.collapseBlockHandler) | [`CollapseBlockHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/folding/CollapseBlockHandler.java) |
| [com.intellij.codeInsight.linkHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.linkHandler) | [`TooltipLinkHandler`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/highlighting/TooltipLinkHandler.java) |
| [com.intellij.codeInsight.template.postfixTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.template.postfixTemplateProvider) | [`PostfixTemplateProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplateProvider.java) |
| [com.intellij.colorAndFontOptionsImportHandler](https://jb.gg/ipe?extensions=com.intellij.colorAndFontOptionsImportHandler) ![Non-Dynamic][non-dynamic] | [`ImportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ImportHandler.java) |
| [com.intellij.colorPickerListenerFactory](https://jb.gg/ipe?extensions=com.intellij.colorPickerListenerFactory) | [`ColorPickerListenerFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ColorPickerListenerFactory.java) |
| [com.intellij.config.codeVisionGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.codeVisionGroupSettingProvider) | [`CodeVisionGroupSettingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/settings/CodeVisionGroupSettingProvider.kt) |
| [com.intellij.config.inlayGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlayGroupSettingProvider) | [`InlayGroupSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayGroupSettingProvider.kt) |
| [com.intellij.config.inlaySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlaySettingsProvider) | [`InlaySettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlaySettingsProvider.kt) |
| [com.intellij.configurablesPatcher](https://jb.gg/ipe?extensions=com.intellij.configurablesPatcher) ![Experimental API][experimental] ![Internal API][internal] | [`ConfigurablesPatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/ConfigurablesPatcher.java) |
| [com.intellij.contentTabActionProvider](https://jb.gg/ipe?extensions=com.intellij.contentTabActionProvider) ![Non-Dynamic][non-dynamic] | [`ContentTabActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/content/tabActions/ContentTabAction.kt) |
| [com.intellij.credentialStore](https://jb.gg/ipe?extensions=com.intellij.credentialStore) ![Non-Dynamic][non-dynamic] | [`CredentialStoreFactory`](%gh-ic%/platform/credential-store/src/CredentialStoreFactory.java) |
| [com.intellij.customFileDropHandler](https://jb.gg/ipe?extensions=com.intellij.customFileDropHandler) ![Project-Level][project-level] | [`CustomFileDropHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/CustomFileDropHandler.java) |
| [com.intellij.customPluginRepoContributor](https://jb.gg/ipe?extensions=com.intellij.customPluginRepoContributor) | [`CustomPluginRepoContributor`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/CustomPluginRepoContributor.java) |
| [com.intellij.customizableActionGroupProvider](https://jb.gg/ipe?extensions=com.intellij.customizableActionGroupProvider) | [`CustomizableActionGroupProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomizableActionGroupProvider.java) |
| [com.intellij.cutElementMarker](https://jb.gg/ipe?extensions=com.intellij.cutElementMarker) | [`CutElementMarker`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/CutElementMarker.java) |
| [com.intellij.dataValidators](https://jb.gg/ipe?extensions=com.intellij.dataValidators) | [`DataValidators`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/DataValidators.java) |
| [com.intellij.dateTimeFormatter](https://jb.gg/ipe?extensions=com.intellij.dateTimeFormatter) | `n/a` |
| [com.intellij.dependencyCollector](https://jb.gg/ipe?extensions=com.intellij.dependencyCollector) | [`DependencyCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/DependencyCollector.kt) |
| [com.intellij.dependencySupport](https://jb.gg/ipe?extensions=com.intellij.dependencySupport) | `n/a` |
| [com.intellij.deuteranopiaSupport](https://jb.gg/ipe?extensions=com.intellij.deuteranopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.diff.DiffExtension](https://jb.gg/ipe?extensions=com.intellij.diff.DiffExtension) | [`DiffExtension`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffExtension.java) |
| [com.intellij.diff.DiffTool](https://jb.gg/ipe?extensions=com.intellij.diff.DiffTool) | [`DiffTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffTool.java) |
| [com.intellij.diff.actions.ShowDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.editor.diffRequestProcessorEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.diff.editor.diffRequestProcessorEditorCustomizer) | [`DiffRequestProcessorEditorCustomizer`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/editor/DiffRequestProcessorEditorCustomizer.kt) |
| [com.intellij.diff.impl.DiffToolSubstitutor](https://jb.gg/ipe?extensions=com.intellij.diff.impl.DiffToolSubstitutor) | [`DiffToolSubstitutor`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/impl/DiffToolSubstitutor.java) |
| [com.intellij.diff.lang.DiffIgnoredRangeProvider](https://jb.gg/ipe?extensions=com.intellij.diff.lang.DiffIgnoredRangeProvider) | [`DiffIgnoredRangeProvider`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/lang/DiffIgnoredRangeProvider.java) |
| [com.intellij.diff.merge.MergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.MergeTool) | [`MergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/MergeTool.java) |
| [com.intellij.diff.merge.external.AutomaticExternalMergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.external.AutomaticExternalMergeTool) | [`AutomaticExternalMergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/external/AutomaticExternalMergeTool.java) |
| [com.intellij.diff.tools.combined.diffBlockFactory](https://jb.gg/ipe?extensions=com.intellij.diff.tools.combined.diffBlockFactory) | [`CombinedDiffBlockFactory`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/tools/combined/CombinedDiffBlocks.kt) |
| [com.intellij.directoryProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectConfigurator) | [`DirectoryProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectConfigurator.kt) |
| [com.intellij.directoryProjectGenerator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectGenerator) | [`DirectoryProjectGenerator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectGenerator.java) |
| [com.intellij.documentationActionProvider](https://jb.gg/ipe?extensions=com.intellij.documentationActionProvider) | [`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) |
| [com.intellij.dynamicActionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.dynamicActionConfigurationCustomizer) | [`DynamicActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/DynamicActionConfigurationCustomizer.java) |
| [com.intellij.editor.injectedFileChangesHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.editor.injectedFileChangesHandlerProvider) | [`InjectedFileChangesHandlerProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/injected/editor/InjectedFileChangesHandlerProvider.java) |
| [com.intellij.editor.linePainter](https://jb.gg/ipe?extensions=com.intellij.editor.linePainter) | [`EditorLinePainter`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/EditorLinePainter.java) |
| [com.intellij.editorActionHandler](https://jb.gg/ipe?extensions=com.intellij.editorActionHandler) | [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) |
| [com.intellij.editorFactoryMouseListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseListener) | [`EditorMouseListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseListener.java) |
| [com.intellij.editorFactoryMouseMotionListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseMotionListener) | [`EditorMouseMotionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseMotionListener.java) |
| [com.intellij.editorFileSwapper](https://jb.gg/ipe?extensions=com.intellij.editorFileSwapper) | [`EditorFileSwapper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorFileSwapper.java) |
| [com.intellij.editorFloatingToolbarProvider](https://jb.gg/ipe?extensions=com.intellij.editorFloatingToolbarProvider) | [`FloatingToolbarProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/toolbar/floating/FloatingToolbarProvider.kt) |
| [com.intellij.editorHighlighterProvider](https://jb.gg/ipe?extensions=com.intellij.editorHighlighterProvider) | [`EditorHighlighterProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/EditorHighlighterProvider.java) |
| [com.intellij.editorNavigation](https://jb.gg/ipe?extensions=com.intellij.editorNavigation) | [`EditorNavigationDelegate`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/EditorNavigationDelegate.java) |
| [com.intellij.editorNotificationProvider](https://jb.gg/ipe?extensions=com.intellij.editorNotificationProvider) ![Project-Level][project-level] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| [com.intellij.editorTabColorProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabColorProvider) | [`EditorTabColorProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java) |
| [com.intellij.editorTabTitleProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabTitleProvider) | [`EditorTabTitleProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.java) |
| [com.intellij.editorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.editorTypedHandler) ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.errorHandler](https://jb.gg/ipe?extensions=com.intellij.errorHandler) | [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) |
| [com.intellij.eventLogCategory](https://jb.gg/ipe?extensions=com.intellij.eventLogCategory) | [`EventLogCategory`](%gh-ic%/platform/ide-core/src/com/intellij/notification/EventLogCategory.java) |
| [com.intellij.execution.syntheticConfigurationTypeProvider](https://jb.gg/ipe?extensions=com.intellij.execution.syntheticConfigurationTypeProvider) ![Experimental API][experimental] | [`SyntheticConfigurationTypeProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/SyntheticConfigurationTypeProvider.java) |
| [com.intellij.executionActionSuppressor](https://jb.gg/ipe?extensions=com.intellij.executionActionSuppressor) ![Experimental API][experimental] ![Internal API][internal] | [`ExecutionActionSuppressor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ExecutionActionSuppressor.java) |
| [com.intellij.experimentalFeature](https://jb.gg/ipe?extensions=com.intellij.experimentalFeature) | `n/a` |
| [com.intellij.exportable](https://jb.gg/ipe?extensions=com.intellij.exportable) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.externalComponentSource](https://jb.gg/ipe?extensions=com.intellij.externalComponentSource) | [`ExternalComponentSource`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/externalComponents/ExternalComponentSource.java) |
| [com.intellij.featureStatisticsBundle](https://jb.gg/ipe?extensions=com.intellij.featureStatisticsBundle) | `n/a` |
| [com.intellij.feedbackDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.feedbackDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`FeedbackDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/FeedbackDescriptionProvider.kt) |
| [com.intellij.fileBreadcrumbsCollector](https://jb.gg/ipe?extensions=com.intellij.fileBreadcrumbsCollector) ![Project-Level][project-level] | [`FileBreadcrumbsCollector`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/breadcrumbs/FileBreadcrumbsCollector.java) |
| [com.intellij.fileDocumentManagerListener](https://jb.gg/ipe?extensions=com.intellij.fileDocumentManagerListener) | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [com.intellij.fileDocumentSynchronizationVetoer](https://jb.gg/ipe?extensions=com.intellij.fileDocumentSynchronizationVetoer) | [`FileDocumentSynchronizationVetoer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentSynchronizationVetoer.java) |
| [com.intellij.fileEditorProvider](https://jb.gg/ipe?extensions=com.intellij.fileEditorProvider) | [`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java) |
| [com.intellij.fileEditorProviderSuppressor](https://jb.gg/ipe?extensions=com.intellij.fileEditorProviderSuppressor) ![Internal API][internal] | [`FileEditorProviderSuppressor`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/FileEditorProviderSuppressor.java) |
| [com.intellij.fileEncodingProvider](https://jb.gg/ipe?extensions=com.intellij.fileEncodingProvider) | [`FileEncodingProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/FileEncodingProvider.java) |
| [com.intellij.fileIconPatcher](https://jb.gg/ipe?extensions=com.intellij.fileIconPatcher) | [`FileIconPatcher`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconPatcher.java) |
| [com.intellij.fileIconProvider](https://jb.gg/ipe?extensions=com.intellij.fileIconProvider) | [`FileIconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconProvider.java) |
| [com.intellij.fileType](https://jb.gg/ipe?extensions=com.intellij.fileType) | [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) |
| [com.intellij.fileTypeFactory](https://jb.gg/ipe?extensions=com.intellij.fileTypeFactory) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`FileTypeFactory`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java) |
| [com.intellij.fileTypeOverrider](https://jb.gg/ipe?extensions=com.intellij.fileTypeOverrider) ![Experimental API][experimental] | [`FileTypeOverrider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileTypes/impl/FileTypeOverrider.java) |
| [com.intellij.fileTypeRegistrar](https://jb.gg/ipe?extensions=com.intellij.fileTypeRegistrar) ![Non-Dynamic][non-dynamic] | [`FileTypeRegistrar`](%gh-ic%/platform/ide-core/src/com/intellij/ide/highlighter/FileTypeRegistrar.java) |
| [com.intellij.fileTypeUsageSchemaDescriptor](https://jb.gg/ipe?extensions=com.intellij.fileTypeUsageSchemaDescriptor) | [`FileTypeUsageSchemaDescriptor`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/fileTypes/FileTypeUsageSchemaDescriptor.java) |
| [com.intellij.fragments.dsl.builder.extender](https://jb.gg/ipe?extensions=com.intellij.fragments.dsl.builder.extender) ![Experimental API][experimental] ![Internal API][internal] | [`FragmentsDslBuilderExtender`](%gh-ic%/platform/platform-api/src/com/intellij/execution/ui/utils/FragmentsDslBuilder.kt) |
| [com.intellij.generalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalOptionsProvider) ![Non-Dynamic][non-dynamic] | [`SearchableConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/SearchableConfigurable.java) |
| [com.intellij.generalTroubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.generalTroubleInfoCollector) | [`GeneralTroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/GeneralTroubleInfoCollector.java) |
| [com.intellij.getDataRule](https://jb.gg/ipe?extensions=com.intellij.getDataRule) | [`GetDataRule`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/dataRules/GetDataRule.java) |
| [com.intellij.gitRepositoryInitializer](https://jb.gg/ipe?extensions=com.intellij.gitRepositoryInitializer) | [`GitRepositoryInitializer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitRepositoryInitializer.java) |
| [com.intellij.gitSilentFileAdder](https://jb.gg/ipe?extensions=com.intellij.gitSilentFileAdder) ![Internal API][internal] ![Project-Level][project-level] | [`GitSilentFileAdderProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitSilentFileAdderProvider.java) |
| [com.intellij.groupConfigurable](https://jb.gg/ipe?extensions=com.intellij.groupConfigurable) | `n/a` |
| [com.intellij.gutterMarkPreprocessor](https://jb.gg/ipe?extensions=com.intellij.gutterMarkPreprocessor) ![Non-Dynamic][non-dynamic] | [`GutterMarkPreprocessor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/GutterMarkPreprocessor.java) |
| [com.intellij.handleTypeFactory](https://jb.gg/ipe?extensions=com.intellij.handleTypeFactory) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`HandleTypeFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/readOnlyHandler/HandleTypeFactory.java) |
| [com.intellij.http.fileEditorActionProvider](https://jb.gg/ipe?extensions=com.intellij.http.fileEditorActionProvider) ![Non-Dynamic][non-dynamic] | [`RemoteFileEditorActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/RemoteFileEditorActionProvider.java) |
| [com.intellij.http.localFileFinder](https://jb.gg/ipe?extensions=com.intellij.http.localFileFinder) | [`LocalFileFinder`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/LocalFileFinder.java) |
| [com.intellij.iconDescriptionBundle](https://jb.gg/ipe?extensions=com.intellij.iconDescriptionBundle) | `n/a` |
| [com.intellij.iconMapper](https://jb.gg/ipe?extensions=com.intellij.iconMapper) | `n/a` |
| [com.intellij.ideEventQueueDispatcher](https://jb.gg/ipe?extensions=com.intellij.ideEventQueueDispatcher) | [`EventDispatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/IdeEventQueue.java) |
| [com.intellij.ideRootPaneNorth](https://jb.gg/ipe?extensions=com.intellij.ideRootPaneNorth) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`IdeRootPaneNorthExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/IdeRootPaneNorthExtension.java) |
| [com.intellij.inspectionPopupLevelChangePolicy](https://jb.gg/ipe?extensions=com.intellij.inspectionPopupLevelChangePolicy) ![Internal API][internal] | [`InspectionPopupLevelChangePolicy`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/InspectionPopupLevelChangePolicy.java) |
| [com.intellij.interactiveCourseFactory](https://jb.gg/ipe?extensions=com.intellij.interactiveCourseFactory) | [`InteractiveCourseFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/InteractiveCourseFactory.kt) |
| [com.intellij.internal.ml.featureProvider](https://jb.gg/ipe?extensions=com.intellij.internal.ml.featureProvider) ![Internal API][internal] | [`MLFeatureProvider`](%gh-ic%/platform/ml-api/src/com/intellij/internal/ml/MLFeatureProvider.kt) |
| [com.intellij.itemPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.itemPresentationProvider) | [`ItemPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/ItemPresentationProvider.java) |
| [com.intellij.iw.actionProvider](https://jb.gg/ipe?extensions=com.intellij.iw.actionProvider) | [`InspectionWidgetActionProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/markup/InspectionWidgetActionProvider.kt) |
| [com.intellij.jbProtocolCommand](https://jb.gg/ipe?extensions=com.intellij.jbProtocolCommand) | [`JBProtocolCommand`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/application/JBProtocolCommand.kt) |
| [com.intellij.jbProtocolRevisionResolver](https://jb.gg/ipe?extensions=com.intellij.jbProtocolRevisionResolver) | [`JBProtocolRevisionResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/navigation/JBProtocolRevisionResolver.java) |
| [com.intellij.jcef.appRequiredArgumentsProvider](https://jb.gg/ipe?extensions=com.intellij.jcef.appRequiredArgumentsProvider) ![Non-Dynamic][non-dynamic] | [`JBCefAppRequiredArgumentsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefAppRequiredArgumentsProvider.kt) |
| [com.intellij.jdkDownloader.jdkInstallerListener](https://jb.gg/ipe?extensions=com.intellij.jdkDownloader.jdkInstallerListener) | [`JdkInstallerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkInstaller.kt) |
| [com.intellij.jdkUpdateCheckContributor](https://jb.gg/ipe?extensions=com.intellij.jdkUpdateCheckContributor) | [`JdkUpdateCheckContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkUpdater.kt) |
| [com.intellij.jps.plugin](https://jb.gg/ipe?extensions=com.intellij.jps.plugin) | `n/a` |
| [com.intellij.keymapExtension](https://jb.gg/ipe?extensions=com.intellij.keymapExtension) | [`KeymapExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapExtension.java) |
| [com.intellij.lafProvider](https://jb.gg/ipe?extensions=com.intellij.lafProvider) ![Non-Dynamic][non-dynamic] | [`LafProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/LafProvider.java) |
| [com.intellij.lang.syntaxHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighterFactory) | [`SyntaxHighlighterFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java) |
| [com.intellij.library.toolWindow](https://jb.gg/ipe?extensions=com.intellij.library.toolWindow) | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.lightEditTabAttributesProvider](https://jb.gg/ipe?extensions=com.intellij.lightEditTabAttributesProvider) ![Experimental API][experimental] | [`LightEditTabAttributesProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditTabAttributesProvider.java) |
| [com.intellij.newProjectWizard.language](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.language) | [`LanguageNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/LanguageNewProjectWizard.kt) |
| [com.intellij.nonProjectFileWritingAccessExtension](https://jb.gg/ipe?extensions=com.intellij.nonProjectFileWritingAccessExtension) ![Project-Level][project-level] | [`NonProjectFileWritingAccessExtension`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/NonProjectFileWritingAccessExtension.java) |
| [com.intellij.notification.group](https://jb.gg/ipe?extensions=com.intellij.notification.group) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.notification.parentGroup](https://jb.gg/ipe?extensions=com.intellij.notification.parentGroup) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.notificationRemindLaterHandler](https://jb.gg/ipe?extensions=com.intellij.notificationRemindLaterHandler) | [`NotificationRemindLaterHandler`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationRemindLaterHandler.kt) |
| [com.intellij.obsoleteStorage](https://jb.gg/ipe?extensions=com.intellij.obsoleteStorage) | `n/a` |
| [com.intellij.pathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.pathMacroContributor) | [`PathMacroContributor`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| [com.intellij.pathMacroExpandableProtocol](https://jb.gg/ipe?extensions=com.intellij.pathMacroExpandableProtocol) | `n/a` |
| [com.intellij.pathMacroFilter](https://jb.gg/ipe?extensions=com.intellij.pathMacroFilter) | [`PathMacroFilter`](%gh-ic%/jps/model-serialization/src/com/intellij/openapi/application/PathMacroFilter.java) |
| [com.intellij.persistentFsConnectionListener](https://jb.gg/ipe?extensions=com.intellij.persistentFsConnectionListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`PersistentFsConnectionListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/newvfs/persistent/PersistentFsConnectionListener.java) |
| [com.intellij.pluginReplacement](https://jb.gg/ipe?extensions=com.intellij.pluginReplacement) | [`PluginReplacement`](%gh-ic%/platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) |
| [com.intellij.pluginRepositoryAuthProvider](https://jb.gg/ipe?extensions=com.intellij.pluginRepositoryAuthProvider) | [`PluginRepositoryAuthProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthProvider.java) |
| [com.intellij.preloadingActivity](https://jb.gg/ipe?extensions=com.intellij.preloadingActivity) ![Internal API][internal] | [`PreloadingActivity`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/PreloadingActivity.kt) |
| [com.intellij.productivityFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.productivityFeaturesProvider) | [`ProductivityFeaturesProvider`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/ProductivityFeaturesProvider.java) |
| [com.intellij.projectAttachProcessor](https://jb.gg/ipe?extensions=com.intellij.projectAttachProcessor) | [`ProjectAttachProcessor`](%gh-ic%/platform/ide-core/src/com/intellij/projectImport/ProjectAttachProcessor.java) |
| [com.intellij.projectBuildWarmupSupport](https://jb.gg/ipe?extensions=com.intellij.projectBuildWarmupSupport) ![Non-Dynamic][non-dynamic] ![Internal API][internal] ![Project-Level][project-level] | [`ProjectBuildWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectBuildWarmupSupport.kt) |
| [com.intellij.projectCloseHandler](https://jb.gg/ipe?extensions=com.intellij.projectCloseHandler) ![Non-Dynamic][non-dynamic] | [`ProjectCloseHandler`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseHandler.java) |
| [com.intellij.projectConfigurable](https://jb.gg/ipe?extensions=com.intellij.projectConfigurable) ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.projectIndexesWarmupSupport](https://jb.gg/ipe?extensions=com.intellij.projectIndexesWarmupSupport) ![Non-Dynamic][non-dynamic] ![Internal API][internal] ![Project-Level][project-level] | [`ProjectIndexesWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectIndexesWarmupSupport.kt) |
| [com.intellij.projectNameProvider](https://jb.gg/ipe?extensions=com.intellij.projectNameProvider) ![Non-Dynamic][non-dynamic] | [`ProjectNameProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/project/ex/ProjectNameProvider.java) |
| [com.intellij.projectOpenProcessor](https://jb.gg/ipe?extensions=com.intellij.projectOpenProcessor) | [`ProjectOpenProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.kt) |
| [com.intellij.projectOriginInfoProvider](https://jb.gg/ipe?extensions=com.intellij.projectOriginInfoProvider) ![Internal API][internal] | [`ProjectOriginInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/ProjectOriginInfoProvider.kt) |
| [com.intellij.projectServiceContainerCustomizer](https://jb.gg/ipe?extensions=com.intellij.projectServiceContainerCustomizer) | [`ProjectServiceContainerCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| [com.intellij.projectServiceContainerInitializedListener](https://jb.gg/ipe?extensions=com.intellij.projectServiceContainerInitializedListener) ![Internal API][internal] | [`ProjectServiceContainerInitializedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| [com.intellij.projectSetProcessor](https://jb.gg/ipe?extensions=com.intellij.projectSetProcessor) ![Non-Dynamic][non-dynamic] | [`ProjectSetProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectSetProcessor.java) |
| [com.intellij.projectStoreClassProvider](https://jb.gg/ipe?extensions=com.intellij.projectStoreClassProvider) ![Non-Dynamic][non-dynamic] | [`ProjectStoreFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectStoreFactory.java) |
| [com.intellij.projectTemplate](https://jb.gg/ipe?extensions=com.intellij.projectTemplate) | `n/a` |
| [com.intellij.projectTemplatesFactory](https://jb.gg/ipe?extensions=com.intellij.projectTemplatesFactory) | [`ProjectTemplatesFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/ProjectTemplatesFactory.java) |
| [com.intellij.projectTypesProvider](https://jb.gg/ipe?extensions=com.intellij.projectTypesProvider) ![Experimental API][experimental] | [`ProjectTypesProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectTypesProvider.java) |
| [com.intellij.projectUndoProvider](https://jb.gg/ipe?extensions=com.intellij.projectUndoProvider) ![Project-Level][project-level] | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.protanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.protanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.protocolHandler](https://jb.gg/ipe?extensions=com.intellij.protocolHandler) | [`ProtocolHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ProtocolHandler.kt) |
| [com.intellij.rawEditorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.rawEditorTypedHandler) ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.recoveryAction](https://jb.gg/ipe?extensions=com.intellij.recoveryAction) ![Internal API][internal] | [`RecoveryAction`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/cache/Saul.kt) |
| [com.intellij.remote.credentialsLanguageContribution](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsLanguageContribution) | [`CredentialsLanguageContribution`](%gh-ic%/platform/platform-impl/src/com/intellij/remote/ext/CredentialsLanguageContribution.java) |
| [com.intellij.remote.credentialsType](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsType) | [`CredentialsType`](%gh-ic%/platform/remote-core/src/remote/CredentialsType.java) |
| [com.intellij.remote.pathMappingProvider](https://jb.gg/ipe?extensions=com.intellij.remote.pathMappingProvider) | [`PathMappingProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/remote/PathMappingProvider.java) |
| [com.intellij.schemeExporter](https://jb.gg/ipe?extensions=com.intellij.schemeExporter) | [`SchemeExporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeExporter.java) |
| [com.intellij.schemeImporter](https://jb.gg/ipe?extensions=com.intellij.schemeImporter) | [`SchemeImporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeImporter.java) |
| [com.intellij.search.optionContributor](https://jb.gg/ipe?extensions=com.intellij.search.optionContributor) | [`SearchableOptionContributor`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionContributor.java) |
| [com.intellij.search.projectOptionsTopHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.projectOptionsTopHitProvider) | [`ProjectLevelProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/OptionsSearchTopHitProvider.java) |
| [com.intellij.search.topHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.topHitProvider) | [`SearchTopHitProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SearchTopHitProvider.java) |
| [com.intellij.search.traverseUiHelper](https://jb.gg/ipe?extensions=com.intellij.search.traverseUiHelper) ![Non-Dynamic][non-dynamic] | [`TraverseUIHelper`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/TraverseUIHelper.java) |
| [com.intellij.selectInTarget](https://jb.gg/ipe?extensions=com.intellij.selectInTarget) ![Project-Level][project-level] | [`SelectInTarget`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SelectInTarget.java) |
| [com.intellij.semanticRootProvider](https://jb.gg/ipe?extensions=com.intellij.semanticRootProvider) ![Non-Dynamic][non-dynamic] | [`RootSemanticAddressProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/newvfs/persistent/RootSemanticAddressProvider.java) |
| [com.intellij.settingsEntryPointActionProvider](https://jb.gg/ipe?extensions=com.intellij.settingsEntryPointActionProvider) | [`ActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| [com.intellij.settingsEntryPointIconCustomizer](https://jb.gg/ipe?extensions=com.intellij.settingsEntryPointIconCustomizer) | [`IconCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| [com.intellij.smartSelectProvider](https://jb.gg/ipe?extensions=com.intellij.smartSelectProvider) | [`SmartSelectProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/SmartSelectProvider.java) |
| [com.intellij.sshCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.sshCredentialProvider) | [`SshCredentialProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/remote/SshCredentialProvider.java) |
| [com.intellij.startPagePromoter](https://jb.gg/ipe?extensions=com.intellij.startPagePromoter) ![Internal API][internal] | [`StartPagePromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StartPagePromoter.kt) |
| [com.intellij.statistic.eventLog.eventLogEndpointSubstitutor](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.eventLogEndpointSubstitutor) ![Deprecated][deprecated] ![Internal API][internal] | [`EventLogEndpointSubstitutor`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogEndpointSubstitutor.java) |
| [com.intellij.statistic.eventLog.eventLoggerProvider](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.eventLoggerProvider) ![Non-Dynamic][non-dynamic] | [`StatisticsEventLoggerProvider`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/StatisticsEventLogger.kt) |
| [com.intellij.statistic.eventLog.externalEventLogSettings](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.externalEventLogSettings) ![Internal API][internal] | [`ExternalEventLogSettings`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogSettings.java) |
| [com.intellij.statistic.eventLog.fusStateEventTracker](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.fusStateEventTracker) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`FeatureUsageStateEventTracker`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/fus/FeatureUsageStateEventTracker.kt) |
| [com.intellij.statistics.actionCustomPlaceAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.actionCustomPlaceAllowlist) | `n/a` |
| [com.intellij.statistics.applicationUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.applicationUsagesCollector) ![Internal API][internal] | [`ApplicationUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ApplicationUsagesCollector.kt) |
| [com.intellij.statistics.balloonIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.balloonIdsHolder) ![Internal API][internal] | [`BalloonIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/ui/BalloonIdsHolder.java) |
| [com.intellij.statistics.collectorExtension](https://jb.gg/ipe?extensions=com.intellij.statistics.collectorExtension) ![Non-Dynamic][non-dynamic] | [`FeatureUsageCollectorExtension`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsageCollectorExtension.java) |
| [com.intellij.statistics.counterUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.counterUsagesCollector) ![Internal API][internal] | [`FeatureUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsagesCollector.java) |
| [com.intellij.statistics.gotItTooltipAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.gotItTooltipAllowlist) | `n/a` |
| [com.intellij.statistics.notificationIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.notificationIdsHolder) ![Internal API][internal] | [`NotificationIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/impl/NotificationIdsHolder.java) |
| [com.intellij.statistics.projectUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.projectUsagesCollector) ![Internal API][internal] | [`ProjectUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ProjectUsagesCollector.java) |
| [com.intellij.statistics.validation.customValidationRule](https://jb.gg/ipe?extensions=com.intellij.statistics.validation.customValidationRule) | [`CustomValidationRule`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRule.java) |
| [com.intellij.statusBarWidgetFactory](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetFactory) | [`StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java) |
| [com.intellij.statusBarWidgetProvider](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetProvider) ![Deprecated][deprecated] | [`StatusBarWidgetProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetProvider.java) |
| [com.intellij.streamProviderFactory](https://jb.gg/ipe?extensions=com.intellij.streamProviderFactory) ![Internal API][internal] ![Project-Level][project-level] | [`StreamProviderFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/configurationStore/StreamProviderFactory.kt) |
| [com.intellij.stripTrailingSpacesFilterFactory](https://jb.gg/ipe?extensions=com.intellij.stripTrailingSpacesFilterFactory) | [`StripTrailingSpacesFilterFactory`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/StripTrailingSpacesFilterFactory.java) |
| [com.intellij.systemProperty](https://jb.gg/ipe?extensions=com.intellij.systemProperty) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.testStatusListener](https://jb.gg/ipe?extensions=com.intellij.testStatusListener) ![Non-Dynamic][non-dynamic] | [`TestStatusListener`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/TestStatusListener.java) |
| [com.intellij.textEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.textEditorCustomizer) | [`TextEditorCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/text/TextEditorCustomizer.java) |
| [com.intellij.themeMetadataProvider](https://jb.gg/ipe?extensions=com.intellij.themeMetadataProvider) | `n/a` |
| [com.intellij.themeProvider](https://jb.gg/ipe?extensions=com.intellij.themeProvider) | `n/a` |
| [com.intellij.tipAndTrick](https://jb.gg/ipe?extensions=com.intellij.tipAndTrick) | `n/a` |
| [com.intellij.titleInfoProvider](https://jb.gg/ipe?extensions=com.intellij.titleInfoProvider) ![Non-Dynamic][non-dynamic] | [`TitleInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [com.intellij.toolWindow](https://jb.gg/ipe?extensions=com.intellij.toolWindow) | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.toolWindowAllowlist](https://jb.gg/ipe?extensions=com.intellij.toolWindowAllowlist) | `n/a` |
| [com.intellij.toolWindowContentExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowContentExtractor) ![Experimental API][experimental] | [`ToolWindowContentExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowContentExtractor.java) |
| [com.intellij.toolWindowExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractor) ![Experimental API][experimental] | [`ToolWindowViewModelExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelExtractor.java) |
| [com.intellij.toolWindowExtractorMode](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractorMode) ![Experimental API][experimental] | `n/a` |
| [com.intellij.trailingSpacesOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.trailingSpacesOptionsProvider) | [`TrailingSpacesOptionsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/TrailingSpacesOptionsProvider.java) |
| [com.intellij.tree.CustomLanguageASTComparator](https://jb.gg/ipe?extensions=com.intellij.tree.CustomLanguageASTComparator) | [`CustomLanguageASTComparator`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/CustomLanguageASTComparator.java) |
| [com.intellij.tritanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.tritanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.troubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.troubleInfoCollector) | [`TroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |
| [com.intellij.trustedHostsConfigurablePanelProvider](https://jb.gg/ipe?extensions=com.intellij.trustedHostsConfigurablePanelProvider) ![Deprecated][deprecated] ![Internal API][internal] | [`TrustedHostsConfigurablePanelProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedHostsConfigurable.kt) |
| [com.intellij.trustedHostsConfigurableProvider](https://jb.gg/ipe?extensions=com.intellij.trustedHostsConfigurableProvider) ![Internal API][internal] | [`TrustedHostsConfigurableProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedHostsConfigurable.kt) |
| [com.intellij.ui.optionEditorProvider](https://jb.gg/ipe?extensions=com.intellij.ui.optionEditorProvider) ![Experimental API][experimental] | [`OptionEditorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/OptionEditorProvider.java) |
| [com.intellij.ui.suitableFontProvider](https://jb.gg/ipe?extensions=com.intellij.ui.suitableFontProvider) | [`SuitableFontProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SuitableFontProvider.java) |
| [com.intellij.undoProvider](https://jb.gg/ipe?extensions=com.intellij.undoProvider) | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.undoReportHandler](https://jb.gg/ipe?extensions=com.intellij.undoReportHandler) ![Internal API][internal] | [`UndoReportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoReportHandler.java) |
| [com.intellij.unknownSdkContributor](https://jb.gg/ipe?extensions=com.intellij.unknownSdkContributor) | [`UnknownSdkContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/UnknownSdkCollector.kt) |
| [com.intellij.unknownSdkResolver](https://jb.gg/ipe?extensions=com.intellij.unknownSdkResolver) | [`UnknownSdkResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/UnknownSdkResolver.java) |
| [com.intellij.updateSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.updateSettingsProvider) | [`UpdateSettingsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsProvider.kt) |
| [com.intellij.utf8BomOptionProvider](https://jb.gg/ipe?extensions=com.intellij.utf8BomOptionProvider) | [`Utf8BomOptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/Utf8BomOptionProvider.java) |
| [com.intellij.vfs.local.fileOperationsHandler](https://jb.gg/ipe?extensions=com.intellij.vfs.local.fileOperationsHandler) | [`LocalFileOperationsHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) |
| [com.intellij.vfs.local.pluggableContentLoader](https://jb.gg/ipe?extensions=com.intellij.vfs.local.pluggableContentLoader) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`PluggableLocalFileSystemContentLoader`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/impl/local/PluggableLocalFileSystemContentLoader.java) |
| [com.intellij.vfs.local.pluggableFileWatcher](https://jb.gg/ipe?extensions=com.intellij.vfs.local.pluggableFileWatcher) ![Non-Dynamic][non-dynamic] | [`PluggableFileWatcher`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/vfs/local/PluggableFileWatcher.java) |
| [com.intellij.webBrowserUrlProvider](https://jb.gg/ipe?extensions=com.intellij.webBrowserUrlProvider) | [`WebBrowserUrlProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/WebBrowserUrlProvider.kt) |
| [com.intellij.webHelpProvider](https://jb.gg/ipe?extensions=com.intellij.webHelpProvider) | [`WebHelpProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) |
| [com.intellij.welcome.projectDetector](https://jb.gg/ipe?extensions=com.intellij.welcome.projectDetector) | [`ProjectDetector`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/ProjectDetector.java) |
| [com.intellij.welcomeFrameProvider](https://jb.gg/ipe?extensions=com.intellij.welcomeFrameProvider) | [`WelcomeFrameProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeFrameProvider.java) |
| [com.intellij.welcomeScreen](https://jb.gg/ipe?extensions=com.intellij.welcomeScreen) ![Non-Dynamic][non-dynamic] | [`WelcomeScreenProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenProvider.java) |
| [com.intellij.welcomeScreenCustomization](https://jb.gg/ipe?extensions=com.intellij.welcomeScreenCustomization) ![Experimental API][experimental] | [`WelcomeScreenCustomization`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenCustomization.java) |
| [com.intellij.welcomeTabFactory](https://jb.gg/ipe?extensions=com.intellij.welcomeTabFactory) | [`WelcomeTabFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeTabFactory.java) |
| [org.jetbrains.javaScriptDebuggerStarter](https://jb.gg/ipe?extensions=org.jetbrains.javaScriptDebuggerStarter) | [`JavaScriptDebuggerStarter`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/browsers/JavaScriptDebuggerStarter.java) |
| [org.jetbrains.urlOpener](https://jb.gg/ipe?extensions=org.jetbrains.urlOpener) | [`UrlOpener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/UrlOpener.java) |

### PlatformLangComponents.xml

[`PlatformLangComponents.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformLangComponents.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.registerToolWindowTaskProvider](https://jb.gg/ipe?extensions=com.intellij.registerToolWindowTaskProvider) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`RegisterToolWindowTaskProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/toolWindow/toolwindow.kt) |

### ProjectModel.xml

[`ProjectModel.xml`](%gh-ic%/platform/projectModel-api/resources/META-INF/ProjectModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customLibraryTable](https://jb.gg/ipe?extensions=com.intellij.customLibraryTable) | [`CustomLibraryTableDescription`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/CustomLibraryTableDescription.java) |
| [com.intellij.filePropertyPusher](https://jb.gg/ipe?extensions=com.intellij.filePropertyPusher) | [`FilePropertyPusher`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/impl/FilePropertyPusher.java) |
| [com.intellij.moduleExtension](https://jb.gg/ipe?extensions=com.intellij.moduleExtension) | [`ModuleExtension`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleExtension.java) |
| [com.intellij.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.orderEnumerationHandlerFactory) | [`Factory`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderEnumerationHandler.java) |
| [com.intellij.orderRootType](https://jb.gg/ipe?extensions=com.intellij.orderRootType) ![Non-Dynamic][non-dynamic] | [`OrderRootType`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderRootType.java) |
| [com.intellij.primaryModuleManager](https://jb.gg/ipe?extensions=com.intellij.primaryModuleManager) | [`PrimaryModuleManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/module/PrimaryModuleManager.java) |
| [com.intellij.projectFileScanner](https://jb.gg/ipe?extensions=com.intellij.projectFileScanner) | [`IndexableFileScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFileScanner.java) |

### ProjectModelImpl.xml

[`ProjectModelImpl.xml`](%gh-ic%/platform/projectModel-impl/resources/META-INF/ProjectModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.additionalLibraryRootsProvider](https://jb.gg/ipe?extensions=com.intellij.additionalLibraryRootsProvider) | [`AdditionalLibraryRootsProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java) |
| [com.intellij.customEntityProjectModelInfoProvider](https://jb.gg/ipe?extensions=com.intellij.customEntityProjectModelInfoProvider) ![Experimental API][experimental] | [`CustomEntityProjectModelInfoProvider`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/CustomEntityProjectModelInfoProvider.java) |
| [com.intellij.directoryIndexExcludePolicy](https://jb.gg/ipe?extensions=com.intellij.directoryIndexExcludePolicy) ![Project-Level][project-level] | [`DirectoryIndexExcludePolicy`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/DirectoryIndexExcludePolicy.java) |
| [com.intellij.projectExtension](https://jb.gg/ipe?extensions=com.intellij.projectExtension) ![Internal API][internal] ![Project-Level][project-level] | [`ProjectExtension`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/ProjectExtension.java) |
| [com.intellij.projectPathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.projectPathMacroContributor) ![Internal API][internal] | [`ProjectWidePathMacroContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/components/impl/ProjectWidePathMacroContributor.java) |
| [com.intellij.workspaceModel.facetContributor](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.facetContributor) ![Internal API][internal] | [`WorkspaceFacetContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/workspaceModel/ide/legacyBridge/WorkspaceFacetContributor.kt) |
| [com.intellij.workspaceModel.fileIndexContributor](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.fileIndexContributor) | [`WorkspaceFileIndexContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/WorkspaceFileIndexContributor.kt) |
| [com.intellij.workspaceModel.moduleExtensionBridgeFactory](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.moduleExtensionBridgeFactory) | [`ModuleExtensionBridgeFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/ide/legacyBridge/ModuleExtensionBridgeFactory.kt) |

### RefactoringExtensionPoints.xml

[`RefactoringExtensionPoints.xml`](%gh-ic%/platform/refactoring/resources/META-INF/RefactoringExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.automaticRenamerFactory](https://jb.gg/ipe?extensions=com.intellij.automaticRenamerFactory) | [`AutomaticRenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/naming/AutomaticRenamerFactory.java) |
| [com.intellij.findInProjectSearchEngine](https://jb.gg/ipe?extensions=com.intellij.findInProjectSearchEngine) ![Experimental API][experimental] | [`FindInProjectSearchEngine`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindInProjectSearchEngine.java) |
| [com.intellij.inlineActionHandler](https://jb.gg/ipe?extensions=com.intellij.inlineActionHandler) | [`InlineActionHandler`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/InlineActionHandler.java) |
| [com.intellij.lang.namesValidator](https://jb.gg/ipe?extensions=com.intellij.lang.namesValidator) | [`NamesValidator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/refactoring/NamesValidator.java) |
| [com.intellij.lang.refactoringSupport](https://jb.gg/ipe?extensions=com.intellij.lang.refactoringSupport) | [`RefactoringSupportProvider`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java) |
| [com.intellij.nameSuggestionProvider](https://jb.gg/ipe?extensions=com.intellij.nameSuggestionProvider) | [`NameSuggestionProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) |
| [com.intellij.qualifiedNameProvider](https://jb.gg/ipe?extensions=com.intellij.qualifiedNameProvider) | [`QualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/QualifiedNameProvider.java) |
| [com.intellij.refactoring.elementListenerProvider](https://jb.gg/ipe?extensions=com.intellij.refactoring.elementListenerProvider) ![Project-Level][project-level] | [`RefactoringElementListenerProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/refactoring/listeners/RefactoringElementListenerProvider.java) |
| [com.intellij.refactoring.helper](https://jb.gg/ipe?extensions=com.intellij.refactoring.helper) | [`RefactoringHelper`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/RefactoringHelper.java) |
| [com.intellij.refactoring.inlineHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.inlineHandler) | [`InlineHandler`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/InlineHandler.java) |
| [com.intellij.rename.inplace.resolveSnapshotProvider](https://jb.gg/ipe?extensions=com.intellij.rename.inplace.resolveSnapshotProvider) | [`ResolveSnapshotProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/ResolveSnapshotProvider.java) |
| [com.intellij.renameHandler](https://jb.gg/ipe?extensions=com.intellij.renameHandler) | [`RenameHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) |
| [com.intellij.renameInputValidator](https://jb.gg/ipe?extensions=com.intellij.renameInputValidator) | [`RenameInputValidator`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameInputValidator.java) |
| [com.intellij.renamePsiElementProcessor](https://jb.gg/ipe?extensions=com.intellij.renamePsiElementProcessor) | [`RenamePsiElementProcessorBase`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenamePsiElementProcessorBase.java) |
| [com.intellij.renameRefactoringDialogProvider](https://jb.gg/ipe?extensions=com.intellij.renameRefactoringDialogProvider) | [`RenameRefactoringDialogProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameRefactoringDialogProvider.java) |
| [com.intellij.renamerFactory](https://jb.gg/ipe?extensions=com.intellij.renamerFactory) ![Experimental API][experimental] | [`RenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenamerFactory.java) |
| [com.intellij.testSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.testSourcesFilter) | [`TestSourcesFilter`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java) |
| [com.intellij.updateAddedFileProcessor](https://jb.gg/ipe?extensions=com.intellij.updateAddedFileProcessor) | [`UpdateAddedFileProcessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/file/UpdateAddedFileProcessor.java) |
| [com.intellij.vetoRenameCondition](https://jb.gg/ipe?extensions=com.intellij.vetoRenameCondition) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.virtualFileQualifiedNameProvider](https://jb.gg/ipe?extensions=com.intellij.virtualFileQualifiedNameProvider) | [`VirtualFileQualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/VirtualFileQualifiedNameProvider.java) |

### RefactoringLangExtensionPoints.xml

[`RefactoringLangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/RefactoringLangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.changeSignatureDetector](https://jb.gg/ipe?extensions=com.intellij.changeSignatureDetector) | [`LanguageChangeSignatureDetector`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/changeSignature/inplace/LanguageChangeSignatureDetector.java) |
| [com.intellij.lang.refactoringSupport.classMembersRefactoringSupport](https://jb.gg/ipe?extensions=com.intellij.lang.refactoringSupport.classMembersRefactoringSupport) | [`ClassMembersRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/classMembers/ClassMembersRefactoringSupport.java) |
| [com.intellij.moveFileHandler](https://jb.gg/ipe?extensions=com.intellij.moveFileHandler) | [`MoveFileHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/moveFilesOrDirectories/MoveFileHandler.java) |
| [com.intellij.refactoring.changeSignatureUsageProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.changeSignatureUsageProcessor) | [`ChangeSignatureUsageProcessor`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/changeSignature/ChangeSignatureUsageProcessor.java) |
| [com.intellij.refactoring.copyHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.copyHandler) | [`CopyHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/copy/CopyHandlerDelegate.java) |
| [com.intellij.refactoring.extractIncludeHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.extractIncludeHandler) | [`RefactoringActionHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/RefactoringActionHandler.java) |
| [com.intellij.refactoring.introduceParameterObject](https://jb.gg/ipe?extensions=com.intellij.refactoring.introduceParameterObject) | [`IntroduceParameterObjectDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/introduceParameterObject/IntroduceParameterObjectDelegate.java) |
| [com.intellij.refactoring.invertBoolean](https://jb.gg/ipe?extensions=com.intellij.refactoring.invertBoolean) | [`InvertBooleanDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/invertBoolean/InvertBooleanDelegate.java) |
| [com.intellij.refactoring.moveDirectoryWithClassesHelper](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveDirectoryWithClassesHelper) | [`MoveDirectoryWithClassesHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveDirectoryWithClassesHelper.java) |
| [com.intellij.refactoring.moveHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveHandler) | [`MoveHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/MoveHandlerDelegate.java) |
| [com.intellij.refactoring.pushDown](https://jb.gg/ipe?extensions=com.intellij.refactoring.pushDown) | [`PushDownDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/memberPushDown/PushDownDelegate.java) |
| [com.intellij.refactoring.safeDeleteProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.safeDeleteProcessor) | [`SafeDeleteProcessorDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/safeDelete/SafeDeleteProcessorDelegate.java) |
| [com.intellij.rename.symbolRenameTargetFactory](https://jb.gg/ipe?extensions=com.intellij.rename.symbolRenameTargetFactory) | [`SymbolRenameTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/symbol/SymbolRenameTargetFactory.java) |
| [com.intellij.renameFileActionProvider](https://jb.gg/ipe?extensions=com.intellij.renameFileActionProvider) | [`RenameFileActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/actions/RenameFileActionProvider.java) |
| [com.intellij.suggestedRefactoringSupport](https://jb.gg/ipe?extensions=com.intellij.suggestedRefactoringSupport) | [`SuggestedRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/suggested/SuggestedRefactoringSupport.kt) |

### RegExpPlugin.xml

[`RegExpPlugin.xml`](%gh-ic%/RegExpSupport/resources/META-INF/RegExpPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.regExpCapabilitiesProvider](https://jb.gg/ipe?extensions=com.intellij.regExpCapabilitiesProvider) | [`RegExpCapabilitiesProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpCapabilitiesProvider.java) |
| [com.intellij.regExpLanguageHost](https://jb.gg/ipe?extensions=com.intellij.regExpLanguageHost) | [`RegExpLanguageHost`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpLanguageHost.java) |
| [com.intellij.regExpMatcherProvider](https://jb.gg/ipe?extensions=com.intellij.regExpMatcherProvider) | [`RegExpMatcherProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpMatcherProvider.java) |
| [com.intellij.regExpModifierProvider](https://jb.gg/ipe?extensions=com.intellij.regExpModifierProvider) | [`RegExpModifierProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpModifierProvider.java) |

### smRunner.xml

[`smRunner.xml`](%gh-ic%/platform/smRunner/resources/META-INF/smRunner.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.importTestOutput](https://jb.gg/ipe?extensions=com.intellij.importTestOutput) | [`ImportTestOutputExtension`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/history/ImportTestOutputExtension.java) |

### SpellCheckerPlugin.xml

[`SpellCheckerPlugin.xml`](%gh-ic%/spellchecker/src/META-INF/SpellCheckerPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spellchecker.bundledDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.bundledDictionaryProvider) | [`BundledDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/BundledDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.customDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.customDictionaryProvider) | [`CustomDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/CustomDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.runtimeDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.runtimeDictionaryProvider) | [`RuntimeDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/RuntimeDictionaryProvider.java) |
| [com.intellij.spellchecker.support](https://jb.gg/ipe?extensions=com.intellij.spellchecker.support) | [`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java) |

### statistics-devkit.xml

[`statistics-devkit.xml`](%gh-ic%/platform/statistics/devkit/resources/META-INF/statistics-devkit.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.statisticsLogGroupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.statisticsLogGroupActionsProvider) ![Internal API][internal] | [`StatisticsLogGroupActionsProvider`](%gh-ic%/platform/statistics/devkit/src/com/intellij/internal/statistic/devkit/toolwindow/StatisticsLogGroupActionsProvider.kt) |

### structuralsearch.xml

[`structuralsearch.xml`](%gh-ic%/platform/structuralsearch/resources/META-INF/structuralsearch.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.structuralsearch.filterProvider](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.filterProvider) | [`FilterProvider`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/plugin/ui/filters/FilterProvider.java) |
| [com.intellij.structuralsearch.profile](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.profile) | [`StructuralSearchProfile`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/StructuralSearchProfile.java) |
| [com.intellij.structuralsearch.specialXmlTagExtractor](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.specialXmlTagExtractor) | [`SpecialElementExtractor`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/SpecialElementExtractor.java) |

### tasks.xml

[`tasks.xml`](%gh-ic%/platform/tasks-platform-impl/resources/META-INF/tasks.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tasks.contextProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.contextProvider) | [`WorkingContextProvider`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/context/WorkingContextProvider.java) |

### UsageViewActions.xml

[`UsageViewActions.xml`](%gh-ic%/platform/usageView/resources/idea/UsageViewActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.generatedSourceUsageFilter](https://jb.gg/ipe?extensions=com.intellij.generatedSourceUsageFilter) | [`GeneratedSourceUsageFilter`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/GeneratedSourceUsageFilter.kt) |
| [com.intellij.usages.usageReferenceClassProvider](https://jb.gg/ipe?extensions=com.intellij.usages.usageReferenceClassProvider) ![Non-Dynamic][non-dynamic] | [`UsageReferenceClassProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/impl/UsageReferenceClassProvider.kt) |

### vcs-log.xml

[`vcs-log.xml`](%gh-ic%/platform/vcs-log/impl/src/META-INF/vcs-log.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customVcsLogUiFactoryProvider](https://jb.gg/ipe?extensions=com.intellij.customVcsLogUiFactoryProvider) ![Experimental API][experimental] ![Project-Level][project-level] | [`CustomVcsLogUiFactoryProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/CustomVcsLogUiFactoryProvider.java) |
| [com.intellij.logHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.logHighlighterFactory) | [`VcsLogHighlighterFactory`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/highlighters/VcsLogHighlighterFactory.java) |
| [com.intellij.logProvider](https://jb.gg/ipe?extensions=com.intellij.logProvider) ![Project-Level][project-level] | [`VcsLogProvider`](%gh-ic%/platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogProvider.java) |
| [com.intellij.vcsLogCommitStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcsLogCommitStatusProvider) ![Experimental API][experimental] | [`VcsCommitExternalStatusProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/frame/VcsCommitExternalStatusProvider.kt) |
| [com.intellij.vcsLogCustomColumn](https://jb.gg/ipe?extensions=com.intellij.vcsLogCustomColumn) | [`VcsLogCustomColumn`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |

### VcsExtensionPoints.xml

[`VcsExtensionPoints.xml`](%gh-ic%/platform/vcs-impl/resources/META-INF/VcsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.changesGroupingPolicy](https://jb.gg/ipe?extensions=com.intellij.changesGroupingPolicy) | [`ChangesGroupingPolicyFactory`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesGroupingPolicyFactory.java) |
| [com.intellij.changesViewContent](https://jb.gg/ipe?extensions=com.intellij.changesViewContent) ![Project-Level][project-level] | [`ChangesViewContentProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentProvider.java) |
| [com.intellij.checkinHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.checkinHandlerFactory) | [`CheckinHandlerFactory`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkin/CheckinHandlerFactory.java) |
| [com.intellij.checkoutCompletedListener](https://jb.gg/ipe?extensions=com.intellij.checkoutCompletedListener) | [`CheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| [com.intellij.checkoutListener](https://jb.gg/ipe?extensions=com.intellij.checkoutListener) | [`CheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| [com.intellij.checkoutProvider](https://jb.gg/ipe?extensions=com.intellij.checkoutProvider) | [`CheckoutProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/CheckoutProvider.java) |
| [com.intellij.editChangelistSupport](https://jb.gg/ipe?extensions=com.intellij.editChangelistSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`EditChangelistSupport`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/EditChangelistSupport.java) |
| [com.intellij.generalVcsSettingsExtension](https://jb.gg/ipe?extensions=com.intellij.generalVcsSettingsExtension) ![Project-Level][project-level] | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.ignoredFileContentProvider](https://jb.gg/ipe?extensions=com.intellij.ignoredFileContentProvider) ![Project-Level][project-level] | [`IgnoredFileContentProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileContentProvider.java) |
| [com.intellij.ignoredFileProvider](https://jb.gg/ipe?extensions=com.intellij.ignoredFileProvider) ![Experimental API][experimental] | [`IgnoredFileProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileProvider.java) |
| [com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider) | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/actions/AnnotateToggleAction.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider) | [`ChangeDiffRequestProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffRequestProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider) | [`ChangeDiffViewerWrapperProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffViewerWrapperProvider.java) |
| [com.intellij.openapi.vcs.changes.ui.filePathIconProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.ui.filePathIconProvider) | [`FilePathIconProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/FilePathIconProvider.java) |
| [com.intellij.openapi.vcs.changes.vcsPreservingExecutor](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.vcsPreservingExecutor) | [`VcsPreservingExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsPreservingExecutor.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider) | [`LocalLineStatusTrackerProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| [com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension) | [`VcsCloneDialogExtension`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/ui/cloneDialog/VcsCloneDialogExtension.kt) |
| [com.intellij.patch.extension](https://jb.gg/ipe?extensions=com.intellij.patch.extension) | [`PatchEP`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/diff/impl/patch/PatchEP.java) |
| [com.intellij.unresolvedMergeCheckProvider](https://jb.gg/ipe?extensions=com.intellij.unresolvedMergeCheckProvider) | [`UnresolvedMergeCheckProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/checkin/UnresolvedMergeCheckProvider.java) |
| [com.intellij.vcs](https://jb.gg/ipe?extensions=com.intellij.vcs) | [`AbstractVcs`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/AbstractVcs.java) |
| [com.intellij.vcs.actions.ScheduleForAdditionActionExtension](https://jb.gg/ipe?extensions=com.intellij.vcs.actions.ScheduleForAdditionActionExtension) | [`ScheduleForAdditionActionExtension`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/ScheduleForAdditionActionExtension.kt) |
| [com.intellij.vcs.baseContentProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.baseContentProvider) ![Project-Level][project-level] | [`VcsBaseContentProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProvider.java) |
| [com.intellij.vcs.branchStateProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.branchStateProvider) ![Project-Level][project-level] | [`BranchStateProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/branch/BranchStateProvider.java) |
| [com.intellij.vcs.changeListChangeAssigner](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListChangeAssigner) ![Experimental API][experimental] ![Project-Level][project-level] | [`ChangeListChangeAssigner`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangeListChangeAssigner.java) |
| [com.intellij.vcs.changeListDecorator](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListDecorator) ![Project-Level][project-level] | [`ChangeListDecorator`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListDecorator.java) |
| [com.intellij.vcs.changes.changesViewModifier](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewModifier) ![Project-Level][project-level] | [`ChangesViewModifier`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [com.intellij.vcs.changes.changesViewNodeAction](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewNodeAction) ![Experimental API][experimental] ![Project-Level][project-level] | [`ChangesViewNodeAction`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewNodeAction.java) |
| [com.intellij.vcs.changes.localCommitExecutor](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.localCommitExecutor) ![Project-Level][project-level] | [`LocalCommitExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalCommitExecutor.java) |
| [com.intellij.vcs.checkoutProcessor](https://jb.gg/ipe?extensions=com.intellij.vcs.checkoutProcessor) | [`VcsCheckoutProcessor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsCheckoutProcessor.java) |
| [com.intellij.vcs.codeVisionLanguageContext](https://jb.gg/ipe?extensions=com.intellij.vcs.codeVisionLanguageContext) ![Experimental API][experimental] | [`VcsCodeVisionLanguageContext`](%gh-ic%/platform/vcs-api/src/com/intellij/codeInsight/hints/VcsCodeVisionLanguageContext.kt) |
| [com.intellij.vcs.commitMessageProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.commitMessageProvider) | [`CommitMessageProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/CommitMessageProvider.java) |
| [com.intellij.vcs.consoleFolding](https://jb.gg/ipe?extensions=com.intellij.vcs.consoleFolding) | [`VcsConsoleFolding`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/console/VcsConsoleView.kt) |
| [com.intellij.vcs.delayedCommitMessageProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.delayedCommitMessageProvider) ![Experimental API][experimental] | [`DelayedCommitMessageProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/DelayedCommitMessageProvider.kt) |
| [com.intellij.vcs.envCustomizer](https://jb.gg/ipe?extensions=com.intellij.vcs.envCustomizer) | [`VcsEnvCustomizer`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsEnvCustomizer.java) |
| [com.intellij.vcs.fileStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.fileStatusProvider) ![Project-Level][project-level] | [`FileStatusProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/impl/FileStatusProvider.java) |
| [com.intellij.vcs.ignoredFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.ignoredFilesHolder) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcs.taskHandler](https://jb.gg/ipe?extensions=com.intellij.vcs.taskHandler) ![Project-Level][project-level] | [`VcsTaskHandler`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsTaskHandler.java) |
| [com.intellij.vcs.unversionedFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.unversionedFilesHolder) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcsAnnotationGutterActionProvider](https://jb.gg/ipe?extensions=com.intellij.vcsAnnotationGutterActionProvider) | [`AnnotationGutterActionProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterActionProvider.java) |
| [com.intellij.vcsAnnotationGutterColumnProvider](https://jb.gg/ipe?extensions=com.intellij.vcsAnnotationGutterColumnProvider) | [`AnnotationGutterColumnProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterColumnProvider.java) |
| [com.intellij.vcsAwareCheckoutListener](https://jb.gg/ipe?extensions=com.intellij.vcsAwareCheckoutListener) | [`VcsAwareCheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/VcsAwareCheckoutListener.java) |
| [com.intellij.vcsBulkMovesOnlyChangesFilter](https://jb.gg/ipe?extensions=com.intellij.vcsBulkMovesOnlyChangesFilter) | [`BulkMovesOnlyChangesFilter`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/browser/BulkMovesOnlyChangesFilter.java) |
| [com.intellij.vcsChangesViewRefresher](https://jb.gg/ipe?extensions=com.intellij.vcsChangesViewRefresher) ![Project-Level][project-level] | [`ChangesViewRefresher`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangesViewRefresher.java) |
| [com.intellij.vcsCheckinHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.vcsCheckinHandlerFactory) | [`VcsCheckinHandlerFactory`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkin/VcsCheckinHandlerFactory.kt) |
| [com.intellij.vcsConfigurableProvider](https://jb.gg/ipe?extensions=com.intellij.vcsConfigurableProvider) | [`VcsConfigurableProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsConfigurableProvider.java) |
| [com.intellij.vcsIgnoreChecker](https://jb.gg/ipe?extensions=com.intellij.vcsIgnoreChecker) | [`VcsIgnoreChecker`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsIgnoreChecker.java) |
| [com.intellij.vcsPopupProvider](https://jb.gg/ipe?extensions=com.intellij.vcsPopupProvider) | [`VcsQuickListContentProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/actions/VcsQuickListContentProvider.java) |
| [com.intellij.vcsRepositoryInitializer](https://jb.gg/ipe?extensions=com.intellij.vcsRepositoryInitializer) | [`VcsRepositoryInitializer`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/VcsRepositoryInitializer.java) |
| [com.intellij.vcsRootChecker](https://jb.gg/ipe?extensions=com.intellij.vcsRootChecker) | [`VcsRootChecker`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsRootChecker.java) |
| [com.intellij.vcsSelectionProvider](https://jb.gg/ipe?extensions=com.intellij.vcsSelectionProvider) | [`VcsSelectionProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcsUtil/VcsSelectionProvider.java) |
| [com.intellij.vcsStartupActivity](https://jb.gg/ipe?extensions=com.intellij.vcsStartupActivity) ![Non-Dynamic][non-dynamic] | [`VcsStartupActivity`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsStartupActivity.java) |

### WebSymbolsExtensionPoints.xml

[`WebSymbolsExtensionPoints.xml`](%gh-ic%/platform/webSymbols/resources/META-INF/WebSymbolsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.webSymbols.codeCompletionItemCustomizer](https://jb.gg/ipe?extensions=com.intellij.webSymbols.codeCompletionItemCustomizer) | [`WebSymbolCodeCompletionItemCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/completion/WebSymbolCodeCompletionItemCustomizer.kt) |
| [com.intellij.webSymbols.context](https://jb.gg/ipe?extensions=com.intellij.webSymbols.context) | [`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt) |
| [com.intellij.webSymbols.declarationProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.declarationProvider) | [`WebSymbolDeclarationProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclarationProvider.kt) |
| [com.intellij.webSymbols.defaultIconProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.defaultIconProvider) | [`WebSymbolDefaultIconProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolDefaultIconProvider.kt) |
| [com.intellij.webSymbols.dependencyProximityProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.dependencyProximityProvider) | [`DependencyProximityProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/DependencyProximityProvider.kt) |
| [com.intellij.webSymbols.documentationCustomizer](https://jb.gg/ipe?extensions=com.intellij.webSymbols.documentationCustomizer) | [`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt) |
| [com.intellij.webSymbols.framework](https://jb.gg/ipe?extensions=com.intellij.webSymbols.framework) | [`WebSymbolsFramework`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/framework/WebSymbolsFramework.kt) |
| [com.intellij.webSymbols.highlightInLanguage](https://jb.gg/ipe?extensions=com.intellij.webSymbols.highlightInLanguage) | `n/a` |
| [com.intellij.webSymbols.inspectionToolMapping](https://jb.gg/ipe?extensions=com.intellij.webSymbols.inspectionToolMapping) | `n/a` |
| [com.intellij.webSymbols.psiSourcedSymbol](https://jb.gg/ipe?extensions=com.intellij.webSymbols.psiSourcedSymbol) | `n/a` |
| [com.intellij.webSymbols.psiSourcedSymbolProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.psiSourcedSymbolProvider) | [`PsiSourcedWebSymbolProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbolProvider.kt) |
| [com.intellij.webSymbols.queryConfigurator](https://jb.gg/ipe?extensions=com.intellij.webSymbols.queryConfigurator) | [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt) |
| [com.intellij.webSymbols.queryResultsCustomizerFactory](https://jb.gg/ipe?extensions=com.intellij.webSymbols.queryResultsCustomizerFactory) | [`WebSymbolsQueryResultsCustomizerFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryResultsCustomizerFactory.kt) |
| [com.intellij.webSymbols.webTypes](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes) ![Internal API][internal] | `n/a` |
| [com.intellij.webSymbols.webTypes.filter](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes.filter) | [`WebSymbolsFilter`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/filters/WebSymbolsFilter.kt) |
| [com.intellij.webSymbols.webTypes.symbolTypeSupportFactory](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes.symbolTypeSupportFactory) | [`Factory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/WebTypesSymbolTypeSupport.kt) |

### WorkspaceModelExtensions.xml

[`WorkspaceModelExtensions.xml`](%gh-ic%/platform/workspaceModel/jps/src/META-INF/WorkspaceModelExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.workspaceModel.cache.version](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.cache.version) | [`WorkspaceModelCacheVersion`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/WorkspaceModelCacheVersion.kt) |
| [com.intellij.workspaceModel.customFacetRelatedEntitySerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customFacetRelatedEntitySerializer) ![Internal API][internal] | [`CustomFacetRelatedEntitySerializer`](%gh-ic%/platform/workspaceModel/jps/src/com/intellij/workspaceModel/ide/impl/jps/serialization/CustomFacetRelatedEntitySerializer.kt) |
| [com.intellij.workspaceModel.customModuleComponentSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleComponentSerializer) ![Internal API][internal] | [`CustomModuleComponentSerializer`](%gh-ic%/platform/workspaceModel/jps/src/com/intellij/workspaceModel/ide/impl/jps/serialization/CustomModuleComponentSerializer.kt) |
| [com.intellij.workspaceModel.customModuleRootsSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleRootsSerializer) ![Internal API][internal] | [`CustomModuleRootsSerializer`](%gh-ic%/platform/workspaceModel/jps/src/com/intellij/workspaceModel/ide/impl/jps/serialization/CustomModuleRootsSerializer.kt) |
| [com.intellij.workspaceModel.preUpdateHandler](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.preUpdateHandler) ![Internal API][internal] | [`WorkspaceModelPreUpdateHandler`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/WorkspaceModelPreUpdateHandler.kt) |

### xdebugger.xml

[`xdebugger.xml`](%gh-ic%/platform/xdebugger-impl/resources/META-INF/xdebugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.xdebugger.attachDebuggerProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachDebuggerProvider) | [`XAttachDebuggerProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachDebuggerProvider.java) |
| [com.intellij.xdebugger.attachHostProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachHostProvider) | [`XAttachHostProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachHostProvider.java) |
| [com.intellij.xdebugger.attachHostSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachHostSettingsProvider) | [`XAttachHostSettingsProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachHostSettingsProvider.kt) |
| [com.intellij.xdebugger.breakpointGroupingRule](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointGroupingRule) | [`XBreakpointGroupingRule`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/ui/XBreakpointGroupingRule.java) |
| [com.intellij.xdebugger.breakpointType](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointType) | [`XBreakpointType`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointType.java) |
| [com.intellij.xdebugger.configurableProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.configurableProvider) | [`DebuggerConfigurableProvider`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/DebuggerConfigurableProvider.java) |
| [com.intellij.xdebugger.debuggerSupport](https://jb.gg/ipe?extensions=com.intellij.xdebugger.debuggerSupport) ![Deprecated][deprecated] | [`DebuggerSupport`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/DebuggerSupport.java) |
| [com.intellij.xdebugger.dialog.presentation.provider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.dialog.presentation.provider) ![Experimental API][experimental] | [`XAttachDialogItemPresentationProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachDialogItemPresentationProvider.kt) |
| [com.intellij.xdebugger.inlineValuePopupProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.inlineValuePopupProvider) | [`InlineValuePopupProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/inline/InlineValuePopupProvider.java) |
| [com.intellij.xdebugger.settings](https://jb.gg/ipe?extensions=com.intellij.xdebugger.settings) | [`XDebuggerSettings`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/XDebuggerSettings.java) |

### xml.xml

[`xml.xml`](%gh-ic%/xml/impl/resources/META-INF/xml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.completion.htmlInTextCompletionEnabler](https://jb.gg/ipe?extensions=com.intellij.completion.htmlInTextCompletionEnabler) ![Internal API][internal] | [`HtmlInTextCompletionEnabler`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionEnabler.java) |
| [com.intellij.completion.htmlInTextCompletionPopupExtension](https://jb.gg/ipe?extensions=com.intellij.completion.htmlInTextCompletionPopupExtension) ![Internal API][internal] | [`HtmlInTextCompletionPopupExtension`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionPopupExtension.java) |
| [com.intellij.html.compatibleLanguage](https://jb.gg/ipe?extensions=com.intellij.html.compatibleLanguage) ![Experimental API][experimental] | `n/a` |

### XmlPlugin.xml

[`XmlPlugin.xml`](%gh-ic%/platform/platform-resources/src/META-INF/XmlPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.embeddedTokenHighlighter](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenHighlighter) | [`EmbeddedTokenHighlighter`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/ide/highlighter/EmbeddedTokenHighlighter.java) |
| [com.intellij.embeddedTokenTypesProvider](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenTypesProvider) | [`EmbeddedTokenTypesProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/lexer/EmbeddedTokenTypesProvider.java) |
| [com.intellij.html.attributeValueProvider](https://jb.gg/ipe?extensions=com.intellij.html.attributeValueProvider) | [`HtmlAttributeValueProvider`](%gh-ic%/xml/impl/src/com/intellij/html/impl/providers/HtmlAttributeValueProvider.java) |
| [com.intellij.html.codestyle.panel](https://jb.gg/ipe?extensions=com.intellij.html.codestyle.panel) | [`HtmlCodeStylePanelExtension`](%gh-ic%/xml/impl/src/com/intellij/application/options/HtmlCodeStylePanelExtension.java) |
| [com.intellij.html.embeddedContentSupport](https://jb.gg/ipe?extensions=com.intellij.html.embeddedContentSupport) | [`HtmlEmbeddedContentSupport`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/html/embedding/HtmlEmbeddedContentSupport.kt) |
| [com.intellij.html.htmlScriptInjectionBlocker](https://jb.gg/ipe?extensions=com.intellij.html.htmlScriptInjectionBlocker) | [`HtmlScriptInjectionBlocker`](%gh-ic%/xml/impl/src/com/intellij/psi/impl/source/html/HtmlScriptInjectionBlocker.java) |
| [com.intellij.html.scriptContentProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptContentProvider) | [`HtmlScriptContentProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/lang/HtmlScriptContentProvider.java) |
| [com.intellij.html.scriptDocumentationProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptDocumentationProvider) | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.standardResource](https://jb.gg/ipe?extensions=com.intellij.standardResource) | `n/a` |
| [com.intellij.standardResourceProvider](https://jb.gg/ipe?extensions=com.intellij.standardResourceProvider) | [`StandardResourceProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/javaee/StandardResourceProvider.java) |
| [com.intellij.webSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.webSmartKeysConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.xml.attributeDescriptorsProvider](https://jb.gg/ipe?extensions=com.intellij.xml.attributeDescriptorsProvider) | [`XmlAttributeDescriptorsProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlAttributeDescriptorsProvider.java) |
| [com.intellij.xml.elementDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.elementDescriptorProvider) | [`XmlElementDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlElementDescriptorProvider.java) |
| [com.intellij.xml.fileNSInfoProvider](https://jb.gg/ipe?extensions=com.intellij.xml.fileNSInfoProvider) | [`XmlFileNSInfoProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFileNSInfoProvider.java) |
| [com.intellij.xml.idContributor](https://jb.gg/ipe?extensions=com.intellij.xml.idContributor) | [`XmlIdContributor`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/util/XmlIdContributor.java) |
| [com.intellij.xml.implicitIdRefProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitIdRefProvider) | [`ImplicitIdRefProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/ImplicitIdRefProvider.java) |
| [com.intellij.xml.implicitNamespaceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitNamespaceDescriptorProvider) | [`ImplicitNamespaceDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ImplicitNamespaceDescriptorProvider.java) |
| [com.intellij.xml.namedReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.xml.namedReferenceProvider) ![Experimental API][experimental] | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.xml.namespaceHelper](https://jb.gg/ipe?extensions=com.intellij.xml.namespaceHelper) | [`XmlNamespaceHelper`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlNamespaceHelper.java) |
| [com.intellij.xml.nsColorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.nsColorProvider) | [`XmlNSColorProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/XmlNSColorProvider.java) |
| [com.intellij.xml.psiPolicy](https://jb.gg/ipe?extensions=com.intellij.xml.psiPolicy) | [`XmlPsiPolicy`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlPsiPolicy.java) |
| [com.intellij.xml.relatedToHtmlFilesContributor](https://jb.gg/ipe?extensions=com.intellij.xml.relatedToHtmlFilesContributor) | [`RelatedToHtmlFilesContributor`](%gh-ic%/xml/impl/src/com/intellij/navigation/RelatedToHtmlFilesContributor.java) |
| [com.intellij.xml.schemaProvider](https://jb.gg/ipe?extensions=com.intellij.xml.schemaProvider) | [`XmlSchemaProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/xml/XmlSchemaProvider.java) |
| [com.intellij.xml.startTagEndToken](https://jb.gg/ipe?extensions=com.intellij.xml.startTagEndToken) | [`StartTagEndTokenProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/xml/StartTagEndTokenProvider.java) |
| [com.intellij.xml.tagNameProvider](https://jb.gg/ipe?extensions=com.intellij.xml.tagNameProvider) | [`XmlTagNameProvider`](%gh-ic%/xml/impl/src/com/intellij/xml/XmlTagNameProvider.java) |
| [com.intellij.xml.undefinedElementFixProvider](https://jb.gg/ipe?extensions=com.intellij.xml.undefinedElementFixProvider) | [`XmlUndefinedElementFixProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/xml/XmlUndefinedElementFixProvider.java) |
| [com.intellij.xml.validateHandler](https://jb.gg/ipe?extensions=com.intellij.xml.validateHandler) | [`ValidateXmlHandler`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/actions/validate/ValidateXmlHandler.java) |
| [com.intellij.xml.xmlAttributeRenameProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlAttributeRenameProvider) | [`XmlAttributeRenameProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInspection/htmlInspections/XmlAttributeRenameProvider.java) |
| [com.intellij.xml.xmlExtension](https://jb.gg/ipe?extensions=com.intellij.xml.xmlExtension) | [`XmlExtension`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlExtension.java) |
| [com.intellij.xml.xmlSuppressionProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlSuppressionProvider) | [`XmlSuppressionProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/codeInspection/XmlSuppressionProvider.java) |
| [com.intellij.xml.xmlTagRuleProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTagRuleProvider) | [`XmlTagRuleProvider`](%gh-ic%/xml/xml-analysis-api/src/com/intellij/xml/XmlTagRuleProvider.java) |
| [com.intellij.xml.xmlTypedHandlersAdditionalSupport](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTypedHandlersAdditionalSupport) | [`XmlTypedHandlersAdditionalSupport`](%gh-ic%/xml/xml-psi-api/src/com/intellij/openapi/editor/XmlTypedHandlersAdditionalSupport.java) |
| [com.intellij.xml.zenCodingFilter](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingFilter) | [`ZenCodingFilter`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/filters/ZenCodingFilter.java) |
| [com.intellij.xml.zenCodingGenerator](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingGenerator) | [`ZenCodingGenerator`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/generators/ZenCodingGenerator.java) |
| [com.intellij.xmlStructureViewBuilderProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewBuilderProvider) | [`XmlStructureViewBuilderProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewBuilderProvider.java) |
| [com.intellij.xmlStructureViewElementProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewElementProvider) | [`XmlStructureViewElementProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewElementProvider.java) |



## IntelliJ Community Plugins


### IntelliJ Community Plugins - Listeners

| Topic | Listener |
|-------|----------|
| [ExternalAnnotationsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.ExternalAnnotationsListener)  | [`ExternalAnnotationsListener`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ExternalAnnotationsListener.java) |
| [BuildManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.BuildManagerListener)  | [`BuildManagerListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildManagerListener.java) |
| [CustomBuilderMessageHandler#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.CustomBuilderMessageHandler)  | [`CustomBuilderMessageHandler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/CustomBuilderMessageHandler.java) |
| [PortableCachesLoadListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.PortableCachesLoadListener)  ![Internal API][internal] ![Project-Level][project-level] | [`PortableCachesLoadListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/PortableCachesLoadListener.java) |
| [DebuggerManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.impl.DebuggerManagerListener)  | [`DebuggerManagerListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/DebuggerManagerListener.java) |
| [GrazieStateLifecycleKt#topic](https://jb.gg/ipe/listeners?topics=com.intellij.grazie.ide.msg.GrazieStateLifecycle)  | [`GrazieStateLifecycle`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/ide/msg/GrazieStateLifecycle.kt) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.starters.StarterModuleProcessListener)  | [`StarterModuleProcessListener`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleProcessListener.kt) |
| [AntExecutionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.ant.config.execution.AntExecutionListener)  | [`AntExecutionListener`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntExecutionListener.java) |
| [CompilerTopics#COMPILATION_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.CompilationStatusListener)  ![Project-Level][project-level] | [`CompilationStatusListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilationStatusListener.java) |
| [ExcludedEntriesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.options.ExcludedEntriesListener)  | [`ExcludedEntriesListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/options/ExcludedEntriesListener.java) |
| [LanguageLevelProjectExtension#LANGUAGE_LEVEL_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.LanguageLevelProjectExtension.LanguageLevelChangeListener)  | [`LanguageLevelChangeListener`](%gh-ic%/java/java-psi-api/src/com/intellij/openapi/roots/LanguageLevelProjectExtension.java) |
| [ConfigurationErrors#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ui.configuration.ConfigurationErrors)  | [`ConfigurationErrors`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ConfigurationErrors.java) |
| [ArtifactManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.packaging.artifacts.ArtifactListener)  ![Project-Level][project-level] | [`ArtifactListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactListener.java) |
| [SvnVcs#ROOTS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.util.Consumer)  | [`Consumer`](%gh-ic%/platform/util-rt/src/com/intellij/util/Consumer.java) |
| [PyStackFrame#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.debugger.PyStackFrame.PyStackFrameRefreshedListener)  | [`PyStackFrameRefreshedListener`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyStackFrame.java) |
| [PyPackageManager#PACKAGE_MANAGER_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.PyPackageManager.Listener)  | [`Listener`](%gh-ic%/python/openapi/src/com/jetbrains/python/packaging/PyPackageManager.java) |
| [PythonPackageManager#PACKAGE_MANAGEMENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.common.PythonPackageManagementListener)  ![Experimental API][experimental] | [`PythonPackageManagementListener`](%gh-ic%/python/src/com/jetbrains/python/packaging/common/util.kt) |
| [GitBranchIncomingOutgoingManager#GIT_INCOMING_OUTGOING_CHANGED](https://jb.gg/ipe/listeners?topics=git4idea.branch.GitBranchIncomingOutgoingManager.GitIncomingOutgoingListener)  | [`GitIncomingOutgoingListener`](%gh-ic%/plugins/git4idea/src/git4idea/branch/GitBranchIncomingOutgoingManager.java) |
| [GitAuthenticationListener#GIT_AUTHENTICATION_SUCCESS](https://jb.gg/ipe/listeners?topics=git4idea.commands.GitAuthenticationListener)  | [`GitAuthenticationListener`](%gh-ic%/plugins/git4idea/src/git4idea/commands/GitAuthenticationListener.java) |
| [GitExecutableManager#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.config.GitExecutableListener)  | [`GitExecutableListener`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitExecutableListener.java) |
| [GitCommitTemplateListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitCommitTemplateListener)  ![Project-Level][project-level] | [`GitCommitTemplateListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitCommitTemplateTracker.kt) |
| [GitConfigListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitConfigListener)  ![Project-Level][project-level] | [`GitConfigListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitConfigListener.kt) |
| [GitRepository#GIT_REPO_CHANGE](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryChangeListener)  | [`GitRepositoryChangeListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitRepositoryChangeListener.java) |
| [GitRefreshListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitRefreshListener)  | [`GitRefreshListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitRefreshListener.java) |
| [GitStagingAreaHolder#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitStagingAreaHolder.StagingAreaListener)  | [`StagingAreaListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitStagingAreaHolder.java) |
| [RootsAndBranches#REFRESH_REQUEST](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [SvnVcs#WC_CONVERTED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [EditorConfigSettings#EDITOR_CONFIG_ENABLED_TOPIC](https://jb.gg/ipe/listeners?topics=org.editorconfig.settings.EditorConfigListener)  | [`EditorConfigListener`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigListener.java) |
| [FontChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownApplicationSettings.FontChangedListener)  ![Deprecated][deprecated] | [`FontChangedListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownApplicationSettings.java) |
| [SettingsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownApplicationSettings.SettingsChangedListener)  ![Deprecated][deprecated] | [`SettingsChangedListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownApplicationSettings.java) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownExtensionsSettings.ChangeListener)  ![Experimental API][experimental] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownExtensionsSettings.kt) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettings.kt) |
| [MavenIndex#INDEX_IS_BROKEN](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.MavenSearchIndex.IndexListener)  | [`IndexListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenSearchIndex.java) |
| [MavenImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenImportListener)  ![Project-Level][project-level] | [`MavenImportListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenImportListener.java) |
| [MavenImportingManager#LEGACY_PROJECT_MANAGER_LISTENER](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenProjectsManager.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenProjectsManager.java) |
| [MavenServerConnector#DOWNLOAD_LISTENER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.server.MavenServerDownloadListener)  | [`MavenServerDownloadListener`](%gh-ic%/plugins/maven-server-api/src/main/java/org/jetbrains/idea/maven/server/MavenServerDownloadListener.java) |
| [Merger#COMMITTED_CHANGES_MERGED_STATE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.integrate.Merger.CommittedChangesMergedStateChanged)  | [`CommittedChangesMergedStateChanged`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/Merger.java) |
| [SvnMergeInfoCache#SVN_MERGE_INFO_CACHE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.mergeinfo.SvnMergeInfoCache.SvnMergeInfoCacheListener)  | [`SvnMergeInfoCacheListener`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/mergeinfo/SvnMergeInfoCache.java) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.base.projectStructure.LibraryInfoListener)  ![Internal API][internal] ![Project-Level][project-level] | [`LibraryInfoListener`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/LibraryInfoCache.kt) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.compiler.configuration.KotlinCompilerSettingsListener)  ![Project-Level][project-level] | [`KotlinCompilerSettingsListener`](%gh-ic%/plugins/kotlin/base/compiler-configuration/src/org/jetbrains/kotlin/idea/compiler/configuration/BaseKotlinCompilerSettings.kt) |
| [KotlinBundledUsageDetector#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.macros.KotlinBundledUsageDetectorListener)  ![Project-Level][project-level] | [`KotlinBundledUsageDetectorListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/macros/KotlinBundledUsageDetectorListener.kt) |
| [Companion#EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringEventListener)  | [`KotlinRefactoringEventListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/RefactoringEventListenerEx.kt) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.scratch.ScratchFileListener)  ![Project-Level][project-level] | [`ScratchFileListener`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFile.kt) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.pullrequest.data.provider.GHPRDataOperationsListener)  | [`GHPRDataOperationsListener`](%gh-ic%/plugins/github/src/org/jetbrains/plugins/github/pullrequest/data/provider/GHPRDataOperationsListener.kt) |
| [GHProjectRepositoriesManager#LIST_CHANGES_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.util.GHProjectRepositoriesManager.ListChangeListener)  | [`ListChangeListener`](%gh-ic%/plugins/github/src/org/jetbrains/plugins/github/util/GHProjectRepositoriesManager.kt) |
| [GradleSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.settings.GradleSettingsListener)  | [`GradleSettingsListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/settings/GradleSettingsListener.java) |
| [GradleUiListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.ui.GradleUiListener)  | [`GradleUiListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/ui/GradleUiListener.java) |
| [TerminalCommandHandlerCustomizer#TERMINAL_COMMAND_HANDLER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.TerminalCommandHandlerCustomizer.TerminalCommandHandlerListener)  | [`TerminalCommandHandlerListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalCommandHandlerCustomizer.kt) |
| [HgVcs#REMOTE_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#STATUS_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#INCOMING_OUTGOING_CHECK_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.status.ui.HgWidgetUpdater)  | [`HgWidgetUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/status/ui/HgWidgetUpdater.java) |
| [Companion#TOPIC](https://jb.gg/ipe/listeners?topics=training.featuresSuggester.FeatureSuggestersManagerListener)  | [`FeatureSuggestersManagerListener`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/FeatureSuggestersManagerListener.kt) |


### AntSupport

[`AntSupport`](%gh-ic%/plugins/ant/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [AntSupport.AntMessageCustomizer](https://jb.gg/ipe?extensions=AntSupport.AntMessageCustomizer) | [`AntMessageCustomizer`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntMessageCustomizer.java) |

### ByteCodeViewer

[`ByteCodeViewer`](%gh-ic%/plugins/ByteCodeViewer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [ByteCodeViewer.classSearcher](https://jb.gg/ipe?extensions=ByteCodeViewer.classSearcher) | [`ClassSearcher`](%gh-ic%/plugins/ByteCodeViewer/src/com/intellij/byteCodeViewer/ClassSearcher.java) |

### com.intellij.completion.evaluation

[`com.intellij.completion.evaluation`](%gh-ic%/plugins/evaluation-plugin/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cce.codeCompletionHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.cce.codeCompletionHandlerFactory) ![Project-Level][project-level] | [`CodeCompletionHandlerFactory`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/CodeCompletionHandlerFactory.kt) |
| [com.intellij.cce.completionEvaluationVisitor](https://jb.gg/ipe?extensions=com.intellij.cce.completionEvaluationVisitor) | [`CompletionEvaluationVisitor`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/visitor/CompletionEvaluationVisitor.kt) |
| [com.intellij.cce.openProjectMethodProvider](https://jb.gg/ipe?extensions=com.intellij.cce.openProjectMethodProvider) | [`OpenProjectMethodProvider`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/actions/OpenProjectMethodProvider.kt) |
| [com.intellij.cce.setupSdkStep](https://jb.gg/ipe?extensions=com.intellij.cce.setupSdkStep) ![Project-Level][project-level] | [`SetupSdkStep`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/SetupSdkStep.kt) |

### com.intellij.completion.ml.ranking

[`com.intellij.completion.ml.ranking`](%gh-ic%/plugins/completion-ml-ranking/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.completion.ml.additionalContextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.additionalContextFeatures) ![Internal API][internal] | [`AdditionalContextFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/AdditionalContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementIdProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementIdProvider) ![Internal API][internal] | [`LookupElementIdProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/util/LookupElementIdProvider.kt) |
| [com.intellij.completion.ml.featuresOverride](https://jb.gg/ipe?extensions=com.intellij.completion.ml.featuresOverride) ![Internal API][internal] | [`RankingFeaturesOverrides`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/RankingFeaturesOverrides.kt) |
| [com.intellij.completion.ml.localModelProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.localModelProvider) ![Internal API][internal] | [`LocalZipModelProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/ranker/local/LocalZipModelProvider.kt) |
| [com.intellij.completion.ml.lookupFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.lookupFeatures) ![Internal API][internal] | [`LookupFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/LookupFeatureProvider.java) |
| [com.intellij.completion.ml.ranking.features.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.features.policy) ![Internal API][internal] | [`CompletionFeaturesPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/CompletionFeaturesPolicy.kt) |
| [com.intellij.completion.ml.ranking.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.policy) ![Experimental API][experimental] | [`CompletionMLPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/CompletionMLPolicy.kt) |

### com.intellij.copyright

[`com.intellij.copyright`](%gh-ic%/plugins/copyright/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.copyright.updater](https://jb.gg/ipe?extensions=com.intellij.copyright.updater) | [`UpdateCopyrightsProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/psi/UpdateCopyrightsProvider.java) |
| [com.intellij.copyright.variablesProvider](https://jb.gg/ipe?extensions=com.intellij.copyright.variablesProvider) | [`CopyrightVariablesProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/pattern/CopyrightVariablesProvider.java) |

### com.intellij.gradle

[`com.intellij.gradle`](%gh-ic%/plugins/gradle/plugin-resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.executionEnvironmentProvider) | [`GradleExecutionEnvironmentProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/build/GradleExecutionEnvironmentProvider.java) |
| [org.jetbrains.plugins.gradle.importCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.importCustomizer) | [`GradleImportCustomizer`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleImportCustomizer.java) |
| [org.jetbrains.plugins.gradle.issueChecker](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.issueChecker) ![Experimental API][experimental] | [`GradleIssueChecker`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/issue/GradleIssueChecker.kt) |
| [org.jetbrains.plugins.gradle.operationHelperExtension](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.operationHelperExtension) ![Experimental API][experimental] | [`GradleOperationHelperExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleOperationHelperExtension.kt) |
| [org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory) | [`FactoryImpl`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/GradleOrderEnumeratorHandler.java) |
| [org.jetbrains.plugins.gradle.projectModelContributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.projectModelContributor) ![Experimental API][experimental] | [`ProjectModelContributor`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/ProjectModelContributor.java) |
| [org.jetbrains.plugins.gradle.projectResolve](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.projectResolve) | [`GradleProjectResolverExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleProjectResolverExtension.java) |
| [org.jetbrains.plugins.gradle.settingsControlProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.settingsControlProvider) | [`GradleSettingsControlProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/settings/GradleSettingsControlProvider.java) |
| [org.jetbrains.plugins.gradle.targetEnvironmentAware](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.targetEnvironmentAware) ![Internal API][internal] | [`GradleTargetEnvironmentAware`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/target/GradleTargetEnvironmentAware.java) |
| [org.jetbrains.plugins.gradle.taskManager](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.taskManager) | [`GradleTaskManagerExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/task/GradleTaskManagerExtension.java) |
| [org.jetbrains.plugins.gradle.taskResultListener](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.taskResultListener) ![Internal API][internal] | [`GradleTaskResultListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/task/GradleTaskResultListener.java) |

### com.intellij.java-i18n

[`com.intellij.java-i18n`](%gh-ic%/plugins/java-i18n/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.java-i18n.i18nizeHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.java-i18n.i18nizeHandlerProvider) | [`I18nizeHandlerProvider`](%gh-ic%/plugins/java-i18n/src/com/intellij/codeInspection/i18n/I18nizeHandlerProvider.java) |
| [com.intellij.java-i18n.resourceBundleManager](https://jb.gg/ipe?extensions=com.intellij.java-i18n.resourceBundleManager) ![Project-Level][project-level] | [`ResourceBundleManager`](%gh-ic%/plugins/java-i18n/src/com/intellij/lang/properties/psi/ResourceBundleManager.java) |

### com.intellij.properties

[`com.intellij.properties`](%gh-ic%/plugins/properties/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.properties.alphaUnsortedInspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.alphaUnsortedInspectionSuppressor) | [`AlphaUnsortedPropertiesFileInspectionSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unsorted/AlphaUnsortedPropertiesFileInspectionSuppressor.java) |
| [com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor) | [`DuplicatePropertyKeyAnnotationSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/DuplicatePropertyKeyAnnotationSuppressor.java) |
| [com.intellij.properties.implicitPropertyUsageProvider](https://jb.gg/ipe?extensions=com.intellij.properties.implicitPropertyUsageProvider) | [`ImplicitPropertyUsageProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ImplicitPropertyUsageProvider.java) |

### com.intellij.searcheverywhere.ml

[`com.intellij.searcheverywhere.ml`](%gh-ic%/plugins/search-everywhere-ml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searcheverywhere.ml.elementKeyForIdProvider](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.elementKeyForIdProvider) | [`ElementKeyForIdProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/id/ElementKeyForIdProvider.kt) |
| [com.intellij.searcheverywhere.ml.rankingModelLoader](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.rankingModelLoader) | [`SearchEverywhereMLRankingModelLoader`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/model/SearchEverywhereMLRankingModelLoader.kt) |
| [com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider) ![Internal API][internal] | [`SearchEverywhereElementFeaturesProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/features/SearchEverywhereElementFeaturesProvider.kt) |

### com.intellij.settingsSync

[`com.intellij.settingsSync`](%gh-ic%/plugins/settings-sync/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.settingsSyncMigration](https://jb.gg/ipe?extensions=com.intellij.settingsSyncMigration) ![Internal API][internal] | [`SettingsSyncMigration`](%gh-ic%/plugins/settings-sync/src/com/intellij/settingsSync/SettingsSyncMigration.kt) |

### com.intellij.stats.completion

[`com.intellij.stats.completion`](%gh-ic%/plugins/stats-collector/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.stats.completion.policy](https://jb.gg/ipe?extensions=com.intellij.stats.completion.policy) ![Internal API][internal] | [`CompletionStatsPolicy`](%gh-ic%/plugins/stats-collector/src/com/intellij/stats/completion/CompletionStatsPolicy.kt) |

### com.intellij.tasks

[`com.intellij.tasks`](%gh-ic%/plugins/tasks/tasks-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tasks.commitPlaceholderProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.commitPlaceholderProvider) | [`CommitPlaceholderProvider`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/CommitPlaceholderProvider.java) |
| [com.intellij.tasks.dialogPanelProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.dialogPanelProvider) ![Non-Dynamic][non-dynamic] | [`TaskDialogPanelProvider`](%gh-ic%/plugins/tasks/tasks-api/src/com/intellij/tasks/ui/TaskDialogPanelProvider.java) |
| [com.intellij.tasks.repositoryType](https://jb.gg/ipe?extensions=com.intellij.tasks.repositoryType) | [`TaskRepositoryType`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/TaskRepositoryType.java) |

### com.intellij.uiDesigner

[`com.intellij.uiDesigner`](%gh-ic%/plugins/ui-designer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.paletteItemProvider](https://jb.gg/ipe?extensions=com.intellij.paletteItemProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`PaletteItemProvider`](%gh-ic%/plugins/ui-designer/src/com/intellij/ide/palette/PaletteItemProvider.java) |
| [com.intellij.uiDesigner.formInspectionTool](https://jb.gg/ipe?extensions=com.intellij.uiDesigner.formInspectionTool) ![Non-Dynamic][non-dynamic] | [`FormInspectionTool`](%gh-ic%/plugins/ui-designer/src/com/intellij/uiDesigner/inspections/FormInspectionTool.java) |

### com.jetbrains.filePrediction

[`com.jetbrains.filePrediction`](%gh-ic%/plugins/filePrediction/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.filePrediction.candidateProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.candidateProvider) ![Internal API][internal] | [`FilePredictionCandidateProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/candidates/FilePredictionCandidateProvider.kt) |
| [com.intellij.filePrediction.featureProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.featureProvider) ![Internal API][internal] | [`FilePredictionFeatureProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/features/FilePredictionFeatureProvider.kt) |
| [com.intellij.filePrediction.ml.model](https://jb.gg/ipe?extensions=com.intellij.filePrediction.ml.model) ![Internal API][internal] | [`FilePredictionModelProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/predictor/model/FilePredictionModelProvider.kt) |
| [com.intellij.filePrediction.referencesProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.referencesProvider) ![Internal API][internal] | [`FileExternalReferencesProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/references/FilePredictionReferencesHelper.kt) |

### com.jetbrains.packagesearch.intellij-plugin

[`com.jetbrains.packagesearch.intellij-plugin`](%gh-ic%/plugins/package-search/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.packagesearch.asyncModuleTransformer](https://jb.gg/ipe?extensions=com.intellij.packagesearch.asyncModuleTransformer) ![Project-Level][project-level] | [`AsyncModuleTransformer`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/AsyncModuleTransformer.kt) |
| [com.intellij.packagesearch.asyncProjectModuleOperationProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.asyncProjectModuleOperationProvider) | [`AsyncProjectModuleOperationProvider`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/AsyncProjectModuleOperationProvider.kt) |
| [com.intellij.packagesearch.configurableContributor](https://jb.gg/ipe?extensions=com.intellij.packagesearch.configurableContributor) ![Project-Level][project-level] | [`ConfigurableContributor`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ConfigurableContributor.kt) |
| [com.intellij.packagesearch.coroutineModuleTransformer](https://jb.gg/ipe?extensions=com.intellij.packagesearch.coroutineModuleTransformer) ![Project-Level][project-level] | [`CoroutineModuleTransformer`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/CoroutineModuleTransformer.kt) |
| [com.intellij.packagesearch.coroutineProjectModuleOperationProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.coroutineProjectModuleOperationProvider) | [`CoroutineProjectModuleOperationProvider`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/CoroutineProjectModuleOperationProvider.kt) |
| [com.intellij.packagesearch.flowModuleChangesSignalProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.flowModuleChangesSignalProvider) ![Project-Level][project-level] | [`FlowModuleChangesSignalProvider`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/FlowModuleChangesSignalProvider.kt) |
| [com.intellij.packagesearch.moduleChangesSignalProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.moduleChangesSignalProvider) ![Project-Level][project-level] | [`ModuleChangesSignalProvider`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleChangesSignalProvider.kt) |
| [com.intellij.packagesearch.moduleTransformer](https://jb.gg/ipe?extensions=com.intellij.packagesearch.moduleTransformer) ![Deprecated][deprecated] ![Project-Level][project-level] | [`ModuleTransformer`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleTransformer.kt) |
| [com.intellij.packagesearch.projectModuleOperationProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.projectModuleOperationProvider) ![Deprecated][deprecated] | [`ProjectModuleOperationProvider`](%gh-ic%/plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ProjectModuleOperationProvider.kt) |

### completion-fe10.xml

[`completion-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/completion-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.completionExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.completionExtension) | [`KotlinCompletionExtension`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/completion/KotlinCompletionExtension.kt) |

### Coverage

[`Coverage`](%gh-ic%/plugins/coverage/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.javaCoverageEngineExtension](https://jb.gg/ipe?extensions=com.intellij.javaCoverageEngineExtension) | [`JavaCoverageEngineExtension`](%gh-ic%/plugins/coverage/src/com/intellij/coverage/JavaCoverageEngineExtension.java) |

### coverage-common-plugin.xml

[`coverage-common-plugin.xml`](%gh-ic%/plugins/coverage-common/src/META-INF/coverage-common-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.coverageEngine](https://jb.gg/ipe?extensions=com.intellij.coverageEngine) | [`CoverageEngine`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageEngine.java) |
| [com.intellij.coverageOptions](https://jb.gg/ipe?extensions=com.intellij.coverageOptions) ![Project-Level][project-level] | [`CoverageOptions`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageOptions.java) |
| [com.intellij.coverageRunner](https://jb.gg/ipe?extensions=com.intellij.coverageRunner) | [`CoverageRunner`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageRunner.java) |

### DesignerCorePlugin.xml

[`DesignerCorePlugin.xml`](%gh-ic%/plugins/ui-designer-core/src/META-INF/DesignerCorePlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Designer.customizations](https://jb.gg/ipe?extensions=Designer.customizations) ![Non-Dynamic][non-dynamic] | [`DesignerCustomizations`](%gh-ic%/plugins/ui-designer-core/src/com/intellij/designer/DesignerCustomizations.java) |

### extensions.xml

[`extensions.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/extensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.buildSystem.kotlin](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.buildSystem.kotlin) | `n/a` |
| [org.jetbrains.kotlin.codeinsight.quickfix.registrar](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.codeinsight.quickfix.registrar) | [`KotlinQuickFixRegistrar`](%gh-ic%/plugins/kotlin/code-insight/api/src/org/jetbrains/kotlin/idea/codeinsight/api/applicators/fixes/KotlinQuickFixService.kt) |

### file-types.xml

[`file-types.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/file-types.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.binaryExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.binaryExtension) | [`KotlinBinaryExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinBinaryExtension.kt) |

### Git4Idea

[`Git4Idea`](%gh-ic%/plugins/git4idea/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Git4Idea.GitCheckinExplicitMovementProvider](https://jb.gg/ipe?extensions=Git4Idea.GitCheckinExplicitMovementProvider) | [`GitCheckinExplicitMovementProvider`](%gh-ic%/plugins/git4idea/src/git4idea/checkin/GitCheckinExplicitMovementProvider.java) |
| [Git4Idea.GitHttpAuthDataProvider](https://jb.gg/ipe?extensions=Git4Idea.GitHttpAuthDataProvider) | [`GitHttpAuthDataProvider`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitHttpAuthDataProvider.java) |
| [Git4Idea.gitFetchHandler](https://jb.gg/ipe?extensions=Git4Idea.gitFetchHandler) | [`GitFetchHandler`](%gh-ic%/plugins/git4idea/src/git4idea/fetch/GitFetchHandler.kt) |
| [Git4Idea.gitProtectedBranchProvider](https://jb.gg/ipe?extensions=Git4Idea.gitProtectedBranchProvider) | [`GitProtectedBranchProvider`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitProtectedBranchProvider.kt) |
| [Git4Idea.gitRepositoryHostingService](https://jb.gg/ipe?extensions=Git4Idea.gitRepositoryHostingService) | [`GitRepositoryHostingService`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitRepositoryHostingService.java) |

### gradle-groovy-integration.xml

[`gradle-groovy-integration.xml`](%gh-ic%/plugins/gradle/java/resources/META-INF/gradle-groovy-integration.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.pluginDescriptions](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.pluginDescriptions) | [`GradlePluginDescriptionsExtension`](%gh-ic%/plugins/gradle/java/src/codeInsight/GradlePluginDescriptionsExtension.java) |
| [org.jetbrains.plugins.gradle.resolve.contributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.resolve.contributor) | [`GradleMethodContextContributor`](%gh-ic%/plugins/gradle/java/src/service/resolve/GradleMethodContextContributor.java) |

### groovy-support.xml

[`groovy-support.xml`](%gh-ic%/plugins/maven/src/main/resources/META-INF/groovy-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.maven.importing.groovy.foldersConfiguratorContributor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.groovy.foldersConfiguratorContributor) | [`PluginContributor`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/plugins/groovy/GroovyPluginConfigurator.kt) |

### highlighting-fe10.xml

[`highlighting-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/highlighting-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.highlighterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.highlighterExtension) | [`KotlinHighlightingVisitorExtension`](%gh-ic%/plugins/kotlin/base/fe10/highlighting/src/org/jetbrains/kotlin/idea/highlighter/KotlinHighlightingVisitorExtension.kt) |

### highlighting.xml

[`highlighting.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/highlighting.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.beforeResolveHighlightingVisitor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.beforeResolveHighlightingVisitor) ![Internal API][internal] | [`BeforeResolveHighlightingExtension`](%gh-ic%/plugins/kotlin/base/highlighting/src/org/jetbrains/kotlin/idea/base/highlighting/KotlinBeforeResolveHighlightingPass.kt) |

### InspectionGadgets.xml

[`InspectionGadgets.xml`](%gh-ic%/plugins/InspectionGadgets/src/META-INF/InspectionGadgets.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.implicit.resource.closer](https://jb.gg/ipe?extensions=com.intellij.implicit.resource.closer) | [`ImplicitResourceCloser`](%gh-ic%/plugins/InspectionGadgets/InspectionGadgetsAnalysis/src/com/intellij/codeInspection/resources/ImplicitResourceCloser.java) |
| [com.intellij.naming.convention.class](https://jb.gg/ipe?extensions=com.intellij.naming.convention.class) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.field](https://jb.gg/ipe?extensions=com.intellij.naming.convention.field) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.method](https://jb.gg/ipe?extensions=com.intellij.naming.convention.method) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |

### intellij.dev.psiViewer.xml

[`intellij.dev.psiViewer.xml`](%gh-ic%/plugins/intellij.dev.psiViewer/resources/intellij.dev.psiViewer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dev.psiViewer.extension](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.extension) | [`PsiViewerExtension`](%gh-ic%/plugins/intellij.dev.psiViewer/src/PsiViewerExtension.java) |

### intellij.devkit.core.xml

[`intellij.devkit.core.xml`](%gh-ic%/plugins/devkit/devkit-core/resources/intellij.devkit.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [DevKit.lang.goodCodeRedVisitor](https://jb.gg/ipe?extensions=DevKit.lang.goodCodeRedVisitor) | [`GoodCodeRedVisitor`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/internal/GoodCodeRedVisitor.java) |

### intellij.java.remoteServers.impl.xml

[`intellij.java.remoteServers.impl.xml`](%gh-ic%/java/remote-servers/impl/src/intellij.java.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.moduleBuilderContribution](https://jb.gg/ipe?extensions=com.intellij.remoteServer.moduleBuilderContribution) ![Non-Dynamic][non-dynamic] | [`CloudModuleBuilderContributionFactory`](%gh-ic%/java/remote-servers/impl/src/com/intellij/remoteServer/impl/module/CloudModuleBuilderContributionFactory.java) |

### JavaAnalysisPlugin.xml

[`JavaAnalysisPlugin.xml`](%gh-ic%/java/java-analysis-impl/src/META-INF/JavaAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allowedApiFilter](https://jb.gg/ipe?extensions=com.intellij.allowedApiFilter) | [`AllowedApiFilterExtension`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/impl/AllowedApiFilterExtension.java) |
| [com.intellij.canBeFinal](https://jb.gg/ipe?extensions=com.intellij.canBeFinal) | [`CanBeFinalHandler`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/canBeFinal/CanBeFinalHandler.java) |
| [com.intellij.codeInsight.changeVariableTypeQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.changeVariableTypeQuickFixProvider) | [`ChangeVariableTypeQuickFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInsight/quickfix/ChangeVariableTypeQuickFixProvider.java) |
| [com.intellij.codeInsight.implicitSubclassProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implicitSubclassProvider) | [`ImplicitSubclassProvider`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/inheritance/ImplicitSubclassProvider.kt) |
| [com.intellij.deadCode](https://jb.gg/ipe?extensions=com.intellij.deadCode) | [`EntryPoint`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| [com.intellij.java.error.fix](https://jb.gg/ipe?extensions=com.intellij.java.error.fix) | [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) |
| [com.intellij.javaLanguageLevelPusherCustomizer](https://jb.gg/ipe?extensions=com.intellij.javaLanguageLevelPusherCustomizer) ![Internal API][internal] | [`JavaLanguageLevelPusherCustomizer`](%gh-ic%/java/java-analysis-impl/src/com/intellij/openapi/roots/impl/JavaLanguageLevelPusherCustomizer.java) |
| [com.intellij.lang.jvm.actions.jvmElementActionsFactory](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.actions.jvmElementActionsFactory) | [`JvmElementActionsFactory`](%gh-ic%/java/java-analysis-api/src/com/intellij/lang/jvm/actions/JvmElementActionsFactory.kt) |
| [com.intellij.lang.jvm.annotationPackageSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.annotationPackageSupport) | [`AnnotationPackageSupport`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/annoPackages/AnnotationPackageSupport.java) |
| [com.intellij.lang.jvm.ignoreAnnotationParamSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.ignoreAnnotationParamSupport) | [`IgnoreAnnotationParamSupport`](%gh-ic%/java/java-impl-inspections/src/com/intellij/codeInspection/DefaultAnnotationParamInspection.java) |
| [com.intellij.visibility](https://jb.gg/ipe?extensions=com.intellij.visibility) | [`VisibilityExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/visibility/VisibilityExtension.java) |

### JavaIndexingPlugin.xml

[`JavaIndexingPlugin.xml`](%gh-ic%/java/java-indexing-impl/src/META-INF/JavaIndexingPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allClassesSearch](https://jb.gg/ipe?extensions=com.intellij.allClassesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.allOverridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.allOverridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedElementsSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedElementsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedPackagesSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedPackagesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.classInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classesWithAnnotatedMembersSearch](https://jb.gg/ipe?extensions=com.intellij.classesWithAnnotatedMembersSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.customPropertyScopeProvider](https://jb.gg/ipe?extensions=com.intellij.customPropertyScopeProvider) | [`CustomPropertyScopeProvider`](%gh-ic%/java/java-indexing-impl/src/com/intellij/psi/impl/search/CustomPropertyScopeProvider.java) |
| [com.intellij.directClassInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.directClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.functionalExpressionSearch](https://jb.gg/ipe?extensions=com.intellij.functionalExpressionSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.implicitToStringSearch](https://jb.gg/ipe?extensions=com.intellij.implicitToStringSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.java.shortNamesCache](https://jb.gg/ipe?extensions=com.intellij.java.shortNamesCache) ![Project-Level][project-level] | [`PsiShortNamesCache`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java) |
| [com.intellij.methodReferencesSearch](https://jb.gg/ipe?extensions=com.intellij.methodReferencesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.overridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.overridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### JavaPlugin.xml

[`JavaPlugin.xml`](%gh-ic%/java/java-impl/src/META-INF/JavaPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.OrderRootTypeUI](https://jb.gg/ipe?extensions=com.intellij.OrderRootTypeUI) | [`OrderRootTypeUIFactory`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/OrderRootTypeUIFactory.java) |
| [com.intellij.attachSourcesProvider](https://jb.gg/ipe?extensions=com.intellij.attachSourcesProvider) | [`AttachSourcesProvider`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/AttachSourcesProvider.java) |
| [com.intellij.buildProcess.parametersProvider](https://jb.gg/ipe?extensions=com.intellij.buildProcess.parametersProvider) ![Project-Level][project-level] | [`BuildProcessParametersProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildProcessParametersProvider.java) |
| [com.intellij.canBeEmpty](https://jb.gg/ipe?extensions=com.intellij.canBeEmpty) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeInsight.createFieldFromUsageHelper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.createFieldFromUsageHelper) | [`CreateFieldFromUsageHelper`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/CreateFieldFromUsageHelper.java) |
| [com.intellij.codeInsight.externalLibraryResolver](https://jb.gg/ipe?extensions=com.intellij.codeInsight.externalLibraryResolver) | [`ExternalLibraryResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/quickFix/ExternalLibraryResolver.java) |
| [com.intellij.compilableFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.compilableFileTypesProvider) ![Project-Level][project-level] | [`CompilableFileTypesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilableFileTypesProvider.java) |
| [com.intellij.compileServer.plugin](https://jb.gg/ipe?extensions=com.intellij.compileServer.plugin) | `n/a` |
| [com.intellij.compiler](https://jb.gg/ipe?extensions=com.intellij.compiler) ![Deprecated][deprecated] ![Project-Level][project-level] | [`Compiler`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/Compiler.java) |
| [com.intellij.compiler.buildIssueContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.buildIssueContributor) ![Experimental API][experimental] | [`BuildIssueContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/progress/BuildIssueContributor.java) |
| [com.intellij.compiler.buildTargetScopeProvider](https://jb.gg/ipe?extensions=com.intellij.compiler.buildTargetScopeProvider) | [`BuildTargetScopeProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) |
| [com.intellij.compiler.inspectionValidator](https://jb.gg/ipe?extensions=com.intellij.compiler.inspectionValidator) ![Project-Level][project-level] | [`InspectionValidator`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/util/InspectionValidator.java) |
| [com.intellij.compiler.isUpToDateCheckConsumer](https://jb.gg/ipe?extensions=com.intellij.compiler.isUpToDateCheckConsumer) | [`IsUpToDateCheckConsumer`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/IsUpToDateCheckConsumer.java) |
| [com.intellij.compiler.optionsManager](https://jb.gg/ipe?extensions=com.intellij.compiler.optionsManager) | [`CompilerOptionsFilter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/options/CompilerOptionsFilter.java) |
| [com.intellij.compiler.task](https://jb.gg/ipe?extensions=com.intellij.compiler.task) ![Project-Level][project-level] | [`CompileTask`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompileTask.java) |
| [com.intellij.compiler.updateResourcesBuildContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.updateResourcesBuildContributor) | [`UpdateResourcesBuildContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/UpdateResourcesBuildContributor.java) |
| [com.intellij.compilerFactory](https://jb.gg/ipe?extensions=com.intellij.compilerFactory) ![Deprecated][deprecated] ![Project-Level][project-level] | [`CompilerFactory`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilerFactory.java) |
| [com.intellij.configuration.ModuleStructureExtension](https://jb.gg/ipe?extensions=com.intellij.configuration.ModuleStructureExtension) | [`ModuleStructureExtension`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureExtension.java) |
| [com.intellij.constructorBodyGenerator](https://jb.gg/ipe?extensions=com.intellij.constructorBodyGenerator) ![Internal API][internal] | [`ConstructorBodyGenerator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/ConstructorBodyGenerator.java) |
| [com.intellij.conversion.rule](https://jb.gg/ipe?extensions=com.intellij.conversion.rule) | [`TypeConversionRule`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/typeMigration/rules/TypeConversionRule.java) |
| [com.intellij.debugger.asyncStackTraceProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.asyncStackTraceProvider) | [`AsyncStackTraceProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/AsyncStackTraceProvider.java) |
| [com.intellij.debugger.codeFragmentFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.codeFragmentFactory) | [`CodeFragmentFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/CodeFragmentFactory.java) |
| [com.intellij.debugger.compoundRendererProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.compoundRendererProvider) | [`CompoundRendererProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/CompoundRendererProvider.java) |
| [com.intellij.debugger.dfaAssistProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.dfaAssistProvider) | [`DfaAssistProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/dfaassist/DfaAssistProvider.java) |
| [com.intellij.debugger.extraSteppingFilter](https://jb.gg/ipe?extensions=com.intellij.debugger.extraSteppingFilter) | [`ExtraSteppingFilter`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/ExtraSteppingFilter.java) |
| [com.intellij.debugger.frameExtraVarsProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.frameExtraVarsProvider) | [`FrameExtraVariablesProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/FrameExtraVariablesProvider.java) |
| [com.intellij.debugger.javaBreakpointHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.javaBreakpointHandlerFactory) | [`JavaBreakpointHandlerFactory`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/JavaBreakpointHandlerFactory.java) |
| [com.intellij.debugger.javaDebugAware](https://jb.gg/ipe?extensions=com.intellij.debugger.javaDebugAware) | [`JavaDebugAware`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/JavaDebugAware.java) |
| [com.intellij.debugger.jvmSmartStepIntoHandler](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSmartStepIntoHandler) | [`JvmSmartStepIntoHandler`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/actions/JvmSmartStepIntoHandler.java) |
| [com.intellij.debugger.jvmSteppingCommandProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSteppingCommandProvider) | [`JvmSteppingCommandProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/JvmSteppingCommandProvider.java) |
| [com.intellij.debugger.nodeNameAdjuster](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeNameAdjuster) | [`NodeDescriptorNameAdjuster`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/ui/tree/NodeDescriptorNameAdjuster.java) |
| [com.intellij.debugger.nodeRenderer](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeRenderer) | [`NodeRenderer`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/NodeRenderer.java) |
| [com.intellij.debugger.positionManagerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.positionManagerFactory) | [`PositionManagerFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/PositionManagerFactory.java) |
| [com.intellij.debugger.simplePropertyGetterProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.simplePropertyGetterProvider) | [`SimplePropertyGetterProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SimplePropertyGetterProvider.java) |
| [com.intellij.debugger.sourcePositionHighlighter](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionHighlighter) | [`SourcePositionHighlighter`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SourcePositionHighlighter.java) |
| [com.intellij.debugger.sourcePositionProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionProvider) | [`SourcePositionProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/SourcePositionProvider.java) |
| [com.intellij.debugger.syntheticProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.syntheticProvider) | [`SyntheticTypeComponentProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SyntheticTypeComponentProvider.java) |
| [com.intellij.debuggerClassFilterProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerClassFilterProvider) | [`DebuggerClassFilterProvider`](%gh-ic%/java/openapi/src/com/intellij/ui/classFilter/DebuggerClassFilterProvider.java) |
| [com.intellij.debuggerEditorTextProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerEditorTextProvider) | [`EditorTextProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/EditorTextProvider.java) |
| [com.intellij.documentationDelegateProvider](https://jb.gg/ipe?extensions=com.intellij.documentationDelegateProvider) | [`DocumentationDelegateProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/javadoc/DocumentationDelegateProvider.java) |
| [com.intellij.encapsulateFields.Helper](https://jb.gg/ipe?extensions=com.intellij.encapsulateFields.Helper) | [`EncapsulateFieldHelper`](%gh-ic%/java/openapi/src/com/intellij/refactoring/encapsulateFields/EncapsulateFieldHelper.java) |
| [com.intellij.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.exceptionFilter) | [`ExceptionFilterFactory`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/ExceptionFilterFactory.java) |
| [com.intellij.externalAnnotationsArtifactsResolver](https://jb.gg/ipe?extensions=com.intellij.externalAnnotationsArtifactsResolver) | [`ExternalAnnotationsArtifactsResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/ExternalAnnotationsArtifactsResolver.java) |
| [com.intellij.framework.type](https://jb.gg/ipe?extensions=com.intellij.framework.type) | [`FrameworkTypeEx`](%gh-ic%/java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java) |
| [com.intellij.frameworkSupport](https://jb.gg/ipe?extensions=com.intellij.frameworkSupport) | [`FrameworkSupportProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/frameworkSupport/FrameworkSupportProvider.java) |
| [com.intellij.frameworkSupportCommunicator](https://jb.gg/ipe?extensions=com.intellij.frameworkSupportCommunicator) | [`FrameworkSupportCommunicator`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/newProjectWizard/impl/FrameworkSupportCommunicator.java) |
| [com.intellij.generateAccessorProvider](https://jb.gg/ipe?extensions=com.intellij.generateAccessorProvider) | [`NotNullFunction`](%gh-ic%/platform/util-rt/src/com/intellij/util/NotNullFunction.java) |
| [com.intellij.generation.toStringClassFilter](https://jb.gg/ipe?extensions=com.intellij.generation.toStringClassFilter) | [`GenerateToStringClassFilter`](%gh-ic%/plugins/generate-tostring/src/org/jetbrains/generate/tostring/GenerateToStringClassFilter.java) |
| [com.intellij.getterSetterProvider](https://jb.gg/ipe?extensions=com.intellij.getterSetterProvider) | [`GetterSetterPrototypeProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/GetterSetterPrototypeProvider.java) |
| [com.intellij.hierarchy.referenceProcessor](https://jb.gg/ipe?extensions=com.intellij.hierarchy.referenceProcessor) | [`CallReferenceProcessor`](%gh-ic%/java/openapi/src/com/intellij/ide/hierarchy/call/CallReferenceProcessor.java) |
| [com.intellij.jarRepositoryAuthenticationDataProvider](https://jb.gg/ipe?extensions=com.intellij.jarRepositoryAuthenticationDataProvider) ![Experimental API][experimental] | [`JarRepositoryAuthenticationDataProvider`](%gh-ic%/java/idea-ui/src/com/intellij/jarRepository/JarRepositoryAuthenticationDataProvider.kt) |
| [com.intellij.java.compiler](https://jb.gg/ipe?extensions=com.intellij.java.compiler) ![Project-Level][project-level] | [`BackendCompiler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/javaCompiler/BackendCompiler.java) |
| [com.intellij.java.effectively.final.fixer](https://jb.gg/ipe?extensions=com.intellij.java.effectively.final.fixer) ![Internal API][internal] | [`EffectivelyFinalFixer`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/makefinal/EffectivelyFinalFixer.java) |
| [com.intellij.java.externalAnnotation](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation) | [`AnnotationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/AnnotationProvider.java) |
| [com.intellij.java.externalAnnotation.locationProvider](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation.locationProvider) | [`AnnotationsLocationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/location/AnnotationsLocationProvider.java) |
| [com.intellij.java.inspection.bulkMethodInfo](https://jb.gg/ipe?extensions=com.intellij.java.inspection.bulkMethodInfo) | [`BulkMethodInfoProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInspection/bulkOperation/BulkMethodInfoProvider.java) |
| [com.intellij.java.programPatcher](https://jb.gg/ipe?extensions=com.intellij.java.programPatcher) | [`JavaProgramPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/runners/JavaProgramPatcher.java) |
| [com.intellij.java.refactoring.chainCallExtractor](https://jb.gg/ipe?extensions=com.intellij.java.refactoring.chainCallExtractor) | [`ChainCallExtractor`](%gh-ic%/java/java-impl/src/com/intellij/refactoring/chainCall/ChainCallExtractor.java) |
| [com.intellij.javaDocNotNecessary](https://jb.gg/ipe?extensions=com.intellij.javaDocNotNecessary) | [`Condition`](%gh-ic%/platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.javaExpressionSurrounder](https://jb.gg/ipe?extensions=com.intellij.javaExpressionSurrounder) | [`JavaExpressionSurrounder`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/generation/surroundWith/JavaExpressionSurrounder.java) |
| [com.intellij.jpsServerAuthExtension](https://jb.gg/ipe?extensions=com.intellij.jpsServerAuthExtension) | [`JpsServerAuthExtension`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/cache/client/JpsServerAuthExtension.kt) |
| [com.intellij.jreProvider](https://jb.gg/ipe?extensions=com.intellij.jreProvider) | [`JreProvider`](%gh-ic%/java/execution/impl/src/com/intellij/execution/ui/JreProvider.java) |
| [com.intellij.junitPatcher](https://jb.gg/ipe?extensions=com.intellij.junitPatcher) | [`JUnitPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitPatcher.java) |
| [com.intellij.junitRecognizer](https://jb.gg/ipe?extensions=com.intellij.junitRecognizer) | [`JUnitRecognizer`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitRecognizer.java) |
| [com.intellij.jvm.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.jvm.exceptionFilter) ![Experimental API][experimental] | [`JvmExceptionOccurrenceFilter`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/JvmExceptionOccurrenceFilter.java) |
| [com.intellij.languageCompilerRefAdapter](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter) | [`LanguageCompilerRefAdapter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/LanguageCompilerRefAdapter.java) |
| [com.intellij.languageCompilerRefAdapter.directInheritorProvider](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter.directInheritorProvider) ![Project-Level][project-level] | [`DirectInheritorProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/DirectInheritorProvider.kt) |
| [com.intellij.library.dependencyScopeSuggester](https://jb.gg/ipe?extensions=com.intellij.library.dependencyScopeSuggester) | [`LibraryDependencyScopeSuggester`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/LibraryDependencyScopeSuggester.java) |
| [com.intellij.library.javaSourceRootDetector](https://jb.gg/ipe?extensions=com.intellij.library.javaSourceRootDetector) | [`RootDetector`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/ui/RootDetector.java) |
| [com.intellij.methodImplementor](https://jb.gg/ipe?extensions=com.intellij.methodImplementor) | [`MethodImplementor`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/MethodImplementor.java) |
| [com.intellij.moduleConfigurable](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurable) | [`ModuleConfigurable`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleConfigurable.java) |
| [com.intellij.newProjectWizard.java.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.java.buildSystem) | [`BuildSystemJavaNewProjectWizard`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/BuildSystemJavaNewProjectWizard.kt) |
| [com.intellij.overrideImplementsAnnotationsHandler](https://jb.gg/ipe?extensions=com.intellij.overrideImplementsAnnotationsHandler) | [`OverrideImplementsAnnotationsHandler`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsHandler.java) |
| [com.intellij.packaging.artifactPropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactPropertiesProvider) | [`ArtifactPropertiesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactPropertiesProvider.java) |
| [com.intellij.packaging.artifactType](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactType) | [`ArtifactType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactType.java) |
| [com.intellij.packaging.elementType](https://jb.gg/ipe?extensions=com.intellij.packaging.elementType) | [`PackagingElementType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/elements/PackagingElementType.java) |
| [com.intellij.packaging.sourceItemFilter](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemFilter) | [`PackagingSourceItemFilter`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemFilter.java) |
| [com.intellij.packaging.sourceItemProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemProvider) | [`PackagingSourceItemsProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemsProvider.java) |
| [com.intellij.predefinedMigrationMapProvider](https://jb.gg/ipe?extensions=com.intellij.predefinedMigrationMapProvider) | [`PredefinedMigrationProvider`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/migration/PredefinedMigrationProvider.java) |
| [com.intellij.projectImportBuilder](https://jb.gg/ipe?extensions=com.intellij.projectImportBuilder) | [`ProjectImportBuilder`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportBuilder.java) |
| [com.intellij.projectImportProvider](https://jb.gg/ipe?extensions=com.intellij.projectImportProvider) | [`ProjectImportProvider`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportProvider.java) |
| [com.intellij.projectModelModifier](https://jb.gg/ipe?extensions=com.intellij.projectModelModifier) ![Project-Level][project-level] | [`JavaProjectModelModifier`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/JavaProjectModelModifier.java) |
| [com.intellij.projectStructureConfigurableFilter](https://jb.gg/ipe?extensions=com.intellij.projectStructureConfigurableFilter) | [`ProjectStructureConfigurableFilter`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ProjectStructureConfigurableFilter.java) |
| [com.intellij.projectStructureDetector](https://jb.gg/ipe?extensions=com.intellij.projectStructureDetector) | [`ProjectStructureDetector`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java) |
| [com.intellij.projectStructureValidator](https://jb.gg/ipe?extensions=com.intellij.projectStructureValidator) | [`ProjectStructureValidator`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/daemon/ProjectStructureValidator.java) |
| [com.intellij.projectWizard.projectCategory](https://jb.gg/ipe?extensions=com.intellij.projectWizard.projectCategory) | [`ProjectCategory`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/ProjectCategory.java) |
| [com.intellij.refactoring.introduceParameterMethodUsagesProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.introduceParameterMethodUsagesProcessor) | [`IntroduceParameterMethodUsagesProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/introduceParameter/IntroduceParameterMethodUsagesProcessor.java) |
| [com.intellij.refactoring.moveAllClassesInFileHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveAllClassesInFileHandler) | [`MoveAllClassesInFileHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveAllClassesInFileHandler.java) |
| [com.intellij.refactoring.moveClassHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassHandler) | [`MoveClassHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassHandler.java) |
| [com.intellij.refactoring.moveClassToInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassToInnerHandler) ![Internal API][internal] | [`MoveClassToInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassToInnerHandler.java) |
| [com.intellij.refactoring.moveInnerClassUsagesHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerClassUsagesHandler) | [`MoveInnerClassUsagesHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerClassUsagesHandler.java) |
| [com.intellij.refactoring.moveInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerHandler) | [`MoveInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerHandler.java) |
| [com.intellij.refactoring.moveMemberHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveMemberHandler) | [`MoveMemberHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveMembers/MoveMemberHandler.java) |
| [com.intellij.refactoring.overrideMethodProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.overrideMethodProcessor) ![Internal API][internal] | [`OverrideMethodsProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/OverrideMethodsProcessor.java) |
| [com.intellij.refactoring.pullUpHelperFactory](https://jb.gg/ipe?extensions=com.intellij.refactoring.pullUpHelperFactory) | [`PullUpHelperFactory`](%gh-ic%/java/openapi/src/com/intellij/refactoring/memberPullUp/PullUpHelperFactory.java) |
| [com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate](https://jb.gg/ipe?extensions=com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate) | [`JavaSafeDeleteDelegate`](%gh-ic%/java/openapi/src/com/intellij/refactoring/safeDelete/JavaSafeDeleteDelegate.java) |
| [com.intellij.repositoryLibrary](https://jb.gg/ipe?extensions=com.intellij.repositoryLibrary) | `n/a` |
| [com.intellij.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.runConfigurationExtension) | [`RunConfigurationExtension`](%gh-ic%/java/execution/impl/src/com/intellij/execution/RunConfigurationExtension.java) |
| [com.intellij.safeDelete.importSearcher](https://jb.gg/ipe?extensions=com.intellij.safeDelete.importSearcher) ![Internal API][internal] | [`ImportSearcher`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/safeDelete/ImportSearcher.java) |
| [com.intellij.sdkEditorAdditionalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.sdkEditorAdditionalOptionsProvider) | [`SdkEditorAdditionalOptionsProvider`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/SdkEditorAdditionalOptionsProvider.java) |
| [com.intellij.starter.moduleImporter](https://jb.gg/ipe?extensions=com.intellij.starter.moduleImporter) | [`StarterModuleImporter`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleImporter.kt) |
| [com.intellij.starter.projectConfigurator](https://jb.gg/ipe?extensions=com.intellij.starter.projectConfigurator) ![Internal API][internal] | [`StarterProjectConfigurator`](%gh-ic%/java/idea-ui/src/com/intellij/ide/impl/StarterProjectConfigurator.kt) |
| [com.intellij.testGenerator](https://jb.gg/ipe?extensions=com.intellij.testGenerator) | [`TestGenerator`](%gh-ic%/java/java-impl/src/com/intellij/testIntegration/createTest/TestGenerator.java) |
| [com.intellij.unscrambleSupport](https://jb.gg/ipe?extensions=com.intellij.unscrambleSupport) | [`UnscrambleSupport`](%gh-ic%/java/openapi/src/com/intellij/unscramble/UnscrambleSupport.java) |
| [com.intellij.unusedDeclarationFixProvider](https://jb.gg/ipe?extensions=com.intellij.unusedDeclarationFixProvider) | [`UnusedDeclarationFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInspection/reference/UnusedDeclarationFixProvider.java) |
| [com.intellij.variableTypeCalculator](https://jb.gg/ipe?extensions=com.intellij.variableTypeCalculator) | [`VariableTypeCalculator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/macro/VariableTypeCalculator.java) |

### JavaPsiPlugin.xml

[`JavaPsiPlugin.xml`](%gh-ic%/java/java-psi-impl/src/META-INF/JavaPsiPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.annotationSupport](https://jb.gg/ipe?extensions=com.intellij.annotationSupport) | [`PsiAnnotationSupport`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiAnnotationSupport.java) |
| [com.intellij.classTypePointerFactory](https://jb.gg/ipe?extensions=com.intellij.classTypePointerFactory) | [`ClassTypePointerFactory`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/ClassTypePointerFactory.java) |
| [com.intellij.codeStyle.ReferenceAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeStyle.ReferenceAdjuster) | [`ReferenceAdjuster`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/codeStyle/ReferenceAdjuster.java) |
| [com.intellij.constantExpressionEvaluator](https://jb.gg/ipe?extensions=com.intellij.constantExpressionEvaluator) | [`ConstantExpressionEvaluator`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ConstantExpressionEvaluator.java) |
| [com.intellij.custom.exception.handler](https://jb.gg/ipe?extensions=com.intellij.custom.exception.handler) | [`CustomExceptionHandler`](%gh-ic%/java/java-psi-impl/src/com/intellij/codeInsight/CustomExceptionHandler.java) |
| [com.intellij.customJavadocTagProvider](https://jb.gg/ipe?extensions=com.intellij.customJavadocTagProvider) | [`CustomJavadocTagProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/CustomJavadocTagProvider.java) |
| [com.intellij.deepestSuperMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.deepestSuperMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.expressionConverter](https://jb.gg/ipe?extensions=com.intellij.expressionConverter) | [`ExpressionConverter`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ExpressionConverter.java) |
| [com.intellij.generation.topLevelFactory](https://jb.gg/ipe?extensions=com.intellij.generation.topLevelFactory) | [`JVMElementFactoryProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JVMElementFactoryProvider.java) |
| [com.intellij.importFilter](https://jb.gg/ipe?extensions=com.intellij.importFilter) | [`ImportFilter`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ImportFilter.java) |
| [com.intellij.java.elementFinder](https://jb.gg/ipe?extensions=com.intellij.java.elementFinder) ![Project-Level][project-level] | [`PsiElementFinder`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiElementFinder.java) |
| [com.intellij.java.languageFeatureProvider](https://jb.gg/ipe?extensions=com.intellij.java.languageFeatureProvider) | [`LanguageFeatureProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/pom/java/LanguageFeatureProvider.java) |
| [com.intellij.javaCompilerConfigurationProxy](https://jb.gg/ipe?extensions=com.intellij.javaCompilerConfigurationProxy) | [`JavaCompilerConfigurationProxy`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaCompilerConfigurationProxy.java) |
| [com.intellij.javaMainMethodProvider](https://jb.gg/ipe?extensions=com.intellij.javaMainMethodProvider) | [`JavaMainMethodProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/runner/JavaMainMethodProvider.java) |
| [com.intellij.javaModuleSystem](https://jb.gg/ipe?extensions=com.intellij.javaModuleSystem) | [`JavaModuleSystem`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaModuleSystem.java) |
| [com.intellij.javadocTagInfo](https://jb.gg/ipe?extensions=com.intellij.javadocTagInfo) ![Project-Level][project-level] | [`JavadocTagInfo`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/JavadocTagInfo.java) |
| [com.intellij.jvm.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.jvm.declarationSearcher) | [`JvmDeclarationSearcher`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/source/JvmDeclarationSearcher.java) |
| [com.intellij.jvm.elementProvider](https://jb.gg/ipe?extensions=com.intellij.jvm.elementProvider) ![Project-Level][project-level] | [`JvmElementProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/facade/JvmElementProvider.java) |
| [com.intellij.lang.inferredAnnotationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.inferredAnnotationProvider) ![Project-Level][project-level] | [`InferredAnnotationProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/InferredAnnotationProvider.java) |
| [com.intellij.lang.psiAugmentProvider](https://jb.gg/ipe?extensions=com.intellij.lang.psiAugmentProvider) | [`PsiAugmentProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/augment/PsiAugmentProvider.java) |
| [com.intellij.psi.classFileDecompiler](https://jb.gg/ipe?extensions=com.intellij.psi.classFileDecompiler) | [`Decompiler`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/compiled/ClassFileDecompilers.java) |
| [com.intellij.psi.clsCustomNavigationPolicy](https://jb.gg/ipe?extensions=com.intellij.psi.clsCustomNavigationPolicy) | [`ClsCustomNavigationPolicy`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/compiled/ClsCustomNavigationPolicy.java) |
| [com.intellij.superMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.superMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.testFramework](https://jb.gg/ipe?extensions=com.intellij.testFramework) | [`TestFramework`](%gh-ic%/platform/core-api/src/com/intellij/testIntegration/TestFramework.java) |
| [org.jetbrains.uast.analysis.uastAnalysisPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.analysis.uastAnalysisPlugin) ![Experimental API][experimental] | [`UastAnalysisPlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/analysis/UastAnalysisPlugin.kt) |
| [org.jetbrains.uast.generate.uastCodeGenerationPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.generate.uastCodeGenerationPlugin) ![Experimental API][experimental] | [`UastCodeGenerationPlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/generate/UastCodeGenerationPlugin.kt) |
| [org.jetbrains.uast.uastLanguagePlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.uastLanguagePlugin) | [`UastLanguagePlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/UastLanguagePlugin.kt) |

### jps.xml

[`jps.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/jps.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idePlatformKind](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKind) ![Non-Dynamic][non-dynamic] | `IdePlatformKind` |
| [org.jetbrains.kotlin.idePlatformKindResolution](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindResolution) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindResolution`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/caches/resolve/IdePlatformKindResolution.kt) |
| [org.jetbrains.kotlin.idePlatformKindTooling](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindTooling) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindTooling`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/base/codeInsight/tooling/IdePlatformKindTooling.kt) |

### JUnit

[`JUnit`](%gh-ic%/plugins/junit/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.junitListener](https://jb.gg/ipe?extensions=com.intellij.junitListener) | [`IDEAJUnitListener`](%gh-ic%/java/java-runtime/src/com/intellij/rt/execution/junit/IDEAJUnitListener.java) |
| [com.intellij.testDiscoveryProducer](https://jb.gg/ipe?extensions=com.intellij.testDiscoveryProducer) | [`TestDiscoveryProducer`](%gh-ic%/java/execution/impl/src/com/intellij/execution/testDiscovery/TestDiscoveryProducer.java) |

### jvm.xml

[`jvm.xml`](%gh-ic%/plugins/kotlin/jvm/resources/META-INF/jvm.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.kotlinJvmDebuggerFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.kotlinJvmDebuggerFacade) | [`KotlinJvmDebuggerFacade`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/extensions/KotlinJvmDebuggerFacade.kt) |

### JvmAnalysisPlugin.xml

[`JvmAnalysisPlugin.xml`](%gh-ic%/jvm/jvm-analysis-impl/resources/META-INF/JvmAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.blockingMethodChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.blockingMethodChecker) | [`BlockingMethodChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/BlockingMethodChecker.java) |
| [com.intellij.codeInsight.nonBlockingContextChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.nonBlockingContextChecker) | [`NonBlockingContextChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/NonBlockingContextChecker.java) |

### k2.xml

[`k2.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) ![Non-Dynamic][non-dynamic] | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |

### kotlin-core-fe10.xml

[`kotlin-core-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/kotlin-core-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.j2kConverterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.j2kConverterExtension) | [`J2kConverterExtension`](%gh-ic%/plugins/kotlin/j2k/old/src/org/jetbrains/kotlin/j2k/J2KConverterExtension.kt) |

### kotlin-core.xml

[`kotlin-core.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/kotlin-core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.buildSystemTypeDetector](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.buildSystemTypeDetector) | [`BuildSystemTypeDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/configuration/BuildSystemType.kt) |
| [org.jetbrains.kotlin.experimentalFeature](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.experimentalFeature) | [`ExperimentalFeature`](%gh-ic%/plugins/kotlin/preferences/src/org/jetbrains/kotlin/idea/configuration/ExperimentalFeatures.kt) |
| [org.jetbrains.kotlin.facetValidatorCreator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.facetValidatorCreator) ![Non-Dynamic][non-dynamic] | [`KotlinFacetValidatorCreator`](%gh-ic%/plugins/kotlin/base/compiler-configuration-ui/src/org/jetbrains/kotlin/idea/base/compilerPreferences/facet/KotlinFacetValidatorCreator.kt) |
| [org.jetbrains.kotlin.failedToDownloadJpsMavenArtifactSuggestedSolutionsContributor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.failedToDownloadJpsMavenArtifactSuggestedSolutionsContributor) ![Internal API][internal] ![Project-Level][project-level] | [`FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor`](%gh-ic%/plugins/kotlin/base/plugin/src/org/jetbrains/kotlin/idea/compiler/configuration/FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor.kt) |
| [org.jetbrains.kotlin.idea.base.platforms.targetPlatformDetector](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.base.platforms.targetPlatformDetector) ![Project-Level][project-level] | [`TargetPlatformDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/base/facet/platform/TargetPlatformDetector.kt) |
| [org.jetbrains.kotlin.idea.base.projectStructure.moduleInfoProviderExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.base.projectStructure.moduleInfoProviderExtension) ![Project-Level][project-level] | [`ModuleInfoProviderExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ModuleInfoProvider.kt) |
| [org.jetbrains.kotlin.idea.codeInsight.unambiguousImports](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.codeInsight.unambiguousImports) | [`KotlinAutoImportsFilter`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/KotlinAutoImportsFilter.kt) |
| [org.jetbrains.kotlin.idea.testFrameworkProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.testFrameworkProvider) | [`KotlinTestFrameworkProvider`](%gh-ic%/plugins/kotlin/run-configurations/jvm/src/org/jetbrains/kotlin/idea/extensions/KotlinTestFrameworkProvider.kt) |
| [org.jetbrains.kotlin.kotlinTestFramework](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kotlinTestFramework) | [`KotlinTestFramework`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/testIntegration/framework/KotlinTestFramework.kt) |
| [org.jetbrains.kotlin.newFileHook](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.newFileHook) ![Internal API][internal] | [`NewKotlinFileHook`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/NewKotlinFileHook.kt) |
| [org.jetbrains.kotlin.pluginUpdateVerifier](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.pluginUpdateVerifier) | [`PluginUpdateVerifier`](%gh-ic%/plugins/kotlin/plugin-updater/src/org/jetbrains/kotlin/idea/update/PluginUpdateVerifier.kt) |
| [org.jetbrains.kotlin.projectConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.projectConfigurator) | [`KotlinProjectConfigurator`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinProjectConfigurator.kt) |

### kotlin.gradle.code-insight-common.xml

[`kotlin.gradle.code-insight-common.xml`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/resources/kotlin.gradle.code-insight-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.gradleBuildScriptSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.gradleBuildScriptSupport) | [`GradleBuildScriptSupport`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/src/org/jetbrains/kotlin/idea/gradleCodeInsightCommon/GradleBuildScriptSupport.kt) |

### kotlin.gradle.gradle-java.xml

[`kotlin.gradle.gradle-java.xml`](%gh-ic%/plugins/kotlin/gradle/gradle-java/resources/kotlin.gradle.gradle-java.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleProjectImportHandler) ![Project-Level][project-level] | [`GradleProjectImportHandler`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/KotlinGradleSourceSetDataService.kt) |

### kotlin.gradle.gradle.xml

[`kotlin.gradle.gradle.xml`](%gh-ic%/plugins/kotlin/gradle/gradle/resources/kotlin.gradle.gradle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleModelFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleModelFacade) | [`KotlinGradleModelFacade`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/inspections/KotlinGradleModelFacade.java) |
| [org.jetbrains.kotlin.kpm.createRoots](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kpm.createRoots) | [`ContentRootsCreator`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ContentRootsCreator.kt) |
| [org.jetbrains.kotlin.kpm.extrasSerialization](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kpm.extrasSerialization) | [`ExtrasSerializationService`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ExtrasSerializationService.kt) |
| [org.jetbrains.kotlin.kpm.moduleInitialize](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kpm.moduleInitialize) | [`ModuleDataInitializer`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ModuleDataInitializer.kt) |

### kotlin.maven.xml

[`kotlin.maven.xml`](%gh-ic%/plugins/kotlin/maven/resources/kotlin.maven.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.mavenProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.mavenProjectImportHandler) ![Project-Level][project-level] | [`MavenProjectImportHandler`](%gh-ic%/plugins/kotlin/maven/src/org/jetbrains/kotlin/idea/maven/KotlinMavenImporter.kt) |

### kotlin.project-wizard.idea.xml

[`kotlin.project-wizard.idea.xml`](%gh-ic%/plugins/kotlin/project-wizard/idea/resources/kotlin.project-wizard.idea.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.kotlin.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.kotlin.buildSystem) | [`BuildSystemKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/BuildSystemKotlinNewProjectWizard.kt) |
| [org.jetbrains.kotlin.idea.ideaWizardService](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.ideaWizardService) ![Project-Level][project-level] | [`IdeaWizardService`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/wizard/service/IdeaWizardService.kt) |

### kotlin.search.xml

[`kotlin.search.xml`](%gh-ic%/plugins/kotlin/kotlin.search/resources/kotlin.search.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.directKotlinClassInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.directKotlinClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### libraryJarUsage.xml

[`libraryJarUsage.xml`](%gh-ic%/java/java-impl/src/META-INF/libraryJarUsage.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.internal.statistic.libraryUsageImportProcessor](https://jb.gg/ipe?extensions=com.intellij.internal.statistic.libraryUsageImportProcessor) ![Internal API][internal] | [`LibraryUsageImportProcessor`](%gh-ic%/java/java-impl/src/com/intellij/internal/statistic/libraryUsage/LibraryUsageImportProcessor.kt) |

### light-classes-fe10.xml

[`light-classes-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/light-classes-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ultraLightClassModifierExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ultraLightClassModifierExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UltraLightClassModifierExtension` |

### ManifestSupport.xml

[`ManifestSupport.xml`](%gh-ic%/java/manifest/src/META-INF/ManifestSupport.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.manifest.parser.provider](https://jb.gg/ipe?extensions=com.intellij.manifest.parser.provider) | [`HeaderParserProvider`](%gh-ic%/java/manifest/src/org/jetbrains/lang/manifest/header/HeaderParserProvider.java) |

### org.editorconfig.editorconfigjetbrains

[`org.editorconfig.editorconfigjetbrains`](%gh-ic%/plugins/editorconfig/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [editorconfig.exportProvider](https://jb.gg/ipe?extensions=editorconfig.exportProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigExportProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigExportProvider.java) |
| [editorconfig.optionDescriptorProvider](https://jb.gg/ipe?extensions=editorconfig.optionDescriptorProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigOptionDescriptorProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/language/extensions/EditorConfigOptionDescriptorProvider.kt) |

### org.intellij.groovy

[`org.intellij.groovy`](%gh-ic%/plugins/groovy/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.groovy.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.groovy.buildSystem) | [`BuildSystemGroovyNewProjectWizard`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/BuildSystemGroovyNewProjectWizard.kt) |
| [org.intellij.groovy.applicabilityProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.applicabilityProvider) ![Experimental API][experimental] | [`GroovyApplicabilityProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyApplicabilityProvider.java) |
| [org.intellij.groovy.astTransformationSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.astTransformationSupport) | [`AstTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/AstTransformationSupport.java) |
| [org.intellij.groovy.callTypeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.callTypeCalculator) ![Experimental API][experimental] | [`GrCallTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrCallTypeCalculator.kt) |
| [org.intellij.groovy.classDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.classDescriptor) | `n/a` |
| [org.intellij.groovy.closureCompleter](https://jb.gg/ipe?extensions=org.intellij.groovy.closureCompleter) | [`ClosureCompleter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/ClosureCompleter.java) |
| [org.intellij.groovy.closureMissingMethodContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.closureMissingMethodContributor) | [`ClosureMissingMethodContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ClosureMissingMethodContributor.java) |
| [org.intellij.groovy.completionCustomizer](https://jb.gg/ipe?extensions=org.intellij.groovy.completionCustomizer) ![Experimental API][experimental] | [`GroovyCompletionCustomizer`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/api/GroovyCompletionCustomizer.kt) |
| [org.intellij.groovy.configSlurperSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.configSlurperSupport) | [`ConfigSlurperSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/configSlurper/ConfigSlurperSupport.java) |
| [org.intellij.groovy.convertToJava.customMethodInvocator](https://jb.gg/ipe?extensions=org.intellij.groovy.convertToJava.customMethodInvocator) | [`CustomMethodInvocator`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/convertToJava/invocators/CustomMethodInvocator.java) |
| [org.intellij.groovy.customAnnotationChecker](https://jb.gg/ipe?extensions=org.intellij.groovy.customAnnotationChecker) | [`CustomAnnotationChecker`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/checkers/CustomAnnotationChecker.java) |
| [org.intellij.groovy.delegatesToProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.delegatesToProvider) | [`GrDelegatesToProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/delegatesTo/GrDelegatesToProvider.java) |
| [org.intellij.groovy.elementFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.elementFilter) ![Experimental API][experimental] | [`GroovyElementFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/GroovyElementFilter.kt) |
| [org.intellij.groovy.expectedPackageNameProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedPackageNameProvider) | [`ExpectedPackageNameProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ExpectedPackageNameProvider.kt) |
| [org.intellij.groovy.expectedTypesContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedTypesContributor) | [`GroovyExpectedTypesContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/expectedTypes/GroovyExpectedTypesContributor.java) |
| [org.intellij.groovy.gdslScriptProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslScriptProvider) | [`GdslScriptProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/GdslScriptProvider.java) |
| [org.intellij.groovy.gdslTopLevelProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslTopLevelProvider) ![Non-Dynamic][non-dynamic] | [`GdslMembersProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/dsltop/GdslMembersProvider.java) |
| [org.intellij.groovy.groovyFrameworkConfigNotification](https://jb.gg/ipe?extensions=org.intellij.groovy.groovyFrameworkConfigNotification) | [`GroovyFrameworkConfigNotification`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/GroovyFrameworkConfigNotification.java) |
| [org.intellij.groovy.groovySourceFolderDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.groovySourceFolderDetector) | [`GroovySourceFolderDetector`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/actions/GroovySourceFolderDetector.java) |
| [org.intellij.groovy.importContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.importContributor) | [`GrImportContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/imports/GrImportContributor.java) |
| [org.intellij.groovy.inlayHintFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.inlayHintFilter) | [`GroovyInlayHintFilter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/editor/GroovyInlayHintFilter.java) |
| [org.intellij.groovy.inlineASTTransformationSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.inlineASTTransformationSupport) ![Experimental API][experimental] | [`GroovyInlineASTTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/inline/GroovyInlineASTTransformationSupport.kt) |
| [org.intellij.groovy.inspectionDisabler](https://jb.gg/ipe?extensions=org.intellij.groovy.inspectionDisabler) | [`FileTypeInspectionDisabler`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/FileTypeInspectionDisabler.kt) |
| [org.intellij.groovy.mapContentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.mapContentProvider) | [`GroovyMapContentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyMapContentProvider.java) |
| [org.intellij.groovy.membersContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.membersContributor) | [`NonCodeMembersContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/NonCodeMembersContributor.java) |
| [org.intellij.groovy.methodComparator](https://jb.gg/ipe?extensions=org.intellij.groovy.methodComparator) | [`GrMethodComparator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/GrMethodComparator.java) |
| [org.intellij.groovy.methodDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.methodDescriptor) | `n/a` |
| [org.intellij.groovy.methodMayBeStaticInspectionFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.methodMayBeStaticInspectionFilter) | [`GrMethodMayBeStaticInspectionFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/declaration/GrMethodMayBeStaticInspectionFilter.java) |
| [org.intellij.groovy.namedArgumentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.namedArgumentProvider) | [`GroovyNamedArgumentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyNamedArgumentProvider.java) |
| [org.intellij.groovy.overloadResolver](https://jb.gg/ipe?extensions=org.intellij.groovy.overloadResolver) | [`GroovyOverloadResolver`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/api/GroovyOverloadResolver.java) |
| [org.intellij.groovy.positionManagerDelegate](https://jb.gg/ipe?extensions=org.intellij.groovy.positionManagerDelegate) | [`ScriptPositionManagerHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/extensions/debugger/ScriptPositionManagerHelper.java) |
| [org.intellij.groovy.psiEnhancerCategory](https://jb.gg/ipe?extensions=org.intellij.groovy.psiEnhancerCategory) ![Non-Dynamic][non-dynamic] | [`PsiEnhancerCategory`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/psi/PsiEnhancerCategory.java) |
| [org.intellij.groovy.referenceTypeEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.referenceTypeEnhancer) | [`GrReferenceTypeEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrReferenceTypeEnhancer.java) |
| [org.intellij.groovy.renameHelper](https://jb.gg/ipe?extensions=org.intellij.groovy.renameHelper) | [`GrRenameHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/rename/GrRenameHelper.java) |
| [org.intellij.groovy.scriptTypeDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.scriptTypeDetector) | [`GroovyScriptTypeDetector`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyScriptTypeDetector.java) |
| [org.intellij.groovy.signatureHintProcessor](https://jb.gg/ipe?extensions=org.intellij.groovy.signatureHintProcessor) | [`SignatureHintProcessor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/SignatureHintProcessor.java) |
| [org.intellij.groovy.typeAugmenter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeAugmenter) | [`TypeAugmenter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/dataFlow/types/TypeAugmenter.kt) |
| [org.intellij.groovy.typeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.typeCalculator) | [`GrTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrTypeCalculator.java) |
| [org.intellij.groovy.typeConverter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeConverter) | [`GrTypeConverter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrTypeConverter.java) |
| [org.intellij.groovy.unresolvedHighlightFileFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFileFilter) | [`GroovyUnresolvedHighlightFileFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFileFilter.java) |
| [org.intellij.groovy.unresolvedHighlightFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFilter) | [`GroovyUnresolvedHighlightFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFilter.java) |
| [org.intellij.groovy.variableEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.variableEnhancer) | [`GrVariableEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrVariableEnhancer.java) |

### org.intellij.intelliLang

[`org.intellij.intelliLang`](%gh-ic%/plugins/IntelliLang/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.intelliLang.injectionConfig](https://jb.gg/ipe?extensions=org.intellij.intelliLang.injectionConfig) | `n/a` |
| [org.intellij.intelliLang.languageSupport](https://jb.gg/ipe?extensions=org.intellij.intelliLang.languageSupport) | [`LanguageInjectionSupport`](%gh-ic%/plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/LanguageInjectionSupport.java) |

### org.intellij.plugins.markdown

[`org.intellij.plugins.markdown`](%gh-ic%/plugins/markdown/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.markdown.browserPreviewExtensionProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.browserPreviewExtensionProvider) | [`Provider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/MarkdownBrowserPreviewExtension.kt) |
| [org.intellij.markdown.fenceGeneratingProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceGeneratingProvider) ![Experimental API][experimental] | [`CodeFenceGeneratingProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/CodeFenceGeneratingProvider.kt) |
| [org.intellij.markdown.fenceLanguageProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceLanguageProvider) | [`CodeFenceLanguageProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/CodeFenceLanguageProvider.java) |
| [org.intellij.markdown.html.panel.provider](https://jb.gg/ipe?extensions=org.intellij.markdown.html.panel.provider) | [`MarkdownHtmlPanelProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownHtmlPanelProvider.java) |
| [org.intellij.markdown.markdownExportProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownExportProvider) ![Experimental API][experimental] | [`MarkdownExportProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/fileActions/export/MarkdownExportProvider.kt) |
| [org.intellij.markdown.markdownRunner](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownRunner) | [`MarkdownRunner`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/jcef/commandRunner/MarkdownRunner.kt) |
| [org.intellij.markdown.previewStylesProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.previewStylesProvider) ![Internal API][internal] | [`MarkdownPreviewStylesProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownPreviewStylesProvider.kt) |

### org.jetbrains.completion.full.line

[`org.jetbrains.completion.full.line`](%gh-ic%/plugins/full-line/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.completion.full.line.fullLineLanguageSupport](https://jb.gg/ipe?extensions=org.jetbrains.completion.full.line.fullLineLanguageSupport) | [`FullLineLanguageSupporter`](%gh-ic%/plugins/full-line/core/src/org/jetbrains/completion/full/line/language/FullLineLanguageSupporter.kt) |

### org.jetbrains.debugger.streams

[`org.jetbrains.debugger.streams`](%gh-ic%/plugins/stream-debugger/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.debugger.streams.librarySupport](https://jb.gg/ipe?extensions=org.jetbrains.debugger.streams.librarySupport) | [`LibrarySupportProvider`](%gh-ic%/plugins/stream-debugger/src/com/intellij/debugger/streams/lib/LibrarySupportProvider.java) |

### org.jetbrains.idea.eclipse

[`org.jetbrains.idea.eclipse`](%gh-ic%/plugins/eclipse/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.eclipse.natureImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.eclipse.natureImporter) | [`EclipseNatureImporter`](%gh-ic%/plugins/eclipse/src/org/jetbrains/idea/eclipse/importWizard/EclipseNatureImporter.java) |

### org.jetbrains.idea.maven

[`org.jetbrains.idea.maven`](%gh-ic%/plugins/maven/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.maven.additional.importing.settings](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.additional.importing.settings) | [`AdditionalMavenImportingSettings`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/AdditionalMavenImportingSettings.java) |
| [org.jetbrains.idea.maven.archetypesProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.archetypesProvider) | [`MavenArchetypesProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenArchetypesProvider.java) |
| [org.jetbrains.idea.maven.artifactBuilder](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.artifactBuilder) | [`MavenArtifactBuilder`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenArtifactBuilder.java) |
| [org.jetbrains.idea.maven.compiler](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.compiler) | [`MavenCompilerExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenCompilerExtension.java) |
| [org.jetbrains.idea.maven.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.executionEnvironmentProvider) | [`MavenExecutionEnvironmentProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenExecutionEnvironmentProvider.java) |
| [org.jetbrains.idea.maven.importer](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importer) | [`MavenImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenImporter.java) |
| [org.jetbrains.idea.maven.importing.afterImportConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.afterImportConfigurator) ![Experimental API][experimental] | [`MavenAfterImportConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| [org.jetbrains.idea.maven.importing.workspaceConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.workspaceConfigurator) ![Experimental API][experimental] | [`MavenWorkspaceConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| [org.jetbrains.idea.maven.log.import.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.import.parser) ![Experimental API][experimental] | [`MavenImportLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/importproject/MavenImportLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.parser) ![Experimental API][experimental] | [`MavenLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.spy.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.spy.parser) ![Experimental API][experimental] | [`MavenSpyLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenSpyLoggedEventParser.java) |
| [org.jetbrains.idea.maven.manifestImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.manifestImporter) | [`ManifestImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/ManifestImporter.java) |
| [org.jetbrains.idea.maven.mavenServerSupportFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.mavenServerSupportFactory) ![Internal API][internal] | [`MavenRemoteProcessSupportFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/MavenRemoteProcessSupportFactory.java) |
| [org.jetbrains.idea.maven.pluginDescriptor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.pluginDescriptor) ![Internal API][internal] | `n/a` |
| [org.jetbrains.idea.maven.remotePathTransformerFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.remotePathTransformerFactory) | [`RemotePathTransformerFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/RemotePathTransformerFactory.java) |
| [org.jetbrains.idea.maven.repositoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.repositoryProvider) | [`MavenRepositoryProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenRepositoryProvider.java) |
| [org.jetbrains.idea.maven.targetConfigurationExtension](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.targetConfigurationExtension) ![Experimental API][experimental] | [`TargetConfigurationMavenExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/target/TargetConfigurationMavenExtension.java) |
| [org.jetbrains.idea.maven.versionAwareMavenSupport](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.versionAwareMavenSupport) ![Internal API][internal] | [`MavenVersionAwareSupportExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/MavenVersionAwareSupportExtension.java) |

### org.jetbrains.idea.reposearch

[`org.jetbrains.idea.reposearch`](%gh-ic%/plugins/repository-search/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.reposearch.provider](https://jb.gg/ipe?extensions=org.jetbrains.idea.reposearch.provider) ![Experimental API][experimental] | [`DependencySearchProvidersFactory`](%gh-ic%/plugins/repository-search/src/main/java/org/jetbrains/idea/reposearch/DependencySearchProvidersFactory.java) |

### org.jetbrains.plugins.gradle

[`org.jetbrains.plugins.gradle`](%gh-ic%/plugins/gradle/java/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.buildTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.buildTasksProvider) | [`GradleBuildTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/build/GradleBuildTasksProvider.java) |
| [org.jetbrains.plugins.gradle.frameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.frameworkSupport) | [`GradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/GradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport) | [`KotlinDslGradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/KotlinDslGradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.testLocationCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.testLocationCustomizer) ![Internal API][internal] | [`GradleTestLocationCustomizer`](%gh-ic%/plugins/gradle/java/src/execution/test/runner/GradleTestLocationCustomizer.kt) |
| [org.jetbrains.plugins.gradle.testTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.testTasksProvider) | [`GradleTestTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/test/runner/GradleTestTasksProvider.java) |

### org.jetbrains.plugins.yaml

[`org.jetbrains.plugins.yaml`](%gh-ic%/plugins/yaml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.yaml.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.yaml.customStructureViewFactory) | [`YAMLCustomStructureViewFactory`](%gh-ic%/plugins/yaml/src/org/jetbrains/yaml/structureView/YAMLCustomStructureViewFactory.java) |

### parcelize.xml

[`parcelize.xml`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/resources/META-INF/parcelize.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider) ![Project-Level][project-level] | [`ParcelizeAvailabilityProvider`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/src/org/jetbrains/kotlin/idea/compilerPlugin/parcelize/ParcelizeAvailability.kt) |

### python-core-common.xml

[`python-core-common.xml`](%gh-ic%/python/src/META-INF/python-core-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.PythonPackagingToolwindowActionProvider](https://jb.gg/ipe?extensions=Pythonid.PythonPackagingToolwindowActionProvider) | [`PythonPackagingToolwindowActionProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/toolwindow/actions.kt) |
| [Pythonid.breakpointHandler](https://jb.gg/ipe?extensions=Pythonid.breakpointHandler) | [`PyBreakpointHandlerFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyBreakpointHandlerFactory.java) |
| [Pythonid.condaSdkCustomizer](https://jb.gg/ipe?extensions=Pythonid.condaSdkCustomizer) ![Experimental API][experimental] | [`PyCondaSdkCustomizer`](%gh-ic%/python/src/com/jetbrains/python/sdk/conda/PyCondaSdkCustomizer.kt) |
| [Pythonid.connectionCredentialsToTargetConfigurationConverter](https://jb.gg/ipe?extensions=Pythonid.connectionCredentialsToTargetConfigurationConverter) ![Internal API][internal] | [`ConnectionCredentialsToTargetConfigurationConverter`](%gh-ic%/python/src/com/jetbrains/python/run/target/ConnectionCredentialsToTargetConfigurationConverter.kt) |
| [Pythonid.consoleOptionsProvider](https://jb.gg/ipe?extensions=Pythonid.consoleOptionsProvider) | [`PyConsoleOptionsProvider`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOptionsProvider.java) |
| [Pythonid.dataViewPanelFactory](https://jb.gg/ipe?extensions=Pythonid.dataViewPanelFactory) | [`PyDataViewPanelFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/containerview/PyDataViewPanelFactory.java) |
| [Pythonid.debugSessionFactory](https://jb.gg/ipe?extensions=Pythonid.debugSessionFactory) | [`PyDebugSessionFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyDebugSessionFactory.java) |
| [Pythonid.documentationLinkProvider](https://jb.gg/ipe?extensions=Pythonid.documentationLinkProvider) | [`PythonDocumentationLinkProvider`](%gh-ic%/python/openapi/src/com/jetbrains/python/documentation/PythonDocumentationLinkProvider.java) |
| [Pythonid.interpreterTargetEnvironmentFactory](https://jb.gg/ipe?extensions=Pythonid.interpreterTargetEnvironmentFactory) ![Experimental API][experimental] | [`PythonInterpreterTargetEnvironmentFactory`](%gh-ic%/python/src/com/jetbrains/python/run/PythonInterpreterTargetEnvironmentFactory.kt) |
| [Pythonid.magicLiteral](https://jb.gg/ipe?extensions=Pythonid.magicLiteral) | [`PyMagicLiteralExtensionPoint`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/magicLiteral/PyMagicLiteralExtensionPoint.java) |
| [Pythonid.packageManagerProvider](https://jb.gg/ipe?extensions=Pythonid.packageManagerProvider) ![Experimental API][experimental] | [`PyPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/PyCustomPackageManagers.kt) |
| [Pythonid.pep8ProblemSuppressor](https://jb.gg/ipe?extensions=Pythonid.pep8ProblemSuppressor) | [`Pep8ProblemSuppressor`](%gh-ic%/python/src/com/jetbrains/python/validation/Pep8ProblemSuppressor.java) |
| [Pythonid.projectSynchronizerProvider](https://jb.gg/ipe?extensions=Pythonid.projectSynchronizerProvider) | [`PyProjectSynchronizerProvider`](%gh-ic%/python/src/com/jetbrains/python/remote/PyProjectSynchronizer.kt) |
| [Pythonid.pyAddSdkProvider](https://jb.gg/ipe?extensions=Pythonid.pyAddSdkProvider) | [`PyAddSdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/add/PyAddSdkProvider.kt) |
| [Pythonid.pyAnnotator](https://jb.gg/ipe?extensions=Pythonid.pyAnnotator) | [`PyAnnotator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/validation/PyAnnotator.java) |
| [Pythonid.pyCustomSdkUiProvider](https://jb.gg/ipe?extensions=Pythonid.pyCustomSdkUiProvider) | [`PyCustomSdkUiProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PyCustomSdkUiProvider.java) |
| [Pythonid.pyPregeneratedSkeletonsProvider](https://jb.gg/ipe?extensions=Pythonid.pyPregeneratedSkeletonsProvider) | [`PyPregeneratedSkeletonsProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/skeletons/PyPregeneratedSkeletonsProvider.java) |
| [Pythonid.pyRootTypeProvider](https://jb.gg/ipe?extensions=Pythonid.pyRootTypeProvider) | [`PyRootTypeProvider`](%gh-ic%/python/src/com/jetbrains/python/module/PyRootTypeProvider.java) |
| [Pythonid.pySdkProvider](https://jb.gg/ipe?extensions=Pythonid.pySdkProvider) ![Experimental API][experimental] | [`PySdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkProvider.kt) |
| [Pythonid.pyTestConfigurationSelector](https://jb.gg/ipe?extensions=Pythonid.pyTestConfigurationSelector) | [`PyTestConfigurationSelector`](%gh-ic%/python/src/com/jetbrains/python/testing/PyTestConfigurationSelector.kt) |
| [Pythonid.pyTestFixtureExtension](https://jb.gg/ipe?extensions=Pythonid.pyTestFixtureExtension) | [`PyTestFixtureExtension`](%gh-ic%/python/src/com/jetbrains/python/testing/pyTestFixtures/PyTestFixtureExtension.kt) |
| [Pythonid.pythonCommandLineEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineEnvironmentProvider) | [`PythonCommandLineEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/PythonCommandLineEnvironmentProvider.java) |
| [Pythonid.pythonCommandLineTargetEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineTargetEnvironmentProvider) ![Experimental API][experimental] ![Internal API][internal] | [`PythonCommandLineTargetEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/target/PythonCommandLineTargetEnvironmentProvider.kt) |
| [Pythonid.pythonPackageManagerProvider](https://jb.gg/ipe?extensions=Pythonid.pythonPackageManagerProvider) ![Experimental API][experimental] | [`PythonPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/management/PythonPackageManagerProvider.kt) |
| [Pythonid.pythonSdkComparator](https://jb.gg/ipe?extensions=Pythonid.pythonSdkComparator) | [`PySdkComparator`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkComparator.java) |
| [Pythonid.remoteConsoleProcessCreator](https://jb.gg/ipe?extensions=Pythonid.remoteConsoleProcessCreator) | [`PythonConsoleRemoteProcessCreator`](%gh-ic%/python/src/com/jetbrains/python/console/PythonConsoleRemoteProcessCreator.kt) |
| [Pythonid.remoteInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.remoteInterpreterManager) | [`PythonRemoteInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonRemoteInterpreterManager.java) |
| [Pythonid.remoteProcessStarterManager](https://jb.gg/ipe?extensions=Pythonid.remoteProcessStarterManager) | [`PyRemoteProcessStarterManager`](%gh-ic%/python/src/com/jetbrains/python/run/PyRemoteProcessStarterManager.java) |
| [Pythonid.remoteSdkValidator](https://jb.gg/ipe?extensions=Pythonid.remoteSdkValidator) | [`PyRemoteSdkValidator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/sdk/PyRemoteSdkValidator.kt) |
| [Pythonid.remoteSkeletonGeneratorFactory](https://jb.gg/ipe?extensions=Pythonid.remoteSkeletonGeneratorFactory) | [`PyRemoteSkeletonGeneratorFactory`](%gh-ic%/python/src/com/jetbrains/python/remote/PyRemoteSkeletonGeneratorFactory.java) |
| [Pythonid.runConfigurationEditorExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationEditorExtension) ![Internal API][internal] | [`PyRunConfigurationEditorExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PyRunConfigurationEditorExtension.java) |
| [Pythonid.runConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationExtension) | [`PythonRunConfigurationExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PythonRunConfigurationExtension.java) |
| [Pythonid.runnableScriptFilter](https://jb.gg/ipe?extensions=Pythonid.runnableScriptFilter) | [`RunnableScriptFilter`](%gh-ic%/python/src/com/jetbrains/python/run/RunnableScriptFilter.java) |
| [Pythonid.sshInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.sshInterpreterManager) | [`PythonSshInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonSshInterpreterManager.java) |
| [Pythonid.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=Pythonid.unresolvedReferenceQuickFixProvider) | [`PyUnresolvedReferenceQuickFixProvider`](%gh-ic%/python/openapi/src/com/jetbrains/python/inspections/PyUnresolvedReferenceQuickFixProvider.java) |
| [com.jetbrains.python.console.executeCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.executeCustomizer) ![Experimental API][experimental] | [`PyExecuteConsoleCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyExecuteConsoleCustomizer.kt) |
| [com.jetbrains.python.console.pyConsoleOutputCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.pyConsoleOutputCustomizer) ![Experimental API][experimental] | [`PyConsoleOutputCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOutputCustomizer.kt) |
| [com.jetbrains.python.debugger.numericContainerPopupCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.debugger.numericContainerPopupCustomizer) ![Experimental API][experimental] | [`PyNumericContainerPopupCustomizer`](%gh-ic%/python/pydevSrc/com/jetbrains/python/debugger/pydev/tables/PyNumericContainerPopupCustomizer.kt) |

### python-rest-plugin.xml

[`python-rest-plugin.xml`](%gh-ic%/python/python-rest/resources/META-INF/python-rest-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [restructured.text.html.preview.output.handler](https://jb.gg/ipe?extensions=restructured.text.html.preview.output.handler) ![Experimental API][experimental] | [`RestOutputHandler`](%gh-ic%/python/python-rest/src/com/jetbrains/rest/RestOutputHandler.kt) |

### PythonPsi.xml

[`PythonPsi.xml`](%gh-ic%/python/python-psi-api/resources/META-INF/PythonPsi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.canonicalPathProvider](https://jb.gg/ipe?extensions=Pythonid.canonicalPathProvider) | [`PyCanonicalPathProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyCanonicalPathProvider.java) |
| [Pythonid.customPackageIdentifier](https://jb.gg/ipe?extensions=Pythonid.customPackageIdentifier) | [`PyCustomPackageIdentifier`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/PyCustomPackageIdentifier.java) |
| [Pythonid.dialectsTokenSetContributor](https://jb.gg/ipe?extensions=Pythonid.dialectsTokenSetContributor) | [`PythonDialectsTokenSetContributor`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |
| [Pythonid.importResolver](https://jb.gg/ipe?extensions=Pythonid.importResolver) | [`PyImportResolver`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyImportResolver.java) |
| [Pythonid.inspectionExtension](https://jb.gg/ipe?extensions=Pythonid.inspectionExtension) | [`PyInspectionExtension`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/inspections/PyInspectionExtension.java) |
| [Pythonid.keywordArgumentProvider](https://jb.gg/ipe?extensions=Pythonid.keywordArgumentProvider) | [`PyKeywordArgumentProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyKeywordArgumentProvider.java) |
| [Pythonid.knownDecoratorProvider](https://jb.gg/ipe?extensions=Pythonid.knownDecoratorProvider) | [`PyKnownDecoratorProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/PyKnownDecoratorProvider.java) |
| [Pythonid.pyClassInheritorsSearch](https://jb.gg/ipe?extensions=Pythonid.pyClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyClassMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyClassMembersProvider) | [`PyClassMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyClassMembersProvider.java) |
| [Pythonid.pyModuleMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyModuleMembersProvider) | [`PyModuleMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyModuleMembersProvider.java) |
| [Pythonid.pyOverridingMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pyOverridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyReferenceResolveProvider](https://jb.gg/ipe?extensions=Pythonid.pyReferenceResolveProvider) | [`PyReferenceResolveProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyReferenceResolveProvider.java) |
| [Pythonid.pySuperMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pySuperMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pythonDocumentationQuickInfoProvider](https://jb.gg/ipe?extensions=Pythonid.pythonDocumentationQuickInfoProvider) | [`PythonDocumentationQuickInfoProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/documentation/PythonDocumentationQuickInfoProvider.java) |
| [Pythonid.resolveResultRater](https://jb.gg/ipe?extensions=Pythonid.resolveResultRater) | [`PyResolveResultRater`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyResolveResultRater.java) |
| [Pythonid.thirdPartySdkDetector](https://jb.gg/ipe?extensions=Pythonid.thirdPartySdkDetector) ![Experimental API][experimental] | [`PyThirdPartySdkDetector`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyThirdPartySdkDetector.java) |
| [Pythonid.typeProvider](https://jb.gg/ipe?extensions=Pythonid.typeProvider) | [`PyTypeProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyTypeProvider.java) |

### PythonPsiImpl.xml

[`PythonPsiImpl.xml`](%gh-ic%/python/python-psi-impl/resources/META-INF/PythonPsiImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.customClassStubType](https://jb.gg/ipe?extensions=Pythonid.customClassStubType) ![Internal API][internal] | [`PyCustomClassStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomClassStubType.java) |
| [Pythonid.customTargetExpressionStubType](https://jb.gg/ipe?extensions=Pythonid.customTargetExpressionStubType) ![Internal API][internal] | [`CustomTargetExpressionStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/CustomTargetExpressionStubType.java) |
| [Pythonid.decoratorIndexer](https://jb.gg/ipe?extensions=Pythonid.decoratorIndexer) ![Experimental API][experimental] | [`PyCustomDecoratorIndexer`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/PyCustomDecoratorIndexer.kt) |
| [Pythonid.importCandidateProvider](https://jb.gg/ipe?extensions=Pythonid.importCandidateProvider) | [`PyImportCandidateProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/imports/PyImportCandidateProvider.java) |
| [Pythonid.pyDataclassParametersProvider](https://jb.gg/ipe?extensions=Pythonid.pyDataclassParametersProvider) | [`PyDataclassParametersProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/PyDataclasses.kt) |
| [Pythonid.pyReferenceCustomTargetChecker](https://jb.gg/ipe?extensions=Pythonid.pyReferenceCustomTargetChecker) | [`PyReferenceCustomTargetChecker`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/references/PyReferenceCustomTargetChecker.kt) |
| [Pythonid.pyiStubSuppressor](https://jb.gg/ipe?extensions=Pythonid.pyiStubSuppressor) ![Experimental API][experimental] | [`PyiStubSuppressor`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/pyi/PyiStubSuppressor.java) |
| [Pythonid.typeCheckerExtension](https://jb.gg/ipe?extensions=Pythonid.typeCheckerExtension) ![Experimental API][experimental] | [`PyTypeCheckerExtension`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/types/PyTypeCheckerExtension.java) |
| [Pythonid.visitorFilter](https://jb.gg/ipe?extensions=Pythonid.visitorFilter) | [`PythonVisitorFilter`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/PythonVisitorFilter.java) |

### PythonSdk.xml

[`PythonSdk.xml`](%gh-ic%/python/python-sdk/resources/META-INF/PythonSdk.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.projectSdkConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.projectSdkConfigurationExtension) ![Experimental API][experimental] | [`PyProjectSdkConfigurationExtension`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/configuration/PyProjectSdkConfigurationExtension.kt) |
| [Pythonid.pythonFlavorProvider](https://jb.gg/ipe?extensions=Pythonid.pythonFlavorProvider) | [`PythonFlavorProvider`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonFlavorProvider.java) |
| [Pythonid.pythonSdkFlavor](https://jb.gg/ipe?extensions=Pythonid.pythonSdkFlavor) | [`PythonSdkFlavor`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonSdkFlavor.java) |

### refactorings-fe10.xml

[`refactorings-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/refactorings-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.additionalExtractableAnalyser](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.additionalExtractableAnalyser) | [`AdditionalExtractableAnalyser`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/AdditionalExtractableAnalyser.kt) |
| [org.jetbrains.kotlin.foreignUsagesRenameProcessor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.foreignUsagesRenameProcessor) | [`ForeignUsagesRenameProcessor`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/rename/ForeignUsagesRenameProcessor.kt) |

### resolution-fe10.xml

[`resolution-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/resolution-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.declarationAttributeAltererExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.declarationAttributeAltererExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DeclarationAttributeAltererExtension` |
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |
| [org.jetbrains.kotlin.syntheticScopeProviderExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.syntheticScopeProviderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SyntheticScopeProviderExtension` |

### rest.xml

[`rest.xml`](%gh-ic%/python/rest/resources/META-INF/rest.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [restructured.text.html.preview.provider](https://jb.gg/ipe?extensions=restructured.text.html.preview.provider) | [`RestPreviewProvider`](%gh-ic%/python/rest/src/com/jetbrains/rest/editor/RestPreviewProvider.java) |

### scripting-support.xml

[`scripting-support.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/scripting-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ideScriptConfigurationControlFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ideScriptConfigurationControlFacade) ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| [org.jetbrains.kotlin.scratchFileLanguageProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scratchFileLanguageProvider) | [`ScratchFileLanguageProvider`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| [org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider) ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsProvider) ![Project-Level][project-level] | [`ScriptDefinitionsProvider`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptDefinitionProvider.kt) |
| [org.jetbrains.kotlin.scriptDiagnosticFixProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDiagnosticFixProvider) | [`ScriptDiagnosticFixProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/ScriptDiagnosticFixProvider.kt) |
| [org.jetbrains.kotlin.scripting.idea.listener](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.listener) ![Project-Level][project-level] | [`ScriptChangeListener`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| [org.jetbrains.kotlin.scripting.idea.loader](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.loader) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| [org.jetbrains.kotlin.scripting.idea.scriptingSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.scriptingSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| [org.jetbrains.kotlin.scripting.idea.settings.provider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.settings.provider) ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### sh.xml

[`sh.xml`](%gh-ic%/plugins/sh/core/resources/META-INF/sh.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.runMarkerContributionAdditionalCondition](https://jb.gg/ipe?extensions=com.intellij.runMarkerContributionAdditionalCondition) | [`ShRunnerAdditionalCondition`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/run/ShRunnerAdditionalCondition.java) |

### tanvd.grazi

[`tanvd.grazi`](%gh-ic%/plugins/grazie/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.grazie.disableChecking](https://jb.gg/ipe?extensions=com.intellij.grazie.disableChecking) | `n/a` |
| [com.intellij.grazie.grammar.strategy](https://jb.gg/ipe?extensions=com.intellij.grazie.grammar.strategy) ![Deprecated][deprecated] | [`GrammarCheckingStrategy`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/grammar/strategy/GrammarCheckingStrategy.kt) |
| [com.intellij.grazie.problemFilter](https://jb.gg/ipe?extensions=com.intellij.grazie.problemFilter) | [`ProblemFilter`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/ProblemFilter.java) |
| [com.intellij.grazie.textChecker](https://jb.gg/ipe?extensions=com.intellij.grazie.textChecker) | [`TextChecker`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextChecker.java) |
| [com.intellij.grazie.textExtractor](https://jb.gg/ipe?extensions=com.intellij.grazie.textExtractor) | [`TextExtractor`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextExtractor.java) |

### terminal.xml

[`terminal.xml`](%gh-ic%/plugins/terminal/resources/META-INF/terminal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.terminal.shellCommandHandler](https://jb.gg/ipe?extensions=com.intellij.terminal.shellCommandHandler) | [`TerminalShellCommandHandler`](%gh-ic%/platform/execution-impl/src/com/intellij/terminal/TerminalShellCommandHandler.kt) |
| [org.jetbrains.plugins.terminal.localTerminalCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.localTerminalCustomizer) | [`LocalTerminalCustomizer`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/LocalTerminalCustomizer.java) |
| [org.jetbrains.plugins.terminal.openPredefinedTerminalProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.openPredefinedTerminalProvider) | [`OpenPredefinedTerminalActionProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/ui/OpenPredefinedTerminalActionProvider.kt) |

### TestNG-J

[`TestNG-J`](%gh-ic%/plugins/testng/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.theoryinpractice.testng.listener](https://jb.gg/ipe?extensions=com.theoryinpractice.testng.listener) | [`IDEATestNGListener`](%gh-ic%/plugins/testng_rt/src/com/intellij/rt/testng/IDEATestNGListener.java) |

### training

[`training`](%gh-ic%/plugins/ide-features-trainer/res/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [training.ifs.suggester](https://jb.gg/ipe?extensions=training.ifs.suggester) | [`FeatureSuggester`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/suggesters/FeatureSuggester.kt) |
| [training.ifs.suggesterSupport](https://jb.gg/ipe?extensions=training.ifs.suggesterSupport) | [`SuggesterSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/SuggesterSupport.kt) |
| [training.ift.language.extension](https://jb.gg/ipe?extensions=training.ift.language.extension) | [`LangSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/lang/LangSupport.kt) |
| [training.ift.learning.commonCourse](https://jb.gg/ipe?extensions=training.ift.learning.commonCourse) | [`LearningCourse`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourse.kt) |
| [training.ift.learning.course](https://jb.gg/ipe?extensions=training.ift.learning.course) | [`LearningCourseBase`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourseBase.kt) |

### XPathView

[`XPathView`](%gh-ic%/plugins/xpath/xpath-view/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [XPathView.xpath.contextProviderExtension](https://jb.gg/ipe?extensions=XPathView.xpath.contextProviderExtension) | [`ContextProviderExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/ContextProviderExtension.java) |
| [XPathView.xpath.functionProvider](https://jb.gg/ipe?extensions=XPathView.xpath.functionProvider) | [`XPathFunctionProvider`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/functions/XPathFunctionProvider.java) |
| [XPathView.xsltRunnerExtension](https://jb.gg/ipe?extensions=XPathView.xsltRunnerExtension) | [`XsltRunnerExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/xslt/run/XsltRunnerExtension.java) |



## Android Plugin


### Android Plugin - Listeners

| Topic | Listener |
|-------|----------|
| [Companion#DATA_IS_READY_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.build.attribution.BuildAnalyzerStorageManager.Listener)  | [`Listener`](%gh-ij-android%/build-attribution/src/com/android/build/attribution/BuildAnalyzerStorageManager.kt) |
| [StatefulButtonNotifier#BUTTON_STATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.StatefulButtonNotifier)  ![Project-Level][project-level] | [`StatefulButtonNotifier`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/StatefulButtonNotifier.java) |
| [AvdLaunchListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.avdmanager.AvdLaunchListener)  | [`AvdLaunchListener`](%gh-ij-android%/android/src/com/android/tools/idea/avdmanager/AvdLaunchListener.java) |
| [IssueProviderListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.common.error.IssueProviderListener)  ![Project-Level][project-level] | [`IssueProviderListener`](%gh-ij-android%/designer/src/com/android/tools/idea/common/error/IssueProvider.kt) |
| [FastPreviewManager#FAST_PREVIEW_MANAGER_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.fast.FastPreviewManager.Companion.FastPreviewManagerListener)  | [`FastPreviewManagerListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/fast/FastPreviewManager.kt) |
| [LiveLiteralsService#DOCUMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.DocumentsUpdatedListener)  | [`DocumentsUpdatedListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsService#MANAGED_ELEMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.ManagedElementsUpdatedListener)  | [`ManagedElementsUpdatedListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsDeploymentReportService#LITERALS_DEPLOYED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.internal.LiveLiteralsDeploymentReportService.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/internal/LiveLiteralsDeploymentReportService.kt) |
| [DeviceMirroringSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.emulator.DeviceMirroringSettingsListener)  | [`DeviceMirroringSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/emulator/DeviceMirroringSettingsListener.java) |
| [EmulatorSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.emulator.EmulatorSettingsListener)  | [`EmulatorSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/emulator/EmulatorSettingsListener.java) |
| [GradleBuildState#GRADLE_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.build.GradleBuildListener)  | [`GradleBuildListener`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/build/GradleBuildListener.java) |
| [GradleSyncStateImplKt#GRADLE_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.sync.GradleSyncListenerWithRoot)  | [`GradleSyncListenerWithRoot`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/sync/GradleSyncListenerWittRoot.kt) |
| [Companion#PROJECT_APPLICATION_IDS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.logcat.ProjectApplicationIdsProvider.ProjectApplicationIdsListener)  | [`ProjectApplicationIdsListener`](%gh-ij-android%/logcat/src/com/android/tools/idea/logcat/ProjectApplicationIdsProvider.kt) |
| [MultiTemplateRenderer#TEMPLATE_RENDERER_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.npw.model.MultiTemplateRenderer.TemplateRendererListener)  | [`TemplateRendererListener`](%gh-ij-android%/android-npw/src/com/android/tools/idea/npw/model/MultiTemplateRenderer.kt) |
| [GradleProjectSystemBuildPublisher#PROJECT_SYSTEM_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemBuildManager.BuildListener)  | [`BuildListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemBuildManager.kt) |
| [ProjectSystemSyncUtil#PROJECT_SYSTEM_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemSyncManager.SyncResultListener)  | [`SyncResultListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemSyncManager.kt) |
| [ClearLogcatListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ClearLogcatListener)  | [`ClearLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ClearLogcatListener.kt) |
| [DeviceHeadsUpListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.DeviceHeadsUpListener)  | [`DeviceHeadsUpListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/DeviceHeadsUpListener.java) |
| [ShowLogcatListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ShowLogcatListener)  | [`ShowLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ShowLogcatListener.kt) |
| [TransportDeviceManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.transport.TransportDeviceManager.TransportDeviceManagerListener)  | [`TransportDeviceManagerListener`](%gh-ij-android%/android-transport/src/com/android/tools/idea/transport/TransportDeviceManager.java) |
| [ResourceFolderManager#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.android.facet.ResourceFolderManager.ResourceFolderListener)  ![Project-Level][project-level] | [`ResourceFolderListener`](%gh-ij-android%/android/src/org/jetbrains/android/facet/ResourceFolderManager.kt) |


### android-adb.xml

[`android-adb.xml`](%gh-ij-android%/android-adb/src/META-INF/android-adb.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.run.deviceNameRenderer](https://jb.gg/ipe?extensions=com.android.run.deviceNameRenderer) ![Non-Dynamic][non-dynamic] | [`DeviceNameRendererEx`](%gh-ij-android%/android-adb/src/com/android/tools/idea/ddms/DeviceNameRendererEx.java) |
| [com.android.tools.idea.deviceProvisioner](https://jb.gg/ipe?extensions=com.android.tools.idea.deviceProvisioner) ![Non-Dynamic][non-dynamic] | [`DeviceProvisionerFactory`](%gh-ij-android%/android-adb/src/com/android/tools/idea/deviceprovisioner/DeviceProvisionerFactory.kt) |

### android-kotlin-extensions-common.xml

[`android-kotlin-extensions-common.xml`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/src/META-INF/android-kotlin-extensions-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.android.model.androidModuleInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.android.model.androidModuleInfoProvider) ![Non-Dynamic][non-dynamic] | [`AndroidModuleInfoProvider`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/src/org/jetbrains/kotlin/android/model/AndroidModuleInfoProvider.kt) |

### android-kotlin.xml

[`android-kotlin.xml`](%gh-ij-android%/android/src/META-INF/android-kotlin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.androidDexer](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.androidDexer) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AndroidDexer`](%gh-ic%/plugins/kotlin/jvm-debugger/evaluation/src/org/jetbrains/kotlin/idea/debugger/evaluate/classLoading/AndroidDexer.kt) |

### android-lang.xml

[`android-lang.xml`](%gh-ij-android%/android-lang/src/META-INF/android-lang.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.lang.androidSql.contextProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.androidSql.contextProvider) ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android-lang/src/com/android/tools/idea/lang/androidSql/AndroidSqlContext.kt) |

### android-navigator.xml

[`android-navigator.xml`](%gh-ij-android%/android-navigator/src/META-INF/android-navigator.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.navigator.androidViewNodeProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.navigator.androidViewNodeProvider) ![Non-Dynamic][non-dynamic] | [`AndroidViewNodeProvider`](%gh-ij-android%/android-navigator/src/com/android/tools/idea/navigator/nodes/AndroidViewNodeProvider.kt) |

### android-npw.xml

[`android-npw.xml`](%gh-ij-android%/android-npw/src/META-INF/android-npw.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.moduleDescriptionProvider](https://jb.gg/ipe?extensions=com.android.moduleDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`ModuleDescriptionProvider`](%gh-ij-android%/android-npw/src/com/android/tools/idea/npw/module/ModuleDescriptionProvider.kt) |

### android-plugin-androidstudio.xml

[`android-plugin-androidstudio.xml`](%gh-ij-android%/android/src/META-INF/android-plugin-androidstudio.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.androidStudioInitializer](https://jb.gg/ipe?extensions=com.intellij.androidStudioInitializer) ![Non-Dynamic][non-dynamic] | `Runnable` |

### android-plugin.xml

[`android-plugin.xml`](%gh-ij-android%/android/src/META-INF/android-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.customProjectTypeImporter](https://jb.gg/ipe?extensions=com.android.customProjectTypeImporter) ![Non-Dynamic][non-dynamic] | [`CustomProjectTypeImporter`](%gh-ij-android%/android/src/com/android/tools/idea/project/CustomProjectTypeImporter.java) |
| [com.android.gradle.sync.postSyncProjectCleanupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectCleanupStep) ![Non-Dynamic][non-dynamic] | [`ProjectCleanupStep`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectCleanupStep.java) |
| [com.android.gradle.sync.postSyncProjectSetupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectSetupStep) ![Non-Dynamic][non-dynamic] | [`ProjectSetupStep`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectSetupStep.java) |
| [com.android.ide.androidConfigurableContributor](https://jb.gg/ipe?extensions=com.android.ide.androidConfigurableContributor) ![Non-Dynamic][non-dynamic] | [`AndroidConfigurableContributor`](%gh-ij-android%/android/src/com/android/tools/idea/structure/dialog/AndroidConfigurableContributor.kt) |
| [com.android.ide.moduleStructureConfigurableContributor](https://jb.gg/ipe?extensions=com.android.ide.moduleStructureConfigurableContributor) ![Non-Dynamic][non-dynamic] | [`ModuleStructureConfigurableContributor`](%gh-ij-android%/android/src/com/android/tools/idea/structure/dialog/ModuleStructureConfigurableContributor.java) |
| [com.android.ide.projectStructureItemsContributor](https://jb.gg/ipe?extensions=com.android.ide.projectStructureItemsContributor) ![Non-Dynamic][non-dynamic] | [`ProjectStructureItemsContributor`](%gh-ij-android%/android/src/com/android/tools/idea/structure/dialog/ProjectStructureItemsContributor.java) |
| [com.android.ide.sdkEventListener](https://jb.gg/ipe?extensions=com.android.ide.sdkEventListener) ![Non-Dynamic][non-dynamic] | [`AndroidSdkEventListener`](%gh-ij-android%/android/src/com/android/tools/idea/sdk/IdeSdks.java) |
| [com.android.logcat.filterProvider](https://jb.gg/ipe?extensions=com.android.logcat.filterProvider) ![Non-Dynamic][non-dynamic] | [`LogcatFilterProvider`](%gh-ij-android%/android/src/com/android/tools/idea/logcat/LogcatFilterProvider.java) |
| [com.android.moduleImporter](https://jb.gg/ipe?extensions=com.android.moduleImporter) ![Non-Dynamic][non-dynamic] | [`AndroidModuleImporter`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/AndroidModuleImporter.kt) |
| [com.android.rendering.renderErrorContributor](https://jb.gg/ipe?extensions=com.android.rendering.renderErrorContributor) ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/RenderErrorContributor.java) |
| [com.android.rendering.renderSecurityManagerOverrides](https://jb.gg/ipe?extensions=com.android.rendering.renderSecurityManagerOverrides) | [`RenderSecurityManagerOverrides`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/RenderSecurityManagerOverrides.kt) |
| [com.android.run.androidDebugger](https://jb.gg/ipe?extensions=com.android.run.androidDebugger) ![Non-Dynamic][non-dynamic] | [`AndroidDebugger`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/AndroidDebugger.java) |
| [com.android.run.androidLaunchTaskContributor](https://jb.gg/ipe?extensions=com.android.run.androidLaunchTaskContributor) ![Non-Dynamic][non-dynamic] | [`AndroidLaunchTaskContributor`](%gh-ij-android%/android/src/com/android/tools/idea/run/AndroidLaunchTaskContributor.java) |
| [com.android.run.createLaunchTasksProvider](https://jb.gg/ipe?extensions=com.android.run.createLaunchTasksProvider) ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android/src/com/android/tools/idea/run/tasks/LaunchTasksProvider.java) |
| [com.android.run.deployTargetProvider](https://jb.gg/ipe?extensions=com.android.run.deployTargetProvider) ![Non-Dynamic][non-dynamic] | [`DeployTargetProvider`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/DeployTargetProvider.java) |
| [com.android.tools.idea.analytics.ideBrandProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.analytics.ideBrandProvider) ![Non-Dynamic][non-dynamic] | [`IdeBrandProvider`](%gh-ij-android%/android/src/com/android/tools/idea/analytics/IdeBrandProvider.kt) |
| [com.android.tools.idea.databinding.layoutBindingSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.databinding.layoutBindingSupport) ![Non-Dynamic][non-dynamic] | [`LayoutBindingSupport`](%gh-ij-android%/android/src/com/android/tools/idea/databinding/LayoutBindingSupport.kt) |
| [com.android.tools.idea.lang.databinding.dataBindingCompletionSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.databinding.dataBindingCompletionSupport) ![Non-Dynamic][non-dynamic] | [`DataBindingCompletionSupport`](%gh-ij-android%/android/src/com/android/tools/idea/lang/databinding/DataBindingCompletionSupport.kt) |
| [com.android.tools.idea.lang.databinding.dataBindingExpressionSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.databinding.dataBindingExpressionSupport) ![Non-Dynamic][non-dynamic] | [`DataBindingExpressionSupport`](%gh-ij-android%/android/src/com/android/tools/idea/lang/databinding/DataBindingExpressionSupport.kt) |
| [com.android.tools.idea.layoutlib.layoutLibraryProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.layoutlib.layoutLibraryProvider) ![Non-Dynamic][non-dynamic] | [`LayoutLibraryProvider`](%gh-ij-android%/layoutlib-loader/src/com/android/tools/idea/layoutlib/LayoutLibraryLoader.java) |
| [com.android.tools.idea.ndk.nativeWorkspaceProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ndk.nativeWorkspaceProvider) ![Non-Dynamic][non-dynamic] | [`NativeWorkspaceProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ndk/NativeWorkspaceProvider.kt) |
| [com.android.tools.idea.run.editor.androidDebuggerInfoProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.run.editor.androidDebuggerInfoProvider) ![Non-Dynamic][non-dynamic] | [`AndroidDebuggerInfoProvider`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/AndroidDebuggerInfoProvider.java) |
| [com.android.tools.idea.sendFeedbackDescriptionProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.sendFeedbackDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`SendFeedbackDescriptionProvider`](%gh-ij-android%/android/src/com/android/tools/idea/actions/SendFeedbackDescriptionProvider.kt) |
| [com.android.tools.idea.ui.designer.overlays.overlayProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ui.designer.overlays.overlayProvider) ![Non-Dynamic][non-dynamic] | [`OverlayProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ui/designer/overlays/OverlayProvider.java) |
| [com.android.tools.idea.ui.guiTestingStatusProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ui.guiTestingStatusProvider) ![Non-Dynamic][non-dynamic] | [`GuiTestingStatusProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ui/GuiTestingStatusProvider.java) |
| [com.android.tools.idea.wizard.template.wizardTemplateProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.wizard.template.wizardTemplateProvider) ![Non-Dynamic][non-dynamic] | `WizardTemplateProvider` |
| [org.jetbrains.android.actions.newResourceCreationHandler](https://jb.gg/ipe?extensions=org.jetbrains.android.actions.newResourceCreationHandler) ![Non-Dynamic][non-dynamic] | [`NewResourceCreationHandler`](%gh-ij-android%/android/src/org/jetbrains/android/actions/NewResourceCreationHandler.java) |
| [org.jetbrains.android.uipreview.viewLoaderExtension](https://jb.gg/ipe?extensions=org.jetbrains.android.uipreview.viewLoaderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewLoaderExtension`](%gh-ij-android%/android/src/org/jetbrains/android/uipreview/ViewLoaderExtension.java) |

### android-templates.xml

[`android-templates.xml`](%gh-ij-android%/android-templates/src/META-INF/android-templates.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.templates.additionalTemplateActionsProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.templates.additionalTemplateActionsProvider) ![Non-Dynamic][non-dynamic] | [`AdditionalTemplateActionsProvider`](%gh-ij-android%/android-templates/src/com/android/tools/idea/templates/AdditionalTemplateActionsProvider.java) |

### app-inspector.xml

[`app-inspector.xml`](%gh-ij-android%/app-inspection/inspector/ide/src/META-INF/app-inspector.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.appinspection.inspector.ide.appInspectorTabProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.appinspection.inspector.ide.appInspectorTabProvider) ![Non-Dynamic][non-dynamic] | [`AppInspectorTabProvider`](%gh-ij-android%/app-inspection/inspector/ide/src/com/android/tools/idea/appinspection/inspector/ide/AppInspectorTabProvider.kt) |

### assistant.xml

[`assistant.xml`](%gh-ij-android%/assistant/src/META-INF/assistant.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.assistant.actionHandler](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.actionHandler) ![Non-Dynamic][non-dynamic] | [`AssistActionHandler`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistActionHandler.java) |
| [com.android.tools.idea.assistant.actionStateManager](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.actionStateManager) ![Non-Dynamic][non-dynamic] | [`AssistActionStateManager`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistActionStateManager.java) |
| [com.android.tools.idea.assistant.assistantBundleCreator](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.assistantBundleCreator) ![Non-Dynamic][non-dynamic] | [`AssistantBundleCreator`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistantBundleCreator.java) |
| [com.android.tools.idea.assistant.navlistener](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.navlistener) ![Non-Dynamic][non-dynamic] | [`AssistNavListener`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistNavListener.java) |
| [com.android.tools.idea.assistant.panelFactory](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.panelFactory) ![Non-Dynamic][non-dynamic] | [`PanelFactory`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/PanelFactory.java) |
| [com.android.tools.idea.assistant.scrollHandler](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.scrollHandler) ![Non-Dynamic][non-dynamic] | [`ScrollHandler`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/ScrollHandler.java) |

### compose-designer.xml

[`compose-designer.xml`](%gh-ij-android%/compose-designer/src/META-INF/compose-designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.compose.preview.composeEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.compose.preview.composeEditorNotificationProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotifications.java) |

### customview.xml

[`customview.xml`](%gh-ij-android%/designer/customview/src/META-INF/customview.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.customview.preview.customViewEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.customview.preview.customViewEditorNotificationProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotifications.java) |

### designer.xml

[`designer.xml`](%gh-ij-android%/designer/src/META-INF/designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider) ![Non-Dynamic][non-dynamic] | [`PreviewRepresentationProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/editor/multirepresentation/PreviewRepresentationProvider.kt) |
| [com.android.tools.idea.uibuilder.editorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.editorNotificationProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotifications.java) |
| [com.android.tools.idea.uibuilder.handlers.viewHandlerProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.handlers.viewHandlerProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewHandlerProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/handlers/ViewHandlerProvider.kt) |

### device-manager.xml

[`device-manager.xml`](%gh-ij-android%/device-manager/src/META-INF/device-manager.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.android.deviceManagerTab](https://jb.gg/ipe?extensions=org.jetbrains.android.deviceManagerTab) ![Non-Dynamic][non-dynamic] | [`DeviceManagerTab`](%gh-ij-android%/device-manager/src/com/android/tools/idea/devicemanager/DeviceManagerTab.java) |

### gradle-dsl.xml

[`gradle-dsl.xml`](%gh-ij-android%/gradle-dsl/resources/META-INF/gradle-dsl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.gradleModelProvider](https://jb.gg/ipe?extensions=com.android.tools.gradleModelProvider) ![Non-Dynamic][non-dynamic] | [`GradleModelProvider`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/api/GradleModelProvider.java) |
| [com.android.tools.idea.gradle.dsl.transformerFactory](https://jb.gg/ipe?extensions=com.android.tools.idea.gradle.dsl.transformerFactory) ![Internal API][internal] | [`GradleDslTransformerFactory`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/parser/GradleDslTransformerFactory.java) |
| [org.jetbrains.idea.gradle.dsl.blockModel](https://jb.gg/ipe?extensions=org.jetbrains.idea.gradle.dsl.blockModel) ![Experimental API][experimental] | [`BlockModelProvider`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/model/GradleBlockModelMap.java) |

### lint-plugin.xml

[`lint-plugin.xml`](%gh-ij-android%/lint/src/META-INF/lint-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.lint.common.lintIdeSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintIdeSupport) ![Non-Dynamic][non-dynamic] | [`LintIdeSupport`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeSupport.kt) |
| [com.android.tools.idea.lint.common.lintQuickFixProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintQuickFixProvider) ![Non-Dynamic][non-dynamic] | [`LintIdeQuickFixProvider`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeQuickFixProvider.java) |

### project-system-plugin.xml

[`project-system-plugin.xml`](%gh-ij-android%/project-system/src/META-INF/project-system-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.androidStartupActivity](https://jb.gg/ipe?extensions=com.android.androidStartupActivity) | [`AndroidStartupActivity`](%gh-ij-android%/project-system/src/com/android/tools/idea/AndroidStartupActivity.kt) |
| [com.android.project.projectsystem](https://jb.gg/ipe?extensions=com.android.project.projectsystem) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AndroidProjectSystemProvider`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/AndroidProjectSystemProvider.kt) |

### resources-explorer.xml

[`resources-explorer.xml`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/META-INF/resources-explorer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.resourceImporter](https://jb.gg/ipe?extensions=com.android.resourceImporter) ![Non-Dynamic][non-dynamic] | [`ResourceImporter`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/plugin/ResourceImporter.kt) |
| [com.android.resourceViewer](https://jb.gg/ipe?extensions=com.android.resourceViewer) ![Non-Dynamic][non-dynamic] | [`DesignAssetRenderer`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/plugin/DesignAssetRenderer.kt) |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
