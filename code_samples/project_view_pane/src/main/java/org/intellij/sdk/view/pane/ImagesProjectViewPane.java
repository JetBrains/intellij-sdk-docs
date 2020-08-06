// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.ide.projectView.BaseProjectTreeBuilder;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.AbstractProjectViewPSIPane;
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase;
import com.intellij.ide.projectView.impl.ProjectTreeStructure;
import com.intellij.ide.projectView.impl.ProjectViewTree;
import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.ide.util.treeView.AbstractTreeUpdater;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultTreeModel;

public class ImagesProjectViewPane extends AbstractProjectViewPSIPane {
  public static final String ID = "IMAGES";

  protected ImagesProjectViewPane(Project project) {
    super(project);
  }

  @NotNull
  @Override
  public String getTitle() {
    return "SDK-Images";
  }

  @NotNull
  @Override
  public javax.swing.Icon getIcon() {
    return AllIcons.FileTypes.Custom;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  public int getWeight() {
    return 10;
  }

  @NotNull
  @Override
  public SelectInTarget createSelectInTarget() {
    return new ProjectViewSelectInTarget(myProject) {

      @Override
      public String toString() {
        return ID;
      }

      @Override
      public String getMinorViewId() {
        return ID;
      }

      @Override
      public float getWeight() {
        return 10;
      }
    };
  }

  @NotNull
  @Override
  protected ProjectAbstractTreeStructureBase createStructure() {
    return new ProjectTreeStructure(myProject, ID) {
      @Override
      protected ImagesProjectNode createRoot(@NotNull Project project, @NotNull ViewSettings settings) {
        return new ImagesProjectNode(project);
      }

      // Children will be searched in async mode
      @Override
      public boolean isToBuildChildrenInBackground(@NotNull Object element) {
        return true;
      }
    };
  }

  @NotNull
  @Override
  protected ProjectViewTree createTree(@NotNull DefaultTreeModel model) {
    return new ProjectViewTree(model) {
      @Override
      public boolean isRootVisible() {
        return true;
      }
    };
  }

  //  Legacy code, awaiting refactoring of AbstractProjectViewPSIPane#createBuilder
  @Override
  protected BaseProjectTreeBuilder createBuilder(@NotNull DefaultTreeModel treeModel) {
    return null;
  }

  //  Legacy code, awaiting refactoring of AbstractProjectViewPSIPane#createTreeUpdater
  @NotNull
  @Override
  protected AbstractTreeUpdater createTreeUpdater(@NotNull AbstractTreeBuilder builder) {
    throw new IllegalStateException("ImagesProjectViewPane tree is async now");
  }
}

