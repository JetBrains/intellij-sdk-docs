<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Testing Extension

<link-summary>IntelliJ Platform Gradle Plugin testing extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The _IntelliJ Platform Gradle Plugin_ introduces a top-level `intellijPlatformExtension` extension.
It provides a possibility for registering custom tasks for running the IDE, unit tests, UI tests, or performance tests.

For each of the custom tasks, a dedicated sandbox is created to isolate them form other tasks or the build flow as they may rely on a different IntelliJ Platform version, plugins, or other configuration.

## IntelliJ Platform Testing

{#intellijPlatformTesting}

After the IntelliJ Platform Gradle Plugin is [applied](tools_intellij_platform_gradle_plugin.md#setup), the `intellijPlatformTesting` extension can be used for registering new tasks to fulfil specific requirements of the project.

The extension exposes four `NamedDomainObjectContainers` which allow for creating new objects of given types.
Registering of a custom task which allows for adjusting the IntelliJ Platform type and version can be done by using one of the below containers, depending on the task purpose.

**Example:**

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatformTesting {
  runIde
  testIde
  testIdeUi
  testIdePerformance
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting {
  runIde
  testIde
  testIdeUi
  testIdePerformance
}
```

</tab>
</tabs>


By default, created tasks depend on the IntelliJ Platform defined with [](tools_intellij_platform_gradle_plugin_dependencies_extension.md).
However, it is possible to adjust it to any requirements with passing custom values directly to the created object, `task`, or `sandboxTask` task instances.

**Example:**

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runPhpStorm by intellijPlatformTesting.runIde.registering {
  type = IntelliJPlatformType.PhpStorm
  version = ...
  useInstaller = ...
  localPath = ...

  sandboxDirectory = ...

  splitMode = ...
  splitModeTarget = ...

  task {
    ...
  }

  prepareSandboxTask {
    ...
  }

  plugins {
    ...
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runPhpStorm {
    type = IntelliJPlatformType.PhpStorm
    version = ...
    useInstaller = ...
    localPath = ...

    sandboxDirectory = ...

    splitMode = ...
    splitModeTarget = ...

    task {
      ...
    }

    prepareSandboxTask {
      ...
    }

    plugins {
      ...
    }
  }
}
```

</tab>
</tabs>

### `task {}`

Depending on the type of registered object, a different `task` class is available for configuration:

| Type                 | Task class                                                                                    |
|----------------------|-----------------------------------------------------------------------------------------------|
| `runIde`             | [`RunIdeTask`](tools_intellij_platform_gradle_plugin_tasks.md#runIde)                         |
| `testIde`            | [`TestIdeTask`](tools_intellij_platform_gradle_plugin_tasks.md#testIde)                       |
| `testIdeUi`          | [`TestIdeUiTask`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi)                   |
| `testIdePerformance` | [`TestIdePerformanceTask`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance) |

### `prepareSandboxTask {}`

The `prepareSandboxTask` refers to a dedicated [`PrepareSandboxTask`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) task instance, connected only with a newly created task.
The name of this task is based on the name of created task, like `prepareSandbox_[TASK_NAME]`.

### `plugins {}`

An extension to provide custom plugins to be added when running the task runtime, or disabling bundled ones.

It provides several methods for adding remote and local plugins, or for disabling already loaded or bundled plugin.

**Example:**

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
  val runIdeWithPlugins by intellijPlatformTesting.runIde.registering {
  // ...
  plugins {
    plugin("pluginId", "1.0.0")
    disablePlugin("pluginId")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runIdeWithPlugins {
    // ...
    plugins {
      plugin("pluginId", "1.0.0")
      disablePlugin("pluginId")
    }
  }
}
```

</tab>
</tabs>

| Function                       | Description                                                                                                                                  |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| `plugin(id, version, channel)` | Adds a dependency on a plugin for a custom IntelliJ Platform.                                                                                |
| `plugin(notation)`             | Adds a dependency on a plugin for a custom IntelliJ Platform using a string notation:<p>`pluginId:version` or `pluginId:version@channel`</p> |
| `plugins(notations)`           | Adds dependencies on plugins for a custom IntelliJ Platform using a string notation:<p>`pluginId:version` or `pluginId:version@channel`</p>  |
| `disablePlugin(id)`            | Disables the specific plugin with its ID.                                                                                                    |
| `disablePlugins(ids)`          | Disables specific plugins with the list of their IDs.                                                                                        |
| `localPlugin(path)`            | Adds a dependency on a local IntelliJ Platform plugin. Accepts path or a dependency on another module.                                       |
| `robotServerPlugin(version)`   | Adds a dependency on a Robot Server Plugin.                                                                                                  |

<include from="snippets.topic" element-id="missingContent"/>
