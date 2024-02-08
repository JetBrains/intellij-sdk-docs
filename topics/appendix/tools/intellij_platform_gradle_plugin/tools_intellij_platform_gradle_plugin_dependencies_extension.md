<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Dependencies Extension

<link-summary>IntelliJ Platform Gradle Plugin dependencies extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

Extension class for managing IntelliJ Platform dependencies in a Gradle build script applied to the `DependencyHandler`.

This class provides methods for adding dependencies to different IntelliJ Platform products and managing local dependencies.

It also includes methods for adding IntelliJ Platform plugins, IntelliJ Platform bundled plugins, JetBrains Runtime, as well as IntelliJ Plugin Verifier and Marketplace ZIP Signer tools.

> Corresponding required repositories must be defined in `repositories {}` section, see [](tools_intellij_platform_gradle_plugin_repositories_extension.md).
>
{style="note"}

**Example:**

- setup Maven Central and `recommended()` [repositories](tools_intellij_platform_gradle_plugin_repositories_extension.md)
- target IntelliJ IDEA Community %ijPlatform%
- add dependency on bundled Java plugin
- add IntelliJ Plugin Verifier and Marketplace ZIP Signer CLI tools

```kotlin
repositories {

  mavenCentral()

  intellijPlatform {
    recommended()
  }
}

dependencies {

  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")

    bundledPlugin("com.intellij.java")

    pluginVerifier()
    zipSigner()
  }

  // other dependencies, e.g., 3rd-party libraries
}
```

> Just one IntelliJ Platform dependency can be added to the project at a time.
>
{style="warning"}

## Default Target Platforms

See [](#custom-target-platforms) for non-default targets.

| Function                         | Description                                                      |
|----------------------------------|------------------------------------------------------------------|
| `androidStudio(version)`         | Adds a dependency on [Android Studio](android_studio.md).        |
| `clion(version)`                 | Adds a dependency on [CLion](clion.md).                          |
| `fleetBackend(version)`          | Adds a dependency on Fleet Backend.                              |
| `gateway(version)`               | Adds a dependency on Gateway.                                    |
| `goland(version)`                | Adds a dependency on [GoLand](goland.md).                        |
| `intellijIdeaCommunity(version)` | Adds a dependency on [IntelliJ IDEA Community](idea.md).         |
| `intellijIdeaUltimate(version)`  | Adds a dependency on [IntelliJ IDEA Ultimate](idea_ultimate.md). |
| `phpstorm(version)`              | Adds a dependency on [PhpStorm](phpstorm.md).                    |
| `pycharmCommunity(version)`      | Adds a dependency on [PyCharm Community](pycharm.md).            |
| `pycharmProfessional(version)`   | Adds a dependency on [PyCharm Professional](pycharm.md).         |
| `rider(version)`                 | Adds a dependency on [Rider](rider.md).                          |
| `rustRover(version)`             | Adds a dependency on Rust Rover.                                 |
| `writerside(version)`            | Adds a dependency on Writerside.                                 |

## Custom Target Platforms

| Function                | Description                                                                                                                                |
|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `create(type, version)` | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `local(localPath)`      | Adds a dependency on a local IntelliJ Platform instance. See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform).       |

See also:

- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)

## Plugins

| Function                       | Description                                                                                                                  |
|--------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| `plugin(id, version, channel)` | Adds a dependency on a plugin for IntelliJ Platform.                                                                         |
| `plugin(notation)`             | Adds a dependency on a plugin for IntelliJ Platform using a string notation (`pluginId:version`, `pluginId:version@channel`) |
| `plugins(notations)`           | Adds dependencies on plugins for IntelliJ Platform using a string notation (`pluginId:version`, `pluginId:version@channel`)  |
| `bundledPlugin(id)`            | Adds a dependency on a bundled IntelliJ Platform plugin.                                                                     |
| `bundledPlugins(ids)`          | Adds dependencies on bundled IntelliJ Platform plugins.                                                                      |

See also:

- [](plugin_dependencies.md)

## Tools

| Function                  | Description                                                                         |
|---------------------------|-------------------------------------------------------------------------------------|
| `pluginVerifier(version)` | Adds a dependency on [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md). |
| `zipSigner(version)`      | Adds a dependency on [Marketplace ZIP Signer](plugin_signing.md).                   |

See also:

- [](verifying_plugin_compatibility.md), [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [](plugin_signing.md), [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)

## Java Runtime

| Function                                                                                            | Description                                                                                                                   |
|-----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|
| <p>`jetbrainsRuntime(version, variant, architecture)`</p><p>`jetbrainsRuntime(explicitVersion)`</p> | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance). |

<include from="snippets.md" element-id="missingContent"/>
