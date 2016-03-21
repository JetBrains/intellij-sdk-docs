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
