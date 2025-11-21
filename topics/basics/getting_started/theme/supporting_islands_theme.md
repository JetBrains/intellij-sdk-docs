<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Supporting Islands Theme

<primary-label ref="2025.3"/>

<link-summary>Instructions on how to convert a custom theme to an Islands theme.</link-summary>

Follow these guidelines to convert an existing custom theme to an Islands theme.

## Defining the Parent Theme

We recommend creating Islands custom themes based on the default Islands themes.
To do that, define the parent theme at the beginning of the <path>\*.theme.json</path> file.
An example for a **dark** custom theme:

```json
{
  "name": "Islands Custom Dark",
    "dark": true,
    "author": "Author Name",
    "editorScheme": "Islands Custom Dark",
    "parentTheme": "Islands Dark",
    "ui": {
    }
}
```
For a **light** theme, inherit from 'Islands Light'.

Here is the Dark Purple theme without and with inheritance from 'Islands Dark':

![Dark Purple theme without inheritance](islands_dark_purple_01.png)

![Dark Purple theme with inheritance](islands_dark_purple_02.png)


## Hiding Sidebar Borders
In the Islands theme, there are no borders for the sidebars around the main window: the main toolbar, tool window stripes, and the status bar.

To hide the borders, set the following keys to a 100% transparent value:

```json
"StatusBar.borderColor": "#XXXXXX00",
"ToolWindow.Stripe.borderColor": "#XXXXXX00",
"MainToolbar.borderColor": "#XXXXXX00"
```

`00` at the end of a hex value sets a 100% transparent color.

The Dark Purple theme with transparent borders for sidebars:

![Dark Purple theme with transparent borders](islands_dark_purple_03.png)


## Main Background Colors

The main colors to change for the Islands theme are backgrounds of tool windows and the main window.

![A schematic look of the main window with keys for main background colors](islands_main_background_colors.png)

<table>
    <tr>
        <th>Key</th>
        <th>Recommendation</th>
    </tr>
    <tr>
        <td><code>ToolWindow.background</code><br/>
            <code>ToolWindow.Header.background</code><br/>
            <code>ToolWindow.Header.inactiveBackground</code>
        </td>
        <td>Use the same color as in the editor background, or a slightly lighter/darker color.</td>
    </tr>
    <tr>
        <td><code>MainWindow.background</code></td>
        <td><b>Dark theme:</b> use a lighter color than the editor and tool windows background.<br/><br/>
            <b>Light theme:</b> use a darker color than the editor and tool windows background.<br/><br/>
            The recommended minimum contrast ratio of tool windows/editor to main window backgrounds is <b>1.20:1</b>.
        </td>
    </tr>
</table>

The Dark Purple theme with tool window and main window backgrounds set:

![Dark Purple theme with correct main background colors](islands_dark_purple_04.png)

## Islands Border Color

By design, island areas do not have a noticeable border color.
Set `Island.borderColor` to the same color as in `ToolWindow.background`.

The Dark Purple theme without visible borders for island areas:

![Dark Purple theme with correct islands border color](islands_dark_purple_05.png)

## Selected Tab Colors

The Islands theme has a new design for the selected tab:

<img src="islands_tabs.png" alt="Comparison of new and old selected tab UI" width="706"/>

Use the following keys to define selected tab colors.
The keys are applied to all tabs, not only the editor tabs.

<table>
    <tr>
        <th>Property</th>
        <th>State: Key</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>Border</td>
        <td>Selected:<br/>
            <code>EditorTabs.underlinedBorderColor</code><br/><br/>
            Selected inactive:<br/>
            <code>EditorTabs.inactiveUnderlinedTabBorderColor</code>
        </td>
        <td>An existing color key. Previously, it was used for the bright tab underline. Leave the color value as is, or adjust if it appears too bright.</td>
    </tr>
    <tr>
        <td>Background</td>
        <td>Selected:<br/>
            <code>EditorTabs.underlinedTabBackground</code><br/><br/>
            Selected inactive:<br/>
            <code>EditorTabs.inactiveUnderlinedTabBackground</code>
        </td>
        <td>A new color key for the background under the tab label. Provide a color that has enough contrast with the label.</td>
    </tr>
</table>

The background of the editor tab bar is defined with the `EditorTabs.background` key and should have the same color as the editor.

The Dark Purple theme with editor tabs background and tab colors defined:

![Dark Purple theme with correct selected tab color](islands_dark_purple_06.png)

## All Islands Keys

Below are all keys for the island areas.
If `parentTheme` is set to Islands Dark or Light, the default values are inherited.
The only exception is `Island.borderColor` which should be set manually.

<table>
    <tr>
        <th>Key</th>
        <th>Default value</th>
        <th>Description</th>
    </tr>
    <tr>
        <td><code>Islands</code></td>
        <td>1</td>
        <td>Defines the theme as the Islands theme.</td>
    </tr>
    <tr>
        <td><code>Island.arc</code><br/>
            <code>Island.arc.compact</code>
        </td>
        <td>20<br/>
            16
        </td>
        <td>Border radius of the islands. The value is 2x, the actual radius in the UI is 10 px.</td>
    </tr>
    <tr>
        <td><code>Island.borderWidth</code><br/>
            <code>Island.borderWidth.compact</code>
        </td>
        <td>5<br/>
            4
        </td>
        <td>The distance between islands. The actual distance appearing in the UI is 1 px less.</td>
    </tr>
    <tr>
        <td><code>Island.borderColor</code></td>
        <td>Theme color</td>
        <td>Supply the chosen value. It is recommended to use the same color as for <code>ToolWindows.background</code>.</td>
    </tr>
    <tr>
        <td><code>Island.inactiveAlpha</code></td>
        <td>0.44</td>
        <td>When the main IDE window is not in focus, to show the inactive state, all content "beneath" the islands is covered with an overlay with this transparency value.</td>
    </tr>
</table>












