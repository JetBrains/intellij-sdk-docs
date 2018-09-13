---
title: Setting-up the Environment for PhpStorm Plugin Development
---

### General information
Please familiarize yourself with the [Getting Started with Plugin Development](/basics/getting_started.md)
section of this guide.

If you are using a [DevKit](/basics/getting_started/using_dev_kit.md)
workflow to develop plugins for PhpStorm, the recommended approach is to base the plugin project SDK on an installation
of PhpStorm. An alternative approach is to base the plugin project SDK on an installation of IntelliJ IDEA Ultimate with the PHP plugin.
However, this runs the risk of accidentally using some APIs which are not available in PhpStorm.

### How to use the PhpStorm OpenAPI Library for Plugin Development

> **Note** The OpenAPI is available for PhpStorm 6 and above.

The [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md) page describes adding dependencies to the
DevKit as well as Gradle development environments. Follow the instructions to:

* Add the PHP OpenAPI classes to the classpath of your plugin:
  * For Gradle-based plugin development, add the `com.jetbrains.php` plugin ID to your build.gradle file. 
  * For DevKit-based plugin development, add the `php-openapi.jar` and `php.jar` libraries to the classpath of your project's SDK.  
    These libraries are located in `<your_installation_of_PhpStorm>/plugins/php/lib` directory.

* Add the `com.jetbrains.php` and `com.intellij.modules.platform` dependencies
  to the your plugin project's `plugin.xml` file.
