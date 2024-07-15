<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Background Processes

<link-summary>Background process is a computation executed on a background thread with the possibility of interrupting it and tracking its progress.</link-summary>

<b><format color="red">IN PROGRESS, DO NOT REVIEW</format></b>

Background process is a time-consuming computation usually executed on a background thread.
The IntelliJ Platform executes background processes widely and provides the possibility of running them by plugins.
The API allows for cancelling tasks and tracking their progress.

[//]: # (TODO[delete?]: Processes can be executed with a modal &#40;dialog&#41;, non-modal &#40;visible in the status bar&#41;, or invisible progress.)

## Cancellation

The most important feature of a background process is the ability to cancel it if the result of the computation gets irrelevant.
Cancellation can be performed either by a user (pressing the <control>Cancel</control> button) or from code when the current operation becomes obsolete due to some changes in the project.
Examples:

- Cancelling the search for symbol usages (cancellation by user):
  1. The user triggers the <control>Find Usages</control> action in a large project.
  2. Results are being calculated and gradually presented to the user.
  3. The user sees the place they were interested in or realizes that they don't need these results anymore.
  4. The user clicks the cancel button in the status bar, and the operation is canceled.
- Code completion (cancellation from code):
  1. The user types a letter in the editor.
  2. Computation of results for code completion is started.
  3. User types another letter.
  4. The computation started in 2. is now outdated and is canceled to start computation for the new input.

Being prepared for cancellation in plugin code is crucial for saving the CPU resources and responsiveness of the IDE.

## Background Process API

> Note that these APIs are obsolete since 2024.1.
> Plugins targeting 2024.1+ should use [Kotlin coroutines](kotlin_coroutines.md), which provide the cancellation mechanism out of the box.
>
{style="warning"}

Background processes are managed by [`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java), which has plenty of methods to execute the given code with a modal (dialog), non-modal (visible in the status bar), or invisible progress.
In all cases, the code is executed on a background thread, which is associated with a [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) object.
The current thread's indicator can be retrieved any time via `ProgressIndicatorProvider.getGlobalProgressIndicator()`.

For visible progresses, threads can use `ProgressIndicator` to notify the user about the current status: for example, set text or visual fraction of the work done.

### Starting

TODO

### Canceling

TODO

### Tracking Progress

TODO

## Process Cancellation

Progress indicators also provide the means to handle cancellation of background processes, either by the user (pressing the <control>Cancel</control> button) or from code (for example, when the current operation becomes obsolete due to some changes in the project).
The progress can be marked as canceled by calling `ProgressIndicator.cancel()`.
The process reacts to this by calling `ProgressIndicator.checkCanceled()` (or `ProgressManager.checkCanceled()` if no indicator instance is available in the current context).
This call throws a special unchecked [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) (PCE) if the background process has been canceled.

All code working with [PSI](psi.md) or in other kinds of background processes must be prepared for PCE being thrown at any point.
This exception must never be logged but rethrown, and it will be handled in the infrastructure that started the process.
Use inspection <control>Plugin DevKit | Code | 'ProcessCanceledException' handled incorrectly</control> (2023.3).

The `checkCanceled()` should be called often enough to guarantee the process's smooth cancellation.
PSI internals have a lot of `checkCanceled()` calls inside.
If a process does lengthy non-PSI activity, insert explicit `checkCanceled()` calls so that it happens frequently, for example, on each _Nth_ loop iteration.
Use inspection <control>Plugin DevKit | Code | Cancellation check in loops</control> (2023.1).


> Throwing PCE from `ProgressIndicator.checkCanceled()` can be disabled in the [internal mode](enabling_internal.md) for development (for example, while debugging the code) by invoking:
> - <ui-path>Tools | Internal Actions | Skip Window Deactivation Events</ui-path> (2023.2+)
> - <ui-path>Tools | Internal Actions | Disable ProcessCanceledException</ui-path> (pre-2023.2)
>
{style="note" title="Disabling ProcessCanceledException"}
