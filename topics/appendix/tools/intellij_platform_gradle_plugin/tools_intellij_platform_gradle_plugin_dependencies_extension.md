<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Dependencies Extension

<link-summary>IntelliJ Platform Gradle Plugin dependencies extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="Beta_Status"/>
<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

IntelliJ Platform Gradle Plugin enhances the `dependencies {}` configuration block by applying a nested `dependencies.intellijPlatform {}` extension.

This class provides methods for adding dependencies to different IntelliJ Platform [products](#default-target-platforms) and managing [local](#custom-target-platforms) dependencies.

It also includes methods for adding [plugins](#plugins) (including bundled), [JetBrains Runtime](#java-runtime), as well as [tools](#tools) like IntelliJ Plugin Verifier and Marketplace ZIP Signer.

> Corresponding required repositories must be defined in the `repositories {}` section, see [](tools_intellij_platform_gradle_plugin_repositories_extension.md).
>
{style="note"}

**Example:**

- setup Maven Central and [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories)
- target IntelliJ IDEA Community %ijPlatform%
- add dependency on the bundled Java plugin
- add IntelliJ Plugin Verifier, Marketplace ZIP Signer CLI, and code instrumentation tools
- add Test Framework for testing plugin with JUnit4

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")

    bundledPlugin("com.intellij.java")

    pluginVerifier()
    zipSigner()
    instrumentationTools()

    testFramework(TestFrameworkType.Platform.JUnit4)
  }

  // other dependencies, e.g., 3rd-party libraries
}
```

## Target Platforms

> Only one IntelliJ Platform dependency can be added to the project at a time.
>
{style="warning"}

### Default Target Platforms

See [](#custom-target-platforms) for non-default targets.

| Function                         | Description                                         |
|----------------------------------|-----------------------------------------------------|
| `androidStudio(version)`         | [Android Studio](android_studio.md)                 |
| `aqua(version)`                  | [Aqua](https://www.jetbrains.com/aqua/)             |
| `clion(version)`                 | [CLion](clion.md)                                   |
| `datagrip(version)`              | [DataGrip](data_grip.md)                            |
| `dataspell(version)`             | [DataSpell](https://www.jetbrains.com/dataspell/)   |
| `fleetBackend(version)`          | Fleet Backend                                       |
| `gateway(version)`               | Gateway                                             |
| `goland(version)`                | [GoLand](goland.md)                                 |
| `intellijIdeaCommunity(version)` | [IntelliJ IDEA Community](idea.md)                  |
| `intellijIdeaUltimate(version)`  | [IntelliJ IDEA Ultimate](idea_ultimate.md)          |
| `mps(version)`                   | [MPS](https://www.jetbrains.com/mps/)               |
| `phpstorm(version)`              | [PhpStorm](phpstorm.md)                             |
| `pycharmCommunity(version)`      | [PyCharm Community](pycharm.md)                     |
| `pycharmProfessional(version)`   | [PyCharm Professional](pycharm.md)                  |
| `rider(version)`                 | [Rider](rider.md)                                   |
| `rubymine(version)`              | [RubyMine](rubymine.md)                             |
| `rustrover(version)`             | [RustRover](https://www.jetbrains.com/rust/)        |
| `webstorm(version)`              | [WebStorm](webstorm.md)                             |
| `writerside(version)`            | [Writerside](https://www.jetbrains.com/writerside/) |

### Custom Target Platforms

| Function                | Description                                                                                                                                |
|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `create(type, version)` | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `local(localPath)`      | Adds a dependency on a local IntelliJ Platform instance. See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform).       |

See also:

- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)

## Plugins

> Use the correct function depending on whether the
targeted plugin is _bundled_ with the Target Platform or not.
>
{title="Bundled vs. Non-Bundled Plugins"}

| Function                       | Description                                                                                                                         |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `bundledPlugin(id)`            | Adds a dependency on a bundled IntelliJ Platform plugin.                                                                            |
| `bundledPlugins(ids)`          | Adds dependencies on bundled IntelliJ Platform plugins.                                                                             |
| `plugin(id, version, channel)` | Adds a dependency on a plugin for IntelliJ Platform.                                                                                |
| `plugin(notation)`             | Adds a dependency on a plugin for IntelliJ Platform using a string notation:<p>`pluginId:version` or `pluginId:version@channel`</p> |
| `plugins(notations)`           | Adds dependencies on plugins for IntelliJ Platform using a string notation:<p>`pluginId:version` or `pluginId:version@channel`</p>  |
| `localPlugin(localPath)`       | Adds a dependency on a local IntelliJ Platform plugin. Accepts path or a dependency on another module.                              |

See also:

- [](plugin_dependencies.md)

## Testing

To implement [tests](testing_plugins.md) for IntelliJ Platform plugin, it is necessary to explicitly add a dependency on the `test-framework` library containing the necessary test base classes.
In most cases, the `Platform.JUnit4` variant will be used:

```kotlin
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

