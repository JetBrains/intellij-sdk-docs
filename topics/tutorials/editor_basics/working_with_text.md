<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 1. Working with Text

<link-summary>Tutorial demonstrating how to retrieve and replace text in the editor.</link-summary>

This tutorial shows how to use actions to access a caret placed in a document open in an editor.
Using information about the caret, replace selected text in a document with a string.

The approach in this tutorial relies heavily on creating and registering actions.
To review the fundamentals of creating and registering actions, refer to [](action_system.md) tutorial.

Multiple examples are used from the [editor_basics](%gh-sdk-samples-master%/editor_basics) plugin code sample from the IntelliJ Platform SDK.
It may be helpful to open that project in an IntelliJ Platform-based IDE, build the project, run it, select some text in the editor, and invoke the **Editor Replace Text** menu item on the editor context menu.

![Editor Basics Menu](basics.png){width="600"}

## Creating a New Menu Action

In this example, we access the `Editor` from an action.
The source code for the Java class in this example is [EditorIllustrationAction](%gh-sdk-samples-master%/editor_basics/src/main/java/org/intellij/sdk/editor/EditorIllustrationAction.java).

To register the action, we must add the corresponding elements to the [`<actions>`](plugin_configuration_file.md#idea-plugin__actions) section of the plugin configuration file [plugin.xml](%gh-sdk-samples-master%/editor_basics/src/main/resources/META-INF/plugin.xml).
For more information, refer to the [](creating_actions_tutorial.md#registering-a-custom-action) section.
The `EditorIllustrationAction` action is registered in the group `EditorPopupMenu` so it will be available from the context menu when focus is on the editor:

```xml
<action
    id="EditorBasics.EditorIllustrationAction"
    class="org.intellij.sdk.editor.EditorIllustrationAction"
    text="Editor Replace Text"
    description="Replaces selected text with 'Replacement'."
    icon="SdkIcons.Sdk_default_icon">
  <add-to-group group-id="EditorPopupMenu" anchor="first"/>
</action>
```

## Defining the Menu Action's Visibility

To determine conditions by which the action will be visible and available requires `EditorIllustrationAction` to override the `AnAction.update()` method.
For more information, refer to [](action_system.md#overriding-the-anactionupdate-method).

To work with a selected part of the text, it's reasonable to make the menu action available only when the following requirements are met:
* There is a [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java) object,
* There is an instance of [`Editor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java) available,
* There is a text selection in `Editor`.

Additional steps will show how to check these conditions through obtaining instances of `Project` and `Editor` objects, and how to show or hide the action's menu items based on them.

### Getting an Instance of the Active Editor from an Action Event

Using the [`AnActionEvent`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) event passed into the `update` method, a reference to an instance of the `Editor` can be obtained by calling `getData(CommonDataKeys.EDITOR)`.
Similarly, to obtain a project reference, we use the `getProject()` method.

```java
public class EditorIllustrationAction extends AnAction {
  @Override
  public void update(@NotNull AnActionEvent event) {
    // Get required data keys
    Project project = event.getProject();
    Editor editor = event.getData(CommonDataKeys.EDITOR);
    // ...
  }
}
```

**Note:**
There are other ways to access an `Editor` instance:
* If a [`DataContext`](%gh-ic%/platform/core-ui/src/openapi/actionSystem/DataContext.java) object is available: `CommonDataKeys.EDITOR.getData(context);`
* If only a `Project` object is available, use `FileEditorManager.getInstance(project).getSelectedTextEditor()`

### Obtaining a Caret Model and Selection

After making sure a project is open, and an instance of the `Editor` is obtained, we need to check if any selection is available.
The [`SelectionModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/SelectionModel.java) interface is accessed from the `Editor` object.
Determining whether some text is selected is accomplished by calling the `SelectionModel.hasSelection()` method.
Here's how the `EditorIllustrationAction.update(AnActionEvent event)` method should look:

```java
public class EditorIllustrationAction extends AnAction {
  @Override
  public void update(@NotNull AnActionEvent event) {
    // Get required data keys
    Project project = event.getProject();
    Editor editor = event.getData(CommonDataKeys.EDITOR);

    // Set visibility only in the case of
    // existing project editor, and selection
    event.getPresentation().setEnabledAndVisible(project != null
        && editor != null && editor.getSelectionModel().hasSelection());
  }
}
```

**Note:**
`Editor` also allows access to different models of text representation.
The model classes are located in [editor](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor), and include:
* [`CaretModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java),
* [`FoldingModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/FoldingModel.java),
* [`IndentsModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/IndentsModel.java),
* [`ScrollingModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
* [`SoftWrapModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/SoftWrapModel.java)

## Safely Replacing Selected Text in the Document

Based on the evaluation of conditions by `EditorIllustrationAction.update()`, the `EditorIllustrationAction` action menu item is visible.
To make the menu item do something, the `EditorIllustrationAction` class must override the `AnAction.actionPerformed()` method.
As explained below, this will require the `EditorIllustrationAction.actionPerformed()` method to:
* Gain access to the document.
* Get the character locations defining the selection.
* Safely replace the contents of the selection.

Modifying the selected text requires an instance of the [`Document`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/Document.java) object, which is accessed from the `Editor` object.
The [Document](documents.md) represents the contents of a text file loaded into memory and opened in an IntelliJ Platform-based IDE editor.
An instance of the `Document` will be used later when a text replacement is performed.

The text replacement will also require information about where the selection is in the document, which is provided by the primary `Caret` object, obtained from the `CaretModel`.
Selection information is measured in terms of [Offset](coordinates_system.md#caret-offset), the count of characters from the beginning of the document to a caret location.

Text replacement could be done by calling the `Document` object's `replaceString()` method.
However, safely replacing the text requires the `Document` to be locked and any changes performed in a write action.
See the [Threading Issues](threading_model.md) section to learn more about synchronization issues and changes safety on the IntelliJ Platform.
This example changes the document within a [`WriteCommandAction`](%gh-ic%/platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java).

The complete `EditorIllustrationAction.actionPerformed()` method is shown below:
* Note the selection in the document is replaced by a string using a method on the `Document` object, but the method call is wrapped in a write action.
* After the document change, the new text is de-selected by a call to the primary caret.

```java
public class EditorIllustrationAction extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    // Get all the required data from data keys
    Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
    Project project = event.getRequiredData(CommonDataKeys.PROJECT);
    Document document = editor.getDocument();

    // Work off of the primary caret to get the selection info
    Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
    int start = primaryCaret.getSelectionStart();
    int end = primaryCaret.getSelectionEnd();

    // Replace the selection with a fixed string.
    // Must do this document change in a write action context.
    WriteCommandAction.runWriteCommandAction(project, () ->
        document.replaceString(start, end, "editor_basics")
    );

    // De-select the text range that was just replaced
    primaryCaret.removeSelection();
  }
}
```
