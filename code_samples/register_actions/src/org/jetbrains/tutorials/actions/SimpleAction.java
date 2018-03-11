package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;

public class SimpleAction extends AnAction {
  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    Project project = anActionEvent.getProject();
    Navigatable navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    if (project != null && navigatable != null) {
      Messages.showMessageDialog(project, navigatable.toString(), "Selected Element", Messages.getInformationIcon());
    }
  }

  @Override
  public void update(AnActionEvent anActionEvent) {
    Project project = anActionEvent.getProject();
    Navigatable navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
      anActionEvent.getPresentation().setEnabledAndVisible(project != null && navigatable != null);
  }
}
