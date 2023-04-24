# Documentation

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Providing code documentation displayed in the popup invoked by hovering over a symbol or invoking the "View | Quick Documentation" action.</link-summary>

<tldr>

**Product Help:** [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)

</tldr>

Custom languages can show documentation for functions, methods, classes, or other constructs right inside the IDE
by implementing one of three EPs depending on their use-case.
The different EPs can build documentation from
[offsets in the current editor](coordinates_system.md#editor-coordinate-systems),
for [Psi elements](psi_elements.md) or for
[Symbols](symbols.md) and we provide all required details in the [](#implementation) section.

Accessing the documentation is done by calling
<ui-path>View | Quick Documentation</ui-path>
or hovering over a symbol, which will open a popup to show type information, parameters, usage descriptions, or examples.
The source of the documentation contents can vary.
Often it is extracted from comments (e.g. JavaDoc comments) in the source code,
but it's also possible to access external resources like web pages.

Custom actions can also be added to documentation inlays and documentation popups via
[`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) registered in the
`com.intellij.documentationActionProvider` extension point.


## Implementation

Custom language developers can choose from three different EPs to provide documentation.
The general approach is the following

1. Implement one of the EPs below which should extract the necessary entity (e.g. a Psi element) for
   which the documentation is requested. It returns instances of [](#documentationtarget).
2. The implementation of `DocumentationTarget` provides the functionality to compute the rendered documentation,
   its presentation in the [documentation tool window](https://www.jetbrains.com/help/idea/documentation-tool-window.html),
   or separate hints that are displayed when hovering over code.
3. The rendered documentation is an instance of
   [`DocumentationResult`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationResult.kt)
   which wraps the documentation in HTML format, but is also able to include images or external URLs.
   `DocumentationResult` can be used asynchronously when building the documentation would take too long
   and block the IDE.

### `DocumentationTargetProvider`

Implement [`DocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.targetProvider` to build documentation
for a certain offset in a `PsiFile` by overriding `DocumentationTargetProvider.documentationTargets()`.

For an example, please refer to
[`KotlinPsiDocumentationTargetProvider`](%gh-ic%/plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/quickDoc/KotlinPsiDocumentationTargetProvider.kt).

### `PsiDocumentationTargetProvider`

Implement [`PsiDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/PsiDocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.psiTargetProvider` to build documentation
for Psi elements by overriding `PsiDocumentationTargetProvider.documentationTarget()`.

For an example, please refer to
[`KotlinPsiDocumentationTargetProvider`](%gh-ic%/plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/quickDoc/KotlinPsiDocumentationTargetProvider.kt).

### `SymbolDocumentationTargetProvider`

Implement [`SymbolDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/SymbolDocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.symbolTargetProvider` to build documentation
for [](symbols.md) by overriding `SymbolDocumentationTargetProvider.documentationTarget()`.

## `DocumentationTarget`

Each of the implementations above returns instances of
[`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt).
The main work is done in `computeDocumentation()` where the documentation is built from the available
information.
If plugin developers worked with the now deprecated
[`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
before, then `computeDocumentation()` should do the work that was formerly done in
`DocumentationProvider.generateDoc()`.

In addition to showing the documentation, the `computeDocumentationHint()` method returns the text to be displayed
when the user hovers over an element with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed.
In the old framework, this method was called `DocumentationProvider.getQuickNavigateInfo()`.

Todo: Further explanation on `createPointer()`

## Further tips

How the documentation for the target element is created is up to the custom language developer.
A common choice is to extract and format documentation comments.
To format the documentation contents, you should use
[`DocumentationMarkup`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationMarkup.java)
to achieve a consistent output.

> Use [`HtmlSyntaxInfoUtil`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/richcopy/HtmlSyntaxInfoUtil.java) to create Lexer-based highlighted code samples.
>

## Examples

Todo
