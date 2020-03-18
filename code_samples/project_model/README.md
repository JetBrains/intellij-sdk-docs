[IntelliJ Platform SDK Code Samples](../README.md)

# Project Model Sample Project

## Quickstart

TODO

## Structure

The plugin was developed using the [IntelliJ Platform SDK][docs_sdk].

The main file is [plugin.xml][plugin.xml], which is created accordingly to the [Plugin Configuration File documentation][docs_pluginxml].
It describes definitions of the actions, extensions, or listeners provided by the plugin:

### Actions

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| SourceRoots | [ShowSourceRootsActions][SourceRoots_implementation] | [AnAction][SourceRoots_interface] |
| ProjectSdk | [ProjectSdkAction][ProjectSdk_implementation] | [AnAction][ProjectSdk_interface] |
| ProjectFileIndex | [ProjectFileIndexSampleAction][ProjectFileIndex_implementation] | [AnAction][ProjectFileIndex_interface] |
| ModificationAction | [ModificationAction][ModificationAction_implementation] | [AnAction][ModificationAction_interface] |
| LibrariesAction | [LibrariesAction][LibrariesAction_implementation] | [AnAction][LibrariesAction_interface] |

[Actions documentation][docs_actions]

## Function

TODO

[plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[docs_tool_windows]: https://www.jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html
[docs_pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs_sdk]: https://www.jetbrains.org/intellij/sdk/docs/intro/about.html
[docs_ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs_run]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin
[docs_actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html

[SourceRoots_implementation]: ./src/main/java/org/intellij/sdk/project/model/ShowSourceRootsActions.java
[SourceRoots_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[ProjectSdk_implementation]: ./src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java
[ProjectSdk_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[ProjectFileIndex_implementation]: ./src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java
[ProjectFileIndex_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[ModificationAction_implementation]: ./src/main/java/org/intellij/sdk/project/model/ModificationAction.java
[ModificationAction_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
[LibrariesAction_implementation]: ./src/main/java/org/intellij/sdk/project/model/LibrariesAction.java
[LibrariesAction_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
