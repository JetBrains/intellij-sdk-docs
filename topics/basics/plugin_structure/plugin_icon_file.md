<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Logo

<link-summary>Plugin logo requirements and best practices.</link-summary>

The IntelliJ Platform supports representing a plugin with a logo.
A _Plugin Logo_ is intended to be a unique representation of a plugin's functionality, technology, or company.

When opening <path>plugin.xml</path> in editor, inspection
<control>Plugin DevKit | Plugin descriptor | Plugin Logo check</control> (2024.3+,
for earlier versions <control>Plugin DevKit | Plugin descriptor | Plugin.xml validity</control>)
will highlight a missing plugin icon.

**Note:** icons and images used within a plugin have different requirements.
See [](icons.md) for more information.

## Plugin Logo Usages
Plugin Logos are shown in the [JetBrains Marketplace](https://plugins.jetbrains.com).
They also appear in the Settings [Plugin Manager](https://www.jetbrains.com/help/idea/managing-plugins.html) UI in IntelliJ Platform-based IDEs.
Whether online or in the product UI, a Plugin Logo helps users to identify a plugin more quickly in a list, as shown below:

![Example Product Plugin Settings Dialog](plugin_prefs.png){width="800"}

> When browsing [custom plugin repositories](custom_plugin_repository.md), there is no support for showing logos for plugins hosted there but not yet installed.
>
{style="note"}

## Plugin Logo Requirements

> Please see also these [important requirements](https://plugins.jetbrains.com/docs/marketplace/best-practices-for-listing.html#plugin-logo) for JetBrains Marketplace.
>
{style="note"}

For a Plugin Logo to be displayed correctly within an IntelliJ Platform-based IDE, it must:
* Follow the best practices design guidelines,
* Be in the correct file format,
* Conform to file name conventions,
* Have the correct size,
* Be in the <path>META-INF</path> folder of the plugin distribution file.

### Plugin Logo Size
The Plugin Logo should be provided in one size: 40px by 40px.

A Plugin Logo is displayed in two sizes, and scales automatically in each context:
* 40px by 40px in the plugins list in the Plugin Manager UI.
* 80px by 80px in the plugin details screen in the Plugin Manager UI and on the plugin's page in JetBrains Marketplace.

Verify that Plugin Logo designs are effective in both sizes and all display contexts.

### Plugin Logo Shape
Plugin Logo designs should leave at least 2px transparent padding around the perimeter, as shown below:

![36px by 36px is the area where the visible part of the Logo should fit](icon_size.png){width="225"}

Make sure Plugin Logos have the same visual weight as the logos in the examples below.
The more filled a Plugin Logo design is, the less actual space it needs.
See more examples of [visual weight compensation](icons_style.md#basic-shapes) in the UI Guidelines for Icons.

For basic shapes, use the following sizes.
Note the different areas of transparent padding used for each shape:

|            ![Square 32px by 32px](square_logo.png){width="225"}             |        ![Circle 36px in diameter](circle_logo.png){width="225"}         |
|:---------------------------------------------------------------------------:|:-----------------------------------------------------------------------:|
|                         _Square logo 32px by 32px_                          |                    _Circular logo 36px in diameter_                     |
| ![Horizontal rectangle 36px by 26px](rectangle_horizontal.png){width="225"} | ![Vertical rectangle 26px by 36px](rectangle_vertical.png){width="225"} |
|                 _Horizontal rectangular logo 36px by 26px_                  |                _Vertical rectangular logo 26px by 36px_                 |
{style="none"}

### Plugin Logo Colors
If the plugin's technology already has a logo, use its colors.
Check the license terms before using the logo.
If there is no existing logo, or its use is prohibited, create a custom logo based on the [Action Colors Palette](icons_style.md#action-icons) in the UI Guidelines for Icons.

| ![The YouTrack Plugin Logo uses the YouTrack product logo ](yt_logo.png){height="200" width="200"} | ![The Keymap Plugin Logo uses a color from the Action Colors Palette](keymap_logo.png){:height="200" width="200"} |
|:--------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------:|
|                   _The YouTrack Plugin Logo uses<br/>the YouTrack product logo_                    |                     _The Keymap Plugin Logo uses a color<br/>from the Action Colors Palette_                      |
{style="none"}

Ensure a Plugin Logo is visible on both light and dark backgrounds.
If one Plugin Logo design does not work on both light and dark backgrounds, create separate light and dark versions of the Plugin Logo.
The examples below illustrate how a Plugin Logo design may work well for a light background but not for a dark background.
Consequently, a separate Plugin Logo for dark backgrounds is needed.

| ![Plugin Logo on Light Theme](light_version.png){width="225"} |     ![Light Plugin Logo on Dark Theme](dark_bad.png){width="225"}     |     ![Plugin Logo for Dark Theme](dark_good.png){width="225"}      |
|:-------------------------------------------------------------:|:---------------------------------------------------------------------:|:------------------------------------------------------------------:|
| _The light Plugin Logo design<br/>works well on light theme_  | _The light Plugin Logo design does<br/>not work well on a dark theme_ | _A separate, dark Plugin Logo design<br/>works well on dark theme_ |
{style="none"}

### Plugin Logo File Format
All Plugin Logo images must be in SVG format.
This vector image format is required because the Plugin Logo file must be small (ideally less than 2-3kB), and the image must scale without any loss of quality.

> Using automatic conversion of bitmap graphics to SVG is highly discouraged, as the resulting files have excessive size (100kB or more).
>
{style="warning"}

### Plugin Logo File Naming Convention
Name the Plugin Logo files according to the following conventions:
* <path>pluginIcon.svg</path> is the default Plugin Logo.
  If a separate Logo file for dark themes exists in the plugin, then this file is used solely for light themes,
* <path>pluginIcon_dark.svg</path> is an optional, alternative Plugin Logo for use solely with dark IDE themes.

## Adding Plugin Logo Files to a Plugin Project
The Plugin Logo files must be packaged in the <path>META-INF</path> folder of the [plugin main JAR file](plugin_content.md).

To include Plugin Logo files, place them into the plugin project's <path>resources/META-INF</path> folder.
For example:

![Plugin Logo Files in META-INF folder](resource_directory_structure.png){width="450"}
