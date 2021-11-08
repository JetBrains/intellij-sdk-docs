# Project Wizard Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Project Wizard in IntelliJ SDK Docs][docs:project_wizard]*

## Quickstart

This demo project shows how to add an extra step to the Project Wizard to provide additional project configuration settings.
The new step contains a simple `JLabel` element as an example presentation of the new step content.

### Extension Points

| Name                         | Implementation                                    | Extension Point Class |
|------------------------------|---------------------------------------------------|-----------------------|
| `com.intellij.moduleBuilder` | [DemoModuleWizardStep][file:DemoModuleWizardStep] | `ModuleBuilder`       |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:project_wizard]: https://plugins.jetbrains.com/docs/intellij/intro-project-wizard.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:DemoModuleWizardStep]: ./src/main/java/org/intellij/sdk/project/wizard/DemoModuleWizardStep.java
