# Maximum Open Projects Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:plugin_services]*

## Quickstart

Maximum Open Projects Sample implements a `StartupActivity` extension point to run on project open as well as a
`ProjectManagerListener` for tracking projects being closed.
Both use `ProjectCountingService` application-level [light service][docs:plugin_services:light_services].
It provides methods to increase and decrease the counter of currently opened projects in the IDE.
When opening more projects than the maximum allowed (3), a message dialog is shown.

### Extension Points

| Name                               | Implementation                                                | Extension Point Class |
|------------------------------------|---------------------------------------------------------------|-----------------------|
| `com.intellij.postStartupActivity` | [ProjectOpenStartupActivity][file:ProjectOpenStartupActivity] | `StartupActivity`     |

### Application Listeners

| Name     | Implementation                                        | Listener Class           |
|----------|-------------------------------------------------------|--------------------------|
| listener | [ProjectOpenCloseListener][file:ProjectCloseListener] | `ProjectManagerListener` |

*Reference: [Plugin Listeners in IntelliJ SDK Docs][docs:listeners]*

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:plugin_services]: https://plugins.jetbrains.com/docs/intellij/plugin-services.html
[docs:plugin_services:light_services]: https://plugins.jetbrains.com/docs/intellij/plugin-services.html#light-services
[docs:listeners]: https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html

[file:ProjectOpenStartupActivity]: ./src/main/kotlin/org/intellij/sdk/maxOpenProjects/ProjectOpenStartupActivity.kt
[file:ProjectCountingService]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectCountingService.java
[file:ProjectCloseListener]: ./src/main/java/org/intellij/sdk/maxOpenProjects/ProjectCloseListener.java
