package org.jetbrains.tutorials.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.*;
import com.intellij.ide.projectView.impl.ProjectViewImpl;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.*;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.util.Alarm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

/**
 * @author Anna Bulenkova
 */
public class ImagesProjectNode extends AbstractTreeNode<VirtualFile> {
  private static final Key<Set<VirtualFile>> IMAGES_PROJECT_DIRS = Key.create("images.files.or.directories");

  public ImagesProjectNode(final Project project) {
    super(project, project.getBaseDir());
    scanImages(project);

    subscribeToVFS(project);
  }

  public ImagesProjectNode(Project project, VirtualFile file) {
    super(project, file);
  }

  private void scanImages(Project project) {
    addAllByExt(project, "png");
    addAllByExt(project, "jpg");
  }

  private void addAllByExt(Project project, String ext) {
    final Set<VirtualFile> imagesFiles = getImagesFiles(project);
    final VirtualFile projectDir = project.getBaseDir();
    for (VirtualFile file : FilenameIndex.getAllFilesByExt(project, ext)) {
      while (file != null && !file.equals(projectDir)) {
        imagesFiles.add(file);
        file = file.getParent();
      }
    }
  }

  @NotNull
  private Set<VirtualFile> getImagesFiles(Project project) {
    Set<VirtualFile> files = project.getUserData(IMAGES_PROJECT_DIRS);
    if (files == null) {
      files = new HashSet<VirtualFile>();
      project.putUserData(IMAGES_PROJECT_DIRS, files);
    }
    return files;
  }

  @Override
  protected VirtualFile getVirtualFile() {
    return getValue();
  }

  @NotNull
  @Override
  public Collection<? extends AbstractTreeNode> getChildren() {
    final List<VirtualFile> files = new ArrayList<VirtualFile>(0);
    for (VirtualFile file : getValue().getChildren()) {
      if (getImagesFiles(myProject).contains(file)) {
        files.add(file);
      }
    }
    if (files.isEmpty()) return Collections.emptyList();
    final List<AbstractTreeNode> nodes = new ArrayList<AbstractTreeNode>(files.size());
    final boolean alwaysOnTop = ((ProjectViewImpl) ProjectView.getInstance(myProject)).isFoldersAlwaysOnTop();
    Collections.sort(files, new Comparator<VirtualFile>() {
      @Override
      public int compare(VirtualFile o1, VirtualFile o2) {
        if (alwaysOnTop) {
          final boolean d1 = o1.isDirectory();
          final boolean d2 = o2.isDirectory();
          if (d1 && !d2) return -1;
          if (!d1 && d2) return 1;
        }

        return StringUtil.naturalCompare(o1.getName(), o2.getName());
      }
    });
    for (VirtualFile file : files) {
      nodes.add(new ImagesProjectNode(myProject, file));
    }
    return nodes;
  }

  @Override
  protected void update(PresentationData data) {
    data.setIcon(getValue().isDirectory() ? AllIcons.Nodes.Folder : getValue().getFileType().getIcon());
    data.setPresentableText(getValue().getName());
  }


  @Override
  public boolean canNavigate() {
    return !getValue().isDirectory();
  }

  @Override
  public boolean canNavigateToSource() {
    return canNavigate();
  }

  @Override
  public void navigate(boolean requestFocus) {
    FileEditorManager.getInstance(myProject).openFile(getValue(), false);
  }

  private void subscribeToVFS(final Project project) {
    final Alarm alarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, project);
    LocalFileSystem.getInstance().addVirtualFileListener(new VirtualFileAdapter() {
      {
        final VirtualFileAdapter me = this;
        Disposer.register(project, new Disposable() {
          @Override
          public void dispose() {
            LocalFileSystem.getInstance().removeVirtualFileListener(me);
          }
        });
      }

      @Override
      public void fileCreated(@NotNull VirtualFileEvent event) {
        handle(event);
      }

      @Override
      public void fileDeleted(@NotNull VirtualFileEvent event) {
        handle(event);
      }

      void handle(VirtualFileEvent event) {
        final String filename = event.getFileName().toLowerCase();
        if (filename.endsWith(".png") || filename.endsWith(".jpg")) {
          alarm.cancelAllRequests();
          alarm.addRequest(new Runnable() {
            public void run() {
              getImagesFiles(project).clear();
              scanImages(project);
              //noinspection SSBasedInspection
              SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                  ProjectView.getInstance(myProject)
                             .getProjectViewPaneById(ImagesProjectViewPane.ID)
                             .updateFromRoot(true);
                }
              });
            }
          }, 1000);
        }
      }
    });
  }
}

