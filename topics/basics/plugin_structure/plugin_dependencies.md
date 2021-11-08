[//]: # (title: Dependencies)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A plugin may depend on classes from other plugins, either bundled, third-party, or by the same author.
This document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
For more information about dependencies on the IntelliJ Platform modules, see Part II of this document: [Plugin Compatibility with IntelliJ Platform Products](plugin_compatibility.md).

 >  It is impossible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))
 >
 {type="note"}

<procedure title="Required Steps">

To express dependencies on classes from other plugins or modules, perform the following three required steps detailed below on this page:

1. Locate Plugin ID
2. Project Setup
3. Declaration in <path>plugin.xml</path>

If `NoClassDefFoundError` occurs at runtime, it means that either Step 3 was omitted or loading the plugin dependency failed (please check log files from [Development Instance](ide_development_instance.md#development-instance-settings-caches-logs-and-plugins)).

</procedure>

## 1. Locating Plugin ID and Preparing Sandbox
A compatible version must be chosen carefully according to the plugin's [compatibility](build_number_ranges.md).

For plugins published on [JetBrains Plugins Repository](https://plugins.jetbrains.com)
- open plugin's detail page
- select <control>Versions</control> tab
- open detail page for the desired version, displaying the <control>Compatibility Range</control> and <control>Plugin ID</control>

For bundled and non-public plugins, locate the plugin's main JAR file containing <path>META-INF/plugin.xml</path> descriptor with `<id>` tag (or `<name>` if not specified).

If the plugin is not bundled with the target IDE, run the (sandbox) [IDE Development Instance](ide_development_instance.md) of your target IDE and install the plugin there.

## 2. Project Setup
Depending on the chosen development workflow (Gradle or DevKit), one of the two following steps is necessary.

<tabs>
<tab title="Gradle">

 >  Please see the `plugins` attribute [gradle-intellij-plugin: Configuration](https://github.com/JetBrains/gradle-intellij-plugin#configuration) for acceptable values.
 >
 {type="note"}

If the project uses [Gradle](gradle_build_system.md) with a Groovy build script to build the plugin, add the dependency to the `plugins` parameter of the `intellij` block in your <path>build.gradle</path>, for example:

<path>build.gradle</path>
```groovy
intellij {
    plugins = ['org.another.plugin:1.0']
}
```

When using Kotlin build script, use `plugins.set()` within the `intellij` block, for example:

<path>build.gradle.kts</path>
```kotlin
intellij {
    plugins.set(listOf("org.another.plugin:1.0"))
}
```

 >  Transitive dependencies required for tests must currently be [specified explicitly](https://github.com/JetBrains/gradle-intellij-plugin/issues/38).
 >
 {type="note"}
</tab>

<tab title="DevKit">

 >  Existing DevKit-based projects can be converted to use [Gradle setup](gradle_prerequisites.md#adding-gradle-support-to-an-existing-devkit-based-intellij-platform-plugin) where managing dependencies is fully automated.
 >
 {type="tip"}

If the project uses [DevKit](using_dev_kit.md), add the JARs of the plugin on which the project depends to the <control>Classpath</control> of the *IntelliJ Platform SDK*.

 >  Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.
 >
 {type="warning"}

To do that, open the Project Structure dialog, select the SDK used in the project, press the <shortcut>+</shortcut> button in the <control>Classpath</control> tab, and select the plugin JAR file(s):
* For bundled plugins, the plugin JAR files are located in <path>plugins/$PLUGINNAME$</path> or <path>plugins/$PLUGINNAME$/lib</path> under the main installation directory.
  If you're not sure which JAR to add, you can add all of them.
* For non-bundled plugins, the plugin JAR files are located in <path>config/plugins/$PLUGINNAME$</path> or <path>config/plugins/$PLUGINNAME$/lib</path> under the directory specified as <control>Sandbox Home</control> in the IntelliJ Platform Plugin SDK settings.

</tab>

</tabs>

## 3. Dependency Declaration in plugin.xml
Regardless of whether a plugin project uses [Modules Available in All Products](plugin_compatibility.md#modules-available-in-all-products), or [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in <path>plugin.xml</path>.
If a project depends on another plugin, the dependency must be declared like a [module](plugin_compatibility.md#modules).
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.

To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the `<depends>` element contents while editing the plugin project's <path>plugin.xml</path> file.

In the <path>plugin.xml</path>, add a `<depends>` tag with the dependency plugin's ID as its content.
Continuing with the example from [Project Setup](#2-project-setup) above, the dependency declaration in <path>plugin.xml</path> would be:

```xml
<depends>org.another.plugin</depends>
```

## Optional Plugin Dependencies
A plugin can also specify an optional plugin dependency.
In this case, the plugin will load even if the plugin it depends on is not installed or enabled, but part of the plugin's functionality will not be available.

Declare additional `optional="true"` and `config-file` attribute pointing to optional plugin descriptor file:

```xml
  <depends optional="true"
           config-file="myPluginId-optionalPluginName.xml">dependency.plugin.id</depends>
```

 >  Additional plugin descriptor files must follow the naming pattern <path>myPluginId-$NAME$.xml</path> resulting in unique filenames to prevent problems with classloaders in tests ([Details](https://youtrack.jetbrains.com/issue/IDEA-205964)).
 >
 {type="note"}

For example, if a plugin adds additional highlighting for Java and Kotlin files, use the following setup.
The main <path>plugin.xml</path> will define an annotator for Java and specify an optional dependency on the Kotlin plugin (plugin ID `org.jetbrains.kotlin`):

<path>plugin.xml</path>
```xml
<idea-plugin>
   ...
   <depends optional="true"
            config-file="myPluginId-withKotlin.xml">org.jetbrains.kotlin</depends>

   <extensions defaultExtensionNs="com.intellij">
      <annotator language="JAVA"
                 implementationClass="com.example.MyJavaAnnotator"/>
   </extensions>
</idea-plugin>
```

Then create a file called <path>myPluginId-withKotlin.xml</path>, in the same directory as the main <path>plugin.xml</path> file.
In that file, define an annotator for Kotlin:

<path>myPluginId-withKotlin.xml</path>

```xml
<idea-plugin>
   <extensions defaultExtensionNs="com.intellij">
      <annotator language="kotlin"
                 implementationClass="com.example.MyKotlinAnnotator"/>
   </extensions>
</idea-plugin>
```
