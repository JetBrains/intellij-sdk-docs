// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.List;

/**
 * @author Anna Bulenkova
 */
public class EditorAreaIllustration extends AnAction {
  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();
    boolean caretModelUpToDate = caretModel.isUpToDate();
    Caret primaryCaret = caretModel.getPrimaryCaret();
    boolean primaryCaretIsValid = primaryCaret.isValid();
    StringBuilder report = new StringBuilder();
    if ( caretModelUpToDate && primaryCaretIsValid ) {
      report.append(primaryCaret.getLogicalPosition().toString() + "\n" );
      report.append(primaryCaret.getVisualPosition().toString() + "\n" );
      report.append("Offset: " + primaryCaret.getOffset() );
    } else {
      report.append("Caret model up to date: " + caretModelUpToDate + "\n" + "Primary Caret is valid: " + primaryCaretIsValid);
    }
    Messages.showInfoMessage(report.toString(),"Caret Parameters Inside The Editor");
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
