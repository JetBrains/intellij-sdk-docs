# Module Type Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:plugin_services]*

## Quickstart

The sample project that presents an implementation of the `moduleType` extension point, which adds a new module type to the *New Module* Project Wizard.
Module with a custom name, description, and icon set provides a `ModuleBuilder` with extra steps present for additional module configuration.

### Extension Points

| Name                      | Implementation                        | Extension Point Class |
| ------------------------- | ------------------------------------- | --------------------- |
| `com.intellij.moduleType` | [DemoModuleType][file:DemoModuleType] | `ModuleType`          |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:plugin_services]: https://plugins.jetbrains.com/docs/intellij/plugin-services.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoModuleType]: ./src/main/java/org/intellij/sdk/module/DemoModuleType.java
