<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Dependencies Extension

<link-summary>IntelliJ Platform Gradle Plugin dependencies extension.</link-summary>

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
- add JUnit4 test dependency
- add Test Framework for testing plugin with JUnit4

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

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

    testFramework(TestFrameworkType.Platform)
  }

  testImplementation("junit:junit:4.13.2")
  // other dependencies, e.g., 3rd-party libraries
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity '%ijPlatform%'

    bundledPlugin 'com.intellij.java'

    testFramework TestFrameworkType.Platform.INSTANCE
  }

  testImplementation 'junit:junit:4.13.2'
  // other dependencies, e.g., 3rd-party libraries
}
```

</tab>
</tabs>


## Target Platforms

> Only one IntelliJ Platform dependency can be added to the project at a time.
>
{style="warning"}

### Default Target Platforms

See [](#custom-target-platforms) for non-default targets.

| Function                                         | Description                                                      |
|--------------------------------------------------|------------------------------------------------------------------|
| `androidStudio(version, configure = {})`         | [Android Studio](android_studio.md)                              |
| `clion(version, configure = {})`                 | [CLion](clion.md)                                                |
| `datagrip(version, configure = {})`              | [DataGrip](data_grip.md)                                         |
| `dataspell(version, configure = {})`             | [DataSpell](https://www.jetbrains.com/dataspell/)                |
| `fleetBackend(version, configure = {})`          | [Fleet](https://www.jetbrains.com/fleet/) Backend                |
| `gateway(version, configure = {})`               | [Gateway](https://www.jetbrains.com/remote-development/gateway/) |
| `goland(version, configure = {})`                | [GoLand](goland.md)                                              |
| `intellijIdeaCommunity(version, configure = {})` | [IntelliJ IDEA Community](idea.md)                               |
| `intellijIdeaUltimate(version, configure = {})`  | [IntelliJ IDEA Ultimate](idea_ultimate.md)                       |
| `mps(version, configure = {})`                   | [MPS](https://www.jetbrains.com/mps/)                            |
| `phpstorm(version, configure = {})`              | [PhpStorm](phpstorm.md)                                          |
| `pycharmCommunity(version, configure = {})`      | [PyCharm Community](pycharm.md)                                  |
| `pycharmProfessional(version, configure = {})`   | [PyCharm Professional](pycharm.md)                               |
| `rider(version, configure = {})`                 | [Rider](rider.md)                                                |
| `rubymine(version, configure = {})`              | [RubyMine](rubymine.md)                                          |
| `rustRover(version, configure = {})`             | [RustRover](https://www.jetbrains.com/rust/)                     |
| `webstorm(version, configure = {})`              | [WebStorm](webstorm.md)                                          |

Notes:
- Writerside (`WRS`) is deprecated and no longer available as a target IntelliJ Platform.
- Aqua (`QA`) has also been removed as a target IntelliJ Platform.

### Custom Target Platforms

| Function                                | Description                                                                                                                                |
|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `create(type, version, configure = {})` | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `create(notation, configure = {})`      | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `local(localPath)`                      | Adds a dependency on a local IntelliJ Platform instance. See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform).       |

### Dependency Configuration Parameters

The last argument passed to the dependency helpers is a `configure` closure that allows to configure the dependency.
It allows you to specify the following parameters:

| Parameter           | Type                   | Description                                                                                                                         |
|---------------------|------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `type`              | `IntelliJPlatformType` | The type of the IntelliJ Platform dependency (target product).                                                                      |
| `version`           | `String`               | The version of the IntelliJ Platform dependency.                                                                                    |
| `productMode`       | `ProductMode`          | Describes a mode in which a product may be started. Default: `ProductMode.MONOLITH`.                                                |
| `useInstaller`      | `Boolean`              | Switches between resolving the IDE installer and a multi-OS archive from the IntelliJ Maven repository. Default: `true`.            |
| `useCustomCache`    | `Boolean`              | Switches between the Gradle cache and a custom cache directory. Default: `false`. See `GradleProperties.IntellijPlatformIdesCache`. |
| `configurationName` | `String`               | The name of the configuration to add the dependency to. Default: `Configurations.INTELLIJ_PLATFORM_DEPENDENCY_ARCHIVE`.             |

All of the above parameters support assignment of direct values and Provider instances.

See also:

- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)
- [Types: `ProductMode`](tools_intellij_platform_gradle_plugin_types.md#ProductMode)

## Target Versions

{#target-versions}

The IntelliJ Platform Gradle Plugin allows for using two types of IntelliJ Platform artifacts for development: installers and multi-OS ZIP archives.
Both have advantages and drawbacks, but in the 2.x releases, installers are now the default choice when setting up the project.

### Installers

{#target-versions-installers}

When declaring a dependency on IntelliJ Platform, the IDE installer is resolved by default.
The IntelliJ Platform installer is the IDE final distribution used by end-users for installing and running IDE in their machines.
Those artifacts are resolved from JetBrains Download CDN (download.jetbrains.com) or Android Studio CDN.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity '%ijPlatform%'
  }
}
```

