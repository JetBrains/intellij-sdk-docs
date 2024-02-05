<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Build Features

<link-summary>IntelliJ Platform Gradle Plugin build features.</link-summary>


The IntelliJ Platform Gradle Plugin build features dedicated to control some of the low-level Gradle plugin behaviors.
To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```
org.jetbrains.intellij.buildFeature.<buildFeatureName>=<true|false>
```

E.g., to disable the [](#selfUpdateCheck) feature, add this line:

```
org.jetbrains.intellij.platform.buildFeature.selfUpdateCheck=false
```


## noSearchableOptionsWarning
{#noSearchableOptionsWarning}

When the [](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) doesn't produce any results, e.g., when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest disabling it for better performance with [](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions).

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.buildFeature.buildSearchableOptions=false
```


## paidPluginSearchableOptionsWarning
{#paidPluginSearchableOptionsWarning}

Due to IDE limitations, it is impossible to run the IDE in headless mode to collect searchable options for a paid plugin.
As paid plugins require providing a valid license and presenting a UI dialog, it is impossible to handle such a case, and the task will fail.
This feature flag displays the given warning when the task is run by a paid plugin.

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.paidPluginSearchableOptionsWarning=false
```


## selfUpdateCheck
{#selfUpdateCheck}

Checks whether the currently used Gradle IntelliJ Plugin is outdated and if a new release is available.
The plugin performs an update check on every run asking the GitHub Releases page for the redirection URL
to the latest version with `HEAD` HTTP request: [](https://github.com/jetbrains/gradle-intellij-plugin/releases/latest).

If the current version is outdated, the plugin will emit a warning with its current and the latest version.

Feature respects the Gradle [`--offline`](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options) mode.

> It is strongly suggested to always use the latest available version. Older plugin versions may also not fully support the latest IDE releases.

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.selfUpdateCheck=false
```


## useCacheRedirector
{#useCacheRedirector}

By default, JetBrains Cache Redirector is used when resolving Maven repositories or any resources used by the IntelliJ Platform Gradle Plugin.
Due to limitations, sometimes it is desired to limit the list of remote endpoints accessed by Gradle.

It is possible to refer to the direct location (whenever it is possible) by switching off JetBrains Cache Redirector globally.

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.useCacheRedirector=false
```
