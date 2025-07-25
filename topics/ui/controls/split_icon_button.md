<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Split Icon Button

<link-summary>UI guidelines on using split icon buttons.</link-summary>

<tldr>

**Implementation:** [`SplitButtonAction`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/SplitButtonAction.java)

</tldr>

A split icon button appears on a horizontal toolbar and consists of two parts: the main icon and a triangle icon for the action list.

![](split_icon_button.png){width=706}

## When to use

Use the split icon button to reduce the number of actions on a toolbar if the following conditions are met:
* The toolbar is horizontal. The split icon button is too wide for vertical toolbars.
* There are already many controls on the toolbar.
* There is a group of similar actions.
* The user invokes one action more often than others. Or the user invokes one action several times, then switches to another action and invokes it several times but does not switch between actions too often.

<note>To decide whether to add the actions to the toolbar, follow the <a href="toolbar.md#what-items-to-add-on-toolbar">Toolbar</a> guidelines.</note>

## How to use

### Add icons to all menu actions
Make sure that all actions in the drop-down menu have icons. When an action is selected, its icon will be used as the main icon.

![](split_icon_button_main_action_icon.png){width=706}

### Disable unavailable actions
Do not hide the unavailable actions, show them as disabled to help users locate them in the future. The action menu should always open, even if all the items in it are disabled.

![](split_icon_button_disabled_item.png){width=706}

