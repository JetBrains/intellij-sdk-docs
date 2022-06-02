[//]: # (title: Code Samples)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This guide comes with a number of sample plugins available from dedicated [intellij-sdk-code-samples](https://github.com/JetBrains/intellij-sdk-code-samples) GitHub repository.

Please see <path>README.md</path> which lists all available code samples with a short description.

Each sample is stored in a dedicated folder and is accompanied by its own <path>README.md</path>.
Links to the corresponding tutorial or reference page in this tutorial, as well as a list of relevant show-cased elements are provided.

## Using Gradle

All sample plugins are based on Gradle, see [Building Plugins with Gradle](gradle_build_system.md) to get started.

Additionally, the screencast *Working with Gradle in IntelliJ IDEA* offers a thorough introduction to Gradle functionality inside IntelliJ IDEA.

<video href="6V6G3RyxEMk" title="Working with Gradle in IntelliJ IDEA" width="300"/>

## Setting up Code Samples

Make sure plugins _Git_, _Gradle_, and _Plugin DevKit_ are enabled.

Clone the [intellij-sdk-code-samples](https://github.com/JetBrains/intellij-sdk-code-samples) GitHub repository via <control>Git | Clone...</control>.
After successful cloning, the IDE suggests opening the project.

Select the code sample(s) to import via the [Gradle tool window](https://www.jetbrains.com/help/idea/gradle.html#link_gradle_project).

Alternatively, import _all_ code samples available by choosing <path>_gradleCompositeBuild</path>, which links all Gradle projects in a Composite Build.

After successful import, the project appears in the <control>Gradle</control> tool window tree as a new node.
Assign a Java 11 SDK in <menupath>Preferences | Build, Execution, Deployment | Build Tools | Gradle</menupath> for <control>Gradle JVM</control>.
Invoke <control>Reload All Gradle Projects</control> from the Gradle tool window toolbar if necessary.

## Running Code Samples

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#executing-the-plugin) task shown under the corresponding project's <control>Tasks</control> node in the <control>Gradle</control> tool window.
