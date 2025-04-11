<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Project Model

<link-summary>Introduction to project structure and components.</link-summary>

[//]: # (TODO: split into parts accordingly to the table of contents)

This topic considers the concept of projects based on the IntelliJ Platform and related subjects, such as modules, libraries, facets, and SDKs.

> The [](workspace_model.md) API is available since 2024.2 for use by third-party plugins and should be preferred over using the Project Model API.
> See [](workspace_model.md#interoperability) and [](workspace_model_usages.md).
>
> Since the 2020.3 release, related internal changes are included;
> see this [blog post](https://blog.jetbrains.com/platform/2020/10/new-implementation-of-project-model-interfaces-in-2020-3/) for details.
> This shouldn't affect any plugins using the Project Model API properly and which don't access internal classes.
>
{style="note" title="Workspace Model API (2024.2+)"}

## Project

<tldr>

**Reference**: [](project.md)

**Product Help**: [Projects](https://www.jetbrains.com/help/idea/creating-and-managing-projects.html)

</tldr>

A project encapsulates all of a project's source code, libraries, and build instructions into a single organizational unit.

> Everything in the IntelliJ Platform SDK is done within the context of a project.

A project defines collections referred to as [Modules](#module) and [Libraries](#library).
Depending on the project's logical and functional requirements, a _single-module_ or a _multi-module_ project can be created.

## Module

<tldr>

**Reference**: [](module.md)

**Product Help**: [Modules](https://www.jetbrains.com/help/idea/creating-and-managing-modules.html)

</tldr>

A module is a discrete unit of functionality that can be run, tested, and debugged independently.
Modules include such things as source code, build scripts, unit tests, deployment descriptors, etc.

In a project, each module can use a specific [Software Development Kit (SDK)](#sdk) or inherit the SDK defined at the project level.
A module can depend on other modules of the project.

## Library

<tldr>

**Reference**: [](library.md)

**Product Help**: [Libraries](https://www.jetbrains.com/help/idea/library.html)

</tldr>

A library is an archive of compiled code (such as JAR files) on which modules depend.
The IntelliJ Platform supports three types of libraries: Module, Project, and Global Libraries.

## SDK

<tldr>

**Reference**: [](sdk.md)

**Product Help**: [SDK](https://www.jetbrains.com/help/idea/sdk.html)

</tldr>

Every project uses a Software Development Kit (SDK).
For Java projects, SDK is referred to as JDK (Java Development Kit).

The SDK determines which API library is used to build the project.
For a multi-module project, the project SDK is common for all [modules](#module) within the project by default.
Optionally, a project can configure an individual SDK for each module.

## Facet

<tldr>

**Reference**: [](facet.md)

**Product Help**: [Facets](https://www.jetbrains.com/help/idea/facet-page.html)

</tldr>

A facet represents a certain configuration, specific for a particular framework or technology associated with a [module](#module).
A module can have multiple facets.

## Configuration

<tldr>

**Product Help**: [Project Structure](https://www.jetbrains.com/help/idea/project-settings-and-structure.html)

</tldr>

The user can configure all entities listed above in the <control>Project Structure</control> dialog.

Use [`ProjectSettingsService`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/configuration/ProjectSettingsService.java) to open related entries programmatically.
