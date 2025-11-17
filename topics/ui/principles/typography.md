<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Typography

<link-summary>UI guidelines on using proper fonts in different contexts.</link-summary>

<tldr>

**Implementation:** [`JBFont`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/JBFont.java)

</tldr>

## UI font

[Inter](https://github.com/rsms/inter) is used for the IDE user interface by default. The default font size is the same for all supported OS: 13.

<!--<table style="header-column">
  <tr>
    <td>  macOS  </td>
    <td> SF Pro Text  </td>
    <td> 13 </td>
  </tr>
  <tr>
    <td> Window </td>
    <td> Segoe UI </td>
    <td> 12 </td>
  </tr>
  <tr>
    <td> Linux </td>
    <td> Ubuntu </td>
    <td> 15 </td>
  </tr>
</table>-->

Users can change the default font in <control>Settings | Appearance</control>. If the default font size is changed, other font sizes used in the UI are scaled respectively.

Use the built-in text styles from the table below whenever possible.

> Implementation example for font styles: [`LabelSizeDemoAction`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/LabelSizeDemoAction.kt)
>
{style="note"}

<table>

  <tr>
  <td width="20%"> Font </td>
  <td width="27%"> Font size </td>
  <td width="53%"> Usage Examples </td></tr>

  <tr>
    <td> H1 <p><code>JBFont.h1().asBold()</code></p></td>
    <td> Default +5 </td>
    <td>
        Main page header. Example: Plugin name
    </td>
  </tr>

  <tr>
    <td> H2 <p><code>JBFont.h2().asBold()</code></p></td>
    <td> Default +3 </td>
    <td>
        Small page header. Example: Headers in a plugin description
    </td>
  </tr>

  <tr>
    <td> Default <p><code>JBFont.regular()</code></p></td>
    <td> Default </td>
    <td>
        Main UI font. Examples: Labels, inputs, links, trees, tables, and other controls
    </td>
  </tr>

  <tr>
    <td> Default semibold <p> <code>JBFont.regular().asBold()</code></p> </td>
    <td> Default </td>
    <td>
        <p>Header in dialogs, popups, notifications, tool windows, Got It tooltips</p>
    </td>
  </tr>

  <tr>
    <td> Paragraph </td>
    <td>
         <p>Default</p>
         <p>Line height default +3</p>
    </td>
    <td>
        Multiline description text
    </td>
  </tr>

  <tr>
    <td>Medium <p><code>JBFont.medium()</code></p></td>
    <td>Default –1</td>
    <td>
        Help text
    </td>
  </tr>

  <tr>
    <td> Medium semibold <p><code>JBFont.medium().asBold()</code></p> </td>
    <td>Default –1</td>
    <td>
        Group headers in popups
    </td>
  </tr>
</table>

If none of the built-in sizes work and a custom one is needed, define it as the default size +/- constant value. Do **not** hardcode font sizes.

### Examples in UI

Run popup:

![](typography_example_1.png){width=706}

Plugin page:

![](typography_example_2.png){width=706}

## Editor font

[JetBrains Mono](https://fonts.google.com/specimen/JetBrains+Mono) font is used by default for the Editor.

<table>
<tr><td width="20%"> Name </td>
<td width="20%"> Font size </td>
<td> Usage </td>  </tr>
  <tr>
    <td> Default </td>
    <td> Default </td>
    <td> Code in the editor, code snippets, completion popup, and documentation popup </td>
  </tr>
  <tr>
    <td> Small </td>
    <td> Default –1 </td>
    <td> Line number in the editor</td>
  </tr>
</table>

Users can change the editor font in <control>Editor | Color scheme | Color Scheme Font</control>. If the default font size is changed, other font sizes used in the editor are scaled respectively.

### Examples in editor

Line numbers in the gutter and code in the editor:

![](typography_example_4.png){width=706}

Editor font and paragraph UI font in the documentation popup:

![](typography_example_5.png){width=706}

<!--## Colors

The IDE text colors are in the table below. The editor text colors are managed by the editor color theme.

<table>
<tr><td> Name </td>
<td> Light </td>
<td> Dark </td>
<td> Usage </td>
<td> Color key </td>
</tr>
  <tr>
    <td> Default </td>
    <td> 000 </td>
    <td> <format color="#BBBBBB">BBB</format> </td>
    <td>
        Labels, inputs, trees, etc.
    </td>
    <td>
        <code>.foreground</code> keys for various UI controls.
        <p>Examples:</p>
        <ul>
        <li><code>Label.foreground</code></li>
        <li><code>Button.foreground</code></li>
        <li><code>ComboBox.foreground</code></li>
        <li><code>MenuItem.foreground</code></li>
        </ul>
    </td>
  </tr>

  <tr>
    <td> Info panel </td>
    <td> <format color="#808080">808080</format> </td>
    <td> <format color="#8C8C8C">8C8C8C</format> </td>
    <td>
        Inline help, shortcuts
    </td>
    <td>
        <code>Label.infoForeground</code>
    </td>
  </tr>

  <tr>
    <td> Info input </td>
    <td> <format color="#999999">999999</format> </td>
    <td> <format color="#787878">787878</format> </td>
    <td>
        Additional info in lists (paths, counters), placeholder
    </td>
    <td>
        <code>Component.infoForeground</code>
    </td>
  </tr>

  <tr>
    <td> Disabled </td>
    <td> <format color="#8C8C8C">8C8C8C</format> </td>
    <td> <format color="#777777">777777</format> </td>
    <td>
        Disabled labels, disabled links
    </td>
    <td>
        <code>.disabledForeground</code> and <code>.disabledText</code> keys for various UI controls.
        <p>Examples:</p>
        <ul>
            <li><code>Label.disabledForeground</code></li>
            <li><code>ComboBox.disabledForeground</code></li>
            <li><code>MenuItem.disabledForeground</code></li>
            <li><code>CheckBox.disabledText</code></li>
            <li><code>Button.disabledText</code></li>
        </ul>
</td>
  </tr>

  <tr>
    <td> Selected </td>
    <td> FFF on <format color="#62A7DB">BACKGROUND</format> </td>
    <td> FFF on <format color="#62A7DB">BACKGROUND</format> </td>
    <td>
        Selected text
    </td>
    <td>
        <code>.selectionForeground</code> keys for various UI controls.
        <p>Examples:</p>
        <ul>
            <li><code>MenuItem.selectionForeground</code></li>
            <li><code>Table.selectionForeground</code></li>
        </ul>
    </td>
  </tr>

  <tr>
    <td> Link </td>
    <td> <format color="#2470B3">2470B3</format> </td>
    <td> <format color="#589DF6">589DF6</format> </td>
    <td>
        Links
    </td>
    <td>
        Use the component <code>LinkLabel</code>
        <p>Color keys:</p>
        <ul>
            <li><code>Link.activeForeground</code></li>
            <li><code>Link.hoverForeground</code></li>
            <li><code>Link.pressedForeground</code></li>
            <li><code>Link.visitedForeground</code></li>
        </ul>
    </td>
  </tr>

  <tr>
    <td> Error </td>
    <td> <format color="#C7222D">C7222D</format> </td>
    <td> <format color="#FF5261">FF5261</format> </td>
    <td>
        Inline errors text
    </td>
    <td>
        <code>Label.errorForeground</code>
    </td>
  </tr>
</table>-->
