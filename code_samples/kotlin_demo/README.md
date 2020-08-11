# Kotlin Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Kotlin for Plugin Developers in IntelliJ SDK Docs][docs:kotlin]*

## Quickstart

Simple Kotlin Demo project is an example of the Kotlin-based plugin providing the most straight forward action
implemented by the [HelloAction.kt][file:HelloAction] Kotlin class.

Action, added to the Main Menu, shows a message dialog when invoked.

### Actions

| ID               | Implementation                  | Extension Point Class    |
| ---------------- | ------------------------------- | ------------------------ |
| `MyPlugin.Hello` | [HelloAction][file:HelloAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:kotlin]: https://jetbrains.org/intellij/sdk/docs/tutorials/kotlin.html

[file:HelloAction]: ./src/main/kotlin/org/intellij/sdk/kotlin/HelloAction.kt

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
