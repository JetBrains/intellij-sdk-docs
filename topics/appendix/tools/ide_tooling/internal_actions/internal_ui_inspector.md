# Internal Actions - UI Inspector

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>UI Inspector allows checking properties of a component selected in the frame of running IDE instance.</link-summary>

The _UI Inspector_ is a tool to interrogate elements of the IntelliJ IDEA UI to get an internal description of each element.
UI elements can be inspected interactively by clicking on the element while the _UI Inspector_ is enabled.

<include from="internal_actions_intro.md" element-id="enable_internal_mode_tip"></include>

## Enabling the UI Inspector

> This step isn't required when using 2021.1 release or later.

Before using the _UI Inspector_, it must be enabled by selecting the menu item <ui-path>Tools | Internal Actions | UI | UI Inspector</ui-path>.
The enabled state of the _UI Inspector_ is modal; it remains enabled until it is disabled by selecting the _UI Inspector_ menu item again.

## Using the UI Inspector

While enabled, centering the cursor on a UI element and pressing <shortcut>Ctrl/Cmd+Alt</shortcut> when _clicking_ the mouse reveals the properties of the Swing component.

For example, to get information about the _Build Project_ button's icon (hammer) on the toolbar (highlighted in green), put the mouse cursor on the icon and press <shortcut>Ctrl/Cmd+Alt</shortcut> while clicking the mouse.

The _UI Inspector_ displays the icon details:

![Internal Icon Info](internal_ui_inspector_icon_info.png)

## Additional Properties

### added-at Property

Sometimes, inspecting complex component's properties is not enough to understand how the component was created and configured.
_UI Inspector_ gives the possibility of finding the code where the selected component was added, which makes it much easier to understand which APIs can be used to build custom components with similar complexity.
To find the place were component was added, select the `added-at` property to show the stacktrace:

![added-at Stacktrace](internal_ui_inspector_added_at.png)

### Specific Component Properties

Various components used in the IntelliJ Platform expose additional properties.
These can be useful to locate the underlying implementation, related Action, etc.

| Type                                                                                               | Place                       | Properties                                                                                                                                                                                                                                                 |
|----------------------------------------------------------------------------------------------------|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`AnAction`](basic_action_system.md)                                                               | Action Button<br/>Menu Item | `Action` - [`AnAction`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) implementation<br/>`Action ID` - Action `id`<br/>`Action Plugin ID` - contributing plugin                                                      |
| [`ActionToolbar`](basic_action_system.md)                                                          | Action Toolbar              | `Toolbar Group` - Action Group ID<br/>`All Groups` - contained Action Group IDs                                                                                                                                                                            |
| [`DialogWrapper`](dialog_wrapper.md)                                                               | Modal Dialog                | `dialogWrapperClass` - [`DialogWrapper`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) implementation                                                                                                                      |
| [`GutterMark`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java) | Editor Gutter Icon          | `gutter renderer` - [`GutterMark`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java) implementation                                                                                                                      |
| [`IntentionAction`/`QuickFix`](code_inspections_and_intentions.md)                                 | Popup Menu in Editor        | `intention action`/`quick fix` - [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) / [`QuickFix`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/QuickFix.java) implementation |
| [`Tree`](lists_and_trees.md)                                                                       | Tree                        | `treeModelClass` - `javax.swing.tree.TreeModel` implementation                                                                                                                                                                                             |

Custom Swing components can also provide additional properties via [`UiInspectorContextProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/inspector/UiInspectorContextProvider.java) (2020.1 and later).

## Settings

Some additional properties are available when inspecting Settings dialog (2023.1+).

| Settings page                                 | Properties                                                                                                                                                                                                                                                                | Reference                                                           |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| _All settings_                                | <control>Configurable class</control> - [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java) class<br/><control>Configurable ID</control> - `id` attribute<br/><control>Configurable weight</control> - `groupWeight` attribute | [](settings_guide.md)                                               |
| <ui-path>Editor &#124; Color Scheme</ui-path> | <control>Text Attributes Key</control> - [`TextAttributesKey`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java) external name                                                                                                     | [](syntax_highlighting_and_error_highlighting.md#textattributeskey) |
| <ui-path>Editor &#124; File Types</ui-path>   | <control>FileTypeID</control> - `FileType.getName()`<br/><control>FileType Class</control> - [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) implementation                                                                      | [](registering_file_type.md)                                        |
| <ui-path>Editor &#124; Inspections</ui-path>  | <control>Inspection key</control> - Inspection `id`<br/><control>Inspection tool class</control> - Inspection implementation                                                                                                                                              | [](code_inspections.md)                                             |
| <ui-path>Plugins</ui-path>                    | <control>Plugin ID</control> - Plugin `<id>`<br/><control>Plugin Dependencies</control> - IDs of dependent plugins                                                                                                                                                        | [](plugin_configuration_file.md)                                    |
