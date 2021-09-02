[//]: # (title: Custom Language Support)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

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
 
* [Registering File Type](registering_file_type.md)
* [Implementing Lexer](implementing_lexer.md)
* [Implementing Parser and PSI](implementing_parser_and_psi.md)
* [Syntax Highlighting and Error Highlighting](syntax_highlighting_and_error_highlighting.md)

### Resolving and Completion

* [References and Resolve](references_and_resolve.md)
* [Symbols](symbols.md)
* [Declarations and References](declarations_and_references.md)
* [Navigation](navigation.md)
* [Code Completion](code_completion.md)

### Refactoring

* [Find Usages](find_usages.md)
* [Rename Refactoring](rename_refactoring.md)
* [Safe Delete Refactoring](safe_delete_refactoring.md)
 
### Editor and IDE Features
 
* [Code Formatter](code_formatting.md)
* [Code Inspections and Intentions](code_inspections_and_intentions.md)
* [Structure View](structure_view.md)
* [Surround With](surround_with.md)
* [Go to Class and Go to Symbol](go_to_class_and_go_to_symbol.md)
* [Documentation](documentation.md)
* [Additional Minor Features](additional_minor_features.md)