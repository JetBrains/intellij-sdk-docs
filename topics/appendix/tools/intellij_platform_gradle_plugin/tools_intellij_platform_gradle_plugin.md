<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin 2.x (EAP)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr id="tldr">

**Current Release**: %intellij-platform-gradle-plugin-version% (Early Access Preview)

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-intellij-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues)

**Slack**: [#intellij-platform-gradle-plugin](https://jetbrains-platform.slack.com/archives/C05C80200LS) on the [JetBrains Platform Slack](https://plugins.jetbrains.com/slack/)

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a plugin for the Gradle build system to help configure environments for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.
It is a successor of _[](tools_gradle_intellij_plugin.md) (1.x)_.

<snippet id="faq">

> See [](tools_intellij_platform_gradle_plugin_faq.md) and [](tools_intellij_platform_gradle_plugin_migration.md).
>
{title="FAQ"}

</snippet>

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

IntelliJ Platform Gradle Plugin 2.x requires the following minimal versions:
- IntelliJ Platform: **2022.3**
- Gradle: **8.2**

  See [the Gradle Installation guide](https://gradle.org/install/) on how to upgrade.
- Java Runtime: **17**

  See <control>Gradle JVM</control> in <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path>.

## Usage

> Please note that the plugin has a new ID `org.jetbrain.intellij.platform`.
>
{style="note"}

To apply the IntelliJ Platform Gradle Plugin to a project, add the following entry to the `plugins` block in the <path>build.gradle.kts</path> file:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

If migrating from the Gradle IntelliJ Plugin `1.x`, replace the old `org.jetbrains.intellij` identifier to `org.jetbrain.intellij.platform` and apply its latest `%intellij-platform-gradle-plugin-version%` version.

### Early Access Preview

To use the current Early Access Preview snapshot versions, add the following to the <path>settings.gradle.kts</path> file:

```kotlin
pluginManagement {
  repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    gradlePluginPortal()
  }
}
```

> The snapshot release is published with the constant version, creating a possibility for Gradle to resort to the cached version of the plugin.
> To update all dependencies in the dependency cache, use the `--refresh-dependencies` command line option.


### Plugins

The IntelliJ Platform Gradle Plugin consists of multiple [plugins](tools_intellij_platform_gradle_plugin_plugins.md) which can be applied in bundles ([](tools_intellij_platform_gradle_plugin_plugins.md#platform) or [](tools_intellij_platform_gradle_plugin_plugins.md#module)) or separately.

Subplugins architecture allows applying a subset of features, e.g., to provide the IntelliJ Platform dependency to a project submodule without creating unnecessary tasks.

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

Some repositories, by default, point to JetBrains Cache Redirector to provide better resource resolution.
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
    local("/Users/user/Applications/IntelliJ IDEA Ultimate.app")
  }
}
```

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### Setting Up Plugin Dependencies

To specify a dependency on a plugin, it is important to distinguish bundled plugins from plugins available in JetBrains Marketplace.

The [](tools_intellij_platform_gradle_plugin_dependencies_extension.md) provides a set of helpers to manage [plugin dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins):

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

## Code Instrumentation

TBD

The compiled code will be enhanced with:
- nullability assertions
- post-processing of forms created by IntelliJ GUI Designer

See also:
- [Tasks: `instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)
- [Extension: `intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode)
- [Task Awares: `JavaCompilerAware`](tools_intellij_platform_gradle_plugin_task_awares.md#JavaCompilerAware)
- [Dependencies: `javaCompiler()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#code-instrumentation)
- [Build Features: `useClosestVersionResolving`](tools_intellij_platform_gradle_plugin_gradle_properties.md#useClosestVersionResolving)

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

<include from="snippets.md" element-id="missingContent"/>
