<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Tips and Tricks
<primary-label ref="2024.1"/>

<link-summary id="link-summary">Tips and tricks to use coroutines efficiently.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

This section presents techniques to use coroutines efficiently and avoid common pitfalls.

## Switching Between the Background Threads and EDT

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

> Do not use `limitedParallelism(1)` for code synchronization.
> Use [`Mutex`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/) instead.
>
{style="warning"}

## Changing Modality State

Avoid [changing modality state](threading_model.md#invoking-operations-on-edt-and-modality) in the middle of a running coroutine:

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

It is possible that the coroutine is launched as a response to a user event from EDT, where `ModalityState.current()` is available.

If the coroutine is launched from a background thread, then it should not be invoked on top of an unrelated dialog anyway.
The absence of the context modality state is effectively equivalent to specifying `ModalityState.nonModal()`.
