<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Kotlin UI DSL Version 1
<primary-label ref="Deprecated"/>

<link-summary>Kotlin DSL for creating UI forms with input components bound to state object.</link-summary>

<tldr>

**UI Guidelines:** [](layout.md)

</tldr>

> When targeting IntelliJ Platform 2021.3 and later, only use [Kotlin UI DSL Version 2](kotlin_ui_dsl_version_2.md) (currently called just _Kotlin UI DSL_).
>
> **Usage of the version documented on this page is an error since 2025.1.**
>
> Please note [breaking changes](api_changes_list.md) can occur for this API between major releases.
>
{style="warning" title="Kotlin UI SDL Version 1 vs 2"}

Kotlin UI DSL allows creating UI forms with input components bound to state objects.
The forms are built by using a declarative Kotlin syntax.
It shares similarities with [Jetpack Compose](https://developer.android.com/jetpack/compose) for Android and is intended to build UI forms or part of forms for, e.g. dialogs and settings pages.

The Kotlin UI DSL is not intended to build general UIs, like tool windows controls that trigger some actions and do not contain any input components bound to state objects.
For this purpose, use [custom Swing components](user_interface_components.md) from the IntelliJ Platform or the standard ones.

This document covers the Kotlin UI DSL in IntelliJ Platform 2019.2.
 A lot of the features described in this document are not available for plugins targeting earlier versions.

The _Kotlin UI DSL Version 1_ functions are located in the [`com.intellij.ui.layout`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/layout) package.

## Layout Structure

> See [](layout.md) topic in UI Guidelines for recommendations on arranging UI controls in dialogs.
>

Use [`panel`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/layout/layout.kt) to create UI:

```kotlin
panel {
  row {
    // child components
  }
}
```

Rows are created vertically from top to bottom, in the same order as lines of code that call `row`.
Inside one row, you add components from left to right in the same order calls to factory method or `()` appear in each row.
Every component is effectively placed in its own grid cell.

The label for the row can be specified as a parameter for the `row` method:

```kotlin
row("Parameters") { ... }
```

Rows can be nested.
Components in a nested row block are considered to be subordinate to the containing row and are indented accordingly.

```kotlin
row {
  checkBox(...)
  row {
    textField(...) // indented relatively to the checkbox above
  }
}
```

To put multiple components in the same grid cell, wrap them in a `cell` method:

```kotlin
row {
  // These two components will occupy two columns in the grid
  label(...)
  textField(...)

  // These two components will be placed in the same grid cell
  cell {
    label(...)
    textField(...)
  }
}
```

To put a component on the right side of a grid row, use the `right` method:

```kotlin
row {
  rememberCheckBox()
  right {
    link("Forgot password")
  }
}
```

> To visually debug layout, enable <control>UI DSL Debug Mode</control> from [Internal Actions - UI Submenu](internal_ui_sub.md).
>

## Adding Components

There are two ways to add child components.
The recommended way is to use factory methods `label`, `button`, `radioButton`, `link`, etc.
It allows you to create consistent UI and reuse common patterns.

These methods also support **property bindings**, allowing you to automatically load the value displayed in the component from a property and to store it back.
The easiest way to do that is to pass a reference to a Kotlin bound property:

```kotlin
checkBox("Show tabs in single row", uiSettings::scrollTabLayoutInEditor)
```

Note that the bound property reference syntax also can be used to reference Java fields, but not getter/setter pairs.

Alternatively, many factory methods support specifying a getter/setter pair for cases when a property mapping is more complicated:

```kotlin
checkBox(
  "Show file extensions in editor tabs",
  { !uiSettings.hideKnownExtensionInTabs },
  { uiSettings.hideKnownExtensionInTabs = !it })
```

If you want to add a component for which there are no factory methods, you can simply invoke an instance of your component, using the `()` overloaded operator:

```kotlin
val customComponent = MyCustomComponent()
panel {
  row { customComponent() }
}
```

## Supported Components

### Labels

Use the `label` method:

```kotlin
label("Sample text")
```

### Checkboxes

See examples above.

### Radio Buttons

Radio button groups are created using the `buttonGroup` block.
There are two ways to use it.
If the selected radio button corresponds to a specific value of a single property, pass the property binding to the `buttonGroup` method and the specific values to `radioButton` functions:

```kotlin
buttonGroup(mySettings::providerType) {
  row { radioButton("In native Keychain", ProviderType.KEYCHAIN) }
  row { radioButton("In KeePass", ProviderType.KEEPASS) }
}
```

If the selected radio button is controlled by multiple boolean properties, use `buttonGroup` with no binding and specify property bindings for all but one of the radio buttons:

```kotlin
buttonGroup {
  row { radioButton("The tab on the left") }
  row { radioButton("The tab on the right", uiSettings::activeRightEditorOnClose) }
  row { radioButton("Most recently opened tab", uiSettings::activeMruEditorOnClose) }
}
```

### Text Fields

Use the `textField` method for a simple text field:

```kotlin
row("Username:") {
    textField(settings::userName)
}
```

For entering numbers, use `intTextField`:

```kotlin
intTextField(uiSettings::editorTabLimit, columns = 4, range = EDITOR_TABS_RANGE)
```

For password text fields, there is no factory function available, so you need to use `()`:

```kotlin
val passwordField = JPasswordField()
val panel = panel {
  // ...
  row { passwordField() }
}
```

To specify the size of a text field, either pass the `columns` parameter as shown in the `intTextField` example above, or use `growPolicy()`:

```kotlin
val userField = JTextField(credentials?.userName)
val panel = panel {
  row("Username:") { userField().growPolicy(GrowPolicy.SHORT_TEXT) }
}
```

### Combo Boxes

Use the `comboBox` method with either a bound property, or a getter/setter pair:

```kotlin
comboBox(DefaultComboBoxModel<Int>(tabPlacements), uiSettings::editorTabPlacement)

comboBox<PgpKey>(
  pgpListModel,
  { getSelectedPgpKey() ?: pgpListModel.items.firstOrNull() },
  { mySettings.state.pgpKeyId = if (usePgpKey.isSelected) it?.keyId else null })
```

### Spinners

Use the `spinner` method:

```kotlin
spinner(retypeOptions::retypeDelay, minValue = 0, maxValue = 5000, step = 50)
```

### Link Label

Use the `link` method:

```kotlin
link("Forgot password?") {
  // handle click, e.g. showing dialog
}
```

To open URL in the browser, use `browserLink`:

```kotlin
browserLink("Open Homepage", "https://www.jetbrains.com")
```

### Separators

Use the `titledRow` method and put the controls under the separator into the nested block:

```kotlin
titledRow("Appearance") {
  row { checkBox(...) }
}
```

### Explanatory Text

Use the `comment` parameter:

```kotlin
checkBox(message("checkbox.smart.tab.reuse"),
       uiSettings::reuseNotModifiedTabs,
       comment = message("checkbox.smart.tab.reuse.inline.help"))
```

## Integrating Panels with Property Bindings

A panel returned by the `panel` method is an instance of [`DialogPanel`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogPanel.kt).
This base class supports the standard `apply()`, `reset()`, and `isModified()` methods.

### Dialogs

**Reference**: [DialogWrapper](dialog_wrapper.md)

If you're using a [`DialogPanel`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogPanel.kt) as the main panel of a `DialogWrapper`, the `apply()` method will be automatically called when the dialog is closed using <control>OK</control> action.
The other methods are unused in this case.

Use the `focused()` method to specify which control should be focused when the dialog is initialized:

```kotlin
return panel {
  row("Target class name:") {
    textField(::className).focused()
  }
}
```

### Configurables

**Reference**: [Settings Guide](settings_guide.md)

If you're using the UI DSL to implement a [`Configurable`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/options/Configurable.java), use [`BoundConfigurable`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/BoundConfigurable.kt) as the base class.
In this case, the `Configurable` methods will be automatically delegated to the panel.

## Enabling and Disabling Controls

Use the `enableIf` method to bind the enabled state of a control to the values entered in other controls.
The parameter of the method is a **predicate**.

```kotlin
checkBox("Show tabs in single row", uiSettings::scrollTabLayoutInEditor)
  .enableIf(myEditorTabPlacement.selectedValueIs(SwingConstants.TOP))
```

The available predicates are:
* `selected` to check the selected state of a checkbox or radio button
* `selectedValueIs` and `selectedValueMatches` to check the selected item in a combo box.

Predicates can be combined with `and` and `or` infix functions:

```kotlin
checkBox("Hide tabs if there is no space", uiSettings::hideTabsIfNeed)
  .enableIf(myEditorTabPlacement.selectedValueMatches { it != UISettings.TABS_NONE } and
              myScrollTabLayoutInEditorCheckBox.selected)
```

## Examples

Sample usages in IntelliJ Platform IDEs:

| User Interface                                                                                                                | Implementation                                                                                                                   |
|-------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| <ui-path>Settings &#124; Editor &#124; Reader Mode</ui-path>                                                                  | [`ReaderModeConfigurable`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/actions/ReaderModeConfigurable.kt)            |
| <control>New Branch</control> dialog in Git ([Manage Git branches](https://www.jetbrains.com/help/idea/manage-branches.html)) | [`GitNewBranchDialog`](%gh-ic%/plugins/git4idea/src/git4idea/branch/GitNewBranchDialog.kt)                                       |
| <ui-path>Settings &#124; Tools &#124; Diff & Merge</ui-path>                                                                  | [`DiffSettingsConfigurable`](%gh-ic%/platform/diff-impl/src/com/intellij/diff/settings/DiffSettingsConfigurable.kt)              |
| <ui-path>Settings &#124; Editor &#124; General &#124; Editor Tabs</ui-path>                                                   | [`EditorTabsConfigurable`](%gh-ic%/platform/platform-impl/src/com/intellij/application/options/editor/EditorTabsConfigurable.kt) |

<include from="kotlin_ui_dsl_version_2.md" element-id="ui_inspector_added_at"></include>

## FAQ

### One Cell Is Minimum, Second One Is Maximum

Set `CCFlags.growX` and `CCFlags.pushX` for some component in the second cell.

## Comparison with Kotlin UI DSL Version 2

> [_Kotlin UI DSL_](kotlin_ui_dsl_version_2.md) was formerly known as _Kotlin UI DSL **Version 2**_.
>
> _Kotlin UI DSL **Version 1**_ was deprecated starting from 2021.3, and its API was gradually reduced.
> Since 2025.1 its usage is considered an error.
>
> For simplicity, _Kotlin UI DSL **Version 2**_ is now just _Kotlin UI DSL_.

The [new Kotlin UI DSL](kotlin_ui_dsl_version_2.md) fixes some crucial problems from version 1.
See [](#migration-to-version-2) on how to port existing UI DSL code from version 1 to the new version.

The following significant changes were made:

- Reduced API, which allows conceiving API easier and faster.
  Example: there were five overloaded methods `Cell.checkBox()` in version 1, now only one method remains.
  Functionality for binding properties is extracted into `Cell<T>.bindSelected()` methods.
- UI DSL became stricter, so the available API in every context is much smaller.
  Example: code like `row { row {` is forbidden now.
- Structured API is mostly based on interfaces because it's easier to learn API by grouped methods.
  Only a small part of the API is implemented as extensions.
- KDoc is widely used.
- MIG layout is fully removed from the new UI DSL and replaced by `GridLayout`.
  Because MIG layout is an external library, it's hard to fix bugs there (e.g., there are layout problems when components become invisible) and extend its functionality.
  Fixed focus ring cropping problems: when components are placed near the panel border focus ring could be cropped if panel insets do not specify enough space.
- Implemented [Placeholder](kotlin_ui_dsl_version_2.md#placeholder) that allows replacing components at runtime after content is shown.

### Migration to Version 2

The new API is very similar to the old one and covers almost all functionality now, so moving to the new version can be done quickly.

Version 1 is placed in `com.intellij.ui.layout` package.

Version 2 is placed in `com.intellij.ui.dsl.builder` package.

<procedure title="Migration Steps">

1. Having a screenshot or live version of the initial components layout can help
2. Remove imports of old UI DSL starting with `com.intellij.ui.layout`
3. Go to the place where the panel is created and import the new UI DSL `com.intellij.ui.dsl.builder` suggested by IntelliJ IDEA
4. Update non-compilable code, according to the following table.

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
