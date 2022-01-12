[//]: # (title: Basics of Working with the Editor)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial will lead you through the series of steps showing how to work with the IntelliJ Platform Editor, how to access and modify text it contains, and how to handle events sent to the editor.
* [1. Working With Text](working_with_text.md)
* [2. Editor coordinate systems: positions and offsets](coordinates_system.md)
* [3. Handling Editor Events](editor_events.md)

**Note:** The part of the API described in this tutorial only allows operations with text.
For operations that require access to the PSI please see the [PSI Cookbook](psi_cookbook.md) section.

**See also:**
The following are referenced in the tutorial:
* The [editor_basics](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/editor_basics/) plugin code sample,
* [editor-ui-api package](upsource:///platform/editor-ui-api),
* Those not found in editor-ui-api package:
  * [`EditorActionManager`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionManager.java),
  * [`EditorActionHandler`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java),
  * [`TypedActionHandler`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java),
  * [`TypedAction`](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java).

**Related topics:**
* [Action System](action_system.md)
* [Threading Issues](general_threading_rules.md)
