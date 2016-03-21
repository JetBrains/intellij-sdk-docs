package org.jetbrains.tutorials.run.configuration;

import com.intellij.execution.configurations.*;
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
