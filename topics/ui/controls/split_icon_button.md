<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Split Icon Button

<link-summary>UI guidelines on using split icon buttons.</link-summary>

<tldr>

**Implementation:** [`SplitButtonAction`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/SplitButtonAction.java)

</tldr>

A split icon button appears on a horizontal toolbar and consists of two parts: the main icon and a triangle icon for the action list.

![](split_icon_button_example.png){width=278}

## When to use

The split icon button helps reduce the number of icons on the toolbar and minimize visual noise.

Use the split icon button for a group of similar actions if there are already many icons on the toolbar and:

* The user invokes one action more often than others,

* Or if the user invokes one action several times, then switches to another action and invokes it several times but does not switch between actions too often.

![](group_actions.png){width=325}

*Profiler actions are grouped into a split button*

If the user invokes actions with the same frequency or often switches from one action to another, use separate icons for each action.

To decide whether to add the whole group of actions on the toolbar, follow the rules for the [toolbar](toolbar.md#what-items-to-add-on-toolbar).

Use the split icon button on horizontal toolbars only, as it’s too wide for vertical toolbars.

## How to use

Make sure that all actions in the popup menu have icons. When an action is launched, its icon will be used as the main icon.

![](behavior.png){width=325}

*The user clicks Profile Allocations, the action starts, and its icon is shown as the main icon.*

## Built-in behavior

On hover, the main icon and the triangle icon are highlighted separately, and the line between two parts is added.
The tooltip for the main icon action is shown on hovering over it.

![](split_icon_button_hover.png){width=192}

The main icon invokes its action on click. The triangle icon opens the action menu on click:

![](click.png){width=325}

If some actions are unavailable, the corresponding menu items are disabled. The action menu should always open, even if all the items in it are disabled.

![](split_icon_button_disabled.png){width=325}

