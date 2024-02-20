<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Migration Guide from Gradle IntelliJ Plugin

<link-summary>IntelliJ Platform Gradle Plugin 2.x migration guide from Gradle IntelliJ Plugin 1.x</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

## Plugin name change

As the `2.x` branch brings significant breaking changes to the plugin, the name was changed from _Gradle IntelliJ Plugin_ to
_IntelliJ Platform Gradle Plugin_ as the old one was confused with the bundled Gradle support plugin in the IDE.
The plugin is published to the Gradle Plugin Portal with a new name as a new entry, and the old one is marked as deprecated.

## Plugin ID change

Plugin ID has changed from `org.jetbrains.intellij` to `org.jetbrains.intellij.platform`.
To apply it, use:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
}
```

## Migration Plugin

The 2.x release introduces [](tools_intellij_platform_gradle_plugin.md#subplugins), which contains also one to help with migration from the Gradle IntelliJ Plugin 1.x.

When you apply the [](#plugin-id-change), Gradle will fail to process the <path>build.gradle.kts</path> file as the old [](#intellij-extension) doesn't exist.
To fulfill all gaps and help you figure out what is required to change, right in the IDE, the `org.jetbrains.intellij.platform.migration` plugin was introduced:

```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
  id("org.jetbrains.intellij.platform.migration") version "%intellij-platform-gradle-plugin-version%"
}
```

## Minimum Gradle version

The minimum required Gradle version is now `8.1`, see [the Gradle Installation guide](https://gradle.org/install/) on how to upgrade.
See also [](tools_intellij_platform_gradle_plugin.md#requirements).

## intellij {} extension

The `intellij {}` extension is no longer available and was replaced with `intellijPlatform {}`.
Note that the available properties differ, see [](tools_intellij_platform_gradle_plugin_extension.md) for details.

### intellij.pluginName

Use: [`intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name):

```kotlin
intellijPlatform {
  pluginConfiguration {
    name = ...
  }
}
```

### intellij.type, intellij.version

Define the IntelliJ Platform dependency in `dependencies {}` block:

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

### intellij.plugins

The `intellij.plugins` property is no longer available.

> Bundled plugins are now separated from plugins available in JetBrains Marketplace.
>
{style="note"}

Define dependencies on plugins or bundled plugins in `dependencies {}` block instead:

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

See: [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins)

<include from="tools_intellij_platform_gradle_plugin_repositories_extension.md" element-id="localPlatformArtifacts_required"/>

### intellij.localPath

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

### intellij.updateSinceUntilBuild, intellij.sameSinceUntilBuild

The <path>plugin.xml</path> file is now fully managed by the [`intellijPlatform`](tools_intellij_platform_gradle_plugin_extension.md) extension`.

### intellij.intellijRepository, intellij.pluginsRepositories, intellij.jreRepository

### intellij.sandboxDir

Use the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

Use the `repositories {}` block to manage repositories instead.

See: [](tools_intellij_platform_gradle_plugin_repositories_extension.md)

### intellij.downloadSources

Downloading sources is managed with the DevKit Plugin in version 2024.1+.

### intellij.ideaDependency

Access now the [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) object using the [`intellijPlatform.productInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) property.

## Tasks

### downloadRobotServerPlugin

The Robot Server Plugin integration is not yet available. See [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi).

### runIdeForUiTests

Use [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi).

### runPluginVerifier

Task responsible for running the IntelliJ Plugin Verifier is now called [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin).

Use [`intellijPlatform.verifyPlugin`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin) extension to configure it.

### setupDependencies

To make the IntelliJ SDK dependency available in the IDE, the `setupDependencies` task was provided by Gradle IntelliJ Plugin 1.x.
This task is no longer required, but when switching from 1.x, Gradle may still want to execute it in the _afterSync_ phase.
To completely drop this approach, it is mandatory to remove its reference manually in the IDE.

<procedure title="Removing setupDependencies task">

1. Open <control>Gradle</control> Tool Window
2. Right-click on the main module and select <control>Tasks Activation</control>
3. In the <control>Tasks Activation</control> modal window, find and remove the `setupDependencies` entry.

</procedure>

## Unresolved 'idea-ext' Plugin

Add an explicit dependency on [the plugin](https://github.com/JetBrains/gradle-idea-ext-plugin) in <path>build.gradle.kts</path>:

```kotlin
plugins {
  id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
}
```
