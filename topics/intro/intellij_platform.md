[//]: # (title: The IntelliJ Platform)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Introduction to the IntelliJ Platform, plugins, and IDEs based on it.</excerpt>

The IntelliJ Platform is not a product in and of itself but provides a platform for building IDEs.
It is used to power JetBrains products such as [IntelliJ IDEA](https://www.jetbrains.com/idea/).
It is also Open Source and can be used by third parties to build IDEs, such as [Android Studio](https://developer.android.com/studio/index.html) from Google.

The IntelliJ Platform provides all the infrastructure that these IDEs need to provide rich language tooling support.
It is a component-driven, cross-platform JVM based application host with a high-level user interface toolkit for creating tool windows, tree views, and lists (supporting fast search) as well as popup menus and dialogs.

The IntelliJ Platform has a full-text editor with abstract implementations of syntax highlighting, code folding, code completion, and other rich text editing features.
An image editor is also included.

Furthermore, it includes open APIs to build standard IDE functionality, such as a project model and a build system.
It also provides an infrastructure for a rich debugging experience, with language-agnostic advanced breakpoint support, call stacks, watch windows, and expression evaluation.

But the IntelliJ Platform's real power comes from the Program Structure Interface (PSI).
It is a set of functionalities used to parse files, build rich syntactic and semantic models of the code, and build indexes from this data.
PSI powers a lot of functionalities, from quick navigating to files, types, and symbols, to the contents of code completion windows and find usages, code inspections, and code rewriting, for quick fixes or refactorings, as well as many other features.

The IntelliJ Platform includes parsers and a PSI model for many languages, and its extensible nature means that it is possible to add support for other languages.

## Plugins

Products built on the IntelliJ Platform are extensible applications, with the platform being responsible for creating components and the injection of dependencies into classes.
The IntelliJ Platform fully supports plugins, and JetBrains hosts the [JetBrains Marketplace](https://plugins.jetbrains.com) which can be used to distribute plugins that support one or more of the products.
It is also possible to distribute plugins using [Custom Plugin Repositories](update_plugins_format.md).

Plugins can extend the platform in many ways, from adding a simple menu item to adding support for a complete language, build system, and debugger.
Many of the existing IntelliJ Platform features are implemented as plugins that can be included or excluded depending on the needs of the end product.
See the [](basics.md) for more details.

The IntelliJ Platform is a JVM application, implemented mostly in Java and [Kotlin](https://kotlinlang.org).
Developing plugins for products based on the IntelliJ Platform requires experience with at least one of these languages, its standard and third party libraries, and associated tooling.
At this time, it's not possible to extend the IntelliJ Platform in non-JVM languages.

> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{type="tip"}

## Open Source

The IntelliJ Platform is Open Source, under the [Apache License](upsource:///LICENSE.txt), and [hosted on GitHub](https://github.com/JetBrains/intellij-community).

While this guide refers to the IntelliJ Platform as a separate entity, there is no "IntelliJ Platform" GitHub repository.
Instead, the platform is considered to be an almost complete overlap with the IntelliJ IDEA Community Edition, which is a free and Open Source version of IntelliJ IDEA Ultimate (the GitHub repository linked above is the [JetBrains/intellij-community](https://github.com/JetBrains/intellij-community) repository).
Please note: starting with the 2021.1 release, some plugins bundled with IntelliJ IDEA Community Edition are not open-source.

The version of the IntelliJ Platform is defined by the version of the corresponding IntelliJ IDEA Community Edition release.
For example, to build a plugin against IntelliJ IDEA (2019.1.1), build #191.6707.61 means specifying the same build number tag to get the correct Intellij Platform files from the `intellij-community` repository.
See the [](build_number_ranges.md) page for more information about build numbers corresponding to version numbering.

Typically, an IDE that is based on the IntelliJ Platform will include the `intellij-community` repository as a Git submodule and provide configuration to describe which plugins from the `intellij-community`, and which custom plugins will make up the product.
This is how the IDEA Ultimate team works, and they contribute code to both the custom plugins and the IntelliJ Platform itself.

## IDEs Based on the IntelliJ Platform

The IntelliJ Platform underlies many JetBrains IDEs.
IntelliJ IDEA Ultimate is a superset of the IntelliJ IDEA Community Edition but includes closed source plugins ([see this feature comparison](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)).
Similarly, other products such as WebStorm and DataGrip are based on the IntelliJ IDEA Community Edition, but with a different set of plugins included and excluding other default plugins.
This allows plugins to target multiple products, as each product will include base functionality and a selection of plugins from the IntelliJ IDEA Community Edition repository.

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

The following IDEs are based on the IntelliJ Platform:
* JetBrains IDEs:
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
* [Android Studio](https://developer.android.com/studio/index.html) IDE from Google
* [Comma](https://commaide.com/) IDE for Raku (formerly known as Perl 6)
* [Jmix Studio](https://www.jmix.io/tools/)

See *Part VIII — Product Specific* for IDE specific details.

### Rider

JetBrains [Rider](https://www.jetbrains.com/rider/) uses the IntelliJ Platform differently than other IntelliJ based IDEs.
It uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE, with the standard IntelliJ editors, tool windows, debugging experience, etc.
It also integrates into the standard Find Usages and Search Everywhere UI and uses code completion, syntax highlighting, and so on.

However, Rider doesn't create a full [PSI](psi.md) (syntactic and semantic) model for C# files.
Instead, it reuses [ReSharper](https://www.jetbrains.com/resharper/) to provide language functionality.
All of the C# PSI model, inspections, code rewritings, such as quick fixes and refactorings are run out of the process, in a command-line version of ReSharper.
This means that creating a plugin for Rider involves two parts - a plugin that lives in the IntelliJ "front end" to show user interface, and a plugin that lives in the ReSharper "back end" to analyze and work with the C# PSI.

Fortunately, many plugins can simply work with the ReSharper backend.
The Rider takes care of displaying the results of inspections and code completion, and many plugins can be implemented without requiring an IntelliJ UI component.
More details can be found in [](rider.md).
