---
title: IntelliJ Platform Artifacts Repositories
---

JetBrains maintains public repositories that host artifacts related to the IntelliJ Platform, such as binaries and source code. These 
repositories make artifacts more accessible for plugin developers.

The IntelliJ Platform artifacts repositories are:
* [Releases repository](https://www.jetbrains.com/intellij-repository/releases/) for release and EAP versions.
* [Snapshots repository](https://www.jetbrains.com/intellij-repository/snapshots/) for EAP candidates and the latest EAP for each branch.

Both the Releases and Snapshots repositories have two types of content:
* Artifacts for cross-platform, zip distributions of IntelliJ IDEA binaries and source code.
These artifacts are not intended to be accessed directly from a plugin project's `build.gradle` file. 
The `gradle-intellij-plugin` will access them as-needed for a plugin project.
* Artifacts for individual modules from the IntelliJ Platform. 
These may be downloaded or accessed directly from a `build.gradle` file, as explained below.
  
Artifacts for IntelliJ Platform module third-party dependencies are hosted at the [Bintray repository](https://jetbrains.bintray.com/intellij-third-party-dependencies). 
These artifacts should not be used directly.
A link to this repository should be added to `pom.xml`/`build.gradle` files if individual modules from the IntelliJ Platform Artifacts Repository are used.    

## Using IntelliJ Platform Module Artifacts
IntelliJ Platform module artifacts are utilized by adding information to a project's `build.gradle` file. 
See the [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin) for more information about
Gradle support. 

There are two types of information needed to use a repository:
1. Specify the corresponding repository URL for the artifact.
2. Specify the Maven coordinates for the artifact. 
 
### Add the Repository URL 
The corresponding URL for the desired artifact needs to be added to a Maven or Gradle script:
* For release or EAP versions, use https://www.jetbrains.com/intellij-repository/releases 
* For EAP candidate snapshots, use https://www.jetbrains.com/intellij-repository/snapshots
* For dependencies of individual modules from the IntelliJ Platform, also use https://jetbrains.bintray.com/intellij-third-party-dependencies 

### Specify the Artifact
Describing a desired IntelliJ Platform module artifact is done with Maven coordinates. 
For example, to specify the `jps-model-serialization` module:
  * groupId = com.jetbrains.intellij.platform
  * artifactId = jps-model-serialization
  * classifier = ""
  * packaging = jar

For each artifact [at the Repository URLs](#add-the-repository-url) there are multiple versions available. The version can be specified in one of several ways:
* A branch build is specified as _BRANCH.BUILD[.FIX]_. For example, a branch build such as `141.233`, or a branch build with a fix such as `139.555.1`
* Release numbers are specified as _MAJOR[.MINOR][.FIX]_. For example `14`, or `14.1`, or `14.1.1`
* A snapshot of a branch from which the next EAP/release build will be produced is specified as _BRANCH-EAP-SNAPSHOT_. For example `141-EAP-SNAPSHOT`


## Gradle Example for an Individual Module from the IntelliJ IDEA Project
This section presents an example of using a Gradle script to incorporate an IntelliJ Platform module and repository in a `build.gradle` file. 
The example illustrates declaring the artifact URL, Maven coordinates, and Version for the `jps-model-serialization` module artifact.
There are two parts to the example: the repository and the dependency sections.

### Repositories Section  
This snippet selects the release repository with the first URL, and repository of IntelliJ IDEA dependencies with the second URL.
The second URL is needed because this example selects individual modules. 
```groovy
repositories {
	maven { url "https://www.jetbrains.com/intellij-repository/releases" }
	maven { url "https://jetbrains.bintray.com/intellij-third-party-dependencies" }
}
```

### Dependencies Section  
This dependencies section specifies the desired module artifacts.
```groovy
dependencies {
	compile "com.jetbrains.intellij.platform:jps-model-serialization:182.2949.4"
	compile "com.jetbrains.intellij.platform:jps-model-impl:182.2949.4"
}
```
Note:
 * The artifact version (`182.2949.4`) must match in both statements.
 * In this example `jps-model-serialization` declares the APIs and `jps-model-impl` provides the implementation, so both
   are required dependencies.
