<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Actions

<link-summary>Introduction to actions allowing to invoke plugin functionalities.</link-summary>

The IntelliJ Platform provides the concept of _actions_.
An action is a class derived from [`AnAction`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java), whose `actionPerformed()` method is called when its menu item or toolbar button is selected.

Actions are the most common way for a user to invoke the functionality of your plugin.
An action can be invoked from a menu or a toolbar, using a keyboard shortcut or the <ui-path>Help | Find Action...</ui-path> lookup.

Actions are organized into groups, which, in turn, can contain other groups.
A group of actions can form a toolbar or a menu.
Subgroups of the group can form submenus of a menu.

The user can customize all registered actions via [Menus and Toolbars](https://www.jetbrains.com/help/idea/customize-actions-menus-and-toolbars.html) settings.

Please see [](action_system.md) on how to create and register actions in the IDE.
