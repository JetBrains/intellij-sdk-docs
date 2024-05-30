<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Writing Short and Clear

<link-summary>Tips on writing short and clear UI texts.</link-summary>

<p>
Texts in user interfaces should be short and clear. Short texts save reading time. Clear texts lead to fewer mistakes. Editing methods described below can help with that.
</p>

## Use simple constructions

Use simple verb forms. Prefer present tense.

Use simple sentences: one idea per sentence.

Avoid passive voice.

This way, the same meaning can be expressed with fewer words. Compare:

<i>Those resources that are available locally</i> → <i>Local resources</i>

<i>Maven has to use</i> → <i>Maven uses</i>

<i>The use of a secure connection is required</i> → <i>Use secure connection</i>

## Remove or elaborate generic words

Words like *general*, *advanced*, and *options* do not add useful information and can be removed with no harm to the meaning:

![](generic-general.png){width=540}

![](generic-options.png){width=540 style=block}
*No meaning is lost after removing group headers "General" and "Options".*

Some generic words cannot be removed. They can appear in actionable elements like buttons, checkboxes, or links. Or removing a group header could break a dialog layout. In such cases, use a more informative label instead:

![](generic-renamed-link.png){width=576 style=block}
*The link "Learn more" does not explain what useful information could be there for the user to click it.*

## Remove obvious objects and actions

Remove verbs that explain the function of a UI control:

![](obvious-specify.png){width=403 style=block}
*Text boxes are made for input — an explicit instruction "specify" duplicates the meaning expressed by the text field. Additional information can be given under the field — see [Context help](context_help.md).*

<table style="none">
  <tr>
     <td width="50%"> <img src="obvious-prefer.png" width="357"/><em> A selected radio button means its option is "preferred". </em> </td>
      <td width="50%"> <img src="obvious-allow.png" width="266"/><em> A checked checkbox means its feature is "allowed". </em> </td>
  </tr>
</table>

Remove words with a meaning that is already expressed in the label:
<table style="none">
    <tr>
       <td width="50%"> <img src="obvious-contents.png" width="357"/>
            <em> The clipboard’s purpose is to keep information so the infinitive "to keep" is implying the obvious and can be removed.</em> </td>
       <td width="50%"> <img src="obvious-functionality.png" width="266"/>
            <em> The word "functionality" is already implied by "Drag’n’Drop" and can be removed. </em> </td>
    </tr>
</table>

## Do not address the user

A user interface is for a person who uses it. Addressing this person is unnecessary because they by default perceive the text they see as for them:

![](addressing1.png){width=674}

![](addressing2.png){width=674 style=block}
*The whole phrase after the comma is not needed because its meaning is already expressed by the verb "configure".*

## Remove duplicates

If the repeating word appears in element labels, move it to the beginning. Finding a setting becomes faster as you scan only meaningful words:

![](writing_short_duplicates.png){width=438}

Remove duplicates in meaning:

![](help-improve-notification.png){width=498}

<table>
<tr>
<td width="30%"> Before </td>
<td width="30%"> After </td>
<td width="40%"> Explanation</td>
</tr>
<tr>
    <td> Help improve IntelliJ IDEA <format color="#cc4700"> by sending anonymous usage statistics to JetBrains s.r.o.</format> </td>
    <td> Help improve IntelliJ IDEA </td>
    <td> <format color="#999999"> The purpose of the header is to attract attention. The shorter and clearer header does that better. </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700"> if you want to help make IntelliJ IDEA better </format> </td>
    <td> Allow sending anonymous usage statistics to JetBrains s.r.o. </td>
    <td> <format color="#999999"> "Help make IntelliJ IDEA better" is already expressed in the message header. The body text now explains how the user can do that. </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700"> more... </format> </td>
    <td> Terms and conditions </td>
    <td> <format color="#999999">The link more... does not tell what is behind it — unclear for the user why to click it.</format></td>
</tr>
</table>

<table>
<tr>
<td width="30%"> Removed </td>
<td width="70%"> Explanation </td>

</tr>
<tr>
    <td> <format color="#cc4700"> click I agree</format> </td>
    <td> <format color="#999999"> The verb "click" is obvious from context: you cannot do much with a link other than click. </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700"> click... I don’t agree otherwise </format> </td>
    <td> <format color="#999999"> The link "I don’t agree" duplicates the notification "Close" button which appears on hover. </format> </td>
</tr>
<tr>
    <td> <img src="notificationInfo.png"/> </td>
    <td> <format color="#999999"> The icon is not needed because the message is purely informative, not an error or a warning. Texts are information by default, there is no need to specify that meaning explicitly. </format> </td>
</tr>
</table>

## Translate from tech to human

When you make a feature, you know how it works from the inside and can describe it from the implementation point of view:

![](indent-options-before.png){width=560}

