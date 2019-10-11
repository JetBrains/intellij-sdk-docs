package org.jetbrains.tutorials.run.configuration;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

/**
 * @author yole
 */
public class DemoRunConfigurationOptions extends RunConfigurationOptions {
  private final StoredProperty<String> myScriptName = string("").provideDelegate(this, "scriptName");

  public String getScriptName() {
    return myScriptName.getValue(this);
  }

  public void setScriptName(String scriptName) {
    myScriptName.setValue(this, scriptName);
  }
}
