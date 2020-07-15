[🔙 IntelliJ Platform SDK Code Samples](../README.md) [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]

# Action Basics

## Quickstart

Action Basics code sample project demonstrates registering actions process in a various configuration.
Each action is an extension of the [`AnAction`][sdk:AnAction] abstract class and brings the possibility
of extending IDE with an event performed with the user interaction - i.e. clicking the button, using keyboard
or mouse shortcuts.

Plugin registers the [`PopupDialogAction`][file:PopupDialogAction] action, which provides a popup dialog as a feedback,
in three different ways:

- by assigning the keyboard (<kbd>Ctrl/Cmd</kbd>+<kbd>Alt</kbd>+<kbd>A</kbd>, <kbd>C</kbd>) and mouse shortcuts
  (<kbd>Ctrl/Cmd</kbd> + <kbd>Mouse Button 3</kbd> + <kbd>Double Click</kbd>),
- by adding action item to the `ToolsMenu` group, available in Tools menu,
- by adding action item to the `EditorPopupMenu` group, available in Editor's context menu.

## Structure

Action Basics plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:pluginxml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Actions

| Name                                               | Implementation Class                                      | Interface                      |
| -------------------------------------------------- | --------------------------------------------------------- | ------------------------------ |
| `org.intellij.sdk.action.PopupDialogAction`        | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.GroupPopDialogAction`     | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.CustomGroupedAction`      | [PopupDialogAction][file:PopupDialogAction]               | [AnAction][sdk:AnAction]       |
| `org.intellij.sdk.action.CustomDefaultActionGroup` | [CustomDefaultActionGroup][file:CustomDefaultActionGroup] | [ActionGroup][sdk:ActionGroup] |
| `org.intellij.sdk.action.DynamicActionGroup`       | [DynamicActionGroup][file:DynamicActionGroup]             | [ActionGroup][sdk:ActionGroup] |

[Actions documentation][docs:actions]

## Function


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:gradle]: https://jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/action/PopupDialogAction.java
[file:CustomDefaultActionGroup]: ./src/main/java/org/intellij/sdk/action/CustomDefaultActionGroup.java
[file:DynamicActionGroup]: ./src/main/java/org/intellij/sdk/action/DynamicActionGroup.java
[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml

[sdk:AnAction]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[sdk:ActionGroup]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/actionSystem/ActionInGroup.java
