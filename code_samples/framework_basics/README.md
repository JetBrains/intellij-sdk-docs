# Framework Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Supporting Frameworks in IntelliJ SDK Docs][docs:supporting_frameworks]*

## Quickstart

Framework Sample Project is providing a [DemoFramework][file:DemoFramework] which allows to embed a framework support
within the Project Wizard. This sample implementation adds a new *SDK Demo Framework* support in the Java type project.

### Extension Points

| Name                          | Implementation                      | Extension Point Class                  |
| ----------------------------- | ----------------------------------- | -------------------------------------- |
| `com.intellij.framework.type` | [DemoFramework][file:DemoFramework] | [FrameworkTypeEx][sdk:FrameworkTypeEx] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:supporting_frameworks]: https://jetbrains.org/intellij/sdk/docs/tutorials/framework.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:DemoFramework]: ./src/main/java/org/intellij/sdk/framework/DemoFramework.java

[sdk:FrameworkTypeEx]: upsource:///java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java
