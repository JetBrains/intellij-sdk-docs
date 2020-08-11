# Project Wizard Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:project_wizard]*

## Quickstart

This demo project shows how to add an extra step to the Project Wizard to provide additional project configuration
settings. The new step contains a simple `JLabel` element as an example presentation of the new step content.

### Extension Points

| Name                         | Implementation                                    | Extension Point Class              |
| ---------------------------- | ------------------------------------------------- | ---------------------------------- |
| `com.intellij.moduleBuilder` | [DemoModuleWizardStep][file:DemoModuleWizardStep] | [ModuleBuilder][sdk:ModuleBuilder] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:project_wizard]: https://jetbrains.org/intellij/sdk/docs/tutorials/project_wizard.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:DemoModuleWizardStep]: ./src/main/java/org/intellij/sdk/project/wizard/DemoModuleWizardStep.java

[sdk:ModuleBuilder]: upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java
