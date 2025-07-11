<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Accessibility

<link-summary>Making the UI accessible to more people.</link-summary>

Accessibility means building interfaces that everyone can use, regardless of their abilities.
It allows people with different needs, such as those who rely on assistive technologies or need keyboard-only navigation, to fully interact with the product.
Follow these guidelines, based on [industry standards](https://www.w3.org/TR/WCAG22/), to ensure the UI is accessible for all.

## Keyboard accessibility

All functionality must be operable by using only the keyboard.
People who have trouble using a mouse, including those who are blind, have low vision, or have motor disabilities, rely on keyboard navigation to effectively use the interface.

### Focus basics

The basic way to interact with the UI by using the keyboard is to switch focus between components with <shortcut>Tab</shortcut> and <shortcut>Shift+Tab</shortcut>.
For some components, arrow keys can also be used to navigate between items.
Once a component is focused, it can be activated by pressing <shortcut>Space</shortcut>.

Make all interactive elements focusable to achieve [full keyboard operability](https://www.w3.org/WAI/WCAG22/Understanding/keyboard.html).
This includes buttons, checkboxes, text fields, lists, tables, dropdowns, and other controls that users can interact with.

Non-interactive components, such as labels and panels, should not be focusable.
However, in some cases, they can be focusable to let screen reader users access important information.
Use [`ScreenReader.isActive()`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/accessibility/ScreenReader.java) to adjust the behavior only for screen reader users.

### Keyboard traps

Make the UI traversable in a cycle without trapping the keyboard focus.
A [keyboard focus trap](https://www.w3.org/WAI/WCAG22/Understanding/no-keyboard-trap.html) occurs when a user cannot navigate away from a component by using the standard keyboard navigation.
This prevents them from accessing other parts of the interface.

Ensure that a user can always navigate away from any focusable component by using <shortcut>Tab</shortcut>, <shortcut>Shift+Tab</shortcut>, or <shortcut>Escape</shortcut>.

### Managing focus

When new content appears, such as popups, modal dialogs, or dynamic content, ensure that:

* Focus is moved to the new content, or there is a way to reach it by using only the keyboard.
* Focus is not moved unexpectedly when users are interacting with components like lists or dropdowns to avoid a [change of context](https://www.w3.org/WAI/WCAG22/Understanding/on-focus.html#dfn-changes-of-context).

## Assistive technology support

Assistive technologies, such as screen readers and voice control, rely on accessibility metadata provided by UI components.
For example, a checkbox might be announced by screen readers as "Don't ask again, checkbox, not checked, Alt+D".
Voice control users can say "Press Don't ask again" to activate the checkbox.
This integration is enabled by properly defined accessible metadata.

Follow these guidelines to ensure that users of assistive technology can fully interact with the UI.

### Accessible properties

Each UI component has an associated [`javax.accessibility.AccessibleContext`](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/AccessibleContext.html) object that defines properties for assistive technologies.
The accessible context can also implement [`Accessible*`](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/package-summary.html#class-summary) interfaces to provide additional metadata or ways for assistive technologies to interact with the component.

> For common UI components, basic accessibility support is often already implemented.
> But when creating custom components or extending existing ones, you may need to extend the accessible context or modify its properties.
>
{style="note"}

Example of customizing the accessible context and its properties:
<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
class ActionLink : JButton() {
  override fun getAccessibleContext(): AccessibleContext {
    if (accessibleContext == null) {
      accessibleContext = object : AccessibleAbstractButton() {
        override fun getAccessibleRole() = AccessibleRole.HYPERLINK
        override fun getAccessibleValue() = null
      }
    }
    return accessibleContext
  }
}

val link = ActionLink()
link.accessibleContext.accessibleName = "Open in browser"
```

</tab>
<tab title="Java" group-key="java">

```java
class ActionLink extends JButton {
  @Override
  public AccessibleContext getAccessibleContext() {
    if (accessibleContext == null) {
      accessibleContext = new AccessibleAbstractButton() {
        @Override
        public AccessibleRole getAccessibleRole() {
          return AccessibleRole.HYPERLINK;
        }

        @Override
        public AccessibleValue getAccessibleValue() {
          return null;
        }
      };
    }
    return accessibleContext;
  }
}

ActionLink link = new ActionLink();
link.getAccessibleContext().setAccessibleName("Open in browser");
```

</tab>
</tabs>

#### Accessible name and description

An [accessible name](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/AccessibleContext.html#getAccessibleName()) is the label that defines the component's purpose for assistive technology users.
Set a clear and descriptive accessible name for all focusable components.

For example, a simple button with text should have that text as its accessible name.
For a text field with a label in front of it, the accessible name should be the label's text.
A list without a visible title should still have an accessible name that describes its content.

In many cases, the accessible name is already taken implicitly from the following sources:

* The text property of components such as labels or buttons.
* Tooltip text.
* The label of the component that was set by `JLabel.setLabelFor()`.

If the name was not applied implicitly, set it by calling `AccessibleContext.setAccessibleName()` or by overriding `AccessibleContext.getAccessibleName()`:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
class AccessibleCheckBox : AccessibleJLabel() {
  override fun getAccessibleName() = myLabel.text
}

checkBox.accessibleContext.accessibleName = "Don't ask again"
```

</tab>
<tab title="Java" group-key="java">

```java
class AccessibleCheckBox extends AccessibleJLabel {
  @Override
  public String getAccessibleName() {
    return myLabel.getText();
  }
}

checkBox.getAccessibleContext().setAccessibleName("Don't ask again");
```

</tab>
</tabs>

Tips for choosing an accessible name:

* Don't include the component's role in the accessible name.
  For example, for a password text field, set the name as "Password" instead of "Password text field."
* For complex components, include all visible information in the accessible name.
  For example, for a panel that consists of a title, subtitle, and icon, combine all these parts in the accessible name.

An accessible description provides additional context, such as instructions, keyboard shortcuts, placeholders, or explanatory text.
Use it for supplementary information that helps users understand how to interact with the component.

The accessible description is set similarly to the accessible name, either by calling `AccessibleContext.setAccessibleDescription()` or by overriding `AccessibleContext.getAccessibleDescription()`.

#### Accessible role

The accessible role tells assistive technologies what type of component they are interacting with.
Use the [`AccessibleRole`](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/AccessibleRole.html) value that correctly represents the component's function.

The accessible role can be changed by overriding `AccessibleContext.getAccessibleRole()`:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
class AccessibleCheckBox : AccessibleJLabel() {
  override fun getAccessibleRole() = AccessibleRole.CHECK_BOX
}
```

</tab>
<tab title="Java" group-key="java">

```java
class AccessibleCheckBox extends AccessibleJLabel {
  @Override
  public AccessibleRole getAccessibleRole() {
    return AccessibleRole.CHECK_BOX;
  }
}
```

</tab>
</tabs>

Tips for customizing the role:

* Use `AccessibleRole.LABEL` for plain text content and `AccessibleRole.TEXT` for text fields and text areas that are editable or support selection.
* Set an appropriate button role: `AccessibleRole.PUSH_BUTTON`, `AccessibleRole.RADIO_BUTTON`, `AccessibleRole.TOGGLE_BUTTON`, or `AccessibleRole.HYPERLINK`.

#### Accessible state

The [accessible state](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/AccessibleState.html) communicates the component's current condition to assistive technologies.
For example, it can tell screen reader users that the component is selected, expanded, or editable.

Adjust the component's state set by overriding `AccessibleContext.getAccessibleStateSet()`:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
class AccessibleCheckBox : AccessibleJLabel() {
  override fun getAccessibleStateSet(): AccessibleStateSet {
    val set = super.getAccessibleStateSet()
    if (myChecked) {
      set.add(AccessibleState.CHECKED)
    }
    return set
  }
}
```

</tab>
<tab title="Java" group-key="java">

```java
class AccessibleCheckBox extends AccessibleJLabel {
  @Override
  public AccessibleStateSet getAccessibleStateSet() {
    AccessibleStateSet set = super.getAccessibleStateSet();
    if (myChecked) {
      set.add(AccessibleState.CHECKED);
    }
    return set;
  }
}
```

</tab>
</tabs>


Notify assistive technologies when the state changes:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
accessibleContext.firePropertyChange(
  AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
  null,
  AccessibleState.CHECKED
)
```

</tab>
<tab title="Java" group-key="java">

```java
getAccessibleContext().firePropertyChange(
  AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
  null,
  AccessibleState.CHECKED
);
```

</tab>
</tabs>

#### Accessible interfaces

Advanced accessibility features are provided through specialized interfaces, such as `AccessibleAction`, `AccessibleText`, `AccessibleSelection`, and `AccessibleValue`.
These are typically needed when implementing custom components from scratch.
Refer to the [list](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/accessibility/package-summary.html) of available interfaces to determine whether any need to be implemented.

> Look at similar Swing components to see which interfaces they implement. For example, when working on a new slider-like component, check the interfaces implemented by [`JSlider.AccessibleJSlider`](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JSlider.AccessibleJSlider.html).
>
{style="note"}

### Announcing live changes

Screen readers announce accessible property changes of the currently focused component automatically.
For example, when a user checks a checkbox, the screen reader announces the new state.
This happens through property change events fired by the component.

However, in some cases, users need to be notified of changes outside the focused component. Or the event that you want to notify about doesn't fit into existing property change support.

For these scenarios, use [`AccessibleAnnouncerUtil.announce()`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/accessibility/AccessibleAnnouncerUtil.java) to make screen readers announce a specific string.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
if (AccessibleAnnouncerUtil.isAnnouncingAvailable()) {
  AccessibleAnnouncerUtil.announce(mySearchPanel, "No results found", true)
}
```

</tab>
<tab title="Java" group-key="java">

```java
if (AccessibleAnnouncerUtil.isAnnouncingAvailable()) {
  AccessibleAnnouncerUtil.announce(mySearchPanel, "No results found", true);
}
```

</tab>
</tabs>

Common cases for using announcements:

* Error messages or validation results.
* Notifications or popups that are shown but don't receive keyboard focus.
* Background task completion.
* Search or filtering results.

## Tools

### Screen readers

The IntelliJ Platform supports NVDA and JAWS screen readers on Windows, and VoiceOver on macOS.
Test the UI with a screen reader to verify that all functionality is available and that the announced information is correct and complete.

> Learn more about screen reader functionality and commands from their official user guides:
> [NVDA](https://download.nvaccess.org/releases/2025.1.2/documentation/userGuide.html),
> [JAWS](https://support.freedomscientific.com/products/blindness/jawsdocumentation),
> and [VoiceOver](https://support.apple.com/guide/voiceover/welcome/mac).

### UI Inspector

Use the [UI Inspector](internal_ui_inspector.md) to examine accessible properties of UI components.
You can also perform an [accessibility audit](internal_ui_inspector.md#accessibility-checks) to check for common issues and get information on how to resolve them.
