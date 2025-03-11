<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Key Topics

<link-summary>Overview of IntelliJ Platform key concepts and extension points.</link-summary>

The IntelliJ Platform is extensive and very capable, and its size and scope can initially be very daunting.
This page is intended to list the key topics that a plugin author would be interested in, and provide quick links to the most common extension points.

## Essential Concepts

- [](developing_plugins.md).
- [](testing_plugins.md).
- Component model - the IntelliJ Platform is a component-based application and is responsible for creating components and injecting dependencies.
  Understanding this is necessary for building plugins.
- [Extension Points](plugin_extensions.md) - how to register components with extension points, and how to find out what extension points are available.
- [](virtual_file.md) - all file access should go through the Virtual File System, which abstracts and caches the file system.
  It means you can work with files that are on the local file system, in a zip file or are old versions from version control.

> See also [](glossary.md) for a handy reference of common terms.
>

## Code Model

The IntelliJ Platform's code model is called the PSI - the [Program Structure Interface](psi.md).
The PSI parses code, builds indexes, and creates a semantic model.

## Common Extension Points

The IntelliJ Platform is extremely extensible, and most features and services can be extended.
Some common extension points are:

* [](action_system.md) - menu and toolbar items
* [](code_inspections.md) - code analysis that looks at the syntax trees and semantic models and highlight issues in the editor.
* [](code_intentions.md) - context-specific actions that are available in the <shortcut>Alt+Enter</shortcut> menu when the text caret is at a particular location.
* [](code_completion.md).
