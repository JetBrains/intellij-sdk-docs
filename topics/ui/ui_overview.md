# User interface overview

The main application window of all IntelliJ-based IDEs by default includes the following parts:
* [Main toolbar](#main-toolbar) at the top
* [Stripes](#stripes) on the left and right sides that provide quick access to tool windows
* [Tool windows](#tool-windows) that surround the editor on the right, left, and bottom
* [Editor area](#editor-area)
* [Status bar](#status-bar) at the bottom

<img src="ui_overview_main_window.png" alt="Main Window" width="1000"/>

IntelliJ platform also provides the following container elements:
* [Dialogs](#dialogs)
* [Popups](#popups)
* [Notifications](#notifications)
* [Context menus](#context-menus)

See the full list of UI components in [](Components.topic).

## Main toolbar
The main toolbar provides quick access to the most commonly used tools for managing the IDE and projects:

<img src="ui_overview_main_toolbar.png" alt="Main toolbar" width="1000"/>

### Project widget
A drop-down control that allows opening existing projects or creating new ones:

<img src="ui_overview_project_widget.png" width="706" alt="Project widget"/>

### VCS widget
A drop-down control for managing VCS branches:

<img src="ui_overview_vcs_widget.png" width="706" alt="VCS widget"/>

### Run widget
A drop-down control for choosing run configurations and buttons for running and debugging the code:

<img src="ui_overview_run_widget.png" alt="Run widget" width="706"/>

### Toolbar buttons
Provide quick access to Search, Settings, and other important IDE features.

<img src="ui_overview_main_toolbar_buttons.png" alt="Main toolbar buttons" width="706"/>

### Main menu (on Windows and Linux)
On Windows and Linux, the toolbar also includes the Hamburger button:

<img src="ui_overview_main_toolbar_windows.png" alt="Main toolbar on Windows" width="1000"/>

On clicking the Hamburger button, the menu items are shown on the right:

<img src="ui_overview_main_toolbar_windows_expanded.png" alt="Main menu expanded" width="1000"/>

You can also opt for showing the menu above the toolbar:

<img src="ui_overview_main_toolbar_windows_expanded_dark_down.png" alt="Main menu expanded on a separate line" width="1000"/>

The main toolbar is fully [customizable](https://www.jetbrains.com/help/idea/customize-actions-menus-and-toolbars.html#customize-main-toolbar),
which means that the user may hide unnecessary widgets and buttons and add new ones.

## Tool windows
Tool windows are the panes inside the main application window that serve for solving different tasks,
for example, navigating through the project files in the Project tool window or working with Terminal in the
Terminal tool window:

<img src="ui_overview_tool_window.png" alt="Terminal tool window" width="706"/>

See more in [Tool windows](tool_window.md).

## Stripes

The left and right sides of the main window feature vertical stripes containing tool window buttons.
These appear as icons by default but can be configured to display tool window names beneath them:

<img src="ui_overview_stripes.png" alt="Stripes" width="706"/>

See more in [Show and hide tool windows names](https://www.jetbrains.com/help/idea/tool-windows.html#show_or_hide_tool_window_names).

## Editor area

Editor area is the main working area in IntelliJ IDEs that is used to write, read, and modify code:

<img src="ui_overview_editor.png" alt="Editor" width="1000"/>

### Code editor

The IntelliJ code editor provides highlighting, navigation, refactorings, and other powerful code insights features.
See implementation details in [Editor basics](editor_basics.md).

### Editor tabs

Tabs display the file name and extension. They can be [pinned](https://www.jetbrains.com/help/idea/using-code-editor.html#pin-or-unpin-a-tab), [opened in separate windows](https://www.jetbrains.com/help/idea/using-code-editor.html#detach_tab) or in a [split view](https://www.jetbrains.com/help/idea/using-code-editor.html#split_screen).

<img src="ui_overview_tabs.png" alt="Editor tabs" width="706"/>

See more details on working with tabs in the editor in [Editor tabs](https://www.jetbrains.com/help/idea/using-code-editor.html#manage_tabs). For UI component reference, go to [Tabs](tabs.md).

### Inspection widget
A small control in the top-right corner of the editor shows the number of errors, warnings, and typos and allows navigating to the detailed view in the Project tool window.

<img src="ui_overview_inspection_widget.png" alt="Inspection widget" width="706"/>

See more in [File and project analysis](https://www.jetbrains.com/help/idea/file-and-project-analysis.html#analysis-current-file).

### Gutter
A vertical area along the left side of the editor that provides important information and action icons within easy reach. It shows line numbers and buttons for folding, navigating, and running and debugging code along with VCS stripes and other controls.

<img src="ui_overview_gutter.png" alt="Editor gutter" width="706"/>

See more in [Editor gutter](https://www.jetbrains.com/help/idea/editor-gutter.html).

### Inlays

Hints that show additional information like parameter types or variable values and interactive controls for showing annotations, usages, and more.

<img src="ui_overview_inlays.png" alt="Inlays" width="706"/>

See more in [implementation details](inlay_hints.md) and [user guidelines](https://www.jetbrains.com/help/idea/inlay-hints.html).

### Floating toolbar
A small toolbar that appears when you select a code fragment in the editor. It contains buttons and controls for the most popular actions relevant in the current context,
such as quick fixes, AI-actions, basic refactorings, and code-formatting options:

<img src="ui_overview_floating_toolbar.png" alt="Floating toolbar" width="706"/>

See more in [Floating toolbar](https://www.jetbrains.com/help/idea/working-with-source-code.html#floating_toolbar).

## Status bar

The status bar on the bottom of the main window displays information on the current state of the project and the IDE as well as the path to the currently opened file:

<img src="ui_overview_status_bar.png" alt="Status bar" width="706"/>

### Navigation bar
The left part of the status bar by default is occupied by the navigation bar that is a compact alternative for the Project tool window.
It shows a path to the selected file and allows you to go to other files and directories using this path:

<img src="ui_overview_navigation_bar.png" alt="Navigation bar" width="706"/>

### Status bar widgets
The right part of the status bar contains status bar widgets which show statuses of various IDE and project settings and provide actions for changing these settings.
For example, the indent widget shows the number of spaces for code formatting in the current file and allows users to change this number:

<img src="ui_overview_status_bar_widget.png" alt="Status bar widget" width="706"/>

See more information on status bar widgets implementation in [Status Bar Widgets](status_bar_widgets.md).

## Dialogs

In the IntelliJ platform, dialogs are modal windows that appear over the main application screen and require user actions to proceed.
The dialogs might be used for different purposes, such as setting up the IDE and projects, creating new instances, asking for confirmations, and so on.

### Dialog examples

Settings:

<img src="ui_overview_settings.png" alt="Settings dialog" width="706"/>

Welcome screen:

<img src="ui_overview_welcome_screen.png" alt="Welcome screen" width="706"/>

New project wizard:

<img src="ui_overview_new_project.png" alt="New project wizard" width="706"/>


## Popups

A popup is a lightweight window that appears above the application screen. Unlike the dialogs, popups do not have the standard OS
title bar and are usually dismissed by clicking outside.

### Popup examples

* Completion popup:

<img src="ui_overview_completion.png" alt="Completion popup" width="706"/>

* Documentation popup:

<img src="ui_overview_documentation.png" alt="Documentation popup" width="706"/>


## Notifications

In IntelliJ Platform, different components are used to inform users about various events and status changes, depending on the severity and context of the message.

### Balloons

[Notification balloons](balloon.md) inform users on the events or system states related to a project or IDE:

<img src="ui_overview_balloon.png" alt="Notification balloon" width="706"/>

### Alerts

Alerts are modal windows that draw the user's attention to crucial information or ask for confirmation before proceeding:

<img src="ui_overview_alert.png" alt="Alert" width="706"/>

### Banners
[Banners](banner.md) appear on top of the editor or in tool windows. They inform the user about the state of a specific context and suggest context-related actions and quick fixes.

<img src="ui_overview_banner.png" alt="Banner" width="706"/>

## Context menus

Context menus list actions available in the current context and open on right-click:

<img src="ui_overview_menu.png" alt="Context menu" width="706"/>
