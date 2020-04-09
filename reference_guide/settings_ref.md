---
title: Settings
---

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

_Settings_ persistently store states that control the behavior and appearance of IntelliJ Platform-based IDEs. 
Plugins can create and store settings to capture their configuration in a way that persists despite restarting the IDE or closing and reopening Projects.
UI for these custom settings can be added to the [IDE Settings dialog](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html).

Settings can [affect different levels](https://www.jetbrains.com/help/idea/configuring-project-and-ide-settings.html) of scope.
This document describes adding custom settings at the Project and Application (or Global, IDE) levels. 

[//]: # (todo: Need to add content to the existing /basics/persisting_state_of_components.md content so this page can point to major topics suggested by “What are settings” section in outline:)
[//]: # (todo: - Non-Roamable isn't recommended for Settings)
[//]: # (todo: - instances of extensions cannot persist their state by implementing PersistentStateComponent)

* bullet list
{:toc}

## Extension Points for Settings
Custom Settings implementations are declared in a plugin's configuration (`plugin.xml`) file using one of two Extension Points, depending on the level of the Settings.
Many [attributes](#attributes-for-settings-ep) are shared between the Extension Point declarations.

Application and Project Settings typically provide an implementation based on the [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java) interface.  
See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information.

>**Note** For performance reasons the recommended approach is to declare as much information as possible about a settings' implementation using attributes in an EP. If it is not declared the component must be loaded to retrieve it from the implementation, degrading UI responsiveness.

### EP for Declaring Application Level Settings
Settings at the Application level use the `com.intellij.applicationConfigurable` EP.
Application level Settings that implement the `Configurable` interface must have a default constructor with no arguments.

An example `applicationConfigurable` EP declaration is shown below.
See [Attributes for Settings EP](#attributes-for-settings-ep) for details.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <applicationConfigurable groupId="tools" instance="org.intellij.sdk.settings.AppSettingsConfigurable"
            id="org.intellij.sdk.settings.AppSettingsConfigurable" displayName="SDK: Application Settings Example"/>
  </extensions>
``` 

### EP for Declaring Project Level Settings
Project level Settings use the `com.intellij.projectConfigurable` EP.
A `Configurable` implementation for Project level Settings must declare a constructor with a single argument of type [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java).

An example `projectConfigurable` EP declaration is shown below.
See [Attributes for Settings EP](#attributes-for-settings-ep) for details.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <projectConfigurable groupId="tools" instance="org.intellij.sdk.settings.AppSettingsConfigurable"
            id="org.intellij.sdk.settings.AppSettingsConfigurable" displayName="SDK: Application Settings Example"/>
  </extensions>
``` 

### Attributes for Settings EP
Readers are encouraged to review the Javadoc comments for [`Configurable`](upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java).
These comments apply to Settings EP declarations in general, not just for implementations based on the `Configurable` interface.
This section provides some additional clarification of those comments.

The attributes supported by `applicationConfigurable` and `projectConfigurable` EPs are given in the table below:
  
| Attribute | Implementation<br>Basis | Optional/<br>Required | Value |
|:---   |  :---:  |  :---:  |:---  |
| `instance` | `Configurable` | R | FQN of implementation.<br>See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information about `Configurable`. |
| `provider` | `ConfigurableProvider` | R | FQN of implementation.<br>See [Implementations for Settings Extension Points](#implementations-for-settings-extension-points) for more information about `ConfigurableProvider`. |
| `nonDefaultProject` | `Configurable` | R | Required _only_ for the `projectConfigurable` (project settings) EP.<br>`true` = show settings for all projects _except_ the [default project](https://www.jetbrains.com/help/idea/configure-project-settings.html#new-default-settings).<br>`false` = show settings for all projects. |
| `displayName` | `Configurable`<br>`ConfigurableProvider` | O[^1] | The _non-localized_ settings name visible to users, which is needed for the Settings dialog left-side menu.<br>Providing a settings display name is highly recommended. If it isn’t declared, the component gets instantiated to retrieve its name dynamically. Loading the plugin's classes increases the delay before showing the settings dialog.<br>For a _localized_ visible name omit `displayName` and use the `key` and `bundle` attributes. |
| `key` and<br>`Bundle` | `Configurable`<br>`ConfigurableProvider` | O[^1] | The _localized_ settings name visible to users.<br>See the performance note listed for `displayName`.<br>For a _non-localized_ visible names omit `key` and `bundle` and use `displayName`. |
| `id` | `Configurable`<br>`ConfigurableProvider` | R | The unique, FQN identifier for this implementation.<br>The FQN should be based on the plugin `id` to ensure uniqueness. |
| `parentId` | `Configurable`<br>`ConfigurableProvider` | O[^2] | This attribute is used to create a hierarchy of settings. If set, this component is declared one of the specified `parentId` component's children.<br>An application configurable can be a parent of project configurable. |
| `groupId` | `Configurable`<br>`ConfigurableProvider` | O[^2] | If set this attribute specifies a top-level settings group to which this component belongs.<br>Acceptable values for `groupId` are: |
|   |   |   | _none_ - Default: If this attribute is not set, the component will be added to the `Other` settings group.  |
|   |   |   | `"other"` - A catch-all category containing settings related to non-bundled custom plugins that are not assigned to any other category. Note IntelliJ IDEA does not currently use this group.  |
|   |   |   | `"root"` - The parent of all existing groups. Generally not used, except for creating new groups (categories) to populate with settings panels.<br>For example, it might be used for large suites of settings or for small IDEs built on top of the IntelliJ Platform.  |
|   |   |   | `“appearance”` - The Appearance & Behavior group contains settings to personalize IDE appearance, such as: changing themes and font size.<br>In addition, it covers settings to customize behavior such as: keymaps, configuring plugins, and system settings such as password policies, HTTP proxy, updates and more.  |
|   |   |   | `"editor"` - The Editor group contains settings to personalize source code appearance, such as fonts, highlighting styles, indents, etc.<br>It also contains settings to customize the editor appearance such as: line numbers, caret placement, tabs, source code inspections, setting up templates, and file encodings.  | |
|   |   |   | `"project"` - Not used. |
|   |   |   | `"build"` - The Build, Execution, Deployment group contains settings to configure project integration with different build tools, modify the default compiler settings, manage server access configurations, customize the debugger behavior, etc.  |
|   |   |   | `"build.tools"` - A subgroup of `build`. This subgroup configures project integration with build tools such as Maven, Gradle, or Gant. |
|   |   |   | `"language"` - The Languages and Frameworks group configures settings related to specific frameworks and technologies used in the project. |
|   |   |   | `"tools"` - The Tools group contains settings to configure integration with third-party applications, specify the SSH Terminal connection settings, manage server certificates and tasks, configure diagrams layout, etc. |
| `groupWeight` | `Configurable`<br>`ConfigurableProvider` | O | Specifies the weight (stacking order) of this component within a group or a parent configurable component.<br>Default: The default weight is 0, meaning lowest in the order.<br>If one child in a group or a parent component has non-zero weight, all children will be sorted descending by their weight. If the weights are equal, the components will be sorted ascending by their display name. |
| `dynamic` | `Configurable.Composite` | O | This component's children are dynamically calculated by calling the `getConfigurables()` method.<br>Not recommended because it requires loading additional classes while building a settings tree. If possible, use XML attributes instead. |
| `childrenEPName` | `Configurable` | O | Specifies the FQN name of the extension point that will be used to calculate the children of this component. |
| ~~`implementation`~~ | N/A | X | Deprecated |  

[//]: # (todo: If parentId is preferred, should the attribute values under parentId rather than groupId?)
[//]: # (todo: Should groupId="other" be listed if it is not used by IDEA?)

[^1]: Only one of these two attributes can be specified.  
[^2]: One of these two options must be specified to declare a parent-child relationship.

### EP for Declaring a Parent-Child Configurable Relationship
There are multiple ways for creating parent-child relationships in groups of Settings.
However, as mentioned above, there are performance penalties for creating these relationships in implementations rather than using EP declarations.
The syntax for declaring parent-child relationships is presented here.

What does a parent-child relationship look like to the user? 
The Copyright settings are a good example.
Selecting **Settings/Preferences \| Editor | Copyright** reveals that Settings for copyrights has two children: **Copyright Profiles** and **Formatting**.
The formatting Settings has numerous children, for example **Java**.
  
#### Nested Settings Configurable EP
One way of declaring a parent-child relationship is by using two different declarations.
This form can be used regardless of whether the parent settings are in the same plugin.
As long as the `parentId` is known, a plugin can add settings as a child.

For example, here are two declarations for project settings.
The first is added to the `tools` group, and the second is added to the `id` of the parent:
```xml
  <extensions defaultExtensionNs="com.intellij">
    <projectConfigurable groupId="tools" id="com.intellij.sdk.tasks" displayName="Tasks" nonDefaultProject="true"
                         instance="com.intellij.sdk.TaskConfigurable"/>
    
    <projectConfigurable parentId="com.intellij.sdk.tasks" id="com.intellij.sdk.tasks.servers" displayName="Servers" nonDefaultProject="true"
                         instance="com.intellij.sdk.TaskRepositoriesConfigurable"/>
  </extensions>
```

A shorthand for this declarative approach is using the `com.intellij.configurable` EP.
This approach nests the child Settings declaration within the `projectConfigurable` or `applicationConfigurable` EP by using the `configurable` EP. 
There isn't a `parentId` for the `configurable` EP.
Instead, formatting restrictions are placed on the `id` attribute.
See the [attributes](#attributes-for-nested-settings-configurable) section.

The example below demonstrates a `configurable` EP declaration.
Using `configurable` EP would not be possible if the example Task Configurable (parent) Settings were declared in another plugin or file.
In that case, multiple `projectConfigurable` or `applicationConfigurable` EPs would be used as shown above. 
However, within the parent `projectConfigurable` EP declaration below, more `configurable` declarations can be added. 
```xml
  <extensions defaultExtensionNs="com.intellij">
    <projectConfigurable groupId="tools" id="com.intellij.sdk.tasks" displayName="Tasks" nonDefaultProject="true"
                         instance="com.intellij.sdk.TaskConfigurable"/>
      <configurable id="com.intellij.sdk.tasks.servers" displayName="Servers" nonDefaultProject="true"
                         instance="com.intellij.sdk.TaskRepositoriesConfigurable"/>
    </projectConfigurable>
  </extensions>
```

[//]: # (todo: Can <configurable> itself be nested?)

#### Attributes for Nested Settings Configurable EP
There is only one attribute unique to the `configurable` EP.
The `id` attribute becomes compound:

| Attribute | Optional/<br>Required | Value |
|:---   |  :---:  |:---  |
| `id` | R | Compound FQN of implementation based on `com.intellij.openapi.options.Configurable` in the form: `XX.YY` where:<br>`XX` is the parent settings component FQN-based id.<br>`YY` is unique to the child among other siblings.  |




## Implementations for Settings Extension Points

### The Configurable Interface
[//]: # (todo: Review documentation in source file)

Recommend reviewing documentation in source file - very complete.

Constructors - signature differences

> **Warning** Do not take a lot of time...

Auto scroll and margin. (see marker interfaces)

#### Sequences
diagram with IntelliJ Platform for use-cases:
* User opens Settings/Preferences
* User selects myPrefs 
* User makes a change
* User presses reset
* User presses Cancel
* User presses Apply (clean or dirty)
* User presses Ok while dirty 
* User switches settings screens

#### Configurable Marker Interfaces
`Configurable.NoScroll`

`Configurable.NoMargin`

 
### The ConfigurableProvider Interface

### IntelliJ Platform Implementation Classes

[//]: # (todo: Resolve whether to discuss UnnamedConfigurable interface - I can find 33 examples in intellij-community)

#### SearchableConfiguration

## Example Settings Implementation Structure
Simplistic.
Many IntelliJ Platform implementations do everything in the Configurable implementation.
Separated here for clarity.

Class Diagram of Configurable, State, Panel
Structure diagram of Configurable, Panel, State

