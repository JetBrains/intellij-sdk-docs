[//]: # (title: Configuring Gradle Projects)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page serves as a guide to Gradle-based plugin configuration for IntelliJ Platform projects.
The IntelliJ IDEA Ultimate and Community editions bundle the _Gradle_ and _Plugin DevKit_ plugins to support Gradle-based development.

The [](gradle_prerequisites.md) page provides a tutorial for creating Gradle-based IntelliJ Platform plugins.
It may be useful to review the IntelliJ Platform page, particularly the description of versioning in the [](intellij_platform.md#open-source) section.

> When adding additional repositories to your Gradle build script, always use HTTPS protocol.
>
{type="warning"}

## Overview of the Gradle IntelliJ Plugin

The Gradle plugin is built from the open-source project [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).
This plugin adds Gradle tasks that enable developing IntelliJ Platform plugins.

## Guide to Configuring Gradle IntelliJ Plugin Functionality

This section presents a guided tour of Gradle plugin attributes to achieve the commonly desired functionality.

### Configuring the Gradle IntelliJ Plugin for Building IntelliJ Platform Plugin Projects

By default, the Gradle plugin will build a plugin project against the IntelliJ Platform defined by the latest EAP snapshot of the IntelliJ IDEA Community Edition.

> Using EAP versions of the IntelliJ Platform requires adding the _Snapshots repository_ to the Gradle build script (see [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md)).
>
{type="note"}

If a matching version of the specified IntelliJ Platform is not available on the local machine, the Gradle plugin downloads the correct version and type.
IntelliJ IDEA then indexes the build and any associated source code and JetBrains Java Runtime.

#### IntelliJ Platform Configuration

Explicitly setting the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) and [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) properties tells the Gradle plugin to use that configuration of the IntelliJ Platform to create the plugin project.

All available platform versions can be browsed in the [](intellij_artifacts.md).

If the chosen platform version is not available in the repositories, or a local installation of the target IDE is the desired type and version of the IntelliJ Platform, use [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) to point to that installation.
If the [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) attribute is set, do not set the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) and [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) attributes as this could result in undefined behavior.

#### Plugin Dependencies

