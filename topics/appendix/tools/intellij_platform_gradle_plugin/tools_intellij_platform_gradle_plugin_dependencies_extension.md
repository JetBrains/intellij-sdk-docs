<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

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

| Function                                              | Description                                                      |
|-------------------------------------------------------|------------------------------------------------------------------|
| `androidStudio(version, useInstaller = true)`         | [Android Studio](android_studio.md)                              |
| `clion(version, useInstaller = true)`                 | [CLion](clion.md)                                                |
| `datagrip(version, useInstaller = true)`              | [DataGrip](data_grip.md)                                         |
| `dataspell(version, useInstaller = true)`             | [DataSpell](https://www.jetbrains.com/dataspell/)                |
| `fleetBackend(version, useInstaller = true)`          | [Fleet](https://www.jetbrains.com/fleet/) Backend                |
| `gateway(version, useInstaller = true)`               | [Gateway](https://www.jetbrains.com/remote-development/gateway/) |
| `goland(version, useInstaller = true)`                | [GoLand](goland.md)                                              |
| `intellijIdeaCommunity(version, useInstaller = true)` | [IntelliJ IDEA Community](idea.md)                               |
| `intellijIdeaUltimate(version, useInstaller = true)`  | [IntelliJ IDEA Ultimate](idea_ultimate.md)                       |
| `mps(version, useInstaller = true)`                   | [MPS](https://www.jetbrains.com/mps/)                            |
| `phpstorm(version, useInstaller = true)`              | [PhpStorm](phpstorm.md)                                          |
| `pycharmCommunity(version, useInstaller = true)`      | [PyCharm Community](pycharm.md)                                  |
| `pycharmProfessional(version, useInstaller = true)`   | [PyCharm Professional](pycharm.md)                               |
| `rider(version, useInstaller = true)`                 | [Rider](rider.md)                                                |
| `rubymine(version, useInstaller = true)`              | [RubyMine](rubymine.md)                                          |
| `rustrover(version, useInstaller = true)`             | [RustRover](https://www.jetbrains.com/rust/)                     |
| `webstorm(version, useInstaller = true)`              | [WebStorm](webstorm.md)                                          |

### Custom Target Platforms

| Function                                     | Description                                                                                                                                |
|----------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `create(type, version, useInstaller = true)` | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `create(notation, useInstaller = true)`      | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `local(localPath)`                           | Adds a dependency on a local IntelliJ Platform instance. See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform).       |

See also:

- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)

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

Use `bundledPlugin(id)` or `bundledPlugins(ids)` to add a dependency on bundled plugin.
The list of bundled plugin IDs is available via [`printBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledPlugins) task.

### Non-Bundled Plugin

Use `plugin(notation)` or `plugin(notations)` to add a dependency on a non-bundled plugin (for example, hosted on [JetBrains Marketplace](https://plugins.jetbrains.com)).

Parameter `notation` uses the following format:

- `pluginId:version` or
- `pluginId:version@channel`.

Alternatively, use `plugin(id, version, group)` where the `group` parameter can define a plugin release channel, like `@eap` or a full Maven coordinates group.
It is set by default to the common [JetBrains Marketplace](https://plugins.jetbrains.com) plugin artifacts group `com.jetbrains.plugins`.

The `group` parameter can also describe the release channel by prefixing the value with `@` character, like `@eap` or `@nightly`.
The channel value is used to prepend the JetBrains Marketplace group and build `nightly.com.jetbrains.plugins`.

If defined explicitly, can be used along with any custom plugin repository, like `org.acme.plugins`.

### Multi-Module Setup

When working on a multi-module project, use `pluginModule(dependency)` to add a dependency on a plugin module to be bundled within the main plugin JAR.
Requires passing an existing dependency, like `pluginModule(implementation(project(":submodule")))`.

### Local Plugin

Use `localPlugin(localPath)` to add a dependency on a local IntelliJ Platform plugin. Accepts path or a dependency on another module.

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

## Tools

| Function                                       | Description                                                                                                                                                                       |
|------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `pluginVerifier(version)`                      | Adds a dependency on [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md). Applied by default and refers to the latest available tool's version.                         |
| `zipSigner(version)`                           | Adds a dependency on [Marketplace ZIP Signer](plugin_signing.md). Applied by default and refers to the latest available tool's version.                                           |
| `bundledLibrary(path)`                         | **SEE NOTE BELOW**<p>Adds a dependency on a bundled library JAR file of the current IntelliJ Platform, like <path>lib/annotations.jar</path>.</p>                                 |
| `platformDependency(coordinates, version)`     | Adds a dependency on a custom IntelliJ Platform dependency available in the [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories).      |
| `testPlatformDependency(coordinates, version)` | Adds a test dependency on a custom IntelliJ Platform dependency available in the [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories). |

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
There's the `instrumentationTools()` dependencies helper introduced to apply all required dependencies using default configuration, however, it is possible to add and configure them separately.

Adds a Java Compiler dependency for code instrumentation.
The version is determined by the IntelliJ Platform build number.
If the exact version is unavailable, the closest one is used, found by scanning all releases.

The `javaCompiler()` helper is applied by default and refers to the tool version close to the currently used IntelliJ Platform.

| Function                                              | Description                                                            |
|-------------------------------------------------------|------------------------------------------------------------------------|
| <p>`instrumentationTools()`</p>                       | A helper function to apply all required dependencies: `javaCompiler()` |
| <p>`javaCompiler()`</p><p>`javaCompiler(version)`</p> | Adds a dependency on Java Compiler.                                    |

- [](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)

<include from="snippets.topic" element-id="missingContent"/>
