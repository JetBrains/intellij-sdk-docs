<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Repositories Extension

<link-summary>IntelliJ Platform Gradle Plugin repositories extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

This is an extension class for managing IntelliJ Platform repositories in a Gradle build script.
It's applied to the `RepositoryHandler`.

Available in both `Project` scope and Gradle Settings for `DependencyResolutionManagement`.

It provides methods to add:

- IntelliJ Platform repositories (for releases, snapshots, and nightly builds)
- JetBrains Marketplace repository for fetching plugins
- JetBrains Runtime repository
- Android Studio and IntelliJ Platform binary release repositories (for IntelliJ Plugin Verifier)
- Ivy local repository (for correct access to local dependencies)

<snippet id="recommendedCallout">

> In most cases, simply using [`recommended()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#recommended) repository will be sufficient.
>
{style="tip"}

**Example:**

Setup Maven Central and [`recommended()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#recommended) repositories:

```kotlin
repositories {
  mavenCentral()

  intellijPlatform {
    recommended()
  }
}
```

</snippet>

## Recommended

The default repository definition suitable for most plugins.

| Function        | Description                                |
|-----------------|--------------------------------------------|
| `recommended()` | Applies a set of recommended repositories. |

It includes:

- `releases()`, `snapshots()`
- `marketplace()`
- `jetbrainsRuntime()`
- `binaryReleases()`, `binaryReleasesAndroidStudio()`

## IDE Releases

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

## Additional Repositories

| Function             | Description                                                                                                                                                                                                                                                                                                         |
|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ivy()`              | Adds a local [Ivy](https://ant.apache.org/ivy/) repository for resolving local Ivy XML files used for describing artifacts like [local IntelliJ Platform instance](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform), bundled plugins, and other dependencies that utilize `createIvyDependency`. |
| `jetbrainsRuntime()` | Adds a repository for accessing [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) releases.                                                                                                                                                                   |
| `marketplace()`      | Adds a repository for accessing plugins hosted on [JetBrains Marketplace](https://plugins.jetbrains.com).                                                                                                                                                                                                           |

See also:

- [](plugin_dependencies.md)

<include from="snippets.md" element-id="missingContent"/>
