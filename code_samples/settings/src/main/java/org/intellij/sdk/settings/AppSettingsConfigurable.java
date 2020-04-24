// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

// Provides controller functionality for application settings.
// See com.intellij.openapi.options.Configurable for additional documentation.
public class AppSettingsConfigurable implements Configurable {

  private AppSettingsComponent mySettingsComponent = null;

  // A default constructor with no arguments is required because this implementation
  // is registered as an applicationConfigurable EP
  public AppSettingsConfigurable() {
  }

  // Returns the visible name of the configurable component.
  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "SDK: Application Settings Example";
  }

  // Returns the component which should be focused when the dialog appears on the screen.
  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  // Creates new Swing form that enables user to configure the settings.
  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  // Indicates whether the Swing form was modified or not.
  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !mySettingsComponent.getUserNameText().equals(settings.userId);
    modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
    return modified;
  }

  // Stores the settings from the Swing form to the configurable component.
  @Override
  public void apply() throws ConfigurationException {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.userId = mySettingsComponent.getUserNameText();
    settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
  }

  // Loads the settings from the configurable State to the Component.
  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    mySettingsComponent.setUserNameText(settings.userId);
    mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
  }

  // Notifies the configurable component that the Swing form will be closed.
  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}
