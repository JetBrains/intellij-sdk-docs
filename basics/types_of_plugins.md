---
title: Main Types of Plugins
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Products based on the *IntelliJ Platform* can be modified and adjusted for custom purposes by adding plugins. All downloadable plugins are available at the [JetBrains Plugins Repository](https://plugins.jetbrains.com/).

The most common types of plugins include:

* UI Themes
* Custom language support
* Framework integration
* Tool integration
* User interface add-ons

## UI Themes

[UI Themes](/reference_guide/ui_themes/themes_intro.md) give designers the ability to customize the appearance of built-in IDE UI elements.
Custom UI Themes can:

* substitute icons,
* change the colors of icons and UI controls,
* alter the borders and insets of UI controls,
* provide custom editor schemes,
* add background images.

The [UI Themes available for download](https://plugins.jetbrains.com/search?headline=164-theme&tags=Theme) illustrate the creative possibilities.

## Custom Language Support

Custom language support provides basic functionality for working with a particular programming language. This includes:

* File type recognition
* Lexical analysis
* Syntax highlighting
* Formatting
* Code insight and code completion
* Inspections and quick-fixes
* Intention actions

Refer to the [Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md) to learn more about the topic.

## Framework Integration

Framework integration consists of improved code insight features which are typical for a given framework, as well as the option to use framework-specific functionality directly from the IDE. Sometimes it also includes language support elements for a custom syntax or DSL.

* Specific code insight
* Direct access to framework-specific functionality

Refer to the [Struts 2 plugin](https://github.com/JetBrains/intellij-plugins/tree/master/struts2) as an example of framework integration.

## Tool Integration

Tool integration makes it possible to manipulate third-party tools and components directly from the IDE without switching contexts.
 
This implies:

* Implementation of additional actions 
* Related UI components
* Access to external resources

Refer to the [Gerrit integration](https://plugins.jetbrains.com/plugin/7272?pr=idea) plugin as an example.

## User Interface Add-Ons

Plugins in this category apply various changes to the standard user interface of the IDE. Some newly added components are interactive and provide new functionality, while others are limited to visual modifications only. The [Random Background](https://plugins.jetbrains.com/plugin/9692-random-background) plugin may serve as an example.
