[//]: # (title: Custom Language Support Tutorial)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

In this tutorial we will add support for a [.properties](https://en.wikipedia.org/wiki/.properties) language and its usages within Java code.

 >  IntelliJ Platform support for custom languages is discussed in more depth in the [Custom Language Support](custom_language_support.md) section.
> Corresponding parts are linked under "Reference" on top of each page in this tutorial.
 >
 {type="tip"}

The example plugin used in this tutorial is the [`simple_language_plugin`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/simple_language_plugin) code sample.
This a step-by-step tutorial, and it requires completing each step, in order:

*  [1. Prerequisites](prerequisites.md)
*  [2. Language and File Type](language_and_filetype.md)
*  [3. Grammar and Parser](grammar_and_parser.md)
*  [4. Lexer and Parser Definition](lexer_and_parser_definition.md)
*  [5. Syntax Highlighter and Color Settings Page](syntax_highlighter_and_color_settings_page.md)
*  [6. PSI Helpers and Utilities](psi_helper_and_utilities.md)
*  [7. Annotator](annotator.md)
*  [8. Line Marker Provider](line_marker_provider.md)
*  [9. Completion Contributor](completion_contributor.md)
*  [10. Reference Contributor](reference_contributor.md)
*  [11. Find Usages Provider](find_usages_provider.md)
*  [12. Folding Builder](folding_builder.md)
*  [13. Go To Symbol Contributor](go_to_symbol_contributor.md)
*  [14. Structure View Factory](structure_view_factory.md)
*  [15. Formatter](formatter.md)
*  [16. Code Style Settings](code_style_settings.md)
*  [17. Commenter](commenter.md)
*  [18. Quick Fix](quick_fix.md)
