<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2026.*

<link-summary>List of known Notable API Changes in 2026.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2026.3

### IntelliJ Platform 2026.3

Plugin management with user consent
:
  The experimental [`PluginPermissionService`](%gh-ic-master%/platform/core-impl/src/com/intellij/ide/plugins/PluginPermissionService.kt) lets a plugin ask the user to allow an operation on other plugins.
  The plugin passes a request and an action to `withPermission()`.
  The IDE shows the request message in a permission dialog, and runs the action only if the user allows the request.
  If the user denies the request, `withPermission()` returns a failure with `PluginPermissionNotGrantedException`.
  [`PluginManagementPermissions.kt`](%gh-ic-master%/platform/platform-impl/src/com/intellij/openapi/updateSettings/PluginManagementPermissions.kt) defines these requests:
  - `EnablePluginRequest`, `DisablePluginRequest`, and `InstallPluginRequest` give the action a `PluginManagementAction`, and its `apply()` starts the operation.
  - `AccessPluginClassLoadersRequest` gives the action a `ReadPluginDescriptorsAction`, which returns the descriptors of all installed plugins and their class loaders.

  Each permission works for one call only, so the plugin must send a new request to repeat the operation.
  Call `withPermission()` directly from the plugin code, because the service identifies the requesting plugin by the calling class.
  Java code can use `PluginPermissionJavaShim.withPermission()`, which returns a `CompletableFuture`.

  ```kotlin
  val result = PluginPermissionService.getInstance().withPermission(
    DisablePluginRequest(
      pluginId,
      MyBundle.message("disable.conflicting.plugin.reason")
    )
  ) { action -> action.apply() }
  ```

  This API replaces the `PluginManagerCore` methods that enable and disable plugins, which are now internal.
  See [](api_changes_list_2026.md#plugin-management-20262).

The Old Search Everywhere API is deprecated
:
  The old Search Everywhere API in the [`com.intellij.ide.actions.searcheverywhere`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere) package is deprecated in favor of the new Search Everywhere API in [`com.intellij.platform.searchEverywhere`](%gh-ic%/platform/searchEverywhere/shared/src), which is enabled by default since 2026.2.
  <br>
  Migrate to the following replacements:
  - [`SearchEverywhereContributor`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributor.java), [`WeightedSearchEverywhereContributor`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/WeightedSearchEverywhereContributor.kt), and [`AbstractGotoSEContributor`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/AbstractGotoSEContributor.kt) – implement [`SeItemsProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProvider.kt).
  - [`SearchEverywhereContributorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereContributorFactory.java) registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.searchEverywhereContributor"/></include> extension point – implement [`SeItemsProviderFactory`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProviderFactory.kt) and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.searchEverywhere.itemsProviderFactory"/></include> extension point.
  - [`FoundItemDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/FoundItemDescriptor.java), [`ContributorSearchResult`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/ContributorSearchResult.java), and [`SearchEverywhereFoundElementInfo`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereFoundElementInfo.java) – use [`SeItem`](%gh-ic%/platform/searchEverywhere/shared/src/SeItem.kt).
  - [`SearchEverywhereExtendedInfoProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereExtendedInfoProvider.kt) and [`ExtendedInfo`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/ExtendedInfo.kt) – use [`SeItemPresentation.extendedInfo`](%gh-ic%/platform/searchEverywhere/shared/src/presentations/SeItemPresentation.kt) and [`SeExtendedInfoCalculator`](%gh-ic-master%/platform/searchEverywhere/shared/src/providers/target/SeExtendedInfoCalculator.kt).
  - [`SearchEverywherePreview`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywherePreview.kt) – implement [`SeItemsPreviewProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsPreviewProvider.kt).
  - [`SearchEverywhereCommandInfo`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereCommandInfo.java) – use [`SeCommandInfo`](%gh-ic%/platform/searchEverywhere/shared/src/SeCommandInfo.kt) and [`SeCommandInfoFactory`](%gh-ic%/platform/searchEverywhere/shared/src/SeCommandInfo.kt).
  - Custom tabs – implement [`SeTabFactory`](%gh-ic%/platform/searchEverywhere/frontend/src/SeTabFactory.kt) and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.searchEverywhere.tabFactory"/></include> extension point.

  The [`com.intellij.ide.actions.searcheverywhere.remote`](%gh-ic%/platform/lang-api/src/com/intellij/ide/actions/searcheverywhere/remote) classes are obsolete without replacement, because the new API supports remote development by design.
  The new API is marked as `@ApiStatus.Experimental`.
  In 2027.1, the deprecated API will be disabled by default via a registry key. In 2027.2, it will be removed.
  <br>
  Report missing functionality on the [JetBrains Platform Forum](https://platform.jetbrains.com).

## 2026.2

### IntelliJ Platform 2026.2

Asynchronous `VirtualFile` content saving
:
[`VirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) content updates via `getOutputStream()` or `setBinaryContent()` can now be postponed – the actual file on disk may be modified with some delay.
See [](virtual_file.md#when-are-virtualfile-changes-persisted-on-disk-and-loaded-from-disk-to-vfs) for more details.

## 2026.1

### IntelliJ Platform 2026.1

Background-capable VFS listeners
:
New APIs allow VFS listener callbacks to run off the Event Dispatch Thread (EDT), reducing UI freezes during heavy file operations.
Bulk listeners can implement the [`BulkFileListenerBackgroundable`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/newvfs/BulkFileListenerBackgroundable.kt) marker interface and subscribe to the [`VirtualFileManager.VFS_CHANGES_BG`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java) message topic (instead of `VFS_CHANGES`).
Async listeners can be registered via [`addAsyncFileListenerBackgroundable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java) or the `com.intellij.vfs.asyncListenerBackgroundable` extension point.
Migrate thread-safe listeners without UI dependencies from `VFS_CHANGES`/[`addAsyncFileListener()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFileManager.java).

New LSP API features
:
Language Server Protocol support gains Range Formatting (`textDocument/rangeFormatting`), Code Lens (`textDocument/codeLens`), and Optimize Imports (`textDocument/codeAction` with `source.organizeImports`). ``Also includes a major rewrite of LSP highlighting for improved performance. See [](language_server_protocol.md).

`OSProcessHandler.waitFor()` on EDT or under read lock logs error
:
Waiting for an external process via [`OSProcessHandler.waitFor()`](%gh-ic%/platform/platform-util-io/src/com/intellij/execution/process/OSProcessHandler.java) on the Event Dispatch Thread or while holding a read lock is now prohibited for all users and logs a `LOG.error`.
This check has been previously enabled in internal mode since 2019.
Move process waiting off the EDT and outside of read actions, e.g., into a background thread or a coroutine.

Non-cancellable read action APIs deprecated
:
[`ReadAction.compute()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java), [`ReadAction.run()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java), and [`runReadAction()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/actions.kt) are deprecated because long-running non-cancellable read actions on background threads can block write actions and cause UI freezes.
Migrate to cancellable coroutine-based APIs: [`readAction {}`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) or [`smartReadAction {}`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) in Kotlin, or [`ReadAction.nonBlocking().submit()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java) / [`.executeSynchronously()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java) in Java.
As a last resort, [`ReadAction.computeBlocking()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java) and [`runReadActionBlocking()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) are available but should only be used under modal progress.

`AnActionEvent.coroutineScope` added
:
[`AnActionEvent`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) now exposes a `coroutineScope` property providing convenient access to a `CoroutineScope` within `actionPerformed`, while maintaining synchronous EDT execution contracts.

### Terminal Plugin 2026.1

`TerminalUtil.hasRunningCommands()` must not be called on EDT
:
[`TerminalUtil.hasRunningCommands()`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalUtil.java) now asserts it is not called on the Event Dispatch Thread via `ThreadingAssertions.assertBackgroundThread`, and logs a `LOG.error` if violated.
Calling this method on the EDT can cause UI freezes because it queries external process state.
Move calls to a background thread or a coroutine.
