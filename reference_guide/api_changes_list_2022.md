<!-- Copyright 2000-2024 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2022.*

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

<link-summary>List of known Breaking API Changes in 2022.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2022.3

### IntelliJ Platform 2022.3

`com.intellij.openapi.externalSystem.dependency.analyzer.DependencyAnalyzerExtension.isApplicable(ProjectSystemId)` abstract method added
: Must be implemented.

`com.intellij.openapi.externalSystem.dependency.analyzer.DependencyAnalyzerExtension.createContributor(Project, Disposable)` method parameter `ProjectSystemId` removed
: Adjust your code.

`com.intellij.openapi.externalSystem.dependency.analyzer.DependencyAnalyzerExtension.createContributor(Project, Disposable)` method return type changed from `DependencyAnalyzerContributor?` to `DependencyAnalyzerContributor`
: Implement `isApplicable()` instead.

`com.intellij.openapi.editor.EditorCopyPasteHelper.getSelectionTransferable(Editor editor, EditorCopyPasteHelper.CopyPasteOptions options)` abstract method added
: Must be implemented instead of and preferred over `com.intellij.openapi.editor.EditorCopyPasteHelper.copySelectionToClipboard(Editor)` which now delegates to the new method.

`com.intellij.codeInsight.template.TemplateContextType.EP_NAME` field removed
: Use `com.intellij.codeInsight.template.impl.TemplateContextTypes` to get `TemplateContextType` extensions.

`com.intellij.ide.actions.searcheverywhere.FoundItemDescriptor(I, int, int)` constructor removed
: Use `FoundItemDescriptor(I, int)` instead.

### Database Plugin 2022.3

`com.intellij.database.dataSource.url.TypesRegistry.ParamEditor` class removed
: Use `com.intellij.database.dataSource.url.TypeDescriptor.ParamEditor` instead.

`com.intellij.database.dataSource.url.TypesRegistry.BaseTypeDescriptor.createFieldImpl(String caption, String configuration, DataInterchange interchange)` abstract method added
: Must be implemented.

`com.intellij.database.psi.DbDataSourceImpl.getDelegate()` method return type changed from `DatabaseSystem` to `RawDataSource`
: Prefer `com.intellij.database.psi.DbDataSource.getDelegateDataSource()`.

`com.intellij.database.psi.DbPsiFacadeImpl.createDataSourceWrapperElement(DasDataSource, DataSourceManager)` method parameter type changed from `DasDataSource` to `RawDataSource`
: Avoid manually wrapping data sources.

`com.intellij.database.vfs.DatabaseElementVirtualFileImpl.setBusy(boolean)` method removed
: That method was an internal method.

`com.intellij.database.util.TreePatternNode.Group(ObjectKind, TreePatternNode[], TreePatternNode)` constructor removed
: Do not manipulate TreePatternNodes. Use `TreePatternUtils` to manipulate `TreePatterns`.

`com.intellij.database.util.TreePatternNode.NegativeNaming(ObjectName[])` constructor removed
: Do not manipulate TreePatternNodes. Use `TreePatternUtils` to manipulate `TreePatterns`.

`com.intellij.database.dataSource.DataSourceConfigurable.getDataSource()` method return type changed from `DasDataSource` to `RawDataSource`
: Stricter generic bound.

### TextMate Plugin 2022.3

`org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistry` class now interface
: Construct and mutate the `org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistryImpl` instead.

`org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistry.fillFromPList(CharSequence, Plist)` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistryImpl.fillFromPList(CharSequence, Plist)` instead.

`org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistry.clear()` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistryImpl.clear()` instead.

`org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistry()` constructor removed
: Instantiate `org.jetbrains.plugins.textmate.language.preferences.PreferencesRegistryImpl` instead.

`org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistry` class now interface
: Construct and mutate the `org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistryImpl` instead.

`org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistry.fillVariablesFromPlist(CharSequence, Plist)` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistryImpl.fillVariablesFromPlist(CharSequence, Plist)` instead.

`org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistry.clear()` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistryImpl.clear()` instead.

