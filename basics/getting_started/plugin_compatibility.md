---
title: Plugin Compatibility with IntelliJ Platform Products
---

<!--TODO link to sample_plugin file-->

All products based on the _IntelliJ Platform_, such as IntelliJ IDEA, RubyMine, WebStorm, and so on, target the same underlying platform API, with some products sharing features built on top of the platform, such as Java support (IntelliJ IDEA
and Android Studio) or database support (IntelliJ IDEA Ultimate, DataGrip, PhpStorm, etc.).

It is possible to write a plugin that is compatible across multiple products, as long as the plugin specifies which product it is compatible with. More specifically, the plugin must specify, using [the `<depends>` tag](http://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_dependencies.html) in the [`plugin.xml` file](http://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html), dependencies on well known _modules_ or plugins.

For the purposes of dependencies, a module can be thought of as a built-in plugin that ships as a non-removable part of a product. Not all products define and declare modules. For example, PhpStorm and GoLand do not have their own modules, but the products themselves depend on (and ship) the language specific plugins. You can make your plugin compatible with these products by also depending on the same language plugins.

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

* `com.intellij.modules.platform`
* `com.intellij.modules.lang`
* `com.intellij.modules.vcs`
* `com.intellij.modules.xml`
* `com.intellij.modules.xdebugger`

This means a plugin can declare a dependency on `com.intellij.modules.vcs` and it will work in any product that supports version control, and since all products currently include the `com.intellij.modules.vcs` module, this plugin will work in all products.

The following modules or built-in plugins are available in these specific products:

| Module or built-in plugin            | Product                                                               |
|--------------------------------------|-----------------------------------------------------------------------|
| `com.intellij.modules.java`          | IntelliJ IDEA, Android Studio                                         |
| `com.intellij.modules.ultimate`      | IntelliJ IDEA Ultimate Edition                                        |
| `com.intellij.modules.androidstudio` | Android Studio                                                        |
| `com.intellij.modules.appcode`       | AppCode                                                               |
| `com.intellij.modules.cidr.lang`     | AppCode, CLion                                                        |
| `com.intellij.modules.cidr.debugger` | AppCode, CLion, RubyMotion                                            |
| `com.intellij.modules.clion`         | CLion                                                                 |
| `com.intellij.modules.database`      | IntelliJ IDEA Ultimate Edition, DataGrip, PhpStorm, PyCharm, RubyMine |
| `com.intellij.modules.python`        | PyCharm                                                               |
| `com.intellij.modules.rider`         | Rider                                                                 |
| `com.intellij.modules.ruby`          | RubyMine                                                              |
| `com.jetbrains.php`                  | PhpStorm (built-in plugin)                                            |
| `org.jetbrains.plugins.go`           | GoLand (built-in plugin)                                              |

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

The [IntelliJ plugin repository](http://plugins.jetbrains.com/) automatically detects the products with which a plugin is compatible, based on the rules above, and makes it available to users of those products.

The API of _IntelliJ Platform_ and bundled plugins may be changed between releases. The major changes which may break plugins are listed on [Incompatible Changes in _IntelliJ Platform_ and Plugins API](/reference_guide/api_changes_list.md) page.