dependencies {
  intellijPlatform {
    testFramework(TestFrameworkType.Platform.JUnit4)
  }
}
```

The provided `testFramework(type, version)` helper method makes it possible to add the base artifact to the test classpath or its variants, such as Java, Go, ReSharper, etc.

| Function                       | Description                                                                                                                                            |
|--------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| `testFramework(type, version)` | Adds a dependency on Test Framework or its variant using [`TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) type. |

> In rare cases, when the presence of a bundled <path>$PLATFORM_PATH$/lib/testFramework.jar</path> library is necessary (like in the case of [Rider](rider.md), as its `test-framework` is not published as an artifact),
> it is possible to attach it by using the [`TestFrameworkType.Platform.Bundled`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) type.
{style="warning"}

See also:
- [Types: `TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType)

## Tools

| Function                  | Description                                                                                                                                |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `pluginVerifier(version)` | Adds a dependency on [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md).                                                        |
| `zipSigner(version)`      | Adds a dependency on [Marketplace ZIP Signer](plugin_signing.md).                                                                          |
| `bundledLibrary(path)`    | **SEE NOTE BELOW** Adds a dependency on a bundled library JAR file of the current IntelliJ Platform, like <path>lib/annotations.jar</path> |

> Do not use **`bundledLibrary()`** in production, as direct access to the IntelliJ Platform libraries is not recommended.
>
> It should only be used as a workaround in case the IntelliJ Platform Gradle Plugin is not aligned with the latest IntelliJ Platform classpath changes.
>
{style="warning"}

See also:

- [](verifying_plugin_compatibility.md), [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [](plugin_signing.md), [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)

## Java Runtime

Using the `jetbrainsRuntime()` or `jetbrainsRuntimeExplicit()` dependency helpers, it is possible to load a custom version of JetBrains Runtime.
However, it is recommended to rely on the runtime bundled within the IntelliJ Platform dependency, if present.

| Function                                                  | Description                                                                                                                                                                                                                     |
|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <p>`jetbrainsRuntime()`</p>                               | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in version obtained with the current IntelliJ Platform if resolved from IntelliJ Maven Repository. |
| <p>`jetbrainsRuntime(version, variant, architecture)`</p> | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).                                                                                                   |
| <p>`jetbrainsRuntimeExplicit(explicitVersion)`</p>        | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in explicit version.                                                                               |

See [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) for more details.

## Code Instrumentation

The code instrumentation process handled with the [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) task, requires extra dependencies to work and properly adjust the Java bytecode.
There's the `instrumentationTools()` dependencies helper introduced to apply all required dependencies using default configuration, however, it is possible to add and configure them separately.

Adds a Java Compiler dependency for code instrumentation.
The version is determined by the IntelliJ Platform build number.
If the exact version is unavailable, the closest one is used, found by scanning all releases.

| Function                                              | Description                                                            |
|-------------------------------------------------------|------------------------------------------------------------------------|
| <p>`instrumentationTools()`</p>                       | A helper function to apply all required dependencies: `javaCompiler()` |
| <p>`javaCompiler()`</p><p>`javaCompiler(version)`</p> | Adds a dependency on Java Compiler.                                    |

- [](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)

<include from="snippets.md" element-id="missingContent"/>
