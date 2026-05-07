<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Frontend, Backend, and Shared APIs

<primary-label ref="2025.3"/>

<link-summary>Common IntelliJ Platform APIs that usually belong on the frontend, backend, or shared side of a split plugin.</link-summary>

This page lists APIs that commonly indicate where plugin code should live in a split architecture:
- [Frontend APIs](#frontend-apis)
- [Backend APIs](#backend-apis)
- [Shared APIs](#shared-apis)

The lists are intentionally conservative.
Treat them as placement defaults rather than an exhaustive type system.
If a feature needs a different placement, review the specific API behavior and validate the result in [Split Mode](split_mode_and_remote_development.md).

Inspection <ui-path>Plugin DevKit | Code | Inappropriate Frontend/Backend API usage in module</ui-path> highlights inappropriate API usage.

See also [Modular Plugins](modular_plugins.md) and [Split Mode Feature Development Guideline](split_mode_feature_development.md).

## Frontend APIs

The following APIs usually belong in the frontend module:

- [`com.intellij.openapi.wm.ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.kt)
- [`com.intellij.openapi.wm.ToolWindow`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/ToolWindow.java)
- [`com.intellij.openapi.editor.toolbar.floating.FloatingToolbarProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/toolbar/floating/FloatingToolbarProvider.kt)
- [`com.intellij.openapi.ui.DialogWrapper`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/ui/WindowWrapperBuilder.java)
- [`com.intellij.openapi.fileEditor.FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java)
- [`com.intellij.openapi.ui.popup.JBPopupFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java)
- [`com.intellij.codeInsight.editorActions.TypedHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java)
- [`com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate.java)
- [`com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegateAdapter.java)
- [`com.intellij.codeInsight.highlighting.BraceMatcher`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/BraceMatcher.java)
- [`com.intellij.openapi.fileEditor.FileEditorManager.getFocusedEditor()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManager.java)
- [`com.intellij.execution.services.ServiceViewContributor`](%gh-ic%/platform/lang-api/src/com/intellij/execution/services/ServiceViewContributor.java)
- [`com.intellij.ui.tree.AsyncTreeModel`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/tree/AsyncTreeModel.java)
- [`com.intellij.ui.jcef.JBCefClient`](%gh-ic%/platform/ui.jcef/jcef/JBCefClient.java)
- [`com.intellij.notification.NotificationGroup`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationGroupManager.java)
- [`com.intellij.openapi.wm.StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java)
- [`com.intellij.openapi.options.Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java)
- [`com.intellij.codeInsight.editorActions.CopyPastePostProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/CopyPastePostProcessor.java)
- [`com.intellij.openapi.editor.actionSystem.EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
- [`com.intellij.openapi.editor.event.CaretListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/CaretListener.java)
- [`com.intellij.openapi.editor.event.EditorMouseMotionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseMotionListener.java)
- [`com.intellij.openapi.editor.event.EditorMouseListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/EditorMouseListener.java)
- [`com.intellij.openapi.editor.event.SelectionListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/SelectionListener.java)
- [`com.intellij.openapi.editor.event.VisibleAreaListener`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/event/VisibleAreaListener.java)
- [`com.intellij.openapi.editor.ex.FoldingListener`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/ex/FoldingListener.java)
- [`com.intellij.openapi.ui.popup.JBPopupListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/ui/popup/JBPopupListener.java)
- [`com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java)
- [`com.intellij.openapi.actionSystem.PlatformDataKeys.TOOL_WINDOW`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/PlatformDataKeys.java)
- [`com.intellij.openapi.actionSystem.PlatformCoreDataKeys.SELECTED_ITEM`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/actionSystem/PlatformCoreDataKeys.java)
- [`com.intellij.openapi.actionSystem.PlatformCoreDataKeys.SELECTED_ITEMS`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/actionSystem/PlatformCoreDataKeys.java)
- [`com.intellij.openapi.actionSystem.PlatformCoreDataKeys.CONTEXT_COMPONENT`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/actionSystem/PlatformCoreDataKeys.java)

## Backend APIs

The following APIs usually belong in the backend module:

- [`com.intellij.openapi.vfs.VirtualFileManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java)
- [`com.intellij.openapi.vfs.VfsUtil`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/vfs/VfsUtil.java)
- [`com.intellij.psi.stubs.StubIndex`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/StubIndexKey.java)
- [`com.intellij.util.indexing.IndexExtension`](%gh-ic%/platform/util/src/com/intellij/util/indexing/IndexExtension.java)
- [`com.intellij.execution.configurations.ConfigurationType`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java)
- [`com.intellij.psi.search.searches.ReferencesSearch`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/searches/ReferencesSearch.java)
- [`com.intellij.model.search.SearchService`](%gh-ic%/platform/indexing-api/src/com/intellij/model/search/SearchService.kt)
- [`com.intellij.util.Query`](%gh-ic%/platform/core-api/src/com/intellij/util/Query.kt)
- [`com.intellij.ide.FileIconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconProvider.java)
- [`com.intellij.codeInsight.hints.InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java)
- [`com.intellij.codeInsight.hints.declarative.InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProvider.kt)
- [`com.intellij.codeInsight.daemon.LineMarkerProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProvider.java)
- [`com.intellij.codeInspection.LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/CleanupLocalInspectionTool.java)
- [`com.intellij.psi.PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java)
- [`com.intellij.codeInsight.completion.CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionItemContributor.java)
- [`com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory`](%gh-ic%/json/backend/src/com/jetbrains/jsonSchema/extension/JsonSchemaProviderFactory.java)
- [`com.intellij.execution.actions.RunConfigurationProducer`](%gh-ic%/platform/lang-api/src/com/intellij/execution/actions/CompatibleRunConfigurationProducer.java)
- [`com.intellij.ide.structureView.StructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewBuilderProvider.java)
- [`com.intellij.openapi.vfs.AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java)
- [`com.intellij.openapi.vfs.VirtualFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileListener.java)
- [`com.intellij.platform.backend.workspace.WorkspaceModelChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt)
- [`com.intellij.openapi.project.ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java)
- [`com.intellij.execution.process.ProcessHandler`](%gh-ic%/platform/util/src/com/intellij/execution/process/ProcessHandler.java)
- [`com.intellij.openapi.actionSystem.CommonDataKeys.SYMBOLS`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java)
- [`com.intellij.openapi.actionSystem.PlatformCoreDataKeys.MODULE`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/actionSystem/PlatformCoreDataKeys.java)
- [`com.intellij.openapi.actionSystem.LangDataKeys.MODULE_CONTEXT`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/actionSystem/LangDataKeys.java)
- [`com.intellij.openapi.actionSystem.LangDataKeys.MODIFIABLE_MODULE_MODEL`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/actionSystem/LangDataKeys.java)

## Shared APIs


The following APIs **can** be used in both frontend and backend modules.

This list currently includes two categories of APIs:
- language support–related extensions
- project/application lifecycle listeners

Language support should be implemented in the shared module so that PSI can be used on both sides.
The frontend benefits from language support extensions because they enable a fast local editing experience and make it possible to build features on top of them.
For example, a typing handler naturally belongs in the frontend, and it requires PSI for the file type it supports.
The backend also benefits from language support because backend-specific extensions depend on it as well.
For example, an inspection that naturally belongs in the backend requires PSI to traverse the file.

The other category consists of application and project lifecycle listeners.
Since their implementations may produce arbitrary side effects, it is up to the plugin developer to decide whether those effects should run in the backend or the frontend.
For example, if a plugin needs to install and run an external tool after a project is loaded, it should be registered in the backend.
If it needs to display a survey UI with a feedback form after a user changes a registry key, it should run in the frontend.

- [`com.intellij.lang.ParserDefinition`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java)
- [`com.intellij.openapi.util.registry.RegistryValueListener`](%gh-ic%/platform/util/src/com/intellij/openapi/util/registry/RegistryValueListener.java)
- [`com.intellij.openapi.application.ApplicationListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ApplicationListener.java)
- [`com.intellij.openapi.project.ProjectCloseListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectCloseListener.kt)
- [`com.intellij.openapi.project.ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java)
- [`com.intellij.openapi.project.VetoableProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/VetoableProjectManagerListener.java)
- [`com.intellij.ide.plugins.DynamicPluginListener`](%gh-ic%/platform/core-api/src/com/intellij/ide/plugins/DynamicPluginListener.kt)
- [`com.intellij.openapi.fileEditor.FileEditorManagerListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java)
- [`com.intellij.openapi.editor.event.DocumentListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/event/DocumentListener.java)

Shared code should stay lightweight.
If shared logic starts depending on frontend-only or backend-only APIs, it usually belongs in a dedicated content module instead.

<include from="snippets.topic" element-id="missingContent"/>
