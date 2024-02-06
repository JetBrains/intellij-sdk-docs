<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

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
  id("org.jetbrains.intellij.platform")
}
```

## Minimum Gradle version

The minimum required Gradle version is now `8.0`, see [Gradle Installation](https://gradle.org/install/) guide on how to upgrade.
See also [](tools_intellij_platform_gradle_plugin.md#requirements).

## `intellij` extension

The `intellij {}` extension is no longer available and was replaced with `intellijPlatform {}`.
Note that the available properties differ, see [](tools_intellij_platform_gradle_plugin_extension.md) for details.

## `setupDependencies` task

To make the IntelliJ SDK dependency available in the IDE, the `setupDependencies` task was provided by Gradle IntelliJ Plugin 1.x.
This task is no longer required, but when switching from 1.x, Gradle may still want to execute it in the _afterSync_ phase.
To completely drop this approach, it is mandatory to remove its reference manually in the IDE.

<procedure title="Removing setupDependencies task">

1. Open <control>Gradle</control> Tool Window
2. Right-click on the main module and select <control>Tasks Activation</control>
3. In the <control>Tasks Activation</control> modal window, find and remove the `setupDependencies` entry.

</procedure>

## Unresolved 'idea' Plugin

Add an explicit dependency on the plugin in <path>build.gradle.kts</path>:

```kotlin
id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
```



