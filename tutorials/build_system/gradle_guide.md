---
title: Configuring Gradle Projects
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page serves as a guide to Gradle-based plugin configuration for _IntelliJ Platform_ projects.
The IntelliJ IDEA Ultimate and Community editions bundle the _Gradle_ and _Plugin DevKit_ plugins to support Gradle-based development. 

The [Getting Started with Gradle](prerequisites.md) page provides a tutorial for creating Gradle-based IntelliJ Platform plugins.
It may be useful to review the IntelliJ Platform page, particularly the description of versioning in the [Open Source](/intro/intellij_platform.md#open-source) section.

> **WARNING** When adding additional repositories to your Gradle build script, make sure to always use HTTPS protocol.

* bullet list
{:toc}

## Overview of the Gradle Plugin 
The Gradle plugin is built from the open-source project [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin).
This plugin adds Gradle tasks that enable developing IntelliJ Platform plugins.
The [README](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md) file has a reference for configuring these tasks.
 
When getting started, there are several items to note on the README page:
* At the top of the page, the [latest production version](https://github.com/JetBrains/gradle-intellij-plugin#the-latest-version) of the plugin is listed. It is advised to upgrade to the latest available version regularly.
* Also, at the top is the minimum required version of Gradle.
* The table of extended Gradle [Tasks](https://github.com/JetBrains/gradle-intellij-plugin#tasks) has a succinct description for each task added by the plugin.
  This documentation will focus on the configuration and use four of those tasks:
  * [Setup DSL](https://github.com/JetBrains/gradle-intellij-plugin#setup-dsl) - `intellij { ... }`.
  * [Running DSL](https://github.com/JetBrains/gradle-intellij-plugin#running-dsl) - `runIde { ... }`
  * [Patching DSL](https://github.com/JetBrains/gradle-intellij-plugin#patching-dsl) - `patchPluginXml { ... }`
  * [Publishing DSL](https://github.com/JetBrains/gradle-intellij-plugin#publishing-dsl) - `publishPlugin { ... }`
* Examples are always a helpful resource, and at the bottom of the page are links to [example](https://github.com/JetBrains/gradle-intellij-plugin#examples) open source IntelliJ Platform plugin projects based on Gradle.
* Almost every Gradle plugin attribute has a default value that will work to get started on a Gradle-based IntelliJ Platform plugin project.


## Guide to Configuring Gradle Plugin Functionality
This section presents a guided tour of Gradle plugin attributes to achieve commonly desired functionality.

### Configuring the Gradle Plugin for Building IntelliJ Platform Plugin Projects
By default, the Gradle plugin will build a plugin project against the IntelliJ Platform defined by the latest EAP snapshot of the IntelliJ IDEA Community Edition.

> **NOTE** Using EAP versions of the IntelliJ Platform requires adding the _Snapshots repository_ to the `build.gradle` file (see [IntelliJ Platform Artifacts Repositories](/reference_guide/intellij_artifacts.md)). 

If a matching version of the specified IntelliJ Platform is not available on the local machine, the Gradle plugin downloads the correct version and type.
IntelliJ IDEA then indexes the build and any associated source code and JetBrains Java Runtime.

#### IntelliJ Platform Configuration
Explicitly setting the [Setup DSL](https://github.com/JetBrains/gradle-intellij-plugin#setup-dsl) attributes `intellij.version` and `intellij.type` tells the Gradle plugin to use that configuration of the IntelliJ Platform to build the plugin project.
                                    
All available platform versions can be browsed in the [IntelliJ Platform Artifacts Repositories](/reference_guide/intellij_artifacts.md).

If the chosen platform version is not available in the repositories, or a local installation of the target IDE is the desired type and version of the IntelliJ Platform, use `intellij.localPath` to point to that installation.
If the `intellij.localPath` attribute is set, do not set the `intellij.version` and `intellij.type` attributes as this could result in undefined behavior.

#### Plugin Dependencies
IntelliJ Platform plugin projects may depend on either bundled or third-party plugins.
In that case, a project should build against a version of those plugins that match the IntelliJ Platform version used to build the plugin project.
The Gradle plugin will fetch any plugins in the list defined by `intellij.plugins`.
See the Gradle plugin [README](https://github.com/JetBrains/gradle-intellij-plugin#setup-dsl) for information about specifying the plugin and version.

Note that this attribute describes a dependency so the Gradle plugin can fetch the required artifacts.
The runtime dependency must be added in the [Plugin Configuration](/basics/plugin_structure/plugin_configuration_file.md) (`plugin.xml`) file as described in [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md#3-dependency-declaration-in-pluginxml).

### Configuring the Gradle Plugin for Running IntelliJ Platform Plugin Projects
By default, the Gradle plugin will use the same version of the IntelliJ Platform for the IDE Development Instance as was used for building the plugin.
Using the corresponding JetBrains Runtime is also the default, so for this use case no further configuration is required.

#### Running Against Alternate Versions and Types of IntelliJ Platform-Based IDEs
The IntelliJ Platform IDE used for the Development Instance can be different from that used to build the plugin project.
Setting the [Running DSL](https://github.com/JetBrains/gradle-intellij-plugin#running-dsl) attribute `runIde.ideDirectory` will define an IDE to be used for the Development Instance.
This attribute is commonly used when running or debugging a plugin in an [alternate IntelliJ Platform-based IDE](/intro/intellij_platform.md#ides-based-on-the-intellij-platform).

#### Running Against Alternate Versions of the JetBrains Runtime
Every version of the IntelliJ Platform has a corresponding version of the [JetBrains Runtime](/basics/ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
A different version of the runtime can be used by specifying the `runIde.jbrVersion` attribute, describing a version of the JetBrains Runtime that should be used by the IDE Development Instance.
The Gradle plugin will fetch the specified JetBrains Runtime as needed.

### Managing Directories Used by the Gradle Plugin
There are several attributes to control where the Gradle plugin places directories for downloads and for use by the IDE Development Instance.

The location of the [sandbox home](/basics/ide_development_instance.md#sandbox-home-location-for-gradle-based-plugin-projects) directory and its subdirectories can be controlled with Gradle plugin attributes.
The `intellij.sandboxDirectory` attribute is used to set the path for the sandbox directory to be used while running the plugin in an IDE Development Instance. 
Locations of the sandbox [subdirectories](/basics/ide_development_instance.md#development-instance-settings-caches-logs-and-plugins) can be controlled using the `runIde.configDirectory`, `runIde.pluginsDirectory`, and `runIde.systemDirectory` attributes.
If the `intellij.sandboxDirectory` path is explicitly set, the subdirectory attributes default to the new sandbox directory.

The storage location of downloaded IDE versions and components defaults to the Gradle cache directory.
However, it can be controlled by setting the `intellij.ideaDependencyCachePath` attribute.

### Controlling Downloads by the Gradle Plugin
As mentioned in the section about [configuring the IntelliJ Platform](#configuring-the-gradle-plugin-for-building-intellij-platform-plugin-projects) used for building plugin projects, the Gradle plugin will fetch the version of the IntelliJ Platform specified by the default or by the `intellij` attributes.
Standardizing the versions of the Gradle plugin and Gradle system across projects will minimize the time spent downloading versions.

There are controls for managing the `gradle-intellij-plugin` version, and the version of Gradle itself.
The plugin version is defined in the `plugins {}` section of a project's `build.gradle` file.
The version of Gradle is defined in `<PROJECT ROOT>/gradle/wrapper/gradle-wrapper.properties`.

### Patching the Plugin Configuration File
A plugin project's `plugin.xml` file has element values that are "patched" at build time from the attributes of the `patchPluginXml` task ([Patching DSL](https://github.com/JetBrains/gradle-intellij-plugin#patching-dsl)). 
As many as possible of the attributes in the Patching DSL will be substituted into the corresponding element values in a plugin project's `plugin.xml` file:
* If a `patchPluginXml` attribute default value is defined, the attribute value will be patched in `plugin.xml` _regardless of whether the `patchPluginXml` task appears in the `build.gradle` file_.
  * For example, the default values for the attributes `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` are defined based on the declared (or default) value of `intellij.version`.
    So by default `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` are substituted into the `<idea-version>` element's `since-build` and `until-build` attributes in the `plugin.xml` file.
* If a `patchPluginXml` attribute value is explicitly defined, the attribute value will be substituted in `plugin.xml`.
  * If both `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` attributes are explicitly set, both are substituted in `plugin.xml`. 
  * If one attribute is explicitly set (e.g. `patchPluginXml.sinceBuild`) and one is not (e.g. `patchPluginXml.untilBuild` has default value,) both attributes are patched at their respective (explicit and default) values.
* For **no substitution** of the `<idea-version>` element's `since-build` and `until-build` attributes, one of the following must appear in the `build.gradle` file:
  * Either set `intellij.updateSinceUntilBuild = false`, which will disable substituting both `since-build` and `until-build` attributes,
  * Or, for independent control, set `patchPluginXml.sinceBuild(null)` and `patchPluginXml.untilBuild(null)` depending on whether the intention is to disable one or both substitutions. 

A best practice to avoid confusion is to replace the elements in `plugin.xml` that will be patched by the Gradle plugin with a comment.
That way the values for these parameters do not appear in two places in the source code.
The Gradle plugin will add the necessary elements as part of the patching process.
For those `patchPluginXml` attributes that contain descriptions such as `changeNotes` and `pluginDescription`, a `CDATA` block is not necessary when using HTML elements.

> **TIP** To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).

As discussed in [Components of a Wizard-Generated Gradle IntelliJ Platform Plugin](prerequisites.md#components-of-a-wizard-generated-gradle-intellij-platform-plugin), the Gradle properties `project.version`, `project.group`, and `rootProject.name` are all generated based on the input to the Wizard.
However, the `gradle-intellij-plugin` does not combine and substitute those Gradle properties for the default `<id>` and `<name>` elements in the `plugin.xml` file.

The best practice is to keep `project.version` current. 
By default, if you modify `project.version` in `build.gradle`, the Gradle plugin will automatically update the `<version>` value in the `plugin.xml` file. 
This practice keeps all version declarations synchronized.

### Publishing with the Gradle Plugin
Please review the [Publishing Plugins with Gradle](deployment.md) page before using the [Publishing DSL](https://github.com/JetBrains/gradle-intellij-plugin#publishing-dsl) attributes.
That documentation explains different ways to use Gradle for plugin uploads without exposing account credentials.


## Common Gradle Plugin Configurations for Development
Different combinations of Gradle plugin attributes are needed to create the desired build or IDE Development Instance environment.
This section reviews some of the more common configurations.

### Plugins Targeting IntelliJ IDEA
IntelliJ Platform plugins targeting IntelliJ IDEA have the most straightforward Gradle plugin configuration.
* Determine the version of [IntelliJ IDEA to use for building the plugin project](#configuring-the-gradle-plugin-for-building-intellij-platform-plugin-projects); this is the desired version of the IntelliJ Platform.
  This can be EAP (default) or determined from the [build number ranges](/basics/getting_started/build_number_ranges.md). 
  * If a production version of IntelliJ IDEA is the desired target, set the `intellij` [version attributes](#intellij-platform-configuration) accordingly. 
  * Set the necessary [plugin dependencies](#plugin-dependencies), if any. 
* If the plugin project should be run or debugged in an IDE Development Instance based on the same IntelliJ IDEA version, no further attributes need to be set for the IDE Development Instance. 
  This is the default behavior and is the most common use case. 
  * If the plugin project should be run or debugged in an IDE Development Instance based on an alternate version of the IntelliJ Platform, set the [Running](#running-against-alternate-versions-and-types-of-intellij-platform-based-ides) DSL attribute accordingly.
  * If the plugin project should be run using a JetBrains Runtime other than the default for the IDE Development Instance, specify the [JetBrains Runtime version](#running-against-alternate-versions-of-the-jetbrains-runtime). 
* Set the appropriate attributes for [patching the `plugin.xml` file](#patching-the-plugin-configuration-file).

### Plugins Targeting Alternate IntelliJ Platform-Based IDEs
Gradle also supports developing plugins to run in IDEs that are based on the IntelliJ Platform.
For more information, see the [Developing for Multiple Products](/products/dev_alternate_products.md) page of this guide.
