[//]: # (title: Developing a Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Develop an IntelliJ Platform plugin using Gradle and Gradle IntelliJ Plugin.</link-summary>

> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{type="tip"}

IntelliJ Platform plugins can be developed by using either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).
Both include the complete set of plugin development tools.
To become more familiar with IntelliJ IDEA, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

## Gradle IntelliJ Plugin

The recommended solution for building IntelliJ Platform plugins is [](tools_gradle_intellij_plugin.md).
The IntelliJ IDEA Ultimate and Community editions bundle the necessary plugins to support Gradle-based plugin development: _Gradle_ and _Plugin DevKit_.
To verify these plugins are installed and enabled, see the help section about [Managing Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html).

Gradle IntelliJ Plugin manages the dependencies of a plugin project - both the base IDE and other [plugin dependencies](plugin_dependencies.md).
It provides tasks to run the IDE with your plugin and to package and [publish](publishing_plugin.md#publishing-plugin-with-gradle) your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com).
To make sure that a plugin is not affected by [API changes](api_changes_list.md), which may happen between major releases of the platform, you can quickly verify your plugin against other IDEs and releases.

There are two main ways of creating a new Gradle-based IntelliJ Platform plugin project:
- dedicated generator available in the [New Project Wizard](https://www.jetbrains.com/help/idea/new-project-wizard.html) - it creates a minimal plugin project with all the required files
- [](plugin_github_template.md) available on GitHub - in addition to the required project files, it includes configuration of the GitHub Actions CI workflows

This documentation section describes plugin structure generated with the <control>New Project</control> wizard, but the project generated with _IntelliJ Platform Plugin Template_ covers all the described files and directories.
See the [](plugin_github_template.md) section for more information about the advantages of this approach and instructions on how to use it.

> The old Plugin DevKit workflow still supports existing projects and is recommended for [creating theme plugins](developing_themes.md).
See how to [migrate a DevKit plugin to Gradle-based](migrating_plugin_devkit_to_gradle.md).
>
{type="tip"}

> A dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available for plugins implemented in Scala.
>
{type="tip"}

## Plugin Development Workflow

* [Creating a Gradle-based Plugin Project](creating_plugin_project.md)
* [Configuring the Gradle IntelliJ Plugin](configuring_plugin_project.md)
  * [Adding Kotlin Support](using_kotlin.md) (optional)
* [Publishing a Plugin](publishing_plugin.md)
