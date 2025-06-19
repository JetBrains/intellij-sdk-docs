<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Components (Deprecated)
<primary-label ref="Deprecated"/>

<link-summary>Migrating deprecated plugin components to the current solutions.</link-summary>

> When writing new plugins, creating Components must be avoided.
> Any existing Components should be migrated to services, extensions, or listeners (see below).
>
{style="warning" title="Deprecation Notice"}

Plugin Components are a legacy feature supported for compatibility with plugins created for older versions of the IntelliJ Platform.
Plugins using Components don't support [dynamic loading](dynamic_plugins.md) (the ability to install, update, and uninstall plugins without restarting the IDE).

Plugin Components are defined in the `<application-components>`, `<project-components>`, and `<module-components>` sections in a [Plugin Configuration File](plugin_configuration_file.md).

## Migration

To migrate existing code from Components to modern APIs, see the following guidelines.

### Manage State

To manage some state or logic that is only necessary when the user performs a specific operation, use a [Service](plugin_services.md).

### Persisting State

To store the state of your plugin at the application or project level, use a [Service](plugin_services.md) and implement the `PersistentStateComponent` interface.
See [Persisting State of Components](persisting_state_of_components.md) for details.

### Subscribing to Events

To subscribe to events, use a [listener](plugin_listeners.md) or create an [extension](plugin_extensions.md) for a dedicated extension point
(for example, <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.editorFactoryListener"/></include>) if one exists for the event to subscribe to.

### Application Startup

> Executing code on application startup should be avoided whenever possible because it slows down startup.
>
{style="warning"}

Plugin code should only be executed when projects are opened (see [Project Open](#project-open)) or when the user invokes an action of a plugin.
If this can't be avoided, add a [listener](plugin_listeners.md) subscribing to the [`AppLifecycleListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/AppLifecycleListener.java) topic.
See also [Running Tasks Once](ide_infrastructure.md#running-tasks-once).

### Project Open

<tabs>

<tab title="2023.1 and later">

Using [Kotlin coroutines](kotlin_coroutines.md), implement [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.postStartupActivity"/></include>.
Examples:
- [`PowerSaveModeNotifier`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/PowerSaveModeNotifier.kt)
- [`TipOfTheDayStartupActivity`](%gh-ic%/platform/tips-of-the-day/src/com/intellij/ide/TipOfTheDayStartupActivity.kt)

Implementation in [Kotlin](using_kotlin.md) is required because Java doesn't support suspending functions.

</tab>

<tab title="Pre-2023.1">

To execute code when a project is being opened, use one of these two [extensions](plugin_extensions.md):

`com.intellij.postStartupActivity`
: [`StartupActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) for immediate execution on [EDT](threading_model.md).
Implement `DumbAware` to indicate activity can run in a background thread (in parallel with other such tasks).

`com.intellij.backgroundPostStartupActivity`
: [`StartupActivity.Background`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) for execution with a 5-second delay in a background thread.

Any long-running or CPU-intensive tasks should be made visible to users by using `ProgressManager.run(Task.Backgroundable)` (see [](background_processes.md)).
Access to indexes must be wrapped with [`DumbService`](indexing_and_psi_stubs.md#dumb-mode), see also [](threading_model.md).

See also [](ide_infrastructure.md#running-tasks-once).

</tab>

</tabs>

### Project and Application Close

To execute code on project closing or application shutdown, implement the [`Disposable`](%gh-ic%/platform/util/src/com/intellij/openapi/Disposable.java) interface in a [Service](plugin_services.md) and place the code in the `dispose()` method.
Alternatively, use `Disposer.register()` passing a `Project` or `Application` service instance as the `parent` argument (see [Choosing a Disposable Parent](disposers.md#choosing-a-disposable-parent)).
