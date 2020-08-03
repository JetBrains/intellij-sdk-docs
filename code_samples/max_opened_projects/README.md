# Maximum Open Projects Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:plugin_services]*

## Quickstart

Maximum Open Projects Sample implements a `ProjectManagerListener` with two methods implemented to check if the current
projects has been opened or closed. Each method refers to the `ProjectCountingService` service registered
as an `applicationService` extension point. It provides methods to increase and decrease global counter of the currently
opened projects in the IDE. After opening each one, a message dialog is presented to the user with the current number.

## Structure

Maximum Open Projects Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name               | Implementation Class                                  | Interface                                      |
| ------------------ | ----------------------------------------------------- | ---------------------------------------------- |
| applicationService | [ProjectCountingService][file:ProjectCountingService] |                                                |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

### Application Listeners

| Name     | Implementation Class                                      | Interface                                            |
| -------- | --------------------------------------------------------- | ---------------------------------------------------- |
| listener | [ProjectOpenCloseListener][file:ProjectOpenCloseListener] | [ProjectManagerListener][sdk:ProjectManagerListener] |

*Reference: [Plugin Listeners in IntelliJ SDK Docs][docs:listeners]*

[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:plugin_services]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_services.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:ProjectCountingService]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectCountingService.java
[file:ProjectOpenCloseListener]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectOpenCloseListener.java

[sdk:ProjectManagerListener]: https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java

[applicationListener_implementation]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectCountingService.java
[applicationListener_topic]: https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java
