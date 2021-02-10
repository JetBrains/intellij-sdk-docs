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

<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?
<class name> (class|interface) now (extends|implements) <class name> and inherits its abstract method <method name>(<human-readable parameters>)?
<class name>.<method name> method <parameter name> parameter marked @<class name>

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<class name>.<method name>(<human-readable parameters>) marked abstract
<class name>.<method name>(<human-readable parameters>) abstract method added
<class name> class moved to package <package name>

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

 >  Changes from API marked with `org.jetbrains.annotations.ApiStatus.@Experimental`/`ScheduledForRemoval` are not listed here, as incompatible changes are to be expected.
 >
 {type="note"}

## 2021.1
                              
### Changes in IntelliJ Platform 2021.1

`com.intellij.util.io.PersistentHashMap.isCorrupted` method removed
: The storage checks for corruption automatically, there is no need of any explicit additional checks.

`com.intellij.lang.StdLanguages.JSPX` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jspx.JspxLanguageImpl.INSTANCE`

`com.intellij.lang.StdLanguages.JSP` field removed
: Add a dependency on the `com.intellij.jsp` plugin and replace the reference with `com.intellij.lang.jsp.NewJspLanguage.getInstance()`

`org.sqlite.SQLiteConfig.setBusyTimeout(String)` method parameter type changed from `String` to `int`
: Please use updated sqlite-jdbc api.

`com.intellij.usages.impl.rules.UsageTypeProvider.getUsageType` method `PsiElement` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.jetbrains.performancePlugin.CommandProvider.getCommands(Project)` method parameter `Project` removed
: Project is now only accessible via `com.intellij.openapi.ui.playback.PlaybackContext.getProject()` since it may change during script execution. 
