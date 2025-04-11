<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PyCharm Plugin Development

<link-summary>Introduction to developing plugins for PyCharm.</link-summary>

<var name="productID" value="pycharm"/>
<var name="marketplaceProductID" value="pycharm_ce"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[PyCharm](https://www.jetbrains.com/pycharm/) is an IntelliJ Platform-based product.
Plugin projects for PyCharm can be developed using [IntelliJ IDEA](idea.md).

## PyCharm Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`pycharmCommunity()` or `pycharmProfessional()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the matching bundled `PythonCore`/`Pythonid` plugin (see [](#python-plugins) below) must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

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
    pycharmCommunity("<versionNumber>")
    bundledPlugin("PythonCore")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

The configuration of PyCharm plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).
The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                               | Attribute Value                                                                |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)                       | `PC` for PyCharm Community Edition<br/>`PY` for PyCharm Professional Edition   |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version)                 | Set to the targeted `PC` or `PY` version.                                      |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins)                 | See [](#python-plugins).                                                       |
| [`intellij.downloadSources`](tools_gradle_intellij_plugin.md#intellij-extension-downloadsources) | `false` is required because no public source code is available.                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)                           | Not needed; the Development Instance will automatically match `intellij.type`. |

</tab>
</tabs>

### plugin.xml

The dependency on the PyCharm APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml), the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tag must declare `com.intellij.modules.python`.

### Python Plugins

| Plugin                                                                                         | Plugin ID    | API                                 |
|------------------------------------------------------------------------------------------------|--------------|-------------------------------------|
| [Python Community Edition](https://plugins.jetbrains.com/plugin/7322-python-community-edition) | `PythonCore` | PyCharm Community Edition (`PC`)    |
| [Python](https://plugins.jetbrains.com/plugin/631-python)                                      | `Pythonid`   | PyCharm Professional Edition (`PY`) |

#### Python Plugins 2024.2

{id="python242"}

<primary-label ref="2024.2"/>

When using functionality from `Pythonid`, a dependency on _both_ `PythonCore` and `Pythonid` is now required.

## Available PyCharm APIs

See [](intellij_community_plugins_extension_point_list.md) for PyCharm Community.

## Additional Articles and Resources

* [Webinar Recording: "Live Development of a PyCharm Plugin" with Joachim Ansorg](https://blog.jetbrains.com/pycharm/2019/01/webinar-recording-live-development-of-a-pycharm-plugin-with-joachim-ansorg/)

## Open Source Plugins for PyCharm

When learning new development configurations, it is helpful to have some representative projects for reference:

* [Flake8 Support](https://github.com/jansorg/pycharm-flake8) Adds support for flake8's `# noqa` comments in PyCharm.
