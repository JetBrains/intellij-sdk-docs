<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Checkbox

<link-summary>UI guidelines on using checkboxes.</link-summary>

<tldr>

**Implementation:** [`JCheckBox`](https://docs.oracle.com/javase/tutorial/uiswing/components/button.html), [`JBCheckBox`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBCheckBox.java)

</tldr>

![](checkbox_example.png){width=166}

## When to use

Use checkboxes for yes/no choices or for selecting several items in a group.

Do **not** use checkboxes if:
* Only one option in a group can be selected. Use a [radio button group](radio_button.md) instead.
* The behavior in the "off" state is unclear from the checkbox label. Use two radio buttons instead and label them accordingly.
![](when_to_use.png){width=365}
* With the checkbox, it is unclear how the setting works if it’s unchecked. With radio buttons, both states are labeled clearly.*


## How to use

### Label

A label accompanies each checkbox and is placed next to it.

![](checkbox_label.png){width=161}

If a label is long, split it into two lines. Use HTML formatting for that.

![](label_twoline.png){width=331}

```java
JCheckBox checkBox = new JCheckBox(
    "<html>Insert selected suggestion by  pressing space, dot,<br/>" +
    "or other context-dependent keys</html>");
```

Avoid labels that take more than two lines. See recommendations on writing concise labels below.

If a checkbox appears in a table, place the label into the column header and do not repeat it on every row.

![](checkbox_table.png){width=347}

**Implementation**: Checkboxes are rendered in tables with [`BooleanTableCellRenderer`](%gh-ic%/platform/core-ui/src/ui/BooleanTableCellRenderer.java)
and edited with `DefaultCellEditor(JCheckBox)` implementation.
For any column that should be rendered as a checkbox, set both a renderer and editor for consistency.
The type of data in the correspondent column of the `Table` model should either be `Boolean` or `String` containing `true` or `false`.

```java
TableColumn column = table.getColumnModel().getColumn(...);
column.setCellEditor(JBTable.createBooleanEditor());
column.setCellRenderer(new BooleanTableCellRender());
```

### Writing guidelines

Use sentence-style capitalization.

Do not use ending punctuation.

Use the imperative form of verbs.

![](label_short.png){width=350}

Do not use negation in labels as it complicates understanding.
**Exception**: "Do not show again" checkbox.

![](label_answeryes.png){width=224}

Make labels short and intelligible — see [Writing short and clear](writing_short.md).


### Three-state checkbox

In a group of options, use the parent checkbox to show the status of its children.

![](indeterminate_checkbox.png){width=542}

*The parent checkbox in checked, indeterminate and unchecked states*
**Implementation**: The three-state checkbox is represented by the [`ThreeStateCheckBox`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/ThreeStateCheckBox.java)
class which represents its state with the `ThreeStateCheckBox.State` enum containing `SELECTED, NOT_SELECTED, DONT_CARE` states.

When the user clicks an indeterminate checkbox for the first time, the whole group becomes checked. The second click unchecks the whole group.

An indeterminate checkbox can also show the download status. An example with a remote repository:

![](indeterminate_status.png){width=358}

*Repositories "tools-base" and "contrib" are being loaded. When loading is finished, the indeterminate checkbox will be replaced with the checked checkbox if there are commits, or an unchecked checkbox if there are no commits.*

**Implementation**: In a table, the three-state checkbox is represented by [`ThreeStateCheckBoxRenderer`](%gh-ic%/platform/lang-impl/src/com/intellij/profile/codeInspection/ui/table/ThreeStateCheckBoxRenderer.java)
that provides both `TableCellRenderer` and `TableEditor`.
It accepts `Boolean` type in the column being supplied by the `TableModel` and becomes `DONT_CARE` when the value in the cell is null.
Otherwise, it becomes `SELECTED` for `Boolean.TRUE`, and `NOT_SELECTED` for `Boolean.FALSE`.

## Placement

If a checkbox depends on another control, e.g., an input field, follow the rules for [dependent controls](layout.md#dependent-controls). Otherwise, follow the rules for [independent controls](layout.md#independent-controls).

<!--
### Colors
<p> The color keys can be used only in a UI theme plugin. </p>

<table>
 <col width="50%">
      <tr>
         <td> Unchecked background </td>
         <td> Checkbox.Background.Default <br/>
              Checkbox.Background.Default.Dark </td>
     </tr>
     <tr>
         <td> Checked background </td>
         <td> Checkbox.Background.Selected <br/>
              Checkbox.Background.Selected.Dark
         </td>
     </tr>
     <tr>
         <td> Disabled background </td>
         <td> Checkbox.Background.Disabled <br/>
              Checkbox.Background.Disabled.Dark
         </td>
     </tr>
     <tr>
         <td> Unchecked border </td>
         <td> Checkbox.Border.Default <br/>
              Checkbox.Border.Default.Dark
         </td>
     </tr>
     <tr>
         <td> Checked border </td>
         <td> Checkbox.Border.Selected <br/>
              Checkbox.Border.Selected.Dark
         </td>
     </tr>
     <tr>
         <td> Disabled border </td>
         <td> Checkbox.Border.Disabled <br/>
              Checkbox.Border.Disabled.Dark
         </td>
     </tr>
     <tr>
          <td> Focused inner 1px border for unchecked state </td>
          <td> Checkbox.Focus.Thin.Default <br/>
               Checkbox.Focus.Thin.Default.Dark
          </td>
      </tr>
      <tr>
          <td> Focused inner 1px border for checked state </td>
          <td> Checkbox.Focus.Thin.Selected <br/>
               Checkbox.Focus.Thin.Selected.Dark
          </td>
      </tr>
      <tr>
          <td> Focused outer 2px border </td>
          <td> Checkbox.Focus.Wide <br/>
               Checkbox.Focus.Wide.Dark
          </td>
      </tr>
      <tr>
          <td> Checkmark fill </td>
          <td> Checkbox.Foreground.Selected <br/>
               Checkbox.Foreground.Selected.Dark
          </td>
      </tr>
      <tr>
          <td> Disabled checkmark fill </td>
          <td> Checkbox.Foreground.Disabled <br/>
               Checkbox.Foreground.Disabled.Dark
          </td>
      </tr>
</table>
-->
