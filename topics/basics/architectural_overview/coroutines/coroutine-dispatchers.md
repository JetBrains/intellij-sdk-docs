<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Dispatchers

<link-summary>Explanation of coroutine dispatcher in the IntelliJ Platform.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

Coroutines are always executed in a [context](https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html) represented by [`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/).
One of the most important parts of the context is a dispatcher, which determines what thread or thread pool the corresponding coroutine is executed on.

In the IntelliJ Platform, coroutines are executed on three main dispatchers:
- [Default Dispatcher](#default-dispatcher)
- [IO Dispatcher](#io-dispatcher)
- [EDT Dispatcher](#edt-dispatcher)

## Default Dispatcher

The [`Dispatchers.Default`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html) dispatcher is used for performing CPU-bound tasks.

It ensures that the number of tasks running in parallel does not exceed the number of CPU cores.
A hundred threads performing CPU-bound work on a machine with 10 CPU cores can result in threads competing for CPU time and excessive thread switching.
This makes the IDE effectively slower, hence the limitation.
Using the default dispatcher (or its [`limitedParallelism()`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/limited-parallelism.html) slice) enables a consistent CPU load.

## IO Dispatcher

The [`Dispatchers.IO`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-i-o.html) dispatcher is used for performing IO operations like reading/writing to files, network, executing external processes, etc.

It should be used at the very deep moment in the trace right before the actual IO operation happens and exited as soon as the operation is finished.
Example:

<compare first-title="Wrong" second-title="Correct" type="top-bottom">

```kotlin
suspend fun readDataFromFile(): Data {
  return withContext(Dispatchers.IO) {
    val fileName = computeFileName()
    val bytes = readFile(fileName)
    Data(parseBytes(bytes))
  }
}
```

```kotlin
suspend fun readDataFromFile(): Data {
  val fileName = computeFileName()
  val bytes = withContext(Dispatchers.IO) {
    readFile(fileName)
  }
  return Data(parseBytes(bytes))
}
```

</compare>

## EDT Dispatcher

The [`Dispatchers.EDT`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt) dispatcher is used for executing UI actions on the Swing Event Dispatch Thread.
`Dispatchers.EDT` dispatches onto the EDT within the context [modality state](general_threading_rules.md#modality-and-invokelater).

In Kotlin, a standard dispatcher for UI-based activities is [`Dispatchers.Main`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html).
In the IntelliJ Platform, the EDT dispatcher is also installed as `Dispatchers.Main` so both can be used, however always prefer `Dispatchers.EDT`.
Use `Dispatchers.Main` only if the coroutine is IntelliJ Platform-context agnostic (e.g., when it can be executed outside the IntelliJ Platform context).
Use `Dispatchers.EDT` when in doubt.

## Dispatchers vs. Threads

The dispatcher concept is a higher level of abstraction over threads.
While the code is always executed on threads, do not think about dispatchers as specific thread instances.

A single coroutine is not bound to the same thread during the whole execution time.
It may happen that a coroutine starts on thread A, is suspended, and finished on thread B, even if the whole is executed with the same dispatcher context.

### ThreadLocal Alternative

The consequence of dispatching coroutines on unpredictable thread instances is that `ThreadLocal` class is unusable in coroutines.

If it is required to store values from coroutines in a thread context, use [`ThreadContextElement`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-thread-context-element/).
It is possible to wrap a `ThreadLocal` instance into `ThreadContextElement` with `ThreadLocal.asContextElement()`.
