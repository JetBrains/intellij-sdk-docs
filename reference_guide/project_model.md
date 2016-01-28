---
title: Project Model. Roots and Libraries. Configuring Project from Code.
---

This section considers the internal architecture of *IntelliJ Platform* projects and gives an overview for classes and packages of the API used to manipulate with projects and their components, such as modules, facets, libraries, SDK.

For general information about the concept of a projects and concepts related to it, refer to [Project](http://www.jetbrains.com/idea/help/project.html), [Module](http://www.jetbrains.com/idea/help/module.html), [Library](http://www.jetbrains.com/idea/help/library.html), and [Facet](http://www.jetbrains.com/idea/help/facet.html) in the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/intellij-idea.html).

## Project Structure

A project consists of one or several modules. Each module includes source code and so called order entries that refer to SDK and libraries the module uses. By default, all modules use the project SDK. In addition, a module can optionally have a set of facets. This document explains how you can explore and change the structure of projects using API.

Main project structure components are:

* [Project](/reference_guide/project_model/project.md)
* [Module](/reference_guide/project_model/module.md)
* [SDK](/reference_guide/project_model/sdk.md)
* [Library](/reference_guide/project_model/library.md)
* [Facet](/reference_guide/project_model/facet.md)
