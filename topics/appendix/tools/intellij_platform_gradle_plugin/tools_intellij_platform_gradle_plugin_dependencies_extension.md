<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

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
- target IntelliJ IDEA %ijPlatform%
- add dependency on the bundled Java plugin
- add a JUnit4 test dependency
- add Test Framework for testing a plugin with JUnit4

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
    intellijIdea("%ijPlatform%")

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
    intellijIdea '%ijPlatform%'

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

> Declare only one target IntelliJ Platform dependency for the main development configuration.
>
{style="warning"}

All product helpers accept `version` as either a `String` or a `Provider<String>` and an optional dependency configuration callback.

### Default Target Platforms

<secondary-label ref="gradle-plugin-intellij-idea-2.9.0"/>
<secondary-label ref="gradle-plugin-pycharm-2.10.0"/>

See [](#custom-target-platforms) for non-default targets.

| Function                                 | Description                                                      |
|------------------------------------------|------------------------------------------------------------------|
| `androidStudio(version, configure = {})` | [Android Studio](android_studio.md)                              |
| `clion(version, configure = {})`         | [CLion](clion.md)                                                |
| `datagrip(version, configure = {})`      | [DataGrip](data_grip.md)                                         |
| `dataspell(version, configure = {})`     | [DataSpell](https://www.jetbrains.com/dataspell/)                |
| `fleetBackend(version, configure = {})`  | [Fleet](https://www.jetbrains.com/fleet/) Backend                |
| `gateway(version, configure = {})`       | [Gateway](https://www.jetbrains.com/remote-development/gateway/) |
| `goland(version, configure = {})`        | [GoLand](goland.md)                                              |
| `intellijIdea(version, configure = {})`  | [IntelliJ IDEA](idea.md)                                         |
| `mps(version, configure = {})`           | [MPS](https://www.jetbrains.com/mps/)                            |
| `phpstorm(version, configure = {})`      | [PhpStorm](phpstorm.md)                                          |
| `pycharm(version, configure = {})`       | [PyCharm](pycharm.md)                                            |
| `rider(version, configure = {})`         | [Rider](rider.md)                                                |
| `rubymine(version, configure = {})`      | [RubyMine](rubymine.md)                                          |
| `rustRover(version, configure = {})`     | [RustRover](https://www.jetbrains.com/rust/)                     |
| `webstorm(version, configure = {})`      | [WebStorm](webstorm.md)                                          |

Notes:
- Writerside (`WRS`) is deprecated and no longer available as a target IntelliJ Platform.
- Aqua (`QA`) is deprecated and no longer available as a target IntelliJ Platform.
- IntelliJ IDEA Community (`IC`) and Ultimate (`IU`) legacy helpers remain available for versions earlier than 2025.3.
- PyCharm Community (`PC`) and Professional (`PY`) legacy helpers remain available for versions earlier than 2025.3.

### Legacy Target Platform Helpers

The following helpers are still available for resolving pre-2025.3 product lines:

| Function                                         | Description                                                      |
|--------------------------------------------------|------------------------------------------------------------------|
| `intellijIdeaCommunity(version, configure = {})` | IntelliJ IDEA Community (`IC`) for versions earlier than 2025.3. |
| `intellijIdeaUltimate(version, configure = {})`  | IntelliJ IDEA Ultimate (`IU`) for versions earlier than 2025.3.  |
| `pycharmCommunity(version, configure = {})`      | PyCharm Community (`PC`) for versions earlier than 2025.3.       |
| `pycharmProfessional(version, configure = {})`   | PyCharm Professional (`PY`) for versions earlier than 2025.3.    |

### Custom Target Platforms

| Function                                | Description                                                                                                                                |
|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `create(type, version, configure = {})` | Adds a configurable dependency on the IntelliJ Platform. See [](tools_intellij_platform_gradle_plugin.md#dependenciesParametrizePlatform). |
| `create(configure)`                      | Adds a dependency configured entirely through an `Action<IntelliJPlatformDependencyConfiguration>`.                                          |
| `create(configuration)`                  | Adds a dependency from an existing `IntelliJPlatformDependencyConfiguration`.                                                                |
| `local(localPath)`                      | Adds a dependency on a local IntelliJ Platform instance. See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform).       |
| `local(localPath, configurationName)`    | Adds a local IntelliJ Platform dependency to the named configuration. This overload requires a `Provider<*>` for `localPath`.                 |

For `create(type, version)`, `type` accepts an [`IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType) or a product code such as `"IU"`.
The supported argument combinations are a direct type and version, a direct type and a version provider, or providers for both arguments.
When using `create { ... }`, set both `type` and `version` in the configuration callback.
JetBrains Client has no dedicated product helper; use `create(IntelliJPlatformType.JetBrainsClient, version)` or request `ProductMode.FRONTEND`.

`local(localPath)` accepts a `String`, `File`, `Path`, `Directory`, or a provider of one of these types.
The path must point to an existing IDE installation directory, not an archive.
On macOS, a path ending in <path>.app</path> is resolved to its <path>Contents</path> directory.
By default, the dependency is added to `intellijPlatformLocal`.
The custom `configurationName` overload changes the dependency destination, not the main platform request configuration.

### Dependency Configuration Parameters

The optional `configure` callback on the target-platform helpers configures an
[`IntelliJPlatformDependencyConfiguration`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformDependencyConfiguration):

| Parameter      | Type                            | Description                                                                                                                                                                                |
|----------------|---------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `type`         | `Property<IntelliJPlatformType>` | The target product. Set by the product helper or the `type` argument of `create()`.                                                                                                         |
| `version`      | `Property<String>`              | The IntelliJ Platform version. Set by the helper's `version` argument.                                                                                                                     |
| `productMode`  | `Property<ProductMode>`         | The requested product mode. Default: `ProductMode.MONOLITH`.                                                                                                                               |
| `useInstaller` | `Property<Boolean>`             | Selects an IDE installer (`true`) or a Maven archive (`false`). Uses the product's default when not set; most products use installers, while Rider uses archives.                           |
| `useCache`     | `Property<Boolean>`             | Uses the managed IDE cache instead of the Gradle cache. Inherits [`intellijPlatform.caching.ides.enabled`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-ides-enabled). |

All properties support assignment of direct values and `Provider` instances.
The managed cache location is configured with [`intellijPlatform.caching.ides.path`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-ides-path).

When `productMode = ProductMode.FRONTEND`, the dependency resolves the matching JetBrains Client installer, regardless of `useInstaller`.
When `splitMode` is enabled or a non-`MONOLITH` `productMode` is requested, the IDE is extracted into the managed IDE cache even if `useCache` is disabled.

See also:

- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)
- [Types: `ProductMode`](tools_intellij_platform_gradle_plugin_types.md#ProductMode)

## Target Versions

{#target-versions}

The IntelliJ Platform Gradle Plugin allows for using two types of IntelliJ Platform artifacts for development: installers and multi-OS ZIP archives.
Installers are the default for most products, while Rider defaults to multi-OS archives.
The plugin currently warns against Rider installers (`useInstaller = true`); retain the archive default.
Not every product offers both formats; see the [`IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType) reference.
Fleet Backend has Maven archive coordinates only and cannot be resolved as an installer.
Use `useInstaller = false` for Fleet Backend.

### Latest Installer

Use `"latest"` as the platform version, for example, `intellijIdea("latest")`, to select the newest listed installer for the requested product type.
Selection compares build numbers across all release channels, so the result may be an EAP release rather than a stable release.
This selector requires `useInstaller = true`; it cannot be used with multi-OS archives.
When the managed IDE cache is used, it receives the resolved concrete version instead of `"latest"`.

### Installers

{#target-versions-installers}

When declaring a dependency on IntelliJ Platform with `useInstaller = true`, the IDE installer is resolved.
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
    intellijIdea("%ijPlatform%")
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
    intellijIdea '%ijPlatform%'
  }
}
```

</tab>
</tabs>


Available installers can be listed by executing the [`printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases) task.
Installer download URLs are resolved from product release metadata.

IntelliJ Platform installers are OS- and architecture-specific and contain bundled [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md).
Published EAP installers can also be used.

To apply required repositories, use [](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) or explicit [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-platform-installers) helpers.

### Multi-OS Archives

{#target-versions-multi-os-archives}

It is still possible to use Multi-OS ZIP archives resolved from [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories).

To enable resolving this kind of artifacts, opt out from installer dependencies by setting `useInstaller = false` in the helper configuration:

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
    intellijIdea("%ijPlatform%") {
      useInstaller = false
    }
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
    intellijIdea("%ijPlatform%") {
      useInstaller = false
    }
  }
}
```

</tab>
</tabs>


The Multi-OS Archives don't bundle [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) needed to run the IDE locally, perform testing, and other crucial operations.
The automatic [runtime dependency](#java-runtime) uses the runtime version declared by the IDE when available.
The JetBrains Runtime repository must also be configured, either through `defaultRepositories()` or the `jetbrainsRuntime()` repository helper.
Use an explicit runtime dependency if the IDE does not declare a runtime version.

It is advised to rely on installer releases, but targeting EAP multi-OS archives helps when making sure the plugin will work in the upcoming IDE releases.

To apply required repositories, use [](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) or explicit [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories) helpers.

### Latest Version

{#target-versions-latest}

Set the version of an installer-based IntelliJ Platform dependency to `"latest"` (or the `Constraints.LATEST_VERSION` constant) to resolve the newest available release for the requested platform type.
The version is resolved from the product releases listing across all channels for the requested type, picking the newest matching release by build number:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.Constants.Constraints

dependencies {
  intellijPlatform {
    intellijIdea("latest")
    // or:
    intellijIdea(Constraints.LATEST_VERSION)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    intellijIdea 'latest'
  }
}
```

</tab>
</tabs>

The `"latest"` version works only with the installer distribution (the default `useInstaller = true`); combining it with `useInstaller = false` fails the build.

The same version value can be used for [custom testing configurations](tools_intellij_platform_gradle_plugin_testing_extension.md) registered with `intellijPlatformTesting`:

```kotlin
val runLatestIde = intellijPlatformTesting.runIde.register("runLatestIde") {
  type = IntelliJPlatformType.IntellijIdea
  version = "latest"
}
```

> Because the resolved version follows the latest release, the exact IntelliJ Platform used may change over time, which affects build reproducibility.
>
{style="warning"}

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

The singular helper accepts a `String` or `Provider<String>`.
The plural helper accepts varargs, a `List<String>`, or a `Provider<List<String>>`.
Required bundled plugin and module dependencies are resolved transitively.

### Bundled Modules

Use `bundledModule(id)` or `bundledModules(ids)` to add a dependency on an IntelliJ Platform bundled module.
This is useful when a specific platform module is required on the classpath.
The `id` value can be either a bundled module ID or a module alias exported by the target IDE, for example `com.intellij.modules.vcs`.

| Function              | Description                                                 |
|-----------------------|-------------------------------------------------------------|
| `bundledModule(id)`   | Adds a dependency on a bundled module or module alias.      |
| `bundledModules(ids)` | Adds dependencies on multiple bundled modules or aliases.   |

The singular helper accepts a `String` or `Provider<String>`.
The plural helper accepts varargs, a `List<String>`, or a `Provider<List<String>>`.
Use a module helper rather than `bundledPlugin()` when the requested entry is a classpath-backed module without a plugin archive.

### Non-Bundled Plugin

Use `plugin(notation)` or `plugins(notations)` to add a dependency on a non-bundled plugin (for example, hosted on [JetBrains Marketplace](https://plugins.jetbrains.com)).

Parameter `notation` uses the following format:

- `pluginId:version` or
- `pluginId:version@channel`.

Alternatively, use:
- `plugin(id, version, group)` – where `group` may define a release channel, like `@eap`, or a full Maven coordinates group (defaults to `com.jetbrains.plugins`). These arguments can be direct strings or providers of strings.
- `plugins(vararg notations)`, `plugins(List<String>)`, or `plugins(Provider<List<String>>)` – for multiple plugins.

For the separate-argument form, `id` and `version` must both be direct strings or both be providers.
If `group` is supplied, use the same form for that argument as well.

The `group` parameter can also describe the release channel by prefixing the value with `@` character, like `@eap` or `@nightly`.
The channel value is used to prepend the JetBrains Marketplace group and build `nightly.com.jetbrains.plugins`.

If defined explicitly, can be used along with any custom plugin repository, like `org.acme.plugins`.

| Function                                        | Description                                                                 |
|-------------------------------------------------|-----------------------------------------------------------------------------|
| `plugin(id, version, group, configure = {})`      | Adds a dependency on a plugin. `group` defaults to `com.jetbrains.plugins`. |
| `plugin(notation, configure = {})`               | Adds a dependency using a `String` or `Provider<String>` notation.           |
| `plugins(notations)`                             | Adds dependencies on multiple plugins without a configuration callback.     |

#### Plugin Library Exclusions
{#plugin-library-exclusions}

<primary-label ref="gradle-plugin-2.19.0"/>

<p id="excluding-plugin-bundled-libraries">
A non-bundled plugin may ship libraries in its <path>lib/</path> and <path>lib/modules/</path> directories that conflict with libraries provided by the target IntelliJ Platform or the developed plugin.
</p>

The optional `configure` callback on `plugin()` and `testPlugin()` accepts an `Action<IntelliJPlatformPluginConfiguration>`.
Use it to exclude packaged libraries with Gradle's Ant-style patterns, relative to the plugin's <path>lib/</path> and <path>lib/modules/</path> directories, such as `jena-*.jar`:

| Function                                  | Description                                               |
|-------------------------------------------|-----------------------------------------------------------|
| `excludeBundledLibrary(pattern)`           | Excludes matching library files from the declared plugin. |
| `excludeBundledLibraries(vararg patterns)` | Adds multiple library exclusion patterns.                |

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    plugin("org.example.plugin:1.0") {
      excludeBundledLibrary("jena-*.jar")
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    plugin('org.example.plugin:1.0') {
      excludeBundledLibrary('jena-*.jar')
    }
  }
}
```

</tab>
</tabs>

Exclusions apply to the plugin's classpath and its sandbox copy, including matching libraries under <path>lib/modules/</path>.
Rules are associated with the plugin ID and do not exclude libraries from other plugins.
Patterns declared for the same plugin ID are combined build-wide.
The filtered plugin must retain a valid plugin descriptor; excluding the JAR containing it causes a failure.

In Groovy, the separate-argument form with a callback requires an explicit `group` argument, for example,
`plugin('org.example.plugin', '1.0', 'com.jetbrains.plugins') { ... }`.
The notation form shown above does not require that extra argument.

The same configuration block is available for the test-scope [`testPlugin()`](#test-scope-plugin-and-module-helpers) helpers.

### Compatible Plugins

Helpers that automatically pick a version compatible with the currently configured IntelliJ Platform by requesting the JetBrains Marketplace API.
These helpers are incubating.

These resolve plugin versions against the target platform configured via this extension.
Make sure the required [plugin repositories](tools_intellij_platform_gradle_plugin_repositories_extension.md#plugin-repositories) are defined.

| Function                 | Description                                       |
|--------------------------|---------------------------------------------------|
| `compatiblePlugin(id)`   | Adds a dependency on a compatible plugin.         |
| `compatiblePlugins(ids)` | Adds a dependency on multiple compatible plugins. |

The singular helper accepts a `String` or `Provider<String>`.
The plural helper accepts varargs, a `List<String>`, or a `Provider<List<String>>`.
Resolution fails if JetBrains Marketplace has no compatible update for a requested plugin ID.

### Multi-Module Setup

When working on a multi-module project, use `pluginComposedModule(dependency)` to add a dependency on a plugin module to be bundled within the main plugin JAR.
This will produce a single plugin Jar file with all submodules' classes composed:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
pluginComposedModule(implementation(project(":submodule")))
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
pluginComposedModule(implementation(project(':submodule')))
```

</tab>
</tabs>

In order to use the plugin model v2, use the `pluginModule(dependency)` instead, which will move the submodule's jar into the `lib/modules/` subdirectory.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
pluginModule(implementation(project(":submodule")))
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
pluginModule(implementation(project(':submodule')))
```

</tab>
</tabs>

Both helpers accept a Gradle `Dependency`; a `null` dependency is ignored.

### Local Plugin

Use `localPlugin(localPath)` to add a dependency on a local IntelliJ Platform plugin.
It accepts a `String`, `File`, or `Directory` pointing to a plugin directory or archive, or a provider supplying a `String`, `File`, `Path`, or `Directory`.
The `localPlugin(dependency)` overload accepts a Gradle `ProjectDependency`.

## Testing

Use `testFramework()` or `testFrameworks()` to explicitly add [test framework](testing_plugins.md) libraries and their test base classes.
For general IntelliJ Platform tests, use `TestFrameworkType.Platform`:

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

The provided helpers make it possible to add the base artifact to the test classpath or its variants, such as Java, Go, ReSharper, etc.

| Function                                          | Description                                                                                                                                                                      |
|---------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `testFramework(type, version, configurationName)` | Adds the requested [`TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType) to a configuration. `version` and `configurationName` are optional. |
| `testFrameworks(vararg types)`                    | Adds all requested Test Framework variants using build-compatible versions.                                                                                                    |
| `testFrameworks(types: List<TestFrameworkType>)` | Adds all Test Framework variants in the list using build-compatible versions.                                                                                                  |

`testFramework()` accepts `version` as a `String` or `Provider<String>`.
The default version selector is `Constants.Constraints.CLOSEST_VERSION`; see [Dependency Version Selection](#dependency-version-selection).
The default `configurationName` is `intellijPlatformTestDependencies`.
In Groovy, the provider-based version overload requires all three arguments, including the configuration name.
Published framework dependencies exclude transitive modules already supplied by the target IDE.

#### `testFrameworks()`

<primary-label ref="gradle-plugin-2.19.0"/>

Use either `testFrameworks()` overload to add multiple Test Framework variants at once:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

dependencies {
  intellijPlatform {
    testFrameworks(
      TestFrameworkType.Platform,
      TestFrameworkType.Plugin.Java,
    )
    testFrameworks(
      listOf(
        TestFrameworkType.JUnit5,
        TestFrameworkType.Plugin.Maven,
      )
    )
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

dependencies {
  intellijPlatform {
    testFrameworks(
      TestFrameworkType.Platform.INSTANCE,
      TestFrameworkType.Plugin.Java.INSTANCE
    )
    testFrameworks([
      TestFrameworkType.JUnit5.INSTANCE,
      TestFrameworkType.Plugin.Maven.INSTANCE
    ])
  }
}
```

</tab>
</tabs>

The plural overloads do not accept an explicit version.
To request a specific version or configuration, call `testFramework()` separately.
For `TestFrameworkType.Bundled`, the JAR is taken directly from the IDE and `version` is not used.

> In rare cases when the bundled <path>\$PLATFORM_PATH\$/lib/testFramework.jar</path> library is required,
> attach it with [`TestFrameworkType.Bundled`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType).
>
{style="warning"}

There are two known issues related to `Platform` and `JUnit5` Test Frameworks:

- [](tools_intellij_platform_gradle_plugin_faq.md#missing-opentest4j-dependency-in-test-framework)
- [](tools_intellij_platform_gradle_plugin_faq.md#junit5-test-framework-refers-to-junit4)

See also:

- [Types: `TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType)

### Test-scope plugin and module helpers

The extension also provides helpers to add dependencies needed only for tests:

| Function                         | Description                                                                                                     |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `testPlugin(notation, configure = {})` | Adds a test dependency on a non-bundled plugin using `pluginId:version` or `pluginId:version@channel` notation. |
| `testPlugin(id, version, group, configure = {})` | Adds a test dependency on a plugin. `group` defaults to `com.jetbrains.plugins`.                  |
| `testPlugins(vararg notations)`  | Adds test dependencies on multiple non-bundled plugins.                                                         |
| `testBundledPlugin(id)`          | Adds a test dependency on a bundled plugin by ID.                                                               |
| `testBundledPlugins(vararg ids)` | Adds test dependencies on multiple bundled plugins by IDs.                                                      |
| `testLocalPlugin(localPath)`     | Adds a test dependency on a local plugin; accepts a path or a project dependency.                               |
| `testBundledModule(id)`          | Adds a test dependency on a specific bundled platform module or module alias.                                  |
| `testBundledModules(vararg ids)` | Adds test dependencies on multiple bundled platform modules or aliases.                                        |

`testPlugin()` supports the same direct-string, provider, and [library exclusion](#plugin-library-exclusions) overloads as `plugin()`.
`testPlugins()`, `testBundledPlugins()`, and `testBundledModules()` each accept varargs, a list, or a provider of a list.
The singular bundled helpers accept a string or provider of a string.
`testLocalPlugin()` accepts the same path types and project dependency overload as `localPlugin()`; it does not have a list overload.
The `testPlugin(...)` helpers also accept the same configuration block as [`plugin(...)`](#excluding-plugin-bundled-libraries) for excluding libraries bundled with the plugin.

## Sandbox Runtime Classpaths
{#sandbox-runtime-classpaths}

The plugin uses dedicated resolvable configurations for dependencies copied to the developed plugin's <path>lib/</path> directory in a sandbox:

| Configuration                                    | Description                                                                                                                                                                                   |
|--------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `intellijPlatformSandboxRuntimeClasspath`        | Extends the project's `runtimeClasspath` and supplies runtime dependencies for regular sandboxes, such as the one created by [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox). |
| `intellijPlatformTestSandboxRuntimeClasspath`    | Extends `intellijPlatformSandboxRuntimeClasspath` and `intellijPlatformTestRuntimeClasspath`, and supplies runtime dependencies for test sandboxes created by [`prepareTestSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestSandbox). |

Excluding a dependency from either configuration changes only the contents of the corresponding sandbox.
It does not remove the dependency from the project's compile, runtime, or test classpaths.
Because the test sandbox configuration extends the regular sandbox configuration, exclusions added to `intellijPlatformSandboxRuntimeClasspath` apply to both regular and test sandboxes.
Configure `intellijPlatformTestSandboxRuntimeClasspath` instead when an exclusion should apply only to test sandboxes.

For example, the following keeps Joda-Time available to compile the plugin but prevents its JAR from being copied into either sandbox:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  implementation("joda-time:joda-time:2.8.1")
}

configurations.named("intellijPlatformSandboxRuntimeClasspath") {
  exclude(group = "joda-time", module = "joda-time")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  implementation 'joda-time:joda-time:2.8.1'
}

configurations.named('intellijPlatformSandboxRuntimeClasspath') {
  exclude group: 'joda-time', module: 'joda-time'
}
```

</tab>
</tabs>

By default, both sandbox configurations exclude `org.jetbrains.kotlin:kotlin-stdlib`, `org.jetbrains.kotlin:kotlin-stdlib-jdk8`, and the known Kotlin Coroutines modules supplied by the IntelliJ Platform.
The Coroutines exclusions cover the `org.jetbrains.kotlinx`, `com.intellij.platform`, and `org.jetbrains.intellij.deps.kotlinx` groups.
Set [`org.jetbrains.intellij.platform.useDefaultSandboxExclusions=false`](tools_intellij_platform_gradle_plugin_gradle_properties.md#useDefaultSandboxExclusions) to opt out.

## Native Files

OS- and architecture-specific native executables and libraries are configured through
[`intellijPlatform.nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants), not a separate dependency helper.
Each target exposes a `ConfigurableFileCollection` for its files.
See [`Variant`](tools_intellij_platform_gradle_plugin_types.md#Variant) for the supported OS and architecture identifiers.

## Tools

| Function                                       | Description                                                                                                                                                                                                                                      |
|------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `pluginVerifier()` / `pluginVerifier(version)` | Adds a dependency on [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md). Applied by default with the `Constants.Constraints.LATEST_VERSION` selector. |
| `zipSigner()` / `zipSigner(version)`           | Adds a dependency on [Marketplace ZIP Signer](plugin_signing.md). Applied by default with the `Constants.Constraints.LATEST_VERSION` selector.                 |
| `bundledLibrary(path)`                         | **SEE NOTE BELOW**<p>Adds a dependency on a bundled library JAR file of the current IntelliJ Platform, like <path>lib/annotations.jar</path>.</p>                                                                                                |
| `platformDependency(coordinates, version, configurationName)` | Adds a custom IntelliJ Platform dependency from the [](tools_intellij_platform_gradle_plugin_repositories_extension.md#intellij-maven-repositories). `version` and `configurationName` are optional. |
| `testPlatformDependency(coordinates, version, configurationName)` | Adds a test dependency on a custom IntelliJ Platform artifact. `version` and `configurationName` are optional. |

Tool versions accept a `String` or `Provider<String>`.
The `bundledLibrary()` path accepts a `String` or `Provider<String>` and is relative to the IDE installation directory.

For `platformDependency()` and `testPlatformDependency()`, `coordinates` is a
[`Coordinates`](tools_intellij_platform_gradle_plugin_types.md#Coordinates) value, not a Gradle dependency notation string.
The default version is the closest available version in the target IDE's build branch that is not newer than the IDE build.
Nightly platform dependencies use the requested platform version instead; see [Dependency Version Selection](#dependency-version-selection).
Transitive dependencies already supplied by the target IDE are excluded.

| Helper                     | Default `configurationName`          |
|----------------------------|--------------------------------------|
| `platformDependency()`     | `intellijPlatformDependencies`     |
| `testPlatformDependency()` | `intellijPlatformTestDependencies` |

`configurationName` selects the dependency destination; version selection and transitive exclusions still use the main target IntelliJ Platform.
The `version` argument accepts a `String` or `Provider<String>`.
In Groovy, the provider-based overload requires all three arguments, including `configurationName`.

> Do not use **`bundledLibrary()`** in production, as direct access to the IntelliJ Platform libraries is not recommended.
>
> It should only be used as a workaround in case the IntelliJ Platform Gradle Plugin is not aligned with the latest IntelliJ Platform classpath changes.
>
{style="warning"}

See also:

- [](verifying_plugin_compatibility.md), [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [](plugin_signing.md), [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)

### Dependency Version Selection
{#dependency-version-selection}

The tool and platform artifact helpers use Gradle version constraints:

| Selector                               | Behavior                                                                                             | Default for                                                                                   |
|----------------------------------------|------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `Constants.Constraints.CLOSEST_VERSION` | Restricts versions to the target IDE's build branch, no newer than the IDE build, and prefers that build. | `javaCompiler()`, `testFramework()`, `platformDependency()`, `testPlatformDependency()` |
| `Constants.Constraints.PLATFORM_VERSION` | Prefers the requested platform version.                                                            | Used in place of `CLOSEST_VERSION` for nightly platform dependencies.                          |
| `Constants.Constraints.LATEST_VERSION`  | Applies `prefer("+")`.                                                                               | `pluginVerifier()`, `zipSigner()`                                                              |

An explicit tool or platform artifact version is set with Gradle's `prefer()` constraint, not a strict version pin.
The target-platform [`"latest"` selector](#latest-installer) is different: it selects a concrete installer from product release metadata.

## Java Runtime

Using the `jetbrainsRuntime()` or `jetbrainsRuntimeExplicit()` dependency helpers, it is possible to load a custom version of JetBrains Runtime.
However, it is recommended to rely on the runtime bundled within the IntelliJ Platform dependency, if present.

The `jetbrainsRuntime()` helper is applied by default when a separate runtime is needed.
It reads `runtimeBuild`, falling back to `jdkBuild`, from the IDE's <path>dependencies.txt</path> file.
If no runtime version can be obtained, this helper adds no dependency; use an explicit version or a local runtime instead.

| Function                                                  | Description                                                                                                                                                                                                                     |
|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <p>`jetbrainsRuntime()`</p>                               | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in version obtained with the current IntelliJ Platform if resolved from IntelliJ Maven Repository. |
| <p>`jetbrainsRuntime(version, variant, architecture)`</p> | Builds an OS-specific runtime version from a version such as `21.0.3b446.1`, a variant, and an architecture.                                                                                                                   |
| <p>`jetbrainsRuntimeLocal(localPath)`</p>                 | Adds a dependency on a local [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) instance.                                                                                  |
| <p>`jetbrainsRuntimeExplicit(explicitVersion)`</p>        | Adds a dependency on [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) in explicit version.                                                                               |

For `jetbrainsRuntime(version, variant, architecture)`, the default variant is `jcef` and the default architecture is detected from the current host.
The `version` must use the `[sdk]b[build]` format, such as `21.0.3b446.1`.
Generated runtime versions use OS identifiers `windows`, `osx`, or `linux`, and normalized architecture identifiers such as `x64` or `aarch64`.
Kotlin allows the last two arguments to be omitted; Groovy requires all three arguments, with `null` selecting a default.
A provider-based overload accepts providers for all three arguments.

`jetbrainsRuntimeExplicit()` accepts a `String` or `Provider<String>` containing the full artifact version, such as `jbr_jcef-21.0.3-linux-x64-b446.1`.
`jetbrainsRuntimeLocal()` accepts a `String`, `Path`, or `Directory`, or a provider supplying a `String`, `File`, `Path`, or `Directory`.

See [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) for more details.

## Code Instrumentation

The code instrumentation process handled with the [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) task requires extra dependencies to work and properly adjust the Java bytecode.
The removed `instrumentationTools()` convenience helper is no longer necessary.
You can still add and configure the dependencies separately if needed.

The `javaCompiler()` helper adds the Java Compiler dependency used for code instrumentation.
By default, it uses the [closest version selector](#dependency-version-selection).

The `javaCompiler()` helper is applied by default and refers to the tool version close to the currently used IntelliJ Platform.

| Function                                              | Description                         |
|-------------------------------------------------------|-------------------------------------|
| <p>`javaCompiler()`</p><p>`javaCompiler(version)`</p> | Adds a dependency on Java Compiler. |

An explicit `version` accepts a `String` or `Provider<String>`.

- [](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)

## Grammar and Parser Generation

Helpers for the [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit) plugin tasks.

| Function                                 | Description                                                                                                              |
|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| `grammarKit()`<br/>`grammarKit(version)` | Adds a Grammar Kit dependency used by [`generateParser`](tools_intellij_platform_gradle_plugin_tasks.md#generateParser). |
| `jflex()`<br/>`jflex(version)`           | Adds a JFlex dependency used by [`generateLexer`](tools_intellij_platform_gradle_plugin_tasks.md#generateLexer).         |

Both helpers accept an explicit version as a `String` or `Provider<String>`.
The no-argument forms use `Constants.Constraints.GRAMMAR_KIT_VERSION` and `Constants.Constraints.JFLEX_VERSION`, respectively, and are available in Kotlin.
In Groovy, pass a version explicitly:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    grammarKit()
    jflex()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.Constants.Constraints

dependencies {
  intellijPlatform {
    grammarKit(Constraints.GRAMMAR_KIT_VERSION)
    jflex(Constraints.JFLEX_VERSION)
  }
}
```

</tab>
</tabs>

## Compose UI

| Function      | Description                                                                             |
|---------------|-----------------------------------------------------------------------------------------|
| `composeUI()` | Adds the bundled modules required for working with Compose UI in the IntelliJ Platform. |

`composeUI()` is incubating and resolves bundled modules according to the target IDE build.
The rows below are cumulative:

| Target build | Added modules                                                                                                                                                             |
|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 2024.3+      | `intellij.libraries.skiko`, `intellij.platform.compose`                                                                                                                   |
| 2025.1+      | `intellij.libraries.compose.foundation.desktop`, `intellij.platform.jewel.foundation`, `intellij.platform.jewel.ui`, `intellij.platform.jewel.ideLafBridge`               |
| 2025.3+      | `intellij.libraries.compose.runtime.desktop`                                                                                                                              |

## Dependency Exclusions

| Function                | Description                                                              |
|-------------------------|--------------------------------------------------------------------------|
| `excludeKotlinStdlib()` | Excludes transitive Kotlin Standard Library artifacts from a dependency. |
| `excludeCoroutines()`   | Excludes transitive Kotlin Coroutines artifacts from a dependency.       |

These are top-level Kotlin extension functions on Gradle's `ModuleDependency`, not methods of `dependencies.intellijPlatform`.
In Kotlin, import and call them inside a dependency configuration callback.
In Groovy, import their generated static methods and pass the dependency explicitly:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.extensions.excludeCoroutines
import org.jetbrains.intellij.platform.gradle.extensions.excludeKotlinStdlib

dependencies {
  implementation("io.kotest:kotest-assertions-core:5.9.1") {
    excludeKotlinStdlib()
    excludeCoroutines()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import static org.jetbrains.intellij.platform.gradle.extensions.IntelliJPlatformDependenciesExtensionKt.excludeCoroutines
import static org.jetbrains.intellij.platform.gradle.extensions.IntelliJPlatformDependenciesExtensionKt.excludeKotlinStdlib

dependencies {
  implementation('io.kotest:kotest-assertions-core:5.9.1') { dependency ->
    excludeKotlinStdlib(dependency)
    excludeCoroutines(dependency)
  }
}
```

</tab>
</tabs>

`excludeKotlinStdlib()` covers `org.jetbrains.kotlin:kotlin-stdlib` and `org.jetbrains.kotlin:kotlin-stdlib-jdk8`.
`excludeCoroutines()` covers `kotlinx-coroutines-core`, `kotlinx-coroutines-core-jvm`, `kotlinx-coroutines-jdk8`, `kotlinx-coroutines-debug`,
`kotlinx-coroutines-guava`, `kotlinx-coroutines-slf4j`, and `kotlinx-coroutines-test` in the `org.jetbrains.kotlinx`, `com.intellij.platform`, and `org.jetbrains.intellij.deps.kotlinx` groups.
These helpers exclude transitive Maven dependencies; use [plugin library exclusions](#plugin-library-exclusions) for JARs packaged inside another plugin.

<include from="snippets.topic" element-id="missingContent"/>
