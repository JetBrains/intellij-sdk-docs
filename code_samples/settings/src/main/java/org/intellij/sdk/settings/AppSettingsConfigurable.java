// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class AppSettingsConfigurable implements Configurable {

  private AppSettingsPanel myConfigPanel;

  public AppSettingsConfigurable() {
    myConfigPanel = null;
  }

  // TODO: 4/21/20 Is there a way to read what's declared in the EP and return it here?
  /**
   * Returns the visible name of the configurable component.
   * Note, that this method must return the display name
   * that is equal to the display name declared in XML
   * to avoid unexpected errors.
   *
   * @return the visible name of the configurable component
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
    return Objects.nonNull(myConfigPanel) ? myConfigPanel.getPreferred() : null;
  }

  /**
   * Creates new Swing form that enables user to configure the settings.
   * Usually this method is called on the EDT, so it should not take a long time.
   * <p>
   * Also this place is designed to allocate resources (subscriptions/listeners etc.)
   *
   * @return new Swing form to show, or {@code null} if it cannot be created
   * @see #disposeUIResources
   */
  @Nullable
  @Override
  public JComponent createComponent() {
    myConfigPanel = new AppSettingsPanel();
    return myConfigPanel.getPanel();
  }

  /**
   * Indicates whether the Swing form was modified or not.
   * This method is called very often, so it should not take a long time.
   *
   * @return {@code true} if the settings were modified, {@code false} otherwise
   */
  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !myConfigPanel.getPanelTextFieldValue().equals(settings.getMyText());
    modified |= myConfigPanel.getPanelCheckBoxValue() != settings.getMyBool();
    return modified;
  }

  /**
   * Stores the settings from the Swing form to the configurable component.
   * This method is called on EDT upon user's request.
   *
   * @throws ConfigurationException if values cannot be applied
   */
  @Override
  public void apply() throws ConfigurationException {
    AppSettingsState settings = AppSettingsState.getInstance();
      settings.setMyText(myConfigPanel.getPanelTextFieldValue());
      settings.setMyBool(myConfigPanel.getPanelCheckBoxValue());
  }

  /**
   * Loads the settings from the configurable component to the Swing form.
   * This method is called on EDT immediately after the form creation or later upon user's request.
   */
  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    myConfigPanel.setPanelTextFieldValue(settings.getMyText());
    myConfigPanel.setPanelCheckBoxValue(settings.getMyBool());
  }

  /**
   * Notifies the configurable component that the Swing form will be closed.
   * This method should dispose all resources associated with the component.
   */
  @Override
  public void disposeUIResources() {
    myConfigPanel = null;
  }

}
