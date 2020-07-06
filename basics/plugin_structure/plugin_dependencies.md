---
title: Plugin Dependencies
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A plugin may depend on classes from other plugins, either bundled, third-party, or by the same author.
This document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
For more information about dependencies on the IntelliJ Platform modules, see Part II of this document: [Plugin Compatibility with IntelliJ Platform Products](/basics/getting_started/plugin_compatibility.md).

> **NOTE** It is not possible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))
 
To express dependencies on classes from other plugins or modules, perform the following three required steps:

## 1. Locating Plugin ID and Preparing Sandbox
For plugins published on [JetBrains Plugins Repository](https://plugins.jetbrains.com)
- open plugin's detail page 
- select _Versions_ tab
- open detail page for the desired version

Otherwise, locate the plugin's main JAR file containing `META-INF/plugin.xml` descriptor with `<id>` tag (or `<name>` if not specified).

If the plugin is not bundled with the target IDE, run the (sandbox) [IDE Development Instance](/basics/ide_development_instance.md) of your target IDE and install the plugin there.

## 2. Project Setup
Depending on the chosen development workflow (Gradle or DevKit), one of the two following steps is necessary.

### 2.1 Gradle
> **NOTE** Please see the `plugins` attribute [gradle-intellij-plugin: Configuration](https://github.com/JetBrains/gradle-intellij-plugin#configuration) for acceptable values.

If the project is using [Gradle](/tutorials/build_system.md) with a Groovy build script to build the plugin, add the dependency to the `plugins` parameter of the `intellij` block in your `build.gradle`, for example:

```groovy
intellij {
    plugins 'org.another.plugin:1.0'
}
```

When using Kotlin build script, use `setPlugins()` within the `intellij` block, for example:

```kotlin
intellij {
    setPlugins("org.another.plugin:1.0")
}
```                      

> **NOTE** Transitive dependencies required for tests must currently be [specified explicitly](https://github.com/JetBrains/gradle-intellij-plugin/issues/38). 

### 2.2 DevKit
> **TIP** Existing DevKit-based projects can be converted to use [Gradle setup](/tutorials/build_system/prerequisites.md#adding-gradle-support-to-an-existing-devkit-based-intellij-platform-plugin) where managing dependencies is fully automated.

If the project is using [DevKit](/basics/getting_started/using_dev_kit.md), add the JARs of the plugin on which the project depends to the **classpath** of the *IntelliJ Platform SDK*.

> **WARNING** Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.

To do that, open the Project Structure dialog, select the SDK used in the project, press the + button in the Classpath tab, and
select the plugin JAR file or files:
* For bundled plugins, the plugin JAR files are located in `plugins/<pluginname>` or `plugins/<pluginname>/lib` under the main installation directory.
  If you're not sure which JAR to add, you can add all of them.
* For non-bundled plugins, the plugin JAR files are located in `config/plugins/<pluginname>` or `config/plugins/<pluginname>/lib` under the directory specified as "Sandbox Home" in the IntelliJ Platform Plugin SDK settings.

## 3. Dependency Declaration in plugin.xml
Regardless of whether a plugin project uses [Modules Available in All Products](/basics/getting_started/plugin_compatibility.md#modules-available-in-all-products), or [Modules Specific to Functionality](/basics/getting_started/plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in `plugin.xml`. 
If a project depends on another plugin, the dependency must be declared like a module.
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.
To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the `<depends>` element contents while editing the plugin project's `plugin.xml` file.

### 3.1 Configuring plugin.xml
In the `plugin.xml`, add a `<depends>` tag with the ID of the dependency plugin as its content.
Continuing with the example from [Section 2](#2-project-setup) above, the dependency declaration in `plugin.xml` would be:

```xml
<depends>org.another.plugin</depends>
```


## Optional Plugin Dependencies
A project can also specify an optional plugin dependency. In this case, the plugin will load even if the plugin it depends on
is not installed or enabled, but part of the functionality of the plugin will not be available. In order to do that,
add `optional="true" config-file="otherconfig.xml"` to the `<depends>` tag.

For example, if a plugin project adds additional highlighting for Java and Kotlin files, use the following setup. 
The main `plugin.xml` will define an annotator for Java and specify an optional dependency on the Kotlin plugin:

_plugin.xml_

```xml
<idea-plugin>
   ...
   <depends optional="true" config-file="withKotlin.xml">org.jetbrains.kotlin</depends>

   <extensions defaultExtensionNs="com.intellij">
      <annotator language="JAVA" implementationClass="com.example.MyJavaAnnotator"/>
   </extensions>
</idea-plugin>
```

Then create a file called `withKotlin.xml`, in the same directory as the main `plugin.xml` file. In that file, define an annotator for Kotlin:

_withKotlin.xml_

```xml
<idea-plugin>
   <extensions defaultExtensionNs="com.intellij">
      <annotator language="kotlin" implementationClass="com.example.MyKotlinAnnotator"/>
   </extensions>
</idea-plugin>
```
