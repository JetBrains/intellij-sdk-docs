# Theme Basics [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Creating Custom UI Themes in IntelliJ SDK Docs][docs:themes]*

## Quickstart

Custom UI Themes are available beginning in version 2019.1.

Creating a custom UI Theme is a process of choosing a base IDE Theme (Light or Darcula) then changing aspect
of the base Theme definition. Custom UI Themes can:
- substitute icons,
- change the colors of icons and UI controls,
- alter the borders and insets of UI controls,
- provide custom editor schemes,
- add background images.

## Structure

Theme Basics
plugin depends on the [IntelliJ Platform SDK][docs] and [DevKit][docs:devkit] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                         | Implementation                                          | Extension Point Class |
| ---------------------------- | ------------------------------------------------------- | --------------------- |
| `com.intellij.themeProvider` | [theme_basics.theme.json][file:theme_basics.theme.json] |                       |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:themes]: https://jetbrains.org/intellij/sdk/docs/reference_guide/ui_themes/themes.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:devkit]: https://jetbrains.org/intellij/sdk/docs/basics/getting_started/using_dev_kit.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./resources/META-INF/plugin.xml
[file:theme_basics.theme.json]: ./resources/theme_basics.theme.json
