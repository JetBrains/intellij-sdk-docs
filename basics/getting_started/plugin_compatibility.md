---
title: Plugin Compatibility with IntelliJ Platform Products
---
<style>
  table {
    width:100%;
  }
  th:first-child, td:first-child {
    width: 30%;
  }
  th:last-child, td:last-child {
    width: 30%;
  }
</style>

<!--TODO link to sample_plugin file-->

All products based on the _IntelliJ Platform_, such as IntelliJ IDEA, RubyMine, WebStorm, and so on, target the same underlying platform API, with some products sharing features built on top of the platform, such as Java support (IntelliJ IDEA
and Android Studio) or database support (IntelliJ IDEA Ultimate, DataGrip, PhpStorm, etc.).

It is possible to write a plugin that is compatible across multiple products, as long as the plugin specifies which product it is compatible with. More specifically, the plugin must specify, using [the `<depends>` tag](/basics/plugin_structure/plugin_dependencies.md) in the [`plugin.xml` file](/basics/plugin_structure/plugin_configuration_file.md), dependencies on well known _modules_ or plugins.

For the purposes of dependencies, a module can be thought of as a built-in plugin that ships as a non-removable part of a product. Not all products define and declare modules. For example, PhpStorm does not have its own module, but the product itself depends on (and ships) the PHP language plugin. You can make your plugin compatible with PhpStorm by also depending on the same language plugin.

For example:

```xml
<idea-plugin>
  ...
  <depends>com.intellij.modules.lang</depends>
  ...
</idea-plugin>
```

<!--TODO link to sample_plugin file--> 

If a plugin does not include any module dependency tags in its `plugin.xml`, it's assumed to be a legacy plugin and is loaded only in IntelliJ IDEA. 

If the `plugin.xml` includes one or more such tags, the plugin is loaded if the product contains **all** of the modules on which the plugin depends.

The following modules are currently available in all products based on IntelliJ Platform:

| Module or built-in plugin  | Functionality  | Product  | 
|----------------------------|----------------|----------|
| `com.intellij.modules.platform`  | (SDK Part II) Messaging, UI Themes, UI Components, Files, Documents, Actions, Components, Services, Extensions, Editors  | All  |
| `com.intellij.modules.lang`  | (SDK Part VII) File Type, Lexer, Parser, Highlighting, References, Code Completion, Find, Rename, Formatter, Go to Class/Symbol  | All  |
| `com.intellij.modules.xml`  | Working with XML DOM, Editing, Custom Models, Attributes, Dynamic DOM Definition, XSD/DTD  | All  |
| `com.intellij.modules.vcs`  | TBD  | All  |
| `com.intellij.modules.xdebugger`  | TBD  | All  |

This means a plugin can declare a dependency on `com.intellij.modules.vcs` and it will work in any product that supports version control, and since all products currently include the `com.intellij.modules.vcs` module, this plugin will work in all products.

The following modules or built-in plugins are available in these specific products:

| Module or built-in plugin  | Functionality  | Product  | 
|----------------------------|----------------|----------|
| `com.intellij.modules.java`  | Java PSI model, Inspections, Intentions, Testing  | IntelliJ IDEA, Android Studio                   |
| `com.intellij.modules.ultimate`      | TBD  | IntelliJ IDEA Ultimate Edition                                                       |
| `com.intellij.modules.androidstudio` | TBD  | Android Studio                                                                       |
| `com.intellij.modules.appcode`       | TBD  | AppCode                                                                              |
| `com.intellij.modules.cidr.lang`     | TBD  | AppCode, CLion                                                                       |
| `com.intellij.modules.cidr.debugger` | TBD  | AppCode, CLion, RubyMotion                                                           |
| `com.intellij.modules.clion`         | TBD  | CLion                                                                                |
| `com.intellij.modules.database`      | TBD  | IntelliJ IDEA Ultimate Edition, DataGrip, GoLand, PhpStorm, PyCharm, Rider, RubyMine  |
| `com.intellij.modules.go`            | TBD  | GoLand                                                                               |
| `com.intellij.modules.python`        | TBD  | PyCharm                                                                              |
| `com.intellij.modules.rider`         | TBD  | Rider                                                                                |
| `com.intellij.modules.ruby`          | TBD  | RubyMine                                                                             |
| `com.jetbrains.php`                  | TBD  | PhpStorm (built-in plugin)                                                           |
| `com.intellij.modules.webstorm`      | TBD  | WebStorm                                                                             |

## Plugin dependencies

If you want to add a dependency to a plugin, then add a `<depends>` tag using the plugin name/ID to `plugin.xml`. For example `JavaScript` or `tslint`. Note that some plugins are not included by default in the target SDK, so you also have to add their jars manually to the SDKs classpath to compile against their provided classes. Make sure that you add the plugin jars to the SDK and not to your plugin, else the jars will be bundled with your plugin.

## Optional dependencies

You can also specify optional dependencies. If your plugin works with all products but provides some Java-specific functionality, you can use a dependency tag like this:

```xml
<depends optional="true" config-file="my-java-features.xml">
  com.intellij.modules.java
</depends>
```

Before marking a plugin as compatible with all products, you should verify that it doesn't use any APIs that are specific to IntelliJ IDEA. To do so, create an _IntelliJ Platform_ SDK pointing to an installation of RubyMine, PyCharm, etc., compile your plugin against that SDK, and verify that everything compiles. Visit the [Open Source Licensing page](https://www.jetbrains.com/buy/opensource/) to check if your project is eligible for free Open Source Licenses of JetBrains products.

The [JetBrains plugin repository](https://plugins.jetbrains.com/) automatically detects the products with which a plugin is compatible, based on the rules above, and makes it available to users of those products.

## Platform Compatibility

The API of _IntelliJ Platform_ and bundled plugins may be changed between releases. The major changes which may break plugins are listed on [Incompatible Changes in IntelliJ Platform and Plugins API](/reference_guide/api_changes_list.md) page.

To be sure that your plugin will work on your targeted platform, you should use the same development version of IntelliJ platform as the one you are targeting. 
