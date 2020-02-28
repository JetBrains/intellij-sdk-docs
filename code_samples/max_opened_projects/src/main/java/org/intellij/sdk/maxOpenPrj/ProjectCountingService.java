// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

/**
 * Application service implementation to keep a running count of
 * how many projects are open at a given time.
 */
public class ProjectCountingService {
  // Sets the maximum allowed number of opened projects.
  private final int MAX_OPEN_PRJ_LIMIT = 3;
  // The count of open projects must always be >= 0
  private int openProjectCount = 0;

  public void incrProjectCount() {
    if (openProjectCount < 0) {
      openProjectCount = 0;
    }
    openProjectCount++;
  }

  public void decrProjectCount() {
    openProjectCount--;
    if (openProjectCount < 0) {
      openProjectCount = 0;
    }
  }

  public boolean projectLimitExceeded() {
    return openProjectCount > MAX_OPEN_PRJ_LIMIT;
  }

}
