// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.framework;

import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

final class DemoFramework extends FrameworkTypeEx {

  public static final String FRAMEWORK_ID = "org.intellij.sdk.framework.DemoFramework";

  DemoFramework() {
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

          @Override
          public JComponent createComponent() {
            return new JCheckBox("SDK Extra Option");
          }

          @Override
          public void addSupport(@NotNull Module module,
                                 @NotNull ModifiableRootModel model,
                                 @NotNull ModifiableModelsProvider provider) {
            // This is the place to set up a library, generate a specific file, etc
            // and actually add framework support to a module.
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
    return "SDK Demo Framework";
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return SdkIcons.Sdk_default_icon;
  }

}
