---
title: Loader
category: Controls
type: Subpage
subpageOf: Progress indicators
---

A loader informs users about performing a lengthy operation.

![](loader.png)

## When to use

Follow the rules for [progress indicators](progress_indicators.md).


## How to use

The loader form and sizes are the same in all themes. Use the default 16x16 loader in all cases.

```java
JLabel label = new JLabel("Loading...",
    new AnimatedIcon.Default(), SwingConstants.LEFT);
```


A loader may have a label if the process is long and the loader is shown in an empty area. In this case, use a [progress text](progress_text.md) as the label:
![](loader_with_progress_text.png)

Remove the loader as soon as the process completes.


## Placement

<table style="none">

<tr>
    <td> Inside a field </td>
    <td> <img src="../../../images/ui/loader/placement_field.png"/> </td>
</tr>

<tr>
    <td colspan="2">
    <code-block lang="java">
    ExpandableTextField textField = new ExpandableTextField();
    textField.addExtension(Extension.create(new AnimatedIcon.Default(), null, null));
    </code-block>
    </td>
</tr>

<tr>
    <td>In a corner</td>
    <td> <img src="../../../images/ui/loader/placement_corner.png" />
    </td>
</tr>

<tr>
    <td>  Next to an item </td>
    <td> <img src="../../../images/ui/loader/placement_item.png" /> </td>
</tr>

<tr>
    <td> Before a progress text </td>
    <td> <img src="../../../images/ui/loader/placement_progress_text.png" />
    </td>
</tr>

</table>




