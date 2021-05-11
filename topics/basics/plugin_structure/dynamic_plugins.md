[//]: # (title: Dynamic Plugins)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Starting with the **2020.1** release, installing, updating, and uninstalling plugins without restarting the IDE is available in the IntelliJ Platform.

During plugin development, [Auto-Reload](ide_development_instance.md#enabling-auto-reload) also allows code changes to take effect immediately in the sandbox IDE instance.
To test whether dynamic installation works correctly, verify installing [local build distribution](deployment.md#building-distribution) succeeds (see [Troubleshooting](#troubleshooting)). 
                                
Please note that any unloading problems in a production environment will simply ask the user to restart the IDE.

 > If a plugin _requires_ restart (e.g., due to using native libraries) specify `require-restart="true"` for `<idea-plugin>` root tag in <path>plugin.xml</path>.
 >
 {type="note"}

## Restrictions

For a plugin to support this, all restrictions listed below must be met.
To verify a plugin locally, invoke <menupath>Analyze | Run Inspection by Name...</menupath> and run <control>Plugin DevKit | Plugin descriptor | Plugin.xml dynamic plugin verification inspection</control> inspection on all plugin descriptor files.

For plugins hosted on the [JetBrains Plugins Repository](https://plugins.jetbrains.com) the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/) will run these checks automatically.
See [Plugin Verifier](api_changes_list.md#plugin-verifier) for more information on how to run it locally or on CI.

### No Use of Components

No Components must be used; existing ones [must be migrated](plugin_components.md) to services, extensions, or listeners.

### Action Group Requires ID

All `<group>`s must declare a unique `id`.

### Use Only Dynamic Extensions

Whether defined in the platform itself ([Extension Point List](extension_point_list.md)) or coming from other plugins, all used extension points must be marked explicitly as dynamic (see next paragraph).

Some deprecated extension points (e.g., `com.intellij.configurationProducer`) are intentionally non-dynamic, and their usage should be migrated to the corresponding replacement.

### Mark Extension Points as Dynamic

If a plugin defines its own custom extension points, they must adhere to specific usage rules and then [be declared](plugin_extension_points.md#dynamic-extension-points) ready for dynamic use explicitly.

### Configurables Depending on Extension Points

Any `Configurable` which depends on dynamic extension points must implement `Configurable.WithEpDependencies`.

### No Use of Service Overrides

Application, project, and module [services](plugin_services.md) declared with `overrides="true"` are not allowed.

## Code

 >  Loading and unloading plugins happens in EDT and under write action.
 >
 {type="note"}

### CachedValue

Loading/Unloading a plugin clears all cached values created using `CachedValuesManager`.

### Do not Store PSI

Do not store references to PSI elements in objects which can survive plugin loading or unloading; use `SmartPsiElementPointer` instead.

### Do not Use FileType/Language as Map Key

Replace with `String` from `Language.getID()`/`FileType.getName()` (use inspection <control>Plugin DevKit | Code | Map key may leak</control>).

### Plugin Load/Unload Events
Register [`com.intellij.ide.plugins.DynamicPluginListener`](upsource:///platform/core-impl/src/com/intellij/ide/plugins/DynamicPluginListener.kt) [application listener](plugin_listeners.md) to receive updates on plugin load/unload events.

This can be used to e.g., cancel long-running activities or disallow unload due to ongoing processes.

## Troubleshooting

When a plugin is being uninstalled or updated, the IDE waits synchronously for plugin unload and asks for restart if the unload failed.

Use the latest available version of the target IDE for verification. See also this [list of known platform issues](https://youtrack.jetbrains.com/issues/IDEA?q=%23dynamic-plugins%20) related to handling dynamic plugins. 

### Logging

All events are tracked under `com.intellij.ide.plugins.DynamicPlugins` category in IDE log file.

### Diagnosing Leaks

To find leaks preventing clean unload, perform the following steps:

- Set registry key `ide.plugins.snapshot.on.unload.fail` to `true`
- Verify that IDE runs with VM parameter `-XX:+UnlockDiagnosticVMOptions`
- Open the <path>.hprof</path> snapshot generated on plugin unload, look for the plugin ID string
- Find the `PluginClassLoader` referencing the plugin ID string
- Look at references to the `PluginClassLoader` instance.
  Every one of them is a memory leak (or part of a memory leak) that needs to be resolved.