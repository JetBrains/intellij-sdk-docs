# PyCharm Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [PyCharm Plugin Development in IntelliJ SDK Docs][docs:pycharm]*

## Quickstart

PyCharm Sample is a plugin that depends on the PyCharm IDE having the proper dependencies specified in the Gradle configuration file.
The implementation utilizes a simple action added to the *MainMenu* group displaying a message dialog after invoking.

### Actions

| ID                                           | Implementation                              | Extension Point Class    |
| -------------------------------------------- | ------------------------------------------- | ------------------------ |
| `org.intellij.sdk.pycharm.PopupDialogAction` | [PopupDialogAction][file:PopupDialogAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:pycharm]: https://jetbrains.org/intellij/sdk/docs/products/pycharm.html

[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/pycharm/PopupDialogAction.java

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
