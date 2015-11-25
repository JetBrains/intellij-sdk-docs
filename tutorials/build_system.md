---
title: Building plugins with the Gradle
---

When building a plugin on the IntelliJ Platform SDK, occasionally major version upgrades may cause breaking API changes. In order to prevent this from occurring unexpectedly, we recommend using Gradle with the [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin). This Gradle plugin allows you to build plugins for the IntelliJ Platform using a specific IntelliJ Platform SDK. Furthermore, it codifies a reproducible build process for compiling a plugin, to running it inside your IDE, and finally publishing it on the plugin repo, within an explicit build script which is managed by the Gradle build system. The following tutorial refers to materials that can be found in the included [gradle_plugin_demo](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/gradle_plugin_demo). Below are a series of steps to configure Gradle support.

*  [1. Prerequisites](build_system/prerequisites.md)
