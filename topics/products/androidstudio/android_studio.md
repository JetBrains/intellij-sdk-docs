<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Android Studio Plugin Development

<link-summary>Building plugins for Android Studio.</link-summary>

<tldr>

**IDE**: [Homepage](https://developer.android.com/studio), [Versions](#android-studio-releases-listing)

**Plugins**: [JetBrains Marketplace](https://plugins.jetbrains.com/androidstudio)

</tldr>

Android Studio plugins extend or add functionality to the [Android Studio IDE](https://developer.android.com/studio).

Android Studio plugins are not Android modules or apps to run in the Android operating system, such as smartphones or tablets.

## Android Studio Plugin Setup

### Matching Versions of the IntelliJ Platform with the Android Studio Version

For API compatibility, it is essential to match the version of the IntelliJ Platform APIs used for plugin development with the target version of Android Studio.
The version number of Android Studio contains the version of the underlying IntelliJ Platform APIs that were used to build it.

To find the version of the IntelliJ Platform used to build Android Studio, use the Android Studio <control>About</control> dialog screen:

![Example Android Studio About Dialog](android_studio_build.png){width="600"}

The actual Android Studio version doesn't entirely reflect the (YEAR.MAJOR.MINOR) version of the IntelliJ Platform.
The Android Studio version presented here is `2021.1.1 Patch 1`, but the `2021.1` part marked with the green rectangle refers to the IntelliJ IDEA release.

In this case, the (BRANCH.BUILD.FIX) version of the IntelliJ Platform is `211.7628.21` – marked with the blue rectangle – is corresponding to the IntelliJ IDEA version `2021.1.3`.

In the Gradle build script, both versions should be set: the build number and the release number.
To figure out the exact release number based on the build number, see [](android_studio_releases_list.md).

The [](#gradle-build-script) section below explains how to set the IntelliJ Platform version to match the target version of Android Studio.

### Android Studio Releases Listing

For the full list of Android Studio releases with more details, see [](android_studio_releases_list.md).

### Gradle Build Script

#### IntelliJ Platform Gradle Plugin (2.x)

Define a dependency using [`androidStudio()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `org.jetbrains.android` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

> Note that Android plugin is no longer bundled with the IDE.
>
> Use `plugin("org.jetbrains.android:$VERSION$")` instead of `bundledPlugin(...)`.
>
{title="Targeting 2023.3+"}

Minimum <path>build.gradle.kts</path> setup:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    androidStudio("<versionNumber>")
    bundledPlugin("org.jetbrains.android")
  }
}
```

#### Gradle IntelliJ Plugin (1.x)

{collapsible="true" default-state="collapsed"}

The use-case of developing for a non-IntelliJ IDEA IDE is reviewed in the [Plugins Targeting Alternate IntelliJ Platform-Based IDEs](dev_alternate_products.md#gradle1) section.
The particular example in that section discusses configuring a plugin project for PhpStorm, so the details for an Android Studio plugin project are reviewed here.

Here are the steps to configure the Gradle build script for developing a plugin to target Android Studio:

* The Gradle plugin attributes describing the configuration of the [IntelliJ Platform used to build the plugin project](configuring_plugin_project.md#intellij-platform-configuration) must be explicitly set.
  Continuing with the example [above](#matching-versions-of-the-intellij-platform-with-the-android-studio-version), set the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) value to `191.8026.42`.
  Alternatively, specify [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) to refer to a local installation of Android Studio.
* Android Studio plugin projects that use APIs from the Android plugin must declare a dependency on that plugin with ID `org.jetbrains.android`.
  Declare the dependency in the Gradle build script using the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) attribute.
* The best practice is to use the target version of Android Studio as the IDE Development Instance.
  Set the Development Instance to the (user-specific) absolute path to the target Android Studio application.

The snippet below is an example of configuring the Setup and Running DSLs in a Gradle build script.

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  // Define IntelliJ Platform against which to build the plugin project.

  // Same IntelliJ IDEA version (2019.1.4) as target 3.5 Android Studio:
  version.set("191.8026.42")

  // Use IntelliJ IDEA CE because it's the basis of the IntelliJ Platform:
  type.set("IC")

  // Require the Android plugin:
  plugins.set(listOf("org.jetbrains.android"))
}

tasks {
  runIde {
    // Absolute path to installed target 3.5 Android Studio to use as
    // IDE Development Instance (the "Contents" directory is macOS specific):
    ideDir.set(file("/Applications/Android Studio.app/Contents"))
  }
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

  // Require the Android plugin:
  plugins = ['org.jetbrains.android']
}

runIde {
  // Absolute path to installed target 3.5 Android Studio to use as
  // IDE Development Instance (the "Contents" directory is macOS specific):
  ideDir = file('/Applications/Android Studio.app/Contents')
}
```

</tab>
</tabs>

### plugin.xml

The dependency on the Android APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.

When using APIs from the Android plugin, declare a dependency:

```xml
<depends>org.jetbrains.android</depends>
```

As discussed in the [Plugin Dependencies](plugin_compatibility.md#declaring-plugin-dependencies) section of this guide, a plugin's dependency on [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) must be declared in <path>[plugin.xml](plugin_configuration_file.md)</path>.
When using Android Studio-specific features (APIs), a dependency on `com.intellij.modules.androidstudio` must be declared.

Otherwise, if only general IntelliJ Platform features (APIs) are used, then a dependency on `com.intellij.modules.platform` must be declared as discussed in [Plugin Compatibility with IntelliJ Platform Products](plugin_compatibility.md).

## Android Specific Extension Points

See [](android_plugin_extension_point_list.md).

## Additional Articles and Resources

* Discussion of extending Android Lint - [How to Register AndroidLintInspectionBase in IntelliJIdea Plugin](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360005018559-How-to-register-AndroidLintInspectionBase-in-IntellijIdea-Plugin)
* Grzegorz Matyszczak's article [How I Automated Creating Files for a New Screen with My Own Android Studio Plugin](https://proandroiddev.com/how-i-automated-creating-files-for-a-new-screen-with-my-own-android-studio-plugin-5d54b14ba6fa)
* Marcos Holgado's article series [Write an Android Studio Plugin (Part 1)](https://proandroiddev.com/write-an-android-studio-plugin-part-1-creating-a-basic-plugin-af956c4f8b50)
* [Android Plugin README](%gh-ij-android%/README.md)

## Open Source Plugins for Android Studio

When learning new development configurations, it is helpful to have some representative projects for reference:

* [ADB Idea](https://github.com/pbreault/adb-idea) plugin for Android Studio and IntelliJ IDEA that speeds up Android development.
* [Android postfix plugin](https://github.com/takahirom/android-postfix-plugin) for Android Studio.
* [Flutter Plugin](https://github.com/flutter/flutter-intellij).
* Bal Sikandar's [list of Android Studio plugins](https://github.com/balsikandar/Android-Studio-Plugins).

## FAQ

### How To Sync Gradle Project

Use [`GradleSyncInvoker.requestProjectSync()`](%gh-ij-android%/project-system-gradle/src/com/android/tools/idea/gradle/project/sync/GradleSyncInvoker.kt) for programmatic synchronization.
