<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Progress Indicators

<link-summary>UI guidelines on using progress indicators.</link-summary>

<tldr>

**Implementation**: Progress tracking in [Progress API](background_processes.md#tracking-progress) and [Kotlin Coroutines](execution_contexts.topic#progress-reporting)

</tldr>

Progress indicators inform users about an ongoing operation. There are three types of indicators:

* [Loader](loader.md)
* [Progress text](progress_text.md)
* [Progress bar](progress_bar.md)

## Which indicator to use

If an operation lasts for less than a second in most cases, do not show any progress indicator. Progress indicators for a short operation are distracting.

If the user has to wait until a process completes to continue working with the app, show a progress bar in a modal dialog.

![](progress_dialog.png){width=440}

If the progress is non-modal, select progress indicator based on where it appears.

* [Input field, combo box, drop-down](#input-field-combo-box-drop-down)

* [Search field](#search-field)

* [Tree, list, tool window](#tree-list-tool-window)

* [Breadcrumb and dialog label](#breadcrumb-and-dialog-label)

* [Settings dialog](#settings-dialog)

* [Empty state](#empty-state)

* [Main window](#main-window)

### Input field, combo box, drop-down

Place the loader inside an input field on the right to show that the field content is being loaded or checked.

![](combo_box_loader.png){width=201}

### Search field

Place the loader on the right to show that search is being performed.

![](search_field_loader.png){width=262}

### Tree, list, tool window

If each tree node loads independently, and it’s important to know the current state of each node, show the loader instead of the node icon:

![](tree_loader_icon.png){width=372}

If nodes do not have icons, or icons can’t be replaced for some reason, show progress text after the node’s label:

![](tree_text.png){width=400}

If the loader applies to the whole content, place it in the top right corner:

![](tree_loader_corner.png){width=400}

If processes start simultaneously and have the same execution time, place the loader in the top right corner of the area.
For example, in the <control>Push</control> dialog, repositories are loaded at the same time.
Loaders in each line will blink at the same time and will look distracting.

![](tree_loader_push.png){width=400}

If the top right corner of the area is taken with content and there is no space for the loader, show the progress bar at the top of the tool window.
For example, the VCS Log tool window has a progress bar at the top when the content is loading or during searching.

![](progress_tool_window.png){width=605}

### Breadcrumb and dialog label

Place the loader after breadcrumbs or a dialog label to show that the page is being loaded:

![](breadcrumb_loader.png){width=274}

### Settings dialog

If some settings or configurations take a significant time to load, it’s preferable to reserve space for a progress bar.
For example, show a progress bar while installing a plugin:

![](settings_loader.png){width=251}

### Empty state

If the state is empty, show progress text. For example, in search dialogs, and the details panel in settings or tool windows:

![](empty_state_text.png){width=708}

Show one more progress indicator, e.g., a loader like on the example above, if loading continues when some data is already displayed.

If a process takes longer than 4 seconds, provide [process details](progress_text.md#details), e.g., the time left or passed, work units
like the current step, to indicate that the process is being performed and is not hanging:

![](empty_state_details.png){width=576}

If it’s not possible to provide progress details, show the loader to indicate that a long process is not frozen:

![](empty_state_loader.png){width=576}

### Main window

If a process is long and can run in the background, show the progress bar in the main window in the status bar:

![](status_bar_progress.png){width=238}

Or in a non-modal dialog:

![](tasks_dialog.png){width=700}
