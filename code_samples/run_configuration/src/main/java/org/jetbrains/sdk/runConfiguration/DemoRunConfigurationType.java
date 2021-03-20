// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.sdk.runConfiguration;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.NotNullLazyValue;

public class DemoRunConfigurationType extends ConfigurationTypeBase {
  protected DemoRunConfigurationType() {
    super("DemoRunConfiguration",
            "Demo",
            "Demo run configuration type",
            NotNullLazyValue.createValue(() -> AllIcons.General.Information)
    );
    addFactory(new DemoConfigurationFactory(this));
  }
}
