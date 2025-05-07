<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# CLion Plugin Development

<link-summary>Introduction to developing plugins for CLion.</link-summary>

<var name="productID" value="clion"/>
<var name="marketplaceProductID" value="clion"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[CLion](https://www.jetbrains.com/clion/) is an IntelliJ Platform-based product.
Plugin projects for CLion can be developed using [IntelliJ IDEA](idea.md).

> CLion is free for non-commercial use

## CLion Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`clion()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `com.intellij.clion` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

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
    clion("<versionNumber>")
    bundledPlugin("com.intellij.clion")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

> When targeting 2020.3, see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
>
{style="warning"}

The configuration of CLion plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                               | Attribute Value                                                                |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)                       | `CL` for the product CLion.                                                    |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version)                 | Set to the targeted CLion version, e.g. `2019.3.1`.                            |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins)                 | No specific declaration is needed.                                             |
| [`intellij.downloadSources`](tools_gradle_intellij_plugin.md#intellij-extension-downloadsources) | `false` is required because no public source code is available.                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)                           | Not needed; the Development Instance will automatically match `intellij.type`. |

</tab>
</tabs>

### plugin.xml

The dependency on the CLion APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `com.intellij.modules.clion` module dependency,
or `com.intellij.clion` plugin dependency when targeting only versions 2020.3+.

## Available CLion APIs

> See [](clion_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the JAR files under the External Library `Gradle:com.jetbrains:clion:<version>`.
Test your plugin with versions of CLion you intend to support.

## Open Source Plugins for CLion

When learning new APIs, it is helpful to have some representative projects for reference:

* [C/C++ Coverage](https://github.com/zero9178/C-Cpp-Coverage-for-CLion)
* [C/C++ Single File Execution](https://github.com/corochann/SingleFileExecutionPlugin)
