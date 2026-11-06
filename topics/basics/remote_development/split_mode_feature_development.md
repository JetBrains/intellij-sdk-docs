<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Implementing a Feature for Split Mode

<primary-label ref="2026.1"/>

<link-summary>Refactor an existing plugin feature or design a new one, so it behaves well in both split-mode and monolithic IDEs.</link-summary>

<tldr>

**Code**: [IntelliJ Platform Modular Plugin Template](https://github.com/JetBrains/intellij-platform-modular-plugin-template)

</tldr>

This page describes a practical flow for implementing new or refactoring an existing feature to make it work natively in [Split Mode](split_mode_and_remote_development.md) and behave the same in a monolithic IDE.
The steps apply both when migrating an existing plugin and when designing a new one:

1. [**Module Structure**](#1-identify-or-create-necessary-plugin-modules) — Ensure shared, frontend, and backend modules are present.
2. [**Code Distribution**](#2-put-the-existing-or-newly-written-code-into-appropriate-module-types) — Verify that code is properly distributed across shared, frontend, and backend modules.
3. [**Data Serialization**](#3-create-dto-classes-required-for-data-exchange-between-the-frontend-ui-and-backend-logic) — Make sure that shared data is serializable.
4. [**Data Transfer**](#4-add-the-transport-layer-to-connect-the-ui-to-the-backend-model) — Implement RPC for data exchange.
5. [**Feature Behavior**](#5-verify-and-polish) — Verify correctness in both monolith and split modes.
6. [**Optimization**](#6-review-common-issues) — Address issues related to empty state and large state loading.
7. [**Tests**](#7-add-tests) — Cover the functionality with regular unit tests and integration UI tests running in both monolith and split mode.

## 1. Identify or Create Necessary Plugin Modules

1. Refer to the [modular plugin template](https://github.com/JetBrains/intellij-platform-modular-plugin-template) for module structure and necessary dependencies and [Modular Plugins](modular_plugins.md) for modular plugin concept description.
2. Create at least those three modules:
   - `<MyPlugin>.Shared` – with as few dependencies as possible
   - `<MyPlugin>.Backend` - with `intellij.platform.backend` dependency
   - `<MyPlugin>.Frontend` - with `intellij.platform.frontend` dependency
3. Make sure the dependencies are properly described in the build scripts – again, refer to the plugin template.
4. Put the <path>plugin.xml</path> file into the root plugin module’s <path>resources/META-INF</path> directory.
5. Put the <path>&lt;MyPlugin>.&lt;ModuleType>.xml</path> file directly into the <path>resources</path> directory in all three freshly created modules.
6. Reference content modules in the root <path>plugin.xml</path> file.

**Expected Outcome**<br>
The plugin consists of at least three modules, namely frontend, backend, and shared, plus possibly the existing code that resides in a root plugin module or in other submodules.

## 2. Put the Existing or Newly Written Code Into Appropriate Module Types

1. Identify which kind of features dominate in the plugin:
   1. See which APIs it uses more and which type of module they belong to by referring to the [list of frontend/backend/shared APIs](frontend_backend_shared_apis.md).
   2. If there are mostly backend extensions in the plugin, consider it backend functionality; otherwise, frontend functionality.
2. Extract the analyzed functionality into the module type determined during the previous step.
3. If some APIs are still used in the wrong module type, that’s expected and will be addressed later.
4. Continue moving the code between modules with a higher level of granularity:

   Example: consider a plugin that provides a tool window which receives data to display from an external CLI tool that analyzes a project. Steps to perform:
   1. Move the tool window implementation to the frontend and register it in its XML descriptor.
   2. Extract the code responsible for external process spawning and reading its output to the backend module.
   3. Ensure no more APIs are reported as being used in an inappropriate module type.

**Expected Outcome**<br>
The plugin is not functioning properly since the UI is now completely detached from the business logic.
The code, though, is properly distributed between modules that do not communicate with each other yet.
The root plugin module carries only the <path>plugin.xml</path> descriptor in its <path>resources</path> folder.
All the code from it is extracted to one of the freshly created modules.

## 3. Create DTO Classes Required For Data Exchange Between the Frontend UI and Backend Logic

1. Investigate what information is necessary for the extracted UI components but is known only on the backend side.
2. Create classes representing the data and annotate them with `@Serializable`.
3. Consider using one of the primitive types as a DTO property, or use custom structure with a proper custom serializer implementation (see [](remote_procedure_calls.md#data-transfer-objects)).
4. Consider using the serializable form of some major platform primitives if necessary (see [](remote_procedure_calls.md#id-types)).

**Expected Outcome**<br>
There are serializable (in terms of `kotlinx.serialization` framework) DTO classes in the shared module representing the data to be sent over RPC calls.

## 4. Add the Transport Layer to Connect the UI to the Backend Model

> For more details about RPC refer to [RPC guideline](remote_procedure_calls.md).

1. Introduce an RPC interface in the shared plugin module.<br>
   The RPC interface **must**:
   - be annotated with `@Rpc`
   - implement [`RemoteApi<Unit>`](%gh-ic%/fleet/rpc/srcCommonMain/fleet/rpc/FleetApi.kt)
   - have only `suspend` functions
   - use only `@Serializable` DTO classes as function parameters and return type
2. Introduce RPC implementation in the backend plugin module.<br>
   The RPC implementation **must**:
   - implement the corresponding RPC interface
   - be registered in the backend module XML descriptor via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.platform.rpc.backend.remoteApiProvider"/></include>
3. Use DTOs created in [step 3](#3-create-dto-classes-required-for-data-exchange-between-the-frontend-ui-and-backend-logic) as input parameters and return values.
   Get back to step 3 if some data is missing.
4. Call the RPC where the backend data is required.
   - It is a crucial detail that RPC calls are always suspending.
     It may be impossible to use suspending code in a particular place in the frontend functionality, either because it is an old implementation written in Java and is not ready for suspend functions at all, or because the data must be available immediately, otherwise causing poor UX or even freezes.
     Remember that a proper UX is one of the main reasons we initiated the entire splitting process for, see the [split-mode introduction](split_mode_and_remote_development.md).
   - RPC can't be called on [EDT](threading_model.md).
     Avoid wrapping it in `runBlockingCancellable` unless it is absolutely necessary, and you understand all the consequences of such a decision, namely blocking the caller thread and breaking the structured concurrency and suspending API concepts.
   - Consider using the existing platform abstraction for shared state as a reference: [FlowWithHistory.kt](https://github.com/JetBrains/intellij-community/blob/1c3952828ff3af2d18f99a6721c48bb22f97bd57/platform/lang-impl/src/com/intellij/build/FlowWithHistory.kt).
5. RPC is designed to be initiated by the frontend, which implies that users always interact with one of the IDE UI components that naturally belong to the frontend.
   In some cases, however, it is required to initiate some UI display from within the backend code.
   For example, a long backend process may want to show a notification after it finishes.
   Consider using the Remote Topic API in such cases:
   - Declare a project- or application-level topic in the shared plugin module by using the [`ProjectRemoteTopic`](%gh-ic%/platform/remote-topics/shared/src/com/intellij/platform/rpc/topics/ProjectRemoteTopic.kt) or [`ApplicationRemoteTopic`](%gh-ic%/platform/remote-topics/shared/src/com/intellij/platform/rpc/topics/ApplicationRemoteTopic.kt), respectively
   - Subscribe to the topic in the frontend plugin module via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.platform.rpc.applicationRemoteTopicListener"/></include> – code to display the desired notification can be invoked here
   - Push the serializable DTO class into the topic in the backend plugin module where necessary – as soon as the DTO is delivered, the frontend topic listener will do its job
   - Example: [ShowStructurePopupRemoteTopicListener.kt](https://github.com/JetBrains/intellij-community/blob/1b63f9058d6285980c1eac14b8b59fca251751b7/platform/structure-view-impl/frontend/src/ShowStructurePopupRemoteTopicListener.kt)

**Expected Outcome**<br>
Frontend UI exchanges serializable data with backend via RPC or RemoteTopic API.

## 5. Verify and Polish

After all infrastructure has been implemented, it is time to verify the feature behavior and polish it.
Refer to [Introduction into Split Mode / Remote Development](split_mode_and_remote_development.md) on how to manually test Split Mode and check the monolithic IDE as well – the behavior is expected to be exactly the same.

**Expected Outcome**<br>
The code is valid from the point of view of this guide, and the feature works as expected in both Split Mode and a monolithic IDE.

## 6. Review Common Issues

Now that the general functionality works as expected, consider reviewing the list of frequently occurring problems and suggested solutions for them.
Necessity of tuning the code depends on the specifics of the feature.

1. Handle reconnection: wrap RPC calls in [`durable {}`](%gh-ic%/fleet/rpc/srcCommonMain/fleet/rpc/client/Durable.kt); this wrapper will restart the call if a network error occurs.
   Be careful with any side effects your code inside the durable block produces – ideally, avoid them.
  Example: [RecentFileModelSynchronizer.kt](https://github.com/JetBrains/intellij-community/blob/1c3952828ff3af2d18f99a6721c48bb22f97bd57/platform/recentFiles/frontend/src/com/intellij/platform/recentFiles/frontend/model/RecentFileModelSynchronizer.kt)
2. Register actions in proper plugin modules
   1. We strongly encourage registering actions on the frontend side, if possible.
      The possibility is determined by the action's update method: if it requires backend entities, the action belongs on the backend.
      Otherwise – on the frontend.
   2. Frontend actions are rendered immediately and do not introduce delays when displaying context menus, popups, or toolbars.
      If the frontend action decides to touch backend entities in the `AnAction.actionPerformed` method, it is completely fine to call RPC there.
   3. (***advanced***) In some complicated cases, the `action.update()` method might require both frontend and backend entities to be available simultaneously.
      Such cases must be addressed individually depending on a specific feature description.
      There are examples of shared, eventually synchronized state implementations in the IntelliJ Platform codebase, for example, in the [Recent Files implementation](%gh-ic%/platform/recentFiles).
      There, an eventually consistent mutable shared state is implemented to make it possible to access the data model on both the frontend and the backend with no RPC calls required for access.
   4. Consider approaching us on the [JetBrains Platform Forum](https://platform.jetbrains.com) to discuss what could be done in your specific case.
3. Display empty state: UI must render without waiting for backend; show placeholders and progressively fill in data.
4. Load large state: avoid “send everything at once” RPC implementations; use paging/lazy loading, and request only what the UI needs now.
   Example: [BackendRecentFileEventsModel.kt](https://github.com/JetBrains/intellij-community/blob/1c3952828ff3af2d18f99a6721c48bb22f97bd57/platform/recentFiles/backend/src/com/intellij/platform/recentFiles/backend/BackendRecentFileEventsModel.kt#L164)
5. Do not load data too frequently: avoid chatty RPC (per keystroke, per scroll tick). Batch requests, cache results, debounce UI events.
   Example: [RecentFilesEditorTypingListener.kt](https://github.com/JetBrains/intellij-community/blob/1c3952828ff3af2d18f99a6721c48bb22f97bd57/platform/recentFiles/frontend/src/com/intellij/platform/recentFiles/frontend/RecentFilesEditorTypingListener.kt)

**Expected Outcome**<br>
Known issues are mitigated, and the plugin quality is now good enough.

## 7. Add Tests

Fix the split feature behavior and quality with unit and integration tests if you have not used the TDD approach earlier.
See the [](split_mode_and_remote_development.md#running-tests-in-split-mode) section.

We suggest paying attention to:

- Data transformations: correct (de)serialization
- Data consistency: correct data merging in case of complicated features with eventually consistent backend/frontend state
- Proper behavior under latency: artificial delay in a test implementation backend service does not bring freezes or broken UX

**Expected Outcome**<br>
The feature implementation has tests covering its correct behavior in remote and local scenarios.

## Getting Help

In case of any questions or uncertainties regarding the splitting process, please post a question on the [JetBrains Platform Forum](https://platform.jetbrains.com).
We will try to provide as much help as possible there and reconsider and adjust for unexpected cases.

<include from="snippets.topic" element-id="missingContent"/>
