package com.intellij.tutorials.project.model;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;

/**
 * @author Anna Bulenkova
 */
public class ProjectSdkAction extends AnAction {
    @Override
    public void actionPerformed(final AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            String projectSDKName = ProjectRootManager.getInstance(project).getProjectSdkName();
            String newProjectSdkName = "New Sdk Name";
            ProjectRootManager.getInstance(project).setProjectSdkName(newProjectSdkName);
            Messages.showInfoMessage(projectSDKName + " has changed to " + newProjectSdkName, "Project Sdk Info");
        }
    }

    @Override
    public void update(final AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();
            e.getPresentation().setEnabledAndVisible(sdk != null);
        }
    }
}
