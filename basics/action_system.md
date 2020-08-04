---
title: Action System
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction
The actions system is an extension point that allows plugins to add their items to IntelliJ Platform-based IDE menus and toolbars. 
For example, one of the action classes is responsible for the **File \| Open File...** menu item and for the **Open File** toolbar button.

Actions in the IntelliJ Platform require a [code implementation](#action-implementation) and must be [registered](#registering-actions) with the IntelliJ Platform. 
The action implementation determines the contexts in which an action is available, and its functionality when selected in the UI. 
Registration determines where an action appears in the IDE UI.
Once implemented and registered, an action receives callbacks from the IntelliJ Platform in response to user gestures.

The [Creating Actions](/tutorials/action_system/working_with_custom_actions.md) tutorial describes the process of adding a custom action to a plugin.
The [Grouping Actions](/tutorials/action_system/grouping_action.md) tutorial demonstrates three types of groups that can contain actions.
The rest of this page is an overview of actions as an extension point.

* bullet list
{:toc}

## Action Implementation
An action is a class derived from the abstract class [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java).
The IntelliJ Platform calls methods of an action when a user interacts with a menu item or toolbar button. 

> **WARNING** Classes based on `AnAction` do not have class fields of any kind. This is because an instance of `AnAction` class
exists for the entire lifetime of the application. If `AnAction` class uses a field to store data that has a shorter 
lifetime and doesn't clear this data promptly, the data leaks. For example, any `AnAction` data that exists 
only within the context of a `Project` causes the `Project` to be kept in memory after the user has closed it.

### Principal Implementation Overrides 
Every IntelliJ Platform action should override `AnAction.update()` and must override `AnAction.actionPerformed()`.
* An action's method `AnAction.update()` is called by the IntelliJ Platform framework to update the state of an action.
  The state (enabled, visible) of an action determines whether the action is available in the UI of an IDE.
  An object of type [`AnActionEvent`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) is passed to this method, and it contains the information about the current context for the action.
  Actions are made available by changing state in the [Presentation](upsource:///platform/platform-api/src/com/intellij/ide/presentation/Presentation.java) object associated with the event context.
  As explained in [Overriding the `AnAction.update()`  Method](#overriding-the-anactionupdate-method), it is vital `update()` methods _execute quickly_ and return execution to the IntelliJ Platform. 
* An action's method `AnAction.actionPerformed()` is called by the IntelliJ Platform if it is available and selected by the user.
  This method does the heavy lifting for the action - it contains the code executed when the action gets invoked.
  The `actionPerformed()` method also receives `AnActionEvent` as a parameter, which is used to access projects, files, selection, etc. 
  See [Overriding the `AnAction.actionPerformed()` Method](#overriding-the-anactionactionperformed-method) for more information.
  
There are other methods to override in the `AnAction` class, such as for changing the default `Presentation` object for the action.
There is also a use case for overriding action constructors when registering them with dynamic action groups, which is demonstrated in the [Grouping Actions](/tutorials/action_system/grouping_action.md#adding-child-actions-to-the-dynamic-group) tutorial. 
However, the `update()` and `actionPerformed()` methods are essential to basic operation.

### Overriding the AnAction.update Method
The method `AnAction.update()` is periodically called by the IntelliJ Platform in response to user gestures.
The `update()` method gives an action to evaluate the current context and enable or disable its functionality.

> **WARNING** The `AnAction.update()` method can be called frequently, and on a UI thread.
This method needs to _execute very quickly_; no real work should be performed in this method.
For example, checking selection in a tree or a list is considered valid, but working with the file system is not.

> **TIP** If the new state of an action cannot be determined quickly, then evaluation should be performed in the `AnAction.actionPerformed()` method, and notify the user that the action cannot be executed if the context isn't suitable. 

#### Determining the Action Context
The `AnActionEvent` object passed to `update()` carries information about the current context for the action.
Context information is available from the methods of `AnActionEvent`, providing information such as the Presentation, and whether the action is triggered from a Toolbar.
Additional context information is available using the method `AnActionEvent.getData()`. 
Keys defined in [`CommonDataKeys`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java) are passed to the `getData()` method to retrieve objects such as `Project`, `Editor`, `PsiFile`, and other information.
Accessing this information is relatively light-weight and is suited for `AnAction.update()`.

#### Enabling and Setting Visibility for an Action
Based on information about the action context, the `AnAction.update()` method can enable, disable, or hide an action.
An action's enable/disable state and visibility are set using methods of the `Presentation` object, which is accessed using `AnActionEvent.getPresentation()`.

The default `Presentation` object is a set of descriptive information about a menu or toolbar action.
Every context for an action - it might appear in multiple menus, toolbars, or Navigation search locations - has a unique presentation. 
Attributes such as an action's text, description, and icons, as well as visibility and enable/disable state, are stored in the presentation.
The attributes in a presentation get initialized from the [action registration](#registering-actions).
However, some can be changed at runtime using the methods of the `Presentation` object associated with an action.

The enabled/disabled state of an action is set using `Presentation.setEnabled()`.
The visibility state of an action is set using `Presentation.setVisible()`
If an action is enabled, the `AnAction.actionPerformed()` can be called if an action is selected in the IDE by a user.
A menu action shows in the UI location specified in the registration.
A toolbar action displays its enabled (or selected) icon, depending on the user interaction.

When an action is disabled `AnAction.actionPerformed()` will not be called.
Toolbar actions display their respective icons for the disabled state.
The visibility of a disabled action in a menu depends on whether the host menu (e.g. "ToolsMenu") containing the action has the `compact` attribute set.
See [Grouping Actions](#grouping-actions) for more information about the `compact` attribute, and the visibility of menu actions.

> **NOTE** If an action is added to a toolbar, its `update()` can be called if there was any user activity or focus transfer. 
If the action's availability changes in the absence of these events, then call [`ActivityTracker.getInstance().inc()`](upsource:///platform/platform-api/src/com/intellij/ide/ActivityTracker.java) to notify the action subsystem to update all toolbar actions. 

An example of enabling a menu action based on whether a project is open is demonstrated in [`PopupDialogAction.update()`](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/java/org/intellij/sdk/action/PopupDialogAction.java) method. 

### Overriding the AnAction.actionPerformed Method
When the user selects an enabled action, be it from a menu or toolbar, the action's `AnAction.actionPerformed()` method is called. 
This method contains the code executed to perform the action, and it is here that the real work gets done.

Using the `AnActionEvent` methods and `CommonDataKeys`, objects such as the `Project`, `Editor`, `PsiFile`, and other information is available.
For example, the `actionPerformed()` method can modify, remove, or add PSI elements to a file open in the editor.

The code that executes in the `AnAction.actionPerformed()` method should execute efficiently, but it does not have to meet the same stringent requirements as the `update()` method.

<!-- TODO: does this all happen inside a transaction? Does that ensure the undo step? -->

An example of inspecting PSI elements is demonstrated in the SDK code sample `action_basics` [`PopupDialogAction.actionPerformed()`](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/action_basics/src/main/java/org/intellij/sdk/action/PopupDialogAction.java) method. 

### Action IDs
Every action and action group has a unique identifier.
Basing the identifier for a custom action on the FQN of the implementation is the best practice, assuming the package incorporates the `<id>` of the plugin.
An action must have a unique identifier for each place it is used in the IDE UI, even though the FQN of the implementation is the same.
Definitions of identifiers for the standard IntelliJ Platform actions are in [`IdeActions`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/IdeActions.java).

### Grouping Actions
Groups organize actions into logical UI structures, which in turn can contain other groups. 
A group of actions can form a toolbar or a menu.
Subgroups of a group can form submenus of a menu.

Actions can be included in multiple groups, and thus appear in multiple places within the IDE UI. 
An action must have a unique identifier for each place it appears in the IDE UI.
See the [Action Declaration Reference](#action-declaration-reference) section for information about how to specify locations in the IDE UI.

<!-- TODO: Reconcile ActionPlaces vs. PlatformActions -->

#### Presentation
For every place where the action appears, a new [`Presentation`](upsource:///platform/platform-api/src/com/intellij/ide/presentation/Presentation.java) is created. 
Therefore the same action can have different text or icons when it appears in different places of the user interface. 
Different presentations for the action are created by copying the Presentation returned by the `AnAction.getTemplatePresentation()` method.

#### Compact Attribute
A group's "compact" attribute specifies whether an action within that group is visible when disabled.
See [Registering Actions in plugin.xml](#registering-actions-in-pluginxml) for an explanation of how the `compact` attribute is set for a group.
If the `compact` attribute is `true` for a menu group, an action in the menu only appears if its state is both enabled and visible.
In contrast, if the `compact` attribute is `false`, an action in the menu appears if its state is disabled but visible.
Some menus like **Tools** have the `compact` attribute set, so there isn't a way to show an action on the tools menu if it is not enabled.

| Host Menu<br>`compact` Setting | Action Enabled | Visibility Enabled | Menu Item Visible? | Menu Item Appears Gray? |
| :-----: | :------------: | :----------------: | :----------------: | :---------------------: |
|    T    |     **F**      |         T          |      **F**         |         N/A             |
|    T    |       T        |         T          |        T           |         F               |
|    F    |     **F**      |         T          |      **T**         |       **T**             |
|    F    |       T        |         T          |        T           |         F               |

All other combinations of `compact`, visibility, and enablement produce N/A for gray appearance because the menu item isn't visible.

See the [Grouping Actions](/tutorials/action_system/grouping_action.md) tutorial for examples of creating action groups.

## Registering Actions
There are two main ways to register an action: either by listing it in the `<actions>` section of a plugin's `plugin.xml` file, or through code.

### Registering Actions in plugin.xml
Registering actions in `plugin.xml` is demonstrated in the following reference examples, which document all elements and attributes that can be used in the `<actions>` section, and describes the meaning of each element.

#### Setting the Override-Text Element for an Action

> **TIP** Beginning in 2020.1, an alternate version of an action's menu text can be declared for use depending on where an action appears.

By using the `<override-text>` element introduced in 2020.1 of the IntelliJ Platform, the menu text for an action can be different depending on context: menu location, toolbar, etc.

In the `action` element reference example (below) with `id` attribute `VssIntegration.GarbageCollection`, the default is to use the menu text "Garbage Collector: Collect _Garbage."
The `add-to-group` element declares the action is added to the Tools Menu.

However, the `override-text` element declares that text for `VssIntegration.GarbageCollection` displayed anywhere in the Main Menu system should be the alternate text "Collect _Garbage."
The Tools Menu is part of the Main Menu, so the displayed menu text is "Collect _Garbage."
A different context, such as searching for the action using **Help \| Find Action...**,  displays the default text "Garbage Collector: Collect _Garbage" to give the user additional information about the action.

A second `override-text` element uses `place` and `use-text-of-place` attributes to declare the same version of the text used in the Main Menu is also used in the Editor Popup Menu.
Additional `override-text` elements could be used to specify additional places where the Main Menu text should be used. 

#### Action Declaration Reference
The places where actions can appear are defined by constants in [`ActionPlaces`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/ActionPlaces.java). 
Group IDs for the IntelliJ Platform are defined in [`PlatformActions.xml`](upsource:///platform/platform-resources/src/idea/PlatformActions.xml). 

This, and additional information can also be found by using the [Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#invoke-basic-completion), [Quick Definition](https://www.jetbrains.com/help/idea/viewing-reference-information.html#view-definition-symbols) and [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation) features.

> **TIP** To lookup existing Action ID (e.g. for use in `relative-to-action`), [UI Inspector](/reference_guide/internal_actions/internal_ui_inspector.md) can be used.

```xml
<!-- Actions -->
<actions>
  <!-- The <action> element defines an action to register.
       The mandatory "id" attribute specifies a unique 
       identifier for the action.
       The mandatory "class" attribute specifies the
       FQN of the class implementing the action.
       The mandatory "text" attribute specifies the default long-version text to be displayed for the
       action (tooltip for toolbar button or text for menu item).
       The optional "use-shortcut-of" attribute specifies the ID
       of the action whose keyboard shortcut this action will use.
       The optional "description" attribute specifies the text
       which is displayed in the status bar when the action is focused.
       The optional "icon" attribute specifies the icon which is
       displayed on the toolbar button or next to the menu item. -->
  <action id="VssIntegration.GarbageCollection" class="com.foo.impl.CollectGarbage" text="Garbage Collector: Collect _Garbage" 
                description="Run garbage collector" icon="icons/garbage.png">
    <!-- The <override-text> element defines an alternate version of the text for the menu action.
         The mandatory "text" attribute defines the text to be displayed for the action.
         The mandatory "place" attribute declares where the alternate text should be used. In this example,
         any time the action is displayed in the IDE Main Menu (and submenus) the override-text
         version should be used.  
         The second <override-text> element uses the alternate attribute "use-text-of-place" to define
         a location (EditorPopup) to use the same text as is used in MainMenu. It is a way to specify 
         use of alternate menu text in multiple discrete menu groups. -->
    <override-text place="MainMenu" text="Collect _Garbage"/>
    <override-text place="EditorPopup" use-text-of-place="MainMenu"/>
    <!-- The <add-to-group> node specifies that the action should be added
         to an existing group. An action can be added to several groups.
         The mandatory "group-id" attribute specifies the ID of the group
         to which the action is added.
         The group must be implemented by an instance of the DefaultActionGroup class.
         The mandatory "anchor" attribute specifies the position of the
         action in the group relative to other actions. It can have the values
         "first", "last", "before" and "after".
         The "relative-to-action" attribute is mandatory if the anchor
         is set to "before" and "after", and specifies the action before or after which
         the current action is inserted. -->
    <add-to-group group-id="ToolsMenu" relative-to-action="GenerateJavadoc" anchor="after"/>
      <!-- The <keyboard-shortcut> node specifies the keyboard shortcut
           for the action. An action can have several keyboard shortcuts.
           The mandatory "first-keystroke" attribute specifies the first
           keystroke of the action. The keystrokes are specified according
           to the regular Swing rules.
           The optional "second-keystroke" attribute specifies the second
           keystroke of the action.
           The mandatory "keymap" attribute specifies the keymap for which
           the action is active. IDs of the standard keymaps are defined as
           constants in the com.intellij.openapi.keymap.KeymapManager class. 
           The optional "remove" attribute in the second <keyboard-shortcut>
           element below means the specified shortcut should be removed from 
           the specified action.
           The optional "replace-all" attribute in the third <keyboard-shortcut>
           element below means remove all keyboard and mouse shortcuts from the specified 
           action before adding the specified shortcut.  -->
    <!-- Add the first and second keystrokes to all keymaps  -->
    <keyboard-shortcut keymap="$default" first-keystroke="control alt G" second-keystroke="C"/>
    <!-- Except to the "Mac OS X" keymap and its children -->
    <keyboard-shortcut keymap="Mac OS X" first-keystroke="control alt G" second-keystroke="C" remove="true"/>
    <!-- The "Mac OS X 10.5+" keymap and its children will have only this keyboard shortcut for this action.  -->
    <keyboard-shortcut keymap="Mac OS X 10.5+" first-keystroke="control alt G" second-keystroke="C" replace-all="true"/>
    <!-- The <mouse-shortcut> node specifies the mouse shortcut for the
           action. An action can have several mouse shortcuts.
           The mandatory "keystroke" attribute specifies the clicks and
           modifiers for the action. It is defined as a sequence of words
           separated by spaces: 
           "button1", "button2", "button3" for the mouse buttons;
           "shift", "control", "meta", "alt", "altGraph" for the modifier keys;
           "doubleClick" if the action is activated by a double-click of the button.
           The mandatory "keymap" attribute specifies the keymap for which
           the action is active. IDs of the standard keymaps are defined as
           constants in the com.intellij.openapi.keymap.KeymapManager class.
           The "remove" and "replace-all" attributes can also be used in
           a <mouse-shortcut> element. See <keyboard-shortcut> for documentation.  -->
    <mouse-shortcut keymap="$default" keystroke="control button3 doubleClick"/>
  </action>
  <!-- The <group> element defines an action group. <action>, <group> and 
       <separator> elements defined within it are automatically included in the group.
       The mandatory "id" attribute specifies a unique identifier for the action.
       The optional "class" attribute specifies the FQN of
       the class implementing the group. If not specified,
       com.intellij.openapi.actionSystem.DefaultActionGroup is used.
       The optional "text" attribute specifies the text of the group (text
       for the menu item showing the submenu).
       The optional "description" attribute specifies the text which is displayed
       in the status bar when the group has focus.
       The optional "icon" attribute specifies the icon which is displayed on
       the toolbar button or next to the menu group.
       The optional "popup" attribute specifies how the group is presented in
       the menu. If a group has popup="true", actions in it are placed in a
       submenu; for popup="false", actions are displayed as a section of the
       same menu delimited by separators. 
       The optional "compact" attribute specifies whether an action within that group is visible when disabled.
       Setting compact="true" specifies an action in the group isn't visible unless the action is enabled.        -->
  <group class="com.foo.impl.MyActionGroup" id="TestActionGroup" text="Test Group" description="Group with test actions" icon="icons/testgroup.png" popup="true" compact="true">
    <action id="VssIntegration.TestAction" class="com.foo.impl.TestAction" text="My Test Action" description="My test action"/>
    <!-- The <separator> element defines a separator between actions.
         It can also have an <add-to-group> child element. -->
    <separator/>
    <group id="TestActionSubGroup"/>
    <!-- The <reference> element allows to add an existing action to the group.
         The mandatory "ref" attribute specifies the ID of the action to add. -->
    <reference ref="EditorCopy"/>
    <add-to-group group-id="MainMenu" relative-to-action="HelpMenu" anchor="before"/>
  </group>
</actions>
```

### Registering Actions from Code
Two steps are required to register an action from code:
* First, an instance of the class derived from `AnAction` must be passed to the `registerAction()` method of [`ActionManager`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionManager.java), to associate the action with an ID.   
* Second, the action needs to be added to one or more groups. 
  To get an instance of an action group by ID, it is necessary to call `ActionManager.getAction()` and cast the returned value to [`DefaultActionGroup`](upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/DefaultActionGroup.java).

## Building UI from Actions
If a plugin needs to include a toolbar or popup menu built from a group of actions in its user interface, that is accomplished through [`ActionPopupMenu`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionPopupMenu.java) and [`ActionToolbar`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ActionToolbar.java). 
These objects can be created through calls to the `ActionManager.createActionPopupMenu()` and `createActionToolbar()` methods. 
To get a Swing component from such an object, call the respective `getComponent()` method.

If an action toolbar is attached to a specific component (for example, a panel in a tool window), call `ActionToolbar.setTargetComponent()` and pass the instance of the related component as a parameter. 
Setting the target ensures that the state of the toolbar buttons depends on the state of the related component, and not on the current focus location within the IDE frame.
