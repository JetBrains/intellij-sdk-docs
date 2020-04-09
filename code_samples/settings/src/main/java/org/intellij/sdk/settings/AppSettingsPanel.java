// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppSettingsPanel {
  // The settings panel
  private final JPanel myMainPanel;
  // JComponents used to get input on the settings panel
  private final JTextField myPanelTextField;
  private final JCheckBox myPanelCheckBoxField;

  public AppSettingsPanel() {
    myPanelTextField = new JTextField();
    myPanelCheckBoxField = new JCheckBox();
    // Build the panel from the JComponents
    FormBuilder myFormBuilder = FormBuilder.createFormBuilder();
    myFormBuilder.addLabeledComponent("Application text setting: ", myPanelTextField, 1);
    myFormBuilder.addLabeledComponent("Application boolean setting: ", myPanelCheckBoxField);
    myMainPanel = myFormBuilder.getPanel();
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferred() {
    return myPanelTextField;
  }

  @NotNull
  public String getPanelTextFieldValue() {
    return myPanelTextField.getText();
  }

  public void setPanelTextFieldValue(@NotNull String text) {
    myPanelTextField.setText(text);
  }

  public boolean getPanelCheckBoxValue() {
    return myPanelCheckBoxField.isSelected();
  }

  public void setPanelCheckBoxValue(boolean bool) {
    myPanelCheckBoxField.setSelected(bool);
  }

}
