package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class BaseActionGroup extends ActionGroup {
  @NotNull
  @Override
  public AnAction[] getChildren(AnActionEvent anActionEvent) {
    return new AnAction[]{new MyAction()};
  }

  class MyAction extends AnAction {
    public MyAction() {
      super("Dynamically Added Action");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
      //does nothing
    }
  }
}
