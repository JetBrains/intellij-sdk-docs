// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.sdk.runConfiguration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DemoRunConfiguration extends RunConfigurationBase<DemoRunConfigurationOptions> {
  protected DemoRunConfiguration(Project project, ConfigurationFactory factory, String name) {
    super(project, factory, name);
  }

  @NotNull
  @Override
  protected DemoRunConfigurationOptions getOptions() {
    return (DemoRunConfigurationOptions) super.getOptions();
  }

  public String getScriptName() {
    return getOptions().getScriptName();
  }

  public void setScriptName(String scriptName) {
    getOptions().setScriptName(scriptName);
  }

  @NotNull
  @Override
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    return new DemoSettingsEditor();
  }

  @Override
  public void checkConfiguration() {
  }

  @Nullable
  @Override
  public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) {
    return new CommandLineState(executionEnvironment) {
      @NotNull
      @Override
      protected ProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine commandLine = new GeneralCommandLine(getOptions().getScriptName());
        OSProcessHandler processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
      }
    };
  }
}
