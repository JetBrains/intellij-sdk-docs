---
title: 1. Working with text
---

The following set of steps will show how to access a text selection and change it.

## 1.1. Introduction
This tutorial relies heavily on the [editor_basics](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/editor_basics/) plugin code sample from the IntelliJ Platform SDK.
It may be helpful to open that project in an IntelliJ Platform-based IDE, build the project, run it, select some text in the editor, and invoke the "Editor Replace Text" menu item on the editor context menu.

-----------

![String replacement action](img/basics.png){:width="600px"}

-----------

### 1.1.1 Creating a New action
The source code for the Java class in this example is [EditorIllustration.java](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/editor_basics/src/main/java/org/intellij/sdk/editor/EditorIllustration.java).

In this example, we access the editor from an action.
To create an action we need to extend the [AnAction.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java) class.
```java
public class EditorIllustration extends AnAction {
}
```

### 1.1.2. Registering an Action
To register the action, we should add the corresponding elements to the `<actions>` section of the plugin configuration file
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/editor_basics/src/main/resources/META-INF/plugin.xml)
```xml
    <action id="EditorBasics.EditorIllustration"
            class="org.intellij.sdk.editor.EditorIllustration"
            text="Editor Replace Text"
            description="Replaces selected text with 'Replacement'."
            icon="EditorBasicsIcons.Sdk_default_icon">
      <add-to-group group-id="EditorPopupMenu" anchor="first"/>
    </action>
```

If an action is registered in the group `EditorPopupMenu`, as the sample above shows,
it will be available from the context menu when the focus is located in the editor.

### 1.1.3. Defining Action's Visibility
To determine conditions by which the action will be visible and available for being executed we need to override its
`public void update(AnActionEvent e)` method.
```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
    }
}
```

If we want to work with a selected part of the text, it's reasonable to make the action available only when the following requirements are met:
* There is a [Project](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) object,
* There is an instance of [Editor](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java) available,
* There is a text selection in `Editor`.

Further steps will show how to check these conditions through obtaining instances of `Project` and `Editor` objects, and how to show or hide the action's menu items based on them.

## 1.2. Getting an Instance of the Active Editor from an Event
Using the [AnActionEvent](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java) event passed into the `update` method, a reference to an instance of the editor can be obtained by calling `getData(CommonDataKeys.EDITOR)`.
Similarly, to obtain a project reference, we use the `getProject()` method.
```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        //Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        //Set visibility only in case of existing project and editor (for now, selection is added below)
        e.getPresentation().setVisible(project != null && editor != null);
    }
}
```

**Note:**  
There are other ways to access an `Editor` instance:
* If a [DataContext](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java) object is available: `final Editor editor = CommonDataKeys.EDITOR.getData(context);`
* If only a `Project` object is available, use `FileEditorManager.getInstance(project).getSelectedTextEditor()`


## 1.3. Obtaining a Caret Model and Selection
After making sure a project is open and an instance of the editor is obtained, we need to check if any selection is available.
The [SelectionModel](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/SelectionModel.java) interface is accessed from the `Editor` object.
Determining whether some text is selected is accomplished by calling the `hasSelection()` method.
Here's how our `update(AnActionEvent e)` method should look like in the end:

```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        //Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        //Set visibility only in case of the existence of a project, editor, and if text is selected in the editor
        e.getPresentation().setVisible( project != null 
                                          && editor != null 
                                          && editor.getSelectionModel().hasSelection() );
    }
}
```

**Note:**
`Editor` also allows access to different models of text representation. 
The model classes are located in [editor](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor), and include:
* [CaretModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java),
* [FoldingModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/FoldingModel.java),
* [IndentsModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/IndentsModel.java),
* [ScrollingModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
* [SoftWrapModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/SoftWrapModel.java)


## 1.4. Obtaining the Document
The `EditorIllustration` action menu item is visible and available now. 
To make it do something we need to override its `actionPerformed()` method.
```java
public class EditorIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    }
}
```

Modifying the text requires an instance of the [Document](upsource:///platform/core-api/src/com/intellij/openapi/editor/Document.java) object, which is accessed from the `Editor` object. 
The [Document](/basics/architectural_overview/documents.md) represents the contents of a text file loaded into memory and possibly opened in an IDEA text editor.
The instance of a Document will be used later when a text replacement is performed.
We also need to figure out where the selected part of the text is located in the document.
```java
@Override
public void actionPerformed(final AnActionEvent e) {
    //Get all the required data from data keys
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    final Project project = e.getProject();
    //Access document, caret, and selection
    final Document document = editor.getDocument();
    final SelectionModel selectionModel = editor.getSelectionModel();
    final int start = selectionModel.getSelectionStart();
    final int end = selectionModel.getSelectionEnd();
}
```

## 1.5. Modifying Text
Generally, text replacement can be done by calling the `Document` object's `replaceString()` method. 
However, safely replacing the text requires the Document to be locked and any changes performed in a [write action](upsource:///platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java)<!--#L172-->.
See the [Threading Issues](/basics/architectural_overview/general_threading_rules.md) section to learn more about synchronization issues and changes safety on the IntelliJ Platform.
This example changes the document within a `WriteCommandAction`.
```java
@Override
public void actionPerformed(final AnActionEvent e) {
    //Get all the required data from data keys
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    final Project project = e.getProject();
    //Access document, caret, and selection
    final Document document = editor.getDocument();
     // Get information about the selection
    final SelectionModel selectionModel = editor.getSelectionModel();
    final int start = selectionModel.getSelectionStart();
    final int end = selectionModel.getSelectionEnd();
    //Make the replacement
    WriteCommandAction.runWriteCommandAction(project, () ->
        document.replaceString(start, end, "Replacement")
    );
    // Deselect the replaced text
    selectionModel.removeSelection();
}
```


