<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Samples

<link-summary>Accessing SDK Docs code samples.</link-summary>

This guide comes with a number of sample plugins available from dedicated [intellij-sdk-code-samples](%gh-sdk-samples-master%/) GitHub repository.

Please see <path>README.md</path> which lists all available code samples with a short description.

Each sample is stored in a dedicated folder and is accompanied by its own <path>README.md</path>.
Links to the corresponding tutorial or reference page in this tutorial, as well as a list of relevant show-cased elements are provided.

## Using Gradle

All sample plugins are based on Gradle, see [](creating_plugin_project.md) to get started.

Additionally, the [Working with Gradle in IntelliJ IDEA](https://youtu.be/6V6G3RyxEMk) screencast offers a thorough introduction to Gradle functionality inside IntelliJ IDEA.

## Setting up Code Samples

Make sure plugins _Git_, _Gradle_, and _Plugin DevKit_ are enabled.

<include from="snippets.topic" element-id="pluginDevKitAvailability"/>

Clone the [intellij-sdk-code-samples](%gh-sdk-samples-master%/) GitHub repository via <control>Git | Clone...</control>.
After successful cloning, the IDE suggests opening the project.

Select the code sample(s) to import via the [Gradle tool window](https://www.jetbrains.com/help/idea/gradle.html#link_gradle_project).

Alternatively, import _all_ code samples available by choosing <path>_gradleCompositeBuild</path>, which links all Gradle projects in a Composite Build.

After successful import, the project appears in the <control>Gradle</control> tool window tree as a new node.

Assign a Java 17 SDK in <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path> for <control>Gradle JVM</control>.

Invoke <control>Reload All Gradle Projects</control> from the Gradle tool window toolbar if necessary.

## Running Code Samples

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#executing-the-plugin) task shown under the corresponding project's <control>Tasks</control> node in the <control>Gradle</control> tool window.
