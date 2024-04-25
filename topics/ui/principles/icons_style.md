<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Icons

<link-summary>Guidelines on designing and creating icons.</link-summary>

Follow these guidelines to create a new icon or redesign an existing one for IntelliJ-based products.

> See all icons in the [Icons list](https://jetbrains.design/intellij/resources/icons_list/).
> Use the [IntelliJ Icons Figma plugin](https://www.figma.com/community/plugin/948276470997072333/IntelliJ-Icons)
> to quickly find and use icons in UI mockups.
>
{style="note"}

## Style
A common style unites several icons into a set and makes them recognizable.

Use a flat geometric icon style with straight corners and edges. Flat icons remain legible even in small sizes.

Use flat shapes instead of complex 3D forms:

| Incorrect                                      | Correct                                          |
|------------------------------------------------|--------------------------------------------------|
| ![](../../../images/ui/icons/style_pin_3d.png) | ![](../../../images/ui/icons/style_pin_flat.png) |


Use straight corners and lines instead of curves. It’s recommended to use 45° and 90° angles, or 30° and 60° where possible:

| Incorrect                                            | Correct                                                |
|------------------------------------------------------|--------------------------------------------------------|
| ![](../../../images/ui/icons/style_folder_round.png) | ![](../../../images/ui/icons/style_folder_straigt.png) |


Icons should be as simple as possible without losing their meaning. Clean and simple icons ensure readability and reduce visual clutter.

<table>
<tr>
    <td> Incorrect </td>
    <td> Correct </td>
</tr>
<tr>
    <td> <img src="../../../images/ui/icons/style_soft_wrap_detailed.png"/> </td>
    <td> <img src="../../../images/ui/icons/style_soft_wrap_simple.png"/> </td>
</tr>
<tr>
    <td colspan="2"><em>Reduced the icon to its minimal form, without losing its meaning.</em></td>
</tr>
</table>


## Grid and size
Basically, icons fit into a square artboard. Leave a transparent padding around the perimeter. For default 16px icons, the transparent border is 1px, so the icon’s visible part should be inside the 14px x 14px area.
![](grid.png)
*14px x 14px is the area where the visible part <br/> of the icon should fit for an 16px icon.*

Content should only go beyond the borders if a [modifier](#modifier) is added, or if additional visual weight is required.

Sizes for other icon sizes can be found in the table below:

<table>

<tr><td> Icon Type </td>
<td> Icons Size </td>
<td> Transparent Border </td></tr>
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
    <td> <span style="font-weight: bold"> Default </span>: toolbar icons, project tree, etc. </td>
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
    <td> 32px x 32px <br /> 64px x 64px <br /> 128px x 128px <br /> 256px x 256px <br /> 512px x 512px </td>
    <td> — </td>
</tr>
</table>


## Basic shapes
Use basic shapes to build icons, such as squares, circles and rectangles. Using a grid and basic shapes ensures consistent visual proportions and placement.

| ![](../../../images/ui/icons/shape_square.png) _Square 12px x 12px_                        | ![](../../../images/ui/icons/shape_circle.png) _Circle 14px_                           |
|--------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| ![](../../../images/ui/icons/shape_rect_horizontal.png) _Horizontal rectangle 10px x 14px_ | ![](../../../images/ui/icons/shape_rect_vertical.png) _Vertical rectangle 14px x 10px_ |
{style=none}

Make sure that icons have the same visual weight. The more filled an icon is, the less space it needs. Following this rule, circles, rhombuses, triangles and other non-square shapes should have larger height and width to be optically balanced with square figures.
![](shapes_weight_1.png)
*Make the circle diameter 2px bigger than square icons’ height to compensate the visual weight.*

Icons with more details should occupy more space.
![](shapes_weight_2.png)
*The eye icon is less detailed and occupies less space than a more complex scissors icon.*

Icon weight can often take priority over the basic shape sizing. For more examples on visual weight compensation, see [Optical Effects in User Interfaces](https://medium.muz.li/optical-effects-9fca82b4cd9a).


## Stroke

Use a 2px stroke as the main drawing line. Consistent line weight makes icons cohesive.

<table style="none">
<tr>
    <td width="50%"> <img src="../../../images/ui/icons/stroke_download.png" width="215"/></td>
    <td width="50%"> <img src="../../../images/ui/icons/stroke_calc.png" width="233"/></td>
</tr>
<tr>
    <td><em>Use consistent stroke width.</em></td>
    <td><em>A thinner stroke can be used for subtle tweaks to the&nbsp;legibility of an icon and for optical correction.</em></td>
</tr>
</table>

Align pixels on the X and Y axis and round to the nearest whole pixel, or 0.5px if it’s not possible to align to integer pixels. Aligning icons off the pixel grid will make the icon blurry when it’s rasterized.

> To check what an icon will look like when rasterized use the <ui-path>View | Canvas | Show Pixels on Zoom</ui-path> option <shortcut>Ctrl+P</shortcut> in Sketch,
> <ui-path>View | Pixel Preview</ui-path> <shortcut>Alt+Ctrl+Y</shortcut> in Illustrator.
>
{style="note"}

<table>
<tr>
    <td>Incorrect</td>
    <td>Correct</td>
</tr>
<tr>
    <td width="50%"><img src="../../../images/ui/icons/stroke_pixels_incorrect.png"/>
         <em> If an icon is positioned off pixels, <br /> it becomes blurry when rasterized.</em> </td>
    <td width="50%"><img src="../../../images/ui/icons/stroke_pixels_correct.png"/>
         <em> Position stokes on integer pixels where possible.</em> </td>
</tr>
</table>

Use the 30°, 45° and 60° angles for diagonal lines as they look sharper than diagonals with other angles.

Round shapes, small details and some letters cannot be aligned to the pixel grid. Make sure that the significant pixels have sufficient saturation.

Reduce the number of anchors, as they increase the size of the image and takes longer to process it.

<table>
<tr>
    <td>Incorrect</td>
    <td>Correct</td>
</tr>
<tr>
    <td width="50%"><img src="../../../images/ui/icons/stroke_anchors_incorrect.png"/>
         <em> Unneeded anchors are added.</em> </td>
    <td width="50%"><img src="../../../images/ui/icons/stroke_anchors_correct.png"/>
         <em> Use the minimum number of anchors.</em> </td>
</tr>
</table>


## Modifier
A modifier is a small shape added over the base icon.

The default modifier position is the bottom right corner. If the base shape is badly recognizable with a modifier in the bottom right corner, or if several modifiers are needed, the modifier can be placed in other corners.

  <table style="none">
  <tr>
      <td width="50%"> <img src="../../../images/ui/icons/modifier_search.png"/>
           <em> Place the modifier in the left corner <br/> if the base shape is badly recognizable <br/> with the modifier in the right corner.</em> </td>
      <td width="50%"> <img src="../../../images/ui/icons/modifier_parameter.png"/>
           <em> Place the modifier in the left corner <br/> if there are several modifiers.</em> </td>
  </tr>
  </table>

A modifier size is from 6px to 9px, depending on the shape weight.  Leave a 1-2px spacing between the modifier and the main shape, to make the modifier clearly visible.
![](modifier_size.png)
*Modifier sizes*


## Arrows
General rules for arrows: arrowhead is a filled triangle with a 90° pointing angle, a 2px body ends in a square stroke. An arrow’s orientation is horizontal, vertical, 45° or round.
![](arrows_basic.png)
*Basic arrow icons*

Unfilled arrowhead can be used if there is only one arrow on an icon to reduce excessive visual weight, for example, for navigation arrows, such as back and forward, up and down.


<table style="none">
<tr>
    <td width="50%"> <img src="../../../images/ui/icons/arrow_filled.png"/></td>
    <td width="50%"> <img src="../../../images/ui/icons/arrow_unfilled.png"/></td>
</tr>
<tr>
    <td><em> 90° arrowhead and 2px square stroke body.</em></td>
    <td><em> Back arrow with unfilled arrowhead to compensate visual weight.</em></td>
</tr>
</table>

Do **not** use other arrow styles, for example unfilled arrows or arrows with thinned tails.
<p>Incorrect</p>

![](../../../images/ui/icons/arrows_incorrect.png)


## Color
Do not use color as the only differentiator between icons (except for the [Noun icon](#noun-icons)) because some people do not distinguish colors. Make sure that icons are easily distinguishable by their shape.

Do not use gradients or shadows.

### Action icons

An action icon is an icon that triggers an action. It usually appears on toolbars and in dialogs.
There are 5 colors in the actions icons palette. Dark version is for Darcula theme.

[//]: # (<note>Download a <a href="../../../images/ui/icons/Icons template.sketch" download>Sketch file</a> with the&nbsp;color palette.</note>)

[//]: # (FIXME: colors are wrong, dark are rendered)

<table>
    <tr>
        <td>
            <img src="../../../images/ui/icons/action/red.png" />
            <br/>
            <p><b>Red</b></p><p>#DB5860</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/action/red_dark.png" />
            <br/>
            <p><b>Red dark</b></p><p>#C75450</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/action/yellow.png" />
            <br/>
            <p><b>Yellow</b></p><p>#EDA200</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/action/yellow_dark.png" />
            <br/>
            <p><b>Yellow dark</b></p><p>#F0A732</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/action/green.png" />
            <br/>
            <p><b>Green</b></p><p>#59A869</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/action/green_dark.png" />
            <br/>
            <p><b>Green dark</b></p><p>#499C54</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/action/blue.png" />
            <br/>
            <p><b>Blue</b></p><p>#389FD6</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/action/blue_dark.png" />
            <br/>
            <p><b>Blue dark</b></p><p>#3592C4</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/action/grey.png" />
            <br/>
            <p><b>Grey</b></p><p>#6E6E6E</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/action/grey_dark.png" />
            <br/>
            <p><b>Grey dark</b></p><p>#AFB1B3</p>
        </td>
    </tr>

</table>

Use these colors according to the following rules:
1. By default, action icons are grey and monochromatic:
    ![](colors_actions_grey.png)

2. If an icon falls into one of the categories below, use the corresponding color:

    <table style="none">
        <tr>
            <td width="50%"> <b>Green</b> <p>Positive actions: run, create</p><br /><img src="../../../images/ui/icons/colors_actions_green.png" /> </td>
            <td width="50%"> <b>Red</b> <p>Destructive actions: stop, remove, force</p><br /><img src="../../../images/ui/icons/colors_actions_red.png" /> </td>
        </tr>
        <tr>
            <td> <b>Blue</b> <p>Accent color to highlight popular icons based on usage statistics, highlight small elements in complex icons</p> <br /><img src="../../../images/ui/icons/colors_actions_blue.png" /> </td>
            <td> <b>Yellow</b> <p>Warning actions: attract attention to the places (e.g. code) that can be optimized or improved</p> <br /><img src="../../../images/ui/icons/colors_actions_yellow.png" /> </td>
        </tr>
    </table>


3. Preserve colors in icons combinations. If an icon is colored in its full size, it should have the same color when reduced.
    ![](colors_actions_modifier.png)

4. Use green and red colors for paired icons. Color helps distinguish between similar icons faster.
    ![](colors_actions_pair.png)

5. Use grey color for tool window icons. Icons are placed on the perimeter of the IDE, so they should not attract too much attention and distract users.
    ![](colors_toolbuttons.png)


### Status icons

Color is a great way to provide status information. Use status icons that are already present in the IDE repository:

![](colors_status.png)

If you need a new icon, color it according to its semantics. Make sure that the status icons are easily distinguishable by their form, as many colorblind people find it difficult to distinguish between red and green.


### Noun icons

A noun icon is an icon that appears in the tree view to mark different file types. It helps to locate items in the list faster and does not trigger an action.

The palette for noun icons is wider, because there are lots of icons in one set:

<table style="none">
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/grey.png" />
            <br/>
            <p><b>Grey</b></p><p>#9AA7B0</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/grey_80.png" />
            <br/>
            <p><b>Grey 80%</b></p><p>#9AA7B0 op. 80%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/grey_60.png" />
            <br/>
            <p><b>Grey 60%</b></p><p>#9AA7B0 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/blue.png" />
            <br/>
            <p><b>Blue</b></p><p>#40B6E0</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/blue_70.png" />
            <br/>
            <p><b>Blue 70%</b></p><p>#40B6E0 op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/blue_60.png" />
            <br/>
            <p><b>Blue 60%</b></p><p>#40B6E0 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/green.png" />
            <br/>
            <p><b>Green</b></p><p>#62B543</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/green_70.png" />
            <br/>
            <p><b>Green 70%</b></p><p>#62B543 op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/green_60.png" />
            <br/>
            <p><b>Green 60%</b></p><p>#62B543 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/yellow.png" />
            <br/>
            <p><b>Yellow</b></p><p>#F4AF3D</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/yellow_70.png" />
            <br/>
            <p><b>Yellow 70%</b></p><p>#F4AF3D op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/yellow_60.png" />
            <br/>
            <p><b>Yellow 60%</b></p><p>#F4AF3D op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/purple.png" />
            <br/>
            <p><b>Purple</b></p><p>#B99BF8</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/purple_70.png" />
            <br/>
            <p><b>Purple 70%</b></p><p>#B99BF8 op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/purple_60.png" />
            <br/>
            <p><b>Purple 60%</b></p><p>#B99BF8 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/pink.png" />
            <br/>
            <p><b>Pink</b></p><p>#F98B9E</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/pink_70.png" />
            <br/>
            <p><b>Pink 70%</b></p><p>#F98B9E op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/pink_60.png" />
            <br/>
            <p><b>Pink 60%</b></p><p>#F98B9E op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/red.png" />
            <br/>
            <p><b>Red</b></p><p>#F26522</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/red_70.png" />
            <br/>
            <p><b>Red 70%</b></p><p>#F26522 op. 70%</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/red_60.png" />
            <br/>
            <p><b>Red 60%</b></p><p>#F26522 op. 60%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/red_status.png" />
            <br/>
            <p><b>Red status</b></p><p>#E05555</p>
        </td>
        <td>
            <img src="../../../images/ui/icons/noun/red_status_70.png" />
            <br/>
            <p><b>Red status 70%</b></p><p>#E05555 op. 70%</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/yellow_dark.png" />
            <br/>
            <p><b>Yellow dark</b></p><p>#D9A343</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/green_android.png" />
            <br/>
            <p><b>Green Android</b></p><p>#A4C639</p>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../../../images/ui/icons/noun/black.png" />
            <br/>
            <p><b>Black text</b></p><p>#231F20 op. 70%</p>
        </td>
    </tr>
</table>

Select colors for new icons based on existing colors and users’ habits.

Use colors with 60% opacity and grey 80% for big parts, like folder icons:
![](colors_nouns_folder.png)
Transparent icons are used in both light and dark themes, so in most cases there’s only one version.


Use colors with 70% opacity for medium size elements that occupy about half of an icon, like file type icons:
![](colors_nouns_file.png)


Use colors without opacity for small elements, like modifiers.
![](colors_nouns_modifier.png)

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
