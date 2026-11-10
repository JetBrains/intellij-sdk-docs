<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle Properties

<link-summary>IntelliJ Platform Gradle Plugin provides a set of Gradle properties to control its behaviors.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The IntelliJ Platform Gradle Plugin exposes a number of Gradle properties to control some of the low-level Gradle plugin behaviors.
To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```properties
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
```properties
org.jetbrains.intellij.platform.downloadSources=true
```


## `intellijPlatformCache`
{#intellijPlatformCache}

Specifies the location of the local IntelliJ Platform cache directory for storing files related to the current project, like:
- XML files generated for the [`localPlatformArtifacts`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) local Ivy repository
- self-update lock file used by the [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) task
- cached product release listings used to resolve IDE versions

> This directory should be excluded from versioning.
>
{style="warning"}

{type="narrow"}
Default value
: <path>[rootProject]/.intellijPlatform/</path>

Example
:
```properties
org.jetbrains.intellij.platform.intellijPlatformCache=/path/to/intellijPlatformCache/
```


## `intellijPlatformIdesCache`
{#intellijPlatformIdesCache}

Specifies the location of the IntelliJ Platform IDEs cache directory for storing downloaded IDE distributions
and related artifacts.
This cache is used to avoid re-downloading the same IDE versions across different project builds and can be shared between multiple projects.
Serialized IDE layout indices are stored in its <path>layoutIndex/</path> subdirectory.

**Note:** this directory can be shared across projects and should be excluded from versioning.

See also:
- [Extension: `intellijPlatform.caching.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-ides)

