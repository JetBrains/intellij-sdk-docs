<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Launching Coroutines
<primary-label ref="2024.1"/>

<link-summary>Techniques for launching coroutines from various contexts.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

There are two approaches to launching coroutines in the IntelliJ Platform:
1. [Service with its own scope](#launching-coroutine-from-service-scope). (recommended)
2. [The `runBlockingCancellable` function](#using-runblockingcancellable).

## Launching Coroutine From Service Scope

The recommended approach is creating a [service](plugin_services.md) that receives [its scope](coroutine_scopes.md#service-scopes) via the constructor injection and launching a coroutine from the service methods.
Please note that while creating a service instance does allocate additional resources, using a dedicated service and scope remains a lightweight and, most importantly, safe solution for launching coroutines.
It should be used whenever possible.

The pattern is as follows:

<tabs>
<tab title="Application Service">

```kotlin
@Service
class MyApplicationService(
  private val cs: CoroutineScope
) {
  fun scheduleSomething() {
    cs.launch {
      // do something
    }
  }
}
```

</tab>
<tab title="Project Service">

```kotlin
@Service(Service.Level.PROJECT)
class MyProjectService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun scheduleSomething() {
    cs.launch {
      // do something
    }
  }
}
```

</tab>
</tabs>

The injected scope is created per service, so each instance has its own isolated scope with a common parent, which is an [intersection scope](coroutine_scopes.md#intersection-scopes).
The injected scope is canceled when the container (application/project) is shut down or when the plugin is unloaded.

## Using `runBlockingCancellable`

> Using `runBlockingCancellable` is not recommended.
> Use [service scopes](#launching-coroutine-from-service-scope) whenever possible.
>
{style="warning"}

In a standard coroutine-based application, the bridge between the regular blocking code and the suspending code is the [`runBlocking`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html) function.

In the IntelliJ Platform, a similar purpose is achieved by the [`runBlockingCancellable`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) function.
In addition to the same semantics as `runBlocking`, the action gets canceled when the current progress indicator or the current job is canceled.

<include from="snippets.topic" element-id="missingContent"/>
