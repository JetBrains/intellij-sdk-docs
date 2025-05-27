<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Custom Editors

<link-summary>Providing custom editors.</link-summary>

> See [](editor_components.md) for editors embedded in a custom UI.

A plugin can provide custom editors for certain files.

Examples include:

- preview for binary files (images (builtin), [PDF Viewer](https://plugins.jetbrains.com/plugin/14494-pdf-viewer))
- hex editor for binary files ([BinEd](https://plugins.jetbrains.com/plugin/9339-bined--binary-hex-editor))
- dedicated editor UI for specific filetypes ([Java JFR Profiler](https://plugins.jetbrains.com/plugin/20937-java-jfr-profiler), [Icon Font Viewer](https://plugins.jetbrains.com/plugin/19274-icon-font-viewer))
- using [](#editor-with-preview) to edit sources and preview simultaneously:
    - markup languages ([Markdown](https://www.jetbrains.com/help/idea/markdown.html), [AsciiDoc](https://plugins.jetbrains.com/plugin/7391-asciidoc))
    - SVG image files (builtin)
    - diagrams stored in text format ([Dot Support](https://plugins.jetbrains.com/plugin/27121-dot-support), [Camunda BPMN Editor](https://plugins.jetbrains.com/plugin/25710-camunda-bpmn-editor?))

## Editor Provider

Implement [`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileEditorProvider"/></include>.

> TODO ??? [`AsyncFileEditorProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileEditor/AsyncFileEditorProvider.kt)

Every provider must return its unique ID from `getEditorTypeId()`.
Set the same value for the `id` attribute in the extension registration in <path>plugin.xml</path>.

Return `true` from `accept(Project, VirtualFile)` if this provider can handle the given [Virtual File](virtual_file.md).

The actual [](#editor-implementation) instance is then created in `createEditor(Project, VirtualFile)`.
The platform will invoke `disposeEditor(FileEditor)` for editors created by this provider to perform any necessary cleanup (see [](disposers.md)).

To control the layout and keep/hide the default text editor, return the desired [`FileEditorPolicy`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorPolicy.java)
from `getPolicy()`.

The associated state for the editor (for example, scroll position) can be persisted and restored via `writeState()` and `loadState()`.

**Examples**:

- Platform [Image Editor](https://www.jetbrains.com/help/idea/view-images-in-ide.html): [`ImageFileEditorProvider`](%gh-ic%/images/src/org/intellij/images/editor/impl/ImageFileEditorProvider.java)
- [OSS plugins providing custom editors](https://jb.gg/ipe?extensions=com.intellij.fileEditorProvider)

## Editor Implementation

[`FileEditor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileEditor/FileEditor.java)

**Example**: Image Editor [`ImageFileEditorImpl`](%gh-ic%/images/src/org/intellij/images/editor/impl/ImageFileEditorImpl.java)

### Editor with Preview

To edit sources and preview simultaneously, use
[`TextEditorWithPreviewProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/TextEditorWithPreviewProvider.kt) for [](#editor-provider)
and [`TextEditorWithPreview`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/TextEditorWithPreview.kt) for the editor implementation.

**Example**: Markdown Editor [`MarkdownSplitEditorProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownSplitEditorProvider.kt) & [`MarkdownEditorWithPreview`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownEditorWithPreview.java)

<include from="snippets.topic" element-id="missingContent"/>
