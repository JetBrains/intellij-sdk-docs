---
title: Custom Language Support
---


*IntelliJ Platform* is a powerful platform for building development tools targeting *any* language.
Most of IDE features consist of language-independent and language-specific parts, and you can support a particular feature for your language with a small amount of effort:
you just need to implement the language-specific part, and the language-independent part is provided for you by the platform.

This part of the documentation will explain the main concepts of the *Language API* and will guide you through the sequence of steps which are usually required to develop a custom language plugin.
You can obtain additional information about the *Language API* from the JavaDoc comments for the *Language API* classes and from the source code of the Properties language support, which is part of the
[IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community)
source code.


If you prefer a full example to the detailed description offered on this page, please check out a step-by-step tutorial how to define custom language support on example of ".properties" files:
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.html)

Providing custom language support includes the following major steps:

* [Registering File Type](reference_guide/custom_language_support/registering_file_type.html)
* [Implementing Lexer](reference_guide/custom_language_support/implementing_lexer.html)
* [Implementing Parser and PSI](reference_guide/custom_language_support/implementing_parser_and_psi.html)
* [Syntax Highlighting and Error Highlighting](reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.html)
* [References and Resolve](reference_guide/custom_language_support/references_and_resolve.html)
* [Code Completion](reference_guide/custom_language_support/code_completion.html)
* [Find Usages](reference_guide/custom_language_support/find_usages.html)
* [Rename Refactoring](reference_guide/custom_language_support/rename_refactoring.html)
* [Safe Delete Refactoring](reference_guide/custom_language_support/safe_delete_refactoring.html)
* [Code Formatter](reference_guide/custom_language_support/code_formatting.html)
* [Code Inspections and Intentions](reference_guide/custom_language_support/code_inspections_and_intentions.html)
* [Structure View](reference_guide/custom_language_support/structure_view.html)
* [Surround With](reference_guide/custom_language_support/surround_with.html)
* [Go to Class and Go to Symbol](reference_guide/custom_language_support/go_to_class_and_go_to_symbol.html)
* [Documentation](reference_guide/custom_language_support/documentation.html)
* [Additional Minor Features](reference_guide/custom_language_support/additional_minor_features.html)


Please ask questions or suggest missing topics in [plugin development forum](http://devnet.jetbrains.com/community/idea/open_api_and_plugin_development).
