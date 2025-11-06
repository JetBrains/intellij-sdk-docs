<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Modular Plugins (Experimental)
<primary-label ref="2025.3"/>

<link-summary>Splitting plugin by modules to support remote development and other cases</link-summary>

A regular plugin has a single [class loader](plugin_class_loaders.md) which is used to load all classes of the plugin.
This approach works well for many plugins, but there are cases when more granularity is needed:
* To properly work in remote development mode, different parts of the plugin should be loaded in the backend and the frontend processes; these parts have different dependencies.
* If some classes of a plugin `A` depend on classes from a plugin `B`, they should be loaded by a separate class loader to allow unloading the plugin `B` without restarting the IDE.

In such cases, it's possible to use the new modular plugin format: Plugin Model Version 2.

> The new format is still experimental and may change in the future.
> It's not recommended to use it for cases other than writing plugins for remote development where the new format is required.
>
> Modular plugins can be developed only with [](tools_intellij_platform_gradle_plugin.md).
>
{style="note"}

## Plugin Modules

A plugin consists of one or more modules registered in the `content` tag in the [plugin descriptor file](plugin_configuration_file.md):

```xml
<content>
  <module name="example.core" loading="required" />
  <module name="example.optional" />
  <module name="example.backend"
          required-if-available="intellij.platform.backend" />
</content>
```

The name of the module is specified by a required `name` attribute and must be unique within the plugin.

When the IntelliJ Platform loads a plugin, it tries to load all its modules.
A module can be loaded if and only if all its dependencies are available.
If some module cannot be loaded, the behavior depends on the value of an optional `loading` attribute:
* `required`: module is the required part of the plugin; if the module cannot be loaded, the whole plugin isn't loaded, and an error is shown to the user.
* `optional`: module is an optional part of the plugin; if the module cannot be loaded, it is skipped and doesn't prevent other modules from the plugin from being loaded.
* other options are currently for internal use only.

If the `loading` attribute is not specified, the module is treated as optional by default.

If the module is optional, it's possible to specify that it should be treated as required when the IDE is running in a specific mode via the `required-if-available` attribute:
* `intellij.platform.backend`: the module is required if the current process is a regular IDE process, or it's a remote development backend process;
* `intellij.platform.frontend`: the module is required if the current process is a regular IDE process, or it's a remote development frontend process (JetBrains Client).

Source code and resource files of modules must be located in separate Gradle projects registered in the main module as [submodules](tools_intellij_platform_gradle_plugin_plugins.md#module).

## Module Descriptor File

A module must have a descriptor file with the name equal to the module name and <path>xml</path> extension (for example, <path>example.my.module.xml</path>, located directly in the <path>main/resources</path> directory of the corresponding Gradle project (**not in** <path>META-INF</path> directory!).

The module descriptor file uses the same format as the [plugin configuration file](plugin_configuration_file.md), but only the following top-level tags are allowed:
* `<resource-bundle>`
* `<extensions>`
* `<extensionPoints>`
* `<actions>`
* `<applicationListeners>`
* `<projectListeners>`

The `<depends>` tag is also not allowed.
A special `<dependencies>` tag can be used to specify dependencies on other modules and plugins:

```xml
<dependencies>
  <module name="example.core" />
  <plugin id="com.example.plugin" />
</dependencies>
```

`<module>` subtag specifies dependency on another module with the given name.
`<plugin>` subtag specifies dependency on a regular (classic) plugin with the given ID.
Dependencies are used at runtime to determine whether a module can be loaded or not, and to configure the class loader of the module.

Like with `<depends>` tags in regular plugins, it's necessary to specify [dependencies in the Gradle build script](plugin_dependencies.md#intellij-platform-gradle-plugin-2x) as well to have them in the compilation classpath.

If no dependencies are specified, the module will always be loaded when the plugin is loaded, regardless of the current IDE and the mode it runs in.

### Class Loaders

Each module has its own class loader.
The class loader has class loaders of modules and plugins specified in `<dependencies>` tag of the module descriptor as its parents, so it delegates loading of classes to them if they aren't found in the module itself.
Also, the core class loader of the IntelliJ Platform is automatically added as a parent class loader.

If a regular (classic) plugin declares a dependency on a modular plugin using `<depends>` tag, class loaders of all plugin modules will be added as parent class loaders.

## Plugin Configuration File

The plugin configuration file for modular plugins uses the same format as a [plugin configuration file for regular (classic) plugins](plugin_configuration_file.md).
However, the following top-level tags related to registration of classes aren't allowed in it; they must be located in the module descriptor files where the referenced classes are defined:
* `<extensions>`
* `<extensionPoints>`
* `<actions>`
* `<applicationListeners>`
* `<projectListeners>`

Also, `<depends>` tags are not allowed.
Dependencies must be specified in the module descriptor files using `<dependencies>` tag.
The IDE will automatically treat dependencies of modules marked as 'required' as necessary dependencies of the plugin and will suggest installing or enabling corresponding plugins when the plugin is being installed.
