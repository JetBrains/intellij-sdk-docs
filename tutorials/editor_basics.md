---
layout: editable
title: Basics of working with the Editor
---


[Source code](https://github.com/JetBrains/intellij-sdk/tree/master/code_samples/editor_basics)

----------

This tutorial will lead you through the series of steps showing how to work with the Editor in IntelliJ IDEA, how access and modify text it contains,
and how to handle events sent to the Editor.

* [1. Working With Text](editor_basics/working_with_text.html)
* [2. Editor coordinates system. Positions and offsets](editor_basics/coordinates_system.html)
* [3. Handling Editor Events](editor_basics/editor_events.html)

**Note:** The described part of the API allows to operate only with text.
If you need to access PSI please see
[PSI Cookbook](basics/psi_cookbook.html)
section.

**See also:**
[editor-ui-api package](https://github.com/JetBrains/intellij-community/tree/master/platform/editor-ui-api),
[Editor.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java),
[EditorImpl.java](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/editor/impl/EditorImpl.java).
[CommonDataKeys.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java),
[DataKey.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataKey.java),
[AnActionEvent](https://github.com/JetBrains/intellij-community/blob/ff16ce78a1e0ddb6e67fd1dbc6e6a597e20d483a/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java),
[DataContext](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)

**Related topics:**
[Action System](https://github.com/JetBrains/intellij-sdk/blob/master/tutorials/action_system/action_system.md)


