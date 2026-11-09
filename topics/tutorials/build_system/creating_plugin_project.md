<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Creating a Plugin Project

<web-summary>
Create and run a Gradle-based IntelliJ Platform plugin project using the IDE Plugin wizard.
</web-summary>

<link-summary>Creating and running a Gradle-based IntelliJ Platform plugin project.</link-summary>

The recommended approach to creating a plugin is the _IDE Plugin_ wizard available in IntelliJ IDEA.
The wizard is available when _Gradle_ and _Plugin DevKit_ plugins are [installed and enabled](https://www.jetbrains.com/help/idea/managing-plugins.html).

It is also possible to create plugins via the web [IDE Plugin generator](https://plugins.jetbrains.com/generator).

> This page describes the wizard available in IntelliJ IDEA 2026.1 and newer.
>
> The <control>IDE Plugin</control> wizard available in older versions may differ in available options.
> If the newest IDE cannot be used, consider generating a project via the [web generator](https://plugins.jetbrains.com/generator) and opening the generated project in the IDE.
>
{style="note"}

<procedure title="Create an IDE Plugin" id="create-ide-plugin">

Launch the <control>[New Project](https://www.jetbrains.com/help/idea/new-project-wizard.html)</control> wizard via the <ui-path>File | New | Project...</ui-path> action and follow these steps:
1. Select the <control>IDE Plugin</control> type from the list on the left.
2. Specify the project <control>Name</control> and <control>Location</control>.
3. Choose the <control>Plugin</control> option in the project <control>Type</control>.
4. Provide the <control>Group</control> which is typically an inverted company domain (e.g. `com.example.mycompany`).<br/>
   It is used for the Gradle property `project.group` value in the project's Gradle build script, the base project package name, and the generated plugin ID.
5. Provide the <control>Artifact</control>, which is the default name of the build project artifact (without the version).<br/>
   It is used for the Gradle property `rootProject.name` value in the project's <path>settings.gradle.kts</path> file, plugin name, and the generated plugin ID.
6. Select a <control>JDK</control> matching the required Java version.<br/>
   It will be the default JRE used to run Gradle, and the JDK used to compile the plugin sources.
   Currently, the generated project targets the platform version compatible with JDK 21.
   > If supporting an older platform version is planned, configure the JDK version compatible with the [target platform version](build_number_ranges.md#platformVersions).
   >
   {style="note"}
7. Enable the <control>Add sample code</control> checkbox to generate a sample tool window code.
8. Click the <control>Next</control> button.
9. Select <control>Features</control> to include in the generated plugin.<br/>
   > New plugin projects should be implemented in a way that supports working in [Remote Development mode](split_mode_and_remote_development.md).
   > It is highly recommended to select the <control>Split Mode (Remote Dev)</control> feature.
   >
   {style="note"}
10. Click the <control>Create</control> button to generate the project.

</procedure>

The created project structure is described in the <path>README.md</path> file in the _Project structure_ section.

## Running the Plugin

> If the <control>Add sample code</control> checkbox was not checked during the project generation, add sample code.
> For example, follow the [](creating_actions_tutorial.md) tutorial to add a menu action.
>
{style="note"}

<tabs>

<tab title="Classic Plugin">

The generated project contains the _Run IDE with Plugin_ [run configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) that can be executed via the <ui-path>Run | Run...</ui-path> action or can be found in the <control>Gradle</control> tool window under the <control>Run Configurations</control> node.

To execute the Gradle `runIde` task directly, open the <control>Gradle</control> tool window and search for the <control>runIde</control> task under the <control>Tasks</control> node.
If it's not on the list, click the <control>Sync All Gradle Projects</control> button on the [toolbar](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#gradle_toolbar) at the top of the <control>Gradle</control> tool window.
Then double-click it to execute.

</tab>

<tab title="Split Mode (Remote Dev) Plugin">

The generated project contains the _Run IDE with Plugin (Split Mode)_ [run configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) that can be executed via the <ui-path>Run | Run...</ui-path> action.

Alternatively, run _Run IDE with Plugin (Backend)_ and _Run IDE with Plugin (Frontend)_ separately.

To execute the Gradle tasks directly, open the <control>Gradle</control> tool window and search for the <control>runIdeBackend</control> and <control>runIdeFrontend</control> tasks under the <control>Tasks</control> node.
If they are not on the list, click the <control>Sync All Gradle Projects</control> button on the [toolbar](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#gradle_toolbar) at the top of the <control>Gradle</control> tool window.
Then double-click tasks to execute.

</tab>

</tabs>

To debug a plugin in a _standalone_ IDE instance, please see [How to Debug Your Own IntelliJ IDEA Instance](https://medium.com/agorapulse-stories/how-to-debug-your-own-intellij-idea-instance-7d7df185a48d) blog post.

> For more information about how to work with Gradle-based projects see the [Working with Gradle in IntelliJ IDEA](https://www.youtube.com/watch?v=6V6G3RyxEMk) screencast and working with [Gradle tasks](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html) in the IntelliJ IDEA help.
