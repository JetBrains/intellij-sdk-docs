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

### Kotlin compiler throws `Out of memory. Java heap space` error

Please upgrade to Kotlin 1.9.0. See the [](using_kotlin.md#incremental-compilation) section if using Kotlin 1.8.20.

<include from="snippets.md" element-id="missingContent"/>
