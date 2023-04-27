<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Documentation

<link-summary>Providing code documentation displayed in the popup invoked by hovering over a symbol or invoking the "View | Quick Documentation" action.</link-summary>

<tldr>

**Product Help:** [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)

</tldr>

Custom languages can display documentation for various constructs, such as functions, methods, classes, or others, directly within the IDE.
To access the documentation, users can either select <ui-path>View | Quick Documentation</ui-path> or hover over a symbol.
This will open a popup that displays type information, parameters, usage descriptions, or examples.
The source of the documentation content can vary.
While it is often extracted from comments in the source code (e.g., Javadoc comments),
external resources, such as web pages, can also be accessed.

From IntelliJ Platform version 2023.1 onwards, plugin developers can choose to implement
one of three extension points (EPs) from the new `DocumentationTarget` API based on the specific use-case.
These EPs enable building documentation from
[offsets in the current editor](coordinates_system.md#editor-coordinate-systems),
[PSI elements](psi_elements.md), or [Symbols](symbols.md).
Detailed information on implementing these EPs can be found in the [](#documentationtarget-api) section.

> Plugins targeting versions earlier than 2023.1 must use the [`DocumentationProvider` API](#documentationprovider-api).
> Note that as long as the transition to the new API is
> not complete, the [custom language tutorial](documentation_provider.md) will use the `DocumentationProvider`.
>
{style="note"}

## DocumentationTarget API

Custom language developers have the flexibility to select from three distinct EPs for providing documentation to their users.
To ensure clarity and avoid confusion, we provide a high-level summary of the overall approach,
outlining the primary components and their interactions.

<procedure title="Overall Approach">

1. Implement one of the EPs below which should extract the necessary entity (e.g. a PSI element) for
   which the documentation is requested. It returns instances of
   [`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt),
   which will be explained in a later section.
2. The implementation of `DocumentationTarget` provides the functionality to compute the rendered documentation,
   its presentation in the [documentation tool window](https://www.jetbrains.com/help/idea/documentation-tool-window.html),
   or separate hints that are displayed when hovering over code.
3. The rendered documentation is an instance of
   [`DocumentationResult`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationResult.kt),
   which wraps the documentation in HTML format but can also include images or external URLs.
   `DocumentationResult` can be used asynchronously when building the documentation would take too long
   and block the IDE.

</procedure>

{style="full"}
DocumentationTargetProvider
: Implement
[`DocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.targetProvider` extension point to build documentation
for a certain offset in a `PsiFile` by overriding `DocumentationTargetProvider.documentationTargets()`.

PsiDocumentationTargetProvider
: Implement
[`PsiDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/PsiDocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.psiTargetProvider` extension point to build documentation
for PSI elements by overriding `PsiDocumentationTargetProvider.documentationTarget()`.

SymbolDocumentationTargetProvider
: Implement
[`SymbolDocumentationTargetProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/SymbolDocumentationTargetProvider.java)
and register it as `com.intellij.platform.backend.documentation.symbolTargetProvider` extension point to build documentation
for [](symbols.md) by overriding `SymbolDocumentationTargetProvider.documentationTarget()`.

### `DocumentationTarget`

Each of the implementations above returns instances of
[`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt).
The main work is done in `computeDocumentation()` where the documentation is built from the available
information.
If a plugin implemented the now deprecated
[`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
before, then `computeDocumentation()` should do the work that was formerly done in
`DocumentationProvider.generateDoc()`.

In addition to showing the documentation, the `computeDocumentationHint()` method returns the text to be displayed
when the user hovers over an element with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed or when
<ui-path>Preferences | Editor | Code Editing | "Show quick documentation on hover"</ui-path> is enabled.
In the old framework, this method was called `DocumentationProvider.getQuickNavigateInfo()`.

The `createPointer()` method manages instance restoration and ensures access to the entity across different read actions.
When implementing the `createPointer()` method, it is essential to handle invalidated PSI elements.
Unlike PSI elements, the `DocumentationTarget` API does not include an `isValid()` method and the returned pointer is expected
to be `null` if the instance (and all contained objects) cannot be restored.
Plugin authors can use
[`KotlinDocumentationTarget.createPointer()`](%gh-ic%/plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/quickDoc/KotlinDocumentationTarget.kt)
as a reference.

### Examples
{#documentationtarget-api-examples}

- [`KotlinPsiDocumentationTargetProvider`](%gh-ic%/plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/quickDoc/KotlinPsiDocumentationTargetProvider.kt).
- [`KotlinDocumentationTarget`](%gh-ic%/plugins/kotlin/fir/src/org/jetbrains/kotlin/idea/quickDoc/KotlinDocumentationTarget.kt)
- [`PsiElementDocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/documentation/psi/PsiElementDocumentationTarget.kt)

## DocumentationProvider API

> As of IntelliJ Platform version 2023.1 the `DocumentationProvider` API is deprecated and plugin
> authors should use the [](#documentationtarget-api).
>
{style="note"}

Custom language developers usually extend from
[`AbstractDocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java)
instead of implementing the
[`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) interface.
This implementation needs to be registered in `com.intellij.lang.documentationProvider` extension point.

The main work is done in `generateDoc()`, which has two PSI element arguments:
the target element for which the documentation is requested and the original element under the cursor.
If IntelliJ Platform's choice of the target element isn't suitable for your language, you can override `getCustomDocumentationElement()`
and provide the correct element.

Once these steps are completed, the following additional features can be implemented:

* Implement `getQuickNavigateInfo()` to provide the text that should be displayed when an element is hovered over with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed.
* Implement `generateHoverDoc()` to show different contents on mouse hover.
* Implement `getDocumentationElementForLookupItem()` to return a suitable PSI element for the given lookup element when
  <ui-path>View | Quick Documentation</ui-path> is called on an element of the autocompletion popup.
* Implement `getUrlFor()` and [`ExternalDocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/ExternalDocumentationProvider.java) to fetch documentation for elements from online resources.

### Examples
{#documentationprovider-api-examples}

The [custom language tutorial](documentation_provider.md) contains a step-by-step guide for the `DocumentationProvider` of the Simple language.
In addition, several implementations of other languages exist in the IntelliJ Platform code, for instance:

* The [Properties Language plugin](%gh-ic%/plugins/properties) has a small and easy-to-understand [`DocumentationProvider`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java) similar to the one shown in the custom language tutorial.
* Usage examples for `DocumentationMarkup` can be found in [`ThemeJsonDocumentationProvider`](%gh-ic%/plugins/devkit/intellij.devkit.themes/src/ThemeJsonDocumentationProvider.java).

## Further tips

Additionally, custom actions can be incorporated into documentation inlays and popups using the
[`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java).
This provider should be registered with the `com.intellij.documentationActionProvider` extension point.

How the documentation for the target element is created is up to the custom language developer.
A common choice is to extract and format documentation comments.
To format the documentation contents, you should use
[`DocumentationMarkup`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationMarkup.java)
to achieve a consistent output.

> Use [`HtmlSyntaxInfoUtil`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/richcopy/HtmlSyntaxInfoUtil.java) to create Lexer-based highlighted code samples.
>
