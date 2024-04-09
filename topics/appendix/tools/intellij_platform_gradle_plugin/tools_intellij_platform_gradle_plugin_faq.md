<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle IntelliJ Plugin – FAQ

<link-summary>FAQ for using IntelliJ Platform Gradle Plugin</link-summary>

### How to modify JVM arguments of runIde task?

[`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task is a [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) task and can be modified according to the documentation.

To add some JVM arguments while launching the IDE, configure [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task as follows:

```kotlin
tasks {
  runIde {
    jvmArgs("-DmyProperty=value")
  }
}
```

### How to modify system properties of runIde task?

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

It is also possible to disable it for specific [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide)-base task as follows:

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

### How to show log file of sandbox instance?

The most convenient way to see the logs of a running IDE is to add a tab to the <control>Run</control> tool window displaying the contents of <path>idea.log</path> file.
In the Gradle `runIde` run configuration, add the log file path according to [sandbox location](ide_development_instance.md#the-development-instance-sandbox-directory) as described in [View logs](https://www.jetbrains.com/help/idea/setting-log-options.html).

### Task `setupDependencies` not found in root project

The [`setupDependencies`](tools_gradle_intellij_plugin.md#tasks-setupdependencies) task was designed to fetch the target IntelliJ Platform dependency in the after-sync Gradle phase as a workaround for the Gradle IntelliJ Plugin `1.x` limitations.
Starting with the IntelliJ Platform Gradle Plugin `2.0`, this task is no longer needed and was removed from available tasks.

Unfortunately, this entry may still remain right after the migration to `2.0` and cause the following exception:

```
Task 'setupDependencies' not found in root project 'projectName'.
```

There are two possible solutions:
- manually edit the <path>.idea/workspace.xml</path> file removing the `setupDependencies` entry
- open the <control>Gradle</control> tool window, select the <ui-path>Tasks Activation</ui-path> action from the context menu of the root project item, and remove the `setupDependencies` entry

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

<include from="snippets.md" element-id="missingContent"/>
