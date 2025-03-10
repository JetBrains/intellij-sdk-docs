<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 2. Editor Coordinates System. Positions and Offsets

<link-summary>Tutorial demonstrating how to access editor coordinate system.</link-summary>

The previous tutorial [Working with Text](working_with_text.md) demonstrated how to use actions to access a caret placed in a document open in an editor.
The examples replaced selected text in a document by using information about the caret.

Every caret has a set of properties describing its position in one of several coordinate systems.
This tutorial describes how to access information about the caret(s) in an editor.

## Editor Basics Code Sample

In this tutorial, the [editor_basics](%gh-sdk-samples-master%/editor_basics) code sample is used to explore caret positions.
In particular, the **Caret Position** action added by `editor_basics` to the editor context menu is used to retrieve information about the current caret position.
A keyboard shortcut can also initiate the action.

![Editor Basics Menu](basics.png){width="600"}

The source code for the Java class behind the menu action is [EditorAreaIllustration](%gh-sdk-samples-master%/editor_basics/src/main/java/org/intellij/sdk/editor/EditorAreaIllustration.java).
The focus of discussion will be the `EditorAreaIllustration.actionPerformed()` method.
For more information about creating action classes, see the [](action_system.md), which covers the topic in depth.

## Caret Positions from the `CaretModel` and `Caret` Objects

