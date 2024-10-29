<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Icons

<link-summary>Guidelines on designing and creating icons.</link-summary>

Follow these guidelines to create a new icon or redesign an existing one for IntelliJ-based products.

> See all icons in the [Icons list](https://intellij-icons.jetbrains.design).
> Use the [IntelliJ Icons Figma plugin](UI_kit.md)
> to quickly find and use icons in UI mockups.
>
{style="note"}

## Style

A common style unites several icons into a set and makes them recognizable.

Use a flat geometric icon style with straight corners and edges. Flat icons remain legible even in small sizes.

Use flat shapes instead of complex 3D forms:

| <format color="Red" style="bold">Incorrect</format> | <format color="Green" style="bold">Correct</format> |
|-----------------------------------------------------|-----------------------------------------------------|
| ![](style_pin_3d.png){width="190"}                  | ![](style_pin_flat.png){width="190"}                |

Use straight corners and lines instead of curves. It’s recommended to use 45° and 90° angles, or 30° and 60° where possible:

| <format color="Red" style="bold">Incorrect</format> | <format color="Green" style="bold">Correct</format> |
|-----------------------------------------------------|-----------------------------------------------------|
| ![](style_folder_round.png){width="144"}            | ![](style_folder_straigt.png){width="144"}          |

Icons should be as simple as possible without losing their meaning. Clean and simple icons ensure readability and reduce visual clutter.

<table>
<tr>
    <td> <format color="Red" style="bold">Incorrect</format> </td>
    <td> <format color="Green" style="bold">Correct</format> </td>
</tr>
<tr>
    <td> <img src="style_soft_wrap_detailed.png" width="160"/> </td>
    <td> <img src="style_soft_wrap_simple.png" width="160"/> </td>
</tr>
<tr>
    <td colspan="2"><em>Reduced the icon to its minimal form, without losing its meaning.</em></td>
</tr>
</table>

## Grid and size

Basically, icons fit into a square artboard. Leave a transparent padding around the perimeter. For default 16px icons, the transparent border is 1px, so the icon’s visible part should be inside the 14px x 14px area.

![](grid.png){width=251 style=block}
*14px x 14px is the area where the visible part of the icon should fit for a 16px icon.*

Content should only go beyond the borders if a [modifier](#modifier) is added, or if additional visual weight is required.

Sizes for other icon sizes can be found in the table below:

<table>

<tr>
<td width="50%"> Icon Type </td>
<td width="25%"> Icons Size </td>
<td width="25%"> Transparent Border </td></tr>
<tr>
    <td> Gutter, status bar </td>
    <td> 12px x 12px </td>
    <td> — </td>
</tr>
<tr>
    <td> Tool window </td>
    <td> 13px x 13px </td>
    <td> — </td>
</tr>
<tr>
    <td> <b>Default:</b> toolbar icons, project tree, etc. </td>
    <td> 16px x 16px </td>
    <td> 1px, except for modifier </td>
</tr>
<tr>
    <td> Dialogs </td>
    <td> 32px x 32px </td>
    <td> 2px </td>
</tr>
<tr>
    <td> Logo app icon </td>
    <td>
         <p>32px x 32px</p>
         <p>64px x 64px</p>
         <p>128px x 128px</p>
         <p>256px x 256px</p>
         <p>512px x 512px</p>
    </td>
    <td> — </td>
</tr>
</table>

## Basic shapes

Use basic shapes to build icons, such as squares, circles and rectangles. Using a grid and basic shapes ensures consistent visual proportions and placement.

<table style="none">
    <tr>
        <td width="50%"><img src="shape_square.png" alt="" width="192" /> <emphasis>Square 12px x 12px</emphasis></td>
        <td width="50%"><img src="shape_circle.png" alt="" width="195" /> <emphasis>Circle 14px</emphasis></td>
    </tr>
    <tr>
        <td><img src="shape_rect_horizontal.png" alt="" width="192" /> <emphasis>Horizontal rectangle 10px x 14px</emphasis></td>
        <td><img src="shape_rect_vertical.png" alt="" width="192" /> <emphasis>Vertical rectangle 14px x 10px</emphasis></td>
    </tr>
</table>

Make sure that icons have the same visual weight. The more filled an icon is, the less space it needs. Following this rule, circles, rhombuses, triangles and other non-square shapes should have larger height and width to be optically balanced with square figures.

![](shapes_weight_1.png){width=542 style=block}
*Make the circle diameter 2px bigger than square icons’ height to compensate for the visual weight.*

Icons with more details should occupy more space.

![](shapes_weight_2.png){width=542 style=block}
*The eye icon is less detailed and occupies less space than a more complex scissors icon.*

Icon weight can often take priority over the basic shape sizing. For more examples on visual weight compensation, see [Optical Effects in User Interfaces](https://medium.muz.li/optical-effects-9fca82b4cd9a).

## Stroke

Use a 2px stroke as the main drawing line. Consistent line weight makes icons cohesive.

<table style="none">
<tr>
    <td width="50%"> <img src="stroke_download.png" width="215"/></td>
    <td width="50%"> <img src="stroke_calc.png" width="233"/></td>
</tr>
<tr>
    <td><em>Use consistent stroke width.</em></td>
    <td><em>A thinner stroke can be used for subtle tweaks to the legibility of an icon and for optical correction.</em></td>
</tr>
</table>

Align pixels on the X and Y axis and round to the nearest whole pixel, or 0.5px if it’s not possible to align to integer pixels. Aligning icons off the pixel grid will make the icon blurry when it’s rasterized.

> To check what an icon will look like when rasterized use the <ui-path>View | Canvas | Show Pixels on Zoom</ui-path> option <shortcut>Ctrl+P</shortcut> in Sketch,
> <ui-path>View | Pixel Preview</ui-path> <shortcut>Alt+Ctrl+Y</shortcut> in Illustrator.
>
{style="note"}

<table>
<tr>
    <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
    <td width="50%"><format color="Green" style="bold">Correct</format></td>
</tr>
<tr>
    <td><img src="stroke_pixels_incorrect.png" width="192"/>
         <em> If an icon is positioned off pixels, <br /> it becomes blurry when rasterized.</em> </td>
    <td><img src="stroke_pixels_correct.png" width="192"/>
         <em> Position stokes on integer pixels where possible.</em> </td>
</tr>
</table>

Use the 30°, 45° and 60° angles for diagonal lines as they look sharper than diagonals with other angles.

Round shapes, small details and some letters cannot be aligned to the pixel grid. Make sure that the significant pixels have sufficient saturation.

Reduce the number of anchors as they increase the size of the image and takes longer to process it.

<table>
<tr>
    <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
    <td width="50%"><format color="Green" style="bold">Correct</format></td>
</tr>
<tr>
    <td><img src="stroke_anchors_incorrect.png" width="192"/>
         <em> Unneeded anchors are added.</em> </td>
    <td><img src="stroke_anchors_correct.png" width="192"/>
         <em> Use the minimum number of anchors.</em> </td>
</tr>
</table>

## Modifier

A modifier is a small shape added over the base icon.

The default modifier position is the bottom right corner. If the base shape is badly recognizable with a modifier in the bottom right corner, or if several modifiers are needed, the modifier can be placed in other corners.

  <table style="none">
  <tr>
      <td width="50%"> <img src="modifier_search.png" width="192"/>
           <em> Place the modifier in the left corner <br/> if the base shape is badly recognizable <br/> with the modifier in the right corner.</em> </td>
      <td width="50%"> <img src="modifier_parameter.png" width="192"/>
           <em> Place the modifier in the left corner <br/> if there are several modifiers.</em> </td>
  </tr>
  </table>

A modifier size is from 6px to 9px, depending on the shape's weight.
Leave a 1-2px spacing between the modifier and the main shape, to make the modifier clearly visible.

![](modifier_size.png){width=577 style=block}
*Modifier sizes*

## Arrows

General rules for arrows: arrowhead is a filled triangle with a 90° pointing angle, a 2px body ends in a square stroke. An arrow’s orientation is horizontal, vertical, 45° or round.

![](arrows_basic.png){width=515 style=block}
*Basic arrow icons*

Unfilled arrowhead can be used if there is only one arrow on an icon to reduce excessive visual weight, for example, for navigation arrows, such as back and forward, up and down.

<table style="none">
<tr>
    <td width="50%"> <img src="arrow_filled.png" width="144"/></td>
    <td width="50%"> <img src="arrow_unfilled.png" width="192"/></td>
</tr>
<tr>
    <td><em> 90° arrowhead and 2px square stroke body.</em></td>
    <td><em> Back arrow with unfilled arrowhead to compensate visual weight.</em></td>
</tr>
</table>

Do **not** use other arrow styles, for example, unfilled arrows or arrows with thinned tails.

<format color="Red" style="bold">Incorrect</format>

![](arrows_incorrect.png){width=510}

## Color

Do not use color as the only differentiator between icons (except for the [Noun icon](#noun-icons)) because some people do not distinguish colors. Make sure that icons are easily distinguishable by their shape.

Do not use gradients or shadows.

### Action icons

An action icon is an icon that triggers an action. It usually appears on toolbars and in dialogs.
There are 5 colors in the action icons palette.
The dark version is for Darcula theme.

[//]: # (<note>Download a <a href="../../../images/ui/icons/Icons template.sketch" download>Sketch file</a> with the color palette.</note>)

<table style="none">
    <tr>
        <td width="50%">
            <img src="action_red.png" width="64" />
            <br/>
            <p><b>Red</b></p><p>#DB5860</p>
        </td>
        <td width="50%">
            <img src="action_red_d.png" width="64" />
            <br/>
            <p><b>Red dark</b></p><p>#C75450</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="action_yellow.png" width="64" />
            <br/>
            <p><b>Yellow</b></p><p>#EDA200</p>
        </td>
        <td>
            <img src="action_yellow_d.png" width="64" />
            <br/>
            <p><b>Yellow dark</b></p><p>#F0A732</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="action_green.png" width="64" />
            <br/>
            <p><b>Green</b></p><p>#59A869</p>
        </td>
        <td>
            <img src="action_green_d.png" width="64" />
            <br/>
            <p><b>Green dark</b></p><p>#499C54</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="action_blue.png" width="64" />
            <br/>
            <p><b>Blue</b></p><p>#389FD6</p>
        </td>
        <td>
            <img src="action_blue_d.png" width="64" />
            <br/>
            <p><b>Blue dark</b></p><p>#3592C4</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="action_grey.png" width="64" />
            <br/>
            <p><b>Grey</b></p><p>#6E6E6E</p>
        </td>
        <td>
            <img src="action_grey_d.png" width="64" />
            <br/>
            <p><b>Grey dark</b></p><p>#AFB1B3</p>
        </td>
    </tr>

</table>

Use these colors according to the following rules:

1. By default, action icons are grey and monochromatic:

   ![](colors_actions_grey.png){width=319}

2. If an icon falls into one of the categories below, use the corresponding color:

    <table style="none">
        <tr>
            <td width="50%"> <b>Green</b> <p>Positive actions: run, create</p><br /><img src="colors_actions_green.png" /> </td>
            <td width="50%"> <b>Red</b> <p>Destructive actions: stop, remove, force</p><br /><img src="colors_actions_red.png" /> </td>
        </tr>
        <tr>
            <td> <b>Blue</b> <p>Accent color to highlight popular icons based on usage statistics, highlight small elements in complex icons</p> <br /><img src="colors_actions_blue.png" /> </td>
            <td> <b>Yellow</b> <p>Warning actions: attract attention to the places (e.g., code) that can be optimized or improved</p> <br /><img src="colors_actions_yellow.png" /> </td>
        </tr>
    </table>

3. Preserve colors in icon combinations. If an icon is colored in its full size, it should have the same color when reduced.

   ![](colors_actions_modifier.png){width=248}

4. Use green and red colors for paired icons. Color helps distinguish between similar icons faster.

   ![](colors_actions_pair.png){width=233}

5. Use grey color for tool window icons. Icons are placed on the perimeter of the IDE, so they should not attract too much attention and distract users.

   ![](colors_toolbuttons.png){width=750}

### Status icons

Color is a great way to provide status information. Use status icons that are already present in the IDE repository:

![](colors_status.png){width=308}

If you need a new icon, color it according to its semantics. Make sure that the status icons are easily distinguishable by their form, as many colorblind people find it difficult to distinguish between red and green.

### Noun icons

A noun icon is an icon that appears in the tree view to mark different file types. It helps to locate items in the list faster and does not trigger an action.

The palette for noun icons is wider, because there are lots of icons in one set:

<table style="none">
    <tr>
        <td width="33%">
            <img src="noun_grey.png" width="64" />
            <br/>
            <p><b>Grey</b></p><p>#9AA7B0</p>
        </td>
        <td width="33%">
            <img src="noun_grey_80.png" width="64" />
            <br/>
            <p><b>Grey 80%</b></p><p>#9AA7B0 op. 80%</p>
        </td>
        <td width="33%">
            <img src="noun_grey_60.png" width="64" />
            <br/>
            <p><b>Grey 60%</b></p><p>#9AA7B0 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_blue.png" width="64" />
            <br/>
            <p><b>Blue</b></p><p>#40B6E0</p>
        </td>
        <td>
            <img src="noun_blue_70.png" width="64" />
            <br/>
            <p><b>Blue 70%</b></p><p>#40B6E0 op. 70%</p>
        </td>
        <td>
            <img src="noun_blue_60.png" width="64" />
            <br/>
            <p><b>Blue 60%</b></p><p>#40B6E0 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_green.png" width="64" />
            <br/>
            <p><b>Green</b></p><p>#62B543</p>
        </td>
        <td>
            <img src="noun_green_70.png" width="64" />
            <br/>
            <p><b>Green 70%</b></p><p>#62B543 op. 70%</p>
        </td>
        <td>
            <img src="noun_green_60.png" width="64" />
            <br/>
            <p><b>Green 60%</b></p><p>#62B543 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_yellow.png" width="64" />
            <br/>
            <p><b>Yellow</b></p><p>#F4AF3D</p>
        </td>
        <td>
            <img src="noun_yellow_70.png" width="64" />
            <br/>
            <p><b>Yellow 70%</b></p><p>#F4AF3D op. 70%</p>
        </td>
        <td>
            <img src="noun_yellow_60.png" width="64" />
            <br/>
            <p><b>Yellow 60%</b></p><p>#F4AF3D op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_purple.png" width="64" />
            <br/>
            <p><b>Purple</b></p><p>#B99BF8</p>
        </td>
        <td>
            <img src="noun_purple_70.png" width="64" />
            <br/>
            <p><b>Purple 70%</b></p><p>#B99BF8 op. 70%</p>
        </td>
        <td>
            <img src="noun_purple_60.png" width="64" />
            <br/>
            <p><b>Purple 60%</b></p><p>#B99BF8 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_pink.png" width="64" />
            <br/>
            <p><b>Pink</b></p><p>#F98B9E</p>
        </td>
        <td>
            <img src="noun_pink_70.png" width="64" />
            <br/>
            <p><b>Pink 70%</b></p><p>#F98B9E op. 70%</p>
        </td>
        <td>
            <img src="noun_pink_60.png" width="64" />
            <br/>
            <p><b>Pink 60%</b></p><p>#F98B9E op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_red.png" width="64" />
            <br/>
            <p><b>Red</b></p><p>#F26522</p>
        </td>
        <td>
            <img src="noun_red_70.png" width="64" />
            <br/>
            <p><b>Red 70%</b></p><p>#F26522 op. 70%</p>
        </td>
        <td>
            <img src="noun_red_60.png" width="64" />
            <br/>
            <p><b>Red 60%</b></p><p>#F26522 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_red_status.png" width="64" />
            <br/>
            <p><b>Red status</b></p><p>#E05555</p>
        </td>
        <td>
            <img src="noun_red_status_70.png" width="64" />
            <br/>
            <p><b>Red status 70%</b></p><p>#E05555 op. 70%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_yellow_d.png" width="64" />
            <br/>
            <p><b>Yellow dark</b></p><p>#D9A343</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_green_android.png" width="64" />
            <br/>
            <p><b>Green Android</b></p><p>#A4C639</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="noun_black.png" width="64" />
            <br/>
            <p><b>Black text</b></p><p>#231F20 op. 70%</p>
        </td>
    </tr>
</table>

Select colors for new icons based on existing colors and users’ habits.

Use colors with 60% opacity and grey 80% for big parts, like folder icons:

![](colors_nouns_folder.png){width=86}

Transparent icons are used in both light and dark themes, so in most cases there’s only one version.

Use colors with 70% opacity for medium size elements that occupy about half of an icon, like file type icons:

![](colors_nouns_file.png){width=111}

Use colors without opacity for small elements, like modifiers.

![](colors_nouns_modifier.png){width=87}

## Font

To make a new icon with a letter, reuse letters from existing icons. If there's no suitable letter in existing icons, use a common sans-serif font like Arial or Open Sans.

## Export Icons

> Install the [Svgo-compressor plugin](https://www.sketchapp.com/extensions/plugins/svgo-compressor/) in Sketch to optimize icons before save.
>
{style="note"}

Use the SVG format for icons files. Use camelCase capitalization for icon names:

<path>iconName.svg</path>

If an icon is not the same in the dark theme, then also create:

<path>iconName_dark.svg</path>
