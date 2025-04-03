<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Creating, showing, and getting the input provided by users in dialogs.</link-summary>

# Dialogs

<tldr>

**UI Guidelines:** [](layout.md), [](validation_errors.md)

</tldr>

## `DialogWrapper`

The [`DialogWrapper`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) is the base class which is supposed to be used for all modal dialogs (and some non-modal dialogs) shown in IntelliJ Platform.

It provides the following features:

* Button layout (platform-specific order of <control>OK</control>/<control>Cancel</control> buttons, macOS-specific <control>Help</control> button)
* Context help
* Remembering the size of the dialog
* Non-modal validation (displaying an error message text when the data entered into the dialog is not valid)
* Keyboard shortcuts:
  * <shortcut>Esc</shortcut> for closing the dialog
  * <shortcut>Left/Right</shortcut> for switching between buttons
  * <shortcut>Y</shortcut>/<shortcut>N</shortcut> for <control>Yes</control>/<control>No</control> actions if they exist in the dialog
* Optional <control>Do not ask again</control> checkbox

> There's also a DSL-like API via [`DialogBuilder`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogBuilder.java).

### Usage

When using the [`DialogWrapper`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) class for a dialog, follow these required steps:

* Call the base class constructor and provide either a `Project` in the frame of which the dialog will be displayed, or a parent component for the dialog.
* Call the `setTitle()` method to set the title for the dialog
* Call the `init()` method from the constructor of the dialog class
* Implement the `createCenterPanel()` method to return the component comprising the main contents of the dialog.

Optionally:

* Override the `getPreferredFocusedComponent()` method and return the component that should be focused when the dialog is first displayed.
* Override the `getDimensionServiceKey()` method to return the identifier which will be used for persisting the dialog dimensions.
* Override the `getHelpId()` method to return the context help topic associated with the dialog (see [Context Help](ide_infrastructure.md#context-help)).

#### Dialog Content

Use [Kotlin UI DSL](kotlin_ui_dsl_version_2.md) to provide the dialog's contents (see [samples](#kotlin)).
Alternatively or when using Java, the `DialogWrapper` class can be used together with [GUI Designer forms](https://www.jetbrains.com/help/idea/gui-designer-basics.html).
In this case, bind a GUI Designer form to the class extending `DialogWrapper`, bind the top-level panel of the form to a field and return that field from the `createCenterPanel()` method.

> See [](layout.md) topic in UI Guidelines for recommendations on arranging UI controls in dialogs.
>
> Existing dialogs can be inspected at runtime using [UI Inspector](internal_ui_inspector.md), for example, to locate the underlying implementation of UI components.
>

#### Displaying the Dialog

To display the dialog, call the `show()` method and then use the `getExitCode()` method to check how the dialog was closed (see `DialogWrapper#OK_EXIT_CODE, CANCEL_EXIT_CODE, CLOSE_EXIT_CODE`).
The `showAndGet()` method can be used to combine these two calls.

#### Customizing Buttons

To customize the buttons displayed in the dialog (replacing the standard <control>OK</control>/<control>Cancel</control>/<control>Help</control> set of buttons), override either the `createActions()` or `createLeftActions()` methods.
Both of these methods return an array of Swing Action objects.
If a button closes the dialog, use [`DialogWrapperExitAction`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java) as the base class for the action.

Use `action.putValue(DialogWrapper.DEFAULT_ACTION, true)` to set the default button and
`action.putValue(DialogWrapper.FOCUSED_ACTION, true)` to set the focused button.

### Input Validation

See also [](validation_errors.md) topic in UI Guidelines.

To validate the data entered into the dialog, call `initValidation()` in the dialog's constructor and
override the `doValidate()` method to perform the actual validation.
The method will be called automatically via a timer.

If the currently entered data is valid, return `null`.
Otherwise, return a [`ValidationInfo`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/ui/ValidationInfo.java) object which encapsulates an error message, and an optional component associated with the invalid data.
When specifying a component, an error icon will be displayed next to it, and it will be focused when the user tries to invoke the <control>OK</control> action.

## Examples

Minimum sample of a [`DialogWrapper`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java):

```java
public class SampleDialogWrapper extends DialogWrapper {

  public SampleDialogWrapper() {
    super(true); // use current window as parent
    setTitle("Test DialogWrapper");
    init();
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    JPanel dialogPanel = new JPanel(new BorderLayout());

    JLabel label = new JLabel("Testing");
    label.setPreferredSize(new Dimension(100, 100));
    dialogPanel.add(label, BorderLayout.CENTER);

    return dialogPanel;
  }
}
```

Show `SampleDialogWrapper` dialog when user clicks on button:

```java
JButton testButton = new JButton();
testButton.addActionListener(actionEvent -> {
  if (new SampleDialogWrapper().showAndGet()) {
    // user pressed OK
  }
});
```

### Kotlin

Dialogs using [Kotlin UI DSL](kotlin_ui_dsl_version_2.md):

- [`AddActionDialog`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/ui/customization/AddActionDialog.kt)
- [`InvalidateCachesDialog`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/InvalidateCachesDialog.kt)
