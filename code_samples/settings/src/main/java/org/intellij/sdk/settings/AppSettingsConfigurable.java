// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
*/
public class AppSettingsConfigurable implements Configurable {
  private AppSettingsComponent mySettingsComponent;

  // A default constructor with no arguments is required because this implementation
  // is registered as an applicationConfigurable EP

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "SDK: Application Settings Example";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !mySettingsComponent.getUserNameText().equals(settings.userId);
    modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
    return modified;
  }

  @Override
  public void apply() throws ConfigurationException {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.userId = mySettingsComponent.getUserNameText();
    settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
  }

  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    mySettingsComponent.setUserNameText(settings.userId);
    mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}
