<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2021.*

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

<link-summary>List of known Breaking API Changes in 2021.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

## 2021.3

### IntelliJ Platform 2021.3

Running tests fails with `jarFiles is not set for [...]` or `Created extension classloader is not equal to plugin's one`
: Set system property `idea.force.use.core.classloader` to `true`.

Running tests fails using Gradle setup
: Please use [workarounds](https://youtrack.jetbrains.com/issue/IDEA-278926#focus=Comments-27-5561012.0-0).

`com.intellij.ui.mac.MacMessages.showMessageDialog(String, String, String[], boolean, Window, int, int, DialogWrapper.DoNotAskOption)` method removed
: Use `com.intellij.ui.mac.MacMessages.showMessageDialog(String, String, String[], boolean, Window, int, int,  DoNotAskOption)` instead.

`com.intellij.openapi.ui.MessageDialogBuilder.doNotAsk(DialogWrapper.DoNotAskOption)` method removed
: Use `com.intellij.openapi.ui.MessageDialogBuilder.doNotAsk(DoNotAskOption)` instead.

`com.intellij.ide.util.projectWizard.WizardContext.getWizard()` method removed
: Use `com.intellij.ide.util.projectWizard.WizardContext.getUserData(AbstractWizard.KEY)` instead.

`com.intellij.openapi.ui.TextComponentAccessor.TEXT_FIELD_WITH_HISTORY_WHOLE_TEXT` field removed
: Use `com.intellij.openapi.ui.TextComponentAccessors.TEXT_FIELD_WITH_HISTORY_WHOLE_TEXT` instead.

`com.intellij.execution.process.ColoredOutputTypeRegistry.getAnsiColorKey(int)` method removed
: Use `com.intellij.execution.process.ColoredOutputTypeRegistryImpl.getAnsiColorKey(int)` instead.

`com.intellij.diagnostic.PerformanceWatcher.Snapshot` class now interface
: Recompile the dependant code or use `com.intellij.diagnostic.PerformanceWatcherImpl.SnapshotImpl` instead.

`com.intellij.openapi.fileEditor.impl.EditorTabPresentationUtil.getEditorTabTitle(Project, VirtualFile, EditorWindow)` method parameter `EditorWindow` removed
: This parameter was never needed, but led to code coupling.

`com.intellij.openapi.fileEditor.impl.EditorTabPresentationUtil.getUniqueEditorTabTitle(Project, VirtualFile, EditorWindow)` method parameter `EditorWindow` removed
: This parameter was never needed, but led to code coupling.

`com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider.getEditorTabTitle(Project, VirtualFile, EditorWindow)` method parameter `EditorWindow` removed
: This parameter was never needed, but led to code coupling.

Constructor `com.intellij.codeInsight.hints.settings.InlayProviderSettingsModel` changed
: Added `Language` parameter.

`com.intellij.codeInsight.hints.settings.InlayProviderSettingsModel.getDescription()` abstract method added
: Provides short description.

`com.intellij.codeInsight.hints.settings.InlayProviderSettingsModel.getCaseDescription(ImmediateConfigurable.Case)` abstract method added
: Provides description text for given case.

`com.intellij.codeInsight.hints.settings.InlayProviderSettingsModel.getCasePreview(ImmediateConfigurable.Case)` abstract method added
: Provides preview text for given case.

`com.intellij.openapi.wm.ToolWindow.getEmptyText()` method removed
: Please safe-cast and use `com.intellij.openapi.wm.ex.ToolWindowEx.getEmptyText()`.

`com.intellij.openapi.actionSystem.ex.CustomComponentAction#createCustomComponent(Presentation, String, DataContext)` method removed
: Please use `createCustomComponent(Presentation, String)`, one shall not depend on `dataContext` there.

`com.intellij.ui.EditorTextField.addNotify()` method marked final
: Please use `addSettingsProvider(EditorSettingsProvider)` to configure `Editor` as editor creation may be postponed now.

### VCS Log 2021.3

`com.intellij.vcs.log.ui.frame.CommitPanel` class removed
: Removed unnecessary inheritance.

### Git Plugin 2021.3

`git4idea.ui.branch.GitBranchActionsUtilKt.checkoutOrReset(Project, List, String, GitNewBranchOptions)` method removed
: Method was dropped to avoid supporting outdated behavior.

`git4idea.ui.branch.GitBranchActionsUtilKt.createNewBranch(Project, List, String, GitNewBranchOptions)` method removed
: Method was dropped to avoid supporting outdated behavior.

### Python Plugin 2021.3

`com.jetbrains.python.console.PydevConsoleRunnerImpl(Project, Sdk, PyConsoleType, String, Map<String, String>, PyConsoleOptions.PyConsoleSettings, Consumer<? super String>, String[])` constructor parameter type `com.intellij.util.Consumer<? super String>` removed
: There is no need to pass a Restart action as a constructor parameter, it should be created inside the `com.jetbrains.python.console.PydevConsoleRunnerImpl#createRerunAction` method

### IntelliJ IDEA Ultimate 2021.3

#### Miscellaneous

`com.intellij.util.JavaeeIcons` class renamed to `com.intellij.javaee.JavaeeIcons`
: To support dynamic plugins.

`icons.RestClientIcons` class renamed to `com.intellij.httpClient.RestClientIcons`
: To support dynamic plugins.

#### Expression Language (EL) / JSP

Expression Language (EL) was extracted from `com.intellij.jsp` (_"Java Server Pages (JSP)"_) plugin to new `com.intellij.javaee.el` (_Java EE: Expression Language (EL)_) plugin.
`com.intellij.jsp` has mandatory dependency on `com.intellij.javaee.el`.
10+ Ultimate plugins (Spring, Java EE, Frameworks) now have optional dependency on `com.intellij.javaee.el` plugin (mandatory dependency to `com.intellij.jsp` was removed).

`com.intellij.jsp.el.impl.ELResolveUtil.VariableInfoData` class renamed to `com.intellij.jsp.el.impl.JspELResolveUtil.VariableInfoData`
: To support dynamic plugins.

`com.intellij.jsp.el.impl.ELResolveUtil` class renamed to `com.intellij.javaee.el.util.ELResolveUtil`
: Use new class from `com.intellij.javaee.el` plugin instead or `com.intellij.jsp.el.impl.JspELResolveUtil` from `com.intellij.jsp` plugin.

`com.intellij.jsp.el.impl.ELElementProcessor` class renamed to `com.intellij.javaee.el.ELElementProcessor`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ELElementTypes` class renamed to `com.intellij.javaee.el.psi.ELElementTypes`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ELLiteralExpression` class renamed to `com.intellij.javaee.el.psi.ELLiteralExpression`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ELElementType` class renamed to `com.intellij.javaee.el.psi.ELElementType`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ELExpressionHolder` class renamed to `com.intellij.javaee.el.psi.ELExpressionHolder`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.jsp.el.impl.ElVariablesProvider` class renamed to `com.intellij.javaee.el.providers.ElVariablesProvider`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ELVariable` class renamed to `com.intellij.javaee.el.psi.ELVariable`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.el.ElLiteralCustomReferenceProvider` class renamed to `com.intellij.javaee.el.psi.ElLiteralCustomReferenceProvider`
: Use new class from `com.intellij.javaee.el` plugin instead.

`com.intellij.psi.jsp.JspImplicitVariable` class renamed to `com.intellij.javaee.el.util.ELImplicitVariable`
: Use new class from `com.intellij.javaee.el` plugin instead.

#### Persistence

Package `com.intellij.javaee.model.xml.persistence` renamed to `com.intellij.jpa.model.xml.persistence`
: To support dynamic plugins.

#### Application Servers

Various package renames to support dynamic plugins

| Old package name                            | New package name                                       |
|---------------------------------------------|--------------------------------------------------------|
| `com.intellij.javaee.serverInstances`       | `com.intellij.javaee.appServers.serverInstances`       |
| `com.intellij.javaee.appServerIntegrations` | `com.intellij.javaee.appServers.appServerIntegrations` |
| `com.intellij.javaee.deployment`            | `com.intellij.javaee.appServers.deployment`            |
| `com.intellij.javaee.run`                   | `com.intellij.javaee.appServers.run`                   |

`com.intellij.javaee.deployment.DeploymentModel` class renamed to `com.intellij.javaee.appServers.deployment.DeploymentModel`
: To support dynamic plugins.

`com.intellij.javaee.deployment.DeploymentSource` class renamed to `com.intellij.javaee.appServers.deployment.DeploymentSource`
: To support dynamic plugins.

`com.intellij.javaee.deployment.DeploymentProvider` class renamed to `com.intellij.javaee.appServers.deployment.DeploymentProvider`
: To support dynamic plugins.

`com.intellij.javaee.openapi.ex.AppServerIntegrationsManager` class renamed to `com.intellij.javaee.appServers.openapi.ex.AppServerIntegrationsManager`
: To support dynamic plugins.

`com.intellij.javaee.appServerIntegrations.AppServerIntegration` class renamed to `com.intellij.javaee.appServers.appServerIntegrations.AppServerIntegration`
: To support dynamic plugins.

`com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentData` class renamed to `com.intellij.javaee.appServers.appServerIntegrations.ApplicationServerPersistentData`
: To support dynamic plugins.

`com.intellij.javaee.facet.JavaeeFrameworkSupportInfoCollector` class renamed to `com.intellij.javaee.appServers.facet.JavaeeFrameworkSupportInfoCollector`
: To support dynamic plugins.

`com.intellij.javaee.appServerIntegrations.ApplicationServer` class renamed to `com.intellij.javaee.appServers.appServerIntegrations.ApplicationServer`
: To support dynamic plugins.

`com.intellij.javaee.appServerIntegrations.ApplicationServerHelper` class renamed to `com.intellij.javaee.appServers.appServerIntegrations.ApplicationServerHelper`
: To support dynamic plugins.

`com.intellij.javaee.serverInstances.J2EEServerInstance` class renamed to `com.intellij.javaee.appServers.serverInstances.J2EEServerInstance`
: To support dynamic plugins.

`com.intellij.javaee.run.configuration.CommonModel` class renamed to `com.intellij.javaee.appServers.run.configuration.CommonModel`
: To support dynamic plugins.

`com.intellij.javaee.run.localRun.ExecutableObjectStartupPolicy` class renamed to `com.intellij.javaee.appServers.run.localRun.ExecutableObjectStartupPolicy`
: To support dynamic plugins.

### Database Plugin 2021.3

`com.intellij.database.model.ModelLightCopierUtils` class removed
: Internal class not to be used by 3rd party.


## 2021.2

### IntelliJ Platform 2021.2

`com.intellij.openapi.editor.impl.event.DocumentEventImpl.translateLineViaDiff(int)` method removed
: Use persistent range markers instead, see `com.intellij.openapi.editor.Document.createRangeMarker(int, int, boolean)` with `surviveOnExternalChange=true`.

`com.intellij.openapi.editor.impl.event.DocumentEventImpl.translateLineViaDiffStrict(int)` method removed
: Use persistent range markers instead, see `com.intellij.openapi.editor.Document.createRangeMarker(int, int, boolean)` with `surviveOnExternalChange=true`.

`com.intellij.openapi.file.exclude.EnforcedPlainTextFileType` class removed
: Use `com.intellij.openapi.fileTypes.PlainTextFileType` instead.

`com.intellij.openapi.updateSettings.impl.CheckForUpdateResult` class removed
: Use `com.intellij.openapi.updateSettings.impl.PlatformUpdates` instead.

`com.intellij.openapi.updateSettings.impl.UpdateStrategy.checkForUpdates()` method return type changed from `com.intellij.openapi.updateSettings.impl.CheckForUpdateResult` to `com.intellij.openapi.updateSettings.impl.PlatformUpdates`
: Use `com.intellij.openapi.updateSettings.impl.PlatformUpdates` instead of `com.intellij.openapi.updateSettings.impl.CheckForUpdateResult`.

`com.intellij.openapi.updateSettings.impl.pluginsAdvertisement.PluginsAdvertiser.Plugin` class removed
: Use `com.intellij.ide.plugins.advertiser.PluginData` instead.

`com.intellij.ide.plugins.DisabledPluginsState.disablePlugin(PluginId)` method removed
: Use either `com.intellij.ide.plugins.PluginManagerCore.disablePlugin(PluginId)` or `com.intellij.ide.plugins.PluginEnabler.disablePlugins(Collection)` instead.

`com.intellij.ide.plugins.PluginManagerMain.suggestToEnableInstalledDependantPlugins(PluginEnabler, List)` method parameter type changed from `com.intellij.ide.plugins.PluginManagerMain.PluginEnabler` to `com.intellij.ide.plugins.PluginEnabler`
: `com.intellij.ide.plugins.PluginManagerMain.PluginEnabler` has been renamed to `com.intellij.ide.plugins.PluginEnabler`.

`com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` class removed
: Use `com.intellij.ssh.ui.unified.SshConfigVisibility` instead.

`com.intellij.ssh.ui.unified.SshConfigComboBox.reload(PresentableId, SshConfigConfigurable.Visibility)` method parameter type changed from `com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` to `com.intellij.ssh.ui.unified.SshConfigVisibility`
: `SshConfigConfigurable.Visibility` has been renamed to `SshConfigVisibility`.

`com.intellij.ssh.ui.unified.SshConfigComboBox(Project, Disposable, SshConfigConfigurable.Visibility)` constructor parameter type changed from `com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` to `com.intellij.ssh.ui.unified.SshConfigVisibility`
: `SshConfigConfigurable.Visibility` has been renamed to `SshConfigVisibility`.

`org.jetbrains.uast.UAnnotated.getAnnotations()` method removed
: Use `org.jetbrains.uast.UAnnotated.getUAnnotations()` instead.

`org.jetbrains.uast.UAnnotated.getUAnnotations()` marked abstract
: Previously this circularly referenced `org.jetbrains.uast.UAnnotated.getAnnotations()`, which has been removed.

`com.intellij.openapi.actionSystem.ActionPlaces.isMainMenuOrShortcut(String)` method removed
: Use `isMainMenuOrActionSearch(String)` method instead.

Add implementation for `com.intellij.openapi.fileEditor.FileEditor.getFile()`
: Implementations should return non-null `VirtualFile` instance.

### Performance Testing Plugin 2021.2

`com.jetbrains.performancePlugin.CommandProvider.getCommands()` method return type changed from `java.util.List` to `java.util.Map`
: Use `java.util.List` instead of `java.util.Map`.

### Database Plugin 2021.2

`com.intellij.database.dataSource.DatabaseCredentialsAuthProvider.UserWidget` class removed
: Use `com.intellij.database.dataSource.DatabaseCredentialsAuthProviderUi.UserWidget` instead.

`com.intellij.database.actions.DdlActions.DeleteProvider1` class removed
: Use `com.intellij.database.actions.DbDeleteProvider.getDeleteProvider()` instead.

`com.intellij.database.model.RawConnectionConfig.getEffectiveUrl(Project)` method removed
: Use `com.intellij.database.model.RawConnectionConfig.getUrl()` instead. Or use `com.intellij.database.dataSource.DatabaseConnectionEstablisher.processInterceptors()`.

### GitHub Plugin 2021.2

`org.jetbrains.plugins.github.util.GithubAuthData` class removed
: Use `org.jetbrains.plugins.github.authentication.GithubAuthenticationManager` instead.

`org.jetbrains.plugins.github.util.GithubAuthData$BasicAuth` class removed
: Use `org.jetbrains.plugins.github.authentication.GithubAuthenticationManager` instead.

`org.jetbrains.plugins.github.util.GithubSettings.getAuthData(GithubAuthData)` method removed
: Use `org.jetbrains.plugins.github.authentication.GithubAuthenticationManager` instead.

`org.jetbrains.plugins.github.util.GithubSettings.getLogin` method removed
: Use `org.jetbrains.plugins.github.authentication.GithubAuthenticationManager` instead.

`org.jetbrains.plugins.github.util.GithubSettings.isAuthConfigured` method removed
: Use `org.jetbrains.plugins.github.authentication.GithubAuthenticationManager` instead.

### Spring Plugin 2021.2

`icons.SpringApiIcons` class moved to package `com.intellij.spring`
: Use `com.intellij.spring.SpringApiIcons` instead.

`icons.SpringApiIcons.Gutter` class moved to package `com.intellij.spring`
: Use `com.intellij.spring.SpringApiIcons.Gutter` instead.

### Python Plugin 2021.2.1

`com.jetbrains.python.psi.types.PyTypedDictType.Companion.match(PyTypedDictType, PyDictLiteralExpression, TypeEvalContext)` method removed
: As the result of the refactoring aimed at fixing [PY-48799](https://youtrack.jetbrains.com/issue/PY-48799), for dict literals containing only string keys we infer `PyTypedDictType` now, so there's no need to match dict literals with `TypedDict`s. There's a new method for comparing the inferred `TypedDict`s with the given ones: `com.jetbrains.python.psi.types.PyTypedDictType.Companion.match(PyType, PyTypedDictType, TypeEvalContext)`.

## 2021.1

### IntelliJ Platform 2021.1

`com.intellij.util.io.PersistentHashMap.isCorrupted` method removed
: The storage checks for corruption automatically, there is no need of any explicit additional checks.

`com.intellij.lang.StdLanguages.JSPX` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jspx.JspxLanguageImpl.INSTANCE`.

`com.intellij.lang.StdLanguages.JSP` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jsp.NewJspLanguage.getInstance()`.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.compareItems(alreadyFoundItem)` method parameter type changed from `SearchEverywhereFoundElementInfo` to `List<SearchEverywhereFoundElementInfo>`
: New API is more abstract which allows to review all already found items before making "deduplication" decision. Also consider implementing `com.intellij.ide.actions.searcheverywhere.AbstractEqualityProvider` instead of `com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider`.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.SKIP` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.DO_NOTHING` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.Replace` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`org.sqlite.SQLiteConfig.setBusyTimeout(String)` method parameter type changed from `String` to `int`
: Please use updated sqlite-jdbc API.

`com.intellij.usages.impl.rules.UsageTypeProvider.getUsageType` method `PsiElement` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.jetbrains.performancePlugin.CommandProvider.getCommands(Project)` method parameter `Project` removed
: Project is now only accessible via `com.intellij.openapi.ui.playback.PlaybackContext.getProject()` since it may change during script execution.

JSON Widget suppressor <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.json.jsonWidgetSuppressor"/></include>
: Override new method [`JsonWidgetSuppressor.isCandidateForSuppress(VirtualFile, Project)`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) for quick check in [EDT](threading_model.md) before `suppressSwitcherWidget()` is called on a background thread.

### HTTP Client Plugin 2021.1

`com.intellij.ws.rest.client` package removed
: Replaced by `com.intellij.httpClient.execution` in HTTP Client plugin.

`com.intellij.ws.actions` package removed
: Replaced by `com.intellij.httpClient.actions` in HTTP Client plugin.

`com.intellij.ws.converters` package removed
: Replaced by `com.intellij.httpClient.converters` in HTTP Client plugin.

`com.intellij.ws.http.request` package removed
: Replaced by `com.intellij.httpClient.http.request` in HTTP Client plugin.

### Java UML Plugin 2021.1

Rename of packages to `.java.` specific variants

| Old package name           | New package name                |
|----------------------------|---------------------------------|
| `com.intellij.uml.utils`   | `com.intellij.uml.java.utils`   |
| `com.intellij.uml.project` | `com.intellij.uml.java.project` |
| `com.intellij.uml.jigsaw`  | `com.intellij.uml.java.jigsaw`  |

### Kotlin Plugin 2021.1

`org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinChangeInfo(KotlinMethodDescriptor, String, KotlinTypeInfo, Visibility, List, KotlinParameterInfo, PsiElement, Collection)` constructor parameter type changed from `org.jetbrains.kotlin.descriptors.Visibility` to `org.jetbrains.kotlin.descriptors.DescriptorVisibility`
: `Visibility` has been renamed to `DescriptorVisibility`.

### Go Plugin 2021.1

`com.goide.sdk.combobox.GoSdkChooserCombo.getSdk` method moved to the superclass
: Recompile the dependant code.

### CoffeeScript Plugin 2021.1

`icons.CoffeescriptIcons` class renamed to `org.coffeescript.CoffeescriptIcons`
: To support dynamic plugins.
