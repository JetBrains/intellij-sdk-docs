# PyCharm Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [PyCharm Plugin Development in IntelliJ SDK Docs][docs:pycharm]*

## Quickstart

PyCharm Sample is a plugin that depends on the PyCharm IDE having the proper dependencies specified in the Gradle configuration file.
The implementation utilizes a simple action added to the *MainMenu* group displaying a message dialog after invoking.

### Actions

| ID                                           | Implementation                              | Base Action Class |
|----------------------------------------------|---------------------------------------------|-------------------|
| `org.intellij.sdk.pycharm.PopupDialogAction` | [PopupDialogAction][file:PopupDialogAction] | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/basic-action-system.html
[docs:pycharm]: https://plugins.jetbrains.com/docs/intellij/pycharm.html

[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/pycharm/PopupDialogAction.java
