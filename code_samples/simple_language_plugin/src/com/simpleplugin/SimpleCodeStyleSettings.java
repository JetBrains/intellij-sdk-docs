package com.simpleplugin;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class SimpleCodeStyleSettings extends CustomCodeStyleSettings {
    public SimpleCodeStyleSettings(CodeStyleSettings settings) {
        super("SimpleCodeStyleSettings", settings);
    }
}