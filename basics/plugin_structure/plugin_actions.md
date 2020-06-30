---
title: Plugin Actions
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The *IntelliJ Platform* provides the concept of _actions_. 
An action is a class derived from [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java), whose `actionPerformed()` method is called when its menu item or toolbar button is selected.

Actions are the most common way for a user to invoke the functionality of your plugin. An action can be invoked from
a menu or a toolbar, using a keyboard shortcut, or from the **Help \| Find Action...** lookup.

Actions are organized into groups, which, in turn, can contain other groups. A group of actions can form a toolbar or a menu. 
Subgroups of the group can form submenus of a menu.

The user can customize all registered actions via [Menus and Toolbars](https://www.jetbrains.com/help/idea/customize-actions-menus-and-toolbars.html) settings.  

Please see [Action System](/basics/action_system.md) on how to create and register actions in the IDE.
