<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle IntelliJ Plugin (1.x) – FAQ
<primary-label ref="Obsolete"/>

<link-summary>FAQ for using Gradle IntelliJ Plugin (1.x)</link-summary>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

### How to target 2022.3 platform?

<include from="snippets.topic" element-id="gradlePluginVersion"/>

### How to modify JVM arguments of runIde task?

[`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task is a [Java Exec](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) task and can be modified according to the documentation.

To add some JVM arguments while launching the IDE, configure [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    jvmArgs("-DmyProperty=value")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
runIde {
  jvmArgs "-DmyProperty=value"
}
```

</tab>
</tabs>

### How to modify system properties of runIde task?

Using the [very same task documentation](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html), configure [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    systemProperty("name", "value")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
runIde {
  systemProperty("name", "value")
}
```

</tab>
</tabs>

### How to disable automatic reload of dynamic plugins?

See [](ide_development_instance.md#enabling-auto-reload) for important caveats.

Configure [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    autoReloadPlugins.set(false)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
runIde {
  autoReloadPlugins = false
}
```

</tab>
</tabs>

### How to disable building searchable options?

Building searchable options can be disabled as a task:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  buildSearchableOptions {
    enabled = false
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
buildSearchableOptions.enabled = false
```

</tab>
</tabs>

As a result of disabling building searchable options, the [Settings](settings.md) that your plugin provides won't be searchable in the <ui-path>Settings</ui-path> dialog.
Disabling of the task is suggested for plugins that are not intended to provide custom settings.

### How to show log file of sandbox instance?

The most convenient way to see the logs of running IDE is to add a tab to the <control>Run</control> tool window displaying the contents of <path>idea.log</path> file.
In the Gradle `runIde` run configuration, add the log file path according to [sandbox location](ide_development_instance.md#the-development-instance-sandbox-directory) as described in [View logs](https://www.jetbrains.com/help/idea/setting-log-options.html).

### How to add a custom file inside plugin distribution?

[`prepareSandbox`](tools_gradle_intellij_plugin.md#tasks-preparesandbox) task is a [`Sync`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html) task and can be modified accordingly.
Something like following should work:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  prepareSandbox {
    from("yourFile") {
      into("${intellij.pluginName.get()}/lib/")
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
prepareSandbox {
  from("yourFile") {
    into "${intellij.pluginName.get()}/lib/"
  }
}
```

</tab>
</tabs>

### Task `setupDependencies` not found in root project

The [`setupDependencies`](tools_gradle_intellij_plugin.md#tasks-setupdependencies) task is designed to fetch the target IDE dependency from the IntelliJ Repository in the after-sync Gradle phase, but only when working inside IntelliJ IDEA – to make the IntelliJ SDK classes resolved and code completion available.
To achieve that, the [`gradle-idea-ext-plugin`](https://github.com/JetBrains/gradle-idea-ext-plugin) is used, which alters the IDEA project's <path>.idea/workspace.xml</path> file making the [`setupDependencies`](tools_gradle_intellij_plugin.md#tasks-setupdependencies) task activated on `after_sync` event.

Unfortunately, this entry remains even after disabling the `org.jetbrains.intellij` plugin in a project – the [`setupDependencies`](tools_gradle_intellij_plugin.md#tasks-setupdependencies) task won't be resolved appropriately, which produces the following exception:

```
Task 'setupDependencies' not found in root project 'projectName'.
```

To fix that, manually edit the <path>.idea/workspace.xml</path> file removing mentioned entry, go to the <control>Gradle</control> tool window, select the <ui-path>Tasks Activation</ui-path> action from the context menu of the root project item, and remove it.

### How to expose my plugin API sources to dependent plugins?

See the [](bundling_plugin_openapi_sources.md) section for details.

### The Plugin Verifier download directory is set to [...], but downloaded IDEs were also found in [...]

With the `1.10.0` release, the [`runPluginVerifier`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) task uses the `XDG_CACHE_HOME` environment variable (see [XDG Base Directory](https://wiki.archlinux.org/title/XDG_Base_Directory) for more details) to resolve the default directory for downloaded IDEs – instead of the user's home directory.
We recommend moving your existing IDEs stored i.e., in <path>~/.pluginVerifier/ides/</path> directory into <path>\$XDG_CACHE_HOME\$/pluginVerifier/ides</path> to avoid downloading them once again.

In case you want to keep the downloaded archives in the previous location, specify the given path explicitly to the [`runPluginVerifier.downloadDir`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier-downloaddir) property:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runPluginVerifier {
    downloadDir.set(System.getProperty("user.home") + "/.pluginVerifier/ides")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
runPluginVerifier {
  downloadDir = System.getProperty("user.home") + "/.pluginVerifier/ides"
}
```

</tab>
</tabs>


### JaCoCo Reports 0% Coverage

The Gradle IntelliJ Plugin, when targeting the IntelliJ SDK `2022.1+`, uses the `PathClassLoader` class loader by the following system property:

```
-Djava.system.class.loader=com.intellij.util.lang.PathClassLoader
```

Because of that, JaCoCo – and other external tools that rely on classes available in the bootstrap class loader – fail to discover plugin classes.

In addition, if the code instrumentation is enabled (see [`intellij.instrumentCode`](tools_gradle_intellij_plugin.md#intellij-extension-instrumentcode)), it's required to switch to the compiled and instrumented output instead of a default compiled classes.

The following changes to your Gradle configuration file:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  withType<Test> {
    configure<JacocoTaskExtension> {
      isIncludeNoLocationClasses = true
      excludes = listOf("jdk.internal.*")
    }
  }

  jacocoTestReport {
    classDirectories.setFrom(instrumentCode)
  }

  jacocoTestCoverageVerification {
    classDirectories.setFrom(instrumentCode)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
test {
  jacoco {
    includeNoLocationClasses = true
    excludes = ["jdk.internal.*"]
  }
}

jacocoTestReport {
  classDirectories.setFrom(instrumentCode)
}

jacocoTestCoverageVerification {
  classDirectories.setFrom(instrumentCode)
}
```

</tab>
</tabs>


### Exception `SSLPeerUnverifiedException: peer not authenticated`

When using Java `11.0.2` for building plugins, resolving dependencies (or making any other network requests) in Gradle IntelliJ Plugin fails due to the [JDK-8220723](https://bugs.openjdk.org/browse/JDK-8220723) issue with the following exception:

```
Exception in thread "main" javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated
```

To fix that issue, upgrade the Java version to the latest patch available of the major version of your choice.


### How to add a dependency on a plugin available in the file system?

It is possible to add a dependency on a plugin available in the file system — like a plugin update downloaded manually from JetBrains Marketplace or built separately in another project.

To configure the dependency, add the `File` instance to the [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) property and point it to the extracted plugin's directory which contains the <path>lib</path> directory.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellij {
    plugins.set(listOf(file("/path/to/plugin/")))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellij {
    plugins = [file("/path/to/plugin/")]
}
```

</tab>
</tabs>

It is also possible to refer to the sandbox directory of another Gradle project — to do that, point to the <path>/projects/plugin-name/build/idea-sandbox/plugins/plugin-name/</path> directory.


### Kotlin compiler throws `Out of memory. Java heap space` error

Please upgrade to Kotlin 1.9.0. See the [](using_kotlin.md#incremental-compilation) section if using Kotlin 1.8.20.

<include from="snippets.topic" element-id="missingContent"/>

