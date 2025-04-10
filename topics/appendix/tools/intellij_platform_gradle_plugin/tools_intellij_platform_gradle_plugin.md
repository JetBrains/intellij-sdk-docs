<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin (2.x)

<link-summary>IntelliJ Platform Gradle Plugin user and migration guide.</link-summary>

<tldr id="tldr">

**Current Release**: %intellij-platform-gradle-plugin-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/intellij-platform-gradle-plugin/releases), [Issue Tracker](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues)

**JetBrains Platform Forum**: [Gradle Build Scripts](https://platform.jetbrains.com/c/intellij-platform/gradle-build-scripts/6) category

</tldr>

The _IntelliJ Platform Gradle Plugin 2.x_ is a [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
plugin for building, testing, verifying, configuring environments, and publishing plugins for IntelliJ-based IDEs.
It is the successor of _[](tools_gradle_intellij_plugin.md)_ which is no longer under active development.

Learn more about it in the [Release Announcement](https://blog.jetbrains.com/platform/2024/07/intellij-platform-gradle-plugin-2-0/).

<snippet id="faq">

> See [](tools_intellij_platform_gradle_plugin_faq.md) and [](tools_intellij_platform_gradle_plugin_migration.md).
>
{title="FAQ"}

</snippet>

## Requirements

IntelliJ Platform Gradle Plugin 2.x requires the following *minimal* versions:

- IntelliJ Platform: **2022.3**
- Gradle: **8.5**

  See [the Gradle Installation guide](https://gradle.org/install/) on how to upgrade.
- Java Runtime: **17**

  See <control>Gradle JVM</control> in <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path>.

> Consider using the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) which additionally provides CI setup covered with GitHub Actions.
>
{style="tip"}

## Setup

> Note that the plugin has a new ID `org.jetbrains.intellij.platform`.
>
{style="note"}

To apply the IntelliJ Platform Gradle Plugin to a project, add the following entry to the `plugins`
block in the Gradle build file:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id 'org.jetbrains.intellij.platform' version '%intellij-platform-gradle-plugin-version%'
}
```

</tab>
</tabs>

### Using Snapshot Release
{collapsible="true" default-state="collapsed"}

To use the latest snapshot version of this plugin, add the following to the Gradle Settings file:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

<path>settings.gradle.kts</path>
```kotlin
pluginManagement {
  repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    gradlePluginPortal()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

<path>settings.gradle</path>
```groovy
pluginManagement {
  repositories {
    maven {
      url 'https://oss.sonatype.org/content/repositories/snapshots/'
    }
    gradlePluginPortal()
  }
}
```

</tab>
</tabs>


> The current IntelliJ Platform Gradle Plugin Snapshot version is ![GitHub Snapshot Release](https://img.shields.io/nexus/s/org.jetbrains.intellij.platform/intellij-platform-gradle-plugin?server=https://oss.sonatype.org&label=)
>
> The snapshot release is published with a fixed version, so Gradle can resort to the cached version of the plugin.
>
> To update all dependencies in the dependency cache, use the `--refresh-dependencies` command line option.

### Plugins

The IntelliJ Platform Gradle Plugin consists of [plugins](tools_intellij_platform_gradle_plugin_plugins.md) which can be applied depending on the purpose.
By default, the [](tools_intellij_platform_gradle_plugin_plugins.md#platform) plugin (`org.jetbrains.intellij.platform`) should be applied to the main plugin project module.

When working in a [](#multi-module-project-structure) it is required to use [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin (`org.jetbrains.intellij.platform.module`) instead of creating tasks and configurations specific to the main module only.

The [](tools_intellij_platform_gradle_plugin_plugins.md#settings) plugin (`org.jetbrains.intellij.platform.settings`) allows for adding plugin development related repositories right in the <path>settings.gradle.kts</path> file if project configuration involves [](#configuration.dependencyResolutionManagement).

### Attaching Sources in the IDE

To attach IntelliJ Platform sources in the IDE, the <control>Download sources</control> setting has to be enabled in IDE versions 2023.2 and later.
This option respects the plugin's [](tools_intellij_platform_gradle_plugin_gradle_properties.md#downloadSources) property, which is enabled by default.

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

The attaching sources operation in the IDE is handled by the _Plugin DevKit_ plugin, thus it is recommended to always use the latest available IDE version.

If the opened compiled class has no sources available locally, the _Plugin DevKit_ plugin will detect the relevant source coordinates and provide an action to <control>Download IntelliJ Platform sources</control> or <control>Attach \$API_NAME\$ sources</control>.

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

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
    marketplace()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  mavenCentral()

  intellijPlatform {
    releases()
    marketplace()
  }
}
```

</tab>
</tabs>


See [](tools_intellij_platform_gradle_plugin_repositories_extension.md) on how to configure additional repositories.

#### Dependency Resolution Management

{#configuration.dependencyResolutionManagement}

To access the IntelliJ Platform Gradle Plugin within the Gradle Settings file to use with `dependencyResolutionManagement`, add:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

<path>settings.gradle.kts</path>

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

</tab>

<tab title="Groovy" group-key="groovy">

<path>settings.gradle</path>

```groovy
import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

plugins {
  id 'org.jetbrains.intellij.platform.settings' version '%intellij-platform-gradle-plugin-version%'
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

</tab>
</tabs>

#### Cache Redirector
{#configuration.cacheRedirector}

Some repositories, by default, point to JetBrains Cache Redirector to provide better resource resolution.
However, it is possible to use the direct repository URL, if available.

To switch off the default usage of JetBrains Cache Redirector, see the [](tools_intellij_platform_gradle_plugin_gradle_properties.md#useCacheRedirector) Gradle property.

### Setting Up IntelliJ Platform

Dependencies and [repositories](#configuration.repositories) are handled using explicit entries within `dependencies {}` and `repositories {}` blocks in the Gradle build file.

A minimum configuration for targeting IntelliJ IDEA Community 2023.3:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

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

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity('2023.3')
  }
}
```

</tab>
</tabs>


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

The above Gradle properties can be referenced in the Gradle build file with:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    val type = providers.gradleProperty("platformType")
    val version = providers.gradleProperty("platformVersion")

    create(type, version)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    def type = providers.gradleProperty('platformType')
    def version = providers.gradleProperty('platformVersion')

    create(type, version)
  }
}
```

</tab>
</tabs>


The `intellijPlatform` helper accepts also the [`IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType) type:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

dependencies {
  intellijPlatform {
    val version = providers.gradleProperty("platformVersion")

    create(IntelliJPlatformType.IntellijIdeaUltimate, version)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

dependencies {
  intellijPlatform {
    def version = providers.gradleProperty('platformVersion')

    create(IntelliJPlatformType.IntellijIdeaUltimate, version)
  }
}
```

</tab>
</tabs>

#### Local IntelliJ Platform IDE Instance

{#dependenciesLocalPlatform}

It is possible to refer to the locally available IntelliJ-based IDE using the `local` helper function:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

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

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    local '/Users/user/Applications/IntelliJ IDEA Ultimate.app'
  }
}
```

</tab>
</tabs>


<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### Setting Up Plugin Dependencies

To specify a dependency on a plugin, it is important to distinguish bundled plugins from plugins available in JetBrains Marketplace.

The [](tools_intellij_platform_gradle_plugin_dependencies_extension.md) provides a set of helpers to manage [plugin dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins):

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

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

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity '%ijPlatform%'

    bundledPlugin 'com.intellij.java'
    plugin 'org.intellij.scala', '2024.1.4'
  }
}
```

</tab>
</tabs>


<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### Multi-Module Project Structure

When working on a complex plugin, it is often convenient to split the code base into multiple submodules.
To avoid polluting submodules with tasks or configurations specific to the root module only (for example, tasks for signing, publishing, or running the plugin),
a dedicated sub-plugin was introduced.

The root module of the IntelliJ-based plugin project must apply the main [](tools_intellij_platform_gradle_plugin_plugins.md#platform) plugin as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id 'org.jetbrains.intellij.platform' version '%intellij-platform-gradle-plugin-version%'
}
```

</tab>
</tabs>


Any other included submodule must use the [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin instead:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.intellij.platform.module")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id 'org.jetbrains.intellij.platform.module'
}
```

</tab>
</tabs>


<include from="snippets.topic" element-id="missingContent"/>
