# Inspection Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Inspections in IntelliJ SDK Docs][docs:code_inspections]*

## Quickstart

Inspection Sample Project implements a simple local inspection producing warnings for the regular plain text files.

Inspection, enabled by default, uses a visitor passing all PSI elements with no error reporting.

### Extension Points

| Name                           | Implementation                                | Extension Point Class |
|--------------------------------|-----------------------------------------------|-----------------------|
| `com.intellij.localInspection` | [DemoCodeInspection][file:DemoCodeInspection] | `LocalInspectionTool` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:code_inspections]: https://plugins.jetbrains.com/docs/intellij/code-inspections.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoCodeInspection]: ./src/main/java/org/intellij/sdk/inspection/DemoCodeInspection.java
