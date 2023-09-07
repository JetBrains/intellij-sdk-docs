// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.project.model;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class ProjectFileIndexSampleAction extends AnAction {

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }

  @Override
  public void update(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    final Editor editor = event.getData(CommonDataKeys.EDITOR);
    boolean visibility = project != null && editor != null;
    event.getPresentation().setEnabledAndVisible(visibility);
  }

  @Override
  public void actionPerformed(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    final Editor editor = event.getData(CommonDataKeys.EDITOR);
    if (project == null || editor == null) {
      return;
    }

    Document document = editor.getDocument();
    FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
    VirtualFile virtualFile = fileDocumentManager.getFile(document);
    ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();
    if (virtualFile != null) {
      Module module = projectFileIndex.getModuleForFile(virtualFile);
      String moduleName;
      moduleName = module != null ? module.getName() : "No module defined for file";

      VirtualFile moduleContentRoot = projectFileIndex.getContentRootForFile(virtualFile);
      boolean isLibraryFile = projectFileIndex.isInLibrary(virtualFile);
      boolean isInLibraryClasses = projectFileIndex.isInLibraryClasses(virtualFile);
      boolean isInLibrarySource = projectFileIndex.isInLibrarySource(virtualFile);
      Messages.showInfoMessage("Module: " + moduleName + "\n" +
              "Module content root: " + moduleContentRoot + "\n" +
              "Is library file: " + isLibraryFile + "\n" +
              "Is in library classes: " + isInLibraryClasses +
              ", Is in library source: " + isInLibrarySource,
          "Main File Info for" + virtualFile.getName());
    }
  }

}
