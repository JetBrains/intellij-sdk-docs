// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppSettingsConfigPanel {

  private JPanel myMainPanel;
  private JTextField myAppSetting0;
  private JCheckBox myAppSetting1;
  private JCheckBox myAppSetting2;

  private AppSettingsConfiguration.AscState myAppSettings;


  public AppSettingsConfigPanel(@NotNull String title) {
    myAppSettings = new AppSettingsConfiguration.AscState();

    myAppSetting0 = new JTextField(myAppSettings.getText().toString());
    myAppSetting1 = new JCheckBox(String.valueOf(myAppSettings.getBool(0)));
    myAppSetting2 = new JCheckBox(String.valueOf(myAppSettings.getBool(1)));

    FormBuilder myFormBuilder = FormBuilder.createFormBuilder();
    myMainPanel = myFormBuilder.getPanel();
    myFormBuilder.addLabeledComponent("Application Setting 0: ", myAppSetting0, 10)
            .addLabeledComponent(new JLabel("Application Setting 1: "), myAppSetting1)
            .addLabeledComponent(new JLabel("Application Setting 2: "), myAppSetting2);
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferred() {
    return myAppSetting0;
  }

}
