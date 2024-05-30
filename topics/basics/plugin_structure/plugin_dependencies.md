<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Dependencies

<link-summary>Declaring dependencies on other IntelliJ Platform-based plugins.</link-summary>

A plugin may depend on API and classes from other plugins, either bundled or third-party.

This document describes the syntax for declaring plugin dependencies and optional plugin dependencies.
For more information about dependencies on the IntelliJ Platform modules, see [](plugin_compatibility.md).

> For adding dependencies on 3rd party libraries, use regular [Gradle dependency management](https://docs.gradle.org/current/userguide/core_dependency_management.html).
>
{style="note"}

<procedure title="Required Steps">

To express a dependency on classes from other plugins or modules, perform the following three required steps detailed below on this page:

1. Locate Plugin ID
2. Project Setup
3. Declaration in <path>[plugin.xml](plugin_configuration_file.md)</path>

> If `java.lang.NoClassDefFoundError` occurs at runtime, most likely Step 3 was omitted.
>
> Otherwise, loading the plugin dependency may have failed, please check log files from
> the [Development Instance](ide_development_instance.md#development-instance-settings-caches-logs-and-plugins)).
>
{title="Getting java.lang.NoClassDefFoundError"}

</procedure>

## 1. Locating Plugin ID and Preparing Sandbox

A compatible version must be chosen carefully according to the plugin's [compatibility](build_number_ranges.md).
For non-bundled plugins, it is not possible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))

### JetBrains Marketplace

