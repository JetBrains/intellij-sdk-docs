<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Dependencies Extension

<link-summary>IntelliJ Platform Gradle Plugin dependencies extension.</link-summary>

Extension class for managing IntelliJ Platform dependencies in a Gradle build script applied to the `DependencyHandler`.

This class provides methods for adding dependencies to different IntelliJ Platform products and managing local dependencies.

It also includes methods for adding JetBrains Runtime, IntelliJ Platform plugins, IntelliJ Platform bundled plugins, IntelliJ Plugin Verifier, and Marketplace ZIP Signer.

**Example:**

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

dependencies {
  // ...

  intellijPlatform {
    // ...

    create(IntelliJPlatformType.PhpStorm, "2023.3")
    create("PS", "2023.3")
    intellijIdeaCommunity("2023.3")
    local(file("/path/to/ide/"))
    jetbrainsRuntime("...")
    plugin("org.intellij.scala")
    bundledPlugin("com.intellij.java")
    pluginVerifier()
    zipSigner()
  }
}
```

> Just one IntelliJ Platform dependency can be added to the project at the time.
>
{style="warning"}

| Function                                                                               | Description                                                    |
|----------------------------------------------------------------------------------------|----------------------------------------------------------------|
| `create(type, version)`                                                                | Adds a dependency on the IntelliJ Platform.                    |
| `androidStudio(version)`                                                               | Adds a dependency on Android Studio.                           |
| `clion(version)`                                                                       | Adds a dependency on CLion.                                    |
| `fleetBackend(version)`                                                                | Adds a dependency on Fleet Backend.                            |
| `gateway(version)`                                                                     | Adds a dependency on Gateway.                                  |
| `goland(version)`                                                                      | Adds a dependency on GoLand.                                   |
| `intellijIdeaCommunity(version)`                                                       | Adds a dependency on IntelliJ IDEA Community.                  |
| `intellijIdeaUltimate(version)`                                                        | Adds a dependency on IntelliJ IDEA Ultimate.                   |
| `phpstorm(version)`                                                                    | Adds a dependency on PhpStorm.                                 |
| `pycharmCommunity(version)`                                                            | Adds a dependency on PyCharm Community.                        |
| `pycharmProfessional(version)`                                                         | Adds a dependency on PyCharm Professional.                     |
| `rider(version)`                                                                       | Adds a dependency on Rider.                                    |
| `rustRover(version)`                                                                   | Adds a dependency on Rust Rover.                               |
| `writerside(version)`                                                                  | Adds a dependency on Writerside.                               |
| `local(localPath)`                                                                     | Adds a local dependency on a local IntelliJ Platform instance. |
| `jetbrainsRuntime(version, variant, architecture)` `jetbrainsRuntime(explicitVersion)` | Adds a dependency on JetBrains Runtime.                        |
| `plugin(id, version, channel)`                                                         | Adds a dependency on a plugin for IntelliJ Platform.           |
| `bundledPlugin(id)`                                                                    | Adds a dependency on a bundled IntelliJ Platform plugin.       |
| `pluginVerifier(version)`                                                              | Adds a dependency on IntelliJ Plugin Verifier.                 |
| `zipSigner(version)`                                                                   | Adds a dependency on Marketplace ZIP Signer.                   |

See also:
- [](verifying_plugin_compatibility.md)
- [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)
- [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)


<include from="snippets.md" element-id="missingContent"/>
