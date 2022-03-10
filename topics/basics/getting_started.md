[//]: # (title: Creating Your First Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This documentation section will help you get started with developing plugins for the IntelliJ Platform.
You can use either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).
Both include the complete set of plugin development tools.
To become more familiar with IntelliJ IDEA, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{type="tip"}

There are three supported workflows available for building plugins.
The recommended workflow for new projects is to [use GitHub Template](#using-github-template) or to [use Gradle](#using-gradle) to create everything from scratch.
The old [Plugin DevKit](#using-devkit) workflow still supports existing projects.

> If a new plugin will be Scala-based, a dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available.
>
{type="note"}

## Using GitHub Template

* [Developing plugins using GitHub Template](github_template.md)

## Using Gradle

* [Developing plugins using Gradle](gradle_build_system.md)
    * [Getting Started with Gradle](gradle_prerequisites.md)
    * [Configuring Gradle Projects](gradle_guide.md)
    * [Publishing Plugins with Gradle](deployment.md)

## Using DevKit

* [Developing plugins using DevKit](using_dev_kit.md)
    * [Setting Up a Development Environment](setting_up_environment.md)
    * [Creating a Plugin Project](creating_plugin_project.md)
    * [Running and Debugging a Plugin](running_and_debugging_a_plugin.md)
    * [Deploying a Plugin](deploying_plugin.md)
    * [Publishing a Plugin](publishing_plugin.md)
