---
title: 1. Working with text
---

The following set of steps will show how to access a text selection and change it.


## 1.1. Pre-requirements

### 1.1.1 Creating a new action

In this example we access the editor from an action.
To create an action we need to extend the
[AnAction.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class.


```java
public class EditorIllustration extends AnAction {
}
```


### 1.1.2. Registering an action

To register the action we should add the corresponding tag to the `<actions>` section of the plugin configuration file
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/editor_basics/resources/META-INF/plugin.xml)


```xml
<actions>
    <action id="EditorBasics.EditorIllustration" class="EditorIllustration" text="Editor Basics"
        description="Illustrates how to plug an action in">
        <add-to-group group-id="EditorPopupMenu" anchor="last"/>
    </action>
</actions>
```

If an action is registered in the group EditorPopupMenu, like the sample above shows,
it will be available from the context menu when the focus is located in the editor.


### 1.1.3. Defining action's visibility

To determine conditions by which the action will be visible and available for being executed we need to override it's
*public void update(AnActionEvent e)* method.

```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
    }
}
```

If we want to work with a selected part of the text, it's reasonable to make the action available only when the following requirements are met:

* There is a project open
* There is an instance of the Editor available
* There is a text selection in the Editor

Further steps will show how to check these conditions through obtaining instances of Project and Editor and how to show or hide the action based on them.

## 1.2. Getting an instance of the active Editor

A reference to an instance of the Editor can be obtained by calling `getData(CommonDataKeys.EDITOR)`.
To obtain a project reference, we use the `getProject()` method.


```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        //Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        //Set visibility only in case of existing project and editor
        e.getPresentation().setVisible(project != null && editor != null);
    }
}
```

**Note:**

To access an Editor instance other ways can also be used:

* If [DataContext](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)
  object is available: `final Editor editor = CommonDataKeys.EDITOR.getData(context);`

* If only a `Project` is available, you can use `FileEditorManager.getInstance(project).getSelectedTextEditor()`


## 1.3. Obtaining a caret model and selection

After making sure we have a project open and an instance of the Editor we need to check if any selection is available and set action's visibility accordingly to these conditions.
[SelectionModel](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/SelectionModel.java)
accessed from the Editor allows to do it by calling its `hasSelection()` method.
Here's how our `update(AnActionEvent e)` method should look like at the end:


```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        //Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        //Set visibility only in case of existing project and editor and if some text in the editor is selected
        e.getPresentation().setVisible(project != null && editor != null &&
            editor.getSelectionModel().hasSelection());
    }
}
```

**Note:**
Editor allows to access different models of text representation. 
Model classes are located in
[editor](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor)
subpackage of the
[editor-ui-api](upsource:///platform/editor-ui-api)
package and include:

* [CaretModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java),
* [FoldingModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/FoldingModel.java),
* [IndentsModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/IndentsModel.java),
* [ScrollingModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
* [ScrollingModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
* [SoftWrapModel.java](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/SoftWrapModel.java)


## 1.4. Obtaining a Document

The action is visible and available now. 
In order to make it do something we need to override it's
`public void actionPerformed(AnActionEvent anActionEvent)` method.


```java
public class EditorIllustration extends AnAction {
    @Override
    public void update(AnActionEvent e) {
    //code here
    }
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    }
}
```

To modify the text an instance of the
[Document](upsource:///platform/core-api/src/com/intellij/openapi/editor/Document.java)
needs to be accessed. [Document](/basics/architectural_overview/documents.md) represents the contents of a text file loaded into memory, and possibly opened in an IDEA text editor.
The instance of a Document will be used later when a text replacement is performed.
We also need to figure out where the selected part of the text is located.


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

## 1.5. Modifying text

Generally replacement can be done by calling
`void replaceString(int startOffset, int endOffset, @NotNull CharSequence s);` of the Document, however,
the operation of replacement must be executed safely, this means the Document must be locked and
any changes should be performed under the [write action](upsource:///platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java)<!--#L172-->.
See the [Threading Issues](/basics/architectural_overview/general_threading_rules.md) section to learn more about synchronization issues and changes safety on the IntelliJ Platform.

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
    //Making the replacement
    WriteCommandAction.runWriteCommandAction(project, () ->
        document.replaceString(start, end, "Replacement")
    );
    selectionModel.removeSelection();
}
```

-----------

![String replacement action](img/basics.png)

-----------

The source code is located in
[EditorIllustration.java](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/editor_basics/src/org/jetbrains/tutorials/editor/basics/EditorIllustration.java).
To see how text replacement works, check out
[Editor Basics](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/editor_basics/src/org/jetbrains/tutorials/editor/basics/)
plugin, make the project, and run it, then invoke the *EditorIllustration* action which is available in the context menu of the editor.
