[//]: # (title: Actions)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction

The actions system is an extension point that allows plugins to add their items to IntelliJ Platform-based IDE menus and toolbars.
For example, one of the action classes is responsible for the <menupath>File | Open File...</menupath> menu item and the <control>Open File</control> toolbar button.

Actions in the IntelliJ Platform require a [code implementation](#action-implementation) and must be [registered](#registering-actions).
The action implementation determines the contexts in which an action is available, and its functionality when selected in the UI.
Registration determines where an action appears in the IDE UI.
Once implemented and registered, an action receives callbacks from the IntelliJ Platform in response to user gestures.

The [Creating Actions](working_with_custom_actions.md) tutorial describes the process of adding a custom action to a plugin.
The [Grouping Actions](grouping_action.md) tutorial demonstrates three types of groups that can contain actions.
The rest of this page is an overview of actions as an extension point.

## Action Implementation

An action is a class derived from the abstract class [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java).
The IntelliJ Platform calls methods of action when a user interacts with a menu item or toolbar button.

> Classes based on `AnAction` do not have class fields of any kind.
> This is because an instance of `AnAction` class exists for the entire lifetime of the application.
> If the `AnAction` class uses a field to store data that has a shorter lifetime and doesn't clear this data promptly, the data leaks.
> For example, any `AnAction` data that exists only within the context of a `Project` causes the `Project` to be kept in memory after the user has closed it.
>
{type="warning"}

### Principal Implementation Overrides

Every IntelliJ Platform action should override `AnAction.update()` and must override `AnAction.actionPerformed()`.
* An action's method `AnAction.update()` is called by the IntelliJ Platform framework to update an action state.
  The state (enabled, visible) of an action determines whether the action is available in the UI of an IDE.
  An object of the [`AnActionEvent`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) type is passed to this method and contains information about the current context for the action.
  Actions are made available by changing state in the [`Presentation`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/Presentation.java) object associated with the event context.
  As explained in [Overriding the `AnAction.update()`  Method](#overriding-the-anactionupdate-method), it is vital `update()` methods _execute quickly_ and return execution to the IntelliJ Platform.
* An action's method `AnAction.actionPerformed()` is called by the IntelliJ Platform if available and selected by the user.
  This method does the heavy lifting for the action - it contains the code executed when the action gets invoked.
  The `actionPerformed()` method also receives `AnActionEvent` as a parameter, which is used to access projects, files, selection, etc.
  See [Overriding the `AnAction.actionPerformed()` Method](#overriding-the-anactionactionperformed-method) for more information.

There are other methods to override in the `AnAction` class, such as changing the default `Presentation` object for the action.
There is also a use case for overriding action constructors when registering them with dynamic action groups, demonstrated in the [Grouping Actions](grouping_action.md#adding-child-actions-to-the-dynamic-group) tutorial.
However, the `update()` and `actionPerformed()` methods are essential to basic operation.

### Overriding the AnAction.update Method

The method `AnAction.update()` is periodically called by the IntelliJ Platform in response to user gestures.
The `update()` method gives an action to evaluate the current context and enable or disable its functionality.
Implementors must ensure that changing presentation and availability status handles all variants and state transitions; otherwise, the given Action will get "stuck".

> The `AnAction.update()` method can be called frequently and on a UI thread.
> This method needs to _execute very quickly_; no real work should be performed in this method.
> For example, checking selection in a tree or a list is considered valid, but working with the file system is not.
>
{type="warning"}

> If the new state of an action cannot be determined quickly, then evaluation should be performed in the `AnAction.actionPerformed()` method, and notify the user that the action cannot be executed if the context isn't suitable.
>
{type="tip"}

#### Determining the Action Context

The `AnActionEvent` object passed to `update()` carries information about the current context for the action.
Context information is available from the methods of `AnActionEvent`, providing information such as the Presentation and whether the action is triggered by a Toolbar.
Additional context information is available using the method `AnActionEvent.getData()`.
Keys defined in [`CommonDataKeys`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java) are passed to the `getData()` method to retrieve objects such as `Project`, `Editor`, `PsiFile`, and other information.
Accessing this information is relatively light-weight and is suited for `AnAction.update()`.

#### Enabling and Setting Visibility for an Action

Based on information about the action context, the `AnAction.update()` method can enable, disable, or hide an action.
An action's enable/disable state and visibility are set using methods of the `Presentation` object, which is accessed using `AnActionEvent.getPresentation()`.

The default `Presentation` object is a set of descriptive information about a menu or toolbar action.
Every context for an action - it might appear in multiple menus, toolbars, or Navigation search locations - has a unique presentation.
Attributes such as an action's text, description, and icons and visibility and enable/disable state, are stored in the presentation.
The attributes in a presentation get initialized from the [action registration](#registering-actions).
However, some can be changed at runtime using the methods of the `Presentation` object associated with an action.

The enabled/disabled state of an action is set using `Presentation.setEnabled()`.
The visibility state of an action is set using `Presentation.setVisible()`
If an action is enabled, the `AnAction.actionPerformed()` can be called if an action is selected in the IDE by a user.
A menu action shows in the UI location specified in the registration.
A toolbar action displays its enabled (or selected) icon, depending on the user interaction.

When an action is disabled `AnAction.actionPerformed()` will not be called.
Toolbar actions display their respective icons for the disabled state.
The visibility of a disabled action in a menu depends on whether the host menu (e.g., "ToolsMenu") containing the action has the `compact` attribute set.
See [Grouping Actions](#grouping-actions) for more information about the `compact` attribute and menu actions' visibility.

> If an action is added to a toolbar, its `update()` can be called if there was any user activity or focus transfer.
> If the action's availability changes in the absence of these events, then call [`ActivityTracker.getInstance().inc()`](upsource:///platform/platform-api/src/com/intellij/ide/ActivityTracker.java) to notify the action subsystem to update all toolbar actions.
>
{type="note"}

An example of enabling a menu action based on whether a project is open is demonstrated in [`PopupDialogAction.update()`](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/action_basics/src/main/java/org/intellij/sdk/action/PopupDialogAction.java) method.

### Overriding the AnAction.actionPerformed Method

When the user selects an enabled action, be it from a menu or toolbar, the action's `AnAction.actionPerformed()` method is called.
This method contains the code executed to perform the action, and it is here that the real work gets done.

By using the `AnActionEvent` methods and `CommonDataKeys`, objects such as the `Project`, `Editor`, `PsiFile`, and other information is available.
For example, the `actionPerformed()` method can modify, remove, or add PSI elements to a file open in the editor.

The code that executes in the `AnAction.actionPerformed()` method should execute efficiently, but it does not have to meet the same stringent requirements as the `update()` method.

An example of inspecting PSI elements is demonstrated in the SDK code sample `action_basics` [`PopupDialogAction.actionPerformed()`](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/action_basics/src/main/java/org/intellij/sdk/action/PopupDialogAction.java) method.

### Action IDs

Every action and action group has a unique identifier.
Basing the identifier for a custom action on the FQN of the implementation is the best practice, assuming the package incorporates the `<id>` of the plugin.
An action must have a unique identifier for each place.
It is used in the IDE UI, even though the FQN of the implementation is the same.
Definitions of identifiers for the standard IntelliJ Platform actions are in [`IdeActions`](upsource:///platform/ide-core/src/com/intellij/openapi/actionSystem/IdeActions.java).

### Grouping Actions

Groups organize actions into logical UI structures, which in turn can contain other groups.
A group of actions can form a toolbar or a menu.
Subgroups of a group can form submenus of a menu.

Actions can be included in multiple groups, and thus appear in different places within the IDE UI.
An action must have a unique identifier for each place it appears in the IDE UI.
See the [Action Declaration Reference](#action-declaration-reference) section for information about how to specify locations in the IDE UI.

#### Presentation

A new [`Presentation`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/Presentation.java) gets created for every place where the action appears.
Therefore, the same action can have different text or icons when it appears in different places of the user interface.
Different presentations for the action are created by copying the Presentation returned by the `AnAction.getTemplatePresentation()` method.

#### Compact Attribute

A group's "compact" attribute specifies whether an action within that group is visible when disabled.
See [Registering Actions in plugin.xml](#registering-actions-in-pluginxml) for an explanation of how the `compact` attribute is set for a group.
If the `compact` attribute is `true` for a menu group, an action in the menu only appears if its state is both enabled and visible.
In contrast, if the `compact` attribute is `false`, an action in the menu appears if its state is disabled but visible.
Some menus like <menupath>Tools</menupath> have the `compact` attribute set, so there isn't a way to show an action on the <menupath>Tools</menupath> menu if it is not enabled.

| Host Menu<br/>`compact` Setting | Action Enabled | Visibility Enabled | Menu Item Visible? | Menu Item Appears Gray? |
|:-------------------------------:|:--------------:|:------------------:|:------------------:|:-----------------------:|
|                T                |     **F**      |         T          |       **F**        |           N/A           |
|                T                |       T        |         T          |         T          |            F            |
|                F                |     **F**      |         T          |       **T**        |          **T**          |
|                F                |       T        |         T          |         T          |            F            |

All other combinations of `compact`, visibility, and enablement produce N/A for gray appearance because the menu item isn't visible.

See the [Grouping Actions](grouping_action.md) tutorial for examples of creating action groups.

## Registering Actions

There are two main ways to register an action: either by listing it in the `<actions>` section of a plugin's <path>plugin.xml</path> file or through code.

### Registering Actions in plugin.xml

Registering actions in <path>plugin.xml</path> is demonstrated in the following reference examples, which document all elements and attributes used in the `<actions>` section and describe each element's meaning.

#### Setting the Override-Text Element

Beginning in 2020.1, an alternate version of an action's menu text can be declared for use depending on where an action appears.
Using the `<override-text>` element, the menu text for an action can be different depending on context: menu location, toolbar, etc.
This is also available for groups in 2020.3 and later.

In the `action` element reference example (below) with `id` attribute `VssIntegration.GarbageCollection`, the default is to use the menu text "Garbage Collector: Collect _Garbage."
The `add-to-group` element declares the action is added to the Tools Menu.

However, the `override-text` element declares that text for `VssIntegration.GarbageCollection` displayed anywhere in the main menu system should be the alternate text "Collect _Garbage."
The <menupath>Tools</menupath> menu is part of the main menu, so the displayed menu text is "Collect _Garbage."
A different context, such as searching for the action using <menupath>Help | Find Action</menupath>, displays the default text "Garbage Collector: Collect _Garbage" to give the user additional information about the action.

A second `override-text` element uses `place` and `use-text-of-place` attributes to declare the same version of the text used in the main menu is also used in the editor popup menu.
Additional `override-text` elements could be used to specify other places where the main menu text should be used.

An example of using `<override-text>` is demonstrated in the [Creating Actions](working_with_custom_actions.md#using-override-text-for-an-action) tutorial.

#### Setting the Synonym Element

_2020.3_
Users can locate actions via their name by invoking <menupath>Help | Find Action</menupath>.

To allow using alternative names in search, add one or more `<synonym>` elements inside `<action>` or `<reference>`:

```xml
<action id="MyAction" text="My Action Name" ...>
  <synonym text="Another Search Term"/>
</action>
```

To provide a localized synonym, specify `key` instead of `text` attribute.

#### Disabling Search for Group

_2020.3_
To exclude a group from appearing in <menupath>Help | Find Action</menupath> results (e.g., <control>New...</control> popup), specify `searchable="false"`.

#### Localizing Actions and Groups

Action and group localization use resource bundles containing property files named `*Bundle.properties`, each file consisting of `key=value` pairs.
The [`action_basics`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/action_basics) plugin demonstrates using a resource bundle to localize the group and action entries added to the Editor Popup Menu.

When localizing actions and groups, the `text` and `description` attributes are not declared in <path>plugin.xml</path>.
Instead, those attribute values vary depending on the locale and get declared in a resource bundle.

The name and location of the resource bundle must be declared in the <path>plugin.xml</path> file.
In the case of `action_basics`, only a default localization resource bundle (<path>/resources/messages/BasicActionsBundle.properties</path>) is provided:

```xml
<resource-bundle>messages.BasicActionsBundle</resource-bundle>
```

_2020.1_
If necessary, a dedicated resource bundle to use for actions and groups can be defined on `<actions>`:

```xml
<actions resource-bundle="messages.MyActionsBundle">
  <!-- action/group defined here will use keys from MyActionsBundle.properties -->
</actions>
```


See [Extending DefaultActionGroup](grouping_action.md#extending-defaultactiongroup) for a tutorial of localizing Actions and Groups.

<tabs>

<tab title="Actions">

For Actions, the key in property files incorporates the action `id` in this specific structure:
* `action.<action-id>.text=Translated Action Text`
* `action.<action-id>.description=Translated Action Description`

_2020.1_
If `<override-text>` is used for an action `id`, the key includes the `<place>` attribute:
* `action.<action-id>.<place>.text=Place-dependent Translated Action Text`

</tab>

<tab title="Groups">

For Groups, the `key` in the property files incorporates the group `id` in this specific structure:
* `group.<group-id>.text=Translated Group Text`
* `group.<group-id>.description=Translated Group Description`

_2020.3_
If `<override-text>` is used for a group `id`, the key includes the `<place>` attribute:
* `group.<group-id>.<place>.text=Place-dependent Translated Group Text`

</tab>

</tabs>


#### Action Declaration Reference

The places where actions can appear are defined by constants in [`ActionPlaces`](upsource:///platform/ide-core/src/com/intellij/openapi/actionSystem/ActionPlaces.java).
Group IDs for the IntelliJ Platform are defined in [`PlatformActions.xml`](upsource:///platform/platform-resources/src/idea/PlatformActions.xml).

This, and additional information can also be found by using the [Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#invoke-basic-completion), [Quick Definition](https://www.jetbrains.com/help/idea/viewing-reference-information.html#view-definition-symbols) and [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation) features.

> To lookup existing Action ID (e.g. for use in `relative-to-action`), [UI Inspector](internal_ui_inspector.md) can be used.
>
{type="tip"}

```xml
<actions>

  <!--
  The <action> element defines an action to register. Attributes:
   - "id" (required) - specifies a unique identifier for the action
   - "class" (required) - specifies the FQN of the class implementing
     the action
   - "text" (required) - specifies the default long-version text to be
     displayed for the action (tooltip for toolbar button or text for
     menu item)
   - "use-shortcut-of" (optional) - specifies the ID of the action
     whose keyboard shortcut this action will use
   - "description" (optional) - specifies the text which is displayed
     in the status bar when the action is focused
   - "icon" (optional) - specifies the icon which is displayed on
     the toolbar button or next to the menu item
  -->
  <action
      id="VssIntegration.GarbageCollection"
      class="com.example.impl.CollectGarbage"
      text="Garbage Collector: Collect _Garbage"
      description="Run garbage collector"
      icon="icons/garbage.png">

    <!--
    The <override-text> element defines an alternate version of the text
    for the menu action. Attributes:
     - "text" (required) - defines the text to be displayed for the action
     - "place" (required) - declares where the alternate text should
        be used. In this example, any time the action is displayed in
        the IDE main menu (and submenus), the override-text version should
        be used.
    The second <override-text> element uses the alternate attribute
    "use-text-of-place" to define a location (EditorPopup) to use the same
    text as is used in MainMenu. It is a way to specify the use of
    an alternate menu text in multiple discrete menu groups.
    -->
    <override-text place="MainMenu" text="Collect _Garbage"/>
    <override-text place="EditorPopup" use-text-of-place="MainMenu"/>

    <!-- Provide alternative names for searching action by name -->
    <synonym text="GC"/>

    <!--
    The <add-to-group> node specifies that the action should be added to an
    existing group. An action can be added to several groups. Attributes:
     - "group-id" (required) - specifies the ID of the group to which the
       action is added. The group must be an implementation of the
       DefaultActionGroup class.
     - "anchor" (required) - specifies the position of the action in
       the relative to other actions. Allowed values: "first", "last",
       "before", and "after".
     - "relative-to-action" (mandatory if "anchor" is "before" or "after") -
       specifies the action before or after which the current action is
       inserted.
    -->
    <add-to-group
        group-id="ToolsMenu"
        relative-to-action="GenerateJavadoc"
        anchor="after"/>

    <!--
    The <keyboard-shortcut> node specifies the keyboard shortcut for
    the action. An action can have several keyboard shortcuts. Attributes:
     - "first-keystroke" (required) - specifies the first keystroke of
       the action. The keystrokes are specified according to the regular
       Swing rules.
     - "second-keystroke" (optional) - specifies the second keystroke of
       the action.
     - "keymap" (required) - specifies the keymap for which the action
       is active. IDs of the standard keymaps are defined as constants
       in the com.intellij.openapi.keymap.KeymapManager class.
     - "remove" (optional) - shortcut should be removed from the specified
       action. See the second <keyboard-shortcut> example element below.
     - "replace-all" (optional) - removes all keyboard and mouse shortcuts
       from the specified action before adding the specified shortcut.
       See the third <keyboard-shortcut> example element below.
    -->

    <!-- Add the first and second keystrokes to all keymaps... -->
    <keyboard-shortcut
        keymap="$default"
        first-keystroke="control alt G"
        second-keystroke="C"/>

    <!-- ...except the "Mac OS X" keymap and its children. -->
    <keyboard-shortcut
        keymap="Mac OS X"
        first-keystroke="control alt G"
        second-keystroke="C"
        remove="true"/>

    <!-- The "Mac OS X 10.5+" keymap and its children will have only this
    keyboard shortcut for this action. -->
    <keyboard-shortcut
        keymap="Mac OS X 10.5+"
        first-keystroke="control alt G"
        second-keystroke="C"
        replace-all="true"/>

    <!--
    The <mouse-shortcut> node specifies the mouse shortcut for the action.
    An action can have several mouse shortcuts. Attributes:
    - "keystroke" (required) - specifies the clicks and modifiers for
      the action. It is defined as a sequence of words separated by spaces:
       * mouse buttons: "button1", "button2", "button3"
       * modifier keys: "shift", "control", "meta", "alt", "altGraph"
       * button double-click: "doubleClick"
    - "keymap" (required) - specifies the keymap for which the action is
      active. IDs of the standard keymaps are defined as constants in
      the com.intellij.openapi.keymap.KeymapManager class.
    The <mouse-shortcut> element can also specify "remove" and "replace-all"
    attributes. See <keyboard-shortcut> description above for details.
    -->
    <mouse-shortcut
        keymap="$default"
        keystroke="control button3 doubleClick"/>
  </action>

  <!--
  This action declares neither a text nor a description attribute. If it
  has a resource bundle declared, the text and descriptions will be
  retrieved based on the action-id incorporated in the key for a translated
  string.
  -->
  <action
      id="sdk.action.PopupDialogAction"
      class="sdk.action.PopupDialogAction"
      icon="SdkIcons.Sdk_default_icon"/>

  <!--
  The <group> element defines an action group. The <action>, <group>
  and <separator> elements defined within it are automatically included
  in the group. Attributes:
   - "id" (required) - specifies a unique identifier for the group.
   - "class" (optional) - specifies the FQN of the class implementing
     the group. If not specified,
     com.intellij.openapi.actionSystem.DefaultActionGroup is used.
   - "text" (optional) - specifies the text of the group (text for the menu
     item showing the submenu).
   - "description" (optional) specifies the text which is displayed in the
     status bar when the group has focus.
   - "icon" (optional) - specifies the icon which is displayed on
     the toolbar button or next to the menu group.
   - "popup" (optional) - specifies how the group is presented in the menu.
      * "true" - group actions are placed in a submenu
      * "false" - actions are displayed as a section of the same menu
        delimited by separators.
   - "compact" (optional) - specifies whether an action within that group
     is visible when disabled. Setting compact="true" specifies an action
     in the group isn't visible unless the action is enabled.
  -->
  <group
      class="com.example.impl.MyActionGroup"
      id="TestActionGroup"
      text="Test Group"
      description="Group with test actions"
      icon="icons/testgroup.png"
      popup="true"
      compact="true">

    <action
        id="VssIntegration.TestAction"
        class="com.example.impl.TestAction"
        text="My Test Action"
        description="My test action"/>

    <!-- The <separator> element defines a separator between actions.
    It can also have an <add-to-group> child element. -->
    <separator/>

    <group id="TestActionSubGroup"/>

    <!-- The <reference> element allows adding an existing action to
    the group. The mandatory "ref" attribute specifies the ID of
    the action to add. -->
    <reference ref="EditorCopy"/>

    <add-to-group
        group-id="MainMenu"
        relative-to-action="HelpMenu"
        anchor="before"/>
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

If an action toolbar is attached to a specific component (for example, a panel in a tool window), call `ActionToolbar.setTargetComponent()` and pass the related component's instance as a parameter.
Setting the target ensures that the toolbar buttons' state depends on the state of the related component, not on the current focus location within the IDE frame.

See [Toolbar](https://jetbrains.design/intellij/controls/toolbar/) in IntelliJ Platform UI Guidelines for an overview.
