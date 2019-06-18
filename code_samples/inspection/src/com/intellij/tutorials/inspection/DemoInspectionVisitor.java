package com.intellij.tutorials.inspection;

import com.intellij.psi.*;

/**
 * @author Anna Bulenkova
 */
public class DemoInspectionVisitor extends PsiElementVisitor {
  @Override
  public void visitElement(PsiElement element) {
    super.visitElement(element);
  }

  @Override
  public void visitPlainTextFile(PsiPlainTextFile file) {
    super.visitPlainTextFile(file);
  }
}
