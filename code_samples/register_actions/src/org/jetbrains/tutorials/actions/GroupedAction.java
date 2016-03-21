package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.*;

/**
 * @author Anna Bulenkova
 */
public class GroupedAction extends AnAction {
  @Override
  public void update(AnActionEvent event) {
    event.getPresentation().setEnabledAndVisible(true);
  }

  @Override
  public void actionPerformed(AnActionEvent event) {
    //Does nothing
  }
}
