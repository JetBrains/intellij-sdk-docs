---
title: Typography
category: Principles
---

## IDE font

The system fonts are used for the IDE user interface by default. The default font sizes are below:

<table style="header-column">
  <tr>
    <td> <strong> macOS </strong> </td>
    <td> SF Pro Text  </td>
    <td> 13 </td>
  </tr>
  <tr>
    <td> <strong>Window</strong> </td>
    <td> Segoe UI </td>
    <td> 12 </td>
  </tr>
  <tr>
    <td> <strong>Linux</strong> </td>
    <td> Ubuntu </td>
    <td> 15 </td>
  </tr>
</table>


Users can change the default font size in Settings. If the default font size is changed, other font sizes used in the UI are scaled respectively.

Use the built-in text styles from the table below whenever possible.

> Implementation example for font styles: [`LabelSizeDemoAction`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/LabelSizeDemoAction.kt)
>
{style="note"}

<table>

  <tr><td> Name </td>
  <td> Font size </td>
  <td> <p>Usage&nbsp;&nbsp;&nbsp;<a href="typography.md">Examples</a> </p></td></tr>

  <tr>
    <td> <strong>H0 bold</strong> <code>JBFont.h0().asBold()</code></td>
    <td> Default + 12 </td>
    <td>
        Rich text headers. See the "What’s New" page example below.
    </td>
  </tr>

  <tr>
    <td> <strong>H1 bold</strong> <code>JBFont.h1().asBold()</code></td>
    <td> Default + 9 </td>
    <td> </td>
  </tr>

  <tr>
    <td >H2<code>JBFont.h2()</code></td>
    <td> Default + 5 </td>
    <td class="table-line"> </td>
  </tr>

  <tr>
    <td> <strong>H2 bold</strong> <code>JBFont.h2().asBold()</code></td>
    <td> </td>
    <td>
        Small page header. Examples: Plugin name, GitHub timeline header
    </td>
  </tr>

  <tr>
    <td > H3<code>JBFont.h3()</code></td>
    <td> Default + 3 </td>
    <td class="table-line">
        Accent body text
    </td>
  </tr>

  <tr>
    <td><strong>H3 bold</strong><code>JBFont.h3().asBold()</code></td>
    <td> </td>
    <td>
        Headers in dialogs with a small number of elements (Customize page on Welcome screen, Login page in Get from VCS dialog)
    </td>
  </tr>

  <tr>
    <td> <strong>H4 bold <br/> (Default bold)</strong> <div style="margin: 10px 0 5px -5px"> <code>JBFont.h4().asBold()</code> <code>JBFont.regular().asBold()</code> </div> </td>
    <td> Default </td>
    <td>
        Header in dialogs with a large number of elements (Run configurations dialog), notification header, breadcrumbs in settings, header in navigation popup, accent elements in lists and trees. <br/><br/>
        Use <a href="group_header.md">Group header</a> to divide the page on groups, but not to draw too much attention to the headings.
    </td>
  </tr>

  <tr>
    <td> Default <div style="margin: 10px 0 5px -5px"><code>JBFont.regular()</code></div></td>
    <td> Default </td>
    <td>
        Labels, inputs, links, trees, tables and other controls; text outputs, notifications, shortcuts
    </td>
  </tr>

  <tr>
    <td> Paragraph </td>
    <td>
         Default <br/>
         Line height default + 3
    </td>
    <td>
        Multiline description text
    </td>
  </tr>

  <tr>
    <td >Medium<code>JBFont.medium()</code></td>
    <td>
        macOS: Default - 1 <br/>
        Win: Default <br/>
        Linux: Default - 1
    </td>
    <td class="table-line">
        Tool window header, navigation bar, editor breadcrumbs, editor tabs (small on macOS)
    </td>
  </tr>

  <tr>
    <td> <span style="font-size: 13px"> <strong>Medium bold</strong> </span> <div style="margin: 10px 0 5px -5px"><code>JBFont.medium().asBold()</code></div></td>
    <td> </td>
    <td>
        Module in the navigation bar
    </td>
  </tr>

  <tr>
    <td> <span style="font-size: 12px"> Small </span> <div style="margin: 10px 0 5px -5px"><code>JBFont.small()</code></div></td>
    <td>
        macOS: Default - 2 <br/>
        Win: Default <br/>
        Linux: Default - 2
    </td>
    <td>
        Status bar, tool window buttons, inline help, help text in tooltips, separators in lists
    </td>
  </tr>
