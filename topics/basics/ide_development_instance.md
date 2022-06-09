[//]: # (title: IDE Development Instances)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A JetBrains feature for developing plugins is running or debugging a plugin project from within an IntelliJ Platform-based IDE such as IntelliJ IDEA.
Selecting the [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task for a Gradle-based project (or [Run](running_and_debugging_a_plugin.md) menu for a DevKit-based project) will launch a _Development Instance_ of the IDE with the plugin enabled.
This page describes how to control some of the settings for the Development Instance.

> Please see also [Advanced Configuration](https://www.jetbrains.com/help/idea/tuning-the-ide.html) for general VM options and properties.
>
{type="tip"}

## Using a JetBrains Runtime for the Development Instance
An everyday use case is to develop (build) a plugin project against a JDK, e.g., Java 8, and then run or debug the plugin in a Development Instance of the IDE.
In such a situation, Development Instance must use a JetBrains Runtime (JBR) rather than the JDK used to build the plugin project.

The JetBrains Runtime is an environment for running IntelliJ Platform-based IDEs on Windows, macOS, and Linux.
It has some modifications by JetBrains, such as fixes for native crashes not present in official JDK builds.
A version of the JetBrains Runtime is bundled with all IntelliJ Platform-based IDEs.
To produce accurate results while running or debugging a plugin project in a Development Instance, follow the procedures below to ensure the Development Instance uses a JetBrains Runtime.

### Determining a JetBrains Runtime Version
The JetBrains Runtime is determined from the JDK version used to build the plugin project, regardless of whether it is built on macOS, Windows, or Linux.
For example, if a plugin is developed against the Java 8 SE Development Kit 8 for macOS (<path>jdk-8u212-macosx-x64.dmg</path>) to acquire the compatible JetBrains Runtime:

* Go to the [GitHub JetBrains Runtime Releases](https://github.com/JetBrains/JetBrainsRuntime) for general information and the latest build.
* Open the [Releases](https://github.com/JetBrains/JetBrainsRuntime/releases) page to access all releases.
* Select the package name corresponding to the platform and SDK version.
  In this case, the package name is `jbrsdk8-osx-x64` for **J**et**B**rains **R**untime _SDK_ version 8, macOS x64 hardware.
* On the macOS package page of the JetBrains Bintray site, select the **Files** menu.
* In the list of files, find the name that satisfies:
  * The version and build number match the JDK used to build the plugin project.
    For example, `jbrx-8u252-osx-x64` matches the Java 8 JDK, build 252: `jdk-8u252-macosx-x64`.
  * Pick the highest JetBrains Runtime build number available.
    For example, the file is <path>jbrx-8u252-osx-x64-b1649.2.tar.gz</path>, meaning build 1649.2 for this JetBrains Runtime matching Java 8 JDK build 252.

### JetBrains Runtime Variants
The JetBrains Runtime is delivered in various variants used for different purposes, like debugging, running for development purposes or bundling with the IDE.

Available JBR variants are:
- `jcef` - the release bundles with the [JCEF](jcef.md) browser engine
- `sdk` - JBR SDK bundle used for development purposes
- `fd` - the fastdebug bundle which also includes the `jcef` module
- `dcevm` - bundles DCEVM (Dynamic Code Evolution Virtual Machine)
- `nomod` – the release bundled without any additional modules

> For `JBR 17`, `dcevm` is bundled by default.
> As a consequence, separated `dcevm` and `nomod` variants are no longer available.
>
{type="note"}

<tabs>

<tab title="Gradle">

By default, the Gradle plugin will fetch and use the version of the JetBrains Runtime for the Development Instance corresponding to the version of the IntelliJ Platform used for building the plugin project.
If required, an alternative version can be specified using [`runIde.jbrVersion`](tools_gradle_intellij_plugin.md#runide-task-jbrversion) task property.

</tab>

<tab title="DevKit">

The [Run Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) for a DevKit-based plugin project controls the JDK used to run and debug a plugin project in a Development Instance.
The default Run Configuration uses the same JDK for building the plugin project and running the plugin in a Development Instance.

To change the runtime for the Development Instance, set the _JRE_ field in the Run Configuration edit dialog to download a JetBrains Runtime.

</tab>
</tabs>

## Enabling Auto-Reload

Starting in 2020.1, this is available for compatible [dynamic plugins](dynamic_plugins.md).
This allows a much faster development cycle by avoiding a full restart of the development instance after detecting code changes (when JARs are modified).

Please note that any unloading problems in a production environment will ask the user to restart the IDE.

> Auto-Reload does not work when the sandbox IDE instance is running under a debugger.
>
{type="warning"}

<tabs>

<tab title="Gradle">

Enabled by default for target platform 2020.2 or later.

Set property `intellij.autoReloadPlugins` in [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task to `true` for enabling it in earlier platform versions or `false` to disable it explicitly, see [](tools_gradle_intellij_plugin_faq.md#how-to-disable-automatic-reload-of-dynamic-plugins).

After starting the sandbox IDE instance, run [`buildPlugin`](tools_gradle_intellij_plugin.md#buildplugin-task) task after modifications in the plugin project and switch focus back to sandbox instance to trigger reload.

> [`buildSearchableOptions`](tools_gradle_intellij_plugin.md#buildsearchableoptions-task) task must currently be [disabled explicitly](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options) to workaround _Only one instance of IDEA can be run at a time_ problem.
>
{type="warning"}

</tab>

<tab title="DevKit">

Add system property `idea.auto.reload.plugins` in the Plugin DevKit [run configuration](running_and_debugging_a_plugin.md).

To disable auto-reload, set `idea.auto.reload.plugins` to `false` explicitly (2020.1.2+).

</tab>

</tabs>

## The Development Instance Sandbox Directory
The _Sandbox Home_ directory contains the [settings, caches, logs, and plugins](#development-instance-settings-caches-logs-and-plugins) for a Development Instance of the IDE.
This information is stored in a different location than for the [installed IDE itself](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs).

<tabs>
<tab title="Gradle">

For Gradle-based plugins, the default Sandbox Home location is defined by the [Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md).
See [Configuring a Gradle Plugin Project](gradle_prerequisites.md) for more information about specifying a Sandbox Home location.

The default Sandbox Home location is:
* Windows: <path>$PROJECT_DIRECTORY$\build\idea-sandbox</path>
* Linux/macOS: <path>$PROJECT_DIRECTORY$/build/idea-sandbox</path>

</tab>

<tab title="DevKit">

For DevKit-based plugins, the default Sandbox Home location is defined in the IntelliJ Platform Plugin SDK.
See specifying the [Sandbox Home for DevKit Projects](setting_up_environment.md) for more information.

The default Sandbox Home directory location is:
* Windows: `<User home>\.<product_system_name><product_version>\system\plugins-sandbox\`
* Linux: `~/.<product_system_name><product_version>/system/plugins-sandbox/`
* macOS: `~/Library/Caches/<product_system_name><product_version>/plugins-sandbox/`

</tab>
</tabs>

### Development Instance Settings, Caches, Logs, and Plugins
Within the Sandbox Home directory are subdirectories of the Development Instance:
* <path>config</path> contains settings for the IDE instance.
* <path>plugins</path> contains folders for each plugin being run in the IDE instance.
* <path>system/caches</path> or <path>system\caches</path> holds the IDE instance data.
* <path>system/log</path> or <path>system\log</path> contains the <path>idea.log</path> file for the IDE instance.

Each of these Sandbox Home subdirectories can be manually cleared to reset the IDE Development Instance.
At the next launch of a Development Instance, the subdirectories will be repopulated with the appropriate information.
