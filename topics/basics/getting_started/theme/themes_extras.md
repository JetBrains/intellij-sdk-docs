<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Themes - Editor Schemes and Background Images

<link-summary>Customizing editor schemes and editor or application background images in a theme plugin.</link-summary>

Themes can also provide custom color and font settings, as well as custom images for display in the IDE application window.

<include from="developing_themes.md" element-id="themeSamplePlugin"/>

## Adding a Custom Editor Scheme

Users of IntelliJ Platform-based IDEs, such as IntelliJ IDEA, can set preferences to configure the colors and fonts used in the Editor.
These custom color and font settings are called _Editor Color Schemes_.

### Creating a Custom Editor Scheme Using Settings

Custom editor color schemes can be specified and exported using the IDE <ui-path>Settings</ui-path> dialog.
Note that editor [Colors and Fonts](https://www.jetbrains.com/help/idea/configuring-colors-and-fonts.html), and [Colors for Version Control File Status](https://www.jetbrains.com/help/idea/file-status-highlights.html) are customized in different sections of <ui-path>Settings</ui-path>.

Use the following procedure to customize an editor color scheme for a theme:
* Create the desired custom editor color scheme using the IDE preferences.
* Export the custom editor color scheme to the desired file name.
  In this example, the file is exported to <path>Lightning.icls</path>.
* Once exported, change the file extension from <path>\*.icls</path> to <path>\*.xml</path>.
  In this example, the result is <path>Lightning.xml</path>.
* See [Customizing Editor Scroll Bar Colors](#customizing-editor-scroll-bar-colors) to change the colors of editor scroll bars.

### Incorporating the Editor Color Scheme in the Custom Theme

The next step is to add the color scheme to the theme plugin project:
* Replace the default generated custom editor color scheme XML file (in this example, <path>theme_basics.xml</path>) in the project's <path>resources</path> folder with the exported custom editor color scheme.
  In this case, the action is to _replace_ <path>theme_basics.xml</path> with <path>Lightning.xml</path>.
* In the theme file (in this example <path>theme_basics.theme.json</path>), replace the name of the generated editor scheme file (<path>theme_basics.xml</path>) with the new (<path>Lightning.xml</path>) file name.
  The `key` is always "editorScheme".
  The `value` is the name of the editor color scheme file.

The example below adds an editor scheme named "Lightning" to the _Theme Basics_ custom theme:

```json
{
  "name": "Theme Basics",
  "dark": false,
  "author": "IntelliJ Platform SDK",
  "editorScheme": "/Lightning.xml",
  "ui": {
  }
}
```

### Editor Color Scheme XML Files

When an editor color scheme is exported as a file, the color options appear as `name`-`value` attributes of `option` elements.
The `name` is the aspect of the editor to be changed, and the `value` is the new color in six-digit RGB or eight-digit RGBA hexadecimal notation.
For example, the snippet below sets the color of the line numbers displayed in the editor:

```xml
<colors>
  <option name="LINE_NUMBERS_COLOR" value="999999"/>
</colors>
```

For additional examples of `name` and `value` attributes, review the editor color scheme XML file for the [High Contrast editor scheme](%gh-ic%/platform/platform-resources/src/themes/highContrastScheme.xml).

### Customizing Version Control File Status Colors

As [described above](#creating-a-custom-editor-scheme-using-settings), colors corresponding to the VCS status of files can be customized and exported via the [Settings](https://www.jetbrains.com/help/idea/file-status-highlights.html).
No other procedure is necessary to customize these colors.
In the exported color scheme file, the `name` is the VCS file status, and the `value` is the new color corresponding to that status.
For example, customized VCS colors for a subset of file statuses will appear in the editor scheme file as:

```xml
<colors>
  <option name="FILESTATUS_ADDED" value="62cc47"/>
  <option name="FILESTATUS_COPIED" value="62cc47"/>
  <option name="FILESTATUS_DELETED" value="ed864a"/>
</colors>

```

For additional examples of `FILESTATUS` color `name` attributes, see the editor color scheme XML file for the [High Contrast editor scheme](%gh-ic%/platform/platform-resources/src/themes/highContrastScheme.xml).

### Customizing Editor Scroll Bar Colors

Editor scroll bar colors should be coordinated with and switch together with an editor color scheme.
Please note that custom theme (<path>*.theme.json</path>) files also contain `ScrollBar.*` name attributes, but these are for scroll bars outside the context of the editor.

> The Editor Scroll Bar colors are the only editor scheme settings that cannot be customized and exported through IDE preferences.
>
{style="note"}

Customizing the editor scroll bar colors requires manually changing an editor color scheme XML file.
At this time there isn't code completion functionality for changing custom color editor scheme XML files, so the `name` attributes are described below.

#### Editor Scroll Bar Attribute Name Format

The typical format of a scroll bar `name` attribute is `ScrollBar.usage`, where `usage` describes where the color is to be applied.
In some cases `usage` itself can be compound such as `ScrollBar.Mac.Transparent.thumbColor`.
In these compound cases, the last portion of the compound `usage` still describes where the color is to be applied.

Note that the following example snippet uses an eight-digit hexadecimal color `value` to give `ScrollBar.Mac.thumbColor` transparency:

```xml
<color>
  <option name="ScrollBar.Mac.trackColor" value="000000"/>
  <option name="ScrollBar.Mac.thumbColor" value="FFFFFFBE"/>
</color>
```

#### Editor Scroll Bar Attribute Names

A list of scroll bar `name` attributes is in the [High Contrast editor scheme](%gh-ic%/platform/platform-resources/src/themes/highContrastScheme.xml) file.
These name attributes cannot be accessed from anywhere in the IDE UI at this time, so they must be manually added to an editor color scheme XML file.

The following list explains the `usage` format of the `name` attribute, i.e., where a custom scroll bar color is applied:
* `*.trackColor` - The scroll bar thumb moves across this area.
  At this time the vertical scrollbar track color and transparency cannot be customized.
* `*.thumbColor` - The movable rectangle that corresponds to the visible content's size.
* `*.thumbBorderColor` - The thumb border.
* `*.hoverTrackColor` - Same `usage` as above but for hover.
  At this time the vertical scrollbar hover track color and transparency cannot be customized.
* `*.hoverThumbColor` - Same `usage` as above but for hover.
* `*.hoverThumbBorderColor` - Same `usage` as above but for hover.

The `name` attribute patterns are enumerated below.

**Platform Independent Name Attributes**

The horizontal scroll bar background color is set by `ScrollBar.background`.
This background color is visible only if the horizontal scroll bar's `*.trackColor` has transparency.

At this time, the vertical scrollbar background color cannot be customized.

**Windows/Linux Name Attributes**

The `name` attributes for Windows and Linux scroll bars have the pattern `ScrollBar.Transparent.*`, where the wildcard portion corresponds to the `usage` definitions above.

**macOS Name Attributes**

The `name` attribute pattern for the vertical scroll bar is `ScrollBar.Mac.*`.

The `name` attribute pattern for the horizontal scroll bar depends on the macOS preferences _Show scroll bars_ setting:
* `ScrollBar.Mac.*` when the _Always_ setting is selected.
* `ScrollBar.Mac.Transparent.*` when the _When scrolling_ setting is selected.

The wildcard portion of these patterns corresponds to the `usage` definitions above.

## Adding a Custom Background Image

The IDE supports setting an image as a background in the application window.
Users can do this manually in [Settings](https://www.jetbrains.com/help/idea/setting-background-image.html).

Themes support specifying a background image as a key-value pair in the `"background": {}` (for editor and tools) and `"emptyFrameBackground": {}` (for empty frame) sections of a Theme description file:
* The `image` key uses the file name of the image as the value.
The background image is placed in the theme plugin project's <path>resources</path> folder.
* The `transparency` key uses a `value` of 1–100.
A `value` of 100 is opaque.
* The `fill` key uses a value of `scale`, meaning to expand the image to fill the space as the window gets resized.
* The `anchor` key uses a value of `center`, meaning to locate the center of the image in the center of the window.

The following example adds an image of the Austrian countryside to the _Theme Basics_ Theme description file:

```json
{
  "name": "Theme Basics",
  "dark": false,
  "author": "IntelliJ Platform SDK",
  "ui": {
  },
  "background": {
    "image": "/austria.png",
    "transparency": 10,
    "fill": "scale",
    "anchor": "center"
  },
  "emptyFrameBackground": {
    "image": "/austria.png",
    "transparency": 20,
    "fill": "scale",
    "anchor": "center"
  }
}
```

