<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Custom Language Support Tutorial

<link-summary>Tutorial for creating a .properties-like custom language.</link-summary>

In this tutorial, we will add support for a [.properties](https://en.wikipedia.org/wiki/.properties) language and its usages within Java code.

> IntelliJ Platform support for custom languages is discussed in more depth in the [Custom Language Support](custom_language_support.md) section.
> Corresponding parts are linked under **Reference** on top of each page in this tutorial.
>
> All relevant code added or changed on a page is linked in **Code**.
>
> The accompanying [](writing_tests_for_plugins.md) tutorial covers testing the functionality; corresponding parts are linked under **Testing**.
>
{title="Navigating this tutorial"}

> The complete and fully working example plugin used in this tutorial is the [`simple_language_plugin`](%gh-sdk-samples-master%/simple_language_plugin) code sample.
>
> See [](code_samples.md) on how to build and run it.
>
{title="Accessing the code" style="note"}

This is a step-by-step tutorial, and it requires completing each step, in order:

* [](prerequisites.md)
* [](folding_builder.md)
* [](language_and_filetype.md)
* [](go_to_symbol_contributor.md)
* [](grammar_and_parser.md)
* [](structure_view_factory.md)
* [](lexer_and_parser_definition.md)
* [](structure_aware_navbar.md)
* [](syntax_highlighter_and_color_settings_page.md)
* [](formatter.md)
* [](psi_helper_and_utilities.md)
* [](code_style_settings.md)
* [](annotator.md)
* [](commenter.md)
* [](line_marker_provider.md)
* [](quick_fix.md)
* [](completion_contributor.md)
* [](documentation_provider.md)
* [](reference_contributor.md)
* [](spell_checking_strategy.md)
* [](find_usages_provider.md)
* {columns="2"}

<include from="snippets.topic" element-id="missingContent"/>

<seealso style="cards">
    <category ref="related">
        <a href="custom_language_support.md"/>
        <a href="writing_tests_for_plugins.md"/>
    </category>
</seealso>
