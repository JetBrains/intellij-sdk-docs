package com.intellij.tutorials.module;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.module.*;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoModuleType extends ModuleType<DemoModuleBuilder> {
  private static final String ID = "DEMO_MODULE_TYPE";

  public DemoModuleType() {
    super(ID);
  }

  public static DemoModuleType getInstance() {
    return (DemoModuleType) ModuleTypeManager.getInstance().findByID(ID);
  }

  @NotNull
  @Override
  public DemoModuleBuilder createModuleBuilder() {
    return new DemoModuleBuilder();
  }

  @NotNull
  @Override
  public String getName() {
    return "Demo Module Type";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Demo Module Type";
  }

  @Override
  public Icon getBigIcon() {
    return AllIcons.General.Information;
  }

  @Override
  public Icon getNodeIcon(@Deprecated boolean b) {
    return AllIcons.General.Information;
  }

  @NotNull
  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                              @NotNull DemoModuleBuilder moduleBuilder,
                                              @NotNull ModulesProvider modulesProvider) {
    return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
  }
}
