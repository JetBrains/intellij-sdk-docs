<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Split Mode (Remote Development)

<primary-label ref="2025.3"/>

<link-summary>Design plugins for both monolithic IDEs and split-process IDE sessions, and validate frontend and backend behavior from the start.</link-summary>

<tldr>

**Code**: [IntelliJ Platform Modular Plugin Template](https://github.com/JetBrains/intellij-platform-modular-plugin-template)

**Video**: [Making an IntelliJ Plugin Remote Development-friendly](https://www.youtube.com/watch?v=T2VvY6kgALY)

</tldr>

## Split Mode Overview

The documentation uses the following basic terms:

- **Remote Development**: a workflow where the IDE frontend connects to a backend running locally or on another machine
- **Split Mode**: essentially a synonym for Remote Development, a JetBrains IDE setup where the frontend and backend run as separate processes
- **Split Plugin** (remote development-native plugin): a plugin consisting of dedicated frontend, backend, and shared modules so each part runs on the appropriate side in Split Mode

> See the [Modular Plugins](modular_plugins.md) section for detailed information about plugin modules.

Remote development changes where plugin code runs.
In Split Mode, the frontend process renders the user interface, while the backend process hosts the project model, indexing, analysis, execution, and other heavy work.
The backend may run on the same machine, on another host, in a container, or in the cloud.

Plugins that provide user interface, typing assistance, or other latency-sensitive features should account for this model from the beginning.
This article explains the terminology, motivation, and architecture behind remote development in JetBrains IDEs, also known as **Split Mode**. It also describes how to run, debug, and test an IDE in Split Mode.

## Remote Development Support Benefits

Remote development support in the IDE provides several benefits for end users:

* Use more powerful hardware than a local laptop.
* Work on a different OS than the one you’re running locally (useful for cross-platform development).
* Use the laptop as a thin client (often no need to keep source code locally).
* Keep sensitive code on company servers while still working “from anywhere”.

## Benefits of Applying Split Mode in Plugins

Applying the Split Mode architecture provides the following benefits for plugins:

* Responsive UX is not affected by latency or unstable connection issues
* Some APIs naturally belong to either the backend or the frontend, and it is crucial that they work on the proper side.
  Take a file system events listener, for instance: the plugin would likely want to observe events in the file system where the project source code is located, not on the client side.
* Split Mode becomes more and more popular and is demanded by JetBrains IDEs customers, who in turn are potential plugin customers as well.

## Problems with Non-Split Plugins

Some features do not require anything specific to work properly in Split Mode.
These include:

* LSP
* MCP
* Inspections and annotators
* Quick fixes and intentions
* Completion contributors
* Reference providers
* Run configurations
* Find Usages providers
* Inlays

Usually, non-split plugins depend on plugins and modules that can be run only on the backend, forcing the plugin to be loaded on the backend only.
If a plugin enabled on the backend provides other functionality, such as UI or editing assistance, it will be available on the frontend side as well, but will be affected by latency and result in suboptimal UX.
For example:

* Language support, lexers, and parsers get processed on the backend and expose their output, such as the PSI tree, only there.
  The frontend does not have information about the file structure and only renders the highlighting received from the backend by applying it to the corresponding ranges in an edited file.
* Typing assistance gets processed on the backend and then sent to the frontend, making it subject to latency issues.
* UI components, actions, dialogs, and popups get rendered on the backend side, and rendering commands are then transmitted to the frontend.
  They do not require additional effort to work, but they are subject to latency issues, and the resulting UX is usually of insufficient quality.

## Split Mode Architecture

The high-level architecture of the Split Mode can be summarized on the following diagram:

![High-level architecture overview](split-mode-high-level-architecture.png){width=420}

A Split Plugin is a plugin consisting of **modules** that implement different parts of its functionality and are loaded either on the backend of a JetBrains IDE, on the frontend, or everywhere.
These modules are referred to as **backend**, **frontend**, and **shared** plugin modules.

An IDE running in Split Mode is essentially two separate processes.
A monolithic IDE is a single process.

A split plugin works just fine in a monolithic IDE.
The only difference is that both frontend and backend functionality are combined in the same process.

IntelliJ Platform determines whether a particular module belongs to the frontend or the backend by examining its dependencies on the corresponding `intellij.platform.frontend` and `intellij.platform.backend` modules.
If a dependency is not satisfied, the module does not get loaded.
Both frontend and backend dependencies are satisfied when an IDE runs in monolithic mode.
See [Plugin Management](plugin_management_in_split_mode.md) for more details.

A more detailed architecture is presented on the following diagram:

![Low-level architecture overview](split-mode-low-level-architecture.png){width=640}

Frontend and backend code communicate with each other via RPC calls.
The RPC framework provided by the IntelliJ Platform implies the existence of a shared plugin module, which gets loaded
regardless of which IDE mode is active.

Relations between modules are as follows:

- The **shared** module defines RPC interfaces.
- The **frontend** module depends on the **shared** module and calls RPC via these interfaces.
- The **backend** module depends on the **shared** module and implements RPC interfaces.

Thus, the primary flow is to get or send data from the frontend to the backend via RPC.

Data sent through RPC must be serializable; the *kotlinx.serialization* framework is used for that
purpose. The data is sent over a secure protocol under the hood, so there is no need to apply
additional measures.

In a monolithic IDE, the whole machinery works inside a single process, and there is no network
access during RPC execution. In fact, one can consider the RPC function call a regular suspend
function call in Kotlin, and thus features properly implemented for Split Mode naturally work well
in a monolithic IDE.

## Running the Sandbox IDE in Split Mode

See [configuring a plugin for Split Mode](configuring_split_mode.md#basic-configuration) for details.

## Running Tests in Split Mode

There are two common needs here:

1. Plugin business logic can be tested by regular unit tests using the IntelliJ test framework (see [IntelliJ Platform Testing Extension](tools_intellij_platform_gradle_plugin_testing_extension.md#intellijPlatformTesting)).

   Tests that verify the functionality of a specific class can be placed in any content module.
   However, testing the plugin as a whole requires placing test classes into the root plugin module
   This is necessary for the correct classpath assembly, which will include the <path>plugin.xml</path> file and properly register all plugin extensions from all content modules.

2. Plugin UI in Split Mode can be tested by the integrated UI test framework (see [Integration Tests: UI Testing](integration_tests_ui.md#interaction-with-components)).
   For example, see [PluginTest](https://github.com/JetBrains/intellij-ide-starter/blob/master/intellij.tools.ide.starter.examples.plugins/src/integrationTest/kotlin/PluginTest.kt).

## Debugging the IDE in Split Mode

Use the <control>Run IDE (Split Mode)</control> run configuration that will start both frontend and backend processes locally.

The [modular plugin template](https://github.com/JetBrains/intellij-platform-modular-plugin-template) already provides such a run configuration out of the box.
To add it in an existing project, use the steps below.

<procedure title="Creating the Run IDE (Split Mode) Run Configuration">

1. Make sure you are using the IntelliJ Platform Gradle Plugin version 2.14 or higher.
2. Call the `:generateSplitModeRunConfigurations` task via Execute Gradle Task action or via terminal.
3. Once finished, the task will produce a compound `Run IDE (Split Mode)` run configuration that can be selected in the run widget and used for debugging or running as usual.
   > Starting with IntelliJ Platform Gradle Plugin 2.15.0, generated run configurations show the full backend or frontend IDE log files in additional tabs.

</procedure>

To enable additional `DEBUG` or `TRACE` logging categories for the split-mode backend or frontend process, configure the `runIdeBackend` and `runIdeFrontend` tasks in the Gradle build script as described in [](configuring_split_mode.md#configuring-debug-and-trace-logs).

## Testing Split Mode Manually and Emulating Latency

Deploying the backend to a real remote machine is not the easiest or fastest way to check that the feature has been properly split. The major problem with features working in a Split Mode IDE is that they are exposed to latency issues. This can be easily emulated within a locally running Split Mode.

<procedure title="Emulating Slow Connection to the Backend">

1. Enable the [internal mode](enabling_internal.md) in the IDE your plugin is being tested against by specifying the system property `-Didea.is.internal=true`.
2. Open the **Split Mode** widget in the upper left corner of the target IDE.
3. Switch to the **Connection Config (internal)** tab.
4. Select the **Enable Connection Widget** checkbox.
5. Specify a reasonably large custom value in the **Direct Ping** field - this will delay all communication that goes through RPC in the entire IDE.

   ![Split Mode widget in the IDE](remdev_split_mode_widget.png){width=706}

</procedure>

Be careful not to set delay too high – the UX will be very poor if a particular feature has not yet been properly split.

Specifying the delay allows experiencing something close to the real feel of the features provided by the plugin.
If the plugin works correctly and fast, it is likely well-prepared for Split Mode and remote development context.

<include from="snippets.topic" element-id="missingContent"/>
