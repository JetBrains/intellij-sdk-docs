/*
 * Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package org.jetbrains.tutorials.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 * @see com.intellij.openapi.actionSystem.AnAction
 * @see com.intellij.openapi.actionSystem.AnActionEvent
 */
public class SimplePopDialogAction extends AnAction {

  /**
   * This default constructor is used by the IntelliJ Platform framework to
   * instantiate this class based on plugin.xml declarations. Only needed in SimplePopDialogAction
   * class because another constructor is overridden.
   * @see AnAction#AnAction()
   */
  public SimplePopDialogAction() {
    super();
  }

  /**
   * This constructor is used to support dynamically added menu actions.
   * It sets the text, description to be displayed for the menu item.
   * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
   * @see AnAction#AnAction(String, String, Icon)
   * @param menuText  The text to be displayed as a menu item.
   * @param menuDescription  The description of the menu item.
   * @param menuIcon  The icon to be used with the menu item.
   */
  public SimplePopDialogAction(@Nullable String menuText, @Nullable String menuDescription, @Nullable Icon menuIcon) {
    super(menuText, menuDescription, menuIcon);
  }

  /**
   * Gives the user feedback when the dynamic action menu is chosen.
   * Pops a simple message dialog. See the psi_demo plugin for an
   * example of how to use AnActionEvent to access Psi data.
   * @param anActionEvent Event received when the associated menu item is chosen.
   */
  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    // Using the event, create and show a dialog
    Project currentProject = anActionEvent.getProject();
    String dlgTitle = anActionEvent.getPresentation().getDescription();
    String dlgMessage = anActionEvent.getPresentation().getText() + " Selected!";
    Messages.showMessageDialog(currentProject, dlgMessage, dlgTitle, Messages.getInformationIcon());
  }

  /**
   * Determines whether this menu item is available for the current context.
   * Requires a project to be open.
   * @param anActionEvent Event received when the associated group-id menu is chosen.
   */
  @Override
  public void update(AnActionEvent anActionEvent) {
    // Set the availability based on whether a project is open
    Project project = anActionEvent.getProject();
    anActionEvent.getPresentation().setEnabledAndVisible(project != null);
  }

}
