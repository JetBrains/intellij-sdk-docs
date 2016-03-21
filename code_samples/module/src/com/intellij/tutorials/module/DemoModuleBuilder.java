package com.intellij.tutorials.module;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.Nullable;

/**
 * @author Anna Bulenkova
 */
public class DemoModuleBuilder extends ModuleBuilder {
  @Override
  public void setupRootModel(ModifiableRootModel model) throws ConfigurationException {

  }

  @Override
  public ModuleType getModuleType() {
    return DemoModuleType.getInstance();
  }

  @Nullable
  @Override
  public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
    return new DemoModuleWizardStep();
  }
}
