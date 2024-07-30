<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Background Processes

<link-summary>Background process is a computation executed on a background thread with the possibility of interrupting it and tracking its progress.</link-summary>

Background process is a time-consuming computation usually executed on a background thread.
The IntelliJ Platform executes background processes widely and provides the API to run them by plugins.
The API is called Progress API and allows for cancelling tasks and tracking their progress.

## Progress API

> Note that Progress API is obsolete since 2024.1.
> Plugins targeting 2024.1+ should use [Kotlin coroutines](kotlin_coroutines.md), which is a more performant solution and provides the cancellation mechanism out of the box.
>
> See [](coroutine_execution_contexts.md) for coroutine-based APIs to use in different contexts.
>
{style="warning"}

The Progress API allows running processes on BGT with a modal (dialog), non-modal (visible in the status bar), or invisible progress.
It also allows for process cancellation and progress tracking (as a fraction of work done or textual).

The key classes are:
- [`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) – provides methods to execute and manage background processes
- [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) – an object associated with a running process.
  It allows cancelling the process and optionally tracking its progress.
  The current thread's indicator can be retrieved any time via `ProgressManager.getProgressIndicator()`.

There are many `ProgressIndicator` implementations and the most commonly used are:
- [`EmptyProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/EmptyProgressIndicator.java) – invisible (ignores text/fraction-related methods), used only for cancellation tracking.
  Remembers its creation [modality state](general_threading_rules.md#invoking-operations-on-edt-and-modality).
- [`ProgressIndicatorBase`](%gh-ic%/platform/analysis-impl/src/com/intellij/openapi/progress/util/ProgressIndicatorBase.java) – invisible but can be made visible by subclassing.
  Stores text/fraction and allows retrieving them and possibly show in the UI.
  Non-modal by default.
- [`ProgressWindow`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/progress/util/ProgressWindow.java) – visible progress, either modal or background.
  Usually not created directly but instantiated internally inside `ProgressManager.run` methods.
- [`ProgressWrapper`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/progress/util/ProgressWrapper.java) – wraps an existing progress indicator, usually to fork another thread with the same cancellation policy.
  Use [`SensitiveProgressWrapper`](%gh-ic%/platform/core-impl/src/com/intellij/concurrency/SensitiveProgressWrapper.java) to allow that separate thread's indicator to be canceled independently of the main thread.

### Starting

Background processes can be started by using one of the following APIs.

#### `ProgressManager`

Use one of the `run*()` methods.
Depending on the needs, it allows running processes synchronously/asynchronously, providing progress indicators, callbacks, and more.
See their Javadocs for more details.

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

#### `Task`

Represents a process in a form of a task that can be queued to execute.
See `Task.*` subclasses for backgroundable, modal, and other types.

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

#### `ProgressRunner`

ProgressRunner is a simplified builder-like API for running processes.
It allows for similar options as `ProgressManager` and additionally allows for running the process on write or pooled thread.

Example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
ProgressRunner { _ ->
    // operation
}
  .onThread(ThreadToUse.POOLED)
  .modal()
  .submit()
```
</tab>
<tab title="Java" group-key="java">

```java
new ProgressRunner<>(() -> {
      // operation
})
  .onThread(ThreadToUse.POOLED)
  .modal()
  .submit();
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

Being prepared for cancellation requests in plugin code is crucial for saving the CPU resources and responsiveness of the IDE.

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
Use inspection <control>Plugin DevKit | Code | 'ProcessCanceledException' handled incorrectly</control> (2023.3).

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

Displaying progress to the user is achieved with `ProgressIndicator` or `ProgressManager`, if no indicator instance is available in the current context.

To report progress, use the following methods:
- `setText(String)` – sets text above the progress bar
- `setText2(String)` – sets text under the progress bar
- `setFraction(double)` – sets the progress fraction: a number between 0.0 (nothing) and 1.0 (all) reflecting the ratio of work that has already been done.
  Only works for determinate indicator.
  The fraction should provide the user with a rough estimation of the time left.
  If this is impossible, consider making the progress indeterminate.
- `setIndeterminate(boolean)` – marks the progress indeterminate (for processes that can't estimate the amount of work to be done) or determinate (for processes that can display the fraction of the work done using `setFraction(double)`).

<include from="snippets.md" element-id="missingContent"/>
