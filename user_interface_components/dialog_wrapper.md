---
title: Dialogs
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## DialogWrapper

The
[`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java)
is the base class which is supposed to be used for all modal dialogs (and some non-modal dialogs) shown in *IntelliJ Platform*.

It provides the following features:

*  Button layout (platform-specific order of _OK_/_Cancel_ buttons, macOS-specific `Help` button)
*  Context help
*  Remembering the size of the dialog
*  Non-modal validation (displaying an error message text when the data entered into the dialog is not valid)
*  Keyboard shortcuts:
    *  <kbd>Esc</kbd> for closing the dialog
    *  <kbd>Left/Right</kbd> for switching between buttons
    *  <kbd>Y</kbd>/<kbd>N</kbd> for _Yes_/_No_ actions if they exist in the dialog
*  Optional _Do not ask again_ checkbox


When using the
[`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java)
class for a dialog, follow these steps:

*  Call the base class constructor and provide either a project in the frame of which the dialog will be displayed, or a parent component for the dialog.
*  Call the `init()` method from the constructor of the dialog class
*  Call the `setTitle()` method to set the title for the dialog box
*  Implement the `createCenterPanel()` method to return the component comprising the main contents of the dialog.

*  *Optional*: Override the `getPreferredFocusedComponent()` method and return the component that should be focused when the dialog is first displayed.
*  *Optional*: Override the `getDimensionServiceKey()` method to return the identifier which will be used for persisting the dialog dimensions.
*  *Optional*: Override the `getHelpId()` method to return the context help topic associated with the dialog.

The
[`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java)
class is often used together with [GUI Designer forms](https://www.jetbrains.com/help/idea/gui-designer-basics.html).
In this case, bind a GUI Designer form to the class extending `DialogWrapper`,
bind the top-level panel of the form to a field and return that field from the `createCenterPanel()` method.
When using Kotlin, use [Kotlin UI DSL](kotlin_ui_dsl.md) to provide the dialog's contents.
      
> **TIP** Existing dialogs can be inspected at runtime using [UI Inspector](/reference_guide/internal_actions/internal_ui_inspector.md), e.g., to locate the underlying implementation of UI components.

To display the dialog, call the `show()` method and then use the `getExitCode()` method to check how the dialog was closed. The `showAndGet()` method can be used to combine these two calls.

To customize the buttons displayed in the dialog (replacing the standard _OK_/_Cancel_/_Help_ set of buttons), override either the `createActions()` or `createLeftActions()` methods.
Both of these methods return an array of Swing Action objects.
If a button closes the dialog, use
[`DialogWrapperExitAction`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java)
as the base class for the action. 
Use `action.putValue(DialogWrapper.DEFAULT_ACTION, true)` to set the default button.

To validate the data entered into the dialog, override the `doValidate()` method.
The method will be called automatically by timer.
If the currently entered data is valid, return `null`.
Otherwise, return a
[`ValidationInfo`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/ValidationInfo.java)
object which encapsulates an error message, and an optional component associated with the invalid data.
When specifying a component, an error icon will be displayed next to it, and it will be focused when the user tries to invoke the _OK_ action.

## Example

Simple definition of a [`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java):

```java
public class SampleDialogWrapper extends DialogWrapper {

    public SampleDialogWrapper() {
        super(true); // use current window as parent
        init();
        setTitle("Test DialogWrapper");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("testing");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
```

Usage of [`DialogWrapper`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java):

```java
JButton testButton = new JButton();
testButton.addActionListener(actionEvent -> {
  if (new SampleDialogWrapper().showAndGet()) {
    // user pressed OK
  }
});
```
