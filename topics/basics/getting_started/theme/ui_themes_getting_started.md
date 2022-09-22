[//]: # (title: Getting Started)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

[UI themes](types_of_plugins.md#ui-themes) can be developed by using either [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) as your IDE (it is highly recommended to use the latest available version).
Both include the complete set of development tools required to develop theme plugins.
To become more familiar with IntelliJ IDEA, please refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/).

A UI theme is one of the [plugin types](types_of_plugins.md).
Its structure doesn't significantly differ from plugins extending IDE behavior, and can be implemented by using one of the supported approaches: _DevKit_ or _Gradle_.
The choice of the development approach depends on the project requirements and developer experience.
See the below sections for details.

## DevKit-Based UI Theme Project

Developing UI theme plugins with DevKit is the simplest solution and does not require experience with Gradle or similar build tools.
The DevKit project structure is generated by default when an IDE Plugin theme project is created by using the New Project Wizard.

See the [](creating_ui_theme_project.md) section for the development instructions.

## Gradle-Based UI Theme Project

Developing UI theme plugins with Gradle requires experience with the Gradle or a similar build tool.
It offers the possibility of automating some parts of the development process, like patching <path>[plugin.xml](plugin_configuration_file.md)</path> file with the theme plugin version and other data, as well as building the plugin distribution on CI servers and publishing it to [JetBrains Marketplace](https://plugins.jetbrains.com).

If your project requires any of the mentioned capabilities, see [Creating a Plugin using Gradle](plugins_getting_started.md) for more details.