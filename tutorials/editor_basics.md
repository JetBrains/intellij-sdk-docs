Basics of working with the editor
===========

### [Sample plugin] (https://github.com/JetBrains/intellij-sdk/tree/master/code_samples/editor_basics)

----------

Classes for working with editor, e.g. to manipulate the caret, get an access to a text selection, or modify the text, currently represented in the editor, are located in
[editor-ui-api] (https://github.com/JetBrains/intellij-community/tree/master/platform/editor-ui-api)
package. Note, that this part of the API allows to operate only with text.
If you need to access PSI please see
[PSI Cookbook] (https://confluence.jetbrains.com/display/IDEADEV/PSI+Cookbook)
section.

-----------

#Editor
An instance on IntelliJ IDEA editor is represented by an interface
[Editor.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java),
and it's implementation can be found in a class
[EditorImpl.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/editor/impl/EditorImpl.java).

##Get an instance of the active editor
A reference to an instance of the editor can be obtained by calling

    CommonDataKeys.EDITOR

To access the editor instance directly the following ways can be used:

* If [DataContext] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)
object is available ```final Editor editor = CommonDataKeys.EDITOR.getData(context);```
* If [ActionEvent] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
object is available ```final Editor editor = actionEvent.getData(CommonDataKeys.EDITOR);```

##Obtaining content: document, caret, selection, and more
###Document.
[Document.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/editor/Document.java)
represents the contents of a text file loaded into memory, and possibly opened in an IDEA
text editor. Line breaks in the document text are always normalized as single \n characters,
and are converted to proper format when the document is saved.
[Document] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/editor/Document.java)
can be obtained by calling ```Document document = editor.getDocument();```

###Models
Different services for controlling and getting information about visible areas the editor can be accessible set of getters, e.g.
```final SelectionModel selectionModel = editor.getSelectionModel();```

Editor model classes are located in
[editor] (https://github.com/JetBrains/intellij-community/tree/master/platform/editor-ui-api/src/com/intellij/openapi/editor)
subpackage of the
[editor-ui-api] (https://github.com/JetBrains/intellij-community/tree/master/platform/editor-ui-api)
package and include:
[CaretModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java),
[FoldingModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/FoldingModel.java),
[IndentsModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/IndentsModel.java),
[ScrollingModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
[ScrollingModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/ScrollingModel.java),
[SoftWrapModel.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/SoftWrapModel.java)

Please see
[EditorIllustration.java] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/editor_basics/src/org/jetbrains/plugins/editor/basics/EditorIllustration.java)
for more details.

###Multiple carets
IntelliJ editor supports work with more than one caret. To learn more about multiple carets please see
[this document] (http://confluence.jetbrains.com/display/IDEADEV/Supporting+multiple+carets)

#EditorFactory
[EditorFactory.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/EditorFactory.java)
Provides services for creating document and editor instances.
Please note, that creating and releasing of editors must be done from the event dispatch thread.

      protected JComponent createCenterPanel() {
         final Document document = ((EditorFactoryImpl)EditorFactory.getInstance()).createDocument(true);
         ((DocumentImpl)document).setAcceptSlashR(true);
         myTextArea = EditorFactory.getInstance().createEditor(document, myProject, StdFileTypes.PLAIN_TEXT, true);
         final EditorSettings settings = myTextArea.getSettings();
         settings.setLineNumbersShown(false);
         settings.setLineMarkerAreaShown(false);
         settings.setFoldingOutlineShown(false);
         settings.setRightMarginShown(false);
         settings.setAdditionalLinesCount(0);
         settings.setAdditionalColumnsCount(0);
         settings.setAdditionalPageAtBottom(false);
         ((EditorEx)myTextArea).setBackgroundColor(UIUtil.getInactiveTextFieldBackgroundColor());
         return myTextArea.getComponent();
      }

Example from
[ExportToFileUtil.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/ide/util/ExportToFileUtil.java)

#Actions activated by different editor events
Classes that provide support for handling events from the editor and react on then are located in
[editor.actionSystem] (https://github.com/JetBrains/intellij-community/tree/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem)
package. Following examples can be considered.

##TypedActionHandler
Interface [TypedActionHandler.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/TypedActionHandler.java)
stays for actions activated by typing in the editor, meaning if typing starts actions will be executed.
An example of using TypedActionHandler can be found in class
[MyTypedHandler.java] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/editor_basics/src/org/jetbrains/plugins/editor/basics/MyTypedHandler.java).
In this case a string *Typed* will be inserted in the editor on the first position after every keystroke.

##EditorActionHandler
Class
[EditorActionHandler.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/actionSystem/EditorActionHandler.java)
also stays for actions activated by keystrokes in the editor.
This type of the editor handler should be registered as an extension point
```<editorActionHandler action="EditorSelectWord" implementationClass="com.intellij.codeInsight.editorActions.SelectWordHandler"/>```.

Two types of handlers are supported: the ones which are executed once, and the ones which are executed for each caret.
Examples of already implemented handlers can be found in
[this package] (https://github.com/JetBrains/intellij-community/tree/master/platform/lang-impl/src/com/intellij/codeInsight/editorActions),
e.g. the class
[CopyHandler.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/codeInsight/editorActions/CopyHandler.java).
To implement the logic you need to override ```implement()``` action.
And used like shows the following example:
[EditorHandlerIllustration.java] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/editor_basics/src/org/jetbrains/plugins/editor/basics/EditorHandlerIllustration.java)

#Working with text
##EditorModificationUtil
Basic, most commonly required actions for text modification, e.g working with text selections,
inserting and deleting symbols and strings, and manipulating with text blocks, are represented are implemented in the utility class
[EditorModificationUtil.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/editor/EditorModificationUtil.java)

#Editor coordinates system. Positions and offsets.
Every caret in the editor has a set of properties describing it's coordinates. These properties can be accessed by obtaining a
[caret model instance] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/CaretModel.java).

##Logical position
[LogicalPosition.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/LogicalPosition.java)
represents a line and a column of the current logical position of the caret. Logical positions ignore folding -
for example, if the top 10 lines of the document are folded, the 10th line in the document will have the line number 10 in its logical position.
Logical position may store additional parameters that define its mapping to
[VisualPosition.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java).
Rationale is that single logical pair matches soft wrap-introduced virtual space, i.e. different visual positions
correspond to the same logical position. It's convenient to store exact visual location details within the logical
position in order to relief further 'logical position' -> 'visual position' mapping.

##Visual position
[VisualPosition.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/editor/VisualPosition.java)
represent a visual position and may very from the corresponding logical position.
Visual positions take folding into account - for example, if the top 10 lines of the document are folded, the 10th line in the document will have the line number 1 in its visual position.

##Offset
An absolute offset for a given caret position is accessible by calling
```int offset = caretModel.getOffset();```

**TODO** [Link to threading issue]

-----------

### [Sample plugin] (https://github.com/JetBrains/intellij-sdk/tree/master/code_samples/editor_basics)
-----------

See also
[CommonDataKeys.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java),
[DataKey.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataKey.java),
[AnActionEvent] (https://github.com/JetBrains/intellij-community/blob/ff16ce78a1e0ddb6e67fd1dbc6e6a597e20d483a/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java),
[DataContext] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)

Related topics
[Action System] (https://github.com/JetBrains/intellij-sdk/blob/master/tutorials/action_system.md)





