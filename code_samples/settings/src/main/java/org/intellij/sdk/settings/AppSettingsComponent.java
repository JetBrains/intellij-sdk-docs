// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a JPanel for the Settings Dialog.
 */
public class AppSettingsComponent {
  private final JPanel myMainPanel;
  private final JBTextField myUserNameText = new JBTextField();
  private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do You Use IntelliJ IDEA? ");

  public AppSettingsComponent() {
    myMainPanel = FormBuilder.createFormBuilder()
                  .addLabeledComponent(new JBLabel("Enter User Name: "), myUserNameText, 1, false)
                  .addComponent(myIdeaUserStatus, 1)
                  .addComponentFillVertically(new JPanel(), 0)
                  .getPanel();
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
