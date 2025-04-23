<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Kotlin UI DSL Version 2
<primary-label ref="2021.3"/>

<link-summary>Kotlin DSL for creating UI forms with input components bound to a state object.</link-summary>

<tldr>

**UI Guidelines:** [](layout.md)

</tldr>

> This page describes API available in IntelliJ Platform releases **2021.3 and later** only.
>
> See [](kotlin_ui_dsl.md) for targeting earlier releases.
>

Kotlin UI DSL Version 2 allows creating UI forms with input components bound to state objects.
The forms are built by using a declarative Kotlin syntax and follow the official IntelliJ Platform UI conventions described in the [](ui_guidelines_welcome.topic).
The library is written in [Kotlin](using_kotlin.md) and makes it easy to develop user interfaces like [dialogs](dialog_wrapper.md) and [settings pages](settings.md).

The Kotlin UI DSL is not intended to build general UIs, like [tool windows](tool_window.md) controls that trigger some actions and do not contain any input components bound to state objects.
For this purpose, use [custom Swing components](user_interface_components.md) from the IntelliJ Platform or the standard ones.

The _Kotlin UI DSL Version 2_ functions are located in the [`com.intellij.ui.dsl.builder`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder) package.

## UI DSL Examples

It is highly recommended to take a look at the UI DSL demo available via <ui-path>Tools | Internal Actions | UI | UI DSL Showcase</ui-path> (see [Internal Actions](internal_actions_intro.md) if not available in your IDE instance).

It describes some UI DSL basics and contains explanations, tips, a list of all available components, and many examples with links to the source code.

> All sections below refer to the relevant tab available in this demo:
>
> **UI DSL Showcase Tab**: _Tab Name_ (Link to sources of demonstration tab)
>

<snippet id="ui_inspector_added_at">

> To understand how a component visible in the IDE is created in code, see the component's [`added-at` property in the UI Inspector](internal_ui_inspector.md#added-at-property).
> Note that it is not limited only to components created with Kotlin UI DSL, but helps to understand the creation of any visible Swing component.
>

</snippet>

## UI DSL Basics

**UI DSL Showcase Tab**: Basics (Sources: [`DemoBasics`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoBasics.kt))

See the following simple example of UI DSL:

```kotlin
panel {
  row("Enter value:") {
    textField()
  }
}
```

Building content of any form starts from `panel {` which returns [`DialogPanel`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogPanel.kt) filled with components described inside the panel block.
A panel consists of any number of rows marked with `row` tag created vertically from top to bottom.

Every row consists of cells where the last cell in a row occupies the remaining width.
Inside one row, cells are added from left to right in the same order calls to factory methods or `cell()` appear in each row.
Cells can contain one component or a sub-panel.

If there are unoccupied cells at the end of a row, they are merged into one cell with the last non-empty cell.

## `Panel`

[`Panel`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Panel.kt) is the start interface for building content.
It can consist of several rows and different UI groups.

### `Panel.row`

Adds row with the label if present.

### `Panel.indent`

**UI DSL Showcase Tab**: Gaps (Sources: [`DemoGaps`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGaps.kt))

Adds standard left indent:

```kotlin
row {
  label("Not indented row")
}
indent {
  row {
    label("Indented row")
  }
}
```

### `Panel.separator`

**UI DSL Showcase Tab**: Groups (Sources: [`DemoGroups`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGroups.kt))

Adds horizontal line separator with an optional title.

### `Panel.panel`

Creates a sub-panel that occupies the whole width and uses its own grid inside.

### `Panel.rowsRange`

Creates a grouped rows range to perform operations on them like `enabled`, `enabledIf` etc.
All rows use the parent grid.

### `Panel.group`

**UI DSL Showcase Tab**: Groups (Sources: [`DemoGroups`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGroups.kt))

Adds a panel with an independent grid, optional title, and some vertical space above and below the group.

```kotlin
group("Title") {
  row("Row:") {
    textField()
  }
}
```

### `Panel.groupRowsRange`

Similar to `Panel.group()` method but uses the same grid as the parent.

### `Panel.collapsibleGroup`

**UI DSL Showcase Tab**: Groups (Sources: [`DemoGroups`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGroups.kt))

Adds a collapsible panel with independent grid, title, and some vertical space above and below the group.

```kotlin
collapsibleGroup("Title") {
  row("Row:") {
    textField()
  }
}
```

### `Panel.buttonsGroup`

**UI DSL Showcase Tab**: Groups (Sources: [`DemoGroups`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGroups.kt))

Unions `Row.radioButton` in one group.
Must also be used for `Row.checkBox` if these are grouped with a title.

