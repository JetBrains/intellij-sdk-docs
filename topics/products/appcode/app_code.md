[//]: # (title: AppCode Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Plugin projects targeting [AppCode](https://www.jetbrains.com/objc/) can be developed using IntelliJ IDEA with the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Configuring Plugin Projects Targeting AppCode

> When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
>
{type="warning"}

The Gradle configuration of AppCode plugin projects uses neither Product-Specific nor IntelliJ IDEA Attributes.
Instead, configure AppCode plugin projects to use the [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) attribute.

> AppCode plugin development requires installing AppCode locally.
>
{type="note"}

The table below summarizes the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                   | Attribute Value                                                                                                                                                                                                   |
|--------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) | Path to locally installed target version of AppCode. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>. |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir)                | Path to locally installed target version of AppCode. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>. |

The dependency on the AppCode APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.appcode` module dependency, or `com.intellij.appcode` plugin dependency for plugins targeting only versions 2020.3+.

## Available AppCode APIs

> See [](appcode_extension_point_list.md) for the complete list.
>
{type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in AppCode.
Test your plugin with any version of AppCode you wish to support.
