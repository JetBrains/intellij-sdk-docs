---
title: Creating Your First Plugin
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This documentation section will help you get started with developing plugins for the *IntelliJ Platform*. You can use either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).  Both include the complete set of plugin development tools. To become more familiar with *IntelliJ IDEA*, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

There are three supported workflows available for building plugins. 
The recommended workflow for new projects is to [use GitHub Template](#using-github-template) or to [use Gradle](#using-gradle) to create everything from scratch.
The old [Plugin DevKit](#using-devkit) workflow still supports existing projects.

> **NOTE** If a new plugin will be Scala-based, a dedicated SBT plugin [sbt-idea-plugin](https://github.com/JetBrains/sbt-idea-plugin) is available. 

The Gradle workflow offers a number of advantages:
  * Representations of source sets, modules, and projects are portable,
  * Projects of any size or complexity usually require scripts for build management, which Gradle handles natively,
  * Training, documentation, and community help for general Gradle topics are widely available.

Specific to development of IntelliJ Platform plugins with the Gradle plugin for IntelliJ IDEA:
  * Changing plugin targets is easier because it is all done in `build.gradle`:
      * Switching the version of the target IntelliJ Platform (IDE),
      * Changing the target IntelliJ Platform-based IDE, e.g., from IntelliJ IDEA to PyCharm,
      * Running a plugin against alternate versions of the JetBrains runtime.
  * Gradle is fully integrated with Continuous Integration systems and [JetBrains Plugin Repository](https://plugins.jetbrains.com), so it is easy to customize and extend the build and publishing processes. 
  * Built-in verification task for `plugin.xml` and plugin distribution structure, the same checks as on [JetBrains Plugin Repository](https://plugins.jetbrains.com).

## Using GitHub Template

* [Developing plugins using GitHub Template](/tutorials/github_template.md)

## Using Gradle

* [Developing plugins using Gradle](/tutorials/build_system.md)
    * [Getting Started with Gradle](/tutorials/build_system/prerequisites.md)
    * [Configuring Gradle Projects](/tutorials/build_system/gradle_guide.md)
    * [Publishing Plugins with Gradle](/tutorials/build_system/deployment.md)

## Using DevKit
* [Developing plugins using DevKit](getting_started/using_dev_kit.md)
    * [Setting Up a Development Environment](getting_started/setting_up_environment.md)
    * [Creating a Plugin Project](getting_started/creating_plugin_project.md)
    * [Creating Actions](/tutorials/action_system/working_with_custom_actions.md)
    * [Running and Debugging a Plugin](getting_started/running_and_debugging_a_plugin.md)
    * [Deploying a Plugin](getting_started/deploying_plugin.md)
    * [Publishing a Plugin](getting_started/publishing_plugin.md)
