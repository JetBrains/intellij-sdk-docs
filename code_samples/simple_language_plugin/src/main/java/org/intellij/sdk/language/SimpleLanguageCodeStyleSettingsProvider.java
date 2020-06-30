// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.*;
import org.jetbrains.annotations.NotNull;

public class SimpleLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
  @NotNull
  @Override
  public Language getLanguage() {
    return SimpleLanguage.INSTANCE;
  }
  
  @Override
  public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
    if (settingsType == SettingsType.SPACING_SETTINGS) {
      consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
      consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
    } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
      consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
    }
  }
  
  @Override
  public String getCodeSample(@NotNull SettingsType settingsType) {
    return "# You are reading the \".properties\" entry.\n" +
                 "! The exclamation mark can also mark text as comments.\n" +
                 "website = https://en.wikipedia.org/\n" +
                 "\n" +
                 "language = English\n" +
                 "# The backslash below tells the application to continue reading\n" +
                 "# the value onto the next line.\n" +
                 "message = Welcome to \\\n" +
                 "          Wikipedia!\n" +
                 "# Add spaces to the key\n" +
                 "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                 "# Unicode\n" +
                 "tab : \\u0009";
  }
}
