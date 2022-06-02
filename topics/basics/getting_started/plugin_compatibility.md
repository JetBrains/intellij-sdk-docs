[//]: # (title: Plugin Compatibility with IntelliJ Platform Products)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction

All products based on the IntelliJ Platform are built on the same underlying API.
Some of these products share features built on top of the platform, such as Java support in IntelliJ IDEA and Android Studio.
Underlying those shared features are shared components.
When authoring a plugin for the IntelliJ Platform, it is important to understand and declare dependencies on these components.
Otherwise, it may not be possible to load or run the plugin in a product because the components on which it depends aren't available.

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## Declaring Plugin Dependencies

For the purposes of dependencies, a _module_ can be thought of as a built-in plugin that ships as a non-removable part of a product.
A working definition of a dependency is that a plugin project cannot be run without the module present in an IntelliJ Platform-based product.
Declaring a dependency on a module also expresses a plugin's compatibility with a product in that the IntelliJ Platform determines whether a product contains the correct modules to support a plugin before loading it.

[Part I](plugin_dependencies.md) of this document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
Part II of this document (below) describes the IntelliJ Platform modules' functionality to aid in determining the dependencies of a plugin.

The way dependency declarations are handled by the Intellij Platform is determined by the contents of the <path>plugin.xml</path> file:
* If a plugin does not declare any dependencies in its <path>plugin.xml</path> file, or if it declares dependencies only on other plugins but not modules, it's assumed to be a legacy plugin and is loaded only in IntelliJ IDEA.
  This configuration of the dependency declaration is deprecated; do not use it for new plugin projects.
* If a plugin declares at least _one_ module dependency in its <path>plugin.xml</path> file, the plugin is loaded if an IntelliJ Platform-based product contains _all the modules and plugins_ on which the plugin has declared a dependency.

## Modules

A _module_ represents a built-in plugin that is a non-removable part of a product.
Some modules are available in all products, and some modules are available only in some, or even just one product.
This section identifies and discusses modules of both types.

### Declaring Incompatibility with Module

Starting in 2020.2, a plugin can declare incompatibility with an arbitrary module by specifying `<incompatible-with>` containing module ID in its <path>plugin.xml</path>.

### Modules Available in All Products

A core set of modules is available in all products based on the IntelliJ Platform.
These modules provide a set of shared functionalities.
The following table lists modules that are currently available in all products.

> All plugins should declare a dependency on **`com.intellij.modules.platform`** to indicate dependence on shared functionality.
>
{type="note"}

| Module for `<depends>` Element<br/>Declaration in <path>plugin.xml</path> File | <br/>Functionality                                                                                            |
|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| **`com.intellij.modules.platform`**                                            | Messaging, UI Themes, UI Components, Files, Documents, Actions, Components, Services, Extensions, Editors     |
| `com.intellij.modules.lang`                                                    | File Type, Lexer, Parser, Highlighting, References, Code Completion, Find, Rename, Formatter, Code Navigation |
| `com.intellij.modules.xml`                                                     | XML, XML DOM, XSD/DTD, DOM Model                                                                              |
| `com.intellij.modules.vcs`                                                     | VCS Revision Numbers, File Status, Change Lists, File History, Annotations                                    |
| `com.intellij.modules.xdebugger`                                               | Debug Session, Stack Frames, Break Points, Source Positions, Memory Views, Tracked Instances                  |

As of this writing, if a plugin: **A)** is dependent _only_ on one or more of the modules in the table above, **and B)** declares those module dependencies in <path>plugin.xml</path>, then any product developed by JetBrains based on the IntelliJ Platform will load it.

### Modules Specific to Functionality

More specialized functionality is also delivered via modules and plugins in IntelliJ Platform-based products.
For example, the `com.intellij.modules.python` module supports the Python language-specific functionality.
If a plugin uses this module's functionality, such as Python-specific inspections and refactoring, it must declare a dependency on this module.

Note that not all products define and declare modules.
For example, PhpStorm does not have its own module, but the product itself depends on (and ships with) the PHP language plugin.
A plugin project is compatible with PHP functionality if it declares a dependency on this PHP language plugin.

