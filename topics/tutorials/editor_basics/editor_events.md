<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 3. Handling Editor Events

<link-summary>Introduction to Editor Action system handling actions activated by keystroke events.</link-summary>

The previous tutorial [Editor Coordinate Systems](coordinates_system.md) described working with caret coordinate systems in an editor window.
Caret position was discussed in terms of Logical Position, Visual Position, and Offset.
This tutorial introduces the Editor Action system, which handles actions activated by keystroke events in the editor.
Two classes from the [editor_basics](%gh-sdk-samples-master%/editor_basics) code sample are used to illustrate:
* Using an IntelliJ Platform [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java) to manipulate a caret.
* Creating and registering a custom [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) to intercept keystrokes and change the document.

## Using an IntelliJ Platform `EditorActionHandler`

In this portion of the tutorial, the [editor_basics](%gh-sdk-samples-master%/editor_basics) code sample is used to demonstrate cloning an existing caret.
A custom action class will use `EditorActionManager` to access a specific `EditorActionHandler` for caret cloning.
The `editor_basics` code sample adds an **Editor Add Caret** menu item to the editor context menu:

![Editor Basics Menu](basics.png){width="600"}

### Creating the Menu Action Class

The source code for the Java action class is [EditorHandlerIllustration](%gh-sdk-samples-master%/editor_basics/src/main/java/org/intellij/sdk/editor/EditorHandlerIllustration.java), a subclass of `AnAction`.
For more information about creating action classes, see [](action_system.md), which covers the topic in depth.

The `EditorHandlerIllustration` action is registered in the _editor_basic_ [`plugin.xml`](%gh-sdk-samples-master%/editor_basics/src/main/resources/META-INF/plugin.xml) file.
Note that this action class is registered to appear on the Editor context menu.

```xml
<actions>
  <action
      id="EditorBasics.EditorHandlerIllustration"
      class="org.intellij.sdk.editor.EditorHandlerIllustration"
      text="Editor Add Caret"
      description="Adds a second caret below the existing one."
      icon="SdkIcons.Sdk_default_icon">
    <add-to-group group-id="EditorPopupMenu" anchor="first"/>
  </action>
</action>
```

### Setting Visibility for the Action Menu Entry

Under what conditions should the `EditorHandlerIllustration` action be capable of cloning a caret?
Only if the following conditions are met in the `EditorHandlerIllustration.update()` method:
* A project is open,
* An editor is available,
* There is at least one caret active in the editor.

After ensuring that `Project` and `Editor` objects are available, the `Editor` object is used to verify there is at least one caret:

```java
public class EditorHandlerIllustration extends AnAction {
  @Override
  public void update(@NotNull AnActionEvent event) {
    Project project = event.getProject();
    Editor editor = event.getData(CommonDataKeys.EDITOR);

    // Make sure at least one caret is available
    boolean menuAllowed = false;
    if (editor != null && project != null) {
      // Ensure the list of carets in the editor is not empty
      menuAllowed = !editor.getCaretModel().getAllCarets().isEmpty();
    }
    event.getPresentation().setEnabledAndVisible(menuAllowed);
  }
}
```

### Acquiring the Correct `EditorActionHandler`

When the `EditorHandlerIllustration.actionPerformed()` method clones the caret, it should use the appropriate IntelliJ Platform [`EditorActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java).
An instance of [`EditorActionManager`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionManager.java) is required to obtain the correct `EditorActionHandler`.
The `EditorActionManager` class provides a static method to do this.

To request the correct `EditorActionHandler` from `EditorActionManager`, consult the [`IdeActions`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/actionSystem/IdeActions.java) interface for the correct constant to pass into the `EditorActionManager.getActionHandler()` method.
For cloning a caret below the primary caret, the constant is `ACTION_EDITOR_CLONE_CARET_BELOW`.
Based on that constant, the `EditorActionManager` returns an instance of [`CloneCaretActionHandler`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/actions/CloneCaretActionHandler.java), a subclass of `EditorActionHandler`.

```java
// Snippet from EditorHandlerIllustration.actionPerformed()
EditorActionManager actionManager = EditorActionManager.getInstance();
EditorActionHandler actionHandler =
    actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
```

### Using an `EditorActionHandler` to Clone the Caret

To clone the caret requires only calling the `EditorActionHandler.execute()` method and passing in the appropriate context.

```java
public class EditorHandlerIllustration extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
    EditorActionManager actionManager = EditorActionManager.getInstance();
    EditorActionHandler actionHandler =
        actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
    actionHandler.execute(editor,
        editor.getCaretModel().getPrimaryCaret(), event.getDataContext());
  }
}
```

## Creating a Custom `TypedActionHandler`

The [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java) interface is the basis for classes that handle keystroke events from the editor.
Custom implementations of the class are registered to handle editor keystroke events, and receive a callback for each keystroke.
The steps below explain how to use `TypedActionHandler` to customize the behavior of the editor when keystroke events are received.

### Implementing a Custom `TypedActionHandler` Class

First, a subclass such as `MyTypedHandler` is created based on [`TypedActionHandler`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java).
The class overrides the method `TypedActionHandler.execute()`, which is the callback for editor keystroke events.

### Implementing the Keystroke Event Handling Logic

Override the `TypedActionHandler.execute()` method in `MyTypedHandler` to implement the logic for handling keystroke events.
This method is called every time a key is pressed when the Editor Tool Window has focus.

In the following example, the `MyTypedHandler.execute()` method inserts "editor_basics\n" at the zero [caret Offset](coordinates_system.md#caret-offset) position when a keystroke event occurs.
As explained in [Working with Text](working_with_text.md#safely-replacing-selected-text-in-the-document), safe modifications to the document must be in the context of a write action.
So although a method on the `Document` interface does the `String` insertion, the write action ensures a stable context.

```java
final class MyTypedHandler implements TypedActionHandler {
  @Override
  public void execute(@NotNull Editor editor,
                      char c,
                      @NotNull DataContext dataContext) {
    Document document = editor.getDocument();
    Project project = editor.getProject();
    Runnable runnable = () -> document.insertString(0, "editor_basics\n");
    WriteCommandAction.runWriteCommandAction(project, runnable);
  }
}
```

### Registering a Custom `TypedActionHandler`

A custom implementation of `TypedActionHandler` must be registered to replace the existing typing handler to receive editor keystroke events.
The registration is done through the [`TypedAction`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedAction.java) class.

As is shown in the snippet below, the `EditorActionManager` is used to get access to the `TypedAction` class.
The method `TypedAction.setupHandler()` is used to register the custom `MyTypedHandler` class:

```java
public class EditorHandlerIllustration extends AnAction {
  static {
    EditorActionManager actionManager = EditorActionManager.getInstance();
    TypedAction typedAction = actionManager.getTypedAction();
    typedAction.setupHandler(new MyTypedHandler());
  }
}
```

Placing the registration code in the `EditorHandlerIllustration` class is somewhat arbitrary in the sense that the registration of `MyTypedHandler` has nothing to do with the `EditorHandlerIllustration` class.
However, the `EditorHandlerIllustration` class is convenient because as an action it gets instantiated at application startup.
On instantiation, the `static` block of code in `EditorHandlerIllustration` gets evaluated.
In the `editor_basics` code sample any of the `AnAction` derived classes would work for registering `MyTypedHandler`.
