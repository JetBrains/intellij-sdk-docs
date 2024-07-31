<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Plugin Template

<link-summary>IntelliJ Platform Plugin Template is a GitHub template containing a minimal preconfigured plugin project and GitHub Actions CI workflows that cover building, testing and deploying the plugin.</link-summary>

The IntelliJ Platform Plugin Template is the alternative solution for creating a new Gradle-based IntelliJ Platform plugin with the [New Project Wizard](creating_plugin_project.md).

[IntelliJ Platform Plugin Template][gh:plugin-template] is a GitHub repository that provides a pure boilerplate template to make it easier to create a new Gradle-based plugin project.

The main goal of this template is to speed up the setup phase of plugin development for both new and experienced developers by preconfiguring the project scaffold and CI, linking to the proper documentation pages, and keeping everything organized.

> Note: This template uses [](tools_intellij_platform_gradle_plugin.md).

GitHub Template allows you to create a new repository from the scaffold without having to copy and paste content, clone repositories, or clear the history manually.
All you have to do is click the <control>Use this template</control> button on the GitHub project page (you must be logged in with your GitHub account).
After that, the GitHub Actions workflow will be triggered to override or remove any template-specific configurations, such as the plugin name, current changelog, etc.

Once this is complete, the project is ready to be cloned to your local environment and opened with [IntelliJ IDEA](https://www.jetbrains.com/idea/download).

For more details, refer to the [IntelliJ Platform Plugin Template][gh:plugin-template] project documentation.

> The recording of the _Busy Plugin Developer. Episode 0_ webinar describes and shows [how to use the IntelliJ Platform Plugin Template](https://youtu.be/-6D5-xEaYig?t=230) in detail.
>
{style="note"}

[gh:plugin-template]: https://github.com/JetBrains/intellij-platform-plugin-template
