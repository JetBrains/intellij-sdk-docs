---
layout: editable
title: Main Types of IntelliJ IDEA Plugins
---

Products based on *IntelliJ Platform* can be modified and adjusted for particular purposes by adding plugins.
All the plugins which are already available for download can be found at 
[JetBrains Plugin Repository](https://plugins.jetbrains.com/).

Most commonly used types of plugins are:

* Custom language support
* Framework integration
* Tools integration
* User interface add-ons

The majority of already existing plugins are complex, so they combine multiple function, but generally these types can be marked out.

## Custom Language Support

Custom language support provides basic functionality for working with a particular programming language. This includes:

* File type recognition
* Lexical analysis and
* Syntax highlighting
* Formatting
* Code insight and code completion
* Inspections and quickfixes
* Intention actions

Refer to the 
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.html)
to learn more about the topic.

## Framework Integration
Framework integrations consists of improved code insight features which are typical for a given framework and
option to use specific functionality of a framework directly from the IDE. May include language support elements
in case of a custom syntax or DSL.

* Specific code insight
* Direct access to the framework's functionality

Refer to 
[Struts 2 plugin](https://plugins.jetbrains.com/plugin/1698) 
as an example of a framework integration.

# Tools Integration
Tools integration makes possible to manipulate third-party tools and components directly from the IDE without making a context switch.
 
This implies:

* Implementation of additional actions 
* Related UI components
* Access to external resources

Refer to 
[Gerrit integration](https://plugins.jetbrains.com/plugin/7272?pr=idea) 
plugin as an example

# User Interface Add-Ons

Plugins from these group apply different changes to the standard user interface of the IDE. 
Newly added components can either be interactive and provide new functionality, or be just a visual modification.
[Background Image](https://plugins.jetbrains.com/plugin/72) 
plugin can be an example.



