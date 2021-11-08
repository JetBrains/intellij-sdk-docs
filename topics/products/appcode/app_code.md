[//]: # (title: AppCode Plugin Development)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
Plugin projects targeting [AppCode](https://www.jetbrains.com/objc/) can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

 >  Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
 >
 {type="tip"}

## Configuring Plugin Projects Targeting AppCode

 >  When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
 >
 {type="warning"}

The Gradle configuration of AppCode plugin projects uses neither Product-Specific nor IntelliJ IDEA Attributes.
Instead, configure AppCode plugin projects to use the `intellij.localPath` attribute.

 >  AppCode plugin development requires installing AppCode locally.
 >
 {type="note"}

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's <path>build.gradle</path> file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute | Attribute Value                                                                                                                                                                                                   |
|------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.localPath`][properties] | Path to locally installed target version of AppCode. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>. |
| [`runIde.ideDir`][dsl]             | Path to locally installed target version of AppCode. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents</path>. |

[properties]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties
[dsl]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl

The dependency on the AppCode APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.appcode`.

## Available AppCode APIs

 > See [AppCode Extension Point List](appcode_extension_point_list.md) for complete list.
 >
 {type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in AppCode.
Test your plugin with any version of AppCode you wish to support.
