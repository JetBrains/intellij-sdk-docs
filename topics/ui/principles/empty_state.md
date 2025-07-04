<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Empty State

<link-summary>Providing instructions in an empty UI area on how to fill it with data.</link-summary>

<tldr>

**Implementation:** [`ComponentWithEmptyText`](%gh-ic%/platform/platform-api/src/com/intellij/util/ui/ComponentWithEmptyText.java)

</tldr>

Empty states of tool windows, lists, trees, tables, master-detail layouts, or other container components should be informative for the users.
Fill in the empty states with the following:
- [A reason why the container is empty](#reason-why-empty)
- [Actions to fill in the area](#actions-to-fill-the-area)
- [A link to the corresponding help topic](#help-topic)

<img src="empty_state_structure.png" alt="Empty state of a tool window" width="706"/>

## Reason why empty

### Explain the current state

The default pattern is `No [entity] added.` If `added`, `created`, `configured`, or other words act as synonyms in a particular case, use `added` for consistency.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_reason_correct.png" alt="Correct text in the empty state" width="378" />
    </td>
    <td >
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_reason_incorrect.png" alt="Incorrect text in the empty state" width="378"/>
    </td>
  </tr>
</table>

### Be precise

<p>Do not introduce new entities that are not used in the current context.
Instead, use the terms from the current UI so that the user understands
what exactly is missing:</p>

<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_clear_reason.png" alt="The reason is clear"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_unclear_reason.png" alt="The reason is not clear"/></td>
  </tr>
</table>

## Actions to fill the area

An action makes it easier to understand what to start with
instead of searching for the appropriate icon on the toolbar.
It can also educate about the shortcut.

### Use one or two actions

Three or more actions would make it harder to choose what to start from.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_actions_correct.png" alt="One action"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_actions_incorrect.png" alt="Three actions"/>
    </td>
  </tr>
</table>

In rare cases, when you need to list all possible starting points, use the list layout.

If there is enough space (for example, in horizontal tool windows), you may use the table layout to place action links, shortcuts, and action descriptions:
<img src="empty_state_table.png" alt="Table layout for actions" width="706"/>

### Open menus from the toolbar

If an action opens a toolbar menu, open it at the same position where it
would be opened with the corresponding toolbar button. This would explain
which toolbar icon opens the menu.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_menu_correct.png" alt="Toolbar menu"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_menu_incorrect.png" alt="Menu under the action"/>
    </td>
  </tr>
</table>

### Explain what to do when there is no action`

If there is no action that can be performed by clicking a link,
add a text describing all the required steps:

<img src="empty_state_no_actions.png" alt="Explain what to do when there is no action" width="706"/>

### Hide the toolbar

Hide the area's toolbar if it does not have the same action as
in the empty state. Usually, all other toolbar actions are not
relevant in this case.

<format color="369650" style="bold">Correct</format>

<img src="empty_state_no_actions.png" alt="Hide the toolbar for the empty state" width="706"/>

<format color="E55765" style="bold">Incorrect</format>

<img src="empty_state_toolbar.png" alt="Toolbar is not needed" width="706"/>

## Help topic
<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <img src="empty_state_link_to_help.png" alt="Link to a help topic"/>
    </td>
    <td>
    <control>Link to a help topic</control>
    <p>In tool windows and <a anchor="empty-state-of-the-master-detail-layout">master-detail layouts</a>,
provide a link to a help topic that explains the functionality.
Add the question mark icon in the beginning.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="empty_state_inline_help.png" alt="Inline hint under the table"/>
    </td>
    <td>
      <control>Context help in inline hints</control>
      <p>In tables, trees, and lists, provide instructions according to the <a href="context_help.md" anchor="inline-help-text">inline text help</a> rules.
 Avoid using question mark links in these cases because such components rarely require deep explanations.</p>
</td>
</tr>
</table>

## Empty state of the master-detail layout

Master-detail layouts include a list or a tree on the left (in the master part)
and the details area on the right.

- If the master area is empty, display the explanation, action, and link to the help article.
- If the details area is empty, no explanations are needed. In this case, show the actions to fill in the area.

<img src="empty_state_master_detail_layout.png" alt="Empty master-detail layout" width="706"/>



## Writing guidelines

### Punctuation

Separate sentences in the empty state with periods, but do not put a period after the action link.
Use the ellipses at the end of the action link if this link opens a dialog or a popup which requires some input from the user.
<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_punctuation_correct.png" alt="Correct punctuation in the empty state"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_punctuation_incorrect.png" alt="Incorrect punctuation in the empty state"/>
    </td>
  </tr>
</table>

### Capitalization

Use sentence capitalization in the empty state texts and links.
<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_capitalization_correct.png" alt="Correct capitalization in the empty state"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_capitalization_incorrect.png" alt="Incorrect capitalization in the empty state"/>
    </td>
  </tr>
</table>

### Avoid excessive verbs

- In actions, avoid words that describe physical actions like `press` or `click` — they are obvious from how the link works.

- Avoid saying `add new`. Just use `add` because all that is added is new in the context of an empty UI area.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_verbs_correct.png" alt="Correct usage of verbs in the empty state"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_verbs_incorrect.png" alt="Incorrect usage of verbs in the empty state"/>
    </td>
  </tr>
</table>


## Sizes and placement

[//]: # (The minimum text width is 40 characters. If an area is too narrow to show the minimum text width, show text in area fields, and if no fields are left, under the area borders.)

[//]: # ()
[//]: # (<format color="Red" style="bold">Incorrect</format>)

[//]: # ()
[//]: # (![]&#40;nbsp-incorrect.png&#41;{width=379})

[//]: # ()
[//]: # (<format color="Green" style="bold">Correct</format>)

[//]: # ()
[//]: # (![]&#40;nbsp-correct.png&#41;{width=379})

[//]: # ()
[//]: # (The text is center-aligned. If possible, the center for the help topic link should be calculated with a 16px inset on the left. This helps visually align the help link with the lines above it.)

[//]: # ()
[//]: # (![]&#40;database-tw-markup1.png&#41;{width=300})

Vertical spacing:

<img src="empty_state_vertical_spacing.png" alt="Empty state vertical spacing" width="706"/>

Minimum right and left margins:

<img src="empty_state_horizontal_spacing.png" alt="Empty state horizontal spacing" width="706"/>

Use non-breaking spaces in the following cases:
* Between action names and shortcuts
* For articles and prepositions
<table style="none" border="false" column-width="fixed">
  <tr>
    <td>
      <format color="369650" style="bold">Correct</format>
      <img src="empty_state_non_breaking_space_correct.png" alt="Correct usage of non-breaking spaces in the empty state"/>
    </td>
    <td>
      <format color="E55765" style="bold">Incorrect</format>
      <img src="empty_state_non_breaking_space_incorrect.png" alt="Incorrect usage of non-breaking spaces in the empty state"/>
    </td>
  </tr>
</table>

[//]: # (The text should wrap when a UI area’s width changes:)

[//]: # ()
[//]: # (![]&#40;database-tw-horizontal.png&#41;{width=579})

[//]: # ()
[//]: # (![]&#40;database-tw.png&#41;{width=300})

[//]: # (## Style)

[//]: # ()
[//]: # (The link should not be underlined.)

[//]: # ()
[//]: # (In Darcula:)

[//]: # ()
[//]: # (![]&#40;database-tw-darcula.png&#41;{width=300})

[//]: # ()
[//]: # (Use non-breaking spaces for articles and prepositions in the instructional text.)

[//]: # ()
[//]: # (Use non-breaking spaces for articles and prepositions in the instructional text.)

