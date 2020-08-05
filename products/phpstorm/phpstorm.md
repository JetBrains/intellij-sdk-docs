---
title: PhpStorm Plugin Development
redirect_from:
  - /phpstorm/phpstorm.html
  - /phpstorm/setting_up_environment.html
  - /products/phpstorm/setting_up_environment.html
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
[PhpStorm](https://www.jetbrains.com/phpstorm/) is an IntelliJ Platform-based product. 
Plugins for PhpStorm are developed in Java using the Ultimate edition of IntelliJ IDEA. 
The [PsiViewer plugin](https://plugins.jetbrains.com/plugin/227-psiviewer) is also recommended.

This page describes configuring plugin projects targeting PhpStorm.
See also:
* [Working with the PHP Open API](php_open_api.md)
* [Example Third Party PhpStorm plugins](existing_plugins.md)

## Configuring Plugin Projects Targeting PhpStorm
The IntelliJ IDEA Ultimate Edition (with the PHP plugin) must be used for developing PhpStorm plugins because the PHP plugin is incompatible with IntelliJ IDEA Community Edition.
However, this IntelliJ IDEA Ultimate configuration runs the risk of accidentally using some APIs that are not available in PhpStorm.
The recommended best practice is to use PhpStorm for testing.

> **NOTE** The OpenAPI is available for PhpStorm 6 and above.

Configuration of a Gradle-based PhpStorm plugin project is used as a tutorial in the section [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](/products/dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute).
Many techniques are discussed, such as choosing a version of IntelliJ IDEA Ultimate given a targeted version of PhpStorm.
The table below summarizes the `gradle-intellij-plugin` attributes to set in the `build.gradle` file for a PhpStorm plugin project:

The table below summarizes the `gradle-intellij-plugin` attributes to set in the plugin project's `build.gradle` file.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in the `build.gradle` file for PhpStorm, see [Configuring build.gradle using the IntelliJ IDEA Product Attribute](/products/dev_alternate_products.md#configuring-buildgradle-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute | <br>Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate. (The required PHP plugin isn't compatible with IntelliJ IDEA Community Edition.) |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the same `IU` BRANCH.BUILD as the PhpStorm target version, e.g. `193.5233.102` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `com.jetbrains.php:193.5233.102` for the PHP plugin.<br>See below for PHP plugin version information. |
| [`runIde.ideDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of PhpStorm. For example, on macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/PhpStorm/ch-0/193.5233.101/PhpStorm.app/Contents` |

The version of the PHP plugin is explicitly declared because it isn't bundled with IntelliJ IDEA Ultimate Edition. 
Select a [version](https://plugins.jetbrains.com/plugin/6610-php/versions) of the PHP plugin that is compatible with the `intellij.version`.

The dependency on the PHP plugin APIs must be declared in the `plugin.xml` file, as shown in the tutorial [Configuring plugin.xml](/products/dev_alternate_products.md#configuring-pluginxml) section.
