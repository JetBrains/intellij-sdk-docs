// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.project.model;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModificationUtil;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class ModificationAction extends AnAction {

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }

  @Override
  public void actionPerformed(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    if (project == null) {
      return;
    }
    Navigatable element = event.getData(CommonDataKeys.NAVIGATABLE);
    if (element instanceof PsiClass) {
      PsiFile file = ((PsiClass) element).getContainingFile();
      if (file == null) {
        return;
      }
      final VirtualFile virtualFile = file.getVirtualFile();
      if (virtualFile == null) {
        return;
      }
      final ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
      final Module module = fileIndex.getModuleForFile(virtualFile);
      if (module == null) {
        return;
      }
      if (!ModuleRootManager.getInstance(module).getFileIndex().isInContent(virtualFile)) {
        ModuleRootModificationUtil.addModuleLibrary(module, virtualFile.getUrl());
      }
    }

  }

  @Override
  public void update(@NotNull final AnActionEvent event) {
    Project project = event.getProject();
    Navigatable element = event.getData(CommonDataKeys.NAVIGATABLE);
    event.getPresentation().setEnabledAndVisible(project != null && element != null);
  }

}
