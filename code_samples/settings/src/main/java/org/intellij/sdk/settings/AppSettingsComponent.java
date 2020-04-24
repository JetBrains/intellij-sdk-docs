// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * This class has responsibility for creating and managing
 * a JPanel for the Settings Dialog.
 */
public class AppSettingsComponent {
  // The settings panel
  private final JPanel myMainPanel;
  // JComponents used to get input on the settings panel
  private final JTextField myUserNameText = new JTextField();
  private final JCheckBox myIdeaUserStatus = new JCheckBox();

  /**
   * Builds the JPanel for the Settings Dialog
   */
  public AppSettingsComponent() {
    // Build the panel from the JComponents
    FormBuilder myFormBuilder = FormBuilder.createFormBuilder()
            .addLabeledComponent("Enter User Name: ", myUserNameText, 1)
            .addLabeledComponentFillVertically("Do You Use IntelliJ IDEA? ", myIdeaUserStatus);
    myMainPanel = myFormBuilder.getPanel();
  }

  /**
   * Provides a reference to the JPanel containing the UI for settings.
   *
   * @return the JPanel
   */
  public JPanel getPanel() {
    return myMainPanel;
  }

  /**
   * @return component which should be focused when the dialog appears
   * on the screen.
   */
  public JComponent getPreferredFocusedComponent() {
    return myUserNameText;
  }

  /**
   * @return the text from the User Name field on the Settings panel.
   */
  @NotNull
  public String getUserNameText() {
    return myUserNameText.getText();
  }

  /**
   * @param newText Text to be displayed in the User Name field on the Settings Panel
   */
  public void setUserNameText(@NotNull String newText) {
    myUserNameText.setText(newText);
  }

  /**
   * @return the value from the User Status field on the Settings panel.
   */
  public boolean getIdeaUserStatus() {
    return myIdeaUserStatus.isSelected();
  }

  /**
   * @param newStatus value to set in User Status field on the Settings panel
   */
  public void setIdeaUserStatus(boolean newStatus) {
    myIdeaUserStatus.setSelected(newStatus);
  }

}
