[//]: # (title: Building Plugins with Gradle)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin) Gradle plugin is the recommended solution for building IntelliJ Platform plugins.
The plugin takes care of the dependencies of your plugin project - both the base IDE and other plugin dependencies.
It provides tasks to run the IDE with your plugin and to package and publish your plugin to the [JetBrains Plugins Repository](https://plugins.jetbrains.com).
To make sure that a plugin is not affected by [API changes](api_changes_list.md), which may happen between major releases of the platform, you can quickly verify your plugin against other IDEs and releases.

 >  [IntelliJ Platform Plugin Template](github_template.md) makes it easier to create and maintain your IDE plugins, having the Gradle plugin already integrated and CI covered with GitHub Actions.
 >
 {type="tip"}

 >  If a new plugin will be Scala-based, a dedicated SBT plugin [sbt-idea-plugin](https://github.com/JetBrains/sbt-idea-plugin) is available.
 >
 {type="note"}


 > Please make sure to always upgrade `gradle-intellij-plugin` to the latest version [![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-intellij-plugin.svg?style=flat-square)](https://github.com/jetbrains/gradle-intellij-plugin/releases)
 >
 > See [What's New & Upgrade Instructions](https://lp.jetbrains.com/gradle-intellij-plugin) for upgrading from pre-1.0 versions.
 >
 {type="note"}

Below are a series of guides to developing and deploying Gradle-based IntelliJ Platform Plugins:

* [Getting Started with Gradle](gradle_prerequisites.md)
* [Configuring Gradle Projects](gradle_guide.md)
* [Publishing Plugins with Gradle](deployment.md)
