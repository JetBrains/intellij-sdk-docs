[//]: # (title: Android Studio Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Android Studio plugins extend or add functionality to the [Android Studio IDE](https://developer.android.com/studio).
Plugins can be written in [Kotlin](kotlin.md) or Java, or a mix of both, and are created using IntelliJ IDEA and the [IntelliJ Platform](intellij_platform.md).
It's also helpful to be familiar with [Java Swing](https://docs.oracle.com/javase/8/javase-clienttechnologies.htm).
Once completed, plugins can be packaged and distributed at [JetBrains Marketplace](https://plugins.jetbrains.com).

Android Studio plugins are not Android modules or apps to run in the Android operating system, such as smartphones or tablets.

## Configuring IntelliJ Platform Projects for Android Studio Plugin Development

To create a new Android Studio plugin project, follow the tutorial on the [Getting Started with Gradle](gradle_prerequisites.md) page.
The tutorial produces a skeleton project suitable to use as a starting point for an Android Studio plugin.
On the [New Project Screen](gradle_prerequisites.md#create-ide-plugin), choose <control>IDE Plugin</control> from the project generators list as described in the tutorial, **not** <control>Android</control>.
Some minor modifications to the skeleton project are needed, as discussed below.

### Matching Versions of the IntelliJ Platform with the Android Studio Version

For API compatibility, it is essential to match the version of the IntelliJ Platform APIs used for plugin development with the target version of Android Studio.
The version number of Android Studio contains the version of the underlying IntelliJ Platform APIs that were used to build it.

The actual Android Studio version doesn't entirely reflect the (YEAR.MAJOR.MINOR) version of the IntelliJ Platform.
The Android Studio version presented below is `2021.1.1 Patch 1`, but the `2021.1` part marked with the green rectangle refers to the IntelliJ IDEA release.

To find the version of the IntelliJ Platform used to build Android Studio, use the Android Studio <control>About</control> dialog screen.
An example is shown below.
In this case, the (BRANCH.BUILD.FIX) version of the IntelliJ Platform is `211.7628.21` – marked with the blue rectangle – is corresponding to the IntelliJ IDEA version `2021.1.3`.

In your Gradle build script, you should set both versions – build number and the release number – to the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) property.
To figure out the exact release number based on the build number, visit the [IntelliJ Repository Releases](https://www.jetbrains.com/intellij-repository/releases/) listing and check the `com.jetbrains.intellij.idea` section.

The [Gradle build script configuration steps](#configuring-the-plugin-gradle-build-script) section below explains how to set the IntelliJ Platform version to match the target version of Android Studio.

![Example Android Studio About Dialog](android_studio_build.png){width="600"}

### Android Studio Releases Listing

Below, you may find a list of recent Android Studio releases mapped to the relevant IntelliJ IDEA versions:

<include src="android_studio_releases.md" include-id="releases_table_short"></include>

For the full list of Android Studio releases with more details, visit the [Android Studio Releases List](android_studio_releases_list.md) page.

### Configuring the Plugin Gradle Build Script

The use-case of developing for a non-IntelliJ IDEA IDE is reviewed in the [Plugins Targeting Alternate IntelliJ Platform-Based IDEs](gradle_guide.md#plugins-targeting-alternate-intellij-platform-based-ides) section of the [Configuring Gradle for IntelliJ Platform Plugins](gradle_guide.md) page.
The particular example in that section discusses configuring a plugin project for PhpStorm, so the details for an Android Studio plugin project are reviewed here.

Here are the steps to configure the Gradle build script for developing a plugin to target Android Studio:
* The Gradle plugin attributes describing the configuration of the [IntelliJ Platform used to build the plugin project](gradle_guide.md#configuring-the-gradle-intellij-plugin-for-building-intellij-platform-plugin-projects) must be explicitly set.
  Continuing with the example [above](#matching-versions-of-the-intellij-platform-with-the-android-studio-version), set the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) value to `191.8026.42`.
  Alternatively, specify [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) to refer to a local installation of Android Studio.
* Android Studio plugin projects that use APIs from the `android` plugin must declare a dependency on that plugin.
  Declare the dependency in the Gradle build script using the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) attribute, which in this case lists the [directory name](tools_gradle_intellij_plugin.md#intellij-extension-pluginname) of the plugin.
* The best practice is to use the target version of Android Studio as the IDE Development Instance.
  Set the Development Instance to the (user-specific) absolute path to the target Android Studio application.

The snippet below is an example of configuring the Setup and Running DSLs in a Gradle build script specific to developing a plugin targeted at Android Studio.

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  // Define IntelliJ Platform against which to build the plugin project.

  // Same IntelliJ IDEA version (2019.1.4) as target 3.5 Android Studio:
  version.set("191.8026.42")

  // Use IntelliJ IDEA CE because it's the basis of the IntelliJ Platform:
  type.set("IC")

  // Require the Android plugin (Gradle will choose the correct version):
  plugins.set(listOf("android"))
}

runIde {
  // Absolute path to installed target 3.5 Android Studio to use as
  // IDE Development Instance (the "Contents" directory is macOS specific):
  ideDir.set(file("/Applications/Android Studio.app/Contents"))
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
  // Define IntelliJ Platform against which to build the plugin project.

  // Same IntelliJ IDEA version (2019.1.4) as target 3.5 Android Studio:
  version = '191.8026.42'

  // Use IntelliJ IDEA CE because it's the basis of the IntelliJ Platform:
  type = 'IC'

  // Require the Android plugin (Gradle will choose the correct version):
  plugins = ['android']
}

runIde {
  // Absolute path to installed target 3.5 Android Studio to use as
  // IDE Development Instance (the "Contents" directory is macOS specific):
  ideDir = file('/Applications/Android Studio.app/Contents')
}
```

</tab>
</tabs>


### Configuring the Plugin plugin.xml File

When using APIs from the `android` plugin, declare a dependency:

```xml
  <depends>org.jetbrains.android</depends>
```

As discussed in the [Plugin Dependencies](plugin_compatibility.md#declaring-plugin-dependencies) section of this guide, a plugin's dependency on [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) must be declared in <path>plugin.xml</path>.
When using Android Studio-specific features (APIs), a dependency on `com.intellij.modules.androidstudio` must be declared as shown in the code snippet below.
Otherwise, if only general IntelliJ Platform features (APIs) are used, then a dependency on `com.intellij.modules.platform` must be declared as discussed in [Plugin Compatibility with IntelliJ Platform Products](plugin_compatibility.md).

```xml
  <depends>com.intellij.modules.androidstudio</depends>
```

### Android Specific Extension Points

See _Android Plugin_ section in [](extension_point_list.md).

## Additional Articles and Resources

* Discussion of extending Android Lint - [How to Register AndroidLintInspectionBase in IntelliJIdea Plugin](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360005018559-How-to-register-AndroidLintInspectionBase-in-IntellijIdea-Plugin)
* Grzegorz Matyszczak's article [How I Automated Creating Files for a New Screen with My Own Android Studio Plugin](https://proandroiddev.com/how-i-automated-creating-files-for-a-new-screen-with-my-own-android-studio-plugin-5d54b14ba6fa)
* Marcos Holgado's article series [Write an Android Studio Plugin (Part 1)](https://proandroiddev.com/write-an-android-studio-plugin-part-1-creating-a-basic-plugin-af956c4f8b50)

## Open Source Plugins for Android Studio

When learning new development configurations, it is helpful to have some representative projects for reference:
* [ADB Idea](https://github.com/pbreault/adb-idea) plugin for Android Studio and Intellij IDEA that speeds up Android development.
* [Android postfix plugin](https://github.com/takahirom/android-postfix-plugin) for Android Studio.
* [Flutter Plugin](https://github.com/flutter/flutter-intellij).
* Bal Sikandar's [list of Android Studio plugins](https://github.com/balsikandar/Android-Studio-Plugins).

## FAQ

### How To Sync Gradle Project

Use `com.android.tools.idea.gradle.project.sync.GradleSyncInvoker.requestProjectSync()` for programmatic synchronization.

## See Also

[IntelliJ Android Plugin README](https://github.com/JetBrains/android#contents)
