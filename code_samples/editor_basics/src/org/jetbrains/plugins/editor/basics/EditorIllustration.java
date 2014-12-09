package org.jetbrains.plugins.editor.basics;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

/**
 * @author Anna Bulenkova
 */
public class EditorIllustration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

    }
    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getData(LangDataKeys.PROJECT);
        final Editor editor = e.getData(LangDataKeys.EDITOR);
        e.getPresentation().setVisible((project != null && editor != null && editor.getSelectionModel().hasSelection()));
    }
}
