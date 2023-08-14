<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2023.*

<!--
Before documenting a breaking API change, please make sure that the change cannot be avoided in an alternative way.

APIs marked with @Deprecated(forRemoval=true), @ApiStatus.Experimental, @ApiStatus.Internal/IntellijInternalApi, or @ApiStatus.ScheduledForRemoval don't need to be documented.

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

<link-summary>List of known Breaking API Changes in 2023.*</link-summary>

<include from="snippets.md" element-id="apiChangesHeader"/>

<include from="snippets.md" element-id="apiChangesJavaVersion"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradle_plugin_223_problem"/>

## 2023.3

### IntelliJ Platform 2023.3

`com.siyeh.ipp.base.Intention` class removed
: As a part of migration to new experimental ModCommand API, the class was removed completely. It's a part of implementation module and was never intended to be inherited by external plugins. Consider implementing `com.intellij.codeInspection.LocalInspectionTool` directly.

## 2023.2

### IntelliJ Platform 2023.2

`com.intellij.openapi.actionSystem.AnActionEvent.getInputEvent()` method return type changed from `InputEvent` to `@Nullable InputEvent`
: This may break source-compatibility with inheritors written in Kotlin.

Specify `displayName`/`key` for `Configurable`
: To improve performance, provide either attribute for `com.intellij.applicationConfigurable` or `com.intellij.projectConfigurable` extension point (see [](settings_guide.md)).

`com.intellij.remote.RemoteProcess.setWindowSize(int, int)` abstract method added
: Should implement this method.

### Database Plugin 2023.2

`com.intellij.database.dataSource.DataSourceStorageCore` class removed
: Use `com.intellij.database.dataSource.DataSourceStorage` instead.

`com.intellij.database.dataSource.DataSourceStorageUtil` class removed
: Use `com.intellij.database.dataSource.DataSourceStorage` instead.

`com.intellij.database.dataSource.DataSourceStorageCore$Listener` class renamed to `com.intellij.database.dataSource.DataSourceStorage$Listener`
: `Core` class removed from hierarchy.

### Maven Plugin 2023.2

`org.jetbrains.idea.maven.server.MavenEmbedderWrapper.customizeForResolve(MavenConsole, MavenProgressIndicator)` method removed
: Use `resolveProject(Collection, MavenExplicitProfiles, ProgressIndicator, MavenSyncConsole, MavenConsole, MavenWorkspaceMap, boolean)` instead.

`org.jetbrains.idea.maven.server.MavenEmbedderWrapper.execute(VirtualFile, Collection, Collection, List)` method removed
: Use `executeGoal(Collection, String, MavenProgressIndicator, MavenConsole)` instead.

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
: UI extracted from `TypesRegistry` to `TypesRegistryUi`. Use `com.intellij.database.urlParamEditorProvider` extension point to register parameter descriptor, use `com.intellij.database.urlParamEditorUiProvider` extension point to register parameter editor descriptor.

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
