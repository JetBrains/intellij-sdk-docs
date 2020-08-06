---
title: What is the IntelliJ Platform?
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The _IntelliJ Platform_ is not a product in and of itself but provides a platform for building IDEs. It is used to power JetBrains products such as [IntelliJ IDEA](https://www.jetbrains.com/idea/). It is also Open Source and can be used by third parties to build IDEs, such as [Android Studio](https://developer.android.com/studio/index.html) from Google.

The IntelliJ Platform provides all of the infrastructure that these IDEs need to provide rich language tooling support. It provides a component driven, cross-platform JVM based application host with a high-level user interface toolkit for creating tool windows, tree views and lists (supporting fast search) as well as popup menus and dialogs.

It also includes an image editor as well as a full text editor, and provides abstract implementations of syntax highlighting, code folding, code completion, and other rich text editing features.

Furthermore, it includes open APIs to build common IDE functionality, such as a project model and a build system. It also provides infrastructure for a very rich debugging experience, with language agnostic advanced breakpoint support, call stacks, watch windows, and expression evaluation.

But the IntelliJ Platform's real power comes from the Program Structure Interface (PSI). This is a set of functionality that can be used to parse files and build rich syntactic and semantic models of the code, and to build indexes from this data. This powers a lot of functionality, from quick navigating to files, types and symbols, to the contents of code completion windows and find usages, code inspections and code rewriting, for quick fixes or refactorings, as well as many other features.

The IntelliJ Platform includes parsers and a PSI model for a number of languages, and its extensible nature means that it is possible to add support for other languages.


## Plugins

Products built on the IntelliJ Platform are extensible applications, with the platform being responsible for the creation of components, and the injection of dependencies into classes. The IntelliJ Platform fully supports plugins, and JetBrains hosts [JetBrains Plugin Repository](https://plugins.jetbrains.com) that can be used to distribute plugins that support one or more of the products. It is also possible to host your own repositories, and distribute plugins separately.

Plugins can extend the platform in lots of ways, from adding a simple menu item to adding support for a complete language, build system and debugger. A lot of the existing functionality in the IntelliJ Platform is written as plugins that can be included or excluded depending on the needs of the end product. See the [Quick Start Guide](/basics/basics.md) for more details.

The IntelliJ Platform is a JVM application, written mostly in Java and Kotlin. You should be experienced with these languages, large libraries written in them, their associated tooling, and large open source projects to write plugins for products based on the IntelliJ Platform. At this time, it's not possible to extend the IntelliJ Platform in non-JVM languages.

## Open Source

The IntelliJ Platform is Open Source, under the [Apache license](upsource:///LICENSE.txt), and [hosted on GitHub](https://github.com/JetBrains/intellij-community).

While this guide refers to the IntelliJ Platform as a separate entity, there is no "IntelliJ Platform" GitHub repo. Instead, the platform is considered to be an almost complete overlap with the IntelliJ IDEA Community Edition, which is a free and Open Source version of IntelliJ IDEA Ultimate (the GitHub repo linked above is the [JetBrains/intellij-community](https://github.com/JetBrains/intellij-community) repo).

The version of the IntelliJ Platform is defined by the version of the corresponding release of IntelliJ IDEA Community Edition. 
For example, to build a plugin against IntelliJ IDEA (2019.1.1,) build #191.6707.61, means specifying the same build number tag to get the correct Intellij Platform files from the `intellij-community` repo. 
See the [build number ranges](/basics/getting_started/build_number_ranges.md) page for more information about build numbers corresponding to version numbering.

Typically, an IDE that is based on the IntelliJ Platform will include the `intellij-community` repo as a Git submodule and provide configuration to describe which plugins from the `intellij-community`, and which custom plugins will make up the product. This is how the IDEA Ultimate team work, and they contribute code to both the custom plugins and the IntelliJ Platform itself.

### IDEs Based on the IntelliJ Platform
The IntelliJ Platform underlies many JetBrains IDEs. 
IntelliJ IDEA Ultimate is a superset of the IntelliJ IDEA Community Edition, but includes closed source plugins ([see this feature comparison](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)). Similarly, other products such as WebStorm and DataGrip are based on the IntelliJ IDEA Community Edition, but with a different set of plugins included and excluding other default plugins.
This allows plugins to target multiple products, as each product will include base functionality and a selection of plugins from the IntelliJ IDEA Community Edition repo.

The following IDEs are based on the IntelliJ Platform:
* JetBrains IDEs
  * [AppCode](https://www.jetbrains.com/objc/)
  * [CLion](https://www.jetbrains.com/clion/)
  * [DataGrip](https://www.jetbrains.com/datagrip/)
  * [GoLand](https://www.jetbrains.com/go/)
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  * [MPS](https://www.jetbrains.com/mps/)
  * [PhpStorm](https://www.jetbrains.com/phpstorm/)
  * [PyCharm](https://www.jetbrains.com/pycharm/)
  * [Rider](#rider)
  * [RubyMine](https://www.jetbrains.com/ruby/) 
  * [WebStorm](https://www.jetbrains.com/webstorm/) 
* [Android Studio](https://developer.android.com/studio/index.html) IDE from Google.
* [Comma](https://commaide.com/) IDE for Raku (formerly known as Perl 6)
* [CUBA Studio](https://www.cuba-platform.com/)

#### Rider
JetBrains [Rider](https://www.jetbrains.com/rider/) uses the IntelliJ Platform differently than other IntelliJ based IDEs. It uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE, with the standard IntelliJ editors, tool windows, debugging experience and so on. It also integrates into the standard Find Usages and Search Everywhere UI, and makes use of code completion, syntax highlighting, and so on.

However, Rider doesn't create a full PSI (syntactic and semantic) model for C# files. Instead, it reuses [ReSharper](https://www.jetbrains.com/resharper/) to provide language functionality. All of the C# PSI model and all inspections and code rewriting, such as quick fixes and refactorings are run out of process, in a command line version of ReSharper. This means that creating a plugin for Rider involves two parts - a plugin that lives in the IntelliJ "front end" to show user interface, and a plugin that lives in the ReSharper "back end" to analyze and work with the C# PSI.

Fortunately, many plugins can simply work with the ReSharper backend - Rider takes care of displaying the results of inspections and code completion, and many plugins can be written that don't require an IntelliJ UI component. More details can be found in the Product Specific section.
