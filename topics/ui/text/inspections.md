<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Inspections

<link-summary>UI guidelines on writing texts used in inspections.</link-summary>

![](main.png){width=834 style=block}
*Inspections configuration on <ui-path>Settings | Editor | Inspections</ui-path> page or in <control>Configure Inspections</control> dialog.*

Inspection names, descriptions, and editor messages should be short and descriptive.
Follow the rules for [short and clear texts](writing_short.md) and [punctuation](punctuation.md) in addition to the rules described below.

### Group Names

Use the names of technologies and frameworks as titles for inspection groups, for example, Ant, Android, Java.

### Inspection Names

Limit a name by ~50 symbols so that it fits the line in the list of inspections in settings.

An inspection name should reflect the code problem that this inspection detects, for example:

![](correct.png){width=245}

Do not just describe the type of code that is being checked:

![](not-a-problem.png){width=613}

When describing a problem, prefer adjectives to complex nouns:

![](noun2.png){width=557}

![](noun.png){width=471}

Do not repeat the group titles in the inspection name:

![](inspections_group.png){width=557}

Do not use the word "problems" or other words that describe how inspections function in general (i.e. "check", "inspection", or "issue"). An inspection is supposed to check code for problems, so mentioning it in its name is not needed.

![](problem.png){width=613}

Do not use the words "warning" and "error" in inspection names. Such a name will become incorrect and misleading if the inspection severity level changes.

![](inspections_warning.png){width=613}

Do not use parentheses in inspection names. All supporting information can be moved to inspection descriptions.

![](parens.png){width=513}

If an inspection is powered by a third-party code quality tool, use the name of this tool in the inspection name.

![](third-party.png){width=245}

### Descriptions

Start descriptions with a verb, e.g. "Reports". Use "Run" for external tools. Do not begin a description with "This inspection".

![](start.png){width=520}

Provide details, such as:

* A quick-fix if applicable:

![](quick-fix.png){width=520}

* A link to the language or framework docs explaining the correct usage of the syntax or feature, if applicable.

![](inpsections_link.png){width=520}

* Two code samples: the code with a problem and the code that bypasses this problem. By comparing the good code and the bad code, users can work out what causes the problem and how to avoid it.
  For example, for <ui-path>Java | Probable bugs | 'equals()' called on array</ui-path> adding such an example helps to understand the inspection quicker:

![](inspections_example.png){width=520}

* Information from external validators.
  Prefer separating inspections so that a user can control them individually.
  If it is not possible, list all problems that an inspection can detect:

![](multiple.png){width=520}

Be very specific about the code problems that an inspection can detect:

![](vague.png){width=520}

Remove unnecessary words:

* Do not duplicate an inspection’s name in its description.

* Inspections highlight code problems, it's their primary functionality. Don't explain it in descriptions:

  ![](purpuse.png){width=520}

For text formatting, see [Description text](description_text.md).

### Editor messages

Describe the problem in the highlighted code in the current context.

![](editor-error-message.png){width=592 style=block}
*For example, Unresolved reference inspection provides the exact reference name which is unresolved.*

Use single quotes for the highlighted piece of code if it is referenced in the error message.

If a quick-fix is not available, describe in more detail a way to resolve the problem:

![](editor-error-message-detail.png){width=478}

### Quick-fixes

Describe the action that fixes the problem. For wording, follow the rules for [buttons](button.topic).

[//]: # (TODO: and [menu actions]&#40;menu.md&#41;.)

Use single quotes for the referenced piece of code.

![](quick-fix-menu.png){width=354}

### Capitalization

Use [sentence case](capitalization.md#sentence) for everything:

* Inspection names and descriptions
* Names of inspection groups and subgroups in settings
* Inspection texts in editor tooltips and quick-fixes

