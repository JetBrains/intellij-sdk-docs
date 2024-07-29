<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin (2.x)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr id="tldr">

**Current Release**: %intellij-platform-gradle-plugin-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-intellij-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues)

**Slack**: [#intellij-platform-gradle-plugin](https://jetbrains-platform.slack.com/archives/C05C80200LS) on the [JetBrains Platform Slack](https://plugins.jetbrains.com/slack/)

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a plugin for the Gradle build system to help configure environments for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.
It is a successor of _[](tools_gradle_intellij_plugin.md)_.

<snippet id="faq">

> See [](tools_intellij_platform_gradle_plugin_faq.md) and [](tools_intellij_platform_gradle_plugin_migration.md).
>
{title="FAQ"}

</snippet>

## Requirements

IntelliJ Platform Gradle Plugin 2.x requires the following minimal versions:
- IntelliJ Platform: **2022.3**
- Gradle: **8.2**

  See [the Gradle Installation guide](https://gradle.org/install/) on how to upgrade.
- Java Runtime: **17**

  See <control>Gradle JVM</control> in <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path>.

## Usage

> Note that the plugin has a new ID `org.jetbrains.intellij.platform`.
>
{style="note"}

To apply the IntelliJ Platform Gradle Plugin to a project, add the following entry to the `plugins` block in the <path>build.gradle.kts</path> file:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

If migrating from the Gradle IntelliJ Plugin 1.x, replace the old `org.jetbrains.intellij` identifier to `org.jetbrains.intellij.platform` and apply its latest `%intellij-platform-gradle-plugin-version%` version.

### Snapshot Release

To use the latest snapshot versions, add the following to the <path>settings.gradle.kts</path> file:

```kotlin
pluginManagement {
  repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    gradlePluginPortal()
  }
}
```

[//]: # (> The current IntelliJ Platform Gradle Plugin Snapshot version is ![GitHub Snapshot Release]&#40;https://img.shields.io/nexus/s/org.jetbrains.intellij.platform/intellij-platform-gradle-plugin?server=https://oss.sonatype.org&label=&#41;)
> The current IntelliJ Platform Gradle Plugin Snapshot version is: `2.0.0-SNAPSHOT`
>
> The snapshot release is published with the constant version, creating a possibility for Gradle to resort to the cached version of the plugin.
>
> To update all dependencies in the dependency cache, use the `--refresh-dependencies` command line option.


### Plugins

The IntelliJ Platform Gradle Plugin consists of [plugins](tools_intellij_platform_gradle_plugin_plugins.md) which can be applied depending on the purpose.
By default, the [](tools_intellij_platform_gradle_plugin_plugins.md#platform) plugin (`org.jetbrains.intellij.platform`) should be applied to the main plugin project module.

When working with the [](#multi-module-project-structure) it is required to use [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin (`org.jetbrains.intellij.platform.module`) instead to creating tasks and configurations specific to the main module only.

The [](tools_intellij_platform_gradle_plugin_plugins.md#settings) plugin (`org.jetbrains.intellij.platform.settings`) allows for adding plugin development related repositories right in the <path>settings.gradle.kts</path> file if project configuration involves [](#configuration.dependencyResolutionManagement).


### Attaching Sources

To attach IntelliJ Platform sources in the IDE, the <control>Download sources</control> setting has to be enabled in IDE versions 2023.2 and later.
This option respects the [](tools_intellij_platform_gradle_plugin_gradle_properties.md#downloadSources) property, which is enabled by default.

<tabs>

<tab title="2023.3+">

In <ui-path>Settings | Advanced Settings</ui-path> enable option <control>Download sources</control> in section <ui-path>Build Tools. Gradle</ui-path>.
Then invoke <control>Reload All Gradle Projects</control> action from the <control>Gradle</control> tool window.

</tab>

<tab title="2023.2">

In <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path> enable <control>Download sources for dependencies</control>.
Then invoke the <control>Reload All Gradle Projects</control> action from the <control>Gradle</control> tool window.

</tab>

<tab title="Earlier versions">

No additional IDE settings are required.

</tab>

</tabs>

The attaching sources is handed by the Plugin DevKit plugin thus it's recommended to use always the latest available IDE release.

If the opened compiled class has no sources available locally, the Plugin DevKit plugin will detect the relevant source coordinates and provide an action to <control>Download IntelliJ Platform sources</control> or <control>Attach \$API_NAME\$ sources</control>.

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

To switch off the default usage of JetBrains Cache Redirector, see the [](tools_intellij_platform_gradle_plugin_gradle_properties.md#useCacheRedirector) Gradle property.

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

> When declaring a dependency on IntelliJ Platform, the IDE installer is resolved by default.
> IDE installers are OS-specific and contain [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) bundled, but have no EAP releases available.
>
> To resolve EAP releases instead, opt-out from installer releases with `useInstaller = false` passed to the dependency helper.
>
> **Important:** non-installer archives have no JetBrains Runtime (JBR) provided.
>
> Read more about [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-versions).
>
{style="note"}

#### Parametrize IntelliJ Platform Dependency
{#dependenciesParametrizePlatform}

As a fallback, `intellijPlatform` extension can be used to allow dynamic configuration of the target platform, for example, via <path>gradle.properties</path>:

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
{#dependenciesLocalPlatform}

It is possible to refer to the locally available IntelliJ-based IDE using the `local` helper function:

```kotlin
repositories {
  intellijPlatform {
    defaultRepositories()
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
    instrumentationTools()

    bundledPlugin("com.intellij.java")
    plugin("org.intellij.scala", "2024.1.4")
  }
}
```

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>


### Multi-Module Project Structure

When working on a complex plugin, it is often convenient to split the code base into multiple submodules.
To avoid polluting submodules with tasks or configurations specific to the root module only (e.g., tasks for signing, publishing, or running the plugin),
a dedicated subplugin was introduced.

The root module of the IntelliJ-based plugin project must apply the main [](tools_intellij_platform_gradle_plugin_plugins.md#platform) plugin as follows:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

Any other included submodule must use the [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin instead:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform.module")
}
```

<include from="snippets.md" element-id="missingContent"/>
