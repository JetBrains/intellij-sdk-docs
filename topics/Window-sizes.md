<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Window sizes

<link-summary>Guidelines on choosing the correct size when creating a dialog or a popup</link-summary>

Follow these guidelines to select the correct size when creating a dialog or a popup for IntelliJ-based products.

For simple cases use default sizes for [dialogs](Window-sizes#default-sizes) and [popups](Window-sizes#popup). When default sizes don't work well, set [minimum sizes for components](Window-sizes#minimum-sizes-for-components).

## Default sizes

### Dialog

There are four recommended window sizes for dialogs: **extra small**, **small**, **medium**, and **large**. Select the size depending on the amount of content to keep the important information visible.

![A preview of recommended window sizes for dialogs](dialog_popup_sizes.png){width="706"}

<note>When setting the default size for your dialog, it should be impossible to make it smaller.</note>

#### Extra small

Size: 350×250 px.

Best for: several components that are stacked vertically.

![The Rename dialog with two input fields, two checkboxes, and actions in the dialog footer. The components are stacked vertically in one column](dialog_popup_sizes_extra_small.png){width="706"}

#### Small

Size: 500×350 px

Best for:
* Multiple components with short labels that are divided into two columns.
* A full-width table with two or three columns.
* A full-width code editor or snippet.

For example, the <control>Evaluate Expressions</control> dialog has two code snippets that are stacked vertically:

![The Evaluate Expressions dialog that has two code snippets stacked vertically](dialog_popup_sizes_small.png){width="706"}

#### Medium

Size: 750×525 px

Best for:
* A full-width table with four or more columns.
* Two-column layout with an image or a video in any of the columns. In this case, the image or the video takes the most space.
* Two-column layout with a code snippet or an input field with long text in any of the columns.
* Two code editors or snippets in a row.

For example, the <control>Run/Debug Configuration</control> dialog has a two-column layout with a tree in the first column and the list of controls with long texts in the second column:

![The Run/Debug Configuration dialog that has a two-column layout. There is a nvigation in the first column and the list of settings in the second column](dialog_popup_sizes_medium.png){width="706"}

#### Large

Size: 1000×700 px

Best for:
* Two-column layout with long names, code editor, code snippet.
* Three-or-more-column layout regardless of the content.

For example, <control>Code Style</control> settings for Java in the <control>Settings</control> dialog. The dialog has three columns with a table and a code snippet in different columns:

![The Code Style settings in the Settings dialog. The dialog has three columns with a table and a code snippet in different columns](dialog_popup_sizes_large.png){width="706"}

### Popup

#### Minimum width

Popups should have the same default width as dialogs. In addition, there is a smaller size for popups – 200 px wide, when the content is narrow.

<table style="none" border="false">
   <tr>
      <td width="50%">
         <p><format style="bold">Insert</format></p>
         <img src="dialog_popup_sizes_popup_xxs.png" alt="" width="378"/>
      </td>
      <td width="50%">
         <p><format style="bold">Quick Fixes</format></p>
         <img src="dialog_popup_sizes_popup_xs.png" alt="" width="378"/>
      </td>
   </tr>
</table>
<table style="none" border="false">
   <tr>
      <td width="100%">
         <p><format style="bold">Documentation</format></p>
         <img src="dialog_popup_sizes_popup_s.png" alt="" width="706"/>
      </td>
   </tr>
   <tr>
      <td width="100%">
         <p><format style="bold">Search Everywhere</format></p>
         <img src="dialog_popup_sizes_popup_m.png" alt="" width="706"/>
      </td>
   </tr>
</table>

#### Minimum height

Unlike dialogs, popups have an adaptive height. Adapt the height of a popup to the amount of content inside.

For example, the height of the <control>Documentation</control> popup is different depending on what is focused in the editor:

<img src="dialog_popup_sizes_popup_adaptive_height.png" alt="The Documentation popup adapts its height based on the content inside"/>

#### Maximum width and height

The maximum width and height shouldn't exceed 80% of a screen size. Use a <control>scrollbar</control> if a popup's content doesn't fit within the set dimensions.

### Tool windows

Default sizes:

* Vertical tool windows should take 20% of the application width but not less than 200×500&nbsp;px.
* Horizontal tool windows should take 20% of the application height but not less than 500×200&nbsp;px.

The default sizes should be set for all the states of the <control>View Mode</control>.

<table style="none" border="false">
    <tr>
        <td width="50%">
            Vertical <control>Project</control> tool window:
            <img src="dialog_popup_sizes_tool_window_vertical.png" alt=""/>
        </td>
        <td width="50%">
            Horizontal <control>Build</control> tool window:
            <img src="dialog_popup_sizes_tool_window_horizontal.png" alt=""/>
        </td>
    </tr>
</table>

### Editor tabs

Place your content into a <control>tab</control> in the editor when there are three or more columns in a row with code that needs to be shown.

For example, the <control>Merge</control> functionality features a list of changes with actions in a tool window and two code editors with line numbers:

![The Merge functionality with a list of changes with actions in a tool window on the left and two code editors with line numbers in the editor tab on the right](dialog_popup_sizes_editor.png){width="706"}

## Minimum sizes for components

When the default sizes are either too big or too small for a window, or the window has a complex layout, add minimum sizes to important content inside the window. This will make the content displayed correctly and reduce any potential issues caused by resizing the window.

### How to set the size correctly

1. Select how the content is displayed:

    <table style="none" border="false">
        <tr>
            <td width="50%">
               <img src="dialog_popup_sizes_table_cell.png" alt="" width="378"/>
            </td>
            <td>
               <p><format style="bold">Table cell</format></p>
               <p>Width: min&nbsp;65&nbsp;px</p>
               <p>Height for the whole table: min&nbsp;120&nbsp;px</p>
               <p>Follow guidelines for table width</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_tree.png" alt="" width="378"/>
            </td>
            <td>
               <p><format style="bold">Tree</format></p>
                <p>Width: min 250&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_text_area.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Text area</format></p>
                <p>Width: min&nbsp;270&nbsp;px, max&nbsp;600&nbsp;px</p>
                <p>Height: min&nbsp;55&nbsp;px</p>
                <p>For size and placement follow the <a href="text_area.md" anchor="size-and-placement">text area</a> guidelines</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_diagram.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Diagram</format></p>
                <p>A container with horizontal and vertical insets with 100&nbsp;px</p>
            </td>
        </tr>
    </table>

2. What type of content is used:

   <table style="none" border="false">
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_class_test_file.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Class/test/file name</format></p>
                <p>Width: min&nbsp;250&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_url_path_fqn.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">URL/Path/FQN for class</format></p>
                <p>Width: min&nbsp;350&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_class_test_file_url_path_fqn.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Class/test/file name + URL/Path/FQN for class</format></p>
                <p>Width: min&nbsp;400&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_code_snippet.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Code snippet or editor</format></p>
                <p>Width: min&nbsp;400&nbsp;px</p>
                <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <img src="dialog_popup_sizes_standalone_text.png" alt="" width="378"/>
            </td>
            <td>
                <p><format style="bold">Standalone text</format></p>
                <p>Width: min&nbsp;300&nbsp;px</p>
                <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
            </td>
        </tr>
    </table>

3. Select the biggest size out of the two to add the minimum size to a component

#### Example 1

The <control>Rename Inheritors</control> dialog has a table with FQNs. Add 350&nbsp;px as the minimum width for a table column. This will make the dialog wider than the recommended 500&nbsp;px but will show more of FQNs.

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
            <img src="dialog_popup_sizes_example_2_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="706">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_2_acceptable.png" alt=""/>
         </td>
    </tr>
</table>

#### Example 3

Add horizontal and vertical 100&nbsp;px insets inside the <control>Diagram</control> popup to make it adaptable to the different amounts of content inside.

<table style="none" border="false">
    <tr>
         <td width="706">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_3_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="706">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_3_acceptable.png" alt=""/>
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
            <img src="dialog_popup_sizes_empty_state_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="706">
            <format color="E55765" style="bold">Incorrect</format>
            <img src="dialog_popup_sizes_empty_state_incorrect.png" alt=""/>
         </td>
    </tr>
</table>
