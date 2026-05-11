<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle Properties

<link-summary>IntelliJ Platform Gradle Plugin provides a set of Gradle properties to control its behaviors.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The IntelliJ Platform Gradle Plugin exposes a number of Gradle properties to control some of the low-level Gradle plugin behaviors.
To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```
org.jetbrains.intellij.platform.<name>=<value>
```


## `downloadSources`
{#downloadSources}

Instruct the IDE that sources are needed to be downloaded when working with IntelliJ Platform Gradle Plugin.

Value is passed directly to the [IDEA Gradle Plugin](https://docs.gradle.org/current/userguide/idea_plugin.html) to the `idea.module.downloadSources` property.

See also:
- [`IdeaModule.downloadSources`](https://docs.gradle.org/current/dsl/org.gradle.plugins.ide.idea.model.IdeaModule.html#org.gradle.plugins.ide.idea.model.IdeaModule:downloadSources)

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.downloadSources=true
```


## `intellijPlatformCache`
{#intellijPlatformCache}

Specifies the location of the local IntelliJ Platform cache directory for storing files related to the current project, like:
- XML files generated for the [`localPlatformArtifacts`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) local Ivy repository
- self-update lock file used by the [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) task

> This directory should be excluded from versioning.
>
{style="warning"}

{type="narrow"}
Default value
: <path>[rootProject]/.intellijPlatform/</path>

Example
:
```
org.jetbrains.intellij.platform.intellijPlatformCache=/path/to/intellijPlatformCache/
```


## `intellijPlatformIdesCache`
{#intellijPlatformIdesCache}

Specifies the location of the IntelliJ Platform IDEs cache directory for storing downloaded IDE distributions
and related artifacts.
This cache is used to avoid re-downloading the same IDE versions across different project builds and can be shared between multiple projects.

**Note:** this directory can be shared across projects and should be excluded from versioning.

See also:
- [Extension: `intellijPlatform.caching.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-ides)

{type="narrow"}
Default value
: <path>[intellijPlatformCache](#intellijPlatformCache)/ides/</path>

Example
:
```
org.jetbrains.intellij.platform.intellijPlatformIdesCache=/path/to/ides-cache/
```

## `intellijPlatformIdesCacheEnabled`
{#intellijPlatformIdesCacheEnabled}

Indicates whether caching for IntelliJ Platform IDEs is enabled globally.

This property can still be overridden locally with `useCache` in an IntelliJ Platform dependency configuration.

See also:
- [Extension: `intellijPlatform.caching.ides.enabled`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-ides-enabled)

{type="narrow"}
Default value
: `false`

Example
:
```
org.jetbrains.intellij.platform.intellijPlatformIdesCacheEnabled=true
```

## `localPlatformArtifacts`
{#localPlatformArtifacts}

The [`localPlatformArtifacts()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) entry applied to the `repositories {}` block is required to apply to the project dependencies that need extra pre-processing before they can be correctly used by the IntelliJ Platform Gradle Plugin and loaded by Gradle.

{type="narrow"}
Default value
: <path>[intellijPlatformCache](#intellijPlatformCache)/localPlatformArtifacts/</path>

Example
:
```
org.jetbrains.intellij.platform.localPlatformArtifacts=/path/to/localPlatformArtifacts/
```


## `noSearchableOptionsWarning`
{#noSearchableOptionsWarning}

When the [](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) doesn't produce any results, for example, when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest disabling it for better performance with [](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions).

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.noSearchableOptionsWarning=false
```


## `forceBuildSearchableOptions`
{#forceBuildSearchableOptions}

Forces the [`buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) task to run even when descriptor analysis doesn't find any `Configurable` extension points.
This property overrides both the automatic skip optimization and the [`intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions) extension property.

{type="narrow"}
Default value
: `false`

Example
:
```
org.jetbrains.intellij.platform.forceBuildSearchableOptions=true
```


## `paidPluginSearchableOptionsWarning`
{#paidPluginSearchableOptionsWarning}

Due to IDE limitations, it is impossible to run the IDE in headless mode to collect searchable options for a paid plugin.
As paid plugins require providing a valid license and presenting a UI dialog, it is impossible to handle such a case, and the task will fail.
This feature flag displays the given warning when the task is run by a paid plugin.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.paidPluginSearchableOptionsWarning=false
```


## `productsReleasesAndroidStudioUrl`
{#productsReleasesAndroidStudioUrl}

Specifies the URL from which the list of all Android Studio releases is fetched.
This listing is later parsed by `ProductReleasesValueSource` to provide a list of IDEs matching the filtering criteria for running the IntelliJ Plugin Verifier tool with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

{type="narrow"}
Default value
: `https://jb.gg/android-studio-releases-list.xml`

Example
:
```
org.jetbrains.intellij.platform.productsReleasesAndroidStudioUrl=https://...
```


## `productsReleasesJetBrainsIdesUrl`
{#productsReleasesJetBrainsIdesUrl}

Specifies the URL from which the list of all JetBrains IDE releases is fetched.
This listing is later parsed by `ProductReleasesValueSource` to provide a list of IDEs matching the filtering criteria for running the IntelliJ Plugin Verifier tool with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

{type="narrow"}
Default value
: `https://www.jetbrains.com/updates/updates.xml`

Example
:
```
org.jetbrains.intellij.platform.productsReleasesJetBrainsIdesUrl=https://...
```


## `selfUpdateCheck`
{#selfUpdateCheck}

Checks whether the currently used IntelliJ Platform Gradle Plugin is outdated and if a new release is available.
The plugin performs an update check on every run asking the GitHub Releases page for the redirection URL
to the latest version with `HEAD` HTTP request: [](https://github.com/JetBrains/intellij-platform-gradle-plugin/releases/latest).

If the current version is outdated, the plugin will emit a warning with its current and the latest version.

Feature respects the Gradle [`--offline`](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options) mode.

> It is strongly suggested to always use the latest available version. Older plugin versions may also not fully support the latest IDE releases.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.selfUpdateCheck=false
```


## `verifyPluginDefaultRecommendedIdes`
{#verifyPluginDefaultRecommendedIdes}

Controls whether [`intellijPlatform.pluginVerification.ides.recommended()`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides) is used automatically when no IntelliJ Plugin Verifier IDEs were configured explicitly.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.verifyPluginDefaultRecommendedIdes=false
```


## `shimServerPort`
{#shimServerPort}

Specifies the default Shim server port at which the local webserver is run.
The Shim server is used to proxy requests to the authorized custom plugin repositories registered with [`customPluginRepository()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories).

{type="narrow"}
Default value
: `7348`

Example
:
```
org.jetbrains.intellij.platform.shimServerPort=7348
```


## `useCacheRedirector`
{#useCacheRedirector}

By default, JetBrains Cache Redirector is used when resolving Maven repositories or any resources used by the IntelliJ Platform Gradle Plugin.
Due to limitations, sometimes it is desired to limit the list of remote endpoints accessed by Gradle.

It is possible to refer to the direct location (whenever it is possible) by switching off JetBrains Cache Redirector globally.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.useCacheRedirector=false
```


## `addDefaultIntelliJPlatformDependencies`
{#addDefaultIntelliJPlatformDependencies}

Controls whether default IntelliJ Platform dependencies (like `com.intellij` and `intellij.rider`) should be added automatically when creating a dependency on the IntelliJ Platform.

{type="narrow"}
Default value
: `true`

Example
:
```
org.jetbrains.intellij.platform.addDefaultIntelliJPlatformDependencies=false
```



## `productsReleasesCdnBuildsUrl`
{#productsReleasesCdnBuildsUrl}

Specifies the URL from which the list of JetBrains IDE CDN release builds is fetched. This listing is used for mapping IDE releases to build numbers (for example, to download the corresponding JetBrains Client archive).

{type="narrow"}
Default value
: `https://data.services.jetbrains.com/products?code={type}&fields=code,releases.type,releases.version,releases.build`

Example
:
```
org.jetbrains.intellij.platform.productsReleasesCdnBuildsUrl=https://...
```


## `verifyPluginProjectConfigurationMutedMessages`
{#verifyPluginProjectConfigurationMutedMessages}

Allows muting specific messages reported by the [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration) task. The property accepts a comma-separated list of message patterns. Each pattern is matched against the message text using a case-sensitive substring search.

{type="narrow"}
Default value
: empty (no messages are muted)

Example
:
```
org.jetbrains.intellij.platform.verifyPluginProjectConfigurationMutedMessages=Kotlin Standard Library,Java Runtime is not JetBrains Runtime
```
