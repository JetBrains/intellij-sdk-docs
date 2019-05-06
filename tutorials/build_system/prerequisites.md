---
title: Getting Started with Gradle
---

Gradle is the preferred solution for creating IntelliJ Platform plugins.
The IntelliJ IDEA Ultimate and Community editions bundle the necessary plugins to support Gradle-based development.
These IntelliJ IDEA plugins are _Gradle_ and _Plugin DevKit_, which are enabled by default.
To verify these plugins are installed and enabled, see the help section about [Managing Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html).

* bullet list
{:toc}


## Creating a Gradle-Based IntelliJ Platform Plugin with New Project Wizard
IntelliJ IDEA supports creating new Gradle-based IntelliJ Platform plugin projects using the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
The Wizard creates all the necessary project files based on a few template inputs.

Before creating a new Gradle project, familiarize yourself with the IntelliJ IDEA help topic [Creating a new Gradle project](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html#create_gradle_project), which is a tutorial for creating general Gradle projects in IntelliJ IDEA.
This page emphasizes the steps in the process pertaining to Gradle-based IntelliJ Platform plugin projects.

Launch the [New Project Wizard](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle).
It guides you through the Gradle project creation process with four screens.

### New Project Configuration Screen
On the first screen the type of project is configured:
* From the _product category_ pane on the left, choose _Gradle_.
* Specify the _Project SDK_.
  Note that an [_IntelliJ Platform Plugin SDK_](/basics/getting_started/setting_up_environment.md#configuring-intellij-platform-sdk) can be used but is not required.
  However, select a _Project SDK_ based on the 1.8 JDK because it:
  * Will be the default JRE used to run Gradle (this can be changed later),
  * Will also be the Java version to use when compiling Java source.
    As of this writing, Java 1.8 is the most recent JDK supported by the IntelliJ Platform.
* In the _Additional Libraries and Frameworks_ panel, select _Java_ and _IntelliJ Platform Plugin_, then click _Next_.

![Select the Gradle facet in the Project Creation Wizard](img/step1_new_gradle_project.png){:width="800px"}

### Project Naming Screen
On this, the second screen of the Wizard, specify a [Group ID, Artifact ID, and plugin Version](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle) using [Maven naming](https://maven.apache.org/guides/mini/guide-naming-conventions.html) conventions.
* _Group ID_ is typically a Java package name, and it is used for the `project.group` value in the project's `build.gradle` file.
* _Artifact ID_ is the default name of the project JAR file (without version).
  It is also used for the `rootProject.name` value in the project's `settings.gradle` file.
* _Version_ is used for the `project.version` value in the `build.gradle` file. 

Click _Next_ to continue. 

### Gradle Settings Screen
The third screen prompts for Gradle-specific settings. 
All of these settings can be changed once the project is created via **Settings \| Build, Execution, Deployment \| Build Tools \| Gradle**. 
* Select whether to [_Use auto-import_](https://www.jetbrains.com/help/idea/gradle-settings.html).
* Select _Create directories for empty content roots automatically_.
  This selection will add a `src` directory to your project.
* Choose how to [_Group modules_](https://www.jetbrains.com/help/idea/creating-and-managing-modules.html#grouping-modules), either by IntelliJ IDEA logical grouping or by Qualified Names. 
  For new projects grouping modules by qualified names is recommended.
* Create a separate module per Gradle [`SourceSet`](https://docs.gradle.org/current/userguide/java_plugin.html#source_sets). 
  For a new project, this option results in `main` and `test` modules.
* It’s recommended to select the [_Use default gradle wrapper_](https://docs.gradle.org/current/userguide/gradle_wrapper.html) option.
  That way IntelliJ IDEA installs everything you need to run Gradle tasks. 
* Skip using a local Gradle distribution, at least initially.
  The Gradle plugin automatically downloads the preferred version of Gradle.
  A local Gradle distribution can be used, but ensure it meets the version requirements of the [Gradle plugin](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md). 
* Finally, specify a JVM for Gradle.
  It can be the _Project JDK_, which was specified as the [_Project SDK_](#new-project-configuration-screen) on the first screen. 
  
Click _Next_ to continue. 

![Verify the JVM is the correct version](img/step3_gradle_config.png){:width="800px"}

### Project Name and Location Screen
The final Wizard screen is for setting the [project name and location](https://www.jetbrains.com/help/idea/project-name-and-location.html#Project_Name_and_Location.xml).
The _Project name_ is pre-filled with the [Artifact ID](#project-naming-screen).

Note the choice of _Project format_ under _More Settings_ is somewhat superfluous.
Although an `.idea` directory or `.ipr` file is generated as the project is created and built, it is Gradle and the IntelliJ IDEA Gradle plugin that control many aspects of the project.  

Click _Finish_.

### Components of a Wizard-Generated Gradle IntelliJ Platform Plugin
The New Project Wizard produces the project layout shown below:
* The default IntelliJ Platform `build.gradle` file.
  The contents are further discussed below.
* The Gradle Wrapper files, and in particular the `gradle-wrapper.properties` file, which specifies the version of Gradle to be used to build the plugin.
  If needed, the IntelliJ IDEA Gradle plugin will download the version of Gradle specified in this file.
* The shell scripts `gradlew` and `gradlew.bat` for executing the plugin build with the Wrapper files. 
* The `settings.gradle` file, containing a definition of the `rootProject.name`.
* The structure under the `src` directory is the default Gradle [`SourceSet`](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout), tailored for an IntelliJ Platform Plugin.

```text
  my_gradle_plugin/
    build.gradle
    gradle/
      wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    gradlew
    gradlew.bat
    settings.gradle
    src/
      main
        java/
        resources/
          META-INF/
            plugin.xml
      test
        java/
        resources/
```

The New Project Wizard produces the `build.gradle` file shown below:
* There is no explicit `buildscript{}` in the file.
  The IntelliJ IDEA Gradle plugin dynamically creates a `buildscript{}`.
* Two plugins to Gradle are explicitly declared:
  * The [Gradle Java](https://docs.gradle.org/current/userguide/java_plugin.html) plugin.
  * The [IntelliJ IDEA Gradle plugin](https://github.com/JetBrains/gradle-intellij-plugin/).
* The _Group ID_ from the Wizard [Project Naming Screen](#project-naming-screen) is the `(project.)group` value.
* The _Version_ from the Wizard [Project Naming Screen](#project-naming-screen) is the `(project.)version` value.
* The `sourceCompatibility` line is injected to enforce using JDK 1.8.
* The only comment in the file is a link to the `README.md` for the IntelliJ IDEA Gradle plugin, which is a reference for the DSLs defined by the plugin.
* The value of the Setup DSL attribute `intellij.version` specifies the version of the IntelliJ Platform to be used to build the plugin.
  It defaults to the version of IntelliJ IDEA that was used to run the Wizard.
* The value of the Patching DSL attribute `patchPluginXml.changeNotes` is set to place holder text.

```text
  plugins {
      id 'java'
      id 'org.jetbrains.intellij' version '0.4.8'
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

### Opening a Wizard-Generated Gradle Project for the First Time
The first time a project is opened an _Import Project from Gradle_ dialog appears.
This dialog is essentially the same as the Wizard [Gradle Settings Screen](#gradle-settings-screen).
Opening the project is an import because the Gradle plugin translates some of the project's Gradle files into IntelliJ IDEA project working files.
For example, the import creates a `.idea` directory and a `<project_name>.iml` file.

These IntelliJ IDEA project working files can be saved with the project to avoid the import step in the future.
See the note at the end of the [Creating a new Gradle project](https://www.jetbrains.com/help/idea/gradle.html#project_create_gradle) about how newly created or imported projects are stored externally by default.

## Adding Gradle Support to an Existing DevKit-Based IntelliJ Platform Plugin
As of this writing, there isn't an IntelliJ IDEA Wizard to automatically convert a DevKit-based plugin project to a Gradle-based plugin project.
Conversion can be done, however, using the New Project Wizard to create a Gradle-based project around the existing DevKit-based project: 
* Ensure the directory containing the DevKit-based IntelliJ Platform plugin project can be fully recovered if necessary.
* Delete all the artifacts of the DevKit-based project:
  * `.idea` directory
  * IML file
  * `out` directory
* Arrange the existing source files within the project directory in Gradle `SourceSet` format. 
* Use [Gradle Plugin Wizard](#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard) as though creating a new project from scratch.
* On the [Project Naming Screen](#project-naming-screen) set the values to:
  * GroupID to the existing package in the initial source set.
  * ArtifactID to the name of the existing plugin.
  * Version to the same as the existing plugin.
* On the [Project Name and Location Screen](#project-project-name-and-location-screen) set the values to:
  * _Project name_ to the name of the existing plugin. 
    (It should be pre-filled from the _ArtifactID_)
  * Set the _Project location_ to the directory of the existing plugin.
* Click _Finish_ to create the new Gradle-based plugin.
* [Add more modules](https://www.jetbrains.com/help/idea/gradle.html#gradle_add_module) using Gradle [_Source Sets_](https://www.jetbrains.com/help/idea/gradle.html#gradle_source_sets) as needed.


## Running a Simple Gradle-Based IntelliJ Platform Plugin
Open the IntelliJ Platform SDK in IntelliJ IDEA Ultimate or Community Edition using **File \| Open** and navigating to the root directory of the local copy of the IntelliJ Platform SDK. Open the Gradle tool window and look for the _gradle_plugin_demo_ project:
* Drill down in the Gradle tool window to find **gradle_plugin_demo \| Tasks \| intellij | runIde**.
  If it’s not in the list, please hit the `Refresh` button at the top of the Gradle tool window. 
* Double-click on the _runIde_ task to execute it.
  See the IntelliJ IDEA help for more information about [Working with Gradle tasks](https://www.jetbrains.com/help/idea/gradle.html#96bba6c3).

Finally, when the _gradle_plugin_demo_ plugin launches in the IDE development instance, there should be a new menu to the right of the **Help** menu. 

## Configuring a Gradle-Based IntelliJ Platform Plugin Project
See the [Gradle plugin README](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#gradle) for more information about configuring IntelliJ Platform Plugin Projects. 

> **Note** Please make sure to always upgrade to the latest version of `gradle-intellij-plugin`.
Follow releases on [GitHub](https://github.com/JetBrains/gradle-intellij-plugin/releases). 

For example, to configure the **Sandbox Home** directory's location include the following in the project's `build.gradle` file:
```groovy
intellij {
  sandboxDirectory = "$project.buildDir/myCustom-sandbox"
}
```
See the [IDE Development Instances](/basics/ide_development_instance.md) page for more information about default Sandbox Home directory locations and contents. 
