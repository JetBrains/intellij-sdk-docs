---
layout: editable
title: 2. Editor coordinates system. Positions and offsets
---


Every caret in the editor has a set of properties describing it's coordinates. 
These properties can be accessed by obtaining a
[caret model instance](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java).
Working with caret positions and it's logical and visual properties will be explained in the sample below.

## 2.1. Pre-requirements
Access to the Editor is performed through an action.

## 2.2. Accessing caret positions

To get an access to caret positions an instance of CaretModel should be obtained.

```java
public class EditorAreaIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
    }
    @Override
    public void update(AnActionEvent e) {
       //...
    }
}
```

## 2.3. Logical position

[LogicalPosition.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/LogicalPosition.java)
represents a line and a column of the current logical position of the caret. Logical positions ignore folding -
for example, if the top 10 lines of the document are folded, the 10th line in the document will have the line number 10 in its logical position.

```java
public class EditorAreaIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        LogicalPosition logicalPosition = caretModel.getLogicalPosition();
    }
    @Override
    public void update(AnActionEvent e) {
        //...
    }
}
```

Logical position may store additional parameters that define its mapping to
[VisualPosition.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java).
Rationale is that single logical pair matches soft wrap-introduced virtual space, i.e. different visual positions
correspond to the same logical position. It's convenient to store exact visual location details within the logical
position in order to relief further 'logical position' -> 'visual position' mapping.

## 2.4. Visual position

[VisualPosition.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java)
represent a visual position and may very from the corresponding logical position.
Visual positions take folding into account - for example,
if the top 10 lines of the document are folded, the 10th line in the document will have the line number 1 in its visual position.

```java
public class EditorAreaIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        LogicalPosition logicalPosition = caretModel.getLogicalPosition();
        VisualPosition visualPosition = caretModel.getVisualPosition();
    }
    @Override
    public void update(AnActionEvent e) {
        //...
    }
}
```

## 2.5. Offset

An absolute offset for a given caret position is accessible through CaretModel as well

```java
public class EditorAreaIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        LogicalPosition logicalPosition = caretModel.getLogicalPosition();
        VisualPosition visualPosition = caretModel.getVisualPosition();
        int offset = caretModel.getOffset();
    }
    @Override
    public void update(AnActionEvent e) {
        //...
    }
}
```

## 2.6. Displaying position values
To display the actual values of logical anf visual positions we add an
```Messages.showInfoMessage()``` call that will show them in form of notification after the action is performed.

```java
public class EditorAreaIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        LogicalPosition logicalPosition = caretModel.getLogicalPosition();
        VisualPosition visualPosition = caretModel.getVisualPosition();
        int offset = caretModel.getOffset();
        Messages.showInfoMessage(logicalPosition.toString() + "\n" +
                        visualPosition.toString() + "\n" +
                        "Offset: " + offset, "Caret Parameters Inside The Editor");
    }
    @Override
    public void update(AnActionEvent e) {
           //...
    }
}
```

