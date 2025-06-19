<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Loader

<link-summary>UI guidelines on using loaders.</link-summary>

<tldr>

**Implementation:** [`AnimatedIcon.Default`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java)

**Related:** [](icons.md#animated-icons)

</tldr>

A loader informs users about performing a lengthy operation.

![](loader.png){width=330}

## When to use

Follow the rules for [progress indicators](progress_indicators.md).

## How to use

The loader form and sizes are the same in all themes. Use the default 16&times;16 loader in all cases.
<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
JLabel(
    "Loading...",
    AnimatedIcon.Default(),
    SwingConstants.LEFT
)
```

</tab>
<tab title="Java" group-key="java">

```java
new JLabel(
    "Loading...",
    new AnimatedIcon.Default(),
    SwingConstants.LEFT
);
```

</tab>
</tabs>



A loader may have a label if the process is long and the loader is shown in an empty area. In this case, use a [progress text](progress_text.md) as the label:

![](loader_with_progress_text.png){width=597}

Remove the loader as soon as the process completes.

## Placement

<table style="none">

<tr>
    <td> Inside a field </td>
    <td> <img src="placement_field.png" width="161"/> </td>
</tr>

<tr>
    <td colspan="2">
        <code-block lang="java">
            ExpandableTextField textField = new ExpandableTextField();
            textField.addExtension(
              Extension.create(new AnimatedIcon.Default(), null, null)
            );
        </code-block>
    </td>
</tr>

<tr>
    <td>In a corner</td>
    <td> <img src="placement_corner.png" width="32" />
    </td>
</tr>

<tr>
    <td>  Next to an item </td>
    <td> <img src="placement_item.png" width="55" /> </td>
</tr>

<tr>
    <td> Before a progress text </td>
    <td> <img src="placement_progress_text.png" width="110" />
    </td>
</tr>

</table>
