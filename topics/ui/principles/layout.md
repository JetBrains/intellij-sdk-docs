<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Layout

<link-summary>UI guidelines on laying out the controls in dialogs.</link-summary>

Arrange UI controls in dialogs according to the rules below to help people locate the necessary settings faster and understand how they are related. <br/>

Independent controls:
- [Labeled input controls](#labeled-input-controls): fields, combo boxes, text areas, etc.
- [Checkboxes and radio buttons](#checkboxes-and-radio-buttons)
- [Buttons and links](#buttons-and-links)
- [Lists, trees, tables](#lists-trees-and-tables)


[Dependent controls ](#dependent-controls)— controls that depend on a parent control. <br/><br/>


Organize controls into easily readable groups with [vertical and horizontal insets](layout.md#organize-with-insets).

[//]: # (TODO: See [Dialog window]&#40;dialog_window.md&#41; for buttons and other controls at the bottom of a dialog.)



## Independent controls


### Labeled input controls

Labeled input controls are: [input field](input_field.md), [combo box](combo_box.md), [drop-down list](drop_down.md), [text area](text_area.md), and spinner.

By default, put input controls with labels of similar length on different lines and align their input boxes on the left side.

![](1_01_input_similar_labels.png)

Do **not** align input boxes on the left side if one label is twice as long as the other one or even longer.

![](1_02_input_different_labels_incorrect.png)

![](1_02_input_different_labels_correct.png)

If several related input controls have labels of up to 10 characters and their input boxes are short, organize them in two columns. Do **not** use more than two columns.

![](1_03_short_inputs.png)

If an input box is long, and the horizontal space is limited, place the label above the box. Otherwise, always put the label and the box on the same line.

![](1_04_input_top_label.png)


#### Separated by other controls

If there are two input controls with labels of similar length that are separated from each other by a single control, align their input boxes on the left side.

![](1_05_separated_by_one.png)

Align only the input boxes of the neighboring input controls. If there're several input controls on a page, and they are separated from each other by two or more other UI elements, do **not** align their input boxes.

![](1_06_separated_by_two.png)

If input groups are separated by a group of other UI controls, align only the boxes located within one group.

![](1_07_separated_groups.png)


#### Labels and right borders


Always left-align labels.

> This alignment is inconsistent with macOS guidelines, but the aim is to maintain consistency in all JetBrains IDEs on all supported platforms.
>
{style="note"}


![](1_08_label_alignment.png)

Align the right borders of input boxes that have a similar length. For alignment, use [built-in icons](built_in_button.md). Do **not** use buttons.

![](1_09_built_in_button.png)


### Checkboxes and radio buttons

By default, put independent checkboxes and radio button groups on different lines.

![](2_01_checkbox_radio_column.png)

If there is a group of 2–3 checkboxes with short labels (1–3 words), place them on the same line. The same rule applies to radio buttons. With this alignment, controls form a short sentence making it easier to understand their meaning compared to when they are split into several lines.

![](2_02_checkbox_radio_line.png)

When there is an input control on one line and a group of checkboxes or radio buttons on the next line, and their labels are of similar length (one is no more that 5 characters longer than the other), align the input box with the checkbox/radio button.

<p>If one label is much longer than the other, do <b>not</b> align these UI elements.</p>

![](2_03_align_group_labels.png)

If a checkbox group does not have a label, align it with other independent controls by the left side.

![](2_03_align_without_group_labels.png)

4 and more checkboxes can be arranged in columns:

* Arrange checkboxes with labels of up to 30 characters in 2 columns.

![](2_04_checkbox_2_columns.png)

* Arrange checkboxes with labels of up to 15 characters in 3 columns.

![](2_05_checkbox_3_columns.png)

Do **not** arrange radio buttons from one group in several columns. Splitting a group of radio buttons into two or more columns makes it unclear that all these radio buttons belong to the same setting.

![](2_06_radio_columns_incorrect.png)


### Buttons and links

Align an independent button or link to the left with other controls.

![](3_01_button_column.png)

![](3_01_link_column.png)

If there are 2–3 buttons or links with labels of up to 30 characters each, place them on one line.

![](3_02_button_line.png)

Do **not** arrange buttons or links in several columns. Such a layout takes more time to parse visually.

![](3_03_button_columns.png)


### Lists, trees and tables

Choose a control width such that most of the common values are visible. Take the whole width of the dialog if necessary.

![](4_01_table_full_width.png)

If the dialog containing the control is noticeably wider than the control itself, reduce the length of the control.

Do **not** put other independent controls to the right of a list, tree, or table.
They would look like dependent controls in the master-detail layout.

![](4_02_table_fixed_width.png)


If there are several lists, trees, or tables in a dialog, make them of the same width.

![](4_03_aligned_lists.png)


## Dependent controls

<p>Align controls according to the rules below to show that they are related.</p>

Place 2–3 related UI controls on the same line if each control takes up to 30 characters. This way the user needs to read just one line, and it is quicker to see that the controls depend on each other.

![](5_01_dependent_one_line.png)

<p>In all other cases, place interrelated controls on different lines:</p>

Main control: **labeled input control**.

* Align other dependent elements with the left border of the input box.

![](5_03_dependent_on_field_checkbox.png)

![](5_03_dependent_on_field_button.png)

* If an input’s label is long or the input box is very short, align by the label and add a horizontal inset.

![](5_04_dependent_on_field_long_label.png)

Main control: **checkbox** or **radio button**. Align dependent controls by the label.

![](5_02_dependent_on_checkbox.png)

When the main control or one of the dependent controls takes the whole width of a panel, left align all elements.

![](5_05_whole_width_text_area.png)
*The text area is the main control, the button is the dependent control.*

![](5_06_whole_width_checkbox.png)
*The checkbox is the main control, the table is the dependent control.*

When the main control takes the whole width of a panel, and there is one small dependent control (for example, a drop-down list), place this dependent control to the top-right corner, above the main control.

![](5_07_whole_width_top_right_corner.png)

[//]: # (TODO: See [Master-detail layout]&#40;masterdetail_layout.md&#41; for more information on how to lay out controls if they depend on lists or trees.)

See the [Inline help text](inline_help_text.md#placement) and [Tooltip](tooltip.md#question-mark-icon-for-help-tooltips) articles for details on how to arrange help information in dialogs.

If controls do **not** depend on each other, left-align them all. Otherwise, the user might think that controls are linked.

![](5_09_incorrect_alignment.png)
*The spinners do not depend on the top checkbox.*


## Organize with insets

Use vertical insets to break a list of controls into easily readable groups. Compare:

<p>Incorrect</p>

![](6_02_group_insets_incorrect.png)
*The list of controls is hard to scan quickly because the controls "stick" together.*

<p>Correct</p>

![](6_02_group_insets_correct.png)
*Scanning a list of controls becomes easier when vertical insets are added between the groups of controls.*

Treat insets with extra care and make sure that elements within a group are actually related. An unnecessary inset may create a false impression that the controls are grouped. This complicates the UI and might cause confusion.

<p>Incorrect</p>

![](6_04_inset_incorrect.png)
*The first checkbox depends on the combo box which is shown with the horizontal inset. However, the checkboxes appear grouped because they are closer.*

<p>Correct</p>

![](6_04_inset_correct.png)
*The first checkbox is closer to its main control and it is easier to see that the combo box and the checkbox are related.*

Horizontal insets also matter for grouping controls.

<p>Incorrect</p>

![](6_05_hor_inset_incorrect.png)
*The checkboxes and the "Length" fields look independent because the horizontal inset between them is bigger than the vertical inset below the second checkbox.*

<p>Better</p>

![](6_05_hor_inset_better.png)
*With a smaller horizontal and bigger vertical insets, the checkboxes and the "Length" fields look related. However, it could be made better if the repeating word “Separator” appears only once.*

<p>Correct</p>

![](6_05_hor_inset_correct.png)
*Correct grouping and no duplicates help understanding the UI quicker.*

See [Groups of controls](groups_of_controls.md) for how to organize a bigger group of controls.
