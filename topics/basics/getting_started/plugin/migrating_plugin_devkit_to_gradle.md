[//]: # (title: Migrating DevKit Plugin to Gradle)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

> See [Revamping Plugins #3 – Migrating from DevKit to the Gradle build system](https://blog.jetbrains.com/platform/2021/12/migrating-from-devkit-to-the-gradle-build-system/) blog post for a step-by-step walk-through.
>
{type="tip"}

Converting a [DevKit-based](creating_devkit_theme_project.md) plugin project to a Gradle-based plugin project can be done using the New Project Wizard to create a Gradle-based project around the existing DevKit-based project:
* Ensure the directory containing the DevKit-based IntelliJ Platform plugin project can be fully recovered if necessary.
* Delete all the artifacts of the DevKit-based project:
    * <path>.idea</path> directory
    * <path>[modulename].iml</path> file
    * <path>out</path> directory
* Arrange the existing source files within the project directory in the Gradle [source set](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) format.
* Use the New Project Wizard as though creating a [new Gradle project](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) from scratch.
* On the [New Project](#create-ide-plugin) choose the <control>IDE Plugin</control> generator and set the values of:
    * <control>Group</control> to the existing package in the initial source set.
    * <control>Artifact</control> to the name of the existing plugin.
    * <control>Name</control> to the name of the directory where the existing plugin is located, e.g. if the plugin project base directory is <path>/Users/john/Projects/old_plugin</path>, it should be the <path>old_plugin</path>.
    * <control>Location</control> to the name of the plugin's parent directory, e.g. if the plugin project base directory is <path>/Users/john/Projects/old_plugin</path>, it should be the <path>/Users/john/Projects</path>.
* Click <control>Finish</control> to create the new Gradle-based plugin.
* [Add more modules](https://www.jetbrains.com/help/idea/gradle.html#gradle_add_module) using Gradle [source sets](https://www.jetbrains.com/help/idea/gradle.html#gradle_source_sets) as needed.

## Running a Simple Gradle-Based IntelliJ Platform Plugin

Gradle projects are run from the IDE's Gradle Tool window.

### Adding Code to the Project

Before running [`my_plugin`](#components-of-a-wizard-generated-gradle-intellij-platform-plugin), some code can be added to provide simple functionality.
See the [Creating Actions](working_with_custom_actions.md) tutorial for step-by-step instructions for adding a menu action.

### Executing the Plugin

The _IDE Plugin_ generator automatically creates the _Run Plugin_ run configuration that can be executed via the <menupath>Run | Run...</menupath> action or can be found in the <control>Gradle</control> tool window under the <control>Run Configurations</control> node.

To execute the Gradle `runIde` task directly, open the <control>Gradle</control> tool window and search for the <control>runIde</control> task under the <control>Tasks</control> node.
If it's not on the list, hit the re-import button in the [toolbar](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#1eeec055) at the top of the Gradle tool window.
When the <control>runIde</control> task is visible, double-click it to execute.

> See the IntelliJ IDEA help for more information about [Gradle tasks](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html).

To debug your plugin in a _standalone_ IDE instance, please see [How to Debug Your Own IntelliJ IDEA Instance](https://medium.com/agorapulse-stories/how-to-debug-your-own-intellij-idea-instance-7d7df185a48d) blog post.

> See the [Working with Gradle in IntelliJ IDEA](https://www.youtube.com/watch?v=6V6G3RyxEMk) screencast for more information about how to work with Gradle-based projects.
>
{type="tip"}
