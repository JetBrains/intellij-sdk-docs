// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class AppSettingsComponent {
  // The settings panel
  private final JPanel myMainPanel;
  // JComponents used to get input on the settings panel
  private final JTextField myUserNameText = new JTextField();
  private final JCheckBox myIdeaUserStatus = new JCheckBox();

  public AppSettingsComponent() {
    // Build the panel from the JComponents
    FormBuilder myFormBuilder = FormBuilder.createFormBuilder();
    myFormBuilder.addLabeledComponent("Enter User Name: ", myUserNameText, 1);
    myFormBuilder.addLabeledComponent("Do You Use IntelliJ IDEA? ", myIdeaUserStatus);
    myMainPanel = myFormBuilder.getPanel();
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return myUserNameText;
  }

  @NotNull
  public String getUserNameText() {
    return myUserNameText.getText();
  }

  public void setUserNameText(@NotNull String newText) {
    myUserNameText.setText(newText);
  }

  public boolean getIdeaUserStatus() {
    return myIdeaUserStatus.isSelected();
  }

  public void setIdeaUserStatus(boolean newStatus) {
    myIdeaUserStatus.setSelected(newStatus);
  }

}
