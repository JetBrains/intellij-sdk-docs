<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IDE Development Instance

<link-summary>Overview of the IDE instance used for running and debugging a plugin during development.</link-summary>

A plugin project can be run or debugged from within the development instance of IntelliJ IDEA.
Selecting the `runIde` task for a Gradle-based project (or [Run](running_and_debugging_a_theme.md) menu for a _Plugin DevKit_-based project)
will launch a _Development Instance_ of the target IDE with the current development version of the plugin enabled.

This page describes how to control some settings for the Development Instance.

> See also `runIde` task (Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [1.x](tools_gradle_intellij_plugin.md#tasks-runide)) properties and [Advanced Configuration](https://www.jetbrains.com/help/idea/tuning-the-ide.html) for general VM options and properties.
>

## Using a JetBrains Runtime for the Development Instance

> See [](tools_intellij_platform_gradle_plugin_jetbrains_runtime.md) when using [](tools_intellij_platform_gradle_plugin.md).
>
{title="IntelliJ Platform Gradle Plugin (2.x)"}

An everyday use case is to develop (build) a plugin project against a JDK, e.g., Java 17, and then run or debug the plugin in a Development Instance of the IDE.
In such a situation, Development Instance must use a [JetBrains Runtime (JBR)](https://www.jetbrains.com/jetbrains-runtime) rather than the JDK used to build the plugin project.

The JetBrains Runtime is an environment for running IntelliJ Platform-based IDEs on Windows, macOS, and Linux.
It has some modifications by JetBrains, such as fixes for native crashes not present in official JDK builds.
A version of the JetBrains Runtime is bundled with all IntelliJ Platform-based IDEs.
To produce accurate results while running or debugging a plugin project in a Development Instance, follow the procedures below to ensure the Development Instance uses a JetBrains Runtime.

### Using JetBrains Runtime

<tabs group="project-type">

<tab title="Gradle IntelliJ Plugin (1.x)" group-key="gradle">

By default, the Gradle plugin will fetch and use the version of the JetBrains Runtime for the Development Instance corresponding to the version of the IntelliJ Platform used for building the plugin project.
If required, an alternative version can be specified using the [`runIde.jbrVersion`](tools_gradle_intellij_plugin.md#tasks-runide-jbrversion) task property.

</tab>

<tab title="Plugin DevKit" group-key="devkit">

The [Run Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) for a DevKit-based plugin project controls the JDK used to run and debug a plugin project in a Development Instance.
The default Run Configuration uses the same JDK for building the plugin project and running the plugin in a Development Instance.

To change the runtime for the Development Instance, set the _JRE_ field in the Run Configuration edit dialog to download a JetBrains Runtime.

</tab>
</tabs>

### Determining a JetBrains Runtime Version

The JetBrains Runtime is determined by the JDK version used to build the plugin project, regardless of whether it is built on macOS, Windows, or Linux.

<procedure title="Determine an Example JetBrains Runtime Version">

If a plugin is developed against the Java 8 SE Development Kit 8 for macOS (<path>jdk-8u212-macosx-x64.dmg</path>) to acquire the compatible JetBrains Runtime:

1. Go to the [GitHub JetBrains Runtime Releases](https://github.com/JetBrains/JetBrainsRuntime) for general information and the latest build.
2. Open the [Releases](https://github.com/JetBrains/JetBrainsRuntime/releases) page to access all releases.
3. Select the package name corresponding to the platform and SDK version.
   In this case, the package name is `jbrsdk8-osx-x64` for **J**et**B**rains **R**untime _SDK_ version 8, macOS x64 hardware.
4. In the list of files, find the name that satisfies:
    * The version and build number match the JDK used to build the plugin project.
      For example, `jbrx-8u252-osx-x64` matches the Java 8 JDK, build 252: `jdk-8u252-macosx-x64`.
    * Pick the highest JetBrains Runtime build number available.
      For example, the file is <path>jbrx-8u252-osx-x64-b1649.2.tar.gz</path>, meaning build 1649.2 for this JetBrains Runtime matching Java 8 JDK build 252.

</procedure>

### JetBrains Runtime Variants

The JetBrains Runtime is delivered in various variants used for different purposes, like debugging, running for development purposes, or bundling with the IDE.

Available JBR variants are:

- `jcef` - the release bundles with the [JCEF](embedded_browser_jcef.md) browser engine
- `sdk` - JBR SDK bundle used for development purposes
- `fd` - the fastdebug bundle which also includes the `jcef` module
- `dcevm` - bundles DCEVM (Dynamic Code Evolution Virtual Machine)
- `nomod` – the release bundled without any additional modules

> For `JBR 17`, `dcevm` is bundled by default.
> As a consequence, separated `dcevm` and `nomod` variants are no longer available.
>
{style="note"}

## Enabling Auto-Reload

<primary-label ref="2020.1"/>

Auto-Reload is available for compatible [dynamic plugins](dynamic_plugins.md).
This allows a much faster development cycle by avoiding a full restart of the development instance after detecting code changes (when JARs are modified).

Please note that any unloading problems in a production environment will ask the user to restart the IDE.

> Auto-Reload does not work when the sandbox IDE instance is running under a debugger.
>
{style="warning" title="Debugging"}

### IntelliJ Platform Gradle Plugin (2.x)

Auto-Reload is enabled by default.

Set property [`intellijPlatform.autoReload`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-autoReload) to `false` to disable it explicitly,
see [](tools_intellij_platform_gradle_plugin_faq.md#how-to-disable-the-automatic-reload-of-dynamic-plugins)

After starting the sandbox IDE instance, run the [`buildPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#buildPlugin) task after modifications
in the plugin project and switch back focus to the sandbox instance to trigger reload.

> [`buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) task must currently be
> [disabled explicitly](tools_intellij_platform_gradle_plugin_faq.md#how-to-disable-building-the-searchable-options) to work around
> _Only one instance of IDEA can be run at a time_ problem.
>
{style="warning"}

### Gradle IntelliJ Plugin (1.x)

{collapsible="true" default-state="collapsed"}

<primary-label ref="Obsolete"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

Auto-Reload is enabled by default when targeting 2020.2 or later.

Set the property [`runIde.autoReloadPlugins`](tools_gradle_intellij_plugin.md#tasks-runide-autoreloadplugins) to `true` for enabling it in earlier platform versions or `false` to disable it explicitly,
see [](tools_gradle_intellij_plugin_faq.md#how-to-disable-automatic-reload-of-dynamic-plugins)

After starting the sandbox IDE instance, run the [`buildPlugin`](tools_gradle_intellij_plugin.md#tasks-buildplugin) task after modifications in the plugin project
and switch focus back to the sandbox instance to trigger reload.

> [`buildSearchableOptions`](tools_gradle_intellij_plugin.md#tasks-buildsearchableoptions) task must currently be
> [disabled explicitly](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options) to work around
> _Only one instance of IDEA can be run at a time_ problem.
>
{style="warning"}

### Plugin DevKit

{collapsible="true" default-state="collapsed"}

Add system property `idea.auto.reload.plugins` in the _Plugin DevKit_ [run configuration](running_and_debugging_a_theme.md).

To disable auto-reload, set `idea.auto.reload.plugins` to `false` explicitly.

## The Development Instance Sandbox Directory

The _Sandbox Home_ directory contains the [settings, caches, logs, and plugins](#development-instance-settings-caches-logs-and-plugins) for a Development Instance of the IDE.
This information is stored in a different location than for the [installed IDE itself](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs).

### IntelliJ Platform Gradle Plugin (2.x) {id="sandboxGradle2"}

The default Sandbox Home location in a [](tools_intellij_platform_gradle_plugin.md) plugin project is:

* Windows: <path>\$PROJECT_DIRECTORY\$\\build\\\$TARGET_IDE\$\\idea-sandbox</path>
* Linux/macOS: <path>\$PROJECT_DIRECTORY\$/build/\$TARGET_IDE\$/idea-sandbox</path>

The Sandbox Home location can be configured with the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer) property.

### Gradle IntelliJ Plugin (1.x) {id="sandboxGradle1"}

{collapsible="true" default-state="collapsed"}

<primary-label ref="Obsolete"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

The default Sandbox Home location in a [](tools_gradle_intellij_plugin.md) plugin project is:

* Windows: <path>\$PROJECT_DIRECTORY\$\\build\\idea-sandbox</path>
* Linux/macOS: <path>\$PROJECT_DIRECTORY\$/build/idea-sandbox</path>

The Sandbox Home location can be configured with the [`intellij.sandboxDir`](tools_gradle_intellij_plugin.md#intellij-extension-sandboxdir) property.

### Plugin DevKit {id="sandboxPluginDevKit"}

{collapsible="true" default-state="collapsed"}

For _Plugin DevKit_-based plugins, the default <control>Sandbox Home</control> location is defined in the IntelliJ Platform Plugin SDK.
See the [Setting Up a Theme Development Environment](setting_up_theme_environment.md#add-intellij-platform-plugin-sdk) for information about how to set up Sandbox Home in IntelliJ Platform SDK.

The default Sandbox Home directory location is:

* Windows: <path>\$USER_HOME\$\\.\$PRODUCT_SYSTEM_NAME\$\$PRODUCT_VERSION\$\\system\\plugins-sandbox\\</path>
* Linux: <path>~/.\$PRODUCT_SYSTEM_NAME\$\$PRODUCT_VERSION\$/system/plugins-sandbox/</path>
* macOS: <path>~/Library/Caches/\$PRODUCT_SYSTEM_NAME\$\$PRODUCT_VERSION\$/plugins-sandbox/</path>

### Development Instance Settings, Caches, Logs, and Plugins

Within the Sandbox Home directory are subdirectories of the Development Instance:

* <path>config</path> contains settings for the IDE instance.
* <path>plugins</path> contains folders for each plugin being run in the IDE instance.
* <path>system/caches</path> or <path>system\caches</path> holds the IDE instance data.
* <path>system/log</path> or <path>system\log</path> contains the <path>idea.log</path> file for the IDE instance.

Each of these Sandbox Home subdirectories can be manually cleared to reset the IDE Development Instance.
At the next launch of a Development Instance, the subdirectories will be repopulated with the appropriate information.