</table>

If none of the built-in sizes work and a custom one is needed, define it as the default size +/- constant value. Do **not** hardcode font sizes.

Use the underlined text style for hovered links.


## Editor font

JetBrains Mono font is used by default for the Editor.

<table>
<tr><td> Name </td>
<td> Font size </td>
<td> Usage </td>  </tr>
  <tr>
    <td> Default </td>
    <td> Default </td>
    <td> Editor </td>
  </tr>
  <tr>
    <td> <span style="font-size: 13px;"> Small </span> </td>
    <td> Default - 1 </td>
    <td> Line number </td>
  </tr>
</table>


## Colors

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
    <td> BBB</td>
    <td>
        Labels, inputs, trees, etc.
    </td>
    <td>
        <code>.foreground</code> keys for various UI controls. Examples:
        <code>Label.foreground</code>
        <code>Button.foreground</code>
        <code>ComboBox.foreground</code>
        <code>MenuItem.foreground</code>
    </td>
  </tr>

  <tr>
    <td> Info panel </td>
    <td> <span style="color: #808080">808080</span> </td>
    <td> <span style="color: #8C8C8C">8C8C8C</span> </td>
    <td>
        Inline help, shortcuts
    </td>
    <td>
        <code>Label.infoForeground</code>
    </td>
  </tr>

  <tr>
    <td> Info input </td>
    <td> <span style="color: #999999">999999</span> </td>
    <td> <span style="color: #787878">787878</span> </td>
    <td>
        Additional info in&nbsp;lists (paths, counters), placeholder
    </td>
    <td>
        <code>Component.infoForeground</code>
    </td>
  </tr>

  <tr>
    <td> Disabled </td>
    <td> <span style="color: #8C8C8C">8C8C8C</span> </td>
    <td> <span style="color: #777777">777777</span> </td>
    <td>
        Disabled labels, disabled links
    </td>
    <td>
        <code>.disabledForeground</code> and <code>.disabledText</code> keys for various UI controls. Examples:
        <code>Label.disabledForeground</code> <br/>
        <code>ComboBox.disabledForeground</code> <br/>
        <code>MenuItem.disabledForeground</code> <br/>
        <code>CheckBox.disabledText</code> <br/>
        <code>Button.disabledText</code> <br/>
</td>
  </tr>

  <tr>
    <td> Selected </td>
    <td> <span style="color: #ffffff; background: #62A7DB;">FFF</span> </td>
    <td> <span style="color: #ffffff; background: #62A7DB;">FFF</span> </td>
    <td>
        Selected text
    </td>
    <td>
        <code>.selectionForeground</code> keys for various UI controls. Examples:
        <code>MenuItem.selectionForeground</code>
        <code>Table.selectionForeground</code>
    </td>
  </tr>

  <tr>
    <td> Link </td>
    <td> <span style="color: #2470B3">2470B3</span> </td>
    <td> <span style="color: #589DF6">589DF6</span> </td>
    <td>
        Links
    </td>
    <td>
        Use the component <code>LinkLabel</code> <br/><br/>
        Color keys: <br/>
        <code>Link.activeForeground</code> <br/>
        <code>Link.hoverForeground</code> <br/>
        <code>Link.pressedForeground</code> <br/>
        <code>Link.visitedForeground</code> <br/>
    </td>
  </tr>

  <tr>
    <td> Error </td>
    <td> <span style="color: #C7222D">C7222D</span> </td>
    <td> <span style="color: #FF5261">FF5261</span> </td>
    <td>
        Inline errors text
    </td>
    <td>
        <code>Label.errorForeground</code>
    </td>
  </tr>
</table>


## Examples

What’s New page that appears in the Editor tab:

![](whats_new.png)

Plugin page in the Settings dialog:

![](plugins.png)


Log in to GitHub page in the dialog:

![](github.png)

