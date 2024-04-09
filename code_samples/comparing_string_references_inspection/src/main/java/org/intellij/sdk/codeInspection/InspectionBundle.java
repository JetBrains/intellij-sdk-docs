// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.codeInspection;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public final class InspectionBundle {

  @NonNls
  public static final String BUNDLE = "messages.InspectionBundle";

  private static final DynamicBundle ourInstance = new DynamicBundle(InspectionBundle.class, BUNDLE);

  private InspectionBundle() {
  }

  public static @Nls String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
                                    Object @NotNull ... params) {
    return ourInstance.getMessage(key, params);
  }

}
