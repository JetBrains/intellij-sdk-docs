<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Read Actions

<link-summary id="link-summary">Executing non-blocking and blocking read actions in coroutines.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

The concept of read/write locks and running blocking and cancellable read actions is explained in the Threading section:
- [](general_threading_rules.md#read-write-lock)
- [](general_threading_rules.md#read-action-cancellability)

This section explains running read actions in coroutines specifically.

## Suspending Read Actions API

Running suspending read actions from coroutines is executed with the following functions from [`coroutines.kt`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt):

| Non-blocking            | Blocking                        |
|-------------------------|---------------------------------|
| `readAction`            | `readActionBlocking`            |
| `smartReadAction`       | `smartReadActionBlocking`       |
| `constrainedReadAction` | `constrainedReadActionBlocking` |

See their KDocs for the details.

> It is important to note that in the coroutines context, default functions (without the `Blocking` suffix) behavior is non-blocking.
> In contrast, in the non-coroutine context, [`Application.runReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) and similar methods (without any prefix/suffix) perform blocking read actions whereas non-blocking read actions are invoked via the [`NonBlockingReadAction` API](general_threading_rules.md#read-action-cancellability).
>
> Be careful when migrating the code running read actions to coroutines.
>
{style="warning" title="Naming Convention"}

### Suspending Non-Blocking Read Action vs. `NonBlockingReadAction`

Suspending non-blocking read action (SNBRA) API is simpler than [`NonBlockingReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/NonBlockingReadAction.java) (NBRA).
SNBRA doesn't need the following API methods:
- `submit(Executor backgroundThreadExecutor)` because this is a responsibility of the coroutine dispatcher
- `executeSynchronously()` because effectively they are executed in the current coroutine dispatcher already
- `expireWhen(BooleanSupplier expireCondition)`/`expireWith(Disposable parentDisposable)`/`wrapProgress(ProgressIndicator progressIndicator)` because they are canceled when the calling coroutine is canceled
- `finishOnUiThread()` because this is handled by switching to the [EDT dispatcher](coroutine_dispatchers.md#edt-dispatcher).
  Note that the UI data must be pure (e.g., strings/icons/element pointers), which inherently cannot be invalidated during the transfer from a background thread to the UI thread.
- `coalesceBy(Object ... equality)` because this should be handled by [`Flow.collectLatest()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/collect-latest.html) and/or [`Flow.distinctUntilChanged()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/distinct-until-changed.html).
  Usually, NBRAs are run as a reaction to user actions, and there might be multiple NBRAs running, even if their results are unused.
  Instead of cancelling the read action, in the coroutine world the coroutines are canceled:
  ```kotlin
  eventFlow.collectLatest { event ->
    // the next emitted event will cancel the current coroutine
    // and run it again with the next event
    readAction { readData() }
  }

  eventFlow.distinctUntilChanged().collectLatest { event ->
    // the next emitted event will cancel the current coroutine
    // and run it again with the next event if the next event
    // was not equal to the previous one
    readAction { readData() }
  }
  ```

## Read Action Cancellability

Suspending read actions use coroutines as the underlying framework.

A non-blocking read action (invoked with [mentioned `*ReadAction` functions](#suspending-read-actions-api)) may make several attempts to execute its block.
The block needs to know whether the current attempt was canceled.
`*ReadAction` functions create a child [`Job`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/) for each attempt, and this job becomes canceled when a write action arrives.
`*ReadAction` restarts the block if it was canceled by a write action, or throws `CancellationException` if the calling coroutine was canceled, causing the cancellation of the child `Job`.

To check whether the current action was canceled, clients must call [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java), which was adjusted to work in coroutines.
Clients must not throw [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) manually.
