# Kotlin Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Inspections in IntelliJ SDK Docs][docs:code_inspections]*

## Quickstart

Simple Kotlin Demo project is an example of the Kotlin-based plugin providing the most straight forward action
implemented by the [HelloAction.kt][file:HelloAction] Kotlin class.

Action, added to the Main Menu, shows a message dialog when invoked.

## Structure

Kotlin Demo
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Actions

| Name   | Implementation Class            | Interface                |
| ------ | ------------------------------- | ------------------------ |
| action | [HelloAction][file:HelloAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:code_inspections]: https://jetbrains.org/intellij/sdk/docs/tutorials/code_inspections.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:HelloAction]: ./src/main/kotlin/org/intellij/sdk/kotlin/HelloAction.kt

[sdk:AnAction]: https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java
