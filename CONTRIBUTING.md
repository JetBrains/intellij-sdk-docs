---
title: Contributing to the IntelliJ Platform SDK
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This document describes our contribution guidelines for the open source IntelliJ Platform SDK documentation and sample code.
Before you begin contributing content to the SDK, please read this page thoroughly as well as the [Code of Conduct](/CODE_OF_CONDUCT.md) and [License](https://github.com/JetBrains/intellij-sdk-docs/blob/master/LICENSE.txt) documents.
For information about contributing to the IntelliJ Platform itself, please visit [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

Here are some useful things to know before authoring SDK content and submitting your Pull Request.
* Dummy list item
{:toc}
 
## Setting Up the Documentation Build Environment

This site runs via [Jekyll](https://jekyllrb.com), which is a popular static site generator, written in Ruby. It can be hosted locally to ensure that any changes are correct. Once set up, running the site is as easy as calling `rake preview`.

Alternatively, the site can also be hosted in a [Docker container](https://www.docker.com). On Mac and Windows, this means the site is hosted in a virtual machine. Docker maintains this container, building it based on the instructions in the [`Dockerfile`](Dockerfile). All dependencies (Ruby, etc.) are automatically installed when building the image, which reduces the manual configuration steps. The Docker image is also used to build the [published site](https://www.jetbrains.org/intellij/sdk/docs/index.html), so it is a known working environment.

### Developing Documentation with Docker

Follow these steps to work with Docker:

* Firstly, install Docker, using the [Docker Toolbox](https://www.docker.com/docker-toolbox).
* On Windows and Mac, start the Docker host virtual machine (start the "Docker Quickstart terminal" or run "Kitematic." See the Getting Started guides on [docker.com](https://www.docker.com) for more details).
* Clone the [`intellij-sdk-docs`](https://github.com/JetBrains/intellij-sdk-docs) repo to the local machine, and [initialise the `sdkdocs-template` submodule](#documentation-repository-submodules).
* Change the current directory to the parent directory of the git repo.
* Run `docker build -t intellij-sdk-docs .` to build the docker image from the current folder, and give it the tag `intellij-sdk-docs`.
    * Note that this must be run from a command prompt that has the various `DOCKER_*` environment variables set up, such as the Docker Quickstart Terminal.
* Run `docker run -p 4000:4000 -v $PWD:/usr/src/app intellij-sdk-docs`. This command will:
    * Start the docker container called `intellij-sdk-docs`.
    * Forward port 4000 from the docker container to port 4000 on the docker client.

    > **NOTE** For Windows and Mac, this means port 4000 of the docker container is forwarded to port 4000 of the docker virtual machine, not `localhost`. For Linux, the docker client is the host machine, so `localhost:4000` is forwarded to port 4000 on the docker container.
    >
    > To hit the container's port 4000 from Windows or the Mac, it is necessary to hit the IP address of the docker client (virtual machine). Use `docker-machine ip default` to get the IP address of the docker client. Use `X.X.X.X:4000` to hit the client in the virtual machine, which is in turn mapped to the container's port 4000.
    >
    > Alternatively, modify the virtual machine's settings to automatically forward port 4000 to `localhost`. See this [blog post](https://acaird.github.io/computers/2014/11/16/docker-virtualbox-host-networking) for more details.
 
    * Mount the current directory (`$PWD` is a Unix style environment variable. You can use `%CD%` on Windows, or specify the full path) as `/usr/src/app` inside the docker container. The docker image will see the `intellij-sdk-docs` repo as the folder `/usr/src/app`.

    > **NOTE** If running on Windows in an MSYS bash script (e.g. the "Docker Quickstart terminal"), the path to the local folder needs to be properly escaped, or the MSYS environment will translate the paths to standard Windows path, and causing an error such as `invalid value "C:\\Users\\...;C:\\Program Files\\Git\\usr\\src\\app" for flag -v`. To fix this problem, prefix the full path with double slashes, e.g. `-v //c/Users/...`, or `docker run -p 4000:4000 -v /$PWD:/usr/src/app intellij-sdk-docs` (note the leading slash before `$PWD`).

    * Run the commands in the Dockerfile's `CMD` instruction to execute: 
  * `rake bootstrap`, which ensures all of the prerequisites are installed, 
  * `rake preview`, which builds the site and starts to host it.
* Finally, you can access the newly created site by visiting [http://localhost:4000/intellij/sdk/docs/](http://localhost:4000/intellij/sdk/docs/), or by using the IP address of the docker client virtual machine (see the note above).

### Developing Documentation Locally

To build the documentation site, you need:

* Ruby 2 - Jekyll is a Ruby application.
* Ruby 2 DevKit (for Windows) - Some of Jekyll's dependencies need to be compiled, and require the DevKit to be installed.
* `gem install bundler` - the site uses [Bundler](https://bundler.io) to manage gem dependencies within the repo, rather than globally installing to the local operating system. Run this command to install the Bundler toolset globally.

#### macOS

macOS comes with Ruby already installed. The only steps required are:

* `gem install bundler`

#### Windows

* Install [Ruby 2](https://rubyinstaller.org) and the [Ruby 2 DevKit](https://rubyinstaller.org/downloads/) (one of the gems needs to build a native component)
    * After installing the DevKit, make sure to edit the `config.yml` file to point to the Ruby installation

This installation is easier if you use [Chocolatey](https://chocolatey.org), a package manager for Windows:

* `choco install ruby`
* `choco install ruby2.devkit`
    * After installing the DevKit, make sure to edit the `config.yml` file to point to the Ruby installation.
    * By default, this is `C:\tools\DevKit\config.yml`
    * Add the line `- C:\tools\ruby21` (including the leading minus sign)

> **NOTE** Before running the `rake bootstrap` step listed below, please run the `devkitvars.bat` file from the DevKit. E.g. `C:\tools\DevKit\devkitvars.bat`

### Bootstrapping the Documentation Build Environment

1. Ensure Bundler is installed - `gem install bundler`.
2. On Windows, ensure the `devkitvars.bat` file has been run in the current command prompt (e.g. `c:\tools\DevKit\devkitvars.bat`).
3. Clone the documentation site.
4. Initialize and update the `sdkdocs-template` submodule - `git submodule init` and `git submodule update`
5. `rake bootstrap` - this uses Bundler to download all required gems.
6. `rake preview` - this will build the site, and host it in a local webserver. 

### Building and Previewing the Site

To build and test the site, run `rake preview`. This will build the site and host it, using the config provided. The URL of the hosted site is displayed on the screen and depends on the `baseurl` field defined in `_config.yml`.

> **NOTE** You must use `localhost` as hostname, _NOT_ 0.0.0.0, otherwise fonts fail to load.

## Documentation Repository Submodules
The `sdkdocs-template` directory is actually a Git submodule, and it contains a submodule to the private `webhelp-template` repository. 
The `sdkdocs-template` repository contains build scripts and compiled and minified JS and CSS that allow the site to run. 
The private `webhelp-template` repository contains the code to build the JS and CSS. 
It is currently closed source, but the plan is to make it open source at some point, in which case it is likely the two repositories will be merged.

After cloning, a submodule needs to be initialized and updated:

```sh
git submodule init
git submodule update
```

Initialization creates a `.gitmodules` file, register a submodule in the `sdkdocs-template` folder and check out the files. 
Note that when a repo is added as a submodule, it doesn't get a `.git` folder, but instead gets a `.git` file that points to the actual location of the `.git` folder.

A submodule can be updated using normal git commands such as `git pull`. 
It can be switched to a different branch using `git checkout`, and any changes to the currently checked out revision need to be committed back into the main repository using git commands. 
A submodule is initially cloned at a specific revision, and not as part of a branch.update

If changes are made to the submodule, they should be made on a branch to a clone, and a Pull Request sent. 
Changes can be made and committed, and the hosting repository will need to commit a pointer to the current version of the submodule.

If there are any problems with the `sdkdocs-template`, please [raise an issue](https://github.com/JetBrains/sdkdocs-template/issues).

## Creating IntelliJ Platform SDK Content
Content contributions to the IntelliJ Platform SDK are welcome.
Please download or clone the open source SDK project from [GitHub](https://github.com/JetBrains/intellij-sdk-docs), make additions or changes, and submit a pull request.
Before creating or altering content, please consult these guides:
* [SDK Documentation Style Guide](intro/sdk_style.md). 
  This guide describes documentation conventions in terms of Markdown syntax.
  Always test documentation changes using a [preview](#building-and-previewing-the-site) of the site.
* [SDK Code Sample Guidelines](intro/sdk_code_guidelines.md). 
  Conventions for code sample organization, project settings, and naming conventions are described in this document.
  Always test code changes by building and testing the SDK code sample.

