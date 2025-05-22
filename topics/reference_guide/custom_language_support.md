<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Custom Language Support

<link-summary>Supporting custom languages overview.</link-summary>

IntelliJ Platform is a powerful platform for building development tools targeting *any* language.
Most of the IDE features consist of language-independent (provided by the platform) and language-specific parts.
Supporting a particular feature for a new language can be achieved with a small amount of effort:
a plugin must implement only the language-specific part.

## Reference

This part of the documentation explains the main concepts of the *Language API* and guides you through the sequence of steps that are usually required to develop a custom language plugin.
You can obtain additional information about the *Language API* from the Javadoc comments for the *Language API* classes and from the Properties language support source code, which is part of the [IntelliJ IDEA Community Edition](%gh-ic%/README.md) source code.

## Tutorial

If you prefer a full example to the detailed descriptions offered in this section, please check out a step-by-step tutorial on how to create custom language support for _Simple Language_:
[](custom_language_support_tutorial.md).
Corresponding steps from the tutorial are linked under the "**Example**" section on each page of this reference.

The webinar [How We Built Comma, the Raku IDE, on the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/01/webinar-recording-how-we-built-comma-the-raku-ide-on-the-intellij-platform/) offers an excellent introduction as well.

<video src="https://www.youtube.com/watch?v=zDP9uUMYrvs" title="How We Built Comma, the Raku IDE, on the IntelliJ Platform"/>

## Topics

### Initial Setup

* [](registering_file_type.md)
* [](implementing_parser_and_psi.md)
* [](implementing_lexer.md)
* [](syntax_highlighting_and_error_highlighting.md)
* {columns="2"}

### Resolving and Completion

* [](references_and_resolve.md)
    * [](symbols.md)
    * [](declarations_and_references.md)
    * [](websymbols.md)
* [](navigation.md)
* [](code_completion.md)
* {columns="2"}

### Refactoring

* [](find_usages.md)
* [](safe_delete_refactoring.md)
* [](rename_refactoring.md)
* {columns="2"}

### Editor and IDE Features

* [](code_formatting.md)
* [](go_to_class_and_go_to_symbol.md)
* [](code_inspections_and_intentions.md)
* [](documentation.md)
* [](structure_view.md)
* [](parameter_info.md)
* [](navbar.md)
* [](inlay_hints.md)
* Code Hierarchy
* [](spell_checking.md)
* [](surround_with.md)
* [](additional_minor_features.md)
* {columns="2"}

See [](language_server_protocol.md) for supporting language servers.

<include from="snippets.topic" element-id="missingContent"/>
