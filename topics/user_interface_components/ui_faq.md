<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# User Interface FAQ

<link-summary>UI-related FAQs and utilities.</link-summary>

<include from="user_interface_components.md" element-id="inspectingExistingUI"/>

## Useful Classes

- Package [`com.intellij.ui`](%gh-ic%/platform/util/ui/src/com/intellij/ui/)
- Package [`com.intellij.util.ui`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/)

## Colors

Always use [`JBColor`](%gh-ic%/platform/util/ui/src/com/intellij/ui/JBColor.java) instead of plain `java.awt.Color`
(highlighted via inspection <control>Plugin DevKit | Code | Use Darcula aware JBColor</control>).
Custom colors must be retrieved via `JBColor.namedColor()` set by the current [Theme](themes_getting_started.md).
See [](themes_metadata.md) on how to expose corresponding metadata.

If it's needed to retrieve a color from one place and use it in another, do not just retrieve once and use the retrieved value.
Instead, use `JBColor.lazy()` and pass in a lambda expression to retrieve the color.
This lambda expression needs to be fast and safe enough, as it will be called every time the color is retrieved, for example, for painting.
Following this approach ensures that the color will be properly updated if it's changed at the source, for example, due to a theme or scheme change.

Generic UI colors (e.g., for drawing borders) can be accessed via [`UIUtil`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/UIUtil.java)
and [`JBUI`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/JBUI.java).
A number of hardcoded colors is defined in `JBColor`, [`Gray`](%gh-ic%/platform/util/ui/src/com/intellij/ui/Gray.java), and [`LightColors`](%gh-ic%/platform/util/ui/src/com/intellij/ui/LightColors.java)

[`ColorUtil`](%gh-ic%/platform/util/ui/src/com/intellij/ui/ColorUtil.java) allows tuning existing colors.

## Text

<tldr>

**UI Guidelines:** [](data_formats.md)

</tldr>

Use [`NaturalComparator`](%gh-ic%/platform/util/base/src/com/intellij/openapi/util/text/NaturalComparator.java) for "natural" sorting of items.

[`StringUtil`](%gh-ic%/platform/util/src/com/intellij/openapi/util/text/StringUtil.java) contains a number of useful methods for manipulating text for UI usage:

- `unpluralize()/pluralize()` using English rules
- `formatDuration()` to format duration: _2 m 3 s 456 ms_
- `formatFileSize()` to format filesize: _1.23 KB_
- `escapeLineBreak()` and related methods to escape special characters
- `shortenTextWithEllipsis()` and `shortenPathWithEllipsis()` to produce abbreviated UI texts ending with '&#8230;'
- `quote()` and `unquoteString()` to wrap values: _Usages of "\$value\$": 218 found_

See [](internationalization.md) for information about internationalizing plugins.

See [`NlsMessages`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/nls/NlsMessages.java) to produce localized messages.

## "Recently Used" Entries

To store and retrieve values for *Recently Used* entries (e.g., filter values), use
[`RecentsManager`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/RecentsManager.java).

## Current Theme: Dark or Bright?

To determine the current [Theme](themes_getting_started.md)'s style, use [`JBColor.isBright()`](%gh-ic%/platform/util/ui/src/com/intellij/ui/JBColor.java).

## Borders and Insets

<tldr>

**UI Guidelines:** [](layout.md)

</tldr>

Always create borders and insets via factory methods from [`JBUI.Borders`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/JBUI.java) and [`JBUI.Insets`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/JBUI.java), which create DPI-aware instances.
Using standard DPI-agnostic instances (reported by inspection <ui-path>Plugin DevKit | Code | Use DPI-aware borders</ui-path> and <ui-path>Plugin DevKit | Code | Use DPI-aware insets</ui-path>)
can result in UI layout problems.

If you use DPI-aware insets in an empty border (`JBUI.Borders.empty()`), then the insets will be updated automatically, for example, if scaling is changed because the <ui-path>IDE Zoom</ui-path> action was performed or for any other reason.
If you use the insets elsewhere, you need to manually call `JBInsets.update()` in your component's `updateUI()` method to update the insets accordingly.

## Icons

### Customizing Existing Icons

Plugins may need to override icons for existing elements, for example, for XML/JSON configuration files related its functionality.

Use [`FileIconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/FileIconProvider.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileIconProvider"/></include>
to provide custom icons for [](virtual_file.md).

For [PSI elements](psi.md), implement [`IconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconProvider.java)
and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconProvider"/></include>.

### Progress Placeholder

[`LoadingDecorator`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/LoadingDecorator.kt) provides "loading" placeholder panel.

### Manipulating Icons

Use [`IconUtil`](%gh-ic%/platform/core-ui/src/util/IconUtil.kt) to scale/colorize/darken/desaturate existing [icons](icons.md) as needed.

### Combining Icons

Use [`RowIcon`](%gh-ic%/platform/core-ui/src/ui/RowIcon.kt) to combine icons horizontally (_\$modifier\$_ _\$element\$_).

To stack icons, use [`LayeredIcon`](%gh-ic%/platform/core-ui/src/ui/LayeredIcon.kt).
[`IconWithOverlay`](%gh-ic%/platform/core-ui/src/ui/icons/IconWithOverlay.java) additionally allows controlling the shape of the overlay icon.

<include from="snippets.topic" element-id="missingContent"/>