The properties of a caret can be accessed by obtaining an instance of the [`CaretModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java) object.
As in the [Working with Text](working_with_text.md) tutorial, the `AnActionEvent` is used to get the `Editor` object.
The `Editor` object provides access to the `CaretModel` object, as shown below:

```java
public class EditorAreaIllustration extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    // Get access to the editor and caret model. update() validated editor's existence.
    Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();
  }
}
```

## Editor Coordinate Systems

When a `Document` is opened, the `Editor` assigns an internal, zero-based coordinate system to lines and columns in the `Document`.
The first line in a `Document` and the first character in each line are assigned the zero position.
Every character in a `Document` is assigned an [Offset](#caret-offset), which is a zero-based count of the characters from the beginning of the file to that character.
These [LogicalPosition](#caret-logical-position) coordinates are used to describe the line and column number for a caret position.
Note that the Logical Position coordinate system is different from the editor UI, which is one-based rather than zero-based.

Logical Position coordinates and other coordinate systems discussed in this tutorial can be used to characterize any location in an `Editor`, not just carets.
Hints used for code insights are characterized in terms of these coordinates, for example [`HintManager.getHintPosition()`](%gh-ic%/platform/platform-impl/src/com/intellij/codeInsight/hint/HintManagerImpl.java).
Custom visual elements displayed in an `Editor`, called [`Inlay`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Inlay.java) objects, are also expressed in terms of these coordinate systems.

The diagram below shows the Logical Position coordinate system applied to some example content.
The character "s" in the red box represents placing the cursor on that character.
It has the caret position of line 1, column 9, and Offset 28.
More about caret [Offsets](#caret-offset) is discussed below.

![Editor Coordinates](editor_coords.png){width="800"}

The [Multiple Carets](multiple_carets.md) documentation covers the subject of more than one caret in an Editor.
For this tutorial, be aware there may be more than one caret in an `Editor` at any given time.
Consequently, examples use the _Primary Caret_ in an `Editor`.
If there is only one caret in an `Editor`, it is the Primary Caret.
For the case of multiple carets in an `Editor`, the Primary Caret is the one on which query and update methods in the model operate at the moment.

### Caret Logical Position

The caret _Logical Position_ is a zero-based, (line and column) position of the caret in the Editor.
Logical Position information is obtained from the [`LogicalPosition`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/LogicalPosition.java) object for that caret.

The Logical Position line number of a caret ignores the effects of settings that change the presentation of a `Document` within the Editor.
Examples of these settings are [Code (Line) Folding](https://www.jetbrains.com/help/idea/working-with-source-code.html#code_folding) and [Soft Line Wrap](https://www.jetbrains.com/help/idea/using-code-editor.html#f804afd8).
These effects mean regardless of whether one or more lines in an Editor are folded or soft-wrapped, the caret Logical Position line number will not change.

In the example Java file below, Logical Position line numbers 1-3 are folded into line 0.
The caret - a blue block - is placed on the letter "p" in "public."
Using the `editor_basics` **Caret Position** action to inspect the caret, it is reported to be at Logical Position (5,0) - which is line 5, character 0 - the first character in the line.
This means that caret Logical Position is not changed by Code Folding:

![Caret Logical Position with Folding](logical_pos_folded.png){width="800"}

However, note that applying Code Folding _does change the reported Visual Position_ of the caret even if the Logical Position stays constant.
More about [Visual Position](#caret-visual-position) is discussed below.
However, it's clear combinations of Code Folding and Soft Wrap means that one Logical Position of a caret could map to multiple Visual Positions.
The `Editor` interface provides methods to work with a caret Logical and Visual Position, such as the method `Editor.logicalToVisualPosition()`.

### Caret Visual Position

A caret's _Visual Position_ differs from Logical Position in that it takes into account editor presentation settings such as Code Folding and Soft Line Wrap.
In doing so, [`VisualPosition`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java) counts - zero-based - the lines of a `Document` that can be _displayed_ in an Editor.
Consequently, Visual Positions can't be uniquely mapped to Logical Positions or corresponding lines in the underlying `Document`.

For example, Soft Line Wrap affects the Visual Position of succeeding lines.
In the image below, Soft Line Wrap is applied to Logical line three.
With the caret placed at the same character location as in previous tests, it is evident the Logical Position has not changed.
However, the Visual Position line number has increased by one!
The comments on each line illustrate how the Soft Wrap portion of Logical line three is evaluated as Visual Position line four, as though it was a separate line.

![Caret Visual Position with Soft-Wrap](vis_pos_soft_wrap.png){width="800"}

The Logical and Visual Position objects for a caret are obtained from the [`Caret`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Caret.java) object, as shown in the code snippet below.

```java
public class EditorAreaIllustration extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    // Get access to the editor and caret model.
    Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();
    Caret primaryCaret = caretModel.getPrimaryCaret();
    LogicalPosition logicalPos = primaryCaret.getLogicalPosition();
    VisualPosition visualPos = primaryCaret.getVisualPosition();
  }
}
```

### Caret Column Position

The _Column Position_ is a count of characters from the beginning of a Logical (Position) line to the current caret position in that line.
Characters are counted using a zero-based numbering system, so the first character of a line is numbered zero.
Note that Column Position is different from the editor UI, which uses a one-based numbering scheme.

Column Position includes:
* Whitespace, such as tabs.
  Tabs can occupy multiple columns, up to the tab size set for the editor.
* The character selected by the caret.

#### Caret Lean

The Column Position of a caret is the boundary between two characters.
A caret can be associated with either a preceding or succeeding character.
The association is important in bidirectional text, where mapping from Logical Column Position to Visual Column Position is not continuous.

As defined in the [`LogicalPosition`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/LogicalPosition.java) class, if a caret position is associated with a succeeding character it _Leans Forward_.
Otherwise, it is associated with the preceding character.

As defined in the [`VisualPosition`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java) class, if a caret position is associated with a succeeding character it _Leans Right_.
Otherwise, it is associated with the preceding character.

#### Examples of Caret Lean

In the example below, placing a (blue) block caret on the first visible character in Logical line three produces a column position of 0 for both Visual and Logical Positions.
Note that the text is unidirectional in this example.
In the Logical Position the caret leans forward, meaning it is associated with the succeeding character in the Logical line.
For the Visual Position the caret leans right, indicating its association with the succeeding character in the Visual line.

![Caret Column Position - Block Caret](caret_col_pos_block.png){width="800"}

Consider the Java snippet below, and use the `editor_basics` **Caret Position** action to report caret information at each step.
Be sure to use the keyboard shortcut to invoke the action so that the caret position is not disturbed.

The line containing the `String` variable declaration contains bidirectional text.
Starting on the left end of the line, and using the arrow key to advance a line-shaped cursor to between the `("` characters reveals discontinuities in caret coordinate column positions.
* After the caret first moves from between `g(` to between `("`, the Logical and Visual Positions have equal column positions of 26, and neither leans.
  Note the value of the caret positions you measure may have different starting values because of line indentation, but the sign and magnitude of the changes in position will be the same.
* Advancing the caret once more does not appear to visually move the cursor.
  However, the Logical Position column increases to 59, and although the Visual Position column does not change, it leans right.
* Continuing to advance the cursor (to the right) through the string causes the Logical Position column to _decrease_, and the Visual Position column to _increase_.
* Once the cursor advances to between the `".` characters, the Logical Position column position is again 26, and leans forward.
  The Visual Position column position is now 59.
* Moving the caret right once more does not appear to visually advance the cursor.
  However, the Logical Position and Visual Position column values are both 59, and both lean.
* As the cursor advances to the right, both Logical and Visual column values increase.

```java
  public void showNow() {
//234567890123456789012345678901234567890123456789012345678901234567890
  String str = new String("تعطي يونيكود رقما فريدا لكل حرف".getBytes(), java.nio.charset.StandardCharsets.UTF_8);
  System.out.println(str);
}
```

The apparent discontinuity in Logical Position is because the RTL portion of the string is treated (or counted) in the logical character order in which it would be written.
The apparent continuity in Visual Position is because the RTL portion of the string is counted in the visual order in which it is displayed in the code.

### Caret Offset

The _Offset_ of a caret is a character count from the beginning of a `Document` to the caret position.
Caret offsets are always calculated in terms of Logical Position.
The caret Offset includes:
* The first (0th) character in a document.
* Whitespace characters, including newline and tabs.
* Any characters after end-of-line if the IDE settings permit them.
  (<ui-path>Settings | Editor | General | Virtual Space</ui-path>)
* The character selected by the caret.

The example below demonstrates the Offset of a caret placed at the first character of Logical line one.
Note the Offset is 22, which is one greater than the number of visible characters on line zero, and the first character on line one.
This apparent discrepancy is actually correct because the Offset includes the newline character for line zero.

![Line 2 Caret Offset](caret_offset_l2.png){width="800"}

## Displaying Caret Positions

To display the values of caret Logical and Visual positions, and Offset, a
`Messages.showInfoMessage()` method shows them in the form of notification as the action is performed.

```java
public class EditorAreaIllustration extends AnAction {

  public void actionPerformed(@NotNull AnActionEvent event) {
    // Get access to the editor and caret model.
    Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();

    // Getting the primary caret ensures we get the correct one of a possible many.
    Caret primaryCaret = caretModel.getPrimaryCaret();
    // Get the caret information
    LogicalPosition logicalPos = primaryCaret.getLogicalPosition();
    VisualPosition visualPos = primaryCaret.getVisualPosition();
    int caretOffset = primaryCaret.getOffset();

    // Build and display the caret report.
    String report = logicalPos.toString() + "\n" +
        visualPos.toString() + "\n" +
        "Offset: " + caretOffset;
    Messages.showInfoMessage(report, "Caret Parameters Inside The Editor");
  }

}
```
