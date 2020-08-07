---
title: AppCode Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
Plugin projects targeting [AppCode](https://www.jetbrains.com/objc/) can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting AppCode
The Gradle configuration of AppCode plugin projects uses neither Product-Specific nor IntelliJ IDEA Attributes.
Instead, configure AppCode plugin projects to use the `intellij.localPath` attribute.

> **NOTE** AppCode plugin development requires installing AppCode locally.

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.localPath`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Path to locally installed target version of AppCode. For example, for macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents`  |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of AppCode. For example, for macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/AppCode/ch-0/193.5662.55/AppCode.app/Contents` |

The dependency on the AppCode APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.appcode`.

## Available AppCode APIs
Use the [Exploring APIs as a Consumer](/basics/getting_started/plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries in AppCode.
Test your plugin with any version of AppCode you wish to support.
