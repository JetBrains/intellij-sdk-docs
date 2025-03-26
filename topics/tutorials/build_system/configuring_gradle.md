<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Configuring IntelliJ Platform Gradle Plugin (2.x)

<web-summary>Essential configuration and tasks for IntelliJ Platform Gradle Plugin (2.x) plugin projects.</web-summary>
<link-summary>Configuring the essential IntelliJ Platform Gradle Plugin (2.x) attributes and tasks.</link-summary>

This section presents a short overview of the most important Gradle plugin configuration elements to achieve commonly desired functionality.

For more advanced options and topics, see:

- [](tools_intellij_platform_gradle_plugin.md) reference
- [](tools_intellij_platform_gradle_plugin_faq.md)
- [](tools_intellij_platform_gradle_plugin_recipes.md)
- [](tools_intellij_platform_gradle_plugin_migration.md)


<include from="snippets.topic" element-id="gradlePluginVersion"/>

## Keep Up To Date

IntelliJ Platform Gradle Plugin (2.x) and [Gradle](https://gradle.org/install/) build system are constantly developed, and every new release
brings important bug fixes, new features, and improvements that make the development more efficient.

It is strongly recommended to keep updating both Gradle and IntelliJ Platform Gradle Plugin to the latest versions.
Newer IDE releases might not be supported fully in older releases of the IntelliJ Platform Gradle Plugin.

> The current IntelliJ Platform Gradle Plugin (2.x) version is **%intellij-platform-gradle-plugin-version%**
>
{style="note"}

## Setup

See [](tools_intellij_platform_gradle_plugin.md#configuration) for the necessary declaration of repositories.

## Target Platform and Dependencies

<include from="configuring_plugin_project.md" element-id="whichPlatformVersion"/>

If a matching version of the specified IntelliJ Platform is not present on the local machine, the Gradle plugin will download it.
IntelliJ IDEA then indexes the build and any associated source code and [JetBrains Java Runtime](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md).

To build a plugin for more than one target platform version, see [](build_number_ranges.md#multipleIDEVersions) for important notes.

### IntelliJ Platform Configuration

The target IDE platform is set in the `dependencies {}` block,
see [](tools_intellij_platform_gradle_plugin.md#setting-up-intellij-platform) for a minimal sample.

[](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-platforms) lists all available extension functions for known target IDE platforms.

### Plugin Dependencies

IntelliJ Platform plugin projects may depend on either bundled or third-party plugins defined in the `dependencies {}` block,
see [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) for details.

The runtime dependency must be added in the [Plugin Configuration](plugin_configuration_file.md) (<path>plugin.xml</path>) file as described in
[Plugin Dependencies](plugin_dependencies.md#dependency-declaration-in-pluginxml).

## Run IDE Task

By default, the [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task will use the same version of the IntelliJ Platform
for the IDE Development instance as was used for building the plugin.

## Verifying Plugin

The following tasks allow running integrity and compatibility tests:

- [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) - runs the [](verifying_plugin_compatibility.md#plugin-verifier) tool to check the binary compatibility with the specified IDE builds
- [`verifyPluginStructure`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginStructure) — validates completeness and contents of [plugin.xml descriptors](plugin_configuration_file.md) as well as the plugin's archive structure
- [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration) — validates the plugin project configuration

## Publishing Plugin

Review the [](publishing_plugin.md) page before using the [`publishPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin) task.

<include from="snippets.topic" element-id="missingContent"/>