`org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistry()` constructor removed
: Instantiate `org.jetbrains.plugins.textmate.language.preferences.ShellVariablesRegistryImpl` instead.

`org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistry` class now interface
: Construct and mutate the `org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistryImpl` instead.

`org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistry.register(TextMateSnippet)` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistryImpl.register(TextMateSnippet)` instead.

`org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistry.clear()` method removed
: Use `org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistryImpl.clear()` instead.

`org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistry()` constructor removed
: Instantiate `org.jetbrains.plugins.textmate.language.preferences.SnippetsRegistryImpl()` instead.

### HTTP Client Plugin 2022.3

`com.intellij.httpClient.http.request.HttpRequestVariableSubstitutor` class now interface
: Use `com.intellij.httpClient.http.request.HttpRequestVariableSubstitutorImpl.create(Project, HttpRequestEnvironment)` for constructing instances.

### JavaScript Plugin 2022.3

`com.intellij.lang.javascript.buildTools.webpack.WebPackConfigManager` class moved to package `com.intellij.webpack`
: Use `com.intellij.webpack.WebpackConfigManager` instead.

`com.intellij.lang.javascript.buildTools.webpack.WebPackConfigManager.Companion` class moved to package `com.intellij.webpack`
: Use `com.intellij.webpack.WebpackConfigManager.Companion` instead.

### Microservices Plugin 2022.3

`com.intellij.microservices.url.inlay.UrlPathInlayAction.isAvailable(file: PsiFile, urlPathContext: UrlPathContext)` method parameter type changed from `com.intellij.microservices.url.references.UrlPathContext` to `com.intellij.microservices.url.inlay.UrlPathInlayHint`
: Use `com.intellij.microservices.url.inlay.UrlPathInlayHint.getContext` to obtain corresponding `UrlPathContext` instance.

### Remote Development Gateway Plugin 2022.3

`com.jetbrains.gateway.ssh.SshMultistagePanelContext.getSshConfig()` method removed
: Use `com.jetbrains.gateway.ssh.SshMultistagePanelContext.getConfig()` instead.

`com.jetbrains.gateway.ssh.SshMultistagePanelContext.setSshConfig(SshConfig)` method removed
: Use `com.jetbrains.gateway.ssh.SshMultistagePanelContext.setConfig(SshConfig)` instead.

### YAML Plugin 2022.3

org.jetbrains.yaml.YAMLParserDefinition no longer implements `org.jetbrains.yaml.YAMLElementTypes`.
: Use `org.jetbrains.yaml.YAMLElementTypes` fields directly.

### Terraform Plugin 2022.3

Top level packages of Terraform `org.intellij.plugins.hcl` and `org.intellij.plugins.hil` moved to single `org.intellij.terraform`.

## 2022.2

### IntelliJ Platform 2022.2

`com.intellij.openapi.actionSystem.TypeSafeDataProvider` class removed
: Use `com.intellij.openapi.actionSystem.DataProvider` class instead.

`com.intellij.openapi.actionSystem.DataSink` class removed
: Removed along with `com.intellij.openapi.actionSystem.TypeSafeDataProvider`.

`com.intellij.openapi.vcs.changes.ui.ChangesBrowser` class removed
: Use `com.intellij.openapi.vcs.changes.ui.ChangesBrowserBase` or `com.intellij.openapi.vcs.changes.ui.SimpleChangesBrowser` instead.

`com.intellij.openapi.vcs.changes.ui.ChangesBrowser.MyUseCase` class removed
: Removed along with `com.intellij.openapi.vcs.changes.ui.ChangesBrowser`.

`com.intellij.openapi.vcs.changes.ui.ChangesTreeList` class removed
: Use `com.intellij.openapi.vcs.changes.ui.ChangesTree` or `com.intellij.openapi.vcs.changes.ui.ChangesTreeImpl` instead.

`com.intellij.ide.IdeEventQueue.unsafeNonblockingExecute(Runnable)` method removed
: Method does not make sense anymore, please see [JBR-4328](https://youtrack.jetbrains.com/issue/JBR-4328).

`com.intellij.vcs.log.VcsLogHighlighter.getStyle(int, VcsShortCommitDetails, boolean)` method removed
: Use `com.intellij.vcs.log.VcsLogHighlighter.getStyle(int, VcsShortCommitDetails, int, boolean)` instead.

`com.intellij.AbstractBundle.getResourceBundle(java.lang.String, java.lang.ClassLoader)` method removed
: Use `com.intellij.DynamicBundle.getResourceBundle(java.lang.ClassLoader, java.lang.String)` instead

### Java Plugin 2022.2

`com.intellij.codeInspection.javaDoc.JavaDocLocalInspection` class removed
: Use `com.intellij.codeInspection.javaDoc.JavadocDeclarationInspection.ADDITIONAL_TAGS` field instead of `JavaDocLocalInspection.myAdditionalJavadocTags`.

### Plugin DevKit Plugin 2022.2

`icons.DevkitIcons` class renamed to `org.jetbrains.idea.devkit.DevKitIcons`
: Use `org.jetbrains.idea.devkit.DevKitIcons` instead.

`icons.DevkitIcons.Gutter` class renamed to `org.jetbrains.idea.devkit.DevKitIcons.Gutter`
: Use `org.jetbrains.idea.devkit.DevKitIcons.Gutter` instead.

### Collaboration Tools 2022.2

`com.intellij.collaboration.auth.ui.AccountsListModelBase.notifyCredentialsChanged(A account)` method visibility changed from protected to private
: Method hidden for better encapsulation.

`com.intellij.collaboration.auth.ui.AccountsPanelFactory.INSTANCE` field removed
: Factory is now a factory.

`com.intellij.collaboration.auth.ui.LoadingAccountsDetailsProvider.DetailsLoadingResult` class removed
: Better API introduced in the form of `com.intellij.collaboration.auth.ui.AccountsDetailsLoader`.

`com.intellij.collaboration.auth.ui.LoadingAccountsDetailsProvider` class removed
: Better API introduced in the form of `com.intellij.collaboration.auth.ui.AccountsDetailsLoader`.

`com.intellij.collaboration.ui.codereview.avatar.CachingAvatarIconsProvider` class renamed to `com.intellij.collaboration.ui.codereview.avatar.CachingCircleImageIconsProvider`
: Icon provider implementation changes to async.

### GitHub 2022.2

`org.jetbrains.plugins.github.api.GithubApiRequestExecutorManager.getExecutor(GithubAccount)` method return type changed from `org.jetbrains.plugins.github.api.GithubApiRequestExecutor.WithTokenAuth` to `org.jetbrains.plugins.github.api.GithubApiRequestExecutor`
: Hidden implementation details.

`org.jetbrains.plugins.github.api.GithubApiRequestExecutorManager.getExecutor(GithubAccount, Project)` method return type changed from `org.jetbrains.plugins.github.api.GithubApiRequestExecutor.WithTokenAuth` to `org.jetbrains.plugins.github.api.GithubApiRequestExecutor`
: Hidden implementation details.

`org.jetbrains.plugins.github.api.GithubApiRequestExecutorManager.getExecutor(GithubAccount, JComponent)` method return type changed from `org.jetbrains.plugins.github.api.GithubApiRequestExecutor.WithTokenAuth` to `org.jetbrains.plugins.github.api.GithubApiRequestExecutor`
: Hidden implementation details.

### Grazie Plugin 2022.2

Method `com.intellij.grazie.GrazieBundle.message(key, parameters)` marked static
: To shorten Java callers.

### Docker Plugin 2022.2

`com.intellij.docker.registry.DockerRegistry` class renamed to `com.intellij.docker.registry.DockerRegistryConfiguration`
: Please update usages.

### JavaScript Plugin 2022.2

`com.intellij.lang.javascript.buildTools.webpack.WebPackConfigManager.setConfig(WebPackConfig)` method moved to the superclass
: Should be used only in tests (marked with `@TestOnly`).

`com.intellij.lang.javascript.buildTools.webpack.WebPackConfig` class renamed to `com.intellij.lang.javascript.buildTools.bundler.WebBundlerConfig`
: Use `com.intellij.lang.javascript.buildTools.bundler.WebBundlerConfig` instead.

`com.intellij.lang.javascript.buildTools.webpack.WebPackResolve` class renamed to `com.intellij.lang.javascript.buildTools.bundler.WebBundlerResolve`
: Use `com.intellij.lang.javascript.buildTools.bundler.WebBundlerResolve` instead.

`com.intellij.lang.javascript.buildTools.webpack.WebPackConfigPath` class removed
: A regular String class is used instead.

## 2022.1

### IntelliJ Platform 2022.1

`org.apache.log4j` package removed
: log4j library removed from IntelliJ Platform, please see this [blog post](https://blog.jetbrains.com/platform/2022/02/removing-log4j-from-the-intellij-platform/) for migration instructions.

`org.slf4j` package removed
: log4j library removed from IntelliJ Platform, please see this [blog post](https://blog.jetbrains.com/platform/2022/02/removing-log4j-from-the-intellij-platform/) for migration instructions.

`com.intellij.openapi.diagnostic.Log4jBasedLogger` class removed
: log4j library removed from IntelliJ Platform, please see this [blog post](https://blog.jetbrains.com/platform/2022/02/removing-log4j-from-the-intellij-platform/) for migration instructions.

`com.intellij.platform.DirectoryProjectConfigurator.configureProject(Project, VirtualFile, Ref<Module>, boolean)` marked abstract
: Implement it instead of removed one.

`com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys.CLASS_FQN` field type changed from `StubIndexKey<Integer, PsiClass>` to `StubIndexKey<CharSequence, PsiClass>`
: `JavaFullClassNameIndex` now takes `CharSequence` instead of its `hashCode` to allow specific optimizations.

`com.intellij.psi.impl.java.stubs.index.JavaFullClassNameIndex.getKey` method return type changed from `StubIndexKey<Integer, PsiClass>` to `StubIndexKey<CharSequence, PsiClass>`
: `JavaFullClassNameIndex` now takes `CharSequence` instead of its `hashCode` to allow specific optimizations.

`com.intellij.psi.impl.java.stubs.index.JavaFullClassNameIndex.get(Integer, Project, GlobalSearchScope)` method parameter type changed from `Integer` to `CharSequence`
: `JavaFullClassNameIndex` now takes `CharSequence` instead of its `hashCode` to allow specific optimizations.

`com.jcraft.jsch` package removed
: [JSch library](https://mvnrepository.com/artifact/com.jcraft/jsch) was removed, bundle it with your plugin instead.

`com.intellij.diagnostic.DialogAppender.addFilter(Filter)` method removed
: `DialogAppender` now implements `java.util.logging.Handler`, use `setFilter` method if you need to apply a filter.

`com.intellij.psi.impl.cache.impl.BaseFilterLexerUtil.ScanContent` class removed
: Todo index is removed from the indexing procedure, use specific `calcIdEntries` or `calcTodoEntries` method instead.

`com.intellij.psi.impl.cache.impl.BaseFilterLexerUtil.scanContent(FileContent, IdAndToDoScannerBasedOnFilterLexer)` method removed
: Todo index is removed from the indexing procedure, use specific `calcIdEntries` or `calcTodoEntries` method instead.

`com.intellij.util.ui.StartupUiUtil.createStyleSheet(String)` method removed
: Method moved to `com.intellij.util.ui.StyleSheetUtil`

### Collaboration Tools 2022.1

`com.intellij.collaboration.auth.ui.AccountsPanelFactory.accountsPanel(Row, AccountManager, PersistentDefaultAccountHolder, AccountsListModel, AccountsDetailsProvider, Disposable, Icon)` method removed
: Support for default accounts required a signature change.

### Markdown Plugin 2022.1

`org.intellij.markdown.parser.constraints.MarkdownConstraints.Companion` class removed
: Some methods from a companion object were moved to `CommonMarkdownConstraints` and to extension functions on `MarkdownConstraints`.

`org.intellij.markdown.parser.constraints.MarkdownConstraints.Companion` field removed
: Some methods from a companion object were moved to `CommonMarkdownConstraints` and to extension functions on `MarkdownConstraints`.

`org.intellij.markdown.parser.constraints.MarkdownConstraints` class now interface
: Use `CommonMarkdownConstraints` for default method implementations instead.

`org.intellij.markdown.parser.markerblocks.providers.AtxHeaderProvider(boolean)` constructor parameter `boolean` removed
: `AtxHeaderProvider` now always requires at least one space between `#` and its content as specified by the CommonMark spec.

