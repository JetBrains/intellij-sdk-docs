[//]: # (title: SDK Docs Style Guide)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Writing and notation styleguide for SDK Docs.</excerpt>

This document describes the writing style used in authoring open-source IntelliJ Platform SDK documentation.
Before you begin, please read this page thoroughly, as well as the [Code of Conduct](intellij-sdk-docs-original_CODE_OF_CONDUCT.md) and [License](https://github.com/JetBrains/intellij-sdk-docs/blob/main/LICENSE.txt) documents.
Please see also [](intellij-sdk-docs-original_CONTRIBUTING.md) for some general remarks.
For information about contributing to the IntelliJ Platform itself, please visit [Contributing to the IntelliJ Platform](platform_contributions.md).

First and foremost, we should keep in mind our audience and their objectives:
_Someone reading technical content is usually looking to answer a specific question.
That question might be broad or narrowly-focused, but either way, our goal is to provide answers without distraction._

The style of the Intellij Platform SDK documentation is captured by using a markup language named [Markdown](https://github.github.com/gfm/).

To verify grammar and correct spelling, it is highly recommended to use [Grazie Professional](https://plugins.jetbrains.com/plugin/16136-grazie-professional) plugin to highlight any issues on-the-fly in the IDE.

## Documentation Markup

The documentation files themselves are [Markdown](https://github.github.com/gfm/) files (<path>*.md</path>) that get automatically converted to HTML when the site is built.

### Page Format

Each Markdown file **must** start with a header defining its title using the following notation:

```yaml
[//]: # (title: Contributing to the IntelliJ Platform SDK)
```

The page title should be as concise as possible, so it can be reused in the [](#table-of-contents) as is.

It **must** be followed by a copyright notice, formatted using HTML comment notation:

```html
<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->
```

Every page **should** provide a short excerpt (usually one sentence) using dedicated `<excerpt>` tag before the main page contents:

```html
<excerpt>Listeners allow subscription to application and project events.</excerpt>
```

## Content Style

### Terminology

Consistent terminology helps the reader grasp new concepts more quickly:
* The open-source code in the GitHub repository `intellij-community` is known as the IntelliJ Platform.
  Use the full phrase in the SDK documentation.
* IDEs based on the IntelliJ Platform are described as _IntelliJ Platform-based IDEs_.
  Once that term is used on a page, authors may use _IDEs_.
* When referring to JetBrains products always use the full name such as _IntelliJ IDEA Ultimate Edition_.
  However, only use product names when extensibility or functionality is particular to a product.

### Text Format Conventions

Start every sentence on a new line.
For very long sentences, add additional line breaks after `,`, `:` or other sensible places.
Very long [links](#links) should also be on a separate line.

Consistent text styles are used to standardize references and keywords:
* Menu paths are wrapped using `<menupath>` with pipe characters separating each level: `<menupath>Settings/Preferences | Editor</menupath>`: <menupath>Settings/Preferences | Editor</menupath>
  Menu paths to settings always start with "Settings/Preferences" to cover all platforms.
  Inside tables, use `&#124;` instead of `|` to prevent escaping problems.
* User interface element names like labels, buttons, checkboxes, etc. are wrapped using `<control>`: `Press <control>Continue</continue>`: Press <control>Continue</control>
* Non-code keywords and quotations, or the names of non-code files, are formatted as italic style: \_UI Theme\_ (_UI Theme_), \_README.md\_ (_README.md_.)
  Examples of this file type include _LICENSE.txt_ and _README.md_.
* Code keywords and classnames are formatted as code style: \`interface\`: `interface`, \`AnAction\`: `AnAction`, \`name\` attribute: `name` attribute.
* File names are wrapped using `<path>`: `<path>build.gradle.kts</path>` <path>build.gradle.kts</path>.
* File formats are shown as all capital letters: PNG and XML.
* Filename extensions are not capitalized when part of a full filename, path, or URL: <path>filename.ext</path>.
* Keyboard shortcuts are wrapped using `<shortcut>`: `press <shortcut>Alt+Insert</shortcut>` becomes "press <shortcut>Alt+Insert</shortcut>".
* See [Guidelines for Highlighting Syntax](#guidelines-for-highlighting-syntax) for more best practices for representing code fragments.
* See [Links to IntelliJ Platform Source](#links-to-intellij-platform-source) for more details about representing names of source files in links.

### Links

Links are handled as standard Markdown links and can be anchored to external sites, pages within the sites, or headings in the sites.

To link to a page within the site using its `title:` as link text, use shortcut notation `[](page.md)`{disable-links}.

When a Markdown header is converted to an HTML header, it is assigned an ID so that it can be linked.
For example, `## Introduction` gets the ID of `introduction`, and can be linked either in the same page or cross-page as described below.

#### General Links

General Markdown links have the default Markdown link style:
* `[Gradle](https://gradle.org)`{disable-links} ([Gradle](https://gradle.org)) links to an external site, such as companies, articles, etc.
  If URL contains `%` character, append `{interpolate-variables="false"}`.
* Linking to pages and page sections within the SDK documentation:
  * `[Page Title](page.md)`{disable-links} links to an SDK doc page (all located under <path>/topics</path>).
    Note that the extension is <path>.md</path>, _NOT_ <path>.html</path>.
  * Specific _sections_ on pages in the SDK documentation are linked by using section anchors.
    The anchor name will be all lower case, and spaces are replaced with `-`, e.g. `## Page setup` becomes `#page-setup`.
    Once the anchor (`#`) character of the link is entered, the IDE code completion feature shows the available sections.
    * `[Link to a section on the current page](#another-section)`{disable-links} links to a heading on the current page.
    * `[Link to the section on another page](other_page.md#another-section)`{disable-links} links to a heading on another page.

  If the desired link label is the same as an SDK doc page or section title, leave the label part empty, e.g., `[](test-page.md)`{disable-links} or `[](test-page.md#section-1)`{disable-links}.
  The empty link label will be automatically filled with the actual page or section title.


#### Links to IntelliJ Platform Source

Links to files in the IntelliJ Platform (`intellij-community`) repository use `upsource:///` instead of the full URL to the repository.
The `upsource:///` URI effectively points to the root of the `intellij-community` repository.
* `[README.md](upsource:///README.md)`{disable-links} links to general, non-code information files. ([README.md](upsource:///README.md))
  Examples of this file type include _LICENSE.txt_ and _README.md_.
* `[`\`plugin.xml\``](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)`{disable-links} links to declarative source code files, use `code` style. ([`plugin.xml`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java))
  Examples of this file type include: `settings.gradle`, `plugin.xml` or `theme_basics.theme.json`.
* `[`\`AnAction\``](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)`{disable-links} links to source files for code objects like interfaces and classes, use `code` style but without the file extension. [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
  Examples of this file type include Java and Kotlin.
  * Note the use of \`\` characters surrounding the class name in the link.
  * When linking to an API in this manner, the FQN isn't necessary in the link.
  * No file extension (*.java, *.kt, *.py, etc.) is used by convention.
  * Be judicious when using such links.
    Generally, only one link is needed for a given file on a documentation page.
* Links to files in source code packages in other repositories follow much the same rules, except the links use `https:` instead of `upsource:///`.

### Guidelines for Highlighting Syntax

In-paragraph code fragments and IntelliJ Platform APIs are formatted according to these rules:
* Avoid using qualifiers like "`Foo` interface" or "`Foo` abstract class".
  Just refer to `Foo`.
* The FQN is used for the first reference to an interface, class, or package on a page.
  Rather than `AnAction`, introduce it as `com.intellij.openapi.actionSystem.AnAction`.
  Subsequent references on the page can be `AnAction`.
  Exception: the FQN is not used with an upsource [link](#links).
* Use the FQN when first introducing an [extension point](plugin_extension_points.md) (EP) on a page.
  Rather than `stubIndex`, introduce `com.intellij.stubIndex`.
  Subsequent mentions on the page can be `stubIndex`.
* For XML elements, use the tag notation with syntax highlighting: `<idea-version>`.
  Attributes are shown with syntax highlighting, and attribute values are shown in quotes: `since-build="191"`
* Method names always use empty parentheses: "call `bar()` to apply."
  Method names are prefixed with the class/interface name when needed for clarity: `Foo.bar()`.

### Source Code

Source code is represented by using code fences, which are three backticks.

Syntax highlighting is applied by specifying the language after the first set of ticks:

```md
    ```xml
        <tagName attribute="value">XML Text</tagName>
    ```
```
Supported languages include `xml`, `java`, `kotlin`, `groovy`, `bash`, `md`, `php`, and `text` for plaintext.

> Source code blocks must have one blank line before and after them, and must have a language specification for highlighting (use `text` as fallback).
>
{type="note"}

Whole files can be imported on a page using `src` attribute after code fences specifying the full path relative to <path>code_samples</path> root folder.

  `{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java"}`


The advantage is the code can come from the `code_samples` directory, so it will be live code that isn't silently stale.

The disadvantage is the file may contain a large class, too large for the documentation page to be useful.
To include only a specific method, specify `include-symbol="methodName"` additionally.

In any case, please keep code samples concise and avoid any unnecessary "surrounding" code or import statements.

### Tables

The syntax is to use the pipe (`|`) and minus symbols:

```md
    | Column 1 | Column 2 | Column 3 |
    | -------- | -------- | -------- |
    | Blah     | Blah     | Blah     |
```

Use `&#124;` instead of `|` to prevent escaping problems for `<menupath>` elements.

### Notes and Callouts

Notes and callouts can be specified using the blockquote syntax.
The converter looks at the `type` attribute specified after the text block.
If so, it applies a callout style.
The example below will be displayed as a callout, styled as a "note":

```md
> This is a note
>
{type="note"}
```
> This is a note
>
{type="note"}

The styles available for callouts are:
* `tip` - Information that makes the reader more productive.
* `note` - Information that is important for the reader to understand.
  This callout is reserved for essential points and concepts.
* `warning` - Information that is critical for the user to understand to prevent failures or errors.

> TODO/todo comments are discouraged in the main branch of `intellij-sdk-docs`.
> There are always exceptions, but the best practice is to resolve all TODOs before the final review.
> If immediate resolution isn't possible, write a YouTrack [SDK Issue](https://youtrack.jetbrains.com/issues/IJSDK) that captures the TODO and remove the comment from the document.
>
{type="tip"}

### Images

Every page typically has a dedicated subdirectory within root <path>/images</path>.

Images in this documentation are generally screenshots.
For consistency, images should be 296, 460, or 706 pixels wide.
The preferred image format is PNG at 144 DPI resolution.
A resolution of 72 DPI is acceptable but may look blurry on high-resolution monitors.

Use [Window Resizer](https://plugins.jetbrains.com/plugin/18045-window-resizer) plugin for exact resizing of the IDE application window.

It is crucial to reduce the size of image files to prevent bloating the repository and impacting the performance of the documentation site.
Optimize the image files using a tool such as the [PNG optimizer](https://plugins.jetbrains.com/plugin/7942-png-optimizer) plugin.

Images are embedded in a document by adding a Markdown link to the image like so:

```md
    ![Alt text](image.png)
```

If the width of an image needs to be adjusted, it can be specified as follows:

```md
    ![Alt text](image.png){width="42"}
```

#### Zoom Popup

Images too big to fit into main content can have <control>+</control> overlay control to open a popup with the "zoomed" variant.

<tabs>
<tab title="PNG">

For **PNG** images, provide an additional zoomed variant <path>image.zoomed.png</path> with this notation:

```md
    ![Alt text](image.png){thumbnail="true"}
```

</tab>

<tab title="SVG">

For **SVG** images, use this notation:

```md
    ![Alt text](image.svg){thumbnail-same-file="true"}
```

</tab>

</tabs>

## Table of Contents

The table of contents for the site is displayed in the tree view on the left-hand side of the site, and it is generated from the <path>ijs.tree</path> file.
The list can have nested items, which are displayed as child items in the table of contents.

If absolutely required, overriding the page title text to show in table of contents is possible via `toc-title` attribute.

### Placeholders

If a node does not have its `id` attribute specified, it will still appear in the table of contents but will be greyed out and not clickable.
It acts as a placeholder for a documentation item.
A placeholder is useful to keep track of what should be documented, but hasn't yet, and can be helpful to show readers that the topic exists, but isn't yet documented (Pull Requests always welcome!).

### Redirects

When renaming pages, redirects **must** be configured so existing bookmarks continue working.
All existing links in other topics must be updated.

Specify the previous path(s) including <path>.html</path> extension in `accepts-web-file-names` attribute:

```xml
<toc-element
    id="fundamentals.md"
    accepts-web-file-names="reference_guide.html,architectural_overview.html"/>
```
