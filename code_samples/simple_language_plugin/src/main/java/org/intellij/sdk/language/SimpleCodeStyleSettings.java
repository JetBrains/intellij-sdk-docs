package org.intellij.sdk.language;

import com.intellij.psi.codeStyle.*;

public class SimpleCodeStyleSettings extends CustomCodeStyleSettings {
  public SimpleCodeStyleSettings(CodeStyleSettings settings) {
    super("SimpleCodeStyleSettings", settings);
  }
}