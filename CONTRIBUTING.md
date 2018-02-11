---
title: Contributing
---

感谢您的贡献！ 在提交您的合并请求之前，请先了解一些有用的信息。

* Licensing - see [LICENSE.txt](LICENSE.txt)
* [Contributing to the IntelliJ Platform](#contributing-to-the-intellij-platform)
* [Setting up your environment](#setting-up-your-environment)
    * [Developing with Docker](#developing-with-docker)
    * [Developing locally](#developing-locally)
* [Markup](#markup)
    * [_SUMMARY.md](#summarymd)
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

## Contributing to the IntelliJ Platform

本文档描述了我们对IntelliJ SDK Docs的贡献准则。 有关对IntelliJ平台作出贡献的信息，请访问 [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

## Setting up your environment

这个站点通过 [Jekyll](http://jekyllrb.com) 运行，这是一个流行的静态站点生成器，用Ruby编写。 它可以在本地托管，以确保任何更改都是正确的。 一旦建立，运行该网站就像调用 “rake preview” 一样简单。

或者，该网站也可以托管在 [Docker container](https://www.docker.com) 中。 在Mac和Windows上，这意味着该网站托管在虚拟机中。 Docker维护这个容器，根据 [`Dockerfile`](Dockerfile) 中的指令构建它。 所有依赖项（Ruby等）在构建映像时自动安装，这减少了手动配置步骤。 Docker镜像也用于构建 [published site](http://www.jetbrains.org/intellij/sdk/docs/index.html)，因此它是一个已知的工作环境。

### Developing with Docker

按照以下步骤操作Docker：

* 首先，使用 [Docker Toolbox](https://www.docker.com/docker-toolbox) 来安装Docker。
* 在 Windows 和 Mac 上，启动 Docker 主机虚拟机（启动“Docker快速入门终端”或运行“Kitematic”）。有关更多详细信息，请参阅 [docker.com](https://www.docker.com) 上的入门指南）。
* 克隆 [`intellij-sdk-docs`](https://github.com/JetBrains/intellij-sdk-docs) 到本地, 然后 [initialise the `sdkdocs-template` submodule](#a-word-on-submodules).
* 将当前目录更改为git仓库的父目录。
* Run `docker build -t intellij-sdk-docs .`. 这将从当前文件夹构建码头图像，并为其添加标签 `intellij-sdk-docs`。
    * 请注意，这必须从设置了各种 `DOCKER_ *` 环境变量的命令提示符运行，例如 Docker 快速入门终端。
* Run `docker run -p 4000:4000 -v $PWD:/usr/src/app intellij-sdk-docs`. This command will:
    * 启动一个叫 `intellij-sdk-docs` 的容器.
    * 将Docker容器中的端口4000转发到docker客户端上的端口4000.

    >**注意** 对于 Windows 和 Mac，这意味着 docker 容器的端口 4000 被转发到 docker 虚拟机的端口 4000，而不是 `localhost`。 对于Linux，docker 客户端是主机，所以 `localhost:4000` 被转发到 docker 容器上的端口4000。
    > 为了从 Windows 或 Mac 中打开容器的端口 4000，必须命中 docker 客户端（虚拟机）的IP地址。 使用 `docker-machine ip default` 来获取docker客户端的IP地址。 使用 `X.X.X.X：4000` 命中虚拟机中的客户端，虚拟机又映射到容器的端口4000。
    >
    > 或者，修改虚拟机的设置，将端口4000自动转发到`localhost`。 看到这个  [blog post](http://acaird.github.io/computers/2014/11/16/docker-virtualbox-host-networking) 的更多细节。
 
    * 将当前目录（`$ PWD` 是一个Unix风格的环境变量，可以在Windows上使用 `％CD％`，或者指定完整路径）作为docker容器内的 `/usr/src/app` 。 这意味着docker镜像会将 “intellij-sdk-docs” 视为文件夹 `/usr/src/app`。

    > **注意** 如果在 msys bash脚本（例如“Docker Quickstart终端”）上的Windows上运行，则本地文件夹的路径需要正确转义，否则msys环境会将路径转换为标准Windows路径， 你会看到一个错误，例如 `invalid value "C:\\Users\\...;C:\\Program Files\\Git\\usr\\src\\app" for flag -v`。 为了解决这个问题，用双斜杠（例如， `-v //c/Users/...` 或者 `docker run -p 4000:4000 -v /$PWD:/usr/src/app intellij-sdk-docs`（注意 `$PWD` 之前的斜杠`）。

    * 运行Dockerfile的 `CMD` 指令中特定的命令，该命令既运行 `rake bootstrap`（确保安装了所有先决条件），又运行 `rake preview` ，它构建站点并开始托管它。
* 最后，您可以通过访问 [http://localhost:4000/intellij/sdk/docs/](http://localhost:4000/intellij/sdk/docs/) 访问新创建的站点，或者使用IP地址 泊坞窗客户端虚拟机（请参阅上面的注释）。

### Developing locally

为了构建文档站点，您需要：

* Ruby 2 - Jekyll是一个Ruby应用程序。
* Ruby 2 DevKit (for Windows) - 一些Jekyll的依赖关系需要编译，并且需要安装DevKit。
* `gem install bundler` - 该站点使用 [Bundler](http://bundler.io) 管理回购库中的gem依赖项，而不是全局安装到本地操作系统。 运行此命令以全局安装Bundler工具集。

**OS X**

OS X已经安装了Ruby。 所需的唯一步骤是：

* `gem install bundler`

**Windows**

* 安装  [Ruby 2](http://rubyinstaller.org) 和[Ruby 2 DevKit](http://rubyinstaller.org/downloads/) （其中一个gem需要构建本地组件）
    * 在安装DevKit之后，确保编辑 `config.yml` 文件以指向Ruby安装

如果使用Windows的包管理器 [Chocolatey](https://chocolatey.org)

* `choco install ruby`
* `choco install ruby2.devkit`
    * 在安装DevKit之后，确保编辑 `config.yml` 文件指向Ruby安装。    
    * 默认情况下,  `C:\tools\DevKit\config.yml`
    * 添加行 `- C:\tools\ruby21`  包括前导减号）

> **注意** 在运行下面列出的 `rake bootstrap` 步骤之前，请运行DevKit中的 `devkitvars.bat` 文件。 例如。`C:\tools\DevKit\devkitvars.bat`

### Bootstrapping the environment

1. 确保Bundler已安装 - `gem install bundler`
2. 在Windows上，确保 `devkitvars.bat` 文件已经在当前的命令提示符下运行（例如 `c:\tools\DevKit\devkitvars.bat`）。
3. 克隆文档站点。
4. 初始化并更新 `sdkdocs-template` 子模块 - `git submodule init` 然后 `git submodule update`
5. `rake bootstrap` - 这使用Bundler下载所有需要的 bootstrap。
6. `rake preview` - 这将构建该网站，并将其托管在本地网络服务器中。

### Building and previewing the site

要建立和测试网站，只需运 `rake preview`。 这将建立网站，并使用提供的配置托管它。 托管网站的URL显示在屏幕上，并取决于 `_config.yml` 中定义的 `baseurl` 字段。

## Markup

默认情况下，在构建站点时，所有文件都被复制到目标 `_site` 文件夹。 一些文件被排除在 `_config.yml` 和 `sdkdocs-template/jekyll/_config-defaults.yml` 文件中。 文档文件本身是[Markdown](http://daringfireball.net/projects/markdown/)  文件（`.md`），在构建站点时会自动转换为HTML。

但是，只有以 [YAML](http://yaml.org) 标头开始的文件才会被转换。 如果文件不包含标题，它将不会被转换。 换句话说，要将 `.md` 文件转换为HTML，它应该如下所示：

```md
---
---

# Introduction

Lorem ipsum...
```

文件顶部的两行是YAML“前端”的标记。 字段可以添加在这些标记之间，并在生成HTML时使用。 通常情况下，这个头文件将是空的，尽管它是 Jekyll 需要的（如果省略，文件不会被转换）。

YAML 标题可以包含生成站点时使用的数据。 例如，页面标题可以被指定为一个简单的标记 - `# Title`，或者可以在 YAML 中指定，页面模板会正确显示：

```md
---
title: The Title Of The Page
---

Lorem ipsum...
```

YAML标题还可以包含  [redirect](#redirects) 信息。

### _SUMMARY.md

该站点的内容列表显示在站点左侧的树形视图中，并且由 `_SUMMARY.md` 文件生成。 这是一个简单的列表，列表中的每个项目都是指向另一个页面的链接，位于根文件夹或子文件夹中。 该列表可以具有嵌套的项目，它们将在内容列表中显示为子项目。

```md
# Summary

* [Introduction](README.md)
* [About This Guide](Intro/About.md)
    * [Key Topics](Intro/KeyTopics.md)
```

可以通过将列表分成几个列表来将内容拆分成 “部分”，每个新列表以2级标题（`##`）开始。

```md
# Summary

* [Introduction](README.md)
* [About This Guide](Intro/About.md)
    * [Key Topics](Intro/KeyTopics.md)

## Part I - Extending the Platform
* [Getting Started](Docs/GettingStarted.md)
* ...
```

如果节点没有链接，但是只是纯文本，它仍然会出现在目录中，但是会变灰，不能点击。 它像一个文档项目的占位符。 这对于追踪应该记录的内容是有用的，但是还没有，并且可以向读者显示该主题存在但是还没有记录（总是欢迎拉请求）。

### Redirects

文档站点设置为包括 [jekyll-redirect-from](https://github.com/jekyll/jekyll-redirect-from) 插件，该插件将生成自动重定向到给定页面的“虚拟”页面。 例如，要指定生成 `index.html` 页面以重定向到 `README.md`，`README.md` 文件应该在 YAML 头文件中包含以下内容：

```md
---
redirect_from:
  - /index.html
---

# Introduction

Lorem ipsum...
```

这将创建一个 `index.html` 文件，将自动重定向到生成的 `README.html` 文件。 这对于允许网站URL自动显示 `README.html` 文件非常有用 - `http://localhost:4001/foo-test/`  将尝试加载 `index.html`，它会自动重定向到 `README.html`。

重命名或移动文件时重定向也很有用。 多个重定向可以添加到YAML头。

### Table of contents

该网站被配置为使用 [Kramdown Markdown converter](http://kramdown.gettalong.org)，它在传统降价上添加了一些额外的功能，比如“属性列表”，它可以将属性应用于生成的元素。

一个有用的属性是 `{：toc}` ，它可以应用于一个列表项目，这个列表项目将被一个链接列表替换。 例如。 下面的列表项将被链接到页面中的所有标题项目所取代：

```md
* Dummy list item
{:toc}
```

进一步的Kramdown功能在 [converter page](http://kramdown.gettalong.org/converter/html.html) 上有描述，属性列表在  [syntax page](http://kramdown.gettalong.org/syntax.html)。 请注意，源代码格式配置为使用 [GitHub Flavoured Mardown](https://help.github.com/articles/github-flavored-markdown/) 和“代码防护”，如下所示。

### Liquid tags and filters

Jekyll使用 [Liquid](http://liquidmarkup.org) 模板语言来处理文件。 这意味着标准的液体标签和过滤器是可用的。 应该没有什么需要使用它们，因为Markdown格式已经相当丰富了。 请参阅 [Jekyll site](http://jekyllrb.com/docs/templates/) 了解更多详情。

### Syntax highlighting

源代码可以通过使用 [GitHub Flavoured Markdown](https://help.github.com/articles/github-flavored-markdown/)  代码栏来表示，它们是三个反标记：

    ```
    // Source code goes here...
    ```

语法突出显示可以通过在第一组记号之后指定语言来应用：

    ```csharp
    // Some C# code
    ```

    ```java
    // Some Java code
    ```

这里是[supported languages](https://github.com/jneen/rouge/wiki/List-of-supported-languages-and-lexers) （还有 [Kotlin](https://kotlinlang.org)）。

<!-- Not currently supported by rouge, or by the site's CSS

The site is also configured to highlight a range of files in the source code, by specifying `{start-end}` which is the start and end line of the highlighting:

    ```java{2-3}
    // Not highlighted
    // Highlighted
    // Highlighted
    // Not highlighted
    ```
-->

### Tables

Kramdown解析器也支持表格。 语法是使用管道（`|`）和减号：

    ```md
    | Column 1 | Column 2 | Column 3 |
    |----------|----------|----------|
    | Blah     | Blah     | Blah     |
    ```

### Links

链接以正常的链接处理，可以链接到外部网站，网站内的网页或网站中的标题。 当 Markdown 标题被转换成 HTML 标题时，它被分配一个ID，所以它可以被链接，例如， `## Introduction`将得到 `introduction` 的ID，并且可以在同一页`[click here](#introduction)` 或者跨页面 `[click here](page.html#introduction)` 链接。 锚名称全部为小写，空格被替换为 `-`，例如，  `## Page setup` 设置成 `#page-setup`。

* `[External site](http://example.org)` 将链接到外部网站
* `[Other page in current directory](Page2.md)` 将链接到与当前页面相同的目录中的页面。 请注意，扩展名是`.md`，而不是`.html`。
* `[Page in another folder](/Folder2/Page2.md)` 将链接到另一个文件夹中的页面。 请注意，该URL是从根目录导航 - 即使该站点托管在一个子文件夹中（例如，此链接可用于 `http://localhost:4000/devguide/Folder2/Page2.html`），这种方式仍然有效。 相关链接也可以工作（ `../ Folder2 / Page2.md`）。
* `[Link to section on another page](Page2.md#another-section)` 将链接到另一页上的标题。 标题的ID通过使文本小写并用`-`替换空格来生成。
* `[Link to section on current page](#another-section)` 将链接到当前页面上的标题。

### Notes and callouts

注释和标注可以使用blockquote语法指定。 转换器会看第一个下一个单词，看它是否是粗体。 如果是这样，它将应用作为标注样式。 例如：

    > **NOTE** This is a note

将显示为标注，标注为 "note"。 其他样式可用于标注 "note", "warning", "tip" 和 "todo".

### Images

可以通过将文件直接添加到存储库并添加链接到图像来包含图像，如下所示：

    ![Alt text](path-to-img.png)

请缩减以高分辨率制作的截图。

## A word on submodules

`sdkdocs-template` 是作为一个子模块添加的，它还包含一个专用 `webhelp-template` 的子模块。 `sdkdocs-template` 包含构建时间脚本和编译和精简的JS和CSS，允许网站运行。 私有 `webhelp-template` 包含构建JS和CSS的代码。 目前它是封闭源码，但是计划是在某些时候使它成为开源的，在这种情况下，很可能这两个回购将被合并。

克隆后，需要初始化和更新子模块：

    ```
    git submodule init
    git submodule update
    ```

这将创建一个 `.gitmodules` 文件，在 `sdkdocs-template` 文件夹中注册一个子模块，并检出这些文件。 （请注意，当一个repo作为子模块添加时，它不会得到一个 `.git` 文件夹，而是得到一个 `.git` 文件，该文件指向 `.git` 文件夹的位置。

子模块可以使用普通的git命令更新，例如 `git pull`。 可以使用 `git checkout` 将其切换到不同的分支，并且对当前检出的修订的任何更改都需要作为正常的git命令提交回主repo。 它最初是在特定版本中克隆的，而不是作为分支的一部分更新

如果对子模块进行了更改，则应在分支上对其进行克隆，并发送合并请求。 可以进行更改和提交，并且托管回购将需要提交指向当前版本的子模块的指针。

如果sdkdocs-template有任何问题，请 [raise an issue](https://github.com/JetBrains/sdkdocs-template/issues).


