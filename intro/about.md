---
title: About this Guide
---

This guide is split into several parts, similar to a text book. Each part builds on the content of the previous part, but it is not necessary to read the guide in order. The [Key Topics](key_topics.md) page aims to link to the pages that are necessary to be able to understand the architecture and get started building plugins.

本指南分为几个部分，类似于课本。 每个部分都建立在前一部分的内容上，但是没有必要按顺序阅读指南。 关键主题页面旨在链接到能够理解架构和开始构建插件所必需的页面。

> **NOTE** While browsing this guide, you will notice that there are topics that are greyed out. Unfortunately, the guide is not complete and contains placeholders for certain topics. We are working on increasing the coverage, but if you get stuck due to missing content, please see the [Getting Help](getting_help.md) section for details on how to get moving again.
> **注意** 在浏览本指南时，您会注意到存在灰色的主题。 不幸的是，该指南并不完整，并包含某些主题的占位符。 我们正在努力扩大覆盖范围，但是如果您由于缺少内容而陷入困境，请参阅 [Getting Help](getting_help.md) 部分以了解有关如何再次移动的详细信息。
>
> The guide is also [Open Source on GitHub](https://github.com/JetBrains/intellij-sdk-docs), and Pull Requests for new content or updates are always gratefully received. A Pull Request does not need to be fully comprehensive - if a little update would help you, it will help other developers too! All pull requests will be reviewed before being accepted, so don't worry about inaccuracies. Please see the [Contributing](/CONTRIBUTING.md) page for details on building the guide locally and contributing.
> 该指南在 [Open Source on GitHub](https://github.com/JetBrains/intellij-sdk-docs)，并且总是非常感谢接收新内容或更新的请求。 合并请求并不需要全面全面 - 如果稍微更新会对您有所帮助，它也会帮助其他开发者！ 所有提款请求在被接受之前都会被审核，所以不要担心不准确。 请参阅 [Contributing](/CONTRIBUTING.md) 页面，了解有关在本地构建指南和贡献的详细信息。

* [**Part I - Plugins**](/basics.md)

    Describes how to create a plugin that can extend the _IntelliJ Platform_. Includes details on how to set up the project, register extension points, target specific versions of the _IntelliJ Platform_, and how to package, deploy and test your plugins.

    介绍如何创建一个可以扩展 _IntelliJ Platform_ 的插件。 包括如何设置项目，注册扩展点，指定 _IntelliJ Platform_ 的特定版本以及如何打包，部署和测试插件的详细信息。

* [**Part II - Architecture**](/basics/architectural_overview.md)

    Provides a high level, introductory overview of the architecture of the _IntelliJ Platform_, looking at how it is split into several layers - Base Platform, Project Model, Features, and so on. It also introduces the Program Structure Index (PSI) which provides the _IntelliJ Platform_ with syntactic and semantic models for many different languages.

    提供关于 _IntelliJ Platform_ 架构的高层次介绍性概述，了解它如何拆分为几个层次 - 基础平台，项目模型，功能等。 它还介绍了 Program Structure Index (PSI)，它为 _IntelliJ Platform_ 提供了许多不同语言的语法和语义模型。
    
* [**Part III - Base Platform**](/platform/fundamentals.md)

    Describes the foundational layer of the architecture, which provides many features and utilities, such as the component model, the user interface, documents and editors, the virtual file system, settings and threading and background tasks. The Base Platform layer essentially comprises the functionality of the _IntelliJ Platform_ that does not target language features or parsing.
    
    介绍该体系结构的基础层，该体系结构提供许多功能和实用程序，如组件模型，用户界面，文档和编辑器，虚拟文件系统，设置以及线程和后台任务。 基本平台层基本上包含 _IntelliJ Platform_ 的功能，该功能不针对语言功能或解析。

* [**Part IV - Project Model**](/reference_guide/project_model.md)

    Documents the Project Model, which represents the files and configuration of the currently loaded project, as well as the build system used to build the project.
    
    项目模型，它表示当前加载的项目的文件和配置，以及用于构建项目的构建系统。
    
* [**Part V - PSI**](/basics/indexing_and_psi_stubs.md)

    The Program Structure Index builds the syntactic and semantic models for lots of different file types. This section describes how to work with the PSI, navigating and manipulating the syntax trees, and also looks at the powerful references system, which allows a syntax tree node to reference an item in the semantic model. It also details how the PSI creates and uses indexes.
    
    Program Structure Index 为许多不同的文件类型构建语法和语义模型。 本节介绍如何使用PSI，浏览和操作语法树，还要查看强大的引用系统，它允许语法树节点引用语义模型中的项目。 它还详细介绍了PSI如何创建和使用索引。

* **Part VI - Features**

    Describes how to extend and interact with various features that use the PSI layer, such as code completion, navigation, <kbd>Alt</kbd>+<kbd>Enter</kbd> items, intentions, refactorings and more. See also the section on Custom Languages below for language specific features that are only applicable when adding support for a new langauge.
    
    介绍如何扩展和使用各种使用PSI图层的功能进行交互，例如代码完成，导航， <kbd>Alt</kbd>+<kbd>Enter</kbd> 项目，意向，重构等。 另请参阅下面的“自定义语言”部分，了解仅在添加对新语言的支持时适用的语言特定功能。

* [**Part VII - Product Specific**](/products/idea.md)

    A lot of the functionality in the _IntelliJ Platform_ is language and product agnostic. For example, code inspections work the same in Java as they do in Ruby, it is just the syntax trees and semantic information that is different. This section describes product specific features, such as specific project model differences and how to target them in a plugin.

    _IntelliJ Platform_ 中的许多功能都是语言和产品无关的。 例如，代码检查在Java中的工作方式与在Ruby中的工作方式相同，只是语法树和语义信息不同。 本节介绍产品的特定功能，如特定的项目模型差异以及如何将其定位到插件中。
    
* [**Part VIII - Custom Languages**](/reference_guide/custom_language_support.md)

    Plugins frequently extend support for existing languages, such as adding inspections to Java files. This section describes how to add support to the _IntelliJ Platform_ for a new language, that isn't supported by default, creating parsers, syntactic and semantic models and all the features that build on top.
    
    插件经常扩展对现有语言的支持，例如添加对Java文件的检查。 本节介绍如何向 _IntelliJ Platform_ 添加对默认不支持的新语言的支持，创建解析器，语法和语义模型以及构建在顶部的所有功能。

* **Part IX - Custom IDEs**

    Documents how to use the _IntelliJ Platform_ to create a new, custom IDE, rather than plugins to an existing product, e.g. like WebStorm, or Android Studio.
    
    介绍如何使用 _IntelliJ Platform_ 创建新的自定义IDE，而不是现有产品的插件，例如 如 WebStorm 或 Android Studio。

* [**Part X - Plugin Repository API**](/plugin_repository/index.md)

    Documents the API for the [Plugin Repository](https://plugins.jetbrains.com) service that JetBrains maintains and is used to host plugins. It is not necessary to know this API in order to publish plugins - plugins can be uploaded manually, or via the Gradle IntelliJ Plugin.
    
    记录 JetBrains 维护并用于托管插件的 [Plugin Repository](https://plugins.jetbrains.com) 服务的API。 没有必要知道这个API来发布插件 - 插件可以手动上传，或通过 Gradle IntelliJ 插件。

* [**Appendix I - Tutorials**](/tutorials.md)

    Provides tutorials and links to working sample code to demonstrate various features and functionality related to the _IntelliJ Platform_.
    
    提供工作示例代码的教程和链接，以演示与 _IntelliJ Platform_ 相关的各种功能和功能。

* [**Appendix II - Resources**](/resources.md)

    Links to useful resources, such as the IntelliJ Community Edition source code, the Plugin Development forum and the Plugin Developers Gitter room.

    链接到有用的资源，例如 IntelliJ Community Edition 源代码，Plugin Development 论坛和 Plugin Developers Gitter room。

