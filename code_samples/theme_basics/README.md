# Theme Basics [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Creating Custom Themes in IntelliJ SDK Docs][docs:themes]*

## Quickstart

Creating a custom theme is a process of choosing a base IDE Theme (Light or Darcula) then changing aspects of the base Theme definition.
Custom themes can:
- substitute icons,
- change the colors of icons and UI controls,
- alter the borders and insets of UI controls,
- provide custom editor schemes,
- add background images.

## Structure

Theme Basics plugin depends on the [IntelliJ Platform SDK][docs] and [DevKit][docs:devkit] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created according to the [Plugin Configuration File documentation][docs:plugin.xml].
It describes definitions of the actions, extensions, or listeners provided by the plugin.

### Extension Points

| Name                         | Implementation                                          | Extension Point Class |
|------------------------------|---------------------------------------------------------|-----------------------|
| `com.intellij.themeProvider` | [theme_basics.theme.json][file:theme_basics.theme.json] |                       |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:themes]: https://plugins.jetbrains.com/docs/intellij/themes-getting-started.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html
[docs:devkit]: https://plugins.jetbrains.com/docs/intellij/developing-themes.html
[docs:plugin.xml]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html

[file:plugin.xml]: ./resources/META-INF/plugin.xml
[file:theme_basics.theme.json]: ./resources/theme_basics.theme.json

## Troubleshooting

To run the `theme_basics` plugin make sure that module SDK is correctly set up and points to IntelliJ Platform Plugin SDK, e.g. `IntelliJ IDEA IC-<version>`. To check this, go to **File | Project Structure | Project Settings | Modules | theme_basics** and select **Dependencies** tab.

If the required SDK doesn't exist, it can be added in **File | Project Structure | Platform Settings | SDKs** by clicking the plus button and selecting **Add IntelliJ Platform Plugin SDK...** item.
