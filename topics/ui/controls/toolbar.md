<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Toolbar

<link-summary>UI guidelines on using toolbars.</link-summary>


<tldr>

**Related:** [](action_system.md#buildingToolbarPopupMenu)

</tldr>

A toolbar provides quick access to frequently used actions and settings. It appears in dialogs, popups, tool windows, lists, trees, and tables.

![A toolbar with 6 icons and a drop-down list](toolbar.png){width=706}

## Toolbar items

![A toolbar with all kinds of UI controls that can appear on it: an action icon button, a drop-down icon button, a toggle icon button, a split icon button, a search field, a button, a label, a toolbar drop-down list and an overflow chevron](toolbar_items.png){width=706}

The following controls can appear on a toolbar:
* [Icon button](icon_button.md): action, toggle, or drop-down
* [Split icon button](split_icon_button.md)
* [Toolbar drop-down list](toolbar_drop_down.md)
* [Search field](search_field.md)
* [Button](button.topic)
* Separator
* Label
* Chevron

## What items to add on toolbar

### Frequently used items
Add actions and settings that are frequently used:
* If usage statistics are available, frequent use is more than 5% of this feature's users.
* If usage statistics are not available:
  * Add items that are needed for the most common use cases of this feature.
  * If possible, add usage statistics logging for toolbar items that lack it. Check statistics after 3–4 weeks of use. If the items are used by less than 5% of the feature's users, consider removing them from the toolbar.


<note>Exception: some actions can be used most frequently because they are widely known, like the Copy and Paste actions. Place such actions on the toolbar only if you want to promote them. Otherwise, place them only in a context menu or the main menu.</note>

### New items
When adding a new action or setting for a UI area that already has a toolbar: if the new item is expected to be used frequently, add it to increase its discoverability.


## How to use

### Location

Toolbars can be horizontal or vertical. Which type to use, depends on what control the toolbar is used for.

#### Lists, trees, tables

Use horizontal toolbars for lists, trees, and tables that appear in dialogs and popups. Place the toolbar at the top of the control.

![A horizontal toolbar with 5 icons above a tree](toolbar_dialogs_horizontal.png){width=706}

#### Vertical tool windows

Use horizontal toolbars in tool windows that are vertical by default.

![The Commit tool window with a horizontal toolbar above a tree with changes](toolbar_vertical_tool_window.png){width=706}

#### Horizontal tool windows

Use vertical toolbars for tool windows that are horizontal by default. Place the toolbar on the left in a tool window.

![The Problems tool window with a vertical toolbar on the left of the tree with file errors](toolbar_horizontal_tool_window.png){width=706}

<br/>

If a horizontal tool window has several sections, place a toolbar on the right in sections after the first one if these conditions are true:
* The sections are a master-detail layout, with a list or tree on the left and a selected item's details on the right.
* The toolbar contains actions that are used occasionally, like the <control>Soft-Wrap</control> setting in the <control>Console</control> section of the <control>Build</control> tool window.

This way the toolbar would not break the connection between the master and the detail sections.


![The Build tool window with the vertical toolbar on the right of the Console section](toolbar_horizontal_tool_window_at_right.png){width=706}

<br/>

A horizontal toolbar can be used in a horizontal tool window in the two cases:
* The toolbar contains items used for managing processes, like <control>Step In</control> and <control>Step Out</control> in the <control>Debug</control> tool window, or <control>Run</control> and <control>Stop</control> in the <control>Run</control> tool window. A horizontal toolbar makes the actions more noticeable and easy to reach.
* The toolbar contains items that need horizontal space, like a <control>search field</control> and <control>drop-down lists</control> in the <control>Git</control> tool window.

![The Debug tool window with a horizontal toolbar containing the Run, Stop, Resume, Pause, Step In, Step Out and other actions](toolbar_horizontal_tool_window_hor_toolbar_debug.png){width=706}


![The Git tool window with a horizontal toolbar containing a search field and four drop-down lists with filters for the list of commits](toolbar_horizontal_tool_window_hor_toolbar_git.png){width=706}


### Items alignment
On a vertical toolbar, always top-align items.

On a horizontal toolbar:
* Left-align items by default.
* Right-align the less frequently used items.

![The Keymap settings with a tree and a toolbar above it: the Expand, Collapse and Edit icon buttons are on the left side of the toolbar, and a search field with another icon button are on the right side](toolbar_items_alignment.png){width=706}


### Items grouping
Group related toolbar items and separate groups with lines.

![A toolbar with three icon groups: Build, Run, Debug and Profile are the Run actions group; Git Update, Push, Diff and two more are the Git actions group; the Project Structure and Settings are the Settings group](toolbar_items_grouping.png){width=706}

<br/>

If there are several unrelated icons, do not add separators after each icon. Separators could be entirely omitted if there are 5 and fewer icons on a toolbar.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="toolbar_separators_correct.png" alt="A toolbar with five icons without separators" width="378"/>
        </td>
        <td><format color="E55765" style="bold">Incorrect</format>
            <img src="toolbar_separators_incorrect.png" alt="A toolbar with five icons and three separators" width="378"/></td>
    </tr>
</table>

#### Label

Label a group of icons if it helps to make the icons clearer and if it’s needed to promote the icons. The icons group becomes more visible with the label.

![A horizontal toolbar with three icon groups, with a label for one of the groups](toolbar_items_grouping_with_label.png){width=706}


### Items order
Arrange separate items or groups in the following order by default.

If usage statistics are available, and popular items appear not at the top or left of a toolbar, consider arranging by the frequency of use.

![A vertical toolbar with icon categories ordered from top to bottom: actions, view options, an action to open a preview panel in a tool window, settings](toolbar_items_order.png){width=706}


### Toolbar overflow
A toolbar's width is usually limited by the element it belongs to. If there is not enough space for items on the toolbar, they are hidden under a <control>chevron</control>. Hovering the chevron shows the hidden items.

It is recommended to keep the number of toolbar items to what would be visible by default. Otherwise, the items will be hidden and it will be hard to discover them.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <img src="toolbar_overflow.png" alt="A horizontal toolbar above a tree with seven icons and an overflow chevron at the right" width="378"/>
        </td>
        <td>
            <img src="toolbar_overflow_hover.png" alt="The same UI as in the previous image but with the overflow chevron hovered: the full toolbar with nine icons becomes visible" width="378"/>
        </td>
    </tr>
</table>


#### Avoid two toolbars
Do not counter a toolbar overflow by adding more toolbars. If there are too many items on a toolbar:
* Group items under drop-down icon buttons
* Remove unpopular items by following the guidelines in [](#what-items-to-add-on-toolbar)

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="more_toolbars_correct.png" alt="A vertical toolbar with seven icons" width="378"/>
        </td>
        <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="more_toolbars_incorrect.png" alt="Two vertical toolbars stacked together with 19 icons in total" width="378"/>
        </td>
    </tr>
</table>


### Toolbar border

For scrollable content in <control>tool windows</control>, do not show a toolbar border if the content is at the top scroll position. Show the border once the content is scrolled under the toolbar.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <img src="toolbar_scroll_top.png" alt="A tree with a toolbar above it. The tree is at the top scroll position, the toolbar border is not visible." width="378"/>
        </td>
        <td>
            <img src="toolbar_scroll_under.png" alt="A tree with a toolbar above it. The tree is scrolled under the toolbar, the toolbar border is visible." width="378"/>
        </td>
    </tr>
</table>


In places other than tool windows, it is fine to always show the border.


## Customization

Provide the ability to customize toolbars in the main window. To do this, add the toolbar to the list on the <ui-path>Settings | Appearance & Behavior | Menus and Toolbars</ui-path> page.

