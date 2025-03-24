<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Settings Tutorial

<link-summary>Implementing custom settings tutorial.</link-summary>

As discussed in the [](settings_guide.md), plugins can add Settings to IntelliJ Platform-based IDEs.
The IDE displays the Settings in response to a user choosing <ui-path>Settings</ui-path>.
Custom Settings are displayed and function like those native to the IDE.

## Overview of Custom Settings Implementation

Using the SDK code sample [`settings`](%gh-sdk-samples-master%/settings), this tutorial illustrates the steps to creating custom application-level settings.
Many IntelliJ Platform Settings implementations use fewer classes, but the `settings` code sample factors the functionality into three classes for clarity:
* The [`AppSettingsConfigurable`](%gh-sdk-samples-master%/settings/src/main/java/org/intellij/sdk/settings/AppSettingsConfigurable.java) is analogous to a Controller in the MVC model – it interacts with the other two Settings classes and the IntelliJ Platform,
* The [`AppSettings`](%gh-sdk-samples-master%/settings/src/main/java/org/intellij/sdk/settings/AppSettings.java) is like a Model because it stores the Settings persistently,
* The [`AppSettingsComponent`](%gh-sdk-samples-master%/settings/src/main/java/org/intellij/sdk/settings/AppSettingsComponent.java) is similar to a View because it displays and captures edits to the values of the Settings.

