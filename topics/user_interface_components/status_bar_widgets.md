[//]: # (title: Status Bar Widgets)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The status bar widget implementation is used to add new widgets to the
[status bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#status-bar).

Widgets on the status bar are small interface elements that allow you to provide the user important information about the current file or the project.
For example, the widget showing the encoding of the current file or the current VCS branch of the project.

The starting point for extending the status bar with new widgets is the
[`StatusBarWidgetFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java),
interface, which is registered in the `com.intellij.statusBarWidgetFactory` extension point.

To reuse the IntelliJ Platform implementation, you can extend the
[`StatusBarEditorBasedWidgetFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/impl/status/widget/StatusBarEditorBasedWidgetFactory.java)
class.

> Note that widgets inherited from this class will only work when at least one file is open.
> If you want your widget to work regardless of open files, implement the `StatusBarWidgetFactory` directly.
>
{type="note"}

Each widget factory returns a new widget from `createWidget()`.
To control the disposing of a widget, implement the `disposeWidget()`, if you just want to dispose it, use `Disposer.dispose(widget)`.

Any widget should implement the
[`StatusBarWidget`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidget.java)
interface.

To reuse the IntelliJ Platform implementation, you can extend one of two classes:

- [`EditorBasedWidget`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/impl/status/EditorBasedWidget.java).
- [`EditorBasedStatusBarPopup`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/impl/status/EditorBasedStatusBarPopup.java).

## `EditorBasedWidget`

`EditorBasedWidget` is the basic widget implementation.
To implement it, override `ID()` where returns the unique ID of the widget.
This identifier may be needed to later get a widget instance.

The IntelliJ Platform provides several predefined widget appearance options:

- `com.intellij.openapi.wm.StatusBarWidget.IconPresentation`

  Widget with only an icon.

  Example:
  [PowerSaveStatusWidgetFactory](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/status/PowerSaveStatusWidgetFactory.java)

- `com.intellij.openapi.wm.StatusBarWidget.TextPresentation`

  Widget with only a text.

  Example:
  [PositionPanel](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/status/PositionPanel.java)

- `com.intellij.openapi.wm.StatusBarWidget.MultipleTextValuesPresentation`

  Widget with a text and a popup.

  Example:
  [DvcsStatusWidget](upsource:///platform/dvcs-impl/src/com/intellij/dvcs/ui/DvcsStatusWidget.java)

> Note that they can't be combined to get, for example, an icon and a text.
>
{type="note"}

To use the selected appearance, return a class implemented one of the interfaces above from `getPresentation()`.

To create a widget with custom content, it should implement the
[`CustomStatusBarWidget`](upsource:///platform/platform-api/src/com/intellij/openapi/wm/CustomStatusBarWidget.java)
interface.
Override `getComponent()` to return the custom widget's component to display.

Example:
[MemoryUsagePanel](upsource:///platform/platform-impl/src/com/intellij/openapi/wm/impl/status/MemoryUsagePanel.java)

## `EditorBasedStatusBarPopup`

`EditorBasedStatusBarPopup` is the basis for all widgets that have a popup with a list of actions.
For example, the encoding widget of the current file.

First, implement `createComponent()`, where returns a component for display.
Each update of the widget IDE calls `updateComponent()` to update this component.
In `updateComponent()` implementation, you can describe how the widget should change depending on the current state.

Implement `getWidgetState()` where returns the current state of the widget.
This state will be passed to the `updateComponent()` when the widget is updated.
The method accepts a file that's now open in the editor.
To create your own state class, inherit it from `com.intellij.openapi.wm.impl.status.EditorBasedStatusBarPopup.WidgetState.WidgetState`.

Implement `ID()`, where returns the unique ID of the widget.
This identifier may be needed to later get a widget instance.

Implement `createInstance()`, where returns the new widget instance.

Finally, implement the `createPopup()` method where returns the [popup](popups.md) that will be displayed when the widget is clicked.

Also, it can be useful to override `registerCustomListeners()` to add custom listeners.
This can be useful if you want to set custom listeners for a widget that will update it.

To update a widget, use `update()`.

## How to change visibility programmatically

By default, when adding a widget to the status bar, it can be displayed/hidden through the context menu of the status bar or widget.

If you want to change visibility programmatically use
`com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetSettings.setEnabled()`.

The first argument to the method is the factory that created the widget.
To get it, use `com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetsManager.findWidgetFactory()` where pass the widget ID and a boolean value that describes whether the widget will be visible or not.

Also, you need to [update](#how-to-update-widget-programmatically) the widget for the changes to take effect.

## How to update widget programmatically

To update a widget, use `com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetsManager.updateWidget()`.

## How to show widget in LightEdit mode

By default, widgets aren't shown in LightEdit mode.
To show a widget, implement the `com.intellij.ide.lightEdit.LightEditCompatible` interface in your factory.
