[//]: # (title: Syntax and Error Highlighting)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The class used to specify how a particular range of text should be highlighted is called [`TextAttributesKey`](upsource:///platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java).
An instance of this class is created for every distinct type of item that should be highlighted (keyword, number, string, etc.).
The `TextAttributesKey` defines the default attributes applied to items of the corresponding type (for example, keywords are bold, numbers are blue, strings are bold and green).
Highlighting from multiple `TextAttributesKey` items can be layered - for example, one key may define an item's boldness and another color.

Existing highlighting can be suppressed programmatically in certain contexts, see [](controlling_highlighting.md).

> To force re-highlighting (e.g., after changing plugin specific settings), use
> [`DaemonCodeAnalyzer.restart()`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java).
>
{type="tip"}

## Color Settings
The mapping of the `TextAttributesKey` to specific attributes used in an editor is defined by the [`EditorColorsScheme`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsScheme.java) class.
It can be configured by the user by providing an implementation of [`ColorSettingPage`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java) registered in `com.intellij.colorSettingsPage` extension point.

The <menupath>File | Export | Files or Selection to HTML</menupath> feature uses the same syntax highlighting mechanism as the editor.
Thus, it will work automatically for custom languages that provide a syntax highlighter.

**Examples**:
- [`ColorSettingsPage`](upsource:///plugins/properties/src/com/intellij/lang/properties/PropertiesColorsPage.java) for [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Color Settings Page](syntax_highlighter_and_color_settings_page.md)

> New functionality about Language Defaults and support for additional color schemes are detailed in [Color Scheme Management](color_scheme_management.md).
>
{type="note"}

The syntax and error highlighting are performed on multiple levels: Lexer, Parser, and (External) Annotator(s).

## Lexer

The first syntax highlighting level is based on the lexer output and is provided through the [`SyntaxHighlighter`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) interface.
The syntax highlighter returns the `TextAttributesKey` instances for each token type, which needs special highlighting.
For highlighting lexer errors, the standard `TextAttributesKey` for bad characters [`HighlighterColors.BAD_CHARACTER`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/HighlighterColors.java) can be used.

**Examples:**
- [`SyntaxHighlighter`](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesHighlighter.java) implementation for [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Syntax Highlighter](syntax_highlighter_and_color_settings_page.md)

> Use [`HtmlSyntaxInfoUtil`](upsource:///platform/lang-impl/src/com/intellij/openapi/editor/richcopy/HtmlSyntaxInfoUtil.java) to create Lexer-based highlighted code samples, e.g. for usage in documentation.
>
{type="tip"}

### Semantic Highlighting

[Semantic highlighting](https://www.jetbrains.com/help/idea/configuring-colors-and-fonts.html#semantic-highlighting) provides an additional coloring layer to improve the visual distinction of several related items (e.g., method parameters, local variables).

Register [`RainbowVisitor`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/RainbowVisitor.java) in `com.intellij.highlightVisitor` extension point.
[](#color-settings) must implement [`RainbowColorSettingsPage`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/RainbowColorSettingsPage.java) in addition.

## Parser

The second level of error highlighting happens during parsing.
If a particular sequence of tokens is invalid according to the grammar of the language, the [`PsiBuilder.error()`](upsource:///platform/core-api/src/com/intellij/lang/PsiBuilder.java) method can highlight the invalid tokens and display an error message showing why they are not valid.

See [](syntax_errors.md) on how to programmatically suppress these errors in certain contexts.

## Annotator

The third level of highlighting is performed through the [`Annotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) interface.
A plugin can register one or more annotators in the `com.intellij.annotator` extension point, and these annotators are called during the background highlighting pass to process the elements in the custom language's PSI tree.

Annotators can analyze not only the syntax, but also the semantics using PSI, and thus can provide much more complex syntax and error highlighting logic.
The annotator can also provide quick fixes to problems it detects.
When the file is changed, the annotator is called incrementally to process only changed elements in the PSI tree.

> See also [Code Inspections](code_inspections_and_intentions.md) which offer a more fine-grained control and some additional features.
>
{type="note"}

### Errors/Warning
See [Inspections](https://jetbrains.design/intellij/text/inspections/) topic in IntelliJ Platform UI Guidelines on how to write message texts for highlighting/quick fixes.

To highlight a region of text as a warning or error:

<tabs>

<tab title="2020.1 and later">

```java
    holder.newAnnotation(HighlightSeverity.WARNING, "Invalid code") // or HighlightSeverity.ERROR
        .withFix(new MyFix(psiElement))
        .create();
```

</tab>

<tab title="Pre-2020.1">

Call `createWarningAnnotation()`/`createErrorAnnotation()` on the [`AnnotationHolder`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java), and optionally calls `registerFix()` on the returned [`Annotation`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotation.java) object to add a quick fix for the error or warning.

</tab>

</tabs>

### Syntax
To apply additional syntax highlighting (2020.1 and later):

<tabs>

<tab title="2020.1 and later">

```java
    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(rangeToHighlight)
            .textAttributes(MyHighlighter.EXTRA_HIGHLIGHT_ATTRIBUTE)
            .create();
```

</tab>

<tab title="Pre-2020.1">

Call `AnnotationHolder.createInfoAnnotation()` with an empty message and then [`Annotation.setTextAttributes()`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotation.java).

</tab>

</tabs>

**Examples:**
- [`Annotator`](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesAnnotator.java) for [Properties language plugin](upsource:///plugins/properties)
- [Custom Language Support Tutorial: Annotator](annotator.md)

## External Tool

Finally, if the custom language employs external tools for validating files in the language (for example, uses the Xerces library for XML schema validation), it can provide an implementation of the [`ExternalAnnotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) interface and register it in `com.intellij.externalAnnotator` extension point (`language` attribute must be specified).

The [`ExternalAnnotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) highlighting has the lowest priority and is invoked only after all other background processing has completed.
It uses the same [`AnnotationHolder`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java) interface for converting the output of the external tool into editor highlighting.

To skip running specific `ExternalAnnotator` for given file, register [`ExternalAnnotatorsFilter`](upsource:///platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) extension in `com.intellij.daemon.externalAnnotatorsFilter` extension point.
