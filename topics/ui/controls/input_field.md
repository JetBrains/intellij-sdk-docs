<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Input Field

<link-summary>UI guidelines on using input fields.</link-summary>

<tldr>

**Implementation:** [`JBTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTextField.java)

</tldr>

An input field allows users to enter or edit a text line using the keyboard.

![](input.png){width=706}

## When to use

### Values can't be predefined

Use an input field if it’s not possible to enumerate the most likely values. Otherwise, use a [combo box](combo_box.md) or a [drop-down list](drop_down.md).

<table border="false" style="none">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
    </tr>
    <tr>
        <td><img src="input_when_to_correct.png" alt=""/></td>
        <td><img src="input_when_to_incorrect.png" alt=""/></td>
    </tr>
</table>

## Input field types

### Previous input is saved

If the previous user input must be preserved, use a [combo box](combo_box.md).

![](combobox_when_to_use_1.png){width=706}

### Large input

Use a [text area](text_area.md) for long or multi-line input.

![](input_text_area.png){width=706}

### Space is limited

If the place is constrained, use an expandable input field [`ExpandableTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/fields/ExpandableTextField.java).
For more details, see [Expand button](built_in_button.md#expand-a-field).

![](input_text_input_expand.png){width=706}

### Many predefined values

If there are many predefined values (for example, code snippets, commit author), add completion to the input field [`TextFieldWithCompletion`](%gh-ic%/platform/platform-impl/src/com/intellij/util/textCompletion/TextFieldWithCompletion.java).

![](input_completion.png){width=706}

An input field with completion looks the same way as a regular input field. When an empty input field gets the focus, show a tooltip after a delay to indicate that code completion is supported. Show the completion popup when the user starts typing or presses <shortcut>Ctrl+Space</shortcut>.

![](input_completion_tooltip.png){width=706}

### Built-in buttons

Use [built-in buttons](built_in_button.md) to help the user enter data. For example, to browse the disk.

### Search

Use a [search field](search_field.md) to input a search query.

![](input_search.png){width="706"}

### Password

If input data is secured, replace it with dots via [`JBPasswordField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBPasswordField.java).

![](input_password.png){width=706}

### Setting a date

Use calendar to set a date.

![](input_date_picker.png){width="706"}

### Choosing a color

Use color box to choose a color.

![](input_colour_box.png){width="706"}

## How to use

### Label

A label accompanies each input field and indicates the information type.

#### General rules

* Labels should be [short and descriptive](writing_short.md).
* Write the label either as a noun and end it with a colon.
* Don't use labels to tell users what to do.
* Use sentence-style capitalization.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_label_1_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_label_1_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

#### Label as a phrase

When writing a label as a phrase, don't use colon and ending punctuation.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_label_2_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_label_2_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

#### Grouped input fields

If there are several input fields in a form, it’s recommended to make labels approximately the same length to avoid gaps between labels and fields.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_label_3_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_label_3_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

#### Disabled state

If an input field is disabled, disable the label too.

![](input_disabled.png){width=706}

#### Selectable label

Make the label text selectable. The user may want to search for this option on the Internet or to send a question to support.

![](input_label_selected.png){width=706}

#### Positioning a label

Place the label on the left or above the input field. For more details, see the [Layout](layout.md) topic.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_positioning_label_1.png" alt="" width="378"/></td>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_positioning_label_2.png" alt="" width="378"/></td>
    </tr>
</table>

### Placeholder

Placeholder is grey text placed inside an input field. To show placeholder text, use `JBTextField.getEmptyText().setText(...)`.

#### General rules {id="placeholder-general-rules"}
* Use sentence-style capitalization.
* Don't use ending punctuation or ellipsis.
* Hide the placeholder when the user starts typing, not when the input field gets the focus.

#### Optional input field

Use the placeholder to indicate that an input field is optional.

![](input_placeholder_optional.png){width=706}

#### Default values

Use the placeholder to show the default value.

![](input_placeholder_default.png){width=706}

If the user overwrites the value, it can be restored by removing the new value from the input field or by clicking the <control>Reset to default</control> link on the right.

![](input_placeholder_default_reset.png){width=706}

#### Showing examples

Don't use the placeholder to show examples. The user can get the impression that the field is already filled. Provide examples or explanation under the input field (see [Context help](context_help.md)):

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_placeholder_example_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_placeholder_example_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

#### Don't use placeholders as labels

Don't use the placeholder as the field label. After the field has been filled, it is difficult to understand its purpose.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_placeholder_label_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_placeholder_label_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

### Prefilled value

Pre-fill the field if it has the default or a frequently used value. Use the default text color for pre-filled values:

![](input_prefilled.png){width=706}

Don't use <control>Unnamed</control> as a prefilled value. It takes time to read it and does not help the user to fill the form.

<table style="none" border="false">
    <tr>
        <td width="50%"><format color="Green" style="bold">Correct</format><img src="input_prefilled_unnamed_correct.png" alt="" width="378"/></td>
        <td width="50%"><format color="Red" style="bold">Incorrect</format><img src="input_prefilled_unnamed_incorrect.png" alt="" width="378"/></td>
    </tr>
</table>

### Field focus

When an input field gets the focus, place the caret at the end of the text:

![](input_focus.png){width=706}

If users are more likely to re-enter the entire value, select the whole text when the field gets the focus:

![](input_focus_selection.png){width=706}

### Validation

If the user enters an invalid value, highlight the field with red and show an error message in a tooltip. For more details, see [Validation errors](validation_errors.md).

![](input_validation.png){width=706}

## Sizes and placement

Sizes are the same for all themes:

![](input_field_sizes.png){width=65}

### Field width

Choose the width appropriate for the most common values, but not less than 65px. The field width helps the user understand what value is expected and to make sure that they fill the field correctly.

| <format color="Green" style="bold">Correct</format> | ![](input_field_size_1.png){width=104} |
|-----------------------------------------------------|----------------------------------------|
| <format color="Red" style="bold">Incorrect</format> | ![](input_field_size_2.png){width=240} |
| <format color="Green" style="bold">Correct</format> | ![](input_field_size_3.png){width=387} |
| <format color="Red" style="bold">Incorrect</format> | ![](input_field_size_4.png){width=331} |

{style=none}

If the input value is longer than the field width, show the beginning of the value when the field becomes inactive:

![](size_long_name.png){width=243}

### Placement

If the input field depends on another control, for example, a checkbox, follow the rules for (layout.md#dependent-controls). Otherwise, follow the rules for [independent controls](layout.md#independent-controls).

<!--
![](sizes_label.png){width=493}

![](sizes_button.png){width=246}

![](sizes_several.png){width=462}
-->
