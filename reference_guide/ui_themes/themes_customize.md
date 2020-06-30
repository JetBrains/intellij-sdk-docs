---
title: Customizing UI Themes - Icons and UI Controls
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<style>
  table {
    width:100%;
  }
  th:first-child, td:first-child {
    width: 20%;
  }
  th:last-child, td:last-child {
    width: 60%;
  }
</style>

A UI Theme is customized by adding information to the UI Theme description file that overrides the base (_Light_ or _Darcula_) UI Theme. 

## Introduction to UI Theme Description File Syntax
The syntax of a Theme description file follows the JSON open-standard file format of key-value pairs. 
The minimum valid default file is the output of the [DevKit Theme Wizard](themes.md#creating-a-ui-theme-with-the-devkit-theme-wizard). 
Adding key-value pairs customizes a Theme.

UI Theme key-value pairs often use a color as the `value`.
Colors are defined by six-digit RGB or eight-digit RGBA hexadecimal notation.

### UI Theme Reference Implementations
When learning new syntax, it is often useful to have some existing implementations for reference.
For example, refer to the [Theme description file](upsource:///platform/platform-resources/src/themes/HighContrast.theme.json) for the IntelliJ IDEA _High Contrast_ Theme.
It may also be helpful to review some of the [UI Themes available](https://plugins.jetbrains.com/search?headline=164-theme&tags=Theme) at the JetBrains Plugins Repository.

## Defining Named Colors
Colors can always be defined individually as six-digit RGB or eight-digit RGBA hexadecimal notation.
However, Theme definitions often use the same color in multiple places.
Maintaining a Theme is more manageable if _Named Colors_ are globally defined in a `colors {}` block as part of the `*.theme.json` file.
After that, the Named Color can be used instead of a hexadecimal description of the color.
For example, defining the Named Color `basicBackground` and then using it to set the background color for panels.
(Don't be concerned with the `"ui"` syntax in the example below, it will be discussed in [Custom UI Control Colors](#custom-ui-control-colors).)

```json
{
  "name": "theme_basics",
  "dark": false,
  "author": "IntelliJ Platform SDK",
  "editorScheme": "/Lightning.xml",

  "colors": {
    "basicBackground": "#E1E1E1"
  },
  "ui": {
    "Panel.background": "basicBackground"
  }
}
```

## Customizing Icons
UI themes can customize the color of default IntelliJ Platform UI icons, or substitute custom icons for the default ones. 
Customization is done by adding an `"icons": {}` section to the Theme description file.

### Overriding the Global Color of Icons
Default global icon colors are customized by adding key-value pairs to a `"ColorPalette": {}` section. 
The `ColorPalette` must be inserted in the `icons` section.

In the following example the `key` - the default red color (#DB5860) used for `Action` icons in the _Light_ Theme - is overridden to the `value` of a different color (#D61A26):

```json
{
  "icons": {
    "ColorPalette": {
      "#DB5860": "#D61A26"
    }
  }
}
```  

This color substitution is applied throughout the IDE UI.

### Custom Icon Palette Colors
Icon Palettes are predefined UI Theme color keys, each describing a single color in an `Actions` or `Objects` context. 

#### Icon Colors in "Actions" and "Objects" Contexts
IntelliJ Platform has default icon colors defined for `Actions` and `Objects` contexts. 
* `Actions` are Theme keys for icons that appear in the context of toolbars, and represent actions such as _Compile_, _Run_, or _Debug_. 
* `Objects` are Theme keys for icons that appear in the contexts of lists and trees, and represent entities like files, symbols, or run and debug configurations.

The [JetBrains Platform UI Guideline for Icons](https://jetbrains.design/intellij/principles/icons/)
defines the default hexadecimal RGB values of colors for `Actions` and `Objects` keys. 
Note that this document refers to `Objects` keys as "Noun icons."

#### Customizing "Actions" and "Objects" Icon Colors
An icon Palette color is customized by adding an `Actions` or `Objects` `key` and custom color `value` to the `"ColorPalette": {}` section in a Theme description file.
The list of available icon `Actions` and `Objects` keys are provided by the completion popup in the editor:

![Color Palette Popup](img/theme_colorpalette_popup.png){:width="600px"}

For example, the following key-value pair changes the color for  all blue-colored icons on toolbars to the color #5BC0DE:

```json
{
  "icons": {
    "ColorPalette": {
      "Actions.Blue": "#5BC0DE"
    }
  }
}
```

This more specific change to the `Actions.Blue` color overrides the default definition. 
It will also, in the narrower context of blue `Actions` icons, supersede any global color overrides of the default blue icon color.

### Custom Icons
The default IntelliJ Platform UI icons can be replaced by custom icons. 
The file format for icons is SVG. 
The [JetBrains Platform UI Guideline for Icons](https://jetbrains.design/intellij/principles/icons/) has detailed specifications for icons. 

An icon replacement is described within the `icon {}` section of a Theme description file. 
Note that icon replacement key-value pairs appear outside of the `ColorPalette` section. 

For icon substitutions, the `key` is the path to the default icon image.
This path is derived from the `AllIcons.[Group].[IconName]` path in icon section reported by the [UI Inspector](/reference_guide/internal_actions/internal_ui_inspector.md).  

For example, the _Build_ (hammer) icon in the toolbar has the path `Allcons.Actions.Compile` as reported by the UI Inspector. 
Therefore the `key` for the _Build_ icon is `/actions/compile.svg`. 
The `value` is the file name of the replacement icon, located in the `resources` folder of the UI Theme plugin project:

```json
{
  "icons": {
    "/actions/compile.svg": "/factory.svg"
  }
}
```

The color of a replaced icon takes precedence over any `ColorPalette` overrides.

## Customizing UI Controls
UI Themes can change the appearance of more general controls in the IntelliJ Platform UI. 
Examples of these controls are labels, buttons, checkboxes, trees, lists, and menus.

### Custom UI Control Colors
The custom color of a UI control is specified by adding a key-value pair to the `"ui": {}` section of a Theme description file.  

A UI control `key` has the compound format `element.property`, where:
* `element` is the type (label, checkbox, etc.,) of the UI control.
* `property` is how the color is applied. Examples include `foreground`, `background`, and `errorForeground `.

Note that some UI control keys have more than two parts, for example `Popup.Advertiser.foreground` or `ScrollBar.Mac.Transparent.thumbColor`.
The full key must be used to customize that specific button control.
However, for other purposes the first section can be considered the `element`, and the last section considered the `property`.

Methods for finding UI control keys are in the [Finding Attribute Keys for UI Controls](#finding-attribute-keys-for-ui-controls) section. 

#### Customizing All UI Control Colors with the Same Property
All UI Controls that have the same `property` portion of their key can be set to the same color. 
This customization is done using the wildcard `"*": {}` section in the Theme description file. 
A key-value pair is inserted in this section, but only the `property` portion of the key is specified. 
The `value` is the custom color. 

The following example would change the default background color to #AED7E3 for all UI controls:

```json
{
  "ui": {
    "*": {
      "background": "#AED7E3"
     }
  }
}
```

Note that the wildcard `"*": {}` section must be within the `"ui": {}` section.

#### Customizing the Color of Specific UI Control Types
The color of a specific UI control types are changed by adding a key-value pair to the `"ui": {}` section of a Theme description file. 
The `key` is the full `element.property` format and the `value` is the custom color.
  
The following example sets the background color for all labels to the color #F6E9C9

```json
{
  "ui": {
    "*": {
      "background": "#AED7E3"
     },
    "Label.background": "#F6E9C9"
  }
}
```

The `Label.background` entry supersedes, in the narrower context of label backgrounds, any default color as well as any wildcard color assigned to backgrounds.

#### Customizing the Color of UI Tabs
UI Tab colors are changed by [key-value pairs](#custom-ui-control-colors) in a Theme description file.

There are two implementations of tabs in the IntelliJ Platform:
* Editor Tabs, which e.g., represent open files in the [Editor window](https://www.jetbrains.com/help/idea/using-code-editor.html), and in [Tool Window bars](https://www.jetbrains.com/help/idea/tool-windows.html#bars_and_buttons).
* Tabbed Panes, which e.g., are used for the [Run/Debug Configurations dialog](https://www.jetbrains.com/help/idea/run-debug-configurations-dialog.html).

The control keys for UI Tabs were expanded from release 2019.1 to 2019.2 of the IntelliJ Platform.
The 2019.1 release control keys are compatible with release 2019.2 and later versions of the IntelliJ Platform. 

| Release 2019.1 Element | Release 2019.2 Element | Description of Release 2019.2 Element |
|------|---------|---------|
| N/A  | **`DefaultTabs`** | Applied to all tabs except `TabbedPane`, _unless_ overridden by a more specific Tab control element. |
| **`EditorTabs`**  | **`EditorTabs`** | Applied only to Editor tabs. Overrides any `DefaultTab` settings. 192 has many more `property` settings than 191. |
| **`ToolWindow.HeaderTab`** | **`ToolWindow.HeaderTab`** | Applied only to Tool Window tabs. Overrides any `DefaultTab` settings. 192 has many more `property` settings than 191. |
| **`DebuggerTabs`** | **`DefaultTabs`** | `DefaultTab` settings are used instead of `DebuggerTabs`, except for key `DebuggerTabs.underlineHeight`. |
| **`TabbedPane`** | **`TabbedPane`** | Applied only to Tabbed Panes. |
| **`Plugins.Tab`** | **`TabbedPane`** | Use `TabbedPane` instead. |
| **`SearchEverywhere.Tab`** | **`SearchEverywhere.Tab`** | No change. |

Methods for identifying UI control keys are in the [Finding Attribute Keys for UI Controls](#finding-attribute-keys-for-ui-controls) section. 

For example, here is an excerpt from the IntelliJ Platform [High Contrast Theme](upsource:///platform/platform-resources/src/themes/HighContrast.theme.json):
Note that a Theme file can mix versions of `property` identifiers:
* The first three `property` entries are recognized by release 2019.1, and ignored by subsequent releases because they are defined by new `property` identifiers.
* The `underlineColor` `property` is recognized by release 2019.1 and subsequent releases.
* The `underlineHeight` `property` was introduced in release 2019.2, and is ignored by previous releases.
* The `underlinedTabBackground` `property` was introduced in release 2019.2, replaces the 2019.1 `selectedBackground`, and is ignored by previous releases.
* The `inactiveColoredFileBackground` `property` was introduced in release 2019.2, replaces the 2019.1 `inactiveMaskColor`, and is ignored by previous releases.

```json
{
  "ui": {
    "EditorTabs": {
      "selectedForeground": "#FFFFFF", 
      "selectedBackground": "#0e5d73",
      "inactiveMaskColor": "#000000FF",

      "underlineColor": "#1AEBFF",
      "underlineHeight": 4,

      "underlinedTabBackground": "#000000",
      "inactiveColoredFileBackground": "#00000000"
    }
  }
}
```

### Customizing the Borders of UI Controls
The color and geometry of borders used in UI controls can be customized by key-value pairs in a Theme description file. 
The format of keys for borders is `element.property`, where:
* `element` is the type of UI control containing a border, such as a window or a popup menu. 
* `property` is the desired border appearance, for example:
  * `border` is the border width (in pixels) specified as top, left, bottom, and right widths.
  Border color is also (optionally) specified as hexadecimal RGB, e.g. "E6E6E6" with no "#" character.
  * `borderInsets` is the inset distance (in pixels) from the edge of the `element` to the border. 
  It is specified as top, left, bottom, and right insets.  

Methods for identifying UI control keys are in the [Finding Attribute Keys for UI Controls](#finding-attribute-keys-for-ui-controls) section. 

#### Customizing the Border Appearance of Specific UI Controls
The appearance of borders for specific UI control types are customized by adding a key-value pair to the `"ui": {}` section of a Theme description file. 

The following example sets a new border width and color for all windows:

```json
{
  "ui": {
    "Window.border" : "4, 4, 4, 4, E6E6E6"
  }
}
```

In this example the customized border supersedes the default definition and 
any global color override.

## Finding Attribute Keys for UI Controls
There are hundreds of UI control `element.property` keys defined in the IntelliJ Platform UI. 
Some keys and strategies for applying them can be gleaned from the [UI Theme reference implementations](#UI-Theme-Reference-Implementations). 
For a general search, here some suggested methods for locating UI control keys.

### Finding a UI Control Key Using Code Completion in the Editor
The preferred method of finding UI control keys is to use the [Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#Auto-Completing_Code.xml) feature in the editor. 
Note that some keys presented by the code completion feature may be deprecated. 
New entries in the `"ui": {}` section will invoke the code completion popup, as shown below:

![UI Control Key Code Completion](img/uit_control_complete.png)

Beginning with version 2019.2 of the IntelliJ Platform, the editor has added features for Code Completion and [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation) to show the release in which a UI control key began to be supported.
It appears as the _Since_ attribute in editor popups.
In the Quick Documentation popup the format is e.g. _Since: 2019.2_.
The Code Completion popup is similar, but it the format is e.g. _[Since 2019.2]_.

### Finding a UI Control Key Using Laf Defaults UI
Using the [Laf Defaults](/reference_guide/internal_actions/internal_ui_laf_defaults.md) inspector, enter the `element` portion of the key. 
The Laf Defaults inspector will prompt with a list of UI Control keys and their default color.
