# Run Configuration Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Run Configurations in IntelliJ SDK Docs][docs:run_configurations]*

## Quickstart

Run Configuration example project provides an implementation of the `com.intellij.configurationType` extension point responsible for adding new options for the Run/Debug Configurations.
In this example, a new *Demo* configuration is added together with `ConfigurationFactory` instance that collects run/debug properties - `scriptName` in this case.

### Extension Points

| Name                             | Implementation                                            | Extension Point Class |
|----------------------------------|-----------------------------------------------------------|-----------------------|
| `com.intellij.configurationType` | [DemoRunConfigurationType][file:DemoRunConfigurationType] | `ConfigurationType`   |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:run_configurations]: https://plugins.jetbrains.com/docs/intellij/run-configurations.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoRunConfigurationType]: ./src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java
