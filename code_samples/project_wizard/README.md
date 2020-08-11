# Project Wizard Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Plugin Services in IntelliJ SDK Docs][docs:project_wizard]*

## Quickstart

This demo project shows how to add an extra step to the Project Wizard to provide additional project configuration
settings. The new step contains a simple `JLabel` element as an example presentation of the new step content.

## Structure

Project Wizard Demo
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name          | Implementation Class                              | Interface                          |
| ------------- | ------------------------------------------------- | ---------------------------------- |
| moduleBuilder | [DemoModuleWizardStep][file:DemoModuleWizardStep] | [ModuleBuilder][sdk:ModuleBuilder] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:project_wizard]: https://jetbrains.org/intellij/sdk/docs/tutorials/project_wizard.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:DemoModuleWizardStep]: ./src/main/java/org/intellij/sdk/project/wizard/DemoModuleWizardStep.java

[sdk:ModuleBuilder]: upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java
