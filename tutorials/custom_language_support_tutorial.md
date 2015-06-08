---
layout: editable
title: Custom Language Support Tutorial
---

In this tutorial we will add basic support for
[.properties](http://en.wikipedia.org/wiki/.properties)
language and it's usages within Java code.

We will generate parser and PSI elements using
[Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) plugin.
The lexer class will be generated with
[JFlex](http://jflex.de/).

This a step-by-step tutorial and it requires performing every step:

*  [1. Prerequisites](tutorials/custom_language_support/prerequisites.html)
*  [2. Language and File Type](tutorials/custom_language_support/language_and_filetype.html)
*  [3. Grammar and Parser](tutorials/custom_language_support/grammar_and_parser.html)
*  [4. Lexer and Parser Definition](tutorials/custom_language_support/lexer_and_parser_definition.html)
*  [5. Syntax Highlighter and Color Settings Page](tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.html)
*  [6. PSI Helpers and Utilities](tutorials/custom_language_support/psi_helper_and_utilities.html)
*  [7. Annotator](tutorials/custom_language_support/annotator.html)
*  [8. Line Marker Provider](tutorials/custom_language_support/line_marker_provider.html)
*  [9. Completion Contributor](tutorials/custom_language_support/completion_contributor.html)
*  [10. Reference Contributor](tutorials/custom_language_support/reference_contributor.html)
*  [11. Find Usages Provider](tutorials/custom_language_support/find_usages_provider.html)
*  [12. Folding Builder](tutorials/custom_language_support/folding_builder.html)
*  [13. Go To Symbol Contributor](tutorials/custom_language_support/go_to_symbol_contributor.html)
*  [14. Structure View Factory](tutorials/custom_language_support/structure_view_factory.html)
*  [15. Formatter](tutorials/custom_language_support/formatter.html)
*  [16. Code Style Settings](tutorials/custom_language_support/code_style_settings.html)
*  [17. Commenter](tutorials/custom_language_support/commenter.html)
*  [18. Quick Fix](tutorials/custom_language_support/quick_fix.html)

The final code can be found on

{% copyArea 'https://github.com/JetBrains/SimplePlugin.git' %}

