// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.view.pane;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.GroupByTypeComparator;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.roots.FileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.update.MergingUpdateQueue;
import com.intellij.util.ui.update.Update;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ImagesProjectNode extends ProjectViewNode<VirtualFile> {

  private static final Key<Set<VirtualFile>> IMAGES_PROJECT_DIRS = Key.create("images.files.or.directories");

  private static final List<String> SUPPORTED_IMAGE_EXTENSIONS = List.of("jpg", "jpeg", "png", "svg");

  private final MergingUpdateQueue updateQueue;

  /**
   * Creates root node.
   */
  public ImagesProjectNode(@NotNull Project project,
                           @NotNull ViewSettings settings,
                           @NotNull VirtualFile rootDir,
                           @NotNull Disposable parentDisposable) {
    super(project, rootDir, settings);
    scanImages(project);
    setupImageFilesRefresher(project, parentDisposable); // subscribe to changes only in the root node
    updateQueue = new MergingUpdateQueue(ImagesProjectNode.class.getName(), 200, true, null, parentDisposable, null);
  }

  /**
   * Creates child node.
   */
  private ImagesProjectNode(@NotNull Project project,
                            @NotNull ViewSettings settings,
                            @NotNull VirtualFile file,
                            @NotNull MergingUpdateQueue updateQueue) {
    super(project, file, settings);
    this.updateQueue = updateQueue;
  }

  private void scanImages(@NotNull Project project) {
    for (String imageExtension : SUPPORTED_IMAGE_EXTENSIONS) {
      addAllByExt(project, imageExtension);
    }
  }

  private void addAllByExt(@NotNull Project project, @NotNull String extension) {
    Set<VirtualFile> imagesFiles = getImagesFiles(project);
    VirtualFile projectDir = ProjectUtil.guessProjectDir(project);
    Collection<VirtualFile> files = ReadAction.compute(() -> FilenameIndex.getAllFilesByExt(project, extension));
    for (VirtualFile file : files) {
      while (file != null && !file.equals(projectDir)) {
        imagesFiles.add(file);
        file = file.getParent();
      }
    }
  }

  @NotNull
  private Set<VirtualFile> getImagesFiles(@NotNull Project project) {
    Set<VirtualFile> files = project.getUserData(IMAGES_PROJECT_DIRS);
    if (files == null) {
      files = new HashSet<>();
      project.putUserData(IMAGES_PROJECT_DIRS, files);
    }
    return files;
  }

  private void setupImageFilesRefresher(@NotNull Project project, @NotNull Disposable parentDisposable) {
    project.getMessageBus().connect(parentDisposable)
        .subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
          @Override
          public void after(@NotNull List<? extends @NotNull VFileEvent> events) {
            boolean hasAnyImageUpdate = false;
            FileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
            for (VFileEvent event : events) {
              VirtualFile file = event.getFile();
              if (file == null || !fileIndex.isInContent(file)) {
                continue;
              }
              String extension = file.getExtension();
              if (extension != null && SUPPORTED_IMAGE_EXTENSIONS.contains(extension)) {
                hasAnyImageUpdate = true;
                break;
              }
            }
            if (hasAnyImageUpdate) {
              updateQueue.queue(new Update("UpdateImages") {
                public void run() {
                  getImagesFiles(project).clear();
                  scanImages(project);
                  ApplicationManager.getApplication().invokeLater(() ->
                          ProjectView.getInstance(project)
                              .getProjectViewPaneById(ImagesProjectViewPane.ID)
                              .updateFromRoot(true),
                      project.getDisposed()
                  );
                }
              });
            }
          }
        });
  }

  @Override
  public boolean contains(@NotNull VirtualFile file) {
    return file.equals(getVirtualFile());
  }

  @Override
  @NotNull
  public VirtualFile getVirtualFile() {
    return getValue();
  }

  @NotNull
  @Override
  public Collection<? extends AbstractTreeNode<?>> getChildren() {
    List<VirtualFile> files = new ArrayList<>();
    for (VirtualFile file : getValue().getChildren()) {
      if (getImagesFiles(myProject).contains(file)) {
        files.add(file);
      }
    }
    if (files.isEmpty()) {
      return Collections.emptyList();
    }
    ViewSettings settings = getSettings();
    return ContainerUtil.sorted(
        ContainerUtil.map(files, (file) -> new ImagesProjectNode(myProject, settings, file, updateQueue)),
        new GroupByTypeComparator(myProject, ImagesProjectViewPane.ID));
  }

  @Override
  protected void update(@NotNull PresentationData data) {
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

  @Override
  public int getTypeSortWeight(boolean sortByType) {
    // required for "Folder Always on Top"
    return getVirtualFile().isDirectory() ? 1 : 0;
  }

}
