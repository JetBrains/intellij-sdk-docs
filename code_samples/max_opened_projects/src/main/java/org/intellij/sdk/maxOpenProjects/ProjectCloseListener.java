// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenProjects;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

/**
 * Listener to detect project close.
 */
public class ProjectCloseListener implements ProjectManagerListener {

  @Override
  public void projectClosed(@NotNull Project project) {
    // Ensure this isn't part of testing
    if (ApplicationManager.getApplication().isUnitTestMode()) {
      return;
    }

    // Get the counting service
    ProjectCountingService projectCountingService =
        ApplicationManager.getApplication().getService(ProjectCountingService.class);
    // Decrement the count because a project just closed
    projectCountingService.decreaseOpenProjectCount();
  }

}
