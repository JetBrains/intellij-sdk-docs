[//]: # (title: Incompatible Changes in IntelliJ Platform and Plugins API)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
=============== DO NOT RENAME OR MOVE THIS FILE ===============

Before documenting a breaking API change, please ensure that the change cannot be avoided in an alternative way.

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
<bundle name> is a fully qualified name of the property bundle, including its package, like `message.IdeBundle`

NOTE: If a code change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack.
An example of a ticket is https://youtrack.jetbrains.com/issue/MP-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes  and will be skipped in API verification.
-->

IntelliJ API may be occasionally changed between releases, leading to existing plugins' incompatibilities with newer IDE builds.

## Verifying Compatibility

### Plugin Verifier
Compatibility with newer IDEs can easily be verified for plugins hosted on the [JetBrains Plugins Repository](https://plugins.jetbrains.com) using the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/).

For local verification or non-public plugins, [intellij-plugin-verifier](https://github.com/JetBrains/intellij-plugin-verifier) can be used standalone as well.

Integration in [Gradle build](gradle_build_system.md) is available using the `runPluginVerifier` task, please see [Gradle IntelliJ Plugin - Plugin Verifier DSL](https://github.com/JetBrains/gradle-intellij-plugin#plugin-verifier-dsl) for details.

You can easily integrate it within your CI by running that task as another quality check step.
Check the IntelliJ Platform Plugin Template [GitHub workflow configuration file](https://github.com/JetBrains/intellij-platform-plugin-template/blob/main/.github/workflows/build.yml) as sample.

If your plugin is hosted on GitHub and you are _not_ using Gradle, consider using third-party GitHub Actions [IntelliJ Platform Plugin Verifier](https://github.com/marketplace/actions/intellij-platform-plugin-verifier) or [IntelliJ Plugin Verifier](https://github.com/marketplace/actions/intellij-plugin-verifier).

### IDE Support
Consider using the following [IDE inspections](https://www.jetbrains.com/help/idea/code-inspection.html) to get additional alerts about code that uses unstable API features:
- JVM languages \| Unstable API Usage
- JVM languages \| Unstable type is used in signature

Usage of deprecated [Extension Points](plugin_extensions.md) is highlighted in `plugin.xml` files.

## Known Breaking Changes

 >  Follow [JBPlatform](https://twitter.com/JBPlatform/) on Twitter and visit [JetBrains Platform Blog](https://blog.jetbrains.com/platform/) for the latest announcements.
 >
 {type="tip"}

The following pages list the breaking changes in IDE and plugin releases with required/recommended steps to take by plugin authors.

* [**Changes in 2021.***](api_changes_list_2021.md)
* [**Changes in 2020.***](api_changes_list_2020.md)
* [**Changes in 2019.***](api_changes_list_2019.md)
* [**Changes in 2018.***](api_changes_list_2018.md)
* [**Changes in 2017.***](api_changes_list_2017.md)
* [**Changes in 2016.***](api_changes_list_2016.md)

