---
title: Creating Your First Plugin
---

This documentation section will help you get started with developing plugins for the *IntelliJ Platform*. You can use either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).  Both include the complete set of plugin development tools. To become more familiar with *IntelliJ IDEA*, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

There are two possible workflows for building plugins. The recommended workflow for new projects is
to [use Gradle](#using-gradle). The old [Plugin DevKit](#using-devkit) workflow still supports existing projects.

The Gradle workflow offers these advantages:
* Gradle in general:
  * Representations of source sets, modules, and projects are portable,
  * Projects of any size or complexity usually require scripts for build management, which Gradle handles natively,
  * Training, documentation, and community help for general Gradle topics are widely available.
* Specific to development of IntelliJ Platform plugins with the Gradle plugin for IntelliJ IDEA:
  * Changing plugin targets is easier because it is all done in `build.gradle`:
      * Switching the version of the target IntelliJ Platform (IDE),
      * Changing the target IntelliJ Platform-based IDE, e.g., from IntelliJ IDEA to PyCharm,
      * Running a plugin against alternate versions of the JetBrains runtime.
  * Gradle is fully integrated with IntelliJ Platform-based IDE CI builds and [plugins.jetbrains.com](https://plugins.jetbrains.com), so it is easy to customize and extend the build and publishing processes. 
  * Gradle has built-in verification for `plugin.xml` module dependency and use of version-specific IntelliJ Platform APIs, the same checks as on [plugins.jetbrains.com](https://plugins.jetbrains.com).

## Using Gradle

* [Developing plugins using Gradle](/tutorials/build_system.md)
    * [Getting Started with Gradle](/tutorials/build_system/prerequisites.md)
    * [Configuring Gradle Projects](/tutorials/build_system/gradle_guide.md)
    * [Publishing Plugins with Gradle](/tutorials/build_system/deployment.md)

## Using DevKit
* [Developing plugins using DevKit](getting_started/using_dev_kit.md)
    * [Setting Up a Development Environment](getting_started/setting_up_environment.md)
    * [Creating a Plugin Project](getting_started/creating_plugin_project.md)
    * [Creating an Action](getting_started/creating_an_action.md)
    * [Running and Debugging a Plugin](getting_started/running_and_debugging_a_plugin.md)
    * [Deploying a Plugin](getting_started/deploying_plugin.md)
    * [Publishing a plugin to plugin repository](getting_started/publishing_plugin.md)
