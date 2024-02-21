<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin 2.x (EAP)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr id="tldr">

**Current Release**: %intellij-platform-gradle-plugin-version% (Early Access Preview)

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-intellij-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues)

**Slack**: [#intellij-platform-gradle-plugin](https://jetbrains-platform.slack.com/archives/C05C80200LS) on the [JetBrains Platform Slack](https://plugins.jetbrains.com/slack/)

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a plugin for the Gradle build system to help configure your environment for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.

It is going to replace the current _[](tools_gradle_intellij_plugin.md) (1.x)_ in the future.

> See [](tools_intellij_platform_gradle_plugin_migration.md) for FAQ.
>

<snippet id="EAP_Status">

> IntelliJ Platform Gradle Plugin 2.x is currently in **Early Access Preview** and may not support all features and project setups yet (see also [](tools_intellij_platform_gradle_plugin.md#requirements)).
> Please report bugs or problems in the GitHub issue tracker or Slack channel (see [here](tools_intellij_platform_gradle_plugin.md#tldr)).
>
> Any documentation issues should be reported using the feedback form on the bottom of this page.
> Please leave your email in case we need more details.
>
> Thanks a lot in advance for your feedback!
>
{title="Early Access Status" style="warning"}

</snippet>

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

> The snapshot release is published with the constant version, creating a possibility for Gradle to resort to the cached version of the plugin.
> If you wish to update all dependencies in the dependency cache, the command line option `--refresh-dependencies` should be used.

### Subplugins

The plugin was split into subplugins that can be applied separately.
This allows for applying only a subset of features, e.g. when you only use an IntelliJ Platform SDK dependency without creating any tasks.

#### org.jetbrains.intellij.platform
{#plugin.platform}

This plugin applies all project-level plugins, which brings the fully-flagged tooling for plugin development for IntelliJ-based IDEs.

It includes [](#plugin.base) and [](#plugin.tasks) subplugins.

#### org.jetbrains.intellij.platform.base
{#plugin.base}

The base plugin sets up all the custom configurations and transforms needed to manage the IntelliJ Platform SDK, JetBrains Runtime, CLI tools, and other plugins when they're added as dependencies.

#### org.jetbrains.intellij.platform.tasks
{#plugin.tasks}

Tasks plugin registers and preconfigures all tasks introduced by the IntelliJ Platform Gradle Plugin.
It can be omitted when referring to any IntelliJ Platform SDK dependencies without invoking tasks on project submodules.

#### org.jetbrains.intellij.platform.settings
{#plugin.settings}

If you define repositories within the <path>settings.gradle.kts</path> using the `dependencyResolutionManagement` Gradle, make sure to include the Settings plugin in your <path>settings.gradle.kts</path>.

#### org.jetbrains.intellij.platform.migration
{#plugin.migration}

The Migration Plugin is designed to assist in upgrading your configuration from Gradle IntelliJ Plugin version 1.x.
To prevent Gradle failing due to breaking changes, the `org.jetbrains.intellij.platform.migration` plugin was introduced to fill missing gaps and provide migration hints.

See [](tools_intellij_platform_gradle_plugin_migration.md) for more details.

## Configuration

> Auto-completion, Quick Documentation, and other code insight features are available for many extension functions and properties.
>
{title="Exploring Configuration Options"}

### Setting Up Repositories
{#configuration.repositories}

All IntelliJ Platform SDK artifacts are available via IntelliJ Maven repositories (see [](intellij_artifacts.md)), which exist in three variants:

- releases
- snapshots
- nightly (only selected artifacts)

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="recommendedCallout"/>

**Example #2:**

Build a plugin against a release version of the IntelliJ Platform with dependency on a plugin from the JetBrains Marketplace:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
    marketplace()
  }
}
```

See [](tools_intellij_platform_gradle_plugin_repositories_extension.md) on how to configure additional repositories.

#### Dependency Resolution Management
{#configuration.dependencyResolutionManagement}

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
      defaultRepositories()
    }
  }
}
```

#### Cache Redirector
{#configuration.cacheRedirector}

Some repositories, by default, point to JetBrains Cache Redirector to provide better resources resolution.
However, it is possible to use the direct repository URL, if available.

To switch off the default usage of JetBrains Cache Redirector, see the [](tools_intellij_platform_gradle_plugin_gradle_properties.md#useCacheRedirector) build feature.

### Setting Up IntelliJ Platform

Dependencies and [repositories](#configuration.repositories) are handled using explicit entries within `dependencies {}` and `repositories {}` blocks in <path>build.gradle.kts</path> file.

A minimum configuration for targeting IntelliJ IDEA Community 2023.3:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")
  }
}
```

The `intellijIdeaCommunity` in the previous sample is one of the extension functions available for adding IntelliJ Platform dependencies to the project.
See [](tools_intellij_platform_gradle_plugin_dependencies_extension.md) on how to target other IDEs.

#### Parametrize IntelliJ Platform Dependency
{id="dependenciesParametrizePlatform"}

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
{id="dependenciesLocalPlatform"}

It is possible to refer to the locally available IntelliJ-based IDE using the `local` helper function:

```kotlin
repositories {
  intellijPlatform {
    localPlatformArtifacts()
  }
}

dependencies {
  intellijPlatform {
    local("/Users/hsz/Applications/IntelliJ IDEA Ultimate.app")
  }
}
```

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### Setting Up Plugin Dependencies

To specify a dependency on a plugin, it is important to distinguish bundled plugins from plugins available in JetBrains Marketplace.

The [](tools_intellij_platform_gradle_plugin_dependencies_extension.md) provides a set of helpers to manage [plugins dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins):

```kotlin
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")

    bundledPlugin("com.intellij.java")
    plugin("org.intellij.scala", "2024.1.4")
  }
}
```

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

<include from="snippets.md" element-id="missingContent"/>
