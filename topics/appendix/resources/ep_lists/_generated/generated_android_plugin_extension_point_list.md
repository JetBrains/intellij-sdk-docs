<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /community/android
-->


<snippet id="content">

110 Extension Points and 34 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## Android Plugin

### Android Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`BuildAnalyzerStorageManager.Companion#DATA_IS_READY_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.build.attribution.BuildAnalyzerStorageManager.Listener)  | [`Listener`](%gh-ij-android%/build-attribution/src/com/android/build/attribution/BuildAnalyzerStorageManager.kt) |
| [`StatefulButtonNotifier#BUTTON_STATE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.StatefulButtonNotifier)  ![Project-Level][project-level] | [`StatefulButtonNotifier`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/StatefulButtonNotifier.java) |
| [`TutorialCardRefreshNotifier#TUTORIAL_CARD_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.TutorialCardRefreshNotifier)  | [`TutorialCardRefreshNotifier`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/TutorialCardRefreshNotifier.java) |
| [`AvdLaunchListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.avdmanager.AvdLaunchListener)  | [`AvdLaunchListener`](%gh-ij-android%/android/src/com/android/tools/idea/avdmanager/AvdLaunchListener.java) |
| [`EmulatorLogListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.avdmanager.EmulatorLogListener)  | [`EmulatorLogListener`](%gh-ij-android%/android/src/com/android/tools/idea/avdmanager/EmulatorLogListener.kt) |
| [`IssueProviderListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.common.error.IssueProviderListener)  ![Project-Level][project-level] | [`IssueProviderListener`](%gh-ij-android%/designer/src/com/android/tools/idea/common/error/IssueProvider.kt) |
| [`IssueProviderListener#UI_CHECK`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.common.error.IssueProviderListener)  ![Project-Level][project-level] | [`IssueProviderListener`](%gh-ij-android%/designer/src/com/android/tools/idea/common/error/IssueProvider.kt) |
| [`ComposeAnimationListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.compose.preview.ComposePreviewRepresentation.ComposeAnimationListener)  | [`ComposeAnimationListener`](%gh-ij-android%/compose-designer/src/com/android/tools/idea/compose/preview/Preview.kt) |
| [`FastPreviewManager#FAST_PREVIEW_MANAGER_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.fast.FastPreviewManager.Companion.FastPreviewManagerListener)  | [`FastPreviewManagerListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/fast/FastPreviewManager.kt) |
| [`GradleBuildState#GRADLE_BUILD_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.build.GradleBuildListener)  | [`GradleBuildListener`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/build/GradleBuildListener.java) |
| [`GradleSyncStateImplKt#GRADLE_SYNC_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.sync.GradleSyncListenerWithRoot)  | [`GradleSyncListenerWithRoot`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/GradleSyncListenerWithRoot.kt) |
| [`FilterStatusChanged.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.logcat.filters.FilterTextField.FilterStatusChanged)  | [`FilterStatusChanged`](%gh-ij-android%/logcat/src/com/android/tools/idea/logcat/filters/FilterTextField.kt) |
| [`MergedManifestSnapshotComputeListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.model.MergedManifestSnapshotComputeListener)  | [`MergedManifestSnapshotComputeListener`](%gh-ij-android%/android/src/com/android/tools/idea/model/MergedManifestManager.kt) |
| [`SafeArgsModeModuleService#MODE_CHANGED`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.nav.safeargs.module.SafeArgsModeModuleService.SafeArgsModeChangedListener)  | [`SafeArgsModeChangedListener`](%gh-ij-android%/nav/safeargs/common/src/com/android/tools/idea/nav/safeargs/module/SafeArgsModeModuleService.kt) |
| [`NavigationResourcesModificationListenerKt#NAVIGATION_RESOURCES_CHANGED`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.nav.safeargs.project.NavigationResourcesChangeListener)  | [`NavigationResourcesChangeListener`](%gh-ij-android%/nav/safeargs/common/src/com/android/tools/idea/nav/safeargs/project/NavigationResourcesModificationListener.kt) |
| [`MultiTemplateRenderer#TEMPLATE_RENDERER_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.npw.model.MultiTemplateRenderer.TemplateRendererListener)  | [`TemplateRendererListener`](%gh-ij-android%/android-npw/src/com/android/tools/idea/npw/model/MultiTemplateRenderer.kt) |
| [`ProjectApplicationIdsProvider.Companion#PROJECT_APPLICATION_IDS_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectApplicationIdsProvider.ProjectApplicationIdsListener)  | [`ProjectApplicationIdsListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectApplicationIdsProvider.kt) |
| [`ProjectSystemBuildUtil#PROJECT_SYSTEM_BUILD_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemBuildManager.BuildListener)  | [`BuildListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemBuildManager.kt) |
| [`ProjectSystemSyncUtil#PROJECT_SYSTEM_SYNC_TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemSyncManager.SyncResultListener)  | [`SyncResultListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemSyncManager.kt) |
| [`ClearLogcatListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ClearLogcatListener)  | [`ClearLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ClearLogcatListener.kt) |
| [`DeviceHeadsUpListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.DeviceHeadsUpListener)  | [`DeviceHeadsUpListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/DeviceHeadsUpListener.java) |
| [`ShowLogcatListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ShowLogcatListener)  | [`ShowLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ShowLogcatListener.kt) |
| [`ApplicationDeployListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.configuration.execution.ApplicationDeployListener)  | [`ApplicationDeployListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/configuration/execution/ApplicationDeployerImpl.kt) |
| [`SdkInstallListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.sdk.SdkInstallListener)  | [`SdkInstallListener`](%gh-ij-android%/android/src/com/android/tools/idea/sdk/SdkInstallListener.kt) |
| [`DeviceMirroringSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.streaming.DeviceMirroringSettingsListener)  | [`DeviceMirroringSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/streaming/DeviceMirroringSettingsListener.java) |
| [`EmulatorSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.streaming.EmulatorSettingsListener)  | [`EmulatorSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/streaming/EmulatorSettingsListener.java) |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.streaming.emulator.actions.FloatingXrToolbarState.Listener)  | [`Listener`](%gh-ij-android%/streaming/src/com/android/tools/idea/streaming/emulator/actions/ToggleFloatingXrToolbarAction.kt) |
| [`TransportDeviceManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.transport.TransportDeviceManager.TransportDeviceManagerListener)  | [`TransportDeviceManagerListener`](%gh-ij-android%/android-transport/src/com/android/tools/idea/transport/TransportDeviceManager.java) |
| [`Listener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.uibuilder.options.NlOptionsConfigurable.Listener)  | [`Listener`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/options/NlOptionsConfigurable.kt) |
| [`OpenHomeTabListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.profilers.taskbased.home.OpenHomeTabListener)  | [`OpenHomeTabListener`](%gh-ij-android%/profilers/src/com/android/tools/profilers/taskbased/home/OpenHomeTabListener.kt) |
| [`OpenPastRecordingsTabListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.profilers.taskbased.pastrecordings.OpenPastRecordingsTabListener)  | [`OpenPastRecordingsTabListener`](%gh-ij-android%/profilers/src/com/android/tools/profilers/taskbased/pastrecordings/OpenPastRecordingsTabListener.kt) |
| [`CreateProfilerTaskTabListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.profilers.taskbased.task.CreateProfilerTaskTabListener)  | [`CreateProfilerTaskTabListener`](%gh-ij-android%/profilers/src/com/android/tools/profilers/taskbased/task/CreateProfilerTaskTabListener.kt) |
| [`OpenProfilerTaskTabListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.android.tools.profilers.taskbased.task.OpenProfilerTaskTabListener)  | [`OpenProfilerTaskTabListener`](%gh-ij-android%/profilers/src/com/android/tools/profilers/taskbased/task/OpenProfilerTaskTabListener.kt) |
| [`ResourceFolderManager#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.android.facet.ResourceFolderManager.ResourceFolderListener)  ![Project-Level][project-level] | [`ResourceFolderListener`](%gh-ij-android%/android/src/org/jetbrains/android/facet/ResourceFolderManager.kt) |


### adt-ui.xml

[`adt-ui.xml`](%gh-ij-android%/adt-ui/resources/META-INF/adt-ui.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.android.toolWindow"/></include> ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |

### aiplugin-api.xml

[`aiplugin-api.xml`](%gh-ij-android%/ml-api/src/resources/META-INF/aiplugin-api.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.gemini.geminiPluginApi"/></include> ![Non-Dynamic][non-dynamic] | [`GeminiPluginApi`](%gh-ij-android%/ml-api/src/main/kotlin/com/android/tools/idea/gemini/GeminiPluginApi.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.ml.studioBotExternalFlags"/></include> ![Non-Dynamic][non-dynamic] | [`StudioBotExternalFlags`](%gh-ij-android%/ml-api/src/main/kotlin/com/android/tools/idea/gemini/StudioBotExternalFlags.kt) |

### android-adb.xml

[`android-adb.xml`](%gh-ij-android%/android-adb/resources/META-INF/android-adb.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.run.deviceNameRenderer"/></include> ![Non-Dynamic][non-dynamic] | [`DeviceNameRendererEx`](%gh-ij-android%/android-adb/src/com/android/tools/idea/ddms/DeviceNameRendererEx.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.deviceProvisioner"/></include> ![Non-Dynamic][non-dynamic] | [`DeviceProvisionerFactory`](%gh-ij-android%/android-adb/src/com/android/tools/idea/deviceprovisioner/DeviceProvisionerFactory.kt) |

### android-editing-metrics.xml

[`android-editing-metrics.xml`](%gh-ij-android%/android/editing/metrics/resources/META-INF/android-editing-metrics.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.editing.metrics.codeEditedListener"/></include> | [`CodeEditedListener`](%gh-ij-android%/android/editing/metrics/src/com/android/tools/idea/editing/metrics/CodeEditedListener.kt) |

### android-execution-common.xml

[`android-execution-common.xml`](%gh-ij-android%/execution/common/resources/META-INF/android-execution-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.execution.common.androidConfigurationExecutorProvider"/></include> ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/execution/common/src/com/android/tools/idea/execution/common/AndroidConfigurationExecutor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.execution.common.debug.utils.facetFinderToken"/></include> ![Non-Dynamic][non-dynamic] | [`FacetFinderToken`](%gh-ij-android%/execution/common/src/com/android/tools/idea/execution/common/debug/utils/FacetFinder.kt) |

### android-kotlin-extensions-common.xml

[`android-kotlin-extensions-common.xml`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/resources/META-INF/android-kotlin-extensions-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.android.model.androidModuleInfoProvider"/></include> ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`AndroidModuleInfoProvider`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/src/org/jetbrains/kotlin/android/synthetic/idea/AndroidModuleInfoProvider.kt) |

### android-kotlin.common.xml

[`android-kotlin.common.xml`](%gh-ij-android%/android-kotlin/idea-android/common/resources/META-INF/android-kotlin.common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.androidDexer"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AndroidDexer`](%gh-ic%/plugins/kotlin/jvm-debugger/evaluation/src/org/jetbrains/kotlin/idea/debugger/evaluate/classLoading/AndroidDexer.kt) |

### android-lang.xml

[`android-lang.xml`](%gh-ij-android%/android-lang/resources/META-INF/android-lang.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lang.androidSql.contextProvider"/></include> ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android-lang/src/com/android/tools/idea/lang/androidSql/AndroidSqlContext.kt) |

### android-navigator.xml

[`android-navigator.xml`](%gh-ij-android%/android-navigator/resources/META-INF/android-navigator.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.navigator.androidViewNodeProvider"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidViewNodeProvider`](%gh-ij-android%/android-navigator/src/com/android/tools/idea/navigator/nodes/AndroidViewNodeProvider.kt) |

### android-npw.xml

[`android-npw.xml`](%gh-ij-android%/android-npw/resources/META-INF/android-npw.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.moduleDescriptionProvider"/></include> ![Non-Dynamic][non-dynamic] | [`ModuleDescriptionProvider`](%gh-ij-android%/android-npw/src/com/android/tools/idea/npw/module/ModuleDescriptionProvider.kt) |

### android-plugin.xml

[`android-plugin.xml`](%gh-ij-android%/android/resources/META-INF/android-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.customProjectTypeImporter"/></include> ![Non-Dynamic][non-dynamic] | [`CustomProjectTypeImporter`](%gh-ij-android%/android/src/com/android/tools/idea/project/CustomProjectTypeImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.ide.androidConfigurableContributor"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidConfigurableContributor`](%gh-ij-android%/android/src/com/android/tools/idea/structure/dialog/AndroidConfigurableContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.ide.sdkEventListener"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidSdkEventListener`](%gh-ij-android%/android/src/com/android/tools/idea/sdk/IdeSdks.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.rendering.renderErrorContributor"/></include> ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/RenderErrorContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.rendering.renderSecurityManagerOverrides"/></include> | [`RenderSecurityManagerOverrides`](%gh-ij-android%/rendering/src/com/android/tools/rendering/security/RenderSecurityManagerOverrides.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.run.androidDebugger"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidDebugger`](%gh-ij-android%/execution/common/src/com/android/tools/idea/execution/common/debug/AndroidDebugger.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.run.androidLaunchTaskContributor"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidLaunchTaskContributor`](%gh-ij-android%/android/src/com/android/tools/idea/run/AndroidLaunchTaskContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.run.deployTargetProvider"/></include> ![Non-Dynamic][non-dynamic] | [`DeployTargetProvider`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/DeployTargetProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.actions.annotations.inferAnnotationsToken"/></include> ![Non-Dynamic][non-dynamic] | [`InferAnnotationsToken`](%gh-ij-android%/android/src/com/android/tools/idea/actions/annotations/InferAnnotationsToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.actions.exportProjectZipExcludesContributor"/></include> ![Non-Dynamic][non-dynamic] | [`ExportProjectZipExcludesContributor`](%gh-ij-android%/android/src/com/android/tools/idea/actions/ExportProjectZipExcludesContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.analytics.ideBrandProvider"/></include> ![Non-Dynamic][non-dynamic] | [`IdeBrandProvider`](%gh-ij-android%/android/src/com/android/tools/idea/analytics/IdeBrandProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.databinding.layoutBindingSupport"/></include> ![Non-Dynamic][non-dynamic] | [`LayoutBindingSupport`](%gh-ij-android%/android/src/com/android/tools/idea/databinding/LayoutBindingSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.diagnostics.report.logsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`DiagnosticsSummaryFileProvider`](%gh-ij-android%/android/src/com/android/tools/idea/diagnostics/report/DiagnosticsSummaryFileProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.editors.manifest.manifestPanelToken"/></include> ![Non-Dynamic][non-dynamic] | [`ManifestPanelToken`](%gh-ij-android%/android/src/com/android/tools/idea/editors/manifest/ManifestPanelToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.flags.experimentalSettingsContributor"/></include> ![Non-Dynamic][non-dynamic] | [`ExperimentalSettingsContributor`](%gh-ij-android%/android/src/com/android/tools/idea/flags/ExperimentalSettingsContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lang.databinding.dataBindingCompletionSupport"/></include> ![Non-Dynamic][non-dynamic] | [`DataBindingCompletionSupport`](%gh-ij-android%/android/src/com/android/tools/idea/lang/databinding/DataBindingCompletionSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lang.databinding.dataBindingExpressionSupport"/></include> ![Non-Dynamic][non-dynamic] | [`DataBindingExpressionSupport`](%gh-ij-android%/android/src/com/android/tools/idea/lang/databinding/DataBindingExpressionSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.layoutlib.layoutLibraryProvider"/></include> ![Non-Dynamic][non-dynamic] | [`LayoutLibraryProvider`](%gh-ij-android%/layoutlib-loader/src/com/android/tools/idea/layoutlib/LayoutLibraryLoader.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.liveedit.tokens.buildSystemLiveEditServices"/></include> ![Non-Dynamic][non-dynamic] | [`BuildSystemLiveEditServices`](%gh-ij-android%/android/src/com/android/tools/idea/run/deployment/liveedit/tokens/BuildSystemLiveEditServices.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.manifest.manifestClassToken"/></include> ![Non-Dynamic][non-dynamic] | [`ManifestClassToken`](%gh-ij-android%/android/src/com/android/tools/idea/manifest/ManifestClassToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.ndk.nativeWorkspaceProvider"/></include> ![Non-Dynamic][non-dynamic] | [`NativeWorkspaceProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ndk/NativeWorkspaceProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.rendering.renderIssueCollectionConsumer"/></include> ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/RenderIssueCollectionConsumer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.rendering.tokens.buildSystemFilePreviewServices"/></include> ![Non-Dynamic][non-dynamic] | [`BuildSystemFilePreviewServices`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/tokens/BuildSystemFilePreviewServices.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.res.resourceClassToken"/></include> ![Non-Dynamic][non-dynamic] | [`ResourceClassToken`](%gh-ij-android%/android/src/com/android/tools/idea/res/ResourceClassToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.run.configuration.editors.androidWearConfigurationEditorToken"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidWearConfigurationEditorToken`](%gh-ij-android%/android/src/com/android/tools/idea/run/configuration/editors/AndroidWearConfigurationEditor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.run.editor.androidDebuggerInfoProvider"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidDebuggerInfoProvider`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/AndroidDebuggerInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.run.editor.testRunParametersToken"/></include> ![Non-Dynamic][non-dynamic] | [`TestRunParametersToken`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/TestRunParameters.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.testartifacts.instrumented.androidTestConfigurationProducerToken"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidTestConfigurationProducerToken`](%gh-ij-android%/android/src/com/android/tools/idea/testartifacts/instrumented/AndroidTestConfigurationProducer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.testartifacts.instrumented.testRunConfigurationOptions"/></include> | [`TestRunConfigurationOptions`](%gh-ij-android%/android/src/com/android/tools/idea/testartifacts/instrumented/AndroidTestConfigurationProducer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.testartifacts.screenshottest.screenshotTestResultListenerToken"/></include> ![Non-Dynamic][non-dynamic] | [`ScreenshotTestResultListenerToken`](%gh-ij-android%/android/src/com/android/tools/idea/testartifacts/screenshottest/ScreenshotTestResultListenerToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.ui.designer.overlays.overlayProvider"/></include> ![Non-Dynamic][non-dynamic] | [`OverlayProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ui/designer/overlays/OverlayProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.ui.guiTestingStatusProvider"/></include> ![Non-Dynamic][non-dynamic] | [`GuiTestingStatusProvider`](%gh-ij-android%/android/src/com/android/tools/idea/ui/GuiTestingStatusProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.ui.resourcemanager.importer.createDefaultResDirectoryToken"/></include> ![Non-Dynamic][non-dynamic] | [`CreateDefaultResDirectoryToken`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/importer/DesignAssetImporter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.wizard.template.wizardTemplateProvider"/></include> ![Non-Dynamic][non-dynamic] | `WizardTemplateProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.android.actions.newResourceCreationHandler"/></include> ![Non-Dynamic][non-dynamic] | [`NewResourceCreationHandler`](%gh-ij-android%/android/src/org/jetbrains/android/actions/NewResourceCreationHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.android.refactoring.unusedResourcesToken"/></include> ![Non-Dynamic][non-dynamic] | [`UnusedResourcesToken`](%gh-ij-android%/android/src/org/jetbrains/android/refactoring/UnusedResourcesProcessor.kt) |

### android-templates.xml

[`android-templates.xml`](%gh-ij-android%/android-templates/resources/META-INF/android-templates.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.templates.additionalTemplateActionsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`AdditionalTemplateActionsProvider`](%gh-ij-android%/android-templates/src/com/android/tools/idea/templates/AdditionalTemplateActionsProvider.java) |

### apkanalyzer.xml

[`apkanalyzer.xml`](%gh-ij-android%/apkanalyzer/src/META-INF/apkanalyzer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.apk.viewer.apkAnalyzerToken"/></include> ![Non-Dynamic][non-dynamic] | [`ApkAnalyzerToken`](%gh-ij-android%/apkanalyzer/src/com/android/tools/idea/apk/viewer/ApkAnalyzerToken.kt) |

### app-inspection.xml

[`app-inspection.xml`](%gh-ij-android%/app-inspection/ide/resources/META-INF/app-inspection.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.appinspection.ide.resolver.artifactResolverFactoryToken"/></include> ![Non-Dynamic][non-dynamic] | [`ArtifactResolverFactoryToken`](%gh-ij-android%/app-inspection/ide/src/com/android/tools/idea/appinspection/ide/resolver/ArtifactResolverFactory.kt) |

### app-inspector.xml

[`app-inspector.xml`](%gh-ij-android%/app-inspection/inspector/ide/resources/META-INF/app-inspector.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.appinspection.inspector.ide.appInspectorTabProvider"/></include> ![Non-Dynamic][non-dynamic] | [`AppInspectorTabProvider`](%gh-ij-android%/app-inspection/inspector/ide/src/com/android/tools/idea/appinspection/inspector/ide/AppInspectorTabProvider.kt) |

### assistant.xml

[`assistant.xml`](%gh-ij-android%/assistant/resources/META-INF/assistant.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.actionHandler"/></include> ![Non-Dynamic][non-dynamic] | [`AssistActionHandler`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistActionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.actionStateManager"/></include> ![Non-Dynamic][non-dynamic] | [`AssistActionStateManager`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistActionStateManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.assistantBundleCreator"/></include> ![Non-Dynamic][non-dynamic] | [`AssistantBundleCreator`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistantBundleCreator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.navlistener"/></include> ![Non-Dynamic][non-dynamic] | [`AssistNavListener`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/AssistNavListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.panelFactory"/></include> ![Non-Dynamic][non-dynamic] | [`PanelFactory`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/PanelFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.assistant.scrollHandler"/></include> ![Non-Dynamic][non-dynamic] | [`ScrollHandler`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/ScrollHandler.java) |

### com.android.tools.gradle.dcl

[`com.android.tools.gradle.dcl`](%gh-ij-android%/gradle-declarative-lang-ide/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.gradle.dcl.ide.declarativeSchemaProvider"/></include> ![Non-Dynamic][non-dynamic] | [`DeclarativeSchemaProvider`](%gh-ij-android%/gradle-declarative-lang-ide/src/com/android/tools/idea/gradle/dcl/lang/ide/DeclarativeSchemaProvider.kt) |

### compose-designer.xml

[`compose-designer.xml`](%gh-ij-android%/compose-designer/resources/META-INF/compose-designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.compose.preview.composeEditorNotificationProvider"/></include> ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.compose.preview.composeStudioBotActionFactory"/></include> ![Non-Dynamic][non-dynamic] | [`ComposeStudioBotActionFactory`](%gh-ij-android%/compose-designer/src/com/android/tools/idea/compose/preview/ComposeStudioBotActionFactory.kt) |

### customview.xml

[`customview.xml`](%gh-ij-android%/designer/customview/resources/META-INF/customview.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.customview.preview.customViewEditorNotificationProvider"/></include> ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |

### databinding.xml

[`databinding.xml`](%gh-ij-android%/databinding/resources/META-INF/databinding.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.databinding.bindingLayoutToken"/></include> ![Non-Dynamic][non-dynamic] | [`BindingLayoutToken`](%gh-ij-android%/databinding/src/com/android/tools/idea/databinding/BindingLayoutToken.kt) |

### designer.xml

[`designer.xml`](%gh-ij-android%/designer/resources/META-INF/designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider"/></include> ![Non-Dynamic][non-dynamic] | [`PreviewRepresentationProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/editor/multirepresentation/PreviewRepresentationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.editorNotificationProvider"/></include> ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.handlers.constraint.constraintLayoutExtension"/></include> | [`ConstraintLayoutExtension`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/handlers/constraint/ConstraintComponentUtilities.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.handlers.viewHandlerProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewHandlerProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/handlers/ViewHandlerProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.property.motionEditorNlPropertiesModelProvider"/></include> | [`NlPropertiesModelProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/property/NlPropertiesModel.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.property.motionEditorNlPropertiesViewProvider"/></include> | [`NlPropertiesViewProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/property/NlPropertiesView.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.scene.decorator.nlDecoratorProvider"/></include> | [`Provider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/scene/decorator/NlSceneDecoratorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.uibuilder.troubleshooting.infoCollector"/></include> ![Non-Dynamic][non-dynamic] | [`TroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |

### device-manager-v2.xml

[`device-manager-v2.xml`](%gh-ij-android%/device-manager-v2/resources/META-INF/device-manager-v2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.devicemanagerv2.deviceManagerOverflowActionContributor"/></include> ![Non-Dynamic][non-dynamic] | [`DeviceManagerOverflowActionContributor`](%gh-ij-android%/device-manager-v2/src/com/android/tools/idea/devicemanagerv2/OverflowButton.kt) |

### gradle-dsl.xml

[`gradle-dsl.xml`](%gh-ij-android%/gradle-dsl/resources/META-INF/gradle-dsl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.gradleModelProvider"/></include> ![Non-Dynamic][non-dynamic] | [`GradleModelProvider`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/api/GradleModelProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.gradle.dsl.blockModelProvider"/></include> ![Experimental][experimental] | [`BlockModelProvider`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/model/BlockModelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.gradle.dsl.transformerFactory"/></include> | [`GradleDslTransformerFactory`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/parser/GradleDslTransformerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.versionCatalogFilesGradleModel"/></include> ![Non-Dynamic][non-dynamic] | [`VersionCatalogFilesModel`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/model/VersionCatalogFilesModel.kt) |

### layout-inspector.xml

[`layout-inspector.xml`](%gh-ij-android%/layout-inspector/resources/META-INF/layout-inspector.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.layoutinspector.pipeline.appinspection.compose.getComposeLayoutInspectorJarToken"/></include> ![Non-Dynamic][non-dynamic] | [`GetComposeLayoutInspectorJarToken`](%gh-ij-android%/layout-inspector/src/com/android/tools/idea/layoutinspector/pipeline/appinspection/compose/ComposeLayoutInspectorClient.kt) |

### lint-plugin.xml

[`lint-plugin.xml`](%gh-ij-android%/lint/src/META-INF/lint-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lint.common.lintIdeSupport"/></include> ![Non-Dynamic][non-dynamic] | [`LintIdeSupport`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lint.common.lintQuickFixProvider"/></include> ![Non-Dynamic][non-dynamic] | [`LintIdeQuickFixProvider`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.lint.common.updateDepsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`DependencyUpdateProvider`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/AndroidLintGradleDependencyInspection.kt) |

### logcat.xml

[`logcat.xml`](%gh-ij-android%/logcat/resources/META-INF/logcat.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.logcat.consoleFilterProvider"/></include> ![Non-Dynamic][non-dynamic] | [`LogcatConsoleFilterProvider`](%gh-ij-android%/logcat/src/com/android/tools/idea/logcat/LogcatConsoleFilterProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.logcat.messages.exceptionMessageRewriter"/></include> ![Non-Dynamic][non-dynamic] | [`ExceptionMessageRewriter`](%gh-ij-android%/logcat/src/com/android/tools/idea/logcat/messages/ExceptionMessageRewriter.kt) |

### native-symbolizer.xml

[`native-symbolizer.xml`](%gh-ij-android%/native-symbolizer/resources/META-INF/native-symbolizer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.nativeSymbolizer.moduleSymbolSourceContributor"/></include> ![Non-Dynamic][non-dynamic] | [`ModuleSymbolSourceContributor`](%gh-ij-android%/native-symbolizer/src/com/android/tools/nativeSymbolizer/SymbolSource.kt) |

### naveditor.xml

[`naveditor.xml`](%gh-ij-android%/nav/editor/resources/META-INF/naveditor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.naveditor.editor.addDestinationMenuToken"/></include> ![Non-Dynamic][non-dynamic] | [`AddDestinationMenuToken`](%gh-ij-android%/nav/editor/src/com/android/tools/idea/naveditor/editor/AddDestinationMenu.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.naveditor.surface.navDesignSurfaceToken"/></include> ![Non-Dynamic][non-dynamic] | [`NavDesignSurfaceToken`](%gh-ij-android%/nav/editor/src/com/android/tools/idea/naveditor/surface/NavDesignSurfaceToken.java) |

### pipeline.xml

[`pipeline.xml`](%gh-ij-android%/android-transport/resources/META-INF/pipeline.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.transport.transportConfigContributor"/></include> ![Non-Dynamic][non-dynamic] | [`TransportConfigContributor`](%gh-ij-android%/android-transport/src/com/android/tools/idea/transport/TransportConfigContributor.java) |

### profilers.xml

[`profilers.xml`](%gh-ij-android%/profilers-android/resources/META-INF/profilers.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.profilers.profilerProgramRunnerToken"/></include> ![Non-Dynamic][non-dynamic] | [`ProfilerProgramRunnerToken`](%gh-ij-android%/profilers-android/src/com/android/tools/idea/profilers/ProfilerProgramRunnerToken.kt) |

### project-system-gradle-plugin.xml

[`project-system-gradle-plugin.xml`](%gh-ij-android%/project-system-gradle/resources/META-INF/project-system-gradle-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.gradle.androidModuleDataService"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidModuleDataServiceExtension`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/projectsystem/gradle/sync/AndroidModuleDataServiceExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.gradle.sync.postSyncProjectCleanupStep"/></include> ![Non-Dynamic][non-dynamic] | [`ProjectCleanupStep`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectCleanupStep.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.gradle.sync.postSyncProjectSetupStep"/></include> ![Non-Dynamic][non-dynamic] | [`ProjectSetupStep`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectSetupStep.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.moduleImporter"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidModuleImporter`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/AndroidModuleImporter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.gradle.errorQuickFixProvider"/></include> ![Non-Dynamic][non-dynamic] | [`GradleErrorQuickFixProvider`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/build/events/GradleErrorQuickFixProvider.kt) |

### project-system-plugin.xml

[`project-system-plugin.xml`](%gh-ij-android%/project-system/resources/META-INF/project-system-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.androidStartupActivity"/></include> | [`AndroidStartupActivity`](%gh-ij-android%/project-system/src/com/android/tools/idea/AndroidStartupActivity.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.project.projectsystem"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidProjectSystemProvider`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/AndroidProjectSystemProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.memorysettings.memorySettingsToken"/></include> ![Non-Dynamic][non-dynamic] | [`MemorySettingsToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/memorysettings/MemorySettingsToken.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.model.mergedManifestInfoToken"/></include> ![Non-Dynamic][non-dynamic] | [`MergedManifestInfoToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/model/MergedManifestInfoToken.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.ApplicationProjectContextProvider"/></include> | [`ApplicationProjectContextProvider`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ApplicationProjectContext.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.androidIconProviderProjectToken"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidIconProviderProjectToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/AndroidIconProviderProjectToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.dynamicAppFeatureOnFeatureToken"/></include> ![Non-Dynamic][non-dynamic] | [`DynamicAppFeatureOnFeatureToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/DynamicAppFeatureOnFeatureToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.findDependenciesWithResourcesToken"/></include> ![Non-Dynamic][non-dynamic] | [`FindDependenciesWithResourcesToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/FindDependenciesWithResourcesToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.libraryDependenciesTroubleInfoCollectorToken"/></include> ![Non-Dynamic][non-dynamic] | [`LibraryDependenciesTroubleInfoCollectorToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/LibraryDependenciesTroubleInfoCollectorToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.projectsystem.pseudoLocalesToken"/></include> ![Non-Dynamic][non-dynamic] | [`PseudoLocalesToken`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/PseudoLocalesToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.android.facet.resourceFolderManagerToken"/></include> ![Non-Dynamic][non-dynamic] | [`ResourceFolderManagerToken`](%gh-ij-android%/project-system/src/org/jetbrains/android/facet/ResourceFolderManagerToken.kt) |

### resources-explorer.xml

[`resources-explorer.xml`](%gh-ij-android%/android/resources/com/android/tools/idea/ui/resourcemanager/META-INF/resources-explorer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.resourceImporter"/></include> ![Non-Dynamic][non-dynamic] | [`ResourceImporter`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/plugin/ResourceImporter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.resourceViewer"/></include> ![Non-Dynamic][non-dynamic] | [`DesignAssetRenderer`](%gh-ij-android%/android/src/com/android/tools/idea/ui/resourcemanager/plugin/DesignAssetRenderer.kt) |

### safeargs.common.xml

[`safeargs.common.xml`](%gh-ij-android%/nav/safeargs/common/resources/META-INF/safeargs.common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.nav.safeargs.module.safeArgsModeToken"/></include> ![Non-Dynamic][non-dynamic] | [`SafeArgsModeToken`](%gh-ij-android%/nav/safeargs/common/src/com/android/tools/idea/nav/safeargs/module/SafeArgsModeModuleService.kt) |

### server-flags.xml

[`server-flags.xml`](%gh-ij-android%/server-flags/resources/META-INF/server-flags.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.mendelFlagsProvider"/></include> ![Non-Dynamic][non-dynamic] | [`MendelFlagsProvider`](%gh-ij-android%/server-flags/src/com/android/tools/idea/mendel/MendelFlagsProvider.kt) |

### testartifacts.xml

[`testartifacts.xml`](%gh-ij-android%/testartifacts/src/META-INF/testartifacts.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.testartifacts.instrumented.androidRunConfigurationToken"/></include> ![Non-Dynamic][non-dynamic] | [`AndroidRunConfigurationToken`](%gh-ij-android%/testartifacts/src/com/android/tools/idea/testartifacts/instrumented/AndroidRunConfigurationToken.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.android.tools.idea.testartifacts.screenshot.screenshotTestRunConfigurationToken"/></include> ![Non-Dynamic][non-dynamic] | [`ScreenshotTestRunConfigurationToken`](%gh-ij-android%/testartifacts/src/com/android/tools/idea/testartifacts/screenshot/ScreenshotTestRunConfigurationToken.kt) |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
