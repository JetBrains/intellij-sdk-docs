<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Dependencies

<!-- https://jb.gg/ij-plugin-dependencies -->

<web-summary>
Declaring dependencies on other IntelliJ Platform-based plugins. Locating plugin IDs required to set up dependencies.
</web-summary>

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
> Otherwise, loading the plugin dependency may have failed, check log files from
> the [Development Instance](ide_development_instance.md#development-instance-settings-caches-logs-and-plugins).
>
{title="Getting java.lang.NoClassDefFoundError"}

</procedure>

## 1. Locating Plugin ID and Preparing Sandbox
{#locating-plugin-id-and-preparing-sandbox}

A compatible version must be chosen carefully according to the plugin's [compatibility](build_number_ranges.md).
For non-bundled plugins, it is not possible to specify the minimum/maximum version for the dependent plugin. ([Issue](https://youtrack.jetbrains.com/issue/IDEABKL-7906))

### JetBrains Marketplace

For plugins published on [JetBrains Marketplace](https://plugins.jetbrains.com):

1. Open plugin's detail page
2. Scroll down to the bottom section <control>Additional Information</control>
3. Copy <control>Plugin ID</control>

### Bundled and Other Plugins

All IDs of bundled plugins can be gathered using a dedicated Gradle task.
See _Other_ tab on how to locate the plugin ID for a plugin distribution file.

<tabs>

<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Use [`printBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledPlugins) task.

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

Use [`listBundledPlugins`](tools_gradle_intellij_plugin.md#tasks-listbundledplugins) task.

</tab>

<tab title="Other">

Locate the plugin's main JAR file containing the <path>META-INF/plugin.xml</path> descriptor with the [`<id>`](plugin_configuration_file.md#idea-plugin__id) tag
(use [`<name>`](plugin_configuration_file.md#idea-plugin__name) if `<id>` is not specified).

Bundled plugins are located in <path>\$PRODUCT_ROOT\$/plugins/\$PLUGIN_NAME\$/lib/\$PLUGIN_NAME\$.jar</path>.

</tab>

</tabs>

#### IDs of Bundled Plugins

The following table lists some commonly used bundled plugins and their ID.
See also [](plugin_compatibility.md#modules-specific-to-functionality).

<!-- please do not add more entries unless it's clearly a popular plugin -->

| Plugin Name               | Plugin ID                       | Related Documentation                                                            |
|---------------------------|---------------------------------|----------------------------------------------------------------------------------|
| Copyright                 | `com.intellij.copyright`        |                                                                                  |
| CSS                       | `com.intellij.css`              | [](webstorm.md)                                                                  |
| Database Tools and SQL    | `com.intellij.database`         | [](data_grip.md)                                                                 |
| Gradle                    | `com.intellij.gradle`           |                                                                                  |
| Groovy                    | `org.intellij.groovy`           |                                                                                  |
| IntelliLang               | `org.intellij.intelliLang`      | [](language_injection.md)                                                        |
| Java                      | `com.intellij.java`             | [](idea.md#java-plugin)                                                          |
| JavaScript and TypeScript | `JavaScript`                    | [](webstorm.md)                                                                  |
| JSON                      | `com.intellij.modules.json`     | [JSON plugin introduction notes](api_changes_list_2024.md#json-plugin-new-20243) |
| Kotlin                    | `org.jetbrains.kotlin`          | [](idea.md#kotlin-plugin)                                                        |
| Markdown                  | `org.intellij.plugins.markdown` |                                                                                  |
| Maven                     | `org.jetbrains.idea.maven`      |                                                                                  |
| Spring                    | `com.intellij.spring`           | [](spring_api.md)                                                                |
| Spring Boot               | `com.intellij.spring.boot`      | [](spring_api.md#spring-boot)                                                    |
| YAML                      | `org.jetbrains.plugins.yaml`    |                                                                                  |

### Preparing Sandbox

If the plugin is not bundled with the target IDE, run the (sandbox) [](ide_development_instance.md) of your target IDE and install the plugin there.

## 2. Project Setup
{#project-setup}

Depending on the chosen development workflow (Gradle or DevKit), one of the following steps is necessary.

### IntelliJ Platform Gradle Plugin (2.x)

{collapsible="true" default-state="expanded"}

Define dependencies on plugins using the provided helper functions in the `dependencies {}` block of the <path>build.gradle.kts</path> file:

```kotlin
dependencies {
  intellijPlatform {
    bundledPlugin("<pluginId>")
    plugin("<nonBundledPluginId>:<version>")
  }
}
```

For bundled plugins, use `bundledPlugin()`. Use `plugin()` for non-bundled plugins (for example, from [JetBrains Marketplace](https://plugins.jetbrains.com)).

See [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) for full reference and additional options.

### Gradle IntelliJ Plugin (1.x)

{collapsible="true" default-state="collapsed"}

<primary-label ref="Obsolete"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

> See the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) property for acceptable values.
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

> Transitive dependencies required for tests must currently be [specified explicitly](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/38).
>
{style="note"}

### Plugin DevKit

{collapsible="true" default-state="collapsed"}

> Existing DevKit-based projects can be [converted to use Gradle setup](migrating_plugin_devkit_to_gradle.md) where dependency management is fully automated.
>
{style="note"}

Add the JARs of the plugin on which the project depends to the <control>Classpath</control> of the [*IntelliJ Platform SDK*](setting_up_theme_environment.md#add-intellij-platform-plugin-sdk).

> Do not add the plugin JARs as a library: this will fail at runtime because the IntelliJ Platform will load two separate copies of the dependency plugin classes.
>
{style="warning"}

<procedure title="Adding a plugin dependency in a DevKit-based plugin">

1. Open the <control>Project Structure</control> dialog and go to the <ui-path>Platform Settings | SDKs</ui-path> section.
2. Select the SDK used in the project.
3. Click the <control>+</control> button in the <control>Classpath</control> tab.
4. Select the plugin JAR depending on whether it is a bundled or non-bundled plugin:
   - For bundled plugins, the plugin JAR files are located in <path>plugins/\$PLUGIN_NAME\$</path> or <path>plugins/\$PLUGIN_NAME\$/lib</path> under the main installation directory.
   - For non-bundled plugins, the plugin JAR files are located in OS-specific [plugins directory](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#plugins-directory)

</procedure>

## 3. Dependency Declaration in plugin.xml
{#dependency-declaration-in-pluginxml}

Regardless of whether a plugin project uses [](plugin_compatibility.md#modules-available-in-all-products) or [](plugin_compatibility.md#modules-specific-to-functionality), the correct module must be listed as a dependency in <path>plugin.xml</path>.
If a project depends on another plugin, the dependency must be declared like a [module](plugin_compatibility.md#modules).
If only general IntelliJ Platform features (APIs) are used, then a default dependency on `com.intellij.modules.platform` must be declared.

To display a list of available IntelliJ Platform modules, invoke the [code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#4eac28ba) feature for the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) element contents while editing the plugin project's <path>plugin.xml</path> file.

In the <path>plugin.xml</path>, add a `<depends>` tag with the dependency plugin's ID as its content.
Continuing with the example from the [Project Setup](#project-setup) above, the dependency declaration in <path>plugin.xml</path> would be:

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

> Additional plugin descriptor files must follow the naming pattern <path>myPluginId-\$NAME\$.xml</path> resulting in unique filenames to prevent problems with classloaders in tests ([Details](https://youtrack.jetbrains.com/issue/IDEA-205964)).
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