{type="narrow"}
Default value
: <path>[intellijPlatformCache](#intellijPlatformCache)/ides/</path>

Example
:
```properties
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
```properties
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
```properties
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
```properties
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
```properties
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
```properties
org.jetbrains.intellij.platform.paidPluginSearchableOptionsWarning=false
```


## `productsReleasesAndroidStudioUrl`
{#productsReleasesAndroidStudioUrl}

Specifies the URL from which the list of all Android Studio releases is fetched.
The JSON listing is used to resolve Android Studio distributions and select IDEs for the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.
Since `2.18.0`, custom mirrors must provide JSON rather than XML.

{type="narrow"}
Default value
: `https://jb.gg/android-studio-releases-list.json`

Example
:
```properties
org.jetbrains.intellij.platform.productsReleasesAndroidStudioUrl=https://...
```


## `productsReleasesJetBrainsIdesUrl`
{#productsReleasesJetBrainsIdesUrl}

Removed in `2.18.0`.
JetBrains IDE release listings are configured with [`productsReleasesCdnBuildsUrl`](#productsReleasesCdnBuildsUrl) instead.


## `selfUpdateCheck`
{#selfUpdateCheck}

Checks whether the currently used IntelliJ Platform Gradle Plugin is outdated and if a new release is available.
The update check reads Maven metadata from the [Gradle Plugin Portal](https://plugins.gradle.org/m2/).

If the current version is outdated, the plugin will emit a warning with its current and the latest version.

Feature respects the Gradle [`--offline`](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options) mode.

> It is strongly suggested to always use the latest available version. Older plugin versions may also not fully support the latest IDE releases.

{type="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.platform.selfUpdateCheck=false
```


## `subscriptionKey`
{#subscriptionKey}

Specifies the path to an IntelliJ IDEA subscription key file used as the default for [`intellijPlatform.subscriptionKey`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-subscriptionKey).
The key file is copied to <path>config/idea.key</path>, and `com.intellij.modules.ultimate` is removed from <path>config/disabled_plugins.txt</path> in each prepared sandbox.
Set [`PrepareSandboxTask.subscriptionKey`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox-subscriptionKey) to override this value for a specific sandbox task.

> Keep the key file outside version control. The property contains its path, not the subscription key itself.
>
{style="warning"}

{type="narrow"}
Default value
: empty (subscription activation is disabled)

Example
:
```text
org.jetbrains.intellij.platform.subscriptionKey=/path/to/idea.key
```

To configure the key-file path globally for all projects, set the corresponding Gradle project property through the `ORG_GRADLE_PROJECT_org.jetbrains.intellij.platform.subscriptionKey` environment variable.


## `testIdeBundledPluginsClasspathEnabled`
{#testIdeBundledPluginsClasspathEnabled}

Removed in `2.20.0`.
Bundled plugins declared in the IntelliJ Platform's <path>product-info.json</path> file are now always added to the classpaths used by the [`testIde`](tools_intellij_platform_gradle_plugin_tasks.md#testIde) and ordinary Gradle [`test`](tools_intellij_platform_gradle_plugin_tasks.md#test) tasks.
Use [`testIdeBundledPluginsClasspathExcludes`](#testIdeBundledPluginsClasspathExcludes) to exclude optional bundled plugins instead.


## `testIdeBundledPluginsClasspathExcludes`
{#testIdeBundledPluginsClasspathExcludes}

Specifies a comma-separated list of bundled plugin IDs or module IDs excluded from the classpaths used by the [`testIde`](tools_intellij_platform_gradle_plugin_tasks.md#testIde) and ordinary Gradle [`test`](tools_intellij_platform_gradle_plugin_tasks.md#test) tasks.
Bundled plugins that require an excluded plugin are excluded, too.
Plugins required by the tested plugin, its plugin dependencies, or explicitly declared bundled plugins and modules are never excluded.

Setting this property explicitly replaces all defaults, including the version-specific exclusions below.
Set it to an empty value to disable the default ID exclusions.

{type="narrow"}
Default value
: `com.intellij.openRewrite,com.intellij.ja,com.intellij.ko,com.intellij.zh,org.jetbrains.plugins.vue,org.jetbrains.plugins.clion.radler`

  The defaults additionally exclude `com.intellij.spring.boot` on IntelliJ Platform `2023.3` (build `233`) and `com.intellij.swagger` on `2024.1` (build `241`).
  These IDE versions do not ship the corresponding test-service implementations.

Example
:
```text
org.jetbrains.intellij.platform.testIdeBundledPluginsClasspathExcludes=
```


## `verifyPluginDefaultRecommendedIdes`
{#verifyPluginDefaultRecommendedIdes}

Controls whether [`intellijPlatform.pluginVerification.ides.recommended()`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides) is used automatically when no IntelliJ Plugin Verifier IDEs were configured explicitly.

{type="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.platform.verifyPluginDefaultRecommendedIdes=false
```


## `shimServerPort`
{#shimServerPort}

Specifies the starting port for the local Shim server.
The Shim server is used to proxy requests to the authorized custom plugin repositories registered with [`customPluginRepository()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#plugin-repositories).
If the port is occupied, the server tries subsequent ports until one is available.

{type="narrow"}
Default value
: `7348`

Example
:
```properties
org.jetbrains.intellij.platform.shimServerPort=7348
```


## `useCacheRedirector`
{#useCacheRedirector}

Controls whether Maven repositories added by the IntelliJ Platform Gradle Plugin use JetBrains Cache Redirector or their direct URLs.
Set this property to `false` to use direct URLs where an alternative is available.
Repositories without a direct alternative, such as the JetBrains Runtime repository, retain their configured URLs.
For isolated networks, see [](tools_intellij_platform_gradle_plugin_repositories_extension.md#mirrored-repositories).

{type="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.platform.useCacheRedirector=false
```


## `useDefaultSandboxExclusions`
{#useDefaultSandboxExclusions}

Controls whether Kotlin standard library and Kotlin Coroutines dependencies provided by the IntelliJ Platform are excluded from sandbox runtime classpaths by default.
The exclusions apply to sandboxes prepared for running the IDE and tests, including the [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) and [`prepareTestSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestSandbox) tasks.
Set this property to `false` to opt out of the default exclusions.

{type="narrow"}
Default value
: `true`

Example
:
```text
org.jetbrains.intellij.platform.useDefaultSandboxExclusions=false
```


## `addDefaultIntelliJPlatformDependencies`
{#addDefaultIntelliJPlatformDependencies}

Controls whether default IntelliJ Platform dependencies (like `com.intellij` and `intellij.rider`) should be added automatically when creating a dependency on the IntelliJ Platform.

{type="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.platform.addDefaultIntelliJPlatformDependencies=false
```



## `productsReleasesCdnBuildsUrl`
{#productsReleasesCdnBuildsUrl}

Specifies the URL from which JetBrains IDE release listings are fetched.
The `{type}` placeholder is replaced with the product code.
The JSON listing supplies versions, build numbers, release channels, and download links for IDE resolution, JetBrains Client, and IntelliJ Plugin Verifier targets.

{type="narrow"}
Default value
: `https://data.services.jetbrains.com/products?code={type}&fields=code,releases.type,releases.version,releases.build,releases.downloads`

Example
:
```properties
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
```properties
org.jetbrains.intellij.platform.verifyPluginProjectConfigurationMutedMessages=Kotlin Standard Library,Java Runtime is not JetBrains Runtime
```
