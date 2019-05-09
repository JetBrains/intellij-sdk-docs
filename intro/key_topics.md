---
title: Key Topics
---

The _IntelliJ Platform_ is very large, and very capable, and its size and scope can initially be very daunting. This page is intended to list the key topics that a plugin author would be interested in, and provide quick links to the most common extension points.

## Essential concepts

- [Getting Started](/basics/getting_started.md) with plugins.
- [Testing plugins](/basics/testing_plugins.md).
- [Architectural overview] - a brief tour of the different layers of the _IntelliJ Platform_.
- Component model - the _IntelliJ Platform_ is a component based application, and is responsible for creating components and injecting dependencies. Understanding this is necessary for building plugins.
- [Extension points](/basics/plugin_structure/plugin_extensions_and_extension_points.md) - how to register components with extension points, and how to find out what extension points are available.
- [Virtual files](/basics/architectural_overview/virtual_file.md) - all file access should go through the Virtual File System which abstracts and caches the file system. This means you can work with files that are on the local file system, in a zip file or are old versions from version control.

## Code model

The _IntelliJ Platform_'s code model is called the PSI - the Program Structure Interface. The PSI parses code, builds indexes and creates a semantic model.

## Common extension points

The _IntelliJ Platform_ is extremely extensible, and most features and services can be extended. Some of the common extension points are:

* [Actions](/tutorials/action_system.md) - menu and toolbar items
* [Code inspections](/tutorials/code_inspections.md) - code analysis that looks at the syntax trees and semantic models and highlight issues in the editor.
* [Intentions](/tutorials/code_intentions.md) - context specific actions that are available in the <kbd>Alt</kbd>+<kbd>Enter</kbd> menu when the text caret is at a certain location.
* [Code completion](/reference_guide/custom_language_support/code_completion.md).
