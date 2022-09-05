[//]: # (title: Dependencies)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

A plugin may depend on classes from other plugins, either bundled, third-party, or by the same author.
This document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
For more information about dependencies on the IntelliJ Platform modules, see Part II of this document: [](plugin_compatibility.md).

> For adding dependencies on 3rd party libraries, use regular [Gradle dependency management](https://docs.gradle.org/current/userguide/core_dependency_management.html).
>
{type="note"}

<procedure title="Required Steps">

To express a dependency on classes from other plugins or modules, perform the following three required steps detailed below on this page:

1. Locate Plugin ID
2. Project Setup
3. Declaration in <path>plugin.xml</path>

If `java.lang.NoClassDefFoundError` occurs at runtime, it means that either Step 3 was omitted or loading the plugin dependency failed (please check log files from [Development Instance](ide_development_instance.md#development-instance-settings-caches-logs-and-plugins)).

</procedure>

## 1. Locating Plugin ID and Preparing Sandbox

A compatible version must be chosen carefully according to the plugin's [compatibility](build_number_ranges.md).
It is not possible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))

### JetBrains Marketplace

For plugins published on [JetBrains Marketplace](https://plugins.jetbrains.com):

1. Open plugin's detail page
2. Select <control>Versions</control> tab
3. Open detail page for the desired version, displaying the <control>Compatibility Range</control> and <control>Plugin ID</control>

### Bundled and Other Plugins

For bundled and non-public plugins, locate the plugin's main JAR file containing <path>META-INF/plugin.xml</path> descriptor with `<id>` tag (or `<name>` if not specified).
Bundled plugins are located in <path>$PRODUCT_ROOT$/plugins/$PLUGIN_NAME$/lib/$PLUGIN_NAME$.jar</path>.

#### IDs of Bundled Plugins

The following table lists some commonly used bundled plugins and their ID.
See also [](extension_point_list.md#intellij-community-plugins) and [](plugin_compatibility.md#modules-specific-to-functionality).

| Plugin Name               | Plugin ID                                    |
|---------------------------|----------------------------------------------|
| Copyright                 | `com.intellij.copyright`                     |
| CSS                       | `com.intellij.css`                           |
| Database Tools and SQL    | `com.intellij.database` [](data_grip.md)     |
| IntelliLang               | `org.intellij.intelliLang`                   |
| Java                      | `com.intellij.java`                          |
| JavaScript and TypeScript | `JavaScript`                                 |
| Kotlin                    | `org.jetbrains.kotlin` [](kotlin.md)         |
| Markdown                  | `org.intellij.plugins.markdown`              |
| Maven                     | `org.jetbrains.idea.maven`                   |
| Spring                    | `com.intellij.spring` [](spring_api.md)      |
| Spring Boot               | `com.intellij.spring.boot` [](spring_api.md) |

### Preparing Sandbox

If the plugin is not bundled with the target IDE, run the (sandbox) [IDE Development Instance](ide_development_instance.md) of your target IDE and install the plugin there.

## 2. Project Setup

Depending on the chosen development workflow (Gradle or DevKit), one of the two following steps is necessary.

<tabs>
<tab title="Gradle">

> Please see the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) property for acceptable values.
>
{type="note"}

If the project uses [Gradle](gradle_build_system.md), add the dependency to the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) parameter in your build script:

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  plugins.set(listOf("com.example.another-plugin:1.0"))
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
  plugins = ['com.example.another-plugin:1.0']
}
```

</tab>
</tabs>

> Transitive dependencies required for tests must currently be [specified explicitly](https://github.com/JetBrains/gradle-intellij-plugin/issues/38).
>
{type="note"}
</tab>

<tab title="DevKit">

> Existing DevKit-based projects can be converted to use [Gradle setup](gradle_prerequisites.md#adding-gradle-support-to-an-existing-devkit-based-intellij-platform-plugin) where dependency management is fully automated.
>
{type="tip"}

If the project uses [DevKit](using_dev_kit.md), add the JARs of the plugin on which the project depends to the <control>Classpath</control> of the *IntelliJ Platform SDK*.

> Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.
>
{type="warning"}

To do that, open the <control>Project Structure</control> dialog, select the SDK used in the project, press the <shortcut>+</shortcut> button in the <control>Classpath</control> tab, and select the plugin JAR file(s):
* For bundled plugins, the plugin JAR files are located in <path>plugins/$PLUGIN_NAME$</path> or <path>plugins/$PLUGIN_NAME$/lib</path> under the main installation directory.
  If you're not sure which JAR to add, you can add all of them.
* For non-bundled plugins, the plugin JAR files are located in <path>config/plugins/$PLUGIN_NAME$</path> or <path>config/plugins/$PLUGIN_NAME$/lib</path> under the directory specified as <control>Sandbox Home</control> in the IntelliJ Platform Plugin SDK settings.

</tab>

</tabs>

## 3. Dependency Declaration in plugin.xml

Regardless of whether a plugin project uses [](plugin_compatibility.md#modules-available-in-all-products), or [](plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in <path>plugin.xml</path>.
If a project depends on another plugin, the dependency must be declared like a [module](plugin_compatibility.md#modules).
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.

To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the `<depends>` element contents while editing the plugin project's <path>plugin.xml</path> file.

In the <path>plugin.xml</path>, add a `<depends>` tag with the dependency plugin's ID as its content.
Continuing with the example from [Project Setup](#2-project-setup) above, the dependency declaration in <path>plugin.xml</path> would be:

```xml
<depends>com.example.another-plugin</depends>
```

## Optional Plugin Dependencies

A plugin can also specify an optional plugin dependency.
In this case, the plugin will load even if the plugin it depends on is not installed or enabled, but part of the plugin's functionality will not be available.

Declare additional `optional="true"` and `config-file` attribute pointing to optional plugin descriptor file:

```xml
<depends
    optional="true"
    config-file="myPluginId-optionalPluginName.xml">dependency.plugin.id</depends>
```

> Additional plugin descriptor files must follow the naming pattern <path>myPluginId-$NAME$.xml</path> resulting in unique filenames to prevent problems with classloaders in tests ([Details](https://youtrack.jetbrains.com/issue/IDEA-205964)).
>
{type="note"}

For example, if a plugin adds additional highlighting for Java and Kotlin files, use the following setup.
The main <path>plugin.xml</path> will define an annotator for Java and specify an optional dependency on the Kotlin plugin (plugin ID `org.jetbrains.kotlin`):

<path>plugin.xml</path>
```xml
<idea-plugin>
   ...
   <depends
       optional="true"
       config-file="myPluginId-withKotlin.xml">org.jetbrains.kotlin</depends>

   <extensions defaultExtensionNs="com.intellij">
      <annotator
          language="JAVA"
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
      <annotator
          language="kotlin"
          implementationClass="com.example.MyKotlinAnnotator"/>
   </extensions>
</idea-plugin>
```
