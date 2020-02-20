// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.tutorials.sample.actions;

import com.intellij.openapi.actionSystem.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class DummyActionGroup extends DefaultActionGroup {
  @NotNull
  @Override
  public AnAction[] getChildren(AnActionEvent anActionEvent) {
    return new GroupedAction[0];
  }
}
