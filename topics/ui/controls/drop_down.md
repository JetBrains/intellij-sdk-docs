<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Drop-Down List

<link-summary>UI guidelines on using drop-down lists.</link-summary>

<tldr>

**Implementation:** [`ComboBox`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/ComboBox.java)

</tldr>

A drop-down list is a type of button that appears in dialogs. It shows a list of choices on clicking it and allows selecting one option.

![](drop_down_example.png){width=134}

## When to use

Use a drop-down list if:

A single option should be selected.

The possible choices are objects or states.

![](output_level.png){width=182}

The number of choices is more than 4.

The screen space is limited, so there is not enough room for radio buttons.

The default value is recommended for the most users. A drop-down is a good way to hide unpopular alternatives.

![](antialiasing.png){width=228}

*"Subpixels" is the best choice for most users.*

If there are other drop-downs in the same window and these options are not more important than others. A list of radio buttons is more noticeable than the drop-down.

If a layout combines various UI elements for one setting, use a drop-down even if there are 4 options or fewer:

![](complex_layout.png){width=523}

### When not to use

If multiple selections are possible, use checkboxes.

If there is a list of actions, use a menu or a split button:

![](drop_down_menu_button.png){width=136}

If there are 4 options or fewer, use radio buttons.

![](radio_buttons.png){width=255}

If users may need to enter a value not currently in the list, use a combo box.

![](combo_box_font_size.png){width=165}

If a drop-down appears on a toolbar, use [toolbar drop-down](toolbar_drop_down.md) instead.

![](toolbar_main.png){width=209}

## How to use

### Label

Follow the rules for the [input field](input_field.md#label).

![](labels.png){width=490}

### Default value

Select the most likely or the safest value by default.

![](imports.png){width=298 style=block}
*The safest value is "Ask", the behavior will not be unexpected to the user.*

Do **not** use an empty value as the default value. If no values are added to a drop-down yet, replace it with a button to add values.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="drop_down_empty.png" alt="" width="221" /></td>
        <td><img src="button.png" alt="" width="190" /></td>
    </tr>
</table>

## Menu {id="menu_1"}

### Control

A drop-down menu opens on clicking the drop-down button anywhere or pressing the Down key when the drop-down is
focused.
The menu opens down by default. If there is not enough space, the menu opens up.

When the menu opens, the default option (displayed when the menu is closed) is selected.

Move the selection in the menu by pressing Up and Down arrows when an item is hovered. On hovering the cursor over the item just move selection and do not update the value.

<!-- * Filter items in the list on typing:

    ![](filter.png)
-->

The menu remains opened until the user selects an item, clicks outside the menu, presses the <shortcut>Esc</shortcut> key or
switches to another app.

Allow single-click activation using mouse: click on a drop-down, the drop-down opens, select an item by hovering, close the drop-down with the new item selected by releasing the mouse button.

### Menu items

Use sentence-style capitalization for each menu item.

![](browser.png){width=251}

Avoid repeating words in drop-down list items. Move repeating words to the label or after the drop-down.

![](refresh_changes.png){width=274}

Sort items in one of the following orders:

* Logical order, for example, in a spatial relationship:

  ![](order_logical.png){width=172}

* Alphabetical or numeric order if the options are equivalent to make it easier to find items:

  ![](order_alphabetical.png){width=267}

* Place the most common options first. If there are more than 10 options, separate the most popular options with a line:

  ![](order_popular.png){width=254}

Group related options, add a separator and group header if possible:

![](drop_down_group.png){width=216}

If there are actions related to the drop-down, put them under the gear icon on the right:

![](scheme.png){width=336}

Enclose meta-options in pointy brackets and place meta-options at the beginning or at the end if they are secondary.

![](run_tests.png){width=263}

*<control>&lt;Choose per test></control> is a meta-options because it’s not a real runner.*

Do **not** assign special meanings to numeric or string values, use meta-options instead. It can be unclear that either 0 or an empty string are used to disable an option.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="version_incorrect.png" alt="" width="262" /></td>
        <td><img src="version_correct.png" alt="" width="262" /></td>
    </tr>
</table>

  <p><em>In the incorrect example an empty string is used to set <control>&lt;Same
  as language level&gt;</control>, which is unclear.</em></p>
  <p>Replace the <control>&lt;None&gt;</control> option with a checkbox if it does not make the layout more complex:</p>

<format color="Red" style="bold">Incorrect</format>

![](none_incorrect.png){width=288}

<format color="Green" style="bold">Correct</format>

![](none_correct.png){width=277}

Preview list items with images if they are associated with well-known icons. Use formatting for a list of fonts. It will help users make a choice.

![](preview.png){width=609}

Use **bold** font to show default values, <format color="#2600FF">blue</format> font for values changed from the default,
and <format color="#787878">grey</format> font to add a hint.

![](blue_text.png){width=188 style=block}
*Default and Darcula are bundled schemes, Default is blue because it’s customized.*

![](grey_text.png){width=529}

Submenus are not supported for the drop-down list. Use a tree view instead:

![](hierarchy.png){width=169}

## Sizes and placement

### Width

A drop-down width is fixed and does not change depending on the selected value.

![](drop_down_width.png){width=133}

A drop-down width should be enough to fit the longest option plus 20px, but not less than 72px.

![](width_sizes.png){width=157}

### Menu

The width of a drop-down menu can either equal the drop-down control width, or be wider to fit longer items.

![](menu_width.png){width=100}

Choose a list length that eliminates unnecessary vertical scrolling. The minimum height of the list with scrolling is 200px. Expand the list to 600px if space is not constrained and the list does not hide meaningful information under it, e.g., the information that can be important to make a choice.

![](menu_height.png){width=225}

[//]: # (TODO: For sizes inside the menu list see [Menu list]&#40;menu_list.md&#41;.)

### Placement

Follow the rules for the [input field](#placement).


