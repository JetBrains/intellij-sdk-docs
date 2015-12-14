---
title: 3. Handling Editor Events
---

The following set of tutorials is meant to be an introduction to actions activated by editor events.
The IntelliJ Platform SDK provides a set of callbacks for handling events related to the Editor.

[Source code](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/editor_basics)


## 3.1. Handling keystrokes in the Editor

To handle keystrokes and provide custom reactions interface
[TypedActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java)
may be used.
Series of steps below shows how to change standard behaviour of the editor and make it react on typing differently instead of simply displaying a typed character in the editor area.

### 3.1.2 Implementing *TypedActionHandler*

First we need to implement an instance of
[TypedActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java):

```java
public class MyTypedHandler implements TypedActionHandler {
    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
    }
}
```

### 3.1.3. Implementing logic for handling keystrokes

`public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext);`

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

### 3.1.4. Setting up *TypedActionHandler*

To enable a custom implementation of *TypedActionHandler* in the plugin we need to create a new instance of it and pass to
`public TypedActionHandler setupHandler(TypedActionHandler handler);` method of the
[TypedAction](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java)
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

## 3.2. Working with EditorActionHandler

Class
[EditorActionHandler.java](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
stays for actions activated by keystrokes in the editor.
Series of steps below show how access *EditorActionManager* and pass it actions to be executed.
In this example we will use *EditorActionHandler* to insert one extra caret above the current caret if available.

### 3.2.1. Pre-requirements

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
[plugin.xml](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/editor_basics/resources/META-INF/plugin.xml):

```xml
<actions>
    <action id="EditorBasics.EditorHandlerIllustration" class="org.jetbrains.tutorials.editor.basics.EditorHandlerIllustration" text="Editor Handler"
            description="Illustrates how to plug an action in">
      <add-to-group group-id="EditorPopupMenu" anchor="first"/>
    </action>
</action>
```

### 3.2.2. Setting visibility

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

### 3.2.3. Obtaining *EditorActionHandler*

To manipulate with standard Editor's actions first we need to obtain
an instance of
[EditorActionHandler](upsource:///platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
for the action we'd like to work with. Ih this case it will be an instance of
[CloneCaretActionHandler](upsource:///platform/platform-impl/src/com/intellij/openapi/editor/actions/CloneCaretActionHandler.java).

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

### 3.2.4. Making *EditorActionHandler* execute actions

To execute an action we need to call the `public final void execute(@NotNull Editor editor, @Nullable final Caret contextCaret, final DataContext dataContext);`
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

[Source code](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/editor_basics)