Check out, compile, and run the
[Editor Basics Plugin](https://github.com/JetBrains/intellij-sdk/tree/master/code_samples/editor_basics),
then move carets, invoke
[EditorAreaIllustration](https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/editor_basics/src/org/jetbrains/plugins/editor/basics/EditorAreaIllustration.java)
action, and see how logical and visual positions are related dependently on folding.

Find the action in the context menu:
![Show coordinates action](img/coordinates_action.png)

Perform the action to see caret positions:
![Show coordinates action](img/coordinates_demo.png)

--------


# Actions activated by editor events
IntelliJ IDEA SDK provides a set of embedded mechanisms for handling events related to the Editor.

## Handling keystrokes in the Editor
To handle keystrokes and provide custom reactions interface
[TypedActionHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java)
may be used.
Series of steps below shows how to change standard behaviour of the editor and make it react on typing differently instead of simply displaying a typed character in the editor area.

### Implementing *TypedActionHandler*
First we need to implement an instance of
[TypedActionHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java):

```java
public class MyTypedHandler implements TypedActionHandler {
    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
    }
}
```

### Implementing logic for handling keystrokes
```public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext);```
method should contain the main logical part for handling keystrokes. It will be called every time a key is pressed.
In the following example our typed handler is meant insert a string at the zero offset in the editor after a keystroke occurs:

```java
public class MyTypedHandler implements TypedActionHandler {
    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        final Document document = editor.getDocument();
        Project project = editor.getProject();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                document.insertString(0, "Typed\n");
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }
}
```

### Setting up *TypedActionHandler*

To enable a custom implementation of *TypedActionHandler* in the plugin we need to create a new instance of it and pass to
```public TypedActionHandler setupHandler(TypedActionHandler handler);``` method of the
[TypedAction](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java)
class. By doing it we replace the typing handler with the specified handler.

```java
public class EditorIllustration extends AnAction {
    static {
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        typedAction.setupHandler(new MyTypedHandler());
    }
}
```

After compiling and running the code snippet above typing in the editor will be handled with inserting an extra string at the 0 position.

## Working with EditorActionHandler
Class
[EditorActionHandler.java](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
stays for actions activated by keystrokes in the editor.
Series of steps below show how access *EditorActionManager* and pass it actions to be executed.
In this example we will use *EditorActionHandler* to insert one extra caret above the current caret if available.

### Prerequirements
Create an action:

```java
public class EditorHandlerIllustration extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    }
    @Override
    public void update(@NotNull final AnActionEvent anActionEvent) {
    }
}
```

Register action in
[plugin.xml](https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/editor_basics/resources/META-INF/plugin.xml):

```xml
<actions>
    <action id="EditorBasics.EditorHandlerIllustration" class="org.jetbrains.tutorials.editor.basics.EditorHandlerIllustration" text="Editor Handler"
            description="Illustrates how to plug an action in">
      <add-to-group group-id="EditorPopupMenu" anchor="first"/>
    </action>
</action>
```

### Setting visibility
Our action should be visible only in case if the following conditions are met:
there's a project open, there's an editor available, and there's at least one caret active in the editor:

```java
public class EditorHandlerIllustration extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    }
    @Override
    public void update(@NotNull final AnActionEvent anActionEvent) {
        final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
        final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        anActionEvent.getPresentation().setVisible((project != null && editor != null && !editor.getCaretModel().getAllCarets().isEmpty()));
    }
}
```

### Obtaining *EditorActionHandler*

To manipulate with standard Editor's actions first we need to obtain
an instance of
[EditorActionHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
for the action we'd like to work with. Ih this case it will be an instance of
[CloneCaretActionHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/editor/actions/CloneCaretActionHandler.java).

```java
public class EditorHandlerIllustration extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        EditorActionManager actionManager = EditorActionManager.getInstance();
        EditorActionHandler actionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
    }
    @Override
    public void update(@NotNull final AnActionEvent anActionEvent) {
        //...
    }
}
```

### Making *EditorActionHandler* execute actions
To execute an action we need to call the ```public final void execute(@NotNull Editor editor, @Nullable final Caret contextCaret, final DataContext dataContext);```
method of a corresponding *EditorActionHandler*

```java
public class EditorHandlerIllustration extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        EditorActionManager actionManager = EditorActionManager.getInstance();
        EditorActionHandler actionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
        actionHandler.execute(editor, editor.getCaretModel().getCurrentCaret(), anActionEvent.getDataContext());
    }
    @Override
    public void update(@NotNull final AnActionEvent anActionEvent) {
        //
    }
}
```

After compiling and running the following code sample, one extra caret will be placed in the editor below the current active caret.

-----------

### [Source code](https://github.com/JetBrains/intellij-sdk/tree/master/code_samples/editor_basics)
-----------

Note, that this part of the API allows to operate only with text.
If you need to access PSI please see
[PSI Cookbook](https://confluence.jetbrains.com/display/IDEADEV/PSI+Cookbook)
section.

**See also**
[editor-ui-api package](https://github.com/JetBrains/intellij-community/tree/master/platform/editor-ui-api),
[Editor.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java),
[EditorImpl.java](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/editor/impl/EditorImpl.java).
[CommonDataKeys.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java),
[DataKey.java](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataKey.java),
[AnActionEvent](https://github.com/JetBrains/intellij-community/blob/ff16ce78a1e0ddb6e67fd1dbc6e6a597e20d483a/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java),
[DataContext](https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)

**Related topics**
[Action System](https://github.com/JetBrains/intellij-sdk/blob/master/tutorials/action_system/action_system.md)





