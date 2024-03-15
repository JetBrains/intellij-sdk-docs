// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.maxOpenProjects;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.ui.Messages;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Invoked on opening a project.
 */
// TODO: convert to Kotlin
final class ProjectOpenStartupActivity implements ProjectActivity, DumbAware {

  @Nullable
  @Override
  public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
    // Ensure this isn't part of testing
    Application application = ApplicationManager.getApplication();
    if (application.isUnitTestMode()) {
      return null;
    }

    // Get the counting service
    ProjectCountingService projectCountingService = application.getService(ProjectCountingService.class);
    // Increment the project count
    projectCountingService.increaseOpenProjectCount();

    // See if the total # of projects violates the limit.
    if (projectCountingService.isOpenProjectsLimitExceeded()) {
      // Transitioned to outside the limit
      String title = String.format("Opening Project \"%s\"", project.getName());
      String message = "<br>The number of open projects exceeds the SDK plugin max_opened_projects limit.<br><br>";

      application.invokeLater(() ->
          Messages.showMessageDialog(project, message, title, Messages.getInformationIcon())
      );
    }
    return null;
  }

}
