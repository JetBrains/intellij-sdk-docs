[//]: # (title: Creating A Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{type="tip"}

This documentation section will help you get started with developing plugins for the IntelliJ Platform.
You can use either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).
Both include the complete set of plugin development tools.
To become more familiar with IntelliJ IDEA, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

The recommended solution for building IntelliJ Platform plugins is [](tools_gradle_intellij_plugin.md).
The plugin takes care of the dependencies of your plugin project - both the base IDE and other [plugin dependencies](plugin_dependencies.md).
It provides tasks to run the IDE with your plugin and to package and [publish](deployment.md) your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com).
To make sure that a plugin is not affected by [API changes](api_changes_list.md), which may happen between major releases of the platform, you can quickly verify your plugin against other IDEs and releases.

The old Plugin DevKit workflow still supports existing projects and is recommended for [creating UI Theme plugins](themes_getting_started.md).

> A dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available for plugins implemented in Scala.
>
{type="tip"}

## Plugin Development Workflow

* [](gradle_prerequisites.md)
  * [](github_template.md) (optional)
  * [](migrating_plugin_devkit_to_gradle.md) (optional)
* [](gradle_guide.md)
* [](kotlin.md) (optional)
* [Configuring Plugin Signing](plugin_signing.md)
* [Publishing a Plugin](deployment.md)
