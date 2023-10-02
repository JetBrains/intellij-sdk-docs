# General Threading Rules

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- short link: https://jb.gg/ij-platform-threading -->

<link-summary>Threading rules for reading and writing to IntelliJ Platform data models, running and canceling background processes, and avoiding UI freezes.</link-summary>

## Read-Write Lock

> [Thread Access Info](https://plugins.jetbrains.com/plugin/16815-thread-access-info) plugin visualizes Read/Write Access and Thread information in debugger.

In general, code-related data structures in the IntelliJ Platform are covered by a single reader/writer lock.

You must not access the model outside a read or write action for the following subsystems:

- [](psi.md)
- [](virtual_file_system.md) (VFS)
- [Project root model](project_structure.md).

> Threading model has changed in 2023.3, please make sure to choose the correct version below.
>
{title="2023.3 Threading Model Changes" style="warning"}

### Read Access

<tabs group="threading">

<tab title="2023.3 and later" group-key="newThreading">

Reading data is allowed from any thread.
Read operations need to be wrapped in a read action (RA) if not invoked via `Application.invokeLater()`.

</tab>

<tab title="Earlier versions" group-key="oldThreading">

Reading data is allowed from any thread.
Reading data from the UI thread does not require any special effort.
However, read operations performed from any other thread need to be wrapped in a read action (RA).

</tab>

</tabs>

The corresponding objects are not guaranteed to survive between several consecutive read actions.
As a rule of thumb, whenever starting a read action, check if the PSI/VFS/project/module is still valid.

**API**: `ApplicationManager.getApplication().runReadAction()` or [`ReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java) `run()`/`compute()`

### Write Access

Writing data is only allowed from the UI thread, and write operations always need to be wrapped in a write action (WA).
Modifying the model is only allowed from write-safe contexts, including user actions and `SwingUtilities.invokeLater()` calls from them (see the next section).
You may not modify PSI, VFS, or project model from inside UI renderers or `SwingUtilities.invokeLater()` calls.

**API**: `ApplicationManager.getApplication().runWriteAction()` or [`WriteAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/WriteAction.java) `run()`/`compute()`

## Modality and `invokeLater()`

To pass control from a background thread to the [Event Dispatch Thread](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html) (EDT), instead of the standard `SwingUtilities.invokeLater()`, plugins should use `ApplicationManager.getApplication().invokeLater()`.
The latter API allows specifying the _modality state_ ([`ModalityState`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ModalityState.java)) for the call, i.e., the stack of modal dialogs under which the call is allowed to execute:

#### `ModalityState.NON_MODAL`

The operation will be executed after all modal dialogs are closed.
If any of the open (unrelated) projects displays a per-project modal dialog, the action will be performed after the dialog is closed.

#### `ModalityState.stateForComponent()`

The operation can be executed when the topmost shown dialog is the one that contains the specified component or is one of its parent dialogs.

#### None Specified

`ModalityState.defaultModalityState()` will be used.
This is the optimal choice in most cases that uses the current modality state when invoked from UI thread.
It has special handling for background processes started with `ProgressManager`: `invokeLater()` from such a process may run in the same dialog that the process started.

#### `ModalityState.any()`

The operation will be executed as soon as possible regardless of modal dialogs.
Please note that modifying PSI, VFS, or project model is prohibited from such runnables.

If a UI thread activity needs to access [file-based index](indexing_and_psi_stubs.md) (e.g., it's doing any project-wide PSI analysis, resolves references, etc.), please use `DumbService.smartInvokeLater()`.
That way, it is run after all possible indexing processes have been completed.

## Background Processes and `ProcessCanceledException`

Background progresses are managed by [`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) class, which has plenty of methods to execute the given code with a modal (dialog), non-modal (visible in the status bar), or invisible progress.
In all cases, the code is executed on a background thread, which is associated with a [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) object.
The current thread's indicator can be retrieved any time via `ProgressIndicatorProvider.getGlobalProgressIndicator()`.

For visible progresses, threads can use `ProgressIndicator` to notify the user about current status: e.g., set text or visual fraction of the work done.

Progress indicators also provide means to handle cancellation of background processes, either by the user (pressing the <control>Cancel</control> button) or from code (e.g., when the current operation becomes obsolete due to some changes in the project).
The progress can be marked as canceled by calling `ProgressIndicator.cancel()`.
The process reacts to this by calling `ProgressIndicator.checkCanceled()` (or `ProgressManager.checkCanceled()` if no indicator instances at hand).
This call throws a special unchecked [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) (PCE) if the background process has been canceled.

All code working with [PSI](psi.md), or in other kinds of background processes, must be prepared for [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) (PCE) being thrown from any point.
This exception should never be logged but rethrown, and it'll be handled in the infrastructure that started the process.
Use inspection <control>Plugin DevKit | Code | 'ProcessCanceledException' handled incorrectly</control> (2023.3).

The `checkCanceled()` should be called often enough to guarantee the process's smooth cancellation.
PSI internals have a lot of `checkCanceled()` calls inside.
If a process does lengthy non-PSI activity, insert explicit `checkCanceled()` calls so that it happens frequently, e.g., on each _Nth_ loop iteration.
Use inspection <control>Plugin DevKit | Code | Cancellation check in loops</control> (2023.1).

### Disabling ProcessCanceledException

Throwing `ProcessCanceledException` from `ProgressIndicator.checkCanceled()` can be disabled for development (e.g., while debugging the code) by invoking:

<tabs>
<tab title="2023.2 and later">

<ui-path>Tools | Internal Actions | Skip Window Deactivation Events</ui-path>

</tab>

<tab title="Earlier Versions">

<ui-path>Tools | Internal Actions | Disable ProcessCanceledException</ui-path>

</tab>
</tabs>

These actions are available only if [Internal Mode is enabled](enabling_internal.md).

## Read Action Cancellability

Background threads shouldn't take plain read actions for a long time.
The reason is that if the UI thread needs a write action (e.g., the user types something), it must be acquired as soon as possible.
Otherwise, the UI will freeze until all background threads have released their read actions.

The best-known approach is to cancel background read actions whenever there's a write action about to occur, and restart that background read action later from scratch.
Editor highlighting, code completion, Goto Class/File/... actions all work like this.

To achieve that, the lengthy background operation is started with a `ProgressIndicator`, and a dedicated listener cancels that indicator when write action is initiated.
The next time the background thread calls `checkCanceled()`, a `ProcessCanceledException` is thrown, and the thread should stop its operation (and finish the read action) as soon as possible.

There are two recommended ways of doing this:

* If on UI thread, call `ReadAction.nonBlocking()` which returns [`NonBlockingReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/NonBlockingReadAction.java) (NBRA)
* If already in a background thread, use `ProgressManager.getInstance().runInReadActionWithWriteActionPriority()` in a loop, until it passes or the whole activity becomes obsolete.

In both approaches, always check at the start of each read action if the objects are still valid, and if the whole operation still makes sense (i.e., not canceled by the user, the project isn't closed, etc.).
With `ReadAction.nonBlocking()`, use `expireWith()` or `expireWhen()` for that.

If the activity has to access [file-based index](indexing_and_psi_stubs.md) (e.g., it's doing any project-wide PSI analysis, resolves references, etc.), use `ReadAction.nonBlocking(...).inSmartMode()`.

## Avoiding UI Freezes

#### Do not Perform Long Operations in UI Thread

In particular, don't traverse [](virtual_file_system.md), parse [PSI](psi.md), resolve [references](psi_references.md) or query [indexes/stubs](indexing_and_psi_stubs.md).

There are still some cases when the platform itself invokes such expensive code (e.g., resolve in `AnAction.update()`), but these are being worked on.
Meanwhile, please try to speed up what you can in your plugin as it will be generally beneficial and also improve background highlighting performance.
For implementations of [`AnAction`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java), plugin authors should specifically
review the documentation of `AnAction.getActionUpdateThread()` in the [](basic_action_system.md) section as it describes how threading works for actions.

`WriteAction`s currently have to happen on UI thread, so to speed them up, you can try moving as much as possible out of write action into a preparation step which can be then invoked in background (e.g., using `ReadAction.nonBlocking()`, see above).

Don't do anything expensive inside event listeners.
Ideally, you should only clear some caches.
You can also schedule background processing of events, but be prepared that some new events might be delivered before your background processing starts, and thus the world might have changed by that moment or even in the middle of background processing.
Consider using [`MergingUpdateQueue`](%gh-ic%/platform/ide-core/src/com/intellij/util/ui/update/MergingUpdateQueue.java) and `ReadAction.nonBlocking()` to mitigate these issues.

Massive batches of VFS events can be pre-processed in background, see [`AsyncFileListener`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) (2019.2 or later).
