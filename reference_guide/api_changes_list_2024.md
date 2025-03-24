<!-- Copyright 2000-2024 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2024.*

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

<link-summary>List of known Breaking API Changes in 2024.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2024.3

### IntelliJ Platform 2024.3

`com.intellij.psi.stubs.StubElement.getChildrenStubs()` method return type changed from `List<StubElement>` to `List<StubElement<?>>`
: Raw-type changed to a properly parameterized type. This is binary compatible change but may cause compilation errors. In most of the cases, it's enough to add `<?>` at the use site to fix the issue.

Unbundled JUnit library
: Add an explicit dependency in the plugin project.

`com.intellij.openapi.actionSystem.impl.MoreActionGroup(boolean, int, DefaultConstructorMarker)` constructor removed
: Use other constructors instead.

`ai.grazie.nlp.stemmer` package removed
: Add an explicit dependency on the NLP platform if needed.

`com.intellij.openapi.externalSystem.model.task.event.ExternalSystemStartEventImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.ExternalSystemStartEvent`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.TaskOperationDescriptorImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.TaskOperationDescriptor`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.DefaultOperationResult` class renamed to `com.intellij.openapi.externalSystem.model.task.event.OperationResult`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.FailureResultImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.FailureResult`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.SkippedResultImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.SkippedResult`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.SuccessResultImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.SuccessResult`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.ExternalSystemStatusEventImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.ExternalSystemStatusEvent`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.FailureImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.Failure`
: Use new class

`com.intellij.openapi.externalSystem.model.task.event.ExternalSystemFinishEventImpl` class renamed to `com.intellij.openapi.externalSystem.model.task.event.ExternalSystemFinishEvent`
: Use new class

Interface `com.intellij.openapi.externalSystem.model.task.event.TaskOperationDescriptor` changed to class
: Recompile your plugin with new class

`com.intellij.openapi.externalSystem.importing.AbstractOpenProjectProvider.linkToExistingProjectAsync(VirtualFile arg0, Project arg1, Continuation arg2)` method marked final
: Override method `com.intellij.openapi.externalSystem.importing.AbstractOpenProjectProvider#linkProject` instead



### JSON Plugin (new) 2024.3

