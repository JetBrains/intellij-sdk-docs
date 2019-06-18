---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2019.*
---

<!--

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

-->

## Changes in IntelliJ Platform 2019.2
`java.org.java_websocket` package removed
: [Java-WebSocket Library](https://github.com/TooTallNate/Java-WebSocket) was removed, bundle it with your plugin instead.

`com.intellij.ui.layout.Cell.invoke$default(Cell, JComponent, CCFlags[], int, GrowPolicy, String, int, Object)` method parameter type changed
: Signature of this function has been seriously changed without possibility to keep the old function. Change invocations and overriding of that function according to new parameters and recompile the code. 

`com.yourkit` package removed
: YourKit library has been extracted into the separate plugin which is not bundled in all IDEs by default. YourKit library is a library for profiling IDE, and its util classes shouldn't be used for general purpose. Instead of `com.yourkit.util.Strings` please use  `org.apache.commons.lang.StringUtils`.  Instead of `com.yourkit.util.ArrayUtil` please use `org.apache.commons.lang.ArrayUtils`

`org.jetbrains.intellij.build.ProductProperties`: fields `yourkitAgentBinariesDirectoryPath` and `enableYourkitAgentInEAP` have been removed
: Please bundle [performanceTesting plugin](https://plugins.jetbrains.com/plugin/7819-performance-testing) in case you would like to bundle YourKit profiler within your IDE.

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
