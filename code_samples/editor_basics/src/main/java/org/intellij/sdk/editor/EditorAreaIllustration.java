// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

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
                             "Offset: " + offset
                             // TODO: Remove the next line of diagnostic code
                             + "\n" + "LogicalPosition.leansForward = " + String.valueOf(logicalPosition.leansForward),
                             "Caret Parameters Inside The Editor");
  }

  @Override
  public void update(AnActionEvent e) {
    //Get required data keys
    final Project project = e.getData(CommonDataKeys.PROJECT);
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    //Set visibility only in case of existing project and editor
    e.getPresentation().setVisible(project != null && editor != null);
  }
}
