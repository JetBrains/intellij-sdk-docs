---
title: WebStorm Plugin Development
---

## Introduction
WebStorm is an IntelliJ Platform-based product.
Plugin projects for WebStorm can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting WebStorm
The configuration of WebStorm plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the `gradle-intellij-plugin` attributes to set in the `build.gradle` file:

| `gradle-intellij-plugin` Attribute | Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate.<br>(`IC` is incompatible with the required `JavaScriptLanguage` plugin.  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `192.7142.36` Set to the same BRANCH.BUILD as the WebStorm target version. |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Dependency on the `JavaScriptLanguage` plugin. |
| [`runIde.ideaDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of WebStorm. For example, for macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/WebStorm/ch-0/192.7142.35/WebStorm.app/Contents` |

The dependency on the WebStorm APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `JavaScriptLanguage`.
**Note** that for WebStorm the plugin.xml file must also declare a dependency on `com.intellij.modules.platform` because `JavaScriptLanguage` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](/basics/getting_started/plugin_compatibility.md#declaring-plugin-dependencies) and will not load in WebStorm.

## Available WebStorm APIs
The `JavaScriptLanguage` plugin, which includes the module `javascript-openapi`, defines the JavaScript APIs for WebStorm.
These are considered stable APIs, but care should be taken to test your plugin with any version of WebStorm you wish to support.

## Open Source Plugins for WebStorm
When learning new plugin development it is helpful to have some representative projects for reference:
* [JS Toolbox](https://github.com/andresdominguez/jsToolbox) 
* [Require.​js](https://github.com/Fedott/WebStormRequireJsPlugin)
* [deep-js-completion](https://github.com/klesun/deep-js-completion)
