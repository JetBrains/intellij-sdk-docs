<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Configuring a Plugin for Split Mode

<link-summary>Configure Gradle so a plugin can run, debug, and test with separate frontend and backend processes.</link-summary>

> Plugins created using the [IDE Plugin generator](creating_plugin_project.md#create-ide-plugin) with the <control>Split Mode (Remote Dev)</control> feature enabled are configured for split mode by default.

Split Mode configuration in the Gradle build script allows for running development sandbox IDEs in mode emulating the [remote development](split_mode_and_remote_development.md) scenario, with both frontend and backend processes running locally.
To make a plugin work natively in split mode, see [plugin modularization](modular_plugins.md).

## Basic Configuration

> Split Mode requires [IntelliJ Platform Gradle Plugin **2.x**](tools_intellij_platform_gradle_plugin.md).
>
{style="warning"}

Enable Split Mode in the `intellijPlatform {}` extension:

```kotlin
intellijPlatform {
  splitMode = true
  pluginInstallationTarget = SplitModeAware.PluginInstallationTarget.BOTH
}
```

The two relevant properties are:

- [`splitMode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-splitMode) – starts separate frontend and backend processes
- [`pluginInstallationTarget`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginInstallationTarget) – selects where the plugin is installed

## Choosing the Installation Target

Choose `pluginInstallationTarget` according to the code being exercised:

- `BACKEND` for backend-only functionality
- `FRONTEND` for frontend-only functionality
- `BOTH` for typical modular plugins that contain frontend, backend, and shared modules

For split plugin development, `BOTH` is the most common choice.

> `pluginInstallationTarget` only controls where the plugin is placed in locally run development sandboxes.
> It doesn't describe end-user installation or synchronization behavior in split mode.
> See [Plugin Management](plugin_management_in_split_mode.md).
>
{style="note"}

## Custom Run and Test Tasks

Custom split-mode tasks can be declared with `intellijPlatformTesting`:

```kotlin
val runIdeSplitMode by intellijPlatformTesting.runIde.registering {
  splitMode = true
  pluginInstallationTarget = SplitModeAware.PluginInstallationTarget.BOTH
}

val testIdeUiSplitMode by intellijPlatformTesting.testIdeUi.registering {
  splitMode = true
  pluginInstallationTarget = SplitModeAware.PluginInstallationTarget.BOTH
}
```

These tasks get dedicated sandboxes and can be used like other development or test tasks.
See [](tools_intellij_platform_gradle_plugin_testing_extension.md) for more details.

## Configuring Debug and Trace Logs

Starting with IntelliJ Platform Gradle Plugin 2.15.0, Split Mode provides dedicated `runIdeBackend` and `runIdeFrontend` tasks, so additional log categories can be enabled separately for each process.

```kotlin
tasks {
  // Set up the additional debug/trace log categories if needed.
  runIdeBackend {
    systemProperty("idea.log.debug.categories", "com.example.plugin.backend")
  }
  runIdeFrontend {
    systemProperty("idea.log.trace.categories", "com.example.plugin.frontend")
  }
}
```

Set `idea.log.debug.categories` or `idea.log.trace.categories` to the package or logger category names that should be enabled in the corresponding IDE process.
Use a comma-separated list to enable multiple categories.
If needed, either property can be applied to either task.

## Typical Next Step

After the Gradle configuration is in place, the next step is deciding how the plugin code should be distributed between frontend, backend, and shared modules.
See [](split_mode_feature_development.md).

## Required Gradle Plugin and Library Versions

The following tables list recommended versions of Gradle plugins and libraries for split mode development, tailored for compatibility with the specified IntelliJ Platform versions.

### Gradle Plugins

| **IntelliJ Platform** | `rpc`          | `org.jetbrains.kotlin.plugin.serialization` |
|-----------------------|----------------|---------------------------------------------|
| **2026.3**            | 2.4.0-RC-0.1   | 2.4.0                                       |
| **2026.2**            | 2.4.0-RC-0.1   | 2.4.0                                       |
| **2026.1**            | 2.3.20-RC2-0.1 | 2.3.20                                      |
| **2025.3**            | 2.1.20-0.1     | 2.1.20                                      |

{style=header-column}


### Libraries

| **IntelliJ Platform** | `kotlinx-serialization-core-jvm` | `kotlinx-serialization-json-jvm` |
|-----------------------|----------------------------------|----------------------------------|
| **2026.3**            | 1.11.0                            | 1.11.0                            |
| **2026.2**            | 1.9.0                            | 1.9.0                            |
| **2026.1**            | 1.9.0                            | 1.9.0                            |
| **2025.3**            | 1.7.3                            | 1.7.3                            |
{style=header-column}
