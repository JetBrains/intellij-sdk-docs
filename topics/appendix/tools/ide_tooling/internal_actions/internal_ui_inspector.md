# Internal Actions - UI Inspector

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>UI Inspector allows checking properties of a component selected in the frame of running IDE instance.</link-summary>

The _UI Inspector_ is a tool to interrogate elements of the IDE's UI to get an internal description of each element.

<include from="internal_actions_intro.md" element-id="enable_internal_mode_tip"></include>

## Enabling the UI Inspector

> This step isn't required when using 2021.1 release or later.

Before using the _UI Inspector_, it must be enabled by selecting the menu item <ui-path>Tools | Internal Actions | UI | UI Inspector</ui-path>.
The enabled state of the _UI Inspector_ is modal; it remains enabled until it is disabled by selecting the menu item again.

## Using the UI Inspector

Centering the cursor on a UI element and pressing <shortcut>Ctrl/Cmd+Alt</shortcut> when _clicking_ the left mouse button reveals the properties of the Swing component.

For example, to get information about the <control>Build Project</control> button's "hammer" icon on the toolbar (highlighted in green), put the mouse cursor on the icon and press <shortcut>Ctrl/Cmd+Alt</shortcut> while clicking the mouse.

The _UI Inspector_ displays the icon details:

![Internal Icon Info](internal_ui_inspector_icon_info.png)

## added-at Property

Sometimes, inspecting complex component's properties is not enough to understand how the component was created and configured.
_UI Inspector_ gives the possibility of finding the code where the selected component was added, which makes it much easier to understand which APIs can be used to build custom components with similar complexity.

To find the place were component was added, select the <control>added-at</control> property to show the stacktrace:

![added-at Stacktrace](internal_ui_inspector_added_at.png)

## Specific Component Properties

Various components used in the IntelliJ Platform expose additional properties.
These can be useful to locate the underlying implementation, related Action, etc.

Custom Swing components can also provide additional properties via [`UiInspectorContextProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/inspector/UiInspectorContextProvider.java) or its dedicated subclasses (2020.1 and later).

### Editor

| Type                                                                                                                                    | Properties                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`ActiveGutterRenderer`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/markup/ActiveGutterRenderer.java)<br/>(2023.1+) | <control>Clicked Renderer (Class)</control> - `ActiveGutterRenderer` instance/class                                                                                                                                                                                                                                                                                                                                                              |
| [`GutterIconRenderer`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/markup/GutterIconRenderer.java)<br/>(2023.1+)     | <control>Clicked Renderer (Class)</control> - `GutterIconRenderer` instance/class<br/><control>Accessible Name</control> - `GutterIconRenderer.getAccessibleName()`<br/><control>Icon</control> - `GutterIconRenderer.getIcon()`<br/><control>Marker Info - Element / Navigation Handler</control> - [`LineMarkerInfo.getElement() / getNavigationHandler()`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerInfo.java) |
| [`GutterMark`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java)                                      | <control>gutter renderer</control> - [`GutterMark`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java) implementation                                                                                                                                                                                                                                                                                           |
| [`Inlay`](inlay_hints.md)<br/>(2023.1+)                                                                                                 | <control>Inlay Renderer (Class)</control> - `Inlay.getRenderer()` instance/class<br/><control>Inlay Gutter Renderer</control> - `Inlay.getGutterIconRenderer()`<br/><control>Inlay Properties</control> - `Inlay.getProperties()`                                                                                                                                                                                                                |
| [`IntentionAction`/`QuickFix`](code_inspections_and_intentions.md)                                                                      | <control>intention action</control>/<control>quick fix</control> - [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) / [`QuickFix`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/QuickFix.java) implementation                                                                                                                                                     |

### Action

| Type                                      | Properties                                                                                                                                                                                                                                                |
|-------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`AnAction`](basic_action_system.md)      | <control>Action</control> - [`AnAction`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) implementation<br/><control>Action ID</control> - Action `id`<br/><control>Action Plugin ID</control> - contributing plugin  |
| [`ActionToolbar`](basic_action_system.md) | <control>Toolbar Group</control> - Action Group ID<br/><control>All Toolbar Groups</control> - contained Action Group IDs<br/><control>Target component</control> - `ActionToolbar.setTargetComponent()`                                                  |

### IDE and UI Components

| Type                                 | Properties                                                                                                                                                                                                                                     |
|--------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`DialogWrapper`](dialog_wrapper.md) | <control>dialogWrapperClass</control> - [`DialogWrapper`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) implementation                                                                                         |
| [`ToolWindow`](tool_windows.md)      | <control>Tool Window ID</control> - `id`<br/><control>Tool Window Icon</control> - `icon`<br/><control>Tool Window Factory</control> - [`ToolWindowFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java) |
| [`Tree`](lists_and_trees.md)         | <control>treeModelClass</control> - `javax.swing.tree.TreeModel` implementation                                                                                                                                                                |

## Inspecting Settings

Some additional properties are available when inspecting <control>Settings</control> dialog (2023.1+).

> _UI Inspector_ must be invoked only after opening the <control>Settings</control> dialog.

| Settings page<br/>Reference                                                                                           | Properties                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
|-----------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| _All settings_<br/>[](settings_guide.md)                                                                              | <control>Configurable class</control> - [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) class<br/><control>Configurable ID</control> - `id` attribute<br/><control>Configurable weight</control> - `groupWeight` attribute                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| <ui-path>Editor &#124; Color Scheme</ui-path><br/>[](syntax_highlighting_and_error_highlighting.md#textattributeskey) | <control>Text Attributes Key</control> - [`TextAttributesKey`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java) external name                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| <ui-path>Editor &#124; File Types</ui-path><br/>[](registering_file_type.md)                                          | <control>FileTypeID</control> - `FileType.getName()`<br/><control>FileType Class</control> - [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) implementation                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| <ui-path>Editor &#124; Inspections</ui-path><br/>[](code_inspections.md)                                              | <control>Inspection key</control> - Inspection `id`<br/><control>Inspection tool class</control> - Inspection implementation                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| <ui-path>Editor &#124; Inlays</ui-path><br/>[](inlay_hints.md)                                                        | <control>Inlay Group Key</control> - [`InlayGroupSettingProvider.getGroup().key`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayGroupSettingProvider.kt)<br/><control>Inlay Group Key</control> - [`InlayGroup.key`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt)<br/><control>Inlay Provider Model ID</control> - [`InlayProviderSettingsModel.id`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/settings/InlayProviderSettingsModel.kt)<br/><control>Inlay ImmediateConfigurable ID</control> - [`ImmediateConfigurable.Case.id`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt) |
| <ui-path>Editor &#124; Intentions</ui-path><br/>[](code_intentions.md)                                                | <control>Intention Class</control> - [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) class<br/><control>Intention description directory</control> - `<descriptionDirectoryName>`                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| <ui-path>Plugins</ui-path><br/>[](plugin_configuration_file.md)                                                       | <control>Plugin ID</control> - Plugin `<id>`<br/><control>Plugin Dependencies</control> - IDs of dependent plugins                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