> A high-level feature comparison tool for JetBrains IDEs is available [here](https://www.jetbrains.com/products/compare/).
>
{type="tip"}


The following table lists **(1)** modules or built-in plugins that provide specific functionality, and the products currently shipping with them.

| Module or Plugin for `<depends>` Element<br/>Declaration in <path>plugin.xml</path> File | <br/>Functionality                                                                                                                                  | IntelliJ Platform-Based<br/>Product Compatibility                                                                                                                          |
|------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `com.intellij.modules.java` See **(2)** below. <br/>`com.intellij.java`                  | **Java** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                       | IntelliJ IDEA, Android Studio                                                                                                                                              |
| `com.intellij.modules.androidstudio`                                                     | Android SDK Platform, Build Tools, Platform Tools, SDK Tools                                                                                        | Android Studio                                                                                                                                                             |
| `com.intellij.modules.cidr.lang`                                                         | **C, C++, Objective-C/C++** language PSI Model, Swift/Objective-C Interaction, Inspections, Intentions, Completion, Refactoring, Test Framework     | AppCode, CLion                                                                                                                                                             |
| `com.intellij.modules.cidr.debugger`                                                     | Debugger Watches, Evaluations, Breakpoints, Inline Debugging                                                                                        | AppCode, CLion, RubyMine                                                                                                                                                   |
| `com.intellij.modules.appcode` See **(3)** below. <br/>`com.intellij.appcode`            | Xcode Project Model, CocoaPods, Core Data Objects, Device & Simulator Support                                                                       | AppCode                                                                                                                                                                    |
| `com.intellij.modules.clion` See **(3)** below. <br/>`com.intellij.clion`                | CMake, Profiler, Embedded Development, Remote Development, Remote Debug, Disassembly                                                                | CLion                                                                                                                                                                      |
| `com.intellij.cidr.base`                                                                 | Native Debugger Integration, Utility Classes, C/C++ Project Model/Workspace Support (OCWorkspace, CidrWorkspace, etc.), C/C++ Build and Run Support | AppCode, CLion                                                                                                                                                             |
| `com.intellij.database`                                                                  | **Database Tools and SQL** language PSI Model, Inspections, Completion, Refactoring, Queries                                                        | DataGrip, IntelliJ IDEA Ultimate, AppCode, PhpStorm, PyCharm Professional, RubyMine, CLion, GoLand, Rider, and WebStorm if the Database Tools and SQL plugin is installed. |
| `com.intellij.modules.go`                                                                | **Go** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                         | GoLand                                                                                                                                                                     |
| `com.intellij.modules.python`                                                            | **Python** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                     | PyCharm, and other products if the Python plugin is installed.                                                                                                             |
| `com.intellij.modules.rider`                                                             | Connection to **ReSharper** Process in Background                                                                                                   | Rider                                                                                                                                                                      |
| `com.intellij.modules.ruby`                                                              | **Ruby** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                       | RubyMine, and IntelliJ IDEA Ultimate if the Ruby plugin is installed.                                                                                                      |
| `com.intellij.modules.ultimate`                                                          | Licensing                                                                                                                                           | All commercial IDEs (IntelliJ IDEA Ultimate, PhpStorm, DataGrip, ...)                                                                                                      |
| `com.intellij.swift`                                                                     | **Swift** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                      | AppCode, CLion                                                                                                                                                             |
| `com.jetbrains.php`                                                                      | **PHP** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                        | PhpStorm, and other products if the PHP plugin is installed.                                                                                                               |
| `JavaScript`                                                                             | **JavaScript** language PSI Model, Inspections, Intentions, Completion, Refactoring, Test Framework                                                 | WebStorm, and other products if the JavaScript plugin is installed.                                                                                                        |

**Notes about Module and Plugin Dependency:**

**(1)** This table is not exhaustive; other modules are currently available in JetBrains' IntelliJ Platform-based IDEs.
To see a list of modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the `<depends>` element contents while editing the <path>plugin.xml</path> file.

**(2)** The [Java language functionality](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/) was extracted as a plugin in version 2019.2 of the IntelliJ Platform.
This refactoring separated the Java implementation from the other, non-language portions of the platform.
Consequently, [dependencies](plugin_dependencies.md) on Java functionality are expressed differently in <path>plugin.xml</path> depending on the version of the IntelliJ Platform being targeted:

* Syntax for 2019.2 and later releases:
  * <path>plugin.xml</path> (_allowable alternative_):
     ```xml
     <depends>com.intellij.java</depends>
     ```
  * Gradle build script (_required_):

    <tabs>
    <tab title="Kotlin">

    ```kotlin
    intellij {
      plugins.set(listOf("com.intellij.java"))
    }
    ```

    </tab>
    <tab title="Groovy">

    ```groovy
    intellij {
      plugins = ['com.intellij.java']
    }
    ```

    </tab>
    </tabs>

* Syntax _required_ for releases prior to 2019.2, _allowable_ in all releases:
  * <path>plugin.xml</path>:
     ```xml
     <depends>com.intellij.modules.java</depends>
     ```

**(3)** The [AppCode and CLion code was restructured](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/) in version 2020.3.
This refactoring extracted some functionalities into specific modules for easier maintainability and reuse between AppCode/CLion and other JetBrains IDEs.
Consequently, [dependencies](plugin_dependencies.md) on AppCode and CLion functionalities are expressed differently in <path>plugin.xml</path> depending on the version of the AppCode/CLion Platform being targeted:


<tabs>
<tab title="AppCode">

* Syntax for 2020.3 and later releases:
  * <path>plugin.xml</path> (_allowable alternative_):
    ```xml
    <depends>com.intellij.appcode</depends>
    ```

  * Gradle build script (_required_):

    <tabs>
    <tab title="Kotlin">

    ```kotlin
    intellij {
      plugins.set(listOf("com.intellij.appcode"))
    }
    ```

    </tab>
    <tab title="Groovy">

    ```groovy
    intellij {
      plugins = ['com.intellij.appcode']
    }
    ```

    </tab>
    </tabs>

* Syntax _required_ for releases prior to 2020.3, _allowable_ in all releases:
  * <path>plugin.xml</path>:
    ```xml
    <depends>com.intellij.modules.appcode</depends>
    ```

</tab>
<tab title="CLion">

* Syntax for 2020.3 and later releases:
  * <path>plugin.xml</path> (_allowable alternative_):
    ```xml
    <depends>com.intellij.clion</depends>
    ```

  * Gradle build script (_required_):

    <tabs>
    <tab title="Kotlin">

    ```kotlin
    intellij {
      plugins.set(listOf("com.intellij.clion"))
    }
    ```

    </tab>
    <tab title="Groovy">

    ```groovy
    intellij {
      plugins = ['com.intellij.clion']
    }
    ```

    </tab>
    </tabs>

* Syntax _required_ for releases prior to 2020.3, _allowable_ in all releases:
  * <path>plugin.xml</path>:
    ```xml
    <depends>com.intellij.modules.clion</depends>
    ```

</tab>
</tabs>

## Exploring Module and Plugin APIs

Once the [dependency on a module or plugin](plugin_dependencies.md) is declared in <path>plugin.xml</path>, it's useful to explore the packages and classes available in that dependency.
The section below gives some recommended procedures for discovering what's available in a module or plugin on which a project depends.
These procedures assume a project has the Gradle build script and <path>plugin.xml</path> dependencies configured correctly.

### Exploring APIs as a Consumer

Exploring the available packages and classes in a plugin or module utilizes features in the IntelliJ IDEA IDE.

If the project is not up-to-date, [reimport the Gradle project](https://www.jetbrains.com/help/idea/work-with-gradle-projects.html#gradle_refresh_project) as a first step.
Reimporting the project will automatically update the dependencies.

In the Project Window, select Project View and scroll to the bottom to see [External Libraries](https://www.jetbrains.com/help/idea/project-tool-window.html#content_pane).
Look for the library `Gradle:unzipped.com.jetbrains.plugins:foo:`, where "foo" matches, or is similar to the contents of the `<depends>` tags in <path>plugin.xml</path> or the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) declaration in the Gradle build script.
The image below shows the External Libraries for the example plugin project configuration explained in [Configuring Gradle build script](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute) and [Configuring plugin.xml](dev_alternate_products.md#configuring-pluginxml).

![Example PhpStorm Project Libraries](php_prj_libs.png){width="700"}

Expand the External Library (as shown) to reveal the JAR files contained in the library.
Drill down into the JAR files to expose the packages and (decompiled) classes.

### Exploring APIs as an Extender

If a project is dependent on a plugin or module, in some cases, the project can also [extend](plugin_extensions.md) the functionality available from the plugin or module.

> See [Explore the IntelliJ Platform API](explore_api.md) for more information and strategies.
> Dedicated Extension Point Lists specific to IDEs are available under _Part VIII — Product Specific_.
>
{type="tip"}

To browse the opportunities for an extension, start by placing the cursor on the contents of the `<depends>` elements in the project's <path>plugin.xml</path> file.
Use the [Go to Declaration](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#go_to_declaration) IDE feature to navigate to the <path>plugin.xml</path> file for the plugin on which the project depends.

For example, performing this procedure on the `<depends>com.jetbrains.php</depends>` declaration in a project's <path>plugin.xml</path> file will navigate to the <path>plugin.xml</path> file for the `com.jetbrains.php` (PHP) project.
A common, but not universal, pattern in the IntelliJ platform is for a plugin (like PHP) to declare `<extensionPoints>` and then implement each one as `<extensions>`.
Continuing the example, search the PHP plugin's <path>plugin.xml</path> file for:
* `<extensionPoints>` to find the opportunities for extending the PHP plugin's functionality.
* `<extensions defaultExtensionNs="com.jetbrains.php">` to find where the PHP plugin extends functionality.
  The extension namespace (in this example `com.jetbrains.php`) will match the `<id>` defined in the <path>plugin.xml</path> file.

## Verifying Dependency

Before marking a plugin project as _dependent only on modules in a target product_ in addition to `com.intellij.modules.platform`, verify the plugin isn't implicitly dependent on any APIs that are specific to IntelliJ IDEA.

For [Gradle-based](gradle_build_system.md) projects, [Plugin Verifier](api_changes_list.md#plugin-verifier) can be used to ensure compatibility with all specified target IDEs.

For [DevKit-based](using_dev_kit.md) projects, create an SDK pointing to an installation of the intended target IntelliJ Platform-based product, e.g., PhpStorm, rather than IntelliJ IDEA.
Use the same development version of the IntelliJ platform as the targeted product.

Based on the tables above, the [JetBrains Marketplace](https://plugins.jetbrains.com/) automatically detects the JetBrains products with which a plugin is compatible, and makes the compatibility information available to plugin authors.
The compatibility information determines if plugins are available for users of a particular JetBrains product.

## Platform API Version Compatibility

The API of IntelliJ Platform and bundled plugins may change between releases.
The significant changes that may break plugins are listed on [Incompatible Changes in IntelliJ Platform and Plugins API](api_changes_list.md) page.
