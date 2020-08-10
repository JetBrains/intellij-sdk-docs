# Settings Example [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Settings Tutorial in IntelliJ SDK Docs][docs:settings_tutorial]*

## Quickstart

This project illustrates a custom Application-level Settings through the implementation of:
- `AppSettingsConfigurable` is analogous to a Controller in the MVC model - it interacts with the other two Settings classes and the IntelliJ Platform,
- `AppSettingsState` is like a Model because it stores the Settings persistently,
- `AppSettingsComponent` is similar to a View because it displays and captures edits to the values of the Settings.

## Structure

Settings Example
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                    | Implementation Class                                    | Interface                                                |
| ----------------------- | ------------------------------------------------------- | -------------------------------------------------------- |
| applicationConfigurable | [AppSettingsConfigurable][file:AppSettingsConfigurable] | [Configurable][sdk:Configurable]                         |
| applicationService      | [AppSettingsState][file:AppSettingsState]               | [PersistentStateComponent][sdk:PersistentStateComponent] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:settings_tutorial]: https://jetbrains.org/intellij/sdk/docs/tutorials/settings_tutorial.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:AppSettingsConfigurable]: ./src/main/java/org/intellij/sdk/settings/AppSettingsConfigurable.java
[file:AppSettingsState]: ./src/main/java/org/intellij/sdk/settings/AppSettingsState.java

[sdk:Configurable]: upsource:///platform/platform-api/src/com/intellij/openapi/options/Configurable.java
[sdk:PersistentStateComponent]: upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java
