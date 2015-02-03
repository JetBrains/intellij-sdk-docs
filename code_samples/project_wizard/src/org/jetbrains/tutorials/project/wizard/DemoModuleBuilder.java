package org.jetbrains.tutorials.project.wizard;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class DemoModuleBuilder extends JavaModuleBuilder implements ModuleBuilderListener {
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{new DemoModuleWizardStep(this)};
    }

    @Override
    public void moduleCreated(@NotNull Module module) {

    }
}
