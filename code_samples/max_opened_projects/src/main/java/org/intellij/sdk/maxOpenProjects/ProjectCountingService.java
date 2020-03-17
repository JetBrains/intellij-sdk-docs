// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenProjects;

/**
 * Application service implementation to keep a running count of
 * how many projects are open at a given time.
 */
public class ProjectCountingService {
  // Sets the maximum allowed number of opened projects.
  private final static int MAX_OPEN_PRJ_LIMIT = 3;
  // The count of open projects must always be >= 0
  private int myOpenProjectCount = 0;

  public void incrProjectCount() {
    validateProjectCount();
    myOpenProjectCount++;
  }

  public void decrProjectCount() {
    myOpenProjectCount--;
    validateProjectCount();
  }

  public boolean projectLimitExceeded() {
    return myOpenProjectCount > MAX_OPEN_PRJ_LIMIT;
  }

  public int getProjectCount() {
    return myOpenProjectCount;
  }

  /**
   * Anti-bugging to ensure the count of open projects never goes below zero.
   */
  private void validateProjectCount() {
    myOpenProjectCount = Math.max(myOpenProjectCount, 0);
  }

}
