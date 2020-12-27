---
title: Dynamic Plugins
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Starting with the 2020.1 release, installing, updating, and uninstalling plugins without restarting the IDE is available in the IntelliJ Platform.
During plugin development, [Auto-Reload](/basics/ide_development_instance.md#enabling-auto-reload) also allows code changes to take effect immediately in the sandbox IDE instance.

> **NOTE** If a plugin _requires_ restart (e.g., due to using native libraries) specify `require-restart="true"` for `<idea-plugin>` root tag in `plugin.xml`.

## Restrictions

For a plugin to support this, all restrictions listed below must be met.
To verify a plugin locally, run **Analyze \| Run Inspection by Name... \| Plugin.xml dynamic plugin verification** inspection on all plugin descriptor files.

For plugins hosted on the [JetBrains Plugins Repository](https://plugins.jetbrains.com) the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/) will run these checks automatically.
See [Plugin Verifier](/reference_guide/api_changes_list.md#plugin-verifier) for more information on how to run it locally or on CI.

### No Use of Components

No Components must be used; existing ones [must be migrated](plugin_components.md) to services, extensions, or listeners.

### Action Group Requires ID

All `<group>`s must declare a unique `id`.

### Use Only Dynamic Extensions

Whether defined in the platform itself or coming from other plugins, all extensions must be marked as dynamic (see next paragraph).

Some deprecated extension points (e.g., `com.intellij.configurationProducer`) are intentionally non-dynamic, and their usage should be migrated to the corresponding replacement.

### Mark Extension Points as Dynamic

The plugin's extension points must adhere to specific usage rules and then [be declared](plugin_extension_points.md#dynamic-extension-points) ready for dynamic use explicitly.

### Configurables Depending on Extension Points

Any `Configurable` which depends on dynamic extension points must implement `Configurable.WithEpDependencies`.

### No Use of Service Overrides

Application, project, and module services declared with `overrides="true"` are not allowed.

## Code

> **NOTE** Loading and unloading plugins happens in EDT and under write action.

### CachedValue

Loading/Unloading a plugin clears all cached values created using `CachedValuesManager`.

### Do not Store PSI

Do not store references to PSI elements in objects which can survive plugin loading or unloading; use `SmartPsiElementPointer` instead.

### Do not Use FileType/Language as Map Key

Replace with `String` from `Language.getID()`/`FileType.getName()`.

### Plugin Load/Unload Events

Register [`com.intellij.ide.plugins.DynamicPluginListener`](upsource:///platform/platform-impl/src/com/intellij/ide/plugins/DynamicPlugins.kt) [application listener](plugin_listeners.md) to receive updates on plugin load/unload events.

This can be used to e.g., cancel long-running activities or disallow unload due to ongoing processes.

## Troubleshooting

When a plugin is being uninstalled or updated, the IDE waits synchronously for plugin unload and asks for restart if the unload failed.

### Logging

All events are tracked under `com.intellij.ide.plugins.DynamicPlugins` category in IDE log file. If a plugin fails to reload, the log will contain a cause as to why.

### Diagnosing Leaks

To find leaks preventing clean unload, perform the following steps:

1. Verify that the IDE is running with the VM parameter `-XX:+UnlockDiagnosticVMOptions`. If you are using the [Gradle](/tutorials/build_system/gradle_guide.md), the `runIde.jvmArgs += "-XX:+UnlockDiagnosticVMOptions"` otherwise you can [Configure JVM Options](https://www.jetbrains.com/help/idea/tuning-the-ide.html#procedure-jvm-options) to change them.
2. Set Registry key `ide.plugins.snapshot.on.unload.fail` to `true` (Go to `Navigate` -> `Search Everywhere` and type `Registry`).
3. Trigger the plugin reload.
3. Open the `.hprof` memory snapshot generated on plugin unload, look for the plugin ID string. [IntelliJ Ultimate](https://www.jetbrains.com/help/idea/analyze-hprof-memory-snapshots.html) can open memory snapshots directly. 
4. Find the `PluginClassLoader` referencing the plugin ID string
5. Look at references to the `PluginClassLoader` instance.
6. Every one of them is a memory leak (or part of a memory leak) that needs to be resolved.

When you've completed step 1 and 2, the log will contain more information about the memory leak, for instance the following shows a chain of field references that
is keeping the class loader in memory.

```
2020-12-26 14:43:24,563 [ 251086]   INFO - lij.ide.plugins.DynamicPlugins - Snapshot analysis result: Root 1:
  ROOT: Global JNI
  sun.awt.X11.XInputMethod.clientComponentWindow
  com.intellij.openapi.wm.impl.IdeFrameImpl.rootPane
  com.intellij.openapi.wm.impl.IdeRootPane.myToolbar
  com.intellij.openapi.actionSystem.impl.ActionToolbarImpl.myVisibleActions
  java.util.ArrayList.elementData
  java.lang.Object[]
  com.test.ActionExample.<class>
  com.test.ActionExample.<loader>
* com.intellij.ide.plugins.cl.PluginClassLoader
```


#### Other Tips

1. Try a newer version of IntelliJ, in some cases platform bugs might be an issue.
2. Try in a fresh and new configuration (e.g., clean the sandbox or use a different configuration directory).