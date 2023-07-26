// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.maxOpenProjects;

import com.intellij.openapi.components.Service;

/**
 * Application service implementation to keep a running count of how many projects are open at a given time.
 */
@Service
public final class ProjectCountingService {

  private final static int MAX_OPEN_PROJECTS_LIMIT = 3;

  private int myOpenProjectCount = 0;

  public void increaseOpenProjectCount() {
    myOpenProjectCount++;
  }

  public void decreaseOpenProjectCount() {
    if (myOpenProjectCount > 0) {
      myOpenProjectCount--;
    }
  }

  public boolean isOpenProjectsLimitExceeded() {
    return myOpenProjectCount > MAX_OPEN_PROJECTS_LIMIT;
  }

}
