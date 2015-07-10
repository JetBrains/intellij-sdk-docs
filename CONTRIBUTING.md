# Contributing

Thanks for contributing! Here are few useful things to know before submitting your Pull Request.

* Licensing - see [LICENSE.txt](LICENSE.txt)
* [Setting up your environment](#setting-up-your-environment)
* [Markup](#markup)
    * _SUMMARY.md
    * [Redirects](#redirects)
    * [Table of contents](#table-of-contents)
    * [Liquid tags and filters](#liquid-tags-and-filters)
    * [Syntax highlighting](#syntax-highlighting)
    * [Tables](#tables)
    * [Links](#links)
    * [Callouts](#notes-and-callouts)
    * [Images](#images)
* Style guide
* [A word on submodules](#a-word-on-submodules)

## Setting up your environment

This site is a [Jekyll](http://jekyllrb.com) site, which is a popular static site generator, written in Ruby. It can be hosted locally to ensure that any changes are correct. Once set up, running the site is as easy as calling `rake preview`.

### Prerequisites

In order to build the documentation site, you will need:

* Ruby 2 - Jekyll is a Ruby application
* Ruby 2 DevKit (for Windows)
* Python 2 - the [Pygments](https://github.com/tmm1/pygments.rb) gem uses Python for syntax highlighting.
* `gem install bundler` - the site uses [Bundler](http://bundler.io) to manage gem dependencies within the repo, rather than globally installing to the local operating system.

**OS X**

OS X comes with Ruby and Python already installed. The only steps required are:

* `gem install bundler`

**Windows**

* Install [Ruby 2](http://rubyinstaller.org) and the [Ruby 2 DevKit](http://rubyinstaller.org/downloads/) (one of the gems needs to build a native component)
    * After installing the DevKit, make sure to edit the `config.yml` file to point to the Ruby installation
* Install [Python 2](https://www.python.org/downloads/windows/) (2.7 is recommended)

This is made easier if you use [Chocolatey](https://chocolatey.org), a package manager for Windows:

* `choco install ruby`
* `choco install ruby2.devkit`
    * After installing the DevKit, make sure to edit the `config.yml` file to point to the Ruby installation.
    * By default, this is `C:\tools\DevKit\config.yml`
    * Add the line `- C:\tools\ruby21` (including the leading minus sign)
* `choco install python2`

**NOTE:** Before running the `rake bootstrap` step listed below, please run the `devkitvars.bat` file from the DevKit. E.g. `C:\tools\DevKit\devkitvars.bat`

### Bootstrapping the environment

1. Ensure Bundler is installed - `gem install bundler`
2. On Windows, ensure the `devkitvars.bat` file has been run in the current command prompt (e.g. `c:\tools\DevKit\devkitvars.bat`).
3. Clone the documentation site.
4. Initialise and update the `sdkdocs-template` submodule - `git submodule init` and `git submodule update`
5. `rake bootstrap` - this uses Bundler to download all required gems.
6. `rake preview` - this will build the site, and host it in a local webserver.

### Building and previewing the site

To build and test the site, simply run `rake preview`. This will build the site and host it, using the config provided. The URL of the hosted site is displayed on the screen, and depends on the `baseurl` field defined in `_config.yml`.

## Markup

By default, when building the site, all files are copied across. Some are excluded in the `_config.yml` and `sdkdocs-template/jekyll/_config-defaults.yml` files. The documentation files themselves are [Markdown](http://daringfireball.net/projects/markdown/) files (`.md`) that get automatically converted to HTML when the site is built.

However, only markdown files beginning with a [YAML](http://yaml.org) header are converted. If the markdown file doesn't contain a header, it won't be converted. In other words, to convert a `.md` file to HTML, it should look like this:

```md
---
---

# Introduction

Lorem ipsum...
```

The two lines at the top of the file are the markers of the YAML "front matter". Fields can be added in between these markers, and are used when generating the HTML. Typically, this header will be empty, although it is required by Jekyll (if omitted, the file isn't converted).

The YAML header can contain data that is used when generating the site. For example, the page title can be specified as a simple piece of markdown - `# Title`, or it can be specified in the YAML, and the page template will display it appropriately:

```md
---
title: The Title Of The Page
---

Lorem ipsum...
```

The YAML header can also include [redirect](#redirects) information.

### _SUMMARY.md

The table of contents is generated from the `_SUMMARY.md` file. It is a simple markdown list, with each item in the list being a link to another markdown page, either in the root folder, or sub-folders. The list can have nested items, which will be displayed as child items in the table of contents.

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

If a node doesn't have a link, but is just plain text, it will still appear in the table of contents, but will be greyed out and not clickable. It acts like a placeholder for a documentation item. This is useful to keep track of what should be documented, but hasn't yet, and can be useful to show readers that the topic exists, but isn't yet documented (Pull Requests always welcome!).

### Redirects

The documentation site is set up to include the `jekyll-redirect-from` plugin, which will generate "dummy" pages that automatically redirect to a given page. For example, to specify that the `index.html` page will be generated to redirect to `README.md`, the `README.md` file should include the following in the YAML header:

```md
---
redirect_from:
  - /index.html
---

# Introduction

Lorem ipsum...
```

This will create an `index.html` file that will automatically redirect to the generated `README.html` file. This is very useful to allow the site URL to automatically show the `README.html` file - `http://localhost:4001/foo-test/` will try to load `index.html`, which will automatically redirect to `README.html`.

It is also useful to redirect when renaming or moving files. Multiple redirects can be added to the YAML header.

### Table of contents

The site is configured to use the [Kramdown Markdown converter](http://kramdown.gettalong.org), which adds some extra features over traditional markdown, such as "attribute lists", which can apply attributes to the generated elements.

One useful attribute is `{:toc}`, which can be applied to a list item, which will get replaced with a list of links to header items. E.g. the following list item will be replaced by links to all of the header items in the page:

```md
* Dummy list item
{:toc}
```

Further Kramdown features are described on the [converter page](http://kramdown.gettalong.org/converter/html.html), and attribute lists are described on the [syntax page](http://kramdown.gettalong.org/syntax.html). Note that source code formatting is configured to use [GitHub Flavoured Mardown](https://help.github.com/articles/github-flavored-markdown/) and "code fences", see below.

### Liquid tags and filters

Jekyll uses the [Liquid](http://liquidmarkup.org) templating language to process files. This means standard Liquid tags and filters are available. There should be little need to use them however, as the Markdown format is already quite rich. See the [Jekyll site](http://jekyllrb.com/docs/templates/) for more details.

### Syntax highlighting

Source code can be represented by using [GitHub Flavoured Markdown](https://help.github.com/articles/github-flavored-markdown/) code fences, which are three back ticks:

    ```
    // Source code goes here...
    ```

Syntax highlighting can be applied by specifying the language after the first set of ticks:

    ```csharp
    // Some C# code
    ```

    ```java
    // Some Java code
    ```

Here is the list of [supported languages](https://github.com/github/linguist/blob/master/lib/linguist/languages.yml).

The site is also configured to highlight a range of files in the source code, by specifying `{start-end}` which is the start and end line of the highlighting:

    ```java{2-3}
    // Not highlighted
    // Highlighted
    // Highlighted
    // Not highlighted
    ```

### Tables

The Kramdown parser also supports tables. The syntax is to use the pipe (`|`) and minus symbols:

    ```md
    | Column 1 | Column 2 | Column 3 |
    |----------|----------|----------|
    | Blah     | Blah     | Blah     |
    ```

### Links

Links are handled as normal markdown links, and can be links to external sites, pages within the sites, or headings in the sites. When a Markdown header is converted to an HTML header, it is assigned an ID, so it can be linked, e.g. `## Introduction` will get the ID of `introduction`, and can be linked either in the same page `[click here](#introduction)` or cross page `[click here](page.html#introduction)`. The anchor name will be all lower case, and spaces are replaced with `-`, e.g. `## Page setup` becomes `#page-setup`.

* `[External site](http://example.org)` will link to an external site
* `[Other page in current directory](Page2.md)` will link to a page in the same directory as the current page. Note that the extension is `.md`, NOT `.html`.
* `[Page in another folder](/Folder2/Page2.md)` will link to a page in another folder. Note that the URL is navigating from root - this works even if the site is hosted in a sub-folder (e.g. this link will work for `http://localhost:4000/devguide/Folder2/Page2.html`). Relative links will also work (`../Folder2/Page2.md`).
* `[Link to section on another page](Page2.md#another-section)` will link to a heading on another page. The ID of the heading is generated by making the text lowercase and replacing spaces with `-`.
* `[Link to section on current page](#another-section)` will link to a heading on the current page.

### Notes and callouts

Notes and callouts can be specified using the blockquote syntax. The converter will look at the first following word to see if it is bold. If so, it will apply that as a callout style. For example:

    > **NOTE** This is a note

Will be displayed as a callout, styled as a "note". The other styles available for callouts are "note", "warning", "tip" and "todo".

### Images

Images can be included by adding the file directly to the repository and adding a link to the image like so:

    ```md
    ![Alt text](path-to-img.png)
    ```

Please downscale screenshots made at high resolution.

## A word on submodules

The `sdkdocs-template` repo is added as a submodule, and it also contains a submodule to the private `webhelp-template` repo. The `sdkdocs-template` repo contains build time scripts and compiled and minified JS and CSS that allow the site to run. The private `webhelp-template` repo contains the code to build the JS and CSS. It is currently closed source, but the plan is to make it open source at some point, in which case, it is likely the two repos will be merged.

After cloning, a submodule needs to be initialised and updated:

    ```
    git submodule init
    git submodule update
    ```

This will create a `.gitmodules` file, register a submodule in the `sdkdocs-template` folder, and check out the files. (Note that when a repo is added as a submodule, it doesn't get a `.git` folder, but instead gets a `.git` file that points to the location of the `.git` folder.

A submodule can be updated using normal git commands such as `git pull`. It can be switched to a different branch using `git checkout`, and any changes to the currently checked out revision need to be committed back into the main repo, as normal git commands. It is initially cloned at a specific revision, and not as part of a branch.update

If changes are made to the submodule, they should be made on a branch to a clone, and a Pull Request sent. Changes can be made and committed, and the hosting repo will need to commit a pointer to the current version of the submodule.

If there are any problems with the `sdkdocs-template`, please [raise an issue](https://github.com/JetBrains/sdkdocs-template/issues).