```kotlin
var value = true
buttonsGroup("Panel.buttonsGroup:") {
 row {
  radioButton("true", true)
 }
 row {
  radioButton("false", false)
 }
}.bind({ value }, { value = it })
```

### `Panel.onApply`/`onReset`/`onIsModified`

Registers callbacks that will be called from `DialogPanel.apply()`/`reset()`/`isModified()` methods.

## `Row`

Every row is represented by the [`Row`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Row.kt) interface.
It contains all available factory methods for creating components (like `button()`, `label()`, `textField()`, etc.) and methods for row configuration.

### `Row.layout`

**UI DSL Showcase Tab**: Row Layout (Sources: [`DemoRowLayout`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoRowLayout.kt))

There are three possible layouts:
- `INDEPENDENT`: all cells of the row (including label if present) independent of the parent grid.
  That means this row has its own grid.
- `LABEL_ALIGNED`: label is aligned with the parent grid, other components independent of the parent grid.
- `PARENT_GRID`: all components, including label (if present), are in the parent grid.

The default value is `LABEL_ALIGNED` when a label is provided for the row, `INDEPENDENT` otherwise.

```kotlin
row("Label:") {
  textField()
}
row("Long label:") {
  textField()
}
```

Here both labels are aligned together because rows have `LABEL_ALIGNED` by default.
If an independent layout is needed, then `INDEPENDENT` layout should be used:

```kotlin
row("Long label:") {
  textField()
}.layout(RowLayout.INDEPENDENT)
```

### `Row.resizableRow`

The row becomes resizable and occupies all vertical free space.
For several resizable rows, extra free space is divided between rows equally.

### `Row.rowComment`

**UI DSL Showcase Tab**: Comments (Sources: [`DemoComments`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoComments.kt))

Adds comment after the row with an appropriate color and font.
Visibility and enabled state of the row affects row comment as well.

### `Row.cell`

Adds `component`.
Use it only for custom specific components, all standard components like `label()`, `button()`, `checkbox()` etc. are covered by dedicated [`Row`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Row.kt) factory methods.

For example, there is no method for password field so that the following code can be used:

```kotlin
val passwordField = JPasswordField()
row {
  cell(passwordField)
}
```

### `Row.scrollCell(component)`

Adds `component` wrapped with [`JBScrollPane`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBScrollPane.java).

### `Row.topGap`/`bottomGap`

Adds a gap above/below the current row.
It is visible together with the row.
By default, `NONE` is used.
Between unrelated settings, `SMALL` can be used.
`MEDIUM` is used between groups and usually set automatically by `group()` method and similar ones.

### `Row.visible`/`enabled`

**UI DSL Showcase Tab**: Enabled/Visible (Sources: [`DemoAvailability`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoAvailability.kt))

Sets visibility/enabled state of the row, including row comment (see `Row.rowComment`) and all children recursively.
The row is invisible/disabled if there is an invisible/disabled parent.

### `Row.visibleIf`/`enabledIf`

**UI DSL Showcase Tab**: Enabled/Visible (Sources: [`DemoAvailability`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoAvailability.kt))

Binds row visibility/enabled state to the provided predicate.
Below is an example of a checkbox whose enabled state depends on another checkbox:

```kotlin
lateinit var checkBox: Cell<JBCheckBox>
row {
  checkBox = checkBox("Check to enable option")
}
row {
  checkBox("Option 1")
}.enabledIf(checkBox.selected)
```

### `Row.panel`

**UI DSL Showcase Tab**: Groups (Sources: [`DemoGroups`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGroups.kt))

Creates a sub-panel inside the cell of the row.
The panel contains its own set of rows and cells.
For example, it is possible to create several columns by creating a row with several panels inside.

## `Cell`

Every component in the UI DSL builder is wrapped into [`Cell`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Cell.kt) class.
Standard components should not be created directly but with factory methods from [`Row`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Row.kt) class like `checkBox()`, `button()` and others because of additional functionality, e.g., `textField()` is configured with the column's width, radio buttons are placed into radio buttons groups.

### `Cell.component`

`JComponent` that occupies the cell.

