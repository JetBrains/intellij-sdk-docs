<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Input Field

<link-summary>UI guidelines on using input fields.</link-summary>

<tldr>

**Implementation:** [`JBTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTextField.java)

</tldr>

An input field allows users to enter or edit a text line using the keyboard.

![](input_field_example.png){width=170}

## When to use

Use an input field if it’s not possible to enumerate the most likely values. Otherwise, use a [combo box](combo_box.md) or a [drop-down list](drop_down.md).

If input has to be in a specific format, use one of the following controls:

* If the previous user input must be preserved, use a [combo box](combo_box.md).
* Use a [text area](text_area.md) for long (commit message) or multi-line (code snippet) input. If place is constrained, use an [expandable input field](#input-field-types).
* Use a slider if a precise value is not required, or if it’s possible to provide feedback on the effect of setting changes. If place is constrained, use an input field.
* Use a [search field](search_field.md) to input a search query.
* Use calendar to set a date.
* Use color box to choose a color.

## How to use

### Label

A label accompanies each input field and indicates the information type.

Labels should be [short and descriptive](writing_short.md).

Write the label either as a noun and end it with a colon:

![](label_noun.png){width=153}

Or as a phrase with no ending punctuation:

![](label_sentence.png){width=247}

Do **not** use labels to tell users what to do:

![](user_action.png){width=186}

Use sentence-style capitalization.

If there are several input fields on a form, it’s recommended to make labels approximately the same length to avoid gaps between labels and fields. For example:

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="several_labels_length.png" alt="" width="385" /></td>
        <td><img src="several_labels_length_1.png" alt="" width="224" /></td>
    </tr>
</table>

If an input field is disabled, disable the label too:

![](label_disabled.png){width=153}

Make the label text selectable. The user may want to search for this option on the Internet or to send a question to support.

Place the label on the left or above the input field. For more details, see the [Layout](layout.md) topic.

### Placeholder

Placeholder is grey text placed inside an input field. Follow these rules:

* Use sentence-style capitalization.
* Do **not** use ending punctuation or ellipsis.
* Hide the placeholder when the user starts typing, not when the input field gets the focus.

To show placeholder text, use `JBTextField.getEmptyText().setText(...)`.

Use the placeholder to indicate that an input field is optional.

![](placeholder_optional.png){width=397}

Use the placeholder to show the default value:

![](placeholder_default.png){width=247}

If the user overwrites the value, it can be restored by removing the new value from the input field or by clicking the "Reset to default" link on the right:

![](placeholder_reset.png){width=361}

Do **not** use the placeholder to show examples. The user can get the impression that the field is already filled. Provide examples or explanation under the input field (see [Context help](context_help.md)):

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="placeholder_examples.png" alt="" width="150" /></td>
        <td><img src="placeholder_examples_1.png" alt="" width="150" /></td>
    </tr>
</table>

Do **not** use the placeholder as the field label. After the field has been filled, it is difficult to understand its purpose.

![](placeholder_label.png){width=100}

### Prefilled value

Pre-fill the field if it has the default or a frequently used value. Use the default text color for pre-filled values:

![](prefill.png){width=152}

Do **not** prefill with "Unnamed". It takes time to read it and does not help the user to fill the form.

![](prefill_unnamed.png){width=535}

### Field focus

When an input field gets the focus, place the caret at the end of the text:

![](focus_end.png){width=321}

If users are more likely to re-enter the entire value, select the whole text when the field gets the focus:

![](focus_all.png){width=274}

### Input field types

If the input text can be long and the place is constrained, use an expandable input field [`ExpandableTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/fields/ExpandableTextField.java).
For more details, see [built-in buttons](built_in_button.md#expand-a-field).

![](expandable_1.png){width=332}

If input data is secured, replace it with dots via [`JBPasswordField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBPasswordField.java).

![](password.png){width=271}

If there are many predefined values (for example, code snippets, commit author), add completion to the input field [`TextFieldWithCompletion`](%gh-ic%/platform/platform-impl/src/com/intellij/util/textCompletion/TextFieldWithCompletion.java).

![](input_field_completion.png){width=509}

An input field with completion looks the same way as a regular input field. When an empty input field gets the focus, show a tooltip after a delay to indicate that code completion is supported.

![](completion_tooltip.png){width=291}

Show the completion popup when the user starts typing or presses <shortcut>Ctrl+Space</shortcut>.

Use [built-in buttons](built_in_button.md) to help the user enter data. For example, to browse the disk.

### Validation

If the user enters an invalid value, highlight the field with red and show an error message in a tooltip. For more details, see [Validation errors](validation_errors.md).

![](input_field_error.png){width=239}

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
