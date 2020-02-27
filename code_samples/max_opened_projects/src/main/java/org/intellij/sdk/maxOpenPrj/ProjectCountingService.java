// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

/**
 * Application service implementation to keep a running count of
 * how many projects are open at a given time.
 */
public class ProjectCountingService {
  // Sets the maximum allowed number of opened projects.
  private final int MAX_OPEN_PRJ_LIMIT = 3;

  public boolean projLimitExceeded() {
    ProjectManager prjMgr = ProjectManager.getInstance();
    return prjMgr.getOpenProjects().length > MAX_OPEN_PRJ_LIMIT;
  }

}
