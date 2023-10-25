// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

final class DemoModuleType extends ModuleType<DemoModuleBuilder> {

  private static final String ID = "DEMO_MODULE_TYPE";

  DemoModuleType() {
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
    return "SDK Module Type";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Example custom module type";
  }

  @NotNull
  @Override
  public Icon getNodeIcon(@Deprecated boolean b) {
    return SdkIcons.Sdk_default_icon;
  }

  @Override
  public ModuleWizardStep @NotNull [] createWizardSteps(@NotNull WizardContext wizardContext,
                                                        @NotNull DemoModuleBuilder moduleBuilder,
                                                        @NotNull ModulesProvider modulesProvider) {
    return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
  }

}
