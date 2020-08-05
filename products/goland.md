---
title: GoLand Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[GoLand](https://www.jetbrains.com/go/) is an IntelliJ Platform-based product.
Plugin projects for GoLand can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting GoLand
The configuration of GoLand plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar `build.gradle` file for PhpStorm, see [Configuring build.gradle using the IntelliJ IDEA Product Attribute](/products/dev_alternate_products.md#configuring-buildgradle-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate. (The Go plugin isn't compatible with IntelliJ IDEA Community Edition.)  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the same `IU` BRANCH.BUILD as the GoLand target version, e.g. `193.5233.102` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `org.jetbrains.plugins.go:193.5233.102.83` for the Go plugin.<br>See below for Go plugin version information. |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of GoLand. For example, on macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/193.5233.112/GoLand.app/Contents` |

The version of the Go plugin is explicitly declared because it isn't bundled with IntelliJ IDEA Ultimate Edition. 
Select a [version](https://plugins.jetbrains.com/plugin/9568-go/versions) of the Go plugin that is compatible with the IntelliJ Idea Ultimate version. 

The dependency on the Go plugin APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.go`.
The `plugin.xml` file must also declare a dependency on `com.intellij.modules.platform` as explained in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml). 
The dependency declaration is illustrated in the `plugin.xml` snippet below:

```xml
  <!-- Requires the Go plugin -->
  <depends>org.jetbrains.plugins.go</depends>
  <!-- Requires the platform module to distinguish it from a legacy plugin -->
  <depends>com.intellij.modules.platform</depends>
```

## Available GoLand APIs
Use the [Exploring APIs as a Consumer](/basics/getting_started/plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the library `intellij-go-<version>.jar`, where `<version>` corresponds to the version of the Go plugin.
Test your plugin with any version of GoLand you intend to support.

## Open Source Plugins for GoLand
When learning new APIs, it is helpful to have some representative projects for reference:
* [Go Method Generator](https://github.com/pkondratev/Intellij-go-method-generator)
* [Go Builder Generator](https://github.com/OddCN/go-builder-generator-idea-plugin)
