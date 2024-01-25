<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin 2.x (EA)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr>

**Current Release**: Early Access

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-intellij-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues)

**Slack**: [#intellij-platform-gradle-plugin](https://jetbrains-platform.slack.com/archives/C05C80200LS) on the [JetBrains Platform Slack](https://plugins.jetbrains.com/slack/)

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a plugin for the Gradle build system to help configure your environment for
building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.

It is going to replace the current _[](tools_gradle_intellij_plugin.md) (1.x)_ in the future.

> This plugin is currently in **Early Access** and may not support all features and project setups yet (see also [](#requirements)).
> Please report bugs or problems in the above-linked issue tracker or Slack channel.
>
> Any documentation issues on this page should be reported using the feedback form on the bottom of this page.
>
> Thanks a lot in advance for your feedback!
>
{title="Early Access Status" style="warning"}

## Requirements

The following platforms and environments are supported:

- IntelliJ Platform: 2022.3 and later
- Java Runtime: 17 and later
- Gradle: 8.0 and later

## Usage

To use the current Early Access snapshot versions, add to your <path>settings.gradle.kts</path> file:

```kotlin
pluginManagement {
  repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    gradlePluginPortal()
  }
}
```

In the <path>build.gradle.kts</path> file, replace the existing reference to the [](tools_gradle_intellij_plugin.md) plugin (`org.jetbrains.intellij`) with:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "2.0.0-SNAPSHOT"
}
```

### Available Subplugins

The plugin has a new ID: `org.jetbrains.intellij.platform`, but also was split into subplugins.
This approach allows you to choose between applying all configurations and tasks at once, or applying them separately, e.g.,
to only use an IntelliJ SDK dependency without creating any tasks.

#### org.jetbrains.intellij.platform

{id="plugin.platform"}

This plugin applies all project-level plugins, which brings the fully-flagged tooling for plugin development for IntelliJ-based IDEs.

It includes [](#plugin.base) and [](#plugin.tasks) subplugins.

#### org.jetbrains.intellij.platform.base

{id="plugin.base"}

Base plugin registers all necessary custom configurations and dependencies transformers used for handling IntelliJ SDK,
JetBrains Runtime, or other plugins when used as dependencies.

#### org.jetbrains.intellij.platform.tasks

{id="plugin.tasks"}

Tasks plugin registers and preconfigures all tasks introduced by the IntelliJ Platform Gradle Plugin.
It can be omitted when referring to any IntelliJ SDK dependencies without invoking tasks on project submodules.

#### org.jetbrains.intellij.platform.settings

{id="plugin.settings"}

Settings plugin is required in <path>settings.gradle.kts</path> when using `RepositoryHandler` extensions when declaring the
IntelliJ Maven repository (see [](intellij_artifacts.md)) for the `dependencyResolutionManagement` Gradle mechanism.

## Configuration

> Auto-completion, Quick Documentation, and other code insight features are available for many extension functions and properties.
>
{title="Exploring Configuration Options"}

### Setting up IntelliJ Repositories

{id="intellij.repositories"}

All IntelliJ SDK artifacts are available via IntelliJ Maven repositories (see [](intellij_artifacts.md)), which exist in three variants:

- releases
- snapshots
- nightly (only select artifacts)

All required repositories for the project must be declared explicitly within the `intellijPlatform` extension:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
    snapshots()
    nightly()
  }
}
```

### Setting up IntelliJ Platform

Dependencies and [repositories](#intellij.repositories) are handled using explicit entries within `dependencies{}` and `repositories{}` blocks in <path>build.gradle.kts</path> file.

A minimum configuration for targeting IntelliJ IDEA Community 2023.3:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
  }
}


dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")
  }
}
```

The `intellijIdeaCommunity` in the previous sample is one of extension functions available for adding IntelliJ SDK dependencies to the project.
Other IDEs can be targeted using one the extensions listed in the table below.

| Extension               | Target IDE                                 |
|-------------------------|--------------------------------------------|
| `androidStudio`         | [Android Studio](android_studio.md)        |
| `clion`                 | [CLion](clion.md)                          |
| `gateway`               | Gateway                                    |
| `goland`                | [GoLand](goland.md)                        |
| `intellijIdeaCommunity` | [IntelliJ IDEA Community Edition](idea.md) |
| `intellijIdeaUltimate`  | [](idea_ultimate.md)                       |
| `phpstorm`              | [PhpStorm](phpstorm.md)                    |
| `pycharmCommunity`      | [PyCharm Community Edition](pycharm.md)    |
| `pycharmProfessional`   | [PyCharm Professional](pycharm.md)         |
| `rider`                 | [Rider](rider.md)                          |

As a fallback, `intellijPlatform` extension can be used to allow dynamic configuration of the target platform, e.g., via <path>build.properties</path>.

## Tasks

TODO

## Migration FAQ

### Unresolved 'idea' plugin

Add an explicit dependency on the plugin in <path>build.gradle.kts</path>:

```kotlin
id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
```

<include from="snippets.md" element-id="missingContent"/>
