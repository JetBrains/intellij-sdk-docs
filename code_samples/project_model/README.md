# Project Model Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Project in IntelliJ SDK Docs][docs:project], [SDK in IntelliJ SDK Docs][docs:sdk], [Library in IntelliJ SDK Docs][docs:library]*

## Quickstart

Project Model Sample project provides five actions that present data extracted using `ProjectRootManager` instance in the message dialogs.
Within the implemented actions, you will be able to:
- fetch libraries used in the project,
- retrieve the information about the module details,
- rename the used SDK,
- get the content source roots,
- or extend the project dependencies with an additional library.

### Actions

| ID                                | Implementation                                                    | Base Action Class |
|-----------------------------------|-------------------------------------------------------------------|-------------------|
| `ProjectModel.SourceRoots`        | [ShowSourceRootsActions][file:ShowSourceRootsActions]             | `AnAction`        |
| `ProjectModel.ProjectSdk`         | [ProjectSdkAction][file:ProjectSdkAction]                         | `AnAction`        |
| `ProjectModel.ProjectFileIndex`   | [ProjectFileIndexSampleAction][file:ProjectFileIndexSampleAction] | `AnAction`        |
| `ProjectModel.ModificationAction` | [ModificationAction][file:ModificationAction]                     | `AnAction`        |
| `ProjectModel.LibrariesAction`    | [LibrariesAction][file:LibrariesAction]                           | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/action-system.html
[docs:project]: https://plugins.jetbrains.com/docs/intellij/project.html
[docs:sdk]: https://plugins.jetbrains.com/docs/intellij/sdk.html
[docs:library]: https://plugins.jetbrains.com/docs/intellij/library.html

[file:ShowSourceRootsActions]: ./src/main/java/org/intellij/sdk/project/model/ShowSourceRootsActions.java
[file:ProjectSdkAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java
[file:ProjectFileIndexSampleAction]: ./src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java
[file:ModificationAction]: ./src/main/java/org/intellij/sdk/project/model/ModificationAction.java
[file:LibrariesAction]: ./src/main/java/org/intellij/sdk/project/model/LibrariesAction.java
