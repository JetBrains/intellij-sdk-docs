// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.*;
import com.intellij.openapi.project.Project;

/**
 * Menu action to replace a selection of characters with a fixed string.
 * @author Anna Bulenkova
 * @see com.intellij.openapi.actionSystem.AnAction
 */
public class EditorIllustration extends AnAction {
  
  /**
   * Replaces the run of text selected by the primary caret with a fixed string.
   * @param e  Event related to this action
   */
  @Override
  public void actionPerformed(final AnActionEvent e) {
    //Get all the required data from data keys
    // Editor and Project were verified in update(), so they are not null.
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
    final Document document = editor.getDocument();
    // Work off of the primary caret to get the selection info
    Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
    int start = primaryCaret.getSelectionStart();
    int end = primaryCaret.getSelectionEnd();
    // Replace the selection with a fixed string.
    // Must do this document change in a write action context.
    WriteCommandAction.runWriteCommandAction(project, () ->
        document.replaceString(start, end, "editor_basics")
    );
    // De-select the text range that was just replaced
    primaryCaret.removeSelection();
  }
  
  /**
   * Sets visibility of this action menu item if:
   *   A project is open,
   *   An editor is active,
   *   Some characters are selected
   * @param e  Event related to this action
   */
  @Override
  public void update(final AnActionEvent e) {
    //Get required data keys
    final Project project = e.getProject();
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    //Set visibility only in case of existing project and editor and if a selection exists
    e.getPresentation().setVisible((project != null && editor != null && editor.getSelectionModel().hasSelection()));
  }
}
