[//]: # (title: Incompatible Changes in IntelliJ Platform and Plugins API 2021.*)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
Before documenting a breaking API change, please, make sure that the change cannot be avoided in an alternative way.

APIs marked with @ApiStatus.Experimental, @ApiStatus.Internal, or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern followed by a 2nd line with ": "-prefixed human-readable description and recommended fix/action.

The following problem patterns are supported:

<package name> package removed

<class name> class removed
<class name> class renamed to <new class name>
<class name> class moved to package <package name>

<class name>.<method name>(<human-readable parameters>) marked abstract
<class name>.<method name>(<human-readable parameters>) abstract method added
<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name> method <parameter name> parameter marked @<class name>
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?
<class name> (class|interface) now (extends|implements) <class name> and inherits its abstract method <method name>(<human-readable parameters>)?

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
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

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes  and will be skipped in API verification.
-->

Please see [Incompatible API Changes](api_changes_list.md) on how to verify compatibility.

 >  Changes from API marked with [`org.jetbrains.annotations.ApiStatus`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Experimental`, `@ScheduledForRemoval`, or `@Internal` are not listed here, as incompatible changes are to be expected.
 >
 {type="note"}
       
 >  Java 11 is required ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)) when targeting 2020.3 and later only.
 >  
 >  Please make sure to always upgrade to the [latest version](https://github.com/JetBrains/gradle-intellij-plugin/releases) of `gradle-intellij-plugin`.
 >
 {type="note"}

## 2021.2
                              
### Changes in IntelliJ Platform 2021.2

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

`com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` class removed
: Use `com.intellij.ssh.ui.unified.SshConfigVisibility` instead.

`com.intellij.ssh.ui.unified.SshConfigComboBox.reload(PresentableId, SshConfigConfigurable.Visibility)` method parameter type changed from `com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` to `com.intellij.ssh.ui.unified.SshConfigVisibility`
: `SshConfigConfigurable.Visibility` has been renamed to `SshConfigVisibility`.

`com.intellij.ssh.ui.unified.SshConfigComboBox(Project, Disposable, SshConfigConfigurable.Visibility)` constructor parameter type changed from `com.intellij.ssh.ui.unified.SshConfigConfigurable.Visibility` to `com.intellij.ssh.ui.unified.SshConfigVisibility`
: `SshConfigConfigurable.Visibility` has been renamed to `SshConfigVisibility`.

`org.jetbrains.uast.UAnnotated.getAnnotations()` method removed
: Use `org.jetbrains.uast.UAnnotated.getUAnnotations()` instead.

`org.jetbrains.uast.UAnnotated.getUAnnotations()` marked abstract
: Previously this circulary referenced `org.jetbrains.uast.UAnnotated.getAnnotations()`, which has been removed.

### Database Plugin 2021.2

`com.intellij.database.dataSource.DatabaseCredentialsAuthProvider.UserWidget` class removed
: Use `com.intellij.database.dataSource.DatabaseCredentialsAuthProviderUi.UserWidget` instead.

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

## 2021.1
                              
### Changes in IntelliJ Platform 2021.1

`com.intellij.util.io.PersistentHashMap.isCorrupted` method removed
: The storage checks for corruption automatically, there is no need of any explicit additional checks.

`com.intellij.lang.StdLanguages.JSPX` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jspx.JspxLanguageImpl.INSTANCE`

`com.intellij.lang.StdLanguages.JSP` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jsp.NewJspLanguage.getInstance()`

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.compareItems(alreadyFoundItem)` method parameter type changed from `SearchEverywhereFoundElementInfo` to `List<SearchEverywhereFoundElementInfo>`
: New API is more abstract which allows to review all already found items before making "deduplication" decision. Also consider implementing `com.intellij.ide.actions.searcheverywhere.AbstractEqualityProvider` instead of `com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider`.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.SKIP` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.DO_NOTHING` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`com.intellij.ide.actions.searcheverywhere.SEResultsEqualityProvider.SEEqualElementsActionType.Replace` field removed
: Enum class `SEEqualElementsActionType` was converted to sealed class with the same name.

`org.sqlite.SQLiteConfig.setBusyTimeout(String)` method parameter type changed from `String` to `int`
: Please use updated sqlite-jdbc api.

`com.intellij.usages.impl.rules.UsageTypeProvider.getUsageType` method `PsiElement` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.jetbrains.performancePlugin.CommandProvider.getCommands(Project)` method parameter `Project` removed
: Project is now only accessible via `com.intellij.openapi.ui.playback.PlaybackContext.getProject()` since it may change during script execution. 
                                
JSON Widget suppressor EP `com.intellij.json.jsonWidgetSuppressor`                                    
: Override new method [`JsonWidgetSuppressor.isCandidateForSuppress(VirtualFile, Project)`](upsource:///json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) for quick check in EDT before `suppressSwitcherWidget()` is called on background thread.

### Changes in HTTP Client Plugin 2021.1

`com.intellij.ws.rest.client` package removed
: Replaced by `com.intellij.httpClient.execution` in HTTP Client plugin

`com.intellij.ws.actions` package removed
: Replaced by `com.intellij.httpClient.actions` in HTTP Client plugin

`com.intellij.ws.converters` package removed
: Replaced by `com.intellij.httpClient.converters` in HTTP Client plugin

`com.intellij.ws.http.request` package removed
: Replaced by `com.intellij.httpClient.http.request` in HTTP Client plugin
                          
### Java UML Plugin 2021.1

Rename of packages to `.java.` specific variants
: A number of packages have been renamed: `com.intellij.uml.utils` becomes `com.intellij.uml.java.utils`, `com.intellij.uml.project` becomes `com.intellij.uml.java.project`, and `com.intellij.uml.jigsaw` becomes `com.intellij.uml.java.jigsaw`.

### Kotlin Plugin 2021.1

`org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinChangeInfo(KotlinMethodDescriptor, String, KotlinTypeInfo, Visibility, List, KotlinParameterInfo, PsiElement, Collection)` constructor parameter type changed from `org.jetbrains.kotlin.descriptors.Visibility` to `org.jetbrains.kotlin.descriptors.DescriptorVisibility`
: `Visibility` has been renamed to `DescriptorVisibility`. 

### Go Plugin 2021.1

`com.goide.sdk.combobox.GoSdkChooserCombo.getSdk` method moved to the superclass
: Recompile the dependant code.
