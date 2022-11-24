[//]: # (title: Creating a Plugin Gradle Project)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

This documentation page describes a Gradle-based plugin project generated with the [New Project Wizard](https://www.jetbrains.com/help/idea/new-project-wizard.html), but the project generated with [](plugin_github_template.md) covers all the described files and directories.

## Creating a Plugin with New Project Wizard

<procedure title="Create IDE Plugin" id="create-ide-plugin">

Launch the <control>New Project</control> wizard via the <ui-path>File | New | Project...</ui-path> action and provide the following information:
1. Select the <control>IDE Plugin</control> generator type from the list on the left.
2. Specify the project <control>Name</control> and <control>Location</control>.
3. Choose the <control>Plugin</control> option in the project <control>Type</control>.
4. Choose the <control>Language</control> the plugin will use for implementation.
   For this example select the <control>Java</control> option.
   See [Kotlin for Plugin Developers](using_kotlin.md) for more information.
5. Provide the <control>Group</control> which is typically an inverted company domain (e.g. `com.example.mycompany`).
   It is used for the Gradle property `project.group` value in the project's Gradle build script.
6. Provide the <control>Artifact</control> which is the default name of the build project artifact (without a version).
   It is also used for the Gradle property `rootProject.name` value in the project's <path>settings.gradle.kts</path> file.
   For this example, enter `my_plugin`.
7. Select <control>JDK</control> 11.
   This JDK will be the default JRE used to run Gradle, and the JDK version used to compile the plugin Java sources.

> When targeting 2020.3 and later only, using Java 11 is required.
> See the [IntelliJ project migrates to Java 11](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/) blog post for details.
>
> When targeting 2022.2 and later only, using Java 17 is required.
>
{style="note"}

8. After providing all the information, click the <control>Create</control> button to generate the project.

</procedure>

### Components of a Wizard-Generated Gradle IntelliJ Platform Plugin

For the example `my_plugin` created with the steps describes above, the _IDE Plugin_ generator creates the following directory content:

```text
my_plugin
├── .run
│   └── Run IDE with Plugin.run.xml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │       └── META-INF
│   │           └── plugin.xml
│   └── test
│       ├── java
│       └── resources
├── .gitignore
├── build.gradle.kts
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

* The default IntelliJ Platform <path>build.gradle.kts</path> file (see next paragraph).
* The <path>settings.gradle.kts</path> file, containing a definition of the `rootProject.name`.
* The Gradle Wrapper files, and in particular the <path>gradle-wrapper.properties</path> file, which specifies the version of Gradle to be used to build the plugin.
  If needed, the IntelliJ IDEA Gradle plugin downloads the version of Gradle specified in this file.
* The <path>META-INF</path> directory under the default `main` [source set](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) contains the plugin [configuration file](plugin_configuration_file.md).
* The _Run Plugin_ [run configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html).

The generated `my_plugin` project <path>build.gradle.kts</path> file:

```kotlin
plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.10.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2021.3")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  patchPluginXml {
    sinceBuild.set("213")
    untilBuild.set("223.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
```

* Two Gradle plugins are explicitly declared:
  * The [Gradle Java](https://docs.gradle.org/current/userguide/java_plugin.html) plugin (`java`).
  * The [](tools_gradle_intellij_plugin.md) (`org.jetbrains.intellij`).
* The <control>Group</control> from the [New Project](#create-ide-plugin) wizard is the `project.group` value.
* The `sourceCompatibility` line is injected to enforce using Java 11 JDK to compile Java sources.
* The values of the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) and [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) properties specify the version and type of the IntelliJ Platform to be used to build the plugin.
* The empty placeholder list for [plugin dependencies](tools_gradle_intellij_plugin.md#intellij-extension-plugins).
* The values of the [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-sincebuild) and [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-untilbuild) properties specifying the minimum and maximum versions of the IDE build the plugin is compatible with.
* The initial [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) and [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) tasks configuration.
  See the [](publishing_plugin.md#publishing-plugin-with-gradle) section for more information.

> Consider using the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) which uses the Gradle setup and provides CI setup covered with GitHub Actions.
>

#### Plugin Gradle Properties and Plugin Configuration File Elements

The Gradle properties `rootProject.name` and `project.group` will not, in general, match the respective [plugin configuration file](plugin_configuration_file.md) <path>plugin.xml</path> elements [`<name>`](plugin_configuration_file.md#idea-plugin__name) and [`<id>`](plugin_configuration_file.md#idea-plugin__id).
There is no IntelliJ Platform-related reason they should as they serve different functions.

The `<name>` element (used as the plugin's display name) is often the same as `rootProject.name`, but it can be more explanatory.

The `<id>` value must be a unique identifier over all plugins, typically a concatenation of the specified <control>Group</control> and <control>Artifact</control>.
Please note that it is impossible to change the `<id>` of a published plugin without losing automatic updates for existing installations.

## Running a Plugin With the `runIde` Gradle task

Gradle projects are run from the IDE's Gradle Tool window.

### Adding Code to the Project

Before running [`my_plugin`](#components-of-a-wizard-generated-gradle-intellij-platform-plugin), some code can be added to provide simple functionality.
See the [Creating Actions](working_with_custom_actions.md) tutorial for step-by-step instructions for adding a menu action.

### Executing the Plugin

The _IDE Plugin_ generator automatically creates the _Run Plugin_ run configuration that can be executed via the <ui-path>Run | Run...</ui-path> action or can be found in the <control>Gradle</control> tool window under the <control>Run Configurations</control> node.

To execute the Gradle `runIde` task directly, open the <control>Gradle</control> tool window and search for the <control>runIde</control> task under the <control>Tasks</control> node.
If it's not on the list, hit the re-import button in the [toolbar](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#1eeec055) at the top of the Gradle tool window.
When the <control>runIde</control> task is visible, double-click it to execute.

To debug your plugin in a _standalone_ IDE instance, please see [How to Debug Your Own IntelliJ IDEA Instance](https://medium.com/agorapulse-stories/how-to-debug-your-own-intellij-idea-instance-7d7df185a48d) blog post.

> For more information about how to work with Gradle-based projects see the [Working with Gradle in IntelliJ IDEA](https://www.youtube.com/watch?v=6V6G3RyxEMk) screencast and working with [Gradle tasks](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html) in the IntelliJ IDEA help.
>
