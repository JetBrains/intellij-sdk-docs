---
layout: editable
title: Plugin Actions
---

*Intellij IDEA* provides the concept of _actions_.
An action is a class, derived from the
[AnAction](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class, whose ```actionPerformed``` method is called when the menu item or toolbar button is selected.
The system of actions allows plugins to add their own items to IDEA menus and toolbars.
Actions are organized into groups, which, in turn, can contain other groups.
A group of actions can form a toolbar or a menu. Subgroups of the group can form submenus of the menu.
You can find detailed information on how to create and register your actions in
[IntelliJ IDEA Action System](action_system.md).