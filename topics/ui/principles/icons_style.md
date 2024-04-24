<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Icons

Follow these guidelines to create a new icon or redesign an existing one for IntelliJ-based products.

> See all icons in the [Icons list](https://jetbrains.design/intellij/resources/icons_list/).
> Use the [IntelliJ Icons Figma plugin](https://www.figma.com/community/plugin/948276470997072333/IntelliJ-Icons)
> to quickly find and use icons in UI mockups.
>
{style="note"}

## Style
A common style unites several icons into a set and makes them recognizable.

Use a flat geometric icon style with straight corners and edges. Flat icons remain legible even in small sizes.

<table style="none">
<tr>
    <td> <p>Incorrect</p> <br /><img src="../../../images/ui/icons/style_pin_3d.png"/> </td>
    <td> <p>Correct</p> <br /><img src="../../../images/ui/icons/style_pin_flat.png"/> </td>
</tr>
<tr>
    <td colspan="2">
        <p style="color: #999999; font-size: 13px; margin-top: -40px; margin-left: 20px">Use flat shapes instead of complex 3d forms.</p>
    </td>
</tr>
</table>

<table style="none">
<tr>
    <td> <p>Incorrect</p> <br /><img src="../../../images/ui/icons/style_folder_round.png"/> </td>
    <td> <p>Correct</p> <br /><img src="../../../images/ui/icons/style_folder_straigt.png"/> </td>
</tr>
<tr>
    <td colspan="2">
        <p style="color: #999999; font-size: 13px; margin-top: -40px; margin-left: 20px">Use straight corners and lines instead of curves. It’s recommended to use 45° and 90° angles,<br />or 30° and 60° where possible.</p>
    </td>
</tr>
</table>

Icons should be as simple as possible without losing their meaning. Clean and simple icons ensure readability and reduce visual clutter.
<table style="none">
<tr>
    <td> <p>Incorrect</p> <br /><img src="../../../images/ui/icons/style_soft_wrap_detailed.png"/> </td>
    <td> <p>Correct</p> <br /><img src="../../../images/ui/icons/style_soft_wrap_simple.png"/> </td>
</tr>
<tr>
    <td colspan="2">
        <p style="color: #999999; font-size: 13px; margin-top: -40px; margin-left: 20px">Reduced the icon to its minimal form, without losing its meaning.</p>
    </td>
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

<table style="none">
<tr>
    <td> <img src="../../../images/ui/icons/shape_square.png"/> <em> Square 12px x 12px </em></td>
    <td> <img src="../../../images/ui/icons/shape_circle.png"/> <em> Circle 14px </em></td>
</tr>
<tr>
    <td> <img src="../../../images/ui/icons/shape_rect_horizontal.png"/> <em> Horizontal rectangle 10px x 14px</em></td>
    <td> <img src="../../../images/ui/icons/shape_rect_vertical.png"/> <em> Vertical rectangle 14px x 10px </em></td>
</tr>
</table>

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
    <td> <img src="../../../images/ui/icons/stroke_download.png"/>
         <em> Use consistent stroke width.</em> </td>
    <td> <img src="../../../images/ui/icons/stroke_calc.png"/>
         <em> A thinner stroke can be used for subtle tweaks to the&nbsp;legibility of an icon and for optical correction.</em> </td>
</tr>
</table>

Align pixels on the X and Y axis and round to the nearest whole pixel, or 0.5px if it’s not possible to align to integer pixels. Aligning icons off the pixel grid will make the icon blurry when it’s rasterized.

> To check what an icon will look like when rasterized use the <ui-path>View | Canvas | Show Pixels on Zoom</ui-path> option <shortcut>Ctrl+P</shortcut> in Sketch,
> <ui-path>View | Pixel Preview</ui-path> <shortcut>Alt+Ctrl+Y</shortcut> in Illustrator.
>
{style="note"}

<table style="none">
<tr>
    <td> <p>Incorrect</p> <br /> <img src="../../../images/ui/icons/stroke_pixels_incorrect.png"/>
         <em> If an icon is positioned off pixels, <br /> it becomes blurry when rasterized.</em> </td>
    <td> <p>Correct</p> <br /> <img src="../../../images/ui/icons/stroke_pixels_correct.png"/>
         <em> Position stokes on integer pixels where possible.</em> </td>
</tr>
</table>

Use the 30°, 45° and 60° angles for diagonal lines as they look sharper than diagonals with other angles.

Round shapes, small details and some letters cannot be aligned to the pixel grid. Make sure that the significant pixels have sufficient saturation.

Reduce the number of anchors, as they increase the size of the image and takes longer to process it.

<table style="none">
<tr>
    <td> <p>Incorrect</p> <br /> <img src="../../../images/ui/icons/stroke_anchors_incorrect.png"/>
         <em> Unneeded anchors are added.</em> </td>
    <td>  <p>Correct</p> <br /> <img src="../../../images/ui/icons/stroke_anchors_correct.png"/>
         <em> Use the minimum number of anchors.</em> </td>
</tr>
</table>


## Modifier
A modifier is a small shape added over the base icon.

The default modifier position is the bottom right corner. If the base shape is badly recognizable with a modifier in the bottom right corner, or if several modifiers are needed, the modifier can be placed in other corners.

  <table style="none">
  <tr>
      <td> <img src="../../../images/ui/icons/modifier_search.png"/>
           <em> Place the modifier in the left corner <br/> if the base shape is badly recognizable <br/> with the modifier in the right corner.</em> </td>
      <td> <img src="../../../images/ui/icons/modifier_parameter.png"/>
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


<table>
<tr>
    <td> <img src="../../../images/ui/icons/arrow_filled.png"/>
         <em> 90° arrowhead and 2px square stroke body.</em> </td>
    <td> <img src="../../../images/ui/icons/arrow_unfilled.png"/>
         <em> Back arrow with unfilled arrowhead to compensate visual weight.</em> </td>
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


<table>
    <tr>
        <td> <img src="../../../images/ui/icons/action/red.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red </span> <br/> <span style="font-size: 80%">#DB5860 </span></td>
        <td> <img src="../../../images/ui/icons/action/red_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red dark </span> <br/> <span style="font-size: 80%">#C75450 </span></td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/action/yellow.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow </span> <br/> <span style="font-size: 80%">#EDA200 </span></td>
        <td> <img src="../../../images/ui/icons/action/yellow_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow dark </span> <br/> <span style="font-size: 80%">#F0A732 </span></td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/action/green.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green </span> <br/> <span style="font-size: 80%">#59A869 </span></td>
        <td> <img src="../../../images/ui/icons/action/green_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green dark </span> <br/> <span style="font-size: 80%">#499C54 </span></td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/action/blue.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Blue </span> <br/> <span style="font-size: 80%">#389FD6 </span></td>
        <td> <img src="../../../images/ui/icons/action/blue_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Blue dark </span> <br/> <span style="font-size: 80%">#3592C4 </span></td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/action/grey.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Grey </span> <br/> <span style="font-size: 80%">#6E6E6E </span></td>
        <td> <img src="../../../images/ui/icons/action/grey_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Grey dark </span> <br/> <span style="font-size: 80%">#AFB1B3 </span></td>
    </tr>
</table>

Use these colors according to the following rules:
1. By default, action icons are grey and monochromatic:
    ![](colors_actions_grey.png)

2. If an icon falls into one of the categories below, use the corresponding color:

    <table>
        <tr>
            <td> <span style="font-weight: bold"> Green </span> <br/> Positive actions: run, create <br /><img src="../../../images/ui/icons/colors_actions_green.png" /> </td>
            <td></td>
            <td> <span style="font-weight: bold"> Red </span> <br/> Destructive actions: stop, remove, force <br /><img src="../../../images/ui/icons/colors_actions_red.png" /> </td>
        </tr>
        <tr>
            <td> <span style="font-weight: bold"> Blue </span> <br/> Accent color to highlight popular icons based on usage statistics, highlight small elements in complex icons <br /><img src="../../../images/ui/icons/colors_actions_blue.png" /> </td>
            <td></td>
            <td> <span style="font-weight: bold"> Yellow </span> <br/> Warning actions: attract attention to the places (e.g. code) that can be optimized or improved <br /><img src="../../../images/ui/icons/colors_actions_yellow.png" /> </td>
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

<table>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/grey.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Grey </span> <br/> <span style="font-size: 80%"> #9AA7B0 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/grey_80.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Grey 80% </span> <br/> <span style="font-size: 80%"> #9AA7B0 op. 80% </span> </td>
        <td> <img src="../../../images/ui/icons/noun/grey_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Grey 60% </span> <br/> <span style="font-size: 80%"> #9AA7B0 op. 60% </span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/blue.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Blue </span> <br/> <span style="font-size: 80%"> #40B6E0 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/blue_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Blue 70% </span> <br/> <span style="font-size: 80%"> #40B6E0 op. 70% </span> </td>
        <td> <img src="../../../images/ui/icons/noun/blue_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Blue 60% </span> <br/> <span style="font-size: 80%"> #40B6E0 op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/green.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green </span> <br/> <span style="font-size: 80%"> #62B543 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/green_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green 70% </span> <br/> <span style="font-size: 80%"> #62B543 op. 70%</span> </td>
        <td> <img src="../../../images/ui/icons/noun/green_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green 60% </span> <br/> <span style="font-size: 80%"> #62B543 op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/yellow.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow </span> <br/> <span style="font-size: 80%"> #F4AF3D </span> </td>
        <td> <img src="../../../images/ui/icons/noun/yellow_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow 70% </span> <br/> <span style="font-size: 80%"> #F4AF3D op. 70%</span> </td>
        <td> <img src="../../../images/ui/icons/noun/yellow_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow 60% </span> <br/> <span style="font-size: 80%"> #F4AF3D op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/purple.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Purple </span> <br/> <span style="font-size: 80%"> #B99BF8 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/purple_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Purple 70% </span> <br/> <span style="font-size: 80%"> #B99BF8 op. 70% </span> </td>
        <td> <img src="../../../images/ui/icons/noun/purple_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Purple 60% </span> <br/> <span style="font-size: 80%"> #B99BF8 op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/pink.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Pink </span> <br/> <span style="font-size: 80%"> #F98B9E </span> </td>
        <td> <img src="../../../images/ui/icons/noun/pink_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Pink 70% </span> <br/> <span style="font-size: 80%"> #F98B9E op. 70%</span> </td>
        <td> <img src="../../../images/ui/icons/noun/pink_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Pink 60% </span> <br/> <span style="font-size: 80%"> #F98B9E op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/red.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red </span> <br/> <span style="font-size: 80%"> #F26522 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/red_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red 70% </span> <br/> <span style="font-size: 80%"> #F26522 op. 70%</span> </td>
        <td> <img src="../../../images/ui/icons/noun/red_60.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red 60% </span> <br/> <span style="font-size: 80%"> #F26522 op. 60%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/red_status.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red status</span> <br/> <span style="font-size: 80%"> #E05555 </span> </td>
        <td> <img src="../../../images/ui/icons/noun/red_status_70.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Red status 70% </span> <br/> <span style="font-size: 80%"> #E05555 op. 70%</span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/yellow_dark.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Yellow dark </span> <br/> <span style="font-size: 80%"> #D9A343 </span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/green_android.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Green Android </span> <br/> <span style="font-size: 80%"> #A4C639 </span> </td>
    </tr>
    <tr>
        <td> <img src="../../../images/ui/icons/noun/black.png" /> </td>
        <td style="padding-top: 30px; padding-left: 16px"> <span style="font-size: 110%"> Black_text </span> <br/> <span style="font-size: 80%"> #231F20 op. 70% </span> </td>
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

