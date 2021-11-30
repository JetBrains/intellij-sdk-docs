[//]: # (title: Getting Started with Gradle)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Gradle is the preferred solution for creating IntelliJ Platform plugins.
The IntelliJ IDEA Ultimate and Community editions bundle the necessary plugins to support Gradle-based development.
These IntelliJ IDEA plugins are _Gradle_ and _Plugin DevKit_, which are enabled by default.
To verify these plugins are installed and enabled, see the help section about [Managing Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html).

 >  [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) makes it easier to create and maintain your IDE plugins, having the Gradle plugin already integrated and CI covered with GitHub Actions.
 >
 {type="tip"}

 >  When adding additional repositories to your Gradle build script, always use HTTPS protocol.
 >
 {type="warning"}

## Creating a Gradle-Based IntelliJ Platform Plugin with New Project Wizard
Creating new Gradle-based IntelliJ Platform plugin projects is performed using the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
The Wizard creates all the necessary project files based on a few template inputs.

Before creating a new Gradle project, familiarize yourself with the help topic [Creating a new Gradle project](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html#create_gradle_project), which is a tutorial for creating general Gradle projects in IntelliJ IDEA.
This page emphasizes the steps in the process of creating IntelliJ Platform plugin projects that are Gradle-based.
Additionally, screencast [Working with Gradle in IntelliJ IDEA](https://www.youtube.com/watch?v=6V6G3RyxEMk) offers a thorough introduction.

Launch the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
It guides you through the Gradle project creation process with two screens.

### New Project Configuration Screen
On the first screen, the type of project is configured:
* From the project type pane on the left, choose <control>Gradle</control>.
* Specify the <control>Project SDK</control> based on the **Java 8** JDK.
  This SDK will be the default JRE used to run Gradle, and the JDK version used to compile the plugin Java sources.

 > When targeting 2020.3 and later only, using Java 11 is now required, please see [blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)
 >
 {type="note"}

* In the <control>Additional Libraries and Frameworks</control> panel, select <control>Java</control> and <control>IntelliJ Platform Plugin</control>.
  These settings will be used for the remainder of this tutorial.

Optionally:
* To include support for the Kotlin language in the plugin, check the _Kotlin/JVM_ box (circled in green below).
  This option can be selected with or without the <control>Java</control> language.
  See [Kotlin for Plugin Developers](kotlin.md) for more information.
* To create the <path>build.gradle</path> file as a Kotlin build script (<path>build.gradle.kts</path>) rather than Groovy, check the _Kotlin DSL build script_ box (circled in magenta below).

Then click _Next_:

![Select Gradle in the Project Creation Wizard](step1_new_gradle_project.png){width="800"}

### Project Naming/Artifact Coordinates Screen
Expand the <control>Artifact Coordinates</control> section and specify a [GroupId, ArtifactId, and Version](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle) using [Maven naming](https://maven.apache.org/guides/mini/guide-naming-conventions.html) conventions.
* <control>GroupId</control> is typically a Java package name, and it is used for the Gradle property `project.group` value in the project's <path>build.gradle</path> file.
  For this example, enter `com.your.company`.
* <control>ArtifactId</control> is the default name of the project JAR file (without version).
  It is also used for the Gradle property `rootProject.name` value in the project's <path>settings.gradle</path> file.
  For this example, enter `my_gradle_plugin`.
* <control>Version</control> is used for the Gradle property `project.version` value in the <path>build.gradle</path> file.
  For this example, enter `1.0`.

The <control>Name</control> field is synced automatically with the specified <control>ArtifactId</control>.

Specify the path for the new project in <control>Location</control> and click <control>Finish</control> to continue and generate the project.

### Components of a Wizard-Generated Gradle IntelliJ Platform Plugin
For the [example](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) `my_gradle_plugin`, the New Project Wizard creates the following directory content:

```text
my_gradle_plugin
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   └── resources
    │       └── META-INF
    │           └── plugin.xml
    └── test
        ├── java
        └── resources
```

* The default IntelliJ Platform <path>build.gradle</path> file (see next paragraph).
* The Gradle Wrapper files, and in particular the <path>gradle-wrapper.properties</path> file, which specifies the version of Gradle to be used to build the plugin.
  If needed, the IntelliJ IDEA Gradle plugin downloads the version of Gradle specified in this file.
* The <path>settings.gradle</path> file, containing a definition of the `rootProject.name`.
* The <path>META-INF</path> directory under the default `main` [SourceSet](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) contains the plugin [configuration file](plugin_configuration_file.md).

 > Please note: the generated <path>build.gradle</path> file needs to be adjusted as shown below, as IntelliJ IDEA currently generates template incompatible with gradle-intellij-plugin 1.0 release.
 > See [Upgrade Instructions](https://lp.jetbrains.com/gradle-intellij-plugin/) for more details.
 >
 {type="warning"}

The generated `my_gradle_plugin` project <path>build.gradle</path> file:

```groovy
  plugins {
      id 'java'
      id 'org.jetbrains.intellij' version '1.3.0'
  }

  group 'com.your.company'
  version '1.0'
  sourceCompatibility = 1.8

  repositories {
      mavenCentral()
  }
  dependencies {
      testImplementation group: 'junit', name: 'junit', version: '4.13.2'
  }

  // See https://github.com/JetBrains/gradle-intellij-plugin/
  intellij {
      version = '2020.1.3'
  }
  patchPluginXml {
      changeNotes = """
        Add change notes here.<br/>
        <em>most HTML tags may be used</em>"""
  }
```

* Two plugins to Gradle are explicitly declared:
  * The [Gradle Java](https://docs.gradle.org/current/userguide/java_plugin.html) plugin.
  * The [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin/).
* The <control>GroupId</control> from the Wizard [Project Naming/Artifact Coordinates Screen](#project-namingartifact-coordinates-screen) is the `project.group` value.
* The <control>Version</control> from the Wizard [Project Naming/Artifact Coordinates Screen](#project-namingartifact-coordinates-screen) is the `project.version` value.
* The `sourceCompatibility` line is injected to enforce using Java 8 JDK to compile Java sources.
* The only comment in the file is a link to the [README.md](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md) for the gradle-intellij-plugin, which is a reference for its configuration DSL.
* The value of the Setup DSL attribute `intellij.version` specifies the version of the IntelliJ Platform to be used to build the plugin.
  It defaults to the version of IntelliJ IDEA that was used to run the New Project Wizard.
* The value of the Patching DSL attribute `patchPluginXml.changeNotes` is set to a place holder text.

#### Plugin Gradle Properties and Plugin Configuration File Elements
The Gradle properties `rootProject.name` and `project.group` will not, in general, match the respective [plugin configuration file](plugin_configuration_file.md) <path>plugin.xml</path> elements `<name>` and `<id>`.
There is no IntelliJ Platform-related reason they should as they serve different functions.

The `<name>` element (used as the plugin's display name) is often the same as `rootProject.name`, but it can be more explanatory.

The `<id>` value must be a unique identifier over all plugins, typically a concatenation of the specified <control>GroupId</control> and <control>ArtifactId</control>.
Please note that it is impossible to change the `<id>` of a published plugin without losing automatic updates for existing installations.

## Adding Gradle Support to an Existing DevKit-Based IntelliJ Platform Plugin
Converting a [DevKit-based](using_dev_kit.md) plugin project to a Gradle-based plugin project can be done using the New Project Wizard to create a Gradle-based project around the existing DevKit-based project:
* Ensure the directory containing the DevKit-based IntelliJ Platform plugin project can be fully recovered if necessary.
* Delete all the artifacts of the DevKit-based project:
  * <path>.idea</path> directory
  * <path>[modulename].iml</path> file
  * <path>out</path> directory
* Arrange the existing source files within the project directory in the Gradle [SourceSet](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) format.
* Use the New Project Wizard as though creating a [new Gradle project](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) from scratch.
* On the [Project Naming/Artifact Coordinates Screen](#project-namingartifact-coordinates-screen) set the values to:
  * <control>GroupId</control> to the existing package in the initial source set.
  * <control>ArtifactId</control> to the name of the existing plugin.
  * <control>Version</control> to the same as the existing plugin.
  * <control>Name</control> to the name of the existing plugin.
    (It should be pre-filled from the <control>ArtifactId</control>)
  * Set the <control>Location</control> to the directory of the existing plugin.
* Click <control>Finish</control> to create the new Gradle-based plugin.
* [Add more modules](https://www.jetbrains.com/help/idea/gradle.html#gradle_add_module) using Gradle [Source Sets](https://www.jetbrains.com/help/idea/gradle.html#gradle_source_sets) as needed.

## Running a Simple Gradle-Based IntelliJ Platform Plugin
Gradle projects are run from the IDE's Gradle Tool window.

### Adding Code to the Project
Before running [`my_gradle_project`](#components-of-a-wizard-generated-gradle-intellij-platform-plugin), some code can be added to provide simple functionality.
See the [Creating Actions](working_with_custom_actions.md) tutorial for step-by-step instructions for adding a menu action.

### Executing the Plugin
Open the Gradle tool window and search for the <control>runIde</control> task:
* If it’s not in the list, hit the [Refresh](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#1eeec055) button at the top of the Gradle tool window.
* Or [Create a new Gradle Run Configuration](https://www.jetbrains.com/help/idea/create-run-debug-configuration-gradle-tasks.html).

![Gradle Tool Window](gradle_tasks_in_tool_window.png){width="398"}

Double-click on the <control>runIde</control> task to execute it.
See the IntelliJ IDEA help for more information about [Working with Gradle tasks](https://www.jetbrains.com/help/idea/gradle.html#96bba6c3).

To debug your plugin in a _standalone_ IDE instance, please see [How to Debug Your Own IntelliJ IDEA Instance](https://medium.com/agorapulse-stories/how-to-debug-your-own-intellij-idea-instance-7d7df185a48d) blog post.
