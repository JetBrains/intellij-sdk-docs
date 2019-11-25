---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2020.*
---

<!--
Before documenting a breaking API change, please, make sure that the change cannot be avoided 
in an alternative way.

APIs marked with @ApiStatus.Experimental, @ApiStatus.Internal or @ApiStatus.ScheduledForRemoval don't need to be documented.

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

<property name> property removed from resource bundle <bundle name>

where 
<class name> is a fully-qualified name of the class, e.g. com.intellij.openapi.actionSystem.AnAction$InnerClass.
<method name> is the exact method's name. Note that constructors have dedicated patterns.
<human-readable parameters> is a string representing parameters, which are not necessarily fully qualified. They do not affect the parser. For example, instead of (java.lang.Object, java.util.List, int) you are free to write (Object, List<String>, int)
<property name> is a full name of a property from .properties file, like "some.action.description"
<bundle name> is a fully qualified name of the property bundle, which includes its package, like "message.IdeBundle"

NOTE: If a change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack. 
An example of a ticket is https://youtrack.jetbrains.com/issue/PR-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using markdown-features:
 1) code quotes: `org.example.Foo.methodName`
 2) links [org.example.Foo](https://github.com/JetBrains/intellij-community/tree/master/)
 3) both code quotes and links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)
-->

> **NOTE** Changes from API marked with [`org.jetbrains.annotations.ApiStatus.@Experimental/ScheduledForRemoval`](upsource:///platform/util/src/org/jetbrains/annotations/ApiStatus.java) are not listed here, as incompatible changes are to be expected.

# 2020.1

## Changes in IntelliJ Platform 2020.1

`com.intellij.compiler.ant` package removed
: 'Generate Ant build' functionality is removed from the IDE. Delete the code extending this or replace it with a dependency on the `generate-ant` plugin.

`com.intellij.codeInsight.editorActions.SelectionQuotingTypedHandler.DequotingFilter` class renamed to `com.intellij.codeInsight.editorActions.SelectionQuotingTypedHandler.UnquotingFilter`
: Use `com.intellij.codeInsight.editorActions.SelectionQuotingTypedHandler.UnquotingFilter` instead.

`com.intellij.codeInsight.TargetElementUtilBase` class removed
: Use `com.intellij.codeInsight.TargetElementUtil` instead.

