---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2019.*
---

<!--
Before documenting a breaking API change, please, make sure that the change cannot be avoided 
in an alternative way.

APIs marked with @ApiStatus.Experimental or @ApiStatus.Internal don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern
followed by a 2nd line with ": "-prefixed human-readable description and recommended fix/action.

The following problem patterns are supported:

<package name> package removed
<class name> class removed
<class name> class renamed to <new class name>

<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<class name>.<method name>(<human-readable parameters>) abstract method added
<class name> class moved to package <package name>

where <class name> is a fully-qualified name of the class, e.g. com.intellij.openapi.actionSystem.AnAction$InnerClass.
<method name> is the exact method's name. Note that constructors have dedicated patterns.
<human-readable parameters> is a string representing parameters, which are not necessarily fully qualified. They do not affect the parser. For example, instead of (java.lang.Object, java.util.List, int) you are free to write (Object, List<String>, int).

NOTE: If a change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack. 
An example of a ticket is https://youtrack.jetbrains.com/issue/PR-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using markdown-features:
 1) code quotes: `org.example.Foo.methodName`
 2) links [org.example.Foo](https://github.com/JetBrains/intellij-community/tree/master/)
 3) both code quotes and links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)
-->

> **NOTE** Changes from API marked with [`org.jetbrains.annotations.ApiStatus.@Experimental`](upsource:///platform/util/src/org/jetbrains/annotations/ApiStatus.java) are not listed here, as incompatible changes are to be expected.

# 2019.3

## Changes in IntelliJ Platform 2019.3

`com.intellij.json.JsonFileTypeFactory` class removed
: Use `com.intellij.fileType` extension point instead.

`org.jetbrains.plugins.ruby.ruby.codeInsight.types.RubyTypeProvider.createTypeBySymbolFromProviders(Symbol symbol, Context context)` method parameter `Context` removed
: This was done as part of [`RUBY-24760`](https://youtrack.jetbrains.com/issue/RUBY-24760) in order to move to new Context-less approach.

`com.intellij.util.containers.ConcurrentHashSet` class removed
: Use `com.intellij.util.containers.ContainerUtil#newConcurrentSet` instead.

`com.intellij.openapi.editor.impl.EditorFactoryImpl(EditorActionManager)` constructor removed
: Use constructor `com.intellij.openapi.editor.impl.EditorFactoryImpl()` instead.

`com.intellij.util.net.ssl.CertificateManager.HOSTNAME_VERIFIER` field removed
: Use `org.apache.http.conn.ssl.DefaultHostnameVerifier` instead.

`org.jetbrains.plugins.groovy.extensions.GroovyScriptTypeDetector(GroovyScriptType, String[])` constructor removed
: Use constructor `GroovyScriptTypeDetector(GroovyScriptType)` instead, and `com.intellij.fileType` to register additional extensions.

`com.intellij.openapi.vcs.changes.ui.ChangesListView.UNVERSIONED_FILES_DATA_KEY` field removed
: Use `com.intellij.openapi.vcs.changes.ui.ChangesListView.UNVERSIONED_FILE_PATHS_DATA_KEY` instead.

`com.intellij.openapi.util.BuildNumber.getBuildNumber()` method removed
: See `BuildNumber.asString`, `BuildNumber.getBaselineVersion()` and `BuildNumber.getComponents()` as alternatives.

## Changes in DataGrip and Database Tools plugin 2019.3

`com.intellij.sql.dialects.mssql.MssqlDialect` class renamed to `com.intellij.sql.dialects.mssql.MsDialect`
: Do not use SQL dialect classes directly.

`com.intellij.sql.dialects.oracle.OracleDialect` class renamed to `com.intellij.sql.dialects.oracle.OraDialect`
: Do not use SQL dialect classes directly.

`com.intellij.sql.dialects.postgres.PostgresDialect` class renamed to `com.intellij.sql.dialects.postgres.PgDialect`
: Do not use SQL dialect classes directly.

# 2019.2 

## Changes in IntelliJ Platform 2019.2
Constructor injection referring to extension points not supported
: Obtain reference to extension points via `(Project)ExtensionPointName.findExtension()` in your constructor instead.

`com.intellij.openapi.components.BaseState.map$default(BaseState, Map, int, Object)` method removed
: Use `com.intellij.openapi.components.BaseState.map()` instead.

`org.nustaq` package removed
: [fast-serialization](https://github.com/RuedigerMoeller/fast-serialization) library was removed, please use `com.intellij.serialization.ObjectSerializer` instead.

`java.org.java_websocket` package removed
: [Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket) library was removed, bundle it with your plugin instead.

`com.intellij.ui.layout.Cell.invoke$default(Cell, JComponent, CCFlags[], int, GrowPolicy, String, int, Object)` method parameter type changed
: Signature of this function has been seriously changed without possibility to keep the old function. Change invocations and overriding of that function according to new parameters and recompile the code. 

`com.intellij.ui.layout.Row.label$default(Row, String, int, UIUtil.ComponentStyle, UIUtil.FontColor, boolean, int, Object)` method removed
: This method has been pulled up to the base class `Cell`; since it has default parameters, it's a binary breaking change in Kotlin.
Recompile your code to pick up the new signature.

`com.yourkit` package removed
: YourKit library has been extracted into the separate plugin which is not bundled in all IDEs by default. YourKit library is a library for profiling IDE, and its util classes shouldn't be used for general purpose. Instead of `com.yourkit.util.Strings` please use  `org.apache.commons.lang.StringUtils`.  Instead of `com.yourkit.util.ArrayUtil` please use `org.apache.commons.lang.ArrayUtils`

`org.jetbrains.intellij.build.ProductProperties.yourkitAgentBinariesDirectoryPath` field removed
: Please bundle [performanceTesting plugin](https://plugins.jetbrains.com/plugin/7819-performance-testing) in case you would like to bundle YourKit profiler within your IDE.

`org.jetbrains.intellij.build.ProductProperties.enableYourkitAgentInEAP` field removed
: Please bundle [performanceTesting plugin](https://plugins.jetbrains.com/plugin/7819-performance-testing) in case you would like to bundle YourKit profiler within your IDE.

`com.intellij.extapi.psi.PsiElementBase` class removed
: Please use `com.intellij.psi.impl.PsiElementBase` or one of its descendants as a base class for PSI elements, e.g. `com.intellij.extapi.psi.ASTWrapperPsiElement`, as suggested in [Custom Language Support Tutorial](../../tutorials/custom_language_support/grammar_and_parser.md).

`com.intellij.extapi.psi.MetadataPsiElementBase` class removed
: Please use different base class for PSI elements.

`com.intellij.ide.actions.SearchAgainAction` class now extends `com.intellij.openapi.editor.actionSystem.EditorAction` and inherits its final method `actionPerformed`
: Please use the `com.intellij.editorActionHandler` extension point to register a different handler for the action.

`com.intellij.ide.actions.SearchBackAction` class now extends `com.intellij.openapi.editor.actionSystem.EditorAction` and inherits its final method `actionPerformed`
: Please use the `com.intellij.editorActionHandler` extension point to register a different handler for the action.

`com.intellij.lexer.RestartableLexer.getRestartableState()` method removed
: Please use `com.intellij.lexer.RestartableLexer.isRestartableState(int state)` instead.

`com.intellij.lexer.RestartableLexer.isRestartableState(int state)` abstract method added
: Implement method in `RestartableLexer` implementations.

`com.intellij.lexer.RestartableLexer.start(CharSequence buffer, int startOffset, int endOffset, int initialState, TokenIterator tokenIterator)` abstract method added
: Implement method in `RestartableLexer` implementations.


# 2019.1
 
## Changes in IntelliJ Platform 2019.1

`kotlinx.coroutines.experimental` package removed 
: Bundled Kotlin library is updated to 1.3 so the plugins must [migrate](https://blog.jetbrains.com/kotlin/2018/09/kotlin-1-3-rc-is-here-migrate-your-coroutines/) to the stable versions of coroutines.

`com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy, VcsFileListenerContextHelper)` constructor removed 
: Use `com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl.<init>(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy)`.

`com.intellij.injected.editor.DocumentWindow.injectedToHost(int, boolean)` abstract method added
: Implement the method in `DocumentWindow` implementations.

`git4idea.rebase.GitCommitEditingAction.actionPerformed(AnActionEvent)` method marked final
: Implement `actionPerformedAfterChecks` instead of `actionPerformed`.

`git4idea.rebase.GitCommitEditingAction.actionPerformedAfterChecks(AnActionEvent)` abstract method added
: Implement `actionPerformedAfterChecks` instead of `actionPerformed`.

`com.intellij.util.lang.UrlClassLoader.loadPlatformLibrary` method removed
: Use `com.intellij.util.loader.NativeLibraryLoader.loadPlatformLibrary` instead.

`com.intellij.openapi.util.KeyedExtensionCollector.getExtensions()` method marked final
: Remove custom implementation.

## Changes in DataGrip and Database Tools plugin 2019.1

`com.intellij.sql.psi.SqlTokens.SQL_IDENT` field type changed from `com.intellij.sql.psi.impl.SqlTokenType` to `com.intellij.sql.psi.SqlTokenType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

## Changes in Kotlin Plugin API 1.3

`org.jetbrains.kotlin.KtNodeTypes.BOOLEAN_CONSTANT` field type changed from `org.jetbrains.kotlin.KtNodeType` to `com.intellij.psi.tree.IElementType`
: Field type has been generalized. In most of the cases, it's enough to recompile the code of the plugin.

`org.jetbrains.kotlin.KtNodeTypes.CHARACTER_CONSTANT` field type changed from `org.jetbrains.kotlin.KtNodeType` to `com.intellij.psi.tree.IElementType`
: Field type has been generalized. In most of the cases, it's enough to recompile the code of the plugin.

`org.jetbrains.kotlin.KtNodeTypes.FLOAT_CONSTANT` field type changed from `org.jetbrains.kotlin.KtNodeType` to `com.intellij.psi.tree.IElementType`
: Field type has been generalized. In most of the cases, it's enough to recompile the code of the plugin.

`org.jetbrains.kotlin.KtNodeTypes.INTEGER_CONSTANT` field type changed from `org.jetbrains.kotlin.KtNodeType` to `com.intellij.psi.tree.IElementType`
: Field type has been generalized. In most of the cases, it's enough to recompile the code of the plugin.

`org.jetbrains.kotlin.KtNodeTypes.STRING_TEMPLATE` field type changed from `org.jetbrains.kotlin.KtNodeType` to `com.intellij.psi.tree.IElementType`
: Field type has been generalized. In most of the cases, it's enough to recompile the code of the plugin.
