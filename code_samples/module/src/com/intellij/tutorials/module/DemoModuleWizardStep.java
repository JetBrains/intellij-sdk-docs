package com.intellij.tutorials.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
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
