[//]: # (title: Documentation)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<tldr>

**Product Help:** [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)

</tldr>

Custom languages can use the `com.intellij.lang.documentationProvider` extension point (EP) to show documentation for functions,
methods, classes, or other constructs right inside the IDE.
Accessing the documentation is done by calling
<ui-path>View | Quick Documentation</ui-path>
or hovering over a symbol, which will open a popup to show type information, parameters, usage descriptions, or examples.
The source of the documentation contents can vary.
Often it is extracted from comments (e.g. JavaDoc comments) in the source code,
but it's also possible to access external resources like web pages.

In addition to showing the documentation, the `getQuickNavigateInfo()` method returns the text to be displayed
when the user hovers over an element with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed.

Custom actions can also be added to documentation inlays and documentation popups via
[`DocumentationActionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/documentation/DocumentationActionProvider.java) registered in the
`com.intellij.documentationActionProvider` extension point.


# Implementation

Custom language developers usually extend from
[`AbstractDocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java)
instead of implementing the
[`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java) interface.
This implementation needs to be registered in `com.intellij.lang.documentationProvider` extension point.

The main work is done in `generateDoc()`, which has two PSI element arguments:
the target element for which the documentation is requested and the original element under the cursor.
If IntelliJ Platform's choice of the target element isn't suitable for your language, you can override `getCustomDocumentationElement()`
and provide the correct element.

How the documentation for the target element is created is up to the custom language developer.
A common choice is to extract and format documentation comments.
To format the documentation contents, you should use
[`DocumentationMarkup`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationMarkup.java)
to achieve a consistent output.

> Use [`HtmlSyntaxInfoUtil`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/richcopy/HtmlSyntaxInfoUtil.java) to create Lexer-based highlighted code samples.
>
{type="tip"}

Once these steps are completed, the following additional features can be implemented:

* Implement `getQuickNavigateInfo()` to provide the text that should be displayed when an element is hovered over with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed.
* Implement `generateHoverDoc()` to show different contents on mouse hover.
* Implement `getDocumentationElementForLookupItem()` to return a suitable PSI element for the given lookup element when
  <ui-path>View | Quick Documentation</ui-path> is called on an element of the autocompletion popup.
* Implement `getUrlFor()` and [`ExternalDocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/ExternalDocumentationProvider.java) to fetch documentation for elements from online resources.


# Examples

The [custom language tutorial](documentation_provider.md) contains a step-by-step guide for the `DocumentationProvider` of the Simple language.
In addition, several implementations of other languages exist in the IntelliJ Platform code, for instance:

* The [Properties Language plugin](%gh-ic%/plugins/properties) has a small and easy-to-understand [`DocumentationProvider`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java) similar to the one shown in the custom language tutorial.
* Usage examples for `DocumentationMarkup` can be found in [`ThemeJsonDocumentationProvider`](%gh-ic%/plugins/devkit/intellij.devkit.themes/src/ThemeJsonDocumentationProvider.java).
