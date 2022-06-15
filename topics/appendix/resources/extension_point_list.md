[//]: # (title: Extension Point and Listener List)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

1407 Extension Points and 210 Listeners for IntelliJ Platform %ijPlatform%

- [](#intellij-platform)
- [](#intellij-community-plugins)
- [](#android-plugin)

> Dedicated Extension Point/Listener Lists specific to IDEs are available under _Part VIII — Product Specific_.
>
> See also [](explore_api.md) for more information and strategies.
>
{type="tip"}

<chunk id="ep_list_legend">

> See [](plugin_extensions.md) on how to declare extensions in your plugin.
>
> See [](plugin_listeners.md) on how to register listeners.
>
{type="tip"}

## Legend

### Listeners

**Topic** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Listener** links corresponding listener to implement.

### Extension Points

**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is related Extension Point class.

### Tags

| Icon                              | Description                         | Details                                                                                                                                                                                                                                   |
|-----------------------------------|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![Deprecated][deprecated]         | Deprecated API                      | Please see code documentation for replacement                                                                                                                                                                                             |
| ![Experimental API][experimental] | Experimental API                    | Annotated with [`@ApiStatus.Experimental`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), API might be altered or removed without prior notice                 |
| ![Internal API][internal]         | Internal API                        | Annotated with [`@ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), should not be used by 3rd party, see [](api_internal.md)                 |
| ![Project-Level][project-level]   | Project-Level Extension Point/Topic | Can have [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) as constructor parameter<br/><br/>Extension Point: Declared with `area="IDEA_PROJECT"`<br/>Listener: registered in `<projectListeners>` |
| ![Non-Dynamic][non-dynamic]       | Non-Dynamic Extension Point         | Installation/update of plugin requires IDE restart ([Dynamic Plugins](dynamic_plugins.md))                                                                                                                                                |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
</chunk>

## IntelliJ Platform


### IntelliJ Platform - Listeners

| Topic | Listener |
|-------|----------|
| [ProblemsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.analysis.problemsView.ProblemsListener)  | [`ProblemsListener`](upsource:///platform/lang-impl/src/com/intellij/analysis/problemsView/ProblemsListener.kt) |
| [EditorOptionsListener#FOLDING_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#APPEARANCE_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#OPTIONS_PANEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#SMART_KEYS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#GUTTER_ICONS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [ReaderModeSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.actions.ReaderModeListener)  ![Project-Level][project-level] | [`ReaderModeListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/actions/ReaderModeListener.kt) |
| [CodeVisionSettings#CODE_LENS_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.codeVision.settings.CodeVisionSettings.CodeVisionSettingsListener)  | [`CodeVisionSettingsListener`](upsource:///platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettings.kt) |
| [CompletionPhaseListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionPhaseListener)  | [`CompletionPhaseListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPhaseListener.java) |
| [DaemonCodeAnalyzer#DAEMON_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.DaemonCodeAnalyzer.DaemonListener)  | [`DaemonListener`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java) |
| [EditorTrackerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.EditorTrackerListener)  ![Project-Level][project-level] | [`EditorTrackerListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/EditorTrackerListener.java) |
| [FileHighlightingSettingListener#SETTING_CHANGE](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSettingListener)  | [`FileHighlightingSettingListener`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/FileHighlightingSettingListener.java) |
| [DocumentationComponentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.documentation.DocumentationComponentListener)  | [`DocumentationComponentListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationComponentListener.java) |
| [EditorHintListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.EditorHintListener)  | [`EditorHintListener`](upsource:///platform/platform-impl/src/com/intellij/codeInsight/hint/EditorHintListener.java) |
| [ExternalParameterInfoChangesProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.ExternalParameterInfoChangesProvider)  | [`ExternalParameterInfoChangesProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hint/ExternalParameterInfoChangesProvider.java) |
| [InlayHintsSettings#INLAY_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hints.InlayHintsSettings.SettingsListener)  | [`SettingsListener`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSettings.kt) |
| [LookupManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.lookup.LookupManagerListener)  ![Project-Level][project-level] | [`LookupManagerListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupManagerListener.java) |
| [TemplateManager#TEMPLATE_STARTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.template.TemplateManagerListener)  ![Project-Level][project-level] | [`TemplateManagerListener`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/template/TemplateManagerListener.java) |
| [GlobalInspectionContextEx#INSPECT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInspection.ex.InspectListener)  | [`InspectListener`](upsource:///platform/analysis-impl/src/com/intellij/codeInspection/ex/InspectListener.java) |
| [PasswordSafeSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.credentialStore.PasswordSafeSettingsListener)  | [`PasswordSafeSettingsListener`](upsource:///platform/credential-store/src/PasswordSafeSettingsListener.java) |
| [IdePerformanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.IdePerformanceListener)  | [`IdePerformanceListener`](upsource:///platform/core-api/src/com/intellij/diagnostic/IdePerformanceListener.java) |
| [RunnablesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.RunnablesListener)  ![Experimental API][experimental] ![Internal API][internal] | [`RunnablesListener`](upsource:///platform/platform-impl/src/com/intellij/diagnostic/RunnablesListener.java) |
| [DvcsBranchManager#DVCS_BRANCH_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.branch.DvcsBranchManager.DvcsBranchManagerListener)  | [`DvcsBranchManagerListener`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/branch/DvcsBranchManager.java) |
| [VcsRepositoryManager#VCS_REPOSITORY_MAPPING_UPDATED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.repo.VcsRepositoryMappingListener)  | [`VcsRepositoryMappingListener`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryMappingListener.java) |
| [ExecutionManager#EXECUTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionListener)  ![Project-Level][project-level] | [`ExecutionListener`](upsource:///platform/execution/src/com/intellij/execution/ExecutionListener.java) |
| [ExecutionTargetManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionTargetListener)  | [`ExecutionTargetListener`](upsource:///platform/execution/src/com/intellij/execution/ExecutionTargetListener.java) |
| [RunManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.RunManagerListener)  | [`RunManagerListener`](upsource:///platform/platform-impl/src/com/intellij/execution/RunManagerListener.java) |
| [RunDashboardManager#DASHBOARD_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.dashboard.RunDashboardListener)  | [`RunDashboardListener`](upsource:///platform/execution/src/com/intellij/execution/dashboard/RunDashboardListener.java) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.process.elevation.settings.ElevationSettings.Listener)  | [`Listener`](upsource:///platform/elevation/src/com/intellij/execution/process/elevation/settings/ElevationSettings.kt) |
| [RunToolbarSlotManager#RUN_TOOLBAR_SLOT_CONFIGURATION_MAP_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.runToolbar.RunToolbarSlotsConfigurationListener)  ![Project-Level][project-level] | [`RunToolbarSlotsConfigurationListener`](upsource:///platform/execution-impl/src/com/intellij/execution/runToolbar/RunToolbarSlotManager.kt) |
| [ServiceEventListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.services.ServiceEventListener)  | [`ServiceEventListener`](upsource:///platform/lang-api/src/com/intellij/execution/services/ServiceEventListener.java) |
| [SMTRunnerEventsListener#TEST_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener)  | [`SMTRunnerEventsListener`](upsource:///platform/smRunner/src/com/intellij/execution/testframework/sm/runner/SMTRunnerEventsListener.java) |
| [RunContentManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunContentWithExecutorListener)  | [`RunContentWithExecutorListener`](upsource:///platform/execution/src/com/intellij/execution/ui/RunContentWithExecutorListener.java) |
| [FacetManager#FACETS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.facet.FacetManagerListener)  | [`FacetManagerListener`](upsource:///platform/lang-core/src/com/intellij/facet/FacetManagerListener.java) |
| [FeaturesRegistryListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeaturesRegistryListener)  ![Experimental API][experimental] | [`FeaturesRegistryListener`](upsource:///platform/platform-api/src/com/intellij/featureStatistics/FeaturesRegistryListener.java) |
| [FindManager#FIND_MODEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.find.FindModelListener)  | [`FindModelListener`](upsource:///platform/refactoring/src/com/intellij/find/FindModelListener.java) |
| [AppLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.AppLifecycleListener)  | [`AppLifecycleListener`](upsource:///platform/platform-impl/src/com/intellij/ide/AppLifecycleListener.java) |
| [FrameStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.FrameStateListener)  | [`FrameStateListener`](upsource:///platform/platform-api/src/com/intellij/ide/FrameStateListener.java) |
| [PowerSaveMode#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.PowerSaveMode.Listener)  | [`Listener`](upsource:///platform/core-api/src/com/intellij/ide/PowerSaveMode.java) |
| [SaveAndSyncHandlerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.SaveAndSyncHandlerListener)  ![Experimental API][experimental] | [`SaveAndSyncHandlerListener`](upsource:///platform/platform-api/src/com/intellij/ide/SaveAndSyncHandlerListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmark.BookmarksListener)  | [`BookmarksListener`](upsource:///platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmarks.BookmarksListener)  | [`BookmarksListener`](upsource:///platform/lang-impl/src/com/intellij/ide/bookmarks/BookmarksListener.java) |
| [BatchFileChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.file.BatchFileChangeListener)  | [`BatchFileChangeListener`](upsource:///platform/platform-impl/src/com/intellij/ide/file/BatchFileChangeListener.java) |
| [TrustStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.impl.TrustStateListener)  ![Experimental API][experimental] | [`TrustStateListener`](upsource:///platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) |
| [LightEditServiceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.lightEdit.LightEditServiceListener)  ![Experimental API][experimental] | [`LightEditServiceListener`](upsource:///platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditServiceListener.java) |
| [NavBarModelListener#NAV_BAR](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.NavBarModelListener)  ![Project-Level][project-level] | [`NavBarModelListener`](upsource:///platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelListener.java) |
| [ExperimentalToolbarStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.experimental.ExperimentalToolbarStateListener)  ![Project-Level][project-level] | [`ExperimentalToolbarStateListener`](upsource:///platform/lang-impl/src/com/intellij/ide/navigationToolbar/experimental/NewToolbarRootPaneExtension.kt) |
| [DynamicPluginListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.DynamicPluginListener)  | [`DynamicPluginListener`](upsource:///platform/core-api/src/com/intellij/ide/plugins/DynamicPluginListener.kt) |
| [PluginRepositoryAuthListener#PLUGIN_REPO_AUTH_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.auth.PluginRepositoryAuthListener)  | [`PluginRepositoryAuthListener`](upsource:///platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthListener.java) |
| [ProjectViewListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.impl.ProjectViewListener)  | [`ProjectViewListener`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewListener.java) |
| [LafManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.LafManagerListener)  | [`LafManagerListener`](upsource:///platform/platform-api/src/com/intellij/ide/ui/LafManagerListener.java) |
| [UISettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.UISettingsListener)  | [`UISettingsListener`](upsource:///platform/editor-ui-api/src/com/intellij/ide/ui/UISettingsListener.java) |
| [VirtualFileAppearanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.VirtualFileAppearanceListener)  | [`VirtualFileAppearanceListener`](upsource:///platform/platform-api/src/com/intellij/ide/ui/VirtualFileAppearanceListener.java) |
| [ComponentHighlightingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.search.ComponentHighlightingListener)  | [`ComponentHighlightingListener`](upsource:///platform/platform-impl/src/com/intellij/ide/ui/search/ComponentHighlightingListener.java) |
| [SettingsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.scopeChooser.ScopeEditorPanel.SettingsChangedListener)  | [`SettingsChangedListener`](upsource:///platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeEditorPanel.java) |
| [EventLogConfigOptionsService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.internal.statistic.eventLog.EventLogConfigOptionsListener)  | [`EventLogConfigOptionsListener`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogConfigOptionsListener.java) |
| [ExternalResourceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javaee.ExternalResourceListener)  | [`ExternalResourceListener`](upsource:///xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceListener.java) |
| [LogModel#LOG_MODEL_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.notification.EventLogListener)  | [`EventLogListener`](upsource:///platform/platform-impl/src/com/intellij/notification/EventLogListener.java) |
| [Notifications#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.notification.Notifications)  | [`Notifications`](upsource:///platform/ide-core/src/com/intellij/notification/Notifications.java) |
| [ActionManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ActionManagerListener)  | [`ActionManagerListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionManagerListener.java) |
| [AnActionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ex.AnActionListener)  | [`AnActionListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ex/AnActionListener.java) |
| [ApplicationActivationListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.application.ApplicationActivationListener)  | [`ApplicationActivationListener`](upsource:///platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) |
| [CommandListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.CommandListener)  | [`CommandListener`](upsource:///platform/core-api/src/com/intellij/openapi/command/CommandListener.java) |
| [BatchUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.components.impl.stores.BatchUpdateListener)  ![Project-Level][project-level] | [`BatchUpdateListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/components/impl/stores/BatchUpdateListener.java) |
| [LatencyListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.actionSystem.LatencyListener)  | [`LatencyListener`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/LatencyListener.java) |
| [EditorColorsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.EditorColorsListener)  | [`EditorColorsListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsListener.java) |
| [EditorColorsManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.impl.EditorColorsManagerListener)  ![Internal API][internal] | [`EditorColorsManagerListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/colors/impl/EditorColorsManagerListener.kt) |
| [DocumentBulkUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.ex.DocumentBulkUpdateListener)  ![Deprecated][deprecated] | [`DocumentBulkUpdateListener`](upsource:///platform/core-impl/src/com/intellij/openapi/editor/ex/DocumentBulkUpdateListener.java) |
| [ExternalSystemProjectNotificationAware#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectNotificationAware.Listener)  | [`Listener`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/autoimport/ExternalSystemProjectNotificationAware.kt) |
| [ProjectDataImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener)  ![Project-Level][project-level] | [`ProjectDataImportListener`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataImportListener.java) |
| [AppTopics#FILE_DOCUMENT_SYNC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  | [`FileDocumentManagerListener`](upsource:///platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [FileEditorManagerListener#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener)  ![Project-Level][project-level] | [`FileEditorManagerListener`](upsource:///platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [Before#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener.Before)  | [`Before`](upsource:///platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [RecentPlacesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.IdeDocumentHistoryImpl.RecentPlacesListener)  ![Project-Level][project-level] | [`RecentPlacesListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/IdeDocumentHistoryImpl.java) |
| [FileTypeManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileTypes.FileTypeListener)  | [`FileTypeListener`](upsource:///platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeListener.java) |
| [KeymapManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.KeymapManagerListener)  | [`KeymapManagerListener`](upsource:///platform/platform-api/src/com/intellij/openapi/keymap/KeymapManagerListener.java) |
| [KeymapListener#CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.impl.ui.KeymapListener)  | [`KeymapListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/keymap/impl/ui/KeymapListener.java) |
| [AdvancedSettingsChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.advanced.AdvancedSettingsChangeListener)  | [`AdvancedSettingsChangeListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/options/advanced/AdvancedSettings.kt) |
| [ExternalUpdateRequest#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.ExternalUpdateRequest)  ![Experimental API][experimental] ![Internal API][internal] | [`ExternalUpdateRequest`](upsource:///platform/platform-impl/src/com/intellij/openapi/options/newEditor/ExternalUpdateRequest.java) |
| [ProgressManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.ProgressManagerListener)  | [`ProgressManagerListener`](upsource:///platform/core-api/src/com/intellij/openapi/progress/ProgressManagerListener.java) |
| [ProgressSuspender#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.impl.ProgressSuspender.SuspenderListener)  | [`SuspenderListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/progress/impl/ProgressSuspender.java) |
| [ProgressWindow#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.util.ProgressWindow.Listener)  | [`Listener`](upsource:///platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) |
| [DumbService#DUMB_MODE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.DumbService.DumbModeListener)  ![Project-Level][project-level] | [`DumbModeListener`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbService.java) |
| [ProjectTopics#MODULES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Project-Level][project-level] | [`ModuleListener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [ProjectManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectManagerListener)  | [`ProjectManagerListener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) |
| [ProjectLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.ProjectLifecycleListener)  | [`ProjectLifecycleListener`](upsource:///platform/platform-api/src/com/intellij/openapi/project/impl/ProjectLifecycleListener.java) |
| [ProjectJdkTable#JDK_TABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.projectRoots.ProjectJdkTable.Listener)  | [`Listener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) |
| [AdditionalLibraryRootsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.AdditionalLibraryRootsListener)  ![Experimental API][experimental] ![Project-Level][project-level] | [`AdditionalLibraryRootsListener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsListener.java) |
| [ProjectTopics#PROJECT_ROOTS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Project-Level][project-level] | [`ModuleRootListener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [RegistryManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.util.registry.RegistryValueListener)  | [`RegistryValueListener`](upsource:///platform/util/src/com/intellij/openapi/util/registry/RegistryValueListener.java) |
| [BranchChangeListener#VCS_BRANCH_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchChangeListener)  | [`BranchChangeListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/BranchChangeListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED_IN_PLUGIN](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.PluginVcsMappingListener)  ![Project-Level][project-level] | [`PluginVcsMappingListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/PluginVcsMappingListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.VcsMappingListener)  ![Project-Level][project-level] | [`VcsMappingListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsMappingListener.java) |
| [ChangeListAvailabilityListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListAvailabilityListener)  ![Internal API][internal] | [`ChangeListAvailabilityListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListAvailabilityListener.java) |
| [ChangeListListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListListener)  | [`ChangeListListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListListener.java) |
| [ChangesViewModifier#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewModifier.ChangesViewModifierListener)  ![Project-Level][project-level] | [`ChangesViewModifierListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [ChangeListManagerImpl#LISTS_LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.LocalChangeListsLoadedListener)  ![Project-Level][project-level] | [`LocalChangeListsLoadedListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalChangeListsLoadedListener.java) |
| [VcsAnnotationRefresher#LOCAL_CHANGES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsAnnotationRefresher)  | [`VcsAnnotationRefresher`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsAnnotationRefresher.java) |
| [VcsEditorTabFilesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsEditorTabFilesListener)  | [`VcsEditorTabFilesListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsEditorTabFilesManager.kt) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsFreezingProcess.Listener)  | [`Listener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsFreezingProcess.java) |
| [VcsManagedFilesHolder#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsManagedFilesHolder.VcsManagedFilesHolderListener)  ![Project-Level][project-level] | [`VcsManagedFilesHolderListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [CommittedChangesCache#COMMITTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesListener)  ![Project-Level][project-level] | [`CommittedChangesListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesListener.java) |
| [CommittedChangesTreeBrowser#ITEMS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesTreeBrowser.CommittedChangesReloadListener)  | [`CommittedChangesReloadListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesTreeBrowser.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED_RESPONSE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.DetailedNotification)  | [`DetailedNotification`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.Notification)  | [`Notification`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [ChangesViewContentManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ui.ChangesViewContentManagerListener)  | [`ChangesViewContentManagerListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentManagerListener.kt) |
| [ProjectLevelVcsManagerEx#VCS_ACTIVATED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.ex.VcsActivationListener)  ![Project-Level][project-level] | [`VcsActivationListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/ex/VcsActivationListener.java) |
| [LineStatusTrackerSettingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerSettingListener)  | [`LineStatusTrackerSettingListener`](upsource:///platform/lang-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerSettingListener.java) |
| [UpdatedFilesListener#UPDATED_FILES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.update.UpdatedFilesListener)  | [`UpdatedFilesListener`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/update/UpdatedFilesListener.java) |
| [EncodingManagerListener#ENCODING_MANAGER_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.encoding.EncodingManagerListener)  | [`EncodingManagerListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/encoding/EncodingManagerListener.java) |
| [VirtualFileManager#VFS_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.newvfs.BulkFileListener)  | [`BulkFileListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) |
| [VirtualFilePointerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.pointers.VirtualFilePointerListener)  | [`VirtualFilePointerListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/pointers/VirtualFilePointerListener.java) |
| [Info#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.StatusBarInfo)  ![Project-Level][project-level] | [`StatusBarInfo`](upsource:///platform/ide-core/src/com/intellij/openapi/wm/StatusBarInfo.java) |
| [ToolWindowManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.ToolWindowManagerListener)  ![Project-Level][project-level] | [`ToolWindowManagerListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/ex/ToolWindowManagerListener.java) |
| [TitleInfoProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.TitleInfoProvider.TitleInfoProviderListener)  ![Internal API][internal] | [`TitleInfoProviderListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [WelcomeBalloonLayoutImpl#BALLOON_NOTIFICATION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeBalloonLayoutImpl.BalloonNotificationListener)  | [`BalloonNotificationListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeBalloonLayoutImpl.java) |
| [WelcomeScreenComponentListener#COMPONENT_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenComponentListener)  | [`WelcomeScreenComponentListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeScreenComponentListener.java) |
| [ModuleAttachListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.ModuleAttachListener)  | [`ModuleAttachListener`](upsource:///platform/lang-impl/src/com/intellij/platform/ModuleAttachListener.kt) |
| [ExternalSystemTestUtil#SETTINGS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.externalSystem.testFramework.TestExternalSystemSettingsListener)  | [`TestExternalSystemSettingsListener`](upsource:///platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/TestExternalSystemSettingsListener.java) |
| [ProblemListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.problems.ProblemListener)  ![Project-Level][project-level] | [`ProblemListener`](upsource:///platform/analysis-api/src/com/intellij/problems/ProblemListener.java) |
| [ProfileChangeAdapter#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.profile.ProfileChangeAdapter)  ![Project-Level][project-level] | [`ProfileChangeAdapter`](upsource:///platform/analysis-api/src/com/intellij/profile/ProfileChangeAdapter.java) |
| [PsiDocumentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.PsiDocumentListener)  | [`PsiDocumentListener`](upsource:///platform/core-api/src/com/intellij/psi/PsiDocumentListener.java) |
| [PsiManagerImpl#ANY_PSI_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.AnyPsiChangeListener)  | [`AnyPsiChangeListener`](upsource:///platform/core-impl/src/com/intellij/psi/impl/AnyPsiChangeListener.java) |
| [PsiDocumentTransactionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.PsiDocumentTransactionListener)  | [`PsiDocumentTransactionListener`](upsource:///platform/core-impl/src/com/intellij/psi/impl/PsiDocumentTransactionListener.java) |
| [PsiModificationTracker#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level] | [`Listener`](upsource:///platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [RefactoringEventListener#REFACTORING_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.refactoring.listeners.RefactoringEventListener)  | [`RefactoringEventListener`](upsource:///platform/refactoring/src/com/intellij/refactoring/listeners/RefactoringEventListener.java) |
| [RemoteMappingsListener#REMOTE_MAPPINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.remote.RemoteMappingsListener)  | [`RemoteMappingsListener`](upsource:///platform/platform-impl/src/com/intellij/remote/RemoteMappingsListener.java) |
| [RemoteServerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.configuration.RemoteServerListener)  | [`RemoteServerListener`](upsource:///platform/remote-servers/api/src/com/intellij/remoteServer/configuration/RemoteServerListener.java) |
| [ServerConnectionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.runtime.ServerConnectionListener)  | [`ServerConnectionListener`](upsource:///platform/remote-servers/api/src/com/intellij/remoteServer/runtime/ServerConnectionListener.java) |
| [SpellCheckerEngineListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spellchecker.engine.SpellCheckerEngineListener)  | [`SpellCheckerEngineListener`](upsource:///spellchecker/src/com/intellij/spellchecker/engine/SpellCheckerEngineListener.java) |
| [ProjectTaskListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.task.ProjectTaskListener)  ![Project-Level][project-level] | [`ProjectTaskListener`](upsource:///platform/lang-api/src/com/intellij/task/ProjectTaskListener.java) |
| [ToolWindowViewModelListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.viewModel.extraction.ToolWindowViewModelListener)  ![Project-Level][project-level] | [`ToolWindowViewModelListener`](upsource:///platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelListener.java) |
| [UnindexedFilesUpdaterListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.UnindexedFilesUpdaterListener)  | [`UnindexedFilesUpdaterListener`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/UnindexedFilesUpdaterListener.java) |
| [CommitModeManager#COMMIT_MODE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.CommitModeListener)  ![Project-Level][project-level] | [`CommitModeListener`](upsource:///platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitModeManager#SETTINGS](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.SettingsListener)  | [`SettingsListener`](upsource:///platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitMessageInspectionProfile#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.message.CommitMessageInspectionProfile.ProfileListener)  | [`ProfileListener`](upsource:///platform/vcs-impl/src/com/intellij/vcs/commit/message/CommitMessageInspectionProfile.java) |
| [VcsProjectLog#VCS_PROJECT_LOG_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.impl.VcsProjectLog.ProjectLogListener)  ![Project-Level][project-level] | [`ProjectLogListener`](upsource:///platform/vcs-log/impl/src/com/intellij/vcs/log/impl/VcsProjectLog.java) |
| [JpsProjectLoadedListener#LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsProjectLoadedListener)  ![Project-Level][project-level] | [`JpsProjectLoadedListener`](upsource:///platform/projectModel-impl/src/com/intellij/workspaceModel/ide/JpsProjectLoadedListener.kt) |
| [WorkspaceModelTopics#CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.WorkspaceModelChangeListener)  ![Project-Level][project-level] | [`WorkspaceModelChangeListener`](upsource:///platform/projectModel-impl/src/com/intellij/workspaceModel/ide/WorkspaceModelTopics.kt) |
| [XDebuggerManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XDebuggerManagerListener)  | [`XDebuggerManagerListener`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/XDebuggerManagerListener.java) |
| [XBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.breakpoints.XBreakpointListener)  | [`XBreakpointListener`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointListener.java) |
| [XDependentBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.impl.breakpoints.XDependentBreakpointListener)  | [`XDependentBreakpointListener`](upsource:///platform/xdebugger-impl/src/com/intellij/xdebugger/impl/breakpoints/XDependentBreakpointListener.java) |
| [BreadcrumbsInitListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xml.breadcrumbs.BreadcrumbsInitListener)  ![Internal API][internal] | [`BreadcrumbsInitListener`](upsource:///platform/platform-impl/src/com/intellij/xml/breadcrumbs/BreadcrumbsInitListener.java) |
| [TodoConfiguration#PROPERTY_CHANGE](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [IndexPatternProvider#INDEX_PATTERNS_CHANGED](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [UsageFilteringRuleProvider#RULES_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [SeverityRegistrar#SEVERITIES_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [JsonSchemaVfsListener#JSON_SCHEMA_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [JsonSchemaVfsListener#JSON_DEPS_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [StructureViewWrapperImpl#STRUCTURE_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [RemoteRevisionsCache#REMOTE_VERSION_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [ShelveChangesManager#SHELF_TOPIC](https://jb.gg/ipe/listeners?topics=javax.swing.event.ChangeListener)  | `ChangeListener` |
| [NotebookOutputInlayControllerKt#OUTPUT_LISTENER](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.visualization.outputs.OutputListener)  | [`OutputListener`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputInlayController.kt) |
| [GraphicsPanelKt#CHANGE_DARK_MODE_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.notebooks.visualization.r.inlays.components.DarkModeNotifier)  | [`DarkModeNotifier`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/GraphicsPanel.kt) |


### Analysis.xml

[`Analysis.xml`](upsource:///platform/analysis-api/resources/META-INF/Analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.containerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.containerProvider) | [`ContainerProvider`](upsource:///platform/core-api/src/com/intellij/codeInsight/ContainerProvider.java) |
| [com.intellij.codeInsight.signatureHelp](https://jb.gg/ipe?extensions=com.intellij.codeInsight.signatureHelp) | [`SignatureHelpProvider`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/signatureHelp/SignatureHelpProvider.java) |
| [com.intellij.codeInspection.InspectionExtension](https://jb.gg/ipe?extensions=com.intellij.codeInspection.InspectionExtension) | [`InspectionExtensionsFactory`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/lang/InspectionExtensionsFactory.java) |
| [com.intellij.completion.contributor](https://jb.gg/ipe?extensions=com.intellij.completion.contributor) | [`CompletionContributor`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) |
| [com.intellij.completion.skip](https://jb.gg/ipe?extensions=com.intellij.completion.skip) | [`CompletionPreselectSkipper`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionPreselectSkipper.java) |
| [com.intellij.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.documentationProvider) | [`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.fileContextProvider](https://jb.gg/ipe?extensions=com.intellij.fileContextProvider) ![Project-Level][project-level] | [`FileContextProvider`](upsource:///platform/core-api/src/com/intellij/psi/FileContextProvider.java) |
| [com.intellij.fileLookupInfoProvider](https://jb.gg/ipe?extensions=com.intellij.fileLookupInfoProvider) | [`FileLookupInfoProvider`](upsource:///platform/analysis-api/src/com/intellij/psi/file/FileLookupInfoProvider.java) |
| [com.intellij.globalInspection](https://jb.gg/ipe?extensions=com.intellij.globalInspection) | [`GlobalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/GlobalInspectionTool.java) |
| [com.intellij.gotoDeclarationHandler](https://jb.gg/ipe?extensions=com.intellij.gotoDeclarationHandler) | [`GotoDeclarationHandler`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/navigation/actions/GotoDeclarationHandler.java) |
| [com.intellij.highlightErrorFilter](https://jb.gg/ipe?extensions=com.intellij.highlightErrorFilter) ![Project-Level][project-level] | [`HighlightErrorFilter`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/highlighting/HighlightErrorFilter.java) |
| [com.intellij.inspectionToolProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionToolProvider) | [`InspectionToolProvider`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/InspectionToolProvider.java) |
| [com.intellij.inspectionsReportConverter](https://jb.gg/ipe?extensions=com.intellij.inspectionsReportConverter) | [`InspectionsReportConverter`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/InspectionsReportConverter.java) |
| [com.intellij.intentionAction](https://jb.gg/ipe?extensions=com.intellij.intentionAction) | [`IntentionAction`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) |
| [com.intellij.lang.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.documentationProvider) | [`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.lang.inspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.lang.inspectionSuppressor) | [`InspectionSuppressor`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/InspectionSuppressor.java) |
| [com.intellij.languageInjector](https://jb.gg/ipe?extensions=com.intellij.languageInjector) | [`LanguageInjector`](upsource:///platform/analysis-api/src/com/intellij/psi/LanguageInjector.java) |
| [com.intellij.liveTemplateContext](https://jb.gg/ipe?extensions=com.intellij.liveTemplateContext) | [`TemplateContextType`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/template/TemplateContextType.java) |
| [com.intellij.liveTemplateMacro](https://jb.gg/ipe?extensions=com.intellij.liveTemplateMacro) | [`Macro`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/template/Macro.java) |
| [com.intellij.localFileSystemTimestampEvaluator](https://jb.gg/ipe?extensions=com.intellij.localFileSystemTimestampEvaluator) ![Internal API][internal] | [`LocalFileSystemTimestampEvaluator`](upsource:///platform/vfs-impl/src/com/intellij/openapi/vfs/impl/local/LocalFileSystemTimestampEvaluator.java) |
| [com.intellij.localInspection](https://jb.gg/ipe?extensions=com.intellij.localInspection) | [`LocalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) |
| [com.intellij.weigher](https://jb.gg/ipe?extensions=com.intellij.weigher) | [`Weigher`](upsource:///platform/analysis-api/src/com/intellij/psi/Weigher.java) |

### AnalysisImpl.xml

[`AnalysisImpl.xml`](upsource:///platform/analysis-impl/resources/META-INF/AnalysisImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.elementLookupRenderer](https://jb.gg/ipe?extensions=com.intellij.elementLookupRenderer) ![Deprecated][deprecated] | [`ElementLookupRenderer`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/lookup/impl/ElementLookupRenderer.java) |
| [com.intellij.liveTemplateOptionalProcessor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateOptionalProcessor) | [`TemplateOptionalProcessor`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplateOptionalProcessor.java) |
| [com.intellij.liveTemplatePreprocessor](https://jb.gg/ipe?extensions=com.intellij.liveTemplatePreprocessor) | [`TemplatePreprocessor`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplatePreprocessor.java) |
| [com.intellij.outerLanguageRangePatcher](https://jb.gg/ipe?extensions=com.intellij.outerLanguageRangePatcher) | [`OuterLanguageRangePatcher`](upsource:///platform/analysis-impl/src/com/intellij/psi/templateLanguages/TemplateDataElementType.java) |
| [com.intellij.psi.fileReferenceHelper](https://jb.gg/ipe?extensions=com.intellij.psi.fileReferenceHelper) | [`FileReferenceHelper`](upsource:///platform/analysis-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/FileReferenceHelper.java) |
| [com.intellij.resolveScopeEnlarger](https://jb.gg/ipe?extensions=com.intellij.resolveScopeEnlarger) | [`ResolveScopeEnlarger`](upsource:///platform/analysis-impl/src/com/intellij/psi/ResolveScopeEnlarger.java) |
| [com.intellij.resolveScopeProvider](https://jb.gg/ipe?extensions=com.intellij.resolveScopeProvider) | [`ResolveScopeProvider`](upsource:///platform/analysis-impl/src/com/intellij/psi/ResolveScopeProvider.java) |
| [com.intellij.severitiesProvider](https://jb.gg/ipe?extensions=com.intellij.severitiesProvider) ![Non-Dynamic][non-dynamic] | [`SeveritiesProvider`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SeveritiesProvider.java) |
| [com.intellij.useScopeEnlarger](https://jb.gg/ipe?extensions=com.intellij.useScopeEnlarger) | [`UseScopeEnlarger`](upsource:///platform/indexing-api/src/com/intellij/psi/search/UseScopeEnlarger.java) |
| [com.intellij.useScopeOptimizer](https://jb.gg/ipe?extensions=com.intellij.useScopeOptimizer) | [`ScopeOptimizer`](upsource:///platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |

### builtInServer.xml

[`builtInServer.xml`](upsource:///platform/built-in-server/resources/META-INF/builtInServer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.httpRequestHandler](https://jb.gg/ipe?extensions=com.intellij.httpRequestHandler) | [`HttpRequestHandler`](upsource:///platform/platform-util-netty/src/org/jetbrains/ide/HttpRequestHandler.kt) |
| [com.intellij.toolboxServiceHandler](https://jb.gg/ipe?extensions=com.intellij.toolboxServiceHandler) ![Internal API][internal] | [`ToolboxServiceHandler`](upsource:///platform/built-in-server/src/org/jetbrains/ide/ToolboxRestService.kt) |
| [org.jetbrains.binaryRequestHandler](https://jb.gg/ipe?extensions=org.jetbrains.binaryRequestHandler) ![Non-Dynamic][non-dynamic] | [`BinaryRequestHandler`](upsource:///platform/platform-util-netty/src/org/jetbrains/ide/BinaryRequestHandler.java) |
| [org.jetbrains.customPortServerManager](https://jb.gg/ipe?extensions=org.jetbrains.customPortServerManager) ![Non-Dynamic][non-dynamic] | [`CustomPortServerManager`](upsource:///platform/built-in-server-api/src/org/jetbrains/ide/CustomPortServerManager.kt) |
| [org.jetbrains.jsonRpcDomain](https://jb.gg/ipe?extensions=org.jetbrains.jsonRpcDomain) ![Internal API][internal] | `Object` |
| [org.jetbrains.webServerFileHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerFileHandler) | [`WebServerFileHandler`](upsource:///platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerFileHandler.kt) |
| [org.jetbrains.webServerPathHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerPathHandler) | [`WebServerPathHandler`](upsource:///platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerPathHandler.kt) |
| [org.jetbrains.webServerRootsProvider](https://jb.gg/ipe?extensions=org.jetbrains.webServerRootsProvider) | [`WebServerRootsProvider`](upsource:///platform/built-in-server-api/src/org/jetbrains/builtInWebServer/WebServerRootsProvider.kt) |

### com.intellij.platform.images

[`com.intellij.platform.images`](upsource:///images/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.images.themeFilter](https://jb.gg/ipe?extensions=com.intellij.images.themeFilter) | [`ThemeFilter`](upsource:///images/src/org/intellij/images/thumbnail/actions/ThemeFilter.java) |

### CompletionExtensionPoints.xml

[`CompletionExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/CompletionExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.wordCompletionFilter](https://jb.gg/ipe?extensions=com.intellij.codeInsight.wordCompletionFilter) | [`WordCompletionElementFilter`](upsource:///platform/lang-api/src/com/intellij/lang/WordCompletionElementFilter.java) |
| [com.intellij.completion.confidence](https://jb.gg/ipe?extensions=com.intellij.completion.confidence) | [`CompletionConfidence`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionConfidence.java) |
| [com.intellij.completion.ml.contextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.contextFeatures) ![Internal API][internal] | [`ContextFeatureProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/ml/ContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementFeatures) ![Internal API][internal] | [`ElementFeatureProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/ml/ElementFeatureProvider.java) |
| [com.intellij.completion.ml.model](https://jb.gg/ipe?extensions=com.intellij.completion.ml.model) ![Internal API][internal] | [`RankingModelProvider`](upsource:///platform/platform-impl/src/com/intellij/internal/ml/completion/RankingModelProvider.java) |
| [com.intellij.completion.plainTextSymbol](https://jb.gg/ipe?extensions=com.intellij.completion.plainTextSymbol) | [`PlainTextSymbolCompletionContributor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java) |
| [com.intellij.completion.preselectionBehaviourProvider](https://jb.gg/ipe?extensions=com.intellij.completion.preselectionBehaviourProvider) | [`CompletionPreselectionBehaviourProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPreselectionBehaviourProvider.java) |
| [com.intellij.createDirectoryCompletionContributor](https://jb.gg/ipe?extensions=com.intellij.createDirectoryCompletionContributor) | [`CreateDirectoryCompletionContributor`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/CreateDirectoryCompletionContributor.java) |
| [com.intellij.lookup.actionProvider](https://jb.gg/ipe?extensions=com.intellij.lookup.actionProvider) | [`LookupActionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupActionProvider.java) |
| [com.intellij.lookup.charFilter](https://jb.gg/ipe?extensions=com.intellij.lookup.charFilter) | [`CharFilter`](upsource:///platform/platform-impl/src/com/intellij/codeInsight/lookup/CharFilter.java) |
| [com.intellij.lookup.usageDetails](https://jb.gg/ipe?extensions=com.intellij.lookup.usageDetails) ![Internal API][internal] | [`LookupUsageDescriptor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupUsageDescriptor.java) |
| [com.intellij.templateParameterTraversalPolicy](https://jb.gg/ipe?extensions=com.intellij.templateParameterTraversalPolicy) | [`TemplateParameterTraversalPolicy`](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/TemplateParameterTraversalPolicy.java) |

### Core.xml

[`Core.xml`](upsource:///platform/core-api/resources/META-INF/Core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.applicationService](https://jb.gg/ipe?extensions=com.intellij.applicationService) | `n/a` |
| [com.intellij.backgroundPostStartupActivity](https://jb.gg/ipe?extensions=com.intellij.backgroundPostStartupActivity) | [`StartupActivity`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) |
| [com.intellij.editorFactoryDocumentListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryDocumentListener) | [`DocumentListener`](upsource:///platform/core-api/src/com/intellij/openapi/editor/event/DocumentListener.java) |
| [com.intellij.fileTypeDetector](https://jb.gg/ipe?extensions=com.intellij.fileTypeDetector) | [`FileTypeDetector`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeRegistry.java) |
| [com.intellij.filetype.decompiler](https://jb.gg/ipe?extensions=com.intellij.filetype.decompiler) | [`BinaryFileDecompiler`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/BinaryFileDecompiler.java) |
| [com.intellij.lang.elementManipulator](https://jb.gg/ipe?extensions=com.intellij.lang.elementManipulator) | [`ElementManipulator`](upsource:///platform/core-api/src/com/intellij/psi/ElementManipulator.java) |
| [com.intellij.lang.parserDefinition](https://jb.gg/ipe?extensions=com.intellij.lang.parserDefinition) | [`ParserDefinition`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java) |
| [com.intellij.languageBundle](https://jb.gg/ipe?extensions=com.intellij.languageBundle) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | `n/a` |
| [com.intellij.languageInjectionContributor](https://jb.gg/ipe?extensions=com.intellij.languageInjectionContributor) | [`LanguageInjectionContributor`](upsource:///platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) |
| [com.intellij.languageInjectionPerformer](https://jb.gg/ipe?extensions=com.intellij.languageInjectionPerformer) | [`LanguageInjectionPerformer`](upsource:///platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java) |
| [com.intellij.metaLanguage](https://jb.gg/ipe?extensions=com.intellij.metaLanguage) | [`MetaLanguage`](upsource:///platform/core-api/src/com/intellij/lang/MetaLanguage.java) |
| [com.intellij.moduleService](https://jb.gg/ipe?extensions=com.intellij.moduleService) | `n/a` |
| [com.intellij.multiHostInjector](https://jb.gg/ipe?extensions=com.intellij.multiHostInjector) ![Project-Level][project-level] | [`MultiHostInjector`](upsource:///platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java) |
| [com.intellij.pom.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.pom.declarationSearcher) | [`PomDeclarationSearcher`](upsource:///platform/core-api/src/com/intellij/pom/PomDeclarationSearcher.java) |
| [com.intellij.postStartupActivity](https://jb.gg/ipe?extensions=com.intellij.postStartupActivity) | [`StartupActivity`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) |
| [com.intellij.projectService](https://jb.gg/ipe?extensions=com.intellij.projectService) | `n/a` |
| [com.intellij.requiredForSmartModeStartupActivity](https://jb.gg/ipe?extensions=com.intellij.requiredForSmartModeStartupActivity) | [`RequiredForSmartMode`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) |
| [com.intellij.sideEffectGuard](https://jb.gg/ipe?extensions=com.intellij.sideEffectGuard) ![Internal API][internal] | [`SideEffectGuard`](upsource:///platform/core-api/src/com/intellij/model/SideEffectGuard.java) |
| [com.intellij.startupActivity](https://jb.gg/ipe?extensions=com.intellij.startupActivity) ![Non-Dynamic][non-dynamic] | [`StartupActivity`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) |
| [com.intellij.stubElementTypeHolder](https://jb.gg/ipe?extensions=com.intellij.stubElementTypeHolder) | `n/a` |
| [com.intellij.vfs.asyncListener](https://jb.gg/ipe?extensions=com.intellij.vfs.asyncListener) | [`AsyncFileListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) |
| [com.intellij.virtualFileManagerListener](https://jb.gg/ipe?extensions=com.intellij.virtualFileManagerListener) | [`VirtualFileManagerListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| [com.intellij.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.writingAccessProvider) ![Project-Level][project-level] | [`WritingAccessProvider`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/WritingAccessProvider.java) |

### CoreImpl.xml

[`CoreImpl.xml`](upsource:///platform/core-impl/resources/META-INF/CoreImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.controlFlowProvider](https://jb.gg/ipe?extensions=com.intellij.controlFlowProvider) | [`ControlFlowProvider`](upsource:///platform/core-impl/src/com/intellij/codeInsight/controlflow/ControlFlowProvider.java) |
| [com.intellij.diagnostic.freezeProfiler](https://jb.gg/ipe?extensions=com.intellij.diagnostic.freezeProfiler) ![Internal API][internal] | [`FreezeProfiler`](upsource:///platform/platform-impl/src/com/intellij/diagnostic/FreezeProfiler.java) |
| [com.intellij.documentWriteAccessGuard](https://jb.gg/ipe?extensions=com.intellij.documentWriteAccessGuard) ![Experimental API][experimental] | [`DocumentWriteAccessGuard`](upsource:///platform/core-impl/src/com/intellij/openapi/editor/impl/DocumentWriteAccessGuard.java) |
| [com.intellij.lang.ast.factory](https://jb.gg/ipe?extensions=com.intellij.lang.ast.factory) | [`ASTFactory`](upsource:///platform/core-impl/src/com/intellij/lang/ASTFactory.java) |
| [com.intellij.lang.tokenSeparatorGenerator](https://jb.gg/ipe?extensions=com.intellij.lang.tokenSeparatorGenerator) | [`TokenSeparatorGenerator`](upsource:///platform/core-api/src/com/intellij/lang/TokenSeparatorGenerator.java) |
| [com.intellij.psi.implicitReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.implicitReferenceProvider) | [`ImplicitReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java) |
| [com.intellij.psi.referenceContributor](https://jb.gg/ipe?extensions=com.intellij.psi.referenceContributor) | [`PsiReferenceContributor`](upsource:///platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java) |
| [com.intellij.psi.symbolReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.symbolReferenceProvider) | [`PsiSymbolReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.psi.treeChangeListener](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangeListener) ![Project-Level][project-level] | [`PsiTreeChangeListener`](upsource:///platform/core-api/src/com/intellij/psi/PsiTreeChangeListener.java) |
| [com.intellij.psi.treeChangePreprocessor](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangePreprocessor) ![Project-Level][project-level] | [`PsiTreeChangePreprocessor`](upsource:///platform/core-impl/src/com/intellij/psi/impl/PsiTreeChangePreprocessor.java) |
| [com.intellij.smartPointer.anchorProvider](https://jb.gg/ipe?extensions=com.intellij.smartPointer.anchorProvider) | [`SmartPointerAnchorProvider`](upsource:///platform/core-impl/src/com/intellij/psi/impl/smartPointers/SmartPointerAnchorProvider.java) |
| [com.intellij.treeCopyHandler](https://jb.gg/ipe?extensions=com.intellij.treeCopyHandler) | [`TreeCopyHandler`](upsource:///platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeCopyHandler.java) |
| [com.intellij.virtualFileSystem](https://jb.gg/ipe?extensions=com.intellij.virtualFileSystem) | [`VirtualFileSystem`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) |

### DomPlugin.xml

[`DomPlugin.xml`](upsource:///xml/dom-impl/src/META-INF/DomPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dom.converter](https://jb.gg/ipe?extensions=com.intellij.dom.converter) | `n/a` |
| [com.intellij.dom.customAnnotationChecker](https://jb.gg/ipe?extensions=com.intellij.dom.customAnnotationChecker) | [`DomCustomAnnotationChecker`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomCustomAnnotationChecker.java) |
| [com.intellij.dom.extender](https://jb.gg/ipe?extensions=com.intellij.dom.extender) | [`DomExtender`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtender.java) |
| [com.intellij.dom.fileDescription](https://jb.gg/ipe?extensions=com.intellij.dom.fileDescription) ![Deprecated][deprecated] | [`DomFileDescription`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.fileMetaData](https://jb.gg/ipe?extensions=com.intellij.dom.fileMetaData) | [`DomFileDescription`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.dom.gotoSuper) | [`DomElementNavigationProvider`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/DomElementNavigationProvider.java) |
| [com.intellij.dom.implementation](https://jb.gg/ipe?extensions=com.intellij.dom.implementation) | [`DomElement`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) |
| [com.intellij.dom.uiControlsProvider](https://jb.gg/ipe?extensions=com.intellij.dom.uiControlsProvider) ![Non-Dynamic][non-dynamic] | [`Consumer`](upsource:///platform/util-rt/src/com/intellij/util/Consumer.java) |
| [com.intellij.moduleContextProvider](https://jb.gg/ipe?extensions=com.intellij.moduleContextProvider) | [`ModuleContextProvider`](upsource:///xml/dom-openapi/src/com/intellij/util/xml/ModuleContextProvider.java) |

### duplicates-analysis.xml

[`duplicates-analysis.xml`](upsource:///platform/duplicates-analysis/resources/META-INF/duplicates-analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.equivalenceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.equivalenceDescriptorProvider) | [`EquivalenceDescriptorProvider`](upsource:///platform/duplicates-analysis/src/com/intellij/dupLocator/equivalence/EquivalenceDescriptorProvider.java) |

### dvcs.xml

[`dvcs.xml`](upsource:///platform/dvcs-impl/src/META-INF/dvcs.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cherryPicker](https://jb.gg/ipe?extensions=com.intellij.cherryPicker) ![Project-Level][project-level] | [`VcsCherryPicker`](upsource:///platform/dvcs-api/src/com/intellij/dvcs/cherrypick/VcsCherryPicker.java) |
| [com.intellij.clonePathProvider](https://jb.gg/ipe?extensions=com.intellij.clonePathProvider) | [`ClonePathProvider`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/repo/ClonePathProvider.java) |
| [com.intellij.prePushHandler](https://jb.gg/ipe?extensions=com.intellij.prePushHandler) ![Project-Level][project-level] | [`PrePushHandler`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/push/PrePushHandler.java) |
| [com.intellij.pushDialogActionsProvider](https://jb.gg/ipe?extensions=com.intellij.pushDialogActionsProvider) ![Internal API][internal] | [`PushDialogActionsProvider`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogActionsProvider.kt) |
| [com.intellij.pushDialogCustomizer](https://jb.gg/ipe?extensions=com.intellij.pushDialogCustomizer) ![Internal API][internal] | [`PushDialogCustomizer`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogCustomizer.kt) |
| [com.intellij.pushSupport](https://jb.gg/ipe?extensions=com.intellij.pushSupport) ![Project-Level][project-level] | [`PushSupport`](upsource:///platform/dvcs-api/src/com/intellij/dvcs/push/PushSupport.java) |
| [com.intellij.vcsRepositoryCreator](https://jb.gg/ipe?extensions=com.intellij.vcsRepositoryCreator) | [`VcsRepositoryCreator`](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryCreator.java) |

### Editor.xml

[`Editor.xml`](upsource:///platform/editor-ui-api/resources/META-INF/Editor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.editorFactoryListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryListener) | [`EditorFactoryListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorFactoryListener.java) |
| [com.intellij.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.syntaxHighlighter) | [`SyntaxHighlighter`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |

### EditorExtensionPoints.xml

[`EditorExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/EditorExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backspaceHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.backspaceHandlerDelegate) | [`BackspaceHandlerDelegate`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceHandlerDelegate.java) |
| [com.intellij.basicWordSelectionFilter](https://jb.gg/ipe?extensions=com.intellij.basicWordSelectionFilter) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.bidiRegionsSeparator](https://jb.gg/ipe?extensions=com.intellij.bidiRegionsSeparator) | [`BidiRegionsSeparator`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/bidi/BidiRegionsSeparator.java) |
| [com.intellij.codeBlockProvider](https://jb.gg/ipe?extensions=com.intellij.codeBlockProvider) | [`CodeBlockProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/CodeBlockProvider.java) |
| [com.intellij.codeInsight.fillParagraph](https://jb.gg/ipe?extensions=com.intellij.codeInsight.fillParagraph) | [`ParagraphFillHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/fillParagraph/ParagraphFillHandler.java) |
| [com.intellij.commentCompleteHandler](https://jb.gg/ipe?extensions=com.intellij.commentCompleteHandler) | [`CommentCompleteHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/CommentCompleteHandler.java) |
| [com.intellij.copyPastePostProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePostProcessor) | [`CopyPastePostProcessor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePostProcessor.java) |
| [com.intellij.copyPastePreProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePreProcessor) | [`CopyPastePreProcessor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePreProcessor.java) |
| [com.intellij.customPasteProvider](https://jb.gg/ipe?extensions=com.intellij.customPasteProvider) | [`PasteProvider`](upsource:///platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.editor.backspaceModeOverride](https://jb.gg/ipe?extensions=com.intellij.editor.backspaceModeOverride) | [`BackspaceModeOverride`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceModeOverride.java) |
| [com.intellij.enterBetweenBracesDelegate](https://jb.gg/ipe?extensions=com.intellij.enterBetweenBracesDelegate) | [`EnterBetweenBracesDelegate`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterBetweenBracesDelegate.java) |
| [com.intellij.enterHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.enterHandlerDelegate) | [`EnterHandlerDelegate`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java) |
| [com.intellij.extendWordSelectionHandler](https://jb.gg/ipe?extensions=com.intellij.extendWordSelectionHandler) | [`ExtendWordSelectionHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java) |
| [com.intellij.flipCommaIntention.flipper](https://jb.gg/ipe?extensions=com.intellij.flipCommaIntention.flipper) | [`Flipper`](upsource:///platform/lang-impl/src/com/intellij/openapi/editor/actions/FlipCommaIntention.java) |
| [com.intellij.generalEditorOptionsCustomizer](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsCustomizer) ![Internal API][internal] | [`EditorOptionsPageCustomizer`](upsource:///platform/lang-impl/src/com/intellij/application/options/editor/EditorOptionsPageCustomizer.kt) |
| [com.intellij.generalEditorOptionsExtension](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsExtension) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.joinLinesHandler](https://jb.gg/ipe?extensions=com.intellij.joinLinesHandler) | [`JoinLinesHandlerDelegate`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) |
| [com.intellij.lang.emacs](https://jb.gg/ipe?extensions=com.intellij.lang.emacs) | [`EmacsProcessingHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/emacs/EmacsProcessingHandler.java) |
| [com.intellij.lang.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.lang.quoteHandler) | [`QuoteHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.lang.smartEnterProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.smartEnterProcessor) | [`SmartEnterProcessor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) |
| [com.intellij.listSplitJoinContext](https://jb.gg/ipe?extensions=com.intellij.listSplitJoinContext) ![Experimental API][experimental] | [`ListSplitJoinContext`](upsource:///platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt) |
| [com.intellij.moveLeftRightHandler](https://jb.gg/ipe?extensions=com.intellij.moveLeftRightHandler) | [`MoveElementLeftRightHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) |
| [com.intellij.preserveIndentOnPaste](https://jb.gg/ipe?extensions=com.intellij.preserveIndentOnPaste) | `n/a` |
| [com.intellij.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.quoteHandler) | [`QuoteHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.selectionUnquotingFilter](https://jb.gg/ipe?extensions=com.intellij.selectionUnquotingFilter) | [`UnquotingFilter`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectionQuotingTypedHandler.java) |
| [com.intellij.statementUpDownMover](https://jb.gg/ipe?extensions=com.intellij.statementUpDownMover) | [`StatementUpDownMover`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java) |
| [com.intellij.typedHandler](https://jb.gg/ipe?extensions=com.intellij.typedHandler) | [`TypedHandlerDelegate`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) |
| [com.intellij.typingActionsExtension](https://jb.gg/ipe?extensions=com.intellij.typingActionsExtension) ![Experimental API][experimental] | [`TypingActionsExtension`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/editorActions/TypingActionsExtension.java) |
| [com.intellij.wordBoundaryFilter](https://jb.gg/ipe?extensions=com.intellij.wordBoundaryFilter) | [`WordBoundaryFilter`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/actions/WordBoundaryFilter.java) |

### ExternalSystemExtensionPoints.xml

[`ExternalSystemExtensionPoints.xml`](upsource:///platform/external-system-impl/resources/META-INF/ExternalSystemExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.externalExecutionAware](https://jb.gg/ipe?extensions=com.intellij.externalExecutionAware) ![Experimental API][experimental] | [`ExternalSystemExecutionAware`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemExecutionAware.kt) |
| [com.intellij.externalIconProvider](https://jb.gg/ipe?extensions=com.intellij.externalIconProvider) | [`ExternalSystemIconProvider`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemIconProvider.kt) |
| [com.intellij.externalProjectDataService](https://jb.gg/ipe?extensions=com.intellij.externalProjectDataService) | [`ProjectDataService`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataService.java) |
| [com.intellij.externalProjectStructureCustomizer](https://jb.gg/ipe?extensions=com.intellij.externalProjectStructureCustomizer) | [`ExternalProjectStructureCustomizer`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/importing/ExternalProjectStructureCustomizer.java) |
| [com.intellij.externalProjectWatcherContributor](https://jb.gg/ipe?extensions=com.intellij.externalProjectWatcherContributor) ![Deprecated][deprecated] | [`Contributor`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/autoimport/ExternalSystemProjectsWatcherImpl.java) |
| [com.intellij.externalSystem.beforeRunTaskImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.beforeRunTaskImporter) | [`BeforeRunTaskImporter`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/BeforeRunTaskImporter.java) |
| [com.intellij.externalSystem.debuggerBackend](https://jb.gg/ipe?extensions=com.intellij.externalSystem.debuggerBackend) | [`DebuggerBackendExtension`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/debugger/DebuggerBackendExtension.java) |
| [com.intellij.externalSystem.facetConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.facetConfigurationImporter) | [`FacetConfigurationImporter`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/FacetConfigurationImporter.java) |
| [com.intellij.externalSystem.modifiableModelsProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystem.modifiableModelsProvider) ![Experimental API][experimental] | [`ModifiableModelsProviderExtension`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ModifiableModelsProviderExtension.java) |
| [com.intellij.externalSystem.moduleDataServiceExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystem.moduleDataServiceExtension) ![Deprecated][deprecated] | [`ModuleDataServiceExtension`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ModuleDataServiceExtension.java) |
| [com.intellij.externalSystem.runConfigurationEx](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationEx) | [`ExternalSystemRunConfigurationExtension`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/configuration/ExternalSystemRunConfigurationExtension.kt) |
| [com.intellij.externalSystem.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationExtension) ![Deprecated][deprecated] | [`ExternalSystemRunConfigurationExtension`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemRunConfigurationExtension.java) |
| [com.intellij.externalSystem.runConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationImporter) | [`RunConfigurationImporter`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/RunConfigurationImporter.java) |
| [com.intellij.externalSystemConfigLocator](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigLocator) | [`ExternalSystemConfigLocator`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/settings/ExternalSystemConfigLocator.java) |
| [com.intellij.externalSystemConfigurationHandler](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigurationHandler) ![Experimental API][experimental] | [`ConfigurationHandler`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/ConfigurationHandler.java) |
| [com.intellij.externalSystemContentRootContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemContentRootContributor) | [`ExternalSystemContentRootContributor`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemContentRootContributor.kt) |
| [com.intellij.externalSystemCrcCalculator](https://jb.gg/ipe?extensions=com.intellij.externalSystemCrcCalculator) ![Experimental API][experimental] | [`ExternalSystemCrcCalculator`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemCrcCalculator.kt) |
| [com.intellij.externalSystemDependencyAnalyzer](https://jb.gg/ipe?extensions=com.intellij.externalSystemDependencyAnalyzer) | [`DependencyAnalyzerExtension`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/dependency/analyzer/DependencyAnalyzerExtension.kt) |
| [com.intellij.externalSystemExecutionConsoleManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemExecutionConsoleManager) | [`ExternalSystemExecutionConsoleManager`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/execution/ExternalSystemExecutionConsoleManager.java) |
| [com.intellij.externalSystemKeymapProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemKeymapProvider) | [`ActionsProvider`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemKeymapExtension.java) |
| [com.intellij.externalSystemManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemManager) | [`ExternalSystemManager`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemManager.java) |
| [com.intellij.externalSystemNotificationExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystemNotificationExtension) | [`ExternalSystemNotificationExtension`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/notification/ExternalSystemNotificationExtension.java) |
| [com.intellij.externalSystemOutputDispatcher](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputDispatcher) | [`ExternalSystemOutputDispatcherFactory`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputDispatcherFactory.kt) |
| [com.intellij.externalSystemOutputParserProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputParserProvider) | [`ExternalSystemOutputParserProvider`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputParserProvider.java) |
| [com.intellij.externalSystemSettingsListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemSettingsListener) | [`ExternalSystemSettingsListenerEx`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/settings/ExternalSystemSettingsListenerEx.kt) |
| [com.intellij.externalSystemTaskNotificationListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemTaskNotificationListener) | [`ExternalSystemTaskNotificationListener`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/model/task/ExternalSystemTaskNotificationListener.java) |
| [com.intellij.externalSystemUnlinkedProjectAware](https://jb.gg/ipe?extensions=com.intellij.externalSystemUnlinkedProjectAware) | [`ExternalSystemUnlinkedProjectAware`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/autolink/ExternalSystemUnlinkedProjectAware.kt) |
| [com.intellij.externalSystemViewContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemViewContributor) | [`ExternalSystemViewContributor`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/view/ExternalSystemViewContributor.java) |
| [com.intellij.externalSystemWorkspaceContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemWorkspaceContributor) ![Experimental API][experimental] | [`ExternalSystemWorkspaceContributor`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ExternalSystemWorkspaceContributor.java) |

### FormatterExtensionPoints.xml

[`FormatterExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/FormatterExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeStyleSettingsModifier](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsModifier) ![Experimental API][experimental] | [`CodeStyleSettingsModifier`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/modifier/CodeStyleSettingsModifier.java) |
| [com.intellij.disabledIndentRangesProvider](https://jb.gg/ipe?extensions=com.intellij.disabledIndentRangesProvider) | [`DisabledIndentRangesProvider`](upsource:///platform/code-style-impl/src/com/intellij/psi/impl/source/DisabledIndentRangesProvider.java) |
| [com.intellij.documentMerger](https://jb.gg/ipe?extensions=com.intellij.documentMerger) | [`DocumentMerger`](upsource:///platform/code-style-api/src/com/intellij/formatting/service/DocumentMerger.java) |
| [com.intellij.externalFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.externalFormatProcessor) ![Experimental API][experimental] | [`ExternalFormatProcessor`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/ExternalFormatProcessor.java) |
| [com.intellij.fileCodeStyleProvider](https://jb.gg/ipe?extensions=com.intellij.fileCodeStyleProvider) | [`FileCodeStyleProvider`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/FileCodeStyleProvider.java) |
| [com.intellij.fileIndentOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.fileIndentOptionsProvider) | [`FileIndentOptionsProvider`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/FileIndentOptionsProvider.java) |
| [com.intellij.fileSetDescriptorFactory](https://jb.gg/ipe?extensions=com.intellij.fileSetDescriptorFactory) | [`FileSetDescriptorFactory`](upsource:///platform/code-style-api/src/com/intellij/formatting/fileSet/FileSetDescriptorFactory.java) |
| [com.intellij.fileTypeIndentOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.fileTypeIndentOptionsProvider) | [`FileTypeIndentOptionsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java) |
| [com.intellij.formatOnSaveOptions.defaultsProvider](https://jb.gg/ipe?extensions=com.intellij.formatOnSaveOptions.defaultsProvider) | [`DefaultsProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/actions/onSave/FormatOnSaveOptionsBase.java) |
| [com.intellij.formattingService](https://jb.gg/ipe?extensions=com.intellij.formattingService) | [`FormattingService`](upsource:///platform/code-style-api/src/com/intellij/formatting/service/FormattingService.java) |
| [com.intellij.lang.formatter](https://jb.gg/ipe?extensions=com.intellij.lang.formatter) | [`FormattingModelBuilder`](upsource:///platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java) |
| [com.intellij.lang.formatter.newLineIndentMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.lang.formatter.newLineIndentMarkerProvider) | [`NewLineIndentMarkerProvider`](upsource:///platform/code-style-impl/src/com/intellij/psi/impl/source/codeStyle/NewLineIndentMarkerProvider.java) |
| [com.intellij.lang.formatter.restriction](https://jb.gg/ipe?extensions=com.intellij.lang.formatter.restriction) | [`LanguageFormattingRestriction`](upsource:///platform/code-style-api/src/com/intellij/lang/LanguageFormattingRestriction.java) |
| [com.intellij.lang.formatting.extractor](https://jb.gg/ipe?extensions=com.intellij.lang.formatting.extractor) | [`LangCodeStyleExtractor`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/extractor/differ/LangCodeStyleExtractor.java) |
| [com.intellij.lang.importOptimizer](https://jb.gg/ipe?extensions=com.intellij.lang.importOptimizer) | [`ImportOptimizer`](upsource:///platform/code-style-api/src/com/intellij/lang/ImportOptimizer.java) |
| [com.intellij.lang.indentStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.indentStrategy) | [`IndentStrategy`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/IndentStrategy.java) |
| [com.intellij.lang.lineWrapStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.lineWrapStrategy) | [`LineWrapPositionStrategy`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/LineWrapPositionStrategy.java) |
| [com.intellij.lang.rearranger](https://jb.gg/ipe?extensions=com.intellij.lang.rearranger) | [`Rearranger`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) |
| [com.intellij.lang.whiteSpaceFormattingStrategy](https://jb.gg/ipe?extensions=com.intellij.lang.whiteSpaceFormattingStrategy) | [`WhiteSpaceFormattingStrategy`](upsource:///platform/code-style-impl/src/com/intellij/psi/formatter/WhiteSpaceFormattingStrategy.java) |
| [com.intellij.langCodeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.langCodeStyleSettingsProvider) | [`LanguageCodeStyleSettingsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java) |
| [com.intellij.lineIndentProvider](https://jb.gg/ipe?extensions=com.intellij.lineIndentProvider) | [`LineIndentProvider`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/lineIndent/LineIndentProvider.java) |
| [com.intellij.postFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.postFormatProcessor) | [`PostFormatProcessor`](upsource:///platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java) |
| [com.intellij.preFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.preFormatProcessor) | [`PreFormatProcessor`](upsource:///platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) |
| [com.intellij.predefinedCodeStyle](https://jb.gg/ipe?extensions=com.intellij.predefinedCodeStyle) | [`PredefinedCodeStyle`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/PredefinedCodeStyle.java) |
| [com.intellij.rearranger.ui](https://jb.gg/ipe?extensions=com.intellij.rearranger.ui) | [`Factory`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/std/ArrangementUiComponent.java) |

### Indexing.xml

[`Indexing.xml`](upsource:///platform/indexing-api/resources/META-INF/Indexing.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.definitionsSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsSearch) ![Deprecated][deprecated] | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.dumbServiceInitializationCondition](https://jb.gg/ipe?extensions=com.intellij.dumbServiceInitializationCondition) ![Internal API][internal] | [`DumbServiceInitializationCondition`](upsource:///platform/platform-impl/src/com/intellij/openapi/project/DumbServiceInitializationCondition.java) |
| [com.intellij.fileBasedIndex](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndex) | [`FileBasedIndexExtension`](upsource:///platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java) |
| [com.intellij.fileBasedIndexInfrastructureExtension](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexInfrastructureExtension) ![Internal API][internal] | [`FileBasedIndexInfrastructureExtension`](upsource:///platform/indexing-impl/src/com/intellij/util/indexing/FileBasedIndexInfrastructureExtension.java) |
| [com.intellij.fileBasedIndexLayout](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexLayout) ![Internal API][internal] | [`FileBasedIndexLayoutProvider`](upsource:///platform/indexing-impl/src/com/intellij/util/indexing/storage/FileBasedIndexLayoutProvider.java) |
| [com.intellij.findModelExtension](https://jb.gg/ipe?extensions=com.intellij.findModelExtension) ![Internal API][internal] | [`FindModelExtension`](upsource:///platform/indexing-api/src/com/intellij/find/FindModelExtension.java) |
| [com.intellij.indexableFilesContributor](https://jb.gg/ipe?extensions=com.intellij.indexableFilesContributor) ![Experimental API][experimental] | [`IndexableFilesContributor`](upsource:///platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFilesContributor.java) |
| [com.intellij.indexedRootsProvider](https://jb.gg/ipe?extensions=com.intellij.indexedRootsProvider) | [`IndexableSetContributor`](upsource:///platform/indexing-api/src/com/intellij/util/indexing/IndexableSetContributor.java) |
| [com.intellij.indexingFlavor](https://jb.gg/ipe?extensions=com.intellij.indexingFlavor) ![Experimental API][experimental] ![Internal API][internal] | [`FileIndexingFlavorProvider`](upsource:///platform/core-api/src/com/intellij/util/indexing/flavor/FileIndexingFlavorProvider.java) |
| [com.intellij.projectIndexingHistoryListener](https://jb.gg/ipe?extensions=com.intellij.projectIndexingHistoryListener) | [`ProjectIndexingHistoryListener`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [com.intellij.referencesSearch](https://jb.gg/ipe?extensions=com.intellij.referencesSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.stubIndex](https://jb.gg/ipe?extensions=com.intellij.stubIndex) | [`StubIndexExtension`](upsource:///platform/indexing-api/src/com/intellij/psi/stubs/StubIndexExtension.java) |

### Inspect.xml

[`Inspect.xml`](upsource:///platform/inspect/resources/META-INF/Inspect.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.inspectResultsConsumer](https://jb.gg/ipe?extensions=com.intellij.inspectResultsConsumer) ![Internal API][internal] | [`InspectResultsConsumer`](upsource:///platform/inspect/src/com/intellij/codeInspection/InspectResultsConsumer.java) |
| [com.intellij.inspectionApplicationFactory](https://jb.gg/ipe?extensions=com.intellij.inspectionApplicationFactory) ![Internal API][internal] | [`InspectionApplicationFactory`](upsource:///platform/inspect/src/com/intellij/codeInspection/InspectionApplicationFactory.java) |

### intellij.json.xml

[`intellij.json.xml`](upsource:///json/resources/intellij.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.JsonSchema.ContentAwareSchemaFileProvider](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ContentAwareSchemaFileProvider) ![Experimental API][experimental] ![Internal API][internal] | [`ContentAwareJsonSchemaFileProvider`](upsource:///json/src/com/jetbrains/jsonSchema/extension/ContentAwareJsonSchemaFileProvider.java) |
| [JavaScript.JsonSchema.ProviderFactory](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ProviderFactory) | [`JsonSchemaProviderFactory`](upsource:///json/src/com/jetbrains/jsonSchema/extension/JsonSchemaProviderFactory.java) |
| [com.intellij.json.catalog.exclusion](https://jb.gg/ipe?extensions=com.intellij.json.catalog.exclusion) | [`JsonSchemaCatalogExclusion`](upsource:///json/src/com/jetbrains/jsonSchema/remote/JsonSchemaCatalogExclusion.java) |
| [com.intellij.json.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.json.customStructureViewFactory) ![Internal API][internal] | [`JsonCustomStructureViewFactory`](upsource:///json/src/com/intellij/json/structureView/JsonCustomStructureViewFactory.java) |
| [com.intellij.json.jsonLikePsiWalkerFactory](https://jb.gg/ipe?extensions=com.intellij.json.jsonLikePsiWalkerFactory) | [`JsonLikePsiWalkerFactory`](upsource:///json/src/com/jetbrains/jsonSchema/extension/JsonLikePsiWalkerFactory.java) |
| [com.intellij.json.jsonLiteralChecker](https://jb.gg/ipe?extensions=com.intellij.json.jsonLiteralChecker) | [`JsonLiteralChecker`](upsource:///json/src/com/intellij/json/codeinsight/JsonLiteralChecker.java) |
| [com.intellij.json.jsonSchemaEnabler](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaEnabler) | [`JsonSchemaEnabler`](upsource:///json/src/com/jetbrains/jsonSchema/extension/JsonSchemaEnabler.java) |
| [com.intellij.json.jsonStandardComplianceProvider](https://jb.gg/ipe?extensions=com.intellij.json.jsonStandardComplianceProvider) | [`JsonStandardComplianceProvider`](upsource:///json/src/com/intellij/json/codeinsight/JsonStandardComplianceProvider.java) |
| [com.intellij.json.jsonWidgetSuppressor](https://jb.gg/ipe?extensions=com.intellij.json.jsonWidgetSuppressor) | [`JsonWidgetSuppressor`](upsource:///json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) |

### intellij.notebooks.visualization.xml

[`intellij.notebooks.visualization.xml`](upsource:///notebooks/visualization/resources/intellij.notebooks.visualization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.datavis.inlays.components.inlayOutputProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.inlayOutputProvider) | [`InlayOutputProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/InlayOutputProvider.kt) |
| [com.intellij.datavis.inlays.components.inlayStateCustomizer](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.inlayStateCustomizer) ![Experimental API][experimental] | [`InlayStateCustomizer`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/InlayStateCustomizer.kt) |
| [com.intellij.datavis.inlays.components.multiOutputProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.components.multiOutputProvider) | [`MultiOutputProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/MultiOutputProvider.kt) |
| [com.intellij.datavis.inlays.inlayDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.inlays.inlayDescriptorProvider) | [`InlayDescriptorProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/InlayElementDescriptor.kt) |
| [com.intellij.datavis.r.inlays.components.graphicsManagerProvider](https://jb.gg/ipe?extensions=com.intellij.datavis.r.inlays.components.graphicsManagerProvider) | [`GraphicsManagerProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/inlays/components/GraphicsManagerProvider.kt) |
| [org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider) | [`NotebookEditorAppearanceProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookEditorAppearanceProvider.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory) | [`NotebookOutputComponentFactory`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputComponentFactory.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper) | [`NotebookOutputComponentWrapper`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputComponentWrapper.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor) | [`NotebookOutputDataKeyExtractor`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/outputs/NotebookOutputDataKeyExtractor.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellGutterController](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellGutterController) | [`NotebookCellGutterController`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellGutterController.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellInlayController](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellInlayController) | [`Factory`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellInlayController.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellLinesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellLinesProvider) | [`NotebookCellLinesProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellLinesProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider) | [`NotebookCellSelectionModelProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookCellSelectionModelProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider) | [`NotebookIntervalPointerFactoryProvider`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/NotebookIntervalPointerFactoryProvider.kt) |
| [org.jetbrains.plugins.notebooks.visualization.r.inlays.visualisation.uiCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.visualization.r.inlays.visualisation.uiCustomizer) | [`UiCustomizer`](upsource:///notebooks/visualization/src/org/jetbrains/plugins/notebooks/visualization/r/ui/UiCustomizer.kt) |

### intellij.platform.remoteServers.impl.xml

[`intellij.platform.remoteServers.impl.xml`](upsource:///platform/remote-servers/impl/resources/intellij.platform.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.defaultConfigurable.includeServerType](https://jb.gg/ipe?extensions=com.intellij.remoteServer.defaultConfigurable.includeServerType) | [`ServerType`](upsource:///platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |
| [com.intellij.remoteServer.deploymentSource.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.deploymentSource.type) | [`DeploymentSourceType`](upsource:///platform/remote-servers/api/src/com/intellij/remoteServer/configuration/deployment/DeploymentSourceType.java) |
| [com.intellij.remoteServer.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.remoteServer.runConfigurationExtension) | [`DeployToServerRunConfigurationExtension`](upsource:///platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerRunConfigurationExtension.java) |
| [com.intellij.remoteServer.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.type) | [`ServerType`](upsource:///platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |

### LangExtensionPoints.xml

[`LangExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/LangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.aliasingPsiTargetMapper](https://jb.gg/ipe?extensions=com.intellij.aliasingPsiTargetMapper) | [`AliasingPsiTargetMapper`](upsource:///platform/core-api/src/com/intellij/psi/targets/AliasingPsiTargetMapper.java) |
| [com.intellij.analyzeStacktraceFilter](https://jb.gg/ipe?extensions=com.intellij.analyzeStacktraceFilter) ![Project-Level][project-level] | [`Filter`](upsource:///platform/execution/src/com/intellij/execution/filters/Filter.java) |
| [com.intellij.anchorReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.anchorReferenceProvider) | [`PathReferenceProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.annotator](https://jb.gg/ipe?extensions=com.intellij.annotator) | [`Annotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) |
| [com.intellij.anonymousElementProvider](https://jb.gg/ipe?extensions=com.intellij.anonymousElementProvider) | [`AnonymousElementProvider`](upsource:///platform/editor-ui-api/src/com/intellij/navigation/AnonymousElementProvider.java) |
| [com.intellij.autoImportOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.autoImportOptionsProvider) ![Project-Level][project-level] | [`AutoImportOptionsProvider`](upsource:///platform/lang-impl/src/com/intellij/application/options/editor/AutoImportOptionsProvider.java) |
| [com.intellij.bookmarkProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarkProvider) ![Project-Level][project-level] | [`BookmarkProvider`](upsource:///platform/lang-api/src/com/intellij/ide/bookmark/BookmarkProvider.java) |
| [com.intellij.bookmarksListProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarksListProvider) ![Project-Level][project-level] | [`BookmarksListProvider`](upsource:///platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListProvider.java) |
| [com.intellij.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.braceMatcher) | [`BraceMatcher`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/highlighting/BraceMatcher.java) |
| [com.intellij.breadcrumbsInfoProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsInfoProvider) | [`BreadcrumbsProvider`](upsource:///platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java) |
| [com.intellij.cacheBuilder](https://jb.gg/ipe?extensions=com.intellij.cacheBuilder) | [`WordsScanner`](upsource:///platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java) |
| [com.intellij.callHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.callHierarchyProvider) | [`HierarchyProvider`](upsource:///platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.cantBeStatic](https://jb.gg/ipe?extensions=com.intellij.cantBeStatic) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeBlockSupportHandler](https://jb.gg/ipe?extensions=com.intellij.codeBlockSupportHandler) | [`CodeBlockSupportHandler`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java) |
| [com.intellij.codeCompletionConfigurable](https://jb.gg/ipe?extensions=com.intellij.codeCompletionConfigurable) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.codeFoldingOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.codeFoldingOptionsProvider) | [`CodeFoldingOptionsProvider`](upsource:///platform/lang-impl/src/com/intellij/application/options/editor/CodeFoldingOptionsProvider.java) |
| [com.intellij.codeInsight.codeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProvider) ![Experimental API][experimental] | [`CodeVisionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProvider.kt) |
| [com.intellij.codeInsight.codeVisionProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProviderFactory) | [`CodeVisionProviderFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProviderFactory.kt) |
| [com.intellij.codeInsight.daemonBoundCodeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.daemonBoundCodeVisionProvider) | [`DaemonBoundCodeVisionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hints/codeVision/DaemonBoundCodeVisionProvider.kt) |
| [com.intellij.codeInsight.delegateMethods](https://jb.gg/ipe?extensions=com.intellij.codeInsight.delegateMethods) | [`LanguageCodeInsightActionHandler`](upsource:///platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.gotoSuper) | [`CodeInsightActionHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/CodeInsightActionHandler.java) |
| [com.intellij.codeInsight.implementMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implementMethod) | [`LanguageCodeInsightActionHandler`](upsource:///platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.inlayProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProvider) | [`InlayHintsProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt) |
| [com.intellij.codeInsight.inlayProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProviderFactory) | [`InlayHintsProviderFactory`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt) |
| [com.intellij.codeInsight.lineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.lineMarkerProvider) | [`LineMarkerProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProvider.java) |
| [com.intellij.codeInsight.overrideMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.overrideMethod) | [`LanguageCodeInsightActionHandler`](upsource:///platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.parameterInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo) | [`ParameterInfoHandler`](upsource:///platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java) |
| [com.intellij.codeInsight.parameterInfo.controller.provider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.controller.provider) | [`ParameterInfoControllerProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoControllerProvider.java) |
| [com.intellij.codeInsight.parameterInfo.listener](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.listener) | [`ParameterInfoListener`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoListener.java) |
| [com.intellij.codeInsight.parameterNameHints](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHints) | [`InlayParameterHintsProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java) |
| [com.intellij.codeInsight.parameterNameHintsSuppressor](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHintsSuppressor) ![Experimental API][experimental] | [`ParameterNameHintsSuppressor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt) |
| [com.intellij.codeInsight.surroundWithRangeAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeInsight.surroundWithRangeAdjuster) | [`SurroundWithRangeAdjuster`](upsource:///platform/lang-api/src/com/intellij/codeInsight/generation/surroundWith/SurroundWithRangeAdjuster.java) |
| [com.intellij.codeInsight.typeInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.typeInfo) | [`ExpressionTypeProvider`](upsource:///platform/lang-api/src/com/intellij/lang/ExpressionTypeProvider.java) |
| [com.intellij.codeInsight.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.unresolvedReferenceQuickFixProvider) | [`UnresolvedReferenceQuickFixProvider`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/quickfix/UnresolvedReferenceQuickFixProvider.java) |
| [com.intellij.codeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsProvider) | [`CodeStyleSettingsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java) |
| [com.intellij.codeUsageScopeOptimizer](https://jb.gg/ipe?extensions=com.intellij.codeUsageScopeOptimizer) | [`ScopeOptimizer`](upsource:///platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |
| [com.intellij.codeVisionPainterProvider](https://jb.gg/ipe?extensions=com.intellij.codeVisionPainterProvider) ![Non-Dynamic][non-dynamic] | [`ICodeVisionEntryBasePainter`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/codeVision/ui/renderers/painters/ICodeVisionEntryBasePainter.kt) |
| [com.intellij.colorAndFontDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.colorAndFontDescriptorProvider) | [`ColorAndFontDescriptorsProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorAndFontDescriptorsProvider.java) |
| [com.intellij.colorAndFontPanelFactory](https://jb.gg/ipe?extensions=com.intellij.colorAndFontPanelFactory) | [`ColorAndFontPanelFactory`](upsource:///platform/platform-impl/src/com/intellij/application/options/colors/ColorAndFontPanelFactory.java) |
| [com.intellij.colorProvider](https://jb.gg/ipe?extensions=com.intellij.colorProvider) | [`ElementColorProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) |
| [com.intellij.colorSettingsPage](https://jb.gg/ipe?extensions=com.intellij.colorSettingsPage) | [`ColorSettingsPage`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java) |
| [com.intellij.commandLineInspectionProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.commandLineInspectionProjectConfigurator) | [`CommandLineInspectionProjectConfigurator`](upsource:///platform/platform-impl/src/com/intellij/ide/CommandLineInspectionProjectConfigurator.java) |
| [com.intellij.commentTokenSetProvider](https://jb.gg/ipe?extensions=com.intellij.commentTokenSetProvider) | [`CommentTokenSetProvider`](upsource:///platform/core-impl/src/com/intellij/psi/impl/cache/CommentTokenSetProvider.java) |
| [com.intellij.concatenationAwareInjector](https://jb.gg/ipe?extensions=com.intellij.concatenationAwareInjector) ![Project-Level][project-level] | [`ConcatenationAwareInjector`](upsource:///platform/lang-api/src/com/intellij/lang/injection/ConcatenationAwareInjector.java) |
| [com.intellij.configurationProducer](https://jb.gg/ipe?extensions=com.intellij.configurationProducer) ![Deprecated][deprecated] | [`RuntimeConfigurationProducer`](upsource:///platform/lang-api/src/com/intellij/execution/junit/RuntimeConfigurationProducer.java) |
| [com.intellij.configurationType](https://jb.gg/ipe?extensions=com.intellij.configurationType) | [`ConfigurationType`](upsource:///platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java) |
| [com.intellij.console.folding](https://jb.gg/ipe?extensions=com.intellij.console.folding) | [`ConsoleFolding`](upsource:///platform/execution-impl/src/com/intellij/execution/ConsoleFolding.java) |
| [com.intellij.consoleActionsPostProcessor](https://jb.gg/ipe?extensions=com.intellij.consoleActionsPostProcessor) | [`ConsoleActionsPostProcessor`](upsource:///platform/lang-api/src/com/intellij/execution/actions/ConsoleActionsPostProcessor.java) |
| [com.intellij.consoleFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleFilterProvider) | [`ConsoleFilterProvider`](upsource:///platform/lang-api/src/com/intellij/execution/filters/ConsoleFilterProvider.java) |
| [com.intellij.consoleHistoryModelProvider](https://jb.gg/ipe?extensions=com.intellij.consoleHistoryModelProvider) | [`ConsoleHistoryModelProvider`](upsource:///platform/lang-impl/src/com/intellij/execution/console/ConsoleHistoryModelProvider.java) |
| [com.intellij.consoleInputFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleInputFilterProvider) | [`ConsoleInputFilterProvider`](upsource:///platform/lang-api/src/com/intellij/execution/filters/ConsoleInputFilterProvider.java) |
| [com.intellij.createFromTemplateActionReplacer](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateActionReplacer) | [`CreateFromTemplateActionReplacer`](upsource:///platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateActionReplacer.java) |
| [com.intellij.createFromTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateHandler) | [`CreateFromTemplateHandler`](upsource:///platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateHandler.java) |
| [com.intellij.customFoldingProvider](https://jb.gg/ipe?extensions=com.intellij.customFoldingProvider) | [`CustomFoldingProvider`](upsource:///platform/core-api/src/com/intellij/lang/folding/CustomFoldingProvider.java) |
| [com.intellij.customLiveTemplate](https://jb.gg/ipe?extensions=com.intellij.customLiveTemplate) | [`CustomLiveTemplate`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/CustomLiveTemplate.java) |
| [com.intellij.customScopesFilter](https://jb.gg/ipe?extensions=com.intellij.customScopesFilter) | [`CustomScopesFilter`](upsource:///platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesFilter.java) |
| [com.intellij.customScopesProvider](https://jb.gg/ipe?extensions=com.intellij.customScopesProvider) ![Project-Level][project-level] | [`CustomScopesProvider`](upsource:///platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesProvider.java) |
| [com.intellij.customUsageSearcher](https://jb.gg/ipe?extensions=com.intellij.customUsageSearcher) | [`CustomUsageSearcher`](upsource:///platform/lang-impl/src/com/intellij/find/findUsages/CustomUsageSearcher.java) |
| [com.intellij.daemon.changeLocalityDetector](https://jb.gg/ipe?extensions=com.intellij.daemon.changeLocalityDetector) | [`ChangeLocalityDetector`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/ChangeLocalityDetector.java) |
| [com.intellij.daemon.externalAnnotatorsFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.externalAnnotatorsFilter) | [`ExternalAnnotatorsFilter`](upsource:///platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) |
| [com.intellij.daemon.highlightInfoFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.highlightInfoFilter) | [`HighlightInfoFilter`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoFilter.java) |
| [com.intellij.daemon.indentsPassFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.indentsPassFilter) | [`IndentsPassFilter`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IndentsPassFilter.java) |
| [com.intellij.daemon.intentionActionFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.intentionActionFilter) | [`IntentionActionFilter`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/IntentionActionFilter.java) |
| [com.intellij.daemon.statusItemMerger](https://jb.gg/ipe?extensions=com.intellij.daemon.statusItemMerger) | [`StatusItemMerger`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/StatusItemMerger.java) |
| [com.intellij.daemon.tooltipActionProvider](https://jb.gg/ipe?extensions=com.intellij.daemon.tooltipActionProvider) | [`TooltipActionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/tooltips/TooltipActionProvider.java) |
| [com.intellij.declarationRangeHandler](https://jb.gg/ipe?extensions=com.intellij.declarationRangeHandler) | [`DeclarationRangeHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) |
| [com.intellij.defaultHighlightingSettingProvider](https://jb.gg/ipe?extensions=com.intellij.defaultHighlightingSettingProvider) | [`DefaultHighlightingSettingProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/DefaultHighlightingSettingProvider.java) |
| [com.intellij.defaultLiveTemplates](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplates) | `n/a` |
| [com.intellij.defaultLiveTemplatesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplatesProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`DefaultLiveTemplatesProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) |
| [com.intellij.defaultTemplatePropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultTemplatePropertiesProvider) | [`DefaultTemplatePropertiesProvider`](upsource:///platform/lang-api/src/com/intellij/ide/fileTemplates/DefaultTemplatePropertiesProvider.java) |
| [com.intellij.definitionsScopedSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsScopedSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.diffPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.diffPreviewProvider) ![Non-Dynamic][non-dynamic] | [`DiffPreviewProvider`](upsource:///platform/diff-api/src/com/intellij/openapi/diff/impl/settings/DiffPreviewProvider.java) |
| [com.intellij.dynamicContextProvider](https://jb.gg/ipe?extensions=com.intellij.dynamicContextProvider) | [`DynamicContextProvider`](upsource:///platform/lang-impl/src/com/intellij/openapi/paths/DynamicContextProvider.java) |
| [com.intellij.editorAppearanceConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorAppearanceConfigurable) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.editorOptionsProvider) | [`EditorOptionsProvider`](upsource:///platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsProvider.java) |
| [com.intellij.editorSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorSmartKeysConfigurable) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorTabsConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorTabsConfigurable) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.elementDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.elementDescriptionProvider) | [`ElementDescriptionProvider`](upsource:///platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java) |
| [com.intellij.elementPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.elementPreviewProvider) | [`ElementPreviewProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/preview/ElementPreviewProvider.java) |
| [com.intellij.elementSignatureProvider](https://jb.gg/ipe?extensions=com.intellij.elementSignatureProvider) | [`ElementSignatureProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/folding/impl/ElementSignatureProvider.java) |
| [com.intellij.elementsToHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.elementsToHighlightFilter) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.emptyInspectionTreeActionProvider](https://jb.gg/ipe?extensions=com.intellij.emptyInspectionTreeActionProvider) | [`EmptyInspectionTreeActionProvider`](upsource:///platform/lang-impl/src/com/intellij/profile/codeInspection/ui/EmptyInspectionTreeActionProvider.java) |
| [com.intellij.errorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.errorOptionsProvider) | [`ErrorOptionsProvider`](upsource:///platform/lang-impl/src/com/intellij/profile/codeInspection/ui/ErrorOptionsProvider.java) |
| [com.intellij.errorQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.errorQuickFixProvider) | [`ErrorQuickFixProvider`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/ErrorQuickFixProvider.java) |
| [com.intellij.executionTargetLanguageRuntimeType](https://jb.gg/ipe?extensions=com.intellij.executionTargetLanguageRuntimeType) | [`LanguageRuntimeType`](upsource:///platform/execution/src/com/intellij/execution/target/LanguageRuntimeType.kt) |
| [com.intellij.executionTargetProvider](https://jb.gg/ipe?extensions=com.intellij.executionTargetProvider) | [`ExecutionTargetProvider`](upsource:///platform/lang-api/src/com/intellij/execution/ExecutionTargetProvider.java) |
| [com.intellij.executionTargetType](https://jb.gg/ipe?extensions=com.intellij.executionTargetType) | [`TargetEnvironmentType`](upsource:///platform/execution/src/com/intellij/execution/target/TargetEnvironmentType.kt) |
| [com.intellij.executor](https://jb.gg/ipe?extensions=com.intellij.executor) | [`Executor`](upsource:///platform/execution/src/com/intellij/execution/Executor.java) |
| [com.intellij.externalAnnotator](https://jb.gg/ipe?extensions=com.intellij.externalAnnotator) | [`ExternalAnnotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) |
| [com.intellij.facet.toolWindow](https://jb.gg/ipe?extensions=com.intellij.facet.toolWindow) | [`ToolWindowFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.facetType](https://jb.gg/ipe?extensions=com.intellij.facetType) | [`FacetType`](upsource:///platform/lang-core/src/com/intellij/facet/FacetType.java) |
| [com.intellij.favoriteNodeProvider](https://jb.gg/ipe?extensions=com.intellij.favoriteNodeProvider) ![Project-Level][project-level] | [`FavoriteNodeProvider`](upsource:///platform/lang-api/src/com/intellij/ide/favoritesTreeView/FavoriteNodeProvider.java) |
| [com.intellij.favoritesListProvider](https://jb.gg/ipe?extensions=com.intellij.favoritesListProvider) ![Deprecated][deprecated] ![Project-Level][project-level] | [`FavoritesListProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/favoritesTreeView/FavoritesListProvider.java) |
| [com.intellij.filePasteProvider](https://jb.gg/ipe?extensions=com.intellij.filePasteProvider) | [`PasteProvider`](upsource:///platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.fileStructureGroupRuleProvider](https://jb.gg/ipe?extensions=com.intellij.fileStructureGroupRuleProvider) | [`FileStructureGroupRuleProvider`](upsource:///platform/usageView-impl/src/com/intellij/usages/impl/FileStructureGroupRuleProvider.java) |
| [com.intellij.fileTemplateGroup](https://jb.gg/ipe?extensions=com.intellij.fileTemplateGroup) | [`FileTemplateGroupDescriptorFactory`](upsource:///platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptorFactory.java) |
| [com.intellij.fileType.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.fileType.fileViewProviderFactory) | [`FileViewProviderFactory`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.fileTypeStatisticProvider](https://jb.gg/ipe?extensions=com.intellij.fileTypeStatisticProvider) | [`FileTypeStatisticProvider`](upsource:///platform/platform-impl/src/com/intellij/internal/statistic/fileTypes/FileTypeStatisticProvider.java) |
| [com.intellij.filetype.prebuiltStubsProvider](https://jb.gg/ipe?extensions=com.intellij.filetype.prebuiltStubsProvider) ![Deprecated][deprecated] | [`PrebuiltStubsProvider`](upsource:///platform/indexing-impl/src/com/intellij/psi/stubs/PrebuiltStubs.kt) |
| [com.intellij.filetype.stubBuilder](https://jb.gg/ipe?extensions=com.intellij.filetype.stubBuilder) | [`BinaryFileStubBuilder`](upsource:///platform/core-api/src/com/intellij/psi/stubs/BinaryFileStubBuilder.java) |
| [com.intellij.findInProjectExtension](https://jb.gg/ipe?extensions=com.intellij.findInProjectExtension) ![Internal API][internal] | [`FindInProjectExtension`](upsource:///platform/lang-impl/src/com/intellij/find/impl/FindInProjectExtension.kt) |
| [com.intellij.findUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.findUsagesHandlerFactory) ![Project-Level][project-level] | [`FindUsagesHandlerFactory`](upsource:///platform/lang-impl/src/com/intellij/find/findUsages/FindUsagesHandlerFactory.java) |
| [com.intellij.focusModeProvider](https://jb.gg/ipe?extensions=com.intellij.focusModeProvider) ![Experimental API][experimental] | [`FocusModeProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/focusMode/FocusModeProvider.java) |
| [com.intellij.framework.detector](https://jb.gg/ipe?extensions=com.intellij.framework.detector) | [`FrameworkDetector`](upsource:///platform/lang-api/src/com/intellij/framework/detection/FrameworkDetector.java) |
| [com.intellij.generalCodeStyleOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalCodeStyleOptionsProvider) | [`GeneralCodeStyleOptionsProvider`](upsource:///platform/lang-impl/src/com/intellij/application/options/GeneralCodeStyleOptionsProvider.java) |
| [com.intellij.generatedSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.generatedSourcesFilter) | [`GeneratedSourcesFilter`](upsource:///platform/analysis-api/src/com/intellij/openapi/roots/GeneratedSourcesFilter.java) |
| [com.intellij.globalIndexFilter](https://jb.gg/ipe?extensions=com.intellij.globalIndexFilter) ![Internal API][internal] | [`GlobalIndexFilter`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/GlobalIndexFilter.java) |
| [com.intellij.goto.nonProjectScopeDisabler](https://jb.gg/ipe?extensions=com.intellij.goto.nonProjectScopeDisabler) | `n/a` |
| [com.intellij.gotoActionAliasMatcher](https://jb.gg/ipe?extensions=com.intellij.gotoActionAliasMatcher) | [`GotoActionAliasMatcher`](upsource:///platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoActionAliasMatcher.java) |
| [com.intellij.gotoClassContributor](https://jb.gg/ipe?extensions=com.intellij.gotoClassContributor) | [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileContributor](https://jb.gg/ipe?extensions=com.intellij.gotoFileContributor) | [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileCustomizer](https://jb.gg/ipe?extensions=com.intellij.gotoFileCustomizer) | [`GotoFileCustomizer`](upsource:///platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoFileCustomizer.java) |
| [com.intellij.gotoPrimeSymbolContributor](https://jb.gg/ipe?extensions=com.intellij.gotoPrimeSymbolContributor) | [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoRelatedProvider](https://jb.gg/ipe?extensions=com.intellij.gotoRelatedProvider) | [`GotoRelatedProvider`](upsource:///platform/lang-api/src/com/intellij/navigation/GotoRelatedProvider.java) |
| [com.intellij.gotoSymbolContributor](https://jb.gg/ipe?extensions=com.intellij.gotoSymbolContributor) | [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoTargetPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetPresentationProvider) | [`GotoTargetPresentationProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetPresentationProvider.java) |
| [com.intellij.gotoTargetRendererProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetRendererProvider) ![Deprecated][deprecated] | [`GotoTargetRendererProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetRendererProvider.java) |
| [com.intellij.hectorComponentProvider](https://jb.gg/ipe?extensions=com.intellij.hectorComponentProvider) ![Project-Level][project-level] | [`HectorComponentPanelsProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/editor/HectorComponentPanelsProvider.java) |
| [com.intellij.highlightInfoPostFilter](https://jb.gg/ipe?extensions=com.intellij.highlightInfoPostFilter) ![Project-Level][project-level] | [`HighlightInfoPostFilter`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoPostFilter.java) |
| [com.intellij.highlightRangeExtension](https://jb.gg/ipe?extensions=com.intellij.highlightRangeExtension) | [`HighlightRangeExtension`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightRangeExtension.java) |
| [com.intellij.highlightUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.highlightUsagesHandlerFactory) | [`HighlightUsagesHandlerFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) |
| [com.intellij.highlightVisitor](https://jb.gg/ipe?extensions=com.intellij.highlightVisitor) ![Project-Level][project-level] | [`HighlightVisitor`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightVisitor.java) |
| [com.intellij.highlightingPassFactory](https://jb.gg/ipe?extensions=com.intellij.highlightingPassFactory) | [`TextEditorHighlightingPassFactoryRegistrar`](upsource:///platform/analysis-impl/src/com/intellij/codeHighlighting/TextEditorHighlightingPassFactoryRegistrar.java) |
| [com.intellij.iconProvider](https://jb.gg/ipe?extensions=com.intellij.iconProvider) | [`IconProvider`](upsource:///platform/core-api/src/com/intellij/ide/IconProvider.java) |
| [com.intellij.idIndexer](https://jb.gg/ipe?extensions=com.intellij.idIndexer) | [`IdIndexer`](upsource:///platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/id/IdIndexer.java) |
| [com.intellij.implementationViewDocumentFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewDocumentFactory) | [`ImplementationViewDocumentFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewDocumentFactory.kt) |
| [com.intellij.implementationViewSessionFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewSessionFactory) | [`ImplementationViewSessionFactory`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewSession.kt) |
| [com.intellij.implicitUsageProvider](https://jb.gg/ipe?extensions=com.intellij.implicitUsageProvider) | [`ImplicitUsageProvider`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/ImplicitUsageProvider.java) |
| [com.intellij.importFilteringRule](https://jb.gg/ipe?extensions=com.intellij.importFilteringRule) | [`ImportFilteringRule`](upsource:///platform/usageView/src/com/intellij/usages/rules/ImportFilteringRule.java) |
| [com.intellij.include.provider](https://jb.gg/ipe?extensions=com.intellij.include.provider) | [`FileIncludeProvider`](upsource:///platform/lang-impl/src/com/intellij/psi/impl/include/FileIncludeProvider.java) |
| [com.intellij.indexPatternBuilder](https://jb.gg/ipe?extensions=com.intellij.indexPatternBuilder) | [`IndexPatternBuilder`](upsource:///platform/indexing-impl/src/com/intellij/psi/impl/search/IndexPatternBuilder.java) |
| [com.intellij.indexPatternProvider](https://jb.gg/ipe?extensions=com.intellij.indexPatternProvider) ![Non-Dynamic][non-dynamic] | [`IndexPatternProvider`](upsource:///platform/indexing-api/src/com/intellij/psi/search/IndexPatternProvider.java) |
| [com.intellij.indexPatternSearch](https://jb.gg/ipe?extensions=com.intellij.indexPatternSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.indexableEntityProvider](https://jb.gg/ipe?extensions=com.intellij.indexableEntityProvider) ![Experimental API][experimental] | [`IndexableEntityProvider`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/roots/IndexableEntityProvider.java) |
| [com.intellij.indexableFilesFilter](https://jb.gg/ipe?extensions=com.intellij.indexableFilesFilter) ![Non-Dynamic][non-dynamic] ![Experimental API][experimental] ![Internal API][internal] | [`IndexableFilesFilter`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/IndexableFilesFilter.java) |
| [com.intellij.indexableIteratorBuilderHandler](https://jb.gg/ipe?extensions=com.intellij.indexableIteratorBuilderHandler) | [`IndexableIteratorBuilderHandler`](upsource:///platform/lang-impl/src/com/intellij/util/indexing/roots/builders/IndexableIteratorBuilderHandler.java) |
| [com.intellij.inspectionElementsMerger](https://jb.gg/ipe?extensions=com.intellij.inspectionElementsMerger) | [`InspectionElementsMerger`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/ex/InspectionElementsMerger.java) |
| [com.intellij.inspectionProfileActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionProfileActionProvider) | [`InspectionProfileActionProvider`](upsource:///platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionProfileActionProvider.java) |
| [com.intellij.inspectionResultsExportActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionResultsExportActionProvider) | [`InspectionResultsExportActionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInspection/ui/actions/InspectionResultsExportActionProvider.kt) |
| [com.intellij.intentionMenuContributor](https://jb.gg/ipe?extensions=com.intellij.intentionMenuContributor) ![Internal API][internal] | [`IntentionMenuContributor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IntentionMenuContributor.java) |
| [com.intellij.intentionsOrderProvider](https://jb.gg/ipe?extensions=com.intellij.intentionsOrderProvider) | [`IntentionsOrderProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionsOrderProvider.java) |
| [com.intellij.internalFileTemplate](https://jb.gg/ipe?extensions=com.intellij.internalFileTemplate) | `n/a` |
| [com.intellij.lang.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.lang.braceMatcher) | [`PairedBraceMatcher`](upsource:///platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java) |
| [com.intellij.lang.codeReferenceSearcher](https://jb.gg/ipe?extensions=com.intellij.lang.codeReferenceSearcher) | [`CodeReferenceSearcher`](upsource:///platform/indexing-api/src/com/intellij/model/search/CodeReferenceSearcher.java) |
| [com.intellij.lang.commenter](https://jb.gg/ipe?extensions=com.intellij.lang.commenter) | [`Commenter`](upsource:///platform/core-api/src/com/intellij/lang/Commenter.java) |
| [com.intellij.lang.directNavigationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.directNavigationProvider) ![Experimental API][experimental] | [`DirectNavigationProvider`](upsource:///platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java) |
| [com.intellij.lang.documentationFixer](https://jb.gg/ipe?extensions=com.intellij.lang.documentationFixer) | [`DocCommentFixer`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/documentation/DocCommentFixer.java) |
| [com.intellij.lang.documentationLinkHandler](https://jb.gg/ipe?extensions=com.intellij.lang.documentationLinkHandler) ![Experimental API][experimental] | [`DocumentationLinkHandler`](upsource:///platform/lang-impl/src/com/intellij/lang/documentation/DocumentationLinkHandler.java) |
| [com.intellij.lang.documentationToolWindowManager](https://jb.gg/ipe?extensions=com.intellij.lang.documentationToolWindowManager) | [`DocToolWindowManager`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/documentation/DocToolWindowManager.java) |
| [com.intellij.lang.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.lang.fileViewProviderFactory) | [`FileViewProviderFactory`](upsource:///platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.lang.findUsagesProvider](https://jb.gg/ipe?extensions=com.intellij.lang.findUsagesProvider) | [`FindUsagesProvider`](upsource:///platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) |
| [com.intellij.lang.foldingBuilder](https://jb.gg/ipe?extensions=com.intellij.lang.foldingBuilder) | [`FoldingBuilder`](upsource:///platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) |
| [com.intellij.lang.implementationTextProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextProcessor) | [`ImplementationTextProcessor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextProcessor.java) |
| [com.intellij.lang.implementationTextSelectioner](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextSelectioner) | [`ImplementationTextSelectioner`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) |
| [com.intellij.lang.psiDocumentation](https://jb.gg/ipe?extensions=com.intellij.lang.psiDocumentation) ![Experimental API][experimental] | [`PsiDocumentationTargetFactory`](upsource:///platform/lang-impl/src/com/intellij/lang/documentation/psi/PsiDocumentationTargetFactory.java) |
| [com.intellij.lang.psiElementExternalizer](https://jb.gg/ipe?extensions=com.intellij.lang.psiElementExternalizer) | [`PsiElementExternalizer`](upsource:///platform/lang-api/src/com/intellij/lang/PsiElementExternalizer.java) |
| [com.intellij.lang.psiStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.lang.psiStructureViewFactory) | [`PsiStructureViewFactory`](upsource:///platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) |
| [com.intellij.lang.sliceProvider](https://jb.gg/ipe?extensions=com.intellij.lang.sliceProvider) | [`SliceLanguageSupportProvider`](upsource:///platform/lang-impl/src/com/intellij/slicer/SliceLanguageSupportProvider.java) |
| [com.intellij.lang.structureViewExtension](https://jb.gg/ipe?extensions=com.intellij.lang.structureViewExtension) | [`StructureViewExtension`](upsource:///platform/structure-view-impl/src/com/intellij/ide/structureView/StructureViewExtension.java) |
| [com.intellij.lang.substitutor](https://jb.gg/ipe?extensions=com.intellij.lang.substitutor) | [`LanguageSubstitutor`](upsource:///platform/core-api/src/com/intellij/psi/LanguageSubstitutor.java) |
| [com.intellij.lang.surroundDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.surroundDescriptor) | [`SurroundDescriptor`](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) |
| [com.intellij.lang.symbolDocumentation](https://jb.gg/ipe?extensions=com.intellij.lang.symbolDocumentation) ![Experimental API][experimental] | [`SymbolDocumentationTargetFactory`](upsource:///platform/lang-impl/src/com/intellij/lang/documentation/symbol/SymbolDocumentationTargetFactory.java) |
| [com.intellij.lang.symbolSearchTarget](https://jb.gg/ipe?extensions=com.intellij.lang.symbolSearchTarget) | [`SymbolSearchTargetFactory`](upsource:///platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java) |
| [com.intellij.lang.symbolTypeProvider](https://jb.gg/ipe?extensions=com.intellij.lang.symbolTypeProvider) ![Experimental API][experimental] | [`SymbolTypeProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/navigation/SymbolTypeProvider.java) |
| [com.intellij.lang.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighter) | [`SyntaxHighlighter`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |
| [com.intellij.lang.treePatcher](https://jb.gg/ipe?extensions=com.intellij.lang.treePatcher) | [`TreePatcher`](upsource:///platform/core-impl/src/com/intellij/psi/templateLanguages/TreePatcher.java) |
| [com.intellij.lang.unwrapDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.unwrapDescriptor) | [`UnwrapDescriptor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/unwrap/UnwrapDescriptor.java) |
| [com.intellij.library.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.library.presentationProvider) | [`LibraryPresentationProvider`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryPresentationProvider.java) |
| [com.intellij.library.type](https://jb.gg/ipe?extensions=com.intellij.library.type) | [`LibraryType`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryType.java) |
| [com.intellij.librarySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.librarySettingsProvider) | [`LibrarySettingsProvider`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/LibrarySettingsProvider.java) |
| [com.intellij.liveTemplateSubstitutor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateSubstitutor) | [`TemplateSubstitutor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/TemplateSubstitutor.java) |
| [com.intellij.longLineInspectionPolicy](https://jb.gg/ipe?extensions=com.intellij.longLineInspectionPolicy) | [`LongLineInspectionPolicy`](upsource:///platform/lang-impl/src/com/intellij/codeInspection/longLine/LongLineInspectionPolicy.java) |
| [com.intellij.macro](https://jb.gg/ipe?extensions=com.intellij.macro) | [`Macro`](upsource:///platform/macro/src/com/intellij/ide/macro/Macro.java) |
| [com.intellij.macroFilter](https://jb.gg/ipe?extensions=com.intellij.macroFilter) | [`MacroFilter`](upsource:///platform/macro/src/com/intellij/ide/macro/MacroFilter.java) |
| [com.intellij.metaDataContributor](https://jb.gg/ipe?extensions=com.intellij.metaDataContributor) | [`MetaDataContributor`](upsource:///platform/core-api/src/com/intellij/psi/meta/MetaDataContributor.java) |
| [com.intellij.methodHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.methodHierarchyProvider) | [`HierarchyProvider`](upsource:///platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.methodNavigationOffsetProvider](https://jb.gg/ipe?extensions=com.intellij.methodNavigationOffsetProvider) | [`MethodNavigationOffsetProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/navigation/MethodNavigationOffsetProvider.java) |
| [com.intellij.modelScopeItemPresenter](https://jb.gg/ipe?extensions=com.intellij.modelScopeItemPresenter) ![Non-Dynamic][non-dynamic] | [`ModelScopeItemPresenter`](upsource:///platform/lang-impl/src/com/intellij/analysis/dialog/ModelScopeItemPresenter.java) |
| [com.intellij.module.workingDirectoryProvider](https://jb.gg/ipe?extensions=com.intellij.module.workingDirectoryProvider) | [`WorkingDirectoryProvider`](upsource:///platform/execution-impl/src/com/intellij/openapi/module/WorkingDirectoryProvider.java) |
| [com.intellij.moduleBuilder](https://jb.gg/ipe?extensions=com.intellij.moduleBuilder) | [`ModuleBuilder`](upsource:///platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) |
| [com.intellij.moduleConfigurationEditorProvider](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurationEditorProvider) | [`ModuleConfigurationEditorProvider`](upsource:///platform/lang-core/src/com/intellij/openapi/roots/ui/configuration/ModuleConfigurationEditorProvider.java) |
| [com.intellij.moduleNameGenerator](https://jb.gg/ipe?extensions=com.intellij.moduleNameGenerator) ![Experimental API][experimental] | [`ModuleNameGenerator`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleNameGenerator.java) |
| [com.intellij.moduleRendererFactory](https://jb.gg/ipe?extensions=com.intellij.moduleRendererFactory) | [`ModuleRendererFactory`](upsource:///platform/lang-impl/src/com/intellij/ide/util/ModuleRendererFactory.java) |
| [com.intellij.moduleType](https://jb.gg/ipe?extensions=com.intellij.moduleType) | [`ModuleType`](upsource:///platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) |
| [com.intellij.multiLangCommenter](https://jb.gg/ipe?extensions=com.intellij.multiLangCommenter) | [`MultipleLangCommentProvider`](upsource:///platform/lang-impl/src/com/intellij/psi/templateLanguages/MultipleLangCommentProvider.java) |
| [com.intellij.multipleRunLocationsProvider](https://jb.gg/ipe?extensions=com.intellij.multipleRunLocationsProvider) | [`MultipleRunLocationsProvider`](upsource:///platform/lang-api/src/com/intellij/execution/actions/MultipleRunLocationsProvider.kt) |
| [com.intellij.navbar](https://jb.gg/ipe?extensions=com.intellij.navbar) | [`NavBarModelExtension`](upsource:///platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelExtension.java) |
| [com.intellij.navbarLeftSide](https://jb.gg/ipe?extensions=com.intellij.navbarLeftSide) ![Internal API][internal] | [`NavBarLeftSideExtension`](upsource:///platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarLeftSideExtension.java) |
| [com.intellij.optionsApplicabilityFilter](https://jb.gg/ipe?extensions=com.intellij.optionsApplicabilityFilter) | [`OptionsApplicabilityFilter`](upsource:///platform/lang-impl/src/com/intellij/application/options/OptionsApplicabilityFilter.java) |
| [com.intellij.outOfSourcesChecker](https://jb.gg/ipe?extensions=com.intellij.outOfSourcesChecker) | [`OutOfSourcesChecker`](upsource:///platform/lang-api/src/com/intellij/openapi/projectRoots/OutOfSourcesChecker.java) |
| [com.intellij.packageDependencies.visitor](https://jb.gg/ipe?extensions=com.intellij.packageDependencies.visitor) | [`DependencyVisitorFactory`](upsource:///platform/analysis-impl/src/com/intellij/packageDependencies/DependencyVisitorFactory.java) |
| [com.intellij.pathReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.pathReferenceProvider) | [`PathReferenceProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.patternDialectProvider](https://jb.gg/ipe?extensions=com.intellij.patternDialectProvider) ![Non-Dynamic][non-dynamic] | [`PatternDialectProvider`](upsource:///platform/lang-impl/src/com/intellij/packageDependencies/ui/PatternDialectProvider.java) |
| [com.intellij.patternProvider](https://jb.gg/ipe?extensions=com.intellij.patternProvider) | [`PatternProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/generation/PatternProvider.java) |
| [com.intellij.patterns.patternClass](https://jb.gg/ipe?extensions=com.intellij.patterns.patternClass) | `Object` |
| [com.intellij.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.presentationProvider) | [`PresentationProvider`](upsource:///platform/analysis-api/src/com/intellij/ide/presentation/PresentationProvider.java) |
| [com.intellij.previewHintProvider](https://jb.gg/ipe?extensions=com.intellij.previewHintProvider) ![Deprecated][deprecated] | [`PreviewHintProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/preview/PreviewHintProvider.java) |
| [com.intellij.printHandler](https://jb.gg/ipe?extensions=com.intellij.printHandler) | [`PrintActionHandler`](upsource:///platform/lang-api/src/com/intellij/ide/actions/PrintActionHandler.java) |
| [com.intellij.printOption](https://jb.gg/ipe?extensions=com.intellij.printOption) | [`PrintOption`](upsource:///platform/lang-impl/src/com/intellij/codeEditor/printing/PrintOption.java) |
| [com.intellij.problemFileHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.problemFileHighlightFilter) ![Project-Level][project-level] | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.problemHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.problemHighlightFilter) | [`ProblemHighlightFilter`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/ProblemHighlightFilter.java) |
| [com.intellij.problemsViewPanelProvider](https://jb.gg/ipe?extensions=com.intellij.problemsViewPanelProvider) ![Project-Level][project-level] | [`ProblemsViewPanelProvider`](upsource:///platform/lang-impl/src/com/intellij/analysis/problemsView/toolWindow/ProblemsViewPanelProvider.kt) |
| [com.intellij.programRunner](https://jb.gg/ipe?extensions=com.intellij.programRunner) | [`ProgramRunner`](upsource:///platform/execution/src/com/intellij/execution/runners/ProgramRunner.java) |
| [com.intellij.project.converterProvider](https://jb.gg/ipe?extensions=com.intellij.project.converterProvider) | [`ConverterProvider`](upsource:///platform/lang-impl/src/com/intellij/conversion/ConverterProvider.java) |
| [com.intellij.projectFacetListener](https://jb.gg/ipe?extensions=com.intellij.projectFacetListener) | [`ProjectFacetListener`](upsource:///platform/lang-api/src/com/intellij/facet/ProjectFacetListener.java) |
| [com.intellij.projectSdkSetupValidator](https://jb.gg/ipe?extensions=com.intellij.projectSdkSetupValidator) | [`ProjectSdkSetupValidator`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java) |
| [com.intellij.projectStructure.sourceRootEditHandler](https://jb.gg/ipe?extensions=com.intellij.projectStructure.sourceRootEditHandler) | [`ModuleSourceRootEditHandler`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/ModuleSourceRootEditHandler.java) |
| [com.intellij.projectTaskRunner](https://jb.gg/ipe?extensions=com.intellij.projectTaskRunner) | [`ProjectTaskRunner`](upsource:///platform/lang-api/src/com/intellij/task/ProjectTaskRunner.java) |
| [com.intellij.projectTemplateFileProcessor](https://jb.gg/ipe?extensions=com.intellij.projectTemplateFileProcessor) | [`ProjectTemplateFileProcessor`](upsource:///platform/lang-impl/src/com/intellij/ide/util/projectWizard/ProjectTemplateFileProcessor.java) |
| [com.intellij.projectTemplateParameterFactory](https://jb.gg/ipe?extensions=com.intellij.projectTemplateParameterFactory) | [`ProjectTemplateParameterFactory`](upsource:///platform/lang-core/src/com/intellij/ide/util/projectWizard/ProjectTemplateParameterFactory.java) |
| [com.intellij.projectViewNestingRulesProvider](https://jb.gg/ipe?extensions=com.intellij.projectViewNestingRulesProvider) | [`ProjectViewNestingRulesProvider`](upsource:///platform/lang-api/src/com/intellij/ide/projectView/ProjectViewNestingRulesProvider.java) |
| [com.intellij.projectViewNodeDecorator](https://jb.gg/ipe?extensions=com.intellij.projectViewNodeDecorator) ![Project-Level][project-level] | [`ProjectViewNodeDecorator`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/ProjectViewNodeDecorator.java) |
| [com.intellij.projectViewPane](https://jb.gg/ipe?extensions=com.intellij.projectViewPane) ![Project-Level][project-level] | [`AbstractProjectViewPane`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/impl/AbstractProjectViewPane.java) |
| [com.intellij.projectViewPaneSelectionHelper](https://jb.gg/ipe?extensions=com.intellij.projectViewPaneSelectionHelper) | [`ProjectViewPaneSelectionHelper`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewPaneSelectionHelper.java) |
| [com.intellij.properties.files.provider](https://jb.gg/ipe?extensions=com.intellij.properties.files.provider) | [`PropertiesProvider`](upsource:///platform/lang-api/src/com/intellij/properties/provider/PropertiesProvider.java) |
| [com.intellij.psi.declarationProvider](https://jb.gg/ipe?extensions=com.intellij.psi.declarationProvider) | [`PsiSymbolDeclarationProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java) |
| [com.intellij.psi.referenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.referenceProvider) ![Non-Dynamic][non-dynamic] | [`PsiReferenceProvider`](upsource:///platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| [com.intellij.psiViewerExtension](https://jb.gg/ipe?extensions=com.intellij.psiViewerExtension) | [`PsiViewerExtension`](upsource:///platform/lang-impl/src/com/intellij/internal/psiView/PsiViewerExtension.java) |
| [com.intellij.readWriteAccessDetector](https://jb.gg/ipe?extensions=com.intellij.readWriteAccessDetector) | [`ReadWriteAccessDetector`](upsource:///platform/core-api/src/com/intellij/codeInsight/highlighting/ReadWriteAccessDetector.java) |
| [com.intellij.readerModeMatcher](https://jb.gg/ipe?extensions=com.intellij.readerModeMatcher) | [`ReaderModeMatcher`](upsource:///platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeMatcher.kt) |
| [com.intellij.readerModeProvider](https://jb.gg/ipe?extensions=com.intellij.readerModeProvider) | [`ReaderModeProvider`](upsource:///platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeProvider.kt) |
| [com.intellij.refGraphAnnotator](https://jb.gg/ipe?extensions=com.intellij.refGraphAnnotator) | [`RefGraphAnnotator`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/reference/RefGraphAnnotator.java) |
| [com.intellij.referenceImporter](https://jb.gg/ipe?extensions=com.intellij.referenceImporter) | [`ReferenceImporter`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/ReferenceImporter.java) |
| [com.intellij.referenceInjector](https://jb.gg/ipe?extensions=com.intellij.referenceInjector) | [`ReferenceInjector`](upsource:///platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) |
| [com.intellij.referenceProviderType](https://jb.gg/ipe?extensions=com.intellij.referenceProviderType) | [`PsiReferenceProvider`](upsource:///platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| [com.intellij.retypeFileAssistant](https://jb.gg/ipe?extensions=com.intellij.retypeFileAssistant) | [`RetypeFileAssistant`](upsource:///platform/lang-impl/src/com/intellij/internal/retype/RetypeFileAction.kt) |
| [com.intellij.roots.watchedRootsProvider](https://jb.gg/ipe?extensions=com.intellij.roots.watchedRootsProvider) | [`WatchedRootsProvider`](upsource:///platform/lang-api/src/com/intellij/openapi/roots/WatchedRootsProvider.java) |
| [com.intellij.runAnything.commandCustomizer](https://jb.gg/ipe?extensions=com.intellij.runAnything.commandCustomizer) | [`RunAnythingCommandCustomizer`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/commands/RunAnythingCommandCustomizer.java) |
| [com.intellij.runAnything.commandHandler](https://jb.gg/ipe?extensions=com.intellij.runAnything.commandHandler) | [`RunAnythingCommandHandler`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/handlers/RunAnythingCommandHandler.java) |
| [com.intellij.runAnything.executionProvider](https://jb.gg/ipe?extensions=com.intellij.runAnything.executionProvider) | [`RunAnythingProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingProvider.java) |
| [com.intellij.runAnything.helpGroup](https://jb.gg/ipe?extensions=com.intellij.runAnything.helpGroup) | [`RunAnythingHelpGroup`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/groups/RunAnythingHelpGroup.java) |
| [com.intellij.runConfigurationBeforeRunProviderDelegate](https://jb.gg/ipe?extensions=com.intellij.runConfigurationBeforeRunProviderDelegate) | [`RunConfigurationBeforeRunProviderDelegate`](upsource:///platform/execution-impl/src/com/intellij/execution/impl/RunConfigurationBeforeRunProviderDelegate.java) |
| [com.intellij.runConfigurationProducer](https://jb.gg/ipe?extensions=com.intellij.runConfigurationProducer) | [`RunConfigurationProducer`](upsource:///platform/lang-api/src/com/intellij/execution/actions/RunConfigurationProducer.java) |
| [com.intellij.runConfigurationTargetEnvironmentAdjuster](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTargetEnvironmentAdjuster) ![Internal API][internal] | [`RunConfigurationTargetEnvironmentAdjuster`](upsource:///platform/execution/src/com/intellij/execution/target/RunConfigurationTargetEnvironmentAdjuster.kt) |
| [com.intellij.runConfigurationTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTemplateProvider) ![Project-Level][project-level] | [`RunConfigurationTemplateProvider`](upsource:///platform/execution-impl/src/com/intellij/execution/impl/RunManagerImpl.kt) |
| [com.intellij.runConfigurationsSettings](https://jb.gg/ipe?extensions=com.intellij.runConfigurationsSettings) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`RunConfigurationsSettings`](upsource:///platform/execution/src/com/intellij/execution/configurations/RunConfigurationsSettings.java) |
| [com.intellij.runDashboardCustomizer](https://jb.gg/ipe?extensions=com.intellij.runDashboardCustomizer) | [`RunDashboardCustomizer`](upsource:///platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardCustomizer.java) |
| [com.intellij.runDashboardDefaultTypesProvider](https://jb.gg/ipe?extensions=com.intellij.runDashboardDefaultTypesProvider) | [`RunDashboardDefaultTypesProvider`](upsource:///platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardDefaultTypesProvider.java) |
| [com.intellij.runDashboardGroupingRule](https://jb.gg/ipe?extensions=com.intellij.runDashboardGroupingRule) | [`RunDashboardGroupingRule`](upsource:///platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardGroupingRule.java) |
| [com.intellij.runLineMarkerContributor](https://jb.gg/ipe?extensions=com.intellij.runLineMarkerContributor) | [`RunLineMarkerContributor`](upsource:///platform/execution-impl/src/com/intellij/execution/lineMarker/RunLineMarkerContributor.java) |
| [com.intellij.runToolbarProcess](https://jb.gg/ipe?extensions=com.intellij.runToolbarProcess) | [`RunToolbarProcess`](upsource:///platform/execution/src/com/intellij/execution/runToolbar/RunToolbarProcess.kt) |
| [com.intellij.runningApplicationUpdaterProvider](https://jb.gg/ipe?extensions=com.intellij.runningApplicationUpdaterProvider) | [`RunningApplicationUpdaterProvider`](upsource:///platform/execution-impl/src/com/intellij/execution/update/RunningApplicationUpdaterProvider.java) |
| [com.intellij.saveFileAsTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.saveFileAsTemplateHandler) | [`SaveFileAsTemplateHandler`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/SaveFileAsTemplateHandler.java) |
| [com.intellij.scopeDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.scopeDescriptorProvider) | [`ScopeDescriptorProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeDescriptorProvider.java) |
| [com.intellij.scopeParserExtension](https://jb.gg/ipe?extensions=com.intellij.scopeParserExtension) | [`PackageSetParserExtension`](upsource:///platform/lang-impl/src/com/intellij/psi/search/scope/packageSet/PackageSetParserExtension.java) |
| [com.intellij.scratch.creationHelper](https://jb.gg/ipe?extensions=com.intellij.scratch.creationHelper) | [`ScratchFileCreationHelper`](upsource:///platform/lang-impl/src/com/intellij/ide/scratch/ScratchFileCreationHelper.java) |
| [com.intellij.scratch.rootType](https://jb.gg/ipe?extensions=com.intellij.scratch.rootType) | [`RootType`](upsource:///platform/analysis-api/src/com/intellij/ide/scratch/RootType.java) |
| [com.intellij.sdkDownload](https://jb.gg/ipe?extensions=com.intellij.sdkDownload) | [`SdkDownload`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/projectRoot/SdkDownload.java) |
| [com.intellij.sdkFinder](https://jb.gg/ipe?extensions=com.intellij.sdkFinder) | [`SdkFinder`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/impl/SdkFinder.java) |
| [com.intellij.sdkType](https://jb.gg/ipe?extensions=com.intellij.sdkType) | [`SdkType`](upsource:///platform/lang-core/src/com/intellij/openapi/projectRoots/SdkType.java) |
| [com.intellij.searchEverywhereClassifier](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereClassifier) | [`SearchEverywhereClassifier`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/SearchEverywhereClassifier.java) |
| [com.intellij.searchEverywhereContributor](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereContributor) | [`SearchEverywhereContributorFactory`](upsource:///platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributorFactory.java) |
| [com.intellij.searchEverywhereMlService](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMlService) | [`SearchEverywhereMlService`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| [com.intellij.searchEverywhereResultsEqualityProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereResultsEqualityProvider) ![Non-Dynamic][non-dynamic] | [`SEResultsEqualityProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEResultsEqualityProvider.kt) |
| [com.intellij.searchScopesProvider](https://jb.gg/ipe?extensions=com.intellij.searchScopesProvider) | [`SearchScopeProvider`](upsource:///platform/analysis-impl/src/com/intellij/psi/search/SearchScopeProvider.java) |
| [com.intellij.searcher](https://jb.gg/ipe?extensions=com.intellij.searcher) | [`Searcher`](upsource:///platform/indexing-api/src/com/intellij/model/search/Searcher.kt) |
| [com.intellij.semContributor](https://jb.gg/ipe?extensions=com.intellij.semContributor) | [`SemContributor`](upsource:///platform/lang-api/src/com/intellij/semantic/SemContributor.java) |
| [com.intellij.serviceViewContributor](https://jb.gg/ipe?extensions=com.intellij.serviceViewContributor) | [`ServiceViewContributor`](upsource:///platform/lang-api/src/com/intellij/execution/services/ServiceViewContributor.java) |
| [com.intellij.silentChangeVetoer](https://jb.gg/ipe?extensions=com.intellij.silentChangeVetoer) ![Internal API][internal] | [`SilentChangeVetoer`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SilentChangeVetoer.kt) |
| [com.intellij.stacktrace.fold](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold) | `n/a` |
| [com.intellij.stacktrace.fold.line.modifier](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold.line.modifier) ![Experimental API][experimental] | [`ConsoleLineModifier`](upsource:///platform/lang-impl/src/com/intellij/execution/console/ConsoleLineModifier.java) |
| [com.intellij.statistician](https://jb.gg/ipe?extensions=com.intellij.statistician) | [`Statistician`](upsource:///platform/analysis-api/src/com/intellij/psi/statistics/Statistician.java) |
| [com.intellij.stepsBeforeRunProvider](https://jb.gg/ipe?extensions=com.intellij.stepsBeforeRunProvider) ![Project-Level][project-level] | [`BeforeRunTaskProvider`](upsource:///platform/execution/src/com/intellij/execution/BeforeRunTaskProvider.java) |
| [com.intellij.structureViewBuilder](https://jb.gg/ipe?extensions=com.intellij.structureViewBuilder) | [`StructureViewBuilder`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewBuilder.java) |
| [com.intellij.symbolDeclarationPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.symbolDeclarationPresentationProvider) | [`SymbolDeclarationPresentationProvider`](upsource:///platform/core-api/src/com/intellij/model/presentation/SymbolDeclarationPresentationProvider.java) |
| [com.intellij.symbolNavigation](https://jb.gg/ipe?extensions=com.intellij.symbolNavigation) ![Experimental API][experimental] | [`SymbolNavigationProvider`](upsource:///platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java) |
| [com.intellij.symbolPresentation](https://jb.gg/ipe?extensions=com.intellij.symbolPresentation) | [`SymbolPresentationProvider`](upsource:///platform/core-api/src/com/intellij/model/presentation/SymbolPresentationProvider.java) |
| [com.intellij.targetElementEvaluator](https://jb.gg/ipe?extensions=com.intellij.targetElementEvaluator) | [`TargetElementEvaluator`](upsource:///platform/core-impl/src/com/intellij/codeInsight/TargetElementEvaluator.java) |
| [com.intellij.targetElementUtilExtender](https://jb.gg/ipe?extensions=com.intellij.targetElementUtilExtender) | [`TargetElementUtilExtender`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/TargetElementUtilExtender.java) |
| [com.intellij.templateCompletionProcessor](https://jb.gg/ipe?extensions=com.intellij.templateCompletionProcessor) | [`TemplateCompletionProcessor`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/template/macro/TemplateCompletionProcessor.java) |
| [com.intellij.testActionProvider](https://jb.gg/ipe?extensions=com.intellij.testActionProvider) | [`ToggleModelActionProvider`](upsource:///platform/testRunner/src/com/intellij/execution/testframework/ToggleModelActionProvider.java) |
| [com.intellij.testCreator](https://jb.gg/ipe?extensions=com.intellij.testCreator) | [`TestCreator`](upsource:///platform/lang-api/src/com/intellij/testIntegration/TestCreator.java) |
| [com.intellij.testFinder](https://jb.gg/ipe?extensions=com.intellij.testFinder) | [`TestFinder`](upsource:///platform/lang-api/src/com/intellij/testIntegration/TestFinder.java) |
| [com.intellij.testSrcLocator](https://jb.gg/ipe?extensions=com.intellij.testSrcLocator) ![Deprecated][deprecated] | [`TestLocationProvider`](upsource:///platform/execution-impl/src/com/intellij/testIntegration/TestLocationProvider.java) |
| [com.intellij.todoExtraPlaces](https://jb.gg/ipe?extensions=com.intellij.todoExtraPlaces) | [`ExtraPlaceChecker`](upsource:///platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/todo/TodoIndexers.java) |
| [com.intellij.todoIndexer](https://jb.gg/ipe?extensions=com.intellij.todoIndexer) | [`DataIndexer`](upsource:///platform/util/src/com/intellij/util/indexing/DataIndexer.java) |
| [com.intellij.toolsCustomizer](https://jb.gg/ipe?extensions=com.intellij.toolsCustomizer) | [`ToolsCustomizer`](upsource:///platform/lang-impl/src/com/intellij/tools/ToolsCustomizer.java) |
| [com.intellij.toolsProvider](https://jb.gg/ipe?extensions=com.intellij.toolsProvider) | [`ToolsProvider`](upsource:///platform/lang-impl/src/com/intellij/tools/ToolsProvider.java) |
| [com.intellij.trafficLightRendererContributor](https://jb.gg/ipe?extensions=com.intellij.trafficLightRendererContributor) | [`TrafficLightRendererContributor`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/TrafficLightRendererContributor.java) |
| [com.intellij.treeGenerator](https://jb.gg/ipe?extensions=com.intellij.treeGenerator) | [`TreeGenerator`](upsource:///platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeGenerator.java) |
| [com.intellij.treeStructureProvider](https://jb.gg/ipe?extensions=com.intellij.treeStructureProvider) ![Project-Level][project-level] | [`TreeStructureProvider`](upsource:///platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) |
| [com.intellij.typeDeclarationProvider](https://jb.gg/ipe?extensions=com.intellij.typeDeclarationProvider) | [`TypeDeclarationProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/navigation/actions/TypeDeclarationProvider.java) |
| [com.intellij.typeHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.typeHierarchyProvider) | [`HierarchyProvider`](upsource:///platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.typeIcon](https://jb.gg/ipe?extensions=com.intellij.typeIcon) | `Object` |
| [com.intellij.typeName](https://jb.gg/ipe?extensions=com.intellij.typeName) | `Object` |
| [com.intellij.uiDebuggerExtension](https://jb.gg/ipe?extensions=com.intellij.uiDebuggerExtension) ![Non-Dynamic][non-dynamic] | [`UiDebuggerExtension`](upsource:///platform/lang-impl/src/com/intellij/ui/debugger/UiDebuggerExtension.java) |
| [com.intellij.usageContextPanelProvider](https://jb.gg/ipe?extensions=com.intellij.usageContextPanelProvider) ![Project-Level][project-level] | [`Provider`](upsource:///platform/usageView/src/com/intellij/usages/UsageContextPanel.java) |
| [com.intellij.usageFilteringRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageFilteringRuleProvider) | [`UsageFilteringRuleProvider`](upsource:///platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleProvider.java) |
| [com.intellij.usageGroupingRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageGroupingRuleProvider) | [`UsageGroupingRuleProvider`](upsource:///platform/usageView/src/com/intellij/usages/rules/UsageGroupingRuleProvider.java) |
| [com.intellij.usageTargetProvider](https://jb.gg/ipe?extensions=com.intellij.usageTargetProvider) | [`UsageTargetProvider`](upsource:///platform/usageView/src/com/intellij/usages/UsageTargetProvider.java) |
| [com.intellij.usageToPsiElementProvider](https://jb.gg/ipe?extensions=com.intellij.usageToPsiElementProvider) | [`UsageToPsiElementProvider`](upsource:///platform/usageView/src/com/intellij/usages/UsageToPsiElementProvider.java) |
| [com.intellij.usageTypeProvider](https://jb.gg/ipe?extensions=com.intellij.usageTypeProvider) | [`UsageTypeProvider`](upsource:///platform/usageView-impl/src/com/intellij/usages/impl/rules/UsageTypeProvider.java) |
| [com.intellij.usageViewElementsListener](https://jb.gg/ipe?extensions=com.intellij.usageViewElementsListener) | [`UsageViewElementsListener`](upsource:///platform/usageView-impl/src/com/intellij/usages/impl/UsageViewElementsListener.java) |
| [com.intellij.usageViewFactory](https://jb.gg/ipe?extensions=com.intellij.usageViewFactory) | [`UsageViewFactory`](upsource:///platform/usageView-impl/src/com/intellij/usages/impl/UsageViewFactory.java) |

### PlatformExtensionPoints.xml

[`PlatformExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ApplicationLoadListener](https://jb.gg/ipe?extensions=com.intellij.ApplicationLoadListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ApplicationLoadListener`](upsource:///platform/platform-impl/src/com/intellij/ide/ApplicationLoadListener.java) |
| [com.intellij.aboutPopupDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.aboutPopupDescriptionProvider) | [`AboutPopupDescriptionProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/AboutPopupDescriptionProvider.kt) |
| [com.intellij.achromatopsiaSupport](https://jb.gg/ipe?extensions=com.intellij.achromatopsiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](upsource:///platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.actionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.actionConfigurationCustomizer) ![Non-Dynamic][non-dynamic] | [`ActionConfigurationCustomizer`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionConfigurationCustomizer.java) |
| [com.intellij.actionFromOptionDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.actionFromOptionDescriptorProvider) | [`ActionFromOptionDescriptorProvider`](upsource:///platform/platform-api/src/com/intellij/ide/ui/search/ActionFromOptionDescriptorProvider.java) |
| [com.intellij.actionOnSave](https://jb.gg/ipe?extensions=com.intellij.actionOnSave) | [`ActionOnSave`](upsource:///platform/platform-impl/src/com/intellij/ide/actionsOnSave/impl/ActionsOnSaveFileDocumentManagerListener.java) |
| [com.intellij.actionOnSaveInfoProvider](https://jb.gg/ipe?extensions=com.intellij.actionOnSaveInfoProvider) | [`ActionOnSaveInfoProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/actionsOnSave/ActionOnSaveInfoProvider.java) |
| [com.intellij.actionPromoter](https://jb.gg/ipe?extensions=com.intellij.actionPromoter) | [`ActionPromoter`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/ActionPromoter.java) |
| [com.intellij.additionalTextAttributes](https://jb.gg/ipe?extensions=com.intellij.additionalTextAttributes) | `n/a` |
| [com.intellij.advancedSetting](https://jb.gg/ipe?extensions=com.intellij.advancedSetting) | `n/a` |
| [com.intellij.appStarter](https://jb.gg/ipe?extensions=com.intellij.appStarter) | [`ApplicationStarter`](upsource:///platform/ide-core/src/com/intellij/openapi/application/ApplicationStarter.java) |
| [com.intellij.appToolbarWidget](https://jb.gg/ipe?extensions=com.intellij.appToolbarWidget) ![Experimental API][experimental] ![Internal API][internal] | [`MainToolbarAppWidgetFactory`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/headertoolbar/MainToolbarAppWidgetFactory.kt) |
| [com.intellij.applicationConfigurable](https://jb.gg/ipe?extensions=com.intellij.applicationConfigurable) | [`Configurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.applicationInitializedListener](https://jb.gg/ipe?extensions=com.intellij.applicationInitializedListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ApplicationInitializedListener`](upsource:///platform/ide-core/src/com/intellij/ide/ApplicationInitializedListener.java) |
| [com.intellij.baseDirectorySuggester](https://jb.gg/ipe?extensions=com.intellij.baseDirectorySuggester) | [`BaseDirectorySuggester`](upsource:///platform/ide-core/src/com/intellij/openapi/project/BaseDirectorySuggester.kt) |
| [com.intellij.beforeRunStartupTasks](https://jb.gg/ipe?extensions=com.intellij.beforeRunStartupTasks) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`BeforeRunStartupTasks`](upsource:///platform/execution-impl/src/com/intellij/execution/startup/BeforeRunStartupTasks.kt) |
| [com.intellij.breadcrumbsPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsPresentationProvider) | [`BreadcrumbsPresentationProvider`](upsource:///platform/platform-api/src/com/intellij/xml/breadcrumbs/BreadcrumbsPresentationProvider.java) |
| [com.intellij.bundledColorScheme](https://jb.gg/ipe?extensions=com.intellij.bundledColorScheme) | `n/a` |
| [com.intellij.bundledInspectionProfile](https://jb.gg/ipe?extensions=com.intellij.bundledInspectionProfile) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.bundledKeymap](https://jb.gg/ipe?extensions=com.intellij.bundledKeymap) | `n/a` |
| [com.intellij.bundledQuickListsProvider](https://jb.gg/ipe?extensions=com.intellij.bundledQuickListsProvider) | [`BundledQuickListsProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/BundledQuickListsProvider.java) |
| [com.intellij.cachedValuesFactory](https://jb.gg/ipe?extensions=com.intellij.cachedValuesFactory) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`CachedValuesFactory`](upsource:///platform/core-impl/src/com/intellij/util/CachedValuesFactory.java) |
| [com.intellij.cachesInvalidator](https://jb.gg/ipe?extensions=com.intellij.cachesInvalidator) | [`CachesInvalidator`](upsource:///platform/ide-core/src/com/intellij/ide/caches/CachesInvalidator.java) |
| [com.intellij.classpathStorageProvider](https://jb.gg/ipe?extensions=com.intellij.classpathStorageProvider) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`ClasspathStorageProvider`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/impl/storage/ClasspathStorageProvider.java) |
| [com.intellij.codeInsight.folding.collapseBlockHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.folding.collapseBlockHandler) | [`CollapseBlockHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/folding/CollapseBlockHandler.java) |
| [com.intellij.codeInsight.linkHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.linkHandler) | [`TooltipLinkHandler`](upsource:///platform/platform-api/src/com/intellij/codeInsight/highlighting/TooltipLinkHandler.java) |
| [com.intellij.codeInsight.template.postfixTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.template.postfixTemplateProvider) | [`PostfixTemplateProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplateProvider.java) |
| [com.intellij.colorAndFontOptionsImportHandler](https://jb.gg/ipe?extensions=com.intellij.colorAndFontOptionsImportHandler) ![Non-Dynamic][non-dynamic] | [`ImportHandler`](upsource:///platform/platform-impl/src/com/intellij/application/options/colors/ImportHandler.java) |
| [com.intellij.colorPickerListenerFactory](https://jb.gg/ipe?extensions=com.intellij.colorPickerListenerFactory) | [`ColorPickerListenerFactory`](upsource:///platform/platform-impl/src/com/intellij/ui/ColorPickerListenerFactory.java) |
| [com.intellij.config.codeVisionGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.codeVisionGroupSettingProvider) | [`CodeVisionGroupSettingProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/codeVision/settings/CodeVisionGroupSettingProvider.kt) |
| [com.intellij.config.inlayGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlayGroupSettingProvider) | [`InlayGroupSettingProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayGroupSettingProvider.kt) |
| [com.intellij.config.inlaySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlaySettingsProvider) | [`InlaySettingsProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlaySettingsProvider.kt) |
| [com.intellij.configurablesModificator](https://jb.gg/ipe?extensions=com.intellij.configurablesModificator) ![Experimental API][experimental] ![Internal API][internal] | [`ConfigurablesPatcher`](upsource:///platform/platform-impl/src/com/intellij/ide/actions/ConfigurablesPatcher.java) |
| [com.intellij.contentTabActionProvider](https://jb.gg/ipe?extensions=com.intellij.contentTabActionProvider) ![Non-Dynamic][non-dynamic] | [`ContentTabActionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/content/tabActions/ContentTabAction.kt) |
| [com.intellij.credentialStore](https://jb.gg/ipe?extensions=com.intellij.credentialStore) ![Non-Dynamic][non-dynamic] | [`CredentialStoreFactory`](upsource:///platform/credential-store/src/CredentialStoreFactory.java) |
| [com.intellij.customFileDropHandler](https://jb.gg/ipe?extensions=com.intellij.customFileDropHandler) ![Project-Level][project-level] | [`CustomFileDropHandler`](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/CustomFileDropHandler.java) |
| [com.intellij.customPluginRepoContributor](https://jb.gg/ipe?extensions=com.intellij.customPluginRepoContributor) | [`CustomPluginRepoContributor`](upsource:///platform/platform-impl/src/com/intellij/ide/plugins/CustomPluginRepoContributor.java) |
| [com.intellij.customizableActionGroupProvider](https://jb.gg/ipe?extensions=com.intellij.customizableActionGroupProvider) | [`CustomizableActionGroupProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/ui/customization/CustomizableActionGroupProvider.java) |
| [com.intellij.cutElementMarker](https://jb.gg/ipe?extensions=com.intellij.cutElementMarker) | [`CutElementMarker`](upsource:///platform/platform-api/src/com/intellij/openapi/ide/CutElementMarker.java) |
| [com.intellij.dataValidators](https://jb.gg/ipe?extensions=com.intellij.dataValidators) | [`DataValidators`](upsource:///platform/ide-core-impl/src/com/intellij/ide/impl/DataValidators.java) |
| [com.intellij.dateTimeFormatter](https://jb.gg/ipe?extensions=com.intellij.dateTimeFormatter) | `n/a` |
| [com.intellij.defaultProjectTypeProvider](https://jb.gg/ipe?extensions=com.intellij.defaultProjectTypeProvider) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.dependencyCollector](https://jb.gg/ipe?extensions=com.intellij.dependencyCollector) | [`DependencyCollector`](upsource:///platform/platform-impl/src/com/intellij/ide/plugins/DependencyCollector.kt) |
| [com.intellij.dependencySupport](https://jb.gg/ipe?extensions=com.intellij.dependencySupport) | `n/a` |
| [com.intellij.deuteranopiaSupport](https://jb.gg/ipe?extensions=com.intellij.deuteranopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](upsource:///platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.diff.DiffExtension](https://jb.gg/ipe?extensions=com.intellij.diff.DiffExtension) | [`DiffExtension`](upsource:///platform/diff-api/src/com/intellij/diff/DiffExtension.java) |
| [com.intellij.diff.DiffTool](https://jb.gg/ipe?extensions=com.intellij.diff.DiffTool) | [`DiffTool`](upsource:///platform/diff-api/src/com/intellij/diff/DiffTool.java) |
| [com.intellij.diff.actions.ShowDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.editor.diffRequestProcessorEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.diff.editor.diffRequestProcessorEditorCustomizer) | [`DiffRequestProcessorEditorCustomizer`](upsource:///platform/diff-impl/src/com/intellij/diff/editor/DiffRequestProcessorEditorCustomizer.kt) |
| [com.intellij.diff.impl.DiffToolSubstitutor](https://jb.gg/ipe?extensions=com.intellij.diff.impl.DiffToolSubstitutor) | [`DiffToolSubstitutor`](upsource:///platform/diff-impl/src/com/intellij/diff/impl/DiffToolSubstitutor.java) |
| [com.intellij.diff.lang.DiffIgnoredRangeProvider](https://jb.gg/ipe?extensions=com.intellij.diff.lang.DiffIgnoredRangeProvider) | [`DiffIgnoredRangeProvider`](upsource:///platform/diff-impl/src/com/intellij/diff/lang/DiffIgnoredRangeProvider.java) |
| [com.intellij.diff.merge.MergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.MergeTool) | [`MergeTool`](upsource:///platform/diff-api/src/com/intellij/diff/merge/MergeTool.java) |
| [com.intellij.diff.merge.external.AutomaticExternalMergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.external.AutomaticExternalMergeTool) | [`AutomaticExternalMergeTool`](upsource:///platform/diff-api/src/com/intellij/diff/merge/external/AutomaticExternalMergeTool.java) |
| [com.intellij.diff.tools.combined.diffBlockFactory](https://jb.gg/ipe?extensions=com.intellij.diff.tools.combined.diffBlockFactory) | [`CombinedDiffBlockFactory`](upsource:///platform/diff-impl/src/com/intellij/diff/tools/combined/CombinedDiffBlocks.kt) |
| [com.intellij.directoryProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectConfigurator) | [`DirectoryProjectConfigurator`](upsource:///platform/platform-impl/src/com/intellij/platform/DirectoryProjectConfigurator.java) |
| [com.intellij.directoryProjectGenerator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectGenerator) | [`DirectoryProjectGenerator`](upsource:///platform/platform-impl/src/com/intellij/platform/DirectoryProjectGenerator.java) |
| [com.intellij.documentationActionProvider](https://jb.gg/ipe?extensions=com.intellij.documentationActionProvider) | [`DocumentationActionProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) |
| [com.intellij.dynamicActionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.dynamicActionConfigurationCustomizer) | [`DynamicActionConfigurationCustomizer`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/DynamicActionConfigurationCustomizer.java) |
| [com.intellij.editor.injectedFileChangesHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.editor.injectedFileChangesHandlerProvider) | [`InjectedFileChangesHandlerProvider`](upsource:///platform/editor-ui-api/src/com/intellij/injected/editor/InjectedFileChangesHandlerProvider.java) |
| [com.intellij.editor.linePainter](https://jb.gg/ipe?extensions=com.intellij.editor.linePainter) | [`EditorLinePainter`](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/EditorLinePainter.java) |
| [com.intellij.editorActionHandler](https://jb.gg/ipe?extensions=com.intellij.editorActionHandler) | [`EditorActionHandler`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) |
| [com.intellij.editorFactoryMouseListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseListener) | [`EditorMouseListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseListener.java) |
| [com.intellij.editorFactoryMouseMotionListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseMotionListener) | [`EditorMouseMotionListener`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseMotionListener.java) |
| [com.intellij.editorFileSwapper](https://jb.gg/ipe?extensions=com.intellij.editorFileSwapper) | [`EditorFileSwapper`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorFileSwapper.java) |
| [com.intellij.editorFloatingToolbarProvider](https://jb.gg/ipe?extensions=com.intellij.editorFloatingToolbarProvider) | [`FloatingToolbarProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/toolbar/floating/FloatingToolbarProvider.kt) |
| [com.intellij.editorHighlighterProvider](https://jb.gg/ipe?extensions=com.intellij.editorHighlighterProvider) | [`EditorHighlighterProvider`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/EditorHighlighterProvider.java) |
| [com.intellij.editorNavigation](https://jb.gg/ipe?extensions=com.intellij.editorNavigation) | [`EditorNavigationDelegate`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/EditorNavigationDelegate.java) |
| [com.intellij.editorNotificationProvider](https://jb.gg/ipe?extensions=com.intellij.editorNotificationProvider) ![Project-Level][project-level] | [`EditorNotificationProvider`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| [com.intellij.editorTabColorProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabColorProvider) | [`EditorTabColorProvider`](upsource:///platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java) |
| [com.intellij.editorTabTitleProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabTitleProvider) | [`EditorTabTitleProvider`](upsource:///platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.java) |
| [com.intellij.editorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.editorTypedHandler) ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.errorHandler](https://jb.gg/ipe?extensions=com.intellij.errorHandler) | [`ErrorReportSubmitter`](upsource:///platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) |
| [com.intellij.eventLogCategory](https://jb.gg/ipe?extensions=com.intellij.eventLogCategory) | [`EventLogCategory`](upsource:///platform/ide-core/src/com/intellij/notification/EventLogCategory.java) |
| [com.intellij.executionActionSuppressor](https://jb.gg/ipe?extensions=com.intellij.executionActionSuppressor) ![Experimental API][experimental] ![Internal API][internal] | [`ExecutionActionSuppressor`](upsource:///platform/execution-impl/src/com/intellij/execution/ExecutionActionSuppressor.java) |
| [com.intellij.experimentalFeature](https://jb.gg/ipe?extensions=com.intellij.experimentalFeature) | `n/a` |
| [com.intellij.exportable](https://jb.gg/ipe?extensions=com.intellij.exportable) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.externalComponentSource](https://jb.gg/ipe?extensions=com.intellij.externalComponentSource) | [`ExternalComponentSource`](upsource:///platform/platform-impl/src/com/intellij/ide/externalComponents/ExternalComponentSource.java) |
| [com.intellij.featureStatisticsBundle](https://jb.gg/ipe?extensions=com.intellij.featureStatisticsBundle) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.feedbackDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.feedbackDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`FeedbackDescriptionProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/FeedbackDescriptionProvider.kt) |
| [com.intellij.fileBreadcrumbsCollector](https://jb.gg/ipe?extensions=com.intellij.fileBreadcrumbsCollector) ![Project-Level][project-level] | [`FileBreadcrumbsCollector`](upsource:///platform/platform-api/src/com/intellij/codeInsight/breadcrumbs/FileBreadcrumbsCollector.java) |
| [com.intellij.fileDocumentManagerListener](https://jb.gg/ipe?extensions=com.intellij.fileDocumentManagerListener) | [`FileDocumentManagerListener`](upsource:///platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [com.intellij.fileDocumentSynchronizationVetoer](https://jb.gg/ipe?extensions=com.intellij.fileDocumentSynchronizationVetoer) ![Non-Dynamic][non-dynamic] | [`FileDocumentSynchronizationVetoer`](upsource:///platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentSynchronizationVetoer.java) |
| [com.intellij.fileEditorProvider](https://jb.gg/ipe?extensions=com.intellij.fileEditorProvider) | [`FileEditorProvider`](upsource:///platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java) |
| [com.intellij.fileEditorProviderSuppressor](https://jb.gg/ipe?extensions=com.intellij.fileEditorProviderSuppressor) ![Internal API][internal] | [`FileEditorProviderSuppressor`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/FileEditorProviderSuppressor.java) |
| [com.intellij.fileEncodingProvider](https://jb.gg/ipe?extensions=com.intellij.fileEncodingProvider) | [`FileEncodingProvider`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/encoding/FileEncodingProvider.java) |
| [com.intellij.fileIconPatcher](https://jb.gg/ipe?extensions=com.intellij.fileIconPatcher) | [`FileIconPatcher`](upsource:///platform/core-api/src/com/intellij/ide/FileIconPatcher.java) |
| [com.intellij.fileIconProvider](https://jb.gg/ipe?extensions=com.intellij.fileIconProvider) | [`FileIconProvider`](upsource:///platform/core-api/src/com/intellij/ide/FileIconProvider.java) |
| [com.intellij.fileType](https://jb.gg/ipe?extensions=com.intellij.fileType) | [`FileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) |
| [com.intellij.fileTypeFactory](https://jb.gg/ipe?extensions=com.intellij.fileTypeFactory) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`FileTypeFactory`](upsource:///platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java) |
| [com.intellij.fileTypeOverrider](https://jb.gg/ipe?extensions=com.intellij.fileTypeOverrider) ![Experimental API][experimental] | [`FileTypeOverrider`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileTypes/impl/FileTypeOverrider.java) |
| [com.intellij.fileTypeRegistrar](https://jb.gg/ipe?extensions=com.intellij.fileTypeRegistrar) ![Non-Dynamic][non-dynamic] | [`FileTypeRegistrar`](upsource:///platform/ide-core/src/com/intellij/ide/highlighter/FileTypeRegistrar.java) |
| [com.intellij.fileTypeUsageSchemaDescriptor](https://jb.gg/ipe?extensions=com.intellij.fileTypeUsageSchemaDescriptor) | [`FileTypeUsageSchemaDescriptor`](upsource:///platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/fileTypes/FileTypeUsageSchemaDescriptor.java) |
| [com.intellij.fragments.dsl.builder.extender](https://jb.gg/ipe?extensions=com.intellij.fragments.dsl.builder.extender) ![Experimental API][experimental] ![Internal API][internal] | [`FragmentsDslBuilderExtender`](upsource:///platform/platform-api/src/com/intellij/execution/ui/utils/FragmentsDslBuilder.kt) |
| [com.intellij.fsRootDataLoader](https://jb.gg/ipe?extensions=com.intellij.fsRootDataLoader) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`FsRootDataLoader`](upsource:///platform/vfs-impl/src/com/intellij/openapi/vfs/newvfs/persistent/FsRootDataLoader.kt) |
| [com.intellij.generalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalOptionsProvider) ![Non-Dynamic][non-dynamic] | [`SearchableConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/SearchableConfigurable.java) |
| [com.intellij.generalTroubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.generalTroubleInfoCollector) | [`GeneralTroubleInfoCollector`](upsource:///platform/platform-impl/src/com/intellij/troubleshooting/GeneralTroubleInfoCollector.java) |
| [com.intellij.getDataRule](https://jb.gg/ipe?extensions=com.intellij.getDataRule) | [`GetDataRule`](upsource:///platform/ide-core-impl/src/com/intellij/ide/impl/dataRules/GetDataRule.java) |
| [com.intellij.gitRepositoryInitializer](https://jb.gg/ipe?extensions=com.intellij.gitRepositoryInitializer) | [`GitRepositoryInitializer`](upsource:///platform/platform-api/src/com/intellij/openapi/GitRepositoryInitializer.java) |
| [com.intellij.gitSilentFileAdder](https://jb.gg/ipe?extensions=com.intellij.gitSilentFileAdder) ![Internal API][internal] ![Project-Level][project-level] | [`GitSilentFileAdderProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/GitSilentFileAdderProvider.java) |
| [com.intellij.groupConfigurable](https://jb.gg/ipe?extensions=com.intellij.groupConfigurable) | `n/a` |
| [com.intellij.gutterMarkPreprocessor](https://jb.gg/ipe?extensions=com.intellij.gutterMarkPreprocessor) ![Non-Dynamic][non-dynamic] | [`GutterMarkPreprocessor`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/GutterMarkPreprocessor.java) |
| [com.intellij.handleTypeFactory](https://jb.gg/ipe?extensions=com.intellij.handleTypeFactory) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`HandleTypeFactory`](upsource:///platform/platform-impl/src/com/intellij/openapi/vcs/readOnlyHandler/HandleTypeFactory.java) |
| [com.intellij.http.fileEditorActionProvider](https://jb.gg/ipe?extensions=com.intellij.http.fileEditorActionProvider) ![Non-Dynamic][non-dynamic] | [`RemoteFileEditorActionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/RemoteFileEditorActionProvider.java) |
| [com.intellij.http.localFileFinder](https://jb.gg/ipe?extensions=com.intellij.http.localFileFinder) | [`LocalFileFinder`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/LocalFileFinder.java) |
| [com.intellij.iconDescriptionBundle](https://jb.gg/ipe?extensions=com.intellij.iconDescriptionBundle) | `n/a` |
| [com.intellij.iconLayerProvider](https://jb.gg/ipe?extensions=com.intellij.iconLayerProvider) ![Non-Dynamic][non-dynamic] | [`IconLayerProvider`](upsource:///platform/core-api/src/com/intellij/ide/IconLayerProvider.java) |
| [com.intellij.ideEventQueueDispatcher](https://jb.gg/ipe?extensions=com.intellij.ideEventQueueDispatcher) | [`EventDispatcher`](upsource:///platform/platform-impl/src/com/intellij/ide/IdeEventQueue.java) |
| [com.intellij.ideRootPaneNorth](https://jb.gg/ipe?extensions=com.intellij.ideRootPaneNorth) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`IdeRootPaneNorthExtension`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/IdeRootPaneNorthExtension.java) |
| [com.intellij.inspectionPopupLevelChangePolicy](https://jb.gg/ipe?extensions=com.intellij.inspectionPopupLevelChangePolicy) ![Internal API][internal] | [`InspectionPopupLevelChangePolicy`](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/impl/InspectionPopupLevelChangePolicy.java) |
| [com.intellij.interactiveCourseFactory](https://jb.gg/ipe?extensions=com.intellij.interactiveCourseFactory) | [`InteractiveCourseFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/InteractiveCourseFactory.kt) |
| [com.intellij.itemPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.itemPresentationProvider) | [`ItemPresentationProvider`](upsource:///platform/core-api/src/com/intellij/navigation/ItemPresentationProvider.java) |
| [com.intellij.iw.actionProvider](https://jb.gg/ipe?extensions=com.intellij.iw.actionProvider) | [`InspectionWidgetActionProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/markup/InspectionWidgetActionProvider.kt) |
| [com.intellij.jbProtocolCommand](https://jb.gg/ipe?extensions=com.intellij.jbProtocolCommand) | [`JBProtocolCommand`](upsource:///platform/platform-impl/src/com/intellij/openapi/application/JBProtocolCommand.java) |
| [com.intellij.jbProtocolRevisionResolver](https://jb.gg/ipe?extensions=com.intellij.jbProtocolRevisionResolver) | [`JBProtocolRevisionResolver`](upsource:///platform/lang-impl/src/com/intellij/navigation/JBProtocolRevisionResolver.java) |
| [com.intellij.jcef.appRequiredArgumentsProvider](https://jb.gg/ipe?extensions=com.intellij.jcef.appRequiredArgumentsProvider) ![Non-Dynamic][non-dynamic] | [`JBCefAppRequiredArgumentsProvider`](upsource:///platform/platform-api/src/com/intellij/ui/jcef/JBCefAppRequiredArgumentsProvider.kt) |
| [com.intellij.jdkDownloader.jdkInstallerListener](https://jb.gg/ipe?extensions=com.intellij.jdkDownloader.jdkInstallerListener) | [`JdkInstallerListener`](upsource:///platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkInstaller.kt) |
| [com.intellij.jdkUpdateCheckContributor](https://jb.gg/ipe?extensions=com.intellij.jdkUpdateCheckContributor) | [`JdkUpdateCheckContributor`](upsource:///platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkUpdater.kt) |
| [com.intellij.jps.plugin](https://jb.gg/ipe?extensions=com.intellij.jps.plugin) | `n/a` |
| [com.intellij.keymapExtension](https://jb.gg/ipe?extensions=com.intellij.keymapExtension) | [`KeymapExtension`](upsource:///platform/platform-api/src/com/intellij/openapi/keymap/KeymapExtension.java) |
| [com.intellij.lafProvider](https://jb.gg/ipe?extensions=com.intellij.lafProvider) ![Non-Dynamic][non-dynamic] | [`LafProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/ui/LafProvider.java) |
| [com.intellij.lang.syntaxHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighterFactory) | [`SyntaxHighlighterFactory`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java) |
| [com.intellij.library.toolWindow](https://jb.gg/ipe?extensions=com.intellij.library.toolWindow) | [`ToolWindowFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.lightEditTabAttributesProvider](https://jb.gg/ipe?extensions=com.intellij.lightEditTabAttributesProvider) ![Experimental API][experimental] | [`LightEditTabAttributesProvider`](upsource:///platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditTabAttributesProvider.java) |
| [com.intellij.newProjectWizard.language](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.language) | [`LanguageNewProjectWizard`](upsource:///platform/platform-impl/src/com/intellij/ide/wizard/LanguageNewProjectWizard.kt) |
| [com.intellij.nonProjectFileWritingAccessExtension](https://jb.gg/ipe?extensions=com.intellij.nonProjectFileWritingAccessExtension) ![Project-Level][project-level] | [`NonProjectFileWritingAccessExtension`](upsource:///platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/NonProjectFileWritingAccessExtension.java) |
| [com.intellij.notification.group](https://jb.gg/ipe?extensions=com.intellij.notification.group) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.notification.parentGroup](https://jb.gg/ipe?extensions=com.intellij.notification.parentGroup) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.notificationGroup](https://jb.gg/ipe?extensions=com.intellij.notificationGroup) | `n/a` |
| [com.intellij.notificationRemindLaterHandler](https://jb.gg/ipe?extensions=com.intellij.notificationRemindLaterHandler) | [`NotificationRemindLaterHandler`](upsource:///platform/ide-core/src/com/intellij/notification/NotificationRemindLaterHandler.kt) |
| [com.intellij.obsoleteStorage](https://jb.gg/ipe?extensions=com.intellij.obsoleteStorage) | `n/a` |
| [com.intellij.pathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.pathMacroContributor) | [`PathMacroContributor`](upsource:///platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| [com.intellij.pathMacroExpandableProtocol](https://jb.gg/ipe?extensions=com.intellij.pathMacroExpandableProtocol) | `n/a` |
| [com.intellij.pathMacroFilter](https://jb.gg/ipe?extensions=com.intellij.pathMacroFilter) | [`PathMacroFilter`](upsource:///jps/model-serialization/src/com/intellij/openapi/application/PathMacroFilter.java) |
| [com.intellij.persistentFsConnectionListener](https://jb.gg/ipe?extensions=com.intellij.persistentFsConnectionListener) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`PersistentFsConnectionListener`](upsource:///platform/vfs-impl/src/com/intellij/openapi/vfs/newvfs/persistent/PersistentFsConnectionListener.java) |
| [com.intellij.pluginReplacement](https://jb.gg/ipe?extensions=com.intellij.pluginReplacement) | [`PluginReplacement`](upsource:///platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) |
| [com.intellij.pluginRepositoryAuthProvider](https://jb.gg/ipe?extensions=com.intellij.pluginRepositoryAuthProvider) | [`PluginRepositoryAuthProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthProvider.java) |
| [com.intellij.preloadingActivity](https://jb.gg/ipe?extensions=com.intellij.preloadingActivity) | [`PreloadingActivity`](upsource:///platform/ide-core/src/com/intellij/openapi/application/PreloadingActivity.java) |
| [com.intellij.productivityFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.productivityFeaturesProvider) ![Non-Dynamic][non-dynamic] | [`ProductivityFeaturesProvider`](upsource:///platform/platform-api/src/com/intellij/featureStatistics/ProductivityFeaturesProvider.java) |
| [com.intellij.projectAttachProcessor](https://jb.gg/ipe?extensions=com.intellij.projectAttachProcessor) | [`ProjectAttachProcessor`](upsource:///platform/ide-core/src/com/intellij/projectImport/ProjectAttachProcessor.java) |
| [com.intellij.projectCloseHandler](https://jb.gg/ipe?extensions=com.intellij.projectCloseHandler) ![Non-Dynamic][non-dynamic] | [`ProjectCloseHandler`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseHandler.java) |
| [com.intellij.projectConfigurable](https://jb.gg/ipe?extensions=com.intellij.projectConfigurable) ![Project-Level][project-level] | [`Configurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.projectNameProvider](https://jb.gg/ipe?extensions=com.intellij.projectNameProvider) ![Non-Dynamic][non-dynamic] | [`ProjectNameProvider`](upsource:///platform/ide-core-impl/src/com/intellij/openapi/project/ex/ProjectNameProvider.java) |
| [com.intellij.projectOpenProcessor](https://jb.gg/ipe?extensions=com.intellij.projectOpenProcessor) | [`ProjectOpenProcessor`](upsource:///platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.java) |
| [com.intellij.projectOriginInfoProvider](https://jb.gg/ipe?extensions=com.intellij.projectOriginInfoProvider) ![Internal API][internal] | [`ProjectOriginInfoProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/impl/ProjectOriginInfoProvider.kt) |
| [com.intellij.projectServiceContainerCustomizer](https://jb.gg/ipe?extensions=com.intellij.projectServiceContainerCustomizer) ![Internal API][internal] | [`ProjectServiceContainerCustomizer`](upsource:///platform/platform-impl/src/com/intellij/openapi/project/impl/projectLoader.kt) |
| [com.intellij.projectServiceContainerInitializedListener](https://jb.gg/ipe?extensions=com.intellij.projectServiceContainerInitializedListener) ![Internal API][internal] | [`ProjectServiceContainerInitializedListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/project/impl/projectLoader.kt) |
| [com.intellij.projectSetProcessor](https://jb.gg/ipe?extensions=com.intellij.projectSetProcessor) ![Non-Dynamic][non-dynamic] | [`ProjectSetProcessor`](upsource:///platform/platform-api/src/com/intellij/projectImport/ProjectSetProcessor.java) |
| [com.intellij.projectStoreClassProvider](https://jb.gg/ipe?extensions=com.intellij.projectStoreClassProvider) ![Non-Dynamic][non-dynamic] | [`ProjectStoreFactory`](upsource:///platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectStoreFactory.java) |
| [com.intellij.projectTemplate](https://jb.gg/ipe?extensions=com.intellij.projectTemplate) | `n/a` |
| [com.intellij.projectTemplatesFactory](https://jb.gg/ipe?extensions=com.intellij.projectTemplatesFactory) | [`ProjectTemplatesFactory`](upsource:///platform/platform-impl/src/com/intellij/platform/ProjectTemplatesFactory.java) |
| [com.intellij.projectToolbarWidget](https://jb.gg/ipe?extensions=com.intellij.projectToolbarWidget) ![Experimental API][experimental] ![Internal API][internal] | [`MainToolbarProjectWidgetFactory`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/headertoolbar/MainToolbarProjectWidgetFactory.kt) |
| [com.intellij.projectUndoProvider](https://jb.gg/ipe?extensions=com.intellij.projectUndoProvider) ![Project-Level][project-level] | [`UndoProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.protanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.protanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](upsource:///platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.protocolHandler](https://jb.gg/ipe?extensions=com.intellij.protocolHandler) | [`ProtocolHandler`](upsource:///platform/platform-impl/src/com/intellij/ide/ProtocolHandler.java) |
| [com.intellij.rawEditorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.rawEditorTypedHandler) ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.recoveryAction](https://jb.gg/ipe?extensions=com.intellij.recoveryAction) ![Internal API][internal] | [`RecoveryAction`](upsource:///platform/platform-impl/src/com/intellij/ide/actions/cache/Saul.kt) |
| [com.intellij.registryKey](https://jb.gg/ipe?extensions=com.intellij.registryKey) | `n/a` |
| [com.intellij.remote.credentialsLanguageContribution](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsLanguageContribution) | [`CredentialsLanguageContribution`](upsource:///platform/platform-impl/src/com/intellij/remote/ext/CredentialsLanguageContribution.java) |
| [com.intellij.remote.credentialsType](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsType) | [`CredentialsType`](upsource:///platform/remote-core/src/remote/CredentialsType.java) |
| [com.intellij.remote.pathMappingProvider](https://jb.gg/ipe?extensions=com.intellij.remote.pathMappingProvider) | [`PathMappingProvider`](upsource:///platform/platform-impl/src/com/intellij/remote/PathMappingProvider.java) |
| [com.intellij.schemeExporter](https://jb.gg/ipe?extensions=com.intellij.schemeExporter) | [`SchemeExporter`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SchemeExporter.java) |
| [com.intellij.schemeImporter](https://jb.gg/ipe?extensions=com.intellij.schemeImporter) | [`SchemeImporter`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SchemeImporter.java) |
| [com.intellij.search.optionContributor](https://jb.gg/ipe?extensions=com.intellij.search.optionContributor) | [`SearchableOptionContributor`](upsource:///platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionContributor.java) |
| [com.intellij.search.projectOptionsTopHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.projectOptionsTopHitProvider) | [`ProjectLevelProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/ui/OptionsSearchTopHitProvider.java) |
| [com.intellij.search.topHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.topHitProvider) | [`SearchTopHitProvider`](upsource:///platform/platform-api/src/com/intellij/ide/SearchTopHitProvider.java) |
| [com.intellij.search.traverseUiHelper](https://jb.gg/ipe?extensions=com.intellij.search.traverseUiHelper) ![Non-Dynamic][non-dynamic] | [`TraverseUIHelper`](upsource:///platform/platform-impl/src/com/intellij/ide/ui/search/TraverseUIHelper.java) |
| [com.intellij.selectInTarget](https://jb.gg/ipe?extensions=com.intellij.selectInTarget) ![Project-Level][project-level] | [`SelectInTarget`](upsource:///platform/platform-api/src/com/intellij/ide/SelectInTarget.java) |
| [com.intellij.semanticRootProvider](https://jb.gg/ipe?extensions=com.intellij.semanticRootProvider) ![Non-Dynamic][non-dynamic] | [`RootSemanticAddressProvider`](upsource:///platform/vfs-impl/src/com/intellij/openapi/vfs/newvfs/persistent/RootSemanticAddressProvider.java) |
| [com.intellij.settingsEntryPointActionProvider](https://jb.gg/ipe?extensions=com.intellij.settingsEntryPointActionProvider) | [`ActionProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| [com.intellij.smartSelectProvider](https://jb.gg/ipe?extensions=com.intellij.smartSelectProvider) | [`SmartSelectProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/ide/SmartSelectProvider.java) |
| [com.intellij.sshCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.sshCredentialProvider) | [`SshCredentialProvider`](upsource:///platform/platform-impl/src/com/intellij/remote/SshCredentialProvider.java) |
| [com.intellij.startPagePromoter](https://jb.gg/ipe?extensions=com.intellij.startPagePromoter) ![Internal API][internal] | [`StartPagePromoter`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/StartPagePromoter.kt) |
| [com.intellij.statistic.eventLog.eventLogEndpointSubstitutor](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.eventLogEndpointSubstitutor) ![Deprecated][deprecated] ![Internal API][internal] | [`EventLogEndpointSubstitutor`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogEndpointSubstitutor.java) |
| [com.intellij.statistic.eventLog.eventLoggerProvider](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.eventLoggerProvider) ![Non-Dynamic][non-dynamic] | [`StatisticsEventLoggerProvider`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/StatisticsEventLogger.kt) |
| [com.intellij.statistic.eventLog.externalEventLogSettings](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.externalEventLogSettings) ![Internal API][internal] | [`ExternalEventLogSettings`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogSettings.java) |
| [com.intellij.statistic.eventLog.fusStateEventTracker](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.fusStateEventTracker) ![Non-Dynamic][non-dynamic] | [`FeatureUsageStateEventTracker`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/fus/FeatureUsageStateEventTracker.kt) |
| [com.intellij.statistics.actionCustomPlaceAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.actionCustomPlaceAllowlist) | `n/a` |
| [com.intellij.statistics.applicationUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.applicationUsagesCollector) ![Internal API][internal] | [`ApplicationUsagesCollector`](upsource:///platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ApplicationUsagesCollector.java) |
| [com.intellij.statistics.balloonIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.balloonIdsHolder) ![Internal API][internal] | [`BalloonIdsHolder`](upsource:///platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/ui/BalloonIdsHolder.java) |
| [com.intellij.statistics.collectorExtension](https://jb.gg/ipe?extensions=com.intellij.statistics.collectorExtension) ![Non-Dynamic][non-dynamic] | [`FeatureUsageCollectorExtension`](upsource:///platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsageCollectorExtension.java) |
| [com.intellij.statistics.counterUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.counterUsagesCollector) ![Internal API][internal] | [`FeatureUsagesCollector`](upsource:///platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsagesCollector.java) |
| [com.intellij.statistics.gotItTooltipAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.gotItTooltipAllowlist) | `n/a` |
| [com.intellij.statistics.notificationIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.notificationIdsHolder) ![Internal API][internal] | [`NotificationIdsHolder`](upsource:///platform/platform-impl/src/com/intellij/notification/impl/NotificationIdsHolder.java) |
| [com.intellij.statistics.projectUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.projectUsagesCollector) ![Internal API][internal] | [`ProjectUsagesCollector`](upsource:///platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ProjectUsagesCollector.java) |
| [com.intellij.statistics.validation.customValidationRule](https://jb.gg/ipe?extensions=com.intellij.statistics.validation.customValidationRule) | [`CustomValidationRule`](upsource:///platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRule.java) |
| [com.intellij.statusBarWidgetFactory](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetFactory) | [`StatusBarWidgetFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java) |
| [com.intellij.statusBarWidgetProvider](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetProvider) ![Deprecated][deprecated] | [`StatusBarWidgetProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetProvider.java) |
| [com.intellij.streamProviderFactory](https://jb.gg/ipe?extensions=com.intellij.streamProviderFactory) ![Internal API][internal] ![Project-Level][project-level] | [`StreamProviderFactory`](upsource:///platform/projectModel-impl/src/com/intellij/configurationStore/StreamProviderFactory.kt) |
| [com.intellij.stripTrailingSpacesFilterFactory](https://jb.gg/ipe?extensions=com.intellij.stripTrailingSpacesFilterFactory) | [`StripTrailingSpacesFilterFactory`](upsource:///platform/core-api/src/com/intellij/openapi/editor/StripTrailingSpacesFilterFactory.java) |
| [com.intellij.systemProperty](https://jb.gg/ipe?extensions=com.intellij.systemProperty) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.testStatusListener](https://jb.gg/ipe?extensions=com.intellij.testStatusListener) ![Non-Dynamic][non-dynamic] | [`TestStatusListener`](upsource:///platform/testRunner/src/com/intellij/execution/testframework/TestStatusListener.java) |
| [com.intellij.textEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.textEditorCustomizer) | [`TextEditorCustomizer`](upsource:///platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/text/TextEditorCustomizer.java) |
| [com.intellij.themeMetadataProvider](https://jb.gg/ipe?extensions=com.intellij.themeMetadataProvider) | `n/a` |
| [com.intellij.themeProvider](https://jb.gg/ipe?extensions=com.intellij.themeProvider) | `n/a` |
| [com.intellij.tipAndTrick](https://jb.gg/ipe?extensions=com.intellij.tipAndTrick) | `n/a` |
| [com.intellij.tipAndTrickPromotionFactory](https://jb.gg/ipe?extensions=com.intellij.tipAndTrickPromotionFactory) | [`TipAndTrickPromotionFactory`](upsource:///platform/platform-impl/src/com/intellij/ide/util/TipAndTrickPromotionFactory.kt) |
| [com.intellij.titleInfoProvider](https://jb.gg/ipe?extensions=com.intellij.titleInfoProvider) ![Non-Dynamic][non-dynamic] | [`TitleInfoProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [com.intellij.toolWindow](https://jb.gg/ipe?extensions=com.intellij.toolWindow) | [`ToolWindowFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [com.intellij.toolWindowAllowlist](https://jb.gg/ipe?extensions=com.intellij.toolWindowAllowlist) | `n/a` |
| [com.intellij.toolWindowContentExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowContentExtractor) ![Experimental API][experimental] | [`ToolWindowContentExtractor`](upsource:///platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowContentExtractor.java) |
| [com.intellij.toolWindowExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractor) ![Experimental API][experimental] | [`ToolWindowViewModelExtractor`](upsource:///platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelExtractor.java) |
| [com.intellij.toolWindowExtractorMode](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractorMode) | `n/a` |
| [com.intellij.trailingSpacesOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.trailingSpacesOptionsProvider) | [`TrailingSpacesOptionsProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/fileEditor/TrailingSpacesOptionsProvider.java) |
| [com.intellij.tree.CustomLanguageASTComparator](https://jb.gg/ipe?extensions=com.intellij.tree.CustomLanguageASTComparator) | [`CustomLanguageASTComparator`](upsource:///platform/core-api/src/com/intellij/psi/tree/CustomLanguageASTComparator.java) |
| [com.intellij.tritanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.tritanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](upsource:///platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.troubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.troubleInfoCollector) | [`TroubleInfoCollector`](upsource:///platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |
| [com.intellij.trustedHostsConfigurablePanelProvider](https://jb.gg/ipe?extensions=com.intellij.trustedHostsConfigurablePanelProvider) ![Internal API][internal] | [`TrustedHostsConfigurablePanelProvider`](upsource:///platform/platform-impl/src/com/intellij/ide/impl/TrustedHostsConfigurable.kt) |
| [com.intellij.ui.optionEditorProvider](https://jb.gg/ipe?extensions=com.intellij.ui.optionEditorProvider) ![Experimental API][experimental] | [`OptionEditorProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/OptionEditorProvider.java) |
| [com.intellij.ui.suitableFontProvider](https://jb.gg/ipe?extensions=com.intellij.ui.suitableFontProvider) | [`SuitableFontProvider`](upsource:///platform/platform-api/src/com/intellij/ui/SuitableFontProvider.java) |
| [com.intellij.undoProvider](https://jb.gg/ipe?extensions=com.intellij.undoProvider) | [`UndoProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.undoReportHandler](https://jb.gg/ipe?extensions=com.intellij.undoReportHandler) ![Internal API][internal] | [`UndoReportHandler`](upsource:///platform/platform-impl/src/com/intellij/openapi/command/impl/UndoReportHandler.java) |
| [com.intellij.unknownSdkContributor](https://jb.gg/ipe?extensions=com.intellij.unknownSdkContributor) | [`UnknownSdkContributor`](upsource:///platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/UnknownSdkCollector.kt) |
| [com.intellij.unknownSdkResolver](https://jb.gg/ipe?extensions=com.intellij.unknownSdkResolver) | [`UnknownSdkResolver`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/UnknownSdkResolver.java) |
| [com.intellij.updateSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.updateSettingsProvider) | [`UpdateSettingsProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsProvider.kt) |
| [com.intellij.utf8BomOptionProvider](https://jb.gg/ipe?extensions=com.intellij.utf8BomOptionProvider) | [`Utf8BomOptionProvider`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/encoding/Utf8BomOptionProvider.java) |
| [com.intellij.vfs.local.pluggableContentLoader](https://jb.gg/ipe?extensions=com.intellij.vfs.local.pluggableContentLoader) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`PluggableLocalFileSystemContentLoader`](upsource:///platform/vfs-impl/src/com/intellij/openapi/vfs/impl/local/PluggableLocalFileSystemContentLoader.java) |
| [com.intellij.vfs.local.pluggableFileWatcher](https://jb.gg/ipe?extensions=com.intellij.vfs.local.pluggableFileWatcher) ![Non-Dynamic][non-dynamic] | [`PluggableFileWatcher`](upsource:///platform/ide-core-impl/src/com/intellij/openapi/vfs/local/PluggableFileWatcher.java) |
| [com.intellij.webBrowserUrlProvider](https://jb.gg/ipe?extensions=com.intellij.webBrowserUrlProvider) | [`WebBrowserUrlProvider`](upsource:///platform/platform-api/src/com/intellij/ide/browsers/WebBrowserUrlProvider.kt) |
| [com.intellij.webHelpProvider](https://jb.gg/ipe?extensions=com.intellij.webHelpProvider) | [`WebHelpProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) |
| [com.intellij.welcome.projectDetector](https://jb.gg/ipe?extensions=com.intellij.welcome.projectDetector) | [`ProjectDetector`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/ProjectDetector.java) |
| [com.intellij.welcomeFrameProvider](https://jb.gg/ipe?extensions=com.intellij.welcomeFrameProvider) | [`WelcomeFrameProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/WelcomeFrameProvider.java) |
| [com.intellij.welcomeScreen](https://jb.gg/ipe?extensions=com.intellij.welcomeScreen) ![Non-Dynamic][non-dynamic] | [`WelcomeScreenProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenProvider.java) |
| [com.intellij.welcomeScreenCustomization](https://jb.gg/ipe?extensions=com.intellij.welcomeScreenCustomization) ![Experimental API][experimental] | [`WelcomeScreenCustomization`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenCustomization.java) |
| [com.intellij.welcomeTabFactory](https://jb.gg/ipe?extensions=com.intellij.welcomeTabFactory) | [`WelcomeTabFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/WelcomeTabFactory.java) |
| [org.jetbrains.javaScriptDebuggerStarter](https://jb.gg/ipe?extensions=org.jetbrains.javaScriptDebuggerStarter) | [`JavaScriptDebuggerStarter`](upsource:///platform/platform-impl/src/com/intellij/ide/browsers/JavaScriptDebuggerStarter.java) |
| [org.jetbrains.urlOpener](https://jb.gg/ipe?extensions=org.jetbrains.urlOpener) | [`UrlOpener`](upsource:///platform/platform-api/src/com/intellij/ide/browsers/UrlOpener.java) |

### PlatformLangComponents.xml

[`PlatformLangComponents.xml`](upsource:///platform/platform-resources/src/META-INF/PlatformLangComponents.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.registerToolWindowTaskProvider](https://jb.gg/ipe?extensions=com.intellij.registerToolWindowTaskProvider) ![Non-Dynamic][non-dynamic] ![Internal API][internal] | [`RegisterToolWindowTaskProvider`](upsource:///platform/platform-impl/src/com/intellij/toolWindow/toolwindow.kt) |

### ProjectModel.xml

[`ProjectModel.xml`](upsource:///platform/projectModel-api/resources/META-INF/ProjectModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customLibraryTable](https://jb.gg/ipe?extensions=com.intellij.customLibraryTable) | [`CustomLibraryTableDescription`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries/CustomLibraryTableDescription.java) |
| [com.intellij.filePropertyPusher](https://jb.gg/ipe?extensions=com.intellij.filePropertyPusher) | [`FilePropertyPusher`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/impl/FilePropertyPusher.java) |
| [com.intellij.moduleExtension](https://jb.gg/ipe?extensions=com.intellij.moduleExtension) | [`ModuleExtension`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleExtension.java) |
| [com.intellij.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.orderEnumerationHandlerFactory) | [`Factory`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/OrderEnumerationHandler.java) |
| [com.intellij.orderRootType](https://jb.gg/ipe?extensions=com.intellij.orderRootType) ![Non-Dynamic][non-dynamic] | [`OrderRootType`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/OrderRootType.java) |
| [com.intellij.primaryModuleManager](https://jb.gg/ipe?extensions=com.intellij.primaryModuleManager) | [`PrimaryModuleManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/module/PrimaryModuleManager.java) |
| [com.intellij.projectFileScanner](https://jb.gg/ipe?extensions=com.intellij.projectFileScanner) ![Experimental API][experimental] | [`IndexableFileScanner`](upsource:///platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFileScanner.java) |

### ProjectModelImpl.xml

[`ProjectModelImpl.xml`](upsource:///platform/projectModel-impl/resources/META-INF/ProjectModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.additionalLibraryRootsProvider](https://jb.gg/ipe?extensions=com.intellij.additionalLibraryRootsProvider) | [`AdditionalLibraryRootsProvider`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java) |
| [com.intellij.directoryIndexExcludePolicy](https://jb.gg/ipe?extensions=com.intellij.directoryIndexExcludePolicy) ![Project-Level][project-level] | [`DirectoryIndexExcludePolicy`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/impl/DirectoryIndexExcludePolicy.java) |
| [com.intellij.projectExtension](https://jb.gg/ipe?extensions=com.intellij.projectExtension) ![Internal API][internal] ![Project-Level][project-level] | [`ProjectExtension`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/ProjectExtension.java) |
| [com.intellij.projectPathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.projectPathMacroContributor) ![Internal API][internal] | [`ProjectWidePathMacroContributor`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/components/impl/ProjectWidePathMacroContributor.java) |
| [com.intellij.workspaceModel.moduleExtensionBridgeFactory](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.moduleExtensionBridgeFactory) | [`ModuleExtensionBridgeFactory`](upsource:///platform/projectModel-impl/src/com/intellij/workspaceModel/ide/legacyBridge/ModuleExtensionBridgeFactory.kt) |

### RefactoringExtensionPoints.xml

[`RefactoringExtensionPoints.xml`](upsource:///platform/refactoring/resources/META-INF/RefactoringExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.automaticRenamerFactory](https://jb.gg/ipe?extensions=com.intellij.automaticRenamerFactory) | [`AutomaticRenamerFactory`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/naming/AutomaticRenamerFactory.java) |
| [com.intellij.findInProjectSearchEngine](https://jb.gg/ipe?extensions=com.intellij.findInProjectSearchEngine) ![Experimental API][experimental] | [`FindInProjectSearchEngine`](upsource:///platform/refactoring/src/com/intellij/find/FindInProjectSearchEngine.java) |
| [com.intellij.inlineActionHandler](https://jb.gg/ipe?extensions=com.intellij.inlineActionHandler) | [`InlineActionHandler`](upsource:///platform/refactoring/src/com/intellij/lang/refactoring/InlineActionHandler.java) |
| [com.intellij.lang.namesValidator](https://jb.gg/ipe?extensions=com.intellij.lang.namesValidator) | [`NamesValidator`](upsource:///platform/analysis-api/src/com/intellij/lang/refactoring/NamesValidator.java) |
| [com.intellij.lang.refactoringSupport](https://jb.gg/ipe?extensions=com.intellij.lang.refactoringSupport) | [`RefactoringSupportProvider`](upsource:///platform/refactoring/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java) |
| [com.intellij.nameSuggestionProvider](https://jb.gg/ipe?extensions=com.intellij.nameSuggestionProvider) | [`NameSuggestionProvider`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) |
| [com.intellij.qualifiedNameProvider](https://jb.gg/ipe?extensions=com.intellij.qualifiedNameProvider) | [`QualifiedNameProvider`](upsource:///platform/refactoring/src/com/intellij/ide/actions/QualifiedNameProvider.java) |
| [com.intellij.refactoring.elementListenerProvider](https://jb.gg/ipe?extensions=com.intellij.refactoring.elementListenerProvider) ![Project-Level][project-level] | [`RefactoringElementListenerProvider`](upsource:///platform/analysis-api/src/com/intellij/refactoring/listeners/RefactoringElementListenerProvider.java) |
| [com.intellij.refactoring.helper](https://jb.gg/ipe?extensions=com.intellij.refactoring.helper) | [`RefactoringHelper`](upsource:///platform/refactoring/src/com/intellij/refactoring/RefactoringHelper.java) |
| [com.intellij.refactoring.inlineHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.inlineHandler) | [`InlineHandler`](upsource:///platform/refactoring/src/com/intellij/lang/refactoring/InlineHandler.java) |
| [com.intellij.rename.inplace.resolveSnapshotProvider](https://jb.gg/ipe?extensions=com.intellij.rename.inplace.resolveSnapshotProvider) | [`ResolveSnapshotProvider`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/ResolveSnapshotProvider.java) |
| [com.intellij.renameHandler](https://jb.gg/ipe?extensions=com.intellij.renameHandler) | [`RenameHandler`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) |
| [com.intellij.renameInputValidator](https://jb.gg/ipe?extensions=com.intellij.renameInputValidator) | [`RenameInputValidator`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameInputValidator.java) |
| [com.intellij.renamePsiElementProcessor](https://jb.gg/ipe?extensions=com.intellij.renamePsiElementProcessor) | [`RenamePsiElementProcessorBase`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenamePsiElementProcessorBase.java) |
| [com.intellij.renameRefactoringDialogProvider](https://jb.gg/ipe?extensions=com.intellij.renameRefactoringDialogProvider) | [`RenameRefactoringDialogProvider`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenameRefactoringDialogProvider.java) |
| [com.intellij.renamerFactory](https://jb.gg/ipe?extensions=com.intellij.renamerFactory) ![Experimental API][experimental] | [`RenamerFactory`](upsource:///platform/refactoring/src/com/intellij/refactoring/rename/RenamerFactory.java) |
| [com.intellij.testSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.testSourcesFilter) | [`TestSourcesFilter`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java) |
| [com.intellij.updateAddedFileProcessor](https://jb.gg/ipe?extensions=com.intellij.updateAddedFileProcessor) | [`UpdateAddedFileProcessor`](upsource:///platform/core-impl/src/com/intellij/psi/impl/file/UpdateAddedFileProcessor.java) |
| [com.intellij.vetoRenameCondition](https://jb.gg/ipe?extensions=com.intellij.vetoRenameCondition) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.virtualFileQualifiedNameProvider](https://jb.gg/ipe?extensions=com.intellij.virtualFileQualifiedNameProvider) | [`VirtualFileQualifiedNameProvider`](upsource:///platform/refactoring/src/com/intellij/ide/actions/VirtualFileQualifiedNameProvider.java) |

### RefactoringLangExtensionPoints.xml

[`RefactoringLangExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/RefactoringLangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.changeSignatureDetector](https://jb.gg/ipe?extensions=com.intellij.changeSignatureDetector) | [`LanguageChangeSignatureDetector`](upsource:///platform/lang-impl/src/com/intellij/refactoring/changeSignature/inplace/LanguageChangeSignatureDetector.java) |
| [com.intellij.lang.refactoringSupport.classMembersRefactoringSupport](https://jb.gg/ipe?extensions=com.intellij.lang.refactoringSupport.classMembersRefactoringSupport) | [`ClassMembersRefactoringSupport`](upsource:///platform/lang-api/src/com/intellij/refactoring/classMembers/ClassMembersRefactoringSupport.java) |
| [com.intellij.moveFileHandler](https://jb.gg/ipe?extensions=com.intellij.moveFileHandler) | [`MoveFileHandler`](upsource:///platform/lang-impl/src/com/intellij/refactoring/move/moveFilesOrDirectories/MoveFileHandler.java) |
| [com.intellij.refactoring.changeSignatureUsageProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.changeSignatureUsageProcessor) | [`ChangeSignatureUsageProcessor`](upsource:///platform/refactoring/src/com/intellij/refactoring/changeSignature/ChangeSignatureUsageProcessor.java) |
| [com.intellij.refactoring.copyHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.copyHandler) | [`CopyHandlerDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/copy/CopyHandlerDelegate.java) |
| [com.intellij.refactoring.extractIncludeHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.extractIncludeHandler) | [`RefactoringActionHandler`](upsource:///platform/refactoring/src/com/intellij/refactoring/RefactoringActionHandler.java) |
| [com.intellij.refactoring.introduceParameterObject](https://jb.gg/ipe?extensions=com.intellij.refactoring.introduceParameterObject) | [`IntroduceParameterObjectDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/introduceParameterObject/IntroduceParameterObjectDelegate.java) |
| [com.intellij.refactoring.invertBoolean](https://jb.gg/ipe?extensions=com.intellij.refactoring.invertBoolean) | [`InvertBooleanDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/invertBoolean/InvertBooleanDelegate.java) |
| [com.intellij.refactoring.moveDirectoryWithClassesHelper](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveDirectoryWithClassesHelper) | [`MoveDirectoryWithClassesHelper`](upsource:///platform/lang-impl/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveDirectoryWithClassesHelper.java) |
| [com.intellij.refactoring.moveHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveHandler) | [`MoveHandlerDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/move/MoveHandlerDelegate.java) |
| [com.intellij.refactoring.pushDown](https://jb.gg/ipe?extensions=com.intellij.refactoring.pushDown) | [`PushDownDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/memberPushDown/PushDownDelegate.java) |
| [com.intellij.refactoring.safeDeleteProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.safeDeleteProcessor) | [`SafeDeleteProcessorDelegate`](upsource:///platform/lang-impl/src/com/intellij/refactoring/safeDelete/SafeDeleteProcessorDelegate.java) |
| [com.intellij.rename.symbolRenameTargetFactory](https://jb.gg/ipe?extensions=com.intellij.rename.symbolRenameTargetFactory) | [`SymbolRenameTargetFactory`](upsource:///platform/lang-impl/src/com/intellij/refactoring/rename/symbol/SymbolRenameTargetFactory.java) |
| [com.intellij.renameFileActionProvider](https://jb.gg/ipe?extensions=com.intellij.renameFileActionProvider) | [`RenameFileActionProvider`](upsource:///platform/lang-impl/src/com/intellij/refactoring/actions/RenameFileActionProvider.java) |
| [com.intellij.suggestedRefactoringSupport](https://jb.gg/ipe?extensions=com.intellij.suggestedRefactoringSupport) | [`SuggestedRefactoringSupport`](upsource:///platform/lang-api/src/com/intellij/refactoring/suggested/SuggestedRefactoringSupport.kt) |

### RegExpPlugin.xml

[`RegExpPlugin.xml`](upsource:///RegExpSupport/resources/META-INF/RegExpPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.regExpCapabilitiesProvider](https://jb.gg/ipe?extensions=com.intellij.regExpCapabilitiesProvider) | [`RegExpCapabilitiesProvider`](upsource:///RegExpSupport/src/org/intellij/lang/regexp/RegExpCapabilitiesProvider.java) |
| [com.intellij.regExpLanguageHost](https://jb.gg/ipe?extensions=com.intellij.regExpLanguageHost) | [`RegExpLanguageHost`](upsource:///RegExpSupport/src/org/intellij/lang/regexp/RegExpLanguageHost.java) |
| [com.intellij.regExpMatcherProvider](https://jb.gg/ipe?extensions=com.intellij.regExpMatcherProvider) | [`RegExpMatcherProvider`](upsource:///RegExpSupport/src/org/intellij/lang/regexp/RegExpMatcherProvider.java) |
| [com.intellij.regExpModifierProvider](https://jb.gg/ipe?extensions=com.intellij.regExpModifierProvider) | [`RegExpModifierProvider`](upsource:///RegExpSupport/src/org/intellij/lang/regexp/RegExpModifierProvider.java) |

### smRunner.xml

[`smRunner.xml`](upsource:///platform/smRunner/resources/META-INF/smRunner.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.importTestOutput](https://jb.gg/ipe?extensions=com.intellij.importTestOutput) | [`ImportTestOutputExtension`](upsource:///platform/smRunner/src/com/intellij/execution/testframework/sm/runner/history/ImportTestOutputExtension.java) |

### SpellCheckerPlugin.xml

[`SpellCheckerPlugin.xml`](upsource:///spellchecker/src/META-INF/SpellCheckerPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spellchecker.bundledDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.bundledDictionaryProvider) | [`BundledDictionaryProvider`](upsource:///spellchecker/src/com/intellij/spellchecker/BundledDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.customDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.customDictionaryProvider) | [`CustomDictionaryProvider`](upsource:///spellchecker/src/com/intellij/spellchecker/dictionary/CustomDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.runtimeDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.runtimeDictionaryProvider) | [`RuntimeDictionaryProvider`](upsource:///spellchecker/src/com/intellij/spellchecker/dictionary/RuntimeDictionaryProvider.java) |
| [com.intellij.spellchecker.support](https://jb.gg/ipe?extensions=com.intellij.spellchecker.support) | [`SpellcheckingStrategy`](upsource:///spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java) |

### statistics-devkit.xml

[`statistics-devkit.xml`](upsource:///platform/statistics/devkit/resources/META-INF/statistics-devkit.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.statisticsLogGroupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.statisticsLogGroupActionsProvider) ![Internal API][internal] | [`StatisticsLogGroupActionsProvider`](upsource:///platform/statistics/devkit/src/com/intellij/internal/statistic/devkit/toolwindow/StatisticsLogGroupActionsProvider.kt) |

### structuralsearch.xml

[`structuralsearch.xml`](upsource:///platform/structuralsearch/source/META-INF/structuralsearch.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.structuralsearch.filter](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.filter) | [`FilterAction`](upsource:///platform/structuralsearch/source/com/intellij/structuralsearch/plugin/ui/filters/FilterAction.java) |
| [com.intellij.structuralsearch.filterProvider](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.filterProvider) | [`FilterProvider`](upsource:///platform/structuralsearch/source/com/intellij/structuralsearch/plugin/ui/filters/FilterProvider.java) |
| [com.intellij.structuralsearch.profile](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.profile) | [`StructuralSearchProfile`](upsource:///platform/structuralsearch/source/com/intellij/structuralsearch/StructuralSearchProfile.java) |
| [com.intellij.structuralsearch.specialXmlTagExtractor](https://jb.gg/ipe?extensions=com.intellij.structuralsearch.specialXmlTagExtractor) | [`SpecialElementExtractor`](upsource:///platform/structuralsearch/source/com/intellij/structuralsearch/SpecialElementExtractor.java) |

### tasks.xml

[`tasks.xml`](upsource:///platform/tasks-platform-impl/resources/META-INF/tasks.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tasks.contextProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.contextProvider) | [`WorkingContextProvider`](upsource:///platform/tasks-platform-api/src/com/intellij/tasks/context/WorkingContextProvider.java) |

### UsageViewActions.xml

[`UsageViewActions.xml`](upsource:///platform/usageView/resources/idea/UsageViewActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.usages.usageReferenceClassProvider](https://jb.gg/ipe?extensions=com.intellij.usages.usageReferenceClassProvider) ![Non-Dynamic][non-dynamic] | [`UsageReferenceClassProvider`](upsource:///platform/usageView/src/com/intellij/usages/impl/UsageReferenceClassProvider.kt) |

### vcs-log.xml

[`vcs-log.xml`](upsource:///platform/vcs-log/impl/src/META-INF/vcs-log.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customVcsLogUiFactoryProvider](https://jb.gg/ipe?extensions=com.intellij.customVcsLogUiFactoryProvider) ![Experimental API][experimental] ![Project-Level][project-level] | [`CustomVcsLogUiFactoryProvider`](upsource:///platform/vcs-log/impl/src/com/intellij/vcs/log/impl/CustomVcsLogUiFactoryProvider.java) |
| [com.intellij.logHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.logHighlighterFactory) | [`VcsLogHighlighterFactory`](upsource:///platform/vcs-log/impl/src/com/intellij/vcs/log/ui/highlighters/VcsLogHighlighterFactory.java) |
| [com.intellij.logProvider](https://jb.gg/ipe?extensions=com.intellij.logProvider) ![Project-Level][project-level] | [`VcsLogProvider`](upsource:///platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogProvider.java) |
| [com.intellij.vcsLogCommitStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcsLogCommitStatusProvider) ![Experimental API][experimental] | [`VcsCommitExternalStatusProvider`](upsource:///platform/vcs-log/impl/src/com/intellij/vcs/log/ui/frame/VcsCommitExternalStatusProvider.kt) |
| [com.intellij.vcsLogCustomColumn](https://jb.gg/ipe?extensions=com.intellij.vcsLogCustomColumn) | [`VcsLogCustomColumn`](upsource:///platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |

### VcsExtensionPoints.xml

[`VcsExtensionPoints.xml`](upsource:///platform/vcs-impl/resources/META-INF/VcsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.changesGroupingPolicy](https://jb.gg/ipe?extensions=com.intellij.changesGroupingPolicy) ![Non-Dynamic][non-dynamic] | [`ChangesGroupingPolicyFactory`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesGroupingPolicyFactory.java) |
| [com.intellij.changesViewContent](https://jb.gg/ipe?extensions=com.intellij.changesViewContent) ![Project-Level][project-level] | [`ChangesViewContentProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentProvider.java) |
| [com.intellij.checkinHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.checkinHandlerFactory) | [`CheckinHandlerFactory`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/checkin/CheckinHandlerFactory.java) |
| [com.intellij.checkoutCompletedListener](https://jb.gg/ipe?extensions=com.intellij.checkoutCompletedListener) | [`CheckoutListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| [com.intellij.checkoutListener](https://jb.gg/ipe?extensions=com.intellij.checkoutListener) | [`CheckoutListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| [com.intellij.checkoutProvider](https://jb.gg/ipe?extensions=com.intellij.checkoutProvider) | [`CheckoutProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/CheckoutProvider.java) |
| [com.intellij.editChangelistSupport](https://jb.gg/ipe?extensions=com.intellij.editChangelistSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`EditChangelistSupport`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/EditChangelistSupport.java) |
| [com.intellij.generalVcsSettingsExtension](https://jb.gg/ipe?extensions=com.intellij.generalVcsSettingsExtension) ![Project-Level][project-level] | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.ignoredFileContentProvider](https://jb.gg/ipe?extensions=com.intellij.ignoredFileContentProvider) ![Project-Level][project-level] | [`IgnoredFileContentProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileContentProvider.java) |
| [com.intellij.ignoredFileProvider](https://jb.gg/ipe?extensions=com.intellij.ignoredFileProvider) ![Experimental API][experimental] | [`IgnoredFileProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileProvider.java) |
| [com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider) | [`Provider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/actions/AnnotateToggleAction.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider) | [`ChangeDiffRequestProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffRequestProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider) | [`ChangeDiffViewerWrapperProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffViewerWrapperProvider.java) |
| [com.intellij.openapi.vcs.changes.ui.filePathIconProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.ui.filePathIconProvider) | [`FilePathIconProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/FilePathIconProvider.java) |
| [com.intellij.openapi.vcs.changes.vcsPreservingExecutor](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.vcsPreservingExecutor) | [`VcsPreservingExecutor`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsPreservingExecutor.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider) | [`LocalLineStatusTrackerProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| [com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension) | [`VcsCloneDialogExtension`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/ui/cloneDialog/VcsCloneDialogExtension.kt) |
| [com.intellij.patch.extension](https://jb.gg/ipe?extensions=com.intellij.patch.extension) | [`PatchEP`](upsource:///platform/vcs-api/vcs-api-core/src/com/intellij/openapi/diff/impl/patch/PatchEP.java) |
| [com.intellij.unresolvedMergeCheckProvider](https://jb.gg/ipe?extensions=com.intellij.unresolvedMergeCheckProvider) | [`UnresolvedMergeCheckProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/checkin/UnresolvedMergeCheckProvider.java) |
| [com.intellij.vcs](https://jb.gg/ipe?extensions=com.intellij.vcs) | [`AbstractVcs`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/AbstractVcs.java) |
| [com.intellij.vcs.actions.ScheduleForAdditionActionExtension](https://jb.gg/ipe?extensions=com.intellij.vcs.actions.ScheduleForAdditionActionExtension) | [`ScheduleForAdditionActionExtension`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/ScheduleForAdditionActionExtension.kt) |
| [com.intellij.vcs.baseContentProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.baseContentProvider) ![Project-Level][project-level] | [`VcsBaseContentProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProvider.java) |
| [com.intellij.vcs.branchStateProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.branchStateProvider) ![Project-Level][project-level] | [`BranchStateProvider`](upsource:///platform/vcs-api/src/com/intellij/vcs/branch/BranchStateProvider.java) |
| [com.intellij.vcs.changeListChangeAssigner](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListChangeAssigner) ![Experimental API][experimental] ![Project-Level][project-level] | [`ChangeListChangeAssigner`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangeListChangeAssigner.java) |
| [com.intellij.vcs.changeListDecorator](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListDecorator) ![Project-Level][project-level] | [`ChangeListDecorator`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListDecorator.java) |
| [com.intellij.vcs.changes.changesViewModifier](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewModifier) ![Project-Level][project-level] | [`ChangesViewModifier`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [com.intellij.vcs.changes.changesViewNodeAction](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewNodeAction) ![Experimental API][experimental] ![Project-Level][project-level] | [`ChangesViewNodeAction`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewNodeAction.java) |
| [com.intellij.vcs.changes.localCommitExecutor](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.localCommitExecutor) ![Project-Level][project-level] | [`LocalCommitExecutor`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalCommitExecutor.java) |
| [com.intellij.vcs.checkoutProcessor](https://jb.gg/ipe?extensions=com.intellij.vcs.checkoutProcessor) | [`VcsCheckoutProcessor`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsCheckoutProcessor.java) |
| [com.intellij.vcs.codeVisionLanguageContext](https://jb.gg/ipe?extensions=com.intellij.vcs.codeVisionLanguageContext) | [`VcsCodeVisionLanguageContext`](upsource:///platform/vcs-impl/src/com/intellij/codeInsight/hints/VcsCodeVisionLanguageContext.kt) |
| [com.intellij.vcs.commitMessageProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.commitMessageProvider) | [`CommitMessageProvider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/CommitMessageProvider.java) |
| [com.intellij.vcs.consoleFolding](https://jb.gg/ipe?extensions=com.intellij.vcs.consoleFolding) | [`VcsConsoleFolding`](upsource:///platform/vcs-impl/src/com/intellij/vcs/console/VcsConsoleView.kt) |
| [com.intellij.vcs.delayedCommitMessageProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.delayedCommitMessageProvider) ![Experimental API][experimental] | [`DelayedCommitMessageProvider`](upsource:///platform/vcs-impl/src/com/intellij/vcs/commit/DelayedCommitMessageProvider.kt) |
| [com.intellij.vcs.envCustomizer](https://jb.gg/ipe?extensions=com.intellij.vcs.envCustomizer) | [`VcsEnvCustomizer`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsEnvCustomizer.java) |
| [com.intellij.vcs.fileStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.fileStatusProvider) ![Project-Level][project-level] | [`FileStatusProvider`](upsource:///platform/platform-impl/src/com/intellij/openapi/vcs/impl/FileStatusProvider.java) |
| [com.intellij.vcs.ignoredFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.ignoredFilesHolder) ![Project-Level][project-level] | [`Provider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcs.taskHandler](https://jb.gg/ipe?extensions=com.intellij.vcs.taskHandler) ![Project-Level][project-level] | [`VcsTaskHandler`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsTaskHandler.java) |
| [com.intellij.vcs.unversionedFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.unversionedFilesHolder) ![Project-Level][project-level] | [`Provider`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcsAnnotationGutterActionProvider](https://jb.gg/ipe?extensions=com.intellij.vcsAnnotationGutterActionProvider) | [`AnnotationGutterActionProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterActionProvider.java) |
| [com.intellij.vcsAnnotationGutterColumnProvider](https://jb.gg/ipe?extensions=com.intellij.vcsAnnotationGutterColumnProvider) | [`AnnotationGutterColumnProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterColumnProvider.java) |
| [com.intellij.vcsAwareCheckoutListener](https://jb.gg/ipe?extensions=com.intellij.vcsAwareCheckoutListener) | [`VcsAwareCheckoutListener`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/checkout/VcsAwareCheckoutListener.java) |
| [com.intellij.vcsBulkMovesOnlyChangesFilter](https://jb.gg/ipe?extensions=com.intellij.vcsBulkMovesOnlyChangesFilter) | [`BulkMovesOnlyChangesFilter`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/browser/BulkMovesOnlyChangesFilter.java) |
| [com.intellij.vcsChangesViewRefresher](https://jb.gg/ipe?extensions=com.intellij.vcsChangesViewRefresher) ![Project-Level][project-level] | [`ChangesViewRefresher`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangesViewRefresher.java) |
| [com.intellij.vcsCheckinHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.vcsCheckinHandlerFactory) | [`VcsCheckinHandlerFactory`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/checkin/VcsCheckinHandlerFactory.kt) |
| [com.intellij.vcsConfigurableProvider](https://jb.gg/ipe?extensions=com.intellij.vcsConfigurableProvider) | [`VcsConfigurableProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsConfigurableProvider.java) |
| [com.intellij.vcsIgnoreChecker](https://jb.gg/ipe?extensions=com.intellij.vcsIgnoreChecker) | [`VcsIgnoreChecker`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsIgnoreChecker.java) |
| [com.intellij.vcsPopupProvider](https://jb.gg/ipe?extensions=com.intellij.vcsPopupProvider) | [`VcsQuickListContentProvider`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/actions/VcsQuickListContentProvider.java) |
| [com.intellij.vcsRepositoryInitializer](https://jb.gg/ipe?extensions=com.intellij.vcsRepositoryInitializer) | [`VcsRepositoryInitializer`](upsource:///platform/vcs-api/src/com/intellij/vcs/VcsRepositoryInitializer.java) |
| [com.intellij.vcsRootChecker](https://jb.gg/ipe?extensions=com.intellij.vcsRootChecker) | [`VcsRootChecker`](upsource:///platform/vcs-api/src/com/intellij/openapi/vcs/VcsRootChecker.java) |
| [com.intellij.vcsSelectionProvider](https://jb.gg/ipe?extensions=com.intellij.vcsSelectionProvider) | [`VcsSelectionProvider`](upsource:///platform/vcs-api/src/com/intellij/vcsUtil/VcsSelectionProvider.java) |
| [com.intellij.vcsStartupActivity](https://jb.gg/ipe?extensions=com.intellij.vcsStartupActivity) ![Non-Dynamic][non-dynamic] | [`VcsStartupActivity`](upsource:///platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsStartupActivity.java) |

### WorkspaceModelExtensions.xml

[`WorkspaceModelExtensions.xml`](upsource:///platform/workspaceModel/jps/src/META-INF/WorkspaceModelExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.workspaceModel.cache.version](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.cache.version) | [`WorkspaceModelCacheVersion`](upsource:///platform/projectModel-impl/src/com/intellij/workspaceModel/ide/WorkspaceModelCacheVersion.kt) |
| [com.intellij.workspaceModel.customModuleComponentSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleComponentSerializer) ![Internal API][internal] | [`CustomModuleComponentSerializer`](upsource:///platform/workspaceModel/jps/src/com/intellij/workspaceModel/ide/impl/jps/serialization/CustomModuleComponentSerializer.kt) |
| [com.intellij.workspaceModel.customModuleRootsSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleRootsSerializer) ![Internal API][internal] | [`CustomModuleRootsSerializer`](upsource:///platform/workspaceModel/jps/src/com/intellij/workspaceModel/ide/impl/jps/serialization/CustomModuleRootsSerializer.kt) |
| [com.intellij.workspaceModel.preUpdateHandler](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.preUpdateHandler) ![Internal API][internal] | [`WorkspaceModelPreUpdateHandler`](upsource:///platform/projectModel-impl/src/com/intellij/workspaceModel/ide/WorkspaceModelPreUpdateHandler.kt) |

### xdebugger.xml

[`xdebugger.xml`](upsource:///platform/xdebugger-impl/resources/META-INF/xdebugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.xdebugger.attachDebuggerProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachDebuggerProvider) | [`XAttachDebuggerProvider`](upsource:///platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachDebuggerProvider.java) |
| [com.intellij.xdebugger.attachHostProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachHostProvider) | [`XAttachHostProvider`](upsource:///platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachHostProvider.java) |
| [com.intellij.xdebugger.breakpointGroupingRule](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointGroupingRule) | [`XBreakpointGroupingRule`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/ui/XBreakpointGroupingRule.java) |
| [com.intellij.xdebugger.breakpointType](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointType) | [`XBreakpointType`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointType.java) |
| [com.intellij.xdebugger.configurableProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.configurableProvider) | [`DebuggerConfigurableProvider`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/settings/DebuggerConfigurableProvider.java) |
| [com.intellij.xdebugger.debuggerSupport](https://jb.gg/ipe?extensions=com.intellij.xdebugger.debuggerSupport) ![Deprecated][deprecated] | [`DebuggerSupport`](upsource:///platform/xdebugger-impl/src/com/intellij/xdebugger/impl/DebuggerSupport.java) |
| [com.intellij.xdebugger.localAttachDebuggerProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.localAttachDebuggerProvider) ![Deprecated][deprecated] | [`XLocalAttachDebuggerProvider`](upsource:///platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XLocalAttachDebuggerProvider.java) |
| [com.intellij.xdebugger.settings](https://jb.gg/ipe?extensions=com.intellij.xdebugger.settings) | [`XDebuggerSettings`](upsource:///platform/xdebugger-api/src/com/intellij/xdebugger/settings/XDebuggerSettings.java) |

### xml.xml

[`xml.xml`](upsource:///xml/impl/resources/META-INF/xml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.html.compatibleLanguage](https://jb.gg/ipe?extensions=com.intellij.html.compatibleLanguage) ![Experimental API][experimental] | `n/a` |

### XmlPlugin.xml

[`XmlPlugin.xml`](upsource:///platform/platform-resources/src/META-INF/XmlPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.embeddedTokenHighlighter](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenHighlighter) | [`EmbeddedTokenHighlighter`](upsource:///xml/xml-psi-impl/src/com/intellij/ide/highlighter/EmbeddedTokenHighlighter.java) |
| [com.intellij.embeddedTokenTypesProvider](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenTypesProvider) | [`EmbeddedTokenTypesProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/lexer/EmbeddedTokenTypesProvider.java) |
| [com.intellij.html.attributeValueProvider](https://jb.gg/ipe?extensions=com.intellij.html.attributeValueProvider) | [`HtmlAttributeValueProvider`](upsource:///xml/impl/src/com/intellij/html/impl/providers/HtmlAttributeValueProvider.java) |
| [com.intellij.html.codestyle.panel](https://jb.gg/ipe?extensions=com.intellij.html.codestyle.panel) | [`HtmlCodeStylePanelExtension`](upsource:///xml/impl/src/com/intellij/application/options/HtmlCodeStylePanelExtension.java) |
| [com.intellij.html.embeddedContentSupport](https://jb.gg/ipe?extensions=com.intellij.html.embeddedContentSupport) | [`HtmlEmbeddedContentSupport`](upsource:///xml/xml-psi-impl/src/com/intellij/html/embedding/HtmlEmbeddedContentSupport.kt) |
| [com.intellij.html.htmlScriptInjectionBlocker](https://jb.gg/ipe?extensions=com.intellij.html.htmlScriptInjectionBlocker) | [`HtmlScriptInjectionBlocker`](upsource:///xml/impl/src/com/intellij/psi/impl/source/html/HtmlScriptInjectionBlocker.java) |
| [com.intellij.html.scriptContentProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptContentProvider) | [`HtmlScriptContentProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/lang/HtmlScriptContentProvider.java) |
| [com.intellij.html.scriptDocumentationProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptDocumentationProvider) | [`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.standardResource](https://jb.gg/ipe?extensions=com.intellij.standardResource) | `n/a` |
| [com.intellij.standardResourceProvider](https://jb.gg/ipe?extensions=com.intellij.standardResourceProvider) | [`StandardResourceProvider`](upsource:///xml/xml-psi-api/src/com/intellij/javaee/StandardResourceProvider.java) |
| [com.intellij.webSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.webSmartKeysConfigurable) | [`UnnamedConfigurable`](upsource:///platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.xml.attributeDescriptorsProvider](https://jb.gg/ipe?extensions=com.intellij.xml.attributeDescriptorsProvider) | [`XmlAttributeDescriptorsProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/xml/XmlAttributeDescriptorsProvider.java) |
| [com.intellij.xml.elementDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.elementDescriptorProvider) | [`XmlElementDescriptorProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlElementDescriptorProvider.java) |
| [com.intellij.xml.fileNSInfoProvider](https://jb.gg/ipe?extensions=com.intellij.xml.fileNSInfoProvider) | [`XmlFileNSInfoProvider`](upsource:///xml/xml-psi-api/src/com/intellij/psi/xml/XmlFileNSInfoProvider.java) |
| [com.intellij.xml.idContributor](https://jb.gg/ipe?extensions=com.intellij.xml.idContributor) | [`XmlIdContributor`](upsource:///xml/xml-psi-impl/src/com/intellij/xml/util/XmlIdContributor.java) |
| [com.intellij.xml.implicitIdRefProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitIdRefProvider) | [`ImplicitIdRefProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/ImplicitIdRefProvider.java) |
| [com.intellij.xml.implicitNamespaceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitNamespaceDescriptorProvider) | [`ImplicitNamespaceDescriptorProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/javaee/ImplicitNamespaceDescriptorProvider.java) |
| [com.intellij.xml.namedReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.xml.namedReferenceProvider) ![Experimental API][experimental] | [`PsiSymbolReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.xml.namespaceHelper](https://jb.gg/ipe?extensions=com.intellij.xml.namespaceHelper) | [`XmlNamespaceHelper`](upsource:///xml/xml-psi-impl/src/com/intellij/xml/XmlNamespaceHelper.java) |
| [com.intellij.xml.nsColorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.nsColorProvider) | [`XmlNSColorProvider`](upsource:///xml/xml-analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/XmlNSColorProvider.java) |
| [com.intellij.xml.psiPolicy](https://jb.gg/ipe?extensions=com.intellij.xml.psiPolicy) | [`XmlPsiPolicy`](upsource:///xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlPsiPolicy.java) |
| [com.intellij.xml.relatedToHtmlFilesContributor](https://jb.gg/ipe?extensions=com.intellij.xml.relatedToHtmlFilesContributor) | [`RelatedToHtmlFilesContributor`](upsource:///xml/impl/src/com/intellij/navigation/RelatedToHtmlFilesContributor.java) |
| [com.intellij.xml.schemaProvider](https://jb.gg/ipe?extensions=com.intellij.xml.schemaProvider) | [`XmlSchemaProvider`](upsource:///xml/xml-psi-api/src/com/intellij/xml/XmlSchemaProvider.java) |
| [com.intellij.xml.startTagEndToken](https://jb.gg/ipe?extensions=com.intellij.xml.startTagEndToken) | [`StartTagEndTokenProvider`](upsource:///xml/xml-psi-impl/src/com/intellij/psi/xml/StartTagEndTokenProvider.java) |
| [com.intellij.xml.tagNameProvider](https://jb.gg/ipe?extensions=com.intellij.xml.tagNameProvider) | [`XmlTagNameProvider`](upsource:///xml/impl/src/com/intellij/xml/XmlTagNameProvider.java) |
| [com.intellij.xml.undefinedElementFixProvider](https://jb.gg/ipe?extensions=com.intellij.xml.undefinedElementFixProvider) | [`XmlUndefinedElementFixProvider`](upsource:///xml/xml-analysis-impl/src/com/intellij/xml/XmlUndefinedElementFixProvider.java) |
| [com.intellij.xml.validateHandler](https://jb.gg/ipe?extensions=com.intellij.xml.validateHandler) | [`ValidateXmlHandler`](upsource:///xml/xml-psi-impl/src/com/intellij/xml/actions/validate/ValidateXmlHandler.java) |
| [com.intellij.xml.xmlExtension](https://jb.gg/ipe?extensions=com.intellij.xml.xmlExtension) | [`XmlExtension`](upsource:///xml/xml-psi-impl/src/com/intellij/xml/XmlExtension.java) |
| [com.intellij.xml.xmlSuppressionProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlSuppressionProvider) | [`XmlSuppressionProvider`](upsource:///xml/xml-psi-api/src/com/intellij/codeInspection/XmlSuppressionProvider.java) |
| [com.intellij.xml.xmlTagRuleProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTagRuleProvider) | [`XmlTagRuleProvider`](upsource:///xml/xml-analysis-api/src/com/intellij/xml/XmlTagRuleProvider.java) |
| [com.intellij.xml.xmlTypedHandlersAdditionalSupport](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTypedHandlersAdditionalSupport) | [`XmlTypedHandlersAdditionalSupport`](upsource:///xml/xml-psi-api/src/com/intellij/openapi/editor/XmlTypedHandlersAdditionalSupport.java) |
| [com.intellij.xml.zenCodingFilter](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingFilter) | [`ZenCodingFilter`](upsource:///xml/impl/src/com/intellij/codeInsight/template/emmet/filters/ZenCodingFilter.java) |
| [com.intellij.xml.zenCodingGenerator](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingGenerator) | [`ZenCodingGenerator`](upsource:///xml/impl/src/com/intellij/codeInsight/template/emmet/generators/ZenCodingGenerator.java) |
| [com.intellij.xmlStructureViewBuilderProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewBuilderProvider) | [`XmlStructureViewBuilderProvider`](upsource:///xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewBuilderProvider.java) |
| [com.intellij.xmlStructureViewElementProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewElementProvider) | [`XmlStructureViewElementProvider`](upsource:///xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewElementProvider.java) |



## IntelliJ Community Plugins


### IntelliJ Community Plugins - Listeners

| Topic | Listener |
|-------|----------|
| [ExternalAnnotationsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.ExternalAnnotationsListener)  | [`ExternalAnnotationsListener`](upsource:///java/java-psi-api/src/com/intellij/codeInsight/ExternalAnnotationsListener.java) |
| [BuildManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.BuildManagerListener)  | [`BuildManagerListener`](upsource:///java/compiler/impl/src/com/intellij/compiler/server/BuildManagerListener.java) |
| [CustomBuilderMessageHandler#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.CustomBuilderMessageHandler)  | [`CustomBuilderMessageHandler`](upsource:///java/compiler/impl/src/com/intellij/compiler/server/CustomBuilderMessageHandler.java) |
| [PortableCachesLoadListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.PortableCachesLoadListener)  ![Internal API][internal] ![Project-Level][project-level] | [`PortableCachesLoadListener`](upsource:///java/compiler/impl/src/com/intellij/compiler/server/PortableCachesLoadListener.java) |
| [DebuggerManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.impl.DebuggerManagerListener)  | [`DebuggerManagerListener`](upsource:///java/debugger/impl/src/com/intellij/debugger/impl/DebuggerManagerListener.java) |
| [GrazieStateLifecycleKt#topic](https://jb.gg/ipe/listeners?topics=com.intellij.grazie.ide.msg.GrazieStateLifecycle)  | [`GrazieStateLifecycle`](upsource:///plugins/grazie/src/main/kotlin/com/intellij/grazie/ide/msg/GrazieStateLifecycle.kt) |
| [AntExecutionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.ant.config.execution.AntExecutionListener)  | [`AntExecutionListener`](upsource:///plugins/ant/src/com/intellij/lang/ant/config/execution/AntExecutionListener.java) |
| [CompilerTopics#COMPILATION_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.CompilationStatusListener)  ![Project-Level][project-level] | [`CompilationStatusListener`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/CompilationStatusListener.java) |
| [ExcludedEntriesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.options.ExcludedEntriesListener)  | [`ExcludedEntriesListener`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/options/ExcludedEntriesListener.java) |
| [LanguageLevelProjectExtension#LANGUAGE_LEVEL_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.LanguageLevelProjectExtension.LanguageLevelChangeListener)  | [`LanguageLevelChangeListener`](upsource:///java/java-psi-api/src/com/intellij/openapi/roots/LanguageLevelProjectExtension.java) |
| [ConfigurationErrors#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ui.configuration.ConfigurationErrors)  | [`ConfigurationErrors`](upsource:///java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ConfigurationErrors.java) |
| [ArtifactManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.packaging.artifacts.ArtifactListener)  ![Project-Level][project-level] | [`ArtifactListener`](upsource:///java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactListener.java) |
| [SettingsSyncMainKt#SETTINGS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.settingsSync.SettingsChangeListener)  | [`SettingsChangeListener`](upsource:///plugins/settings-sync/src/com/intellij/settingsSync/SettingsChangeListener.kt) |
| [SvnVcs#ROOTS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.util.Consumer)  | [`Consumer`](upsource:///platform/util-rt/src/com/intellij/util/Consumer.java) |
| [PyPackageManager#PACKAGE_MANAGER_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.PyPackageManager.Listener)  | [`Listener`](upsource:///python/openapi/src/com/jetbrains/python/packaging/PyPackageManager.java) |
| [GitBranchIncomingOutgoingManager#GIT_INCOMING_OUTGOING_CHANGED](https://jb.gg/ipe/listeners?topics=git4idea.branch.GitBranchIncomingOutgoingManager.GitIncomingOutgoingListener)  | [`GitIncomingOutgoingListener`](upsource:///plugins/git4idea/src/git4idea/branch/GitBranchIncomingOutgoingManager.java) |
| [GitAuthenticationListener#GIT_AUTHENTICATION_SUCCESS](https://jb.gg/ipe/listeners?topics=git4idea.commands.GitAuthenticationListener)  | [`GitAuthenticationListener`](upsource:///plugins/git4idea/src/git4idea/commands/GitAuthenticationListener.java) |
| [GitExecutableManager#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.config.GitExecutableListener)  | [`GitExecutableListener`](upsource:///plugins/git4idea/src/git4idea/config/GitExecutableListener.java) |
| [GitCommitTemplateListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitCommitTemplateListener)  ![Project-Level][project-level] | [`GitCommitTemplateListener`](upsource:///plugins/git4idea/src/git4idea/repo/GitCommitTemplateTracker.kt) |
| [GitConfigListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitConfigListener)  ![Project-Level][project-level] | [`GitConfigListener`](upsource:///plugins/git4idea/src/git4idea/repo/GitConfigListener.kt) |
| [GitRepository#GIT_REPO_CHANGE](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryChangeListener)  | [`GitRepositoryChangeListener`](upsource:///plugins/git4idea/src/git4idea/repo/GitRepositoryChangeListener.java) |
| [GitRefreshListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitRefreshListener)  | [`GitRefreshListener`](upsource:///plugins/git4idea/src/git4idea/status/GitRefreshListener.java) |
| [GitStagingAreaHolder#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitStagingAreaHolder.StagingAreaListener)  | [`StagingAreaListener`](upsource:///plugins/git4idea/src/git4idea/status/GitStagingAreaHolder.java) |
| [SvnVcs#WC_CONVERTED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [RootsAndBranches#REFRESH_REQUEST](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [EditorConfigSettings#EDITOR_CONFIG_ENABLED_TOPIC](https://jb.gg/ipe/listeners?topics=org.editorconfig.settings.EditorConfigListener)  | [`EditorConfigListener`](upsource:///plugins/editorconfig/src/org/editorconfig/settings/EditorConfigListener.java) |
| [FontChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownApplicationSettings.FontChangedListener)  ![Deprecated][deprecated] | [`FontChangedListener`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownApplicationSettings.java) |
| [SettingsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownApplicationSettings.SettingsChangedListener)  ![Deprecated][deprecated] | [`SettingsChangedListener`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownApplicationSettings.java) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownExtensionsSettings.ChangeListener)  ![Experimental API][experimental] | [`ChangeListener`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownExtensionsSettings.kt) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettings.kt) |
| [MavenImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenImportListener)  ![Project-Level][project-level] | [`MavenImportListener`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenImportListener.java) |
| [Merger#COMMITTED_CHANGES_MERGED_STATE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.integrate.Merger.CommittedChangesMergedStateChanged)  | [`CommittedChangesMergedStateChanged`](upsource:///plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/Merger.java) |
| [SvnMergeInfoCache#SVN_MERGE_INFO_CACHE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.mergeinfo.SvnMergeInfoCache.SvnMergeInfoCacheListener)  | [`SvnMergeInfoCacheListener`](upsource:///plugins/svn4idea/src/org/jetbrains/idea/svn/mergeinfo/SvnMergeInfoCache.java) |
| [GHAccountManager#ACCOUNT_REMOVED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.authentication.accounts.AccountRemovedListener)  ![Deprecated][deprecated] | [`AccountRemovedListener`](upsource:///plugins/github/src/org/jetbrains/plugins/github/authentication/accounts/GHAccountManager.kt) |
| [GHAccountManager#ACCOUNT_TOKEN_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.authentication.accounts.AccountTokenChangedListener)  ![Deprecated][deprecated] | [`AccountTokenChangedListener`](upsource:///plugins/github/src/org/jetbrains/plugins/github/authentication/accounts/GHAccountManager.kt) |
| [GHProjectRepositoriesManager#LIST_CHANGES_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.util.GHProjectRepositoriesManager.ListChangeListener)  | [`ListChangeListener`](upsource:///plugins/github/src/org/jetbrains/plugins/github/util/GHProjectRepositoriesManager.kt) |
| [GradleSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.settings.GradleSettingsListener)  | [`GradleSettingsListener`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/settings/GradleSettingsListener.java) |
| [GradleUiListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.ui.GradleUiListener)  | [`GradleUiListener`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/ui/GradleUiListener.java) |
| [TerminalCommandHandlerCustomizer#TERMINAL_COMMAND_HANDLER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.TerminalCommandHandlerCustomizer.TerminalCommandHandlerListener)  | [`TerminalCommandHandlerListener`](upsource:///plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalCommandHandlerCustomizer.kt) |
| [HgVcs#REMOTE_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  | [`HgUpdater`](upsource:///plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#STATUS_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  | [`HgUpdater`](upsource:///plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#INCOMING_OUTGOING_CHECK_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.status.ui.HgWidgetUpdater)  | [`HgWidgetUpdater`](upsource:///plugins/hg4idea/src/org/zmlx/hg4idea/status/ui/HgWidgetUpdater.java) |


### AntSupport

[`AntSupport`](upsource:///plugins/ant/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [AntSupport.AntMessageCustomizer](https://jb.gg/ipe?extensions=AntSupport.AntMessageCustomizer) | [`AntMessageCustomizer`](upsource:///plugins/ant/src/com/intellij/lang/ant/config/execution/AntMessageCustomizer.java) |

### ByteCodeViewer

[`ByteCodeViewer`](upsource:///plugins/ByteCodeViewer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [ByteCodeViewer.classSearcher](https://jb.gg/ipe?extensions=ByteCodeViewer.classSearcher) | [`ClassSearcher`](upsource:///plugins/ByteCodeViewer/src/com/intellij/byteCodeViewer/ClassSearcher.java) |

### com.intellij.completion.ml.ranking

[`com.intellij.completion.ml.ranking`](upsource:///plugins/completion-ml-ranking/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.completion.ml.additionalContextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.additionalContextFeatures) ![Internal API][internal] | [`AdditionalContextFeatureProvider`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/AdditionalContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementIdProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementIdProvider) ![Internal API][internal] | [`LookupElementIdProvider`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/util/LookupElementIdProvider.kt) |
| [com.intellij.completion.ml.featuresOverride](https://jb.gg/ipe?extensions=com.intellij.completion.ml.featuresOverride) ![Internal API][internal] | [`RankingFeaturesOverrides`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/RankingFeaturesOverrides.kt) |
| [com.intellij.completion.ml.localModelProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.localModelProvider) ![Internal API][internal] | [`LocalZipModelProvider`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/ranker/local/LocalZipModelProvider.kt) |
| [com.intellij.completion.ml.lookupFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.lookupFeatures) ![Internal API][internal] | [`LookupFeatureProvider`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/LookupFeatureProvider.java) |
| [com.intellij.completion.ml.ranking.features.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.features.policy) ![Internal API][internal] | [`CompletionFeaturesPolicy`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/CompletionFeaturesPolicy.kt) |
| [com.intellij.completion.ml.ranking.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.policy) ![Experimental API][experimental] | [`CompletionMLPolicy`](upsource:///plugins/completion-ml-ranking/src/com/intellij/completion/ml/CompletionMLPolicy.kt) |

### com.intellij.copyright

[`com.intellij.copyright`](upsource:///plugins/copyright/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.copyright.updater](https://jb.gg/ipe?extensions=com.intellij.copyright.updater) | [`UpdateCopyrightsProvider`](upsource:///plugins/copyright/src/com/maddyhome/idea/copyright/psi/UpdateCopyrightsProvider.java) |
| [com.intellij.copyright.variablesProvider](https://jb.gg/ipe?extensions=com.intellij.copyright.variablesProvider) | [`CopyrightVariablesProvider`](upsource:///plugins/copyright/src/com/maddyhome/idea/copyright/pattern/CopyrightVariablesProvider.java) |

### com.intellij.gradle

[`com.intellij.gradle`](upsource:///plugins/gradle/plugin-resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.executionEnvironmentProvider) | [`GradleExecutionEnvironmentProvider`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/execution/build/GradleExecutionEnvironmentProvider.java) |
| [org.jetbrains.plugins.gradle.importCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.importCustomizer) | [`GradleImportCustomizer`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleImportCustomizer.java) |
| [org.jetbrains.plugins.gradle.issueChecker](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.issueChecker) ![Experimental API][experimental] | [`GradleIssueChecker`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/issue/GradleIssueChecker.kt) |
| [org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory) | [`FactoryImpl`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/execution/GradleOrderEnumeratorHandler.java) |
| [org.jetbrains.plugins.gradle.projectModelContributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.projectModelContributor) ![Experimental API][experimental] | [`ProjectModelContributor`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/ProjectModelContributor.java) |
| [org.jetbrains.plugins.gradle.projectResolve](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.projectResolve) | [`GradleProjectResolverExtension`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleProjectResolverExtension.java) |
| [org.jetbrains.plugins.gradle.settingsControlProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.settingsControlProvider) | [`GradleSettingsControlProvider`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/service/settings/GradleSettingsControlProvider.java) |
| [org.jetbrains.plugins.gradle.targetEnvironmentAware](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.targetEnvironmentAware) ![Internal API][internal] | [`GradleTargetEnvironmentAware`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/execution/target/GradleTargetEnvironmentAware.java) |
| [org.jetbrains.plugins.gradle.taskManager](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.taskManager) | [`GradleTaskManagerExtension`](upsource:///plugins/gradle/src/org/jetbrains/plugins/gradle/service/task/GradleTaskManagerExtension.java) |

### com.intellij.java-i18n

[`com.intellij.java-i18n`](upsource:///plugins/java-i18n/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.java-i18n.i18nizeHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.java-i18n.i18nizeHandlerProvider) | [`I18nizeHandlerProvider`](upsource:///plugins/java-i18n/src/com/intellij/codeInspection/i18n/I18nizeHandlerProvider.java) |
| [com.intellij.java-i18n.resourceBundleManager](https://jb.gg/ipe?extensions=com.intellij.java-i18n.resourceBundleManager) ![Project-Level][project-level] | [`ResourceBundleManager`](upsource:///plugins/java-i18n/src/com/intellij/lang/properties/psi/ResourceBundleManager.java) |

### com.intellij.properties

[`com.intellij.properties`](upsource:///plugins/properties/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.properties.alphaUnsortedInspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.alphaUnsortedInspectionSuppressor) | [`AlphaUnsortedPropertiesFileInspectionSuppressor`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unsorted/AlphaUnsortedPropertiesFileInspectionSuppressor.java) |
| [com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor) | [`DuplicatePropertyKeyAnnotationSuppressor`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/DuplicatePropertyKeyAnnotationSuppressor.java) |
| [com.intellij.properties.implicitPropertyUsageProvider](https://jb.gg/ipe?extensions=com.intellij.properties.implicitPropertyUsageProvider) | [`ImplicitPropertyUsageProvider`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ImplicitPropertyUsageProvider.java) |

### com.intellij.searcheverywhere.ml

[`com.intellij.searcheverywhere.ml`](upsource:///plugins/search-everywhere-ml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searcheverywhere.ml.elementKeyForIdProvider](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.elementKeyForIdProvider) | [`ElementKeyForIdProvider`](upsource:///plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/id/ElementKeyForIdProvider.kt) |
| [com.intellij.searcheverywhere.ml.rankingModelLoader](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.rankingModelLoader) | [`SearchEverywhereMLRankingModelLoader`](upsource:///plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/model/SearchEverywhereMLRankingModelLoader.kt) |
| [com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider) | [`SearchEverywhereElementFeaturesProvider`](upsource:///plugins/search-everywhere-ml/src/com/intellij/ide/actions/searcheverywhere/ml/features/SearchEverywhereElementFeaturesProvider.kt) |

### com.intellij.stats.completion

[`com.intellij.stats.completion`](upsource:///plugins/stats-collector/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.stats.completion.policy](https://jb.gg/ipe?extensions=com.intellij.stats.completion.policy) ![Internal API][internal] | [`CompletionStatsPolicy`](upsource:///plugins/stats-collector/src/com/intellij/stats/completion/CompletionStatsPolicy.kt) |

### com.intellij.tasks

[`com.intellij.tasks`](upsource:///plugins/tasks/tasks-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tasks.commitPlaceholderProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.commitPlaceholderProvider) | [`CommitPlaceholderProvider`](upsource:///platform/tasks-platform-api/src/com/intellij/tasks/CommitPlaceholderProvider.java) |
| [com.intellij.tasks.dialogPanelProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.dialogPanelProvider) ![Non-Dynamic][non-dynamic] | [`TaskDialogPanelProvider`](upsource:///plugins/tasks/tasks-api/src/com/intellij/tasks/ui/TaskDialogPanelProvider.java) |
| [com.intellij.tasks.repositoryType](https://jb.gg/ipe?extensions=com.intellij.tasks.repositoryType) | [`TaskRepositoryType`](upsource:///platform/tasks-platform-api/src/com/intellij/tasks/TaskRepositoryType.java) |

### com.intellij.uiDesigner

[`com.intellij.uiDesigner`](upsource:///plugins/ui-designer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.paletteItemProvider](https://jb.gg/ipe?extensions=com.intellij.paletteItemProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`PaletteItemProvider`](upsource:///plugins/ui-designer/src/com/intellij/ide/palette/PaletteItemProvider.java) |
| [com.intellij.uiDesigner.formInspectionTool](https://jb.gg/ipe?extensions=com.intellij.uiDesigner.formInspectionTool) ![Non-Dynamic][non-dynamic] | [`FormInspectionTool`](upsource:///plugins/ui-designer/src/com/intellij/uiDesigner/inspections/FormInspectionTool.java) |

### com.jetbrains.filePrediction

[`com.jetbrains.filePrediction`](upsource:///plugins/filePrediction/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.filePrediction.candidateProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.candidateProvider) ![Internal API][internal] | [`FilePredictionCandidateProvider`](upsource:///plugins/filePrediction/src/com/intellij/filePrediction/candidates/FilePredictionCandidateProvider.kt) |
| [com.intellij.filePrediction.featureProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.featureProvider) ![Internal API][internal] | [`FilePredictionFeatureProvider`](upsource:///plugins/filePrediction/src/com/intellij/filePrediction/features/FilePredictionFeatureProvider.kt) |
| [com.intellij.filePrediction.ml.model](https://jb.gg/ipe?extensions=com.intellij.filePrediction.ml.model) ![Internal API][internal] | [`FilePredictionModelProvider`](upsource:///plugins/filePrediction/src/com/intellij/filePrediction/predictor/model/FilePredictionModelProvider.kt) |
| [com.intellij.filePrediction.referencesProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.referencesProvider) ![Internal API][internal] | [`FileExternalReferencesProvider`](upsource:///plugins/filePrediction/src/com/intellij/filePrediction/references/FilePredictionReferencesHelper.kt) |

### com.jetbrains.packagesearch.intellij-plugin

[`com.jetbrains.packagesearch.intellij-plugin`](upsource:///plugins/package-search/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.packagesearch.configurableContributor](https://jb.gg/ipe?extensions=com.intellij.packagesearch.configurableContributor) ![Project-Level][project-level] | [`ConfigurableContributor`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ConfigurableContributor.kt) |
| [com.intellij.packagesearch.coroutineModuleTransformer](https://jb.gg/ipe?extensions=com.intellij.packagesearch.coroutineModuleTransformer) ![Project-Level][project-level] | [`CoroutineModuleTransformer`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleTransformer.kt) |
| [com.intellij.packagesearch.flowModuleChangesSignalProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.flowModuleChangesSignalProvider) ![Project-Level][project-level] | [`FlowModuleChangesSignalProvider`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleChangesSignalProvider.kt) |
| [com.intellij.packagesearch.moduleChangesSignalProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.moduleChangesSignalProvider) ![Project-Level][project-level] | [`ModuleChangesSignalProvider`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleChangesSignalProvider.kt) |
| [com.intellij.packagesearch.moduleTransformer](https://jb.gg/ipe?extensions=com.intellij.packagesearch.moduleTransformer) ![Project-Level][project-level] | [`ModuleTransformer`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ModuleTransformer.kt) |
| [com.intellij.packagesearch.projectModuleOperationProvider](https://jb.gg/ipe?extensions=com.intellij.packagesearch.projectModuleOperationProvider) | [`ProjectModuleOperationProvider`](upsource:///plugins/package-search/src/com/jetbrains/packagesearch/intellij/plugin/extensibility/ProjectModuleOperationProvider.kt) |

### completion.xml

[`completion.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/completion.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.completionExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.completionExtension) ![Non-Dynamic][non-dynamic] | [`KotlinCompletionExtension`](upsource:///plugins/kotlin/completion/src/org/jetbrains/kotlin/idea/completion/KotlinCompletionContributor.kt) |

### Coverage

[`Coverage`](upsource:///plugins/coverage/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.javaCoverageEngineExtension](https://jb.gg/ipe?extensions=com.intellij.javaCoverageEngineExtension) | [`JavaCoverageEngineExtension`](upsource:///plugins/coverage/src/com/intellij/coverage/JavaCoverageEngineExtension.java) |

### coverage-common-plugin.xml

[`coverage-common-plugin.xml`](upsource:///plugins/coverage-common/src/META-INF/coverage-common-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.coverageEngine](https://jb.gg/ipe?extensions=com.intellij.coverageEngine) | [`CoverageEngine`](upsource:///plugins/coverage-common/src/com/intellij/coverage/CoverageEngine.java) |
| [com.intellij.coverageOptions](https://jb.gg/ipe?extensions=com.intellij.coverageOptions) ![Project-Level][project-level] | [`CoverageOptions`](upsource:///plugins/coverage-common/src/com/intellij/coverage/CoverageOptions.java) |
| [com.intellij.coverageRunner](https://jb.gg/ipe?extensions=com.intellij.coverageRunner) | [`CoverageRunner`](upsource:///plugins/coverage-common/src/com/intellij/coverage/CoverageRunner.java) |

### DesignerCorePlugin.xml

[`DesignerCorePlugin.xml`](upsource:///plugins/ui-designer-core/src/META-INF/DesignerCorePlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Designer.customizations](https://jb.gg/ipe?extensions=Designer.customizations) ![Non-Dynamic][non-dynamic] | [`DesignerCustomizations`](upsource:///plugins/ui-designer-core/src/com/intellij/designer/DesignerCustomizations.java) |

### DevKit

[`DevKit`](upsource:///plugins/devkit/devkit-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [DevKit.lang.goodCodeRedVisitor](https://jb.gg/ipe?extensions=DevKit.lang.goodCodeRedVisitor) | [`GoodCodeRedVisitor`](upsource:///plugins/devkit/devkit-core/src/inspections/internal/GoodCodeRedVisitor.java) |

### extensions.xml

[`extensions.xml`](upsource:///plugins/kotlin/resources-fir/resources/META-INF/extensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.buildSystem.kotlin](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.buildSystem.kotlin) | `n/a` |
| [org.jetbrains.kotlin.ktQuickFixRegistrar](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ktQuickFixRegistrar) | [`KtQuickFixRegistrar`](upsource:///plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/fir/api/fixes/KtQuickFixService.kt) |

### file-types.xml

[`file-types.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/file-types.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.binaryExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.binaryExtension) ![Non-Dynamic][non-dynamic] | [`KotlinBinaryExtension`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/util/ProjectRootsUtil.kt) |

### Git4Idea

[`Git4Idea`](upsource:///plugins/git4idea/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Git4Idea.GitCheckinExplicitMovementProvider](https://jb.gg/ipe?extensions=Git4Idea.GitCheckinExplicitMovementProvider) | [`GitCheckinExplicitMovementProvider`](upsource:///plugins/git4idea/src/git4idea/checkin/GitCheckinExplicitMovementProvider.java) |
| [Git4Idea.GitHttpAuthDataProvider](https://jb.gg/ipe?extensions=Git4Idea.GitHttpAuthDataProvider) | [`GitHttpAuthDataProvider`](upsource:///plugins/git4idea/src/git4idea/remote/GitHttpAuthDataProvider.java) |
| [Git4Idea.gitFetchHandler](https://jb.gg/ipe?extensions=Git4Idea.gitFetchHandler) | [`GitFetchHandler`](upsource:///plugins/git4idea/src/git4idea/fetch/GitFetchHandler.kt) |
| [Git4Idea.gitProtectedBranchProvider](https://jb.gg/ipe?extensions=Git4Idea.gitProtectedBranchProvider) | [`GitProtectedBranchProvider`](upsource:///plugins/git4idea/src/git4idea/config/GitProtectedBranchProvider.kt) |
| [Git4Idea.gitRepositoryHostingService](https://jb.gg/ipe?extensions=Git4Idea.gitRepositoryHostingService) | [`GitRepositoryHostingService`](upsource:///plugins/git4idea/src/git4idea/remote/GitRepositoryHostingService.java) |

### google-app-engine-plugin.xml

[`google-app-engine-plugin.xml`](upsource:///plugins/google-app-engine/resources/META-INF/google-app-engine-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.appengine.forbiddenCodeHandler](https://jb.gg/ipe?extensions=com.intellij.appengine.forbiddenCodeHandler) ![Non-Dynamic][non-dynamic] | [`AppEngineForbiddenCodeHandler`](upsource:///plugins/google-app-engine/source/com/intellij/appengine/inspections/AppEngineForbiddenCodeHandler.java) |

### gradle-groovy-integration.xml

[`gradle-groovy-integration.xml`](upsource:///plugins/gradle/java/resources/META-INF/gradle-groovy-integration.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.pluginDescriptions](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.pluginDescriptions) | [`GradlePluginDescriptionsExtension`](upsource:///plugins/gradle/java/src/codeInsight/GradlePluginDescriptionsExtension.java) |
| [org.jetbrains.plugins.gradle.resolve.contributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.resolve.contributor) | [`GradleMethodContextContributor`](upsource:///plugins/gradle/java/src/service/resolve/GradleMethodContextContributor.java) |

### highlighting.xml

[`highlighting.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/highlighting.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.beforeResolveHighlightingVisitor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.beforeResolveHighlightingVisitor) ![Non-Dynamic][non-dynamic] | [`BeforeResolveHighlightingExtension`](upsource:///plugins/kotlin/frontend-independent/src/org/jetbrains/kotlin/idea/highlighter/KotlinBeforeResolveHighlightingPass.kt) |
| [org.jetbrains.kotlin.highlighterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.highlighterExtension) | [`HighlighterExtension`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/highlighter/HighlighterExtension.kt) |

### InspectionGadgets.xml

[`InspectionGadgets.xml`](upsource:///plugins/InspectionGadgets/src/META-INF/InspectionGadgets.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.implicit.resource.closer](https://jb.gg/ipe?extensions=com.intellij.implicit.resource.closer) | [`ImplicitResourceCloser`](upsource:///plugins/InspectionGadgets/InspectionGadgetsAnalysis/src/com/intellij/codeInspection/resources/ImplicitResourceCloser.java) |
| [com.intellij.naming.convention.class](https://jb.gg/ipe?extensions=com.intellij.naming.convention.class) | [`NamingConvention`](upsource:///platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.field](https://jb.gg/ipe?extensions=com.intellij.naming.convention.field) | [`NamingConvention`](upsource:///platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.method](https://jb.gg/ipe?extensions=com.intellij.naming.convention.method) | [`NamingConvention`](upsource:///platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |

### intellij.java.remoteServers.impl.xml

[`intellij.java.remoteServers.impl.xml`](upsource:///java/remote-servers/impl/src/intellij.java.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.moduleBuilderContribution](https://jb.gg/ipe?extensions=com.intellij.remoteServer.moduleBuilderContribution) ![Non-Dynamic][non-dynamic] | [`CloudModuleBuilderContributionFactory`](upsource:///java/remote-servers/impl/src/com/intellij/remoteServer/impl/module/CloudModuleBuilderContributionFactory.java) |

### JavaAnalysisPlugin.xml

[`JavaAnalysisPlugin.xml`](upsource:///java/java-analysis-impl/src/META-INF/JavaAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allowedApiFilter](https://jb.gg/ipe?extensions=com.intellij.allowedApiFilter) | [`AllowedApiFilterExtension`](upsource:///java/java-analysis-impl/src/com/intellij/psi/impl/AllowedApiFilterExtension.java) |
| [com.intellij.canBeFinal](https://jb.gg/ipe?extensions=com.intellij.canBeFinal) | [`CanBeFinalHandler`](upsource:///java/java-analysis-impl/src/com/intellij/codeInspection/canBeFinal/CanBeFinalHandler.java) |
| [com.intellij.codeInsight.changeVariableTypeQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.changeVariableTypeQuickFixProvider) | [`ChangeVariableTypeQuickFixProvider`](upsource:///java/java-analysis-api/src/com/intellij/codeInsight/quickfix/ChangeVariableTypeQuickFixProvider.java) |
| [com.intellij.codeInsight.implicitSubclassProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implicitSubclassProvider) | [`ImplicitSubclassProvider`](upsource:///java/java-analysis-impl/src/com/intellij/codeInspection/inheritance/ImplicitSubclassProvider.kt) |
| [com.intellij.deadCode](https://jb.gg/ipe?extensions=com.intellij.deadCode) | [`EntryPoint`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| [com.intellij.java.error.fix](https://jb.gg/ipe?extensions=com.intellij.java.error.fix) | [`IntentionAction`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) |
| [com.intellij.lang.jvm.actions.jvmElementActionsFactory](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.actions.jvmElementActionsFactory) | [`JvmElementActionsFactory`](upsource:///java/java-analysis-api/src/com/intellij/lang/jvm/actions/JvmElementActionsFactory.kt) |
| [com.intellij.lang.jvm.annotationPackageSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.annotationPackageSupport) | [`AnnotationPackageSupport`](upsource:///java/java-impl/src/com/intellij/codeInsight/annoPackages/AnnotationPackageSupport.java) |
| [com.intellij.lang.jvm.ignoreAnnotationParamSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.ignoreAnnotationParamSupport) | [`IgnoreAnnotationParamSupport`](upsource:///java/java-impl-inspections/src/com/intellij/codeInspection/DefaultAnnotationParamInspection.java) |
| [com.intellij.visibility](https://jb.gg/ipe?extensions=com.intellij.visibility) | [`VisibilityExtension`](upsource:///platform/analysis-impl/src/com/intellij/codeInspection/visibility/VisibilityExtension.java) |

### JavaIndexingPlugin.xml

[`JavaIndexingPlugin.xml`](upsource:///java/java-indexing-impl/src/META-INF/JavaIndexingPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allClassesSearch](https://jb.gg/ipe?extensions=com.intellij.allClassesSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.allOverridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.allOverridingMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedElementsSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedElementsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedPackagesSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedPackagesSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.classInheritorsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classesWithAnnotatedMembersSearch](https://jb.gg/ipe?extensions=com.intellij.classesWithAnnotatedMembersSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.customPropertyScopeProvider](https://jb.gg/ipe?extensions=com.intellij.customPropertyScopeProvider) | [`CustomPropertyScopeProvider`](upsource:///java/java-indexing-impl/src/com/intellij/psi/impl/search/CustomPropertyScopeProvider.java) |
| [com.intellij.directClassInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.directClassInheritorsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.functionalExpressionSearch](https://jb.gg/ipe?extensions=com.intellij.functionalExpressionSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.implicitToStringSearch](https://jb.gg/ipe?extensions=com.intellij.implicitToStringSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.java.shortNamesCache](https://jb.gg/ipe?extensions=com.intellij.java.shortNamesCache) ![Project-Level][project-level] | [`PsiShortNamesCache`](upsource:///java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java) |
| [com.intellij.methodReferencesSearch](https://jb.gg/ipe?extensions=com.intellij.methodReferencesSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.overridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.overridingMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### JavaPlugin.xml

[`JavaPlugin.xml`](upsource:///java/java-impl/src/META-INF/JavaPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.OrderRootTypeUI](https://jb.gg/ipe?extensions=com.intellij.OrderRootTypeUI) | [`OrderRootTypeUIFactory`](upsource:///platform/lang-api/src/com/intellij/openapi/roots/ui/OrderRootTypeUIFactory.java) |
| [com.intellij.attachSourcesProvider](https://jb.gg/ipe?extensions=com.intellij.attachSourcesProvider) | [`AttachSourcesProvider`](upsource:///java/openapi/src/com/intellij/codeInsight/AttachSourcesProvider.java) |
| [com.intellij.buildProcess.parametersProvider](https://jb.gg/ipe?extensions=com.intellij.buildProcess.parametersProvider) ![Project-Level][project-level] | [`BuildProcessParametersProvider`](upsource:///java/compiler/impl/src/com/intellij/compiler/server/BuildProcessParametersProvider.java) |
| [com.intellij.canBeEmpty](https://jb.gg/ipe?extensions=com.intellij.canBeEmpty) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeInsight.createFieldFromUsageHelper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.createFieldFromUsageHelper) | [`CreateFieldFromUsageHelper`](upsource:///java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/CreateFieldFromUsageHelper.java) |
| [com.intellij.codeInsight.externalLibraryResolver](https://jb.gg/ipe?extensions=com.intellij.codeInsight.externalLibraryResolver) | [`ExternalLibraryResolver`](upsource:///java/java-impl/src/com/intellij/codeInsight/daemon/quickFix/ExternalLibraryResolver.java) |
| [com.intellij.compilableFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.compilableFileTypesProvider) ![Project-Level][project-level] | [`CompilableFileTypesProvider`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/CompilableFileTypesProvider.java) |
| [com.intellij.compileServer.plugin](https://jb.gg/ipe?extensions=com.intellij.compileServer.plugin) | `n/a` |
| [com.intellij.compiler](https://jb.gg/ipe?extensions=com.intellij.compiler) ![Deprecated][deprecated] ![Project-Level][project-level] | [`Compiler`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/Compiler.java) |
| [com.intellij.compiler.buildIssueContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.buildIssueContributor) ![Experimental API][experimental] | [`BuildIssueContributor`](upsource:///java/compiler/impl/src/com/intellij/compiler/progress/BuildIssueContributor.java) |
| [com.intellij.compiler.buildTargetScopeProvider](https://jb.gg/ipe?extensions=com.intellij.compiler.buildTargetScopeProvider) | [`BuildTargetScopeProvider`](upsource:///java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) |
| [com.intellij.compiler.inspectionValidator](https://jb.gg/ipe?extensions=com.intellij.compiler.inspectionValidator) ![Project-Level][project-level] | [`InspectionValidator`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/util/InspectionValidator.java) |
| [com.intellij.compiler.isUpToDateCheckConsumer](https://jb.gg/ipe?extensions=com.intellij.compiler.isUpToDateCheckConsumer) | [`IsUpToDateCheckConsumer`](upsource:///java/compiler/impl/src/com/intellij/compiler/backwardRefs/IsUpToDateCheckConsumer.java) |
| [com.intellij.compiler.optionsManager](https://jb.gg/ipe?extensions=com.intellij.compiler.optionsManager) | [`CompilerOptionsFilter`](upsource:///java/compiler/impl/src/com/intellij/compiler/options/CompilerOptionsFilter.java) |
| [com.intellij.compiler.task](https://jb.gg/ipe?extensions=com.intellij.compiler.task) ![Project-Level][project-level] | [`CompileTask`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/CompileTask.java) |
| [com.intellij.compiler.updateResourcesBuildContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.updateResourcesBuildContributor) | [`UpdateResourcesBuildContributor`](upsource:///java/compiler/impl/src/com/intellij/compiler/impl/UpdateResourcesBuildContributor.java) |
| [com.intellij.compilerFactory](https://jb.gg/ipe?extensions=com.intellij.compilerFactory) ![Deprecated][deprecated] ![Project-Level][project-level] | [`CompilerFactory`](upsource:///java/compiler/openapi/src/com/intellij/openapi/compiler/CompilerFactory.java) |
| [com.intellij.configuration.ModuleStructureExtension](https://jb.gg/ipe?extensions=com.intellij.configuration.ModuleStructureExtension) | [`ModuleStructureExtension`](upsource:///java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureExtension.java) |
| [com.intellij.constructorBodyGenerator](https://jb.gg/ipe?extensions=com.intellij.constructorBodyGenerator) ![Internal API][internal] | [`ConstructorBodyGenerator`](upsource:///java/java-impl/src/com/intellij/codeInsight/generation/ConstructorBodyGenerator.java) |
| [com.intellij.conversion.rule](https://jb.gg/ipe?extensions=com.intellij.conversion.rule) | [`TypeConversionRule`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/typeMigration/rules/TypeConversionRule.java) |
| [com.intellij.debugger.asyncStackTraceProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.asyncStackTraceProvider) | [`AsyncStackTraceProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/engine/AsyncStackTraceProvider.java) |
| [com.intellij.debugger.codeFragmentFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.codeFragmentFactory) | [`CodeFragmentFactory`](upsource:///java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/CodeFragmentFactory.java) |
| [com.intellij.debugger.compoundRendererProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.compoundRendererProvider) | [`CompoundRendererProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/ui/tree/render/CompoundRendererProvider.java) |
| [com.intellij.debugger.extraSteppingFilter](https://jb.gg/ipe?extensions=com.intellij.debugger.extraSteppingFilter) | [`ExtraSteppingFilter`](upsource:///java/debugger/impl/src/com/intellij/debugger/engine/ExtraSteppingFilter.java) |
| [com.intellij.debugger.frameExtraVarsProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.frameExtraVarsProvider) | [`FrameExtraVariablesProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/engine/FrameExtraVariablesProvider.java) |
| [com.intellij.debugger.javaBreakpointHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.javaBreakpointHandlerFactory) | [`JavaBreakpointHandlerFactory`](upsource:///java/debugger/impl/src/com/intellij/debugger/engine/JavaBreakpointHandlerFactory.java) |
| [com.intellij.debugger.javaDebugAware](https://jb.gg/ipe?extensions=com.intellij.debugger.javaDebugAware) | [`JavaDebugAware`](upsource:///java/debugger/openapi/src/com/intellij/debugger/engine/JavaDebugAware.java) |
| [com.intellij.debugger.jvmSmartStepIntoHandler](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSmartStepIntoHandler) | [`JvmSmartStepIntoHandler`](upsource:///java/debugger/impl/src/com/intellij/debugger/actions/JvmSmartStepIntoHandler.java) |
| [com.intellij.debugger.jvmSteppingCommandProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSteppingCommandProvider) | [`JvmSteppingCommandProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/impl/JvmSteppingCommandProvider.java) |
| [com.intellij.debugger.nodeNameAdjuster](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeNameAdjuster) | [`NodeDescriptorNameAdjuster`](upsource:///java/debugger/openapi/src/com/intellij/debugger/ui/tree/NodeDescriptorNameAdjuster.java) |
| [com.intellij.debugger.nodeRenderer](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeRenderer) | [`NodeRenderer`](upsource:///java/debugger/impl/src/com/intellij/debugger/ui/tree/render/NodeRenderer.java) |
| [com.intellij.debugger.positionManagerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.positionManagerFactory) | [`PositionManagerFactory`](upsource:///java/debugger/openapi/src/com/intellij/debugger/PositionManagerFactory.java) |
| [com.intellij.debugger.simplePropertyGetterProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.simplePropertyGetterProvider) | [`SimplePropertyGetterProvider`](upsource:///java/debugger/openapi/src/com/intellij/debugger/engine/SimplePropertyGetterProvider.java) |
| [com.intellij.debugger.sourcePositionHighlighter](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionHighlighter) | [`SourcePositionHighlighter`](upsource:///java/debugger/openapi/src/com/intellij/debugger/engine/SourcePositionHighlighter.java) |
| [com.intellij.debugger.sourcePositionProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionProvider) | [`SourcePositionProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/engine/SourcePositionProvider.java) |
| [com.intellij.debugger.syntheticProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.syntheticProvider) | [`SyntheticTypeComponentProvider`](upsource:///java/debugger/openapi/src/com/intellij/debugger/engine/SyntheticTypeComponentProvider.java) |
| [com.intellij.debuggerClassFilterProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerClassFilterProvider) | [`DebuggerClassFilterProvider`](upsource:///java/openapi/src/com/intellij/ui/classFilter/DebuggerClassFilterProvider.java) |
| [com.intellij.debuggerEditorTextProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerEditorTextProvider) | [`EditorTextProvider`](upsource:///java/debugger/impl/src/com/intellij/debugger/impl/EditorTextProvider.java) |
| [com.intellij.documentationDelegateProvider](https://jb.gg/ipe?extensions=com.intellij.documentationDelegateProvider) | [`DocumentationDelegateProvider`](upsource:///java/java-impl/src/com/intellij/codeInsight/javadoc/DocumentationDelegateProvider.java) |
| [com.intellij.encapsulateFields.Helper](https://jb.gg/ipe?extensions=com.intellij.encapsulateFields.Helper) | [`EncapsulateFieldHelper`](upsource:///java/openapi/src/com/intellij/refactoring/encapsulateFields/EncapsulateFieldHelper.java) |
| [com.intellij.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.exceptionFilter) | [`ExceptionFilterFactory`](upsource:///java/execution/openapi/src/com/intellij/execution/filters/ExceptionFilterFactory.java) |
| [com.intellij.externalAnnotationsArtifactsResolver](https://jb.gg/ipe?extensions=com.intellij.externalAnnotationsArtifactsResolver) | [`ExternalAnnotationsArtifactsResolver`](upsource:///java/java-impl/src/com/intellij/codeInsight/ExternalAnnotationsArtifactsResolver.java) |
| [com.intellij.framework.type](https://jb.gg/ipe?extensions=com.intellij.framework.type) | [`FrameworkTypeEx`](upsource:///java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java) |
| [com.intellij.frameworkSupport](https://jb.gg/ipe?extensions=com.intellij.frameworkSupport) | [`FrameworkSupportProvider`](upsource:///platform/lang-api/src/com/intellij/ide/util/frameworkSupport/FrameworkSupportProvider.java) |
| [com.intellij.frameworkSupportCommunicator](https://jb.gg/ipe?extensions=com.intellij.frameworkSupportCommunicator) | [`FrameworkSupportCommunicator`](upsource:///java/idea-ui/src/com/intellij/ide/util/newProjectWizard/impl/FrameworkSupportCommunicator.java) |
| [com.intellij.generateAccessorProvider](https://jb.gg/ipe?extensions=com.intellij.generateAccessorProvider) | [`NotNullFunction`](upsource:///platform/util-rt/src/com/intellij/util/NotNullFunction.java) |
| [com.intellij.generation.toStringClassFilter](https://jb.gg/ipe?extensions=com.intellij.generation.toStringClassFilter) | [`GenerateToStringClassFilter`](upsource:///plugins/generate-tostring/src/org/jetbrains/generate/tostring/GenerateToStringClassFilter.java) |
| [com.intellij.getterSetterProvider](https://jb.gg/ipe?extensions=com.intellij.getterSetterProvider) | [`GetterSetterPrototypeProvider`](upsource:///java/java-impl/src/com/intellij/codeInsight/generation/GetterSetterPrototypeProvider.java) |
| [com.intellij.hierarchy.referenceProcessor](https://jb.gg/ipe?extensions=com.intellij.hierarchy.referenceProcessor) | [`CallReferenceProcessor`](upsource:///java/openapi/src/com/intellij/ide/hierarchy/call/CallReferenceProcessor.java) |
| [com.intellij.jarRepositoryAuthenticationDataProvider](https://jb.gg/ipe?extensions=com.intellij.jarRepositoryAuthenticationDataProvider) ![Experimental API][experimental] | [`JarRepositoryAuthenticationDataProvider`](upsource:///java/idea-ui/src/com/intellij/jarRepository/JarRepositoryAuthenticationDataProvider.kt) |
| [com.intellij.java.compiler](https://jb.gg/ipe?extensions=com.intellij.java.compiler) ![Project-Level][project-level] | [`BackendCompiler`](upsource:///java/compiler/impl/src/com/intellij/compiler/impl/javaCompiler/BackendCompiler.java) |
| [com.intellij.java.externalAnnotation](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation) | [`AnnotationProvider`](upsource:///java/java-impl/src/com/intellij/codeInsight/externalAnnotation/AnnotationProvider.java) |
| [com.intellij.java.externalAnnotation.locationProvider](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation.locationProvider) | [`AnnotationsLocationProvider`](upsource:///java/java-impl/src/com/intellij/codeInsight/externalAnnotation/location/AnnotationsLocationProvider.java) |
| [com.intellij.java.inspection.bulkMethodInfo](https://jb.gg/ipe?extensions=com.intellij.java.inspection.bulkMethodInfo) | [`BulkMethodInfoProvider`](upsource:///java/java-impl/src/com/intellij/codeInspection/bulkOperation/BulkMethodInfoProvider.java) |
| [com.intellij.java.programPatcher](https://jb.gg/ipe?extensions=com.intellij.java.programPatcher) | [`JavaProgramPatcher`](upsource:///java/execution/openapi/src/com/intellij/execution/runners/JavaProgramPatcher.java) |
| [com.intellij.java.refactoring.chainCallExtractor](https://jb.gg/ipe?extensions=com.intellij.java.refactoring.chainCallExtractor) | [`ChainCallExtractor`](upsource:///java/java-impl/src/com/intellij/refactoring/chainCall/ChainCallExtractor.java) |
| [com.intellij.javaDocNotNecessary](https://jb.gg/ipe?extensions=com.intellij.javaDocNotNecessary) | [`Condition`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.javaExpressionSurrounder](https://jb.gg/ipe?extensions=com.intellij.javaExpressionSurrounder) | [`JavaExpressionSurrounder`](upsource:///java/openapi/src/com/intellij/codeInsight/generation/surroundWith/JavaExpressionSurrounder.java) |
| [com.intellij.jpsServerAuthExtension](https://jb.gg/ipe?extensions=com.intellij.jpsServerAuthExtension) | [`JpsServerAuthExtension`](upsource:///java/compiler/impl/src/com/intellij/compiler/cache/client/JpsServerAuthExtension.java) |
| [com.intellij.jreProvider](https://jb.gg/ipe?extensions=com.intellij.jreProvider) | [`JreProvider`](upsource:///java/execution/impl/src/com/intellij/execution/ui/JreProvider.java) |
| [com.intellij.junitPatcher](https://jb.gg/ipe?extensions=com.intellij.junitPatcher) | [`JUnitPatcher`](upsource:///java/execution/openapi/src/com/intellij/execution/JUnitPatcher.java) |
| [com.intellij.junitRecognizer](https://jb.gg/ipe?extensions=com.intellij.junitRecognizer) | [`JUnitRecognizer`](upsource:///java/execution/openapi/src/com/intellij/execution/JUnitRecognizer.java) |
| [com.intellij.jvm.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.jvm.exceptionFilter) ![Experimental API][experimental] | [`JvmExceptionOccurrenceFilter`](upsource:///java/execution/openapi/src/com/intellij/execution/filters/JvmExceptionOccurrenceFilter.java) |
| [com.intellij.languageCompilerRefAdapter](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter) | [`LanguageCompilerRefAdapter`](upsource:///java/compiler/impl/src/com/intellij/compiler/backwardRefs/LanguageCompilerRefAdapter.java) |
| [com.intellij.languageCompilerRefAdapter.directInheritorProvider](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter.directInheritorProvider) ![Project-Level][project-level] | [`DirectInheritorProvider`](upsource:///java/compiler/impl/src/com/intellij/compiler/backwardRefs/DirectInheritorProvider.kt) |
| [com.intellij.library.dependencyScopeSuggester](https://jb.gg/ipe?extensions=com.intellij.library.dependencyScopeSuggester) | [`LibraryDependencyScopeSuggester`](upsource:///java/java-impl/src/com/intellij/openapi/roots/LibraryDependencyScopeSuggester.java) |
| [com.intellij.library.javaSourceRootDetector](https://jb.gg/ipe?extensions=com.intellij.library.javaSourceRootDetector) | [`RootDetector`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/libraries/ui/RootDetector.java) |
| [com.intellij.methodImplementor](https://jb.gg/ipe?extensions=com.intellij.methodImplementor) | [`MethodImplementor`](upsource:///java/openapi/src/com/intellij/codeInsight/MethodImplementor.java) |
| [com.intellij.moduleConfigurable](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurable) | [`ModuleConfigurable`](upsource:///java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleConfigurable.java) |
| [com.intellij.newProjectWizard.java.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.java.buildSystem) | [`BuildSystemJavaNewProjectWizard`](upsource:///java/idea-ui/src/com/intellij/ide/projectWizard/generators/BuildSystemJavaNewProjectWizard.kt) |
| [com.intellij.overrideImplementsAnnotationsHandler](https://jb.gg/ipe?extensions=com.intellij.overrideImplementsAnnotationsHandler) | [`OverrideImplementsAnnotationsHandler`](upsource:///java/java-impl/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsHandler.java) |
| [com.intellij.packaging.artifactPropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactPropertiesProvider) | [`ArtifactPropertiesProvider`](upsource:///java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactPropertiesProvider.java) |
| [com.intellij.packaging.artifactType](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactType) | [`ArtifactType`](upsource:///java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactType.java) |
| [com.intellij.packaging.elementType](https://jb.gg/ipe?extensions=com.intellij.packaging.elementType) | [`PackagingElementType`](upsource:///java/compiler/openapi/src/com/intellij/packaging/elements/PackagingElementType.java) |
| [com.intellij.packaging.sourceItemFilter](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemFilter) | [`PackagingSourceItemFilter`](upsource:///java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemFilter.java) |
| [com.intellij.packaging.sourceItemProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemProvider) | [`PackagingSourceItemsProvider`](upsource:///java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemsProvider.java) |
| [com.intellij.predefinedMigrationMapProvider](https://jb.gg/ipe?extensions=com.intellij.predefinedMigrationMapProvider) | [`PredefinedMigrationProvider`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/migration/PredefinedMigrationProvider.java) |
| [com.intellij.projectImportBuilder](https://jb.gg/ipe?extensions=com.intellij.projectImportBuilder) | [`ProjectImportBuilder`](upsource:///java/idea-ui/src/com/intellij/projectImport/ProjectImportBuilder.java) |
| [com.intellij.projectImportProvider](https://jb.gg/ipe?extensions=com.intellij.projectImportProvider) | [`ProjectImportProvider`](upsource:///java/idea-ui/src/com/intellij/projectImport/ProjectImportProvider.java) |
| [com.intellij.projectModelModifier](https://jb.gg/ipe?extensions=com.intellij.projectModelModifier) ![Project-Level][project-level] | [`JavaProjectModelModifier`](upsource:///java/java-impl/src/com/intellij/openapi/roots/JavaProjectModelModifier.java) |
| [com.intellij.projectStructureConfigurableFilter](https://jb.gg/ipe?extensions=com.intellij.projectStructureConfigurableFilter) | [`ProjectStructureConfigurableFilter`](upsource:///java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ProjectStructureConfigurableFilter.java) |
| [com.intellij.projectStructureDetector](https://jb.gg/ipe?extensions=com.intellij.projectStructureDetector) | [`ProjectStructureDetector`](upsource:///java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java) |
| [com.intellij.projectStructureValidator](https://jb.gg/ipe?extensions=com.intellij.projectStructureValidator) | [`ProjectStructureValidator`](upsource:///java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/daemon/ProjectStructureValidator.java) |
| [com.intellij.projectWizard.projectCategory](https://jb.gg/ipe?extensions=com.intellij.projectWizard.projectCategory) | [`ProjectCategory`](upsource:///java/idea-ui/src/com/intellij/ide/projectWizard/ProjectCategory.java) |
| [com.intellij.refactoring.introduceParameterMethodUsagesProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.introduceParameterMethodUsagesProcessor) | [`IntroduceParameterMethodUsagesProcessor`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/introduceParameter/IntroduceParameterMethodUsagesProcessor.java) |
| [com.intellij.refactoring.moveAllClassesInFileHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveAllClassesInFileHandler) | [`MoveAllClassesInFileHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveAllClassesInFileHandler.java) |
| [com.intellij.refactoring.moveClassHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassHandler) | [`MoveClassHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassHandler.java) |
| [com.intellij.refactoring.moveClassToInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassToInnerHandler) ![Internal API][internal] | [`MoveClassToInnerHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassToInnerHandler.java) |
| [com.intellij.refactoring.moveInnerClassUsagesHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerClassUsagesHandler) | [`MoveInnerClassUsagesHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerClassUsagesHandler.java) |
| [com.intellij.refactoring.moveInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerHandler) | [`MoveInnerHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerHandler.java) |
| [com.intellij.refactoring.moveMemberHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveMemberHandler) | [`MoveMemberHandler`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/move/moveMembers/MoveMemberHandler.java) |
| [com.intellij.refactoring.overrideMethodProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.overrideMethodProcessor) ![Internal API][internal] | [`OverrideMethodsProcessor`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/OverrideMethodsProcessor.java) |
| [com.intellij.refactoring.pullUpHelperFactory](https://jb.gg/ipe?extensions=com.intellij.refactoring.pullUpHelperFactory) | [`PullUpHelperFactory`](upsource:///java/openapi/src/com/intellij/refactoring/memberPullUp/PullUpHelperFactory.java) |
| [com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate](https://jb.gg/ipe?extensions=com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate) | [`JavaSafeDeleteDelegate`](upsource:///java/openapi/src/com/intellij/refactoring/safeDelete/JavaSafeDeleteDelegate.java) |
| [com.intellij.repositoryLibrary](https://jb.gg/ipe?extensions=com.intellij.repositoryLibrary) | `n/a` |
| [com.intellij.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.runConfigurationExtension) | [`RunConfigurationExtension`](upsource:///java/execution/impl/src/com/intellij/execution/RunConfigurationExtension.java) |
| [com.intellij.safeDelete.importSearcher](https://jb.gg/ipe?extensions=com.intellij.safeDelete.importSearcher) ![Internal API][internal] | [`ImportSearcher`](upsource:///java/java-impl-refactorings/src/com/intellij/refactoring/safeDelete/ImportSearcher.java) |
| [com.intellij.sdkEditorAdditionalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.sdkEditorAdditionalOptionsProvider) | [`SdkEditorAdditionalOptionsProvider`](upsource:///java/idea-ui/src/com/intellij/openapi/SdkEditorAdditionalOptionsProvider.java) |
| [com.intellij.starter.moduleImporter](https://jb.gg/ipe?extensions=com.intellij.starter.moduleImporter) | [`StarterModuleImporter`](upsource:///java/idea-ui/src/com/intellij/ide/starters/StarterModuleImporter.kt) |
| [com.intellij.testGenerator](https://jb.gg/ipe?extensions=com.intellij.testGenerator) | [`TestGenerator`](upsource:///java/java-impl/src/com/intellij/testIntegration/createTest/TestGenerator.java) |
| [com.intellij.unscrambleSupport](https://jb.gg/ipe?extensions=com.intellij.unscrambleSupport) | [`UnscrambleSupport`](upsource:///java/openapi/src/com/intellij/unscramble/UnscrambleSupport.java) |
| [com.intellij.unusedDeclarationFixProvider](https://jb.gg/ipe?extensions=com.intellij.unusedDeclarationFixProvider) | [`UnusedDeclarationFixProvider`](upsource:///java/java-analysis-api/src/com/intellij/codeInspection/reference/UnusedDeclarationFixProvider.java) |
| [com.intellij.variableTypeCalculator](https://jb.gg/ipe?extensions=com.intellij.variableTypeCalculator) | [`VariableTypeCalculator`](upsource:///java/java-impl/src/com/intellij/codeInsight/template/macro/VariableTypeCalculator.java) |

### JavaPsiPlugin.xml

[`JavaPsiPlugin.xml`](upsource:///java/java-psi-impl/src/META-INF/JavaPsiPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.annotationSupport](https://jb.gg/ipe?extensions=com.intellij.annotationSupport) | [`PsiAnnotationSupport`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiAnnotationSupport.java) |
| [com.intellij.classTypePointerFactory](https://jb.gg/ipe?extensions=com.intellij.classTypePointerFactory) | [`ClassTypePointerFactory`](upsource:///java/java-psi-api/src/com/intellij/psi/ClassTypePointerFactory.java) |
| [com.intellij.codeStyle.ReferenceAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeStyle.ReferenceAdjuster) | [`ReferenceAdjuster`](upsource:///java/java-psi-api/src/com/intellij/psi/codeStyle/ReferenceAdjuster.java) |
| [com.intellij.constantExpressionEvaluator](https://jb.gg/ipe?extensions=com.intellij.constantExpressionEvaluator) | [`ConstantExpressionEvaluator`](upsource:///java/java-psi-impl/src/com/intellij/psi/impl/ConstantExpressionEvaluator.java) |
| [com.intellij.custom.exception.handler](https://jb.gg/ipe?extensions=com.intellij.custom.exception.handler) | [`CustomExceptionHandler`](upsource:///java/java-psi-impl/src/com/intellij/codeInsight/CustomExceptionHandler.java) |
| [com.intellij.customJavadocTagProvider](https://jb.gg/ipe?extensions=com.intellij.customJavadocTagProvider) | [`CustomJavadocTagProvider`](upsource:///java/java-psi-api/src/com/intellij/psi/javadoc/CustomJavadocTagProvider.java) |
| [com.intellij.deepestSuperMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.deepestSuperMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.expressionConverter](https://jb.gg/ipe?extensions=com.intellij.expressionConverter) | [`ExpressionConverter`](upsource:///java/java-psi-impl/src/com/intellij/psi/impl/ExpressionConverter.java) |
| [com.intellij.generation.topLevelFactory](https://jb.gg/ipe?extensions=com.intellij.generation.topLevelFactory) | [`JVMElementFactoryProvider`](upsource:///java/java-psi-api/src/com/intellij/psi/JVMElementFactoryProvider.java) |
| [com.intellij.importFilter](https://jb.gg/ipe?extensions=com.intellij.importFilter) | [`ImportFilter`](upsource:///java/java-psi-api/src/com/intellij/codeInsight/ImportFilter.java) |
| [com.intellij.java.elementFinder](https://jb.gg/ipe?extensions=com.intellij.java.elementFinder) ![Project-Level][project-level] | [`PsiElementFinder`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiElementFinder.java) |
| [com.intellij.java.languageFeatureProvider](https://jb.gg/ipe?extensions=com.intellij.java.languageFeatureProvider) | [`LanguageFeatureProvider`](upsource:///java/java-psi-api/src/com/intellij/pom/java/LanguageFeatureProvider.java) |
| [com.intellij.javaCompilerConfigurationProxy](https://jb.gg/ipe?extensions=com.intellij.javaCompilerConfigurationProxy) | [`JavaCompilerConfigurationProxy`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaCompilerConfigurationProxy.java) |
| [com.intellij.javaMainMethodProvider](https://jb.gg/ipe?extensions=com.intellij.javaMainMethodProvider) | [`JavaMainMethodProvider`](upsource:///java/java-psi-api/src/com/intellij/codeInsight/runner/JavaMainMethodProvider.java) |
| [com.intellij.javaModuleSystem](https://jb.gg/ipe?extensions=com.intellij.javaModuleSystem) | [`JavaModuleSystem`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaModuleSystem.java) |
| [com.intellij.javadocTagInfo](https://jb.gg/ipe?extensions=com.intellij.javadocTagInfo) ![Project-Level][project-level] | [`JavadocTagInfo`](upsource:///java/java-psi-api/src/com/intellij/psi/javadoc/JavadocTagInfo.java) |
| [com.intellij.jvm.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.jvm.declarationSearcher) | [`JvmDeclarationSearcher`](upsource:///java/java-psi-api/src/com/intellij/lang/jvm/source/JvmDeclarationSearcher.java) |
| [com.intellij.jvm.elementProvider](https://jb.gg/ipe?extensions=com.intellij.jvm.elementProvider) ![Project-Level][project-level] | [`JvmElementProvider`](upsource:///java/java-psi-api/src/com/intellij/lang/jvm/facade/JvmElementProvider.java) |
| [com.intellij.lang.inferredAnnotationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.inferredAnnotationProvider) ![Project-Level][project-level] | [`InferredAnnotationProvider`](upsource:///java/java-psi-api/src/com/intellij/codeInsight/InferredAnnotationProvider.java) |
| [com.intellij.lang.psiAugmentProvider](https://jb.gg/ipe?extensions=com.intellij.lang.psiAugmentProvider) | [`PsiAugmentProvider`](upsource:///java/java-psi-api/src/com/intellij/psi/augment/PsiAugmentProvider.java) |
| [com.intellij.psi.classFileDecompiler](https://jb.gg/ipe?extensions=com.intellij.psi.classFileDecompiler) | `Decompiler` |
| [com.intellij.psi.clsCustomNavigationPolicy](https://jb.gg/ipe?extensions=com.intellij.psi.clsCustomNavigationPolicy) | [`ClsCustomNavigationPolicy`](upsource:///java/java-psi-impl/src/com/intellij/psi/impl/compiled/ClsCustomNavigationPolicy.java) |
| [com.intellij.superMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.superMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.testFramework](https://jb.gg/ipe?extensions=com.intellij.testFramework) | [`TestFramework`](upsource:///platform/core-api/src/com/intellij/testIntegration/TestFramework.java) |
| [org.jetbrains.uast.analysis.uastAnalysisPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.analysis.uastAnalysisPlugin) ![Experimental API][experimental] | [`UastAnalysisPlugin`](upsource:///uast/uast-common/src/org/jetbrains/uast/analysis/UastAnalysisPlugin.kt) |
| [org.jetbrains.uast.generate.uastCodeGenerationPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.generate.uastCodeGenerationPlugin) ![Experimental API][experimental] | [`UastCodeGenerationPlugin`](upsource:///uast/uast-common/src/org/jetbrains/uast/generate/UastCodeGenerationPlugin.kt) |
| [org.jetbrains.uast.uastLanguagePlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.uastLanguagePlugin) | [`UastLanguagePlugin`](upsource:///uast/uast-common/src/org/jetbrains/uast/UastLanguagePlugin.kt) |

### jps.xml

[`jps.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/jps.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idePlatformKind](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKind) ![Non-Dynamic][non-dynamic] | `IdePlatformKind` |
| [org.jetbrains.kotlin.idePlatformKindResolution](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindResolution) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindResolution`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/caches/resolve/IdePlatformKindResolution.kt) |
| [org.jetbrains.kotlin.idePlatformKindTooling](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindTooling) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindTooling`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/platform/IdePlatformKindTooling.kt) |

### JUnit

[`JUnit`](upsource:///plugins/junit/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.junitListener](https://jb.gg/ipe?extensions=com.intellij.junitListener) | [`IDEAJUnitListener`](upsource:///java/java-runtime/src/com/intellij/rt/execution/junit/IDEAJUnitListener.java) |
| [com.intellij.testDiscoveryProducer](https://jb.gg/ipe?extensions=com.intellij.testDiscoveryProducer) | [`TestDiscoveryProducer`](upsource:///java/execution/impl/src/com/intellij/execution/testDiscovery/TestDiscoveryProducer.java) |

### jvm-common.xml

[`jvm-common.xml`](upsource:///plugins/kotlin/jvm/resources/META-INF/jvm-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.lightTestFramework](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.lightTestFramework) | [`LightTestFramework`](upsource:///plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/platform/testintegration/LightTestFramework.kt) |

### jvm.xml

[`jvm.xml`](upsource:///plugins/kotlin/jvm/resources/META-INF/jvm.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.kotlinJvmDebuggerFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.kotlinJvmDebuggerFacade) | [`KotlinJvmDebuggerFacade`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/extensions/KotlinDebuggerFacade.kt) |

### JvmAnalysisPlugin.xml

[`JvmAnalysisPlugin.xml`](upsource:///jvm/jvm-analysis-impl/resources/META-INF/JvmAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.blockingMethodChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.blockingMethodChecker) | [`BlockingMethodChecker`](upsource:///jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/BlockingMethodChecker.java) |
| [com.intellij.codeInsight.nonBlockingContextChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.nonBlockingContextChecker) | [`NonBlockingContextChecker`](upsource:///jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/NonBlockingContextChecker.java) |

### kotlin-core.xml

[`kotlin-core.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/kotlin-core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.buildSystemTypeDetector](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.buildSystemTypeDetector) | [`BuildSystemTypeDetector`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/configuration/BuildSystemType.kt) |
| [org.jetbrains.kotlin.clearBuildState](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.clearBuildState) ![Non-Dynamic][non-dynamic] | [`ClearBuildStateExtension`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/compiler/configuration/ClearBuildStateExtension.kt) |
| [org.jetbrains.kotlin.experimentalFeature](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.experimentalFeature) | [`ExperimentalFeature`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/configuration/ExperimentalFeatures.kt) |
| [org.jetbrains.kotlin.facetValidatorCreator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.facetValidatorCreator) ![Non-Dynamic][non-dynamic] | [`KotlinFacetValidatorCreator`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/facet/KotlinFacetValidatorCreator.kt) |
| [org.jetbrains.kotlin.idea.codeInsight.unambiguousImports](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.codeInsight.unambiguousImports) | [`KotlinAutoImportsFilter`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/KotlinAutoImportsFilter.kt) |
| [org.jetbrains.kotlin.idea.testFrameworkProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.testFrameworkProvider) | [`KotlinTestFrameworkProvider`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/extensions/KotlinTestFrameworkProvider.kt) |
| [org.jetbrains.kotlin.j2kConverterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.j2kConverterExtension) | [`J2kConverterExtension`](upsource:///plugins/kotlin/j2k/old/src/org/jetbrains/kotlin/j2k/J2KConverterExtension.kt) |
| [org.jetbrains.kotlin.newFileHook](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.newFileHook) ![Non-Dynamic][non-dynamic] | [`NewKotlinFileHook`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/actions/NewKotlinFileAction.kt) |
| [org.jetbrains.kotlin.pluginUpdateVerifier](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.pluginUpdateVerifier) ![Non-Dynamic][non-dynamic] | [`PluginUpdateVerifier`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/update/PluginUpdateVerifier.kt) |
| [org.jetbrains.kotlin.projectConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.projectConfigurator) | [`KotlinProjectConfigurator`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/configuration/KotlinProjectConfigurator.kt) |
| [org.jetbrains.kotlin.updater](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.updater) ![Non-Dynamic][non-dynamic] | `n/a` |

### kotlin.gradle.gradle-idea.xml

[`kotlin.gradle.gradle-idea.xml`](upsource:///plugins/kotlin/gradle/gradle-idea/resources/kotlin.gradle.gradle-idea.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleModelFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleModelFacade) | [`KotlinGradleModelFacade`](upsource:///plugins/kotlin/gradle/gradle-idea/src/org/jetbrains/kotlin/idea/gradle/inspections/KotlinGradleModelFacade.java) |
| [org.jetbrains.kotlin.idea.gradleBuildScriptSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.gradleBuildScriptSupport) | [`GradleBuildScriptSupport`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/extensions/gradle/GradleBuildScriptSupport.kt) |

### kotlin.gradle.gradle-java.xml

[`kotlin.gradle.gradle-java.xml`](upsource:///plugins/kotlin/gradle/gradle-java/resources/kotlin.gradle.gradle-java.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleProjectImportHandler) ![Project-Level][project-level] | [`GradleProjectImportHandler`](upsource:///plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/KotlinGradleSourceSetDataService.kt) |

### kotlin.maven.xml

[`kotlin.maven.xml`](upsource:///plugins/kotlin/maven/resources/kotlin.maven.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.mavenProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.mavenProjectImportHandler) ![Project-Level][project-level] | [`MavenProjectImportHandler`](upsource:///plugins/kotlin/maven/src/org/jetbrains/kotlin/idea/maven/KotlinMavenImporter.kt) |

### kotlin.project-wizard.idea.xml

[`kotlin.project-wizard.idea.xml`](upsource:///plugins/kotlin/project-wizard/idea/resources/kotlin.project-wizard.idea.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.kotlin.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.kotlin.buildSystem) | [`BuildSystemKotlinNewProjectWizard`](upsource:///plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/BuildSystemKotlinNewProjectWizard.kt) |
| [org.jetbrains.kotlin.idea.ideaWizardService](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.ideaWizardService) ![Project-Level][project-level] | [`IdeaWizardService`](upsource:///plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/wizard/service/IdeaWizardService.kt) |

### libraryJarUsage.xml

[`libraryJarUsage.xml`](upsource:///java/java-impl/src/META-INF/libraryJarUsage.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.internal.statistic.libraryUsage.libraryUsageImportProcessor](https://jb.gg/ipe?extensions=com.intellij.internal.statistic.libraryUsage.libraryUsageImportProcessor) | [`LibraryUsageImportProcessor`](upsource:///java/java-impl/src/com/intellij/internal/statistic/libraryUsage/LibraryUsageImportProcessor.kt) |

### light-classes.xml

[`light-classes.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/light-classes.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ultraLightClassModifierExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ultraLightClassModifierExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UltraLightClassModifierExtension` |

### ManifestSupport.xml

[`ManifestSupport.xml`](upsource:///java/manifest/src/META-INF/ManifestSupport.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.manifest.parser.provider](https://jb.gg/ipe?extensions=com.intellij.manifest.parser.provider) | [`HeaderParserProvider`](upsource:///java/manifest/src/org/jetbrains/lang/manifest/header/HeaderParserProvider.java) |

### org.editorconfig.editorconfigjetbrains

[`org.editorconfig.editorconfigjetbrains`](upsource:///plugins/editorconfig/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [editorconfig.exportProvider](https://jb.gg/ipe?extensions=editorconfig.exportProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigExportProvider`](upsource:///plugins/editorconfig/src/org/editorconfig/settings/EditorConfigExportProvider.java) |
| [editorconfig.optionDescriptorProvider](https://jb.gg/ipe?extensions=editorconfig.optionDescriptorProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigOptionDescriptorProvider`](upsource:///plugins/editorconfig/src/org/editorconfig/language/extensions/EditorConfigOptionDescriptorProvider.kt) |

### org.intellij.groovy

[`org.intellij.groovy`](upsource:///plugins/groovy/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.groovy.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.groovy.buildSystem) | [`BuildSystemGroovyNewProjectWizard`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/BuildSystemGroovyNewProjectWizard.kt) |
| [org.intellij.groovy.applicabilityProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.applicabilityProvider) ![Experimental API][experimental] | [`GroovyApplicabilityProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyApplicabilityProvider.java) |
| [org.intellij.groovy.astTransformationSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.astTransformationSupport) | [`AstTransformationSupport`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/AstTransformationSupport.java) |
| [org.intellij.groovy.callTypeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.callTypeCalculator) ![Experimental API][experimental] | [`GrCallTypeCalculator`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrCallTypeCalculator.kt) |
| [org.intellij.groovy.classDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.classDescriptor) | `n/a` |
| [org.intellij.groovy.closureCompleter](https://jb.gg/ipe?extensions=org.intellij.groovy.closureCompleter) | [`ClosureCompleter`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/ClosureCompleter.java) |
| [org.intellij.groovy.closureMissingMethodContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.closureMissingMethodContributor) | [`ClosureMissingMethodContributor`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ClosureMissingMethodContributor.java) |
| [org.intellij.groovy.configSlurperSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.configSlurperSupport) | [`ConfigSlurperSupport`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/configSlurper/ConfigSlurperSupport.java) |
| [org.intellij.groovy.convertToJava.customMethodInvocator](https://jb.gg/ipe?extensions=org.intellij.groovy.convertToJava.customMethodInvocator) | [`CustomMethodInvocator`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/convertToJava/invocators/CustomMethodInvocator.java) |
| [org.intellij.groovy.customAnnotationChecker](https://jb.gg/ipe?extensions=org.intellij.groovy.customAnnotationChecker) | [`CustomAnnotationChecker`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/checkers/CustomAnnotationChecker.java) |
| [org.intellij.groovy.delegatesToProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.delegatesToProvider) | [`GrDelegatesToProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/delegatesTo/GrDelegatesToProvider.java) |
| [org.intellij.groovy.elementFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.elementFilter) ![Experimental API][experimental] | [`GroovyElementFilter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/GroovyElementFilter.kt) |
| [org.intellij.groovy.expectedPackageNameProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedPackageNameProvider) | [`ExpectedPackageNameProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ExpectedPackageNameProvider.kt) |
| [org.intellij.groovy.expectedTypesContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedTypesContributor) | [`GroovyExpectedTypesContributor`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/expectedTypes/GroovyExpectedTypesContributor.java) |
| [org.intellij.groovy.gdslScriptProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslScriptProvider) | [`GdslScriptProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/GdslScriptProvider.java) |
| [org.intellij.groovy.gdslTopLevelProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslTopLevelProvider) ![Non-Dynamic][non-dynamic] | [`GdslMembersProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/dsltop/GdslMembersProvider.java) |
| [org.intellij.groovy.groovyFrameworkConfigNotification](https://jb.gg/ipe?extensions=org.intellij.groovy.groovyFrameworkConfigNotification) | [`GroovyFrameworkConfigNotification`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/GroovyFrameworkConfigNotification.java) |
| [org.intellij.groovy.groovySourceFolderDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.groovySourceFolderDetector) | [`GroovySourceFolderDetector`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/actions/GroovySourceFolderDetector.java) |
| [org.intellij.groovy.importContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.importContributor) | [`GrImportContributor`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/imports/GrImportContributor.java) |
| [org.intellij.groovy.inlayHintFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.inlayHintFilter) | [`GroovyInlayHintFilter`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/editor/GroovyInlayHintFilter.java) |
| [org.intellij.groovy.inspectionDisabler](https://jb.gg/ipe?extensions=org.intellij.groovy.inspectionDisabler) | [`FileTypeInspectionDisabler`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/FileTypeInspectionDisabler.kt) |
| [org.intellij.groovy.mapContentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.mapContentProvider) | [`GroovyMapContentProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyMapContentProvider.java) |
| [org.intellij.groovy.membersContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.membersContributor) | [`NonCodeMembersContributor`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/NonCodeMembersContributor.java) |
| [org.intellij.groovy.methodComparator](https://jb.gg/ipe?extensions=org.intellij.groovy.methodComparator) | [`GrMethodComparator`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/GrMethodComparator.java) |
| [org.intellij.groovy.methodDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.methodDescriptor) | `n/a` |
| [org.intellij.groovy.methodMayBeStaticInspectionFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.methodMayBeStaticInspectionFilter) | [`GrMethodMayBeStaticInspectionFilter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/declaration/GrMethodMayBeStaticInspectionFilter.java) |
| [org.intellij.groovy.namedArgumentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.namedArgumentProvider) | [`GroovyNamedArgumentProvider`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyNamedArgumentProvider.java) |
| [org.intellij.groovy.overloadResolver](https://jb.gg/ipe?extensions=org.intellij.groovy.overloadResolver) | [`GroovyOverloadResolver`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/api/GroovyOverloadResolver.java) |
| [org.intellij.groovy.positionManagerDelegate](https://jb.gg/ipe?extensions=org.intellij.groovy.positionManagerDelegate) | [`ScriptPositionManagerHelper`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/extensions/debugger/ScriptPositionManagerHelper.java) |
| [org.intellij.groovy.psiEnhancerCategory](https://jb.gg/ipe?extensions=org.intellij.groovy.psiEnhancerCategory) ![Non-Dynamic][non-dynamic] | [`PsiEnhancerCategory`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/psi/PsiEnhancerCategory.java) |
| [org.intellij.groovy.referenceTypeEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.referenceTypeEnhancer) | [`GrReferenceTypeEnhancer`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrReferenceTypeEnhancer.java) |
| [org.intellij.groovy.renameHelper](https://jb.gg/ipe?extensions=org.intellij.groovy.renameHelper) | [`GrRenameHelper`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/rename/GrRenameHelper.java) |
| [org.intellij.groovy.scriptTypeDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.scriptTypeDetector) | [`GroovyScriptTypeDetector`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyScriptTypeDetector.java) |
| [org.intellij.groovy.signatureHintProcessor](https://jb.gg/ipe?extensions=org.intellij.groovy.signatureHintProcessor) | [`SignatureHintProcessor`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/SignatureHintProcessor.java) |
| [org.intellij.groovy.typeAugmenter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeAugmenter) | [`TypeAugmenter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/dataFlow/types/TypeAugmenter.kt) |
| [org.intellij.groovy.typeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.typeCalculator) | [`GrTypeCalculator`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrTypeCalculator.java) |
| [org.intellij.groovy.typeConverter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeConverter) | [`GrTypeConverter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrTypeConverter.java) |
| [org.intellij.groovy.unresolvedHighlightFileFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFileFilter) | [`GroovyUnresolvedHighlightFileFilter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFileFilter.java) |
| [org.intellij.groovy.unresolvedHighlightFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFilter) | [`GroovyUnresolvedHighlightFilter`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFilter.java) |
| [org.intellij.groovy.variableEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.variableEnhancer) | [`GrVariableEnhancer`](upsource:///plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrVariableEnhancer.java) |

### org.intellij.intelliLang

[`org.intellij.intelliLang`](upsource:///plugins/IntelliLang/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.intelliLang.injectionConfig](https://jb.gg/ipe?extensions=org.intellij.intelliLang.injectionConfig) | `n/a` |
| [org.intellij.intelliLang.languageSupport](https://jb.gg/ipe?extensions=org.intellij.intelliLang.languageSupport) | [`LanguageInjectionSupport`](upsource:///plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/LanguageInjectionSupport.java) |

### org.intellij.plugins.markdown

[`org.intellij.plugins.markdown`](upsource:///plugins/markdown/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.markdown.browserPreviewExtensionProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.browserPreviewExtensionProvider) | [`Provider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/MarkdownBrowserPreviewExtension.kt) |
| [org.intellij.markdown.fenceGeneratingProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceGeneratingProvider) ![Experimental API][experimental] | [`CodeFenceGeneratingProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/CodeFenceGeneratingProvider.kt) |
| [org.intellij.markdown.fenceLanguageProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceLanguageProvider) | [`CodeFenceLanguageProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/injection/CodeFenceLanguageProvider.java) |
| [org.intellij.markdown.html.panel.provider](https://jb.gg/ipe?extensions=org.intellij.markdown.html.panel.provider) | [`MarkdownHtmlPanelProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownHtmlPanelProvider.java) |
| [org.intellij.markdown.markdownExportProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownExportProvider) ![Experimental API][experimental] | [`MarkdownExportProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/fileActions/export/MarkdownExportProvider.kt) |
| [org.intellij.markdown.markdownRunner](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownRunner) | [`MarkdownRunner`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/jcef/commandRunner/MarkdownRunner.kt) |
| [org.intellij.markdown.previewStylesProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.previewStylesProvider) ![Internal API][internal] | [`MarkdownPreviewStylesProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownPreviewStylesProvider.kt) |

### org.jetbrains.debugger.streams

[`org.jetbrains.debugger.streams`](upsource:///plugins/stream-debugger/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.debugger.streams.librarySupport](https://jb.gg/ipe?extensions=org.jetbrains.debugger.streams.librarySupport) | [`LibrarySupportProvider`](upsource:///plugins/stream-debugger/src/com/intellij/debugger/streams/lib/LibrarySupportProvider.java) |

### org.jetbrains.idea.eclipse

[`org.jetbrains.idea.eclipse`](upsource:///plugins/eclipse/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.eclipse.natureImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.eclipse.natureImporter) | [`EclipseNatureImporter`](upsource:///plugins/eclipse/src/org/jetbrains/idea/eclipse/importWizard/EclipseNatureImporter.java) |

### org.jetbrains.idea.maven

[`org.jetbrains.idea.maven`](upsource:///plugins/maven/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.maven.additional.importing.settings](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.additional.importing.settings) | [`AdditionalMavenImportingSettings`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/project/AdditionalMavenImportingSettings.java) |
| [org.jetbrains.idea.maven.archetypesProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.archetypesProvider) | [`MavenArchetypesProvider`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenArchetypesProvider.java) |
| [org.jetbrains.idea.maven.artifactBuilder](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.artifactBuilder) | [`MavenArtifactBuilder`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenArtifactBuilder.java) |
| [org.jetbrains.idea.maven.compiler](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.compiler) | [`MavenCompilerExtension`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenCompilerExtension.java) |
| [org.jetbrains.idea.maven.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.executionEnvironmentProvider) | [`MavenExecutionEnvironmentProvider`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenExecutionEnvironmentProvider.java) |
| [org.jetbrains.idea.maven.import.status.listener](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.import.status.listener) | [`MavenImportStatusListener`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/project/importing/MavenImportStatusListener.java) |
| [org.jetbrains.idea.maven.importer](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importer) | [`MavenImporter`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenImporter.java) |
| [org.jetbrains.idea.maven.log.import.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.import.parser) ![Experimental API][experimental] | [`MavenImportLoggedEventParser`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/importproject/MavenImportLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.parser) ![Experimental API][experimental] | [`MavenLoggedEventParser`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.spy.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.spy.parser) ![Experimental API][experimental] | [`MavenSpyLoggedEventParser`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenSpyLoggedEventParser.java) |
| [org.jetbrains.idea.maven.manifestImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.manifestImporter) | [`ManifestImporter`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/ManifestImporter.java) |
| [org.jetbrains.idea.maven.mavenServerSupportFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.mavenServerSupportFactory) ![Internal API][internal] | [`MavenRemoteProcessSupportFactory`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/server/MavenRemoteProcessSupportFactory.java) |
| [org.jetbrains.idea.maven.pluginDescriptor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.pluginDescriptor) ![Internal API][internal] | `n/a` |
| [org.jetbrains.idea.maven.remotePathTransformerFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.remotePathTransformerFactory) | [`RemotePathTransformerFactory`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/server/RemotePathTransformerFactory.java) |
| [org.jetbrains.idea.maven.repositoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.repositoryProvider) | [`MavenRepositoryProvider`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenRepositoryProvider.java) |
| [org.jetbrains.idea.maven.targetConfigurationExtension](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.targetConfigurationExtension) ![Experimental API][experimental] | [`TargetConfigurationMavenExtension`](upsource:///plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/target/TargetConfigurationMavenExtension.java) |

### org.jetbrains.idea.reposearch

[`org.jetbrains.idea.reposearch`](upsource:///plugins/repository-search/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.reposearch.provider](https://jb.gg/ipe?extensions=org.jetbrains.idea.reposearch.provider) ![Experimental API][experimental] | [`DependencySearchProvidersFactory`](upsource:///plugins/repository-search/src/main/java/org/jetbrains/idea/reposearch/DependencySearchProvidersFactory.java) |

### org.jetbrains.kotlin.fir

[`org.jetbrains.kotlin.fir`](upsource:///plugins/kotlin/resources-fir/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.pluginUpdateVerifier](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.pluginUpdateVerifier) ![Non-Dynamic][non-dynamic] | [`PluginUpdateVerifier`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/update/PluginUpdateVerifier.kt) |
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) ![Non-Dynamic][non-dynamic] | [`KotlinResolveScopeEnlarger`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/caches/resolve/util/scopeUtils.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptDefinitionsProvider`](upsource:///plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptDefinitionProvider.kt) |
| [org.jetbrains.kotlin.scripting.idea.listener](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.listener) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptChangeListener`](upsource:///plugins/kotlin/core/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |

### org.jetbrains.plugins.gradle

[`org.jetbrains.plugins.gradle`](upsource:///plugins/gradle/java/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.buildTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.buildTasksProvider) | [`GradleBuildTasksProvider`](upsource:///plugins/gradle/java/src/execution/build/GradleBuildTasksProvider.java) |
| [org.jetbrains.plugins.gradle.frameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.frameworkSupport) | [`GradleFrameworkSupportProvider`](upsource:///plugins/gradle/java/src/frameworkSupport/GradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport) | [`KotlinDslGradleFrameworkSupportProvider`](upsource:///plugins/gradle/java/src/frameworkSupport/KotlinDslGradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.testTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.testTasksProvider) | [`GradleTestTasksProvider`](upsource:///plugins/gradle/java/src/execution/test/runner/GradleTestTasksProvider.java) |

### org.jetbrains.plugins.yaml

[`org.jetbrains.plugins.yaml`](upsource:///plugins/yaml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.yaml.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.yaml.customStructureViewFactory) | [`YAMLCustomStructureViewFactory`](upsource:///plugins/yaml/src/org/jetbrains/yaml/structureView/YAMLCustomStructureViewFactory.java) |

### parcelize.xml

[`parcelize.xml`](upsource:///plugins/kotlin/compiler-plugins/parcelize/common/resources/META-INF/parcelize.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider) ![Project-Level][project-level] | [`ParcelizeAvailabilityProvider`](upsource:///plugins/kotlin/compiler-plugins/parcelize/common/src/org/jetbrains/kotlin/idea/compilerPlugin/parcelize/ParcelizeAvailability.kt) |

### python-core-common.xml

[`python-core-common.xml`](upsource:///python/src/META-INF/python-core-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.breakpointHandler](https://jb.gg/ipe?extensions=Pythonid.breakpointHandler) | [`PyBreakpointHandlerFactory`](upsource:///python/src/com/jetbrains/python/debugger/PyBreakpointHandlerFactory.java) |
| [Pythonid.condaSdkCustomizer](https://jb.gg/ipe?extensions=Pythonid.condaSdkCustomizer) ![Experimental API][experimental] | [`PyCondaSdkCustomizer`](upsource:///python/src/com/jetbrains/python/sdk/conda/PyCondaSdkCustomizer.kt) |
| [Pythonid.consoleOptionsProvider](https://jb.gg/ipe?extensions=Pythonid.consoleOptionsProvider) | [`PyConsoleOptionsProvider`](upsource:///python/src/com/jetbrains/python/console/PyConsoleOptionsProvider.java) |
| [Pythonid.dataViewPanelFactory](https://jb.gg/ipe?extensions=Pythonid.dataViewPanelFactory) | [`PyDataViewPanelFactory`](upsource:///python/src/com/jetbrains/python/debugger/containerview/PyDataViewPanelFactory.java) |
| [Pythonid.debugSessionFactory](https://jb.gg/ipe?extensions=Pythonid.debugSessionFactory) | [`PyDebugSessionFactory`](upsource:///python/src/com/jetbrains/python/debugger/PyDebugSessionFactory.java) |
| [Pythonid.documentationLinkProvider](https://jb.gg/ipe?extensions=Pythonid.documentationLinkProvider) | [`PythonDocumentationLinkProvider`](upsource:///python/openapi/src/com/jetbrains/python/documentation/PythonDocumentationLinkProvider.java) |
| [Pythonid.interpreterTargetEnvironmentFactory](https://jb.gg/ipe?extensions=Pythonid.interpreterTargetEnvironmentFactory) ![Experimental API][experimental] | [`PythonInterpreterTargetEnvironmentFactory`](upsource:///python/src/com/jetbrains/python/run/PythonInterpreterTargetEnvironmentFactory.kt) |
| [Pythonid.magicLiteral](https://jb.gg/ipe?extensions=Pythonid.magicLiteral) | [`PyMagicLiteralExtensionPoint`](upsource:///python/python-psi-impl/src/com/jetbrains/python/magicLiteral/PyMagicLiteralExtensionPoint.java) |
| [Pythonid.packageManagerProvider](https://jb.gg/ipe?extensions=Pythonid.packageManagerProvider) ![Experimental API][experimental] | [`PyPackageManagerProvider`](upsource:///python/src/com/jetbrains/python/packaging/PyCustomPackageManagers.kt) |
| [Pythonid.pep8ProblemSuppressor](https://jb.gg/ipe?extensions=Pythonid.pep8ProblemSuppressor) | [`Pep8ProblemSuppressor`](upsource:///python/src/com/jetbrains/python/validation/Pep8ProblemSuppressor.java) |
| [Pythonid.projectSynchronizerProvider](https://jb.gg/ipe?extensions=Pythonid.projectSynchronizerProvider) | [`PyProjectSynchronizerProvider`](upsource:///python/src/com/jetbrains/python/remote/PyProjectSynchronizer.kt) |
| [Pythonid.pyAddSdkProvider](https://jb.gg/ipe?extensions=Pythonid.pyAddSdkProvider) | [`PyAddSdkProvider`](upsource:///python/src/com/jetbrains/python/sdk/add/PyAddSdkProvider.kt) |
| [Pythonid.pyAnnotator](https://jb.gg/ipe?extensions=Pythonid.pyAnnotator) | [`PyAnnotator`](upsource:///python/python-psi-impl/src/com/jetbrains/python/validation/PyAnnotator.java) |
| [Pythonid.pyCustomSdkUiProvider](https://jb.gg/ipe?extensions=Pythonid.pyCustomSdkUiProvider) | [`PyCustomSdkUiProvider`](upsource:///python/src/com/jetbrains/python/sdk/PyCustomSdkUiProvider.java) |
| [Pythonid.pyPregeneratedSkeletonsProvider](https://jb.gg/ipe?extensions=Pythonid.pyPregeneratedSkeletonsProvider) | [`PyPregeneratedSkeletonsProvider`](upsource:///python/src/com/jetbrains/python/sdk/skeletons/PyPregeneratedSkeletonsProvider.java) |
| [Pythonid.pyRootTypeProvider](https://jb.gg/ipe?extensions=Pythonid.pyRootTypeProvider) | [`PyRootTypeProvider`](upsource:///python/src/com/jetbrains/python/module/PyRootTypeProvider.java) |
| [Pythonid.pySdkProvider](https://jb.gg/ipe?extensions=Pythonid.pySdkProvider) ![Experimental API][experimental] | [`PySdkProvider`](upsource:///python/src/com/jetbrains/python/sdk/PySdkProvider.kt) |
| [Pythonid.pyTestConfigurationSelector](https://jb.gg/ipe?extensions=Pythonid.pyTestConfigurationSelector) | [`PyTestConfigurationSelector`](upsource:///python/src/com/jetbrains/python/testing/PyTestConfigurationSelector.kt) |
| [Pythonid.pyTestFixtureExtension](https://jb.gg/ipe?extensions=Pythonid.pyTestFixtureExtension) | [`PyTestFixtureExtension`](upsource:///python/src/com/jetbrains/python/testing/pyTestFixtures/PyTestFixtureExtension.kt) |
| [Pythonid.pythonCommandLineEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineEnvironmentProvider) | [`PythonCommandLineEnvironmentProvider`](upsource:///python/src/com/jetbrains/python/run/PythonCommandLineEnvironmentProvider.java) |
| [Pythonid.pythonCommandLineTargetEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineTargetEnvironmentProvider) ![Experimental API][experimental] ![Internal API][internal] | [`PythonCommandLineTargetEnvironmentProvider`](upsource:///python/src/com/jetbrains/python/run/target/PythonCommandLineTargetEnvironmentProvider.kt) |
| [Pythonid.pythonSdkComparator](https://jb.gg/ipe?extensions=Pythonid.pythonSdkComparator) | [`PySdkComparator`](upsource:///python/src/com/jetbrains/python/sdk/PySdkComparator.java) |
| [Pythonid.remoteConsoleProcessCreator](https://jb.gg/ipe?extensions=Pythonid.remoteConsoleProcessCreator) | [`PythonConsoleRemoteProcessCreator`](upsource:///python/src/com/jetbrains/python/console/PythonConsoleRemoteProcessCreator.kt) |
| [Pythonid.remoteInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.remoteInterpreterManager) | [`PythonRemoteInterpreterManager`](upsource:///python/src/com/jetbrains/python/remote/PythonRemoteInterpreterManager.java) |
| [Pythonid.remoteProcessStarterManager](https://jb.gg/ipe?extensions=Pythonid.remoteProcessStarterManager) | [`PyRemoteProcessStarterManager`](upsource:///python/src/com/jetbrains/python/run/PyRemoteProcessStarterManager.java) |
| [Pythonid.remoteSdkValidator](https://jb.gg/ipe?extensions=Pythonid.remoteSdkValidator) | [`PyRemoteSdkValidator`](upsource:///python/python-psi-impl/src/com/jetbrains/python/sdk/PyRemoteSdkValidator.kt) |
| [Pythonid.remoteSkeletonGeneratorFactory](https://jb.gg/ipe?extensions=Pythonid.remoteSkeletonGeneratorFactory) | [`PyRemoteSkeletonGeneratorFactory`](upsource:///python/src/com/jetbrains/python/remote/PyRemoteSkeletonGeneratorFactory.java) |
| [Pythonid.runConfigurationEditorExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationEditorExtension) ![Internal API][internal] | [`PyRunConfigurationEditorExtension`](upsource:///python/src/com/jetbrains/python/run/PyRunConfigurationEditorExtension.java) |
| [Pythonid.runConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationExtension) | [`PythonRunConfigurationExtension`](upsource:///python/src/com/jetbrains/python/run/PythonRunConfigurationExtension.java) |
| [Pythonid.runnableScriptFilter](https://jb.gg/ipe?extensions=Pythonid.runnableScriptFilter) | [`RunnableScriptFilter`](upsource:///python/src/com/jetbrains/python/run/RunnableScriptFilter.java) |
| [Pythonid.sshInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.sshInterpreterManager) | [`PythonSshInterpreterManager`](upsource:///python/src/com/jetbrains/python/remote/PythonSshInterpreterManager.java) |
| [Pythonid.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=Pythonid.unresolvedReferenceQuickFixProvider) | [`PyUnresolvedReferenceQuickFixProvider`](upsource:///python/openapi/src/com/jetbrains/python/inspections/PyUnresolvedReferenceQuickFixProvider.java) |
| [com.jetbrains.python.console.executeCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.executeCustomizer) ![Experimental API][experimental] | [`PyExecuteConsoleCustomizer`](upsource:///python/src/com/jetbrains/python/console/PyExecuteConsoleCustomizer.kt) |
| [com.jetbrains.python.console.pyConsoleOutputCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.pyConsoleOutputCustomizer) ![Experimental API][experimental] | [`PyConsoleOutputCustomizer`](upsource:///python/src/com/jetbrains/python/console/PyConsoleOutputCustomizer.kt) |
| [com.jetbrains.python.debugger.numericContainerPopupCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.debugger.numericContainerPopupCustomizer) ![Experimental API][experimental] | [`PyNumericContainerPopupCustomizer`](upsource:///python/pydevSrc/com/jetbrains/python/debugger/pydev/tables/PyNumericContainerPopupCustomizer.kt) |

### python-rest-plugin.xml

[`python-rest-plugin.xml`](upsource:///python/python-rest/resources/META-INF/python-rest-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [restructured.text.html.preview.output.handler](https://jb.gg/ipe?extensions=restructured.text.html.preview.output.handler) ![Experimental API][experimental] | [`RestOutputHandler`](upsource:///python/python-rest/src/com/jetbrains/rest/RestOutputHandler.kt) |

### PythonPsi.xml

[`PythonPsi.xml`](upsource:///python/python-psi-api/resources/META-INF/PythonPsi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.canonicalPathProvider](https://jb.gg/ipe?extensions=Pythonid.canonicalPathProvider) | [`PyCanonicalPathProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyCanonicalPathProvider.java) |
| [Pythonid.customPackageIdentifier](https://jb.gg/ipe?extensions=Pythonid.customPackageIdentifier) | [`PyCustomPackageIdentifier`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/PyCustomPackageIdentifier.java) |
| [Pythonid.dialectsTokenSetContributor](https://jb.gg/ipe?extensions=Pythonid.dialectsTokenSetContributor) | [`PythonDialectsTokenSetContributor`](upsource:///python/python-psi-api/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |
| [Pythonid.importResolver](https://jb.gg/ipe?extensions=Pythonid.importResolver) | [`PyImportResolver`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/impl/PyImportResolver.java) |
| [Pythonid.inspectionExtension](https://jb.gg/ipe?extensions=Pythonid.inspectionExtension) | [`PyInspectionExtension`](upsource:///python/python-psi-api/src/com/jetbrains/python/inspections/PyInspectionExtension.java) |
| [Pythonid.keywordArgumentProvider](https://jb.gg/ipe?extensions=Pythonid.keywordArgumentProvider) | [`PyKeywordArgumentProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/impl/PyKeywordArgumentProvider.java) |
| [Pythonid.knownDecoratorProvider](https://jb.gg/ipe?extensions=Pythonid.knownDecoratorProvider) | [`PyKnownDecoratorProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/PyKnownDecoratorProvider.java) |
| [Pythonid.pyClassInheritorsSearch](https://jb.gg/ipe?extensions=Pythonid.pyClassInheritorsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyClassMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyClassMembersProvider) | [`PyClassMembersProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/types/PyClassMembersProvider.java) |
| [Pythonid.pyModuleMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyModuleMembersProvider) | [`PyModuleMembersProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/types/PyModuleMembersProvider.java) |
| [Pythonid.pyOverridingMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pyOverridingMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyReferenceResolveProvider](https://jb.gg/ipe?extensions=Pythonid.pyReferenceResolveProvider) | [`PyReferenceResolveProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyReferenceResolveProvider.java) |
| [Pythonid.pySuperMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pySuperMethodsSearch) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pythonDocumentationQuickInfoProvider](https://jb.gg/ipe?extensions=Pythonid.pythonDocumentationQuickInfoProvider) | [`PythonDocumentationQuickInfoProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/documentation/PythonDocumentationQuickInfoProvider.java) |
| [Pythonid.resolveResultRater](https://jb.gg/ipe?extensions=Pythonid.resolveResultRater) | [`PyResolveResultRater`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/impl/PyResolveResultRater.java) |
| [Pythonid.thirdPartySdkDetector](https://jb.gg/ipe?extensions=Pythonid.thirdPartySdkDetector) ![Experimental API][experimental] | [`PyThirdPartySdkDetector`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyThirdPartySdkDetector.java) |
| [Pythonid.typeProvider](https://jb.gg/ipe?extensions=Pythonid.typeProvider) | [`PyTypeProvider`](upsource:///python/python-psi-api/src/com/jetbrains/python/psi/impl/PyTypeProvider.java) |

### PythonPsiImpl.xml

[`PythonPsiImpl.xml`](upsource:///python/python-psi-impl/resources/META-INF/PythonPsiImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.customClassStubType](https://jb.gg/ipe?extensions=Pythonid.customClassStubType) ![Internal API][internal] | [`PyCustomClassStubType`](upsource:///python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomClassStubType.java) |
| [Pythonid.customTargetExpressionStubType](https://jb.gg/ipe?extensions=Pythonid.customTargetExpressionStubType) ![Internal API][internal] | [`CustomTargetExpressionStubType`](upsource:///python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/CustomTargetExpressionStubType.java) |
| [Pythonid.decoratorIndexer](https://jb.gg/ipe?extensions=Pythonid.decoratorIndexer) ![Experimental API][experimental] | [`PyCustomDecoratorIndexer`](upsource:///python/python-psi-impl/src/com/jetbrains/python/PyCustomDecoratorIndexer.kt) |
| [Pythonid.importCandidateProvider](https://jb.gg/ipe?extensions=Pythonid.importCandidateProvider) | [`PyImportCandidateProvider`](upsource:///python/python-psi-impl/src/com/jetbrains/python/codeInsight/imports/PyImportCandidateProvider.java) |
| [Pythonid.pyDataclassParametersProvider](https://jb.gg/ipe?extensions=Pythonid.pyDataclassParametersProvider) | [`PyDataclassParametersProvider`](upsource:///python/python-psi-impl/src/com/jetbrains/python/codeInsight/PyDataclasses.kt) |
| [Pythonid.pyReferenceCustomTargetChecker](https://jb.gg/ipe?extensions=Pythonid.pyReferenceCustomTargetChecker) | [`PyReferenceCustomTargetChecker`](upsource:///python/python-psi-impl/src/com/jetbrains/python/psi/impl/references/PyReferenceCustomTargetChecker.kt) |
| [Pythonid.pyiStubSuppressor](https://jb.gg/ipe?extensions=Pythonid.pyiStubSuppressor) ![Experimental API][experimental] | [`PyiStubSuppressor`](upsource:///python/python-psi-impl/src/com/jetbrains/python/pyi/PyiStubSuppressor.java) |
| [Pythonid.typeCheckerExtension](https://jb.gg/ipe?extensions=Pythonid.typeCheckerExtension) ![Experimental API][experimental] | [`PyTypeCheckerExtension`](upsource:///python/python-psi-impl/src/com/jetbrains/python/psi/types/PyTypeCheckerExtension.java) |
| [Pythonid.visitorFilter](https://jb.gg/ipe?extensions=Pythonid.visitorFilter) | [`PythonVisitorFilter`](upsource:///python/python-psi-impl/src/com/jetbrains/python/psi/PythonVisitorFilter.java) |

### PythonSdk.xml

[`PythonSdk.xml`](upsource:///python/python-sdk/resources/META-INF/PythonSdk.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.projectSdkConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.projectSdkConfigurationExtension) ![Experimental API][experimental] | [`PyProjectSdkConfigurationExtension`](upsource:///python/python-sdk/src/com/jetbrains/python/sdk/configuration/PyProjectSdkConfigurationExtension.kt) |
| [Pythonid.pythonFlavorProvider](https://jb.gg/ipe?extensions=Pythonid.pythonFlavorProvider) | [`PythonFlavorProvider`](upsource:///python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonFlavorProvider.java) |
| [Pythonid.pythonSdkFlavor](https://jb.gg/ipe?extensions=Pythonid.pythonSdkFlavor) | [`PythonSdkFlavor`](upsource:///python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonSdkFlavor.java) |

### refactorings.xml

[`refactorings.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/refactorings.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.additionalExtractableAnalyser](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.additionalExtractableAnalyser) ![Non-Dynamic][non-dynamic] | [`AdditionalExtractableAnalyser`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/AdditionalExtractableAnalyser.kt) |
| [org.jetbrains.kotlin.foreignUsagesRenameProcessor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.foreignUsagesRenameProcessor) | [`ForeignUsagesRenameProcessor`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/rename/ForeignUsagesRenameProcessor.kt) |

### resolution.xml

[`resolution.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/resolution.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.declarationAttributeAltererExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.declarationAttributeAltererExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DeclarationAttributeAltererExtension` |
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) | [`KotlinResolveScopeEnlarger`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/caches/resolve/util/scopeUtils.kt) |
| [org.jetbrains.kotlin.syntheticScopeProviderExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.syntheticScopeProviderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `SyntheticScopeProviderExtension` |

### rest.xml

[`rest.xml`](upsource:///python/rest/resources/META-INF/rest.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [restructured.text.html.preview.provider](https://jb.gg/ipe?extensions=restructured.text.html.preview.provider) | [`RestPreviewProvider`](upsource:///python/rest/src/com/jetbrains/rest/editor/RestPreviewProvider.java) |

### scripting-support.xml

[`scripting-support.xml`](upsource:///plugins/kotlin/plugin/resources/META-INF/scripting-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.scratchFileLanguageProvider](https://jb.gg/ipe?extensions=com.intellij.scratchFileLanguageProvider) ![Non-Dynamic][non-dynamic] | [`ScratchFileLanguageProvider`](upsource:///plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| [org.jetbrains.kotlin.ideScriptConfigurationControlFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ideScriptConfigurationControlFacade) ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](upsource:///plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| [org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](upsource:///plugins/kotlin/core/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsProvider) ![Project-Level][project-level] | [`ScriptDefinitionsProvider`](upsource:///plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptDefinitionProvider.kt) |
| [org.jetbrains.kotlin.scriptDiagnosticFixProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDiagnosticFixProvider) | [`ScriptDiagnosticFixProvider`](upsource:///plugins/kotlin/analysis/src/org/jetbrains/kotlin/idea/highlighter/ScriptDiagnosticFixProvider.kt) |
| [org.jetbrains.kotlin.scripting.idea.listener](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.listener) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptChangeListener`](upsource:///plugins/kotlin/core/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| [org.jetbrains.kotlin.scripting.idea.loader](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.loader) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](upsource:///plugins/kotlin/core/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| [org.jetbrains.kotlin.scripting.idea.scriptingSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.scriptingSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](upsource:///plugins/kotlin/core/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| [org.jetbrains.kotlin.scripting.idea.settings.provider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.settings.provider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### tanvd.grazi

[`tanvd.grazi`](upsource:///plugins/grazie/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.grazie.disableChecking](https://jb.gg/ipe?extensions=com.intellij.grazie.disableChecking) | `n/a` |
| [com.intellij.grazie.grammar.strategy](https://jb.gg/ipe?extensions=com.intellij.grazie.grammar.strategy) ![Deprecated][deprecated] | [`GrammarCheckingStrategy`](upsource:///plugins/grazie/src/main/kotlin/com/intellij/grazie/grammar/strategy/GrammarCheckingStrategy.kt) |
| [com.intellij.grazie.problemFilter](https://jb.gg/ipe?extensions=com.intellij.grazie.problemFilter) | [`ProblemFilter`](upsource:///plugins/grazie/src/main/kotlin/com/intellij/grazie/text/ProblemFilter.java) |
| [com.intellij.grazie.textChecker](https://jb.gg/ipe?extensions=com.intellij.grazie.textChecker) | [`TextChecker`](upsource:///plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextChecker.java) |
| [com.intellij.grazie.textExtractor](https://jb.gg/ipe?extensions=com.intellij.grazie.textExtractor) | [`TextExtractor`](upsource:///plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextExtractor.java) |

### terminal.xml

[`terminal.xml`](upsource:///plugins/terminal/resources/META-INF/terminal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.terminal.shellCommandHandler](https://jb.gg/ipe?extensions=com.intellij.terminal.shellCommandHandler) | [`TerminalShellCommandHandler`](upsource:///platform/execution-impl/src/com/intellij/terminal/TerminalShellCommandHandler.kt) |
| [org.jetbrains.plugins.terminal.localTerminalCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.localTerminalCustomizer) | [`LocalTerminalCustomizer`](upsource:///plugins/terminal/src/org/jetbrains/plugins/terminal/LocalTerminalCustomizer.java) |
| [org.jetbrains.plugins.terminal.openPredefinedTerminalProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.openPredefinedTerminalProvider) | [`OpenPredefinedTerminalActionProvider`](upsource:///plugins/terminal/src/org/jetbrains/plugins/terminal/ui/OpenPredefinedTerminalActionProvider.kt) |

### TestNG-J

[`TestNG-J`](upsource:///plugins/testng/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.theoryinpractice.testng.listener](https://jb.gg/ipe?extensions=com.theoryinpractice.testng.listener) | [`IDEATestNGListener`](upsource:///plugins/testng_rt/src/com/intellij/rt/testng/IDEATestNGListener.java) |

### training

[`training`](upsource:///plugins/ide-features-trainer/res/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [training.ifs.suggester](https://jb.gg/ipe?extensions=training.ifs.suggester) | [`FeatureSuggester`](upsource:///plugins/ide-features-trainer/src/training/featuresSuggester/suggesters/FeatureSuggester.kt) |
| [training.ifs.suggesterSupport](https://jb.gg/ipe?extensions=training.ifs.suggesterSupport) | [`SuggesterSupport`](upsource:///plugins/ide-features-trainer/src/training/featuresSuggester/SuggesterSupport.kt) |
| [training.ift.language.extension](https://jb.gg/ipe?extensions=training.ift.language.extension) | [`LangSupport`](upsource:///plugins/ide-features-trainer/src/training/lang/LangSupport.kt) |
| [training.ift.learning.commonCourse](https://jb.gg/ipe?extensions=training.ift.learning.commonCourse) | [`LearningCourse`](upsource:///plugins/ide-features-trainer/src/training/learn/course/LearningCourse.kt) |
| [training.ift.learning.course](https://jb.gg/ipe?extensions=training.ift.learning.course) | [`LearningCourseBase`](upsource:///plugins/ide-features-trainer/src/training/learn/course/LearningCourseBase.kt) |

### XPathView

[`XPathView`](upsource:///plugins/xpath/xpath-view/src/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [XPathView.xpath.contextProviderExtension](https://jb.gg/ipe?extensions=XPathView.xpath.contextProviderExtension) | [`ContextProviderExtension`](upsource:///plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/ContextProviderExtension.java) |
| [XPathView.xpath.functionProvider](https://jb.gg/ipe?extensions=XPathView.xpath.functionProvider) | [`XPathFunctionProvider`](upsource:///plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/functions/XPathFunctionProvider.java) |
| [XPathView.xsltRunnerExtension](https://jb.gg/ipe?extensions=XPathView.xsltRunnerExtension) | [`XsltRunnerExtension`](upsource:///plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/xslt/run/XsltRunnerExtension.java) |



## Android Plugin


### Android Plugin - Listeners

| Topic | Listener |
|-------|----------|
| [StatefulButtonNotifier#BUTTON_STATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.StatefulButtonNotifier)  ![Project-Level][project-level] | [`StatefulButtonNotifier`](upsource:///android/assistant/src/com/android/tools/idea/assistant/StatefulButtonNotifier.java) |
| [AvdLaunchListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.avdmanager.AvdLaunchListener)  | [`AvdLaunchListener`](upsource:///android/android/src/com/android/tools/idea/avdmanager/AvdLaunchListener.java) |
| [LiveLiteralsService#DOCUMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.DocumentsUpdatedListener)  | [`DocumentsUpdatedListener`](upsource:///android/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsService#MANAGED_ELEMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.ManagedElementsUpdatedListener)  | [`ManagedElementsUpdatedListener`](upsource:///android/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsDeploymentReportService#LITERALS_DEPLOYED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.internal.LiveLiteralsDeploymentReportService.Listener)  ![Project-Level][project-level] | [`Listener`](upsource:///android/android/src/com/android/tools/idea/editors/literals/internal/LiveLiteralsDeploymentReportService.kt) |
| [EmulatorSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.emulator.EmulatorSettingsListener)  | [`EmulatorSettingsListener`](upsource:///android/android/src/com/android/tools/idea/emulator/EmulatorSettingsListener.java) |
| [GradleBuildState#GRADLE_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.build.GradleBuildListener)  | [`GradleBuildListener`](upsource:///android/android/src/com/android/tools/idea/gradle/project/build/GradleBuildListener.java) |
| [GradleSyncState#GRADLE_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.sync.GradleSyncListener)  | [`GradleSyncListener`](upsource:///android/android/src/com/android/tools/idea/gradle/project/sync/GradleSyncListener.java) |
| [MultiTemplateRenderer#TEMPLATE_RENDERER_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.npw.model.MultiTemplateRenderer.TemplateRendererListener)  | [`TemplateRendererListener`](upsource:///android/android/src/com/android/tools/idea/npw/model/MultiTemplateRenderer.kt) |
| [GradleProjectSystemBuildPublisher#PROJECT_SYSTEM_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemBuildManager.BuildListener)  | [`BuildListener`](upsource:///android/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemBuildManager.kt) |
| [ProjectSystemSyncUtil#PROJECT_SYSTEM_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemSyncManager.SyncResultListener)  | [`SyncResultListener`](upsource:///android/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemSyncManager.kt) |
| [AppDeploymentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.AppDeploymentListener)  | [`AppDeploymentListener`](upsource:///android/android/src/com/android/tools/idea/run/AppDeploymentListener.java) |
| [TransportDeviceManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.transport.TransportDeviceManager.TransportDeviceManagerListener)  | [`TransportDeviceManagerListener`](upsource:///android/android-transport/src/com/android/tools/idea/transport/TransportDeviceManager.java) |
| [ResourceFolderManager#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.android.facet.ResourceFolderManager.ResourceFolderListener)  ![Project-Level][project-level] | [`ResourceFolderListener`](upsource:///android/android/src/org/jetbrains/android/facet/ResourceFolderManager.kt) |


### android-kotlin-extensions-common.xml

[`android-kotlin-extensions-common.xml`](upsource:///android/android-kotlin/android-extensions-idea-common/src/META-INF/android-kotlin-extensions-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.android.model.androidModuleInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.android.model.androidModuleInfoProvider) ![Non-Dynamic][non-dynamic] | [`AndroidModuleInfoProvider`](upsource:///android/android-kotlin/android-extensions-idea-common/src/org/jetbrains/kotlin/android/model/AndroidModuleInfoProvider.kt) |

### android-kotlin.xml

[`android-kotlin.xml`](upsource:///android/android/src/META-INF/android-kotlin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.androidDexer](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.androidDexer) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AndroidDexer`](upsource:///plugins/kotlin/jvm-debugger/evaluation/src/org/jetbrains/kotlin/idea/debugger/evaluate/classLoading/AndroidDexer.kt) |

### android-lang.xml

[`android-lang.xml`](upsource:///android/android-lang/src/META-INF/android-lang.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.lang.androidSql.contextProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.androidSql.contextProvider) ![Non-Dynamic][non-dynamic] | [`Provider`](upsource:///android/android-lang/src/com/android/tools/idea/lang/androidSql/AndroidSqlContext.kt) |

### android-plugin.xml

[`android-plugin.xml`](upsource:///android/android/src/META-INF/android-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.captureType](https://jb.gg/ipe?extensions=com.android.captureType) ![Non-Dynamic][non-dynamic] | [`CaptureType`](upsource:///android/android/src/com/android/tools/idea/profiling/capture/CaptureType.java) |
| [com.android.customProjectTypeImporter](https://jb.gg/ipe?extensions=com.android.customProjectTypeImporter) ![Non-Dynamic][non-dynamic] | [`CustomProjectTypeImporter`](upsource:///android/android/src/com/android/tools/idea/project/CustomProjectTypeImporter.java) |
| [com.android.gradle.sync.postSyncProjectCleanupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectCleanupStep) ![Non-Dynamic][non-dynamic] | [`ProjectCleanupStep`](upsource:///android/android/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectCleanupStep.java) |
| [com.android.gradle.sync.postSyncProjectSetupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectSetupStep) ![Non-Dynamic][non-dynamic] | [`ProjectSetupStep`](upsource:///android/android/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectSetupStep.java) |
| [com.android.ide.androidConfigurableContributor](https://jb.gg/ipe?extensions=com.android.ide.androidConfigurableContributor) ![Non-Dynamic][non-dynamic] | [`AndroidConfigurableContributor`](upsource:///android/android/src/com/android/tools/idea/structure/dialog/AndroidConfigurableContributor.kt) |
| [com.android.ide.moduleStructureConfigurableContributor](https://jb.gg/ipe?extensions=com.android.ide.moduleStructureConfigurableContributor) ![Non-Dynamic][non-dynamic] | [`ModuleStructureConfigurableContributor`](upsource:///android/android/src/com/android/tools/idea/structure/dialog/ModuleStructureConfigurableContributor.java) |
| [com.android.ide.projectStructureItemsContributor](https://jb.gg/ipe?extensions=com.android.ide.projectStructureItemsContributor) ![Non-Dynamic][non-dynamic] | [`ProjectStructureItemsContributor`](upsource:///android/android/src/com/android/tools/idea/structure/dialog/ProjectStructureItemsContributor.java) |
| [com.android.ide.sdkEventListener](https://jb.gg/ipe?extensions=com.android.ide.sdkEventListener) ![Non-Dynamic][non-dynamic] | [`AndroidSdkEventListener`](upsource:///android/android/src/com/android/tools/idea/sdk/IdeSdks.java) |
| [com.android.logcat.filterProvider](https://jb.gg/ipe?extensions=com.android.logcat.filterProvider) ![Non-Dynamic][non-dynamic] | [`LogcatFilterProvider`](upsource:///android/android/src/com/android/tools/idea/logcat/LogcatFilterProvider.java) |
| [com.android.moduleDescriptionProvider](https://jb.gg/ipe?extensions=com.android.moduleDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`ModuleDescriptionProvider`](upsource:///android/android/src/com/android/tools/idea/npw/module/ModuleDescriptionProvider.kt) |
| [com.android.moduleImporter](https://jb.gg/ipe?extensions=com.android.moduleImporter) ![Non-Dynamic][non-dynamic] | [`AndroidModuleImporter`](upsource:///android/android/src/com/android/tools/idea/gradle/project/AndroidModuleImporter.kt) |
| [com.android.rendering.renderErrorContributor](https://jb.gg/ipe?extensions=com.android.rendering.renderErrorContributor) ![Non-Dynamic][non-dynamic] | [`Provider`](upsource:///android/android/src/com/android/tools/idea/rendering/RenderErrorContributor.java) |
| [com.android.rendering.renderSecurityManagerOverrides](https://jb.gg/ipe?extensions=com.android.rendering.renderSecurityManagerOverrides) | [`RenderSecurityManagerOverrides`](upsource:///android/android/src/com/android/tools/idea/rendering/RenderSecurityManagerOverrides.kt) |
| [com.android.run.androidDebugger](https://jb.gg/ipe?extensions=com.android.run.androidDebugger) ![Non-Dynamic][non-dynamic] | [`AndroidDebugger`](upsource:///android/android/src/com/android/tools/idea/run/editor/AndroidDebugger.java) |
| [com.android.run.androidLaunchTaskContributor](https://jb.gg/ipe?extensions=com.android.run.androidLaunchTaskContributor) ![Non-Dynamic][non-dynamic] | [`AndroidLaunchTaskContributor`](upsource:///android/android/src/com/android/tools/idea/run/AndroidLaunchTaskContributor.java) |
| [com.android.run.deployTargetProvider](https://jb.gg/ipe?extensions=com.android.run.deployTargetProvider) ![Non-Dynamic][non-dynamic] | [`DeployTargetProvider`](upsource:///android/android/src/com/android/tools/idea/run/editor/DeployTargetProvider.java) |
| [com.android.run.deviceNameRenderer](https://jb.gg/ipe?extensions=com.android.run.deviceNameRenderer) ![Non-Dynamic][non-dynamic] | [`DeviceNameRendererEx`](upsource:///android/android/src/com/android/tools/idea/ddms/DeviceNameRendererEx.java) |
| [com.android.tools.gradleModelProvider](https://jb.gg/ipe?extensions=com.android.tools.gradleModelProvider) ![Non-Dynamic][non-dynamic] | [`GradleModelProvider`](upsource:///android/gradle-dsl/src/com/android/tools/idea/gradle/dsl/api/GradleModelProvider.java) |
| [com.android.tools.idea.analytics.ideBrandProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.analytics.ideBrandProvider) ![Non-Dynamic][non-dynamic] | [`IdeBrandProvider`](upsource:///android/android/src/com/android/tools/idea/analytics/IdeBrandProvider.kt) |
| [com.android.tools.idea.databinding.layoutBindingSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.databinding.layoutBindingSupport) ![Non-Dynamic][non-dynamic] | [`LayoutBindingSupport`](upsource:///android/android/src/com/android/tools/idea/databinding/LayoutBindingSupport.kt) |
| [com.android.tools.idea.lang.databinding.dataBindingCompletionSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.databinding.dataBindingCompletionSupport) ![Non-Dynamic][non-dynamic] | [`DataBindingCompletionSupport`](upsource:///android/android/src/com/android/tools/idea/lang/databinding/DataBindingCompletionSupport.kt) |
| [com.android.tools.idea.lang.databinding.dataBindingExpressionSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lang.databinding.dataBindingExpressionSupport) ![Non-Dynamic][non-dynamic] | [`DataBindingExpressionSupport`](upsource:///android/android/src/com/android/tools/idea/lang/databinding/DataBindingExpressionSupport.kt) |
| [com.android.tools.idea.ndk.nativeWorkspaceProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ndk.nativeWorkspaceProvider) ![Non-Dynamic][non-dynamic] | [`NativeWorkspaceProvider`](upsource:///android/android/src/com/android/tools/idea/ndk/NativeWorkspaceProvider.kt) |
| [com.android.tools.idea.run.editor.androidDebuggerInfoProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.run.editor.androidDebuggerInfoProvider) ![Non-Dynamic][non-dynamic] | [`AndroidDebuggerInfoProvider`](upsource:///android/android/src/com/android/tools/idea/run/editor/AndroidDebuggerInfoProvider.java) |
| [com.android.tools.idea.templates.additionalTemplateActionsProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.templates.additionalTemplateActionsProvider) ![Non-Dynamic][non-dynamic] | [`AdditionalTemplateActionsProvider`](upsource:///android/android/src/com/android/tools/idea/templates/AdditionalTemplateActionsProvider.java) |
| [com.android.tools.idea.ui.designer.overlays.overlayProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ui.designer.overlays.overlayProvider) ![Non-Dynamic][non-dynamic] | [`OverlayProvider`](upsource:///android/android/src/com/android/tools/idea/ui/designer/overlays/OverlayProvider.java) |
| [com.android.tools.idea.ui.guiTestingStatusProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.ui.guiTestingStatusProvider) ![Non-Dynamic][non-dynamic] | [`GuiTestingStatusProvider`](upsource:///android/android/src/com/android/tools/idea/ui/GuiTestingStatusProvider.java) |
| [com.android.tools.idea.wizard.template.wizardTemplateProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.wizard.template.wizardTemplateProvider) ![Non-Dynamic][non-dynamic] | `WizardTemplateProvider` |
| [org.jetbrains.android.actions.newResourceCreationHandler](https://jb.gg/ipe?extensions=org.jetbrains.android.actions.newResourceCreationHandler) ![Non-Dynamic][non-dynamic] | [`NewResourceCreationHandler`](upsource:///android/android/src/org/jetbrains/android/actions/NewResourceCreationHandler.java) |
| [org.jetbrains.android.uipreview.viewLoaderExtension](https://jb.gg/ipe?extensions=org.jetbrains.android.uipreview.viewLoaderExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewLoaderExtension`](upsource:///android/android/src/org/jetbrains/android/uipreview/ViewLoaderExtension.java) |

### app-inspector.xml

[`app-inspector.xml`](upsource:///android/app-inspection/inspector/ide/src/META-INF/app-inspector.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.appinspection.inspector.ide.appInspectorTabProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.appinspection.inspector.ide.appInspectorTabProvider) ![Non-Dynamic][non-dynamic] | [`AppInspectorTabProvider`](upsource:///android/app-inspection/inspector/ide/src/com/android/tools/idea/appinspection/inspector/ide/AppInspectorTabProvider.kt) |

### assistant.xml

[`assistant.xml`](upsource:///android/assistant/src/META-INF/assistant.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.assistant.actionHandler](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.actionHandler) ![Non-Dynamic][non-dynamic] | [`AssistActionHandler`](upsource:///android/assistant/src/com/android/tools/idea/assistant/AssistActionHandler.java) |
| [com.android.tools.idea.assistant.actionStateManager](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.actionStateManager) ![Non-Dynamic][non-dynamic] | [`AssistActionStateManager`](upsource:///android/assistant/src/com/android/tools/idea/assistant/AssistActionStateManager.java) |
| [com.android.tools.idea.assistant.assistantBundleCreator](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.assistantBundleCreator) ![Non-Dynamic][non-dynamic] | [`AssistantBundleCreator`](upsource:///android/assistant/src/com/android/tools/idea/assistant/AssistantBundleCreator.java) |
| [com.android.tools.idea.assistant.navlistener](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.navlistener) ![Non-Dynamic][non-dynamic] | [`AssistNavListener`](upsource:///android/assistant/src/com/android/tools/idea/assistant/AssistNavListener.java) |
| [com.android.tools.idea.assistant.panelFactory](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.panelFactory) ![Non-Dynamic][non-dynamic] | [`PanelFactory`](upsource:///android/assistant/src/com/android/tools/idea/assistant/PanelFactory.java) |
| [com.android.tools.idea.assistant.scrollHandler](https://jb.gg/ipe?extensions=com.android.tools.idea.assistant.scrollHandler) ![Non-Dynamic][non-dynamic] | [`ScrollHandler`](upsource:///android/assistant/src/com/android/tools/idea/assistant/ScrollHandler.java) |

### compose-designer.xml

[`compose-designer.xml`](upsource:///android/compose-designer/src/META-INF/compose-designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.compose.preview.composeEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.compose.preview.composeEditorNotificationProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`Provider`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotifications.java) |

### customview.xml

[`customview.xml`](upsource:///android/designer/customview/src/META-INF/customview.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.customview.preview.customViewEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.customview.preview.customViewEditorNotificationProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`Provider`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotifications.java) |

### designer.xml

[`designer.xml`](upsource:///android/designer/src/META-INF/designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider) ![Non-Dynamic][non-dynamic] | [`PreviewRepresentationProvider`](upsource:///android/designer/src/com/android/tools/idea/uibuilder/editor/multirepresentation/PreviewRepresentationProvider.kt) |
| [com.android.tools.idea.uibuilder.handlers.viewHandlerProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.handlers.viewHandlerProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewHandlerProvider`](upsource:///android/designer/src/com/android/tools/idea/uibuilder/handlers/ViewHandlerProvider.kt) |

### lint-plugin.xml

[`lint-plugin.xml`](upsource:///android/lint/src/META-INF/lint-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.lint.common.lintIdeSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintIdeSupport) ![Non-Dynamic][non-dynamic] | [`LintIdeSupport`](upsource:///android/lint/src/com/android/tools/idea/lint/common/LintIdeSupport.kt) |
| [com.android.tools.idea.lint.common.lintQuickFixProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintQuickFixProvider) ![Non-Dynamic][non-dynamic] | [`LintIdeQuickFixProvider`](upsource:///android/lint/src/com/android/tools/idea/lint/common/LintIdeQuickFixProvider.java) |

### org.jetbrains.idea.gradle.dsl

[`org.jetbrains.idea.gradle.dsl`](upsource:///android/gradle-dsl/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.gradle.dsl.blockModel](https://jb.gg/ipe?extensions=org.jetbrains.idea.gradle.dsl.blockModel) ![Experimental API][experimental] | [`BlockModelProvider`](upsource:///android/gradle-dsl/src/com/android/tools/idea/gradle/dsl/model/GradleBlockModelMap.java) |
| [org.jetbrains.idea.gradle.dsl.parserFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.gradle.dsl.parserFactory) ![Internal API][internal] | [`GradleDslConverterFactory`](upsource:///android/gradle-dsl/src/com/android/tools/idea/gradle/dsl/parser/GradleDslConverterFactory.java) |

### project-system-plugin.xml

[`project-system-plugin.xml`](upsource:///android/project-system/src/META-INF/project-system-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.androidStartupActivity](https://jb.gg/ipe?extensions=com.android.androidStartupActivity) | [`AndroidStartupActivity`](upsource:///android/project-system/src/com/android/tools/idea/AndroidStartupActivity.kt) |
| [com.android.project.projectsystem](https://jb.gg/ipe?extensions=com.android.project.projectsystem) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AndroidProjectSystemProvider`](upsource:///android/project-system/src/com/android/tools/idea/projectsystem/AndroidProjectSystemProvider.kt) |

### resources-explorer.xml

[`resources-explorer.xml`](upsource:///android/android/src/com/android/tools/idea/ui/resourcemanager/META-INF/resources-explorer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.resourceImporter](https://jb.gg/ipe?extensions=com.android.resourceImporter) ![Non-Dynamic][non-dynamic] | [`ResourceImporter`](upsource:///android/android/src/com/android/tools/idea/ui/resourcemanager/plugin/ResourceImporter.kt) |
| [com.android.resourceViewer](https://jb.gg/ipe?extensions=com.android.resourceViewer) ![Non-Dynamic][non-dynamic] | [`DesignAssetRenderer`](upsource:///android/android/src/com/android/tools/idea/ui/resourcemanager/plugin/DesignAssetRenderer.kt) |

