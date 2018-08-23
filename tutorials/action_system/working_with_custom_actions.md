---
title: 1. Registering an Action
---


An action is technically a class, derived from the
[AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class.
To update the state of the action, the method `AnAction.update(AnActionEvent)` is called by the IntelliJ Platform framework.
The object of type
[AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
passed to this method carries the information about the current context for the action,
and in particular, the specific presentation which needs to be updated.


### 1.1. Creating actions

To create a new action we need to extend the [AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class. As an example we will do that in the `SimplePopDialogAction` class in the `register_actions` code sample.

```java
public class SimplePopDialogAction extends AnAction {
}
```

**Note** the `SimplePopDialogAction` does not have class fields of any kind. This is because an instance of `AnAction` class
exists for the entire lifetime of the application. If `AnAction` class uses a field to store data which has a shorter 
lifetime, and doesn't clear this data promptly, the data will be leaked. For example, any `AnAction` data that exists 
only within the context of a `Project` will cause the `Project` to be kept in memory after the user has closed it.


### 1.2. Overriding actionPerformed()

The [AnAction](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class is abstract, and classes that extend it must override the `AnAction.actionPerformed(AnActionEvent)` method.
This method should contain the code to be executed when the action has been invoked.
In this case `SimplePopDialogAction.actionPerformed(AnActionEvent)` doesn't do anything yet.

```java
public class SimplePopDialogAction extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    // Using the event, create and show a dialog
  }
}
```

### 1.3. Registering actions

To register a newly created action, an `<action>` attribute should be added to the `<actions>` section of the plugin configuration file
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml).
IntelliJ IDEA has an embedded inspection that spots unregistered actions. Here is an example using the `SimplePopDialogAction` class:

!["Action never used" inspection](img/action_never_used.png)

To register `SimplePopDialogAction` and set up its attributes press ***Alt + Enter*** while the caret is placed on the action's declaration.

Then fill out the **New Action** form to set up `SimplePopDialogAction`'s parameters such as: the action's name and description, 
a UI component the action is bound to, the visual position of the menu item the action is bound to, and a shortcut for invoking the action.
In this case `SimplePopDialogAction` would be available in the **Tools Menu**, it would be placed on top, and would have no shortcuts.

![New Action](img/new_action.png)

In this example, after completing the **New Action** form and applying the changes, the `<actions>` section of the plugin's `plugins.xml` file
would now contain:

```xml
<actions>
    <!-- Add your actions here -->
    <action id="org.jetbrains.tutorials.actions.SimplePopDialogAction"
            class="org.jetbrains.tutorials.actions.SimplePopDialogAction" text="Simple Action"
            description="IntelliJ Action System Demo">
      <add-to-group group-id="ToolsMenu" anchor="first"/>
    </action>
</actions>
```
This declaration is adequate, but as we'll see in the next section there are more elements that can be added to the declaration.


### 1.4. Setting attributes manually

You can configure additional attributes of the action by adding them to the **New Action** form or by editing its registration in the plugin.xml file.
Please refer to the [Action System documentation](/basics/action_system.html#registering-actions) for the full list
of supported attributes.

The `<action>` declaration for `SimplePopDialogAction` in the register_actions 
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml) 
file actually contains elements for `<keyboard-shortcut>` and `<mouse-shortcut>`. The full declaration is:

```xml
<action id="org.jetbrains.tutorials.actions.SimpleAction" class="org.jetbrains.tutorials.actions.SimplePopDialogAction"
        text="Simple Action" description="IntelliJ Action System Demo">
  <keyboard-shortcut first-keystroke="control alt A" second-keystroke="C" keymap="$default"/>
  <mouse-shortcut keystroke="control button3 doubleClick" keymap="$default"/>
  <add-to-group group-id="ToolsMenu" anchor="first"/>
</action>
```
The [plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/register_actions/resources/META-INF/plugin.xml) 
file contains copious comments about the declaration.

After performing the steps described above we need to compile and run the plugin to the the newly created action available as a Tools Menu item:

!["Register action" quick fix](img/tools_menu_item_action.png)


### 1.5. Performing an action

In order to make the action do something we need to add code to the `SimplePopDialoigAction.actionPerformed(AnActionEvent)` method.
The following code gets information from the `anActionEvent` input parameter and constructs a simple message dialog.
A generic icon, and the `description` and `text` attributes from the invoking menu action are displayed.

For demonstration purposes the `AnActionEvent.getData()` method tests if a [Navigatable](upsource:///platform/core-api/src/com/intellij/pom/Navigatable.java) 
object is available, meaning e.g. an element has been selected in the editor. If so, information about 
the selected element is opportunistically added to the dialog.

```java
@Override
public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    // Using the event, create and show a dialog
    Project currentProject = anActionEvent.getProject();
    StringBuffer dlgMsg = new StringBuffer(anActionEvent.getPresentation().getText() + " Selected!");
    String dlgTitle = anActionEvent.getPresentation().getDescription();
    // If an element is selected in the editor, add info about it.
    Navigatable nav = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    if (nav != null) {
      dlgMsg.append(String.format("\nSelected Element: %s", nav.toString()));
    }
Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon());
}
```

### 1.6. Setting up an action's visibility and availability

To control the action's visibility and availability we need to override the `AnAction.update(AnActionEvent)` method.
The default implementation of this method does nothing, which means the action is always disabled.
Override this method to provide the ability to dynamically change action's state and(or) presentation depending on the context.

In this example the `SimplePopDialogAction.actionPerformed(AnActionEvent)` method relies on a `Project`
object being available. So the `SimplePopDialogAction.update(AnActionEvent)` method disables
the action for contexts where a`Project` object isn't defined:

```java
@Override
public void update(AnActionEvent anActionEvent) {
    // Set the availability based on whether a project is open
    Project project = anActionEvent.getProject();
    anActionEvent.getPresentation().setEnabledAndVisible(project != null);
}
```

Parameter `anActionEvent` carries information on the invocation place and data available. Note the `update()` method does
not check to see if a [Navigatable](upsource:///platform/core-api/src/com/intellij/pom/Navigatable.java) object is available
before enabling `SimplePopDialogAction`. This is done for the purposes of demonstration code.  

**Note** This method can be called frequently: for instance, if an action is added to a toolbar, it will be updated twice a second.
This means that this method is supposed to _work really fast_; no real work should be done at this phase.
For example, checking selection in a tree or a list, is considered valid but working with the file system is not.
If you cannot understand the state of the action fast you should do it in the
[AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
method and notify the user that the action cannot be executed if it's the case.


After compiling and running the plugin project and invoking the action, the dialog will pop up:

![Action performed](img/action_performed.png)
