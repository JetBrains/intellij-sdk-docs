---
title: CLion Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[CLion](https://www.jetbrains.com/clion/) is an IntelliJ Platform-based product.
Plugin projects for CLion can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting CLion
The configuration of CLion plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `CL` for the product CLion  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the targeted CLion version, e.g. `2019.3.1` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | No specific declaration is needed. |
| [`intellij.downloadSources`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `false` is required because no public source code is available. |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Not needed; the Development Instance will automatically match intellij.type |

The dependency on the CLion APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` elements should contain the CLion module, as illustrated in the `plugin.xml` snippet below: 

```xml
  <!-- Required for core CLion functionality -->
  <depends>com.intellij.modules.clion</depends>
```

## Available CLion APIs
Use the [Exploring APIs as a Consumer](/basics/getting_started/plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the JAR files under the External Library `Gradle:com.jetbrains:clion:<version>`.
Test your plugin with versions of CLion you intend to support.

## Open Source Plugins for CLion
When learning new APIs, it is helpful to have some representative projects for reference:
* [C/C+​+​ Coverage](https://github.com/zero9178/C-Cpp-Coverage-for-CLion)
* [C/C+​+​ Single File Execution](https://github.com/corochann/SingleFileExecutionPlugin)
