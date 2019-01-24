---
title: IntelliJ Platform Artifacts Repository
---

JetBrains maintains a public repository that hosts artifacts related to the IntelliJ Platform, such as binaries and source code. This 
repository makes artifacts more accessible for plugin authors.

The IntelliJ Platform Artifacts Repository contains:
* [Releases repository](https://www.jetbrains.com/intellij-repository/releases/) for release and EAP versions.
* [Snapshots repository](https://www.jetbrains.com/intellij-repository/snapshots/) for EAP candidates and the latest EAP for each branch.
  
[Dependencies](https://jetbrains.bintray.com/intellij-third-party-dependencies) of individual modules from the IntelliJ
Platform are hosted at the bintray repository. These artifacts should not be used directly, but a link to this 
repository should be added to pom.xml/build.gradle files if individual modules from the IntelliJ Platform Artifacts Repository are used.    

## How to Use the IntelliJ Platform Artifacts Repository
Artifacts in the repositories are utilized by adding information to a project's build.gradle file. See
the [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin) for more information about
Gradle support. 

If you want to setup the dependencies manually, there are two types of information needed to use a repository:
1. Specify the corresponding repository URL for the artifact.
2. Specify the Maven coordinates for the artifact. 
 
### Add the Repository URL 
The corresponding URL for the desired artifact needs to be added to a Maven or Gradle script:
* For release or EAP versions, use https://www.jetbrains.com/intellij-repository/releases 
* For EAP candidate snapshots, use https://www.jetbrains.com/intellij-repository/snapshots
* For dependencies of individual modules from the IntelliJ Platform, use https://jetbrains.bintray.com/intellij-third-party-dependencies 

### Specify the Artifact
Describing the desired artifact is done with Maven coordinates:
* Cross-platform zip distributions of IntelliJ Platform artifacts:
  * groupId = com.jetbrains.intellij.idea
  * artifactId = ideaIC
  * classifier = ""
  * packaging = .zip
* Sources of IntelliJ IDEA Community Edition:
  * groupId = com.jetbrains.intellij.idea
  * artifactId = ideaIC
  * classifier = sources
  * packaging = jar
* Individual modules from the IntelliJ Platform with their dependencies. For example, to specify the jps-model-serialization module:
  * groupId = com.jetbrains.intellij.platform
  * artifactId = jps-model-serialization
  * classifier = ""
  * packaging = jar

For each artifact [at the Repository URLs](#add-the-repository-url) there are multiple versions available. The version can be specified in one of several ways:
* A branch build is specified as _BRANCH.BUILD[.FIX]_. For example, a branch build such as `141.233`, or a branch build with a fix such as `139.555.1`
* Release numbers are specified as _MAJOR[.MINOR][.FIX]_. For example `14`, or `14.1`, or `14.1.1`
* A snapshot of a branch from which the next EAP/release build will be produced is specified as _BRANCH-EAP-SNAPSHOT_. For example `141-EAP-SNAPSHOT`


## Examples
This section presents several examples of using a Gradle script to incorporate the repository in a build.gradle file. Each example
illustrates declaring the artifact URL, Maven coordinates, and Version for an artifact.
There are two parts to each example: the repository and the dependency sections.

### Gradle Example for IntelliJ IDEA Community Edition Distribution
These snippets illustrate incorporating an IntelliJ snapshot build into a build.gradle file.

**Repository Section**  
This snippet selects the snapshot repository for the artifact by virtue of the URL.
```groovy
repositories {
    maven { url "https://www.jetbrains.com/intellij-repository/snapshots" }
}
```

**Dependencies Section**  
Given the repository section has selected a snapshot, the dependencies section specifies the desired build artifact.
```groovy
dependencies {
    idea_dep "com.jetbrains.intellij.idea:ideaIC:141-SNAPSHOT@zip"
}
```
Where `idea_dep` names this custom configuration.

Here is an example of a [build.gradle](https://github.com/shalupov/idea-cloudformation/blob/9007023afa187a1fb8b45c3ca66d5a51f86b795c/build.gradle)
file that is currently in use.

### Gradle Example for an Individual Module from the IntelliJ IDEA Project
These snippets illustrate incorporating an IntelliJ module into a build.gradle file. In this case the desired module is `jps-model-serialization`.

**Repositories Section**  
This snippet selects the release repository with the first URL, and the repository of IntelliJ IDEA dependencies with the second URL.
The second URL is needed because this example selects individual modules, not a full build. 
```groovy
repositories {
	maven { url "https://www.jetbrains.com/intellij-repository/releases" }
	maven { url "https://jetbrains.bintray.com/intellij-third-party-dependencies" }
}
```

**Dependencies Section**  
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
