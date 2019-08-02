// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.List;

/**
 * If conditions support it, makes a menu visible to display information
 * about the caret.
 * @author Anna Bulenkova
 * @see com.intellij.openapi.actionSystem.AnAction
 */
public class EditorAreaIllustration extends AnAction {
  
  /**
   * Displays a message with information about the current caret.
   * @param e  Event related to this action
   */
  @Override
  public void actionPerformed(AnActionEvent e) {
    // Get access to the editor and caret model. update() validated editor's existence.
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();
    // Getting the primary caret ensures we get the right one of a possible many.
    Caret primaryCaret = caretModel.getPrimaryCaret();
    // Build and display the caret report.
    StringBuilder report = new StringBuilder();
    report.append(primaryCaret.getLogicalPosition().toString() + "\n");
    report.append(primaryCaret.getVisualPosition().toString() + "\n");
    report.append("Offset: " + primaryCaret.getOffset());
    Messages.showInfoMessage(report.toString(), "Caret Parameters Inside The Editor");
  }
  
  /**
   * Sets visibility of this action menu item if:
   *   A project is open,
   *   An editor is active,
   * @param e  Event related to this action
   */
  @Override
  public void update(AnActionEvent e) {
    //Get required data keys
    final Project project = e.getProject();
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    //Set visibility only in case of existing project and editor
    e.getPresentation().setVisible(project != null && editor != null);
  }
}
