<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# How to Write Guidelines

<link-summary>Workflow and basic rules of writing articles for IntelliJ Platform UI Guidelines.</link-summary>

<p>This page describes the workflow and basic rules of writing articles for IntelliJ Platform UI Guidelines.</p>

## Workflow

Follow these steps if you want to add a new article to the guidelines:
1. Write an article in the Google doc.
2. Share the Google doc with the designers team, so they can review and comment the document.
3. When all comments are resolved, send the Google doc to tech writers for grammar review. [Create ticket](https://youtrack.jetbrains.com/newIssue?project=DOC&clearDraft=true&c=Type+Task&c=Assignee+Anna.Gasparyan&c=Subsystem+IntelliJ+IDEA) in YouTrack project “Documentation”, subsystem “IntelliJ IDEA”, auto-assigned to Anna Gasparyan.
4. After the review, add the article to the guidelines. Follow the instructions on [https://github.com/JetBrains/ui](https://github.com/JetBrains/ui).
4. Contact developers to add Code Snippets to the article.

## Text

The text should be short and clear. Follow the rules:

### Grammar

<table style="none">
<tr>
    <td> Use present tense.</td>
    <td style="font-style:italic"> A progress bar <font color="#18B04B">informs </font> user about the progress of a lengthy operation. </td>
</tr>
<tr>
    <td> Write in the active voice. </td>
    <td style="font-style:italic"> Progress bar <font color="#FF001B">is shown</font>.<br /> Progress bar <font color="#18B04B">appears</font>. </td>
</tr>
<tr>
    <td> Avoid unnecessary modal verbs. </td>
    <td style="font-style:italic"> Label <font color="#FF001B">should use</font> sentence-style capitalization.<br /> <font color="#18B04B">Use</font> sentence capitalization in labels. </td>
</tr>
<tr>
    <td> Use imperatives. </td>
    <td style="font-style:italic"><font color="#FF001B">The cursor changes</font> to the pointing hand.<br /> <font color="#18B04B">Change the cursor</font> to the pointing hand. </td>
</tr>
<tr>
    <td> Do not address the reader. </td>
    <td style="font-style:italic"> Use combobox if..., Follow guidelines... </td>
</tr>
<tr>
    <td> When describing user behavior, write: </td>
    <td style="font-style:italic"> A <font color="#18B04B"> user looks </font> forward to what will appear after completion. </td>
</tr>
<tr>
    <td> Avoid bracketed text, it complicates reading. If information is important — put it in a new sentence, if not — remove it. </td>
    <td style="font-style:italic">Provide a header <font color="#FF001B">(bold)</font> for each progress. <br /> Provide a <font color="#18B04B">bold</font> header for each progress. </td>
</tr>
</table>


### Contents

* Omit common introductory phrases.

* Write one idea per sentence.

* Split the text to subsections and short paragraphs.

* Use bulleted lists when the order of points does not matter, and numbered list when they do.

* When giving a recommendation, explain why it is useful.
    *Bad: *If a process is started by a user, provide a notification when the process finishes*.
    * Good>: *If a process is started by a user, provide a notification when the process finishes. This way the user, if switched to another task while waiting for a process to finish, would know they can return back and see the results*.

*  Add links if you refer to other sections. Links should be descriptive, do not use *Click here* links.


### Word-level recommendations

<table style="none">
<tr>
    <td> <i>Would be</i> — use <i>is</i> instead, when possible. </td>
    <td> Displaying indicator <font color="#FF001B">would be</font> distracting.<br />Displaying indicator <font color="#18B04B">is</font> distracting. </td>
</tr>
<tr>
    <td> <i>Then</i> — omit if possible. </td>
    <td> If a process is started by the user, <font color="#FF001B">then</font>. provide notification.</td>
</tr>
<tr>
    <td> <emphasis>He/she</emphasis> — replace with <emphasis>they</emphasis>. </td>
    <td> If a process lasts less than 1 second, the user won’t be able to read the process name and showing it would just distract <font color="#18B04B">them</font>.</td>
</tr>
<tr>
    <td> Select a word with <b>bold</b> to emphasise or with <i>italic</i> to quote. </td>
</tr>
</table>


## Structure

If an article is about a control, add a control's class name under the article title:

<code-block lang="java">
codename: JButton
</code-block>

Structure a single guideline as follows:
* Start each guideline with a text description and provide an image *under* it if necessary. Do **not** use a reversed order (image than text).
* Place an additional text under the image only if it does not make sense placing it with the text above the image.

Guideline numbered anchors:
* Each paragraph `<p>` is assigned a numbered anchor. An anchor helps referencing a particular guideline. Structure the article so that each guideline is a single paragraph.
* To start a new paragraph, add an empty line above.
* To create a text block without an anchor, do not add an empty line above. Add two spaces in the end of the previous text block.
* To add extra vertical space without creating a paragraph, use `<br/>`.
* If some element gets an unnecessary anchor, use the class `noanchor`. Note that Markdown does not work inside the `<p>` tag, replace it with HTML. Example:
<code-block>{% highlight html %}</code-block>
<p>
For when to use the empty state, see the
<a href="empty_state.md">Empty State</a>.
</p>


The article structure can vary depending on whether a control, component or principle is described. Generally, use the sections that are described below.

### Introduction paragraph

In the first paragraph describe a control, component or principle and provide an illustration. If there are different types of the control, describe all of them.

### When to use

Describe when to use the control or when to apply the principle.

If the control is often used incorrectly, describe cases when the control should not be used.

### How to use

Provide guidelines on how to use the control, component or principle. Group guidelines by their subject. For a control it can be:
* Behavior details for a single control and for a group of such controls (if applicable)
* Wording — how to write a label for the control
* Using the control with other controls
* Any other recommendations specific to this control

Use notes for links to additional materials, sources, useful facts and examples. To insert a note, use:
<code-block>{% highlight html %}&lt;note>Note text&lt;/note></code-block>

Use formatting for <shortcut>shortcuts</shortcut>:
<code-block>{% highlight html %}&lt;shortcut>Ctrl+Space&lt;/shortcut></code-block>

To add a horizontal line in a table, use:
<code-block>{% highlight html %}</code-block>


### Sizes and placement

Give recommendations for:
* Minimum and maximum sizes in pixels
* How to layout with other controls. Refer to [Layout](layout.md) if possible.
* Insets between controls in pixels

Illustrate sizes and insets as [described below](#colors-insets-and-sizes).


### Style

Provide [an illustration](#style) how a control or component looks in different look-and-feels.

List color keys used for this control.

Do not provide font properties and specific hex colors.


## Images

Illustrate all statements with interface examples. Use **default macOS** theme as the main themes for illustrations. Add a section with examples for Darcula theme in the Google doc, but do not move it to the official guidelines.

If an image description appears above the image, end it with a colon:

![](example_1.png){width=312}

If under, do not use a period at the end:

![](example_1.png){width=312}

*Image description*

Several not wide images can be placed in two or three columns:

![](example_2.png){width=760}

If there is a set of images that illustrates the sequence of states, place them horizontally or vertically and link with an arrow:

![](example_3.png){width=565}

If there is a common mistake in UI, provide Correct/Incorrect images:

![](corret_incorrect.png){width=417}

The “Correct/Incorrect” can be placed above the image or on the left:

![](correct_incorrect_vertical.png){width=285}

If there is one image and it’s not big, cross out the image with 1px line instead of “Incorrect” header:

![](incorrect.png){width=268}

Use html to add Correct/Incorrect label:
<code-block>{% highlight html %}&lt;p>Incorrect&lt;/p>
</code-block>

![](correct_incorrect_sizes.png){width=530}

Save all images in two sizes: example.png and example@2x.png.


### Callouts

All text on images should be horizontally oriented.

Font-style: Gotham Book
Font-size: 14px
Line height: 20px
Max width: 300px
Color:  #999999

Place callouts around the image at a distance of at least 30px:

![](callout_1.png){width=474}

Or use leader lines to sign specific items on the image:

![](callout_2.png){width=330}

**Leader line** is 1px line, color: #000000 op. 0.3.

Line is vertical or horizontal. It can be bent once if there is not enough space for the text. Do not intersect lines.

Leave 1px between the line and the element to which it refers or place line over the element:

![](leader_lines_1.png){width=182}

Lines go beyond the image by 20px:

![](leader_lines_2.png){width=353}

Text position:

![](leader_lines_3.png){width=452}

If line is horizontal, center it with the first comment line.

### Colors, insets and sizes

Use the Hex Code format to specify colors:

![](how_to_write_guidelines_colors.png){width=252}

Use colored rectangles to specify sizes inside the element and lines to specify external sizes:

![](insets.png){width=377}

* Main rectangle: <span style="color:#DA769D">#DA769D</span> op. 0.4.
* Secondary rectangle: <span style="color:#6D9AE6">#6D9AE6</span> op. 0.4.
* Text and line: <span style="color:#BD136B">#BD136B</span>, <span style="color:#0054C0">#0054C0</span>.
* Distance between image and line, between line and text is 5px.
* Align all sizes on the right.

Use line to show that text is aligned with an element:

![](how_to_write_guidelines_alignment.png){width=155}

Specify the element sizes in the following format:
![](how_to_write_guidelines_sizes.png){width=54}

For text labels, specify insets from the bounding box:

![](boundingBox.png){width=216}

![](insetsText.png){width=125}

Parameters for regular labels that make bounding boxes in Sketch the same size as in Java (already used in Sketch libraries symbols):
- *macOS*: SF UI Text Medium / 13 size / –0.1 character spacing / 16 line spacing
- *Windows*: Segoe UI Regular / 12 size / 16 line spacing
- *Linux*: Ubuntu Regular / 15 size / 18 line spacing


If unsure about a bounding box size for other font sizes, check with UI Inspector.

<!--
If there are many properties for different themes, put them in the table:
![](tdemes.png)
-->

## Code snippets
Provide code snippets along the article to help developers implement the described look and behavior.

If a code snippet is too big, put it at the end of the article and provide a link.

To insert a snippet, use:
<code-block>
{% highlight html %}{{ "{% highlight java " }}%}
Code snippet
{{ "{% endhighlight " }}%}
</code-block>


