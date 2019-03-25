// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.tutorials.inspection;

import com.intellij.codeInspection.InspectionToolProvider;

/**
 * @author Anna Bulenkova
 */
public class DemoInspectionToolProvider implements InspectionToolProvider {
  public Class[] getInspectionClasses() {
    return new Class[]{DemoCodeInspection.class};
  }
}
