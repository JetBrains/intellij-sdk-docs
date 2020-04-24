// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

// This class has responsibility for creating and managing a JPanel for the Settings Dialog.
public class AppSettingsComponent {
  // The settings panel
  private final JPanel myMainPanel;
  // JComponents used to get input on the settings panel
  private final JBTextField myUserNameText = new JBTextField();
  private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do You Use IntelliJ IDEA? ");

  // Builds the JPanel for the Settings Dialog
  public AppSettingsComponent() {
    // Build the panel from the JComponents
    FormBuilder myFormBuilder = FormBuilder.createFormBuilder()
            .addLabeledComponent(new JBLabel("Enter User Name: "), myUserNameText, 1, false)
            .addComponent(myIdeaUserStatus, 1);
    myMainPanel = myFormBuilder.getPanel();
  }

  // Provides a reference to the JPanel containing the UI for settings.
  public JPanel getPanel() {
    return myMainPanel;
  }

  // Returns the component which should be focused when the Settings dialog appears
  public JComponent getPreferredFocusedComponent() {
    return myUserNameText;
  }

  // Returns the text from the User Name field on the Settings panel.
  @NotNull
  public String getUserNameText() {
    return myUserNameText.getText();
  }

  // Sets the text to be displayed in the User Name field on the Settings Panel
  public void setUserNameText(@NotNull String newText) {
    myUserNameText.setText(newText);
  }

  // Returns the value from the User Status field on the Settings panel.
  public boolean getIdeaUserStatus() {
    return myIdeaUserStatus.isSelected();
  }

  // Sets new value in User Status field on the Settings panel
  public void setIdeaUserStatus(boolean newStatus) {
    myIdeaUserStatus.setSelected(newStatus);
  }

}
