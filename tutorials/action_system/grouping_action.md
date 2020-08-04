---
title: Grouping Actions
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

If an implementation requires several actions, or there are simply too many actions that overload the menu, the actions can be placed into groups.
This tutorial demonstrates adding an action to an existing group, creating a new action group, and action groups with a variable number of actions.
The sample code discussed in this tutorial is from the code sample [`action_basics`](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/action_basics).

Some content in this tutorial assumes the reader is familiar with the tutorial for [Creating Actions](working_with_custom_actions.md).

* bullet list
{:toc}

## Simple Action Groups
In this first example, the action group will be available as a top-level menu item, and actions are represented as drop-down menu items.
The group is based on a default IntelliJ Platform implementation.

### Creating Simple Groups
Grouping can be registered by adding a `<group>` element to the `<actions>` section of a plugin's `plugin.xml` file.
This example has no `class` attribute in the `<group>` element because the IntelliJ Platform framework will supply a default implementation class for the group. 
This default implementation is used if a set of actions belonging to the group is static, i.e., does not change at runtime, which is the majority of cases.
The `id` attribute must be unique, so incorporating the plugin ID or package name is the best practice.

The `popup` attribute determines whether actions in the group are placed in a submenu.
The `icon` attribute specifies the FQN of an [`Icon`](/reference_guide/work_with_icons_and_images.md) object to be displayed.
No `compact` attribute is specified, which means this group will support submenus.
See [Registering Actions in plugin.xml](/basics/action_system.md#registering-actions-in-pluginxml) for more information about these attributes.

```xml
    <group id="org.intellij.sdk.action.GroupedActions" text="Static Grouped Actions" popup="true" icon="SdkIcons.Sdk_default_icon">
    </group>
```

### Binding Action Groups to UI Components
The following sample shows how to use an `<add-to-group>` element to place a custom action group relative to an entry in the **Tools** menu. 
The attribute `relative-to-action` references the action `id` for `PopupDialogAction`, which is not a native IntelliJ menu entry. 
Rather `PopupDialogAction` is defined in the same [`plugin.xml`](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/resources/META-INF/plugin.xml) file.
This group is placed after the single entry for the action `PopupDialogAction`, as defined in the tutorial [Creating Actions](working_with_custom_actions.md#registering-an-action-with-the-new-action-form).

```xml
    <group id="org.intellij.sdk.action.GroupedActions" text="Static Grouped Actions" popup="true" icon="SdkIcons.Sdk_default_icon">
      <add-to-group group-id="ToolsMenu" anchor="after" relative-to-action="org.intellij.sdk.action.PopupDialogAction"/>
    </group>
```

### Adding a New Action to the Static Grouped Actions
The `PopupDialogAction` implementation will be reused and registered in the newly created static group. 
The `id` attribute for the reused `PopupDialogAction` implementation is set to a unique value, `org.intellij.sdk.action.GroupPopDialogAction`.
This value differentiates this new `<action>` entry from the `id` previously used to register this action implementation in the [Creating Actions](working_with_custom_actions.md#registering-an-action-with-the-new-action-form) tutorial. 
A unique `id` supports reuse of action classes in more than one menu or group.
The action in this group will display the menu text "A Group Action".

```xml
    <group id="org.intellij.sdk.action.GroupedActions" text="Static Grouped Actions" popup="true" icon="SdkIcons.Sdk_default_icon">
      <add-to-group group-id="ToolsMenu" anchor="after" relative-to-action="org.intellij.sdk.action.PopupDialogAction"/>
      <action class="org.intellij.sdk.action.PopupDialogAction" id="org.intellij.sdk.action.GroupPopDialogAction"
              text="A Group Action" description="SDK static grouped action example" icon="SdkIcons.Sdk_default_icon">
      </action>
    </group>
```

After performing the steps described above the action group and its content will be available in the **Tools** menu.
The underlying `PopupDialogAction` implementation is reused for two entries in the **Tools** menu:
* Once for the top menu entry **Tools \| Pop Dialog Action** with the action `id` equal to `org.intellij.sdk.action.PopupDialogAction` as set in the [Creating Actions](/tutorials/action_system/working_with_custom_actions.md#registering-an-action-with-the-new-action-form) tutorial.
* A section time for the menu entry **Tools \| Static Grouped Actions \| A Group Action** with the action `id` equal to `org.intellij.sdk.action.GroupPopDialogAction`.

![Simple Action Group](img/grouped_action.png){:width="550px"}
    
  
## Implementing Custom Action Group Classes
In some cases, the specific behavior of a group of actions needs to depend on the context.
The solution is analagous to making a [single action entry dependent on context](working_with_custom_actions.md#extending-the-update-method). 

The steps below show how to make a group of actions available and visible if certain conditions are met.
In this case, the condition is having an instance of an editor is available. 
This condition is needed because the custom action group is added to an IntelliJ menu that is only enabled for editing.

### Extending DefaultActionGroup
The [`DefaultActionGroup`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/DefaultActionGroup.java) is an implementation of [`ActionGroup`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionGroup.java).
The `DefaultActionGroup` class is used to add child actions and separators between them to a group.
This class is used if a set of actions belonging to the group does not change at runtime, which is the majority of cases.

As an example, extend [`DefaultActionGroup`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/DefaultActionGroup.java) 
to create the `CustomDefaultActionGroup` class in the `action_basics` code sample:

```java
  public class CustomDefaultActionGroup extends DefaultActionGroup {
    @Override
    public void update(AnActionEvent event) {
      // Enable/disable depending on whether user is editing...
    }
  }
```

### Registering the Custom Action Group
As in the case with the static action group, the action `<group>` should be declared in the `<actions>` section of the `plugin.xml` file, for example, the [action_basics](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/resources/META-INF/plugin.xml) plugin. 
The declaration below shows:
* The presence of the `class` attribute in the `<group>` element, which tells the IntelliJ Platform framework to use `CustomDefaultActionGroup` rather than the default implementation.
* Setting the group's `popup` attribute to allow submenus. 
* The `<add-to-group>` element specifies adding the group in the first position of the existing `EditorPopupMenu`.

```xml
   <group id="org.jetbrains.tutorials.actions.ExampleCustomDefaultActionGroup" 
        class="org.jetbrains.tutorials.actions.CustomDefaultActionGroup" popup="true"
        text="Example Custom DefaultActionGroup" description="Custom DefaultActionGroup Demo">
      <add-to-group group-id="EditorPopupMenu" anchor="first"/>
    </group>
```

### Adding Actions to the Custom Group
As in [Static Grouped Actions](#adding-a-new-action-to-the-static-grouped-actions), the `PopupDialogAction` action is added as an `<action>` element in the `<group>` element. 
In the declaration below, note:
  * The `class` attribute in the `<action>` element has the same FQN to reuse this action implementation.
  * The `id` attribute is unique to distinguish it from other uses of the implementation in the Action System.
   
```xml
    <group id="org.intellij.sdk.action.CustomDefaultActionGroup" class="org.intellij.sdk.action.CustomDefaultActionGroup" popup="true"
            text="Popup Grouped Actions" description="Custom defaultActionGroup demo" icon="SdkIcons.Sdk_default_icon">
      <add-to-group group-id="EditorPopupMenu" anchor="first"/>
      <action class="org.intellij.sdk.action.PopupDialogAction" id="org.intellij.sdk.action.CustomGroupedAction"
              text="A Popup Action" description="SDK popup grouped action example" icon="SdkIcons.Sdk_default_icon"/>
    </group>
```

### Providing Specific Behavior for the Custom Group
Override the `CustomDefaultActionGroup.update()` method to make the group visible only if there's an instance of the editor available. 
Also, a custom icon is added to demonstrate that icons can be changed depending on the action context:

```java
public class CustomDefaultActionGroup extends DefaultActionGroup {
  @Override
  public void update(AnActionEvent event) {
    // Enable/disable depending on whether user is editing
    Editor editor = event.getData(CommonDataKeys.EDITOR);
    event.getPresentation().setEnabled(editor != null);
    // Take this opportunity to set an icon for the menu entry.
    event.getPresentation().setIcon(SdkIcons.Sdk_default_icon);
  }
}
```

After compiling and running the code sample above and opening a file in the editor and right-clicking, the **Editing** menu will pop up containing a new group of actions in the first position. 
The new group will also have an icon:

![Custom Action Group](img/editor_popup_menu.png){:width="600px"}
  

## Action Groups with Variable Actions Sets
If a set of actions belonging to a custom group varies depending on the context, the group must extend [`ActionGroup`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionGroup.java).
The set of actions in the `ActionGroup` is dynamically defined.

### Creating Variable Action Group
To create a group of actions with a variable number of actions, extend `ActionGroup`.
For example, as in the `action_basics` class [`DynamicActionGroup`](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/java/org/intellij/sdk/action/DynamicActionGroup.java) code:

```java
public class DynamicActionGroup extends ActionGroup {
}
```

### Registering a Variable Action Group
To register the dynamic menu group, a `<group>` attribute needs to be placed in the `<actions>` section of [`plugin`.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/resources/META-INF/plugin.xml).
When enabled, this group appears at the entry just below the [Static Grouped Actions](#binding-action-groups-to-ui-components) in the **Tools** menu:

```xml
    <group id="org.intellij.sdk.action.DynamicActionGroup" class="org.intellij.sdk.action.DynamicActionGroup" popup="true"
            text="Dynamically Grouped Actions" description="SDK dynamically grouped action example" icon="SdkIcons.Sdk_default_icon">
      <add-to-group group-id="ToolsMenu" anchor="after" relative-to-action="org.intellij.sdk.action.GroupedActions"/>
    </group>
```

> **WARNING** If a`<group>` element's `class` attribute names a class derived from `ActionGroup`, then any static `<action>` declarations in that group throw an exception. 
For a statically defined group, use `DefaultActionGroup`.

### Adding Child Actions to the Dynamic Group
To add actions to the `DynamicActionGroup`, a non-empty array of `AnAction` instances should be returned from the `DynamicActionGroup.getChildren()` method. 
Here again, reuse the `PopupDialogAction` implementation.
This use case is why `PopupDialogAction` overrides a constructor:

```java
public class DynamicActionGroup extends ActionGroup {
  @NotNull
  @Override
  public AnAction[] getChildren(AnActionEvent e) {
    return new AnAction[]{ new PopupDialogAction("Action Added at Runtime",
                                                 "Dynamic Action Demo",
                                                 SdkIcons.Sdk_default_icon) };
  }
}
```

After providing the implementation of `DynamicActionGroup` and making it return a non-empty array of actions, the third position in the **Tools** Menu will contain a new group of actions:

![Dynamic Action Group](img/dynamic_action_group.png){:width="600px"}
