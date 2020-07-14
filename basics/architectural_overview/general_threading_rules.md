---
title: General Threading Rules
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Read/Write Lock

In general, code-related data structures in the *IntelliJ Platform* are covered by a single reader/writer lock. 

You must not access the model outside a read or write action for the following subsystems:
- PSI 
- VFS
- project root model.

**Reading** data is allowed from any thread. 
Reading data from the UI thread does not require any special effort. 
However, read operations performed from any other thread need to be wrapped in a read action by using `ApplicationManager.getApplication().runReadAction()` or, shorter, [`ReadAction`](upsource:///platform/core-api/src/com/intellij/openapi/application/ReadAction.java) `run()`/`compute()`.
The corresponding objects are not guaranteed to survive between several consecutive read actions.
As a rule of thumb, whenever starting a read action, check if PSI/VFS/project/module is still valid.

**Writing** data is only allowed from the UI thread, and write operations always need to be wrapped in a write action with `ApplicationManager.getApplication().runWriteAction()` or, shorter, [`WriteAction`](upsource:///platform/core-api/src/com/intellij/openapi/application/WriteAction.java) `run()`/`compute()`.
Modifying the model is only allowed from write-safe contexts, including user actions and `invokeLater()` calls from them (see the next section).
You may not modify PSI, VFS, or project model from inside UI renderers or `SwingUtilities.invokeLater()` calls.


## Modality and `invokeLater()`

To pass control from a background thread to the event dispatch thread, instead of the standard `SwingUtilities.invokeLater()`, plugins should use `ApplicationManager.getApplication().invokeLater()`.
The latter API allows specifying the _modality state_ ([`ModalityState`](upsource:///platform/core-api/src/com/intellij/openapi/application/ModalityState.java)) for the call, i.e., the stack of modal dialogs under which the call is allowed to execute: 

`ModalityState.NON_MODAL`
: The operation will be executed after all modal dialogs are closed. Note that if any of the open (unrelated) project displays a per-project modal dialog, the action will be executed after the dialog is closed. 

`ModalityState.stateForComponent()`
: The operation can be executed when the topmost shown dialog is the one that contains the specified component or is one of its parent dialogs. 

None specified
: `ModalityState.defaultModalityState()` will be used. In most cases, this is the optimal choice, that uses current modality state when invoked from UI thread, and has special handling for background processes started with `ProgressManager`: `invokeLater()` from such a process may run in the same dialog that the process started.

`ModalityState.any()`
: The operation will be executed as soon as possible regardless of modal dialogs. Please note that modifying PSI, VFS, or project model is prohibited from such runnables.

If a UI thread activity needs to access [file-based index](/basics/indexing_and_psi_stubs.md) (e.g., it's doing any project-wide PSI analysis, resolves references, etc.), please use `DumbService.smartInvokeLater()`. 
That way, it is run after all possible indexing processes have been completed.


## Background processes and `ProcessCanceledException`

Background progresses are managed by [`ProgressManager`](upsource:///platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) class, 
which has plenty of methods to execute the given code with a modal (dialog), non-modal (visible in the status bar), or invisible progress. 
In all cases, the code is executed on a background thread, which is associated with a [`ProgressIndicator`](upsource:///platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) object.
The current thread's indicator can be retrieved any time via `ProgressIndicatorProvider.getGlobalProgressIndicator()`.

For visible progresses, threads can use `ProgressIndicator` to notify the user about current status:
e.g., set text or visible fraction of the work done.

Progress indicators also provide means to handle cancellation of background processes, either by the user (pressing _Cancel_ button) 
or from code (e.g., when the current operation becomes obsolete due to some changes in the project).
The progress can be marked as canceled by calling `ProgressIndicator.cancel()`.
The process reacts to this by calling `ProgressIndicator.checkCanceled()` (or `ProgressManager.checkCanceled()` if no indicator instance at hand).
This call throws a special unchecked [`ProcessCanceledException`](upsource:///platform/util/src/com/intellij/openapi/progress/ProcessCanceledException.java) if the background process has been canceled.

All code working with PSI, or in other kinds of background processes, must be prepared for [`ProcessCanceledException`](upsource:///platform/util/src/com/intellij/openapi/progress/ProcessCanceledException.java) being thrown from any point.
This exception should never be logged, but rethrown, and it'll be handled in the infrastructure that started the process.

The `checkCanceled()` should be called often enough to guarantee smooth cancellation of the process. 
PSI internals have a lot of `checkCanceled()` calls inside.
If a process does lengthy non-PSI activity, insert explicit `checkCanceled()` calls so that it happens frequently, e.g., on each _Nth_ loop iteration.

## Read Action Cancellability

Background threads shouldn't take plain read actions for a long time. The reason is that if the UI thread needs a write action (e.g., the user types something), 
it must be acquired as soon as possible. Otherwise, the UI will freeze until all background threads have released their read actions.

The best-known approach is to cancel background read actions whenever there's a write action about to occur, and restart that background read action later from scratch.
Editor highlighting, code completion, Goto Class/File/... actions all work like this.

To achieve that, the lengthy background operation is started with a `ProgressIndicator`, and a dedicated listener
cancels that indicator when write action is started.
The next time the background thread calls `checkCanceled()`, a `ProcessCanceledException` is thrown,
and the thread should stop its operation (and finish the read action) as soon as possible. 
 
There are two recommended ways of doing this:

* If on UI thread, call `ReadAction.nonBlocking()` which returns [`NonBlockingReadAction`](upsource:///platform/core-api/src/com/intellij/openapi/application/NonBlockingReadAction.java)
* If already in a background thread, use `ProgressManager.getInstance().runInReadActionWithWriteActionPriority()` in a loop, until it passes or the whole activity becomes obsolete.

In both approaches, always check at the start of each read action, if the objects are still valid, and if the whole operation still makes sense (i.e., not canceled by the user, the project isn't closed, etc.).
With `ReadAction.nonBlocking()`, use `expireWith()` or `expireWhen()` for that.

If the activity has to access [file-based index](/basics/indexing_and_psi_stubs.md) (e.g., it's doing any project-wide PSI analysis, resolves references, etc.), use `ReadAction.nonBlocking(...).inSmartMode()`.
