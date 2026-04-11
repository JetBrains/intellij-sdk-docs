<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2025.*

<link-summary>List of known Notable API Changes in 2025.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2025.3

### IntelliJ Platform 2025.3

Threading Model changes
: Several write actions now run on background threads instead of EDT: VFS refresh, document commit (PSI reparsing), and document saving on frame deactivation.
  API changes and additions:
- [`Dispatchers.UI`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) — new coroutine dispatcher for the UI thread that does **not** acquire write-intent lock; use it for pure UI operations that do not need read/write lock protection (as opposed to `Dispatchers.EDT`, which acquires write-intent lock for legacy model access).
- [`backgroundWriteAction()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) and [`readAndBackgroundWriteAction()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) — stabilized suspending functions for executing write actions on a background thread.
- Editor model data (document, caret, selection, folding) no longer requires a read lock when accessed on EDT.
- Tests relying on spinning the AWT event queue to wait for write actions may hang; use [`PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java) which is adapted to await background write actions.

`IdeProductMode`: Detecting Frontend/Backend/Monolith Mode
: [`IdeProductMode`](%gh-ic%/platform/platform-api/src/com/intellij/platform/ide/productMode/IdeProductMode.kt) (experimental) allows plugins to determine the current application mode at runtime:
- `IdeProductMode.isBackend` — the IDE runs as a remote development backend host
- `IdeProductMode.isFrontend` — the IDE runs as a frontend (JetBrains Client) connected to a remote host
- `IdeProductMode.isMonolith` — the IDE runs as a standard local (monolith) instance

  Use this API to implement mode-specific behavior, e.g., to skip UI-related functionality when running as a backend.

LSP API updates
: New LSP capabilities are supported, all enabled by default and controllable via `LspServerDescriptor.lspCustomization`:
- Server Initiated Progress (`$/progress`)
- Highlight Usages In File (`textDocument/documentHighlight`)
- Go To Symbol (`workspace/symbol`)
- File Structure, Breadcrumbs, and Sticky Lines (`textDocument/documentSymbol`)
- Parameter Info (`textDocument/signatureHelp`)

  See [](language_server_protocol.md#supported-features) for the full list.

Islands UI Theme is now default
: The Islands theme is now the default UI theme. Custom themes must be updated to support it.
  See [](supporting_islands_theme.md) for migration steps including setting the parent theme, adapting background colors, border colors, and selected tab colors.

Search Everywhere: New API compatible with Remote Development
: The Search Everywhere architecture has been redesigned to support remote development by separating data/logic from UI presentation, enabling results to be serialized and transmitted across process boundaries.
  New API components:
- [`SeItemsProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProvider.kt) replaces `SearchEverywhereContributor` as the main interface for providing search results. It can run on both backend and frontend, and returns items with serializable presentation data.
- [`SeItemsProviderFactory`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProviderFactory.kt) is the new extension point for registering result sources.
- [`SeTab`](%gh-ic%/platform/searchEverywhere/frontend/src/SeTab.kt) and [`SeTabFactory`](%gh-ic%/platform/searchEverywhere/frontend/src/SeTabFactory.kt) represent frontend-only tab abstractions for the Search Everywhere dialog.
- [`SeLegacyItemPresentationProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeLegacyItemPresentationProvider.kt) provides a temporary adapter for migrating existing `SearchEverywhereContributor` implementations.

  Existing plugins continue working in local (monolith) environments via adapters. The new API is enabled by default in remote development. Migration is recommended now; the old API will be deprecated in 2026.2.
  See also [blog post](https://blog.jetbrains.com/platform/2025/12/major-architectural-update-introducing-the-new-search-everywhere-api-built-for-remote-development/) for details.

## 2025.2

### IntelliJ Platform 2025.2

Threading Model changes
: Several APIs are stabilized and one is deprecated:
- [`runBlockingCancellable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt), [`currentThreadCoroutineScope()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt), and [`coroutineToIndicator()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) are promoted to stable.
- [`blockingContext()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) is now a no-op and deprecated ([IJPL-445](https://youtrack.jetbrains.com/issue/IJPL-445)). The platform manages thread contexts automatically; remove all usages.
- Threads blocked on read/write lock acquisition now react to cancellation and throw `CancellationException` instead of proceeding.

## 2025.1

### IntelliJ Platform 2025.1

[`ContainerUtil`](%gh-ic%/platform/util/src/com/intellij/util/containers/ContainerUtil.java) using unmodifiable collections
: Methods marked with [`@Unmodifiable`](%gh-java-annotations%/java8/src/main/java/org/jetbrains/annotations/Unmodifiable.java) now really return unmodifiable collections (only in [internal](enabling_internal.md)/test mode for now).
