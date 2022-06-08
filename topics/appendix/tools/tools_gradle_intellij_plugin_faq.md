[//]: # (title: Gradle IntelliJ Plugin – FAQ)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Frequently Asked Questions

### How to modify JVM arguments of runIde task
[`runIde`](tools_gradle_intellij_plugin.md#runide-task) task is a [Java Exec](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) task and can be modified according to the documentation.

To add some JVM arguments while launching the IDE, configure [`runIde`](tools_gradle_intellij_plugin.md#runide-task) task as follows:

<tabs>
<tab title="Kotlin">

```kotlin
tasks {
    runIde {
        jvmArgs("-DmyProperty=value")
    }
}
```

</tab>
<tab title="Groovy">

```groovy
runIde {
    jvmArgs "-DmyProperty=value"
}
```

</tab>
</tabs>


### How to modify system properties of runIde task
Using the [very same task documentation](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html), configure [`runIde`](tools_gradle_intellij_plugin.md#runide-task) task:

<tabs>
<tab title="Kotlin">

```kotlin
tasks {
    runIde {
       systemProperty("name", "value")
    }
}
```

</tab>
<tab title="Groovy">

```groovy
runIde {
    systemProperty("name", "value")
}
```

</tab>
</tabs>


### How to disable automatic reload of dynamic plugins
Configure [`runIde`](tools_gradle_intellij_plugin.md#runide-task) task as follows:

<tabs>
<tab title="Kotlin">

```kotlin
tasks {
    runIde {
        autoReloadPlugins.set(false)
    }
}
```

</tab>
<tab title="Groovy">

```groovy
runIde {
    autoReloadPlugins = false
}
```

</tab>
</tabs>


### How to disable building searchable options
Building searchable options can be disabled as a task:

<tabs>
<tab title="Kotlin">

```kotlin
tasks {
    buildSearchableOptions {
        enabled = false
    }
}
```

</tab>
<tab title="Groovy">

```groovy
buildSearchableOptions.enabled = false
```

</tab>
</tabs>

As a result of disabling building searchable options, the configurables that your plugin provides won't be searchable in the Settings dialog.


### How do I add my a custom file inside plugin distribution
[`prepareSandbox`](tools_gradle_intellij_plugin.md#preparesandbox-task) task is a [`Sync`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html) task and can be modified accordingly.
Something like following should work:

<tabs>
<tab title="Kotlin">

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
<tab title="Groovy">

```groovy
prepareSandbox {
    from("yourFile") {
        into "${intellij.pluginName.get()}/lib/"
    }
}
```

</tab>
</tabs>


### Task 'setupDependencies' not found in root project
The [`setupDependencies`](tools_gradle_intellij_plugin.md#setupdependencies-task) task is designed to fetch the target IDE dependency from the IntelliJ Repository in the after-sync Gradle phase, but only when working in the IntelliJ IDEA – to make the IntelliJ SDK classes resolved and code completion available.
To achieve that, the [`gradle-idea-ext-plugin`](https://github.com/JetBrains/gradle-idea-ext-plugin) is used, which alters the IDEA project's <path>.idea/workspace.xml</path> file making the [`setupDependencies`](tools_gradle_intellij_plugin.md#setupdependencies-task) task activated on `after_sync` event.

Unfortunately, this entry remains even after you disable the `org.jetbrains.intellij` in the project – the [`setupDependencies`](tools_gradle_intellij_plugin.md#setupdependencies-task) task won't be resolved appropriately, which produces the following exception:

```
Task 'setupDependencies' not found in root project 'projectName'.
```

To fix that, manually edit the <path>.idea/workspace.xml</path> file removing mentioned entry, go to the Gradle ToolWindow, select the Task Activation action from the context menu of the root project item, and remove it.
