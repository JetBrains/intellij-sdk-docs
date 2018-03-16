---
title: Building plugins with Gradle
---

The [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin) Gradle plugin is the recommended
solution for building IntelliJ plugins. The plugin takes care of the dependencies of your plugin project - both the
base IDE and the other plugins that your plugin may depend on. It also provides tasks to run the IDE with your plugin
and to publish your plugin to the JetBrains plugins repository. To make sure that your plugin is not affected by API changes
which may happen between major releases of the platform, you can easily build your plugin against many versions
of the base IDE.

The following tutorial refers to materials that can be found in the included [gradle_plugin_demo](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/gradle_plugin_demo) project. Below are a series of guides to configure Gradle support.

*  [1. Getting Started](build_system/prerequisites.md)
*  [2. Deploying a plugin](build_system/deployment.md)