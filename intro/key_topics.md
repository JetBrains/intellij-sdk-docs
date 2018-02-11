---
title: Key Topics
---

The _IntelliJ Platform_ is very large, and very capable, and its size and scope can initially be very daunting. This page is intended to list the key topics that a plugin author would be interested in, and provide quick links to the most common extension points.

_IntelliJ Platform_ 非常大，功能非常强大，它的大小和范围最初可能非常艰巨。 本页旨在列出插件作者感兴趣的关键主题，并提供到最常用的扩展点的快速链接。

## Essential concepts

- [Getting Started](/basics/getting_started.md) with plugins.
- [Testing plugins](/basics/testing_plugins.md).
- [Architectural overview] - _IntelliJ Platform_ 的不同层次的简要介绍.
- Component model - _IntelliJ Platform_ 是一个基于组件的应用程序，负责创建组件和注入依赖项。 理解这是建立插件所必需的。
- Extension points - 如何向扩展点注册组件，以及如何找到可用的扩展点。
- [Virtual files](/basics/architectural_overview/virtual_file.md) - 所有的文件访问都应该通过虚拟文件系统来抽象和缓存文件系统。 这意味着您可以使用本地文件系统上的文件，zip文件或版本控制的旧版本。
- [Extension points](/basics/plugin_structure/plugin_extensions_and_extension_points.md)

## Code model

The _IntelliJ Platform_'s code model is called the PSI - the Program Structure Index. The PSI parses code, builds indexes and creates a semantic model.

_IntelliJ Platform_ 的代码模型被称为 PSI - 程序结构索引。 PSI解析代码，建立索引并创建语义模型。

## Common extension points

The _IntelliJ Platform_ is extremely extensible, and most features and services can be extended. Some of the common extension points are:

_IntelliJ平台_ 是非常可扩展的，大多数功能和服务可以扩展。 一些常见的扩展点是：

* [Actions](/tutorials/action_system.md) - 菜单和工具栏项目
* [Code inspections](/tutorials/code_inspections.md) - 代码分析，查看语法树和语义模型，并在编辑器中突出显示问题.
* [Intentions](/tutorials/code_intentions.md) - 当文本插入符号位于特定位置时，在 <kbd>Alt</kbd>+<kbd>Enter</kbd> 菜单中可用的上下文特定操作。
* [Code completion](/reference_guide/custom_language_support/code_completion.md).


