<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# SDK Docs Style Guide

<link-summary>Writing and notation styleguide for SDK Docs.</link-summary>

This document describes the writing style used in authoring open-source IntelliJ Platform SDK documentation.
Before you begin, read this page thoroughly, as well as the [Code of Conduct](intellij-sdk-docs-original_CODE_OF_CONDUCT.md) and [License](https://github.com/JetBrains/intellij-sdk-docs/blob/main/LICENSE.txt) documents.
See also [](intellij-sdk-docs-original_CONTRIBUTING.md) for some general remarks.

For information about contributing to the IntelliJ Platform itself, visit [Contributing to the IntelliJ Platform](platform_contributions.md).

First and foremost, we should keep in mind our audience and their objectives:
_Someone reading technical content is usually looking to answer a specific question.
That question might be broad or narrowly-focused, but either way, our goal is to provide answers without distraction._

To verify grammar and correct spelling, it is highly recommended to use [Grazie Professional](https://plugins.jetbrains.com/plugin/16136-grazie-professional) plugin to highlight any issues on-the-fly in the IDE.

## Documentation Markup

The documentation project is using [Writerside](https://plugins.jetbrains.com/plugin/20158-writerside), so the plugin should be installed to have full support in the IDE.
The topic files themselves are [Markdown](https://github.github.com/gfm/) files (<path>*.md</path>) using some Writerside-specific custom tags (see below).

### Page Format

Each Markdown file **must** start with a copyright notice, formatted using HTML comment notation:

```html
<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->
```

It **must** be followed by a header defining its title using a level 1 heading:

```md
# Contributing to the IntelliJ Platform SDK
```

The page title should be as concise as possible, so it can be reused in the [](#table-of-contents) as is.

#### Excerpt

Every page **should** provide a short excerpt (usually one sentence) using the dedicated `<link-summary>` tag before the main page contents:

```html
<link-summary>Defining groups of related settings.</link-summary>
```

#### Highlighted Links

A page can highlight related topics and other important links before the actual content using the `<tldr>` tag.
Links must be grouped using "**Bold Category Name**: link1, link2, \[...]" ([Example](language_and_filetype.md)).

Use _Reference_ to link to other topics, _Code_ to link to code/files, _UI Guidelines_ for links to [UI Guidelines](ui_guidelines_welcome.topic), and _Product Help_ for links to [IntelliJ IDEA Help](https://www.jetbrains.com/help/idea).

#### Introductory Text

Do **not** use headings like _Introduction_, _Overview_, etc. for any introductory text.

## Content Style

### Terminology

Consistent terminology helps the reader grasp new concepts more quickly:

* The open-source code in the GitHub repository `intellij-community` is known as the IntelliJ Platform.
  Use the full phrase in the SDK documentation.
* IDEs based on the IntelliJ Platform are described as _IntelliJ Platform-based IDEs_.
  Once that term is used on a page, authors may use _IDEs_.
* When referring to JetBrains products, always use the full name such as _IntelliJ IDEA Ultimate Edition_.
  However, only use product names when extensibility or functionality is particular to a product.

> TODO/todo comments are discouraged in the main branch of `intellij-sdk-docs`.
> There are always exceptions, but the best practice is to resolve all TODOs before the final review.
> If immediate resolution isn't possible, write a YouTrack [SDK Issue](https://youtrack.jetbrains.com/issues/IJSDK) that captures the TODO and remove the comment from the document.
>
{title="Do not use TODOs"}

### Text Format Conventions

Start every sentence on a new line.
For very long sentences, add additional line breaks after `,`, `:` or other sensible places.
Very long [links](#links) should also be on a separate line.

Consistent text styles are used to standardize references and keywords:

* Menu paths are wrapped using `<ui-path>` with pipe characters separating each level: `<ui-path>Settings | Editor</ui-path>`: <ui-path>Settings | Editor</ui-path>
  Inside tables, use `&#124;` instead of `|` to prevent escaping problems.
* User interface element names like labels, buttons, checkboxes, etc. are wrapped using `<control>`: `Press <control>Continue</continue>`: Press <control>Continue</control>
* Non-code keywords and quotations, or the names of non-code files are formatted as italic style: \_Theme\_ (_Theme_), \_README.md\_ (_README.md_.)
  Examples of this file type include _LICENSE.txt_ and _README.md_.
* Code keywords and class names are formatted as code style: \`interface\`: `interface`, \`AnAction\`: `AnAction`, \`name\` attribute: `name` attribute.
* Filenames are wrapped using `<path>`: `<path>build.gradle.kts</path>` <path>build.gradle.kts</path>.
* File formats are shown as all capital letters: PNG and XML.
* Filename extensions are not capitalized when part of a full filename, path, or URL: <path>plugin.xml</path>.
* When using \$PLACEHOLDER\$ in non-code parts, it must be escaped: `<path>\$PLACEHOLDER\$/somePath</path>`.
* Keyboard shortcuts are wrapped using `<shortcut>`: `press <shortcut>Alt+Insert</shortcut>` becomes "press <shortcut>Alt+Insert</shortcut>".

See [Guidelines for Highlighting Syntax](#guidelines-for-highlighting-syntax) for best practices for representing code fragments.
See [Links to IntelliJ Platform Source](#links-to-intellij-platform-source) for more details about representing names of source files in links.

### Links

Links are handled as standard Markdown links and can be anchored to external sites, pages within the site, or headings in the pages.

When a Markdown header is converted to an HTML header, it is assigned an ID so that it can be linked.
For example, `## Basics` gets the ID of `basics`, and can be linked either on the same page or cross-page as described below.

In some cases (e.g., the same heading text appears multiple times on the same page), it is necessary to specify a distinct ID manually:

```md
## Task 1
### Properties
{#task1-properties}
[...]

## Task 2
### Properties
{#task2-properties}
[...]
```

#### General Links

General Markdown links have the default Markdown link style:

* `[Gradle](https://gradle.org)`{disable-links} ([Gradle](https://gradle.org)) links to an external site, such as companies, articles, etc.
  If the URL contains a `%` character, append `{ignore-vars="true"}`.
* Linking to pages and page sections within the SDK documentation:
    * `[Page Title](page.md)`{disable-links} or `[](page.md)` (use page title as link text) links to an SDK doc page (all located under <path>/topics</path>).
      Note that the extension is <path>.md</path>, _NOT_ <path>.html</path>.
    * Specific _sections_ on pages in the SDK documentation are linked by using section anchors.
      The anchor name will be all lower case, and spaces are replaced with `-`, e.g. `## Page setup` becomes `#page-setup`.
      Once the anchor (`#`) character of the link is entered, the IDE code completion feature shows the available sections.
        * `[Link to a section on the current page](#another-section)`{disable-links} links to a heading on the current page.
        * `[Link to the section on another page](other_page.md#another-section)`{disable-links} links to a heading on another page.

  If the desired link label is the same as an SDK doc page or section title, leave the label part empty, e.g., `[](test-page.md)`{disable-links} or `[](test-page.md#section-1)`{disable-links}.
  The empty link label will be automatically filled with the actual page or section title.

#### Links to IntelliJ Platform Source

Links to files in the IntelliJ Platform ([`intellij-community`](%gh-ic%/README.md)) repository use the `%\gh-ic%` prefix instead of the full URL to the repository.
Links to files in source code packages in other GitHub repositories follow the same rules, except the links use a different custom `gh-...` prefix defined in <path>v.list</path>.

* `[README.md](%\gh-ic%/README.md)`{disable-links} links to any general, non-code information files. ([README.md](%gh-ic%/README.md))
  Examples of this file type include _LICENSE.txt_ and _README.md_.
* `[`IdeaPlugin.xml`](%\gh-ic%/community-resources/resources/META-INF/IdeaPlugin.xml)`{disable-links} links to declarative source code files, use `code` style. ([`IdeaPlugin.xml`](%gh-ic%/community-resources/resources/META-INF/IdeaPlugin.xml))
  Examples of this file type include: `settings.gradle`, `plugin.xml` or `theme_basics.theme.json`.
* `[`\`AnAction\``](%\gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)`{disable-links} links to source files for code objects like interfaces and classes, use `code` style but without the file extension. ([`AnAction`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java))
  Examples of this file type include Java and Kotlin sources.
    * Note the use of \`\` characters surrounding the class name in the link.
    * When linking to an API in this manner, the FQN isn't necessary in the link.
    * No file extension (*.java, *.kt, *.py, etc.) is used by convention.
    * Be judicious when using such links.
      Generally, only one link is needed for a given file on a documentation page.

### Guidelines for Highlighting Syntax

In-paragraph code fragments and IntelliJ Platform APIs are formatted according to the following rules.

#### Code

* Avoid using qualifiers like "`Foo` interface" or "`Foo` abstract class".
  Instead, refer to `Foo`.
* The FQN is used for the first reference to an interface, class, or package on a page.
  Rather than `AnAction`, introduce it as `com.intellij.openapi.actionSystem.AnAction`.
  Later references on the page can be `AnAction`.
  Exception: the FQN is not used with a GitHub [link](#links).
* Method names always use empty parentheses: "call `bar()` to apply."
  Method names are prefixed with the class/interface name when needed for clarity: `Foo.bar()`.

#### Extension Points

Use `<include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.annotator"/></include>` snippet to introduce EP:

- <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.annotator"/></include>

Use `<include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotator"/></include>` for variant without "extension point" suffix:

- <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotator"/></include>

#### XML

For XML elements, use the tag notation with syntax highlighting: `<idea-version>`.
Attributes are shown with syntax highlighting, and attribute values are shown in quotes: `since-build="191"`

### Source Code

Source code is represented by using code fences, which are three backticks.

Syntax highlighting is applied by specifying the language after the first set of ticks:

```
    ```xml
    <tagName attribute="value">XML Text</tagName>
    ```
```

Supported languages include `xml`, `java`, `kotlin`, `groovy`, `bash`, `md`, `php`, and `text` for plaintext.

> Source code blocks must have one blank line before and after them and must have a language specification for highlighting (use `text` as fallback).
>
{style="note"}

Whole files can be imported on a page using the `src` attribute after code fences specifying the full path relative to the <path>code_samples</path> root folder.

`{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java"}`

The advantage is the code can come from the `code_samples` directory, so it will be live code that isn't silently stale.

The disadvantage is the file may contain a large class, too large for the documentation page to be useful.
If possible, use `include-symbol="ClassName"` to show only the class body without any headers and imports.
To include only a specific method, specify `include-symbol="methodName"` additionally.

In any case, keep code samples concise and avoid any unnecessary "surrounding" code or import statements.

### Tables

The syntax is to use the pipe (`|`) and hyphen symbols:

```md
    | Column 1 | Column 2 | Column 3 |
    |----------|----------|----------|
    | Blah     | Blah     | Blah     |
```

Use `&#124;` instead of `|` to prevent escaping problems for `<ui-path>` elements inside cells.

Use `<p>Line 1</p><p>Line 2</p>` for multi-line content in a cell.

### Notes and Callouts

Notes and callouts can be specified using the blockquote syntax.
The converter looks at the `type` attribute specified after the text block.
If so, it applies a callout style.
The example below will be displayed as a callout, styled as a "note":

```md
> This is a simple note.
>
{style="note"}
```

> This is a simple note.
>
{style="note"}

The styles available for callouts are:

* `tip`—Information that makes the reader more productive (Default).
* `note`—Information that is important for the reader to understand.
  This callout is reserved for essential points and concepts.
* `warning`—Information that is critical for the user to understand to prevent failures or errors.

Complex callouts can also specify the `title` attribute:

```md
> This is a note.
> We have a lot of text.
> Don't make everyone read it fully by adding a good title.
>
{title="A useful title"}
```

> This is a note.
> We have a lot of text.
> Don't make everyone read it fully by
> adding a good title.
>
{title="A useful title"}

### Images

Every page typically has a dedicated subdirectory within root <path>/images</path>.

Images in this documentation are generally screenshots.
For consistency, images should be 296, 460, or 706 pixels wide.
The preferred image format is PNG at a resolution of 144 DPI.
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

Images too big to fit into the main content can have <control>+</control> overlay control to open a popup with the "zoomed" variant.

<tabs>
<tab title="PNG">

For **PNG** images, provide an additional zoomed variant <path>image.zoomed.png</path> with this notation:

```md
    ![Alt text](image.png){thumbnail="true"}
```

</tab>

<tab title="SVG">

For **SVG** images, use this notation:

```
    ![Alt text](image.svg){thumbnail-same-file="true"}
```

</tab>

</tabs>

### Other

Use a concise and neutral voice when describing topics:
   - **Avoid:** "A custom icon for files with a substituted language would be a great addition to the plugin. It is easy as implementing the `xyz` extension point."
   - **Prefer:** "To add a custom icon for files with a substituted language, implement the `xyz` extension point."

Avoid directly addressing the reader and the author with "you", "your", "we", etc.
   - **Avoid:** "Add a dependency in your plugin descriptor."
   - **Prefer:** "Add a dependency in the plugin descriptor."

## Table of Contents

The table of contents for the site is displayed in the tree view on the left side of the site, and it is generated from the <path>ijs.tree</path> file.
The list can have nested items, which are displayed as child items in the table of contents.

If absolutely required, overriding the page title text to show in the table of contents is possible via the `toc-title` attribute.

### Placeholders

If a node does not have its `id` attribute specified, it will still appear in the table of contents but will be greyed out and not clickable.
It acts as a placeholder for a documentation item.
A placeholder is useful to keep track of what should be documented but hasn't yet, and can be helpful to show readers that the topic exists but isn't yet documented (Pull Requests always welcome!).

### Redirects

When renaming pages, redirects **must** be configured so existing bookmarks continue working.
All existing links in other topics must be updated.

Specify the previous path(s) including <path>.html</path> extension in `accepts-web-file-names` attribute:

```xml
<toc-element
        id="themes_getting_started.md"
        accepts-web-file-names="themes.html,themes-intro.html"/>
```
