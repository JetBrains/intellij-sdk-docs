<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Description Text

<link-summary>UI guidelines on descriptions texts.</link-summary>

Description text provides explanatory information about a set of settings or a single item in a list or tree.

![](../../../images/ui/description_text/01_example.png)


## When to Use

Use description text to:

* Show details about items in a list or tree:

![](02_use_in_tree.png)

* Describe a group of settings:

<img src="../../../images/ui/description_text/03_use_in_settings.png"/>


## How to Use

### Text

Follow general rules in [Writing short and clear](writing_short.md) and [Punctuation](punctuation.md).

Use the default text style for plain text. Increase line height by 3px from the default value.

Use [H4 bold](typography.md) (Default bold) for headings or to highlight important words:

![](04_bold_header.png)

Use the editor font to highlight code snippets:

![](05_editor_font.png)

Use bullets for lists:

![](06_bullets.png)

Limit the width of the line to 70–80 symbols, as it is not convenient to read very long lines:

<p class='label incorrect'>Incorrect</p>

![](07_width_incorrect.png)

<p class='label correct'>Correct</p>

![](07_width_correct.png)

If the text does not fit, add a scrollbar:

![](08_scroll.png)


### Appearance

Do not add borders around description text. Use the panel color for the background.

<table>
<tr>
<td> <p> Incorrect </p></td>
<td> <p> Correct </p></td>
</tr>
  <tr>
    <td>
        <img src="../../../images/ui/description_text/09_frame_incorrect.png" />
    </td>
    <td>
        <img src="../../../images/ui/description_text/09_frame_correct.png" />
    </td>
  </tr>
</table>

For [links](link.md), use the default link color: `Link.activeForeground`. Underline links on hover:

![](10_link.png)


### Insets

<p>Separate the text from the surroundings with insets.</p>

Use a 12px horizontal inset if the description text’s area has other elements:

![](11_insets_12.png)

Increase inset up to 20px if the description text is the only element:

![](11_insets_20.png)