IntelliJ Platform plugin projects may depend on either bundled or third-party plugins.
In that case, a project should build against a version of those plugins that match the IntelliJ Platform version used to build the plugin project.
The Gradle plugin will fetch any plugins in the list defined by [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins).
See the Gradle plugin [IntelliJ Extension](tools_gradle_intellij_plugin.md#intellij-extension) for information about specifying the plugin and version.

Note that this attribute describes a dependency so that the Gradle plugin can fetch the required artifacts.
The runtime dependency must be added in the [Plugin Configuration](plugin_configuration_file.md) (<path>plugin.xml</path>) file as described in [Plugin Dependencies](plugin_dependencies.md#3-dependency-declaration-in-pluginxml).

### Configuring the Gradle Plugin for Running IntelliJ Platform Plugin Projects

By default, the Gradle plugin will use the same version of the IntelliJ Platform for the IDE Development Instance as was used for building the plugin.
Using the corresponding JetBrains Runtime is also the default, so for this use-case no further configuration is required.

#### Running Against Alternate Versions and Types of IntelliJ Platform-Based IDEs

The IntelliJ Platform IDE used for the Development Instance can be different from that used to build the plugin project.
Setting the [`runIde.ideDir`](tools_gradle_intellij_plugin.md#runide-task-idedir) property will define an IDE to be used for the Development Instance.
This attribute is commonly used when running or debugging a plugin in an [alternate IntelliJ Platform-based IDE](intellij_platform.md#ides-based-on-the-intellij-platform).

#### Running Against Alternate Versions of the JetBrains Runtime

Every version of the IntelliJ Platform has a corresponding version of the [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
A different version of the runtime can be used by specifying the [`runIde.jbrVersion`](tools_gradle_intellij_plugin.md#runide-task-jbrversion) attribute, describing a version of the JetBrains Runtime that should be used by the IDE Development Instance.
The Gradle plugin will fetch the specified JetBrains Runtime as needed.

### Managing Directories Used by the Gradle Plugin

There are several attributes to control where the Gradle plugin places directories for downloads and use by the IDE Development Instance.

The location of the [sandbox home](ide_development_instance.md#the-development-instance-sandbox-directory) directory and its subdirectories can be controlled with Gradle plugin attributes.
The [`intellij.sandboxDirectory`](tools_gradle_intellij_plugin.md#intellij-extension-sandboxdir) attribute is used to set the path for the sandbox directory to be used while running the plugin in an IDE Development Instance.
Locations of the sandbox [subdirectories](ide_development_instance.md#development-instance-settings-caches-logs-and-plugins) can be controlled using the [`runIde.configDirectory`](tools_gradle_intellij_plugin.md#runide-task), [`runIde.pluginsDirectory`](tools_gradle_intellij_plugin.md#runide-task), and [`runIde.systemDirectory`](tools_gradle_intellij_plugin.md#runide-task) attributes.
If the [`intellij.sandboxDirectory`](tools_gradle_intellij_plugin.md#intellij-extension-sandboxdir) path is explicitly set, the subdirectory attributes default to the new sandbox directory.

The storage location of downloaded IDE versions and components defaults to the Gradle cache directory.
However, it can be controlled by setting the [`intellij.ideaDependencyCachePath`](tools_gradle_intellij_plugin.md#intellij-extension-ideadependencycachepath) attribute.

### Controlling Downloads by the Gradle Plugin

As mentioned in the section about [configuring the IntelliJ Platform](#configuring-the-gradle-intellij-plugin-for-building-intellij-platform-plugin-projects) used for building plugin projects, the Gradle plugin will fetch the version of the IntelliJ Platform specified by the default or by the `intellij` attributes.
Standardizing the versions of the Gradle plugin and Gradle system across projects will minimize the time spent downloading versions.

There are controls for managing the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) version, and the version of Gradle itself.
The plugin version is defined in the `plugins {...}` section of a project's Gradle build script.
The version of Gradle is defined in <path>$PROJECT_ROOT$/gradle/wrapper/gradle-wrapper.properties</path>.

### Patching the Plugin Configuration File

A plugin project's <path>plugin.xml</path> file has element values that are "patched" at build time from the attributes of the [`patchPluginXml`](tools_gradle_intellij_plugin.md#patchpluginxml-task) task.
As many as possible of the attributes in the Patching DSL will be substituted into the corresponding element values in a plugin project's <path>plugin.xml</path> file:
* If a [`patchPluginXml`](tools_gradle_intellij_plugin.md#patchpluginxml-task) attribute default value is defined, the attribute value will be patched in <path>plugin.xml</path> _regardless of whether the [`patchPluginXml`](tools_gradle_intellij_plugin.md#patchpluginxml-task) task appears in the Gradle build script_.
  * For example, the default values for the attributes [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-sincebuild) and [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-untilbuild) are defined based on the declared (or default) value of [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version).
    So by default [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-sincebuild) and [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-untilbuild) are substituted into the `<idea-version>` element's `since-build` and `until-build` attributes in the <path>plugin.xml</path> file.
* If a [`patchPluginXml`](tools_gradle_intellij_plugin.md#patchpluginxml-task) attribute value is explicitly defined, the attribute value will be substituted in <path>plugin.xml</path>.
  * If both [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-sincebuild) and [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-untilbuild) attributes are explicitly set, both are substituted in <path>plugin.xml</path>.
  * If one attribute is explicitly set (e.g. [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-sincebuild)) and one is not (e.g. [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#patchpluginxml-task-untilbuild) has a default value,) both attributes are patched at their respective (explicit and default) values.
* For **no substitution** of the `<idea-version>` element's `since-build` and `until-build` attributes, one of the following must appear in the Gradle build script:
  * Either set [`intellij.updateSinceUntilBuild`](tools_gradle_intellij_plugin.md#intellij-extension-updatesinceuntilbuild) to `false`, which will disable substituting both `since-build` and `until-build` attributes,

The best practice to avoid confusion is to replace the elements in <path>plugin.xml</path> that will be patched by the Gradle plugin with a comment.
That way, the values for these parameters do not appear in two places in the source code.
The Gradle plugin will add the necessary elements as part of the patching process.
For those [`patchPluginXml`](tools_gradle_intellij_plugin.md#patchpluginxml-task) attributes that contain descriptions such as [`patchPluginXml.changeNotes`](tools_gradle_intellij_plugin.md#patchpluginxml-task-changenotes) and [`patchPluginXml.pluginDescription`](tools_gradle_intellij_plugin.md#patchpluginxml-task-plugindescription), a `CDATA` block is not necessary when using HTML elements.

> To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).
>
{type="tip"}

As discussed in [Components of a Wizard-Generated Gradle IntelliJ Platform Plugin](gradle_prerequisites.md#components-of-a-wizard-generated-gradle-intellij-platform-plugin), the Gradle properties `project.version`, `project.group`, and `rootProject.name` are all generated based on the input to the Wizard.
However, the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md) does not combine and substitute those Gradle properties for the default `<id>` and `<name>` elements in the <path>plugin.xml</path> file.

The best practice is to keep `project.version` current.
By default, if you modify `project.version` in Gradle build script, the Gradle plugin will automatically update the `<version>` value in the <path>plugin.xml</path> file.
This practice keeps all version declarations synchronized.

### Verifying Plugin

The Gradle plugin provides two tasks that allow for running integrity and compatibility tests:
* [`verifyPlugin`](tools_gradle_intellij_plugin.md#verifyplugin-task) task - validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin's archive structure,
* [`runPluginVerifier`](tools_gradle_intellij_plugin.md#runpluginverifier-task) task - runs the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool to check the binary compatibility with specified IntelliJ IDE builds.

Plugin Verifier integration task allows for configuring the exact IDE versions that your plugin will be checked against.
See [Verifying Compatibility](api_changes_list.md#verifying-compatibility) for more information.

### Publishing with the Gradle Plugin

Please review the [](deployment.md) page before using the [`publishPlugin`](tools_gradle_intellij_plugin.md#publishplugin-task) task.
That documentation explains different ways to use Gradle for plugin uploads without exposing account credentials.

## Common Gradle Plugin Configurations for Development

Different combinations of Gradle plugin attributes are needed to create the desired build or IDE Development Instance environment.
This section reviews some of the more common configurations.

### Plugins Targeting IntelliJ IDEA

IntelliJ Platform plugins targeting IntelliJ IDEA have the most straightforward Gradle plugin configuration.
* Determine the version of [IntelliJ IDEA to use for building the plugin project](#configuring-the-gradle-intellij-plugin-for-building-intellij-platform-plugin-projects); this is the desired version of the IntelliJ Platform.
  This can be EAP (default) or determined from the [build number ranges](build_number_ranges.md).
  * If a production version of IntelliJ IDEA is the desired target, set the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) property accordingly.
  * Set the necessary [plugin dependencies](#plugin-dependencies), if any.
* If the plugin project should be run or debugged in an IDE Development Instance based on the same IntelliJ IDEA version, no further attributes need to be set for the IDE Development Instance.
  This is the default behavior and is the most common use case.
  * If the plugin project should be run or debugged in an IDE Development Instance based on an alternate version of the IntelliJ Platform, set the [Running](#running-against-alternate-versions-and-types-of-intellij-platform-based-ides) DSL attribute accordingly.
  * If the plugin project should be run using a JetBrains Runtime other than the default for the IDE Development Instance, specify the [JetBrains Runtime version](#running-against-alternate-versions-of-the-jetbrains-runtime).
* Set the appropriate attributes for [patching the <path>plugin.xml</path> file](#patching-the-plugin-configuration-file).

### Plugins Targeting Alternate IntelliJ Platform-Based IDEs

Gradle also supports developing plugins to run in IDEs that are based on the IntelliJ Platform.
For more information, see the [Developing for Multiple Products](dev_alternate_products.md) page of this guide.
