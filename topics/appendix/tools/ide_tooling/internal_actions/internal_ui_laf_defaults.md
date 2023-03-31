<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Internal Actions - LaF Defaults

<link-summary>Using LaF Defaults window to view and prototype UI control colors in a running IDE.</link-summary>

The <control>LaF Defaults</control> window provides a key-value pair lookup for UI Controls.
It also allows interactive prototyping of UI Control color changes.

_LaF_ stands for _Look and Feel_, see [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html) for more details.

<include from="internal_actions_intro.md" element-id="enable_internal_mode_tip"></include>

## Opening LaF Defaults

The <control>LaF Defaults</control> window is opened by selecting the menu item <ui-path>Tools | Internal Actions | UI | LaF Defaults</ui-path>.

It has two columns representing key-value pairs for UI Controls:

* The <control>Name</control> column contains the UI Control `key` for each IntelliJ Platform UI element available at runtime.
* The <control>Value</control> column contains the UI Control color `value` for each IntelliJ Platform UI element.

## Using the LaF Panel

### Finding UI Controls

The <control>LaF Defaults</control> window is used interactively by entering a UI element type - e.g. `Panel` - in the <control>Filter</control> text box at the top.
<control>LaF Defaults</control> shows the list of UI Control names matching the filter.

Enable <control>Colors only</control> to show only entries with color value.

Clicking on one of the names narrows the information to show only the key-value pair for that UI element:

![LaF Lookup](internal_lafd_win.png)

### Prototyping the Color of UI Controls

The color of UI Controls can be changed (in real time) by clicking in the <control>Value</control> column next to a <control>Name</control> (`key`) of interest.
The <control>Choose Color</control> window is displayed.
Color changes can be specified as RGB, HSB, hexadecimal, or using the graphical color picker.
Pressing the <control>Choose</control> button changes the UI Control color immediately.

UI Control colors can be reset using the <control>Choose Color</control> window, or by resetting the [theme](https://www.jetbrains.com/help/idea/settings-appearance.html).
