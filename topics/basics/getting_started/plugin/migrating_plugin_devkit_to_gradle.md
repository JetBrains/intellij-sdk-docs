# Migrating DevKit Plugin to Gradle

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Migrating existing DevKit-based plugins to Gradle setup.</link-summary>

> See [Revamping Plugins #3 – Migrating from DevKit to the Gradle build system](https://blog.jetbrains.com/platform/2021/12/migrating-from-devkit-to-the-gradle-build-system/) blog post for a step-by-step walk-through.

Converting a plugin created with the old DevKit approach (which can be used for [creating themes](creating_theme_project.md)) to a Gradle-based plugin project can be done using the <control>New Project</control> wizard to create a Gradle-based project around the existing DevKit-based project:
* Ensure the directory containing the DevKit-based IntelliJ Platform plugin project can be fully recovered if necessary.
* Delete all the artifacts of the DevKit-based project:
    * <path>.idea</path> directory
    * <path>[modulename].iml</path> file
    * <path>out</path> directory
* Arrange the existing source files within the project directory in the Gradle [source set](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) format.
* Use the <control>[New Project](creating_plugin_project.md#create-ide-plugin)</control> wizard as though creating a new Gradle-based plugin project from scratch. On the <control>New Project</control> page choose the <control>IDE Plugin</control> generator and set the values of:
    * <control>Group</control> to the existing package in the initial source set.
    * <control>Artifact</control> to the name of the existing plugin.
    * <control>Name</control> to the name of the directory where the existing plugin is located, e.g. if the plugin project base directory is <path>/Users/john/Projects/old_plugin</path>, it should be the <path>old_plugin</path>.
    * <control>Location</control> to the name of the plugin's parent directory, e.g. if the plugin project base directory is <path>/Users/john/Projects/old_plugin</path>, it should be the <path>/Users/john/Projects</path>.
* Click <control>Finish</control> to create the new Gradle-based plugin.
* [Add more modules](https://www.jetbrains.com/help/idea/gradle.html#gradle_add_module) using Gradle [source sets](https://www.jetbrains.com/help/idea/gradle.html#gradle_source_sets) as needed.
