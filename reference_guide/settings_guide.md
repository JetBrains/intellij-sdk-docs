---
title: Settings Guide
---

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

_Settings_ persistently store states that control the behavior and appearance of IntelliJ Platform-based IDEs.
On this page, the term "Settings" means the same as "Preferences" on some platforms.

Plugins can create and store Settings to capture their configuration in a way that uses the IntelliJ Platform [Persistence Model](/basics/persisting_state_of_components.md).
The User Interface (UI) for these custom Settings can be added to the [IDE Settings dialog](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html).

Settings can [affect different levels](https://www.jetbrains.com/help/idea/configuring-project-and-ide-settings.html) of scope.
This document describes adding custom Settings at the Project and Application (or Global, IDE) levels. 

* bullet list
{:toc}

## Extension Points for Settings
Custom Settings implementations are declared in a plugin's configuration (`plugin.xml`) file using one of two Extension Points (EPs), depending on the level of the Settings.
Many [attributes](#settings-declaration-attributes) are shared between the EP declarations.

Application and Project Settings typically provide an implementation based on the [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) interface because they do not have runtime dependencies. 
See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information.
 
> **NOTE** For performance reasons, the recommended approach is to declare as much information as possible about a Settings' implementation using attributes in the Extension Point. If it is not declared, the component must be loaded to retrieve it from the implementation, degrading UI responsiveness.

### Declaring Application Settings
Settings at the Application level use the `com.intellij.applicationConfigurable` EP.

An example `<applicationConfigurable>` EP declaration is shown below.
The declaration indicates the settings are a child of the `tools` settings group, the implementation FQN is `org.company.ApplicationSettingsConfigurable`, the unique ID is the same as the implementation FQN, and the (non-localized) title displayed to users is "My Application Settings".
See [Settings Declaration Attributes](#settings-declaration-attributes) for more information.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <applicationConfigurable parentId="tools" instance="org.company.ApplicationSettingsConfigurable"
            id="org.company.ApplicationSettingsConfigurable" displayName="My Application Settings"/>
  </extensions>
``` 

### Declaring Project Settings
Project level Settings use the `com.intellij.projectConfigurable` EP.

An example `<projectConfigurable>` EP declaration is shown below.
Similar to the application setting example above, but it includes the additional attribute `nonDefaultProject` indicating these settings do not apply to the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).
See [Settings Declaration Attributes](#settings-declaration-attributes) for details.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <projectConfigurable parentId="tools" instance="org.company.ProjectSettingsConfigurable"
            id="org.company.ProjectSettingsConfigurable" displayName="My Project Settings"
            nonDefaultProject="true"/>
  </extensions>
``` 

### Settings Declaration Attributes 
Readers are encouraged to review the Javadoc comments for [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) because the attribute information applies to `ConfigurableProvider` as well as `Configurable`, as noted.
This section provides some additional clarification of those comments.

#### Table of Attributes
The attributes supported by `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` EPs are in the table below:

| Attribute |Implementation<br>Basis | Required&ensp;&ensp; | Attribute<br>Value  |
| :---------- | :----- | :--------: |:------ |
| `instance` | `Configurable` | (1) | FQN of implementation. See [The Configurable Interface](#the-configurable-interface) for more information. |
| `provider` | `ConfigurableProvider` | (1) | FQN of implementation. See [The ConfigurableProvider Class](#the-configurableprovider-class) for more information. |
| `nonDefaultProject`  | `Configurable` | Y | Applicable _only_ to the `com.intellij.projectConfigurable` (project Settings) EP.<br>`true` = show Settings for all projects _except_ the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).<br>`false` = show Settings for all projects. |
| `displayName` | `Configurable`<br>`ConfigurableProvider` | (2) | The non-localized Settings name visible to users, which is needed for the Settings dialog left-side menu.<br>For a _localized_ visible name omit `displayName` and use the `key` and `bundle` attributes. |
| `key` and<br>`bundle` | `Configurable`<br>`ConfigurableProvider` | (2) | The [localization](/reference_guide/localization_guide.md) key and bundle for the Settings name visible to users.<br>For non-localized visible names omit `key` and `bundle` and use `displayName`. |
| `id` | `Configurable`<br>`ConfigurableProvider` | Y | The unique, FQN identifier for this implementation.<br>The FQN should be based on the plugin `id` to ensure uniqueness. |
| `parentId` | `Configurable`<br>`ConfigurableProvider` | Y | This attribute is used to create a hierarchy of Settings. This component is declared one of the specified `parentId` component's children. Typically used for placing a Settings panel within the Settings Dialog menu. Acceptable values for `parentId` are given in [Values for Parent ID Attribute](#values-for-parent-id-attribute).<br>`groupId` is deprecated.(3) |
| `groupWeight` | `Configurable`<br>`ConfigurableProvider` | N | Specifies the weight (stacking order) of this component within the group of a parent configurable component. The default weight is 0, meaning lowest in the order.<br>If one child in a group or a parent component has non-zero weight, all children will be sorted descending by their weight. If the weights are equal, the components will be sorted ascending by their display name. |
| `dynamic` | `Configurable.Composite` | N | This component's children are dynamically calculated by calling the `getConfigurables()` method.<br>Not recommended because it requires loading additional classes while building a Settings tree. If possible, use XML attributes instead. |
| `childrenEPName` | `Configurable` | N | Specifies the FQN name of the Extension Point that will be used to calculate the children of this component. |

**Attribute Notes:**  
1) Either `instance` or `provider` must be specified depending on the implementation.   
2) One of these attribute sets must be specified depending on whether the displayed Settings name is localized.   
3) If both `groupId` and `parentId` are specified, a warning is logged. Also, see _default_ entry in [Values for Parent ID Attribute](#values-for-parent-id-attribute).

#### Values for Parent ID Attribute
The table below shows the allowed values for the `parentId` attribute.  
See the [previous section](#table-of-attributes) for all supported attributes.

| `parentId` Value | Group | Details |
| :--------------- | :---- | :------ |
|_default_ | `other`  | If neither `parentId` nor `groupId` attribute is set, the component is added to the `other` Settings group. This is undesirable; see `other` group description.  |
|`appearance` | Appearance & Behavior | This child group contains Settings to personalize IDE appearance, such as: changing themes and font size. Also, it covers Settings to customize behavior such as keymaps, configuring plugins, and system Settings such as password policies, HTTP proxy, updates, and more.  |
|`build` | Build, Execution, Deployment |  Child group containing Settings to configure project integration with different build tools, modify the default compiler Settings, manage server access configurations, customize the debugger behavior, etc.  |
|`build.tools` | Build Integration | A subgroup of `build`. This subgroup configures project integration with build tools such as Maven, Gradle, or Gant. |
|`editor` | Editor | Child group containing Settings to personalize source code appearance, such as fonts, highlighting styles, indents, etc. It also contains Settings to customize the editor's appearance, such as line numbers, caret placement, tabs, source code inspections, setting up templates, and file encodings.  | |
|`language` | Languages and Frameworks | Child group containing Settings related to specific language frameworks and technologies used in the project. |
|`tools` | 3rd Party Settings | Child group containing Settings to configure integration with third-party applications, specify the SSH Terminal connection Settings, manage server certificates and tasks, configure diagrams layout, etc. |
|`root` | Super Parent | The invisible parent of all existing groups. Not used except for IDEs built on top of the IntelliJ Platform, or extensive suites of Settings. You should not place settings in this group. |
|`other` | Catch-all | The IntelliJ Platform no longer uses this group. Do not use this group. Use the `tools` group instead.  |
|`project` | Project-related Settings | The IntelliJ Platform no longer uses this group. It was intended to store some project-related settings. Do not use this group. |


## Implementations for Settings Extension Points
Implementations for `com.intellij.projectConfigurable` and `com.intellij.applicationConfigurable` EPs can have one of two bases: 
* The [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) interface, which provides a named configurable component with a Swing form.
  Most Settings providers are based on the `Configurable` interface or one of its sub- or supertypes.
* The [`ConfigurableProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/ConfigurableProvider.java) class, which can hide a configurable component from the Settings dialog based on runtime conditions.

### The Configurable Interface
Many Settings in the `intellij-community` code base implement `Configurable` or one of its subtypes, such as [`SearchableConfigurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SearchableConfigurable.java). 
Readers are encouraged to review the Javadoc comments for `Configurable`.

#### Constructors
Implementations must meet several requirements for constructors. 
* Application Settings implementations, declared using the [`applicationConfiguration` EP](#declaring-application-settings), must have a default constructor with no arguments. 
* Project Settings implementations, declared using the [`projectSettings` EP](#declaring-project-settings), must declare a constructor with a single argument of type [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java).
* Beginning in 2020.2, constructor injection (other than for `Project`) is not allowed.

For a `Configurable` implementation correctly declared using an EP, the implementation's constructor is not invoked by the IntelliJ Platform until a user chooses the corresponding Settings `displayName` in the Settings Dialog menu.

> **WARNING** The IntelliJ Platform may instantiate a `Configurable` implementation on a background thread, so creating Swing components in a constructor can degrade UI responsiveness.  

#### IntelliJ Platform Interactions with Configurable
The instantiation of a generic `Configurable` implementation is documented in the interface file.
A few high-level points are reviewed here:
* The `Configurable.reset()` method is invoked immediately after `Configurable.createComponent()`. 
  Initialization of Setting values in the constructor or `createComponent()` is unnecessary.
* See the [Constructors](#constructors) section for information about when a Settings object is instantiated.
* Once instantiated, a `Configurable` instance's lifetime continues regardless of whether the implementation's Settings are changed, or the user chooses a different entry on the Settings Dialog menu.
* A `Configurable` instance's lifetime ends when **OK** or **Cancel** is selected in the Settings Dialog.
  An instance's `Configurable.disposeUIResources()` is called when the Settings Dialog is closing.

To open Settings dialog or show specific `Configurable`, see [`ShowSettingsUtil`](upsource:///platform/platform-api/src/com/intellij/openapi/options/ShowSettingsUtil.java).

#### Configurable Marker Interfaces
Implementations based on `Configurable` can implement marker interfaces, which provide additional flexibility in the implementation.
 
The following nested interfaces are markers, which convey information about the form to the IntelliJ Platform:
  * `Configurable.NoScroll` - Notifies the Settings dialog not to add scroll bars to the form.
    By default, a plugin's Settings component is put into a scrollable pane.
    However, a Settings panel can have a `JTree`, which requires its own `JScrollPane`. 
    So `NoScroll` interface should be used to remove the outer `JScrollPane`.
  * `Configurable.NoMargin` - Notifies the Settings dialog not to add an empty border to the form.
    By default, an empty border is added for a plugin's Settings component.

#### Additional Interfaces Based on Configurable
There are classes in the IntelliJ Platform specialized for particular types of Settings.
These subtypes are based on `com.intellij.openapi.options.ConfigurableEP`.
For example, **Settings/Preferences \| Editor \| General \|Appearance** allows adding Settings via [`EditorSmartKeysConfigurableEP`](upsource:///platform/lang-impl/src/com/intellij/application/options/editor/EditorSmartKeysConfigurableEP.java) and `com.intellij.editorSmartKeysConfigurable` EP.

### The ConfigurableProvider Class
The [`ConfigurableProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/ConfigurableProvider.java) class only provides a `Configurable` implementation if its runtime conditions are met. 
The IntelliJ Platform first calls the `ConfigurableProvider.canCreateConfigurable()`, which evaluates runtime conditions to determine if Settings changes make sense in the current context.
If the Settings make sense to display, `canCreateConfigurable()` returns `true`.
In that case the IntelliJ Platform calls `ConfigurableProvider.createConfigurable()`, which returns the `Configurable` object for its Settings implementation.

By choosing not to provide a `Configuration` implementation in some circumstances, the `ConfigurableProvider` opts out of the Settings display and modification process.
The use of `ConfigurableProvider` as a basis for a Settings implementation is declared using [attributes](#table-of-attributes) in the EP declaration.
