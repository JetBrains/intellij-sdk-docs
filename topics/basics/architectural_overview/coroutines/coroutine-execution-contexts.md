<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Execution Contexts

<link-summary>Tracking execution progress, checking for cancellations, and switching between different execution contexts.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

The IntelliJ Platform provides APIs that allow to track the progress of background processes and cancel their execution, when they are canceled by a user, or they become obsolete due to some changes in the data model.

Background processes can be executed in three contexts:
- [](#suspending-context)
- [](#blocking-context)
- [](#progress-indicator) (obsolete)

Currently, the Progress Indicator context is the most widely used approach in the IntelliJ Platform.
As the platform's execution model moves towards coroutines, this approach can be considered obsolete.
Starting with 2024.1, it is recommended to execute new code in suspending or blocking context.

Once the client code switches to a suspending or a blocking context, it should not switch back to a progress indicator context.

The following sections explain the contexts and provide information about process cancellation, progress tracking, and switching between different contexts.

## Suspending Context

Code executed in Kotlin coroutines is executed in a suspending context.
This context is the default context since 2024.1, and should be preferred to maximize CPU utilization.

> Note that executing code in a suspending context is possible only with [Kotlin](using_kotlin.md).
>
{style="warning"}

In a suspending context, it is forbidden to call blocking functions directly.
See how to [switch to a blocking context](#suspending-context-switching-to-other-contexts).

> Functions which schedule execution via [`Application.executeOnPooledThread()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java)
> and similar methods, and which rely on [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) should be marked with [`@RequiresBlockingContext`](%gh-ic%/platform/core-api/src/com/intellij/util/concurrency/annotations/RequiresBlockingContext.java)
> to inform clients about the required switch to a blocking context.

### Cancellation Check
{#suspending-context-cancellation-check}

- IntelliJ Platform API: [`checkCancelled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) - supports coroutine pause-ability while checking for cancellation
- Kotlin coroutine's API: [`ensureActive()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/ensure-active.html)

> Note that [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) does not work in a coroutine context.
>
{style="warning"}

### Progress Reporting
{#suspending-context-progress-reporting}

- [`ProgressStep`](%gh-ic-master%/platform/util/progress/src/impl/ProgressStep.kt) - a step-based progress reporting (see its KDoc for details)
- [`RawProgressReporter`](%gh-ic%/platform/util/progress/src/RawProgressReporter.kt) - a raw text, details, and fraction reporting (invoked via [`reportRawProgress()`](%gh-ic-master%/platform/util/progress/src/steps.kt))

### Switching to Other Contexts
{#suspending-context-switching-to-other-contexts}

- to blocking context:
  - [`blockingContext()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) - enables `ProgressManager.checkCanceled()`, forwards modality state, etc.
  - [`blockingContextScope()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) - same as `blockingContext()`, but additionally enables tracking of the children completion (structured concurrency)
- to progress indicator: unavailable ([`coroutineToIndicator()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) is internal and exists only to aid platform migration)

## Blocking Context

Code executed on a background thread, not under a coroutine or [a progress indicator](#progress-indicator), is executed in a blocking context.

### Cancellation Check
{#blocking-context-cancellation-check}

- [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) - delegates to [`Cancellation.checkCancelled()`](%gh-ic%/platform/util/src/com/intellij/openapi/progress/Cancellation.java),
  which throws a special [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java)
  with wrapped [`CancellationException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CancellationException.html).
  Note that `Cancellation.checkCancelled()` is internal and must not be used by plugins directly.

### Progress Reporting
{#blocking-context-progress-reporting}

Progress reporting is not available in a blocking context.

### Switching to Other Contexts
{#blocking-context-switching-to-other-contexts}

- to coroutine: [`runBlockingCancellable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt)
- to progress indicator: unavailable ([`blockingContextToIndicator()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) is internal and exists only to aid platform migration)

## Progress Indicator

Code executed via the Progress API ([`ProgressManager`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java), [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java), etc.) is executed in a progress indicator context.
See the [running background processes](general_threading_rules.md#background-processes-and-processcanceledexception) section for details.

> Executing code under progress indicator is obsolete since 2024.1.
>
> New code should use Kotlin coroutines (preferred) or be invoked on background threads with [`Application.executeOnPooledThread()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) and `Application.invokeLater()`.
>
> Please note that obsolete status does not mean deprecation.
> Executing code using the Progress API is still allowed, but the alternatives presented in this section are more performant.
>
{style="warning"}

### Cancellation Check
{#progress-indicator-cancellation-check}

- `ProgressManager.checkCanceled()` - as described in the [](general_threading_rules.md#background-processes-and-processcanceledexception) section

### Progress Reporting
{#progress-indicator-progress-reporting}

- current thread's [`ProgressIndicator`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressIndicator.java) methods
- [`ProgressManager`'s](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java) `progress` and `progress2` static methods

### Switching to Other Contexts
{#progress-indicator-switching-to-other-contexts}

- to coroutine: [`runBlockingCancellable`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) - it installs [`RawProgressReporter`](%gh-ic%/platform/util/progress/src/RawProgressReporter.kt) into coroutine context
- to blocking: unavailable
