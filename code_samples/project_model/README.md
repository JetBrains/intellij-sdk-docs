# Project Model Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [PyCharm Plugin Development in IntelliJ SDK Docs][docs:pycharm]*

## Quickstart

Project Model Sample project provides five actions that present data extracted using `ProjectRootManager` instance
in the message dialogs. Within the implemented actions, you will be able to:
- fetch libraries used in the project,
- retrieve the information about the module details,
- rename the used SDK,
- get the content source roots,
- or extend the project dependencies with an additional library.

## Structure

Project Model Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Actions

| Name               | Implementation Class                                              | Interface                |
| ------------------ | ----------------------------------------------------------------- | ------------------------ |
| SourceRoots        | [ShowSourceRootsActions][file:ShowSourceRootsActions]             | [AnAction][sdk:AnAction] |
| ProjectSdk         | [ProjectSdkAction][file:ProjectSdkAction]                         | [AnAction][sdk:AnAction] |
| ProjectFileIndex   | [ProjectFileIndexSampleAction][file:ProjectFileIndexSampleAction] | [AnAction][sdk:AnAction] |
| ModificationAction | [ModificationAction][file:ModificationAction]                     | [AnAction][sdk:AnAction] |
| LibrariesAction    | [LibrariesAction][file:LibrariesAction]                           | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:pycharm]: https://jetbrains.org/intellij/sdk/docs/products/pycharm.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:ShowSourceRootsActions]: ./src/main/java/org/intellij/sdk/project/model/ShowSourceRootsActions.java
[file:ProjectSdkAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java
[file:ProjectFileIndexSampleAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java
[file:ModificationAction]: ./src/main/java/org/intellij/sdk/project/model/ModificationAction.java
[file:LibrariesAction]: ./src/main/java/org/intellij/sdk/project/model/LibrariesAction.java

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
