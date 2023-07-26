# Project View Pane Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Project View in IntelliJ SDK Docs][docs:project_view]*

## Quickstart

The current demo describes an implementation of the `com.intellij.projectViewPane` extension point, which allows creating an additional presentation type for the Project view pane.
`ImagesProjectViewPane` limits the project tree to the images only.

### Extension Points

| Name                           | Implementation                                      | Extension Point Class     |
|--------------------------------|-----------------------------------------------------|---------------------------|
| `com.intellij.projectViewPane` | [ImagesProjectViewPane][file:ImagesProjectViewPane] | `AbstractProjectViewPane` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:project_view]: https://plugins.jetbrains.com/docs/intellij/project-view.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:ImagesProjectViewPane]: ./src/main/java/org/intellij/sdk/view/pane/ImagesProjectViewPane.java
