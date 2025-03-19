<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2023.*

<!--
Before documenting a breaking API change, please make sure that the change cannot be avoided in an alternative way.

APIs marked with @Deprecated(forRemoval=true), @ApiStatus.Experimental, @ApiStatus.Internal/IntellijInternalApi, or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern followed by a 2nd line with ": "-prefixed human-readable description
and recommended fix/action (REQUIRED, please write full sentence ending with '.', see existing entries as reference).
Non-platform changes must be grouped under relevant section for plugin.

The following problem patterns are supported and must be followed EXACTLY (e.g., no '#' instead of '.'):

<package name> package removed

<class name> class removed
<class name> class renamed to <new class name>
<class name> class moved to package <package name>

<class name>.<method name>(<human-readable parameters>) marked abstract
<class name>.<method name>(<human-readable parameters>) abstract method added
<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method moved to the superclass
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name> method <parameter name> parameter marked @<class name>
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?
<class name> (class|interface) now (extends|implements) <class name> and inherits its abstract method <method name>(<human-readable parameters>)?
<class name> class now interface

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field moved to the superclass
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<property name> property removed from resource bundle <bundle name>

Where the placeholders must be enclosed in code quotes (`name`):

<class name> is a fully-qualified name of the class, e.g. `com.intellij.openapi.actionSystem.AnAction$InnerClass`.
<method name> is the exact method's name. Note that constructors have dedicated patterns.
<human-readable parameters> is a string representing parameters, which are not necessarily fully qualified. They do not affect the parser. For example, instead of (java.lang.Object, java.util.List, int) you are free to write (Object, List<String>, int)
<parameter name> is exact name of the method's parameter
<property name> is a full name of a property from .properties file, like `some.action.description`
<bundle name> is a fully qualified name of the property bundle, which includes its package, like `message.IdeBundle`

NOTE: If a code change you're trying to document doesn't match any of the above patterns, please ask in #plugins-verifier

NOTE: You are allowed to prettify the pattern using links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes and will be skipped in API verification.
-->

<link-summary>List of known Breaking API Changes in 2023.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2023.3

### IntelliJ Platform 2023.3

Threading Model changes
: Please see updated [](threading_model.md).

Removal of commons-lang2 and commons-collections libraries
: _commons-lang2_ library is going to be removed, a temporary compatibility layer (marked with `@Deprecated(forRemoval = true)` to highlight usages) is bundled.
Please consider migrating to either _commons-lang3_ or _commons-text_ libraries and bundle them with your plugin.
Library _commons-collections_ is going to be removed.

