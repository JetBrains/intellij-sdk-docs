<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Link

<link-summary>UI guidelines on using links.</link-summary>

<tldr>

**Implementation:** [`ActionLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/ActionLink.kt), [`DropDownLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/DropDownLink.kt)

</tldr>

![A preview of link types: default, drop-down, and external.](link.png){width=706}

## When to use

### Navigation inside a window

Use a regular link for navigation between pages of the same window. In the example bellow <control>Manage scopes…</control> opens another page in Settings for editing the values.

<table style="none" border="false">
  <tr>
    <td>
        <format color="369650" style="bold">Correct</format>
        <img src="link_when_to_1_correct.png" width="378" alt="A vertical list of three options: 'Non-Project Files', 'Problems', and 'Production', followed by a 'Manage scopes…' link below."/>
    </td>
    <td>
        <format color="E55765" style="bold">Incorrect</format>
        <img src="link_when_to_1_incorrect.png" width="378" alt="A vertical list of three options: 'Non-Project Files', 'Problems', and 'Production', followed 'Manage scopes' button below."/>
    </td>
  </tr>
</table>

### Web resources

Use an external link for navigation to web resources. See below for the [external link icon](#external-link-icon).

![Text 'Learn more about what is logged' with 'what is logged' as an external link with an arrow icon.](link_when_to_2.png){width=706}

### Secondary actions in packed UI areas

Use a regular or [drop-down link](#drop-down-link) for secondary actions in packed or small UI areas.


![Build and run section with 'Modify options' drop-down link. Contains a Java SDK selector, a command line arguments selector, and a program arguments input.](link_when_to_4.png){width=706}

### Secondary actions in a tree or table

#### Some items can be changed

Use a link as a secondary action for some items of a tree or table.

![A tree with a checkbox and four dependent controls, and links 'Set…' in the first and the fourth control](link_when_to_5.png){width=706}

#### All items can be changed

<p>If a link in every tree or table line has meaningful formatting, leave it.</p>

<p>For example, links in the <control>Push Commits</control> window help understand the relation between them: <control>[remote repository] : [branch]</control>; moving these links elsewhere would break this meaning.</p>

<img src="link_when_to_6_3.png" width="706" alt="Links in the Push Commits window 'origin : master'"/>

## When not to use

### Primary actions

Use a [button](button.topic) or a [drop down list](drop_down.md) for primary actions. These controls can be selected from the keyboard and are bigger and easier to click.

<table style="none" border="false">
  <tr>
    <td>
        <format color="369650" style="bold">Correct</format>
        <img src="link_when_not_to_1_correct.png" width="378" alt="The Commit Message input with primary and secondary actions as buttons."/>
    </td>
    <td>
        <format color="E55765" style="bold">Incorrect</format>
        <img src="link_when_not_to_1_incorrect.png" width="378" alt="The Commit Message input with primary and secondary actions as links."/>
    </td>
  </tr>
</table>

### Secondary actions in a tree or table {id="secondary-actions-not-to-use"}

#### Editable items

<p>When all items can be changed, add an <a href="icon_button.md">icon button</a> to a toolbar.</p>

<table style="none" border="false">
  <tr>
    <td>
        <format color="369650" style="bold">Correct</format>
        <img src="link_when_to_6_correct.png" width="378" alt="Table with a toolbar with icon-buttons"/>
    </td>
    <td>
        <format color="E55765" style="bold">Incorrect</format>
        <img src="link_when_to_6_incorrect.png" width="378" alt="Table with a link 'Edit…' in every row"/>
    </td>
  </tr>
</table>

#### List of choices

<p>For a list of choices, add a separate table column of drop-down cells (see <a href="table.md" anchor="editing-values">Table</a>).</p>

<table style="none" border="false">
  <tr>
    <td>
        <format color="369650" style="bold">Correct</format>
        <img src="link_when_not_to_3_correct.png" width="378" alt="Table with a toolbar with icon-buttons"/>
    </td>
    <td>
        <format color="E55765" style="bold">Incorrect</format>
        <img src="link_when_not_to_3_incorrect.png" width="378" alt="Table with a link 'Edit…' in every row"/>
    </td>
  </tr>
</table>

### Simple UI

Use a [button](button.topic) or a [drop down list](drop_down.md) when a UI is not constrained.
These controls can be selected from the keyboard and are bigger and easier to click.

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_when_not_to_2_correct.png" width="706" alt="A stack of controls with a button 'Edit' following one of the controls."/>
    </td>
  </tr>
  <tr>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_when_not_to_2_incorrect.png" width="706" alt="A stack of controls with a link 'Edit' following one of the controls."/>
    </td>
  </tr>
</table>

## How to use

### Writing guidelines

#### Use sentence capitalization

Follow the [sentence capilaziation](capitalization.md#sentence) guideline.

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_1_correct.png" width="378" alt="A 'Modify options' drod-down with the sentence style capitalization."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_1_incorrect.png" width="378" alt="A 'Modify Options' drod-down with the title style capitalization."/>
    </td>
  </tr>
</table>

#### Ellipsis

Add an ellipsis to a link if it opens another UI area where input is possible. See examples for the [Button](button.topic).

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_2_correct.png" width="378" alt="A vertical list of three options: 'Non-Project Files', 'Problems', and 'Production', followed by a 'Manage scopes…' link below. The link has an ellipsis in the end."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_2_incorrect.png" width="378" alt="Vertical list of three options: 'Non-Project Files', 'Problems', and 'Production', followed by a 'Manage scopes' link below. The link doesn't have an ellipsis in the end."/>
    </td>
  </tr>
</table>

#### Wording

Do not use words like <code>navigate</code> or <code>click here</code>. A link already implies navigation or clicking.

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_3_correct.png" width="378" alt="Link 'Proxy settings…'."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_3_incorrect.png" width="378" alt="Link 'Navigate to general proxy settings'."/>
    </td>
  </tr>
</table>

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_4_correct.png" width="378" alt="Link 'Download drivers…'."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_4_incorrect.png" width="378" alt="Text reading 'Click here to download drivers' where 'here' is a link."/>
    </td>
  </tr>
</table>

#### Make it informative

When possible, replace <code>Learn more</code> and other generic phrases with more informative ones.

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_5_correct.png" width="378" alt="Link 'How to use Closure Linter'."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_5_incorrect.png" width="378" alt="Link 'Learn more'."/>
    </td>
  </tr>
</table>

### Link as a part of text

A link can be a part of a checkbox or radio button label or of any stand-alone text, like the text in an [empty state](empty_state.md) or in [context help](context_help.md):

![Checkbox 'Use console font instead of the default font' where 'deafult font' is a link.](link_how_to_6.png){width=706}

Make a link the minimum phrase that is enough to understand what will happen without reading the whole text:

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_7_correct.png" width="378" alt="Text reading 'Learn more about what is logged' where 'what is logged' is a link."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_7_incorrect.png" width="378" alt="Link 'Learn more about what is logged'."/>
    </td>
  </tr>
</table>

### External link icon

Add the arrow icon for an external link. The icon shows that the user will switch to a browser and lose the current context.

![Text 'Learn more about what is logged' where 'what is logged' is an external link with the external link icon.](link_how_to_8.png){width=706}

<chapter title="Implementation" collapsible="true">

  <tabs group="languages">
    <tab title="Kotlin" group-key="kotlin">

  ```kotlin
  val externalLink = ActionLink("External link") {
    BrowserUtil.browse("https://www.jetbrains.com")
  }.apply {
    setExternalLinkIcon()
  }
  ```
  </tab>
  <tab title="Java" group-key="java">

  ```java
  ActionLink externalLink = new ActionLink(
      "External link",
      event -> BrowserUtil.browse("https://www.jetbrains.com")
  );
  externalLink.setExternalLinkIcon();
  ```
  </tab>
  </tabs>

</chapter>

### Help icon

Use the help icon in links in [empty states](empty_state.md) to indicate that this is an external help resource. The arrow icon in this case is unnecessary.

<table style="none" border="false">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="link_how_to_9_correct.png" width="378" alt="Empty state in the Database tool window with an explanation text and a help source link 'Defining a database' with the help icon."/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="link_how_to_9_incorrect.png" width="378" alt="Empty state in the Database tool window with an explanation text and an external link 'Defining a database' with the external link icon."/>
    </td>
  </tr>
</table>

### Drop-down link

Drop-down links show a context menu or a popup. To implement, use [`DropDownLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/DropDownLink.kt).

![Control 'Changes from 2 commits' where '2 commits' is a drop-down link opening a list of all commits](link_dropdown.png){width=706}

## Placement

* For default links follow [layout of buttons and links](layout.md#buttons-and-links).
* For drop-down links follow [layout of labeled controls](layout.md#labeled-input-controls).

## Built-in behavior

A focused link is activated from the keyboard with <shortcut>Space</shortcut>.
