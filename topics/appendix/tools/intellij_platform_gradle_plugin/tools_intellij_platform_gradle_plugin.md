<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin 2.x (EAP)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr>

**Current Release**: Early Access Preview

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-intellij-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues)

**Slack**: [#intellij-platform-gradle-plugin](https://jetbrains-platform.slack.com/archives/C05C80200LS) on the [JetBrains Platform Slack](https://plugins.jetbrains.com/slack/)

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a plugin for the Gradle build system to help configure your environment for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.

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
- Gradle: 8.1 and later

## Usage

> Please note that the plugin has a new ID `org.jetbrain.intellij.platform`.
>
{style="note"}

To use the current Early Access Preview snapshot versions, add the following to your <path>settings.gradle.kts</path> file:

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
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

### Subplugins

The plugin was split into subplugins that can be applied separately.
This allows for applying only a subset of features, e.g. when you only use an IntelliJ Platform SDK dependency without creating any tasks.

#### org.jetbrains.intellij.platform

{id="plugin.platform"}

This plugin applies all project-level plugins, which brings the fully-flagged tooling for plugin development for IntelliJ-based IDEs.

It includes [](#plugin.core) and [](#plugin.tasks) subplugins.

#### org.jetbrains.intellij.platform.core

{id="plugin.core"}

The base plugin sets up all the custom configurations and transforms needed to manage the IntelliJ Platform SDK, JetBrains Runtime, CLI tools, and other plugins when they're added as dependencies.

#### org.jetbrains.intellij.platform.tasks

{id="plugin.tasks"}

Tasks plugin registers and preconfigures all tasks introduced by the IntelliJ Platform Gradle Plugin.
It can be omitted when referring to any IntelliJ Platform SDK dependencies without invoking tasks on project submodules.

#### org.jetbrains.intellij.platform.settings

{id="plugin.settings"}

If you define repositories within the <path>settings.gradle.kts</path> using the `dependencyResolutionManagement` Gradle, make sure to include the Settings plugin in your <path>settings.gradle.kts</path>.

## Configuration

> Auto-completion, Quick Documentation, and other code insight features are available for many extension functions and properties.
>
{title="Exploring Configuration Options"}

### Setting Up Repositories

{id="configuration.repositories"}

All IntelliJ Platform SDK artifacts are available via IntelliJ Maven repositories (see [](intellij_artifacts.md)), which exist in three variants:

- releases
- snapshots
- nightly (only selected artifacts)

Assume you want to build your plugin against a release version of the IntelliJ Platform and you also have a dependency on a plugin from the Marketplace, then you would declare the following repositories:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
    marketplace()
  }
}
```

The `intellijPlatform` extension available for the `repositories {}` provides the following helper methods for adding repositories:

| Name                          | Description                                                                                                                                       |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| `releases`                    | IntelliJ Platform Releases repository.                                                                                                            |
| `snapshots`                   | IntelliJ Platform Snapshots repository.                                                                                                           |
| `nightly`                     | IntelliJ Platform Nightly repository, not available publicly.                                                                                     |
| `ivy`                         | Local Ivy repository for resolving local Ivy XML files used for describing artifacts like local IntelliJ Platform instance, bundled plugins, etc. |
| `marketplace`                 | JetBrains Marketplace Repository, hosts plugins for IntelliJ-based IDEs.                                                                          |
| `jetbrainsRuntime`            | JetBrains Runtime (JBR), CloudFront-based hosting from which specific JBR releases are fetched, if explicitly requested.                          |
| `binaryReleasesAndroidStudio` | Android Studio Binary Releases, required when running IntelliJ Plugin Verifier against Android Studio releases.                                   |
| `binaryReleases`              | IntelliJ IDEA Binary Releases, required when running IntelliJ Plugin Verifier against JetBrains IntelliJ-based IDE releases.                      |
| `recommended`                 | Applies a set of recommended repositories.                                                                                                        |

#### Dependency Resolution Management

{id="configuration.dependencyResolutionManagement"}

To access the IntelliJ Platform Gradle Plugin within the <path>settings.gradle.kts</path> to use with `dependencyResolutionManagement`, add:

```kotlin
import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

