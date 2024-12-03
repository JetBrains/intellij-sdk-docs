<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Combo Box

<link-summary>UI guidelines on using combo boxes.</link-summary>

<tldr>

**Implementation**: [`ComboBox`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/ComboBox.java)

</tldr>

A combo box combines a [drop down list](drop_down.md) and an [input field](input_field.md), allowing the user to select a value from the list or enter a custom value.

![A preview of a combo box](combobox.png){width=706}

Use [`ComboBox`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/ComboBox.java) instead of `javax.swing.JComboBox`.
To make combo box editable invoke:

```java
comboBox.setEditable(true);
```

## When to use


### Select value or enter custom

When the user needs to select a value or enter a custom value, use the combo box:

![A font size  that allows selecting predefined values as well as entering a custom value](combobox_when_to_use_1.png){width=706}

### Return to previous states
Use a combo box if users may need to revisit previous values, such as saving entered paths for quick selection later:

![A  with an open menu with previously selected values](combobox_when_to_use_2.png){width=706}

## When not to use

### The number of options is finite

If the number of options is finite, and there is no need to enter custom values, use a [dropdown list](drop_down.md) or [radio buttons](radio_button.md):

![The Theme dropdown with a predefined list of values](combobox_when_not_to_use_1.png){width="706"}

### Impossible to list values

If it is not possible to list the most likely choices, use an [input field](input_field.md) instead:

![A port number input](combobox_when_not_to_use_2.png){width=706}

### Large list with expected values

If the list is big, and the user knows what value they need and won’t review the list, use an input field with completion:

![An input field with a completion popup](combobox_when_not_to_use_3.png){width=706}

## How to use

### Label and default value

For the [label](drop_down.md#label) and the [default value](drop_down.md#default-value) apply the same rules as for the
[dropdown list](drop_down.md).

### Initial state

If there are no values in the list initially, replace the combo box with an input field.
This way, users won't waste their time clicking the empty combo box to find out that there are no options available.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="combobox_how_to_use_1_incorrect.png" alt="An incorrect example of a repository input field without values"/>
    </td>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="combobox_how_to_use_1_correct.png" alt="A correct example of a repository input field without values"/>
    </td>
  </tr>
</table>

Replace the input field with a combo box after a value has been entered and confirmed:

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="combobox_how_to_use_2_incorrect.png" alt="An incorrect example of a repository input field without values"/>
    </td>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="combobox_how_to_use_2_correct.png" alt="A correct example of a repository input field without values"/>
    </td>
  </tr>
</table>

### Menu items

#### Predefined options

Show the predefined or most likely options in the list. Follow the rules for [dropdown menu items](drop_down.md#menu-items).

#### Previous inputs

If the user needs to return to previous inputs, add such values to the end of the list when the confirmation button in the dialog is clicked:

![A combo box with previously entered values in the menu](combobox_menu_items_1.png){width=706}

#### Built-in button

Use [built-in buttons](built_in_button.md) to add values or expand the combo box, e.g., the browse button:

![A combo box with the browse built-in button](combobox_menu_items_2.png){width=706}

## Validation

If the user enters an invalid value, highlight the combo box with red and show an error message in a tooltip. For
more details, see [validation errors](validation_errors.md).

![A combo box with an error message](combobox_validation.png){width=706}

## How to layout

Follow the [labeled input controls](layout.md#labeled-input-controls).

## Built-in behaviour

### Opening the menu

The combo box menu opens by clicking on the arrow button on the right or pressing the <shortcut>Down</shortcut> arrow key when the combo box is focused.
   The menu opens down by default. If there is not enough space, the menu opens up.

### Selected value

The value that was shown in the closed combo box is selected in the open menu. A value in the list is not selected if a custom value was entered.

### Moving the selection

The selection is moved and the value in the combo box is updated by pressing the <control>Up</control> and <control>Down</control> arrow keys. When using a mouse, the selection is changed on mouse hover, the value is updated by clicking the mouse button or pressing <shortcut>Enter</shortcut>.

### Closing the menu

The menu remains opened until the user clicks the item in the list, presses <shortcut>Enter</shortcut> or <shortcut>Esc</shortcut>, clicks outside the menu,
   or switches to another app.
