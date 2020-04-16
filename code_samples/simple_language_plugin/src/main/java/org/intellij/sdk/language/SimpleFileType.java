// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SimpleFileType extends LanguageFileType {
  public static final SimpleFileType INSTANCE = new SimpleFileType();

  private SimpleFileType() {
    super(SimpleLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Simple File";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Simple language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "simple";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return SimpleIcons.FILE;
  }
}
