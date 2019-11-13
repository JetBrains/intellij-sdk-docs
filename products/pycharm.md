---
title: PyCharm Plugin Development
---

## Introduction
PyCharm is an IntelliJ Platform-based product.
Plugin projects for PyCharm can be developed using IntelliJ IDEA with the Gradle plugin.

## Configuring Plugin Projects Targeting PyCharm
The configuration of PyCharm plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-the-plugin-pluginxml-file).
The table below summarizes the attributes to set in the `build.gradle` file:

| Gradle Plugin Attribute | Value |
|-----------|-------|
| `intellij.type` | `PY` or `PC` |
| `intellij.version` | Match the targeted `PY` or `PC` version |
| `intellij.plugins` | No specific declaration is needed to use `PY` or `PC` APIs |
| `runIde.ideaDirectory` | Not needed; the Development Instance will automatically match `intellij.type` |

The dependency on the PyCharm APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.python`

See the SDK code sample [`pycharm_basics`](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/product_specific/pycharm_basics/) for an example configuration.

## Available PyCharm APIs
The APIs from the set of [Modules Available in All Products](/basics/getting_started/plugin_compatibility.md#modules-available-in-all-products) can be used in PyCharm plugins.
APIs for PyCharm Professional are defined in the plugin [Python](https://plugins.jetbrains.com/plugin/631-python).
The APIs for PyCharm Community are defined in the plugin [Python Community Edition](https://plugins.jetbrains.com/plugin/7322-python-community-edition/).
These are considered stable APIs, but care should be taken to test your plugin with any version of PyCharm you wish to support.

## Additional Articles and Resources

## Open Source Plugins for PyCharm