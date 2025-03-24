<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# AppCode Plugin Development

<primary-label ref="Deprecated"/>

<link-summary>Introduction to developing plugins for AppCode.</link-summary>

<var name="productID" value="objc"/>
<var name="marketplaceProductID" value="appcode"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

Plugin projects targeting [AppCode](https://www.jetbrains.com/objc/) can be developed using IntelliJ IDEA with the [](tools_gradle_intellij_plugin.md).

<snippet id="appCodeSunset">

> With the release of AppCode 2022.3, we're sunsetting the product.
> Please see this [blog post](https://blog.jetbrains.com/appcode/2022/12/appcode-2022-3-release-and-end-of-sales-and-support/) for details.
>
{style="warning"}

</snippet>

## Configuring Plugin Projects Targeting AppCode

> When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
>
{style="warning"}

The Gradle configuration of AppCode plugin projects uses neither Product-Specific nor IntelliJ IDEA Attributes.
Instead, configure AppCode plugin projects to use the [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) attribute.

> AppCode plugin development requires installing AppCode locally.
>
{style="note"}

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                   | Attribute Value                                                                                                                                                                                                              |
|--------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) | <p>Path to locally installed target version of AppCode. For example, for macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>.</p> |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)               | <p>Path to locally installed target version of AppCode. For example, for macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>.</p> |

The dependency on the AppCode APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `com.intellij.modules.appcode` module dependency, or `com.intellij.appcode` plugin dependency for plugins targeting only versions 2020.3+.

## Available AppCode APIs

> See [](appcode_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in AppCode.
Test your plugin with any version of AppCode you wish to support.
