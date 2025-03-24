<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Kotlin Coroutines
<primary-label ref="2024.1"/>

<link-summary>Introduction to Kotlin Coroutines in the IntelliJ Platform.</link-summary>

The IntelliJ Platform is a multithreading environment that executes many asynchronous and non-blocking tasks to avoid UI freezes.
These tasks are usually executed in background threads, which is a standard approach in the JVM world.

Since version 1.1, [Kotlin](using_kotlin.md) has introduced coroutines as a lightweight and cleaner abstraction over threads, allowing them to be utilized more efficiently.
The IntelliJ Platform started adapting coroutines in its APIs and internal code, and since 2024.1 it is recommended to use the coroutines approach over threads.

> Plugins _must_ use the bundled Kotlin Coroutines library, see [](using_kotlin.md#coroutinesLibraries).
>
{style="warning"}

### Coroutines Advantages

The reason for coroutines being lightweight is the fact that they aren't bound to OS native threads, as opposed to the JVM threads.
It enables much less memory consumption and more efficient context switching, which makes the platform and plugins more performant.
For example, it is straightforward to run 100.000 coroutines on a standard computer, which is not possible with threads as it would cause `OutOfMemoryError`.

Besides performance, there are more advantages of using coroutines:
- Coroutines greatly simplify the way of writing non-blocking code.
  What was usually implemented with hard to understand, implement, and maintain callbacks, with coroutines looks like regular sequential/imperative code.
- Coroutines allow for implementing structured concurrency (coroutines can spawn child coroutines), which allows for easily managing the lifecycle of concurrent tasks and error handling.
  For example, cancelling a parent coroutine automatically cancels all child coroutines.
- It is trivial to switch execution of the code parts between [UI and background threads](threading_model.md).

### Java Interoperability

Coroutines provide very limited Java interoperability, and coroutine-based APIs can’t be used to the full extent from Java code.

Kotlin Coroutines are relatively new to the IntelliJ Platform and aren't yet widely adopted in public APIs.
In the future, the number of coroutine-based APIs will grow, and using only Java may not be enough to implement a fully functional plugin.
It will be required to use [Kotlin](using_kotlin.md), at least partially, for example, to implement coroutine-based [extension points](plugin_extension_points.md).

## Learning Resources

Before going to the next coroutine-related sections, it is highly recommended to go through the following resources.
It will help understand coroutines and become fluent with available APIs:

- [Official Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- KotlinConf talks by Roman Elizarov (Kotlin Coroutines architect):
  - [Introduction to Coroutines](https://www.youtube.com/watch?v=_hfBv0a09Jc)
  - [Deep Dive into Coroutines on JVM](https://www.youtube.com/watch?v=YrrUCSi72E8)
  - [Coroutines in Practice](https://www.youtube.com/watch?v=a3agLJQ6vt8)
  - [Asynchronous Data Streams with Flow](https://www.youtube.com/watch?v=tYcqn48SMT8)

<include from="snippets.topic" element-id="missingContent"/>
