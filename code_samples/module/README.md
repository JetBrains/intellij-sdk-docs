# Module Type Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:plugin_services]*

## Quickstart

Sample project that presents an implementation of the `moduleType` extension point, which adds a new module type
to the *New Module* Project Wizard. Module with a custom name, description and icon set provides a `ModuleBuilder`
with extra steps present for additional module configuration.

## Structure

Module Type Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                      | Implementation Class                  | Interface                    |
| ------------------------- | ------------------------------------- | ---------------------------- |
| `com.intellij.moduleType` | [DemoModuleType][file:DemoModuleType] | [ModuleType][sdk:ModuleType] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:plugin_services]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_services.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:DemoModuleType]: ./src/main/java/org/intellij/sdk/module/DemoModuleType.java

[sdk:ModuleType]: upsource:///platform/lang-api/src/com/intellij/openapi/module/ModuleType.java