`org.intellij.markdown.html.HtmlGenerator.generateHtml()` method removed
: Use `org.intellij.markdown.html.HtmlGenerator.generateHtml(org.intellij.markdown.html.HtmlGenerator.TagRenderer)` instead.

`org.intellij.markdown.parser.markerblocks.MarkerBlockProvider.Companion.passSmallIndent(CharSequence)` method removed
: Use `org.intellij.markdown.parser.markerblocks.MarkerBlockProvider.Companion.passSmallIndent(CharSequence, Integer)` instead.

### Database Tools and SQL Plugin 2022.1

`com.intellij.database.datagrid.DataProducer.processRequest(DataRequest)` method parameter type changed from `DataRequest` to `GridDataRequest`
: `GridDataRequest` is a part of new API for async loading of table data. It's not possible to keep old method with default implementation because `DataProducer` will no longer have dependency on `DataRequest`. Plugins need to be recompiled to maintain bytecode compatibility.

`com.intellij.database.datagrid.DataRequest.RawQueryRequest.afterLastRowAdded(DataRequest.Context, int)` method parameter type changed from `DataRequest.Context` to `GridDataRequest.Context`
: The signature of the method was changed in the interface `com.intellij.database.datagrid.DataConsumer` that is now a part of new API for async loading of table data. Change the parameter type of the overridden method and recompile plugin to maintain bytecode compatibility.

