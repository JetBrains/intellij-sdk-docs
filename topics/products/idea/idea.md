<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ IDEA Plugin Development

<link-summary>Introduction to developing plugins for IntelliJ IDEA.</link-summary>

<var name="productID" value="idea"/>
<var name="marketplaceProductID" value="idea_ce"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[IntelliJ IDEA](https://www.jetbrains.com/idea/) is available in two editions: IntelliJ Community Edition and IntelliJ IDEA Ultimate.
<snippet id="idea_editions">
See [Choose your edition](https://www.jetbrains.com/idea/features/#choose-your-edition) and [Feature Comparison](https://www.jetbrains.com/products/compare/?product=idea&product=idea-ce) for a detailed comparison.
</snippet>

## IntelliJ IDEA Plugin Setup

{id="ideaPluginSetup"}

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`intellijIdeaCommunity()` or `intellijIdeaUltimate()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
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
    intellijIdeaCommunity("<versionNumber>")
  }
}
```

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of IntelliJ IDEA plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                               |
|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | <p>`IC` for IntelliJ IDEA Community Edition (default)</p><p>`IU` for [](idea_ultimate.md)</p> |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | IDE version, e.g. `2022.2`                                                                    |

</tab>
</tabs>

## Available API

See [](intellij_community_plugins_extension_point_list.md) for API from bundled plugins.

[](idea_ultimate.md) provides information specific to this edition.

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

Configure bundled Kotlin [plugin dependency](plugin_dependencies.md) with plugin ID `org.jetbrains.kotlin`.

See also [UAST](uast.md) on how to support multiple JVM languages, including Kotlin.
