// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.editor;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * This is a custom TypedActionHandler that handles actions activated
 * keystrokes in the editor.
 * The execute method inserts a fixed string at Offset 0 of the document.
 * Document changes are made in the context of a write action.
 * MyTypedHandler is registered by static code in the EditorHandlerIllustration class.
 *
 * @see com.intellij.openapi.editor.actionSystem.TypedActionHandler
 */
class MyTypedHandler implements TypedActionHandler {
  @Override
  public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
    // Get the document and project
    final Document document = editor.getDocument();
    final Project project = editor.getProject();
    // Construct the runnable to substitute the string at offset 0 in the document
    Runnable runnable = () -> document.insertString(0, "editor_basics\n");
    // Make the document change in the context of a write action.
    WriteCommandAction.runWriteCommandAction(project, runnable);
  }
}
