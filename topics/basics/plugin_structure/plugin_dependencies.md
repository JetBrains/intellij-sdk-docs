[//]: # (title: Plugin Dependencies)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A plugin may depend on classes from other plugins, either bundled, third-party, or by the same author.
This document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
For more information about dependencies on the IntelliJ Platform modules, see Part II of this document: [Plugin Compatibility with IntelliJ Platform Products](plugin_compatibility.md).

 >  It is impossible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))
 >
 {type="note"}

To express dependencies on classes from other plugins or modules, perform the following three required steps:

## Locating Plugin ID and Preparing Sandbox
A compatible version must be chosen carefully according to the plugin's [compatibility](build_number_ranges.md). 

For plugins published on [JetBrains Plugins Repository](https://plugins.jetbrains.com)
- open plugin's detail page
- select _Versions_ tab
- open detail page for the desired version, displaying the _Compatibility Range_ and _Plugin ID_

For bundled and non-public plugins, locate the plugin's main JAR file containing `META-INF/plugin.xml` descriptor with `<id>` tag (or `<name>` if not specified).

If the plugin is not bundled with the target IDE, run the (sandbox) [IDE Development Instance](ide_development_instance.md) of your target IDE and install the plugin there.

## Project Setup
Depending on the chosen development workflow (Gradle or DevKit), one of the two following steps is necessary.

### Gradle
 >  Please see the `plugins` attribute [gradle-intellij-plugin: Configuration](https://github.com/JetBrains/gradle-intellij-plugin#configuration) for acceptable values.
 >
 {type="note"}

If the project uses [Gradle](gradle_build_system.md) with a Groovy build script to build the plugin, add the dependency to the `plugins` parameter of the `intellij` block in your `build.gradle`, for example:

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

 >  Transitive dependencies required for tests must currently be [specified explicitly](https://github.com/JetBrains/gradle-intellij-plugin/issues/38).
 >
 {type="note"}

### DevKit
 >  Existing DevKit-based projects can be converted to use [Gradle setup](gradle_prerequisites.md#adding-gradle-support-to-an-existing-devkit-based-intellij-platform-plugin) where managing dependencies is fully automated.
 >
 {type="tip"}

If the project uses [DevKit](using_dev_kit.md), add the JARs of the plugin on which the project depends to the **classpath** of the *IntelliJ Platform SDK*.

 >  Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.
 >
 {type="warning"}

To do that, open the Project Structure dialog, select the SDK used in the project, press the <kbd>+</kbd> button in the Classpath tab, and select the plugin JAR file or files:
* For bundled plugins, the plugin JAR files are located in `plugins/<pluginname>` or `plugins/<pluginname>/lib` under the main installation directory.
  If you're not sure which JAR to add, you can add all of them.
* For non-bundled plugins, the plugin JAR files are located in `config/plugins/<pluginname>` or `config/plugins/<pluginname>/lib` under the directory specified as "Sandbox Home" in the IntelliJ Platform Plugin SDK settings.

## Dependency Declaration in plugin.xml
Regardless of whether a plugin project uses [Modules Available in All Products](plugin_compatibility.md#modules-available-in-all-products), or [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in `plugin.xml`.
If a project depends on another plugin, the dependency must be declared like a [module](plugin_compatibility.md#modules).
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.

To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the `<depends>` element contents while editing the plugin project's `plugin.xml` file.

### Configuring plugin.xml
In the `plugin.xml`, add a `<depends>` tag with the dependency plugin's ID as its content.
Continuing with the example from [Project Setup](#project-setup) above, the dependency declaration in `plugin.xml` would be:

```xml
<depends>org.another.plugin</depends>
```

## Optional Plugin Dependencies
A project can also specify an optional plugin dependency.
In this case, the plugin will load even if the plugin it depends on is not installed or enabled, but part of the plugin's functionality will not be available.

Declare additional `optional="true"` and `config-file` attribute pointing to optional plugin descriptor file:

```xml
  <depends optional="true" config-file="myPluginId-optionalPluginName.xml">dependency.plugin.id</depends> 
```
                                                                         
 >  Additional plugin descriptor files must follow the naming pattern `myPluginId-$NAME$.xml` resulting in unique filenames to prevent problems with classloaders in tests ([Details](https://youtrack.jetbrains.com/issue/IDEA-205964)).
 >
 {type="note"}

For example, if a plugin adds additional highlighting for Java and Kotlin files, use the following setup.
The main `plugin.xml` will define an annotator for Java and specify an optional dependency on the Kotlin plugin (`org.jetbrains.kotlin`):

_plugin.xml_

```xml
<idea-plugin>
   ...
   <depends optional="true" config-file="myPluginId-withKotlin.xml">org.jetbrains.kotlin</depends>

   <extensions defaultExtensionNs="com.intellij">
      <annotator language="JAVA" implementationClass="com.example.MyJavaAnnotator"/>
   </extensions>
</idea-plugin>
```

Then create a file called `myPluginId-withKotlin.xml`, in the same directory as the main `plugin.xml` file.
In that file, define an annotator for Kotlin:

_myPluginId-withKotlin.xml_

```xml
<idea-plugin>
   <extensions defaultExtensionNs="com.intellij">
      <annotator language="kotlin" implementationClass="com.example.MyKotlinAnnotator"/>
   </extensions>
</idea-plugin>
```