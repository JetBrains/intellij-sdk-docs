<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Configuring Gradle IntelliJ Plugin (1.x)
<primary-label ref="Obsolete"/>

<link-summary>Configuring the essential Gradle IntelliJ Plugin attributes and tasks.</link-summary>

This page presents a guided tour of Gradle plugin attributes to achieve the commonly desired functionality.
For more advanced options, see the full [](tools_gradle_intellij_plugin.md) reference.

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## Keep Up To Date

Gradle IntelliJ Plugin and [Gradle](https://gradle.org/install/) build system are constantly developed, and every new release brings important bug fixes, new features, and improvements that makes the development more efficient.
It is strongly recommended to keep updating both Gradle and Gradle IntelliJ Plugin to the latest versions.
Newer IDE releases might not be supported fully in older releases of Gradle IntelliJ Plugin.

> Current Gradle IntelliJ Plugin version is %gradle-intellij-plugin-version%
{style="note"}

## Target Platform and Dependencies

<snippet id="whichPlatformVersion">

> Which versions should your plugin support? We've collected some insights based on download statistics in [Statistics: Product Versions in Use](https://plugins.jetbrains.com/docs/marketplace/product-versions-in-use-statistics.html).
>

</snippet>

By default, the Gradle plugin will build a plugin project against the IntelliJ Platform defined by the latest EAP snapshot of the IntelliJ IDEA Community Edition.

If a matching version of the specified IntelliJ Platform is not available on the local machine, the Gradle plugin downloads the correct version and type.
IntelliJ IDEA then indexes the build and any associated source code and JetBrains Java Runtime.

To build a plugin for more than one target platform version, see [](build_number_ranges.md#multipleIDEVersions) for important notes.

### IntelliJ Platform Configuration

Explicitly setting the [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) and [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) properties tells the Gradle plugin to use that configuration of the IntelliJ Platform to create the plugin project.

> See the [Developing for Multiple Products](dev_alternate_products.md) page for information about how to develop a plugin that is compatible with multiple IntelliJ-based IDEs.
>

All available platform versions can be browsed in the [](intellij_artifacts.md).

If the chosen platform version is not available in the repositories, or a local installation of the target IDE is the desired type and version of the IntelliJ Platform, use [`intellij.localPath`](tools_gradle_intellij_plugin.md#intellij-extension-localpath) to point to that installation.
If the `intellij.localPath` attribute is set, do not set the `intellij.version` and `intellij.type` attributes as this could result in undefined behavior.

### Plugin Dependencies

IntelliJ Platform plugin projects may depend on either bundled or third-party plugins.
In that case, a project should build against a version of those plugins that match the IntelliJ Platform version used to build the plugin project.
The Gradle plugin will fetch any plugins in the list defined by [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins).
See the Gradle plugin [IntelliJ Extension](tools_gradle_intellij_plugin.md#configuration-intellij-extension) for information about specifying the plugin and version.

Note that this attribute describes a dependency so that the Gradle plugin can fetch the required artifacts.
The runtime dependency must be added in the [Plugin Configuration](plugin_configuration_file.md) (<path>plugin.xml</path>) file as described in [Plugin Dependencies](plugin_dependencies.md#dependency-declaration-in-pluginxml).

## Run IDE Task

By default, the Gradle plugin will use the same version of the IntelliJ Platform for the IDE Development Instance as was used for building the plugin.
Using the corresponding JetBrains Runtime is also the default, so for this use-case no further configuration is required.

### Running Against Alternate Versions and Types of IntelliJ Platform-Based IDEs

The IntelliJ Platform IDE used for the [Development Instance](ide_development_instance.md) can be different from that used to build the plugin project.
Setting the [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir) property will define an IDE to be used for the Development Instance.
This attribute is commonly used when running or debugging a plugin in an [alternate IntelliJ Platform-based IDE](intellij_platform.md#ides-based-on-the-intellij-platform).

### Running Against Alternate Versions of the JetBrains Runtime

Every version of the IntelliJ Platform has a corresponding version of the [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
A different version of the runtime can be used by specifying the [`runIde.jbrVersion`](tools_gradle_intellij_plugin.md#tasks-runide-jbrversion) attribute, describing a version of the JetBrains Runtime that should be used by the IDE Development Instance.
The Gradle plugin will fetch the specified JetBrains Runtime as needed.

## Patching the Plugin Configuration File

A plugin project's <path>plugin.xml</path> file has element values that are "patched" at build time from the attributes of the [`patchPluginXml`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml) task.
As many as possible of the attributes in the Patching DSL will be substituted into the corresponding element values in a plugin project's <path>plugin.xml</path> file:
* If a `patchPluginXml` attribute default value is defined, the attribute value will be patched in <path>plugin.xml</path> _regardless of whether the `patchPluginXml` task appears in the Gradle build script_.
  * For example, the default values for the attributes [`patchPluginXml.sinceBuild`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-sincebuild) and [`patchPluginXml.untilBuild`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-untilbuild) are defined based on the declared (or default) value of [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version).
    So by default `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` are substituted into the [`<idea-version>`](plugin_configuration_file.md#idea-plugin__idea-version) element's `since-build` and `until-build` attributes in the <path>plugin.xml</path> file.
* If a [`patchPluginXml`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml) task's attribute value is explicitly defined, the attribute value will be substituted in <path>plugin.xml</path>.
  * If both `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` attributes are explicitly set, both are substituted in <path>plugin.xml</path>.
  * If one attribute is explicitly set (e.g. `patchPluginXml.sinceBuild`) and one is not (e.g. `patchPluginXml.untilBuild` has a default value), both attributes are patched at their respective (explicit and default) values.
* For **no substitution** of the `<idea-version>` element's `since-build` and `until-build` attributes, set [`intellij.updateSinceUntilBuild`](tools_gradle_intellij_plugin.md#intellij-extension-updatesinceuntilbuild) to `false`, and do not provide `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` values.

The best practice to avoid confusion is to replace the elements in <path>plugin.xml</path> that will be patched by the Gradle plugin with a comment.
That way, the values for these parameters do not appear in two places in the source code.
The Gradle plugin will add the necessary elements as part of the patching process.
For those [`patchPluginXml`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml) attributes that contain descriptions such as [`patchPluginXml.changeNotes`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-changenotes) and [`patchPluginXml.pluginDescription`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml-plugindescription), a `CDATA` block is not necessary when using HTML elements.

> To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).
>

As discussed in [](creating_plugin_project.md#components-of-a-wizard-generated-gradle-intellij-platform-plugin), the Gradle properties `project.version`, `project.group`, and `rootProject.name` are all generated based on the input to the Wizard.
However, the [](tools_gradle_intellij_plugin.md) does not combine and substitute those Gradle properties for the default [`<id>`](plugin_configuration_file.md#idea-plugin__id) and [`<name>`](plugin_configuration_file.md#idea-plugin__name) elements in the <path>plugin.xml</path> file.

The best practice is to keep `project.version` current.
By default, if you modify `project.version` in Gradle build script, the Gradle plugin will automatically update the [`<version>`](plugin_configuration_file.md#idea-plugin__version) value in the <path>plugin.xml</path> file.
This practice keeps all version declarations synchronized.

## Verifying Plugin

The Gradle plugin provides tasks that allow for running integrity and compatibility tests:
* [`verifyPluginConfiguration`](tools_gradle_intellij_plugin.md#tasks-verifypluginconfiguration) - validates the versions of SDK, target platform, APIs, etc., configured in a plugin project,
* [`verifyPlugin`](tools_gradle_intellij_plugin.md#tasks-verifyplugin) - validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin's archive structure,
* [`runPluginVerifier`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) - runs the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool to check the binary compatibility with specified IntelliJ IDE builds.

Plugin Verifier integration task allows for configuring the exact IDE versions that your plugin will be checked against.
See [](verifying_plugin_compatibility.md#plugin-verifier) for more information.

## Publishing Plugin

Please review the [](publishing_plugin.md) page before using the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) task.
That documentation explains different ways to use Gradle for plugin uploads without exposing account credentials.
