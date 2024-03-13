<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Tips and Tricks

<link-summary id="link-summary">Tips and tricks to use coroutines efficiently.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

This section presents techniques to use coroutines efficiently and avoid common pitfalls.

## Switching Between the Background and UI Threads

Avoid the `invokeLater`-style often found in Java:

```kotlin
launch(Dispatchers.EDT) {
  val uiData = collectUiData()
  // switch to Default:
  launch(Dispatchers.Default) {
    val result = compute(uiData)
    // switch to EDT again:
    launch(Dispatchers.EDT) {
      applyUiData(result)
    }
  }
}
```

The recommended approach:

```kotlin
launch(Dispatchers.EDT) {
  val uiData = collectUiData()
  // switch to Default:
  val result = withContext(Dispatchers.Default) {
    compute(uiData)
  }
  // this will be resumed on EDT automatically:
  applyUiData(result)
}
```

## Dispatching to the End of a Queue

In some cases, it is required to exit the current EDT event and continue after all events in the queue are processed.
In a non-coroutine context, it could be implemented like in the following snippet:

```kotlin
invokeLater {
  step1()
  invokeLater {
    step2()
    invokeLater {
      step3()
    }
  }
}
```

In a coroutine context, use the following approach:

```kotlin
withContext(Dispatchers.EDT) {
  step1()
  yield() // suspends here, dispatches the following block again on EDT
  step2()
  yield()
  step3()
}
```

This approach works with any sequential dispatcher, e.g., created with `Dispatchers.Default.limitedParallelism(1)`.

