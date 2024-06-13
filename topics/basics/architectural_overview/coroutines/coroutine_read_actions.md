<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Read Actions
<primary-label ref="2024.1"/>

<link-summary id="link-summary">Executing read actions in coroutines.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

The concept of read/write locks and running blocking and cancellable read actions is explained in the Threading section:
- [](general_threading_rules.md#read-write-lock)
- [](general_threading_rules.md#read-action-cancellability)

This section explains running read actions (RA) in coroutines specifically.

## Coroutine Read Actions API

Running RA from coroutines is executed with `*ReadAction*` functions from [`coroutines.kt`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) (see their KDocs for the details).
Functions can be divided into two groups, which differ in reacting to an incoming write action (WA):

| Write Allowing Read Action (WARA) | Write Blocking Read Action (WBRA) |
|-----------------------------------|-----------------------------------|
| `readAction`                      | `readActionBlocking`              |
| `smartReadAction`                 | `smartReadActionBlocking`         |
| `constrainedReadAction`           | `constrainedReadActionBlocking`   |

WARA is canceled when a parent coroutine is canceled or a WA arrives.

WBRA is canceled only when a parent coroutine is canceled.
It blocks WA until finishing its lambda.

> It is important to note that in the coroutines context, default functions (without the `Blocking` suffix) behavior prioritizes WA.
>
> In contrast, in the non-coroutine context, [`Application.runReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) and similar methods (without any prefix/suffix) perform RA blocking WA, whereas RA allowing WA are invoked via the [`NonBlockingReadAction` API](general_threading_rules.md#read-action-cancellability).
>
> Be careful when migrating the code running read actions to coroutines.
>
{style="warning" title="Naming Convention"}

### Write Allowing Read Action vs. `NonBlockingReadAction`

WARA API is simpler than [`NonBlockingReadAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/NonBlockingReadAction.java) (NBRA).
WARA doesn't need the following API methods:
- `submit(Executor backgroundThreadExecutor)` because this is a responsibility of the coroutine dispatcher
- `executeSynchronously()` because effectively they are executed in the current coroutine dispatcher already
- `expireWhen(BooleanSupplier expireCondition)`/`expireWith(Disposable parentDisposable)`/`wrapProgress(ProgressIndicator progressIndicator)` because they are canceled when the calling coroutine is canceled
- `finishOnUiThread()` because this is handled by switching to the [EDT dispatcher](coroutine_dispatchers.md#edt-dispatcher).
  Note that the UI data must be pure (e.g., strings/icons/element pointers), which inherently cannot be invalidated during the transfer from a background thread to EDT.

  In the case of using NBRA's `finishOnUiThread` to start a write action, the coroutine equivalent is `readAndWriteAction`:
  ```kotlin
  readAndWriteAction {
    val computedData = computeDataInReadAction()
    writeAction {
      applyData(computedData)
    }
  }
  ```
  It provides the same guarantees as `finishOnUIThread` (no WA between `computeDataInReadAction` and `applyData`), but it is not bound to EDT.
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

#### Read Action Cancellability

Suspending read actions use coroutines as the underlying framework.

WARA (invoked with [mentioned `*ReadAction` functions](#coroutine-read-actions-api)) may make several attempts to execute its lambda.
The block needs to know whether the current attempt was canceled.
`*ReadAction` functions create a child [`Job`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/) for each attempt, and this job becomes canceled when a write action arrives.
`*ReadAction` restarts the block if it was canceled by a write action, or throws `CancellationException` if the calling coroutine was canceled, causing the cancellation of the child `Job`.

To check whether the current action was canceled, clients must call [`ProgressManager.checkCanceled()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/ProgressManager.java), which was adjusted to work in coroutines.
Clients must not throw [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) manually.

<include from="snippets.md" element-id="missingContent"/>