For plugins published on [JetBrains Marketplace](https://plugins.jetbrains.com):

1. Open plugin's detail page
2. Scroll down to the bottom section <control>Additional Information</control>
3. Copy <control>Plugin ID</control>

### Bundled and Other Plugins

When using [Gradle IntelliJ Plugin](developing_plugins.md), all bundled plugin IDs can be gathered using [`listBundledPlugins`](tools_gradle_intellij_plugin.md#tasks-listbundledplugins) task.

When using [DevKit](developing_themes.md) and for non-public plugins, locate the plugin's main JAR file containing <path>META-INF/plugin.xml</path> descriptor with [`<id>`](plugin_configuration_file.md#idea-plugin__id) tag (or [`<name>`](plugin_configuration_file.md#idea-plugin__name) if not specified).
Bundled plugins are located in <path>$PRODUCT_ROOT$/plugins/$PLUGIN_NAME$/lib/$PLUGIN_NAME$.jar</path>.

#### IDs of Bundled Plugins

The following table lists some commonly used bundled plugins and their ID.
See also [](intellij_community_plugins_extension_point_list.md) and [](plugin_compatibility.md#modules-specific-to-functionality).

| Plugin Name               | Plugin ID                       | Related Documentation         |
|---------------------------|---------------------------------|-------------------------------|
| Copyright                 | `com.intellij.copyright`        |                               |
| CSS                       | `com.intellij.css`              | [](webstorm.md)               |
| Database Tools and SQL    | `com.intellij.database`         | [](data_grip.md)              |
| Gradle                    | `com.intellij.gradle`           |                               |
| IntelliLang               | `org.intellij.intelliLang`      | [](language_injection.md)     |
| Java                      | `com.intellij.java`             | [](idea.md#java)              |
| JavaScript and TypeScript | `JavaScript`                    | [](webstorm.md)               |
| Kotlin                    | `org.jetbrains.kotlin`          | [](using_kotlin.md)           |
| Markdown                  | `org.intellij.plugins.markdown` |                               |
| Maven                     | `org.jetbrains.idea.maven`      |                               |
| Spring                    | `com.intellij.spring`           | [](spring_api.md)             |
| Spring Boot               | `com.intellij.spring.boot`      | [](spring_api.md#spring-boot) |
| YAML                      | `org.jetbrains.plugins.yaml`    |                               |

### Preparing Sandbox

If the plugin is not bundled with the target IDE, run the (sandbox) [IDE Development Instance](ide_development_instance.md) of your target IDE and install the plugin there.

## 2. Project Setup

Depending on the chosen development workflow (Gradle or DevKit), one of the two following steps is necessary.

<tabs>
<tab title="Gradle">

> Please see the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) property for acceptable values.
>
{style="note"}

Add the dependency to the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) parameter in your build script:

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
{style="note"}
</tab>

<tab title="DevKit">

> Existing DevKit-based projects can be [converted to use Gradle setup](migrating_plugin_devkit_to_gradle.md) where dependency management is fully automated.
>
{style="note"}

Add the JARs of the plugin on which the project depends to the <control>Classpath</control> of the [*IntelliJ Platform SDK*](setting_up_theme_environment.md#add-intellij-platform-plugin-sdk).

> Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.
>
{style="warning"}

<procedure title="Adding a plugin dependency in DevKit-based plugin">

1. Open the <control>Project Structure</control> dialog and go to <ui-path>Platform Settings | SDKs</ui-path> section.
2. Select the SDK used in the project.
3. Click the <control>+</control> button in the <control>Classpath</control> tab.
4. Select the plugin JAR depending on whether it is bundled or non-bundled plugin:
   - For bundled plugins, the plugin JAR files are located in <path>plugins/$PLUGIN_NAME$</path> or <path>plugins/$PLUGIN_NAME$/lib</path> under the main installation directory.
   - For non-bundled plugins, depending on the platform version, the plugin JAR files are located in:
     - [plugins directory for versions 2020.1+](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#plugins-directory)
     - [plugins directory for versions pre-2020.1](https://www.jetbrains.com/help/idea/2019.3/tuning-the-ide.html#plugins-directory)

</procedure>

</tab>

</tabs>

## 3. Dependency Declaration in plugin.xml

Regardless of whether a plugin project uses [](plugin_compatibility.md#modules-available-in-all-products), or [](plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in <path>plugin.xml</path>.
If a project depends on another plugin, the dependency must be declared like a [module](plugin_compatibility.md#modules).
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.

To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) element contents while editing the plugin project's <path>plugin.xml</path> file.

In the <path>plugin.xml</path>, add a `<depends>` tag with the dependency plugin's ID as its content.
Continuing with the example from [Project Setup](#2-project-setup) above, the dependency declaration in <path>plugin.xml</path> would be:

```xml
<depends>com.example.another-plugin</depends>
```

## Optional Plugin Dependencies

A plugin can also specify an optional plugin dependency.
In this case, the plugin will load even if the plugin it depends on is not installed or enabled, but part of the plugin's functionality will not be available.

Declare additional `optional="true"` and required `config-file` attribute pointing to the [optional plugin descriptor file](plugin_configuration_file.md#additional-plugin-configuration-files):

```xml
<depends
    optional="true"
    config-file="myPluginId-optionalPluginName.xml">dependency.plugin.id</depends>
```

> Additional plugin descriptor files must follow the naming pattern <path>myPluginId-$NAME$.xml</path> resulting in unique filenames to prevent problems with classloaders in tests ([Details](https://youtrack.jetbrains.com/issue/IDEA-205964)).
>
{style="note"}

### Sample

The plugin adds additional highlighting for Java and Kotlin files.
The main <path>plugin.xml</path> defines a required dependency on the Java plugin (plugin ID `com.intellij.java`) and registers the corresponding `com.intellij.annotator` extension.
Additionally, it specifies an optional dependency on the Kotlin plugin (plugin ID `org.jetbrains.kotlin`):

<path>plugin.xml</path>
```xml
<idea-plugin>
   ...
   <depends>com.intellij.java</depends>

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

The configuration file <path>myPluginId-withKotlin.xml</path> is located in the same directory as the main <path>plugin.xml</path> file.
In that file, the annotator extension for Kotlin is defined:

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
