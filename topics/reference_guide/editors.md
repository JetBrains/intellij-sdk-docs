<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Editors

<link-summary>The Editors section overview.</link-summary>

This section covers working with text in the IntelliJ Platform editor.
* [](editor_basics.md)
* [](text_selection.md)
* [](multiple_carets.md)

## Editors FAQ

### How do I get the active editor instance?

Current select editor(s) can be queried from [`FileEditorManager`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManager.java).

From an [Action's `DataContext`](action_system.md#determining-the-action-context), use [`CommonDataKeys.EDITOR`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java).

For a given PSI Element, use [`PsiEditorUtil.findEditor()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/psi/util/PsiEditorUtil.java)

### How do I open an text editor for a "fake" file?

Create a [`LightVirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/testFramework/LightVirtualFile.java) with the contents
and open it via [`FileEditorManager.openFile()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManager.java).
