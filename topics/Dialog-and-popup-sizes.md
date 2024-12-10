<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Dialog and Popup Sizes

<link-summary>Guidelines on choosing the correct size when creating a dialog or a popup</link-summary>

Follow these guidelines to select the correct size when creating a dialog or a popup for IntelliJ-based products.

## Default sizes

### Dialog

There are four recommended window sizes for dialogs: **extra small**, **small**, **medium**, and **large**. Select the size depending on the amount of content to keep the important information visible.

<note>When setting the default size for your dialog, it should be impossible to make it smaller.</note>

#### Extra small

Size: 350×250 px.

Best for: several components that are stacked vertically.

For example, the <control>Rename File</control> dialog has several input fields and checkboxes in one column:

![The Rename dialog with two input fields, two checkboxes, and actions in the dialog footer. The components are stacked vertically in one column](dialog_popup_sizes_extra_small.png){width="706"}

#### Small

Size: 500×350 px

Best for:
* Two-column layout with a tree on one column and several components that are stacked vertically in the second column.
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
* Three-column layout with a code editor or snippet in any of the columns.
* Two code editors or snippets in a row.

For example, the <control>Image Viewer</control> dialog has a two-column layout with image or video in the first column and the list of input fields with actions in the second column:

![The Recent Files popup that has a two-column layout. There are quick links in the first column and the list of recent files with long names in the second column](dialog_popup_sizes_medium.png){width="706"}

#### Large

Size: 1000×700 px

Best for:
* Two-column layout featuring components with long names in one of the columns.
* Three-column layout featuring a table in one of the columns.
* Four-or-more-column layout possibly featuring a code editor or snippet in any of the columns.

For example, <control>Code Style</control> settings for Java in the <control>Settings</control> dialog. The dialog has three columns with a table and a code snippet in different columns:

![The Code Style settings in the Settings dialog. The dialog has three columns with a table and a code snippet in different columns](dialog_popup_sizes_large.png){width="706"}

### Popup

Popups should have the same default sizes as dialogs, but unlike dialogs, they can have adaptive height. If the popup displays different amounts of content depending on the situation, adapt the popup size to the content on opening.

For example, the height of the <control>Git Brunches</control> popup adapts to the amount of the content inside:

![Two Git Branches popups adapting to the height of their content. The popup on the left is shorter because it has fewer branches, while the popup on the right is longer because it has more branches](dialog_popup_sizes_adaptive.png){width="706"}

When a user changes an adaptive popup’s size, the size is saved and the adaptivity becomes inapplicable.

### Tool windows

* Vertical tool windows should have the default size set to 250×500&nbsp;px.
* Horizontal tool windows should have the default size set to 500×250&nbsp;px.

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

Place your content into a <control>tab</control> in the editor when there are three or more columns in a row with important information that needs to be shown.

For example, the <control>Merge</control> functionality features a list of changes with actions in a tool window and two code editors with line numbers:

![The Merge functionality with a list of changes with actions in a tool window on the left and two code editors with line numbers in the editor tab on the right](dialog_popup_sizes_editor.png){width="706"}

## Minimum sizes for components

When the default sizes are either too big or too small for a window, or the window has a complex layout, add minimum sizes to important content inside the window. This will make the content displayed correctly and reduce any potential issues caused by resizing the window.

### How to set the size correctly

1. Select how the content is displayed:

    <table style="header-column">
        <tr>
            <td width="40%">
                Table cell
            </td>
            <td>
                <p>Width: min&nbsp;65&nbsp;px</p>
                <p>Height for the whole table: min&nbsp;120&nbsp;px</p>
                <p>Follow guidelines for table width</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                Tree
            </td>
            <td>
                Width: min 250&nbsp;px
            </td>
        </tr>
        <tr>
            <td width="40%">
                Text area
            </td>
            <td>
                <p>Width: min&nbsp;270&nbsp;px, max&nbsp;600&nbsp;px</p>
                <p>Height: min&nbsp;55&nbsp;px</p>
                <p>For size and placement follow the <a href="text_area.md" anchor="size-and-placement">text area</a> guidelines</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                Diagram
            </td>
            <td>
                A container with horizontal and vertical insets with 100&nbsp;px
            </td>
        </tr>
    </table>

2. What type of content is used:

   <table style="header-column">
        <tr>
            <td width="40%">
                Class/test/file name
            </td>
            <td>
                <p>Width: min&nbsp;250&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                URL/Path/FQN for class
            </td>
            <td>
                <p>Width: min&nbsp;350&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                Class/test/file name + URL/Path/FQN for class
            </td>
            <td>
                <p>Width: min&nbsp;400&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                Code snippet or editor
            </td>
            <td>
                <p>Width: min&nbsp;400&nbsp;px</p>
                <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
            </td>
        </tr>
        <tr>
            <td width="40%">
                Standalone text
            </td>
            <td>
                <p>Width: min&nbsp;300&nbsp;px</p>
                <p>Height (when multiple lines): min&nbsp;120&nbsp;px</p>
            </td>
        </tr>
    </table>

3. Select the biggest size out of the two to add the minimal size to a component

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

<control>Settings</control> dialog has the default size 1000&nbsp;px. In the <control>Code Style settings for Java</control> set 400&nbsp;px as the minimal width of the code editor. This will make the dialog wider but will show more code

<table style="none" border="false">
    <tr>
         <td width="100%">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_2_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="100%">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_2_acceptable.png" alt=""/>
         </td>
    </tr>
</table>

#### Example 3

Add horizontal and vertical 100&nbsp;px insets inside the <control>Diagram</control> popup to make it adaptable to the different amounts of content inside

<table style="none" border="false">
    <tr>
         <td width="100%">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_example_3_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="100%">
            <format color="DarkOrange" style="bold">Acceptable</format>
            <img src="dialog_popup_sizes_example_3_acceptable.png" alt=""/>
         </td>
    </tr>
</table>

## Empty state

Some components, for example, <control>tables</control>, can have an [empty state](empty_state.md) when there is no content. In this case, the minimum size depends on which state takes more space: the component with content or with an empty state.

In most cases, an empty state takes less space than the minimum size of a component. In other cases, the minimum size of the component should be determined by the size of the empty state to reduce unexpected resizing.

<table style="none" border="false">
    <tr>
         <td width="100%">
            <format color="369650" style="bold">Correct</format>
            <img src="dialog_popup_sizes_empty_state_correct.png" alt=""/>
         </td>
    </tr>
    <tr>
         <td width="100%">
            <format color="E55765" style="bold">Incorrect</format>
            <img src="dialog_popup_sizes_empty_state_incorrect.png" alt=""/>
         </td>
    </tr>
</table>
