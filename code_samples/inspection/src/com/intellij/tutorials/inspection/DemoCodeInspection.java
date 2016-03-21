package com.intellij.tutorials.inspection;

import com.intellij.codeInspection.*;
import org.jetbrains.annotations.*;

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