</tab>
</tabs>


The listing of all present installers can be resolved with updates XML files for [JetBrains IDEs](https://www.jetbrains.com/updates/updates.xml) and [Android Studio](https://jb.gg/android-studio-releases-list.xml) as well as by executing the [`printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases) task.

IntelliJ Platform installers are OS-specific and contain bundled [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md), but are limited to public releases only.

To apply required repositories, use [](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) or explicit [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-platform-installers) helpers.

### Multi-OS Archives

{#target-versions-multi-os-archives}

It is still possible to use Multi-OS ZIP archives resolved from [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories).

To enable resolving this kind of artifacts, opt-out from the installer dependencies by adding `useInstaller = false` argument to helpers described in [](#target-platforms), like:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%", useInstaller = false)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity "%ijPlatform%", false
  }
}
```

</tab>
</tabs>


The Multi-OS Archives don't bundle [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) needed to run the IDE locally, perform testing, and other crucial operations.
Therefore, it is required to explicitly add a dependency on JetBrains Runtime (JBR) by adding extra `jetbrainsRuntime()` repository and dependency entries.

It is advised to rely on installer releases, but targeting EAP multi-OS archives helps when making sure your plugin will work in the upcoming IDE releases.

To apply required repositories, use [](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) or explicit [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories) helpers.

## Plugins

Make sure that required [plugin repositories](tools_intellij_platform_gradle_plugin_repositories_extension.md#plugin-repositories) are defined to access non-bundled plugins.

See also:

- [](plugin_dependencies.md)

> Use the correct function depending on whether the
> targeted plugin is _bundled_ with the Target Platform or not.
>
{title="Bundled vs. Non-Bundled Plugins"}

### Bundled Plugin

Use `bundledPlugin(id)` or `bundledPlugins(ids)` to add a dependency on the bundled plugin within the currently targeted IntelliJ Platform.
The list of bundled plugin IDs is available via [`printBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledPlugins) task.

| Function              | Description                                    |
|-----------------------|------------------------------------------------|
| `bundledPlugin(id)`   | Adds a dependency on a bundled plugin.         |
| `bundledPlugins(ids)` | Adds a dependency on multiple bundled plugins. |

### Bundled Modules

Use `bundledModule(id)` or `bundledModules(ids)` to add a dependency on an IntelliJ Platform bundled module.
This is useful when a specific platform module is required on the classpath.

| Function              | Description                                    |
|-----------------------|------------------------------------------------|
| `bundledModule(id)`   | Adds a dependency on a bundled module.         |
| `bundledModules(ids)` | Adds a dependency on multiple bundled modules. |

### Non-Bundled Plugin

