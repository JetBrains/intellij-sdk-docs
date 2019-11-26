---
title: Getting Started with Gradle
---

Gradle is the preferred solution for creating IntelliJ Platform plugins.
The IntelliJ IDEA Ultimate and Community editions bundle the necessary plugins to support Gradle-based development.
These IntelliJ IDEA plugins are _Gradle_ and _Plugin DevKit_, which are enabled by default.
To verify these plugins are installed and enabled, see the help section about [Managing Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html).

> **WARNING** When adding additional repositories to your Gradle build script, make sure to always use HTTPS protocol.

* bullet list
{:toc}

## Creating a Gradle-Based IntelliJ Platform Plugin with New Project Wizard
IntelliJ IDEA supports creating new Gradle-based IntelliJ Platform plugin projects using the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
The Wizard creates all the necessary project files based on a few template inputs.

Before creating a new Gradle project, familiarize yourself with the IntelliJ IDEA help topic [Creating a new Gradle project](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html#create_gradle_project), which is a tutorial for creating general Gradle projects in IntelliJ IDEA.
This page emphasizes the steps in the process for creating IntelliJ Platform plugin projects that are Gradle-based.

Launch the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
It guides you through the Gradle project creation process with four screens.

### New Project Configuration Screen
On the first screen, the type of project is configured:
* From the _product category_ pane on the left, choose _Gradle_.
* Specify the _Project SDK_ based on the Java 8 JDK.
  This SDK will be the default JRE used to run Gradle, and the JDK version used to compile the plugin Java source.
  Based on the Project SDK, the IntelliJ IDEA Gradle Plugin will download the corresponding version of the IntelliJ Platform-based IDE automatically.
* In the _Additional Libraries and Frameworks_ panel, select _Java_ and _IntelliJ Platform Plugin_.
  These settings will be used for the remainder of this tutorial.
* Optionally:
  * To include support for the Kotlin language in the plugin, check the _Kotlin/JVM_ box (circled in green below.)
    This option can be selected with or without the _Java_ language.
  * To create the `build.gradle` file as a Kotlin build script rather than Groovy, check the _Kotlin DSL build script_ box (circled in magenta below.)

Then click _Next_:

![Select the Gradle facet in the Project Creation Wizard](img/step1_new_gradle_project.png){:width="800px"}

### Project Naming Screen
On this, the second screen of the Wizard, specify a [Group ID, Artifact ID, and plugin Version](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle) using [Maven naming](https://maven.apache.org/guides/mini/guide-naming-conventions.html) conventions.
* _Group ID_ is typically a Java package name, and it is used for the Gradle property `project.group` value in the project's `build.gradle` file.
  For this example, enter "com.your.company".
* _Artifact ID_ is the default name of the project JAR file (without version).
  It is also used for the Gradle property `rootProject.name` value in the project's `settings.gradle` file.
  For this example, enter "my_gradle_plugin".
* _Version_ is used for the Gradle property `project.version` value in the `build.gradle` file. 
  For this example, enter "1.0".

Click _Next_ to continue. 

### Gradle Settings Screen
The third screen prompts for Gradle-specific settings. 
All of these settings can be changed once the project is created via **Settings \| Build, Execution, Deployment \| Build Tools \| Gradle**. 
Select the [default settings](https://www.jetbrains.com/help/idea/gradle-settings.html) and click _Next_ to continue.

### Project Name and Location Screen
The final Wizard screen is for setting the [project name and location](https://www.jetbrains.com/help/idea/project-name-and-location.html#Project_Name_and_Location.xml).
The _Project name_ is pre-filled with the [Artifact ID](#project-naming-screen).

Note the choice of _Project format_ under _More Settings_ is somewhat superfluous.
Although an `.idea` directory or `.ipr` file is generated as the project is created and built, it is Gradle and the IntelliJ IDEA Gradle plugin that control many aspects of the project.  

Click _Finish_.

### Components of a Wizard-Generated Gradle IntelliJ Platform Plugin
For the [example](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) `my_gradle_plugin`, the New Project Wizard creates the directory content shown below:
* The default IntelliJ Platform `build.gradle` file.
  The contents are further discussed below.
* The Gradle Wrapper files, and in particular the `gradle-wrapper.properties` file, which specifies the version of Gradle to be used to build the plugin.
  If needed, the IntelliJ IDEA Gradle plugin will download the version of Gradle specified in this file.
* The `settings.gradle` file, containing a definition of the `rootProject.name`.
* The `META-INF` directory under the default `main` [SourceSet](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) contains the plugin [configuration file](/basics/plugin_structure/plugin_configuration_file.md).

```text
my_gradle_plugin
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   └── resources
    │       └── META-INF
    │           └── plugin.xml
    └── test
        ├── java
        └── resources
```

The New Project Wizard produces the `my_gradle_plugin` project `build.gradle` file shown below:
* There is no explicit `buildscript{}` in the file.
  The IntelliJ IDEA Gradle plugin dynamically creates a `buildscript{}`.
* Two plugins to Gradle are explicitly declared:
  * The [Gradle Java](https://docs.gradle.org/current/userguide/java_plugin.html) plugin.
  * The [IntelliJ IDEA Gradle plugin](https://github.com/JetBrains/gradle-intellij-plugin/).
* The _Group ID_ from the Wizard [Project Naming Screen](#project-naming-screen) is the `project.group` value.
* The _Version_ from the Wizard [Project Naming Screen](#project-naming-screen) is the `project.version` value.
* The `sourceCompatibility` line is injected to enforce using Java 8 JDK to compile Java source.
* The only comment in the file is a link to the [`README.md`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md) for the IntelliJ IDEA Gradle plugin, which is a reference for the DSLs defined by the plugin.
* The value of the Setup DSL attribute `intellij.version` specifies the version of the IntelliJ Platform to be used to build the plugin.
  It defaults to the version of IntelliJ IDEA that was used to run the New Project Wizard.
* The value of the Patching DSL attribute `patchPluginXml.changeNotes` is set to place holder text.

```groovy
  plugins {
      id 'java'
      id 'org.jetbrains.intellij' version '0.4.14'
  }
  
  group 'com.your.company'
  version '1.0' 
  sourceCompatibility = 1.8
  
  repositories {
      mavenCentral()
  } 
  dependencies {
      testCompile group: 'junit', name: 'junit', version: '4.12'
  }
  
  // See https://github.com/JetBrains/gradle-intellij-plugin/
  intellij {
      version '2019.1'
  }
  patchPluginXml {
      changeNotes """
        Add change notes here.<br>
        <em>most HTML tags may be used</em>"""
  }
```

#### Plugin Gradle Properties and Plugin Configuration File Elements
The Gradle properties `rootProject.name` and `project.group` will not, in general, match the respective `plugin.xml` elements `<name>` and `<id>`.
There is no IntelliJ Platform-related reason they should as they serve different functions.
The `<name>` element is often similar to the content root, but is more explanatory than the `rootProject.name`.
The `<id>` is a unique identifier over all plugins, typically a concatenation of the Maven `groupId` and `artifactId`; the default Gradle `project.group` property is only the `groupId`.


## Adding Gradle Support to an Existing DevKit-Based IntelliJ Platform Plugin
Converting a DevKit-based plugin project to a Gradle-based plugin project can be done using the New Project Wizard to create a Gradle-based project around the existing DevKit-based project: 
* Ensure the directory containing the DevKit-based IntelliJ Platform plugin project can be fully recovered if necessary.
* Delete all the artifacts of the DevKit-based project:
  * `.idea` directory
  * IML file
  * `out` directory
* Arrange the existing source files within the project directory in Gradle [SourceSet](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout) format. 
* Use the New Project Wizard as though creating a [new Gradle project](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) from scratch.
* On the [Project Naming Screen](#project-naming-screen) set the values to:
  * GroupID to the existing package in the initial source set.
  * ArtifactID to the name of the existing plugin.
  * Version to the same as the existing plugin.
* On the [Project Name and Location Screen](#project-name-and-location-screen) set the values to:
  * _Project name_ to the name of the existing plugin. 
    (It should be pre-filled from the _ArtifactID_)
  * Set the _Project location_ to the directory of the existing plugin.
* Click _Finish_ to create the new Gradle-based plugin.
* [Add more modules](https://www.jetbrains.com/help/idea/gradle.html#gradle_add_module) using Gradle [_Source Sets_](https://www.jetbrains.com/help/idea/gradle.html#gradle_source_sets) as needed.


## Running a Simple Gradle-Based IntelliJ Platform Plugin
Before running [`my_gradle_project`](#components-of-a-wizard-generated-gradle-intellij-platform-plugin), some code needs to be added to provide simple functionality.
* Using the code below, add a `HelloAction.java` class to the `src/main/java/` folder.
  For the sake of simplicity, no Java package is being used in this example. 

```java
  import com.intellij.openapi.actionSystem.*;
  import com.intellij.openapi.project.Project;
  import com.intellij.openapi.ui.Messages;
  
  public class HelloAction extends AnAction {
    public HelloAction() {
      super("Hello");
    }
  
    public void actionPerformed(AnActionEvent event) {
      Project project = event.getProject();
      Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
    }
  }
```

* Add the code below to the `<actions>` section of the `plugin.xml` file: 

```xml
  <group id="MyPlugin.SampleMenu" text="Greeting" description="Greeting menu">
    <add-to-group group-id="MainMenu" anchor="last"/>
    <action id="Myplugin.Textboxes" class="HelloAction" text="Hello" description="Says hello"/>
  </group>
```
 
* Open the Gradle tool window and search for the `runIde` task. 
  If it’s not in the list, hit the [Refresh](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html#1eeec055) button at the top of the Gradle window. 
  Or [Create a new Gradle Run Configuration](https://www.jetbrains.com/help/idea/create-run-debug-configuration-gradle-tasks.html).
  
![Gradle Tool Window](img/gradle_tasks_in_tool_window.png){:width="398px"}
   
* Double-click on the _runIde_ task to execute it.
  See the IntelliJ IDEA help for more information about [Working with Gradle tasks](https://www.jetbrains.com/help/idea/gradle.html#96bba6c3).

Finally, when `my_gradle_plugin` launches in the IDE development instance, there should be a new **Greeting** main menu to the right of the **Help** menu. 
