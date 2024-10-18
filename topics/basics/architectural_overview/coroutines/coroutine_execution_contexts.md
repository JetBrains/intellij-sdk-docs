<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Execution Contexts
<primary-label ref="2024.1"/>

<link-summary>Tracking execution progress, checking for cancellations, and switching between different execution contexts.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

The IntelliJ Platform provides APIs that allow tracking the progress of background processes and canceling their execution when they are canceled by a user, or they become obsolete due to some changes in the data model.

Background processes can be executed in three contexts:
- [](#suspending-context)
- [](#blocking-context)
- [](#progress-indicator) (obsolete)

Currently, the Progress Indicator context is the most widely used approach in the IntelliJ Platform.
As the platform's execution model moves towards coroutines, this approach can be considered obsolete.
Starting with 2024.1, it is recommended to execute new code in the [suspending context](#suspending-context).

Once the client code switches to a suspending or a blocking context, it should not switch back to a progress indicator context.

The following sections explain the contexts and provide information about process cancellation, progress tracking, and switching between different contexts.

## Suspending Context

Code [executed in Kotlin coroutines](launching_coroutines.md) is executed in a suspending context.
Since 2024.1, this context is recommended for executing background tasks to maximize CPU utilization.

> Note that executing code in a suspending context is possible only with [Kotlin](using_kotlin.md).
>
{style="warning"}

In a suspending context, methods such as `ProgressManager.checkCanceled()` or `ModalityState.defaultModalityState()` won't have any effect.
Therefore, if their behavior is required, [switch to a blocking context](#suspending-context-switching-to-other-contexts).

> Inspection <control>Plugin DevKit | Code | Forbidden in suspend context method usage</control> reports calling blocking code from suspending context.

### Cancellation Check
{#suspending-context-cancellation-check}

- [`ensureActive()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/ensure-active.html) from Kotlin coroutine's API

> Note that [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) does not work in a coroutine context.
>
{style="warning"}

### Progress Reporting
{#suspending-context-progress-reporting}

- [`ProgressStep`](%gh-ic%/platform/util/progress/src/impl/ProgressStep.kt) - a step-based progress reporting (see its KDoc for details)
- [`RawProgressReporter`](%gh-ic%/platform/util/progress/src/RawProgressReporter.kt) - a raw text, details, and fraction reporting (invoked via [`reportRawProgress()`](%gh-ic%/platform/util/progress/src/steps.kt))

Any [`report*Progress()`](%gh-ic%/platform/util/progress/src/steps.kt) function should be used inside [`with*Progress()` or `runWithModalProgressBlocking()`](%gh-ic%/platform/progress/shared/src/tasks.kt).
Otherwise, if there is no reporter in the context, using `report*Progress()` will have no effect.

### Switching to Other Contexts
{#suspending-context-switching-to-other-contexts}

- to blocking context: [`blockingContext()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) - enables `ProgressManager.checkCanceled()`, forwards modality state, etc. This function has an opposite behavior to [`runBlockingCancellable()`](#blocking-context-switching-to-other-contexts).
- to progress indicator: unavailable ([`coroutineToIndicator()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) is an internal API and exists only to aid platform migration)

## Blocking Context

Executing tasks in a blocking context means executing them on a thread without access to the [coroutine context](#suspending-context) (basically, in non-suspending functions) and not under [a progress indicator](#progress-indicator).
Such tasks can't be canceled by using coroutines' or progress indicator's cancellation capabilities, which may block threads and consume resources even if the task is no longer relevant.

Usually, plugins should not execute new code in the blocking context.
Always prefer executing tasks in the [suspending context](#suspending-context) or under the [progress indicator](#progress-indicator) if a plugin cannot use Kotlin.

> Functions which schedule execution via [`Application.executeOnPooledThread()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java)
> and similar methods, and which rely on [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java)
> should be annotated with [`@RequiresBlockingContext`](%gh-ic%/platform/core-api/src/com/intellij/util/concurrency/annotations/RequiresBlockingContext.kt)
> to inform clients about the required switch to a blocking context.
>
> Inspection <control>Plugin DevKit | Code | Calling method should be annotated with @RequiresBlockingContext</control> reports missing annotations.

### Cancellation Check
{#blocking-context-cancellation-check}

- [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java)

### Progress Reporting
{#blocking-context-progress-reporting}

Progress reporting is not available in the blocking context.

### Switching to Other Contexts
{#blocking-context-switching-to-other-contexts}

- to coroutine: [`runBlockingCancellable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt). This function has an opposite behavior to [`blockingContext()`](#suspending-context-switching-to-other-contexts).
- to progress indicator: unavailable ([`blockingContextToIndicator()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) is an internal API and exists only to aid platform migration)

## Progress Indicator

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

### Cancellation Check
{#progress-indicator-cancellation-check}

- `ProgressManager.checkCanceled()` - as described in the [](background_processes.md#cancellation) section

### Progress Reporting
{#progress-indicator-progress-reporting}

- current thread's [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) methods
- [`ProgressManager`'s](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) `progress` and `progress2` static methods

### Switching to Other Contexts
{#progress-indicator-switching-to-other-contexts}

- to coroutine: [`runBlockingCancellable`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt)
- to blocking: unavailable

<include from="snippets.md" element-id="missingContent"/>