Use `plugin(notation)` or `plugins(notations)` to add a dependency on a non-bundled plugin (for example, hosted on [JetBrains Marketplace](https://plugins.jetbrains.com)).

Parameter `notation` uses the following format:

- `pluginId:version` or
- `pluginId:version@channel`.

Alternatively, use:
- `plugin(id, version, group)` – where `group` may define a release channel, like `@eap`, or a full Maven coordinates group (defaults to `com.jetbrains.plugins`).
- `plugins(vararg notations)` / `plugins(List<String>)` – for multiple plugins.

The `group` parameter can also describe the release channel by prefixing the value with `@` character, like `@eap` or `@nightly`.
The channel value is used to prepend the JetBrains Marketplace group and build `nightly.com.jetbrains.plugins`.

If defined explicitly, can be used along with any custom plugin repository, like `org.acme.plugins`.

| Function                     | Description                            |
|------------------------------|----------------------------------------|
| `plugin(id, version, group)` | Adds a dependency on a plugin.         |
| `plugin(notation)`           | Adds a dependency on a plugin.         |
| `plugins(notations)`         | Adds a dependency on multiple plugins. |

### Compatible Plugins

Helpers that automatically pick a version compatible with the currently configured IntelliJ Platform by requesting the JetBrains Marketplace API.

These resolve plugin versions against the target platform configured via this extension.
Make sure the required [plugin repositories](tools_intellij_platform_gradle_plugin_repositories_extension.md#plugin-repositories) are defined.

| Function                 | Description                                       |
|--------------------------|---------------------------------------------------|
| `compatiblePlugin(id)`   | Adds a dependency on a compatible plugin.         |
| `compatiblePlugins(ids)` | Adds a dependency on multiple compatible plugins. |

### Multi-Module Setup

When working on a multi-module project, use `pluginComposedModule(dependency)` to add a dependency on a plugin module to be bundled within the main plugin JAR.
This will produce a single plugin Jar file with all submodules' classes composed:

```kotlin
pluginComposedModule(implementation(project(":submodule")))
```

In order to use the plugin model v2, use the `pluginModule(dependency)` instead, which will move the submodule's jar into the `lib/modules/` subdirectory.

```kotlin
pluginModule(implementation(project(":submodule")))
```

### Local Plugin

Use `localPlugin(localPath)` to add a dependency on a local IntelliJ Platform plugin.

## Testing

To implement [tests](testing_plugins.md) for IntelliJ Platform plugin, it is necessary to explicitly add a dependency on the `test-framework` library containing the necessary test base classes.
In most cases, the `TestFrameworkType.Platform` variant will be used:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

dependencies {
  intellijPlatform {
    testFramework(TestFrameworkType.Platform)
  }

  testImplementation("junit:junit:4.13.2")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

dependencies {
  intellijPlatform {
    testFramework TestFrameworkType.Platform.INSTANCE
  }

  testImplementation 'junit:junit:4.13.2'
}
```

</tab>
</tabs>

See [`TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) reference for other test-frameworks,
for example, `Plugin.Java` when testing Java-based functionality.

The provided `testFramework(type, version)` helper method makes it possible to add the base artifact to the test classpath or its variants, such as Java, Go, ReSharper, etc.

| Function                       | Description                                                                                                                                            |
|--------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| `testFramework(type, version)` | Adds a dependency on Test Framework or its variant using [`TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) type. |

> In rare cases, when the presence of a bundled <path>\$PLATFORM_PATH\$/lib/testFramework.jar</path> library is necessary (like in the case of [Rider](rider.md), as its `test-framework` is not published as an artifact),
> it is possible to attach it by using the [`TestFrameworkType.Platform.Bundled`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) type.
> {style="warning"}

There are two known issues related to `Platform` and `JUnit5` Test Frameworks:

- [](tools_intellij_platform_gradle_plugin_faq.md#missing-opentest4j-dependency-in-test-framework)
- [](tools_intellij_platform_gradle_plugin_faq.md#junit5-test-framework-refers-to-junit4)

See also:

- [Types: `TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType)

### Test-scope plugin and module helpers

The extension also provides helpers to add dependencies needed only for tests:

| Function                         | Description                                                                                                     |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `testPlugin(notation)`           | Adds a test dependency on a non-bundled plugin using `pluginId:version` or `pluginId:version@channel` notation. |
| `testPlugins(vararg notations)`  | Adds test dependencies on multiple non-bundled plugins.                                                         |
| `testBundledPlugin(id)`          | Adds a test dependency on a bundled plugin by ID.                                                               |
| `testBundledPlugins(vararg ids)` | Adds test dependencies on multiple bundled plugins by IDs.                                                      |
| `testLocalPlugin(localPath)`     | Adds a test dependency on a local plugin; accepts a path or a project dependency.                               |
| `testBundledModule(id)`          | Adds a test dependency on a specific bundled platform module.                                                   |
| `testBundledModules(vararg ids)` | Adds test dependencies on multiple bundled platform modules.                                                    |

Provider and list-based overloads are available for the above helpers, mirroring their production-scope counterparts.

## Tools

| Function                                       | Description                                                                                                                                                                                                                                      |
|------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `pluginVerifier(version)`                      | Adds a dependency on [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md). Applied by default and refers to the latest available tool's version.                                                                                        |
| `zipSigner(version)`                           | Adds a dependency on [Marketplace ZIP Signer](plugin_signing.md). Applied by default and refers to the latest available tool's version.                                                                                                          |
| `bundledLibrary(path)`                         | **SEE NOTE BELOW**<p>Adds a dependency on a bundled library JAR file of the current IntelliJ Platform, like <path>lib/annotations.jar</path>.</p>                                                                                                |
| `platformDependency(coordinates, version)`     | Adds a dependency on a custom IntelliJ Platform dependency available in the [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories). If version is omitted, the closest compatible version is used.      |
| `testPlatformDependency(coordinates, version)` | Adds a test dependency on a custom IntelliJ Platform dependency available in the [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories). If version is omitted, the closest compatible version is used. |

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

The `jetbrainsRuntime()` helper is applied by default and if JetBrains Runtime is not bundled within the currently used IntelliJ Platform, it refers the relevant runtime version automatically.

| Function                                                  | Description                                                                                                                                                                                                                     |
|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <p>`jetbrainsRuntime()`</p>                               | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in version obtained with the current IntelliJ Platform if resolved from IntelliJ Maven Repository. |
| <p>`jetbrainsRuntime(version, variant, architecture)`</p> | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).                                                                                                   |
| <p>`jetbrainsRuntimeLocal(path)`</p>                      | Adds a dependency on a local [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) instance.                                                                                  |
| <p>`jetbrainsRuntimeExplicit(explicitVersion)`</p>        | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in explicit version.                                                                               |

See [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) for more details.

## Code Instrumentation

The code instrumentation process handled with the [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) task, requires extra dependencies to work and properly adjust the Java bytecode.
There used to be an `instrumentationTools()` convenience helper that applied the required dependencies using defaults; it is now deprecated and calling it is no longer necessary. You can still add and configure the dependencies separately if needed.

Adds a Java Compiler dependency for code instrumentation.
The version is determined by the IntelliJ Platform build number.
If the exact version is unavailable, the closest one is used, found by scanning all releases.

The `javaCompiler()` helper is applied by default and refers to the tool version close to the currently used IntelliJ Platform.

| Function                                              | Description                                                                                 |
|-------------------------------------------------------|---------------------------------------------------------------------------------------------|
| <p>`instrumentationTools()`</p>                       | Deprecated: calling this helper is no longer necessary; previously applied `javaCompiler()`. |
| <p>`javaCompiler()`</p><p>`javaCompiler(version)`</p> | Adds a dependency on Java Compiler.                                                         |

- [](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)

<include from="snippets.topic" element-id="missingContent"/>
