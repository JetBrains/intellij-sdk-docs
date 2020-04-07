// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//

package org.intellij.sdk.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.util.xmlb.XmlSerializer;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AppSettingsConfiguration implements PersistentStateComponent<AppSettingsConfiguration.AscState> {

  AscState myState = new AscState();

  static class AscState {
    private StringBuffer text = new StringBuffer("");
    private Boolean[] data = {  new Boolean(false),
                                new Boolean(false) };

    public AscState() {}

    public @NotNull String getText() {
      return text.toString();
    }

    public void setText(@NotNull String newText) {
      text.delete(0, text.length());
      text.append(newText);
    }

    public @NotNull Boolean getBool(@NotNull int place) {
      if (place ==0) {
        return new Boolean(data[0]);
      } else {
        return new Boolean(data[1]);
      }
    }

    public void setBools(@NotNull boolean[] checks) {
      if (checks.length >= 2) {
        data[0] = checks[0];
        data[1] = checks[1];
      }
    }

  }


  /**
   * Saves all public variables to disk.
   * @return a component state. All properties, public and annotated fields are serialized. Only values, which differ
   * from the default (i.e., the value of newly instantiated class) are serialized. {@code null} value indicates
   * that the returned state won't be stored, as a result previously stored state will be used.
   * @see XmlSerializer
   */
  @Nullable
  @Override
  public AscState getState() {
    return myState;
  }

  /**
   * Restores State from disk
   * This method is called when new component state is loaded. The method can and will be called several times, if
   * config files were externally changed while IDE was running.
   * <p>
   * State object should be used directly, defensive copying is not required.
   *
   * @param state loaded component state
   * @see XmlSerializerUtil#copyBean(Object, Object)
   */
  @Override
  public void loadState(@NotNull AscState state) {
    this.myState = state;
  }

}