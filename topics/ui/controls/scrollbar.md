<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Scrollbar

<link-summary>UI guidelines on using scrollbars.</link-summary>

<tldr>

**Implementation:** [`JBScrollBar`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBScrollBar.java)

</tldr>

The scrollbar allows users to browse content that’s larger than the visible area, such as text or images, by scrolling it horizontally or vertically.

![](scrollbar-small.png){width=370}

## Parts of a scrollbar

A scroll bar consists of a track, a thumb, and a status icon if there is a known status for displayed content.

![](scrollbar-detailed.png){width=157}

### Track

A track represents the overall length or width of the document.

The track can contain optional markers for quicker navigation between the important parts of the document, for example:

* The occurrences of a search string.

* Breakpoints and the current execution lines.

* A place where an inspection failed.

* Files with errors laid out in a tree.

* Changed parts of a file.

A marker can fit the track width or be placed to the left of the track. Use the latter when you expect the marked region to be tall.

Hovering over the track can also show a lens with a preview of the content at the corresponding position in the document.
This can be useful in large documents when it is not desirable to change the viewpoint:

![](lens-temp.png){width=622}

### Thumb

A thumb represents the visible portion of the content. For example, a small thumb indicates that there’s lots of content available for scrolling.

### Status icon (optional)

A status icon shows the overall status of inspections performed on the content and allows you to avoid scrolling over the entire view.
If at least one inspection has failed, the overall status should be the status of that inspection.

![](commit.png){width=492}

## How to use

Respect the OS settings when showing the scrollbar.
For example, on macOS you can hide the scrollbar until you scroll the content.
Always show the scrollbar when it is important to understand which part of the content is currently shown.
For example, the scrollbar in the Editor helps users understand the location of the current code fragment and the overall size of the currently opened file.

Show the track on hover to make the clickable area clearer. Consider not showing the track on hover if it might have more than 5–10 stripes at a time.

Increase the thumb contrast against the background on hover and when scrolling to make it more prominent.

Clicking the track above/below the thumb scrolls one page up/down the viewpoint.
This allows you to hide the up/down icons on the scrollbar to lessen the visual noise and to scroll with mouse-clicks.
Track markers serve as a mini-map of the document.
If they are present, clicking them takes you to the position that this marker is pointing to without having to scroll down the page.
Change the cursor to "Hand" when hovering such scrollbars.

When showing a text, wrap it instead of showing the horizontal scrollbar when possible. With both scrollbars, it is harder to manipulate the content.

<format color="Red" style="bold">Incorrect</format>

![](soft-wrap-incorrect.png){width=500}

<format color="Green" style="bold">Correct</format>

![](soft-wrap-correct.png){width=500}

A horizontal scroll bar can be added for code snippets or for formatted input. Use the context menu to enable soft-wrapping.

<format color="Green" style="bold">Correct</format>

![](soft-wrap-context.png){width=370}

Show only 1 scrollbar per table which scrolls the entire table:

<format color="Red" style="bold">Incorrect</format>

![](table-scrollbar-incorrect.png){width=562}

<format color="Green" style="bold">Correct</format>

![](table-scrollbar-correct.png){width=562}

## Placement

Reserve the space for a vertical scrollbar if there is a column on the right side, otherwise the scrollbar always overlaps content.

<format color="Red" style="bold">Incorrect</format>

![](vertical-scrollbar-incorrect.png){width=350}

<format color="Green" style="bold">Correct</format>

![](vertical-scrollbar-correct.png){width=350}

## Style

<table>
    <tr>
        <td width="50%">Windows/Linux</td>
        <td width="50%">macOS</td></tr>
    <tr>
        <td><img src="win-light.png" alt="" width="96" /></td>
        <td><img src="mac-light.png" alt="" width="96" /></td>
    </tr>
</table>
