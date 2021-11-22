[//]: # (title: WebStorm Plugin Development)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[WebStorm](https://www.jetbrains.com/webstorm/) is an IntelliJ Platform-based product.
Plugin projects for WebStorm can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

  > Follow _Building a Plugin for WebStorm – Tutorial for JavaScript Developers_ blog post series to get started: [Part 1](https://blog.jetbrains.com/webstorm/2021/09/building-a-plugin-for-webstorm-part-1/) [Part 2](https://blog.jetbrains.com/webstorm/2021/11/building-a-plugin-for-webstorm-part-2/)
  >
  {type="note"}

 >  Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
 >
 {type="tip"}

## Configuring Plugin Projects Targeting WebStorm
The configuration of WebStorm plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute) and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml) for PhpStorm.

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's <path>build.gradle</path> file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar <path>build.gradle</path> file for PhpStorm, see [Configuring build.gradle using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-buildgradle-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute | Attribute Value                                                                                                                                                                                                      |
|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`][properties]      | `IU` for IntelliJ IDEA Ultimate.<br/>(`IC` is incompatible with the required `JavaScriptLanguage` plugin)                                                                                                            |
| [`intellij.version`][properties]   | `192.7142.36` Set to the same BRANCH.BUILD as the WebStorm target version.                                                                                                                                           |
| [`intellij.plugins`][properties]   | Dependency on the `JavaScriptLanguage` plugin.                                                                                                                                                                       |
| [`runIde.ideDir`][dsl]             | Path to locally installed target version of WebStorm. For example, for macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/WebStorm/ch-0/192.7142.35/WebStorm.app/Contents</path>. |

[properties]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties
[dsl]: https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl

The dependency on the WebStorm APIs must be declared in the <path>plugin.xml</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `JavaScriptLanguage`.
**Note** that for WebStorm, the <path>plugin.xml</path> file must also declare a dependency on `com.intellij.modules.platform` because `JavaScriptLanguage` is not recognized as a module.
Consequently, without the `com.intellij.modules.platform` declaration the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in WebStorm.

## Available WebStorm APIs

 > See [WebStorm Extension Point List](webstorm_extension_point_list.md) for complete list.
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
