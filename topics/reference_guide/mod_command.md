<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# ModCommand

<link-summary>ModCommand API allows executing declarative transparent commands.</link-summary>

> Page is under construction.
>
{style="warning"}

[`ModCommand`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModCommand.java)
is a declarative transparent command, which modifies the project/workspace state or produces a user interaction.

It is a sealed hierarchy, so things which can be done with `ModCommand`s are limited.

> This API is available since 2024.1.
>
{style="warning"}

## `ModCommand` Implementations

> In most cases, it’s unnecessary to create `ModCommand` instances directly.
> Instead, the static factory methods listed below from `ModCommand` are used.
>
{style="tip"}

### Generic

#### `ModNothing`

Do nothing.

`ModCommand.nop()`

[`ModNothing`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModNothing.java)

#### `ModCompositeCommand`

Do several things sequentially (collection of `ModCommand`s).

`ModCommand.andThen()`

[`ModCompositeCommand`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModCompositeCommand.java)

### File Modification

#### `ModUpdateFileText`

Update the contents of a single [PSI file](psi_files.md).

[`ModUpdateFileText`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModUpdateFileText.java)

#### `ModCreateFile`

Create a new file with specified contents.

[`ModCreateFile`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModCreateFile.java)

#### `ModDeleteFile`

Delete file.

[`ModDeleteFile`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModDeleteFile.java)

### User Interface

#### `ModDisplayMessage`

Show a localized message (information or error).

`ModCommand.info()`, `ModCommand.error()`

[`ModDisplayMessage`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModDisplayMessage.java)

#### `ModChooseAction`

Display a chooser that allows the user to choose an action to execute.

`ModCommand.chooseAction()`

[`ModChooseAction`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModChooseAction.java)

### Code

#### `ModNavigate`

Change caret position and selection in the editor that edits the specified file. May assume opening a new editor.

`ModCommand.moveTo()`, `ModCommand.select()`

[`ModNavigate`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModNavigate.java)

#### `ModHighlight`

Add highlighting(s) to the specified ranges of specified file.

`ModCommand.highlight()`

[`ModHighlight`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModHighlight.java)

#### `ModChooseMember`

Display a chooser dialog that allows user to select one or several members and invoke a next step based on the choice.

`ModCommand.chooseMultipleMembers()`

[`ModChooseMember`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModChooseMember.java)

#### `ModShowConflicts`

Display a dialog with conflicts allowing to ignore them and continue the next step execution.

`ModCommand.showConflicts()`

[`ModShowConflicts`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModShowConflicts.java)

#### `ModStartTemplate`

Start a template in the editor (limited functionality for now).
[`ModStartTemplate`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModStartTemplate.java)

#### `ModStartRename`

Create a template to perform a rename of a symbol, along with its occurrences.

[`ModStartRename`](%gh-ic-master%/platform/analysis-api/src/com/intellij/modcommand/ModStartRename.java)

#### `ModUpdateReferences`

Update references to the changed declaration via ‘Suggested refactoring’ functionality.

[`ModUpdateReferences`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModUpdateReferences.java)

### IDE/System

#### `ModCopyToClipboard`

Copy a string to the OS clipboard.

`ModCommand.copyToClipboard()`

[`ModCopyToClipboard`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModCopyToClipboard.java)

#### `ModUpdateSystemOptions`

Update IDE/project option(s), notable inspection profile settings.

`ModCommand.updateOption()`, `ModCommand.updateOptionList()`

[`ModUpdateSystemOptions`](%gh-ic-master%/platform/analysis-api/src/com/intellij/modcommand/ModUpdateSystemOptions.java)

## `ModCommand.psiUpdate()`

The most powerful command is `ModCommand.psiUpdate()`.
Most of the `ModCommand`s one may want to create can be done with it.
Notable exceptions are [](#modchooseaction), [](#modchoosemember) and [](#modshowconflicts).

It does the following:

- Create a non-physical copy of the file where the specified element is located
- Call the function that accepts a non-physical copy of the input element
- Track all the changes inside that non-physical copy
- Create an appropriate command to perform the changes

### `ModPsiUpdater`

It also optionally provides [`ModPsiUpdater`](%gh-ic%/platform/analysis-api/src/com/intellij/modcommand/ModPsiUpdater.java) service object,
which can be used to perform more interesting tasks:

- Request more non-physical element copies (`getWritable()`), including copies from different files (the composite command to update several files will be created).
  It's also possible to use `getWritable()` for `PsiDirectory`; currently, it allows only creating new files.
- Move caret (`moveCaretTo()`), select elements and text ranges (`select()`), highlight elements (`highlight()`). Positions will be automatically adjusted if more changes are performed after that.
- Start renaming the symbol (`rename()`)
- Show conflicts (`showConflicts()`)
- Create a template (`templateBuilder()`)
- Display an information message (`message()`)
- Cancel all the changes and display an error message (`cancel()`)

<include from="snippets.md" element-id="missingContent"/>
