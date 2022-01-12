// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Menu action to clone a new caret based on an existing one.
 *
 * @see AnAction
 */
public class EditorHandlerIllustration extends AnAction {

  /**
   * Clones a new caret at a higher Logical Position line number.
   *
   * @param e Event related to this action
   */
  @Override
  public void actionPerformed(@NotNull final AnActionEvent e) {
    // Editor is known to exist from update, so it's not null
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    // Get the action manager in order to get the necessary action handler...
    final EditorActionManager actionManager = EditorActionManager.getInstance();
    // Get the action handler registered to clone carets
    final EditorActionHandler actionHandler =
            actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_BELOW);
    // Clone one caret below the active caret
    actionHandler.execute(editor, editor.getCaretModel().getPrimaryCaret(), e.getDataContext());
  }

  /**
   * Enables and sets visibility of this action menu item if:
   * <ul>
   *   <li>a project is open</li>
   *   <li>an editor is active</li>
   *   <li>at least one caret exists</li>
   * </ul>
   *
   * @param e Event related to this action
   */
  @Override
  public void update(@NotNull final AnActionEvent e) {
    final Project project = e.getProject();
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    // Make sure at least one caret is available
    boolean menuAllowed = false;
    if (editor != null && project != null) {
      // Ensure the list of carets in the editor is not empty
      menuAllowed = !editor.getCaretModel().getAllCarets().isEmpty();
    }
    e.getPresentation().setEnabledAndVisible(menuAllowed);
  }

}
