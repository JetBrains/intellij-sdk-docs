# Project Model Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [PyCharm Plugin Development in IntelliJ SDK Docs][docs:pycharm]*

## Quickstart

Project Model Sample project provides five actions that present data extracted using `ProjectRootManager` instance in the message dialogs. Within the implemented actions, you will be able to:
- fetch libraries used in the project,
- retrieve the information about the module details,
- rename the used SDK,
- get the content source roots,
- or extend the project dependencies with an additional library.

### Actions

| ID                                | Implementation                                                    | Extension Point Class    |
| --------------------------------- | ----------------------------------------------------------------- | ------------------------ |
| `ProjectModel.SourceRoots`        | [ShowSourceRootsActions][file:ShowSourceRootsActions]             | [AnAction][sdk:AnAction] |
| `ProjectModel.ProjectSdk`         | [ProjectSdkAction][file:ProjectSdkAction]                         | [AnAction][sdk:AnAction] |
| `ProjectModel.ProjectFileIndex`   | [ProjectFileIndexSampleAction][file:ProjectFileIndexSampleAction] | [AnAction][sdk:AnAction] |
| `ProjectModel.ModificationAction` | [ModificationAction][file:ModificationAction]                     | [AnAction][sdk:AnAction] |
| `ProjectModel.LibrariesAction`    | [LibrariesAction][file:LibrariesAction]                           | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:pycharm]: https://jetbrains.org/intellij/sdk/docs/products/pycharm.html

[file:ShowSourceRootsActions]: ./src/main/java/org/intellij/sdk/project/model/ShowSourceRootsActions.java
[file:ProjectSdkAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java
[file:ProjectFileIndexSampleAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java
[file:ModificationAction]: ./src/main/java/org/intellij/sdk/project/model/ModificationAction.java
[file:LibrariesAction]: ./src/main/java/org/intellij/sdk/project/model/LibrariesAction.java

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
