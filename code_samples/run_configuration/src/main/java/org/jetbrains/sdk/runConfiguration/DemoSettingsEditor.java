// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.sdk.runConfiguration;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DemoSettingsEditor extends SettingsEditor<DemoRunConfiguration> {

  private final JPanel myPanel;
  private final TextFieldWithBrowseButton scriptPathField;

  public DemoSettingsEditor() {
    scriptPathField = new TextFieldWithBrowseButton();
    scriptPathField.addBrowseFolderListener("Select Script File", null, null,
        FileChooserDescriptorFactory.createSingleFileDescriptor());
    myPanel = FormBuilder.createFormBuilder()
        .addLabeledComponent("Script file", scriptPathField)
        .getPanel();
  }

  @Override
  protected void resetEditorFrom(DemoRunConfiguration demoRunConfiguration) {
    scriptPathField.setText(demoRunConfiguration.getScriptName());
  }

  @Override
  protected void applyEditorTo(@NotNull DemoRunConfiguration demoRunConfiguration) {
    demoRunConfiguration.setScriptName(scriptPathField.getText());
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return myPanel;
  }

}
