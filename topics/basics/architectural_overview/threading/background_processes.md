<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Background Processes

<link-summary>Background process is a computation executed on a background thread with the possibility of interrupting it and tracking its progress.</link-summary>

Background process is a time-consuming computation usually executed on a background thread.
The IntelliJ Platform executes background processes widely and provides two main ways to run them by plugins:
- [Progress API](#progress-api) that allows for cancelling tasks and tracking their progress
- [`Application.executeOnPooledThread()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) methods for running background tasks that don't need progress tracking

## Progress API
<primary-label ref="obsolete-2024.1"/>

> Plugins targeting 2024.1+ should use [Kotlin coroutines](kotlin_coroutines.md), which is a more performant solution and provides the cancellation mechanism out of the box.
>
> See [](execution_contexts.topic) for coroutine-based APIs to use in different contexts.
>
{style="warning" title="Use Kotlin Coroutines"}

The Progress API allows running processes on BGT with a modal (dialog), non-modal (visible in the status bar), or invisible progress.
It also allows for process cancellation and progress tracking (as a fraction of work done or textual).

The key classes are:
- [`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) – provides methods to execute and manage background processes
- [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) – an object associated with a running process.
  It allows cancelling the process and optionally tracking its progress.
  The current thread's indicator can be retrieved any time via `ProgressManager.getProgressIndicator()`.

  There are many `ProgressIndicator` implementations and the most commonly used are:
  - [`EmptyProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/EmptyProgressIndicator.java) – invisible (ignores text/fraction-related methods), used only for cancellation tracking.
    Remembers its creation [modality state](threading_model.md#invoking-operations-on-edt-and-modality).
  - [`ProgressIndicatorBase`](%gh-ic%/platform/analysis-impl/src/com/intellij/openapi/progress/util/ProgressIndicatorBase.java) – invisible but can be made visible by subclassing.
    Stores text/fraction and allows retrieving them and possibly show in the UI.
    Non-modal by default.
  - [`ProgressWindow`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) – visible progress, either modal or background.
    Usually not created directly but instantiated internally inside `ProgressManager.run` methods.
  - [`ProgressWrapper`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/progress/util/ProgressWrapper.java) – wraps an existing progress indicator, usually to fork another thread with the same cancellation policy.
    Use [`SensitiveProgressWrapper`](%gh-ic%/platform/core-impl/src/com/intellij/concurrency/SensitiveProgressWrapper.java) to allow that separate thread's indicator to be canceled independently of the main thread.

- [`Task`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/Task.java) – encapsulates an operation to perform.
  See `Task`'s inner subclasses for backgroundable, modal and other base task classes.

### Starting

Background processes encapsulated within `Task` can be run with queueing them.
Example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
object : Task.Backgroundable(project, "Synchronizing data", true) {
  override fun run(indicator: ProgressIndicator) {
    // operation
  }
}
  .setCancelText("Stop loading")
  .queue()
```
</tab>
<tab title="Java" group-key="java">

```java
new Task.Backgroundable(project, "Synchronizing data", true) {
  public void run(ProgressIndicator indicator) {
    // operation
  }
}
  .setCancelText("Stop loading")
  .queue();
```
</tab>
</tabs>

> To run a backgroundable task under a custom progress indicator, for example, `EmptyProgressIndicator` to hide progress, use:
>   ```kotlin
>   ProgressManager.getInstance()
>       .runProcessWithProgressAsynchronously(
>           backgroundableTask, EmptyProgressIndicator())
>   ```
> Note that hiding progress from users should be avoided as it may break the [UX](plugin_user_experience.md).

`ProgressManager` also allows running `Runnable` and `Computable` instances not wrapped within `Task` with several `run*()` methods.
Example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
ProgressManager.getInstance().runProcessWithProgressSynchronously(
    ThrowableComputable {
      // operation
    },
    "Synchronizing data", true, project
)
```
</tab>
<tab title="Java" group-key="java">

```java
ProgressManager.getInstance().runProcessWithProgressSynchronously(
    () -> {
      // operation
    },
    "Synchronizing data", true, project
);
```
</tab>
</tabs>

### Cancellation

The most important feature of Progress API is the ability to cancel a process if the result of the computation gets irrelevant.
Cancellation can be performed either by a user (pressing a cancel button) or from code when the current operation becomes obsolete due to some changes in the project.
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

Being prepared for cancellation requests in plugin code is crucial for saving CPU resources and responsiveness of the IDE.

#### Requesting Cancellation

The process can be marked as canceled by calling `ProgressIndicator.cancel()`.
This method is called by the infrastructure that started the process, for example, when the mentioned cancel button is clicked, or by code responsible for invoking code completion.

The `cancel()` method marks the process as canceled, and it is up to the running operation to actually cancel itself.
See the section below for handling cancellation.

#### Handling Cancellation

The cancellation is handled in the running process code by calling `ProgressIndicator.checkCanceled()`, or `ProgressManager.checkCanceled()`, if no indicator instance is available in the current context.

If the process was [marked as canceled](#requesting-cancellation), then the call to `checkCanceled()` throws an instance of a special unchecked [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) (PCE) and the actual cancellation happens.
This exception doesn't represent any error and is only used to handle cancellation for convenience.
It allows canceling processes deeply in the call stack, without the need to handle cancellation on each level.

PCE is handled by the infrastructure that started the process and must never be logged or swallowed.
In case of catching it for some reason, it must be rethrown.
Use inspection
<control>Plugin DevKit | Code | Cancellation exception handled incorrectly</control> (2024.3)
(previously named <control>'ProcessCanceledException' handled incorrectly</control> (2023.3)).

All code working with [PSI](psi.md) or in other kinds of background processes must be prepared for PCE being thrown at any point.

**The `checkCanceled()` should be called by the running operation often enough** to guarantee the process's smooth cancellation.
PSI internals have a lot of `checkCanceled()` calls inside.
If a process does lengthy non-PSI activity, insert explicit `checkCanceled()` calls so that it happens frequently, for example, on each _Nth_ loop iteration.
Use inspection <control>Plugin DevKit | Code | Cancellation check in loops</control> (2023.1).

> Throwing PCE from `checkCanceled()` can be disabled in the [internal mode](enabling_internal.md) for development (for example, while debugging the code) by invoking:
> - <ui-path>Tools | Internal Actions | Skip Window Deactivation Events</ui-path> (2023.2+)
> - <ui-path>Tools | Internal Actions | Disable ProcessCanceledException</ui-path> (pre-2023.2)
>
{style="note" title="Disabling ProcessCanceledException"}

### Tracking Progress

Displaying progress to the user is achieved with:
- `ProgressIndicator` - if available in the current context
- `ProgressManager` - if no indicator instance is available in the current context

To report progress with `ProgressIndicator`, use the following methods:
- `setText(String)` – sets the progress text displayed above the progress bar
- `setText2(String)` – sets the progress details text displayed under the progress bar
- `setFraction(double)` – sets the progress fraction: a number between 0.0 (nothing) and 1.0 (all) reflecting the ratio of work that has already been done.
  Only works for determinate indicator.
  The fraction should provide the user with an estimation of the time left.
  If this is impossible, consider making the progress indeterminate.
- `setIndeterminate(boolean)` – marks the progress indeterminate (for processes that can't estimate the amount of work to be done) or determinate (for processes that can display the fraction of the work done using `setFraction(double)`).

`ProgressManager` allows for reporting progress texts through `progress()`/`progress2()` methods, which are counterparts of `ProgressIndicator.setText()`/`setText2()`.
In addition, it exposes the `ProgressIndicator.getProgressIndicator()` method for getting an indicator instance associated with the current thread.

## Pre-2025.1: `ProcessCanceledException` and Debugging

Sometimes, a PCE is thrown from `checkCanceled()` in the code inspected by a plugin developer during a debugging session.
If the developer tries to step over a line and this line throws PCE (potentially from a deep call frame), the next place where the debugger stops is a catch/finally block intercepting the exception.
This greatly breaks the developer's workflow as the analysis must be started over.

> With the Plugin DevKit plugin installed, the debugger will prevent PCE from being thrown during stepping and evaluation with no additional actions needed.
>
{title="2025.1+"}

This situation can be avoided by enabling an action available in the [internal mode](enabling_internal.md):

<tabs>
<tab title="2023.2+">

<ui-path>Tools | Internal Actions | Skip Window Deactivation Events</ui-path>

Action disabling window deactivation events.
This helps avoid PCEs thrown as a result of deactivating the IDE development instance window.
For example, when the IDE window is deactivated, it closes the completion popup, which, in turn, cancels the completion process.

</tab>

<tab title="Earlier Versions">

<ui-path>Tools | Internal Actions | Disable ProcessCanceledException</ui-path>

Action disabling throwing `ProcessCanceledException`.

</tab>
</tabs>

<include from="snippets.topic" element-id="missingContent"/>
