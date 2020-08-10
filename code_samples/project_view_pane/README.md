# Project View Pane Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:project_view]*

## Quickstart

The current demo describes an implementation of the `projectViewPane` extension point, which allows to create
an additional presentation type for the Project view pane. `ImagesProjectViewPane` limits the project tree to the images
only.

## Structure

Project View Pane Demo
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name            | Implementation Class                                | Interface                                                    |
| --------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| projectViewPane | [ImagesProjectViewPane][file:ImagesProjectViewPane] | [AbstractProjectViewPSIPane][sdk:AbstractProjectViewPSIPane] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:project_view]: https://jetbrains.org/intellij/sdk/docs/basics/project_view.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:ImagesProjectViewPane]: ./src/main/java/org/intellij/sdk/view/pane/ImagesProjectViewPane.java

[sdk:AbstractProjectViewPSIPane]: upsource:///platform/lang-impl/src/com/intellij/ide/projectView/impl/AbstractProjectViewPSIPane.java
