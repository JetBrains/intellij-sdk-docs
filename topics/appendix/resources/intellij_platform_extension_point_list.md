<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!--
EP List Directory:
/community/
EXCLUDING:
/community/android
/community/java
/community/json
/community/jvm
/community/plugins
/community/python

Note: if section 'IntelliJ Community Plugins" appears on this page, add excluded directories in the list above
and add in intellij_community_plugins_extension_point_list.md.
-->

# IntelliJ Platform Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for IntelliJ Platform.</link-summary>

1169 Extension Points and 224 Listeners for IntelliJ Platform %ijPlatform%

<include from="snippets.topic" element-id="ep_list_legend"/>

## IntelliJ Platform


### IntelliJ Platform – Listeners

| Topic | Listener |
|-------|----------|
| [ProblemsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.analysis.problemsView.ProblemsListener)  ![Project-Level][project-level] | [`ProblemsListener`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/problemsView/ProblemsListener.kt) |
| [RainbowStateChangeListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.colors.RainbowStateChangeListener)  | [`RainbowStateChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/RainbowStateChangeListener.kt) |
| [EditorOptionsListener#FOLDING_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#APPEARANCE_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#OPTIONS_PANEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#SMART_KEYS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [EditorOptionsListener#GUTTER_ICONS_CONFIGURABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [ReaderModeSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.actions.ReaderModeListener)  ![Project-Level][project-level] | [`ReaderModeListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/ReaderModeListener.kt) |
| [CodeVisionSettings#CODE_LENS_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.codeVision.settings.CodeVisionSettings.CodeVisionSettingsListener)  | [`CodeVisionSettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettings.kt) |
| [CompletionContributorListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionContributorListener)  ![Internal][internal] | [`CompletionContributorListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionContributorListener.kt) |
| [CompletionPhaseListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionPhaseListener)  | [`CompletionPhaseListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPhaseListener.java) |
| [DaemonCodeAnalyzer#DAEMON_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.DaemonCodeAnalyzer.DaemonListener)  ![Project-Level][project-level] | [`DaemonListener`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java) |
| [EditorTrackerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.EditorTrackerListener)  ![Project-Level][project-level] | [`EditorTrackerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/EditorTracker.kt) |
| [FileHighlightingSettingListener#SETTING_CHANGE](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSettingListener)  | [`FileHighlightingSettingListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/FileHighlightingSettingListener.java) |
| [DocRenderItemManagerImpl#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.documentation.render.DocRenderItemManagerImpl.Listener)  | [`Listener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/render/DocRenderItemManagerImpl.kt) |
| [EditorHintListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.EditorHintListener)  | [`EditorHintListener`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/hint/EditorHintListener.java) |
| [ExternalParameterInfoChangesProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.ExternalParameterInfoChangesProvider)  | [`ExternalParameterInfoChangesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ExternalParameterInfoChangesProvider.java) |
| [InlayHintsSettings#INLAY_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hints.InlayHintsSettings.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSettings.kt) |
| [InlineCompletionInstallListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.inline.completion.InlineCompletionInstallListener)  ![Experimental][experimental] | [`InlineCompletionInstallListener`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionInstallListener.kt) |
| [LookupManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.lookup.LookupManagerListener)  ![Project-Level][project-level] | [`LookupManagerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupManagerListener.java) |
| [CodeInsightContextManager.Companion#topic](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.multiverse.CodeInsightContextChangeListener)  | [`CodeInsightContextChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextManager.kt) |
| [EditorContextManager.Companion#topic](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.multiverse.EditorContextManager.ChangeEventListener)  | [`ChangeEventListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/multiverse/EditorContextManager.kt) |
| [TemplateManager#TEMPLATE_STARTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.template.TemplateManagerListener)  ![Project-Level][project-level] | [`TemplateManagerListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/TemplateManagerListener.java) |
| [GlobalInspectionContextEx#INSPECT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInspection.ex.InspectListener)  ![Internal][internal] ![Project-Level][project-level] | [`InspectListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/ex/InspectListener.java) |
| [BatchUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.configurationStore.BatchUpdateListener)  ![Project-Level][project-level] | [`BatchUpdateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/configurationStore/BatchUpdateListener.java) |
| [PasswordSafeSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.credentialStore.PasswordSafeSettingsListener)  | [`PasswordSafeSettingsListener`](%gh-ic%/platform/credential-store-impl/src/credentialStore/PasswordSafeSettingsListener.java) |
| [CsvFormatsSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.csv.CsvFormatsSettings.Listener)  | [`Listener`](%gh-ic%/grid/csv/src/csv/CsvFormatsSettings.java) |
| [DataGrid#ACTIVE_GRID_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGrid.ActiveGridListener)  | [`ActiveGridListener`](%gh-ic%/grid/impl/src/datagrid/DataGrid.java) |
| [DataGridListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGridListener)  | [`DataGridListener`](%gh-ic%/grid/impl/src/datagrid/DataGridListener.java) |
| [DataGridAppearanceSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridAppearanceSettings.Listener)  | [`Listener`](%gh-ic%/grid/core-impl/src/settings/DataGridAppearanceSettings.java) |
| [DataGridSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridSettings.Listener)  | [`Listener`](%gh-ic%/grid/core-impl/src/settings/DataGridSettings.java) |
| [IdePerformanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.IdePerformanceListener)  ![Internal][internal] | [`IdePerformanceListener`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/IdePerformanceListener.kt) |
| [RunnablesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.RunnablesListener)  ![Experimental][experimental] ![Internal][internal] | [`RunnablesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/RunnablesListener.java) |
| [ExecutionManager#EXECUTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionListener)  ![Project-Level][project-level] | [`ExecutionListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionListener.java) |
| [ExecutionTargetManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionTargetListener)  | [`ExecutionTargetListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionTargetListener.java) |
| [RunManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.RunManagerListener)  ![Project-Level][project-level] | [`RunManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/execution/RunManagerListener.java) |
| [RunDashboardManager#DASHBOARD_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.dashboard.RunDashboardListener)  | [`RunDashboardListener`](%gh-ic%/platform/execution/src/com/intellij/execution/dashboard/RunDashboardListener.java) |
| [ExecutionEventsBus#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.multilaunch.execution.messaging.ExecutionNotifier)  | [`ExecutionNotifier`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/messaging/ExecutionNotifier.kt) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.process.elevation.settings.ElevationSettings.Listener)  | [`Listener`](%gh-ic%/platform/execution-process-elevation/src/com/intellij/execution/process/elevation/settings/ElevationSettings.kt) |
| [RunToolbarSlotManager#RUN_TOOLBAR_SLOT_CONFIGURATION_MAP_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.runToolbar.data.RWSlotsConfigurationListener)  ![Project-Level][project-level] | [`RWSlotsConfigurationListener`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/runToolbar/data/RWSlotsConfigurationListener.kt) |
| [ServiceEventListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.services.ServiceEventListener)  | [`ServiceEventListener`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceEventListener.java) |
| [AutoTestListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.autotest.AutoTestListener)  ![Internal][internal] ![Project-Level][project-level] | [`AutoTestListener`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/autotest/AutoTestListener.kt) |
| [SMTRunnerEventsListener#TEST_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener)  ![Project-Level][project-level] | [`SMTRunnerEventsListener`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/SMTRunnerEventsListener.java) |
| [RunConfigurationStartHistory#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunConfigurationStartHistory.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ui/RunToolbarPopup.kt) |
| [RunContentManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunContentWithExecutorListener)  | [`RunContentWithExecutorListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ui/RunContentWithExecutorListener.java) |
| [FacetManager#FACETS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.facet.FacetManagerListener)  ![Project-Level][project-level] | [`FacetManagerListener`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetManagerListener.java) |
| [FeatureStatisticsUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeatureStatisticsUpdateListener)  ![Internal][internal] | [`FeatureStatisticsUpdateListener`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/FeatureStatisticsUpdateListener.kt) |
| [FeaturesRegistryListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeaturesRegistryListener)  ![Experimental][experimental] | [`FeaturesRegistryListener`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/FeaturesRegistryListener.java) |
| [FindManager#FIND_MODEL_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.find.FindModelListener)  ![Project-Level][project-level] | [`FindModelListener`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindModelListener.java) |
| [AppLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.AppLifecycleListener)  | [`AppLifecycleListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/AppLifecycleListener.java) |
| [FrameStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.FrameStateListener)  | [`FrameStateListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/FrameStateListener.java) |
| [PowerSaveMode#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.PowerSaveMode.Listener)  | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/ide/PowerSaveMode.java) |
| [RecentProjectsManager.Companion#RECENT_PROJECTS_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.RecentProjectsManager.RecentProjectsChange)  | [`RecentProjectsChange`](%gh-ic%/platform/ide-core/src/com/intellij/ide/RecentProjectsManager.kt) |
| [RegionSettingsListener#UPDATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.RegionSettings.RegionSettingsListener)  ![Experimental][experimental] ![Internal][internal] | [`RegionSettingsListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/RegionSettings.java) |
| [SaveAndSyncHandlerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.SaveAndSyncHandlerListener)  ![Experimental][experimental] | [`SaveAndSyncHandlerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SaveAndSyncHandlerListener.java) |
| [SearchEverywhereUI#PREVIEW_EVENTS](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.PreviewListener)  ![Internal][internal] | [`PreviewListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/PreviewListener.java) |
| [SEHeaderActionListener.Companion#SE_HEADER_ACTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SEHeaderActionListener)  ![Internal][internal] | [`SEHeaderActionListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEHeaderActionListener.kt) |
| [SETabSwitcherListener.Companion#SE_TAB_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SETabSwitcherListener)  ![Internal][internal] | [`SETabSwitcherListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SETabSwitcherListener.kt) |
| [SearchEverywhereUI#SEARCH_EVENTS](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SearchListener)  | [`SearchListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmark.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListener.java) |
| [BookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmarks.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/bookmarks/src/com/intellij/ide/bookmarks/BookmarksListener.java) |
| [BatchFileChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.file.BatchFileChangeListener)  | [`BatchFileChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/file/BatchFileChangeListener.java) |
| [DataSharingSettingsChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.gdpr.DataSharingSettingsChangeListener)  ![Internal][internal] | [`DataSharingSettingsChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/gdpr/DataSharingSettingsChangeListener.kt) |
| [TrustStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.impl.TrustStateListener)  ![Deprecated][deprecated] | [`TrustStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) |
| [LightEditServiceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.lightEdit.LightEditServiceListener)  ![Experimental][experimental] | [`LightEditServiceListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditServiceListener.java) |
| [RiderMainToolbarStateListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.rider.RiderMainToolbarStateListener)  ![Project-Level][project-level] | [`RiderMainToolbarStateListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/rider/RiderMainToolbarRootPaneExtension.kt) |
| [DynamicPluginListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.DynamicPluginListener)  | [`DynamicPluginListener`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginListener.kt) |
| [PluginRepositoryAuthListener#PLUGIN_REPO_AUTH_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.auth.PluginRepositoryAuthListener)  | [`PluginRepositoryAuthListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthListener.java) |
| [ProjectViewSelectionTopicKt#PROJECT_VIEW_SELECTION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.ProjectViewSelectionListener)  ![Project-Level][project-level] | [`ProjectViewSelectionListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/projectView/ProjectViewSelectionTopic.kt) |
| [ProjectViewListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.impl.ProjectViewListener)  ![Project-Level][project-level] | [`ProjectViewListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewListener.java) |
| [TrustedProjectsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.trustedProjects.TrustedProjectsListener)  ![Experimental][experimental] | [`TrustedProjectsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/trustedProjects/TrustedProjectsListener.kt) |
| [LafManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.LafManagerListener)  | [`LafManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/LafManagerListener.java) |
| [UISettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.UISettingsListener)  | [`UISettingsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/UISettingsListener.java) |
| [VirtualFileAppearanceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.VirtualFileAppearanceListener)  | [`VirtualFileAppearanceListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/VirtualFileAppearanceListener.java) |
| [CustomActionsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.customization.CustomActionsListener)  | [`CustomActionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomActionsListener.kt) |
| [ComponentHighlightingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.search.ComponentHighlightingListener)  | [`ComponentHighlightingListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/ComponentHighlightingListener.java) |
| [FileStructurePopupListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.FileStructurePopupListener)  ![Experimental][experimental] ![Internal][internal] ![Project-Level][project-level] | [`FileStructurePopupListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/FileStructurePopupListener.java) |
| [SettingsChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.scopeChooser.ScopeEditorPanel.SettingsChangedListener)  ![Project-Level][project-level] | [`SettingsChangedListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeEditorPanel.java) |
| [EventLogConfigOptionsService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.internal.statistic.eventLog.EventLogConfigOptionsListener)  | [`EventLogConfigOptionsListener`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogConfigOptionsListener.java) |
| [ExternalResourceListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.javaee.ExternalResourceListener)  | [`ExternalResourceListener`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceListener.java) |
| [LocalizationListener.Companion#UPDATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.l10n.LocalizationListener)  ![Internal][internal] | [`LocalizationListener`](%gh-ic%/platform/core-api/src/com/intellij/l10n/LocalizationListener.kt) |
| [DocumentationPopupListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.documentation.ide.impl.DocumentationPopupListener)  ![Project-Level][project-level] | [`DocumentationPopupListener`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/ide/impl/DocumentationPopupListener.kt) |
| [EndpointsChangeTracker.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsChangeTracker)  ![Project-Level][project-level] | [`EndpointsChangeTracker`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsChangeTracker.kt) |
| [EndpointsViewListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsViewListener)  ![Project-Level][project-level] | [`EndpointsViewListener`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsViewListener.kt) |
| [EndpointsViewOpener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsViewOpener)  ![Project-Level][project-level] | [`EndpointsViewOpener`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsViewOpener.kt) |
| [NotebookEditorModeKt#NOTEBOOK_EDITOR_MODE](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.ui.editor.actions.command.mode.NotebookEditorModeListener)  | [`NotebookEditorModeListener`](%gh-ic%/notebooks/notebook-ui/src/com/intellij/notebooks/ui/editor/actions/command/mode/NotebookEditorMode.kt) |
| [ChangeListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.visualization.NotebookIntervalPointerFactory.ChangeListener)  | [`ChangeListener`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookIntervalPointer.kt) |
| [JupyterCellSelectionNotifier.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.visualization.ui.JupyterCellSelectionNotifier)  | [`JupyterCellSelectionNotifier`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/ui/JupyterCellSelectionNotifier.kt) |
| [ActionCenter#MODEL_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.notification.ActionCenter.EventListener)  | [`EventListener`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/ActionCenter.java) |
| [Notifications#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.notification.Notifications)  ![Project-Level][project-level] | [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java) |
| [AnActionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ex.AnActionListener)  | [`AnActionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ex/AnActionListener.java) |
| [ToolbarActionsUpdatedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.impl.segmentedActionBar.ToolbarActionsUpdatedListener)  ![Internal][internal] | [`ToolbarActionsUpdatedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/segmentedActionBar/ToolbarActionsUpdatedListener.java) |
| [ApplicationActivationListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.application.ApplicationActivationListener)  | [`ApplicationActivationListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) |
| [CommandListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.CommandListener)  | [`CommandListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/command/CommandListener.java) |
| [UndoRedoListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.impl.UndoRedoListener)  ![Experimental][experimental] ![Internal][internal] | [`UndoRedoListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoRedoListener.kt) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.EditorMouseHoverPopupManager.Listener)  ![Internal][internal] ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/EditorMouseHoverPopupManager.java) |
| [LatencyListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.actionSystem.LatencyListener)  | [`LatencyListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/LatencyListener.java) |
| [EditorColorsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.EditorColorsListener)  | [`EditorColorsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsListener.java) |
| [EditorColorsManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.impl.EditorColorsManagerListener)  ![Internal][internal] | [`EditorColorsManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/colors/impl/EditorColorsManagerListener.kt) |
| [DocumentBulkUpdateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.ex.DocumentBulkUpdateListener)  ![Deprecated][deprecated] ![Removal][removal] | [`DocumentBulkUpdateListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/ex/DocumentBulkUpdateListener.java) |
| [AppTopics#FILE_DOCUMENT_SYNC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  ![Deprecated][deprecated] | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [FileDocumentManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [FileEditorManagerListener#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener)  ![Project-Level][project-level] | [`FileEditorManagerListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [Before#FILE_EDITOR_MANAGER](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener.Before)  ![Project-Level][project-level] | [`Before`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [FileOpenedSyncListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileOpenedSyncListener)  ![Project-Level][project-level] | [`FileOpenedSyncListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileOpenedSyncListener.kt) |
| [FileDocumentBindingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.FileDocumentBindingListener)  ![Experimental][experimental] ![Internal][internal] | [`FileDocumentBindingListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/fileEditor/impl/FileDocumentBindingListener.kt) |
| [RecentPlacesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.IdeDocumentHistoryImpl.RecentPlacesListener)  ![Project-Level][project-level] | [`RecentPlacesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/IdeDocumentHistoryImpl.kt) |
| [FileTypeManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileTypes.FileTypeListener)  | [`FileTypeListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeListener.java) |
| [KeymapManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.KeymapManagerListener)  | [`KeymapManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapManagerListener.java) |
| [KeymapListener#CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.impl.ui.KeymapListener)  | [`KeymapListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/keymap/impl/ui/KeymapListener.java) |
| [AdvancedSettingsChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.advanced.AdvancedSettingsChangeListener)  | [`AdvancedSettingsChangeListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/options/advanced/AdvancedSettings.kt) |
| [ExternalUpdateRequest#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.ExternalUpdateRequest)  ![Experimental][experimental] ![Internal][internal] | [`ExternalUpdateRequest`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/options/newEditor/ExternalUpdateRequest.java) |
| [SettingsDialogListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.SettingsDialogListener)  ![Experimental][experimental] ![Internal][internal] | [`SettingsDialogListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/options/newEditor/SettingsDialogListener.kt) |
| [ProgressManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.ProgressManagerListener)  ![Internal][internal] | [`ProgressManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManagerListener.java) |
| [ProgressSuspender#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.impl.ProgressSuspender.SuspenderListener)  | [`SuspenderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/impl/ProgressSuspender.java) |
| [ProgressWindow#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.util.ProgressWindow.Listener)  | [`Listener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) |
| [BaseProjectDirectories#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.BaseProjectDirectoriesListener)  ![Project-Level][project-level] | [`BaseProjectDirectoriesListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/project/BaseProjectDirectoriesListener.kt) |
| [DumbService#DUMB_MODE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.DumbService.DumbModeListener)  ![Project-Level][project-level] | [`DumbModeListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.kt) |
| [ProjectTopics#MODULES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Deprecated][deprecated] ![Project-Level][project-level] | [`ModuleListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [ModuleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Project-Level][project-level] | [`ModuleListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [ProjectCloseListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectCloseListener)  ![Experimental][experimental] | [`ProjectCloseListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseListener.kt) |
| [ProjectManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectManagerListener)  | [`ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) |
| [ProjectNameListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectNameListener)  ![Project-Level][project-level] | [`ProjectNameListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/ProjectNameListener.kt) |
| [DefaultProjectListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.DefaultProjectListener)  ![Internal][internal] | [`DefaultProjectListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/DefaultProjectListener.kt) |
| [ProjectLifecycleListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.ProjectLifecycleListener)  | [`ProjectLifecycleListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/project/impl/ProjectLifecycleListener.java) |
| [ConfigFolderChangedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.shared.ConfigFolderChangedListener)  | [`ConfigFolderChangedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/shared/SharedConfigFolderUtil.kt) |
| [ProjectJdkTable#JDK_TABLE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.projectRoots.ProjectJdkTable.Listener)  | [`Listener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) |
| [AdditionalLibraryRootsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.AdditionalLibraryRootsListener)  ![Experimental][experimental] ![Project-Level][project-level] | [`AdditionalLibraryRootsListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsListener.java) |
| [ProjectTopics#PROJECT_ROOTS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Deprecated][deprecated] ![Project-Level][project-level] | [`ModuleRootListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [ModuleRootListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Project-Level][project-level] | [`ModuleRootListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [BalloonListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.ui.popup.BalloonListener)  | [`BalloonListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/BalloonListener.kt) |
| [PluginAutoUpdateListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.updateSettings.impl.PluginAutoUpdateListener)  | [`PluginAutoUpdateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/PluginAutoUpdateService.kt) |
| [RegistryManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.util.registry.RegistryValueListener)  | [`RegistryValueListener`](%gh-ic%/platform/util/src/com/intellij/openapi/util/registry/RegistryValueListener.java) |
| [FileStatusListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.FileStatusListener)  ![Project-Level][project-level] | [`FileStatusListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/vcs/FileStatusListener.java) |
| [LineStatusTrackerSettingListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerSettingListener)  | [`LineStatusTrackerSettingListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerSettingListener.java) |
| [VirtualFileManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.VirtualFileManagerListener)  | [`VirtualFileManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| [EncodingManagerListener#ENCODING_MANAGER_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.encoding.EncodingManagerListener)  | [`EncodingManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/EncodingManagerListener.java) |
| [VirtualFileManager#VFS_CHANGES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.newvfs.BulkFileListener)  | [`BulkFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) |
| [VirtualFilePointerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.pointers.VirtualFilePointerListener)  | [`VirtualFilePointerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/pointers/VirtualFilePointerListener.java) |
| [Info#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.StatusBarInfo)  ![Project-Level][project-level] | [`StatusBarInfo`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/StatusBarInfo.java) |
| [ToolWindowManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.ToolWindowManagerListener)  ![Project-Level][project-level] | [`ToolWindowManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/ex/ToolWindowManagerListener.java) |
| [WindowManagerListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.WindowManagerListener)  ![Internal][internal] | [`WindowManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/ex/WindowManagerListener.kt) |
| [TitleInfoProvider#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.TitleInfoProvider.TitleInfoProviderListener)  ![Internal][internal] | [`TitleInfoProviderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [WelcomeBalloonLayoutImpl#BALLOON_NOTIFICATION_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeBalloonLayoutImpl.BalloonNotificationListener)  | [`BalloonNotificationListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeBalloonLayoutImpl.java) |
| [WelcomeScreenComponentListener#COMPONENT_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenComponentListener)  ![Internal][internal] | [`WelcomeScreenComponentListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeScreenComponentListener.java) |
| [CloneableProjectsService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.cloneableProjects.CloneableProjectsService.CloneProjectListener)  | [`CloneProjectListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/cloneableProjects/CloneableProjectsService.kt) |
| [CourseDataStorageKt#COURSE_DELETED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.learnIde.coursesInProgress.CourseDeletedListener)  ![Internal][internal] | [`CourseDeletedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/learnIde/coursesInProgress/CourseDeletedListener.kt) |
| [ModuleAttachListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.ModuleAttachListener)  ![Project-Level][project-level] | [`ModuleAttachListener`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/ModuleAttachListener.kt) |
| [WorkspaceModelTopics#CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.platform.backend.workspace.WorkspaceModelChangeListener)  ![Obsolete][obsolete] ![Project-Level][project-level] | [`WorkspaceModelChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt) |
| [WorkspaceModelTopics#UNLOADED_ENTITIES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.platform.backend.workspace.WorkspaceModelUnloadedStorageChangeListener)  ![Internal][internal] ![Project-Level][project-level] | [`WorkspaceModelUnloadedStorageChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt) |
| [TelemetryReceivedListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.diagnostic.telemetry.impl.TelemetryReceivedListener)  ![Experimental][experimental] ![Internal][internal] | [`TelemetryReceivedListener`](%gh-ic%/platform/diagnostic/telemetry-impl/src/TelemetryReceivedListener.kt) |
| [ProblemListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.problems.ProblemListener)  ![Project-Level][project-level] | [`ProblemListener`](%gh-ic%/platform/analysis-api/src/com/intellij/problems/ProblemListener.java) |
| [ProfileChangeAdapter#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.profile.ProfileChangeAdapter)  ![Project-Level][project-level] | [`ProfileChangeAdapter`](%gh-ic%/platform/analysis-api/src/com/intellij/profile/ProfileChangeAdapter.java) |
| [PsiDocumentListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.PsiDocumentListener)  ![Project-Level][project-level] | [`PsiDocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentListener.java) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.codeStyle.CodeStyleManager.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/codeStyle/CodeStyleManager.java) |
| [CodeStyleSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.codeStyle.CodeStyleSettingsListener)  ![Project-Level][project-level] | [`CodeStyleSettingsListener`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsListener.java) |
| [PsiManagerImpl#ANY_PSI_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.AnyPsiChangeListener)  ![Project-Level][project-level] | [`AnyPsiChangeListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/AnyPsiChangeListener.java) |
| [PsiDocumentTransactionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.PsiDocumentTransactionListener)  ![Project-Level][project-level] | [`PsiDocumentTransactionListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiDocumentTransactionListener.java) |
| [FileTypeIndex#INDEX_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.search.FileTypeIndex.IndexChangeListener)  ![Experimental][experimental] | [`IndexChangeListener`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) |
| [PsiModificationTracker#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [RefactoringEventListener#REFACTORING_EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.refactoring.listeners.RefactoringEventListener)  ![Project-Level][project-level] | [`RefactoringEventListener`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/listeners/RefactoringEventListener.java) |
| [RemoteMappingsListener#REMOTE_MAPPINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.remote.RemoteMappingsListener)  | [`RemoteMappingsListener`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/RemoteMappingsListener.java) |
| [RemoteServerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.configuration.RemoteServerListener)  | [`RemoteServerListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/RemoteServerListener.java) |
| [ServerConnectionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.runtime.ServerConnectionListener)  | [`ServerConnectionListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/runtime/ServerConnectionListener.java) |
| [SpellCheckerEngineListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spellchecker.engine.SpellCheckerEngineListener)  ![Project-Level][project-level] | [`SpellCheckerEngineListener`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/engine/SpellCheckerEngineListener.java) |
| [ProjectTaskListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.task.ProjectTaskListener)  ![Project-Level][project-level] | [`ProjectTaskListener`](%gh-ic%/platform/lang-api/src/com/intellij/task/ProjectTaskListener.java) |
| [DeferredIconListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.DeferredIconListener)  ![Internal][internal] | [`DeferredIconListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/DeferredIconListener.kt) |
| [AuthStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.JBAccountInfoService.AuthStateListener)  | [`AuthStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/JBAccountInfoService.java) |
| [LicenseStateListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.LicensingFacade.LicenseStateListener)  | [`LicenseStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/LicensingFacade.java) |
| [JBCefHealthCheckTopic#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.jcef.JBCefHealthMonitor.JBCefHealthCheckTopic)  | [`JBCefHealthCheckTopic`](%gh-ic%/platform/ui.jcef/jcef/JBCefHealthMonitor.java) |
| [ToolWindowViewModelListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ui.viewModel.extraction.ToolWindowViewModelListener)  ![Project-Level][project-level] | [`ToolWindowViewModelListener`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelListener.java) |
| [UnindexedFilesUpdaterListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.UnindexedFilesUpdaterListener)  ![Deprecated][deprecated] | [`UnindexedFilesUpdaterListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/UnindexedFilesUpdaterListener.java) |
| [ProjectIndexingActivityHistoryListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.diagnostic.ProjectIndexingActivityHistoryListener)  | [`ProjectIndexingActivityHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [WebSymbolContextChangeListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.webSymbols.context.WebSymbolContextChangeListener)  | [`WebSymbolContextChangeListener`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolContextChangeListener.kt) |
| [JpsGlobalModelLoadedListener.Companion#LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsGlobalModelLoadedListener)  ![Internal][internal] | [`JpsGlobalModelLoadedListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/JpsGlobalModelLoadedListener.kt) |
| [JpsProjectLoadedListener.Companion#LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsProjectLoadedListener)  ![Internal][internal] ![Project-Level][project-level] | [`JpsProjectLoadedListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/JpsProjectLoadedListener.kt) |
| [XDebuggerManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XDebuggerManagerListener)  ![Project-Level][project-level] | [`XDebuggerManagerListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/XDebuggerManagerListener.java) |
| [XEvaluationListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XEvaluationListener)  ![Internal][internal] | [`XEvaluationListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/XEvaluationListener.kt) |
| [XBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.breakpoints.XBreakpointListener)  ![Project-Level][project-level] | [`XBreakpointListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointListener.java) |
| [XDependentBreakpointListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.impl.breakpoints.XDependentBreakpointListener)  ![Project-Level][project-level] | [`XDependentBreakpointListener`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/breakpoints/XDependentBreakpointListener.java) |
| [BreadcrumbsInitListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.xml.breadcrumbs.BreadcrumbsInitListener)  ![Internal][internal] ![Project-Level][project-level] | [`BreadcrumbsInitListener`](%gh-ic%/platform/platform-impl/src/com/intellij/xml/breadcrumbs/BreadcrumbsInitListener.java) |
| [TodoConfiguration#PROPERTY_CHANGE](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  ![Project-Level][project-level] | `PropertyChangeListener` |
| [IndexPatternProvider#INDEX_PATTERNS_CHANGED](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [SeverityRegistrar#SEVERITIES_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [UsageFilteringRuleProvider#RULES_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [RunToolbarPopupKt#VOID_EXECUTION_TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [StructureViewWrapperImpl#STRUCTURE_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [UpdateActionsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.ide.UpdateActionsListener)  | [`UpdateActionsListener`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxUpdateActions.kt) |


### Analysis.xml

[`Analysis.xml`](%gh-ic%/platform/analysis-api/resources/META-INF/Analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.containerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.containerProvider) | [`ContainerProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/ContainerProvider.java) |
| [com.intellij.codeInspection.InspectionExtension](https://jb.gg/ipe?extensions=com.intellij.codeInspection.InspectionExtension) | [`InspectionExtensionsFactory`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/lang/InspectionExtensionsFactory.java) |
| [com.intellij.completion.contributor](https://jb.gg/ipe?extensions=com.intellij.completion.contributor) ![DumbAware][dumb-aware] | [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) |
| [com.intellij.completion.skip](https://jb.gg/ipe?extensions=com.intellij.completion.skip) | [`CompletionPreselectSkipper`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionPreselectSkipper.java) |
| [com.intellij.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.documentationProvider) ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.dynamicInspectionsProvider](https://jb.gg/ipe?extensions=com.intellij.dynamicInspectionsProvider) ![Internal][internal] | [`DynamicInspectionsProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/ex/dynamic-inspections.kt) |
| [com.intellij.fileContextProvider](https://jb.gg/ipe?extensions=com.intellij.fileContextProvider) ![Project-Level][project-level] | [`FileContextProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileContextProvider.java) |
| [com.intellij.fileLookupInfoProvider](https://jb.gg/ipe?extensions=com.intellij.fileLookupInfoProvider) | [`FileLookupInfoProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/file/FileLookupInfoProvider.java) |
| [com.intellij.formatting.caretRestorationDecider](https://jb.gg/ipe?extensions=com.intellij.formatting.caretRestorationDecider) ![Experimental][experimental] | [`CaretRestorationDecider`](%gh-ic%/platform/analysis-api/src/com/intellij/formatting/CaretRestorationDecider.kt) |
| [com.intellij.generatedSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.generatedSourcesFilter) | [`GeneratedSourcesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/roots/GeneratedSourcesFilter.kt) |
| [com.intellij.globalInspection](https://jb.gg/ipe?extensions=com.intellij.globalInspection) | [`GlobalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/GlobalInspectionTool.java) |
| [com.intellij.gotoDeclarationHandler](https://jb.gg/ipe?extensions=com.intellij.gotoDeclarationHandler) | [`GotoDeclarationHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/navigation/actions/GotoDeclarationHandler.java) |
| [com.intellij.highlightErrorFilter](https://jb.gg/ipe?extensions=com.intellij.highlightErrorFilter) ![Project-Level][project-level] | [`HighlightErrorFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/highlighting/HighlightErrorFilter.java) |
| [com.intellij.inspectionCustomComponent](https://jb.gg/ipe?extensions=com.intellij.inspectionCustomComponent) ![Experimental][experimental] | [`CustomComponentExtension`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/CustomComponentExtension.java) |
| [com.intellij.inspectionElementsMerger](https://jb.gg/ipe?extensions=com.intellij.inspectionElementsMerger) | [`InspectionElementsMerger`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/ex/InspectionElementsMerger.java) |
| [com.intellij.inspectionToolProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionToolProvider) | [`InspectionToolProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionToolProvider.java) |
| [com.intellij.inspectionsReportConverter](https://jb.gg/ipe?extensions=com.intellij.inspectionsReportConverter) | [`InspectionsReportConverter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionsReportConverter.java) |
| [com.intellij.intentionAction](https://jb.gg/ipe?extensions=com.intellij.intentionAction) | [`CommonIntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/CommonIntentionAction.java) |
| [com.intellij.lang.documentationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.documentationProvider) ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.lang.inspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.lang.inspectionSuppressor) ![DumbAware][dumb-aware] | [`InspectionSuppressor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionSuppressor.java) |
| [com.intellij.languageInjector](https://jb.gg/ipe?extensions=com.intellij.languageInjector) | [`LanguageInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/LanguageInjector.java) |
| [com.intellij.liveTemplateContext](https://jb.gg/ipe?extensions=com.intellij.liveTemplateContext) | [`TemplateContextType`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/TemplateContextType.java) |
| [com.intellij.liveTemplateContextProvider](https://jb.gg/ipe?extensions=com.intellij.liveTemplateContextProvider) | [`LiveTemplateContextProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/LiveTemplateContextProvider.java) |
| [com.intellij.liveTemplateInternalContext](https://jb.gg/ipe?extensions=com.intellij.liveTemplateInternalContext) | `n/a` |
| [com.intellij.liveTemplateMacro](https://jb.gg/ipe?extensions=com.intellij.liveTemplateMacro) | [`Macro`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/Macro.java) |
| [com.intellij.localFileSystemTimestampEvaluator](https://jb.gg/ipe?extensions=com.intellij.localFileSystemTimestampEvaluator) ![Internal][internal] | [`LocalFileSystemTimestampEvaluator`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/impl/local/LocalFileSystemTimestampEvaluator.java) |
| [com.intellij.localInspection](https://jb.gg/ipe?extensions=com.intellij.localInspection) ![DumbAware][dumb-aware] | [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) |
| [com.intellij.optionController](https://jb.gg/ipe?extensions=com.intellij.optionController) ![Experimental][experimental] | [`OptionControllerProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/OptionControllerProvider.java) |
| [com.intellij.weigher](https://jb.gg/ipe?extensions=com.intellij.weigher) | [`Weigher`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/Weigher.java) |

### AnalysisImpl.xml

[`AnalysisImpl.xml`](%gh-ic%/platform/analysis-impl/resources/META-INF/AnalysisImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dataflowIRProvider](https://jb.gg/ipe?extensions=com.intellij.dataflowIRProvider) | [`DataFlowIRProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/dataFlow/lang/ir/DataFlowIRProvider.java) |
| [com.intellij.elementLookupRenderer](https://jb.gg/ipe?extensions=com.intellij.elementLookupRenderer) ![Deprecated][deprecated] ![Removal][removal] | [`ElementLookupRenderer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/lookup/impl/ElementLookupRenderer.java) |
| [com.intellij.liveTemplateOptionalProcessor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateOptionalProcessor) ![DumbAware][dumb-aware] | [`TemplateOptionalProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplateOptionalProcessor.java) |
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
| [com.intellij.toolboxServiceHandler](https://jb.gg/ipe?extensions=com.intellij.toolboxServiceHandler) ![Internal][internal] | [`ToolboxServiceHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxRestService.kt) |
| [org.jetbrains.binaryRequestHandler](https://jb.gg/ipe?extensions=org.jetbrains.binaryRequestHandler) ![Non-Dynamic][non-dynamic] | [`BinaryRequestHandler`](%gh-ic%/platform/platform-util-netty/src/org/jetbrains/ide/BinaryRequestHandler.java) |
| [org.jetbrains.customPortServerManager](https://jb.gg/ipe?extensions=org.jetbrains.customPortServerManager) ![Non-Dynamic][non-dynamic] | [`CustomPortServerManager`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/ide/CustomPortServerManager.kt) |
| [org.jetbrains.jsonRpcDomain](https://jb.gg/ipe?extensions=org.jetbrains.jsonRpcDomain) ![Internal][internal] | `Object` |
| [org.jetbrains.webServerFileHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerFileHandler) | [`WebServerFileHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerFileHandler.kt) |
| [org.jetbrains.webServerPathHandler](https://jb.gg/ipe?extensions=org.jetbrains.webServerPathHandler) | [`WebServerPathHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/BuiltInWebServer.kt) |
| [org.jetbrains.webServerRootsProvider](https://jb.gg/ipe?extensions=org.jetbrains.webServerRootsProvider) | [`WebServerRootsProvider`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/builtInWebServer/WebServerRootsProvider.kt) |

### CodeStyle.xml

[`CodeStyle.xml`](%gh-ic%/platform/code-style-impl/resources/META-INF/CodeStyle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeFormattingDataPreparer](https://jb.gg/ipe?extensions=com.intellij.codeFormattingDataPreparer) ![Internal][internal] | [`CodeFormattingDataPreparer`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/codeStyle/CodeFormattingDataPreparer.java) |
| [com.intellij.codeStyleSettingsModifier](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsModifier) ![Experimental][experimental] | [`CodeStyleSettingsModifier`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/modifier/CodeStyleSettingsModifier.java) |
| [com.intellij.disabledIndentRangesProvider](https://jb.gg/ipe?extensions=com.intellij.disabledIndentRangesProvider) | [`DisabledIndentRangesProvider`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/DisabledIndentRangesProvider.java) |
| [com.intellij.externalFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.externalFormatProcessor) ![Experimental][experimental] | [`ExternalFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/ExternalFormatProcessor.java) |
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
| [com.intellij.postQuickFixTaskService](https://jb.gg/ipe?extensions=com.intellij.postQuickFixTaskService) ![Internal][internal] | [`PostQuickFixTaskService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/PostQuickFixTaskService.kt) |
| [com.intellij.preFormatProcessor](https://jb.gg/ipe?extensions=com.intellij.preFormatProcessor) | [`PreFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) |

### com.intellij.platform.images

[`com.intellij.platform.images`](%gh-ic%/images/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.images.themeFilter](https://jb.gg/ipe?extensions=com.intellij.images.themeFilter) | [`ThemeFilter`](%gh-ic%/images/src/org/intellij/images/thumbnail/actions/ThemeFilter.java) |

### com.intellij.smartUpdate

[`com.intellij.smartUpdate`](%gh-ic%/platform/smart-update/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.smartUpdateStep](https://jb.gg/ipe?extensions=com.intellij.smartUpdateStep) | [`SmartUpdateStep`](%gh-ic%/platform/smart-update/src/com/intellij/smartUpdate/SmartUpdateStep.kt) |

### CompletionExtensionPoints.xml

[`CompletionExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/CompletionExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.wordCompletionFilter](https://jb.gg/ipe?extensions=com.intellij.codeInsight.wordCompletionFilter) | [`WordCompletionElementFilter`](%gh-ic%/platform/lang-api/src/com/intellij/lang/WordCompletionElementFilter.java) |
| [com.intellij.completion.confidence](https://jb.gg/ipe?extensions=com.intellij.completion.confidence) | [`CompletionConfidence`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionConfidence.java) |
| [com.intellij.completion.ml.contextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.contextFeatures) ![Internal][internal] | [`ContextFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementFeatures) ![Internal][internal] | [`ElementFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ElementFeatureProvider.java) |
| [com.intellij.completion.ml.model](https://jb.gg/ipe?extensions=com.intellij.completion.ml.model) ![Internal][internal] | [`RankingModelProvider`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ml/completion/RankingModelProvider.java) |
| [com.intellij.completion.plainTextSymbol](https://jb.gg/ipe?extensions=com.intellij.completion.plainTextSymbol) | [`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java) |
| [com.intellij.completion.preselectionBehaviourProvider](https://jb.gg/ipe?extensions=com.intellij.completion.preselectionBehaviourProvider) | [`CompletionPreselectionBehaviourProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPreselectionBehaviourProvider.java) |
| [com.intellij.createDirectoryCompletionContributor](https://jb.gg/ipe?extensions=com.intellij.createDirectoryCompletionContributor) | [`CreateDirectoryCompletionContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CreateDirectoryCompletionContributor.java) |
| [com.intellij.lookup.actionProvider](https://jb.gg/ipe?extensions=com.intellij.lookup.actionProvider) | [`LookupActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupActionProvider.java) |
| [com.intellij.lookup.charFilter](https://jb.gg/ipe?extensions=com.intellij.lookup.charFilter) | [`CharFilter`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/lookup/CharFilter.java) |
| [com.intellij.lookup.customizer](https://jb.gg/ipe?extensions=com.intellij.lookup.customizer) ![Experimental][experimental] ![Internal][internal] | [`LookupCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/ClientLookupManager.kt) |
| [com.intellij.lookup.usageDetails](https://jb.gg/ipe?extensions=com.intellij.lookup.usageDetails) ![Internal][internal] | [`LookupUsageDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupUsageDescriptor.java) |
| [com.intellij.lookup.vetoPolicy](https://jb.gg/ipe?extensions=com.intellij.lookup.vetoPolicy) ![Internal][internal] | [`LookupImplVetoPolicy`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupImplVetoPolicy.kt) |
| [com.intellij.templateParameterTraversalPolicy](https://jb.gg/ipe?extensions=com.intellij.templateParameterTraversalPolicy) | [`TemplateParameterTraversalPolicy`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/TemplateParameterTraversalPolicy.java) |

### Core.xml

[`Core.xml`](%gh-ic%/platform/core-api/resources/META-INF/Core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backgroundPostStartupActivity](https://jb.gg/ipe?extensions=com.intellij.backgroundPostStartupActivity) | [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.editorFactoryDocumentListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryDocumentListener) | [`DocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/event/DocumentListener.java) |
| [com.intellij.eelProvider](https://jb.gg/ipe?extensions=com.intellij.eelProvider) ![Internal][internal] | [`EelProvider`](%gh-ic%/platform/eel-provider/src/com/intellij/platform/eel/provider/EelProvider.kt) |
| [com.intellij.fileIconPatcher](https://jb.gg/ipe?extensions=com.intellij.fileIconPatcher) | [`FileIconPatcher`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconPatcher.java) |
| [com.intellij.fileIconProvider](https://jb.gg/ipe?extensions=com.intellij.fileIconProvider) | [`FileIconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconProvider.java) |
| [com.intellij.fileTypeDetector](https://jb.gg/ipe?extensions=com.intellij.fileTypeDetector) | [`FileTypeDetector`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeRegistry.java) |
| [com.intellij.filetype.decompiler](https://jb.gg/ipe?extensions=com.intellij.filetype.decompiler) | [`BinaryFileDecompiler`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/BinaryFileDecompiler.java) |
| [com.intellij.iconLayerProvider](https://jb.gg/ipe?extensions=com.intellij.iconLayerProvider) ![Non-Dynamic][non-dynamic] | [`IconLayerProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconLayerProvider.java) |
| [com.intellij.iconProvider](https://jb.gg/ipe?extensions=com.intellij.iconProvider) ![DumbAware][dumb-aware] | [`IconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconProvider.java) |
| [com.intellij.initProjectActivity](https://jb.gg/ipe?extensions=com.intellij.initProjectActivity) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`InitProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.inspection.basicVisitor](https://jb.gg/ipe?extensions=com.intellij.inspection.basicVisitor) ![Experimental][experimental] | [`PsiElementVisitor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElementVisitor.java) |
| [com.intellij.lang.commenter](https://jb.gg/ipe?extensions=com.intellij.lang.commenter) | [`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) |
| [com.intellij.lang.elementManipulator](https://jb.gg/ipe?extensions=com.intellij.lang.elementManipulator) | [`ElementManipulator`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementManipulator.java) |
| [com.intellij.lang.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.lang.fileViewProviderFactory) | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.lang.parserDefinition](https://jb.gg/ipe?extensions=com.intellij.lang.parserDefinition) | [`ParserDefinition`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java) |
| [com.intellij.lang.substitutor](https://jb.gg/ipe?extensions=com.intellij.lang.substitutor) | [`LanguageSubstitutor`](%gh-ic%/platform/core-api/src/com/intellij/psi/LanguageSubstitutor.java) |
| [com.intellij.languageBundle](https://jb.gg/ipe?extensions=com.intellij.languageBundle) ![Internal][internal] | `n/a` |
| [com.intellij.languageInjectionContributor](https://jb.gg/ipe?extensions=com.intellij.languageInjectionContributor) | [`LanguageInjectionContributor`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) |
| [com.intellij.languageInjectionPerformer](https://jb.gg/ipe?extensions=com.intellij.languageInjectionPerformer) | [`LanguageInjectionPerformer`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java) |
| [com.intellij.metaLanguage](https://jb.gg/ipe?extensions=com.intellij.metaLanguage) | [`MetaLanguage`](%gh-ic%/platform/core-api/src/com/intellij/lang/MetaLanguage.java) |
| [com.intellij.multiHostInjector](https://jb.gg/ipe?extensions=com.intellij.multiHostInjector) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`MultiHostInjector`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java) |
| [com.intellij.multiverse.codeInsightContextPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.multiverse.codeInsightContextPresentationProvider) | [`CodeInsightContextPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextPresentationProvider.kt) |
| [com.intellij.multiverse.codeInsightContextProvider](https://jb.gg/ipe?extensions=com.intellij.multiverse.codeInsightContextProvider) | [`CodeInsightContextProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextProvider.kt) |
| [com.intellij.multiverseEnabler](https://jb.gg/ipe?extensions=com.intellij.multiverseEnabler) ![Non-Dynamic][non-dynamic] | [`MultiverseEnabler`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContext.kt) |
| [com.intellij.pom.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.pom.declarationSearcher) | [`PomDeclarationSearcher`](%gh-ic%/platform/core-api/src/com/intellij/pom/PomDeclarationSearcher.java) |
| [com.intellij.postStartupActivity](https://jb.gg/ipe?extensions=com.intellij.postStartupActivity) | [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.requiredForSmartModeStartupActivity](https://jb.gg/ipe?extensions=com.intellij.requiredForSmartModeStartupActivity) | [`RequiredForSmartMode`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| [com.intellij.stubElementTypeHolder](https://jb.gg/ipe?extensions=com.intellij.stubElementTypeHolder) | `n/a` |
| [com.intellij.vfs.asyncListener](https://jb.gg/ipe?extensions=com.intellij.vfs.asyncListener) | [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) |
| [com.intellij.virtualFileManagerListener](https://jb.gg/ipe?extensions=com.intellij.virtualFileManagerListener) | [`VirtualFileManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| [com.intellij.virtualFilePreCloseCheck](https://jb.gg/ipe?extensions=com.intellij.virtualFilePreCloseCheck) ![Non-Dynamic][non-dynamic] ![Experimental][experimental] | [`VirtualFilePreCloseCheck`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFilePreCloseCheck.kt) |
| [com.intellij.writingAccessProvider](https://jb.gg/ipe?extensions=com.intellij.writingAccessProvider) ![Project-Level][project-level] | [`WritingAccessProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/WritingAccessProvider.java) |

### CoreImpl.xml

[`CoreImpl.xml`](%gh-ic%/platform/core-impl/resources/META-INF/CoreImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.controlFlowProvider](https://jb.gg/ipe?extensions=com.intellij.controlFlowProvider) | [`ControlFlowProvider`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/controlflow/ControlFlowProvider.java) |
| [com.intellij.diagnostic.freezeProfiler](https://jb.gg/ipe?extensions=com.intellij.diagnostic.freezeProfiler) ![Internal][internal] | [`FreezeProfiler`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/FreezeProfiler.java) |
| [com.intellij.documentWriteAccessGuard](https://jb.gg/ipe?extensions=com.intellij.documentWriteAccessGuard) ![Experimental][experimental] | [`DocumentWriteAccessGuard`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/impl/DocumentWriteAccessGuard.java) |
| [com.intellij.elementsToHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.elementsToHighlightFilter) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.fileEditor.fileSizeChecker](https://jb.gg/ipe?extensions=com.intellij.fileEditor.fileSizeChecker) ![Internal][internal] | [`FileSizeLimit`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/limits/FileSizeLimit.kt) |
| [com.intellij.fileEditor.textPresentationTransformer](https://jb.gg/ipe?extensions=com.intellij.fileEditor.textPresentationTransformer) ![Internal][internal] | [`TextPresentationTransformer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/transformer/TextPresentationTransformer.kt) |
| [com.intellij.inlineCompletionLineRendererCustomization](https://jb.gg/ipe?extensions=com.intellij.inlineCompletionLineRendererCustomization) ![Internal][internal] | [`InlineCompletionInlayRenderer`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/render/InlineCompletionInlayRenderer.kt) |
| [com.intellij.lang.ast.factory](https://jb.gg/ipe?extensions=com.intellij.lang.ast.factory) | [`ASTFactory`](%gh-ic%/platform/core-impl/src/com/intellij/lang/ASTFactory.java) |
| [com.intellij.lang.tokenSeparatorGenerator](https://jb.gg/ipe?extensions=com.intellij.lang.tokenSeparatorGenerator) | [`TokenSeparatorGenerator`](%gh-ic%/platform/core-api/src/com/intellij/lang/TokenSeparatorGenerator.java) |
| [com.intellij.lang.treePatcher](https://jb.gg/ipe?extensions=com.intellij.lang.treePatcher) | [`TreePatcher`](%gh-ic%/platform/core-impl/src/com/intellij/psi/templateLanguages/TreePatcher.java) |
| [com.intellij.psi.batchReferenceProcessingSuppressor](https://jb.gg/ipe?extensions=com.intellij.psi.batchReferenceProcessingSuppressor) ![Experimental][experimental] | [`BatchReferenceProcessingSuppressor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiFileEx.java) |
| [com.intellij.psi.implicitReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.implicitReferenceProvider) | [`ImplicitReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java) |
| [com.intellij.psi.referenceContributor](https://jb.gg/ipe?extensions=com.intellij.psi.referenceContributor) | [`PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java) |
| [com.intellij.psi.symbolReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.psi.symbolReferenceProvider) | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.psi.treeChangeListener](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangeListener) ![Project-Level][project-level] | [`PsiTreeChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiTreeChangeListener.java) |
| [com.intellij.psi.treeChangePreprocessor](https://jb.gg/ipe?extensions=com.intellij.psi.treeChangePreprocessor) ![Project-Level][project-level] | [`PsiTreeChangePreprocessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiTreeChangePreprocessor.java) |
| [com.intellij.smartPointer.anchorProvider](https://jb.gg/ipe?extensions=com.intellij.smartPointer.anchorProvider) | [`SmartPointerAnchorProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/smartPointers/SmartPointerAnchorProvider.java) |
| [com.intellij.treeCopyHandler](https://jb.gg/ipe?extensions=com.intellij.treeCopyHandler) | [`TreeCopyHandler`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeCopyHandler.java) |
| [com.intellij.virtualFileSystem](https://jb.gg/ipe?extensions=com.intellij.virtualFileSystem) | [`VirtualFileSystem`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) |

### DomPlugin.xml

[`DomPlugin.xml`](%gh-ic%/xml/dom-impl/resources/META-INF/DomPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dom.converter](https://jb.gg/ipe?extensions=com.intellij.dom.converter) | `n/a` |
| [com.intellij.dom.customAnnotationChecker](https://jb.gg/ipe?extensions=com.intellij.dom.customAnnotationChecker) | [`DomCustomAnnotationChecker`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomCustomAnnotationChecker.java) |
| [com.intellij.dom.extender](https://jb.gg/ipe?extensions=com.intellij.dom.extender) | [`DomExtender`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtender.java) |
| [com.intellij.dom.fileDescription](https://jb.gg/ipe?extensions=com.intellij.dom.fileDescription) ![Deprecated][deprecated] | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.fileMetaData](https://jb.gg/ipe?extensions=com.intellij.dom.fileMetaData) | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| [com.intellij.dom.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.dom.gotoSuper) | [`DomElementNavigationProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementNavigationProvider.java) |
| [com.intellij.dom.implementation](https://jb.gg/ipe?extensions=com.intellij.dom.implementation) | [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) |
| [com.intellij.dom.uiControlsProvider](https://jb.gg/ipe?extensions=com.intellij.dom.uiControlsProvider) ![Obsolete][obsolete] ![Non-Dynamic][non-dynamic] | [`Consumer`](%gh-ic%/platform/util/src/com/intellij/util/Consumer.java) |
| [com.intellij.moduleContextProvider](https://jb.gg/ipe?extensions=com.intellij.moduleContextProvider) | [`ModuleContextProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ModuleContextProvider.java) |

### duplicates-analysis.xml

[`duplicates-analysis.xml`](%gh-ic%/platform/duplicates-analysis/resources/META-INF/duplicates-analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.equivalenceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.equivalenceDescriptorProvider) | [`EquivalenceDescriptorProvider`](%gh-ic%/platform/duplicates-analysis/src/com/intellij/dupLocator/equivalence/EquivalenceDescriptorProvider.java) |

### Editor.xml

[`Editor.xml`](%gh-ic%/platform/editor-ui-api/resources/META-INF/Editor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.editorFactoryListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryListener) | [`EditorFactoryListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorFactoryListener.java) |
| [com.intellij.mergeableGutterIconRendererProvider](https://jb.gg/ipe?extensions=com.intellij.mergeableGutterIconRendererProvider) ![Internal][internal] | [`MergeableGutterIconRendererProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/MergeableGutterIconRendererProvider.java) |
| [com.intellij.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.syntaxHighlighter) | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |

### EditorExtensionPoints.xml

[`EditorExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/EditorExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.backspaceHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.backspaceHandlerDelegate) | [`BackspaceHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceHandlerDelegate.java) |
| [com.intellij.basicWordSelectionFilter](https://jb.gg/ipe?extensions=com.intellij.basicWordSelectionFilter) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.bidiRegionsSeparator](https://jb.gg/ipe?extensions=com.intellij.bidiRegionsSeparator) | [`BidiRegionsSeparator`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/bidi/BidiRegionsSeparator.java) |
| [com.intellij.codeBlockProvider](https://jb.gg/ipe?extensions=com.intellij.codeBlockProvider) | [`CodeBlockProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CodeBlockProvider.java) |
| [com.intellij.codeInsight.fillParagraph](https://jb.gg/ipe?extensions=com.intellij.codeInsight.fillParagraph) | [`ParagraphFillHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/fillParagraph/ParagraphFillHandler.java) |
| [com.intellij.commentCompleteHandler](https://jb.gg/ipe?extensions=com.intellij.commentCompleteHandler) | [`CommentCompleteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CommentCompleteHandler.java) |
| [com.intellij.copyPastePostProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePostProcessor) | [`CopyPastePostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/CopyPastePostProcessor.java) |
| [com.intellij.copyPastePreProcessor](https://jb.gg/ipe?extensions=com.intellij.copyPastePreProcessor) | [`CopyPastePreProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePreProcessor.java) |
| [com.intellij.customPasteProvider](https://jb.gg/ipe?extensions=com.intellij.customPasteProvider) | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.editor.backspaceModeOverride](https://jb.gg/ipe?extensions=com.intellij.editor.backspaceModeOverride) | [`BackspaceModeOverride`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceModeOverride.java) |
| [com.intellij.enterBetweenBracesDelegate](https://jb.gg/ipe?extensions=com.intellij.enterBetweenBracesDelegate) | [`EnterBetweenBracesDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterBetweenBracesDelegate.java) |
| [com.intellij.enterHandlerDelegate](https://jb.gg/ipe?extensions=com.intellij.enterHandlerDelegate) | [`EnterHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java) |
| [com.intellij.extendWordSelectionHandler](https://jb.gg/ipe?extensions=com.intellij.extendWordSelectionHandler) | [`ExtendWordSelectionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java) |
| [com.intellij.flipCommaIntention.flipper](https://jb.gg/ipe?extensions=com.intellij.flipCommaIntention.flipper) | [`Flipper`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/FlipCommaIntention.java) |
| [com.intellij.generalEditorOptionsCustomizer](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsCustomizer) ![Internal][internal] | [`EditorOptionsPageCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/EditorOptionsPageCustomizer.kt) |
| [com.intellij.generalEditorOptionsExtension](https://jb.gg/ipe?extensions=com.intellij.generalEditorOptionsExtension) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.inline.completion.editorTypeResolver](https://jb.gg/ipe?extensions=com.intellij.inline.completion.editorTypeResolver) ![Internal][internal] | [`InlineCompletionEditorTypeResolver`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/editor/InlineCompletionEditorType.kt) |
| [com.intellij.inline.completion.element.manipulator](https://jb.gg/ipe?extensions=com.intellij.inline.completion.element.manipulator) ![Experimental][experimental] | [`InlineCompletionElementManipulator`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/elements/InlineCompletionElementManipulator.kt) |
| [com.intellij.inline.completion.handlerInitializer](https://jb.gg/ipe?extensions=com.intellij.inline.completion.handlerInitializer) ![Internal][internal] | [`InlineCompletionHandlerInitializer`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionHandlerInitializer.kt) |
| [com.intellij.inline.completion.partial.accept.handler](https://jb.gg/ipe?extensions=com.intellij.inline.completion.partial.accept.handler) ![Experimental][experimental] ![Internal][internal] | [`InlineCompletionPartialAcceptHandler`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/suggestion/InlineCompletionPartialAcceptHandler.kt) |
| [com.intellij.inline.completion.provider](https://jb.gg/ipe?extensions=com.intellij.inline.completion.provider) | [`InlineCompletionProvider`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionProvider.kt) |
| [com.intellij.inline.completion.quoteHandlerEx](https://jb.gg/ipe?extensions=com.intellij.inline.completion.quoteHandlerEx) ![Experimental][experimental] ![Internal][internal] | [`InlineCompletionQuoteHandlerEx`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/inline/completion/suggestion/InlineCompletionQuoteHandlerEx.kt) |
| [com.intellij.inline.completion.session.logs](https://jb.gg/ipe?extensions=com.intellij.inline.completion.session.logs) ![Internal][internal] | [`InlineCompletionSessionLogsEP`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/logs/InlineCompletionSessionLogsEP.kt) |
| [com.intellij.inline.completion.usage.data](https://jb.gg/ipe?extensions=com.intellij.inline.completion.usage.data) ![Internal][internal] | [`InlineCompletionProviderSpecificUsageData`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/logs/InlineCompletionProviderSpecificUsageData.kt) |
| [com.intellij.joinLinesHandler](https://jb.gg/ipe?extensions=com.intellij.joinLinesHandler) | [`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) |
| [com.intellij.lang.emacs](https://jb.gg/ipe?extensions=com.intellij.lang.emacs) | [`EmacsProcessingHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/emacs/EmacsProcessingHandler.java) |
| [com.intellij.lang.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.lang.quoteHandler) | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.lang.smartEnterProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.smartEnterProcessor) | [`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) |
| [com.intellij.listSplitJoinContext](https://jb.gg/ipe?extensions=com.intellij.listSplitJoinContext) ![Experimental][experimental] | [`ListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt) |
| [com.intellij.moveLeftRightHandler](https://jb.gg/ipe?extensions=com.intellij.moveLeftRightHandler) | [`MoveElementLeftRightHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) |
| [com.intellij.nonWriteAccessTypedHandler](https://jb.gg/ipe?extensions=com.intellij.nonWriteAccessTypedHandler) ![Experimental][experimental] ![Internal][internal] | [`NonWriteAccessTypedHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/NonWriteAccessTypedHandler.java) |
| [com.intellij.preserveIndentOnPaste](https://jb.gg/ipe?extensions=com.intellij.preserveIndentOnPaste) | `n/a` |
| [com.intellij.quoteHandler](https://jb.gg/ipe?extensions=com.intellij.quoteHandler) | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| [com.intellij.selectionUnquotingFilter](https://jb.gg/ipe?extensions=com.intellij.selectionUnquotingFilter) | [`UnquotingFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectionQuotingTypedHandler.java) |
| [com.intellij.statementUpDownMover](https://jb.gg/ipe?extensions=com.intellij.statementUpDownMover) | [`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java) |
| [com.intellij.typedHandler](https://jb.gg/ipe?extensions=com.intellij.typedHandler) | [`TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) |
| [com.intellij.typingActionsExtension](https://jb.gg/ipe?extensions=com.intellij.typingActionsExtension) ![Experimental][experimental] | [`TypingActionsExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/TypingActionsExtension.java) |
| [com.intellij.wordBoundaryFilter](https://jb.gg/ipe?extensions=com.intellij.wordBoundaryFilter) | [`WordBoundaryFilter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/actions/WordBoundaryFilter.java) |

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
| [com.intellij.langCodeStyleSettingsContributor](https://jb.gg/ipe?extensions=com.intellij.langCodeStyleSettingsContributor) ![Internal][internal] | [`LanguageCodeStyleSettingsContributor`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsContributor.java) |
| [com.intellij.langCodeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.langCodeStyleSettingsProvider) | [`LanguageCodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java) |
| [com.intellij.lineIndentProvider](https://jb.gg/ipe?extensions=com.intellij.lineIndentProvider) | [`LineIndentProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/lineIndent/LineIndentProvider.java) |
| [com.intellij.predefinedCodeStyle](https://jb.gg/ipe?extensions=com.intellij.predefinedCodeStyle) | [`PredefinedCodeStyle`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/PredefinedCodeStyle.java) |
| [com.intellij.rearranger.ui](https://jb.gg/ipe?extensions=com.intellij.rearranger.ui) | [`Factory`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/std/ArrangementUiComponent.java) |

### IdeCore.xml

[`IdeCore.xml`](%gh-ic%/platform/ide-core-impl/resources/META-INF/IdeCore.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.applicationActivity](https://jb.gg/ipe?extensions=com.intellij.applicationActivity) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationActivity`](%gh-ic%/platform/ide-core/src/com/intellij/ide/ApplicationActivity.kt) |
| [com.intellij.applicationInitializedListener](https://jb.gg/ipe?extensions=com.intellij.applicationInitializedListener) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationInitializedListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/ApplicationInitializedListener.kt) |
| [com.intellij.notificationGroup](https://jb.gg/ipe?extensions=com.intellij.notificationGroup) | `n/a` |
| [com.intellij.registry.managed](https://jb.gg/ipe?extensions=com.intellij.registry.managed) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ManagedRegistry`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/util/registry/ManagedRegistry.kt) |
| [com.intellij.registryKey](https://jb.gg/ipe?extensions=com.intellij.registryKey) | `n/a` |

### IJent.xml

[`IJent.xml`](%gh-ic%/platform/ijent/resources/META-INF/IJent.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ijent.deploymentListener](https://jb.gg/ipe?extensions=com.intellij.ijent.deploymentListener) | [`IjentDeploymentListener`](%gh-ic%/platform/ijent/src/com/intellij/platform/ijent/spi/IjentDeployingOverShellProcessStrategy.kt) |

### Indexing.xml

[`Indexing.xml`](%gh-ic%/platform/indexing-api/resources/META-INF/Indexing.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.binaryFileSourceProvider](https://jb.gg/ipe?extensions=com.intellij.binaryFileSourceProvider) ![Internal][internal] | [`BinaryFileSourceProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/platform/indexing/BinaryFileSourceProvider.java) |
| [com.intellij.codeUsageScopeOptimizer](https://jb.gg/ipe?extensions=com.intellij.codeUsageScopeOptimizer) | [`ScopeOptimizer`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |
| [com.intellij.definitionsSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsSearch) ![Deprecated][deprecated] | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.dumbServiceInitializationCondition](https://jb.gg/ipe?extensions=com.intellij.dumbServiceInitializationCondition) ![Internal][internal] | [`DumbServiceInitializationCondition`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/DumbServiceInitializationCondition.java) |
| [com.intellij.fileBasedIndex](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndex) | [`FileBasedIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java) |
| [com.intellij.fileBasedIndexInfrastructureExtension](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexInfrastructureExtension) ![Internal][internal] | [`FileBasedIndexInfrastructureExtension`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/FileBasedIndexInfrastructureExtension.java) |
| [com.intellij.fileBasedIndexLayout](https://jb.gg/ipe?extensions=com.intellij.fileBasedIndexLayout) ![Internal][internal] | [`FileBasedIndexLayoutProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/storage/FileBasedIndexLayoutProvider.java) |
| [com.intellij.findModelExtension](https://jb.gg/ipe?extensions=com.intellij.findModelExtension) ![Internal][internal] | [`FindModelExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/find/FindModelExtension.java) |
| [com.intellij.indexableFilesContributor](https://jb.gg/ipe?extensions=com.intellij.indexableFilesContributor) ![Deprecated][deprecated] ![Removal][removal] | [`IndexableFilesContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFilesContributor.java) |
| [com.intellij.indexedRootsProvider](https://jb.gg/ipe?extensions=com.intellij.indexedRootsProvider) | [`IndexableSetContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/IndexableSetContributor.java) |
| [com.intellij.indexingFlavor](https://jb.gg/ipe?extensions=com.intellij.indexingFlavor) ![Experimental][experimental] ![Internal][internal] | [`FileIndexingFlavorProvider`](%gh-ic%/platform/core-api/src/com/intellij/util/indexing/flavor/FileIndexingFlavorProvider.java) |
| [com.intellij.languageStubDefinition](https://jb.gg/ipe?extensions=com.intellij.languageStubDefinition) ![Experimental][experimental] | [`LanguageStubDefinition`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/LanguageStubDefinition.kt) |
| [com.intellij.projectIndexingActivityHistoryListener](https://jb.gg/ipe?extensions=com.intellij.projectIndexingActivityHistoryListener) | [`ProjectIndexingActivityHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [com.intellij.referencesSearch](https://jb.gg/ipe?extensions=com.intellij.referencesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.stubElementRegistryExtension](https://jb.gg/ipe?extensions=com.intellij.stubElementRegistryExtension) ![Experimental][experimental] | [`StubRegistryExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubRegistryExtension.kt) |
| [com.intellij.stubIndex](https://jb.gg/ipe?extensions=com.intellij.stubIndex) | [`StubIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubIndexExtension.java) |
| [com.intellij.trigramIndexFilterExcludeExtension](https://jb.gg/ipe?extensions=com.intellij.trigramIndexFilterExcludeExtension) ![Internal][internal] | [`TrigramIndexFilterExcludeExtension`](%gh-ic%/platform/indexing-impl/src/com/intellij/find/ngrams/TrigramIndexFilter.kt) |

### Inspect.xml

[`Inspect.xml`](%gh-ic%/platform/inspect/resources/META-INF/Inspect.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.inspectResultsConsumer](https://jb.gg/ipe?extensions=com.intellij.inspectResultsConsumer) ![Internal][internal] | [`InspectResultsConsumer`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/InspectResultsConsumer.java) |
| [com.intellij.inspectionGroupProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionGroupProvider) | [`InspectionGroupProvider`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/inspectionProfile/InspectionGroupProvider.kt) |

### intellij.grid.core.impl.xml

[`intellij.grid.core.impl.xml`](%gh-ic%/grid/core-impl/resources/intellij.grid.core.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.datagrid.extractorsHelper](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.extractorsHelper) | [`ExtractorsHelper`](%gh-ic%/grid/core-impl/src/extractors/ExtractorsHelper.java) |
| [com.intellij.database.datagrid.formatterCreatorProvider](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.formatterCreatorProvider) | [`FormatterCreatorProvider`](%gh-ic%/grid/core-impl/src/datagrid/FormatterCreatorProvider.java) |
| [com.intellij.database.datagrid.objectNormalizerProvider](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.objectNormalizerProvider) | [`ObjectNormalizerProvider`](%gh-ic%/grid/core-impl/src/datagrid/ObjectNormalizerProvider.java) |

### intellij.grid.impl.xml

[`intellij.grid.impl.xml`](%gh-ic%/grid/impl/resources/intellij.grid.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.datagrid.cellViewerFactory](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.cellViewerFactory) | [`CellViewerFactory`](%gh-ic%/grid/impl/src/run/ui/CellViewer.kt) |
| [com.intellij.database.datagrid.valueEditorTab](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.valueEditorTab) | [`ValueEditorTab`](%gh-ic%/grid/impl/src/run/ui/ValueEditorTab.kt) |
| [com.intellij.database.minimizedFormatDetector](https://jb.gg/ipe?extensions=com.intellij.database.minimizedFormatDetector) | [`MinimizedFormatDetector`](%gh-ic%/grid/impl/src/run/ui/MinimizedFormatDetector.java) |

### intellij.notebooks.visualization.xml

[`intellij.notebooks.visualization.xml`](%gh-ic%/notebooks/visualization/resources/intellij.notebooks.visualization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider) | [`NotebookEditorAppearanceProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookEditorAppearanceProvider.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory) | [`NotebookOutputComponentFactory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputComponentFactory.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper) | [`NotebookOutputComponentWrapper`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputComponentWrapper.kt) |
| [org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor) | [`NotebookOutputDataKeyExtractor`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputDataKeyExtractor.kt) |
| [org.jetbrains.plugins.notebooks.inputFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.inputFactory) | [`InputFactory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellInlayController.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellInlayController](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellInlayController) | [`Factory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellInlayController.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellLinesProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellLinesProvider) | [`NotebookCellLinesProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellLinesProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider) | [`NotebookCellSelectionModelProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellSelectionModelProvider.kt) |
| [org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider) | [`NotebookIntervalPointerFactoryProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookIntervalPointerFactoryProvider.kt) |

### intellij.platform.execution.dashboard.xml

[`intellij.platform.execution.dashboard.xml`](%gh-ic%/platform/execution.dashboard/resources/intellij.platform.execution.dashboard.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.runDashboardChecker](https://jb.gg/ipe?extensions=com.intellij.runDashboardChecker) ![Experimental][experimental] | [`RunDashboardChecker`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardChecker.kt) |

### intellij.platform.experiment.xml

[`intellij.platform.experiment.xml`](%gh-ic%/platform/experiment/resources/intellij.platform.experiment.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.experiment.abExperimentOption](https://jb.gg/ipe?extensions=com.intellij.experiment.abExperimentOption) | [`ABExperimentOption`](%gh-ic%/platform/experiment/src/com/intellij/platform/experiment/ab/impl/experiment/ABExperimentOption.kt) |

### intellij.platform.feedback.xml

[`intellij.platform.feedback.xml`](%gh-ic%/platform/feedback/resources/intellij.platform.feedback.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.feedback.idleFeedbackSurvey](https://jb.gg/ipe?extensions=com.intellij.feedback.idleFeedbackSurvey) | [`FeedbackSurvey`](%gh-ic%/platform/feedback/src/com/intellij/platform/feedback/FeedbackSurvey.kt) |

### intellij.platform.ide.newUiOnboarding.xml

[`intellij.platform.ide.newUiOnboarding.xml`](%gh-ic%/platform/new-ui-onboarding/resources/intellij.platform.ide.newUiOnboarding.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ide.newUiOnboarding](https://jb.gg/ipe?extensions=com.intellij.ide.newUiOnboarding) | `n/a` |
| [com.intellij.ide.newUiOnboarding.step](https://jb.gg/ipe?extensions=com.intellij.ide.newUiOnboarding.step) ![Internal][internal] | [`NewUiOnboardingStep`](%gh-ic%/platform/new-ui-onboarding/src/com/intellij/platform/ide/newUiOnboarding/NewUiOnboardingStep.kt) |

### intellij.platform.inline.completion.xml

[`intellij.platform.inline.completion.xml`](%gh-ic%/platform/inline-completion/shared/resources/intellij.platform.inline.completion.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.inline.completion.shortcutHintListener](https://jb.gg/ipe?extensions=com.intellij.inline.completion.shortcutHintListener) ![Internal][internal] | [`InlineCompletionShortcutHintListener`](%gh-ic%/platform/inline-completion/shared/src/shortcut/InlineCompletionShortcutHintListener.kt) |

### intellij.platform.kernel.xml

[`intellij.platform.kernel.xml`](%gh-ic%/platform/kernel/shared/resources/intellij.platform.kernel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.platform.entityTypes](https://jb.gg/ipe?extensions=com.intellij.platform.entityTypes) | [`EntityTypeProvider`](%gh-ic%/platform/kernel/shared/src/EntityTypeProvider.kt) |

### intellij.platform.navbar.backend.xml

[`intellij.platform.navbar.backend.xml`](%gh-ic%/platform/navbar/backend/resources/intellij.platform.navbar.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.navbar.item.provider](https://jb.gg/ipe?extensions=com.intellij.navbar.item.provider) | [`NavBarItemProvider`](%gh-ic%/platform/navbar/backend/src/NavBarItemProvider.kt) |

### intellij.platform.project.xml

[`intellij.platform.project.xml`](%gh-ic%/platform/project/shared/resources/intellij.platform.project.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.project.projectIdResolver](https://jb.gg/ipe?extensions=com.intellij.project.projectIdResolver) ![Internal][internal] | [`ProjectIdResolver`](%gh-ic%/platform/project/shared/src/ProjectId.kt) |

### intellij.platform.remoteServers.impl.xml

[`intellij.platform.remoteServers.impl.xml`](%gh-ic%/platform/remote-servers/impl/resources/intellij.platform.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.defaultConfigurable.includeServerType](https://jb.gg/ipe?extensions=com.intellij.remoteServer.defaultConfigurable.includeServerType) | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |
| [com.intellij.remoteServer.deploymentConfiguration.stateProvider](https://jb.gg/ipe?extensions=com.intellij.remoteServer.deploymentConfiguration.stateProvider) | [`DeployToServerStateProvider`](%gh-ic%/platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerStateProvider.kt) |
| [com.intellij.remoteServer.deploymentSource.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.deploymentSource.type) | [`DeploymentSourceType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/deployment/DeploymentSourceType.java) |
| [com.intellij.remoteServer.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.remoteServer.runConfigurationExtension) | [`DeployToServerRunConfigurationExtension`](%gh-ic%/platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerRunConfigurationExtension.java) |
| [com.intellij.remoteServer.type](https://jb.gg/ipe?extensions=com.intellij.remoteServer.type) | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |

### intellij.platform.rpc.backend.xml

[`intellij.platform.rpc.backend.xml`](%gh-ic%/platform/kernel/rpc.backend/resources/intellij.platform.rpc.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.platform.rpc.backend.remoteApiProvider](https://jb.gg/ipe?extensions=com.intellij.platform.rpc.backend.remoteApiProvider) | [`RemoteApiProvider`](%gh-ic%/platform/kernel/rpc.backend/src/RemoteApiProvider.kt) |

### intellij.platform.searchEverywhere.xml

[`intellij.platform.searchEverywhere.xml`](%gh-ic%/platform/searchEverywhere/shared/resources/intellij.platform.searchEverywhere.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searchEverywhere.itemsProviderFactory](https://jb.gg/ipe?extensions=com.intellij.searchEverywhere.itemsProviderFactory) ![Experimental][experimental] ![Internal][internal] | [`SeItemsProviderFactory`](%gh-ic%/platform/searchEverywhere/shared/src/api/SeItemsProviderFactory.kt) |
| [com.intellij.searchEverywhere.tabProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhere.tabProvider) ![Experimental][experimental] ![Internal][internal] | [`SeTabProvider`](%gh-ic%/platform/searchEverywhere/shared/src/api/SeTabProvider.kt) |

### intellij.platform.settings.local.xml

[`intellij.platform.settings.local.xml`](%gh-ic%/platform/settings-local/resources/intellij.platform.settings.local.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.settingsController](https://jb.gg/ipe?extensions=com.intellij.settingsController) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`DelegatedSettingsController`](%gh-ic%/platform/settings/src/com/intellij/platform/settings/SettingsController.kt) |

### intellij.platform.statistics.devkit.xml

[`intellij.platform.statistics.devkit.xml`](%gh-ic%/platform/statistics/devkit/resources/intellij.platform.statistics.devkit.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.internal.statistic.devkit.toolwindow.logGroupActionsProvider](https://jb.gg/ipe?extensions=com.intellij.internal.statistic.devkit.toolwindow.logGroupActionsProvider) ![Internal][internal] | [`StatisticsLogGroupActionsProvider`](%gh-ic%/platform/statistics/devkit/src/com/intellij/internal/statistic/devkit/toolwindow/StatisticsLogGroupActionsProvider.kt) |

### intellij.platform.syntax.psi.xml

[`intellij.platform.syntax.psi.xml`](%gh-ic%/platform/syntax/syntax-psi/resources/META-INF/intellij.platform.syntax.psi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.syntax.elementTypeConverter](https://jb.gg/ipe?extensions=com.intellij.syntax.elementTypeConverter) | [`ElementTypeConverter`](%gh-ic%/platform/syntax/syntax-psi/src/com/intellij/platform/syntax/psi/ElementTypeConverter.kt) |
| [com.intellij.syntax.syntaxDefinition](https://jb.gg/ipe?extensions=com.intellij.syntax.syntaxDefinition) | [`LanguageSyntaxDefinition`](%gh-ic%/platform/syntax/syntax-psi/src/com/intellij/platform/syntax/psi/LanguageSyntaxDefinition.kt) |

### intellij.platform.tips.xml

[`intellij.platform.tips.xml`](%gh-ic%/platform/tips-of-the-day/resources/intellij.platform.tips.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tipAndTrickPromotionFactory](https://jb.gg/ipe?extensions=com.intellij.tipAndTrickPromotionFactory) ![Internal][internal] | [`TipAndTrickPromotionFactory`](%gh-ic%/platform/tips-of-the-day/src/com/intellij/ide/util/TipAndTrickPromotionFactory.kt) |

### intellij.settingsSync.core.xml

[`intellij.settingsSync.core.xml`](%gh-ic%/platform/settings-sync-core/resources/intellij.settingsSync.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.settingsSync.communicatorProvider](https://jb.gg/ipe?extensions=com.intellij.settingsSync.communicatorProvider) | [`SettingsSyncCommunicatorProvider`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/communicator/SettingsSyncCommunicatorProvider.kt) |
| [com.intellij.settingsSync.settingsProvider](https://jb.gg/ipe?extensions=com.intellij.settingsSync.settingsProvider) ![Internal][internal] | [`SettingsProvider`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/SettingsProvider.kt) |
| [com.intellij.settingsSyncMigration](https://jb.gg/ipe?extensions=com.intellij.settingsSyncMigration) ![Internal][internal] | [`SettingsSyncMigration`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/SettingsSyncMigration.kt) |

### LangExtensionPoints.xml

[`LangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.aliasingPsiTargetMapper](https://jb.gg/ipe?extensions=com.intellij.aliasingPsiTargetMapper) | [`AliasingPsiTargetMapper`](%gh-ic%/platform/core-api/src/com/intellij/psi/targets/AliasingPsiTargetMapper.java) |
| [com.intellij.analyzeStacktraceFilter](https://jb.gg/ipe?extensions=com.intellij.analyzeStacktraceFilter) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`Filter`](%gh-ic%/platform/execution/src/com/intellij/execution/filters/Filter.java) |
| [com.intellij.analyzeStacktraceRunContentProvider](https://jb.gg/ipe?extensions=com.intellij.analyzeStacktraceRunContentProvider) ![Experimental][experimental] ![Project-Level][project-level] | [`StacktraceTabContentProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/unscramble/StacktraceTabContentProvider.kt) |
| [com.intellij.anchorReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.anchorReferenceProvider) | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.annotator](https://jb.gg/ipe?extensions=com.intellij.annotator) ![DumbAware][dumb-aware] | [`Annotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) |
| [com.intellij.anonymousElementProvider](https://jb.gg/ipe?extensions=com.intellij.anonymousElementProvider) | [`AnonymousElementProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/navigation/AnonymousElementProvider.java) |
| [com.intellij.autoImportOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.autoImportOptionsProvider) ![Project-Level][project-level] | [`AutoImportOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/AutoImportOptionsProvider.java) |
| [com.intellij.bookmarkProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarkProvider) ![Project-Level][project-level] | [`BookmarkProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarkProvider.java) |
| [com.intellij.bookmarksListProvider](https://jb.gg/ipe?extensions=com.intellij.bookmarksListProvider) ![Project-Level][project-level] | [`BookmarksListProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListProvider.java) |
| [com.intellij.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.braceMatcher) | [`BraceMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/BraceMatcher.java) |
| [com.intellij.breadcrumbsInfoProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsInfoProvider) | [`BreadcrumbsProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java) |
| [com.intellij.cacheBuilder](https://jb.gg/ipe?extensions=com.intellij.cacheBuilder) ![Internal][internal] | [`WordsScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java) |
| [com.intellij.callHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.callHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.cantBeStatic](https://jb.gg/ipe?extensions=com.intellij.cantBeStatic) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeBlockSupportHandler](https://jb.gg/ipe?extensions=com.intellij.codeBlockSupportHandler) | [`CodeBlockSupportHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java) |
| [com.intellij.codeCompletionConfigurable](https://jb.gg/ipe?extensions=com.intellij.codeCompletionConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.codeFoldingOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.codeFoldingOptionsProvider) | [`CodeFoldingOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/CodeFoldingOptionsProvider.java) |
| [com.intellij.codeInsight.codeVision.settings.defaults](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVision.settings.defaults) ![Experimental][experimental] | [`CodeVisionSettingsDefaults`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettingsDefaults.kt) |
| [com.intellij.codeInsight.codeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProvider) ![Experimental][experimental] | [`CodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProvider.kt) |
| [com.intellij.codeInsight.codeVisionProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionProviderFactory) | [`CodeVisionProviderFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProviderFactory.kt) |
| [com.intellij.codeInsight.codeVisionSettingsPreviewLanguage](https://jb.gg/ipe?extensions=com.intellij.codeInsight.codeVisionSettingsPreviewLanguage) | `n/a` |
| [com.intellij.codeInsight.completion.applicable.command](https://jb.gg/ipe?extensions=com.intellij.codeInsight.completion.applicable.command) ![DumbAware][dumb-aware] | [`ApplicableCompletionCommand`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CompletionCommand.kt) |
| [com.intellij.codeInsight.completion.command.factory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.completion.command.factory) ![DumbAware][dumb-aware] | [`CommandCompletionFactory`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CommandCompletionFactory.kt) |
| [com.intellij.codeInsight.completion.command.provider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.completion.command.provider) ![DumbAware][dumb-aware] | [`CommandProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CommandProvider.kt) |
| [com.intellij.codeInsight.completion.intention.skipper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.completion.intention.skipper) | [`IntentionCommandSkipper`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/command/commands/DirectIntentionCommandProvider.kt) |
| [com.intellij.codeInsight.daemon.impl.injectedLanguageHighlightingRangeReducer](https://jb.gg/ipe?extensions=com.intellij.codeInsight.daemon.impl.injectedLanguageHighlightingRangeReducer) ![Non-Dynamic][non-dynamic] ![Experimental][experimental] | [`InjectedLanguageHighlightingRangeReducer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/InjectedLanguageHighlightingRangeReducer.java) |
| [com.intellij.codeInsight.daemonBoundCodeVisionProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.daemonBoundCodeVisionProvider) | [`DaemonBoundCodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hints/codeVision/DaemonBoundCodeVisionProvider.kt) |
| [com.intellij.codeInsight.declarativeInlayProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.declarativeInlayProvider) ![DumbAware][dumb-aware] | [`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProvider.kt) |
| [com.intellij.codeInsight.declarativeInlayProviderCustomSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.declarativeInlayProviderCustomSettingsProvider) | [`InlayHintsCustomSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsCustomSettingsProvider.kt) |
| [com.intellij.codeInsight.declarativeInlayProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.declarativeInlayProviderFactory) | [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProviderFactory.kt) |
| [com.intellij.codeInsight.delegateMethods](https://jb.gg/ipe?extensions=com.intellij.codeInsight.delegateMethods) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.gotoSuper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.gotoSuper) | [`CodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/CodeInsightActionHandler.java) |
| [com.intellij.codeInsight.implementMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implementMethod) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.inlayActionHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayActionHandler) | [`InlayActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayActionHandler.kt) |
| [com.intellij.codeInsight.inlayHintsSwitch](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayHintsSwitch) | [`InlayHintsSwitch`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSwitch.kt) |
| [com.intellij.codeInsight.inlayProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProvider) | [`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt) |
| [com.intellij.codeInsight.inlayProviderFactory](https://jb.gg/ipe?extensions=com.intellij.codeInsight.inlayProviderFactory) ![DumbAware][dumb-aware] | [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt) |
| [com.intellij.codeInsight.lineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.lineMarkerProvider) ![DumbAware][dumb-aware] | [`LineMarkerProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProvider.java) |
| [com.intellij.codeInsight.overrideMethod](https://jb.gg/ipe?extensions=com.intellij.codeInsight.overrideMethod) | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| [com.intellij.codeInsight.parameterInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo) ![DumbAware][dumb-aware] | [`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java) |
| [com.intellij.codeInsight.parameterInfo.controller.provider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.controller.provider) | [`ParameterInfoControllerProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoControllerProvider.java) |
| [com.intellij.codeInsight.parameterInfo.listener](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo.listener) | [`ParameterInfoListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoListener.java) |
| [com.intellij.codeInsight.parameterNameHints](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHints) | [`InlayParameterHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java) |
| [com.intellij.codeInsight.parameterNameHintsSuppressor](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterNameHintsSuppressor) | [`ParameterNameHintsSuppressor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt) |
| [com.intellij.codeInsight.surroundWithRangeAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeInsight.surroundWithRangeAdjuster) | [`SurroundWithRangeAdjuster`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/generation/surroundWith/SurroundWithRangeAdjuster.java) |
| [com.intellij.codeInsight.typeInfo](https://jb.gg/ipe?extensions=com.intellij.codeInsight.typeInfo) ![DumbAware][dumb-aware] | [`ExpressionTypeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/lang/ExpressionTypeProvider.java) |
| [com.intellij.codeInsight.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.unresolvedReferenceQuickFixProvider) | [`UnresolvedReferenceQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/quickfix/UnresolvedReferenceQuickFixProvider.java) |
| [com.intellij.codeStyleSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.codeStyleSettingsProvider) | [`CodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java) |
| [com.intellij.codeVisionPainterProvider](https://jb.gg/ipe?extensions=com.intellij.codeVisionPainterProvider) ![Non-Dynamic][non-dynamic] | [`ICodeVisionEntryBasePainter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/ui/renderers/painters/ICodeVisionEntryBasePainter.kt) |
| [com.intellij.colorAndFontDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.colorAndFontDescriptorProvider) | [`ColorAndFontDescriptorsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorAndFontDescriptorsProvider.java) |
| [com.intellij.colorAndFontPanelFactory](https://jb.gg/ipe?extensions=com.intellij.colorAndFontPanelFactory) | [`ColorAndFontPanelFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ColorAndFontPanelFactory.java) |
| [com.intellij.colorProvider](https://jb.gg/ipe?extensions=com.intellij.colorProvider) | [`ElementColorProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) |
| [com.intellij.colorSettingsPage](https://jb.gg/ipe?extensions=com.intellij.colorSettingsPage) | [`ColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java) |
| [com.intellij.commandLineInspectionProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.commandLineInspectionProjectConfigurator) ![Obsolete][obsolete] | [`CommandLineInspectionProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/CommandLineInspectionProjectConfigurator.java) |
| [com.intellij.commentTokenSetProvider](https://jb.gg/ipe?extensions=com.intellij.commentTokenSetProvider) | [`CommentTokenSetProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/cache/CommentTokenSetProvider.java) |
| [com.intellij.concatenationAwareInjector](https://jb.gg/ipe?extensions=com.intellij.concatenationAwareInjector) ![Project-Level][project-level] | [`ConcatenationAwareInjector`](%gh-ic%/platform/lang-api/src/com/intellij/lang/injection/ConcatenationAwareInjector.java) |
| [com.intellij.configurationProducer](https://jb.gg/ipe?extensions=com.intellij.configurationProducer) ![Deprecated][deprecated] ![DumbAware][dumb-aware] | [`RuntimeConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/junit/RuntimeConfigurationProducer.java) |
| [com.intellij.configurationType](https://jb.gg/ipe?extensions=com.intellij.configurationType) ![DumbAware][dumb-aware] | [`ConfigurationType`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java) |
| [com.intellij.console.folding](https://jb.gg/ipe?extensions=com.intellij.console.folding) | [`ConsoleFolding`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ConsoleFolding.java) |
| [com.intellij.consoleActionsPostProcessor](https://jb.gg/ipe?extensions=com.intellij.consoleActionsPostProcessor) | [`ConsoleActionsPostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/ConsoleActionsPostProcessor.java) |
| [com.intellij.consoleConfigurableProvider](https://jb.gg/ipe?extensions=com.intellij.consoleConfigurableProvider) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.consoleFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleFilterProvider) | [`ConsoleFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleFilterProvider.java) |
| [com.intellij.consoleHistoryModelProvider](https://jb.gg/ipe?extensions=com.intellij.consoleHistoryModelProvider) | [`ConsoleHistoryModelProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleHistoryModelProvider.java) |
| [com.intellij.consoleInputFilterProvider](https://jb.gg/ipe?extensions=com.intellij.consoleInputFilterProvider) | [`ConsoleInputFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleInputFilterProvider.java) |
| [com.intellij.contributedReferencesAnnotator](https://jb.gg/ipe?extensions=com.intellij.contributedReferencesAnnotator) | [`ContributedReferencesAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ContributedReferencesAnnotator.java) |
| [com.intellij.createFromTemplateActionReplacer](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateActionReplacer) | [`CreateFromTemplateActionReplacer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateActionReplacer.java) |
| [com.intellij.createFromTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.createFromTemplateHandler) | [`CreateFromTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateHandler.java) |
| [com.intellij.customFoldingProvider](https://jb.gg/ipe?extensions=com.intellij.customFoldingProvider) | [`CustomFoldingProvider`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/CustomFoldingProvider.java) |
| [com.intellij.customLiveTemplate](https://jb.gg/ipe?extensions=com.intellij.customLiveTemplate) | [`CustomLiveTemplate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/CustomLiveTemplate.java) |
| [com.intellij.customScopesFilter](https://jb.gg/ipe?extensions=com.intellij.customScopesFilter) | [`CustomScopesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesFilter.java) |
| [com.intellij.customScopesProvider](https://jb.gg/ipe?extensions=com.intellij.customScopesProvider) ![Project-Level][project-level] | [`CustomScopesProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesProvider.java) |
| [com.intellij.customUsageSearcher](https://jb.gg/ipe?extensions=com.intellij.customUsageSearcher) | [`CustomUsageSearcher`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/CustomUsageSearcher.java) |
| [com.intellij.daemon.changeLocalityDetector](https://jb.gg/ipe?extensions=com.intellij.daemon.changeLocalityDetector) | [`ChangeLocalityDetector`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ChangeLocalityDetector.java) |
| [com.intellij.daemon.essentialHighlightingRestarterDisablement](https://jb.gg/ipe?extensions=com.intellij.daemon.essentialHighlightingRestarterDisablement) | [`EssentialHighlightingRestarterDisablement`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/EssentialHighlightingRestarterDisablement.java) |
| [com.intellij.daemon.externalAnnotatorsFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.externalAnnotatorsFilter) | [`ExternalAnnotatorsFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) |
| [com.intellij.daemon.highlightInfoFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.highlightInfoFilter) | [`HighlightInfoFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoFilter.java) |
| [com.intellij.daemon.indentsPassFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.indentsPassFilter) | [`IndentsPassFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IndentsPassFilter.java) |
| [com.intellij.daemon.intentionActionFilter](https://jb.gg/ipe?extensions=com.intellij.daemon.intentionActionFilter) | [`IntentionActionFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/IntentionActionFilter.java) |
| [com.intellij.daemon.statusItemMerger](https://jb.gg/ipe?extensions=com.intellij.daemon.statusItemMerger) | [`StatusItemMerger`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/StatusItemMerger.java) |
| [com.intellij.daemon.tooltipActionProvider](https://jb.gg/ipe?extensions=com.intellij.daemon.tooltipActionProvider) | [`TooltipActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/tooltips/TooltipActionProvider.java) |
| [com.intellij.declarationRangeHandler](https://jb.gg/ipe?extensions=com.intellij.declarationRangeHandler) | [`DeclarationRangeHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) |
| [com.intellij.defaultHighlightingSettingProvider](https://jb.gg/ipe?extensions=com.intellij.defaultHighlightingSettingProvider) ![DumbAware][dumb-aware] | [`DefaultHighlightingSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/DefaultHighlightingSettingProvider.java) |
| [com.intellij.defaultLiveTemplates](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplates) | `n/a` |
| [com.intellij.defaultLiveTemplatesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultLiveTemplatesProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`DefaultLiveTemplatesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) |
| [com.intellij.defaultTemplatePropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.defaultTemplatePropertiesProvider) | [`DefaultTemplatePropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/DefaultTemplatePropertiesProvider.java) |
| [com.intellij.definitionsScopedSearch](https://jb.gg/ipe?extensions=com.intellij.definitionsScopedSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.diffPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.diffPreviewProvider) ![Non-Dynamic][non-dynamic] | [`DiffPreviewProvider`](%gh-ic%/platform/diff-api/src/com/intellij/openapi/diff/impl/settings/DiffPreviewProvider.java) |
| [com.intellij.documentation.documentationDownloader](https://jb.gg/ipe?extensions=com.intellij.documentation.documentationDownloader) ![Experimental][experimental] | [`DocumentationDownloader`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/actions/DocumentationDownloader.kt) |
| [com.intellij.dynamicContextProvider](https://jb.gg/ipe?extensions=com.intellij.dynamicContextProvider) | [`DynamicContextProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/paths/DynamicContextProvider.java) |
| [com.intellij.editorAppearanceConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorAppearanceConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.editorOptionsProvider) | [`EditorOptionsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsProvider.java) |
| [com.intellij.editorSearchAreaProvider](https://jb.gg/ipe?extensions=com.intellij.editorSearchAreaProvider) ![Experimental][experimental] | [`EditorSearchAreaProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/livePreview/EditorSearchAreaProvider.java) |
| [com.intellij.editorSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorSmartKeysConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.editorTabsConfigurable](https://jb.gg/ipe?extensions=com.intellij.editorTabsConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.elementDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.elementDescriptionProvider) | [`ElementDescriptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java) |
| [com.intellij.elementPreviewProvider](https://jb.gg/ipe?extensions=com.intellij.elementPreviewProvider) | [`ElementPreviewProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/preview/ElementPreviewProvider.java) |
| [com.intellij.elementSignatureProvider](https://jb.gg/ipe?extensions=com.intellij.elementSignatureProvider) | [`ElementSignatureProvider`](%gh-ic%/platform/foldings/src/com/intellij/codeInsight/folding/impl/ElementSignatureProvider.java) |
| [com.intellij.environmentKeyProvider](https://jb.gg/ipe?extensions=com.intellij.environmentKeyProvider) ![Experimental][experimental] | [`EnvironmentKeyProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/environment/EnvironmentKeyProvider.kt) |
| [com.intellij.errorOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.errorOptionsProvider) | [`ErrorOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/ErrorOptionsProvider.java) |
| [com.intellij.errorQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.errorQuickFixProvider) ![DumbAware][dumb-aware] | [`ErrorQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/ErrorQuickFixProvider.java) |
| [com.intellij.executionTargetLanguageRuntimeType](https://jb.gg/ipe?extensions=com.intellij.executionTargetLanguageRuntimeType) | [`LanguageRuntimeType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/LanguageRuntimeType.kt) |
| [com.intellij.executionTargetProvider](https://jb.gg/ipe?extensions=com.intellij.executionTargetProvider) | [`ExecutionTargetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/ExecutionTargetProvider.java) |
| [com.intellij.executionTargetType](https://jb.gg/ipe?extensions=com.intellij.executionTargetType) | [`TargetEnvironmentType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/TargetEnvironmentType.kt) |
| [com.intellij.executor](https://jb.gg/ipe?extensions=com.intellij.executor) | [`Executor`](%gh-ic%/platform/execution/src/com/intellij/execution/Executor.java) |
| [com.intellij.externalAnnotator](https://jb.gg/ipe?extensions=com.intellij.externalAnnotator) ![DumbAware][dumb-aware] | [`ExternalAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) |
| [com.intellij.facet.toolWindow](https://jb.gg/ipe?extensions=com.intellij.facet.toolWindow) ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| [com.intellij.facetType](https://jb.gg/ipe?extensions=com.intellij.facetType) | [`FacetType`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetType.java) |
| [com.intellij.favoriteNodeProvider](https://jb.gg/ipe?extensions=com.intellij.favoriteNodeProvider) ![Deprecated][deprecated] ![Removal][removal] ![Project-Level][project-level] | [`FavoriteNodeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/favoritesTreeView/FavoriteNodeProvider.java) |
| [com.intellij.favoritesListProvider](https://jb.gg/ipe?extensions=com.intellij.favoritesListProvider) ![Deprecated][deprecated] ![Internal][internal] ![Project-Level][project-level] | [`FavoritesListProvider`](%gh-ic%/platform/favoritesTreeView/src/com/intellij/ide/favoritesTreeView/FavoritesListProvider.java) |
| [com.intellij.filePasteProvider](https://jb.gg/ipe?extensions=com.intellij.filePasteProvider) | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| [com.intellij.fileStructureGroupRuleProvider](https://jb.gg/ipe?extensions=com.intellij.fileStructureGroupRuleProvider) | [`FileStructureGroupRuleProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/FileStructureGroupRuleProvider.java) |
| [com.intellij.fileTemplateGroup](https://jb.gg/ipe?extensions=com.intellij.fileTemplateGroup) | [`FileTemplateGroupDescriptorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptorFactory.java) |
| [com.intellij.fileType.fileViewProviderFactory](https://jb.gg/ipe?extensions=com.intellij.fileType.fileViewProviderFactory) | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| [com.intellij.fileTypeStatisticProvider](https://jb.gg/ipe?extensions=com.intellij.fileTypeStatisticProvider) ![Deprecated][deprecated] | [`FileTypeStatisticProvider`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/statistic/fileTypes/FileTypeStatisticProvider.java) |
| [com.intellij.filetype.prebuiltStubsProvider](https://jb.gg/ipe?extensions=com.intellij.filetype.prebuiltStubsProvider) ![Deprecated][deprecated] ![Internal][internal] | [`PrebuiltStubsProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/stubs/PrebuiltStubs.kt) |
| [com.intellij.filetype.stubBuilder](https://jb.gg/ipe?extensions=com.intellij.filetype.stubBuilder) | [`BinaryFileStubBuilder`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/BinaryFileStubBuilder.java) |
| [com.intellij.findInProjectExtension](https://jb.gg/ipe?extensions=com.intellij.findInProjectExtension) ![Internal][internal] | [`FindInProjectExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/FindInProjectExtension.kt) |
| [com.intellij.findUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.findUsagesHandlerFactory) ![Project-Level][project-level] | [`FindUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/FindUsagesHandlerFactory.java) |
| [com.intellij.focusModeProvider](https://jb.gg/ipe?extensions=com.intellij.focusModeProvider) ![Experimental][experimental] | [`FocusModeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/focusMode/FocusModeProvider.java) |
| [com.intellij.framework.detector](https://jb.gg/ipe?extensions=com.intellij.framework.detector) | [`FrameworkDetector`](%gh-ic%/platform/lang-api/src/com/intellij/framework/detection/FrameworkDetector.java) |
| [com.intellij.generalCodeStyleOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalCodeStyleOptionsProvider) | [`GeneralCodeStyleOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/GeneralCodeStyleOptionsProvider.java) |
| [com.intellij.globalIndexFilter](https://jb.gg/ipe?extensions=com.intellij.globalIndexFilter) ![Internal][internal] | [`GlobalIndexFilter`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/GlobalIndexFilter.java) |
| [com.intellij.goto.nonProjectScopeDisabler](https://jb.gg/ipe?extensions=com.intellij.goto.nonProjectScopeDisabler) ![Internal][internal] | `n/a` |
| [com.intellij.gotoActionAliasMatcher](https://jb.gg/ipe?extensions=com.intellij.gotoActionAliasMatcher) | [`GotoActionAliasMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoActionAliasMatcher.java) |
| [com.intellij.gotoClassContributor](https://jb.gg/ipe?extensions=com.intellij.gotoClassContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileContributor](https://jb.gg/ipe?extensions=com.intellij.gotoFileContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoFileCustomizer](https://jb.gg/ipe?extensions=com.intellij.gotoFileCustomizer) | [`GotoFileCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoFileCustomizer.java) |
| [com.intellij.gotoRelatedProvider](https://jb.gg/ipe?extensions=com.intellij.gotoRelatedProvider) | [`GotoRelatedProvider`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/GotoRelatedProvider.java) |
| [com.intellij.gotoSymbolContributor](https://jb.gg/ipe?extensions=com.intellij.gotoSymbolContributor) | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| [com.intellij.gotoTargetPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetPresentationProvider) | [`GotoTargetPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetPresentationProvider.java) |
| [com.intellij.gotoTargetRendererProvider](https://jb.gg/ipe?extensions=com.intellij.gotoTargetRendererProvider) ![Deprecated][deprecated] | [`GotoTargetRendererProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetRendererProvider.java) |
| [com.intellij.heavyBracesHighlighter](https://jb.gg/ipe?extensions=com.intellij.heavyBracesHighlighter) ![Non-Dynamic][non-dynamic] | [`HeavyBraceHighlighter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HeavyBraceHighlighter.java) |
| [com.intellij.hectorComponentProvider](https://jb.gg/ipe?extensions=com.intellij.hectorComponentProvider) ![Project-Level][project-level] | [`HectorComponentPanelsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/HectorComponentPanelsProvider.java) |
| [com.intellij.highlightInfoPostFilter](https://jb.gg/ipe?extensions=com.intellij.highlightInfoPostFilter) ![Project-Level][project-level] | [`HighlightInfoPostFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoPostFilter.java) |
| [com.intellij.highlightRangeExtension](https://jb.gg/ipe?extensions=com.intellij.highlightRangeExtension) | [`HighlightRangeExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightRangeExtension.java) |
| [com.intellij.highlightUsagesHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.highlightUsagesHandlerFactory) ![DumbAware][dumb-aware] | [`HighlightUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) |
| [com.intellij.highlightVisitor](https://jb.gg/ipe?extensions=com.intellij.highlightVisitor) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`HighlightVisitor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightVisitor.java) |
| [com.intellij.highlightingPassFactory](https://jb.gg/ipe?extensions=com.intellij.highlightingPassFactory) | [`TextEditorHighlightingPassFactoryRegistrar`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeHighlighting/TextEditorHighlightingPassFactoryRegistrar.java) |
| [com.intellij.idIndexer](https://jb.gg/ipe?extensions=com.intellij.idIndexer) | [`IdIndexer`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/id/IdIndexer.java) |
| [com.intellij.implementationViewDocumentFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewDocumentFactory) | [`ImplementationViewDocumentFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewDocumentFactory.kt) |
| [com.intellij.implementationViewSessionFactory](https://jb.gg/ipe?extensions=com.intellij.implementationViewSessionFactory) | [`ImplementationViewSessionFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewSession.kt) |
| [com.intellij.implicitUsageProvider](https://jb.gg/ipe?extensions=com.intellij.implicitUsageProvider) | [`ImplicitUsageProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ImplicitUsageProvider.java) |
| [com.intellij.importBlockRangeProvider](https://jb.gg/ipe?extensions=com.intellij.importBlockRangeProvider) | [`ImportBlockRangeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/lang/imports/ImportBlockRangeProvider.kt) |
| [com.intellij.importFilteringRule](https://jb.gg/ipe?extensions=com.intellij.importFilteringRule) | [`ImportFilteringRule`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/ImportFilteringRule.java) |
| [com.intellij.include.provider](https://jb.gg/ipe?extensions=com.intellij.include.provider) | [`FileIncludeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeProvider.java) |
| [com.intellij.indexPatternBuilder](https://jb.gg/ipe?extensions=com.intellij.indexPatternBuilder) | [`IndexPatternBuilder`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/search/IndexPatternBuilder.java) |
| [com.intellij.indexPatternProvider](https://jb.gg/ipe?extensions=com.intellij.indexPatternProvider) ![Non-Dynamic][non-dynamic] | [`IndexPatternProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/IndexPatternProvider.java) |
| [com.intellij.indexPatternSearch](https://jb.gg/ipe?extensions=com.intellij.indexPatternSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.indexableEntityProvider](https://jb.gg/ipe?extensions=com.intellij.indexableEntityProvider) ![Experimental][experimental] ![Internal][internal] | [`IndexableEntityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/IndexableEntityProvider.java) |
| [com.intellij.indexableIteratorBuilderHandler](https://jb.gg/ipe?extensions=com.intellij.indexableIteratorBuilderHandler) | [`IndexableIteratorBuilderHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/builders/IndexableIteratorBuilderHandler.java) |
| [com.intellij.inlineCompletionConfigurable](https://jb.gg/ipe?extensions=com.intellij.inlineCompletionConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.inlinePrompt](https://jb.gg/ipe?extensions=com.intellij.inlinePrompt) ![Internal][internal] | [`InlinePromptExtension`](%gh-ic%/platform/analysis-api/src/com/intellij/inlinePrompt/InlinePromptExtension.kt) |
| [com.intellij.inspectionProfileActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionProfileActionProvider) | [`InspectionProfileActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionProfileActionProvider.java) |
| [com.intellij.inspectionResultsExportActionProvider](https://jb.gg/ipe?extensions=com.intellij.inspectionResultsExportActionProvider) ![DumbAware][dumb-aware] | [`InspectionResultsExportActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/ui/actions/InspectionResultsExportActionProvider.kt) |
| [com.intellij.inspectionTreeAdvertiser](https://jb.gg/ipe?extensions=com.intellij.inspectionTreeAdvertiser) | [`InspectionTreeAdvertiser`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionTreeAdvertiser.java) |
| [com.intellij.intentionMenuContributor](https://jb.gg/ipe?extensions=com.intellij.intentionMenuContributor) ![Internal][internal] | [`IntentionMenuContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IntentionMenuContributor.java) |
| [com.intellij.intentionPopupProvider](https://jb.gg/ipe?extensions=com.intellij.intentionPopupProvider) | [`IntentionPopupProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionPopupProvider.kt) |
| [com.intellij.intentionsOrderProvider](https://jb.gg/ipe?extensions=com.intellij.intentionsOrderProvider) | [`IntentionsOrderProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionsOrderProvider.java) |
| [com.intellij.internalFileTemplate](https://jb.gg/ipe?extensions=com.intellij.internalFileTemplate) | `n/a` |
| [com.intellij.internalHighlightingLayerSupplier](https://jb.gg/ipe?extensions=com.intellij.internalHighlightingLayerSupplier) ![Experimental][experimental] ![Internal][internal] | [`InternalLayerSupplier`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/InternalLayerSupplier.java) |
| [com.intellij.lang.braceMatcher](https://jb.gg/ipe?extensions=com.intellij.lang.braceMatcher) | [`PairedBraceMatcher`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java) |
| [com.intellij.lang.directNavigationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.directNavigationProvider) ![Experimental][experimental] | [`DirectNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java) |
| [com.intellij.lang.documentation.syntaxHighlightingHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.lang.documentation.syntaxHighlightingHandlerFactory) ![Internal][internal] | [`QuickDocSyntaxHighlightingHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/QuickDocSyntaxHighlightingHandler.kt) |
| [com.intellij.lang.documentationFixer](https://jb.gg/ipe?extensions=com.intellij.lang.documentationFixer) | [`DocCommentFixer`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocCommentFixer.java) |
| [com.intellij.lang.documentationToolWindowManager](https://jb.gg/ipe?extensions=com.intellij.lang.documentationToolWindowManager) ![Deprecated][deprecated] | [`DocToolWindowManager`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocToolWindowManager.java) |
| [com.intellij.lang.findUsagesProvider](https://jb.gg/ipe?extensions=com.intellij.lang.findUsagesProvider) | [`FindUsagesProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) |
| [com.intellij.lang.floatingToolbar](https://jb.gg/ipe?extensions=com.intellij.lang.floatingToolbar) ![Experimental][experimental] | [`FloatingToolbarCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/codeFloatingToolbar/FloatingToolbarCustomizer.kt) |
| [com.intellij.lang.floatingToolbarCustomizer](https://jb.gg/ipe?extensions=com.intellij.lang.floatingToolbarCustomizer) ![Deprecated][deprecated] ![Experimental][experimental] | [`FloatingToolbarCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/codeFloatingToolbar/FloatingToolbarCustomizer.kt) |
| [com.intellij.lang.foldingBuilder](https://jb.gg/ipe?extensions=com.intellij.lang.foldingBuilder) ![DumbAware][dumb-aware] | [`FoldingBuilder`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) |
| [com.intellij.lang.implementationTextProcessor](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextProcessor) | [`ImplementationTextProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextProcessor.java) |
| [com.intellij.lang.implementationTextSelectioner](https://jb.gg/ipe?extensions=com.intellij.lang.implementationTextSelectioner) | [`ImplementationTextSelectioner`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) |
| [com.intellij.lang.logicalStructureElementsProvider](https://jb.gg/ipe?extensions=com.intellij.lang.logicalStructureElementsProvider) ![Experimental][experimental] | [`LogicalStructureElementsProvider`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/logical/LogicalStructureElementsProvider.kt) |
| [com.intellij.lang.logicalStructureTreeElementProvider](https://jb.gg/ipe?extensions=com.intellij.lang.logicalStructureTreeElementProvider) ![Experimental][experimental] | [`LogicalStructureTreeElementProvider`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/logical/LogicalStructureTreeElementProvider.kt) |
| [com.intellij.lang.psiElementExternalizer](https://jb.gg/ipe?extensions=com.intellij.lang.psiElementExternalizer) | [`PsiElementExternalizer`](%gh-ic%/platform/lang-api/src/com/intellij/lang/PsiElementExternalizer.java) |
| [com.intellij.lang.psiStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.lang.psiStructureViewFactory) | [`PsiStructureViewFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) |
| [com.intellij.lang.sliceProvider](https://jb.gg/ipe?extensions=com.intellij.lang.sliceProvider) | [`SliceLanguageSupportProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/slicer/SliceLanguageSupportProvider.java) |
| [com.intellij.lang.structureViewExtension](https://jb.gg/ipe?extensions=com.intellij.lang.structureViewExtension) | [`StructureViewExtension`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/StructureViewExtension.java) |
| [com.intellij.lang.surroundDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.surroundDescriptor) | [`SurroundDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) |
| [com.intellij.lang.symbolSearchTarget](https://jb.gg/ipe?extensions=com.intellij.lang.symbolSearchTarget) | [`SymbolSearchTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java) |
| [com.intellij.lang.symbolTypeProvider](https://jb.gg/ipe?extensions=com.intellij.lang.symbolTypeProvider) ![Experimental][experimental] | [`SymbolTypeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/SymbolTypeProvider.java) |
| [com.intellij.lang.syntaxHighlighter](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighter) | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |
| [com.intellij.lang.unwrapDescriptor](https://jb.gg/ipe?extensions=com.intellij.lang.unwrapDescriptor) | [`UnwrapDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/unwrap/UnwrapDescriptor.java) |
| [com.intellij.library.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.library.presentationProvider) | [`LibraryPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryPresentationProvider.java) |
| [com.intellij.library.type](https://jb.gg/ipe?extensions=com.intellij.library.type) | [`LibraryType`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryType.java) |
| [com.intellij.librarySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.librarySettingsProvider) | [`LibrarySettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/configuration/LibrarySettingsProvider.java) |
| [com.intellij.liveTemplateSubstitutor](https://jb.gg/ipe?extensions=com.intellij.liveTemplateSubstitutor) | [`TemplateSubstitutor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/TemplateSubstitutor.java) |
| [com.intellij.longLineInspectionPolicy](https://jb.gg/ipe?extensions=com.intellij.longLineInspectionPolicy) | [`LongLineInspectionPolicy`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/longLine/LongLineInspectionPolicy.java) |
| [com.intellij.macro](https://jb.gg/ipe?extensions=com.intellij.macro) | [`Macro`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/Macro.java) |
| [com.intellij.macroFilter](https://jb.gg/ipe?extensions=com.intellij.macroFilter) | [`MacroFilter`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/MacroFilter.java) |
| [com.intellij.marketplaceLocalRanker](https://jb.gg/ipe?extensions=com.intellij.marketplaceLocalRanker) ![Internal][internal] | [`MarketplaceLocalRanker`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/marketplace/ranking/MarketplaceLocalRanker.kt) |
| [com.intellij.marketplaceTextualFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.marketplaceTextualFeaturesProvider) ![Internal][internal] | [`MarketplaceTextualFeaturesProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/marketplace/statistics/features/MarketplaceTextualFeaturesProvider.kt) |
| [com.intellij.metaDataContributor](https://jb.gg/ipe?extensions=com.intellij.metaDataContributor) | [`MetaDataContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/meta/MetaDataContributor.java) |
| [com.intellij.methodHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.methodHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.methodNavigationOffsetProvider](https://jb.gg/ipe?extensions=com.intellij.methodNavigationOffsetProvider) | [`MethodNavigationOffsetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/navigation/MethodNavigationOffsetProvider.java) |
| [com.intellij.microservices.clientGenerator](https://jb.gg/ipe?extensions=com.intellij.microservices.clientGenerator) ![Experimental][experimental] | [`ClientGenerator`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/client/generator/ClientGenerator.kt) |
| [com.intellij.microservices.endpointsProjectModel](https://jb.gg/ipe?extensions=com.intellij.microservices.endpointsProjectModel) | [`EndpointsProjectModel`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsProjectModels.kt) |
| [com.intellij.microservices.endpointsProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.endpointsProvider) | [`EndpointsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsProvider.kt) |
| [com.intellij.microservices.endpointsSidePanelProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.endpointsSidePanelProvider) | [`EndpointsSidePanelProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsSidePanelProvider.kt) |
| [com.intellij.microservices.featuresAvailabilityProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.featuresAvailabilityProvider) ![Experimental][experimental] | [`MicroservicesFeaturesAvailabilityProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/MicroservicesFeaturesAvailabilityProvider.kt) |
| [com.intellij.microservices.oasSerializationCompatibilityProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.oasSerializationCompatibilityProvider) ![Deprecated][deprecated] ![Internal][internal] | [`OasSerializationCompatibilityProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/oas/serialization/OasSerializationUtils.kt) |
| [com.intellij.microservices.oasSpecificationProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.oasSpecificationProvider) | [`OasSpecificationProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/oas/OasSpecificationProvider.kt) |
| [com.intellij.microservices.requestNavigator](https://jb.gg/ipe?extensions=com.intellij.microservices.requestNavigator) | [`RequestNavigator`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/http/request/RequestNavigator.kt) |
| [com.intellij.microservices.urlInlayAction](https://jb.gg/ipe?extensions=com.intellij.microservices.urlInlayAction) | [`UrlPathInlayAction`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/inlay/UrlPathInlayAction.kt) |
| [com.intellij.microservices.urlInlayLanguagesProvider](https://jb.gg/ipe?extensions=com.intellij.microservices.urlInlayLanguagesProvider) | [`UrlPathInlayLanguagesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/inlay/UrlPathInlayLanguagesProvider.kt) |
| [com.intellij.microservices.urlResolverFactory](https://jb.gg/ipe?extensions=com.intellij.microservices.urlResolverFactory) | [`UrlResolverFactory`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/UrlResolverFactory.kt) |
| [com.intellij.mlCodeCompletionConfigurable](https://jb.gg/ipe?extensions=com.intellij.mlCodeCompletionConfigurable) ![Internal][internal] | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.mlCompletionFeaturesCollector](https://jb.gg/ipe?extensions=com.intellij.mlCompletionFeaturesCollector) ![Internal][internal] | [`InlineCompletionFeaturesCollector`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/inline/completion/features/InlineCompletionFeaturesCollector.kt) |
| [com.intellij.modelScopeItemPresenter](https://jb.gg/ipe?extensions=com.intellij.modelScopeItemPresenter) ![Non-Dynamic][non-dynamic] | [`ModelScopeItemPresenter`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/dialog/ModelScopeItemPresenter.java) |
| [com.intellij.module.workingDirectoryProvider](https://jb.gg/ipe?extensions=com.intellij.module.workingDirectoryProvider) | [`WorkingDirectoryProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/openapi/module/WorkingDirectoryProvider.java) |
| [com.intellij.moduleBuilder](https://jb.gg/ipe?extensions=com.intellij.moduleBuilder) | [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) |
| [com.intellij.moduleConfigurationEditorProvider](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurationEditorProvider) | [`ModuleConfigurationEditorProvider`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/roots/ui/configuration/ModuleConfigurationEditorProvider.java) |
| [com.intellij.moduleNameGenerator](https://jb.gg/ipe?extensions=com.intellij.moduleNameGenerator) ![Experimental][experimental] | [`ModuleNameGenerator`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleNameGenerator.java) |
| [com.intellij.moduleRendererFactory](https://jb.gg/ipe?extensions=com.intellij.moduleRendererFactory) | [`ModuleRendererFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/ModuleRendererFactory.java) |
| [com.intellij.moduleType](https://jb.gg/ipe?extensions=com.intellij.moduleType) ![Obsolete][obsolete] | [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) |
| [com.intellij.multiLangCommenter](https://jb.gg/ipe?extensions=com.intellij.multiLangCommenter) | [`MultipleLangCommentProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/templateLanguages/MultipleLangCommentProvider.java) |
| [com.intellij.multipleRunLocationsProvider](https://jb.gg/ipe?extensions=com.intellij.multipleRunLocationsProvider) | [`MultipleRunLocationsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/MultipleRunLocationsProvider.kt) |
| [com.intellij.navbar](https://jb.gg/ipe?extensions=com.intellij.navbar) | [`NavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelExtension.java) |
| [com.intellij.newFileActionCategoryHandler](https://jb.gg/ipe?extensions=com.intellij.newFileActionCategoryHandler) ![Experimental][experimental] | [`NewFileActionCategoryHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/NewFileActionCategoryHandler.java) |
| [com.intellij.optionsApplicabilityFilter](https://jb.gg/ipe?extensions=com.intellij.optionsApplicabilityFilter) | [`OptionsApplicabilityFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/OptionsApplicabilityFilter.java) |
| [com.intellij.outOfSourcesChecker](https://jb.gg/ipe?extensions=com.intellij.outOfSourcesChecker) | [`OutOfSourcesChecker`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/projectRoots/OutOfSourcesChecker.java) |
| [com.intellij.overrideImplementsAnnotationsFilter](https://jb.gg/ipe?extensions=com.intellij.overrideImplementsAnnotationsFilter) | [`OverrideImplementsAnnotationsFilter`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsFilter.java) |
| [com.intellij.packageDependencies.visitor](https://jb.gg/ipe?extensions=com.intellij.packageDependencies.visitor) | [`DependencyVisitorFactory`](%gh-ic%/platform/analysis-impl/src/com/intellij/packageDependencies/DependencyVisitorFactory.java) |
| [com.intellij.packageGroupRuleProvider](https://jb.gg/ipe?extensions=com.intellij.packageGroupRuleProvider) ![Internal][internal] | [`PackageGroupRuleProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/PackageGroupRuleProvider.kt) |
| [com.intellij.pathReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.pathReferenceProvider) | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| [com.intellij.patternDialectProvider](https://jb.gg/ipe?extensions=com.intellij.patternDialectProvider) ![Non-Dynamic][non-dynamic] | [`PatternDialectProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/packageDependencies/ui/PatternDialectProvider.java) |
| [com.intellij.patterns.patternClass](https://jb.gg/ipe?extensions=com.intellij.patterns.patternClass) | `Object` |
| [com.intellij.platform.backend.documentation.inlineDocumentationProvider](https://jb.gg/ipe?extensions=com.intellij.platform.backend.documentation.inlineDocumentationProvider) | [`InlineDocumentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/InlineDocumentationProvider.java) |
| [com.intellij.platform.backend.documentation.linkHandler](https://jb.gg/ipe?extensions=com.intellij.platform.backend.documentation.linkHandler) | [`DocumentationLinkHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationLinkHandler.java) |
| [com.intellij.platform.backend.documentation.psiTargetProvider](https://jb.gg/ipe?extensions=com.intellij.platform.backend.documentation.psiTargetProvider) | [`PsiDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/PsiDocumentationTargetProvider.java) |
| [com.intellij.platform.backend.documentation.symbolTargetProvider](https://jb.gg/ipe?extensions=com.intellij.platform.backend.documentation.symbolTargetProvider) ![Experimental][experimental] | [`SymbolDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/SymbolDocumentationTargetProvider.java) |
| [com.intellij.platform.backend.documentation.targetProvider](https://jb.gg/ipe?extensions=com.intellij.platform.backend.documentation.targetProvider) | [`DocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTargetProvider.java) |
| [com.intellij.platform.ijent.ijentExecFileProvider](https://jb.gg/ipe?extensions=com.intellij.platform.ijent.ijentExecFileProvider) ![Internal][internal] | [`IjentExecFileProvider`](%gh-ic%/platform/ijent/src/com/intellij/platform/ijent/IjentExecFileProvider.kt) |
| [com.intellij.platform.lang.lsWidget.itemsProvider](https://jb.gg/ipe?extensions=com.intellij.platform.lang.lsWidget.itemsProvider) ![Experimental][experimental] | [`LanguageServiceWidgetItemsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/platform/lang/lsWidget/LanguageServiceWidgetItemsProvider.kt) |
| [com.intellij.pluginExternalResources.unpackToPlugin](https://jb.gg/ipe?extensions=com.intellij.pluginExternalResources.unpackToPlugin) | `n/a` |
| [com.intellij.presentationProvider](https://jb.gg/ipe?extensions=com.intellij.presentationProvider) | [`PresentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/PresentationProvider.java) |
| [com.intellij.printHandler](https://jb.gg/ipe?extensions=com.intellij.printHandler) | [`PrintActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/PrintActionHandler.java) |
| [com.intellij.printOption](https://jb.gg/ipe?extensions=com.intellij.printOption) | [`PrintOption`](%gh-ic%/platform/lang-impl/src/com/intellij/codeEditor/printing/PrintOption.java) |
| [com.intellij.problemFileHighlightFilter](https://jb.gg/ipe?extensions=com.intellij.problemFileHighlightFilter) ![Project-Level][project-level] | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
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
| [com.intellij.projectView.externalLibraries.workspaceModelNodesProvider](https://jb.gg/ipe?extensions=com.intellij.projectView.externalLibraries.workspaceModelNodesProvider) ![Experimental][experimental] | [`ExternalLibrariesWorkspaceModelNodesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/nodes/ExternalLibrariesWorkspaceModelNodesProvider.java) |
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
| [com.intellij.refactoring.codeVisionSupport](https://jb.gg/ipe?extensions=com.intellij.refactoring.codeVisionSupport) | [`RefactoringCodeVisionSupport`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/RefactoringCodeVisionSupport.java) |
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
| [com.intellij.runConfigurationProducer](https://jb.gg/ipe?extensions=com.intellij.runConfigurationProducer) ![DumbAware][dumb-aware] | [`RunConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/RunConfigurationProducer.java) |
| [com.intellij.runConfigurationTargetEnvironmentAdjusterFactory](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTargetEnvironmentAdjusterFactory) ![Internal][internal] | [`Factory`](%gh-ic%/platform/execution/src/com/intellij/execution/target/RunConfigurationTargetEnvironmentAdjuster.kt) |
| [com.intellij.runConfigurationTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.runConfigurationTemplateProvider) ![Project-Level][project-level] | [`RunConfigurationTemplateProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/impl/RunManagerImpl.kt) |
| [com.intellij.runConfigurationsSettings](https://jb.gg/ipe?extensions=com.intellij.runConfigurationsSettings) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`RunConfigurationsSettings`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfigurationsSettings.java) |
| [com.intellij.runDashboardCustomizer](https://jb.gg/ipe?extensions=com.intellij.runDashboardCustomizer) | [`RunDashboardCustomizer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardCustomizer.java) |
| [com.intellij.runDashboardDefaultTypesProvider](https://jb.gg/ipe?extensions=com.intellij.runDashboardDefaultTypesProvider) | [`RunDashboardDefaultTypesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardDefaultTypesProvider.java) |
| [com.intellij.runDashboardGroupingRule](https://jb.gg/ipe?extensions=com.intellij.runDashboardGroupingRule) | [`RunDashboardGroupingRule`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardGroupingRule.java) |
| [com.intellij.runLineMarkerContributor](https://jb.gg/ipe?extensions=com.intellij.runLineMarkerContributor) ![DumbAware][dumb-aware] | [`RunLineMarkerContributor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/lineMarker/RunLineMarkerContributor.java) |
| [com.intellij.runToolbarProcess](https://jb.gg/ipe?extensions=com.intellij.runToolbarProcess) | [`RunToolbarProcess`](%gh-ic%/platform/execution/src/com/intellij/execution/runToolbar/RunToolbarProcess.kt) |
| [com.intellij.runningApplicationUpdaterProvider](https://jb.gg/ipe?extensions=com.intellij.runningApplicationUpdaterProvider) | [`RunningApplicationUpdaterProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/update/RunningApplicationUpdaterProvider.java) |
| [com.intellij.safeDeleteTargetProvider](https://jb.gg/ipe?extensions=com.intellij.safeDeleteTargetProvider) | [`SafeDeleteTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/safeDelete/api/SafeDeleteTargetProvider.kt) |
| [com.intellij.saveFileAsTemplateHandler](https://jb.gg/ipe?extensions=com.intellij.saveFileAsTemplateHandler) | [`SaveFileAsTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SaveFileAsTemplateHandler.java) |
| [com.intellij.scopeDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.scopeDescriptorProvider) | [`ScopeDescriptorProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeDescriptorProvider.kt) |
| [com.intellij.scopeParserExtension](https://jb.gg/ipe?extensions=com.intellij.scopeParserExtension) | [`PackageSetParserExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/search/scope/packageSet/PackageSetParserExtension.java) |
| [com.intellij.scratch.creationHelper](https://jb.gg/ipe?extensions=com.intellij.scratch.creationHelper) | [`ScratchFileCreationHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/scratch/ScratchFileCreationHelper.java) |
| [com.intellij.scratch.rootType](https://jb.gg/ipe?extensions=com.intellij.scratch.rootType) | [`RootType`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/scratch/RootType.java) |
| [com.intellij.sdkDownload](https://jb.gg/ipe?extensions=com.intellij.sdkDownload) | [`SdkDownload`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/projectRoot/SdkDownload.java) |
| [com.intellij.sdkFinder](https://jb.gg/ipe?extensions=com.intellij.sdkFinder) | [`SdkFinder`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/SdkFinder.java) |
| [com.intellij.sdkType](https://jb.gg/ipe?extensions=com.intellij.sdkType) | [`SdkType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/projectRoots/SdkType.java) |
| [com.intellij.searchEverywhereClassifier](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereClassifier) | [`SearchEverywhereClassifier`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SearchEverywhereClassifier.java) |
| [com.intellij.searchEverywhereContributor](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereContributor) | [`SearchEverywhereContributorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributorFactory.java) |
| [com.intellij.searchEverywhereEssentialContributorsMarker](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereEssentialContributorsMarker) ![Internal][internal] | [`SearchEverywhereEssentialContributorMarker`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereEssentialContributorMarker.kt) |
| [com.intellij.searchEverywhereMlContributorReplacement](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMlContributorReplacement) ![Internal][internal] | [`SearchEverywhereMlContributorReplacement`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlContributorReplacement.kt) |
| [com.intellij.searchEverywhereMlService](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMlService) ![Internal][internal] | [`SearchEverywhereMlService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| [com.intellij.searchEverywherePreviewPrimaryUsageFinder](https://jb.gg/ipe?extensions=com.intellij.searchEverywherePreviewPrimaryUsageFinder) ![Internal][internal] | [`SearchEverywherePreviewPrimaryUsageFinder`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywherePreviewPrimaryUsageFinder.kt) |
| [com.intellij.searchEverywhereRemoteConverter](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereRemoteConverter) | [`RemoteSearchEverywhereConverterSupplier`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/remote/RemoteSearchEverywhereConverterSupplier.java) |
| [com.intellij.searchEverywhereReorderingService](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereReorderingService) ![Internal][internal] | [`SearchEverywhereReorderingService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereReorderingService.kt) |
| [com.intellij.searchEverywhereResultsEqualityProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereResultsEqualityProvider) | [`SEResultsEqualityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEResultsEqualityProvider.kt) |
| [com.intellij.searchEverywhereSpellingCorrector](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereSpellingCorrector) ![Internal][internal] | [`SearchEverywhereSpellingCorrectorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereSpellingCorrector.kt) |
| [com.intellij.searchScopesProvider](https://jb.gg/ipe?extensions=com.intellij.searchScopesProvider) | [`SearchScopeProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/search/SearchScopeProvider.java) |
| [com.intellij.searcher](https://jb.gg/ipe?extensions=com.intellij.searcher) | [`Searcher`](%gh-ic%/platform/indexing-api/src/com/intellij/model/search/Searcher.kt) |
| [com.intellij.semContributor](https://jb.gg/ipe?extensions=com.intellij.semContributor) | [`SemContributor`](%gh-ic%/platform/lang-api/src/com/intellij/semantic/SemContributor.java) |
| [com.intellij.serviceViewContributor](https://jb.gg/ipe?extensions=com.intellij.serviceViewContributor) | [`ServiceViewContributor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceViewContributor.java) |
| [com.intellij.silentChangeVetoer](https://jb.gg/ipe?extensions=com.intellij.silentChangeVetoer) ![Internal][internal] | [`SilentChangeVetoer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SilentChangeVetoer.java) |
| [com.intellij.stacktrace.fold](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold) | `n/a` |
| [com.intellij.stacktrace.fold.line.modifier](https://jb.gg/ipe?extensions=com.intellij.stacktrace.fold.line.modifier) ![Experimental][experimental] | [`ConsoleLineModifier`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleLineModifier.java) |
| [com.intellij.statistician](https://jb.gg/ipe?extensions=com.intellij.statistician) | [`Statistician`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/statistics/Statistician.java) |
| [com.intellij.stepsBeforeRunProvider](https://jb.gg/ipe?extensions=com.intellij.stepsBeforeRunProvider) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`BeforeRunTaskProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/BeforeRunTaskProvider.java) |
| [com.intellij.structureViewBuilder](https://jb.gg/ipe?extensions=com.intellij.structureViewBuilder) | [`StructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewBuilder.kt) |
| [com.intellij.symbolDeclarationPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.symbolDeclarationPresentationProvider) | [`SymbolDeclarationPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/presentation/SymbolDeclarationPresentationProvider.java) |
| [com.intellij.symbolNavigation](https://jb.gg/ipe?extensions=com.intellij.symbolNavigation) ![Experimental][experimental] | [`SymbolNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java) |
| [com.intellij.targetElementEvaluator](https://jb.gg/ipe?extensions=com.intellij.targetElementEvaluator) | [`TargetElementEvaluator`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/TargetElementEvaluator.java) |
| [com.intellij.targetElementUtilExtender](https://jb.gg/ipe?extensions=com.intellij.targetElementUtilExtender) | [`TargetElementUtilExtender`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/TargetElementUtilExtender.java) |
| [com.intellij.templateCompletionProcessor](https://jb.gg/ipe?extensions=com.intellij.templateCompletionProcessor) | [`TemplateCompletionProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/macro/TemplateCompletionProcessor.java) |
| [com.intellij.testActionProvider](https://jb.gg/ipe?extensions=com.intellij.testActionProvider) | [`ToggleModelActionProvider`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/ToggleModelActionProvider.java) |
| [com.intellij.testCreator](https://jb.gg/ipe?extensions=com.intellij.testCreator) ![DumbAware][dumb-aware] | [`TestCreator`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestCreator.java) |
| [com.intellij.testDiffProvider](https://jb.gg/ipe?extensions=com.intellij.testDiffProvider) | [`TestDiffProvider`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/actions/TestDiffProvider.java) |
| [com.intellij.testFinder](https://jb.gg/ipe?extensions=com.intellij.testFinder) | [`TestFinder`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestFinder.java) |
| [com.intellij.testSrcLocator](https://jb.gg/ipe?extensions=com.intellij.testSrcLocator) ![Deprecated][deprecated] ![Removal][removal] | [`TestLocationProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/testIntegration/TestLocationProvider.java) |
| [com.intellij.todoExtraPlaces](https://jb.gg/ipe?extensions=com.intellij.todoExtraPlaces) | [`ExtraPlaceChecker`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/todo/TodoIndexers.java) |
| [com.intellij.todoIndexer](https://jb.gg/ipe?extensions=com.intellij.todoIndexer) | [`DataIndexer`](%gh-ic%/platform/util/src/com/intellij/util/indexing/DataIndexer.java) |
| [com.intellij.toolsCustomizer](https://jb.gg/ipe?extensions=com.intellij.toolsCustomizer) | [`ToolsCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsCustomizer.java) |
| [com.intellij.toolsProvider](https://jb.gg/ipe?extensions=com.intellij.toolsProvider) | [`ToolsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsProvider.java) |
| [com.intellij.trafficLightRendererContributor](https://jb.gg/ipe?extensions=com.intellij.trafficLightRendererContributor) | [`TrafficLightRendererContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/TrafficLightRendererContributor.java) |
| [com.intellij.treeGenerator](https://jb.gg/ipe?extensions=com.intellij.treeGenerator) | [`TreeGenerator`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeGenerator.java) |
| [com.intellij.treeStructureProvider](https://jb.gg/ipe?extensions=com.intellij.treeStructureProvider) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`TreeStructureProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) |
| [com.intellij.typeDeclarationProvider](https://jb.gg/ipe?extensions=com.intellij.typeDeclarationProvider) | [`TypeDeclarationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/actions/TypeDeclarationProvider.java) |
| [com.intellij.typeHierarchyProvider](https://jb.gg/ipe?extensions=com.intellij.typeHierarchyProvider) | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| [com.intellij.typeIcon](https://jb.gg/ipe?extensions=com.intellij.typeIcon) ![Internal][internal] | `Object` |
| [com.intellij.typeName](https://jb.gg/ipe?extensions=com.intellij.typeName) | `Object` |
| [com.intellij.uiDebuggerExtension](https://jb.gg/ipe?extensions=com.intellij.uiDebuggerExtension) ![Non-Dynamic][non-dynamic] | [`UiDebuggerExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ui/debugger/UiDebuggerExtension.java) |
| [com.intellij.usageContextPanelProvider](https://jb.gg/ipe?extensions=com.intellij.usageContextPanelProvider) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageContextPanel.java) |
| [com.intellij.usageFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.usageFeaturesProvider) ![Experimental][experimental] | [`UsageSimilarityFeaturesProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/similarity/features/UsageSimilarityFeaturesProvider.java) |
| [com.intellij.usageFilteringRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageFilteringRuleProvider) | [`UsageFilteringRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleProvider.java) |
| [com.intellij.usageGroupingRuleProvider](https://jb.gg/ipe?extensions=com.intellij.usageGroupingRuleProvider) | [`UsageGroupingRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageGroupingRuleProvider.java) |
| [com.intellij.usageTargetProvider](https://jb.gg/ipe?extensions=com.intellij.usageTargetProvider) ![DumbAware][dumb-aware] | [`UsageTargetProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageTargetProvider.java) |
| [com.intellij.usageToPsiElementProvider](https://jb.gg/ipe?extensions=com.intellij.usageToPsiElementProvider) | [`UsageToPsiElementProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageToPsiElementProvider.java) |
| [com.intellij.usageTypeProvider](https://jb.gg/ipe?extensions=com.intellij.usageTypeProvider) | [`UsageTypeProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/rules/UsageTypeProvider.java) |
| [com.intellij.usageViewElementsListener](https://jb.gg/ipe?extensions=com.intellij.usageViewElementsListener) | [`UsageViewElementsListener`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewElementsListener.java) |
| [com.intellij.usageViewFactory](https://jb.gg/ipe?extensions=com.intellij.usageViewFactory) | [`UsageViewFactory`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewFactory.java) |
| [com.intellij.usageViewPopupFactory](https://jb.gg/ipe?extensions=com.intellij.usageViewPopupFactory) ![Internal][internal] | [`UsageViewPopupFactory`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewPopup.kt) |

### LangExtensions.xml

[`LangExtensions.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.facetIgnorer](https://jb.gg/ipe?extensions=com.intellij.facetIgnorer) ![Internal][internal] | [`FacetIgnorer`](%gh-ic%/platform/lang-impl/src/com/intellij/facet/impl/invalid/FacetIgnorer.kt) |

### ml.xml

[`ml.xml`](%gh-ic%/platform/ml-impl/resources/META-INF/ml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.platform.ml.impl.approach](https://jb.gg/ipe?extensions=com.intellij.platform.ml.impl.approach) ![Internal][internal] | [`MLTaskApproachBuilder`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/MLTask.kt) |
| [com.intellij.platform.ml.impl.turboComplete.smartPipelineRunner](https://jb.gg/ipe?extensions=com.intellij.platform.ml.impl.turboComplete.smartPipelineRunner) ![Internal][internal] | [`SmartPipelineRunner`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/SmartPipelineRunner.kt) |

### OpenTelemetryExtensions.xml

[`OpenTelemetryExtensions.xml`](%gh-ic%/platform/diagnostic/telemetry-impl/resources/META-INF/OpenTelemetryExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.openTelemetryExporterProvider](https://jb.gg/ipe?extensions=com.intellij.openTelemetryExporterProvider) ![Internal][internal] | [`OpenTelemetryExporterProvider`](%gh-ic%/platform/diagnostic/telemetry-impl/src/OpenTelemetryExporterProvider.kt) |

### PlatformExecutionActions.xml

[`PlatformExecutionActions.xml`](%gh-ic%/platform/execution-impl/resources/META-INF/PlatformExecutionActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.execution.displayDescriptorChooser](https://jb.gg/ipe?extensions=com.intellij.execution.displayDescriptorChooser) | [`DisplayDescriptorChooser`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/StoppableRunDescriptors.kt) |
| [com.intellij.multilaunch.condition.template](https://jb.gg/ipe?extensions=com.intellij.multilaunch.condition.template) ![Experimental][experimental] | [`ConditionTemplate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/conditions/ConditionTemplate.kt) |
| [com.intellij.multilaunch.task.definition](https://jb.gg/ipe?extensions=com.intellij.multilaunch.task.definition) ![Experimental][experimental] | [`TaskExecutableTemplate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/executables/TaskExecutableTemplate.kt) |

### PlatformExtensionPoints.xml

[`PlatformExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.ApplicationLoadListener](https://jb.gg/ipe?extensions=com.intellij.ApplicationLoadListener) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationLoadListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ApplicationLoadListener.kt) |
| [com.intellij.aboutPopupDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.aboutPopupDescriptionProvider) | [`AboutPopupDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/AboutPopupDescriptionProvider.kt) |
| [com.intellij.achromatopsiaSupport](https://jb.gg/ipe?extensions=com.intellij.achromatopsiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.actionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.actionConfigurationCustomizer) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionConfigurationCustomizer.kt) |
| [com.intellij.actionFromOptionDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.actionFromOptionDescriptorProvider) | [`ActionFromOptionDescriptorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/ActionFromOptionDescriptorProvider.java) |
| [com.intellij.actionGroupCustomization](https://jb.gg/ipe?extensions=com.intellij.actionGroupCustomization) ![Internal][internal] | [`ActionGroupCustomizationExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/ActionGroupCustomizationService.kt) |
| [com.intellij.actionOnSave](https://jb.gg/ipe?extensions=com.intellij.actionOnSave) | [`ActionOnSave`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/impl/ActionsOnSaveFileDocumentManagerListener.kt) |
| [com.intellij.actionOnSaveInfoProvider](https://jb.gg/ipe?extensions=com.intellij.actionOnSaveInfoProvider) | [`ActionOnSaveInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/ActionOnSaveInfoProvider.java) |
| [com.intellij.actionPromoter](https://jb.gg/ipe?extensions=com.intellij.actionPromoter) | [`ActionPromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/ActionPromoter.java) |
| [com.intellij.activityTracker](https://jb.gg/ipe?extensions=com.intellij.activityTracker) | [`ActivityTracker`](%gh-ic%/platform/backend/observation/src/com/intellij/platform/backend/observation/ActivityTracker.kt) |
| [com.intellij.additionalTextAttributes](https://jb.gg/ipe?extensions=com.intellij.additionalTextAttributes) | `n/a` |
| [com.intellij.advancedSetting](https://jb.gg/ipe?extensions=com.intellij.advancedSetting) | `n/a` |
| [com.intellij.appStarter](https://jb.gg/ipe?extensions=com.intellij.appStarter) | [`ApplicationStarter`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationStarter.kt) |
| [com.intellij.applicationConfigurable](https://jb.gg/ipe?extensions=com.intellij.applicationConfigurable) | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.applicationSettings](https://jb.gg/ipe?extensions=com.intellij.applicationSettings) ![Experimental][experimental] | [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) |
| [com.intellij.authorizationProvider](https://jb.gg/ipe?extensions=com.intellij.authorizationProvider) ![Internal][internal] | [`AuthorizationProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/AuthorizationProvider.java) |
| [com.intellij.backedVirtualFileProvider](https://jb.gg/ipe?extensions=com.intellij.backedVirtualFileProvider) ![Experimental][experimental] | [`BackedVirtualFileProvider`](%gh-ic%/platform/core-api/src/com/intellij/notebook/editor/BackedVirtualFileProvider.java) |
| [com.intellij.beforeRunStartupTasks](https://jb.gg/ipe?extensions=com.intellij.beforeRunStartupTasks) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`BeforeRunStartupTasks`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/startup/BeforeRunStartupTasks.kt) |
| [com.intellij.breadcrumbsPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.breadcrumbsPresentationProvider) | [`BreadcrumbsPresentationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/xml/breadcrumbs/BreadcrumbsPresentationProvider.java) |
| [com.intellij.bundledColorScheme](https://jb.gg/ipe?extensions=com.intellij.bundledColorScheme) | `n/a` |
| [com.intellij.bundledInspectionProfile](https://jb.gg/ipe?extensions=com.intellij.bundledInspectionProfile) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.bundledKeymap](https://jb.gg/ipe?extensions=com.intellij.bundledKeymap) | `n/a` |
| [com.intellij.bundledQuickListsProvider](https://jb.gg/ipe?extensions=com.intellij.bundledQuickListsProvider) | [`BundledQuickListsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/BundledQuickListsProvider.java) |
| [com.intellij.cachedValuesFactory](https://jb.gg/ipe?extensions=com.intellij.cachedValuesFactory) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`CachedValuesFactory`](%gh-ic%/platform/core-impl/src/com/intellij/util/CachedValuesFactory.kt) |
| [com.intellij.cachesInvalidator](https://jb.gg/ipe?extensions=com.intellij.cachesInvalidator) | [`CachesInvalidator`](%gh-ic%/platform/ide-core/src/com/intellij/ide/caches/CachesInvalidator.java) |
| [com.intellij.cefDelegate](https://jb.gg/ipe?extensions=com.intellij.cefDelegate) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`CefDelegate`](%gh-ic%/platform/ui.jcef/jcef/CefDelegate.java) |
| [com.intellij.classpathStorageProvider](https://jb.gg/ipe?extensions=com.intellij.classpathStorageProvider) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ClasspathStorageProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/impl/storage/ClasspathStorageProvider.java) |
| [com.intellij.codeInsight.folding.collapseBlockHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.folding.collapseBlockHandler) | [`CollapseBlockHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/folding/CollapseBlockHandler.java) |
| [com.intellij.codeInsight.linkHandler](https://jb.gg/ipe?extensions=com.intellij.codeInsight.linkHandler) | [`TooltipLinkHandler`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/highlighting/TooltipLinkHandler.java) |
| [com.intellij.codeInsight.template.postfixTemplateProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.template.postfixTemplateProvider) | [`PostfixTemplateProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplateProvider.java) |
| [com.intellij.codeWithMe.authorizationProvider](https://jb.gg/ipe?extensions=com.intellij.codeWithMe.authorizationProvider) ![Deprecated][deprecated] ![Experimental][experimental] ![Internal][internal] | [`CodeWithMeAuthorizationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/codeWithMe/CodeWithMeAuthorizationProvider.kt) |
| [com.intellij.codeWithMe.serverUrlProvider](https://jb.gg/ipe?extensions=com.intellij.codeWithMe.serverUrlProvider) ![Deprecated][deprecated] ![Experimental][experimental] ![Internal][internal] | [`CodeWithMeServerUrlProvider`](%gh-ic%/platform/platform-api/src/com/intellij/codeWithMe/CodeWithMeServerUrlProvider.kt) |
| [com.intellij.colorAndFontOptionsImportHandler](https://jb.gg/ipe?extensions=com.intellij.colorAndFontOptionsImportHandler) ![Non-Dynamic][non-dynamic] | [`ImportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ImportHandler.java) |
| [com.intellij.colorPickerListenerFactory](https://jb.gg/ipe?extensions=com.intellij.colorPickerListenerFactory) | [`ColorPickerListenerFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ColorPickerListenerFactory.java) |
| [com.intellij.commandLineEnvCustomizer](https://jb.gg/ipe?extensions=com.intellij.commandLineEnvCustomizer) ![Internal][internal] | [`CommandLineEnvCustomizer`](%gh-ic%/platform/platform-api/src/com/intellij/execution/process/GeneralCommandLineEnvCustomizerService.kt) |
| [com.intellij.config.codeVisionGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.codeVisionGroupSettingProvider) | [`CodeVisionGroupSettingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/settings/CodeVisionGroupSettingProvider.kt) |
| [com.intellij.config.inlayGroupSettingProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlayGroupSettingProvider) | [`InlayGroupSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayGroupSettingProvider.kt) |
| [com.intellij.config.inlaySettingsProvider](https://jb.gg/ipe?extensions=com.intellij.config.inlaySettingsProvider) | [`InlaySettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlaySettingsProvider.kt) |
| [com.intellij.configurablesPatcher](https://jb.gg/ipe?extensions=com.intellij.configurablesPatcher) ![Experimental][experimental] ![Internal][internal] | [`ConfigurablesPatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/ConfigurablesPatcher.java) |
| [com.intellij.contentTabActionProvider](https://jb.gg/ipe?extensions=com.intellij.contentTabActionProvider) | [`ContentTabActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/content/tabActions/ContentTabAction.kt) |
| [com.intellij.coursesStorageProvider](https://jb.gg/ipe?extensions=com.intellij.coursesStorageProvider) | [`CoursesStorageProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/learnIde/coursesInProgress/CoursesStorageProvider.kt) |
| [com.intellij.credentialStore](https://jb.gg/ipe?extensions=com.intellij.credentialStore) ![Non-Dynamic][non-dynamic] | [`CredentialStoreFactory`](%gh-ic%/platform/credential-store-impl/src/credentialStore/CredentialStoreFactory.java) |
| [com.intellij.customFileDropHandler](https://jb.gg/ipe?extensions=com.intellij.customFileDropHandler) ![Deprecated][deprecated] ![Project-Level][project-level] | [`CustomFileDropHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/CustomFileDropHandler.java) |
| [com.intellij.customTypeRpcSerializer](https://jb.gg/ipe?extensions=com.intellij.customTypeRpcSerializer) ![Internal][internal] | [`CustomTypeRpcSerializer`](%gh-ic%/platform/platform-impl/rpc/src/com/intellij/ide/rpc/CustomTypeRpcSerializer.kt) |
| [com.intellij.customizableActionGroupProvider](https://jb.gg/ipe?extensions=com.intellij.customizableActionGroupProvider) | [`CustomizableActionGroupProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomizableActionGroupProvider.java) |
| [com.intellij.cutElementMarker](https://jb.gg/ipe?extensions=com.intellij.cutElementMarker) | [`CutElementMarker`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/CutElementMarker.java) |
| [com.intellij.dataValidators](https://jb.gg/ipe?extensions=com.intellij.dataValidators) ![Internal][internal] | [`DataValidators`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/DataValidators.java) |
| [com.intellij.defaultToolWindowLayout](https://jb.gg/ipe?extensions=com.intellij.defaultToolWindowLayout) | [`DefaultToolWindowLayoutExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/toolWindow/defaultToolWindowlayoutProvider.kt) |
| [com.intellij.defender.config](https://jb.gg/ipe?extensions=com.intellij.defender.config) | [`Extension`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/WindowsDefenderChecker.java) |
| [com.intellij.dependencyCollector](https://jb.gg/ipe?extensions=com.intellij.dependencyCollector) | [`DependencyCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/DependencyCollector.kt) |
| [com.intellij.dependencySupport](https://jb.gg/ipe?extensions=com.intellij.dependencySupport) | `n/a` |
| [com.intellij.deuteranopiaSupport](https://jb.gg/ipe?extensions=com.intellij.deuteranopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.dialogInvocationPlace](https://jb.gg/ipe?extensions=com.intellij.dialogInvocationPlace) ![Internal][internal] | `n/a` |
| [com.intellij.diff.DiffExtension](https://jb.gg/ipe?extensions=com.intellij.diff.DiffExtension) | [`DiffExtension`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffExtension.java) |
| [com.intellij.diff.DiffTool](https://jb.gg/ipe?extensions=com.intellij.diff.DiffTool) | [`DiffTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffTool.java) |
| [com.intellij.diff.actions.ShowDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.diff.editor.diffRequestProcessorEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.diff.editor.diffRequestProcessorEditorCustomizer) | [`DiffRequestProcessorEditorCustomizer`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/editor/DiffRequestProcessorEditorCustomizer.kt) |
| [com.intellij.diff.impl.DiffToolSubstitutor](https://jb.gg/ipe?extensions=com.intellij.diff.impl.DiffToolSubstitutor) ![Internal][internal] | [`DiffToolSubstitutor`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/impl/DiffToolSubstitutor.java) |
| [com.intellij.diff.lang.DiffIgnoredRangeProvider](https://jb.gg/ipe?extensions=com.intellij.diff.lang.DiffIgnoredRangeProvider) | [`DiffIgnoredRangeProvider`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/lang/DiffIgnoredRangeProvider.java) |
| [com.intellij.diff.merge.MergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.MergeTool) | [`MergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/MergeTool.java) |
| [com.intellij.diff.merge.external.AutomaticExternalMergeTool](https://jb.gg/ipe?extensions=com.intellij.diff.merge.external.AutomaticExternalMergeTool) | [`AutomaticExternalMergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/external/AutomaticExternalMergeTool.java) |
| [com.intellij.directoryProjectConfigurator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectConfigurator) | [`DirectoryProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectConfigurator.kt) |
| [com.intellij.directoryProjectGenerator](https://jb.gg/ipe?extensions=com.intellij.directoryProjectGenerator) | [`DirectoryProjectGenerator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectGenerator.java) |
| [com.intellij.documentationActionProvider](https://jb.gg/ipe?extensions=com.intellij.documentationActionProvider) | [`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) |
| [com.intellij.dynamicActionConfigurationCustomizer](https://jb.gg/ipe?extensions=com.intellij.dynamicActionConfigurationCustomizer) | [`DynamicActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/DynamicActionConfigurationCustomizer.java) |
| [com.intellij.editor.injectedFileChangesHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.editor.injectedFileChangesHandlerProvider) | [`InjectedFileChangesHandlerProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/injected/editor/InjectedFileChangesHandlerProvider.java) |
| [com.intellij.editor.linePainter](https://jb.gg/ipe?extensions=com.intellij.editor.linePainter) | [`EditorLinePainter`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/EditorLinePainter.java) |
| [com.intellij.editorActionHandler](https://jb.gg/ipe?extensions=com.intellij.editorActionHandler) | [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) |
| [com.intellij.editorAutoClosingHandler](https://jb.gg/ipe?extensions=com.intellij.editorAutoClosingHandler) ![Experimental][experimental] ![Internal][internal] | [`EditorAutoClosingHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorAutoClosingHandler.kt) |
| [com.intellij.editorFactoryMouseListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseListener) | [`EditorMouseListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseListener.java) |
| [com.intellij.editorFactoryMouseMotionListener](https://jb.gg/ipe?extensions=com.intellij.editorFactoryMouseMotionListener) | [`EditorMouseMotionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseMotionListener.java) |
| [com.intellij.editorFileSwapper](https://jb.gg/ipe?extensions=com.intellij.editorFileSwapper) | [`EditorFileSwapper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorFileSwapper.java) |
| [com.intellij.editorFloatingToolbarProvider](https://jb.gg/ipe?extensions=com.intellij.editorFloatingToolbarProvider) | [`FloatingToolbarProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/toolbar/floating/FloatingToolbarProvider.kt) |
| [com.intellij.editorHighlighterProvider](https://jb.gg/ipe?extensions=com.intellij.editorHighlighterProvider) | [`EditorHighlighterProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/EditorHighlighterProvider.java) |
| [com.intellij.editorNavigation](https://jb.gg/ipe?extensions=com.intellij.editorNavigation) | [`EditorNavigationDelegate`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/EditorNavigationDelegate.java) |
| [com.intellij.editorNotificationProvider](https://jb.gg/ipe?extensions=com.intellij.editorNotificationProvider) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| [com.intellij.editorTabColorProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabColorProvider) ![DumbAware][dumb-aware] | [`EditorTabColorProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java) |
| [com.intellij.editorTabTitleProvider](https://jb.gg/ipe?extensions=com.intellij.editorTabTitleProvider) | [`EditorTabTitleProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.kt) |
| [com.intellij.editorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.editorTypedHandler) ![Removal][removal] ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.endUserAgreementUpdater](https://jb.gg/ipe?extensions=com.intellij.endUserAgreementUpdater) | `n/a` |
| [com.intellij.errorHandler](https://jb.gg/ipe?extensions=com.intellij.errorHandler) | [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) |
| [com.intellij.eventLogCategory](https://jb.gg/ipe?extensions=com.intellij.eventLogCategory) | [`EventLogCategory`](%gh-ic%/platform/ide-core/src/com/intellij/notification/EventLogCategory.java) |
| [com.intellij.execution.syntheticConfigurationTypeProvider](https://jb.gg/ipe?extensions=com.intellij.execution.syntheticConfigurationTypeProvider) ![Experimental][experimental] | [`SyntheticConfigurationTypeProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/SyntheticConfigurationTypeProvider.java) |
| [com.intellij.executionActionSuppressor](https://jb.gg/ipe?extensions=com.intellij.executionActionSuppressor) ![Experimental][experimental] ![Internal][internal] | [`ExecutionActionSuppressor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ExecutionActionSuppressor.java) |
| [com.intellij.experimentalFeature](https://jb.gg/ipe?extensions=com.intellij.experimentalFeature) | `n/a` |
| [com.intellij.exportable](https://jb.gg/ipe?extensions=com.intellij.exportable) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.externalComponentSource](https://jb.gg/ipe?extensions=com.intellij.externalComponentSource) | [`ExternalComponentSource`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/externalComponents/ExternalComponentSource.java) |
| [com.intellij.featureStatisticsBundle](https://jb.gg/ipe?extensions=com.intellij.featureStatisticsBundle) | `n/a` |
| [com.intellij.feedbackDescriptionProvider](https://jb.gg/ipe?extensions=com.intellij.feedbackDescriptionProvider) ![Non-Dynamic][non-dynamic] | [`FeedbackDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/FeedbackDescriptionProvider.kt) |
| [com.intellij.fileBreadcrumbsCollector](https://jb.gg/ipe?extensions=com.intellij.fileBreadcrumbsCollector) ![Project-Level][project-level] | [`FileBreadcrumbsCollector`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/breadcrumbs/FileBreadcrumbsCollector.java) |
| [com.intellij.fileDocumentManagerListener](https://jb.gg/ipe?extensions=com.intellij.fileDocumentManagerListener) | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [com.intellij.fileDocumentSynchronizationVetoer](https://jb.gg/ipe?extensions=com.intellij.fileDocumentSynchronizationVetoer) | [`FileDocumentSynchronizationVetoer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentSynchronizationVetoer.java) |
| [com.intellij.fileDropHandler](https://jb.gg/ipe?extensions=com.intellij.fileDropHandler) | [`FileDropHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/FileDropHandler.kt) |
| [com.intellij.fileEditorProvider](https://jb.gg/ipe?extensions=com.intellij.fileEditorProvider) ![DumbAware][dumb-aware] | [`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java) |
| [com.intellij.fileEditorProviderSuppressor](https://jb.gg/ipe?extensions=com.intellij.fileEditorProviderSuppressor) ![Internal][internal] | [`FileEditorProviderSuppressor`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/FileEditorProviderSuppressor.java) |
| [com.intellij.fileEncodingProvider](https://jb.gg/ipe?extensions=com.intellij.fileEncodingProvider) | [`FileEncodingProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/FileEncodingProvider.java) |
| [com.intellij.fileType](https://jb.gg/ipe?extensions=com.intellij.fileType) | [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) |
| [com.intellij.fileTypeFactory](https://jb.gg/ipe?extensions=com.intellij.fileTypeFactory) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`FileTypeFactory`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java) |
| [com.intellij.fileTypeOverrider](https://jb.gg/ipe?extensions=com.intellij.fileTypeOverrider) | [`FileTypeOverrider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileTypes/impl/FileTypeOverrider.java) |
| [com.intellij.fileTypeRegistrar](https://jb.gg/ipe?extensions=com.intellij.fileTypeRegistrar) ![Non-Dynamic][non-dynamic] | [`FileTypeRegistrar`](%gh-ic%/platform/ide-core/src/com/intellij/ide/highlighter/FileTypeRegistrar.java) |
| [com.intellij.fileTypeUsageSchemaDescriptor](https://jb.gg/ipe?extensions=com.intellij.fileTypeUsageSchemaDescriptor) ![Internal][internal] | [`FileTypeUsageSchemaDescriptor`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/fileTypes/FileTypeUsageSchemaDescriptor.java) |
| [com.intellij.findInDirectoryScopeProvider](https://jb.gg/ipe?extensions=com.intellij.findInDirectoryScopeProvider) ![Experimental][experimental] ![Internal][internal] | [`FindInDirectoryScopeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/FindInDirectoryScopeProvider.kt) |
| [com.intellij.flsConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.flsConfigurationProvider) ![Internal][internal] | [`FLSConfigurationProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/FLSConfigurationProvider.java) |
| [com.intellij.fragments.dsl.builder.extender](https://jb.gg/ipe?extensions=com.intellij.fragments.dsl.builder.extender) ![Experimental][experimental] ![Internal][internal] | [`FragmentsDslBuilderExtender`](%gh-ic%/platform/platform-api/src/com/intellij/execution/ui/utils/FragmentsDslBuilder.kt) |
| [com.intellij.generalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.generalOptionsProvider) ![Non-Dynamic][non-dynamic] | [`SearchableConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/SearchableConfigurable.java) |
| [com.intellij.generalTroubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.generalTroubleInfoCollector) | [`GeneralTroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/GeneralTroubleInfoCollector.java) |
| [com.intellij.genericAuthProvider](https://jb.gg/ipe?extensions=com.intellij.genericAuthProvider) ![Experimental][experimental] ![Internal][internal] | [`GenericAuthProviderExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/auth/GenericAuthProvider.kt) |
| [com.intellij.getDataRule](https://jb.gg/ipe?extensions=com.intellij.getDataRule) ![Internal][internal] | [`GetDataRule`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/dataRules/GetDataRule.java) |
| [com.intellij.gitRepositoryInitializer](https://jb.gg/ipe?extensions=com.intellij.gitRepositoryInitializer) | [`GitRepositoryInitializer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitRepositoryInitializer.java) |
| [com.intellij.gitSilentFileAdder](https://jb.gg/ipe?extensions=com.intellij.gitSilentFileAdder) ![Internal][internal] ![Project-Level][project-level] | [`GitSilentFileAdderProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitSilentFileAdderProvider.java) |
| [com.intellij.groupConfigurable](https://jb.gg/ipe?extensions=com.intellij.groupConfigurable) | `n/a` |
| [com.intellij.gutterMarkPreprocessor](https://jb.gg/ipe?extensions=com.intellij.gutterMarkPreprocessor) ![Non-Dynamic][non-dynamic] | [`GutterMarkPreprocessor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/GutterMarkPreprocessor.java) |
| [com.intellij.handleTypeFactory](https://jb.gg/ipe?extensions=com.intellij.handleTypeFactory) ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`HandleTypeFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/readOnlyHandler/HandleTypeFactory.java) |
| [com.intellij.http.fileEditorActionProvider](https://jb.gg/ipe?extensions=com.intellij.http.fileEditorActionProvider) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`RemoteFileEditorActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/RemoteFileEditorActionProvider.java) |
| [com.intellij.http.localFileFinder](https://jb.gg/ipe?extensions=com.intellij.http.localFileFinder) | [`LocalFileFinder`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/LocalFileFinder.java) |
| [com.intellij.iconDescriptionBundle](https://jb.gg/ipe?extensions=com.intellij.iconDescriptionBundle) | `n/a` |
| [com.intellij.iconMapper](https://jb.gg/ipe?extensions=com.intellij.iconMapper) | `n/a` |
| [com.intellij.iconMapperSuppressor](https://jb.gg/ipe?extensions=com.intellij.iconMapperSuppressor) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.ide.dynamicPluginVetoer](https://jb.gg/ipe?extensions=com.intellij.ide.dynamicPluginVetoer) | [`DynamicPluginVetoer`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginVetoer.kt) |
| [com.intellij.ideEventQueueDispatcher](https://jb.gg/ipe?extensions=com.intellij.ideEventQueueDispatcher) | [`EventDispatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/IdeEventQueue.kt) |
| [com.intellij.idePerformanceListener](https://jb.gg/ipe?extensions=com.intellij.idePerformanceListener) ![Experimental][experimental] ![Internal][internal] | [`PerformanceListener`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/PerformanceListener.kt) |
| [com.intellij.ideRootPaneNorth](https://jb.gg/ipe?extensions=com.intellij.ideRootPaneNorth) ![Non-Dynamic][non-dynamic] | [`IdeRootPaneNorthExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/IdeRootPaneNorthExtension.kt) |
| [com.intellij.ideStartupWizard](https://jb.gg/ipe?extensions=com.intellij.ideStartupWizard) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`IdeStartupWizard`](%gh-ic%/platform/platform-impl/bootstrap/src/com/intellij/platform/ide/bootstrap/IdeStartupWizard.kt) |
| [com.intellij.inspectionPopupLevelChangePolicy](https://jb.gg/ipe?extensions=com.intellij.inspectionPopupLevelChangePolicy) ![Internal][internal] | [`InspectionPopupLevelChangePolicy`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/InspectionPopupLevelChangePolicy.java) |
| [com.intellij.interactiveCourseFactory](https://jb.gg/ipe?extensions=com.intellij.interactiveCourseFactory) | [`InteractiveCourseFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/InteractiveCourseFactory.kt) |
| [com.intellij.internal.ml.featureProvider](https://jb.gg/ipe?extensions=com.intellij.internal.ml.featureProvider) ![Internal][internal] | [`MLFeatureProvider`](%gh-ic%/platform/ml-api/src/com/intellij/internal/ml/MLFeatureProvider.kt) |
| [com.intellij.itemPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.itemPresentationProvider) | [`ItemPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/ItemPresentationProvider.java) |
| [com.intellij.iw.actionProvider](https://jb.gg/ipe?extensions=com.intellij.iw.actionProvider) | [`InspectionWidgetActionProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/markup/InspectionWidgetActionProvider.kt) |
| [com.intellij.jbProtocolCommand](https://jb.gg/ipe?extensions=com.intellij.jbProtocolCommand) | [`JBProtocolCommand`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/application/JBProtocolCommand.kt) |
| [com.intellij.jbProtocolRevisionResolver](https://jb.gg/ipe?extensions=com.intellij.jbProtocolRevisionResolver) | [`JBProtocolRevisionResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/navigation/JBProtocolRevisionResolver.java) |
| [com.intellij.jcef.appRequiredArgumentsProvider](https://jb.gg/ipe?extensions=com.intellij.jcef.appRequiredArgumentsProvider) ![Non-Dynamic][non-dynamic] | [`JBCefAppRequiredArgumentsProvider`](%gh-ic%/platform/ui.jcef/jcef/JBCefAppRequiredArgumentsProvider.kt) |
| [com.intellij.jdkDownloader.jdkInstallerListener](https://jb.gg/ipe?extensions=com.intellij.jdkDownloader.jdkInstallerListener) ![Internal][internal] | [`JdkInstallerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkInstaller.kt) |
| [com.intellij.jdkUpdateCheckContributor](https://jb.gg/ipe?extensions=com.intellij.jdkUpdateCheckContributor) | [`JdkUpdateCheckContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkUpdater.kt) |
| [com.intellij.jps.plugin](https://jb.gg/ipe?extensions=com.intellij.jps.plugin) | `n/a` |
| [com.intellij.keymapExtension](https://jb.gg/ipe?extensions=com.intellij.keymapExtension) | [`KeymapExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapExtension.java) |
| [com.intellij.lang.syntaxHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.lang.syntaxHighlighterFactory) | [`SyntaxHighlighterFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java) |
| [com.intellij.library.toolWindow](https://jb.gg/ipe?extensions=com.intellij.library.toolWindow) ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| [com.intellij.lightEditTabAttributesProvider](https://jb.gg/ipe?extensions=com.intellij.lightEditTabAttributesProvider) ![Experimental][experimental] | [`LightEditTabAttributesProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditTabAttributesProvider.java) |
| [com.intellij.logLevelConfigurationListener](https://jb.gg/ipe?extensions=com.intellij.logLevelConfigurationListener) ![Internal][internal] | [`Listener`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/logs/LogLevelConfigurationManager.kt) |
| [com.intellij.logsPreprocessor](https://jb.gg/ipe?extensions=com.intellij.logsPreprocessor) ![Internal][internal] | [`LogProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/logsUploader/LogProvider.kt) |
| [com.intellij.lowLevelProjectOpenProcessor](https://jb.gg/ipe?extensions=com.intellij.lowLevelProjectOpenProcessor) ![Internal][internal] | [`LowLevelProjectOpenProcessor`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/project/ex/LowLevelProjectOpenProcessor.kt) |
| [com.intellij.mac.dockMenuActions](https://jb.gg/ipe?extensions=com.intellij.mac.dockMenuActions) ![Non-Dynamic][non-dynamic] ![Experimental][experimental] ![Internal][internal] | [`MacDockMenuActions`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/mac/MacDockMenuActions.kt) |
| [com.intellij.meetNewUiCustomization](https://jb.gg/ipe?extensions=com.intellij.meetNewUiCustomization) ![Internal][internal] | [`MeetNewUiCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/experimental/meetNewUi/MeetNewUiCustomization.kt) |
| [com.intellij.navbarLeftSide](https://jb.gg/ipe?extensions=com.intellij.navbarLeftSide) ![Internal][internal] | [`NavBarLeftSideExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarLeftSideExtension.java) |
| [com.intellij.newProject.onboarding.tips](https://jb.gg/ipe?extensions=com.intellij.newProject.onboarding.tips) ![Internal][internal] | [`NewProjectOnboardingTips`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectOnboardingTips.kt) |
| [com.intellij.newProjectWizard.generator](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.generator) | [`GeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/GeneratorNewProjectWizard.kt) |
| [com.intellij.newProjectWizard.language](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.language) ![Deprecated][deprecated] | [`LanguageNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/LanguageNewProjectWizard.kt) |
| [com.intellij.newProjectWizard.languageGenerator](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.languageGenerator) | [`LanguageGeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/language/LanguageGeneratorNewProjectWizard.kt) |
| [com.intellij.nonProjectFileWritingAccessExtension](https://jb.gg/ipe?extensions=com.intellij.nonProjectFileWritingAccessExtension) ![Project-Level][project-level] | [`NonProjectFileWritingAccessExtension`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/NonProjectFileWritingAccessExtension.java) |
| [com.intellij.notification.group](https://jb.gg/ipe?extensions=com.intellij.notification.group) ![Non-Dynamic][non-dynamic] ![Internal][internal] | `n/a` |
| [com.intellij.notification.parentGroup](https://jb.gg/ipe?extensions=com.intellij.notification.parentGroup) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.notificationRemindLaterHandler](https://jb.gg/ipe?extensions=com.intellij.notificationRemindLaterHandler) | [`NotificationRemindLaterHandler`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationRemindLaterHandler.kt) |
| [com.intellij.notificationRouter](https://jb.gg/ipe?extensions=com.intellij.notificationRouter) ![Internal][internal] | [`NotificationRouter`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationRouter.kt) |
| [com.intellij.obsoleteStorage](https://jb.gg/ipe?extensions=com.intellij.obsoleteStorage) ![Internal][internal] | `n/a` |
| [com.intellij.pathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.pathMacroContributor) | [`PathMacroContributor`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| [com.intellij.pathMacroExpandableProtocol](https://jb.gg/ipe?extensions=com.intellij.pathMacroExpandableProtocol) | `n/a` |
| [com.intellij.pathMacroFilter](https://jb.gg/ipe?extensions=com.intellij.pathMacroFilter) | [`PathMacroFilter`](%gh-ic%/jps/model-serialization/src/com/intellij/openapi/application/PathMacroFilter.java) |
| [com.intellij.persistentFsConnectionListener](https://jb.gg/ipe?extensions=com.intellij.persistentFsConnectionListener) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`PersistentFsConnectionListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/vfs/newvfs/persistent/PersistentFsConnectionListener.java) |
| [com.intellij.platform.ml.descriptor](https://jb.gg/ipe?extensions=com.intellij.platform.ml.descriptor) ![Internal][internal] | [`TierDescriptor`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/TierDescriptor.kt) |
| [com.intellij.platform.ml.environmentExtender](https://jb.gg/ipe?extensions=com.intellij.platform.ml.environmentExtender) ![Internal][internal] | [`EnvironmentExtender`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/environment/EnvironmentExtender.kt) |
| [com.intellij.platform.ml.taskListener](https://jb.gg/ipe?extensions=com.intellij.platform.ml.taskListener) ![Internal][internal] | [`MLTaskGroupListener`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/monitoring/MLApproachListener.kt) |
| [com.intellij.pluginInstallationCustomization](https://jb.gg/ipe?extensions=com.intellij.pluginInstallationCustomization) ![Internal][internal] | [`PluginInstallationCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/newui/PluginInstallationCustomization.kt) |
| [com.intellij.pluginReplacement](https://jb.gg/ipe?extensions=com.intellij.pluginReplacement) | [`PluginReplacement`](%gh-ic%/platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) |
| [com.intellij.pluginRepositoryAuthProvider](https://jb.gg/ipe?extensions=com.intellij.pluginRepositoryAuthProvider) | [`PluginRepositoryAuthProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthProvider.java) |
| [com.intellij.pluginSuggestionProvider](https://jb.gg/ipe?extensions=com.intellij.pluginSuggestionProvider) ![Internal][internal] | [`PluginSuggestionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/pluginsAdvertisement/PluginSuggestionProvider.kt) |
| [com.intellij.pluginsViewCustomizer](https://jb.gg/ipe?extensions=com.intellij.pluginsViewCustomizer) ![Experimental][experimental] ![Internal][internal] | [`PluginsViewCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/newui/PluginsViewCustomizer.kt) |
| [com.intellij.productivityFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.productivityFeaturesProvider) | [`ProductivityFeaturesProvider`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/ProductivityFeaturesProvider.java) |
| [com.intellij.projectAttachProcessor](https://jb.gg/ipe?extensions=com.intellij.projectAttachProcessor) | [`ProjectAttachProcessor`](%gh-ic%/platform/ide-core/src/com/intellij/projectImport/ProjectAttachProcessor.kt) |
| [com.intellij.projectCloseHandler](https://jb.gg/ipe?extensions=com.intellij.projectCloseHandler) ![Non-Dynamic][non-dynamic] | [`ProjectCloseHandler`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseHandler.java) |
| [com.intellij.projectConfigurable](https://jb.gg/ipe?extensions=com.intellij.projectConfigurable) ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| [com.intellij.projectCustomDataSynchronizer](https://jb.gg/ipe?extensions=com.intellij.projectCustomDataSynchronizer) ![Experimental][experimental] | [`ProjectCustomDataSynchronizer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/ProjectCustomDataSynchronizer.kt) |
| [com.intellij.projectNameProvider](https://jb.gg/ipe?extensions=com.intellij.projectNameProvider) ![Non-Dynamic][non-dynamic] | [`ProjectNameProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/project/ex/ProjectNameProvider.java) |
| [com.intellij.projectOpenProcessor](https://jb.gg/ipe?extensions=com.intellij.projectOpenProcessor) | [`ProjectOpenProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.kt) |
| [com.intellij.projectOriginInfoProvider](https://jb.gg/ipe?extensions=com.intellij.projectOriginInfoProvider) ![Internal][internal] | [`ProjectOriginInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/ProjectOriginInfoProvider.kt) |
| [com.intellij.projectServiceContainerCustomizer](https://jb.gg/ipe?extensions=com.intellij.projectServiceContainerCustomizer) ![Internal][internal] | [`ProjectServiceContainerCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| [com.intellij.projectServiceInitializer](https://jb.gg/ipe?extensions=com.intellij.projectServiceInitializer) ![Internal][internal] | [`ProjectServiceInitializer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| [com.intellij.projectServiceInitializer.essential](https://jb.gg/ipe?extensions=com.intellij.projectServiceInitializer.essential) ![Internal][internal] | [`ProjectServiceInitializer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| [com.intellij.projectSetProcessor](https://jb.gg/ipe?extensions=com.intellij.projectSetProcessor) ![Non-Dynamic][non-dynamic] | [`ProjectSetProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectSetProcessor.java) |
| [com.intellij.projectSettings](https://jb.gg/ipe?extensions=com.intellij.projectSettings) ![Experimental][experimental] ![Project-Level][project-level] | [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) |
| [com.intellij.projectStoreClassProvider](https://jb.gg/ipe?extensions=com.intellij.projectStoreClassProvider) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ProjectStoreFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectStoreFactory.java) |
| [com.intellij.projectTaskManagerListener](https://jb.gg/ipe?extensions=com.intellij.projectTaskManagerListener) ![Internal][internal] | [`ProjectTaskManagerListenerExtensionPoint`](%gh-ic%/platform/lang-impl/src/com/intellij/task/impl/ProjectTaskManagerListenerExtensionPoint.java) |
| [com.intellij.projectTemplate](https://jb.gg/ipe?extensions=com.intellij.projectTemplate) | `n/a` |
| [com.intellij.projectTemplatesFactory](https://jb.gg/ipe?extensions=com.intellij.projectTemplatesFactory) | [`ProjectTemplatesFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/ProjectTemplatesFactory.java) |
| [com.intellij.projectTypesProvider](https://jb.gg/ipe?extensions=com.intellij.projectTypesProvider) ![Experimental][experimental] | [`ProjectTypesProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectTypesProvider.java) |
| [com.intellij.projectUndoProvider](https://jb.gg/ipe?extensions=com.intellij.projectUndoProvider) ![Project-Level][project-level] | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.protanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.protanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.protocolHandler](https://jb.gg/ipe?extensions=com.intellij.protocolHandler) | [`ProtocolHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ProtocolHandler.kt) |
| [com.intellij.protocolNavigationCommandProcessor](https://jb.gg/ipe?extensions=com.intellij.protocolNavigationCommandProcessor) ![Internal][internal] | [`ProtocolNavigationCommandProcessor`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/protocolHandler/ProtocolNavigationCommandProcessor.kt) |
| [com.intellij.proxySettingsOverrideProvider](https://jb.gg/ipe?extensions=com.intellij.proxySettingsOverrideProvider) | [`ProxySettingsOverrideProvider`](%gh-ic%/platform/platform-api/src/com/intellij/util/net/ProxySettingsOverrideProvider.kt) |
| [com.intellij.rawEditorTypedHandler](https://jb.gg/ipe?extensions=com.intellij.rawEditorTypedHandler) ![Removal][removal] ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| [com.intellij.recentProjectsBranchesProvider](https://jb.gg/ipe?extensions=com.intellij.recentProjectsBranchesProvider) | [`RecentProjectsBranchesProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/vcs/RecentProjectsBranchesProvider.kt) |
| [com.intellij.recentProjectsProvider](https://jb.gg/ipe?extensions=com.intellij.recentProjectsProvider) ![Internal][internal] | [`RecentProjectProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/RecentProjectProvider.kt) |
| [com.intellij.recoveryAction](https://jb.gg/ipe?extensions=com.intellij.recoveryAction) ![Internal][internal] | [`RecoveryAction`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/cache/Saul.kt) |
| [com.intellij.remote.credentialsLanguageContribution](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsLanguageContribution) | [`CredentialsLanguageContribution`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/ext/CredentialsLanguageContribution.java) |
| [com.intellij.remote.credentialsType](https://jb.gg/ipe?extensions=com.intellij.remote.credentialsType) | [`CredentialsType`](%gh-ic%/platform/remote-core/src/remote/CredentialsType.java) |
| [com.intellij.remote.pathMappingProvider](https://jb.gg/ipe?extensions=com.intellij.remote.pathMappingProvider) | [`PathMappingProvider`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/PathMappingProvider.java) |
| [com.intellij.schemeExporter](https://jb.gg/ipe?extensions=com.intellij.schemeExporter) | [`SchemeExporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeExporter.java) |
| [com.intellij.schemeImporter](https://jb.gg/ipe?extensions=com.intellij.schemeImporter) | [`SchemeImporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeImporter.java) |
| [com.intellij.search.additionalOptionsLocation](https://jb.gg/ipe?extensions=com.intellij.search.additionalOptionsLocation) | [`AdditionalLocationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionsRegistrar.java) |
| [com.intellij.search.optionContributor](https://jb.gg/ipe?extensions=com.intellij.search.optionContributor) | [`SearchableOptionContributor`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionContributor.kt) |
| [com.intellij.search.projectOptionsTopHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.projectOptionsTopHitProvider) | [`ProjectLevelProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/OptionsSearchTopHitProvider.java) |
| [com.intellij.search.topHitProvider](https://jb.gg/ipe?extensions=com.intellij.search.topHitProvider) | [`SearchTopHitProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SearchTopHitProvider.kt) |
| [com.intellij.search.traverseUiHelper](https://jb.gg/ipe?extensions=com.intellij.search.traverseUiHelper) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`TraverseUIHelper`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/TraverseUIHelper.java) |
| [com.intellij.selectInTarget](https://jb.gg/ipe?extensions=com.intellij.selectInTarget) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`SelectInTarget`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SelectInTarget.java) |
| [com.intellij.semanticRootProvider](https://jb.gg/ipe?extensions=com.intellij.semanticRootProvider) ![Non-Dynamic][non-dynamic] | [`RootSemanticAddressProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/newvfs/persistent/RootSemanticAddressProvider.java) |
| [com.intellij.settingsEntryPointActionProvider](https://jb.gg/ipe?extensions=com.intellij.settingsEntryPointActionProvider) | [`ActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| [com.intellij.settingsEntryPointIconCustomizer](https://jb.gg/ipe?extensions=com.intellij.settingsEntryPointIconCustomizer) | [`IconCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| [com.intellij.smartSelectProvider](https://jb.gg/ipe?extensions=com.intellij.smartSelectProvider) | [`SmartSelectProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/SmartSelectProvider.java) |
| [com.intellij.sshCredentialProvider](https://jb.gg/ipe?extensions=com.intellij.sshCredentialProvider) | [`SshCredentialProvider`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/SshCredentialProvider.java) |
| [com.intellij.startPagePromoter](https://jb.gg/ipe?extensions=com.intellij.startPagePromoter) ![Internal][internal] | [`StartPagePromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StartPagePromoter.kt) |
| [com.intellij.statistic.eventLog.eventLoggerProvider](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.eventLoggerProvider) | [`StatisticsEventLoggerProvider`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/StatisticsEventLogger.kt) |
| [com.intellij.statistic.eventLog.externalEventLogSettings](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.externalEventLogSettings) ![Internal][internal] | [`ExternalEventLogSettings`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogSettings.java) |
| [com.intellij.statistic.eventLog.externalListenerProvider](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.externalListenerProvider) ![Internal][internal] | [`ExternalEventLogListenerProviderExtension`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogListenerProviderExtension.java) |
| [com.intellij.statistic.eventLog.fusStateEventTracker](https://jb.gg/ipe?extensions=com.intellij.statistic.eventLog.fusStateEventTracker) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`FeatureUsageStateEventTracker`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/fus/FeatureUsageStateEventTracker.kt) |
| [com.intellij.statistics.actionCustomPlaceAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.actionCustomPlaceAllowlist) | `n/a` |
| [com.intellij.statistics.actionIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.actionIdsHolder) ![Internal][internal] | [`ActionIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/actions/persistence/ActionIdsHolder.kt) |
| [com.intellij.statistics.applicationUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.applicationUsagesCollector) ![Internal][internal] | [`ApplicationUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ApplicationUsagesCollector.kt) |
| [com.intellij.statistics.balloonIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.balloonIdsHolder) ![Internal][internal] | [`BalloonIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/ui/BalloonIdsHolder.java) |
| [com.intellij.statistics.collectorExtension](https://jb.gg/ipe?extensions=com.intellij.statistics.collectorExtension) | [`FeatureUsageCollectorExtension`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsageCollectorExtension.java) |
| [com.intellij.statistics.counterUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.counterUsagesCollector) ![Internal][internal] | [`FeatureUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsagesCollector.java) |
| [com.intellij.statistics.gotItTooltipAllowlist](https://jb.gg/ipe?extensions=com.intellij.statistics.gotItTooltipAllowlist) | `n/a` |
| [com.intellij.statistics.notificationIdsHolder](https://jb.gg/ipe?extensions=com.intellij.statistics.notificationIdsHolder) ![Internal][internal] | [`NotificationIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/impl/NotificationIdsHolder.java) |
| [com.intellij.statistics.projectUsagesCollector](https://jb.gg/ipe?extensions=com.intellij.statistics.projectUsagesCollector) ![Internal][internal] | [`ProjectUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ProjectUsagesCollector.kt) |
| [com.intellij.statistics.validation.customValidationRule](https://jb.gg/ipe?extensions=com.intellij.statistics.validation.customValidationRule) | [`CustomValidationRule`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRule.java) |
| [com.intellij.statistics.validation.customValidationRuleFactory](https://jb.gg/ipe?extensions=com.intellij.statistics.validation.customValidationRuleFactory) | [`CustomValidationRuleFactory`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRuleFactory.java) |
| [com.intellij.statusBarWidgetFactory](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetFactory) | [`StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java) |
| [com.intellij.statusBarWidgetProvider](https://jb.gg/ipe?extensions=com.intellij.statusBarWidgetProvider) ![Deprecated][deprecated] ![Removal][removal] | [`StatusBarWidgetProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetProvider.java) |
| [com.intellij.streamProviderFactory](https://jb.gg/ipe?extensions=com.intellij.streamProviderFactory) ![Internal][internal] ![Project-Level][project-level] | [`StreamProviderFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/configurationStore/StreamProviderFactory.kt) |
| [com.intellij.stripTrailingSpacesFilterFactory](https://jb.gg/ipe?extensions=com.intellij.stripTrailingSpacesFilterFactory) | [`StripTrailingSpacesFilterFactory`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/StripTrailingSpacesFilterFactory.java) |
| [com.intellij.subprojectInfoProvider](https://jb.gg/ipe?extensions=com.intellij.subprojectInfoProvider) ![Internal][internal] | [`SubprojectInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/workspace/SubprojectInfoProvider.kt) |
| [com.intellij.systemProperty](https://jb.gg/ipe?extensions=com.intellij.systemProperty) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.testStatusListener](https://jb.gg/ipe?extensions=com.intellij.testStatusListener) ![Non-Dynamic][non-dynamic] | [`TestStatusListener`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/TestStatusListener.java) |
| [com.intellij.textEditorCustomizer](https://jb.gg/ipe?extensions=com.intellij.textEditorCustomizer) ![Internal][internal] | [`TextEditorCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/text/TextEditorCustomizer.kt) |
| [com.intellij.textEditorNecromancerAwaker](https://jb.gg/ipe?extensions=com.intellij.textEditorNecromancerAwaker) ![Non-Dynamic][non-dynamic] | [`NecromancerAwaker`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/zombie/Necromancer.kt) |
| [com.intellij.themeMetadataProvider](https://jb.gg/ipe?extensions=com.intellij.themeMetadataProvider) | `n/a` |
| [com.intellij.themeProvider](https://jb.gg/ipe?extensions=com.intellij.themeProvider) | `n/a` |
| [com.intellij.themeRemapper](https://jb.gg/ipe?extensions=com.intellij.themeRemapper) ![Internal][internal] | [`UiThemeRemapper`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/laf/UiThemeMapper.kt) |
| [com.intellij.tipAndTrick](https://jb.gg/ipe?extensions=com.intellij.tipAndTrick) | `n/a` |
| [com.intellij.titleInfoProvider](https://jb.gg/ipe?extensions=com.intellij.titleInfoProvider) ![Non-Dynamic][non-dynamic] | [`TitleInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [com.intellij.toolWindow](https://jb.gg/ipe?extensions=com.intellij.toolWindow) ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| [com.intellij.toolWindowAllowlist](https://jb.gg/ipe?extensions=com.intellij.toolWindowAllowlist) | `n/a` |
| [com.intellij.toolWindowContentExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowContentExtractor) ![Internal][internal] | [`ToolWindowContentExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowContentExtractor.java) |
| [com.intellij.toolWindowExtractor](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractor) ![Internal][internal] | [`ToolWindowViewModelExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelExtractor.java) |
| [com.intellij.toolWindowExtractorMode](https://jb.gg/ipe?extensions=com.intellij.toolWindowExtractorMode) ![Experimental][experimental] | `n/a` |
| [com.intellij.toolWindowTabInEditorHelper](https://jb.gg/ipe?extensions=com.intellij.toolWindowTabInEditorHelper) ![Experimental][experimental] ![Internal][internal] | [`ToolWindowTabInEditorHelper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/tabInEditor/ToolWindowTabInEditorHelper.java) |
| [com.intellij.toolbarQuickAction](https://jb.gg/ipe?extensions=com.intellij.toolbarQuickAction) ![Non-Dynamic][non-dynamic] | [`ToolbarAddQuickActionInfo`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/ToolbarAddQuickActionInfo.kt) |
| [com.intellij.trailingSpacesOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.trailingSpacesOptionsProvider) | [`TrailingSpacesOptionsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/TrailingSpacesOptionsProvider.java) |
| [com.intellij.tree.CustomLanguageASTComparator](https://jb.gg/ipe?extensions=com.intellij.tree.CustomLanguageASTComparator) | [`CustomLanguageASTComparator`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/CustomLanguageASTComparator.java) |
| [com.intellij.tritanopiaSupport](https://jb.gg/ipe?extensions=com.intellij.tritanopiaSupport) ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| [com.intellij.troubleInfoCollector](https://jb.gg/ipe?extensions=com.intellij.troubleInfoCollector) | [`TroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |
| [com.intellij.trustedHostsConfigurableProvider](https://jb.gg/ipe?extensions=com.intellij.trustedHostsConfigurableProvider) ![Internal][internal] | [`TrustedHostsConfigurableProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedHostsConfigurable.kt) |
| [com.intellij.trustedProjectsLocator](https://jb.gg/ipe?extensions=com.intellij.trustedProjectsLocator) | [`TrustedProjectsLocator`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/trustedProjects/TrustedProjectsLocator.kt) |
| [com.intellij.ui.content.impl.toolWindowContentPostprocessor](https://jb.gg/ipe?extensions=com.intellij.ui.content.impl.toolWindowContentPostprocessor) ![Deprecated][deprecated] ![Internal][internal] | [`ToolWindowContentPostProcessor`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/content/impl/ToolWindowContentPostProcessor.kt) |
| [com.intellij.ui.optionEditorProvider](https://jb.gg/ipe?extensions=com.intellij.ui.optionEditorProvider) ![Experimental][experimental] | [`OptionEditorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/OptionEditorProvider.java) |
| [com.intellij.ui.suitableFontProvider](https://jb.gg/ipe?extensions=com.intellij.ui.suitableFontProvider) ![Internal][internal] | [`SuitableFontProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SuitableFontProvider.java) |
| [com.intellij.uiChangeListener](https://jb.gg/ipe?extensions=com.intellij.uiChangeListener) ![Internal][internal] | [`Listener`](%gh-ic%/platform/core-ui/src/ui/ExperimentalUI.kt) |
| [com.intellij.uiDataRule](https://jb.gg/ipe?extensions=com.intellij.uiDataRule) | [`UiDataRule`](%gh-ic%/platform/core-ui/src/openapi/actionSystem/UiDataProvider.kt) |
| [com.intellij.undoProvider](https://jb.gg/ipe?extensions=com.intellij.undoProvider) | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| [com.intellij.undoReportHandler](https://jb.gg/ipe?extensions=com.intellij.undoReportHandler) ![Internal][internal] | [`UndoReportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoReportHandler.java) |
| [com.intellij.unknownSdkContributor](https://jb.gg/ipe?extensions=com.intellij.unknownSdkContributor) | [`UnknownSdkContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/UnknownSdkCollector.kt) |
| [com.intellij.unknownSdkResolver](https://jb.gg/ipe?extensions=com.intellij.unknownSdkResolver) | [`UnknownSdkResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/UnknownSdkResolver.java) |
| [com.intellij.updateSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.updateSettingsProvider) | [`UpdateSettingsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsProvider.java) |
| [com.intellij.updateSettingsUIProvider](https://jb.gg/ipe?extensions=com.intellij.updateSettingsUIProvider) | [`UpdateSettingsUIProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsUIProvider.kt) |
| [com.intellij.usageFilteringRuleCustomizer](https://jb.gg/ipe?extensions=com.intellij.usageFilteringRuleCustomizer) ![Internal][internal] | [`UsageFilteringRuleCustomizer`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleCustomizer.kt) |
| [com.intellij.utf8BomOptionProvider](https://jb.gg/ipe?extensions=com.intellij.utf8BomOptionProvider) | [`Utf8BomOptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/Utf8BomOptionProvider.java) |
| [com.intellij.vfs.local.fileOperationsHandler](https://jb.gg/ipe?extensions=com.intellij.vfs.local.fileOperationsHandler) | [`LocalFileOperationsHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) |
| [com.intellij.vfs.local.pluggableFileWatcher](https://jb.gg/ipe?extensions=com.intellij.vfs.local.pluggableFileWatcher) ![Non-Dynamic][non-dynamic] | [`PluggableFileWatcher`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/vfs/local/PluggableFileWatcher.java) |
| [com.intellij.virtualFileCustomDataConsumer](https://jb.gg/ipe?extensions=com.intellij.virtualFileCustomDataConsumer) ![Experimental][experimental] | [`VirtualFileCustomDataConsumer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileCustomDataConsumer.kt) |
| [com.intellij.virtualFileCustomDataProvider](https://jb.gg/ipe?extensions=com.intellij.virtualFileCustomDataProvider) ![Experimental][experimental] | [`VirtualFileCustomDataProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileCustomDataProvider.kt) |
| [com.intellij.warmupConfigurator](https://jb.gg/ipe?extensions=com.intellij.warmupConfigurator) ![Deprecated][deprecated] ![Removal][removal] | [`WarmupConfigurator`](%gh-ic%/platform/platform-api/src/com/intellij/ide/warmup/WarmupConfigurator.kt) |
| [com.intellij.webBrowserUrlProvider](https://jb.gg/ipe?extensions=com.intellij.webBrowserUrlProvider) ![DumbAware][dumb-aware] | [`WebBrowserUrlProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/WebBrowserUrlProvider.kt) |
| [com.intellij.webHelpProvider](https://jb.gg/ipe?extensions=com.intellij.webHelpProvider) | [`WebHelpProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) |
| [com.intellij.welcome.projectDetector](https://jb.gg/ipe?extensions=com.intellij.welcome.projectDetector) | [`ProjectDetector`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/ProjectDetector.java) |
| [com.intellij.welcomeFrameProvider](https://jb.gg/ipe?extensions=com.intellij.welcomeFrameProvider) | [`WelcomeFrameProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeFrameProvider.java) |
| [com.intellij.welcomeScreen](https://jb.gg/ipe?extensions=com.intellij.welcomeScreen) ![Non-Dynamic][non-dynamic] | [`WelcomeScreenProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenProvider.java) |
| [com.intellij.welcomeScreenCustomization](https://jb.gg/ipe?extensions=com.intellij.welcomeScreenCustomization) ![Experimental][experimental] | [`WelcomeScreenCustomization`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenCustomization.java) |
| [com.intellij.welcomeTabFactory](https://jb.gg/ipe?extensions=com.intellij.welcomeTabFactory) | [`WelcomeTabFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeTabFactory.java) |
| [org.jetbrains.javaScriptDebuggerStarter](https://jb.gg/ipe?extensions=org.jetbrains.javaScriptDebuggerStarter) | [`JavaScriptDebuggerStarter`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/browsers/JavaScriptDebuggerStarter.java) |
| [org.jetbrains.urlOpener](https://jb.gg/ipe?extensions=org.jetbrains.urlOpener) | [`UrlOpener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/UrlOpener.java) |

### PlatformLangComponents.xml

[`PlatformLangComponents.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformLangComponents.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.diagnostic.freezeNotifier](https://jb.gg/ipe?extensions=com.intellij.diagnostic.freezeNotifier) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`FreezeNotifier`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/FreezeNotifier.kt) |
| [com.intellij.registerToolWindowTaskProvider](https://jb.gg/ipe?extensions=com.intellij.registerToolWindowTaskProvider) ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`RegisterToolWindowTaskProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/toolWindow/toolwindow.kt) |

### PlatformWarmup.xml

[`PlatformWarmup.xml`](%gh-ic%/platform/warmup/resources/META-INF/PlatformWarmup.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.projectBuildWarmupSupport](https://jb.gg/ipe?extensions=com.intellij.projectBuildWarmupSupport) ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`ProjectBuildWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectBuildWarmupSupport.kt) |
| [com.intellij.projectIndexesWarmupSupport](https://jb.gg/ipe?extensions=com.intellij.projectIndexesWarmupSupport) ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`ProjectIndexesWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectIndexesWarmupSupport.kt) |

### ProjectModel.xml

[`ProjectModel.xml`](%gh-ic%/platform/projectModel-api/resources/META-INF/ProjectModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customLibraryTable](https://jb.gg/ipe?extensions=com.intellij.customLibraryTable) ![Internal][internal] | [`CustomLibraryTableDescription`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/CustomLibraryTableDescription.java) |
| [com.intellij.filePropertyPusher](https://jb.gg/ipe?extensions=com.intellij.filePropertyPusher) ![Experimental][experimental] | [`FilePropertyPusher`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/impl/FilePropertyPusher.java) |
| [com.intellij.moduleExtension](https://jb.gg/ipe?extensions=com.intellij.moduleExtension) | [`ModuleExtension`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleExtension.java) |
| [com.intellij.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.orderEnumerationHandlerFactory) | [`Factory`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderEnumerationHandler.java) |
| [com.intellij.orderRootType](https://jb.gg/ipe?extensions=com.intellij.orderRootType) ![Non-Dynamic][non-dynamic] | [`OrderRootType`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderRootType.java) |
| [com.intellij.primaryModuleManager](https://jb.gg/ipe?extensions=com.intellij.primaryModuleManager) | [`PrimaryModuleManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/module/PrimaryModuleManager.java) |
| [com.intellij.projectFileScanner](https://jb.gg/ipe?extensions=com.intellij.projectFileScanner) ![Internal][internal] | [`IndexableFileScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFileScanner.java) |

### ProjectModelImpl.xml

[`ProjectModelImpl.xml`](%gh-ic%/platform/projectModel-impl/resources/META-INF/ProjectModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.additionalLibraryRootsProvider](https://jb.gg/ipe?extensions=com.intellij.additionalLibraryRootsProvider) | [`AdditionalLibraryRootsProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java) |
| [com.intellij.pathMacroSubstitutorProvider](https://jb.gg/ipe?extensions=com.intellij.pathMacroSubstitutorProvider) ![Internal][internal] | [`PathMacroSubstitutorProvider`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/components/impl/PathMacroSubstitutorProvider.kt) |
| [com.intellij.projectExtension](https://jb.gg/ipe?extensions=com.intellij.projectExtension) ![Internal][internal] ![Project-Level][project-level] | [`ProjectExtension`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/ProjectExtension.java) |
| [com.intellij.projectPathMacroContributor](https://jb.gg/ipe?extensions=com.intellij.projectPathMacroContributor) ![Internal][internal] | [`ProjectWidePathMacroContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/components/impl/ProjectWidePathMacroContributor.java) |
| [com.intellij.workspaceModel.facetContributor](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.facetContributor) ![Internal][internal] | [`WorkspaceFacetContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/workspaceModel/ide/legacyBridge/WorkspaceFacetContributor.kt) |
| [com.intellij.workspaceModel.moduleExtensionBridgeFactory](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.moduleExtensionBridgeFactory) ![Internal][internal] | [`ModuleExtensionBridgeFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/ide/legacyBridge/ModuleExtensionBridgeFactory.kt) |

### RefactoringExtensionPoints.xml

[`RefactoringExtensionPoints.xml`](%gh-ic%/platform/refactoring/resources/META-INF/RefactoringExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.automaticRenamerFactory](https://jb.gg/ipe?extensions=com.intellij.automaticRenamerFactory) | [`AutomaticRenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/naming/AutomaticRenamerFactory.java) |
| [com.intellij.findInProjectSearchEngine](https://jb.gg/ipe?extensions=com.intellij.findInProjectSearchEngine) ![Experimental][experimental] | [`FindInProjectSearchEngine`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindInProjectSearchEngine.java) |
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
| [com.intellij.renamerFactory](https://jb.gg/ipe?extensions=com.intellij.renamerFactory) ![Experimental][experimental] | [`RenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenamerFactory.java) |
| [com.intellij.testSourcesFilter](https://jb.gg/ipe?extensions=com.intellij.testSourcesFilter) | [`TestSourcesFilter`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java) |
| [com.intellij.updateAddedFileProcessor](https://jb.gg/ipe?extensions=com.intellij.updateAddedFileProcessor) | [`UpdateAddedFileProcessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/file/UpdateAddedFileProcessor.java) |
| [com.intellij.vetoRenameCondition](https://jb.gg/ipe?extensions=com.intellij.vetoRenameCondition) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.virtualFileQualifiedNameProvider](https://jb.gg/ipe?extensions=com.intellij.virtualFileQualifiedNameProvider) | [`VirtualFileQualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/VirtualFileQualifiedNameProvider.java) |

### RefactoringLangExtensionPoints.xml

[`RefactoringLangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/RefactoringLangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.changeSignature.usageProvider](https://jb.gg/ipe?extensions=com.intellij.changeSignature.usageProvider) ![Experimental][experimental] | [`ChangeSignatureUsageProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/changeSignature/ChangeSignatureUsageProvider.java) |
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

### RemoteDevUtil.xml

[`RemoteDevUtil.xml`](%gh-ic%/platform/remoteDev-util/resources/META-INF/RemoteDevUtil.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteDev.configureClientHook](https://jb.gg/ipe?extensions=com.intellij.remoteDev.configureClientHook) | [`ConfigureClientHook`](%gh-ic%/platform/remoteDev-util/src/com/intellij/remoteDev/downloader/ConfigureClientHook.kt) |

### smRunner.xml

[`smRunner.xml`](%gh-ic%/platform/smRunner/resources/META-INF/smRunner.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.importTestOutput](https://jb.gg/ipe?extensions=com.intellij.importTestOutput) | [`ImportTestOutputExtension`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/history/ImportTestOutputExtension.java) |

### SpellCheckerPlugin.xml

[`SpellCheckerPlugin.xml`](%gh-ic%/spellchecker/resources/META-INF/SpellCheckerPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spellchecker.builtInDictionariesProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.builtInDictionariesProvider) ![Internal][internal] | [`BuiltInDictionariesProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/settings/BuiltInDictionariesProvider.kt) |
| [com.intellij.spellchecker.bundledDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.bundledDictionaryProvider) | [`BundledDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/BundledDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.checker](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.checker) | [`DictionaryChecker`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/DictionaryChecker.java) |
| [com.intellij.spellchecker.dictionary.customDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.customDictionaryProvider) | [`CustomDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/CustomDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionary.runtimeDictionaryProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionary.runtimeDictionaryProvider) | [`RuntimeDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/RuntimeDictionaryProvider.java) |
| [com.intellij.spellchecker.dictionaryLayersProvider](https://jb.gg/ipe?extensions=com.intellij.spellchecker.dictionaryLayersProvider) ![Internal][internal] | [`DictionaryLayersProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/DictionaryLevel.kt) |
| [com.intellij.spellchecker.lifecycle](https://jb.gg/ipe?extensions=com.intellij.spellchecker.lifecycle) ![Experimental][experimental] | [`SpellcheckerLifecycle`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/grazie/SpellcheckerLifecycle.kt) |
| [com.intellij.spellchecker.quickFixFactory](https://jb.gg/ipe?extensions=com.intellij.spellchecker.quickFixFactory) ![Internal][internal] | [`SpellCheckerQuickFixFactory`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/quickfixes/SpellCheckerQuickFixFactory.kt) |
| [com.intellij.spellchecker.support](https://jb.gg/ipe?extensions=com.intellij.spellchecker.support) ![DumbAware][dumb-aware] | [`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java) |

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
| [com.intellij.tasks.repositoryType](https://jb.gg/ipe?extensions=com.intellij.tasks.repositoryType) | [`TaskRepositoryType`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/TaskRepositoryType.java) |

### UsageViewActions.xml

[`UsageViewActions.xml`](%gh-ic%/platform/usageView/resources/idea/UsageViewActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.generatedSourceUsageFilter](https://jb.gg/ipe?extensions=com.intellij.generatedSourceUsageFilter) | [`GeneratedSourceUsageFilter`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/GeneratedSourceUsageFilter.kt) |
| [com.intellij.usages.usageReferenceClassProvider](https://jb.gg/ipe?extensions=com.intellij.usages.usageReferenceClassProvider) ![Non-Dynamic][non-dynamic] | [`UsageReferenceClassProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/impl/UsageReferenceClassProvider.kt) |

### WebSymbolsExtensionPoints.xml

[`WebSymbolsExtensionPoints.xml`](%gh-ic%/platform/webSymbols/resources/META-INF/WebSymbolsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.webSymbols.codeCompletionItemCustomizer](https://jb.gg/ipe?extensions=com.intellij.webSymbols.codeCompletionItemCustomizer) | [`WebSymbolCodeCompletionItemCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/completion/WebSymbolCodeCompletionItemCustomizer.kt) |
| [com.intellij.webSymbols.context](https://jb.gg/ipe?extensions=com.intellij.webSymbols.context) | [`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt) |
| [com.intellij.webSymbols.contextSourceProximityProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.contextSourceProximityProvider) | [`WebSymbolsContextSourceProximityProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextSourceProximityProvider.kt) |
| [com.intellij.webSymbols.declarationProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.declarationProvider) | [`WebSymbolDeclarationProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclarationProvider.kt) |
| [com.intellij.webSymbols.defaultIconProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.defaultIconProvider) | [`WebSymbolDefaultIconProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolDefaultIconProvider.kt) |
| [com.intellij.webSymbols.documentationCustomizer](https://jb.gg/ipe?extensions=com.intellij.webSymbols.documentationCustomizer) | [`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt) |
| [com.intellij.webSymbols.enableInLanguage](https://jb.gg/ipe?extensions=com.intellij.webSymbols.enableInLanguage) ![Experimental][experimental] | `n/a` |
| [com.intellij.webSymbols.framework](https://jb.gg/ipe?extensions=com.intellij.webSymbols.framework) | [`WebSymbolsFramework`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/framework/WebSymbolsFramework.kt) |
| [com.intellij.webSymbols.highlightingCustomizer](https://jb.gg/ipe?extensions=com.intellij.webSymbols.highlightingCustomizer) | [`WebSymbolHighlightingCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/highlighting/WebSymbolHighlightingCustomizer.kt) |
| [com.intellij.webSymbols.inspectionToolMapping](https://jb.gg/ipe?extensions=com.intellij.webSymbols.inspectionToolMapping) | `n/a` |
| [com.intellij.webSymbols.problemQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.problemQuickFixProvider) | [`WebSymbolsProblemQuickFixProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/inspections/WebSymbolsProblemQuickFixProvider.kt) |
| [com.intellij.webSymbols.psiReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.psiReferenceProvider) | [`PsiWebSymbolReferenceProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/references/PsiWebSymbolReferenceProvider.kt) |
| [com.intellij.webSymbols.psiSourcedSymbol](https://jb.gg/ipe?extensions=com.intellij.webSymbols.psiSourcedSymbol) | `n/a` |
| [com.intellij.webSymbols.psiSourcedSymbolProvider](https://jb.gg/ipe?extensions=com.intellij.webSymbols.psiSourcedSymbolProvider) | [`PsiSourcedWebSymbolProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbolProvider.kt) |
| [com.intellij.webSymbols.queryConfigurator](https://jb.gg/ipe?extensions=com.intellij.webSymbols.queryConfigurator) | [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt) |
| [com.intellij.webSymbols.queryResultsCustomizerFactory](https://jb.gg/ipe?extensions=com.intellij.webSymbols.queryResultsCustomizerFactory) | [`WebSymbolsQueryResultsCustomizerFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryResultsCustomizerFactory.kt) |
| [com.intellij.webSymbols.webTypes](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes) | `n/a` |
| [com.intellij.webSymbols.webTypes.filter](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes.filter) | [`WebSymbolsFilter`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/filters/WebSymbolsFilter.kt) |
| [com.intellij.webSymbols.webTypes.symbolFactory](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes.symbolFactory) | [`WebTypesSymbolFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/WebTypesSymbolFactory.kt) |
| [com.intellij.webSymbols.webTypes.symbolTypeSupportFactory](https://jb.gg/ipe?extensions=com.intellij.webSymbols.webTypes.symbolTypeSupportFactory) ![Internal][internal] | [`WebTypesSymbolTypeSupportFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/WebTypesSymbolTypeSupportFactory.kt) |

### WorkspaceModel.xml

[`WorkspaceModel.xml`](%gh-ic%/platform/projectModel-api/resources/META-INF/WorkspaceModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.workspace.bridgeInitializer](https://jb.gg/ipe?extensions=com.intellij.workspace.bridgeInitializer) ![Internal][internal] | [`BridgeInitializer`](%gh-ic%/platform/backend/workspace/src/BridgeInitializer.kt) |
| [com.intellij.workspaceModel.preUpdateHandler](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.preUpdateHandler) ![Internal][internal] | [`WorkspaceModelPreUpdateHandler`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelPreUpdateHandler.kt) |

### WorkspaceModelExtensions.xml

[`WorkspaceModelExtensions.xml`](%gh-ic%/platform/workspace/jps/resources/META-INF/WorkspaceModelExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.workspaceModel.customFacetRelatedEntitySerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customFacetRelatedEntitySerializer) ![Internal][internal] | [`CustomFacetRelatedEntitySerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomFacetRelatedEntitySerializer.kt) |
| [com.intellij.workspaceModel.customModuleComponentSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleComponentSerializer) ![Internal][internal] | [`CustomModuleComponentSerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomModuleComponentSerializer.kt) |
| [com.intellij.workspaceModel.customModuleRootsSerializer](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.customModuleRootsSerializer) ![Internal][internal] | [`CustomModuleRootsSerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomModuleRootsSerializer.kt) |

### WorkspaceModelImpl.xml

[`WorkspaceModelImpl.xml`](%gh-ic%/platform/projectModel-impl/resources/META-INF/WorkspaceModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.directoryIndexExcludePolicy](https://jb.gg/ipe?extensions=com.intellij.directoryIndexExcludePolicy) ![Project-Level][project-level] | [`DirectoryIndexExcludePolicy`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/DirectoryIndexExcludePolicy.java) |
| [com.intellij.workspaceModel.entityLifecycleSupporter](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.entityLifecycleSupporter) ![Experimental][experimental] | [`WorkspaceEntityLifecycleSupporter`](%gh-ic%/platform/backend/workspace/src/WorkspaceEntityLifecycleSupporter.kt) |
| [com.intellij.workspaceModel.fileIndexContributor](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.fileIndexContributor) ![Non-Dynamic][non-dynamic] | [`WorkspaceFileIndexContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/WorkspaceFileIndexContributor.kt) |
| [com.intellij.workspaceModel.optionalExclusionContributor](https://jb.gg/ipe?extensions=com.intellij.workspaceModel.optionalExclusionContributor) ![Experimental][experimental] ![Internal][internal] | [`OptionalExclusionContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/OptionalExclusionContributor.kt) |

### xdebugger.xml

[`xdebugger.xml`](%gh-ic%/platform/xdebugger-impl/resources/META-INF/xdebugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.xdebugger.attachDebuggerProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachDebuggerProvider) | [`XAttachDebuggerProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachDebuggerProvider.java) |
| [com.intellij.xdebugger.attachHostProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachHostProvider) | [`XAttachHostProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachHostProvider.kt) |
| [com.intellij.xdebugger.attachHostSettingsProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.attachHostSettingsProvider) | [`XAttachHostSettingsProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachHostSettingsProvider.kt) |
| [com.intellij.xdebugger.breakpointGroupingRule](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointGroupingRule) | [`XBreakpointGroupingRule`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/ui/XBreakpointGroupingRule.java) |
| [com.intellij.xdebugger.breakpointType](https://jb.gg/ipe?extensions=com.intellij.xdebugger.breakpointType) | [`XBreakpointType`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointType.java) |
| [com.intellij.xdebugger.configurableProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.configurableProvider) | [`DebuggerConfigurableProvider`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/DebuggerConfigurableProvider.java) |
| [com.intellij.xdebugger.controlExceptionBreakpointSupport](https://jb.gg/ipe?extensions=com.intellij.xdebugger.controlExceptionBreakpointSupport) | [`ControlExceptionBreakpointSupport`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/codeinsight/ControlExceptionBreakpointSupport.kt) |
| [com.intellij.xdebugger.debuggerSupport](https://jb.gg/ipe?extensions=com.intellij.xdebugger.debuggerSupport) ![Deprecated][deprecated] | [`DebuggerSupport`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/DebuggerSupport.java) |
| [com.intellij.xdebugger.dialog.item.presentation.provider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.dialog.item.presentation.provider) ![Experimental][experimental] | [`XAttachDialogItemPresentationProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachDialogItemPresentationProvider.kt) |
| [com.intellij.xdebugger.dialog.presentation.provider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.dialog.presentation.provider) ![Experimental][experimental] | [`XAttachDialogPresentationProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachDialogPresentationProvider.kt) |
| [com.intellij.xdebugger.dialog.process.view.provider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.dialog.process.view.provider) ![Internal][internal] | [`XAttachToProcessViewProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachToProcessViewProvider.kt) |
| [com.intellij.xdebugger.hotSwapUiExtension](https://jb.gg/ipe?extensions=com.intellij.xdebugger.hotSwapUiExtension) ![Internal][internal] | [`HotSwapUiExtension`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/hotswap/ui.kt) |
| [com.intellij.xdebugger.inlineBreakpointsDisabler](https://jb.gg/ipe?extensions=com.intellij.xdebugger.inlineBreakpointsDisabler) ![Experimental][experimental] | [`InlineBreakpointsDisabler`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/InlineBreakpointsDisabler.kt) |
| [com.intellij.xdebugger.inlineValuePopupProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.inlineValuePopupProvider) | [`InlineValuePopupProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/inline/InlineValuePopupProvider.java) |
| [com.intellij.xdebugger.nodeLinkActionProvider](https://jb.gg/ipe?extensions=com.intellij.xdebugger.nodeLinkActionProvider) ![Experimental][experimental] | [`XDebuggerNodeLinkActionProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/collection/visualizer/XDebuggerNodeLinkActionProvider.kt) |
| [com.intellij.xdebugger.settings](https://jb.gg/ipe?extensions=com.intellij.xdebugger.settings) | [`XDebuggerSettings`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/XDebuggerSettings.java) |
| [com.intellij.xdebugger.textValueVisualizer](https://jb.gg/ipe?extensions=com.intellij.xdebugger.textValueVisualizer) ![Experimental][experimental] | [`TextValueVisualizer`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/ui/TextValueVisualizer.kt) |

### xml.xml

[`xml.xml`](%gh-ic%/xml/impl/resources/META-INF/xml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.completion.htmlInTextCompletionEnabler](https://jb.gg/ipe?extensions=com.intellij.completion.htmlInTextCompletionEnabler) ![Internal][internal] | [`HtmlInTextCompletionEnabler`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionEnabler.java) |
| [com.intellij.completion.htmlInTextCompletionPopupExtension](https://jb.gg/ipe?extensions=com.intellij.completion.htmlInTextCompletionPopupExtension) ![Internal][internal] | [`HtmlInTextCompletionPopupExtension`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionPopupExtension.java) |
| [com.intellij.html.compatibleLanguage](https://jb.gg/ipe?extensions=com.intellij.html.compatibleLanguage) ![Experimental][experimental] | `n/a` |

### XmlPlugin.xml

[`XmlPlugin.xml`](%gh-ic%/platform/platform-resources/src/META-INF/XmlPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.embeddedTokenHighlighter](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenHighlighter) | [`EmbeddedTokenHighlighter`](%gh-ic%/xml/xml-frontback-impl/src/com/intellij/ide/highlighter/EmbeddedTokenHighlighter.java) |
| [com.intellij.embeddedTokenTypesProvider](https://jb.gg/ipe?extensions=com.intellij.embeddedTokenTypesProvider) | [`EmbeddedTokenTypesProvider`](%gh-ic%/xml/xml-parser/src/com/intellij/lexer/EmbeddedTokenTypesProvider.java) |
| [com.intellij.html.attributeValueProvider](https://jb.gg/ipe?extensions=com.intellij.html.attributeValueProvider) | [`HtmlAttributeValueProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/html/impl/providers/HtmlAttributeValueProvider.java) |
| [com.intellij.html.codestyle.panel](https://jb.gg/ipe?extensions=com.intellij.html.codestyle.panel) | [`HtmlCodeStylePanelExtension`](%gh-ic%/xml/impl/src/com/intellij/application/options/HtmlCodeStylePanelExtension.java) |
| [com.intellij.html.embeddedContentSupport](https://jb.gg/ipe?extensions=com.intellij.html.embeddedContentSupport) | [`HtmlEmbeddedContentSupport`](%gh-ic%/xml/xml-parser/src/com/intellij/html/embedding/HtmlEmbeddedContentSupport.kt) |
| [com.intellij.html.htmlScriptInjectionBlocker](https://jb.gg/ipe?extensions=com.intellij.html.htmlScriptInjectionBlocker) | [`HtmlScriptInjectionBlocker`](%gh-ic%/xml/impl/src/com/intellij/psi/impl/source/html/HtmlScriptInjectionBlocker.java) |
| [com.intellij.html.scriptContentProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptContentProvider) | [`HtmlScriptContentProvider`](%gh-ic%/xml/xml-parser/src/com/intellij/lang/HtmlScriptContentProvider.java) |
| [com.intellij.html.scriptDocumentationProvider](https://jb.gg/ipe?extensions=com.intellij.html.scriptDocumentationProvider) ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| [com.intellij.standardResource](https://jb.gg/ipe?extensions=com.intellij.standardResource) | `n/a` |
| [com.intellij.standardResourceProvider](https://jb.gg/ipe?extensions=com.intellij.standardResourceProvider) | [`StandardResourceProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/javaee/StandardResourceProvider.java) |
| [com.intellij.webSmartKeysConfigurable](https://jb.gg/ipe?extensions=com.intellij.webSmartKeysConfigurable) | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| [com.intellij.xml.attributeDescriptorsProvider](https://jb.gg/ipe?extensions=com.intellij.xml.attributeDescriptorsProvider) | [`XmlAttributeDescriptorsProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlAttributeDescriptorsProvider.java) |
| [com.intellij.xml.autoPopupEnabler](https://jb.gg/ipe?extensions=com.intellij.xml.autoPopupEnabler) ![Experimental][experimental] | [`XmlAutoPopupEnabler`](%gh-ic%/xml/xml-psi-api/src/com/intellij/xml/psi/codeInsight/XmlAutoPopupEnabler.java) |
| [com.intellij.xml.elementDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.elementDescriptorProvider) | [`XmlElementDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlElementDescriptorProvider.java) |
| [com.intellij.xml.fileNSInfoProvider](https://jb.gg/ipe?extensions=com.intellij.xml.fileNSInfoProvider) | [`XmlFileNSInfoProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFileNSInfoProvider.java) |
| [com.intellij.xml.idContributor](https://jb.gg/ipe?extensions=com.intellij.xml.idContributor) | [`XmlIdContributor`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/util/XmlIdContributor.java) |
| [com.intellij.xml.implicitIdRefProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitIdRefProvider) | [`ImplicitIdRefProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/ImplicitIdRefProvider.java) |
| [com.intellij.xml.implicitNamespaceDescriptorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.implicitNamespaceDescriptorProvider) | [`ImplicitNamespaceDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ImplicitNamespaceDescriptorProvider.java) |
| [com.intellij.xml.namedReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.xml.namedReferenceProvider) ![Experimental][experimental] | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| [com.intellij.xml.namespaceHelper](https://jb.gg/ipe?extensions=com.intellij.xml.namespaceHelper) | [`XmlNamespaceHelper`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlNamespaceHelper.java) |
| [com.intellij.xml.nsColorProvider](https://jb.gg/ipe?extensions=com.intellij.xml.nsColorProvider) | [`XmlNSColorProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/XmlNSColorProvider.java) |
| [com.intellij.xml.psiPolicy](https://jb.gg/ipe?extensions=com.intellij.xml.psiPolicy) | [`XmlPsiPolicy`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlPsiPolicy.java) |
| [com.intellij.xml.relatedToHtmlFilesContributor](https://jb.gg/ipe?extensions=com.intellij.xml.relatedToHtmlFilesContributor) | [`RelatedToHtmlFilesContributor`](%gh-ic%/xml/impl/src/com/intellij/navigation/RelatedToHtmlFilesContributor.java) |
| [com.intellij.xml.schemaProvider](https://jb.gg/ipe?extensions=com.intellij.xml.schemaProvider) ![DumbAware][dumb-aware] | [`XmlSchemaProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/xml/XmlSchemaProvider.kt) |
| [com.intellij.xml.startTagEndToken](https://jb.gg/ipe?extensions=com.intellij.xml.startTagEndToken) | [`StartTagEndTokenProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/xml/StartTagEndTokenProvider.java) |
| [com.intellij.xml.tagNameProvider](https://jb.gg/ipe?extensions=com.intellij.xml.tagNameProvider) | [`XmlTagNameProvider`](%gh-ic%/xml/impl/src/com/intellij/xml/XmlTagNameProvider.java) |
| [com.intellij.xml.undefinedElementFixProvider](https://jb.gg/ipe?extensions=com.intellij.xml.undefinedElementFixProvider) | [`XmlUndefinedElementFixProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/xml/XmlUndefinedElementFixProvider.java) |
| [com.intellij.xml.validateHandler](https://jb.gg/ipe?extensions=com.intellij.xml.validateHandler) | [`ValidateXmlHandler`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/actions/validate/ValidateXmlHandler.java) |
| [com.intellij.xml.xmlAttributeRenameProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlAttributeRenameProvider) | [`XmlUnknownAttributeQuickFixProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInspection/htmlInspections/XmlUnknownAttributeQuickFixProvider.java) |
| [com.intellij.xml.xmlCustomTagHighlightingStrategy](https://jb.gg/ipe?extensions=com.intellij.xml.xmlCustomTagHighlightingStrategy) ![Experimental][experimental] | [`XmlCustomTagHighlightingStrategy`](%gh-ic%/xml/xml-psi-api/src/com/intellij/openapi/editor/XmlCustomTagHighlightingStrategy.java) |
| [com.intellij.xml.xmlExtension](https://jb.gg/ipe?extensions=com.intellij.xml.xmlExtension) | [`XmlExtension`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlExtension.java) |
| [com.intellij.xml.xmlSuppressionProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlSuppressionProvider) ![DumbAware][dumb-aware] | [`XmlSuppressionProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/codeInspection/XmlSuppressionProvider.java) |
| [com.intellij.xml.xmlTagRuleProvider](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTagRuleProvider) | [`XmlTagRuleProvider`](%gh-ic%/xml/xml-analysis-api/src/com/intellij/xml/XmlTagRuleProvider.java) |
| [com.intellij.xml.xmlTypedHandlersAdditionalSupport](https://jb.gg/ipe?extensions=com.intellij.xml.xmlTypedHandlersAdditionalSupport) | [`XmlTypedHandlersAdditionalSupport`](%gh-ic%/xml/xml-psi-api/src/com/intellij/openapi/editor/XmlTypedHandlersAdditionalSupport.java) |
| [com.intellij.xml.zenCodingFilter](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingFilter) | [`ZenCodingFilter`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/filters/ZenCodingFilter.java) |
| [com.intellij.xml.zenCodingGenerator](https://jb.gg/ipe?extensions=com.intellij.xml.zenCodingGenerator) | [`ZenCodingGenerator`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/generators/ZenCodingGenerator.java) |
| [com.intellij.xmlStructureViewBuilderProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewBuilderProvider) | [`XmlStructureViewBuilderProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewBuilderProvider.java) |
| [com.intellij.xmlStructureViewElementProvider](https://jb.gg/ipe?extensions=com.intellij.xmlStructureViewElementProvider) | [`XmlStructureViewElementProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewElementProvider.java) |



## External System


### External System – Listeners

| Topic | Listener |
|-------|----------|
| [ExternalSystemProjectNotificationAware#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectNotificationAware.Listener)  | [`Listener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autoimport/ExternalSystemProjectNotificationAware.kt) |
| [ProjectDataImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener)  ![Project-Level][project-level] | [`ProjectDataImportListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataImportListener.java) |
| [ExternalSystemTestUtil#SETTINGS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.platform.externalSystem.testFramework.TestExternalSystemSettingsListener)  | [`TestExternalSystemSettingsListener`](%gh-ic%/platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/TestExternalSystemSettingsListener.java) |


### ExternalSystemDependencyUpdater.xml

[`ExternalSystemDependencyUpdater.xml`](%gh-ic%/platform/external-system-api/dependency-updater/resources/META-INF/ExternalSystemDependencyUpdater.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.externalSystem.dependencyModifier](https://jb.gg/ipe?extensions=com.intellij.externalSystem.dependencyModifier) ![Experimental][experimental] ![Project-Level][project-level] | [`ExternalDependencyModificator`](%gh-ic%/platform/external-system-api/dependency-updater/src/com/intellij/externalSystem/ExternalDependencyModificator.java) |

### ExternalSystemExtensionPoints.xml

[`ExternalSystemExtensionPoints.xml`](%gh-ic%/platform/external-system-impl/resources/META-INF/ExternalSystemExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.externalExecutionAware](https://jb.gg/ipe?extensions=com.intellij.externalExecutionAware) ![Experimental][experimental] | [`ExternalSystemExecutionAware`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemExecutionAware.kt) |
| [com.intellij.externalIconProvider](https://jb.gg/ipe?extensions=com.intellij.externalIconProvider) | [`ExternalSystemIconProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemIconProvider.kt) |
| [com.intellij.externalProjectDataService](https://jb.gg/ipe?extensions=com.intellij.externalProjectDataService) | [`ProjectDataService`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataService.java) |
| [com.intellij.externalProjectStructureCustomizer](https://jb.gg/ipe?extensions=com.intellij.externalProjectStructureCustomizer) | [`ExternalProjectStructureCustomizer`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/importing/ExternalProjectStructureCustomizer.java) |
| [com.intellij.externalProjectWatcherContributor](https://jb.gg/ipe?extensions=com.intellij.externalProjectWatcherContributor) ![Deprecated][deprecated] | [`Contributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/autoimport/ExternalSystemProjectsWatcherImpl.java) |
| [com.intellij.externalSystem.beforeRunTaskImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.beforeRunTaskImporter) | [`BeforeRunTaskImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/BeforeRunTaskImporter.java) |
| [com.intellij.externalSystem.debuggerBackend](https://jb.gg/ipe?extensions=com.intellij.externalSystem.debuggerBackend) | [`DebuggerBackendExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/debugger/DebuggerBackendExtension.java) |
| [com.intellij.externalSystem.facetConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.facetConfigurationImporter) | [`FacetConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/FacetConfigurationImporter.java) |
| [com.intellij.externalSystem.modifiableModelsProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystem.modifiableModelsProvider) ![Experimental][experimental] | [`ModifiableModelsProviderExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ModifiableModelsProviderExtension.java) |
| [com.intellij.externalSystem.runConfigurationEx](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationEx) | [`ExternalSystemRunConfigurationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/configuration/ExternalSystemRunConfigurationExtension.kt) |
| [com.intellij.externalSystem.runConfigurationImporter](https://jb.gg/ipe?extensions=com.intellij.externalSystem.runConfigurationImporter) | [`RunConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/RunConfigurationImporter.java) |
| [com.intellij.externalSystemConfigLocator](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigLocator) | [`ExternalSystemConfigLocator`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/settings/ExternalSystemConfigLocator.java) |
| [com.intellij.externalSystemConfigurationHandler](https://jb.gg/ipe?extensions=com.intellij.externalSystemConfigurationHandler) ![Experimental][experimental] | [`ConfigurationHandler`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/ConfigurationHandler.java) |
| [com.intellij.externalSystemContentRootContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemContentRootContributor) | [`ExternalSystemContentRootContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemContentRootContributor.kt) |
| [com.intellij.externalSystemCoordinateContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemCoordinateContributor) | [`ExternalSystemCoordinateContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ExternalSystemCoordinateContributor.kt) |
| [com.intellij.externalSystemCrcCalculator](https://jb.gg/ipe?extensions=com.intellij.externalSystemCrcCalculator) ![Experimental][experimental] | [`ExternalSystemCrcCalculator`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemCrcCalculator.kt) |
| [com.intellij.externalSystemDependencyAnalyzer](https://jb.gg/ipe?extensions=com.intellij.externalSystemDependencyAnalyzer) | [`DependencyAnalyzerExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/dependency/analyzer/DependencyAnalyzerExtension.kt) |
| [com.intellij.externalSystemExecutionConsoleManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemExecutionConsoleManager) | [`ExternalSystemExecutionConsoleManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/execution/ExternalSystemExecutionConsoleManager.java) |
| [com.intellij.externalSystemKeymapProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemKeymapProvider) | [`ActionsProvider`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemKeymapExtension.java) |
| [com.intellij.externalSystemManager](https://jb.gg/ipe?extensions=com.intellij.externalSystemManager) | [`ExternalSystemManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemManager.java) |
| [com.intellij.externalSystemNotificationExtension](https://jb.gg/ipe?extensions=com.intellij.externalSystemNotificationExtension) | [`ExternalSystemNotificationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/notification/ExternalSystemNotificationExtension.java) |
| [com.intellij.externalSystemOutputDispatcher](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputDispatcher) | [`ExternalSystemOutputDispatcherFactory`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputDispatcherFactory.kt) |
| [com.intellij.externalSystemOutputParserProvider](https://jb.gg/ipe?extensions=com.intellij.externalSystemOutputParserProvider) | [`ExternalSystemOutputParserProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputParserProvider.java) |
| [com.intellij.externalSystemRecoveryContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemRecoveryContributor) | [`Factory`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemRecoveryContributor.kt) |
| [com.intellij.externalSystemSettingsListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemSettingsListener) | [`ExternalSystemSettingsListenerEx`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/settings/ExternalSystemSettingsListenerEx.kt) |
| [com.intellij.externalSystemTaskNotificationListener](https://jb.gg/ipe?extensions=com.intellij.externalSystemTaskNotificationListener) | [`ExternalSystemTaskNotificationListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/task/ExternalSystemTaskNotificationListener.java) |
| [com.intellij.externalSystemTaskProgressIndicatorUpdater](https://jb.gg/ipe?extensions=com.intellij.externalSystemTaskProgressIndicatorUpdater) | [`ExternalSystemTaskProgressIndicatorUpdater`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/ExternalSystemTaskProgressIndicatorUpdater.kt) |
| [com.intellij.externalSystemUnlinkedProjectAware](https://jb.gg/ipe?extensions=com.intellij.externalSystemUnlinkedProjectAware) | [`ExternalSystemUnlinkedProjectAware`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autolink/ExternalSystemUnlinkedProjectAware.kt) |
| [com.intellij.externalSystemViewContributor](https://jb.gg/ipe?extensions=com.intellij.externalSystemViewContributor) | [`ExternalSystemViewContributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/view/ExternalSystemViewContributor.java) |
| [com.intellij.externalTextProvider](https://jb.gg/ipe?extensions=com.intellij.externalTextProvider) | [`ExternalSystemTextProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemTextProvider.kt) |
| [com.intellij.externalWorkspaceDataService](https://jb.gg/ipe?extensions=com.intellij.externalWorkspaceDataService) ![Experimental][experimental] | [`WorkspaceDataService`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/WorkspaceDataService.kt) |
| [com.intellij.libraryDataServiceExtension](https://jb.gg/ipe?extensions=com.intellij.libraryDataServiceExtension) ![Internal][internal] | [`LibraryDataServiceExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/LibraryDataServiceExtension.java) |
| [com.intellij.openapi.externalSystem.autoimport.autoReloadTypeProviderExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.externalSystem.autoimport.autoReloadTypeProviderExtension) ![Internal][internal] | [`DefaultAutoReloadTypeProvider`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/autoimport/DefaultAutoReloadTypeProvider.kt) |
| [com.intellij.openapi.externalSystem.projectSetupExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.externalSystem.projectSetupExtension) ![Internal][internal] | [`ExternalSystemProjectSetupExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemProjectSetupExtension.kt) |



## Version Control


### Version Control – Listeners

| Topic | Listener |
|-------|----------|
| [DvcsBranchManager#DVCS_BRANCH_SETTINGS_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.branch.DvcsBranchManager.DvcsBranchManagerListener)  | [`DvcsBranchManagerListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/branch/DvcsBranchManager.java) |
| [VcsRepositoryManager#VCS_REPOSITORY_MAPPING_UPDATED](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.repo.VcsRepositoryMappingListener)  ![Project-Level][project-level] | [`VcsRepositoryMappingListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryMappingListener.java) |
| [BranchChangeListener#VCS_BRANCH_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchChangeListener)  | [`BranchChangeListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchChangeListener.java) |
| [BranchRenameListener#VCS_BRANCH_RENAMED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchRenameListener)  | [`BranchRenameListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchRenameListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED_IN_PLUGIN](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.PluginVcsMappingListener)  ![Project-Level][project-level] | [`PluginVcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/PluginVcsMappingListener.java) |
| [ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.VcsMappingListener)  ![Project-Level][project-level] | [`VcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsMappingListener.java) |
| [ChangeListAvailabilityListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListAvailabilityListener)  ![Internal][internal] | [`ChangeListAvailabilityListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListAvailabilityListener.java) |
| [ChangeListListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListListener)  | [`ChangeListListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListListener.java) |
| [ChangesViewModifier#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewModifier.ChangesViewModifierListener)  ![Project-Level][project-level] | [`ChangesViewModifierListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [ChangesViewWorkflowManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewWorkflowManager.ChangesViewWorkflowListener)  ![Project-Level][project-level] | [`ChangesViewWorkflowListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewWorkflowManager.java) |
| [ChangeListManagerImpl#LISTS_LOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.LocalChangeListsLoadedListener)  ![Project-Level][project-level] | [`LocalChangeListsLoadedListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalChangeListsLoadedListener.java) |
| [VcsAnnotationRefresher#LOCAL_CHANGES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsAnnotationRefresher)  | [`VcsAnnotationRefresher`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsAnnotationRefresher.java) |
| [VcsEditorTabFilesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsEditorTabFilesListener)  ![Internal][internal] | [`VcsEditorTabFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsEditorTabFilesManager.kt) |
| [Listener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsFreezingProcess.Listener)  | [`Listener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsFreezingProcess.kt) |
| [VcsManagedFilesHolder#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsManagedFilesHolder.VcsManagedFilesHolderListener)  ![Project-Level][project-level] | [`VcsManagedFilesHolderListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [CommittedChangesCache#COMMITTED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesListener)  ![Project-Level][project-level] | [`CommittedChangesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesListener.java) |
| [CommittedChangesTreeBrowser#ITEMS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesTreeBrowser.CommittedChangesReloadListener)  ![Project-Level][project-level] | [`CommittedChangesReloadListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesTreeBrowser.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED_RESPONSE](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.DetailedNotification)  ![Project-Level][project-level] | [`DetailedNotification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [VcsConfigurationChangeListener#BRANCHES_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.Notification)  ![Project-Level][project-level] | [`Notification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [ShelveChangesManager#SHELF_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.shelf.ShelveChangesManagerListener)  ![Internal][internal] ![Project-Level][project-level] | [`ShelveChangesManagerListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveChangesManagerListener.java) |
| [ChangesViewContentManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ui.ChangesViewContentManagerListener)  ![Project-Level][project-level] | [`ChangesViewContentManagerListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentManagerListener.kt) |
| [ProjectLevelVcsManagerEx#VCS_ACTIVATED](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.ex.VcsActivationListener)  ![Project-Level][project-level] | [`VcsActivationListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/ex/VcsActivationListener.java) |
| [LineStatusTrackerManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerManager.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| [VcsBaseContentProviderListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.VcsBaseContentProviderListener)  ![Project-Level][project-level] | [`VcsBaseContentProviderListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProviderListener.java) |
| [UpdatedFilesListener#UPDATED_FILES](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.update.UpdatedFilesListener)  ![Project-Level][project-level] | [`UpdatedFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/update/UpdatedFilesListener.java) |
| [CommitModeManager#COMMIT_MODE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.CommitModeListener)  ![Project-Level][project-level] | [`CommitModeListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitModeManager#SETTINGS](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [CommitMessageInspectionProfile#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.message.CommitMessageInspectionProfile.ProfileListener)  | [`ProfileListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/message/CommitMessageInspectionProfile.java) |
| [VcsProjectLog#VCS_PROJECT_LOG_CHANGED](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.impl.VcsProjectLog.ProjectLogListener)  ![Project-Level][project-level] | [`ProjectLogListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/VcsProjectLog.kt) |
| [VcsLogBookmarksListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.VcsLogBookmarksListener)  | [`VcsLogBookmarksListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/VcsLogBookmarkProvider.kt) |
| [VcsLogCustomColumnListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.table.column.VcsLogCustomColumnListener)  | [`VcsLogCustomColumnListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |
| [CommitLinksResolveListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.table.links.CommitLinksResolveListener)  ![Experimental][experimental] ![Project-Level][project-level] | [`CommitLinksResolveListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/links/CommitLinksProvider.kt) |
| [RemoteRevisionsCache#REMOTE_VERSION_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |


### intellij.platform.lvcs.impl.xml

[`intellij.platform.lvcs.impl.xml`](%gh-ic%/platform/lvcs-impl/resources/intellij.platform.lvcs.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.history.activityPresentationProvider](https://jb.gg/ipe?extensions=com.intellij.history.activityPresentationProvider) ![Experimental][experimental] | [`ActivityPresentationProvider`](%gh-ic%/platform/lvcs-api/src/com/intellij/history/ActivityPresentationProvider.kt) |

### intellij.platform.vcs.dvcs.impl.xml

[`intellij.platform.vcs.dvcs.impl.xml`](%gh-ic%/platform/dvcs-impl/resources/intellij.platform.vcs.dvcs.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cherryPicker](https://jb.gg/ipe?extensions=com.intellij.cherryPicker) ![Project-Level][project-level] | [`VcsCherryPicker`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/cherrypick/VcsCherryPicker.java) |
| [com.intellij.clonePathProvider](https://jb.gg/ipe?extensions=com.intellij.clonePathProvider) | [`ClonePathProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/ClonePathProvider.java) |
| [com.intellij.commitNodeUiRenderExtension](https://jb.gg/ipe?extensions=com.intellij.commitNodeUiRenderExtension) ![Experimental][experimental] | [`CommitNodeUiRenderExtension`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/ui/CommitNodeUiRenderExtension.java) |
| [com.intellij.customPushOptionsPanelFactory](https://jb.gg/ipe?extensions=com.intellij.customPushOptionsPanelFactory) ![Experimental][experimental] | [`CustomPushOptionsPanelFactory`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/push/CustomPushOptionsPanelFactory.kt) |
| [com.intellij.prePushHandler](https://jb.gg/ipe?extensions=com.intellij.prePushHandler) | [`PrePushHandler`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PrePushHandler.java) |
| [com.intellij.pushDialogActionsProvider](https://jb.gg/ipe?extensions=com.intellij.pushDialogActionsProvider) ![Internal][internal] | [`PushDialogActionsProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogActionsProvider.kt) |
| [com.intellij.pushDialogCustomizer](https://jb.gg/ipe?extensions=com.intellij.pushDialogCustomizer) ![Internal][internal] | [`PushDialogCustomizer`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogCustomizer.kt) |
| [com.intellij.pushSupport](https://jb.gg/ipe?extensions=com.intellij.pushSupport) ![Project-Level][project-level] | [`PushSupport`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/push/PushSupport.java) |
| [com.intellij.vcsRepositoryCreator](https://jb.gg/ipe?extensions=com.intellij.vcsRepositoryCreator) | [`VcsRepositoryCreator`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryCreator.java) |

### intellij.platform.vcs.impl.backend.xml

[`intellij.platform.vcs.impl.backend.xml`](%gh-ic%/platform/vcs-impl/backend/resources/intellij.platform.vcs.impl.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.vcs.impl.backend.treeNodeConverter](https://jb.gg/ipe?extensions=com.intellij.vcs.impl.backend.treeNodeConverter) ![Internal][internal] | [`NodeToEntityConverter`](%gh-ic%/platform/vcs-impl/backend/src/com/intellij/platform/vcs/impl/backend/shelf/ChangesBrowserNodeConverter.kt) |

### intellij.platform.vcs.log.impl.xml

[`intellij.platform.vcs.log.impl.xml`](%gh-ic%/platform/vcs-log/impl/resources/intellij.platform.vcs.log.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.customVcsLogUiFactoryProvider](https://jb.gg/ipe?extensions=com.intellij.customVcsLogUiFactoryProvider) ![Experimental][experimental] ![Project-Level][project-level] | [`CustomVcsLogUiFactoryProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/CustomVcsLogUiFactoryProvider.java) |
| [com.intellij.fileHistoryPerformanceListener](https://jb.gg/ipe?extensions=com.intellij.fileHistoryPerformanceListener) ![Internal][internal] | [`FileHistoryPerformanceListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/history/FileHistoryPerformanceListener.kt) |
| [com.intellij.logHighlighterFactory](https://jb.gg/ipe?extensions=com.intellij.logHighlighterFactory) | [`VcsLogHighlighterFactory`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/highlighters/VcsLogHighlighterFactory.java) |
| [com.intellij.logProvider](https://jb.gg/ipe?extensions=com.intellij.logProvider) ![Project-Level][project-level] | [`VcsLogProvider`](%gh-ic%/platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogProvider.java) |
| [com.intellij.vcsLogCommitStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcsLogCommitStatusProvider) ![Experimental][experimental] | [`VcsCommitExternalStatusProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/frame/VcsCommitExternalStatusProvider.kt) |
| [com.intellij.vcsLogCustomColumn](https://jb.gg/ipe?extensions=com.intellij.vcsLogCustomColumn) | [`VcsLogCustomColumn`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |
| [com.intellij.vcsLogFileHistoryHandler](https://jb.gg/ipe?extensions=com.intellij.vcsLogFileHistoryHandler) ![Experimental][experimental] ![Project-Level][project-level] | [`VcsLogFileHistoryHandler`](%gh-ic%/platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogFileHistoryHandler.kt) |

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
| [com.intellij.ignoredFileProvider](https://jb.gg/ipe?extensions=com.intellij.ignoredFileProvider) | [`IgnoredFileProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileProvider.java) |
| [com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider) | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/actions/AnnotateToggleAction.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider) | [`ChangeDiffRequestProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffRequestProvider.java) |
| [com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider) ![Internal][internal] | [`ChangeDiffViewerWrapperProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffViewerWrapperProvider.java) |
| [com.intellij.openapi.vcs.changes.ui.filePathIconProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.ui.filePathIconProvider) | [`FilePathIconProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/FilePathIconProvider.java) |
| [com.intellij.openapi.vcs.changes.vcsPreservingExecutor](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.changes.vcsPreservingExecutor) | [`VcsPreservingExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsPreservingExecutor.java) |
| [com.intellij.openapi.vcs.history.actions.GetVersionAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.GetVersionAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider) | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| [com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider) | [`LocalLineStatusTrackerProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| [com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension) | [`VcsCloneDialogExtension`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/ui/cloneDialog/VcsCloneDialogExtension.kt) |
| [com.intellij.openapi.vcs.ui.commitOptionsDialogExtension](https://jb.gg/ipe?extensions=com.intellij.openapi.vcs.ui.commitOptionsDialogExtension) ![Experimental][experimental] | [`CommitOptionsDialogExtension`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/ui/CommitOptionsDialogExtension.kt) |
| [com.intellij.patch.extension](https://jb.gg/ipe?extensions=com.intellij.patch.extension) | [`PatchEP`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/diff/impl/patch/PatchEP.java) |
| [com.intellij.unresolvedMergeCheckProvider](https://jb.gg/ipe?extensions=com.intellij.unresolvedMergeCheckProvider) | [`UnresolvedMergeCheckProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/checkin/UnresolvedMergeCheckProvider.java) |
| [com.intellij.vcs](https://jb.gg/ipe?extensions=com.intellij.vcs) | [`AbstractVcs`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/AbstractVcs.java) |
| [com.intellij.vcs.actions.ScheduleForAdditionActionExtension](https://jb.gg/ipe?extensions=com.intellij.vcs.actions.ScheduleForAdditionActionExtension) | [`ScheduleForAdditionActionExtension`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/ScheduleForAdditionActionExtension.kt) |
| [com.intellij.vcs.baseContentProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.baseContentProvider) ![Project-Level][project-level] | [`VcsBaseContentProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProvider.java) |
| [com.intellij.vcs.branchStateProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.branchStateProvider) ![Project-Level][project-level] | [`BranchStateProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/branch/BranchStateProvider.java) |
| [com.intellij.vcs.changeListChangeAssigner](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListChangeAssigner) ![Experimental][experimental] ![Project-Level][project-level] | [`ChangeListChangeAssigner`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangeListChangeAssigner.java) |
| [com.intellij.vcs.changeListDecorator](https://jb.gg/ipe?extensions=com.intellij.vcs.changeListDecorator) ![Project-Level][project-level] | [`ChangeListDecorator`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListDecorator.java) |
| [com.intellij.vcs.changes.changesViewModifier](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewModifier) ![Project-Level][project-level] | [`ChangesViewModifier`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [com.intellij.vcs.changes.changesViewNodeAction](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.changesViewNodeAction) ![Experimental][experimental] ![Project-Level][project-level] | [`ChangesViewNodeAction`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewNodeAction.java) |
| [com.intellij.vcs.changes.localCommitExecutor](https://jb.gg/ipe?extensions=com.intellij.vcs.changes.localCommitExecutor) ![Project-Level][project-level] | [`CommitExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/CommitExecutor.java) |
| [com.intellij.vcs.checkoutProcessor](https://jb.gg/ipe?extensions=com.intellij.vcs.checkoutProcessor) | [`VcsCheckoutProcessor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsCheckoutProcessor.java) |
| [com.intellij.vcs.codeVisionLanguageContext](https://jb.gg/ipe?extensions=com.intellij.vcs.codeVisionLanguageContext) ![Experimental][experimental] | [`VcsCodeVisionLanguageContext`](%gh-ic%/platform/vcs-api/src/com/intellij/codeInsight/hints/VcsCodeVisionLanguageContext.kt) |
| [com.intellij.vcs.commitMessageProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.commitMessageProvider) | [`CommitMessageProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ui/CommitMessageProvider.java) |
| [com.intellij.vcs.consoleFolding](https://jb.gg/ipe?extensions=com.intellij.vcs.consoleFolding) | [`VcsConsoleFolding`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/console/VcsConsoleView.kt) |
| [com.intellij.vcs.defaultCommitMessagePolicy](https://jb.gg/ipe?extensions=com.intellij.vcs.defaultCommitMessagePolicy) ![Internal][internal] | [`DefaultCommitMessagePolicy`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/DefaultCommitMessagePolicy.kt) |
| [com.intellij.vcs.diffRevisionMetadataProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.diffRevisionMetadataProvider) | [`DiffRevisionMetadataProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/diff/impl/DiffRevisionMetadataProvider.kt) |
| [com.intellij.vcs.envCustomizer](https://jb.gg/ipe?extensions=com.intellij.vcs.envCustomizer) | [`VcsEnvCustomizer`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsEnvCustomizer.java) |
| [com.intellij.vcs.fileStatusProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.fileStatusProvider) ![Project-Level][project-level] | [`FileStatusProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/impl/FileStatusProvider.java) |
| [com.intellij.vcs.ignoredFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.ignoredFilesHolder) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcs.lineStatusClientIdRenderer](https://jb.gg/ipe?extensions=com.intellij.vcs.lineStatusClientIdRenderer) ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`LineStatusClientIdRenderer`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/ex/LineStatusClientIdRenderer.kt) |
| [com.intellij.vcs.shelveSilentlyGotItTooltipProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.shelveSilentlyGotItTooltipProvider) ![Internal][internal] | [`ShelveSilentlyGotItTooltipProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveSilentlyGotItTooltipProvider.kt) |
| [com.intellij.vcs.shelveSilentlyTitleProvider](https://jb.gg/ipe?extensions=com.intellij.vcs.shelveSilentlyTitleProvider) ![Experimental][experimental] | [`ShelveSilentlyTitleProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveSilentlyTitleProvider.kt) |
| [com.intellij.vcs.taskHandler](https://jb.gg/ipe?extensions=com.intellij.vcs.taskHandler) ![Project-Level][project-level] | [`VcsTaskHandler`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsTaskHandler.java) |
| [com.intellij.vcs.unversionedFilesHolder](https://jb.gg/ipe?extensions=com.intellij.vcs.unversionedFilesHolder) ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [com.intellij.vcs.vcsSymlinkResolver](https://jb.gg/ipe?extensions=com.intellij.vcs.vcsSymlinkResolver) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`VcsSymlinkResolver`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/VcsSymlinkResolver.java) |
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
| [com.intellij.vcsRootErrorFilter](https://jb.gg/ipe?extensions=com.intellij.vcsRootErrorFilter) ![Internal][internal] | [`VcsRootErrorFilter`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsRootErrorFilter.kt) |
| [com.intellij.vcsSelectionProvider](https://jb.gg/ipe?extensions=com.intellij.vcsSelectionProvider) | [`VcsSelectionProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcsUtil/VcsSelectionProvider.java) |
| [com.intellij.vcsSharedChecker](https://jb.gg/ipe?extensions=com.intellij.vcsSharedChecker) ![Internal][internal] ![Project-Level][project-level] | [`VcsSharedChecker`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/VcsSharedChecker.kt) |
| [com.intellij.vcsStartupActivity](https://jb.gg/ipe?extensions=com.intellij.vcsStartupActivity) ![Non-Dynamic][non-dynamic] | [`VcsStartupActivity`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsInitialization.kt) |

[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
