// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Storage macro defining name and where (application) these persistent data are stored
@State(
        name = "org.intellij.sdk.settings.AppSettingsState",
        storages = {
                @Storage(value = "$APP_CONFIG$/SdkSettingsPlugin.xml")
        }
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

  /* Persistent data classes
      These are public, so by default they will be written as
      option XML elements in the SdkSettingsPlugin.xml file.
  */
  public String myText;
  public boolean myBool;

  public AppSettingsState() {
    // Set defaults
    setMyText("The quick brown fox jumps over the lazy dog.");
    setMyBool(false);
  }

  // Naming matters
  @NotNull
  public @NonNls String getMyText() {
    return myText;
  }

  public void setMyText(@NotNull String newText) {
    myText = newText;
  }

  public boolean getMyBool() {
    return myBool;
  }

  public void setMyBool(boolean check) {
    myBool = check;
  }


  public static AppSettingsState getInstance() {
    return ServiceManager.getService(AppSettingsState.class);
  }

  /**
   * Saves State to disk.
   */
  @Nullable
  @Override
  public AppSettingsState getState() {
    return this;
  }

  /**
   * Restores State from disk
   */
  @Override
  public void loadState(@NotNull AppSettingsState state) {
    XmlSerializerUtil.copyBean(state, this);
  }

}