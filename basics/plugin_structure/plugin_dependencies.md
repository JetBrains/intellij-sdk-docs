---
title: Plugin Dependencies
---

Your plugin may depend on classes from other plugins, either bundled, third-party or your own. In order to express such 
dependencies, you need to perform the following three steps:

### 1. Preparing Sandbox
If the plugin is not bundled, run the sandbox instance of your target IDE and install the plugin there.

### 2. Project Setup
Depending on your chosen workflow (Gradle or DevKit), one of the two following steps is necessary.

### 2.1 Gradle
> **NOTE** Please see the `plugins` attribute [gradle-intellij-plugin: Configuration](https://github.com/JetBrains/gradle-intellij-plugin#configuration) for acceptable values.

If you're using [Gradle](/tutorials/build_system.md) with a Groovy build script to build your plugin,  add the dependency to the `plugins` parameter of the `intellij` block in your build.gradle, for example:

```groovy
intellij {
    plugins 'org.jetbrains.kotlin:1.3.11-release-IJ2018.3-1'
}
```

If you are using [Gradle](/tutorials/build_system.md) with a Kotlin build script to build your plugin, use `setPlugins()` within the `intellij` block, for example:

```kotlin
intellij {
        setPlugins("org.jetbrains.kotlin:1.3.11-release-IJ2018.3-1")
}
```

#### 2.2 DevKit
If you are using [DevKit](/basics/getting_started/using_dev_kit.md), add the JARs of the plugin you're depending on to the classpath of your *IntelliJ Platform SDK*.

> **WARNING** Do not add the plugin JARs as a library: this will fail at runtime because IntelliJ Platform will load two separate copies of the dependency plugin classes.

In order to do that, open the Project Structure dialog, select the SDK you're using, press the + button in the Classpath tab, and
select the plugin JAR file or files:
* For bundled plugins, the plugin JAR files are located in `plugins/<pluginname>` or `plugins/<pluginname>/lib` under the main installation directory.
  If you're not sure, which JAR to add, you can add all of them.
* For non-bundled plugins, the plugin JAR files are located in `config/plugins/<pluginname>` or `config/plugins/<pluginname>/lib` under the directory specified as "Sandbox Home" in the IntelliJ Platform Plugin SDK settings.

![Adding Plugin to Classpath](img/add_plugin_dependency.png)

### 3. Declaration in plugin.xml
In your `plugin.xml`, add a `<depends>` tag with the ID of the dependency plugin as its content (autocompletion is available).
For example:

```xml
<depends>org.jetbrains.kotlin</depends>
```

## Optional Plugin Dependencies

You can also specify an optional plugin dependency. In this case, your plugin will load even if the plugin you depend on
is not installed or enabled, but part of the functionality of your plugin will not be available. In order to do that,
add `optional="true" config-file="otherconfig.xml"` to the `<depends>` tag.

For example,
if you're working on a plugin that adds additional highlighting for Java and Kotlin files, you can use the following setup. 

Your main `plugin.xml` will define an annotator for Java and specify an optional dependency on the Kotlin plugin:
```xml
<idea-plugin>
   ...
   <depends optional="true" config-file="withKotlin.xml">org.jetbrains.kotlin</depends>

   <extensions defaultExtensionNs="com.intellij">
      <annotator language="JAVA" implementationClass="com.example.MyJavaAnnotator"/>
   </extensions>
</idea-plugin>
```

Then, you create a file called `withKotlin.xml`, in the same directory as your main `plugin.xml` file. In that file, you
define an annotator for Kotlin:
```xml
<idea-plugin>
   <extensions defaultExtensionNs="com.intellij">
      <annotator language="kotlin" implementationClass="com.example.MyKotlinAnnotator"/>
   </extensions>
</idea-plugin>
```