`com.intellij.database.datagrid.DataConsumer.addRows(DataRequest.Context, List<DataConsumer.Row>)` method parameter type changed from `DataRequest.Context` to `GridDataRequest.Context`
: The signature of the method was changed in the interface `com.intellij.database.datagrid.DataConsumer` that is now a part of new API for async loading of table data. Change the parameter type of the overridden method and recompile plugin to maintain bytecode compatibility.

`com.intellij.database.datagrid.DataConsumer.addRows(DataRequest.Context, List<DataConsumer.Row>)` method parameter type changed from `List<DataConsumer.Row>` to `List<? extends GridRow>`
: The signature of the method was changed in the interface `com.intellij.database.datagrid.DataConsumer` that is now a part of new API for async loading of table data. Change the parameter type of the overridden method and recompile plugin to maintain bytecode compatibility.

`com.intellij.database.extractors.ObjectFormatter.getPlainValue(Object, DataConsumer.Column, Dbms)` method removed
: Method was removed because we refactor table editor API. It will not depend on Dbms anymore. Please use `ObjectFormatter.objectToString` instead.

`com.intellij.database.DatabaseDataKeys.DATA_SOURCE_KEY` field removed
: `DatabaseDataKeys` no longer extends `DatabaseDataKeysCore` because `DatabaseDataKeys` was moved to a separate module for table editor. `DATA_SOURCE_KEY` now has to be accessed directly via `DatabaseDataKeysCore`.

`com.intellij.database.extractors.ObjectFormatter` class now interface
: Method was removed because we refactor table editor API. New API will allow to use table editor in other products and fully customize it.
