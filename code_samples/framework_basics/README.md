# Framework Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Supporting Frameworks in IntelliJ SDK Docs][docs:supporting_frameworks]*

## Quickstart

Framework Sample Project provides a [DemoFramework][file:DemoFramework], which allows embedding framework support within the Project Wizard.
This sample implementation adds a new *SDK Demo Framework* support in the Java type project.

### Extension Points

| Name                          | Implementation                      | Extension Point Class |
|-------------------------------|-------------------------------------|-----------------------|
| `com.intellij.framework.type` | [DemoFramework][file:DemoFramework] | `FrameworkTypeEx`     |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:supporting_frameworks]: https://plugins.jetbrains.com/docs/intellij/framework.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoFramework]: ./src/main/java/org/intellij/sdk/framework/DemoFramework.java
