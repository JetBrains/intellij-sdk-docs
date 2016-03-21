---
title: 1. Working With Custom Actions
---


An action is technically a class, derived from the
[AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class.
To update the state of the action, the method AnAction.update() is periodically called by IDEA.
The object of type
[AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
passed to this method carries the information about the current context for the action,
and in particular, the specific presentation which needs to be updated.


### 1.1. Creating actions

To create a new we need to extend
[AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class:

```java
public class SimpleAction extends AnAction {
}
```

### 1.2. Overriding actionPerformed()

The only method of an inheritor of
[AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
which needs to be overridden is `public void actionPerformed(AnActionEvent anActionEvent);`, and it should contain a part of code to be executed after the action has been invoked. 
In this case the action does nothing.

```java
public class SimpleAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    }
}
```

### 1.3. Registering actions

To register a newly created action, `<action>` attribute should be added to the `<actions>` section of the plugin configuration file
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml).
IntelliJ IDEA has an embedded inspection that spots unregistered actions.
!["Action never used" inspection](img/action_never_used.png)

To register the action and set up it's attributes press ***Alt + Enter*** while the caret is placed on the action's declaration.
!["Register action" quick fix](img/action_never_used.png)

Fill the **New Action** form to set up action's parameters such as: action's name and description, a UI component the action is bound to,
visual position of the menu item the action is bound to, and a shortcut for invoking the action.
In our case the action will be available in the **Tools Menu**, it will be placed on top, and will have no shortcuts.
!["Register action" quick fix](img/new_action.png)

After filling the **New Action** form and applying the changes `<actions>` section of our
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml)
file will look like this:

```xml
<actions>
  <!-- Add your actions here -->
    <action id="org.jetbrains.tutorials.actions.SimpleAction" class="org.jetbrains.tutorials.actions.SimpleAction"
            text="Simple Action" description="IntelliJ Action System Demo">
        <add-to-group group-id="ToolsMenu" anchor="first"/>
    </action>
</actions>
```

### 1.4. Setting attributes manually

Full list of action's attributes can also be set manually in
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml)
configuration file like the following code sample shows:

```xml
<actions>
    <!-- Add your actions here -->
    <!-- The <action> element defines an action to register.
    The mandatory "id" attribute specifies an unique identifier for the action.
    The mandatory "class" attribute specifies the full-qualified name of the class implementing the action.
    The mandatory "text" attribute specifies the text of the action (tooltip for toolbar button or text for menu item).
    The optional "use-shortcut-of" attribute specifies the ID of the action whose keyboard shortcut this action will use.
    The optional "description" attribute specifies the text which is displayed in the status bar when the action is focused.
    The optional "icon" attribute specifies the icon which is displayed on the toolbar button or next to the menu item. -->
      <action id="org.jetbrains.tutorials.actions.SimpleAction" class="org.jetbrains.tutorials.actions.SimpleAction"
              text="Simple Action" description="IntelliJ Action System Demo">
          <!-- The <keyboard-shortcut> node specifies the keyboard shortcut for the action. An action can have several keyboard shortcuts.
          The mandatory "first-keystroke" attribute specifies the first keystroke of the action. The key strokes are specified according to the regular Swing rules.
          The optional "second-keystroke" attribute specifies the second keystroke of the action.
          The mandatory "keymap" attribute specifies the keymap for which the action is active. IDs of the standard keymaps are defined as
          constants in the com.intellij.openapi.keymap.KeymapManager class. -->
          <keyboard-shortcut first-keystroke="control alt A" second-keystroke="C" keymap="$default"/>
          <!-- The <mouse-shortcut> node specifies the mouse shortcut for the action. An action can have several mouse shortcuts.
          The mandatory "keystroke" attribute specifies the clicks and modifiers for the action. It is defined as a sequence of words separated by spaces:
          "button1", "button2", "button3" for the mouse buttons; "shift", "control", "meta", "alt", "altGraph" for the modifier keys;
          "doubleClick" if the action is activated by a double-click of the button.
          The mandatory "keymap" attribute specifies the keymap for which the action is active. IDs of the standard keymaps are defined as
          constants in the com.intellij.openapi.keymap.KeymapManager class. -->
          <mouse-shortcut keystroke="control button3 doubleClick" keymap="$default"/>
          <!-- The <add-to-group> node specifies that the action should be added to an existing group. An action can be added to several groups.
          The mandatory "group-id" attribute specifies the ID of the group to which the action is added.
          The group must be implemented by an instance of the DefaultActionGroup class.
          The mandatory "anchor" attribute specifies the position of the action in the group relative to other actions. It can have the values
          "first", "last", "before" and "after".
          The "relative-to-action" attribute is mandatory if the anchor is set to "before" and "after", and specifies the action before or after which
          the current action is inserted. -->
          <add-to-group group-id="ToolsMenu" anchor="first"/>
      </action>
</actions>
```

After performing the steps described above we need to compile and run the plugin to the the newly created action available as a Tools Menu item:

!["Register action" quick fix](img/tools_menu_item_action.png)

### 1.5. Performing an action

In order to make the action do something we need to implement it's `public void actionPerformed(AnActionEvent anActionEvent);` method.
In the following example action invokes a dialog that shows information about a selected Project View Item and has no icon and any pre-selected default option:

```java
@Override
public void actionPerformed(AnActionEvent anActionEvent) {
    Object navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    if (navigatable != null) {
        Messages.showDialog(navigatable.toString(), "Selected Element:", new String[]{"OK"}, -1, null);
    }
}
```


### 1.6. Setting up action's visibility and availability

To manipulate with action's visibility and availability we need to override it's `public void update(@NotNull AnActionEvent e);`

Default implementation of this method does nothing.
Override this method to provide the ability to dynamically change action's state and(or) presentation depending on the context.

```java
public class SimpleAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    //...
    }

    @Override
    public void update(AnActionEvent anActionEvent) {
    }
}
```

The following code sample illustrates how to make the action visible and available only when the following conditions are met:
there's a project available and there's an item you can navigate to selected in the project view pane:

```java
public class SimpleAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    //...
    }

     @Override
     public void update(AnActionEvent anActionEvent) {
         final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
         if (project == null)
             return;
         Object navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
         anActionEvent.getPresentation().setEnabledAndVisible(navigatable != null);
     }
}
```

Parameter anActionEvent carries information on the invocation place and data available.

**Note** This method can be called frequently, for instance, if an action is added to a toolbar, it will be updated twice a second.
This means that this method is supposed to work really fast, no real work should be done at this phase.
For example, checking selection in a tree or a list, is considered valid, but working with a file system is not.
If you cannot understand the state of the action fast you should do it in the
[AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
method and notify the user that action cannot be executed if it's the case.


After compiling and running the plugin project and invoking the action, the dialog will pop up:

!["Register action" quick fix](img/action_performed.png)
