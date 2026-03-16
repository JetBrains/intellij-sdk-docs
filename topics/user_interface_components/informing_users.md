<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Informing Users

<link-summary>Notifying users about errors, action statuses, or other events without interrupting their workflow by showing modal message boxes requiring confirmation.</link-summary>

<tldr>

**UI Guidelines:** [](notification_types.md)

</tldr>

One of the leading design principles is avoiding the use of modal message boxes for notifying the user about errors and other situations that may warrant the user's attention.
As a replacement, the IntelliJ Platform provides multiple non-modal notification UI options.

### Dialogs

<tldr>

**UI Guidelines:** [](validation_errors.md)

</tldr>

When working in a dialog, do not check the validity of the input and notify the user about invalid data with a modal dialog when the <control>OK</control> button is pressed.
Instead, use
[`DialogWrapper.doValidate()`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java)
described in the [Dialogs](dialog_wrapper.md#input-validation) section.

### Editor Hints

For actions invoked from the editor (such as refactorings, navigation actions, and different code insight features), the best way to notify the user about the inability to perform an action is to use the [`HintManager`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) class.

Its method `showErrorHint()` displays a floating popup above the editor which is automatically hidden when the user starts performing another action in the editor.
Other [`HintManager`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) methods can be used for displaying other kinds of non-modal notification hints over an editor.

### Editor Banner

<tldr>

**UI Guidelines:** [](banner.md)

</tldr>

Notifications that appear at the top of the file editor are a great way to ask the user to take an important action that would otherwise impede their experience if ignored (e.g., missing SDK, setup/project configuration requiring user input).

Register an implementation of [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java)
using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.editorNotificationProvider"/></include>.
If access to indexes is not required, it can be marked [dumb-aware](indexing_and_psi_stubs.md#DumbAwareAPI).

A commonly used UI implementation is [`EditorNotificationPanel`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationPanel.java).

### "Got It" Notification

{id="gotIt"}

<tldr>

**UI Guidelines:** [](got_it_tooltip.md)

</tldr>

Use to highlight important new/changed features via [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt).

### Notification Balloons

{id="balloons"}

The most general way to display non-modal notifications is to use [notification balloons](notification_balloons.md).
