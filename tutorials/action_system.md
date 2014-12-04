IntelliJ Action System
==========
Action system provides an option to handle certain events in a desired way. Action can either be simply a responce to some state,
or be bound to UI element and could be invoked on demand. These UI elements include main menu, context menus and toolbars.
An action is technically a class, derived from the [AnAction] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) class.
To update the state of the action, the method AnAction.update() is periodically called by IDEA.
The object of type [AnActionEvent] (https://github.com/JetBrains/intellij-community/blob/ff16ce78a1e0ddb6e67fd1dbc6e6a597e20d483a/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
passed to this method carries the information about the current context for the action,
and in particular, the specific presentation which needs to be updated.

#How create a new menu item and bidn an action to it?
To register an action as a menu item, an <action> attribute should be added to the <actions> section of the plugin configuration file
[plugin.xml] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/plugin_sample/META-INF/plugin.xml)

    <actions>

    <!-- The <action> element defines an action to register.
    The mandatory "id" attribute specifies an unique identifier for the action.
    The mandatory "class" attribute specifies the full-qualified name of the class implementing the action.
    The mandatory "text" attribute specifies the text of the action (tooltip for toolbar button or text for menu item).
    The optional "use-shortcut-of" attribute specifies the ID of the action whose keyboard shortcut this action will use.
    The optional "description" attribute specifies the text which is displayed in the status bar when the action is focused.
    The optional "icon" attribute specifies the icon which is displayed on the toolbar button or next to the menu item. -->

    <action id="PluginSample.DummyAction" class="org.jetbrains.plugins.sample.SimpleAction" text="Dummy Action" description="Illustrates how to plug an action in">

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
      <add-to-group group-id="ToolsMenu" anchor="after"/>
    </action>
    </actions>

[Link to source code] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/plugin_sample/META-INF/plugin.xml)

