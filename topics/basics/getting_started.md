[//]: # (title: Creating Your First Plugin)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This documentation section will help you get started with developing plugins for the IntelliJ Platform.
You can use either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).
Both include the complete set of plugin development tools.
To become more familiar with IntelliJ IDEA, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

There are three supported workflows available for building plugins.
The recommended workflow for new projects is to [use GitHub Template](#using-github-template) or to [use Gradle](#using-gradle) to create everything from scratch.
The old [Plugin DevKit](#using-devkit) workflow still supports existing projects.

 >  If a new plugin will be Scala-based, a dedicated SBT plugin [sbt-idea-plugin](https://github.com/JetBrains/sbt-idea-plugin) is available.
 >
 {type="note"}

The Gradle workflow offers several advantages:
  * Representations of source sets, modules, and projects are portable,
  * Projects of any size or complexity usually require scripts for build management, which Gradle handles natively,
  * Training, documentation, and community help for general Gradle topics are widely available.

Specific to development of IntelliJ Platform plugins with the Gradle plugin for IntelliJ IDEA:
  * Changing plugin targets is more comfortable because it is all done in the Gradle build file:
      * Switching the version of the target IntelliJ Platform (IDE),
      * Changing the target IntelliJ Platform-based IDE, e.g., from IntelliJ IDEA to PyCharm,
      * Running a plugin against alternate versions of the JetBrains runtime.
  * Gradle is fully integrated with Continuous Integration systems and [JetBrains Plugin Repository](https://plugins.jetbrains.com), so it is easy to customize and extend the build and publishing processes.
  * Built-in verification task for <path>plugin.xml</path> and plugin distribution structure.
  * Built-in integration with [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool used for running the compatibility checks as performed on [JetBrains Plugin Repository](https://plugins.jetbrains.com).

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
    * [Creating Actions](working_with_custom_actions.md)
    * [Running and Debugging a Plugin](running_and_debugging_a_plugin.md)
    * [Deploying a Plugin](deploying_plugin.md)
    * [Publishing a Plugin](publishing_plugin.md)
