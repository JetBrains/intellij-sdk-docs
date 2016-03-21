package com.intellij.tutorials.project.model;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class ProjectSdkAction extends AnAction {
  @Override
  public void actionPerformed(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    if (project != null) {
      String projectSDKName = ProjectRootManager.getInstance(project).getProjectSdkName();
      String newProjectSdkName = "New Sdk Name";
      ProjectRootManager.getInstance(project).setProjectSdkName(newProjectSdkName);
      Messages.showInfoMessage(projectSDKName + " has changed to " + newProjectSdkName, "Project Sdk Info");
    }
  }

  @Override
  public void update(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    if (project != null) {
      Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();
      event.getPresentation().setEnabledAndVisible(sdk != null);
    }
  }
}
