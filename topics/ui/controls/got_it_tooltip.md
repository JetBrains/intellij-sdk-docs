<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Got It Tooltip

<link-summary>UI guidelines on using "Got it" tooltips.</link-summary>

<tldr>

**Implementation:** [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt)

**Related:** [](notifications.md#gotIt)

</tldr>

A Got It tooltip informs users about a new or changed behavior and gives basic information about it.

![](01_got_it_example.png){width=335}

## Structure

TODO: Add image

### Body text

Always add the body text.

### Title

Add a header if the body text is 2 lines and more. A short header can quickly explain what this tooltip is about.

![](10_header.png){width=342}

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

### Counter

Shows progression

### Shortcut

Add a shortcut if the tooltip describes a single action that has a shortcut.

![](11_shortcut.png){width=248}

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

![](12_link_action.png){width=389}

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

![](13_link_help.png){width=340}

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

## When to usee

### New feature

Something about presenting a new feature that is not very vital for users but still can change the way they do their routine tasks in an IDE

Should be used rarely because it distracts users — define when

### Changed behavior

Suggest a new pattern to a user's task that improves their workflow

### Ambiguos behavior

Explain behavior that is not clear from the UI.

![](04_explain_how_to_use.png){width=333 style=block}
*When extracting a method, users can change only the method name in the blue box. The tooltip explains where to change the order of parameters in the parentheses and other properties of a method.*

### Contextual

## When not to use

### Nothing to point to

Use a [banner](banner.md) in a dialog or a [notification](balloon.md) in the main window.

![](07_got_it_banner.png){width=709}

![](08_got_it_notification.png){width=397}

## How to use

### Small UI controls

Point to small UI controls that can be missed among other information in the screen.

![](02_new_feature.png){width=509 style=block}
*After method or parameter names are edited, the Inplace Refactoring icon appears.*

![](03_new_plugin.png){width=375 style=block}
*After the "Code With Me" plugin is installed, a drop-down menu appears on the toolbar.*

### Shortcuts

Suggest keyboard interactions.

![](05_suggest_keyboard_actions.png){width=244 style=block}
*The inlay with options after the blue box is a new control. The tooltip explains how to use it with the keyboard.*

![](09_required_and_optional_information.png){width=527}

### Text length and formatting

Show no more than 5 lines of body text. If the text does not fit, leave only the essential information and add a link to a help article.

Use sentence case both for the header and body text, and follow the [punctuation rules](punctuation.md).

Make the help text [short and descriptive](writing_short.md).

Avoid using style formatting. It makes the tooltip harder to read.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="14_formatting_incorrect.png" alt="" width="341" /></td>
        <td><img src="14_formatting_correct.png" alt="" width="341" /></td>
    </tr>
</table>

### Positioning

Do **not** cover the information the user is currently working with.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="15_location_incorrect.png" alt="" width="509" /></td>
        <td><img src="15_location_correct.png" alt="" width="509" /></td>
    </tr>
</table>

**Implementation:** See four predefined point providers in the [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt) class.

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

### Timeout

Consider adding a timeout if:

* The text is no longer than 10 words.
* The tooltip appears at the place at which the user is currently looking.
* There is no link in the tooltip.

![](05_suggest_keyboard_actions.png){width=244}

*The Got It tooltip has a timeout because the text is short, the user has just started the Rename refactoring, and is very likely looking at this place.*

Note that adding a timeout automatically hides the Got It button.<br/><br/>

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

If a tooltip appears automatically after the IDE starts, tie it to the IDE version. Due to the technical limitations, tooltip counters might be reset when the IDE version is updated, and the users might see the same tooltips again.

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

Text width is 280px by default. The tooltip width adjusts automatically to make the right margin 16px.

![](16_width_custom.png){width=681}

![](17_width_adjusted.png){width=625}
