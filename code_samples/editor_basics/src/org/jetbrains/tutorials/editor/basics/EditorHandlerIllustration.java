package org.jetbrains.tutorials.editor.basics;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class EditorHandlerIllustration extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
    EditorActionManager actionManager = EditorActionManager.getInstance();
    //Insert one more caret below the active caret
    EditorActionHandler actionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
    actionHandler.execute(editor, editor.getCaretModel().getCurrentCaret(), anActionEvent.getDataContext());
  }

  @Override
  public void update(@NotNull final AnActionEvent anActionEvent) {
    //Set visible if at least one caret is available
    final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
    final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
    anActionEvent.getPresentation()
                 .setVisible((project != null && editor != null && !editor.getCaretModel().getAllCarets().isEmpty()));
  }
}
