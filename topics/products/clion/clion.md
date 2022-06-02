[//]: # (title: CLion Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[CLion](https://www.jetbrains.com/clion/) is an IntelliJ Platform-based product.
Plugin projects for CLion can be developed using IntelliJ IDEA with the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Configuring Plugin Projects Targeting CLion

> When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
>
{type="warning"}

The configuration of CLion plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                               | Attribute Value                                                                |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)                       | `CL` for the product CLion.                                                    |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version)                 | Set to the targeted CLion version, e.g. `2019.3.1`.                            |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins)                 | No specific declaration is needed.                                             |
| [`intellij.downloadSources`](tools_gradle_intellij_plugin.md#intellij-extension-downloadsources) | `false` is required because no public source code is available.                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir)                            | Not needed; the Development Instance will automatically match `intellij.type`. |

The dependency on the CLion APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.clion` module dependency, or `com.intellij.clion` plugin dependency for plugins targeting only versions 2020.3+.

## Available CLion APIs

> See [](clion_extension_point_list.md) for the complete list.
>
{type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the JAR files under the External Library `Gradle:com.jetbrains:clion:<version>`.
Test your plugin with versions of CLion you intend to support.

## Open Source Plugins for CLion
When learning new APIs, it is helpful to have some representative projects for reference:
* [C/C++ Coverage](https://github.com/zero9178/C-Cpp-Coverage-for-CLion)
* [C/C++ Single File Execution](https://github.com/corochann/SingleFileExecutionPlugin)
