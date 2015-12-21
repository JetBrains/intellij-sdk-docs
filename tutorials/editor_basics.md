---
title: Basics of working with the Editor
---


[Source code](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/editor_basics)

----------

This tutorial will lead you through the series of steps showing how to work with the IntelliJ Platform Editor, how access and modify text it contains,
and how to handle events sent to the Editor.

* [1. Working With Text](editor_basics/working_with_text.md)
* [2. Editor coordinates system. Positions and offsets](editor_basics/coordinates_system.md)
* [3. Handling Editor Events](editor_basics/editor_events.md)

**Note:** The described part of the API allows to operate only with text.
If you need to access PSI please see
[PSI Cookbook](/basics/psi_cookbook.md)
section.

**See also:**
[editor-ui-api package](upsource:///platform/editor-ui-api),
[Editor.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java),
[EditorImpl.java](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/impl/EditorImpl.java).
[CommonDataKeys.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java),
[DataKey.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataKey.java),
[AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java),
[DataContext](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)

**Related topics:**
[Action System](/tutorials/action_system.md)


