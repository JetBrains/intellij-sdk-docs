package org.jetbrains.tutorials.editor.basics;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author Anna Bulenkova
 */
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
    final Project project = e.getData(CommonDataKeys.PROJECT);
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    e.getPresentation().setVisible(project != null && editor != null);
  }
}
