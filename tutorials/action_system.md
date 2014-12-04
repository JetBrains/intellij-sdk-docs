IntelliJ Action System
==========
The system of actions allows plugins to add their own items to IDEA menus and toolbars.
An action is a class, derived from the [AnAction] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) class,
whose actionPerformed method is called when the menu item or toolbar button is selected.
For example, one of the action classes is responsible for the "File | Open File..." menu item and for the "Open File" toolbar button.
Actions may be organized into groups, which, in turn, can contain other groups. A group of actions can form a toolbar or a menu.
Subgroups of the group can form submenus of the menu.

