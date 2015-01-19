package com.intellij.tutorials.project.model;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author Anna Bulenkova
 */
public class ShowSourceRootsActions extends AnAction {
    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        if (project == null) return;
        String projectName = project.getName();
        StringBuilder sourceRootsList = new StringBuilder();
        VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
        for (VirtualFile file : vFiles) {
            sourceRootsList.append(file.getUrl()).append("\n");
        }
        Messages.showInfoMessage("Source roots for the " + projectName + " plugin:\n" + sourceRootsList, "Project Properties");
    }

    @Override
    public void update(final AnActionEvent e) {
        boolean visibility = e.getProject() != null;
        e.getPresentation().setEnabled(visibility);
        e.getPresentation().setVisible(visibility);
    }
}
