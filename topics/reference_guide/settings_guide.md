<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Settings Guide

<link-summary>Adding entries in Settings.</link-summary>

_Settings_ persistently store states that control the behavior and appearance of IntelliJ Platform-based IDEs.
On this page, the term "Settings" means the same as "Preferences" on some platforms.

Plugins can create and store Settings to capture their configuration in a way that uses the IntelliJ Platform [Persistence Model](persisting_state_of_components.md).
The User Interface (UI) for these custom Settings can be added to the [IDE Settings dialog](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html).

Settings can [affect different levels](https://www.jetbrains.com/help/idea/configuring-project-and-ide-settings.html) of scope.
This document describes adding custom Settings at the Project and Application (or Global, IDE) levels.

> See [](settings_tutorial.md) for step-by-step instructions for creating a simple set of custom Settings.
>
{style="note"}

<snippet id="settings_ui_inspector">

> See [](internal_ui_inspector.md#inspecting-settings) on how to gather information in the IDE instance for <control>Settings</control> dialog.
>
{style="tip"}

</snippet>

## Extension Points for Settings

Custom Settings implementations are declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file using one of two extension points (EP), depending on the level of the Settings.
Many [attributes](#settings-declaration-attributes) are shared between the EP declarations.

Application and Project Settings typically provide an implementation based on the [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) interface because they do not have runtime dependencies.
See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information.

> For performance reasons, it is recommended to declare as much information as possible about a 'Settings' implementation using attributes in the EP element in the <path>plugin.xml</path> descriptor.
> If it is not declared, the component must be loaded to retrieve it from the implementation, degrading UI responsiveness.
>
{style="note"}

### Declaring Application Settings

The application-level settings are declared using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationConfigurable"/></include>.

An example `<applicationConfigurable>` EP declaration is shown below.
The declaration indicates the settings are a child of the `tools` settings group, the implementation FQN is `com.example.ApplicationSettingsConfigurable`, the unique ID is the same as the implementation fully qualified name (FQN), and the (non-localized) title displayed to users is "My Application Settings".
See [](#settings-declaration-attributes) for more information.

```xml
<extensions defaultExtensionNs="com.intellij">
  <applicationConfigurable
      parentId="tools"
      instance="com.example.ApplicationSettingsConfigurable"
      id="com.example.ApplicationSettingsConfigurable"
      displayName="My Application Settings"/>
</extensions>
```

> To [localize](providing_translations.md) the display name, instead of the `displayName` attribute, use `key` and `bundle` attributes pointing to a key in a [message bundle](internationalization.md#message-bundles).
> The same applies for [`projectConfigurable`](#declaring-project-settings) extensions.

### Declaring Project Settings

The project-level settings are declared using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectConfigurable"/></include>.

An example `<projectConfigurable>` EP declaration is shown below.
Similar to the application setting example above, but it includes the additional attribute `nonDefaultProject` indicating these settings do not apply to the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).
See [](#settings-declaration-attributes) for details.

```xml
<extensions defaultExtensionNs="com.intellij">
  <projectConfigurable
      parentId="tools"
      instance="com.example.ProjectSettingsConfigurable"
      id="com.example.ProjectSettingsConfigurable"
      displayName="My Project Settings"
      nonDefaultProject="true"/>
</extensions>
```

### Settings Declaration Attributes

Readers are encouraged to review the Javadoc comments for [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) because the attribute information applies to `ConfigurableProvider` as well as `Configurable`, as noted.
This section provides some additional clarification of those comments.

#### Table of Attributes

The attributes supported by <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationConfigurable"/></include> and <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectConfigurable"/></include> are in the table below:

| Attribute           | Required                        | <p>Attribute</p><p>Value</p>                                                                                                                                                                                                                                                                                                                                                                        | <p>Implementation</p><p>Basis</p>                  |
|---------------------|---------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|
| `instance`          | **yes** [(1)](#attribute-notes) | FQN of implementation. See [](#the-configurable-interface) for more information.                                                                                                                                                                                                                                                                                                                    | `Configurable`                                     |
| `provider`          | **yes** [(1)](#attribute-notes) | FQN of implementation. See [](#the-configurableprovider-class) for more information.                                                                                                                                                                                                                                                                                                                | `ConfigurableProvider`                             |
| `nonDefaultProject` | **yes**                         | <p>Applicable _only_ to the `com.intellij.projectConfigurable` (project Settings) EP.</p><p>`true` = show Settings for all projects _except_ the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).</p><p>`false` = show Settings for all projects.</p>                                                                                   | `Configurable`                                     |
| `displayName`       | **yes** [(2)](#attribute-notes) | <p>The non-localized Settings name visible to users, which is needed for the Settings dialog left-side menu.</p><p>For a _localized_ visible name omit `displayName` and use the `key` and `bundle` attributes.</p>                                                                                                                                                                                 | <p>`Configurable`</p><p>`ConfigurableProvider`</p> |
| `key` and `bundle`  | **yes** [(2)](#attribute-notes) | <p>The [localization](internationalization.md#message-bundles) key and bundle for the Settings name visible to users.</p><p>For non-localized visible names omit `key` and `bundle` and use `displayName`.</p>                                                                                                                                                                                      | <p>`Configurable`</p><p>`ConfigurableProvider`</p> |
| `id`                | **yes**                         | <p>The unique, FQN identifier for this implementation.</p><p>The FQN should be based on the plugin `id` to ensure uniqueness.</p>                                                                                                                                                                                                                                                                   | <p>`Configurable`</p><p>`ConfigurableProvider`</p> |
| `parentId`          | **yes**                         | <p>This attribute is used to create a hierarchy of Settings. This component is declared one of the specified `parentId` component's children. Typically used for placing a Settings panel within the Settings Dialog menu. Acceptable values for `parentId` are given in [](#values-for-parent-id-attribute).</p><p>`groupId` is deprecated. [(3)](#attribute-notes)</p>                            | <p>`Configurable`</p><p>`ConfigurableProvider`</p> |
| `groupWeight`       | no                              | <p>Specifies the weight (stacking order) of this component within the group of a parent configurable component. The default weight is 0, meaning lowest in the order.</p><p>If one child in a group or a parent component has non-zero weight, all children will be sorted descending by their weight. If the weights are equal, the components will be sorted ascending by their display name.</p> | <p>`Configurable`</p><p>`ConfigurableProvider`</p> |
| `dynamic`           | no                              | <p>This component's children are dynamically calculated by calling the `getConfigurables()` method.</p><p>Not recommended because it requires loading additional classes while building a Settings tree. If possible, use XML attributes instead.</p>                                                                                                                                               | `Configurable.Composite`                           |
| `childrenEPName`    | no                              | Specifies the FQN name of the Extension Point that will be used to calculate the children of this component.                                                                                                                                                                                                                                                                                        | `Configurable`                                     |

##### Attribute Notes

(1) Either `instance` or `provider` must be specified depending on the implementation.

(2) Either `displayName` or `key` and `bundle` must be specified depending on whether the displayed Settings name is localized.

(3) If both `groupId` and `parentId` are specified, a warning is logged. Also, see _default_ entry in [](#values-for-parent-id-attribute).

#### Values for Parent ID Attribute

The table below shows all <control>Settings</control> groups and their corresponding value for the `parentId` attribute.
See the [previous section](#table-of-attributes) for all supported attributes.

| Group                                                | `parentId` Value | Details                                                                                                                                                                                                                                                                                                  |
|------------------------------------------------------|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Appearance & Behavior                                | `appearance`     | This child group contains Settings to personalize IDE appearance, such as: changing themes and font size. Also, it covers Settings to customize behavior such as keymaps, configuring plugins, and system Settings such as password policies, HTTP proxy, updates, and more.                             |
| Build, Execution, Deployment                         | `build`          | Child group containing Settings to configure project integration with different build tools, modify the default compiler Settings, manage server access configurations, customize the debugger behavior, etc.                                                                                            |
| Build Integration                                    | `build.tools`    | A subgroup of `build`. This subgroup configures project integration with build tools such as Maven, Gradle, or Gant.                                                                                                                                                                                     |
| Editor                                               | `editor`         | Child group containing Settings to personalize source code appearance, such as fonts, highlighting styles, indents, etc. It also contains Settings to customize the editor's appearance, such as line numbers, caret placement, tabs, source code inspections, setting up templates, and file encodings. | |
| Languages and Frameworks                             | `language`       | Child group containing Settings related to specific language frameworks and technologies used in the project.                                                                                                                                                                                            |
| 3rd Party Settings                                   | `tools`          | Child group containing Settings to configure integration with third-party applications, specify the SSH Terminal connection Settings, manage server certificates and tasks, configure diagrams layout, etc.                                                                                              |
| Super Parent                                         | `root`           | The invisible parent of all existing groups. Not used except for IDEs built on top of the IntelliJ Platform, or extensive suites of Settings. You should not place settings in this group.                                                                                                               |
| <p>`other`</p><p>**Do not use**</p>                  | _default_        | If neither `parentId` nor `groupId` attribute is set, the component is added to the `other` Settings group. This is undesirable; see `other` group description.                                                                                                                                          |
| <p>Catch-all</p><p>**Deprecated**</p>                | `other`          | The IntelliJ Platform no longer uses this group. Do not use this group. Use the `tools` group instead.                                                                                                                                                                                                   |
| <p>Project-related Settings</p><p>**Deprecated**</p> | `project`        | The IntelliJ Platform no longer uses this group. It was intended to store some project-related settings. Do not use this group.                                                                                                                                                                          |

## Implementations for Settings Extension Points

Implementations for <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationConfigurable"/></include> and <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectConfigurable"/></include> can have one of two bases:
* The [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) interface, which provides a named configurable component with a Swing form.
  Most Settings providers are based on the `Configurable` interface or one of its sub- or supertypes.
* The [`ConfigurableProvider`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/ConfigurableProvider.java) class, which can hide a configurable component from the Settings dialog based on runtime conditions.

### The `Configurable` Interface

Many Settings in the `intellij-community` code base implement `Configurable` or one of its subtypes, such as [`SearchableConfigurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/SearchableConfigurable.java).
Readers are encouraged to review the Javadoc comments for `Configurable`.

#### Constructors

Implementations must meet several requirements for constructors.
* Application Settings implementations, declared using the [`applicationConfigurable` EP](#declaring-application-settings), must have a default constructor with no arguments.
* Project Settings implementations, declared using the [`projectConfigurable` EP](#declaring-project-settings), must declare a constructor with a single argument of type [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java).
* Constructor injection (other than for `Project`) is not allowed.

For a `Configurable` implementation correctly declared using an EP, the implementation's constructor is not invoked by the IntelliJ Platform until a user chooses the corresponding Settings `displayName` in the Settings Dialog menu.

> The IntelliJ Platform may instantiate a `Configurable` implementation on a background thread, so creating Swing components in a constructor can degrade UI responsiveness.
>
{style="warning"}

#### IntelliJ Platform Interactions with `Configurable`

The instantiation of a generic `Configurable` implementation is documented in the interface file.
A few high-level points are reviewed here:
* The `Configurable.reset()` method is invoked immediately after `Configurable.createComponent()`.
  Initialization of Setting values in the constructor or `createComponent()` is unnecessary.
* See the [](#constructors) section for information about when a Settings object is instantiated.
* Once instantiated, a `Configurable` instance's lifetime continues regardless of whether the implementation's Settings are changed, or the user chooses a different entry on the Settings Dialog menu.
* A `Configurable` instance's lifetime ends when <control>OK</control> or <control>Cancel</control> is selected in the Settings Dialog.
  An instance's `Configurable.disposeUIResources()` is called when the Settings Dialog is closing.

To open the Settings dialog or show a specific `Configurable`, see [`ShowSettingsUtil`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/ShowSettingsUtil.java).

#### `Configurable` Marker Interfaces

Implementations based on `Configurable` can implement marker interfaces, which provide additional flexibility in the implementation.

`Configurable.NoScroll`
: Do not add scroll bars to the form. By default, a plugin's Settings component is put into a scrollable pane.
  However, a Settings panel can have a `JTree`, which requires its own `JScrollPane`.
  So the `NoScroll` interface should be used to remove the outer `JScrollPane`.

`Configurable.NoMargin`
: Do not add an empty border to the form. By default, an empty border is added for a plugin's Settings component.

`Configurable.Beta`
: _(2022.3)_ Adds <control>Beta</control> label next to settings page title in <control>Settings</control> tree.

#### Additional Interfaces Based on `Configurable`

There are classes in the IntelliJ Platform specialized in particular types of Settings.
These subtypes are based on `com.intellij.openapi.options.ConfigurableEP`.
For example, <ui-path>Settings | Editor | General | Appearance</ui-path> allows adding Settings via [`EditorSmartKeysConfigurableEP`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/EditorSmartKeysConfigurableEP.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.editorSmartKeysConfigurable"/></include>.

#### Examples

Existing implementations of `Configurable` in the IntelliJ Platform that can serve as a reference are:
* [`ConsoleConfigurable`](%gh-ic%/platform/lang-impl/src/com/intellij/execution/console/ConsoleConfigurable.java) (application configurable)
* [`AutoImportOptionsConfigurable`](%gh-ic%/platform/lang-impl/src/com/intellij/application/options/editor/AutoImportOptionsConfigurable.kt) (project configurable)

### The `ConfigurableProvider` Class

The [`ConfigurableProvider`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/ConfigurableProvider.java) class only provides a `Configurable` implementation if its runtime conditions are met.
The IntelliJ Platform first calls the `ConfigurableProvider.canCreateConfigurable()`, which evaluates runtime conditions to determine if Settings changes make sense in the current context.
If the Settings make sense to display, `canCreateConfigurable()` returns `true`.
In that case the IntelliJ Platform calls `ConfigurableProvider.createConfigurable()`, which returns the `Configurable` instance for its Settings implementation.

By choosing not to provide a `Configuration` implementation in some circumstances, the `ConfigurableProvider` opts out of the Settings display and modification process.
The use of `ConfigurableProvider` as a basis for a Settings implementation is declared using [attributes](#table-of-attributes) in the EP declaration.

**Examples:**
- [`RunToolbarSettingsConfigurableProvider`](%gh-ic%/platform/execution-impl/src/com/intellij/execution/runToolbar/RunToolbarSettingsConfigurableProvider.kt)
- [`VcsManagerConfigurableProvider`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/configurable/VcsManagerConfigurableProvider.java)
