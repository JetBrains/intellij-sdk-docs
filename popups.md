---
title: Popups
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Popups
-->

# {{ page.title }}

## Popups

The IntelliJ IDEA user interface makes extensive use of popups *  semi-modal windows that have no chrome (explicit closing buttons) and disappear automatically on focus loss. Making use of these controls in your plugin ensures a consisent user experience between your plugin and the rest of the IDE.

Popups can optionally display a title, are optionally movable and resizable (and support remembering their size), and can be nested (show another popup when an item is selected).

The JBPopupFactory interface allows you to create popups that display different kinds of components, depending on your specific needs. The most commonly used methods are:

*  createComponentPopupBuilder() is the most generic one, allowing you to show any Swing component in the popup.

*  createListPopupBuilder() creates a popup for choosing one or more items from a Swing JList.

*  createConfirmation() creates a popup for choosing between two options, and performing different actions depending on which option is selected.

*  createActionGroupPopup() creates a popup which shows the actions from an action group and executes the action selected by the user.

Action group popups support different ways of choosing an action from the keyboard, in additional to the normal arrow keys.
By passing one of the constants in the ActionSelectionAid enumeration, you can choose whether an action can be selected by pressing a key corresponding to its sequential number, typing part of its text (speed search) or pressing a mnemonic character.
For popups with a fixed set of items, the recommended selection method is sequential numbering;
for popups with a variable and potentially large number of items, speed search typically works best.

If you need to create a list-like popup which is more flexible than a simple JList but don't want to represent the possible choices as actions in an action group, you can work directly with the ListPopupStep interface and the JBPopupFactory.createListPopup() method.
Normally you don't need to implement the entire interface; instead, you can derive from the BaseListPopupStep class. The key methods to override are getTextFor() (returning the text to display for an item) and onChosen() (called when an item is selected).
By returning a new popup step from the onChosen() method, you can implement hierarchical (nested) popups.

Once you've created the popup, you need to display it by calling one of the show() methods.
You can let IntelliJ IDEA automatically choose the position based on the context, by calling showInBestPositionFor(), or specify the position explicitly through methods like showUnderneathOf() and showInCenterOf().
Note that the show() methods return immediately and do not wait for the popup to be closed. If you need to perform some action when the popup is closed, you can either attach a listener to it using the addListener() method, override a method of the popup contents such as PopupStep.onChosen(), or attach an event handler to your own component within the popup.

