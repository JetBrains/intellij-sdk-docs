<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Toolbar

<link-summary>UI guidelines on using toolbars.</link-summary>


<tldr>

**Related:** [](basic_action_system.md#buildingToolbarPopupMenu)

</tldr>

A toolbar provides quick access to frequently used actions and settings. It appears in dialogs, pop-ups, tool windows, lists, trees, and tables.

![](toolbar.png){width=706}

## Toolbar items

![](toolbar_items.png){width=706}

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

Add actions and settings that are frequently used:
* If usage statistics are available, frequent use is more than 5% of this feature's users. <!--REWRITE-->
* If usage statistics are not available:
  * Add items that are needed for the most common use cases of this feature.
  * If possible, add usage statistics logging for toolbar items without it. Check statistics after 3–4 weeks of use. If the items are used by less than 5% of the feature's users, consider removing them from the toolbar.

Exception: some actions can be used most frequently because they are widely known, like the Copy and Paste actions. Place such actions on the toolbar only if you want to promote them. Otherwise, place them only in a context menu or the main menu.

<!--TODO EXAMPLE-->


## How to use

### Location

Toolbars can be horizontal or vertical. Which type to use, depends on what control the toolbar is used for.

#### Lists, trees, tables

Use horizontal toolbars for lists, trees, and tables that appear in dialogs and popups. Place the toolbar at the top of the control.

![](toolbar_dialogs_horizontal.png){width=706}

#### Horizontal tool windows

Use horizontal toolbars in tool windows that are vertical by default.

![](toolbar_vertical_tool_window.png){width=706}

#### Vertical tool windows

Use vertical toolbars for tool windows that are horizontal by default. Place the toolbar on the left in a tool window.

![](toolbar_horizontal_tool_window.png){width=706}

<br/>

If a horizontal tool window has several sections, it is possible to place the toolbar on the right in sections after the first one.

![](toolbar_horizontal_tool_window_at_right.png){width=706}

<br/>

It is possible to use a horizontal toolbar in a horizontal tool window in the two cases:
* The toolbar contains items that are likely to be used all the time, like the <control>Step In</control> and <control>Step Out</control> actions in the <control>Debug</control> tool window. A horizontal toolbar makes the actions more noticeable and easy to reach.
* The toolbar contains items other than simple icons: a drop-down list, search field, button, or others.

![](toolbar_horizontal_tool_window_hor_toolbar_debug.png){width=706}


![](toolbar_horizontal_tool_window_hor_toolbar_git.png){width=706}


### Items alignment
On a vertical toolbar, always top-align items.

On a horizontal toolbar:
* Left-align items by default.
* Right-align the less frequently used items.

![](toolbar_items_alignment.png){width=706}


### Items grouping
Group related toolbar items and separate groups with lines.

![](toolbar_items_grouping.png){width=706}

<br/>

If there are several unrelated icons, do not add separators after each icon. Separators could be entirely omitted if there are 5 and fewer icons on a toolbar.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="toolbar_separators_correct.png" alt="" width="378"/>
        </td>
        <td><format color="E55765" style="bold">Incorrect</format>
            <img src="toolbar_separators_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

#### Label

Label a group of icons if it helps to make the icons clearer and if it’s needed to promote the icons. The icons group becomes more visible with the label.

![](toolbar_items_grouping_with_label.png){width=706}


### Items order
Arrange separate items or groups in the following order by default.

If usage statistics are available, and popular items appear not at the top or left of a toolbar, consider arranging by the frequency of use.

![](toolbar_items_order.png){width=706}


### Toolbar overflow
A toolbar's width is usually limited by the element it belongs to. If there is not enough space for items on the toolbar, they are hidden under a <control>chevron</control>.

It is recommended to keep the number of toolbar items to what would be visible by default. Otherwise, the items will be hidden and it will be hard to discover them.

![](toolbar_overflow.png){width=706}


#### Avoid two toolbars
Do not counter a toolbar overflow by adding more toolbars. One toolbar should be enough for the most popular actions.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="more_toolbars_correct.png" alt="" width="378"/>
        </td>
        <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="more_toolbars_incorrect.png" alt="" width="378"/>
        </td>
    </tr>
</table>


### Toolbar separator

For scrollable content in <control>tool windows</control>, do not show a toolbar separator if the content at the top scroll position. Show the separator once the content is scrolled under the toolbar.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <img src="toolbar_scroll_top.png" alt="" width="378"/>
        </td>
        <td>
            <img src="toolbar_scroll_under.png" alt="" width="378"/>
        </td>
    </tr>
</table>


In places other than tool windows, it is fine to always show the separator.


## Customization

Provide the ability to customize toolbars in the main window. To do this, add the toolbar to the list on the <ui-path>Settings | Appearance & Behavior | Menus and Toolbars</ui-path> page.

