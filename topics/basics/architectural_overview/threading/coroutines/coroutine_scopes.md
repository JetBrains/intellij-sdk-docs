<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutine Scopes
<primary-label ref="2024.1"/>

<link-summary>Explanation of coroutine scopes in the IntelliJ Platform.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

Kotlin's coroutines follow the principle of structured concurrency.
It means that each coroutine is run in a specific [CoroutineScope](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/), which delimits the lifetime of the coroutine.
This ensures that they are not lost and do not leak.
An outer scope does not complete until all its child coroutines are completed.
Cancellation of the outer scope also cancels its child coroutines.
Structured concurrency ensures that any errors in the code are properly reported and never lost.

## IntelliJ Platform Scopes

IntelliJ Platform provides special coroutine scopes that help ensure proper structured concurrency of coroutines run from the platform or plugin code.
After cancellation, the platform awaits the completion of each scope.
Using correct parent scopes guarantees that child coroutines will be properly canceled when no longer needed, preventing resource leaks.

The following diagram presents the scopes and their parent-child relationships:

![IntelliJ Platform Coroutine Scopes](intellij_platform_coroutine_scopes.svg)

All scopes presented on the diagram are [supervisor scopes](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/supervisor-scope.html) — they ignore the failures of their children.

Each coroutine scope can have only one actual parent, pointed with solid arrow lines.
Dashed arrow lines point to fictional parents, which follow the actual coroutine parent-child semantics:
- a parent scope cancels children on its own cancellation
- a parent scope awaits children before considering itself complete
- a failed child cancels its parent (which effectively is not happening because presented scopes are supervisors)

The **Application×Plugin** and **Project×Plugin** are [intersection scopes](#intersection-scopes) with two semantic parents (actual and fictional).

### Main Scopes

- **Root** - the root scope spans all the coroutines.
  This is the standard root scope launched with [`runBlocking`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html) coroutine builder.

- **Application** - a scope associated with the [`Application`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) container (component manager) lifetime.
  It is canceled on application shutdown.
  This triggers cancellation of the **Application×Plugin** scope and, subsequently, its children, including the **Project×Plugin** scope.

- **Project** - a scope associated with a [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java) container (component manager) lifetime.
  It is canceled when a project is being closed.
  This triggers the cancellation of the **Project×Plugin** scope and, subsequently, its children.

- **Plugin** - a scope associated with a plugin lifetime.
  It is canceled on unloading of the corresponding plugin.
  This triggers cancellation of the **Application×Plugin** scope and, subsequently, its children, including the **Project×Plugin** scope.

### Intersection Scopes

- **Application×Plugin** - a scope which is an intersection of the **Application** and **Plugin** scopes.
  It is canceled when the application is shutdown or the corresponding plugin is unloaded.
  This triggers the cancellation of its children and the **Project×Plugin** scope and, subsequently, its children.
- **Project×Plugin** - a scope which is an intersection of the **Project** and **Plugin** scopes.
  It is canceled when a project is being closed or the corresponding plugin is unloaded.

Intersection scopes enable creating coroutines whose lifetime is limited by application/project and plugin lifetimes, e.g.,
application/project [services](plugin_services.md) provided by a plugin.

### Service Scopes

<snippet id="serviceScopes">

The **Application Service** and **Project Service** scopes are bound to an application and project [service](plugin_services.md#types) lifetimes accordingly.
They are children of the [](coroutine_scopes.md#intersection-scopes), which means that they are canceled when the application/project is closed or a plugin is unloaded.

The service scope is provided to services via constructor injection.
The following constructor signatures are supported:

- `MyService(CoroutineScope)` for application and project services
- `MyProjectService(Project, CoroutineScope)` for project services

Each service instance receives its own scope instance.
The injected scopes' contexts contain [`Dispatchers.Default`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html) and [`CoroutineName(serviceClass)`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-name/).

See [](launching_coroutines.md#launching-coroutine-from-service-scope) for full samples.

</snippet>

## Using a Correct Scope

### Use Service Scopes

If a plugin requires running some code in a coroutine, the recommended approach is to create a separate [service](plugin_services.md) that will receive its [own scope](#service-scopes) via constructor and launch the coroutine in this scope.
This approach guarantees the usage of the correct scope, preventing leaks and canceling wrong scopes and killing all their (e.g., application's or project's) coroutines accidentally.

See the [](launching_coroutines.md) section for details.

> The following sections describe the potential problems that would occur if the wrong coroutine scopes were used.
> This allows better understanding of the platform scopes and why the [service approach](#use-service-scopes) mentioned above must be used.
>
{style="warning"}

### Do Not Use Application/Project Scope

Application and Project scopes are exposed with `Application.getCoroutineScope()` and `Project.getCoroutineScope()`.
Never use these methods, as they are deprecated and will be removed in the future.

Using these scopes could easily lead to project or plugin class leaks.

1. Project leak:
    ```kotlin
    application.coroutineScope.launch {
      project.getService(PsiDirectoryFactory::class.java)
    }
    ```
    Closing the project cancels its scope.
    The application scope remains active, and the project is leaked.

    ```mermaid
    gantt
        dateFormat X
        %% do not remove trailing space in axisFormat
        axisFormat ‎
        section Lifetimes
            Application Scope : 0, 10
            Project Scope     : done, 2, 8
            Project leak      : crit, 4, 10
    ```

2. Plugin leak:
    ```kotlin
    project.coroutineScope.launch {
      project.getService(MyPluginService::class.java)
    }
    ```
    Unloading of the plugin cancels its scope.
    The project scope remains active, and the plugin classes are leaked.

    ```mermaid
    gantt
        dateFormat X
        %% do not remove trailing space in axisFormat
        axisFormat ‎
        section Lifetimes
            Project Scope        : 0, 10
            Plugin Scope         : done, 2, 8
            MyPluginService leak : crit, 4, 10
    ```

### Do Not Use Intersection Scopes

There is no API for retrieving **Application×Plugin** and **Project×Plugin** [intersection scopes](#intersection-scopes),
but let's assume there is a method exposing the **Project×Plugin** scope:

```kotlin
/**
 * Returns the correct intersection scope for the project and plugin
 * by a given plugin class.
 */
fun Project.getCoroutineScope(pluginClass: Class<*>): CoroutineScope
```

Using this scope could lead to a plugin leak:

```kotlin
project.getCoroutineScope(PluginBService::class.java).launch {
  project.getService(PluginAService::class.java)
}
```

Unloading of Plugin A cancels its scope.
The **Project × Plugin B** scope remains active, and the Plugin A classes are leaked.

```mermaid
gantt
    dateFormat X
    %% do not remove trailing space in axisFormat
    axisFormat ‎
    section Lifetimes
        Application Scope                               : done, 0, 10
        Project Scope                                   : done, 2, 8
        Plugin A Scope                                  : active, done, 1, 6
        Project × Plugin A Scope                        : active, done, 2, 6
        Plugin B Scope                                  : 4, 9
        Project × Plugin B Scope                        : 4, 8
        PluginAService leak                             : crit, 5, 8
        Correct PluginAService lifetime should end here : active, milestone, 6, 6
```

<include from="snippets.topic" element-id="missingContent"/>
