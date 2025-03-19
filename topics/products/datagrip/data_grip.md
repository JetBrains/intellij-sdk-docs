<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# DataGrip Plugin Development

<link-summary>Introduction to developing plugins for DataGrip.</link-summary>

<var name="productID" value="datagrip"/>
<var name="marketplaceProductID" value="dbe"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[DataGrip](https://www.jetbrains.com/datagrip/) is an IntelliJ Platform-based product.
Plugin projects targeting DataGrip can be developed using [IntelliJ IDEA](idea.md).

## DataGrip Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">


Define a dependency using [`datagrip()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `com.intellij.database` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

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
    datagrip("<versionNumber>")
    bundledPlugin("com.intellij.database")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of DataGrip plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                                  |
|----------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | <p>`IU` for IntelliJ IDEA Ultimate.</p><p>_`IC` is incompatible with the required `DatabaseTools` plugin._</p>                                                                                                                   |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | `2019.3` Set to the same version as the DataGrip target version, as set by `runIde.ideDir`.                                                                                                                                      |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | `DatabaseTools` Dependency on the bundled `DatabaseTools` plugin.                                                                                                                                                                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)           | <p>Path to locally installed target version of DataGrip. For example, for macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/datagrip/ch-0/193.5233.139/DataGrip.app/Contents</path>.</p> |

</tab>
</tabs>

### plugin.xml

The dependency on the DataGrip APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `com.intellij.database`.

Note that DataGrip plugins must also declare a dependency on `com.intellij.modules.platform` because `com.intellij.database` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in DataGrip.

## Available DataGrip APIs

> See [](data_grip_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in `DatabaseTools`.
Test your plugin with any version of DataGrip you wish to support.
