<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 1. Prerequisites

<link-summary>Steps required before starting implementation of a sample custom language support.</link-summary>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

## Download and Install IntelliJ IDEA

Download and install either IntelliJ IDEA Ultimate or IntelliJ IDEA Community Edition from [here](https://www.jetbrains.com/idea/download/).

## Check out Community Edition Source Files

> While not required, having the full sources of the platform and all bundled plugins available for browsing allows finding related implementations.
>
{style="note"}

Download the IntelliJ IDEA Community Edition source files as described in the IntelliJ IDEA Community Edition [README](%gh-ic%/README.md) file.

## Install Required Plugins

Make sure that the _Plugin DevKit_ and _Gradle_ plugins are enabled.

<include from="snippets.topic" element-id="pluginDevKitAvailability"/>

Install and enable [Grammar-Kit](https://plugins.jetbrains.com/plugin/6606-grammar-kit) and [PsiViewer](https://plugins.jetbrains.com/plugin/227-psiviewer) (optionally) plugins.

## Create a Project

Create an empty [IntelliJ Platform Plugin project](creating_plugin_project.md) or start using [](plugin_github_template.md) when creating a plugin hosted on GitHub.
