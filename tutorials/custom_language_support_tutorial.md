---
title: Custom Language Support Tutorial
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

In this tutorial we will add support for a [.properties](https://en.wikipedia.org/wiki/.properties) language and its usages within Java code.

> **TIP** IntelliJ Platform support for custom languages is discussed in more depth in the [Custom Language Support](/reference_guide/custom_language_support.md) section. 
> Corresponding parts are linked under "Reference" on top of each page in this tutorial.
 
The example plugin used in this tutorial is the [`simple_language_plugin`](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/simple_language_plugin) code sample.
This a step-by-step tutorial, and it requires completing each step, in order: 

*  [1. Prerequisites](custom_language_support/prerequisites.md)
*  [2. Language and File Type](custom_language_support/language_and_filetype.md)
*  [3. Grammar and Parser](custom_language_support/grammar_and_parser.md)
*  [4. Lexer and Parser Definition](custom_language_support/lexer_and_parser_definition.md)
*  [5. Syntax Highlighter and Color Settings Page](custom_language_support/syntax_highlighter_and_color_settings_page.md)
*  [6. PSI Helpers and Utilities](custom_language_support/psi_helper_and_utilities.md)
*  [7. Annotator](custom_language_support/annotator.md)
*  [8. Line Marker Provider](custom_language_support/line_marker_provider.md)
*  [9. Completion Contributor](custom_language_support/completion_contributor.md)
*  [10. Reference Contributor](custom_language_support/reference_contributor.md)
*  [11. Find Usages Provider](custom_language_support/find_usages_provider.md)
*  [12. Folding Builder](custom_language_support/folding_builder.md)
*  [13. Go To Symbol Contributor](custom_language_support/go_to_symbol_contributor.md)
*  [14. Structure View Factory](custom_language_support/structure_view_factory.md)
*  [15. Formatter](custom_language_support/formatter.md)
*  [16. Code Style Settings](custom_language_support/code_style_settings.md)
*  [17. Commenter](custom_language_support/commenter.md)
*  [18. Quick Fix](custom_language_support/quick_fix.md)
