package org.jetbrains.sdk.runConfiguration;

import com.intellij.openapi.options.*;
import com.intellij.openapi.ui.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoSettingsEditor extends SettingsEditor<DemoRunConfiguration> {
  private JPanel myPanel;
  private LabeledComponent<TextFieldWithBrowseButton> myScriptName;

  @Override
  protected void resetEditorFrom(DemoRunConfiguration demoRunConfiguration) {
    myScriptName.getComponent().setText(demoRunConfiguration.getScriptName());
  }

  @Override
  protected void applyEditorTo(@NotNull DemoRunConfiguration demoRunConfiguration) {
    demoRunConfiguration.setScriptName(myScriptName.getComponent().getText());
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return myPanel;
  }

  private void createUIComponents() {
    myScriptName = new LabeledComponent<>();
    myScriptName.setComponent(new TextFieldWithBrowseButton());
  }
}

