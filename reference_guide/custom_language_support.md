---
title: Custom Language Support
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

*IntelliJ Platform* is a powerful platform for building development tools targeting *any* language.
Most of the IDE features consist of language-independent (provided by the platform) and language-specific parts.
Supporting a particular feature for a new language can be achieved with a small amount of effort: 
a plugin must implement only the language-specific part.

This part of the documentation explains the main concepts of the *Language API* and guides you through the sequence of steps that are usually required to develop a custom language plugin.
You can obtain additional information about the *Language API* from the JavaDoc comments for the *Language API* classes and from the source code of the Properties language support, which is part of the
[IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community)
source code.


If you prefer a full example to the detailed descriptions offered in this section, please check out a step-by-step tutorial on how to create custom language support for _Simple Language_:
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md). Corresponding steps from the tutorial are linked under "Examples" section on each page of this reference.

The webinar [How We Built Comma, the Raku IDE, on the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/01/webinar-recording-how-we-built-comma-the-raku-ide-on-the-intellij-platform/) offers an excellent introduction as well.

Providing custom language support includes the following major steps:

* [Registering File Type](/reference_guide/custom_language_support/registering_file_type.md)
* [Implementing Lexer](/reference_guide/custom_language_support/implementing_lexer.md)
* [Implementing Parser and PSI](/reference_guide/custom_language_support/implementing_parser_and_psi.md)
* [Syntax Highlighting and Error Highlighting](/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.md)
* [References and Resolve](/reference_guide/custom_language_support/references_and_resolve.md)
* [Code Completion](/reference_guide/custom_language_support/code_completion.md)
* [Find Usages](/reference_guide/custom_language_support/find_usages.md)
* [Rename Refactoring](/reference_guide/custom_language_support/rename_refactoring.md)
* [Safe Delete Refactoring](/reference_guide/custom_language_support/safe_delete_refactoring.md)
* [Code Formatter](/reference_guide/custom_language_support/code_formatting.md)
* [Code Inspections and Intentions](/reference_guide/custom_language_support/code_inspections_and_intentions.md)
* [Structure View](/reference_guide/custom_language_support/structure_view.md)
* [Surround With](/reference_guide/custom_language_support/surround_with.md)
* [Go to Class and Go to Symbol](/reference_guide/custom_language_support/go_to_class_and_go_to_symbol.md)
* [Documentation](/reference_guide/custom_language_support/documentation.md)
* [Additional Minor Features](/reference_guide/custom_language_support/additional_minor_features.md)