The structure of the implementation is the same for Project Settings, but there are minor differences in the [`Configurable` implementation](settings_guide.md#constructors) and [extension point (EP) declaration](settings_guide.md#declaring-project-settings).

> See
> [`MarkdownSettings`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettings.kt)
> and
> [`MarkdownSettingsConfigurable`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettingsConfigurable.kt)
> classes for the settings example implemented in Kotlin with usage of [Kotlin UI DSL](kotlin_ui_dsl_version_2.md).
>
{style="note"}

## The `AppSettings` Class

The `AppSettings` class persistently stores the custom Settings.
It is based on the [IntelliJ Platform Persistence Model](persisting_state_of_components.md#using-persistentstatecomponent).

### Declaring `AppSettings`

Given a [Light Service](plugin_services.md#light-services) is not used, the persistent data class must be declared as a [Service](plugin_services.md#declaring-a-service) EP in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
If these were Project Settings, the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectService"/></include> would be used.
However, because these are Application Settings, the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationService"/></include> is used with the fully qualified name (FQN) of the implementation class:

```xml
<extensions defaultExtensionNs="com.intellij">
  <applicationService
      serviceImplementation="org.intellij.sdk.settings.AppSettings"/>
</extensions>
```

### Creating the `AppSettings` Implementation

As discussed in [Implementing the PersistentStateComponent Interface](persisting_state_of_components.md#implementing-the-persistentstatecomponent-interface), `AppSettings` uses the pattern of implementing [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) parameterized by a separate state class:

```java
```
{src="settings/src/main/java/org/intellij/sdk/settings/AppSettings.java" include-symbol="AppSettings"}

#### `@Storage` Annotation

The [`@State`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/State.java) annotation, located above the class declaration, [defines the data storage location](persisting_state_of_components.md#defining-the-storage-location).
For `AppSettings`, the data `name` parameter is the FQN of the class.
Using FQN is the best practice to follow and is required if custom data gets stored in the standard project or workspace files.

The `storages` parameter uses the [`@Storage`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/Storage.java) annotation to define a custom filename for the `AppSettings` data.
In this case, the file is located in the `options` directory of the [configuration directory](https://www.jetbrains.com/help/idea/tuning-the-ide.html#config-directory) for the IDE.

#### Persistent State Class

The `AppSettings` implementation contains an inner state class with two public fields: a `String` and a `boolean`.
Conceptually, these fields hold the name of a user and whether that person is an IntelliJ IDEA user, respectively.
See [Implementing the State Class](persisting_state_of_components.md#implementing-the-state-class) for more information about how `PersistentStateComponent` serializes public fields.

#### `AppSettings` Methods

The fields are so limited and straightforward for this class that encapsulation is not used for simplicity.
All that is needed for functionality is to override the two methods called by the IntelliJ Platform when a new component state is loaded (`PersistentStateComponent.loadState()`), and when a state is saved (`PersistentStateComponent.getState()`).
See [`PersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) for more information about these methods.

One static convenience method has been added – `AppSettings.getInstance()` – which allows `AppSettingsConfigurable` to easily acquire a reference to `AppSetting`.

## The `AppSettingsComponent` Class

The role of the [`AppSettingsComponent`](%gh-sdk-samples-master%/settings/src/main/java/org/intellij/sdk/settings/AppSettingsComponent.java) is to provide a `JPanel` for the custom Settings to the IDE Settings Dialog.
The `AppSettingsComponent` has-a `JPanel`, and is responsible for its lifetime.
The `AppSettingsComponent` is instantiated by `AppSettingsConfigurable`.

### Creating the `AppSettingsComponent` Implementation

The `AppSettingsComponent` defines a `JPanel` containing a [`JBTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTextField.java) and a [`JBCheckBox`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBCheckBox.java) to hold and display the data that maps to the [data fields of `AppSettings.State`](#persistent-state-class):

```java
```
{src="settings/src/main/java/org/intellij/sdk/settings/AppSettingsComponent.java" include-symbol="AppSettingsComponent"}

#### `AppSettingsComponent` Methods

The constructor builds the `JPanel` using the convenient [`FormBuilder`](%gh-ic%/platform/platform-api/src/com/intellij/util/ui/FormBuilder.java) and saves a reference to the `JPanel`.
The rest of the class are simple accessors and mutators to encapsulate the UI components used on the `JPanel`.

## The `AppSettingsConfigurable` Class

The methods of [`AppSettingsConfigurable`](%gh-sdk-samples-master%/settings/src/main/java/org/intellij/sdk/settings/AppSettingsConfigurable.java) are called by the IntelliJ Platform, and `AppSettingsConfigurable` in turn interacts with `AppSettingsComponent` and `AppSettings`.

### Declaring the `AppSettingsConfigurable`

As described in [Declaring Application Settings](settings_guide.md#declaring-application-settings), the `com.intellij.applicationConfigurable` is used as the EP.
An explanation of this declaration can be found in [Declaring Application Settings](settings_guide.md#declaring-application-settings):

```xml
<extensions defaultExtensionNs="com.intellij">
  <applicationConfigurable
      parentId="tools"
      instance="org.intellij.sdk.settings.AppSettingsConfigurable"
      id="org.intellij.sdk.settings.AppSettingsConfigurable"
      displayName="SDK: Application Settings Example"/>
</extensions>
```

### Creating the `AppSettingsConfigurable` Implementation

The `AppSettingsConfigurable` class implements [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java).
The class has one field to hold a reference to the `AppSettingsComponent`.

```java
```
{src="settings/src/main/java/org/intellij/sdk/settings/AppSettingsConfigurable.java" include-symbol="AppSettingsConfigurable"}

#### `AppSettingsConfigurable` Methods

All the methods in this class are overrides of the methods in the `Configurable` interface.
Readers are encouraged to review the Javadoc comments for the `Configurable` methods.
Also, review notes about [IntelliJ Platform Interactions](settings_guide.md#intellij-platform-interactions-with-configurable) with `Configurable` methods.

## Testing the Custom Settings Plugin

After performing the steps described above, compile and run the plugin in a Development Instance to see the custom Settings available in the Settings Dialog.
Open the IDE Settings by selecting <ui-path>Settings | Tools | SDK: Application Settings Example</ui-path>.
The settings are preloaded with the default values:

!["Settings Defaults"](settings_defaults.png){width="700"}

Now edit the settings values to "John Doe" and click the checkbox.
Click the <control>OK</control> button to close the Settings dialog and save the changes.
Exit the Development Instance.

Open the file <path>code_samples/settings/build/idea-sandbox/config/options/SdkSettingsPlugin.xml</path> to see the Settings persistently stored.

> In this demonstration the file resides in <path>code_samples/settings/build/idea-sandbox/config/options/</path>.
> See [IDE Development Instances](ide_development_instance.md) for the general Development Instance case or [Default IDE directories](https://www.jetbrains.com/help/idea/tuning-the-ide.html#default-dirs) if the **settings** plugin is installed directly in the IDE.
