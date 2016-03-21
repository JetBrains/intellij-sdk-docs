package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author Anna Bulenkova
 */
public class SimpleAction extends AnAction {
  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    Object navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    if (navigatable != null) {
      Messages.showDialog(navigatable.toString(), "Selected Element:", new String[]{"OK"}, -1, null);
    }
  }

  @Override
  public void update(AnActionEvent anActionEvent) {
    final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
    if (project != null)
      return;
    Object navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    anActionEvent.getPresentation().setVisible(navigatable != null);
  }
}