`com.intellij.json.JsonElementTypes` class removed
:
<snippet id="json-plugin-fix">
Add an [explicit dependency](plugin_dependencies.md) on the newly extracted JSON plugin (`com.intellij.modules.json`) in `plugin.xml`.<br/>If the plugin is built against 2024.3+, also add `com.intellij.modules.json` to the [bundled plugins](tools_intellij_platform_gradle_plugin_dependencies_extension.md#bundled-plugin) in the Gradle build script.
</snippet>

`com.intellij.json.JsonFileType` class removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.JsonLanguage` class removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.JsonParserDefinition` class removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.JsonTokenType` class removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json` package removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.codeinsight` package removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.highlighting` package removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.intellij.json.psi` package removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

`com.jetbrains.jsonSchema` package removed
: <include from="api_changes_list_2024.md" element-id="json-plugin-fix"/>

### Database Plugin 2024.3

`com.intellij.database.datagrid.DataGrid.getFormatterConfig(ModelIndex)` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

### Package Checker 2024.3

`com.intellij.packageChecker.model.Dependency(Package, Set, DataContext)` constructor removed
: Use other constructors instead.

### HTTP Client Plugin 2024.3

`com.intellij.httpClient.http.request.run.HttpRunRequestInfo` class moved to package `com.intellij.httpClient.http.request.run.info`
: Update code usages.

### JavaScript Plugin 2024.3

`com.intellij.lang.ecmascript6.JSXHarmonyFileType` class renamed to `com.intellij.lang.javascript.JSXFileType`
: Update code usages.

Interface `com.intellij.lang.javascript.JSElementTypes` no longer extends `com.intellij.lang.javascript.JSStubElementTypes`
: Update code usages.

Field `com.intellij.lang.javascript.JavaScriptSupportLoader.ECMA_SCRIPT_L4` moved to `com.intellij.lang.javascript.flex.FlexSupportLoader`
: Update code usages.

### Kotlin Plugin 2024.3

`org.jetbrains.kotlin.idea.quickfix.AddAnnotationFix(KtElement, ClassId, AddAnnotationFix.Kind, FqName, SmartPsiElementPointer, int, DefaultConstructorMarker)` constructor parameter type changed from `FqName` to `List<String>`
: Update code usages.


## 2024.2

### IntelliJ Platform 2024.2

Constructor of `com.intellij.ui.tabs.TabInfo` requires not-null `JComponent` argument.

`com.intellij.execution.lineMarker.ExecutorAction.Companion.wrap(RunContextAction, Int)` method removed
: Use `com.intellij.execution.lineMarker.ExecutorAction.Companion.wrap(AnAction, Executor, Int)` instead.

`org.apache.sanselan.util` package removed
: `org.apache.sanselan.util.IOUtils` compatibility shim is obsolete; instead, please use JRE methods or `org.apache.commons.io.IOUtils`.

`com.intellij.platform.workspace.storage.url.VirtualFileUrlManager.getOrCreateFromUri(String)` method removed
: Use `com.intellij.platform.workspace.storage.url.VirtualFileUrlManager.getOrCreateFromUrl(String)` instead.

`com.intellij.platform.workspace.jps.entities.DependenciesKt.modifyEntity(MutableEntityStorage, LibraryEntity, Function1)` method removed
: Use `com.intellij.platform.workspace.jps.entities.DependenciesKt.modifyLibraryEntity(MutableEntityStorage, LibraryEntity, Function1)` instead.

`ai.grazie.nlp.utils.UtilsKt.tokenizeByWhitespace(String)` method removed
: NLP platform internals.

### UML Plugin 2024.2

`com.intellij.uml.UmlGraphBuilder(Project, Graph2D, Graph2DView, DiagramDataModel<?>, DiagramPresentationModel)` constructor removed
: Use `com.intellij.uml.UmlGraphBuilder(Project, Graph2D, Graph2DView, DiagramDataModel<?>, GraphThreadingType, DiagramPresentationModel)` instead.

`com.intellij.openapi.graph.impl.builder.GraphBuilderImpl.addEdge(Object, Object, Object)` method removed
: Use `com.intellij.openapi.graph.impl.builder.GraphBuilderImpl.addEdge(Object, Object, String, Object, Object[], Function<? super E,? extends Edge>)` instead.

### Python Plugin 2024.2

`com.jetbrains.python.PyElementTypes.STATEMENT_LIST` field type changed from `PyElementType` to `IElementType`
: Update code usages.

### Kotlin Plugin 2024.2

`org.jetbrains.kotlin.analysis.low.level.api.fir.LLFirResolveSessionService.getFirResolveSessionNoCaching(module: KtModule)` method removed
: Internals of Kotlin plugin.

`org.jetbrains.kotlin.analysis.project.structure.ProjectStructureProvider.Companion.getModule(Project, PsiElement, KtModule)` method removed
: Use `org.jetbrains.kotlin.analysis.api.projectStructure.KaModuleProvider.Companion.getModule(Project, PsiElement, KtModule)` instead.

`org.jetbrains.kotlin.codegen.state.TypeMappingUtil.getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME` method removed
: Use `org.jetbrains.kotlin.name.JvmStandardClassIds.getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME` instead.

`org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt.renderReadableWithFqNames(ConeKotlinType)` method removed
: Internals of Kotlin compiler.

`org.jetbrains.kotlin.analysis.project.structure.KtModule` class renamed to `org.jetbrains.kotlin.analysis.api.projectStructure.KaModule`
: Update code usages.

`org.jetbrains.kotlin.fir.expressions.FirAssignmentOperatorStatement` class removed
: Internals of Kotlin compiler.


### Database Plugin 2024.2

`com.intellij.database.datagrid.DataGrid.getCoroutineScope()` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getModificationTracker()` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.adaptForNewQuery()` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

### HTTP Client Plugin 2024.2

`com.intellij.httpClient.http.request.HttpRequestPsiConverter.toRequestConfig(HttpRequest)` method visibility changed from `public` to `private`
: This method is an implementation detail.

### Dependency Analysis Plugin 2024.2

`com.jetbrains.dependencyAnalysis` package removed
: The package `com.jetbrains.dependencyAnalysis` has been removed. Please update your code to use the new package `com.jetbrains.dependencyAnalysis.core` instead.

## 2024.1

### IntelliJ Platform 2024.1

`com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages, List<PsiElement> elements)` abstract method added
: Use instead of `com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages)` and `com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages, PsiElement primaryElement)`.

`com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages)` method removed
: Use `com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages, List<PsiElement> elements)` instead.

`com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages, PsiElement primaryElement)` method removed
: Use `com.intellij.refactoring.RefactoringHelper.prepareOperation(UsageInfo [] usages, List<PsiElement> elements)` instead.

`com.jetbrains.commandInterface.commandLine.psi.CommandLineFile` class moved to package `com.intellij.commandInterface.commandLine.psi`
: Update code usages.

`com.jetbrains.commandInterface.commandLine.CommandLineLanguage` class moved to package `com.intellij.commandInterface.commandLine`
: Update code usages.

`com.jetbrains.commandInterface.commandLine.psi.CommandLineArgument` class moved to package `com.intellij.commandInterface.commandLine.psi`
: Update code usages.

`com.jetbrains.commandInterface.commandLine.psi.CommandLineOption` class moved to package `com.intellij.commandInterface.commandLine.psi`
: Update code usages.

`com.intellij.application.options.editor.CodeFoldingConfigurable.applyCodeFoldingSettingsChanges()` method removed
: Use top-level method `CodeFoldingConfigurableKt.applyCodeFoldingSettingsChanges` instead.

`com.intellij.ide.bookmark.providers.LineBookmarkProvider.Companion` class removed
: Use `com.intellij.ide.bookmark.providers.LineBookmarkProvider.Util` instead.

`com.intellij.execution.process.mediator.util` package removed
: The sole extension method `Deferred<T>.blockingGet()` contained in this package is an anti-pattern, and was not supposed to be exposed in the first place. The process mediator and the elevation service are now product modules, and no longer part of the platform.

`com.intellij.vcs.log.VcsLogFileHistoryHandler.getSupportedVcs()` abstract method added
: Must be implemented.

`com.intellij.vcs.log.VcsLogFileHistoryHandler.getHistoryFast(root: VirtualFile, filePath: FilePath, hash: Hash?, filters: VcsLogFilterCollection, commitCount: Int)` abstract method added
: Parameter `filters: VcsLogFilterCollection` was added to provide filtering capabilities to file history. Implement `com.intellij.vcs.log.VcsLogFileHistoryHandler.getSupportedFilters` to specify which filters are supported by this extension (currently, branch filter, revision filter and range filter are available).

`com.intellij.vcs.log.VcsLogFileHistoryHandler.collectHistory(root: VirtualFile, filePath: FilePath, hash: Hash?, filters: VcsLogFilterCollection, consumer)` abstract method added
: Parameter `filters: VcsLogFilterCollection` was added to provide filtering capabilities to file history. Implement `com.intellij.vcs.log.VcsLogFileHistoryHandler.getSupportedFilters` to specify which filters are supported by this extension (currently, branch filter, revision filter and range filter are available).

`org.apache.tools` package removed
: Please provide all necessary libraries in your plugin distribution.

`com.intellij.openapi.projectRoots.impl.ProjectJdkImpl.readExternal(Element, ProjectJdkTable)` method removed
: Use `com.intellij.openapi.projectRoots.impl.ProjectJdkImpl.readExternal(Element, Function<String, SdkTypeId>)` instead.

`com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil.createSdk(Collection<Sdk>, String, SdkType, SdkAdditionalData, String)` method return type changed from `ProjectJdkImpl` to `Sdk`
: Update code usages.

`com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil.createSdk(Collection<Sdk>, VirtualFile, SdkType, SdkAdditionalData, String)` method return type changed from `ProjectJdkImpl` to `Sdk`
: Update code usages.

Class `com.intellij.diff.editor.DiffVirtualFile` now extends `com.intellij.diff.editor.DiffViewerVirtualFile` and inherits its abstract method `com.intellij.diff.editor.DiffViewerVirtualFile.createViewer(Project)`
: Update code usages.

`com.intellij.diff.tools.combined.CombinedDiffVirtualFile.getSourceId()` method removed
: Update code usages.

`com.intellij.diff.tools.combined.CombinedDiffModel` interface removed
: Use `com.intellij.diff.tools.combined.CombinedDiffModel` class instead.

`com.intellij.diff.tools.combined.CombinedDiffVirtualFile.createViewer(Project)` abstract method added
: Must be implemented.

`com.intellij.openapi.util.io.NioPathUtil.isAncestor(Path, Path, boolean)` method removed
: Use `Path.startsWith()` instead.

`com.intellij.util.CachedValueBase.setData(CachedValueBase.Data)` abstract method added
: Must be implemented.

`com.intellij.util.CachedValueBase.getRawData()` abstract method added
: Must be implemented.

Visibility of class `com.intellij.util.CachedValuesFactory` changed from public to internal
: The class is not supposed to be used directly.

`com.intellij.ui.popup.ActionPopupStep.performAction(AnAction, InputEvent)` method parameter type changed from `AnAction` to `ActionItem`
: Use `com.intellij.ui.popup.ActionPopupStep.performActionItem(ActionItem, InputEvent)` instead.

`com.intellij.openapi.actionSystem.AnAction.getTemplateText()` method marked final
: Use `AnAction.getTemplatePresentation().setText()` instead.

`com.intellij.openapi.actionSystem.ActionGroup.isPopup()` method marked final
: Use `ActionGroup.getTemplatePresentation().setPopupGroup(boolean)` instead.

`com.intellij.webcore.packaging.InstalledPackagesPanel.myInstallButton` field removed
: Use `InstalledPackagesPanel.myInstallEnabled` instead.

### UML Plugin 2024.1

`com.intellij.uml.core.actions.ShowDiagramBase.findProviders(AnActionEvent, DiagramProvider, BiFunction)` method removed
: Use `com.intellij.uml.core.actions.ShowDiagramBase.findProviders(DiagramProvider<?>, BiFunction<? super DiagramProvider<?>,? super DataContext,java.lang.Object>, DataContext)` instead.

### Java Plugin 2024.1

`com.intellij.lang.properties.RemovePropertyLocalFix` class removed
: Use `com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix` instead.

### Django Plugin 2024.1

Package `com.jetbrains.jinja2` renamed to `com.intellij.jinja`
: Update code usages.

### Restructured Text Plugin 2024.1

`com.jetbrains.rest.RestLanguage` class moved to package `com.intellij.python.reStructuredText`
: Update code usages.

### GitHub Plugin 2024.1

`org.jetbrains.plugins.github.pullrequest.comment.GHPRDiffReviewSupport` class removed
: Migrated to MVVM.

`org.jetbrains.plugins.github.pullrequest.comment.GHPRDiffReviewSupport.Companion` class removed
: Migrated to MVVM.

`org.jetbrains.plugins.github.pullrequest.action.GHPRActionKeys.getPULL_REQUEST_DATA_PROVIDER()` method removed
: Migrated to MVVM, hidden implementation details.

### Kotlin Plugin 2024.1

`org.jetbrains.kotlin.ir.visitors.IrElementVisitor.visitInlinedFunctionBlock(inlinedFunctionBlock: IrInlinedFunctionBlock, data: D)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.ir.visitors.IrElementVisitor.visitReturnableBlock(returnableBlock: IrReturnableBlock, data: D)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitInlinedFunctionBlock(inlinedFunctionBlock: IrInlinedFunctionBlock)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitInlinedFunctionBlock(inlinedFunctionBlock: IrInlinedFunctionBlock, data: Nothing?)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitReturnableBlock(returnableBlock: IrReturnableBlock)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitReturnableBlock(retunrableBlock: IrReturnableBlock, data: Nothing?)` abstract method added
: Update code usages.

`org.jetbrains.kotlin.fir.expressions.FirConstExpression` class renamed `org.jetbrains.kotlin.fir.expressions.FirLiteralExpression`
: Update code usages.

`org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.DELEGATE` class removed
: `org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.Companion.DELEGATE` should be used instead.

`org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.DELEGATED_MEMBER` class removed
: `org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.Companion.DELEGATED_MEMBER` should be used instead.

`org.jetbrains.kotlin.config.JvmDefaultMode.DEFAULT` field removed
: Use `org.jetbrains.kotlin.config.JvmDefaultMode.DISABLE`.

Method `org.jetbrains.kotlin.backend.common.lower.LocalDeclarationsLoweringKt.getParentsWithSelf(IrDeclaration)` renamed to `org.jetbrains.kotlin.ir.util.IrUtilsKt.getParentsWithSelf`
: Update code usages.

`org.jetbrains.kotlin.daemon.common.CompileService.Companion.getNO_SESSION()` method removed
: Use `org.jetbrains.kotlin.daemon.common.CompileService.NO_SESSION` const instead.

Class `org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl` made final
: Create a new `IrDeclarationOrigin` by delegation. See https://github.com/JetBrains/kotlin/blob/a3b55cf758f3a7ceb596f65507c2f61ada5266af/compiler/ir/ir.tree/src/org/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin.kt#L20.

`org.jetbrains.kotlin.idea.refactoring.introduce.extractFunction.ExtractKotlinFunctionHandlerKt` class removed
: Use `com.intellij.lang.refactoring.RefactoringSupportProvider.getExtractMethodHandler` instead to invoke Kotlin extract function refactoring

### Maven Plugin 2024.1

`org.jetbrains.idea.maven.indices.MavenIndex.getUpdateTimestamp()` method removed
: Use `org.jetbrains.idea.maven.indices.MavenIndexImpl.getUpdateTimestamp()` instead. `MavenIndex` is an obsolete interface now with `MavenIndexImpl` as the only implementation, consider using `MavenGAVIndex` to get information about available Maven GAV coordinates, or `MavenSearchIndex` to search Maven artifacts by content.

`org.jetbrains.idea.maven.indices.MavenIndex.getFailureMessage()` method removed
: Use `org.jetbrains.idea.maven.indices.MavenIndexImpl.getFailureMessage()` instead. `MavenIndex` is an obsolete interface now with `MavenIndexImpl` as the only implementation, consider using `MavenGAVIndex` to get information about available Maven GAV coordinates, or `MavenSearchIndex` to search Maven artifacts by content.

`org.jetbrains.idea.maven.indices.MavenIndex.getRepositoryPathOrUrl()` method removed
: Use `org.jetbrains.idea.maven.indices.MavenRepositoryIndex.getRepository().getUrl()` instead. Also, `MavenRepositoryInfo.getKind()` could be used to distinguish between local and remote repo.

`org.jetbrains.idea.maven.indices.MavenIndicesManager.scheduleUpdateContent(List<MavenIndex>, boolean)` method removed
: Use `org.jetbrains.idea.maven.indices.searcher.MavenLuceneIndexer.update(List<MavenRepositoryInfo>, Boolean)` to update content for lucene indices. You should not care of GAV indices update.

`org.jetbrains.idea.maven.indices.MavenIndicesManager.scheduleUpdateIndicesList(Consumer<MavenIndex>)` method removed
: Use `org.jetbrains.idea.maven.indices.searcher.MavenIndicesManager.scheduleUpdateIndicesList()` to update an indices list for a specific project. To get all search indices for specific project use `MavenSystemIndicesManager.getClassIndexForRepository()`, you can get a list of all repositories with `MavenIndexUtils.getAllRepositories(Project)`.

### Database Plugin 2024.1

`com.intellij.database.datagrid.DataGrid.getLocalFilterState()` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.database.datagrid.DataGrid.getColumnAttributes()` abstract method added
: Only recompilation is needed for classes that implement `DataGrid` and delegate calls to an actual `DataGrid` implementation.

