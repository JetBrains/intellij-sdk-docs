// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.util.Alarm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

public class ImagesProjectNode extends AbstractTreeNode<VirtualFile> {
  private static final Key<Set<VirtualFile>> IMAGES_PROJECT_DIRS = Key.create("images.files.or.directories");

  public ImagesProjectNode(final Project project) {
    super(project, ProjectUtil.guessProjectDir(project));
    scanImages(project);

    subscribeToVFS(project);
  }

  public ImagesProjectNode(Project project, VirtualFile file) {
    super(project, file);
  }

  private void scanImages(Project project) {
    addAllByExt(project, "png");
    addAllByExt(project, "jpg");
    addAllByExt(project, "svg");
  }

  // Creates a collection of image files asynchronously
  private void addAllByExt(Project project, String ext) {
    final Set<VirtualFile> imagesFiles = getImagesFiles(project);
    final VirtualFile projectDir = ProjectUtil.guessProjectDir(project);

    try {
      final Collection<VirtualFile> files = ReadAction.compute(() -> FilenameIndex.getAllFilesByExt(project, ext));

      for (VirtualFile file : files) {
        while (file != null && !file.equals(projectDir)) {
          imagesFiles.add(file);
          file = file.getParent();
        }
      }

    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  @NotNull
  private Set<VirtualFile> getImagesFiles(Project project) {
    Set<VirtualFile> files = project.getUserData(IMAGES_PROJECT_DIRS);
    if (files == null) {
      files = new HashSet<>();
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
  public Collection<? extends AbstractTreeNode<?>> getChildren() {
    final List<VirtualFile> files = new ArrayList<>(0);
    for (VirtualFile file : getValue().getChildren()) {
      if (getImagesFiles(myProject).contains(file)) {
        files.add(file);
      }
    }
    if (files.isEmpty()) return Collections.emptyList();
    final List<AbstractTreeNode<?>> nodes = new ArrayList<>(files.size());
    final boolean alwaysOnTop = ProjectView.getInstance(myProject).isFoldersAlwaysOnTop("");
    files.sort((o1, o2) -> {
      if (alwaysOnTop) {
        final boolean d1 = o1.isDirectory();
        final boolean d2 = o2.isDirectory();
        if (d1 && !d2) return -1;
        if (!d1 && d2) return 1;
      }

      return StringUtil.naturalCompare(o1.getName(), o2.getName());
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
    LocalFileSystem.getInstance().addVirtualFileListener(new VirtualFileListener() {
      {
        final VirtualFileListener me = this;
        Disposer.register(project, () -> LocalFileSystem.getInstance().removeVirtualFileListener(me));
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
          alarm.addRequest(() -> {
            getImagesFiles(project).clear();
            scanImages(project);
            SwingUtilities.invokeLater(() -> ProjectView.getInstance(myProject)
                    .getProjectViewPaneById(ImagesProjectViewPane.ID)
                    .updateFromRoot(true));
          }, 1000);
        }
      }
    });
  }
}

