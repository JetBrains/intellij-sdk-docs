---
title: Popups
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Popups

The *IntelliJ Platform* user interface makes extensive use of popups \- semi-modal windows that have no chrome (explicit closing buttons) and disappear automatically on focus loss.
Making use of these controls in your plugin ensures a consistent user experience between your plugin and the rest of the IDE.

Popups can optionally display a title, are optionally movable and resizable (and support remembering their size), and can be nested (show another popup when an item is selected).

The
[`JBPopupFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java)
interface allows you to create popups that display different kinds of components, depending on your specific needs.
The most commonly used methods are:

*  `createComponentPopupBuilder()` is the most generic one, allowing you to show any
[Swing](https://docs.oracle.com/javase/tutorial/uiswing/start/index.html)
component in the popup.

*  `createPopupChooserBuilder()` creates a popup for choosing one or more items from a plain `java.util.List`

*  `createConfirmation()` creates a popup for choosing between two options, and performing different actions depending on which option is selected.

*  `createActionGroupPopup()` creates a popup which shows the actions from an action group and executes the action selected by the user.

Action group popups support different ways of choosing an action from the keyboard, in additional to the normal arrow keys.
By passing one of the constants in the
[`ActionSelectionAid`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java)
enumeration, you can choose whether an action can be selected by pressing a key corresponding to its sequential number, typing part of its text (speed search) or pressing a mnemonic character.
For popups with a fixed set of items, the recommended selection method is sequential numbering;
for popups with a variable and potentially large number of items, speed search typically works best.

If you need to create a list-like popup which is more flexible than a simple
[`JList`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JList.html)
but don't want to represent the possible choices as actions in an action group, you can work directly with the
[`ListPopupStep`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/popup/ListPopupStep.java)
interface and the
[`JBPopupFactory.createListPopup()`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java)
method.
Normally you don't need to implement the entire interface; instead, you can derive from the
[`BaseListPopupStep`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/popup/util/BaseListPopupStep.java)
class.
The key methods to override are `getTextFor()` (returning the text to display for an item) and `onChosen()` (called when an item is selected).
By returning a new popup step from the `onChosen()` method, you can implement hierarchical (nested) popups.

Once you've created the popup, you need to display it by calling one of the `show()` methods.
You can let the IntelliJ Platform automatically choose the position based on the context, by calling `showInBestPositionFor()`, or specify the position explicitly through methods like `showUnderneathOf()` and `showInCenterOf()`.

> **NOTE**  The `show()` methods return immediately and do not wait for the popup to be closed.

If you need to perform some action when the popup is closed, you can either attach a listener to it using the `addListener()` method, override a method of the popup contents such as
[`PopupStep.onChosen()`](upsource:///platform/core-ui/src/openapi/ui/popup/PopupStep.java),
or attach an event handler to your own component within the popup.

