[//]: # (title: WebStorm Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[WebStorm](https://www.jetbrains.com/webstorm/) is an IntelliJ Platform-based product.
Plugin projects for WebStorm can be developed using IntelliJ IDEA with the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).

> Follow [Building a Plugin for WebStorm – Tutorial for JavaScript Developers](learning_resources.md#articles) blog post series to get started.
>
{type="note"}

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Configuring Plugin Projects Targeting WebStorm
The configuration of WebStorm plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute) and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml) for PhpStorm.

The table below summarizes the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                      |
|----------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for IntelliJ IDEA Ultimate.<br/>(`IC` is incompatible with the required `JavaScriptLanguage` plugin)                                                                                                            |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | `192.7142.36` Set to the same BRANCH.BUILD as the WebStorm target version.                                                                                                                                           |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | Dependency on the `JavaScriptLanguage` plugin.                                                                                                                                                                       |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir)            | Path to locally installed target version of WebStorm. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/WebStorm/ch-0/192.7142.35/WebStorm.app/Contents</path>. |

The dependency on the WebStorm APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `JavaScriptLanguage`.
**Note** that for WebStorm, the <path>plugin.xml</path> file must also declare a dependency on `com.intellij.modules.platform` because `JavaScriptLanguage` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in WebStorm.

## Available WebStorm APIs

> See [](webstorm_extension_point_list.md) for the complete list.
>
{type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the libraries <path>JavaScriptLanguage.jar</path>, and <path>javascript-openapi.jar</path>.
Test your plugin with any version of WebStorm you wish to support.

### Javascript Testframework
To use existing test base classes, specify `com.jetbrains.intellij.javascript:javascript-test-framework:$VERSION$` as `testImplementation` dependency explicitly (see [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md#gradle-example-for-an-individual-module-from-the-intellij-platform)) (2020.3 and later).

## Open Source Plugins for WebStorm
When learning new plugin development it is helpful to have some representative projects for reference:
* [JS Toolbox](https://github.com/andresdominguez/jsToolbox)
* [Require.js](https://github.com/Fedott/WebStormRequireJsPlugin)
* [deep-js-completion](https://github.com/klesun/deep-js-completion)
* [Run Configuration for TypeScript](https://plugins.jetbrains.com/plugin/10841-run-configuration-for-typescript)
