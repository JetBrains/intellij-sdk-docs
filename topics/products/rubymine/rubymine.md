<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# RubyMine Plugin Development

<link-summary>Introduction to developing plugins for RubyMine.</link-summary>

<var name="productID" value="ruby"/>
<var name="marketplaceProductID" value="ruby"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[RubyMine](https://www.jetbrains.com/ruby/) is an IntelliJ Platform-based product.
Plugin projects for RubyMine can be developed using [IntelliJ IDEA](idea.md).

## RubyMine Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`rubymine()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `org.jetbrains.plugins.ruby` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

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
    rubymine("<versionNumber>")
    bundledPlugin("org.jetbrains.plugins.ruby")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of RubyMine plugin projects follows the methods described in [](dev_alternate_products.md#using-the-intellij-idea-product-attribute) and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the Gradle build script for a RubyMine plugin project.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                                |
|----------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for IntelliJ IDEA Ultimate.                                                                                                                                                                                               |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | Set to the same `IU` BRANCH.BUILD as the RubyMine target version, e.g. `192.7142.36`.                                                                                                                                          |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | <p>`org.jetbrains.plugins.ruby:2019.2.20191029` for the Ruby plugin.</p><p>See below for Ruby plugin version information.</p>                                                                                                  |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)           | <p>Path to locally installed target version of RubyMine. For example, on macOS:</p><p><path>/Users/\$USERNAME\$/Library/Application Support/JetBrains/Toolbox/apps/RubyMine/ch-0/192.7142.37/RubyMine.app/Contents</path>.</p> |

The required `org.jetbrains.plugins.ruby` plugin isn't compatible with IntelliJ IDEA Community Edition but is compatible with IntelliJ IDEA Ultimate (`IU`) edition.
Product compatibility is determined from the Ruby plugin [version page](https://plugins.jetbrains.com/plugin/1293-ruby/versions).
The Ruby plugin isn't bundled with `IU`, so the Ruby plugin version must be explicitly declared to support the target RubyMine (and `IU`) BRANCH.BUILD version.
The correct Ruby plugin version is also determined from the Ruby plugin version page.

</tab>
</tabs>

### plugin.xml

The dependency on the Ruby plugin APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in the [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must contain `com.intellij.modules.ruby`.

## Available RubyMine APIs

> See [](rubymine_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the library <path>ruby.jar</path>.
Test your plugin with any version of RubyMine you intend to support.

### RubyMine Test Framework

To use existing test base classes, add `TestFrameworkType.Plugin.Ruby` test-framework available from [](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType-Plugin).

Alternatively, specify `com.jetbrains.intellij.idea:ruby-test-framework:$VERSION$` as `testImplementation` dependency explicitly (see [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md#gradle-example-for-an-individual-module-from-the-intellij-platform)).

## Open Source Plugins for RubyMine

When learning new APIs, it is helpful to have some representative projects for reference:

* [Ruby-Doc-Adder](https://github.com/aristotll/RubyDocAdder)
* [Ruby Dynamic Code Insight](https://github.com/JetBrains/ruby-type-inference)
* [Railways](https://github.com/basgren/railways)
