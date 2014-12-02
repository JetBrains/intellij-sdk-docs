Main types of IntelliJ IDEA plugins
=========
IntelliJ IDEA can be modified and adjusted for particular purposes by adding plugins.
All the plugins which are already available for download can be found at [JetBrains Plugin Repository] (https://plugins.jetbrains.com/).
Most commonly used types of plugins are:

* Custom language support
* Framework integration
* Tools integration
* User interface add-ons
The majority of already existing plugins are complex, so they combine multiple function, but generally these types can be marked out
----------
#Custom language support

Custom language support provides basic functionality for working with a particular programming language. This includes:

* File type recognition
* Lexical analysis and
* Syntax highlighting
* Formatting
* Code insight and code completion
* Inspections and quickfixes
* Intention actions

[Tutorial] (http://confluence.jetbrains.com/display/IntelliJIDEA/Custom+Language+Support) for a custom language support implementations

#Framework integration
Framework integrations consists of improved code insight features which are typical for a given framework and
option to use specific functionality of a framework directly from the IDE. May include language support elements
in case of a custom syntax or DSL.

* Specific code insight
* Direct access to the framework's functionality

A [framework integration] (https://plugins.jetbrains.com/plugin/6971?pr=idea) plugin for AngularJS

#Tools integration
Tools integration makes possible to manipulate third-party tools and components directly from the IDE without making a context switch. This implies:

* Implementation of additional actions 
* Related UI components
* Access to external resources

[TeamCity integration] (https://plugins.jetbrains.com/plugin/1820?pr=idea) plugin as an example

#User interface add-ons
Plugins from these group apply different changes to the standard user interface of the IDE. Newly added components can either be interactive and provide new functionality, or be just a visual modification.
[Background Image] (https://plugins.jetbrains.com/plugin/274?pr=idea) plugin can be an example.



