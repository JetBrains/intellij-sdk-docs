# Settings Example [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Settings Tutorial in IntelliJ SDK Docs][docs:settings_tutorial]*

## Quickstart

This project illustrates a custom Application-level Settings through the implementation of:
- `AppSettingsConfigurable` is analogous to a Controller in the MVC model - it interacts with the other two Settings classes and the IntelliJ Platform,
- `AppSettingsState` is like a Model because it stores the Settings persistently,
- `AppSettingsComponent` is similar to a View because it displays and captures edits to the values of the Settings.

### Extension Points

| Name                                   | Implementation                                          | Extension Point Class      |
| -------------------------------------- | ------------------------------------------------------- | -------------------------- |
| `com.intellij.applicationConfigurable` | [AppSettingsConfigurable][file:AppSettingsConfigurable] | `Configurable`             |
| `com.intellij.applicationService`      | [AppSettingsState][file:AppSettingsState]               | `PersistentStateComponent` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:settings_tutorial]: https://plugins.jetbrains.com/docs/intellij/settings-tutorial.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:AppSettingsConfigurable]: ./src/main/java/org/intellij/sdk/settings/AppSettingsConfigurable.java
[file:AppSettingsState]: ./src/main/java/org/intellij/sdk/settings/AppSettingsState.java

