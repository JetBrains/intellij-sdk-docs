<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Artifacts Repositories

<link-summary>Overview of the repositories hosting artifacts related to the IntelliJ Platform.</link-summary>

> When using additional repositories, make sure to use HTTPS always.
>
{style="warning"}

JetBrains maintains public repositories that host artifacts related to the IntelliJ Platform, such as binaries and source code.
These repositories make artifacts more accessible for plugin developers.

The IntelliJ Platform artifacts repositories are:
* [Releases repository](https://www.jetbrains.com/intellij-repository/releases/) for release versions by [build number](build_number_ranges.md).
* [Snapshots repository](https://www.jetbrains.com/intellij-repository/snapshots/) for _BRANCH#-EAP-SNAPSHOT_, _EAP-CANDIDATE-SNAPSHOT_, _LATEST-EAP-SNAPSHOT_, and the _EAP-SNAPSHOT_.

See the [Maven coordinates](#specify-the-maven-coordinates-for-the-artifact) section for details about specifying these artifacts.

Both the Releases and Snapshots repositories have two types of content:
* Binary and source code artifacts for cross-platform, ZIP distributions of IntelliJ Platform-based IDEs, such as IntelliJ IDEA, CLion, Rider, and MPS.
  These artifacts are _not intended_ to be accessed directly from a plugin project's Gradle build script.
  See also [](tools_intellij_platform_gradle_plugin_repositories_extension.md).

  The [](tools_gradle_intellij_plugin.md) will access them implicitly as-needed for a plugin project.
* Artifacts for individual modules from the IntelliJ Platform.
These may be downloaded, or accessed directly from a Gradle build script, as explained below.

### Third-Party Dependencies

Artifacts for IntelliJ Platform third-party dependencies are hosted at a separate [intellij-dependencies](https://cache-redirector.jetbrains.com/intellij-dependencies) repository.
A link to this repository should be added to Maven POM or Gradle build script when individual modules from an IntelliJ Platform artifacts repository are used.

> Usages of deprecated URL `https://jetbrains.bintray.com/intellij-third-party-dependencies` must be replaced with `https://cache-redirector.jetbrains.com/intellij-dependencies` in build scripts.
>
{style="warning"}

## Using IntelliJ Platform Module Artifacts

IntelliJ Platform module artifacts are utilized by adding information to a project's Gradle build script.
More information about [Gradle support](https://www.jetbrains.com/help/idea/gradle.html) is available in the IntelliJ IDEA Help documentation.

To set up dependencies on a module, there are two types of information needed:
1. Specify the corresponding repository URL for the artifact.
2. Specify the [Maven coordinates](https://maven.apache.org/pom.html#Maven_Coordinates) for the artifact.

### Specify the Repository URL

The URL for the desired artifact needs to be added to a Maven or Gradle script:
* For release versions, use:

  `https://www.jetbrains.com/intellij-repository/releases`
* For EAP snapshots, use:

  `https://www.jetbrains.com/intellij-repository/snapshots`
* For dependencies on individual modules from the IntelliJ Platform, also use:

  `https://cache-redirector.jetbrains.com/intellij-dependencies`

### Specify the Maven Coordinates for the Artifact

Describing a desired IntelliJ Platform module artifact is done with Maven coordinates: _groupId_, _artifactId_, and _version_.
The Maven coordinates are based on the names of modules.

The _groupId_ for a module is the prefix `com.jetbrains.` concatenated with the first two parts of the module name.
For example, the module `intellij.xml` would have the groupId `com.jetbrains.intellij.xml`.

The _artifactId_ is the second.._n_ parts of the module name separated by "-" characters.
For example, the module `intellij.xml` would have the artifactId `xml`.
There are some special cases to artifactId names.
If the second part of the module name is a common group like `platform`, `vcs`, or `cloud`, the second part of the module name is dropped, and the artifactId becomes the third.._n_ parts of the module name, separated by "-" characters.
Portions of the module name expressed in `camelCase` format are divided and used in the artifactId as (all lower case) `camel-case`.

The table below shows some example module names and their corresponding groupId and artifactId.

| Module Name                     | groupId                         | artifactId              |
|---------------------------------|---------------------------------|-------------------------|
| intellij.java.compiler.antTasks | com.jetbrains.intellij.java     | java-compiler-ant-tasks |
| intellij.java.debugger          | com.jetbrains.intellij.java     | java-debugger           |
| intellij.platform.util          | com.jetbrains.intellij.platform | util                    |
| intellij.platform.vcs.log       | com.jetbrains.intellij.platform | vcs-log                 |
| intellij.spring                 | com.jetbrains.intellij.spring   | spring                  |
| intellij.xml.impl               | com.jetbrains.intellij.xml      | xml-impl                |

The artifact _version_ can be specified in one of several ways because each artifact [at the Repository URLs](#specify-the-repository-url) has multiple versions available:
* Specify release build versions as _MAJOR\[.MINOR]\[.FIX]_. For example `14`, or `14.1`, or `14.1.1`
* Snapshot versions are specified as:
  * The snapshot of the most recent branch build is specified as _BRANCH-EAP-SNAPSHOT_. For example, `193-EAP-SNAPSHOT`.
    There is only one of this type of build for each branch of each product.
  * The snapshot of the branch from which the next EAP/release build might be produced is specified as _BRANCH.BUILD-EAP-CANDIDATE-SNAPSHOT_. For example `193.4386-EAP-CANDIDATE-SNAPSHOT`.
    There are multiple builds of this type, one for each build in each branch of every product.
  * The latest snapshot of a product is always specified as _LATEST-EAP-SNAPSHOT_.
    There is only one build of this type per product, and it is always the same as the _BRANCH-EAP-SNAPSHOT_ for the newest branch of the product.
  * A snapshot of a branch is specified as _BRANCH.BUILD.FIX-EAP-SNAPSHOT_. For example, `193.4386.10-EAP-SNAPSHOT`.
    There are many builds of this type for each branch of each product.

### Example Artifact Specification

For example, to specify the `jps-model-serialization` module:
* _groupId_ = `com.jetbrains.intellij.platform`
* _artifactId_ = `jps-model-serialization`
* _classifier_ = `""`
* _packaging_ = `jar`

## Gradle Example for an Individual Module from the IntelliJ Platform

This section presents an example of adding IntelliJ Platform repository and module in a Gradle build script.
The example illustrates declaring the artifact URL, Maven coordinates, and version for the `jps-model-serialization` module artifact.
There are two parts to the example: the repository and the dependency sections.

### Repositories Section

This code snippet selects the release repository with the first URL, and the repository of IntelliJ Platform dependencies with the second URL.
The second URL is needed because this example selects individual modules.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  maven("https://www.jetbrains.com/intellij-repository/releases")
  maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  maven { url "https://www.jetbrains.com/intellij-repository/releases" }
  maven { url "https://cache-redirector.jetbrains.com/intellij-dependencies" }
}
```

</tab>
</tabs>

### Dependencies Section

This code snippet specifies the desired module artifacts.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  implementation("com.jetbrains.intellij.platform:jps-model-serialization:182.2949.4")
  implementation("com.jetbrains.intellij.platform:jps-model-impl:182.2949.4")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  implementation "com.jetbrains.intellij.platform:jps-model-serialization:182.2949.4"
  implementation "com.jetbrains.intellij.platform:jps-model-impl:182.2949.4"
}
```

</tab>
</tabs>

Note:
* The artifact version (`182.2949.4`) must match in both statements.
* In this example `jps-model-serialization` declares the APIs and `jps-model-impl` provides the implementation, so both are required dependencies.
