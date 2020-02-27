// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 13, 2010
 * Time: 3:49:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyCounter {
  // Sets the maximum allowed number of opened projects.
  public final int maxCount = 3;
  private int count = 0;

  public MyCounter() {
  }

  public int increaseCounter() {
    return (++count > maxCount) ? -1 : count;
  }

  @SuppressWarnings("UnusedReturnValue")
  public int decreaseCounter() {
    return (--count > maxCount) ? -1 : count;
  }

}
