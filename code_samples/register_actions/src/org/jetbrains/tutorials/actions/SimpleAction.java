package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;

/**
 * @author Anna Bulenkova
 */
public class SimpleAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

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
