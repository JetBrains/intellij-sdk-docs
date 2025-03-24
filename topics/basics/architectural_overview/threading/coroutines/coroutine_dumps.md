<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Dumps
<primary-label ref="2024.1"/>

<link-summary>Explanation of coroutine dumps format.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

The <ui-path>Help | Diagnostic Tools | Dump Threads</ui-path> action creates a thread dump, which is useful when investigating freezes or deadlocks.
Thread dumps include all application threads and coroutines existing at the moment of dump creation.

## Coroutine Dump Format

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

### `[xN of]`

A particular subtree is repeated N times (included only if N > 1).

### `name`

A coroutine name.

Notable names:
- `ApplicationImpl@xxxxxxxx container` - application coroutine.
- `ProjectImpl@xxxxxxxx container` - project coroutine.
- `com.intellij.*.AClass` - a coroutine bound to some specific class instance, e.g., an extension or a service.
  Unnamed coroutines are hard to identify, so it is recommended to add `CoroutineName(someName)` into a coroutine context.
- `(a x b)` - an intersection of coroutines `a` and `b`, e.g., `(ApplicationImpl@56422718 x com.example.myplugin)` is an intersection of the application and a plugin scope.
  See also [](coroutine_scopes.md#intersection-scopes).

### `CoroutineClass{JobState}`

A coroutine's `toString()`:
- `CoroutineClass` - a coroutine class. Notable values:
    - `StandaloneCoroutine` and `LazyStandaloneCoroutine` are created by [`launch`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html).
    - `DeferredCoroutine` and `LazyDeferredCoroutine` are created by [`async`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html).
    - `BlockingCoroutine` is created by [`runBlockingCancellable()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt).
    - `ProducerCoroutine` is created by [`produce`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/produce.html).
    - `ChildScope` is created by [`childScope`](%gh-ic%/platform/util/coroutines/src/coroutineScope.kt).
- `JobState` - a coroutine `Job`'s state.
  Possible states and transition can be found in the [`Job`'s KDoc](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/).

### `state: STATE`

A coroutine's state.

Possible states:
- `CREATED` - a coroutine was created but not yet started.
- `SUSPENDED` - a coroutine was executed up until the last frame in the stacktrace.
  This is where it was last seen running.
- `RUNNING` - a coroutine is currently executed by a thread.
  Its stacktrace reflects what the coroutine is doing right now (probably blocked waiting for something, otherwise a `RUNNING` coroutine is rarely seen unless it’s doing CPU-intensive work).

### `[context]`

A coroutine context.
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

<include from="snippets.topic" element-id="missingContent"/>
