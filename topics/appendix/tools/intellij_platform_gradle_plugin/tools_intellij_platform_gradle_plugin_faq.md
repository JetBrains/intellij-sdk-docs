<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Gradle Plugin – FAQ

<link-summary>FAQ for using IntelliJ Platform Gradle Plugin (2.x)</link-summary>

### How to modify system properties of the `runIde` task?

Using the [very same task documentation](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html), configure [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    jvmArgumentProviders += CommandLineArgumentProvider {
      listOf("-Dname=value")
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks {
  runIde {
    jvmArgumentProviders.add({
      ["-Dname=value"]
    } as CommandLineArgumentProvider)
  }
}

```

</tab>
</tabs>


### Task `runIdeForUiTests` not found

The [`runIdeForUiTests`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeForUiTests) is no longer registered by default.
Follow the task documentation for more details.

### Missing `opentest4j` dependency in Test Framework

Due to the [IJPL-157292](https://youtrack.jetbrains.com/issue/IJPL-157292/lib-testFramework.jar-is-missing-library-opentest4j) issue, the `opentest4j` dependency is not resolved when using `TestFrameworkType.Platform` or `TestFrameworkType.JUnit5`.

This results in the `NoClassDefFoundError` exception:

```
java.lang.NoClassDefFoundError: org/opentest4j/AssertionFailedError
```

To apply the workaround, add the missing `org.opentest4j:opentest4j` test dependency to your Gradle build configuration:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  // ...
  testImplementation("org.opentest4j:opentest4j:1.3.0")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  // ...
  testImplementation 'org.opentest4j:opentest4j:1.3.0'
}
```

</tab>
</tabs>


### JUnit5 Test Framework refers to JUnit4

Due to the [IJPL-159134](https://youtrack.jetbrains.com/issue/IJPL-159134/JUnit5-Test-Framework-refers-to-JUnit4-java.lang.NoClassDefFoundError-junit-framework-TestCase) issue, the JUnit5 Test Framework refers to JUnit4 classes when running test.

This results in the `NoClassDefFoundError` exceptions:

```
Caused by: java.lang.NoClassDefFoundError: junit/framework/TestCase

Caused by: java.lang.NoClassDefFoundError: org/junit/rules/TestRule
```

To apply the workaround, add the JUnit4 test runtime dependency to your Gradle build configuration:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  // ...
  testRuntimeOnly("junit:junit:4.13.2")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  // ...
  testRuntimeOnly 'junit:junit:4.13.2'
}
```

</tab>
</tabs>


### Dependency on bundled plugin is not resolved after migrating from 1.x

{id="migrateToPluginId"}

Using [](tools_gradle_intellij_plugin.md), specifying the _directory name_ of the plugin was possible when adding a dependency on a bundled plugin
for compatibility reasons.
Now, using the actual plugin ID is required.
For example, provide plugin ID `com.intellij.java` instead of its directory name `java`.
A migration hint is provided for this and some other commonly used cases.

See [](plugin_dependencies.md#bundled-and-other-plugins) on how to get all bundled plugin IDs as well as a list of some commonly used ones.

### How to disable the automatic reload of dynamic plugins?

See [](ide_development_instance.md#enabling-auto-reload) for important caveats.

Disable auto-reload globally with [`intellijPlatform.autoReload`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-autoReload):

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  autoReload = false
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  autoReload = false
}
```

</tab>
</tabs>


It is also possible to disable it for a specific [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde)-based task as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    autoReload = false
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks {
  runIde {
    autoReload = false
  }
}
```

</tab>
</tabs>


### How to disable building the searchable options?

Building the searchable options can be disabled using [`intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode):

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  buildSearchableOptions = false
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  buildSearchableOptions = false
}
```

</tab>
</tabs>


As a result of disabling building searchable options, the [Settings](settings.md) that your plugin provides won't be searchable in the <ui-path>Settings</ui-path> dialog.
Disabling of the task is suggested for plugins that are not intended to provide custom settings.

### Gradle fails with `The request for this plugin could not be satisfied`

Gradle may fail with the following exception if the IntelliJ Platform Gradle Plugin is applied to the project multiple times with version specified more than once:

`The request for this plugin could not be satisfied because the plugin is already on the classpath with an unknown version, so compatibility cannot be checked.`

When applying the plugin in the <path>settings.gradle.kts</path> file, the version needs to be omitted when applying it in other <path>build.gradle.kts</path> files.

### How to show the log file of a sandbox instance?

The most convenient way to see the logs of a running IDE is to add a tab to the <control>Run</control> tool window displaying the contents of <path>idea.log</path> file.
In the Gradle `runIde` run configuration, add the log file path according to [sandbox location](ide_development_instance.md#the-development-instance-sandbox-directory) as described in [View logs](https://www.jetbrains.com/help/idea/setting-log-options.html).

### Task `setupDependencies` not found in root project 'projectName'

This exception is thrown when there's no `setupDependencies` task present in the project scope.

See [Migrating from Gradle IntelliJ Plugin](tools_intellij_platform_gradle_plugin_migration.md#setupdependencies) for more details.

### How to expose my plugin API sources to dependent plugins?

See the [](bundling_plugin_openapi_sources.md) section for details.

### How to mute specific problems in `pluginVerification`?

{id="mutePluginVerifierProblems"}

To mute specific problems (for example, use of specific forbidden words in the plugin name), use the [`freeArgs`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-freeArgs) parameter to pass a comma-separated list of problem IDs to be muted.

See the list of [available options](https://github.com/JetBrains/intellij-plugin-verifier/?tab=readme-ov-file#specific-options).

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  pluginVerification {
    // ...
    freeArgs = listOf(
      "-mute",
      "TemplateWordInPluginId,ForbiddenPluginIdPrefix"
    )
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  pluginVerification {
    // ...
    freeArgs = [
      "-mute",
      "TemplateWordInPluginId,ForbiddenPluginIdPrefix",
    ]
  }
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

To list the IntelliJ Platform releases matching your criteria (IntelliJ Platform type, release channels, or build range),
use the [](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases) task as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease

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

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease

tasks {
  printProductsReleases {
    channels = [ProductRelease.Channel.EAP]
    types = [IntelliJPlatformType.IntellijIdeaCommunity]
    untilBuild = null

    doLast {
      def latestEap = productsReleases.get().max()
    }
  }
}
```

