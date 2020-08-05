---
title: DataGrip Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[DataGrip](https://www.jetbrains.com/datagrip/) is an IntelliJ Platform-based product.
Plugin projects targeting DataGrip can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting DataGrip
The configuration of DataGrip plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

> **NOTE** DataGrip plugin development may require setting an additional Gradle attribute: `runIde.jvmArgs`. See table below.

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar `build.gradle` file for PhpStorm, see [Configuring build.gradle using the IntelliJ IDEA Product Attribute](/products/dev_alternate_products.md#configuring-buildgradle-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate.<br>(`IC` is incompatible with the required `DatabaseTools` plugin.)  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `2019.3` Set to the same version as the DataGrip target version, as set by `runIde.ideDirectory` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `plugins 'DatabaseTools'` Dependency on the bundled `DatabaseTools` plugin. |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of DataGrip. For example, for macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/datagrip/ch-0/193.5233.139/DataGrip.app/Contents` |
| [`runIde.jvmArgs`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | `jvmArgs '-Didea.platform.prefix=DataGrip'`<br>Only required for `gradle-intellij-plugin` 0.4.16 or earlier. |

The additional attribute `runIde.jvmArgs` is required for versions of the `gradle-intellij-plugin` 0.4.16 and earlier.
This attribute declares that Gradle should use the DataGrip platform to run/debug plugins in a development instance.
It is not required for building plugins and manually installing them in DataGrip.
Benign, but redundant attribute if used for later versions of the `gradle-intellj-plugin`. 

The dependency on the DataGrip APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.database`.
**Note** that DataGrip plugins must also declare a dependency on `com.intellij.modules.platform` because `com.intellij.database` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](/basics/getting_started/plugin_compatibility.md#declaring-plugin-dependencies) and will not load in DataGrip.

## Available DataGrip APIs
Use the [Exploring APIs as a Consumer](/basics/getting_started/plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in `DatabaseTools`.
Test your plugin with any version of DataGrip you wish to support.
