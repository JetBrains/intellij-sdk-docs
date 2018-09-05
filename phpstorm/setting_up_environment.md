---
title: Setting-up the Environment for PhpStorm Plugin Development
---

### General information
Please review the information in the [Getting Started with Plugin Development](/basics/getting_started.md)
section of this guide.

PhpStorm plugin development is done in Java using IntelliJ IDEA. However, a plugin can be developed to target either
IntelliJ IDEA or PhpStorm. The choice dictates how the plugin project SDK is configured:
1. Target IntelliJ IDEA Ultimate with the PHP plugin installed: base the plugin project SDK on the installed version of IntelliJ IDEA.
2. Target PhpStorm: base the plugin project SDK on the installed version of PhpStorm.

Choosing an installed product as the basis for a project is described on 
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
