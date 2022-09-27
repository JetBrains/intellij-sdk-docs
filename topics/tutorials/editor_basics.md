[//]: # (title: Basics of Working with the Editor)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial will lead you through a series of steps showing how to work with the IntelliJ Platform Editor, how to access and modify text it contains, and how to handle events sent to the editor.
* [](working_with_text.md)
* [](coordinates_system.md)
* [](editor_events.md)

> The part of the API described in this tutorial only allows operations with "plain text".
> For operations that require access to the PSI, please see [](psi.md).

The following are referenced in the tutorial:
* The [editor_basics](%gh-sdk-samples%/editor_basics/) plugin code sample,
* [editor-ui-api package](%gh-ic%/platform/editor-ui-api),
* Those not found in `editor-ui-api` package:
  * [`EditorActionManager`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionManager.java),
  * [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java),
  * [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java),
  * [`TypedAction`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java).

**Related topics:**
* [](basic_action_system.md)
* [](general_threading_rules.md)
