// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/*
 * Supports storing the application settings in a persistent way.
 * The {@link com.intellij.openapi.components.State State} and {@link Storage}
 * annotations define the name of the data and the filename where these persistent
 * application settings are stored.
 */

@State(
    name = "org.intellij.sdk.settings.AppSettings",
    storages = @Storage("SdkSettingsPlugin.xml")
)
final class AppSettings
    implements PersistentStateComponent<AppSettings.State> {

  static class State {
    @NonNls
    public String userId = "John Smith";
    public boolean ideaStatus = false;
  }

  private State myState = new State();

  static AppSettings getInstance() {
    return ApplicationManager.getApplication()
        .getService(AppSettings.class);
  }

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(@NotNull State state) {
    myState = state;
  }

}
