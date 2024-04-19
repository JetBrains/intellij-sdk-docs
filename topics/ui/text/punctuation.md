---
title: Punctuation
category: Text
---

Use the following rules for labels and description texts in IntelliJ user interfaces. For punctuation in numbers and times, see [Data formats](data_formats.md).

## Ellipsis
End an action name with an ellipsis if it opens a dialog where input is required or possible. An ellipsis helps users understand if an action is immediate or additional interaction is to follow.

<p>This rule applies to actions in the following controls: <a href="button.topic">button</a>, <a href="link.md">link</a>, <a href="menu.md">menu item</a>.</p>

<p>Examples:</p>
<table style="none">
<tr>
    <td>
        <i>Save All</i><br/>
        <i>Build Project</i>
    </td>
    <td>
        Happens immediately, no ellipsis
    </td>
</tr>
<tr>
    <td>
        <i>Import Settings…</i><br/>
        <i>Export to HTML…</i>
    </td>
    <td>
        Opens dialogs that require input
    </td>
</tr>
<tr>
    <td>
        <i>Project Structure…</i>
    </td>
    <td>
        Opens a complex dialog where input is not required but possible
    </td>
</tr>
<tr>
    <td>
        <i>Tip of the Day</i><br/>
        <i>About IntelliJ IDEA</i>
    </td>
    <td>
        Opens dialogs that just show information, no input is either required or possible
    </td>
</tr>
</table>

Use an ellipsis at the end of a truncated text if there is no scrollbar and this is not a table column, see [truncation in table columns](table.md#sizes-and-placement). Provide a way to show the full text, for example, expand the control or show a tooltip on hover.

![](2_01_truncated_text.png)
*A notification can be expanded to show the full text.*

Use an ellipsis with verbs describing an ongoing process, for example,  Searching… See more examples in [Progress text](progress_text.md).

Use the ellipsis character … <span style="color: #999999;">(U+2026 in Unicode)</span>. Do not use three separate “dot” characters.


## Period
Do **not** put a period at the end of a single sentence, even if it is a complete sentence.

![](1_01_no_period_1.png)

![](1_01_no_period_2.png)

If a text consists of several sentences, put a period after each sentence.
![](1_02_periods_several_sentences.png)

Do **not** put a period at the end of an IDE action.
![](1_03_periods_action.png)
*The empty text consists of two sentences but the second one is an IDE action so it should not have a period.*

<p>Note that links that are not IDE actions can have a period in the end.</p>

![](1_04_period_navigation_link.png)


## Comma
<p>Use a comma:</p>

Before a conjunction in a list of three or more items (the Oxford or serial comma).
<p style="margin: 0 0 36px 20px;"><i>Border for a text field, combo box, or spinner</i></p>

Between symbols in series.
<p style="margin: 0 0 36px 20px;"><i>Use the following characters: &, $, ., and *</i></p>



## Colon
Use a colon after labels for inputs and radio button / checkbox groups.

![](../../../images/ui/input_field/label_noun.png)

![](../../../images/ui/radiobutton/radio_example.png)

Do **not** use a colon if a label and text inside the input element make a phrase.

![](../../../images/ui/input_field/label_sentence.png)


## Contractions
Generally, do not use contractions.
<p style="margin: 0 0 36px 20px;">
<i>Path <span style="color: #C3481B;">can’t</span> be found → Path cannot be found</i>
</p>

Use contractions only if the action name has 4 and more words, and the contracted word does not affect the meaning significantly.
<p style="margin: 0 0 36px 20px;">
<i>What’s New in IntelliJ IDEA</i>
</p>

*Don’t* or *Do not*:
* Always contract in the phrase *Don’t [some verb] again*. The phrase is common, and the meaning is easily recognized.
  ![](3_01_dont_ask_again.png)
* In other cases, do not contract. The full form reduces chances that the relevant meaning might be missed.
<p style="margin: -10px 0 36px 41px; line-height: 240%;">
<i><span style="color: #C3481B;">Don’t</span> send → Do not send</i><br/>
<i>Do not save, forget passwords after restart</i>
</p>


## Quotation marks
Use single quotation marks by default.

<note>Use a straight single quotation mark ' <span style="color: #999999;">U+0027</span>. Do not use opening and closing quotation marks ‘ ’ <span style="color: #999999;">U+2018, U+2019</span>.</note>

<p style="margin: -10px 0 36px 20px; line-height: 240%;">
<i>Indexing library 'KotlinJavaRuntime'<br/>
Error parsing '.mvn/maven.config'<br/>
Add 'root=true' to the beginning of the file<br/>
Show 'Scratches and Consoles' in the Project view</i>
</p>

Do not use quotation marks for keyboard key names.
<p style="margin: 0 0 36px 20px;">
<i>Type an expression and press <span style="color: #C3481B;"><b>'</b></span>Enter<span style="color: #C3481B;"><b>'</b></span> → press Enter</i>
</p>

Do not use double quotation marks.


## Question marks
Use only in alerts when asking for confirmation.
<p style="margin: 0 0 36px 20px;">
<i>A file with this name already exists. Do you want to overwrite it?</i>
</p>

Avoid in other cases.
<p style="margin: 0 0 36px 20px;">
<i>Forgot password? → Remind password</i>
</p>


## Exclamation points
Do not use. Exclamation points can cause the sentence tone to be interpreted as aggressive, condescending, or overly informal.
