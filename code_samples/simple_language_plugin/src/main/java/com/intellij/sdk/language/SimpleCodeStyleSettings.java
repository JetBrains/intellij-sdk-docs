// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.sdk.language;

import com.intellij.psi.codeStyle.*;

public class SimpleCodeStyleSettings extends CustomCodeStyleSettings {
  public SimpleCodeStyleSettings(CodeStyleSettings settings) {
    super("SimpleCodeStyleSettings", settings);
  }
}