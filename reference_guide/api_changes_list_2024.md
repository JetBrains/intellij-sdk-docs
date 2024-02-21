<!-- Copyright 2000-2024 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2024.*

<!--
Before documenting a breaking API change, please make sure that the change cannot be avoided in an alternative way.

APIs marked with @Deprecated(forRemoval=true), @ApiStatus.Experimental, @ApiStatus.Internal/IntellijInternalApi, or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern followed by a 2nd line with ": "-prefixed human-readable description and recommended fix/action.

The following problem patterns are supported and must be followed EXACTLY:

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

NOTE: If a code change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack.
An example of a ticket is https://youtrack.jetbrains.com/issue/MP-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes and will be skipped in API verification.
-->

<link-summary>List of known Breaking API Changes in 2024.*</link-summary>

<include from="snippets.md" element-id="apiChangesHeader"/>

<include from="snippets.md" element-id="apiChangesJavaVersion"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradle_plugin_223_problem"/>

## 2024.1

### IntelliJ Platform 2024.2


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
: Parameter `filters: VcsLogFilterCollection` was added to provide filtering capabilities to file history. Implement `com.intellij.vcs.log.VcsLogFileHistoryHandler#getSupportedFilters` to specify which filters are supported by this extension (currently, branch filter, revision filter and range filter are available).

`com.intellij.vcs.log.VcsLogFileHistoryHandler.collectHistory(root: VirtualFile, filePath: FilePath, hash: Hash?, filters: VcsLogFilterCollection, consumer)` abstract method added
: Parameter `filters: VcsLogFilterCollection` was added to provide filtering capabilities to file history. Implement `com.intellij.vcs.log.VcsLogFileHistoryHandler#getSupportedFilters` to specify which filters are supported by this extension (currently, branch filter, revision filter and range filter are available).

Method `com.intellij.psi.util.PsiTreeUtilKt.getFirstLeaf(PsiElement)` renamed to `com.intellij.psi.util.PsiTreeUtilKt.firstLeaf(PsiElement)`
: Update code usages.

Method `com.intellij.psi.util.PsiTreeUtilKt.getLastLeaf(PsiElement)` renamed to `com.intellij.psi.util.PsiTreeUtilKt.lastLeaf(PsiElement)`
: Update code usages.

Method `com.intellij.psi.util.PsiTreeUtilKt.getChildLeafs(PsiElement)` renamed to `com.intellij.psi.util.PsiTreeUtilKt.childLeafs(PsiElement)`
: Update code usages.

`org.apache.tools` package removed
: Please provide all necessary libraries in your plugin distribution.

`com.intellij.openapi.projectRoots.impl.ProjectJdkImpl.readExternal(Element, ProjectJdkTable)` method removed
: Use `com.intellij.openapi.projectRoots.impl.ProjectJdkImpl.readExternal(Element, Function<String, SdkTypeId>)` instead.

`com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil.createSdk(Collection<Sdk>, String, SdkType, SdkAdditionalData, String)` method return type changed from `ProjectJdkImpl` to `Sdk`
: Update code usages.

`com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil.createSdk(Collection<Sdk>, VirtualFile, SdkType, SdkAdditionalData, String)` method return type changed from `ProjectJdkImpl` to `Sdk`
: Update code usages.

### Java Plugin 2024.1

`com.intellij.lang.properties.RemovePropertyLocalFix` class removed
: Use `com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix` instead.

`com.intellij.psi.util.PsiUtil#isLanguageLevel10OrHigher()` method removed
: It's recommended to use a new method `com.intellij.psi.util.PsiUtil#isAvailable()` instead to check whether a particular feature is available, rather than to check against a language level. If you still need an explicit language level check, you may use `PsiUtil.getLanguageLevel(element).isAtLeast(level)`.

`com.intellij.psi.util.PsiUtil#isLanguageLevel11OrHigher()` method removed
: It's recommended to use a new method `com.intellij.psi.util.PsiUtil#isAvailable()` instead to check whether a particular feature is available, rather than to check against a language level. If you still need an explicit language level check, you may use `PsiUtil.getLanguageLevel(element).isAtLeast(level)`.

`com.intellij.psi.util.PsiUtil#isLanguageLevel14OrHigher()` method removed
: It's recommended to use a new method `com.intellij.psi.util.PsiUtil#isAvailable()` instead to check whether a particular feature is available, rather than to check against a language level. If you still need an explicit language level check, you may use `PsiUtil.getLanguageLevel(element).isAtLeast(level)`.

`com.intellij.psi.util.PsiUtil#isLanguageLevel16OrHigher()` method removed
: It's recommended to use a new method `com.intellij.psi.util.PsiUtil#isAvailable()` instead to check whether a particular feature is available, rather than to check against a language level. If you still need an explicit language level check, you may use `PsiUtil.getLanguageLevel(element).isAtLeast(level)`.

`com.intellij.psi.util.PsiUtil#isLanguageLevel17OrHigher()` method removed
: It's recommended to use a new method `com.intellij.psi.util.PsiUtil#isAvailable()` instead to check whether a particular feature is available, rather than to check against a language level. If you still need an explicit language level check, you may use `PsiUtil.getLanguageLevel(element).isAtLeast(level)`.

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

`org.jetbrains.kotlin.ir.visitors.IrElementVisitor.visitReturnableBlock(returnableBlock: IrReturnableBlock, data: D)` abstract method added

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitInlinedFunctionBlock(inlinedFunctionBlock: IrInlinedFunctionBlock)` abstract method added

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitInlinedFunctionBlock(inlinedFunctionBlock: IrInlinedFunctionBlock, data: Nothing?)` abstract method added

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitReturnableBlock(returnableBlock: IrReturnableBlock)` abstract method added

`org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid.visitReturnableBlock(retunrableBlock: IrReturnableBlock, data: Nothing?)` abstract method added

`org.jetbrains.kotlin.fir.expressions.FirConstExpression` class renamed `org.jetbrains.kotlin.fir.expressions.FirLiteralExpression`

`org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.DELEGATE` class removed
: `org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.Companion.DELEGATE` should be used instead

`org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.DELEGATED_MEMBER` class removed
: `org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.Companion.DELEGATED_MEMBER` should be used instead

`org.jetbrains.kotlin.config.JvmDefaultMode.DEFAULT` field removed
: Use `org.jetbrains.kotlin.config.JvmDefaultMode.DISABLE`

Method `org.jetbrains.kotlin.backend.common.lower.LocalDeclarationsLoweringKt.getParentsWithSelf(IrDeclaration)` renamed to `org.jetbrains.kotlin.ir.util.IrUtilsKt.getParentsWithSelf`

`org.jetbrains.kotlin.daemon.common.CompileService.Companion.getNO_SESSION()` method removed
: Use `org.jetbrains.kotlin.daemon.common.CompileService.NO_SESSION` const instead

Class `org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl` made final
: Create a new `IrDeclarationOrigin` by delegation. See https://github.com/JetBrains/kotlin/blob/a3b55cf758f3a7ceb596f65507c2f61ada5266af/compiler/ir/ir.tree/src/org/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin.kt#L20

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

### HTTP Client Plugin 2024.1
`com.intellij.httpClient.actions.generation.RequestUrlContextInfo(requestContextData: RequestContextData)` constructor parameter removed.
: Use `com.intellij.httpClient.actions.generation.RequestBody` and `com.intellij.httpClient.actions.generation.HttpRequestUrlPathInfo.Companion#create` to describe a request body that will be coomputed lazily during the corresponding request generation.

