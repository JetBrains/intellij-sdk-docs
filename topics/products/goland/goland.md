<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# GoLand Plugin Development

<link-summary>Introduction to developing plugins for GoLand.</link-summary>

<var name="productID" value="go"/>
<var name="marketplaceProductID" value="go"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[GoLand](https://www.jetbrains.com/go/) is an IntelliJ Platform-based product.
Plugin projects for GoLand can be developed using [IntelliJ IDEA](idea.md).

## GoLand Plugin Setup

### Gradle Build Script

#### IntelliJ Platform Gradle Plugin (2.x)

Define a dependency using [`goland()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `org.jetbrains.plugins.go` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

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
    goland("<versionNumber>")
    bundledPlugin("org.jetbrains.plugins.go")
  }
}
```

#### Gradle IntelliJ Plugin (1.x)

{collapsible="true" default-state="collapsed"}

<primary-label ref="Obsolete"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

<tabs>

<tab title="GoLand IDE">

The configuration of targeting GoLand IDE follows the methods described in [Configuring Plugin Projects Using a Product-Specific Attribute](dev_alternate_products.md#using-a-product-specific-attribute).

Starting with 2020.2, it's possible to configure `GO` for `intellij.type` in the Gradle build script.
If you need to use Go language APIs, specifying the `GO` platform type is not enough - it is required to add a dependency to the `org.jetbrains.plugins.go` plugin.

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  version.set("2020.3")
  type.set("GO")

  // required if Go language API is needed:
  plugins.set(listOf("org.jetbrains.plugins.go"))
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
  version = '2020.3'
  type = 'GO'

  // required if Go language API is needed:
  plugins = ['org.jetbrains.plugins.go']
}
```

</tab>
</tabs>

</tab>

<tab title="Using Plugin">

The configuration of GoLand plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

The Go plugin version is explicitly declared because it isn't bundled with IntelliJ IDEA Ultimate Edition.
Select a [version](https://plugins.jetbrains.com/plugin/9568-go/versions) of the Go plugin compatible with the IntelliJ Idea Ultimate version.

| Gradle IntelliJ Plugin Attribute                                                 | Attribute Value                                                                                                                                                                                                           |
|----------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for IntelliJ IDEA Ultimate. The Go plugin isn't compatible with IntelliJ IDEA Community Edition.                                                                                                                     |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | Set to the same `IU` BRANCH.BUILD as the GoLand target version, e.g. `193.5233.102`.                                                                                                                                      |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | <p>`org.jetbrains.plugins.go:193.5233.102.83` for the Go plugin.</p><p>See below for Go plugin version information.</p>                                                                                                   |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)           | <p>Path to locally installed target version of GoLand. For example, on macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/193.5233.112/GoLand.app/Contents</path>.</p> |

</tab>

</tabs>

### plugin.xml

Depending on plugin's requirements, the following [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) entries are needed in the <path>plugin.xml</path> file:

* `com.intellij.modules.platform` - Always required. See [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml) for details.
* `org.jetbrains.plugins.go` - Required if the Go plugin APIs are used in the plugin.
* `com.intellij.modules.goland` (2020.2+) or `com.intellij.modules.go` (pre-2020.2) - Required if the plugin targets GoLand IDE only. The plugin will not be loaded in other IDEs, even if the Go plugin is present.

### Targeting IDEs Other Than GoLand

Depending on `com.intellij.modules.goland` allows a plugin to be installed in the GoLand IDE only.
However, the Go plugin can be installed in [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/) and potentially other IDEs.
To make the plugin compatible with GoLand and other IDEs supporting the Go language consider depending on:

* `org.jetbrains.plugins.go` - The plugin will be loaded only when the Go plugin is actually installed in the running IDE.
* `com.intellij.modules.go-capable` - The plugin will be loaded in IDEs that are capable of installing the Go plugin.
  Note that the Go plugin doesn't have to be actually installed when this module is present.

## Available GoLand APIs

> See [](goland_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the library <path>intellij-go-\$version\$.jar</path>, where `$version$` corresponds to the version of the Go plugin.
Test your plugin with any version of GoLand you intend to support.

## GoLand Test Framework

Please see [this issue](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/477#issuecomment-845022914) for required additional dependency setup.

## Open Source Plugins for GoLand

When learning new APIs, it is helpful to have some representative projects for reference:

* [Go Method Generator](https://github.com/pkondratev/Intellij-go-method-generator)
* [Go Builder Generator](https://github.com/OddCN/go-builder-generator-idea-plugin)
