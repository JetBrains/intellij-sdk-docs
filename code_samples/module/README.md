# Module Type Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Project Wizard Tutorial in IntelliJ SDK Docs][docs:wizard]*

## Quickstart

The sample project that presents an implementation of the `com.intellij.moduleType` extension point, which adds a new module type to the *New Module* Project Wizard.
Module with a custom name, description, and icon set provides a `ModuleBuilder` with extra steps present for additional module configuration.

### Extension Points

| Name                      | Implementation                        | Extension Point Class |
|---------------------------|---------------------------------------|-----------------------|
| `com.intellij.moduleType` | [DemoModuleType][file:DemoModuleType] | `ModuleType`          |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:wizard]: https://plugins.jetbrains.com/docs/intellij/intro-project-wizard.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoModuleType]: ./src/main/java/org/intellij/sdk/module/DemoModuleType.java
