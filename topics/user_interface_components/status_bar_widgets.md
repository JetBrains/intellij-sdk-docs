[//]: # (title: Status Bar Widgets)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<microformat>

**Product Help:** [Status bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#status-bar)

</microformat>

The IntelliJ Platform allows plugins to extend the IDE status bar with additional custom widgets.

Status bar widgets are small UI elements that allow providing users with useful information and settings for the current file, project, IDE, etc.
For example, the status bar contains the widget showing the encoding of the current file, or the current VCS branch of the project.

Due to the prominent presentation and limited space, they should be used only for information or settings that are relevant enough to be "always" shown.

The starting point for extending the status bar with new widgets is the
[`StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java)
interface, which is registered in the `com.intellij.statusBarWidgetFactory` extension point.

In case a widget provides information or functionality related to the editor files, consider extending the
[`StatusBarEditorBasedWidgetFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/widget/StatusBarEditorBasedWidgetFactory.java)
class.

Each widget factory returns a new widget from `createWidget()`.
To control the disposing of a widget, implement the `disposeWidget()`, if you just want to dispose it, use `Disposer.dispose(widget)`.

Any widget must implement the
[`StatusBarWidget`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/StatusBarWidget.java)
interface.

To reuse the IntelliJ Platform implementation, you can extend one of two classes:

- [`EditorBasedWidget`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/EditorBasedWidget.java)
- [`EditorBasedStatusBarPopup`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/EditorBasedStatusBarPopup.java)

## EditorBasedWidget

`EditorBasedWidget` is the basic widget implementation.
To implement it, override `ID()` where returns the unique ID of the widget.
This identifier may be needed to later obtain a widget instance.

Use one of the existing predefined widget appearance options:

- `com.intellij.openapi.wm.StatusBarWidget.IconPresentation`

  Widget with only an icon.

  Example:
  [PowerSaveStatusWidgetFactory](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/PowerSaveStatusWidgetFactory.java)

- `com.intellij.openapi.wm.StatusBarWidget.TextPresentation`

  Widget with only a text.

  Example:
  [PositionPanel](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/PositionPanel.java)

- `com.intellij.openapi.wm.StatusBarWidget.MultipleTextValuesPresentation`

  Widget with a text and a popup.

  Example:
  [DvcsStatusWidget](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/ui/DvcsStatusWidget.java)

> Note that they can't be combined to get, for example, an icon and a text.
>
{type="note"}

To use the selected appearance, return a class that implements one of the above interfaces from `getPresentation()`.

To create a widget with custom content, it should implement the
[`CustomStatusBarWidget`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/CustomStatusBarWidget.java)
interface.
Override `getComponent()` to return the custom widget's component to display.

Example:
[MemoryUsagePanel](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/MemoryUsagePanel.java)

## EditorBasedStatusBarPopup

`EditorBasedStatusBarPopup` is the basis for all widgets that have a popup with a list of actions.
For example, the encoding widget of the current file.

The component to display is returned from `createComponent()`.
Each update of the widget IDE calls `updateComponent()` to update this component.
In `updateComponent()` implementation, you can describe how the widget should change depending on the current state.

Implement `getWidgetState()` to return the current state of the widget.
This state will be passed to the `updateComponent()` when the widget is updated.
The method accepts a file that's currently opened in the editor
To create your own state class, inherit it from `EditorBasedStatusBarPopup.WidgetState.WidgetState`.

Implement `ID()`, and return the unique ID of the widget.
This identifier may be needed to later get a widget instance.

Implement `createInstance()`, and return the new widget instance.

Finally, implement the `createPopup()` method, which returns the [popup](popups.md) that will be displayed when the widget is clicked.

Custom listeners to be notified of widget updates can be registered using `registerCustomListeners()`.

To update a widget, use `update()`.

## Controlling Widgets Programmatically

By default, when adding a widget to the status bar, it can be displayed/hidden through the context menu of the status bar or widget.

If you want to change visibility programmatically use
[`StatusBarWidgetSettings.setEnabled()`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/widget/StatusBarWidgetSettings.kt).

The first argument to the method is the factory that created the widget.
To get it, use
[`StatusBarWidgetsManager.findWidgetFactory()`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/widget/StatusBarWidgetsManager.java)
and pass the widget ID and a boolean value that describes whether the widget will be visible or not.

Also, you need to update the widget for the changes to take effect with
[`StatusBarWidgetsManager.updateWidget()`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/widget/StatusBarWidgetsManager.java).

## Showing Widget in LightEdit Mode

By default, widgets aren't shown in [LightEdit](https://www.jetbrains.com/help/idea/lightedit-mode.html) mode.
To show a widget, implement
[`LightEditCompatible`](%gh-ic%/platform/core-api/src/com/intellij/ide/lightEdit/LightEditCompatible.java)
in your factory.
