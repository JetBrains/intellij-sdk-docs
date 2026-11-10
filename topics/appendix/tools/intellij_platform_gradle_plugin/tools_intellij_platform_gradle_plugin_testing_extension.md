<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Testing Extension

<link-summary>IntelliJ Platform Gradle Plugin testing extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The _IntelliJ Platform Gradle Plugin_ introduces a top-level `intellijPlatformTesting` extension.
It provides a possibility for registering custom tasks for running the IDE, unit tests, UI tests, or performance tests.

For each of the custom tasks, a dedicated sandbox is created to isolate them from other tasks or the build flow as they may rely on a different IntelliJ Platform version, plugins, or other configuration.

## IntelliJ Platform Testing

{#intellijPlatformTesting}

After the IntelliJ Platform Gradle Plugin is [applied](tools_intellij_platform_gradle_plugin.md#setup), the `intellijPlatformTesting` extension can be used for registering new tasks to fulfil specific requirements of the project.

The extension exposes four `NamedDomainObjectContainer` instances for registering task configurations.
Registering an entry creates an execution task with the entry's name and a sandbox preparation task named `prepareSandbox_<name>`.
The container names describe task types, not additional tasks created merely by accessing the containers.

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
Configure the entry directly to select another IDE, and use `task {}` or `prepareSandboxTask {}` to customize its execution or sandbox preparation.

Each registered entry extends the [IntelliJ Platform dependency configuration model](tools_intellij_platform_gradle_plugin_dependencies_extension.md#dependency-configuration-parameters), so properties like `type`, `version`, `productMode`, `useInstaller`, and `useCache` can be configured directly on the entry.
The additional `localPath` property selects a local IDE installation and takes precedence over a remote request.
Without `localPath`, explicitly setting `type`, `version`, or `useInstaller` selects a custom remote IDE even if the project's base IDE is local.
Otherwise, the base local IDE is reused when one is configured.
The `version` accepts `"latest"` (or `Constraints.LATEST_VERSION`) to resolve the [newest available installer release](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-versions-latest) for the configured `type`.

In projects using the [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin, this extension can register custom `testIde` tasks only.
Register custom `runIde`, `testIdeUi`, and `testIdePerformance` tasks in the root project using the [](tools_intellij_platform_gradle_plugin_plugins.md#platform) plugin.

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
  pluginInstallationTarget = ...

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
    pluginInstallationTarget = ...

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

### Common Properties
{#intellijPlatformTesting-properties}

| Property | Type | Default or behavior |
|----------|------|---------------------|
| `name` | `String` (read-only) | The registered entry's name, also used for the execution task. |
| `localPath` | `DirectoryProperty` | Unset. Selects a local IDE instead of downloading one. |
| `sandboxDirectory` | `DirectoryProperty` | Inherits the base `prepareSandbox` task's sandbox directory. |
| `splitMode` | `Property<Boolean>` | Inherits the base `prepareSandbox` task's split-mode setting. |
| `pluginInstallationTarget` | `Property<PluginInstallationTarget>` | Inherits the base `prepareSandbox` task's installation target; the effective default is `BACKEND`. |
| `splitModeTarget` | `Property<SplitModeTarget>` | Deprecated compatibility alias for `pluginInstallationTarget`. |

Each entry implements `Buildable` and can be passed directly to `dependsOn()` to schedule its execution task.
The `task` and `prepareSandboxTask` properties provide read-only access to the corresponding `TaskProvider` instances.

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
The execution task uses this task's sandbox automatically.
For `runIde` entries, the sandbox also includes the current host's [native variant](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants) when native variants are enabled.
Custom `testIde`, `testIdeUi`, and `testIdePerformance` entries do not select a native variant automatically.

### `testFramework(...)`

Adds a dependency on a `test-framework` library variant for the created testing entry.
The version can be provided as a string or as a `Provider<String>`.
When omitted, the closest version matching the configured IntelliJ Platform build is used.
This is the entry's target platform build, which can differ from the project's base IntelliJ Platform.

See also:
- [Types: `TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType)
- [Dependencies Extension: Testing](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing)

### `testFrameworks(...)`

Adds dependencies on multiple `test-framework` library variants for the created testing entry.
It accepts either vararg `TestFrameworkType` arguments or a `List<TestFrameworkType>`.
The closest version matching the configured IntelliJ Platform build is selected for every variant.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

val platformTests by intellijPlatformTesting.testIde.registering {
  testFrameworks(
    TestFrameworkType.Platform,
    TestFrameworkType.Plugin.Java,
  )
  testFrameworks(
    listOf(
      TestFrameworkType.JUnit5,
      TestFrameworkType.Plugin.Maven,
    )
  )
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

intellijPlatformTesting.testIde {
  platformTests {
    testFrameworks(
      TestFrameworkType.Platform.INSTANCE,
      TestFrameworkType.Plugin.Java.INSTANCE
    )
    testFrameworks([
      TestFrameworkType.JUnit5.INSTANCE,
      TestFrameworkType.Plugin.Maven.INSTANCE
    ])
  }
}
```

</tab>
</tabs>

The plural overloads do not accept an explicit version.
To pin a specific version, call `testFramework(type, version)` separately.

See also:
- [Types: `TestFrameworkType`](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType)
- [Dependencies Extension: Testing](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing)

### `plugins {}`

Configures plugins for the registered entry's IDE and test classpaths and sandbox.

Remote and local plugin dependencies from the project's test plugin configurations are inherited.
Bundled plugin and module helpers resolve against the entry's target IDE.
Plugins can also be disabled in the entry's sandbox.

**Example:**

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runIdeWithPlugins by intellijPlatformTesting.runIde.registering {
  // ...
  plugins {
    plugin("pluginId", "1.0.0") {
      excludeBundledLibrary("jena-*.jar")
      excludeBundledLibraries("legacy-*.jar", "unused-*.jar")
    }
    disablePlugin("bundledPluginId")
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
      plugin('pluginId', '1.0.0', '') {
        excludeBundledLibrary('jena-*.jar')
        excludeBundledLibraries('legacy-*.jar', 'unused-*.jar')
      }
      disablePlugin('bundledPluginId')
    }
  }
}
```

</tab>
</tabs>

| Function                       | Description                                                                                                                                  |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| `plugin(id, version, channel, configure)` | Adds a plugin dependency. The channel and configuration block are optional. Supply the ID, version, and channel consistently as strings or providers of strings. |
| `plugin(notation, configure)`  | Adds a plugin dependency using `pluginId:version` or `pluginId:version@channel`, as a string or provider. The configuration block is optional. |
| `plugins(notations)`           | Adds dependencies on plugins for a custom IntelliJ Platform using a string notation:<p>`pluginId:version` or `pluginId:version@channel`</p>  |
| `compatiblePlugin(id)`         | Adds a dependency on a plugin in a version compatible with the custom IntelliJ Platform.                                                     |
| `compatiblePlugins(ids)`       | Adds dependencies on plugins in versions compatible with the custom IntelliJ Platform.                                                       |
| `bundledPlugin(id)`            | Adds a dependency on a bundled IntelliJ Platform plugin from the custom target IDE.                                                          |
| `bundledPlugins(ids)`          | Adds dependencies on bundled IntelliJ Platform plugins from the custom target IDE.                                                           |
| `bundledModule(id)`            | Adds a dependency on a bundled IntelliJ Platform module or module alias from the custom target IDE.                                         |
| `bundledModules(ids)`          | Adds dependencies on bundled IntelliJ Platform modules or module aliases from the custom target IDE.                                        |
| `disablePlugin(id)`            | Disables the specific plugin with its ID.                                                                                                    |
| `disablePlugins(ids)`          | Disables specific plugins with the list of their IDs.                                                                                        |
| `localPlugin(path)`            | Adds a dependency on a local IntelliJ Platform plugin. Accepts path or a dependency on another module.                                       |
| `robotServerPlugin(version)`   | Adds a Robot Server Plugin dependency. Accepts a string or provider; defaults to the latest version.                                          |

`plugins()`, `compatiblePlugins()`, `bundledPlugins()`, `bundledModules()`, and `disablePlugins()` accept vararg strings, a `List<String>`, or a `Provider<List<String>>`.
The singular `compatiblePlugin()`, `bundledPlugin()`, `bundledModule()`, and `disablePlugin()` methods also accept `Provider<String>` arguments.
`localPlugin()` accepts a `String`, `File`, `Directory`, a provider of a local path, or a `ProjectDependency`.

#### Bundled Library Exclusions
{#intellijPlatformTesting-plugins-exclusions}

The optional `plugin()` configuration block exposes [`IntelliJPlatformPluginConfiguration`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/extensions/IntelliJPlatformPluginConfiguration.kt):

| Function | Description |
|----------|-------------|
| `excludeBundledLibrary(pattern)` | Excludes files matching one Gradle Ant-style pattern. |
| `excludeBundledLibraries(patterns)` | Excludes files matching any of the supplied vararg patterns. |

Patterns are relative to the dependency plugin's <path>lib/</path> and <path>lib/modules/</path> directories.
Matching files are excluded from project classpaths and sandbox installations.
Patterns declared for the same plugin ID are combined build-wide, rather than being isolated to this testing entry.
To configure exclusions for several plugins, use separate `plugin()` calls; the plural `plugins()` helper does not accept a configuration block.
With Groovy, the ID/version overload requires an explicit channel argument when supplying a configuration closure; use `''` for the default channel.

<include from="snippets.topic" element-id="missingContent"/>
