package org.jetbrains.sdk.runConfiguration;

import com.intellij.execution.configurations.*;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoRunConfigurationType implements ConfigurationType {
  @NotNull
  @Override
  public String getDisplayName() {
    return "Demo";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "Demo Run Configuration Type";
  }

  @Override
  public Icon getIcon() {
    return AllIcons.General.Information;
  }

  @NotNull
  @Override
  public String getId() {
    return "DEMO_RUN_CONFIGURATION";
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{new DemoConfigurationFactory(this)};
  }
}
