# Maximum Open Projects Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:plugin_services]*

## Quickstart

Maximum Open Projects Sample implements a `ProjectManagerListener` with two methods applied to check if the current projects have been opened or closed.
Each method refers to the `ProjectCountingService` [light service][docs:plugin_services:light_services].
It provides methods to increase and decrease the global counter of the currently opened projects in the IDE.
After opening each one, a message dialog is presented to the user with the current number.

### Application Listeners

| Name     | Implementation                                            | Listener Class           |
|----------|-----------------------------------------------------------|--------------------------|
| listener | [ProjectOpenCloseListener][file:ProjectOpenCloseListener] | `ProjectManagerListener` |

*Reference: [Plugin Listeners in IntelliJ SDK Docs][docs:listeners]*

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:plugin_services]: https://plugins.jetbrains.com/docs/intellij/plugin-services.html
[docs:plugin_services:light_services]: https://plugins.jetbrains.com/docs/intellij/plugin-services.html#light-services
[docs:listeners]: https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html

[file:ProjectCountingService]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectCountingService.java
[file:ProjectOpenCloseListener]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectOpenCloseListener.java
