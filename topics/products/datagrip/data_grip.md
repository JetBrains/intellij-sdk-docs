[//]: # (title: DataGrip Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[DataGrip](https://www.jetbrains.com/datagrip/) is an IntelliJ Platform-based product.
Plugin projects targeting DataGrip can be developed using IntelliJ IDEA with the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Configuring Plugin Projects Targeting DataGrip
The configuration of DataGrip plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                       |
|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for IntelliJ IDEA Ultimate.<br/>(`IC` is incompatible with the required `DatabaseTools` plugin.)                                                                                                                 |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | `2019.3` Set to the same version as the DataGrip target version, as set by `runIde.ideDir`.                                                                                                                           |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | `DatabaseTools` Dependency on the bundled `DatabaseTools` plugin.                                                                                                                                                     |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir)            | Path to locally installed target version of DataGrip. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/datagrip/ch-0/193.5233.139/DataGrip.app/Contents</path>. |

The dependency on the DataGrip APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.database`.
**Note** that DataGrip plugins must also declare a dependency on `com.intellij.modules.platform` because `com.intellij.database` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in DataGrip.

## Available DataGrip APIs

> See [](data_grip_extension_point_list.md) for the complete list.
>
{type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in `DatabaseTools`.
Test your plugin with any version of DataGrip you wish to support.
