[IntelliJ Platform SDK Code Samples](../README.md)

# Editor Sample Project

## Quickstart

TODO

## Structure

The plugin was developed using the [IntelliJ Platform SDK][docs_sdk].

The main file is [plugin.xml][plugin.xml], which is created accordingly to the [Plugin Configuration File documentation][docs_pluginxml].
It describes definitions of the actions, extensions, or listeners provided by the plugin:

### Extension Points

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| typedHandler | [MyTypedHandler][typedHandler_implementation] | [TypedHandlerDelegate][typedHandler_interface] |

[Extension Points documentation][docs_ep]

### Actions

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| EditorIllustrationAction | [EditorIllustrationAction][EditorIllustrationAction_implementation] | [AnAction][EditorIllustrationAction_interface] |
| EditorHandlerIllustration | [EditorHandlerIllustration][EditorHandlerIllustration_implementation] | [AnAction][EditorHandlerIllustration_interface] |
| LogicalPositionIllustration | [EditorAreaIllustration][LogicalPositionIllustration_implementation] | [AnAction][LogicalPositionIllustration_interface] |

[Actions documentation][docs_actions]

## Function

TODO


[plugin.xml]: ../inspection_basics/src/main/resources/META-INF/plugin.xml
[docs_tool_windows]: https://www.jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html
[docs_pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs_sdk]: https://www.jetbrains.org/intellij/sdk/docs/intro/about.html
[docs_ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs_run]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin
[docs_actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html

[typedHandler_implementation]: ./src/main/java/org/intellij/sdk/editor/MyTypedHandler.java
[typedHandler_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java

[EditorIllustrationAction_implementation]: ./src/main/java/org/intellij/sdk/editor/EditorIllustrationAction.java
[EditorIllustrationAction_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[EditorHandlerIllustration_implementation]: ./src/main/java/org/intellij/sdk/editor/EditorHandlerIllustration.java
[EditorHandlerIllustration_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[LogicalPositionIllustration_implementation]: ./src/main/java/org/intellij/sdk/editor/EditorAreaIllustration.java
[LogicalPositionIllustration_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
