[//]: # (title: Key Topics)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform is extensive and very capable, and its size and scope can initially be very daunting.
This page is intended to list the key topics that a plugin author would be interested in, and provide quick links to the most common extension points.

## Essential Concepts

- [Creating Your First Plugin](getting_started.md).
- [](testing_plugins.md).
- Component model - the IntelliJ Platform is a component-based application and is responsible for creating components and injecting dependencies.
  Understanding this is necessary for building plugins.
- [Extension points](plugin_extensions.md) - how to register components with extension points, and how to find out what extension points are available.
- [Virtual files](virtual_file.md) - all file access should go through the Virtual File System, which abstracts and caches the file system.
  It means you can work with files that are on the local file system, in a zip file or are old versions from version control.

> See also [Glossary](glossary.md) for a handy reference of common terms.
>
{type="tip"}

## Code Model

The IntelliJ Platform's code model is called the PSI - the [Program Structure Interface](psi.md).
The PSI parses code, builds indexes, and creates a semantic model.

## Common Extension Points

The IntelliJ Platform is extremely extensible, and most features and services can be extended.
Some of the common extension points are:

* [Actions](action_system.md) - menu and toolbar items
* [Code inspections](code_inspections.md) - code analysis that looks at the syntax trees and semantic models and highlight issues in the editor.
* [Intentions](code_intentions.md) - context-specific actions that are available in the <shortcut>Alt+Enter</shortcut> menu when the text caret is at a particular location.
* [Code completion](code_completion.md).
