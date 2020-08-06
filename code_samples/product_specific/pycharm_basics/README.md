# PyCharm Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [PyCharm Plugin Development in IntelliJ SDK Docs][docs:pycharm]*

## Quickstart

PyCharm Sample is a plugin that depends on the PyCharm IDE having the proper dependencies specified
in the Gradle configuration file. Implementation utilizes a simple action added to the *MainMenu* group displaying
a message dialog after invoking.

## Structure

PyCharm Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Actions

| Name              | Implementation Class                        | Interface                |
| ----------------- | ------------------------------------------- | ------------------------ |
| PopupDialogAction | [PopupDialogAction][file:PopupDialogAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:pycharm]: https://jetbrains.org/intellij/sdk/docs/products/pycharm.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:PopupDialogAction]: ./src/main/java/org/intellij/sdk/pycharm/PopupDialogAction.java

[sdk:AnAction]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
