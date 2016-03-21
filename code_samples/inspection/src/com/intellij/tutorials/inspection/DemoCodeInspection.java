package com.intellij.tutorials.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class DemoCodeInspection extends LocalInspectionTool {
  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return "Demo Inspection";
  }

  @NotNull
  @Override
  public DemoInspectionVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new DemoInspectionVisitor();
  }
}
