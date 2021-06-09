[//]: # (title: Documentation)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Custom languages can use the `com.intellij.lang.documentationProvider` extension point (EP) to show documentation for functions,
methods, classes, or other constructs right inside the IDE.
Accessing the documentation is done by calling
[<menupath>View | Quick Documentation</menupath>](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
or hovering over a symbol, which will open a popup to show type information, parameters, usage descriptions, or examples.
The source of the documentation contents can vary.
Often it is extracted from comments (e.g. JavaDoc comments) in the source code,
but it’s also possible to access external resources like web pages.

In addition to showing the documentation, the `getQuickNavigateInfo()` method returns the text to be displayed
when the user hovers over an element with `Ctrl`/`Cmd` pressed.

Custom actions can also be added to documentation inlays and documentation popups via
`com.intellij.codeInsight.documentation.DocumentationActionProvider` registered in the
`com.intellij.documentationActionProvider` extension point.


# Implementation

Custom language developers usually extend from
[`AbstractDocumentationProvider`](https://upsource.jetbrains.com/idea-ce/file/idea-ce-7b9b8cc138bbd90aec26433f82cd2c6838694003/platform/analysis-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java?_ga=2.266434503.859029121.1622422021-1187869345.1619700453)
instead of implementing the
[`DocumentationProvider`](https://upsource.jetbrains.com/idea-ce/file/idea-ce-7b9b8cc138bbd90aec26433f82cd2c6838694003/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java?_ga=2.97574870.859029121.1622422021-1187869345.1619700453) interface.
This implementation needs to be registered as `com.intellij.lang.documentationProvider` in the <path>plugin.xml</path>.

The main work is done in `generateDoc()`, which has two PSI element arguments:
the target element for which the documentation is requested and the original element under the cursor.
If  IntelliJ Platform's choice of target element isn't suitable for your language, you can override `getCustomDocumentationElement()`
and provide the correct element.

How the documentation for the target element is created is up to the custom language developer.
A common choice is to extract and format documentation comments.
To format the documentation contents, you should use
[`DocumentationMarkup`](https://upsource.jetbrains.com/idea-ce/file/idea-ce-7b9b8cc138bbd90aec26433f82cd2c6838694003/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationMarkup.java?_ga=2.194568036.859029121.1622422021-1187869345.1619700453)
to achieve a consistent output.

Once these steps are completed, the following additional features can be implemented:

* Implement `getQuickNavigateInfo()` to provide the text that should be displayed when an element is hovered over with `Ctrl`/`Cmd` pressed.
* Implement `generateHoverDoc()` to show different contents on mouse hover.
* Implement `getDocumentationElementForLookupItem()` to return a suitable PSI element for the given lookup element when 
  <menupath>View | Quick Documentation</menupath> is called on an element of the autocompletion popup.
* Implement `getUrlFor()` and [`ExternalDocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/ExternalDocumentationProvider.java) to fetch documentation for elements from online resources.


# Examples

The [custom language tutorial](documentation_provider.md) contains a step-by-step guide for the `DocumentationProvider` of the Simple language.
In addition, several implementations of other languages exist in the IntelliJ Platform code, for instance:

* The [Properties Language plugin](upsource:///plugins/properties/) has a small and easy-to-understand [`DocumentationProvider`](upsource:///plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java) similar to the one shown in the custom language tutorial.
* The [`CssDocumentationProvider`](upsource:///CSS/src/com/intellij/psi/css/impl/util/CssDocumentationProvider.java) is an example of an `ExternalDocumentationProvider`, which accesses online resources to provide documentation.
* Usage examples for DocumentationMarkup can be found in [`ThemeJsonDocumentationProvider`](upsource:///plugins/devkit/devkit-core/src/themes/ThemeJsonDocumentationProvider.java).
