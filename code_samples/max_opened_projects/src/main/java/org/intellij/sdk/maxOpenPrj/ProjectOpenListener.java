// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Listener to detect opening of projects.
 */
public class ProjectOpenListener implements ProjectManagerListener {

  /**
   * Invoked on project open.
   *
   * @param project opening project
   */
  @Override
  public void projectOpened(@NotNull Project project) {
    // Get the counting service
    ProjectCountingService prjCountingSrvc = ServiceManager.getService(ProjectCountingService.class);
    // See if the total # of projects violates the limit.
    if (prjCountingSrvc.projLimitExceeded()) {
      Messages.showMessageDialog(
              "The number of open projects exceeds the SDK plugin max_opened_projects limit. Close at least one open project and try again.",
              "Cannot open project \"" + project.getName() + "\"",
              Messages.getErrorIcon());
      ProjectManager prjMgr = ProjectManager.getInstance();
      boolean closedSuccessfully = prjMgr.closeProject(project);
      System.out.println(String.format("Project closing was %s", closedSuccessfully));
    }
  }

}
