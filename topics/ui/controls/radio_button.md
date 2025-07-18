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

### Several options can be selected
Use a group of [checkboxes](checkbox.md) when several options can be selected.

### Yes or No Options
For only two opposing yes/no options, use a checkbox.

<table style="none" border="false">
  <tr>
    <td width="378px">
      <format color="Green" style="bold">Correct</format><img src="02_When_to_use_correct.png" alt="A correct example of using a checkbox instead of radiobuttons"/>
    </td>
    <td width="378px">
      <format color="Red" style="bold">Incorrect</format><img src="02_When_to_use_incorrect.png" alt="An incorrect example of using radiobuttons instead of a checkbox"/>
    </td>
  </tr>
</table>

### 2-5 options with short labels
If the options have short labels, use a segmented button.

![](03_When_to_use_Segmented_button.png){width=706}


## When to use a drop-down instead
Consider using a [drop-down list](drop_down.md) if:
* There are more than 5 options or the options have long labels:
<table style="none" border="false">
  <tr>
    <td width="378">
      <format color="Green" style="bold">Correct</format><img src="04_When_to_use_correct.png" alt="A correct example of using a drop-down list instead of radiobuttons"/>
    </td>
    <td width="378">
      <format color="Red" style="bold">Incorrect</format><img src="04_When_to_use_incorrect.png" alt="An incorrect example of using radiobuttons for a list of 5 or more options"/>
    </td>
  </tr>
  </table>

* The screen space is limited.
* The option might be used less often than other options on the screen.
* There are other drop-down lists in the same group of UI components. A radio button group is more noticeable than a drop-down list, so it will look like a more important setting.
* The setting combines several UI components into one control:
  ![](05_When_to_use_Segmented_button.png){width=706}

## How to use

### Label

<table style="none" border="false" column-width="fixed">
    <tr>
      <td><img src="06_How_to_use.png" alt="Label example" width="378"/></td>
      <td><p>A label accompanies each radio button and is placed next to it.</p></td>
    </tr>
    <tr>
      <td><img src="07_How_to_use.png" alt="An example of a label spanning two lines." width="378"/></td>
      <td><p>If a label is long, split it into two lines. Avoid labels that take more than two lines. See recommendations on writing concise labels below.</p></td>
    </tr>
</table>

<chapter title="Implementation" collapsible="true">
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
</chapter>

### Writing guidelines

* Use sentence-style capitalization.
* Do not use ending punctuation.
* Use the imperative form of verbs.
* Do not use negation in labels as it complicates understanding.
* Make labels short and intelligible — see [Writing short and clear text](writing_short.md).

<format color="369650" style="bold">Correct</format>
![](08_How_to_use_correct.png){width=706}

 <format color="E55765" style="bold">Incorrect</format>
![](08_How_to_use_incorrect.png){width=706}


### Group label
Always start a radio button group with a group label. It explains what the options are for.
Use a colon at the end of a group label.
![](10_How_to_use.png){width=706}

Use a checkbox or another radio button as a group label if the radio button group needs to be turned on or off.
![](09_How_to_use.png){width=706}


## Sizes and placement

Follow the layout of [checkboxes and radio-buttons](layout.md)

