package com.intellij.tutorials.project.model;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class ShowSourceRootsActions extends AnAction {
  @Override
  public void actionPerformed(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    if (project == null) return;
    String projectName = project.getName();
    StringBuilder sourceRootsList = new StringBuilder();
    VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
    for (VirtualFile file : vFiles) {
      sourceRootsList.append(file.getUrl()).append("\n");
    }
    Messages.showInfoMessage("Source roots for the " + projectName + " plugin:\n" + sourceRootsList,
                             "Project Properties");
  }

  @Override
  public void update(@NotNull final AnActionEvent event) {
    boolean visibility = event.getProject() != null;
    event.getPresentation().setEnabled(visibility);
    event.getPresentation().setVisible(visibility);
  }
}
