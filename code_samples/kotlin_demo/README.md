# Kotlin Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Kotlin for Plugin Developers in IntelliJ SDK Docs][docs:kotlin]*

## Quickstart

Simple Kotlin Demo project is an example of a minimal Kotlin-based plugin that provides the most straightforward action implemented by the [HelloAction.kt][file:HelloAction] Kotlin class.

Action, added to the main menu, shows a message dialog when invoked.

### Actions

| ID               | Implementation                  | Base Action Class |
|------------------|---------------------------------|-------------------|
| `MyPlugin.Hello` | [HelloAction][file:HelloAction] | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/basic-action-system.html
[docs:kotlin]: https://plugins.jetbrains.com/docs/intellij/kotlin.html

[file:HelloAction]: ./src/main/kotlin/org/intellij/sdk/kotlin/HelloAction.kt
