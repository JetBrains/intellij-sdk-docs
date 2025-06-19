<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# WebStorm Plugin Development

<link-summary>Introduction to developing plugins for WebStorm.</link-summary>

<var name="productID" value="webstorm"/>
<var name="marketplaceProductID" value="webstorm"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[WebStorm](https://www.jetbrains.com/webstorm/) is an IntelliJ Platform-based product.
Plugin projects for WebStorm can be developed using [IntelliJ IDEA](idea.md).

> WebStorm is free for non-commercial use

> Follow [Building a Plugin for WebStorm – Tutorial for JavaScript Developers](learning_resources.md#articles) blog post series to get started
> and [How To Build a Plugin for JetBrains IDEs (Analog.js Example)](learning_resources.md#webinars) webinar.
>
{style="note"}

## WebStorm Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`webstorm()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `JavaScript` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

Minimum <path>build.gradle.kts</path> setup:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    webstorm("<versionNumber>")
    bundledPlugin("JavaScript")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of WebStorm plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#using-the-intellij-idea-product-attribute) and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml) for PhpStorm.

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                                 |
|----------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for [](idea_ultimate.md).                                                                                                                                                                                                  |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | `192.7142.36` Set to the same BRANCH.BUILD as the WebStorm target version.                                                                                                                                                      |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | Dependency on the `JavaScript` plugin.                                                                                                                                                                                          |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)           | <p>Path to locally installed target version of WebStorm. For example, for macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/WebStorm/ch-0/192.7142.35/WebStorm.app/Contents</path>.</p> |

</tab>
</tabs>

### plugin.xml

The dependency on the WebStorm APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in the [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `JavaScript`.

Note that for WebStorm, the <path>plugin.xml</path> file must also declare a dependency on `com.intellij.modules.platform` because `JavaScript` is not recognized as a module.
Otherwise, the plugin is assumed to be a [legacy plugin](plugin_compatibility.md#declaring-plugin-dependencies) and will not load in WebStorm.

## Available WebStorm APIs

> See [](webstorm_extension_point_list.md) for the complete list.
>
{style="note"}

See [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer).
Test your plugin with any version of WebStorm you wish to support.

### JavaScript Test Framework

<primary-label ref="2020.3"/>

To use existing test base classes, add `TestFrameworkType.Plugin.JavaScript` test-framework available from [](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType-Plugin).

Alternatively, specify `com.jetbrains.intellij.javascript:javascript-test-framework:$VERSION$` as `testImplementation` dependency explicitly (see [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md#gradle-example-for-an-individual-module-from-the-intellij-platform)).

## Open Source Plugins for WebStorm

When learning new plugin development, it is helpful to have some representative projects for reference:

* [Vue.js](%gh-ij-plugins%/vuejs)
* [JS Toolbox](https://github.com/andresdominguez/jsToolbox)
* [deep-js-completion](https://github.com/klesun/deep-js-completion)
* [Run Configuration for TypeScript](https://github.com/bluelovers/idea-run-typescript)
