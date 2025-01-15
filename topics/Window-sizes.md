<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Window Sizes

<link-summary>Guidelines on choosing the correct size when creating a dialog or a popup</link-summary>

Follow these guidelines to select the correct size when creating a dialog or a popup for IntelliJ-based products.

For simple cases use [default sizes](Window-sizes.md#default-sizes). When they don't work well, set [minimum sizes for components](Window-sizes.md#minimum-sizes-for-components).

## Default sizes

### Dialog

There are four recommended window sizes for dialogs: **small**, **medium**, **large**, and **extra large**. Select the size depending on the amount of content to keep the important information visible.

![A preview of recommended window sizes for dialogs: extra small, small, medium, and large](dialog_popup_sizes.png){width="706"}

<note>A dialog can't be made smaller than the default size.</note>

#### Small

Size: 350×250 px.

Best for: several components that are stacked vertically.

![The Rename dialog with two input fields, two checkboxes, and actions in the dialog footer. The components are stacked vertically in one column](dialog_popup_sizes_extra_small.png){width="706"}

#### Medium

Size: 500×350 px

Best for:
* Multiple components with short labels that are divided into two columns.
* A full-width table with two or three columns.
* A full-width code snippet or input with long text.

For example, the <control>Evaluate Expressions</control> dialog has an input field with long text and a tree with code stacked vertically:

![The Evaluate Expressions dialog that has an input field with long text and a tree with code stacked vertically](dialog_popup_sizes_small.png){width="706"}

#### Large

Size: 750×525 px

Best for:
* A full-width table with four or more columns.
* A master-detail layout.
* Two-column layout with a code snippet or an input field with long text in any of the columns.
* Two code snippets or input fields with long texts in a row.
* Two-column layout with an image or a video in any of the columns. In this case, the image or the video takes the most space.

For example, the <control>Run/Debug Configuration</control> dialog has a master-detail layout with a tree in the first column and the list of controls with long texts in the second column:

![The Run/Debug Configuration dialog that has a two-column layout. There is a navigation in the first column and the list of settings in the second column](dialog_popup_sizes_medium.png){width="706"}

#### Extra Large

Size: 1000×700 px

Best for:
* Two-column layout with code snippets or input fields with long texts in both columns.
* Three-or-more-column layout regardless of the content.

For example, <control>Code Style</control> settings for Java in the <control>Settings</control> dialog. The dialog has three columns with a tree, a table, and a code snippet in different columns:

![The Code Style settings in the Settings dialog. The dialog has three columns with a tree, a table, and a code snippet in different columns](dialog_popup_sizes_large.png){width="706"}

#### Maximum width and height {id="maximum-width-and-height_dialog"}

Set the dialog's maximum dimensions to match screen size to prevent resizing beyond screen bounds.

### Popup

Popups should have the same default width as dialogs. In addition, there is an **extra small** size for popups – 200 px wide, when the content is narrow.

<note>A popup can't be made smaller than the default size.</note>

#### Action Popups

If a popup has a vertical list of actions or options, set a default width to either **extra small** (200 px) or **small** (350 px).

The width and height are adaptable based on the content if it exceeds the set dimensions.


<table style="none" border="false">
   <tr>
      <td width="50%">
         <p><control>Extra small</control></p>
         <img src="dialog_popup_sizes_popup_xxs.png" alt="Insert popup 200 px wide" width="378"/>
      </td>
      <td width="50%">
         <p><control>Small</control></p>
         <img src="dialog_popup_sizes_popup_xs.png" alt="Quick Fixes popup 350 px wide" width="378"/>
      </td>
   </tr>
</table>

#### Content-rich Popups

If a popup has several sections and features long texts, set a default width to **medium** (500 px) or **large** (750 px).

Height is adaptable based on the content. If a popup has a scrollable content, set the default height respectively: **medium** (350 px) or **large** (525 px).

<table style="none" border="false">
   <tr>
      <td width="100%">
         <p><control>Medium</control></p>
         <img src="dialog_popup_sizes_popup_adaptive_height.png" alt="Documentation popup 500 px wide" width="706"/>
      </td>
   </tr>
   <tr>
      <td width="100%">
         <p><control>Large</control></p>
         <img src="dialog_popup_sizes_popup_m.png" alt="Search Everywhere popup 750 px wide" width="706"/>
      </td>
   </tr>
</table>

When the default width doesn't work, set [minimum sizes for components](Window-sizes.md#minimum-sizes-for-components).

#### Maximum width and height

The maximum width and height shouldn't exceed 80% of a screen size. Use a [scrollbar](scrollbar.md) if a popup's content doesn't fit within the set dimensions.

### Tool windows

Default sizes:

* Vertical tool windows should take minimum 20% of the application width but not less than 200×500&nbsp;px.
* Horizontal tool windows should take minimum 20% of the application height but not less than 500×200&nbsp;px.

The default sizes should be set for all the states of the <control>View Mode</control>.

<note>A tool window can't be made smaller than the default size.</note>

For example, the vertical <control>Project</control> tool window and the horizontal <control>Build</control> tool window with minimum sizes in <control>Window</control> mode:

![Vertical Project and horizontal Build tool windows with the default sizes in Window mode](dialog_popup_sizes_tool_window.png){width="706"}


## Minimum sizes for components

When the default sizes are either too big or too small for a window, or the window has a complex layout, add minimum sizes to content inside the window. This will make the content displayed correctly and reduce any potential issues caused by resizing the window.

### How to set the size correctly

1. Select how the content is displayed.
2. What type of content is used.
3. Select the biggest size out of the two to add the minimum size to a component.

#### How the content is displayed

<table style="none" border="false">
   <tr>
      <td width="50%">
         <img src="dialog_popup_sizes_table_cell.png" alt="Example of a table with three cells" width="378"/>
      </td>
      <td>
         <p><format style="bold">Table cell</format></p>
         <p>Width: min&nbsp;65&nbsp;px</p>
         <p>Height for the whole table: min&nbsp;120&nbsp;px</p>
         <p>Follow guidelines for <a href="table.md" anchor="sizes">table sizes</a></p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_tree.png" alt="Example of a tree" width="378"/>
      </td>
      <td>
         <p><format style="bold">Tree</format></p>
          <p>Width: min 250&nbsp;px</p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_text_area.png" alt="Example of a text area" width="378"/>
      </td>
      <td>
          <p><format style="bold">Text area</format></p>
          <p>Width: min&nbsp;270&nbsp;px, max&nbsp;600&nbsp;px</p>
          <p>Height: min&nbsp;55&nbsp;px</p>
          <p>Follow guidelines for <a href="text_area.md" anchor="size-and-placement">text area sizes</a></p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_diagram.png" alt="Example of a diagram" width="378"/>
      </td>
      <td>
          <p><format style="bold">Diagram</format></p>
          <p>A container with horizontal and vertical insets with 100&nbsp;px</p>
      </td>
   </tr>
</table>

#### Types of content

<table style="none" border="false">
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_class_test_file.png" alt="Examples of a class, test, and file name" width="378"/>
      </td>
      <td>
          <p><format style="bold">Class/test/file name</format></p>
          <p>Width: min&nbsp;250&nbsp;px</p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_url_path_fqn.png" alt="Examples of a URL, path, and an FQN for class" width="378"/>
      </td>
      <td>
          <p><format style="bold">URL/Path/FQN for class</format></p>
          <p>Width: min&nbsp;350&nbsp;px</p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_class_test_file_url_path_fqn.png" alt="Examples of a class, test, or file name together with a URL, path, or an FQN for class" width="378"/>
      </td>
      <td>
          <p><format style="bold">Class/test/file name + URL/Path/FQN for class</format></p>
          <p>Width: min&nbsp;400&nbsp;px</p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_code_snippet.png" alt="Example of a code snippet" width="378"/>
      </td>
      <td>
          <p><format style="bold">Code snippet or editor</format></p>
          <p>Width: min&nbsp;400&nbsp;px</p>
          <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
      </td>
   </tr>
   <tr>
      <td width="50%">
          <img src="dialog_popup_sizes_standalone_text.png" alt="Example of a paragraph" width="378"/>
      </td>
      <td>
          <p><format style="bold">Paragraph or long text</format></p>
          <p>Width: min&nbsp;300&nbsp;px</p>
          <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
      </td>
   </tr>
 </table>

#### Example 1

The <control>Rename Inheritors</control> dialog has a table with FQNs. Add 350&nbsp;px as the minimum width for a table column. This will make the dialog wider than the default <control>Small</control> size (500&nbsp;px) but will show more of FQNs.

<table style="none" border="false">
    <tr>
         <td width="706">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_1_correct.png" alt="The Rename Inheritors dialog has a minimum width set for each table column"/>
         </td>
    </tr>
    <tr>
         <td width="706">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_1_acceptable.png" alt="The Rename Inheritors dialog has the Medium size as default"/>
         </td>
    </tr>
</table>

#### Example 2

<control>Settings</control> dialog has the default size 1000&nbsp;px. In the <control>Code Style settings for Java</control> set 400&nbsp;px as the minimum width of the code editor. This will make the dialog wider but will show more code

<table style="none" border="false">
    <tr>
         <td width="706">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_2_correct.png" alt="The code snippet in the Code Style settings for Java is 400 px, making the dialog wider"/>
         </td>
    </tr>
    <tr>
         <td width="706">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_2_acceptable.png" alt="Settings dialog is 1000 px wide"/>
         </td>
    </tr>
</table>

#### Example 3

Instead of setting a default size for a diagram popup, for example, the <control>Small</control> size, add horizontal and vertical 100&nbsp;px insets inside the <control>Diagram</control> popup, and make the width and height adaptive to its content. This will help small diagrams be more noticeable and make a diagram popup of any size easier to read.

<table style="none" border="false">
    <tr>
        <td width="706">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_3_correct.png" alt="Diagram popup has 100 px insets from its borders to its content"/>
        </td>
    </tr>
    <tr>
        <td width="706">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_3_acceptable.png" alt="Diagram popup is set to the default small size"/>
        </td>
    </tr>
</table>

## Empty state

Some components, for example, <control>tables</control>, can have an [empty state](empty_state.md) when there is no content. In this case, the minimum size of a component in both empty and filled states is defined by its filled state.

For example, the size of the <control>Custom Plugin Repository</control> dialog is defined by the minimum size of the table that contains URLs (minimum 350&nbsp;px wide), and not by the size of the smaller empty state:

<table style="none" border="false">
    <tr>
        <td width="706">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_empty_state_correct.png" alt="The table in the Custom Plugin Repository dialog has the same sizes in both empty and filled states"/>
        </td>
    </tr>
    <tr>
        <td width="706">
            <format color="E55765" style="bold">Incorrect</format>
            <img src="dialog_popup_sizes_empty_state_incorrect.png" alt="The table in the Custom Plugin Repository dialog has different sizes in the empty and the filled states"/>
        </td>
    </tr>
</table>
