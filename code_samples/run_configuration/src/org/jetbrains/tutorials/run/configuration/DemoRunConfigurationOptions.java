package org.jetbrains.tutorials.run.configuration;

import com.intellij.execution.configurations.RunConfigurationOptions;

/**
 * @author yole
 */
public class DemoRunConfigurationOptions extends RunConfigurationOptions {
  private String myScriptName;

  public String getScriptName() {
    return myScriptName;
  }

  public void setScriptName(String myScriptName) {
    this.myScriptName = myScriptName;
  }
}
