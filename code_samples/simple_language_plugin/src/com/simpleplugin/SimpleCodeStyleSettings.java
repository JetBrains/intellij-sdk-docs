package com.simpleplugin;

import com.intellij.psi.codeStyle.*;

public class SimpleCodeStyleSettings extends CustomCodeStyleSettings {
  public SimpleCodeStyleSettings(CodeStyleSettings settings) {
    super("SimpleCodeStyleSettings", settings);
  }
}