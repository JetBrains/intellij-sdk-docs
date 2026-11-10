<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Introduction to Plugin Development

<link-summary>Develop an IntelliJ Platform plugin using Gradle and Gradle IntelliJ Plugin.</link-summary>

<include from="intellij_platform.md" element-id="pluginAlternatives"></include>

IntelliJ Platform plugins can be developed by using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) with the [Plugin DevKit](https://plugins.jetbrains.com/plugin/22851-plugin-devkit) plugin installed.
It is highly recommended to always use the latest available version of the IDE and Plugin DevKit, as the plugin development tooling is constantly improved.

Before starting with the actual development, make sure to understand all requirements to achieve the best [plugin user experience](plugin_user_experience.md).

## Gradle Build System

The recommended solution for building IntelliJ Platform plugins is using the [Gradle build system](https://www.gradle.org) with a dedicated Gradle plugin:
[IntelliJ Platform Gradle Plugin](tools_intellij_platform_gradle_plugin.md).

The IntelliJ IDEA provides the necessary plugins to support Gradle-based plugin development: _Gradle_ and _Plugin DevKit_.
To verify these plugins are installed and enabled, see the [Install plugins](https://www.jetbrains.com/help/idea/managing-plugins.html) help section.

### IntelliJ Platform Gradle Plugin

IntelliJ Platform Gradle Plugin manages the dependencies of a plugin project – both the base IDE and other [plugin dependencies](plugin_dependencies.md).
It provides tasks to run the IDE with the plugin and to package and [publish](publishing_plugin.md#publishing-plugin-with-gradle) it to the [JetBrains Marketplace](https://plugins.jetbrains.com).
It allows verifying that the plugin is not affected by [API changes](api_changes_list.md) and is backward compatible.

### Alternatives to Gradle

The old DevKit project model and workflow are still supported in existing projects and are recommended for [creating theme plugins](developing_themes.md).
See how to [migrate a DevKit plugin to Gradle](migrating_plugin_devkit_to_gradle.md).

A dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available for plugins implemented in Scala.

## Plugin Development Workflow

* [](creating_plugin_project.md)
* [](configuring_gradle.md) _(optional)_
* [](configuring_split_mode.md) _(optional)_
* [](using_kotlin.md) _(optional)_
* [](plugin_signing.md)
* [](publishing_plugin.md)
