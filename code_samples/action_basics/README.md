# Action Basics Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

## Quickstart

The Action Basics Sample Project demonstrates the process of registering actions in various configurations.
Each action is an extension of the `AnAction` abstract class and brings the possibility of extending the IDE with an event performed with the user interaction - i.e., clicking the button, using the keyboard or mouse shortcuts.

This Plugin registers the [`PopupDialogAction`][file:PopupDialogAction] action, which provides a popup dialog as feedback, in three different ways:
- by assigning the keyboard (<kbd>Ctrl/Cmd</kbd>+<kbd>Alt</kbd>+<kbd>A</kbd>, <kbd>C</kbd>) and mouse shortcuts (<kbd>Ctrl/Cmd</kbd> + <kbd>Mouse Button 3</kbd> + <kbd>Double Click</kbd>),
- by adding an action to the `ToolsMenu` directly, and as part of new groups added to the Tools menu,
- by adding an action to a new group in the `EditorPopupMenu`, which is the Editor's context menu.

Additional features of the plugin:
- [Using the `<override-text>`][docs:action-override] element in an [`<action>`][docs:plugin-configuration-file:actions:action] element is demonstrated in the `plugin.xml` declaration to add the `PopupDialogAction` action directly to the `ToolsMenu`.
- [Localization of action and group][docs:action-locale] `text` and `description` attributes using a [`<resource-bundle>`][docs:plugin-configuration-file:resource-bundle] is demonstrated in the declaration to add a new group to the `EditorPopupMenu`.

### Actions

| ID                                                 | Implementation                                            | Base Action Class |
|----------------------------------------------------|-----------------------------------------------------------|-------------------|
| `org.intellij.sdk.action.GroupPopDialogAction`     | [PopupDialogAction][file:PopupDialogAction]               | `AnAction`        |
| `org.intellij.sdk.action.PopupDialogAction`        | [PopupDialogAction][file:PopupDialogAction]               | `AnAction`        |
| `org.intellij.sdk.action.CustomGroupedAction`      | [PopupDialogAction][file:PopupDialogAction]               | `AnAction`        |
| `org.intellij.sdk.action.CustomDefaultActionGroup` | [CustomDefaultActionGroup][file:CustomDefaultActionGroup] | `ActionGroup`     |
| `org.intellij.sdk.action.DynamicActionGroup`       | [DynamicActionGroup][file:DynamicActionGroup]             | `ActionGroup`     |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/action-system.html
[docs:action-override]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html#idea-plugin__actions__action__override-text
[docs:action-locale]: https://plugins.jetbrains.com/docs/intellij/action-system.html#localizing-actions-and-groups
[docs:plugin-configuration-file:actions:action]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html#idea-plugin__actions__action
[docs:plugin-configuration-file:resource-bundle]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html#idea-plugin__resource-bundle

[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/action/PopupDialogAction.java
[file:CustomDefaultActionGroup]: ./src/main/java/org/intellij/sdk/action/CustomDefaultActionGroup.java
[file:DynamicActionGroup]: ./src/main/java/org/intellij/sdk/action/DynamicActionGroup.java