### `Cell.horizontalAlign`/`verticalAlign`
{#cell-horizontalVerticalAlign}

> Deprecated in 2022.3, use [](#cell-align) instead.

Sets horizontal/vertical alignment of content inside the cell.

```kotlin
row("Row:") {
  textField()
    .horizontalAlign(HorizontalAlign.FILL)
}
```

### `Cell.align`
{#cell-align}

<primary-label ref="2022.3"/>

Updates horizontal and/or vertical alignment of the component inside the cell.
Default alignment is `AlignX.LEFT` and `AlignY.CENTER`.

To stretch the content on whole cell, use `AlignX.FILL`/`AlignY.FILL`/`Align.FILL`.
For setting both horizontal and vertical alignment, use `Align` constants or overloaded plug operator
like `align(AlignX.LEFT + AlignY.TOP)`.

```kotlin
row("Row:") {
  textField()
    .align(AlignX.FILL)
}
```

### `Cell.resizableColumn`

**UI DSL Showcase Tab**: Tips (Sources: [`DemoTips`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoTips.kt))

Marks column of the cell as resizable: the column occupies all extra horizontal space in the panel and changes size together with the panel.
It's possible to have several resizable columns, which means extra space is shared between them.
There is no need to set resizable for cells in different rows, but in the same column: it has no additional effect.
Note that the size and placement of components in columns are managed by [](#cell-align) ([](#cell-horizontalVerticalAlign) for pre-2022.3).

```kotlin
row("Row") {
  textField()
    .resizableColumn()
  link("Config...") {}
}
```

### `Cell.gap`

**UI DSL Showcase Tab**: Gaps (Sources: [`DemoGaps`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoGaps.kt))

Separates the next cell in the current row with `rightGap`.
`RightGap.SMALL` gap is set after row label automatically by `Panel.row()` methods.

Below are some cases where `RightGap.SMALL` should be used:

```kotlin
row {
  val checkBox = checkBox("Use mail:")
                  .gap(RightGap.SMALL)
  textField()
}
row("Width:") {
  textField()
    .gap(RightGap.SMALL)
  label("pixels")
}
```

### `Cell.visible`/`enabled`

**UI DSL Showcase Tab**: Enabled/Visible (Sources: [`DemoAvailability`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoAvailability.kt))

Sets visibility/enabled state of the cell and all children recursively.
The cell is invisible/disabled if there is an invisible/disabled parent.

### `Cell.visibleIf`/`enabledIf`

**UI DSL Showcase Tab**: Enabled/Visible (Sources: [`DemoAvailability`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoAvailability.kt))

Binds cell visibility/enabled state to the provided predicate.

```kotlin
row {
  val mailCheckBox = checkBox("Use mail:")
                      .gap(RightGap.SMALL)
  textField()
    .enabledIf(mailCheckBox.selected)
}
```

### `Cell.comment`

**UI DSL Showcase Tab**: Comments (Sources: [`DemoComments`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoComments.kt))

Adds comment under the cell aligned by left edge with an appropriate color and font size (macOS uses smaller font).
Comment can contain HTML tags except `<html>`, which is added automatically.
The comment occupies the available width before the following comment (if present) or the whole remaining width.
Visibility and enabled state of the cell affect comment as well.

```kotlin
row("Label:") {
  textField()
    .comment("Comment for textField")
}
```

### `Cell.label`

**UI DSL Showcase Tab**: Components Labels (Sources: [`DemoComponentLabels`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoComponentLabels.kt))

Adds label at the specified position.
`LabelPosition.TOP` labels occupy available width before the next top label (if present) or the whole remaining width.
Visibility and enabled state of the cell affect the label as well.

```kotlin
row {
  textField()
    .label("Cell label on top:", LabelPosition.TOP)
}
```

### `Cell.onApply`/`onReset`/`onIsModified`

Registers callbacks that will be called for the component from `DialogPanel.apply()`/`reset()`/`isModified()` methods.

## Placeholder

Used as a reserved cell in the layout.
It can be created by `Row.placeholder()` method and populated by content later via component property or reset to `null`.

## Bindings

It is possible to bind component values to properties with the following methods.

### `Cell.validationRequestor`

Binds `component` value changing to property.
The property is updated when the value is changed and is not related to `DialogPanel.apply()`.
The method is rarely used directly.
There are many extensions for specific components described in [](#cellbind).

> In versions before 2023.3, use `Cell.graphProperty`.

### `Cell.bind`

**UI DSL Showcase Tab**: Binding (Sources: [`DemoBinding`](%gh-ic%/platform/platform-impl/internal/src/com/intellij/internal/ui/uiDslShowcase/DemoBinding.kt))

Binds `component` value provided by `componentGet` and `componentSet` methods to the specified binding property.
The property is applied only when `DialogPanel.apply()` is invoked.
Methods `DialogPanel.isModified()` and `DialogPanel.reset()` are also supported automatically for bound properties.

The `bind()` method is rarely used directly.
There are many extensions for specific components, see the following example:

```kotlin
row {
  checkBox("Checkbox")
    .bindSelected(model::checkbox)
}
row("textField:") {
  textField()
    .bindText(model::textField)
}
row("intTextField(0..100):") {
  intTextField()
    .bindIntText(model::intTextField)
}
row("comboBox:") {
  comboBox(Color.values())
    .bindItem(model::comboBoxColor)
}
row("slider:") {
  slider(0, 100, 10, 50)
    .bindValue(model::slider)
}
row("spinner:") {
  spinner(0..100)
    .bindIntValue(model::spinner)
}
buttonsGroup(title = "radioButton:") {
 for (value in Color.values()) {
  row {
   radioButton(value.name, value)
  }
 }
}.bind(model::radioButtonColor)
```

## Version 1 and 2 Comparison

In UI DSL version 2, some crucial problems from version 1 have been fixed, so porting is highly desirable.
See [](#migration-from-version-1) on how to port existing UI DSL code from version 1 to version 2 API.
Version 1 is deprecated and will be removed in future platform releases.

The following significant changes were made:

- Reduced API, which allows conceiving API easier and faster.
  Example: there were 5 overloaded methods `Cell.checkBox()` in version 1, now only one method remains.
  Functionality for binding properties is extracted into `Cell<T>.bindSelected()` methods.
- UI DSL became stricter, so the available API in every context is much smaller.
  Example: code like `row { row {` is forbidden now.
- Structured API is mostly based on interfaces, because it's easier to learn API by grouped methods.
  Only a small part of the API is implemented as extensions.
- KDoc is widely used.
- MIG layout is fully removed from the new UI DSL and replaced by `GridLayout`.
  Because MIG layout is an external library, it's hard to fix bugs there (e.g., there are layout problems when components become invisible) and extend its functionality.
  Fixed focus ring cropping problems: when components are placed near the panel border focus ring could be cropped if panel insets do not specify enough space.
- Implemented [Placeholder](#placeholder) that allows replacing components at runtime after content is shown.

### Migration from Version 1

The new API is very similar to the old one and covers almost all functionality now, so moving to the new version can be done quickly.

Version 1 is placed in `com.intellij.ui.layout` package.

Version 2 is placed in `com.intellij.ui.dsl.builder` package.

<procedure title="Migration Steps">

1. Having a screenshot or live version of the initial components layout can help
2. Remove imports of old UI DSL starting with `com.intellij.ui.layout`
3. Go to the place where the panel is created and import new UI DSL `com.intellij.ui.dsl.builder` suggested by IntelliJ IDEA
4. Update non-compilable code, using the following table

</procedure>

| Version 1                                         | Version 2                                                             |
|---------------------------------------------------|-----------------------------------------------------------------------|
| `row { row {`                                     | `indent { row {`                                                      |
| `row { cell(isFullWidth = true)`                  | `row {`                                                               |
| `fullRow {`                                       | `row {`                                                               |
| `titledRow(…) {`                                  | `group(…) {`                                                          |
| `hideableRow`                                     | `collapsibleGroup`                                                    |
| `cell` used as sub-grid                           | `row { panel { … } }`                                                 |
| `component(…)` or its invocation via `()`         | `cell(…)`                                                             |
| `enableIf`                                        | `enabledIf`                                                           |
| `checkBox(text, bindOptions)`                     | `checkBox(text).bindSelected(bindOptions)`                            |
| `radioButton(text, bindOptions)`                  | `radioButton(text).bindSelected(bindOptions)`                         |
| `comboBox(…, bindOptions)`                        | `comboBox(text).bindItem(bindOptions)`                                |
| `textField(bindOptions, columns)`                 | `textField().bindText(bindOptions).columns(columns)`                  |
| `scrollableTextArea(bindOptions, rows, columns)`  | `textArea().bindText(bindOptions).rows(rows).columns(columns)`        |
| `intTextField(bindOptions, columns, range, step)` | `intTextField(range, step).bindIntText(bindOptions).columns(columns)` |
| `textFieldWithBrowseButton(bindOptions, …)`       | `textFieldWithBrowseButton(…).bindText(bindOptions)`                  |
| `.growPolicy(GrowPolicy.COLUMNS_SHORT)`           | `.columns(SHORT_TEXT)`                                                |
| `.growPolicy(GrowPolicy.MEDIUM_TEXT)`             | `.columns(COLUMNS_MEDIUM)`                                            |
| `label(…, bold = true)`                           | `label(…).bold()`                                                     |
| `withLeftGap()`                                   | For previous left cell use `Cell.gap(SMALL)`                          |
| `withLeftGap(gapLeft)`                            | Please do not use custom gaps if possible                             |
| `withLargeLeftGap()`                              | Not needed, this gap is set by default                                |
| `withValidationOnInput`                           | `validationOnInput`                                                   |
| `withValidationOnApply`                           | `validationOnApply`                                                   |
| `withErrorOnApplyIf`                              | `errorOnApply`                                                        |
| `withBinding`                                     | `bind`                                                                |
