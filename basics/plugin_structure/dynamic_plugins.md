---
title: Dynamic Plugins
---

> **WARNING** Please note that the information on this page is preliminary and might change.

Starting with 2020.1 release, the ability to install, update and uninstall plugins without restarting the IDE is available in the IntelliJ Platform.

For a plugin to support this, all restrictions listed below must be met. To verify a plugin locally, run _Plugin DevKit | Plugin descriptor | Plugin.xml dynamic plugin verification_ 
inspection on all plugin descriptor files (required `plugin.xml` as well as any additional files).

For plugins hosted on the [JetBrains plugin repository](/plugin_repository/index.md) the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/)
will run these checks automatically.

### No use of Components
No Components must be used; existing ones [must be migrated](plugin_components.md) to services, extensions or listeners.

### Action Group requires ID
All `<group>`s must declare a unique `id`.

### Use only dynamic Extensions
All extensions, whether defined in the platform itself or coming from other plugins, must be marked as dynamic (see next paragraph). 

### Mark Extension Points as dynamic
All extension points provided by the plugin must adhere to specific usage rules and then [be declared](plugin_extension_points.md#dynamic-extension-points) ready for dynamic use explicitly.

### Configurables depending on Extension Points
Any `Configurable` which depends on dynamic extension points must implement `Configurable.WithEpDependencies`.

## Plugin Load/Unload Events
Register `com.intellij.ide.plugins.DynamicPluginListener` [listener](plugin_listeners.md) to receive updates on plugin load/unload events.