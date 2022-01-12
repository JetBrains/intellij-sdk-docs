// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class DemoModuleWizardStep extends ModuleWizardStep {

  @Override
  public JComponent getComponent() {
    return new JLabel("Provide some setting here");
  }

  @Override
  public void updateDataModel() {
    //todo update model according to UI
  }

}
