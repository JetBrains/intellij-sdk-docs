---
title: Building plugins with Gradle
---

When building a plugin on the IntelliJ Platform SDK, occasionally major version upgrades may cause breaking API changes. In order to prevent this from occurring unexpectedly, we recommend using Gradle with the [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin). This Gradle plugin allows you to build plugins for the IntelliJ Platform on a specific version of the IntelliJ Platform SDK. 

Additionally, it codifies a reproducible build process for compiling a plugin, running it inside your IDE, and finally publishing it on the plugin repo. The following tutorial refers to materials that can be found in the included [gradle_plugin_demo](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/gradle_plugin_demo) project. Below are a series of guides to configure Gradle support.

*  [1. Getting Started](build_system/prerequisites.md)
*  [2. Deploying a plugin](build_system/deployment.md)