<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Roaming Settings

<web-summary>
Sharing the persistent state of application-level components between IDE installations via Export Settings and the Backup and Sync plugin.
</web-summary>

<link-summary>Sharing the persistent state of application-level components between IDE installations.</link-summary>

<tldr>

**Product Help:** [Share IDE settings](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html)

**Reference:** [](persisting_state_of_components.md)

</tldr>

The persistent state of components can be shared and synchronized between different IDE installations.
This allows users to have the same settings on every development machine.
Sharing settings in this way is called _roaming_.

Settings can be shared in the following ways:
- The _[Export Settings](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html#import-export-settings)_ feature, which allows users to export settings manually and import them into another IDE installation.
- The _[Backup and Sync](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html#IDE_settings_sync)_ plugin, which synchronizes settings via the user's [JetBrains Account](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html).
  The plugin must be installed and enabled in the IDE.
  Users can select the categories of settings that are synchronized.

> Before version 2022.3, settings could also be synchronized via the bundled [_Settings Repository_](https://plugins.jetbrains.com/plugin/7566-settings-repository-deprecated-) plugin,
> which stores them in a Git repository created and configured by the user.
> The plugin has been unbundled since then and is no longer maintained.
>
{style="warning"}

Decide carefully which persistent component state is shareable.
Share only settings that aren't specific to a given machine.
For example, paths to user-specific directories shouldn't be shared.
If a component contains both shareable and non-shareable data, split it into two separate persistent state components.

## Requirements for Roamable Components

Only the state of [application-level](plugin_services.md#types) services implementing [`PersistentStateComponent`](persisting_state_of_components.md) can roam.
Project-level state is stored in project files and is typically shared via version control.

A roamable persistent state component must be a service declared in the plugin configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationService"/></include> (see [](plugin_services.md#declaring-a-service)).

> [Light services](plugin_services.md#light-services) can't be reliably discovered for roaming.
> If an application-level light service is a `PersistentStateComponent`, roaming must be disabled with `roamingType = RoamingType.DISABLED` (see [](plugin_services.md#light-service-restrictions)).
>
{style="warning"}

The following example shows a roamable application-level persistent state component.
It is stored in a custom <path>myPlugin.xml</path> file with the default roaming type, provides a [presentable name](#presentable-name-in-export-settings), and defines a [settings category](#backup-and-sync-plugin).
Such a component supports both the [](#export-settings) feature and the [](#backup-and-sync-plugin):

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
@State(
  name = "MyPluginSettings",
  storages = [Storage("myPlugin.xml")],
  presentableName = MyPluginSettings.MyPluginSettingsName::class,
  category = SettingsCategory.TOOLS
)
class MyPluginSettings :
    SerializablePersistentStateComponent<MyPluginSettings.State>(State()) {

  var value: String
    get() = state.value
    set(value) {
      updateState {
        it.copy(value = value)
      }
    }

  data class State(
    @Property val value: String = "default value"
  )

  class MyPluginSettingsName :
      com.intellij.openapi.components.State.NameGetter() {
    override fun get(): String =
      MyBundle.message("settings.presentable.name")
  }
}
```

</tab>
<tab title="Java" group-key="java">

```java
@State(
    name = "MyPluginSettings",
    storages = @Storage("myPlugin.xml"),
    presentableName = MyPluginSettings.MyPluginSettingsName.class,
    category = SettingsCategory.TOOLS
)
final class MyPluginSettings
    implements PersistentStateComponent<MyPluginSettings.State> {

  static final class State {
    public String value = "default value";
  }

  private State myState = new State();

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(State state) {
    myState = state;
  }

  static final class MyPluginSettingsName
      extends com.intellij.openapi.components.State.NameGetter {
    @Override
    public String get() {
      return MyBundle.message("settings.presentable.name");
    }
  }
}
```

</tab>
</tabs>

The persistent state component is declared in the plugin configuration file:

```xml
<extensions defaultExtensionNs="com.intellij">
  <applicationService
      serviceImplementation="com.example.MyPluginSettings"/>
</extensions>
```

## Roaming Types

The `roamingType` attribute of the [`@Storage`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/Storage.java) annotation specifies whether and how the settings are shared.
The available values are defined in [`RoamingType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/components/RoamingType.java):

| `RoamingType` | Description                                                                    |
|---------------|--------------------------------------------------------------------------------|
| `DEFAULT`     | Settings are shared (default value).                                           |
| `PER_OS`      | Settings are shared per operating system.                                      |
| `LOCAL`       | Settings aren't shared but can be migrated to another IDE on the same machine. |
| `DISABLED`    | Settings aren't shared.                                                        |

If the component state is OS-dependent, for example, it contains keyboard shortcuts or paths specific to an operating system, set `roamingType` to `RoamingType.PER_OS`.

> If multiple components store their state in the same file, they must use the same `roamingType` value.
>
{style="warning"}

> The <path>other.xml</path> file, defined by the [`StoragePathMacros.NON_ROAMABLE_FILE`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/StoragePathMacros.java) constant, is a non-roamable storage.
> The state of a component stored in this file is never exported, backed up, or synchronized, regardless of the `roamingType` value.
>
{style="warning"}

## Export Settings

The [_Export Settings_](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html#import-export-settings) feature allows users to save the selected settings to an archive file and import them into another IDE installation.

By default, all application-level persistent components declared in the plugin configuration file are eligible for export if their storage:
- is an explicitly specified custom file.
  The following storages don't support roaming, and components stored in them are not exported:
  - [`StoragePathMacros.NON_ROAMABLE_FILE`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/StoragePathMacros.java) (<path>other.xml</path>)
  - [`StoragePathMacros.WORKSPACE_FILE`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/StoragePathMacros.java) (the workspace file)
  - [`StoragePathMacros.CACHE_FILE`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/StoragePathMacros.java) (the cache file)

  See [](persisting_state_of_components.md#defining-the-storage-location).
- has the `roamingType` set to `RoamingType.DEFAULT` (default value) or `RoamingType.PER_OS`.
  Omitting the `roamingType` attribute is equivalent to setting it to `RoamingType.DEFAULT`.

The following example shows a minimal annotation of an exportable component:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
@State(
  name = "MyPluginSettings",
  storages = [Storage("myPlugin.xml")]
)
```

</tab>
<tab title="Java" group-key="java">

```java
@State(
    name = "MyPluginSettings",
    storages = @Storage("myPlugin.xml")
)
```

</tab>
</tabs>

To make a component exportable regardless of its roaming type, set `exportable = true` in the [`@State`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/State.java) or `@Storage` annotation.

### Presentable Name in Export Settings

The _Export Settings_ dialog lists each exportable persistent component under a presentable name.
The IDE determines this name in the following order.

#### 1. Explicit `presentableName`

Set the `presentableName` attribute of `@State` to a class that extends `State.NameGetter`.
See the example in [](#requirements-for-roamable-components).

#### 2. Resource Bundle Key

If `presentableName` is not set, the IDE looks up the following key:

```properties
exportable.<name>.presentable.name=My Plugin Settings
```

`<name>` is the value of the `name` attribute of `@State`.
For the example above, the key is `exportable.MyPluginSettings.presentable.name`.

The IDE searches only the resource bundle that the plugin declares with the [`<resource-bundle>`](plugin_configuration_file.md#idea-plugin__resource-bundle) element in the plugin configuration file.

#### 3. Fallback

If no name is found in steps 1 and 2, the IDE shows the `name` attribute value without the `.Settings` suffix.
For example, `MyPlugin.Settings` is shown as `MyPlugin`.

> To provide a localized, user-friendly name, use step 1 or step 2.
> The `name` attribute is an internal identifier and is often unclear to users.
>
{style="note"}

## Backup and Sync Plugin
<primary-label ref="2022.3"/>

> This feature works only if the _Backup and Sync_ plugin is installed and enabled in the IDE.
> The plugin was called _Settings Sync_ before version 2024.3.
>
{style="note"}

The _Backup and Sync_ plugin synchronizes settings via the user's [JetBrains Account](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html).

To include a component state in the _Backup and Sync_ synchronization, the following requirements must be met:
- The `roamingType` attribute of the `@Storage` annotation is `DEFAULT` (also when the attribute is omitted) or `PER_OS`.
  Component states with `LOCAL` or `DISABLED` values are not synchronized.
- No other `PersistentStateComponent` stored in the same storage file has a different `roamingType` value.
- The `category` attribute of the `@State` annotation is set explicitly to a value other than `OTHER`.

Set the `category` to the [`SettingsCategory`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/SettingsCategory.java) constant that best describes the component state.
Each category corresponds to an option in the _Backup and Sync_ settings, where users can enable or disable synchronization of each category separately:

| Category        | `SettingsCategory` | Related Settings                                           |
|-----------------|--------------------|------------------------------------------------------------|
| UI settings     | `UI`               | Theme, editor font, toolbar, and notifications.            |
| Keymaps         | `KEYMAP`           | Keymaps and keyboard shortcuts.                            |
| Code settings   | `CODE`             | Code style, file types, inspections, and live templates.   |
| Plugins         | `PLUGINS`          | Installed plugins and plugin state.                        |
| Tools           | `TOOLS`            | Tool integrations, version control, and debugger.          |
| System settings | `SYSTEM`           | System-level IDE settings not covered by other categories. |
| –               | `OTHER`            | Default value, never synchronized.                         |

The [_Backup and Sync_ settings](https://www.jetbrains.com/help/idea/sharing-your-ide-settings.html#enable-backup-sync) list each installed non-bundled plugin under its [plugin name](plugin_configuration_file.md#idea-plugin__name).
The checkbox next to a plugin controls the synchronization of the plugin itself.
When the checkbox is selected, _Backup and Sync_ installs, enables, or disables the plugin in the other IDE installations to match the current IDE.
The checkbox doesn't control the synchronization of the plugin's settings, which depends on the `category` of its components.

## Supporting Export Settings and Backup and Sync

To make the state of a persistent state component available to both the [](#export-settings) feature and the [](#backup-and-sync-plugin), the following conditions must be met:
1. The component is an application-level service declared in the plugin configuration file (see [](#requirements-for-roamable-components)).
2. The component state is stored in a custom storage file, for example, <path>myPlugin.xml</path>.
3. The `roamingType` attribute of the `@Storage` annotation is set explicitly to `RoamingType.DEFAULT` or `RoamingType.PER_OS`.
   Omitting the attribute is equivalent to `RoamingType.DEFAULT`, but setting it explicitly makes the intention to share the state clear.
4. The `category` attribute of the `@State` annotation is set to a `SettingsCategory` constant other than `OTHER`.
   Otherwise, the component state can be exported, but it is not synchronized by the _Backup and Sync_ plugin.
5. The component has a user-friendly name in the _Export Settings_ dialog (see [](#presentable-name-in-export-settings)).
   Either the `presentableName` attribute of the `@State` annotation is set, or the plugin's resource bundle defines the `exportable.<name>.presentable.name` key.
6. All components stored in the same file have the same `roamingType` value.
