---
title: PhpStorm Plugin Development
redirect_from:
  - /phpstorm/phpstorm.html
  - /phpstorm/setting_up_environment.html
  - /products/phpstorm/setting_up_environment.html
---

## Introduction
PhpStorm is an IntelliJ Platform-based product. 
Plugins for PhpStorm are developed in Java using the Ultimate edition of IntelliJ IDEA. 
The [PsiViewer plugin](https://plugins.jetbrains.com/plugin/227-psiviewer) is also recommended.

This page describes [Configuring Plugin Projects Targeting PhpStorm](#configuring-plugin-projects-targeting-phpstorm).
See also:
* [Working with the PHP Open API](php_open_api.md)
* [Example Third Party PhpStorm plugins](existing_plugins.md)

## Configuring Plugin Projects Targeting PhpStorm
The IntelliJ IDEA Ultimate Edition (with the PHP plugin) must be used for developing PhpStorm plugins because the PHP plugin is incompatible with IntelliJ IDEA Community Edition.
However, this IntelliJ IDEA Ultimate configuration runs the risk of accidentally using some APIs that are not available in PhpStorm.
The recommended best practice is to use PhpStorm for testing.

> **Note** The OpenAPI is available for PhpStorm 6 and above.

### Configuring build.gradle for PhpStorm Plugin Projects using Gradle
Configuration of a Gradle-based PhpStorm plugin project is used as a tutorial in the section [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](/products/dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute).
Many important details are provided, such as choosing a version of IntelliJ IDEA Ultimate given a targeted version of PhpStorm.
The table below summarizes the `gradle-intellij-plugin` attributes to set in the `build.gradle` file for a PhpStorm plugin project:

| `gradle-intellij-plugin` Attribute | <br>Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate.  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the same `IU` BRANCH.BUILD as the PhpStorm target version, e.g. `192.7142.36` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `com.jetbrains.php:192.6603.42` for the PHP plugin.<br>See below for PHP plugin version information. |
| [`runIde.ideaDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of PhpStorm. For example, on macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/PhpStorm/ch-0/192.7142.51/PhpStorm.app/Contents` |

The required `org.jetbrains.plugins.go` plugin isn't compatible with IntelliJ IDEA Community edition but is compatible with IntelliJ IDEA Ultimate (`IU`) edition.
Product compatibility is determined from the PHP plugin [version page](https://plugins.jetbrains.com/plugin/6610-php/versions). 
The PHP plugin isn't bundled with `IU`, so the PHP plugin version must be explicitly declared to support the target PhpStorm (and `IU`) BRANCH.BUILD version. 
The correct PHP plugin version is also determined from the PHP plugin version page.

Add the [dependencies](#declaring-dependencies-in-pluginxml) to the plugin project's `plugin.xml` file

### Configuring PhpStorm Plugin Projects using DevKit
If you are using a DevKit workflow to develop plugins for PhpStorm, the recommended approach is to base the plugin project SDK on a local installation of PhpStorm. 
An alternative approach is to base the plugin project SDK on a local installation of IntelliJ IDEA Ultimate with the PHP plugin.

The [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md) page describes adding dependencies to the
DevKit development environments. 
Follow the instructions to add the PHP OpenAPI classes to the classpath of your plugin:
* Add the `php-openapi.jar` and `php.jar` libraries to the classpath of your project's SDK.  
  These libraries are located in `<your_installation_of_PhpStorm>/plugins/php/lib` directory.
* Add the [dependencies](#declaring-dependencies-in-pluginxml) to the plugin project's `plugin.xml` file

### Declaring Dependencies in plugin.xml
The dependency on the PHP plugin APIs must be declared in the `plugin.xml` file as per the tutorial in the [Configuring plugin.xml](/products/dev_alternate_products.html#configuring-pluginxml) section.
