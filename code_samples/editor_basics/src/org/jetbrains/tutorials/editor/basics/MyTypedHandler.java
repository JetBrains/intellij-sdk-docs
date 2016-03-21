package org.jetbrains.tutorials.editor.basics;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class MyTypedHandler implements TypedActionHandler {
  @Override
  public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
    final Document document = editor.getDocument();
    Project project = editor.getProject();
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        document.insertString(0, "Typed\n");
      }
    };
    WriteCommandAction.runWriteCommandAction(project, runnable);
  }
}
