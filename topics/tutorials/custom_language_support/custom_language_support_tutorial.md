[//]: # (title: Custom Language Support Tutorial)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Tutorial for creating a .properties-like custom language.</excerpt>

In this tutorial we will add support for a [.properties](https://en.wikipedia.org/wiki/.properties) language and its usages within Java code.

> IntelliJ Platform support for custom languages is discussed in more depth in the [Custom Language Support](custom_language_support.md) section.
> Corresponding parts are linked under **Reference** on top of each page in this tutorial.
>
{type="tip"}


> The complete and fully working example plugin used in this tutorial is the [`simple_language_plugin`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/simple_language_plugin) code sample.
> See [](code_samples.md) on how to build and run it.
>
{type="note"}

This a step-by-step tutorial, and it requires completing each step, in order:

* [](prerequisites.md)
* [](language_and_filetype.md)
* [](grammar_and_parser.md)
* [](lexer_and_parser_definition.md)
* [](syntax_highlighter_and_color_settings_page.md)
* [](psi_helper_and_utilities.md)
* [](annotator.md)
* [](line_marker_provider.md)
* [](completion_contributor.md)
* [](reference_contributor.md)
* [](find_usages_provider.md)
* [](folding_builder.md)
* [](go_to_symbol_contributor.md)
* [](structure_view_factory.md)
* [](formatter.md)
* [](code_style_settings.md)
* [](commenter.md)
* [](quick_fix.md)
* [](documentation_provider.md)

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
