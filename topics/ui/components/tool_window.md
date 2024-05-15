<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tool Window

<link-summary>UI guidelines on using tool windows.</link-summary>

A tool window is a pane inside the main IDE window. For information about tool windows, see [IntelliJ IDEA Web Help](https://www.jetbrains.com/help/idea/tool-windows.html). For information about implementing tool windows, see [](tool_windows.md).

![](tool_window_example.png){width=960}

## When to use

Use the tool window to show a large amount of information that the user needs while working side-by-side with the editor or other tool windows. For example, viewing the project structure, running and debugging an application, and viewing git log.

If the amount of information is small, show it in the editor, main toolbar, status bar or popup. For example, show version control changes on the editor gutter, breadcrumbs in the main toolbar, current branch and file details in the status bar, quick doc in a popup.

If the user does not need the information from the main window to complete an operation, show the content in the popup or dialog window. For example, Search Everywhere popup, Settings dialog.

## Tool window structure

![](tool_window_structure.png){width=483}

## How to use

Give the tool window a name:

* The name should be [short and descriptive](writing_short.md), preferably not longer than two words.
* Use title-case capitalization.

Show the tool window name in the tool window button and header. If there are tabs in the tool window header, add a colon after the tool window name. Otherwise, do not add it.

![](pull_requests.png){width=308}

Add an icon for the tool window. The icon is 13px x 13px, grey and monochromatic. See how to create icons in the [icons guidelines](icons_style.md).

**Exception:** <control>Problems</control> and <control>Event Log</control> icons change color and show the current status.

Select the tool window orientation depending on the content. Vertical tool windows work better for trees, for example, Project, Structure, or Maven tool windows. Horizontal tool windows work better for tables, wide content or master-detail panels, for example, Git Log, Terminal, or Problems.

Do **not** show the tool window button if the tool window is not relevant to the current project configuration. For example, do not show the Maven tool window for a project without Maven configuration files.

If a tool window has no content yet, show its button by default only if the window contains basic functionality that is likely to be used for all projects, for example, Version Control or Problems. Otherwise, do not show the tool window button by default.

Add a toolbar for [frequently used actions and filters](toolbar.md#what-items-to-add-on-toolbar). Use a horizontal toolbar for vertical tool windows and a vertical toolbar for horizontal tool windows. For more details, see the [toolbar guidelines](toolbar.md).

Put tool window viewing mode settings under the gear icon in the header. If there is no toolbar, put other options under the gear icon as well. For example, see the gear icon in the Project tool window.

Add tabs if all the information does not fit in one tab or refers to similar instances, like run sessions, history for files, find results. The tabs for entities should be closable.

![](git.png){width=308 style=block}
*Separate tabs are added for each file history.*

![](find.png){width=448 style=block}
*Separate tabs are added for different search queries.*

