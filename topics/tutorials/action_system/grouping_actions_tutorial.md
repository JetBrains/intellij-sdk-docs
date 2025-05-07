<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Grouping Actions

<link-summary>Organizing actions in custom groups and attaching them to the existing ones.</link-summary>

If an implementation requires several actions, or there are simply too many actions that overload the menu, the actions can be placed into groups.
This tutorial demonstrates adding an action to an existing group, creating a new action group, and action groups with a variable number of actions.
The sample code discussed in this tutorial is from the code sample [`action_basics`](%gh-sdk-samples-master%/action_basics).

Some content in this tutorial assumes the reader is familiar with the [](creating_actions_tutorial.md) tutorial.

## Simple Action Groups

In this first example, the action group will be available as a top-level menu item, and actions are represented as drop-down menu items.
The group is based on a default IntelliJ Platform implementation.

### Creating Simple Groups

Grouping can be registered by adding a [`<group>`](plugin_configuration_file.md#idea-plugin__actions__group) element to the [`<actions>`](plugin_configuration_file.md#idea-plugin__actions) section of a plugin's <path>[plugin.xml](plugin_configuration_file.md)</path> file.
This example has no `class` attribute in the `<group>` element because the IntelliJ Platform framework will supply a default implementation class for the group.
This default implementation is used if a set of actions belonging to the group is static, i.e., does not change at runtime, which is the majority of cases.
The `id` attribute must be unique, so incorporating the plugin ID or package name is the best practice.

The `popup` attribute determines whether actions in the group are placed in a submenu.
The `icon` attribute specifies the FQN of an [`Icon`](icons.md) object to be displayed.
No `compact` attribute is specified, which means this group will support submenus.
See [](action_system.md#registering-actions-in-pluginxml) for more information about these attributes.

```xml
<group
    id="org.intellij.sdk.action.GroupedActions"
    text="Static Grouped Actions"
    popup="true"
    icon="SdkIcons.Sdk_default_icon"/>
```

### Binding Action Groups to UI Components

The following sample shows how to use an [`<add-to-group>`](plugin_configuration_file.md#idea-plugin__actions__action__add-to-group) element to place a custom action group relative to an entry in the <ui-path>Tools</ui-path> menu.
The attribute `relative-to-action` references the action `id` for `PopupDialogAction`, not a native IntelliJ menu entry.
Rather `PopupDialogAction` is defined in the same [`plugin.xml`](%gh-sdk-samples-master%/action_basics/src/main/resources/META-INF/plugin.xml) file.
This group is placed after the single entry for the action `PopupDialogAction`, as defined in the [Creating Actions](creating_actions_tutorial.md#registering-an-action-with-the-new-action-form) tutorial.

```xml
<group
    id="org.intellij.sdk.action.GroupedActions"
    text="Static Grouped Actions"
    popup="true"
    icon="SdkIcons.Sdk_default_icon">
  <add-to-group
      group-id="ToolsMenu"
      anchor="after"
      relative-to-action="org.intellij.sdk.action.PopupDialogAction"/>
</group>
```

### Adding a New Action to the Static Grouped Actions

The `PopupDialogAction` implementation will be reused and registered in the newly created static group.
The `id` attribute for the reused `PopupDialogAction` implementation is set to a unique value, `org.intellij.sdk.action.GroupPopDialogAction`.
This value differentiates this new [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) entry from the `id` previously used to register this action implementation in the [Creating Actions](creating_actions_tutorial.md#registering-an-action-with-the-new-action-form) tutorial.
A unique `id` supports reuse of action classes in more than one menu or group.
The action in this group will be displayed in the menu as "A Group Action".

```xml
<group
    id="org.intellij.sdk.action.GroupedActions"
    text="Static Grouped Actions"
    popup="true"
    icon="SdkIcons.Sdk_default_icon">
  <add-to-group
      group-id="ToolsMenu"
      anchor="after"
      relative-to-action="org.intellij.sdk.action.PopupDialogAction"/>
  <action
      class="org.intellij.sdk.action.PopupDialogAction"
      id="org.intellij.sdk.action.GroupPopDialogAction"
      text="A Group Action"
      description="SDK static grouped action example"
      icon="SdkIcons.Sdk_default_icon">
  </action>
</group>
```

After performing the steps described above, the action group and its content will be available in the <ui-path>Tools</ui-path> menu.
The underlying `PopupDialogAction` implementation is reused for two entries in the <ui-path>Tools</ui-path> menu:
* Once for the top menu entry <ui-path>Tools | Pop Dialog Action</ui-path> with the action `id` equal to `org.intellij.sdk.action.PopupDialogAction` as set in the [Creating Actions](creating_actions_tutorial.md#registering-an-action-with-the-new-action-form) tutorial.
* A second time for the menu entry <ui-path>Tools | Static Grouped Actions | A Group Action</ui-path> with the action `id` equal to `org.intellij.sdk.action.GroupPopDialogAction`.

![Simple Action Group](grouped_action.png){width="550"}

## Implementing Custom Action Group Classes

In some cases, the specific behavior of an action group needs to depend on the context.
The solution is analogous to making a [single action entry dependent on context](creating_actions_tutorial.md#extending-the-update-method).

The steps below show how to make a group of actions available and visible if certain conditions are met.
In this case, the condition is having an instance of available editor.
This condition is needed because the custom action group is added to an IntelliJ menu that is only enabled for editing.

### Extending `DefaultActionGroup`

The [`DefaultActionGroup`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/DefaultActionGroup.java) is an implementation of [`ActionGroup`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionGroup.java).
The `DefaultActionGroup` class is used to add child actions and separators between them to a group.
This class is used if a set of actions belonging to the group does not change at runtime.

As an example, extend `DefaultActionGroup` to create the `CustomDefaultActionGroup` class in the `action_basics` code sample:

```java
public class CustomDefaultActionGroup extends DefaultActionGroup {
  @Override
  public void update(AnActionEvent event) {
    // Enable/disable depending on whether a user is editing...
  }
}
```

### Registering the Custom Action Group

As in the case with the static action group, the action [`<group>`](plugin_configuration_file.md#idea-plugin__actions__group) should be declared in the [`<actions>`](plugin_configuration_file.md#idea-plugin__actions) section of the <path>plugin.xml</path> file, for example, the [action_basics](%gh-sdk-samples-master%/action_basics/src/main/resources/META-INF/plugin.xml) plugin.
For demonstration purposes, this implementation will use localization.

The `<group>` element declaration below shows:
* An optional resource bundle declaration outside the `<actions>` section for localizing actions.
* The presence of the `class` attribute in the `<group>` element tells the IntelliJ Platform framework to use `CustomDefaultActionGroup` rather than the default implementation.
* Setting the group's `popup` attribute to allow submenus.
* The `text` and `description` attributes are omitted in the `<group>` declaration in favor of using the localization resource bundle to define them.
* There is no `icon` attribute for the group; the `CustomDefaultActionGroup` implementation will [add an icon for the group](#providing-specific-behavior-for-the-custom-group).
* The [`<add-to-group>`](plugin_configuration_file.md#idea-plugin__actions__action__add-to-group) element specifies adding the group in the first position of the existing `EditorPopupMenu`.

```xml
<resource-bundle>messages.BasicActionsBundle</resource-bundle>

<actions>
  <group
      id="org.intellij.sdk.action.CustomDefaultActionGroup"
      class="org.intellij.sdk.action.CustomDefaultActionGroup"
      popup="true">
    <add-to-group group-id="EditorPopupMenu" anchor="first"/>
  </group>
</actions>
```

### Adding Actions to the Custom Group

As in [Static Grouped Actions](#adding-a-new-action-to-the-static-grouped-actions), the `PopupDialogAction` action is added as an [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) element in the [`<group>`](plugin_configuration_file.md#idea-plugin__actions__group) element.
In the `<action>` element declaration below:
* The `class` attribute in the `<action>` element has the same FQN to reuse this action implementation.
* The `id` attribute is unique to distinguish it from other uses of the implementation in the Action System.
* The `text` and `description` attributes are omitted in the `<action>` declaration; they are instead defined using the localization resource bundle.
* The SDK icon is declared for use with this action.

```xml
<group
    id="org.intellij.sdk.action.CustomDefaultActionGroup"
    class="org.intellij.sdk.action.CustomDefaultActionGroup"
    popup="true"
    icon="SdkIcons.Sdk_default_icon">
  <add-to-group group-id="EditorPopupMenu" anchor="first"/>
  <action
      id="org.intellij.sdk.action.CustomGroupedAction"
      class="org.intellij.sdk.action.PopupDialogAction"
      icon="SdkIcons.Sdk_default_icon"/>
</group>
```

Now the translations for the `text` and `description` attributes must be provided in the resource bundle [`BasicActionsBundle.properties`](%gh-sdk-samples-master%/action_basics/src/main/resources/messages/BasicActionsBundle.properties) file according to [](action_system.md#localizing-actions-and-groups).
Note there are two sets of `text` and `description` translations, one for the action and one for the group.
Conceivably, there could be another set of translations for the action if it used the [`<override-text>`](plugin_configuration_file.md#idea-plugin__actions__action__override-text) attribute.

```
action.org.intellij.sdk.action.CustomGroupedAction.text=A Popup Action [EN]
action.org.intellij.sdk.action.CustomGroupedAction.description=SDK popup grouped action example [EN]
group.org.intellij.sdk.action.CustomDefaultActionGroup.text=Popup Grouped Actions [EN]
group.org.intellij.sdk.action.CustomDefaultActionGroup.description=Custom defaultActionGroup demo [EN]
```

### Providing Specific Behavior for the Custom Group

Override the `CustomDefaultActionGroup.update()` method to make the group visible only if there's an instance of the editor available.
Also, a custom icon is added to demonstrate that group icons can be changed depending on the action context:

```java
public class CustomDefaultActionGroup extends DefaultActionGroup {
  @Override
  public void update(AnActionEvent event) {
    // Enable/disable depending on whether a user is editing
    Editor editor = event.getData(CommonDataKeys.EDITOR);
    event.getPresentation().setEnabled(editor != null);
    // Take this opportunity to set an icon for the group.
    event.getPresentation().setIcon(SdkIcons.Sdk_default_icon);
  }
}
```

After compiling and running the code sample above and opening a file in the editor and right-clicking, the Editor context menu will pop up containing a new group of actions in the first position.
Note that the group and actions come from the resource file as all contain the suffix " [EN]".
The new group will also have an icon:

![Custom Action Group](editor_popup_menu.png)

## Action Groups with Dynamic Actions Sets

If a set of actions belonging to a custom group varies depending on the context, the group must extend [`ActionGroup`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionGroup.java).
The set of actions in the `ActionGroup` is dynamically defined.

### Creating Variable Action Group

To create a group of actions with a variable number of actions, extend `ActionGroup`.
For example, as in the `action_basics` class [`DynamicActionGroup`](%gh-sdk-samples-master%/action_basics/src/main/java/org/intellij/sdk/action/DynamicActionGroup.java) code:

```java
public class DynamicActionGroup extends ActionGroup {
}
```

### Registering a Variable Action Group

To register the dynamic menu group, a [`<group>`](plugin_configuration_file.md#idea-plugin__actions__group) attribute needs to be placed in the [`<actions>`](plugin_configuration_file.md#idea-plugin__actions) section of [`plugin`.xml](%gh-sdk-samples-master%/action_basics/src/main/resources/META-INF/plugin.xml).
When enabled, this group appears just below the [Static Grouped Actions](#binding-action-groups-to-ui-components) in the <ui-path>Tools</ui-path> menu:

```xml
<group
    id="org.intellij.sdk.action.DynamicActionGroup"
    class="org.intellij.sdk.action.DynamicActionGroup"
    popup="true"
    text="Dynamically Grouped Actions"
    description="SDK dynamically grouped action example"
    icon="SdkIcons.Sdk_default_icon">
  <add-to-group
      group-id="ToolsMenu"
      anchor="after"
      relative-to-action="org.intellij.sdk.action.GroupedActions"/>
</group>
```

> If a`<group>` element's `class` attribute names a class derived from `ActionGroup`, then any static `<action>` declarations in that group throw an exception.
> For a statically defined group, use `DefaultActionGroup`.
>
{style="warning"}

### Adding Child Actions to the Dynamic Group

To add actions to the `DynamicActionGroup`, a non-empty array of `AnAction` instances should be returned from the `DynamicActionGroup.getChildren()` method.
Here again, reuse the `PopupDialogAction` implementation.
This use case is why `PopupDialogAction` overrides a constructor:

```java
public class DynamicActionGroup extends ActionGroup {
  @NotNull
  @Override
  public AnAction[] getChildren(AnActionEvent event) {
    return new AnAction[]{
        new PopupDialogAction(
            "Action Added at Runtime",
            "Dynamic Action Demo",
            SdkIcons.Sdk_default_icon)
    };
  }
}
```

After providing the implementation of `DynamicActionGroup` and making it return a non-empty array of actions, the third position in the <ui-path>Tools</ui-path> menu will contain a new group of actions:

![Dynamic Action Group](dynamic_action_group.png){width="600"}