JsonPath library unbundled
: Bundle it [explicitly](https://youtrack.jetbrains.com/issue/IDEA-328219) with your plugin.

`com.intellij.codeInsight.intention.BaseElementAtCaretIntentionAction.isAvailable` method `Editor` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare it as nullable.

`com.intellij.codeInsight.intention.BaseElementAtCaretIntentionAction.invoke` method `Editor` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare it as nullable.

`com.intellij.openapi.actionSystem.ex.ActionUtil.showDumbModeWarning(Project, AnActionEvent[])` method removed
: Use `showDumbModeWarning(Project project, AnAction action, AnActionEvent @NotNull ... events)` instead.

`com.intellij.profiler.eventtrace` package removed
: Update code usages.

`org.jetbrains.plugins.gradle.service.project.GradleProjectResolverUtil.buildDependencies(ProjectResolverContext, Map, Map, DataNode, Collection, DataNode)` method parameter type changed from `Map<String, String>` to `ArtifactMappingService`
: Update usages of this method. Change parameter `artifactsMap` value to an `ArtifactMappingService` instance. It can be obtained from `ProjectResolverContext`, or created in-place using the `MapBasedArtifactMappingService`.

`org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.CONFIGURATION_ARTIFACTS` field removed
: Related mapping information is no longer accessible using this key. Artifacts mapping data is now stored in the instance of the `ArtifactMappingService` and can be obtained via `org.jetbrains.plugins.gradle.service.project.ProjectResolverContext#getArtifactsMap()`.

`com.intellij.ide.plugins.enums.PluginsGroupType.FEATURED` enum renamed to `com.intellij.ide.plugins.enums.PluginsGroupType.STAFF_PICKS`
: Use `com.intellij.ide.plugins.enums.PluginsGroupType.STAFF_PICKS` instead.

`com.intellij.ide.plugins.newui.SearchWords.ORGANIZATION` enum renamed to `com.intellij.ide.plugins.newui.SearchWords.VENDOR`
: Use `com.intellij.ide.plugins.newui.SearchWords.VENDOR` instead.

`com.intellij.execution.console.ConsoleHistoryCopyHandler.PROMPT_LENGTH_MARKER` field removed
: Use `com.intellij.execution.console.ConsoleHistoryCopyHandlerKt#PROMPT_LENGTH_MARKER` instead.

### Collaboration Tools Module 2023.3

`com.intellij.collaboration.ui.codereview.action.CodeReviewCheckoutRemoteBranchAction` class removed
: Action moved to a view model.

`com.intellij.collaboration.ui.codereview.details.CodeReviewDetailsBranchComponentFactory.create(CoroutineScope, CodeReviewBranchesViewModel, AnAction, DataContext)` method removed
: Action moved to a view model.

`com.intellij.collaboration.ui.codereview.diff.viewer.DiffEditorUtilKt` class removed
: Moved to `com.intellij.collaboration.ui.codereview.editor.EditorComponentInlaysUtilKt.controlInlaysIn`.

`com.intellij.collaboration.ui.toolwindow.ReviewListTabComponentDescriptor` class removed
: Descriptor removed in favour of tab type `com.intellij.collaboration.ui.toolwindow.ReviewTab`.

`com.intellij.collaboration.ui.toolwindow.ReviewTabsController` class removed
: Controller reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewToolwindowDataKeys.getREVIEW_TABS_CONTROLLER()` method removed
: Controller reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectContext` class removed
: Context reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewToolwindowViewModel.getProjectVm()` method return type changed from `SharedFlow<C>` to `SharedFlow<PVM>`
: Context reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewTabsComponentFactory.createReviewListComponent(CoroutineScope, PVM)` abstract method added
: Context reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewTabsComponentFactory.createTabComponent(CoroutineScope, PVM, TVM)` abstract method added
: Context reworked to viewmodel `com.intellij.collaboration.ui.toolwindow.ReviewToolwindowProjectViewModel`.

`com.intellij.collaboration.ui.toolwindow.ReviewToolwindowTabsManagerKt.manageReviewToolwindowTabs(CoroutineScope, ToolWindow, ReviewToolwindowViewModel, ReviewTabsController, ReviewTabsComponentFactory, String)` method removed
: Added a tab viewmodel type.

`com.intellij.collaboration.ui.codereview.timeline.TimelineDiffComponentFactory.createDiffComponent(Project, EditorFactory, PatchHunk, Pair, Pair)` method removed
: Coroutine scope was added to track editor lifetime.

`com.intellij.collaboration.ui.codereview.details.model.CodeReviewChangesViewModelBase` class removed
: Incorrect [EDT](threading_model.md)-reliant implementation removed.

`com.intellij.collaboration.async.CoroutineUtilKt.DisposingScope(Disposable, CoroutineContext)` method removed
: Use `com.intellij.collaboration.async.CoroutineUtilKt.disposingScope(CoroutineContext)` instead.

`com.intellij.collaboration.ui.codereview.details.model.CodeReviewChangesViewModel.getReviewCommits()` method return type changed from `Flow` to `SharedFlow`
: Concrete type usage forced to ensure correct behavior

`com.intellij.collaboration.ui.codereview.details.model.CodeReviewChangesViewModel.getSelectedCommit()` method return type changed from `Flow` to `SharedFlow`
: Concrete type usage forced to ensure correct behavior

`com.intellij.collaboration.ui.codereview.details.model.CodeReviewChangesViewModel.getSelectedCommitIndex()` method return type changed from `Flow` to `SharedFlow`
: Concrete type usage forced to ensure correct behavior

`com.intellij.collaboration.auth.ui.login.LoginTokenGenerator.generateToken(String)` method return type changed from `String` to `void`
: Removed unused return value

`com.intellij.collaboration.auth.ui.login.TokenLoginDialog(Project, Component, LoginModel, String, DialogPanelSupplier)` constructor parameter type changed from `() -> DialogPanel`  to `CoroutineScope.() -> DialogPanel`
: Allow using dialog scope in dialog panel

`com.intellij.collaboration.ui.codereview.list.search.ChooserPopupUtil.showAsyncChooserPopup(RelativePoint, Flow<List<T>>, Mapper, ListCellRenderer, PopupConfig)` method parameter type changed from `Flow<List<T>>` to `Flow<Result<List<T>>>`
: Handle list loading errors inside the popup

`com.intellij.collaboration.ui.codereview.list.search.ChooserPopupUtil.showAsyncChooserPopup(RelativePoint, Flow<List<T>>, Presenter, PopupConfig)` method parameter type changed from `Flow<List<T>>` to `Flow<Result<List<T>>>`
: Handle list loading errors inside the popup

### Java Plugin 2023.3

`com.siyeh.ipp.base.Intention` class removed
: As a part of migration to new experimental [`ModCommand`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModCommand.java) API, the class was removed completely. It's a part of implementation module and was never intended to be inherited by external plugins. Consider implementing [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) directly.

`com.intellij.codeInsight.TailTypes` class renamed to `com.intellij.codeInsight.JavaTailTypes`
: Update code usages.

`com.intellij.unscramble.ThreadDumpParser` class moved to package `com.intellij.threadDumpParser`
: Update code usages.

### JavaScript Plugin 2023.3

`com.intellij.lang.javascript.buildTools.npm.PackageJsonUtil.createPackageJson(PsiDirectory, boolean)` method removed
: Use `com.intellij.lang.javascript.buildTools.npm.PackageJsonFileTemplate.create(PsiDirectory, boolean, Consumer<PsiFile>)` instead.

### External System Run Configuration 2023.3

Property `com.intellij.openapi.externalSystem.service.ui.command.line.CompletionTableInfo.completionInfo` replaced by suspend function `CompletionTableInfo.collectCompletionInfo`
: Implement the new function to support async completion collecting.

Property `com.intellij.openapi.externalSystem.service.ui.command.line.CompletionTableInfo.tableCompletionInfo` replaced by suspend function `CompletionTableInfo.collectTableCompletionInfo`
: Implement the new function to support async completion collecting.

Function `com.intellij.openapi.externalSystem.service.ui.completion.TextCompletionField.getCompletionVariants` replaced by property `TextCompletionField.completionCollector`
: Implement the new function to support async completion collecting.

`com.intellij.openapi.externalSystem.service.execution.configuration.SettingsFragmentsContainer` class renamed to `com.intellij.openapi.externalSystem.service.execution.configuration.fragments.SettingsEditorFragmentContainer`
: Use new run configuration fragment builders.

`com.intellij.openapi.externalSystem.service.execution.configuration.SettingsEditorLabeledComponent` class moved to package `com.intellij.openapi.externalSystem.service.execution.configuration.fragments`
: Use the new run configuration fragment builders.

Parameter type of fragment builder functions from `ExternalSystemRunConfigurationUtil` file changed from `C` to `(Disposable) -> C`
: Use the new run configuration fragment builders.

Fragment builder functions from `ExternalSystemRunConfigurationUtil` file moved to `SettingsEditorFragmentBuilders` and `ExternalSystemRunConfigurationFragmentBuilders` files
: Use the new run configuration fragment builders.

### Kotlin Plugin 2023.3

`org.jetbrains.kotlin.idea.actions.JavaToKotlinAction.Companion` class renamed to `org.jetbrains.kotlin.idea.actions.JavaToKotlinAction.Handler`
: In order to not load additional code eagerly on action instantiation.

`org.jetbrains.kotlin.idea.compiler.configuration.KotlinIdePluginVersion.Companion` class removed
: Now, the Kotlin plugin version does not include a compiler version, so the class is unnecessary. Use `com.intellij.openapi.application.ApplicationInfo` to get the IntelliJ version.

`org.jetbrains.kotlin.idea.compiler.configuration.KotlinIdePluginVersion` class removed
: Now, the Kotlin plugin version does not include a compiler version, so the class is unnecessary. Use `com.intellij.openapi.application.ApplicationInfo` to get the IntelliJ version.


### Markdown Plugin 2023.3

`org.intellij.plugins.markdown.editor.images` package removed
: Use `org.intellij.plugins.markdown.images` from `intellij.markdown.images` module.

`org.intellij.plugins.markdown.lang.psi.MarkdownPsiElementFactory.createHtmlBlockWithImage(Project, MarkdownImageData)` method removed
: Use `org.intellij.plugins.markdown.images.editor.ImagePsiElementFactory.createHtmlBlockWithImage` instead.

`org.intellij.plugins.markdown.lang.psi.MarkdownPsiElementFactory.createHtmlImageTag(Project, MarkdownImageData)` method removed
: Use `org.intellij.plugins.markdown.images.editor.ImagePsiElementFactory.createHtmlImageTag` instead.

`org.intellij.plugins.markdown.lang.psi.MarkdownPsiElementFactory.createImage(Project, String, String, String)` method removed
: Use `org.intellij.plugins.markdown.images.editor.ImagePsiElementFactory.createImage`.

`action.org.intellij.plugins.markdown.ui.actions.styling.InsertImageAction.insert.popup.text` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.browse.image.title` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.convert.to.html.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.description.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.height.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.path.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.screen.reader.text.panel.title` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.title.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.configure.image.dialog.width.label` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

`markdown.insert.image.dialog.title` property removed from resource bundle `messages.MarkdownBundle`
: Use `org.intellij.plugins.markdown.images.MarkdownImagesBundle` instead.

### Python Plugin 2023.3

`org.jetbrains.plugins.notebooks.jupyter.variables` package removed
: It is now part of separate _Jupyter_ plugin.

`com.jetbrains.python.psi.PyClass.getPropertiesInherited(TypeEvalContext)` abstract method added
: Should implement this method.

### Database Plugin 2023.3

`com.intellij.database.datagrid.DataGrid.getName(ModelIndex<GridColumn>)` method removed
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setDisplayType(ModelIndex<GridColumn>, DisplayType)` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getDisplayType(ModelIndex<GridColumn>)` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getPureDisplayType(ModelIndex<GridColumn>)` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setBinaryDisplayType(ModelIndex<GridColumn>, BinaryDisplayType)` method removed
: Use `com.intellij.database.datagrid.DataGrid.setDisplayType(ModelIndex<GridColumn>, DisplayType)` instead.

`com.intellij.database.datagrid.DataGrid.getBinaryDisplayType(ModelIndex<GridColumn>)` method removed
: Use `com.intellij.database.datagrid.DataGrid.getDisplayType(ModelIndex<GridColumn>)` instead.

`com.intellij.database.datagrid.DataGrid.getPureBinaryDisplayType(ModelIndex<GridColumn>)` method removed
: Use `com.intellij.database.datagrid.DataGrid.getPureDisplayType(ModelIndex<GridColumn>)` instead.

### Package Checker Plugin 2023.3

`com.intellij.packageChecker.maven.MavenSharedDependenciesModel` class renamed to `com.intellij.packageChecker.java.BuildSystemDependenciesModelBase`
: Renamed to a more correct and generic name.

## 2023.2

### IntelliJ Platform 2023.2

`com.intellij.openapi.actionSystem.AnActionEvent.getInputEvent()` method return type changed from `InputEvent` to `@Nullable InputEvent`
: This may break source-compatibility with inheritors written in Kotlin.

Specify `displayName`/`key` for `Configurable`
: To improve performance, provide either attribute for <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationConfigurable"/></include> or <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectConfigurable"/></include> (see [](settings_guide.md)).

`com.intellij.remote.RemoteProcess.setWindowSize(int, int)` abstract method added
: Should implement this method.

### Database Plugin 2023.2

`com.intellij.database.dataSource.DataSourceStorageCore` class removed
: Use `com.intellij.database.dataSource.DataSourceStorage` instead.

`com.intellij.database.dataSource.DataSourceStorageUtil` class removed
: Use `com.intellij.database.dataSource.DataSourceStorage` instead.

`com.intellij.database.dataSource.DataSourceStorageCore$Listener` class renamed to `com.intellij.database.dataSource.DataSourceStorage$Listener`
: `Core` class removed from hierarchy.

`com.intellij.database.datagrid.CoreGrid.showCell(ModelIndex<Row>, ModelIndex<Column>)` method parameter type changed from `ModelIndex<Row>` to `int`
: `ModelIndex` class is used to reference data in the table model. Row indexes in the table model start with 0, even when the table is scrolled to page _N>1_. Parameter type was changed to `int` to indicate that it is an absolute index in the DB table.

`com.intellij.database.datagrid.DataGrid.getName(ModelIndex<GridColumn>)` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

### Maven Plugin 2023.2

`org.jetbrains.idea.maven.server.MavenEmbedderWrapper.customizeForResolve(MavenConsole, MavenProgressIndicator)` method removed
: Use `resolveProject(Collection, MavenExplicitProfiles, ProgressIndicator, MavenSyncConsole, MavenConsole, MavenWorkspaceMap, boolean)` instead.

`org.jetbrains.idea.maven.server.MavenEmbedderWrapper.execute(VirtualFile, Collection, Collection, List)` method removed
: Use `executeGoal(Collection, String, MavenProgressIndicator, MavenConsole)` instead.

### Apache Velocity Plugin 2023.2

`com.intellij.velocity.psi.VtlVariable.getPsiType()` method return type changed from `com.intellij.psi.PsiType` to `com.intellij.velocity.psi.VtlVariableType`
: Use `com.intellij.velocity.java.reference.VtlPsiType` to wrap Java types to return result from implementations.

### JSON Path Plugin 2023.2

Moved package `com.intellij.jsonpath`
: It is now part of separate _JSONPath_ plugin. [Add dependency](plugin_dependencies.md) on `com.intellij.jsonpath` to use its API.

## 2023.1

### IntelliJ Platform 2023.1

Apache Batik library unbundled
: Please bundle and use [echosvg](https://github.com/css4j/echosvg) library instead.

`com.intellij.grazie.text.RuleGroup.Companion.getLITERALS() method removed`
: Replaced by constant field `LITERALS`.

`com.intellij.ui.treeStructure.SimpleNode.doUpdate()` method removed
: It was replaced by `doUpdate(PresentationData)` which should now only modify the state of its parameter.

`com.intellij.ide.ui.laf.darcula.ui.DarculaProgressBarUI.getFinishedColor()` method removed
: Use `getFinishedColor(JComponent c)` overload instead.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String)` method removed
: Use `setNameAndTooltip(PresentationData, String, String)` overload instead.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String, String)` method removed
: Use `setNameAndTooltip(PresentationData, String, String, String)` overload instead.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String, SimpleTextAttributes)` method removed
: Use `setNameAndTooltip(PresentationData, String, String, SimpleTextAttributes)` overload instead.

`com.intellij.ssh.config.unified.SshConfigManager.register(boolean, String, String, String, String, AuthType, String, String, boolean, boolean, String, String)` method parameter `String` removed
: Local port is the part of the tunnel configuration, not SSH settings.

`com.intellij.openapi.fileEditor.impl.HTMLEditorProvider.Companion.getAFFINITY_KEY()` method removed
: It was an accidentally exposed internal API. Please use `HTMLEditorProvider.openEditor()` methods, or implement your own file editor provider.

`com.intellij.execution.RunnerAndConfigurationSettings.setFocusToolWindowBeforeRun(boolean)` abstract method added
: The interface is not intended to be implemented in external plugins.

`com.intellij.execution.RunnerAndConfigurationSettings.getFocusToolWindowBeforeRun()` abstract method added
: The interface is not intended to be implemented in external plugins.

### Database Plugin 2023.1

`com.intellij.database.dataSource.url.TypeDescriptor.ParamEditor` class moved to package `com.intellij.database.dataSource.url`
: The inner interface was moved to upper level.

`com.intellij.database.dataSource.url.TypesRegistry.BaseTypeDescriptor` class moved to package `com.intellij.database.dataSource.url.ui`
: UI extracted from `TypesRegistry` to `TypesRegistryUi`. Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.database.urlParamEditorProvider"/></include> to register parameter descriptor, use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.database.urlParamEditorUiProvider"/></include> to register parameter editor descriptor.

`com.intellij.database.datagrid.DataGrid.setAnonymousColumnName(String)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewAdditionalRowsCount(int)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewAllowMultilineColumnLabels(boolean)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewPaintHorizontalLines(boolean)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewShowRowNumbers(boolean)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewTransparentHeaderBackground(boolean)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.setResultViewVisibleRowCount(int)` method removed
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.countSortedColumns()` abstract method added
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getAppearance()` abstract method added
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getHoveredRowBackground()` abstract method added
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getStripeRowBackground()` abstract method added
: Only recompilation needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.plan.ExplainPlanProvider.createRawExplainTask(LocalDataSource dataSource, boolean analyze)` abstract method added
: Should implement this method.

