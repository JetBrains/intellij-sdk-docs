<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /community
EXCLUDING:
- /community/android
- /community/java
- /community/json
- /community/jvm
- /community/plugins
- /community/python
-->


<snippet id="content">

1172 Extension Points and 225 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## IntelliJ Platform

### IntelliJ Platform – Listeners

| Topic | Listener |
|-------|----------|
| [`ProblemsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.analysis.problemsView.ProblemsListener)  ![Project-Level][project-level] | [`ProblemsListener`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/problemsView/ProblemsListener.kt) |
| [`RainbowStateChangeListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.colors.RainbowStateChangeListener)  | [`RainbowStateChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/RainbowStateChangeListener.kt) |
| [`EditorOptionsListener#FOLDING_CONFIGURABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`EditorOptionsListener#APPEARANCE_CONFIGURABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`EditorOptionsListener#OPTIONS_PANEL_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`EditorOptionsListener#SMART_KEYS_CONFIGURABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`EditorOptionsListener#GUTTER_ICONS_CONFIGURABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.application.options.editor.EditorOptionsListener)  | [`EditorOptionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsListener.java) |
| [`ReaderModeSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.actions.ReaderModeListener)  ![Project-Level][project-level] | [`ReaderModeListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/ReaderModeListener.kt) |
| [`CodeVisionSettings#CODE_LENS_SETTINGS_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.codeVision.settings.CodeVisionSettings.CodeVisionSettingsListener)  | [`CodeVisionSettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettings.kt) |
| [`CompletionContributorListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionContributorListener)  ![Internal][internal] | [`CompletionContributorListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionContributorListener.kt) |
| [`CompletionPhaseListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.completion.CompletionPhaseListener)  | [`CompletionPhaseListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPhaseListener.java) |
| [`DaemonCodeAnalyzer#DAEMON_EVENT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.DaemonCodeAnalyzer.DaemonListener)  ![Project-Level][project-level] | [`DaemonListener`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java) |
| [`EditorTrackerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.EditorTrackerListener)  ![Project-Level][project-level] | [`EditorTrackerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/EditorTracker.kt) |
| [`FileHighlightingSettingListener#SETTING_CHANGE`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSettingListener)  | [`FileHighlightingSettingListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/FileHighlightingSettingListener.java) |
| [`DocRenderItemManagerImpl#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.documentation.render.DocRenderItemManagerImpl.Listener)  | [`Listener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/render/DocRenderItemManagerImpl.kt) |
| [`EditorHintListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.EditorHintListener)  | [`EditorHintListener`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/hint/EditorHintListener.java) |
| [`ExternalParameterInfoChangesProvider#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hint.ExternalParameterInfoChangesProvider)  | [`ExternalParameterInfoChangesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ExternalParameterInfoChangesProvider.java) |
| [`InlayHintsSettings#INLAY_SETTINGS_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.hints.InlayHintsSettings.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSettings.kt) |
| [`InlineCompletionInstallListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.inline.completion.InlineCompletionInstallListener)  ![Experimental][experimental] | [`InlineCompletionInstallListener`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionInstallListener.kt) |
| [`LookupManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.lookup.LookupManagerListener)  ![Project-Level][project-level] | [`LookupManagerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupManagerListener.java) |
| [`CodeInsightContextManager.Companion#topic`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.multiverse.CodeInsightContextChangeListener)  | [`CodeInsightContextChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextManager.kt) |
| [`EditorContextManager.Companion#topic`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.multiverse.EditorContextManager.ChangeEventListener)  | [`ChangeEventListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/multiverse/EditorContextManager.kt) |
| [`TemplateManager#TEMPLATE_STARTED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.template.TemplateManagerListener)  ![Project-Level][project-level] | [`TemplateManagerListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/TemplateManagerListener.java) |
| [`GlobalInspectionContextEx#INSPECT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInspection.ex.InspectListener)  ![Internal][internal] ![Project-Level][project-level] | [`InspectListener`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/ex/InspectListener.java) |
| [`BatchUpdateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.configurationStore.BatchUpdateListener)  ![Project-Level][project-level] | [`BatchUpdateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/configurationStore/BatchUpdateListener.java) |
| [`PasswordSafeSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.credentialStore.PasswordSafeSettingsListener)  | [`PasswordSafeSettingsListener`](%gh-ic%/platform/credential-store-impl/src/credentialStore/PasswordSafeSettingsListener.java) |
| [`CsvFormatsSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.csv.CsvFormatsSettings.Listener)  | [`Listener`](%gh-ic%/grid/csv/src/csv/CsvFormatsSettings.java) |
| [`DataGrid#ACTIVE_GRID_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGrid.ActiveGridListener)  | [`ActiveGridListener`](%gh-ic%/grid/impl/src/datagrid/DataGrid.java) |
| [`DataGridListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGridListener)  | [`DataGridListener`](%gh-ic%/grid/impl/src/datagrid/DataGridListener.java) |
| [`DataGridAppearanceSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridAppearanceSettings.Listener)  | [`Listener`](%gh-ic%/grid/core-impl/src/settings/DataGridAppearanceSettings.java) |
| [`DataGridSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridSettings.Listener)  | [`Listener`](%gh-ic%/grid/core-impl/src/settings/DataGridSettings.java) |
| [`IdePerformanceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.IdePerformanceListener)  ![Internal][internal] | [`IdePerformanceListener`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/IdePerformanceListener.kt) |
| [`RunnablesListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.diagnostic.RunnablesListener)  ![Experimental][experimental] ![Internal][internal] | [`RunnablesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/RunnablesListener.java) |
| [`ExecutionManager#EXECUTION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionListener)  ![Project-Level][project-level] | [`ExecutionListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionListener.java) |
| [`ExecutionTargetManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ExecutionTargetListener)  | [`ExecutionTargetListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ExecutionTargetListener.java) |
| [`RunManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.RunManagerListener)  ![Project-Level][project-level] | [`RunManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/execution/RunManagerListener.java) |
| [`RunDashboardManager#DASHBOARD_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.dashboard.RunDashboardListener)  | [`RunDashboardListener`](%gh-ic%/platform/execution/src/com/intellij/execution/dashboard/RunDashboardListener.java) |
| [`ExecutionEventsBus#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.multilaunch.execution.messaging.ExecutionNotifier)  | [`ExecutionNotifier`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/messaging/ExecutionNotifier.kt) |
| [`Listener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.process.elevation.settings.ElevationSettings.Listener)  | [`Listener`](%gh-ic%/platform/execution-process-elevation/src/com/intellij/execution/process/elevation/settings/ElevationSettings.kt) |
| [`RunToolbarSlotManager#RUN_TOOLBAR_SLOT_CONFIGURATION_MAP_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.runToolbar.data.RWSlotsConfigurationListener)  ![Project-Level][project-level] | [`RWSlotsConfigurationListener`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/runToolbar/data/RWSlotsConfigurationListener.kt) |
| [`ServiceEventListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.services.ServiceEventListener)  | [`ServiceEventListener`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceEventListener.java) |
| [`AutoTestListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.autotest.AutoTestListener)  ![Internal][internal] ![Project-Level][project-level] | [`AutoTestListener`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/autotest/AutoTestListener.kt) |
| [`SMTRunnerEventsListener#TEST_STATUS`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener)  ![Project-Level][project-level] | [`SMTRunnerEventsListener`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/SMTRunnerEventsListener.java) |
| [`RunConfigurationStartHistory#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunConfigurationStartHistory.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ui/RunToolbarPopup.kt) |
| [`RunContentManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.execution.ui.RunContentWithExecutorListener)  | [`RunContentWithExecutorListener`](%gh-ic%/platform/execution/src/com/intellij/execution/ui/RunContentWithExecutorListener.java) |
| [`FacetManager#FACETS_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.facet.FacetManagerListener)  ![Project-Level][project-level] | [`FacetManagerListener`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetManagerListener.java) |
| [`FeatureStatisticsUpdateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeatureStatisticsUpdateListener)  ![Internal][internal] | [`FeatureStatisticsUpdateListener`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/FeatureStatisticsUpdateListener.kt) |
| [`FeaturesRegistryListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.featureStatistics.FeaturesRegistryListener)  ![Experimental][experimental] | [`FeaturesRegistryListener`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/FeaturesRegistryListener.java) |
| [`FindManager#FIND_MODEL_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.find.FindModelListener)  ![Project-Level][project-level] | [`FindModelListener`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindModelListener.java) |
| [`AppLifecycleListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.AppLifecycleListener)  | [`AppLifecycleListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/AppLifecycleListener.java) |
| [`FrameStateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.FrameStateListener)  | [`FrameStateListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/FrameStateListener.java) |
| [`PowerSaveMode#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.PowerSaveMode.Listener)  | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/ide/PowerSaveMode.java) |
| [`RecentProjectsManager.Companion#RECENT_PROJECTS_CHANGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.RecentProjectsManager.RecentProjectsChange)  | [`RecentProjectsChange`](%gh-ic%/platform/ide-core/src/com/intellij/ide/RecentProjectsManager.kt) |
| [`RegionSettingsListener#UPDATE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.RegionSettings.RegionSettingsListener)  ![Experimental][experimental] ![Internal][internal] | [`RegionSettingsListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/RegionSettings.java) |
| [`SaveAndSyncHandlerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.SaveAndSyncHandlerListener)  ![Experimental][experimental] | [`SaveAndSyncHandlerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SaveAndSyncHandlerListener.java) |
| [`SearchEverywhereUI#PREVIEW_EVENTS`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.PreviewListener)  ![Internal][internal] | [`PreviewListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/PreviewListener.java) |
| [`SEHeaderActionListener.Companion#SE_HEADER_ACTION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SEHeaderActionListener)  ![Internal][internal] | [`SEHeaderActionListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEHeaderActionListener.kt) |
| [`SETabSwitcherListener.Companion#SE_TAB_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SETabSwitcherListener)  ![Internal][internal] | [`SETabSwitcherListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SETabSwitcherListener.kt) |
| [`SearchEverywhereUI#SEARCH_EVENTS`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.actions.searcheverywhere.SearchListener)  | [`SearchListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchListener.java) |
| [`BookmarksListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmark.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListener.java) |
| [`BookmarksListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.bookmarks.BookmarksListener)  | [`BookmarksListener`](%gh-ic%/platform/bookmarks/src/com/intellij/ide/bookmarks/BookmarksListener.java) |
| [`BatchFileChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.file.BatchFileChangeListener)  | [`BatchFileChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/file/BatchFileChangeListener.java) |
| [`DataSharingSettingsChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.gdpr.DataSharingSettingsChangeListener)  ![Internal][internal] | [`DataSharingSettingsChangeListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/gdpr/DataSharingSettingsChangeListener.kt) |
| [`TrustStateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.impl.TrustStateListener)  ![Deprecated][deprecated] | [`TrustStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) |
| [`LightEditServiceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.lightEdit.LightEditServiceListener)  ![Experimental][experimental] | [`LightEditServiceListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditServiceListener.java) |
| [`RiderMainToolbarStateListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.navigationToolbar.rider.RiderMainToolbarStateListener)  ![Project-Level][project-level] | [`RiderMainToolbarStateListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/rider/RiderMainToolbarRootPaneExtension.kt) |
| [`DynamicPluginListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.DynamicPluginListener)  | [`DynamicPluginListener`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginListener.kt) |
| [`PluginRepositoryAuthListener#PLUGIN_REPO_AUTH_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.plugins.auth.PluginRepositoryAuthListener)  | [`PluginRepositoryAuthListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthListener.java) |
| [`ProjectViewSelectionTopicKt#PROJECT_VIEW_SELECTION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.ProjectViewSelectionListener)  ![Project-Level][project-level] | [`ProjectViewSelectionListener`](%gh-ic%/platform/lang-api/src/com/intellij/ide/projectView/ProjectViewSelectionTopic.kt) |
| [`ProjectViewListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.projectView.impl.ProjectViewListener)  ![Project-Level][project-level] | [`ProjectViewListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewListener.java) |
| [`TrustedProjectsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.trustedProjects.TrustedProjectsListener)  ![Experimental][experimental] | [`TrustedProjectsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/trustedProjects/TrustedProjectsListener.kt) |
| [`LafManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.LafManagerListener)  | [`LafManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/LafManagerListener.java) |
| [`UISettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.UISettingsListener)  | [`UISettingsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/UISettingsListener.java) |
| [`VirtualFileAppearanceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.VirtualFileAppearanceListener)  | [`VirtualFileAppearanceListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/VirtualFileAppearanceListener.java) |
| [`CustomActionsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.customization.CustomActionsListener)  | [`CustomActionsListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomActionsListener.kt) |
| [`ComponentHighlightingListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.ui.search.ComponentHighlightingListener)  | [`ComponentHighlightingListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/ComponentHighlightingListener.java) |
| [`FileStructurePopupListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.FileStructurePopupListener)  ![Experimental][experimental] ![Internal][internal] ![Project-Level][project-level] | [`FileStructurePopupListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/FileStructurePopupListener.java) |
| [`SettingsChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.util.scopeChooser.ScopeEditorPanel.SettingsChangedListener)  ![Project-Level][project-level] | [`SettingsChangedListener`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeEditorPanel.java) |
| [`EventLogConfigOptionsService#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.internal.statistic.eventLog.EventLogConfigOptionsListener)  | [`EventLogConfigOptionsListener`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/EventLogConfigOptionsListener.java) |
| [`ExternalResourceListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.javaee.ExternalResourceListener)  | [`ExternalResourceListener`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceListener.java) |
| [`LocalizationListener.Companion#UPDATE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.l10n.LocalizationListener)  ![Internal][internal] | [`LocalizationListener`](%gh-ic%/platform/core-api/src/com/intellij/l10n/LocalizationListener.kt) |
| [`DocumentationPopupListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.documentation.ide.impl.DocumentationPopupListener)  ![Project-Level][project-level] | [`DocumentationPopupListener`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/ide/impl/DocumentationPopupListener.kt) |
| [`EndpointsChangeTracker.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsChangeTracker)  ![Project-Level][project-level] | [`EndpointsChangeTracker`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsChangeTracker.kt) |
| [`EndpointsViewListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsViewListener)  ![Project-Level][project-level] | [`EndpointsViewListener`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsViewListener.kt) |
| [`EndpointsViewOpener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.microservices.endpoints.EndpointsViewOpener)  ![Project-Level][project-level] | [`EndpointsViewOpener`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsViewOpener.kt) |
| [`NotebookEditorModeKt#NOTEBOOK_EDITOR_MODE`](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.ui.editor.actions.command.mode.NotebookEditorModeListener)  | [`NotebookEditorModeListener`](%gh-ic%/notebooks/notebook-ui/src/com/intellij/notebooks/ui/editor/actions/command/mode/NotebookEditorMode.kt) |
| [`ChangeListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.visualization.NotebookIntervalPointerFactory.ChangeListener)  | [`ChangeListener`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookIntervalPointer.kt) |
| [`JupyterCellSelectionNotifier.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.notebooks.visualization.ui.JupyterCellSelectionNotifier)  | [`JupyterCellSelectionNotifier`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/ui/JupyterCellSelectionNotifier.kt) |
| [`ActionCenter#MODEL_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.notification.ActionCenter.EventListener)  | [`EventListener`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/ActionCenter.java) |
| [`Notifications#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.notification.Notifications)  ![Project-Level][project-level] | [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java) |
| [`AnActionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.ex.AnActionListener)  | [`AnActionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ex/AnActionListener.java) |
| [`ToolbarActionsUpdatedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.actionSystem.impl.segmentedActionBar.ToolbarActionsUpdatedListener)  ![Internal][internal] | [`ToolbarActionsUpdatedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/segmentedActionBar/ToolbarActionsUpdatedListener.java) |
| [`ApplicationActivationListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.application.ApplicationActivationListener)  | [`ApplicationActivationListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) |
| [`CommandListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.CommandListener)  | [`CommandListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/command/CommandListener.java) |
| [`UndoRedoListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.command.impl.UndoRedoListener)  ![Experimental][experimental] ![Internal][internal] | [`UndoRedoListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoRedoListener.kt) |
| [`Listener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.EditorMouseHoverPopupManager.Listener)  ![Internal][internal] ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/EditorMouseHoverPopupManager.java) |
| [`LatencyListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.actionSystem.LatencyListener)  | [`LatencyListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/LatencyListener.java) |
| [`EditorColorsManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.EditorColorsListener)  | [`EditorColorsListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsListener.java) |
| [`EditorColorsManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.colors.impl.EditorColorsManagerListener)  ![Internal][internal] | [`EditorColorsManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/colors/impl/EditorColorsManagerListener.kt) |
| [`DocumentBulkUpdateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.editor.ex.DocumentBulkUpdateListener)  ![Deprecated][deprecated] ![Removal][removal] | [`DocumentBulkUpdateListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/ex/DocumentBulkUpdateListener.java) |
| [`AppTopics#FILE_DOCUMENT_SYNC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  ![Deprecated][deprecated] | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [`FileDocumentManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileDocumentManagerListener)  | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| [`FileEditorManagerListener#FILE_EDITOR_MANAGER`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener)  ![Project-Level][project-level] | [`FileEditorManagerListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [`Before#FILE_EDITOR_MANAGER`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileEditorManagerListener.Before)  ![Project-Level][project-level] | [`Before`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java) |
| [`FileOpenedSyncListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.FileOpenedSyncListener)  ![Project-Level][project-level] | [`FileOpenedSyncListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileOpenedSyncListener.kt) |
| [`FileDocumentBindingListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.FileDocumentBindingListener)  ![Experimental][experimental] ![Internal][internal] | [`FileDocumentBindingListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/fileEditor/impl/FileDocumentBindingListener.kt) |
| [`RecentPlacesListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileEditor.impl.IdeDocumentHistoryImpl.RecentPlacesListener)  ![Project-Level][project-level] | [`RecentPlacesListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/IdeDocumentHistoryImpl.kt) |
| [`FileTypeManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.fileTypes.FileTypeListener)  | [`FileTypeListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeListener.java) |
| [`KeymapManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.KeymapManagerListener)  | [`KeymapManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapManagerListener.java) |
| [`KeymapListener#CHANGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.keymap.impl.ui.KeymapListener)  | [`KeymapListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/keymap/impl/ui/KeymapListener.java) |
| [`AdvancedSettingsChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.advanced.AdvancedSettingsChangeListener)  | [`AdvancedSettingsChangeListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/options/advanced/AdvancedSettings.kt) |
| [`ExternalUpdateRequest#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.ExternalUpdateRequest)  ![Experimental][experimental] ![Internal][internal] | [`ExternalUpdateRequest`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/options/newEditor/ExternalUpdateRequest.java) |
| [`SettingsDialogListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.options.newEditor.SettingsDialogListener)  ![Experimental][experimental] ![Internal][internal] | [`SettingsDialogListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/options/newEditor/SettingsDialogListener.kt) |
| [`ProgressManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.ProgressManagerListener)  ![Internal][internal] | [`ProgressManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManagerListener.java) |
| [`ProgressSuspender#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.impl.ProgressSuspender.SuspenderListener)  | [`SuspenderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/impl/ProgressSuspender.java) |
| [`ProgressWindow#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.progress.util.ProgressWindow.Listener)  | [`Listener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) |
| [`BaseProjectDirectories#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.BaseProjectDirectoriesListener)  ![Project-Level][project-level] | [`BaseProjectDirectoriesListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/project/BaseProjectDirectoriesListener.kt) |
| [`DumbService#DUMB_MODE`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.DumbService.DumbModeListener)  ![Project-Level][project-level] | [`DumbModeListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.kt) |
| [`ProjectTopics#MODULES`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Deprecated][deprecated] ![Project-Level][project-level] | [`ModuleListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [`ModuleListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ModuleListener)  ![Project-Level][project-level] | [`ModuleListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ModuleListener.java) |
| [`ProjectCloseListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectCloseListener)  ![Experimental][experimental] | [`ProjectCloseListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseListener.kt) |
| [`ProjectManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectManagerListener)  | [`ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) |
| [`ProjectNameListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.ProjectNameListener)  ![Project-Level][project-level] | [`ProjectNameListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/ProjectNameListener.kt) |
| [`DefaultProjectListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.DefaultProjectListener)  ![Internal][internal] | [`DefaultProjectListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/DefaultProjectListener.kt) |
| [`ProjectLifecycleListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.ProjectLifecycleListener)  | [`ProjectLifecycleListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/project/impl/ProjectLifecycleListener.java) |
| [`ConfigFolderChangedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.project.impl.shared.ConfigFolderChangedListener)  | [`ConfigFolderChangedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/shared/SharedConfigFolderUtil.kt) |
| [`ProjectJdkTable#JDK_TABLE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.projectRoots.ProjectJdkTable.Listener)  | [`Listener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) |
| [`AdditionalLibraryRootsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.AdditionalLibraryRootsListener)  ![Experimental][experimental] ![Project-Level][project-level] | [`AdditionalLibraryRootsListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsListener.java) |
| [`ProjectTopics#PROJECT_ROOTS`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Deprecated][deprecated] ![Project-Level][project-level] | [`ModuleRootListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [`ModuleRootListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ModuleRootListener)  ![Project-Level][project-level] | [`ModuleRootListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootListener.java) |
| [`BalloonListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.ui.popup.BalloonListener)  | [`BalloonListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/BalloonListener.kt) |
| [`PluginAutoUpdateListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.updateSettings.impl.PluginAutoUpdateListener)  | [`PluginAutoUpdateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/PluginAutoUpdateService.kt) |
| [`RegistryManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.util.registry.RegistryValueListener)  | [`RegistryValueListener`](%gh-ic%/platform/util/src/com/intellij/openapi/util/registry/RegistryValueListener.java) |
| [`FileStatusListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.FileStatusListener)  ![Project-Level][project-level] | [`FileStatusListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/vcs/FileStatusListener.java) |
| [`LineStatusTrackerSettingListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerSettingListener)  | [`LineStatusTrackerSettingListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerSettingListener.java) |
| [`VirtualFileManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.VirtualFileManagerListener)  | [`VirtualFileManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| [`EncodingManagerListener#ENCODING_MANAGER_CHANGES`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.encoding.EncodingManagerListener)  | [`EncodingManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/EncodingManagerListener.java) |
| [`VirtualFileManager#VFS_CHANGES`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.newvfs.BulkFileListener)  | [`BulkFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListener.java) |
| [`VirtualFilePointerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vfs.pointers.VirtualFilePointerListener)  | [`VirtualFilePointerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/pointers/VirtualFilePointerListener.java) |
| [`Info#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.StatusBarInfo)  ![Project-Level][project-level] | [`StatusBarInfo`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/StatusBarInfo.java) |
| [`ToolWindowManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.ToolWindowManagerListener)  ![Project-Level][project-level] | [`ToolWindowManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/ex/ToolWindowManagerListener.java) |
| [`WindowManagerListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.ex.WindowManagerListener)  ![Internal][internal] | [`WindowManagerListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/ex/WindowManagerListener.kt) |
| [`TitleInfoProvider#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.TitleInfoProvider.TitleInfoProviderListener)  ![Internal][internal] | [`TitleInfoProviderListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| [`WelcomeBalloonLayoutImpl#BALLOON_NOTIFICATION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeBalloonLayoutImpl.BalloonNotificationListener)  | [`BalloonNotificationListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeBalloonLayoutImpl.java) |
| [`WelcomeScreenComponentListener#COMPONENT_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenComponentListener)  ![Internal][internal] | [`WelcomeScreenComponentListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/WelcomeScreenComponentListener.java) |
| [`CloneableProjectsService#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.cloneableProjects.CloneableProjectsService.CloneProjectListener)  | [`CloneProjectListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/cloneableProjects/CloneableProjectsService.kt) |
| [`CourseDataStorageKt#COURSE_DELETED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.wm.impl.welcomeScreen.learnIde.coursesInProgress.CourseDeletedListener)  ![Internal][internal] | [`CourseDeletedListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/learnIde/coursesInProgress/CourseDeletedListener.kt) |
| [`ModuleAttachListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.platform.ModuleAttachListener)  ![Project-Level][project-level] | [`ModuleAttachListener`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/ModuleAttachListener.kt) |
| [`WorkspaceModelTopics#CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.platform.backend.workspace.WorkspaceModelChangeListener)  ![Obsolete][obsolete] ![Project-Level][project-level] | [`WorkspaceModelChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt) |
| [`WorkspaceModelTopics#UNLOADED_ENTITIES_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.platform.backend.workspace.WorkspaceModelUnloadedStorageChangeListener)  ![Internal][internal] ![Project-Level][project-level] | [`WorkspaceModelUnloadedStorageChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt) |
| [`TelemetryReceivedListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.platform.diagnostic.telemetry.impl.TelemetryReceivedListener)  ![Experimental][experimental] ![Internal][internal] | [`TelemetryReceivedListener`](%gh-ic%/platform/diagnostic/telemetry-impl/src/TelemetryReceivedListener.kt) |
| [`ProblemListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.problems.ProblemListener)  ![Project-Level][project-level] | [`ProblemListener`](%gh-ic%/platform/analysis-api/src/com/intellij/problems/ProblemListener.java) |
| [`ProfileChangeAdapter#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.profile.ProfileChangeAdapter)  ![Project-Level][project-level] | [`ProfileChangeAdapter`](%gh-ic%/platform/analysis-api/src/com/intellij/profile/ProfileChangeAdapter.java) |
| [`PsiDocumentListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.PsiDocumentListener)  ![Project-Level][project-level] | [`PsiDocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentListener.java) |
| [`Listener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.codeStyle.CodeStyleManager.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/codeStyle/CodeStyleManager.java) |
| [`CodeStyleSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.codeStyle.CodeStyleSettingsListener)  ![Project-Level][project-level] | [`CodeStyleSettingsListener`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsListener.java) |
| [`PsiManagerImpl#ANY_PSI_CHANGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.AnyPsiChangeListener)  ![Project-Level][project-level] | [`AnyPsiChangeListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/AnyPsiChangeListener.java) |
| [`PsiDocumentTransactionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.impl.PsiDocumentTransactionListener)  ![Project-Level][project-level] | [`PsiDocumentTransactionListener`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiDocumentTransactionListener.java) |
| [`FileTypeIndex#INDEX_CHANGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.search.FileTypeIndex.IndexChangeListener)  ![Experimental][experimental] | [`IndexChangeListener`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) |
| [`PsiModificationTracker#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.psi.util.PsiModificationTracker.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) |
| [`RefactoringEventListener#REFACTORING_EVENT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.refactoring.listeners.RefactoringEventListener)  ![Project-Level][project-level] | [`RefactoringEventListener`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/listeners/RefactoringEventListener.java) |
| [`RemoteMappingsListener#REMOTE_MAPPINGS_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.remote.RemoteMappingsListener)  | [`RemoteMappingsListener`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/RemoteMappingsListener.java) |
| [`RemoteServerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.configuration.RemoteServerListener)  | [`RemoteServerListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/RemoteServerListener.java) |
| [`ServerConnectionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.remoteServer.runtime.ServerConnectionListener)  | [`ServerConnectionListener`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/runtime/ServerConnectionListener.java) |
| [`SpellCheckerEngineListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spellchecker.engine.SpellCheckerEngineListener)  ![Project-Level][project-level] | [`SpellCheckerEngineListener`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/engine/SpellCheckerEngineListener.java) |
| [`ProjectTaskListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.task.ProjectTaskListener)  ![Project-Level][project-level] | [`ProjectTaskListener`](%gh-ic%/platform/lang-api/src/com/intellij/task/ProjectTaskListener.java) |
| [`DeferredIconListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ui.DeferredIconListener)  ![Internal][internal] | [`DeferredIconListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/DeferredIconListener.kt) |
| [`AuthStateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ui.JBAccountInfoService.AuthStateListener)  | [`AuthStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/JBAccountInfoService.java) |
| [`LicenseStateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ui.LicensingFacade.LicenseStateListener)  | [`LicenseStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/LicensingFacade.java) |
| [`JBCefHealthCheckTopic#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ui.jcef.JBCefHealthMonitor.JBCefHealthCheckTopic)  | [`JBCefHealthCheckTopic`](%gh-ic%/platform/ui.jcef/jcef/JBCefHealthMonitor.java) |
| [`ToolWindowViewModelListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ui.viewModel.extraction.ToolWindowViewModelListener)  ![Project-Level][project-level] | [`ToolWindowViewModelListener`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelListener.java) |
| [`UnindexedFilesUpdaterListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.UnindexedFilesUpdaterListener)  ![Deprecated][deprecated] | [`UnindexedFilesUpdaterListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/UnindexedFilesUpdaterListener.java) |
| [`ProjectIndexingActivityHistoryListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.util.indexing.diagnostic.ProjectIndexingActivityHistoryListener)  | [`ProjectIndexingActivityHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| [`WebSymbolContextChangeListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.webSymbols.context.WebSymbolContextChangeListener)  | [`WebSymbolContextChangeListener`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolContextChangeListener.kt) |
| [`WebSymbolsQueryExecutorListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.webSymbols.query.WebSymbolsQueryExecutorListener)  | [`WebSymbolsQueryExecutorListener`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutorListener.kt) |
| [`JpsGlobalModelLoadedListener.Companion#LOADED`](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsGlobalModelLoadedListener)  ![Internal][internal] | [`JpsGlobalModelLoadedListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/JpsGlobalModelLoadedListener.kt) |
| [`JpsProjectLoadedListener.Companion#LOADED`](https://jb.gg/ipe/listeners?topics=com.intellij.workspaceModel.ide.JpsProjectLoadedListener)  ![Internal][internal] ![Project-Level][project-level] | [`JpsProjectLoadedListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/workspaceModel/ide/JpsProjectLoadedListener.kt) |
| [`XDebuggerManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XDebuggerManagerListener)  ![Project-Level][project-level] | [`XDebuggerManagerListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/XDebuggerManagerListener.java) |
| [`XEvaluationListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.XEvaluationListener)  ![Internal][internal] | [`XEvaluationListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/XEvaluationListener.kt) |
| [`XBreakpointListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.breakpoints.XBreakpointListener)  ![Project-Level][project-level] | [`XBreakpointListener`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointListener.java) |
| [`XDependentBreakpointListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.xdebugger.impl.breakpoints.XDependentBreakpointListener)  ![Project-Level][project-level] | [`XDependentBreakpointListener`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/breakpoints/XDependentBreakpointListener.java) |
| [`BreadcrumbsInitListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.xml.breadcrumbs.BreadcrumbsInitListener)  ![Internal][internal] ![Project-Level][project-level] | [`BreadcrumbsInitListener`](%gh-ic%/platform/platform-impl/src/com/intellij/xml/breadcrumbs/BreadcrumbsInitListener.java) |
| [`TodoConfiguration#PROPERTY_CHANGE`](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  ![Project-Level][project-level] | `PropertyChangeListener` |
| [`IndexPatternProvider#INDEX_PATTERNS_CHANGED`](https://jb.gg/ipe/listeners?topics=java.beans.PropertyChangeListener)  | `PropertyChangeListener` |
| [`SeverityRegistrar#SEVERITIES_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [`UsageFilteringRuleProvider#RULES_CHANGED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [`RunToolbarPopupKt#VOID_EXECUTION_TOPIC`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [`StructureViewWrapperImpl#STRUCTURE_CHANGED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [`UpdateActionsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.ide.UpdateActionsListener)  | [`UpdateActionsListener`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxUpdateActions.kt) |


### Analysis.xml

[`Analysis.xml`](%gh-ic%/platform/analysis-api/resources/META-INF/Analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.containerProvider"/></include> | [`ContainerProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/ContainerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInspection.InspectionExtension"/></include> | [`InspectionExtensionsFactory`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/lang/InspectionExtensionsFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.contributor"/></include> ![DumbAware][dumb-aware] | [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.skip"/></include> | [`CompletionPreselectSkipper`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionPreselectSkipper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentationProvider"/></include> ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dynamicInspectionsProvider"/></include> ![Internal][internal] | [`DynamicInspectionsProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/ex/dynamic-inspections.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileContextProvider"/></include> ![Project-Level][project-level] | [`FileContextProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileContextProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileLookupInfoProvider"/></include> | [`FileLookupInfoProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/file/FileLookupInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.formatting.caretRestorationDecider"/></include> ![Experimental][experimental] | [`CaretRestorationDecider`](%gh-ic%/platform/analysis-api/src/com/intellij/formatting/CaretRestorationDecider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generatedSourcesFilter"/></include> | [`GeneratedSourcesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/roots/GeneratedSourcesFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.globalInspection"/></include> | [`GlobalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/GlobalInspectionTool.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoDeclarationHandler"/></include> | [`GotoDeclarationHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/navigation/actions/GotoDeclarationHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightErrorFilter"/></include> ![Project-Level][project-level] | [`HighlightErrorFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/highlighting/HighlightErrorFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionCustomComponent"/></include> ![Experimental][experimental] | [`CustomComponentExtension`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/CustomComponentExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionElementsMerger"/></include> | [`InspectionElementsMerger`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/ex/InspectionElementsMerger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionToolProvider"/></include> | [`InspectionToolProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionToolProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionsReportConverter"/></include> | [`InspectionsReportConverter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionsReportConverter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.intentionAction"/></include> | [`CommonIntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/CommonIntentionAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.documentationProvider"/></include> ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.inspectionSuppressor"/></include> ![DumbAware][dumb-aware] | [`InspectionSuppressor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageInjector"/></include> | [`LanguageInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/LanguageInjector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateContext"/></include> | [`TemplateContextType`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/TemplateContextType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateContextProvider"/></include> | [`LiveTemplateContextProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/LiveTemplateContextProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateInternalContext"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateMacro"/></include> | [`Macro`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/template/Macro.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.localFileSystemTimestampEvaluator"/></include> ![Internal][internal] | [`LocalFileSystemTimestampEvaluator`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/impl/local/LocalFileSystemTimestampEvaluator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.localInspection"/></include> ![DumbAware][dumb-aware] | [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.optionController"/></include> ![Experimental][experimental] | [`OptionControllerProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/OptionControllerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.weigher"/></include> | [`Weigher`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/Weigher.java) |

### AnalysisImpl.xml

[`AnalysisImpl.xml`](%gh-ic%/platform/analysis-impl/resources/META-INF/AnalysisImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dataflowIRProvider"/></include> | [`DataFlowIRProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/dataFlow/lang/ir/DataFlowIRProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.elementLookupRenderer"/></include> ![Deprecated][deprecated] ![Removal][removal] | [`ElementLookupRenderer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/lookup/impl/ElementLookupRenderer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateOptionalProcessor"/></include> ![DumbAware][dumb-aware] | [`TemplateOptionalProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplateOptionalProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplatePreprocessor"/></include> | [`TemplatePreprocessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/impl/TemplatePreprocessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.outerLanguageRangePatcher"/></include> | [`OuterLanguageRangePatcher`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/templateLanguages/TemplateDataElementType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.fileReferenceHelper"/></include> | [`FileReferenceHelper`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/FileReferenceHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.resolveScopeEnlarger"/></include> | [`ResolveScopeEnlarger`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/ResolveScopeEnlarger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.resolveScopeProvider"/></include> | [`ResolveScopeProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/ResolveScopeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.severitiesProvider"/></include> ![Non-Dynamic][non-dynamic] | [`SeveritiesProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SeveritiesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.useScopeEnlarger"/></include> | [`UseScopeEnlarger`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/UseScopeEnlarger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.useScopeOptimizer"/></include> | [`ScopeOptimizer`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |

### builtInServer.xml

[`builtInServer.xml`](%gh-ic%/platform/built-in-server/resources/META-INF/builtInServer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.httpRequestHandler"/></include> | [`HttpRequestHandler`](%gh-ic%/platform/platform-util-netty/src/org/jetbrains/ide/HttpRequestHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolboxServiceHandler"/></include> ![Internal][internal] | [`ToolboxServiceHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/ide/ToolboxRestService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.binaryRequestHandler"/></include> ![Non-Dynamic][non-dynamic] | [`BinaryRequestHandler`](%gh-ic%/platform/platform-util-netty/src/org/jetbrains/ide/BinaryRequestHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.customPortServerManager"/></include> ![Non-Dynamic][non-dynamic] | [`CustomPortServerManager`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/ide/CustomPortServerManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.jsonRpcDomain"/></include> ![Internal][internal] | `Object` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.webServerFileHandler"/></include> | [`WebServerFileHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/WebServerFileHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.webServerPathHandler"/></include> | [`WebServerPathHandler`](%gh-ic%/platform/built-in-server/src/org/jetbrains/builtInWebServer/BuiltInWebServer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.webServerRootsProvider"/></include> | [`WebServerRootsProvider`](%gh-ic%/platform/built-in-server-api/src/org/jetbrains/builtInWebServer/WebServerRootsProvider.kt) |

### CodeStyle.xml

[`CodeStyle.xml`](%gh-ic%/platform/code-style-impl/resources/META-INF/CodeStyle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeFormattingDataPreparer"/></include> ![Internal][internal] | [`CodeFormattingDataPreparer`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/codeStyle/CodeFormattingDataPreparer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeStyleSettingsModifier"/></include> ![Experimental][experimental] | [`CodeStyleSettingsModifier`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/modifier/CodeStyleSettingsModifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.disabledIndentRangesProvider"/></include> | [`DisabledIndentRangesProvider`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/DisabledIndentRangesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalFormatProcessor"/></include> ![Experimental][experimental] | [`ExternalFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/ExternalFormatProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileCodeStyleProvider"/></include> | [`FileCodeStyleProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/FileCodeStyleProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileIndentOptionsProvider"/></include> | [`FileIndentOptionsProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/FileIndentOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.formattingService"/></include> | [`FormattingService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/FormattingService.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.formatter"/></include> | [`FormattingModelBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.formatter.newLineIndentMarkerProvider"/></include> | [`NewLineIndentMarkerProvider`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/impl/source/codeStyle/NewLineIndentMarkerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.formatter.restriction"/></include> | [`LanguageFormattingRestriction`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/LanguageFormattingRestriction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.formatter.syntaxErrorsVerifier"/></include> | [`CustomAutoFormatSyntaxErrorsVerifier`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/CustomAutoFormatSyntaxErrorsVerifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.importOptimizer"/></include> | [`ImportOptimizer`](%gh-ic%/platform/code-style-api/src/com/intellij/lang/ImportOptimizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.indentStrategy"/></include> | [`IndentStrategy`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/IndentStrategy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.lineWrapStrategy"/></include> | [`LineWrapPositionStrategy`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/LineWrapPositionStrategy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.rearranger"/></include> | [`Rearranger`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.whiteSpaceFormattingStrategy"/></include> | [`WhiteSpaceFormattingStrategy`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/WhiteSpaceFormattingStrategy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.postFormatProcessor"/></include> | [`PostFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PostFormatProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.postQuickFixTaskService"/></include> ![Internal][internal] | [`PostQuickFixTaskService`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/PostQuickFixTaskService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.preFormatProcessor"/></include> | [`PreFormatProcessor`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/impl/source/codeStyle/PreFormatProcessor.java) |

### com.intellij.platform.images

[`com.intellij.platform.images`](%gh-ic%/images/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.images.themeFilter"/></include> | [`ThemeFilter`](%gh-ic%/images/src/org/intellij/images/thumbnail/actions/ThemeFilter.java) |

### com.intellij.smartUpdate

[`com.intellij.smartUpdate`](%gh-ic%/platform/smart-update/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.smartUpdateStep"/></include> | [`SmartUpdateStep`](%gh-ic%/platform/smart-update/src/com/intellij/smartUpdate/SmartUpdateStep.kt) |

### CompletionExtensionPoints.xml

[`CompletionExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/CompletionExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.wordCompletionFilter"/></include> | [`WordCompletionElementFilter`](%gh-ic%/platform/lang-api/src/com/intellij/lang/WordCompletionElementFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.confidence"/></include> | [`CompletionConfidence`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionConfidence.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.contextFeatures"/></include> ![Internal][internal] | [`ContextFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ContextFeatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.elementFeatures"/></include> ![Internal][internal] | [`ElementFeatureProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/ml/ElementFeatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.model"/></include> ![Internal][internal] | [`RankingModelProvider`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ml/completion/RankingModelProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.plainTextSymbol"/></include> | [`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.preselectionBehaviourProvider"/></include> | [`CompletionPreselectionBehaviourProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/CompletionPreselectionBehaviourProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.createDirectoryCompletionContributor"/></include> | [`CreateDirectoryCompletionContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CreateDirectoryCompletionContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lookup.actionProvider"/></include> | [`LookupActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/LookupActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lookup.charFilter"/></include> | [`CharFilter`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/lookup/CharFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lookup.customizer"/></include> ![Experimental][experimental] ![Internal][internal] | [`LookupCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/ClientLookupManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lookup.usageDetails"/></include> ![Internal][internal] | [`LookupUsageDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupUsageDescriptor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lookup.vetoPolicy"/></include> ![Internal][internal] | [`LookupImplVetoPolicy`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/lookup/impl/LookupImplVetoPolicy.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.templateParameterTraversalPolicy"/></include> | [`TemplateParameterTraversalPolicy`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/TemplateParameterTraversalPolicy.java) |

### Core.xml

[`Core.xml`](%gh-ic%/platform/core-api/resources/META-INF/Core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backgroundPostStartupActivity"/></include> | [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFactoryDocumentListener"/></include> | [`DocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/event/DocumentListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.eelProvider"/></include> ![Internal][internal] | [`EelProvider`](%gh-ic%/platform/eel-provider/src/com/intellij/platform/eel/provider/EelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileIconPatcher"/></include> | [`FileIconPatcher`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconPatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileIconProvider"/></include> | [`FileIconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeDetector"/></include> | [`FileTypeDetector`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeRegistry.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filetype.decompiler"/></include> | [`BinaryFileDecompiler`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/BinaryFileDecompiler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iconLayerProvider"/></include> ![Non-Dynamic][non-dynamic] | [`IconLayerProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconLayerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iconProvider"/></include> ![DumbAware][dumb-aware] | [`IconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.initProjectActivity"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`InitProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspection.basicVisitor"/></include> ![Experimental][experimental] | [`PsiElementVisitor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElementVisitor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.commenter"/></include> | [`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.elementManipulator"/></include> | [`ElementManipulator`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementManipulator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.fileViewProviderFactory"/></include> | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.parserDefinition"/></include> | [`ParserDefinition`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.substitutor"/></include> | [`LanguageSubstitutor`](%gh-ic%/platform/core-api/src/com/intellij/psi/LanguageSubstitutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageBundle"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageInjectionContributor"/></include> | [`LanguageInjectionContributor`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageInjectionPerformer"/></include> | [`LanguageInjectionPerformer`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.metaLanguage"/></include> | [`MetaLanguage`](%gh-ic%/platform/core-api/src/com/intellij/lang/MetaLanguage.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multiHostInjector"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`MultiHostInjector`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multiverse.codeInsightContextPresentationProvider"/></include> | [`CodeInsightContextPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextPresentationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multiverse.codeInsightContextProvider"/></include> | [`CodeInsightContextProvider`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContextProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multiverseEnabler"/></include> ![Non-Dynamic][non-dynamic] | [`MultiverseEnabler`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/multiverse/CodeInsightContext.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pom.declarationSearcher"/></include> | [`PomDeclarationSearcher`](%gh-ic%/platform/core-api/src/com/intellij/pom/PomDeclarationSearcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.postStartupActivity"/></include> | [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.requiredForSmartModeStartupActivity"/></include> | [`RequiredForSmartMode`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stubElementTypeHolder"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vfs.asyncListener"/></include> | [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFileManagerListener"/></include> | [`VirtualFileManagerListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManagerListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFilePreCloseCheck"/></include> ![Non-Dynamic][non-dynamic] ![Experimental][experimental] | [`VirtualFilePreCloseCheck`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFilePreCloseCheck.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.writingAccessProvider"/></include> ![Project-Level][project-level] | [`WritingAccessProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/WritingAccessProvider.java) |

### CoreImpl.xml

[`CoreImpl.xml`](%gh-ic%/platform/core-impl/resources/META-INF/CoreImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.controlFlowProvider"/></include> | [`ControlFlowProvider`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/controlflow/ControlFlowProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diagnostic.freezeProfiler"/></include> ![Internal][internal] | [`FreezeProfiler`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/FreezeProfiler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentWriteAccessGuard"/></include> ![Experimental][experimental] | [`DocumentWriteAccessGuard`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/impl/DocumentWriteAccessGuard.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.elementsToHighlightFilter"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileEditor.fileSizeChecker"/></include> ![Internal][internal] | [`FileSizeLimit`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/limits/FileSizeLimit.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileEditor.textPresentationTransformer"/></include> ![Internal][internal] | [`TextPresentationTransformer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/transformer/TextPresentationTransformer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inlineCompletionLineRendererCustomization"/></include> ![Internal][internal] | [`InlineCompletionInlayRenderer`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/render/InlineCompletionInlayRenderer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.ast.factory"/></include> | [`ASTFactory`](%gh-ic%/platform/core-impl/src/com/intellij/lang/ASTFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.tokenSeparatorGenerator"/></include> | [`TokenSeparatorGenerator`](%gh-ic%/platform/core-api/src/com/intellij/lang/TokenSeparatorGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.treePatcher"/></include> | [`TreePatcher`](%gh-ic%/platform/core-impl/src/com/intellij/psi/templateLanguages/TreePatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.batchReferenceProcessingSuppressor"/></include> ![Experimental][experimental] | [`BatchReferenceProcessingSuppressor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiFileEx.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.implicitReferenceProvider"/></include> | [`ImplicitReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.referenceContributor"/></include> | [`PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.symbolReferenceProvider"/></include> | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.treeChangeListener"/></include> ![Project-Level][project-level] | [`PsiTreeChangeListener`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiTreeChangeListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.treeChangePreprocessor"/></include> ![Project-Level][project-level] | [`PsiTreeChangePreprocessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/PsiTreeChangePreprocessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.smartPointer.anchorProvider"/></include> | [`SmartPointerAnchorProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/smartPointers/SmartPointerAnchorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.treeCopyHandler"/></include> | [`TreeCopyHandler`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeCopyHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFileSystem"/></include> | [`VirtualFileSystem`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileSystem.java) |

### DomPlugin.xml

[`DomPlugin.xml`](%gh-ic%/xml/dom-impl/resources/META-INF/DomPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.converter"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.customAnnotationChecker"/></include> | [`DomCustomAnnotationChecker`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomCustomAnnotationChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.extender"/></include> | [`DomExtender`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtender.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.fileDescription"/></include> ![Deprecated][deprecated] | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.fileMetaData"/></include> | [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.gotoSuper"/></include> | [`DomElementNavigationProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementNavigationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.implementation"/></include> | [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.uiControlsProvider"/></include> ![Obsolete][obsolete] ![Non-Dynamic][non-dynamic] | [`Consumer`](%gh-ic%/platform/util/src/com/intellij/util/Consumer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleContextProvider"/></include> | [`ModuleContextProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ModuleContextProvider.java) |

### duplicates-analysis.xml

[`duplicates-analysis.xml`](%gh-ic%/platform/duplicates-analysis/resources/META-INF/duplicates-analysis.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.equivalenceDescriptorProvider"/></include> | [`EquivalenceDescriptorProvider`](%gh-ic%/platform/duplicates-analysis/src/com/intellij/dupLocator/equivalence/EquivalenceDescriptorProvider.java) |

### Editor.xml

[`Editor.xml`](%gh-ic%/platform/editor-ui-api/resources/META-INF/Editor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFactoryListener"/></include> | [`EditorFactoryListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorFactoryListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.mergeableGutterIconRendererProvider"/></include> ![Internal][internal] | [`MergeableGutterIconRendererProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/MergeableGutterIconRendererProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.syntaxHighlighter"/></include> | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |

### EditorExtensionPoints.xml

[`EditorExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/EditorExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backspaceHandlerDelegate"/></include> | [`BackspaceHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.basicWordSelectionFilter"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bidiRegionsSeparator"/></include> | [`BidiRegionsSeparator`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/bidi/BidiRegionsSeparator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeBlockProvider"/></include> | [`CodeBlockProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CodeBlockProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.fillParagraph"/></include> | [`ParagraphFillHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/fillParagraph/ParagraphFillHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commentCompleteHandler"/></include> | [`CommentCompleteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CommentCompleteHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.copyPastePostProcessor"/></include> | [`CopyPastePostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/CopyPastePostProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.copyPastePreProcessor"/></include> | [`CopyPastePreProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyPastePreProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customPasteProvider"/></include> | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editor.backspaceModeOverride"/></include> | [`BackspaceModeOverride`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/BackspaceModeOverride.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.enterBetweenBracesDelegate"/></include> | [`EnterBetweenBracesDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterBetweenBracesDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.enterHandlerDelegate"/></include> | [`EnterHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.extendWordSelectionHandler"/></include> | [`ExtendWordSelectionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.flipCommaIntention.flipper"/></include> | [`Flipper`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/FlipCommaIntention.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalEditorOptionsCustomizer"/></include> ![Internal][internal] | [`EditorOptionsPageCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/EditorOptionsPageCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalEditorOptionsExtension"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.editorTypeResolver"/></include> ![Internal][internal] | [`InlineCompletionEditorTypeResolver`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/editor/InlineCompletionEditorType.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.element.manipulator"/></include> ![Experimental][experimental] | [`InlineCompletionElementManipulator`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/elements/InlineCompletionElementManipulator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.handlerInitializer"/></include> ![Internal][internal] | [`InlineCompletionHandlerInitializer`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionHandlerInitializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.partial.accept.handler"/></include> ![Experimental][experimental] ![Internal][internal] | [`InlineCompletionPartialAcceptHandler`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/suggestion/InlineCompletionPartialAcceptHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.provider"/></include> | [`InlineCompletionProvider`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/InlineCompletionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.quoteHandlerEx"/></include> ![Experimental][experimental] ![Internal][internal] | [`InlineCompletionQuoteHandlerEx`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/inline/completion/suggestion/InlineCompletionQuoteHandlerEx.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.session.logs"/></include> ![Internal][internal] | [`InlineCompletionSessionLogsEP`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/logs/InlineCompletionSessionLogsEP.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.usage.data"/></include> ![Internal][internal] | [`InlineCompletionProviderSpecificUsageData`](%gh-ic%/platform/platform-impl/codeinsight-inline/src/com/intellij/codeInsight/inline/completion/logs/InlineCompletionProviderSpecificUsageData.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.joinLinesHandler"/></include> | [`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.emacs"/></include> | [`EmacsProcessingHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/emacs/EmacsProcessingHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.quoteHandler"/></include> | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.smartEnterProcessor"/></include> | [`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.listSplitJoinContext"/></include> ![Experimental][experimental] | [`ListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moveLeftRightHandler"/></include> | [`MoveElementLeftRightHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nonWriteAccessTypedHandler"/></include> ![Experimental][experimental] ![Internal][internal] | [`NonWriteAccessTypedHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/NonWriteAccessTypedHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.preserveIndentOnPaste"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.quoteHandler"/></include> | [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.selectionUnquotingFilter"/></include> | [`UnquotingFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectionQuotingTypedHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statementUpDownMover"/></include> | [`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typedHandler"/></include> | [`TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typingActionsExtension"/></include> ![Experimental][experimental] | [`TypingActionsExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/TypingActionsExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.wordBoundaryFilter"/></include> | [`WordBoundaryFilter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/actions/WordBoundaryFilter.java) |

### FormatterExtensionPoints.xml

[`FormatterExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/FormatterExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentMerger"/></include> | [`DocumentMerger`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/service/DocumentMerger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileSetDescriptorFactory"/></include> | [`FileSetDescriptorFactory`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/fileSet/FileSetDescriptorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeIndentOptionsProvider"/></include> | [`FileTypeIndentOptionsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.formatOnSaveOptions.defaultsProvider"/></include> | [`DefaultsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/onSave/FormatOnSaveOptionsBase.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.formatting.injectedOptions"/></include> | [`InjectedFormattingOptionsProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/InjectedFormattingOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.formatting.extractor"/></include> | [`LangCodeStyleExtractor`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/extractor/differ/LangCodeStyleExtractor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.langCodeStyleSettingsContributor"/></include> ![Internal][internal] | [`LanguageCodeStyleSettingsContributor`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.langCodeStyleSettingsProvider"/></include> | [`LanguageCodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lineIndentProvider"/></include> | [`LineIndentProvider`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/lineIndent/LineIndentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.predefinedCodeStyle"/></include> | [`PredefinedCodeStyle`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/PredefinedCodeStyle.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rearranger.ui"/></include> | [`Factory`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/std/ArrangementUiComponent.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.resultHandlerProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`IncorrectFormattingResultHandlerProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/incorrectFormatting/IncorrectFormattingResultHandlerProvider.kt) |

### IdeCore.xml

[`IdeCore.xml`](%gh-ic%/platform/ide-core-impl/resources/META-INF/IdeCore.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.applicationActivity"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationActivity`](%gh-ic%/platform/ide-core/src/com/intellij/ide/ApplicationActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.applicationInitializedListener"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationInitializedListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/ApplicationInitializedListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.notificationGroup"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.registry.managed"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ManagedRegistry`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/util/registry/ManagedRegistry.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.registryKey"/></include> | `n/a` |

### IJent.xml

[`IJent.xml`](%gh-ic%/platform/ijent/resources/META-INF/IJent.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ijent.deploymentListener"/></include> | [`IjentDeploymentListener`](%gh-ic%/platform/ijent/src/com/intellij/platform/ijent/spi/IjentDeployingOverShellProcessStrategy.kt) |

### Indexing.xml

[`Indexing.xml`](%gh-ic%/platform/indexing-api/resources/META-INF/Indexing.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.binaryFileSourceProvider"/></include> ![Internal][internal] | [`BinaryFileSourceProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/platform/indexing/BinaryFileSourceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeUsageScopeOptimizer"/></include> | [`ScopeOptimizer`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/ScopeOptimizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.definitionsSearch"/></include> ![Deprecated][deprecated] | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dumbServiceInitializationCondition"/></include> ![Internal][internal] | [`DumbServiceInitializationCondition`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/DumbServiceInitializationCondition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileBasedIndex"/></include> | [`FileBasedIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileBasedIndexInfrastructureExtension"/></include> ![Internal][internal] | [`FileBasedIndexInfrastructureExtension`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/FileBasedIndexInfrastructureExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileBasedIndexLayout"/></include> ![Internal][internal] | [`FileBasedIndexLayoutProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/indexing/storage/FileBasedIndexLayoutProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.findModelExtension"/></include> ![Internal][internal] | [`FindModelExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/find/FindModelExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexableFilesContributor"/></include> ![Deprecated][deprecated] ![Removal][removal] | [`IndexableFilesContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFilesContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexedRootsProvider"/></include> | [`IndexableSetContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/IndexableSetContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexingFlavor"/></include> ![Experimental][experimental] ![Internal][internal] | [`FileIndexingFlavorProvider`](%gh-ic%/platform/core-api/src/com/intellij/util/indexing/flavor/FileIndexingFlavorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageStubDefinition"/></include> ![Experimental][experimental] | [`LanguageStubDefinition`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/LanguageStubDefinition.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectIndexingActivityHistoryListener"/></include> | [`ProjectIndexingActivityHistoryListener`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/diagnostic/ProjectIndexingHistory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.referencesSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stubElementRegistryExtension"/></include> ![Experimental][experimental] | [`StubRegistryExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubRegistryExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stubIndex"/></include> | [`StubIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubIndexExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.trigramIndexFilterExcludeExtension"/></include> ![Internal][internal] | [`TrigramIndexFilterExcludeExtension`](%gh-ic%/platform/indexing-impl/src/com/intellij/find/ngrams/TrigramIndexFilter.kt) |

### Inspect.xml

[`Inspect.xml`](%gh-ic%/platform/inspect/resources/META-INF/Inspect.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectResultsConsumer"/></include> ![Internal][internal] | [`InspectResultsConsumer`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/InspectResultsConsumer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionGroupProvider"/></include> | [`InspectionGroupProvider`](%gh-ic%/platform/inspect/src/com/intellij/codeInspection/inspectionProfile/InspectionGroupProvider.kt) |

### intellij.grid.core.impl.xml

[`intellij.grid.core.impl.xml`](%gh-ic%/grid/core-impl/resources/intellij.grid.core.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.datagrid.extractorsHelper"/></include> | [`ExtractorsHelper`](%gh-ic%/grid/core-impl/src/extractors/ExtractorsHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.datagrid.formatterCreatorProvider"/></include> | [`FormatterCreatorProvider`](%gh-ic%/grid/core-impl/src/datagrid/FormatterCreatorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.datagrid.objectNormalizerProvider"/></include> | [`ObjectNormalizerProvider`](%gh-ic%/grid/core-impl/src/datagrid/ObjectNormalizerProvider.java) |

### intellij.grid.impl.xml

[`intellij.grid.impl.xml`](%gh-ic%/grid/impl/resources/intellij.grid.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.datagrid.cellViewerFactory"/></include> | [`CellViewerFactory`](%gh-ic%/grid/impl/src/run/ui/CellViewer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.datagrid.valueEditorTab"/></include> | [`ValueEditorTab`](%gh-ic%/grid/impl/src/run/ui/ValueEditorTab.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.minimizedFormatDetector"/></include> | [`MinimizedFormatDetector`](%gh-ic%/grid/impl/src/run/ui/MinimizedFormatDetector.java) |

### intellij.notebooks.visualization.xml

[`intellij.notebooks.visualization.xml`](%gh-ic%/notebooks/visualization/resources/intellij.notebooks.visualization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.editor.notebookEditorAppearanceProvider"/></include> | [`NotebookEditorAppearanceProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookEditorAppearanceProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentFactory"/></include> | [`NotebookOutputComponentFactory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputComponentFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputComponentWrapper"/></include> | [`NotebookOutputComponentWrapper`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputComponentWrapper.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.editor.outputs.notebookOutputDataKeyExtractor"/></include> | [`NotebookOutputDataKeyExtractor`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/outputs/NotebookOutputDataKeyExtractor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.inputFactory"/></include> | [`InputFactory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellInlayController.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.notebookCellInlayController"/></include> | [`Factory`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellInlayController.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.notebookCellLinesProvider"/></include> | [`NotebookCellLinesProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellLinesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.notebookCellSelectionModelProvider"/></include> | [`NotebookCellSelectionModelProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookCellSelectionModelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.notebooks.notebookIntervalPointerFactoryProvider"/></include> | [`NotebookIntervalPointerFactoryProvider`](%gh-ic%/notebooks/visualization/src/com/intellij/notebooks/visualization/NotebookIntervalPointerFactoryProvider.kt) |

### intellij.platform.execution.dashboard.xml

[`intellij.platform.execution.dashboard.xml`](%gh-ic%/platform/execution.dashboard/resources/intellij.platform.execution.dashboard.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runDashboardChecker"/></include> ![Experimental][experimental] | [`RunDashboardChecker`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardChecker.kt) |

### intellij.platform.experiment.xml

[`intellij.platform.experiment.xml`](%gh-ic%/platform/experiment/resources/intellij.platform.experiment.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.experiment.abExperimentOption"/></include> | [`ABExperimentOption`](%gh-ic%/platform/experiment/src/com/intellij/platform/experiment/ab/impl/experiment/ABExperimentOption.kt) |

### intellij.platform.feedback.xml

[`intellij.platform.feedback.xml`](%gh-ic%/platform/feedback/resources/intellij.platform.feedback.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.feedback.idleFeedbackSurvey"/></include> | [`FeedbackSurvey`](%gh-ic%/platform/feedback/src/com/intellij/platform/feedback/FeedbackSurvey.kt) |

### intellij.platform.ide.newUiOnboarding.xml

[`intellij.platform.ide.newUiOnboarding.xml`](%gh-ic%/platform/new-ui-onboarding/resources/intellij.platform.ide.newUiOnboarding.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ide.newUiOnboarding"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ide.newUiOnboarding.step"/></include> ![Internal][internal] | [`NewUiOnboardingStep`](%gh-ic%/platform/new-ui-onboarding/src/com/intellij/platform/ide/newUiOnboarding/NewUiOnboardingStep.kt) |

### intellij.platform.inline.completion.xml

[`intellij.platform.inline.completion.xml`](%gh-ic%/platform/inline-completion/shared/resources/intellij.platform.inline.completion.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inline.completion.shortcutHintListener"/></include> ![Internal][internal] | [`InlineCompletionShortcutHintListener`](%gh-ic%/platform/inline-completion/shared/src/shortcut/InlineCompletionShortcutHintListener.kt) |

### intellij.platform.kernel.xml

[`intellij.platform.kernel.xml`](%gh-ic%/platform/kernel/shared/resources/intellij.platform.kernel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.entityTypes"/></include> | [`EntityTypeProvider`](%gh-ic%/platform/kernel/shared/src/EntityTypeProvider.kt) |

### intellij.platform.navbar.backend.xml

[`intellij.platform.navbar.backend.xml`](%gh-ic%/platform/navbar/backend/resources/intellij.platform.navbar.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.navbar.item.provider"/></include> | [`NavBarItemProvider`](%gh-ic%/platform/navbar/backend/src/NavBarItemProvider.kt) |

### intellij.platform.project.xml

[`intellij.platform.project.xml`](%gh-ic%/platform/project/shared/resources/intellij.platform.project.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.project.projectIdResolver"/></include> ![Internal][internal] | [`ProjectIdResolver`](%gh-ic%/platform/project/shared/src/ProjectId.kt) |

### intellij.platform.remoteServers.impl.xml

[`intellij.platform.remoteServers.impl.xml`](%gh-ic%/platform/remote-servers/impl/resources/intellij.platform.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.defaultConfigurable.includeServerType"/></include> | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.deploymentConfiguration.stateProvider"/></include> | [`DeployToServerStateProvider`](%gh-ic%/platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerStateProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.deploymentSource.type"/></include> | [`DeploymentSourceType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/configuration/deployment/DeploymentSourceType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.runConfigurationExtension"/></include> | [`DeployToServerRunConfigurationExtension`](%gh-ic%/platform/remote-servers/impl/src/com/intellij/remoteServer/impl/configuration/deployment/DeployToServerRunConfigurationExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.type"/></include> | [`ServerType`](%gh-ic%/platform/remote-servers/api/src/com/intellij/remoteServer/ServerType.java) |

### intellij.platform.rpc.backend.xml

[`intellij.platform.rpc.backend.xml`](%gh-ic%/platform/kernel/rpc.backend/resources/intellij.platform.rpc.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.rpc.backend.remoteApiProvider"/></include> | [`RemoteApiProvider`](%gh-ic%/platform/kernel/rpc.backend/src/RemoteApiProvider.kt) |

### intellij.platform.searchEverywhere.xml

[`intellij.platform.searchEverywhere.xml`](%gh-ic%/platform/searchEverywhere/shared/resources/intellij.platform.searchEverywhere.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhere.itemsProviderFactory"/></include> ![Experimental][experimental] ![Internal][internal] | [`SeItemsProviderFactory`](%gh-ic%/platform/searchEverywhere/shared/src/api/SeItemsProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhere.tabProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`SeTabProvider`](%gh-ic%/platform/searchEverywhere/shared/src/api/SeTabProvider.kt) |

### intellij.platform.settings.local.xml

[`intellij.platform.settings.local.xml`](%gh-ic%/platform/settings-local/resources/intellij.platform.settings.local.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsController"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`DelegatedSettingsController`](%gh-ic%/platform/settings/src/com/intellij/platform/settings/SettingsController.kt) |

### intellij.platform.statistics.devkit.xml

[`intellij.platform.statistics.devkit.xml`](%gh-ic%/platform/statistics/devkit/resources/intellij.platform.statistics.devkit.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.internal.statistic.devkit.toolwindow.logGroupActionsProvider"/></include> ![Internal][internal] | [`StatisticsLogGroupActionsProvider`](%gh-ic%/platform/statistics/devkit/src/com/intellij/internal/statistic/devkit/toolwindow/StatisticsLogGroupActionsProvider.kt) |

### intellij.platform.syntax.psi.xml

[`intellij.platform.syntax.psi.xml`](%gh-ic%/platform/syntax/syntax-psi/resources/META-INF/intellij.platform.syntax.psi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.syntax.elementTypeConverter"/></include> | [`ElementTypeConverter`](%gh-ic%/platform/syntax/syntax-psi/src/com/intellij/platform/syntax/psi/ElementTypeConverter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.syntax.syntaxDefinition"/></include> | [`LanguageSyntaxDefinition`](%gh-ic%/platform/syntax/syntax-psi/src/com/intellij/platform/syntax/psi/LanguageSyntaxDefinition.kt) |

### intellij.platform.tips.xml

[`intellij.platform.tips.xml`](%gh-ic%/platform/tips-of-the-day/resources/intellij.platform.tips.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tipAndTrickPromotionFactory"/></include> ![Internal][internal] | [`TipAndTrickPromotionFactory`](%gh-ic%/platform/tips-of-the-day/src/com/intellij/ide/util/TipAndTrickPromotionFactory.kt) |

### intellij.settingsSync.core.xml

[`intellij.settingsSync.core.xml`](%gh-ic%/platform/settings-sync-core/resources/intellij.settingsSync.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsSync.communicatorProvider"/></include> | [`SettingsSyncCommunicatorProvider`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/communicator/SettingsSyncCommunicatorProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsSync.settingsProvider"/></include> ![Internal][internal] | [`SettingsProvider`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/SettingsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsSyncMigration"/></include> ![Internal][internal] | [`SettingsSyncMigration`](%gh-ic%/platform/settings-sync-core/src/com/intellij/settingsSync/core/SettingsSyncMigration.kt) |

### LangExtensionPoints.xml

[`LangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.aliasingPsiTargetMapper"/></include> | [`AliasingPsiTargetMapper`](%gh-ic%/platform/core-api/src/com/intellij/psi/targets/AliasingPsiTargetMapper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.analyzeStacktraceFilter"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`Filter`](%gh-ic%/platform/execution/src/com/intellij/execution/filters/Filter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.analyzeStacktraceRunContentProvider"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`StacktraceTabContentProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/unscramble/StacktraceTabContentProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.anchorReferenceProvider"/></include> | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotator"/></include> ![DumbAware][dumb-aware] | [`Annotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.anonymousElementProvider"/></include> | [`AnonymousElementProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/navigation/AnonymousElementProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.autoImportOptionsProvider"/></include> ![Project-Level][project-level] | [`AutoImportOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/AutoImportOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bookmarkProvider"/></include> ![Project-Level][project-level] | [`BookmarkProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarkProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bookmarksListProvider"/></include> ![Project-Level][project-level] | [`BookmarksListProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/bookmark/BookmarksListProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.braceMatcher"/></include> | [`BraceMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/BraceMatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.breadcrumbsInfoProvider"/></include> | [`BreadcrumbsProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cacheBuilder"/></include> ![Internal][internal] | [`WordsScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.callHierarchyProvider"/></include> | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cantBeStatic"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeBlockSupportHandler"/></include> | [`CodeBlockSupportHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeCompletionConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeFoldingOptionsProvider"/></include> | [`CodeFoldingOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/CodeFoldingOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.codeVision.settings.defaults"/></include> ![Experimental][experimental] | [`CodeVisionSettingsDefaults`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/codeVision/settings/CodeVisionSettingsDefaults.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.codeVisionProvider"/></include> ![Experimental][experimental] | [`CodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.codeVisionProviderFactory"/></include> | [`CodeVisionProviderFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.codeVisionSettingsPreviewLanguage"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.completion.applicable.command"/></include> ![DumbAware][dumb-aware] | [`ApplicableCompletionCommand`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CompletionCommand.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.completion.command.factory"/></include> ![DumbAware][dumb-aware] | [`CommandCompletionFactory`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CommandCompletionFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.completion.command.provider"/></include> ![DumbAware][dumb-aware] | [`CommandProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/command/CommandProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.completion.intention.skipper"/></include> | [`IntentionCommandSkipper`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/completion/command/commands/DirectIntentionCommandProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.daemon.impl.injectedLanguageHighlightingRangeReducer"/></include> ![Non-Dynamic][non-dynamic] ![Experimental][experimental] | [`InjectedLanguageHighlightingRangeReducer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/InjectedLanguageHighlightingRangeReducer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.daemonBoundCodeVisionProvider"/></include> | [`DaemonBoundCodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hints/codeVision/DaemonBoundCodeVisionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProvider"/></include> ![DumbAware][dumb-aware] | [`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProviderCustomSettingsProvider"/></include> | [`InlayHintsCustomSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsCustomSettingsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProviderFactory"/></include> | [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.delegateMethods"/></include> | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.gotoSuper"/></include> | [`CodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/CodeInsightActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.implementMethod"/></include> | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.inlayActionHandler"/></include> | [`InlayActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayActionHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.inlayHintsSwitch"/></include> | [`InlayHintsSwitch`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsSwitch.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.inlayProvider"/></include> | [`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.inlayProviderFactory"/></include> ![DumbAware][dumb-aware] | [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.lineMarkerProvider"/></include> ![DumbAware][dumb-aware] | [`LineMarkerProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.overrideMethod"/></include> | [`LanguageCodeInsightActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/LanguageCodeInsightActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.parameterInfo"/></include> ![DumbAware][dumb-aware] | [`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.parameterInfo.controller.provider"/></include> | [`ParameterInfoControllerProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoControllerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.parameterInfo.listener"/></include> | [`ParameterInfoListener`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.parameterNameHints"/></include> | [`InlayParameterHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.parameterNameHintsSuppressor"/></include> | [`ParameterNameHintsSuppressor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.surroundWithRangeAdjuster"/></include> | [`SurroundWithRangeAdjuster`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/generation/surroundWith/SurroundWithRangeAdjuster.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.typeInfo"/></include> ![DumbAware][dumb-aware] | [`ExpressionTypeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/lang/ExpressionTypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.unresolvedReferenceQuickFixProvider"/></include> | [`UnresolvedReferenceQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/quickfix/UnresolvedReferenceQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeStyleSettingsProvider"/></include> | [`CodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeVisionPainterProvider"/></include> ![Non-Dynamic][non-dynamic] | [`ICodeVisionEntryBasePainter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/ui/renderers/painters/ICodeVisionEntryBasePainter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorAndFontDescriptorProvider"/></include> | [`ColorAndFontDescriptorsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorAndFontDescriptorsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorAndFontPanelFactory"/></include> | [`ColorAndFontPanelFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ColorAndFontPanelFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorProvider"/></include> | [`ElementColorProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorSettingsPage"/></include> | [`ColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commandLineInspectionProjectConfigurator"/></include> ![Obsolete][obsolete] | [`CommandLineInspectionProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/CommandLineInspectionProjectConfigurator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commentTokenSetProvider"/></include> | [`CommentTokenSetProvider`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/cache/CommentTokenSetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.concatenationAwareInjector"/></include> ![Project-Level][project-level] | [`ConcatenationAwareInjector`](%gh-ic%/platform/lang-api/src/com/intellij/lang/injection/ConcatenationAwareInjector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.configurationProducer"/></include> ![Deprecated][deprecated] ![DumbAware][dumb-aware] | [`RuntimeConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/junit/RuntimeConfigurationProducer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.configurationType"/></include> ![DumbAware][dumb-aware] | [`ConfigurationType`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.console.folding"/></include> | [`ConsoleFolding`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ConsoleFolding.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.consoleActionsPostProcessor"/></include> | [`ConsoleActionsPostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/ConsoleActionsPostProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.consoleConfigurableProvider"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.consoleFilterProvider"/></include> | [`ConsoleFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleFilterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.consoleHistoryModelProvider"/></include> | [`ConsoleHistoryModelProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleHistoryModelProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.consoleInputFilterProvider"/></include> | [`ConsoleInputFilterProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/filters/ConsoleInputFilterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.contributedReferencesAnnotator"/></include> | [`ContributedReferencesAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ContributedReferencesAnnotator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.createFromTemplateActionReplacer"/></include> | [`CreateFromTemplateActionReplacer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateActionReplacer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.createFromTemplateHandler"/></include> | [`CreateFromTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customFoldingProvider"/></include> | [`CustomFoldingProvider`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/CustomFoldingProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customLiveTemplate"/></include> | [`CustomLiveTemplate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/CustomLiveTemplate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customScopesFilter"/></include> | [`CustomScopesFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customScopesProvider"/></include> ![Project-Level][project-level] | [`CustomScopesProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/search/scope/packageSet/CustomScopesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customUsageSearcher"/></include> | [`CustomUsageSearcher`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/CustomUsageSearcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.changeLocalityDetector"/></include> | [`ChangeLocalityDetector`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ChangeLocalityDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.essentialHighlightingRestarterDisablement"/></include> | [`EssentialHighlightingRestarterDisablement`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/EssentialHighlightingRestarterDisablement.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.externalAnnotatorsFilter"/></include> | [`ExternalAnnotatorsFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.highlightInfoFilter"/></include> | [`HighlightInfoFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.indentsPassFilter"/></include> | [`IndentsPassFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IndentsPassFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.intentionActionFilter"/></include> | [`IntentionActionFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/IntentionActionFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.statusItemMerger"/></include> | [`StatusItemMerger`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/StatusItemMerger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.daemon.tooltipActionProvider"/></include> | [`TooltipActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/tooltips/TooltipActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.declarationRangeHandler"/></include> | [`DeclarationRangeHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defaultHighlightingSettingProvider"/></include> ![DumbAware][dumb-aware] | [`DefaultHighlightingSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/impl/analysis/DefaultHighlightingSettingProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defaultLiveTemplates"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defaultLiveTemplatesProvider"/></include> ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`DefaultLiveTemplatesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defaultTemplatePropertiesProvider"/></include> | [`DefaultTemplatePropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/DefaultTemplatePropertiesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.definitionsScopedSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diffPreviewProvider"/></include> ![Non-Dynamic][non-dynamic] | [`DiffPreviewProvider`](%gh-ic%/platform/diff-api/src/com/intellij/openapi/diff/impl/settings/DiffPreviewProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentation.documentationDownloader"/></include> ![Experimental][experimental] | [`DocumentationDownloader`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/actions/DocumentationDownloader.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dynamicContextProvider"/></include> | [`DynamicContextProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/paths/DynamicContextProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorAppearanceConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorOptionsProvider"/></include> | [`EditorOptionsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorSearchAreaProvider"/></include> ![Experimental][experimental] | [`EditorSearchAreaProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/livePreview/EditorSearchAreaProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorSmartKeysConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorTabsConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.elementDescriptionProvider"/></include> | [`ElementDescriptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.elementPreviewProvider"/></include> | [`ElementPreviewProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/preview/ElementPreviewProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.elementSignatureProvider"/></include> | [`ElementSignatureProvider`](%gh-ic%/platform/foldings/src/com/intellij/codeInsight/folding/impl/ElementSignatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.environmentKeyProvider"/></include> ![Experimental][experimental] | [`EnvironmentKeyProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/environment/EnvironmentKeyProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.errorOptionsProvider"/></include> | [`ErrorOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/ErrorOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.errorQuickFixProvider"/></include> ![DumbAware][dumb-aware] | [`ErrorQuickFixProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/ErrorQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.executionTargetLanguageRuntimeType"/></include> | [`LanguageRuntimeType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/LanguageRuntimeType.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.executionTargetProvider"/></include> | [`ExecutionTargetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/ExecutionTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.executionTargetType"/></include> | [`TargetEnvironmentType`](%gh-ic%/platform/execution/src/com/intellij/execution/target/TargetEnvironmentType.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.executor"/></include> | [`Executor`](%gh-ic%/platform/execution/src/com/intellij/execution/Executor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalAnnotator"/></include> ![DumbAware][dumb-aware] | [`ExternalAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.facet.toolWindow"/></include> ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.facetType"/></include> | [`FacetType`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.favoriteNodeProvider"/></include> ![Deprecated][deprecated] ![Removal][removal] ![Project-Level][project-level] | [`FavoriteNodeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/favoritesTreeView/FavoriteNodeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.favoritesListProvider"/></include> ![Deprecated][deprecated] ![Internal][internal] ![Project-Level][project-level] | [`FavoritesListProvider`](%gh-ic%/platform/favoritesTreeView/src/com/intellij/ide/favoritesTreeView/FavoritesListProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePasteProvider"/></include> | [`PasteProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/PasteProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileStructureGroupRuleProvider"/></include> | [`FileStructureGroupRuleProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/FileStructureGroupRuleProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTemplateGroup"/></include> | [`FileTemplateGroupDescriptorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileType.fileViewProviderFactory"/></include> | [`FileViewProviderFactory`](%gh-ic%/platform/core-api/src/com/intellij/psi/FileViewProviderFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeStatisticProvider"/></include> ![Deprecated][deprecated] | [`FileTypeStatisticProvider`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/statistic/fileTypes/FileTypeStatisticProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filetype.prebuiltStubsProvider"/></include> ![Deprecated][deprecated] ![Internal][internal] | [`PrebuiltStubsProvider`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/stubs/PrebuiltStubs.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filetype.stubBuilder"/></include> | [`BinaryFileStubBuilder`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/BinaryFileStubBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.findInProjectExtension"/></include> ![Internal][internal] | [`FindInProjectExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/FindInProjectExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.findUsagesHandlerFactory"/></include> ![Project-Level][project-level] | [`FindUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/findUsages/FindUsagesHandlerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.focusModeProvider"/></include> ![Experimental][experimental] | [`FocusModeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/focusMode/FocusModeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.framework.detector"/></include> | [`FrameworkDetector`](%gh-ic%/platform/lang-api/src/com/intellij/framework/detection/FrameworkDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalCodeStyleOptionsProvider"/></include> | [`GeneralCodeStyleOptionsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/GeneralCodeStyleOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.globalIndexFilter"/></include> ![Internal][internal] | [`GlobalIndexFilter`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/GlobalIndexFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.goto.nonProjectScopeDisabler"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoActionAliasMatcher"/></include> | [`GotoActionAliasMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoActionAliasMatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoClassContributor"/></include> | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoFileContributor"/></include> | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoFileCustomizer"/></include> | [`GotoFileCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/gotoByName/GotoFileCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoRelatedProvider"/></include> | [`GotoRelatedProvider`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/GotoRelatedProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoSymbolContributor"/></include> | [`ChooseByNameContributor`](%gh-ic%/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoTargetPresentationProvider"/></include> | [`GotoTargetPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetPresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoTargetRendererProvider"/></include> ![Deprecated][deprecated] | [`GotoTargetRendererProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/GotoTargetRendererProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.heavyBracesHighlighter"/></include> ![Non-Dynamic][non-dynamic] | [`HeavyBraceHighlighter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HeavyBraceHighlighter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.hectorComponentProvider"/></include> ![Project-Level][project-level] | [`HectorComponentPanelsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/HectorComponentPanelsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightInfoPostFilter"/></include> ![Project-Level][project-level] | [`HighlightInfoPostFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoPostFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightRangeExtension"/></include> | [`HighlightRangeExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightRangeExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightUsagesHandlerFactory"/></include> ![DumbAware][dumb-aware] | [`HighlightUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightVisitor"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`HighlightVisitor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightVisitor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightingPassFactory"/></include> | [`TextEditorHighlightingPassFactoryRegistrar`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeHighlighting/TextEditorHighlightingPassFactoryRegistrar.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.idIndexer"/></include> | [`IdIndexer`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/id/IdIndexer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implementationViewDocumentFactory"/></include> | [`ImplementationViewDocumentFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewDocumentFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implementationViewSessionFactory"/></include> | [`ImplementationViewSessionFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ImplementationViewSession.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implicitUsageProvider"/></include> | [`ImplicitUsageProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ImplicitUsageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.importBlockRangeProvider"/></include> | [`ImportBlockRangeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/lang/imports/ImportBlockRangeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.importFilteringRule"/></include> | [`ImportFilteringRule`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/ImportFilteringRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.include.provider"/></include> | [`FileIncludeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexPatternBuilder"/></include> | [`IndexPatternBuilder`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/search/IndexPatternBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexPatternProvider"/></include> ![Non-Dynamic][non-dynamic] | [`IndexPatternProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/IndexPatternProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexPatternSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexableEntityProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`IndexableEntityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/IndexableEntityProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.indexableIteratorBuilderHandler"/></include> | [`IndexableIteratorBuilderHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/util/indexing/roots/builders/IndexableIteratorBuilderHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inlineCompletionConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inlinePrompt"/></include> ![Internal][internal] | [`InlinePromptExtension`](%gh-ic%/platform/analysis-api/src/com/intellij/inlinePrompt/InlinePromptExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionProfileActionProvider"/></include> | [`InspectionProfileActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionProfileActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionResultsExportActionProvider"/></include> ![DumbAware][dumb-aware] | [`InspectionResultsExportActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/ui/actions/InspectionResultsExportActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionTreeAdvertiser"/></include> | [`InspectionTreeAdvertiser`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/InspectionTreeAdvertiser.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.intentionMenuContributor"/></include> ![Internal][internal] | [`IntentionMenuContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/IntentionMenuContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.intentionPopupProvider"/></include> | [`IntentionPopupProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionPopupProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.intentionsOrderProvider"/></include> | [`IntentionsOrderProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/IntentionsOrderProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.internalFileTemplate"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.internalHighlightingLayerSupplier"/></include> ![Experimental][experimental] ![Internal][internal] | [`InternalLayerSupplier`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/InternalLayerSupplier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.braceMatcher"/></include> | [`PairedBraceMatcher`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.directNavigationProvider"/></include> ![Experimental][experimental] | [`DirectNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.documentation.syntaxHighlightingHandlerFactory"/></include> ![Internal][internal] | [`QuickDocSyntaxHighlightingHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/QuickDocSyntaxHighlightingHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.documentationFixer"/></include> | [`DocCommentFixer`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocCommentFixer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.documentationToolWindowManager"/></include> ![Deprecated][deprecated] | [`DocToolWindowManager`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocToolWindowManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.findUsagesProvider"/></include> | [`FindUsagesProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.floatingToolbar"/></include> ![Experimental][experimental] | [`FloatingToolbarCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/codeFloatingToolbar/FloatingToolbarCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.floatingToolbarCustomizer"/></include> ![Deprecated][deprecated] ![Experimental][experimental] | [`FloatingToolbarCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/codeFloatingToolbar/FloatingToolbarCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.foldingBuilder"/></include> ![DumbAware][dumb-aware] | [`FoldingBuilder`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.implementationTextProcessor"/></include> | [`ImplementationTextProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.implementationTextSelectioner"/></include> | [`ImplementationTextSelectioner`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/ImplementationTextSelectioner.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.logicalStructureElementsProvider"/></include> ![Experimental][experimental] | [`LogicalStructureElementsProvider`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/logical/LogicalStructureElementsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.logicalStructureTreeElementProvider"/></include> ![Experimental][experimental] | [`LogicalStructureTreeElementProvider`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/logical/LogicalStructureTreeElementProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.psiElementExternalizer"/></include> | [`PsiElementExternalizer`](%gh-ic%/platform/lang-api/src/com/intellij/lang/PsiElementExternalizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.psiStructureViewFactory"/></include> | [`PsiStructureViewFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.sliceProvider"/></include> | [`SliceLanguageSupportProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/slicer/SliceLanguageSupportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.structureViewExtension"/></include> | [`StructureViewExtension`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/StructureViewExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.surroundDescriptor"/></include> | [`SurroundDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.symbolSearchTarget"/></include> | [`SymbolSearchTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.symbolTypeProvider"/></include> ![Experimental][experimental] | [`SymbolTypeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/SymbolTypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.syntaxHighlighter"/></include> | [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.unwrapDescriptor"/></include> | [`UnwrapDescriptor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/unwrap/UnwrapDescriptor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.library.presentationProvider"/></include> | [`LibraryPresentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryPresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.library.type"/></include> | [`LibraryType`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/LibraryType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.librarySettingsProvider"/></include> | [`LibrarySettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/configuration/LibrarySettingsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.liveTemplateSubstitutor"/></include> | [`TemplateSubstitutor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/TemplateSubstitutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.longLineInspectionPolicy"/></include> | [`LongLineInspectionPolicy`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/longLine/LongLineInspectionPolicy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.macro"/></include> | [`Macro`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/Macro.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.macroFilter"/></include> | [`MacroFilter`](%gh-ic%/platform/macro/src/com/intellij/ide/macro/MacroFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.marketplaceLocalRanker"/></include> ![Internal][internal] | [`MarketplaceLocalRanker`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/marketplace/ranking/MarketplaceLocalRanker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.marketplaceTextualFeaturesProvider"/></include> ![Internal][internal] | [`MarketplaceTextualFeaturesProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/marketplace/statistics/features/MarketplaceTextualFeaturesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.metaDataContributor"/></include> | [`MetaDataContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/meta/MetaDataContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.methodHierarchyProvider"/></include> | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.methodNavigationOffsetProvider"/></include> | [`MethodNavigationOffsetProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/navigation/MethodNavigationOffsetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.clientGenerator"/></include> ![Experimental][experimental] | [`ClientGenerator`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/client/generator/ClientGenerator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.endpointsProjectModel"/></include> | [`EndpointsProjectModel`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsProjectModels.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.endpointsProvider"/></include> | [`EndpointsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.endpointsSidePanelProvider"/></include> | [`EndpointsSidePanelProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/endpoints/EndpointsSidePanelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.featuresAvailabilityProvider"/></include> ![Experimental][experimental] | [`MicroservicesFeaturesAvailabilityProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/MicroservicesFeaturesAvailabilityProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.oasSerializationCompatibilityProvider"/></include> ![Deprecated][deprecated] ![Internal][internal] | [`OasSerializationCompatibilityProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/oas/serialization/OasSerializationUtils.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.oasSpecificationProvider"/></include> | [`OasSpecificationProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/oas/OasSpecificationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.requestNavigator"/></include> | [`RequestNavigator`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/http/request/RequestNavigator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.urlInlayAction"/></include> | [`UrlPathInlayAction`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/inlay/UrlPathInlayAction.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.urlInlayLanguagesProvider"/></include> | [`UrlPathInlayLanguagesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/inlay/UrlPathInlayLanguagesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.microservices.urlResolverFactory"/></include> | [`UrlResolverFactory`](%gh-ic%/platform/lang-api/src/com/intellij/microservices/url/UrlResolverFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.mlCodeCompletionConfigurable"/></include> ![Internal][internal] | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.mlCompletionFeaturesCollector"/></include> ![Internal][internal] | [`InlineCompletionFeaturesCollector`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/inline/completion/features/InlineCompletionFeaturesCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.modelScopeItemPresenter"/></include> ![Non-Dynamic][non-dynamic] | [`ModelScopeItemPresenter`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/dialog/ModelScopeItemPresenter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.module.workingDirectoryProvider"/></include> | [`WorkingDirectoryProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/openapi/module/WorkingDirectoryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleBuilder"/></include> | [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleConfigurationEditorProvider"/></include> | [`ModuleConfigurationEditorProvider`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/roots/ui/configuration/ModuleConfigurationEditorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleNameGenerator"/></include> ![Experimental][experimental] | [`ModuleNameGenerator`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleNameGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleRendererFactory"/></include> | [`ModuleRendererFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/ModuleRendererFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleType"/></include> ![Obsolete][obsolete] | [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multiLangCommenter"/></include> | [`MultipleLangCommentProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/templateLanguages/MultipleLangCommentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multipleRunLocationsProvider"/></include> | [`MultipleRunLocationsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/MultipleRunLocationsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.navbar"/></include> | [`NavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newFileActionCategoryHandler"/></include> ![Experimental][experimental] | [`NewFileActionCategoryHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/NewFileActionCategoryHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.optionsApplicabilityFilter"/></include> | [`OptionsApplicabilityFilter`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/OptionsApplicabilityFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.outOfSourcesChecker"/></include> | [`OutOfSourcesChecker`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/projectRoots/OutOfSourcesChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.overrideImplementsAnnotationsFilter"/></include> | [`OverrideImplementsAnnotationsFilter`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packageDependencies.visitor"/></include> | [`DependencyVisitorFactory`](%gh-ic%/platform/analysis-impl/src/com/intellij/packageDependencies/DependencyVisitorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packageGroupRuleProvider"/></include> ![Internal][internal] | [`PackageGroupRuleProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/PackageGroupRuleProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pathReferenceProvider"/></include> | [`PathReferenceProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/paths/PathReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.patternDialectProvider"/></include> ![Non-Dynamic][non-dynamic] | [`PatternDialectProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/packageDependencies/ui/PatternDialectProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.patterns.patternClass"/></include> | `Object` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.backend.documentation.inlineDocumentationProvider"/></include> | [`InlineDocumentationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/InlineDocumentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.backend.documentation.linkHandler"/></include> | [`DocumentationLinkHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationLinkHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.backend.documentation.psiTargetProvider"/></include> | [`PsiDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/PsiDocumentationTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.backend.documentation.symbolTargetProvider"/></include> ![Experimental][experimental] | [`SymbolDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/SymbolDocumentationTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.backend.documentation.targetProvider"/></include> | [`DocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ijent.ijentExecFileProvider"/></include> ![Internal][internal] | [`IjentExecFileProvider`](%gh-ic%/platform/ijent/src/com/intellij/platform/ijent/IjentExecFileProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.lang.lsWidget.itemsProvider"/></include> ![Experimental][experimental] | [`LanguageServiceWidgetItemsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/platform/lang/lsWidget/LanguageServiceWidgetItemsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginExternalResources.unpackToPlugin"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.presentationProvider"/></include> | [`PresentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/PresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.printHandler"/></include> | [`PrintActionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/PrintActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.printOption"/></include> | [`PrintOption`](%gh-ic%/platform/lang-impl/src/com/intellij/codeEditor/printing/PrintOption.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.problemFileHighlightFilter"/></include> ![Project-Level][project-level] | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.problemHighlightFilter"/></include> | [`ProblemHighlightFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ProblemHighlightFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.problemsViewPanelProvider"/></include> ![Project-Level][project-level] | [`ProblemsViewPanelProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/analysis/problemsView/toolWindow/ProblemsViewPanelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.programRunner"/></include> | [`ProgramRunner`](%gh-ic%/platform/execution/src/com/intellij/execution/runners/ProgramRunner.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.project.converterProvider"/></include> | [`ConverterProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/conversion/ConverterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectFacetListener"/></include> | [`ProjectFacetListener`](%gh-ic%/platform/lang-api/src/com/intellij/facet/ProjectFacetListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectSdkSetupValidator"/></include> | [`ProjectSdkSetupValidator`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectStructure.sourceRootEditHandler"/></include> | [`ModuleSourceRootEditHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/ModuleSourceRootEditHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTaskRunner"/></include> | [`ProjectTaskRunner`](%gh-ic%/platform/lang-api/src/com/intellij/task/ProjectTaskRunner.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplateFileProcessor"/></include> | [`ProjectTemplateFileProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/projectWizard/ProjectTemplateFileProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplateParameterFactory"/></include> | [`ProjectTemplateParameterFactory`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ProjectTemplateParameterFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectView.externalLibraries.workspaceModelNodesProvider"/></include> ![Experimental][experimental] | [`ExternalLibrariesWorkspaceModelNodesProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/nodes/ExternalLibrariesWorkspaceModelNodesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectViewNestingRulesProvider"/></include> | [`ProjectViewNestingRulesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/projectView/ProjectViewNestingRulesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectViewNodeDecorator"/></include> ![Project-Level][project-level] | [`ProjectViewNodeDecorator`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/ProjectViewNodeDecorator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectViewPane"/></include> ![Project-Level][project-level] | [`AbstractProjectViewPane`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/AbstractProjectViewPane.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectViewPaneSelectionHelper"/></include> | [`ProjectViewPaneSelectionHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/impl/ProjectViewPaneSelectionHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.files.provider"/></include> | [`PropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/properties/provider/PropertiesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.declarationProvider"/></include> | [`PsiSymbolDeclarationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.referenceProvider"/></include> ![Non-Dynamic][non-dynamic] | [`PsiReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.readWriteAccessDetector"/></include> | [`ReadWriteAccessDetector`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/highlighting/ReadWriteAccessDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.readerModeMatcher"/></include> | [`ReaderModeMatcher`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeMatcher.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.readerModeProvider"/></include> | [`ReaderModeProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refGraphAnnotator"/></include> | [`RefGraphAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/RefGraphAnnotator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.codeVisionSupport"/></include> | [`RefactoringCodeVisionSupport`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/RefactoringCodeVisionSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.referenceImporter"/></include> | [`ReferenceImporter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/ReferenceImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.referenceInjector"/></include> | [`ReferenceInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.referenceProviderType"/></include> | [`PsiReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.retypeFileAssistant"/></include> | [`RetypeFileAssistant`](%gh-ic%/platform/lang-impl/src/com/intellij/internal/retype/RetypeFileAction.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.roots.watchedRootsProvider"/></include> | [`WatchedRootsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/WatchedRootsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runAnything.commandCustomizer"/></include> | [`RunAnythingCommandCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/commands/RunAnythingCommandCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runAnything.commandHandler"/></include> | [`RunAnythingCommandHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/handlers/RunAnythingCommandHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runAnything.executionProvider"/></include> | [`RunAnythingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runAnything.helpGroup"/></include> | [`RunAnythingHelpGroup`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/groups/RunAnythingHelpGroup.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationBeforeRunProviderDelegate"/></include> | [`RunConfigurationBeforeRunProviderDelegate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/impl/RunConfigurationBeforeRunProviderDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationProducer"/></include> ![DumbAware][dumb-aware] | [`RunConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/RunConfigurationProducer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationTargetEnvironmentAdjusterFactory"/></include> ![Internal][internal] | [`Factory`](%gh-ic%/platform/execution/src/com/intellij/execution/target/RunConfigurationTargetEnvironmentAdjuster.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationTemplateProvider"/></include> ![Project-Level][project-level] | [`RunConfigurationTemplateProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/impl/RunManagerImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationsSettings"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`RunConfigurationsSettings`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfigurationsSettings.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runDashboardCustomizer"/></include> | [`RunDashboardCustomizer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runDashboardDefaultTypesProvider"/></include> | [`RunDashboardDefaultTypesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardDefaultTypesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runDashboardGroupingRule"/></include> | [`RunDashboardGroupingRule`](%gh-ic%/platform/lang-api/src/com/intellij/execution/dashboard/RunDashboardGroupingRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runLineMarkerContributor"/></include> ![DumbAware][dumb-aware] | [`RunLineMarkerContributor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/lineMarker/RunLineMarkerContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runToolbarProcess"/></include> | [`RunToolbarProcess`](%gh-ic%/platform/execution/src/com/intellij/execution/runToolbar/RunToolbarProcess.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runningApplicationUpdaterProvider"/></include> | [`RunningApplicationUpdaterProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/update/RunningApplicationUpdaterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.safeDeleteTargetProvider"/></include> | [`SafeDeleteTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/safeDelete/api/SafeDeleteTargetProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.saveFileAsTemplateHandler"/></include> | [`SaveFileAsTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SaveFileAsTemplateHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.scopeDescriptorProvider"/></include> | [`ScopeDescriptorProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/scopeChooser/ScopeDescriptorProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.scopeParserExtension"/></include> | [`PackageSetParserExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/search/scope/packageSet/PackageSetParserExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.scratch.creationHelper"/></include> | [`ScratchFileCreationHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/scratch/ScratchFileCreationHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.scratch.rootType"/></include> | [`RootType`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/scratch/RootType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sdkDownload"/></include> | [`SdkDownload`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/projectRoot/SdkDownload.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sdkFinder"/></include> | [`SdkFinder`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/SdkFinder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sdkType"/></include> | [`SdkType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/projectRoots/SdkType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereClassifier"/></include> | [`SearchEverywhereClassifier`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SearchEverywhereClassifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereContributor"/></include> | [`SearchEverywhereContributorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereEssentialContributorsMarker"/></include> ![Internal][internal] | [`SearchEverywhereEssentialContributorMarker`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereEssentialContributorMarker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMlContributorReplacement"/></include> ![Internal][internal] | [`SearchEverywhereMlContributorReplacement`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlContributorReplacement.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMlService"/></include> ![Internal][internal] | [`SearchEverywhereMlService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywherePreviewPrimaryUsageFinder"/></include> ![Internal][internal] | [`SearchEverywherePreviewPrimaryUsageFinder`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywherePreviewPrimaryUsageFinder.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereRemoteConverter"/></include> | [`RemoteSearchEverywhereConverterSupplier`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/remote/RemoteSearchEverywhereConverterSupplier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereReorderingService"/></include> ![Internal][internal] | [`SearchEverywhereReorderingService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereReorderingService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereResultsEqualityProvider"/></include> | [`SEResultsEqualityProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SEResultsEqualityProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereSpellingCorrector"/></include> ![Internal][internal] | [`SearchEverywhereSpellingCorrectorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereSpellingCorrector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchScopesProvider"/></include> | [`SearchScopeProvider`](%gh-ic%/platform/analysis-impl/src/com/intellij/psi/search/SearchScopeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searcher"/></include> | [`Searcher`](%gh-ic%/platform/indexing-api/src/com/intellij/model/search/Searcher.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.semContributor"/></include> | [`SemContributor`](%gh-ic%/platform/lang-api/src/com/intellij/semantic/SemContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.serviceViewContributor"/></include> | [`ServiceViewContributor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceViewContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.silentChangeVetoer"/></include> ![Internal][internal] | [`SilentChangeVetoer`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/SilentChangeVetoer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stacktrace.fold"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stacktrace.fold.line.modifier"/></include> ![Experimental][experimental] | [`ConsoleLineModifier`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleLineModifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistician"/></include> | [`Statistician`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/statistics/Statistician.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stepsBeforeRunProvider"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`BeforeRunTaskProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/BeforeRunTaskProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.structureViewBuilder"/></include> | [`StructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewBuilder.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.symbolDeclarationPresentationProvider"/></include> | [`SymbolDeclarationPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/presentation/SymbolDeclarationPresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.symbolNavigation"/></include> ![Experimental][experimental] | [`SymbolNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.targetElementEvaluator"/></include> | [`TargetElementEvaluator`](%gh-ic%/platform/core-impl/src/com/intellij/codeInsight/TargetElementEvaluator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.targetElementUtilExtender"/></include> | [`TargetElementUtilExtender`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/TargetElementUtilExtender.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.templateCompletionProcessor"/></include> | [`TemplateCompletionProcessor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/template/macro/TemplateCompletionProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testActionProvider"/></include> | [`ToggleModelActionProvider`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/ToggleModelActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testCreator"/></include> ![DumbAware][dumb-aware] | [`TestCreator`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestCreator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testDiffProvider"/></include> | [`TestDiffProvider`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/actions/TestDiffProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testFinder"/></include> | [`TestFinder`](%gh-ic%/platform/lang-api/src/com/intellij/testIntegration/TestFinder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testSrcLocator"/></include> ![Deprecated][deprecated] ![Removal][removal] | [`TestLocationProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/testIntegration/TestLocationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.todoExtraPlaces"/></include> | [`ExtraPlaceChecker`](%gh-ic%/platform/indexing-impl/src/com/intellij/psi/impl/cache/impl/todo/TodoIndexers.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.todoIndexer"/></include> | [`DataIndexer`](%gh-ic%/platform/util/src/com/intellij/util/indexing/DataIndexer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolsCustomizer"/></include> | [`ToolsCustomizer`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolsProvider"/></include> | [`ToolsProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/tools/ToolsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.trafficLightRendererContributor"/></include> | [`TrafficLightRendererContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/TrafficLightRendererContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.treeGenerator"/></include> | [`TreeGenerator`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/TreeGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.treeStructureProvider"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`TreeStructureProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typeDeclarationProvider"/></include> | [`TypeDeclarationProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/actions/TypeDeclarationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typeHierarchyProvider"/></include> | [`HierarchyProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/hierarchy/HierarchyProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typeIcon"/></include> ![Internal][internal] | `Object` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.typeName"/></include> | `Object` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.uiDebuggerExtension"/></include> ![Non-Dynamic][non-dynamic] | [`UiDebuggerExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ui/debugger/UiDebuggerExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageContextPanelProvider"/></include> ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageContextPanel.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageFeaturesProvider"/></include> ![Experimental][experimental] | [`UsageSimilarityFeaturesProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/similarity/features/UsageSimilarityFeaturesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageFilteringRuleProvider"/></include> | [`UsageFilteringRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageGroupingRuleProvider"/></include> | [`UsageGroupingRuleProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageGroupingRuleProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageTargetProvider"/></include> ![DumbAware][dumb-aware] | [`UsageTargetProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageToPsiElementProvider"/></include> | [`UsageToPsiElementProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/UsageToPsiElementProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageTypeProvider"/></include> | [`UsageTypeProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/rules/UsageTypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageViewElementsListener"/></include> | [`UsageViewElementsListener`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewElementsListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageViewFactory"/></include> | [`UsageViewFactory`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageViewPopupFactory"/></include> ![Internal][internal] | [`UsageViewPopupFactory`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/UsageViewPopup.kt) |

### LangExtensions.xml

[`LangExtensions.xml`](%gh-ic%/platform/platform-resources/src/META-INF/LangExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.facetIgnorer"/></include> ![Internal][internal] | [`FacetIgnorer`](%gh-ic%/platform/lang-impl/src/com/intellij/facet/impl/invalid/FacetIgnorer.kt) |

### ml.xml

[`ml.xml`](%gh-ic%/platform/ml-impl/resources/META-INF/ml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ml.impl.approach"/></include> ![Internal][internal] | [`MLTaskApproachBuilder`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/MLTask.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ml.impl.turboComplete.smartPipelineRunner"/></include> ![Internal][internal] | [`SmartPipelineRunner`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/SmartPipelineRunner.kt) |

### OpenTelemetryExtensions.xml

[`OpenTelemetryExtensions.xml`](%gh-ic%/platform/diagnostic/telemetry-impl/resources/META-INF/OpenTelemetryExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openTelemetryExporterProvider"/></include> ![Internal][internal] | [`OpenTelemetryExporterProvider`](%gh-ic%/platform/diagnostic/telemetry-impl/src/OpenTelemetryExporterProvider.kt) |

### PlatformExecutionActions.xml

[`PlatformExecutionActions.xml`](%gh-ic%/platform/execution-impl/resources/META-INF/PlatformExecutionActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.execution.displayDescriptorChooser"/></include> | [`DisplayDescriptorChooser`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/StoppableRunDescriptors.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multilaunch.condition.template"/></include> ![Experimental][experimental] | [`ConditionTemplate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/conditions/ConditionTemplate.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.multilaunch.task.definition"/></include> ![Experimental][experimental] | [`TaskExecutableTemplate`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/multilaunch/execution/executables/TaskExecutableTemplate.kt) |

### PlatformExtensionPoints.xml

[`PlatformExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ApplicationLoadListener"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ApplicationLoadListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ApplicationLoadListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.aboutPopupDescriptionProvider"/></include> | [`AboutPopupDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/AboutPopupDescriptionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.achromatopsiaSupport"/></include> ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionConfigurationCustomizer"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionConfigurationCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionFromOptionDescriptorProvider"/></include> | [`ActionFromOptionDescriptorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/ActionFromOptionDescriptorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionGroupCustomization"/></include> ![Internal][internal] | [`ActionGroupCustomizationExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/ActionGroupCustomizationService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionOnSave"/></include> | [`ActionOnSave`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/impl/ActionsOnSaveFileDocumentManagerListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionOnSaveInfoProvider"/></include> | [`ActionOnSaveInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actionsOnSave/ActionOnSaveInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.actionPromoter"/></include> | [`ActionPromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/ActionPromoter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.activityTracker"/></include> | [`ActivityTracker`](%gh-ic%/platform/backend/observation/src/com/intellij/platform/backend/observation/ActivityTracker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.additionalTextAttributes"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.advancedSetting"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.aiDataCollectionExternalSettings"/></include> ![Internal][internal] | [`AiDataCollectionExternalSettings`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/gdpr/ui/consents/AiDataCollectionConsentUi.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.appStarter"/></include> | [`ApplicationStarter`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationStarter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.applicationConfigurable"/></include> | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.applicationSettings"/></include> ![Experimental][experimental] | [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.authorizationProvider"/></include> ![Internal][internal] | [`AuthorizationProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/AuthorizationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.backedVirtualFileProvider"/></include> ![Experimental][experimental] | [`BackedVirtualFileProvider`](%gh-ic%/platform/core-api/src/com/intellij/notebook/editor/BackedVirtualFileProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.beforeRunStartupTasks"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`BeforeRunStartupTasks`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/startup/BeforeRunStartupTasks.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.breadcrumbsPresentationProvider"/></include> | [`BreadcrumbsPresentationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/xml/breadcrumbs/BreadcrumbsPresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bundledColorScheme"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bundledInspectionProfile"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bundledKeymap"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.bundledQuickListsProvider"/></include> | [`BundledQuickListsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/BundledQuickListsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cachedValuesFactory"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`CachedValuesFactory`](%gh-ic%/platform/core-impl/src/com/intellij/util/CachedValuesFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cachesInvalidator"/></include> | [`CachesInvalidator`](%gh-ic%/platform/ide-core/src/com/intellij/ide/caches/CachesInvalidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cefDelegate"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`CefDelegate`](%gh-ic%/platform/ui.jcef/jcef/CefDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.classpathStorageProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ClasspathStorageProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/impl/storage/ClasspathStorageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.folding.collapseBlockHandler"/></include> | [`CollapseBlockHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/folding/CollapseBlockHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.linkHandler"/></include> | [`TooltipLinkHandler`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/highlighting/TooltipLinkHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.template.postfixTemplateProvider"/></include> | [`PostfixTemplateProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplateProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeWithMe.authorizationProvider"/></include> ![Deprecated][deprecated] ![Experimental][experimental] ![Internal][internal] | [`CodeWithMeAuthorizationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/codeWithMe/CodeWithMeAuthorizationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeWithMe.serverUrlProvider"/></include> ![Deprecated][deprecated] ![Experimental][experimental] ![Internal][internal] | [`CodeWithMeServerUrlProvider`](%gh-ic%/platform/platform-api/src/com/intellij/codeWithMe/CodeWithMeServerUrlProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorAndFontOptionsImportHandler"/></include> ![Non-Dynamic][non-dynamic] | [`ImportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/colors/ImportHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorPickerListenerFactory"/></include> | [`ColorPickerListenerFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ColorPickerListenerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commandLineEnvCustomizer"/></include> ![Internal][internal] | [`CommandLineEnvCustomizer`](%gh-ic%/platform/platform-api/src/com/intellij/execution/process/GeneralCommandLineEnvCustomizerService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.config.codeVisionGroupSettingProvider"/></include> | [`CodeVisionGroupSettingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/settings/CodeVisionGroupSettingProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.config.inlayGroupSettingProvider"/></include> | [`InlayGroupSettingProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayGroupSettingProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.config.inlaySettingsProvider"/></include> | [`InlaySettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlaySettingsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.configurablesPatcher"/></include> ![Experimental][experimental] ![Internal][internal] | [`ConfigurablesPatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/ConfigurablesPatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.contentTabActionProvider"/></include> | [`ContentTabActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/content/tabActions/ContentTabAction.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.coursesStorageProvider"/></include> | [`CoursesStorageProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/learnIde/coursesInProgress/CoursesStorageProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.credentialStore"/></include> ![Non-Dynamic][non-dynamic] | [`CredentialStoreFactory`](%gh-ic%/platform/credential-store-impl/src/credentialStore/CredentialStoreFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customFileDropHandler"/></include> ![Deprecated][deprecated] ![Project-Level][project-level] | [`CustomFileDropHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/CustomFileDropHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customTypeRpcSerializer"/></include> ![Internal][internal] | [`CustomTypeRpcSerializer`](%gh-ic%/platform/platform-impl/rpc/src/com/intellij/ide/rpc/CustomTypeRpcSerializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customizableActionGroupProvider"/></include> | [`CustomizableActionGroupProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/CustomizableActionGroupProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cutElementMarker"/></include> | [`CutElementMarker`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/CutElementMarker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dataValidators"/></include> ![Internal][internal] | [`DataValidators`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/DataValidators.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defaultToolWindowLayout"/></include> | [`DefaultToolWindowLayoutExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/toolWindow/defaultToolWindowlayoutProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.defender.config"/></include> | [`Extension`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/WindowsDefenderChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dependencyCollector"/></include> | [`DependencyCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/DependencyCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dependencySupport"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.deuteranopiaSupport"/></include> ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dialogInvocationPlace"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.DiffExtension"/></include> | [`DiffExtension`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.DiffTool"/></include> | [`DiffTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/DiffTool.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.actions.ShowDiffAction.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.actions.ShowStandaloneDiffAction.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.editor.diffRequestProcessorEditorCustomizer"/></include> | [`DiffRequestProcessorEditorCustomizer`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/editor/DiffRequestProcessorEditorCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.impl.DiffToolSubstitutor"/></include> ![Internal][internal] | [`DiffToolSubstitutor`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/impl/DiffToolSubstitutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.lang.DiffIgnoredRangeProvider"/></include> | [`DiffIgnoredRangeProvider`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/lang/DiffIgnoredRangeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.merge.MergeTool"/></include> | [`MergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/MergeTool.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diff.merge.external.AutomaticExternalMergeTool"/></include> | [`AutomaticExternalMergeTool`](%gh-ic%/platform/diff-api/src/com/intellij/diff/merge/external/AutomaticExternalMergeTool.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.directoryProjectConfigurator"/></include> | [`DirectoryProjectConfigurator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectConfigurator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.directoryProjectGenerator"/></include> | [`DirectoryProjectGenerator`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/DirectoryProjectGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentationActionProvider"/></include> | [`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dynamicActionConfigurationCustomizer"/></include> | [`DynamicActionConfigurationCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/DynamicActionConfigurationCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editor.injectedFileChangesHandlerProvider"/></include> | [`InjectedFileChangesHandlerProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/injected/editor/InjectedFileChangesHandlerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editor.linePainter"/></include> | [`EditorLinePainter`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/EditorLinePainter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorActionHandler"/></include> | [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorAutoClosingHandler"/></include> ![Experimental][experimental] ![Internal][internal] | [`EditorAutoClosingHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorAutoClosingHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFactoryMouseListener"/></include> | [`EditorMouseListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFactoryMouseMotionListener"/></include> | [`EditorMouseMotionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseMotionListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFileSwapper"/></include> | [`EditorFileSwapper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/EditorFileSwapper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFloatingToolbarProvider"/></include> | [`FloatingToolbarProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/toolbar/floating/FloatingToolbarProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorHighlighterProvider"/></include> | [`EditorHighlighterProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/EditorHighlighterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorNavigation"/></include> | [`EditorNavigationDelegate`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/EditorNavigationDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorNotificationProvider"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorTabColorProvider"/></include> ![DumbAware][dumb-aware] | [`EditorTabColorProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorTabTitleProvider"/></include> | [`EditorTabTitleProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorTypedHandler"/></include> ![Removal][removal] ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.endUserAgreementUpdater"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.errorHandler"/></include> | [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.eventLogCategory"/></include> | [`EventLogCategory`](%gh-ic%/platform/ide-core/src/com/intellij/notification/EventLogCategory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.execution.syntheticConfigurationTypeProvider"/></include> ![Experimental][experimental] | [`SyntheticConfigurationTypeProvider`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/SyntheticConfigurationTypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.executionActionSuppressor"/></include> ![Experimental][experimental] ![Internal][internal] | [`ExecutionActionSuppressor`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/ExecutionActionSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.experimentalFeature"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.exportable"/></include> ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalComponentSource"/></include> | [`ExternalComponentSource`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/externalComponents/ExternalComponentSource.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.featureStatisticsBundle"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.feedbackDescriptionProvider"/></include> ![Non-Dynamic][non-dynamic] | [`FeedbackDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/FeedbackDescriptionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileBreadcrumbsCollector"/></include> ![Project-Level][project-level] | [`FileBreadcrumbsCollector`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/breadcrumbs/FileBreadcrumbsCollector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileDocumentManagerListener"/></include> | [`FileDocumentManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentManagerListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileDocumentSynchronizationVetoer"/></include> | [`FileDocumentSynchronizationVetoer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/FileDocumentSynchronizationVetoer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileDropHandler"/></include> | [`FileDropHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/FileDropHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileEditorProvider"/></include> ![DumbAware][dumb-aware] | [`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileEditorProviderSuppressor"/></include> ![Internal][internal] | [`FileEditorProviderSuppressor`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/FileEditorProviderSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileEncodingProvider"/></include> | [`FileEncodingProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/FileEncodingProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileType"/></include> | [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeFactory"/></include> ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`FileTypeFactory`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeOverrider"/></include> | [`FileTypeOverrider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileTypes/impl/FileTypeOverrider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeRegistrar"/></include> ![Non-Dynamic][non-dynamic] | [`FileTypeRegistrar`](%gh-ic%/platform/ide-core/src/com/intellij/ide/highlighter/FileTypeRegistrar.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileTypeUsageSchemaDescriptor"/></include> ![Internal][internal] | [`FileTypeUsageSchemaDescriptor`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/fileTypes/FileTypeUsageSchemaDescriptor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.findInDirectoryScopeProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`FindInDirectoryScopeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/FindInDirectoryScopeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.flsConfigurationProvider"/></include> ![Internal][internal] | [`FLSConfigurationProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/FLSConfigurationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fragments.dsl.builder.extender"/></include> ![Experimental][experimental] ![Internal][internal] | [`FragmentsDslBuilderExtender`](%gh-ic%/platform/platform-api/src/com/intellij/execution/ui/utils/FragmentsDslBuilder.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalOptionsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`SearchableConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/SearchableConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalTroubleInfoCollector"/></include> | [`GeneralTroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/GeneralTroubleInfoCollector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.genericAuthProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`GenericAuthProviderExtension`](%gh-ic%/platform/platform-impl/src/com/intellij/auth/GenericAuthProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.getDataRule"/></include> ![Internal][internal] | [`GetDataRule`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/impl/dataRules/GetDataRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gitRepositoryInitializer"/></include> | [`GitRepositoryInitializer`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitRepositoryInitializer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gitSilentFileAdder"/></include> ![Internal][internal] ![Project-Level][project-level] | [`GitSilentFileAdderProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/GitSilentFileAdderProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.groupConfigurable"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gutterMarkPreprocessor"/></include> ![Non-Dynamic][non-dynamic] | [`GutterMarkPreprocessor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/GutterMarkPreprocessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.handleTypeFactory"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`HandleTypeFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/readOnlyHandler/HandleTypeFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.http.fileEditorActionProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`RemoteFileEditorActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/RemoteFileEditorActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.http.localFileFinder"/></include> | [`LocalFileFinder`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/http/LocalFileFinder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iconDescriptionBundle"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iconMapper"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iconMapperSuppressor"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ide.dynamicPluginVetoer"/></include> | [`DynamicPluginVetoer`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginVetoer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ideEventQueueDispatcher"/></include> | [`EventDispatcher`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/IdeEventQueue.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.idePerformanceListener"/></include> ![Experimental][experimental] ![Internal][internal] | [`PerformanceListener`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/PerformanceListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ideRootPaneNorth"/></include> ![Non-Dynamic][non-dynamic] | [`IdeRootPaneNorthExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/IdeRootPaneNorthExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ideStartupWizard"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`IdeStartupWizard`](%gh-ic%/platform/platform-impl/bootstrap/src/com/intellij/platform/ide/bootstrap/IdeStartupWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inspectionPopupLevelChangePolicy"/></include> ![Internal][internal] | [`InspectionPopupLevelChangePolicy`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/InspectionPopupLevelChangePolicy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.interactiveCourseFactory"/></include> | [`InteractiveCourseFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/InteractiveCourseFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.internal.ml.featureProvider"/></include> ![Internal][internal] | [`MLFeatureProvider`](%gh-ic%/platform/ml-api/src/com/intellij/internal/ml/MLFeatureProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.itemPresentationProvider"/></include> | [`ItemPresentationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/ItemPresentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.iw.actionProvider"/></include> | [`InspectionWidgetActionProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/markup/InspectionWidgetActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jbProtocolCommand"/></include> | [`JBProtocolCommand`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/application/JBProtocolCommand.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jbProtocolRevisionResolver"/></include> | [`JBProtocolRevisionResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/navigation/JBProtocolRevisionResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jcef.appRequiredArgumentsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`JBCefAppRequiredArgumentsProvider`](%gh-ic%/platform/ui.jcef/jcef/JBCefAppRequiredArgumentsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jdkDownloader.jdkInstallerListener"/></include> ![Internal][internal] | [`JdkInstallerListener`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkInstaller.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jdkUpdateCheckContributor"/></include> | [`JdkUpdateCheckContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/jdkDownloader/JdkUpdater.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jps.plugin"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.keymapExtension"/></include> | [`KeymapExtension`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/keymap/KeymapExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.syntaxHighlighterFactory"/></include> | [`SyntaxHighlighterFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.library.toolWindow"/></include> ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lightEditTabAttributesProvider"/></include> ![Experimental][experimental] | [`LightEditTabAttributesProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/lightEdit/LightEditTabAttributesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.logLevelConfigurationListener"/></include> ![Internal][internal] | [`Listener`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/logs/LogLevelConfigurationManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.logsPreprocessor"/></include> ![Internal][internal] | [`LogProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/logsUploader/LogProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lowLevelProjectOpenProcessor"/></include> ![Internal][internal] | [`LowLevelProjectOpenProcessor`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/project/ex/LowLevelProjectOpenProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.mac.dockMenuActions"/></include> ![Non-Dynamic][non-dynamic] ![Experimental][experimental] ![Internal][internal] | [`MacDockMenuActions`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/mac/MacDockMenuActions.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.meetNewUiCustomization"/></include> ![Internal][internal] | [`MeetNewUiCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/experimental/meetNewUi/MeetNewUiCustomization.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.navbarLeftSide"/></include> ![Internal][internal] | [`NavBarLeftSideExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarLeftSideExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProject.onboarding.tips"/></include> ![Internal][internal] | [`NewProjectOnboardingTips`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectOnboardingTips.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.generator"/></include> | [`GeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/GeneratorNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.language"/></include> ![Deprecated][deprecated] | [`LanguageNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/LanguageNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.languageGenerator"/></include> | [`LanguageGeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/language/LanguageGeneratorNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nonProjectFileWritingAccessExtension"/></include> ![Project-Level][project-level] | [`NonProjectFileWritingAccessExtension`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/NonProjectFileWritingAccessExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.notification.group"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.notification.parentGroup"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.notificationRemindLaterHandler"/></include> | [`NotificationRemindLaterHandler`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationRemindLaterHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.notificationRouter"/></include> ![Internal][internal] | [`NotificationRouter`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationRouter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.obsoleteStorage"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pathMacroContributor"/></include> | [`PathMacroContributor`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pathMacroExpandableProtocol"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pathMacroFilter"/></include> | [`PathMacroFilter`](%gh-ic%/jps/model-serialization/src/com/intellij/openapi/application/PathMacroFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.persistentFsConnectionListener"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`PersistentFsConnectionListener`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/vfs/newvfs/persistent/PersistentFsConnectionListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ml.descriptor"/></include> ![Internal][internal] | [`TierDescriptor`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/TierDescriptor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ml.environmentExtender"/></include> ![Internal][internal] | [`EnvironmentExtender`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/environment/EnvironmentExtender.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.platform.ml.taskListener"/></include> ![Internal][internal] | [`MLTaskGroupListener`](%gh-ic%/platform/ml-api/src/com/intellij/platform/ml/monitoring/MLApproachListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginInstallationCustomization"/></include> ![Internal][internal] | [`PluginInstallationCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/newui/PluginInstallationCustomization.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginReplacement"/></include> | [`PluginReplacement`](%gh-ic%/platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginRepositoryAuthProvider"/></include> | [`PluginRepositoryAuthProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginSuggestionProvider"/></include> ![Internal][internal] | [`PluginSuggestionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/pluginsAdvertisement/PluginSuggestionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pluginsViewCustomizer"/></include> ![Experimental][experimental] ![Internal][internal] | [`PluginsViewCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/newui/PluginsViewCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.productivityFeaturesProvider"/></include> | [`ProductivityFeaturesProvider`](%gh-ic%/platform/platform-api/src/com/intellij/featureStatistics/ProductivityFeaturesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectAttachProcessor"/></include> | [`ProjectAttachProcessor`](%gh-ic%/platform/ide-core/src/com/intellij/projectImport/ProjectAttachProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectCloseHandler"/></include> ![Non-Dynamic][non-dynamic] | [`ProjectCloseHandler`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectConfigurable"/></include> ![Project-Level][project-level] | [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectCustomDataSynchronizer"/></include> ![Experimental][experimental] | [`ProjectCustomDataSynchronizer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/ProjectCustomDataSynchronizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectNameProvider"/></include> ![Non-Dynamic][non-dynamic] | [`ProjectNameProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/project/ex/ProjectNameProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectOpenProcessor"/></include> | [`ProjectOpenProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectOriginInfoProvider"/></include> ![Internal][internal] | [`ProjectOriginInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/ProjectOriginInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectServiceContainerCustomizer"/></include> ![Internal][internal] | [`ProjectServiceContainerCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectServiceInitializer"/></include> ![Internal][internal] | [`ProjectServiceInitializer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectServiceInitializer.essential"/></include> ![Internal][internal] | [`ProjectServiceInitializer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectManagerImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectSetProcessor"/></include> ![Non-Dynamic][non-dynamic] | [`ProjectSetProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectSetProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectSettings"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectStoreClassProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`ProjectStoreFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/impl/ProjectStoreFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTaskManagerListener"/></include> ![Internal][internal] | [`ProjectTaskManagerListenerExtensionPoint`](%gh-ic%/platform/lang-impl/src/com/intellij/task/impl/ProjectTaskManagerListenerExtensionPoint.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplate"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplatesFactory"/></include> | [`ProjectTemplatesFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/platform/ProjectTemplatesFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTypesProvider"/></include> ![Experimental][experimental] | [`ProjectTypesProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectTypesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectUndoProvider"/></include> ![Project-Level][project-level] | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protanopiaSupport"/></include> ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protocolHandler"/></include> | [`ProtocolHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ProtocolHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protocolNavigationCommandProcessor"/></include> ![Internal][internal] | [`ProtocolNavigationCommandProcessor`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/protocolHandler/ProtocolNavigationCommandProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.proxySettingsOverrideProvider"/></include> | [`ProxySettingsOverrideProvider`](%gh-ic%/platform/platform-api/src/com/intellij/util/net/ProxySettingsOverrideProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rawEditorTypedHandler"/></include> ![Removal][removal] ![Non-Dynamic][non-dynamic] | [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.recentProjectsBranchesProvider"/></include> | [`RecentProjectsBranchesProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/vcs/RecentProjectsBranchesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.recentProjectsProvider"/></include> ![Internal][internal] | [`RecentProjectProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/RecentProjectProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.recoveryAction"/></include> ![Internal][internal] | [`RecoveryAction`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/cache/Saul.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remote.credentialsLanguageContribution"/></include> | [`CredentialsLanguageContribution`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/ext/CredentialsLanguageContribution.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remote.credentialsType"/></include> | [`CredentialsType`](%gh-ic%/platform/remote-core/src/remote/CredentialsType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remote.pathMappingProvider"/></include> | [`PathMappingProvider`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/PathMappingProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.schemeExporter"/></include> | [`SchemeExporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeExporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.schemeImporter"/></include> | [`SchemeImporter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/SchemeImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.search.additionalOptionsLocation"/></include> | [`AdditionalLocationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionsRegistrar.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.search.optionContributor"/></include> | [`SearchableOptionContributor`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/SearchableOptionContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.search.projectOptionsTopHitProvider"/></include> | [`ProjectLevelProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/OptionsSearchTopHitProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.search.topHitProvider"/></include> | [`SearchTopHitProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SearchTopHitProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.search.traverseUiHelper"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`TraverseUIHelper`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/search/TraverseUIHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.selectInTarget"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`SelectInTarget`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SelectInTarget.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.semanticRootProvider"/></include> ![Non-Dynamic][non-dynamic] | [`RootSemanticAddressProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vfs/newvfs/persistent/RootSemanticAddressProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsEntryPointActionProvider"/></include> | [`ActionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.settingsEntryPointIconCustomizer"/></include> | [`IconCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/SettingsEntryPointAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.smartSelectProvider"/></include> | [`SmartSelectProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ide/SmartSelectProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sshCredentialProvider"/></include> | [`SshCredentialProvider`](%gh-ic%/platform/platform-impl/remote/src/com/intellij/remote/SshCredentialProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.startPagePromoter"/></include> ![Internal][internal] | [`StartPagePromoter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StartPagePromoter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistic.eventLog.eventLoggerProvider"/></include> | [`StatisticsEventLoggerProvider`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/StatisticsEventLogger.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistic.eventLog.externalEventLogSettings"/></include> ![Internal][internal] | [`ExternalEventLogSettings`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogSettings.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistic.eventLog.externalListenerProvider"/></include> ![Internal][internal] | [`ExternalEventLogListenerProviderExtension`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/ExternalEventLogListenerProviderExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistic.eventLog.fusStateEventTracker"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`FeatureUsageStateEventTracker`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/fus/FeatureUsageStateEventTracker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.actionCustomPlaceAllowlist"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.actionIdsHolder"/></include> ![Internal][internal] | [`ActionIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/actions/persistence/ActionIdsHolder.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.applicationUsagesCollector"/></include> ![Internal][internal] | [`ApplicationUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ApplicationUsagesCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.balloonIdsHolder"/></include> ![Internal][internal] | [`BalloonIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/statistic/collectors/fus/ui/BalloonIdsHolder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.collectorExtension"/></include> | [`FeatureUsageCollectorExtension`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsageCollectorExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.counterUsagesCollector"/></include> ![Internal][internal] | [`FeatureUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/FeatureUsagesCollector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.gotItTooltipAllowlist"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.notificationIdsHolder"/></include> ![Internal][internal] | [`NotificationIdsHolder`](%gh-ic%/platform/platform-impl/src/com/intellij/notification/impl/NotificationIdsHolder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.projectUsagesCollector"/></include> ![Internal][internal] | [`ProjectUsagesCollector`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/service/fus/collectors/ProjectUsagesCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.validation.customValidationRule"/></include> | [`CustomValidationRule`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statistics.validation.customValidationRuleFactory"/></include> | [`CustomValidationRuleFactory`](%gh-ic%/platform/statistics/src/com/intellij/internal/statistic/eventLog/validator/rules/impl/CustomValidationRuleFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statusBarWidgetFactory"/></include> | [`StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statusBarWidgetProvider"/></include> ![Deprecated][deprecated] ![Removal][removal] | [`StatusBarWidgetProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.streamProviderFactory"/></include> ![Internal][internal] ![Project-Level][project-level] | [`StreamProviderFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/configurationStore/StreamProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stripTrailingSpacesFilterFactory"/></include> | [`StripTrailingSpacesFilterFactory`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/StripTrailingSpacesFilterFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.subprojectInfoProvider"/></include> ![Internal][internal] | [`SubprojectInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/project/workspace/SubprojectInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.systemProperty"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testStatusListener"/></include> ![Non-Dynamic][non-dynamic] | [`TestStatusListener`](%gh-ic%/platform/testRunner/src/com/intellij/execution/testframework/TestStatusListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.textEditorCustomizer"/></include> ![Internal][internal] | [`TextEditorCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/text/TextEditorCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.textEditorNecromancerAwaker"/></include> ![Non-Dynamic][non-dynamic] | [`NecromancerAwaker`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/zombie/Necromancer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.themeMetadataProvider"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.themeProvider"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.themeRemapper"/></include> ![Internal][internal] | [`UiThemeRemapper`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/laf/UiThemeMapper.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tipAndTrick"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.titleInfoProvider"/></include> ![Non-Dynamic][non-dynamic] | [`TitleInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/TitleInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindow"/></include> ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindowAllowlist"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindowContentExtractor"/></include> ![Internal][internal] | [`ToolWindowContentExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowContentExtractor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindowExtractor"/></include> ![Internal][internal] | [`ToolWindowViewModelExtractor`](%gh-ic%/platform/platform-api/src/com/intellij/ui/viewModel/extraction/ToolWindowViewModelExtractor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindowExtractorMode"/></include> ![Experimental][experimental] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolWindowTabInEditorHelper"/></include> ![Experimental][experimental] ![Internal][internal] | [`ToolWindowTabInEditorHelper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/tabInEditor/ToolWindowTabInEditorHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.toolbarQuickAction"/></include> ![Non-Dynamic][non-dynamic] | [`ToolbarAddQuickActionInfo`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/ToolbarAddQuickActionInfo.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.trailingSpacesOptionsProvider"/></include> | [`TrailingSpacesOptionsProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/TrailingSpacesOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tree.CustomLanguageASTComparator"/></include> | [`CustomLanguageASTComparator`](%gh-ic%/platform/core-api/src/com/intellij/psi/tree/CustomLanguageASTComparator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tritanopiaSupport"/></include> ![Non-Dynamic][non-dynamic] | [`ColorBlindnessSupport`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/ui/ColorBlindnessSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.troubleInfoCollector"/></include> | [`TroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.trustedHostsConfigurableProvider"/></include> ![Internal][internal] | [`TrustedHostsConfigurableProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedHostsConfigurable.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.trustedProjectsLocator"/></include> | [`TrustedProjectsLocator`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/trustedProjects/TrustedProjectsLocator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ui.content.impl.toolWindowContentPostprocessor"/></include> ![Deprecated][deprecated] ![Internal][internal] | [`ToolWindowContentPostProcessor`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/content/impl/ToolWindowContentPostProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ui.optionEditorProvider"/></include> ![Experimental][experimental] | [`OptionEditorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/OptionEditorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ui.suitableFontProvider"/></include> ![Internal][internal] | [`SuitableFontProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SuitableFontProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.uiChangeListener"/></include> ![Internal][internal] | [`Listener`](%gh-ic%/platform/core-ui/src/ui/ExperimentalUI.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.uiDataRule"/></include> | [`UiDataRule`](%gh-ic%/platform/core-ui/src/openapi/actionSystem/UiDataProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.undoProvider"/></include> | [`UndoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.undoReportHandler"/></include> ![Internal][internal] | [`UndoReportHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/command/impl/UndoReportHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.unknownSdkContributor"/></include> | [`UnknownSdkContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/projectRoots/impl/UnknownSdkCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.unknownSdkResolver"/></include> | [`UnknownSdkResolver`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/UnknownSdkResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.updateSettingsProvider"/></include> | [`UpdateSettingsProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.updateSettingsUIProvider"/></include> | [`UpdateSettingsUIProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/UpdateSettingsUIProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usageFilteringRuleCustomizer"/></include> ![Internal][internal] | [`UsageFilteringRuleCustomizer`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/UsageFilteringRuleCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.utf8BomOptionProvider"/></include> | [`Utf8BomOptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/Utf8BomOptionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vfs.local.fileOperationsHandler"/></include> | [`LocalFileOperationsHandler`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/LocalFileOperationsHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vfs.local.pluggableFileWatcher"/></include> ![Non-Dynamic][non-dynamic] | [`PluggableFileWatcher`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/vfs/local/PluggableFileWatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFileCustomDataConsumer"/></include> ![Experimental][experimental] | [`VirtualFileCustomDataConsumer`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileCustomDataConsumer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFileCustomDataProvider"/></include> ![Experimental][experimental] | [`VirtualFileCustomDataProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileCustomDataProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.warmupConfigurator"/></include> ![Deprecated][deprecated] ![Removal][removal] | [`WarmupConfigurator`](%gh-ic%/platform/platform-api/src/com/intellij/ide/warmup/WarmupConfigurator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webBrowserUrlProvider"/></include> ![DumbAware][dumb-aware] | [`WebBrowserUrlProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/WebBrowserUrlProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webHelpProvider"/></include> | [`WebHelpProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.welcome.projectDetector"/></include> | [`ProjectDetector`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/welcomeScreen/ProjectDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.welcomeFrameProvider"/></include> | [`WelcomeFrameProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeFrameProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.welcomeScreen"/></include> ![Non-Dynamic][non-dynamic] | [`WelcomeScreenProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.welcomeScreenCustomization"/></include> ![Experimental][experimental] | [`WelcomeScreenCustomization`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeScreenCustomization.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.welcomeTabFactory"/></include> | [`WelcomeTabFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeTabFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.javaScriptDebuggerStarter"/></include> | [`JavaScriptDebuggerStarter`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/browsers/JavaScriptDebuggerStarter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.urlOpener"/></include> | [`UrlOpener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/UrlOpener.java) |

### PlatformLangComponents.xml

[`PlatformLangComponents.xml`](%gh-ic%/platform/platform-resources/src/META-INF/PlatformLangComponents.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.diagnostic.freezeNotifier"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`FreezeNotifier`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/FreezeNotifier.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.registerToolWindowTaskProvider"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] | [`RegisterToolWindowTaskProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/toolWindow/toolwindow.kt) |

### PlatformWarmup.xml

[`PlatformWarmup.xml`](%gh-ic%/platform/warmup/resources/META-INF/PlatformWarmup.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectBuildWarmupSupport"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`ProjectBuildWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectBuildWarmupSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectIndexesWarmupSupport"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`ProjectIndexesWarmupSupport`](%gh-ic%/platform/warmup/src/com/intellij/warmup/ProjectIndexesWarmupSupport.kt) |

### ProjectModel.xml

[`ProjectModel.xml`](%gh-ic%/platform/projectModel-api/resources/META-INF/ProjectModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customLibraryTable"/></include> ![Internal][internal] | [`CustomLibraryTableDescription`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/CustomLibraryTableDescription.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePropertyPusher"/></include> ![Experimental][experimental] | [`FilePropertyPusher`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/impl/FilePropertyPusher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleExtension"/></include> | [`ModuleExtension`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.orderEnumerationHandlerFactory"/></include> | [`Factory`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderEnumerationHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.orderRootType"/></include> ![Non-Dynamic][non-dynamic] | [`OrderRootType`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/OrderRootType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.primaryModuleManager"/></include> | [`PrimaryModuleManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/module/PrimaryModuleManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectFileScanner"/></include> ![Internal][internal] | [`IndexableFileScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/roots/IndexableFileScanner.java) |

### ProjectModelImpl.xml

[`ProjectModelImpl.xml`](%gh-ic%/platform/projectModel-impl/resources/META-INF/ProjectModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.additionalLibraryRootsProvider"/></include> | [`AdditionalLibraryRootsProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pathMacroSubstitutorProvider"/></include> ![Internal][internal] | [`PathMacroSubstitutorProvider`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/components/impl/PathMacroSubstitutorProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectExtension"/></include> ![Internal][internal] ![Project-Level][project-level] | [`ProjectExtension`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/ProjectExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectPathMacroContributor"/></include> ![Internal][internal] | [`ProjectWidePathMacroContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/components/impl/ProjectWidePathMacroContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.facetContributor"/></include> ![Internal][internal] | [`WorkspaceFacetContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/workspaceModel/ide/legacyBridge/WorkspaceFacetContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.moduleExtensionBridgeFactory"/></include> ![Internal][internal] | [`ModuleExtensionBridgeFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/ide/legacyBridge/ModuleExtensionBridgeFactory.kt) |

### RefactoringExtensionPoints.xml

[`RefactoringExtensionPoints.xml`](%gh-ic%/platform/refactoring/resources/META-INF/RefactoringExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.automaticRenamerFactory"/></include> | [`AutomaticRenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/naming/AutomaticRenamerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.findInProjectSearchEngine"/></include> ![Experimental][experimental] | [`FindInProjectSearchEngine`](%gh-ic%/platform/refactoring/src/com/intellij/find/FindInProjectSearchEngine.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.inlineActionHandler"/></include> | [`InlineActionHandler`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/InlineActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.namesValidator"/></include> | [`NamesValidator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/refactoring/NamesValidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.refactoringSupport"/></include> | [`RefactoringSupportProvider`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nameSuggestionProvider"/></include> | [`NameSuggestionProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.qualifiedNameProvider"/></include> | [`QualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/QualifiedNameProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.elementListenerProvider"/></include> ![Project-Level][project-level] | [`RefactoringElementListenerProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/refactoring/listeners/RefactoringElementListenerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.helper"/></include> | [`RefactoringHelper`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/RefactoringHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.inlineHandler"/></include> | [`InlineHandler`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/InlineHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rename.inplace.resolveSnapshotProvider"/></include> | [`ResolveSnapshotProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/ResolveSnapshotProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renameHandler"/></include> | [`RenameHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renameInputValidator"/></include> | [`RenameInputValidator`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameInputValidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renamePsiElementProcessor"/></include> | [`RenamePsiElementProcessorBase`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenamePsiElementProcessorBase.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renameRefactoringDialogProvider"/></include> | [`RenameRefactoringDialogProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameRefactoringDialogProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renamerFactory"/></include> ![Experimental][experimental] | [`RenamerFactory`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenamerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testSourcesFilter"/></include> | [`TestSourcesFilter`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.updateAddedFileProcessor"/></include> | [`UpdateAddedFileProcessor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/file/UpdateAddedFileProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vetoRenameCondition"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.virtualFileQualifiedNameProvider"/></include> | [`VirtualFileQualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/VirtualFileQualifiedNameProvider.java) |

### RefactoringLangExtensionPoints.xml

[`RefactoringLangExtensionPoints.xml`](%gh-ic%/platform/platform-resources/src/META-INF/RefactoringLangExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.changeSignature.usageProvider"/></include> ![Experimental][experimental] | [`ChangeSignatureUsageProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/changeSignature/ChangeSignatureUsageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.changeSignatureDetector"/></include> | [`LanguageChangeSignatureDetector`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/changeSignature/inplace/LanguageChangeSignatureDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.refactoringSupport.classMembersRefactoringSupport"/></include> | [`ClassMembersRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/classMembers/ClassMembersRefactoringSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moveFileHandler"/></include> | [`MoveFileHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/moveFilesOrDirectories/MoveFileHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.changeSignatureUsageProcessor"/></include> | [`ChangeSignatureUsageProcessor`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/changeSignature/ChangeSignatureUsageProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.copyHandler"/></include> | [`CopyHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/copy/CopyHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.extractIncludeHandler"/></include> | [`RefactoringActionHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/RefactoringActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.introduceParameterObject"/></include> | [`IntroduceParameterObjectDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/introduceParameterObject/IntroduceParameterObjectDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.invertBoolean"/></include> | [`InvertBooleanDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/invertBoolean/InvertBooleanDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveDirectoryWithClassesHelper"/></include> | [`MoveDirectoryWithClassesHelper`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveDirectoryWithClassesHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveHandler"/></include> | [`MoveHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/move/MoveHandlerDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.pushDown"/></include> | [`PushDownDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/memberPushDown/PushDownDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.safeDeleteProcessor"/></include> | [`SafeDeleteProcessorDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/safeDelete/SafeDeleteProcessorDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.rename.symbolRenameTargetFactory"/></include> | [`SymbolRenameTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/symbol/SymbolRenameTargetFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.renameFileActionProvider"/></include> | [`RenameFileActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/actions/RenameFileActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.suggestedRefactoringSupport"/></include> | [`SuggestedRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/suggested/SuggestedRefactoringSupport.kt) |

### RegExpPlugin.xml

[`RegExpPlugin.xml`](%gh-ic%/RegExpSupport/resources/META-INF/RegExpPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.regExpCapabilitiesProvider"/></include> | [`RegExpCapabilitiesProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpCapabilitiesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.regExpLanguageHost"/></include> | [`RegExpLanguageHost`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpLanguageHost.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.regExpMatcherProvider"/></include> | [`RegExpMatcherProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpMatcherProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.regExpModifierProvider"/></include> | [`RegExpModifierProvider`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpModifierProvider.java) |

### RemoteDevUtil.xml

[`RemoteDevUtil.xml`](%gh-ic%/platform/remoteDev-util/resources/META-INF/RemoteDevUtil.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteDev.configureClientHook"/></include> | [`ConfigureClientHook`](%gh-ic%/platform/remoteDev-util/src/com/intellij/remoteDev/downloader/ConfigureClientHook.kt) |

### smRunner.xml

[`smRunner.xml`](%gh-ic%/platform/smRunner/resources/META-INF/smRunner.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.importTestOutput"/></include> | [`ImportTestOutputExtension`](%gh-ic%/platform/smRunner/src/com/intellij/execution/testframework/sm/runner/history/ImportTestOutputExtension.java) |

### SpellCheckerPlugin.xml

[`SpellCheckerPlugin.xml`](%gh-ic%/spellchecker/resources/META-INF/SpellCheckerPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.builtInDictionariesProvider"/></include> ![Internal][internal] | [`BuiltInDictionariesProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/settings/BuiltInDictionariesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.bundledDictionaryProvider"/></include> | [`BundledDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/BundledDictionaryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.dictionary.checker"/></include> | [`DictionaryChecker`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/DictionaryChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.dictionary.customDictionaryProvider"/></include> | [`CustomDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/CustomDictionaryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.dictionary.runtimeDictionaryProvider"/></include> | [`RuntimeDictionaryProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/dictionary/RuntimeDictionaryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.dictionaryLayersProvider"/></include> ![Internal][internal] | [`DictionaryLayersProvider`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/DictionaryLevel.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.lifecycle"/></include> ![Experimental][experimental] | [`SpellcheckerLifecycle`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/grazie/SpellcheckerLifecycle.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.quickFixFactory"/></include> ![Internal][internal] | [`SpellCheckerQuickFixFactory`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/quickfixes/SpellCheckerQuickFixFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spellchecker.support"/></include> ![DumbAware][dumb-aware] | [`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java) |

### structuralsearch.xml

[`structuralsearch.xml`](%gh-ic%/platform/structuralsearch/resources/META-INF/structuralsearch.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.structuralsearch.filterProvider"/></include> | [`FilterProvider`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/plugin/ui/filters/FilterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.structuralsearch.profile"/></include> | [`StructuralSearchProfile`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/StructuralSearchProfile.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.structuralsearch.specialXmlTagExtractor"/></include> | [`SpecialElementExtractor`](%gh-ic%/platform/structuralsearch/source/com/intellij/structuralsearch/SpecialElementExtractor.java) |

### tasks.xml

[`tasks.xml`](%gh-ic%/platform/tasks-platform-impl/resources/META-INF/tasks.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tasks.contextProvider"/></include> | [`WorkingContextProvider`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/context/WorkingContextProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tasks.repositoryType"/></include> | [`TaskRepositoryType`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/TaskRepositoryType.java) |

### UsageViewActions.xml

[`UsageViewActions.xml`](%gh-ic%/platform/usageView/resources/idea/UsageViewActions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generatedSourceUsageFilter"/></include> | [`GeneratedSourceUsageFilter`](%gh-ic%/platform/usageView/src/com/intellij/usages/rules/GeneratedSourceUsageFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.usages.usageReferenceClassProvider"/></include> ![Non-Dynamic][non-dynamic] | [`UsageReferenceClassProvider`](%gh-ic%/platform/usageView/src/com/intellij/usages/impl/UsageReferenceClassProvider.kt) |

### WebSymbolsExtensionPoints.xml

[`WebSymbolsExtensionPoints.xml`](%gh-ic%/platform/webSymbols/resources/META-INF/WebSymbolsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.codeCompletionItemCustomizer"/></include> | [`WebSymbolCodeCompletionItemCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/completion/WebSymbolCodeCompletionItemCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.context"/></include> | [`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.contextSourceProximityProvider"/></include> | [`WebSymbolsContextSourceProximityProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextSourceProximityProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.declarationProvider"/></include> | [`WebSymbolDeclarationProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclarationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.defaultIconProvider"/></include> | [`WebSymbolDefaultIconProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolDefaultIconProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.documentationCustomizer"/></include> | [`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.enableInLanguage"/></include> ![Experimental][experimental] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.framework"/></include> | [`WebSymbolsFramework`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/framework/WebSymbolsFramework.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.highlightingCustomizer"/></include> | [`WebSymbolHighlightingCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/highlighting/WebSymbolHighlightingCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.inspectionToolMapping"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.problemQuickFixProvider"/></include> | [`WebSymbolsProblemQuickFixProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/inspections/WebSymbolsProblemQuickFixProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.psiReferenceProvider"/></include> | [`PsiWebSymbolReferenceProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/references/PsiWebSymbolReferenceProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.psiSourcedSymbol"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.psiSourcedSymbolProvider"/></include> | [`PsiSourcedWebSymbolProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbolProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.queryConfigurator"/></include> | [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.queryResultsCustomizerFactory"/></include> | [`WebSymbolsQueryResultsCustomizerFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryResultsCustomizerFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.webTypes"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.webTypes.filter"/></include> | [`WebSymbolsFilter`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/filters/WebSymbolsFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.webTypes.symbolFactory"/></include> | [`WebTypesSymbolFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/WebTypesSymbolFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSymbols.webTypes.symbolTypeSupportFactory"/></include> ![Internal][internal] | [`WebTypesSymbolTypeSupportFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/webTypes/WebTypesSymbolTypeSupportFactory.kt) |

### WorkspaceModel.xml

[`WorkspaceModel.xml`](%gh-ic%/platform/projectModel-api/resources/META-INF/WorkspaceModel.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspace.bridgeInitializer"/></include> ![Internal][internal] | [`BridgeInitializer`](%gh-ic%/platform/backend/workspace/src/BridgeInitializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.preUpdateHandler"/></include> ![Internal][internal] | [`WorkspaceModelPreUpdateHandler`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelPreUpdateHandler.kt) |

### WorkspaceModelExtensions.xml

[`WorkspaceModelExtensions.xml`](%gh-ic%/platform/workspace/jps/resources/META-INF/WorkspaceModelExtensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.customFacetRelatedEntitySerializer"/></include> ![Internal][internal] | [`CustomFacetRelatedEntitySerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomFacetRelatedEntitySerializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.customModuleComponentSerializer"/></include> ![Internal][internal] | [`CustomModuleComponentSerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomModuleComponentSerializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.customModuleRootsSerializer"/></include> ![Internal][internal] | [`CustomModuleRootsSerializer`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/serialization/impl/CustomModuleRootsSerializer.kt) |

### WorkspaceModelImpl.xml

[`WorkspaceModelImpl.xml`](%gh-ic%/platform/projectModel-impl/resources/META-INF/WorkspaceModelImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.directoryIndexExcludePolicy"/></include> ![Project-Level][project-level] | [`DirectoryIndexExcludePolicy`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/DirectoryIndexExcludePolicy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.entityLifecycleSupporter"/></include> ![Experimental][experimental] | [`WorkspaceEntityLifecycleSupporter`](%gh-ic%/platform/backend/workspace/src/WorkspaceEntityLifecycleSupporter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.fileIndexContributor"/></include> ![Non-Dynamic][non-dynamic] | [`WorkspaceFileIndexContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/WorkspaceFileIndexContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.workspaceModel.optionalExclusionContributor"/></include> ![Experimental][experimental] ![Internal][internal] | [`OptionalExclusionContributor`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/OptionalExclusionContributor.kt) |

### xdebugger.xml

[`xdebugger.xml`](%gh-ic%/platform/xdebugger-impl/resources/META-INF/xdebugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.attachDebuggerProvider"/></include> | [`XAttachDebuggerProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachDebuggerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.attachHostProvider"/></include> | [`XAttachHostProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/attach/XAttachHostProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.attachHostSettingsProvider"/></include> | [`XAttachHostSettingsProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachHostSettingsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.breakpointGroupingRule"/></include> | [`XBreakpointGroupingRule`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/ui/XBreakpointGroupingRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.breakpointType"/></include> | [`XBreakpointType`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/XBreakpointType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.configurableProvider"/></include> | [`DebuggerConfigurableProvider`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/DebuggerConfigurableProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.controlExceptionBreakpointSupport"/></include> | [`ControlExceptionBreakpointSupport`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/codeinsight/ControlExceptionBreakpointSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.debuggerSupport"/></include> ![Deprecated][deprecated] | [`DebuggerSupport`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/DebuggerSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.dialog.item.presentation.provider"/></include> ![Experimental][experimental] | [`XAttachDialogItemPresentationProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachDialogItemPresentationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.dialog.presentation.provider"/></include> ![Experimental][experimental] | [`XAttachDialogPresentationProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachDialogPresentationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.dialog.process.view.provider"/></include> ![Internal][internal] | [`XAttachToProcessViewProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/ui/attach/dialog/extensions/XAttachToProcessViewProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.hotSwapUiExtension"/></include> ![Internal][internal] | [`HotSwapUiExtension`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/hotswap/ui.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.inlineBreakpointsDisabler"/></include> ![Experimental][experimental] | [`InlineBreakpointsDisabler`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/breakpoints/InlineBreakpointsDisabler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.inlineValuePopupProvider"/></include> | [`InlineValuePopupProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/inline/InlineValuePopupProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.nodeLinkActionProvider"/></include> ![Experimental][experimental] | [`XDebuggerNodeLinkActionProvider`](%gh-ic%/platform/xdebugger-impl/src/com/intellij/xdebugger/impl/collection/visualizer/XDebuggerNodeLinkActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.settings"/></include> | [`XDebuggerSettings`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/settings/XDebuggerSettings.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xdebugger.textValueVisualizer"/></include> ![Experimental][experimental] | [`TextValueVisualizer`](%gh-ic%/platform/xdebugger-api/src/com/intellij/xdebugger/ui/TextValueVisualizer.kt) |

### xml.xml

[`xml.xml`](%gh-ic%/xml/impl/resources/META-INF/xml.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.htmlInTextCompletionEnabler"/></include> ![Internal][internal] | [`HtmlInTextCompletionEnabler`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionEnabler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.htmlInTextCompletionPopupExtension"/></include> ![Internal][internal] | [`HtmlInTextCompletionPopupExtension`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/HtmlInTextCompletionPopupExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.compatibleLanguage"/></include> ![Experimental][experimental] | `n/a` |

### XmlPlugin.xml

[`XmlPlugin.xml`](%gh-ic%/platform/platform-resources/src/META-INF/XmlPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.embeddedTokenHighlighter"/></include> | [`EmbeddedTokenHighlighter`](%gh-ic%/xml/xml-frontback-impl/src/com/intellij/ide/highlighter/EmbeddedTokenHighlighter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.embeddedTokenTypesProvider"/></include> | [`EmbeddedTokenTypesProvider`](%gh-ic%/xml/xml-parser/src/com/intellij/lexer/EmbeddedTokenTypesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.attributeValueProvider"/></include> | [`HtmlAttributeValueProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/html/impl/providers/HtmlAttributeValueProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.codestyle.panel"/></include> | [`HtmlCodeStylePanelExtension`](%gh-ic%/xml/impl/src/com/intellij/application/options/HtmlCodeStylePanelExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.embeddedContentSupport"/></include> | [`HtmlEmbeddedContentSupport`](%gh-ic%/xml/xml-parser/src/com/intellij/html/embedding/HtmlEmbeddedContentSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.htmlScriptInjectionBlocker"/></include> | [`HtmlScriptInjectionBlocker`](%gh-ic%/xml/impl/src/com/intellij/psi/impl/source/html/HtmlScriptInjectionBlocker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.scriptContentProvider"/></include> | [`HtmlScriptContentProvider`](%gh-ic%/xml/xml-parser/src/com/intellij/lang/HtmlScriptContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.html.scriptDocumentationProvider"/></include> ![Obsolete][obsolete] | [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.standardResource"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.standardResourceProvider"/></include> | [`StandardResourceProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/javaee/StandardResourceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.webSmartKeysConfigurable"/></include> | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.attributeDescriptorsProvider"/></include> | [`XmlAttributeDescriptorsProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlAttributeDescriptorsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.autoPopupEnabler"/></include> ![Experimental][experimental] | [`XmlAutoPopupEnabler`](%gh-ic%/xml/xml-psi-api/src/com/intellij/xml/psi/codeInsight/XmlAutoPopupEnabler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.elementDescriptorProvider"/></include> | [`XmlElementDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlElementDescriptorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.fileNSInfoProvider"/></include> | [`XmlFileNSInfoProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFileNSInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.idContributor"/></include> | [`XmlIdContributor`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/util/XmlIdContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.implicitIdRefProvider"/></include> | [`ImplicitIdRefProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/resolve/reference/impl/providers/ImplicitIdRefProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.implicitNamespaceDescriptorProvider"/></include> | [`ImplicitNamespaceDescriptorProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ImplicitNamespaceDescriptorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.namedReferenceProvider"/></include> ![Experimental][experimental] | [`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.namespaceHelper"/></include> | [`XmlNamespaceHelper`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlNamespaceHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.nsColorProvider"/></include> | [`XmlNSColorProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/XmlNSColorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.psiPolicy"/></include> | [`XmlPsiPolicy`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/impl/source/xml/XmlPsiPolicy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.relatedToHtmlFilesContributor"/></include> | [`RelatedToHtmlFilesContributor`](%gh-ic%/xml/impl/src/com/intellij/navigation/RelatedToHtmlFilesContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.schemaProvider"/></include> ![DumbAware][dumb-aware] | [`XmlSchemaProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/xml/XmlSchemaProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.startTagEndToken"/></include> | [`StartTagEndTokenProvider`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/psi/xml/StartTagEndTokenProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.tagNameProvider"/></include> | [`XmlTagNameProvider`](%gh-ic%/xml/impl/src/com/intellij/xml/XmlTagNameProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.undefinedElementFixProvider"/></include> | [`XmlUndefinedElementFixProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/xml/XmlUndefinedElementFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.validateHandler"/></include> | [`ValidateXmlHandler`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/actions/validate/ValidateXmlHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlAttributeRenameProvider"/></include> | [`XmlUnknownAttributeQuickFixProvider`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInspection/htmlInspections/XmlUnknownAttributeQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlCustomTagHighlightingStrategy"/></include> ![Experimental][experimental] | [`XmlCustomTagHighlightingStrategy`](%gh-ic%/xml/xml-psi-api/src/com/intellij/openapi/editor/XmlCustomTagHighlightingStrategy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlExtension"/></include> | [`XmlExtension`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/xml/XmlExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlSuppressionProvider"/></include> ![DumbAware][dumb-aware] | [`XmlSuppressionProvider`](%gh-ic%/xml/xml-psi-api/src/com/intellij/codeInspection/XmlSuppressionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlTagRuleProvider"/></include> | [`XmlTagRuleProvider`](%gh-ic%/xml/xml-analysis-api/src/com/intellij/xml/XmlTagRuleProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.xmlTypedHandlersAdditionalSupport"/></include> | [`XmlTypedHandlersAdditionalSupport`](%gh-ic%/xml/xml-psi-api/src/com/intellij/openapi/editor/XmlTypedHandlersAdditionalSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.zenCodingFilter"/></include> | [`ZenCodingFilter`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/filters/ZenCodingFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xml.zenCodingGenerator"/></include> | [`ZenCodingGenerator`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/template/emmet/generators/ZenCodingGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xmlStructureViewBuilderProvider"/></include> | [`XmlStructureViewBuilderProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewBuilderProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.xmlStructureViewElementProvider"/></include> | [`XmlStructureViewElementProvider`](%gh-ic%/xml/xml-structure-view-api/src/com/intellij/ide/structureView/xml/XmlStructureViewElementProvider.java) |


## External System

### External System – Listeners

| Topic | Listener |
|-------|----------|
| [`ExternalSystemProjectNotificationAware#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectNotificationAware.Listener)  | [`Listener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autoimport/ExternalSystemProjectNotificationAware.kt) |
| [`ProjectDataImportListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener)  ![Project-Level][project-level] | [`ProjectDataImportListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataImportListener.java) |
| [`ExternalSystemTestUtil#SETTINGS_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.platform.externalSystem.testFramework.TestExternalSystemSettingsListener)  | [`TestExternalSystemSettingsListener`](%gh-ic%/platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/TestExternalSystemSettingsListener.java) |


### ExternalSystemDependencyUpdater.xml

[`ExternalSystemDependencyUpdater.xml`](%gh-ic%/platform/external-system-api/dependency-updater/resources/META-INF/ExternalSystemDependencyUpdater.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.dependencyModifier"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`ExternalDependencyModificator`](%gh-ic%/platform/external-system-api/dependency-updater/src/com/intellij/externalSystem/ExternalDependencyModificator.java) |

### ExternalSystemExtensionPoints.xml

[`ExternalSystemExtensionPoints.xml`](%gh-ic%/platform/external-system-impl/resources/META-INF/ExternalSystemExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalExecutionAware"/></include> ![Experimental][experimental] | [`ExternalSystemExecutionAware`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemExecutionAware.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalIconProvider"/></include> | [`ExternalSystemIconProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemIconProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalProjectDataService"/></include> | [`ProjectDataService`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataService.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalProjectStructureCustomizer"/></include> | [`ExternalProjectStructureCustomizer`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/importing/ExternalProjectStructureCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalProjectWatcherContributor"/></include> ![Deprecated][deprecated] | [`Contributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/autoimport/ExternalSystemProjectsWatcherImpl.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.beforeRunTaskImporter"/></include> | [`BeforeRunTaskImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/BeforeRunTaskImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.debuggerBackend"/></include> | [`DebuggerBackendExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/debugger/DebuggerBackendExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.facetConfigurationImporter"/></include> | [`FacetConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/FacetConfigurationImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.modifiableModelsProvider"/></include> ![Experimental][experimental] | [`ModifiableModelsProviderExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ModifiableModelsProviderExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.runConfigurationEx"/></include> | [`ExternalSystemRunConfigurationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/execution/configuration/ExternalSystemRunConfigurationExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystem.runConfigurationImporter"/></include> | [`RunConfigurationImporter`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/RunConfigurationImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemConfigLocator"/></include> | [`ExternalSystemConfigLocator`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/settings/ExternalSystemConfigLocator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemConfigurationHandler"/></include> ![Experimental][experimental] | [`ConfigurationHandler`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/settings/ConfigurationHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemContentRootContributor"/></include> | [`ExternalSystemContentRootContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemContentRootContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemCoordinateContributor"/></include> | [`ExternalSystemCoordinateContributor`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/ExternalSystemCoordinateContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemCrcCalculator"/></include> ![Experimental][experimental] | [`ExternalSystemCrcCalculator`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/util/ExternalSystemCrcCalculator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemDependencyAnalyzer"/></include> | [`DependencyAnalyzerExtension`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/dependency/analyzer/DependencyAnalyzerExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemExecutionConsoleManager"/></include> | [`ExternalSystemExecutionConsoleManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/execution/ExternalSystemExecutionConsoleManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemKeymapProvider"/></include> | [`ActionsProvider`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemKeymapExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemManager"/></include> | [`ExternalSystemManager`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemNotificationExtension"/></include> | [`ExternalSystemNotificationExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/notification/ExternalSystemNotificationExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemOutputDispatcher"/></include> | [`ExternalSystemOutputDispatcherFactory`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputDispatcherFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemOutputParserProvider"/></include> | [`ExternalSystemOutputParserProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/execution/ExternalSystemOutputParserProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemRecoveryContributor"/></include> | [`Factory`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemRecoveryContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemSettingsListener"/></include> | [`ExternalSystemSettingsListenerEx`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/settings/ExternalSystemSettingsListenerEx.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemTaskNotificationListener"/></include> | [`ExternalSystemTaskNotificationListener`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/task/ExternalSystemTaskNotificationListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemTaskProgressIndicatorUpdater"/></include> | [`ExternalSystemTaskProgressIndicatorUpdater`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/ExternalSystemTaskProgressIndicatorUpdater.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemUnlinkedProjectAware"/></include> | [`ExternalSystemUnlinkedProjectAware`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/autolink/ExternalSystemUnlinkedProjectAware.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalSystemViewContributor"/></include> | [`ExternalSystemViewContributor`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/view/ExternalSystemViewContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalTextProvider"/></include> | [`ExternalSystemTextProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemTextProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalWorkspaceDataService"/></include> ![Experimental][experimental] | [`WorkspaceDataService`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/WorkspaceDataService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.libraryDataServiceExtension"/></include> ![Internal][internal] | [`LibraryDataServiceExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/LibraryDataServiceExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.externalSystem.autoimport.autoReloadTypeProviderExtension"/></include> ![Internal][internal] | [`DefaultAutoReloadTypeProvider`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/autoimport/DefaultAutoReloadTypeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.externalSystem.projectSetupExtension"/></include> ![Internal][internal] | [`ExternalSystemProjectSetupExtension`](%gh-ic%/platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/project/manage/ExternalSystemProjectSetupExtension.kt) |


## Version Control

### Version Control – Listeners

| Topic | Listener |
|-------|----------|
| [`DvcsBranchManager#DVCS_BRANCH_SETTINGS_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.branch.DvcsBranchManager.DvcsBranchManagerListener)  | [`DvcsBranchManagerListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/branch/DvcsBranchManager.java) |
| [`VcsRepositoryManager#VCS_REPOSITORY_MAPPING_UPDATED`](https://jb.gg/ipe/listeners?topics=com.intellij.dvcs.repo.VcsRepositoryMappingListener)  ![Project-Level][project-level] | [`VcsRepositoryMappingListener`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryMappingListener.java) |
| [`BranchChangeListener#VCS_BRANCH_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchChangeListener)  | [`BranchChangeListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchChangeListener.java) |
| [`BranchRenameListener#VCS_BRANCH_RENAMED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.BranchRenameListener)  | [`BranchRenameListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/BranchRenameListener.java) |
| [`ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED_IN_PLUGIN`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.PluginVcsMappingListener)  ![Project-Level][project-level] | [`PluginVcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/PluginVcsMappingListener.java) |
| [`ProjectLevelVcsManager#VCS_CONFIGURATION_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.VcsMappingListener)  ![Project-Level][project-level] | [`VcsMappingListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsMappingListener.java) |
| [`ChangeListAvailabilityListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListAvailabilityListener)  ![Internal][internal] | [`ChangeListAvailabilityListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListAvailabilityListener.java) |
| [`ChangeListListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangeListListener)  | [`ChangeListListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListListener.java) |
| [`ChangesViewModifier#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewModifier.ChangesViewModifierListener)  ![Project-Level][project-level] | [`ChangesViewModifierListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| [`ChangesViewWorkflowManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ChangesViewWorkflowManager.ChangesViewWorkflowListener)  ![Project-Level][project-level] | [`ChangesViewWorkflowListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewWorkflowManager.java) |
| [`ChangeListManagerImpl#LISTS_LOADED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.LocalChangeListsLoadedListener)  ![Project-Level][project-level] | [`LocalChangeListsLoadedListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalChangeListsLoadedListener.java) |
| [`VcsAnnotationRefresher#LOCAL_CHANGES_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsAnnotationRefresher)  | [`VcsAnnotationRefresher`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsAnnotationRefresher.java) |
| [`VcsEditorTabFilesListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsEditorTabFilesListener)  ![Internal][internal] | [`VcsEditorTabFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsEditorTabFilesManager.kt) |
| [`Listener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsFreezingProcess.Listener)  | [`Listener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsFreezingProcess.kt) |
| [`VcsManagedFilesHolder#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.VcsManagedFilesHolder.VcsManagedFilesHolderListener)  ![Project-Level][project-level] | [`VcsManagedFilesHolderListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| [`CommittedChangesCache#COMMITTED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesListener)  ![Project-Level][project-level] | [`CommittedChangesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesListener.java) |
| [`CommittedChangesTreeBrowser#ITEMS_RELOADED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.CommittedChangesTreeBrowser.CommittedChangesReloadListener)  ![Project-Level][project-level] | [`CommittedChangesReloadListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/CommittedChangesTreeBrowser.java) |
| [`VcsConfigurationChangeListener#BRANCHES_CHANGED_RESPONSE`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.DetailedNotification)  ![Project-Level][project-level] | [`DetailedNotification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [`VcsConfigurationChangeListener#BRANCHES_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener.Notification)  ![Project-Level][project-level] | [`Notification`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/committed/VcsConfigurationChangeListener.java) |
| [`ShelveChangesManager#SHELF_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.shelf.ShelveChangesManagerListener)  ![Internal][internal] ![Project-Level][project-level] | [`ShelveChangesManagerListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveChangesManagerListener.java) |
| [`ChangesViewContentManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.changes.ui.ChangesViewContentManagerListener)  ![Project-Level][project-level] | [`ChangesViewContentManagerListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentManagerListener.kt) |
| [`ProjectLevelVcsManagerEx#VCS_ACTIVATED`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.ex.VcsActivationListener)  ![Project-Level][project-level] | [`VcsActivationListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/ex/VcsActivationListener.java) |
| [`LineStatusTrackerManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.LineStatusTrackerManager.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| [`VcsBaseContentProviderListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.impl.VcsBaseContentProviderListener)  ![Project-Level][project-level] | [`VcsBaseContentProviderListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProviderListener.java) |
| [`UpdatedFilesListener#UPDATED_FILES`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.vcs.update.UpdatedFilesListener)  ![Project-Level][project-level] | [`UpdatedFilesListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/update/UpdatedFilesListener.java) |
| [`CommitModeManager#COMMIT_MODE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.CommitModeListener)  ![Project-Level][project-level] | [`CommitModeListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [`CommitModeManager#SETTINGS`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.CommitModeManager.SettingsListener)  | [`SettingsListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/CommitModeManager.kt) |
| [`CommitMessageInspectionProfile#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.commit.message.CommitMessageInspectionProfile.ProfileListener)  | [`ProfileListener`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/message/CommitMessageInspectionProfile.java) |
| [`VcsProjectLog#VCS_PROJECT_LOG_CHANGED`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.impl.VcsProjectLog.ProjectLogListener)  ![Project-Level][project-level] | [`ProjectLogListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/VcsProjectLog.kt) |
| [`VcsLogBookmarksListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.VcsLogBookmarksListener)  | [`VcsLogBookmarksListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/VcsLogBookmarkProvider.kt) |
| [`VcsLogCustomColumnListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.table.column.VcsLogCustomColumnListener)  | [`VcsLogCustomColumnListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |
| [`CommitLinksResolveListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.vcs.log.ui.table.links.CommitLinksResolveListener)  ![Experimental][experimental] ![Project-Level][project-level] | [`CommitLinksResolveListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/links/CommitLinksProvider.kt) |
| [`RemoteRevisionsCache#REMOTE_VERSION_CHANGED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |


### intellij.platform.lvcs.impl.xml

[`intellij.platform.lvcs.impl.xml`](%gh-ic%/platform/lvcs-impl/resources/intellij.platform.lvcs.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.history.activityPresentationProvider"/></include> ![Experimental][experimental] | [`ActivityPresentationProvider`](%gh-ic%/platform/lvcs-api/src/com/intellij/history/ActivityPresentationProvider.kt) |

### intellij.platform.vcs.dvcs.impl.xml

[`intellij.platform.vcs.dvcs.impl.xml`](%gh-ic%/platform/dvcs-impl/resources/intellij.platform.vcs.dvcs.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cherryPicker"/></include> ![Project-Level][project-level] | [`VcsCherryPicker`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/cherrypick/VcsCherryPicker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.clonePathProvider"/></include> | [`ClonePathProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/ClonePathProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commitModeProvider"/></include> ![Internal][internal] | [`DvcsCommitModeProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/commit/DvcsCommitModeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.commitNodeUiRenderExtension"/></include> ![Experimental][experimental] | [`CommitNodeUiRenderExtension`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/ui/CommitNodeUiRenderExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customPushOptionsPanelFactory"/></include> ![Experimental][experimental] | [`CustomPushOptionsPanelFactory`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/push/CustomPushOptionsPanelFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.prePushHandler"/></include> | [`PrePushHandler`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PrePushHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pushDialogActionsProvider"/></include> ![Internal][internal] | [`PushDialogActionsProvider`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogActionsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pushDialogCustomizer"/></include> ![Internal][internal] | [`PushDialogCustomizer`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/push/PushDialogCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.pushSupport"/></include> ![Project-Level][project-level] | [`PushSupport`](%gh-ic%/platform/dvcs-api/src/com/intellij/dvcs/push/PushSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsRepositoryCreator"/></include> | [`VcsRepositoryCreator`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/repo/VcsRepositoryCreator.java) |

### intellij.platform.vcs.impl.backend.xml

[`intellij.platform.vcs.impl.backend.xml`](%gh-ic%/platform/vcs-impl/backend/resources/intellij.platform.vcs.impl.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.impl.backend.treeNodeConverter"/></include> ![Internal][internal] | [`NodeToEntityConverter`](%gh-ic%/platform/vcs-impl/backend/src/com/intellij/platform/vcs/impl/backend/shelf/ChangesBrowserNodeConverter.kt) |

### intellij.platform.vcs.log.impl.xml

[`intellij.platform.vcs.log.impl.xml`](%gh-ic%/platform/vcs-log/impl/resources/intellij.platform.vcs.log.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customVcsLogUiFactoryProvider"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`CustomVcsLogUiFactoryProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/impl/CustomVcsLogUiFactoryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.fileHistoryPerformanceListener"/></include> ![Internal][internal] | [`FileHistoryPerformanceListener`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/history/FileHistoryPerformanceListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.logHighlighterFactory"/></include> | [`VcsLogHighlighterFactory`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/highlighters/VcsLogHighlighterFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.logProvider"/></include> ![Project-Level][project-level] | [`VcsLogProvider`](%gh-ic%/platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsLogCommitStatusProvider"/></include> ![Experimental][experimental] | [`VcsCommitExternalStatusProvider`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/frame/VcsCommitExternalStatusProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsLogCustomColumn"/></include> | [`VcsLogCustomColumn`](%gh-ic%/platform/vcs-log/impl/src/com/intellij/vcs/log/ui/table/column/VcsLogCustomColumn.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsLogFileHistoryHandler"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`VcsLogFileHistoryHandler`](%gh-ic%/platform/vcs-log/api/src/com/intellij/vcs/log/VcsLogFileHistoryHandler.kt) |

### VcsExtensionPoints.xml

[`VcsExtensionPoints.xml`](%gh-ic%/platform/vcs-impl/resources/META-INF/VcsExtensionPoints.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.changesGroupingPolicy"/></include> | [`ChangesGroupingPolicyFactory`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesGroupingPolicyFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.changesViewContent"/></include> ![Project-Level][project-level] | [`ChangesViewContentProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/ChangesViewContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.checkinHandlerFactory"/></include> | [`CheckinHandlerFactory`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkin/CheckinHandlerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.checkoutCompletedListener"/></include> | [`CheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.checkoutListener"/></include> | [`CheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/CheckoutListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.checkoutProvider"/></include> | [`CheckoutProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/CheckoutProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editChangelistSupport"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`EditChangelistSupport`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/EditChangelistSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generalVcsSettingsExtension"/></include> ![Project-Level][project-level] | [`UnnamedConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/UnnamedConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ignoredFileContentProvider"/></include> ![Project-Level][project-level] | [`IgnoredFileContentProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.ignoredFileProvider"/></include> | [`IgnoredFileProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/IgnoredFileProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.actions.AnnotateToggleAction.Provider"/></include> | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/actions/AnnotateToggleAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Clipboard.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.actions.CreatePatchFromChangesAction.Dialog.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider"/></include> | [`ChangeDiffRequestProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffRequestProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffViewerWrapperProvider"/></include> ![Internal][internal] | [`ChangeDiffViewerWrapperProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/diff/ChangeDiffViewerWrapperProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.ui.filePathIconProvider"/></include> | [`FilePathIconProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/FilePathIconProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.changes.vcsPreservingExecutor"/></include> | [`VcsPreservingExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsPreservingExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.history.actions.GetVersionAction.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.history.actions.ShowDiffAfterWithLocalAction.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.history.actions.ShowDiffBeforeWithLocalAction.ExtensionProvider"/></include> | [`AnActionExtensionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/AnActionExtensionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.impl.LocalLineStatusTrackerProvider"/></include> | [`LocalLineStatusTrackerProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/LineStatusTrackerManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.ui.cloneDialog.VcsCloneDialogExtension"/></include> | [`VcsCloneDialogExtension`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/ui/cloneDialog/VcsCloneDialogExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.vcs.ui.commitOptionsDialogExtension"/></include> ![Experimental][experimental] | [`CommitOptionsDialogExtension`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/ui/CommitOptionsDialogExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.patch.extension"/></include> | [`PatchEP`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/diff/impl/patch/PatchEP.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.unresolvedMergeCheckProvider"/></include> | [`UnresolvedMergeCheckProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/checkin/UnresolvedMergeCheckProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs"/></include> | [`AbstractVcs`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/AbstractVcs.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.actions.ScheduleForAdditionActionExtension"/></include> | [`ScheduleForAdditionActionExtension`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/actions/ScheduleForAdditionActionExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.baseContentProvider"/></include> ![Project-Level][project-level] | [`VcsBaseContentProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsBaseContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.branchStateProvider"/></include> ![Project-Level][project-level] | [`BranchStateProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/branch/BranchStateProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.changeListChangeAssigner"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`ChangeListChangeAssigner`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangeListChangeAssigner.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.changeListDecorator"/></include> ![Project-Level][project-level] | [`ChangeListDecorator`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeListDecorator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.changes.changesViewModifier"/></include> ![Project-Level][project-level] | [`ChangesViewModifier`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewModifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.changes.changesViewNodeAction"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`ChangesViewNodeAction`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ChangesViewNodeAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.changes.localCommitExecutor"/></include> ![Project-Level][project-level] | [`CommitExecutor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/CommitExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.checkoutProcessor"/></include> | [`VcsCheckoutProcessor`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsCheckoutProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.codeVisionLanguageContext"/></include> ![Experimental][experimental] | [`VcsCodeVisionLanguageContext`](%gh-ic%/platform/vcs-api/src/com/intellij/codeInsight/hints/VcsCodeVisionLanguageContext.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.commitMessageProvider"/></include> | [`CommitMessageProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ui/CommitMessageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.consoleFolding"/></include> | [`VcsConsoleFolding`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/console/VcsConsoleView.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.defaultCommitMessagePolicy"/></include> ![Internal][internal] | [`DefaultCommitMessagePolicy`](%gh-ic%/platform/vcs-impl/src/com/intellij/vcs/commit/DefaultCommitMessagePolicy.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.diffRevisionMetadataProvider"/></include> | [`DiffRevisionMetadataProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/diff/impl/DiffRevisionMetadataProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.envCustomizer"/></include> | [`VcsEnvCustomizer`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsEnvCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.fileStatusProvider"/></include> ![Project-Level][project-level] | [`FileStatusProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/vcs/impl/FileStatusProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.ignoredFilesHolder"/></include> ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.lineStatusClientIdRenderer"/></include> ![Non-Dynamic][non-dynamic] ![Internal][internal] ![Project-Level][project-level] | [`LineStatusClientIdRenderer`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/ex/LineStatusClientIdRenderer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.shelveSilentlyGotItTooltipProvider"/></include> ![Internal][internal] | [`ShelveSilentlyGotItTooltipProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveSilentlyGotItTooltipProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.shelveSilentlyTitleProvider"/></include> ![Experimental][experimental] | [`ShelveSilentlyTitleProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/shelf/ShelveSilentlyTitleProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.taskHandler"/></include> ![Project-Level][project-level] | [`VcsTaskHandler`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsTaskHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.unversionedFilesHolder"/></include> ![Project-Level][project-level] | [`Provider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/VcsManagedFilesHolder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.vcsSymlinkResolver"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`VcsSymlinkResolver`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/VcsSymlinkResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsAnnotationGutterActionProvider"/></include> | [`AnnotationGutterActionProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterActionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsAnnotationGutterColumnProvider"/></include> | [`AnnotationGutterColumnProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/annotate/AnnotationGutterColumnProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsAwareCheckoutListener"/></include> | [`VcsAwareCheckoutListener`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkout/VcsAwareCheckoutListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsBulkMovesOnlyChangesFilter"/></include> | [`BulkMovesOnlyChangesFilter`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/changes/ui/browser/BulkMovesOnlyChangesFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsChangesViewRefresher"/></include> ![Project-Level][project-level] | [`ChangesViewRefresher`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangesViewRefresher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsCheckinHandlerFactory"/></include> | [`VcsCheckinHandlerFactory`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/checkin/VcsCheckinHandlerFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsConfigurableProvider"/></include> | [`VcsConfigurableProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsConfigurableProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsIgnoreChecker"/></include> | [`VcsIgnoreChecker`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsIgnoreChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsPopupProvider"/></include> | [`VcsQuickListContentProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/actions/VcsQuickListContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsRepositoryInitializer"/></include> | [`VcsRepositoryInitializer`](%gh-ic%/platform/vcs-api/src/com/intellij/vcs/VcsRepositoryInitializer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsRootChecker"/></include> | [`VcsRootChecker`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsRootChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsRootErrorFilter"/></include> ![Internal][internal] | [`VcsRootErrorFilter`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/VcsRootErrorFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsSelectionProvider"/></include> | [`VcsSelectionProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/vcsUtil/VcsSelectionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsSharedChecker"/></include> ![Internal][internal] ![Project-Level][project-level] | [`VcsSharedChecker`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/VcsSharedChecker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcsStartupActivity"/></include> ![Non-Dynamic][non-dynamic] | [`VcsStartupActivity`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/impl/VcsInitialization.kt) |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
