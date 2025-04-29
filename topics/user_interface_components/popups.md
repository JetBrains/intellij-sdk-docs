<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Popups

<link-summary>Creating different kinds of popups.</link-summary>

The IntelliJ Platform user interface makes extensive use of popups - semi-modal windows that have no chrome (explicit closing buttons) and disappear automatically on focus loss.
Making use of these controls in your plugin ensures a consistent user experience between your plugin and the rest of the IDE.

Popups can optionally display a title, are optionally movable and resizable (and support remembering their size), and can be nested (show another popup when an item is selected).

The [`JBPopupFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java) interface allows you to create popups that display different kinds of components, depending on your specific needs.
The most commonly used methods are:

| Method                          | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|---------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `createComponentPopupBuilder()` | <p>Generic, allows showing any [Swing](https://docs.oracle.com/javase/tutorial/uiswing/) component.</p><p>See [`ComponentPopupBuilder`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/ComponentPopupBuilder.java)'s methods for possible options.</p><p>**Example:** [`IntentionPreviewPopupUpdateProcessor`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/intention/impl/preview/IntentionPreviewPopupUpdateProcessor.kt) creating a popup rendering the intention preview.</p> |
| `createPopupChooserBuilder()`   | <p>For choosing one or more items from a plain `java.util.List`.</p><p>See [`PopupChooserBuilder`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/PopupChooserBuilder.java)'s methods for possible options.</p><p>**Example:** [`ShowMessageHistoryAction`](%gh-ic%/platform/vcs-impl/src/com/intellij/openapi/vcs/actions/ShowMessageHistoryAction.kt) creating a popup with recent commit messages history in the commit message text area.</p>                                            |
| `createConfirmation()`          | <p>For choosing between two options, and performing different actions depending on which option is selected.</p><p>**Example:** [`VariableInplaceRenamer`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/inplace/VariableInplaceRenamer.java) creating confirmation popup after invalid variable name is provided in the inplace rename action.</p>                                                                                                                                          |
| `createActionGroupPopup()`      | <p>Show actions from an [Action Group](grouping_actions_tutorial.md) and executes the action selected by the user.</p><p>**Example:** [`ShowRecentFindUsagesGroup`](%gh-ic%/platform/lang-impl/src/com/intellij/find/impl/ShowRecentFindUsagesGroup.java) invoked via <ui-path>Edit / Find Usages / Recent Find Usages</ui-path> and showing recent find usages group popup.</p>                                                                                                                              |

### Action Groups

Action group popups support different ways of choosing an action from the keyboard, in addition to the normal arrow keys.
By passing one of the constants in the [`JBPopupFactory.ActionSelectionAid`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java) enumeration, you can choose whether an action can be selected by
- pressing a key corresponding to its sequential number
- typing part of its text (speed search)
- pressing a mnemonic character

For popups with a fixed set of items, the recommended selection method is sequential numbering.
For popups with a variable and potentially large number of items, speed search typically works best.

### List Popups

To create a list-like popup which is more flexible than a simple
[`JList`](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/JList.html)
and doesn't represent the possible choices as actions in an action group, use
[`JBPopupFactory.createListPopup()`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/JBPopupFactory.java)
with a
[`ListPopupStep`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/ui/popup/ListPopupStep.java)
implementation (usually, [`BaseListPopupStep`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/popup/util/BaseListPopupStep.java) is used as a base).
The key methods to override are:
- `getTextFor()` - returning the text to display for an item
- `onChosen()` - called when an item is selected

By returning a new popup step from the `onChosen()` method, you can implement hierarchical (nested) popups.

### Showing Popup

Once you've created the popup, you need to display it by calling one of the `show()` methods.
You can let the IntelliJ Platform automatically choose the position based on the context, by calling `showInBestPositionFor()`, or specify the position explicitly through methods like `showUnderneathOf()` and `showInCenterOf()`.

> The `show()` methods return immediately and do not wait for the popup to be closed.
>
{style="note"}

To perform some action when the popup is closed, use one of the following approaches:
- attach a listener to it using the `addListener()` method
- override a method of the popup contents such as [`PopupStep.onChosen()`](%gh-ic%/platform/core-ui/src/openapi/ui/popup/PopupStep.java)
- attach an event handler to your own component within the popup
