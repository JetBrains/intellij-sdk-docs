---
title: IDE Development Instances - Settings, Caches, Logs, and Plugins
---

A JetBrains feature for developing plugins is to run or debug a plugin project from within a JetBrains IDE, e.g. IntelliJ IDEA. 
[Selecting the **Run** menu](https://www.jetbrains.com/help/idea/running-and-debugging-plugins.html) will launch 
a _Development Instance_ of the IDE with your plugin enabled. 

The **Sandbox Home** directory contains the
[settings, caches, logs, and plugins](#development-instance-settings-caches-logs-and-plugins)
for a Development Instance of the IDE. This information is stored in a different location than for the
[installed IDE itself](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs).

### Sandbox Home Location for Gradle-Based Plugin Projects
For Gradle-based plugins, the default **Sandbox Home** location is defined by the IntelliJ Platform `gradle-intellij-plugin`. 
See [Configuring a Gradle Plugin Project](/tutorials/build_system/prerequisites.md)
for more information about specifying a **Sandbox Home** location. The default **Sandbox Home** location
for Gradle-based plugin projects is:
* **Windows** `<Project Dir>\build\idea-sandbox`
* **Linux or Macos** `<Project Dir>/build/idea-sandbox`

### Sandbox Home Location for DevKit-Based Plugin Projects
For DevKit-based plugins, the default **Sandbox Home** location is defined in the IntelliJ Platform Plugin SDK. 
See specifying the [Sandbox Home for DevKit Projects](/basics/getting_started/setting_up_environment.md) for more information.
The default **Sandbox Home** directory location for DevKit-based plugin projects is:  
* **Windows:** `<User home>\.<product_system_name><product_version>\system\plugins-sandbox\`
* **Linux:** `~/.<product_system_name><product_version>/system/plugins-sandbox/`
* **Macos** `~/Library/Caches/<product_system_name><product_version>/plugins-sandbox/`

### Development Instance Settings, Caches, Logs, and Plugins
Within the **Sandbox Home** directory are subdirectories pertaining to the Development Instance:
* `config` contains settings for the IDE instance.
* `plugins` contains folders for each plugin being run in the IDE instance. 
* `system/caches` or `system\caches` holds the IDE instance data.
* `system/log` or `system\log` contains the `idea.log` file for the IDE instance.

Each of these **Sandbox Home** subdirectories can be manually cleared to reset the IDE Development Instance. 
At the next launch of a Development Instance the subdirectories will be repopulated with the appropriate information.

