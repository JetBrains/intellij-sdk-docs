# Inspection Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Inspections in IntelliJ SDK Docs][docs:code_inspections]*

## Quickstart

Inspection Sample Project implements a simple local inspection producing warnings for the regular plain text files.

Inspection, enabled by default, uses a visitor passing all PSI elements with no error reporting.

## Structure

Inspection Sample Project
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name            | Implementation Class                          | Interface                                      |
| --------------- | --------------------------------------------- | ---------------------------------------------- |
| localInspection | [DemoCodeInspection][file:DemoCodeInspection] | [LocalInspectionTool][sdk:LocalInspectionTool] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:code_inspections]: https://jetbrains.org/intellij/sdk/docs/tutorials/code_inspections.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:DemoCodeInspection]: ./src/main/java/org/intellij/sdk/inspection/DemoCodeInspection.java

[sdk:LocalInspectionTool]: upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java
