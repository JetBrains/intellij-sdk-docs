# Incompatible Changes in IntelliJ Platform and Plugins API 2023.*

<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

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

## 2023.1

### IntelliJ Platform 2023.1

`com.intellij.ui.treeStructure.SimpleNode.doUpdate()` method removed
: It was replaced by `doUpdate(PresentationData)` which should now only modify the state of its parameter.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String)` method removed
: The new `setNameAndTooltip(PresentationData, String, String)` overload should be used instead.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String, String)` method removed
: The new `setNameAndTooltip(PresentationData, String, String, String)` overload should be used instead.

`com.intellij.openapi.externalSystem.view.ExternalSystemNode.setNameAndTooltip(String, String, SimpleTextAttributes)` method removed
: The new `setNameAndTooltip(PresentationData, String, String, SimpleTextAttributes)` overload should be used instead.
