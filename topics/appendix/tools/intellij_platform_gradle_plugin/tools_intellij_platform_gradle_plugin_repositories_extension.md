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

**Example:**

```kotlin
repositories {
  // ...

  intellijPlatform {
    // ...

    releases()
    snapshots()
    nightly()
    marketplace()
    jetbrainsRuntime()
    binaryReleasesAndroidStudio()
    binaryReleases()
    ivy()
    recommended()
  }
}
```

| Function                        | Description                                                                                                                                                                                                    |
|---------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `releases()`                    | Adds a repository for accessing IntelliJ Platform stable releases.                                                                                                                                             |
| `snapshots()`                   | Adds a repository for accessing IntelliJ Platform snapshot releases.                                                                                                                                           |
| `nightly()`                     | Adds a repository for accessing IntelliJ Platform nightly releases.                                                                                                                                            |
| `marketplace()`                 | Adds a repository for accessing plugins hosted on JetBrains Marketplace.                                                                                                                                       |
| `jetbrainsRuntime()`            | Adds a repository for accessing JetBrains Runtime releases.                                                                                                                                                    |
| `binaryReleasesAndroidStudio()` | Adds a repository for accessing Android Studio binary releases.                                                                                                                                                |
| `binaryReleases()`              | Adds a repository for accessing IntelliJ Platform binary releases.                                                                                                                                             |
| `ivy()`                         | Adds a local Ivy repository for resolving local Ivy XML files used for describing artifacts like local IntelliJ Platform instance, bundled plugins, and other dependencies that utilize `createIvyDependency`. |
| `recommended()`                 | Applies a set of recommended repositories.                                                                                                                                                                     |


<include from="snippets.md" element-id="missingContent"/>
