<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Drop-Down List

<link-summary>UI guidelines on using drop-down lists.</link-summary>

<tldr>

**Implementation:** [`ComboBox`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/ComboBox.java)

</tldr>

A drop-down list is a control that displays a list of choices on click and allows selecting one option.

![Example of a Theme drop-down with options: Light (selected in light), Dark (selected in dark), Light with Light Header, High Contrast, Darcula, and an option to get more themes](dropdown.png){width=706}

## When to use

### Single option

Use when a single option should be selected.

![Interface language drop-down with options: English (selected), German, Dutch, Chinese (Simplified), and an option to get more languages](dropdown_when_to_1.png){width="706"}

When multiple selections are possible, use [checkboxes](checkbox.topic).

### Options are objects or states

The possible choices are objects or states.

![Output level drop-down with options: Debug, Info (selected), Warn, Error, and Warning](dropdown_when_to_2.png){width=706}

When options are actions, use a menu or a [split button](split_button.md).

### More than four options

The number of options is more than four.

![Regions drop-down with options: Africa, Americas, Asia except China Mainland, Europe (selected), Middle East, Oceania](dropdown_when_to_3.png){width="706"}

When there are four options or fewer, use [radio buttons](radio_button.md).

### Space is limited

The screen space is limited, so there is not enough room for [radio buttons](radio_button.md).

![Settings | Appearence dialog that has a complex layout with multiple drop-downs: Theme, Zoom, and Custom font](dropdown_when_to_4.png){width="706"}

### Default value is the most used

The default value is recommended for most users. A drop-down is a good way to hide unpopular alternatives.

In the example bellow a drop-down is used because <control>Subpixels</control> is the most popular choice. Using [radio-buttons](radio_button.md)  puts all options on the same level of importance or popularity.

![Antialiasing drop-down with options: Subpixels (selected), Greyscale, Not antialiasing](dropdown_when_to_5.png){width=706}

### Combined controls

If a control combines various UI elements as one setting, use a drop-down even if there are four options or fewer:

![Combined control with a checkbox 'Automatically check updated for' and a drop-down with 'Early access program' selected, following by a button 'Check now'](dropdown_when_to_6.png){width=706}

## When not to use

### Four or less options

If there are four options or fewer, use [radio buttons](radio_button.md).

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_when_not_to_3_correct.png" alt="'Open project in' radio group with options: 'New window', 'Current window', and 'Ask'" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_when_not_to_3_incorrect.png" alt="'Open project in' drop-down with options: 'New window' (selected), 'Current window', and 'Ask'" width="378"/>
    </td>
  </tr>
</table>

### Multiple selection

If multiple selection is possible, use [checkboxes](checkbox.topic).

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_when_not_to_1_correct.png" alt="'UI Options' checkbox group with options: 'Smooth scrolling' (checked), 'Display icons in menu items' (checked), and 'Enable mnemonics in menu'" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_when_not_to_1_incorrect.png" alt="'UI Options' drop-down with options: 'Smooth scrolling' (checked), 'Display icons in menu items' (checked), and 'Enable mnemonics in menu'" width="378"/>
    </td>
  </tr>
</table>

