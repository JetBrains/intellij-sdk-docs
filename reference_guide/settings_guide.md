---
title: Settings Guide
---

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

_Settings_ persistently store states that control the behavior and appearance of IntelliJ Platform-based IDEs.
On this page the term "Settings" means the same as "Preferences" on some platforms.

Plugins can create and store Settings to capture their configuration in a way that uses the IntelliJ Platform [Persistence Model](/basics/persisting_state_of_components.md).
UI for these custom Settings can be added to the [IDE Settings dialog](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html).

Settings can [affect different levels](https://www.jetbrains.com/help/idea/configuring-project-and-ide-settings.html) of scope.
This document describes adding custom Settings at the Project and Application (or Global, IDE) levels. 

* bullet list
{:toc}

## Extension Points for Settings
Custom Settings implementations are declared in a plugin's configuration (`plugin.xml`) file using one of two Extension Points, depending on the level of the Settings.
Many [attributes](#settings-ep-attributes) are shared between the Extension Point declarations.

Application and Project Settings typically provide an implementation based on the [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) interface.  
See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information.

>**Note** For performance reasons, the recommended approach is to declare as much information as possible about a Settings' implementation using attributes in an EP. If it is not declared, the component must be loaded to retrieve it from the implementation, degrading UI responsiveness.

### EP for Declaring Application Settings
Settings at the Application level use the `com.intellij.applicationConfigurable` EP.

An example `<applicationConfigurable>` EP declaration is shown below.
See [Settings EP Attributes](#settings-ep-attributes) for details.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <applicationConfigurable parentId="tools" instance="org.company.ApplicationSettingsConfigurable"
            id="org.company.ApplicationSettingsConfigurable" displayName="My Application Settings"/>
  </extensions>
``` 

### EP for Declaring Project Settings
Project level Settings use the `com.intellij.projectConfigurable` EP.

An example `<projectConfigurable>` EP declaration is shown below.
See [Settings EP Attributes](#settings-ep-attributes) for details.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <projectConfigurable parentId="tools" instance="org.company.ProjectSettingsConfigurable"
            id="org.company.ProjectSettingsConfigurable" displayName="My Project Settings"
            nonDefaultProject="true"/>
  </extensions>
``` 

### Settings EP Attributes 
Readers are encouraged to review the Javadoc comments for [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java).
These comments apply to Settings EP declarations in general, not just for implementations based on the `Configurable` interface.
This section provides some additional clarification of those comments.

#### Table of Attributes
The attributes supported by `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` EPs are in the table below:

| Attribute | Implementation<br>Basis |Required |&ensp; &ensp; Attribute Value |
| :---------- | :-----: | :--------: |:------ |
| `instance` | `Configurable` | Y | FQN of implementation. See [The Configurable Interface](#the-configurable-interface) for more information. |
| `provider` | `ConfigurableProvider` | Y | FQN of implementation. See [The ConfigurableProvider Class](#the-configurableprovider-class) for more information. |
| `nonDefaultProject` | `Configurable` | Y | Applicable _only_ to the `com.intellij.projectConfigurable` (project Settings) EP.<br>`true` = show Settings for all projects _except_ the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).<br>`false` = show Settings for all projects. |
| `displayName` | `Configurable`<br>`ConfigurableProvider` | (1) | The non-localized Settings name visible to users, which is needed for the Settings dialog left-side menu.<br>Providing a Settings display name is highly recommended. If it isn’t declared, the component gets instantiated to retrieve its name dynamically. Loading the plugin's classes increases the delay before showing the Settings dialog.<br>For a _localized_ visible name omit `displayName` and use the `key` and `bundle` attributes. |
| `key` and<br>`bundle` | `Configurable`<br>`ConfigurableProvider` | (1) | The [localization](/reference_guide/localization_guide.md) key and bundle for the Settings name visible to users.<br>See the performance note listed for `displayName`.<br>For non-localized visible names omit `key` and `bundle` and use `displayName`. |
| `id` | `Configurable`<br>`ConfigurableProvider` | Y | The unique, FQN identifier for this implementation.<br>The FQN should be based on the plugin `id` to ensure uniqueness. |
| `parentId` | `Configurable`<br>`ConfigurableProvider` |   | This attribute is used to create a hierarchy of Settings. If set, this component is declared one of the specified `parentId` component's children. Typically used for placing a Settings panel within the Settings Dialog menu. Acceptable values for `parentId` are given in [Values for Parent ID Attribute](#values-for-parent-id-attribute).<br>`groupId` is deprecated.(2) |
| `groupWeight` | `Configurable`<br>`ConfigurableProvider` |   | Specifies the weight (stacking order) of this component within the group of a parent configurable component. The default weight is 0, meaning lowest in the order.<br>If one child in a group or a parent component has non-zero weight, all children will be sorted descending by their weight. If the weights are equal, the components will be sorted ascending by their display name. |
| `dynamic` | `Configurable.Composite` |   | This component's children are dynamically calculated by calling the `getConfigurables()` method.<br>Not recommended because it requires loading additional classes while building a Settings tree. If possible, use XML attributes instead. |
| `childrenEPName` | `Configurable` |   | Specifies the FQN name of the Extension Point that will be used to calculate the children of this component. |

**Notes:**  
1) One of these two attributes must be specified, but only one.   
2) If both `groupId` and `parentId` are specified, a warning is logged.

#### Values for Parent ID Attribute
The table below shows the allowed values for the `parentId` attribute of the `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` EPs.  
See the [previous section](#table-of-attributes) for all supported attributes.

| `parentId`<br>Value| Group | Details |
|:---   | :---: |:---  |
| _none_ | None  | Default: If this attribute is not set, the component is added to the `Other` Settings group.  |
| `root` | Super Parent | The parent of all existing groups. Generally not used, except for creating new groups (categories) to populate with Settings panels. For example, it might be used for large suites of Settings or small IDEs built on top of the IntelliJ Platform.  |
| `appearance` | Appearance & Behavior | This child group contains Settings to personalize IDE appearance, such as: changing themes and font size. In addition, it covers Settings to customize behavior such as keymaps, configuring plugins, and system Settings such as password policies, HTTP proxy, updates, and more.  |
| `editor` | Editor | Child group containing Settings to personalize source code appearance, such as fonts, highlighting styles, indents, etc.<br>It also contains Settings to customize the editor appearance such as line numbers, caret placement, tabs, source code inspections, setting up templates, and file encodings.  | |
| `build` | Build, Execution, Deployment |  Child group containing Settings to configure project integration with different build tools, modify the default compiler Settings, manage server access configurations, customize the debugger behavior, etc.  |
| `build.tools` | Build Integration | A subgroup of `build`. This subgroup configures project integration with build tools such as Maven, Gradle, or Gant. |
| `language` | Languages and Frameworks | Child group containing Settings related to specific language frameworks and technologies used in the project. |
| `tools` | 3rd Party Settings | Child group containing Settings to configure integration with third-party applications, specify the SSH Terminal connection Settings, manage server certificates and tasks, configure diagrams layout, etc. |
| `other` | Catch-all | IntelliJ IDEA no longer uses this group. It was a catch-all child category containing Settings related to non-bundled custom plugins that are not assigned to any other category.   |
| `project` | Project-related Settings | IntelliJ IDEA no longer uses this group. It was intended to store some project-related settings. |


## Implementations for Settings Extension Points
Implementations for `com.intellij.projectConfigurable` and `com.intellij.applicationConfigurable` EPs can have one of two bases. 
* The [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) interface, which provides a named configurable component with a Swing form.
  Most of the Settings providers in the `intellij-community` code base are based on the `Configurable` interface or one of its sub- or supertypes.
* The [`ConfigurableProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/ConfigurableProvider.java) class, which can hide a configurable component from the Settings dialog based on runtime conditions.

### The Configurable Interface
Many Settings in the `intellij-community` code base implement `Configurable` or one of its subtypes, such as [`SearchableConfigurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SearchableConfigurable.java). 
Readers are encouraged to review the Javadoc comments for `Configurable`.
Some key points are emphasized here. 

#### Constructors
Implementations must meet several requirements for constructors. 
* Application Settings implementations, declared using the [`applicationConfiguration` EP](#ep-for-declaring-application-settings), must have a default constructor with no arguments. 
* Project Settings implementations, declared using the [`projectSettings` EP](#ep-for-declaring-project-settings), must declare a constructor with a single argument of type [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java).

For a `Configurable` implementation properly declared using an EP, the implementation's constructor is not called by the IntelliJ Platform until the corresponding Settings `displayName` entry is chosen in the Settings Dialog menu.
For performance reasons, do not create any Swing components in a constructor.

> **Warning** The IntelliJ Platform may instantiate a `Configurable` implementation on a background thread, so creating Swing components in a constructor can degrade UI responsiveness.  

#### IntelliJ Platform Interactions with Configurable
Interactions between the IntelliJ Platform and a generic implementation of the `Configurable` interface are documented in the source file.
A few high points are reviewed here:
* After calling `Configurable.createComponent()`, the IntelliJ Platform immediately calls `Configurable.reset()` to initialize the values of the Settings.
  So initialization of Setting values in the constructor or `createComponent()` is unnecessary.
* See the [Constructors](#constructors) section for information about when a Settings object is instantiated.
* Once instantiated, a `Configurable` instance is valid until the user presses **OK** or **Cancel** in the Settings Dialog.
  This lifetime holds true regardless of whether the implementation's Settings are changed, or the user chooses a different entry on the Settings Dialog menu. 
  The IntelliJ Platform calls the `Configurable.disposeUIResources()` method as the last call to an instantiated Settings implementation.

#### Configurable Marker Interfaces
Implementations based on `Configurable` can have nested interfaces, which provide additional flexibility in the implementation.
See the `Configurable` interface file for details.
 
The following nested interfaces are markers, which convey information about the Swing form to the IntelliJ Platform:
  * `Configurable.NoScroll` - Notifies the Settings dialog not to add scroll bars to the Swing form.
    By default, a plugin's Settings component is put into a scrollable pane.
    However, a Settings panel can have a JTree, which requires its own JScrollPane. So NoScroll interface should be used to remove outer JScrollPane.
  * `Configurable.NoMargin` - Notifies the Settings dialog not to add an empty border to the Swing form.
    By default, an empty border is added for a plugin's Settings component.

#### Additional Interfaces Based on Configurable
There are over 90 interfaces and classes based on `Configurable` in the IntelliJ Platform code base.
These subtypes have been specialized for particular kinds of Settings.
For example, **Settings/Preferences \| Editor \| General \|Appearance** allows adding Settings via [`EditorSmartKeysConfigurableEP`](upsource:///platform/lang-impl/src/com/intellij/application/options/editor/EditorSmartKeysConfigurableEP.java) and `com.intellij.editorSmartKeysConfigurable` EP.

### The ConfigurableProvider Class
The [`ConfigurableProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/options/ConfigurableProvider.java) class only provides a `Configurable` implementation if its runtime conditions are met. 
The IntelliJ Platform first calls the `ConfigurableProvider.canCreateConfigurable()`, which evaluates runtime conditions to determine if Settings changes make sense in the current context.
If the Settings make sense to display, `canCreateConfigurable()` returns `true`.
In that case the IntelliJ Platform calls `ConfigurableProvider.createConfigurable()`, which returns the `Configurable` object for its Settings implementation.

Implementations should subclass `ConfigurableProvider` and add their custom logic to `canCreateConfigurable()`, and the code to instantiate their `Configurable` implementation in createConfigurable().

By choosing not to provide a `Configuration` implementation in some circumstances, the `ConfigurableProvider` opts out of the Settings display and modification process.
The use of `ConfigurableProvider` as a basis for a Settings implementation is declared using [attributes](#table-of-attributes) in the EP declaration.


