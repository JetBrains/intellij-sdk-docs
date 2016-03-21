package com.intellij.tutorials.framework;

import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.*;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.openapi.module.*;
import com.intellij.openapi.roots.*;
import org.jetbrains.annotations.*;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoFramework extends FrameworkTypeEx {
  public static final String FRAMEWORK_ID = "Demo";

  protected DemoFramework() {
    super(FRAMEWORK_ID);
  }

  @NotNull
  @Override
  public FrameworkSupportInModuleProvider createProvider() {
    return new FrameworkSupportInModuleProvider() {
      @NotNull
      @Override
      public FrameworkTypeEx getFrameworkType() {
        return DemoFramework.this;
      }

      @NotNull
      @Override
      public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
        return new FrameworkSupportInModuleConfigurable() {
          @Nullable
          @Override
          public JComponent createComponent() {
            return new JCheckBox("Extra Option");
          }

          @Override
          public void addSupport(@NotNull Module module,
                                 @NotNull ModifiableRootModel model,
                                 @NotNull ModifiableModelsProvider provider) {
            //do what you want here: setup a library, generate a specific file, etc
          }
        };
      }

      @Override
      public boolean isEnabledForModuleType(@NotNull ModuleType type) {
        return true;
      }
    };
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "Demo Framework";
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return AllIcons.Providers.Apache;
  }
}
