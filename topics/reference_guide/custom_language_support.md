[//]: # (title: Custom Language Support)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Supporting custom languages overview.</excerpt>

IntelliJ Platform is a powerful platform for building development tools targeting *any* language.
Most of the IDE features consist of language-independent (provided by the platform) and language-specific parts.
Supporting a particular feature for a new language can be achieved with a small amount of effort:
a plugin must implement only the language-specific part.

## Reference

This part of the documentation explains the main concepts of the *Language API* and guides you through the sequence of steps that are usually required to develop a custom language plugin.
You can obtain additional information about the *Language API* from the JavaDoc comments for the *Language API* classes and from the Properties language support source code, which is part of the [IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community) source code.

## Tutorial

If you prefer a full example to the detailed descriptions offered in this section, please check out a step-by-step tutorial on how to create custom language support for _Simple Language_:
[Custom Language Support Tutorial](custom_language_support_tutorial.md).
Corresponding steps from the tutorial are linked under the "**Example**" section on each page of this reference.

The webinar [How We Built Comma, the Raku IDE, on the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/01/webinar-recording-how-we-built-comma-the-raku-ide-on-the-intellij-platform/) offers an excellent introduction as well.

<video href="zDP9uUMYrvs" title="How We Built Comma, the Raku IDE, on the IntelliJ Platform" width="300"/>

## Topics

### Initial Setup

* [](registering_file_type.md)
* [](implementing_lexer.md)
* [](implementing_parser_and_psi.md)
* [](syntax_highlighting_and_error_highlighting.md)

### Resolving and Completion

* [](references_and_resolve.md)
* [](symbols.md)
* [](declarations_and_references.md)
* [](navigation.md)
* [](code_completion.md)

### Refactoring

* [](find_usages.md)
* [](rename_refactoring.md)
* [](safe_delete_refactoring.md)

### Editor and IDE Features

* [](code_formatting.md)
* [](code_inspections_and_intentions.md)
* [](structure_view.md)
* [](navbar.md)
* Code Hierarchy
* [](surround_with.md)
* [](go_to_class_and_go_to_symbol.md)
* [](documentation.md)
* [](additional_minor_features.md)
* [](parameter_info.md)
* Parameter Hints

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
