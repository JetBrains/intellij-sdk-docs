<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# The IntelliJ Platform

<link-summary>Introduction to the IntelliJ Platform, plugins, and IDEs based on it.</link-summary>

The IntelliJ Platform is not a product in and of itself but provides a platform for building IDEs.
It is used to power JetBrains products such as [IntelliJ IDEA](https://www.jetbrains.com/idea/).
It is also Open Source and can be used by third parties to build IDEs, such as [Android Studio](https://developer.android.com/studio/index.html) from Google.

The IntelliJ Platform provides all the infrastructure that these IDEs need to provide rich language tooling support.
It is a component-driven, cross-platform JVM-based application host with a high-level user interface toolkit for creating [tool windows](tool_windows.md), tree views, and lists (supporting fast search) as well as popup menus and [dialogs](dialog_wrapper.md).

The IntelliJ Platform has a full-text [editor](editors.md) with abstract implementations of [syntax highlighting](analyzing.md), [code folding](folding_builder.md), [code completion](code_completion.md), and other rich text [editing features](editing.md).
An image editor is also included.

Furthermore, it includes open APIs to build standard IDE functionality, such as a [project model](project.md) and a [build system](external_system_integration.md).
It also provides an infrastructure for a rich debugging experience, with language-agnostic advanced breakpoint support, call stacks, watch windows, and expression evaluation.

But the IntelliJ Platform's real power comes from the Program Structure Interface ([PSI](psi.md)).
It is a set of functionalities used to parse files, build rich syntactic and semantic models of the code, and build [indexes](indexing_and_psi_stubs.md) from this data.
PSI powers a lot of functionalities, from quick navigating to [files](psi_files.md), types, and [symbols](symbols.md), to the contents of [code completion](code_completion.md) popups and [find usages](find_usages.md), [code inspections](code_inspections.md), and code rewriting, for quick fixes or [refactorings](rename_refactoring.md), as well as many other features.

The IntelliJ Platform includes [parsers](implementing_parser_and_psi.md) and a PSI model for many languages, and its extensible nature means that it is possible to [add support for other languages](custom_language_support.md).

## Plugins

Products built on the IntelliJ Platform are extensible applications, with the platform being responsible for creating [](plugin_extensions.md).
The IntelliJ Platform fully supports [plugins](developing_plugins.md), and JetBrains hosts the [JetBrains Marketplace](https://plugins.jetbrains.com), which can be used to distribute plugins that support one or more of the products.
It is also possible to distribute plugins using a [](custom_plugin_repository.md).

Plugins can extend the platform in many ways, from adding a simple menu item to adding support for a complete language, build system, and debugger.
Many of the existing IntelliJ Platform features are implemented as plugins that can be included or excluded depending on the needs of the end product.
See the [](plugins_quick_start.md) for more details.

<snippet id="pluginAlternatives">

> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{style="note" title="Plugin Alternatives"}

</snippet>

## Open Source

The IntelliJ Platform is Open Source, under the [Apache License](%gh-ic%/LICENSE.txt), and [hosted on GitHub](https://github.com/JetBrains/intellij-community).

While this guide refers to the IntelliJ Platform as a separate entity, there is no "IntelliJ Platform" GitHub repository.
Instead, the platform is considered to be an almost complete overlap with the [IntelliJ IDEA Community Edition](idea.md), which is a free and Open Source version of IntelliJ IDEA Ultimate (the GitHub repository linked above is the [JetBrains/intellij-community](%gh-ic%/README.md) repository).
Please note: starting with the 2021.1 release, some plugins bundled with IntelliJ IDEA Community Edition are not open-source.

The version of the IntelliJ Platform is defined by the version of the corresponding IntelliJ IDEA Community Edition release.
For example, to build a plugin against IntelliJ IDEA (2019.1.1), build #191.6707.61 means specifying the same build number tag to get the correct IntelliJ Platform files from the `intellij-community` repository.
See the [](build_number_ranges.md) page for more information about build numbers corresponding to version numbering.

Typically, an IDE that is based on the IntelliJ Platform will include the `intellij-community` repository as a Git submodule and provide configuration to describe which plugins from the `intellij-community`, and which custom plugins will make up the product.

## IDEs Based on the IntelliJ Platform

The IntelliJ Platform underlies many JetBrains IDEs.
[IntelliJ IDEA Ultimate](idea_ultimate.md) is a superset of the IntelliJ IDEA Community Edition but includes closed-source plugins ([see this feature comparison](https://www.jetbrains.com/idea/features/editions_comparison_matrix.html)).
Similarly, other products such as [WebStorm](webstorm.md) and [DataGrip](data_grip.md) are based on the IntelliJ IDEA Community Edition, but with a different set of plugins included and excluding other default plugins.
This allows plugins to target multiple products, as each product will include base functionality and a selection of plugins from the IntelliJ IDEA Community Edition repository.

The following IDEs are based on the IntelliJ Platform:

* [JetBrains](https://www.jetbrains.com) IDEs:
    * [AppCode](app_code.md)
    * [CLion](clion.md)
    * [DataGrip](data_grip.md)
    * [DataSpell](https://www.jetbrains.com/dataspell/)
    * [GoLand](goland.md)
    * [IntelliJ IDEA](idea.md)
    * [MPS](https://www.jetbrains.com/mps/)
    * [PhpStorm](phpstorm.md)
    * [PyCharm](pycharm.md)
    * [Rider](#rider)
    * [RubyMine](rubymine.md)
    * [RustRover](https://www.jetbrains.com/rust/)
    * [WebStorm](webstorm.md)
* [Android Studio](android_studio.md) IDE from Google
* [Jmix Studio](https://www.jmix.io/tools/)

### Rider

JetBrains [Rider](rider.md) uses the IntelliJ Platform differently than other IntelliJ-based IDEs.
It uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE, with the standard IntelliJ editors, tool windows, debugging experience, etc.
It also integrates into the standard Find Usages and Search Everywhere UI and uses code completion, syntax highlighting, and so on.

However, Rider doesn't create a full [PSI](psi.md) (syntactic and semantic) model for C# files.
Instead, it reuses [ReSharper](https://www.jetbrains.com/resharper/) to provide language functionality.
All the C# PSI models, inspections, code rewritings, such as quick fixes, and refactorings are run out of the process, in a command-line version of ReSharper.
This means that creating a plugin for Rider involves two parts: a plugin that lives in the IntelliJ "front end" to show user interface, and a plugin that lives in the ReSharper "back end" to analyze and work with the C# PSI.

Fortunately, many plugins can simply work with the ReSharper backend.
Rider takes care of displaying the results of inspections and code completion, and many plugins can be implemented without requiring an IntelliJ UI component.
