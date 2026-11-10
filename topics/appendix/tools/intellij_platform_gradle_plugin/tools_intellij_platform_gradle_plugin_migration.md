<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Migrating from Gradle IntelliJ Plugin (1.x)

<link-summary>IntelliJ Platform Gradle Plugin (2.x) migration guide from Gradle IntelliJ Plugin (1.x)</link-summary>

## Plugin Name Change

As the **2.x** branch brings significant breaking changes to the plugin, the name was changed from _Gradle IntelliJ Plugin_ to
_IntelliJ Platform Gradle Plugin_ as the old one was confused with the bundled Gradle support plugin in the IDE.

The plugin is published to the Gradle Plugin Portal with a new name as a new entry, and the old one is marked as deprecated.

## Minimum Gradle and Java Versions

The minimum required Gradle version is now **9.0.0** running on Java **17** or later.
The target IntelliJ Platform must be **2023.3** or later and may require a newer Java runtime than the JVM running Gradle.
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

Early 2.x releases introduced a dedicated migration plugin to ease the transition from Gradle IntelliJ Plugin 1.x.

After applying the [](#plugin-id-change), Gradle will fail to process the <path>build.gradle.kts</path> file as the old [](#intellij-extension) doesn't exist anymore.

> The `org.jetbrains.intellij.platform.migration` plugin was removed in IntelliJ Platform Gradle Plugin `2.12.0`.
> Use this migration guide as a manual checklist and apply only `org.jetbrains.intellij.platform`.
>
{style="note"}

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

### `intellij.intellijRepository`, `intellij.pluginsRepositories`, `intellij.jreRepository`

Use the `repositories {}` block to manage repositories instead.

See: [](tools_intellij_platform_gradle_plugin_repositories_extension.md)

### `intellij.sandboxDir`

Use the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

### `intellij.downloadSources`

Downloading and attaching sources is managed by the _Plugin DevKit_ plugin.
See [](tools_intellij_platform_gradle_plugin.md#attaching-sources-in-the-ide) and the [`downloadSources`](tools_intellij_platform_gradle_plugin_gradle_properties.md#downloadSources) Gradle property.

### `intellij.ideaDependency`

Access the [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) object using the [`intellijPlatform.productInfo`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-productInfo) property.

## Tasks

The verification task names changed meaning between 1.x and 2.x:

| Gradle IntelliJ Plugin 1.x task | IntelliJ Platform Gradle Plugin 2.x task |
|--------------------------------|-----------------------------------------|
| `runPluginVerifier` | [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin), which runs the IntelliJ Plugin Verifier |
| `verifyPlugin` | [`verifyPluginStructure`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginStructure), which validates the archive and descriptors |
| `verifyPluginConfiguration` | [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration), which checks the build configuration |

### Running Tests

For JVM plugin tests, declare the required [test framework dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing).
The Starter-based [`testUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi) task supplies its framework dependencies automatically.

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
intellijPlatformTesting.runIde {
  runIdeForUiTests {
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
> To fix this problem, remove any references to `setupDependencies` task from the project.
>
{title="Task 'setupDependencies' not found" style="warning"}

To completely drop this approach, it is mandatory to remove its reference manually in the IDE.

<procedure title="Removing 'setupDependencies' Task">

1. Open <control>Gradle</control> Tool Window
2. Right-click on the main module and select <control>Tasks Activation</control>
3. In the <control>Tasks Activation</control> modal window, find and remove the `setupDependencies` entry.

</procedure>

Alternatively, edit the <path>.idea/workspace.xml</path> file and remove the `setupDependencies` entry.

> The `setupDependencies` task has been removed in IntelliJ Platform Gradle Plugin 2.19.0.
>
{style="warning"}

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

## Upgrading Within 2.x
{#upgrading-2-x}

The current reference describes the latest released 2.x APIs.
When upgrading an older 2.x build, check the following compatibility and configuration changes as well as the [release notes](https://github.com/JetBrains/intellij-platform-gradle-plugin/releases).

### Minimum Versions

The minimum supported versions changed at these releases:

| Gradle plugin version | Minimum Gradle | Minimum target IntelliJ Platform |
|-----------------------|----------------|----------------------------------|
| `2.0.0` | `8.2` | `2022.3` |
| `2.2.0` | `8.5` | `2022.3` |
| `2.7.1` | `8.6` | `2022.3` |
| `2.10.0` | `8.13` | `2022.3` |
| `2.12.0` | `9.0.0` | `2022.3` |
| `2.14.0` | `9.0.0` | `2023.3` |

The JVM running Gradle and the target IDE's required Java version are separate requirements.
See [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md).

### Dependency and Cache Configuration

- Since `2.7.0`, `pluginModule(project(...))` packages a separate JAR under <path>lib/modules/</path> in the plugin distribution.
  To merge a module into the main plugin JAR, use `pluginComposedModule(project(...))` instead.
- Since `2.8.0`, [`intellijPlatform.caching.path`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-path) is read-only.
  Configure the project cache root through [`org.jetbrains.intellij.platform.intellijPlatformCache`](tools_intellij_platform_gradle_plugin_gradle_properties.md#intellijPlatformCache), not by assigning to `caching.path`.
- In `2.12.0`, deprecated positional `useInstaller` and `productMode` arguments were removed from dependency helpers.
  Set them in the configuration block instead:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    intellijIdea("%ijPlatform%") {
      useInstaller = false
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    intellijIdea('%ijPlatform%') {
      useInstaller = false
    }
  }
}
```

</tab>
</tabs>

Other deprecated APIs removed in `2.12.0` include:

| Removed API | Replacement |
|-------------|-------------|
| `intellijPlatform.cachePath` | Read-only `intellijPlatform.caching.path`; configure the root with the Gradle property above |
| Dependency configuration `useCustomCache` | `useCache` |
| `instrumentationTools()` | Automatic compiler dependency; use `javaCompiler(version)` to override it |
| `intellijPlatform.verifyPlugin` | `intellijPlatform.pluginVerification` |
| `pluginVerification.ides.ide()` / `ides()` | `create()` or `select()` |
| `aqua()` / `writerside()` | Discontinued target products; the dependency helpers are no longer available |

The `org.jetbrains.intellij.platform.migration` plugin and its legacy stub task classes, including `RunPluginVerifierTask`, were also removed in `2.12.0`.

### Changed Defaults

- Since `2.6.0`, `ideaVersion.untilBuild` has no default.
  Since `2.14.0`, `sinceBuild` defaults to the target IDE's major build number, such as `243`, rather than a major/minor pair.
  Explicitly configured values are still written to <path>plugin.xml</path>.
- Since `2.12.0`, the default sandbox container is <path>.intellijPlatform/sandbox/</path>, outside the build directory.
  Since `2.13.0`, sandbox paths also contain the project name.
  Use [`cleanSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#cleanSandbox), not `clean`, to remove these persistent sandboxes.
- Since `2.14.0`, the Java toolchain language version follows the target platform unless overridden, and recommended verifier IDEs are selected when no explicit targets are configured.
  Signing and publishing credentials default to `CERTIFICATE_CHAIN`, `PRIVATE_KEY`, `PRIVATE_KEY_PASSWORD`, and `PUBLISH_TOKEN` environment variables.
- Since `2.15.0`, the default verifier failure levels also include internal and override-only API usages.
- Since `2.18.0`, product release metadata uses JSON; custom Android Studio release mirrors must no longer serve XML.
  The `productsReleasesJetBrainsIdesUrl` property was removed in favor of [`productsReleasesCdnBuildsUrl`](tools_intellij_platform_gradle_plugin_gradle_properties.md#productsReleasesCdnBuildsUrl).
- Since `2.19.0`, Kotlin Standard Library and coroutines dependencies are excluded from sandbox runtime classpaths by default.
  The Settings plugin also defaults `kotlin.stdlib.default.dependency` to `false` unless explicitly configured.

### Task Configuration Changes

- In `2.19.0`, `PublishPluginTask.archiveFile` was replaced with [`archiveFiles`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-archiveFiles) to support multiple native distributions.
  Native variant archives are not signed automatically before publishing.
- The no-op `setupDependencies` task was removed in `2.19.0`; remove old IDE task-activation references as described [above](#setupdependencies).
- Since `2.19.0`, Plugin Verifier uses each IDE's bundled runtime by default.
  Set [`pluginVerification.useBundledRuntime`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-useBundledRuntime) to `false` to use the build runtime instead.
- Since `2.20.0`, the preconfigured Starter UI task is named [`testUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi) and uses <path>src/testUi</path>.
  The custom-task container remains `intellijPlatformTesting.testIdeUi`.
- In `2.20.0`, `testIdeBundledPluginsClasspathEnabled` was removed; bundled plugins are always considered for test classpaths.
  Use [`testIdeBundledPluginsClasspathExcludes`](tools_intellij_platform_gradle_plugin_gradle_properties.md#testIdeBundledPluginsClasspathExcludes) for optional exclusions.
  Required and explicitly declared plugin dependencies are retained.
