<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Editors

<link-summary>The Editors section overview/FAQ.</link-summary>

<tldr>

**Product Help**: [Editor basics](https://www.jetbrains.com/help/idea/using-code-editor.html)

</tldr>

This section covers working with text in the IntelliJ Platform editor:

* Tutorial: [](editor_basics.md)
    * [](working_with_text.md)
    * [](coordinates_system.md)
    * [](editor_events.md)
* [](text_selection.md)
* [](multiple_carets.md)

## Editors FAQ

### How do I get the active editor instance?

| Context                    | API                                                                                                                 |
|----------------------------|---------------------------------------------------------------------------------------------------------------------|
| Generic                    | [`FileEditorManager`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManager.java)     |
| [Action](action_system.md) | [`CommonDataKeys.EDITOR`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java) |
| [](psi_elements.md)        | [`PsiEditorUtil.findEditor()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/psi/util/PsiEditorUtil.java)         |

### How can I be notified about editor events?

Use project-level listener [`FileEditorManagerListener`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java)
or [`FileEditorManagerListener$Before`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManagerListener.java)
to be notified about all file open/closed/selection changed events.

### How can I set a custom editor tab name and tooltip?

[`EditorTabTitleProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.kt) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.editorTabTitleProvider"/></include> allows
for specifying custom names and tooltips displayed in the title of editor tabs.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

### How can I set a custom editor tab background color?

[`EditorTabColorProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.editorTabColorProvider"/></include> allows
for the modification of the background color for specific files.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

### How do I open a text editor for a "fake" file?

Create a [`LightVirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/testFramework/LightVirtualFile.java) with the contents
and open it via [`FileEditorManager.openFile()`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorManager.java).
