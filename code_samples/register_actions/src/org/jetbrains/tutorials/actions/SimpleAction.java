/*
 * Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;


/**
 * Action class to demonstrate how to interact with the IntelliJ Platform when a menu entry is chosen.
 * The association between this Java class and the menu structure is declared in this module's plugin.xml file
 * @see com.intellij.openapi.actionSystem.AnAction
 * @see com.intellij.openapi.actionSystem.AnActionEvent
 */
public class SimpleAction extends AnAction {

  /**
   * Takes action based on the user choosing the menu item.
   * @param anActionEvent  Event received when the associated menu item is chosen.
   */
  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    // Using the event, get the project and navigatable objects
    Project project = anActionEvent.getProject();
    Navigatable navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    if (project != null && navigatable != null) {
      // Pop a small dialog
      Messages.showMessageDialog(project, navigatable.toString(), "Selected Element", Messages.getInformationIcon());
    }
  }

  /**
   * Determines whether this menu item is suitable for the current context.
   * @param anActionEvent  Event received when the associated group-id menu is chosen.
   */
  @Override
  public void update(AnActionEvent anActionEvent) {
    // Using the event, get the project and navigatable objects - they will be needed in actionPerformed()
    Project project = anActionEvent.getProject();
    Navigatable navigatable = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
    // Make the associated menu item both visible and enabled
    anActionEvent.getPresentation().setEnabledAndVisible(project != null && navigatable != null);
  }
}