`com.intellij.sql.psi.SqlTableExpression.getSqlType()` method removed
: Use `getDasType()` instead

### HTTP Client Plugin 2024.1

`com.intellij.httpClient.actions.generation.RequestUrlContextInfo(requestContextData: RequestContextData)` constructor parameter removed
: Use `com.intellij.httpClient.actions.generation.RequestBody` and `com.intellij.httpClient.actions.generation.HttpRequestUrlPathInfo.Companion.create()` to describe a request body that will be coomputed lazily during the corresponding request generation.

### Markdown Plugin 2024.1

`org.intellij.plugins.markdown.ui.split` package removed
: Update code usages.

`org.intellij.plugins.markdown.ui.split.SplitTextEditorProvider` class removed
: Use `com.intellij.openapi.fileEditor.TextEditorWithPreviewProvider` instead.

### Python Plugin 2024.1

`com.jetbrains.extensions.ModuleExt` class moved to package `com.jetbrains.python.extensions`
: Update code usages.

`com.jetbrains.extensions.QualifiedNameExt` class moved to package `com.jetbrains.python.extensions`
: Update code usages.

`com.jetbrains.extensions.python.PyCallExpressionExt` class moved to package `com.jetbrains.python.extensions.python`
: Update code usages.

`com.jetbrains.extensions.python.FileChooserDescriptorExtKt` class moved to package `com.jetbrains.python.extensions`
: Update code usages.

