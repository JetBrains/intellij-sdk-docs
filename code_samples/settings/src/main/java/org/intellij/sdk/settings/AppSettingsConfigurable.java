// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller function for application settings.
 * Interacts with the persistent data class, @org.intellij.sdk.settings.AppSettingsState
 * and the UI component @org.intellij.sdk.settings.AppSettingsComponent.
 * <p>
 * See @com.intellij.openapi.options.Configurable for additional documentation.
 */
public class AppSettingsConfigurable implements Configurable {

  private AppSettingsComponent mySettingsComponent = null;

  /**
   * A constructor with no arguments is required because this implementation is registered
   * as an <code>applicationConfigurable</code> EP
   */
  public AppSettingsConfigurable() {
  }

  // TODO: 4/21/20 Is there a way to read what's declared in the EP and return it here?
  /**
   * Returns the visible name of the configurable component.
   * Must match the string provided in the EP declaration.
   */
  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "SDK: Application Settings Example";
  }

  /**
   * @return component which should be focused when the dialog appears
   * on the screen.
   */
  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  /**
   * Creates new Swing form that enables user to configure the settings.
   *
   * @see #disposeUIResources
   */
  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  /**
   * Indicates whether the Swing form was modified or not.
   */
  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !mySettingsComponent.getUserNameText().equals(settings.userId);
    modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
    return modified;
  }

  /**
   * Stores the settings from the Swing form to the configurable component.
   */
  @Override
  public void apply() throws ConfigurationException {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.userId = null;
    settings.userId = mySettingsComponent.getUserNameText();
    settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
  }

  /**
   * Loads the settings from the configurable component to the Swing form.
   */
  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    mySettingsComponent.setUserNameText(settings.userId);
    mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
  }

  /**
   * Notifies the configurable component that the Swing form will be closed.
   * This method should dispose all resources associated with the component.
   */
  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}
