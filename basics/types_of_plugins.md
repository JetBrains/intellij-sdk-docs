---
layout: editable
title: Main Types of IntelliJ IDEA Plugins
---

Products based on *IntelliJ Platform* can be modified and adjusted for custom purposes by adding plugins.
All downloadable plugins are available at 
[JetBrains Plugin Repository](https://plugins.jetbrains.com/).

The most common types of plugins include:

* Custom language support
* Framework integration
* Tool integration
* User interface add-ons

The majority of existing plugins are complex and combine multiple functions, but typically fall under the following categories:

## Custom Language Support

Custom language support provides basic functionality for working with a particular programming language. This includes:

* File type recognition
* Lexical analysis
* Syntax highlighting
* Formatting
* Code insight and code completion
* Inspections and quick-fixes
* Intention actions

Refer to the 
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.html)
to learn more about the topic.

## Framework Integration
Framework integration consists of improved code insight features which are typical for a given framework, as well as the option to use framework-specific functionality directly from the IDE. Sometimes it also includes language support elements for a custom syntax or DSL.

* Specific code insight
* Direct access to framework-specific functionality

Refer to 
[Struts 2 plugin](https://plugins.jetbrains.com/plugin/1698) 
as an example of framework integration.

# Tool Integration
Tool integration makes it possible to manipulate third-party tools and components directly from the IDE without switching contexts.
 
This implies:

* Implementation of additional actions 
* Related UI components
* Access to external resources

Refer to the 
[Gerrit integration](https://plugins.jetbrains.com/plugin/7272?pr=idea) 
plugin as an example.

# User Interface Add-Ons

Plugins in this category apply various changes to the standard user interface of the IDE. 
Some newly added components are interactive and provide new functionality, while others are limited to visual modifications only.
The following plugin may serve as an example: [Background Image](https://plugins.jetbrains.com/plugin/72) 
