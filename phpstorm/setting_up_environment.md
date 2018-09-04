---
title: Setting-up the Environment for PhpStorm Plugin Development
---

### General information
Please review the information in the [Getting Started with Plugin Development](/basics/getting_started.md)
section of this guide.

There are two ways to develop plugins for PhpStorm:
1. Use IntelliJ IDEA as the IDE with the PHP plugin installed.
2. Use PhpStorm as the IDE.

The choice affects how you will configure an SDK for developing a plugin. Using IntelliJ IDEA as the IDE requires basing the
SDK for your project on your installed version of IntelliJ IDEA. Using PhpStorm as the IDE requires basing the SDK for
your project on your installed version of PhpStorm. Choosing an installed IDE as the basis for a project is described on 
the [Setting Up a Development Environment](/basics/getting_started/setting_up_environment.md) page.

> **Note** IntelliJ IDEA Community Edition does not support the PHP plugin. The IDE for developing a PhpStorm plugin can be IntelliJ IDEA Community Edition, but the IDE used for testing must be IntelliJ IDEA Ultimate.

### How to use the PhpStorm OpenAPI Library for Plugin Development

> OpenAPI is available for PhpStorm 6 and above.

The [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md) instructions cover adding dependencies to the
DevKit as well as Gradle development environments. Follow the instructions to:

* Add the PHP OpenAPI classes to the classpath of your plugin:
  * For Gradle-based plugin development, add the `com.jetbrains.php` plugin ID to your build.gradle file. 
  * For DevKit-based plugin development, add the `php-openapi.jar` and `php.jar` libraries to the classpath of your project's SDK.  
    These libraries are located in `<your_installation_of_PhpStorm>/plugins/php/lib` directory.

* Add the `com.jetbrains.php` and `com.intellij.modules.platform` dependencies
  to the your plugin project's `plugin.xml` file.
