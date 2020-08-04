---
title: Building Plugins with Gradle
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin) Gradle plugin is the recommended solution for building IntelliJ plugins.
The plugin takes care of the dependencies of your plugin project - both the base IDE and other plugin dependencies.

> **TIP** [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) makes it easier to create and maintain your IDE plugins, having the Gradle plugin already integrated and CI covered with GitHub Actions.

> **NOTE** If a new plugin will be Scala-based, a dedicated SBT plugin [sbt-idea-plugin](https://github.com/JetBrains/sbt-idea-plugin) is available.

The gradle-intellij-plugin provides tasks to run the IDE with your plugin and to publish your plugin to the [JetBrains Plugins Repository](https://plugins.jetbrains.com).
To make sure that your plugin is not affected by [API changes](/reference_guide/api_changes_list.md) which may happen between major releases of the platform, you can easily build your plugin against many versions of the base IDE.

> **WARNING** When adding additional repositories to your Gradle build script, make sure to always use HTTPS protocol.

> **NOTE** Please make sure to always upgrade to the latest version of `gradle-intellij-plugin`.
Follow releases on [GitHub](https://github.com/JetBrains/gradle-intellij-plugin/releases).

Below are a series of guides to developing and deploying Gradle-based IntelliJ Platform Plugins:

* [Getting Started with Gradle](build_system/prerequisites.md)
* [Configuring Gradle Projects](build_system/gradle_guide.md)
* [Publishing Plugins with Gradle](build_system/deployment.md)
