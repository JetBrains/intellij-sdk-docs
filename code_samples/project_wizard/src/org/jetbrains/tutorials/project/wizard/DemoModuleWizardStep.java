package org.jetbrains.tutorials.project.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoModuleWizardStep extends ModuleWizardStep {
    private DemoModuleBuilder myBuilder;
    public DemoModuleWizardStep(DemoModuleBuilder builder) {
        myBuilder = builder;
    }

    @Override
    public JComponent getComponent() {
        return new JPanel();
    }

    @Override
    public void updateDataModel() {

    }
}
