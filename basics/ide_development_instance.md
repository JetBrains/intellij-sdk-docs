---
title: IDE Development Instances
redirect_from:
  - /basics/settings_caches_logs.html
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A JetBrains feature for developing plugins is running or debugging a plugin project from within an IntelliJ Platform-based IDE such as IntelliJ IDEA. 
Selecting the [**runIde**](/tutorials/build_system/prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task for a Gradle-based project (or [**Run**](getting_started/running_and_debugging_a_plugin.md) menu for a DevKit-based project) will launch a _Development Instance_ of the IDE with the plugin enabled.
This page describes how to control some of the settings for the Development Instance.
                                            
> **TIP** Please see also [Advanced Configuration](https://www.jetbrains.com/help/idea/tuning-the-ide.html) for general VM options and properties.

## Using a JetBrains Runtime for the Development Instance
A common use case is to develop (build) a plugin project against a JDK, e.g. Java 8, and then run or debug the plugin in a Development Instance of the IDE.
In such a situation, it is important the Development Instance uses a JetBrains Runtime (JBR) rather than the JDK used to build the plugin project.

The JetBrains Runtime is an environment for running IntelliJ Platform-based IDEs on Windows, macOS, and Linux. 
It has some modifications by JetBrains, such as fixes for native crashes not present in official JDK builds.
A version of the JetBrains Runtime is bundled with all IntelliJ Platform-based IDEs.
To produce accurate results while running or debugging a plugin project in a Development Instance, follow the procedures below to ensure the Development Instance uses a JetBrains Runtime.

### Determining a JetBrains Runtime Version
The JetBrains Runtime is determined from the JDK version used to build the plugin project.
If a plugin is being developed against the Java 8 SE Development Kit 8 for macOS, for example, `jdk-8u212-macosx-x64.dmg`.
(This example will use macOS, but Windows and Linux follow the same procedure.)
To acquire the compatible JetBrains Runtime:
* Go to the [JetBrains Runtime Site](https://confluence.jetbrains.com/display/JBR/JetBrains+Runtime) for general information and latest build
* Open [Release notes](https://confluence.jetbrains.com/display/JBR/Release+notes) page to access all releases.
* Select the package name corresponding to the platform and SDK version.
  In this case the package name is `jbrsdk8-osx-x64` for **j**et **b**rains **r**untime _sdk_ version 8, macOS x64 hardware.
* On the macOS package page of the JetBrains Bintray site, select the **Files** menu.
* In the list of files, find the name that satisfies:
  * The version and build number match the JDK used to build the plugin project.
    For example, `jbrx-8u252-osx-x64` matches the Java 8 JDK, build 252: `jdk-8u252-macosx-x64`.
  * Pick the highest JetBrains Runtime build number available.
    For example, as of this writing, the file is `jbrx-8u252-osx-x64-b1649.2.tar.gz`, meaning build 1649.2 for this JetBrains Runtime matching Java 8 JDK build 252.

### Setting a JetBrains Runtime for Gradle-Based Plugin Projects
By default, the Gradle plugin will fetch and use the version of the JetBrains Runtime for the Development Instance corresponding to the version of the IntelliJ Platform used for building the plugin project.
If required, an alternative version can be specified using `jbrVersion` attribute of `runIde` [task](https://github.com/JetBrains/gradle-intellij-plugin/#running-dsl).

### Setting a JetBrains Runtime for DevKit-Based Plugin Projects
The [Run Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) for a DevKit-based plugin project controls the JDK used to run and debug a plugin project in a Development Instance.
The default Run Configuration uses the same JDK for building the plugin project and running the plugin in a Development Instance.
To change the runtime for the Development Instance, set the _JRE_ field in the Run Configuration edit dialog to use a downloaded JetBrains Runtime.


## Enabling Auto-Reload
> **NOTE** Starting in 2020.1, this is available for compatible [dynamic plugins](/basics/plugin_structure/dynamic_plugins.md).

When adding system property `idea.auto.reload.plugins` in the [run configuration](getting_started/running_and_debugging_a_plugin.md) (DevKit-based) or [**runIde**](/tutorials/build_system/prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task (Gradle-based), dynamic plugins are reloaded automatically when their JARs are modified.
This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.
For [Gradle-based plugins](/tutorials/build_system/prerequisites.md) using `gradle-intellij-plugin` 0.4.17 or later, this property is set automatically.

To disable auto-reload, set `idea.auto.reload.plugins` to `false` explicitly (2020.1.2+). Please note that any unloading problems in production environment will ask the user to restart the IDE.


## The Development Instance Sandbox Directory 
The _Sandbox Home_ directory contains the [settings, caches, logs, and plugins](#development-instance-settings-caches-logs-and-plugins) for a Development Instance of the IDE. 
This information is stored in a different location than for the [installed IDE itself](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs).

### Sandbox Home Location for Gradle-Based Plugin Projects
For Gradle-based plugins, the default Sandbox Home location is defined by the IntelliJ Platform `gradle-intellij-plugin`. 
See [Configuring a Gradle Plugin Project](/tutorials/build_system/prerequisites.md) for more information about specifying a Sandbox Home location. 
The default Sandbox Home location for Gradle-based plugin projects is:
* **Windows** `<Project Dir>\build\idea-sandbox`
* **Linux or macOS** `<Project Dir>/build/idea-sandbox`

### Sandbox Home Location for DevKit-Based Plugin Projects
For DevKit-based plugins, the default Sandbox Home location is defined in the IntelliJ Platform Plugin SDK. 
See specifying the [Sandbox Home for DevKit Projects](/basics/getting_started/setting_up_environment.md) for more information.
The default Sandbox Home directory location for DevKit-based plugin projects is:  
* **Windows:** `<User home>\.<product_system_name><product_version>\system\plugins-sandbox\`
* **Linux:** `~/.<product_system_name><product_version>/system/plugins-sandbox/`
* **macOS** `~/Library/Caches/<product_system_name><product_version>/plugins-sandbox/`

### Development Instance Settings, Caches, Logs, and Plugins
Within the Sandbox Home directory are subdirectories pertaining to the Development Instance:
* `config` contains settings for the IDE instance.
* `plugins` contains folders for each plugin being run in the IDE instance. 
* `system/caches` or `system\caches` holds the IDE instance data.
* `system/log` or `system\log` contains the `idea.log` file for the IDE instance.

Each of these Sandbox Home subdirectories can be manually cleared to reset the IDE Development Instance. 
At the next launch of a Development Instance, the subdirectories will be repopulated with the appropriate information.
