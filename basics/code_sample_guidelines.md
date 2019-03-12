---
title: Guidelines for Creating SDK Plugin Sample Code
---

## Objectives
Keep the following considerations in mind while authoring an SDK code sample:
* The purpose of an SDK sample is to demonstrate the implementation pattern to build on top of subsystems and components of the IntelliJ Platform.
* Think in terms of two levels of SDK samples:
  * A _basic_ sample is focused on a particular subject by demonstrating a limited area of the IntelliJ Platform. 
    It should show all the components and architecture but ultimately accomplish something elementary. 
    For example, demonstrate persistence by storing only a `String`.
  * An _advanced_ sample demonstrates fuller functionality that could be a very sparse proto-feature. 
    These sample plugins by nature can't just focus on implementing one area of the platform.
    Multiple IntelliJ Platform areas are needed to accomplish the richer functionality.
    Demonstrating how different parts of the IntelliJ Platform integrate and work together is an essential objective for this type of code sample. 
* SDK code samples are instructional code, intended to teach.
  * Code samples replace lots of documentation, and code is generally preferred by developers over documentation. 
  * Instructional code differs from production code in some key aspects:
    * Sacrifice thorough error checking in the interests of simplicity and brevity.
      Use error checking where it is necessary to make a point about an implementation pitfall.
    * Keep implementations as simple as possible, but use the full features of the IntelliJ Platform and Java language. 
    * Use meaningful names for interfaces, classes, fields, and methods.
    * Write more verbose comments.
    
Ultimately, the goal is to provide developers with roadmaps for implementing functionality in an IntelliJ Platform-based plugin.
Each roadmap should contain:
* Pointers to SDK documentation about the IntelliJ Platform APIs needed to implement the functionality.
* Pointers to relevant _basic_ SDK sample plugins.
* Pointers to relevant _advanced_ SDK sample plugins.

## Naming Conventions for SDK Plugins
For _basic_ samples the naming convention is focused on the IntelliJ Platform APIs being demonstrated:  
    `foo_basics`  
where `foo` corresponds to an IntelliJ Platform feature, framework, or component.
Some examples include `facet_basics` and `editor_basics`.
There is only one _basic_ sample per IntelliJ Platform API area.

For _advanced_ code samples, the name should reflect the complex functionality delivered by the plugin rather than the IntelliJ Platform APIs.
Advanced samples will be cross-referenced to the IntelliJ Platform APIs demonstrated in the sample.

The plugin name is also known as the `Artifact ID` in the Gradle plugin wizard.

## Plugin Copyright Statements
Use the standard intellij-community copyright notice in all sample plugins authored by JetBrains:  
   "_Copyright 2000-$today.year JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file._"  
The copyright statement must appear in every source file.

## Plugin ID Conventions
The plugin ID (`<id>` in `plugin.xml`) always begins with `org.intellij.sdk`.
The plugin ID is known as the `Group ID` in the Gradle plugin wizard.

For _basic_ code samples, it is not necessary to include "basic" in the plugin ID.
A plugin like `facet_basics` has the ID `org.intellij.sdk.facet`.

## Plugin Package Names
Packages in plugins should begin with the plugin ID.
If there is only one package in a plugin, then the package name is the same as the plugin ID.

## Plugin Structure
SDK sample plugins should have a standard directory footprint.
Standardized structure not only makes the samples easier to navigate and understand, but it builds on the default Gradle plugin project structure.
```text
basic_foo/
  gradle/
  src/
    main/
      java/
      resources/
        icons/
          sdk.svg           # The standard SDK icon for menus, etc.
        META-INF/
          plugin.xml
          pluginIcon.svg    # The standard SDK plugin icon
    test/                # Omit if there are no tests
      java/
      resources/
  build.gradle
  gradlew
  gradle.bat
  settings.gradle
```

## plugin.xml Conventions
A consistent structure for the `plugin.xml` file is important because we want to draw attention to the unique parts of the file for a plugin.
Inconsistent element order is visually noisy.
Comment profusely about unique elements and configurations, and comment sparingly for the rest.

The sequence of elements in a `plugin.xml` file is:
* `<id>`
* `<name>` The name value does not have to match the plugin name.
  It might reflect the functionality of the plugin.
  The name value must start with "SDK."
* `<version>` in MAJOR.MINOR.FIX format.
  The change classification should be considered in terms of significance and scope:
  * MAJOR corresponds to a compelling release.
  * MINOR corresponds to minor refactoring and changes in functionality. 
  * FIX corresponds to changes that fix problems without significant refactoring.
* `<idea-version>` should have a whole number for the `since-build` attribute.
  The value of the `since-build` attribute should be the earliest build compatible with the plugin's use of the IntelliJ Platform API.
  Except for special cases, the `until` attribute is not used in SDK plugins.
* `<description>` is a succinct explanation of what is demonstrated and how a consumer would access the functionality.
* `<change-notes>` is an ordered list by version numbers with a brief description of changes for each version.
* `vendor` is always: 
  `<vendor email="sdk-example@jetbrains.com" url="https://plugins.jetbrains.com">IntelliJ Platform SDK</vendor>`
* `<depends>` Except for special cases this attribute is not used in SDK plugins. 
* `<helpset>` Except for special cases this attribute is not used in SDK plugins.
* `<resource-bundle>` Except for special cases this attribute is not used in SDK plugins.
* `<application-components>` Deprecated and should not be used for new plugins.
* `<module-components>` Omit if not specifically used.
* `<project-components>` Omit if not specifically used.
* `<extensionPoints>` Generally not used by SDK plugins.
* `<actions>` Omit if not specifically used.
* `<extensions>` Omit if not specifically used.

## build.gradle Conventions 
A consistent structure for a `build.gradle` file is also important. 
We only want to draw attention to the parts of the file that are unique for a plugin.
The most important comment is to point developers to the `gradle-intellij-plugin` [documentation](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md).
The default contents of a `build.gradle` file are produced by the **File | New | Project** Gradle wizard. 

Some issues with the default Gradle configuration:
* Should we model [idea-gitignore](https://github.com/hsz/idea-gitignore/blob/master/build.gradle) to solve the following problems?
  * intellij.version is specified and defaults to the version of IntelliJ IDEA executing the wizard.
    In eap/beta cases gradle cannot find a build.
  * intellij.type is not specified and defaults to IC.
    It is the correct default, but should be specified explicitly.
  * project.group and project.version are disconnected from <id> and <version> tags in plugin.xml 
* patchPluginXml.changeNotes is generated - build.gradle or plugin.xml?
