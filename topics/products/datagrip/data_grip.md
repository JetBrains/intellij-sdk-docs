[//]: # (title: DataGrip Plugin Development)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[DataGrip](https://www.jetbrains.com/datagrip/) is an IntelliJ Platform-based product.
Plugin projects targeting DataGrip can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

 >  Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
 >
 {type="tip"}

## Configuring Plugin Projects Targeting DataGrip
The configuration of DataGrip plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

 >  DataGrip plugin development may require setting an additional Gradle attribute: `runIde.jvmArgs`. See table below.
 >
 {type="note"}

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's <path>build.gradle</path> file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar <path>build.gradle</path> file for PhpStorm, see [Configuring build.gradle using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-buildgradle-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute | Attribute Value                                                                                                                                                                                                       |
|------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`][properties]      | `IU` for IntelliJ IDEA Ultimate.<br/>(`IC` is incompatible with the required `DatabaseTools` plugin.)                                                                                                                 |
| [`intellij.version`][properties]   | `2019.3` Set to the same version as the DataGrip target version, as set by `runIde.ideDir`.                                                                                                                           |
| [`intellij.plugins`][properties]   | `plugins 'DatabaseTools'` Dependency on the bundled `DatabaseTools` plugin.                                                                                                                                           |
| [`runIde.ideDir`][dsl]             | Path to locally installed target version of DataGrip. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/datagrip/ch-0/193.5233.139/DataGrip.app/Contents</path>. |
| [`runIde.jvmArgs`][dsl]            | `jvmArgs '-Didea.platform.prefix=DataGrip'`<br/>Only required for `gradle-intellij-plugin` 0.4.16 or earlier.                                                                                                         |

[properties]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties
[dsl]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl

The additional attribute `runIde.jvmArgs` is required for versions of the `gradle-intellij-plugin` 0.4.16 and earlier.
This attribute declares that Gradle should use the DataGrip platform to run/debug plugins in a development instance.
It is not required for building plugins and manually installing them in DataGrip.
Benign, but redundant attribute if used for later versions of the `gradle-intellj-plugin`.

The dependency on the DataGrip APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.database`.
**Note** that DataGrip plugins must also declare a dependency on `com.intellij.modules.platform` because `com.intellij.database` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in DataGrip.

## Available DataGrip APIs

 > See [DataGrip Extension Point List](data_grip_extension_point_list.md) for complete list.
 >
 {type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in `DatabaseTools`.
Test your plugin with any version of DataGrip you wish to support.
