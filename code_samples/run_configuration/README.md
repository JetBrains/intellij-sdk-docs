# Run Configuration Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Run Configurations in IntelliJ SDK Docs][docs:run_configurations]*

## Quickstart

Run Configuration example project provides an implementation of the `configurationType` extension point responsible for adding new options for the Run/Debug Configurations.
In this example, a new *Demo* configuration is added together with `ConfigurationFactory` instance that collects run/debug properties - `scriptName` in this case.

### Extension Points

| Name                             | Implementation                                            | Extension Point Class                      |
| -------------------------------- | --------------------------------------------------------- | ------------------------------------------ |
| `com.intellij.configurationType` | [DemoRunConfigurationType][file:DemoRunConfigurationType] | [ConfigurationType][sdk:ConfigurationType] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:run_configurations]: https://jetbrains.org/intellij/sdk/docs/basics/run_configurations.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:DemoRunConfigurationType]: ./src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java

[sdk:ConfigurationType]: upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java
