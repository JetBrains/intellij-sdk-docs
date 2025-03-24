<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutines on EDT and Locks

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

Sometimes, errors similar to the below can be observed in the IDE:

```
Access is allowed with explicit read lock.
Now each coroutine scheduled on EDT wrapped in implicit write intent
lock (which implies read lock too). This implicit lock will be removed
in future releases.
Please, use explicit lock API like ReadAction.run(),
WriteIntentReadAction.run(), readAction() or writeIntentReadAction()
to wrap code which needs lock to access model or PSI.
Please note, that read action API can re-schedule your code
to background threads, if you are sure that your code need
to be executed on EDT, you need to use write intent read action.
```

Currently, this is not an actual error.
Nothing is broken and no data races occurred.
This assertion message is a way to prepare plugins for the [future changes](#planned-changes) in the IntelliJ Platform.

Note that this error is shown only when the IDE [internal mode](enabling_internal.md) is enabled.

## Error Context

There are two contexts in which these errors can occur:
- **Coroutines dispatched on EDT**

  Consider code that is run on [`Dispatchers.EDT`](coroutine_dispatchers.md#edt-dispatcher) with the following construct (or any of the wrappers for this code):

  ```kotlin
  withContext(Dispatchers.EDT) {
    // ...
  }
  ```

  Currently, the execution system wraps the executed code into a [write intent lock](threading_model.md#read-write-lock).
  Such a write intent lock is called _implicit_.

- **Specific GUI events**

  Lock covers all base events (like mouse events, keyboard events, focus change events, etc.), and such lock isn't counted as _implicit_ for now.

  But some events can be missed and need personal attention.
  Especially if such events trigger listeners and the IntelliJ Platform internal message bus.

When code in above contexts checks for the state of lock with [`ThreadingAssertions.assertReadAccess()`/`assertWriteIntentReadAccess()`](%gh-ic%/platform/core-api/src/com/intellij/util/concurrency/ThreadingAssertions.java) (or their deprecated [`Application`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) counterparts), an error is reported, but the assertion passes.

Only one error for each executed block is reported (see 2. in [](#planned-changes) about a problem with suspending).

## Planned Changes

In future releases of the IntelliJ Platform, this implicit lock acquisition will be removed, and warnings will become actual errors (assertions will
fail).

The current implicit lock acquisition behavior is not optimal due to the following reasons:

1. Not every code block executed on EDT needs any lock at all.
   More acquired locks limit concurrency and degrade performance.
2. Executed code block could have wrong assumptions, as it can *suspend*, and at this moment lock will be released and obtained later, on resume.
   Code looks like it is executed in a single locking session, but in reality it is not.

## Required Action

It is preferable to:
- minimize locks usage
- acquire locks explicitly to make the locked code blocks clear

Code triggering the error should be reviewed and adjusted in a proper way: code which really needs locking should be determined, and proper explicit locking should be used.

### Identifying Code Requiring Locking

Each report includes a stacktrace which leads to assertion causing the error.

Wrapping into lock code calling the assertion is seldom the right decision (except some property getters).
Often it is necessary to determine which code higher in the stack constitutes a region which should be protected at once.
Often (but not always) whole code scheduled to execute on the EDT dispatcher must be protected.
Sometimes something in the middle between these extremes.

It is impossible to give one instruction that covers all cases: a proper solution is always context-dependent.
The sections below provide some advice on how to find a place requiring locking.

#### Coroutine Changing Dispatcher Explicitly

A simple case is when in the middle of a coroutine there is `withContext(Dispatchers.EDT)` or similar explicit rescheduling on EDT.

In such a case, focus on frames between the assertion call (top frame) and the closets call to `invokeSuspend` of any class.
Typically, the location of `invokeSuspend` (invisible in code) is properly indicated in stacktrace, too.

For example:

```
at com.intellij.util.concurrency.ThreadingAssertions.reportImplicitWriteIntent(ThreadingAssertions.java:187)
at com.intellij.util.concurrency.ThreadingAssertions.assertWriteIntentReadAccess(ThreadingAssertions.java:178)
at com.intellij.openapi.application.impl.ApplicationImpl.assertWriteIntentLockAcquired(ApplicationImpl.java:946)
at com.intellij.openapi.command.impl.CoreCommandProcessor.executeCommand(CoreCommandProcessor.java:200)
at com.intellij.openapi.command.impl.CoreCommandProcessor.executeCommand(CoreCommandProcessor.java:188)
at com.intellij.openapi.command.WriteCommandAction$BuilderImpl.doRunWriteCommandAction(WriteCommandAction.java:154)
at com.intellij.openapi.command.WriteCommandAction$BuilderImpl.run(WriteCommandAction.java:121)
at com.intellij.openapi.command.WriteCommandAction$BuilderImpl.compute(WriteCommandAction.java:164)
at com.intellij.httpClient.http.request.run.HttpScratchRequestPostProcessor.updateRequest(HttpScratchRequestPostProcessor.java:54)
at com.intellij.httpClient.http.request.run.HttpRequestFilePostProcessor.onResponseExecuted(HttpRequestFilePostProcessor.java:31)
at com.intellij.httpClient.http.request.run.HttpRequestCompositePostProcessor.onResponseExecuted(HttpRequestCompositePostProcessor.java:18)
at com.intellij.httpClient.http.request.run.controller.HttpClientExecutionControllerHelper$processResponse$2$2.invokeSuspend(HttpClientExecutionControllerHelper.kt:158)
at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
at com.intellij.openapi.application.impl.DispatchedRunnable.run(DispatchedRunnable.kt:44)
at com.intellij.openapi.application.TransactionGuardImpl.runWithWritingAllowed(TransactionGuardImpl.java:240)
at com.intellij.openapi.application.TransactionGuardImpl.access$100(TransactionGuardImpl.java:25)
```

In the stacktrace above, a good start for investigation is `HttpClientExecutionControllerHelper.kt:158`.
Sometimes such frames don't contain proper line information, but the next frame may help to identify the actual location.

In such cases, it is often possible to wrap into lock the whole code scheduled on EDT.

#### Callbacks (Coroutine and Blocking)

A more complex case is when code is called as callback via coroutines or even as blocking code via some events.

Such cases can be recognized by some generic classes near the top of the stack.
Main suspects are:
- `EventDispatcher`
- `MessageBus`

Note that this list is not exhaustive.

Another pattern for this case is the pair of the following calls on the stack, which means default event processing:
```
EventQueue.dispatchEvent()
IdeEventQueue.defaultDispatchEvent()
```

Note that this pattern is usable only if there is no `invokeSuspend` higher on the stack.

In this case, it is better to find the place where the callback is registered.
It may be not so trivial, but searching by usages of some code in stacktrace typically works.

## Locking API Recommendations

There are several locking APIs to use for fixing these errors.
The following sections list them in the order of preference, from the most recommended to the least.

### No API

Sometimes the code in question doesn't need locking and assertion at all.

In some cases, assertion for write intent lock is used instead of assertion for execution on EDT.
If it is clear that code doesn't touch PSI or project model or other internal data and if code is under your control, remove the assertion or change it to the proper one.

### Suspending `readAction()`

The [`readAction()` API](coroutine_read_actions.topic#coroutine-read-actions-api) is the best and least intrusive.
It can suspend by itself but doesn't allow the executed code block to suspend (see 2. in [](#planned-changes) why it is the desired behavior).

One property of `readAction()` makes it not as universal as expected: it uses [`Dispatchers.Default`](coroutine_dispatchers.md#default-dispatcher) for code execution.
Sometimes it is not desired, as the coroutine in question was scheduled on [`Dispatchers.EDT`](coroutine_dispatchers.md#edt-dispatcher) for a reason.

Anyway, it is suspicious when a read action cannot be executed on a background thread and a part of coroutine code cannot be suspended and rescheduled on EDT.
This may signal a code issue.

Note that this API cannot be used in the [Blocking Context](execution_contexts.topic#blocking-context).

### Suspending `readAndWriteAction()`

A good choice for a coroutine which needs to make some preparation work under a read lock and then complete work under the write lock.

It cannot be used as a drop-in fix - migration to this API requires code reorganization.
Consider rewriting code to use it, if possible.

### Blocking `ReadAction.run()`/`compute()`

[`ReadAction.run()` and `ReadAction.compute()`](threading_model.md#read-actions) are as smart as [`readAction()`](#suspending-readaction), but they are always executed on the calling thread.

Use them if a read action is required, but it is unacceptable to reschedule code execution on a different dispatcher.

These APIs are marked to use only in the [Blocking Context](execution_contexts.topic#blocking-context), so their usage in the [Suspending Context](execution_contexts.topic#suspending-context-coroutines) will trigger a warning.
It is intentional, as coroutines should be prepared to be rescheduled and should use `readAction()`.

### Suspending `writeIntentReadAction()` and Blocking `WriteIntentReadAction.run()`/`compute()`

These APIs are the last resort.
They are intended to be used by the IntelliJ Platform itself.
Avoid using them in plugin code.

Use it only as a temporal solution if it is hard to quickly rewrite code which checks for write intent lock.

<include from="snippets.topic" element-id="missingContent"/>
