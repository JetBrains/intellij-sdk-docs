package org.jetbrains.tutorials.run.configuration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

/**
 * @author Anna Bulenkova
 */
public class DemoConfigurationFactory extends ConfigurationFactory {
  private static final String FACTORY_NAME = "Demo configuration factory";

  protected DemoConfigurationFactory(ConfigurationType type) {
    super(type);
  }

  @Override
  public RunConfiguration createTemplateConfiguration(Project project) {
    return new DemoRunConfiguration(project, this, "Demo");
  }

  @Override
  public String getName() {
    return FACTORY_NAME;
  }
}
