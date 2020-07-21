---
title: PyCharm Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[PyCharm](https://www.jetbrains.com/pycharm/) is an IntelliJ Platform-based product.
Plugin projects for PyCharm can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting PyCharm
The configuration of PyCharm plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).
The table below summarizes the `gradle-intellij-plugin` attributes to set in the `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `PY` for PyCharm Professional Edition, or `PC` for PyCharm Community Edition |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the targeted `PY` or `PC` version |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | No specific declaration is needed to use `PY` or `PC` APIs |
| [`intellij.downloadSources`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `false` is required because no public source code is available. |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Not needed; the Development Instance will automatically match `intellij.type` |

The dependency on the PyCharm APIs must be declared in the `plugin.xml` file.
As described in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml), the `<depends>` tags must declare `com.intellij.modules.python`.

See the SDK code sample [`pycharm_basics`](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/product_specific/pycharm_basics/) for an example configuration.
Please note that this code sample must be imported into Gradle explicitly, as it is not included in the `_gradleCompositeBuild`.

## Available PyCharm APIs
The plugin [Python](https://plugins.jetbrains.com/plugin/631-python) defines the APIs for PyCharm Professional.
The plugin [Python Community Edition](https://plugins.jetbrains.com/plugin/7322-python-community-edition) defines the APIs for PyCharm Community.
These plugins include the modules `openapi` and `python-psi-api`.
These are considered stable APIs, but care should be taken to test your plugin with any version of PyCharm you wish to support.

## Additional Articles and Resources
* [Webinar Recording: “Live Development of a PyCharm Plugin” with Joachim Ansorg](https://blog.jetbrains.com/pycharm/2019/01/webinar-recording-live-development-of-a-pycharm-plugin-with-joachim-ansorg/) 

## Open Source Plugins for PyCharm
When learning new development configurations, it is helpful to have some representative projects for reference:
* [Flake8 Support](https://github.com/jansorg/pycharm-flake8) Adds support for flake8's # noqa comments in PyCharm.
