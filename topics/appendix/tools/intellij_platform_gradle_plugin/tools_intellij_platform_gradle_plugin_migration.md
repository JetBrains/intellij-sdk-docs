<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Migration Guide from Gradle IntelliJ Plugin

<link-summary>This is the IntelliJ Platform Gradle Plugin migration guide from the Gradle IntelliJ Plugin 1.x</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

## Plugin name change
As the `2.x` branch brings significant breaking changes to the plugin, we also decided to change its name from _Gradle IntelliJ Plugin_ to _IntelliJ Platform Gradle Plugin_ as the old one was confused with the Gradle support plugin for IntelliJ-based IDEs.
The plugin is published to the Gradle Plugin Portal with a new name as a new entry, and the old one is marked as deprecated.

## Plugin ID change
Plugin ID has changed from `org.jetbrains.intellij` to `org.jetbrains.intellij.platform`.
To apply is, use:

```kotlin
plugins {
    id("org.jetbrains.intellij.platform")
}
```

## Minimal Gradle version
The minimal required Gradle version is now `8.0`.

## `intellij` extension
The `intellij {}` extension is no longer available and was replaced with `intellijPlatform {}` — note that the available properties differ.

## `setupDependencies` task
To make IntelliJ SDK dependency available in the IDE for class resolution and code completion, there was the `setupDependencies` task introduced in the Gradle IntelliJ Plugin 1.x.
With IntelliJ Platform Gradle Plugin, such a task is no longer required, but if you are switching from the previous approach, Gradle still may want to execute it in the _afterSync_ phase.
To completely drop this approach, it is mandatory to remove its reference manualy in your IDE:

1. Open Gradle Tool Window
2. Right-click on the main module and select _Tasks Activation_
3. In the _Tasks Activation_ modal window, find and remove the `setupDependencies` entry.
