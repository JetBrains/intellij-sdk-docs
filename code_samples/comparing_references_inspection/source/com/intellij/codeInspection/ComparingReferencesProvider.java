package com.intellij.codeInspection;

/**
 * This class provides a list of inspections (classes) for this plugin.
 * Each inspection class should have a corresponding entry in plugin.xml
 * @author max
 */
public class ComparingReferencesProvider implements InspectionToolProvider {
  public Class[] getInspectionClasses() {
    return new Class[]{ComparingReferencesInspection.class};
  }
}
