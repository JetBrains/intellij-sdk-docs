<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Toolbar

<link-summary>UI guidelines on using toolbars.</link-summary>


<tldr>

**Related:** [](basic_action_system.md#buildingToolbarPopupMenu)

</tldr>

A toolbar provides quick access to frequently used actions and settings. It appears in dialogs, pop-ups, tool windows, lists, trees, and tables.

![](toolbar_example.png){width=428}

## Toolbar items

The following controls can appear on a toolbar:

* [Icon button](icon_button.md): action, toggle, or drop-down

* [Split icon button](split_icon_button.md)

* [Toolbar drop-down list](toolbar_drop_down.md)

* [Search field](search_field.md)

* [Button](button.topic)

* Separator

* Label

* Chevron

![](items.png){width=704}

## What items to add on toolbar

Add actions and settings that are frequently used:
* If usage statistics are available, frequent use is more than 5% of this feature's users. <!--REWRITE-->
* If usage statistics are not available:
  * Add items that are needed for the most common use cases of this feature.
  * If possible, add usage statistics logging for toolbar items without it. Check statistics after 3–4 weeks of use. If the items are used by less than 5% of the feature's users, consider removing them from the toolbar.

Exception: some actions can be used most frequently because they are widely known, like the Copy and Paste actions. Place such actions on the toolbar only if you want to promote them. Otherwise, place them only in a context menu or the main menu.

<!--TODO EXAMPLE-->

### Make available from other places
<!--TODO REWRITE-->
Make every toolbar action available from the main menu or the context menu.
Toolbars are customizable and can be hidden, so items should be available from other places as well.


## How to use

### Location

Toolbars can be horizontal or vertical:
* For lists, trees, tables that appear in dialogs and popups, use horizontal toolbars.
* For tool windows, use horizontal toolbars in tool windows that are vertical by default, and vertical toolbars for tool windows that are horizontal by default. Otherwise, a toolbar would occupy too much space in a tool window.

![](placement.png){width=498}

#### Horizontal toolbars

Place horizontal toolbars at the top of the area.

![](placement_top.png){width=220}

By default, left-align items on a horizontal toolbar. Right-align the less frequently used items.

![](right_aligned.png){width=491}

#### Vertical toolbars
By default, place a vertical toolbar on the left in a horizontal tool window.

![](placement_left.png){width=220}

If a horizontal tool window has several sections, it's possible to place the toolbar on the right in sections after the first one.

![](placement_right.png){width=386}

Top-align items by default on a vertical toolbar.


### Items grouping
Group related toolbar items and separate groups with lines. If there are several unrelated icons, do not add separators after each icon.

<!--TODO EXAMPLE correct, incorrect-->

#### Label

Label a group of icons if it helps to make the icons clearer and if it’s needed to promote the icons. The icons group becomes more visible with the label.

![](toolbar_group.png){width=242}


### Items order
Arrange separate items or groups in the following order by default.

If usage statistics are available, and popular items appear not at the top or left of a toolbar, consider arranging by the frequency of use.

![](toolbar_tool_window.png){width=260}


### Toolbar overflow
If there is not enough space for items on a toolbar, they are hidden under a <control>chevron</control>. The toolbar width is usually limited by the width of the element it belongs to.

It is recommended to keep the number of toolbar items to what would be visible by default. Otherwise, the items will be hidden and it will be hard to discover them.

<!--TODO correct / incorrect examples-->

![](width_correct.png){width=283}


#### Avoid two toolbars
Do not put two toolbars next to each other. One toolbar should be enough for the most popular actions.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="toolbars_2.png" alt="" width="58" /></td>
        <td><img src="toolbars_1.png" alt="" width="30" /></td>
    </tr>
</table>

### Toolbar separator

For scrollable content in tool windows, do not show a toolbar separator if the content at the top scroll position. Show the separator once the content is scrolled under the toolbar.

In places other than tool windows, it is fine to always show the separator.

<!--TODO PIC-->


## Customization

Provide the ability to customize toolbars in the main window.
To do this, add the toolbar to the list on the <ui-path>Settings | Appearance & Behavior | Menus and Toolbars</ui-path> page and open
it using the <control>Customize Toolbar...</control> action from the toolbar context menu.

![](toolbar_customize.png){width=404}

