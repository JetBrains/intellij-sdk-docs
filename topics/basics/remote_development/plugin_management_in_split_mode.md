<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Management in Split Mode

<primary-label ref="2026.1"/>

<link-summary>Understand compatibility checks, plugin synchronization, and installation paths for plugins in split mode.</link-summary>

A plugin can be installed on the backend, on the frontend, or on both sides.
This article explains how the platform determines that and how plugin synchronization works.

A separate question is which *modules* of an installed plugin are actually loaded in each process.
For split plugins, module loadability is controlled by content-module dependencies such as `intellij.platform.backend` and `intellij.platform.frontend`.
See [Modular Plugins](modular_plugins.md) and [Split Mode](split_mode_and_remote_development.md) for details.

## Compatibility Check

A plugin may be:
- compatible only with the backend
- compatible only with the frontend
- compatible with both sides

Compatibility is determined by the dependencies declared by the plugin and its content modules.
By default, content modules are optional, which means that if a particular module's dependencies are not satisfied, that module is not loaded, but this does not affect the loading of other plugin modules.
As a result, if the plugin dependencies are satisfied, the plugin is loaded.
If a plugin consists of multiple modules and the dependencies of at least one module are satisfied, the plugin is loaded.
For more details about modules and dependencies, see [Modular Plugins](modular_plugins.md).

When a plugin is installed from JetBrains Marketplace, the plugin manager checks its compatibility with both the frontend and the backend and installs it wherever its dependencies are satisfied.

## Plugin Sync Flow

When a project is opened, the frontend and backend plugin sets are compared.
If a difference is detected, the IDE shows a plugin synchronization notification, allowing the user to choose which plugins to install on each side.

### Current Limitation

Plugin synchronization currently works only for plugins that are available on [JetBrains Marketplace](https://plugins.jetbrains.com).

If a plugin is installed only from a local ZIP or JAR file, the synchronization flow cannot resolve it for the other side through Marketplace metadata.
For a more convenient installation method than ad hoc local archives, consider publishing the plugin to JetBrains Marketplace or using a [custom plugin repository](custom_plugin_repository.md).

<include from="snippets.topic" element-id="missingContent"/>
