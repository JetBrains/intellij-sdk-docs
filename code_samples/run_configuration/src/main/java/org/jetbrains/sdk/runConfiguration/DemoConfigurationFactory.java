// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.sdk.runConfiguration;

import com.intellij.execution.configurations.*;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DemoConfigurationFactory extends ConfigurationFactory {
  private static final String FACTORY_NAME = "Demo configuration factory";

  protected DemoConfigurationFactory(ConfigurationType type) {
    super(type);
  }

  @NotNull
  @Override
  public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
    return new DemoRunConfiguration(project, this, "Demo");
  }

  @NotNull
  @Override
  public String getName() {
    return FACTORY_NAME;
  }

  @Nullable
  @Override
  public Class<? extends BaseState> getOptionsClass() {
    return DemoRunConfigurationOptions.class;
  }
}
