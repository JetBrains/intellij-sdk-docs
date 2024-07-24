<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Repositories Extension

<link-summary>IntelliJ Platform Gradle Plugin repositories extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

This is an extension class for managing IntelliJ Platform repositories in a Gradle build script.
It's applied to the `RepositoryHandler`.

Available in both `Project` scope and Gradle Settings for `DependencyResolutionManagement`.

It provides methods to add:

- IntelliJ Platform repositories (for releases, snapshots, and nightly builds)
- JetBrains Marketplace repository (for dependencies on non-bundled plugins)
- JetBrains Runtime repository
- Android Studio and IntelliJ Platform binary release repositories (for IntelliJ Plugin Verifier)
- Ivy local repository (for access to local dependencies)

<snippet id="recommendedCallout">

> In most cases, [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) repository should be sufficient.
>
{style="tip"}

**Example:**

Setup Maven Central and [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) repositories:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}
```

</snippet>

## Default Repositories

The default repository definition suitable for most plugins.

| Function                | Description                                                                                                |
|-------------------------|------------------------------------------------------------------------------------------------------------|
| `defaultRepositories()` | Applies a set of recommended repositories required for building plugins and running the most common tasks. |

It includes:
- `releases()` and `snapshots()` — IntelliJ Platform releases channels
- `marketplace()` — JetBrains Marketplace plugins repository
- `localPlatformArtifacts()` — required to use plugins bundled with IntelliJ Platform or refer to a local IDE
- `intellijDependencies()` — required for resolving extra IntelliJ Platform dependencies used for running specific tasks
- `binaryReleases()` — JetBrains IDEs releases required for running the IntelliJ Plugin Verifier


## IDE Releases

The following IntelliJ Platform repositories contain not only the IntelliJ Platform releases in stable, snapshot, and nightly versions, but also various dependencies, such as:
- Java Compiler required for [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#code-instrumentation)
- Test Framework required for [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing)

| Function      | Description                                                                                 |
|---------------|---------------------------------------------------------------------------------------------|
| `releases()`  | Adds a repository for accessing IntelliJ Platform stable releases.                          |
| `snapshots()` | Adds a repository for accessing IntelliJ Platform snapshot releases.                        |
| `nightly()`   | Adds a repository for accessing IntelliJ Platform nightly releases, not available publicly. |

See also:
- [](intellij_artifacts.md)


## Binary IDE Releases

| Function                        | Description                                                                                                  |
|---------------------------------|--------------------------------------------------------------------------------------------------------------|
| `binaryReleases()`              | Adds a repository for accessing IntelliJ Platform IDE binary releases for use with IntelliJ Plugin Verifier. |
| `binaryReleasesAndroidStudio()` | Adds a repository for accessing Android Studio binary releases for use with IntelliJ Plugin Verifier.        |

See also:

- [](verifying_plugin_compatibility.md)


## Plugin Repositories

It is possible to define a dependency on another plugin using [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helpers.
Such plugins are resolved as any other dependencies in Gradle using repositories added to the project configuration.

The common repository hosting plugins is [JetBrains Marketplace](https://plugins.jetbrains.com), which can be added with the `marketplace()` helper.
To refer to its plugins, use the [`plugin(id, version)`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) dependency helper.

It is also possible to resolve plugins from regular Maven repositories other than JetBrains Marketplace.
In such a case, refer to them using [`plugin(id, version, group)`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins), which will build `group:id:version` plugin coordinates.

The third possibility is to use the [](custom_plugin_repository.md) with optional authorization credentials provided by defining the URL to the XML listing file, like:

```kotlin
repositories {
  intellijPlatform {
    customPluginRepository("https://example.com/plugins.xml", CustomPluginRepositoryType.SIMPLE) {
      credentials<HttpHeaderCredentials> {
        name = "Authorization"
        value = "Automation amFrdWJfdGVzdDotX...MkV2UkFwekFWTnNwZjA="
      }
    }
  }
}
```

The final plugin archive is eventually resolved using the same credentials used for resolving the listing.


| Function                            | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|-------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `localPlatformArtifacts()`          | Certain dependencies, such as the [local IntelliJ Platform instance](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) and bundled IDE plugins, need extra pre-processing before they can be correctly used by the IntelliJ Platform Gradle Plugin and loaded by Gradle. <p>This pre-processing involves generating XML files that detail these specific artifacts. Once created, these are stored in a unique custom [Ivy](https://ant.apache.org/ivy/) repository directory.</p> |
| `intellijDependencies()`            | Adds a repository for accessing IntelliJ Platform dependencies.                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| `jetbrainsRuntime()`                | Adds a repository for accessing [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) releases.                                                                                                                                                                                                                                                                                                                                                    |
| `marketplace()`                     | Adds a repository for accessing plugins hosted on [JetBrains Marketplace](https://plugins.jetbrains.com).                                                                                                                                                                                                                                                                                                                                                                                            |
| `customPluginRepository(url, type)` | Creates a custom plugin repository from which plugins                                                                                                                                                                                                                                                                                                                                                                                                                                                |


## Additional Repositories

| Function                            | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|-------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `localPlatformArtifacts()`          | Certain dependencies, such as the [local IntelliJ Platform instance](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) and bundled IDE plugins, need extra pre-processing before they can be correctly used by the IntelliJ Platform Gradle Plugin and loaded by Gradle. <p>This pre-processing involves generating XML files that detail these specific artifacts. Once created, these are stored in a unique custom [Ivy](https://ant.apache.org/ivy/) repository directory.</p> |
| `intellijDependencies()`            | Adds a repository for accessing IntelliJ Platform dependencies.                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| `jetbrainsRuntime()`                | Adds a repository for accessing [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) releases.                                                                                                                                                                                                                                                                                                                                                    |

See also:

- [](plugin_dependencies.md)

<snippet id="localPlatformArtifacts_required">

> Note that unless using recommended default [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories),
> the [`localPlatformArtifacts()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) entry needs to be added to the `repositories {}` block
> explicitly to use local dependencies (bundled plugins, local IDE, custom plugin repositories, etc.).
>
{style="tip" title="localPlatformArtifacts() and defaultRepositories()"}

</snippet>

<include from="snippets.md" element-id="missingContent"/>
