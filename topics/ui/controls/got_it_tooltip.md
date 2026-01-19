<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Got It Tooltip

<link-summary>UI guidelines on using "Got it" tooltips.</link-summary>

<tldr>

**Implementation:** [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt)

**Related:** [](notifications.md#gotIt)

</tldr>

A Got It tooltip teaches users about a new or changed behavior and gives information about it.

![](got_it.png){width=706}

## Structure

![](got_it_structure.png){width=706}

### Body text

Always add the body text. The text should be short and describe what the tooltip is showing.

Don't use body text as a title.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_body_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_body_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

### Title

Add a header if the body text is 2 lines and more. A header should be short and quickly explain what a tooltip is about.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_title_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_title_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

<chapter title="Implementation" collapsible="true">

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(TOOLTIP_ID, GET_IT_TEXT, parentDisposable)
    .withHeader("The reader mode is on")
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(TOOLTIP_ID, GET_IT_TEXT, parentDisposable)
    .withHeader("The reader mode is on");
```

</tab>
</tabs>

</chapter>

### Step counter

Use a step counter to numerate a group of sequential Got It tooltips.

<note>Only group tooltips that show related content.</note>

![](got_it_steps.png){width=706}

### Shortcut

Use a shortcut if the tooltip describes an action that has a shortcut.

![](got_it_shortcut.png){width=706}

<chapter title="Implementation" collapsible="true">

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(
    TOOLTIP_ID,
    { "You can rename usages ${shortcut("My.Action")}" },
    parentDisposable
)
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(
    TOOLTIP_ID,
    gotItTextBuilder -> {
        String shortcut = gotItTextBuilder.shortcut("My.Action");
        return "You can rename usages " + shortcut;
    },
    parentDisposable
);
```

</tab>
</tabs>

</chapter>

### Link

Add a link if users might want to revert changes in a feature or configure it.

<note>Only one link can be added</note>

![](got_it_link.png){width=706}

<chapter title="Implementation" collapsible="true">

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(TOOLTIP_ID, TOOLTIP_TEXT, parentDisposable)
    .withLink("Disable for all files", this::actionMethodReference)
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(TOOLTIP_ID, TOOLTIP_TEXT, parentDisposable)
    .withLink("Disable for all files", this::actionMethodReference);
```

</tab>
</tabs>

</chapter>

Add an external link if there is a help source that can further explain the functionality.

![](got_it_link_ext.png){width=706}

<chapter title="Implementation" collapsible="true">

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .withBrowserLink("How to use", new URL("https://example.com"))
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .withBrowserLink("How to use", new URL("https://example.com"));
```

</tab>
</tabs>

</chapter>

## When to use

### New feature

Teach users about a new feature contextually when they start working with a tool. Describe the behavior and what is the benefit for users.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_feature_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_feature_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

### Changed behavior

Suggest a new pattern to a user's task that improves their workflow.

### Ambiguous behavior

Explain behavior that is not clear from the UI.

For example, when extracting a method, users can change only the method name in the blue box. The tooltip explains where to change the order of parameters in the parentheses and other properties of a method:

![](got_it_ambiguous.png){width=706}

## When not to use

### Presenting a new tool

Don't use the Got It tooltip as a marketing tool. It distracts users, especially on a startup. Use marketing channels for this purpose.

### Nothing to point to

When there is nothing to point to in a dialog or a tool window, use a [banner](banner.md).

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_banner_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_banner_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

### Feedback from the interface

Don't use Got It tooltips to give users a feedback from the interface. Use [notifications](balloon.md) instead.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_feedback_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_feedback_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

### New option in a tree or list

When there is a new option in a tree or a list of choices that users see regularly, use a badge instead.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_tree_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_tree_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

## How to use

### Small UI controls

Point to a small and important UI control that can be missed among other information on the screen.

### Showing a group of tooltips

Show a sequential group of tooltips only when users initiate it. Users can launch the show, for example, by pressing a respective button in the interface.

### Text length and formatting

* Show no more than 5 lines of body text. If the text does not fit, leave only the essential information and add a link to a help article. Make the help text [short and descriptive](writing_short.md).

* Use sentence case both for the header and body text, and follow the [punctuation rules](punctuation.md).

* Don't use style formatting. It makes the tooltip harder to read.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_content_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_content_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

### Positioning

Don't cover the information the user is currently working with.

<table style="none" border="false">
  <tr>
    <td width="50%">
      <format color="Green" style="bold">Correct</format><img src="got_it_position_correct.png" alt=""/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="got_it_position_incorrect.png" alt=""/>
    </td>
  </tr>
</table>

<chapter title="Implementation" collapsible="true">

See four predefined point providers in the [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt) class.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .show(gutterComponent, GotItTooltip.TOP_MIDDLE)
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .show(gutterComponent, GotItTooltip.TOP_MIDDLE);
```

</tab>
</tabs>

</chapter>

### Timeout for hiding

Consider adding a timeout if:

* The text is no longer than 10 words.
* The tooltip appears at the place at which the user is currently looking.
* There is no link in the tooltip.

For example, the Got It tooltip has a timeout because the text is short, the user has just started the Rename refactoring, and is very likely looking at this place:

![](got_it_short.png){width=706}

<chapter title="Implementation" collapsible="true">

Default timeout duration is 5 seconds. A custom duration can be set:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .withTimeout(3000)
```

</tab>
<tab title="Java" group-key="java">

```java
new GotItTooltip(TOOLTIP_ID, GOT_IT_TEXT, parentDisposable)
    .withTimeout(3000);
```

</tab>
</tabs>

</chapter>

### Versioning

<!--If a tooltip appears automatically after the IDE starts, tie it to the IDE version. Due to the technical limitations, tooltip counters might be reset when the IDE version is updated, and the users might see the same tooltips again.-->

If a tooltip is triggered by an action or plugin installation, do not tie them to the current IDE version. In this case, users might miss a tooltip if they are using this functionality or plugin for the first time in the next IDE version.

## Built-in behavior

### Frequency

By default, a tooltip is shown only once per user.

### Closable

The tooltip disappears when:

* <shortcut>Esc</shortcut> is pressed
* User clicks any place outside the tooltip
* On a timeout

### Multiple Got It tooltips

If several tooltips appear on application start, they are shown one by one.

### Text width

 The tooltip width adjusts automatically to make the right margin 16 px. Text width is 280 px by default.
