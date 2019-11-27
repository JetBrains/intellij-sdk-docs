---
title: GoLand Plugin Development
---

## Introduction
GoLand is an IntelliJ Platform-based product.
Plugin projects for GoLand can be developed using IntelliJ IDEA with the `gradle-intellij-plugin`.

## Configuring Plugin Projects Targeting GoLand
The configuration of GoLand plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the `gradle-intellij-plugin` attributes to set in the `build.gradle` file for a GoLand plugin project:

| `gradle-intellij-plugin`<br>Attribute| <br>Attribute Value |
|-----------|-------|
| [`intellij.type`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `IU` for IntelliJ IDEA Ultimate.  |
| [`intellij.version`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | Set to the same `IU` BRANCH.BUILD as the GoLand target version, e.g. `192.7142.36` |
| [`intellij.plugins`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#intellij-platform-properties) | `org.jetbrains.plugins.go:192.6603.23.335` for the Go plugin.<br>See below for Go plugin version information. |
| [`runIde.ideaDirectory`](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#running-dsl) | Path to locally installed target version of GoLand. For example, on macOS:<br>`/Users/<user name>/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/192.7142.48/GoLand.app/Contents` |

The required `org.jetbrains.plugins.go` plugin isn't compatible with IntelliJ IDEA Community edition but is compatible with IntelliJ IDEA Ultimate (`IU`) edition.
Product compatibility is determined from the Go plugin [version page](http://plugins.jetbrains.com/plugin/9568-go/versions). 
The Go plugin isn't bundled with `IU`, so the Go plugin version must be explicitly declared and support the target GoLand (and `IU`) BRANCH.BUILD version. 
The correct Go plugin version is also determined from the Go plugin version page.

The dependency on the Go plugin APIs must be declared in the `plugin.xml` file.
As described in [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality) table, the `<depends>` tags must declare `com.intellij.modules.go`.
The plugin.xml file must also declare a dependency on `com.intellij.modules.platform` as explained in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml). 
The dependency declaration is illustrated in the `plugin.xml` snippet below:
```xml
  <!-- Requires the Go plugin -->
  <depends>org.jetbrains.plugins.go</depends>
  <!-- Requires the platform module to distinguish it from a legacy plugin -->
  <depends>com.intellij.modules.platform</depends>
```
## Available GoLand APIs
Use the [Exploring APIs as a Consumer](/basics/getting_started/plugin_compatibility.html#exploring-apis-as-a-consumer) process to identify the library `intellij-go-<version>.jar`, where `<version>` in the JAR file name corresponds to the version of the Go plugin.
For the `intellij.plugins` example attribute in the above table, the corresponding library would be `intellij-go-192.6603.23.335.jar`.
Test your plugin with any version of GoLand you intend to support.

## Open Source Plugins for GoLand
When learning new APIs, it is helpful to have some representative projects for reference:
* [Go Method Generator](https://github.com/pkondratev/Intellij-go-method-generator)
* [Go Builder Generator](https://github.com/OddCN/go-builder-generator-idea-plugin)
