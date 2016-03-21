package org.jetbrains.tutorials.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.*;
import com.intellij.ide.util.treeView.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.*;

import javax.swing.tree.*;

/**
 * @author Anna Bulenkova
 */
public class ImagesProjectViewPane extends AbstractProjectViewPSIPane {
  public static final String ID = "IMAGES";

  protected ImagesProjectViewPane(Project project) {
    super(project);
  }

  @Override
  public String getTitle() {
    return "Images";
  }

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

  @Override
  public SelectInTarget createSelectInTarget() {
    return new ProjectViewSelectInTarget(myProject) {

      @Override
      public String toString() {
        return "images";
      }

      @Nullable
      @Override
      public String getMinorViewId() {
        return "images";
      }

      @Override
      public float getWeight() {
        return 10;
      }
    };
  }

  @Override
  protected ProjectAbstractTreeStructureBase createStructure() {
    return new ProjectTreeStructure(myProject, ID) {
      @Override
      protected AbstractTreeNode createRoot(Project project, ViewSettings settings) {
        return new ImagesProjectNode(project);
      }

      @Override
      public Object[] getChildElements(Object element) {
        return super.getChildElements(element);
      }
    };
  }

  @Override
  protected ProjectViewTree createTree(DefaultTreeModel model) {
    return new ProjectViewTree(myProject, model) {
      @Override
      public DefaultMutableTreeNode getSelectedNode() {
        return ImagesProjectViewPane.this.getSelectedNode();
      }

      @Override
      public boolean isRootVisible() {
        return true;
      }
    };
  }

  @Override
  protected AbstractTreeUpdater createTreeUpdater(AbstractTreeBuilder builder) {
    return new AbstractTreeUpdater(builder);
  }
}

