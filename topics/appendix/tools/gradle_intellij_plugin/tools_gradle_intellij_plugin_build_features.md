<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle IntelliJ Plugin (1.x) – Build Features
<primary-label ref="Obsolete"/>

<link-summary>Overview of Gradle IntelliJ Plugin (1.x) feature flags.</link-summary>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

With ongoing Gradle IntelliJ Plugin releases, new features are introduced that require additional research, collecting more feedback from developers, or should be enabled or disabled under particular conditions.
Build Features are an implementation of the feature flags concept and let you control some behaviors of the Gradle IntelliJ Plugin.

To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```
org.jetbrains.intellij.buildFeature.<buildFeatureName>=<true|false>
```

E.g., to disable the [`selfUpdateCheck`](#selfupdatecheck) feature, add this line:

```
org.jetbrains.intellij.buildFeature.selfUpdateCheck=false
```


## `noSearchableOptionsWarning`

When the [`buildSearchableOptions`](tools_gradle_intellij_plugin.md#tasks-buildsearchableoptions) doesn't produce any results, e.g., when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest [disabling the task](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options) for better performance.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.buildFeature.buildSearchableOptions=false
```


## `paidPluginSearchableOptionsWarning`

Due to IDE limitations, it is impossible to run the IDE in headless mode to collect searchable options for a paid plugin.
As paid plugins require providing a valid license and presenting a UI dialog, it is impossible to handle such a case, and the task will fail.
This feature flag displays the given warning when the task is run by a paid plugin.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.buildFeature.paidPluginSearchableOptionsWarning=false
```


## `selfUpdateCheck`

Checks whether the currently used Gradle IntelliJ Plugin is outdated and if a new release is available.
The plugin performs an update check on every run asking the GitHub Releases page for the redirection URL
to the latest version with `HEAD` HTTP request: `https://github.com/JetBrains/intellij-platform-gradle-plugin/releases/latest`.

If the current version is outdated, the plugin will emit a warning with its current and the latest version.

Feature respects the Gradle [`--offline`](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options) mode.

> It is strongly suggested to always use the latest available version. Older plugin versions may also not fully support the latest IDE releases.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.buildFeature.selfUpdateCheck=false
```
