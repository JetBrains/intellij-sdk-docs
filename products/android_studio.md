---
title: Android Studio Plugin Development
---

## Introduction 
The Android Studio IDE is built on top of the IntelliJ Platform.
This relationship means that Android Studio plugins can be created using IntelliJ IDEA, Java or Kotlin, and the IntelliJ Platform.
Android Studio plugins can also be packaged and distributed at [plugins.jetbrains.com](https://plugins.jetbrains.com), like plugins for JetBrains IntelliJ Platform-based products.

Android Studio plugins extend or add functionality to the Android Studio IDE.
They are not Android modules or apps to be run in the Android operating system such as on a smartphone or tablet.

## Configuring IntelliJ Platform Projects for Android Studio Plugin Development
An Android Studio plugin project is an example of cross-development: plugins projects are created in IntelliJ IDEA but intended to be run in a different IntelliJ Platform-based IDE.
The configuration for an Android Studio plugin project is similar to the configuration for an IntelliJ IDEA plugin because both IDEs are based on the IntelliJ Platform.

To create a new Android Studio plugin project, follow the tutorial on the [Getting Started with Gradle](/tutorials/build_system/prerequisites.md) page.
The tutorial will produce a skeleton project suitable to use as a starting point for an Android Studio plugin.
Note that on the [New Project Configuration Screen](/tutorials/build_system/prerequisites.md#new-project-configuration-screen) of the New Project Wizard tutorial: 
* Choose Gradle from the product category pane as described in the tutorial, **not** _Android_.
* Choose either Java and/or Kotlin/JVM as desired in the _Additional Libraries and Frameworks_ pane, but the tutorial continues assuming Java.
  See the SDK code sample `kotlin_demo` for an example of a generic IntelliJ IDEA plugin based on Kotlin.
  
Some minor modifications to the skeleton project are needed, as discussed below.

### Matching Versions of the IntelliJ Platform with the Android Studio Version
For API compatibility it is important to match the version of the IntelliJ Platform APIs used for plugin development with the target version of Android Studio.
The version number of Android Studio contains the version of the underlying IntelliJ Platform APIs that were used to build it.

To find the version of the IntelliJ Platform used to build Android Studio, see the example _About_ dialog screen below.
In this case the (BRANCH.BUILD.FIX) version of the IntelliJ Platform is `191.8026.42`, which corresponds to the IntelliJ IDEA version 2019.1.4.
The [`build.gradle` configuration steps](#configuring-the-plugin-buildgradle-file) below explain how to set the IntelliJ Platform version to match the target version of Android Studio.

![Example Android Studio About Dialog](img/android_studio_build.png){:width="600px"}

Using Gradle as the basis for developing Android Studio plugins means the version of the IntelliJ IDEA IDE used to do the actual development does not have to match the targeted version of Android Studio.
This is because the version of the IntelliJ Platform API to be used when developing a plugin can easily be set independently of the version of the IntelliJ IDEA IDE being used to do the development.

### Configuring the Plugin build.gradle File
The use-case of developing for a non-IntelliJ IDEA IDE is reviewed in the [Plugins Targeting Alternate IntelliJ Platform-Based IDEs](/tutorials/build_system/gradle_guide.md#plugins-targeting-alternate-intellij-platform-based-ides) section of the [Configuring Gradle for IntelliJ Platform Plugins](/tutorials/build_system/gradle_guide.md) page.
The particular example in that section discusses configuring a plugin project for PhpStorm, so the details for an Android Studio plugin project will be reviewed in detail here.

Here are the steps to configure the `build.gradle` file for developing a plugin to target Android Studio:
* The Gradle plugin attributes describing the configuration of the [IntelliJ Platform used to build the plugin project](/tutorials/build_system/gradle_guide.md#configuring-the-gradle-plugin-for-building-intellij-platform-plugin-projects) must be explicitly set. 
  The type will be "IC" because the IntelliJ Platform is defined by the IntelliJ IDEA Community Edition.
  The BRANCH.BUILD.FIX number of the IntelliJ Platform (IntelliJ IDEA CE) is the same as for the Android Studio target version. 
  Although the FIX (tertiary) number may sometimes differ between versions of the two applications, it won't affect the match of the IntelliJ Platform.
  Continuing with the example [above](#matching-versions-of-the-intellij-platform-with-the-android-studio-version), set the `intellij.version` value to `191.8026.42`.
* All Android Studio plugin projects have a dependency on the Android Studio plugin, and must declare a dependency on that plugin.
  The dependency is declared using the Gradle plugin `intellij.plugins` attribute, which in this case lists the [directory name](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) of the plugin.
* The best practice is to use the target version of Android Studio as the IDE Development Instance.
  That enables running and debugging the plugin in the target (e.g., Android Studio) application.
  The choice of application to use for the IDE Development Instance is configured using the Gradle plugin attribute `runIde.ideaDirectory`.
  In this case it is set to the (user-specific) absolute path to the Android Studio application.

The snippet below is an example of configuring the Setup and Running DSLs in a `build.gradle` file to develop a plugin targeted at Android Studio. 
All other `build.gradle` contents from the New Project Wizard are necessary, but only the Android Studio-specific changes are shown.
The configuration uses IntelliJ IDEA Community Edition v2019.1.4 (build 191.8026.42) as the IntelliJ Platform against which the plugin project is built.
It uses Android Studio v3.5 (build 191.8026.42) as the IDE Development Instance in which the plugin project is run and debugged.
```groovy
  intellij {
    // Define IntelliJ Platform against which to build the plugin project.
    version '191.8026.42'  // Same IntelliJ IDEA version (2019.1.4) as target v3.5 Android Studio   
    type 'IC'              // Use IntelliJ IDEA CE because it's the basis of the IntelliJ Platform   
    // Require the Android plugin, Gradle will match the plugin version to intellij.version 
    plugins 'android'     
  }
  
  runIde {
      // Absolute path to installed v3.5 Android Studio to use as IDE Development Instance
      ideaDirectory '/Applications/apps/AndroidStudio/ch-0/191.5791312/Android Studio.app'
  }
```

### Configuring the Plugin plugin.xml File
As discussed in the [Declaring Plugin Dependencies](/basics/getting_started/plugin_compatibility.md#declaring-plugin-dependencies) section of this guide, a plugin's dependency on [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) must be declared in `plugin.xml`. 
In the case of developing a plugin for Android Studio, if Android Studio-specific features (APIs) are used, then a dependency on `com.intellij.modules.androidstudio` must be declared as shown in the code snippet below.
Otherwise, if only general, non-Android Studio-specific features (APIs) are used, then a dependency on `com.intellij.modules.platform` must be declared.
See the SDK Guide page [Plugin Compatibility with IntelliJ Platform Products](/basics/getting_started/plugin_compatibility.md) for more information.
```xml
  <depends>com.intellij.modules.androidstudio</depends>
```

## Additional Resources
* Welcome screen - custom
* Extending Android Lint - [How to register AndroidLintInspectionBase in IntellijIdea Plugin](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360005018559-How-to-register-AndroidLintInspectionBase-in-IntellijIdea-Plugin)  
* Grzegorz Matyszczak's article [How I automated creating files for a new screen with my own Android Studio Plugin](https://proandroiddev.com/how-i-automated-creating-files-for-a-new-screen-with-my-own-android-studio-plugin-5d54b14ba6fa)
* Marcos Holgado's article series [Write an Android Studio Plugin (Part 1)](https://proandroiddev.com/write-an-android-studio-plugin-part-1-creating-a-basic-plugin-af956c4f8b50)

## Open Source Plugins for Android Studio
When learning new development configurations it is helpful to have some representative projects for reference.  
* [](https://github.com/pbreault/adb-idea)
* [](https://github.com/takahirom/android-postfix-plugin)
* [](https://github.com/flutter/flutter-intellij)
* [Bal Sikandar's list of Android Studio plugins](https://github.com/balsikandar/Android-Studio-Plugins)
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
