<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Migrating from Gradle IntelliJ Plugin (1.x)

<link-summary>IntelliJ Platform Gradle Plugin (2.x) migration guide from Gradle IntelliJ Plugin (1.x)</link-summary>

> Consider using the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template) which additionally provides CI setup covered with GitHub Actions.
>
{style="tip"}

## Plugin Name Change

As the **2.x** branch brings significant breaking changes to the plugin, the name was changed from _Gradle IntelliJ Plugin_ to
_IntelliJ Platform Gradle Plugin_ as the old one was confused with the bundled Gradle support plugin in the IDE.

The plugin is published to the Gradle Plugin Portal with a new name as a new entry, and the old one is marked as deprecated.

## Minimum Gradle and Java Versions

The minimum required Gradle version is now **8.5** running on Java **17** or later.
See [](tools_intellij_platform_gradle_plugin.md#requirements).

## Plugin ID Change

Plugin ID has changed from `org.jetbrains.intellij` to `org.jetbrains.intellij.platform`.
To apply it, use:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

## Migration Plugin

The 2.x release introduces multiple [](tools_intellij_platform_gradle_plugin_plugins.md), featuring a dedicated one to help with migration from Gradle IntelliJ Plugin 1.x: [](tools_intellij_platform_gradle_plugin_plugins.md#migration).

When you apply the [](#plugin-id-change), Gradle will fail to process the <path>build.gradle.kts</path> file as the old [](#intellij-extension) doesn't exist anymore.
To fill all gaps and help users figure out required changes - right in the IDE - the `org.jetbrains.intellij.platform.migration` plugin was introduced:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
  id("org.jetbrains.intellij.platform.migration") version "%intellij-platform-gradle-plugin-version%"
}
```

## `intellij {}` Extension

The `intellij {}` extension is no longer available and was replaced with `intellijPlatform {}`.
Note that the available properties differ, see [](tools_intellij_platform_gradle_plugin_extension.md) for details.

### `intellij.pluginName`

Use: [`intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name):

```kotlin
intellijPlatform {
  pluginConfiguration {
    name = ...
  }
}
```

### `intellij.type`, `intellij.version`

Define the IntelliJ Platform dependency in `dependencies{}` block:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    create(type, version)
  }
}
```

See: [](tools_intellij_platform_gradle_plugin_dependencies_extension.md)

### `intellij.plugins`

The `intellij.plugins` property is no longer available.

> Bundled plugins are now defined separately from plugins of other sources (like JetBrains Marketplace).
>
{style="note"}

Define dependencies on plugins or bundled plugins in `dependencies {}` block instead:

**Example:**

Setting up dependencies on comma-separated plugins listed in `platformPlugins` and `platformBundledPlugins` properties from <path>gradle.properties</path>.

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    plugins(providers.gradleProperty("platformPlugins").map { it.split(',') })
    bundledPlugins(providers.gradleProperty("platformBundledPlugins").map { it.split(',') })
  }
}
```

See:
- [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins)
- [](tools_intellij_platform_gradle_plugin_faq.md#migrateToPluginId)

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### `intellij.localPath`

Define dependencies on local IDE instance in `dependencies {}` block:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    local(localPath)
  }
}
```

See: [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#custom-target-platforms)

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### `intellij.updateSinceUntilBuild`, `intellij.sameSinceUntilBuild`

The since/until properties in the <path>plugin.xml</path> file are now managed by the [`intellijPlatform`](tools_intellij_platform_gradle_plugin_extension.md) extension.

### `intellij.intellijRepository`,` intellij.pluginsRepositories`, `intellij.jreRepository`

### `intellij.sandboxDir`

Use the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

Use the `repositories {}` block to manage repositories instead.

See: [](tools_intellij_platform_gradle_plugin_repositories_extension.md)

### `intellij.downloadSources`

Downloading sources is managed by the _Plugin DevKit_ plugin in version 2024.1+.

### `intellij.ideaDependency`

Access the [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) object using the [`intellijPlatform.productInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) property.

## Tasks

### Running Tests

An [explicit dependency](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing) on a test framework is now required.

### `downloadRobotServerPlugin`

The Robot Server Plugin can now be downloaded using `plugins { robotServerPlugin() }` dependency helper when declaring a custom task.


### `runIdeForUiTests`

The `runIdeForUiTests` task is obsolete and should be replaced with an explicit declaration.

The task running IDE with the Robot Server Plugin should be declared now as a custom `runIde` task with plugin loaded:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runIdeForUiTests by intellijPlatformTesting.runIde.registering {
  task {
    jvmArgumentProviders += CommandLineArgumentProvider {
      listOf(
        "-Drobot-server.port=8082",
        "-Dide.mac.message.dialogs.as.sheets=false",
        "-Djb.privacy.policy.text=<!--999.999-->",
        "-Djb.consents.confirmation.enabled=false",
      )
    }
  }

  plugins {
    robotServerPlugin()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
val runIdeForUiTests by intellijPlatformTesting.runIde.registering {
  task {
    jvmArgumentProviders.add({
      [
        '-Drobot-server.port=8082',
        '-Dide.mac.message.dialogs.as.sheets=false',
        '-Djb.privacy.policy.text=<!--999.999-->',
        '-Djb.consents.confirmation.enabled=false',
      ]
    } as CommandLineArgumentProvider)
  }

  plugins {
    robotServerPlugin()
  }
}
```

</tab>
</tabs>


### `runPluginVerifier`

The task for running the IntelliJ Plugin Verifier is now called [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin).

Use [`intellijPlatform.pluginVerification`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification) extension to configure it.

### `setupDependencies`

To make the IntelliJ SDK dependency available in the IDE, the `setupDependencies` task was provided by Gradle IntelliJ Plugin 1.x.
This task is no longer required, but when switching from 1.x, Gradle may still want to execute it in the _afterSync_ phase.

> Due to the presence of the `setupDependencies` task in the <control>Tasks Activation</control>, the IDE may fail with the following exception if the task is not present:
> ```
> Task 'setupDependencies' not found in root project 'projectName'.
> ```
>
> To fix this problem, remove any references to `setupDependencies` task from your project.
>
{title="Task 'setupDependencies' not found" style="warning"}

To completely drop this approach, it is mandatory to remove its reference manually in the IDE.

<procedure title="Removing 'setupDependencies' Task">

1. Open <control>Gradle</control> Tool Window
2. Right-click on the main module and select <control>Tasks Activation</control>
3. In the <control>Tasks Activation</control> modal window, find and remove the `setupDependencies` entry.

</procedure>

Alternatively, edit the <path>.idea/workspace.xml</path> file and remove the `setupDependencies` entry.

## Other

### Unresolved 'idea-ext' Plugin

Add an explicit dependency on [the plugin](https://github.com/JetBrains/gradle-idea-ext-plugin) in <path>build.gradle.kts</path>:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.9"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id 'org.jetbrains.gradle.plugin.idea-ext' version '1.1.9'
}
```

</tab>
</tabs>
