<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle Properties

<link-summary>IntelliJ Platform Gradle Plugin provides a set of Gradle properties and build features to control its behaviors.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

The IntelliJ Platform Gradle Plugin exposes a number of build features to control some of the low-level Gradle plugin behaviors.
To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```
org.jetbrains.intellij.platform.<name>=<value>
```

## General Gradle Properties

### `intellijPlatformCache`
{#intellijPlatformCache}

The plugin uses a dedicated cache directory to store files related to the current project configuration files, such as:
- XML files generated for the [`localPlatformArtifacts()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) local Ivy repository
- coroutines Java agent file created by the [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) task

The IntelliJ Platform cache, by default, points to the <path>[rootProject]/.intellijPlatform</path>.
It is possible to override that path using with the `org.jetbrains.intellij.platform.intellijPlatformCache` property.

Example
:
```
org.jetbrains.intellij.platform.intellijPlatformCache=/path/to/intellijPlatformCache/
```

### `localPlatformArtifacts`
{#localPlatformArtifacts}

The [`localPlatformArtifacts()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) entry applied to the `repositories {}` block is required to apply to the project dependencies that need extra pre-processing before they can be correctly used by the IntelliJ Platform Gradle Plugin and loaded by Gradle.

This is resolved by creating an Ivy XML file in a dedicated directory, which by default points to the <path>[`intellijPlatformCache`](#intellijPlatformCache)/ivy/</path>.

It is possible to customize this path using the `org.jetbrains.intellij.platform.localPlatformArtifacts` property.

Example
:
```
org.jetbrains.intellij.platform.localPlatformArtifacts=/path/to/localPlatformArtifacts/
```

## Build Features

Build features are Gradle properties defined by the IntelliJ Platform Gradle Plugin to control specific features.
Such properties have a simplified form:

```
org.jetbrains.intellij.platform.buildFeature.<buildFeatureName>=<true|false>
```

E.g., to disable the [](#selfUpdateCheck) feature, add this line:

```
org.jetbrains.intellij.platform.buildFeature.selfUpdateCheck=false
```

### `downloadSources`
{#downloadSources}

Instruct the IDE that sources are needed to be downloaded when working with IntelliJ Platform Gradle Plugin.

Value is passed directly to the [Idea Gradle Plugin](https://docs.gradle.org/current/userguide/idea_plugin.html) to the `idea.module.downloadSources` property.

See also:
- [`IdeaModule.downloadSources`](https://docs.gradle.org/current/dsl/org.gradle.plugins.ide.idea.model.IdeaModule.html#org.gradle.plugins.ide.idea.model.IdeaModule:downloadSources)

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.downloadSources=true
```


### `noSearchableOptionsWarning`
{#noSearchableOptionsWarning}

When the [](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) doesn't produce any results, e.g., when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest disabling it for better performance with [](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions).

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.buildSearchableOptions=false
```

### `paidPluginSearchableOptionsWarning`
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

### `selfUpdateCheck`
{#selfUpdateCheck}

Checks whether the currently used IntelliJ Platform Gradle Plugin is outdated and if a new release is available.
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

### `useCacheRedirector`
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

### `useClosestVersionResolving`
{#useClosestVersionResolving}

Some dependencies are tied to IntelliJ Platform build numbers and hosted in the IntelliJ Dependencies Repository.
Despite this, certain versions (like EAP or nightly builds) might be absent.

To solve this, we fetch a list of all versions from the Maven repository and locate the closest match.
This method requires an additional remote repository request.
If undesired, this feature can be disabled to strictly match dependencies to your build version.

{style="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.buildFeature.useClosestVersionResolving=false
```
