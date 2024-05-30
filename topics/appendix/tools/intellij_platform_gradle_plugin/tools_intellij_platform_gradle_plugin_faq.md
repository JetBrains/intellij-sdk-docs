<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin – FAQ

<link-summary>FAQ for using IntelliJ Platform Gradle Plugin</link-summary>

### How to modify JVM arguments of the `runIde` task?

[`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task is a [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) task and can be modified according to the documentation.

To add some JVM arguments while launching the IDE, configure [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task as follows:

```kotlin
tasks {
  runIde {
    jvmArgs("-DmyProperty=value")
  }
}
```

### How to modify system properties of the `runIde` task?

Using the [very same task documentation](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html), configure [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task:

```kotlin
tasks {
  runIde {
    systemProperty("name", "value")
  }
}
```

### How to disable automatic reload of dynamic plugins?

See [](ide_development_instance.md#enabling-auto-reload) for important caveats.

You can disable auto-reload globally with [`intellijPlatform.autoReload`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-autoReload):

```kotlin
intellijPlatform {
  autoReload = false
}
```

It is also possible to disable it for a specific [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide)-based task as follows:

```kotlin
tasks {
  runIde {
    autoReload = false
  }
}
```

### How to disable building searchable options?

Building searchable options can be disabled using [`intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode):

```kotlin
intellijPlatform {
  buildSearchableOptions  = false
}
```

As a result of disabling building searchable options, the [Settings](settings.md) that your plugin provides won't be searchable in the <ui-path>Settings</ui-path> dialog.
Disabling of the task is suggested for plugins that are not intended to provide custom settings.

### How to show the log file of a sandbox instance?

The most convenient way to see the logs of a running IDE is to add a tab to the <control>Run</control> tool window displaying the contents of <path>idea.log</path> file.
In the Gradle `runIde` run configuration, add the log file path according to [sandbox location](ide_development_instance.md#the-development-instance-sandbox-directory) as described in [View logs](https://www.jetbrains.com/help/idea/setting-log-options.html).

### Task `setupDependencies` not found in root project 'projectName'

This exception is thrown when there's no `setupDependencies` task present in the project scope.

See [Migrating from Gradle IntelliJ Plugin](tools_intellij_platform_gradle_plugin_migration.md#setupdependencies) for more details.

### How to expose my plugin API sources to dependent plugins?

See the [](bundling_plugin_openapi_sources.md) section for details.

### JaCoCo reports 0% coverage

The Gradle IntelliJ Plugin, when targeting the IntelliJ SDK `2022.1+`, uses the `PathClassLoader` class loader by the following system property:

```
-Djava.system.class.loader=com.intellij.util.lang.PathClassLoader
```

Because of that, JaCoCo – and other external tools that rely on classes available in the bootstrap class loader – fail to discover plugin classes.

In addition, if the code instrumentation is enabled (see [`intellij.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode)), it's required to switch to the compiled and instrumented output instead of default compiled classes.

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


### Kotlin compiler throws `Out of memory. Java heap space` error

Please upgrade to Kotlin 1.9.0. See the [](using_kotlin.md#incremental-compilation) section if using Kotlin 1.8.20.


### How to check the latest available EAP release?

To list the IntelliJ Platform releases matching your criteria (IntelliJ Platform type, release channels, or build range), you may use the [](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases) task, as follows:

```kotlin
tasks {
  printProductsReleases {
    channels = listOf(ProductRelease.Channel.EAP)
    types = listOf(IntelliJPlatformType.IntellijIdeaCommunity)
    untilBuild = provider { null }

    doLast {
      val latestEap = productsReleases.get().max()
    }
  }
}
```

### The currently selected Java Runtime is not JetBrains Runtime (JBR)

When running tests or IDE with your plugin loaded, it is necessary to use JetBrains Runtime (JBR).
In the case, no JBR is found in the plugin configuration, there's the following warning logged by the [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration) task:


```
The currently selected Java Runtime is not JetBrains Runtime (JBR).
This may lead to unexpected IDE behaviors.
Please use IntelliJ Platform binary release with bundled JBR
or define it explicitly with project dependencies or JVM Toolchain.
```

To correctly run your tests or a specific IDE:
- use a binary IDE distribution with bundled JetBrains Runtime, i.e., by referring to a local IDE [`local(localPath)`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#custom-target-platforms)
  ```kotlin
  repositories {
    mavenCentral()

    intellijPlatform {
      defaultRepositories()
    }
  }

  dependencies {
    intellijPlatform {
      local("/Users/hsz/Applications/IntelliJ IDEA Ultimate.app")
    }
  }
  ```

- add an explicit dependency on a JetBrains Runtime with [`jetbrainsRuntime()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#java-runtime)
  ```kotlin
  repositories {
    mavenCentral()

    intellijPlatform {
      defaultRepositories()
      jetbrainsRuntime()
    }
  }

  dependencies {
    intellijPlatform {
      intellijIdeaCommunity("%ijPlatform%")
      jetbrainsRuntime(...)
    }
  }
  ```
- specify the vendor when configuring the JVM Toolchain along with [Foojay Toolchains Plugin](https://github.com/gradle/foojay-toolchains):
  ```kotlin
  kotlin {
    jvmToolchain {
      languageVersion = JavaLanguageVersion.of(17)
      vendor = JvmVendorSpec.JETBRAINS
    }
  }
  ```


<include from="snippets.md" element-id="missingContent"/>
