<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2026.*

<link-summary>List of known Notable API Changes in 2026.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

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
