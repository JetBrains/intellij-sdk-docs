<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Incompatible Changes in IntelliJ Platform and Plugins API

<!--
=============== DO NOT RENAME OR MOVE THIS FILE ===============

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

<link-summary>List of known Breaking API Changes by version</link-summary>

IntelliJ API may be occasionally changed between releases, leading to existing plugins' incompatibilities with newer IDE builds.

See [](verifying_plugin_compatibility.md) on how to use Plugin Verifier and IDE inspections to check such problems.

<include from="snippets.topic" element-id="subscribeNews"/>

## Known Breaking Changes

The following pages list the breaking changes in IDE and plugin releases with required/recommended steps to take by plugin authors.

* [Changes in 2025.*](api_changes_list_2025.md)
* [Changes in 2024.*](api_changes_list_2024.md)
* [Changes in 2023.*](api_changes_list_2023.md)

### Older Versions

{collapsible="true" default-state="collapsed"}

* [Changes in 2022.*](api_changes_list_2022.md)
* [Changes in 2021.*](api_changes_list_2021.md)
* [Changes in 2020.*](api_changes_list_2020.md)

## Bundled Library Updates

Information about bundled Third-Party Software/Libraries and their respective versions is available [here](https://www.jetbrains.com/legal/third-party-software/).
