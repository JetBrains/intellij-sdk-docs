---
title: Syntax Highlighting and Error Highlighting
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The class used to specify how a particular range of text should be highlighted is called
[`TextAttributesKey`](upsource:///platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java).
An instance of this class is created for every distinct type of item which should be highlighted (keyword, number, string and so on).
The `TextAttributesKey` defines the default attributes which are applied to items of the corresponding type (for example, keywords are bold, numbers are blue, strings are bold and green).
The mapping of the `TextAttributesKey` to specific attributes used in an editor is defined by the
[`EditorColorsScheme`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsScheme.java)
class, and can be configured by the user if the plugin provides an appropriate configuration interface.
Highlighting from multiple `TextAttributesKey` items can be layered - for example, one key may define an item's boldness and another its color.

**Note:**
New functionality about Language Defaults and support for additional color schemes as detailed in
[Color Scheme Management](/reference_guide/color_scheme_management.md).


The syntax and error highlighting is performed on multiple levels: Lexer, Parser and (External) Annotator.

### Lexer

The first level of syntax highlighting is based on the lexer output, and is provided through the
[`SyntaxHighlighter`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java)
interface.
The syntax highlighter returns the `TextAttributesKey` instances for each token type which needs special highlighting.
For highlighting lexer errors, the standard `TextAttributesKey` for bad characters
[`HighlighterColors.BAD_CHARACTER`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/HighlighterColors.java)
can be used.

**Examples:**
- [`SyntaxHighlighter`](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesHighlighter.java)
implementation for
[Properties language plugin](upsource:///plugins/properties/)
- [Custom Language Support Tutorial: Syntax Highlighter](/tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.md)

### Parser

The second level of error highlighting happens during parsing.
If a particular sequence of tokens is invalid according to the grammar of the language, the
[`PsiBuilder.error()`](upsource:///platform/core-api/src/com/intellij/lang/PsiBuilder.java)
method can be used to highlight the invalid tokens and display an error message showing why they are not valid.

### Annotator

The third level of highlighting is performed through the
[`Annotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java)
interface.
A plugin can register one or more annotators in the `com.intellij.annotator` extension point, and these annotators are called during the background highlighting pass to process the elements in the PSI tree of the custom language.
Annotators can analyze not only the syntax, but also the semantics using PSI, and thus can provide much more complex syntax and error highlighting logic.
The annotator can also provide quick fixes to problems it detects.

When the file is changed, the annotator is called incrementally to process only changed elements in the PSI tree.

To highlight a region of text as a warning or error, the annotator calls `createErrorAnnotation()` or `createWarningAnnotation()` on the
[`AnnotationHolder`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java)
object passed to it, and optionally calls `registerFix()` on the returned
[`Annotation`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotation.java)
object to add a quick fix for the error or warning.
To apply additional syntax highlighting, the annotator can call
[`AnnotationHolder.createInfoAnnotation()`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java)
with an empty message and then call
[`Annotation.setTextAttributes()`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotation.java)
to specify the text attributes key for the highlighting.

> **NOTE** See also [Code Inspections](code_inspections_and_intentions.md) which offer a more fine-grained control and some additional features.

**Examples:**
- [`Annotator`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesAnnotator.java)
for
[Properties language plugin](upsource:///plugins/properties/)
- [Custom Language Support Tutorial: Annotator](/tutorials/custom_language_support/annotator.md)

### External Tool

Finally, if the custom language employs external tools for validating files in the language (for example, uses the Xerces library for XML schema validation), it can provide an implementation of the
[`ExternalAnnotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java)
interface and register it in `com.intellij.externalAnnotator` extension point.
The
[`ExternalAnnotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java)
highlighting has the lowest priority and is invoked only after all other background processing has completed.
It uses the same
[`AnnotationHolder`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java)
interface for converting the output of the external tool into editor highlighting.

## Color Settings

The plugin can also provide a configuration interface to allow the user to configure the colors used for highlighting specific items.
In order to do that, it should provide an implementation of
[`ColorSettingPage`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java)
and register it in the `com.intellij.colorSettingsPage` extension point.

The _Export to HTML_ feature uses the same syntax highlighting mechanism as the editor, so it will work automatically for custom languages which provide a syntax highlighter.

**Examples**:
- [`ColorSettingsPage`](upsource:///plugins/properties/src/com/intellij/openapi/options/colors/pages/PropertiesColorsPage.java)
for
[Properties language plugin](upsource:///plugins/properties/)
- [Custom Language Support Tutorial: Color Settings Page](/tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.md)
