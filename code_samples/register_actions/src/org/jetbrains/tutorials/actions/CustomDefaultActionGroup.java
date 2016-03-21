package org.jetbrains.tutorials.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Editor;

/**
 * @author Anna Bulenkova
 */
public class CustomDefaultActionGroup extends DefaultActionGroup {
  @Override
  public void update(AnActionEvent event) {
    Editor editor = event.getData(CommonDataKeys.EDITOR);
    event.getPresentation().setVisible(true);
    event.getPresentation().setEnabled(editor != null);
    event.getPresentation().setIcon(AllIcons.General.Error);
  }
}
