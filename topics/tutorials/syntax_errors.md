# Syntax Errors

<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<link-summary>Suppressing syntax errors in the editor programmatically.</link-summary>

The IntelliJ Platform provides a mechanism for analyzing the PSI tree and highlighting syntax errors out of the box.

While the PSI tree for the code is being built, a [parser](implementing_parser_and_psi.md) tries to consume tokens according to language grammar.
When it encounters a syntax error, like an unexpected token, a [`PsiErrorElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiErrorElement.java) is created and added to the PSI tree with an appropriate error description.
In the code analysis daemon, the IDE visits every PSI element in the tree, and when a `PsiErrorElement` is encountered, information about it is collected and used while highlighting the code in the editor.

> Additional highlighting can also be added using [Annotators](syntax_highlighting_and_error_highlighting.md#syntax) or Inspections, see [Controlling Highlighting](controlling_highlighting.md) on how to suppress.

## Controlling Syntax Errors Highlighting

In some cases highlighting syntax errors is insufficient or even unnecessary:
- An error can be presented to the user in an easier-to-understand way.
- The actual error cause is in a different location in the code, which is not easily visible when looking at the syntax error.
- An error can be safely ignored in a given context, e.g., incomplete code fragment injected in a Markdown code block.
- A syntax error is not critical and can be considered a warning or even information.

The IntelliJ Platform allows plugins to disable highlighting particular syntax errors.
These errors can be optionally handled by additional Annotators or [Inspections](code_inspections.md) if needed.

To control which `PsiErrorElement`s should be reported and which can be ignored, a plugin has to provide an implementation of [`HighlightErrorFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/highlighting/HighlightErrorFilter.java) and
register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.highlightErrorFilter"/></include>.
It contains a single abstract method `shouldHighlightErrorElement()` which should return `false` if a given `PsiErrorElement` should not be highlighted in the editor.

**Examples:**
- [`HtmlClosingTagErrorFilter`](%gh-ic%/xml/xml-analysis-impl/src/com/intellij/codeInsight/highlighting/HtmlClosingTagErrorFilter.java) ignoring unmatched closing tag in HTML files
- [`MarkdownCodeFenceErrorHighlightingIntention`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/MarkdownCodeFenceErrorHighlightingIntention.kt) ignoring all syntax errors in a code injected into a Markdown code blocks

**See also:**
- [Controlling Highlighting](controlling_highlighting.md)