For a [single selection](drop_down.md#single-option) use a drop-down.

### List of actions

If there is a list of actions, use a menu or a [split button](split_button.md).

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_when_not_to_2_correct.png" alt="Split-button 'Commit' and options: 'Commit and Push', 'Commit and Push Silently' (focused), and 'Create Patch'" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_when_not_to_2_incorrect.png" alt="Drop-down 'Commit' with options: 'Commit' (focused), 'Commit and Push', 'Commit and Push Silently', and 'Create Patch'" width="378"/>
    </td>
  </tr>
</table>

For a list of [objects or states](drop_down.md#options-are-objects-or-states), use a drop-down.

### Adding values

If users may need to enter a value not currently in the list, use a [combo box](combo_box.md).

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_when_not_to_4_correct.png" alt="A combo box for selecting font size and entering a custom value is open, displaying options ranging from 20 to 72. The current selection is 20" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_when_not_to_4_incorrect.png" alt="A dropdown menu for selecting font size is open, displaying options ranging from 20 to 72. The current selection is 20" width="378"/>
    </td>
  </tr>
</table>

### On a toolbar

If a drop-down appears on a toolbar, use [toolbar drop-down](toolbar_drop_down.md) instead.

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_when_not_to_5_correct.png" alt="Run/debug widget in the main toolbar with multiple icon buttons, and a toolbar drop-down with selected 'StandAlone' option" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_when_not_to_5_incorrect.png" alt="Run/debug widget in the main toolbar with multiple icon buttons, and a drop-down with selected 'StandAlone' option" width="378"/>
    </td>
  </tr>
</table>

## How to use

### Label

Follow the rules for the [input field](input_field.md#label).

![Drop-down menu labeled 'Placement' with the selected option set to 'Left'.](dropdown_how_to_1.png){width=706}

### Default value

Select the most likely or the safest value by default. In the xample bellow the safest value is <control>Ask</control>, the behavior will not be unexpected to users:

![Drop-down menu labeled 'Insert imports on paste' with options 'All', 'Ask', and 'None'. The selected option set to 'Ask'.](dropdown_how_to_2.png){width=706}

Do not use an empty value as the default value. If no values are added to a drop-down yet, replace it with a button to add values.

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_how_to_3_correct.png" alt="Secondary button 'Add Settings Repository' which opens a dialog" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_how_to_3_incorrect.png" alt="Drop-down 'Repository' with no selected option" width="378"/>
    </td>
  </tr>
</table>

### Menu items

#### Capitalization

Use [sentence-style capitalization](capitalization.md#sentence) for each menu item.

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_how_to_4_correct.png" alt="Drop-down menu labeled 'Default browser' with options 'System default', 'First listed', and 'Custom path'. The selected option set to 'System default'" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_how_to_4_incorrect.png" alt="Drop-down menu labeled 'Default browser' with options 'System Default', 'First Listed', and 'Custom Path'. The selected option set to 'System Default'" width="378"/>
    </td>
  </tr>
</table>

#### No repeated words in items

Avoid repeating words in drop-down list items. Move repeating words to the label or after the drop-down.

<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="dropdown_how_to_5_correct.png" alt="Drop-down menu labeled 'Refresh every [selection] minutes' with options '10', '15', and '30'. The selected option set to '10'" width="378"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="dropdown_how_to_5_incorrect.png" alt="Drop-down menu labeled 'Refresh every' with options '10 minutes', '15 minutes', and '30 minutes'. The selected option set to '10 minutes'" width="378"/>
    </td>
  </tr>
</table>

#### Sorting items

Sort items in one of the following orders:

<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="dropdown_how_to_6_1.png" alt="Drop-down menu labeled 'Placement' with options 'Left', 'Top', 'Right', and 'Bottom'. The selected option set to 'Top'"/>
    </td>
    <td>
      <p>Logical order, for example, in a spatial relationship.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="dropdown_how_to_6_2.png" alt="Drop-down menu labeled 'Global SQL dialect' with options '<None>', '<Generic SQL>', 'ClickHouse', 'DB2', 'Derby', 'Exasol', and other options available when scrolling. The selected option set to '<None>'"/>
    </td>
    <td>
      <p>Alphabetical or numeric order if the options are equivalent to make it easier to find items.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="dropdown_how_to_6_3.png" alt="Drop-down menu labeled 'Global encoding' with multiple options grouped in two groups and divided: popular options and the rest"/>
    </td>
    <td>
      <p>Place the most common options first. If there are more than 10 options, separate the most popular options with a line.</p>
      <p>Sort the remaining items in alphabetical or numeric order.</p>
    </td>
  </tr>
</table>

#### Grouping

Group related items using separators. Add group headers if possible.

<table style="none" border="false">
    <tr>
        <td width="50%">
            <format style="bold">With dividers</format>
            <img src="dropdown_how_to_7_1.png" alt="Drop-down menu labeled 'Version' with options ranging from 1.5 to 1.9, and a meta-option 'Same as language version'" width="372"/>
        </td>
        <td width="50%">
            <format style="bold">With a divider and group headers</format>
            <img src="dropdown_how_to_7_2.png" alt="Drop-down menu labeled 'Scheme' with two groups of options that have group titles and divided by a divider'" width="372"/>
        </td>
    </tr>
</table>

#### Actions related to a drop-down

If there are actions related to the drop-down, put them under the gear icon on the right:

![Drop-down menu 'Color Scheme' followed by a Settings icon-button related to the drop-down](dropdown_how_to_8.png){width=706}


#### Meta-options

<note>Meta-option is a special choice that controls how selections behave rather than being a specific value.</note>

Enclose meta-options in pointy brackets and place them at the beginning of the list when they're important or popular options. In other cases, place them at the end of the list.

![Drop-down menu labeled 'Run tests with' with options 'Platform test runner', 'Gradle test runner', and '<Choose per test>'. The selected option set to '<Choose per test>'](dropdown_how_to_9.png){width=706}

Do not assign special meanings to numeric or string values, use meta-options instead. It can be unclear that either 0 or an empty string are used to disable an option.

<table style="none" border="false">
    <tr>
        <td width="50%">
            <format color="Green" style="bold">Correct</format>
            <img src="dropdown_how_to_10_correct.png" alt="Drop-down menu labeled 'Version' with options ranging from 1.5 to 1.9, and a meta-option 'Same as language version'" width="372"/>
        </td>
        <td width="50%">
            <format color="Red" style="bold">Incorrect</format>
            <img src="dropdown_how_to_10_incorrect.png" alt="Drop-down menu labeled 'Version' with options ranging from 1.5 to 1.9, and an option '0'" width="372"/>
        </td>
    </tr>
</table>

#### 'None' in simple layouts

Replace the <control>&lt;None&gt;</control> option with a checkbox if it doesn't make the layout more complex.

<table style="none" border="false">
    <tr>
        <td width="50%">
            <format color="Green" style="bold">Correct</format>
            <img src="dropdown_how_to_11_correct.png" alt="Checkbox 'Show close button at' followed by two radio buttons 'Right' and 'Left'" width="372"/>
        </td>
        <td width="50%">
            <format color="Red" style="bold">Incorrect</format>
            <img src="dropdown_how_to_11_incorrect.png" alt="Drop-down 'Close button position' with options 'Left', 'Right', '<None>'. The selected options is '<None>'" width="372"/>
        </td>
    </tr>
</table>

#### Use icons and font formatting

<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="dropdown_how_to_12_1.png" alt="Drop-down 'Run on' with multiple options that have icons related to their meanings"/>
    </td>
    <td>
      <p>Add icons to the values if they're well-known.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="dropdown_how_to_12_2.png" alt="Drop-down 'Font' with multiple options that are showed in different fonts"/>
    </td>
    <td>
      <p>Use font formatting for a list of fonts. It will help users make a choice.</p>
    </td>
  </tr>
</table>

#### Default and changed values

<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="dropdown_how_to_13_2.png" alt="Drop-down 'Editor color scheme' with multiple options. Option 'Light (Theme default)' has 'Light' in blue color and 'Theme default' in grey color"/>
    </td>
    <td>
        <ul>
            <li>Use a <format color="#3574F0">blue</format> font for values changed from the default.</li>
            <li>Use a <format color="#787878">secondary text</format> to mark an option as default.</li>
        </ul>
    </td>
  </tr>
</table>

#### Submenus are not supported

Submenus are not supported for the drop-down list. Use a tree view instead

![Drop-down 'Keymap' with multiple options in a tree view showing dependencies in options](dropdown_how_to_14.png){width=706}

## Built-in behaviour

#### Opening the menu

A drop-down menu opens on clicking the drop-down button anywhere or pressing the <shortcut>Down</shortcut> key when the drop-down is
focused.

The menu opens down by default. If there is not enough space, the menu opens up.

#### Selected value

When the menu opens, the default option (displayed when the menu is closed) is selected.

#### Moving the selection

Selection in the menu is moved by pressing <shortcut>Up</shortcut> and <shortcut>Down</shortcut> keys when an item is hovered. Selection is moved as well when hovering the cursor over an item but it doesn't update the value.

#### Closing the menu

The menu remains opened until the user selects an item, clicks outside the menu, presses the <shortcut>Esc</shortcut> key, or switches to another app.

#### Single-click activation

A dropdown should work with a single mouse click:
1. Clicking to open a drop-down.
2. Hover over an item to select it.
3. Release the mouse to confirm the selection and close the dropdown.

## How to layout

Follow the [labeled input controls](layout.md#labeled-input-controls) guideline.
