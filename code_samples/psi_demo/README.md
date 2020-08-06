# PSI Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Navigating the PSI in IntelliJ SDK Docs][docs:navigating_psi]*

## Quickstart

PSI Demo project demonstrates working with the PSI Navigation by implementing `AnAction` that through the message
dialog, informs about:
- an element at the caret,
- containing method,
- containing class,
- local variables.

## Structure

PSI Demo
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Actions

| Name          | Implementation Class                              | Interface                          |
| ------------- | ------------------------------------------------- | ---------------------------------- |
| action | [TextOnlyTreeStructureProvider][file:PsiNavigationDemoAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:navigating_psi]: https://jetbrains.org/intellij/sdk/docs/basics/architectural_overview/navigating_psi.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:PsiNavigationDemoAction]: ./src/main/java/org/intellij/sdk/psi/PsiNavigationDemoAction.java

[sdk:AnAction]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
