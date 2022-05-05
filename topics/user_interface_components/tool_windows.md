[//]: # (title: Tool Windows)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

_Tool windows_ are child windows of the IDE used to display information.
These windows generally have their own toolbars (referred to as _tool window bars_) along the outer edges of the main window containing one or more _tool window buttons_, which activate panels displayed on the left, bottom and right sides of the main IDE window.
For detailed information about tool windows, please see [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/tool-windows.html) and [Tool window](https://jetbrains.design/intellij/components/tool_window/) topic in IntelliJ Platform UI Guidelines.

Each side contains two tool window groups, the primary and the secondary one, and only one tool window from each group can be active at a time.

Each tool window can show multiple tabs (or "contents", as they are called in the API).
For example, the Run tool window displays a tab for each active run configuration, and the Changes/Version Control tool window displays a fixed set of tabs depending on the version control system used in the project.

There are two main scenarios for the use of tool windows in a plugin.
Using [declarative setup](#declarative-setup), a tool window button is always visible, and the user can activate it and interact with the plugin functionality at any time.
Alternatively, using [programmatic setup](#programmatic-setup), the tool window is created to show the results of a specific operation, and can be closed by the user after the operation is completed.

Project-level topic [`ToolWindowManagerListener`](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/ex/ToolWindowManagerListener.java) allows listening to tool window (un-)registering/show events (see [](plugin_listeners.md)).

### Declarative Setup

The tool window is registered in <path>plugin.xml</path> using the `com.intellij.toolWindow` extension point.
The extension point attributes specify all the data which is necessary to display the tool window button:

*  The `id` of the tool window - corresponds to the text displayed on the tool window button.
To provide a localized text, specify matching `toolwindow.stripe.[id]` message key (escape spaces with `_`) in your [message bundle](localization_guide.md) (code insight supported in 2020.3 and later).

*  The `icon` to display on the tool window button (13x13 pixels, grey and monochromatic; see [Tool window](https://jetbrains.design/intellij/components/tool_window/#07) in IntelliJ Platform UI Guidelines and [Working with Icons and Images](work_with_icons_and_images.md))

*  The `anchor`, meaning the side of the screen on which the tool window is displayed ("left" (default), "right" or "bottom")

*  The `secondary` attribute, specifying whether the tool window is displayed in the primary or the secondary group

In addition to that, specify the `factoryClass` attribute - the name of a class implementing the [`ToolWindowFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) interface.
When the user clicks on the tool window button, the `createToolWindowContent()` method of the factory class is called, and initializes the UI of the tool window.
This procedure ensures that unused tool windows don't cause any overhead in startup time or memory usage: if a user does not interact with the tool window, no plugin code will be loaded or executed.

If the tool window of a plugin doesn't need to be displayed for all projects:

<tabs>

<tab title="2021.1 and later">

Implement the `isApplicable(Project)` method.

</tab>

<tab title="2019.3 and earlier">

Specify the `conditionClass` attribute in <path>plugin.xml</path> with a class implementing [`Condition<Project>`](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) (can be the same class as the `ToolWindowFactory` implementation).

</tab>

</tabs>

Note, the condition is evaluated only once when the project is loaded.
To show and hide a tool window dynamically while the user is working with the project, use [programmatic setup](#programmatic-setup) for tool window registration.

### Programmatic Setup

The second method involves simply calling [`ToolWindowManager.registerToolWindow()`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowManager.kt) from the plugin code.
The method has multiple overloads that can be used depending on the task.
When using an overload that takes a component, the component becomes the first content (tab) displayed in the tool window.

## Contents (Tabs)

Displaying the contents of many tool windows requires access to [indexes](indexing_and_psi_stubs.md).
Because of that, tool windows are normally disabled while building indexes unless the `ToolWindowFactory` implements [`DumbAware`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbAware.java).
For programmatic setup, parameter `canWorkInDumbMode` must be set to `true` in calls to `registerToolWindow()`.

As mentioned previously, tool windows can contain multiple contents (tabs).
To manage the contents of a tool window, call [`ToolWindow.getContentManager()`](upsource:///platform/ide-core/src/com/intellij/openapi/wm/ToolWindow.java).
To add a content (tab), first create it by calling [`ContentManager.getFactory().createContent()`](upsource:///platform/ide-core/src/com/intellij/ui/content/ContentManager.java), and then to add it to the tool window using [`ContentManager.addContent()`](upsource:///platform/ide-core/src/com/intellij/ui/content/ContentManager.java).
Use `Content.setDisposer()` to register associated `Disposable` (see [](disposers.md)).

### Closing Tabs

A plugin can control whether the user is allowed to close tabs either globally or on a per-content basis.
The former is done by passing the `canCloseContents` parameter to the `registerToolWindow()` function, or by specifying `canCloseContents="true"` in <path>plugin.xml</path>.
The default value is `false`; calling `setClosable(true)` on `ContentManager` content will be ignored unless `canCloseContents` is explicitly set.
If closing tabs is enabled in general, a plugin can disable closing of specific tabs by calling [`Content.setCloseable(false)`](upsource:///platform/ide-core/src/com/intellij/ui/content/Content.java).

## Sample Plugin

To clarify how to develop plugins that create tool windows, consider the **toolWindow** sample plugin available in the [code samples](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/tool_window).

See [Code Samples](code_samples.md) on how to set up and run the plugin.

This plugin creates the <control>Sample Calendar</control> tool window that displays the system date, time and time zone.
When opened, this tool window is similar to the following screen:

![Sample Calendar](sample_calendar.png)
