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


All products based on the _IntelliJ Platform_, such as IntelliJ IDEA, RubyMine, WebStorm, and so on, target the same underlying platform API.
Some of these products share features built on top of the platform, such as Java support (IntelliJ IDEA and Android Studio) or database support (IntelliJ IDEA Ultimate, DataGrip, PhpStorm, etc.).
Underlying those shared features are shared components.
When authoring a plugin for the IntelliJ Platform, it is important to understand and declare dependencies on these components.
Otherwise, it may not be possible to load or run the plugin in a product because the underlying components on which it depends aren't available.

## Declaring Plugin Dependencies
For the purposes of dependencies, a _module_ can be thought of like a built-in plugin that ships as a non-removable part of a product.
A working definition of a dependency is that a plugin project cannot be built without the underlying library or plugin corresponding to a module, and cannot run without the module present in an IntelliJ Platform-based product.
Declaring a dependency on a module also expresses a plugin's compatibility with a product in that the IntelliJ Platform determines whether a product contains the correct modules to support a plugin before loading it. 

Part I of this document describes the syntax for declaring [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md) and [Optional Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md#optional-plugin-dependencies).  

Note:
* If a plugin does not include any module dependency tags in its `plugin.xml` file, it's assumed to be a legacy plugin and is loaded only in IntelliJ IDEA.
  This form of dependency "non-declaration" is deprecated; do not use it for new plugin projects. 
* If the `plugin.xml` file includes one or more dependency tags, the plugin is loaded by the IntelliJ Platform if the product contains **all** of the modules on which the plugin depends.

## Modules
A _module_ represents a built-in plugin that is a non-removable part of a product.
Some modules are available in all products, and some modules are available only in some, or even just one product.
This section identifies and discusses modules of both types.

### Modules Available in All Products
A core set of modules are available in all products based on the IntelliJ Platform.
These modules provide a set of shared functionality.
The following table lists modules that are currently available in all products. 
Module FQN is listed on the left, an overview of functionality is listed in the middle column, and availability in products is listed on the right:

| Plugin Dependency:<br>Module or Built-in Plugin  | Functionality  | IntelliJ Platform-Based Products  | 
|----------------------------|----------------|----------|
| `com.intellij.modules.platform`  | Messaging, UI Themes, UI Components, Files, Documents, Actions, Components, Services, Extensions, Editors  | All  |
| `com.intellij.modules.lang`  | File Type, Lexer, Parser, Highlighting, References, Code Completion, Find, Rename, Formatter, Code Navigation  | All  |
| `com.intellij.modules.xml`  | XML, XML DOM, XSD/DTD, DOM Model  | All  |
| `com.intellij.modules.vcs`  | VCS Revision Numbers, Content Revision, File Status, Change List, Change Provider  | All  |
| `com.intellij.modules.xdebugger`  | Debug Session, Stack Frames, Break Points, Source Positions, Memory Views, Tracked Instances | All  |

For example, if a plugin is dependent only on one or more of the modules in the table above, and declares the module dependencies in `plugin.xml`, it will work in any product based on the IntelliJ Platform.

### Modules Specific to Functionality
More specialized functionality is also delivered via modules in IntelliJ Platform-based products.
For example, the `com.intellij.modules.java` module supports the Java language-specific functionality.
If a plugin uses functionality from this module, such as Java-specific inspections and refactoring, it must declare a dependency on this module.

Note that not all products define and declare modules. 
For example, PhpStorm does not have its own module, but the product itself depends on (and ships with) the PHP language plugin. 
You can make a plugin compatible with PHP functionality by also depending on the same language plugin.

The following table lists modules or built-in plugins that provide specific functionality, and the products that currently ship with them.
Module FQN is listed on the left, an overview of functionality is listed in the middle column, and availability in products is listed on the right:

| Plugin Dependency:<br>Module or Built-in Plugin  | Functionality<br>Supports  | IntelliJ Platform-Based Products  | 
|----------------------------|----------------|----------------------------|----------------|
| `com.intellij.modules.java`          | **Java** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework  | IntelliJ IDEA, Android Studio                                   |
| `com.intellij.modules.ultimate`      | JavaScript and TypeScript language PSI Models, Java EE, JVM Frameworks: Spring, Play, Grails...  | IntelliJ IDEA Ultimate Edition                                |
| `com.intellij.modules.androidstudio` | Android SDK Platform, Build Tools, Platform Tools, SDK Tools | Android Studio                                                                                    |
| `com.intellij.modules.appcode`       | CocoaPods, Core Data Objects, Device & Simulator Support, Testing with REST  | AppCode                                                                           |
| `com.intellij.modules.cidr.lang`     | **C, C++, Objective-C/C++ and Swift** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework  | AppCode, CLion                     |
| `com.intellij.modules.cidr.debugger` | Debugger Watches, Evaluations, Breakpoints, Inline Debugging  | AppCode, CLion, RubyMotion                                                                       |
| `com.intellij.modules.clion`         | CMake, Profiler, Embedded Development, Remote Development, Remote Debug, Disassembly | CLion                                                                     |
| `com.intellij.modules.database`      | **SQL** language PSI Model, Inspections, Completion, Refactoring, Queries | IntelliJ IDEA Ultimate Edition, DataGrip, GoLand, PhpStorm, PyCharm, Rider, RubyMine |
| `com.intellij.modules.go`            | **Go** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework  | GoLand                                                            |
| `com.intellij.modules.python`        | **Python** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework  | PyCharm                                                       |
| `com.intellij.modules.rider`         | ReSharper Platform: **.NET** Analysis, Completion, Refactoring, Navigation, Testing   | Rider                                                                    |
| `com.intellij.modules.ruby`          | **Ruby** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework  | RubyMine                                                        |
| `com.jetbrains.php`                  | **PHP** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework | PhpStorm (built-in plugin)                                        |
| `com.intellij.modules.webstorm`      | **Web** languages PSI Models, Inspections, Intentions, Completion, Refactoring, Test Framework  | WebStorm                                                       |


## Verifying Dependency
Before marking a plugin as dependent only on modules in a target product, verify the plugin isn't implicitly dependent on any APIs that are specific to IntelliJ IDEA.
For example, ensure a plugin isn't dependent on the Java language module `com.intellij.modules.java` if the target product doesn't support it. 
To verify, create a test SDK pointing to an installation of the intended target IntelliJ Platform-based product, e.g., PhpStorm, PyCharm, etc.
* More specific information about developing for some IntelliJ Platform-based products is given in Part VIII of this document.
* For Gradle projects the default configuration is per the example [Plugins Targeting Alternate IDEs](https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/gradle_guide.html#plugins-targeting-alternate-intellij-platform-based-ides).
* For DevKit projects the default configuration is per the example [Configuring IntelliJ Platform SDK](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/setting_up_environment.html#configuring-intellij-platform-sdk), except:
  * Specify the installation folder of the IntelliJ Platform-based product, e.g., PyCharm, etc., instead of an installation of IntelliJ IDEA.
  * Skip adding the source code directory.  
* Compile your plugin against that test SDK, and verify there are no errors related to missing IntelliJ IDEA-related classes. 

The [JetBrains plugin repository](https://plugins.jetbrains.com/) automatically detects the products with which a plugin is compatible, based on the rules above, and makes it available to users of those products.

Visit the [Open Source Licensing page](https://www.jetbrains.com/buy/opensource/) to check if your project is eligible for free Open Source Licenses of JetBrains products.

## Platform Compatibility
The API of _IntelliJ Platform_ and bundled plugins may change between releases. 
The significant changes that may break plugins are listed on [Incompatible Changes in IntelliJ Platform and Plugins API](/reference_guide/api_changes_list.md) page.

To be sure a plugin will work on the targeted platform, use the same development version of IntelliJ platform as the one being targeted. 
