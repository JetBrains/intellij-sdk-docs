[//]: # (title: PyCharm Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[PyCharm](https://www.jetbrains.com/pycharm/) is an IntelliJ Platform-based product.
Plugin projects for PyCharm can be developed using IntelliJ IDEA with the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Configuring Plugin Projects Targeting PyCharm
The configuration of PyCharm plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).
The table below summarizes the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) attributes to set in the Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                               | Attribute Value                                                                |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)                       | `PY` for PyCharm Professional Edition, or `PC` for PyCharm Community Edition.  |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version)                 | Set to the targeted `PY` or `PC` version.                                      |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins)                 | `Pythonid` for `PY` / `PythonCore` for `PC`.                                   |
| [`intellij.downloadSources`](tools_gradle_intellij_plugin.md#intellij-extension-downloadsources) | `false` is required because no public source code is available.                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir)                            | Not needed; the Development Instance will automatically match `intellij.type`. |

The dependency on the PyCharm APIs must be declared in the <path>plugin.xml</path> file.
As described in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml), the `<depends>` tags must declare `com.intellij.modules.python`.

See the SDK code sample [`pycharm_basics`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/product_specific/pycharm_basics/) for an example configuration.
Please note that this code sample must be imported into Gradle explicitly, as it is not included in the `_gradleCompositeBuild`.

## Available PyCharm APIs
See [](extension_point_list.md) for PyCharm Community.

The plugin [Python](https://plugins.jetbrains.com/plugin/631-python) (Plugin ID `Pythonid`) defines the APIs for PyCharm Professional.

The plugin [Python Community Edition](https://plugins.jetbrains.com/plugin/7322-python-community-edition) (Plugin ID `PythonCore`) defines the APIs for PyCharm Community.

These plugins include the modules `openapi` and `python-psi-api`.
These are considered stable APIs, but care should be taken to test your plugin with any version of PyCharm you wish to support.

## Additional Articles and Resources
* [Webinar Recording: "Live Development of a PyCharm Plugin" with Joachim Ansorg](https://blog.jetbrains.com/pycharm/2019/01/webinar-recording-live-development-of-a-pycharm-plugin-with-joachim-ansorg/)

## Open Source Plugins for PyCharm
When learning new development configurations, it is helpful to have some representative projects for reference:
* [Flake8 Support](https://github.com/jansorg/pycharm-flake8) Adds support for flake8's # noqa comments in PyCharm.
