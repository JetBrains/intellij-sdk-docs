[//]: # (title: Incompatible Changes in IntelliJ Platform and Plugins API 2022.*)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
Before documenting a breaking API change, please make sure that the change cannot be avoided in an alternative way.

APIs marked with @Deprecated(forRemoval=true), @ApiStatus.Experimental, @ApiStatus.Internal, or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern followed by a 2nd line with ": "-prefixed human-readable description and recommended fix/action.

The following problem patterns are supported:

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

Please see [Incompatible API Changes](api_changes_list.md) on how to verify compatibility.

> Changes from API marked with `@Deprecated(forRemoval=true)` or any of [`org.jetbrains.annotations.ApiStatus`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Experimental`, `@ScheduledForRemoval`, or `@Internal` are not listed here, as incompatible changes are to be expected.
>
> For API annotated with `ApiStatus.@Internal`, see [](api_internal.md) for more details and replacements.
>
{type="note"}

> Java 17 is required when targeting 2022.2 or later only.
>
> Java 11 is required ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)) when targeting 2020.3 and later only.
>
> Please make sure to always upgrade `gradle-intellij-plugin` to the latest version [![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-intellij-plugin.svg?style=flat-square)](https://github.com/jetbrains/gradle-intellij-plugin/releases)
>
{type="note"}

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

## 2022.3

### IntelliJ Platform 2022.3

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
: Use `com.intellij.vcs.log.VcsLogHighlighter.getStyle(int, VcsShortCommitDetails, int, boolean)` instead

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