</tab>
</tabs>


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
  <tabs group="languages">
  <tab title="Kotlin" group-key="kotlin">

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

  </tab>
  <tab title="Groovy" group-key="groovy">

  ```groovy
    repositories {
      mavenCentral()

      intellijPlatform {
        defaultRepositories()
      }
    }

    dependencies {
      intellijPlatform {
        local '/Users/hsz/Applications/IntelliJ IDEA Ultimate.app'
      }
    }
    ```

  </tab>
  </tabs>

- add an explicit dependency on a JetBrains Runtime with [`jetbrainsRuntime()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#java-runtime)
  <tabs group="languages">
  <tab title="Kotlin" group-key="kotlin">

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
      jetbrainsRuntime("...")
    }
  }
  ```

  </tab>
  <tab title="Groovy" group-key="groovy">

  ```groovy
    repositories {
      mavenCentral()

      intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
      }
    }

    dependencies {
      intellijPlatform {
        intellijIdeaCommunity '%ijPlatform%'
        jetbrainsRuntime '...'
      }
    }
    ```

  </tab>
  </tabs>

- specify the vendor when configuring the JVM Toolchain along with [Foojay Toolchains Plugin](https://github.com/gradle/foojay-toolchains):
  <tabs group="languages">
  <tab title="Kotlin" group-key="kotlin">

  ```kotlin
  kotlin {
    jvmToolchain {
      languageVersion = JavaLanguageVersion.of(17)
      vendor = JvmVendorSpec.JETBRAINS
    }
  }
  ```

  </tab>
  <tab title="Groovy" group-key="groovy">

  ```groovy
    java {
      toolchain {
        languageVersion = JavaLanguageVersion.of(17)
        vendor = JvmVendorSpec.JETBRAINS
      }
    }
    ```

  </tab>
  </tabs>

### plugin.xml: `Cannot resolve plugin com.intellij.modules.vcs`

Upgrade to the latest version of the IntelliJ Platform Gradle Plugin.

<include from="snippets.topic" element-id="missingContent"/>
