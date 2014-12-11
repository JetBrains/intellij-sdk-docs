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

**TODO** [Link to threading issue]

#DataContext
Class
[DataContext.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java)
allows an action to retrieve information about the context in which it was invoked.
It's only method

```@Nullable Object getData(@NonNls String dataId);```

returns an object corresponding to the specified data identifier. Some of the supported
data identifiers are defined in class
[PlatformDataKeys.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/actionSystem/PlatformDataKeys.java)


#AnActionEvent
[AnActionEvent.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)
contains the information necessary to execute or update an
[action] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java),
such as
[DataContext] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataContext.java),
[Project] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/project/Project.java),
and other instances related to the action execution environment.

-----------

See also
[CommonDataKeys.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/CommonDataKeys.java),
[DataKey.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/DataKey.java)





