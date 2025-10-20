<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tool Window

<link-summary>UI guidelines on using tool windows.</link-summary>

A tool window is a pane inside the main IDE window. For information about tool windows, see [IntelliJ IDEA Web Help](https://www.jetbrains.com/help/idea/tool-windows.html). For information about implementing tool windows, see [](tool_windows.md).

![](toolwindow.png){width=706}

## When to use

Use the tool window to show a large amount of information that the user needs while working side-by-side with the editor or other tool windows. For example, viewing the project structure, running and debugging an application, and viewing git log.

## When not to use

### Small amount of information

If the amount of information is small, show it in the editor, main toolbar, status bar, or popup. For example, show version control changes in the editor gutter, breadcrumbs in the main toolbar, current branch and file details in the status bar, quick doc in a popup.

<!--For more information about choosing the correct placement for content, read [UI guidelines on placing content](content_placement.md). //TODO: Write a guideline on placing content in IDE.-->

### Main window content is not needed

If the user does not need the information from the main window to complete an operation, show the content in the popup or dialog window. For example, the <control>Search Everywhere</control> popup, <control>Settings</control> dialog.

## Tool window structure

![](toolwindow_structure.png){width=706}

### Tool window name

* The name should be [short and descriptive](writing_short.md), preferably not longer than two words.
* Use title-case capitalization.

<!--Show the tool window name in the tool window button and header. If there are tabs in the tool window header, add a colon after the tool window name. Otherwise, do not add it.-->

![](toolwindow_name.png){width=706}

### Icon

Add an icon for the tool window button. The icon should be created in two sizes: 20×20px and 16×16px, grey, and monochromatic. See how to create icons in the [icons guidelines](icons_style.md).

<!--### Icon

Follow [tool window icons guidelines](icons_style#NNN.md) to create correct icons for tool windows.
// TODO: publish this chapter when the guideline about icons is public too
-->

#### Feedback

If the content changes and can contain different types of feedback, for example, new updates or errors, a colored badge is shown above the icon.

![](toolwindow_badge.png){width=706}

Do not change the icon for the tool window button when the content changes.

<!--**Exception:** <control>Problems</control> and <control>Event Log</control> icons change color and show the current status.-->

### Tabs

Add tabs if all the information does not fit on one screen or refers to similar instances, like run sessions, history for files, find results. The tabs for entities should be closable.

![](toolwindow_tabs.png){width=706}

### Orientation and content

#### Vertical window

Vertical tool windows work better for trees, for example, Project, Structure, or Maven tool windows.

![](toolwindow_vertical.png){width=706}

#### Horizontal window

Horizontal tool windows work better for tables, wide content, or master-detail panels, for example, Git Log, Terminal, or Problems.

![](toolwindow_horizontal.png){width=706}

#### Visibility

If a tool window has no content yet, show its button by default only if the window contains basic functionality that is likely to be used for all projects, for example, <control>Version Control</control> or <control>Problems</control>. Otherwise, hide the tool window button by default. Hidden tool windows are still available under <control>More tool windows</control> menu.

Don't show the tool window button if the tool window is not relevant to the current project configuration. For example, don't show the <control>Maven</control> tool window for a project without Maven configuration files.

### Toolbar

Add a toolbar for [frequently used actions and filters](toolbar.md#what-items-to-add-on-toolbar). For more details, see the [toolbar guidelines](toolbar.md).

<!--Put tool window viewing mode settings under the gear icon in the header. If there is no toolbar, put other options under the gear icon as well. For example, see the gear icon in the Project tool window.-->

