<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Developing a Plugin

<link-summary>Develop an IntelliJ Platform plugin using Gradle and Gradle IntelliJ Plugin.</link-summary>

IntelliJ Platform plugins can be developed by using either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE.
It is highly recommended to always use the latest available version, as the plugin development tooling support from _Plugin DevKit_ continues supporting new features.

Before starting with the actual development, make sure to understand all requirements to achieve best [](plugin_user_experience.md).

<include from="intellij_platform.md" element-id="pluginAlternatives"/>

## Gradle Plugin

The recommended solution for building IntelliJ Platform plugins is using [Gradle](https://www.gradle.org) with
a dedicated Gradle plugin:
[](tools_intellij_platform_gradle_plugin.md) or
[](tools_gradle_intellij_plugin.md) (obsolete).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

The IntelliJ IDEA Ultimate and Community editions provide the necessary plugins to support Gradle-based plugin development: _Gradle_ and _Plugin DevKit_.
To verify these plugins are installed and enabled, see the help section about [Managing Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html).

<include from="snippets.topic" element-id="pluginDevKitAvailability"/>

The Gradle plugin manages the dependencies of a plugin project – both the base IDE and other [plugin dependencies](plugin_dependencies.md).
It provides tasks to run the IDE with your plugin and to package and [publish](publishing_plugin.md#publishing-plugin-with-gradle) your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com).
To make sure that a plugin is not affected by [API changes](api_changes_list.md), which may happen between major releases of the platform, you can quickly verify your plugin against other IDEs and releases.

There are two main ways of creating a new Gradle-based IntelliJ Platform plugin project:
- dedicated generator available in the [New Project Wizard](https://www.jetbrains.com/help/idea/new-project-wizard.html) – it creates a minimal plugin project with all the required files
- [](plugin_github_template.md) available on GitHub – in addition to the required project files, it includes configuration of the GitHub Actions CI workflows

This documentation section describes the plugin structure generated with the <control>New Project</control> wizard, but the project generated with _IntelliJ Platform Plugin Template_ covers all the described files and directories.
See [](plugin_github_template.md) for more information about the advantages of this approach and instructions on how to use it.

### Alternatives

The old DevKit project model and workflow are still supported in existing projects and are recommended for [creating theme plugins](developing_themes.md).
See how to [migrate a DevKit plugin to Gradle](migrating_plugin_devkit_to_gradle.md).

A dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available for plugins implemented in Scala.

## Plugin Development Workflow

* [](creating_plugin_project.md)
  * [](plugin_github_template.md)
* [](configuring_gradle.md)
  * [](configuring_plugin_project.md) _(Obsolete)_
* [](using_kotlin.md)
* [](plugin_signing.md)
* [](publishing_plugin.md)
