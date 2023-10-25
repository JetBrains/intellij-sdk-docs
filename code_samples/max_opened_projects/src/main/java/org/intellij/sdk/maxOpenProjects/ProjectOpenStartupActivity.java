// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.maxOpenProjects;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Invoked on opening a project.
 */
final class ProjectOpenStartupActivity implements StartupActivity.DumbAware {

  @Override
  public void runActivity(@NotNull Project project) {
    // Ensure this isn't part of testing
    if (ApplicationManager.getApplication().isUnitTestMode()) {
      return;
    }

    // Get the counting service
    ProjectCountingService projectCountingService =
        ApplicationManager.getApplication().getService(ProjectCountingService.class);
    // Increment the project count
    projectCountingService.increaseOpenProjectCount();

    // See if the total # of projects violates the limit.
    if (projectCountingService.isOpenProjectsLimitExceeded()) {
      // Transitioned to outside the limit
      String title = String.format("Opening Project \"%s\"", project.getName());
      String message = "<br>The number of open projects exceeds the SDK plugin max_opened_projects limit.<br><br>";

      ApplicationManager.getApplication().invokeLater(() ->
          Messages.showMessageDialog(project, message, title, Messages.getInformationIcon())
      );
    }
  }

}
