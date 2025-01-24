<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Toggle Button

<link-summary>UI guidelines on using toggle buttons.</link-summary>

<tldr>

**Implementation:** see [](#control)

</tldr>

The toggle button is used to switch between On and Off states.

![](toggle_button_example.png){width=53}

## When to use

Use the toggle button to switch the state of an item in search results:

![](example_se.png){width=676}

Do not use the toggle button for items in dialogs and menus. Instead, use a checkbox in dialogs and a checkmark in menus:

![](when_to_use_dialog_or_menu.png){width=400}

## How to use

### Label

The toggle button in search results should duplicate the option from the settings or the menu.
Label and capitalization should be the same as on the option label:

![](label_checkbox.png){width=228 style=block}
*Setting in the preferences*

![](label_checkbox_se.png){width=676 style=block}
*The same setting in search results*

Do not make a setting available only from search results.

[//]: # (TODO: See [discoverability]&#40;discoverability.md&#41; for details.)

If the setting is in a tree or menu, use the toggle button label to specify where the setting is located:

![](label_tree.png){width=387 style=block}
*Setting in a tree*

![](label_tree_se.png){width=676 style=block}
*The same setting in search results; separate tree levels with a colon*

![](label_menu.png){width=497 style=block}
*Setting in the main menu*

![](label_menu_se.png){width=676 style=block}
*The same setting in search results; separate the first menu level with a vertical bar, and separate others with a colon*

Refer to [checkbox](checkbox.topic) for writing checkbox labels and menu labels.

[//]: # (TODO: and [menu]&#40;menu_list.md&#41;)

Do not add the word "On" or "Off" to the item name, since the state description is already in the toggle button.

### Control

A toggle button is implemented with the [`OnOffButton`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/OnOffButton.java) class.
But generally, you shouldn't use the class directly.
The IDE automatically places the buttons in the search feed if you follow one of the patterns described below:

1. If this is a system or editor or another kind of settings, register the corresponding [`BooleanOptionDescription`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/search/BooleanOptionDescription.java) for the option. The options can be bound (but not limited) to:

    - A [`SearchTopHitProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ide/SearchTopHitProvider.kt) instance which is registered in <path>plugin.xml</path> with the `<search.topHitProvider implementation="fq.class.name"/>` tag. For example, see the [`SystemOptionsTopHitProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/SystemOptionsTopHitProvider.java) class that represents matching of <control>Reopen last project on startup</control> checkbox
      to `BooleanOptionDescription`.

    - [`EditorOptionDescription`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/EditorOptionDescription.java) bound to [`EditorSettingsExternalizable`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/editor/ex/EditorSettingsExternalizable.java), which under the hood works with the <path>editor.xml</path>.
2. Implement your own action that's inherited from [`ToggleAction`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/ToggleAction.java) and registered in <path>plugin.xml</path>.

The toggle button changes state when it is clicked with the mouse or when <shortcut>Enter</shortcut> is pressed on the item line.
