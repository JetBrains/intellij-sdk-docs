---
title: Guidelines for Creating IntelliJ Platform SDK Code Samples
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This document describes the coding guidelines used for authoring open-source IntelliJ Platform SDK code samples.
Before you begin, please read this page thoroughly, as well as the [Code of Conduct](/CODE_OF_CONDUCT.md) and [License](https://github.com/JetBrains/intellij-sdk-docs/blob/master/LICENSE.txt) documents.
For information about contributing to the IntelliJ Platform itself visit [Contributing to the IntelliJ Platform](/basics/platform_contributions.md).

  * Dummy list item
  {:toc}
  
## Objectives
Keep the following considerations in mind while authoring an SDK code sample:
* The purpose of an SDK sample is to demonstrate an implementation pattern of the IntelliJ Platform.
* SDK code samples are instructional code, intended to teach.
  * Instructional code differs from production code in some key aspects:
    * Sacrifice some robustness in the interest of simplicity and brevity.
      Use error checking where it is necessary to make a point about an implementation pitfall.
    * Keep implementations as simple as possible, but use the full features of the IntelliJ Platform, programming language, and libraries. 
    * Use meaningful names for interfaces, classes, fields, methods, and extension points.
    * Write instructional JavaDoc comments.    
  * Code samples replace lots of documentation. 
* Aim for two levels of SDK samples:
  * A _basic_ sample is focused on a particular subject by demonstrating a limited area of the IntelliJ Platform. 
    It should show all the components and architecture but ultimately accomplish something elementary. 
    For example, demonstrate persistence by storing only a `String`.
  * An _advanced_ sample demonstrates how different parts of the IntelliJ Platform integrate and work together, such as combining inspections or intentions with non-trivial PsiTree manipulation.
 
Ultimately, the goal is to provide developers with roadmaps for implementing functionality in an IntelliJ Platform-based plugin.
Each roadmap should contain:
* Pointers to SDK documentation about the IntelliJ Platform APIs needed to implement the functionality.
* Pointers to relevant _basic_ SDK sample plugins.
* Pointers to related _advanced_ SDK sample plugins.

## Plugin Copyright Statements
Use the standard intellij-community copyright notice in all sample plugins authored by JetBrains:

```text  
Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file."  
```

> **NOTE** The copyright statement must appear at the top of every source file. Use the [IntelliJ Platform SDK](https://github.com/JetBrains/intellij-sdk-docs/tree/master/.idea/copyright) copyright profile. 

## Directory Naming Conventions for SDK Plugins
For _basic_ samples, the plugin directory name is derived from the IntelliJ Platform extension points demonstrated.
For example, `foo_basics` where `foo` corresponds to an IntelliJ Platform framework, extension point, or component.
Some naming examples include `facet_basics` and `editor_basics`.
There is only one _basic_ sample per IntelliJ Platform API area.

For _advanced_ code samples, the name should reflect the complex functionality delivered by the plugin rather than the IntelliJ Platform APIs.
Advanced samples will be cross-referenced to the IntelliJ Platform APIs demonstrated in the sample.

The only symbol characters allowed in the name of a plugin root directory are underscores, such as `foo_basics` or `max_opened_projects`.
However, underscores should _not_ be used in any other symbol names, such as `Artifact ID`, plugin ID, package names, etc.
Instead, concatenate a long name into camelCase such as `maxOpenedProjects`

## Group and Artifact ID
When creating a Gradle-based IntelliJ Platform plugin, the plugin's Maven coordinates (`Group ID`, `Artifact ID`, `Version`) are defined.

The `Group ID` for SDK plugins is always `org.intellij.sdk`.

The `Artifact ID` is a succinct derivative of the plugin directory name.
An `Artifact ID` should not contain symbols.

For _basic_ code samples, it is not necessary to include "basic" in the `Artifact ID`.
For example, the `foo_basics` directory name would have the `Artifact ID` `foo`.

A plugin with a longer directory name, such as `conditional_operator_intention`, could have the more succinct `Artifact ID` of `conditionalOperatorIntention`.
(For legacy reasons, the `conditional_operator_intention` plugin uses a more succinct `Artifact ID`.)

## Plugin ID Conventions
The plugin ID appears between `<id>` elements in the `plugin.xml` file.

In general, the plugin ID is the `Group ID` concatenated with the `Artifact ID`.
For example, a plugin like `facet_basics` has the plugin ID `org.intellij.sdk.facet`.

Plugin IDs do not contain underscores. 

## Plugin Package Names
Packages in plugins should begin with the plugin ID.
If there is only one package in a plugin, then the package name is the same as the plugin ID.
Additional suffix words, separated by "." characters, can be added to form more specific package names.
Package names do not contain underscores. 

## Plugin Directory Structure
SDK sample code should have a standard directory footprint.
Standardized structure not only makes the samples simpler to navigate and understand, but it builds on the default Gradle plugin project structure.

Note that directories below the plugin root folder should not have underscore characters, and should use camelCase if needed.
The following is an example directory structure for a `foo_basics` plugin.

```text
code_samples/
  foo_basics/
    gradle/
    src/
      main/
        java/
          org.intellij.sdk.foo/
          icons/
            SdkIcons.java     # The standard SDK icon class
        resources/
          icons/
            sdk_16.svg        # The standard SDK icon for menus, etc.
          META-INF/
            plugin.xml        # The plugin configuration file
            pluginIcon.svg    # The standard SDK plugin icon
      test/                   # Omit if there are no tests
        java/
          org.intellij.sdk.foo/
        resources/
    build.gradle
    gradlew
    gradle.bat
    settings.gradle
```

## build.gradle Conventions 
New SDK code samples should be developed [using Gradle](/tutorials/build_system.md). 
As of this writing, the use of Gradle in SDK code samples still relies heavily on the `plugin.xml` for specifying the plugin configuration.
At a later, second phase, the SDK code samples will transition to rely more on the Gradle configuration. 

The default contents of a `build.gradle` file are produced by the [New Project Wizard](/tutorials/build_system/prerequisites.md#creating-a-gradle-based-intellij-platform-plugin-with-new-project-wizard). 
A consistent structure for an SDK code sample's `build.gradle` file is important for clarity and is based on the default produced by the project wizard. 
Comments in SDK code sample `build.gradle` files should only draw attention to the parts of the Gradle configuration that are unique for a plugin.

For SDK code samples, a few alterations are needed to the default `build.gradle` file produced by the plugin wizard:
* Maintain the Gradle properties `version` (`project.version`) and `group` (`project.group`).
  See the [Plugin Gradle Properties](/tutorials/build_system/prerequisites.md#plugin-gradle-properties-and-plugin-configuration-file-elements) section for how these Gradle properties relate to the elements in `plugin.xml`.
* Add the following statement to the [Setup DSL](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#setup-dsl) (`intellij{}`) section:
  ```groovy
      // Prevents patching <idea-version> attributes in plugin.xml
      updateSinceUntilBuild = false 
  ``` 
* Add the following statement to the [Patching DSL](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#patching-dsl) (`patchPluginXml{}`) section:
  ```groovy
      // Patches <version> value in plugin.xml
      version = project.version
  ```   
  
## plugin.xml Conventions
A consistent structure for the [`plugin.xml`](/basics/plugin_structure/plugin_configuration_file.md) configuration file of an SDK code sample is important because we want to draw attention to the unique parts of the file for a plugin.
Comment profusely about unique elements and configurations, and comment sparingly for the rest.

The sequence of elements in an SDK code sample `plugin.xml` file is:
* `<id>` Use the fully qualified [Plugin ID](#plugin-id-conventions).
* `<name>` The name value does not have to match the [Plugin Directory Name](#directory-naming-conventions-for-sdk-plugins).
  The name is used for display purposes, and should reflect the functionality of the plugin.
  The name must start with "SDK: ".
* `<version>` The code sample's version in MAJOR.MINOR.FIX format.
  * MAJOR corresponds to a significant upgrade in functionality.
  * MINOR corresponds to minor refactoring and small improvements in functionality. 
  * FIX corresponds to changes that fix problems without significant refactoring.
* `<idea-version>` Set the attributes:
  * `since-build` attribute to the earliest compatible build number of the IntelliJ Platform.
    The default for SDK samples is "193".
  * `until-build` Omit this attribute for new sample plugins.
    SDK code samples are reviewed before every major release (20XX.1) to ensure compatibility with the latest IntelliJ Platform.
    Add this attribute if a plugin sample is deprecated with a release of the IntelliJ Platform.
* `<depends>` Include at least one dependency with the module `com.intellij.modules.platform` to indicate basic plugin compatibility with IntelliJ Platform-based products.
  Add `<depends>` elements containing module FQNs as needed to describe more specialized [Compatibility with Multiple Products](/basics/getting_started/plugin_compatibility.md), and any other [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md). 
* `<description>` is a concise explanation of what is being demonstrated and how a user would access the functionality.
  If the plugin by default overrides IDE behavior (such as `tree_structure_provider`) it must be noted in the description.
* `<change-notes>` is an ordered list by version numbers with a brief description of changes for each version.
* `<vendor>` Set the value to `IntelliJ Platform SDK`.
  Set the attributes:
  * `email` omit this attribute. 
  * `url` to the JetBrains Plugins Repository `"https://plugins.jetbrains.com"`
* The remainder of the [plugin configuration elements](/basics/plugin_structure/plugin_configuration_file.md) should only appear if they are needed by a specific plugin.

## Testing
IntelliJ Platform SDK code samples should be built and tested against the `since-build` version.

Code samples should build cleanly, with no warnings or errors, and new code samples should pass all default IntelliJ IDEA code inspections.

Testers should complete the following checklist. 
Here the term "IDE" means the IntelliJ Platform-based IDE in which the plugin is designed to run:
* The plugin should load in the IDE.
* The correct information about the plugin should display in the **Preferences \| Plugins** panel.
* If applicable, the plugin UI such as tool windows, menu additions, etc. should display correctly.
* The functionality of the plugin should be tested with a sample file.
* If applicable, the plugin should pass unit tests.