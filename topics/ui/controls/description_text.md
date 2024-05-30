<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Description Text

<link-summary>UI guidelines on descriptions texts.</link-summary>

Description text provides explanatory information about a set of settings or a single item in a list or tree.

![](01_description_text_example.png){width=715}

## When to Use

Use description text to:

* Show details about items in a list or tree:

![](02_use_in_tree.png){width=665}

* Describe a group of settings:

![](03_use_in_settings.png){width=1012}

## How to Use

### Text

Follow general rules in [Writing short and clear](writing_short.md) and [Punctuation](punctuation.md).

Use the default text style for plain text. Increase line height by 3px from the default value.

Use [H4 bold](typography.md) (Default bold) for headings or to highlight important words:

![](04_bold_header.png){width=320}

Use the editor font to highlight code snippets:

![](05_editor_font.png){width=283}

Use bullets for lists:

![](06_bullets.png){width=283}

Limit the width of the line to 70–80 symbols, as it is not convenient to read very long lines:

<format color="Red" style="bold">Incorrect</format>

![](07_width_incorrect.png){width=878}

<format color="Green" style="bold">Correct</format>

![](07_width_correct.png){width=878}

If the text does not fit, add a scrollbar:

![](08_scroll.png){width=333}

### Appearance

Do not add borders around the description text. Use the panel color for the background.

| <format color="Red" style="bold">Incorrect</format> | <format color="Green" style="bold">Correct</format> |
|-----------------------------------------------------|-----------------------------------------------------|
| ![](09_frame_incorrect.png){width="302"}            | ![](09_frame_correct.png){width="302"}              |

For [links](link.md), use the default link color: `Link.activeForeground`. Underline links on hover:

![](10_link.png){width=283}

### Insets

Separate the text from the surroundings with insets.

Use a 12px horizontal inset if the description text’s area has other elements:

![](11_insets_12.png){width=586}

Increase inset up to 20px if the description text is the only element:

![](11_insets_20.png){width=571}

