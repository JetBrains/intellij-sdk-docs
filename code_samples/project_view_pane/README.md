# Project View Pane Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:project_view]*

## Quickstart

The current demo describes an implementation of the `projectViewPane` extension point, which allows creating an additional presentation type for the Project view pane.
`ImagesProjectViewPane` limits the project tree to the images only.

### Extension Points

| Name                           | Implementation                                      | Extension Point Class                                        |
| ------------------------------ | --------------------------------------------------- | ------------------------------------------------------------ |
| `com.intellij.projectViewPane` | [ImagesProjectViewPane][file:ImagesProjectViewPane] | [AbstractProjectViewPSIPane][sdk:AbstractProjectViewPSIPane] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:project_view]: https://jetbrains.org/intellij/sdk/docs/basics/project_view.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:ImagesProjectViewPane]: ./src/main/java/org/intellij/sdk/view/pane/ImagesProjectViewPane.java

[sdk:AbstractProjectViewPSIPane]: upsource:///platform/lang-impl/src/com/intellij/ide/projectView/impl/AbstractProjectViewPSIPane.java
