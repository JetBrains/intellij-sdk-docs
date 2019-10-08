package org.jetbrains.tutorials.run.configuration;

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
  protected void applyEditorTo(@NotNull DemoRunConfiguration demoRunConfiguration) throws ConfigurationException {
    demoRunConfiguration.setScriptName(myScriptName.getComponent().getText());
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return myPanel;
  }

  private void createUIComponents() {
    myScriptName = new LabeledComponent<TextFieldWithBrowseButton>();
    myScriptName.setComponent(new TextFieldWithBrowseButton());
  }
}

