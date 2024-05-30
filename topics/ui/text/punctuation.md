<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Punctuation

<link-summary>Punctuation rules for labels and description texts.</link-summary>

Use the following rules for labels and description texts in IntelliJ user interfaces. For punctuation in numbers and times, see [Data formats](data_formats.md).

## Ellipsis

End an action name with an ellipsis if it opens a dialog where input is required or possible. An ellipsis helps users understand if an action is immediate, or additional interaction is to follow.

<p>This rule applies to actions in the following controls: <a href="button.topic">button</a>, <a href="link.md">link</a>, menu item.</p>

<p>Examples:</p>
<table style="none">
<tr>
    <td>
        <p><i>Save All</i></p>
        <p><i>Build Project</i></p>
    </td>
    <td>
        Happens immediately, no ellipsis
    </td>
</tr>
<tr>
    <td>
        <p><i>Import Settings…</i></p>
        <p><i>Export to HTML…</i></p>
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
        <p><i>Tip of the Day</i></p>
        <p><i>About IntelliJ IDEA</i></p>
    </td>
    <td>
        Opens dialogs that just show information, no input is either required or possible
    </td>
</tr>
</table>

Use an ellipsis at the end of a truncated text if there is no scrollbar and this is not a table column, see [truncation in table columns](table.md#sizes-and-placement). Provide a way to show the full text, for example, expand the control or show a tooltip on hover.

![](2_01_truncated_text.png){width=399 style=block}
*A notification can be expanded to show the full text.*

Use an ellipsis with verbs describing an ongoing process, for example, Searching… See more examples in [Progress text](progress_text.md).

Use the ellipsis character … (`U+2026` in Unicode). Do not use three separate "dot" characters.

## Period

Do **not** put a period at the end of a single sentence, even if it is a complete sentence.

![](1_01_no_period_1.png){width=385}

![](1_01_no_period_2.png){width=396}

If a text consists of several sentences, put a period after each sentence.
![](1_02_periods_several_sentences.png){width=488}

Do **not** put a period at the end of an IDE action.

![](1_03_periods_action.png){width=385 style=block}
*The empty text consists of two sentences, but the second one is an IDE action, so it should not have a period.*

<p>Note that links that are not IDE actions can have a period in the end.</p>

![](1_04_period_navigation_link.png){width=376}

## Comma

Use a comma:

Before a conjunction in a list of three or more items (the Oxford or serial comma).

*Border for a text field, combo box, or spinner*

Between symbols in series.

*Use the following characters: &, $, ., and \**

## Colon

Use a colon after labels for inputs and radio button / checkbox groups.

![](label_noun.png){width=153}

![](radio_example.png){width=213}

Do **not** use a colon if a label and text inside the input element make a phrase.

![](label_sentence.png){width=247}

## Contractions

Generally, do not use contractions.

*Path <format color="#C3481B">can’t</format> be found → Path cannot be found*

Use contractions only if the action name has 4 and more words, and the contracted word does not affect the meaning significantly.

*What’s New in IntelliJ IDEA*

*Don’t* or *Do not*:

* Always contract in the phrase *Don’t [some verb] again*. The phrase is common, and the meaning is easily recognized.
  ![](3_01_dont_ask_again.png){width=399}
* In other cases, do not contract. The full form reduces chances that the relevant meaning might be missed.

*<format color="#C3481B">Don’t</format> send → Do not send*

*Do not save, forget passwords after restart*

## Quotation marks

Use single quotation marks by default.

> Use a straight single quotation mark ' (`U+0027`). Do not use opening and closing quotation marks ‘ ’ (`U+2018`/`U+2019`).
>
{style="note"}


<i>Indexing library 'KotlinJavaRuntime'<br/>
Error parsing '.mvn/maven.config'<br/>
Add 'root=true' to the beginning of the file<br/>
Show 'Scratches and Consoles' in the Project view</i>

Do not use quotation marks for keyboard key names.

*Type an expression and press <format color="#cc4700" style="bold">'</format>Enter<format color="#cc4700" style="bold">'</format> → press Enter*

Do not use double quotation marks.

## Question marks

Use only in alerts when asking for confirmation.

*A file with this name already exists. Do you want to overwrite it?*

Avoid in other cases.

*Forgot password? → Remind password*

## Exclamation points

Do not use. Exclamation points can cause the sentence tone to be interpreted as aggressive, condescending, or overly informal.