A person not familiar with implementation details won’t know what the Automatic indent options detector is, why file’s indent options have been overwritten and what indent size=2 means. Translated to the "human" language, the message reads:

![](indent-options-after.png){width=560}

Always write UI text from a user’s perspective. Avoid technical terms, jargon and descriptions of inner logic that a user might not know.

## Write for first-time users

After writing a UI text, imagine seeing it for the first time and try to understand what might be unclear or confusing. Then correct if necessary.

![](first-time-file-colors-before.png){width=474 style=block}
*For a first-time user, Enable File Colors enables or disables the other two options (but it does not).*

![](first-time-file-colors-after.png){width=474 style=block}
*Rewritten: now all options are equal.*

More examples:

![](first-time-auto-insert.png){width=318 style=block}
*Before: The title can be read as "Auto-insert when only one checkbox is on" making you ask how it works when both checkboxes are on. After: Replacing choice with completion option makes the title unambiguous.*

![](first-time-smart-keys.png){width=318 style=block}
*Before: What do smart Home and End keys do? What do they do if they are not smart? After: The added text explains what the options do so that the reader can make an informed choice.*

## Examples

![](project-structure.png){width=642}

<table>

<tr>
<td width="30%"> Before </td>
<td width="30%"> After </td>
<td width="40%"> Explanation </td>
</tr>

<tr>
    <td> <format color="#cc4700">Project</format> name <format color="#cc4700">Project</format> SDK <format color="#cc4700">Project</format> language level </td>
    <td> Project name SDK Language level </td>
    <td> <format color="#999999"> "Project" can be left only for the first field. All others will be understood as project settings because they appear in the same group of UI elements. </format> </td>
</tr>
<tr>
    <td> This path <format color="#cc4700">is used</format> to store </td>
    <td> This path stores </td>
    <td> <format color="#999999"> Simple verb form </format> </td>
</tr>
<tr>
    <td> store <format color="#cc4700">all</format> project compilation <format color="#cc4700">results</format> </td>
    <td> stores project compilation output </td>
    <td> <format color="#999999"> "all" is extra because it is implied by default. "results" is another word for "output" — having different words for the same meaning complicates understanding. </format> </td>
</tr>
<tr>
    <td> A directory corresponding to each module <format color="#cc4700">is created</format> under this path. This directory contains <format color="#cc4700">two</format> subdirectories... </td>
    <td> It has a separate directory for each module, with the Production and Test subdirectories. </td>
    <td> <format color="#999999"> "is created" is not relevant to the meaning of this phrase. "two" is obvious from context: you see that there are two subdirectories because only two are named. </format> </td>
</tr>
</table>

<table>
<tr>
<td width="50%"> Moved </td>
<td width="50%"> Moved </td></tr>
<tr>
    <td> <format color="#cc4700">This</format> [field name] <format color="#cc4700">is default for all project modules. A module specific</format> [field name] <format color="#cc4700">can be configured for each of the modules is required.</format> </td>
    <td> <format color="#999999"> This phrase is repeated for all fields. Can be shown only once at the bottom of the dialog.</format> </td>
</tr>
</table>

![](offline-mode.png){width=566}
<table>
<tr>
<td width="30%"> Before </td>
<td width="30%"> After </td>
<td width="40%"> Explanation </td>
</tr>
<tr>
    <td> <format color="#cc4700">visits the</format> remote repositories <format color="#cc4700">and</format> checks for updates </td>
    <td> checks remote repositories for updates </td>
    <td> <format color="#999999"> "Checks" already includes the meaning of "visits". </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700">When you switch to</format> offline mode </td>
    <td> In the offline mode </td>
    <td> <format color="#999999"> The action "switched" is not relevant for the meaning of this phrase. Also, an unnecessary reference to the user — "you". </format> </td>
</tr>
<tr>
    <td> Maven <format color="#cc4700">has to</format> use </td>
    <td> Maven uses </td>
    <td> <format color="#999999"> Not relevant that a technology must do something, enough to say it just works this way. </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700">those</format> resources <format color="#cc4700">that are available</format> locally </td>
    <td> local resources </td>
    <td>  </td>
</tr>
<tr>
    <td> reports <format color="#cc4700">about the problems</format> if something is missing  </td>
    <td> reports if something is missing </td>
    <td>  </td>
</tr>
</table>

<table>
<tr>
<td width="30%"> Removed </td>
<td width="70%"> Explanation </td>
</tr>
<tr>
    <td> <format color="#cc4700">The offline mode is helpful when you need to work offline</format> </td>
    <td> <format color="#999999"> The phrase does not explain when the offline mode is helpful. It should either be elaborated or removed. </format> </td>
</tr>
<tr>
    <td> <format color="#cc4700"> or when your network connection is slow </format> </td>
    <td> <format color="#999999"> Only one of possible uses for the offline mode and an obvious one. It can be removed for a more concise text. </format> </td>
</tr>
</table>