plugins {
    id("org.jetbrains.intellij.platform.settings") version "%intellij-platform-gradle-plugin-version%"
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        mavenCentral()

        intellijPlatform {
            recommended()
        }
    }
}
```

#### Cache Redirector

{id="configuration.cacheRedirector"}

Some repositories, by default, point to JetBrains Cache Redirector to provide better resources resolution.
However, it is possible to use the direct repository URL, if available.

To switch off the default usage of JetBrains Cache Redirector, see the [](tools_intellij_platform_gradle_plugin_build_features.md#useCacheRedirector) build feature.

### Setting Up IntelliJ Platform

Dependencies and [repositories](#configuration.repositories) are handled using explicit entries within `dependencies {}` and `repositories {}` blocks in <path>build.gradle.kts</path> file.

A minimum configuration for targeting IntelliJ IDEA Community 2023.3:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    recommended()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")
  }
}
```

The `intellijIdeaCommunity` in the previous sample is one of extension functions available for adding IntelliJ Platform dependencies to the project.
Other IDEs can be targeted using one the extensions listed in the table below.

| Extension               | Target IDE                                 |
|-------------------------|--------------------------------------------|
| `androidStudio`         | [Android Studio](android_studio.md)        |
| `clion`                 | [CLion](clion.md)                          |
| `fleetBackend`          | Fleet Backend                              |
| `gateway`               | Gateway                                    |
| `goland`                | [GoLand](goland.md)                        |
| `intellijIdeaCommunity` | [IntelliJ IDEA Community Edition](idea.md) |
| `intellijIdeaUltimate`  | [](idea_ultimate.md)                       |
| `phpstorm`              | [PhpStorm](phpstorm.md)                    |
| `pycharmCommunity`      | [PyCharm Community Edition](pycharm.md)    |
| `pycharmProfessional`   | [PyCharm Professional](pycharm.md)         |
| `rider`                 | [Rider](rider.md)                          |
| `rustRover`             | Rust Rover                                 |
| `writerside`            | Writerside                                 |

#### Parametrize IntelliJ Platform Dependency

As a fallback, `intellijPlatform` extension can be used to allow dynamic configuration of the target platform, e.g., via <path>gradle.properties</path>:

```
platformType = IC
platformVersion = 2023.3
```

The above Gradle properties can be referenced in the <path>build.gradle.kts</path> file with:

```kotlin
dependencies {
  intellijPlatform {
    val type = providers.gradleProperty("platformType")
    val version = providers.gradleProperty("platformVersion")

    create(type, version)
  }
}
```

The `intellijPlatform` helper accepts also the [`IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType) type:

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

dependencies {
  intellijPlatform {
    val version = providers.gradleProperty("platformVersion")

    create(IntelliJPlatformType.IntellijIdeaUltimate, version)
  }
}
```

#### Local IntelliJ Platform IDE Instance

It is possible to refer to the locally available IntelliJ-based IDE using the `local` helper function:

```kotlin
repositories {
  intellijPlatform {
    ...
    ivy()
  }
}

dependencies {
  intellijPlatform {
    local("/Users/hsz/Applications/IntelliJ IDEA Ultimate.app")
  }
}
```

Note that the `ivy` entry needs to be added to the `repositories {}` block as all local dependencies (local IDE, bundled plugins, etc.) rely on the Ivy repositories.

### Setting Up Plugin Dependencies

TODO

## Tasks

TODO

## Migration FAQ

### Unresolved 'idea' Plugin

Add an explicit dependency on the plugin in <path>build.gradle.kts</path>:

```kotlin
id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
```

<include from="snippets.md" element-id="missingContent"/>
