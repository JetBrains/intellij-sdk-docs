<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Status Bar Widgets

<link-summary>Extending the status bar with custom widgets providing information about the current file, project, IDE, and similar.</link-summary>

<tldr>

**Product Help:** [Status bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#status-bar)

</tldr>

The IntelliJ Platform allows plugins to extend the IDE status bar with additional custom widgets.

Status bar widgets are small UI elements that allow providing users with useful information and settings for the current file, project, IDE, and similar.
For example, the status bar contains the widget showing the encoding of the current file, or the current VCS branch of the project.

Due to the prominent presentation and limited space, they should be used only for information or settings that are relevant enough to be "always" shown.

The starting point for extending the status bar with new widgets is the
[`StatusBarWidgetFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/StatusBarWidgetFactory.java)
interface, which is registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.statusBarWidgetFactory"/></include>.
Note: `id` attribute must be provided in <path>plugin.xml</path> registration and match value from `StatusBarWidgetFactory.getId()`.

In case a widget provides information or functionality related to the editor files, consider extending the
[`StatusBarEditorBasedWidgetFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/widget/StatusBarEditorBasedWidgetFactory.kt)
class.

Each widget factory returns a new widget from `createWidget()`.
To control the disposing of a widget, implement the `disposeWidget()`.
To dispose it, use `Disposer.dispose(widget)`.

Any widget must implement the
[`StatusBarWidget`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/wm/StatusBarWidget.kt)
interface.

To reuse the IntelliJ Platform implementation, you can extend one of two classes:

- [`EditorBasedWidget`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/EditorBasedWidget.kt)
- [`EditorBasedStatusBarPopup`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/EditorBasedStatusBarPopup.kt)

## `EditorBasedWidget`

`EditorBasedWidget` is the basic widget implementation.
To implement it, override `ID()` which returns the unique ID of the widget.
This identifier may be necessary to later get a widget instance.

Use one of the existing predefined widget appearance options:

- `com.intellij.openapi.wm.StatusBarWidget.IconPresentation`

  Widget with only an icon.

  Example:
  [`PowerSaveStatusWidgetFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/PowerSaveStatusWidgetFactory.java)

- `com.intellij.openapi.wm.StatusBarWidget.TextPresentation`

  Widget with only a text.

  Example:
  [`SkipWindowDeactivationEventsAction.StatusWidget`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/SkipWindowDeactivationEventsAction.kt)

- `com.intellij.openapi.wm.StatusBarWidget.MultipleTextValuesPresentation`

  Widget with a text and a popup.

  Example:
  [`DvcsStatusWidget`](%gh-ic%/platform/dvcs-impl/src/com/intellij/dvcs/ui/DvcsStatusWidget.java)

> Note that they can't be combined to get, for example, an icon and a text.
>
{style="note"}

To use the selected appearance, return a class that implements one of the above interfaces from `getPresentation()`.

To create a widget with custom content, it should implement the
[`CustomStatusBarWidget`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/CustomStatusBarWidget.java)
interface.
Override `getComponent()` to return the custom widget's component to display.

Example:
[`MemoryUsagePanel`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/wm/impl/status/MemoryUsagePanel.java)

## `EditorBasedStatusBarPopup`

`EditorBasedStatusBarPopup` is the basis for all widgets that have a popup with a list of actions.
For example, the encoding widget of the current file.

The component to display is returned from `createComponent()`.
Each update of the widget IDE calls `updateComponent()` to update this component.
In `updateComponent()` implementation, you can describe how the widget should change depending on the current state.

Implement `getWidgetState()` to return the current state of the widget.
This state will be passed to the `updateComponent()` when the widget is updated.
The method accepts a file currently opened in the editor.
To create your own state class, inherit it from `EditorBasedStatusBarPopup.WidgetState.WidgetState`.

Implement `ID()` and return the unique ID of the widget.
This identifier may be necessary to later get a widget instance.

Implement `createInstance()` and return the new widget instance.

Finally, implement the `createPopup()` method, which returns the [popup](popups.md) that will be displayed when the widget is clicked.

Custom listeners to be notified of widget updates can be registered using `registerCustomListeners()`.

To update a widget, use `update()`.

## Showing Widget in LightEdit Mode

By default, widgets aren't shown in [LightEdit](https://www.jetbrains.com/help/idea/lightedit-mode.html) mode.
To show a widget, implement
[`LightEditCompatible`](%gh-ic%/platform/core-api/src/com/intellij/ide/lightEdit/LightEditCompatible.java)
in your factory.

## FAQ

### How to get a widget programmatically?

```kotlin
val widget = WindowManager.getInstance().getStatusBar(project)
    .getWidget(MyWidget.ID) as MyWidget

```
