---
title: About This Guide
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This guide is split into several parts, similar to a text book. Each part builds on the content of the previous part, but it is not necessary to read the guide in order. The [Key Topics](key_topics.md) page aims to link to the pages that are necessary to be able to understand the architecture and get started building plugins.

> **NOTE** While browsing this guide, you will notice that there are topics that are greyed out. Unfortunately, the guide is not complete and contains placeholders for certain topics. We are working on increasing the coverage, but if you get stuck due to missing content, please see the [Getting Help](getting_help.md) section for details on how to get moving again.
>
> The guide is also [Open Source on GitHub](https://github.com/JetBrains/intellij-sdk-docs), and Pull Requests for new content or updates are always gratefully received. A Pull Request does not need to be fully comprehensive - if a little update would help you, it will help other developers too! All pull requests will be reviewed before being accepted, so don't worry about inaccuracies. Please see the [Contributing](/CONTRIBUTING.md) page for details on building the guide locally and contributing.

#### [Part I - Plugins](/basics/basics.md)

Describes how to create a plugin that can extend the _IntelliJ Platform_. Includes details on how to set up the project, register extension points, target specific versions of the _IntelliJ Platform_, and how to package, deploy and test your plugins.

#### [Part II - Base Platform](/platform/fundamentals.md)

Describes the foundational layer of the architecture, which provides many features and utilities, such as the component model, the user interface, documents and editors, the virtual file system, settings and threading and background tasks. The Base Platform layer essentially comprises the functionality of the _IntelliJ Platform_ that does not target language features or parsing.

#### [Part III - Project Model](/basics/project_structure.md)

Documents the Project Model, which represents the files and configuration of the currently loaded project, as well as the build system used to build the project.

#### [Part IV - PSI](/basics/architectural_overview/psi.md)

The Program Structure Interface builds the syntactic and semantic models for lots of different file types. This section describes how to work with the PSI, navigating and manipulating the syntax trees, and also looks at the powerful references system, which allows a syntax tree node to reference an item in the semantic model. It also details how the PSI creates and uses indexes.

#### Part V - Features

Describes how to extend and interact with various features that use the PSI layer, such as code completion, navigation, <kbd>Alt</kbd>+<kbd>Enter</kbd> items, intentions, refactorings and more. See also the section on Custom Languages below for language specific features that are only applicable when adding support for a new language.

#### [Part VI - Testing](/basics/testing_plugins/testing_plugins.md)

Describes the available infrastructure for writing automated tests covering the functionality of plugins.

#### [Part VII - Custom Languages](/reference_guide/custom_language_support.md)

Plugins frequently extend support for existing languages, such as adding inspections to Java files. This section describes how to add support to the _IntelliJ Platform_ for a new language, that isn't supported by default, creating parsers, syntactic and semantic models and all the features that build on top.

#### [Part VIII - Product Specific](/products/dev_alternate_products.md)

A lot of the functionality in the _IntelliJ Platform_ is language and product agnostic. For example, code inspections work the same in Java as they do in Ruby, it is just the syntax trees and semantic information that is different. This section describes product specific features, such as specific project model differences and how to target them in a plugin.

#### Part IX - Custom IDEs

Documents how to use the _IntelliJ Platform_ to create a new, custom IDE, rather than plugins to an existing product, e.g. like WebStorm, or Android Studio.

#### [Part X - Plugin Repository](/appendix/plugin_repository_obsolete.md)

This part has been moved to [JetBrains Marketplace documentation](https://plugins.jetbrains.com/docs/marketplace/about-marketplace.html).

#### [Appendix I - Resources](/appendix/resources/useful_links.md)

Links to useful resources, such as the IntelliJ Community Edition source code, the Plugin Development forum and the JetBrains Platform Slack.

#### [Appendix II - API Changes](/reference_guide/api_changes_list.md)

Provides a list of [backwards-incompatible](/reference_guide/api_changes_list.md) API changes as well as [notable changes and new features](/reference_guide/api_notable/api_notable.md) in each major release of the IntelliJ Platform.
