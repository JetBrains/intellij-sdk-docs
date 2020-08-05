---
title: Style Guide for SDK Documents
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This document describes the writing style used in authoring open-source IntelliJ Platform SDK documentation.
Before you begin, please read this page thoroughly, as well as the [Code of Conduct](/CODE_OF_CONDUCT.md) and [License](https://github.com/JetBrains/intellij-sdk-docs/blob/master/LICENSE.txt) documents.
For information about contributing to the IntelliJ Platform itself, please visit [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

First and foremost, we should keep in mind our audience and their objectives:
_Someone reading technical content is usually looking to answer a specific question. 
That question might be broad or narrowly-focused, but either way, our goal is to provide answers without distraction._

The style of the _Intellij Platform SDK_ documentation is captured by using a markup language named [_Markdown_](https://github.github.com/gfm/).
The following sections describe the SDK documentation style in terms of the Markdown formats:

* bullet list
{:toc}

## Documentation Markup
By default, when building the site, all files are copied to the destination `_site` folder. 
Some files are excluded in the `_config.yml` and `sdkdocs-template/jekyll/_config-defaults.yml` files. 
The documentation files themselves are [Markdown](https://github.github.com/gfm/) files (`*.md`) that get automatically converted to HTML when the site is built.

### Liquid tags and filters
Jekyll uses the [Liquid](https://shopify.github.io/liquid/) templating language to process files. 
This process means standard Liquid tags and filters are available. 
There should be little need to use them, however, as the Markdown format is already quite rich. 
See the [Jekyll site](https://jekyllrb.com/docs/liquid/) for more details.

### Page Format
Only Markdown files beginning with a [YAML](https://yaml.org) header are converted to HTML. 
If the Markdown file doesn't contain a header, it won't be converted. 
In other words, to convert a `.md` file to HTML, it should look like this:

```yaml
---
---

Lorem ipsum...
```

The two lines at the top of the file are the markers of the YAML _front matter_. 
Fields can be added in between these markers, and are used when generating the HTML. 
Typically, this header is empty, although it is required by Jekyll (if omitted, the file isn't converted).

The YAML header can contain data that is used when generating the site. 
For example, the page title can be specified as a simple piece of Markdown - `# Title`, or it can be specified in the YAML, and the page template displays it appropriately:

> **NOTE** The page copyright notice should appear immediately below the YAML header, as shown below. Use the [IntelliJ Platform SDK](https://github.com/JetBrains/intellij-sdk-docs/tree/master/.idea/copyright) copyright profile. 

```yaml
---
title: The Title Of The Page
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Lorem ipsum...
```

The YAML header can also include [redirect](#redirects) information.

Line spacing around headings in Markdown files generally doesn't affect the HTML conversion, but it does make Markdown pages more readable for authors:
* No space between a heading and the first content below it.
* One space before a heading if it is the same level or a sub-heading of the previous section.
* Two spaces before a heading that is higher-level than the heading of the previous section.

### Redirects
The documentation site is set up to include the [jekyll-redirect-from](https://github.com/jekyll/jekyll-redirect-from) plugin, which generates "dummy" pages that automatically redirect to a given page. 
For example, to specify that the `index.html` page be generated to redirect to `README.md`, the `README.md` file should include the following in the YAML header:

```yaml
---
redirect_from:
  - /index.html
---

Lorem ipsum...
```

The redirect will create an `index.html` file that will automatically redirect to the generated `README.html` file. 
Redirects enable the site URL to automatically show the `README.html` file - `http://localhost:4001/foo-test/` will try to load `index.html`, which will automatically redirect to `README.html`.

It is also useful to redirect when renaming or moving files. 
Multiple redirects can be added to the YAML header.

> **NOTE** Please update all existing internal links to the new page location.

### Table of Contents for a Page
The site is configured to use the [Kramdown Markdown converter](https://kramdown.gettalong.org), which adds some extra features over traditional Markdown.
For example, "attribute lists" that can apply attributes to the generated elements.

One useful attribute is `{:toc}`, which can be applied to a list item, which will get replaced with a list of links to header items. 
E.g., the following list item will be replaced by links to all of the header items in the page:

```md
  * Dummy list item
  {:toc}
```

## Content Style
Further Kramdown features are described on the [converter page](https://kramdown.gettalong.org/converter/html.html), and attribute lists are described on the [syntax page](https://kramdown.gettalong.org/syntax.html). 
Note that source code formatting is configured to use [GitHub Flavoured Markdown](https://help.github.com/articles/github-flavored-markdown/) and "code fences", see below.

### Terminology
Consistent terminology helps the reader grasp new concepts more quickly:
* The open-source code in the GitHub repository `intellij-community` is known as the _IntelliJ Platform_.
  Use the full phrase in the SDK documentation.
* IDEs based on the IntelliJ Platform are described as _IntelliJ Platform-based IDEs_.
  Once that term is used on a page, authors may use _IDEs_ 
* When referring to JetBrains products always use the full name such as _IntelliJ IDEA Ultimate Edition_.
  However, only use product names when extensibility or functionality is particular to a product.

### Text Format Conventions
Consistent text styles are used to standardize references and keywords:
* Menu paths are formatted as bold with pipe characters separating each level: \*\*Settings/Preferences \\\| Editor\*\* (**Settings/Preferences \| Editor**)
  Menu paths to settings always start with "Settings/Preferences" to cover all platforms.
* Non-code keywords and quotations, or the names of non-code files, are formatted as italic style: \_UI Theme\_ (_UI Theme_), \_README.md\_ (_README.md_.) 
  Examples of this file type include: _LICENSE.txt_ and _README.md_. 
* Code keywords, or the names of files containing source code, are formatted as code style: \`interface\` (`interface`), \`plugin.xml\` (`plugin.xml`), \`AnAction\` (`AnAction`) 
  Examples of this file type include: `build.gradle`, `AnAction.java` or `theme_basics.theme.json`.
* File formats are shown as all capital letters: PNG and XML.
* File name extensions are not capitalized when part of a full file name, path, or URL: `filename.ext`.
* Represent keyboard shortcuts with HTML elements: `press <kbd>Alt</kbd>+<kbd>Insert</kbd>` becomes "press <kbd>Alt</kbd>+<kbd>Insert</kbd>"
* See [Guidelines for Highlighting Syntax](#guidelines-for-highlighting-syntax) for more best practices for representing code fragments.
* See [Links to IntelliJ Platform Source](#links-to-intellij-platform-source) for more details about representing names of source files in links.

### Links
Links are handled as standard Markdown links and can be anchored to external sites, pages within the sites, or headings in the sites. 
When a Markdown header is converted to an HTML header, it is assigned an ID so that it can be linked.
For example, `## Introduction` gets the ID of `introduction`, and can be linked either in the same page or cross-page as described below. 

#### General Links
General Markdown links have the default Markdown link style:
* `[Gradle](https://gradle.org)` ([Gradle](https://gradle.org)) links to an external site, such as companies, articles, etc.
* Linking to pages within the SDK documentation:
  * `[SDK doc page in current directory](Page2.md)` links to an SDK doc page in the same directory as the current page. 
    Note that the extension is `.md`, _NOT_ `.html`.
  * `[SDK page in another folder](/Folder2/Page2.md)` links to a page in another folder. 
    Note the URL is navigating from the project root.
This format works even if the site is hosted in a sub-folder. 
    Relative links also work (`../Folder2/Page2.md`).
* Linking to specific _sections_ on pages in the SDK documentation.
  The anchor name will be all lower case, and spaces are replaced with `-`, e.g. `## Page setup` becomes `#page-setup`.
  Once the anchor (`#`) character of the link is entered, the IDE code completion feature shows the available sections. 
  * `[Link to a section on the current page](#another-section)` links to a heading on the current page.
  * `[Link to the section on another page](Page2.md#another-section)` links to a heading on another page. 

#### Links to IntelliJ Platform Source
Links to files in the IntelliJ Platform (`intellij-community`) repository use `upsource:///` instead of the full URL to the repository.
The `upsource:///` URI effectively points to the root of the `intellij-community` repository.
* `[_README.md_](upsource:///README.md)` links to general, non-code information files, use _italic_ style. ([_README.md_](upsource:///README.md))
  Examples of this file type include _LICENSE.txt_ and _README.md_. 
* `[`\`plugin.xml\``](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)` links to declarative source code files, use `code` style. ([`plugin.xml`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java))
  Examples of this file type include: `settings.gradle`, `plugin.xml` or `theme_basics.theme.json`.
* `[`\`AnAction\``](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)` links to source files for code objects like interfaces and classes.  
  Examples of this file type include: `Project.java`, and `HelloAction.kt`.
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
* Use the FQN when first introducing an extension point (EP) on a page.
  Rather than `stubIndex`, introduce `com.intellij.stubIndex`.
  Subsequent mentions on the page can be `stubIndex`.
  Exception: the FQN is not used when an extension point is introduced in an upsource [link](#links).
* For XML elements, use the tag notation with syntax highlighting: `<idea-version>`.
  Attributes are shown with syntax highlighting, and attribute values are shown in quotes: `since-build="191"`
* Method names always use empty parentheses: "call `bar()` to apply." 
  Method names are prefixed with the class/interface name when needed for clarity: `Foo.bar()`.

### Source Code
Source code can be represented by using [GitHub Flavoured Markdown](https://help.github.com/articles/github-flavored-markdown/) code fences, which are three backticks:  

        ```
            // Source code goes here...
        ```

> **NOTE** Source code blocks must have one blank line before and after them.

Syntax highlighting can be applied by specifying the language after the first set of ticks:  

        ```csharp
            // Some C# code
        ```

        ```java
            // Some Java code
        ```  

Here is the list of [supported languages](https://github.com/jneen/rouge/wiki/List-of-supported-languages-and-lexers), and also [Kotlin](https://kotlinlang.org), of course. 

Whole files can be imported on a page using an include statement within code fences.
Search the documentation for `% include` to get the correct include statement syntax.

        ```java 
            // Include statement 
        ``` 

The advantage is the code can come from the `code_samples` directory, so it will be live code that isn't silently stale.
The disadvantage is the file may contain a large class, too large for the documentation page to be useful. 
In any case, please keep code samples concise and avoid any unnecessary "surrounding" code or import statements. 


<!-- //TODO: Not currently supported by rouge, or by the site's CSS

The site is also configured to highlight a range of files in the source code, by specifying `{start-end}` which is the start and end line of the highlighting:

    ```java{2-3}
    // Not highlighted
    // Highlighted
    // Highlighted
    // Not highlighted
    ```
-->

### Tables
The Kramdown parser also supports tables. 
The syntax is to use the pipe (`|`) and minus symbols:

    ```md
    | Column 1 | Column 2 | Column 3 |
    |----------|----------|----------|
    | Blah     | Blah     | Blah     |
    ```


### Notes and Callouts
Notes and callouts can be specified using the blockquote syntax. 
The converter looks at the first following word to see if it is bold. 
If so, it applies a callout style. 
The example below will be displayed as a callout, styled as a "note":

    > **NOTE** This is a note

> **NOTE** This is a note
    

The styles available for callouts are:
* TODO - Do not use in the SDK documentation.
  Instead, temporarily embed `[//]: # (TODO: description)` comments. 
  This format isn't visible in the HTML but is recognized by IDE inspections.
* TIP - Information that makes the reader more productive.
* NOTE - Information that is important for the reader to understand.
  This callout is reserved for important points and concepts.
* WARNING - Information that is critical for the user to understand to prevent failures or errors.

> **TIP** TODO/todo comments are discouraged in the master branch of `intellij-sdk-docs`. 
> There are always exceptions, but the best practice is to resolve all TODOs before the final review.
> If immediate resolution isn't possible, write a YouTrack [SDK Issue](https://youtrack.jetbrains.com/issues/IJSDK) that captures the TODO and remove the comment from the document.

### Images
Images can be included by adding the file directly to the `intellij-sdk-docs` repository.
Each subject directory typically has an `img` subdirectory.
Keep images close to the corresponding documentation file in the repository directory structure.

Images in this documentation are generally screenshots.
The preferred image format is PNG at 144 DPI resolution.
A resolution of 72 DPI is acceptable but may look blurry on high-resolution monitors.

It is important to reduce the size of image files to prevent bloating the repository and impacting the performance of the documentation site.
Resize an image to be nearly the desired width on a documentation page.
Reducing an image's dimensions is the most effective way to reduce image file size. 
Also, optimize the image files using a tool such as the [PNG optimizer](https://plugins.jetbrains.com/plugin/7942-png-optimizer).

Images are embedded in a document by adding a Markdown link to the image like so:

    ![Alt text](path-to-img.png)

If the width of an image needs to be adjusted, use Kramdown markup:

    ![Alt text](path-to-img.png){:width="42px"}


## _SUMMARY Site Table of Contents
The table of contents for the site is displayed in the tree view on the left-hand side of the site, and it is generated from the `_SUMMARY.md` file. 
It is a simple Markdown list, with each item in the list being a link to another Markdown page, either in the root folder or sub-folders. 
The list can have nested items, which are displayed as child items in the table of contents.

> ***WARNING*** Every Markdown (`*.md`) document within the SDK repository (`intellij-sdk-docs`) must have an entry in `_SUMMARY.md`.

```md
# Summary

* [Introduction](README.md)
* [About This Guide](Intro/About.md)
    * [Key Topics](Intro/KeyTopics.md)
```

The contents can be split into "parts" by separating the list into several lists, each new list starting with a level 2 heading (`##`).

```md
# Summary

* [Introduction](README.md)
* [About This Guide](Intro/About.md)
    * [Key Topics](Intro/KeyTopics.md)

## Part I - Extending the Platform
* [Getting Started](Docs/GettingStarted.md)
* ...
```

If a node doesn't have a link but is just plain text, it will still appear in the table of contents but will be greyed out and not clickable. 
It acts as a placeholder for a documentation item. 
A placeholder is useful to keep track of what should be documented, but hasn't yet, and can be useful to show readers that the topic exists, but isn't yet documented (Pull Requests always welcome!).
