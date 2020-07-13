---
title: Plugin Components
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** When writing new plugins, creating components should be avoided. Any existing components should be migrated to services, extensions, or listeners (see below).

Plugin components are a legacy feature supported for compatibility with plugins created for older versions of the
IntelliJ Platform. Plugins using components do not support [dynamic loading](dynamic_plugins.md) (the ability to install, update, and
uninstall plugins without restarting the IDE). 

Plugin components are defined using `<application-components>`, `<project-components>`, and `<module-components>`
tags in a `plugin.xml` file. 
                            
## Migration
To migrate existing code from components to more modern APIs, please use the following guidelines:

  * To manage some state or logic that is only needed when the user performs a specific operation,
    use a [Service](plugin_services.md).
  * To store the state of your plugin at the application or project level, use a [Service](plugin_services.md),
    and implement the `PersistentStateComponent` interface. See [Persisting State of Components](/basics/persisting_state_of_components.md) for details.
  * To subscribe to events, use a [listener](plugin_listeners.md) or create an [extension](plugin_extensions.md) for a dedicated extension point (for example, `com.intellij.editorFactoryListener`) if one exists for the event to subscribe to.
  * Executing code on application startup should be avoided whenever possible because it slows down startup.
    Plugin code should only be executed when projects are opened or when the user invokes an action of a plugin. If this cannot be avoided, add a [listener](plugin_listeners.md) subscribing to the [AppLifecycleListener](upsource:///platform/platform-impl/src/com/intellij/ide/AppLifecycleListener.java) topic.
  * To execute code when a project is being opened, provide [StartupActivity](upsource:///platform/core-api/src/com/intellij/openapi/startup/StartupActivity.java) implementation and register an [extension](plugin_extensions.md) for the `com.intellij.postStartupActivity` or `com.intellij.backgroundPostStartupActivity` extension point (the latter is supported starting with version 2019.3 of the platform).
  * To execute code on project closing or application shutdown, implement the `Disposable` interface in a [Service](plugin_services.md)
    and place the code in the `dispose()` method. Alternatively, use `Disposer.register()` passing a `Project` or `Application` service instance
    as the `parent` argument (see [Choosing a Disposable Parent](/basics/disposers.md#choosing-a-disposable-parent)).
