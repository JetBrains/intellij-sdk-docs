<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ IDEA Plugin Development

<link-summary>Introduction to developing plugins for IntelliJ IDEA.</link-summary>

<var name="productID" value="idea"/>
<var name="marketplaceProductID" value="idea"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

## IntelliJ IDEA Plugin Setup

{id="ideaPluginSetup"}

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`intellijIdea()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

Minimum <path>build.gradle.kts</path> setup:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdea("<versionNumber>")
  }
}
```

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of IntelliJ IDEA plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                   |
|----------------------------------------------------------------------------------|-----------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for [IntelliJ IDEA](idea.md) |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | IDE version, e.g. `2022.2`        |

</tab>
</tabs>

## Available API

See [](intellij_community_plugins_extension_point_list.md) for API from bundled plugins.

### Product-Specific APIs in IntelliJ IDEA

The following pages describe targeting APIs available in IntelliJ IDEA:

* [Spring API](spring_api.md)
* [Tomcat Integration](tomcat_integration.md)

## Java Plugin

<tldr>

**Java Plugin Extension Points**: [](intellij_community_plugins_extension_point_list.md#java-plugin)

</tldr>

Configure bundled Java [plugin dependency](plugin_dependencies.md) with plugin ID `com.intellij.java`.

[PSI Cookbook](psi_cookbook.md#java-specific) lists a number of common operations for working with Java PSI.

Depending on the exact functionality, a plugin can also target [](uast.md) to support multiple JVM languages, including Java and Kotlin.

### Java Test Framework

To use existing test base classes, add `TestFrameworkType.Plugin.Java` test-framework available from [](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType-Plugin).

Alternatively, specify `com.jetbrains.intellij.java:java-test-framework:$VERSION$` as `testImplementation` dependency explicitly (see [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md#gradle-example-for-an-individual-module-from-the-intellij-platform)).

## Kotlin Plugin

<tldr>

**Kotlin Plugin Extension Points**: [](intellij_community_plugins_extension_point_list.md#kotlin-plugin)

</tldr>

Configure the bundled Kotlin [plugin dependency](plugin_dependencies.md) with plugin ID `org.jetbrains.kotlin`.

[Kotlin PSI](https://kotlin.github.io/analysis-api/fundamentals.html#kotlin-psi) explains the differences from the IntelliJ IDEA PSI and introduces the Analysis API as the new recommended way to work with Kotlin code.

See also [UAST](uast.md) on how to support multiple JVM languages, including Kotlin.

### Analysis API

> Analysis API is available since version 2024.2.

Starting from IntelliJ IDEA 2025.1, K2 Kotlin mode is enabled by default.
To ensure your plugin works with the newest versions of IntelliJ IDEA, you must migrate to the Analysis API and declare compatibility with the K2 compiler.

For migration details, see [Migrating from K1](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

For comprehensive information about the Analysis API, refer to [Kotlin Analysis API Documentation](https://kotlin.github.io/analysis-api/index_md.html).

### Testing K1 (K2) mode

See [Testing in K1 Locally](https://kotlin.github.io/analysis-api/testing-in-k1-locally.html).

### Kotlin Code FAQ

[How to shorten references?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010025120-Add-new-parameter-into-kotlin-data-class-from-IDEA-plugin?page=1#community_comment_360002950760)