The same applies to [`runBlockingCancellable`](launching_coroutines.md#using-runblockingcancellable):

```kotlin
runBlockingCancellable {
  println(1)
  launch {
    print(2)
    yield()
    print(3)
  }
  print(4)
  yield()
  print(5)
  yield()
  print(6)
}
// Output: 142536
```

## Scheduling Tasks With a Fixed Delay

There is no `scheduleWithFixedDelay()` in coroutines, because it can be easily implemented with the following snippet:

```kotlin
val job = coroutineScope.launch {
  delay(initialDelayMs)
  while (true) {
    action() // can be suspending as well
    delay(delayMs)
  }
}
```

When the job is no longer needed, remember to cancel the launched coroutine:
```kotlin
job.cancel()
```
or the whole scope:
```kotlin
coroutineScope.cancel()
```

## Limiting Dispatcher Parallelism

Each call of [`limitedParallelism()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/limited-parallelism.html) creates a new independent dispatcher instance, effectively not limiting the parallelism:

```kotlin
suspend fun doSomething() {
  withContext(Dispatchers.Default.limitedParallelism(3)) {
    // ...
  }
}
```

Instead, store the dispatcher instance into a static property and use it as a context:

```kotlin
private val myDispatcher = Dispatchers.Default.limitedParallelism(3)

suspend fun doSomething() {
  withContext(myDispatcher) {
    // ...
  }
}
```

## Changing Modality State

Avoid [changing modality state](general_threading_rules.md#modality-and-invokelater) in the middle of a running coroutine:

```kotlin
cs.launch {
  // ...
  withContext(Dispatchers.EDT + ModalityState.any().asContextElement()) {
    // ...
  }
}
```

Add the modality state to the context when launching a coroutine:

```kotlin
cs.launch(ModalityState.current().asContextElement()) {
  // ...
  withContext(Dispatchers.EDT) {
    // ...
  }
}
```

It is possible that the coroutine is launched as a response to a user event from the UI thread, where `ModalityState.current()` is available.

If the coroutine is launched from a background thread, then it should not be invoked on top of an unrelated dialog anyway.
The absence of the context modality state is effectively equivalent to specifying `ModalityState.nonModal()`.

## Reading Coroutine Dumps

The <ui-path>Help | Diagnostic Tools | Dump Threads</ui-path> action creates a thread dump, which is useful when investigating freezes or deadlocks.
Thread dumps include all application threads and coroutines existing at the moment of dump creation.

A coroutine dump format is:

```
- parent coroutine header
	at stackframe
	at stackframe
	...
	- child coroutine 0 header
		at stackframe
		at stackframe
		...
		- grandchild coroutine header
			at stackframe
			at stackframe
			...
	- child coroutine 1 header
	- child coroutine 2 header
		at stackframe
		at stackframe
		...
```

Each coroutine entry starts with a `-` character.
Indentation represents parent-child relationships.
A coroutine entry may not include a stacktrace (see `child coroutine 1 header`) because it has no executable body, or it did not start executing yet.

An example coroutine header:
```
-[x5 of] "my task":StandaloneCoroutine{Active}, state: SUSPENDED [ComponentManager(ApplicationImpl@xxxxxxxx), Dispatchers.EDT]
```

Its format is as follows:
```
-[xN of] "name":CoroutineClass{JobState}, state: STATE [context]
```

where:

- `[xN of]` - a particular subtree is repeated N times (included only if N > 1)

- `name` - a coroutine name. Notable names:
  - `ApplicationImpl@xxxxxxxx container` - application coroutine.
  - `ProjectImpl@xxxxxxxx container` - project coroutine.
  - `com.intellij.*.AClass` - a coroutine bound to some specific class instance, e.g., an extension or a service.
    Unnamed coroutines are hard to identify, so it is recommended to add `CoroutineName(someName)` into a coroutine context.
  - `(a x b)` - an intersection of coroutines `a` and `b`, e.g., `(ApplicationImpl@56422718 x com.example.myplugin)` is an intersection of the application and a plugin scope.
    See also [](coroutine_scopes.md#intersection-scopes).

- `CoroutineClass{JobState}` - a coroutine's `toString()`:
  - `CoroutineClass` - a coroutine class. Notable values:
    - `StandaloneCoroutine` and `LazyStandaloneCoroutine` are created by [`launch`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html).
    - `DeferredCoroutine` and `LazyDeferredCoroutine` are created by [`async`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html).
    - `BlockingCoroutine` is created by [`runBlockingCancellable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt).
    - `ProducerCoroutine` is created by [`produce`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/produce.html).
    - `ChildScope` is created by [`childScope`](%gh-ic-master%/platform/util/coroutines/src/coroutineScope.kt) or [`namedChildScope`](%gh-ic-master%/platform/util/coroutines/src/coroutineScope.kt).
  - `JobState` - a coroutine `Job`'s state.
    Possible states and transition can be found in the [`Job`'s KDoc](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/).

- `state: STATE` - a coroutine's state. Possible states:
  - `CREATED` - a coroutine was created but not yet started.
  - `SUSPENDED` - a coroutine was executed up until the last frame in the stacktrace.
    This is where it was last seen running.
  - `RUNNING` - a coroutine is currently executed by a thread.
    Its stacktrace reflects what the coroutine is doing right now (probably blocked waiting for something, otherwise a `RUNNING` coroutine is rarely seen unless it’s doing CPU-intensive work).

- `[context]` - a coroutine context.
  Context elements are separated with `,`.
  Notable context elements:
  - `no parent and no name` comes from the startup tracer.
    It should not be present in application/project coroutines and their children.
  - `ComponentManager(ApplicationImpl@xxxxxxxx)` - application or project, which serves as the coroutine parent.
  - `BlockingEventLoop`, `Dispatchers.Default`, `Dispatchers.IO`, `LimitedDispatcher`, `Dispatchers.EDT` - a [coroutine dispatcher](coroutine_dispatchers.md).
    Absence means [Dispatchers.Default](coroutine_dispatchers.md#default-dispatcher).
  - `ModalityState.xxx` - modality state.
    Absence means [`ModalityState.nonModal()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ModalityState.java).

    When EDT is in a modal state, all non-modal coroutines are suspended until the modal state ends, and EDT goes back to non-modal state as well.

    Some `RUNNING` coroutines might block in an `invokeAndWait` call, which means that `invokeAndWait` used non-modal default modality state for one of two reasons:
    - the coroutine contains the correct modality state in its context, but `invokeAndWait` is not aware of it
    - a modal coroutine awaits another unrelated coroutine, which in turn requires non-modal EDT to complete.

    Same problems can be found in regular thread dumps and blocking code, but coroutines suspend instead of blocking a thread, so it’s only possible to observe the last seen frame, which is usually enough.

<include from="snippets.md" element-id="missingContent"/>
