[//]: # (title: Internal Actions - UI Inspector)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The _UI Inspector_ is a tool to interrogate elements of the IntelliJ IDEA UI to get an internal description of each element.
UI elements can be tested interactively by clicking on the element while the _UI Inspector_ is enabled.

<include src="internal_actions_intro.md" include-id="enable_internal_mode_tip"></include>

## Enabling the UI Inspector

Before using the _UI Inspector_, it must be enabled by selecting the menu item <menupath>Tools | Internal Actions | UI | UI Inspector</menupath>.
The enabled state of the _UI Inspector_ is modal; it remains enabled until it is disabled by selecting the _UI Inspector_ menu item again.

## Using the UI Inspector

While enabled, centering the cursor on a UI element and pressing <shortcut>Control/Cmd+Alt</shortcut> when _clicking_ the mouse reveals the properties of the Swing component.

For example, to get information about the _Build Project_ button's icon (hammer) on the toolbar (highlighted in green), put the mouse cursor on the icon and press <shortcut>Control/Cmd+Alt</shortcut> while clicking the mouse.

The _UI Inspector_ displays that the icon has the internal path `AllIcons.Actions.Compile`:

![Internal Icon Info](internal_uii_icon_info.png)

## Additional Properties

Various components used in the IntelliJ Platform expose additional properties.
These can be useful to locate the underlying implementation, related Action, etc.

| Type                                                                                                   | Place                       | Properties                                                                                                                                                                                                                                                         |
|--------------------------------------------------------------------------------------------------------|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`AnAction`](basic_action_system.md)                                                                   | Action Button<br/>Menu Item | `Action` - [`AnAction`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) implementation<br/>`Action ID` - Action `id`<br/>`Action Plugin ID` - contributing plugin                                                          |
| [`ActionToolbar`](basic_action_system.md)                                                              | Action Toolbar              | `Toolbar Group` - Action Group ID<br/>`All Groups` - contained Action Group IDs                                                                                                                                                                                    |
| [`DialogWrapper`](dialog_wrapper.md)                                                                   | Modal Dialog                | `dialogWrapperClass` - [`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) implementation                                                                                                                          |
| [`GutterMark`](upsource:///platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java) | Editor Gutter Icon          | `gutter renderer` - [`GutterMark`](upsource:///platform/editor-ui-api/src/com/intellij/codeInsight/daemon/GutterMark.java) implementation                                                                                                                          |
| [`IntentionAction`/`QuickFix`](code_inspections_and_intentions.md)                                     | Popup Menu in Editor        | `intention action`/`quick fix` - [`IntentionAction`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) / [`QuickFix`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/QuickFix.java) implementation |
| [`Tree`](lists_and_trees.md)                                                                           | Tree                        | `treeModelClass` - `javax.swing.tree.TreeModel` implementation                                                                                                                                                                                                     |

Custom Swing components can also provide additional properties via [`UiInspectorContextProvider`](upsource:///platform/platform-impl/src/com/intellij/internal/inspector/UiInspectorContextProvider.java) (2020.1 and later).
