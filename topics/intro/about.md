[//]: # (title: About This Guide)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Introduction and summary overview of contents.</excerpt>

This guide is split into several parts, similar to a textbook.
Each one builds on the content of the previous section, but it is not necessary to read the guide in order.
The [Key Topics](key_topics.md) page aims to link to the pages that are necessary to be able to understand the architecture and get started building plugins.

All source links and reference lists target IntelliJ Platform %ijPlatform%.

> While browsing this guide, you will notice that there are topics that are greyed out.
> Unfortunately, the guide is not complete and contains placeholders for specific topics.
> We are working on increasing the coverage, but if you get stuck due to missing content, please see the [](getting_help.md) section for details on how to get moving again.
>
> The guide is also [Open Source on GitHub](https://github.com/JetBrains/intellij-sdk-docs), and Pull Requests for new content, corrections or updates are always gratefully received.
> Please see the [Contributing](intellij-sdk-docs-original_CONTRIBUTING.md) page for details.
>
{type="note"}

> See also [](glossary.md) for a handy reference of common terms.
>
{type="tip"}

#### Part I — Plugins

Describes how to create a plugin that can extend the IntelliJ Platform.
Includes details on how to set up the project, register extension points, target specific versions of the IntelliJ Platform, and how to package, deploy, and test your plugins.

#### Part II — Base Platform

Describes the foundational layer of the architecture, which provides many features and utilities, such as the component model, the user interface, documents and editors, the virtual file system, settings, threading, and background tasks.
The Base Platform layer mainly comprises the functionality of the IntelliJ Platform that does not target language features or parsing.

#### Part III — Project Model

Documents the Project Model, which represents the files and configuration of the currently loaded project, as well as the build system used to build the project.

#### Part IV — PSI

The Program Structure Interface (PSI) builds the syntactic and semantic models for lots of different file types.
This section describes how to work with the PSI, navigating and manipulating the syntax trees, and also looks at the powerful references system, which allows a syntax tree node to reference an item in the semantic model.
It also details how PSI creates and uses indexes.

#### Part V — Features

Describes how to extend and interact with various features that use the PSI layer, such as code completion, navigation, <shortcut>Alt+Enter</shortcut> items, intentions, refactorings, and more.
See also the section on Custom Languages below for language-specific features that are only applicable when adding support for a new language.

#### Part VI — Testing

Describes the available infrastructure for writing automated tests covering the functionality of plugins.

#### Part VII — Custom Languages

Plugins frequently extend support for existing languages, such as adding inspections to Java files.
This section describes how to add support to the IntelliJ Platform for a new language that isn't supported by default, creating parsers, syntactic and semantic models, and all the features that build on top.

#### Part VIII — Product Specific

A lot of the functionalities in the IntelliJ Platform are language and product agnostic.
For example, code inspections work the same in Java as they do in Ruby; it is just the syntax trees and semantic information that is different.
This section describes product-specific features, such as specific project model differences and how to target them in a plugin.

#### Part IX — Custom IDEs

Documents how to use the IntelliJ Platform to create a new, custom IDE, rather than plugins to an existing product, e.g., WebStorm, or Android Studio.

#### Appendix I — Resources

Links to [useful resources](useful_links.md), a [](glossary.md), [](extension_point_list.md), tips on how to [](explore_api.md) and [](learning_resources.md).

#### Appendix II — API Changes

Provides a list of [backward-incompatible](api_changes_list.md) API changes as well as [notable changes and new features](api_notable.md) in each major release of the IntelliJ Platform.

#### Appendix III — Tools

Reference and usage guides for commonly used tools like the [](tools_gradle_intellij_plugin.md).
