---
title: Prerequisites
---

Adding Gradle build support to an IntelliJ Platform Plugin requires a recent distribution the Gradle build system and IntelliJ IDEA.

### 1.0. Download and install Gradle

Download and install Gradle, following the instructions provided in the [Gradle User Guide](https://docs.gradle.org/current/userguide/installation.html). 

### 1.1. Download and install IntelliJ IDEA

Download and install either IntelliJ IDEA Ultimate or the IntelliJ IDEA Community Edition.

### 1.2. Ensure the Gradle plugin is enabled

The Gradle plugin is required if you want to add a [Gradle Run Configuration](https://www.jetbrains.com/idea/help/create-run-debug-configuration-for-gradle-tasks.html) to IntelliJ IDEA. You can verify the Gradle plugin is enabled by visiting **Settings | Plugins | Gradle**.

![Ensure the Gradle plugin is enabled](img/step0_gradle_enabled.png)

### 1.3. Create a new Gradle project 

There are two ways to add Gradle support to an existing project. Both will require adding a `build.gradle` file under the root directory, with at least the following contents:

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id "org.jetbrains.intellij" version "0.0.20"
}

apply plugin: 'idea'
apply plugin: 'org.jetbrains.intellij'
apply plugin: 'java'

intellij {
    version '15.0.1'
    plugins 'coverage'
    pluginName 'plugin_name_goes_here'
}
```

To add Gradle support to an existing project, copy the above Gradle buildscript into your `build.gradle` file, and with the Gradle executable on your system `PATH`, execute the following command on your system command line:

```
gradle cleanIdea
```

This will clean any existing IntelliJ IDEA configuration files and generate a new Gradle build configuration recognized by IntelliJ IDEA. Once your project refreshes, you should be able to view and click the Gradle tool window displayed under **View | Tool Windows | Gradle**. This indicates that IntelliJ IDEA recognizes the Gradle facet.

The second method to add Gradle support is by creating a new project from scratch in IntelliJ IDEA and copying over any existing sources. This may be the preferred option in case Gradle is not able to convert an existing project. To do so, create a new project in IntelliJ IDEA by opening **File | New... | Project**, and select Gradle from the dialog box: 

![Select the Gradle facet in the Project Creation Wizard](img/step1_new_gradle_project.png)

The Project Creation Wizard will now guide you through the Gradle project creation process. You will need to specify a Group ID, Artifact ID, and Version:

![Specify the Group, Artifact, and Version IDs](img/step2_group_artifact_version.png)

Finally, make sure Gradle is using the correct JVM. This should be the same version as the corresponding 64- or 32-bit Gradle distribution from [Step 1.0](#download-and-install-gradle):

![Verify the JVM is the correct version](img/step2_group_artifact_version.png)

Now, add the above script to your `build.gradle` file, overwriting any existing contents.

### 1.3. Running a simple plugin

Create the following directory structure:

![Gradle directory structure](img/gradle_directory_structure.png)

```java
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class HelloAction extends AnAction {
    public HelloAction() {
        super("Hello");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
    }
}
```

```xml
<idea-plugin version="2">
  <id>org.jetbrains</id>
  <name>gradle_plugin_demo</name>
  <version>0.0.1</version>
  <vendor email="dummy" url="dummy">dummy</vendor>

  <description><![CDATA[
      Sample plugin.<br>
    ]]></description>

  <change-notes><![CDATA[
      Release 0.0.1: Initial release.<br>
    ]]>
  </change-notes>

  <!-- please see https://confluence.jetbrains.com/display/IDEADEV/Build+Number+Ranges for description -->
  <idea-version since-build="131"/>

  <!-- please see https://confluence.jetbrains.com/display/IDEADEV/Plugin+Compatibility+with+IntelliJ+Platform+Products
       on how to target different products -->
  <!-- uncomment to enable plugin in all products
  <depends>com.intellij.modules.lang</depends>
  -->

  <extensions defaultExtensionNs="com.intellij">
  </extensions>

  <application-components>
  </application-components>

  <project-components>
  </project-components>

  <actions>
    <group id="MyPlugin.SampleMenu" text="Greeting" description="Greeting menu">
      <add-to-group group-id="MainMenu" anchor="last"  />
      <action id="Myplugin.Textboxes" class="HelloAction" text="Hello" description="Says hello" />
    </group>
  </actions>

</idea-plugin>
```

Add a new Gradle Run Configuration, configured like so:
 
![Gradle Run Configuration](img/gradle_run_config.png)

Launch the new Gradle Run Configuration. From the Run Window, there should be some output.

![Gradle task output](img/launched.png)

Finally, when the IDE launches, there should be a new menu to the right of **Help**. Your plugin has launched!

[Top](/tutorials/build_system.md)