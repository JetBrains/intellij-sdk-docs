# Framework Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Supporting Frameworks in IntelliJ SDK Docs][docs:supporting_frameworks]*

## Quickstart

Framework Sample Project is providing a [DemoFramework][file:DemoFramework] which allows to embed a framework support
within the Project Wizard. This sample implementation adds a new *SDK Demo Framework* support in the Java type project.

## Structure

Framework Sample Project
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name           | Implementation Class                | Interface                              |
| -------------- | ----------------------------------- | -------------------------------------- |
| framework.type | [DemoFramework][file:DemoFramework] | [FrameworkTypeEx][sdk:FrameworkTypeEx] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:supporting_frameworks]: https://jetbrains.org/intellij/sdk/docs/tutorials/framework.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:DemoFramework]: ./src/main/java/org/intellij/sdk/framework/DemoFramework.java

[sdk:FrameworkTypeEx]: https://github.com/JetBrains/intellij-community/blob/master/java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java
