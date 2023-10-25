// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.treeStructureProvider;

import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

final class TextOnlyTreeStructureProvider implements TreeStructureProvider {

  @NotNull
  @Override
  public Collection<AbstractTreeNode<?>> modify(@NotNull AbstractTreeNode<?> parent,
                                                @NotNull Collection<AbstractTreeNode<?>> children,
                                                ViewSettings settings) {
    ArrayList<AbstractTreeNode<?>> nodes = new ArrayList<>();
    for (AbstractTreeNode<?> child : children) {
      if (child instanceof PsiFileNode) {
        VirtualFile file = ((PsiFileNode) child).getVirtualFile();
        if (file != null && !file.isDirectory() && !(file.getFileType() instanceof PlainTextFileType)) {
          continue;
        }
      }
      nodes.add(child);
    }
    return nodes;
  }

}
