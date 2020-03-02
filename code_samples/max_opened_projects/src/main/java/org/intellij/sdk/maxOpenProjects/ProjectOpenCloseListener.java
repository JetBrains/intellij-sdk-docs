// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenProjects;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Listener to detect project open and close.
 * Depends on org.intellij.sdk.maxOpenProjects.ProjectCountingService
 */
public class ProjectOpenCloseListener implements ProjectManagerListener {

  /**
   * Invoked on project open.
   *
   * @param project opening project
   */
  @Override
  public void projectOpened(@NotNull Project project) {
    // Ensure this isn't part of testing
    if (ApplicationManager.getApplication().isUnitTestMode()) return;
    // Get the counting service
    ProjectCountingService projectCountingService = ServiceManager.getService(ProjectCountingService.class);
    // Increment the project count
    projectCountingService.incrProjectCount();
    // See if the total # of projects violates the limit.
    if (projectCountingService.projectLimitExceeded()) {
      // Transitioned to outside the limit
      String title = String.format("Opening Project \"%s\"", project.getName());
      String message = "<br>The number of open projects exceeds the SDK plugin max_opened_projects limit.<br><br>" +
              "This is not an error<br><br>";
      Messages.showMessageDialog(project, message, title, Messages.getInformationIcon());
    }
  }

  /**
   * Invoked on project close.
   *
   * @param project closing project
   */
  @Override
  public void projectClosed(@NotNull Project project) {
    // Ensure this isn't part of testing
    if (ApplicationManager.getApplication().isUnitTestMode()) return;
    // Get the counting service
    ProjectCountingService projectCountingService = ServiceManager.getService(ProjectCountingService.class);
    // Decrement the count because a project just closed
    projectCountingService.decrProjectCount();
  }

}