`com.jetbrains.python.module.PythonModuleBuilder` class moved to package `com.intellij.python.community.plugin.java.facet`
: Update code usages.

`com.jetbrains.python.facet` package removed
: Private package is no longer available as an API.

`com.jetbrains.python.debugger.remote` package removed
: Private package is no longer available as an API.

`com.jetbrains.django.util.DjangoUtil` class removed
: Private class is no longer available as an API.

`com.jetbrains.django.testRunner` package removed
: Private package is no longer available as an API.

### JavaScript Plugin 2024.1

`com.intellij.lang.javascript.documentation.JSDocumentationProvider.generateDoc(PsiElement, PsiElement)` method marked final
: Override `com.intellij.lang.javascript.documentation.JSDocumentationProvider.generateDoc(PsiElement, PsiElement, Ref<String>)` instead

### Rd Framework 2024.1

`com.jetbrains.rd.framework.IMarshaller.DefaultImpls.getId(IMarshaller)` method removed
: Due to `RdId` becoming a value class, the `getId` method is removed at runtime, causing unresolved method invocations. Use the method that returns long in Java and recompile the Kotlin code.

`com.jetbrains.rd.framework.RdId.write(AbstractBuffer)` method removed
: Due to `RdId` becoming a value class, the `getId` method is removed at runtime, causing unresolved method invocations. Use `AbstractBuffer.writeLong(long)` method in Java and recompile the Kotlin code.

`org.digma.intellij.plugin.rider.protocol.LensPerObjectId.getRdid()` method removed
: Due to `RdId` becoming a value class, the `getId` method is removed at runtime, causing unresolved method invocations. Use the method that returns `long` in Java and recompile the Kotlin code.

`com.jetbrains.rd.framework.RdId.Companion.read(AbstractBuffer)` method removed
: Due to `RdId` becoming a value class, the `getId` method is removed at runtime, causing unresolved method invocations. Use `AbstractBuffer.readLong()` method in Java and recompile the Kotlin code.

`com.jetbrains.rd.framework.base.RdBindableBaseKt.withId(RdBindableBase, RdId)` method removed
: Due to `RdId` becoming a value class, the `getId` method is removed at runtime, causing unresolved method invocations. Use `withId(RdBindableBase, long)` method in Java and recompile the Kotlin code.

### Properties Plugin 2024.1

Added method parameter `Property` to `com.intellij.lang.properties.PropertiesQuickFixFactory.createRemovePropertyLocalFix()`
: Supply the property that the fix should be applied for.
