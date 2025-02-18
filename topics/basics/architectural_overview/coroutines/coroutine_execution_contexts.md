<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Execution Contexts

<link-summary>Tracking execution progress, checking for cancellations, and switching between different execution contexts.</link-summary>

The IntelliJ Platform provides APIs that allow tracking the progress of background processes and canceling their execution when they are canceled by a user, or they become obsolete due to some changes in the data model.

Background processes can be executed in three contexts:
- [](#suspending-context-coroutines) — available since 2024.1
- [](#blocking-context)
- [](#progress-indicator) — obsolete since 2024.1

Currently, the Progress Indicator context is the most widely used approach in the IntelliJ Platform.
As the platform's execution model moves towards [coroutines](launching_coroutines.md), this approach can be considered obsolete.
Starting with 2024.1, it is recommended to execute new code in the [suspending context](#suspending-context-coroutines).

The following sections explain the contexts and provide information about process cancellation, progress tracking, and switching between different contexts.

## Suspending Context (Coroutines)
<primary-label ref="2024.1"/>

Code [executed in Kotlin coroutines](launching_coroutines.md) is executed in a suspending context.
Since 2024.1, this context is recommended for executing background tasks to maximize CPU utilization.

> Note that executing code in a suspending context is possible only with [Kotlin](using_kotlin.md).
>
{style="warning"}

In a suspending context, methods such as `ProgressManager.checkCanceled()` or `ModalityState.defaultModalityState()` won't have any effect.
Therefore, if their behavior is required, [switch to a blocking context](#switching-between-contexts).

> Inspection <control>Plugin DevKit | Code | Forbidden in suspend context method usage</control> reports calling blocking code from suspending context.

## Blocking Context

Executing tasks in a blocking context means executing them on a thread without access to the [coroutine context](#suspending-context-coroutines) (basically, in non-suspending functions) and not under [a progress indicator](#progress-indicator).
Such tasks can't be canceled by using coroutines' or progress indicator's cancellation capabilities, which may block threads and consume resources even if the task is no longer relevant.

Usually, plugins should not execute new code in the blocking context.
Always prefer executing tasks in the [suspending context](#suspending-context-coroutines) or under the [progress indicator](#progress-indicator) if a plugin cannot use Kotlin.

> Functions which schedule execution via [`Application.executeOnPooledThread()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java)
> and similar methods, and which rely on [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java)
> should be annotated with [`@RequiresBlockingContext`](%gh-ic%/platform/core-api/src/com/intellij/util/concurrency/annotations/RequiresBlockingContext.kt)
> to inform clients about the required switch to a blocking context.
>
> Inspection <control>Plugin DevKit | Code | Calling method should be annotated with @RequiresBlockingContext</control> reports missing annotations.

## Progress Indicator
<primary-label ref="obsolete-2024.1"/>

Code executed via the Progress API
([`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java),
[`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java), etc.)
is executed in a progress indicator context.
See the [](background_processes.md#progress-api) section for details.

> Executing code under the progress indicator is obsolete since 2024.1.
> It is advised to use Kotlin coroutines in new code.
>
> Please note that obsolete status does not mean deprecation.
> Executing code using the Progress API is still allowed, but coroutines are recommended as a more performant solution.
>
{style="tip" title="Obsolete approach since 2024.1"}

## Execution Contexts APIs

### Cancellation Check

The following table presents APIs to use for checking whether a task was canceled in different execution contexts.

<table style="header-column">
    <tr>
      <td width="16%">Suspending</td>
      <td>
        <code><a href="https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/ensure-active.html">ensureActive()</a></code> from Kotlin coroutine's API
        <warning>
          Note that <code><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java">ProgressManager.checkCanceled()</a></code> does not work in a suspending context.
        </warning>
      </td>
    </tr>
    <tr>
        <td>Blocking</td>
        <td>
          <code><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java">ProgressManager.checkCanceled()</a></code>
           <p>See <a href="background_processes.md#cancellation">Background Processes: Cancellation</a> for details.</p>
        </td>
    </tr>
    <tr>
      <td>Progress&nbsp;Indicator</td>
        <td>
          <code><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java">ProgressIndicator.checkCanceled()</a></code>, <code><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java">ProgressManager.checkCanceled()</a></code>
           <p>See <a href="background_processes.md#cancellation">Background Processes: Cancellation</a> for details.</p>
        </td>
    </tr>
</table>

### Progress Reporting

The following table presents the possibilities and APIs to use for reporting progress in different execution contexts.

<table style="header-column">
    <tr>
      <td width="16%">Suspending</td>
      <td>
        <ul>
          <li><code><a href="%gh-ic%/platform/util/progress/src/impl/ProgressStep.kt">ProgressStep</a></code> - a step-based progress reporting (see its KDoc for details)</li>
          <li><code><a href="%gh-ic%/platform/util/progress/src/RawProgressReporter.kt">RawProgressReporter</a></code> - a raw text, details, and fraction reporting (invoked via <code><a href="%gh-ic%/platform/util/progress/src/steps.kt">reportRawProgress()</a></code>)</li>
          <li></li>
        </ul>
        <p>
          Any <code><a href="%gh-ic%/platform/util/progress/src/steps.kt">report*Progress()</a></code> function must be used inside <code>withBackgroundProgress()</code>, <code>withModalProgress()</code>, or <code>runWithModalProgressBlocking()</code> from <a href="%gh-ic%/platform/progress/shared/src/tasks.kt">tasks.kt</a>.
          Otherwise, if there is no reporter in the context, using `report*Progress()` will have no effect.
          Example:
        </p>
        <code-block lang="kotlin">
          withBackgroundProgress(...) { // or other
            // ...
            reportProgress { reporter -> // or another report*Progress
              // do tasks and report progress
            }
            // ...
          }
        </code-block>
      </td>
    </tr>
    <tr>
        <td>Blocking</td>
        <td>
          unavailable
        </td>
    </tr>
    <tr>
      <td>Progress&nbsp;Indicator</td>
        <td>
          <a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java"><code>ProgressIndicator</code>'s</a> or <a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java"><code>ProgressManager</code>'s</a> methods
           <p>See <a href="background_processes.md#tracking-progress">Background Processes: Tracking Progress</a> for details.</p>
        </td>
    </tr>
</table>

### Switching Between Contexts

The following table presents the possibilities and APIs to use for switching between different execution contexts.

<table style="both">
    <tr>
        <td width="16%"></td>
        <td>To&nbsp;Suspending</td>
        <td>To&nbsp;Blocking</td>
        <td>To&nbsp;Progress&nbsp;Indicator</td>
    </tr>
    <tr>
        <td>From Suspending</td>
        <td>-</td>
        <td><code>blockingContext()</code> <sup>1</sup></td>
        <td>unavailable <sup>3</sup></td>
    </tr>
    <tr>
        <td>From Blocking</td>
        <td><code>runBlockingCancellable()</code> <sup>2</sup></td>
        <td>-</td>
        <td>unavailable <sup>4</sup></td>
    </tr>
    <tr>
        <td>From Progress&nbsp;Indicator</td>
        <td><code>runBlockingCancellable()</code> <sup>2</sup></td>
        <td>unavailable</td>
        <td>-</td>
    </tr>
    <tr>
      <td colspan="4">
        <sup>1</sup> <i><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt"><code>blockingContext()</code></a> enables <code>ProgressManager.checkCanceled()</code>, forwards modality state, etc. It has an opposite behavior to <code>runBlockingCancellable()</code>.</i><br/>
        <sup>2</sup> <i><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt"><code>runBlockingCancellable()</code></a> has an opposite behavior to <code>blockingContext()</code></i><br/>
        <sup>3</sup> <i><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt"><code>coroutineToIndicator()</code></a> is an internal API to aid platform migration</i><br/>
        <sup>4</sup> <i><a href="%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt"><code>blockingContextToIndicator()</code></a> is an internal API to aid platform migration</i>
      </td>
    </tr>
</table>

It is only possible to:
- switch from the blocking context or progress indicator to the suspending context
- switch from the suspending context to the blocking context

The lack of an API for switching from suspending and blocking contexts to progress indicator is intentional.
Cancellable and trackable tasks should be run in coroutines as the progress indicator is obsolete since 2024.1.