### JavaScript Plugin 2023.1

`com.intellij.lang.javascript.JavascriptParserDefinition.createFile(FileViewProvider)` method return type changed from `com.intellij.lang.javascript.psi.JSFile` to `com.intellij.psi.PsiFile`
: Required to support Astro file format.

### JavaScript Debugger Plugin 2023.1

`org.jetbrains.wip.WipVm.initDomains()` method return type changed from `void` to `org.jetbrains.concurrency.Promise<*>`
: `initDomains()` is now awaitable to make WIP/CDP domains-dependent initialization logic possible.

`org.jetbrains.wip.WipVm.ready()` method return type changed from `void` to `org.jetbrains.concurrency.Promise<*>`
: `ready()` is now awaitable to make WIP/CDP connection-dependent initialization logic possible.

### JetBrains Gateway Plugin 2023.1

`com.jetbrains.gateway.ssh.HighLevelHostAccessor.isPathPresentOnRemote(String, FileSystemItem)` method parameter type changed from `String` to `com.jetbrains.gateway.ssh.deploy.RemotePath`
: Using strings instead of paths breaks a lot of things when doing cross-platform development, and is generally not a good idea. Use `com.jetbrains.gateway.ssh.HighLevelHostAccessor.makeRemotePath` to prepare a path to pass into any of the methods requiring it.

### PHP Plugin 2023.1

`com.jetbrains.php.config.library.PhpIncludePathManager.getRoots()` method removed
: This class no longer handles all paths configured in non-runtime `com.jetbrains.php.config.library.PhpLibraryRootProvider`. Paths configured in <control>Include Path Settings</control> are available with `PhpIncludePathManager.getIncludePaths()`. All additional paths to use as roots for resolving via `PhpIncludePathManager.getAllIncludedRoots()`.
