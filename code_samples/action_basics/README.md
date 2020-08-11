# Action Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

## Quickstart

Action Sample Project demonstrates registering actions process in a various configuration.
Each action is an extension of the [`AnAction`][sdk:AnAction] abstract class and brings the possibility
of extending IDE with an event performed with the user interaction - i.e. clicking the button, using keyboard
or mouse shortcuts.

Plugin registers the [`PopupDialogAction`][file:PopupDialogAction] action, which provides a popup dialog as a feedback,
in three different ways:

- by assigning the keyboard (<kbd>Ctrl/Cmd</kbd>+<kbd>Alt</kbd>+<kbd>A</kbd>, <kbd>C</kbd>) and mouse shortcuts
  (<kbd>Ctrl/Cmd</kbd> + <kbd>Mouse Button 3</kbd> + <kbd>Double Click</kbd>),
- by adding action item to the `ToolsMenu` group, available in Tools menu,
- by adding action item to the `EditorPopupMenu` group, available in Editor's context menu.

### Actions

| Name                                               | Implementation Class                                      | Interface                      |
| -------------------------------------------------- | --------------------------------------------------------- | ------------------------------ |
| `org.intellij.sdk.action.PopupDialogAction`        | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.GroupPopDialogAction`     | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.CustomGroupedAction`      | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.CustomDefaultActionGroup` | [CustomDefaultActionGroup][file:CustomDefaultActionGroup] | [ActionGroup][sdk:ActionGroup] |
| `org.intellij.sdk.action.DynamicActionGroup`       | [DynamicActionGroup][file:DynamicActionGroup]             | [ActionGroup][sdk:ActionGroup] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html

[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/action/PopupDialogAction.java
[file:CustomDefaultActionGroup]: ./src/main/java/org/intellij/sdk/action/CustomDefaultActionGroup.java
[file:DynamicActionGroup]: ./src/main/java/org/intellij/sdk/action/DynamicActionGroup.java

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[sdk:ActionGroup]: upsource:///platform/platform-api/src/com/intellij/openapi/actionSystem/ActionInGroup.java
