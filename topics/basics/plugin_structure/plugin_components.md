[//]: # (title: Components)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> When writing new plugins, creating Components should be avoided.
> Any existing Components should be migrated to services, extensions, or listeners (see below).
>
{type="warning"}

Plugin Components are a legacy feature supported for compatibility with plugins created for older versions of the IntelliJ Platform.
Plugins using Components do not support [dynamic loading](dynamic_plugins.md) (the ability to install, update, and uninstall plugins without restarting the IDE).

Plugin Components are defined in the `<application-components>`, `<project-components>`, and `<module-components>` sections in a [Plugin Configuration File](plugin_configuration_file.md).

## Migration

To migrate existing code from Components to more modern APIs, please see the following guidelines.

### Manage State

To manage some state or logic that is only needed when the user performs a specific operation, use a [Service](plugin_services.md).

### Persisting State

To store the state of your plugin at the application or project level, use a [Service](plugin_services.md), and implement the `PersistentStateComponent` interface.
See [Persisting State of Components](persisting_state_of_components.md) for details.

### Subscribing to Events

To subscribe to events, use a [listener](plugin_listeners.md) or create an [extension](plugin_extensions.md) for a dedicated extension point (for example, `com.intellij.editorFactoryListener`) if one exists for the event to subscribe to.

### Application Startup

> Executing code on application startup should be avoided whenever possible because it slows down startup.
>
{type="warning"}

Plugin code should only be executed when projects are opened (see [Project Open](#project-open)) or when the user invokes an action of a plugin.
If this cannot be avoided, add a [listener](plugin_listeners.md) subscribing to the [`AppLifecycleListener`](upsource:///platform/platform-impl/src/com/intellij/ide/AppLifecycleListener.java) topic.
See also [Running Tasks Once](ide_infrastructure.md).

To execute an activity in background on IDE startup (e.g., to warm up caches), use [`PreloadingActivity`](upsource:///platform/ide-core/src/com/intellij/openapi/application/PreloadingActivity.java).

### Project Open

To execute code when a project is being opened, use one of these two [extensions](plugin_extensions.md):

#### `com.intellij.postStartupActivity`
[`StartupActivity`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) for immediate execution on EDT.
Implement `DumbAware` to indicate activity can run in background thread (in parallel with other such tasks).

#### `com.intellij.backgroundPostStartupActivity`
[`StartupActivity.Background`](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) for execution with 5 seconds delay in background thread (2019.3 or later).

Any long-running or CPU intensive tasks should be made visible to users by using `ProgressManager.run(Task.Backgroundable)`.
Access to indices must be wrapped with `DumbService`, see also [General Threading Rules](general_threading_rules.md).

See also [](ide_infrastructure.md#running-tasks-once).

### Project and Application Close

To execute code on project closing or application shutdown, implement the `Disposable` interface in a [Service](plugin_services.md) and place the code in the `dispose()` method.
Alternatively, use `Disposer.register()` passing a `Project` or `Application` service instance as the `parent` argument (see [Choosing a Disposable Parent](disposers.md#choosing-a-disposable-parent)).
