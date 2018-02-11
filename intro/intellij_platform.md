---
title: What is the IntelliJ Platform?
---

The _IntelliJ Platform_ is not a product in and of itself, but provides a platform for building IDEs. It is used to power JetBrains products such as [IntelliJ IDEA](https://www.jetbrains.com/idea/), [WebStorm](https://www.jetbrains.com/webstorm/), [RubyMine](https://www.jetbrains.com/ruby/), [DataGrip](https://www.jetbrains.com/datagrip/) and [Rider](https://www.jetbrains.com/rider/). It is also Open Source, and can be used by third parties to build IDEs, such as [Android Studio](https://developer.android.com/studio/index.html) from Google.

_IntelliJ Platform_ 不是产品本身，而是提供了一个平台构建 IDEs。平台能更好的构建诸如 [IntelliJ IDEA](https://www.jetbrains.com/idea/), [WebStorm](https://www.jetbrains.com/webstorm/), [RubyMine](https://www.jetbrains.com/ruby/), [DataGrip](https://www.jetbrains.com/datagrip/) 和 [Rider](https://www.jetbrains.com/rider/)。它也是开源的，并且可以通过第三方应用构建 IDEs，如谷歌的 [Android Studio](https://developer.android.com/studio/index.html)。


The _IntelliJ Platform_ provides all of the infrastructure that these IDEs need to provide rich language tooling support. It provides a component driven, cross platform JVM based application host with a high level user interface toolkit for creating tool windows, tree views and lists (supporting fast search) as well as popup menus and dialogs.

_IntelliJ Platform_ 为那些需要提供丰富的语言工具的 IDEs 提供了所有的基础支持。它提供了一个组件驱动的、跨平台的基于JVM的应用程序宿主，它有一个高级的用户界面工具包，用于创建工具窗口、树视图和列表（支持快速搜索）以及弹出菜单和对话框。

It also includes an image editor as well as a full text editor, and provides abstract implementations of syntax highlighting, code folding, code completion, and other rich text editing features.

它还包括一个图像编辑器和一个完整的文本编辑器，并提供语法突出显示、代码折叠、代码完成和其他丰富的文本编辑功能的抽象实现。

Furthermore, it includes pluggable APIs to build common IDE functionality, such as a project model and a build system. It also provides infrastructure for a very rich debugging experience, with language agnostic advanced breakpoint support, call stacks, watch windows and expression evaluation.

此外，它还包括可插入的 APIs 来构建公共 IDE 功能，如项目模型和构建系统。它还提供了一个非常丰富的调试经验的基础设施，具有不依赖语言的高级断点支持、调用堆栈、监视窗口和表达式求值。

But the _IntelliJ Platform_'s real power comes from the Program Structure Index (PSI). This is a set of functionality that can be used to parse files and build rich syntactic and semantic models of the code, and to build indexes from this data. This powers a lot of functionality, from quick navigating to files, types and symbols, to the contents of code completion windows and find usages, code inspections and code rewriting, for quick fixes or refactorings, as well as many other features.

但 _IntelliJ Platform_ 真正的力量来自于 Program Structure Index (PSI)。这是一组可用于解析文件和构建代码的丰富语法和语义模型的功能，并从这些数据构建创建索引。有一些很强大的功能，快速浏览文件，类型和符号，在代码完成窗口的内容和发现的使用，代码检查和代码重写，快速修复或重构，以及许多其他功能。

The _IntelliJ Platform_ includes parsers and a PSI model for a number of languages, and its composable nature means that it is possible to add support for other languages.

_IntelliJ Platform_ 包括分析器和一个语言 PSI 模型，这些可组合性意味着它可以添加对其他语言的支持。

## Plugins

Products built on the _IntelliJ Platform_ are composable applications, with the platform being responsible for the creation of components, and the injection of dependencies into classes. The _IntelliJ Platform_ fully supports plugins, and JetBrains hosts a [plugin repository](https://plugins.jetbrains.com) that can be used to distribute plugins that support one or more of the products. It is also possible to host your own repositories, and distribute plugins separately.

构建在_IntelliJ平台上的产品是可组合的应用程序，该平台负责创建组件，并将依赖注入到类中。 _IntelliJ Platform_ 完全支持插件，JetBrains托管一个 [plugin repository](https://plugins.jetbrains.com)，可用于分发支持一个或多个产品的插件。 也可以托管自己的仓库，并分别分发插件。

Plugins can extend the platform in lots of ways, from adding a simple menu item to adding support for a complete language, build system and debugger. A lot of the existing functionality in the _IntelliJ Platform_ is written as plugins that can be included or excluded depending on the needs of the end product. See the section on [Plugins](/basics.md) for more details.

插件可以通过多种方式扩展平台，从添加简单的菜单项到添加对完整语言、构建系统和调试器的支持。_IntelliJ Platform_ 中的许多现有功能都是作为插件编写的，可以根据最终产品的需要包含或排除。有关详细信息，请参见 [Plugins](/basics.md) 一节。

The _IntelliJ Platform_ is a JVM application, written mostly in Java and Kotlin. You should be familiar with these languages, and associated tooling, in order to write plugins for products based on the _IntelliJ Platform_. At this time, it's not possible to extend the _IntelliJ Platform_ in non-JVM languages.

_IntelliJ Platform_ 是一个JVM应用程序，主要用Java和Kotlin编写。 您应该熟悉这些语言和相关的工具，才能为基于_IntelliJ Platform_ 的产品编写插件。 目前，无法在非JVM语言中扩展_IntelliJ Platform_。

## Open Source

The _IntelliJ Platform_ is Open Source, under the [Apache license](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt), and [hosted on GitHub](https://github.com/JetBrains/intellij-community).

_IntelliJ Platform_ 是开源的， [Apache license](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt), [hosted on GitHub](https://github.com/JetBrains/intellij-community).

While this guide refers to the _IntelliJ Platform_ as a separate entity, there is no "IntelliJ Platform" GitHub repo. Instead, the platform is considered to be an almost complete overlap with the IntelliJ IDEA Community Edition, which is a free and Open Source version of IntelliJ IDEA Ultimate (the GitHub repo linked above is the [JetBrains/intellij-community](https://github.com/JetBrains/intellij-community) repo).

虽然本指南将 _IntelliJ Platform_ 作为单独的实体引用，但没有 “IntelliJ Platform” GitHub repo。 相反，该平台被认为与 IntelliJ IDEA Community Edition 几乎完全重叠，IntelliJ IDEA Community Edition 是 IntelliJ IDEA Ultimate 的免费开源版本（以上链接的GitHub repo是 [JetBrains/intellij-community](https://github.com/JetBrains/intellij-community)）。

IntelliJ IDEA Ultimate is a superset of the IntelliJ IDEA Community Edition. It is based on the community edition, but includes closed source plugins ([see this feature comparison](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)). Similarly, other products such as WebStorm and DataGrip are based on the IntelliJ IDEA Community Edition, but with a different set of plugins included and excluding other default plugins.

IntelliJ IDEA Ultimate 是IntelliJ IDEA Community Edition 的超集。 它基于社区版，但包含封闭的源代码插件（[请参阅此功能比较](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)）。 同样，其他产品（如 WebStorm 和 DataGrip ）基于IntelliJ IDEA Community Edition，但包含一组不同的插件，不包括其他默认插件。

This allows plugins to target multiple products, as each product will include base functionality and a selection of plugins from the IntelliJ IDEA Community Edition repo. This is what we call the _IntelliJ Platform_.

这允许插件将多个产品作为目标，因为每个产品都将包含基本功能和IntelliJ IDEA Community Edition 插件的选择。 这就是我们所说的 _IntelliJ Platform_。

Typically, an IDE that is based on the _IntelliJ Platform_ will include the `intellij-community` repo as a Git submodule and provide configuration to describe which plugins from the `intellij-community`, and which custom plugins will make up the product. This is how the IDEA Ultimate team work, and they contribute code to both the custom plugins and the _IntelliJ Platform_ itself.

通常情况下，一个基于 _IntelliJ Platform_ 的 IDE 将把 intellij-community repo 作为一个 Git 子模块，并提供配置来描述来自 intellij-community 的哪些插件以及哪些自定义插件将组成产品。 这就是 IDEA Ultimate team 的工作，他们为自定义插件和 _IntelliJ Platform_ 本身贡献代码。

Of course, because the _IntelliJ Platform_ is open source, we also accept [pull requests](https://github.com/JetBrains/intellij-community/pulls) to the platform itself, rather than just opening the source for view. Issue tracking is managed with [YouTrack (using the IDEA project)](https://youtrack.jetbrains.com/issues/IDEA), and if you wish to contribute to the platform, it is usually a good idea to open an issue describing the changes you wish to make before making the changes - this allows the team chance to give feedback and advice. More details can be found in the section on [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

当然，因为 _IntelliJ Platform_ 是开源的，我们也接受 [pull requests](https://github.com/JetBrains/intellij-community/pulls)，而不是仅仅为了查阅。 问题跟踪通过 [YouTrack (using the IDEA project)](https://youtrack.jetbrains.com/issues/IDEA) 进行管理，如果您希望为平台作出贡献，通常是一个好主意，可以解决问题 描述您在进行更改之前所做的更改 - 这可让团队有机会提供反馈和建议。 更多细节可以在 [Contributing to the IntelliJ Platform](/basics/platform_contributions.md) 一节中找到。

## Rider

[Rider](https://www.jetbrains.com/rider/) uses the _IntelliJ Platform_ differently to other IntelliJ based IDEs. It uses the _IntelliJ Platform_ to provide the user interface for a C# and .NET IDE, with the standard IntelliJ editors, toolwindows, debugging experience and so on. It also integrates into the standard Find Usages and Search Everywhere UI, and makes use of code completion, syntax highlighting and so on.

[Rider](https://www.jetbrains.com/rider/) 与其他基于 _IntelliJ Platform_ 开发的 IDEs 不同。 它使用_IntelliJ Platform_ 为 C＃ 和 .NET IDE 提供用户界面，并提供标准的 IntelliJ 编辑器，工具窗口，调试体验等。 它还集成到标准的查找用法和搜索到处的用户界面，并使用代码完成，语法高亮等。

However, it doesn't create a full PSI (syntactic and semantic) model for C# files. Instead, it reuses [ReSharper](https://www.jetbrains.com/resharper/) to provide language functionality. All of the C# PSI model, and all inspections and code rewriting, such as quick fixes and refactorings are run out of process, in a command line version of ReSharper. This means that creating a plugin for Rider involves two parts - a plugin that lives in the IntelliJ "front end" to show user interface, and a plugin that lives in the ReSharper "back end" to analyse and work with the C# PSI.

但是，它不会为 C＃ 文件创建完整的 PSI（语法和语义）模型。 相反，它重用 [ReSharper](https://www.jetbrains.com/resharper/)）来提供语言功能。 所有的 C＃ PSI 模型以及所有的检查和代码重写，例如快速修复和重构都是在 ReSharper 的命令行版本中运行的。 这意味着为 Rider 创建一个插件涉及两个部分
一个在IntelliJ “前端” 以显示用户界面的插件，以及一个在 ReSharper “后端”的插件来分析和使用C＃PSI。

Fortunately, many plugins can simply work with the ReSharper backend - Rider takes care of displaying the results of inspections and code completion, and many plugins can be written that don't require an IntelliJ UI component. More details can be found in the Product Specific section.

幸运的是，许多插件可以简单地使用ReSharper后端 - Rider负责显示检查和代码完成的结果，并且可以编写许多不需要IntelliJ UI组件的插件。 更多细节可以在产品特定部分找到。

