---
title: What is the IntelliJ Platform?
---

The _IntelliJ Platform_ is not a product in and of itself, but provides a platform for building IDEs. It is used to power JetBrains products such as [IntelliJ IDEA](https://www.jetbrains.com/idea/), [WebStorm](https://www.jetbrains.com/webstorm/), [RubyMine](https://www.jetbrains.com/ruby/), [DataGrip](https://www.jetbrains.com/datagrip/) and [Rider](https://www.jetbrains.com/rider/). It is also Open Source, and can be used by third parties to build IDEs, such as [Android Studio](https://developer.android.com/studio/index.html) from Google.

The _IntelliJ Platform_ provides all of the infrastructure that these IDEs need to provide rich language tooling support. It provides a component driven, cross platform JVM based application host with a high level user interface toolkit for creating tool windows, tree views and lists (supporting fast search) as well as popup menus and dialogs.

It also includes an image editor as well as a full text editor, and provides abstract implementations of syntax highlighting, code folding, code completion, and other rich text editing features.

Furthermore, it includes pluggable APIs to build common IDE functionality, such as a project model and a build system. It also provides infrastructure for a very rich debugging experience, with language agnostic advanced breakpoint support, call stacks, watch windows and expression evaluation.

But the _IntelliJ Platform_'s real power comes from the Program Structure Index (PSI). This is a set of functionality that can be used to parse files and build rich syntactic and semantic models of the code, and to build indexes from this data. This powers a lot of functionality, from quick navigating to files, types and symbols, to the contents of code completion windows and find usages, code inspections and code rewriting, for quick fixes or refactorings, as well as many other features.

The _IntelliJ Platform_ includes parsers and a PSI model for a number of languages, and its composable nature means that it is possible to add support for other languages.

## Plugins

Products built on the _IntelliJ Platform_ are composable applications, with the platform being responsible for the creation of components, and the injection of dependencies into classes. The _IntelliJ Platform_ fully supports plugins, and JetBrains hosts a [plugin repository](https://plugins.jetbrains.com) that can be used to distribute plugins that support one or more of the products. It is also possible to host your own repositories, and distribute plugins separately.

Plugins can extend the platform in lots of ways, from adding a simple menu item to adding support for a complete language, build system and debugger. A lot of the existing functionality in the _IntelliJ Platform_ is written as plugins that can be included or excluded depending on the needs of the end product. See the section on [Plugins](/basics.md) for more details.

The _Intellij Platform_ is a JVM application, written mostly in Java and Kotlin. You should be familiar with these languages, and associated tooling, in order to write plugins for products based on the _IntelliJ Platform_. At this time, it's not possible to extend the _IntelliJ Platform_ in non-JVM languages.

## Open Source

The _IntelliJ Platform_ is Open Source, under the [Apache license](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt), and [hosted on GitHub](https://github.com/JetBrains/intellij-community).

While this guide refers to the _IntelliJ Platform_ as a separate entity, there is no "IntelliJ Platform" GitHub repo. Instead, the platform is considered to be an almost complete overlap with the IntelliJ IDEA Community Edition, which is a free and Open Source version of IntelliJ IDEA Ultimate (the GitHub repo linked above is the [JetBrains/intellij-community](https://github.com/JetBrains/intellij-community) repo).

IntelliJ IDEA Ultimate is a superset of the IntelliJ IDEA Community Edition. It is based on the community edition, but includes closed source plugins ([see this feature comparison](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)). Similarly, other products such as WebStorm and DataGrip are based on the IntelliJ IDEA Community Edition, but with a different set of plugins included and excluding other default plugins.

This allows plugins to target multiple products, as each product will include base functionality and a selection of plugins from the IntelliJ IDEA Community Edition repo. This is what we call the _IntelliJ Platform_.

Typically, an IDE that is based on the _IntelliJ Platform_ will include the `intellij-community` repo as a Git submodule and provide configuration to describe which plugins from the `intellij-community`, and which custom plugins will make up the product. This is how the IDEA Ultimate team work, and they contribute code to both the custom plugins and the _IntelliJ Platform_ itself.

Of course, because the _IntelliJ Platform_ is open source, we also accept [pull requests](https://github.com/JetBrains/intellij-community/pulls) to the platform itself, rather than just opening the source for view. Issue tracking is managed with [YouTrack (using the IDEA project)](https://youtrack.jetbrains.com/issues/IDEA), and if you wish to contribute to the platform, it is usually a good idea to open an issue describing the changes you wish to make before making the changes - this allows the team chance to give feedback and advice. More details can be found in the section on [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

## Rider

[Rider](https://www.jetbrains.com/rider/) uses the _IntelliJ Platform_ differently to other IntelliJ based IDEs. It uses the _IntelliJ Platform_ to provide the user interface for a C# and .NET IDE, with the standard IntelliJ editors, toolwindows, debugging experience and so on. It also integrates into the standard Find Usages and Search Everywhere UI, and makes use of code completion, syntax highlighting and so on.

However, it doesn't create a full PSI (syntactic and semantic) model for C# files. Instead, it reuses [ReSharper](https://www.jetbrains.com/resharper/) to provide language functionality. All of the C# PSI model, and all inspections and code rewriting, such as quick fixes and refactorings are run out of process, in a command line version of ReSharper. This means that creating a plugin for Rider involves two parts - a plugin that lives in the IntelliJ "front end" to show user interface, and a plugin that lives in the ReSharper "back end" to analyse and work with the C# PSI.

Fortunately, many plugins can simply work with the ReSharper backend - Rider takes care of displaying the results of inspections and code completion, and many plugins can be written that don't require an IntelliJ UI component. More details can be found in the Product Specific section.
