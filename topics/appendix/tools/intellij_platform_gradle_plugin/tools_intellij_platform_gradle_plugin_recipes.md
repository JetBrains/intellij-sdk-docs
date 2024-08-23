<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Recipes

<link-summary>Recipes for solving particular tasks with IntelliJ Platform Gradle Plugin</link-summary>

## Run a custom task with customized sandbox location

To create a custom task with the sandbox directory specified outside of the default <path>build/idea-sandbox/[TYPE]-[VERSION]/</path> location, pass the new location to its `prepareSandboxTask` sandbox producer configuration:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runWithCustomSandbox by intellijPlatformTesting.runIde.registering {
  // ...

  prepareSandboxTask {
    sandboxDirectory = project.layout.buildDirectory.dir("custom-sandbox")
    sandboxSuffix = ""
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runWithCustomSandbox {
    // ...

    prepareSandboxTask {
      sandboxDirectory = project.layout.buildDirectory.dir('custom-sandbox')
      sandboxSuffix = ''
    }
  }
}
```

</tab>
</tabs>


This will result in the following sandbox structure:

```
build/
├── custom-sandbox
│   ├── config
│   ├── log
│   ├── plugins
│   └── system
...
```

## Access IntelliJ Platform from any Gradle task

With [](tools_intellij_platform_gradle_plugin_task_awares.md) it is possible to enhance any Gradle task with features provided with the IntelliJ Platform Gradle Plugin.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.aware.IntelliJPlatformVersionAware

abstract class MyTask : DefaultTask(), IntelliJPlatformVersionAware

val myTask by tasks.registering(MyTask::class) {
  doLast {
    println("platformPath = \n${platformPath}")
    println("productInfo.buildNumber = ${productInfo.buildNumber}")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.aware.IntelliJPlatformVersionAware

abstract class MyTask extends DefaultTask implements IntelliJPlatformVersionAware {}

tasks.register('myTask', MyTask) {
  doLast {
    println("platformPath = \n${platformPath}")
    println("productInfo.buildNumber = ${productInfo.buildNumber}")
  }
}
```

</tab>
</tabs>


As soon as the registered task inherits from the `*Aware` interface, such as [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), all the related information will be injected during the configuration phase.


## Bundle additional files with the plugin

Additional files can be bundled by adding them to the plugin directory when preparing the sandbox:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  prepareSandbox {
    from(layout.projectDirectory.dir("extraFiles")) {
      into(pluginName.map { "$it/extra" })
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks.named('prepareSandbox', PrepareSandboxTask) {
  from layout.projectDirectory.dir('extraFiles')
  into it.pluginName.map { "$it/extra" }
}
```

</tab>
</tabs>


To apply that to the custom task, use the [`prepareSandboxTask`](tools_intellij_platform_gradle_plugin_testing_extension.md#preparesandboxtask) reference:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runWithCustomSandbox by intellijPlatformTesting.runIde.registering {
  // ...

  prepareSandboxTask {
    from(...) {
      into(...)
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runWithCustomSandbox {
    // ...

    prepareSandboxTask {
      from(...)
      into(...)
    }
  }
}
```

</tab>
</tabs>


It is possible to apply that to all [`PrepareSandboxTask`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) with:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  withType<PrepareSandboxTask> {
    from(...) {
      into(...)
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks {
  withType(PrepareSandboxTask) {
    from(...)
    into(...)
  }
}
```

</tab>
</tabs>


## ProGuard configuration

To configure [ProGuard](https://github.com/Guardsquare/proguard), intercept the [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) task and replace the plugin Jar archive produced by the [`composedJar`](tools_intellij_platform_gradle_plugin_tasks.md#composedJar) task with the obfuscated/minified file.

First, define the ProGuard Jar archive location and store it inside the <path>build/libs/</path> directory.
Pass the output ProGuard location to the `outjars()` helper and use it as a new sandbox input for [`prepareSandbox.pluginJar`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox-pluginJar).

Finally, pass the [`composedJar.archiveFile`](tools_intellij_platform_gradle_plugin_tasks.md#composedJar-archiveFile) to the `injars()` helper.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import proguard.gradle.ProGuardTask

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("com.guardsquare:proguard-gradle:7.5")
  }
}

tasks {
  val proguardJar = layout.buildDirectory.file("libs/$name-$version-proguard.jar")

  val proguard by registering(ProGuardTask::class) {
    injars(composedJar.map { it.archiveFile })
    outjars(proguardJar)

    // ...
  }

  prepareSandbox {
    pluginJar = proguardJar
    dependsOn(proguard)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import proguard.gradle.ProGuardTask

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("com.guardsquare:proguard-gradle:7.5")
  }
}

def proguardJar = layout.buildDirectory.file("libs/$name-$version-proguard.jar")

tasks.register('proguard', ProGuardTask) {
  it.injars(composedJar.archiveFile)
  outjars(proguardJar)

  // ...
}

prepareSandbox {
  pluginJar = proguardJar
  dependsOn(proguard)
}
```

</tab>
</tabs>
