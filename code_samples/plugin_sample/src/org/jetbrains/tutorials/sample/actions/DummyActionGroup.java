package org.jetbrains.tutorials.sample.actions;

import com.intellij.openapi.actionSystem.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class DummyActionGroup extends ActionGroup {
  @NotNull
  @Override
  public AnAction[] getChildren(AnActionEvent anActionEvent) {
    return new GroupedAction[0];
  }
}
