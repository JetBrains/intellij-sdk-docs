# Run Configuration Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Run Configurations in IntelliJ SDK Docs][docs:run_configurations]*

## Quickstart

Run Configuration example project provides an implementation of the `configurationType` extension point responsible
for adding new options for the Run/Debug Configurations. In this example, a new *Demo* configuration is added together
with `ConfigurationFactory` instance that collects run/debug properties - `scriptName` in this case.

## Structure

Run Configuration Demo
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name              | Implementation Class                                      | Interface                                  |
| ----------------- | --------------------------------------------------------- | ------------------------------------------ |
| configurationType | [DemoRunConfigurationType][file:DemoRunConfigurationType] | [ConfigurationType][sdk:ConfigurationType] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:run_configurations]: https://jetbrains.org/intellij/sdk/docs/basics/run_configurations.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:DemoRunConfigurationType]: ./src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java

[sdk:ConfigurationType]: upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java
