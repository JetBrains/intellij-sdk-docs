<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Custom Tasks

<link-summary>IntelliJ Platform Gradle Plugin custom tasks.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="Beta_Status"/>
<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

By default, the [](tools_intellij_platform_gradle_plugin_tasks.md#runIde), `test`, [](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi), and [](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance) tasks depend on the IntelliJ Platform defined with [](tools_intellij_platform_gradle_plugin_dependencies_extension.md).

The IntelliJ Platform Gradle Plugin allows also for introducing custom tasks dedicated to running or testing your plugin using a custom IntelliJ Platform.

Registering of a custom task which allows for adjusting the IntelliJ Platform type and version can be done by using one of the below `Custom*Task` classes, depending on the task purpose.


## `CustomRunIdeTask`
{#CustomRunIdeTask}

<tldr>

**Extends**: [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware)

**Sources**: [`CustomRunIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/CustomRunIdeTask.kt)

</tldr>

Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin loaded.

It directly extends the [`JavaExec`][gradle-javaexec-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

This task class also inherits from [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware), which makes it possible to create `runIde`-like tasks using custom IntelliJ Platform versions:

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.tasks.CustomRunIdeTask

tasks {
  val runPhpStorm by registering(CustomRunIdeTask::class) {
    type = IntelliJPlatformType.PhpStorm
    version = "2023.2.2"
  }

  val runWithScalaPlugin by registering(CustomRunIdeTask::class) {
    plugins {
      plugin("org.intellij.scala", "2023.3.29")
    }
  }

  val runWithoutGitHubPlugin by registering(CustomRunIdeTask::class) {
    plugins {
      disablePlugin("org.jetbrains.plugins.github")
    }
  }

  val runLocalIde by registering(CustomRunIdeTask::class) {
    localPath = file("/Users/user/Applications/Android Studio.app")
  }
}
```


## `CustomTestIdeTask`
{#CustomTestIdeTask}

<tldr>

**Extends**: [`test`][gradle-test-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware), [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware)

**Sources**: [`CustomTestIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/CustomTestIdeTask.kt)

</tldr>

Runs plugin tests against the currently selected IntelliJ Platform with the built plugin loaded.

It directly extends the [`Test`][gradle-test-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

This task class also inherits from [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware), which makes it possible to create `runIde`-like tasks using custom IntelliJ Platform versions:

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.tasks.CustomTestIdeTask

tasks {
  val testPhpStorm by registering(CustomTestIdeTask::class) {
    type = IntelliJPlatformType.PhpStorm
    version = "2023.2.2"
  }

  val testLocalIde by registering(CustomTestIdeTask::class) {
    localPath = file("/Users/hsz/Applications/Android Studio.app")
  }
}
```


## `CustomTestIdeUiTask`
{#CustomTestIdeUiTask}

<tldr>

**Extends**: [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi), [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware)

**Sources**: [`CustomTestIdeUiTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/CustomTestIdeUiTask.kt)

</tldr>

> Not implemented.
>
{style="warning"}


## `CustomTestIdePerformanceTask`
{#CustomTestIdePerformanceTask}

<tldr>

**Extends**: [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance), [`CustomIntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CustomIntelliJPlatformVersionAware)

**Sources**: [`CustomTestIdePerformanceTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/CustomTestIdePerformanceTask.kt)

</tldr>

> Not implemented.
>
{style="warning"}


<include from="snippets.md" element-id="missingContent"/>

[gradle-javaexec-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html
[gradle-test-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html
