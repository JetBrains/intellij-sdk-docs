<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /community/android -->

# Android Plugin Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for Android Plugin.</link-summary>

<tldr>

**Product-Specific Plugin Development**: [Android Studio](android_studio.md)

</tldr>

60 Extension Points and 29 Listeners for Android Plugin

<include from="snippets.md" element-id="ep_list_legend"/>

## Android Plugin


### Android Plugin - Listeners

| Topic | Listener |
|-------|----------|
| [BuildAnalyzerStorageManager.Companion#DATA_IS_READY_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.build.attribution.BuildAnalyzerStorageManager.Listener)  | [`Listener`](%gh-ij-android%/build-attribution/src/com/android/build/attribution/BuildAnalyzerStorageManager.kt) |
| [StatefulButtonNotifier#BUTTON_STATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.StatefulButtonNotifier)  ![Project-Level][project-level] | [`StatefulButtonNotifier`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/StatefulButtonNotifier.java) |
| [TutorialCardRefreshNotifier#TUTORIAL_CARD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.assistant.TutorialCardRefreshNotifier)  | [`TutorialCardRefreshNotifier`](%gh-ij-android%/assistant/src/com/android/tools/idea/assistant/TutorialCardRefreshNotifier.java) |
| [AvdLaunchListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.avdmanager.AvdLaunchListener)  | [`AvdLaunchListener`](%gh-ij-android%/android/src/com/android/tools/idea/avdmanager/AvdLaunchListener.java) |
| [IssueProviderListener#UI_CHECK](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.common.error.IssueProviderListener)  ![Project-Level][project-level] | [`IssueProviderListener`](%gh-ij-android%/designer/src/com/android/tools/idea/common/error/IssueProvider.kt) |
| [IssueProviderListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.common.error.IssueProviderListener)  ![Project-Level][project-level] | [`IssueProviderListener`](%gh-ij-android%/designer/src/com/android/tools/idea/common/error/IssueProvider.kt) |
| [FastPreviewManager#FAST_PREVIEW_MANAGER_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.fast.FastPreviewManager.Companion.FastPreviewManagerListener)  | [`FastPreviewManagerListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/fast/FastPreviewManager.kt) |
| [LiveLiteralsService#DOCUMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.DocumentsUpdatedListener)  | [`DocumentsUpdatedListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsService#MANAGED_ELEMENTS_UPDATED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.LiveLiteralsService.ManagedElementsUpdatedListener)  | [`ManagedElementsUpdatedListener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/LiveLiteralsService.kt) |
| [LiveLiteralsDeploymentReportService#LITERALS_DEPLOYED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.editors.literals.internal.LiveLiteralsDeploymentReportService.Listener)  ![Project-Level][project-level] | [`Listener`](%gh-ij-android%/android/src/com/android/tools/idea/editors/literals/internal/LiveLiteralsDeploymentReportService.kt) |
| [GradleBuildState#GRADLE_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.build.GradleBuildListener)  | [`GradleBuildListener`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/build/GradleBuildListener.java) |
| [GradleSyncStateImplKt#GRADLE_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.gradle.project.sync.GradleSyncListenerWithRoot)  | [`GradleSyncListenerWithRoot`](%gh-ij-android%/android/src/com/android/tools/idea/gradle/project/sync/GradleSyncListenerWithRoot.kt) |
| [FilterStatusChanged.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.logcat.filters.FilterTextField.FilterStatusChanged)  | [`FilterStatusChanged`](%gh-ij-android%/logcat/src/com/android/tools/idea/logcat/filters/FilterTextField.kt) |
| [MergedManifestSnapshotComputeListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.model.MergedManifestSnapshotComputeListener)  | [`MergedManifestSnapshotComputeListener`](%gh-ij-android%/android/src/com/android/tools/idea/model/MergedManifestManager.kt) |
| [EssentialsModeMessenger#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.modes.essentials.EssentialsModeMessenger.Listener)  | [`Listener`](%gh-ij-android%/android/src/com/android/tools/idea/modes/essentials/EssentialsModeMessenger.kt) |
| [MultiTemplateRenderer#TEMPLATE_RENDERER_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.npw.model.MultiTemplateRenderer.TemplateRendererListener)  | [`TemplateRendererListener`](%gh-ij-android%/android-npw/src/com/android/tools/idea/npw/model/MultiTemplateRenderer.kt) |
| [ProjectApplicationIdsProvider.Companion#PROJECT_APPLICATION_IDS_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectApplicationIdsProvider.ProjectApplicationIdsListener)  | [`ProjectApplicationIdsListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectApplicationIdsProvider.kt) |
| [ProjectSystemBuildUtil#PROJECT_SYSTEM_BUILD_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemBuildManager.BuildListener)  | [`BuildListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemBuildManager.kt) |
| [ProjectSystemSyncUtil#PROJECT_SYSTEM_SYNC_TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.projectsystem.ProjectSystemSyncManager.SyncResultListener)  | [`SyncResultListener`](%gh-ij-android%/project-system/src/com/android/tools/idea/projectsystem/ProjectSystemSyncManager.kt) |
| [ClearLogcatListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ClearLogcatListener)  | [`ClearLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ClearLogcatListener.kt) |
| [DeviceHeadsUpListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.DeviceHeadsUpListener)  | [`DeviceHeadsUpListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/DeviceHeadsUpListener.java) |
| [ShowLogcatListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.run.ShowLogcatListener)  | [`ShowLogcatListener`](%gh-ij-android%/android/src/com/android/tools/idea/run/ShowLogcatListener.kt) |
| [SdkInstallListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.sdk.SdkInstallListener)  | [`SdkInstallListener`](%gh-ij-android%/android/src/com/android/tools/idea/sdk/SdkInstallListener.kt) |
| [DeviceMirroringSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.streaming.DeviceMirroringSettingsListener)  | [`DeviceMirroringSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/streaming/DeviceMirroringSettingsListener.java) |
| [EmulatorSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.streaming.EmulatorSettingsListener)  | [`EmulatorSettingsListener`](%gh-ij-android%/android/src/com/android/tools/idea/streaming/EmulatorSettingsListener.java) |
| [TransportDeviceManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.transport.TransportDeviceManager.TransportDeviceManagerListener)  | [`TransportDeviceManagerListener`](%gh-ij-android%/android-transport/src/com/android/tools/idea/transport/TransportDeviceManager.java) |
| [Listener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.idea.uibuilder.options.NlOptionsConfigurable.Listener)  | [`Listener`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/options/NlOptionsConfigurable.kt) |
| [OpenProfilerTaskListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.android.tools.profilers.tasks.OpenProfilerTaskListener)  | [`OpenProfilerTaskListener`](%gh-ij-android%/profilers/src/com/android/tools/profilers/tasks/OpenProfilerTaskListener.kt) |
| [ResourceFolderManager#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.android.facet.ResourceFolderManager.ResourceFolderListener)  ![Project-Level][project-level] | [`ResourceFolderListener`](%gh-ij-android%/android/src/org/jetbrains/android/facet/ResourceFolderManager.kt) |


### adt-ui.xml

[`adt-ui.xml`](%gh-ij-android%/adt-ui/src/main/java/META-INF/adt-ui.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.android.toolWindow](https://jb.gg/ipe?extensions=com.intellij.android.toolWindow) ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt) |

### android-adb.xml

[`android-adb.xml`](%gh-ij-android%/android-adb/src/META-INF/android-adb.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.run.deviceNameRenderer](https://jb.gg/ipe?extensions=com.android.run.deviceNameRenderer) ![Non-Dynamic][non-dynamic] | [`DeviceNameRendererEx`](%gh-ij-android%/android-adb/src/com/android/tools/idea/ddms/DeviceNameRendererEx.java) |
| [com.android.tools.idea.deviceProvisioner](https://jb.gg/ipe?extensions=com.android.tools.idea.deviceProvisioner) ![Non-Dynamic][non-dynamic] | [`DeviceProvisionerFactory`](%gh-ij-android%/android-adb/src/com/android/tools/idea/deviceprovisioner/DeviceProvisionerFactory.kt) |

### android-execution-common.xml

[`android-execution-common.xml`](%gh-ij-android%/execution/common/src/META-INF/android-execution-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.execution.common.androidConfigurationExecutorProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.execution.common.androidConfigurationExecutorProvider) ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/execution/common/src/com/android/tools/idea/execution/common/AndroidConfigurationExecutor.kt) |

### android-kotlin-extensions-common.xml

[`android-kotlin-extensions-common.xml`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/src/META-INF/android-kotlin-extensions-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.android.model.androidModuleInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.android.model.androidModuleInfoProvider) ![Deprecated][deprecated] ![Non-Dynamic][non-dynamic] | [`AndroidModuleInfoProvider`](%gh-ij-android%/android-kotlin/android-extensions-idea-common/src/org/jetbrains/kotlin/android/synthetic/idea/AndroidModuleInfoProvider.kt) |

### android-kotlin.xml

[`android-kotlin.xml`](%gh-ij-android%/android-kotlin/idea-android/src/META-INF/android-kotlin.xml)

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

### android-plugin.xml

[`android-plugin.xml`](%gh-ij-android%/android/src/META-INF/android-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.customProjectTypeImporter](https://jb.gg/ipe?extensions=com.android.customProjectTypeImporter) ![Non-Dynamic][non-dynamic] | [`CustomProjectTypeImporter`](%gh-ij-android%/android/src/com/android/tools/idea/project/CustomProjectTypeImporter.java) |
| [com.android.ide.androidConfigurableContributor](https://jb.gg/ipe?extensions=com.android.ide.androidConfigurableContributor) ![Non-Dynamic][non-dynamic] | [`AndroidConfigurableContributor`](%gh-ij-android%/android/src/com/android/tools/idea/structure/dialog/AndroidConfigurableContributor.kt) |
| [com.android.ide.sdkEventListener](https://jb.gg/ipe?extensions=com.android.ide.sdkEventListener) ![Non-Dynamic][non-dynamic] | [`AndroidSdkEventListener`](%gh-ij-android%/android/src/com/android/tools/idea/sdk/IdeSdks.java) |
| [com.android.rendering.renderErrorContributor](https://jb.gg/ipe?extensions=com.android.rendering.renderErrorContributor) ![Non-Dynamic][non-dynamic] | [`Provider`](%gh-ij-android%/android/src/com/android/tools/idea/rendering/RenderErrorContributor.java) |
| [com.android.rendering.renderSecurityManagerOverrides](https://jb.gg/ipe?extensions=com.android.rendering.renderSecurityManagerOverrides) | [`RenderSecurityManagerOverrides`](%gh-ij-android%/rendering/src/com/android/tools/rendering/security/RenderSecurityManagerOverrides.kt) |
| [com.android.run.androidDebugger](https://jb.gg/ipe?extensions=com.android.run.androidDebugger) ![Non-Dynamic][non-dynamic] | [`AndroidDebugger`](%gh-ij-android%/execution/common/src/com/android/tools/idea/execution/common/debug/AndroidDebugger.java) |
| [com.android.run.androidLaunchTaskContributor](https://jb.gg/ipe?extensions=com.android.run.androidLaunchTaskContributor) ![Non-Dynamic][non-dynamic] | [`AndroidLaunchTaskContributor`](%gh-ij-android%/android/src/com/android/tools/idea/run/AndroidLaunchTaskContributor.java) |
| [com.android.run.deployTargetProvider](https://jb.gg/ipe?extensions=com.android.run.deployTargetProvider) ![Non-Dynamic][non-dynamic] | [`DeployTargetProvider`](%gh-ij-android%/android/src/com/android/tools/idea/run/editor/DeployTargetProvider.java) |
| [com.android.tools.idea.analytics.ideBrandProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.analytics.ideBrandProvider) ![Non-Dynamic][non-dynamic] | [`IdeBrandProvider`](%gh-ij-android%/android/src/com/android/tools/idea/analytics/IdeBrandProvider.kt) |
| [com.android.tools.idea.databinding.layoutBindingSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.databinding.layoutBindingSupport) ![Non-Dynamic][non-dynamic] | [`LayoutBindingSupport`](%gh-ij-android%/android/src/com/android/tools/idea/databinding/LayoutBindingSupport.kt) |
| [com.android.tools.idea.diagnostics.report.logsProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.diagnostics.report.logsProvider) ![Non-Dynamic][non-dynamic] | [`DiagnosticsSummaryFileProvider`](%gh-ij-android%/android/src/com/android/tools/idea/diagnostics/report/DiagnosticsSummaryFileProvider.kt) |
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
| [com.android.tools.idea.compose.preview.composeEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.compose.preview.composeEditorNotificationProvider) ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |

### customview.xml

[`customview.xml`](%gh-ij-android%/designer/customview/src/META-INF/customview.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.customview.preview.customViewEditorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.customview.preview.customViewEditorNotificationProvider) ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |

### designer.xml

[`designer.xml`](%gh-ij-android%/designer/src/META-INF/designer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.editor.multirepresentation.sourcecode.sourceCodePreviewRepresentationProvider) ![Non-Dynamic][non-dynamic] | [`PreviewRepresentationProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/editor/multirepresentation/PreviewRepresentationProvider.kt) |
| [com.android.tools.idea.uibuilder.editorNotificationProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.editorNotificationProvider) ![Non-Dynamic][non-dynamic] ![DumbAware][dumb-aware] | [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java) |
| [com.android.tools.idea.uibuilder.handlers.viewHandlerProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.handlers.viewHandlerProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ViewHandlerProvider`](%gh-ij-android%/designer/src/com/android/tools/idea/uibuilder/handlers/ViewHandlerProvider.kt) |
| [com.android.tools.idea.uibuilder.troubleshooting.infoCollector](https://jb.gg/ipe?extensions=com.android.tools.idea.uibuilder.troubleshooting.infoCollector) ![Non-Dynamic][non-dynamic] | [`TroubleInfoCollector`](%gh-ic%/platform/platform-impl/src/com/intellij/troubleshooting/TroubleInfoCollector.java) |

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
| [com.android.tools.idea.gradle.dsl.transformerFactory](https://jb.gg/ipe?extensions=com.android.tools.idea.gradle.dsl.transformerFactory) ![Internal][internal] | [`GradleDslTransformerFactory`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/parser/GradleDslTransformerFactory.java) |
| [org.jetbrains.idea.gradle.dsl.blockModel](https://jb.gg/ipe?extensions=org.jetbrains.idea.gradle.dsl.blockModel) ![Experimental][experimental] | [`BlockModelProvider`](%gh-ij-android%/gradle-dsl/src/com/android/tools/idea/gradle/dsl/model/GradleBlockModelMap.java) |

### layout-inspector.xml

[`layout-inspector.xml`](%gh-ij-android%/layout-inspector/src/META-INF/layout-inspector.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.layoutinspector.pipeline.appinspection.compose.getComposeLayoutInspectorJarToken](https://jb.gg/ipe?extensions=com.android.tools.idea.layoutinspector.pipeline.appinspection.compose.getComposeLayoutInspectorJarToken) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`GetComposeLayoutInspectorJarToken`](%gh-ij-android%/layout-inspector/src/com/android/tools/idea/layoutinspector/pipeline/appinspection/compose/ComposeLayoutInspectorClient.kt) |

### lint-plugin.xml

[`lint-plugin.xml`](%gh-ij-android%/lint/src/META-INF/lint-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.lint.common.lintIdeSupport](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintIdeSupport) ![Non-Dynamic][non-dynamic] | [`LintIdeSupport`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeSupport.kt) |
| [com.android.tools.idea.lint.common.lintQuickFixProvider](https://jb.gg/ipe?extensions=com.android.tools.idea.lint.common.lintQuickFixProvider) ![Non-Dynamic][non-dynamic] | [`LintIdeQuickFixProvider`](%gh-ij-android%/lint/src/com/android/tools/idea/lint/common/LintIdeQuickFixProvider.java) |

### naveditor.xml

[`naveditor.xml`](%gh-ij-android%/nav/editor/src/META-INF/naveditor.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.tools.idea.naveditor.editor.addDestinationMenuToken](https://jb.gg/ipe?extensions=com.android.tools.idea.naveditor.editor.addDestinationMenuToken) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`AddDestinationMenuToken`](%gh-ij-android%/nav/editor/src/com/android/tools/idea/naveditor/editor/AddDestinationMenu.kt) |
| [com.android.tools.idea.naveditor.surface.navDesignSurfaceToken](https://jb.gg/ipe?extensions=com.android.tools.idea.naveditor.surface.navDesignSurfaceToken) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`NavDesignSurfaceToken`](%gh-ij-android%/nav/editor/src/com/android/tools/idea/naveditor/surface/NavDesignSurfaceToken.java) |

### project-system-gradle-plugin.xml

[`project-system-gradle-plugin.xml`](%gh-ij-android%/project-system-gradle/src/META-INF/project-system-gradle-plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.android.gradle.sync.postSyncProjectCleanupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectCleanupStep) ![Non-Dynamic][non-dynamic] | [`ProjectCleanupStep`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectCleanupStep.java) |
| [com.android.gradle.sync.postSyncProjectSetupStep](https://jb.gg/ipe?extensions=com.android.gradle.sync.postSyncProjectSetupStep) ![Non-Dynamic][non-dynamic] | [`ProjectSetupStep`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/setup/post/ProjectSetupStep.java) |
| [com.android.moduleImporter](https://jb.gg/ipe?extensions=com.android.moduleImporter) ![Non-Dynamic][non-dynamic] | [`AndroidModuleImporter`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/AndroidModuleImporter.kt) |

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

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
