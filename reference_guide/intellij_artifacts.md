---
title: IntelliJ IDEA Community Edition Artifacts Repository
---

@nik - a few questions in the document. The one systemic improvement I'd like to make is about nomenclature. Why is `artifactId` used
in the Artifact Description, but `name` is used as a key in the example Dependency statement? I feel like I need to provide a set
of bullet items after each example to translate the terms.

JetBrains maintains a public repository for hosting IntelliJ IDEA Community Edition artifacts such as binaries and source code. This 
repository makes artifacts more accessible for plugin writers.

@nik - Does this repository replace a previous system for external plugin developers, or were the artifacts just not available?  
@nik - Should we state on a public page that there will be additional products in the future - any concern about leading with our chin? 

Multiple types of content are hosted:
* [Releases repository](https://www.jetbrains.com/intellij-repository/releases/) for release and EAP versions.
* [Snapshots repository](https://www.jetbrains.com/intellij-repository/snapshots/) for nightly successful 
  builds of each branch.
* [Dependencies](https://jetbrains.bintray.com/intellij-third-party-dependencies) of individual modules from the IntelliJ IDEA project.  

### How to Use the Repository
There are three steps to use the repository:
1. Add the corresponding repository URL to a maven or gradle script.
2. Specify the description for the desired artifact.
3. Specify the build type and version for the artiface.
 
 
#### Add the Repository URL 
The corresponding URL for the desired artifact needs to be added to a maven or gradle script:
* For release or EAP versions, use https://www.jetbrains.com/intellij-repository/releases 
* For snapshot or EAP snapshots, use https://www.jetbrains.com/intellij-repository/snapshots
* For dependencies of individual modules from the IntelliJ IDEA Community Edition Project, use https://jetbrains.bintray.com/intellij-third-party-dependencies 

@nik - the dependencies URL leads
to a page with `org/` which leads to `jetbrains/` to `intellij/` to `deps/` which seems like a lot to wade through. Should
the URL include these subdirectories?

#### Specify the Artifact Description
Describing the details of a desired artifact is done with parameters to specify the IDs, type, and packaging of the artifacts:
* Cross-platform zip distributions of IntelliJ IDEA Community Edition:
  * groupId = com.jetbrains.intellij.idea
  * artifactId = ideaIC
  * classifier = ""
  * packaging = .zip
* Sources of IntelliJ IDEA Community Edition:
  * groupId = com.jetbrains.intellij.idea
  * artifactId = ideaIC
  * classifier = sources
  * packaging = jar
* Some individual modules from IntelliJ IDEA project with their dependencies, for example for module jps-model-serialization:
  * groupId = com.jetbrains.intellij.platform
  * artifactId = jps-model-serialization
  * classifier = ""
  * packaging = jar

@nik - Do all module specifications use `artifactID` = `jps-model-serialization`? Or is this just an example?

#### Specify the Artifact Version
For each artifact there are several versions available. The version can be specified in one of several ways:
* A branch build is specified as _BRANCH.BUILD[.FIX]_. For example, a branch build such as `141.233`, or a branch build with a fix such as `139.555.1`
* Release numbers are specified as _MAJOR[.MINOR][.FIX]_. For example `14`, or `14.1`, or `14.1.1`
* A snapshot of a branch is specified as _XXX-SNAPSHOT_. For example `142-SNAPSHOT`
* A snapshot of an early release is specified as _BRANCH-EAP-SNAPSHOT_. For example `141-EAP-SNAPSHOT`


### Examples
This section presents several examples of using a gradle script to incorporate the repository in a build.gradle file. Each example
illustrates declaring the artifact URL, Description, and Version for an artifact.
There are two parts to each example: the repository and the dependency sections.

#### Gradle Example for IntelliJ IDEA Community distribution
These snippets illustrate incorporating an IntelliJ snapshot build into a build.gradle file.

**Repository Section**  
This snippet selects the snapshot repository for the artifact by virtue of the URL.
```
repositories {
    maven { url "https://www.jetbrains.com/intellij-repository/snapshots" }
}
```

**Dependencies Section**  
Given the repository section has selected a snapshot, the dependencies section specifies the desired build artifact
based on the Artifact Description and Artifact Version.
```
dependencies {
    idea_dep group: 'com.jetbrains.intellij.idea', name: 'ideaIC', version: '141-SNAPSHOT', ext: 'zip'
}
```
Where:
* `idea_dep` names this specification as a custom build configuration.
* `group` corresponds to the `groupId` for builds in the Artifact Description section.
* `name` corresponds to the `artifactId` in the Artifact Description section.
* `version` specifies the artifact version - see the Artifact Version section.
* `ext` corresponds to the `packaging` in the Artifact Description section.

@nik - Is `idea_dep` being declared as this particular artifact dependency?

Here is an example of a [build.gradle](https://github.com/shalupov/idea-cloudformation/blob/9007023afa187a1fb8b45c3ca66d5a51f86b795c/build.gradle)
that is currently in use.

#### Gradle Example for an Iindividual Module from the IntelliJ IDEA project
These snippets illustrate incorporating separate IntelliJ modules into a build.gradle file.

**Repositories Section**  
This snippet selects the release respository with the first URL, and the repository of IntelliJ IDEA dependencies with the second URL.
The second URL is important because this example selects individual modules, not a full build. 
```
repositories {
	maven { url "https://www.jetbrains.com/intellij-repository/releases" }
	maven { url "https://jetbrains.bintray.com/intellij-third-party-dependencies" }
}
```

**Dependencies Section**  
This dependencies section specifies the desired module artifacts based on the Artifact Description and Artifact Version.
```
dependencies {
	compile "com.jetbrains.intellij.platform:jps-model-serialization:182.2949.4"
	compile "com.jetbrains.intellij.platform:jps-model-impl:182.2949.4"
}
```
Where:
* `com.jetbrains.intellij.platform` corresponds to the `groupId` for modules in the Artifact Description section.
* `jps-model-serialization` corresponds to the `artifactId` in the Artifact Description section.
* `182.2949.4` specifies the artifact version - see the Artifact Version section.
* `jps-model-impl` corresponds to the module `artifactId` in the Artifact Description section.
* **Note:** the artifact version must match in both statements.

@nik - Are the statements related in terms of both specifying `jps-model-`? Is there an example where only one module
would be specified?