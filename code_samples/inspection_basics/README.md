# Inspection Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Inspections in IntelliJ SDK Docs][docs:code_inspections]*

## Quickstart

Inspection Sample Project implements a simple local inspection producing warnings for the regular plain text files.

Inspection, enabled by default, uses a visitor passing all PSI elements with no error reporting.

### Extension Points

| Name                           | Implementation Class                          | Interface                                      |
| ------------------------------ | --------------------------------------------- | ---------------------------------------------- |
| `com.intellij.localInspection` | [DemoCodeInspection][file:DemoCodeInspection] | [LocalInspectionTool][sdk:LocalInspectionTool] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:code_inspections]: https://jetbrains.org/intellij/sdk/docs/tutorials/code_inspections.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:DemoCodeInspection]: ./src/main/java/org/intellij/sdk/inspection/DemoCodeInspection.java

[sdk:LocalInspectionTool]: upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java
