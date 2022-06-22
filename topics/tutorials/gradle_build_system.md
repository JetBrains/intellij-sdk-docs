[//]: # (title: Building Plugins with Gradle)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The [](tools_gradle_intellij_plugin.md) is the recommended solution for building IntelliJ Platform plugins.
The plugin takes care of the dependencies of your plugin project — both the base IDE and other [plugin dependencies](plugin_dependencies.md).
It provides tasks to run the IDE with your plugin and to package and [publish](deployment.md) your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com).
To make sure that a plugin is not affected by [API changes](api_changes_list.md), which may happen between major releases of the platform, you can quickly verify your plugin against other IDEs and releases.

> [](github_template.md) makes it easier to create and maintain your IDE plugins, having the Gradle plugin already integrated and CI covered with GitHub Actions.
>
{type="tip"}

> If a new plugin is Scala-based, a dedicated [SBT plugin](https://github.com/JetBrains/sbt-idea-plugin) is available.
>
{type="note"}


> Please make sure to always upgrade [](tools_gradle_intellij_plugin.md) to the latest version [![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-intellij-plugin.svg?style=flat-square)](https://github.com/jetbrains/gradle-intellij-plugin/releases)
>
{type="note"}

Below are a series of guides to developing and deploying Gradle-based IntelliJ Platform Plugins:

* [](gradle_prerequisites.md)
* [](gradle_guide.md)
* [](deployment.md)
