---
title: Development Instance Settings, Caches, Logs, and Plugins
---

A useful workflow for developing plugins is to run or debug a plugin project from within a JetBrains IDE, e.g. IntelliJ IDEA. 
[Selecting the **Run** menu](https://www.jetbrains.com/help/idea/running-and-debugging-plugins.html) will launch 
a _Development Instance_ of the IDE with your plugin enabled. 

This page describes where the settings, caches, logs, and plugins are located for the Development Instance of the IDE.
This information is stored in a different location than for the
[installed IDE itself](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs).
Please review the documentation for the installed IDE to understand conventions about product names and versions.
These conventions are used below.

### Directory Locations for Development Instances of the IDE -  DevKit-Based Plugins
Development Instance configuration data is stored in the following directories:
* **Windows:** `<User home>\.<product_system_name><product_version>\system\plugins-sandbox\`
* **Linux:** `~/.<product_system_name><product_version>/system/plugins-sandbox/`
* **Macos** `~/Library/Caches/<product_system_name><product_version>/plugins-sandbox/`

Within those directories are folders pertaining to the Development Instance:
* `config` contains settings for the IDE instance.
* `plugins` contains folders for each plugin being run in the IDE instance. 
* `system/caches or system\caches` caches the IDE instance data.
* `system/log or system\log` contains the `idea.log` file for the IDE instance.

### Directory Locations for Development Instances of the IDE -  Gradle-Based Plugins
_**This is just Placeholder Content**_

Development Instance configuration data is stored in the following directories:
* **Windows:** `<User home>\.<product_system_name><product_version>\system\plugins-sandbox\`
* **Linux:** `~/.<product_system_name><product_version>/system/plugins-sandbox/`
* **Macos** `~/Library/Caches/<product_system_name><product_version>/plugins-sandbox/`

Within those directories are folders pertaining to the Development Instance:
* `config` contains settings for the IDE instance.
* `plugins` contains folders for each plugin being run in the IDE instance. 
* `system/caches or system\caches` caches the IDE instance data.
* `system/log or system\log` contains the `idea.log` file for the IDE instance.

