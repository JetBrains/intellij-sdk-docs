---
title: Plugin Actions
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The *IntelliJ Platform* provides the concept of _actions_. An action is a class, derived from the [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) class, whose `actionPerformed()` method is called when the menu item or toolbar button is selected.

Actions are the most common way for a user to invoke the functionality of your plugin. An action can be invoked from
a menu or a toolbar, using a keyboard shortcut, or from the Find Action interface.

Actions are organized into groups, which, in turn, can contain other groups. A group of actions can form a toolbar or a menu. Subgroups of the group can form submenus of a menu. You can find detailed information on how to create and register your actions in the [IntelliJ Platform Action System](/basics/action_system.md).
