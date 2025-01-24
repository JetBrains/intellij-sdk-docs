<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Radio Button

<link-summary>UI guidelines on using radio buttons.</link-summary>

<tldr>

**Implementation:** [`JBRadioButton`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBRadioButton.java)

</tldr>

![](01_Radio Button.png){width=706}

## When to use

Use a radio button group to choose one option from 2 to 4 mutually exclusive options.

## When not to use

### Several options in a group can be selected
Use a group of [checkboxes](checkbox.topic) instead.

### Binary Yes/No Options
For only two opposing yes/no options, use a checkbox instead.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="02_When_to_use_correct.png" alt="A correct example of using a checkbox instead of a radiobutton"/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="02_When_to_use_incorrect.png" alt="An incorrect example of using a radiobutton instead of a checkbox"/>
    </td>
  </tr>
</table>

### 5 and more options
If the options have short labels, use a segmented button. Or, if the options can be represented on an axis, such as a time delay, use a slider instead.

![](03_When_to_use_Segmented_button.png){width=706}

For other cases with 5 or more options use a [drop-down list](drop_down.md):

  <table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="04_When_to_use_correct.png" alt="A correct example of using a checkbox instead of a radiobutton"/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="04_When_to_use_incorrect.png" alt="An incorrect example of using a radiobutton instead of a checkbox"/>
    </td>
  </tr>
</table>

### Use dropdown
Consider using a [drop-down list](drop_down.md) if:
* The screen space is limited.
* The option might be used less often than other options on the screen.
* There are other drop-down lists in the same group of UI components. A radio button group is more noticeable than a drop-down list, so it will look like a more important setting.
* There is a combination of several UI components for one setting:
  ![](05_When_to_use_Segmented_button.png){width=706}
  *The automatic updates setting consists of a checkbox, three lengthy-labeled options in a dropdown and a button.*

## How to use

### Label

<table style="none" border="false" column-width="fixed">
    <tr>
      <td><img src="06_How_to_use.png" alt="Using links instead of buttons" width="378"/></td>
      <td><p>A label accompanies each checkbox and is placed next to it.</p></td>
    </tr>
    <tr>
      <td><img src="07_How_to_use.png" alt="Using links instead of buttons" width="378"/></td>
      <td><p>If a label is long, split it into two lines. Avoid labels that take more than two lines. See recommendations on writing concise labels below.</p></td>
    </tr>
</table>

To implement this, use HTML formatting:
<tabs group="languages">
<tab title="Kotlin UI DSL" group-key="kotlin">

```kotlin
radioButton(
    "<html>Show options before adding<br>to version control</html>")
```

</tab>
<tab title="Java" group-key="java">

```java
new JRadioButton(
    "<html>Show options before adding<br>to version control</html>");
```

</tab>
</tabs>

### Writing guidelines

Use sentence-style capitalization.

Do not use ending punctuation.

Use the imperative form of verbs.

Do not use negation in labels as it complicates understanding.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="08_How_to_use_correct.png" alt="A correct example of using a checkbox instead of a radiobutton"/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="08_How_to_use_incorrect.png" alt="An incorrect example of using a radiobutton instead of a checkbox"/>
    </td>
  </tr>
</table>

Make labels short and intelligible — see [Writing short and clear text](writing_short.md).

### Group label

![](09_How_to_use.png){width=706}

Always start a radio button group with a group label. It explains what the options are for.

Use a checkbox or another radio button as a group label if the radio button group needs to be turned on or off.

Use a colon at the end of a group label.

## Sizes and placement

If a radio button group depends on another control, e.g., a checkbox, follow the rules for [dependent colors](layout.md#dependent-controls).
Otherwise, follow the rules for [independent colors](layout.md).

