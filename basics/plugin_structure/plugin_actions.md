---
title: Plugin Actions
---

*Intellij IDEA* provides the concept of _actions_. An action is a class, derived from the [`AnAction`](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) class, whose `actionPerformed` method is called when the menu item or toolbar button is selected.

The system of actions allows plugins to add their own items to IDEA menus and toolbars. Actions are organized into groups, which, in turn, can contain other groups. A group of actions can form a toolbar or a menu. Subgroups of the group can form submenus of a menu. You can find detailed information on how to create and register your actions in [IntelliJ IDEA Action System](/basics/action_system.md).
