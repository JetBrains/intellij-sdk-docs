[//]: # (title: 1. Prerequisites)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

## Download and Install IntelliJ IDEA

Download and install either IntelliJ IDEA Ultimate or IntelliJ IDEA Community Edition from [here](https://www.jetbrains.com/idea/download/).

## Check out Community Edition Source Files

> While not required, having the full sources of the platform and all bundled plugins available for browsing allows finding related implementations.
>
{type="note"}

Download the IntelliJ IDEA Community Edition source files as described in the IntelliJ IDEA Community Edition [README](upsource:///README.md) file.

## Install Required Plugins

Make sure that the bundled *Plugin DevKit* and *Gradle* plugins are enabled.
Install and enable [Grammar-Kit](https://plugins.jetbrains.com/plugin/6606-grammar-kit) and [PsiViewer](https://plugins.jetbrains.com/plugin/227-psiviewer) plugins.

## Create a Project

Create an empty [IntelliJ Platform Plugin project](gradle_prerequisites.md) or start using [](github_template.md) when creating a plugin hosted on GitHub.
